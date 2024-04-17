/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NSB.NSBB048001;

import static com.canon.cusa.s21.batch.NSB.NSBB048001.constant.NSBB048001Constant.*;

import java.util.ArrayList;
import java.util.List;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailAttachment;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;

/** 
 *<pre>
 *
 * Escalation Email Batch
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/13/2015   Hitachi         J.Kim           Create          N/A
 *</pre>
 */
public class NSBB048001TssMail {

    /**
     * Global Company Code
     */
    private String glblCmpyCd;

    /**
     * CSV HEADER
     */
    private StringBuilder csvValueHeaderStr = new StringBuilder();

    /**
     * Constructor
     * 
     * @param glblCmpyCd Global Company Code
     */
    public NSBB048001TssMail(String glblCmpyCd) {
        this.glblCmpyCd = glblCmpyCd;
    }

    /**
     * 
     * Mail send
     * 
     * @param csvValueStr Target Information
     * @param techSprtGrpEmlAddr MailAddress
     * @param svcTssEsclDt Detection Time
     * @param currentSystemTs Current Time
     * @return String
     */
    public String sendMail(StringBuilder csvValueStr, String techSprtGrpEmlAddr, String svcTssEsclDt, String currentSystemTs) {

        // 1. Get From Address
        S21MailGroup groupFrom = new S21MailGroup(this.glblCmpyCd, MAIL_GROUP_ID_FROM);
        groupFrom.setMailKey1(MAIL_GROUP_KEY_FROM);

        List<S21MailAddress> addrFromList = groupFrom.getMailAddress();
        if (addrFromList == null || addrFromList.isEmpty()) {
            return setErrorInfo(NSAM0069E, new String[] {"FROM mail-address.", (MAIL_GROUP_ID_FROM + "/" + MAIL_GROUP_KEY_FROM) });
        }

        // 2. Get To Address
        List<S21MailAddress> addrToList = new ArrayList<S21MailAddress>();
        S21MailAddress addr = new S21MailAddress(this.glblCmpyCd, techSprtGrpEmlAddr);
        addrToList.add(addr);
        if (addrToList == null || addrToList.isEmpty()) {
            return setErrorInfo(NSAM0069E, new String[] {"TO mail-address.", MAIL_GROUP_ID_TO });
        }

        // 3. Get Mail Template.
        S21MailTemplate template = new S21MailTemplate(glblCmpyCd, MAIL_TEMPLATE_ID_01);
        if (!ZYPCommonFunc.hasValue(template.getBody())) {
            return setErrorInfo(NSAM0069E, new String[] {"Mailtemplate", MAIL_TEMPLATE_ID_01 });
        }

        // 4. Create message for Body
        S21MailAddress fromAddress;
        fromAddress = addrFromList.get(0);

        // CSV file attachment for email.
        S21MailAttachment attachment = new S21MailAttachment(this.glblCmpyCd);
        creatHeader();
        String csvInfo = this.csvValueHeaderStr.append(csvValueStr).toString();
        byte[] attachData = csvInfo.getBytes();
        int attachId = attachment.setAttachment(attachData);
        attachment.setFileName(setFileName(currentSystemTs));

        // 5. Create Subject and Body
        S21Mail mail = new S21Mail(glblCmpyCd);
        mail.setFromAddress(fromAddress);
        mail.setToAddressList(addrToList);
        mail.setAttachmentId(attachId);
        template.setTemplateParameter(MAIL_ITEM_DT, svcTssEsclDt);
        template.setTemplateParameter(MAIL_ITEM_SVC_TSS_ESCL_DT, ZYPDateUtil.DateFormatter(svcTssEsclDt, "yyyyMMdd", "MM/dd/yyyy"));
        template.setTemplateParameter(MAIL_ITEM_CSV_FILE_NAME, setFileName(currentSystemTs));

        // 6. Call Mail API
        mail.setMailTemplate(template);
        mail.setSubject(template.getSubject(), MAIL_CHARSET);
        mail.postMail();

        return null;
    }

    private String setFileName(String currentSystemTs) {

        StringBuffer sb = new StringBuffer();
        sb.append(TTS_FILE_NAME);
        sb.append(currentSystemTs.substring(0, DT_LEN));
        sb.append(CSV_EXT);

        return sb.toString();
    }

    /**
     * set error info
     * @param msgId
     * @param apiBizId
     */
    private String setErrorInfo(String msgId, String[] params) {
        S21InfoLogOutput.println(msgId, params);
        return S21MessageFunc.clspGetMessage(msgId, params);
    }


    private void creatHeader() {

        for (String column : CSV_HEADER) {
            this.csvValueHeaderStr.append(column);
            this.csvValueHeaderStr.append(STR_CNM);
        }

        this.csvValueHeaderStr.append(STR_CRLF);
    }
}
