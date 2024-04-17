/*
 * <Pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NWA.NWAB237001;

import static com.canon.cusa.s21.batch.NWA.NWAB237001.constant.NWAB237001Constant.*;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDItemAttribute;
import parts.dbcommon.EZDTBLAccessor;
import business.db.INTFC_CPO_WRKTMsg;

import com.canon.cusa.s21.common.NLC.NLCC001001.TRX_RSN;
import com.canon.cusa.s21.common.NMX.NMXC104001.NMXC104001ConvertPartsMdseCd;
import com.canon.cusa.s21.common.NMX.NMXC104001.NMXC104001ConvertPartsMdseCdBean;
import com.canon.cusa.s21.common.NWX.NWXC100001.NWXC100001SendMailForErrorInfo;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_WRK_ORD_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INVTY_ORD_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RCPT_CMPY_CLS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WRK_ORD_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

/**
 * <pre>
 * SCUBE Received Customer Order(Work Creation)
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/04/2013    Hitachi       K.Kasai          Create          N/A
 * 10/25/2013    Hitachi       K.Kasai          Update          MEX-IF012
 * 11/23/2013    Hitachi       H.Narumi         Update          QC3160
 * 12/16/2013    CSAI          Y.Imazu          Update          QC3258
 * 05/16/2016    CITS          N.Akaishi        Update          V1.0
 * 09/04/2018    CITS          T.Tokutomi       Update          QC#26966
 * 03/28/2019    CITS          T.Tokutomi       Update          QC#30963
 * 04/02/2019    CITS          T.Tokutomi       Update          QC#30964
 * 04/12/2019    CITS          T.Tokutomi       Update          QC#31174
 * 02/03/2020    Fujitsu       S.Iidaka         Update          QC#55572
 *</pre>
 */
public class NWAB237001 extends S21BatchMain {

    /** termination code */
    private TERM_CD termCd = null;

    /** Global Company Code */
    private String glblCmpyCd;

    /** Batch Process Date */
    private String batDt;

    /** success count */
    private int successCount;

    /** error count */
    private int errorCount;

    /** Commit Number */
    private int commitCount;

    /** vendor Code */
    private String specifiedVndCd;

    /** global global order category code */
    private String glblOrdCatgCd;

    /** Parts Charge Indicator:Sales */
    private String prtChrgIndSales;

    /** Parts Emergency Order Indicator */
    private String prtEmerOrdInd;

    /** SSM Batch Client */
    private S21SsmBatchClient ssmBatchClient = null;

    /** SSM Low Level Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** INTFC_CPO_ORD_DT:Digit */
    private int digitIntfcCpoOrdDt = 0;

    /** INTFC_SELL_TO_CUST_CD:Digit */
    private int digitIntfcSellToCustCd = 0;

    /** PRT_INCL_TECH_INVTY_CINC_FLG */
    private String prtInclTechInvtyCincFlg;

    /** PRT_EXCL_INVTY_LOC_CD_CINC */
    private ArrayList<String> invtyLocCdList = null;

    /** Message Error List */
    private List<String> msgErrList = new ArrayList<String>();

    /** SCUBE_VND_SYS_TP_CD */
    private ArrayList<String> scubeVndSysTpCdList = null;

    /**
     * Initialize method.
     */
    @Override
    protected void initRoutine() {

        // Get GLBL_CMPY_CD
        this.glblCmpyCd = getGlobalCompanyCode();

        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {

            throw new S21AbendException(ZZM9000E, new String[] {MSG_ITEM_GLOBAL_COMPANY_CODE });
        }

        // Get Batch Process Date
        this.batDt = ZYPDateUtil.getBatProcDate(this.glblCmpyCd);

        // initialize
        this.termCd = TERM_CD.NORMAL_END;
        this.successCount = 0;
        this.errorCount = 0;
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

        // get the Commit Count
        this.commitCount = getCommitCount();

        if (this.commitCount <= 0 || this.commitCount >= MAX_COMMIT_NUMBER) {

            this.commitCount = MAX_COMMIT_NUMBER;
        }

        // get vendor code
        this.specifiedVndCd = getVarCharConst(KEY_VND_CD, true);

        // get glbl Ord Catg Cd
        this.glblOrdCatgCd = getVarCharConst(KEY_CINC_GLBL_ORD_CATG_CD_NOMAL_ORDER, true);

        // get parts Charge Indicator:Sales
        this.prtChrgIndSales = getVarCharConst(KEY_PRT_CHRG_IND_C, true);

        // get Parts Emergency Order Indicator
        this.prtEmerOrdInd = getVarCharConst(KEY_PRT_EMER_ORD_IND_N, true);

        // get digit of items
        EZDItemAttribute getDigitIntfcCpoOrdDt = new INTFC_CPO_WRKTMsg().getAttr("intfcCpoOrdDt");
        EZDItemAttribute getDigitIntfcSellToCustCd = new INTFC_CPO_WRKTMsg().getAttr("intfcSellToCustCd");

        this.digitIntfcCpoOrdDt = getDigitIntfcCpoOrdDt.getDigit();
        this.digitIntfcSellToCustCd = getDigitIntfcSellToCustCd.getDigit();

        this.invtyLocCdList = getExclInvtyLocCd();
        this.prtInclTechInvtyCincFlg = getVarCharConst(KEY_PRT_INCL_TECH_INVTY_CINC_FLG, false);
        this.scubeVndSysTpCdList = getScubeVndSysTpCd();

        if (!ZYPConstant.FLG_ON_Y.equals(this.prtInclTechInvtyCincFlg) && !ZYPConstant.FLG_OFF_N.equals(this.prtInclTechInvtyCincFlg)) {
            this.termCd = TERM_CD.ABNORMAL_END;
        }

        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
    }

    @Override
    protected void mainRoutine() {

        if (TERM_CD.ABNORMAL_END.equals(this.termCd)) {
            return;
        }

        // Delete Work Data of Batch Date (for Rerun)
        deleteIntfcCpoWrk(this.batDt, ZYPConstant.FLG_ON_Y);

        String standardDt = getStandardDt();

        createIntfcCpoWrk(standardDt, STMT_GET_PARTS_ORD_DATA_CPO);
        createIntfcCpoWrk(standardDt, STMT_GET_PARTS_ORD_DATA_CPO_WH_TRNSF);
        createIntfcCpoWrk(standardDt, STMT_GET_PARTS_ORD_DATA_RMA);
        createIntfcCpoWrk(standardDt, STMT_GET_PARTS_ORD_DATA_INVTY_ORD);
        createIntfcCpoWrk(standardDt, STMT_GET_PARTS_ORD_DATA_INVTY_ORD_TECH_TRNSF);
        if (ZYPConstant.FLG_ON_Y.equals(this.prtInclTechInvtyCincFlg)) {
            createIntfcCpoWrk(standardDt, STMT_GET_PARTS_ORD_DATA_INVTY_TRX);
            createIntfcCpoWrk(standardDt, STMT_GET_PARTS_ORD_DATA_INVTY_TRX_RMN);
        }
        createIntfcCpoWrk(standardDt, STMT_GET_PARTS_ORD_DATA_INVTY_TRX_RMN_ITM_CHG);
        createIntfcCpoWrk(standardDt, STMT_GET_PARTS_ORD_DATA_WRK_ORD);

        // Delete Old data for INTFC_CPO_WRK
        deleteIntfcCpoWrk(standardDt, ZYPConstant.FLG_OFF_N);

    }

    @Override
    protected void termRoutine() {

        // post error mail.
        if (this.msgErrList.size() > 0) {

            NWXC100001SendMailForErrorInfo errMailCtrl = new NWXC100001SendMailForErrorInfo();
            errMailCtrl.addErrMsgList(this.msgErrList);
            String retValue = errMailCtrl.sendMail(glblCmpyCd, NWAB237001.class.getSimpleName());

            if (ZYPCommonFunc.hasValue(retValue)) {
                termCd = TERM_CD.ABNORMAL_END;
            }
        }

        setTermState(this.termCd, this.successCount, this.errorCount, this.successCount + this.errorCount);
    }

    /**
     * @param args String[]
     */
    public static void main(String[] args) {

        new NWAB237001().executeBatch(NWAB237001.class.getSimpleName());
    }

    /**
     * Get Standard Date for Process
     * @return String
     */
    private String getStandardDt() {

        // get MAX(INTFC_CRAT_DT) From INTFC_CPO_WRK
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(BIND_GLBL_CMPY_CD, this.glblCmpyCd);
        ssmParam.put(BIND_INTFC_CRAT_DT, this.batDt);

        String intfcCpoWrkDt = (String) ssmBatchClient.queryObject(STMT_GET_PRE_INTFC_CRAT_DT, ssmParam);

        // set standardDt
        if (intfcCpoWrkDt == null) {

            return ZYPDateUtil.addDays(this.batDt, -1);
        }

        return intfcCpoWrkDt;
    }

    /**
     * Check Insert Data
     * @param rs ResultSet
     * @return boolean
     */
    private boolean checkInsertData(ResultSet rs) {

        try {

            String shpgMethWarningCd = rs.getString(TRD_PTNR_SHPG_METH_CD);

            if (!ZYPCommonFunc.hasValue(shpgMethWarningCd)) {
                return true;
            }

        } catch (SQLException e) {

            sqlExceptionHandler(e);
        }

        return false;
    }

    /**
     * Get Max PSD Date for CPO
     * @param rs ResultSet
     * @return String
     */
    private String getIntfcRsdDt(ResultSet rs) throws SQLException {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(BIND_GLBL_CMPY_CD, this.glblCmpyCd);

        ssmParam.put(BIND_CPO_ORD_NUM, rs.getString(INTFC_CPO_ORD_NUM));
        ssmParam.put(BIND_SHPG_SVC_LVL_CD, rs.getString(SHPG_SVC_LVL_CD));
        ssmParam.put(BIND_MDSE_CD, rs.getString(INTFC_PRT_MDSE_CD));

        return (String) ssmBatchClient.queryObject(STMT_GET_MAX_PSD_DT, ssmParam);
    }

    private String editDigit(String cpoOrdTs, int digit) {

        if (cpoOrdTs != null && cpoOrdTs.length() > digit) {

            return cpoOrdTs.substring(0, digit);
        }

        return cpoOrdTs;
    }

