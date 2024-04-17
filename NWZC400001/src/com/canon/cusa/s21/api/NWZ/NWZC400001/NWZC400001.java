/*
 *  <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC400001;

import static com.canon.cusa.s21.api.NWZ.NWZC400001.constant.NWZC400001Constant.CHK_PNT_OV;
import static com.canon.cusa.s21.api.NWZ.NWZC400001.constant.NWZC400001Constant.NWZM0001E;
import static com.canon.cusa.s21.api.NWZ.NWZC400001.constant.NWZC400001Constant.NWZM0002E;
import static com.canon.cusa.s21.api.NWZ.NWZC400001.constant.NWZC400001Constant.NWZM0003E;
import static com.canon.cusa.s21.api.NWZ.NWZC400001.constant.NWZC400001Constant.NWZM0004E;
import static com.canon.cusa.s21.api.NWZ.NWZC400001.constant.NWZC400001Constant.NWZM0073E;
import static com.canon.cusa.s21.api.NWZ.NWZC400001.constant.NWZC400001Constant.NWZM0080E;
import static com.canon.cusa.s21.api.NWZ.NWZC400001.constant.NWZC400001Constant.NWZM0245E;
import static com.canon.cusa.s21.api.NWZ.NWZC400001.constant.NWZC400001Constant.NWZM0445E;
import static com.canon.cusa.s21.api.NWZ.NWZC400001.constant.NWZC400001Constant.REQ_TYPE_ORD_VLD;
import static com.canon.cusa.s21.api.NWZ.NWZC400001.constant.NWZC400001Constant.REQ_TYPE_RECAL;
import static com.canon.cusa.s21.api.NWZ.NWZC400001.constant.NWZC400001Constant.SET_ITEM_PARENT_NUM;
import static com.canon.cusa.s21.api.NWZ.NWZC400001.constant.NWZC400001Constant.SUB_SYS_ID;
import static com.canon.cusa.s21.api.NWZ.NWZC400001.constant.NWZC400001Constant.PROCESS_ID;
import static com.canon.cusa.s21.api.NWZ.NWZC400001.constant.NWZC400001Constant.DOCUMENT_TYPE;
import static com.canon.cusa.s21.api.NWZ.NWZC400001.constant.NWZC400001Constant.EVENT_ID;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import parts.common.EZDMsg;
import parts.common.EZDTMsg;
import parts.common.EZDTMsgArray;
import parts.dbcommon.EZDTBLAccessor;
import business.db.CMPSNTMsg;
import business.db.CPOTMsg;
import business.db.CPO_DTLTMsg;
import business.db.DS_CPO_CONFIGTMsg;
import business.db.DS_CPO_CONFIGTMsgArray;
import business.db.DS_CPO_RTRN_DTLTMsg;
import business.db.DS_CPO_RTRN_DTLTMsgArray;
import business.db.HLDTMsg;
import business.db.HLDTMsgArray;
import business.db.HLD_RSNTMsg;
import business.db.MDSETMsg;
import business.db.S21_ORGTMsg;
import business.parts.NWXC005001PMsg;
import business.parts.NWZC400001PMsg;
import business.parts.NWZC400001_xxOrdInfoListPMsg;

import com.canon.cusa.s21.api.NWZ.NWZC158001.NWZC158001;
import com.canon.cusa.s21.api.NWZ.NWZC159001.NWZC159001;
import com.canon.cusa.s21.common.NWX.NWXC004001.NWXCmpsnTMsgThreadLocalCache;
import com.canon.cusa.s21.common.NWX.NWXC004001.NWXHldRsnTMsgThreadLocalCache;
import com.canon.cusa.s21.common.NWX.NWXC004001.NWXMdseTMsgThreadLocalCache;
import com.canon.cusa.s21.common.NWX.NWXC004001.NWXHldRsnTMsgThreadLocalCache.HldRsnTMsgCacheKey;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.HLD_APPLY_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.HLD_LVL;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.HLD_RSN;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_CATG_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_HDR_DPLY_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTRN_LINE_DPLY_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTRN_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WH_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.collections.S21LRUMap;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmListResultSetHandlerSupport;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmVoidResultSetHandlerSupport;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21BusinessProcessLog;
import com.canon.cusa.s21.framework.log.S21BusinessProcessLogMsg;
import com.canon.cusa.s21.framework.userprofile.S21UserInfo;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * NWZC4000 : Return Validation API
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/12   Fujitsu         S.Yoshida       Create          N/A
 * 2016/02/03   Fujitsu         S.Takami        Update          S21_NA#4055
 * 2016/02/26   Fujitsu         T.Ishii         Update          S21_NA#3960
 * 2016/04/05   Fujitsu         S.Takami        Update          S21_NA#5519-2
 * 2016/08/04   Fujitsu         T.Yoshida       Update          S21_NA#12895
 * 2016/09/20   Fujitsu         Y.Kanefusa      Update          S21_NA#11306
 * 2017/04/12   Fujitsu         S.Takami        Update          S21_NA#Review structure Lv.1
 * 2017/04/28   Fujitsu         S.Takami        Update          S21_NA#Review structure Lv.2
 * 2017/05/10   Fujitsu         S.Takami        Update          S21_NA#Review structure Lv.2 RS#7255(Display Status)
 * 2017/10/06   Fujitsu         A.Kosai         Update          S21_NA#21511
 * 2018/05/08   Fujitsu         K.Ishizuka      Update          S21_NA#22131
 * 2018/11/05   Fujitsu         K.Kato          Update          S21_NA#29015
 * 2019/04/10   Fujitsu         Mz.Takahashi    Update          S21_NA#31053
 * 2019/05/15   Fujitsu         M.Ohno          Update          S21_NA#50132
 * 2019/05/15   Fujitsu         M.Ohno          Update          S21_NA#50352
 *</pre>
 */
public class NWZC400001 extends S21ApiCommonBase {

    /** SSM Client */
    private final S21SsmBatchClient ssmClient;

    /** Message ID Set */
    private Set<String> xxMsgIdSet;

    /** Batch Type */
    private ONBATCH_TYPE onBatchType;

    /** Previous CPO Order Number */
    private String preCpoOrdNum;

    /** Component Composition List */
    private Map<String, Object> compCmpsnList;

    /** Order Validator */
    private OrderValidator orderValidator;

    /** Time Stamp String Pattern */
    private static final String DATE_TIME_PATTERN = "yyyyMMddHHmmssSSS"; // 2016/02/03 S21_NA#4055 Add

    /** cache "Validation APIs" */
    private final Map<Class< ? extends S21ApiCommonBase>, S21ApiCommonBase> apiCache = new HashMap<Class< ? extends S21ApiCommonBase>, S21ApiCommonBase>();


