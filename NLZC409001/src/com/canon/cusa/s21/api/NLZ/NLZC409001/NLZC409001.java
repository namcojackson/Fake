/**
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NLZ.NLZC409001;

import static com.canon.cusa.s21.api.NLZ.NLZC409001.NLZC409001Constant.CHAR_BLANK;
import static com.canon.cusa.s21.api.NLZ.NLZC409001.NLZC409001Constant.CHAR_HYPHEN;
import static com.canon.cusa.s21.api.NLZ.NLZC409001.NLZC409001Constant.CSA_RMA_RPT_PRINT_RQST_SQ;
import static com.canon.cusa.s21.api.NLZ.NLZC409001.NLZC409001Constant.CST_DEBUG_MSG_LVL;
import static com.canon.cusa.s21.api.NLZ.NLZC409001.NLZC409001Constant.DEBUG_LOG_HDR;
import static com.canon.cusa.s21.api.NLZ.NLZC409001.NLZC409001Constant.EXT;
import static com.canon.cusa.s21.api.NLZ.NLZC409001.NLZC409001Constant.FILE_NM_PREF;
import static com.canon.cusa.s21.api.NLZ.NLZC409001.NLZC409001Constant.GLBL_CMPY_CD;
import static com.canon.cusa.s21.api.NLZ.NLZC409001.NLZC409001Constant.HALF_SPACE;
import static com.canon.cusa.s21.api.NLZ.NLZC409001.NLZC409001Constant.INTL_LANG_VAL_COL_NM;
import static com.canon.cusa.s21.api.NLZ.NLZC409001.NLZC409001Constant.MAIL_GROUP_ID_FROM;
import static com.canon.cusa.s21.api.NLZ.NLZC409001.NLZC409001Constant.MAIL_KEY;
import static com.canon.cusa.s21.api.NLZ.NLZC409001.NLZC409001Constant.MAIL_TEMPLATE_ID;
import static com.canon.cusa.s21.api.NLZ.NLZC409001.NLZC409001Constant.NLZM2259E;
import static com.canon.cusa.s21.api.NLZ.NLZC409001.NLZC409001Constant.NLZM2485E;
import static com.canon.cusa.s21.api.NLZ.NLZC409001.NLZC409001Constant.NLZM2486E;
import static com.canon.cusa.s21.api.NLZ.NLZC409001.NLZC409001Constant.NLZM2487E;
import static com.canon.cusa.s21.api.NLZ.NLZC409001.NLZC409001Constant.NLZM2488E;
import static com.canon.cusa.s21.api.NLZ.NLZC409001.NLZC409001Constant.NLZM2489E;
import static com.canon.cusa.s21.api.NLZ.NLZC409001.NLZC409001Constant.NLZM2490E;
import static com.canon.cusa.s21.api.NLZ.NLZC409001.NLZC409001Constant.NLZM2491E;
import static com.canon.cusa.s21.api.NLZ.NLZC409001.NLZC409001Constant.NLZM2502E;
import static com.canon.cusa.s21.api.NLZ.NLZC409001.NLZC409001Constant.REPORT_ID;
import static com.canon.cusa.s21.api.NLZ.NLZC409001.NLZC409001Constant.REPORT_TITLE;
import static com.canon.cusa.s21.api.NLZ.NLZC409001.NLZC409001Constant.RWS_REF_NUM;

import java.util.List;

import parts.common.EZDDebugOutput;
import business.parts.NLZC409001PMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRSMT_METH_TP;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;
import com.canon.cusa.s21.framework.printing.common.S21EIPPrintingService;
import com.canon.cusa.s21.framework.printing.common.S21ReportRequestBean;
import com.canon.cusa.s21.framework.printing.eip.S21EmailOutputParameter;
import com.canon.cusa.s21.framework.printing.eip.S21InputParameter;
import com.canon.cusa.s21.framework.printing.eip.S21PrinterOutputParameter;

/**
 *<pre>
 * NLZC4090:RMA Report Send To EIP API
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/18/2016   CITS            M.Naito         Create          N/A
 * 12/27/2016   CITS            Y.Fujii         Update          QC#16547
 *</pre>
 */
public class NLZC409001 extends S21ApiCommonBase {

    /**
     * <pre>Constructor</pre>
     * @param none
     * @throws none
     */
    public NLZC409001() {
        super();

    }

