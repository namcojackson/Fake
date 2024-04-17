/*
 * <Pre>Copyright (c) 2012 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NPZ.NPZC005001;

import static com.canon.cusa.s21.api.NPZ.NPZC005001.NPZC005001Constant.CST_DEBUG_MSG_LVL;
import static com.canon.cusa.s21.api.NPZ.NPZC005001.NPZC005001Constant.DFAULT_FAX_SUBJECT;
import static com.canon.cusa.s21.api.NPZ.NPZC005001.NPZC005001Constant.GLBL_CMPY_CD;
import static com.canon.cusa.s21.api.NPZ.NPZC005001.NPZC005001Constant.HALF_SPACE;
import static com.canon.cusa.s21.api.NPZ.NPZC005001.NPZC005001Constant.INTL_LANG_VAL_COL_NM;
import static com.canon.cusa.s21.api.NPZ.NPZC005001.NPZC005001Constant.MAIL_BODY_COMMENT;
import static com.canon.cusa.s21.api.NPZ.NPZC005001.NPZC005001Constant.MAIL_GROUP_ID_FROM;
import static com.canon.cusa.s21.api.NPZ.NPZC005001.NPZC005001Constant.MAIL_KEY_FROM;
import static com.canon.cusa.s21.api.NPZ.NPZC005001.NPZC005001Constant.MAIL_TEMPLATE_ID;
import static com.canon.cusa.s21.api.NPZ.NPZC005001.NPZC005001Constant.NPAM0053E;
import static com.canon.cusa.s21.api.NPZ.NPZC005001.NPZC005001Constant.NPAM1250E;
import static com.canon.cusa.s21.api.NPZ.NPZC005001.NPZC005001Constant.NPAM1251E;
import static com.canon.cusa.s21.api.NPZ.NPZC005001.NPZC005001Constant.NPAM1252E;
import static com.canon.cusa.s21.api.NPZ.NPZC005001.NPZC005001Constant.NPAM1253E;
import static com.canon.cusa.s21.api.NPZ.NPZC005001.NPZC005001Constant.NPAM1254E;
import static com.canon.cusa.s21.api.NPZ.NPZC005001.NPZC005001Constant.NPAM1265E;
import static com.canon.cusa.s21.api.NPZ.NPZC005001.NPZC005001Constant.NPAM1266E;
import static com.canon.cusa.s21.api.NPZ.NPZC005001.NPZC005001Constant.NPAM1359E;
import static com.canon.cusa.s21.api.NPZ.NPZC005001.NPZC005001Constant.NPZM0001E;
import static com.canon.cusa.s21.api.NPZ.NPZC005001.NPZC005001Constant.PO_ORD_NUM;
import static com.canon.cusa.s21.api.NPZ.NPZC005001.NPZC005001Constant.PO_RPT_PRINT_RQST_SQ;
import static com.canon.cusa.s21.api.NPZ.NPZC005001.NPZC005001Constant.RCV_RPT_TS;
import static com.canon.cusa.s21.api.NPZ.NPZC005001.NPZC005001Constant.REPORT_ID;
import static com.canon.cusa.s21.api.NPZ.NPZC005001.NPZC005001Constant.RPT_NM_SUFIX_DOWN;
import static com.canon.cusa.s21.api.NPZ.NPZC005001.NPZC005001Constant.RPT_NM_SUFIX_EMAIL;
import static com.canon.cusa.s21.api.NPZ.NPZC005001.NPZC005001Constant.RPT_NM_SUFIX_FAX;
import static com.canon.cusa.s21.api.NPZ.NPZC005001.NPZC005001Constant.RPT_NM_SUFIX_PRINTER;
import static com.canon.cusa.s21.api.NPZ.NPZC005001.NPZC005001Constant.TRSMT_METH_TP_SCRN;
import static com.canon.cusa.s21.api.NPZ.NPZC005001.NPZC005001Constant.USR_ID;
import static com.canon.cusa.s21.api.NPZ.NPZC005001.NPZC005001Constant.ZZSM4122E;
import static com.canon.cusa.s21.api.NPZ.NPZC005001.NPZC005001Constant.MAIL_SUBJECT_PO_ORD_NUM;
import static com.canon.cusa.s21.api.NPZ.NPZC005001.NPZC005001Constant.FILE_NM_PREF;
import static com.canon.cusa.s21.api.NPZ.NPZC005001.NPZC005001Constant.FILE_NM_SUFF;
import static com.canon.cusa.s21.api.NPZ.NPZC005001.NPZC005001Constant.EXT;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDDebugOutput;
import business.parts.NPZC005001PMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRSMT_METH_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;
import com.canon.cusa.s21.framework.printing.common.S21EIPPrintingService;
import com.canon.cusa.s21.framework.printing.common.S21ReportRequestBean;
import com.canon.cusa.s21.framework.printing.eip.S21EmailOutputParameter;
import com.canon.cusa.s21.framework.printing.eip.S21FaxOutputParameter;
import com.canon.cusa.s21.framework.printing.eip.S21InputParameter;

/**
 * <pre>
 * Purchase Order Report API
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/04/2010   Fujitsu         R.Mori          Create          N/A
 * 02/04/2013   Hitachi         T.Aoyagi        Update          WDS#71
 * 02/05/2016   CITS            R.Shimamoto     Update          V0.4
 * 05/19/2016   CITS            Hisashi         Update          V0.7
 * 11/28/2016   CITS            Y.Fujii         Update          R350
 * 01/24/2019   CITS            T.Tokutomi      Update          QC#29971
 * 02/03/2021   CITS            J.Evangelista   Update          QC#58284
 *</pre>
 */

