/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NFC.NFCB058001;

import static com.canon.cusa.s21.batch.NFC.NFCB058001.constant.NFCB058001Constant.API_ERR_CASH_APPLICATION_ERROR;
import static com.canon.cusa.s21.batch.NFC.NFCB058001.constant.NFCB058001Constant.API_ERR_EXCLUSION_ERROR;
import static com.canon.cusa.s21.batch.NFC.NFCB058001.constant.NFCB058001Constant.API_ERR_OTHER_ERROR;
import static com.canon.cusa.s21.batch.NFC.NFCB058001.constant.NFCB058001Constant.API_ERR_UNPROCESSING;
import static com.canon.cusa.s21.batch.NFC.NFCB058001.constant.NFCB058001Constant.APPLY_RTNCD_CASHAPP_ERROR;
import static com.canon.cusa.s21.batch.NFC.NFCB058001.constant.NFCB058001Constant.APPLY_RTNCD_EXCLUSION_ERROR;
import static com.canon.cusa.s21.batch.NFC.NFCB058001.constant.NFCB058001Constant.APPLY_RTNCD_NORMAL;
import static com.canon.cusa.s21.batch.NFC.NFCB058001.constant.NFCB058001Constant.APPLY_RTNCD_OTHERS_ERROR;
import static com.canon.cusa.s21.batch.NFC.NFCB058001.constant.NFCB058001Constant.APPLY_RTNCD_UN_PROCCES;
import static com.canon.cusa.s21.batch.NFC.NFCB058001.constant.NFCB058001Constant.AR_APPLY_INTFC_WRK;
import static com.canon.cusa.s21.batch.NFC.NFCB058001.constant.NFCB058001Constant.AR_BAT_USR_ID;
import static com.canon.cusa.s21.batch.NFC.NFCB058001.constant.NFCB058001Constant.AR_PUR_RCPT_BAT_NUM;
import static com.canon.cusa.s21.batch.NFC.NFCB058001.constant.NFCB058001Constant.AR_PUR_RCPT_TP_CD;
import static com.canon.cusa.s21.batch.NFC.NFCB058001.constant.NFCB058001Constant.AR_PUR_RCPT_TRX_TP_CD;
import static com.canon.cusa.s21.batch.NFC.NFCB058001.constant.NFCB058001Constant.AR_RCPT;
import static com.canon.cusa.s21.batch.NFC.NFCB058001.constant.NFCB058001Constant.AR_RCPT_BAT_SQ_NUM;
import static com.canon.cusa.s21.batch.NFC.NFCB058001.constant.NFCB058001Constant.AR_RCPT_CHK_NUM;
import static com.canon.cusa.s21.batch.NFC.NFCB058001.constant.NFCB058001Constant.AR_RCPT_DTL;
import static com.canon.cusa.s21.batch.NFC.NFCB058001.constant.NFCB058001Constant.AR_RCPT_PROD_CD;
import static com.canon.cusa.s21.batch.NFC.NFCB058001.constant.NFCB058001Constant.AR_RCPT_TOC_CD;
import static com.canon.cusa.s21.batch.NFC.NFCB058001.constant.NFCB058001Constant.AR_SUB_SYS_ID;
import static com.canon.cusa.s21.batch.NFC.NFCB058001.constant.NFCB058001Constant.AR_TRX_BAL;
import static com.canon.cusa.s21.batch.NFC.NFCB058001.constant.NFCB058001Constant.AR_TRX_BAL_SQ_RTNCD_NORMAL;
import static com.canon.cusa.s21.batch.NFC.NFCB058001.constant.NFCB058001Constant.AR_TRX_BAL_SQ_START_NUM;
import static com.canon.cusa.s21.batch.NFC.NFCB058001.constant.NFCB058001Constant.BIZAPL_RCPTNUMKEY;
import static com.canon.cusa.s21.batch.NFC.NFCB058001.constant.NFCB058001Constant.BLANK;
import static com.canon.cusa.s21.batch.NFC.NFCB058001.constant.NFCB058001Constant.BUSINESS_ID;
import static com.canon.cusa.s21.batch.NFC.NFCB058001.constant.NFCB058001Constant.DEAL_SQ_DTL_NUM;
import static com.canon.cusa.s21.batch.NFC.NFCB058001.constant.NFCB058001Constant.DEAL_SQ_NUM;
import static com.canon.cusa.s21.batch.NFC.NFCB058001.constant.NFCB058001Constant.EXTERNAL;
import static com.canon.cusa.s21.batch.NFC.NFCB058001.constant.NFCB058001Constant.FETCH_SIZE;
import static com.canon.cusa.s21.batch.NFC.NFCB058001.constant.NFCB058001Constant.INTERNAL;
import static com.canon.cusa.s21.batch.NFC.NFCB058001.constant.NFCB058001Constant.LINE_FEED_CODE;
import static com.canon.cusa.s21.batch.NFC.NFCB058001.constant.NFCB058001Constant.MAIL_GRP_ID_FROM;
import static com.canon.cusa.s21.batch.NFC.NFCB058001.constant.NFCB058001Constant.MAIL_GRP_ID_TO;
import static com.canon.cusa.s21.batch.NFC.NFCB058001.constant.NFCB058001Constant.MAIL_KEY_1_TO;
import static com.canon.cusa.s21.batch.NFC.NFCB058001.constant.NFCB058001Constant.MAIL_NFCB0580M001;
import static com.canon.cusa.s21.batch.NFC.NFCB058001.constant.NFCB058001Constant.MODE_APPLY;
import static com.canon.cusa.s21.batch.NFC.NFCB058001.constant.NFCB058001Constant.MODE_RECEIPT;
import static com.canon.cusa.s21.batch.NFC.NFCB058001.constant.NFCB058001Constant.NFCM0002E;
import static com.canon.cusa.s21.batch.NFC.NFCB058001.constant.NFCB058001Constant.NFCM0079E;
import static com.canon.cusa.s21.batch.NFC.NFCB058001.constant.NFCB058001Constant.NFCM0531E;
import static com.canon.cusa.s21.batch.NFC.NFCB058001.constant.NFCB058001Constant.NFCM0616E;
import static com.canon.cusa.s21.batch.NFC.NFCB058001.constant.NFCB058001Constant.NFCM0619W;
import static com.canon.cusa.s21.batch.NFC.NFCB058001.constant.NFCB058001Constant.NFCM0809E;
import static com.canon.cusa.s21.batch.NFC.NFCB058001.constant.NFCB058001Constant.NFCM0811E;
import static com.canon.cusa.s21.batch.NFC.NFCB058001.constant.NFCB058001Constant.NFCM0812E;
import static com.canon.cusa.s21.batch.NFC.NFCB058001.constant.NFCB058001Constant.NFCM0813E;
import static com.canon.cusa.s21.batch.NFC.NFCB058001.constant.NFCB058001Constant.ORIGINAL;
import static com.canon.cusa.s21.batch.NFC.NFCB058001.constant.NFCB058001Constant.PROC_TP_CD_NEW;
import static com.canon.cusa.s21.batch.NFC.NFCB058001.constant.NFCB058001Constant.REBILL;
import static com.canon.cusa.s21.batch.NFC.NFCB058001.constant.NFCB058001Constant.RECEIPT;
import static com.canon.cusa.s21.batch.NFC.NFCB058001.constant.NFCB058001Constant.TYPE_TIME_STAMP;
import static com.canon.cusa.s21.batch.NFC.NFCB058001.constant.NFCB058001Constant.VIEW_SCALE;
import static com.canon.cusa.s21.batch.NFC.NFCB058001.constant.NFCB058001Constant.ZERO;
import static com.canon.cusa.s21.batch.NFC.NFCB058001.constant.NFCB058001Constant.YEAR;
import static com.canon.cusa.s21.batch.NFC.NFCB058001.constant.NFCB058001Constant.MONTH;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.ACCT_DLY_ACTL_EXCH_RATESTMsg;
import business.db.AR_APPLY_INTFC_WRKTMsg;
import business.db.AR_RCPTTMsg;
import business.db.AR_RCPT_DTLTMsg;
import business.db.AR_TRX_BALTMsg;
import business.db.CUST_CR_PRFLTMsg;
import business.db.DS_ACCT_CR_PRFLTMsg;
import business.db.GLBL_CMPYTMsg;
import business.parts.NFZC202001PMsg;
import business.parts.NFZC301001PMsg;

import com.canon.cusa.s21.api.NFC.NFZC202001.NFZC202001;
import com.canon.cusa.s21.api.NFC.NFZC301001.NFZC301001;
import com.canon.cusa.s21.common.NFX.NFXC306001.NFCNumbering;
import com.canon.cusa.s21.common.NFX.NFXC306001.NFXC3060Bean;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_CASH_APPLY_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_CRAT_METH_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_RCPT_SRC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_RCPT_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SYS_SRC;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.numbering.ZYPNumbering;
import com.canon.cusa.s21.framework.api.S21ApiInterface;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.batch.S21BatchUtil;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;
/**
 * <pre>
 * NFCB0580:Auto Cash Apply for Credit Memo
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/04   Fujitsu         S.Fujita        Create          N/A
 * 2016/04/11   Fujitsu         S.Fujita        Update          QC#6826
 * 2016/07/27   Fujitsu         S.Fujita        Update          QC#12476
 * 2016/07/29   Fujitsu         S.Fujita        Update          QC#12680
 * 2016/08/02   Fujitsu         S.Fujita        Update          QC#12756
 * 2016/12/19   Fujitsu         T.Murai         Update          QC#16520
 * 2018/04/19   Fujitsu         R.Nakamura      Update          QC#25517
 * 2018/09/20   Fujitsu         T.Ogura         Update          QC#28097
 *</pre>
 */
public class NFCB058001 extends S21BatchMain {

    /** Termination Code of Execution Process */
    private TERM_CD termCd = null;

    /** SSM-Client*/
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** SSM Cursor Parameter */
    private S21SsmExecutionParameter execParam;

    /** SSM-Batch-Client*/
    private S21SsmBatchClient ssmBatchClient = null;

    /** Global Company Code */
    private String glblCmpyCd = null;
    /** Batch Process Date */
    private String batProcDate = null;
    /** Counter of Records: Error */
    private int procCnt = 0;
    /** Counter of Records: Successful */
    private int normalCnt = 0;
    /** Error count */
    private int errCnt = 0;
    /** Time Stamp */
    private String sysTimeStamp = null;
    /** Standard Currency Code */
    private String stdCcyCd = null;

    /** VarChar Constant of batUsrId */
    private String batUsrId = null;
    /** VarChar Constant of rcptBatNum */
    private String rcptBatNum = null;
    /** VarChar Constant of rcptTpCd */
    private String rcptTpCd = null;
    /** VarChar Constant of rcptTrxTpCd */
    private String rcptTrxTpCd = null;
    /** VarChar Constant of rcptProdCd */
    private String rcptProdCd = null;
    /** VarChar Constant of rcptTocCd */
    private String rcptTocCd = null;
    /** VarChar Constant of subSysId */
    private String subSysId = null;

    /** Deal Remaining Balance Gross Amount for Credit Memo */
    private BigDecimal crmAmt = BigDecimal.ZERO;
    /** Deal Remaining Balance Gross Amount for Original Invoice */
    private BigDecimal orgInvAmt = BigDecimal.ZERO;
    /** Deal Remaining Balance Gross Amount for Rebill Invoice */
    private BigDecimal rebInvAmt = BigDecimal.ZERO;
    /** Transaction Date Check Flag */
    private String flgTrnDtChk = null;
    /** Original Invoice Apply Flag */
    private String flgOrgInvApp = null;
    /** Rebilll Invoice Apply Flag */
    private String flgRebInvApp = null;

