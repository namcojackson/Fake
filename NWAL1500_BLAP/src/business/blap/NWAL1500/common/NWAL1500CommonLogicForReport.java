/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1500.common;

import static business.blap.NWAL1500.constant.NWAL1500Constant.BIZ_ID;
import static business.blap.NWAL1500.constant.NWAL1500Constant.BRUNCH_00;
import static business.blap.NWAL1500.constant.NWAL1500Constant.BRUNCH_03;
import static business.blap.NWAL1500.constant.NWAL1500Constant.DATE_FORMAT;
import static business.blap.NWAL1500.constant.NWAL1500Constant.ML_GRP_ID_LFS;
import static business.blap.NWAL1500.constant.NWAL1500Constant.ML_GRP_ID_PPS;
import static business.blap.NWAL1500.constant.NWAL1500Constant.ML_GRP_ID_DEF;
import static business.blap.NWAL1500.constant.NWAL1500Constant.MAIL_KEY_FROM;
import static business.blap.NWAL1500.constant.NWAL1500Constant.NWAF0060;
import static business.blap.NWAL1500.constant.NWAL1500Constant.NWAF0070;
import static business.blap.NWAL1500.constant.NWAL1500Constant.NWAF0095;
import static business.blap.NWAL1500.constant.NWAL1500Constant.NWAL1500M001;
import static business.blap.NWAL1500.constant.NWAL1500Constant.NWAL1770M002;
import static business.blap.NWAL1500.constant.NWAL1500Constant.PARENTHESES_CLOSE;
import static business.blap.NWAL1500.constant.NWAL1500Constant.PDF;
import static business.blap.NWAL1500.constant.NWAL1500Constant.RTRN_BY_CUST_CD;
import static business.blap.NWAL1500.constant.NWAL1500Constant.REPORT_NM_ORD_CONF;
import static business.blap.NWAL1500.constant.NWAL1500Constant.REPORT_NM_RTRN_AUTH;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NWAM0268E;

import java.util.List;

import com.canon.cusa.s21.api.NWZ.NWZC169001.NWZC169001;
import com.canon.cusa.s21.api.NWZ.NWZC228001.NWZC228001;
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
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;
import com.canon.cusa.s21.framework.printing.common.S21EIPPrintingService;
import com.canon.cusa.s21.framework.printing.common.S21ReportRequestBean;
import com.canon.cusa.s21.framework.printing.eip.S21EmailOutputParameter;
import com.canon.cusa.s21.framework.printing.eip.S21InputParameter;
import com.canon.cusa.s21.framework.printing.eip.S21PrinterOutputParameter;

import business.blap.NWAL1500.NWAL1500CMsg;
import business.blap.NWAL1500.NWAL1500Query;
import business.blap.NWAL1500.NWAL1500SMsg;
import business.db.CPOTMsg;
import business.db.SPLY_QUOTE_RPT_OTPT_LOGTMsg;
import business.parts.NWZC169001PMsg;
import business.parts.NWZC228001PMsg;
/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/05/11   Fujitsu         T.Aoi           Create          S21_NA#21139
 * 2018/05/21   Fujitsu         T.Aoi           Update          S21_NA#22139-2
 * 2018/11/01   Fujitsu         C.Hara          Update          S21_NA#29004
 * 2019/07/06   Fujitsu         Mz.Takahashi    Update          QC#51252
 * 
 *</pre>
 */
public class NWAL1500CommonLogicForReport {

