package com.canon.cusa.s21.batch.NFD.NFDB008001;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.canon.cusa.s21.batch.NFD.NFDB008001.constant.NFDB008001Constant;
import com.canon.cusa.s21.common.NFX.NFXC308001.NFCConst;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_CASH_APPLY_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CLT_CUST_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CLT_DSPT_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CLT_STRGY_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CLT_WRK_ITEM_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CLT_WRK_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;

import business.db.CLT_STRGY_TRXTMsg;
import business.db.CLT_STRGY_WRK_ITEM_TRXTMsg;
import business.db.CLT_WRK_ITEMTMsg;

/**
 *<pre>
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/05/05   Fujitsu         K.Fujita        Create          CSA
 * 2016/01/11   CSAI            K.Uramori       Update          Add consolidated invoice concept
 * 2016/03/18   CSAI            K.Uramori       Update          QC#5525  RWED was less than RWSD
 * 2016/07/20   Hitachi         K.Kojima        Update          QC#10188
 * 2016/08/23   Hitachi         K.Kojima        Update          QC#13204
 * 2016/08/29   Hitachi         K.Kojima        Update          QC#10786
 * 2016/09/26   Hitachi         K.Kojima        Update          QC#13004
 * 2017/08/07   Hitachi         T.Tsuchida      Update          QC#19576
 * 2017/09/14   Hitachi         T.Tsuchida      Update          QC#21160
 * 2018/05/11   Hitachi         J.Kim           Update          QC#21426
 * 2018/08/09   Hitachi         J.Kim           Update          QC#27446
 * 2021/04/30   CITS            H.Dimay         Update          QC#58696
 * 2021/05/19   CITS            H.Dimay         Update          QC#58696-1
 * 2021/10/08   CITS            K.Suzuki        Update          QC#59284
 * 2022/02/01   CITS            K.Suzuki        Update          QC#59672
 *</pre>
 */
public class NFDB008001 extends S21BatchMain implements NFDB008001Constant {

    /**
     * Error Message: [@] is mandatory.
     */
    public static final String ZZZM9025E = "ZZZM9025E";

    /**
     * Error Message: Failed to insert [@].
     */
    public static final String NFDM0013E = "NFDM0013E";

    /**
     * Global Company Code
     */
    private String glblCmpyCd;

    /**
     * Batch Process Date
     */
    private String bpDt;

    /**
     * Count of committed records
     */
    private int commitCount;

    // START 2016/09/26 K.Kojima [QC#13004,ADD]
    /** Commit Max Number */
    private int commitNumber = 0;

    /** SSM-Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** VarCharConst : SUB_SYS_CD */
    private String subSysCd = null;

    // END 2016/09/26 K.Kojima [QC#13004,ADD]

    /**
     * Batch Client
     */
    private S21SsmBatchClient ssmBatchClient;

    /**
     * Termination Code
     */
    private TERM_CD termCd;

    /**
     * CLT_STRGY_TRX_SQ
     */
    static final String CLT_STRGY_TRX_SQ = "CLT_STRGY_TRX_SQ";

    /**
     * CLT_STRGY_WRK_ITEM_TRX_SQ
     */
    static final String CLT_STRGY_WRK_ITEM_TRX_SQ = "CLT_STRGY_WRK_ITEM_TRX_SQ";

    /**
     * Collection Work Item Status of Open List
     */
    private final String[] cltWrkItemStsCdOpenList = new String[] {CLT_WRK_ITEM_STS.PENDING, CLT_WRK_ITEM_STS.OPEN};

    // START 2021/10/08 K.Suzuki [QC#59284,ADD]
    /**
     * Collection Work Item Status of Complete List
     */
    private final String[] cltWrkItemStsCdCompleteList = new String[] {CLT_WRK_ITEM_STS.COMPLETED};
    // END   2021/10/08 K.Suzuki [QC#59284,ADD]

    /**
     * Date Format
     */
    private DateFormat df;

    /**
     * Calendar
     */
    private Calendar cal;

    @Override
    protected void initRoutine() {

        // Global Company Code
        glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(glblCmpyCd)) {
            throw new S21AbendException(ZZZM9025E, toArray("Global Company Code"));
        }

        // Batch Process Date
        bpDt = ZYPDateUtil.getBatProcDate(glblCmpyCd);