public class NPZC005001 extends S21ApiCommonBase {

    /**
     * Type Screen
     */
    private String typeScreen;

    // QC#29971 Add.
    /** S21SsmBatchClient */
    private S21SsmBatchClient glSsmBatchClient = null;


    /**
     * <pre>Constructor</pre>
     *
     * @param none
     * @throws none
     */
    public NPZC005001() {
        super();
    }

    /**
     * <pre>
     * Call execute(NPZC005001PMsg, ONBATCH_TYPE) method by each PMsg.
     * </pre>
     * @param inpPrmMsg NPZC005001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final List<NPZC005001PMsg> inpPrmMsg, final ONBATCH_TYPE onBatchType) {
        for (int i = 0; i < inpPrmMsg.size(); i++) {
            execute(inpPrmMsg.get(i), onBatchType);
        }
    }

    /**
     * <pre>
     * Call execute(NPZC005001PMsg, ONBATCH_TYPE) method by each PMsg.
     * </pre>
     * @param inpPrmPMsg NPZC005001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final NPZC005001PMsg inpPrmPMsg, final ONBATCH_TYPE onBatchType) {

        final S21ApiMessageMap msgMap = new S21ApiMessageMap(inpPrmPMsg);
        glSsmBatchClient = S21SsmBatchClient.getClient(this.getClass());

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
        NPZC005001PMsg pMsg = (NPZC005001PMsg) msgMap.getPmsg();


        S21EIPPrintingService eipBasic = new S21EIPPrintingService();

        long rptRqstPk = 0;

        try {
            if (ZYPCommonFunc.hasValue(pMsg.glblCmpyCd)) {
                typeScreen = ZYPCodeDataUtil.getVarCharConstValue(TRSMT_METH_TP_SCRN, pMsg.glblCmpyCd.getValue());
            }
            if (!checkParam(msgMap)) {
                return;
            }

            // Generate S21ReportRequestBean
            S21ReportRequestBean requestBean = new S21ReportRequestBean(REPORT_ID);
            requestBean.setRptFileTpCd(S21ReportRequestBean.OUTPUT_FORMAT_TYPE_PDF);

            // Generate S21InputParameter
            S21InputParameter param = new S21InputParameter();
            param.addReportParameter(GLBL_CMPY_CD, pMsg.glblCmpyCd.getValue());
            param.addReportParameter(USR_ID, pMsg.userId.getValue());
            param.addReportParameter(PO_ORD_NUM, pMsg.poOrdNum.getValue());
            param.addReportParameter(RCV_RPT_TS, pMsg.procStartTs.getValue());
            param.addReportParameter(PO_RPT_PRINT_RQST_SQ, pMsg.poRptPrintRqstSq.getValue());
            // INTL_LANG_VAL_COL_NM
            param.addReportParameter(INTL_LANG_VAL_COL_NM, param.getSystemDefaultLanguage());
            requestBean.setInputParamBean(param);

            // Case PDF Download
            if (pMsg.trsmtMethTpCd.getValue().equals(typeScreen)) {

                requestBean.setRptTtlNm(RPT_NM_SUFIX_DOWN + pMsg.poOrdNum.getValue() + pMsg.procStartTs.getValue());

                // START 2021/02/03 J.Evangelista [QC#58284,MOD]
//                rptRqstPk = callS21EIPPrintingServiceToCreate(requestBean);
                rptRqstPk = callS21EIPPrintingServiceToCreate(eipBasic, requestBean);
                // END 2021/02/03 J.Evangelista [QC#58284,MOD]

            // Case Email
            } else if (TRSMT_METH_TP.EMAIL_PDF.equals(pMsg.trsmtMethTpCd.getValue())) {

                requestBean.setRptTtlNm(RPT_NM_SUFIX_EMAIL + pMsg.poOrdNum.getValue() + pMsg.procStartTs.getValue());

                rptRqstPk = sendEmail(msgMap, eipBasic, requestBean);

                if (rptRqstPk == -1) {
                    // Error ML Template not set.
                    return;
                }

            // Case FAX
            } else if (TRSMT_METH_TP.FAX.equals(pMsg.trsmtMethTpCd.getValue())) {

                requestBean.setRptTtlNm(RPT_NM_SUFIX_FAX + pMsg.poOrdNum.getValue() + pMsg.procStartTs.getValue());

                rptRqstPk = sendFax(msgMap, eipBasic, requestBean);

            // Case Printer
            } else if (TRSMT_METH_TP.PRINTER.equals(pMsg.trsmtMethTpCd.getValue())) {

                requestBean.setRptTtlNm(RPT_NM_SUFIX_PRINTER + pMsg.poOrdNum.getValue() + pMsg.procStartTs.getValue());

                print(msgMap, eipBasic, requestBean);
            }

            if (rptRqstPk != 0) {
                // activate Report Processing.
                long processPk = eipBasic.activateAsyncReportJob();
                // set EIP Reprt Request PK
                ZYPEZDItemValueSetter.setValue(pMsg.eipRptRqstPk, new BigDecimal(rptRqstPk));
            }

        } catch (Exception e) {
            // START 2021/02/03 J.Evangelista [QC#58284,ADD]
            e.printStackTrace();
            // END 2021/02/03 J.Evangelista [QC#58284,ADD]
            msgMap.addXxMsgId(NPAM0053E);
            S21InfoLogOutput.printlnv(NPAM0053E, e.getMessage());
        }

    }

    /**
     * <pre>
     * Check parameter
     * </pre>
     * @param msgMap S21ApiMessageMap
     */
    private boolean checkParam(S21ApiMessageMap msgMap) {
        NPZC005001PMsg pMsg = (NPZC005001PMsg) msgMap.getPmsg();

        if (!ZYPCommonFunc.hasValue(pMsg.glblCmpyCd)) {
            addMsgId(msgMap, NPZM0001E);
            return false;
        }

        if (!ZYPCommonFunc.hasValue(pMsg.userId)) {
            addMsgId(msgMap, ZZSM4122E);
            return false;
        }

        if (!ZYPCommonFunc.hasValue(pMsg.procStartTs)) {
            addMsgId(msgMap, NPAM1251E);
            return false;
        }

        if (!ZYPCommonFunc.hasValue(pMsg.poOrdNum)) {
            addMsgId(msgMap, NPAM1250E);
            return false;
        }

        if (!ZYPCommonFunc.hasValue(pMsg.trsmtMethTpCd)) {
            addMsgId(msgMap, NPAM1252E);
            return false;
        }

        if (TRSMT_METH_TP.EMAIL_PDF.equals(pMsg.trsmtMethTpCd.getValue())) {
            if (pMsg.sendPoEmlAddrList.getValidCount() < 1) {
                addMsgId(msgMap, NPAM1253E);
                return false;
            }
        }

        if (TRSMT_METH_TP.FAX.equals(pMsg.trsmtMethTpCd.getValue()) && !ZYPCommonFunc.hasValue(pMsg.faxNum)) {
            addMsgId(msgMap, NPAM1254E);
            return false;
        }

        if (TRSMT_METH_TP.PRINTER.equals(pMsg.trsmtMethTpCd.getValue()) && !ZYPCommonFunc.hasValue(pMsg.rptDestId)) {
            addMsgId(msgMap, NPAM1359E);
            return false;
        }

        return true;
    }

