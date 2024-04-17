package com.canon.cusa.s21.batch.NFA.NFAB106001;

import static com.canon.cusa.s21.batch.NFA.NFAB106001.constant.NFAB106001Constant.*;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.db.AJE_INTFC_CTRLTMsg;
import business.db.MAN_JRNL_ENTRY_DTLTMsg;
import business.db.MAN_JRNL_ENTRY_HDRTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AJE_INTFC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.JRNL_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;

/**
 * <pre>
 * Chargeback Creation
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/10/03   Hitachi         K.Kojima        Create          N/A
 * </pre>
 */
public class NFAB106001 extends S21BatchMain {

    /** SSM-Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** SSM-Client */
    private S21SsmBatchClient ssmBatchClient = null;

    /** termination code */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Batch Process Date */
    private String batProcDate = null;

    /** Error Count */
    private int errorCount = 0;

    /** Normal Count */
    private int normalCount = 0;

    /** Commit Max Number */
    private int commitNumber = 0;

    /** AJE_VAL_TRX_RSN_CHRGBM */
    private String ajeValTrxRsnChrgbk = null;

    @Override
    protected void initRoutine() {
        // "Global Company Code" Mandatory
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            this.termCd = TERM_CD.ABNORMAL_END;
            throw new S21AbendException(NFAM0052E, new String[] {"Global Company Code" });
        }

