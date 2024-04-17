/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWCL0060.common;

import static business.servlet.NWCL0060.constant.NWCL0060Constant.BTN_08_CLE_GUARD;
import static business.servlet.NWCL0060.constant.NWCL0060Constant.BTN_08_CLE_LABEL;
import static business.servlet.NWCL0060.constant.NWCL0060Constant.BTN_08_CLE_NAME;
import static business.servlet.NWCL0060.constant.NWCL0060Constant.BTN_10_CLS_GUARD;
import static business.servlet.NWCL0060.constant.NWCL0060Constant.BTN_10_CLS_LABEL;
import static business.servlet.NWCL0060.constant.NWCL0060Constant.BTN_10_CLS_NAME;
import static business.servlet.NWCL0060.constant.NWCL0060Constant.BTN_CANCEL_LABEL;
import static business.servlet.NWCL0060.constant.NWCL0060Constant.BTN_SEND_MAIL_LABEL;
import business.servlet.NWCL0060.NWCL0060BMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/05/25   Fujitsu         M.Nakamura      Create          N/A
 * 2022/03/31   CITS            A.Cullano       Update          QC#59828
 *</pre>
 */
public class NWCL0060CommonLogic {

    /**
     * Initial Common Button properties.
     * 
     * @param handler Event Handler
     */
    public static void initCmnBtnProp(S21CommonHandler handler) {
        // 4th parameter. (0:Inactive, 1:Active)
        handler.setButtonProperties(BTN_08_CLE_NAME, BTN_08_CLE_GUARD, BTN_08_CLE_LABEL, 0, null);
        handler.setButtonProperties(BTN_10_CLS_NAME, BTN_10_CLS_GUARD, BTN_10_CLS_LABEL, 1, null);
    }

    /**
     * Set Screen Control.
     * 
     * @param scrnMsg NWCL0060BMsg
     * @param isProtect boolean
     * @param handler S21CommonHandler
     */
    public static void setScreenControl(NWCL0060BMsg scrnMsg, boolean isProtect, S21CommonHandler handler) {

        // START 2022/03/31 A.Cullano [QC#59828, ADD]
        scrnMsg.xxRadioBtn_H1.setInputProtected(isProtect);
        // END 2022/03/31 A.Cullano [QC#59828, ADD]
        scrnMsg.emlAddr_H1.setInputProtected(isProtect);
        scrnMsg.mlSubjTxt_H1.setInputProtected(true);
        scrnMsg.xxArMlBodyTmplTxt_H1.setInputProtected(true);
        scrnMsg.xxMlCmntTxt_H1.setInputProtected(isProtect);

        boolean isEnable = true;
        if (isProtect) {
            isEnable = false;
        }
        handler.setButtonEnabled(BTN_SEND_MAIL_LABEL, isEnable);
        handler.setButtonEnabled(BTN_CANCEL_LABEL, isEnable);
    }
}
