/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1770.common;

import static business.blap.NWAL1770.constant.NWAL1770Constant.BIZ_ID;
import static business.blap.NWAL1770.constant.NWAL1770Constant.BRUNCH_00;
import static business.blap.NWAL1770.constant.NWAL1770Constant.BRUNCH_03;
import static business.blap.NWAL1770.constant.NWAL1770Constant.DATE_FORMAT;
import static business.blap.NWAL1770.constant.NWAL1770Constant.HYPHEN;
import static business.blap.NWAL1770.constant.NWAL1770Constant.ML_GRP_ID_DEF;
import static business.blap.NWAL1770.constant.NWAL1770Constant.ML_GRP_ID_LFS;
import static business.blap.NWAL1770.constant.NWAL1770Constant.ML_GRP_ID_PPS;
import static business.blap.NWAL1770.constant.NWAL1770Constant.MAIL_KEY_FROM;
import static business.blap.NWAL1770.constant.NWAL1770Constant.NWAF0060;
import static business.blap.NWAL1770.constant.NWAL1770Constant.NWAF0065;
import static business.blap.NWAL1770.constant.NWAL1770Constant.NWAF0070;
import static business.blap.NWAL1770.constant.NWAL1770Constant.NWAF0075;
import static business.blap.NWAL1770.constant.NWAL1770Constant.NWAF1040;
import static business.blap.NWAL1770.constant.NWAL1770Constant.NWAL1770M001;
import static business.blap.NWAL1770.constant.NWAL1770Constant.NWAL1770M002;
import static business.blap.NWAL1770.constant.NWAL1770Constant.NWAL1770M003;
import static business.blap.NWAL1770.constant.NWAL1770Constant.PARENTHESES_CLOSE;
import static business.blap.NWAL1770.constant.NWAL1770Constant.PDF;
import static business.blap.NWAL1770.constant.NWAL1770Constant.REPORT_NM_ORD_CONF;
import static business.blap.NWAL1770.constant.NWAL1770Constant.REPORT_NM_QUOTE;
import static business.blap.NWAL1770.constant.NWAL1770Constant.REPORT_NM_SPLY_TRK;
import static business.blap.NWAL1770.constant.NWAL1770Constant.SPACE;
import static business.blap.NWAL1770.constant.NWAL1770MsgConstant.NWAM0268E;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import business.blap.NWAL1770.NWAL1770CMsg;
import business.blap.NWAL1770.NWAL1770Query;
import business.db.SPLY_QUOTE_RPT_OTPT_LOGTMsg;
import business.parts.NWZC168001PMsg;
import business.parts.NWZC169001PMsg;

import com.canon.cusa.s21.api.NWZ.NWZC168001.NWZC168001;
import com.canon.cusa.s21.api.NWZ.NWZC169001.NWZC169001;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LINE_BIZ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SPLY_QUOTE_RPT_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;
import com.canon.cusa.s21.framework.printing.common.S21EIPPrintingService;
import com.canon.cusa.s21.framework.printing.common.S21ReportRequestBean;
import com.canon.cusa.s21.framework.printing.eip.S21EmailOutputParameter;
import com.canon.cusa.s21.framework.printing.eip.S21InputParameter;
import com.canon.cusa.s21.framework.printing.eip.S21PrinterOutputParameter;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/08   Fujitsu         T.Yoshida       Create          N/A
 * </pre>
 */
public class NWAL1770CommonLogicForReport {

    /**
     * Call Supply Quote Report Creation API
     * @param bizMsg NWAL1770CMsg
     * @return No Error : true
     */
    public static boolean callSplyQuoteReportCratApi(NWAL1770CMsg bizMsg) {

        NWZC169001PMsg pMsg = new NWZC169001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, bizMsg.slsDt);
        ZYPEZDItemValueSetter.setValue(pMsg.bizAppId, BIZ_ID);
        ZYPEZDItemValueSetter.setValue(pMsg.otptOpCd, bizMsg.adminPsnCd);
        ZYPEZDItemValueSetter.setValue(pMsg.lineBizTpCd, bizMsg.lineBizTpCd);
        ZYPEZDItemValueSetter.setValue(pMsg.delFlg, ZYPConstant.FLG_ON_Y);

