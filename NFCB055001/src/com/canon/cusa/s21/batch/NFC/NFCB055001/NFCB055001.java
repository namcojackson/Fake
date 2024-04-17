package com.canon.cusa.s21.batch.NFC.NFCB055001;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDMessageEnv;
import parts.dbcommon.EZDTBLAccessor;
import business.db.ECHK_NTFY_INFOTMsg;

import com.canon.cusa.s21.common.NFX.NFXC308001.NFCConst;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.common.ZYPFormatUtil;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_CASH_APPLY_STS;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;
import com.canon.cusa.s21.framework.userprofile.S21UserInfo;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;


/**
 * <pre>
 * NFCB0550:eCheck Notification
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/22/2016   CSAI            Y.Miyauchi      Create          NEW
 * 01/12/2017   Hitachi         E.Kameishi      Update          QC#16536
 * 2018/06/11   Fujitsu         Y.Matsui        Update          QC#25707
 * 2019/02/28   Fujitsu         S.Ohki          Update          QC#30584
 *</pre>
 */
public class NFCB055001 extends S21BatchMain implements NFCB055001Constant {

    /** User Profile */
    private S21UserProfileService profile;
    /** Global Company Code */
    private String glblCmpyCd;
    /** SSM Batch Client */
    private S21SsmBatchClient ssmBatchClient;
    /** Normal Record Count */
    private int normalRcCnt = 0;
    /** Total Record Count */
    private int totRcCnt = 0;
    /** Error Record Count */
    private int errRcCnt = 0;

   @Override
    protected void initRoutine() {
        S21InfoLogOutput.println("initRoutine Method Start");

        /** Initialize SSM Batch client. */
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        /** Get User Profile Service Instance */
        this.profile = S21UserProfileServiceFactory.getInstance().getService();
        /** Get Global Company Code */
        this.glblCmpyCd = this.profile.getGlobalCompanyCode();
        normalRcCnt = 0;
        totRcCnt = 0;
        errRcCnt = 0;

        S21InfoLogOutput.println("initRoutine Method End");
    }

