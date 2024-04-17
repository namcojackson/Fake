package com.canon.cusa.s21.batch.NWB.NWBB402001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static com.canon.cusa.s21.framework.common.S21StringUtil.isEquals;
import static com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor.findByKey;
import static com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor.findByKeyForUpdate;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import business.db.CPOTMsg;
import business.db.DS_CPO_RTRN_DTLTMsg;
import business.parts.NWXC005001PMsg;
import business.parts.NWZC044001PMsg;

import com.canon.cusa.s21.api.NWZ.NWZC044001.NWZC044001;
import com.canon.cusa.s21.api.NWZ.NWZC186001.NWZC186001;
import com.canon.cusa.s21.api.NWZ.NWZC187001.NWZC187001;
import com.canon.cusa.s21.common.NWX.NWXC005001.NWXC005001ValidationBean;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.HLD_LVL;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.HLD_RSN;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_HDR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTRN_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX_SRC_TP;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;

/**
 *<pre>
 * Return Validation Batch
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/10/05   Fujitsu         M.Ohno          Create          QC#14612
 * 2016/10/18   Fujitsu         M.Ohno          Update          QC#15097
 * 2017/06/07   Fujitsu         M.Yamada        Update          QC#17941
 *</pre>
 */
public class NWBB402001 extends S21BatchMain {
    /** NWAM0103E */
    private static final String NWAM0103E = "NWAM0103E";

    /** NWAM0188I */
    private static final String NWAM0188I = "NWAM0188I";

    /** NWAM0189E */
    private static final String NWAM0189E = "NWAM0189E";

    /** NWAM0196E */
    private static final String NWAM0196E = "NWAM0196E";

    /** DEFAULT_FETCH_SIZE */
    public static final int DEFAULT_FETCH_SIZE = 1000;

    /** Sls Dt */
    private String slsDt;

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Termination Code */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** Total Count */
    private int totalRecCnt = 0;

    /** Total Normal Count */
    private int normalRecCnt = 0;

    /** Total Error Count */
    private int errRecCnt = 0;

    /** timestamp of target data(DS_CPO_RTRN_DTL) */
    private final Map<String, DS_CPO_RTRN_DTLTMsg> targetTimestampRD = new HashMap<String, DS_CPO_RTRN_DTLTMsg>();

    /** timestamp of target data(CPO) */
    private final Map<String, CPOTMsg> targetTimestampC = new HashMap<String, CPOTMsg>();

    /** SQL access parts */
    private S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

//    private NWBB402001() {
//        ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
//    }

    /**
     * <pre>
     * A main method for batch application start.
     * </pre>
     * @param args String[]
     */
    public static void main(String[] args) {
        new NWBB402001().executeBatch();
    }

    @Override
    protected void initRoutine() {
        // --------------------------------------------------
        // GLBL_CMPY_CD
        // --------------------------------------------------
        glblCmpyCd = getGlobalCompanyCode();
        if (!hasValue(glblCmpyCd)) {
            throw new S21AbendException(NWAM0103E);
        }

        // --------------------------------------------------
        // SALES_DT
        // --------------------------------------------------
        slsDt = ZYPDateUtil.getSalesDate(glblCmpyCd);

        // TERM_CD
        termCd = TERM_CD.NORMAL_END;

    }

    @Override
    protected void mainRoutine() {
        // --------------------------------------------------
        // 1. get [HLD_RSN] informations.
        // --------------------------------------------------

        // --------------------------------------------------
        // 2. execute Validation.
        // --------------------------------------------------
        final List<NWZC044001PMsg> hldPMsgList = execValidation();

        // --------------------------------------------------
        // 4. add [HLD] by '(NWZC044001)Add Hold Info API'.
        // --------------------------------------------------
        if (!isEmpty(hldPMsgList)) {
            execAddHldInfo(hldPMsgList);
        }

        if (errRecCnt > 0) {
            this.termCd = TERM_CD.WARNING_END;
        } else {
            this.termCd = TERM_CD.NORMAL_END;
        }
    }

    @Override
    protected void termRoutine() {
        setTermState(this.termCd, this.normalRecCnt, this.errRecCnt, this.totalRecCnt);

    }

    private static boolean isEmpty(Collection collection) {
        return collection == null || collection.isEmpty();
    }

