/**
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NFC.NFCB054001;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import parts.dbcommon.EZDTBLAccessor;
import business.db.AR_RCPT_RCV_INFO_WRKTMsg;
import business.db.AR_RCPT_RCV_INTFCTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_BAT_INFO_LVL;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_CUST_ID_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_LOCK_BOX_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.numbering.ZYPExtnNumbering;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.integration.S21TransactionTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

/**
 * <pre>
 * NFCB054001.
 * eCheck Integration - import interface data.
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 04/13/2015   Fujitsu         S.Tsunaki       Create          N/A
 * 12/16/2015   CSAI            K.Uramori       Update          Term Code not to set WARNING
 * 11/24/2016   Fujitsu         S.Fujita        Update          QC#15742
 * 12/05/2016   Fujitsu         S.Fujita        Update          QC#16255
 * 03/09/2018   Hitachi         T.Tsuchida      Update          QC#24753
 * 2018/07/12   Fujitsu         H.Ikeda         Update          QC#26905
 * 2018/08/23   Fujitsu         H.Ikeda         Update          QC#27776
 *</pre>
 */
public class NFCB054001 extends S21BatchMain {

    /** Common component for Database accessing */
    private S21SsmBatchClient ssmBatchClient;

    /** SQL Access parts */
    private S21SsmLowLevelCodingClient ssmLLClient;

    /** Common component for Integration management record creation */
    private S21TransactionTableAccessor s21TranTblAccs;

    /** Global Company Code */
    private String glblCmpyCd;

    /** Interface ID */
    private String intfcId;

    /** Batch Process Date */
    private String batchProcDt;

    /** Batch Receipt Name */
    private String batRcptNm;

    /** ReceiptSource Code */
    private String rcptSrcCd;

    /** Payment Method Code */
    private String pmtMethCd;

    /** New Batch Receipt Name */
    private String newBatRcptNm;

    /** Total Record Count */
    private int ttlRecCnt = 0;

    /** Normal Record Count */
    private int normRecCnt = 0;

    /** Error Record Count */
    private int errRecCnt = 0;

    /** Termination Code */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** First Number */
    private static final String FIRST_NUM = "_01";

    /** Underscore */
    private static final String STR_UNDERSCORE = "_";

    /** Percent */
    private static final String STR_PERCENT = "%";

    /** Digit Check Pattern */
    private static final String CHK_PTTERN = "^[0-9]*$";

    /** AUTO_SEQ Key : RCPT_EC# */
    private static final String SEQ_RCPT_EC = "RCPT_EC#";

    /** [@] is not found */
    private static final String ZZZM9006E = "ZZZM9006E";

    /** message of [@] is mandatory. */
    private static final String ZZZM9025E = "ZZZM9025E";

    /** @ is missing.Merchant Order:@ */
    private static final String NFCM0757E = "NFCM0757E";

    // START 2018/07/11 H.Ikeda [QC#26905,ADD]
    /** The same file has already been imported. IntfcId = @, DEP_DT = @, DEP_TM = @ */
    private static final String NFCM0884E = "NFCM0884E";
    // END   2018/07/11 H.Ikeda [QC#26905,ADD]

    // START 2016/11/24 S.Fujita [QC#15742,MOD]
    /** File Record Count */
    private BigDecimal fileRecCnt;

    /** File Record Total Amount */
    private BigDecimal fileRecTotAmt;

    /** Var Char CONST_VAL : AR_LOCK_BOX_CD */
    private static final String AR_LOCK_BOX_CD = "AR_LOCK_BOX_CD";
    
    /** Default Ar Lock Box Bat Number */
    private static final String DEF_BAT_NUM = "001";
    
    /** Initial Ar Lock Box Bat Line Number */
    private static final int INIT_BAT_LINE_NUM = 1;
    
    /** Default Ar Lock Box Bat Line Sequence Number */
    private static final String DEF_BAT_LINE_SQ_NUM = "00001";
    // END   2016/11/24 S.Fujita [QC#15742,MOD]

