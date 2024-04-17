/**
 * Copyright (c) 2016 Canon USA Inc. All rights reserved.
 **/
package com.canon.cusa.s21.batch.NFB.NFBB111001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDDebugOutput;
import business.db.CCYTMsg;
import business.db.CM_MAINT_INVTMsg;
import business.db.GLBL_CMPYTMsg;
import business.parts.NFBCommonBusiness;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AP_DS_WF_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AP_MAINT_INV_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_INV_CHRG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmBooleanResultSetHandlerSupport;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * Tolerance limit for AP Invoice maintenance 
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/01/2015   CUSA            Y.Aikawa        Create          N/A
 * 08/05/2016   Fujitsu         T.Murai         Update          QC#12711
 * 2016/09/21   Hitachi         K.Kojima        Update          QC#11168
 * 2017/11/06   CITS            K.Ogino         Update          QC#22192
 * 2017/12/26   Hitachi         J.Kim           Update          QC#22458
 * 2019/02/28   Fujitsu         S.Ohki          Update          QC#30584
 * </pre>
 */
public class NFBB111001 extends S21BatchMain implements NFBB111001Constant {

    /** User Profile */
    private S21UserProfileService profile;
    /** Global Company Code */
    private String glblCmpyCd;
    /** Batch Process Date */
    private String procDt;
    /** SSM Batch Client */
    private S21SsmBatchClient ssmBatchClient;
    /** TERM_CD */
    TERM_CD termCd = TERM_CD.NORMAL_END;

    /** Commit Count (CM_MAINT_INV) */
    private int cmMaintInvCommitCount;
    /** List For handling CM_MAINT_INV Bulk TBL Accessor */
    private List<CM_MAINT_INVTMsg> listCmMaintInvTMsg;
    /** CM_MAINT_INV Bulk Update Count */
    private int iCntCmMaintInv;
    /** Total Commit Count */
    private int totalCommitCount;
    
    /** Standard Currrency Code */
    private String stdCcyCd;
    /** Accounting Arithmetic Type Code */
    private String acctArthTpCd;
    /** After Decimal Point Digit Number */
    private BigDecimal aftDeclPntDigitNum;

    /** AP_MAINT_INV_FROM_AMT */
    private BigDecimal apMaintInvFromAmt;
    /** AP_MAINT_INV_TO_AMT */
    private BigDecimal apMaintInvToAmt;
    /** AP_MAINT_INV_BASE_FROM_RATE */
    private BigDecimal apMaintInvBaseFromRate;
    /** AP_MAINT_INV_BASE_TO_RATE */
    private BigDecimal apMaintInvBaseToRate;
    /** AP_MAINT_INV_OVER_FROM_RATE */
    private BigDecimal apMaintInvOverFromRate;
    /** AP_MAINT_INV_OVER_TO_RATE */
    private BigDecimal apMaintInvOverToRate;
    /** AP_MAINT_INV_FROM_CNT */
    private BigDecimal apMaintInvFromCnt;
    /** AP_MAINT_INV_TO_CNT */
    private BigDecimal apMaintInvToCnt;
    /** AP_MAINT_INV_MTR_FROM_RATE */
    private BigDecimal apMaintInvMtrFromRate;
    /** AP_MAINT_INV_MTR_TO_RATE */
    private BigDecimal apMaintInvMtrToRate;
    /** AP_MAINT_INV_MTH_TOL_CNT */
    private BigDecimal apMaintInvMthTolCnt;

    /** List for Approval Email Information(Subject) */
    private List<String> listApvlEmlSubject;
    /** List for Error Email Information(Subject) */
    private List<String> listErrEmlSubject;
    /** List for Approval Email Information(Message) */
    private List<String> listApvlEmlMessage;
    /** List for Error Email Information(Message) */
    private List<String> listErrEmlMessage;
    /** List for Approval Email Information(Contract Admin Info) */
    private List<Map<String, String>> listApvlEmlContrAdminInfo;
    /** List for Error Email Information(Contract Admin Info) */
    private List<Map<String, String>> listErrEmlContrAdminInfo;
    /** List for Approval Email Index */
    private List<Boolean> listApvlEmlIndex;
    /** List for Error Email Index */
    private List<Boolean> listErrEmlIndex;

    /**
     * main method, which be called by Batch Shell
     * @param args argument
     */
    public static void main(final String[] args) {
        /** Initialize S21BatchMain */
        new NFBB111001().executeBatch(NFBB111001.class.getSimpleName());
    }
    @Override
    protected void initRoutine() {
        /** Get User Profile Service Instance */
        this.profile = S21UserProfileServiceFactory.getInstance().getService();
        /** Get Global Company Code */
        this.glblCmpyCd = this.profile.getGlobalCompanyCode();
        /** Initialize SSM Batch client. */
        ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        /** Initialize Commit Count */
        initCommitCount();
        /** Initialize List for bulk TBL Accessor */
        initListForBulkTBLAccessor();
        /** Initialize Bulk Update Count */
        initBulkCount();
        /** Initialize AP_MAINT_TOL values */
        initApMaintTolValue();
        /** Initialize Batch Process Date */
        procDt = ZYPDateUtil.getBatProcDate();
        /** Initialize List for Approval Email Information */
        listApvlEmlSubject = new ArrayList<String>();
        listApvlEmlMessage = new ArrayList<String>();
        listApvlEmlContrAdminInfo = new ArrayList<Map<String, String>>();
        listApvlEmlIndex = new ArrayList<Boolean>();
        /** Initialize List for Error Email Information */
        listErrEmlSubject = new ArrayList<String>();
        listErrEmlMessage = new ArrayList<String>();
        listErrEmlContrAdminInfo = new ArrayList<Map<String, String>>();
        listErrEmlIndex = new ArrayList<Boolean>();
    }