        if (ZYPCommonFunc.hasValue(bizMsg.cpoOrdNum)) {
            ZYPEZDItemValueSetter.setValue(pMsg.splyQuoteRptTpCd, NWZC169001.ORDER_CONF_CD);
            ZYPEZDItemValueSetter.setValue(pMsg.cpoOrdNum, bizMsg.cpoOrdNum);
        } else {
            ZYPEZDItemValueSetter.setValue(pMsg.splyQuoteRptTpCd, NWZC169001.QUOTE_CD);
            ZYPEZDItemValueSetter.setValue(pMsg.splyQuoteNum, bizMsg.splyQuoteNum);
        }

        // Call NWZC1690 Supply Tracking Report Creation API
        new NWZC169001().execute(pMsg, ONBATCH_TYPE.ONLINE);

        if (S21ApiUtil.isXxMsgId(pMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
            for (int i = 0; i < msgList.size(); i++) {
                S21ApiMessage msg = msgList.get(i);
                bizMsg.setMessageInfo(msg.getXxMsgid());
                return false;
            }
        }

        return true;
    }

    /**
     * Call Supply Tracking Report Creation API
     * @param bizMsg NWAL1770CMsg
     * @return No Error : true
     */
    public static boolean callSplyTrackReportCratApi(NWAL1770CMsg bizMsg) {

        NWZC168001PMsg pMsg = new NWZC168001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, bizMsg.slsDt);
        ZYPEZDItemValueSetter.setValue(pMsg.bizAppId, BIZ_ID);
        ZYPEZDItemValueSetter.setValue(pMsg.otptOpCd, bizMsg.adminPsnCd);
        ZYPEZDItemValueSetter.setValue(pMsg.sellToCustCd, bizMsg.sellToCustCd);
        ZYPEZDItemValueSetter.setValue(pMsg.cpoOrdNum, bizMsg.cpoOrdNum);
        ZYPEZDItemValueSetter.setValue(pMsg.delFlg, ZYPConstant.FLG_ON_Y);

        // Call NWZC1680 Supply Tracking Report Creation API
        new NWZC168001().execute(pMsg, ONBATCH_TYPE.ONLINE);

        if (S21ApiUtil.isXxMsgId(pMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
            for (int i = 0; i < msgList.size(); i++) {
                S21ApiMessage msg = msgList.get(i);
                bizMsg.setMessageInfo(msg.getXxMsgid());
                return false;
            }
        }

        return true;
    }

