/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.common.NLX.NLXC009001;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;

/**
 * <pre>
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/03/2009   Fujitsu         M.Irisawa       Create          N/A
 * 10/23/2013   Fujitsu         A.Wada          Update          Def#349
 * 12/21/2020   CITS            K.Ogino         Update          QC#58137
 *</pre>
 */
public class NLXMailSend {
    /* */
    public static final String KEY_MESSAGE_ID = "KEY_MESSAGE_ID";

    /* */
    public static final String KEY_MESSAGE = "KEY_MESSAGE";

    /* */
    public static final String FMT_ERR_DATETIME = "MM/dd/yyyy HH:mm:ss";

    /* */
    private static final String NLXM1004E = "NLXM1004E";

    /* */
    private static final String TEMPLATE_KEY_BATCH_ID = "batchId";

    /* */
    private static final String TEMPLATE_KEY_ERR_DATE = "errdate";

    /* */
    private static final String TEMPLATE_KEY_MESSAGE = "message";

    /* */
    private static final String HEADER_OM4 = "Message#      Message";

    /* */
    private static final String STR_CRLF = "\r\n";

    /* */
    private static final String STR_BLANK_CONT = "     ";

    /* */
    private static final String STR_BLANK = " ";

    /* */
    private static final String TEMPLATE_KEY_NLXC0090 = "NLXC0090";

    /* */
    private static final String TEMPLATE_KEY_NLXC0080 = "NLXC0080";

    /* */
    private static final String MT_ID = "M";

    /* */
    private static final String SEQ = "001";

    /* */
    private static final String FROMID = "FROM0003";

    /* */
    // START 2013/10/23 A.Wada [Def#349, MOD]
    // private static final String FROMKEY = "NLA";
    private static final String FROMKEY = "NLA";
    // END   2013/10/23 A.Wada [Def#349, MOD]

    /* */
    private String glblCmpyCd = null;

    /* */
    private S21Mail mail = null;

    /**
     */
    public NLXMailSend(String glblCmpyCd) {
        this.glblCmpyCd = glblCmpyCd;
        this.mail = new S21Mail(this.glblCmpyCd);
    }

    /**
     */
    public void send(String batchId, String errDate, String header, List<String> errList) {
        send(batchId, errDate, header, errList, false);
    }

    /**
     */
    public void send(String batchId, String errDate, String header, List<String> errList, boolean isSendSameTime) {
        StringBuilder errString = new StringBuilder();
        if (errList != null) {
            for (String tmpMsg : errList) {
                errString.append(tmpMsg);
                errString.append(STR_CRLF);
            }
        }
        S21MailGroup mailGroupTo = new S21MailGroup(this.glblCmpyCd, TEMPLATE_KEY_NLXC0090);
        send(TEMPLATE_KEY_NLXC0090, batchId, errDate, header, errString.toString(), mailGroupTo.getMailAddress(), isSendSameTime);
    }

    /**
     */
    public void send(String batchId, List<Map<String, String>> errList) {
        send(batchId, ZYPDateUtil.getCurrentSystemTime(FMT_ERR_DATETIME), errList);
    }

    /**
     */
    public void send(String batchId, List<Map<String, String>> errList, boolean isSendSameTime) {
        send(batchId, ZYPDateUtil.getCurrentSystemTime(FMT_ERR_DATETIME), errList, isSendSameTime);
    }

    /**
     */
    public void send(String batchId, String errDate, List<Map<String, String>> errList) {
        send(batchId, errDate, errList, false);
    }

    /**
     */
    public void send(String batchId, String errDate, List<Map<String, String>> errList, boolean isSendSameTime) {
        StringBuilder errString = new StringBuilder();
        if (errList != null) {
            for (Map<String, String> tmpMsgMap : errList) {
                String tmpMsgId = tmpMsgMap.get(KEY_MESSAGE_ID);
                String tmpMsg = tmpMsgMap.get(KEY_MESSAGE);
                if (ZYPCommonFunc.hasValue(tmpMsgId) && ZYPCommonFunc.hasValue(tmpMsg)) {
                    errString.append(tmpMsgId);
                    errString.append(STR_BLANK_CONT);
                    if (tmpMsg.startsWith(tmpMsgId)) {
                        StringBuilder sb = new StringBuilder(tmpMsg);
                        sb.delete(tmpMsg.indexOf(tmpMsgId), tmpMsg.indexOf(STR_BLANK) + 1);
                        tmpMsg = sb.toString();
                    }
                    errString.append(tmpMsg);
                    errString.append(STR_CRLF);
                }
            }
        }
        S21MailGroup mailGroupTo = new S21MailGroup(this.glblCmpyCd, TEMPLATE_KEY_NLXC0090);
        List<S21MailAddress> toAddressList = null;
        if (mailGroupTo != null) {
            toAddressList = mailGroupTo.getMailAddress();
        }
        send(TEMPLATE_KEY_NLXC0090, batchId, errDate, HEADER_OM4, errString.toString(), toAddressList, isSendSameTime);
    }

