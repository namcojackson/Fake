/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0550.common;

import static business.servlet.NSAL0550.constant.NSAL0550Constant.*;

import java.util.List;

import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

import parts.servletcommon.EZDCommonHandler;
import business.servlet.NSAL0550.NSAL0550BMsg;
import business.servlet.NSAL0550.NSAL0550Bean;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/18   Hitachi         Y.Takeno        Create          N/A
 * 2016/11/21   Hitachi         T.Mizuki        Update          QC#16116
 * 2017/01/18   Hitachi         K.Ochiai        Update          QC#16331
 * 2018/06/11   Fujitsu         M.Ohno          Update          QC#22381
 *</pre>
 */
public class NSAL0550CommonLogic {

    /**
     * <pre>
     * The initial state of the screen item is set.
     * </pre>
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0550BMsg
     */
    public static final void initialControlScreen(EZDCommonHandler handler, NSAL0550BMsg scrnMsg) {
        initCommonButton(handler, scrnMsg);
    }

    private static final void initCommonButton(EZDCommonHandler handler, NSAL0550BMsg scrnMsg) {
        handler.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPLY[0], BTN_CMN_APPLY[1], BTN_CMN_APPLY[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_REJECT[0], BTN_CMN_REJECT[1], BTN_CMN_REJECT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DOWNLOAD[0], BTN_CMN_DOWNLOAD[1], BTN_CMN_DOWNLOAD[2], 1, null);
        handler.setButtonProperties(BTN_CMN_DELETE[0], BTN_CMN_DELETE[1], BTN_CMN_DELETE[2], 0, null);
        // START 2017/01/18 K.Ochiai [QC#16331, MOD]
        handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 1, null);
        // END 2017/01/18 K.Ochiai [QC#16331, MOD]
        handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
        // mod start 2016/11/21 CSA QC#16116
        if (!hasUpdateFuncId()) {
            handler.setButtonEnabled("CreditAndRebill", false);
        }
    }

    /**
     * hasRegistFuncId
     * @return boolean true:upedate false:reference
     */
    public static boolean hasUpdateFuncId() {

        List<String> funcList = S21UserProfileServiceFactory.getInstance().getService().getFunctionCodeListForBizAppId(BUSINESS_ID);
        if (funcList == null || funcList.isEmpty()) {
            throw new S21AbendException("You can't operate Renewal Rules contract/machine level(" + BUSINESS_ID + "). UserID is -> "
                    + S21UserProfileServiceFactory.getInstance().getService().getContextUserInfo().getUserId());
        }

        for (String func : funcList) {
            if (AUTH_CONTR_MGR.equals(func)) {
                return true;
            }
        }

        return false;
    }
    // mod end 2016/11/21 CSA QC#16116

    // START 2018/06/11 M.Ohno [QC#22381, ADD]
    public static void alternateTableRowColor(NSAL0550BMsg scrnMsg) {
        S21TableColorController control = new S21TableColorController(SCREEN_ID, scrnMsg);
        control.setAlternateRowsBG(NSAL0550Bean.A, scrnMsg.A);
    }
    // END 2018/06/11 M.Ohno [QC#22381, ADD]
}