    /**
     * Call Supply Quote Report Creation API
     * @param bizMsg NWAL1500CMsg
     * @return No Error : true
     */
    public static boolean callSplyQuoteReportCratApi(NWAL1500CMsg bizMsg) {

        NWZC169001PMsg pMsg = new NWZC169001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, bizMsg.slsDt);
        ZYPEZDItemValueSetter.setValue(pMsg.bizAppId, BIZ_ID);
        ZYPEZDItemValueSetter.setValue(pMsg.otptOpCd, bizMsg.adminPsnCd);
        ZYPEZDItemValueSetter.setValue(pMsg.lineBizTpCd, bizMsg.lineBizTpCd);
        ZYPEZDItemValueSetter.setValue(pMsg.delFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(pMsg.splyQuoteRptTpCd, NWZC169001.ORDER_CONF_CD);
        ZYPEZDItemValueSetter.setValue(pMsg.cpoOrdNum, bizMsg.cpoOrdNum);

        // Call NWZC1690 Supply Quote Report Creation API
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
     * Return Authorization Report Check
     * @param bizMsg NWAL1500CMsg
     * @param glblMsg NWAL1500SMsg
     * @return No Error : true
     */
    public static boolean rtrnAuthPrintChk(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {

        if (RTRN_BY_CUST_CD.equals(bizMsg.rmaRptTpCd_RA.getValue())) {

            String rtlWh = null;

            // 2018/11/01 S21_NA#29004 Del Start
            //if (glblMsg.D.getValidCount() > 0) {
            //    rtlWh = glblMsg.D.no(0).rtlWhCd_RL.getValue();
            //}
            // 2018/11/01 S21=NA#29004 Del End

            for (int i = 0; i < glblMsg.D.getValidCount(); i++) {

                // 2018/11/01 S21_NA#29004 Add Start
                if (!ZYPCommonFunc.hasValue(rtlWh)) {
                    rtlWh = glblMsg.D.no(i).rtlWhCd_RL.getValue();
                    continue;
                }

                if (!ZYPCommonFunc.hasValue(glblMsg.D.no(i).rtlWhCd_RL.getValue())) {
                    continue;
                }
                // 2018/11/01 S21_NA#29004 Add End

                if(!S21StringUtil.isEquals(rtlWh, glblMsg.D.no(i).rtlWhCd_RL.getValue())) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Call Return Authorization Report Creation API
     * @param bizMsg NWAL1500CMsg
     * @return No Error : true
     */
    public static boolean callRtrnAuthReportCratApi(NWAL1500CMsg bizMsg) {

        NWZC228001PMsg pMsg = new NWZC228001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, bizMsg.slsDt);
        ZYPEZDItemValueSetter.setValue(pMsg.bizAppId, BIZ_ID);
        ZYPEZDItemValueSetter.setValue(pMsg.otptOpCd, bizMsg.adminPsnCd);
        ZYPEZDItemValueSetter.setValue(pMsg.lineBizTpCd, bizMsg.lineBizTpCd);
        ZYPEZDItemValueSetter.setValue(pMsg.delFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(pMsg.cpoOrdNum, bizMsg.cpoOrdNum);
        ZYPEZDItemValueSetter.setValue(pMsg.rmaRptTpCd, bizMsg.rmaRptTpCd_RA);
        ZYPEZDItemValueSetter.setValue(pMsg.rtrnAuthCmntTxt, bizMsg.rtrnAuthCmntTxt_RA);

        // Call NWZC2280 Supply Quote Report Creation API
        new NWZC228001().execute(pMsg, ONBATCH_TYPE.ONLINE);

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
     * @param bizMsg NWAL1500CMsg
     * @param splyQuoteRptTpCd
     */
    public static void executeEipProcessForSendMail(NWAL1500CMsg bizMsg, String splyQuoteRptTpCd) {

        // From Address
        String fromAddress = getFromAddress(bizMsg, splyQuoteRptTpCd);
        if (!ZYPCommonFunc.hasValue(fromAddress)) {
            bizMsg.setMessageInfo(NWAM0268E);
            return;
        }

        // Report ID
        String reportId = getReportId(bizMsg, splyQuoteRptTpCd);

        // Mail Template
        S21MailTemplate template = getTemplate(bizMsg, splyQuoteRptTpCd);
        if (template == null) {
            bizMsg.setMessageInfo(NWAM0268E);
            return;
        }

        S21ReportRequestBean request = new S21ReportRequestBean(reportId);
        request.setRptArcFlg(true);

        S21InputParameter inputParam = request.getInputParamBeanInstance();
        if (SPLY_QUOTE_RPT_TP.ORDER_CONF.equals(splyQuoteRptTpCd)) {
            inputParam.addReportParameter("GLBL_CMPY_CD", bizMsg.glblCmpyCd.getValue());
            inputParam.addReportParameter("INTL_LANG_VAL_COL_NM", inputParam.getSystemDefaultLanguage());
            inputParam.addReportParameter("BIZ_APP_ID", BIZ_ID);
            inputParam.addReportParameter("OTPT_OP_CD", bizMsg.adminPsnCd.getValue());
            inputParam.addReportParameter("LINE_BIZ_TP_CD", bizMsg.lineBizTpCd.getValue());
            inputParam.addReportParameter("SPLY_QUOTE_RPT_TP_CD", NWZC169001.ORDER_CONF_CD);
            inputParam.addReportParameter("TRX_HDR_NUM", bizMsg.cpoOrdNum.getValue());
        } else {
            inputParam.addReportParameter("GLBL_CMPY_CD", bizMsg.glblCmpyCd.getValue());
            inputParam.addReportParameter("INTL_LANG_VAL_COL_NM", inputParam.getSystemDefaultLanguage());
            inputParam.addReportParameter("BIZ_APP_ID", BIZ_ID);
            inputParam.addReportParameter("TRX_HDR_NUM", bizMsg.cpoOrdNum.getValue());
            inputParam.addReportParameter("OTPT_OP_CD", bizMsg.adminPsnCd.getValue()); //2018/05/21 QC#22139-2
        }

        request.setInputParamBean(inputParam);

        S21EmailOutputParameter outputParam = request.getEmailOutParamInstance();
        outputParam.setBranchNo(String.valueOf(BRUNCH_03));
        outputParam.setAttachementFlag(true);
        for (int i = 0; i < bizMsg.Q.getValidCount(); i++) {
            outputParam.addToAddress(bizMsg.Q.no(i).ctacPsnEmlAddr_QL.getValue());
        }
        outputParam.setSubject(template.getSubject());
        outputParam.setBodyText(template.getBody());
        outputParam.setAttachFileName(getReportFileName(bizMsg, splyQuoteRptTpCd, true));
        outputParam.setSenderAddress(fromAddress);
        request.setEmailOutParamBean(outputParam);

        // Create Report
        S21EIPPrintingService service = new S21EIPPrintingService();
        service.createReportByAsync(request);
        service.activateAsyncReportJob();
    }

    /**
     * Execute EIP Process For Send Mail
     * @param bizMsg NWAL1500CMsg
     * @param splyQuoteRptTpCd String
     * @return Report Data
     */
    public static byte[] executeEipProcessForPrint(NWAL1500CMsg bizMsg, String splyQuoteRptTpCd) {

        S21ReportRequestBean request = new S21ReportRequestBean(getReportId(bizMsg, splyQuoteRptTpCd));
        request.setRptArcFlg(true);

        S21InputParameter inputParam = request.getInputParamBeanInstance();
        if (SPLY_QUOTE_RPT_TP.ORDER_CONF.equals(splyQuoteRptTpCd)) {
            inputParam.addReportParameter("GLBL_CMPY_CD", bizMsg.glblCmpyCd.getValue());
            inputParam.addReportParameter("INTL_LANG_VAL_COL_NM", inputParam.getSystemDefaultLanguage());
            inputParam.addReportParameter("BIZ_APP_ID", BIZ_ID);
            inputParam.addReportParameter("OTPT_OP_CD", bizMsg.adminPsnCd.getValue());
            inputParam.addReportParameter("LINE_BIZ_TP_CD", bizMsg.lineBizTpCd.getValue());
            inputParam.addReportParameter("SPLY_QUOTE_RPT_TP_CD", NWZC169001.ORDER_CONF_CD);
            inputParam.addReportParameter("TRX_HDR_NUM", bizMsg.cpoOrdNum.getValue());
        } else {
            inputParam.addReportParameter("GLBL_CMPY_CD", bizMsg.glblCmpyCd.getValue());
            inputParam.addReportParameter("INTL_LANG_VAL_COL_NM", inputParam.getSystemDefaultLanguage());
            inputParam.addReportParameter("BIZ_APP_ID", BIZ_ID);
            inputParam.addReportParameter("TRX_HDR_NUM", bizMsg.cpoOrdNum.getValue());
            inputParam.addReportParameter("OTPT_OP_CD", bizMsg.adminPsnCd.getValue()); // 2018/05/21 QC#22139-2
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
     * @param splyQuoteRptTpCd String
     * @return From Address
     */
    private static String getFromAddress(NWAL1500CMsg bizMsg, String splyQuoteRptTpCd) {

        // 2019/07/26 QC#51252 Mod Start
        S21MailGroup groupFrom = null;

        if (LINE_BIZ_TP.LFS.equals(bizMsg.lineBizTpCd.getValue())){
            groupFrom = new S21MailGroup(bizMsg.glblCmpyCd.getValue(), ML_GRP_ID_LFS);
        } else {
            groupFrom = new S21MailGroup(bizMsg.glblCmpyCd.getValue(), ML_GRP_ID_PPS);
        }

        groupFrom.setMailKey1(MAIL_KEY_FROM);
        groupFrom.setMailKey2(bizMsg.dsOrdCatgCd.getValue());
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

        //S21MailGroup groupFrom = null;
        //
        //if (SPLY_QUOTE_RPT_TP.ORDER_CONF.equals(splyQuoteRptTpCd)) {
        //    groupFrom = new S21MailGroup(bizMsg.glblCmpyCd.getValue(), MAIL_GROUP_ID_ORD_CONF);
        //} else {
        //    groupFrom = new S21MailGroup(bizMsg.glblCmpyCd.getValue(), MAIL_GROUP_ID_RTRN_AUTH);
        //}
        //
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
     * @param bizMsg NWAL1500CMsg
     * @param splyQuoteRptTpCd String
     * @return Report ID
     */
    public static String getReportId(NWAL1500CMsg bizMsg, String splyQuoteRptTpCd) {

        if (SPLY_QUOTE_RPT_TP.ORDER_CONF.equals(splyQuoteRptTpCd)) {
            if (LINE_BIZ_TP.LFS.equals(bizMsg.lineBizTpCd.getValue())) {

                return NWAF0060;
            }

            return NWAF0070;
        } else {

            return NWAF0095;
        }
    }

    /**
     * Get Mail Template
     * @param bizMsg NWAL1500CMsg
     * @param splyQuoteRptTpCd
     * @return S21MailTemplate
     */
    private static S21MailTemplate getTemplate(NWAL1500CMsg bizMsg, String splyQuoteRptTpCd) {

        String templateId = null;

        if (SPLY_QUOTE_RPT_TP.ORDER_CONF.equals(splyQuoteRptTpCd)) {

            templateId = NWAL1770M002;

        } else {

            templateId = NWAL1500M001;
        }

        // Create template
        S21MailTemplate template = new S21MailTemplate(bizMsg.glblCmpyCd.getValue(), templateId);
        if (template == null) {
            return null;
        }

        // Set Parameter
        template.setTemplateParameter("cpoOrdNum", bizMsg.cpoOrdNum.getValue());
        template.setTemplateParameter("dsAcctNum", bizMsg.sellToCustCd.getValue());
        template.setTemplateParameter("dsAcctNm", bizMsg.soldToCustAcctNm.getValue());

        return template;
    }

    /**
     * Get Report File Name
     * @param bizMsg NWAL1500CMsg
     * @param splyQuoteRptTpCd String
     * @param needExtenstion Need Extension
     * @return Attach File Name
     */
    public static String getReportFileName(NWAL1500CMsg bizMsg, String splyQuoteRptTpCd, boolean needExtension) {

        String reportFileName = null;

        if (SPLY_QUOTE_RPT_TP.ORDER_CONF.equals(splyQuoteRptTpCd)) {
            reportFileName = REPORT_NM_ORD_CONF + bizMsg.cpoOrdNum.getValue() + PARENTHESES_CLOSE;
        } else {
            reportFileName = REPORT_NM_RTRN_AUTH + bizMsg.cpoOrdNum.getValue() + PARENTHESES_CLOSE;
        }

        if (needExtension) {
            return reportFileName + PDF;
        }

        return reportFileName;
    }

    /**
     * Partial Update CPO
     * @param bizMsg NWAL1500CMsg
     */
    public static boolean partialUpdateCpo(NWAL1500CMsg bizMsg) {

        String[] condColName;
        String[] updColName;

        CPOTMsg cpoCondTMsg = new CPOTMsg();
        ZYPEZDItemValueSetter.setValue(cpoCondTMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(cpoCondTMsg.cpoOrdNum, bizMsg.cpoOrdNum);
        condColName = new String[]  {"glblCmpyCd", "cpoOrdNum" };

        CPOTMsg cpoUpdTMsg = (CPOTMsg) S21FastTBLAccessor.findByKeyForUpdate(cpoCondTMsg);

        if (cpoUpdTMsg == null) {
            return false;
        }

        ZYPEZDItemValueSetter.setValue(cpoUpdTMsg.rmaRptTpCd, bizMsg.rmaRptTpCd_RA.getValue());
        if (ZYPCommonFunc.hasValue(bizMsg.rtrnAuthCmntTxt_RA)) {
            ZYPEZDItemValueSetter.setValue(cpoUpdTMsg.rtrnAuthCmntTxt, bizMsg.rtrnAuthCmntTxt_RA.getValue());
        } else {
            cpoUpdTMsg.rtrnAuthCmntTxt.clear();
        }
        updColName = new String[] {"rmaRptTpCd", "rtrnAuthCmntTxt" };

        int result = S21FastTBLAccessor.updateByPartialValue(cpoCondTMsg, condColName, cpoUpdTMsg, updColName);

        if (result != 0) {
            ZYPEZDItemValueSetter.setValue(bizMsg.ezUpTime, cpoCondTMsg.ezUpTime);
            ZYPEZDItemValueSetter.setValue(bizMsg.ezUpTimeZone, cpoCondTMsg.ezUpTimeZone);
        }

        return true;
    }

    /**
     * Insert Report Output Log
     * @param bizMsg NWAL1500CMsg
     * @param splyQuoteRptTpCd String
     * @return No Error : true
     */
    public static boolean insertReportOutputLog(NWAL1500CMsg bizMsg, String splyQuoteRptTpCd) {

        String splyQuoteNum = NWAL1500Query.getInstance().getSplyQuoteNum(bizMsg.glblCmpyCd.getValue(), bizMsg.cpoOrdNum.getValue());

        SPLY_QUOTE_RPT_OTPT_LOGTMsg rptOtptLogTMsg = new SPLY_QUOTE_RPT_OTPT_LOGTMsg();

        ZYPEZDItemValueSetter.setValue(rptOtptLogTMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(rptOtptLogTMsg.splyQuoteRptOtptLogPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SPLY_QUOTE_RPT_OTPT_LOG_SQ));
        if (SPLY_QUOTE_RPT_TP.ORDER_CONF.equals(splyQuoteRptTpCd)) {
            ZYPEZDItemValueSetter.setValue(rptOtptLogTMsg.splyQuoteRptTpCd, SPLY_QUOTE_RPT_TP.ORDER_CONF);
        } else {
            ZYPEZDItemValueSetter.setValue(rptOtptLogTMsg.splyQuoteRptTpCd, SPLY_QUOTE_RPT_TP.RETURN_AUTH);
        }
        if (ZYPCommonFunc.hasValue(splyQuoteNum)) {
            ZYPEZDItemValueSetter.setValue(rptOtptLogTMsg.splyQuoteNum, splyQuoteNum);
        }
        ZYPEZDItemValueSetter.setValue(rptOtptLogTMsg.splyQuoteRptOtptUsrId, bizMsg.adminPsnCd);
        ZYPEZDItemValueSetter.setValue(rptOtptLogTMsg.splyQuoteRptOtptTs, ZYPDateUtil.getCurrentSystemTime(DATE_FORMAT));

        S21FastTBLAccessor.insert(rptOtptLogTMsg);

        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rptOtptLogTMsg.getReturnCode())) {
            bizMsg.setMessageInfo("XXX");
            return false;
        }

        return true;
    }
}