    /**
     * Delete Past Work Data
     * @param standardDt String
     * @param rerunFlg String
     */
    private void deleteIntfcCpoWrk(String standardDt, String rerunFlg) {

        // get PreIntfcCratDt from INTFC_CPO_WRK
        List<INTFC_CPO_WRKTMsg> inTMsgList = new ArrayList<INTFC_CPO_WRKTMsg>();

        // get IntfcData(For rerun)
        PreparedStatement stmt = null;
        ResultSet rs = null;
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(this.commitCount);
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put(BIND_GLBL_CMPY_CD, this.glblCmpyCd);
        paramMap.put(BIND_PRE_INTFC_CRAT_DT, standardDt);
        paramMap.put(BIND_RERUN_FLG, rerunFlg);

        try {

            stmt = this.ssmLLClient.createPreparedStatement(STMT_GET_PAST_DATA, paramMap, execParam);
            rs = stmt.executeQuery();

            int inputCount = 0;
            int totalDeleteCount = 0;

            while (rs.next()) {

                inputCount++;
                inTMsgList.add(setDeleteValue(rs));

                if (this.commitCount == inTMsgList.size()) {

                    deleteIntfcCpoWrkDataFindByIntfcCratDt(inTMsgList);
                    totalDeleteCount += this.commitCount;
                }
            }

            if (inputCount != totalDeleteCount) {

                deleteIntfcCpoWrkDataFindByIntfcCratDt(inTMsgList);
            }

        } catch (SQLException e) {

            sqlExceptionHandler(e);

        } finally {

            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    /**
     * Set Delete Data to TMsg
     * @param rs ResultSet
     * @return INTFC_CPO_WRKTMsg
     */
    private INTFC_CPO_WRKTMsg setDeleteValue(ResultSet rs) {

        INTFC_CPO_WRKTMsg inParam = new INTFC_CPO_WRKTMsg();

        try {

            ZYPEZDItemValueSetter.setValue(inParam.glblCmpyCd, rs.getString("GLBL_CMPY_CD"));
            ZYPEZDItemValueSetter.setValue(inParam.intfcCpoOrdNum, rs.getString(INTFC_CPO_ORD_NUM));
            ZYPEZDItemValueSetter.setValue(inParam.cincGlblWhCd, rs.getString(CINC_GLBL_WH_CD));
            ZYPEZDItemValueSetter.setValue(inParam.intfcCpoOrdDt, rs.getString(INTFC_CPO_ORD_DT));
            ZYPEZDItemValueSetter.setValue(inParam.intfcPrtMdseCd, rs.getString(INTFC_PRT_MDSE_CD));
            ZYPEZDItemValueSetter.setValue(inParam.prtSizeTxt, rs.getString(PRT_SIZE_TXT));
            ZYPEZDItemValueSetter.setValue(inParam.cincGlblShpgMethCd, rs.getString(CINC_GLBL_SHPG_METH_CD));
            ZYPEZDItemValueSetter.setValue(inParam.intfcCratDt, rs.getString(INTFC_CRAT_DT));

        } catch (SQLException e) {

            sqlExceptionHandler(e);
        }

        return inParam;
    }

    /**
     * Delete Work Date by IF Creation Date
     * @param inMsgLst List<INTFC_CPO_WRKTMsg>
     */
    private void deleteIntfcCpoWrkDataFindByIntfcCratDt(List<INTFC_CPO_WRKTMsg> inMsgLst) {

        INTFC_CPO_WRKTMsg[] inMsgArray;
        inMsgArray = new INTFC_CPO_WRKTMsg[inMsgLst.size()];
        S21FastTBLAccessor.removePhysical(inMsgLst.toArray(inMsgArray));
        commit();
    }

    /**
     * Set Error Message
     * @param msg String
     * @param inParam INTFC_CPO_WRKTMsg
     * @return String
     */
    private String setErrorMessage(String msg, INTFC_CPO_WRKTMsg inParam) {

        StringBuilder sb = new StringBuilder();
        sb.append("INTFC_CPO_WRK Key:INTFC_CPO_ORD_NUM:").append(inParam.intfcCpoOrdNum.getValue());
        sb.append(" INTFC_CPO_ORD_DT:").append(inParam.intfcCpoOrdDt.getValue());

        return S21MessageFunc.clspGetMessage(msg, new String[] {sb.toString() });
    }

    /**
     * Set Error Message
     * @param msg String
     * @param rs ResultSet
     * @return String
     */
    private String setErrorMessage(String msg, ResultSet rs) {

        StringBuilder sb = new StringBuilder();

        try {

            sb.append("CPO_ORD_NUM:" + rs.getString(INTFC_CPO_ORD_NUM));
            sb.append(", CPO_ORD_DT:" + rs.getString(INTFC_CPO_ORD_DT));
            sb.append(", MDSE_CD:" + rs.getString(INTFC_PRT_MDSE_CD));
            sb.append(", SHPG_SVC_LVL_CD:" + rs.getString(TRD_PTNR_SHPG_METH_CD));

        } catch (SQLException e) {

            sqlExceptionHandler(e);
        }

        return S21MessageFunc.clspGetMessage(msg, new String[] {TBL_INTFC_CPO_WRK, CINC_GLBL_SHPG_METH_CD, sb.toString() });
    }

    /**
     * Get Varchar Constant
     * @param keyValue String
     * @param doCheck boolean
     * @return String
     */
    private String getVarCharConst(String keyValue, boolean doCheck) {

        String varCharConstVal = ZYPCodeDataUtil.getVarCharConstValue(keyValue, this.glblCmpyCd);

        if (doCheck && !ZYPCommonFunc.hasValue(varCharConstVal)) {

            throw new S21AbendException(NWAM0525E, new String[] {TBL_VAR_CHAR_CONST, keyValue });
        }

        return varCharConstVal;
    }

    /**
     * Create IF CPO Work Data
     * @param standardDt String
     * @param ssmStmntId String
     */
    private void createIntfcCpoWrk(String standardDt, String ssmStmntId) {

        PreparedStatement stmt = null;
        ResultSet rs = null;

        // set SSM query parameter
        Map<String, Object> queryParam = setQueryParam(standardDt, ssmStmntId);
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(this.commitCount);
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        try {

            // get parts order data from Return/Parts Usage Return
            stmt = this.ssmLLClient.createPreparedStatement(ssmStmntId, queryParam, execParam);
            rs = stmt.executeQuery();

            String preCpoOrdNum = null;
            String currentCpoOrdNum = null;
            String preCpoOrdDt = null;
            String currentCpoOrdDt = null;
            INTFC_CPO_WRKTMsg intfcCpoWrkTMsg = null;
            boolean isError = false;
            boolean existData = false;
            boolean isWarning = false;

            while (rs.next()) {

                // set Flag to commit/rollback last data
                if (!existData) {

                    existData = true;
                }

                // commit/rollback
                currentCpoOrdNum = rs.getString(INTFC_CPO_ORD_NUM);
                currentCpoOrdDt = rs.getString(INTFC_CPO_ORD_DT);

                if (ZYPCommonFunc.hasValue(currentCpoOrdDt) == false) {

                    currentCpoOrdDt = this.batDt;
                }

                if (preCpoOrdNum != null && (!currentCpoOrdDt.equals(preCpoOrdDt) || !currentCpoOrdNum.equals(preCpoOrdNum))) {

                    if (isError) {

                        rollback();
                        this.errorCount++;

                    } else {

                        commit();
                        this.successCount++;
                    }

                    isError = false;
                    isWarning = false;
                }

                if (!isError) {

                    if (!isWarning && checkInsertData(rs)) {
                        this.msgErrList.add(setErrorMessage(NWAM0312W, rs));
                        isWarning = true;
                        this.termCd = TERM_CD.WARNING_END;
                        StringBuilder subMsg = new StringBuilder("CPO_ORD_NUM:").append(rs.getString(INTFC_CPO_ORD_NUM));
                        subMsg.append(", CPO_ORD_DT:").append(rs.getString(INTFC_CPO_ORD_DT));
                        subMsg.append(", MDSE_CD:").append(rs.getString(INTFC_PRT_MDSE_CD));
                        subMsg.append(", SHPG_SVC_LVL_CD:").append(rs.getString(TRD_PTNR_SHPG_METH_CD));
                        S21InfoLogOutput.println(NWAM0312W, new String[] {TBL_TRD_PTNR_SHPG_X_REF, SHPG_SVC_LVL_CD, subMsg.toString() });
                    }

                    // create INTFC_CPO_WRK
                    intfcCpoWrkTMsg = setIntfcCpoWrkValue(rs, ssmStmntId);
                    EZDTBLAccessor.insert(intfcCpoWrkTMsg);

                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(intfcCpoWrkTMsg.getReturnCode())) {

                        this.msgErrList.add(setErrorMessage(NWAM0546E, intfcCpoWrkTMsg));
                        isError = true;
                    }
                }

                preCpoOrdNum = rs.getString(INTFC_CPO_ORD_NUM);
                preCpoOrdDt = rs.getString(INTFC_CPO_ORD_DT);
            }

            // commit or rollback last order data
            if (existData) {
                if (isError) {

                    rollback();
                    this.errorCount++;

                } else {

                    commit();
                    this.successCount++;
                }
            }

        } catch (SQLException e) {

            sqlExceptionHandler(e);

        } finally {

            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    /**
     * Set Query Parameter for each process
     * @param standardDt String
     * @param ssmStmntId String
     * @return Map<String, Object>
     */
    private Map<String, Object> setQueryParam(String standardDt, String ssmStmntId) {

        Map<String, Object> queryParam = null;

        // Stock Out
        if (STMT_GET_PARTS_ORD_DATA_CPO.equals(ssmStmntId)) {

            queryParam = setQueryParamForCpo(standardDt);
        }

        // WH Transfer
        if (STMT_GET_PARTS_ORD_DATA_CPO_WH_TRNSF.equals(ssmStmntId)) {

            queryParam = setQueryParamForCpoWhTrnsf(standardDt);
        }

        // Return/Parts Usage Return
        if (STMT_GET_PARTS_ORD_DATA_RMA.equals(ssmStmntId)) {

            queryParam = setQueryParamForRma(standardDt);
        }

        // WH Transfer/ Disposal/ ItemChange
        if (STMT_GET_PARTS_ORD_DATA_INVTY_ORD.equals(ssmStmntId)) {

            queryParam = setQueryParamForInvtyOrd(standardDt);
        }

        // Tech Req / Tech Return
        if (STMT_GET_PARTS_ORD_DATA_INVTY_ORD_TECH_TRNSF.equals(ssmStmntId)) {

            queryParam = setQueryParamForInvtyOrdTechTrnsf(standardDt);
        }

        // Parts Usage / Parts Usage For Return
        if (STMT_GET_PARTS_ORD_DATA_INVTY_TRX.equals(ssmStmntId)) {

            queryParam = setQueryParamForInvtyTrx(standardDt);
        }

        // Parts Usage / Parts Usage For Return (Reman)
        if (STMT_GET_PARTS_ORD_DATA_INVTY_TRX_RMN.equals(ssmStmntId)) {

            queryParam = setQueryParamForInvtyTrxRmn(standardDt);
        }

        // Reman Item Change
        if (STMT_GET_PARTS_ORD_DATA_INVTY_TRX_RMN_ITM_CHG.equals(ssmStmntId)) {

            queryParam = setQueryParamForInvtyTrxRmnItmChg(standardDt);
        }

        // Work Order
        if (STMT_GET_PARTS_ORD_DATA_WRK_ORD.equals(ssmStmntId)) {

            queryParam = setQueryParamForWrkOrd(standardDt);
        }

        // Common
        // QC#30964 Add SCUBE_EXCL_MDSE_CD_LIST.
        String orgExclMdseCommaList = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_KEY_SCUBE_EXCL_MDSE_CD_LIST, this.glblCmpyCd);

        if (ZYPCommonFunc.hasValue(orgExclMdseCommaList)) {
            String[] exclMdseList = orgExclMdseCommaList.split(",");

            queryParam.put("exclMdseCdList", exclMdseList);
        }

        // Add Start 2020/02/03 QC#55572
        String scubeExclSwhCdList = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_KEY_SCUBE_EXCL_SWH_CD_LIST, this.glblCmpyCd);

        if (ZYPCommonFunc.hasValue(scubeExclSwhCdList)) {
            String[] exclSwhCdList = scubeExclSwhCdList.split(",");

            queryParam.put("exclSwhCdList", exclSwhCdList);
        }
        // Add End 2020/02/03 QC#55572

        return queryParam;
    }

    /**
     * Set Query Parameter for CPO
     * @param standardDt String
     * @return Map<String, Object>
     */
    private Map<String, Object> setQueryParamForCpo(String standardDt) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(BIND_GLBL_CMPY_CD, this.glblCmpyCd);
        queryParam.put(BIND_CPO_ORD_TS, standardDt);
        queryParam.put(BIND_EXEC_LOC_CD_LIST, this.invtyLocCdList);
        queryParam.put(BIND_VND_SYS_TP_CD, this.scubeVndSysTpCdList);
        queryParam.put(BIND_TECHNICIAN, LOC_TP.TECHNICIAN);
        queryParam.put(BIND_DS_COND_CONST_GRP_ID_CUSA_VND_CD, KEY_SCUBE_IF_CUSA_VND_CD);
        queryParam.put(BIND_SCUBE_IF_CINC_VND_CD, this.specifiedVndCd);
        queryParam.put(BIND_CARR_CD_AST, ASTERISK);
        queryParam.put(BIND_FRT_COND_CD_AST, ASTERISK);
        queryParam.put(BIND_RTL_WH_CD_AST, ASTERISK);
        queryParam.put(BIND_WH_OWNR_CD_AST, ASTERISK);
        queryParam.put(BIND_LOC_TP_CD_AST, ASTERISK);
        queryParam.put(BIND_CMPY_INVTY_FLG, ZYPConstant.FLG_ON_Y);
        queryParam.put(BIND_INVTY_CTRL_FLG, ZYPConstant.FLG_ON_Y);
        if (this.invtyLocCdList != null){
            queryParam.put(BIND_INVTY_LOC_CD_LIST_SIZE, this.invtyLocCdList.size());
        }
        queryParam.put(BIND_EFF_FROM_DT_DEFAULT, EFF_FROM_DT_DEF);
        queryParam.put(BIND_IDX_1, VAL_1);
        queryParam.put(BIND_TRD_PTNR_SHPG_METH_CD_OTHER, CINC_GLBL_SHPG_METH_CD_OTHER);
        queryParam.put(BIND_DS_COND_CONST_GRP_ID_DS_ORD_TP, TP_SCUBE_IF_CUSA_VND_CD);
        queryParam.put(BIND_SUBMIT_FLG, ZYPConstant.FLG_ON_Y);
        queryParam.put(BIND_CPO_DTL_CANC_FLG_N, ZYPConstant.FLG_OFF_N);
        queryParam.put(BIND_CPO_DTL_CANC_FLG_Y, ZYPConstant.FLG_ON_Y);
        queryParam.put(BIND_NUM_ONE, NUM_ONE);

        return queryParam;
    }

    /**
     * Set Query Parameter for CPO(WH Transfer)
     * @param standardDt String
     * @return Map<String, Object>
     */
    private Map<String, Object> setQueryParamForCpoWhTrnsf(String standardDt) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(BIND_GLBL_CMPY_CD, this.glblCmpyCd);
        queryParam.put(BIND_CPO_ORD_TS, standardDt);
        queryParam.put(BIND_CPO_CANC_DT, standardDt);
        queryParam.put(BIND_SUBMIT_FLG, ZYPConstant.FLG_ON_Y);
        queryParam.put(BIND_VND_CD, this.specifiedVndCd);
        queryParam.put(BIND_CARR_CD, ASTERISK);
        queryParam.put(BIND_PRT_INCL_TECH_INVTY_CINC_FLG, this.prtInclTechInvtyCincFlg);
        queryParam.put(BIND_EFF_FROM_DT_DEFAULT, EFF_FROM_DT_DEF);
        queryParam.put(BIND_RTL_WH_CD_AST, ASTERISK);
        queryParam.put(BIND_WH_OWNR_CD_AST, ASTERISK);
        queryParam.put(BIND_LOC_TP_CD_AST, ASTERISK);
        queryParam.put(BIND_FRT_COND_CD, ASTERISK);
        queryParam.put(BIND_CMPY_INVTY_FLG, ZYPConstant.FLG_ON_Y);
        queryParam.put(BIND_FROM_RTL_WH_CD_AST, ASTERISK);
        queryParam.put(BIND_FROM_WH_OWNR_CD_AST, ASTERISK);
        queryParam.put(BIND_FROM_LOC_TP_CD_AST, ASTERISK);
        queryParam.put(BIND_TO_RTL_WH_CD_AST, ASTERISK);
        queryParam.put(BIND_TO_WH_OWNR_CD_AST, ASTERISK);
        queryParam.put(BIND_TO_LOC_TP_CD_AST, ASTERISK);
        queryParam.put(BIND_NUM_ONE, NUM_ONE);
        queryParam.put(BIND_DS_COND_CONST_GRP_ID, KEY_SCUBE_IF_CUSA_VND_CD);
        queryParam.put(BIND_SCUBE_WHT_DS_ORD_TP, TP_SCUBE_IF_CUSA_VND_CD);
        queryParam.put(BIND_TRD_PTNR_SHPG_METH_CD_OTHER, CINC_GLBL_SHPG_METH_CD_OTHER);
        queryParam.put(BIND_VND_CD_CINC, this.specifiedVndCd);
        queryParam.put(BIND_CPO_DTL_CANC_FLG_Y, ZYPConstant.FLG_ON_Y);
        queryParam.put(BIND_CPO_DTL_CANC_FLG_N, ZYPConstant.FLG_OFF_N);
        queryParam.put(BIND_INVTY_CTRL_FLG, ZYPConstant.FLG_ON_Y);
        queryParam.put(BIND_EXEC_LOC_CD_LIST, this.invtyLocCdList);

