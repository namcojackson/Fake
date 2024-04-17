/**
 * <Pre>Copyright (c) 2012 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NPA.NPAB114001;

/**
 *<pre>
 * NPAB1140:MRP Run Post-process Batch Mail
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/05/2012   Hitachi         T.Tomita        Create          N/A
 *</pre>
 */
public class NPAB114001Mail {
    //
    // /** Mail Item : Global Company Code */
    // private String glblCmpyCd;
    //
    // /** Mail Item : Error Merchandise Code Map */
    // private String errMdseCodeMap;
    //
    // /**
    // * Constructor
    // * @param glblCmpyCd Global Company Code
    // * @param errMdseCodeMap Error Merchandise Code Map
    // */
    // public NPAB114001Mail(String glblCmpyCd, String errMdseCodeMap)
    // {
    // this.glblCmpyCd = glblCmpyCd;
    // this.errMdseCodeMap = errMdseCodeMap;
    // }
    //
    // /**
    // * Mail Send.
    // */
    // public void send() {
    // String errDate =
    // ZYPDateUtil.getCurrentSystemTime(MAIL_DATE_FORMAT);
    //
    // // Get From Mail Address.
    // S21MailGroup groupFrom = new S21MailGroup(glblCmpyCd,
    // MAIL_GROUP_ID_FROM);
    // groupFrom.setMailKey1(MAIL_KEY_FROM);
    // S21MailAddress fromAddress;
    // List<S21MailAddress> addrFromList = groupFrom.getMailAddress();
    // if (addrFromList == null || addrFromList.isEmpty()) {
    // throw new S21AbendException(NPAM0063E, new String[]
    // {MAIL_GROUP_ID_FROM, MAIL_KEY_FROM });
    // }
    //
    // // Get To Mail Address.
    // S21MailGroup groupTo = new S21MailGroup(glblCmpyCd,
    // MAIL_GROUP_ID_TO);
    // groupTo.setMailKey1(MAIL_KEY_TO);
    // List<S21MailAddress> addrToList = groupTo.getMailAddress();
    // if (addrToList == null || addrToList.isEmpty()) {
    // throw new S21AbendException(NPAM0063E, new String[]
    // {MAIL_GROUP_ID_TO, MAIL_KEY_TO });
    // }
    //
    // // Get Mail Template.
    // S21MailTemplate template = new S21MailTemplate(glblCmpyCd,
    // MAIL_TEMPLATE_ID);
    // if (!ZYPCommonFunc.hasValue(template.getBody())) {
    // throw new S21AbendException(NPAM0064E, new String[]
    // {MAIL_TEMPLATE_ID });
    // }
    //
    // fromAddress = addrFromList.get(0);
    //
    // S21Mail mail = new S21Mail(glblCmpyCd);
    //
    // // Set From Mail Address.
    // mail.setFromAddress(fromAddress);
    // // Set To Mail Address.
    // mail.setToAddressList(addrToList);
    //
    // // Set Parameter
    // template.setTemplateParameter(MAIL_ITEM_ERROR_DATE, errDate);
    // template.setTemplateParameter(MAIL_ITEM_COMMENT,
    // MAIL_ERROR_COMMENT);
    // template.setTemplateParameter(MAIL_ITEM_MDSE_CD_MAP,
    // errMdseCodeMap);
    //
    // mail.setMailTemplate(template);
    // mail.setSubject(template.getSubject(), MAIL_CHARSET);
    //
    // String resCd = mail.postMail();
    // if (!ZYPCommonFunc.hasValue(resCd)) {
    // throw new S21AbendException(NPAM0119E, new String[]
    // {"Send e-mail" });
    // }
    // }
}