    /**
     * Execute EIP Process For Send Mail
     * @param bizMsg NWAL1770CMsg
     */
    public static void executeEipProcessForSendMail(NWAL1770CMsg bizMsg) {

        // From Address
        String fromAddress = getFromAddress(bizMsg);
        if (!ZYPCommonFunc.hasValue(fromAddress)) {
            bizMsg.setMessageInfo(NWAM0268E);
            return;
        }

        // Report ID
        String reportId = getReportId(bizMsg);

        // Mail Template
        S21MailTemplate template = getTemplate(bizMsg, reportId);
        if (template == null) {
            bizMsg.setMessageInfo(NWAM0268E);
            return;
        }

        S21ReportRequestBean request = new S21ReportRequestBean(reportId);
        request.setRptArcFlg(true);

        S21InputParameter inputParam = request.getInputParamBeanInstance();
        inputParam.addReportParameter("GLBL_CMPY_CD", bizMsg.glblCmpyCd.getValue());
        inputParam.addReportParameter("INTL_LANG_VAL_COL_NM", inputParam.getSystemDefaultLanguage());
        inputParam.addReportParameter("BIZ_APP_ID", BIZ_ID);
        inputParam.addReportParameter("OTPT_OP_CD", bizMsg.adminPsnCd.getValue());

        if ("OpenWin_Confirmation".equals(bizMsg.xxScrEventNm.getValue())) {
            inputParam.addReportParameter("LINE_BIZ_TP_CD", bizMsg.lineBizTpCd.getValue());
            if (ZYPCommonFunc.hasValue(bizMsg.cpoOrdNum)) {
                inputParam.addReportParameter("SPLY_QUOTE_RPT_TP_CD", NWZC169001.ORDER_CONF_CD);
                inputParam.addReportParameter("TRX_HDR_NUM", bizMsg.cpoOrdNum.getValue());
            } else {
                inputParam.addReportParameter("SPLY_QUOTE_RPT_TP_CD", NWZC169001.QUOTE_CD);
                inputParam.addReportParameter("TRX_HDR_NUM", bizMsg.splyQuoteNum.getValue());
            }
        } else {
            inputParam.addReportParameter("CPO_ORD_NUM", bizMsg.cpoOrdNum.getValue());
        }
        request.setInputParamBean(inputParam);

        S21EmailOutputParameter outputParam = request.getEmailOutParamInstance();
        outputParam.setBranchNo(String.valueOf(BRUNCH_03));
        outputParam.setAttachementFlag(true);
        for (int i = 0; i < bizMsg.W.getValidCount(); i++) {
            outputParam.addToAddress(bizMsg.W.no(i).ctacPsnEmlAddr_W.getValue());
        }
        outputParam.setSubject(template.getSubject());
        outputParam.setBodyText(template.getBody());
        outputParam.setAttachFileName(getReportFileName(bizMsg, true));
        outputParam.setSenderAddress(fromAddress);
        request.setEmailOutParamBean(outputParam);

        // Create Report
        S21EIPPrintingService service = new S21EIPPrintingService();
        service.createReportByAsync(request);
        service.activateAsyncReportJob();
    }

    /**
     * Execute EIP Process For Send Mail
     * @param bizMsg NWAL1770CMsg
     * @return Report Data
     */
    public static byte[] executeEipProcessForPrint(NWAL1770CMsg bizMsg) {

        S21ReportRequestBean request = new S21ReportRequestBean(getReportId(bizMsg));
        request.setRptArcFlg(true);

        S21InputParameter inputParam = request.getInputParamBeanInstance();
        inputParam.addReportParameter("GLBL_CMPY_CD", bizMsg.glblCmpyCd.getValue());
        inputParam.addReportParameter("INTL_LANG_VAL_COL_NM", inputParam.getSystemDefaultLanguage());
        inputParam.addReportParameter("BIZ_APP_ID", BIZ_ID);
        inputParam.addReportParameter("OTPT_OP_CD", bizMsg.adminPsnCd.getValue());
        inputParam.addReportParameter("LINE_BIZ_TP_CD", bizMsg.lineBizTpCd.getValue());
        if (ZYPCommonFunc.hasValue(bizMsg.cpoOrdNum)) {
            inputParam.addReportParameter("SPLY_QUOTE_RPT_TP_CD", NWZC169001.ORDER_CONF_CD);
            inputParam.addReportParameter("TRX_HDR_NUM", bizMsg.cpoOrdNum.getValue());
        } else {
            inputParam.addReportParameter("SPLY_QUOTE_RPT_TP_CD", NWZC169001.QUOTE_CD);
            inputParam.addReportParameter("TRX_HDR_NUM", bizMsg.splyQuoteNum.getValue());
        }
        request.setInputParamBean(inputParam);

        S21PrinterOutputParameter outputParam = request.getPrintOutParamBeanInstance();
        outputParam.setBranchNo(BRUNCH_00);

        // Create Report
        S21EIPPrintingService service = new S21EIPPrintingService();
        return service.onlineReport(request);
    }

