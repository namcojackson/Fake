/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.common.NSX.NSXC001001;

import java.util.ArrayList;
import java.util.List;

import parts.common.EZDMessageInfo;
import parts.dbcommon.EZDTBLAccessor;
import business.db.MBL_TECH_MSTRTMsg;
import business.db.TECH_MSTRTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;

/**
 *<pre>
 * Send Mail To Dispatcher
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/12/19   Fujitsu         T.Yoshida       Create          N/A
 *</pre>
 */
public class NSXC001001SendMailToDispatcher {

    /** Error Message List */
    private static List<String> errorMsgList = null;

    /** Input parameter "Global Company Code" is a mandatory field. */
    private static final String NSXM0003E = "NSXM0003E";

    /** Input parameter "InterfaceId" is a mandatory field. */
    private static final String NSXM0004E = "NSXM0004E";

    /** Input parameter "InterfaceName" is a mandatory field. */
    private static final String NSXM0005E = "NSXM0005E";

    /** Input parameter "Message ID" is a mandatory field. */
    private static final String NSXM0006E = "NSXM0006E";

    /** Input parameter "Technician Code" is a mandatory field. */
    private static final String NSXM0007E = "NSXM0007E";

    /** Could not get the "From Address". */
    private static final String NSXM0008E = "NSXM0008E";

    /** Could not get the "To Address".. */
    private static final String NSXM0009E = "NSXM0009E";

    /** Could not get the "Mail Template". */
    private static final String NSXM0010E = "NSXM0010E";

    /** An error has occurred in the "post mail Process". */
    private static final String NSXM0011E = "NSXM0011E";

    /** line feed code */
    private static final String LINE_FEED_CODE = "\r\n";

    /** Mail Group ID */
    private static final String FROM0003 = "FROM0003";

    /** Mail Key for NSZ */
    private static final String MAIL_KEY_NSZ = "NSZ";

    /** Mail Template ID */
    private static final String MAIL_TEMPLATE_ID = "NSXC0010M001";

    /**
     * Post Mail To Dispatcher
     * @param mailInfoBean MailInfoBean
     * @return Error Message ID List
     */
    public static List<String> postMail(MailInfoBean mailInfoBean) {

        errorMsgList = new ArrayList<String>();

        chkInputParam(mailInfoBean);
        if (!errorMsgList.isEmpty()) {
            return errorMsgList;
        }

        // get From Mail Address
        S21MailGroup mailGroupFrom = new S21MailGroup(mailInfoBean.getGlblCmpyCd(), FROM0003);
        mailGroupFrom.setMailKey1(MAIL_KEY_NSZ);
        List<S21MailAddress> fromAddressList = mailGroupFrom.getMailAddress();

        if (fromAddressList == null || fromAddressList.isEmpty()) {
            S21InfoLogOutput.println(NSXM0008E, null);
            errorMsgList.add(NSXM0008E);
            return errorMsgList;
        }

        // get To Mail Address(Dispatcher Mail Address)
        String dispatcherAddress = getDispatcherAddress(mailInfoBean);

        if (!ZYPCommonFunc.hasValue(dispatcherAddress)) {
            S21InfoLogOutput.println(NSXM0009E, null);
            errorMsgList.add(NSXM0009E);
            return errorMsgList;
        }

        List<S21MailAddress> toAddressList = new ArrayList<S21MailAddress>();
        String[] dispatcherAddressArray = dispatcherAddress.split(",");
        for (String address : dispatcherAddressArray) {
            S21MailAddress toAddress = new S21MailAddress(mailInfoBean.getGlblCmpyCd(), address.trim());
            toAddressList.add(toAddress);
        }

        // get Mail Template
        S21MailTemplate template = new S21MailTemplate(mailInfoBean.getGlblCmpyCd(), MAIL_TEMPLATE_ID);
        if (template == null || !ZYPCommonFunc.hasValue(template.getSubject())) {
            S21InfoLogOutput.println(NSXM0010E, null);
            errorMsgList.add(NSXM0010E);
            return errorMsgList;
        }
        template.setTemplateParameter("interfaceId", mailInfoBean.getInterfaceId());
        template.setTemplateParameter("interfaceNm", mailInfoBean.getInterfaceNm());
        template.setTemplateParameter("errorMessage", createMailDtlMsg(mailInfoBean.getMsgIdList()));

        TECH_MSTRTMsg techMstrTMsg = getTechMstrInfo(mailInfoBean.getGlblCmpyCd(), mailInfoBean.getTechCd());
        if (null != techMstrTMsg) {
            template.setTemplateParameter("techNm", techMstrTMsg.techNm.getValue());
            template.setTemplateParameter("techTelNum", techMstrTMsg.telNum.getValue());
            template.setTemplateParameter("techCellPhoNum", techMstrTMsg.techCellPhoNum.getValue());
            template.setTemplateParameter("techFaxNum", techMstrTMsg.faxNum.getValue());
            template.setTemplateParameter("techEmlAddr", techMstrTMsg.emlAddr.getValue());
        }

        // set Mail Data
        S21Mail mail = new S21Mail(mailInfoBean.getGlblCmpyCd());
        mail.setFromAddress(fromAddressList.get(0));
        mail.setToAddressList(toAddressList);
        mail.setMailTemplate(template);

        // post Mail
        String resCd = mail.postMail();
        if (!ZYPCommonFunc.hasValue(resCd)) {
            S21InfoLogOutput.println(NSXM0011E, null);
            errorMsgList.add(NSXM0011E);
            return errorMsgList;
        }

        return null;
    }

