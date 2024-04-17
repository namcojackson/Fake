/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */

package com.canon.cusa.s21.batch.NWC.NWCB018001;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.CUST_BLLG_VCLETMsg;
import business.db.DS_CTAC_PNTTMsg;
import business.db.DS_CTAC_PNTTMsgArray;
import business.db.INV_PRT_CTRLTMsg;
import business.db.ML_BIZ_ADDR_MAPTMsg;
import business.db.S21_PSNTMsg;
import business.db.S21_PSNTMsgArray;
import business.parts.NMZC610001PMsg;
import business.parts.NMZC610001_InvoiceRuleListPMsg;

import com.canon.cusa.s21.api.NMZ.NMZC610001.NMZC610001;
import com.canon.cusa.s21.api.NMZ.NMZC610001.constant.NMZC610001Constant;
import com.canon.cusa.s21.framework.ZYP.aspose.pdf.S21PDFMerger;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CUST_BLLG_VCLE;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CUST_INV_SRC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_BIZ_AREA;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CTAC_PNT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_PRT_BAT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_PRT_CTRL_REC;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailAttachment;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;
import com.canon.cusa.s21.framework.printing.S21PrintingUtil;
import com.canon.cusa.s21.framework.printing.common.S21EIPPrintingService;

/**
 * <pre>
 * Invoice Email Request
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/26   Fujitsu         Y.Kanefusa      Create          N/A
 * 2016/06/17   SRAA            K.Aratani       Update          QC#7781
 * 2016/07/07   SRAA            K.Aratani       Update          QC#6934
 * 2017/03/14   Fujitsu         H.Nagashima     Update          QC#17962
 * 2018/01/03   SRAA            K.Aratani       Update          QC#18694
 * 2018/03/29   SRAA            K.Aratani       Update          QC#18693
 * 2018/10/12   Fujitsu         K.Kato          Update          QC#28777
 * 2018/11/15   Fujitsu         M.Ohno          Update          QC#27954
 * 2018/12/13   Fujitsu         K.Ishizuka      Update          QC#29561
 * 2019/01/10   Fujitsu         N.Sugiura       Update          QC#29758
 * 2019/02/27   Fujitsu         S.Kosaka        Update          QC#30533
 * 2019/09/13   Fujitsu         Y.Kanefusa      Update          QC#53015
 * 2019/11/11   Fujitsu         Y.Kanefusa      Update          QC#54520
 * 2022/03/28   CITS            G.Delgado       Update          QC#59827
 * 2022/03/28   CITS            G.Delgado       Update          QC#59829
 * 2022/04/12   CITS            A.Cullano       Update          QC#59827
 * </pre>
 */
public class NWCB018001 extends S21BatchMain {
    /** for Debug */
    int DEBUG_MSG_LVL = 1;

    /** S21SsmBatchClient */
    private S21SsmBatchClient ssmBatchClient;

    /** Total Commit Count */
    private int totalCommitCount = 0;

    /** Total Error Count */
    private int totalErrCount = 0;

    /** Terminate Cord */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** Global Company Code */
    private String glblCmpyCd;

    /** Sales Date */
    private String slsDt;

    /** Branch No. */
    private String prBrNo;

    /** Branch No. */
    private String defaultEmlAddr;

    /** Branch No. */
    private String defaultEmlSbj;

    ///** sysTimeStamp */
    //private String  sysTimeStamp;

    // START 2022/03/28 G.Delgado [QC#59827, ADD]
    /** Payment Related E-mail Address */
    private String pmtRelEmlAddr;
    // END 2022/03/28 G.Delgado [QC#59827, ADD]

    /** Mail List */
    private List<Map<String, String>> mailErrorList = new ArrayList<Map<String, String>>();

    /** processPk */
    private long processPk = 0;

    /**
     * It is the main method that is called from the batch processing.
     * @param args Argument
     */
    public static void main(String[] args) {

        new NWCB018001().executeBatch(NWCB018001.class.getSimpleName());
    }

    @Override
    protected void initRoutine() {
        // Global Company Code
        glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(glblCmpyCd)) {
            setMainRoutineEnd();
            this.termCd = TERM_CD.ABNORMAL_END;
            throw new S21AbendException(NWCB018001Constant.ZZZM9025E, new String[] {"Global Company Code" });
        }
        // Sales Date
        slsDt = ZYPDateUtil.getSalesDate(glblCmpyCd);
        // Branch No.
        prBrNo = ZYPCodeDataUtil.getVarCharConstValue(NWCB018001Constant.DEF_EML_BR_NUM, glblCmpyCd);
        if(prBrNo == null || prBrNo.isEmpty()){
            throw new S21AbendException(NWCB018001Constant.NWCM0104E, null );
        }
        // Default E-Mail Address
        defaultEmlAddr = ZYPCodeDataUtil.getVarCharConstValue(NWCB018001Constant.STGY_DEF_CLTR_EML, glblCmpyCd);
        // Default E-Mail Subject
        defaultEmlSbj = ZYPCodeDataUtil.getVarCharConstValue(NWCB018001Constant.DEF_EML_SBJ, glblCmpyCd);
        // Timestamp
        //sysTimeStamp = ZYPDateUtil.getCurrentSystemTime(NWCB018001Constant.TYPE_TIME_STAMP);