    /**
     * Remittance amount cannot be less than zero.Receipt#:@,Amount
     * Remitted:@
     */
    private static final String NFCM0758E = "NFCM0758E";

    /**
     * It is the main method that is called from the batch shell.
     * @param args String[]
     */
    public static void main(String[] args) {
        new NFCB054001().executeBatch(NFCB054001.class.getSimpleName());
    }

    @Override
    protected void initRoutine() {
        this.ssmBatchClient = S21SsmBatchClient.getClient(NFCB054001.class);

        this.s21TranTblAccs = new S21TransactionTableAccessor();

        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

        this.glblCmpyCd = this.getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(ZZZM9025E, new String[] {"Global Company Code" });
        }

        this.batchProcDt = ZYPDateUtil.getBatProcDate();
        if (!ZYPCommonFunc.hasValue(batchProcDt)) {
            throw new S21AbendException(ZZZM9025E, new String[] {"Batch Process Date" });
        }

        this.intfcId = super.getInterfaceID();
        if (!ZYPCommonFunc.hasValue(this.intfcId)) {
            throw new S21AbendException(ZZZM9025E, new String[] {"Interface ID" });
        }

        this.batRcptNm = S21BatchMain.getUserVariable1();
        if (!ZYPCommonFunc.hasValue(this.batRcptNm)) {
            throw new S21AbendException(ZZZM9025E, new String[] {"Batch Receipt Name" });
        }

        this.rcptSrcCd = S21BatchMain.getUserVariable2();
        if (!ZYPCommonFunc.hasValue(this.rcptSrcCd)) {
            throw new S21AbendException(ZZZM9025E, new String[] {"Receipt Source Code" });
        }

        this.pmtMethCd = S21BatchMain.getUserVariable3();
        if (!ZYPCommonFunc.hasValue(this.pmtMethCd)) {
            throw new S21AbendException(ZZZM9025E, new String[] {"Payment Method Code" });
        }

