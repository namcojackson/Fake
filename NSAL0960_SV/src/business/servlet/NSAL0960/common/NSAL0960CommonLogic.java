/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0960.common;

import static business.servlet.NSAL0960.constant.NSAL0960Constant.AUTH_UPDATE;
import static business.servlet.NSAL0960.constant.NSAL0960Constant.BTN_CMN_APPLY;
import static business.servlet.NSAL0960.constant.NSAL0960Constant.BTN_CMN_APPROVE;
import static business.servlet.NSAL0960.constant.NSAL0960Constant.BTN_CMN_CLEAR;
import static business.servlet.NSAL0960.constant.NSAL0960Constant.BTN_CMN_DELETE;
import static business.servlet.NSAL0960.constant.NSAL0960Constant.BTN_CMN_DOWNROAD;
import static business.servlet.NSAL0960.constant.NSAL0960Constant.BTN_CMN_REJECT;
import static business.servlet.NSAL0960.constant.NSAL0960Constant.BTN_CMN_RESET;
import static business.servlet.NSAL0960.constant.NSAL0960Constant.BTN_CMN_RETURN;
import static business.servlet.NSAL0960.constant.NSAL0960Constant.BTN_CMN_SAVE;
import static business.servlet.NSAL0960.constant.NSAL0960Constant.BTN_CMN_SUBMIT;
import static business.servlet.NSAL0960.constant.NSAL0960Constant.BUSINESS_ID;
import static business.servlet.NSAL0960.constant.NSAL0960Constant.SCREEN_ID;

import java.util.List;

import parts.servletcommon.EZDCommonHandler;
import business.servlet.NSAL0960.NSAL0960BMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * Contract Validation List Setup
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/14   Hitachi         Y.Tsuchimoto    Create          N/A
 *</pre>
 */
public class NSAL0960CommonLogic {

    /**
     * <pre>
     * The initial state of the screen item is set.
     * </pre>
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0960BMsg
     */
    public static final void initialControlScreen(EZDCommonHandler handler, NSAL0960BMsg scrnMsg) {
        initCommonButton(handler, scrnMsg);
        controlScreenFields(handler, scrnMsg);

        S21TableColorController control = new S21TableColorController(SCREEN_ID, scrnMsg);
        control.setAlternateRowsBG("A", scrnMsg.A);
    }

    /**
     * Method name: initCommonButton <dd>The method explanation: init
     * Common Button Control. <dd>Remarks:
     * @param handler EZ Common Handler
     * @param scrnMsg NSAL0960BMsg
     */
    private static final void initCommonButton(EZDCommonHandler handler, NSAL0960BMsg scrnMsg) {
        handler.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 1, null);
        handler.setButtonProperties(BTN_CMN_APPLY[0], BTN_CMN_APPLY[1], BTN_CMN_APPLY[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_REJECT[0], BTN_CMN_REJECT[1], BTN_CMN_REJECT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DOWNROAD[0], BTN_CMN_DOWNROAD[1], BTN_CMN_DOWNROAD[2], 1, null);
        handler.setButtonProperties(BTN_CMN_DELETE[0], BTN_CMN_DELETE[1], BTN_CMN_DELETE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
        if (hasUpdateFuncId()) {
            handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 1, null);
            handler.setButtonEnabled("AddLine", true);
            handler.setButtonEnabled("DeleteLine", true);
        } else {
            handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 0, null);
            handler.setButtonEnabled("AddLine", false);
            handler.setButtonEnabled("DeleteLine", false);
        }
    }

    /**
     * Control screen fields
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0960BMsg
     */
    private static final void controlScreenFields(EZDCommonHandler handler, NSAL0960BMsg scrnMsg) {
        controlScreenTableFields(handler, scrnMsg);
    }

    /**
     * hasRegistFuncId
     * @return boolean true:upedate false:reference
     */
    public static boolean hasUpdateFuncId() {

        List<String> funcList = S21UserProfileServiceFactory.getInstance().getService().getFunctionCodeListForBizAppId(BUSINESS_ID);
        if (funcList == null || funcList.isEmpty()) {
            throw new S21AbendException("You can't operate Interface Maintenance (" + BUSINESS_ID + "). UserID is -> " + S21UserProfileServiceFactory.getInstance().getService().getContextUserInfo().getUserId());
        }

        for (String func : funcList) {
            if (AUTH_UPDATE.equals(func)) {
                return true;
            }
        }

        return false;
    }

    /**
     * controlScreenTableFields
     * @param scrnMsg NSAL0960BMsg
     */
    private static final void controlScreenTableFields(EZDCommonHandler handler, NSAL0960BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).vldSortNum_A.setInputProtected(false);
            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).xxExstFlg_A.getValue())) {
                handler.setButtonEnabled("OpenWin_VldCategory", i, false);
            } else {
                handler.setButtonEnabled("OpenWin_VldCategory", i, true);
            }
            scrnMsg.A.no(i).dsContrVldCatgDescTxt_A.setInputProtected(true);
            scrnMsg.A.no(i).dsContrVldNm_A.setInputProtected(true);
            scrnMsg.A.no(i).dsContrVldDescTxt_A.setInputProtected(true);
            scrnMsg.A.no(i).vldActCd_AS.setInputProtected(false);
            scrnMsg.A.no(i).effFromDt_A.setInputProtected(false);
            scrnMsg.A.no(i).effToDt_A.setInputProtected(false);
        }
    }

    /**
     * commonAddCheckItem
     * @param scrnMsg NSAL0960BMsg
     */
    public static void commonAddCheckItem(NSAL0960BMsg scrnMsg) {
        scrnMsg.addCheckItem(scrnMsg.dsContrVldListNm_H);
        scrnMsg.addCheckItem(scrnMsg.dsContrVldListDescTxt_H);
        scrnMsg.addCheckItem(scrnMsg.effFromDt_H);
        scrnMsg.addCheckItem(scrnMsg.effToDt_H);
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).vldSortNum_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).dsContrVldNm_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).dsContrVldCatgDescTxt_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).effFromDt_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).effToDt_A);
        }
    }
}
