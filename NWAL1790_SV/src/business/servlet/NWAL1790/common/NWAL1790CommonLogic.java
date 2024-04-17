/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1790.common;

import static business.servlet.NWAL1790.constant.NWAL1790Constant.BTN_08_CLE_GUARD;
import static business.servlet.NWAL1790.constant.NWAL1790Constant.BTN_08_CLE_LABEL;
import static business.servlet.NWAL1790.constant.NWAL1790Constant.BTN_08_CLE_NAME;
import static business.servlet.NWAL1790.constant.NWAL1790Constant.BTN_10_CLS_GUARD;
import static business.servlet.NWAL1790.constant.NWAL1790Constant.BTN_10_CLS_LABEL;
import static business.servlet.NWAL1790.constant.NWAL1790Constant.BTN_10_CLS_NAME;
//import static business.servlet.NWAL1790.constant.NWAL1790Constant.CHK_EMAIL_PATTERN;
import static business.servlet.NWAL1790.constant.NWAL1790Constant.EMAIL_ADDRESS;
import static business.servlet.NWAL1790.constant.NWAL1790Constant.EMAIL_FORMAT;
import static business.servlet.NWAL1790.constant.NWAL1790Constant.NWAM0186E;
import static business.servlet.NWAL1790.constant.NWAL1790Constant.NWAM0664E;
import static business.servlet.NWAL1790.constant.NWAL1790Constant.ZZZM9025E;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NWAL1790.NWAL1790BMsg;
import business.servlet.NWAL1790.NWAL1790_ABMsgArray;

import com.canon.cusa.s21.common.NMX.NMXC106001.NMXC106001CommonCheckUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;

/**
 *<pre>
 * NWAL1790CommonLogic
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/24   CUSA             T.Murai        Create          N/A
 * 2016/10/06   Fujitsu          Mz.Takahashi   Update          QC#14431
 *</pre>
 */
public class NWAL1790CommonLogic {

    /**
     * Set Button Enable Init
     * @param handler EZDCommonHandler
     */
    public static void initButton(EZDCommonHandler handler) {
        handler.setButtonProperties(BTN_08_CLE_NAME, BTN_08_CLE_GUARD, BTN_08_CLE_LABEL, 1, null);
        handler.setButtonProperties(BTN_10_CLS_NAME, BTN_10_CLS_GUARD, BTN_10_CLS_LABEL, 1, null);
    }

    /**
     * Checking EMail
     * @param scrnMsg NWAL1790BMsg
     */
    public static void checkEMail(NWAL1790BMsg scrnMsg) {

        NWAL1790_ABMsgArray abMsgArray = scrnMsg.A;
        boolean checkFlag = false;

        for (int idx = 0; idx < abMsgArray.getValidCount(); idx++) {
            if (ZYPCommonFunc.hasValue(abMsgArray.no(idx).ctacPsnEmlAddr_A)) {
                // 2016/10/06 QC#1443 Add Start --------------
                boolean ret = NMXC106001CommonCheckUtil.checkEmailFormat(abMsgArray.no(idx).ctacPsnEmlAddr_A.getValue());

                if (!ret){
                    abMsgArray.no(idx).ctacPsnEmlAddr_A.setErrorInfo(1, NWAM0664E, new String[] {EMAIL_FORMAT });
                }
                // 2016/10/06 QC#1443 Add End --------------
                //if (!abMsgArray.no(idx).ctacPsnEmlAddr_A.getValue().matches(CHK_EMAIL_PATTERN)) {
                //    abMsgArray.no(idx).ctacPsnEmlAddr_A.setErrorInfo(1, NWAM0664E, new String[] {EMAIL_FORMAT });
                //}
            }

            if (!abMsgArray.no(idx).xxChkBox_A.isClear()) {
                checkFlag = true;
            }

            if (!ZYPCommonFunc.hasValue(abMsgArray.no(idx).ctacPsnEmlAddr_A) && !abMsgArray.no(idx).xxChkBox_A.isClear()) {
                abMsgArray.no(idx).ctacPsnEmlAddr_A.setErrorInfo(1, ZZZM9025E, new String[] {EMAIL_ADDRESS });
            }
        }

        if (!checkFlag) {
            for (int idx = 0; idx < abMsgArray.getValidCount(); idx++) {
                abMsgArray.no(idx).xxChkBox_A.setErrorInfo(1, NWAM0186E);
            }
        }
    }
}
