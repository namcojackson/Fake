/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWCL0060;

import static business.blap.NWCL0060.constant.NWCL0060Constant.ML_TMPL_KEY_BILL_TO_DS_ACCT_NM;
import static business.blap.NWCL0060.constant.NWCL0060Constant.TMP_CMNT;
import static business.blap.NWCL0060.constant.NWCL0060Constant.ML_TMPL;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;

import business.blap.NWCL0060.common.NWCL0060CommonLogic;
import parts.common.EZDSystemEnv;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailAttachment;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;
import com.canon.cusa.s21.framework.mail.S21MailAttachment.AttachmentType;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.pdf.S21PDFMergeService;
import com.canon.cusa.s21.framework.pdf.message.S21EmailOutputParameter;
import com.canon.cusa.s21.framework.pdf.message.S21InputParameter;
import com.canon.cusa.s21.framework.pdf.message.S21PDFMergeRequest;
import com.canon.cusa.s21.framework.printing.common.S21EIPPrintingService;

/**
 *<pre>
 * Mail Entry.
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/05/25   Fujitsu         M.Nakamura         Create          N/A
 * 2018/09/20   Hitachi         Y.Takeno           Update          QC#19578
 * 2022/03/31   CITS            A.Cullano          Update          QC#59828
 * 2022/10/09   Hitachi         S.Nakatani         Update          QC#56656
 * 2023/04/07   Hitachi         S.Nakatani         Update          QC#61270
 *</pre>
 */
public class NWCL0060BL06 extends S21BusinessHandler {

    // START 2022/10/09 S.Nakatani [QC#56656, MOD]
    private S21PDFMergeService service;
    // END 2022/10/09 [QC#56656, MOD]

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NWCL0060CMsg bizMsg = (NWCL0060CMsg) cMsg;

