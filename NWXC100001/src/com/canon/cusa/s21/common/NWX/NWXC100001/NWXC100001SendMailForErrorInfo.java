/*
 * <Pre>Copyright (c) 2012 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.common.NWX.NWXC100001;

import java.util.List;
import java.util.ArrayList;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;

/**
 *<pre>
 *  Send E-Mail for Error Info.
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/24/2012   Fujitsu         A.Wada          Create          N/A
 *</pre>
 */
public class NWXC100001SendMailForErrorInfo {

    /**
     * Mail
     */
    /** mail group id for From */
    public static final String MAIL_GROUP_ID_FROM = "FROM0005";

    /** mail group id for To */
    public static final String MAIL_GROUP_ID_TO = "NWXC1000BERR";

    /** mail key for To */
    public static final String MAIL_KEY_TO = "To";

    /** template ID */
    public static final String MAIL_TEMPLATE_ID = "NWXC1000M002";

    /** mail message header */
    public static final String MAIL_MSG_HEADER = "Error Message";

    /** line feed code */
    public static final String LINE_FEED_CODE = "\r\n";

    /** template parameter key : batch id */
    public static final String MAIL_TEMPLATE_KEY_ID = "batchId";

    /** template parameter key : err date */
    public static final String MAIL_TEMPLATE_KEY_DATE = "errDate";

    /** template parameter key : message */
    public static final String MAIL_TEMPLATE_KEY_MESSAGE = "message";

    /** Date Time Pattern For Mail */
    public static final String DATE_TIME_PATTERN_FOR_MAIL = "MM/dd/yyyy HH:mm:ss";

    /** Error Message : It failed to send mail. */
    public static final String NWBM0092E = "NWBM0092E";

    /** Error Message List  */
    private List<String> errMsgTxtList;

    /**
     *<pre>
     * constractor
     *</pre>
     */
    public NWXC100001SendMailForErrorInfo() {
        this.errMsgTxtList = new ArrayList<String>();
    }

    /**
     *<pre>
     * Add Error Message Text
     * @param xxMsgTxt String Error Message Text
     *</pre>
     */
    public void addErrMsg(final String xxMsgTxt) {
        this.errMsgTxtList.add(xxMsgTxt);
    }

    /**
     *<pre>
     * Add Error Message Text List
     * @param xxMsgTxtList List<String> List of Error Message Text
     *</pre>
     */
    public void addErrMsgList(final List<String> xxMsgTxtList) {
        this.errMsgTxtList.addAll(xxMsgTxtList);
    }

    /**
     *<pre>
     * Send Mail
     * @param glblCmpyCd String Global Company Code
     * @param bizId String Business ID
     * @return Error Message ID
     *</pre>
     */
    public String sendMail(final String glblCmpyCd, final String bizId) {

        // 1. Get From Address
        S21MailGroup groupFrom = new S21MailGroup(glblCmpyCd, MAIL_GROUP_ID_FROM);
        List<S21MailAddress> addrFromList = groupFrom.getMailAddress();

        S21MailAddress from = null;
        if (!addrFromList.isEmpty()) {
            from = addrFromList.get(0);
        }

        // 2. Get To Address
        S21MailGroup groupTo = new S21MailGroup(glblCmpyCd, MAIL_GROUP_ID_TO);
        groupTo.setMailKey1(MAIL_KEY_TO);
        List<S21MailAddress> addrToList = groupTo.getMailAddress();
        if (addrToList.isEmpty()) {
            return NWBM0092E;
        }

        // 3. Create message for Body
        StringBuilder msgBuf = new StringBuilder();
        msgBuf.append(MAIL_MSG_HEADER);
        msgBuf.append(LINE_FEED_CODE);
        for (String wkStr : this.errMsgTxtList) {
            msgBuf.append(wkStr);
            msgBuf.append(LINE_FEED_CODE);
        }
        String message = msgBuf.toString();

        // 4. Create Subject and Body
        S21MailTemplate template = new S21MailTemplate(glblCmpyCd, MAIL_TEMPLATE_ID);
        if (template == null) {
            return NWBM0092E;
        }

        String currentTime = ZYPDateUtil.getCurrentSystemTime(DATE_TIME_PATTERN_FOR_MAIL);

        template.setTemplateParameter(MAIL_TEMPLATE_KEY_ID, bizId);
        template.setTemplateParameter(MAIL_TEMPLATE_KEY_DATE, currentTime);
        template.setTemplateParameter(MAIL_TEMPLATE_KEY_MESSAGE, message);

        // 5. Call Mail API
        S21Mail mail = new S21Mail(glblCmpyCd);
        mail.setFromAddress(from);
        mail.setToAddressList(addrToList);
        mail.setMailTemplate(template);
        mail.postMail();

        return null;
    }

    /**
     *<pre>
     * Clear Error Message Text List
     *</pre>
     */
    public void clearMsgTxtList() {
        errMsgTxtList.clear();
    }
}
