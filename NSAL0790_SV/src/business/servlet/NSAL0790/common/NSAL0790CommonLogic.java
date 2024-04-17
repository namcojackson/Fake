/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0790.common;

import static business.servlet.NSAL0790.constant.NSAL0790Constant.*;

import java.util.List;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

import parts.common.EZDBMsgArray;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NSAL0790.NSAL0790BMsg;

/**
 *<pre>
 * Fleet Rollup Detail
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/22   Hitachi         A.Kohinata      Create          N/A
 *</pre>
 */
public class NSAL0790CommonLogic {

    /**
     * The initial state of the screen item is set.
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0790BMsg
     */
    public static final void initialControlScreen(EZDCommonHandler handler, NSAL0790BMsg scrnMsg) {

        initControlCommonButton(handler);
        controlScreenFields(handler, scrnMsg);
    }

    /**
     * init Control Common Button
     * @param handler EZDCommonHandler
     */
    public static void initControlCommonButton(EZDCommonHandler handler) {

        handler.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPLY[0], BTN_CMN_APPLY[1], BTN_CMN_APPLY[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_REJECT[0], BTN_CMN_REJECT[1], BTN_CMN_REJECT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DOWNROAD[0], BTN_CMN_DOWNROAD[1], BTN_CMN_DOWNROAD[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BLANK7[0], BTN_CMN_BLANK7[1], BTN_CMN_BLANK7[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
    }

    /**
     * control screen fields
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0790BMsg
     */
    private static final void controlScreenFields(EZDCommonHandler handler, NSAL0790BMsg scrnMsg) {

        if (hasUpdateFuncId() && ZYPCommonFunc.hasValue(scrnMsg.fleetCalcProcCd_H) && scrnMsg.A.getValidCount() > 0) {
            handler.setButtonEnabled(BTN_RESUBMIT_ROLLUP, true);
        } else {
            handler.setButtonEnabled(BTN_RESUBMIT_ROLLUP, false);
        }

        scrnMsg.dsContrNum_H.setInputProtected(true);
        scrnMsg.fleetCalcProcDescTxt_H.setInputProtected(true);
        scrnMsg.vldMsgTxt_H.setInputProtected(true);

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).serNum_A.setInputProtected(true);
            scrnMsg.A.no(i).mtrLbDescTxt_A.setInputProtected(true);
            scrnMsg.A.no(i).fleetCalcProcDescTxt_A.setInputProtected(true);
            scrnMsg.A.no(i).vldMsgTxt_A.setInputProtected(true);
            handler.setButtonEnabled(BTN_ENTRY, i, true);
        }

        if (scrnMsg.A.getValidCount() > 0) {
            NSAL0790CommonLogic.setRowColors(scrnMsg);
        }
    }

    /**
     * hasRegistFuncId
     * @return boolean true:upedate false:reference
     */
    private static boolean hasUpdateFuncId() {

        List<String> funcList = S21UserProfileServiceFactory.getInstance().getService().getFunctionCodeListForBizAppId(BIZ_ID);
        if (funcList == null || funcList.isEmpty()) {
            throw new S21AbendException("You can't operate Supplemantal Task (" + BIZ_ID + "). UserID is -> " + S21UserProfileServiceFactory.getInstance().getService().getContextUserInfo().getUserId());
        }

        if (funcList.contains(AUTH_UPDATE)) {
            return true;
        }
        return false;
    }

    /**
     * set Row Colors
     * @param scrnMsg NSAL0790BMsg
     */
    public static void setRowColors(NSAL0790BMsg scrnMsg) {

        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        try {
            EZDBMsgArray table = (EZDBMsgArray) scrnMsg.getClass().getField("A").get(scrnMsg);
            tblColor.setAlternateRowsBG("A", table);
        } catch (Throwable e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