    /**
     * <pre>
     * Constructor
     * </pre>
     */
    public NWZC400001() {
        super();
        ssmClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * Validation Process Manager.
     * @param params List<NWZC400001PMsg>
     * @param pOnBatchType ONBATCH_TYPE
     */
    public void execute(final List<NWZC400001PMsg> params, final ONBATCH_TYPE pOnBatchType) {
        for (NWZC400001PMsg param : params) {
            this.execute(param, pOnBatchType);
        }
    }

    /**
     * Validation Process Manager.
     * @param param NWZC400001PMsg
     * @param pOnBatchType ONBATCH_TYPE
     */
    public void execute(final NWZC400001PMsg param, final ONBATCH_TYPE pOnBatchType) {
        final String methodNm = "execute";
        writePerformanceProfilingLogStart(getClass(), methodNm);

        try {

            /*********************************************************
             * 1. initialize global variables.
             ********************************************************/
            this.onBatchType = pOnBatchType;
            this.xxMsgIdSet = new LinkedHashSet<String>();
            this.preCpoOrdNum = null;
            this.compCmpsnList = null;

            /*********************************************************
             * 2. check API parameter.
             ********************************************************/
            if (!checkReqParams(param)) {
                return;
            }

            /*********************************************************
             * 3. Validation
             ********************************************************/
            final String xxRqstTpCd = param.xxProcMd.getValue();

            if (REQ_TYPE_ORD_VLD.equals(xxRqstTpCd)) {
                execOrderValidation(param);

            } else if (REQ_TYPE_RECAL.equals(xxRqstTpCd)) {
                execRecalculation(param);
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);

        } finally {
            // set param.xxMsgIdList.
            if (hasXxMsgId()) {
                int cnt = 0;
                for (String xxMsgId : xxMsgIdSet) {
                    if (cnt >= param.xxMsgIdList.length()) {
                        break;
                    }
                    ZYPEZDItemValueSetter.setValue(param.xxMsgIdList.no(cnt).xxMsgId, xxMsgId);
                    cnt++;
                }
                param.xxMsgIdList.setValidCount(cnt);
            }

            writePerformanceProfilingLog(getClass(), "#xxHldFlg=[" + param.xxHldFlg.getValue() + "]");
            writePerformanceProfilingLogEnd(getClass(), methodNm);
        }
    }


    /**
     * Order Validation.
     * @param param
     * @throws SQLException
     */
    protected void execOrderValidation(NWZC400001PMsg param) throws SQLException {
        final String methodNm = "execOrderValidation";
        writePerformanceProfilingLogStart(getClass(), methodNm);

        try {

            // --------------------------------------------------
            // get Validation Order List.
            // --------------------------------------------------
            List<Map<String, Object>> orderList = getOrderList(param);
            if (orderList.isEmpty()) {
                addXxMsgId(NWZM0073E);
                return;
            }

            // --------------------------------------------------
            // execute Validation.
            // --------------------------------------------------
            getOrderValidator().exec(param, orderList, CHK_PNT_OV);
            if (hasXxMsgId()) {
                return;
            }

            // Release Book Hold
            releaseBookHold(param);

            // --------------------------------------------------
            // S21ApiTBLAccessor.update Hld Flg.
            // --------------------------------------------------
            String cpoOrdNum = (String) orderList.get(0).get("CPO_ORD_NUM");
            updateHldFlg(param, cpoOrdNum);
            if (hasXxMsgId()) {
                return;
            }

            // Update Order's Book Flag and Date
            updateOrdBook(param);

            // --------------------------------------------------
            // set Hld Flg.
            // --------------------------------------------------
            setHldFlg(param);

        } finally {
            writePerformanceProfilingLogEnd(getClass(), methodNm);
        }
    }

    /**
     * Recalculation.
     * @param param
     * @throws SQLException
     */
    protected void execRecalculation(NWZC400001PMsg param) throws SQLException {
        final String methodNm = "execRecalculation";
        writePerformanceProfilingLogStart(getClass(), methodNm);

        try {

            // --------------------------------------------------
            // get Recalculation Order List.
            // --------------------------------------------------
            final List<Map<String, Object>> orderList = getRecalcOrderList(param);
            if (orderList.isEmpty()) {
                addXxMsgId(NWZM0073E);
                return;
            }

            final String cpoOrdNum = (String) orderList.get(0).get("CPO_ORD_NUM");
            if (preCpoOrdNum == null) {
                preCpoOrdNum = cpoOrdNum;
            } else if (preCpoOrdNum.equals(cpoOrdNum)) {
                return;
            } else {
                preCpoOrdNum = cpoOrdNum;
            }

            // Release Book Hold
            releaseBookHold(param);

            // --------------------------------------------------
            // S21ApiTBLAccessor.update Hld Flg.
            // --------------------------------------------------
            updateHldFlg(param, cpoOrdNum);
            if (hasXxMsgId()) {
                return;
            }

            // Update Order's Book Flag and Date
            updateOrdBook(param);

            // --------------------------------------------------
            // set Hld Flg.
            // --------------------------------------------------
            setHldFlg(param);

        } finally {
            writePerformanceProfilingLogEnd(getClass(), methodNm);
        }
    }


    private boolean checkReqParams(final NWZC400001PMsg param) {

        String glblCmpyCd = param.glblCmpyCd.getValue();
        if (!ZYPCommonFunc.hasValue(glblCmpyCd)) {
            addXxMsgId(NWZM0001E);
            return false;
        }

        String slsDt = param.slsDt.getValue();
        if (!ZYPCommonFunc.hasValue(slsDt)) {
            addXxMsgId(NWZM0445E);
            return false;
        }

        String xxRqstTpCd = param.xxProcMd.getValue();
        String cpoDtlLineNum = param.dsCpoRtrnLineNum_I.getValue();
        String cpoDtlLineSubNum = param.dsCpoRtrnLineSubNum_I.getValue();

        if (!REQ_TYPE_ORD_VLD.equals(xxRqstTpCd)
                && !REQ_TYPE_RECAL.equals(xxRqstTpCd)) {
            addXxMsgId(NWZM0245E);
            return false;
        }

        if (!ZYPCommonFunc.hasValue(param.cpoOrdNum_I)) {
            addXxMsgId(NWZM0002E);
            return false;

        } else if (ZYPCommonFunc.hasValue(cpoDtlLineSubNum)
                && !ZYPCommonFunc.hasValue(cpoDtlLineNum)) {
            addXxMsgId(NWZM0003E);
            return false;

        } else if (ZYPCommonFunc.hasValue(cpoDtlLineNum)
                && !ZYPCommonFunc.hasValue(cpoDtlLineSubNum)) {
            addXxMsgId(NWZM0004E);
            return false;
        }

        return true;
    }

    /** Get Component Qty */
    private BigDecimal getCompQty(String glblCmpyCd, String prntMdseCd, String childmdseCode, String ordTs) {

        CMPSNTMsg cmpsnMsg = NWXCmpsnTMsgThreadLocalCache.getInstance().get(glblCmpyCd, prntMdseCd, childmdseCode, ordTs, ordTs);

        BigDecimal compQty = BigDecimal.ZERO;
        if (cmpsnMsg != null) {
            compQty = cmpsnMsg.childMdseQty.getValue();
        }

        return compQty;
    }

    @SuppressWarnings("unchecked")
    private List<String> getHldOrderList(String glblCmpyCd, String cpoOrdNum) {

        final Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("cpoOrdNum", cpoOrdNum);

        final Map<String, String> hldLvl = new HashMap<String, String>();
        ssmParam.put("hldLvl", hldLvl);
        hldLvl.put("cpo", HLD_LVL.CPO_LEVEL);
        hldLvl.put("cpoDtl", HLD_LVL.CPO_DETAIL_LEVEL);
        ssmParam.put("trxSrcTp", TRX_SRC_TP.WHOLE_SALES_RETURN);  // QC#11306 2016/09/20 Add

        return ssmClient.queryObjectList("getHldOrderList", ssmParam);
    }

    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> getOrderList(NWZC400001PMsg param) {
        final String methodNm = "getOrderList";
        writePerformanceProfilingLogStart(getClass(), methodNm);

        writePerformanceProfilingLog(getClass(), //
                " - CPO_ORD_NUM=[" + param.cpoOrdNum_I.getValue() + "], " //
                        + "DS_CPO_RTRN_LINE_NUM=[" + param.dsCpoRtrnLineNum_I.getValue() + "], " //
                        + "DS_CPO_RTRN_LINE_SUB_NUM=[" + param.dsCpoRtrnLineSubNum_I.getValue() + "], " //
        );

        try {

            final Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
            ssmParam.put("cpoOrdNum_I", param.cpoOrdNum_I.getValue());
            ssmParam.put("dsCpoRtrnLineNum_I", param.dsCpoRtrnLineNum_I.getValue());
            ssmParam.put("dsCpoRtrnLineSubNum_I", param.dsCpoRtrnLineSubNum_I.getValue());

            final Map<String, String> ordLineSts = new HashMap<String, String>();
            ssmParam.put("ordLineSts", ordLineSts);
            ordLineSts.put("cancel", ORD_LINE_STS.CANCELLED);

            final List<Map<String, Object>> orderList = ssmClient.queryObjectList("getOrderList", ssmParam, new S21SsmListResultSetHandlerSupport() {
                @Override
                protected List doProcessQueryResult(ResultSet rs) throws SQLException {
                    return toMapList(rs);
                }
            });

            // WH
            for (Map<String, Object> order : orderList) {
                order.put("whType", getWhType((String) order.get("GLBL_CMPY_CD"), (String) order.get("RTRN_MDSE_CD")));
            }

            executeForUpdate(orderList);

            writePerformanceProfilingLog(getClass(), " - orderList.size=[" + orderList.size() + "]");
            return orderList;

        } finally {
            writePerformanceProfilingLogEnd(getClass(), methodNm);
        }
    }

    private OrderValidator getOrderValidator() {

        if (orderValidator == null) {
            orderValidator = new OrderValidator();
        }
        return orderValidator;
    }

    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> getRecalcOrderList(NWZC400001PMsg param) {
        final String methodNm = "getRecalcOrderList";
        writePerformanceProfilingLogStart(getClass(), methodNm);

        writePerformanceProfilingLog(getClass(), //
                " - CPO_ORD_NUM=[" + param.cpoOrdNum_I.getValue() + "], " //
                + "DS_CPO_RTRN_LINE_NUM=[" + param.dsCpoRtrnLineNum_I.getValue() + "], " //
                + "DS_CPO_RTRN_LINE_SUB_NUM=[" + param.dsCpoRtrnLineSubNum_I.getValue() + "], " //
        );

        try {

            final Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
            ssmParam.put("cpoOrdNum_I", param.cpoOrdNum_I.getValue());

            final Map<String, String> ordLineSts = new HashMap<String, String>();
            ssmParam.put("ordLineSts", ordLineSts);
            ordLineSts.put("cancel", ORD_LINE_STS.CANCELLED);

            // --------------------------------------------------
            // DS_CPO_RTRN_DTL
            // --------------------------------------------------
            if (ZYPCommonFunc.hasValue(param.dsCpoRtrnLineSubNum_I)) {

                DS_CPO_RTRN_DTLTMsg dsCpoRtrnDtlTMsg = new DS_CPO_RTRN_DTLTMsg();
                ZYPEZDItemValueSetter.setValue(dsCpoRtrnDtlTMsg.glblCmpyCd, param.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(dsCpoRtrnDtlTMsg.cpoOrdNum, param.cpoOrdNum_I);
                ZYPEZDItemValueSetter.setValue(dsCpoRtrnDtlTMsg.dsCpoRtrnLineNum, param.dsCpoRtrnLineNum_I);
                ZYPEZDItemValueSetter.setValue(dsCpoRtrnDtlTMsg.dsCpoRtrnLineSubNum, param.dsCpoRtrnLineSubNum_I);

                dsCpoRtrnDtlTMsg = (DS_CPO_RTRN_DTLTMsg) S21FastTBLAccessor.findByKey(dsCpoRtrnDtlTMsg);

                if (ZYPCommonFunc.hasValue(dsCpoRtrnDtlTMsg.setMdseCd)
                        || SET_ITEM_PARENT_NUM.equals(dsCpoRtrnDtlTMsg.dsCpoRtrnLineSubNum.getValue())) {
                    ssmParam.put("dsCpoRtrnLineNum_I", dsCpoRtrnDtlTMsg.dsCpoRtrnLineNum.getValue());
                } else {
                    ssmParam.put("dsCpoRtrnLineNum_I", dsCpoRtrnDtlTMsg.dsCpoRtrnLineNum.getValue());
                    ssmParam.put("dsCpoRtrnLineSubNum_I", dsCpoRtrnDtlTMsg.dsCpoRtrnLineSubNum.getValue());
                }
            }

            final List<Map<String, Object>> orderList = ssmClient.queryObjectList("getOrderList", ssmParam, new S21SsmListResultSetHandlerSupport() {
                @Override
                protected List doProcessQueryResult(ResultSet rs) throws SQLException {
                    return toMapList(rs);
                }
            });

            String dsCpoRtrnLineSubNum = "";
            boolean isCompCmpsnListFlg = false;

            if (this.compCmpsnList == null) {
                this.compCmpsnList = new HashMap<String, Object>();
                isCompCmpsnListFlg = true;
            }

            for (Map<String, Object> order : orderList) {

                // WH
                order.put("whType", getWhType((String) order.get("GLBL_CMPY_CD"), (String) order.get("RTRN_MDSE_CD")));

                if (isCompCmpsnListFlg) {
                    if (!dsCpoRtrnLineSubNum.equals((String) order.get("DS_CPO_RTRN_LINE_SUB_NUM"))) {
                        final String setMdseCd = (String) order.get("SET_MDSE_CD");
                        if (ZYPCommonFunc.hasValue(setMdseCd)) {
                            final BigDecimal compQty = getCompQty(param.glblCmpyCd.getValue(), setMdseCd, (String) order.get("RTRN_MDSE_CD"), (String) order.get("CPO_ORD_TS"));
                            this.compCmpsnList.put((String) order.get("DS_CPO_RTRN_LINE_NUM"), compQty);
                            dsCpoRtrnLineSubNum = (String) order.get("DS_CPO_RTRN_LINE_SUB_NUM");
                        }
                    }
                }
            }

            executeForUpdate(orderList);

            writePerformanceProfilingLog(getClass(), " - orderList.size=[" + orderList.size() + "]");
            return orderList;

        } finally {
            writePerformanceProfilingLogEnd(getClass(), methodNm);
        }
    }

    private void releaseBookHold(NWZC400001PMsg param) {

        String glblCmpyCd = param.glblCmpyCd.getValue();
        String cpoOrdNum = param.cpoOrdNum_I.getValue();
        String slsDt = param.slsDt.getValue();
        String psnCd = getPsnCd();

        final List<BigDecimal> bookHldPkList = (List<BigDecimal>) getBookHldListWOBlockHld(glblCmpyCd, cpoOrdNum, slsDt);
        if (bookHldPkList == null || bookHldPkList.isEmpty()) {
            return;
        }

        List<HLDTMsg> hldTMsgList = new ArrayList<HLDTMsg>();
        for (BigDecimal bookHldPk : bookHldPkList) {
            HLDTMsg hldTMsg = new HLDTMsg();
            hldTMsg.glblCmpyCd.setValue(glblCmpyCd);
            hldTMsg.hldPk.setValue(bookHldPk);
            hldTMsg = (HLDTMsg) S21ApiTBLAccessor.findByKeyForUpdate(hldTMsg);
            if (hldTMsg == null) {
                continue;
            }
            hldTMsg.relPsnCd.setValue(psnCd);
            hldTMsg.relFlg.setValue(ZYPConstant.FLG_ON_Y);
            hldTMsg.relDt.setValue(slsDt);
            hldTMsgList.add(hldTMsg);
        }

        if (!hldTMsgList.isEmpty()) {
            S21FastTBLAccessor.update(hldTMsgList.toArray(new HLDTMsg[0]));
        }
    }

    private void updateOrdBook(NWZC400001PMsg param) {
        // 2017/10/06 S21_NA#21511 Add Start
        if (!S21StringUtil.isEquals(param.xxProcMd.getValue(), REQ_TYPE_ORD_VLD)) {
            return;
        }

        if (!(ZYPCommonFunc.hasValue(param.cpoOrdNum_I)
                && !ZYPCommonFunc.hasValue(param.dsCpoRtrnLineNum_I)
                && !ZYPCommonFunc.hasValue(param.dsCpoRtrnLineSubNum_I))) {
            return;
        }
        // 2017/10/06 S21_NA#21511 Add End
        String glblCmpyCd = param.glblCmpyCd.getValue();
        String cpoOrdNum = param.cpoOrdNum_I.getValue();
        String slsDt = param.slsDt.getValue();

        int hldCnt = getHldCnt(glblCmpyCd, cpoOrdNum, slsDt);
        if (hldCnt > 0) {
            return;
        }

        CPOTMsg cpoTMsg = new CPOTMsg();
        cpoTMsg.glblCmpyCd.setValue(glblCmpyCd);
        cpoTMsg.cpoOrdNum.setValue(cpoOrdNum);
        cpoTMsg = (CPOTMsg) S21ApiTBLAccessor.findByKeyForUpdate(cpoTMsg);
        if (cpoTMsg == null) {
            return;
        }
        // 2017/10/06 S21_NA#21511 Add Start
        if (S21StringUtil.isEquals(cpoTMsg.ordBookFlg.getValue(), ZYPConstant.FLG_ON_Y)) {
            return;
        }
        // 2017/10/06 S21_NA#21511 Add End
        // 2017/10/13 S21_NA#21511 Add Start
        if (!ZYPCommonFunc.hasValue(cpoTMsg.ordBookReqTs) || !ZYPCommonFunc.hasValue(cpoTMsg.ordBookReqUsrId)) {
            return;
        }
        // 2017/10/13 S21_NA#21511 Add End
        cpoTMsg.ordBookFlg.setValue(ZYPConstant.FLG_ON_Y);
        // 2016/02/03 S21_NA#4055 Mod Start
        // dsCpoTMsg.ordBookTs.setValue(slsDt);
        // 2019/05/15 QC#50352 Mod Start
//        String systemTime = ZYPDateUtil.getCurrentSystemTime(DATE_TIME_PATTERN);
//        String ordBookTs = slsDt + systemTime.substring(8);
//        cpoTMsg.ordBookTs.setValue(ordBookTs);
        ZYPEZDItemValueSetter.setValue(cpoTMsg.ordBookTs, ZYPDateUtil.getCurrentSystemTime(DATE_TIME_PATTERN));
         // 2019/05/15 QC#50352 Mod End

        // 2016/02/03 S21_NA#4055 Mod End
        // 2017/05/10 S21_NA#Review structure Lv.2 RS#7255(Display Status) Add Start
        cpoTMsg.ordHdrDplyStsCd.setValue(ORD_HDR_DPLY_STS.BOOKED);
        // 2017/05/10 S21_NA#Review structure Lv.2 RS#7255(Display Status) Add End
        S21ApiTBLAccessor.update(cpoTMsg);

        DS_CPO_RTRN_DTLTMsg dsCpoRtrnDtlTMsg = new DS_CPO_RTRN_DTLTMsg();
        dsCpoRtrnDtlTMsg.setSQLID("001");
        dsCpoRtrnDtlTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        dsCpoRtrnDtlTMsg.setConditionValue("cpoOrdNum01", cpoOrdNum);

        DS_CPO_RTRN_DTLTMsgArray cpoRtrnDtlTMsgAry =
            (DS_CPO_RTRN_DTLTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(dsCpoRtrnDtlTMsg);
        if (cpoRtrnDtlTMsgAry.length() == 0) {
            return;
        }

        List<DS_CPO_RTRN_DTLTMsg> dsCpoRtrnDtlTMsgList = new ArrayList<DS_CPO_RTRN_DTLTMsg>();
        for (int i = 0; i < cpoRtrnDtlTMsgAry.getValidCount(); i++) {
            dsCpoRtrnDtlTMsg = cpoRtrnDtlTMsgAry.no(i);
            if (!isActive(dsCpoRtrnDtlTMsg.rtrnLineStsCd.getValue())) {
                continue;
            }
            dsCpoRtrnDtlTMsg.rtrnLineStsCd.setValue(RTRN_LINE_STS.BOOKED);
            // 2019/04/10 S21_NA#31053 Mod Start
            dsCpoRtrnDtlTMsg.rtrnLineDplyStsCd.setValue(RTRN_LINE_DPLY_STS.PENDING_RETURN);
            //// 2017/04/28 S21_NA#Review structure Lv.2 Start
            //dsCpoRtrnDtlTMsg.rtrnLineDplyStsCd.setValue(RTRN_LINE_DPLY_STS.BOOKED);
            //// 2017/04/28 S21_NA#Review structure Lv.2 Endt
            // 2019/04/10 S21_NA#31053 Mod End

            dsCpoRtrnDtlTMsgList.add(dsCpoRtrnDtlTMsg);
        }
        S21FastTBLAccessor.update(dsCpoRtrnDtlTMsgList.toArray(new DS_CPO_RTRN_DTLTMsg[0]));
        
        // 2018/11/05 S21_NA#29015 Add Start
        insBizProcLogBooked(cpoOrdNum);
        // 2018/11/05 S21_NA#29015 Add End
    }

    @SuppressWarnings("unchecked")
    private List<BigDecimal> getBookHldListWOBlockHld(String glblCmpyCd, String cpoOrdNum, String slsDt) {

        final Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("cpoOrdNum", cpoOrdNum);
        ssmParam.put("slsDt", slsDt);

        final Map<String, String> hldRsn = new HashMap<String, String>();
        ssmParam.put("hldRsn", hldRsn);
        hldRsn.put("book", HLD_RSN.BOOK);

        return ssmClient.queryObjectList("getBookHldListWOBlockHld", ssmParam);
    }

    private int getHldCnt(String glblCmpyCd, String cpoOrdNum, String slsDt) {

        final Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("cpoOrdNum", cpoOrdNum);
        ssmParam.put("slsDt", slsDt);
        ssmParam.put("trxSrcTp", TRX_SRC_TP.WHOLE_SALES_RETURN);  // QC#11306 2016/09/20 Add

        return (Integer) ssmClient.queryObject("getHldCnt", ssmParam);
    }

    private boolean isActive(String rtrnLineStsCd) {
        if (RTRN_LINE_STS.CLOSED.equals(rtrnLineStsCd) //
                || RTRN_LINE_STS.CANCELLED.equals(rtrnLineStsCd)) {
            return false;
        }
        return true;
    }

    private String getPsnCd() {
        S21UserProfileService prof = S21UserProfileServiceFactory.getInstance().getService();
        S21UserInfo userInfo = prof.getContextUserInfo();
        return userInfo.getUserId();
    }

    private void updateHldFlg(NWZC400001PMsg param, String cpoOrdNum) {
        final String methodNm = "updateHldFlg";
        writePerformanceProfilingLogStart(getClass(), methodNm);

        try {

            final String glblCmpyCd = param.glblCmpyCd.getValue();
            final List<String> hldOrdList = getHldOrderList(glblCmpyCd, cpoOrdNum);

            final Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", glblCmpyCd);
            ssmParam.put("cpoOrdNum", cpoOrdNum);

            ssmClient.queryObjectList("getOrderListToUpdateHldFlg", ssmParam, new S21SsmVoidResultSetHandlerSupport() {

                @Override
                protected void doProcessQueryResult(ResultSet rs) throws SQLException {

                    final String[] cpoHldFlgColNm = {"cpoHldFlg" };
                    final String[] cpoDtlHldFlgColNm = {"cpoDtlHldFlg" };

                    while (rs.next()) {

                        final String tblNm = rs.getString("TBL_NM");
                        final String hldFlg = rs.getString("HLD_FLG");
                        final String cpoOrdNum = rs.getString("CPO_ORD_NUM");
                        final String dsCpoRtrnLineNum = rs.getString("DS_CPO_RTRN_LINE_NUM");
                        final String dsCpoRtrnLineSubNum = rs.getString("DS_CPO_RTRN_LINE_SUB_NUM");

                        // ------------------------------
                        // CPO
                        // ------------------------------
                        if ("CPO".equals(tblNm)) {

                            final StringBuilder key = new StringBuilder();
                            key.append(HLD_LVL.CPO_LEVEL);
                            key.append(".").append(cpoOrdNum);

                            // CPO_HLD_FLG
                            final String cpoHldFlg;
                            if (hldOrdList.contains(key.toString())) {
                                cpoHldFlg = ZYPConstant.FLG_ON_Y;
                            } else {
                                cpoHldFlg = ZYPConstant.FLG_OFF_N;
                            }

                            if (!cpoHldFlg.equals(hldFlg)) {
                                writePerformanceProfilingLog(NWZC400001.class, " #S21ApiTBLAccessor.update CPO_HLD_FLG. cpoOrdNum=[" + cpoOrdNum + "], cpoHldFlg=[" + hldFlg + "]->[" + cpoHldFlg + "]");

                                final CPOTMsg cpoTMsg = new CPOTMsg();
                                ZYPEZDItemValueSetter.setValue(cpoTMsg.glblCmpyCd, glblCmpyCd);
                                ZYPEZDItemValueSetter.setValue(cpoTMsg.cpoOrdNum, cpoOrdNum);

                                // ***** S21ApiTBLAccessor.updateSelectionField
                                ZYPEZDItemValueSetter.setValue(cpoTMsg.cpoHldFlg, cpoHldFlg);
                                S21ApiTBLAccessor.updateSelectionField(cpoTMsg, cpoHldFlgColNm);
                            }

                        // ------------------------------
                        // DS_CPO_RTRN_DTL
                        // ------------------------------
                        } else if ("DS_CPO_RTRN_DTL".equals(tblNm)) {

                            final StringBuilder key = new StringBuilder();
                            key.append(HLD_LVL.CPO_DETAIL_LEVEL);
                            key.append(".").append(cpoOrdNum);
                            key.append(".").append(dsCpoRtrnLineNum);
                            key.append(".").append(dsCpoRtrnLineSubNum);

                            // CPO_DTL_HLD_FLG
                            final String cpoDtlHldFlg;
                            if (hldOrdList.contains(key.toString())) {
                                cpoDtlHldFlg = ZYPConstant.FLG_ON_Y;
                            } else {
                                cpoDtlHldFlg = ZYPConstant.FLG_OFF_N;
                            }

                            if (!cpoDtlHldFlg.equals(hldFlg)) {
                                writePerformanceProfilingLog(NWZC400001.class, " #S21ApiTBLAccessor.update CPO_DTL_HLD_FLG. cpoOrdNum=[" + cpoOrdNum + "],"
                                        + "dsCpoRtrnLineNum=[" + dsCpoRtrnLineNum + "], dsCpoRtrnLineSubNum=[" + dsCpoRtrnLineSubNum + "], cpoDtlHldFlg=["
                                        + hldFlg + "]->[" + cpoDtlHldFlg + "]");

                                final DS_CPO_RTRN_DTLTMsg cpoDtlTMsg = new DS_CPO_RTRN_DTLTMsg();
                                ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.glblCmpyCd, glblCmpyCd);
                                ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.cpoOrdNum, cpoOrdNum);
                                ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.dsCpoRtrnLineNum, dsCpoRtrnLineNum);
                                ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.dsCpoRtrnLineSubNum, dsCpoRtrnLineSubNum);

                                // ***** S21ApiTBLAccessor.updateSelectionField
                                ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.cpoDtlHldFlg, cpoDtlHldFlg);
                                S21ApiTBLAccessor.updateSelectionField(cpoDtlTMsg, cpoDtlHldFlgColNm);
                            }

                        }
                    }
                }
            });

        } finally {
            writePerformanceProfilingLogEnd(getClass(), methodNm);
        }
    }

    private static EZDTMsg findByKeyWithCache(EZDTMsg reqTMsg) {

        return S21CacheTBLAccessor.findByKey(reqTMsg);
    }

    private static String getWhType(String glblCmpyCd, String mdseCd) {

        String whType = null;

        final MDSETMsg mdseTMsg = NWXMdseTMsgThreadLocalCache.getInstance().get(glblCmpyCd, mdseCd);
        if (mdseTMsg != null) {
            final String invtyCtrlFlg = mdseTMsg.invtyCtrlFlg.getValue();
            if (ZYPConstant.FLG_ON_Y.equals(invtyCtrlFlg)) {
                whType = WH_TP.COMMON;
            } else {
                whType = WH_TP.NON_W_OR_H;
            }
        }

        return whType;
    }

    @SuppressWarnings("unchecked")
    private static boolean isEmpty(Collection col) {
        return col == null || col.isEmpty();
    }

    @SuppressWarnings("unchecked")
    private static void writePerformanceProfilingLog(Class clazz, String str) {
        // S21_NA#12895 Del Start
        // final StringBuilder sb = new StringBuilder();
        // sb.append("#Performance Profiling# [").append(clazz.getSimpleName()).append("] : ").append(str);
        // System.out.println(sb.toString());
        // S21_NA#12895 Del End
    }

    @SuppressWarnings("unchecked")
    private static void writePerformanceProfilingLogEnd(Class clazz, String methodNm) {
        // S21_NA#12895 Del Start
        // final StringBuilder sb = new StringBuilder();
        // sb.append("#Performance Profiling# [").append(clazz.getSimpleName()).append("] : ").append(methodNm).append(" - [E N D] ").append(System.currentTimeMillis());
        // System.out.println(sb.toString());
        // S21_NA#12895 Del End    
    }

    @SuppressWarnings("unchecked")
    private static void writePerformanceProfilingLogStart(Class clazz, String methodNm) {
        // S21_NA#12895 Del Start
        // final StringBuilder sb = new StringBuilder();
        // sb.append("#Performance Profiling# [").append(clazz.getSimpleName()).append("] : ").append(methodNm).append(" - [START] ").append(System.currentTimeMillis());
        // System.out.println(sb.toString());
        // S21_NA#12895 Del End
    }

    /**
     * <pre>
     * Hold process definition
     * </pre>
     */
    private static final class HldProcDfn {

        /** Hold Reason Code */
        final String hldRsnCd;

        /** Hold Reason Name */
        final String hldRsnNm;

        /** Hold Level Code */
        final String hldLvlCd;

        /** Hold Request Flag */
        final String exReqFlg;

        private HldProcDfn(ResultSet rs) throws SQLException {
            this.hldRsnCd = rs.getString("HLD_RSN_CD");
            this.hldRsnNm = rs.getString("HLD_RSN_NM");
            this.hldLvlCd = rs.getString("HLD_LVL_CD");
            this.exReqFlg = rs.getString("EX_REQ_FLG");
        }
    }

    /**
     * Order Validator
     * @author K.Tajima
     */
    private final class OrderValidator extends S21SsmVoidResultSetHandlerSupport {

        /** Parameter Message */
        private NWZC400001PMsg param;

        /** Global Company Code */
        private String glblCmpyCd;

        /** Hold Definition Check Point */
        private String hldDfnChkPnt;

        /** Apply Order Type Code */
        private String applyDsOrdTpCd;

        /** Order List */
        private List<Map<String, Object>> orderList;

        /** CPO TMsg */
        private CPOTMsg cpoTMsg;

        /** DS_CPO Return Detail TMsg */
        private DS_CPO_RTRN_DTLTMsgArray dcpoRtrnDtlTMsgArray;

        /** DS CPO Config TMsgArray */
        private DS_CPO_CONFIGTMsgArray dcpoConfigTMsgArray;

        /** Hold Process Definition List for cache */
        private final S21LRUMap<String, List<HldProcDfn>> hldProcDfnListCache = new S21LRUMap<String, List<HldProcDfn>>();

        /** Hold Control Organization List for cache */
        private final S21LRUMap<String, List<Map<String, String>>> hldCtrlOrgListCache = new S21LRUMap<String, List<Map<String, String>>>();

        /** Apply Order Type List for cache */
        private final S21LRUMap<String, List<String>> applyOrdTpListCache = new S21LRUMap<String, List<String>>();

        /** Hold Control Organization List */
        private List<Map<String, String>> hldCtrlOrgList;

        /** Apply Order Type List */
        private List<String> applyOrdTpList;

        /** cache "BILL_TO_CUST.FL_PLN_CMPY_FLG" */
        final S21LRUMap<String, String> flPlnCmpyFlgCache = new S21LRUMap<String, String>();

        private OrderValidator() {
        }

        /**
         * <pre>
         * execute Validation. (Order, Shipping, Credit or Soft Allocation Validation)
         * 
         * @param inParam NWZC400001PMsg
         * @param pOrderList SHPG_PLN list
         * @param pHldDfnChkPnt HLD_DFN_CHK_PNT
         * @return true/has holds, false/nothing holds
         * </pre>
         */
        public boolean exec(NWZC400001PMsg inParam, List<Map<String, Object>> pOrderList, String pHldDfnChkPnt) {
            final String methodNm = "exec";
            writePerformanceProfilingLogStart(getClass(), methodNm);

            boolean hasHlds = false;

            try {

                if (isEmpty(pOrderList)) {
                    return hasHlds;
                }

                this.param = inParam;
                this.glblCmpyCd = inParam.glblCmpyCd.getValue();
                this.hldDfnChkPnt = pHldDfnChkPnt;
                this.orderList = pOrderList;
                this.applyDsOrdTpCd = (String) pOrderList.get(0).get("DS_ORD_TP_CD");

                /**************************************************************
                 * get 'HldCtrlOrgList' and 'ApplyOrdTpList'
                 *************************************************************/
                this.hldCtrlOrgList = getHldCtrlOrgList();
                this.applyOrdTpList = getApplyOrdTpList();

                /**************************************************************
                 * get [CPO], [DS_CPO], [DS_CPO_RTRN_DTL], [DS_CPO_CONFIG].
                 *************************************************************/
                final String cpoOrdNum;

                cpoOrdNum = inParam.cpoOrdNum_I.getValue();

                // [CPO]
                final CPOTMsg reqCpoTMsg = new CPOTMsg();
                ZYPEZDItemValueSetter.setValue(reqCpoTMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(reqCpoTMsg.cpoOrdNum, cpoOrdNum);
                this.cpoTMsg = (CPOTMsg) S21FastTBLAccessor.findByKeyForUpdateAPI(reqCpoTMsg);

                // [DS_CPO_RTRN_DTL]
                final DS_CPO_RTRN_DTLTMsg reqCpoRtrnDtlTMsg = new DS_CPO_RTRN_DTLTMsg();
                reqCpoRtrnDtlTMsg.setSQLID("001");
                reqCpoRtrnDtlTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
                reqCpoRtrnDtlTMsg.setConditionValue("cpoOrdNum01", cpoOrdNum);
                this.dcpoRtrnDtlTMsgArray = (DS_CPO_RTRN_DTLTMsgArray) S21ApiTBLAccessor.findByCondition(reqCpoRtrnDtlTMsg);

                // [DS_CPO_CONFIG]
                final DS_CPO_CONFIGTMsg reqDcpoConfigTMsg = new DS_CPO_CONFIGTMsg();
                reqDcpoConfigTMsg.setSQLID("001");
                reqDcpoConfigTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
                reqDcpoConfigTMsg.setConditionValue("cpoOrdNum01", cpoOrdNum);
                this.dcpoConfigTMsgArray = (DS_CPO_CONFIGTMsgArray) S21ApiTBLAccessor.findByCondition(reqDcpoConfigTMsg);

                /**************************************************************
                 * get 'HLD_PROC_DFN' and exec Validation.
                 *************************************************************/
                final String hldProcDfnCacheKey = new StringBuilder().append(glblCmpyCd).append(",").append(pHldDfnChkPnt).toString();

                if (hldProcDfnListCache.get(hldProcDfnCacheKey) != null) {
                    validation(hldProcDfnListCache.get(hldProcDfnCacheKey));
                } else {
                    final Map<String, String> ssmParam = new HashMap<String, String>();
                    ssmParam.put("glblCmpyCd", glblCmpyCd);
                    ssmParam.put("hldDfnChkPnt", pHldDfnChkPnt);
                    ssmClient.queryObjectList("getHldProcDfnList", ssmParam, this);
                }
                
                addHldByValSetTgt(inParam, pOrderList); // 2018/05/08 S21_NA#22131 Add

                // has Hlds?
                hasHlds = inParam.xxOrdInfoList.getValidCount() > 0;
                return hasHlds;

            } finally {
                writePerformanceProfilingLog(getClass(), "#hasHlds?=[" + hasHlds + "]");
                writePerformanceProfilingLogEnd(getClass(), methodNm);
            }
        }

        @Override
        protected void doProcessQueryResult(ResultSet rs) throws SQLException {
            final String methodNm = "doProcessQueryResult";
            writePerformanceProfilingLogStart(getClass(), methodNm);

            try {

                if (!rs.next()) {
                    return;
                }

                // cache.
                final String hldProcDfnCacheKey = new StringBuilder().append(glblCmpyCd).append(",").append(hldDfnChkPnt).toString();
                hldProcDfnListCache.put(hldProcDfnCacheKey, new ArrayList<HldProcDfn>());

                writePerformanceProfilingLogStart(getClass(), "validation");
                try {
                    do {
                        final HldProcDfn hldProcDfn = new HldProcDfn(rs);
                        hldProcDfnListCache.get(hldProcDfnCacheKey).add(hldProcDfn);
                        // ***** validation
                        validation(hldProcDfn);
                    } while (rs.next());
                } finally {
                    writePerformanceProfilingLogEnd(getClass(), "validation");
                }

            } finally {
                writePerformanceProfilingLogEnd(getClass(), methodNm);
            }
        }

        protected void validation(List<HldProcDfn> hldProcDfnList) {
            final String methodNm = "validation";
            writePerformanceProfilingLogStart(getClass(), methodNm);

            try {

                for (HldProcDfn hldProcDfn : hldProcDfnList) {
                    validation(hldProcDfn);
                }

            } finally {
                writePerformanceProfilingLogEnd(getClass(), methodNm);
            }
        }

        void validation(HldProcDfn hldProcDfn) {

            // Hold Process Definition Information
            final String hldRsnCd = hldProcDfn.hldRsnCd;
            final String hldRsnNm = hldProcDfn.hldRsnNm;
            final String hldLvlCd = hldProcDfn.hldLvlCd;
            final String exReqFlg = hldProcDfn.exReqFlg;

            // cache 'HLD_RSN.EX_REQ_FLG'.
            cacheExReqFlg(glblCmpyCd, hldRsnCd, exReqFlg);

            /*****************************************************
             * HLD_LVL : CPO
             ****************************************************/
            if (HLD_LVL.CPO_LEVEL.equals(hldLvlCd)) {

                String preCpoOrdNum1 = "";

                for (Map<String, Object> order : orderList) {

                    final String cpoSrcTpCd = (String) order.get("CPO_SRC_TP_CD");
                    final String cpoOrdTpCd = (String) order.get("C_CPO_ORD_TP_CD");
                    final String whTpCd = WH_TP.COMMON;

                    final String cpoOrdNum = (String) order.get("CPO_ORD_NUM");

                    if (!preCpoOrdNum1.equals(cpoOrdNum)) {

                        preCpoOrdNum1 = cpoOrdNum;

                        if (!isValidOrdTp(applyOrdTpList, hldRsnCd, cpoSrcTpCd, cpoOrdTpCd, whTpCd)) {
                            continue;
                        }

                        final DS_CPO_RTRN_DTLTMsg dcpoRtrnDtlTMsg = dcpoRtrnDtlTMsgArray.no(0);
                        final DS_CPO_CONFIGTMsg dcpoConfigTMsg = dcpoConfigTMsgArray.no(0);

                        boolean isHldCtrlOrg = false;
                        for (int i = 0; i < dcpoRtrnDtlTMsgArray.getValidCount(); i++) {
                            isHldCtrlOrg = isHldCtrlOrg(dcpoRtrnDtlTMsgArray.no(i), hldCtrlOrgList, hldRsnCd);
                            if (isHldCtrlOrg) {
                                break;
                            }
                        }

                        if (!isHldCtrlOrg) {
                            continue;
                        }

                        // --------------------------------------------------
                        // call Validation API.
                        // --------------------------------------------------
                        final NWXC005001PMsg validPMsg = new NWXC005001PMsg();
                        ZYPEZDItemValueSetter.setValue(validPMsg.glblCmpyCd, glblCmpyCd);
                        ZYPEZDItemValueSetter.setValue(validPMsg.slsDt, param.slsDt);
                        ZYPEZDItemValueSetter.setValue(validPMsg.cpoOrdNum_I, cpoOrdNum);

                        writePerformanceProfilingLog(getClass(), " - HLD_LVL=[CPO], HLD_RSN_CD=[" + hldRsnCd + "], HLD_RSN_NM=[" + hldRsnNm + "] (" + cpoOrdNum + ")");
                        callValidationAPI(hldRsnCd, order, validPMsg, cpoTMsg, hldLvlCd, dcpoRtrnDtlTMsg, dcpoConfigTMsg);

                        if (HLD_RSN.SHIP_COMPLETE_HOLD.equals(hldRsnCd)) {
                            break;
                        }
                    }
                }

            /*************************************************
             * CPO_DETAIL
             ************************************************/
            } else if (HLD_LVL.CPO_DETAIL_LEVEL.equals(hldLvlCd)) {

                String preCpoOrdNum1 = "";
                String preCpoRtrnLineNum1 = "";
                String preCpoRtrnLineSubNum1 = "";

                for (Map<String, Object> order : orderList) {

                    final String cpoSrcTpCd = (String) order.get("CPO_SRC_TP_CD");
                    final String cpoOrdTpCd = (String) order.get("RTRN_CPO_ORD_TP_CD");
                    final String whTpCd = (String) order.get("whType");

                    final String cpoOrdNum = (String) order.get("CPO_ORD_NUM");
                    final String dsCpoRtrnLineNum = (String) order.get("DS_CPO_RTRN_LINE_NUM");
                    final String dsCpoRtrnLineSubNum = (String) order.get("DS_CPO_RTRN_LINE_SUB_NUM");
                    final BigDecimal svcConfigMstrPk = (BigDecimal) order.get("SVC_CONFIG_MSTR_PK");

                    if (!preCpoOrdNum1.equals(cpoOrdNum) || !preCpoRtrnLineNum1.equals(dsCpoRtrnLineNum) || !preCpoRtrnLineSubNum1.equals(dsCpoRtrnLineSubNum)) {

                        preCpoOrdNum1 = cpoOrdNum;
                        preCpoRtrnLineNum1 = dsCpoRtrnLineNum;
                        preCpoRtrnLineSubNum1 = dsCpoRtrnLineSubNum;

                        if (!isValidOrdTp(applyOrdTpList, hldRsnCd, cpoSrcTpCd, cpoOrdTpCd, whTpCd)) {
                            continue;
                        }

                        final DS_CPO_RTRN_DTLTMsg dsCpoRtrnDtlTMsg = getDsCpoRtrnDtlTMsg(dcpoRtrnDtlTMsgArray, dsCpoRtrnLineNum, dsCpoRtrnLineSubNum);
                        final DS_CPO_CONFIGTMsg dcpoConfigTMsg = getDcpoConfigTMsg(dcpoConfigTMsgArray, svcConfigMstrPk); // 20150924 add

                        if (!isHldCtrlOrg(dsCpoRtrnDtlTMsg, hldCtrlOrgList, hldRsnCd)) {
                            continue;
                        }

                        // --------------------------------------------------
                        // call Validation API.
                        // --------------------------------------------------
                        final NWXC005001PMsg validPMsg = new NWXC005001PMsg();
                        ZYPEZDItemValueSetter.setValue(validPMsg.glblCmpyCd, glblCmpyCd);
                        ZYPEZDItemValueSetter.setValue(validPMsg.slsDt, param.slsDt);
                        ZYPEZDItemValueSetter.setValue(validPMsg.cpoOrdNum_I, cpoOrdNum);
                        ZYPEZDItemValueSetter.setValue(validPMsg.cpoDtlLineNum_I, dsCpoRtrnLineNum);
                        ZYPEZDItemValueSetter.setValue(validPMsg.cpoDtlLineSubNum_I, dsCpoRtrnLineSubNum);

                        writePerformanceProfilingLog(getClass(), " - HLD_LVL=[CPO_DTL], HLD_RSN_CD=[" + hldRsnCd + "], HLD_RSN_NM=[" + hldRsnNm + "] (" + cpoOrdNum + "." + dsCpoRtrnLineNum + "." + dsCpoRtrnLineSubNum + ")");

                        callValidationAPI(hldRsnCd, order, validPMsg, cpoTMsg, hldLvlCd, dsCpoRtrnDtlTMsg, dcpoConfigTMsg);

                        if (HLD_RSN.SHIP_COMPLETE_HOLD.equals(hldRsnCd)) {
                            break;
                        }
                    }
                }
            }
        }

        private DS_CPO_CONFIGTMsg getDcpoConfigTMsg(DS_CPO_CONFIGTMsgArray pDcpoConfigTMsgArray, BigDecimal pSvcConfigMstrPk) {
            for (int i = 0; i < pDcpoConfigTMsgArray.getValidCount(); i++) {
                final DS_CPO_CONFIGTMsg dcpoConfigTMsg = pDcpoConfigTMsgArray.no(i);
                if (!ZYPCommonFunc.hasValue(dcpoConfigTMsg.svcConfigMstrPk)) {
                    continue;
                }
                // 2016/04/05 S21_NA#5519-2 Add Start
                if (!ZYPCommonFunc.hasValue(pSvcConfigMstrPk)) {
                    continue;
                }
                // 2016/04/05 S21_NA#5519-2 Add End
                if (dcpoConfigTMsg.svcConfigMstrPk.getValue().compareTo(pSvcConfigMstrPk) == 0) {
                    return dcpoConfigTMsg;
                }
            }
            return null;
        }

        private void addHld(Map<String, Object> hldOrd, BigDecimal hldQty) {

            String hldRsnCd = (String) hldOrd.get("HLD_RSN_CD");
            String cpoOrdNum = (String) hldOrd.get("CPO_ORD_NUM");
            String cpoDtlLineNum = (String) hldOrd.get("CPO_DTL_LINE_NUM");
            String cpoDtlLineSubNum = (String) hldOrd.get("CPO_DTL_LINE_SUB_NUM");

            int level = 0;
            if (ZYPCommonFunc.hasValue(cpoDtlLineNum) && ZYPCommonFunc.hasValue(cpoDtlLineSubNum)) {
                level = 2;
            } else {
                level = 1;
            }

            HLDTMsg hldTMsg = new HLDTMsg();
            hldTMsg.setSQLID("015");
            hldTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
            hldTMsg.setConditionValue("cpoOrdNum01", cpoOrdNum);
            hldTMsg.setConditionValue("cpoDtlLineNum01", cpoDtlLineNum);
            hldTMsg.setConditionValue("cpoDtlLineSubNum01", cpoDtlLineSubNum);
            hldTMsg.setConditionValue("hldRsnCd01", hldRsnCd);
            hldTMsg.setConditionValue("relFlg01", ZYPConstant.FLG_OFF_N);

            HLDTMsgArray hldTMsgArray = (HLDTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(hldTMsg);

            // --------------------------------------------------
            // S21ApiTBLAccessor.insert
            // --------------------------------------------------
            if (hldTMsgArray.getValidCount() == 0) {

                ZYPEZDItemValueSetter.setValue(hldTMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(hldTMsg.hldPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.HLD_SQ));
                ZYPEZDItemValueSetter.setValue(hldTMsg.hldRsnCd, hldRsnCd);
                ZYPEZDItemValueSetter.setValue(hldTMsg.hldDt, param.slsDt);
                ZYPEZDItemValueSetter.setValue(hldTMsg.relFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(hldTMsg.trxSrcTpCd, TRX_SRC_TP.WHOLE_SALES_RETURN); // S21_NA#3960
                ZYPEZDItemValueSetter.setValue(hldTMsg.rvwFlg, ZYPConstant.FLG_OFF_N);

                if (level == 1) {
                    ZYPEZDItemValueSetter.setValue(hldTMsg.cpoOrdNum, cpoOrdNum);

                } else if (level == 2) {
                    ZYPEZDItemValueSetter.setValue(hldTMsg.cpoOrdNum, cpoOrdNum);
                    ZYPEZDItemValueSetter.setValue(hldTMsg.cpoDtlLineNum, cpoDtlLineNum);
                    ZYPEZDItemValueSetter.setValue(hldTMsg.cpoDtlLineSubNum, cpoDtlLineSubNum);
                    ZYPEZDItemValueSetter.setValue(hldTMsg.hldQty, hldQty);
                }

                // ***** S21ApiTBLAccessor.insert
                S21ApiTBLAccessor.insert(hldTMsg);

                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(hldTMsg.getReturnCode())) {
                    addXxMsgId(NWZM0080E);
                }
            }
        }


        private void cacheExReqFlg(String pGlblCmpyCd, String pHldRsnCd, String pExReqFlg) {

            final HldRsnTMsgCacheKey cacheKey = NWXHldRsnTMsgThreadLocalCache.getInstance().createCacheKey(pGlblCmpyCd, pHldRsnCd);

            final HLD_RSNTMsg hldRsnTMsg = new HLD_RSNTMsg();
            hldRsnTMsg.exReqFlg.setValue(pExReqFlg);

            NWXHldRsnTMsgThreadLocalCache.getInstance().cache(cacheKey, hldRsnTMsg);
        }


        private void callValidationAPI(//
                String hldRsnCd //
                , Map<String, Object> order //
                , NWXC005001PMsg valParam //
                , CPOTMsg pCpoTMsg //
                , String hldLvlCd //
                , DS_CPO_RTRN_DTLTMsg pDcpoRtrnDtlTMsg //
                , DS_CPO_CONFIGTMsg pDcpoConfigTMsg) {


            // --------------------------------------------------
            // ValidationManagerBean
            // --------------------------------------------------
            String dsCpoRtrnLineNum = pDcpoRtrnDtlTMsg.dsCpoRtrnLineNum.getValue();
            String dsCpoRtrnLineSubNum = pDcpoRtrnDtlTMsg.dsCpoRtrnLineSubNum.getValue();

            CPO_DTLTMsg cpoDtlTMsg = new CPO_DTLTMsg();
            EZDMsg.copy(pDcpoRtrnDtlTMsg, null, cpoDtlTMsg, null);
            cpoDtlTMsg.cpoDtlLineNum.setValue(dsCpoRtrnLineNum);
            cpoDtlTMsg.cpoDtlLineSubNum.setValue(dsCpoRtrnLineSubNum);

            final ValidationManagerBean validBean = new ValidationManagerBean(
                    valParam, pCpoTMsg, cpoDtlTMsg, null, pDcpoConfigTMsg);

            // ORD_QTY
            final BigDecimal ordQty;
            if (HLD_LVL.CPO_LEVEL.equals(hldLvlCd)) {
                ordQty = null;
            } else {
                ordQty = (BigDecimal) order.get("RTRN_ORD_QTY");
            }
            validBean.setOrdQty(ordQty);

            /*********************************************
             * VALIDATION_HOLD
             ********************************************/
            if (HLD_RSN.VALIDATION_HOLD.equals(hldRsnCd)) {
                ((NWZC158001) getAPI(NWZC158001.class)).execute(validBean, onBatchType);

            /*********************************************
             * PROFITABILITY_HOLD
             ********************************************/
            } else if (HLD_RSN.PROFITABILITY_HOLD.equals(hldRsnCd)) {
                ((NWZC159001) getAPI(NWZC159001.class)).execute(validBean, onBatchType);

            } else {
                return;
            }

            final NWXC005001PMsg apiPMsg = validBean.getInputPMsg();

            // has API errors?
            final int apiErrMsgIdList = apiPMsg.xxMsgIdList.getValidCount();
            if (apiErrMsgIdList > 0) {
                for (int i = 0; i < apiErrMsgIdList; i++) {
                    addXxMsgId(apiPMsg.xxMsgIdList.no(i).xxMsgId.getValue());
                }
                return;
            }

            // ----- HLD
            if (ZYPCommonFunc.hasValue(apiPMsg.hldRsnCd)) {

                final Map<String, Object> hldOrd = new HashMap<String, Object>();
                hldOrd.put("HLD_RSN_CD", apiPMsg.hldRsnCd.getValue());
                hldOrd.put("CPO_ORD_NUM", apiPMsg.cpoOrdNum_O.getValue());
                hldOrd.put("CPO_DTL_LINE_NUM", apiPMsg.cpoDtlLineNum_O.getValue());
                hldOrd.put("CPO_DTL_LINE_SUB_NUM", apiPMsg.cpoDtlLineSubNum_O.getValue());
                hldOrd.put("SHPG_PLN_NUM", apiPMsg.shpgPlnNum_O.getValue());
                hldOrd.put("ORD_QTY", ((ValidationManagerBean) validBean).getOrdQty());

                execHld(hldOrd);
            }
        }


        private void execHld(Map<String, Object> hldOrd) {
            writePerformanceProfilingLog(getClass(), "  execHld = " + hldOrd);

            String hldRsnCd = (String) hldOrd.get("HLD_RSN_CD");
            String cpoOrdNum = (String) hldOrd.get("CPO_ORD_NUM");
            String cpoDtlLineNum = (String) hldOrd.get("CPO_DTL_LINE_NUM");
            String cpoDtlLineSubNum = (String) hldOrd.get("CPO_DTL_LINE_SUB_NUM");
            BigDecimal ordQty = (BigDecimal) hldOrd.get("ORD_QTY");

            // add HLD.
            this.addHld(hldOrd, ordQty);

            // S21ApiTBLAccessor.update HLD_FLG
            this.updateHldFlg(hldOrd);

            // write Biz Process Log.
            this.writeBizProcLog(cpoOrdNum, cpoDtlLineNum, cpoDtlLineSubNum, hldRsnCd);

            if (param.xxOrdInfoList.getValidCount() >= param.xxOrdInfoList.length()) {
                return;
            }
            param.xxOrdInfoList.setValidCount(param.xxOrdInfoList.getValidCount() + 1);
            final NWZC400001_xxOrdInfoListPMsg lastLine = (NWZC400001_xxOrdInfoListPMsg) param.xxOrdInfoList.get(param.xxOrdInfoList.getValidCount() - 1);
            ZYPEZDItemValueSetter.setValue(lastLine.cpoOrdNum_O, cpoOrdNum);
            ZYPEZDItemValueSetter.setValue(lastLine.dsCpoRtrnLineNum_O, cpoDtlLineNum);
            ZYPEZDItemValueSetter.setValue(lastLine.dsCpoRtrnLineSubNum_O, cpoDtlLineSubNum);
        }

        @SuppressWarnings("unchecked")
        private List<String> getApplyOrdTpList() {

            // cache.
            final String cacheKey = new StringBuilder().append(glblCmpyCd).append(",").append(hldDfnChkPnt).append(",").append(applyDsOrdTpCd).toString();

            List<String> applyOrdTpList1 = this.applyOrdTpListCache.get(cacheKey);

            // query & cache.
            if (applyOrdTpList1 == null) {
                final Map<String, String> ssmParam = new HashMap<String, String>();
                ssmParam.put("glblCmpyCd", glblCmpyCd);
                ssmParam.put("hldDfnChkPnt", hldDfnChkPnt);
                ssmParam.put("applyDsOrdTpCd", applyDsOrdTpCd);
                applyOrdTpList1 = ssmClient.queryObjectList("getApplyOrdTpList", ssmParam);
                this.applyOrdTpListCache.put(cacheKey, applyOrdTpList1);
            }

            if (applyOrdTpList1 != null) {
                return applyOrdTpList1;
            } else {
                return Collections.emptyList();
            }
        }

        private DS_CPO_RTRN_DTLTMsg getDsCpoRtrnDtlTMsg(DS_CPO_RTRN_DTLTMsgArray pDcpoRtrnDtlTMsgArray, String pDsCpoRtrnLineNum, String pDsCpoRtrnLineSubNum) {

            for (int i = 0; i < pDcpoRtrnDtlTMsgArray.getValidCount(); i++) {
                final DS_CPO_RTRN_DTLTMsg cpoRtrnDtlTMsg = pDcpoRtrnDtlTMsgArray.no(i);
                if (cpoRtrnDtlTMsg.dsCpoRtrnLineNum.getValue().equals(pDsCpoRtrnLineNum)) {
                    if (cpoRtrnDtlTMsg.dsCpoRtrnLineSubNum.getValue().equals(pDsCpoRtrnLineSubNum)) {
                        return cpoRtrnDtlTMsg;
                    }
                }
            }
            return null;
        }

        @SuppressWarnings("unchecked")
        private List<Map<String, String>> getHldCtrlOrgList() {

            // cache.
            final String cacheKey = new StringBuilder().append(glblCmpyCd).toString();

            List<Map<String, String>> hldCtrlOrgList1 = this.hldCtrlOrgListCache.get(cacheKey);

            // query & cache.
            if (hldCtrlOrgList1 == null) {
                final Map<String, String> ssmParam = new HashMap<String, String>();
                ssmParam.put("glblCmpyCd", glblCmpyCd);
                hldCtrlOrgList1 = ssmClient.queryObjectList("getHldCtrlOrgList", ssmParam);
                this.hldCtrlOrgListCache.put(cacheKey, hldCtrlOrgList1);
            }

            if (hldCtrlOrgList1 != null) {
                return hldCtrlOrgList1;
            } else {
                return Collections.emptyList();
            }
        }


        private void updateHldFlg(Map<String, Object> hldOrd) {

            String cpoOrdNum = (String) hldOrd.get("CPO_ORD_NUM");
            String cpoDtlLineNUm = (String) hldOrd.get("CPO_DTL_LINE_NUM");
            String cpoDtlLineSubNum = (String) hldOrd.get("CPO_DTL_LINE_SUB_NUM");

            // CPO_DTL
            if (ZYPCommonFunc.hasValue(cpoDtlLineNUm) && ZYPCommonFunc.hasValue(cpoDtlLineSubNum)) {

                final DS_CPO_RTRN_DTLTMsg cpoDtlTMsg = new DS_CPO_RTRN_DTLTMsg();
                ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.cpoOrdNum, cpoOrdNum);
                ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.dsCpoRtrnLineNum, cpoDtlLineNUm);
                ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.dsCpoRtrnLineSubNum, cpoDtlLineSubNum);
                ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.cpoDtlHldFlg, ZYPConstant.FLG_ON_Y);

                // ***** S21ApiTBLAccessor.updateSelectionField
                S21ApiTBLAccessor.updateSelectionField(cpoDtlTMsg, new String[] {"cpoDtlHldFlg"});

            // CPO
            } else if (ZYPCommonFunc.hasValue(cpoOrdNum)) {

                final CPOTMsg cpoTMsg1 = new CPOTMsg();
                ZYPEZDItemValueSetter.setValue(cpoTMsg1.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(cpoTMsg1.cpoOrdNum, cpoOrdNum);
                ZYPEZDItemValueSetter.setValue(cpoTMsg1.cpoHldFlg, ZYPConstant.FLG_ON_Y);

                // ***** S21ApiTBLAccessor.updateSelectionField
                S21ApiTBLAccessor.updateSelectionField(cpoTMsg1, new String[] {"cpoHldFlg"});
            }
        }

        private void writeBizProcLog(String cpoOrdNum, String cpoDtlLineNum, String cpoDtlLineSubNum, String hldRsnCd) {

            final S21BusinessProcessLogMsg bizLogMsg = new S21BusinessProcessLogMsg();
            bizLogMsg.setSubSysId("NWZ");
            bizLogMsg.setProcId("OM");

            // 2019/05/15 S21_NA#50132 Add Start
            HLD_RSNTMsg hldRsnTMsg = new HLD_RSNTMsg();
            ZYPEZDItemValueSetter.setValue(hldRsnTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(hldRsnTMsg.hldRsnCd, hldRsnCd);
            hldRsnTMsg = (HLD_RSNTMsg) S21ApiTBLAccessor.findByKey(hldRsnTMsg);
            if (hldRsnTMsg != null && HLD_APPLY_TP.MANUAL.equals(hldRsnTMsg.hldApplyTpCd.getValue())) {
                bizLogMsg.setEventId("Manual Hold");
                bizLogMsg.setBizProcCmntTxt_02(S21StringUtil.concatStrings(hldRsnTMsg.hldRsnCd.getValue(), ":", hldRsnTMsg.hldRsnDescTxt.getValue()));
            // 2019/05/15 S21_NA#50132 Add End
            } else {
                bizLogMsg.setEventId("Hold");
                bizLogMsg.setBizProcCmntTxt_02(hldRsnCd);
            }

            // bizLogMsg.setDocTpCd("OM"); S21_NA#3960
            bizLogMsg.setDocTpCd("RT"); // S21_NA#3960
            bizLogMsg.setPrntDocId(cpoOrdNum);
            bizLogMsg.setBizProcCmntTxt_01(null);
            bizLogMsg.setBizProcCmntTxt_03(null);

            if (!ZYPCommonFunc.hasValue(cpoDtlLineNum)) {
                bizLogMsg.setDocId("");
            } else {
                bizLogMsg.setDocId(cpoDtlLineNum + "." + cpoDtlLineSubNum);
            }

            S21BusinessProcessLog.print(bizLogMsg);
        }
        
        // 2018/05/08 S21_NA#22131 Add Start
        private void addHldByValSetTgt(NWZC400001PMsg inParam, List<Map<String, Object>> orderList){
            String hldRsnCd = getHldRsnFromValSet(orderList);
            
            if (ZYPCommonFunc.hasValue(hldRsnCd)) {
                addHld(inParam, orderList, hldRsnCd);
            }
        }
        
        private String getHldRsnFromValSet(List<Map<String, Object>> orderList) {
            Map<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put("glblCmpyCd", (String) orderList.get(0).get("GLBL_CMPY_CD"));
            queryParam.put("dsOrdCatgCd", (String) orderList.get(0).get("DS_ORD_CATG_CD"));
            queryParam.put("dsOrdTpCd", (String) orderList.get(0).get("DS_ORD_TP_CD"));
            queryParam.put("ordCatgCtxTpCd", ORD_CATG_CTX_TP.RETURN_HOLD_TARGET);

            return (String) ssmClient.queryObject("getHldRsnFromValSet", queryParam);
        }
        
        private void addHld(NWZC400001PMsg inParam, List<Map<String, Object>> orderList, String hldRsnCd){
            
            HLDTMsg hldTMsg = new HLDTMsg();
            hldTMsg.setSQLID("008");
            hldTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
            hldTMsg.setConditionValue("cpoOrdNum01", (String) orderList.get(0).get("CPO_ORD_NUM"));
            hldTMsg.setConditionValue("hldRsnCd01", hldRsnCd);
            
            EZDTMsgArray array =  S21ApiTBLAccessor.findByCondition(hldTMsg);
            if (array != null && array.length() != 0) {
                return;
            }
            
            final Map<String, Object> hldOrd = new HashMap<String, Object>();
            hldOrd.put("HLD_RSN_CD", hldRsnCd);
            hldOrd.put("CPO_ORD_NUM", (String) orderList.get(0).get("CPO_ORD_NUM"));
            
            execHld(hldOrd);
        }
        // 2018/05/08 S21_NA#22131 Add End
    }

    private static void setHldFlg(NWZC400001PMsg pMsg) {

        if (pMsg.xxOrdInfoList.getValidCount() > 0) {
            ZYPEZDItemValueSetter.setValue(pMsg.xxHldFlg, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(pMsg.xxHldFlg, ZYPConstant.FLG_OFF_N);
        }
    }

    /**
     * Process Requester.
     * @author K.Tajima
     */
    public static enum Requester {

        /**
         * [NWAL0010] : Order Entry Screen (New Entry)
         */
        NWAL0010_ORDER_ENTRY_SCREEN_NEW_ENTRY

        /**
         * [NWAL0050] : Hold Release Screen
         */
        , NWAL0050_HOLD_RELEASE_SCREEN;
    }


    private boolean hasXxMsgId() {
        return xxMsgIdSet != null && !xxMsgIdSet.isEmpty();
    }

    private void addXxMsgId(String xxMsgId) {
        if (xxMsgIdSet != null) {
            xxMsgIdSet.add(xxMsgId);
        }
    }

    private static List<Map<String, Object>> toMapList(ResultSet rs) throws SQLException {

        final List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        if (rs.next()) {

            final Set<String> columnNameSet = new HashSet<String>();
            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                columnNameSet.add(rs.getMetaData().getColumnName(i));
            }

            do {
                final Map<String, Object> data = new HashMap<String, Object>();
                list.add(data);
                for (String columnName : columnNameSet) {
                    data.put(columnName, rs.getObject(columnName));
                }
            } while (rs.next());
        }

        return list;
    }

    private S21ApiCommonBase getAPI(Class< ? extends S21ApiCommonBase> clazz) {

        if (!apiCache.containsKey(clazz)) {
            try {
                apiCache.put(clazz, (S21ApiCommonBase) Class.forName(clazz.getName()).newInstance());
            } catch (Exception e) {
                throw new S21AbendException(e);
            }
        }

        return apiCache.get(clazz);
    }

    private void executeForUpdate(List<Map<String, Object>> orderList) {

        Set<String> ordNumSet = new HashSet<String>();
        Set<String> lineNumSet = new HashSet<String>();

        for (Map<String, Object> order : orderList) {

            String glblCmpyCd = (String) order.get("GLBL_CMPY_CD");
            String cpoOrdNum = (String) order.get("CPO_ORD_NUM");
            String dsCpoRtrnLineNum = (String) order.get("DS_CPO_RTRN_LINE_NUM");
            String dsCpoRtrnLineSubNum = (String) order.get("DS_CPO_RTRN_LINE_SUB_NUM");

            if (!ordNumSet.contains(cpoOrdNum)) {
                CPOTMsg cpoTMsg = new CPOTMsg();
                ZYPEZDItemValueSetter.setValue(cpoTMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(cpoTMsg.cpoOrdNum, cpoOrdNum);
                cpoTMsg = (CPOTMsg) S21FastTBLAccessor.findByKeyForUpdateAPI(cpoTMsg);

                ordNumSet.add(cpoOrdNum);
            }

            StringBuilder lineNum = new StringBuilder();
            lineNum.append(dsCpoRtrnLineNum).append(",").append(dsCpoRtrnLineSubNum);

            if (!lineNumSet.contains(lineNum.toString())) {
                DS_CPO_RTRN_DTLTMsg dsCpoRtrnDtlTMsg = new DS_CPO_RTRN_DTLTMsg();
                ZYPEZDItemValueSetter.setValue(dsCpoRtrnDtlTMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(dsCpoRtrnDtlTMsg.cpoOrdNum, cpoOrdNum);
                ZYPEZDItemValueSetter.setValue(dsCpoRtrnDtlTMsg.dsCpoRtrnLineNum, dsCpoRtrnLineNum);
                ZYPEZDItemValueSetter.setValue(dsCpoRtrnDtlTMsg.dsCpoRtrnLineSubNum, dsCpoRtrnLineSubNum);
                dsCpoRtrnDtlTMsg = (DS_CPO_RTRN_DTLTMsg) S21FastTBLAccessor.findByKeyForUpdateAPI(dsCpoRtrnDtlTMsg);

                lineNumSet.add(lineNum.toString());
            }
        }
    }


    private boolean isHldCtrlOrg(DS_CPO_RTRN_DTLTMsg cpoRtrnDtlTMsg, List<Map<String, String>> hldCtrlOrgList, String hldRsnCd) {

        // ---------------------------------------------------------------------
        // If data doesn't exist in the HLD_CTRL_ORG table, Hold is not created.
        // ---------------------------------------------------------------------
        if (isEmpty(hldCtrlOrgList)) {
            return false;
        }

        // HLD_CTRL_ORG
        for (Map<String, String> hldCtrlOrg : hldCtrlOrgList) {
            if (S21StringUtil.isEquals(hldRsnCd, hldCtrlOrg.get("HLD_RSN_CD"))) {
                if (S21StringUtil.isEquals("*", hldCtrlOrg.get("FIRST_ORG_CD"))) {
                    return true;
                }
                if (!ZYPCommonFunc.hasValue(hldCtrlOrg.get("FIRST_ORG_CD"))) {
                    return false;
                }
            }
        }

        // TOC_CD
        final S21_ORGTMsg s21Org = getS21Org(cpoRtrnDtlTMsg.glblCmpyCd.getValue(), cpoRtrnDtlTMsg.slsRepOrSlsTeamTocCd.getValue());
        if (s21Org == null) {
            return false;
        }

        for (Map<String, String> hldCtrlOrg : hldCtrlOrgList) {

            final String defHldRsnCd = hldCtrlOrg.get("HLD_RSN_CD");
            final String tocCd = hldCtrlOrg.get("TOC_CD");
            final String eighthOrgCd = hldCtrlOrg.get("EIGHTH_ORG_CD");
            final String svnthOrgCd = hldCtrlOrg.get("SVNTH_ORG_CD");
            final String sixthOrgCd = hldCtrlOrg.get("SIXTH_ORG_CD");
            final String fifthOrgCd = hldCtrlOrg.get("FIFTH_ORG_CD");
            final String frthOrgCd = hldCtrlOrg.get("FRTH_ORG_CD");
            final String thirdOrgCd = hldCtrlOrg.get("THIRD_ORG_CD");
            final String scdOrgCd = hldCtrlOrg.get("SCD_ORG_CD");
            final String firstOrgCd = hldCtrlOrg.get("FIRST_ORG_CD");

            // HldRsnCd
            if (ZYPCommonFunc.hasValue(defHldRsnCd)) {
                if (!S21StringUtil.isEquals(hldRsnCd, defHldRsnCd)) {
                    continue;
                }
            } else {
                continue;
            }

            // Toc
            if (ZYPCommonFunc.hasValue(tocCd)) {
                if (S21StringUtil.isEquals(tocCd, s21Org.tocCd.getValue())) {
                    return true;
                }

                // 8th
            } else if (ZYPCommonFunc.hasValue(eighthOrgCd)) {
                if (S21StringUtil.isEquals(eighthOrgCd, s21Org.eighthOrgCd.getValue())
                        && S21StringUtil.isEquals(svnthOrgCd, s21Org.svnthOrgCd.getValue())
                        && S21StringUtil.isEquals(sixthOrgCd, s21Org.sixthOrgCd.getValue())
                        && S21StringUtil.isEquals(fifthOrgCd, s21Org.fifthOrgCd.getValue())
                        && S21StringUtil.isEquals(frthOrgCd, s21Org.frthOrgCd.getValue())
                        && S21StringUtil.isEquals(thirdOrgCd, s21Org.thirdOrgCd.getValue())
                        && S21StringUtil.isEquals(scdOrgCd, s21Org.scdOrgCd.getValue())
                        && S21StringUtil.isEquals(firstOrgCd, s21Org.firstOrgCd.getValue())) {
                    return true;
                }

                // 7th
            } else if (ZYPCommonFunc.hasValue(svnthOrgCd)) {
                if (S21StringUtil.isEquals(svnthOrgCd, s21Org.svnthOrgCd.getValue())
                        && S21StringUtil.isEquals(sixthOrgCd, s21Org.sixthOrgCd.getValue())
                        && S21StringUtil.isEquals(fifthOrgCd, s21Org.fifthOrgCd.getValue())
                        && S21StringUtil.isEquals(frthOrgCd, s21Org.frthOrgCd.getValue())
                        && S21StringUtil.isEquals(thirdOrgCd, s21Org.thirdOrgCd.getValue())
                        && S21StringUtil.isEquals(scdOrgCd, s21Org.scdOrgCd.getValue())
                        && S21StringUtil.isEquals(firstOrgCd, s21Org.firstOrgCd.getValue())) {
                    return true;
                }

                // 6th
            } else if (ZYPCommonFunc.hasValue(sixthOrgCd)) {
                if (S21StringUtil.isEquals(sixthOrgCd, s21Org.sixthOrgCd.getValue())
                        && S21StringUtil.isEquals(fifthOrgCd, s21Org.fifthOrgCd.getValue())
                        && S21StringUtil.isEquals(frthOrgCd, s21Org.frthOrgCd.getValue())
                        && S21StringUtil.isEquals(thirdOrgCd, s21Org.thirdOrgCd.getValue())
                        && S21StringUtil.isEquals(scdOrgCd, s21Org.scdOrgCd.getValue())
                        && S21StringUtil.isEquals(firstOrgCd, s21Org.firstOrgCd.getValue())) {
                    return true;
                }

                // 5th
            } else if (ZYPCommonFunc.hasValue(fifthOrgCd)) {
                if (S21StringUtil.isEquals(fifthOrgCd, s21Org.fifthOrgCd.getValue())
                        && S21StringUtil.isEquals(frthOrgCd, s21Org.frthOrgCd.getValue())
                        && S21StringUtil.isEquals(thirdOrgCd, s21Org.thirdOrgCd.getValue())
                        && S21StringUtil.isEquals(scdOrgCd, s21Org.scdOrgCd.getValue())
                        && S21StringUtil.isEquals(firstOrgCd, s21Org.firstOrgCd.getValue())) {
                    return true;
                }

                // 4th
            } else if (ZYPCommonFunc.hasValue(frthOrgCd)) {
                if (S21StringUtil.isEquals(frthOrgCd, s21Org.frthOrgCd.getValue())
                        && S21StringUtil.isEquals(thirdOrgCd, s21Org.thirdOrgCd.getValue())
                        && S21StringUtil.isEquals(scdOrgCd, s21Org.scdOrgCd.getValue())
                        && S21StringUtil.isEquals(firstOrgCd, s21Org.firstOrgCd.getValue())) {
                    return true;
                }

                // 3rd
            } else if (ZYPCommonFunc.hasValue(thirdOrgCd)) {
                if (S21StringUtil.isEquals(thirdOrgCd, s21Org.thirdOrgCd.getValue())
                        && S21StringUtil.isEquals(scdOrgCd, s21Org.scdOrgCd.getValue())
                        && S21StringUtil.isEquals(firstOrgCd, s21Org.firstOrgCd.getValue())) {
                    return true;
                }

                // 2nd
            } else if (ZYPCommonFunc.hasValue(scdOrgCd)) {
                if (S21StringUtil.isEquals(scdOrgCd, s21Org.scdOrgCd.getValue())
                        && S21StringUtil.isEquals(firstOrgCd, s21Org.firstOrgCd.getValue())) {
                    return true;
                }

                // 1st
            } else if (ZYPCommonFunc.hasValue(firstOrgCd)) {
                if (S21StringUtil.isEquals(firstOrgCd, s21Org.firstOrgCd.getValue())) {
                    return true;
                }
            }
        }

        return false;
    }

    private S21_ORGTMsg getS21Org(final String glblCmpyCd, final String tocCd) {

        final S21_ORGTMsg s21OrgTMsg = new S21_ORGTMsg();
        ZYPEZDItemValueSetter.setValue(s21OrgTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(s21OrgTMsg.tocCd, tocCd);

        return (S21_ORGTMsg) findByKeyWithCache(s21OrgTMsg);
    }

    private boolean isValidOrdTp(List<String> applyOrdTpList, String hldRsnCd, String cpoSrcTpCd, String cpoOrdTpCd, String whTpCd) {

        // HLD_RSN_CD + "." + CPO_SRC_TP_CD + "." + CPO_ORD_TP_CD + "." + WH_TP_CD
        final StringBuilder sb = new StringBuilder();
        sb.append(hldRsnCd).append(".").append(cpoSrcTpCd).append(".").append(cpoOrdTpCd).append(".").append(whTpCd);

        boolean isValidOrdTp = applyOrdTpList.contains(sb.toString());

        if (!isValidOrdTp) {
            if (!WH_TP.COMMON.equals(whTpCd)) {
                isValidOrdTp = isValidOrdTp(applyOrdTpList, hldRsnCd, cpoSrcTpCd, cpoOrdTpCd, WH_TP.COMMON);
            }
        }

        return isValidOrdTp;
    }

    // 2018/11/05 S21_NA#29015 Add Start
    private void insBizProcLogBooked(String trxHdrNum) {

        final S21BusinessProcessLogMsg bizLogMsg = new S21BusinessProcessLogMsg();
        bizLogMsg.setSubSysId(SUB_SYS_ID);
        bizLogMsg.setProcId(PROCESS_ID);
        bizLogMsg.setEventId(EVENT_ID);
        bizLogMsg.setDocTpCd(DOCUMENT_TYPE);
        bizLogMsg.setDocId("");
        bizLogMsg.setPrntDocId(trxHdrNum);
        bizLogMsg.setBizProcCmntTxt_01(null);
        bizLogMsg.setBizProcCmntTxt_02(null);
        bizLogMsg.setBizProcCmntTxt_03(null);

        S21BusinessProcessLog.print(bizLogMsg);
    }
    // 2018/11/05 S21_NA#29015 Add End
}
