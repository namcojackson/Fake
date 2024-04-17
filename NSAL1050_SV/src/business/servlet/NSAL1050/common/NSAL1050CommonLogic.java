/* <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre> */
package business.servlet.NSAL1050.common;

import static business.servlet.NSAL1050.constant.NSAL1050Constant.BIZ_ID;
import static business.servlet.NSAL1050.constant.NSAL1050Constant.BTN_ADD_LINE;
import static business.servlet.NSAL1050.constant.NSAL1050Constant.BTN_CMN_APPLY;
import static business.servlet.NSAL1050.constant.NSAL1050Constant.BTN_CMN_APPROVE;
import static business.servlet.NSAL1050.constant.NSAL1050Constant.BTN_CMN_CLEAR;
import static business.servlet.NSAL1050.constant.NSAL1050Constant.BTN_CMN_DELETE;
import static business.servlet.NSAL1050.constant.NSAL1050Constant.BTN_CMN_DOWNROAD;
import static business.servlet.NSAL1050.constant.NSAL1050Constant.BTN_CMN_REJECT;
import static business.servlet.NSAL1050.constant.NSAL1050Constant.BTN_CMN_RESET;
import static business.servlet.NSAL1050.constant.NSAL1050Constant.BTN_CMN_RETURN;
import static business.servlet.NSAL1050.constant.NSAL1050Constant.BTN_CMN_SAVE;
import static business.servlet.NSAL1050.constant.NSAL1050Constant.BTN_CMN_SUBMIT;
import static business.servlet.NSAL1050.constant.NSAL1050Constant.BTN_DELETE_LINE;
import static business.servlet.NSAL1050.constant.NSAL1050Constant.FUNC_ID_UPD;
import static business.servlet.NSAL1050.constant.NSAL1050Constant.SCREEN_ID;

import java.util.List;

import parts.servletcommon.EZDCommonHandler;
import business.servlet.NSAL1050.NSAL1050BMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_TERM_ATTRB_DATA_TP;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;

/**
 *<pre>
 * T&C Attributes Setup
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/11   Hitachi         T.Mizuki        Create          N/A
 * 2016/06/02   Hitachi         T.Tomita        Update          QC#5489
 * 2017/01/20   Hitachi         K.Ochiai        Update          QC#16331
 * 2018/06/25   Hitachi         A.Kohinata      Update          QC#17369
 *</pre>
 */
public class NSAL1050CommonLogic {

    /**
     * <pre>
     * The initial state of the screen item is set.
     * </pre>
     * @param userProfileService S21UserProfileService
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL1050BMsg
     */
    public static final void initialControlScreen(S21UserProfileService userProfileService, EZDCommonHandler handler, NSAL1050BMsg scrnMsg) {
        initCommonButton(userProfileService, handler, scrnMsg);
        controlScreenFields(handler, scrnMsg);
    }

    /**
     * Method name: initCommonButton <dd>The method explanation: init
     * Common Button Control. <dd>Remarks:
     * @param userProfileService S21UserProfileService
     * @param handler EZ Common Handler
     * @param scrnMsg NSAL1050BMsg
     */
    private static final void initCommonButton(S21UserProfileService userProfileService, EZDCommonHandler handler, NSAL1050BMsg scrnMsg) {

        handler.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPLY[0], BTN_CMN_APPLY[1], BTN_CMN_APPLY[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_REJECT[0], BTN_CMN_REJECT[1], BTN_CMN_REJECT[2], 0, null);
        // START 2017/01/20 K.Ochiai [QC#16331,MOD]
        handler.setButtonProperties(BTN_CMN_DOWNROAD[0], BTN_CMN_DOWNROAD[1], BTN_CMN_DOWNROAD[2], 1, null);
        handler.setButtonProperties(BTN_CMN_DELETE[0], BTN_CMN_DELETE[1], BTN_CMN_DELETE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 0, null);
        // END 2017/01/20 K.Ochiai [QC#16331,MOD]
        handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);

        List<String> functionIds = userProfileService.getFunctionCodeListForBizAppId(BIZ_ID);
        if (!functionIds.contains(FUNC_ID_UPD)) {
            handler.setButtonEnabled(BTN_CMN_SUBMIT[0], false);
            handler.setButtonEnabled(BTN_ADD_LINE[0], false);
            handler.setButtonEnabled(BTN_DELETE_LINE[0], false);
        } else {
            handler.setButtonEnabled(BTN_CMN_SUBMIT[0], true);
            handler.setButtonEnabled(BTN_ADD_LINE[0], true);
            handler.setButtonEnabled(BTN_DELETE_LINE[0], true);
        }
    }

    /**
     * Control screen fields
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL1050BMsg
     */
    private static final void controlScreenFields(EZDCommonHandler handler, NSAL1050BMsg scrnMsg) {

        scrnMsg.clearAllGUIAttribute(SCREEN_ID);
        scrnMsg.setInputProtected(false);
    }

    /**
     * set Row Colors
     * @param scrnMsg NSAL1050BMsg
     */
    public static void setRowColors(NSAL1050BMsg scrnMsg) {

        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        tblColor.setAlternateRowsBG("A", scrnMsg.A);

    }

    /**
     * Control screen List
     * @param scrnMsg NSAL1050BMsg
     */
    public static void controlScreenList(NSAL1050BMsg scrnMsg) {

        if (scrnMsg.A.getValidCount() > 0) {
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).svcTermCondAttrbPk_A)) {
                    scrnMsg.A.no(i).xxChkBox_A.setInputProtected(true);
                }
                scrnMsg.A.no(i).svcTermCondSrcDescTxt_A.setInputProtected(true);
            }
            NSAL1050CommonLogic.setRowColors(scrnMsg);
            scrnMsg.setFocusItem(scrnMsg.A.no(0).svcTermAttrbDescTxt_A);
        } else {
            scrnMsg.setFocusItem(scrnMsg.svcTermAttrbDescTxt);
        }
    }

    /**
     * Control screen List
     * @param scrnMsg NSAL1050BMsg
     * @param handler EZDCommonHandler
     */
    public static void controlLovDdfList(NSAL1050BMsg scrnMsg, EZDCommonHandler handler) {

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            // START 2016/06/02 T.Tomita [QC#5489, MOD]
            String svcTermDataTpCd = scrnMsg.A.no(i).svcTermDataTpCd_1V.getValue();
            // mod start 2018/06/25 QC#17369
            if (!SVC_TERM_ATTRB_DATA_TP.DROPDOWN.equals(svcTermDataTpCd) && !SVC_TERM_ATTRB_DATA_TP.LOOKUP.equals(svcTermDataTpCd) && !SVC_TERM_ATTRB_DATA_TP.LOOKUP_POPUP.equals(svcTermDataTpCd)) {
            // mod end 2018/06/25 QC#17369
                scrnMsg.A.no(i).svcTermCondSrcDescTxt_A.clear();
                scrnMsg.A.no(i).svcTermCondDataSrcCd_A.clear();
                handler.setButtonEnabled("OpenWin_TandC_Source", i, false);
            } else {
                handler.setButtonEnabled("OpenWin_TandC_Source", i, true);
            }
            // END 2016/06/02 T.Tomita [QC#5489, MOD]
        }
    }
}