        checkArRcptSrc();

    }

    @Override
    protected void mainRoutine() {
        final BigDecimal[] tranIdAry = this.s21TranTblAccs.getIntegrationRecord(this.intfcId);

        // START 2016/12/05 S.Fujita [QC#16255,ADD]
        if (tranIdAry.length != 0) {
        // END   2016/12/05 S.Fujita [QC#16255,ADD]
            for (BigDecimal tranId : tranIdAry) {

                newBatRcptNm = createArBatRcptNm();

                insertArRcptRcvIntfc(tranId);

                this.s21TranTblAccs.endIntegrationProcess(this.intfcId, tranId);

                super.commit();

                // START 2016/11/24 S.Fujita [QC#15742,MOD]
                updateArRcptRcvIntfc();
                // END   2016/11/24 S.Fujita [QC#15742,MOD]
            }
        // START 2016/12/05 S.Fujita [QC#16255,ADD]
        } else {
            updateArRcptRcvIntfc();
        }
        // END   2016/12/05 S.Fujita [QC#16255,ADD]
    }

    @Override
    protected void termRoutine() {
        super.setTermState(this.termCd, this.normRecCnt, this.errRecCnt, this.ttlRecCnt);
    }

    private void insertArRcptRcvIntfc(BigDecimal tranId) {
        PreparedStatement pst = null;
        ResultSet rs = null;
        int batLineNum = INIT_BAT_LINE_NUM;
        // START 2018/07/11 H.Ikeda [QC#26905,ADD]
        boolean errFlg = false;
        boolean firstFlg = true;
        // END   2018/07/11 H.Ikeda [QC#26905,ADD]
        try {
            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("intfcId", this.intfcId);
            ssmParam.put("tranId", tranId);

            pst = this.ssmLLClient.createPreparedStatement("getInterface", ssmParam);
            rs = pst.executeQuery();

            while (rs.next()) {
                // START 2018/07/11 H.Ikeda [QC#26905,ADD]
                if (firstFlg) {
                    errFlg = chkFile(rs.getString("ECHK_SUBMT_DT"));
                    firstFlg = false;
                }
                // END   2018/07/11 H.Ikeda [QC#26905,ADD]

                String custRcptNum = ZYPExtnNumbering.getUniqueID(SEQ_RCPT_EC);

                String pmtMeth = rs.getString("INTFC_PMT_METH_TP_TXT");
                if (!this.pmtMethCd.equals(pmtMeth)) {
                    continue;
                }

                AR_RCPT_RCV_INTFCTMsg tMsg = new AR_RCPT_RCV_INTFCTMsg();
                // START 2016/11/24 S.Fujita [QC#15742,MOD]
                tMsg = setArRcptRcvIntfc(tMsg, rs, custRcptNum, batLineNum);
                batLineNum++;
//                tMsg = setArRcptRcvIntfc(tMsg, rs, custRcptNum);
                // END   2016/11/24 S.Fujita [QC#15742,MOD]

                // START 2016/12/05 S.Fujita [QC#16255,DEL]
//                tMsg = checkData(tMsg, rs.getString("ECHK_CUST_REF_NUM"));
                // END   2016/12/05 S.Fujita [QC#16255,DEL]

                // START 2018/07/11 H.Ikeda [QC#26905,ADD]
                if (errFlg) {
                    // The same file has already been imported. IntfcId = @, DEP_DT = @, DEP_TM = @
                    tMsg = setError(tMsg, NFCM0884E, tMsg.depDt.getValue(), tMsg.depTm.getValue());
                }
                // END   2018/07/11 H.Ikeda [QC#26905,ADD]
                EZDTBLAccessor.insert(tMsg);
            }
        } catch (SQLException e) {
            super.sqlExceptionHandler(e);

        } finally {
            S21SsmLowLevelCodingClient.closeResource(pst, rs);
        }

    }

    // START 2018/07/11 H.Ikeda [QC#26905,ADD]
    private boolean chkFile(String dt) throws SQLException {
        if (ZYPCommonFunc.hasValue(dt)) {

            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", this.glblCmpyCd);
            ssmParam.put("rcvFuncId", this.intfcId);
            ssmParam.put("depDt", dt); // DEP_DT

            BigDecimal errDataCnt = (BigDecimal) ssmBatchClient.queryObject("getArErrFileDataCnt", ssmParam);
            if (errDataCnt != null && errDataCnt.compareTo(BigDecimal.ZERO) != 0) {
                return true;
            }
        }
        return false;
    }
    // END   2018/07/11 H.Ikeda [QC#26905,ADD]
    
    // START 2018/07/11 H.Ikeda [QC#26905,ADD]
    //private AR_RCPT_RCV_INTFCTMsg checkData(AR_RCPT_RCV_INTFCTMsg tMsg, String echkCustRefNum) {
    private AR_RCPT_RCV_INTFCTMsg checkData(AR_RCPT_RCV_INTFCTMsg tMsg, String echkCustRefNum, String errChkFlg) {
    // END   2018/07/11 H.Ikeda [QC#26905,ADD]
        boolean hasError = false;

        // START 2016/12/05 S.Fujita [QC#16255,ADD]
        // START 2018/07/11 H.Ikeda [QC#26905,MOD]
        if (ZYPCommonFunc.hasValue(errChkFlg) &&  ZYPConstant.FLG_ON_Y.equals(errChkFlg)) {
            ZYPEZDItemValueSetter.setValue(tMsg.arRcptRcvErrFlg, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(tMsg.arLockBoxFileErrFlg, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(tMsg.arRcptRcvErrFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(tMsg.arLockBoxFileErrFlg, ZYPConstant.FLG_OFF_N);
        }
        // END   2018/07/11 H.Ikeda [QC#26905,MOD]
        // END   2016/12/05 S.Fujita [QC#16255,MOD]

        // Mandatory
        if (!ZYPCommonFunc.hasValue(tMsg.depDt)) {
            tMsg = setError(tMsg, NFCM0757E, "Date the receipts submitted by Chase", echkCustRefNum);
            hasError = true;
        }
        if (!ZYPCommonFunc.hasValue(tMsg.custRcptAmt)) {
            tMsg = setError(tMsg, NFCM0757E, "Amount of the receipt to be applied", echkCustRefNum);
            hasError = true;
        }

        // Value Check
        if (ZYPCommonFunc.hasValue(tMsg.custRcptAmt)) {
            if (!(tMsg.custRcptAmt.getValue().compareTo(BigDecimal.ZERO) >= 0)) {
                tMsg = setError(tMsg, NFCM0758E, tMsg.custRcptNum.getValue(), tMsg.custRcptAmt.getValue().toPlainString());
                hasError = true;
            }
        } else {
            tMsg = setError(tMsg, NFCM0758E, tMsg.custRcptNum.getValue(), "null");
            hasError = true;
        }

        // START 2016/11/24 S.Fujita [QC#15742,MOD]
        // START 2018/07/11 H.Ikeda [QC#26905,MOD]
        //if (hasError) {
        if (hasError || (ZYPCommonFunc.hasValue(errChkFlg) &&  ZYPConstant.FLG_ON_Y.equals(errChkFlg))) {
        // END   2018/07/11 H.Ikeda [QC#26905,MOD]
            this.errRecCnt++;
            this.termCd = TERM_CD.WARNING_END;
        } else {
            this.normRecCnt++;
        }
//        // This is not batch error, but validation check error. Don't have to set term code to WARNING.
//        /*if (hasError) {
//            this.errRecCnt++;
//            this.termCd = TERM_CD.WARNING_END;
//        } else {
//        */
//            this.normRecCnt++;
//        //}
         // END   2016/11/24 S.Fujita [QC#15742,MOD]
        this.ttlRecCnt++;

        return tMsg;
    }

    private AR_RCPT_RCV_INTFCTMsg setArRcptRcvIntfc(AR_RCPT_RCV_INTFCTMsg tMsg, ResultSet nfci1110Rs, String custRcptNum, int batLineNum) throws SQLException {
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.arRcptRcvIntfcPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.AR_RCPT_RCV_INTFC_SQ));
        ZYPEZDItemValueSetter.setValue(tMsg.rcvFuncId, this.intfcId);
        ZYPEZDItemValueSetter.setValue(tMsg.rcvDt, this.batchProcDt);
        ZYPEZDItemValueSetter.setValue(tMsg.depDt, nfci1110Rs.getString("ECHK_SUBMT_DT"));
        ZYPEZDItemValueSetter.setValue(tMsg.depTm, "000000");
        ZYPEZDItemValueSetter.setValue(tMsg.arBatRcptNm, this.newBatRcptNm);
        // ZYPEZDItemValueSetter.setValue(tMsg.remBankRteNum, "");
        // ZYPEZDItemValueSetter.setValue(tMsg.remDsBankAcctNum, "");
        // ZYPEZDItemValueSetter.setValue(tMsg.remDsBankMicrNum, "");
        // ZYPEZDItemValueSetter.setValue(tMsg.remDsBankAcctPk, "");
        // ZYPEZDItemValueSetter.setValue(tMsg.custBankRteNum, "");
        // ZYPEZDItemValueSetter.setValue(tMsg.custDsBankAcctNum, "");
        // ZYPEZDItemValueSetter.setValue(tMsg.custDsBankMicrNum, "");
        // ZYPEZDItemValueSetter.setValue(tMsg.custDsBankAcctPk, "");

        String echkCustRefNum = nfci1110Rs.getString("ECHK_CUST_REF_NUM");
        if (echkCustRefNum.startsWith("#")) {
            ZYPEZDItemValueSetter.setValue(tMsg.custAcctRefNum, echkCustRefNum.substring(1));
        }

        // ZYPEZDItemValueSetter.setValue(tMsg.payerCustCd, "");
        // ZYPEZDItemValueSetter.setValue(tMsg.billToCustCd, "");
        ZYPEZDItemValueSetter.setValue(tMsg.custRcptNum, custRcptNum);
        ZYPEZDItemValueSetter.setValue(tMsg.vldCustRcptNum, custRcptNum);
        if (ZYPCommonFunc.hasValue(nfci1110Rs.getBigDecimal("ECHK_RCPT_AMT"))) {
            ZYPEZDItemValueSetter.setValue(tMsg.custRcptAmt, nfci1110Rs.getBigDecimal("ECHK_RCPT_AMT"));
        }
        if (!echkCustRefNum.startsWith("#")) {
            ZYPEZDItemValueSetter.setValue(tMsg.custInvNum, echkCustRefNum);
        }
        // ZYPEZDItemValueSetter.setValue(tMsg.arTrxNum, "");
        // ZYPEZDItemValueSetter.setValue(tMsg.arTrxConslNum, "");
        // ZYPEZDItemValueSetter.setValue(tMsg.invConslTpCd, "");

        if (ZYPCommonFunc.hasValue(nfci1110Rs.getBigDecimal("ECHK_RCPT_AMT"))) {
            ZYPEZDItemValueSetter.setValue(tMsg.custInvAmt, nfci1110Rs.getBigDecimal("ECHK_RCPT_AMT"));
        }
        ZYPEZDItemValueSetter.setValue(tMsg.arRcptSrcCd, this.rcptSrcCd);
        ZYPEZDItemValueSetter.setValue(tMsg.arRcptRcvErrFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(tMsg.arCustIdStsCd, AR_CUST_ID_STS.SAVED);
        ZYPEZDItemValueSetter.setValue(tMsg.arRcptRcvWrkCratFlg, ZYPConstant.FLG_OFF_N);

        // START 2016/12/05 S.Fujita [QC#16255,ADD]
        ZYPEZDItemValueSetter.setValue(tMsg.arLockBoxStsCd, AR_LOCK_BOX_STS.TEMPORARY_SAVED);
        // END   2016/12/05 S.Fujita [QC#16255,ADD]

        // START 2016/11/24 S.Fujita [QC#15742,MOD]
        ZYPEZDItemValueSetter.setValue(tMsg.arLockBoxFileNm, this.newBatRcptNm.toUpperCase());
        ZYPEZDItemValueSetter.setValue(tMsg.arLockBoxBatNum, DEF_BAT_NUM);
        ZYPEZDItemValueSetter.setValue(tMsg.arLockBoxBatLineNum, String.format("%03d", batLineNum));
        ZYPEZDItemValueSetter.setValue(tMsg.arLockBoxBatLineSqNum, DEF_BAT_LINE_SQ_NUM);
        ZYPEZDItemValueSetter.setValue(tMsg.arLockBoxCd, ZYPCodeDataUtil.getVarCharConstValue(AR_LOCK_BOX_CD, this.glblCmpyCd));
        // ZYPEZDItemValueSetter.setValue(tMsg.arLockBoxFileNm, "");
        // ZYPEZDItemValueSetter.setValue(tMsg.arLockBoxBatNum, "");
        // ZYPEZDItemValueSetter.setValue(tMsg.arLockBoxBatLineNum,
        // "");
        // ZYPEZDItemValueSetter.setValue(tMsg.arLockBoxBatLineSqNum,
        // "");
        // ZYPEZDItemValueSetter.setValue(tMsg.arLockBoxCd, "");
        // END   2016/11/24 S.Fujita [QC#15742,MOD]

        // ZYPEZDItemValueSetter.setValue(tMsg.hdrLockBoxNumTxt, "");
        // ZYPEZDItemValueSetter.setValue(tMsg.batLockBoxNumTxt, "");
        // ZYPEZDItemValueSetter.setValue(tMsg.trailLockBoxNumTxt,
        // "");
        ZYPEZDItemValueSetter.setValue(tMsg.arLockBoxFileErrFlg, ZYPConstant.FLG_OFF_N);
        // ZYPEZDItemValueSetter.setValue(tMsg.intfcFileRecCnt, "");

        // ZYPEZDItemValueSetter.setValue(tMsg.lockBoxRecCnt, "");
        // ZYPEZDItemValueSetter.setValue(tMsg.lockBoxTotAmt, "");
        // ZYPEZDItemValueSetter.setValue(tMsg.batRcptRecCnt, "");
        // ZYPEZDItemValueSetter.setValue(tMsg.batRcptTotAmt, "");

        return tMsg;
    }

    private AR_RCPT_RCV_INTFCTMsg setError(AR_RCPT_RCV_INTFCTMsg tMsg, String msgId, String val1, String val2) {

        insertArRcptRcvInfoWrk(tMsg, msgId, val1, val2);

        ZYPEZDItemValueSetter.setValue(tMsg.arRcptRcvErrFlg, ZYPConstant.FLG_ON_Y);
        // START 2016/12/05 S.Fujita [QC#16255,ADD]
        ZYPEZDItemValueSetter.setValue(tMsg.arLockBoxFileErrFlg, ZYPConstant.FLG_ON_Y);
        // END   2016/12/05 S.Fujita [QC#16255,ADD]
        return tMsg;
    }

    private void insertArRcptRcvInfoWrk(AR_RCPT_RCV_INTFCTMsg tMsg, String msgId, String val1, String val2) {

        String[] prmMsg = null;

        if (NFCM0757E.equals(msgId)) {
            prmMsg = new String[] {val1, val2 };
        } else if (NFCM0758E.equals(msgId)) {
            prmMsg = new String[] {val1, val2 };
        }
        // START 2018/07/11 H.Ikeda [QC#26905,ADD]
        else if (NFCM0884E.equals(msgId)) {
            // START 2018/08/22 H.Ikeda [QC#27776,MOD]
            //prmMsg = new String[] {val1, val2};
            prmMsg = new String[] {this.intfcId, val1, val2};
            // END   2018/08/22 H.Ikeda [QC#27776,MOD]
        }
        // END   2018/07/11 H.Ikeda [QC#26905,ADD]

        AR_RCPT_RCV_INFO_WRKTMsg insTMsg = new AR_RCPT_RCV_INFO_WRKTMsg();
        ZYPEZDItemValueSetter.setValue(insTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(insTMsg.arRcptRcvInfoWrkPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.AR_RCPT_RCV_INFO_WRK_SQ));
        ZYPEZDItemValueSetter.setValue(insTMsg.arRcptRcvIntfcPk, tMsg.arRcptRcvIntfcPk);
        ZYPEZDItemValueSetter.setValue(insTMsg.arBatInfoMsgTxt, getMessageText(msgId, prmMsg));
        ZYPEZDItemValueSetter.setValue(insTMsg.arBatInfoLvlCd, AR_BAT_INFO_LVL.ERROR);

        EZDTBLAccessor.insert(insTMsg);
    }

    private void checkArRcptSrc() {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("rcptSrcCd", this.rcptSrcCd);
        BigDecimal arRcptSrcCnt = (BigDecimal) ssmBatchClient.queryObject("getArRcptSrcCnt", ssmParam);
        if (arRcptSrcCnt == null || arRcptSrcCnt.compareTo(BigDecimal.ONE) != 0) {
            throw new S21AbendException(ZZZM9006E, new String[] {"Receipt Source Code" });
        }
    }

    private String createArBatRcptNm() {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        // START 2018/03/09 T.Tsuchida [QC#24753,MOD]
        //ssmParam.put("batRcptNm", this.batRcptNm + STR_PERCENT);
        ssmParam.put("batRcptNm", this.batRcptNm + this.batchProcDt + STR_PERCENT);
        // END 2018/03/09 T.Tsuchida [QC#24753,MOD]
        List<String> queryObjectList = (List<String>) ssmBatchClient.queryObjectList("getArBatRcptNm", ssmParam);
        return createArBatRcptNm(queryObjectList);
    }

    private String createArBatRcptNm(List<String> valLsit) {

        if (valLsit == null || valLsit.isEmpty()) {
            return this.batRcptNm + this.batchProcDt + FIRST_NUM;
        }

        int maxIndex = 0;
        String[] arLockBoxFileNmArray;
        String index = "";
        String existFileNm = "";
        Pattern p = Pattern.compile(CHK_PTTERN);
        for (String arLockBoxFileNm : valLsit) {
            arLockBoxFileNmArray = arLockBoxFileNm.split(STR_UNDERSCORE);
            index = arLockBoxFileNmArray[arLockBoxFileNmArray.length - 1];
            if (p.matcher(index).find()) {
                if (maxIndex < Integer.valueOf(index)) {
                    maxIndex = Integer.valueOf(index);
                }
                if (!ZYPCommonFunc.hasValue(existFileNm)) {
                    StringBuilder str = new StringBuilder();
                    for (int i = 0; i < arLockBoxFileNmArray.length - 1; i++) {
                        if (i != 0) {
                            str.append(STR_UNDERSCORE);
                        }
                        str.append(arLockBoxFileNmArray[i]);
                    }
                    existFileNm = str.toString();
                }
            } else {
                existFileNm = arLockBoxFileNm;
            }
        }

        if (maxIndex == 0) {
            return existFileNm + FIRST_NUM;
        } else {
            maxIndex++;
            return existFileNm + STR_UNDERSCORE + ZYPCommonFunc.leftPad(String.valueOf(maxIndex), 2, BigDecimal.ZERO.toString());
        }
    }

    /**
     * Get Message Text.
     * @param messageId MessageID
     * @return message text
     */
    private String getMessageText(String messageId, String[] prm) {
        return S21MessageFunc.clspGetMessage(messageId, prm);
    }

    // START 2016/11/24 S.Fujita [QC#15742,ADD]
    private void setFileRecCntAndTotAmt(String arLockBoxFileNm) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("intfcId", this.intfcId);
        // START 2016/12/05 S.Fujita [QC#16255,MOD]
//        ssmParam.put("arLockBoxFileNm", this.newBatRcptNm.toUpperCase());
        ssmParam.put("arLockBoxFileNm", arLockBoxFileNm);
        // END   2016/12/05 S.Fujita [QC#16255,MOD]

        Map<String, Object> eCheckInfo = (Map<String, Object>) ssmBatchClient.queryObject("getFileRecCntAndTotAmt", ssmParam);
        if (eCheckInfo != null) {
            this.fileRecCnt = nvl(((BigDecimal) eCheckInfo.get("REC_CNT")));
            this.fileRecTotAmt = nvl(((BigDecimal) eCheckInfo.get("REC_TOT_AMT")));
        }
    }

    private BigDecimal nvl(BigDecimal val) {
        if (val == null) {
            return BigDecimal.ZERO;
        }
        return val;
    }

    private void updateArRcptRcvIntfc() {
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", this.glblCmpyCd);
            ssmParam.put("intfcId", this.intfcId);
            // START 2016/12/05 S.Fujita [QC#16255,MOD]
//            ssmParam.put("arLockBoxFileNm", this.newBatRcptNm.toUpperCase());
            ssmParam.put("saved", AR_LOCK_BOX_STS.TEMPORARY_SAVED);
            ssmParam.put("reprocess", AR_LOCK_BOX_STS.REPROCESS);
            // END   2016/12/05 S.Fujita [QC#16255,MOD]

            pst = this.ssmLLClient.createPreparedStatement("getArRcptRcvIntfc", ssmParam);
            rs = pst.executeQuery();

            String preArLockBoxFileNm = "";
            while (rs.next()) {

                BigDecimal arRcptRcvIntfcPk = rs.getBigDecimal("AR_RCPT_RCV_INTFC_PK");
                // START 2016/12/05 S.Fujita [QC#16255,ADD]
                deleteArRcptRcvInfoWrk(arRcptRcvIntfcPk);
                String arLockBoxFileNm = rs.getString("AR_LOCK_BOX_FILE_NM");
                if (!preArLockBoxFileNm.equals(arLockBoxFileNm)) {
                    setFileRecCntAndTotAmt(arLockBoxFileNm);
                }
                preArLockBoxFileNm = arLockBoxFileNm;
                // END   2016/12/05 S.Fujita [QC#16255,ADD]

                AR_RCPT_RCV_INTFCTMsg tMsg = new AR_RCPT_RCV_INTFCTMsg();
                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(tMsg.arRcptRcvIntfcPk, arRcptRcvIntfcPk);
                AR_RCPT_RCV_INTFCTMsg updMsg = (AR_RCPT_RCV_INTFCTMsg) EZDTBLAccessor.findByKeyForUpdate(tMsg);

                ZYPEZDItemValueSetter.setValue(updMsg.lockBoxRecCnt, this.fileRecCnt);
                ZYPEZDItemValueSetter.setValue(updMsg.lockBoxTotAmt, this.fileRecTotAmt);
                ZYPEZDItemValueSetter.setValue(updMsg.batRcptRecCnt, this.fileRecCnt);
                ZYPEZDItemValueSetter.setValue(updMsg.batRcptTotAmt, this.fileRecTotAmt);

                // START 2016/12/05 S.Fujita [QC#16255,ADD]
                String echkCustRefNum = setEchkCustRefNum(rs.getString("CUST_ACCT_REF_NUM"), rs.getString("CUST_INV_NUM"));
                // START 2018/07/11 H.Ikeda [QC#26905,MOD]
                //updMsg = checkData(updMsg, echkCustRefNum);
                updMsg = checkData(updMsg, echkCustRefNum, rs.getString("AR_RCPT_RCV_ERR_FLG"));
                // END   2018/07/11 H.Ikeda [QC#26905,MOD]
                // END   2016/12/05 S.Fujita [QC#16255,ADD]

                if (errCheck(updMsg)) {
                    ZYPEZDItemValueSetter.setValue(updMsg.arLockBoxStsCd, AR_LOCK_BOX_STS.ERROR);
                } else {
                    ZYPEZDItemValueSetter.setValue(updMsg.arLockBoxStsCd, AR_LOCK_BOX_STS.WORK_IN_PROGRESS);
                }
                EZDTBLAccessor.update(updMsg);
                commit();
            }
        } catch (SQLException e) {
            super.sqlExceptionHandler(e);

        } finally {
            S21SsmLowLevelCodingClient.closeResource(pst, rs);
        }
    }

    private boolean errCheck(AR_RCPT_RCV_INTFCTMsg tMsg) {
        if (ZYPConstant.FLG_ON_Y.equals(tMsg.arLockBoxFileErrFlg.getValue()) //
                || ZYPConstant.FLG_ON_Y.equals(tMsg.arRcptRcvErrFlg.getValue())) {
            return true;
        }
        return false;
    }
    // END   2016/11/24 S.Fujita [QC#15742,ADD]

    // START 2016/12/05 S.Fujita [QC#16255,ADD]
    private void deleteArRcptRcvInfoWrk(BigDecimal arRcptRcvIntfcPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("arRcptRcvIntfcPk", arRcptRcvIntfcPk);
        // START 2018/08/22 H.Ikeda [QC#27776,ADD]
        ssmParam.put("errMsgid", NFCM0884E);
        // END   2018/08/22 H.Ikeda [QC#27776,ADD]
        List<BigDecimal> arRcptRcvInfoWrkPkList = (List<BigDecimal>) ssmBatchClient.queryObjectList("getArRcptRcvInfoWrk", ssmParam);
        if (arRcptRcvInfoWrkPkList.isEmpty()) {
            return;
        }

        AR_RCPT_RCV_INFO_WRKTMsg inMsg = new AR_RCPT_RCV_INFO_WRKTMsg();
        for (BigDecimal arRcptRcvInfoWrkPk : arRcptRcvInfoWrkPkList) {
            inMsg.clear();
            ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(inMsg.arRcptRcvInfoWrkPk, arRcptRcvInfoWrkPk);

            EZDTBLAccessor.logicalRemove(inMsg);
        }
    }

    private String setEchkCustRefNum(String custAcctRefNum, String custInvNum) {
        StringBuilder str = new StringBuilder();
        if (ZYPCommonFunc.hasValue(custAcctRefNum)) {
            str.append("#");
            str.append(custAcctRefNum);
            return str.toString();
        } else {
            return custInvNum;
        }
    }
    // END   2016/12/05 S.Fujita [QC#16255,ADD]
}