    /**
     * Get From Address
     * @param bizMsg NWAL1770CMsg
     * @return From Address
     */
    private static String getFromAddress(NWAL1770CMsg bizMsg) {

        // 2019/07/26 QC#51252 Mod Start
        S21MailGroup groupFrom = null;

        if (LINE_BIZ_TP.LFS.equals(bizMsg.lineBizTpCd.getValue())){
            groupFrom = new S21MailGroup(bizMsg.glblCmpyCd.getValue(), ML_GRP_ID_LFS);
        } else {
            groupFrom = new S21MailGroup(bizMsg.glblCmpyCd.getValue(), ML_GRP_ID_PPS);
        }

        groupFrom.setMailKey1(MAIL_KEY_FROM);
        groupFrom.setMailKey2(bizMsg.splyQuoteCatgCd.getValue());
        groupFrom.setMailKey3(bizMsg.dsOrdTpCd.getValue());
        List<S21MailAddress> addrFromList = groupFrom.getMailAddress();

        if (addrFromList != null && !addrFromList.isEmpty()) {
            return addrFromList.get(0).getAddress();
        }

        groupFrom = new S21MailGroup(bizMsg.glblCmpyCd.getValue(), ML_GRP_ID_DEF);
        groupFrom.setMailKey1(MAIL_KEY_FROM);
        addrFromList = groupFrom.getMailAddress();

        if (addrFromList != null && !addrFromList.isEmpty()) {
            return addrFromList.get(0).getAddress();
        }

        //S21MailGroup groupFrom = new S21MailGroup(bizMsg.glblCmpyCd.getValue(), MAIL_GROUP_ID);
        //groupFrom.setMailKey1(MAIL_KEY_FROM);
        //List<S21MailAddress> addrFromList = groupFrom.getMailAddress();
        //
        //if (addrFromList != null && !addrFromList.isEmpty()) {
        //    return addrFromList.get(0).getAddress();
        //}
        // 2019/07/26 QC#51252 Mod End

        return null;
    }

    /**
     * Get Report ID
     * @param bizMsg NWAL1770CMsg
     * @return Report ID
     */
    public static String getReportId(NWAL1770CMsg bizMsg) {

        if ("OpenWin_Tracking".equals(bizMsg.xxScrEventNm.getValue())) {
            return NWAF1040;
        }

        if (ZYPCommonFunc.hasValue(bizMsg.cpoOrdNum)) {
            if (LINE_BIZ_TP.LFS.equals(bizMsg.lineBizTpCd.getValue())) {
                return NWAF0060;
            } else {
                return NWAF0070;
            }
        }

        if (LINE_BIZ_TP.LFS.equals(bizMsg.lineBizTpCd.getValue())) {
            return NWAF0065;
        }

        return NWAF0075;
    }

    /**
     * Get Mail Template
     * @param bizMsg NWAL1770CMsg
     * @param reportId Report ID
     * @return S21MailTemplate
     */
    private static S21MailTemplate getTemplate(NWAL1770CMsg bizMsg, String reportId) {

        String templateId = null;
        if (NWAF0065.equals(reportId) || NWAF0075.equals(reportId)) {
            templateId = NWAL1770M001;
        } else if (NWAF0060.equals(reportId) || NWAF0070.equals(reportId)) {
            templateId = NWAL1770M002;
        } else {
            templateId = NWAL1770M003;
        }

        // Create template
        S21MailTemplate template = new S21MailTemplate(bizMsg.glblCmpyCd.getValue(), templateId);
        if (template == null) {
            return null;
        }

        // Set Parameter
        if (NWAF0065.equals(reportId) || NWAF0075.equals(reportId)) {
            template.setTemplateParameter("splyQuoteNum", bizMsg.splyQuoteNum.getValue());
        } else {
            template.setTemplateParameter("cpoOrdNum", bizMsg.cpoOrdNum.getValue());
        }
        template.setTemplateParameter("dsAcctNum", bizMsg.sellToCustCd.getValue());
        template.setTemplateParameter("dsAcctNm", bizMsg.soldToCustAcctNm.getValue());

        return template;
    }