    // START 2021/02/03 J.Evangelista [QC#58284,MOD]
//    private long callS21EIPPrintingServiceToCreate(S21ReportRequestBean requestBean) {
//
//        S21EIPPrintingService eipPrintingService = new S21EIPPrintingService();
//
//        return eipPrintingService.createReportByAsync(requestBean);
//    }
    private long callS21EIPPrintingServiceToCreate(S21EIPPrintingService eipBasic, S21ReportRequestBean requestBean) {

        return eipBasic.createReportByAsync(requestBean);
    }
    // END 2021/02/03 J.Evangelista [QC#58284,MOD]

    // QC#29971 Update method. Get Mail Template.
    private long sendEmail(S21ApiMessageMap msgMap, S21EIPPrintingService eipBasic, S21ReportRequestBean requestBean) {

        NPZC005001PMsg pMsg = (NPZC005001PMsg) msgMap.getPmsg();

        // Get Default From Mail Address.
        S21MailGroup groupFrom = new S21MailGroup(pMsg.glblCmpyCd.getValue(), MAIL_GROUP_ID_FROM);
        groupFrom.setMailKey1(MAIL_KEY_FROM);
        List<S21MailAddress> addrFromList = groupFrom.getMailAddress();
        if (addrFromList == null || addrFromList.isEmpty()) {
            addMsgId(msgMap, NPAM1265E);
            return -1;
        }

        // Get Mail Template.
        S21MailTemplate template = new S21MailTemplate(pMsg.glblCmpyCd.getValue(), MAIL_TEMPLATE_ID);
        if (template == null || !ZYPCommonFunc.hasValue(template.getBody())) {
            addMsgId(msgMap, NPAM1266E);
            return -1;
        } else {
            String buyerName = getBuyerName(pMsg.glblCmpyCd.getValue(), pMsg.poOrdNum.getValue());

            template.setTemplateParameter(MAIL_SUBJECT_PO_ORD_NUM, pMsg.poOrdNum.getValue());
            template.setTemplateParameter(MAIL_BODY_COMMENT, buyerName);
        }

        S21EmailOutputParameter emailParam = new S21EmailOutputParameter();

        for (int i = 0; i < pMsg.sendPoEmlAddrList.getValidCount(); i++) {
            String sendPoEmlAddr = pMsg.sendPoEmlAddrList.no(i).emlToAddr.getValue();
            if (ZYPCommonFunc.hasValue(sendPoEmlAddr)) {
                emailParam.addToAddress(sendPoEmlAddr.replace(HALF_SPACE, ""));
            }
        }

        if (TRSMT_METH_TP.EMAIL_PDF.equals(pMsg.trsmtMethTpCd.getValue())) {

            emailParam.setBranchNo(pMsg.trsmtMethTpCd.getValue());
            emailParam.setAttachementFlag(true);
            emailParam.setAttachFileName(FILE_NM_PREF + pMsg.poOrdNum.getValue() + FILE_NM_SUFF + pMsg.procStartTs.getValue() + EXT);

            if (ZYPCommonFunc.hasValue(pMsg.emlFromAddr)) {
                emailParam.setSenderAddress(pMsg.emlFromAddr.getValue());
            } else {
                //Default Mail Template
                S21MailAddress fromAddress = addrFromList.get(0);
                emailParam.setSenderAddress(fromAddress.getAddress());
            }

            if (ZYPCommonFunc.hasValue(pMsg.emlSubjTxt)) {
                emailParam.setSubject(pMsg.emlSubjTxt.getValue() + " : " + pMsg.poOrdNum.getValue());
            } else {
                //Default Mail Template
                emailParam.setSubject(template.getSubject());
            }

            if (ZYPCommonFunc.hasValue(pMsg.xxMlBodyTxt)) {
                emailParam.setBodyText(pMsg.xxMlBodyTxt.getValue());
            } else {
                //Default Mail Template
                emailParam.setBodyText(template.getBody());
            }
        }
        requestBean.setEmailOutParamBean(emailParam);

        return eipBasic.createReportByAsync(requestBean);
    }