        commitCount = 0;
        termCd = TERM_CD.NORMAL_END;

        // START 2016/09/26 K.Kojima [QC#13004,ADD]

        // Get Commit Number
        this.commitNumber = getCommitCount();
        if (this.commitNumber <= 0 || this.commitNumber >= MAX_COMMIT_NUMBER) {
            this.commitNumber = MAX_COMMIT_NUMBER;
        }

        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        // END 2016/09/26 K.Kojima [QC#13004,ADD]
        ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

        cal = Calendar.getInstance();

        df = new SimpleDateFormat("yyyyMMdd");

        // START 2016/09/27 K.Kojima [QC#13004,ADD]
        this.subSysCd = ZYPCodeDataUtil.getVarCharConstValue(NFCConst.CST_VAR_CHAR_CONST_NAME_SUB_SYS_ID, glblCmpyCd);
        // END 2016/09/27 K.Kojima [QC#13004,ADD]
    }

    private static String[] toArray(String str) {
        return new String[] {str };
    }

    @Override
    protected void mainRoutine() {

        // START 2016/09/27 K.Kojima [QC#13004,ADD]
        PreparedStatement ps = null;
        ResultSet rs = null;
        ps = null;
        rs = null;
        List<CLT_STRGY_TRXTMsg> trxTMsgList = new ArrayList<CLT_STRGY_TRXTMsg>();
        try {
            Map<String, Object> stesParam = new HashMap<String, Object>();
            stesParam.put("glblCmpyCd", this.glblCmpyCd);
            stesParam.put("cltStrgyStsCdOpen", CLT_STRGY_STS.OPEN);
            stesParam.put("flgY", ZYPConstant.FLG_ON_Y);
            stesParam.put("cltWrkItemStsCdOpenList", this.cltWrkItemStsCdOpenList);
            ps = this.ssmLLClient.createPreparedStatement("getCloseStrategyPk1", stesParam, execParam());
            rs = ps.executeQuery();
            while (rs.next()) {
                CLT_STRGY_TRXTMsg tMsg = getCltStrgyTrxTMsg(rs);
                ZYPEZDItemValueSetter.setValue(tMsg.cltStrgyCurFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(tMsg.cltStrgyStsCd, CLT_STRGY_STS.CLOSE);
                trxTMsgList.add(tMsg);
                if (this.commitNumber == trxTMsgList.size()) {
                    executionUpdate(trxTMsgList);
                    this.commitCount += trxTMsgList.size();
                    trxTMsgList = new ArrayList<CLT_STRGY_TRXTMsg>();
                }
            }
            if (trxTMsgList.size() > 0) {
                executionUpdate(trxTMsgList);
                this.commitCount += trxTMsgList.size();
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }

        ps = null;
        rs = null;
        trxTMsgList = new ArrayList<CLT_STRGY_TRXTMsg>();
        List<CLT_STRGY_WRK_ITEM_TRXTMsg> wrkTMsgList = new ArrayList<CLT_STRGY_WRK_ITEM_TRXTMsg>();
        try {
            Map<String, Object> stesParam = getStrategyAssignListParam();
            ps = this.ssmLLClient.createPreparedStatement("getCloseStrategyPk2", stesParam, execParam());
            rs = ps.executeQuery();
            while (rs.next()) {
                CLT_STRGY_TRXTMsg tMsg = getCltStrgyTrxTMsg(rs);
                ZYPEZDItemValueSetter.setValue(tMsg.cltStrgyCurFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(tMsg.cltStrgyStsCd, CLT_STRGY_STS.CLOSE);
                trxTMsgList.add(tMsg);
                getCancelCltWrkItem(wrkTMsgList, tMsg.cltStrgyTrxPk.getValue());
                if (this.commitNumber == trxTMsgList.size()) {
                    executionUpdate(trxTMsgList, wrkTMsgList);
                    this.commitCount += trxTMsgList.size();
                    trxTMsgList = new ArrayList<CLT_STRGY_TRXTMsg>();
                    wrkTMsgList = new ArrayList<CLT_STRGY_WRK_ITEM_TRXTMsg>();
                }
            }
            if (trxTMsgList.size() > 0) {
                executionUpdate(trxTMsgList, wrkTMsgList);
                this.commitCount += trxTMsgList.size();
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }
        trxTMsgList = new ArrayList<CLT_STRGY_TRXTMsg>();
        wrkTMsgList = new ArrayList<CLT_STRGY_WRK_ITEM_TRXTMsg>();
        try {
            Map<String, Object> stesParam = getStrategyAssignListParam();
            ps = this.ssmLLClient.createPreparedStatement("selectStrategyAssignList", stesParam, execParam());
            rs = ps.executeQuery();
            while (rs.next()) {
                String cltStrMstCd = rs.getString("CLT_STRGY_CD");
                String billToCustCd = rs.getString("BILL_TO_CUST_CD");
                String billToCustAcctCd = rs.getString("BILL_TO_CUST_ACCT_CD");
                String cltPsnCd = rs.getString("CLT_PSN_CD");
                BigDecimal cltStrgyTrxSeq = createCollectionStrategyTransaction(trxTMsgList, cltStrMstCd, billToCustCd, billToCustAcctCd);
                createCollectionStrategyWorkItemTransaction(wrkTMsgList, cltStrMstCd, billToCustCd, cltStrgyTrxSeq, cltPsnCd);
                if (this.commitNumber == trxTMsgList.size()) {
                    executionInsert(trxTMsgList, wrkTMsgList);
                    this.commitCount += trxTMsgList.size();
                    trxTMsgList = new ArrayList<CLT_STRGY_TRXTMsg>();
                    wrkTMsgList = new ArrayList<CLT_STRGY_WRK_ITEM_TRXTMsg>();
                }
            }
            if (trxTMsgList.size() > 0) {
                executionInsert(trxTMsgList, wrkTMsgList);
                this.commitCount += trxTMsgList.size();
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }
        // END 2016/09/27 K.Kojima [QC#13004,ADD]
        // START 2018/08/09 J.Kim [QC#27446,ADD]
        setCltWrkItemRwdDt(wrkTMsgList);
        if (wrkTMsgList.size() > 0) {
            CLT_STRGY_WRK_ITEM_TRXTMsg[] cltStrgyWrkItemTrxList = new CLT_STRGY_WRK_ITEM_TRXTMsg[wrkTMsgList.size()];
            int rtn = S21FastTBLAccessor.update(wrkTMsgList.toArray(cltStrgyWrkItemTrxList));
            if (rtn > 0) {
                wrkTMsgList = new ArrayList<CLT_STRGY_WRK_ITEM_TRXTMsg>();
            } else {
                throw new S21AbendException("NFBM0028E");
            }
        }
        // END 2018/08/09 J.Kim [QC#27446,ADD]
        // START 2017/08/07 T.Tsuchida [QC#19576,ADD]
        getOpenCltWrkItem(wrkTMsgList);
        if (wrkTMsgList.size() > 0) {
            CLT_STRGY_WRK_ITEM_TRXTMsg[] cltStrgyWrkItemTrxList = new CLT_STRGY_WRK_ITEM_TRXTMsg[wrkTMsgList.size()];
            int rtn = S21FastTBLAccessor.update(wrkTMsgList.toArray(cltStrgyWrkItemTrxList));
            if (rtn > 0) {
                this.commitCount += rtn;
            } else {
                throw new S21AbendException("NFBM0028E");
            }
        }
        // END 2017/08/07 T.Tsuchida [QC#19576,ADD]
    }

    @Override
    protected void termRoutine() {
        super.setTermState(termCd, commitCount, 0, 0);
    }

    /**
     * @param args String[]
     */
    public static void main(String[] args) {
        new NFDB008001().executeBatch(NFDB008001.class.getSimpleName());
    }

    // START 2017/09/14 T.Tsuchida [QC#21160,MOD]
    /**
     * getStrategyAssignListParam
     * @return Map<String, Object>
     */
    private Map<String, Object> getStrategyAssignListParam() throws SQLException {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("arCashApplyStsCdUnapplied", AR_CASH_APPLY_STS.UNAPPLIED);
        param.put("arCashApplyStsCdPartial", AR_CASH_APPLY_STS.PARTIAL);
        param.put("arTrxTpRcp", AR_TRX_TP.RECEIPT);
        param.put("cltCustTpCdNoDngStrgy", CLT_CUST_TP.NO_DUNNING_STRATEGY);
        param.put("cltCustTpCdStrategyCompleted", CLT_CUST_TP.STRATEGY_COMPLETED);
        param.put("cltStrgyTrxY", ZYPConstant.FLG_ON_Y);
        param.put("bpDt", bpDt);
        param.put("cltDsptStsCdApproved", CLT_DSPT_STS.APPROVED);
        param.put("subSysCd", this.subSysCd);
        param.put("batTpCd", NFCConst.CST_ONL_BAT_TP_CD_BAT);
        param.put("cltStrgyStsCdOpen", CLT_STRGY_STS.OPEN);
        return param;
    }
    // END 2017/09/14 T.Tsuchida [QC#21160,MOD]

    // START 2016/09/27 K.Kojima [QC#13004,MOD]
    /**
     * <pre> 
     *  Create Collection Strategy Transaction
     * </pre>
     * @param colStrMstCd Collection Strategy Master Code
     * @param billToCustCd Bill To Customer Code
     * @return cltStrgyTrxSeq
     */
    private BigDecimal createCollectionStrategyTransaction(List<CLT_STRGY_TRXTMsg> trxTMsgList, String cltStrMstCd, String billToCustCd, String billToCustAcctCd) {

        BigDecimal cltStrgyTrxSeq = ZYPOracleSeqAccessor.getNumberBigDecimal(CLT_STRGY_TRX_SQ);

        CLT_STRGY_TRXTMsg cltStrgyTrxtmsg = new CLT_STRGY_TRXTMsg();
        cltStrgyTrxtmsg.glblCmpyCd.setValue(glblCmpyCd);
        cltStrgyTrxtmsg.cltStrgyTrxPk.setValue(cltStrgyTrxSeq);
        cltStrgyTrxtmsg.cltStrgyCd.setValue(cltStrMstCd);
        cltStrgyTrxtmsg.cltStrgyStsCd.setValue(CLT_STRGY_STS.OPEN);
        cltStrgyTrxtmsg.cltStrgyCurFlg.setValue(ZYPConstant.FLG_ON_Y);
        cltStrgyTrxtmsg.billToCustCd.setValue(billToCustCd);
        cltStrgyTrxtmsg.cltAcctCd.setValue(billToCustAcctCd);

        trxTMsgList.add(cltStrgyTrxtmsg);

        return cltStrgyTrxSeq;
    }

    // START 2016/09/27 K.Kojima [QC#13004,MOD]
    /**
     * <pre> 
     *  Create Collection Strategy Work Item Transaction
     * </pre>
     * @param wrkTMsgList List<CLT_STRGY_WRK_ITEM_TRXTMsg>
     * @param cltStrMstCd Collection Strategy Master Code
     * @param billToCustCd Bill To Customer Code
     * @param cltStrgyTrxSeq Collection Strategy Transaction Table
     * Sequence
     * @param cltPsnCd Collection Person Code
     */
    private void createCollectionStrategyWorkItemTransaction(List<CLT_STRGY_WRK_ITEM_TRXTMsg> wrkTMsgList, String cltStrMstCd, String billToCustCd, BigDecimal cltStrgyTrxSeq, String cltPsnCd) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("cltStrMstCd", cltStrMstCd);

        List<Map> workItemList = (List<Map>) this.ssmBatchClient.queryObjectList("selectWorkItemList", param);

        String cltWrkItemRwsdDt = bpDt;
        // START 2018/05/11 J.Kim [QC#21426, MOD]
        // for (Map workItem : workItemList) {
        for (int index = 0; index < workItemList.size(); index++) {
            Map workItem = workItemList.get(index);
            // END 2018/05/11 J.Kim [QC#21426, MOD]

            // Get Collection Work Item Transaction
            String cltWrkItemCd = (String) workItem.get("CLT_WRK_ITEM_CD");
            String cltWrkItemNm = (String) workItem.get("CLT_WRK_ITEM_NM");
            String cltWrkTpCd = (String) workItem.get("CLT_WRK_TP_CD");
            String cltWrkTpNm = (String) workItem.get("CLT_WRK_TP_NM");
            String cltCatgCd = (String) workItem.get("CLT_WRK_CATG_CD");
            String cltCatgNm = (String) workItem.get("CLT_WRK_CATG_NM");
            BigDecimal cltWrkWaitDaysAot = (BigDecimal) workItem.get("CLT_WRK_WAIT_DAYS_AOT");
            BigDecimal cltWrkEsclWaitDaysAot = (BigDecimal) workItem.get("CLT_WRK_ESCL_WAIT_DAYS_AOT");

            BigDecimal cltStrgyWorkItemTrxSeq = ZYPOracleSeqAccessor.getNumberBigDecimal(CLT_STRGY_WRK_ITEM_TRX_SQ);

            CLT_STRGY_WRK_ITEM_TRXTMsg cltStrgyWrkItemTrxtmsg = new CLT_STRGY_WRK_ITEM_TRXTMsg();
            cltStrgyWrkItemTrxtmsg.glblCmpyCd.setValue(glblCmpyCd);
            cltStrgyWrkItemTrxtmsg.cltStrgyTrxPk.setValue(cltStrgyTrxSeq);
            cltStrgyWrkItemTrxtmsg.cltStrgyWrkItemTrxPk.setValue(cltStrgyWorkItemTrxSeq);

            cltStrgyWrkItemTrxtmsg.cltWrkItemCd.setValue(cltWrkItemCd);
            cltStrgyWrkItemTrxtmsg.cltWrkItemNm.setValue(cltWrkItemNm);
            cltStrgyWrkItemTrxtmsg.cltWrkTpCd.setValue(cltWrkTpCd);
            cltStrgyWrkItemTrxtmsg.cltWrkTpNm.setValue(cltWrkTpNm);
            cltStrgyWrkItemTrxtmsg.cltWrkCatgCd.setValue(cltCatgCd);
            cltStrgyWrkItemTrxtmsg.cltWrkCatgNm.setValue(cltCatgNm);

            // START 2018/05/11 J.Kim [QC#21426, MOD]
            if (index == 0) {
                ZYPEZDItemValueSetter.setValue(cltStrgyWrkItemTrxtmsg.cltWrkItemRwsdDt, cltWrkItemRwsdDt);
                cltWrkItemRwsdDt = getAddDate(cltWrkItemRwsdDt, cltWrkWaitDaysAot);

                if (CLT_WRK_TP.AUTOMATIC.equals(cltWrkTpCd) || CLT_WRK_TP.WORK_FLOW.equals(cltWrkTpCd)) {
                    cltStrgyWrkItemTrxtmsg.cltWrkItemRwedDt.setValue(cltStrgyWrkItemTrxtmsg.cltWrkItemRwsdDt.getValue());
                } else {
                    cltStrgyWrkItemTrxtmsg.cltWrkItemRwedDt.setValue(getAddDate(cltStrgyWrkItemTrxtmsg.cltWrkItemRwsdDt.getValue(), cltWrkEsclWaitDaysAot));
                }
            }
            // END 2018/05/11 J.Kim [QC#21426, MOD]
            cltStrgyWrkItemTrxtmsg.billToCustCd.setValue(billToCustCd);
            cltStrgyWrkItemTrxtmsg.cltWrkItemStsCd.setValue(CLT_WRK_ITEM_STS.PENDING);

            if (CLT_WRK_TP.MANUAL.equals(cltWrkTpCd)) {
                ZYPEZDItemValueSetter.setValue(cltStrgyWrkItemTrxtmsg.cltWrkItemOwnrId, cltPsnCd);
            }

            wrkTMsgList.add(cltStrgyWrkItemTrxtmsg);
        }
    }
    // END 2016/09/27 K.Kojima [QC#13004,MOD]

    /**
     * Add date
     * @param dateStr now date(yyyyMMdd) string
     * @param addDate add date
     * @return add date string
     */
    public String getAddDate(String dateStr, BigDecimal addDate) {

        Date dt;
        try {
            dt = df.parse(dateStr);

            cal.setTime(dt);

            cal.add(Calendar.DATE, addDate.intValue());

        } catch (ParseException e) {
            throw new S21AbendException(NFDM0013E, toArray("CLT_STRGY_WRK_ITEM_TRX"));
        }

        return df.format(cal.getTime());
    }

    // START 2016/09/26 K.Kojima [QC#13004,ADD]
    /**
     * execParam
     * @return S21SsmExecutionParameter
     */
    private S21SsmExecutionParameter execParam() {
        S21SsmExecutionParameter execPrm = new S21SsmExecutionParameter();
        execPrm.setFetchSize(FETCH_SIZE_MAX);
        execPrm.setFetchDirection(ResultSet.FETCH_FORWARD);
        execPrm.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execPrm.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
        return execPrm;
    }

    /**
     * getApLseBoWfRstTMsg
     * @param rs ResultSet
     * @return AP_LSE_BO_WF_RQSTTMsg
     * @throws SQLException SQLException
     */
    private CLT_STRGY_TRXTMsg getCltStrgyTrxTMsg(ResultSet rs) throws SQLException {
        CLT_STRGY_TRXTMsg tmsg = new CLT_STRGY_TRXTMsg();
        ZYPEZDItemValueSetter.setValue(tmsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tmsg.cltStrgyTrxPk, rs.getBigDecimal(CLT_STRGY_TRX_PK));
        return (CLT_STRGY_TRXTMsg) S21ApiTBLAccessor.findByKeyForUpdate(tmsg);
    }

    /**
     * getCancelCltWrkItem
     * @param wrkTMsgList List<CLT_STRGY_WRK_ITEM_TRXTMsg>
     * @param cltStrgyTrxPk String
     */
    private void getCancelCltWrkItem(List<CLT_STRGY_WRK_ITEM_TRXTMsg> wrkTMsgList, BigDecimal cltStrgyTrxPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("cltStrgyTrxPk", cltStrgyTrxPk);
        param.put("cltWrkItemStsCdOpenList", this.cltWrkItemStsCdOpenList);
        List<Map<String, Object>> workItemList = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getCancelWrkItemPk", param);
        for (int i = 0; i < workItemList.size(); i++) {
            CLT_STRGY_WRK_ITEM_TRXTMsg tmsg = new CLT_STRGY_WRK_ITEM_TRXTMsg();
            ZYPEZDItemValueSetter.setValue(tmsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tmsg.cltStrgyWrkItemTrxPk, (BigDecimal) workItemList.get(i).get("CLT_STRGY_WRK_ITEM_TRX_PK"));
            tmsg = (CLT_STRGY_WRK_ITEM_TRXTMsg) S21ApiTBLAccessor.findByKeyForUpdate(tmsg);
            ZYPEZDItemValueSetter.setValue(tmsg.cltWrkItemStsCd, CLT_WRK_ITEM_STS.CANCEL);
            wrkTMsgList.add(tmsg);
        }
    }

    /**
     * executionInsert
     * @param trxTMsgList List<CLT_STRGY_TRXTMsg>
     * @param wrkTMsgList List<CLT_STRGY_WRK_ITEM_TRXTMsg>
     */
    private void executionInsert(List<CLT_STRGY_TRXTMsg> trxTMsgList, List<CLT_STRGY_WRK_ITEM_TRXTMsg> wrkTMsgList) {
        if (trxTMsgList.size() > 0) {
            CLT_STRGY_TRXTMsg[] cltStrgyTrxList = new CLT_STRGY_TRXTMsg[trxTMsgList.size()];
            S21FastTBLAccessor.insert(trxTMsgList.toArray(cltStrgyTrxList));
        }
        if (wrkTMsgList.size() > 0) {
            CLT_STRGY_WRK_ITEM_TRXTMsg[] cltStrgyWrkItemTrxList = new CLT_STRGY_WRK_ITEM_TRXTMsg[wrkTMsgList.size()];
            S21FastTBLAccessor.insert(wrkTMsgList.toArray(cltStrgyWrkItemTrxList));
        }
        commit();
    }

    /**
     * executionUpdate
     * @param trxTMsgList List<CLT_STRGY_TRXTMsg>
     */
    private void executionUpdate(List<CLT_STRGY_TRXTMsg> trxTMsgList) {
        CLT_STRGY_TRXTMsg[] cltStrgyTrxList = new CLT_STRGY_TRXTMsg[trxTMsgList.size()];
        S21FastTBLAccessor.update(trxTMsgList.toArray(cltStrgyTrxList));
        commit();
    }

    /**
     * executionUpdate
     * @param trxTMsgList List<CLT_STRGY_TRXTMsg>
     * @param wrkTMsgList List<CLT_STRGY_WRK_ITEM_TRXTMsg>
     */
    private void executionUpdate(List<CLT_STRGY_TRXTMsg> trxTMsgList, List<CLT_STRGY_WRK_ITEM_TRXTMsg> wrkTMsgList) {
        if (trxTMsgList.size() > 0) {
            CLT_STRGY_TRXTMsg[] cltStrgyTrxList = new CLT_STRGY_TRXTMsg[trxTMsgList.size()];
            S21FastTBLAccessor.update(trxTMsgList.toArray(cltStrgyTrxList));
        }
        if (wrkTMsgList.size() > 0) {
            CLT_STRGY_WRK_ITEM_TRXTMsg[] cltStrgyWrkItemTrxList = new CLT_STRGY_WRK_ITEM_TRXTMsg[wrkTMsgList.size()];
            S21FastTBLAccessor.update(wrkTMsgList.toArray(cltStrgyWrkItemTrxList));
        }
        commit();
    }
    // END 2016/09/26 K.Kojima [QC#13004,ADD]

    // START 2017/08/07 T.Tsuchida [QC#19576,ADD]
    /**
     * getOpenCltWrkItem
     * @param wrkTMsgList List<CLT_STRGY_WRK_ITEM_TRXTMsg>
     */
    private void getOpenCltWrkItem(List<CLT_STRGY_WRK_ITEM_TRXTMsg> wrkTMsgList) {
        Map<String, Object> param = new HashMap<String, Object>();
        String startDt = getAddDate(this.bpDt, BigDecimal.ONE);
        param.put("glblCmpyCd", this.glblCmpyCd);
        // START 2018/06/12 J.Kim [QC#21426, DEL] 
        // param.put("cltWrkTpCdIsManual", CLT_WRK_TP.MANUAL);
        // END 2018/06/12 J.Kim [QC#21426, DEL]
        param.put("cltWrkItemStsCdIsPending", CLT_WRK_ITEM_STS.PENDING);
        param.put("cltWrkItemStsCdOpenList", this.cltWrkItemStsCdOpenList);
        param.put("batProcDt", startDt);
        List<Map<String, Object>> workItemList = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getOpenCltWrkItemPk", param);
        for (int i = 0; i < workItemList.size(); i++) {
            CLT_STRGY_WRK_ITEM_TRXTMsg tmsg = new CLT_STRGY_WRK_ITEM_TRXTMsg();
            ZYPEZDItemValueSetter.setValue(tmsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tmsg.cltStrgyWrkItemTrxPk, (BigDecimal) workItemList.get(i).get("CLT_STRGY_WRK_ITEM_TRX_PK"));
            tmsg = (CLT_STRGY_WRK_ITEM_TRXTMsg) S21ApiTBLAccessor.findByKeyForUpdate(tmsg);
            ZYPEZDItemValueSetter.setValue(tmsg.cltWrkItemWsrdDt, startDt);
            ZYPEZDItemValueSetter.setValue(tmsg.cltWrkItemStsCd, CLT_WRK_ITEM_STS.OPEN);
            wrkTMsgList.add(tmsg);
        }
    }
    // END 2017/08/07 T.Tsuchida [QC#19576,ADD]

    // START 2018/08/09 J.Kim [QC#27446,ADD]
    /**
     * getOpenCltWrkItem
     * @param wrkTMsgList List<CLT_STRGY_WRK_ITEM_TRXTMsg>
     */
    @SuppressWarnings(value = {"unchecked" })
    private void setCltWrkItemRwdDt(List<CLT_STRGY_WRK_ITEM_TRXTMsg> wrkTMsgList) {

        Map<String, Object> param = new HashMap<String, Object>();
        String cltWrkItemRwsdDt = bpDt;
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("cltWrkItemStsCdOpenList", this.cltWrkItemStsCdOpenList);
        List<Map<String, Object>> workItemList = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getCltWrkItemRwdDtPk", param);
        for (int i = 0; i < workItemList.size(); i++) {
            Map<String, Object> workItem = workItemList.get(i);
            CLT_STRGY_WRK_ITEM_TRXTMsg tmsg = new CLT_STRGY_WRK_ITEM_TRXTMsg();
            ZYPEZDItemValueSetter.setValue(tmsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tmsg.cltStrgyWrkItemTrxPk, (BigDecimal) workItem.get("CLT_STRGY_WRK_ITEM_TRX_PK"));
            tmsg = (CLT_STRGY_WRK_ITEM_TRXTMsg) S21ApiTBLAccessor.findByKeyForUpdate(tmsg);

            String cltWrkItemCd = (String) workItem.get("CLT_WRK_ITEM_CD");
            CLT_WRK_ITEMTMsg out = getWrkItemTMsg(cltWrkItemCd);
            if (out == null) {
                continue;
            }

            // START 2021/10/08 K.Suzuki [QC#59284,ADD]
            String completeLatestWrkItemCd = getCompleteLatestWrkItemCd((BigDecimal) workItem.get("CLT_STRGY_TRX_PK"));
            CLT_WRK_ITEMTMsg out2 = getWrkItemTMsg(completeLatestWrkItemCd);
            if (out2 == null) {
                continue;
            }
            // END   2021/10/08 K.Suzuki [QC#59284,ADD]

            String cltWrkTpCd = out.cltWrkTpCd.getValue();
            // START 2021/10/08 K.Suzuki [QC#59284,MOD]
            //BigDecimal cltWrkWaitDaysAot = out.cltWrkWaitDaysAot.getValue();
            BigDecimal cltWrkWaitDaysAot = out2.cltWrkWaitDaysAot.getValue();
            // END   2021/10/08 K.Suzuki [QC#59284,MOD]
            // START 04/30/2021 H.Dimay [QC#58696, MOD]
            //BigDecimal cltWrkEsclWaitDaysAot = out.cltWrkEsclWaitDaysAot.getValue();
            BigDecimal cltWrkNextWaitDaysAot = out.cltWrkNextWaitDaysAot.getValue();
            //ZYPEZDItemValueSetter.setValue(tmsg.cltWrkItemRwsdDt, cltWrkItemRwsdDt);
            cltWrkItemRwsdDt = getAddDate(bpDt, cltWrkWaitDaysAot);
            ZYPEZDItemValueSetter.setValue(tmsg.cltWrkItemRwsdDt, cltWrkItemRwsdDt);
            // END 04/30/2021 H.Dimay [QC#58696, MOD]

            if (CLT_WRK_TP.AUTOMATIC.equals(cltWrkTpCd) || CLT_WRK_TP.WORK_FLOW.equals(cltWrkTpCd)) {
                ZYPEZDItemValueSetter.setValue(tmsg.cltWrkItemRwedDt, cltWrkItemRwsdDt);
            } else {
                // START 04/30/2021 H.Dimay [QC#58696, MOD]
                //ZYPEZDItemValueSetter.setValue(tmsg.cltWrkItemRwedDt, getAddDate(tmsg.cltWrkItemRwsdDt.getValue(), cltWrkEsclWaitDaysAot));
                ZYPEZDItemValueSetter.setValue(tmsg.cltWrkItemRwedDt, getAddDate(tmsg.cltWrkItemRwsdDt.getValue(), cltWrkNextWaitDaysAot.add(new BigDecimal(-1))));
                // END 04/30/2021 H.Dimay [QC#58696, MOD]
            }
            wrkTMsgList.add(tmsg);
        }
    }

    /**
     * getWrkItemTMsg
     * @param String cltWrkItemCd
     * @return CLT_WRK_ITEMTMsg
     */
    private CLT_WRK_ITEMTMsg getWrkItemTMsg(String cltWrkItemCd) {
        CLT_WRK_ITEMTMsg tmsg = new CLT_WRK_ITEMTMsg();
        ZYPEZDItemValueSetter.setValue(tmsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tmsg.cltWrkItemCd, cltWrkItemCd);
        return (CLT_WRK_ITEMTMsg) S21ApiTBLAccessor.findByKey(tmsg);
    }
    // END 2018/08/09 J.Kim [QC#27446,ADD]

    // START 2021/10/08 K.Suzuki [QC#59284,ADD]
    /**
     * getCompleteLatestWrkItemCd
     * @param BigDecimal cltStrgyTrxPk
     * @return String
     */
    @SuppressWarnings(value = {"unchecked" })
    private String getCompleteLatestWrkItemCd(BigDecimal cltStrgyTrxPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("cltStrgyTrxPk", cltStrgyTrxPk);
        param.put("cltWrkItemStsCdOpenList", this.cltWrkItemStsCdOpenList);
        param.put("cltWrkItemStsCdCompleteList", this.cltWrkItemStsCdCompleteList);
        List<Map<String, Object>> workItemList = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getCompleteLatestWrkItemCd", param);
        Map<String, Object> workItem = workItemList.get(0);
        return (String) workItem.get("CLT_WRK_ITEM_CD");
    }
    // END   2021/10/08 K.Suzuki [QC#59284,ADD]
}