    /**
     * <pre>
     * Report Send To EIP API
     * Call execute(NLZC409001PMsg, ONBATCH_TYPE) method by each PMsg.
     * </pre>
     * @param inpPrmMsg NLZC409001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final List<NLZC409001PMsg> inpPrmMsg, final ONBATCH_TYPE onBatchType) {
        for (int i = 0; i < inpPrmMsg.size(); i++) {
            execute(inpPrmMsg.get(i), onBatchType);
        }
    }

    /**
     * <pre>
     * Call execute(NLZC409001PMsg, ONBATCH_TYPE) method by each PMsg.
     * </pre>
     * @param inpPrmPMsg NLZC409001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final NLZC409001PMsg inpPrmPMsg, final ONBATCH_TYPE onBatchType) {

        final S21ApiMessageMap msgMap = new S21ApiMessageMap(inpPrmPMsg);

        try {
            writeReport(msgMap);
        } finally {
            msgMap.flush();
        }
    }

    /**
     * <pre>
     * Write Report
     * </pre>
     * @param msgMap
     */
    private void writeReport(S21ApiMessageMap msgMap) {
        NLZC409001PMsg pMsg = (NLZC409001PMsg) msgMap.getPmsg();

        S21EIPPrintingService eipBasic = new S21EIPPrintingService();

        long rptRqstPk = 0;
        if (!checkParam(msgMap)) {
            return;
        }

        // Generate S21ReportRequestBean
        S21ReportRequestBean requestBean = new S21ReportRequestBean(REPORT_ID);
        requestBean.setRptFileTpCd(S21ReportRequestBean.OUTPUT_FORMAT_TYPE_PDF);

        // Generate S21InputParameter
        S21InputParameter param = new S21InputParameter();
        param.addReportParameter(GLBL_CMPY_CD, pMsg.glblCmpyCd.getValue());
        param.addReportParameter(CSA_RMA_RPT_PRINT_RQST_SQ, pMsg.csaRmaRptPrintRqstSq.getValue());
        param.addReportParameter(INTL_LANG_VAL_COL_NM, param.getSystemDefaultLanguage());
        param.addReportParameter(RWS_REF_NUM, pMsg.rwsRefNum.getValue());
        requestBean.setInputParamBean(param);

        String titleRwsRefNum = pMsg.rwsRefNum.getValue();
        titleRwsRefNum = titleRwsRefNum.replace(CHAR_HYPHEN, CHAR_BLANK);
        requestBean.setRptTtlNm(titleRwsRefNum + REPORT_TITLE);
        // Case PDF Download
        if (TRSMT_METH_TP.PDF_DOWNLOAD.equals(pMsg.trsmtMethTpCd.getValue())) {

            rptRqstPk = eipBasic.createReportByAsync(requestBean);

        // Case Email
        } else if (TRSMT_METH_TP.EMAIL_PDF.equals(pMsg.trsmtMethTpCd.getValue())) {

            rptRqstPk = sendEmail(msgMap, eipBasic, requestBean);

        // Case Printer
        } else if (TRSMT_METH_TP.PRINTER.equals(pMsg.trsmtMethTpCd.getValue())) {

            rptRqstPk = print(eipBasic, pMsg.rptBrId.getValue(), requestBean);

        } else {
            this.addMsgId(msgMap, NLZM2487E);
        }

        if (rptRqstPk != 0) {
            // activate Report Processing.
            long processPk = eipBasic.activateAsyncReportJob();
        }

    }

    /**
     * <pre>
     * common input parameter check
     * </pre>
     * @param msgMap Message Map
     * @return check result(OK:true, NG:false)
     */
    private boolean checkParam(S21ApiMessageMap msgMap) {

        // IN-parameter PMsg
        NLZC409001PMsg inpPrmPMsg = (NLZC409001PMsg) msgMap.getPmsg();

        // Global Company Code
        if (!ZYPCommonFunc.hasValue(inpPrmPMsg.glblCmpyCd)) {
            this.addMsgId(msgMap, NLZM2259E);
            return false;
        }

        // csaRmaRptPrintRqstSq
        if (!ZYPCommonFunc.hasValue(inpPrmPMsg.csaRmaRptPrintRqstSq)) {
            this.addMsgId(msgMap, NLZM2485E);
            return false;
        }

        // rwsRefNum
        if (!ZYPCommonFunc.hasValue(inpPrmPMsg.rwsRefNum)) {
            this.addMsgId(msgMap, NLZM2502E);
            return false;
        }
        // trsmtMethTpCd
        if (!ZYPCommonFunc.hasValue(inpPrmPMsg.trsmtMethTpCd)) {
            this.addMsgId(msgMap, NLZM2486E);
            return false;
        }

        if (TRSMT_METH_TP.PRINTER.equals(inpPrmPMsg.trsmtMethTpCd)) {
            // rptRptId
            if (!ZYPCommonFunc.hasValue(inpPrmPMsg.rptBrId)) {
                this.addMsgId(msgMap, NLZM2489E);
                return false;
            }
        } else if (TRSMT_METH_TP.EMAIL_PDF.equals(inpPrmPMsg.trsmtMethTpCd)) {
            // carrCtacEmlAddr
            if (!ZYPCommonFunc.hasValue(inpPrmPMsg.carrCtacEmlAddr)) {
                this.addMsgId(msgMap, NLZM2488E);
                return false;
            }
        }
        return true;
    }