    @Override
    protected void mainRoutine() {
        S21InfoLogOutput.println("mainRoutine Method Start");

        try {

            //Get PO Information
            /** set queryMap */
            Map<String, Object> queryMap = new HashMap<String, Object>();
            queryMap.put(DB_ITEM_NAME_J.GLBL_CMPY_CD.getKey(), this.glblCmpyCd);
            queryMap.put(DB_ITEM_NAME_J.AR_RCPT_RCV_ERR_FLG.getKey(), FLG.Y.toString());
            queryMap.put(DB_ITEM_NAME_J.RCV_FUNC_ID.getKey(), TARGET_INFTERFACE_ID);
            queryMap.put(DB_ITEM_NAME_J.AR_CASH_APPLY_STS_CD_U.getKey(), NFCConst.CST_AR_CASH_APPLY_STS_CD_UNAPPLY);
            queryMap.put(DB_ITEM_NAME_J.AR_CASH_APPLY_STS_CD_P.getKey(), NFCConst.CST_AR_CASH_APPLY_STS_CD_PARTIAL);

            /** getPOInformation */
            List<Map<String, Object>> ssmResult = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getTargetList", queryMap);

            if (ssmResult.size() <= 0) {
                // START 2017/01/12 E.Kameishi [QC#16536,MOD]
                setTermState(TERM_CD.NORMAL_END, this.normalRcCnt, this.errRcCnt, this.totRcCnt);
                //return;
                // END 2017/01/12 E.Kameishi [QC#16536,MOD]
            } else {

                totRcCnt = ssmResult.size();

                for (Map<String, Object> rcptInfo : ssmResult) {

                    String echkNtfyTpCd = (String) rcptInfo.get(DB_ITEM_NAME.ERR_STS_CD.toString());
                    List<Map<String, Object>> invInfoList = new ArrayList<Map<String, Object>>();

                    if (!ECHK_NTFY_TP_CD.INTERFACE_ERROR.getKey().equals(echkNtfyTpCd)) {
                        invInfoList = getInvoiceInformation((String) rcptInfo.get(DB_ITEM_NAME.RCPT_NUM.toString()));
                        if (isAppliedTotally(rcptInfo, invInfoList)) {
                            echkNtfyTpCd = ECHK_NTFY_TP_CD.NO_NOTIFICATION.getKey();
                        }
                    }

                    ECHK_NTFY_INFOTMsg insEchkNtfyInfoTMsg = new ECHK_NTFY_INFOTMsg();
                    ZYPEZDItemValueSetter.setValue(insEchkNtfyInfoTMsg.glblCmpyCd, glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(insEchkNtfyInfoTMsg.echkNtfyInfoRefTxt, (String) rcptInfo.get(DB_ITEM_NAME.ECHK_NTFY_INFO_REF_TXT.toString()));
                    ZYPEZDItemValueSetter.setValue(insEchkNtfyInfoTMsg.echkNtfyTpCd, echkNtfyTpCd);
                    ZYPEZDItemValueSetter.setValue(insEchkNtfyInfoTMsg.echkNtfyDt, ZYPDateUtil.getBatProcDate());
                    EZDTBLAccessor.insert(insEchkNtfyInfoTMsg);

                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(insEchkNtfyInfoTMsg.getReturnCode())) {
                        errRcCnt++;
                        setTermState(TERM_CD.ABNORMAL_END, this.normalRcCnt, this.errRcCnt, this.totRcCnt);
                        rollback();
                        throw new S21AbendException(MSG_ERROR_CD.NFCM0532E.toString(), ECHK_NTFY_INFO_LIST);
                    }

                    if (!ECHK_NTFY_TP_CD.NO_NOTIFICATION.getKey().equals(echkNtfyTpCd)) {
                        S21MailTemplate template = initializeMailTemplate();
                        fillMailTemplateParameter(template, rcptInfo, invInfoList, echkNtfyTpCd);
                        if (!sendEMail(template, (String) rcptInfo.get(DB_ITEM_NAME.CLT_PSN_CD.toString()))) {
                            S21InfoLogOutput.println("EMail Failed");
                        }
                    }

                    normalRcCnt++;
                    commit();
                }

                setTermState(TERM_CD.NORMAL_END, this.normalRcCnt, this.errRcCnt, this.totRcCnt);
           }
        } catch (S21AbendException e) {
            errRcCnt++;
            rollback();
            throw new S21AbendException(e);
        }

        S21InfoLogOutput.println("mainRoutine Method End");
    }

    @Override
    protected void termRoutine() {
    }

    /**
     * @param args parameters
     */
    public static void main(String[] args) {
        S21InfoLogOutput.println("Main Method Start");

        /** Initialize S21BatchMain */
        new NFCB055001().executeBatch(NFCB055001.class.getSimpleName());

        S21InfoLogOutput.println("Main Method End");
    }