    @Override
    protected void mainRoutine() {
        GLBL_CMPYTMsg glblCmpy = new GLBL_CMPYTMsg();
        ZYPEZDItemValueSetter.setValue(glblCmpy.glblCmpyCd, this.glblCmpyCd);
        glblCmpy = (GLBL_CMPYTMsg)S21FastTBLAccessor.findByKey(glblCmpy);
        if (glblCmpy == null) {
            throw new S21AbendException(NFBM0028E);
        } else {
            stdCcyCd = glblCmpy.stdCcyCd.getValue();
        }
        CCYTMsg ccy = new CCYTMsg();
        ZYPEZDItemValueSetter.setValue(ccy.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(ccy.ccyCd, stdCcyCd);
        ccy = (CCYTMsg)S21FastTBLAccessor.findByKey(ccy);
        if (ccy == null) {
            throw new S21AbendException(NFBM0028E);
        } else {
            acctArthTpCd = ccy.acctArthTpCd.getValue();
            aftDeclPntDigitNum = ccy.aftDeclPntDigitNum.getValue();
        }
        // Get AP_MAINT_TOL table value
        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("apMaintTolCd", AP_MAINT_TOL_CD_TOLERANCE);
        Boolean bRet = (Boolean) ssmBatchClient.queryObject(SELECT_AP_MAINT_TOL, queryParam, new SelectApMaintTolHandler());
        if (!bRet) {
            throw new S21AbendException(NFBM0028E);
        }
        queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        // START 2017/12/26 J.Kim [QC#22458,MOD]
        // queryParam.put("apDsWfStsCd", AP_DS_WF_STS_CD_00);
        // queryParam.put("apMaintInvStsCd", AP_MAINT_INV_STS_CD_10);
        queryParam.put("apDsWfStsCd", AP_DS_WF_STS.PENDING);
        queryParam.put("apMaintInvStsCd", AP_MAINT_INV_STS.INVOICE_BATCH_ENTRY_COMPLETED);
        // END 2017/12/26 J.Kim [QC#22458,MOD]
        bRet = (Boolean) ssmBatchClient.queryObject(SELECT_CM_MAINT_INV, queryParam, new SelectCmMaintInvHandler());
        if (bRet == Boolean.TRUE) {
            if (iCntCmMaintInv > 0) {
                bulkUpdateCmMaintInv();
            }
            // Send Email
            for (int i = 0; i < listApvlEmlIndex.size(); i++) {
                boolean send = (Boolean)listApvlEmlIndex.get(i);
                if (send) {
                    String subject = (String)listApvlEmlSubject.get(i);
                    String message = (String)listApvlEmlMessage.get(i);
                    Map<String, String> contrAdminInfoMap = (Map<String, String>)listApvlEmlContrAdminInfo.get(i);
                    sendApprovalEmail(subject, message, contrAdminInfoMap);
                }
            }
            for (int i = 0; i < listErrEmlIndex.size(); i++) {
                boolean send = (Boolean)listErrEmlIndex.get(i);
                if (send) {
                    String subject = (String)listErrEmlSubject.get(i);
                    String message = (String)listErrEmlMessage.get(i);
                    Map<String, String> contrAdminInfoMap = (Map<String, String>)listErrEmlContrAdminInfo.get(i);
                    sendErrorEmail(subject, message, contrAdminInfoMap);
                }
            }
            commit();
        } else {
            rollback();
        }        
    }
    @Override
    protected void termRoutine() {
        this.totalCommitCount = cmMaintInvCommitCount;
        /** Normal End this process */
        setTermState(termCd, totalCommitCount, 0, 0);
    }
    /**
     * Private class: SelectApMaintTolHandler
     */
    private class SelectApMaintTolHandler extends S21SsmBooleanResultSetHandlerSupport {
        public Boolean doProcessQueryResult(ResultSet rs) throws SQLException {
            if (rs.next()) {
                apMaintInvFromAmt = NFBCommonBusiness.chkNull(rs.getBigDecimal(AP_MAINT_INV_FROM_AMT));
                apMaintInvToAmt = NFBCommonBusiness.chkNull(rs.getBigDecimal(AP_MAINT_INV_TO_AMT));
                apMaintInvBaseFromRate = NFBCommonBusiness.chkNull(rs.getBigDecimal(AP_MAINT_INV_BASE_FROM_RATE));
                apMaintInvBaseToRate = NFBCommonBusiness.chkNull(rs.getBigDecimal(AP_MAINT_INV_BASE_TO_RATE));
                apMaintInvOverFromRate = NFBCommonBusiness.chkNull(rs.getBigDecimal(AP_MAINT_INV_OVER_FROM_RATE));
                apMaintInvOverToRate = NFBCommonBusiness.chkNull(rs.getBigDecimal(AP_MAINT_INV_OVER_TO_RATE));
                apMaintInvFromCnt = NFBCommonBusiness.chkNull(rs.getBigDecimal(AP_MAINT_INV_FROM_CNT));
                apMaintInvToCnt = NFBCommonBusiness.chkNull(rs.getBigDecimal(AP_MAINT_INV_TO_CNT));
                apMaintInvMtrFromRate = NFBCommonBusiness.chkNull(rs.getBigDecimal(AP_MAINT_INV_MTR_FROM_RATE));
                apMaintInvMtrToRate = NFBCommonBusiness.chkNull(rs.getBigDecimal(AP_MAINT_INV_MTR_TO_RATE));
                apMaintInvMthTolCnt = NFBCommonBusiness.chkNull(rs.getBigDecimal(AP_MAINT_INV_MTH_TOL_CNT));
                return Boolean.TRUE;
            }
            return Boolean.FALSE;
        }
    }

    /**
     * Private class: SelectCmMaintInvHandler
     */
    private class SelectCmMaintInvHandler extends S21SsmBooleanResultSetHandlerSupport {
        public Boolean doProcessQueryResult(ResultSet rs) throws SQLException {

            String prevApInvNum = EMPTY_STRING;
            String prevApVndCd = EMPTY_STRING;
            String prevSerNum = EMPTY_STRING;
            String prevStartDt = EMPTY_STRING;

            BigDecimal basePrcDealAmt = BigDecimal.ZERO;
            BigDecimal bwMtrAlwncCnt = BigDecimal.ZERO;
            BigDecimal bwMtrPrcAmtRate = BigDecimal.ZERO;
            BigDecimal colorMtrAlwncCnt = BigDecimal.ZERO;
            BigDecimal colorMtrPrcAmtRate = BigDecimal.ZERO;
            BigDecimal contrFromAmt = BigDecimal.ZERO;
            BigDecimal contrToAmt = BigDecimal.ZERO;
            String contrAdminPsnCd = EMPTY_STRING;
            String emlAddr = EMPTY_STRING;
            String langCd = EMPTY_STRING;
            String ctryCd = EMPTY_STRING;
            String firstNm = EMPTY_STRING;
            String midNm = EMPTY_STRING;
            String lastNm = EMPTY_STRING;

            boolean isError = false;

            // Email message body for Auto Approval Email
            StringBuilder sbMessageBodyApvlEmail = new StringBuilder();
            // Email message subject for Error Email
            StringBuilder sbMessageSubjectErrorEmail = new StringBuilder();
            // Email message body for Error Email
            StringBuilder sbMessageBodyErrorEmail = new StringBuilder();
            // Email message subject for Auto Approval Email
            StringBuilder sbMessageSubjectApvlEmail = new StringBuilder();

            while (rs.next()) {

                String apInvNum = NFBCommonBusiness.chkNull(rs.getString(AP_INV_NUM));
                String apVndCd = NFBCommonBusiness.chkNull(rs.getString(AP_VND_CD));
                String serNum = NFBCommonBusiness.chkNull(rs.getString(SER_NUM));
                String startDt = NFBCommonBusiness.chkNull(rs.getString(START_DT));
                String cntTpCd = NFBCommonBusiness.chkNull(rs.getString(CNT_TP_CD));
                BigDecimal startReadMtrCnt = NFBCommonBusiness.chkNull(rs.getBigDecimal(START_READ_MTR_CNT));
                BigDecimal endReadMtrCnt = NFBCommonBusiness.chkNull(rs.getBigDecimal(END_READ_MTR_CNT));
                BigDecimal readMtrCnt = NFBCommonBusiness.chkNull(rs.getBigDecimal(READ_MTR_CNT));
                BigDecimal apTolAmt = NFBCommonBusiness.chkNull(rs.getBigDecimal(AP_TOL_AMT));
                BigDecimal baseAmt = rs.getBigDecimal(BASE_AMT);
                BigDecimal apInvAmt = NFBCommonBusiness.chkNull(rs.getBigDecimal(AP_INV_AMT));
                String ovrdSerNum = NFBCommonBusiness.chkNull(rs.getString(OVRD_SER_NUM));
                String siteNm = NFBCommonBusiness.chkNull(rs.getString(VND_SITE_NM));
                String endDt = NFBCommonBusiness.chkNull(rs.getString(END_DT));
                String cntrTpNm = NFBCommonBusiness.chkNull(rs.getString(CNTR_TP_NM));
                String invDt = NFBCommonBusiness.chkNull(rs.getString(INV_DT));
                String prntVndNm = NFBCommonBusiness.chkNull(rs.getString(PRNT_VND_NM));
                BigDecimal apTaxAmt = NFBCommonBusiness.chkNull(rs.getBigDecimal(AP_TAX_AMT));
                BigDecimal lateFeeAmt = NFBCommonBusiness.chkNull(rs.getBigDecimal(LATE_FEE_AMT));

                if (!serNum.equals(prevSerNum)) {
                    // Get contract
                    List<Map<String, Object>> contrList = getContractFromSerNum(serNum);
                    if (contrList.size() == 0) {
                        if (!isError) {
                            isError = true;
                        }
                    } else {
                        Map<String, Object> contrMap = (Map<String, Object>)contrList.get(0);
                        basePrcDealAmt = NFBCommonBusiness.chkNull((BigDecimal)contrMap.get(BASE_PRC_DEAL_AMT));
                        bwMtrAlwncCnt = NFBCommonBusiness.chkNull((BigDecimal)contrMap.get(BW_MTR_ALWNC_CNT));
                        bwMtrPrcAmtRate = NFBCommonBusiness.chkNull((BigDecimal)contrMap.get(BW_MTR_PRC_AMT_RATE));
                        colorMtrAlwncCnt = NFBCommonBusiness.chkNull((BigDecimal)contrMap.get(COLOR_MTR_ALWNC_CNT));
                        colorMtrPrcAmtRate = NFBCommonBusiness.chkNull((BigDecimal)contrMap.get(COLOR_MTR_PRC_AMT_RATE));
                        contrAdminPsnCd = NFBCommonBusiness.chkNull((String)contrMap.get(CONTR_ADMIN_PSN_CD));
                        emlAddr = NFBCommonBusiness.chkNull((String)contrMap.get(EML_ADDR));
                        langCd = NFBCommonBusiness.chkNull((String)contrMap.get(LANG_CD));
                        ctryCd = NFBCommonBusiness.chkNull((String)contrMap.get(CTRY_CD));
                        firstNm = NFBCommonBusiness.chkNull((String)contrMap.get(FIRST_NM));
                        midNm = NFBCommonBusiness.chkNull((String)contrMap.get(MID_NM));
                        lastNm = NFBCommonBusiness.chkNull((String)contrMap.get(LAST_NM));
                    }
                }

                // QC#22192 Start
                String chkTpCd = CHK_TP_BASE_AND_METER;
                BigDecimal retReadMtrCnt = rs.getBigDecimal(READ_MTR_CNT);
                BigDecimal retBaseAmt = rs.getBigDecimal(BASE_AMT);
                if (ZYPCommonFunc.hasValue(retBaseAmt) && ZYPCommonFunc.hasValue(retReadMtrCnt)) {
                    /*
                     * - Invoice Total Amount Check
                     * - Base Amount Check
                     * - Meter Amount Check
                     * - Meter Count Check
                     */
                    chkTpCd = CHK_TP_BASE_AND_METER;
                } else if (ZYPCommonFunc.hasValue(retBaseAmt) && !ZYPCommonFunc.hasValue(retReadMtrCnt)) {
                    /*
                     * - Invoice Total AmountのCheck
                     * - Base AmountのCheck
                     */
                    chkTpCd = CHK_TP_BASE;
                } else if (!ZYPCommonFunc.hasValue(retBaseAmt) && ZYPCommonFunc.hasValue(retReadMtrCnt)) {
                    /*
                     * - Invoice Total Amount Check
                     * - Meter Amount Check
                     * - Meter Count Check
                     */
                    chkTpCd = CHK_TP_METER;
                }
                // QC#22192 End
                /*--------------------------------------------------------------
                  Check by Invoice
                --------------------------------------------------------------*/
                if (!apInvNum.equals(prevApInvNum)
                ||  !apVndCd.equals(prevApVndCd)) {
                    if (ZYPCommonFunc.hasValue(prevApInvNum)
                    ||  ZYPCommonFunc.hasValue(prevApVndCd)) {
                        // Not First record
                        String strMessage = sbMessageBodyApvlEmail.toString();
                        listApvlEmlMessage.add(strMessage);
                        // Initialize message body string for Auto Approval Email
                        sbMessageBodyApvlEmail = new StringBuilder();
                        strMessage = sbMessageBodyErrorEmail.toString();
                        listErrEmlMessage.add(strMessage);
                        // Initialize message body string for Error Email
                        sbMessageBodyErrorEmail = new StringBuilder();
                    }
                    
                    /*------------------------------------------------------
                     (1) Check invoice total amount and tolerance amount
                     ------------------------------------------------------*/
                    if (apMaintInvFromAmt.compareTo(apInvAmt) == -1
                    &&  apInvAmt.compareTo(apMaintInvToAmt) == -1) {
                        // OK
                    } else {
                        // NG
                        if (!isError) {
                            isError = true;
                        }
                    }
                    // For Error Email
                    sbMessageSubjectErrorEmail = new StringBuilder();
                    sbMessageSubjectErrorEmail.append("AP Maintenance Invoice Approval Notification; Invoice #: ");
                    sbMessageSubjectErrorEmail.append(apInvNum);
                    sbMessageSubjectErrorEmail.append("; Invoice Amount: ");
                    sbMessageSubjectErrorEmail.append(NFBCommonBusiness.formatEmailAmountString(apInvAmt));
                    sbMessageSubjectErrorEmail.append("; Invoice Date: ");
                    sbMessageSubjectErrorEmail.append(NFBCommonBusiness.getMMDDYYYYWithSlash(invDt));
                    sbMessageSubjectErrorEmail.append("; Supplier Name: ");
                    sbMessageSubjectErrorEmail.append(prntVndNm);
                    sbMessageSubjectErrorEmail.append("; Invoice Amount Less Taxes And Fees: ");
                    BigDecimal emailAmt = apInvAmt.subtract(apTaxAmt).subtract(lateFeeAmt);
                    sbMessageSubjectErrorEmail.append(NFBCommonBusiness.formatEmailAmountString(emailAmt));
                    String strSubject= sbMessageSubjectErrorEmail.toString();
                    if (strSubject.length() > 200) {
                        strSubject = strSubject.substring(0, 200);
                    }
                    listErrEmlSubject.add(strSubject);

                    // For Auto Approval Email
                    sbMessageSubjectApvlEmail = new StringBuilder();
                    sbMessageSubjectApvlEmail.append("Automated Approval Notification");
                    strSubject= sbMessageSubjectApvlEmail.toString();
                    if (strSubject.length() > 200) {
                        strSubject = strSubject.substring(0, 200);
                    }
                    listApvlEmlSubject.add(strSubject);

                    sbMessageBodyApvlEmail.append("Invoice Amount: ");
                    sbMessageBodyApvlEmail.append(NFBCommonBusiness.formatEmailAmountString(apInvAmt));
                    sbMessageBodyApvlEmail.append(". Tolerance Low: ");
                    sbMessageBodyApvlEmail.append(NFBCommonBusiness.formatEmailAmountString(apMaintInvFromAmt));
                    sbMessageBodyApvlEmail.append(". Tolerance High: ");
                    sbMessageBodyApvlEmail.append(NFBCommonBusiness.formatEmailAmountString(apMaintInvToAmt));
                    sbMessageBodyApvlEmail.append(LINE_FEED_CODE);

                    Map<String, String> contrAdminMap = new HashMap<String, String>();
                    contrAdminMap.put(CONTR_ADMIN_PSN_CD, contrAdminPsnCd);
                    contrAdminMap.put(EML_ADDR, emlAddr);
                    contrAdminMap.put(LANG_CD, langCd);
                    contrAdminMap.put(CTRY_CD, ctryCd);
                    contrAdminMap.put(FIRST_NM, firstNm);
                    contrAdminMap.put(MID_NM, midNm);
                    contrAdminMap.put(LAST_NM, lastNm);
                    listApvlEmlContrAdminInfo.add(contrAdminMap);
                    listErrEmlContrAdminInfo.add(contrAdminMap);

                }
                /*--------------------------------------------------------------
                  Check by Serial Number
                --------------------------------------------------------------*/
                if (!apInvNum.equals(prevApInvNum)
                ||  !apVndCd.equals(prevApVndCd)
                ||  !serNum.equals(prevSerNum)
                ||  !startDt.equals(prevStartDt)
                ) {
                    // Add Start 2016/08/05 QC#12711
                    // Serial Number Exist Check
                    if (!ZYPConstant.FLG_ON_Y.equals(ovrdSerNum)) {
                        if (!checkSerial(serNum, siteNm)){
                            if (!isError) {
                                isError = true;
                            }
                        }
                    }
                    // Add End 2016/08/05 QC#12711

                    /*-----------------------------------------------------------
                     (2) Check invoice base amount and contract base amount with
                         tolerance amount.
                    -----------------------------------------------------------*/
                    if (CHK_TP_BASE_AND_METER.equals(chkTpCd) || CHK_TP_BASE.equals(chkTpCd)) {
                        contrFromAmt = basePrcDealAmt.multiply(apMaintInvBaseFromRate);
                        // contrFromAmt = contrFromAmt.setScale(aftDeclPntDigitNum.intValue(), BigDecimal.ROUND_HALF_UP); // #8554 Comment Out
                        contrToAmt = basePrcDealAmt.multiply(apMaintInvBaseToRate);
                        // contrToAmt = contrToAmt.setScale(aftDeclPntDigitNum.intValue(), BigDecimal.ROUND_HALF_UP); // #8554 Comment Out
                        contrFromAmt = basePrcDealAmt.subtract(contrFromAmt);
                        contrToAmt = basePrcDealAmt.add(contrToAmt);

                        if (baseAmt.compareTo(contrFromAmt) >= 0
                        &&  baseAmt.compareTo(contrToAmt)   <= 0) {
                            // OK
                        } else {
                            // NG
                            if (!isError) {
                                isError = true;
                            }
                        }
                    }
                    /*--------------------------------------------------------------
                      (4) Check Meter Counts with past invoice
                      --------------------------------------------------------------*/
                    if (CHK_TP_BASE_AND_METER.equals(chkTpCd) || CHK_TP_METER.equals(chkTpCd)) {
                        // Get Average Copy Quantity by Meter Read Date
                        List<Map<String, Object>> listAvgCopyQtyByReadDt = getAvgCopyQtyByReadDt(serNum);
                        int validListCount = 0;
                        if (ZYPCommonFunc.hasValue(apMaintInvMthTolCnt)) {
                            validListCount = apMaintInvMthTolCnt.intValue();
                        }
                        if (validListCount > listAvgCopyQtyByReadDt.size()) {
                            validListCount = listAvgCopyQtyByReadDt.size();
                        }
                        BigDecimal totAvgCopyQty = BigDecimal.ZERO;
                        for (int i = 0; i < validListCount; i++) {
                            Map<String, Object> mapAvgCopyQty = listAvgCopyQtyByReadDt.get(i);
                            BigDecimal avgCopyQty = NFBCommonBusiness.chkNull((BigDecimal)mapAvgCopyQty.get(AVG_COPY_QTY));
                            totAvgCopyQty = totAvgCopyQty.add(avgCopyQty);
                        }
                        BigDecimal svcAvgCopyQty = BigDecimal.ZERO;
                        if (validListCount > 0) {
                        	svcAvgCopyQty = totAvgCopyQty.divide(new BigDecimal(validListCount),0,BigDecimal.ROUND_HALF_UP);
                        }
                        BigDecimal fromQty = BigDecimal.ZERO;
                        BigDecimal toQty = BigDecimal.ZERO;
                        fromQty = readMtrCnt.divide(apMaintInvFromCnt, 0, BigDecimal.ROUND_HALF_UP);
                        fromQty = readMtrCnt.subtract(fromQty);
                        toQty = readMtrCnt.divide(apMaintInvToCnt, 0, BigDecimal.ROUND_HALF_UP);
                        // START 2016/09/21 K.Kojima [QC#11168,ADD]
                        // toQty = readMtrCnt.subtract(toQty);
                        toQty = readMtrCnt.add(toQty);
                        // END 2016/09/21 K.Kojima [QC#11168,ADD]
                        if (svcAvgCopyQty.compareTo(fromQty) >= 0
                        &&  svcAvgCopyQty.compareTo(toQty) <= 0) {
                            // OK
                        } else {
                            // NG
                            if (!isError) {
                                isError = true;
                            }
                        }

                        // For Error Email
                        sbMessageBodyErrorEmail.append("Serial # : ");
                        sbMessageBodyErrorEmail.append(serNum);
                        // Del Start 2016/08/05 QC#11711
//                      sbMessageBodyErrorEmail.append(" Corrected Serial # : ");
//                      sbMessageBodyErrorEmail.append(ovrdSerNum);
                        // Del End 2016/08/05 QC#11711
                        sbMessageBodyErrorEmail.append(" Start Date : ");
                        sbMessageBodyErrorEmail.append(NFBCommonBusiness.getMMDDYYYYWithSlash(startDt));
                        sbMessageBodyErrorEmail.append(" End Date : ");
                        sbMessageBodyErrorEmail.append(NFBCommonBusiness.getMMDDYYYYWithSlash(endDt));
                        sbMessageBodyErrorEmail.append(" Base : ");
                        sbMessageBodyErrorEmail.append(NFBCommonBusiness.formatEmailAmountString(baseAmt));
                        sbMessageBodyErrorEmail.append(" Reason : ");
                        // TODO reason (list of tolerances that the invoice failed for?)
                        sbMessageBodyErrorEmail.append(LINE_FEED_CODE);

                        // For Auto Approval Email
                        sbMessageBodyApvlEmail.append("Serial Number: ");
                        sbMessageBodyApvlEmail.append(serNum);
                        sbMessageBodyApvlEmail.append(" Base Amount: ");
                        sbMessageBodyApvlEmail.append(NFBCommonBusiness.formatEmailAmountString(baseAmt));
                        sbMessageBodyApvlEmail.append(" Tolerance Low: ");
                        sbMessageBodyApvlEmail.append(NFBCommonBusiness.formatEmailAmountString(contrFromAmt));
                        sbMessageBodyApvlEmail.append(" Tolerance High: ");
                        sbMessageBodyApvlEmail.append(NFBCommonBusiness.formatEmailAmountString(contrToAmt));
                        sbMessageBodyApvlEmail.append(LINE_FEED_CODE);
                    }
                }
                /*--------------------------------------------------------------
                  Check by Meter Counter
                 -------------------------------------------------------------*/
                BigDecimal fromAmt = BigDecimal.ZERO;
                BigDecimal toAmt = BigDecimal.ZERO;
                if (ZYPCommonFunc.hasValue(cntTpCd)) {
                    /*--------------------------------------------------------------
                      (3) Check invoice Meter Overage amount and contract amount
                          with tolerance amount.
                     --------------------------------------------------------------*/
                    BigDecimal mtrAlwncCnt = BigDecimal.ZERO;
                    BigDecimal mtrPrcAmtRate = BigDecimal.ZERO;
                    if (cntTpCd.equals(CNT_TP_CD_BW)) {
                        mtrAlwncCnt = bwMtrAlwncCnt;
                        mtrPrcAmtRate = bwMtrPrcAmtRate;
                        
                    } else if (cntTpCd.equals(CNT_TP_CD_CLR)) {
                        mtrAlwncCnt = colorMtrAlwncCnt;
                        mtrPrcAmtRate = colorMtrPrcAmtRate;
                    } else {
                        mtrAlwncCnt = bwMtrAlwncCnt;
                        mtrPrcAmtRate = bwMtrPrcAmtRate;
                    }
                    fromAmt = (readMtrCnt.subtract(mtrAlwncCnt)).multiply(mtrPrcAmtRate);
                    fromAmt = fromAmt.subtract(fromAmt.multiply(apMaintInvOverFromRate));
                    toAmt = (readMtrCnt.subtract(mtrAlwncCnt)).multiply(mtrPrcAmtRate);
                    toAmt = toAmt.add(toAmt.multiply(apMaintInvOverToRate));
                    if (apTolAmt.compareTo(fromAmt) >= 0
                    &&  apTolAmt.compareTo(toAmt) <= 0) {
                        // OK
                    } else {
                        // NG
                        isError = true;
                    }
                    /*--------------------------------------------------------------
                      (5) Check final meter
                    --------------------------------------------------------------*/
                    BigDecimal svcEndCnt = BigDecimal.ZERO;
                    List<Map<String, Object>> listSvcPhysMtrRead = getSvcPhysMtrRead(serNum, cntTpCd, endDt);
                    if (listSvcPhysMtrRead.size() > 0) {
                        Map<String, Object> mapSvcPhysMtrRead = (Map<String, Object>)listSvcPhysMtrRead.get(0);
                        BigDecimal dayDiffCnt = (BigDecimal) mapSvcPhysMtrRead.get(DAY_CNT);
                        BigDecimal dayDiff = (BigDecimal) mapSvcPhysMtrRead.get(DAY_DIFF);
                        svcEndCnt = (BigDecimal) mapSvcPhysMtrRead.get(READ_MTR_CNT);
                        if (dayDiffCnt.compareTo(BigDecimal.ZERO) != 0) {
                            List<Map<String, Object>> listSvcAvgMtrReadCnt = getSvcAvgMtrReadCnt(serNum, cntTpCd);
                            BigDecimal avgMtrReadCnt = BigDecimal.ZERO;
                            if (listSvcAvgMtrReadCnt.size() > 0) {
                                Map<String, Object> mapSvcAvgMtrReadCnt = (Map<String, Object>)listSvcAvgMtrReadCnt.get(0);
                                avgMtrReadCnt = (BigDecimal)mapSvcAvgMtrReadCnt.get(AVG_MTR_READ_CNT);
                            }
                            BigDecimal diffCnt = dayDiff.multiply(avgMtrReadCnt);
                            svcEndCnt = svcEndCnt.add(diffCnt);
                        }
                        BigDecimal fromCnt = BigDecimal.ZERO;
                        BigDecimal toCnt = BigDecimal.ZERO;
                        fromCnt = svcEndCnt.multiply(apMaintInvMtrFromRate);
                        toCnt = svcEndCnt.multiply(apMaintInvMtrToRate);
                        fromCnt = svcEndCnt.subtract(fromCnt);
                        toCnt = svcEndCnt.add(toCnt);
                        if (endReadMtrCnt.compareTo(fromCnt) >= 0
                        &&  endReadMtrCnt.compareTo(toCnt) <= 0) {
                            // OK
                        } else {
                            // NG
                            isError = true;
                        }   
                    }
                }

                // For Error Email
                sbMessageBodyErrorEmail.append("Counter Type : ");
                sbMessageBodyErrorEmail.append(cntrTpNm);
                sbMessageBodyErrorEmail.append(" Start Meter : ");
                sbMessageBodyErrorEmail.append(NFBCommonBusiness.formatEmailQtyString(startReadMtrCnt));
                sbMessageBodyErrorEmail.append(" End Meter : ");
                sbMessageBodyErrorEmail.append(NFBCommonBusiness.formatEmailQtyString(endReadMtrCnt));
                sbMessageBodyErrorEmail.append(" Count : ");
                sbMessageBodyErrorEmail.append(NFBCommonBusiness.formatEmailQtyString(readMtrCnt));
                sbMessageBodyErrorEmail.append(" Overage : ");
                sbMessageBodyErrorEmail.append(NFBCommonBusiness.formatEmailAmountString(apTolAmt));
                sbMessageBodyErrorEmail.append(LINE_FEED_CODE);

                // For Auto Approval Email
                sbMessageBodyApvlEmail.append("Serial Number: ");
                sbMessageBodyApvlEmail.append(serNum);
                sbMessageBodyApvlEmail.append(" Counter Type: ");
                sbMessageBodyApvlEmail.append(cntrTpNm);
                sbMessageBodyApvlEmail.append(" Overage Amount: ");
                sbMessageBodyApvlEmail.append(NFBCommonBusiness.formatEmailAmountString(apTolAmt));
                sbMessageBodyApvlEmail.append(" Tolerance Low: ");
                sbMessageBodyApvlEmail.append(NFBCommonBusiness.formatEmailAmountString(fromAmt));
                sbMessageBodyApvlEmail.append(" Tolerance High: ");
                sbMessageBodyApvlEmail.append(NFBCommonBusiness.formatEmailAmountString(toAmt));
                sbMessageBodyApvlEmail.append(LINE_FEED_CODE);

                // START 2017/12/26 J.Kim [QC#22458,MOD]
                // String apDsWfStsCd = AP_DS_WF_STS_CD_01;
                // String apMaintInvStsCd = AP_MAINT_INV_STS_CD_20;
                String apDsWfStsCd = AP_DS_WF_STS.APPROVED;
                String apMaintInvStsCd = AP_MAINT_INV_STS.APPROVED;
                // END 2017/12/26 J.Kim [QC#22458,MOD]
                if (isError) {
                    // START 2017/12/26 J.Kim [QC#22458,MOD]
                    // apDsWfStsCd = AP_DS_WF_STS_CD_00;
                    // apMaintInvStsCd = AP_MAINT_INV_STS_CD_11;
                    apDsWfStsCd = AP_DS_WF_STS.PENDING;
                    apMaintInvStsCd = AP_MAINT_INV_STS.PENDING_CONTRACT_APPROVAL;
                    // END 2017/12/26 J.Kim [QC#22458,MOD]
                }

                String nextApInvNum = EMPTY_STRING;
                String nextApVndCd = EMPTY_STRING;
                if (!rs.isLast()) {
                    rs.next();
                    nextApInvNum = rs.getString(AP_INV_NUM);
                    nextApVndCd = rs.getString(AP_VND_CD);
                    rs.previous();
                }

                if (rs.isLast()
                ||  (
                        !nextApInvNum.equals(rs.getString(AP_INV_NUM))
                    ||  !nextApVndCd.equals(rs.getString(AP_VND_CD))
                    )
                ) {
                    // Update CM_MAINT_INV record
                    CM_MAINT_INVTMsg cmMaintInvTMsg = new CM_MAINT_INVTMsg();
                    ZYPEZDItemValueSetter.setValue(cmMaintInvTMsg.glblCmpyCd, glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(cmMaintInvTMsg.apInvNum, rs.getString(AP_INV_NUM));
                    ZYPEZDItemValueSetter.setValue(cmMaintInvTMsg.apVndCd, rs.getString(AP_VND_CD));
                    S21FastTBLAccessor.findByKeyForUpdate(cmMaintInvTMsg);
                    ZYPEZDItemValueSetter.setValue(cmMaintInvTMsg.apDsWfStsCd, apDsWfStsCd);
                    ZYPEZDItemValueSetter.setValue(cmMaintInvTMsg.apMaintInvStsCd, apMaintInvStsCd);
                    listCmMaintInvTMsg.add(cmMaintInvTMsg);
                    iCntCmMaintInv++;
                    if (iCntCmMaintInv == INT_BULK_COM_LIM) {
                        bulkUpdateCmMaintInv();
                    }
                    if (rs.isLast()) {
                        // Send Approval Email
                        String strMessage = sbMessageBodyApvlEmail.toString();
                        listApvlEmlMessage.add(strMessage);
                        // Initialize message body string for Auto Approval Email
                        sbMessageBodyApvlEmail = new StringBuilder();
                        // Send Error Email
                        strMessage = sbMessageBodyErrorEmail.toString();
                        listErrEmlMessage.add(strMessage);
                        // Initialize message body string for Error Email
                        sbMessageBodyErrorEmail = new StringBuilder();
                    }
                    if (isError) {
                        listApvlEmlIndex.add(Boolean.FALSE);
                        listErrEmlIndex.add(Boolean.TRUE);
                    } else {
                        listApvlEmlIndex.add(Boolean.TRUE);
                        listErrEmlIndex.add(Boolean.FALSE);
                    }
                    isError = false;
                }

                prevApInvNum = NFBCommonBusiness.chkNull(rs.getString(AP_INV_NUM));
                prevApVndCd = NFBCommonBusiness.chkNull(rs.getString(AP_VND_CD));
                prevSerNum = NFBCommonBusiness.chkNull(rs.getString(SER_NUM));
                prevStartDt = NFBCommonBusiness.chkNull(rs.getString(START_DT));
            }
            return Boolean.TRUE;
        }
    }

    /**
     * <pre>
     * getAvgCopyQtyByReadDt
     * </pre>
     * 
     * @param serNum String
     * @return listAvgCopyByReadDt List<Map>
     */
    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> getAvgCopyQtyByReadDt(String serNum) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("serNum", serNum);
        ssmParam.put("svcMachMstrStsCd_W4I", SVC_MACH_MSTR_STS.WAITING_FOR_INSTALLATION);
        ssmParam.put("svcMachMstrStsCd_INSTL", SVC_MACH_MSTR_STS.INSTALLED);
        ssmParam.put("dsContrDtlStsCd_ACTV", DS_CONTR_DTL_STS.ACTIVE);
        ssmParam.put("flg_Y", ZYPConstant.FLG_ON_Y);
        ssmParam.put("flg_N", ZYPConstant.FLG_OFF_N);
        ssmParam.put("procDt", procDt);
        ssmParam.put("svcInvChrgTpCd_MC", SVC_INV_CHRG_TP.METER_CHARGE);
        List<Map<String, Object>> listAvgCopyByReadDt = (List<Map<String, Object>>) ssmBatchClient.queryObjectList(SELECT_AVG_COPY_QTY_BY_READ_DT, ssmParam);
        if (listAvgCopyByReadDt == null || listAvgCopyByReadDt.size() == 0) {
            return new ArrayList<Map<String, Object>>();
        }
        return listAvgCopyByReadDt;
    }

    /**
     * <pre>
     * getSvcPhysMtrRead
     * </pre>
     * 
     * @param serNum String
     * @param cntTpCd String
     * @param endDt String 
     * @return listSvcPhysMtrRead List<Map>
     */
    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> getSvcPhysMtrRead(String serNum, String cntTpCd, String endDt) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("serNum", serNum);
        ssmParam.put("svcMachMstrStsCd_W4I", SVC_MACH_MSTR_STS.WAITING_FOR_INSTALLATION);
        ssmParam.put("svcMachMstrStsCd_INSTL", SVC_MACH_MSTR_STS.INSTALLED);
        ssmParam.put("cntrTpCd", cntTpCd);
        ssmParam.put("endDt", endDt);
        ssmParam.put("yyyymmdd", YYYYMMDD);
        ssmParam.put("flg_Y", ZYPConstant.FLG_ON_Y);
        ssmParam.put("flg_N", ZYPConstant.FLG_OFF_N);
        List<Map<String, Object>> listSvcPhysMtrRead = (List<Map<String, Object>>) ssmBatchClient.queryObjectList(SELECT_SVC_PHYS_MTR_READ, ssmParam);
        if (listSvcPhysMtrRead == null || listSvcPhysMtrRead.size() == 0) {
            return new ArrayList<Map<String, Object>>();
        }
        return listSvcPhysMtrRead;
    }
    