        // "Batch Process Date"
        this.batProcDate = ZYPDateUtil.getBatProcDate(glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.batProcDate)) {
            this.termCd = TERM_CD.ABNORMAL_END;
            throw new S21AbendException(NZZM0012E, new String[] {"Batch Process Date" });
        }

        // Get Commit Number
        this.commitNumber = getCommitCount();
        if (this.commitNumber <= 0 || this.commitNumber >= MAX_COMMIT_NUMBER) {
            this.commitNumber = MAX_COMMIT_NUMBER;
        }

        // get VAR_CHAR_CONST
        this.ajeValTrxRsnChrgbk = ZYPCodeDataUtil.getVarCharConstValue(AJE_VAL_TRX_RSN_CHRGBK, this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.ajeValTrxRsnChrgbk)) {
            this.termCd = TERM_CD.ABNORMAL_END;
            throw new S21AbendException(NZZM0012E, new String[] {"AJE_VAL_TRX_RSN_CHRGBK" });
        }

        // initialize
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }

    @Override
    protected void mainRoutine() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<MAN_JRNL_ENTRY_DTLTMsg> dtlTMsgList = new ArrayList<MAN_JRNL_ENTRY_DTLTMsg>();
        List<AJE_INTFC_CTRLTMsg> ctrlTMsgList = new ArrayList<AJE_INTFC_CTRLTMsg>();

        try {
            Map<String, Object> stesParam = new HashMap<String, Object>();
            stesParam.put("glblCmpyCd", this.glblCmpyCd);
            stesParam.put("trxCd", TRX.EMSD_CHARGEBACK);
            stesParam.put("trxRsnCdList", this.ajeValTrxRsnChrgbk.split(","));
            stesParam.put("glDt", subString(this.batProcDate, 0, 6));
            stesParam.put("ajeIntfcTpCd", AJE_INTFC_TP.FM_CHARGEBACK);

            ps = this.ssmLLClient.createPreparedStatement("getChargeBack", stesParam, execParam());
            rs = ps.executeQuery();

            BigDecimal manJrnlEntryHdrPk = null;
            BigDecimal manJrnlLineNum = null;
            while (rs.next()) {
                if (rs.isFirst()) {
                    manJrnlEntryHdrPk = getManJrnlEntryHdrPk();
                    if (manJrnlEntryHdrPk == null) {
                        BigDecimal chrgBackEntryVrsnNum = getNextChrgBackEntryVrsnNum();
                        MAN_JRNL_ENTRY_HDRTMsg hdrTMsg = new MAN_JRNL_ENTRY_HDRTMsg();
                        manJrnlEntryHdrPk = insertHdr(hdrTMsg, chrgBackEntryVrsnNum);
                        manJrnlLineNum = BigDecimal.ONE;
                    } else {
                        manJrnlLineNum = getMaxManJrnlLineNum(manJrnlEntryHdrPk);
                    }
                }
                MAN_JRNL_ENTRY_DTLTMsg dtlTMsg = new MAN_JRNL_ENTRY_DTLTMsg();
                AJE_INTFC_CTRLTMsg ctrlTMsg = new AJE_INTFC_CTRLTMsg();
                createDtlTMsg(rs, dtlTMsg, manJrnlEntryHdrPk, manJrnlLineNum);
                createCtrlTMsg(rs, ctrlTMsg);
                dtlTMsgList.add(dtlTMsg);
                ctrlTMsgList.add(ctrlTMsg);
                if (this.commitNumber == dtlTMsgList.size()) {
                    executeInsert(dtlTMsgList, ctrlTMsgList);
                    this.normalCount += dtlTMsgList.size();
                    dtlTMsgList = new ArrayList<MAN_JRNL_ENTRY_DTLTMsg>();
                    ctrlTMsgList = new ArrayList<AJE_INTFC_CTRLTMsg>();
                }
                manJrnlLineNum = manJrnlLineNum.add(BigDecimal.ONE);
            }
            if (dtlTMsgList.size() > 0) {
                executeInsert(dtlTMsgList, ctrlTMsgList);
                this.normalCount += dtlTMsgList.size();
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }
    }

    @Override
    protected void termRoutine() {
        if (this.errorCount > 0) {
            this.termCd = TERM_CD.WARNING_END;
        }
        setTermState(this.termCd, this.normalCount, this.errorCount);
    }

    /**
     * main
     * @param args String[]
     */
    public static void main(String[] args) {
        new NFAB106001().executeBatch(NFAB106001.class.getSimpleName());
    }

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
     * getManJrnlEntryHdr
     * @return BigDecimal
     */
    private BigDecimal getManJrnlEntryHdrPk() {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", this.glblCmpyCd);
        params.put("chrgBackEntryCd", CHRG_BACK_ENTRY_CD_VALUE);
        params.put("glDt", subString(this.batProcDate, 0, 6));
        params.put("flgN", ZYPConstant.FLG_OFF_N);
        BigDecimal result = (BigDecimal) this.ssmBatchClient.queryObject("getManJrnlEntryHdrPk", params, execParam());
        return result;
    }

    /**
     * getManJrnlEntryHdr
     * @return BigDecimal
     */
    private BigDecimal getNextChrgBackEntryVrsnNum() {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", this.glblCmpyCd);
        params.put("chrgBackEntryCd", CHRG_BACK_ENTRY_CD_VALUE);
        params.put("glDt", subString(this.batProcDate, 0, 6));
        params.put("flgY", ZYPConstant.FLG_ON_Y);
        BigDecimal result = (BigDecimal) this.ssmBatchClient.queryObject("getNextChrgBackEntryVrsnNum", params, execParam());
        return result;
    }

    /**
     * getMaxManJrnlLineNum
     * @param manJrnlEntryHdrPk BigDecimal
     * @return BigDecimal
     */
    private BigDecimal getMaxManJrnlLineNum(BigDecimal manJrnlEntryHdrPk) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", this.glblCmpyCd);
        params.put("manJrnlEntryHdrPk", manJrnlEntryHdrPk);
        BigDecimal result = (BigDecimal) this.ssmBatchClient.queryObject("getMaxManJrnlLineNum", params, execParam());
        return result;
    }

    /**
     * createHdrTmsg
     * @param tMsg MAN_JRNL_ENTRY_HDRTMsg
     * @param chrgBackEntryVrsnNum BigDecimal
     * @return BigDecimal
     */
    private BigDecimal insertHdr(MAN_JRNL_ENTRY_HDRTMsg tMsg, BigDecimal chrgBackEntryVrsnNum) {
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.manJrnlEntryHdrPk, getZYPOracleSeqManJrnlEntryHdrSq());
        ZYPEZDItemValueSetter.setValue(tMsg.glPerNm, convertGlPeriod(this.batProcDate));
        ZYPEZDItemValueSetter.setValue(tMsg.glDt, this.batProcDate);
        ZYPEZDItemValueSetter.setValue(tMsg.jrnlCatgCd, JRNL_CATG_CD_CHARGEBACK);
        String manJrnlNm = subString(this.batProcDate, 0, 6) + ZYPCodeDataUtil.getName(JRNL_CATG.class, this.glblCmpyCd, tMsg.jrnlCatgCd.getValue()) + "_" + chrgBackEntryVrsnNum;
        ZYPEZDItemValueSetter.setValue(tMsg.manJrnlNm, manJrnlNm);
        ZYPEZDItemValueSetter.setValue(tMsg.manJrnlDescTxt, tMsg.manJrnlNm);
        ZYPEZDItemValueSetter.setValue(tMsg.rvslCpltFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(tMsg.autoRvslFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(tMsg.manJrnlCpltFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(tMsg.glSendCpltFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(tMsg.chrgBackEntryCd, CHRG_BACK_ENTRY_CD_VALUE);
        ZYPEZDItemValueSetter.setValue(tMsg.chrgBackEntryVrsnNum, chrgBackEntryVrsnNum);
        S21FastTBLAccessor.insert(tMsg);
        return tMsg.manJrnlEntryHdrPk.getValue();
    }

    /**
     * createDtlTMsg
     * @param rs ResultSet
     * @param tMsg MAN_JRNL_ENTRY_DTLTMsg
     * @param manJrnlEntryHdrPk BigDecimal
     * @param manJrnlLineNum BigDecimal
     * @throws SQLException
     */
    private void createDtlTMsg(ResultSet rs, MAN_JRNL_ENTRY_DTLTMsg tMsg, BigDecimal manJrnlEntryHdrPk, BigDecimal manJrnlLineNum) throws SQLException {
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.manJrnlEntryDtlPk, getZYPOracleSeqManJrnlEntryDtlSq());
        ZYPEZDItemValueSetter.setValue(tMsg.manJrnlEntryHdrPk, manJrnlEntryHdrPk);
        ZYPEZDItemValueSetter.setValue(tMsg.manJrnlLineNum, manJrnlLineNum);
        ZYPEZDItemValueSetter.setValue(tMsg.drCrTpCd, rs.getString(DR_CR_TP_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.coaCmpyCd, subString(rs.getString(AJE_COA_CMPY_CD), 0, 3));
        ZYPEZDItemValueSetter.setValue(tMsg.coaBrCd, subString(rs.getString(AJE_COA_BR_CD), 0, 3));
        ZYPEZDItemValueSetter.setValue(tMsg.coaCcCd, subString(rs.getString(AJE_COA_CC_CD), 0, 6));
        ZYPEZDItemValueSetter.setValue(tMsg.coaAcctCd, subString(rs.getString(AJE_COA_ACCT_CD), 0, 8));
        ZYPEZDItemValueSetter.setValue(tMsg.coaProdCd, subString(rs.getString(AJE_COA_PROD_CD), 0, 8));
        ZYPEZDItemValueSetter.setValue(tMsg.coaChCd, subString(rs.getString(AJE_COA_CH_CD), 0, 3));
        ZYPEZDItemValueSetter.setValue(tMsg.coaAfflCd, subString(rs.getString(AJE_COA_AFFL_CD), 0, 3));
        ZYPEZDItemValueSetter.setValue(tMsg.coaProjCd, subString(rs.getString(AJE_COA_PROJ_CD), 0, 2));
        ZYPEZDItemValueSetter.setValue(tMsg.coaExtnCd, subString(rs.getString(AJE_COA_EXTN_CD), 0, 3));
        ZYPEZDItemValueSetter.setValue(tMsg.jrnlDealAmt, rs.getBigDecimal(JRNL_DEAL_AMT));
        ZYPEZDItemValueSetter.setValue(tMsg.jrnlFuncAmt, rs.getBigDecimal(JRNL_FUNC_AMT));
        ZYPEZDItemValueSetter.setValue(tMsg.dealCcyCd, rs.getString(DEAL_CCY_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.manJrnlLineTxt, rs.getString(TRX_RSN_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.dsAcctNum, rs.getString(BILL_TO_CUST_ACCT_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.serNum, rs.getString(SER_NUM));
    }

    /**
     * createCtrlTMsg
     * @param rs ResultSet
     * @param tMsg AJE_INTFC_CTRLTMsg
     * @throws SQLException
     */
    private void createCtrlTMsg(ResultSet rs, AJE_INTFC_CTRLTMsg tMsg) throws SQLException {
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.ajeIntfcTpCd, AJE_INTFC_TP.FM_CHARGEBACK);
        ZYPEZDItemValueSetter.setValue(tMsg.ajeIntfcPk, rs.getBigDecimal(AJE_INV_ACCT_DIST_PK));
        ZYPEZDItemValueSetter.setValue(tMsg.procDt, this.batProcDate);
        ZYPEZDItemValueSetter.setValue(tMsg.jrnlCpltFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(tMsg.ajePgmId, PROGRAM_ID);
    }

    /**
     * executeInsert
     * @param dtlTMsgList List<MAN_JRNL_ENTRY_DTLTMsg>
     * @param ctrlTMsgList List<AJE_INTFC_CTRLTMsg>
     */
    private void executeInsert(List<MAN_JRNL_ENTRY_DTLTMsg> dtlTMsgList, List<AJE_INTFC_CTRLTMsg> ctrlTMsgList) {
        MAN_JRNL_ENTRY_DTLTMsg[] dtlList = new MAN_JRNL_ENTRY_DTLTMsg[dtlTMsgList.size()];
        AJE_INTFC_CTRLTMsg[] ctrlList = new AJE_INTFC_CTRLTMsg[ctrlTMsgList.size()];
        S21FastTBLAccessor.insert(dtlTMsgList.toArray(dtlList));
        S21FastTBLAccessor.insert(ctrlTMsgList.toArray(ctrlList));
        commit();
    }

    /**
     * getZYPOracleSeqManJrnlEntryHdrSq
     * @return BigDecimal
     */
    private static BigDecimal getZYPOracleSeqManJrnlEntryHdrSq() {
        return ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.MAN_JRNL_ENTRY_HDR_SQ);
    }

    /**
     * getZYPOracleSeqManJrnlEntryDtlSq
     * @return BigDecimal
     */
    private static BigDecimal getZYPOracleSeqManJrnlEntryDtlSq() {
        return ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.MAN_JRNL_ENTRY_DTL_SQ);
    }

    /**
     * convertGlPeriod
     * @param glDt String
     * @return String
     */
    private static String convertGlPeriod(String glDt) {

        String glPeriod = "";
        if (glDt != null && glDt.length() >= 6) {

            String strYYYY = glDt.substring(2, 4);
            String monthNm = glDt.substring(4, 6);
            String strYYYYMM = "-".concat(strYYYY);
            String strMM = "";

            if ("01".equals(monthNm)) {
                strMM = "JAN";
            } else if ("02".equals(monthNm)) {
                strMM = "FEB";
            } else if ("03".equals(monthNm)) {
                strMM = "MAR";
            } else if ("04".equals(monthNm)) {
                strMM = "APR";
            } else if ("05".equals(monthNm)) {
                strMM = "MAY";
            } else if ("06".equals(monthNm)) {
                strMM = "JUN";
            } else if ("07".equals(monthNm)) {
                strMM = "JUL";
            } else if ("08".equals(monthNm)) {
                strMM = "AUG";
            } else if ("09".equals(monthNm)) {
                strMM = "SEP";
            } else if ("10".equals(monthNm)) {
                strMM = "OCT";
            } else if ("11".equals(monthNm)) {
                strMM = "NOV";
            } else if ("12".equals(monthNm)) {
                strMM = "DEC";
            } else {
                strYYYYMM = "";
            }

            glPeriod = strMM.concat(strYYYYMM);
        }
        return glPeriod;
    }

    /**
     * subString
     * @param str
     * @param beginIndex
     * @param endIndex
     * @return
     */
    private String subString(String str, int beginIndex, int endIndex) {
        if (str == null) {
            return null;
        }
        if (str.length() <= (endIndex - beginIndex)) {
            return str;
        }
        return str.substring(beginIndex, endIndex);
    }

}
