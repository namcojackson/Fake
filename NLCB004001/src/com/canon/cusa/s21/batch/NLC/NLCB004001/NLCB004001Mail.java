/**
 * <Pre>Copyright (c) 2012 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NLC.NLCB004001;

import static com.canon.cusa.s21.batch.NLC.NLCB004001.constant.NLCB004001Constant.*;

import java.util.List;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;

/**
 * <pre>
 * Send Mail Program Class for PI(Physical Inventory) Start Job
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/29/2016    CITS            N.Akaishi       Create          N/A
 * </pre>
 */
public class NLCB004001Mail {

    /** S21Mail */
    S21Mail mail;

    /** S21MailTemplate */
    S21MailTemplate template = null;

    /** mail item : errDetailMap */
    StringBuilder errDetailMap;

    /**
     * @param errDetailMap set errDetailMap
     */
    public void setErrDetailMap(StringBuilder errDetailMap) {
        this.errDetailMap.append(errDetailMap);
    }

    /**
     * Constructor
     * @param glblCmpyCd Global Company Code
     */
    public NLCB004001Mail(String glblCmpyCd) {

        // Get From Mail Address.
        S21MailGroup groupFrom = new S21MailGroup(glblCmpyCd, MAIL_GROUP_ID_FROM);
        groupFrom.setMailKey1(MAIL_KEY_FROM);
        S21MailAddress fromAddress;
        List<S21MailAddress> addrFromList = groupFrom.getMailAddress();
        if (addrFromList == null || addrFromList.isEmpty()) {
            throw new S21AbendException(NPAM0063E, new String[] {MAIL_GROUP_ID_FROM, MAIL_KEY_FROM });
        }

        // Get To Mail Address.
        S21MailGroup groupTo = new S21MailGroup(glblCmpyCd, MAIL_GROUP_ID_TO);
        groupTo.setMailKey1(MAIL_KEY_TO);
        List<S21MailAddress> addrToList = groupTo.getMailAddress();
        if (addrToList == null || addrToList.isEmpty()) {
            throw new S21AbendException(NPAM0063E, new String[] {MAIL_GROUP_ID_TO, MAIL_KEY_TO });
        }

        // Get Mail Template.
        template = new S21MailTemplate(glblCmpyCd, MAIL_TEMPLATE_ID);
        if (!ZYPCommonFunc.hasValue(template.getBody())) {
            throw new S21AbendException(NPAM0064E, new String[] {MAIL_TEMPLATE_ID });
        }

        fromAddress = addrFromList.get(0);

        mail = new S21Mail(glblCmpyCd);

        // Set From Mail Address.
        mail.setFromAddress(fromAddress);
        // Set To Mail Address.
        mail.setToAddressList(addrToList);
        this.errDetailMap = new StringBuilder();

    }

    /**
     * Mail Send.
     * @return boolean
     */
    public boolean send() {
        boolean rtn = true;

        // Set Parameter
        template.setTemplateParameter(MAIL_ITEM_TODAY, ZYPDateUtil.getCurrentSystemTime(MAIL_DATE_FORMAT_MDY));
        template.setTemplateParameter(MAIL_ITEM_TIME, ZYPDateUtil.getCurrentSystemTime(MAIL_DATE_FORMAT_HMS));
        template.setTemplateParameter(MAIL_ITEM_ERR_MSG, errDetailMap.toString());

        mail.setMailTemplate(template);
        mail.setSubject(template.getSubject(), MAIL_CHARSET);

        String resCd = mail.postMail();
        if (!ZYPCommonFunc.hasValue(resCd)) {
            rtn = false;
        }
        return rtn;
    }

    /**
     * String Value Format
     * @param instr String Value
     * @param outlen Return Value Length
     * @return Format String Value
     */
    public String formatString(String instr, int outlen) {
        StringBuilder rtnstrbldr = new StringBuilder();

        if (!ZYPCommonFunc.hasValue(instr)) {
            instr = "";
        }

        rtnstrbldr.append(instr);
        rtnstrbldr.append(FULL_SPACE);

        return rtnstrbldr.substring(0, outlen);
    }

}