    /**
     * check Input Parameter
     * @param mailInfoBean MailInfoBean
     */
    private static void chkInputParam(MailInfoBean mailInfoBean) {

        if (!ZYPCommonFunc.hasValue(mailInfoBean.getGlblCmpyCd())) {
            S21InfoLogOutput.println(NSXM0003E, null);
            errorMsgList.add(NSXM0003E);
        }

        if (!ZYPCommonFunc.hasValue(mailInfoBean.getInterfaceId())) {
            S21InfoLogOutput.println(NSXM0004E, null);
            errorMsgList.add(NSXM0004E);
        }

        if (!ZYPCommonFunc.hasValue(mailInfoBean.getInterfaceNm())) {
            S21InfoLogOutput.println(NSXM0005E, null);
            errorMsgList.add(NSXM0005E);
        }

        List<String> msgIdList = mailInfoBean.getMsgIdList();
        if (msgIdList == null || msgIdList.isEmpty()) {
            S21InfoLogOutput.println(NSXM0006E, null);
            errorMsgList.add(NSXM0006E);

        } else {
            for (String msgId : msgIdList) {
                if (!ZYPCommonFunc.hasValue(msgId)) {
                    S21InfoLogOutput.println(NSXM0006E, null);
                    errorMsgList.add(NSXM0006E);
                    break;
                }
            }
        }

        if (!ZYPCommonFunc.hasValue(mailInfoBean.getTechCd())) {
            S21InfoLogOutput.println(NSXM0007E, null);
            errorMsgList.add(NSXM0007E);
        }
    }

    /**
     * get Dispatcher Mail Address from MBL_TECH_MSTR
     * @param mailInfoBean MailInfoBean
     * @return Dispatcher Mail Address
     */
    private static String getDispatcherAddress(MailInfoBean mailInfoBean) {

        MBL_TECH_MSTRTMsg mblTechMstrTMsg = new MBL_TECH_MSTRTMsg();
        ZYPEZDItemValueSetter.setValue(mblTechMstrTMsg.glblCmpyCd, mailInfoBean.getGlblCmpyCd());
        ZYPEZDItemValueSetter.setValue(mblTechMstrTMsg.techTocCd, mailInfoBean.getTechCd());
        mblTechMstrTMsg = (MBL_TECH_MSTRTMsg) EZDTBLAccessor.findByKey(mblTechMstrTMsg);

        if (mblTechMstrTMsg != null) {
            return mblTechMstrTMsg.disptEmlAddr.getValue();
        }

        return null;
    }

    /**
     * Create Mail Detail Message
     * @param msgIdList Message ID List
     * @return Mail Detail Message
     */
    private static String createMailDtlMsg(List<String> msgIdList) {

        StringBuilder mailDtlMsg = null;

        for (String msgId : msgIdList) {
            if (mailDtlMsg == null) {
                mailDtlMsg = new StringBuilder();
                mailDtlMsg.append(new EZDMessageInfo(msgId, null).getMessage());
                continue;
            }

            mailDtlMsg.append(LINE_FEED_CODE);
            mailDtlMsg.append(new EZDMessageInfo(msgId, null).getMessage());
        }

        return mailDtlMsg.toString();
    }

    /**
     * get Technician Info from TECH_MSTR
     * @param glblCmpyCd Global Company Code
     * @param techTocCd Technician TOC Code
     * @return Technician Info
     */
    private static TECH_MSTRTMsg getTechMstrInfo(String glblCmpyCd, String techTocCd) {

        TECH_MSTRTMsg techMstrTMsg = new TECH_MSTRTMsg();
        techMstrTMsg.glblCmpyCd.setValue(glblCmpyCd);
        techMstrTMsg.techTocCd.setValue(techTocCd);

        return (TECH_MSTRTMsg) S21FastTBLAccessor.findByKey(techMstrTMsg);
    }
}