    /**
     * <pre>
     * getSvcAvgMtrReadCnt
     * </pre>
     * 
     * @param serNum String
     * @return listSvcAvgMtrReadCnt List<Map>
     */
    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> getSvcAvgMtrReadCnt(String serNum, String cntTpCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("serNum", serNum);
        // START 2016/09/23 K.Kojima [QC#11168,ADD]
        ssmParam.put("cntrTpCd", cntTpCd);
        ssmParam.put("flg_Y", ZYPConstant.FLG_ON_Y);
        ssmParam.put("flg_N", ZYPConstant.FLG_OFF_N);
        // END 2016/09/23 K.Kojima [QC#11168,ADD]
        List<Map<String, Object>> listSvcAvgMtrReadCnt = (List<Map<String, Object>>) ssmBatchClient.queryObjectList(SELECT_SVC_AVG_MTR_READ_CNT, ssmParam);
        if (listSvcAvgMtrReadCnt == null || listSvcAvgMtrReadCnt.size() == 0) {
            return new ArrayList<Map<String, Object>>();
        }
        return listSvcAvgMtrReadCnt;
    }

    /**
     * <pre>
     * getContractFromSerNum
     * </pre>
     * 
     * @param serNum String
     * @return contrList List<Map>
     */
    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> getContractFromSerNum(String serNum) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("serNum", serNum);
        ssmParam.put("svcMachMstrStsCd_W4I", SVC_MACH_MSTR_STS.WAITING_FOR_INSTALLATION);
        ssmParam.put("svcMachMstrStsCd_INSTL", SVC_MACH_MSTR_STS.INSTALLED);
        ssmParam.put("dsContrDtlStsCd_ACTV", DS_CONTR_DTL_STS.ACTIVE);
        ssmParam.put("flg_Y", ZYPConstant.FLG_ON_Y);
        ssmParam.put("flg_N", ZYPConstant.FLG_OFF_N);
        ssmParam.put("procDt", procDt);
        List<Map<String, Object>> contrList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList(SELECT_CONTR_FROM_SER_NUM, ssmParam);
        if (contrList == null || contrList.size() == 0) {
            return new ArrayList<Map<String, Object>>();
        }
        return contrList;
    }

    /**
     * Initialize Commit Count.
     */
    private void initCommitCount() {
        this.cmMaintInvCommitCount = 0;
        this.totalCommitCount = 0;
    }

    /**
     * Initialize list for bulk TBL accessor.
     */
    private void initListForBulkTBLAccessor() {
        this.listCmMaintInvTMsg = new ArrayList<CM_MAINT_INVTMsg>();
    }

    /**
     * Initialize Bulk Count.
     */
    private void initBulkCount() {
        this.iCntCmMaintInv = 0;
    }

    /**
     * Initialize Commit Count.
     */
    private void initApMaintTolValue() {
        apMaintInvFromAmt = BigDecimal.ZERO;
        apMaintInvToAmt = BigDecimal.ZERO;
        apMaintInvBaseFromRate = BigDecimal.ZERO;
        apMaintInvBaseToRate = BigDecimal.ZERO;
        apMaintInvOverFromRate = BigDecimal.ZERO;
        apMaintInvOverToRate = BigDecimal.ZERO;
        apMaintInvFromCnt = BigDecimal.ZERO;
        apMaintInvToCnt = BigDecimal.ZERO;
        apMaintInvMtrFromRate = BigDecimal.ZERO;
        apMaintInvMtrToRate = BigDecimal.ZERO;
        apMaintInvMthTolCnt = BigDecimal.ZERO;
    }

    /**
     * Bulk update CM_MAINT_INV table.
     */
    private void bulkUpdateCmMaintInv() {
        CM_MAINT_INVTMsg[] cmMaintInvTMsgs = listCmMaintInvTMsg.toArray(new CM_MAINT_INVTMsg[listCmMaintInvTMsg.size()]);
        int iRet = S21FastTBLAccessor.update(cmMaintInvTMsgs);
        if (iRet > 0) {
            cmMaintInvCommitCount = cmMaintInvCommitCount + iRet;
            listCmMaintInvTMsg = new ArrayList<CM_MAINT_INVTMsg>();
            iCntCmMaintInv = 0;
        } else {
            cmMaintInvCommitCount = 0;
            throw new S21AbendException(NFBM0028E);
        }
    }

    /****************************
     * Send Auto Approval email.
     * @param subject String
     * @param message String
     * @param contrAdminInfoMap Map<String, String>
     ****************************/
    private void sendApprovalEmail(String subject, String message, Map<String, String> contrAdminInfoMap){
        if (!ZYPCommonFunc.hasValue(glblCmpyCd)
        ||  !ZYPCommonFunc.hasValue(contrAdminInfoMap.get(CONTR_ADMIN_PSN_CD))
        ||  !ZYPCommonFunc.hasValue(contrAdminInfoMap.get(EML_ADDR))
        ||  !ZYPCommonFunc.hasValue(contrAdminInfoMap.get(LANG_CD))
        ||  !ZYPCommonFunc.hasValue(contrAdminInfoMap.get(CTRY_CD))
        ||  !ZYPCommonFunc.hasValue(contrAdminInfoMap.get(FIRST_NM))
        ||  !ZYPCommonFunc.hasValue(contrAdminInfoMap.get(LAST_NM))) {
            // Skip sending email.
            return;
        }
        /*******************************
         * Set mail parameter
         *******************************/
        Map<String, String> mailParamMap = new HashMap<String, String>();
        mailParamMap.put(GLBL_CMPY_CD, glblCmpyCd);
        mailParamMap.put(CONTR_ADMIN_PSN_CD, contrAdminInfoMap.get(CONTR_ADMIN_PSN_CD));
        mailParamMap.put(EML_ADDR, contrAdminInfoMap.get(EML_ADDR));
        mailParamMap.put(LANG_CD, contrAdminInfoMap.get(LANG_CD));
        mailParamMap.put(CTRY_CD, contrAdminInfoMap.get(CTRY_CD));
        mailParamMap.put(FIRST_NM, contrAdminInfoMap.get(FIRST_NM));
        mailParamMap.put(MID_NM, contrAdminInfoMap.get(MID_NM));
        mailParamMap.put(LAST_NM, contrAdminInfoMap.get(LAST_NM));
        /*******************************
         * Create mail template
         *******************************/
        S21MailTemplate template = new S21MailTemplate(glblCmpyCd, ML_TMPL_ID);
        if (template == null) {
            S21InfoLogOutput.println(NFBM0184E, new String[] {ML_TMPL_ID});
            termCd = TERM_CD.WARNING_END;
            return;
        }
        /*******************************
         * Set mail template parameter
         *******************************/
        /** subject */
        template.setTemplateParameter(MAIL_TEMPLATE_KEY_SUBJECT, subject);
        /** message */
        template.setTemplateParameter(MAIL_TEMPLATE_KEY_MESSAGE, message);
        /*******************************
         * Send Mail
         *******************************/
        sendEmailFromPsnCd(mailParamMap, template);
    }

    /****************************
     * Send Error email.
     * @param subject String
     * @param message String
     * @param contrAdminInfoMap Map<String, String>
     ****************************/
    private void sendErrorEmail(String subject, String message, Map<String, String> contrAdminInfoMap){
        if (!ZYPCommonFunc.hasValue(glblCmpyCd)
        ||  !ZYPCommonFunc.hasValue(contrAdminInfoMap.get(CONTR_ADMIN_PSN_CD))
        ||  !ZYPCommonFunc.hasValue(contrAdminInfoMap.get(EML_ADDR))
        ||  !ZYPCommonFunc.hasValue(contrAdminInfoMap.get(LANG_CD))
        ||  !ZYPCommonFunc.hasValue(contrAdminInfoMap.get(CTRY_CD))
        ||  !ZYPCommonFunc.hasValue(contrAdminInfoMap.get(FIRST_NM))
        ||  !ZYPCommonFunc.hasValue(contrAdminInfoMap.get(LAST_NM))) {
            // Skip sending email.
            return;
        }
        /*******************************
         * Set mail parameter
         *******************************/
        Map<String, String> mailParamMap = new HashMap<String, String>();
        mailParamMap.put(GLBL_CMPY_CD, glblCmpyCd);
        mailParamMap.put(CONTR_ADMIN_PSN_CD, contrAdminInfoMap.get(CONTR_ADMIN_PSN_CD));
        mailParamMap.put(EML_ADDR, contrAdminInfoMap.get(EML_ADDR));
        mailParamMap.put(LANG_CD, contrAdminInfoMap.get(LANG_CD));
        mailParamMap.put(CTRY_CD, contrAdminInfoMap.get(CTRY_CD));
        mailParamMap.put(FIRST_NM, contrAdminInfoMap.get(FIRST_NM));
        mailParamMap.put(MID_NM, contrAdminInfoMap.get(MID_NM));
        mailParamMap.put(LAST_NM, contrAdminInfoMap.get(LAST_NM));
        /*******************************
         * Create mail template
         *******************************/
        S21MailTemplate template = new S21MailTemplate(glblCmpyCd, ML_TMPL_ID);
        if (template == null) {
            S21InfoLogOutput.println(NFBM0184E, new String[] {ML_TMPL_ID});
            termCd = TERM_CD.WARNING_END;
            return;
        }
        /*******************************
         * Set mail template parameter
         *******************************/
        /** subject */
        template.setTemplateParameter(MAIL_TEMPLATE_KEY_SUBJECT, subject);
        /** message */
        template.setTemplateParameter(MAIL_TEMPLATE_KEY_MESSAGE, message);
        /*******************************
         * Send Mail
         *******************************/
        sendEmailFromPsnCd(mailParamMap, template);
    }

    /*******************************************
     * Send email from person code.
     * @param mailParamMap Map<String, String>
     * @param template S21MailTemplate
     *******************************************/
    private void sendEmailFromPsnCd(Map<String, String> mailParamMap, S21MailTemplate template){
        /****************************
         * Get Parameter
         ****************************/
        String glblCmpyCd = (String)mailParamMap.get(GLBL_CMPY_CD);
        String contrAdminPsnCd = (String)mailParamMap.get(CONTR_ADMIN_PSN_CD);
        String emlAddr = (String)mailParamMap.get(EML_ADDR);
        String langCd = (String)mailParamMap.get(LANG_CD);
        String ctryCd = (String)mailParamMap.get(CTRY_CD);
        String firstNm = (String)mailParamMap.get(FIRST_NM);
        String midNm = (String)mailParamMap.get(MID_NM);
        String lastNm = (String)mailParamMap.get(LAST_NM);
        /****************************
         * Mandatory Parameter Check
         ****************************/
        if (!ZYPCommonFunc.hasValue(glblCmpyCd)) {
            throw new S21AbendException(ZZMM0007E, new String[] {"Global Company Code" });
        }
        if (!ZYPCommonFunc.hasValue(contrAdminPsnCd)) {
            throw new S21AbendException(ZZMM0007E, new String[] {"Contract Admin Person Code" });
        }
        if (!ZYPCommonFunc.hasValue(emlAddr)) {
            throw new S21AbendException(ZZMM0007E, new String[] {"Email Address" });
        }
        if (!ZYPCommonFunc.hasValue(langCd)) {
            throw new S21AbendException(ZZMM0007E, new String[] {"Language Code" });
        }
        if (!ZYPCommonFunc.hasValue(ctryCd)) {
            throw new S21AbendException(ZZMM0007E, new String[] {"Country Code" });
        }
        if (!ZYPCommonFunc.hasValue(firstNm)) {
            throw new S21AbendException(ZZMM0007E, new String[] {"First Name" });
        }
        if (!ZYPCommonFunc.hasValue(lastNm)) {
            throw new S21AbendException(ZZMM0007E, new String[] {"Last Name" });
        }
        if (template == null) {
            throw new S21AbendException(ZZMM0007E, new String[] {"Mail Template" });
        }
        /*******************************
         * Set "From" email address to
         * "S21_NFB@cusa.canon.com"
         *******************************/
        S21MailGroup groupFrom = new S21MailGroup(glblCmpyCd, MAIL_GRP_ID_FROM);
        groupFrom.setMailKey1(MAIL_KEY_1_FROM);
        List<S21MailAddress> addrFromList = groupFrom.getMailAddress();
        S21MailAddress from = null;
        if (addrFromList.size() <= 0) {
            throw new S21AbendException(ZZMM0007E, new String[] {"From Address" });
        } else {
            from = addrFromList.get(0);
        }
        /*******************************
         * Get "To" email address
         *******************************/
        List<S21MailAddress> addrToList = new ArrayList<S21MailAddress>();
        S21MailAddress s21MailAddress = new S21MailAddress(glblCmpyCd, emlAddr);
        String locale = langCd + "_" + ctryCd;
        String name = EMPTY_STRING;
        if (ZYPCommonFunc.hasValue(midNm)) {
            name = firstNm + SPACE + midNm + SPACE + lastNm;
        } else {
            name = firstNm + SPACE + lastNm;
        }
        s21MailAddress.setUserId(contrAdminPsnCd);
        s21MailAddress.setLocale(locale);
        s21MailAddress.setName(name);
        addrToList.add(s21MailAddress);
        /*******************************
         * Get "CC" email address
         *******************************/
        List<S21MailAddress> addrCcList = new ArrayList<S21MailAddress>();
        /*******************************
         * Get "BCC" email address
         *******************************/
        List<S21MailAddress> addrBccList = new ArrayList<S21MailAddress>();
        /*******************************
         * Send mail.
         *******************************/
        S21Mail mail = new S21Mail(glblCmpyCd);
        mail.setMailTemplate(template);
        mail.setFromAddress(from);
        mail.setToAddressList(addrToList);
        mail.setCcAddressList(addrCcList);
        mail.setBccAddressList(addrBccList);
        mail.setSubject(template.getSubject("en"), "ISO-8859-1");
        if (mailParamMap.containsKey("attachmentId")) {
            mail.setAttachmentId(Integer.parseInt((String)mailParamMap.get("attachmentId")));
        }
        // START 2019/02/28 S.Ohki [QC#30584, MOD]
//        String mailEvent = mail.sendMail();
        String mailEvent = mail.postMail();
        // END 2019/02/28 S.Ohki [QC#30584, MOD]
        if(!hasValue(mailEvent)){
            return;
        }
    }

    // Add Start 2016/08/04 QC#12692
    /**
     * Check Serial Number
     * @param bizMsg NFBL1110CMsg
     * @return boolean
     */
    public boolean checkSerial(String serNum, String siteNm) {

        String procDtYearsAgo = getYearsAgo(procDt.substring(0, 8));

        Map<String, String> queryParam = new HashMap<String, String>();
        // START 2016/09/21 K.Kojima [QC#11168,MOD]
        // queryParam.put("glblCmpyCd", GLBL_CMPY_CD);
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        // END 2016/09/21 K.Kojima [QC#11168,MOD]
        queryParam.put("locNm", siteNm);
        queryParam.put("batchDt", procDtYearsAgo);
        queryParam.put("serNum", serNum);
        Integer serCount = (Integer) ssmBatchClient.queryObject("countSerNum", queryParam);

        if (serCount > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @param slsDt
     * @return String
     */
    private String getYearsAgo(String slsDt) {

        final SimpleDateFormat dtFormatter = new SimpleDateFormat("yyyyMMdd");
        String str = "";

        try {
            Date date = dtFormatter.parse(slsDt);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.YEAR, -1);
            date = cal.getTime();
            str = dtFormatter.format(date);

        } catch (ParseException pe) {
            EZDDebugOutput.println(1, "getBeforeYear() param:" + slsDt + ", format:" + dtFormatter, null);
        }
        return str;
    }
    // Add End 2016/08/04 QC#12692
}
