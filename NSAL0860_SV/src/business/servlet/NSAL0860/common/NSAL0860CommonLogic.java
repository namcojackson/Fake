/**
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0860.common;

import static business.servlet.NSAL0860.constant.NSAL0860Constant.*;

import java.util.List;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import business.servlet.NSAL0860.NSAL0860BMsg;
import parts.servletcommon.EZDCommonHandler;

/**
 *<pre>
 * Register & Deregister Screen
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/06   Hitachi         Y.Osawa         Create          N/A
 * 2016/06/17   Hitachi         O.Okuma         Update          QC#9951
 *</pre>
 */
public class NSAL0860CommonLogic {

    /**
     * screenControlProcessForInit
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0860BMsg
     * @param functionList List<String>
     */
    public static void screenControlProcessForInit(EZDCommonHandler handler, NSAL0860BMsg scrnMsg, List<String> functionList) {

        scrnMsg.clearAllGUIAttribute(SCREEN_ID);

        protectButtonForFunction(handler, scrnMsg, functionList);
        initCommonItem(scrnMsg, functionList);
    }

    /**
     * screenControlProcess
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0860BMsg
     * @param functionList List<String>
     */
    public static void screenControlProcess(EZDCommonHandler handler, NSAL0860BMsg scrnMsg, List<String> functionList) {

        if (scrnMsg.A.getValidCount() == 0) {
            handler.setButtonEnabled(BTN_SELECT_ALL, false);
            handler.setButtonEnabled(BTN_UNSELECT_ALL, false);
            handler.setButtonEnabled(BTN_REGISTER, false);
            handler.setButtonEnabled(BTN_DEREGISTER, false);
        } else {

            if (isUpdFunc(functionList)) {
                // Inquiry
                handler.setButtonEnabled(BTN_SEARCH, true);
                handler.setButtonEnabled(BTN_SELECT_ALL, true);
                handler.setButtonEnabled(BTN_UNSELECT_ALL, true);
                handler.setButtonEnabled(BTN_REGISTER, true);
                handler.setButtonEnabled(BTN_DEREGISTER, true);
                handler.setButtonEnabled(BTN_UPLOAD, true);
            }

            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                if (scrnMsg.A.no(i).shrDlrFlg_A.getValue().equals(ZYPConstant.FLG_ON_Y)) {
                    scrnMsg.A.no(i).xxChkBox_A.setInputProtected(true);
                } else {
                    scrnMsg.A.no(i).xxChkBox_A.setInputProtected(false);
                }

                scrnMsg.A.no(i).t_MdlNm_A.setInputProtected(true);
                scrnMsg.A.no(i).shipToCustLocAddr_A.setInputProtected(true);
                scrnMsg.A.no(i).xxScrStsTxt_A.setInputProtected(true);
                scrnMsg.A.no(i).shrDlrFlg_A.setInputProtected(true);
                scrnMsg.A.no(i).dsAcctNm_A.setInputProtected(true);
                scrnMsg.A.no(i).curLocAcctNum_A.setInputProtected(true);
                scrnMsg.A.no(i).ugwActDescTxt_A.setInputProtected(true);
                scrnMsg.A.no(i).ugwActStsDescTxt_A.setInputProtected(true);
                scrnMsg.A.no(i).xxMsgTxt_A.setInputProtected(true);
            }
            setTableBGColor(scrnMsg);
        }
    }

    private static final void protectButtonForFunction(EZDCommonHandler handler, NSAL0860BMsg scrnMsg, List<String> functionList) {

        handler.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPLY[0], BTN_CMN_APPLY[1], BTN_CMN_APPLY[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_REJECT[0], BTN_CMN_REJECT[1], BTN_CMN_REJECT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DOWNROAD[0], BTN_CMN_DOWNROAD[1], BTN_CMN_DOWNROAD[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BLANK7[0], BTN_CMN_BLANK7[1], BTN_CMN_BLANK7[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);

        handler.setButtonEnabled(BTN_SEARCH, false);
        handler.setButtonEnabled(BTN_SELECT_ALL, false);
        handler.setButtonEnabled(BTN_UNSELECT_ALL, false);
        handler.setButtonEnabled(BTN_REGISTER, false);
        handler.setButtonEnabled(BTN_DEREGISTER, false);
        handler.setButtonEnabled(BTN_UPLOAD, false);

        if (isInqFunc(functionList)) {
            handler.setButtonEnabled(BTN_SEARCH, true);
        }

        if (isUpdFunc(functionList)) {
            // Inquiry
            handler.setButtonEnabled(BTN_SEARCH, true);
            handler.setButtonEnabled(BTN_SELECT_ALL, true);
            handler.setButtonEnabled(BTN_UNSELECT_ALL, true);
            handler.setButtonEnabled(BTN_UPLOAD, true);
            scrnMsg.xxFileData_U.setInputProtected(true);
        }
    }

    private static final void initCommonItem(NSAL0860BMsg scrnMsg, List<String> functionList) {
        scrnMsg.xxLinkAncr.setInputProtected(true);
        scrnMsg.xxFileData_U.setInputProtected(true);
        if (isUpdFunc(functionList)) {
            scrnMsg.xxFileData_U.setInputProtected(false);
            scrnMsg.xxLinkAncr.setInputProtected(false);
        }
    }

    private static boolean isInqFunc(List<String> functionList) {
        return functionList != null && !functionList.isEmpty() && functionList.contains(FUNC_ID_INQ);
    }

    private static boolean isUpdFunc(List<String> functionList) {
        if (functionList != null && !functionList.isEmpty()) {
            if (functionList.contains(FUNC_ID_UPD)) {
                return true;
            }
        }
        return false;
    }

    private static void setTableBGColor(NSAL0860BMsg scrnMsg) {

        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        if (scrnMsg.A.getValidCount() > 0) {
            tblColor.clearRowsBG("A", scrnMsg.A);
            tblColor.setAlternateRowsBG("A", scrnMsg.A);
        } else {
            tblColor.clearRowsBG("A", scrnMsg.A);
        }
    }

    /**
     * Clear Popup Parameter
     * @param scrnMsg NSAL0860BMsg
     */
    public static void clearPopupParameter(NSAL0860BMsg scrnMsg) {
        scrnMsg.xxPopPrm_00.clear();
        scrnMsg.xxPopPrm_01.clear();
        scrnMsg.xxPopPrm_02.clear();
        scrnMsg.xxPopPrm_03.clear();
        scrnMsg.xxPopPrm_04.clear();
        scrnMsg.xxPopPrm_05.clear();
        scrnMsg.xxPopPrm_06.clear();
        scrnMsg.xxPopPrm_07.clear();
        scrnMsg.xxPopPrm_08.clear();
        scrnMsg.xxPopPrm_09.clear();
        scrnMsg.dsContrPk_P.clear();
        scrnMsg.dsContrDtlPk_P.clear();

        ZYPTableUtil.clear(scrnMsg.O);
    }

    /**
     * Focus item
     * @param scrnMsg NSAL0860BMsg
     */
    public static void focusItem(NSAL0860BMsg scrnMsg) {
        scrnMsg.setFocusItem(scrnMsg.xxFileData_U);
    }
}