    private boolean sendEMail(S21MailTemplate template, String strCltAnalyst) {

        /*******************************
         * Send Mail
         *******************************/

        /*******************************
         * Set "From" email address to
         * "S21_AFB@cusa.canon.com"
         *******************************/
        S21MailGroup mailGroup = new S21MailGroup(glblCmpyCd, MAIL_CONST.ML_DEFAULT_GRP_ID.getKey());
        mailGroup.setMailKey1(MAIL_CONST.ML_DEFAULT_GRP_ID_KEY_FROM.getKey());
        List<S21MailAddress> addrFromList = mailGroup.getMailAddress();
        S21MailAddress from = null;
        if (addrFromList.size() <= 0) {
            throw new S21AbendException(MSG_ERROR_CD.ZZMM0007E.toString(), new String[] {"From Address" });
        } else {
            from = addrFromList.get(0);
        }
        /*******************************
         * Get "To" email address
         *******************************/
        List<S21MailAddress> addrToList = new ArrayList<S21MailAddress>();
        if (ZYPCommonFunc.hasValue(strCltAnalyst)) {

            String cltAnalystAdd = getCollectorAnalyst_EMail(strCltAnalyst);
            if (ZYPCommonFunc.hasValue(cltAnalystAdd)) {
                S21MailAddress emlAddr = new S21MailAddress(glblCmpyCd, cltAnalystAdd);
                emlAddr.setAddress(cltAnalystAdd);
                addrToList.add(emlAddr);
            } else {
                mailGroup.setMailKey1(MAIL_CONST.ML_DEFAULT_GRP_ID_KEY_TO.getKey());
                addrToList = mailGroup.getMailAddress();
                if (addrToList.size() <= 0) {
                    throw new S21AbendException(MSG_ERROR_CD.ZZMM0007E.toString(), new String[] {"To Address" });
                }
            }
        } else {
            mailGroup.setMailKey1(MAIL_CONST.ML_DEFAULT_GRP_ID_KEY_TO.getKey());
            addrToList = mailGroup.getMailAddress();
            if (addrToList.size() <= 0) {
                throw new S21AbendException(MSG_ERROR_CD.ZZMM0007E.toString(), new String[] {"To Address" });
            }
        }

        /*******************************
         * Get "CC" email address
         *******************************/
        List<S21MailAddress> addrCcList = new ArrayList<S21MailAddress>();
        mailGroup.setMailKey1(MAIL_CONST.ML_DEFAULT_GRP_ID_KEY_CC.getKey());
        addrCcList = mailGroup.getMailAddress();

        /*******************************
         * Send mail.
         *******************************/
        S21Mail mail = new S21Mail(glblCmpyCd);
        mail.setMailTemplate(template);
        mail.setFromAddress(from);
        mail.setToAddressList(addrToList);
        if (addrCcList.size() <= 0) {
            mail.setCcAddressList(addrCcList);
        }
        mail.setSubject(template.getSubject("en"), "ISO-8859-1");
        Map<String, String> mailParamMap = new HashMap<String, String>();
        if (mailParamMap.containsKey("attachmentId")) {
            mail.setAttachmentId(Integer.parseInt((String) mailParamMap.get("attachmentId")));
        }
        // START 2019/02/28 S.Ohki [QC#30584, MOD]
//        String mailEvent = mail.sendMail();
        String mailEvent = mail.postMail();
        // END 2019/02/28 S.Ohki [QC#30584, MOD]
        if (!ZYPCommonFunc.hasValue(mailEvent)) {
            return false;
        } else {
            String mailResult = mail.getResultCd();
            if (ZYPCommonFunc.hasValue(mailResult)) {
                return false;
            }
        }

        return true;
    }