        // START 2022/03/28 G.Delgado [QC#59827, ADD]
        // Payment Related E-mail Address
        pmtRelEmlAddr = ZYPCodeDataUtil.getVarCharConstValue(NWCB018001Constant.NWCB0180_PMT_REL_EML_ADDR, glblCmpyCd);
        // END 2022/03/28 G.Delgado [QC#59827, ADD]

        ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }

    @Override
    protected void mainRoutine() {
        try {
            doProcess();
        } catch (Exception e) {
            e.printStackTrace();
            rollback();
        } finally {
            if (mailErrorList.size() > 0) {
                postErrMail();
                termCd = TERM_CD.WARNING_END;
            }
            commit();
        }
    }

    @Override
    protected void termRoutine() {
        int totalCount = totalCommitCount + totalErrCount;
        setTermState(termCd, totalCommitCount, totalErrCount, totalCount);
    }

    private void doProcess() {
        S21EIPPrintingService service = new S21EIPPrintingService();
        String type = null;
        String number = null;
        String billToCustCd = null;
        String billToDsAcctNum = null; // QC#54520 2019/11/11 Add
        NMZC610001PMsg pmsg;
        List<String> toAddrList = new ArrayList<String>();
        String fromAddr = null;
        String subject = null;
        String prmAttachFileName = null;
        BigDecimal rptRqstPk = null;
        String invBatTp = null;  // QC#53015 2019/09/13 Add
        String lineBizTp = null; // QC#54520 2019/11/11 Add
        // START 2022/03/28 G.Delgado [QC#59827, ADD]
        String dsBizAreaCd = null;
        // END 2022/03/28 G.Delgado [QC#59827, ADD]

        List<Map<?, ?>> list = getInvPrtCtrl();
        if(list == null || list.isEmpty()){
            return;
        }
        
        List<String> dailySumBillToCustCdList = new ArrayList<String>();
        boolean needEmailReviewFlag = false;
        boolean reviewRequireFlg = false; // QC#53015 2019/09/13 Add
        for (Map<?, ?> data : list) {
            type = (String) data.get("TYPE");
            number = (String) data.get("NUM");
            billToCustCd = (String) data.get("BILL_TO_CUST_CD");
            billToDsAcctNum = (String) data.get("BILL_TO_DS_ACCT_NUM"); // QC#54520 2019/11/11 Add
            rptRqstPk = (BigDecimal) data.get("INV_FTP_RQST_NUM");
            invBatTp = (String) data.get("INV_PRT_BAT_TP_CD"); // QC#53015 2019/09/13 Add
            lineBizTp = (String) data.get("LINE_BIZ_TP_CD"); // QC#54520 2019/11/11 Add
            // START 2022/03/28 G.Delgado [QC#59827, ADD]
            dsBizAreaCd = (String) data.get("DS_BIZ_AREA_CD");
            // END 2022/03/28 G.Delgado [QC#59827, ADD]

            //if Daily Summary EMail & Print and Already output, Skip
            if (dailySumBillToCustCdList.contains(billToCustCd)) {
                continue;
            }
            
            needEmailReviewFlag = false;
            reviewRequireFlg = false; // QC#53015 2019/09/13 Add
            pmsg = callNMZC6100Api(data);
            List<String> itrlRvwPsnCdList = new ArrayList<String>();
            List<BigDecimal> ctacPsnPkList = new ArrayList<BigDecimal>();
            if (!S21ApiUtil.isXxMsgId(pmsg)) {
                NMZC610001_InvoiceRuleListPMsg invoiceRuleListPMsg = pmsg.InvoiceRuleList.no(0);
                if (invoiceRuleListPMsg.NMZC610002PMsg != null) {
                    if (ZYPCommonFunc.hasValue(invoiceRuleListPMsg.custBllgVcleCd)) {
                        CUST_BLLG_VCLETMsg tMsg = new CUST_BLLG_VCLETMsg();
                        tMsg.glblCmpyCd.setValue(this.glblCmpyCd);
                        tMsg.custBllgVcleCd.setValue(invoiceRuleListPMsg.custBllgVcleCd.getValue());
                        tMsg = (CUST_BLLG_VCLETMsg) EZDTBLAccessor.findByKey(tMsg);
                        if (tMsg != null && ZYPConstant.FLG_ON_Y.equals(tMsg.itrlRvwFlg.getValue())) {
                            needEmailReviewFlag = true;
                            reviewRequireFlg = true; // QC#53015 2019/09/13 Add
                            if (invoiceRuleListPMsg.NMZC610002PMsg.A != null && invoiceRuleListPMsg.NMZC610002PMsg.A.getValidCount() > 0) {
	                            for (int i = 0; i < invoiceRuleListPMsg.NMZC610002PMsg.A.getValidCount(); i++) {
	                                if (ZYPCommonFunc.hasValue(invoiceRuleListPMsg.NMZC610002PMsg.A.no(i).itrlRvwPsnCd)) {
	                                    itrlRvwPsnCdList.add(invoiceRuleListPMsg.NMZC610002PMsg.A.no(i).itrlRvwPsnCd.getValue());
	                                }
	                            }
                            }
                        }
                    }
                }

                // if (invoiceRuleListPMsg.NMZC610002PMsg != null && invoiceRuleListPMsg.NMZC610002PMsg.A != null && invoiceRuleListPMsg.NMZC610002PMsg.A.getValidCount() > 0) {
                if (invoiceRuleListPMsg.NMZC610002PMsg != null && invoiceRuleListPMsg.NMZC610002PMsg.B != null && invoiceRuleListPMsg.NMZC610002PMsg.B.getValidCount() > 0) { // 2018/12/13 S21_NA#29561 Mod
                    for (int i = 0; i < invoiceRuleListPMsg.NMZC610002PMsg.B.getValidCount(); i++) {
                        if (ZYPCommonFunc.hasValue(invoiceRuleListPMsg.NMZC610002PMsg.B.no(i).ctacPsnPk)) {
                            ctacPsnPkList.add(invoiceRuleListPMsg.NMZC610002PMsg.B.no(i).ctacPsnPk.getValue());
                        }
                    }
                }
            } else {
                List<String> msgIdList = S21ApiUtil.getXxMsgIdList(pmsg);
                for (int i = 0; i < msgIdList.size(); i++) {
                    String msgID = msgIdList.get(i);
                    setMailMessage(null, number, msgID, null);
                    totalErrCount = totalErrCount + 1;
                }
                continue;
            }
            // QC#53015 2019/09/13 Add Start
            if(INV_PRT_BAT_TP.NEEDS_REVIEW.equals(invBatTp)){
                reviewRequireFlg = true;
            }
            if(INV_PRT_BAT_TP.REVIEW_REQUIRED.equals(invBatTp)){
                reviewRequireFlg = true;
            }
            // QC#53015 2019/09/13 Add End

            //to Address
            toAddrList.clear();
            //Need EMail Review
            if (needEmailReviewFlag) {
                if (itrlRvwPsnCdList != null && !itrlRvwPsnCdList.isEmpty()) {
                    for (String itrlRvwPsnCd : itrlRvwPsnCdList) {
                        String emlAddr = getToAddr(itrlRvwPsnCd);
                        // QC#53015 2019/09/13 Add Start
                        //if(emlAddr != null){
                        if(ZYPCommonFunc.hasValue(emlAddr)){
                        // QC#53015 2019/09/13 Add End
                            toAddrList.add(emlAddr);
                        }
                    }
                }
            } else {
                if (ctacPsnPkList != null && !ctacPsnPkList.isEmpty()) {
                    for (BigDecimal ctacPsnPk : ctacPsnPkList) {
                        // 2019/01/10 QC#29758 Mod Start
                        // toAddrList = getCtacPnt(ctacPsnPk);
                        toAddrList = getCtacPnt(ctacPsnPk, toAddrList);
                        // 2019/01/10 QC#29758 Mod End
                    }
                }
            }

            // QC#53015 2019/09/13 Del Start
            //if(toAddrList.isEmpty()){
            //    if(defaultEmlAddr != null){
            //        toAddrList.add(defaultEmlAddr);
            //    }
            //}
            //if(toAddrList.isEmpty()){
            //    setMailMessage(null, number, NWCB018001Constant.NWCM0105E, new String[]{ number , billToCustCd});
            //    totalErrCount = totalErrCount + 1;
            //    continue;
            //}
            // QC#53015 2019/09/13 Del End

            //from Address
            // QC#54520 2019/11/11 Add Start
            // fromAddr = getFromAddr(billToCustCd);
            // START 2022/03/28 G.Delgado [QC#59827, MOD]
            // fromAddr = getFromAddr(billToDsAcctNum, billToCustCd);
            fromAddr = getFromAddrByBiz(lineBizTp, dsBizAreaCd);
            // END 2022/03/28 G.Delgado [QC#59827, MOD]
            // QC#54520 2019/11/11 Add End
            // QC#54520 2019/11/11 Add Start
            if (fromAddr == null) {
                S21MailGroup groupfrom = new S21MailGroup(glblCmpyCd, NWCB018001Constant.MAIL_GROUP_FROM);
                groupfrom.setMailKey1(lineBizTp);
                if(groupfrom.getMailAddress().size() > 0){
                    fromAddr = groupfrom.getMailAddress().get(0).getAddress();
                }
            }
            // QC#54520 2019/11/11 Add End
            if (fromAddr == null) {
                fromAddr = defaultEmlAddr;
            }
            if(fromAddr == null || fromAddr.length() == 0){
                setMailMessage(null, number, NWCB018001Constant.NWCM0105E, new String[]{ number , billToCustCd});
                totalErrCount = totalErrCount + 1;
                continue;
            }
            //subject
            subject = pmsg.InvoiceRuleList.no(0).custEmlMsgTxt.getValue();
            if(subject == null || subject.length() == 0){
                if(ZYPCommonFunc.hasValue(defaultEmlSbj)){
                    // START 2022/03/28 G.Delgado [QC#59829, MOD]
                    // subject = defaultEmlSbj + billToCustCd;
                    subject = defaultEmlSbj + billToDsAcctNum;
                    // END 2022/03/28 G.Delgado [QC#59829, MOD]
                }
            }
            if(subject == null || subject.length() == 0){
                setMailMessage(null, number, NWCB018001Constant.NWCM0106E, new String[]{ number , billToCustCd});
                totalErrCount = totalErrCount + 1;
                continue;
            }

            
            //if CUST_BLLG_VCLE_CD = DAILY_SUMMARY_EMAIL_AND_PRINT(91), Summary - Bill To Customer Code
            String custBllgVcle = pmsg.InvoiceRuleList.no(0).custBllgVcleCd.getValue();
            if (ZYPCommonFunc.hasValue(custBllgVcle) && CUST_BLLG_VCLE.DAILY_SUMMARY_EMAIL_AND_PRINT.equals(custBllgVcle)) {
                //get Invoice print data by bill to customer code.
                List<Map<?, ?>> dailySumEmailPrintList = getInvPrtCtrlByBillToCust(billToCustCd);
                if(dailySumEmailPrintList == null || dailySumEmailPrintList.isEmpty()){
                    continue;
                }
                //QC#18693
                //List<S21ReportRequestBean> requestList = new ArrayList<S21ReportRequestBean>();
                //S21ReportRequestBean mergeRequest = null;
                //S21InputParameter mergeInputParam = null;
                //Set Request List for SelltoCust and PayerCD combination
                // START 2022/04/12 A.Cullano [QC#59827, MOD]
                // boolean firstRcd = true;
                StringBuffer body = new StringBuffer();
                body.append(NWCB018001Constant.MAIL_BODY_MSG);
                // END 2022/04/12 A.Cullano [QC#59827, MOD]

                List<String> filePathofPdffiles = new ArrayList<String>();
                reviewRequireFlg = false;
                for (Map<?, ?> mergeMap : dailySumEmailPrintList) {
                    //mergeRequest = new S21ReportRequestBean(NWCB018001Constant.REPORT_ID);
                    //mergeRequest.setRptFileTpCd(S21ReportRequestBean.OUTPUT_FORMAT_TYPE_PDF);
                    //mergeRequest.setRptArcFlg(false);
                    //if (firstRcd) {
                    //    String mergeRptTtlNm = NWCB018001Constant.RPT_TTL_NAME + (String) mergeMap.get("NUM") + NWCB018001Constant.RPT_TIME + sysTimeStamp;
                    //    mergeRequest.setRptTtlNm(mergeRptTtlNm);
                    //}
                    //mergeInputParam = mergeRequest.getInputParamBeanInstance();
                    //mergeInputParam.addReportParameter(NWCB018001Constant.GLBL_CMPY_CD, glblCmpyCd);
                    //mergeInputParam.addReportParameter(NWCB018001Constant.INV_BILL_NUM, (String) mergeMap.get("NUM"));
                    //mergeInputParam.addReportParameter(NWCB018001Constant.INTL_LANG_VAL_COL_NM, mergeInputParam.getSystemDefaultLanguage());
                    //mergeInputParam.addReportParameter(NWCB018001Constant.CONSL_BILL_FLG, (String) mergeMap.get("CONSL_BILL_FLG"));
                    //mergeInputParam.addReportParameter(NWCB018001Constant.INV_PRT_CTRL_PK, String.valueOf((BigDecimal) mergeMap.get("INV_PRT_CTRL_PK")));
                    //mergeRequest.setInputParamBean(mergeInputParam);
                    //requestList.add(mergeRequest);

                    // START 2022/04/12 A.Cullano [QC#59827, DEL]
                    // if (firstRcd) {
                    //    body.append((String) mergeMap.get("NUM"));
                    // } else {
                    //    body.append("," + (String) mergeMap.get("NUM"));
                    // }
                    // firstRcd = false;
                    // END 2022/04/12 A.Cullano [QC#59827, DEL]

                    BigDecimal invFtpRqstNum = (BigDecimal)mergeMap.get("INV_FTP_RQST_NUM");
                    if (ZYPCommonFunc.hasValue(invFtpRqstNum)) {
                        filePathofPdffiles.add(service.getFilePathForCupsPrint(invFtpRqstNum.longValue(), 0));
                    }
                    // QC#53015 2019/09/13 Add Start
                    if(INV_PRT_BAT_TP.NEEDS_REVIEW.equals(invBatTp)){
                        reviewRequireFlg = true;
                    }
                    // QC#53015 2019/09/13 Add End
                }

                byte[] pdf = mergePdfFiles(filePathofPdffiles);
                //byte[] pdf = service.onlineMergeReports(requestList);
                if (pdf == null) {
                    //NWAM0867E=0,It failed to get merge report bytes failure. Bill#/Invoice# : {@}, Bill To Customer : {@}
                    setMailMessage(null, number, NWCB018001Constant.NWAM0867E, new String[]{ number , billToCustCd});
                    totalErrCount = totalErrCount + 1;
                    continue;
                }

                dailySumBillToCustCdList.add(billToCustCd);

                // Send e-Mail with using S21Mail component.
                S21Mail mail = new S21Mail(glblCmpyCd);
                // Set Subject
                // QC#53015 2019/09/13 Add Start
                if (reviewRequireFlg) {
                    subject = NWCB018001Constant.REVIEW_REQUIRED + subject;
                }
                // QC#53015 2019/09/13 Add End
                mail.setSubject(subject);
                // Set Body
                // QC#53015 2019/09/13 Mod Start
                //mail.setText(body.toString());
                String bodyTxt = null;
                if (reviewRequireFlg) {
                    bodyTxt = NWCB018001Constant.REVIEW_REQUIRED + body.toString();
                } else {
                    bodyTxt = body.toString();
                }
                // START 2022/03/28 G.Delgado [QC#59827, ADD]
                bodyTxt = S21StringUtil.concatStrings(bodyTxt, NWCB018001Constant.MAIL_FOOTER_MSG, pmtRelEmlAddr);
                // END 2022/03/28 G.Delgado [QC#59827, ADD]
                mail.setText(bodyTxt);
                // QC#53015 2019/09/13 Mod End
                // Set From Address
                S21MailAddress s21FromAddr = new S21MailAddress(glblCmpyCd, fromAddr);
                mail.setFromAddress(s21FromAddr);
                // Set To Address
                List<S21MailAddress> toAddrs = new ArrayList<S21MailAddress>();
                // QC#53015 2019/09/13 Mod Start
                //for (String toAddr : toAddrList) {
                //    S21MailAddress s21ToAddr = new S21MailAddress(glblCmpyCd, toAddr);
                //    toAddrs.add(s21ToAddr);
                //}
                if (needEmailReviewFlag) {
                    for (String toAddr : toAddrList) {
                        S21MailAddress s21ToAddr = new S21MailAddress(glblCmpyCd, toAddr);
                        toAddrs.add(s21ToAddr);
                    }
                    if (toAddrList.isEmpty()) {
                        S21MailGroup group = new S21MailGroup(glblCmpyCd, NWCB018001Constant.MAIL_GROUP_RR);
                        toAddrs = group.getMailAddress();
                    }
                } else if (reviewRequireFlg) {
                    S21MailGroup group = new S21MailGroup(glblCmpyCd, NWCB018001Constant.MAIL_GROUP_RR);
                    toAddrs = group.getMailAddress();
                } else {
                    for (String toAddr : toAddrList) {
                        S21MailAddress s21ToAddr = new S21MailAddress(glblCmpyCd, toAddr);
                        toAddrs.add(s21ToAddr);
                    }
                    if (toAddrList.isEmpty()) {
                        if (defaultEmlAddr != null) {
                            toAddrList.add(defaultEmlAddr);
                        }
                    }
                }
                if (toAddrList.isEmpty()) {
                    setMailMessage(null, number, NWCB018001Constant.NWCM0105E, new String[] {number, billToCustCd });
                    totalErrCount = totalErrCount + 1;
                    continue;
                }
                // QC#53015 2019/09/13 Mod End
                mail.setToAddressList(toAddrs);
                
                // Set Attachment
                S21MailAttachment attachment = new S21MailAttachment(glblCmpyCd);
                attachment.setAttachment(pdf);
                prmAttachFileName = "Daily Summary Email (" + billToCustCd + NWCB018001Constant.FILE_NAME_PDF;
                attachment.setFileName(prmAttachFileName);
                mail.setAttachment(attachment);

                // 2019/02/27 QC#30533 Mod Start
//                // Call sendMail component (Sync)
//                String messageId = mail.sendMail();
                String messageId = mail.postMail();
                // 2019/02/27 QC#30533 Mod End
                if (S21PrintingUtil.isEmpty(messageId)) {
                    //NWAM0868E=0,It failed to send email to Customer. Bill#/Invoice# : {@}, Bill To Customer : {@}
                    setMailMessage(null, number, NWCB018001Constant.NWAM0868E, new String[]{ number , billToCustCd});
                    totalErrCount = totalErrCount + 1;
                    continue;
                }
                //update INV_PRT_CTRL
                for (Map<?, ?> mergeMap : dailySumEmailPrintList) {
                    updateInvPrtCtrlForMerge((BigDecimal) mergeMap.get("INV_PRT_CTRL_PK"), fromAddr, subject, toAddrList, (String) data.get("NUM"), rptRqstPk);
                }
                continue;
            }
            
            
            //Use the created PDF
            // Send e-Mail with using S21Mail component.
            S21Mail mail = new S21Mail(glblCmpyCd);
            // Set Subject
            // QC#53015 2019/09/13 Add Start
            if (reviewRequireFlg) {
                subject = NWCB018001Constant.REVIEW_REQUIRED + subject;
            }
            // QC#53015 2019/09/13 Add End
            mail.setSubject(subject);
            // Set Body
            // QC#53015 2019/09/13 Mod Start
            // mail.setText((String) data.get("NUM"));
            String bodyTxt = null;
            if (reviewRequireFlg) {
                // START 2022/04/12 A.Cullano [QC#59827, MOD]
                // bodyTxt = NWCB018001Constant.REVIEW_REQUIRED + (String) data.get("NUM");
                bodyTxt = S21StringUtil.concatStrings(NWCB018001Constant.REVIEW_REQUIRED, NWCB018001Constant.MAIL_BODY_MSG);
                // END 2022/04/12 A.Cullano [QC#59827, MOD]
            } else {
                // START 2022/04/12 A.Cullano [QC#59827, MOD]
                // bodyTxt = (String) data.get("NUM");
                bodyTxt = NWCB018001Constant.MAIL_BODY_MSG;
                // END 2022/04/12 A.Cullano [QC#59827, MOD]
            }
            // START 2022/03/28 G.Delgado [QC#59827, ADD]
            bodyTxt = S21StringUtil.concatStrings(bodyTxt, NWCB018001Constant.MAIL_FOOTER_MSG, pmtRelEmlAddr);
            // END 2022/03/28 G.Delgado [QC#59827, ADD]
            mail.setText(bodyTxt);
            // QC#53015 2019/09/13 Mod End

            // Set From Address
            S21MailAddress s21FromAddr = new S21MailAddress(glblCmpyCd, fromAddr);
            mail.setFromAddress(s21FromAddr);
            // Set To Address
            List<S21MailAddress> toAddrs = new ArrayList<S21MailAddress>();
            // QC#53015 2019/09/13 Mod Start
            // for (String toAddr : toAddrList) {
            //     S21MailAddress s21ToAddr = new S21MailAddress(glblCmpyCd, toAddr);
            //     toAddrs.add(s21ToAddr);
            // }
            if (needEmailReviewFlag) {
                for (String toAddr : toAddrList) {
                    S21MailAddress s21ToAddr = new S21MailAddress(glblCmpyCd, toAddr);
                    toAddrs.add(s21ToAddr);
                }
                if (toAddrList.isEmpty()) {
                    S21MailGroup group = new S21MailGroup(glblCmpyCd, NWCB018001Constant.MAIL_GROUP_RR);
                    toAddrs = group.getMailAddress();
                }
            } else if (reviewRequireFlg) {
                S21MailGroup group = new S21MailGroup(glblCmpyCd, NWCB018001Constant.MAIL_GROUP_RR);
                toAddrs = group.getMailAddress();
            } else {
                if (toAddrList.isEmpty()) {
                    if (defaultEmlAddr != null) {
                        toAddrList.add(defaultEmlAddr);
                    }
                }
                for (String toAddr : toAddrList) {
                    S21MailAddress s21ToAddr = new S21MailAddress(glblCmpyCd, toAddr);
                    toAddrs.add(s21ToAddr);
                }
            }
            if (toAddrs.isEmpty()) {
                setMailMessage(null, number, NWCB018001Constant.NWCM0105E, new String[] {number, billToCustCd });
                totalErrCount = totalErrCount + 1;
                continue;
            }
            // QC#53015 2019/09/13 Mod End
            mail.setToAddressList(toAddrs);
            
            // Set Attachment
            S21MailAttachment attachment = new S21MailAttachment(glblCmpyCd);
            rptRqstPk = (BigDecimal) data.get("INV_FTP_RQST_NUM");
            if (rptRqstPk == null) {
                System.out.println("Can't email because PDF was not created. Bill#/Invoice# : " + (String) data.get("NUM") + "INV_PRT_CTRL_PK : " + (BigDecimal) data.get("INV_PRT_CTRL_PK"));
                // send EIP error eMail
                setMailMessageForEIP((BigDecimal) data.get("INV_PRT_CTRL_PK"), 
                        (String) data.get("NUM"), 
                        "Can't email because PDF was not created.");
                totalErrCount = totalErrCount + 1;
                continue;
            }
            //byte[] pdf = service.getArchivedReport(rptRqstPk.longValue());
            List<String> filePathofPdffiles = new ArrayList<String>();
            filePathofPdffiles.add(service.getFilePathForCupsPrint(rptRqstPk.longValue(), 0));
            byte[] pdf = mergePdfFiles(filePathofPdffiles);
            if (pdf == null) {
                System.out.println("It failed to get PDF report bytes failure. Bill#/Invoice# : " + (String) data.get("NUM") + "EIP Request PK : " + rptRqstPk);
                // send EIP error eMail
                setMailMessageForEIP(rptRqstPk, 
                        (String) data.get("NUM"), 
                        "It failed to get PDF report bytes failure.");
                totalErrCount = totalErrCount + 1;
                continue;
            }
            attachment.setAttachment(pdf);
            if (S21StringUtil.isEquals(type, NWCB018001Constant.TYPE_BILL)) {
                prmAttachFileName = NWCB018001Constant.FILE_NAME_BILL + number + NWCB018001Constant.FILE_NAME_PDF;
            } else {
                prmAttachFileName = NWCB018001Constant.FILE_NAME_INVOICE + number + NWCB018001Constant.FILE_NAME_PDF;
            }
            // QC#28777 2018/10/11 Add Start
            attachment.setFileName(prmAttachFileName);
            // QC#28777 2018/10/11 Add End
            mail.setAttachment(attachment);

            // 2019/02/27 QC#30533 Mod Start
            // Call sendMail component (Sync)
            // String messageId = mail.sendMail();
            String messageId = mail.postMail();
            // 2019/02/27 QC#30533 Mod End
            if (S21PrintingUtil.isEmpty(messageId)) {
                //NWAM0868E=0,It failed to send email to Customer. Bill#/Invoice# : {@}, Bill To Customer : {@}
                setMailMessage(null, number, NWCB018001Constant.NWAM0868E, new String[] {number, billToCustCd });
                totalErrCount = totalErrCount + 1;
                continue;
            }

            updateInvPrtCtrl((BigDecimal) data.get("INV_PRT_CTRL_PK"), rptRqstPk, fromAddr, subject, toAddrList, (String) data.get("NUM"));
        }
    }

    @SuppressWarnings("unchecked")
    private List<Map<?, ?>> getInvPrtCtrl() {
        Map<String, Object> mapParam = new HashMap<String, Object>();
        List<Map<?, ?>> ssmResList;
        mapParam.put("glblCmpyCd", glblCmpyCd);
        mapParam.put("invPrtCtrlRecCd", INV_PRT_CTRL_REC.CONSOLIDATED_BILL);
        mapParam.put("invPrtProcStsCd", NWCB018001Constant.NOT_EXECUTED);
        mapParam.put("invOtptReqFlg", ZYPConstant.FLG_ON_Y);
        ssmResList = ssmBatchClient.queryObjectList("selectInvPrtCtrl", mapParam);
        return ssmResList;
    }

    @SuppressWarnings("unchecked")
    private List<Map<?, ?>> getInvPrtCtrlByBillToCust(String billToCustCd) {
        Map<String, Object> mapParam = new HashMap<String, Object>();
        List<Map<?, ?>> ssmResList;
        mapParam.put("glblCmpyCd", glblCmpyCd);
        mapParam.put("invPrtCtrlRecCd", INV_PRT_CTRL_REC.CONSOLIDATED_BILL);
        mapParam.put("invPrtProcStsCd", NWCB018001Constant.NOT_EXECUTED);
        mapParam.put("invOtptReqFlg", ZYPConstant.FLG_ON_Y);
        mapParam.put("billToCustCd", billToCustCd);
        ssmResList = ssmBatchClient.queryObjectList("selectInvPrtCtrlByBillToCust", mapParam);
        return ssmResList;
    }

    private NMZC610001PMsg callNMZC6100Api(Map<?, ?> data){
        NMZC610001PMsg pmsg = new NMZC610001PMsg();
        ZYPEZDItemValueSetter.setValue(pmsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pmsg.xxModeCd, NMZC610001Constant.PROCESS_MODE_INVOICE);
        ZYPEZDItemValueSetter.setValue(pmsg.custInvSrcCd, convertInvSrcCd((String) data.get("DS_BIZ_AREA_CD")));
        ZYPEZDItemValueSetter.setValue(pmsg.dsAcctNum_I1, (String) data.get("BILL_TO_DS_ACCT_NUM"));
        ZYPEZDItemValueSetter.setValue(pmsg.billToCustCd, (String) data.get("BILL_TO_CUST_CD"));
        new NMZC610001().execute(pmsg, ONBATCH_TYPE.BATCH);
        return pmsg;
    }

    private String convertInvSrcCd(String dsBizAreaCd) {
        if (S21StringUtil.isEquals(DS_BIZ_AREA.EQUIPMENT, dsBizAreaCd)) {
            return CUST_INV_SRC.EQUIPMENT;
        } else if (S21StringUtil.isEquals(DS_BIZ_AREA.SUPPLIES, dsBizAreaCd)) {
            return CUST_INV_SRC.SUPPLIES;
        } else if (S21StringUtil.isEquals(DS_BIZ_AREA.PARTS, dsBizAreaCd)) { // QC#
            // 2018/11/15 QC#27954 Mod Start
            return CUST_INV_SRC.PARTS;
            // 2018/11/15 QC#27954 Mod End
        } else if (S21StringUtil.isEquals(DS_BIZ_AREA.CONTRACTS, dsBizAreaCd)) {
            return CUST_INV_SRC.SERVICE_CONTRACT;
        } else if (S21StringUtil.isEquals(DS_BIZ_AREA.FIELD_SERVICE, dsBizAreaCd)) {
            return CUST_INV_SRC.SERVICE_CALL;
            // QC#17962 add Start
        } else if (S21StringUtil.isEquals(DS_BIZ_AREA.INVOICING, dsBizAreaCd)) {
            return CUST_INV_SRC.INVOICING;
            // QC#17962 add End
        }
        return null;
    }

    // 2019/01/10 QC#29758 Mod Start
    // private List<String> getCtacPnt(BigDecimal ctacPsnPk) {
    private List<String> getCtacPnt(BigDecimal ctacPsnPk, List<String> list) {
    // 2019/01/10 QC#29758 Mod End
        // List<String> list = new ArrayList<String>(); // 2019/01/10 QC#29758 Del
        DS_CTAC_PNTTMsg inTMsg = new DS_CTAC_PNTTMsg();
        DS_CTAC_PNTTMsg tMsg = new DS_CTAC_PNTTMsg();
        inTMsg.setSQLID("001");
        inTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inTMsg.setConditionValue("ctacPsnPk01", ctacPsnPk);
        inTMsg.setConditionValue("dsCtacPntTpCd01", DS_CTAC_PNT_TP.EMAIL_WORK);
        inTMsg.setConditionValue("dsOpsOutFlg01", ZYPConstant.FLG_OFF_N);
        inTMsg.setConditionValue("dsCtacPntActvFlg01", ZYPConstant.FLG_ON_Y);
        DS_CTAC_PNTTMsgArray array = (DS_CTAC_PNTTMsgArray) EZDTBLAccessor.findByCondition(inTMsg);
        if (array == null || array.length() == 0) {
            return list;
        }
        for (int i = 0; i < array.length(); i++) {
            tMsg = array.no(i);
            list.add(tMsg.dsCtacPntValTxt.getValue());
        }
        return list;
    }

    private String getToAddr(String psnCd) {
        S21_PSNTMsg inTMsg = new S21_PSNTMsg();
        inTMsg.setSQLID("001");
        inTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inTMsg.setConditionValue("psnCd01", psnCd);
        S21_PSNTMsgArray array = (S21_PSNTMsgArray) EZDTBLAccessor.findByCondition(inTMsg);
        if (array == null || array.length() == 0) {
            return null;
        }
        return array.no(0).emlAddr.getValue();
    }

    @SuppressWarnings("unchecked")
    // QC#54520 2019/11/11 Mod Start
    //private String getFromAddr(String billToCustCd) {
    private String getFromAddr(String billToDsAcctNum, String billToCustCd) {
    // QC#54520 2019/11/11 Mod End
        Map<String, Object> mapParam = new HashMap<String, Object>();
        List<Map<?, ?>> ssmResList;
        mapParam.put("glblCmpyCd", glblCmpyCd);
        mapParam.put("sellToCustCd", billToDsAcctNum);
        mapParam.put("billToCustCd", billToCustCd);
        ssmResList = ssmBatchClient.queryObjectList("getFromAddr", mapParam);
        if (ssmResList == null || ssmResList.size() == 0) {
            return null;
        }
        return (String) ssmResList.get(0).get("EML_ADDR");
    }

    // START 2022/03/28 G.Delgado [QC#59827, ADD]
    /**
     * Get From e-mail address by business
     * @param lineBizTpCd String
     * @param dsBizAreaCd String
     * @return String
     */
    private String getFromAddrByBiz(String lineBizTpCd, String dsBizAreaCd) {
        if (!ZYPCommonFunc.hasValue(lineBizTpCd) || !ZYPCommonFunc.hasValue(dsBizAreaCd)) {
            return null;
        }

        ML_BIZ_ADDR_MAPTMsg mlBizAddrMapTmsg = new ML_BIZ_ADDR_MAPTMsg();
        ZYPEZDItemValueSetter.setValue(mlBizAddrMapTmsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(mlBizAddrMapTmsg.lineBizTpCd, lineBizTpCd);
        ZYPEZDItemValueSetter.setValue(mlBizAddrMapTmsg.dsBizAreaCd, dsBizAreaCd);

        mlBizAddrMapTmsg = (ML_BIZ_ADDR_MAPTMsg) EZDTBLAccessor.findByKey(mlBizAddrMapTmsg);
        if (mlBizAddrMapTmsg == null) {
            return null;
        }
        return mlBizAddrMapTmsg.mlBizAddr.getValue();
    }
    // END 2022/03/28 G.Delgado [QC#59827, ADD]

    private void updateInvPrtCtrl(BigDecimal invPrtCtrlPk, BigDecimal requestPk, String fromAddr, String subject, List<String> toAddrList, String invBillNum) {

        INV_PRT_CTRLTMsg invPrtCtrlTMsg = new INV_PRT_CTRLTMsg();
        ZYPEZDItemValueSetter.setValue(invPrtCtrlTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(invPrtCtrlTMsg.invPrtCtrlPk, invPrtCtrlPk);
        invPrtCtrlTMsg = (INV_PRT_CTRLTMsg) EZDTBLAccessor.findByKeyForUpdateWait(invPrtCtrlTMsg);
        if (invPrtCtrlTMsg == null) {
            return;
        }

        ZYPEZDItemValueSetter.setValue(invPrtCtrlTMsg.invEmlProcStsCd, NWCB018001Constant.EXECUTED);
        ZYPEZDItemValueSetter.setValue(invPrtCtrlTMsg.invFromEmlAddr, fromAddr);
        ZYPEZDItemValueSetter.setValue(invPrtCtrlTMsg.invEmlSubjTxt, subject);
        ZYPEZDItemValueSetter.setValue(invPrtCtrlTMsg.invEmlRqstNum, requestPk);
        String addr = null;
        for (String str : toAddrList) {
            if (addr == null) {
                addr = str;
            } else {
                addr = addr + NWCB018001Constant.COMMA + str;
            }
        }
        ZYPEZDItemValueSetter.setValue(invPrtCtrlTMsg.invEmlAddr, cutOffString(addr, 320));
        ZYPEZDItemValueSetter.setValue(invPrtCtrlTMsg.invEmlProcDt, slsDt);
        S21FastTBLAccessor.update(invPrtCtrlTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(invPrtCtrlTMsg.getReturnCode())) {
            // Error
            totalErrCount = totalErrCount + 1;
            setMailMessage(requestPk.toString(), invBillNum, NWCB018001Constant.ZZZM9013E, new String[] {invPrtCtrlTMsg.getReturnCode() });
            rollback();
            return;
        }
        // Release lock record
        commit();
        totalCommitCount = totalCommitCount + 1;
    }
    private void updateInvPrtCtrlForMerge(BigDecimal invPrtCtrlPk, String fromAddress, String subject, List<String> toAddrList, String invBillNum, BigDecimal rptRqstPk) {

        INV_PRT_CTRLTMsg invPrtCtrlTMsg = new INV_PRT_CTRLTMsg();
        ZYPEZDItemValueSetter.setValue(invPrtCtrlTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(invPrtCtrlTMsg.invPrtCtrlPk, invPrtCtrlPk);
        invPrtCtrlTMsg = (INV_PRT_CTRLTMsg) EZDTBLAccessor.findByKeyForUpdateWait(invPrtCtrlTMsg);
        if (invPrtCtrlTMsg == null) {
            return;
        }

        ZYPEZDItemValueSetter.setValue(invPrtCtrlTMsg.invEmlProcStsCd, NWCB018001Constant.EXECUTED);
        ZYPEZDItemValueSetter.setValue(invPrtCtrlTMsg.invFromEmlAddr, fromAddress);
        ZYPEZDItemValueSetter.setValue(invPrtCtrlTMsg.invEmlSubjTxt, subject);
        if (rptRqstPk != null) {
            ZYPEZDItemValueSetter.setValue(invPrtCtrlTMsg.invEmlRqstNum, rptRqstPk);
        }
        String addr = null;
        for (String str : toAddrList) {
            if (addr == null) {
                addr = str;
            } else {
                addr = addr + NWCB018001Constant.COMMA + str;
            }
        }
        ZYPEZDItemValueSetter.setValue(invPrtCtrlTMsg.invEmlAddr, cutOffString(addr, 320));
        ZYPEZDItemValueSetter.setValue(invPrtCtrlTMsg.invEmlProcDt, slsDt);
        S21FastTBLAccessor.update(invPrtCtrlTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(invPrtCtrlTMsg.getReturnCode())) {
            // Error
            totalErrCount = totalErrCount + 1;
            setMailMessage(invPrtCtrlPk.toString(), invBillNum, NWCB018001Constant.ZZZM9013E, new String[] {invPrtCtrlTMsg.getReturnCode() });
            rollback();
            return;
        }
        // Release lock record
        commit();
        totalCommitCount = totalCommitCount + 1;
    }
    
    private void postErrMail() {

        // get mail information : address
        S21Mail mail = new S21Mail(glblCmpyCd);
        S21MailGroup groupFrom = new S21MailGroup(glblCmpyCd, NWCB018001Constant.GROUP_FROM);

        List<S21MailAddress> fromAddrList = groupFrom.getMailAddress();
        if (fromAddrList.isEmpty()) {
            throw new S21AbendException(NWCB018001Constant.NWCM0060E);
        }
        mail.setFromAddress(fromAddrList.get(0));

        S21MailGroup group = new S21MailGroup(glblCmpyCd, NWCB018001Constant.BUSINESS_ID);
        List<S21MailAddress> toAddrList = group.getMailAddress();

        if (toAddrList.isEmpty()) {
            throw new S21AbendException(NWCB018001Constant.NWCM0060E);
        }
        mail.setToAddressList(toAddrList);

        // get mail template
        S21MailTemplate template = new S21MailTemplate(glblCmpyCd, NWCB018001Constant.MAIL_TEMPLATE_ID);
        template.setTemplateParameter("batchId", NWCB018001Constant.BUSINESS_ID);
        template.setTemplateParameter("batchNm", NWCB018001Constant.BATCH_NM);
        template.setTemplateParameter("batchProcLogId", super.getBatchProcessLogID());
        template.setTemplateParameter("eipRptProcLogPk", processPk);

        // set Message
        StringBuilder msg = new StringBuilder();
        msg.append(NWCB018001Constant.TITLE);
        for (Map<String, String> map : mailErrorList) {
            msg.append(NWCB018001Constant.LINE_FEED_CODE);
            msg.append(map.get(NWCB018001Constant.KEYID));
            msg.append(NWCB018001Constant.SEPARATOR);
            msg.append(map.get(NWCB018001Constant.NUMBER));
            msg.append(NWCB018001Constant.SEPARATOR);
            msg.append(map.get(NWCB018001Constant.MESSAGE));
        }

        template.setTemplateParameter("ErrorInfo", msg.toString());
        mail.setMailTemplate(template);

        // post mail
        mail.postMail();
    }

    /**
     * setMailMessage
     * @param key long
     * @param number String
     * @param msgId String
     * @param msgParam String[]
     */
    private void setMailMessage(String key, String number, String msgId, String[] msgParam) {

        Map<String, String> map = new HashMap<String, String>();
        if (key == null) {
            map.put(NWCB018001Constant.KEYID, "0");
        } else {
            map.put(NWCB018001Constant.KEYID, new BigDecimal(key).toString());
        }
        map.put(NWCB018001Constant.NUMBER, number);
        if (msgParam == null) {
            map.put(NWCB018001Constant.MESSAGE, S21MessageFunc.clspGetMessage(msgId));
        } else {
            map.put(NWCB018001Constant.MESSAGE, S21MessageFunc.clspGetMessage(msgId, msgParam));
        }
        mailErrorList.add(map);
    }
    
    /**
     * setMailMessage
     * @param key long
     * @param number String
     * @param msgId String
     * @param msgParam String[]
     */
    private void setMailMessageForEIP(BigDecimal key, String status, String msg) {
        Map<String, String> map = new HashMap<String, String>();
        map.put(NWCB018001Constant.KEYID, key.toString());
        map.put(NWCB018001Constant.NUMBER, "Status : " + status);
        map.put(NWCB018001Constant.MESSAGE, msg);
        mailErrorList.add(map);
    }

    private String cutOffString(String val, int len) {
        if (ZYPCommonFunc.hasValue(val) && val.length() > len) {
            return val.substring(0, len);
        } else {
            return val;
        }
    }
    //QC#18693
    private static byte[] mergePdfFiles(List<String> filePathofPdffiles) {
        S21PDFMerger pdfTool = new S21PDFMerger();
        byte[] mergedPdf = pdfTool.merge(filePathofPdffiles);
        return mergedPdf;
    }
}
