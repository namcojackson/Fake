/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWCL0060.common;

import java.util.ArrayList;
import java.util.List;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.mail.S21MailAddress;

import business.blap.NWCL0060.NWCL0060CMsg;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/05/25   Fujitsu         M.Nakamura      Create          N/A
 *</pre>
 */
public class NWCL0060CommonLogic {

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed. String mailAddress
     * @param mailAddress String
     * @param bizMsg Business Component Interface Message
     * @param glblCmpyCd String
     * @return List
     */
    public static List<S21MailAddress> getMailList(String mailAddress, NWCL0060CMsg bizMsg, String glblCmpyCd) {

        List<S21MailAddress> mailAddrList = new ArrayList<S21MailAddress>();
        if (!mailAddress.contains("@")) {
            return null;
        }

        String[] mailArray = mailAddress.split(",");
        S21MailAddress mailAddr = null;

        for (int i = 0; i < mailArray.length; i++) {
            if (!ZYPCommonFunc.hasValue(mailArray[i])) {
                continue;
            } else if (mailArray[i].contains("@")) {
                mailAddr = new S21MailAddress(glblCmpyCd, mailArray[i]);
                mailAddrList.add(mailAddr);
            } else {
                return null;
            }
        }
        return mailAddrList;
    }
}
