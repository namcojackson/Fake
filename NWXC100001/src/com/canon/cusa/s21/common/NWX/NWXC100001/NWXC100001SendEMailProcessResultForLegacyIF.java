/*
 * <Pre>Copyright (c) 2012 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.common.NWX.NWXC100001;

import java.util.List;
import java.util.ArrayList;

import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;

/**
 *<pre>
 *  Send E-Mail Process Result for LegacyIF.
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/25/2012   Fujitsu         A.Wada          Create          N/A
 *</pre>
 */
public class NWXC100001SendEMailProcessResultForLegacyIF {

    /**
     * Mail
     */
    /** mail group id for From */
    public static final String MAIL_GROUP_ID_FROM = "FROM0011";

    /** mail group id for To */
    public static final String MAIL_GROUP_ID_TO = "NWXC1000TOSC";

    /** mail key for From */
    public static final String MAIL_KEY_FROM = "From";

    /** mail key for To */
    public static final String MAIL_KEY_TO = "To";

    /** template ID */
    public static final String MAIL_TEMPLATE_ID = "NWXC1000M001";

    /** line feed code */
    public static final String LINE_FEED_CODE = "\r\n";

    /** template parameter key : Report Title Name */
    public static final String MAIL_TEMPLATE_KEY_RPT_TTL_NM    =   "rptName";

    /** template parameter key : Process Status Name */
    public static final String MAIL_TEMPLATE_KEY_PROC_STS_NM   =   "prcResult";

    /** template parameter key : Process Date */
    public static final String MAIL_TEMPLATE_KEY_PROC_DT       =   "prcDate";

    /** template parameter key : Transaction Name1 */
    public static final String MAIL_TEMPLATE_KEY_TRX_NM1       =   "trxName1";

    /** template parameter key : Transaction Number1 */
    public static final String MAIL_TEMPLATE_KEY_TRX_NUM1      =   "trxNumber1";

    /** template parameter key : Transaction Name2 */
    public static final String MAIL_TEMPLATE_KEY_TRX_NM2       =   "trxName2";

    /** template parameter key : Transaction Number2 */
    public static final String MAIL_TEMPLATE_KEY_TRX_NUM2      =   "trxNumber2";

    /** template parameter key : Transaction Name3 */
    public static final String MAIL_TEMPLATE_KEY_TRX_NM3       =   "trxName3";

    /** template parameter key : Transaction Number3 */
    public static final String MAIL_TEMPLATE_KEY_TRX_NUM3      =   "trxNumber3";

    /** template parameter key : Message */
    public static final String MAIL_TEMPLATE_KEY_MESSAGE       =   "Message1";

    /** Date Time Pattern For Mail */
    public static final String DATE_TIME_PATTERN_FOR_MAIL = "MM/dd/yyyy HH:mm:ss";

    /** Error Message  */
    public static final String NWBM0092E = "NWBM0092E";

    /** Error Message List  */
    private List<NWXC100001MsgBean> msgBeanList;

    /**
     *<pre>
     * constractor
     *</pre>
     */
    public NWXC100001SendEMailProcessResultForLegacyIF() {
        this.msgBeanList = new ArrayList<NWXC100001MsgBean>();
    }

    /**
     *<pre>
     * Add Process Result Message
     * @param xxMsgBean NWXC100001MsgBean Process Result Message
     *</pre>
     */
    public void addProcRsltMsg(final NWXC100001MsgBean xxMsgBean) {
        this.msgBeanList.add(xxMsgBean);
    }

    /**
     *<pre>
     * Add Process Result Message List
     * @param xxMsgBeanList List<NWXC100001MsgBean> List of Process Result Message
     *</pre>
     */
    public void addProcRsltMsgList(final List<NWXC100001MsgBean> xxMsgBeanList) {
        this.msgBeanList.addAll(xxMsgBeanList);
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
        return sendMail(glblCmpyCd, bizId, MAIL_GROUP_ID_TO, MAIL_KEY_TO);
    }


    /**
     *<pre>
     * Send Mail
     * @param glblCmpyCd String Global Company Code
     * @param bizId String Business ID
     * @param toMailGrpId String Mail Group ID
     * @param toMailKey1 String Mail Key1
     * @return Error Message ID
     *</pre>
     */
    public String sendMail(final String glblCmpyCd, final String bizId, final String toMailGrpId, final String toMailKey1) {

        // 1. Get From
        S21MailGroup groupFrom = new S21MailGroup(glblCmpyCd, MAIL_GROUP_ID_FROM);
        groupFrom.setMailKey1(MAIL_KEY_FROM);
        List<S21MailAddress> addrFromList = groupFrom.getMailAddress();

        S21MailAddress from = null;
        if (!addrFromList.isEmpty()) {
            from = addrFromList.get(0);
        }

        // 2. Get To
        S21MailGroup groupTo = new S21MailGroup(glblCmpyCd, toMailGrpId);
        groupTo.setMailKey1(toMailKey1);
        List<S21MailAddress> addrToList = groupTo.getMailAddress();
        if (addrToList.isEmpty()) {
            return NWBM0092E;
        }

        for (NWXC100001MsgBean msgBean : msgBeanList) {

            // 3. Create message for Body
            StringBuilder msgBuf = new StringBuilder();
            for (String xxMsgId : msgBean.getMsgIdList()) {
                msgBuf.append(S21MessageFunc.clspGetMessage(xxMsgId));
                msgBuf.append(LINE_FEED_CODE);
            }
            String message = msgBuf.toString();

            // 4. Create Subject and Body
            S21MailTemplate template = new S21MailTemplate(glblCmpyCd, MAIL_TEMPLATE_ID);
            if (template == null) {
                return NWBM0092E;
            }

            template.setTemplateParameter(MAIL_TEMPLATE_KEY_RPT_TTL_NM,     msgBean.getRptTtlNm());
            template.setTemplateParameter(MAIL_TEMPLATE_KEY_PROC_STS_NM,    msgBean.getProcStsNm());
            template.setTemplateParameter(MAIL_TEMPLATE_KEY_PROC_DT,        msgBean.getProcDt());
            template.setTemplateParameter(MAIL_TEMPLATE_KEY_TRX_NM1,        msgBean.getXxTrxNm1());
            template.setTemplateParameter(MAIL_TEMPLATE_KEY_TRX_NUM1,       msgBean.getXxTrxNum1());
            template.setTemplateParameter(MAIL_TEMPLATE_KEY_TRX_NM2,        msgBean.getXxTrxNm2());
            template.setTemplateParameter(MAIL_TEMPLATE_KEY_TRX_NUM2,       msgBean.getXxTrxNum2());
            template.setTemplateParameter(MAIL_TEMPLATE_KEY_TRX_NM3,        msgBean.getXxTrxNm3());
            template.setTemplateParameter(MAIL_TEMPLATE_KEY_TRX_NUM3,       msgBean.getXxTrxNum3());
            template.setTemplateParameter(MAIL_TEMPLATE_KEY_MESSAGE,        message);

            // 5. Call Mail API
            S21Mail mail = new S21Mail(glblCmpyCd);
            mail.setFromAddress(from);
            mail.setToAddressList(addrToList);
            mail.setMailTemplate(template);
            mail.postMail();

        }

        return null;
    }

    /**
     *<pre>
     * Clear Error Message Text List
     *</pre>
     */
    public void clearMsgTxtList() {
        msgBeanList.clear();
    }

}