        if (ZYPConstant.FLG_OFF_N.equals(this.prtInclTechInvtyCincFlg)) {

            queryParam.put(BIND_TECHNICIAN, LOC_TP.TECHNICIAN);

        } else {

            queryParam.put(BIND_TECHNICIAN, null);
        }

        return queryParam;
    }

    /**
     * Set Query Parameter for Work Order
     * @param standardDt String
     * @return Map<String, Object>
     */
    private Map<String, Object> setQueryParamForWrkOrd(String standardDt) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(BIND_GLBL_CMPY_CD, this.glblCmpyCd);
        queryParam.put(BIND_TECHNICIAN, LOC_TP.TECHNICIAN);

        queryParam.put(BIND_WRL_ORD_STS_CD_SAVED, WRK_ORD_STS.SAVED);
        queryParam.put(BIND_WRL_ORD_STS_CD_CANCEL, WRK_ORD_STS.CANCELLED);

        queryParam.put(BIND_EFF_FROM_DT_DEFAULT, EFF_FROM_DT_DEF);
        queryParam.put(BIND_IDX_1, VAL_1);
        queryParam.put(BIND_TRD_PTNR_SHPG_METH_CD_OTHER, CINC_GLBL_SHPG_METH_CD_OTHER);
        queryParam.put(BIND_DS_COND_CONST_GRP_ID_CUSA_VND_CD, KEY_SCUBE_IF_CUSA_VND_CD);
        queryParam.put(BIND_WH_OWNR_CD_AST, ASTERISK);
        queryParam.put(BIND_LOC_TP_CD_AST, ASTERISK);
        queryParam.put(BIND_RTL_WH_CD_AST, ASTERISK);
        queryParam.put(BIND_CARR_CD_AST, ASTERISK);
        queryParam.put(BIND_FRT_COND_CD_AST, ASTERISK);

        queryParam.put(BIND_CPO_ORD_TS, standardDt);
        queryParam.put(BIND_CMPY_INVTY_FLG, ZYPConstant.FLG_ON_Y);

        queryParam.put(BIND_SCUBE_IF_CINC_VND_CD, this.specifiedVndCd);

        queryParam.put(BIND_EXEC_LOC_CD_LIST, this.invtyLocCdList);
        if (this.invtyLocCdList != null) {
            queryParam.put(BIND_INVTY_LOC_CD_LIST_SIZE, this.invtyLocCdList.size());
        }
        queryParam.put(BIND_VND_SYS_TP_CD, this.scubeVndSysTpCdList);
        queryParam.put(BIND_NUM_ONE, NUM_ONE);

        // QC#31174 Update.Support Un-Kit
        queryParam.put(BIND_DS_WRK_ORD_TP_UN_KIT, DS_WRK_ORD_TP.UN_KIT);

        return queryParam;
    }

    /**
     * Set Query Parameter for Inventory Transaction
     * @param standardDt String
     * @return Map<String, Object>
     */
    private Map<String, Object> setQueryParamForInvtyTrx(String standardDt) {

        Map<String, Object> queryParam = new HashMap<String, Object>();

        queryParam.put(BIND_GLBL_CMPY_CD, this.glblCmpyCd);
        queryParam.put(BIND_EFF_FROM_DT_DEFAULT, EFF_FROM_DT_DEF);

        queryParam.put(BIND_VND_SYS_TP_CD, this.scubeVndSysTpCdList);
        queryParam.put(BIND_DS_COND_CONST_GRP_ID_CUSA_VND_CD, KEY_SCUBE_IF_CUSA_VND_CD);
        queryParam.put(BIND_WH_OWNR_CD_AST, ASTERISK);
        queryParam.put(BIND_LOC_TP_CD_AST, ASTERISK);
        queryParam.put(BIND_RTL_WH_CD_AST, ASTERISK);
        queryParam.put(BIND_INVTY_TRX_CD_PRTS_USG, TRX.PARTS_USAGE);
        queryParam.put(BIND_INVTY_TRX_RSN_CD_PRTS_USG, TRX_RSN.PURCHASE_STOCK_IN);
        queryParam.put(BIND_INVTY_TRX_RSN_CD_PRTS_USG_RTRN, TRX_RSN.EXPORT_VENDOR_RETURN);
        queryParam.put(BIND_CPO_ORD_TS, standardDt);
        queryParam.put(BIND_EXEC_LOC_CD_LIST, this.invtyLocCdList);
        queryParam.put(BIND_TECHNICIAN, LOC_TP.TECHNICIAN);
        queryParam.put(BIND_CMPY_INVTY_FLG, ZYPConstant.FLG_ON_Y);
        queryParam.put(BIND_IDX_1, VAL_1);
        queryParam.put(BIND_NUM_ONE, NUM_ONE);
        queryParam.put(BIND_TRD_PTNR_SHPG_METH_CD_OTHER, CINC_GLBL_SHPG_METH_CD_OTHER);

        return queryParam;
    }

    /**
     * Set Query Parameter for Inventory Transaction (Reman)
     * @param standardDt String
     * @return Map<String, Object>
     */
    private Map<String, Object> setQueryParamForInvtyTrxRmn(String standardDt) {

        Map<String, Object> queryParam = new HashMap<String, Object>();

        queryParam.put(BIND_GLBL_CMPY_CD, this.glblCmpyCd);

        queryParam.put(BIND_EFF_FROM_DT_DEFAULT, EFF_FROM_DT_DEF);
        queryParam.put(BIND_IDX_1, VAL_1);
        queryParam.put(BIND_TRD_PTNR_SHPG_METH_CD_OTHER, CINC_GLBL_SHPG_METH_CD_OTHER);
        queryParam.put(BIND_DS_COND_CONST_GRP_ID_CUSA_VND_CD, KEY_SCUBE_IF_CUSA_VND_CD);

        queryParam.put(BIND_FROM_WH_OWNR_CD_AST, ASTERISK);
        queryParam.put(BIND_FROM_LOC_TP_CD_AST, ASTERISK);
        queryParam.put(BIND_TO_WH_OWNR_CD_AST, ASTERISK);
        queryParam.put(BIND_TO_LOC_TP_CD_AST, ASTERISK);
        queryParam.put(BIND_TO_LOC_TP_CD_AST, ASTERISK);
        queryParam.put(BIND_WH_OWNR_CD_AST, ASTERISK);
        queryParam.put(BIND_LOC_TP_CD_AST, ASTERISK);
        queryParam.put(BIND_RTL_WH_CD_AST, ASTERISK);
        queryParam.put(BIND_TO_RTL_WH_CD_AST, ASTERISK);
        queryParam.put(BIND_FROM_RTL_WH_CD_AST, ASTERISK);

        queryParam.put(BIND_INVTY_TRX_CD_RMN, TRX.REMANUFACTURING);

        queryParam.put(BIND_CPO_ORD_TS, standardDt);
        queryParam.put(BIND_CMPY_INVTY_FLG, ZYPConstant.FLG_ON_Y);
        queryParam.put(BIND_INVTY_TRX_RSN_CD_PRTS_USG_FOR_RMN, TRX_RSN.PURCHASE_STOCK_IN);
        queryParam.put(BIND_INVTY_TRX_RSN_CD_PRTS_USG_RTRN_FOR_RMN, TRX_RSN.EXPENSE_STOCK_IN);

        queryParam.put(BIND_EXEC_LOC_CD_LIST, this.invtyLocCdList);
        if (this.invtyLocCdList != null) {
            queryParam.put(BIND_INVTY_LOC_CD_LIST_SIZE, this.invtyLocCdList.size());
        }
        queryParam.put(BIND_VND_SYS_TP_CD, this.scubeVndSysTpCdList);
        queryParam.put(BIND_NUM_ONE, NUM_ONE);

        return queryParam;
    }

    /**
     * Set Query Parameter for Inventory Transaction (Reman Item
     * Change)
     * @param standardDt String
     * @return Map<String, Object>
     */
    private Map<String, Object> setQueryParamForInvtyTrxRmnItmChg(String standardDt) {

        Map<String, Object> queryParam = new HashMap<String, Object>();

        queryParam.put(BIND_GLBL_CMPY_CD, this.glblCmpyCd);
        queryParam.put(BIND_EFF_FROM_DT_DEFAULT, EFF_FROM_DT_DEF);
        queryParam.put(BIND_IDX_1, VAL_1);
        queryParam.put(BIND_DS_COND_CONST_GRP_ID_CUSA_VND_CD, KEY_SCUBE_IF_CUSA_VND_CD);
        queryParam.put(BIND_WH_OWNR_CD_AST, ASTERISK);
        queryParam.put(BIND_LOC_TP_CD_AST, ASTERISK);
        queryParam.put(BIND_RTL_WH_CD_AST, ASTERISK);

        queryParam.put(BIND_INVTY_TRX_CD_RMN, TRX.REMANUFACTURING);
        // QC#31174 Update.
        queryParam.put(BIND_INVTY_TRX_RSN_CD_RMN_ITM_CHG_STKOUT, TRX_RSN.REMAN_ITEM_CHANGE_STOCK_OUT_ACSRY);
        queryParam.put(BIND_INVTY_TRX_RSN_CD_RMN_ITM_CHG_STKIN, TRX_RSN.REMAN_ITEM_CHANGE_STOCK_IN_ACSRY);
        queryParam.put(BIND_CPO_ORD_TS, standardDt);
        queryParam.put(BIND_TECHNICIAN, LOC_TP.TECHNICIAN);
        queryParam.put(BIND_CMPY_INVTY_FLG, ZYPConstant.FLG_ON_Y);

        queryParam.put(BIND_EXEC_LOC_CD_LIST, this.invtyLocCdList);
        if (this.invtyLocCdList != null) {
            queryParam.put(BIND_INVTY_LOC_CD_LIST_SIZE, this.invtyLocCdList.size());
        }
        queryParam.put(BIND_VND_SYS_TP_CD, this.scubeVndSysTpCdList);
        queryParam.put(BIND_NUM_ONE, NUM_ONE);
        queryParam.put(BIND_TRD_PTNR_SHPG_METH_CD_OTHER, CINC_GLBL_SHPG_METH_CD_OTHER);

        return queryParam;
    }

    /**
     * Set Query Parameter for Inventory Order
     * @param standardDt String
     * @return Map<String, Object>
     */
    private Map<String, Object> setQueryParamForInvtyOrd(String standardDt) {

        Map<String, Object> queryParam = new HashMap<String, Object>();

        queryParam.put(BIND_GLBL_CMPY_CD, this.glblCmpyCd);
        queryParam.put(BIND_EFF_FROM_DT_DEFAULT, EFF_FROM_DT_DEF);
        queryParam.put(BIND_IDX_1, VAL_1);
        queryParam.put(BIND_DS_COND_CONST_GRP_ID_CUSA_VND_CD, KEY_SCUBE_IF_CUSA_VND_CD);
        queryParam.put(BIND_TRD_PTNR_SHPG_METH_CD_OTHER, CINC_GLBL_SHPG_METH_CD_OTHER);
        queryParam.put(BIND_VND_CD_CINC, this.specifiedVndCd);

        queryParam.put(BIND_CARR_CD_AST, ASTERISK);
        queryParam.put(BIND_FRT_COND_CD_AST, ASTERISK);
        queryParam.put(BIND_WH_OWNR_CD_AST, ASTERISK);
        queryParam.put(BIND_LOC_TP_CD_AST, ASTERISK);
        queryParam.put(BIND_RTL_WH_CD_AST, ASTERISK);

        queryParam.put(BIND_INV_ORD_TP_DISPOSAL, INVTY_ORD_TP.DISPOSAL);
        queryParam.put(BIND_INV_ORD_TP_WRITE_OFF, INVTY_ORD_TP.WRITE_OFF);
        queryParam.put(BIND_INV_ORD_TP_ITEM_CHANGE, INVTY_ORD_TP.ITEM_CHANGE);
        queryParam.put(BIND_INV_ORD_TP_SUB_CONTRACT, INVTY_ORD_TP.SUBCONTRACT);
        queryParam.put(BIND_INV_ORD_TP_REFURBISH_OUT, INVTY_ORD_TP.REFURBISH_OUT);
        queryParam.put(BIND_CPO_ORD_TS, standardDt);
        queryParam.put(BIND_CMPY_INVTY_FLG, ZYPConstant.FLG_ON_Y);
        queryParam.put(BIND_INVTY_CTRL_FLG, ZYPConstant.FLG_ON_Y);
        queryParam.put(BIND_EXEC_LOC_CD_LIST, this.invtyLocCdList);
        if (this.invtyLocCdList != null) {
            queryParam.put(BIND_INVTY_LOC_CD_LIST_SIZE, this.invtyLocCdList.size());
        }
        queryParam.put(BIND_VND_SYS_TP_CD, this.scubeVndSysTpCdList);
        queryParam.put(BIND_NUM_ONE, NUM_ONE);

        if (ZYPConstant.FLG_OFF_N.equals(this.prtInclTechInvtyCincFlg)) {
            queryParam.put(BIND_TECHNICIAN, LOC_TP.TECHNICIAN);
        } else {
            queryParam.put(BIND_TECHNICIAN, null);
        }

        return queryParam;
    }

    private Map<String, Object> setQueryParamForInvtyOrdTechTrnsf(String standardDt) {

        Map<String, Object> queryParam = new HashMap<String, Object>();

        queryParam.put(BIND_GLBL_CMPY_CD, this.glblCmpyCd);
        queryParam.put(BIND_EFF_FROM_DT_DEFAULT, EFF_FROM_DT_DEF);
        queryParam.put(BIND_VND_SYS_TP_CD, this.scubeVndSysTpCdList);
        queryParam.put(BIND_IDX_1, VAL_1);
        queryParam.put(BIND_DS_COND_CONST_GRP_ID_CUSA_VND_CD, KEY_SCUBE_IF_CUSA_VND_CD);
        queryParam.put(BIND_TRD_PTNR_SHPG_METH_CD_OTHER, CINC_GLBL_SHPG_METH_CD_OTHER);
        queryParam.put(BIND_VND_CD_CINC, this.specifiedVndCd);
        queryParam.put(BIND_VND_CD_AST, ASTERISK);
        queryParam.put(BIND_CARR_CD_AST, ASTERISK);
        queryParam.put(BIND_FRT_COND_CD_AST, ASTERISK);

        queryParam.put(BIND_EXEC_LOC_CD_LIST, this.invtyLocCdList);
        queryParam.put(BIND_DC_TRANSFER, INVTY_ORD_TP.DC_TRANSFER);
        queryParam.put(BIND_CPO_ORD_TS, standardDt);
        queryParam.put(BIND_LOC_TP_CD_AST, ASTERISK);
        queryParam.put(BIND_RTL_WH_CD_AST, ASTERISK);
        queryParam.put(BIND_WH_OWNR_CD_AST, ASTERISK);
        queryParam.put(BIND_CMPY_INVTY_FLG, ZYPConstant.FLG_ON_Y);
        queryParam.put(BIND_INVTY_CTRL_FLG, ZYPConstant.FLG_ON_Y);
        queryParam.put(BIND_NUM_ONE, NUM_ONE);
        queryParam.put(BIND_FROM_RTL_WH_CD_AST, ASTERISK);
        queryParam.put(BIND_FROM_WH_OWNR_CD_AST, ASTERISK);
        queryParam.put(BIND_FROM_LOC_TP_CD_AST, ASTERISK);
        queryParam.put(BIND_TO_RTL_WH_CD_AST, ASTERISK);
        queryParam.put(BIND_TO_WH_OWNR_CD_AST, ASTERISK);
        queryParam.put(BIND_TO_LOC_TP_CD_AST, ASTERISK);
        queryParam.put(BIND_TECHNICIAN, LOC_TP.TECHNICIAN);

        return queryParam;
    }

    /**
     * Set Query Parameter for Return
     * @param standardDt String
     * @return Map<String, Object>
     */
    private Map<String, Object> setQueryParamForRma(String standardDt) {

        Map<String, Object> queryParam = new HashMap<String, Object>();

        queryParam.put(BIND_GLBL_CMPY_CD, this.glblCmpyCd);

        queryParam.put(BIND_EFF_FROM_DT_DEFAULT, EFF_FROM_DT_DEF);
        queryParam.put(BIND_DS_COND_CONST_GRP_ID_CUSA_VND_CD, KEY_SCUBE_IF_CUSA_VND_CD);

        queryParam.put(BIND_WH_OWNR_CD_AST, ASTERISK);
        queryParam.put(BIND_LOC_TP_CD_AST, ASTERISK);
        queryParam.put(BIND_RTL_WH_CD_AST, ASTERISK);
        queryParam.put(BIND_DS_COND_CONST_GRP_ID_DS_ORD_TP, TP_SCUBE_IF_CUSA_VND_CD);
        queryParam.put(BIND_CPO_ORD_TS, standardDt);
        queryParam.put(BIND_SUBMIT_FLG, ZYPConstant.FLG_ON_Y);
        queryParam.put(BIND_CPO_DTL_CANC_FLG_Y, ZYPConstant.FLG_ON_Y);
        queryParam.put(BIND_CPO_DTL_CANC_FLG_N, ZYPConstant.FLG_OFF_N);
        queryParam.put(BIND_CMPY_INVTY_FLG, ZYPConstant.FLG_ON_Y);
        queryParam.put(BIND_INVTY_CTRL_FLG, ZYPConstant.FLG_ON_Y);

        queryParam.put(BIND_EXEC_LOC_CD_LIST, this.invtyLocCdList);
        if (this.invtyLocCdList != null) {
            queryParam.put(BIND_INVTY_LOC_CD_LIST_SIZE, this.invtyLocCdList.size());
        }
        queryParam.put(BIND_VND_SYS_TP_CD, this.scubeVndSysTpCdList);
        queryParam.put(BIND_IDX_1, VAL_1);
        queryParam.put(BIND_NUM_ONE, NUM_ONE);
        queryParam.put(BIND_TRD_PTNR_SHPG_METH_CD_OTHER, CINC_GLBL_SHPG_METH_CD_OTHER);

        if (ZYPConstant.FLG_OFF_N.equals(this.prtInclTechInvtyCincFlg)) {
            queryParam.put(BIND_TECHNICIAN, LOC_TP.TECHNICIAN);
        } else {
            queryParam.put(BIND_TECHNICIAN, null);
        }

        return queryParam;
    }

    /**
     * Set Value to TMsg for each process
     * @param rs ResultSet
     * @param ssmStmntId String
     * @return INTFC_CPO_WRKTMsg
     */
    private INTFC_CPO_WRKTMsg setIntfcCpoWrkValue(ResultSet rs, String ssmStmntId) {

        INTFC_CPO_WRKTMsg resultMsg = new INTFC_CPO_WRKTMsg();

        try {

            // Call Convert Parts MDSE Code
            NMXC104001ConvertPartsMdseCdBean convMdseCdBean = new NMXC104001ConvertPartsMdseCdBean();
            // QC#30963 Add glblCmpyCd.
            convMdseCdBean.setGlblCmpyCd(this.glblCmpyCd);
            convMdseCdBean.setMode(NMXC104001ConvertPartsMdseCd.MODE_MDSE);
            convMdseCdBean.setMdseCd(rs.getString(INTFC_PRT_MDSE_CD));
            convMdseCdBean.setCusaVndCd(rs.getString(CUSA_VND_CD));
            NMXC104001ConvertPartsMdseCd.convertPartsMdseCd(convMdseCdBean);

            // Stock Out / WH Transfer (CPO)
            if (STMT_GET_PARTS_ORD_DATA_CPO.equals(ssmStmntId) || STMT_GET_PARTS_ORD_DATA_CPO_WH_TRNSF.equals(ssmStmntId)) {

                setWrkTMsgForCpo(rs, convMdseCdBean, resultMsg);
            }

            // Return
            if (STMT_GET_PARTS_ORD_DATA_RMA.equals(ssmStmntId)) {

                setWrkTMsgForRma(rs, convMdseCdBean, resultMsg);
            }

            // WH Transfer/ Disposal/ ItemChange
            if (STMT_GET_PARTS_ORD_DATA_INVTY_ORD.equals(ssmStmntId) || STMT_GET_PARTS_ORD_DATA_INVTY_ORD_TECH_TRNSF.equals(ssmStmntId)) {

                setWrkTMsgForInvtyOrd(rs, convMdseCdBean, resultMsg);
            }

            // Parts Usage
            if (STMT_GET_PARTS_ORD_DATA_INVTY_TRX.equals(ssmStmntId)) {

                setWrkTMsgForInvtyTrx(rs, convMdseCdBean, resultMsg);
            }
            // Parts Usage Reman
            if (STMT_GET_PARTS_ORD_DATA_INVTY_TRX_RMN.equals(ssmStmntId)) {

                setWrkTMsgForInvtyTrxRmn(rs, convMdseCdBean, resultMsg);
            }
            // Reman Item Change
            if (STMT_GET_PARTS_ORD_DATA_INVTY_TRX_RMN_ITM_CHG.equals(ssmStmntId)) {

                setWrkTMsgForInvtyTrxRmnItmClg(rs, convMdseCdBean, resultMsg);
            }

            // Work Order
            if (STMT_GET_PARTS_ORD_DATA_WRK_ORD.equals(ssmStmntId)) {

                setWrkTMsgForWrkOrd(rs, convMdseCdBean, resultMsg);
            }

        } catch (SQLException e) {

            sqlExceptionHandler(e);
        }

        // QC#26966 Update.
        if (resultMsg != null) {
            ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblOrdCatgCd //
                    , setCincGlblOrdCatgCd(resultMsg.cincGlblWhCd.getValue()//
                            , resultMsg.cincShipToGlblCmpyCd.getValue()//
                            , resultMsg.cincRcvGlblWhCd.getValue()//
                            , resultMsg.cincGlblOrdCatgCd.getValue()));
        }

        return resultMsg;
    }

    /**
     * Set Value to TMsg for CPO
     * @param rs ResultSet
     * @param convMdseCdBean NMXC104001ConvertPartsMdseCdBean
     * @param resultMsg INTFC_CPO_WRKTMsg
     * @throws SQLException
     */
    private void setWrkTMsgForCpo(ResultSet rs, NMXC104001ConvertPartsMdseCdBean convMdseCdBean, INTFC_CPO_WRKTMsg resultMsg) throws SQLException {

        String cpoCancFlg = rs.getString(INTFC_CATG_TXT);

        // Stock Out - New
        if (ZYPCommonFunc.hasValue(cpoCancFlg) && "NEW".equals(cpoCancFlg)) {

            ZYPEZDItemValueSetter.setValue(resultMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblWhCd, rs.getString(CINC_GLBL_WH_CD));
            ZYPEZDItemValueSetter.setValue(resultMsg.intfcCpoOrdDt, editDigit(rs.getString(INTFC_CPO_ORD_DT), this.digitIntfcCpoOrdDt));
            ZYPEZDItemValueSetter.setValue(resultMsg.intfcCpoOrdNum, rs.getString(INTFC_CPO_ORD_NUM));
            ZYPEZDItemValueSetter.setValue(resultMsg.intfcPrtMdseCd, convMdseCdBean.getXtrnlSysPrtCd());
            ZYPEZDItemValueSetter.setValue(resultMsg.prtSizeTxt, convMdseCdBean.getXtrnlSysSize());
            ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblShpgMethCd, rs.getString(TRD_PTNR_SHPG_METH_CD));
            ZYPEZDItemValueSetter.setValue(resultMsg.cincShipToGlblCmpyCd, getCincShipToGlblCmpyCdReturn(rs));
            ZYPEZDItemValueSetter.setValue(resultMsg.cincRcvGlblWhCd, "");
            ZYPEZDItemValueSetter.setValue(resultMsg.cincBillToGlblCmpyCd, getCincBillToGlblCmpyCdReturn(rs));
            ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblCmpyCatgCd, RCPT_CMPY_CLS.OTHER);
            ZYPEZDItemValueSetter.setValue(resultMsg.intfcSellToCustCd, editDigit(rs.getString(INTFC_SELL_TO_CUST_CD), this.digitIntfcSellToCustCd));
            ZYPEZDItemValueSetter.setValue(resultMsg.intfcOrdQtySgnTxt, getOrdQtySgnTxt(rs));
            ZYPEZDItemValueSetter.setValue(resultMsg.intfcOrdQty, getAbsOrdQty(rs));
            String intfcRsdDt = getIntfcRsdDtStockOut(rs);
            ZYPEZDItemValueSetter.setValue(resultMsg.intfcRsdDt, intfcRsdDt);
            ZYPEZDItemValueSetter.setValue(resultMsg.intfcExpdShipDt, getIntfcExpdShipDtStockOut(rs));
            ZYPEZDItemValueSetter.setValue(resultMsg.intfcRddDt, getIntfcRddDtStockOut(rs, intfcRsdDt));
            ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblOrdCatgCd, getCincGlblOrdCatgCd(rs));
            ZYPEZDItemValueSetter.setValue(resultMsg.prtChrgInd, this.prtChrgIndSales);
            ZYPEZDItemValueSetter.setValue(resultMsg.prtEmerOrdInd, this.prtEmerOrdInd);
            ZYPEZDItemValueSetter.setValue(resultMsg.intfcCratDt, this.batDt);
        }

        // Stock Out - Cancel
        if (ZYPCommonFunc.hasValue(cpoCancFlg) && "CANCEL".equals(cpoCancFlg)) {

            ZYPEZDItemValueSetter.setValue(resultMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblWhCd, rs.getString(CINC_GLBL_WH_CD));
            ZYPEZDItemValueSetter.setValue(resultMsg.intfcCpoOrdDt, editDigit(rs.getString(INTFC_CPO_ORD_DT), this.digitIntfcCpoOrdDt));
            ZYPEZDItemValueSetter.setValue(resultMsg.intfcCpoOrdNum, rs.getString(INTFC_CPO_ORD_NUM));
            ZYPEZDItemValueSetter.setValue(resultMsg.intfcPrtMdseCd, convMdseCdBean.getXtrnlSysPrtCd());
            ZYPEZDItemValueSetter.setValue(resultMsg.prtSizeTxt, convMdseCdBean.getXtrnlSysSize());
            ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblShpgMethCd, rs.getString(TRD_PTNR_SHPG_METH_CD));
            ZYPEZDItemValueSetter.setValue(resultMsg.cincShipToGlblCmpyCd, getCincShipToGlblCmpyCdReturn(rs));
            ZYPEZDItemValueSetter.setValue(resultMsg.cincRcvGlblWhCd, "");
            ZYPEZDItemValueSetter.setValue(resultMsg.cincBillToGlblCmpyCd, getCincBillToGlblCmpyCdReturn(rs));
            ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblCmpyCatgCd, RCPT_CMPY_CLS.OTHER);
            ZYPEZDItemValueSetter.setValue(resultMsg.intfcSellToCustCd, editDigit(rs.getString(INTFC_SELL_TO_CUST_CD), this.digitIntfcSellToCustCd));
            ZYPEZDItemValueSetter.setValue(resultMsg.intfcOrdQtySgnTxt, VALUE_INTFC_QTY_SGN_TXT_PLUS);
            ZYPEZDItemValueSetter.setValue(resultMsg.intfcOrdQty, BigDecimal.ZERO);
            String intfcRsdDt = getIntfcRsdDtStockOut(rs);
            ZYPEZDItemValueSetter.setValue(resultMsg.intfcRsdDt, intfcRsdDt);
            ZYPEZDItemValueSetter.setValue(resultMsg.intfcExpdShipDt, getIntfcExpdShipDtStockOut(rs));
            ZYPEZDItemValueSetter.setValue(resultMsg.intfcRddDt, getIntfcRddDtStockOut(rs, intfcRsdDt));
            ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblOrdCatgCd, getCincGlblOrdCatgCd(rs));
            ZYPEZDItemValueSetter.setValue(resultMsg.prtChrgInd, this.prtChrgIndSales);
            ZYPEZDItemValueSetter.setValue(resultMsg.prtEmerOrdInd, this.prtEmerOrdInd);
            ZYPEZDItemValueSetter.setValue(resultMsg.intfcCratDt, this.batDt);
        }

        // WH Transfer - New
        if (ZYPCommonFunc.hasValue(cpoCancFlg) && "WHT_NEW".equals(cpoCancFlg)) {

            String[] cincGlblCmpyCatgCdList = null;
            if (ZYPCommonFunc.hasValue(rs.getString(CINC_GLBL_CMPY_CATG_CD))) {
                cincGlblCmpyCatgCdList = rs.getString(CINC_GLBL_CMPY_CATG_CD).split(",");
            }

            ZYPEZDItemValueSetter.setValue(resultMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblWhCd, rs.getString(CINC_GLBL_WH_CD));
            ZYPEZDItemValueSetter.setValue(resultMsg.intfcCpoOrdDt, editDigit(rs.getString(INTFC_CPO_ORD_DT), this.digitIntfcCpoOrdDt));
            ZYPEZDItemValueSetter.setValue(resultMsg.intfcCpoOrdNum, rs.getString(INTFC_CPO_ORD_NUM));
            ZYPEZDItemValueSetter.setValue(resultMsg.intfcPrtMdseCd, convMdseCdBean.getXtrnlSysPrtCd());
            ZYPEZDItemValueSetter.setValue(resultMsg.prtSizeTxt, convMdseCdBean.getXtrnlSysSize());
            ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblShpgMethCd, rs.getString(TRD_PTNR_SHPG_METH_CD));
            ZYPEZDItemValueSetter.setValue(resultMsg.cincShipToGlblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(resultMsg.cincRcvGlblWhCd, rs.getString(CINC_RCV_GLBL_WH_CD));
            ZYPEZDItemValueSetter.setValue(resultMsg.cincBillToGlblCmpyCd, this.glblCmpyCd);

            ZYPEZDItemValueSetter.setValue(resultMsg.intfcSellToCustCd, editDigit(rs.getString(INTFC_SELL_TO_CUST_CD), this.digitIntfcSellToCustCd));
            ZYPEZDItemValueSetter.setValue(resultMsg.intfcOrdQtySgnTxt, getOrdQtySgnTxt(rs));
            ZYPEZDItemValueSetter.setValue(resultMsg.intfcOrdQty, getAbsOrdQty(rs));
            String intfcRsdDt = getIntfcRsdDtStockOut(rs);
            ZYPEZDItemValueSetter.setValue(resultMsg.intfcRsdDt, intfcRsdDt);
            ZYPEZDItemValueSetter.setValue(resultMsg.intfcExpdShipDt, getIntfcExpdShipDtStockOut(rs));
            ZYPEZDItemValueSetter.setValue(resultMsg.intfcRddDt, getIntfcRddDtStockOut(rs, intfcRsdDt));

            if (cincGlblCmpyCatgCdList != null && cincGlblCmpyCatgCdList.length > 0) {
                ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblCmpyCatgCd, cincGlblCmpyCatgCdList[0]);
                ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblOrdCatgCd, cincGlblCmpyCatgCdList[1]);
            }

            ZYPEZDItemValueSetter.setValue(resultMsg.prtChrgInd, this.prtChrgIndSales);
            ZYPEZDItemValueSetter.setValue(resultMsg.prtEmerOrdInd, this.prtEmerOrdInd);
            ZYPEZDItemValueSetter.setValue(resultMsg.intfcCratDt, this.batDt);
        }

        // WH Transfer - Cancel
        if (ZYPCommonFunc.hasValue(cpoCancFlg) && "WHT_CANCEL".equals(cpoCancFlg)) {

            String[] cincGlblCmpyCatgCdList = null;
            if (ZYPCommonFunc.hasValue(rs.getString(CINC_GLBL_CMPY_CATG_CD))) {
                cincGlblCmpyCatgCdList = rs.getString(CINC_GLBL_CMPY_CATG_CD).split(",");
            }

            ZYPEZDItemValueSetter.setValue(resultMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblWhCd, rs.getString(CINC_GLBL_WH_CD));
            ZYPEZDItemValueSetter.setValue(resultMsg.intfcCpoOrdDt, editDigit(rs.getString(INTFC_CPO_ORD_DT), this.digitIntfcCpoOrdDt));
            ZYPEZDItemValueSetter.setValue(resultMsg.intfcCpoOrdNum, rs.getString(INTFC_CPO_ORD_NUM));
            ZYPEZDItemValueSetter.setValue(resultMsg.intfcPrtMdseCd, convMdseCdBean.getXtrnlSysPrtCd());
            ZYPEZDItemValueSetter.setValue(resultMsg.prtSizeTxt, convMdseCdBean.getXtrnlSysSize());
            ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblShpgMethCd, rs.getString(TRD_PTNR_SHPG_METH_CD));
            ZYPEZDItemValueSetter.setValue(resultMsg.cincShipToGlblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(resultMsg.cincRcvGlblWhCd, rs.getString(CINC_RCV_GLBL_WH_CD));
            ZYPEZDItemValueSetter.setValue(resultMsg.cincBillToGlblCmpyCd, this.glblCmpyCd);
            //ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblCmpyCatgCd, rs.getString(CINC_GLBL_CMPY_CATG_CD));
            ZYPEZDItemValueSetter.setValue(resultMsg.intfcSellToCustCd, editDigit(rs.getString(INTFC_SELL_TO_CUST_CD), this.digitIntfcSellToCustCd));
            ZYPEZDItemValueSetter.setValue(resultMsg.intfcOrdQtySgnTxt, VALUE_INTFC_QTY_SGN_TXT_PLUS);
            ZYPEZDItemValueSetter.setValue(resultMsg.intfcOrdQty, BigDecimal.ZERO);
            String intfcRsdDt = getIntfcRsdDtStockOut(rs);
            ZYPEZDItemValueSetter.setValue(resultMsg.intfcRsdDt, intfcRsdDt);
            ZYPEZDItemValueSetter.setValue(resultMsg.intfcExpdShipDt, getIntfcExpdShipDtStockOut(rs));
            ZYPEZDItemValueSetter.setValue(resultMsg.intfcRddDt, getIntfcRddDtStockOut(rs, intfcRsdDt));
            //ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblOrdCatgCd, rs.getString("CINC_GLBL_ORD_CATG_CD"));
            ZYPEZDItemValueSetter.setValue(resultMsg.prtChrgInd, this.prtChrgIndSales);
            ZYPEZDItemValueSetter.setValue(resultMsg.prtEmerOrdInd, this.prtEmerOrdInd);
            ZYPEZDItemValueSetter.setValue(resultMsg.intfcCratDt, this.batDt);

            if (cincGlblCmpyCatgCdList != null && cincGlblCmpyCatgCdList.length > 0) {
                ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblCmpyCatgCd, cincGlblCmpyCatgCdList[0]);
                ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblOrdCatgCd, cincGlblCmpyCatgCdList[1]);
            }
        }
    }

    /**
     * Set Value to TMsg for Work Order
     * @param rs ResultSet
     * @param convMdseCdBean NMXC104001ConvertPartsMdseCdBean
     * @param resultMsg INTFC_CPO_WRKTMsg
     * @throws SQLException
     */
    private void setWrkTMsgForWrkOrd(ResultSet rs, NMXC104001ConvertPartsMdseCdBean convMdseCdBean, INTFC_CPO_WRKTMsg resultMsg) throws SQLException {

        String intfcCatg = rs.getString(INTFC_CATG_TXT);

        // Component / Original Stock Out
        if (ZYPCommonFunc.hasValue(intfcCatg) && ("WO_STK_OUT".equals(intfcCatg))) {

            ZYPEZDItemValueSetter.setValue(resultMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblWhCd, rs.getString(CINC_GLBL_WH_CD));
            ZYPEZDItemValueSetter.setValue(resultMsg.intfcCpoOrdDt, editDigit(rs.getString(INTFC_CPO_ORD_DT), this.digitIntfcCpoOrdDt));
            ZYPEZDItemValueSetter.setValue(resultMsg.intfcCpoOrdNum, rs.getString(INTFC_CPO_ORD_NUM));
            ZYPEZDItemValueSetter.setValue(resultMsg.intfcPrtMdseCd, convMdseCdBean.getXtrnlSysPrtCd());
            ZYPEZDItemValueSetter.setValue(resultMsg.prtSizeTxt, convMdseCdBean.getXtrnlSysSize());
            ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblShpgMethCd, rs.getString(TRD_PTNR_SHPG_METH_CD));
            ZYPEZDItemValueSetter.setValue(resultMsg.cincShipToGlblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(resultMsg.cincRcvGlblWhCd, "");
            ZYPEZDItemValueSetter.setValue(resultMsg.cincBillToGlblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblCmpyCatgCd, CINC_GLBL_CMPY_CATG_CD_01);
            ZYPEZDItemValueSetter.setValue(resultMsg.intfcSellToCustCd, editDigit(rs.getString(INTFC_SELL_TO_CUST_CD), this.digitIntfcSellToCustCd));
            ZYPEZDItemValueSetter.setValue(resultMsg.intfcOrdQtySgnTxt, getOrdQtySgnTxt(rs));
            ZYPEZDItemValueSetter.setValue(resultMsg.intfcOrdQty, getAbsOrdQty(rs));
            ZYPEZDItemValueSetter.setValue(resultMsg.intfcRsdDt, getIntfcRsdDtWorkOrder(rs));
            ZYPEZDItemValueSetter.setValue(resultMsg.intfcExpdShipDt, getIntfcExpdShipDtWorkOrder(rs));
            ZYPEZDItemValueSetter.setValue(resultMsg.intfcRddDt, getIntfcRddDtWorkOrder(rs));
            ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblOrdCatgCd, CINC_GLBL_ORD_CATG_CD_ZZ);
            ZYPEZDItemValueSetter.setValue(resultMsg.prtChrgInd, this.prtChrgIndSales);
            ZYPEZDItemValueSetter.setValue(resultMsg.prtEmerOrdInd, this.prtEmerOrdInd);
            ZYPEZDItemValueSetter.setValue(resultMsg.intfcCratDt, this.batDt);
        }

        // Finished Stock In
        if (ZYPCommonFunc.hasValue(intfcCatg) && ("WO_STK_IN".equals(intfcCatg))) {

            ZYPEZDItemValueSetter.setValue(resultMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblWhCd, rs.getString(CINC_GLBL_WH_CD));
            ZYPEZDItemValueSetter.setValue(resultMsg.intfcCpoOrdDt, editDigit(rs.getString(INTFC_CPO_ORD_DT), this.digitIntfcCpoOrdDt));
            ZYPEZDItemValueSetter.setValue(resultMsg.intfcCpoOrdNum, rs.getString(INTFC_CPO_ORD_NUM));
            ZYPEZDItemValueSetter.setValue(resultMsg.intfcPrtMdseCd, convMdseCdBean.getXtrnlSysPrtCd());
            ZYPEZDItemValueSetter.setValue(resultMsg.prtSizeTxt, convMdseCdBean.getXtrnlSysSize());
            ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblShpgMethCd, CINC_GLBL_SHPG_METH_CD_OTHER);
            ZYPEZDItemValueSetter.setValue(resultMsg.cincShipToGlblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(resultMsg.cincRcvGlblWhCd, "");
            ZYPEZDItemValueSetter.setValue(resultMsg.cincBillToGlblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblCmpyCatgCd, CINC_GLBL_CMPY_CATG_CD_01);
            ZYPEZDItemValueSetter.setValue(resultMsg.intfcSellToCustCd, editDigit(rs.getString(INTFC_SELL_TO_CUST_CD), this.digitIntfcSellToCustCd));
            ZYPEZDItemValueSetter.setValue(resultMsg.intfcOrdQtySgnTxt, getOrdQtySgnTxtReverse(rs));
            ZYPEZDItemValueSetter.setValue(resultMsg.intfcOrdQty, getAbsOrdQty(rs));
            ZYPEZDItemValueSetter.setValue(resultMsg.intfcRsdDt, getIntfcRsdDtWorkOrder(rs));
            ZYPEZDItemValueSetter.setValue(resultMsg.intfcExpdShipDt, getIntfcExpdShipDtWorkOrder(rs));
            ZYPEZDItemValueSetter.setValue(resultMsg.intfcRddDt, getIntfcRddDtWorkOrder(rs));
            ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblOrdCatgCd, CINC_GLBL_ORD_CATG_CD_ZZ);
            ZYPEZDItemValueSetter.setValue(resultMsg.prtChrgInd, this.prtChrgIndSales);
            ZYPEZDItemValueSetter.setValue(resultMsg.prtEmerOrdInd, this.prtEmerOrdInd);
            ZYPEZDItemValueSetter.setValue(resultMsg.intfcCratDt, this.batDt);
        }
    }

    /**
     * Set Value to TMsg for Inventory Transaction(Parts Usage)
     * @param rs ResultSet
     * @param convMdseCdBean NMXC104001ConvertPartsMdseCdBean
     * @param resultMsg INTFC_CPO_WRKTMsg
     * @throws SQLException
     */
    private void setWrkTMsgForInvtyTrx(ResultSet rs, NMXC104001ConvertPartsMdseCdBean convMdseCdBean, INTFC_CPO_WRKTMsg resultMsg) throws SQLException {

        ZYPEZDItemValueSetter.setValue(resultMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblWhCd, rs.getString(CINC_GLBL_WH_CD));
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcCpoOrdDt, editDigit(rs.getString(INTFC_CPO_ORD_DT), this.digitIntfcCpoOrdDt));
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcCpoOrdNum, rs.getString(INTFC_CPO_ORD_NUM));
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcPrtMdseCd, convMdseCdBean.getXtrnlSysPrtCd());
        ZYPEZDItemValueSetter.setValue(resultMsg.prtSizeTxt, convMdseCdBean.getXtrnlSysSize());
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblShpgMethCd, CINC_GLBL_SHPG_METH_CD_OTHER);
        ZYPEZDItemValueSetter.setValue(resultMsg.cincShipToGlblCmpyCd, getCincShipToGlblCmpyCdPartsUsage(rs));
        ZYPEZDItemValueSetter.setValue(resultMsg.cincRcvGlblWhCd, "");
        ZYPEZDItemValueSetter.setValue(resultMsg.cincBillToGlblCmpyCd, getCincShipToGlblCmpyCdPartsUsage(rs));
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblCmpyCatgCd, CINC_GLBL_CMPY_CATG_CD_01);
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcSellToCustCd, editDigit(rs.getString(INTFC_SELL_TO_CUST_CD), this.digitIntfcSellToCustCd));
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcOrdQtySgnTxt, getOrdQtySgnTxtReverse(rs));
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcOrdQty, getAbsOrdQty(rs));
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcRsdDt, editDigit(rs.getString(INTFC_CPO_ORD_DT), this.digitIntfcCpoOrdDt));
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcExpdShipDt, editDigit(rs.getString(INTFC_CPO_ORD_DT), this.digitIntfcCpoOrdDt));
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcRddDt, editDigit(rs.getString(INTFC_CPO_ORD_DT), this.digitIntfcCpoOrdDt));
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblOrdCatgCd, getCincGlblOrdCatgCd(rs));
        ZYPEZDItemValueSetter.setValue(resultMsg.prtChrgInd, this.prtChrgIndSales);
        ZYPEZDItemValueSetter.setValue(resultMsg.prtEmerOrdInd, this.prtEmerOrdInd);
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcCratDt, this.batDt);

    }

    /**
     * Set Value to TMsg for Inventory Transaction(Parts Usage Reman)
     * @param rs ResultSet
     * @param convMdseCdBean NMXC104001ConvertPartsMdseCdBean
     * @param resultMsg INTFC_CPO_WRKTMsg
     * @throws SQLException
     */
    private void setWrkTMsgForInvtyTrxRmn(ResultSet rs, NMXC104001ConvertPartsMdseCdBean convMdseCdBean, INTFC_CPO_WRKTMsg resultMsg) throws SQLException {

        ZYPEZDItemValueSetter.setValue(resultMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblWhCd, rs.getString(CINC_GLBL_WH_CD));
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcCpoOrdDt, editDigit(rs.getString(INTFC_CPO_ORD_DT), this.digitIntfcCpoOrdDt));
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcCpoOrdNum, rs.getString(INTFC_CPO_ORD_NUM));
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcPrtMdseCd, convMdseCdBean.getXtrnlSysPrtCd());
        ZYPEZDItemValueSetter.setValue(resultMsg.prtSizeTxt, convMdseCdBean.getXtrnlSysSize());
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblShpgMethCd, CINC_GLBL_SHPG_METH_CD_OTHER);
        ZYPEZDItemValueSetter.setValue(resultMsg.cincShipToGlblCmpyCd, this.glblCmpyCd);
        // QC#31174 Update.Delete cincRcvGlblWhCd.
        ZYPEZDItemValueSetter.setValue(resultMsg.cincRcvGlblWhCd, "");
        ZYPEZDItemValueSetter.setValue(resultMsg.cincBillToGlblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblCmpyCatgCd, rs.getString(CINC_GLBL_CMPY_CATG_CD));
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcSellToCustCd, editDigit(rs.getString(INTFC_SELL_TO_CUST_CD), this.digitIntfcSellToCustCd));
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcOrdQtySgnTxt, getOrdQtySgnTxtReverse(rs));
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcOrdQty, getAbsOrdQty(rs));
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcRsdDt, editDigit(rs.getString(INTFC_CPO_ORD_DT), this.digitIntfcCpoOrdDt));
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcExpdShipDt, editDigit(rs.getString(INTFC_CPO_ORD_DT), this.digitIntfcCpoOrdDt));
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcRddDt, editDigit(rs.getString(INTFC_CPO_ORD_DT), this.digitIntfcCpoOrdDt));
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblOrdCatgCd, getCincGlblOrdCatgCd(rs));
        ZYPEZDItemValueSetter.setValue(resultMsg.prtChrgInd, this.prtChrgIndSales);
        ZYPEZDItemValueSetter.setValue(resultMsg.prtEmerOrdInd, this.prtEmerOrdInd);
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcCratDt, this.batDt);

    }

    /**
     * Set Value to TMsg for Inventory Transaction(Reman Item Change)
     * @param rs ResultSet
     * @param convMdseCdBean NMXC104001ConvertPartsMdseCdBean
     * @param resultMsg INTFC_CPO_WRKTMsg
     * @throws SQLException
     */
    private void setWrkTMsgForInvtyTrxRmnItmClg(ResultSet rs, NMXC104001ConvertPartsMdseCdBean convMdseCdBean, INTFC_CPO_WRKTMsg resultMsg) throws SQLException {

        ZYPEZDItemValueSetter.setValue(resultMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblWhCd, rs.getString(CINC_GLBL_WH_CD));
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcCpoOrdDt, editDigit(rs.getString(INTFC_CPO_ORD_DT), this.digitIntfcCpoOrdDt));
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcCpoOrdNum, rs.getString(INTFC_CPO_ORD_NUM));
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcPrtMdseCd, convMdseCdBean.getXtrnlSysPrtCd());
        ZYPEZDItemValueSetter.setValue(resultMsg.prtSizeTxt, convMdseCdBean.getXtrnlSysSize());
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblShpgMethCd, CINC_GLBL_SHPG_METH_CD_OTHER);
        ZYPEZDItemValueSetter.setValue(resultMsg.cincShipToGlblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(resultMsg.cincRcvGlblWhCd, "");
        ZYPEZDItemValueSetter.setValue(resultMsg.cincBillToGlblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblCmpyCatgCd, CINC_GLBL_CMPY_CATG_CD_01);
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcSellToCustCd, editDigit(rs.getString(INTFC_SELL_TO_CUST_CD), this.digitIntfcSellToCustCd));
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcOrdQtySgnTxt, getOrdQtySgnTxtReverse(rs));
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcOrdQty, getAbsOrdQty(rs));
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcRsdDt, editDigit(rs.getString(INTFC_CPO_ORD_DT), this.digitIntfcCpoOrdDt));
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcExpdShipDt, editDigit(rs.getString(INTFC_CPO_ORD_DT), this.digitIntfcCpoOrdDt));
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcRddDt, editDigit(rs.getString(INTFC_CPO_ORD_DT), this.digitIntfcCpoOrdDt));
        ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblOrdCatgCd, getCincGlblOrdCatgCd(rs));
        ZYPEZDItemValueSetter.setValue(resultMsg.prtChrgInd, this.prtChrgIndSales);
        ZYPEZDItemValueSetter.setValue(resultMsg.prtEmerOrdInd, this.prtEmerOrdInd);
        ZYPEZDItemValueSetter.setValue(resultMsg.intfcCratDt, this.batDt);

    }

    /**
     * Set Value to TMsg for Inventory Order
     * @param rs ResultSet
     * @param convMdseCdBean NMXC104001ConvertPartsMdseCdBean
     * @param resultMsg INTFC_CPO_WRKTMsg
     * @throws SQLException
     */
    private void setWrkTMsgForInvtyOrd(ResultSet rs, NMXC104001ConvertPartsMdseCdBean convMdseCdBean, INTFC_CPO_WRKTMsg resultMsg) throws SQLException {

        String intfcCatg = rs.getString(INTFC_CATG_TXT);

        // Disposal, Refurbish, SubContract, Item Change
        if (ZYPCommonFunc.hasValue(intfcCatg) && ("INVTY_ORD_NOT_TECH_REQ_RETURN".equals(intfcCatg))) {

            ZYPEZDItemValueSetter.setValue(resultMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblWhCd, rs.getString(CINC_GLBL_WH_CD));
            ZYPEZDItemValueSetter.setValue(resultMsg.intfcCpoOrdDt, editDigit(rs.getString(INTFC_CPO_ORD_DT), this.digitIntfcCpoOrdDt));
            ZYPEZDItemValueSetter.setValue(resultMsg.intfcCpoOrdNum, rs.getString(INTFC_CPO_ORD_NUM));
            ZYPEZDItemValueSetter.setValue(resultMsg.intfcPrtMdseCd, convMdseCdBean.getXtrnlSysPrtCd());
            ZYPEZDItemValueSetter.setValue(resultMsg.prtSizeTxt, convMdseCdBean.getXtrnlSysSize());
            ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblShpgMethCd, rs.getString(TRD_PTNR_SHPG_METH_CD));
            ZYPEZDItemValueSetter.setValue(resultMsg.cincShipToGlblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(resultMsg.cincRcvGlblWhCd, "");
            ZYPEZDItemValueSetter.setValue(resultMsg.cincBillToGlblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblCmpyCatgCd, CINC_GLBL_CMPY_CATG_CD_01);
            ZYPEZDItemValueSetter.setValue(resultMsg.intfcSellToCustCd, editDigit(rs.getString(INTFC_SELL_TO_CUST_CD), this.digitIntfcSellToCustCd));
            ZYPEZDItemValueSetter.setValue(resultMsg.intfcOrdQtySgnTxt, getOrdQtySgnTxt(rs));
            ZYPEZDItemValueSetter.setValue(resultMsg.intfcOrdQty, getAbsOrdQty(rs));
            ZYPEZDItemValueSetter.setValue(resultMsg.intfcRsdDt, getIntfcRsdDtDisposal(rs));
            ZYPEZDItemValueSetter.setValue(resultMsg.intfcExpdShipDt, getIntfcExpdShipDtDisposal(rs));
            ZYPEZDItemValueSetter.setValue(resultMsg.intfcRddDt, getIntfcRddDtDisposal(rs));
            ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblOrdCatgCd, CINC_GLBL_ORD_CATG_CD_ZZ);
            ZYPEZDItemValueSetter.setValue(resultMsg.prtChrgInd, this.prtChrgIndSales);
            ZYPEZDItemValueSetter.setValue(resultMsg.prtEmerOrdInd, this.prtEmerOrdInd);
            ZYPEZDItemValueSetter.setValue(resultMsg.intfcCratDt, this.batDt);
        }

        // Disposal, Refurbish, SubContract, Item Change (CANCEL)
        if (ZYPCommonFunc.hasValue(intfcCatg) && ("INVTY_ORD_NOT_TECH_REQ_RETURN_CANCEL".equals(intfcCatg))) {

            ZYPEZDItemValueSetter.setValue(resultMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblWhCd, rs.getString(CINC_GLBL_WH_CD));
            ZYPEZDItemValueSetter.setValue(resultMsg.intfcCpoOrdDt, editDigit(rs.getString(INTFC_CPO_ORD_DT), this.digitIntfcCpoOrdDt));
            ZYPEZDItemValueSetter.setValue(resultMsg.intfcCpoOrdNum, rs.getString(INTFC_CPO_ORD_NUM));
            ZYPEZDItemValueSetter.setValue(resultMsg.intfcPrtMdseCd, convMdseCdBean.getXtrnlSysPrtCd());
            ZYPEZDItemValueSetter.setValue(resultMsg.prtSizeTxt, convMdseCdBean.getXtrnlSysSize());
            ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblShpgMethCd, rs.getString(TRD_PTNR_SHPG_METH_CD));
            ZYPEZDItemValueSetter.setValue(resultMsg.cincShipToGlblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(resultMsg.cincRcvGlblWhCd, "");
            ZYPEZDItemValueSetter.setValue(resultMsg.cincBillToGlblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblCmpyCatgCd, CINC_GLBL_CMPY_CATG_CD_01);
            ZYPEZDItemValueSetter.setValue(resultMsg.intfcSellToCustCd, editDigit(rs.getString(INTFC_SELL_TO_CUST_CD), this.digitIntfcSellToCustCd));
            ZYPEZDItemValueSetter.setValue(resultMsg.intfcOrdQtySgnTxt, getOrdQtySgnTxtReverse(rs));
            ZYPEZDItemValueSetter.setValue(resultMsg.intfcOrdQty, getAbsOrdQty(rs));
            ZYPEZDItemValueSetter.setValue(resultMsg.intfcRsdDt, getIntfcRsdDtDisposal(rs));
            ZYPEZDItemValueSetter.setValue(resultMsg.intfcExpdShipDt, getIntfcExpdShipDtDisposal(rs));
            ZYPEZDItemValueSetter.setValue(resultMsg.intfcRddDt, getIntfcRddDtDisposal(rs));
            ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblOrdCatgCd, CINC_GLBL_ORD_CATG_CD_ZZ);
            ZYPEZDItemValueSetter.setValue(resultMsg.prtChrgInd, this.prtChrgIndSales);
            ZYPEZDItemValueSetter.setValue(resultMsg.prtEmerOrdInd, this.prtEmerOrdInd);
            ZYPEZDItemValueSetter.setValue(resultMsg.intfcCratDt, this.batDt);
        }

        // Tech Req / Tech Return
        if (ZYPCommonFunc.hasValue(intfcCatg) && ("INVTY_ORD_TECH_REQ_RETURN".equals(intfcCatg))) {

            String[] cincGlblCmpyCatgCdList = null;
            if (ZYPCommonFunc.hasValue(rs.getString(CINC_GLBL_CMPY_CATG_CD))) {
                cincGlblCmpyCatgCdList = rs.getString(CINC_GLBL_CMPY_CATG_CD).split(",");
            }

            ZYPEZDItemValueSetter.setValue(resultMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblWhCd, rs.getString(CINC_GLBL_WH_CD));
            ZYPEZDItemValueSetter.setValue(resultMsg.intfcCpoOrdDt, editDigit(this.batDt, this.digitIntfcCpoOrdDt));
            ZYPEZDItemValueSetter.setValue(resultMsg.intfcCpoOrdNum, rs.getString(INTFC_CPO_ORD_NUM));
            ZYPEZDItemValueSetter.setValue(resultMsg.intfcPrtMdseCd, convMdseCdBean.getXtrnlSysPrtCd());
            ZYPEZDItemValueSetter.setValue(resultMsg.prtSizeTxt, convMdseCdBean.getXtrnlSysSize());
            ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblShpgMethCd, rs.getString(TRD_PTNR_SHPG_METH_CD));
            ZYPEZDItemValueSetter.setValue(resultMsg.cincShipToGlblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(resultMsg.cincRcvGlblWhCd, rs.getString(CINC_RCV_GLBL_WH_CD));
            ZYPEZDItemValueSetter.setValue(resultMsg.cincBillToGlblCmpyCd, this.glblCmpyCd);
            //ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblCmpyCatgCd, CINC_GLBL_CMPY_CATG_CD_01);
            ZYPEZDItemValueSetter.setValue(resultMsg.intfcSellToCustCd, editDigit(getIntfcSellToCustCdItemChange(rs), this.digitIntfcSellToCustCd));
            ZYPEZDItemValueSetter.setValue(resultMsg.intfcOrdQtySgnTxt, getOrdQtySgnTxt(rs));
            ZYPEZDItemValueSetter.setValue(resultMsg.intfcOrdQty, getAbsOrdQty(rs));
            ZYPEZDItemValueSetter.setValue(resultMsg.intfcRsdDt, getIntfcRsdDtDisposal(rs));
            ZYPEZDItemValueSetter.setValue(resultMsg.intfcExpdShipDt, getIntfcExpdShipDtDisposal(rs));
            ZYPEZDItemValueSetter.setValue(resultMsg.intfcRddDt, getIntfcRddDtDisposal(rs));
            //ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblOrdCatgCd, CINC_GLBL_ORD_CATG_CD_ZZ);
            ZYPEZDItemValueSetter.setValue(resultMsg.prtChrgInd, this.prtChrgIndSales);
            ZYPEZDItemValueSetter.setValue(resultMsg.prtEmerOrdInd, this.prtEmerOrdInd);
            ZYPEZDItemValueSetter.setValue(resultMsg.intfcCratDt, this.batDt);

            if (cincGlblCmpyCatgCdList != null && cincGlblCmpyCatgCdList.length > 0) {
                ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblCmpyCatgCd, cincGlblCmpyCatgCdList[0]);
                ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblOrdCatgCd, cincGlblCmpyCatgCdList[1]);
            }

        }

        // Tech Req / Tech Return (CANCEL)
        if (ZYPCommonFunc.hasValue(intfcCatg) && ("INVTY_ORD_TECH_REQ_RETURN_CANCEL".equals(intfcCatg))) {

            String[] cincGlblCmpyCatgCdList = null;
            if (ZYPCommonFunc.hasValue(rs.getString(CINC_GLBL_CMPY_CATG_CD))) {
                cincGlblCmpyCatgCdList = rs.getString(CINC_GLBL_CMPY_CATG_CD).split(",");
            }

            ZYPEZDItemValueSetter.setValue(resultMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblWhCd, rs.getString(CINC_GLBL_WH_CD));
            ZYPEZDItemValueSetter.setValue(resultMsg.intfcCpoOrdDt, editDigit(this.batDt, this.digitIntfcCpoOrdDt));
            ZYPEZDItemValueSetter.setValue(resultMsg.intfcCpoOrdNum, rs.getString(INTFC_CPO_ORD_NUM));
            ZYPEZDItemValueSetter.setValue(resultMsg.intfcPrtMdseCd, convMdseCdBean.getXtrnlSysPrtCd());
            ZYPEZDItemValueSetter.setValue(resultMsg.prtSizeTxt, convMdseCdBean.getXtrnlSysSize());
            ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblShpgMethCd, rs.getString(TRD_PTNR_SHPG_METH_CD));
            ZYPEZDItemValueSetter.setValue(resultMsg.cincShipToGlblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(resultMsg.cincRcvGlblWhCd, rs.getString(CINC_RCV_GLBL_WH_CD));
            ZYPEZDItemValueSetter.setValue(resultMsg.cincBillToGlblCmpyCd, this.glblCmpyCd);
            //ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblCmpyCatgCd, CINC_GLBL_CMPY_CATG_CD_01);
            ZYPEZDItemValueSetter.setValue(resultMsg.intfcSellToCustCd, editDigit(getIntfcSellToCustCdItemChange(rs), this.digitIntfcSellToCustCd));
            ZYPEZDItemValueSetter.setValue(resultMsg.intfcOrdQtySgnTxt, getOrdQtySgnTxtReverse(rs));
            ZYPEZDItemValueSetter.setValue(resultMsg.intfcOrdQty, getAbsOrdQty(rs));
            ZYPEZDItemValueSetter.setValue(resultMsg.intfcRsdDt, getIntfcRsdDtDisposal(rs));
            ZYPEZDItemValueSetter.setValue(resultMsg.intfcExpdShipDt, getIntfcExpdShipDtDisposal(rs));
            ZYPEZDItemValueSetter.setValue(resultMsg.intfcRddDt, getIntfcRddDtDisposal(rs));
            //ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblOrdCatgCd, CINC_GLBL_ORD_CATG_CD_ZZ);
            ZYPEZDItemValueSetter.setValue(resultMsg.prtChrgInd, this.prtChrgIndSales);
            ZYPEZDItemValueSetter.setValue(resultMsg.prtEmerOrdInd, this.prtEmerOrdInd);
            ZYPEZDItemValueSetter.setValue(resultMsg.intfcCratDt, this.batDt);

            if (cincGlblCmpyCatgCdList != null && cincGlblCmpyCatgCdList.length > 0) {
                ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblCmpyCatgCd, cincGlblCmpyCatgCdList[0]);
                ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblOrdCatgCd, cincGlblCmpyCatgCdList[1]);
            }

        }

    }

    /**
     * Set Value to TMsg for Inventory Return
     * @param rs ResultSet
     * @param convMdseCdBean NMXC104001ConvertPartsMdseCdBean
     * @param resultMsg INTFC_CPO_WRKTMsg
     * @throws SQLException
     */
    private void setWrkTMsgForRma(ResultSet rs, NMXC104001ConvertPartsMdseCdBean convMdseCdBean, INTFC_CPO_WRKTMsg resultMsg) throws SQLException {

        String intfcCatg = rs.getString(INTFC_CATG_TXT);

        // RETURN
        if (ZYPCommonFunc.hasValue(intfcCatg) && "RETURN_NEW".equals(intfcCatg)) {

            ZYPEZDItemValueSetter.setValue(resultMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblWhCd, rs.getString(CINC_GLBL_WH_CD));
            ZYPEZDItemValueSetter.setValue(resultMsg.intfcCpoOrdDt, editDigit(rs.getString(INTFC_CPO_ORD_DT), this.digitIntfcCpoOrdDt));
            ZYPEZDItemValueSetter.setValue(resultMsg.intfcCpoOrdNum, rs.getString(INTFC_CPO_ORD_NUM));
            ZYPEZDItemValueSetter.setValue(resultMsg.intfcPrtMdseCd, convMdseCdBean.getXtrnlSysPrtCd());
            ZYPEZDItemValueSetter.setValue(resultMsg.prtSizeTxt, convMdseCdBean.getXtrnlSysSize());
            ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblShpgMethCd, CINC_GLBL_SHPG_METH_CD_OTHER);
            ZYPEZDItemValueSetter.setValue(resultMsg.cincShipToGlblCmpyCd, getCincShipToGlblCmpyCdReturn(rs));
            ZYPEZDItemValueSetter.setValue(resultMsg.cincRcvGlblWhCd, "");
            ZYPEZDItemValueSetter.setValue(resultMsg.cincBillToGlblCmpyCd, getCincBillToGlblCmpyCdReturn(rs));
            ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblCmpyCatgCd, RCPT_CMPY_CLS.OTHER);
            ZYPEZDItemValueSetter.setValue(resultMsg.intfcSellToCustCd, editDigit(rs.getString(INTFC_SELL_TO_CUST_CD), this.digitIntfcSellToCustCd));
            // QC#26966 09/14/2018 Update. do not need to reverse.
            ZYPEZDItemValueSetter.setValue(resultMsg.intfcOrdQtySgnTxt, getOrdQtySgnTxt(rs));
            ZYPEZDItemValueSetter.setValue(resultMsg.intfcOrdQty, getAbsOrdQty(rs));
            ZYPEZDItemValueSetter.setValue(resultMsg.intfcRsdDt, getIntfcRsdDtReturn(rs));
            ZYPEZDItemValueSetter.setValue(resultMsg.intfcExpdShipDt, getIntfcExpdShipdDtReturn(rs));
            ZYPEZDItemValueSetter.setValue(resultMsg.intfcRddDt, getIntfcRddDtReturn(rs));
            ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblOrdCatgCd, getCincGlblOrdCatgCd(rs));
            ZYPEZDItemValueSetter.setValue(resultMsg.prtChrgInd, this.prtChrgIndSales);
            ZYPEZDItemValueSetter.setValue(resultMsg.prtEmerOrdInd, this.prtEmerOrdInd);
            ZYPEZDItemValueSetter.setValue(resultMsg.intfcCratDt, this.batDt);
        }

        // RETURN (CANCEL)
        if (ZYPCommonFunc.hasValue(intfcCatg) && "RETURN_CANCEL".equals(intfcCatg)) {

            ZYPEZDItemValueSetter.setValue(resultMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblWhCd, rs.getString(CINC_GLBL_WH_CD));
            ZYPEZDItemValueSetter.setValue(resultMsg.intfcCpoOrdDt, editDigit(rs.getString(INTFC_CPO_ORD_DT), this.digitIntfcCpoOrdDt));
            ZYPEZDItemValueSetter.setValue(resultMsg.intfcCpoOrdNum, rs.getString(INTFC_CPO_ORD_NUM));
            ZYPEZDItemValueSetter.setValue(resultMsg.intfcPrtMdseCd, convMdseCdBean.getXtrnlSysPrtCd());
            ZYPEZDItemValueSetter.setValue(resultMsg.prtSizeTxt, convMdseCdBean.getXtrnlSysSize());
            ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblShpgMethCd, CINC_GLBL_SHPG_METH_CD_OTHER);
            ZYPEZDItemValueSetter.setValue(resultMsg.cincShipToGlblCmpyCd, getCincShipToGlblCmpyCdReturn(rs));
            ZYPEZDItemValueSetter.setValue(resultMsg.cincRcvGlblWhCd, "");
            ZYPEZDItemValueSetter.setValue(resultMsg.cincBillToGlblCmpyCd, getCincBillToGlblCmpyCdReturn(rs));
            ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblCmpyCatgCd, RCPT_CMPY_CLS.OTHER);
            ZYPEZDItemValueSetter.setValue(resultMsg.intfcSellToCustCd, editDigit(rs.getString(INTFC_SELL_TO_CUST_CD), this.digitIntfcSellToCustCd));
            ZYPEZDItemValueSetter.setValue(resultMsg.intfcOrdQtySgnTxt, getOrdQtySgnTxtReverse(rs));
            ZYPEZDItemValueSetter.setValue(resultMsg.intfcOrdQty, getAbsOrdQty(rs));
            ZYPEZDItemValueSetter.setValue(resultMsg.intfcRsdDt, getIntfcRsdDtReturn(rs));
            ZYPEZDItemValueSetter.setValue(resultMsg.intfcExpdShipDt, getIntfcExpdShipdDtReturn(rs));
            ZYPEZDItemValueSetter.setValue(resultMsg.intfcRddDt, getIntfcRddDtReturn(rs));
            ZYPEZDItemValueSetter.setValue(resultMsg.cincGlblOrdCatgCd, getCincGlblOrdCatgCd(rs));
            ZYPEZDItemValueSetter.setValue(resultMsg.prtChrgInd, this.prtChrgIndSales);
            ZYPEZDItemValueSetter.setValue(resultMsg.prtEmerOrdInd, this.prtEmerOrdInd);
            ZYPEZDItemValueSetter.setValue(resultMsg.intfcCratDt, this.batDt);
        }
    }

    private String getCincShipToGlblCmpyCdReturn(ResultSet rs) throws SQLException {

        String shipToglblCmpyCd = rs.getString(CINC_SHIP_TO_GLBL_CMPY_CD);

        if (!ZYPCommonFunc.hasValue(shipToglblCmpyCd)) {

            shipToglblCmpyCd = "";
        }

        return shipToglblCmpyCd;
    }

    private String getCincBillToGlblCmpyCdReturn(ResultSet rs) throws SQLException {

        String shipToglblCmpyCd = rs.getString(CINC_BILL_TO_GLBL_CMPY_CD);

        if (!ZYPCommonFunc.hasValue(shipToglblCmpyCd)) {

            shipToglblCmpyCd = "";
        }

        return shipToglblCmpyCd;
    }

    private String getOrdQtySgnTxt(ResultSet rs) throws SQLException {

        String ordQtySgnTxt = null;
        BigDecimal intfcOrdQty = rs.getBigDecimal(INTFC_ORD_QTY);

        if (ZYPCommonFunc.hasValue(intfcOrdQty) && BigDecimal.ZERO.compareTo(intfcOrdQty) > 0) {

            ordQtySgnTxt = VALUE_INTFC_QTY_SGN_TXT_MINUS;

        } else {

            ordQtySgnTxt = VALUE_INTFC_QTY_SGN_TXT_PLUS;
        }

        return ordQtySgnTxt;
    }

    private String getOrdQtySgnTxtReverse(ResultSet rs) throws SQLException {

        String ordQtySgnTxt = null;
        BigDecimal intfcOrdQty = rs.getBigDecimal(INTFC_ORD_QTY);

        if (ZYPCommonFunc.hasValue(intfcOrdQty) && BigDecimal.ZERO.compareTo(intfcOrdQty) >= 0) {

            ordQtySgnTxt = VALUE_INTFC_QTY_SGN_TXT_PLUS;

        } else {

            ordQtySgnTxt = VALUE_INTFC_QTY_SGN_TXT_MINUS;
        }

        return ordQtySgnTxt;
    }

    private BigDecimal getAbsOrdQty(ResultSet rs) throws SQLException {

        BigDecimal absOrdQty = rs.getBigDecimal(INTFC_ORD_QTY);

        if (ZYPCommonFunc.hasValue(absOrdQty)) {

            absOrdQty = absOrdQty.abs();
        }

        return absOrdQty;
    }

    private String getIntfcRsdDtWorkOrder(ResultSet rs) throws SQLException {

        String intfcRsdDt = rs.getString(RQST_RCV_DT);

        if (!ZYPCommonFunc.hasValue(intfcRsdDt)) {

            intfcRsdDt = ZYPDateUtil.DateFormatter(rs.getString(INTFC_CPO_ORD_DT), YYYYMMDDHHMMSSSSS, ZYPDateUtil.TYPE_YYYYMMDD);
            intfcRsdDt = ZYPDateUtil.addBusinessDay(this.glblCmpyCd, intfcRsdDt, 1);
        }

        return intfcRsdDt;
    }

    private String getIntfcExpdShipDtWorkOrder(ResultSet rs) throws SQLException {

        String intfcExpdShipDt = rs.getString(RQST_RCV_DT);

        if (!ZYPCommonFunc.hasValue(intfcExpdShipDt)) {

            intfcExpdShipDt = ZYPDateUtil.DateFormatter(rs.getString(INTFC_CPO_ORD_DT), YYYYMMDDHHMMSSSSS, ZYPDateUtil.TYPE_YYYYMMDD);
            intfcExpdShipDt = ZYPDateUtil.addBusinessDay(this.glblCmpyCd, intfcExpdShipDt, 1);
        }

        return intfcExpdShipDt;
    }

    private String getIntfcRddDtWorkOrder(ResultSet rs) throws SQLException {

        String intfcRddDt = rs.getString(RQST_RCV_DT);

        if (!ZYPCommonFunc.hasValue(intfcRddDt)) {

            intfcRddDt = ZYPDateUtil.DateFormatter(rs.getString(INTFC_CPO_ORD_DT), YYYYMMDDHHMMSSSSS, ZYPDateUtil.TYPE_YYYYMMDD);
            intfcRddDt = ZYPDateUtil.addBusinessDay(this.glblCmpyCd, intfcRddDt, 1);
        }

        return intfcRddDt;
    }

    private String getIntfcRsdDtDisposal(ResultSet rs) throws SQLException {

        String intfcRsdDt = rs.getString(INTFC_RSD_DT);

        if (!ZYPCommonFunc.hasValue(intfcRsdDt)) {

            intfcRsdDt = ZYPDateUtil.DateFormatter(rs.getString(INTFC_CPO_ORD_DT), YYYYMMDDHHMMSSSSS, ZYPDateUtil.TYPE_YYYYMMDD);
            intfcRsdDt = ZYPDateUtil.addBusinessDay(this.glblCmpyCd, intfcRsdDt, 1);
        }

        return intfcRsdDt;
    }

    private String getIntfcExpdShipDtDisposal(ResultSet rs) throws SQLException {

        String intfcExpdShipDt = rs.getString(INTFC_RSD_DT);

        if (!ZYPCommonFunc.hasValue(intfcExpdShipDt)) {

            intfcExpdShipDt = ZYPDateUtil.DateFormatter(rs.getString(INTFC_CPO_ORD_DT), YYYYMMDDHHMMSSSSS, ZYPDateUtil.TYPE_YYYYMMDD);
            intfcExpdShipDt = ZYPDateUtil.addBusinessDay(this.glblCmpyCd, intfcExpdShipDt, 1);
        }

        return intfcExpdShipDt;
    }

    private String getIntfcRddDtDisposal(ResultSet rs) throws SQLException {

        String intfcRddDt = rs.getString(INTFC_RSD_DT);

        if (!ZYPCommonFunc.hasValue(intfcRddDt)) {

            intfcRddDt = ZYPDateUtil.DateFormatter(rs.getString(INTFC_CPO_ORD_DT), YYYYMMDDHHMMSSSSS, ZYPDateUtil.TYPE_YYYYMMDD);
            intfcRddDt = ZYPDateUtil.addBusinessDay(this.glblCmpyCd, intfcRddDt, 1);
        }

        return intfcRddDt;
    }

    private String getIntfcRsdDtReturn(ResultSet rs) throws SQLException {

        String intfcRsdDt = rs.getString(RQST_PICK_UP_DT);

        if (!ZYPCommonFunc.hasValue(intfcRsdDt)) {

            intfcRsdDt = rs.getString("INTFC_CPO_DT");
            intfcRsdDt = ZYPDateUtil.addBusinessDay(this.glblCmpyCd, intfcRsdDt, 1);
        }

        return intfcRsdDt;
    }

    private String getIntfcExpdShipdDtReturn(ResultSet rs) throws SQLException {

        String intfcRsdDt = rs.getString(RQST_PICK_UP_DT);

        if (!ZYPCommonFunc.hasValue(intfcRsdDt)) {

            intfcRsdDt = rs.getString("INTFC_CPO_DT");
            intfcRsdDt = ZYPDateUtil.addBusinessDay(this.glblCmpyCd, intfcRsdDt, 1);
        }

        return intfcRsdDt;
    }

    private String getIntfcRddDtReturn(ResultSet rs) throws SQLException {

        String intfcRsdDt = rs.getString(RQST_PICK_UP_DT);

        if (!ZYPCommonFunc.hasValue(intfcRsdDt)) {

            intfcRsdDt = rs.getString(INTFC_CPO_ORD_DT);
            intfcRsdDt = ZYPDateUtil.addBusinessDay(this.glblCmpyCd, intfcRsdDt, 1);
        }

        return intfcRsdDt;
    }

    private String getIntfcRsdDtStockOut(ResultSet rs) throws SQLException {

        String intfcRsdDt = getIntfcRsdDt(rs);

        if (!ZYPCommonFunc.hasValue(intfcRsdDt)) {

            intfcRsdDt = getIntfcExpdShipDtStockOut(rs);
        }

        return intfcRsdDt;
    }

    private String getIntfcExpdShipDtStockOut(ResultSet rs) throws SQLException {

        String intfcRsdDt = rs.getString(INTFC_EXPD_SHIP_DT);

        if (!ZYPCommonFunc.hasValue(intfcRsdDt)) {

            intfcRsdDt = rs.getString(INTFC_CPO_ORD_DT);
            intfcRsdDt = ZYPDateUtil.addBusinessDay(this.glblCmpyCd, intfcRsdDt, 1);
        }

        return intfcRsdDt;
    }

    private String getIntfcRddDtStockOut(ResultSet rs, String intfcRsdDt) throws SQLException {

        String intfcRddDt = rs.getString(INTFC_RDD_DT);

        if (!ZYPCommonFunc.hasValue(intfcRddDt)) {

            intfcRddDt = intfcRsdDt;
        }

        return intfcRddDt;
    }

    private String getCincGlblOrdCatgCd(ResultSet rs) throws SQLException {

        String cincGlblOrdCatgCd = null;

        if (this.glblCmpyCd.equals(rs.getString(CINC_SHIP_TO_GLBL_CMPY_CD))) {

            cincGlblOrdCatgCd = CINC_GLBL_ORD_CATG_CD_ZZ;

        } else {

            cincGlblOrdCatgCd = this.glblOrdCatgCd;
        }

        return cincGlblOrdCatgCd;
    }

    private String getIntfcSellToCustCdItemChange(ResultSet rs) throws SQLException {

        return rs.getString(INTFC_SELL_TO_CUST_CD);
    }

    private String getCincShipToGlblCmpyCdPartsUsage(ResultSet rs) throws SQLException {

        String cincShipToGlblCmpyCd = rs.getString(CINC_SHIP_TO_GLBL_CMPY_CD);

        if (!ZYPCommonFunc.hasValue(cincShipToGlblCmpyCd)) {

            cincShipToGlblCmpyCd = "";
        }

        return cincShipToGlblCmpyCd;
    }

    private ArrayList<String> getExclInvtyLocCd() {

        String varCharCnstVal = getVarCharConst(KEY_PRT_EXCL_INVTY_LOC_CD_CINC, false);
        ArrayList<String> invtyLocCdListTemp = null;

        if (ZYPCommonFunc.hasValue(varCharCnstVal)) {

            invtyLocCdListTemp = new ArrayList<String>();

            for (String invtyLocCd : varCharCnstVal.split(",")) {

                invtyLocCdListTemp.add(invtyLocCd);
            }
        }

        return invtyLocCdListTemp;
    }

    private ArrayList<String> getScubeVndSysTpCd() {

        String varCharCnstVal = getVarCharConst(KEY_SCUBE_VND_SYS_TP_CD, false);
        ArrayList<String> scubeVndSysTpCdTemp = null;

        if (ZYPCommonFunc.hasValue(varCharCnstVal)) {

            scubeVndSysTpCdTemp = new ArrayList<String>();

            for (String vndSysTpCd : varCharCnstVal.split(",")) {

                scubeVndSysTpCdTemp.add(vndSysTpCd);
            }
        }

        return scubeVndSysTpCdTemp;
    }

    // QC#26966 Add method.
    private String setCincGlblOrdCatgCd(String cincGlblWh, String rcvCincGlblCmpyCd, String rcvCincGlblWh, String cincGlblOrdCatgCd) {

        // his.glblOrdCatgCd = "A1"
        if (!this.glblOrdCatgCd.equals(cincGlblOrdCatgCd)) {
            return cincGlblOrdCatgCd;
        }

        String cincGlblWhCdOfItasca = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_KEY_CINC_GLBL_WH_CD_ITASC, this.glblCmpyCd);

        if (!ZYPCommonFunc.hasValue(cincGlblWhCdOfItasca)) {
            cincGlblWhCdOfItasca = "ITASC";
        }

        if (!cincGlblWhCdOfItasca.equals(cincGlblWh) //
                && (!ZYPCommonFunc.hasValue(rcvCincGlblCmpyCd)//
                || !ZYPCommonFunc.hasValue(rcvCincGlblWh))) {
            return cincGlblOrdCatgCd;
        } else {
            return CINC_GLBL_ORD_CATG_CD_BZ;
        }
    }
}