    private List<NWZC044001PMsg> execValidation() {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {

            final Map<String, String> ssmParam = new HashMap<String, String>();

            ssmParam.put("glblCmpyCd", glblCmpyCd);
            ssmParam.put("rtrnLineStsCd", RTRN_LINE_STS.BOOKED);

            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(DEFAULT_FETCH_SIZE);
            execParam.setMaxRows(0);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

            stmt = this.ssmLLClient.createPreparedStatement("getRtrnDtl", ssmParam, execParam);
            rs = stmt.executeQuery();

            return (List<NWZC044001PMsg>) (new OrderValidator()).doProcessQueryResult(rs);

        } catch (SQLException e) {
            super.sqlExceptionHandler(e);
            return null;
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    /** */
    private final class OrderValidator {
        /** */
        private final Cache cache = new Cache();

        /** */
        private final Map<String, String> validationApiMap;

        private OrderValidator() {

            this.validationApiMap = new HashMap<String, String>();
            // HDD Removal Hold API
            validationApiMap.put(HLD_RSN.HDD_REMOVAL, NWZC186001.class.getName());
            // HDD Erase Hold API
            validationApiMap.put(HLD_RSN.HDD_ERASE, NWZC187001.class.getName());
        }

        //@Override
        protected List<NWZC044001PMsg> doProcessQueryResult(ResultSet rs) throws SQLException {

            try {

                // records.
                rs.next();
                int records = rs.getRow();
                rs.first();

                if (records == 0) {
                    infoLogOut(NWAM0188I, new DS_CPO_RTRN_DTLTMsg().getTableName());
                    return emptyList();
                }

                return validate(rs);

            } finally {

            }
        }

        protected List<NWZC044001PMsg> validate(ResultSet rs) throws SQLException {

            try {
                final List<NWZC044001PMsg> hldPMsgList = new ArrayList<NWZC044001PMsg>();

                final Set<String> doneOrderSet = new HashSet<String>();

                do {

                    // Order Info
                    final OrderInfo orderInfo = new OrderInfo(rs);

                    // QC#17941
                    DS_CPO_RTRN_DTLTMsg rdTMsg = new DS_CPO_RTRN_DTLTMsg();
                    ZYPEZDItemValueSetter.setValue(rdTMsg.cpoOrdNum, orderInfo.getTrxHdrNum());
                    ZYPEZDItemValueSetter.setValue(rdTMsg.dsCpoRtrnLineNum, orderInfo.getTrxLineNum());
                    ZYPEZDItemValueSetter.setValue(rdTMsg.dsCpoRtrnLineSubNum, orderInfo.getTrxLineSubNum());
                    ZYPEZDItemValueSetter.setValue(rdTMsg.ezUpTime, orderInfo.getEzuptimeRD());
                    ZYPEZDItemValueSetter.setValue(rdTMsg.ezUpTimeZone, orderInfo.getEzuptimezoneRD());
                    targetTimestampRD.put(//
                            createCacheKey(orderInfo.getTrxHdrNum(), orderInfo.getTrxLineNum(), orderInfo.getTrxLineSubNum()), rdTMsg);

                    // is changed 'CPO_ORD_NUM'?
                    final String doneOrderKey = createCacheKey(orderInfo.getCpoSrcTpCd(), orderInfo.getTrxHdrNum());
                    if (!doneOrderSet.contains(doneOrderKey)) {
                        this.cache.cpo.clear();
                        this.cache.dsCpoRtrnDtl.clear();
                        this.cache.doneCpoDtlLvlValidationSet.clear();
                        doneOrderSet.add(doneOrderKey);
                        // QC#17941
                        CPOTMsg cTMsg = new CPOTMsg();
                        ZYPEZDItemValueSetter.setValue(cTMsg.cpoOrdNum, orderInfo.getTrxHdrNum());
                        ZYPEZDItemValueSetter.setValue(cTMsg.ezUpTime, orderInfo.getEzuptimeC());
                        ZYPEZDItemValueSetter.setValue(cTMsg.ezUpTimeZone, orderInfo.getEzuptimezoneC());
                        targetTimestampC.put(orderInfo.getTrxHdrNum(), cTMsg);
                    }

                    final String hldRsnCd = orderInfo.getHldRsnCd();

                    // ***** validation
                    if (this.validationApiMap.keySet().contains(hldRsnCd)) {
                        hldPMsgList.addAll(validation(orderInfo));
                    }
                    // }

                } while (rs.next());

                return hldPMsgList;
            } finally {
                // do nothing
            }
        }

        List<NWZC044001PMsg> validation(OrderInfo orderInfo) {

            final String cpoSrcTpCd = orderInfo.getCpoSrcTpCd();
            final String trxHdrNum = orderInfo.getTrxHdrNum();
            final String trxLineNum = orderInfo.getTrxLineNum();
            final String trxLineSubNum = orderInfo.getTrxLineSubNum();

            final String hldLvlCd = orderInfo.getHldLvlCd();
            final String hldRsnCd = orderInfo.getHldRsnCd();

            /**************************************************
             * CPO_LEVEL
             **************************************************/
            if (isEquals(HLD_LVL.CPO_LEVEL, hldLvlCd)) {
                return emptyList();

                /**************************************************
                 * CPO_DETAIL_LEVEL
                 **************************************************/
            } else if (isEquals(HLD_LVL.CPO_DETAIL_LEVEL, hldLvlCd)) {

                // already done validation?
                final String doneValidationKey = createCacheKey(cpoSrcTpCd, trxHdrNum, trxLineNum, trxLineSubNum, hldRsnCd);
                if (this.cache.doneCpoDtlLvlValidationSet.contains(doneValidationKey)) {
                    return emptyList();
                } else {
                    this.cache.doneCpoDtlLvlValidationSet.add(doneValidationKey);
                }

                final CPOTMsg cpoTMsg = getCpo(trxHdrNum);
                final DS_CPO_RTRN_DTLTMsg dsCpoRtrnDtlTMsg = getDsCpoRtrnDtl(trxHdrNum, trxLineNum, trxLineSubNum);
                final String allLineCreditFlg = null;

                return callValidationAPI(hldRsnCd, cpoTMsg, dsCpoRtrnDtlTMsg, allLineCreditFlg);

                /**************************************************
                 * SHIPPING_PLAN_LEVEL
                 **************************************************/
            } else if (isEquals(HLD_LVL.SHIPPING_PLAN_LEVEL, hldLvlCd)) {
                return emptyList();

            }

            return emptyList();
        }

        List<NWZC044001PMsg> callValidationAPI(String hldRsnCd, CPOTMsg cpoTMsg, DS_CPO_RTRN_DTLTMsg dsCpoRtrnDtlTMsg, String allLineCreditFlg) {

            String cpoOrdNum = null;
            String cpoDtlLineNum = null;
            String cpoDtlLineSubNum = null;

            // DS_CPO_RTRN_DTL
            if (dsCpoRtrnDtlTMsg != null) {
                cpoOrdNum = dsCpoRtrnDtlTMsg.cpoOrdNum.getValue();
                cpoDtlLineNum = dsCpoRtrnDtlTMsg.dsCpoRtrnLineNum.getValue();
                cpoDtlLineSubNum = dsCpoRtrnDtlTMsg.dsCpoRtrnLineSubNum.getValue();
                // CPO
            } else {
                cpoOrdNum = cpoTMsg.cpoOrdNum.getValue();
            }

            final NWXC005001PMsg apiPMsg = new NWXC005001PMsg();
            setValue(apiPMsg.glblCmpyCd, glblCmpyCd);
            setValue(apiPMsg.slsDt, slsDt);
            setValue(apiPMsg.cpoOrdNum_I, cpoOrdNum);
            setValue(apiPMsg.cpoDtlLineNum_I, cpoDtlLineNum);
            setValue(apiPMsg.cpoDtlLineSubNum_I, cpoDtlLineSubNum);

            final NWXC005001ValidationBean apiBean = new NWXC005001ValidationBean(apiPMsg, cpoTMsg, null, null);

            // Validation API
            S21ApiCommonBase validationAPI = null;
            try {
                validationAPI = (S21ApiCommonBase) Class.forName(this.validationApiMap.get(hldRsnCd)).newInstance();
            } catch (Exception e) {
                throw toS21AbendException(e);
            }

            /**************************************************
             * NWZC186001 : HDD Removal Hold API
             **************************************************/
            if (validationAPI instanceof NWZC186001) {
                ((NWZC186001) validationAPI).execute(apiBean, ONBATCH_TYPE.BATCH);

                /**************************************************
                 * NWZC187001 : HDD Erase Hold API
                 **************************************************/
            } else if (validationAPI instanceof NWZC187001) {
                ((NWZC187001) validationAPI).execute(apiBean, ONBATCH_TYPE.BATCH);

            } else {
                return emptyList();
            }

            return toHldPMsg(hldRsnCd, apiPMsg);
        }

        List<NWZC044001PMsg> toHldPMsg(String hldRsnCd, NWXC005001PMsg resApiPMsg) {

            final List<NWZC044001PMsg> hldPMsgList = new ArrayList<NWZC044001PMsg>();

            if (resApiPMsg.xxMsgIdList.getValidCount() > 0) {

                String errMessageId = resApiPMsg.xxMsgIdList.no(0).xxMsgId.getValue();
                infoLogOut(errMessageId);
                throw new S21AbendException(errMessageId);
            }

            final String rtnHldRsnCd = resApiPMsg.hldRsnCd.getValue();

            if (hasValue(rtnHldRsnCd)) {

                final NWZC044001PMsg hldPMsg = new NWZC044001PMsg();
                hldPMsgList.add(hldPMsg);

                setValue(hldPMsg.glblCmpyCd, glblCmpyCd);
                setValue(hldPMsg.cpoOrdNum, resApiPMsg.cpoOrdNum_O);
                setValue(hldPMsg.cpoDtlLineNum, resApiPMsg.cpoDtlLineNum_O);
                setValue(hldPMsg.cpoDtlLineSubNum, resApiPMsg.cpoDtlLineSubNum_O);
                setValue(hldPMsg.shpgPlnNum, resApiPMsg.shpgPlnNum_O);
                setValue(hldPMsg.hldRsnCd, rtnHldRsnCd);
                setValue(hldPMsg.slsDt, slsDt);
                setValue(hldPMsg.trxSrcTpCd, TRX_SRC_TP.WHOLE_SALES_RETURN);
            }

            return hldPMsgList;
        }

        private CPOTMsg getCpo(String cpoOrdNum) {

            final String cacheKey = cpoOrdNum;

            CPOTMsg resTMsg = this.cache.cpo.get(cacheKey);

            if (resTMsg == null) {
                CPOTMsg reqTMsg = new CPOTMsg();
                reqTMsg.glblCmpyCd.setValue(glblCmpyCd);
                reqTMsg.cpoOrdNum.setValue(cpoOrdNum);
                resTMsg = (CPOTMsg) findByKey(reqTMsg);
                if (resTMsg != null) {
                    this.cache.cpo.put(cacheKey, resTMsg);
                }
            }

            return resTMsg;
        }

        private DS_CPO_RTRN_DTLTMsg getDsCpoRtrnDtl(String cpoOrdNum, String cpoDtlLineNum, String cpoDtlLineSubNum) {

            final String cacheKey = createCacheKey(cpoOrdNum, cpoDtlLineNum, cpoDtlLineSubNum);

            DS_CPO_RTRN_DTLTMsg resTMsg = this.cache.dsCpoRtrnDtl.get(cacheKey);

            if (resTMsg == null) {
                DS_CPO_RTRN_DTLTMsg reqTMsg = new DS_CPO_RTRN_DTLTMsg();
                reqTMsg.glblCmpyCd.setValue(glblCmpyCd);
                reqTMsg.cpoOrdNum.setValue(cpoOrdNum);
                reqTMsg.dsCpoRtrnLineNum.setValue(cpoDtlLineNum);
                reqTMsg.dsCpoRtrnLineSubNum.setValue(cpoDtlLineSubNum);
                resTMsg = (DS_CPO_RTRN_DTLTMsg) findByKey(reqTMsg);
                if (resTMsg != null) {
                    this.cache.dsCpoRtrnDtl.put(cacheKey, resTMsg);
                }
            }

            return resTMsg;
        }
    }

    /** */
    private static class OrderInfo {
        /** */
        private String cpoSrcTpCd;

        /** */
        private String trxHdrNum;

        /** */
        private String trxLineNum;

        /** */
        private String trxLineSubNum;

        /** */
        private String hldRsnCd;

        /** */
        private String hldRsnNm;

        /** */
        private String hldLvlCd;

        /** */
        private String exReqFlg;

        // QC#17941
        /** RTN_DTL.EZUPTIME */
        private String ezuptimeRD;

        /** RTN_DTL.EZUPTIMEZONE */
        private String ezuptimezoneRD;

        /** CPO.EZUPTIME */
        private String ezuptimeC;

        /** CPO.EZUPTIMEZONE */
        private String ezuptimezoneC;

        private OrderInfo(ResultSet rs) throws SQLException {

            this.trxHdrNum = rs.getString("CPO_ORD_NUM");
            this.trxLineNum = rs.getString("DS_CPO_RTRN_LINE_NUM");
            this.trxLineSubNum = rs.getString("DS_CPO_RTRN_LINE_SUB_NUM");
            this.cpoSrcTpCd = rs.getString("CPO_SRC_TP_CD");
            this.hldRsnCd = rs.getString("HLD_RSN_CD");
            this.hldRsnNm = rs.getString("HLD_RSN_NM");
            this.hldLvlCd = rs.getString("HLD_LVL_CD");
            this.exReqFlg = rs.getString("EX_REQ_FLG");
            // QC#17941
            this.ezuptimeRD     = rs.getString("EZUPTIME_RD");
            this.ezuptimezoneRD = rs.getString("EZUPTIMEZONE_RD");
            this.ezuptimeC      = rs.getString("EZUPTIME_C");
            this.ezuptimezoneC  = rs.getString("EZUPTIMEZONE_C");
        }

        private String getTrxHdrNum() {
            return trxHdrNum;
        }

        private String getTrxLineNum() {
            return trxLineNum;
        }

        private String getTrxLineSubNum() {
            return trxLineSubNum;
        }

        private String getCpoSrcTpCd() {
            return cpoSrcTpCd;
        }

        public String getExReqFlg() {
            return exReqFlg;
        }

        public String getHldLvlCd() {
            return hldLvlCd;
        }

        public String getHldRsnCd() {
            return hldRsnCd;
        }

        public String getHldRsnNm() {
            return hldRsnNm;
        }

        // QC#17941
        public String getEzuptimeRD() {
            return ezuptimeRD;
        }

        public String getEzuptimezoneRD() {
            return ezuptimezoneRD;
        }

        public String getEzuptimeC() {
            return ezuptimeC;
        }

        public String getEzuptimezoneC() {
            return ezuptimezoneC;
        }
    }

    private String createCacheKey(String... strs) {
        StringBuilder sb = new StringBuilder();
        for (String str : strs) {
            sb.append(str).append(",");
        }
        return sb.toString();
    }

    /** */
    private static final class Cache {

        /** */
        private final Map<String, CPOTMsg> cpo = new HashMap<String, CPOTMsg>();

        /** */
        private final Map<String, DS_CPO_RTRN_DTLTMsg> dsCpoRtrnDtl = new HashMap<String, DS_CPO_RTRN_DTLTMsg>();

        /** */
        private final Set<String> doneCpoDtlLvlValidationSet = new HashSet<String>();
    }

    private static S21AbendException toS21AbendException(Exception e) {
        if (e instanceof S21AbendException) {
            return (S21AbendException) e;
        } else {
            return new S21AbendException(e);
        }
    }

    private void execAddHldInfo(List<NWZC044001PMsg> hldPMsgList) {

        try {

            final Map<String, List<NWZC044001PMsg>> addHldApiPMsgMap = new HashMap<String, List<NWZC044001PMsg>>();

            // --------------------------------------------------
            // grouping [NWZC044001PMsg] by 'CPO_ORD_NUM'.
            // --------------------------------------------------
            for (NWZC044001PMsg hldPMsg : hldPMsgList) {
                String cpoOrdNum = hldPMsg.cpoOrdNum.getValue();
                String groupingKey = cpoOrdNum;
                if (!addHldApiPMsgMap.containsKey(groupingKey)) {
                    addHldApiPMsgMap.put(groupingKey, new ArrayList<NWZC044001PMsg>());
                }
                addHldApiPMsgMap.get(groupingKey).add(hldPMsg);
            }

            this.totalRecCnt  = 0;
            this.normalRecCnt = 0;
            this.errRecCnt    = 0;
            // --------------------------------------------------
            // records lock & status check.
            // --------------------------------------------------
            List<String> lockedOrderNumList = new ArrayList<String>(); // QC#17941
            cpoOrdNumLoop: for (Entry<String, List<NWZC044001PMsg>> entry : addHldApiPMsgMap.entrySet()) {

                String cpoOrdNum = entry.getKey();
                // QC#17941
                if (lockedOrderNumList.contains(cpoOrdNum)) {
                    continue;
                }
                CPOTMsg cTMsg = targetTimestampC.get(cpoOrdNum); // QC#17941

                // CPO
                final CPOTMsg cpoTMsg = lockCpo(cpoOrdNum);
                if (cpoTMsg == null // QC#17941
                        || !ZYPDateUtil.isSameTimeStamp(cTMsg.ezUpTime.getValue(), cTMsg.ezUpTimeZone.getValue(), cpoTMsg.ezUpTime.getValue(), cpoTMsg.ezUpTimeZone.getValue())) { // QC#17941
                    // lock failure -> next 'CPO_ORD_NUM'
                    infoLogOut(NWAM0196E, S21StringUtil.concatStrings("CPO(", cpoOrdNum, ")"));
//                    addHldApiPMsgMap.remove(entry.getKey());
                    lockedOrderNumList.add(cpoOrdNum); // QC#17941
                    rollback(); // QC#17941
                    continue;
                } else {
                    // ORD_HDR_STS != 'VALIDATED'
                    String ordHdrStsCd = cpoTMsg.ordHdrStsCd.getValue();
                    if (!asList(ORD_HDR_STS.VALIDATED).contains(ordHdrStsCd)) {
//                        addHldApiPMsgMap.remove(entry.getKey());
                        lockedOrderNumList.add(cpoOrdNum); // QC#17941
                        rollback(); // QC#17941
                       continue;
                    }
                }

                final Iterator<NWZC044001PMsg> it = entry.getValue().iterator();
                while (it.hasNext()) {

                    final NWZC044001PMsg addHldApiPMsg = it.next();
                    String cpoDtlLineNum = addHldApiPMsg.cpoDtlLineNum.getValue();
                    String cpoDtlLineSubNum = addHldApiPMsg.cpoDtlLineSubNum.getValue();
                    // QC#17941
                    DS_CPO_RTRN_DTLTMsg rdTMsg = targetTimestampRD.get(S21StringUtil.concatStrings(//
                            cpoOrdNum, ",", cpoDtlLineNum, ",", cpoDtlLineSubNum, ","));

                    // DS_CPO_RTRN_DTL
                    if (hasValue(cpoDtlLineNum) && hasValue(cpoDtlLineSubNum)) {
                        final DS_CPO_RTRN_DTLTMsg dsCpoRtrnDtlTMsg = lockDsCpoRtrnDtl(cpoOrdNum, cpoDtlLineNum, cpoDtlLineSubNum);
                        if (dsCpoRtrnDtlTMsg == null || rdTMsg == null // QC#17941
                                || !ZYPDateUtil.isSameTimeStamp(rdTMsg.ezUpTime.getValue(), rdTMsg.ezUpTimeZone.getValue(), dsCpoRtrnDtlTMsg.ezUpTime.getValue(), dsCpoRtrnDtlTMsg.ezUpTimeZone.getValue())) { // QC#17941
                            // lock failure -> next 'DS_CPO_RTRN_DTL'
                            infoLogOut(NWAM0196E, S21StringUtil.concatStrings("DS_CPO_RTRN_DTL(", cpoOrdNum, ".", cpoDtlLineNum, ".", cpoDtlLineSubNum, ")"));
//                            addHldApiPMsgMap.remove(entry.getKey());
                            lockedOrderNumList.add(cpoOrdNum); // QC#17941
                            rollback(); // QC#17941
                            continue cpoOrdNumLoop;
                        }
                    }
                }
                // --------------------------------------------------
                // call [NWZC044001 : Add Hold Info API]
                // --------------------------------------------------
                final List<NWZC044001PMsg> addHldApiPMsgList = entry.getValue();

                this.totalRecCnt += addHldApiPMsgList.size();

                if (!isEmpty(addHldApiPMsgList)) {
                    boolean isNormalEnd = callAddHldInfoAPI(addHldApiPMsgList);
                    if (isNormalEnd) {
                        commit();
                        this.normalRecCnt += addHldApiPMsgList.size();
                    } else {
                        rollback();
                    }
                }
            }

//            // --------------------------------------------------
//            // call [NWZC044001 : Add Hold Info API]
//            // --------------------------------------------------
//            this.totalRecCnt = 0;
//            this.normalRecCnt = 0;
//            this.errRecCnt = 0;
//
//            for (Entry<String, List<NWZC044001PMsg>> entry : addHldApiPMsgMap.entrySet()) {
//
//                final List<NWZC044001PMsg> addHldApiPMsgList = entry.getValue();
//
//                this.totalRecCnt += addHldApiPMsgList.size();
//
//                if (!isEmpty(addHldApiPMsgList)) {
//                    boolean isNormalEnd = callAddHldInfoAPI(addHldApiPMsgList);
//                    if (isNormalEnd) {
//                        commit();
//                        this.normalRecCnt += addHldApiPMsgList.size();
//                    } else {
//                        rollback();
//                    }
//                }
//            }

            this.errRecCnt = this.totalRecCnt - this.normalRecCnt;

        } finally {
            // do nothing
        }
    }

    private CPOTMsg lockCpo(String cpoOrdNum) {

        CPOTMsg reqCpoTMsg = new CPOTMsg();
        reqCpoTMsg.glblCmpyCd.setValue(glblCmpyCd);
        reqCpoTMsg.cpoOrdNum.setValue(cpoOrdNum);

        return (CPOTMsg) findByKeyForUpdate(reqCpoTMsg);
    }

    private DS_CPO_RTRN_DTLTMsg lockDsCpoRtrnDtl(String cpoOrdNum, String cpoDtlLineNum, String cpoDtlLineSubNum) {

        DS_CPO_RTRN_DTLTMsg reqTMsg = new DS_CPO_RTRN_DTLTMsg();
        reqTMsg.glblCmpyCd.setValue(glblCmpyCd);
        reqTMsg.cpoOrdNum.setValue(cpoOrdNum);
        reqTMsg.dsCpoRtrnLineNum.setValue(cpoDtlLineNum);
        reqTMsg.dsCpoRtrnLineSubNum.setValue(cpoDtlLineSubNum);

        return (DS_CPO_RTRN_DTLTMsg) findByKeyForUpdate(reqTMsg);
    }

    private boolean callAddHldInfoAPI(List<NWZC044001PMsg> apiPMsgList) {

        // --------------------------------------------------
        // NWZC044001 : Add Hold Info API
        // --------------------------------------------------
        new NWZC044001().execute(apiPMsgList, ONBATCH_TYPE.BATCH);

        boolean isNormalEnd = true;

        for (NWZC044001PMsg pMsg : apiPMsgList) {
            if (pMsg.xxMsgIdList.getValidCount() > 0) {
                isNormalEnd = false;
                for (int i = 0; i < pMsg.xxMsgIdList.getValidCount(); i++) {
                    final String xxMsgId = pMsg.xxMsgIdList.no(i).xxMsgId.getValue();
                    infoLogOut(NWAM0189E, createApiErrMsgByAddHoldInfoAPI(pMsg, xxMsgId));
                }
            }
        }

        return isNormalEnd;
    }

    private String createApiErrMsgByAddHoldInfoAPI(NWZC044001PMsg pMsg, String errMsgId) {

        String cpoOrdNum = pMsg.cpoOrdNum.getValue();
        String cpoDtlLineNum = pMsg.cpoDtlLineNum.getValue();
        String cpoDtlLineSubNum = pMsg.cpoDtlLineSubNum.getValue();
        String shpgPlnNum = pMsg.shpgPlnNum.getValue();

        return createApiErrMsg("[NWZC044001:Add Hold Info API]", cpoOrdNum, cpoDtlLineNum, cpoDtlLineSubNum, shpgPlnNum, errMsgId);
    }

    /**
     * <pre>
     * Edit error message when an error occurred in API
     * 
     * <pre>
     * @param hldRsnCdNm
     * @param cpoOrdNum
     * @param cpoDtlLineNum
     * @param cpoDtlLineSubNum
     * @param shpgPlnNum
     * @param errMsgId
     * @return
     */
    private String createApiErrMsg(String hldRsnCdNm, String cpoOrdNum, String cpoDtlLineNum, String cpoDtlLineSubNum, String shpgPlnNum, String errMsgId) {

        StringBuilder sb = new StringBuilder(225);

        sb.append("API : ").append(hldRsnCdNm);
        sb.append(", Global Company Code : ").append(glblCmpyCd);
        sb.append(", CPO Order Number : ").append(cpoOrdNum);

        if (hasValue(cpoDtlLineNum)) {
            sb.append(", CPO Detail Line Number : ").append(cpoDtlLineNum);
        }

        if (hasValue(cpoDtlLineSubNum)) {
            sb.append(", CPO Detail Line Sub Number : ").append(cpoDtlLineSubNum);
        }

        if (hasValue(shpgPlnNum)) {
            sb.append(", Shipping Plan Number : ").append(shpgPlnNum);
        }

        sb.append(", API Error ID : ").append(errMsgId);

        return sb.toString();
    }

}