    // QC#29971 Add method.
    private String getBuyerName(String glblCmpyCd, String poOrdNum) {

        Map<String, Object> queryParam = new HashMap<String, Object>();

        queryParam.put("GLBL_CMPY_CD", glblCmpyCd);
        queryParam.put("PO_ORD_NUM", poOrdNum);

        Map<String, String> buyerInfo = (Map<String, String>) glSsmBatchClient.queryObject("getPoBuyer", queryParam);

        if (buyerInfo == null) {
            return "";
        } else if (ZYPCommonFunc.hasValue(buyerInfo.get("FULL_PSN_NM"))) {
            return buyerInfo.get("FULL_PSN_NM");
        } else if (ZYPCommonFunc.hasValue(buyerInfo.get("BUYER_PSN_CD"))) {
            return buyerInfo.get("BUYER_PSN_CD");
        }

        return "";
    }

    private long sendFax(S21ApiMessageMap msgMap, S21EIPPrintingService eipBasic, S21ReportRequestBean requestBean) {

        NPZC005001PMsg pMsg = (NPZC005001PMsg) msgMap.getPmsg();

        // Get Default Fax Subject.
        if (!ZYPCommonFunc.hasValue(pMsg.faxSubjNm)) {
            ZYPEZDItemValueSetter.setValue(pMsg.faxSubjNm, ZYPCodeDataUtil.getVarCharConstValue(DFAULT_FAX_SUBJECT, pMsg.glblCmpyCd.getValue()));
        }

        S21FaxOutputParameter faxParam = new S21FaxOutputParameter();

        faxParam.setBranchNo(pMsg.trsmtMethTpCd.getValue());
        faxParam.setFaxSubject(pMsg.faxSubjNm.getValue() + ":" + pMsg.poOrdNum.getValue());
        faxParam.setFaxRecipientNumber(pMsg.faxNum.getValue());

        requestBean.setFaxOutParamBean(faxParam);

        return eipBasic.createReportByAsync(requestBean);
    }

    private void print(S21ApiMessageMap msgMap, S21EIPPrintingService eipBasic, S21ReportRequestBean requestBean) {

        S21EIPPrintingService service = new S21EIPPrintingService();

        byte[] pdf = service.onlineReport(requestBean);
    }

    /**
     * <pre>
     * Set message ID
     * </pre>
     * 
     * @param msgMap Message Manager
     * @param msgId String setting value for Message ID
     * @throws none
     */
    private void addMsgId(S21ApiMessageMap msgMap, String msgId) {

        msgMap.addXxMsgId(msgId);

        printDebugLog("setMsgId:" + msgId);
    }

    /**
     * <pre>
     * Print Debug Log
     * </pre>
     * 
     * @param debugMsg
     */
    private void printDebugLog(String debugMsg) {
        if (EZDDebugOutput.isDebug()) {
            EZDDebugOutput.println(CST_DEBUG_MSG_LVL, debugMsg, this);
        }
    }
}