    /**
     */
    public void send(String batchId, List<String> errList, String toAddress) {
        send(batchId, errList, toAddress, false);
    }

    /**
     */
    public void send(String batchId, List<String> errList, String toAddress, boolean isSendSameTime) {
        StringBuilder errString = new StringBuilder();
        if (errList != null) {
            for (String tmpMsg : errList) {
                errString.append(tmpMsg);
                errString.append(STR_CRLF);
            }
        }
        List<S21MailAddress> mailGroupTo = new ArrayList<S21MailAddress>();
        S21MailAddress address = new S21MailAddress(this.glblCmpyCd, toAddress);
        mailGroupTo.add(address);
        send(TEMPLATE_KEY_NLXC0080, batchId, null, null, errString.toString(), mailGroupTo, isSendSameTime);
    }

    /**
     */
    private void send(String templateKey, String batchId, String errDate, String header, String errMessage, List<S21MailAddress> toAddressList, boolean isSendSameTime) {
        String returnCode = null;
        S21MailTemplate template = new S21MailTemplate(this.glblCmpyCd, templateKey + MT_ID + SEQ);
        template.setTemplateParameter(TEMPLATE_KEY_BATCH_ID, batchId);
        if (ZYPCommonFunc.hasValue(errDate)) {
            template.setTemplateParameter(TEMPLATE_KEY_ERR_DATE, errDate);
        }
        StringBuilder errString = new StringBuilder();
        if (ZYPCommonFunc.hasValue(header)) {
            errString.append(header + STR_CRLF);
        }
        errString.append(errMessage);
        template.setTemplateParameter(TEMPLATE_KEY_MESSAGE, errString.toString());

        S21MailGroup mailGroupFrom = new S21MailGroup(this.glblCmpyCd, FROMID);
        mailGroupFrom.setMailKey1(FROMKEY);
        S21MailAddress fromAddress = null;
        if (mailGroupFrom != null) {
            fromAddress = mailGroupFrom.getMailAddress().get(0);
        }

        returnCode = post(toAddressList, fromAddress, template, isSendSameTime);
        if (!ZYPCommonFunc.hasValue(returnCode)) {
            S21InfoLogOutput.println(NLXM1004E);
        }
    }

    /**
     */
    private String post(List<S21MailAddress> toAddress, S21MailAddress fromAddress, S21MailTemplate template, boolean isSendSameTime) {

        this.mail.setToAddressList(toAddress);

        this.mail.setFromAddress(fromAddress);

        this.mail.setMailTemplate(template);

        if (isSendSameTime) {
            return this.mail.sendMail();
        } else {
            return this.mail.postMail();
        }
    }

    // QC#58137 Add Start
    /**
     */
    public void send(String batchId, List<Map<String, String>> errList, String[] mlGrps) {
        send(batchId, ZYPDateUtil.getCurrentSystemTime(FMT_ERR_DATETIME), errList, mlGrps, false);
    }

    /**
     */
    public void send(String batchId, String errDate, List<Map<String, String>> errList, String[] mlGrps, boolean isSendSameTime) {
        StringBuilder errString = new StringBuilder();
        if (errList != null) {
            for (Map<String, String> tmpMsgMap : errList) {
                String tmpMsgId = tmpMsgMap.get(KEY_MESSAGE_ID);
                String tmpMsg = tmpMsgMap.get(KEY_MESSAGE);
                if (ZYPCommonFunc.hasValue(tmpMsgId) && ZYPCommonFunc.hasValue(tmpMsg)) {
                    errString.append(tmpMsgId);
                    errString.append(STR_BLANK_CONT);
                    if (tmpMsg.startsWith(tmpMsgId)) {
                        StringBuilder sb = new StringBuilder(tmpMsg);
                        sb.delete(tmpMsg.indexOf(tmpMsgId), tmpMsg.indexOf(STR_BLANK) + 1);
                        tmpMsg = sb.toString();
                    }
                    errString.append(tmpMsg);
                    errString.append(STR_CRLF);
                }
            }
        }

        List<S21MailAddress> toAddressList = null;
        for (String mlGrop : mlGrps) {
            S21MailGroup mailGroupTo = new S21MailGroup(this.glblCmpyCd, mlGrop);
            if (mailGroupTo != null) {
                toAddressList = mailGroupTo.getMailAddress();
            }
        }
        send(TEMPLATE_KEY_NLXC0090, batchId, errDate, HEADER_OM4, errString.toString(), toAddressList, isSendSameTime);
    }
    // QC#58137 Add End
}
