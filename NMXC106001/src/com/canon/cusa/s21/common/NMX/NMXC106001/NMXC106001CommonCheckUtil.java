package com.canon.cusa.s21.common.NMX.NMXC106001;

import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;
import com.canon.cusa.s21.framework.ZYP.mail.ZYPMail;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/10/05   Fujitsu         Mz.Takahashi    Create          CSA-QC#14431
 * 2020/12/23   CITS            J.Evangelista   Update          QC#57543
 *</pre>
 */
public class NMXC106001CommonCheckUtil extends S21SsmEZDQuerySupport {

    /**
     * checkEmailFormat
     * @param mailAddr
     * @return
     */
    public static boolean checkEmailFormat(String mailAddr) {
        boolean ret = true;

        // START 2020/12/23 J.Evangelista [QC#57543,MOD]
//        try {
//            InternetAddress addr = new InternetAddress(mailAddr);
//            addr.validate();
//        } catch (AddressException e) {
//            ret = false;
//        }
        ret = ZYPMail.checkEmailFormat(mailAddr);
        // END  2020/12/23 J.Evangelista [QC#57543,MOD]

        return ret;
    }
}