            if ("NWCL0060Scrn00_SendMail".equals(screenAplID)) {
                doProcess_NWCL0060Scrn00_SendMail(bizMsg);
           } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * <pre>
     * SendMail Event
     * </pre>
     * @param bizMsg Business Msg
     */
    private void doProcess_NWCL0060Scrn00_SendMail(NWCL0060CMsg bizMsg) {

        // START 2022/10/09 S.Nakatani [QC#56656, ADD]
        // START 2023/04/07 S.Nakatani [QC#61270,MOD]
//        if(bizMsg.A.getValidCount() > 1){
        if(bizMsg.A.getValidCount() > 1 && ZYPConstant.FLG_ON_Y.equals(bizMsg.conslInvFlg_H1.getValue())){
        // END 2023/04/07 S.Nakatani [QC#61270,MOD]
            invMergeRequest(bizMsg);
        } else {
        // END 2022/10/09 S.Nakatani [QC#56656, ADD]
            
        // Send Mail
        S21Mail s21Mail = new S21Mail(getGlobalCompanyCode());

        // START 2022/03/31 A.Cullano [QC#59828, MOD]
        // String defFromEmailAddr = ZYPCodeDataUtil.getVarCharConstValue("NWCL0060_DEF_FROM_EML_ADDR", getGlobalCompanyCode());
        // String fromEmlAddr = getContextUserInfo().getEmailAddress();
        // if (fromEmlAddr == null || "".equals(fromEmlAddr)) {
        //    fromEmlAddr = defFromEmailAddr;
        // }
        String fromEmlAddr;
        if (bizMsg.xxRadioBtn_H1.getValue().intValue() == 1) {
            // Your email address
            fromEmlAddr = bizMsg.fromEmlAddr_H1.getValue();
        } else {
            // Default email address
            fromEmlAddr = bizMsg.fromEmlAddr_H2.getValue();
        }
        // END 2022/03/31 A.Cullano [QC#59828, MOD]

        // From Mail Address
        S21MailAddress emlAddr = new S21MailAddress(getGlobalCompanyCode(), fromEmlAddr);
        s21Mail.setFromAddress(emlAddr);

        // To Mail Address
        String mailAddressS = bizMsg.emlAddr_H1.getValue();
        mailAddressS = mailAddressS.replaceAll(" ", "");
        bizMsg.emlAddr_H1.setValue(mailAddressS);

        List<S21MailAddress> outMailList = NWCL0060CommonLogic.getMailList(mailAddressS, bizMsg, getGlobalCompanyCode());
        if (outMailList == null || outMailList.isEmpty()) {
            bizMsg.emlAddr_H1.setErrorInfo(1, "NWCM0093E");
            return;
        } else {
            s21Mail.setToAddressList(outMailList);
        }

        // Set Template
        S21MailTemplate s21MailTemplate = new S21MailTemplate(getGlobalCompanyCode(), ML_TMPL);
        s21MailTemplate.setTemplateParameter(TMP_CMNT, bizMsg.xxMlCmntTxt_H1.getValue());
        // START 2018/09/20 [QC#19578, ADD]
        String billToDsAcctNm = bizMsg.A.no(0).billToDsAcctNm_A1.getValue();
        s21MailTemplate.setTemplateParameter(ML_TMPL_KEY_BILL_TO_DS_ACCT_NM, billToDsAcctNm);
        // END   2018/09/20 [QC#19578, ADD]

        s21Mail.setMailTemplate(s21MailTemplate);
        S21EIPPrintingService eipService = new S21EIPPrintingService();

        try {
            // Set Attachment
            List<S21MailAttachment> attachmentList = new ArrayList<S21MailAttachment>();
            for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
                String filePath = bizMsg.A.no(i).invFileUrl_A1.getValue();
    //CSA MOD Start
    //            if (!ZYPCommonFunc.hasValue(filePath)) {
    //                continue;
    //            }
    //            S21MailAttachment attachment = new S21MailAttachment(getGlobalCompanyCode());
    //            String[] fileNameArray = filePath.replace("\\", "/").split("/");
    //            String fileName = fileNameArray[fileNameArray.length - 1];
    
                S21MailAttachment attachment = new S21MailAttachment(getGlobalCompanyCode());
                String fileName;
                if (ZYPCommonFunc.hasValue(filePath)) {
                    String[] fileNameArray = filePath.replace("\\", "/").split("/");
                    fileName = fileNameArray[fileNameArray.length - 1];
                    attachment.setAttachmentFilePath(filePath);
                    attachment.setFileName(fileName);
                } else {
                    BigDecimal rqstNum = bizMsg.A.no(i).eipRptRqstPk_A1.getValue();
                    if (rqstNum == null) {
                        throw new S21AbendException("get report failure");
                    }
                    fileName = bizMsg.A.no(i).xxFilePathTxt_A1.getValue();
    
                    // Initialize EIP Printing Service Class
//                    S21EIPPrintingService eipService = new S21EIPPrintingService();
                    // Get Archived Report byte array, input parameter is 'Report Request PK'(Mandatory)
                    byte[] report = eipService.getArchivedReport(rqstNum.longValue());
                    if (report == null) {
                        throw new S21AbendException("get report failure");
                    }

                    attachment.setAttachment(report);
    //                attachment.setFileName(rqstNum.toString());
                    attachment.setFileName(fileName);
                }
    //CSA MOD End
                attachment.setContentType(AttachmentType.DEFAULT_BINARY.getType());
                attachmentList.add(attachment);
            }
            s21Mail.setAttachmentList(attachmentList);
            
            s21Mail.postMail();

        } catch (S21AbendException e) {
            bizMsg.xxArMlBodyTmplTxt_H1.setErrorInfo(1, "ZZXM0001E", new String[] {e.getMessage()});
        }
        
        
// CSA ADD Start
        ZYPEZDItemValueSetter.setValue(bizMsg.scrInvEmlTs, ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmss"));
        ZYPEZDItemValueSetter.setValue(bizMsg.scrInvEmlAddr, bizMsg.emlAddr_H1.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.scrInvEmlSubjTxt, s21MailTemplate.getSubject());
        ZYPEZDItemValueSetter.setValue(bizMsg.scrInvEmlCmntTxt, bizMsg.xxMlCmntTxt_H1.getValue());
// CSA ADD End
        // START 2022/10/09 S.Nakatani [QC#56656, ADD]
        }
        // END 2022/10/09 S.Nakatani [QC#56656, ADD]
    }

    // START 2022/10/09 S.Nakatani [QC#56656, ADD]
    private void invMergeRequest(NWCL0060CMsg bizMsg){
        
        // Start Merge Process
        service = new S21PDFMergeService();
        S21PDFMergeRequest request = new S21PDFMergeRequest();
        
        // Set mrgRequestPK
        long mrgRequestPK = service.init();
        request.setMrgRequestPK(mrgRequestPK);
        
        // Set filePathList
        List<S21InputParameter> filePathList = new ArrayList<S21InputParameter>();
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            S21InputParameter inputParameter = new S21InputParameter();
            inputParameter.setReportRequestPK((bizMsg.A.no(i).eipRptRqstPk_A1.getValue()).longValue());
            filePathList.add(inputParameter);
        }
        request.setFilePathList(filePathList);
        request = service.getArchivedReport(request);
        String mergeErr = null;
        if(ZYPCommonFunc.hasValue(request.getErrCd())){
            mergeErr = request.getErrCd().toString();
            if(mergeErr.endsWith("E")){
            bizMsg.setMessageInfo(mergeErr);
            return;
            }
        }
        request.setFilePathList(filePathList);

        // Set fileOutFlg
        request.setFileOutFlg("Y");

        // Set emailOutFlg
        request.setEmailOutFlg("Y");

        // Set mailParam
        S21EmailOutputParameter mailParam = new S21EmailOutputParameter();
        String billToDsAcctNm = bizMsg.A.no(0).billToDsAcctNm_A1.getValue();
        S21MailTemplate s21MailTemplate = new S21MailTemplate(getGlobalCompanyCode(), "NWCL0050M001");
        s21MailTemplate.setTemplateParameter(TMP_CMNT, bizMsg.xxMlCmntTxt_H1.getValue());
        s21MailTemplate.setTemplateParameter(ML_TMPL_KEY_BILL_TO_DS_ACCT_NM, billToDsAcctNm);
        mailParam.setSubject(s21MailTemplate.getSubject());
        mailParam.setBodyText(s21MailTemplate.getBody());