    /**
     * Get Report File Name
     * @param bizMsg NWAL1770CMsg
     * @param needExtenstion Need Extenstion
     * @return Attach File Name
     */
    public static String getReportFileName(NWAL1770CMsg bizMsg, boolean needExtenstion) {

        String reportFileName = null;

        if ("OpenWin_Tracking".equals(bizMsg.xxScrEventNm.getValue())) {
            reportFileName = REPORT_NM_SPLY_TRK + bizMsg.cpoOrdNum.getValue() + PARENTHESES_CLOSE;
        } else if (ZYPCommonFunc.hasValue(bizMsg.cpoOrdNum)) {
            reportFileName = REPORT_NM_ORD_CONF + bizMsg.cpoOrdNum.getValue() + PARENTHESES_CLOSE;
        } else {
            reportFileName = REPORT_NM_QUOTE + bizMsg.splyQuoteNum.getValue() + PARENTHESES_CLOSE;
        }

        if (needExtenstion) {
            return reportFileName + PDF;
        }

        return reportFileName;
    }

    /**
     * Insert Report Output Log
     * @param bizMsg NWAL1770CMsg
     * @return No Error : true
     */
    public static boolean insertReportOutputLog(NWAL1770CMsg bizMsg) {

        SPLY_QUOTE_RPT_OTPT_LOGTMsg rptOtptLogTMsg = new SPLY_QUOTE_RPT_OTPT_LOGTMsg();

        ZYPEZDItemValueSetter.setValue(rptOtptLogTMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(rptOtptLogTMsg.splyQuoteRptOtptLogPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SPLY_QUOTE_RPT_OTPT_LOG_SQ));
        if (ZYPCommonFunc.hasValue(bizMsg.cpoOrdNum)) {
            ZYPEZDItemValueSetter.setValue(rptOtptLogTMsg.splyQuoteRptTpCd, SPLY_QUOTE_RPT_TP.ORDER_CONF);
        } else {
            ZYPEZDItemValueSetter.setValue(rptOtptLogTMsg.splyQuoteRptTpCd, SPLY_QUOTE_RPT_TP.QUOTE);
        }
        ZYPEZDItemValueSetter.setValue(rptOtptLogTMsg.splyQuoteNum, bizMsg.splyQuoteNum);
        ZYPEZDItemValueSetter.setValue(rptOtptLogTMsg.splyQuoteRptOtptUsrId, bizMsg.adminPsnCd);
        ZYPEZDItemValueSetter.setValue(rptOtptLogTMsg.splyQuoteRptOtptTs, ZYPDateUtil.getCurrentSystemTime(DATE_FORMAT));

        S21FastTBLAccessor.insert(rptOtptLogTMsg);

        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rptOtptLogTMsg.getReturnCode())) {
            bizMsg.setMessageInfo("XXX");
            return false;
        }

        return true;
    }

    /**
     * Create Confirmation Output Pulldown
     * @param bizMsg NWAL1770CMsg
     */
    @SuppressWarnings("unchecked")
    public static void createConfOutputPulldown(NWAL1770CMsg bizMsg) {

        S21SsmEZDResult ssmResult = NWAL1770Query.getInstance().getReportOutputLogList(bizMsg);

        if (ssmResult.isCodeNormal()) {
            List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
            for (int i = 0; i < resultList.size(); i++) {
                Map<String, Object> result = (Map<String, Object>) resultList.get(i);
                ZYPEZDItemValueSetter.setValue(bizMsg.splyQuoteRptOtptLogPk_PL.no(i), (BigDecimal) result.get("SPLY_QUOTE_RPT_OTPT_LOG_PK"));
                ZYPEZDItemValueSetter.setValue(bizMsg.xxDtlNm_PL.no(i), getConfOutputName(result));
            }
        }
    }

    /**
     * Get Confirmation Output Name
     * @param resultMap Map<String, Object>
     * @return Confirmation Output Name
     */
    private static String getConfOutputName(Map<String, Object> resultMap) {

        String rptOtptTs = (String) resultMap.get("SPLY_QUOTE_RPT_OTPT_TS");
        String rptTpDescTxt = (String) resultMap.get("SPLY_QUOTE_RPT_TP_DESC_TXT");

        StringBuilder confOutputName = new StringBuilder(rptOtptTs);
        confOutputName.append(SPACE);
        confOutputName.append(HYPHEN);
        confOutputName.append(SPACE);
        confOutputName.append(rptTpDescTxt);

        return confOutputName.toString();
    }
}
