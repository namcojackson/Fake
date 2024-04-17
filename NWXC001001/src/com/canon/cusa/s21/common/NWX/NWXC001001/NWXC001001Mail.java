package com.canon.cusa.s21.common.NWX.NWXC001001;

import java.util.ArrayList;
import java.util.List;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;

import com.canon.cusa.s21.framework.userprofile.S21UserInfo;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory; 

public class NWXC001001Mail {

    /** Mail Group ID (From) */
    private static final String CST_MAIL_GRP_ID_FROM      = "FROM0005";
    
    /**
     *  post Mail
     * @param glblCmpyCd        String
     * @param bizId             String
     * @param tmplId            String
     * @param sbstStrList       List<NWXC001001MailSubstituteString>
     * @return Boolean
     */
    public Boolean  postMail(String glblCmpyCd, String bizId, String tmplId, List<NWXC001001MailSubstituteString> sbstStrList) {

        // Get From
        S21MailGroup groupFrom = new S21MailGroup(glblCmpyCd, CST_MAIL_GRP_ID_FROM);
        List<S21MailAddress> addrFromList = groupFrom.getMailAddress();

        S21MailAddress from = null;
        if (addrFromList.size() == 1) {
            from = addrFromList.get(0);
        } else {
            return Boolean.FALSE;
        }

        // Get To
        S21MailGroup groupTo = new S21MailGroup(glblCmpyCd, bizId);
        List<S21MailAddress> addrToList = groupTo.getMailAddress();
        if (addrToList.isEmpty()) {
            return Boolean.FALSE;
        }

        // Create Subject and Body
        S21MailTemplate template = new S21MailTemplate(glblCmpyCd, tmplId);
        if (template == null) {
            return Boolean.FALSE;
        }

        if( !sbstStrList.isEmpty() ){
            for( NWXC001001MailSubstituteString sbstStr : sbstStrList ) {
                template.setTemplateParameter( sbstStr.getSbstKey(), sbstStr.getSbstStr() );
            }
        }

        // Call Mail API
        S21Mail mail = new S21Mail(glblCmpyCd);
        mail.setFromAddress(from);
        mail.setToAddressList(addrToList);
        mail.setMailTemplate(template);
        mail.postMail();
        
        return Boolean.TRUE;
    }
    
    /**
     * post email. e-mail address get from userid list
     * @param glblCmpyCd            Global Company Code
     * @param userIdList            user id list
     * @param tmplId                templete ID
     * @param sbstStrList           mail parameter list
     * @param userProfileService    S21UserProfileService
     * @return
     */
    public Boolean postMail(String glblCmpyCd, List<String> userIdList, String tmplId, List<NWXC001001MailSubstituteString> sbstStrList, S21UserProfileService userProfileService) {
        // Get From
        S21MailGroup groupFrom = new S21MailGroup(glblCmpyCd, CST_MAIL_GRP_ID_FROM);
        List<S21MailAddress> addrFromList = groupFrom.getMailAddress();

        S21MailAddress from = null;
        if (addrFromList.size() == 1) {
            from = addrFromList.get(0);
        } else {
            return Boolean.FALSE;
        }

        // Get To
        List<S21MailAddress> addrToList = new ArrayList<S21MailAddress>();

        for (String userId : userIdList) {
            S21UserInfo userInfo = userProfileService.getUserInfoFor(userId);
            if (userInfo != null) {
                String mailAddress = userInfo.getEmailAddress();
                if (ZYPCommonFunc.hasValue(mailAddress)) {
                    S21MailAddress addressInfo = new S21MailAddress(glblCmpyCd, mailAddress);
                    addrToList.add(addressInfo);
                }
            }
        }
        if (addrToList.isEmpty()) {
            return Boolean.FALSE;
        }

        // Create Subject and Body
        S21MailTemplate template = new S21MailTemplate(glblCmpyCd, tmplId);
        if (template == null) {
            return Boolean.FALSE;
        }

        if( !sbstStrList.isEmpty() ){
            for( NWXC001001MailSubstituteString sbstStr : sbstStrList ) {
                template.setTemplateParameter( sbstStr.getSbstKey(), sbstStr.getSbstStr() );
            }
        }

        // Call Mail API
        S21Mail mail = new S21Mail(glblCmpyCd);
        mail.setFromAddress(from);
        mail.setToAddressList(addrToList);
        mail.setMailTemplate(template);
        mail.postMail();
        
        return Boolean.TRUE;
    }
}