    /***
     * 
     * @param receiptNum
     * @return
     * @throws SQLException
     */
    private List<Map<String, Object>> getInvoiceInformation(String receiptNum) {

        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put(DB_ITEM_NAME_J.GLBL_CMPY_CD.getKey(), this.glblCmpyCd);
        queryMap.put(DB_ITEM_NAME_J.RCPT_NUM.getKey(), receiptNum);
        queryMap.put(DB_ITEM_NAME_J.AR_CASH_APPLY_STS_CD_U.getKey(), NFCConst.CST_AR_CASH_APPLY_STS_CD_UNAPPLY);
        queryMap.put(DB_ITEM_NAME_J.AR_CASH_APPLY_STS_CD_P.getKey(), NFCConst.CST_AR_CASH_APPLY_STS_CD_PARTIAL);
        // START 2018/06/11 Y.Matsui [QC#25707,ADD]
        queryMap.put(DB_ITEM_NAME_J.AR_CASH_APPLY_STS_CD_A.getKey(), NFCConst.CST_AR_CASH_APPLY_STS_CD_APPLY);
        // END   2018/06/11 Y.Matsui [QC#25707,ADD]
        queryMap.put(DB_ITEM_NAME_J.AR_TRX_TP_CD_INV.getKey(), NFCConst.CST_AR_TRX_TP_CD_INVOICE);
        queryMap.put(DB_ITEM_NAME_J.AR_TRX_TP_CD_CRM.getKey(), NFCConst.CST_AR_TRX_TP_CD_CREDITMEMO);

        return  (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getInvoiceInformation", queryMap);
    }

    /***
     * @param collectionAnalystCd
     * @return
     */
    private S21UserInfo getCollectorAnalyst(String collectionAnalystCd) {

        S21UserInfo userInfo = getUserProfileService().getUserInfoFor(collectionAnalystCd);
        return userInfo;
    }

    /***
     * @param collectionAnalystCd
     * @return
     */
    private String getCollectorAnalyst_EMail(String collectionAnalystCd) {

        S21UserInfo userInfo = getUserProfileService().getUserInfoFor(collectionAnalystCd);
        return userInfo.getEmailAddress();
    }

    /***
     * @param collectionAnalystCd
     * @return
     */
    private S21UserInfo getCollectorSuperviser(String collectionAnalystCd) {

        S21UserInfo userInfo = getUserProfileService().getUserInfoFor(collectionAnalystCd);
        String spvsrId = userInfo.getUserDetails().getManagerId();

        S21UserInfo userSprVsrInfo = getUserProfileService().getUserInfoFor(spvsrId);
        return userSprVsrInfo;
    }

    /***
     * @param userSprVsrInfo
     * @return
     */
    private S21UserInfo getCollectorManager(S21UserInfo userSprVsrInfo) {
        String mgrId = userSprVsrInfo.getUserDetails().getManagerId();
        S21UserInfo userMgrInfo = getUserProfileService().getUserInfoFor(mgrId);

        return userMgrInfo;
    }

    private S21MailTemplate fillMailTemplateParameter(S21MailTemplate template, Map<String, Object> rcptInfo, List<Map<String, Object>> invInfoList, String echkNtfyTpCd) {

        ECHK_NTFY_TP_CD echkNtfyTp = ECHK_NTFY_TP_CD.fromKey(echkNtfyTpCd);

        StringBuilder invNum = new StringBuilder();
        StringBuilder invAmt = new StringBuilder();

        for (int i = 0; i < invInfoList.size(); i++) {
            Map<String, Object> invInfo = invInfoList.get(i);
            invNum.append(invInfo.get(DB_ITEM_NAME.AR_TRX_NUM.toString()));
            invAmt.append(formatAmount((BigDecimal) invInfo.get(DB_ITEM_NAME.FUNC_ORIG_GRS_AMT.toString())));
            if (i < invInfoList.size() - 1) {
                invNum.append(INVOICE_SEPARATOR);
                invAmt.append(INVOICE_SEPARATOR);
            }
        }

        /*******************************
         * Set mail template parameter
         *******************************/

        /** set Key to Template */
        /** message */

        setTemplateParameter(template, MAIL_TEMPLATE_KEY.NOTIFYTP_MESSAGE_TXT, getNotificationMessage(echkNtfyTp));
        setTemplateParameter(template, MAIL_TEMPLATE_KEY.BATCH_DT, ZYPDateUtil.getBatProcDate());
        setTemplateParameter(template, MAIL_TEMPLATE_KEY.INV_NUM, invNum.toString());
        setTemplateParameter(template, MAIL_TEMPLATE_KEY.INV_AMT, invAmt.toString());
        setTemplateParameter(template, MAIL_TEMPLATE_KEY.RCPT_NUM, (String) rcptInfo.get(DB_ITEM_NAME.RCPT_CHK_NUM.toString()));
        setTemplateParameter(template, MAIL_TEMPLATE_KEY.RCPT_AMT, formatAmount((BigDecimal) rcptInfo.get(DB_ITEM_NAME.FUNC_RCPT_AMT.toString())));
        setTemplateParameter(template, MAIL_TEMPLATE_KEY.PAYER_CUST_CD, (String) rcptInfo.get(DB_ITEM_NAME.PAYER_CUST_CD.toString()));
        setTemplateParameter(template, MAIL_TEMPLATE_KEY.BILL_TO_LOC_NUM, (String) rcptInfo.get(DB_ITEM_NAME.LOC_NM.toString()));
        setTemplateParameter(template, MAIL_TEMPLATE_KEY.PORT_FOLIO_NM, (String) rcptInfo.get(DB_ITEM_NAME.CLT_PTFO_NM.toString()));
        setTemplateParameter(template, MAIL_TEMPLATE_KEY.ERR_MSG, getErrorMessage(echkNtfyTp));

        String strCltAnalyst = (String) rcptInfo.get(DB_ITEM_NAME.CLT_PSN_CD.toString());
        if (ZYPCommonFunc.hasValue(strCltAnalyst)) {
            S21UserInfo collectorAnalyst = getCollectorAnalyst(strCltAnalyst);
            template.setTemplateParameter(MAIL_TEMPLATE_KEY.CLT_ANALYST.getKey(), collectorAnalyst.getFullName());
            S21UserInfo sprVisrInfo = getCollectorSuperviser(strCltAnalyst);
            if (sprVisrInfo != null) {
                template.setTemplateParameter(MAIL_TEMPLATE_KEY.CLT_SPR_VISOR.getKey(), sprVisrInfo.getFullName());
                S21UserInfo managerInfo = getCollectorManager(sprVisrInfo);
                if (managerInfo != null) {
                    template.setTemplateParameter(MAIL_TEMPLATE_KEY.CLT_MANAGER.getKey(), managerInfo.getFullName());
                }
            }
        }
        return template;
    }

    private S21MailTemplate initializeMailTemplate() {
        S21MailTemplate template = new S21MailTemplate(glblCmpyCd, MAIL_CONST.ML_TMPL_ID.getKey());
        for (MAIL_TEMPLATE_KEY mlTemplKey : MAIL_TEMPLATE_KEY.values()) {
            setTemplateParameter(template, mlTemplKey, "");
        }
        return template;
    }

    private String formatAmount(BigDecimal amt) {
        if (ZYPCommonFunc.hasValue(amt)) {
            return ZYPFormatUtil.formatNumToSysDisp(amt.setScale(2));
        }
        return "";
    }

    private void setTemplateParameter(S21MailTemplate template, MAIL_TEMPLATE_KEY key, String value) {
        if (ZYPCommonFunc.hasValue(value)) {
            template.setTemplateParameter(key.getKey(), value);
        }
    }

    private String getNotificationMessage(ECHK_NTFY_TP_CD echkNtfyTpCd) {
        return EZDMessageEnv.getMessage(echkNtfyTpCd.getNtfyMsgId());
    }

    private String getErrorMessage(ECHK_NTFY_TP_CD echkNtfyTpCd) {
        return EZDMessageEnv.getMessage(echkNtfyTpCd.getErrMsgId());
    }

    private boolean isAppliedTotally(Map<String, Object> rcptInfo, List<Map<String, Object>> invInfoList) {
        // Receipt applied ?
        String rcptAppStsCd = (String) rcptInfo.get(DB_ITEM_NAME.AR_CASH_APPLY_STS_CD.toString());
        if (!AR_CASH_APPLY_STS.APPLIED.equals(rcptAppStsCd)) {
            return false;
        }
        // All invoices applied ?
        for (int i = 0; i < invInfoList.size(); i++) {
            Map<String, Object> invInfo = invInfoList.get(i);
            String invAppStsCd = (String) invInfo.get(DB_ITEM_NAME.AR_CASH_APPLY_STS_CD.toString());
            if (!AR_CASH_APPLY_STS.APPLIED.equals(invAppStsCd)) {
                return false;
            }
        }
        return true;
    }

}