    private long sendEmail(S21ApiMessageMap msgMap, S21EIPPrintingService eipBasic, S21ReportRequestBean requestBean) {

        NLZC409001PMsg pMsg = (NLZC409001PMsg) msgMap.getPmsg();

        // From Address
        String fromAddress = getFromAddress(pMsg.glblCmpyCd.getValue());
        if (!ZYPCommonFunc.hasValue(fromAddress)) {
            this.addMsgId(msgMap, NLZM2490E);
        }

        // Mail Template
        S21MailTemplate template = getTemplate(pMsg);
        if (template == null) {
            this.addMsgId(msgMap, NLZM2491E);
        }

        S21EmailOutputParameter emailParam = new S21EmailOutputParameter();
        emailParam.addToAddress(pMsg.carrCtacEmlAddr.getValue().replace(HALF_SPACE, ""));
        emailParam.setBranchNo(pMsg.trsmtMethTpCd.getValue());
        emailParam.setAttachementFlag(true);
        emailParam.setSubject(template.getSubject());
        emailParam.setBodyText(template.getBody());
        emailParam.setAttachFileName(FILE_NM_PREF + pMsg.rwsRefNum.getValue() + EXT);
        emailParam.setSenderAddress(fromAddress);
        requestBean.setEmailOutParamBean(emailParam);

        return eipBasic.createReportByAsync(requestBean);
    }

    /**
     * Get From Address
     * @return From Address
     */
    private String getFromAddress(String glblCmpyCd) {

        S21MailGroup groupFrom = new S21MailGroup(glblCmpyCd, MAIL_GROUP_ID_FROM);
        groupFrom.setMailKey1(MAIL_KEY);
        List<S21MailAddress> addrFromList = groupFrom.getMailAddress();

        if (addrFromList != null && !addrFromList.isEmpty()) {
            return addrFromList.get(0).getAddress();
        }

        return null;
    }

    /**
     * Get Mail Template
     * @param bean OrderInfoBean
     * @return S21MailTemplate
     */
    private S21MailTemplate getTemplate(NLZC409001PMsg pMsg) {

        // Create template
        S21MailTemplate template = new S21MailTemplate(pMsg.glblCmpyCd.getValue(), MAIL_TEMPLATE_ID);
        if (template == null) {
            return null;
        }

        // Set Parameter
        template.setTemplateParameter("rma", pMsg.rwsRefNum.getValue());

        return template;
    }

    private long print(S21EIPPrintingService eipBasic, String rptBrNum, S21ReportRequestBean requestBean) {

        S21PrinterOutputParameter outputParam = new S21PrinterOutputParameter();
        //Set Output Parameter
        outputParam.setBranchNo(rptBrNum);
        requestBean.setPrintOutParamBean(outputParam);

        return eipBasic.createReportByAsync(requestBean);
    }

    /**
     * <pre>
     * Add Message ID to MessageMap, and print debug log.
     * </pre>
     * @param msgMap Message Manager
     * @param msgId String setting value for Message ID
     * @throws none
     */
    private void addMsgId(S21ApiMessageMap msgMap, String msgId) {

        msgMap.addXxMsgId(msgId);

        printDebugLog(DEBUG_LOG_HDR + msgId);
    }

    /**
     * <pre>
     * Print debug log.
     * </pre>
     * @param debugMsg
     */
    private void printDebugLog(String debugMsg) {
        if (EZDDebugOutput.isDebug()) {
            EZDDebugOutput.println(CST_DEBUG_MSG_LVL, debugMsg, this);
        }
    }
}
