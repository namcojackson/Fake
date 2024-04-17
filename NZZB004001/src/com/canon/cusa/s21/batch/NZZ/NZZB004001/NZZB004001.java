/*
 * <Pre>Copyright (c) 2019 Canon USA Inc. All rights reserved.</Pre>
 */

package com.canon.cusa.s21.batch.NZZ.NZZB004001;

import static com.canon.cusa.s21.batch.NZZ.NZZB004001.constant.NZZB004001Constant.MAIL_GROUP_ID_TO;
import static com.canon.cusa.s21.batch.NZZ.NZZB004001.constant.NZZB004001Constant.MAIL_GROUP_ID_FROM;
import static com.canon.cusa.s21.batch.NZZ.NZZB004001.constant.NZZB004001Constant.TODAY;
import static com.canon.cusa.s21.batch.NZZ.NZZB004001.constant.NZZB004001Constant.TIME;
import static com.canon.cusa.s21.batch.NZZ.NZZB004001.constant.NZZB004001Constant.ALXM1004E;
import static com.canon.cusa.s21.batch.NZZ.NZZB004001.constant.NZZB004001Constant.APAM0063E;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.util.List;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;

/**
 * <pre>
 * Send notifying emal when the job is completed
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 04/26/19     CITS            T.Sakata        Create          QC#31335
*</pre>
 */
public class NZZB004001 extends S21BatchMain {

    /** mailTemplateId */
    private static String MAIL_TEMPLATE_ID = "";    // get from shell parameter1

    /** Mail Key 1 Value (To) */
    private static String MAIL_GROUP_KEY_TO = "";   // get from shell parameter2

    /** Mail Key 1 Value (From) */
    private static String MAIL_GROUP_KEY_FROM = ""; // get from shell parameter3

    /** success count */
    private int successCount = 0;

    /** error count */
    private int errorCount = 0;

    /** total count */
    private int totalCount = 0;

    /** UserProfile global company code */
    private String glblCmpyCd = "";

    /** termination code */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /**
     * Called batch Shell
     * @param args String[]
     */
    public static void main(String[] args) {

        new NZZB004001().executeBatch(NZZB004001.class.getSimpleName());
    }

    @Override
    protected void initRoutine() {

        glblCmpyCd = getGlobalCompanyCode();
        
        // get parameter from shell
        MAIL_TEMPLATE_ID = getUserVariable1();
        
        MAIL_GROUP_KEY_TO = getUserVariable2();
        
        MAIL_GROUP_KEY_FROM = getUserVariable3();

    }

    @Override
    protected void mainRoutine() {
        
        // Mail Send
        if (!postMail()) {
            this.termCd = TERM_CD.ABNORMAL_END;
            throw new S21AbendException(ALXM1004E);
        }
        
    }

    @Override
    protected void termRoutine() {

        setTermState(termCd, successCount, errorCount, totalCount);
    }

    
    /**
     * Mail is set to DB.
     */
    private boolean postMail() {

        S21Mail mail = new S21Mail(this.glblCmpyCd);

        // Get From Mail Address
        S21MailGroup groupFrom = new S21MailGroup(glblCmpyCd, MAIL_GROUP_ID_FROM);
        groupFrom.setMailKey1(MAIL_GROUP_KEY_FROM);
        List<S21MailAddress> addrFromList = groupFrom.getMailAddress();
        S21MailAddress from = null;
        if (!addrFromList.isEmpty()) {
            from = addrFromList.get(0);
        } else {
            return false;
        }

        mail.setFromAddress(from);

        // Get To Mail Address
        S21MailGroup groupTo = new S21MailGroup(glblCmpyCd, MAIL_GROUP_ID_TO);
        groupTo.setMailKey1(MAIL_GROUP_KEY_TO);
        List<S21MailAddress> addrToList = groupTo.getMailAddress();
        if (addrToList.isEmpty()) {
            throw new S21AbendException(APAM0063E, new String[]{MAIL_GROUP_ID_TO, MAIL_GROUP_KEY_TO});
        }

        mail.setToAddressList(addrToList);

        // Create Subject and Body
        S21MailTemplate template = new S21MailTemplate(glblCmpyCd, MAIL_TEMPLATE_ID);

        if (template == null) {
            return false;
        }

        template.setTemplateParameter(TODAY, ZYPDateUtil.getCurrentSystemTime("MM/dd/yyyy"));
        template.setTemplateParameter(TIME, ZYPDateUtil.getCurrentSystemTime("HH:mm"));

        String subject = template.getSubject();
        if (!hasValue(subject)) {
            return false;
        }
        mail.setSubject(subject);

        String body = template.getBody();
        if (!hasValue(subject)) {
            return false;
        }
        mail.setText(body);
        mail.setMailTemplate(template);

        String mailEvent = mail.sendMail();
        if (!hasValue(mailEvent)) {
            return false;
        }

        return true;
    }

}