        String billToDsAcctNum = bizMsg.A.no(0).billToDsAcctNum_A1.getValue();
        String createDate = ZYPDateUtil.convertFormat(ZYPDateUtil.getSalesDate(getGlobalCompanyCode()), ZYPDateUtil.TYPE_YYYYMMDD, ZYPDateUtil.TYPE_MMDDYYYY, null);
        String fileName = billToDsAcctNum + "_" + createDate + ".pdf";
        mailParam.setAttachFileName(fileName);

        String fromEmlAddr;
        if (bizMsg.xxRadioBtn_H1.getValue().intValue() == 1) {
            // User email address
            fromEmlAddr = bizMsg.fromEmlAddr_H1.getValue();
        } else {
            // Default email address
            fromEmlAddr = bizMsg.fromEmlAddr_H2.getValue();
        }
        mailParam.setFromAddress(new S21MailAddress(getGlobalCompanyCode(), fromEmlAddr));
        String toEmlAddr = bizMsg.emlAddr_H1.getValue();
        toEmlAddr = toEmlAddr.replaceAll(" ", "");
        bizMsg.emlAddr_H1.setValue(toEmlAddr);

        ArrayList<S21MailAddress> toAddressList = (ArrayList<S21MailAddress>) NWCL0060CommonLogic.getMailList(toEmlAddr, bizMsg, getGlobalCompanyCode());
        if (toAddressList == null || toAddressList.isEmpty()) {
            bizMsg.emlAddr_H1.setErrorInfo(1, "NWCM0093E");
            return;
        }
        mailParam.setToAddressList(toAddressList);
        request.setMailParam(mailParam);

        // Set errMailParam
        S21EmailOutputParameter errMailParam = new S21EmailOutputParameter();
        billToDsAcctNm = bizMsg.A.no(0).billToDsAcctNm_A1.getValue();
        S21MailTemplate errS21MailTemplate = new S21MailTemplate(getGlobalCompanyCode(), "NWCL0060M001");
        errS21MailTemplate.setTemplateParameter(ML_TMPL_KEY_BILL_TO_DS_ACCT_NM, billToDsAcctNm);
        errMailParam.setSubject(errS21MailTemplate.getSubject());

        double devisorVal =  Math.pow(1024, 2);
        long maxMrgFileSize = Long.parseLong(EZDSystemEnv.getString("S21_PDF_MRG_MAX_MRG_FILE_SIZE"));
        double mSize = (double) maxMrgFileSize / devisorVal;
        String maxSize = String.format("%.3f", mSize);
        errS21MailTemplate.setTemplateParameter("PdfMergeLimit", maxSize);

        long totalFileSize = 0;
        for (S21InputParameter filePath : request.getFilePathList()) {
            totalFileSize += filePath.getFileSize();
        }
        double tSize = (double) totalFileSize / devisorVal;
        String totalSize = String.format("%.3f", tSize);
        errS21MailTemplate.setTemplateParameter("PdfMergeSize", totalSize);
        errMailParam.setBodyText(errS21MailTemplate.getBody());

        fromEmlAddr = bizMsg.fromEmlAddr_H2.getValue();
        errMailParam.setFromAddress(new S21MailAddress(getGlobalCompanyCode(), fromEmlAddr));

        toEmlAddr = bizMsg.fromEmlAddr_H1.getValue();
        ArrayList<S21MailAddress> outMailList = (ArrayList<S21MailAddress>) NWCL0060CommonLogic.getMailList(toEmlAddr, bizMsg, getGlobalCompanyCode());
        if (outMailList == null || outMailList.isEmpty()) {
            bizMsg.emlAddr_H1.setErrorInfo(1, "NWCM0093E");
            return;
        }
        errMailParam.setToAddressList(outMailList);
        request.setErrMailParam(errMailParam);

        // Merge Request
        request = service.mergeRequest(request);
        if(ZYPCommonFunc.hasValue(request.getErrCd())){
            mergeErr = request.getErrCd().toString();
            if(mergeErr.endsWith("E")){
            bizMsg.setMessageInfo(mergeErr);
            return;
            }
        }
        
        ZYPEZDItemValueSetter.setValue(bizMsg.scrInvEmlTs, ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmss"));
        ZYPEZDItemValueSetter.setValue(bizMsg.scrInvEmlAddr, bizMsg.emlAddr_H1.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.scrInvEmlSubjTxt, s21MailTemplate.getSubject());
        ZYPEZDItemValueSetter.setValue(bizMsg.scrInvEmlCmntTxt, bizMsg.xxMlCmntTxt_H1.getValue());
    }
    // END 2022/10/09 S.Nakatani [QC#56656, ADD]
}
