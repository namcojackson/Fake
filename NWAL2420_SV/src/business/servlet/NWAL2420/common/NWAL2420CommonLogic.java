/*
 * <pre>Copyright (c) 2018 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2420.common;

import static business.servlet.NWAL2420.constant.NWAL2420Constant.AUTHORIZATION_COMMENT;
import static business.servlet.NWAL2420.constant.NWAL2420Constant.BTN_08_CLE_GUARD;
import static business.servlet.NWAL2420.constant.NWAL2420Constant.BTN_08_CLE_LABEL;
import static business.servlet.NWAL2420.constant.NWAL2420Constant.BTN_08_CLE_NAME;
import static business.servlet.NWAL2420.constant.NWAL2420Constant.BTN_10_CLS_GUARD;
import static business.servlet.NWAL2420.constant.NWAL2420Constant.BTN_10_CLS_LABEL;
import static business.servlet.NWAL2420.constant.NWAL2420Constant.BTN_10_CLS_NAME;
import static business.servlet.NWAL2420.constant.NWAL2420Constant.EMAIL_ADDRESS;
import static business.servlet.NWAL2420.constant.NWAL2420Constant.EMAIL_FORMAT;
import static business.servlet.NWAL2420.constant.NWAL2420Constant.MAX_COMMENT_LENGTH;
import static business.servlet.NWAL2420.constant.NWAL2420Constant.NWAM0186E;
import static business.servlet.NWAL2420.constant.NWAL2420Constant.NWAM0664E;
import static business.servlet.NWAL2420.constant.NWAL2420Constant.NWAM0954E;
import static business.servlet.NWAL2420.constant.NWAL2420Constant.ZZZM9025E;
import business.servlet.NWAL2420.NWAL2420BMsg;
import business.servlet.NWAL2420.NWAL2420_ABMsgArray;

import com.canon.cusa.s21.common.NMX.NMXC106001.NMXC106001CommonCheckUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL2420CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/05/14   CUSA            H.Tomimatsu     Create          S21_NA#22139
 *</pre>
 */
public class NWAL2420CommonLogic {

    /**
     * Initial Common Button properties.
     * @param handler Event Handler
     */
    public static void initButton(S21CommonHandler handler) {

        handler.setButtonProperties(BTN_08_CLE_NAME, BTN_08_CLE_GUARD, BTN_08_CLE_LABEL, 1, null);
        handler.setButtonProperties(BTN_10_CLS_NAME, BTN_10_CLS_GUARD, BTN_10_CLS_LABEL, 1, null);

    }

    /**
     * Checking EMail
     * @param scrnMsg NWAL2420BMsg
     */
    public static void checkEMail(NWAL2420BMsg scrnMsg) {

        NWAL2420_ABMsgArray abMsgArray = scrnMsg.A;
        boolean checkFlag = false;

        for (int idx = 0; idx < abMsgArray.getValidCount(); idx++) {
            if (ZYPCommonFunc.hasValue(abMsgArray.no(idx).ctacPsnEmlAddr_A)) {

                boolean ret = NMXC106001CommonCheckUtil.checkEmailFormat(abMsgArray.no(idx).ctacPsnEmlAddr_A.getValue());

                if (!ret) {
                    abMsgArray.no(idx).ctacPsnEmlAddr_A.setErrorInfo(1, NWAM0664E, new String[] {EMAIL_FORMAT });
                }
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

    /**
     * Checking Comment
     * @param scrnMsg NWAL2420BMsg
     */
    public static void checkComment(NWAL2420BMsg scrnMsg) {

        if (scrnMsg.rtrnAuthCmntTxt_01.getValue().length() > MAX_COMMENT_LENGTH) {
            scrnMsg.rtrnAuthCmntTxt_01.setErrorInfo(1, NWAM0954E, new String[] {AUTHORIZATION_COMMENT });
        }

    }

}