    /** WrkTMsgList of Credit Memo */
    private List<AR_APPLY_INTFC_WRKTMsg> crmIntfcWrkTMsgList = new ArrayList<AR_APPLY_INTFC_WRKTMsg>();
    /** WrkTMsgList of Original Invoice */
    private List<AR_APPLY_INTFC_WRKTMsg> orgInvIntfcWrkTMsgList = new ArrayList<AR_APPLY_INTFC_WRKTMsg>();
    /** WrkTMsgList of Rebill Invoice */
    private List<AR_APPLY_INTFC_WRKTMsg> rebInvIntfcWrkTMsgList = new ArrayList<AR_APPLY_INTFC_WRKTMsg>();

    /** Mail */
    /** Error Mail Count */
    private static int errMlCnt = 0;
    /** Mail Message Information */
    private StringBuilder mailMsgInfo = null;
    /** Err Rebill Information */
    private String errRebInfo = null;

    /**
     * @param args String[]
     */
    public static void main(String[] args) {
        new NFCB058001().executeBatch(NFCB058001.class.getSimpleName());
    }

    /**
     * Initial processing
     */
    @Override
    protected void initRoutine() {
        // Initialize Variables
        termCd = TERM_CD.NORMAL_END;
        // Initialize ssmLLClient
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        // Getting Global Company Code
        this.glblCmpyCd = getGlobalCompanyCode();
        // Getting Batch Process Date
        this.batProcDate = ZYPDateUtil.getBatProcDate(this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.batProcDate)) {
            execAbendException(NFCM0531E, new String[] {"Batch Operation Date"});
        }
        // Getting Time Stamp
        this.sysTimeStamp = ZYPDateUtil.getCurrentSystemTime(TYPE_TIME_STAMP);
        if (!ZYPCommonFunc.hasValue(this.sysTimeStamp)) {
            execAbendException(NFCM0531E, new String[] {"Time Stamp"});
        }
        // Getting VarChar Constant
        this.batUsrId = ZYPCodeDataUtil.getVarCharConstValue(AR_BAT_USR_ID, this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.batUsrId)) {
            execAbendException(NFCM0531E, new String[] {"User ID for Batch Process of AR System" });
        }
        this.rcptBatNum = ZYPCodeDataUtil.getVarCharConstValue(AR_PUR_RCPT_BAT_NUM, this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.rcptBatNum)) {
            execAbendException(NFCM0531E, new String[] {"Purge Offset  Receipt Batch Number" });
        }
        this.rcptTpCd = ZYPCodeDataUtil.getVarCharConstValue(AR_PUR_RCPT_TP_CD, this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.rcptTpCd)) {
            execAbendException(NFCM0531E, new String[] {"Purge Offset  Receipt Type Code" });
        }
        this.rcptTrxTpCd = ZYPCodeDataUtil.getVarCharConstValue(AR_PUR_RCPT_TRX_TP_CD, this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.rcptTrxTpCd)) {
            execAbendException(NFCM0531E, new String[] {"Purge Offset  Receipt Transaction Type Code" });
        }
        this.rcptProdCd = ZYPCodeDataUtil.getVarCharConstValue(AR_RCPT_PROD_CD, this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.rcptProdCd)) {
            execAbendException(NFCM0531E, new String[] {"Receipt Product Code" });
        }
        this.rcptTocCd = ZYPCodeDataUtil.getVarCharConstValue(AR_RCPT_TOC_CD, this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.rcptTocCd)) {
            execAbendException(NFCM0531E, new String[] {"Receipt TOC Code" });
        }
        this.subSysId = ZYPCodeDataUtil.getVarCharConstValue(AR_SUB_SYS_ID, this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.subSysId)) {
            execAbendException(NFCM0531E, new String[] {"Sub system ID of AR System" });
        }
        // Get Standard Currency Code
        GLBL_CMPYTMsg glblCmpyT = getStdCcyCd();
        if (glblCmpyT == null) {
            execAbendException(NFCM0531E, new String[] {"Standard Currency Code"});
        }
        this.stdCcyCd = glblCmpyT.stdCcyCd.getValue();
        // Get parameter
        this.flgTrnDtChk = S21BatchUtil.getUserVariable1();
        if (!ZYPCommonFunc.hasValue(this.flgTrnDtChk)) {
            this.flgTrnDtChk = ZYPConstant.FLG_ON_Y;
        }
        // Set SSM Cursor Parameter
        this.execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(FETCH_SIZE);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
    }

    /**
     * Main processing
     */
    @Override
    protected void mainRoutine() {
        try {
            //Purge Process
            autoCashApply();
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }
        //Error Mail Send Process
        eMailSend();
    }

    /**
     * Termination
     */
    @Override
    protected void termRoutine() {
        if (errCnt > 0) {
            termCd = TERM_CD.WARNING_END;
        }
        setTermState(termCd, this.normalCnt, this.errCnt, this.procCnt);
    }

    /**
     * Auto Cash Apply
     * @throws SQLException
     */
    private void autoCashApply() throws SQLException {

        // START 2016/07/27 S.Fujita [QC#12476,DEL]
//        this.crmAmt = BigDecimal.ZERO;
//        this.orgInvAmt = BigDecimal.ZERO;
//        this.rebInvAmt = BigDecimal.ZERO;
//        this.crmIntfcWrkTMsgList.clear();
//        this.orgInvIntfcWrkTMsgList.clear();
//        this.rebInvIntfcWrkTMsgList.clear();
        // END   2016/07/27 S.Fujita [QC#12476,DEL]
        //----------------------------------------------------
        // Credit Memo List
        //----------------------------------------------------
        PreparedStatement stmtSelect = null;
        ResultSet rsCrm = null;
        try {

            Map<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put("glblCmpyCd", this.glblCmpyCd);
            queryParam.put("arTrxTpCrm", AR_TRX_TP.CREDIT_MEMO);
            queryParam.put("arCashAppStsUnApp", AR_CASH_APPLY_STS.UNAPPLIED);
            queryParam.put("arCashAppStsPart", AR_CASH_APPLY_STS.PARTIAL);
            queryParam.put("batProcDate", batProcDate);
            queryParam.put("flgTrnDtChk", this.flgTrnDtChk);

            stmtSelect = this.ssmLLClient.createPreparedStatement("getCreditMemo", queryParam, execParam);
            rsCrm = stmtSelect.executeQuery();

            //----------------------------------------------------
            // Credit Memo Loop
            //----------------------------------------------------
            while (rsCrm.next()) {
                // START 2016/07/27 S.Fujita [QC#12476,ADD]
                this.crmAmt = BigDecimal.ZERO;
                this.orgInvAmt = BigDecimal.ZERO;
                this.rebInvAmt = BigDecimal.ZERO;
                this.crmIntfcWrkTMsgList.clear();
                this.orgInvIntfcWrkTMsgList.clear();
                this.rebInvIntfcWrkTMsgList.clear();
                // END   2016/07/27 S.Fujita [QC#12476,ADD]

                this.procCnt++;
                String arTrxNum = checkNull(rsCrm.getString("AR_TRX_NUM"));
                String orgInvNum = checkNull(rsCrm.getString("ORIG_INV_NUM"));
                String custTktNum = checkNull(rsCrm.getString("CUST_CARE_TKT_NUM"));
                String arTrxDtCrm = checkNull(rsCrm.getString("AR_TRX_DT"));
                String arAutoPurOfFlg = checkNull(rsCrm.getString("AR_AUTO_PURGE_OFS_FLG"));
                String crRebRsnCatCd = checkNull(rsCrm.getString("CR_REBIL_RSN_CATG_CD"));
                // START 2016/07/29 S.Fujita [QC#12680,ADD]
                String sysSrcCd = checkNull(rsCrm.getString("SYS_SRC_CD"));
                // END   2016/07/29 S.Fujita [QC#12680,ADD]
                setIntrfaceWrkInfoList(rsCrm);
                //----------------------------------------------------
                // Original Invoice List
                //----------------------------------------------------
                AR_TRX_BALTMsg tMsg = getOrgInv(orgInvNum);
                if (tMsg == null) {
                    errCnt++;
                    errMlCnt++;
                    String[] str = {"Original Invoice" };
                    createMailMsgInfo(arTrxNum, orgInvNum, null, APPLY_RTNCD_OTHERS_ERROR, API_ERR_OTHER_ERROR, NFCM0619W, str);
                    continue;
                }
                String arTrxDtOrg = checkNull(tMsg.arTrxDt.getValue());
                String billToCustCd = checkNull(tMsg.billToCustCd.getValue());
                String billToCustAcctCd = checkNull(tMsg.billToCustAcctCd.getValue());
                String dealCcyCd = checkNull(tMsg.dealCcyCd.getValue());
                String arCashAppStsCd = checkNull(tMsg.arCashApplyStsCd.getValue());
                setIntrfaceWrkInfoOrgList(tMsg, ORIGINAL, dealCcyCd);
                //----------------------------------------------------
                // Adopt Cash Apply Transaction
                //----------------------------------------------------
                // START 2016/07/29 S.Fujita [QC#12680,MOD]
//                if (!setCashAppPtn(arAutoPurOfFlg, crRebRsnCatCd, arCashAppStsCd)) {
                if (!setCashAppPtn(arAutoPurOfFlg, crRebRsnCatCd, arCashAppStsCd, sysSrcCd)) {
                // END   2016/07/29 S.Fujita [QC#12680,MOD]
                    continue;
                }
                //----------------------------------------------------
                // Rebill Invoice List
                //----------------------------------------------------
                // Credit Memo Only
                List<AR_TRX_BALTMsg> resultList = getRebList(orgInvNum, custTktNum);

                if (resultList.isEmpty() && ZYPConstant.FLG_OFF_N.equals(this.flgOrgInvApp)) {
                    continue;
                }
                for (AR_TRX_BALTMsg arTrxBalT : resultList) {
                    setIntrfaceWrkInfoOrgList(arTrxBalT, REBILL, dealCcyCd);
                }
                //----------------------------------------------------
                // Get Error Rebill Invoice Number
                //----------------------------------------------------
                getErrRebInvNum();
                //----------------------------------------------------
                // Deposit Date Check
                //----------------------------------------------------
                if (!depositDateCheck(arTrxDtCrm, arTrxDtOrg)) {
                    errCnt++;
                    errMlCnt++;
                    createMailMsgInfo(arTrxNum, orgInvNum, this.errRebInfo, APPLY_RTNCD_OTHERS_ERROR, API_ERR_OTHER_ERROR, NFCM0809E, null);
                    continue;
                }

                String crMgrPsnCd = null; // ADD 2016/12/19 T.Murai [QC#16520]
                // START 2016/12/19 T.Murai [QC#16520,DEL]
//                //----------------------------------------------------
//                // Credit Analyst Code Check
//                //----------------------------------------------------
//                String crMgrPsnCd = creAnalysCdCheck(billToCustCd, billToCustAcctCd);
//                if (!ZYPCommonFunc.hasValue(crMgrPsnCd)) {
//                    errCnt++;
//                    errMlCnt++;
//                    String[] str = {billToCustCd, billToCustAcctCd};
//                    createMailMsgInfo(arTrxNum, orgInvNum, this.errRebInfo, APPLY_RTNCD_OTHERS_ERROR, API_ERR_OTHER_ERROR, NFCM0810E, str);
//                    continue;
//                }
                // END   2016/12/19 T.Murai [QC#16520,DEL]

                //----------------------------------------------------
                // Get Exchange Rate
                //---------------------------------------------------
                BigDecimal actlExchRate = getActlExchRate(dealCcyCd);
                if (!ZYPCommonFunc.hasValue(actlExchRate)) {
                    errCnt++;
                    errMlCnt++;
                    String[] str = {dealCcyCd, this.batProcDate};
                    createMailMsgInfo(arTrxNum, orgInvNum, this.errRebInfo, APPLY_RTNCD_OTHERS_ERROR, API_ERR_OTHER_ERROR, NFCM0811E, str);
                    continue;
                }
                //----------------------------------------------------
                // Apply Amount Check
                //----------------------------------------------------
                applyAmountCheck();

                //----------------------------------------------------
                // create Apply info
                //----------------------------------------------------
                if (createApplyInfo(arTrxNum, orgInvNum, billToCustCd, billToCustAcctCd, crMgrPsnCd, dealCcyCd, actlExchRate)) {
                    commit();
                    this.normalCnt++;
                } else {
                    rollback();
                }
            }
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rsCrm);
        }
    }

    /**
     * Get Original Invoice
     * @param orgInvNum String
     * @return tMsg AR_TRX_BALTMsg
     */
    private AR_TRX_BALTMsg getOrgInv(String orgInvNum) {

        Map<String, Object> queryParamOrg = new HashMap<String, Object>();
        queryParamOrg.put("glblCmpyCd", this.glblCmpyCd);
        queryParamOrg.put("orgInvNum", orgInvNum);
        queryParamOrg.put("arTrxTpRcp", AR_TRX_TP.RECEIPT);

        return (AR_TRX_BALTMsg) this.ssmBatchClient.queryObject("getOrgInv", queryParamOrg);
    }

    /**
     * Get Rebill Invoice List
     * @param orgInvNum String
     * @param custTktNum String
     * @return rsReb ResultSet
     */
    private List<AR_TRX_BALTMsg> getRebList(String orgInvNum, String custTktNum) {

        Map<String, Object> queryParamReb = new HashMap<String, Object>();
        queryParamReb.put("glblCmpyCd", this.glblCmpyCd);
        queryParamReb.put("orgInvNum", orgInvNum);
        // START 2016/08/02 S.Fujita [QC#12756,DEL]
//        queryParamReb.put("custTktNum", custTktNum);
        // END   2016/08/02 S.Fujita [QC#12756,DEL]
        queryParamReb.put("arTrxTpRcp", AR_TRX_TP.RECEIPT);
        queryParamReb.put("arTrxTpCrm", AR_TRX_TP.CREDIT_MEMO);
        queryParamReb.put("arCashAppStsUnApp", AR_CASH_APPLY_STS.UNAPPLIED);
        queryParamReb.put("arCashAppStsPart", AR_CASH_APPLY_STS.PARTIAL);

        return (List<AR_TRX_BALTMsg>) this.ssmBatchClient.queryObjectList("getRebill", queryParamReb);
    }

    /**
     * Set IntrfaceWrkInfoList
     * @param rs
     * @throws SQLException
     */
    private void setIntrfaceWrkInfoList(ResultSet rs) throws SQLException {

        AR_APPLY_INTFC_WRKTMsg intfcWrkTMsg = new AR_APPLY_INTFC_WRKTMsg();

        ZYPEZDItemValueSetter.setValue(intfcWrkTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(intfcWrkTMsg.applyGrpKey, BUSINESS_ID);
        ZYPEZDItemValueSetter.setValue(intfcWrkTMsg.applyGrpSubPk, BigDecimal.ONE);
        ZYPEZDItemValueSetter.setValue(intfcWrkTMsg.bizAppId, BUSINESS_ID);
        ZYPEZDItemValueSetter.setValue(intfcWrkTMsg.upldCsvRqstPk, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(intfcWrkTMsg.procTpCd, PROC_TP_CD_NEW);
        ZYPEZDItemValueSetter.setValue(intfcWrkTMsg.dealSqNum, DEAL_SQ_NUM);
        ZYPEZDItemValueSetter.setValue(intfcWrkTMsg.dealSqDtlNum, DEAL_SQ_DTL_NUM);
        ZYPEZDItemValueSetter.setValue(intfcWrkTMsg.procStsCd, PROC_STS.IN_COMPLETED);
        ZYPEZDItemValueSetter.setValue(intfcWrkTMsg.usrId, this.batUsrId);
        ZYPEZDItemValueSetter.setValue(intfcWrkTMsg.rcptNum, ZERO);
        ZYPEZDItemValueSetter.setValue(intfcWrkTMsg.rcptInProcSqPk, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(intfcWrkTMsg.rcptGlDt, rs.getString("AR_TRX_DT"));
        ZYPEZDItemValueSetter.setValue(intfcWrkTMsg.payerCustCd, rs.getString("BILL_TO_CUST_CD"));
        ZYPEZDItemValueSetter.setValue(intfcWrkTMsg.rcptTrxBalPk, rs.getBigDecimal("AR_TRX_BAL_PK"));
        ZYPEZDItemValueSetter.setValue(intfcWrkTMsg.rcptHdrLastUpdTs, rs.getString("EZUPTIME"));
        ZYPEZDItemValueSetter.setValue(intfcWrkTMsg.rcptHdrTz, rs.getString("EZUPTIMEZONE"));
        ZYPEZDItemValueSetter.setValue(intfcWrkTMsg.rcptTrxBalLastUpdTs, rs.getString("EZUPTIME"));
        ZYPEZDItemValueSetter.setValue(intfcWrkTMsg.rcptTrxBalTz, rs.getString("EZUPTIMEZONE"));
        ZYPEZDItemValueSetter.setValue(intfcWrkTMsg.grpInvFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(intfcWrkTMsg.invNum, rs.getString("AR_TRX_NUM"));
        ZYPEZDItemValueSetter.setValue(intfcWrkTMsg.arTrxTpCd, rs.getString("AR_TRX_TP_CD"));
        ZYPEZDItemValueSetter.setValue(intfcWrkTMsg.invTrxBalPk, rs.getBigDecimal("AR_TRX_BAL_PK"));
        ZYPEZDItemValueSetter.setValue(intfcWrkTMsg.invTrxBalLastUpdTs, rs.getString("EZUPTIME"));
        ZYPEZDItemValueSetter.setValue(intfcWrkTMsg.invTrxBalTz, rs.getString("EZUPTIMEZONE"));
        ZYPEZDItemValueSetter.setValue(intfcWrkTMsg.crTrxBalPk, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(intfcWrkTMsg.dealCcyCd, BLANK);
        ZYPEZDItemValueSetter.setValue(intfcWrkTMsg.dealApplyAmt, rs.getBigDecimal("DEAL_RMNG_BAL_GRS_AMT"));
        ZYPEZDItemValueSetter.setValue(intfcWrkTMsg.dealCashDiscAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(intfcWrkTMsg.dealOnAcctCashAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(intfcWrkTMsg.dealApplyAdjAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(intfcWrkTMsg.dealApplyAdjRsvdAmt, BigDecimal.ZERO);

        this.crmAmt = crmAmt.add(convertZero(intfcWrkTMsg.dealApplyAmt.getValue(), VIEW_SCALE));
        this.crmIntfcWrkTMsgList.add(intfcWrkTMsg);
    }

    /**
     * Set IntrfaceWrkInfoOrgList
     * @param tMsg
     * @param modeTrn
     * @param dealCcyCd String
     * @throws SQLException
     */
    private void setIntrfaceWrkInfoOrgList(AR_TRX_BALTMsg tMsg, String modeTrn, String dealCcyCd) throws SQLException {

        AR_APPLY_INTFC_WRKTMsg intfcWrkTMsg = new AR_APPLY_INTFC_WRKTMsg();

        ZYPEZDItemValueSetter.setValue(intfcWrkTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(intfcWrkTMsg.applyGrpKey, BUSINESS_ID);
        ZYPEZDItemValueSetter.setValue(intfcWrkTMsg.applyGrpSubPk, BigDecimal.ONE);
        ZYPEZDItemValueSetter.setValue(intfcWrkTMsg.bizAppId, BUSINESS_ID);
        ZYPEZDItemValueSetter.setValue(intfcWrkTMsg.upldCsvRqstPk, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(intfcWrkTMsg.procTpCd, PROC_TP_CD_NEW);
        ZYPEZDItemValueSetter.setValue(intfcWrkTMsg.dealSqNum, DEAL_SQ_NUM);
        ZYPEZDItemValueSetter.setValue(intfcWrkTMsg.dealSqDtlNum, DEAL_SQ_DTL_NUM);
        ZYPEZDItemValueSetter.setValue(intfcWrkTMsg.procStsCd, PROC_STS.IN_COMPLETED);
        ZYPEZDItemValueSetter.setValue(intfcWrkTMsg.usrId, this.batUsrId);
        ZYPEZDItemValueSetter.setValue(intfcWrkTMsg.rcptNum, ZERO);
        ZYPEZDItemValueSetter.setValue(intfcWrkTMsg.rcptInProcSqPk, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(intfcWrkTMsg.rcptGlDt, tMsg.arTrxDt.getValue());
        ZYPEZDItemValueSetter.setValue(intfcWrkTMsg.payerCustCd, tMsg.billToCustCd.getValue());
        ZYPEZDItemValueSetter.setValue(intfcWrkTMsg.rcptTrxBalPk, tMsg.arTrxBalPk.getValue());
        ZYPEZDItemValueSetter.setValue(intfcWrkTMsg.rcptHdrLastUpdTs, tMsg.ezUpTime.getValue());
        ZYPEZDItemValueSetter.setValue(intfcWrkTMsg.rcptHdrTz, tMsg.ezUpTimeZone.getValue());
        ZYPEZDItemValueSetter.setValue(intfcWrkTMsg.rcptTrxBalLastUpdTs, tMsg.ezUpTime.getValue());
        ZYPEZDItemValueSetter.setValue(intfcWrkTMsg.rcptTrxBalTz, tMsg.ezUpTimeZone.getValue());
        ZYPEZDItemValueSetter.setValue(intfcWrkTMsg.grpInvFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(intfcWrkTMsg.invNum, tMsg.arTrxNum.getValue());
        ZYPEZDItemValueSetter.setValue(intfcWrkTMsg.arTrxTpCd, tMsg.arTrxTpCd.getValue());
        ZYPEZDItemValueSetter.setValue(intfcWrkTMsg.invTrxBalPk, tMsg.arTrxBalPk.getValue());
        ZYPEZDItemValueSetter.setValue(intfcWrkTMsg.invTrxBalLastUpdTs, tMsg.ezUpTime.getValue());
        ZYPEZDItemValueSetter.setValue(intfcWrkTMsg.invTrxBalTz, tMsg.ezUpTimeZone.getValue());
        ZYPEZDItemValueSetter.setValue(intfcWrkTMsg.crTrxBalPk, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(intfcWrkTMsg.dealCcyCd, dealCcyCd);
        ZYPEZDItemValueSetter.setValue(intfcWrkTMsg.dealApplyAmt, tMsg.dealRmngBalGrsAmt.getValue());
        ZYPEZDItemValueSetter.setValue(intfcWrkTMsg.dealCashDiscAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(intfcWrkTMsg.dealOnAcctCashAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(intfcWrkTMsg.dealApplyAdjAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(intfcWrkTMsg.dealApplyAdjRsvdAmt, BigDecimal.ZERO);

        if (ORIGINAL.equals(modeTrn)) {
            this.orgInvAmt = orgInvAmt.add(convertZero(intfcWrkTMsg.dealApplyAmt.getValue(), VIEW_SCALE));
            this.orgInvIntfcWrkTMsgList.add(intfcWrkTMsg);
        } else if (REBILL.equals(modeTrn)) {
            this.rebInvAmt = rebInvAmt.add(convertZero(intfcWrkTMsg.dealApplyAmt.getValue(), VIEW_SCALE));
            this.rebInvIntfcWrkTMsgList.add(intfcWrkTMsg);
        }
    }

    /**
     * Deposit Date Check
     * @param arTrxDtCrm String
     * @param arTrxDtOrg String
     * @return boolean
     */
    private boolean depositDateCheck(String arTrxDtCrm, String arTrxDtOrg) {

        String lastBatProcDate = getEndofMonth(this.batProcDate);
        // -------------------------------------
        // Credit Memo Date match
        // -------------------------------------
        if (lastBatProcDate.compareTo(arTrxDtCrm) < 0) {
            return false;
        }
        // -------------------------------------
        // Original Invoice Date match
        // -------------------------------------
        if (lastBatProcDate.compareTo(arTrxDtOrg) < 0) {
            return false;
        }
        // -------------------------------------
        // Rebill Date match
        // -------------------------------------
        for (AR_APPLY_INTFC_WRKTMsg arApplyIntfcWrkT : this.rebInvIntfcWrkTMsgList) {
            String arTrxDtReb = arApplyIntfcWrkT.rcptGlDt.getValue();
            if (lastBatProcDate.compareTo(arTrxDtReb) < 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Credit Analyst Code Check
     * @param billToCustCd String
     * @param billToCustAcctCd String
     * @return crMgrPsnCd
     */
    private String creAnalysCdCheck(String billToCustCd, String billToCustAcctCd) {
        // Bill To
        String crMgrPsnCd = getCustomerBillInfo(billToCustCd);

        String crMgrPsnCdAcct = null;
        if (!ZYPCommonFunc.hasValue(crMgrPsnCd)) {
            // Account
            DS_ACCT_CR_PRFLTMsg dsAcctCrPrflT = getCustomerAcctInfo(billToCustAcctCd);
            if (dsAcctCrPrflT != null) {
                crMgrPsnCdAcct = dsAcctCrPrflT.crMgrPsnCd.getValue();
            }
            return crMgrPsnCdAcct;
        } else {
            return crMgrPsnCd;
        }
    }

    /**
     * Get Exchange Rate
     * @param dealCcyCd String
     * @return actlExchRate
     */
    private BigDecimal getActlExchRate(String dealCcyCd) {
        BigDecimal actlExchRate = null;
        if (this.stdCcyCd.equals(dealCcyCd)) {
            actlExchRate = BigDecimal.ONE;
        } else {
            ACCT_DLY_ACTL_EXCH_RATESTMsg acctDlyActlExchRatesT = getActlExchRates(dealCcyCd);
            if (acctDlyActlExchRatesT != null) {
                actlExchRate = acctDlyActlExchRatesT.actlExchRate.getValue();
            }
        }
        return actlExchRate;
    }

    /**
     * Apply Amount Check
     */
    private void applyAmountCheck() {
        BigDecimal totDealApplyAmt = BigDecimal.ZERO;
        BigDecimal totInvDealApplyAmt = BigDecimal.ZERO;

        if (ZYPConstant.FLG_ON_Y.equals(this.flgOrgInvApp) && ZYPConstant.FLG_ON_Y.equals(this.flgRebInvApp)) {
            totDealApplyAmt = this.orgInvAmt.add(this.rebInvAmt);
            totInvDealApplyAmt = this.orgInvAmt.add(this.crmAmt);
        } else if (ZYPConstant.FLG_ON_Y.equals(this.flgOrgInvApp) && ZYPConstant.FLG_OFF_N.equals(this.flgRebInvApp)) {
            totDealApplyAmt = this.orgInvAmt;
            totInvDealApplyAmt = this.orgInvAmt.add(this.crmAmt);
        } else if (ZYPConstant.FLG_OFF_N.equals(this.flgOrgInvApp) && ZYPConstant.FLG_ON_Y.equals(this.flgRebInvApp)) {
            totDealApplyAmt = this.rebInvAmt;
            totInvDealApplyAmt = this.crmAmt;
        }
        // -------------------------------------
        // All Apply
        // -------------------------------------
        if (this.crmAmt.abs().compareTo(totDealApplyAmt) >= 0) {
            applyAll(totDealApplyAmt);
        }
        // -------------------------------------
        // Partial Apply
        // -------------------------------------
        if (this.crmAmt.abs().compareTo(totDealApplyAmt) < 0) {
            applyPartial(totInvDealApplyAmt);
        }
    }

    /**
     * Apply All
     */
    private void applyAll(BigDecimal totDealApplyAmt) {
        // Credit Memo
        ZYPEZDItemValueSetter.setValue(crmIntfcWrkTMsgList.get(0).dealApplyAmt, totDealApplyAmt.negate());
    }

    /**
     * Apply Partial
     */
    private void applyPartial(BigDecimal totInvDealApplyAmt) {

        // Mod Start 2018/04/19 QC#25517
        if (S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, this.flgOrgInvApp) && S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, this.flgRebInvApp)) {
            // -------------------------------------
            // Apply Over Tolerance
            // -------------------------------------
            if (this.crmAmt.abs().compareTo(this.orgInvAmt) <= 0) {
                // Original Invoice
                ZYPEZDItemValueSetter.setValue(orgInvIntfcWrkTMsgList.get(0).dealApplyAmt, this.crmAmt.negate());
                // Rebill Invoice
                this.flgRebInvApp = ZYPConstant.FLG_OFF_N;
                // -------------------------------------
                // Apply Under Tolerance
                // -------------------------------------
            } else if (this.crmAmt.abs().compareTo(this.orgInvAmt) > 0) {
                for (AR_APPLY_INTFC_WRKTMsg arApplyIntfcWrkT : this.rebInvIntfcWrkTMsgList) {
                    this.rebInvAmt = arApplyIntfcWrkT.dealApplyAmt.getValue();
                    if (totInvDealApplyAmt.abs().compareTo(this.rebInvAmt) >= 0) {
                        arApplyIntfcWrkT.dealApplyAmt.setValue(this.rebInvAmt);
                        totInvDealApplyAmt = totInvDealApplyAmt.add(this.rebInvAmt);
                    } else {
                        arApplyIntfcWrkT.dealApplyAmt.setValue(totInvDealApplyAmt.negate());
                    }
                }
            }
        } else if (S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, this.flgOrgInvApp) && S21StringUtil.isEquals(ZYPConstant.FLG_OFF_N, this.flgRebInvApp)) {
            if (this.crmAmt.abs().compareTo(this.orgInvAmt) <= 0) {
                ZYPEZDItemValueSetter.setValue(orgInvIntfcWrkTMsgList.get(0).dealApplyAmt, this.crmAmt.negate());
            } else if (this.crmAmt.abs().compareTo(this.orgInvAmt) > 0) {
                ZYPEZDItemValueSetter.setValue(orgInvIntfcWrkTMsgList.get(0).dealApplyAmt, this.orgInvAmt);

            }
        } else if (S21StringUtil.isEquals(ZYPConstant.FLG_OFF_N, this.flgOrgInvApp) && S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, this.flgRebInvApp)) {
            if (this.crmAmt.abs().compareTo(this.rebInvAmt) <= 0) {
                ZYPEZDItemValueSetter.setValue(rebInvIntfcWrkTMsgList.get(0).dealApplyAmt, this.crmAmt.negate());
            } else if (this.crmAmt.abs().compareTo(this.rebInvAmt) > 0) {
                for (AR_APPLY_INTFC_WRKTMsg arApplyIntfcWrkT : this.rebInvIntfcWrkTMsgList) {
                    this.rebInvAmt = arApplyIntfcWrkT.dealApplyAmt.getValue();
                    if (totInvDealApplyAmt.abs().compareTo(this.rebInvAmt) >= 0) {
                        arApplyIntfcWrkT.dealApplyAmt.setValue(this.rebInvAmt);
                        totInvDealApplyAmt = totInvDealApplyAmt.add(this.rebInvAmt);
                    } else {
                        arApplyIntfcWrkT.dealApplyAmt.setValue(totInvDealApplyAmt.negate());
                    }
                }
            }
        }
        // Mod End 2018/04/19 QC#25517
    }

    /**
     * Get StdCcyCd
     * @return GLBL_CMPYTMsg
     */
    private GLBL_CMPYTMsg getStdCcyCd() {

        GLBL_CMPYTMsg inMsg = new GLBL_CMPYTMsg();
        inMsg.glblCmpyCd.setValue(this.glblCmpyCd);
        return (GLBL_CMPYTMsg) EZDTBLAccessor.findByKey(inMsg);
    }

    /**
     * Execute AbendException
     * @param msgId String
     * @param msgStr String[]
     */
    private void execAbendException(String msgId, String[] msgStr) {

        termCd = TERM_CD.ABNORMAL_END;
        throw new S21AbendException(msgId, msgStr);
    }

    /**
     * Get Customer BillInfo
     * @param billToCustCd String
     * @return String
     */
    private String getCustomerBillInfo(String billToCustCd) {

        String crMgrPsnCd = null;
        Map<String, Object> getCustCrPrflParam = new HashMap<String, Object>();
        getCustCrPrflParam.put("glblCmpyCd", this.glblCmpyCd);
        getCustCrPrflParam.put("billToCustCd", billToCustCd);
        @SuppressWarnings("unchecked")
        List<CUST_CR_PRFLTMsg> retultList = (List<CUST_CR_PRFLTMsg>) this.ssmBatchClient.queryObjectList("getCustCrPrfl", getCustCrPrflParam);

        if (!retultList.isEmpty()) {
            CUST_CR_PRFLTMsg custCrPrflTMsg = retultList.get(0);
            crMgrPsnCd = custCrPrflTMsg.crMgrPsnCd.getValue();
        }
        return crMgrPsnCd;
    }

    /**
     * Get Customer AcctInfo
     * @param billToCustAcctCd String
     * @return DS_ACCT_CR_PRFLTMsg
     */
    private DS_ACCT_CR_PRFLTMsg getCustomerAcctInfo(String billToCustAcctCd) {

        DS_ACCT_CR_PRFLTMsg dsAcctCrPrflTMsg = new DS_ACCT_CR_PRFLTMsg();
        dsAcctCrPrflTMsg.glblCmpyCd.setValue(this.glblCmpyCd);
        dsAcctCrPrflTMsg.dsAcctNum.setValue(billToCustAcctCd);
        return (DS_ACCT_CR_PRFLTMsg) EZDTBLAccessor.findByKey(dsAcctCrPrflTMsg);
    }

    /**
     * Get Actual Exchange Rates
     * @param dealCcyCd String
     * @return ACCT_DLY_ACTL_EXCH_RATESTMsg
     */
    private ACCT_DLY_ACTL_EXCH_RATESTMsg getActlExchRates(String dealCcyCd) {

        ACCT_DLY_ACTL_EXCH_RATESTMsg exchRateTMsg = new ACCT_DLY_ACTL_EXCH_RATESTMsg();
        exchRateTMsg.glblCmpyCd.setValue(this.glblCmpyCd);
        exchRateTMsg.ccyCd.setValue(dealCcyCd);
        exchRateTMsg.actlExchRateEntDt.setValue(this.batProcDate);
        return (ACCT_DLY_ACTL_EXCH_RATESTMsg) EZDTBLAccessor.findByKey(exchRateTMsg);
    }

    /**
     * Create Apply Info
     * @param arTrxNum String
     * @param orgInvNum String
     * @param billToCustCd String
     * @param billToCustAcctCd String
     * @param crMgrPsnCd String
     * @param dealCcyCd StringactlExchRate
     * @param actlExchRate BigDecimal
     * @return boolean
     */
    private boolean createApplyInfo(String arTrxNum, String orgInvNum, String billToCustCd, String billToCustAcctCd, String crMgrPsnCd, String dealCcyCd, BigDecimal actlExchRate) {

        String rcptNum = getRcptNum();
        if (!ZYPCommonFunc.hasValue(rcptNum)) {
            execAbendException(NFCM0531E, new String[] {"Receipt Number"});
        }

        BigDecimal arTrxBalPk = getArTrxBalPk();
        if (!ZYPCommonFunc.hasValue(arTrxBalPk)) {
            execAbendException(NFCM0531E, new String[] {"AR Transaction Balance PK"});
        }
        //------------------------------------------------
        // for AR_RCPT  Header
        //------------------------------------------------
        // create AR_RCPT
        if (!createArRcpt(rcptNum, billToCustAcctCd, crMgrPsnCd, dealCcyCd, actlExchRate)) {
            errCnt++;
            errMlCnt++;
            String[] str = {RECEIPT, AR_RCPT};
            createMailMsgInfo(arTrxNum, orgInvNum, this.errRebInfo, APPLY_RTNCD_OTHERS_ERROR, API_ERR_OTHER_ERROR, NFCM0616E, str);
            return false;
        }
        // create AR_RCPT_DTL
        if (!createArRcptDtl(rcptNum)) {
            errCnt++;
            errMlCnt++;
            String[] str = {RECEIPT, AR_RCPT_DTL};
            createMailMsgInfo(arTrxNum, orgInvNum, this.errRebInfo, APPLY_RTNCD_OTHERS_ERROR, API_ERR_OTHER_ERROR, NFCM0616E, str);
            return false;
        }
        // create AR_TRX_BAL for AR_RCPT
        if (!createArTrxBalForRcpt(rcptNum, arTrxBalPk, billToCustAcctCd, dealCcyCd, actlExchRate)) {
            errCnt++;
            errMlCnt++;
            String[] str = {RECEIPT, AR_TRX_BAL};
            createMailMsgInfo(arTrxNum, orgInvNum, this.errRebInfo, APPLY_RTNCD_OTHERS_ERROR, API_ERR_OTHER_ERROR, NFCM0616E, str);
            return false;
        }
        // Get Apply Group PK
        String applyGrpKey = null;
        applyGrpKey = BUSINESS_ID.concat(ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS"));
        // create AR_APPLY_INTFC_WRK for AR_RCPT
        if (!createArApplyIntfcWrkForHeader(rcptNum, arTrxBalPk, applyGrpKey, billToCustAcctCd, dealCcyCd)) {
            errCnt++;
            errMlCnt++;
            String[] str = {RECEIPT, AR_APPLY_INTFC_WRK};
            createMailMsgInfo(arTrxNum, orgInvNum, this.errRebInfo, APPLY_RTNCD_OTHERS_ERROR, API_ERR_OTHER_ERROR, NFCM0616E, str);
            return false;
        }
        // NFZC301001 for AR_RCPT
        if (!callApplyAPI(applyGrpKey, rcptNum, MODE_RECEIPT, arTrxNum, orgInvNum)) {
            return false;
        }
        //------------------------------------------------
        // for Detail
        //------------------------------------------------
        // Get Apply Group PK
        applyGrpKey = BUSINESS_ID.concat(ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS"));
        if (!createArApplyIntfcWrkForDetail(rcptNum, arTrxBalPk, applyGrpKey, dealCcyCd)) {
            errCnt++;
            errMlCnt++;
            String[] str = {RECEIPT, AR_APPLY_INTFC_WRK};
            createMailMsgInfo(arTrxNum, orgInvNum, this.errRebInfo, APPLY_RTNCD_OTHERS_ERROR, API_ERR_OTHER_ERROR, NFCM0616E, str);
            return false;
        }
        // NFZC301001 for Detail
        if (!callApplyAPI(applyGrpKey, rcptNum, MODE_APPLY, arTrxNum, orgInvNum)) {
            return false;
        }
        //------------------------------------------------
        // Credit Profile Update
        //------------------------------------------------
        // NFZC202001 for Header
        if (ZYPConstant.FLG_ON_Y.equals(this.flgOrgInvApp)) {
            if (!callCreditProfileUpdateApi(billToCustCd, arTrxNum, orgInvNum)) {
                return false;
            }
        }
        boolean flgCrm = true;
        // NFZC202001 for Detail
        if (ZYPConstant.FLG_ON_Y.equals(this.flgRebInvApp)) {
            for (AR_APPLY_INTFC_WRKTMsg arApplyIntfcWrkT : this.rebInvIntfcWrkTMsgList) {
                String billToCustCdReb = arApplyIntfcWrkT.payerCustCd.getValue();
                if (ZYPConstant.FLG_OFF_N.equals(this.flgOrgInvApp) && flgCrm) {
                    if (!callCreditProfileUpdateApi(billToCustCdReb, arTrxNum, orgInvNum)) {
                        return false;
                    }
                    flgCrm = false;
                    billToCustCd = billToCustCdReb;
                    continue;
                }
                if (!billToCustCd.equals(billToCustCdReb)) {
                    if (!callCreditProfileUpdateApi(billToCustCdReb, arTrxNum, orgInvNum)) {
                        return false;
                    }
                    billToCustCd = billToCustCdReb;
                }
            }
        }
        return true;
    }

    /**
     * Call CreditProfileUpdateAPI
     * @param billTo String
     * @param arTrxNum String
     * @param orgInvNum String
     * @return boolean
     */
    public boolean callCreditProfileUpdateApi(String billTo, String arTrxNum, String orgInvNum) {
        // Credit Profile Update (Balance)
        NFZC202001 crPrflUpdApi = new NFZC202001();
        NFZC202001PMsg apiMsg = new NFZC202001PMsg();

        apiMsg.xxModeCd.setValue(NFZC202001.MODE_BILL_TO_CUST);
        apiMsg.glblCmpyCd.setValue(this.glblCmpyCd);
        apiMsg.billToCustCd.setValue(billTo);
        apiMsg.procDt.setValue(this.batProcDate);

        crPrflUpdApi.execute(apiMsg, ONBATCH_TYPE.BATCH);
        if (S21ApiUtil.isXxMsgId(apiMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(apiMsg);
            S21ApiMessage msg = msgList.get(0);
            String msgId = msg.getXxMsgid();
            String[] msgPrms = msg.getXxMsgPrmArray();
            String rtnMsg = getRtnMsg(msgId, msgPrms);

            errCnt++;
            errMlCnt++;
            String[] str = {msgId, rtnMsg, billTo};
            createMailMsgInfo(arTrxNum, orgInvNum, this.errRebInfo, APPLY_RTNCD_OTHERS_ERROR, API_ERR_OTHER_ERROR, NFCM0813E, str);
            return false;
        }
        return true;
    }

    /**
     * Get Return Message
     * @param msgId String
     * @param prm String[]
     * @return String
     */
    public static String getRtnMsg(String msgId, String[] prm) {
        String rtnVal = "";
        if (ZYPCommonFunc.hasValue(msgId)) {
            rtnVal = S21MessageFunc.clspGetMessage(msgId, prm);
            rtnVal = rtnVal.substring(msgId.length()).trim();
        }
        return rtnVal;
    }

    /**
     * create AR_RCPT
     * @param rcptNum
     * @param billToCustAcctCd String
     * @param crMgrPsnCd String
     * @param dealCcyCd String
     * @param actlExchRate BigDecimal
     * @return boolean
     */
    private boolean createArRcpt(String rcptNum, String billToCustAcctCd, String crMgrPsnCd, String dealCcyCd, BigDecimal actlExchRate) {

        AR_RCPTTMsg insMsg = new AR_RCPTTMsg();

        ZYPEZDItemValueSetter.setValue(insMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(insMsg.rcptNum, rcptNum);
        ZYPEZDItemValueSetter.setValue(insMsg.rcptBatNum, this.rcptBatNum);
        ZYPEZDItemValueSetter.setValue(insMsg.rcptBatSqNum, AR_RCPT_BAT_SQ_NUM);
        ZYPEZDItemValueSetter.setValue(insMsg.arRcptTrxTpCd, this.rcptTrxTpCd);
        ZYPEZDItemValueSetter.setValue(insMsg.arRcptTpCd, this.rcptTpCd);
        ZYPEZDItemValueSetter.setValue(insMsg.dealCcyCd, dealCcyCd);
        ZYPEZDItemValueSetter.setValue(insMsg.dealRcptAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(insMsg.dealApplyAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(insMsg.dealApplyAdjAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(insMsg.dealRfAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(insMsg.dealVoidAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(insMsg.dealRcptRmngBalAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(insMsg.exchRate, actlExchRate);
        ZYPEZDItemValueSetter.setValue(insMsg.funcCcyCd, this.stdCcyCd);
        ZYPEZDItemValueSetter.setValue(insMsg.funcRcptAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(insMsg.funcApplyAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(insMsg.funcApplyAdjAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(insMsg.funcRfAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(insMsg.funcVoidAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(insMsg.funcRvalVarAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(insMsg.funcRcptRmngBalAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(insMsg.rcptDt, this.batProcDate);
        ZYPEZDItemValueSetter.setValue(insMsg.glDt, this.batProcDate);
        ZYPEZDItemValueSetter.setValue(insMsg.rcptChkNum, AR_RCPT_CHK_NUM);
        ZYPEZDItemValueSetter.setValue(insMsg.payerCustCd, billToCustAcctCd);
        ZYPEZDItemValueSetter.setValue(insMsg.tocCd, this.rcptTocCd);
        ZYPEZDItemValueSetter.setValue(insMsg.coaProdCd, this.rcptProdCd);
        //ZYPEZDItemValueSetter.setValue(insMsg.crAnlstPsnCd, crMgrPsnCd); // DEL 2016/12/19 T.Murai [QC#16520]
        ZYPEZDItemValueSetter.setValue(insMsg.arCashApplyStsCd, AR_CASH_APPLY_STS.APPLIED);
        ZYPEZDItemValueSetter.setValue(insMsg.voidFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(insMsg.cratMethTpCd, AR_CRAT_METH_TP.AUTO_EDI);
        ZYPEZDItemValueSetter.setValue(insMsg.dealFirstExptChrgAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(insMsg.funcFirstExptChrgAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(insMsg.dealScdExptChrgAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(insMsg.funcScdExptChrgAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(insMsg.dealNetRcptAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(insMsg.funcNetRcptAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(insMsg.fgnExchLossGainAmt, BigDecimal.ZERO);
        // START 2018/09/20 T.Ogura [QC#28097,MOD]
//        ZYPEZDItemValueSetter.setValue(insMsg.arRcptStsCd, AR_RCPT_STS.CLEARED);
        ZYPEZDItemValueSetter.setValue(insMsg.arRcptStsCd, AR_RCPT_STS.UNAPPLIED);
        // END   2018/09/20 T.Ogura [QC#28097,MOD]
        ZYPEZDItemValueSetter.setValue(insMsg.ajeCratCpltFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(insMsg.exptFlg, ZYPConstant.FLG_OFF_N);
        // START 2018/09/20 T.Ogura [QC#28097,ADD]
        ZYPEZDItemValueSetter.setValue(insMsg.arRcptSrcCd, AR_RCPT_SRC.SYSTEM_CREATED);
        // END   2018/09/20 T.Ogura [QC#28097,ADD]

        EZDTBLAccessor.create(insMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(insMsg.getReturnCode())) {
            return false;
        }
        return true;
    }

    /**
     * Create AR_RCPT Detail
     * @param rcptNum
     * @return boolean
     */
    private boolean createArRcptDtl(String rcptNum) {

        AR_RCPT_DTLTMsg insMsg = new AR_RCPT_DTLTMsg();

        ZYPEZDItemValueSetter.setValue(insMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(insMsg.rcptNum, rcptNum);
        ZYPEZDItemValueSetter.setValue(insMsg.rcptDtlNum, "0001");
        ZYPEZDItemValueSetter.setValue(insMsg.arCustRefNum, AR_RCPT_CHK_NUM);
        ZYPEZDItemValueSetter.setValue(insMsg.arCustRefTpCd, AR_TRX_TP.INVOICE);
        ZYPEZDItemValueSetter.setValue(insMsg.dealRcptDtlAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(insMsg.funcRcptDtlAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(insMsg.autoCratFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(insMsg.grpInvFlg, ZYPConstant.FLG_OFF_N);

        EZDTBLAccessor.create(insMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(insMsg.getReturnCode())) {
            return false;
        }
        return true;
    }

    /**
     * Create AR_TRX_BAL For Rcpt
     * @param rcptNum
     * @param arTrxBalPk
     * @param billToCustAcctCd String
     * @param dealCcyCd String
     * @param actlExchRate BigDecimal
     * @return boolean
     */
    private boolean createArTrxBalForRcpt(String rcptNum, BigDecimal arTrxBalPk, String billToCustAcctCd, String dealCcyCd, BigDecimal actlExchRate) {

        AR_TRX_BALTMsg insMsg = new AR_TRX_BALTMsg();

        ZYPEZDItemValueSetter.setValue(insMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(insMsg.arTrxBalPk, arTrxBalPk);
        ZYPEZDItemValueSetter.setValue(insMsg.arTrxNum, rcptNum);
        ZYPEZDItemValueSetter.setValue(insMsg.arTrxTpCd, AR_TRX_TP.RECEIPT);
        ZYPEZDItemValueSetter.setValue(insMsg.dealCcyCd, dealCcyCd);
        ZYPEZDItemValueSetter.setValue(insMsg.dealOrigGrsAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(insMsg.dealApplyGrsAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(insMsg.dealApplyCashDiscAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(insMsg.dealApplyCrAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(insMsg.dealApplyAdjAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(insMsg.dealApplyAdjRsvdAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(insMsg.dealRmngBalGrsAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(insMsg.dealNetSlsAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(insMsg.dealFrtAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(insMsg.dealTaxAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(insMsg.dealInvDiscAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(insMsg.dealPrmoDiscAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(insMsg.dealRcptVoidAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(insMsg.exchRate, actlExchRate);
        ZYPEZDItemValueSetter.setValue(insMsg.funcCcyCd, this.stdCcyCd);
        ZYPEZDItemValueSetter.setValue(insMsg.funcOrigGrsAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(insMsg.funcApplyGrsAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(insMsg.funcApplyCashDiscAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(insMsg.funcApplyCrAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(insMsg.funcApplyAdjAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(insMsg.funcApplyAdjRsvdAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(insMsg.funcRvalVarAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(insMsg.funcRmngBalGrsAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(insMsg.funcNetSlsAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(insMsg.funcFrtAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(insMsg.funcTaxAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(insMsg.funcInvDiscAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(insMsg.funcPrmoDiscAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(insMsg.funcRcptVoidAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(insMsg.arCashApplyStsCd, AR_CASH_APPLY_STS.APPLIED);
        ZYPEZDItemValueSetter.setValue(insMsg.arTrxDt, this.batProcDate);
        ZYPEZDItemValueSetter.setValue(insMsg.glDt, this.batProcDate);
        ZYPEZDItemValueSetter.setValue(insMsg.billToCustCd, billToCustAcctCd);
        ZYPEZDItemValueSetter.setValue(insMsg.sellToCustCd, billToCustAcctCd);
        ZYPEZDItemValueSetter.setValue(insMsg.payerCustCd, billToCustAcctCd);
        ZYPEZDItemValueSetter.setValue(insMsg.tocCd, this.rcptTocCd);
        ZYPEZDItemValueSetter.setValue(insMsg.coaProdCd, this.rcptProdCd);
        ZYPEZDItemValueSetter.setValue(insMsg.arCustRefNum, AR_RCPT_CHK_NUM);
        ZYPEZDItemValueSetter.setValue(insMsg.exptFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(insMsg.arAutoPurgeOfsFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(insMsg.billToCustAcctCd, billToCustAcctCd);

        EZDTBLAccessor.create(insMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(insMsg.getReturnCode())) {
            return false;
        }
        return true;
    }

    /**
     * Create AR_APPLY_INTFC_WRK For Header
     * @param rcptNum
     * @param arTrxBalPk
     * @param aGrKey
     * @param billToCustAcctCd String
     * @param dealCcyCd String
     * @return boolean
     */
    private boolean createArApplyIntfcWrkForHeader(String rcptNum, BigDecimal arTrxBalPk, String aGrKey, String billToCustAcctCd, String dealCcyCd) {

        String ezUpTimeRcpt = null;
        String ezUpdTimeZoneRcpt = null;
        String ezUpTimeTrx = null;
        String ezUpdTimeZoneTrx = null;

        AR_RCPTTMsg arRcptMsg = findArRcptInfo(rcptNum);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(arRcptMsg.getReturnCode())) {
            execAbendException(NFCM0079E, null);
        } else {
            ezUpTimeRcpt = arRcptMsg.ezUpTime.getValue();
            ezUpdTimeZoneRcpt = arRcptMsg.ezUpTimeZone.getValue();
        }

        AR_TRX_BALTMsg arTrxBalMsg = findArTrxBalInfo(arTrxBalPk);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(arTrxBalMsg.getReturnCode())) {
            execAbendException(NFCM0079E, null);
        } else {
            ezUpTimeTrx = arTrxBalMsg.ezUpTime.getValue();
            ezUpdTimeZoneTrx = arTrxBalMsg.ezUpTimeZone.getValue();
        }

        AR_APPLY_INTFC_WRKTMsg insMsg = new AR_APPLY_INTFC_WRKTMsg();

        ZYPEZDItemValueSetter.setValue(insMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(insMsg.applyGrpKey, aGrKey);
        ZYPEZDItemValueSetter.setValue(insMsg.applyGrpSubPk, BigDecimal.ONE);
        ZYPEZDItemValueSetter.setValue(insMsg.bizAppId, BUSINESS_ID);
        ZYPEZDItemValueSetter.setValue(insMsg.upldCsvRqstPk, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(insMsg.procTpCd, PROC_TP_CD_NEW);
        ZYPEZDItemValueSetter.setValue(insMsg.dealSqNum, DEAL_SQ_NUM);
        ZYPEZDItemValueSetter.setValue(insMsg.dealSqDtlNum, DEAL_SQ_DTL_NUM);
        ZYPEZDItemValueSetter.setValue(insMsg.procStsCd, PROC_STS.IN_COMPLETED);
        ZYPEZDItemValueSetter.setValue(insMsg.usrId, this.batUsrId);
        ZYPEZDItemValueSetter.setValue(insMsg.rcptNum, rcptNum);
        ZYPEZDItemValueSetter.setValue(insMsg.rcptInProcSqPk, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(insMsg.rcptGlDt, this.batProcDate);
        ZYPEZDItemValueSetter.setValue(insMsg.payerCustCd, billToCustAcctCd);
        ZYPEZDItemValueSetter.setValue(insMsg.rcptTrxBalPk, arTrxBalPk);
        ZYPEZDItemValueSetter.setValue(insMsg.rcptHdrLastUpdTs, ezUpTimeRcpt);
        ZYPEZDItemValueSetter.setValue(insMsg.rcptHdrTz, ezUpdTimeZoneRcpt);
        ZYPEZDItemValueSetter.setValue(insMsg.rcptTrxBalLastUpdTs, ezUpTimeTrx);
        ZYPEZDItemValueSetter.setValue(insMsg.rcptTrxBalTz, ezUpdTimeZoneTrx);
        ZYPEZDItemValueSetter.setValue(insMsg.grpInvFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(insMsg.invNum, "");
        ZYPEZDItemValueSetter.setValue(insMsg.arTrxTpCd, "");
        ZYPEZDItemValueSetter.setValue(insMsg.invTrxBalPk, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(insMsg.invTrxBalLastUpdTs, "");
        ZYPEZDItemValueSetter.setValue(insMsg.invTrxBalTz, "");
        ZYPEZDItemValueSetter.setValue(insMsg.crTrxBalPk, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(insMsg.dealCcyCd, dealCcyCd);
        ZYPEZDItemValueSetter.setValue(insMsg.dealApplyAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(insMsg.dealCashDiscAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(insMsg.dealOnAcctCashAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(insMsg.dealApplyAdjAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(insMsg.dealApplyAdjRsvdAmt, BigDecimal.ZERO);

        EZDTBLAccessor.create(insMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(insMsg.getReturnCode())) {
            return false;
        }
        return true;
    }

    /**
     * Create AR_APPLY_INTFC_WRK For Detail
     * @param rcptNum
     * @param arTrxBalPk
     * @param aGrKey
     * @param dealCcyCd String
     * @return boolean
     */
    private boolean createArApplyIntfcWrkForDetail(String rcptNum, BigDecimal arTrxBalPk, String aGrKey, String dealCcyCd) {

        String ezUpTimeRcpt = null;
        String ezUpdTimeZoneRcpt = null;
        String ezUpTimeTrx = null;
        String ezUpdTimeZoneTrx = null;

        AR_RCPTTMsg arRcptMsg = findArRcptInfo(rcptNum);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(arRcptMsg.getReturnCode())) {
            execAbendException(NFCM0079E, null);
        } else {
            ezUpTimeRcpt = arRcptMsg.ezUpTime.getValue();
            ezUpdTimeZoneRcpt = arRcptMsg.ezUpTimeZone.getValue();
        }

        AR_TRX_BALTMsg arTrxBalMsg = findArTrxBalInfo(arTrxBalPk);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(arTrxBalMsg.getReturnCode())) {
            execAbendException(NFCM0079E, null);
        } else {
            ezUpTimeTrx = arTrxBalMsg.ezUpTime.getValue();
            ezUpdTimeZoneTrx = arTrxBalMsg.ezUpTimeZone.getValue();
        }
        //----------------------------------
        // CRM : ORG : REB
        //  1  :  1  :  N
        //----------------------------------

        // Credit Memo
        int applyGrpSubKey = 0;
        applyGrpSubKey++;
        AR_APPLY_INTFC_WRKTMsg insCrmMsg = new AR_APPLY_INTFC_WRKTMsg();
        ZYPEZDItemValueSetter.setValue(insCrmMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(insCrmMsg.applyGrpKey, aGrKey);
        ZYPEZDItemValueSetter.setValue(insCrmMsg.applyGrpSubPk, BigDecimal.valueOf(applyGrpSubKey));
        ZYPEZDItemValueSetter.setValue(insCrmMsg.bizAppId, BUSINESS_ID);
        ZYPEZDItemValueSetter.setValue(insCrmMsg.upldCsvRqstPk, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(insCrmMsg.procTpCd, PROC_TP_CD_NEW);
        ZYPEZDItemValueSetter.setValue(insCrmMsg.dealSqNum, DEAL_SQ_NUM);
        ZYPEZDItemValueSetter.setValue(insCrmMsg.dealSqDtlNum, DEAL_SQ_DTL_NUM);
        ZYPEZDItemValueSetter.setValue(insCrmMsg.procStsCd, PROC_STS.IN_COMPLETED);
        ZYPEZDItemValueSetter.setValue(insCrmMsg.usrId, this.batUsrId);
        ZYPEZDItemValueSetter.setValue(insCrmMsg.rcptNum, rcptNum);
        ZYPEZDItemValueSetter.setValue(insCrmMsg.rcptInProcSqPk, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(insCrmMsg.rcptGlDt, "");
        ZYPEZDItemValueSetter.setValue(insCrmMsg.payerCustCd, "");
        ZYPEZDItemValueSetter.setValue(insCrmMsg.rcptTrxBalPk, arTrxBalPk);
        ZYPEZDItemValueSetter.setValue(insCrmMsg.rcptHdrLastUpdTs, ezUpTimeRcpt);
        ZYPEZDItemValueSetter.setValue(insCrmMsg.rcptHdrTz, ezUpdTimeZoneRcpt);
        ZYPEZDItemValueSetter.setValue(insCrmMsg.rcptTrxBalLastUpdTs, ezUpTimeTrx);
        ZYPEZDItemValueSetter.setValue(insCrmMsg.rcptTrxBalTz, ezUpdTimeZoneTrx);
        ZYPEZDItemValueSetter.setValue(insCrmMsg.grpInvFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(insCrmMsg.crTrxBalPk, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(insCrmMsg.dealCcyCd, dealCcyCd);
        ZYPEZDItemValueSetter.setValue(insCrmMsg.cashAppGlDt, this.batProcDate);
        ZYPEZDItemValueSetter.setValue(insCrmMsg.dealCashDiscAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(insCrmMsg.dealOnAcctCashAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(insCrmMsg.dealApplyAdjAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(insCrmMsg.dealApplyAdjRsvdAmt, BigDecimal.ZERO);

        ZYPEZDItemValueSetter.setValue(insCrmMsg.invNum, this.crmIntfcWrkTMsgList.get(0).invNum.getValue());
        ZYPEZDItemValueSetter.setValue(insCrmMsg.arTrxTpCd, this.crmIntfcWrkTMsgList.get(0).arTrxTpCd.getValue());
        ZYPEZDItemValueSetter.setValue(insCrmMsg.invTrxBalPk, this.crmIntfcWrkTMsgList.get(0).invTrxBalPk.getValue());
        ZYPEZDItemValueSetter.setValue(insCrmMsg.invTrxBalLastUpdTs, this.crmIntfcWrkTMsgList.get(0).invTrxBalLastUpdTs.getValue());
        ZYPEZDItemValueSetter.setValue(insCrmMsg.invTrxBalTz, this.crmIntfcWrkTMsgList.get(0).invTrxBalTz.getValue());
        ZYPEZDItemValueSetter.setValue(insCrmMsg.dealApplyAmt, this.crmIntfcWrkTMsgList.get(0).dealApplyAmt.getValue());

        EZDTBLAccessor.create(insCrmMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(insCrmMsg.getReturnCode())) {
            return false;
        }
        if (ZYPConstant.FLG_ON_Y.equals(this.flgOrgInvApp)) {
            //----------------------------------
            // Original Invoice
            //----------------------------------
            applyGrpSubKey++;
            AR_APPLY_INTFC_WRKTMsg insOrgMsg = new AR_APPLY_INTFC_WRKTMsg();
            ZYPEZDItemValueSetter.setValue(insOrgMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(insOrgMsg.applyGrpKey, aGrKey);
            ZYPEZDItemValueSetter.setValue(insOrgMsg.applyGrpSubPk, BigDecimal.valueOf(applyGrpSubKey));
            ZYPEZDItemValueSetter.setValue(insOrgMsg.bizAppId, BUSINESS_ID);
            ZYPEZDItemValueSetter.setValue(insOrgMsg.upldCsvRqstPk, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(insOrgMsg.procTpCd, PROC_TP_CD_NEW);
            ZYPEZDItemValueSetter.setValue(insOrgMsg.dealSqNum, DEAL_SQ_NUM);
            ZYPEZDItemValueSetter.setValue(insOrgMsg.dealSqDtlNum, DEAL_SQ_DTL_NUM);
            ZYPEZDItemValueSetter.setValue(insOrgMsg.procStsCd, PROC_STS.IN_COMPLETED);
            ZYPEZDItemValueSetter.setValue(insOrgMsg.usrId, this.batUsrId);
            ZYPEZDItemValueSetter.setValue(insOrgMsg.rcptNum, rcptNum);
            ZYPEZDItemValueSetter.setValue(insOrgMsg.rcptInProcSqPk, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(insOrgMsg.rcptGlDt, "");
            ZYPEZDItemValueSetter.setValue(insOrgMsg.payerCustCd, "");
            ZYPEZDItemValueSetter.setValue(insOrgMsg.rcptTrxBalPk, arTrxBalPk);
            ZYPEZDItemValueSetter.setValue(insOrgMsg.rcptHdrLastUpdTs, ezUpTimeRcpt);
            ZYPEZDItemValueSetter.setValue(insOrgMsg.rcptHdrTz, ezUpdTimeZoneRcpt);
            ZYPEZDItemValueSetter.setValue(insOrgMsg.rcptTrxBalLastUpdTs, ezUpTimeTrx);
            ZYPEZDItemValueSetter.setValue(insOrgMsg.rcptTrxBalTz, ezUpdTimeZoneTrx);
            ZYPEZDItemValueSetter.setValue(insOrgMsg.grpInvFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(insOrgMsg.crTrxBalPk, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(insOrgMsg.dealCcyCd, dealCcyCd);
            ZYPEZDItemValueSetter.setValue(insOrgMsg.cashAppGlDt, this.batProcDate);
            ZYPEZDItemValueSetter.setValue(insOrgMsg.dealCashDiscAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(insOrgMsg.dealOnAcctCashAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(insOrgMsg.dealApplyAdjAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(insOrgMsg.dealApplyAdjRsvdAmt, BigDecimal.ZERO);

            ZYPEZDItemValueSetter.setValue(insOrgMsg.invNum, this.orgInvIntfcWrkTMsgList.get(0).invNum.getValue());
            ZYPEZDItemValueSetter.setValue(insOrgMsg.arTrxTpCd, this.orgInvIntfcWrkTMsgList.get(0).arTrxTpCd.getValue());
            ZYPEZDItemValueSetter.setValue(insOrgMsg.invTrxBalPk, this.orgInvIntfcWrkTMsgList.get(0).invTrxBalPk.getValue());
            ZYPEZDItemValueSetter.setValue(insOrgMsg.invTrxBalLastUpdTs, this.orgInvIntfcWrkTMsgList.get(0).invTrxBalLastUpdTs.getValue());
            ZYPEZDItemValueSetter.setValue(insOrgMsg.invTrxBalTz, this.orgInvIntfcWrkTMsgList.get(0).invTrxBalTz.getValue());
            ZYPEZDItemValueSetter.setValue(insOrgMsg.dealApplyAmt, this.orgInvIntfcWrkTMsgList.get(0).dealApplyAmt.getValue());

            EZDTBLAccessor.create(insOrgMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(insOrgMsg.getReturnCode())) {
                return false;
            }
        }
        if (ZYPConstant.FLG_ON_Y.equals(this.flgRebInvApp)) {
            //----------------------------------
            // Rebill Invoice
            //----------------------------------
            for (AR_APPLY_INTFC_WRKTMsg arApplyIntfcWrkT : this.rebInvIntfcWrkTMsgList) {
                applyGrpSubKey++;
                AR_APPLY_INTFC_WRKTMsg insRebMsg = new AR_APPLY_INTFC_WRKTMsg();
                ZYPEZDItemValueSetter.setValue(insRebMsg.glblCmpyCd, this.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(insRebMsg.applyGrpKey, aGrKey);
                ZYPEZDItemValueSetter.setValue(insRebMsg.applyGrpSubPk, BigDecimal.valueOf(applyGrpSubKey));
                ZYPEZDItemValueSetter.setValue(insRebMsg.bizAppId, BUSINESS_ID);
                ZYPEZDItemValueSetter.setValue(insRebMsg.upldCsvRqstPk, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(insRebMsg.procTpCd, PROC_TP_CD_NEW);
                ZYPEZDItemValueSetter.setValue(insRebMsg.dealSqNum, DEAL_SQ_NUM);
                ZYPEZDItemValueSetter.setValue(insRebMsg.dealSqDtlNum, DEAL_SQ_DTL_NUM);
                ZYPEZDItemValueSetter.setValue(insRebMsg.procStsCd, PROC_STS.IN_COMPLETED);
                ZYPEZDItemValueSetter.setValue(insRebMsg.usrId, this.batUsrId);
                ZYPEZDItemValueSetter.setValue(insRebMsg.rcptNum, rcptNum);
                ZYPEZDItemValueSetter.setValue(insRebMsg.rcptInProcSqPk, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(insRebMsg.rcptGlDt, "");
                ZYPEZDItemValueSetter.setValue(insRebMsg.payerCustCd, "");
                ZYPEZDItemValueSetter.setValue(insRebMsg.rcptTrxBalPk, arTrxBalPk);
                ZYPEZDItemValueSetter.setValue(insRebMsg.rcptHdrLastUpdTs, ezUpTimeRcpt);
                ZYPEZDItemValueSetter.setValue(insRebMsg.rcptHdrTz, ezUpdTimeZoneRcpt);
                ZYPEZDItemValueSetter.setValue(insRebMsg.rcptTrxBalLastUpdTs, ezUpTimeTrx);
                ZYPEZDItemValueSetter.setValue(insRebMsg.rcptTrxBalTz, ezUpdTimeZoneTrx);
                ZYPEZDItemValueSetter.setValue(insRebMsg.grpInvFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(insRebMsg.crTrxBalPk, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(insRebMsg.dealCcyCd, dealCcyCd);
                ZYPEZDItemValueSetter.setValue(insRebMsg.cashAppGlDt, this.batProcDate);
                ZYPEZDItemValueSetter.setValue(insRebMsg.dealCashDiscAmt, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(insRebMsg.dealOnAcctCashAmt, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(insRebMsg.dealApplyAdjAmt, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(insRebMsg.dealApplyAdjRsvdAmt, BigDecimal.ZERO);

                ZYPEZDItemValueSetter.setValue(insRebMsg.invNum, arApplyIntfcWrkT.invNum.getValue());
                ZYPEZDItemValueSetter.setValue(insRebMsg.arTrxTpCd, arApplyIntfcWrkT.arTrxTpCd.getValue());
                ZYPEZDItemValueSetter.setValue(insRebMsg.invTrxBalPk, arApplyIntfcWrkT.invTrxBalPk.getValue());
                ZYPEZDItemValueSetter.setValue(insRebMsg.invTrxBalLastUpdTs, arApplyIntfcWrkT.invTrxBalLastUpdTs.getValue());
                ZYPEZDItemValueSetter.setValue(insRebMsg.invTrxBalTz, arApplyIntfcWrkT.invTrxBalTz.getValue());
                ZYPEZDItemValueSetter.setValue(insRebMsg.dealApplyAmt, arApplyIntfcWrkT.dealApplyAmt.getValue());

                EZDTBLAccessor.create(insRebMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(insRebMsg.getReturnCode())) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Call ApplyAPI
     * @param applyGrpKey
     * @param rcptNum
     * @param mode
     * @param arTrxNum String
     * @param orgInvNum String
     * @return boolean
     */
    private boolean callApplyAPI(String applyGrpKey, String rcptNum, String mode, String arTrxNum, String orgInvNum) {

        NFZC301001 api = new NFZC301001();
        NFZC301001PMsg apiMsg = new NFZC301001PMsg();
        apiMsg.glblCmpyCd.setValue(this.glblCmpyCd);
        apiMsg.applyGrpKey.setValue(applyGrpKey);
        apiMsg.dealSqNum.setValue(DEAL_SQ_NUM);
        apiMsg.batDt.setValue(this.batProcDate);
        api.execute(apiMsg, S21ApiInterface.ONBATCH_TYPE.BATCH);
        String result = apiMsg.getReturnCode();
        String strMl = "";

        if (!APPLY_RTNCD_NORMAL.equals(result)) {
                // result == "0"
            if (APPLY_RTNCD_UN_PROCCES.equals(result)) {
                strMl = API_ERR_UNPROCESSING;
                // result == "2"
            } else if (APPLY_RTNCD_CASHAPP_ERROR.equals(result)) {
                strMl = API_ERR_CASH_APPLICATION_ERROR;
                // result == "3"
            } else if (APPLY_RTNCD_EXCLUSION_ERROR.equals(result)) {
                strMl = API_ERR_EXCLUSION_ERROR;
                // result == "4"
            } else if (APPLY_RTNCD_OTHERS_ERROR.equals(result)) {
                strMl = API_ERR_OTHER_ERROR;
            }
            errCnt++;
            errMlCnt++;
            String[] str = {mode, applyGrpKey};
            createMailMsgInfo(arTrxNum, orgInvNum, this.errRebInfo, result, strMl, NFCM0812E, str);
            return false;
        }
        return true;
    }

    /**
     * Get RcptNum
     * @return rcptNum
     */
    private String getRcptNum() {
        String rcptNum = null;
        rcptNum = ZYPNumbering.getUniqueID(BIZAPL_RCPTNUMKEY);
        return rcptNum;
    }

    /**
     * Get ArTrxBalPk
     * @return arTrxBalPk
     */
    private BigDecimal getArTrxBalPk() {
        BigDecimal arTrxBalPk = null;

        NFCNumbering nfcNumbering = new NFCNumbering();
        NFXC3060Bean outGetNumber = nfcNumbering.getNumber(ZYPOracleSeqAccessor.AR_TRX_BAL_SQ, "", AR_TRX_BAL_SQ_START_NUM);
        if (AR_TRX_BAL_SQ_RTNCD_NORMAL.equals(outGetNumber.getRtrnNo())) {
            arTrxBalPk = outGetNumber.getOraSqNo();
        }
        return arTrxBalPk;
    }

    /**
     * Find AR_RCPT Info
     * @param rcptNum
     * @return AR_RCPTTMsg
     */
    private AR_RCPTTMsg findArRcptInfo(String rcptNum) {

        AR_RCPTTMsg inMsg = new AR_RCPTTMsg();

        inMsg.glblCmpyCd.setValue(this.glblCmpyCd);
        inMsg.rcptNum.setValue(rcptNum);

        AR_RCPTTMsg outMsg = (AR_RCPTTMsg) EZDTBLAccessor.findByKey(inMsg);
        return outMsg;
    }

    /**
     * Find AR_TRX_BAL Info
     * @param arTrxBalPk
     * @return AR_TRX_BALTMsg
     */
    private AR_TRX_BALTMsg findArTrxBalInfo(BigDecimal arTrxBalPk) {

        AR_TRX_BALTMsg inMsg = new AR_TRX_BALTMsg();

        inMsg.glblCmpyCd.setValue(this.glblCmpyCd);
        inMsg.arTrxBalPk.setValue(arTrxBalPk);

        AR_TRX_BALTMsg outMsg = (AR_TRX_BALTMsg) EZDTBLAccessor.findByKey(inMsg);
        return outMsg;
    }

    /**
     * Convert Zero
     * @param param BigDecimal
     * @param scale int 
     * @return retVal
     */
    public static BigDecimal convertZero(BigDecimal param, int scale) {

        BigDecimal retVal = null;
        // param == null
        if (!ZYPCommonFunc.hasValue(param)) {
            retVal = BigDecimal.ZERO;
        } else {
            retVal = getRoundHalfUp(param, scale);
        }
        return retVal;
    }

    /**
     * Get RoundHalfUp
     * @param param BigDecimal
     * @param scale int
     * @return retVal
     */
    public static BigDecimal getRoundHalfUp(BigDecimal param, int scale) {

        BigDecimal retVal = param;

        if (ZYPCommonFunc.hasValue(param)) {
            retVal = param.setScale(scale, BigDecimal.ROUND_HALF_UP);
        }
        return retVal;
    }

    /**
     * Get Error Rebill Invoice Number
     */
    private void getErrRebInvNum() {
        StringBuilder sb = new StringBuilder();
        String rebInvNum = null;

        for (AR_APPLY_INTFC_WRKTMsg arApplyIntfcWrkT : this.rebInvIntfcWrkTMsgList) {
            rebInvNum = arApplyIntfcWrkT.invNum.getValue();
            if (sb.length() != 0) {
                sb.append(",");
            }
            sb.append(rebInvNum);
        }
        this.errRebInfo = sb.toString();
    }

    /**
     * Set Cash Apply Pattern
     * @param arAutoPurOfFlg String
     * @param crRebRsnCatCd String
     * @param arCashAppStsCd String
     * @param sysSrcCd String
     * @return boolean
     */
    private boolean setCashAppPtn(String arAutoPurOfFlg, String crRebRsnCatCd, String arCashAppStsCd, String sysSrcCd) {
        // START 2016/07/29 S.Fujita [QC#12680,MOD]
//        if (NFC.equals(this.subSysId)) {
        if (SYS_SRC.S21_ACCOUNTING_AR.equals(sysSrcCd)) {
        // END   2016/07/29 S.Fujita [QC#12680,MOD]
            if (ZYPConstant.FLG_ON_Y.equals(arAutoPurOfFlg)) {
                if (AR_CASH_APPLY_STS.UNAPPLIED.equals(arCashAppStsCd)) {
                    this.flgOrgInvApp = ZYPConstant.FLG_ON_Y;
                    this.flgRebInvApp = ZYPConstant.FLG_OFF_N;
                } else if (AR_CASH_APPLY_STS.PARTIAL.equals(arCashAppStsCd)) {
                    this.flgOrgInvApp = ZYPConstant.FLG_ON_Y;
                    this.flgRebInvApp = ZYPConstant.FLG_ON_Y;
                } else if (AR_CASH_APPLY_STS.APPLIED.equals(arCashAppStsCd)) {
                    this.flgOrgInvApp = ZYPConstant.FLG_OFF_N;
                    this.flgRebInvApp = ZYPConstant.FLG_ON_Y;
                }
                return true;
            } else {
                return false;
            }
        } else {
            if (crRebRsnCatCd.equals(EXTERNAL)) {
                if (AR_CASH_APPLY_STS.UNAPPLIED.equals(arCashAppStsCd)) {
                    this.flgOrgInvApp = ZYPConstant.FLG_ON_Y;
                    this.flgRebInvApp = ZYPConstant.FLG_OFF_N;
                } else if (AR_CASH_APPLY_STS.PARTIAL.equals(arCashAppStsCd)) {
                    this.flgOrgInvApp = ZYPConstant.FLG_ON_Y;
                    this.flgRebInvApp = ZYPConstant.FLG_ON_Y;
                } else if (AR_CASH_APPLY_STS.APPLIED.equals(arCashAppStsCd)) {
                    this.flgOrgInvApp = ZYPConstant.FLG_OFF_N;
                    this.flgRebInvApp = ZYPConstant.FLG_ON_Y;
                }
                return true;
            } else if (INTERNAL.equals(crRebRsnCatCd)) {
                this.flgOrgInvApp = ZYPConstant.FLG_OFF_N;
                this.flgRebInvApp = ZYPConstant.FLG_ON_Y;
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * getEndofMonth
     * @param date String "YYYYMMDD'
     * @return lastDay String "YYYYMMDD"
     */
    public static String getEndofMonth(String date) {

        String lastDay = null;
        int year = Integer.parseInt(date.substring(0, YEAR));
        int month = Integer.parseInt(date.substring(YEAR, MONTH));

        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month - 1);
        c.set(Calendar.DATE, 1);
        int day = c.getActualMaximum(Calendar.DATE);

        lastDay = date.substring(0, MONTH) + day;

        return lastDay;
    }

    /**
     * Create Mail Message Information
     * @param crmNum String
     * @param orgNum String
     * @param rebNum String[]
     * @param errCd String
     * @param errNm String
     * @param xxMsgId String
     * @param str List of String[]
     */
    public void createMailMsgInfo(String crmNum, String orgNum, String rebNum, String errCd,  String errNm, String xxMsgId, String[] str) {
        // Get message
        String message = S21MessageFunc.clspGetMessage(xxMsgId, str);

        if (this.mailMsgInfo == null) {
            this.mailMsgInfo = new StringBuilder();
        }
        //Generate the Contents
        this.mailMsgInfo.append("CRM#            : ");
        this.mailMsgInfo.append(crmNum);
        this.mailMsgInfo.append(LINE_FEED_CODE);
        this.mailMsgInfo.append("Original INV#   : ");
        this.mailMsgInfo.append(orgNum);
        this.mailMsgInfo.append(LINE_FEED_CODE);
        this.mailMsgInfo.append("REBILL INV#     : ");
        this.mailMsgInfo.append(rebNum);
        this.mailMsgInfo.append(LINE_FEED_CODE);
        this.mailMsgInfo.append("Error Reason    : ");
        this.mailMsgInfo.append(errCd + ":" + errNm);
        this.mailMsgInfo.append(LINE_FEED_CODE);
        this.mailMsgInfo.append("Message         : ");
        this.mailMsgInfo.append(message);
        this.mailMsgInfo.append(LINE_FEED_CODE);
        this.mailMsgInfo.append("-------------------------------------------------------------------------");
        this.mailMsgInfo.append(LINE_FEED_CODE);
    }

    /**
     * Send eMail
     */
    private void eMailSend() {
        if (errMlCnt != 0) {
            //=========================================
            // Mail Template
            // =========================================
            S21MailTemplate s21MailTemplate = new S21MailTemplate(this.glblCmpyCd, MAIL_NFCB0580M001);
            if (!ZYPCommonFunc.hasValue(s21MailTemplate.getSubject()) || !ZYPCommonFunc.hasValue(s21MailTemplate.getBody())) {
                execAbendException(NFCM0002E, new String[] {"Mail Template nothing Error"});
            }
            s21MailTemplate.setTemplateParameter("Msg", this.mailMsgInfo.toString());
            //=========================================
            // Mail From Address
            //=========================================
            S21MailGroup groupFrom = new S21MailGroup(this.glblCmpyCd, MAIL_GRP_ID_FROM);
            List<S21MailAddress> addrFromList = groupFrom.getMailAddress();
            if (addrFromList.size() <= 0) {
                execAbendException(NFCM0002E, new String[] {"Mail Template nothing Error"});
            }
            //=========================================
            // Mail To Address
            //=========================================
            S21MailGroup groupTo = new S21MailGroup(this.glblCmpyCd, MAIL_GRP_ID_TO);
            groupTo.setMailKey1(MAIL_KEY_1_TO);
            List<S21MailAddress> addrToList = groupTo.getMailAddress();
            if (addrToList.size() <= 0) {
                execAbendException(NFCM0002E, new String[] {"Mail Template nothing Error"});
            }
            //=========================================
            //  Send mail
            //=========================================
            S21Mail mail = new S21Mail(this.glblCmpyCd);
            mail.setMailTemplate(s21MailTemplate);
            mail.setFromAddress(addrFromList.get(0));
            mail.setToAddressList(addrToList);
            String mailEvent = mail.postMail();
            if (!ZYPCommonFunc.hasValue(mailEvent)) {
                return;
            }
        }
    }

    /**
     * checkNull
     * @param val String
     * @return val String
     */
    private String checkNull(String val) {
        if (!ZYPCommonFunc.hasValue(val)) {
            return BLANK;
        } else {
            return val;
        }
    }
}
