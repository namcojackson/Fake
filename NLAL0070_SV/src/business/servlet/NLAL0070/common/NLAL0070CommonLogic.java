/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2010/03/15   CUSA            FJ)A.Akabane    Create          N/A
 * 2010/04/19   CUSA            FJ)R.Mori       Update          4984
 * 2013/06/03   Hitachi         T.Tomita        Update          QC1209
 * 2017/01/16   CITS            T.Kikuhara      Update          QC#16256
 *</pre>
 */
package business.servlet.NLAL0070.common;

import java.util.List;

import parts.servletcommon.EZDCommonHandler;
import business.servlet.NLAL0070.NLAL0070BMsg;
import business.servlet.NLAL0070.constant.NLAL0070Constant;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

public class NLAL0070CommonLogic implements NLAL0070Constant{

    /**
     * @param handler EZDCommonHandler
     * @param scrnMsg NLAL0070BMsg
     */
    public static void cntrlDispScrnItem(EZDCommonHandler handler, NLAL0070BMsg scrnMsg) {

        scrnMsg.clearAllGUIAttribute(SCRN_ID);

        if (TB_SCHEDULE.equals(scrnMsg.xxDplyTab_BK.getValue()) ) {
            if (scrnMsg.A.getValidCount() > 0) {
                cntrlDispScrnItemForScheduleSearch(handler, scrnMsg);
                cntrlScdlFinalCheckBox(scrnMsg);
            } else {
                cntrlDispScrnItemForScheduleInit(handler, scrnMsg);
            }
        } else if (TB_SUMMARY.equals(scrnMsg.xxDplyTab_BK.getValue())) {
            if (scrnMsg.B.getValidCount() > 0) {
                cntrlDispScrnItemForSummarySearch(handler, scrnMsg);
            } else {
                if (ZYPCommonFunc.hasValue(scrnMsg.xxRefTblDtTxt_B1)) {
                    cntrlDispScrnItemForSummaryPrevNext(handler, scrnMsg);
                } else {
                    cntrlDispScrnItemForSummaryInit(handler, scrnMsg);
                }
            }
        }
    }
    
    /**
     * @param handler EZDCommonHandler
     * @param scrnMsg NLAL0070BMsg
     */
    private static void cntrlDispScrnItemForScheduleInit(EZDCommonHandler handler, NLAL0070BMsg scrnMsg) {

        scrnMsg.clearAllGUIAttribute(SCRN_ID);

        handler.setButtonEnabled("CheckAll",   false);
        handler.setButtonEnabled("UncheckAll", false);
        handler.setButtonEnabled("OpenSubWin_Inbound_Inquiry", true);

        handler.setButtonProperties(BTN_CMN_SAVE[0],     BTN_CMN_SAVE[1],     BTN_CMN_SAVE[2],     0, null);
        handler.setButtonProperties(BTN_CMN_SUBMIT[0],   BTN_CMN_SUBMIT[1],   BTN_CMN_SUBMIT[2],   0, null);
        handler.setButtonProperties(BTN_CMN_APPLY[0],    BTN_CMN_APPLY[1],    BTN_CMN_APPLY[2],    0, null);
        handler.setButtonProperties(BTN_CMN_APPROVE[0],  BTN_CMN_APPROVE[1],  BTN_CMN_APPROVE[2],  0, null);
        handler.setButtonProperties(BTN_CMN_REJECT[0],   BTN_CMN_REJECT[1],   BTN_CMN_REJECT[2],   0, null);
        handler.setButtonProperties(BTN_CMN_DOWNLOAD[0], BTN_CMN_DOWNLOAD[1], BTN_CMN_DOWNLOAD[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DELETE[0],   BTN_CMN_DELETE[1],   BTN_CMN_DELETE[2],   0, null);
        handler.setButtonProperties(BTN_CMN_CLEAR[0],    BTN_CMN_CLEAR[1],    BTN_CMN_CLEAR[2],    0, null);
        handler.setButtonProperties(BTN_CMN_RESET[0],    BTN_CMN_RESET[1],    BTN_CMN_RESET[2],    1, null);
        handler.setButtonProperties(BTN_CMN_RETURN[0],   BTN_CMN_RETURN[1],   BTN_CMN_RETURN[2],   1, null);
    }

    /**
     * @param handler EZDCommonHandler
     * @param scrnMsg NLAL0070BMsg
     */
    private static void cntrlDispScrnItemForScheduleSearch(EZDCommonHandler handler, NLAL0070BMsg scrnMsg) {

        scrnMsg.clearAllGUIAttribute(SCRN_ID);
        S21TableColorController tblColor = new S21TableColorController(SCRN_ID, scrnMsg);
        tblColor.setAlternateRowsBG(TBL_ID_A_LEFT,  scrnMsg.A);
        tblColor.setAlternateRowsBG(TBL_ID_A_RIGHT, scrnMsg.A);

        handler.setButtonEnabled("CheckAll",   true);
        handler.setButtonEnabled("UncheckAll", true);
        handler.setButtonEnabled("OpenSubWin_Inbound_Inquiry", true);

        handler.setButtonProperties(BTN_CMN_SAVE[0],     BTN_CMN_SAVE[1],     BTN_CMN_SAVE[2],     0, null);
        // QC#16256 add start
        if (isUpdatable()) {
            handler.setButtonProperties(BTN_CMN_SUBMIT[0],   BTN_CMN_SUBMIT[1],   BTN_CMN_SUBMIT[2],   1, null);
        } else {
            handler.setButtonProperties(BTN_CMN_SUBMIT[0],   BTN_CMN_SUBMIT[1],   BTN_CMN_SUBMIT[2],   0, null);
        }
        // QC#16256 add end
        handler.setButtonProperties(BTN_CMN_APPLY[0],    BTN_CMN_APPLY[1],    BTN_CMN_APPLY[2],    0, null);
        handler.setButtonProperties(BTN_CMN_APPROVE[0],  BTN_CMN_APPROVE[1],  BTN_CMN_APPROVE[2],  0, null);
        handler.setButtonProperties(BTN_CMN_REJECT[0],   BTN_CMN_REJECT[1],   BTN_CMN_REJECT[2],   0, null);
        handler.setButtonProperties(BTN_CMN_DOWNLOAD[0], BTN_CMN_DOWNLOAD[1], BTN_CMN_DOWNLOAD[2], 1, null);
        handler.setButtonProperties(BTN_CMN_DELETE[0],   BTN_CMN_DELETE[1],   BTN_CMN_DELETE[2],   0, null);
        handler.setButtonProperties(BTN_CMN_CLEAR[0],    BTN_CMN_CLEAR[1],    BTN_CMN_CLEAR[2],    0, null);
        handler.setButtonProperties(BTN_CMN_RESET[0],    BTN_CMN_RESET[1],    BTN_CMN_RESET[2],    1, null);
        handler.setButtonProperties(BTN_CMN_RETURN[0],   BTN_CMN_RETURN[1],   BTN_CMN_RETURN[2],   1, null);
    }

    /**
     * @param handler EZDCommonHandler
     * @param scrnMsg NLAL0070BMsg
     */
    private static void cntrlDispScrnItemForSummaryInit(EZDCommonHandler handler, NLAL0070BMsg scrnMsg) {

        scrnMsg.clearAllGUIAttribute(SCRN_ID);

        handler.setButtonEnabled("PrevWeek", false);
        handler.setButtonEnabled("NextWeek", false);

        handler.setButtonProperties(BTN_CMN_SAVE[0],     BTN_CMN_SAVE[1],     BTN_CMN_SAVE[2],     0, null);
        handler.setButtonProperties(BTN_CMN_SUBMIT[0],   BTN_CMN_SUBMIT[1],   BTN_CMN_SUBMIT[2],   0, null);
        handler.setButtonProperties(BTN_CMN_APPLY[0],    BTN_CMN_APPLY[1],    BTN_CMN_APPLY[2],    0, null);
        handler.setButtonProperties(BTN_CMN_APPROVE[0],  BTN_CMN_APPROVE[1],  BTN_CMN_APPROVE[2],  0, null);
        handler.setButtonProperties(BTN_CMN_REJECT[0],   BTN_CMN_REJECT[1],   BTN_CMN_REJECT[2],   0, null);
        handler.setButtonProperties(BTN_CMN_DOWNLOAD[0], BTN_CMN_DOWNLOAD[1], BTN_CMN_DOWNLOAD[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DELETE[0],   BTN_CMN_DELETE[1],   BTN_CMN_DELETE[2],   0, null);
        handler.setButtonProperties(BTN_CMN_CLEAR[0],    BTN_CMN_CLEAR[1],    BTN_CMN_CLEAR[2],    0, null);
        handler.setButtonProperties(BTN_CMN_RESET[0],    BTN_CMN_RESET[1],    BTN_CMN_RESET[2],    1, null);
        handler.setButtonProperties(BTN_CMN_RETURN[0],   BTN_CMN_RETURN[1],   BTN_CMN_RETURN[2],   1, null);
    }

    /**
     * @param handler EZDCommonHandler
     * @param scrnMsg NLAL0070BMsg
     */
    private static void cntrlDispScrnItemForSummarySearch(EZDCommonHandler handler, NLAL0070BMsg scrnMsg) {

        scrnMsg.clearAllGUIAttribute(SCRN_ID);
        S21TableColorController tblColor = new S21TableColorController(SCRN_ID, scrnMsg);
        tblColor.setAlternateRowsBG(TBL_ID_B_LEFT,  scrnMsg.B);
        tblColor.setAlternateRowsBG(TBL_ID_B_RIGHT, scrnMsg.B); 

        handler.setButtonEnabled("PrevWeek", true);
        handler.setButtonEnabled("NextWeek", true);

        handler.setButtonProperties(BTN_CMN_SAVE[0],     BTN_CMN_SAVE[1],     BTN_CMN_SAVE[2],     0, null);
        handler.setButtonProperties(BTN_CMN_SUBMIT[0],   BTN_CMN_SUBMIT[1],   BTN_CMN_SUBMIT[2],   0, null);
        handler.setButtonProperties(BTN_CMN_APPLY[0],    BTN_CMN_APPLY[1],    BTN_CMN_APPLY[2],    0, null);
        handler.setButtonProperties(BTN_CMN_APPROVE[0],  BTN_CMN_APPROVE[1],  BTN_CMN_APPROVE[2],  0, null);
        handler.setButtonProperties(BTN_CMN_REJECT[0],   BTN_CMN_REJECT[1],   BTN_CMN_REJECT[2],   0, null);
        handler.setButtonProperties(BTN_CMN_DOWNLOAD[0], BTN_CMN_DOWNLOAD[1], BTN_CMN_DOWNLOAD[2], 1, null);
        handler.setButtonProperties(BTN_CMN_DELETE[0],   BTN_CMN_DELETE[1],   BTN_CMN_DELETE[2],   0, null);
        handler.setButtonProperties(BTN_CMN_CLEAR[0],    BTN_CMN_CLEAR[1],    BTN_CMN_CLEAR[2],    0, null);
        handler.setButtonProperties(BTN_CMN_RESET[0],    BTN_CMN_RESET[1],    BTN_CMN_RESET[2],    1, null);
        handler.setButtonProperties(BTN_CMN_RETURN[0],   BTN_CMN_RETURN[1],   BTN_CMN_RETURN[2],   1, null);
    }

    /**
     * @param handler EZDCommonHandler
     * @param scrnMsg NLAL0070BMsg
     */
    private static void cntrlDispScrnItemForSummaryPrevNext(EZDCommonHandler handler, NLAL0070BMsg scrnMsg) {

        scrnMsg.clearAllGUIAttribute(SCRN_ID);
        S21TableColorController tblColor = new S21TableColorController(SCRN_ID, scrnMsg);
        tblColor.setAlternateRowsBG(TBL_ID_B_LEFT,  scrnMsg.B);
        tblColor.setAlternateRowsBG(TBL_ID_B_RIGHT, scrnMsg.B);

        handler.setButtonEnabled("PrevWeek", true);
        handler.setButtonEnabled("NextWeek", true);

        handler.setButtonProperties(BTN_CMN_SAVE[0],     BTN_CMN_SAVE[1],     BTN_CMN_SAVE[2],     0, null);
        handler.setButtonProperties(BTN_CMN_SUBMIT[0],   BTN_CMN_SUBMIT[1],   BTN_CMN_SUBMIT[2],   0, null);
        handler.setButtonProperties(BTN_CMN_APPLY[0],    BTN_CMN_APPLY[1],    BTN_CMN_APPLY[2],    0, null);
        handler.setButtonProperties(BTN_CMN_APPROVE[0],  BTN_CMN_APPROVE[1],  BTN_CMN_APPROVE[2],  0, null);
        handler.setButtonProperties(BTN_CMN_REJECT[0],   BTN_CMN_REJECT[1],   BTN_CMN_REJECT[2],   0, null);
        handler.setButtonProperties(BTN_CMN_DOWNLOAD[0], BTN_CMN_DOWNLOAD[1], BTN_CMN_DOWNLOAD[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DELETE[0],   BTN_CMN_DELETE[1],   BTN_CMN_DELETE[2],   0, null);
        handler.setButtonProperties(BTN_CMN_CLEAR[0],    BTN_CMN_CLEAR[1],    BTN_CMN_CLEAR[2],    0, null);
        handler.setButtonProperties(BTN_CMN_RESET[0],    BTN_CMN_RESET[1],    BTN_CMN_RESET[2],    1, null);
        handler.setButtonProperties(BTN_CMN_RETURN[0],   BTN_CMN_RETURN[1],   BTN_CMN_RETURN[2],   1, null);
    }

    /**
     * 
     * @param scrnMsg NLAL0070BMsg
     * @param selectedRowNum Selected row number
     * @param isErrItemSet is Errer Item Set
     * @return
     */
    public static boolean checkScdlRowOn(NLAL0070BMsg scrnMsg, int selectedRowNum, boolean isErrItemSet) {

        if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(selectedRowNum).schdWhInEtaDt_A1)) {

            if (isErrItemSet) {
                scrnMsg.A.no(selectedRowNum).schdWhInEtaDt_A1.setErrorInfo(1, "ZZM9000E", new String[] {"Schedule WETA" });
                scrnMsg.addCheckItem(scrnMsg.A.no(selectedRowNum).schdWhInEtaDt_A1);
            }
            return false;
        } else {

            if (!ZYPDateUtil.checkDate(scrnMsg.A.no(selectedRowNum).schdWhInEtaDt_A1.getValue())) {

                if (isErrItemSet) {
                    scrnMsg.A.no(selectedRowNum).schdWhInEtaDt_A1.setErrorInfo(1, "ZZM9032E", new String[] {"Schedule WETA" });
                    scrnMsg.addCheckItem(scrnMsg.A.no(selectedRowNum).schdWhInEtaDt_A1);
                    return false;
                }
            }
//Unnecessary
//            if (1 == ZYPDateUtil.compare(scrnMsg.A.no(selectedRowNum).imptTrmEtaDt_A1.getValue(),
//                    scrnMsg.A.no(selectedRowNum).schdWhInEtaDt_A1.getValue())) {
//
//                if (isErrItemSet) {
//                    scrnMsg.A.no(selectedRowNum).schdWhInEtaDt_A1.setErrorInfo(1, "NLAM0190E", null);
//                    scrnMsg.addCheckItem(scrnMsg.A.no(selectedRowNum).schdWhInEtaDt_A1);
//                }
//                return false;
//            }
        }

        return true;
    }

    /**
     * 
     * @param scrnMsg NLAL0070BMsg
     * @param selectedRowNum Selected row number
     * @param isErrItemSet is Errer Item Set
     * @return
     */
    public static boolean checkFinalRowOn(NLAL0070BMsg scrnMsg, int selectedRowNum, boolean isErrItemSet) {

        if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(selectedRowNum).finalWhInEtaDt_A1)) {

            if (isErrItemSet) {
                scrnMsg.A.no(selectedRowNum).finalWhInEtaDt_A1.setErrorInfo(1, "ZZM9000E", new String[] {"Final Schedule WETA" });
                scrnMsg.addCheckItem(scrnMsg.A.no(selectedRowNum).finalWhInEtaDt_A1);
            }
            return false;
        } else {
            if (!ZYPDateUtil.checkDate(scrnMsg.A.no(selectedRowNum).finalWhInEtaDt_A1.getValue())) {

                if (isErrItemSet) {
                    scrnMsg.A.no(selectedRowNum).finalWhInEtaDt_A1.setErrorInfo(1, "ZZM9032E", new String[] {"Final Schedule WETA" });
                    scrnMsg.addCheckItem(scrnMsg.A.no(selectedRowNum).finalWhInEtaDt_A1);
                }
                return false;
            }
//Unnecessary
//            if (1 == ZYPDateUtil.compare(scrnMsg.A.no(selectedRowNum).imptTrmEtaDt_A1.getValue(),
//                    scrnMsg.A.no(selectedRowNum).finalWhInEtaDt_A1.getValue())) {
//
//                if (isErrItemSet) {
//                    scrnMsg.A.no(selectedRowNum).finalWhInEtaDt_A1.setErrorInfo(1, "NLAM0190E", null);
//                    scrnMsg.addCheckItem(scrnMsg.A.no(selectedRowNum).finalWhInEtaDt_A1);
//                }
//                return false;
//            }
        }

        return true;
    }

    /**
     * @param scrnMsg NLAL0070BMsg
     */
    private static boolean cntrlScdlFinalCheckBox(NLAL0070BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

            scrnMsg.A.no(i).schdWhInEtaDt_A1.setInputProtected(false);
            scrnMsg.A.no(i).schdEtaChkFlg_A1.setInputProtected(false);
            scrnMsg.A.no(i).finalWhInEtaDt_A1.setInputProtected(false);
            scrnMsg.A.no(i).finalEtaChkFlg_A1.setInputProtected(false);

            if (ZYPConstant.CHKBOX_ON_Y.equals(scrnMsg.A.no(i).schdEtaChkFlg_A1.getValue())
                    && checkScdlRowOn(scrnMsg, i, false)) {

                scrnMsg.A.no(i).schdWhInEtaDt_A1.setInputProtected(true);

                if (ZYPConstant.CHKBOX_ON_Y.equals(scrnMsg.A.no(i).finalEtaChkFlg_A1.getValue())
                        && checkFinalRowOn(scrnMsg, i, false)) {

                    scrnMsg.A.no(i).schdEtaChkFlg_A1.setInputProtected(true);
                    scrnMsg.A.no(i).finalWhInEtaDt_A1.setInputProtected(true);
                }
            } else {

                scrnMsg.A.no(i).finalEtaChkFlg_A1.setInputProtected(true);
            }
        }

        return true;
    }

    /**
     * @param scrnMsg NLAL0070BMsg
     */
    public static void disableTableTextFieldForSchedule(NLAL0070BMsg scrnMsg) {

        if (TB_SCHEDULE.equals(scrnMsg.xxDplyTab_BK.getValue())) {

            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

                scrnMsg.A.no(i).mdseDescShortTxt_A1.setInputProtected(true);

            }
        }
    }

    /**
     * @param scrnMsg NLAL0070BMsg
     */
    public static void disableTableTextFieldForSummary(NLAL0070BMsg scrnMsg) {

        if (TB_SUMMARY.equals(scrnMsg.xxDplyTab_BK.getValue())) {

            for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {

                scrnMsg.B.no(i).schdTpNm_B1.setInputProtected(true);

            }
        }
    }

    /**
     * @param scrnMsg NLAL0070BMsg
     */
    public static void scrnItemControl(NLAL0070BMsg scrnMsg) {

        scrnMsg.setFocusItem(scrnMsg.A.no(0).xxChkBox_A1);

        scrnMsg.setMessageInfo("NZZM0002I", null);

    }

    // 2013/06/03 QC1209 T.Tomita Add Start
    /**
     * @param scrnMsg NLAL0070BMsg
     */
    public static void scrnInputProtected(NLAL0070BMsg scrnMsg) {
        scrnMsg.invtyLocNm_P1.setInputProtected(true);
    }
    // 2013/06/03 QC1209 T.Tomita Add End

    // QC#16256 add start
    /**
     * Function check
     * @return true:edit false:reference
     */
    private static boolean isUpdatable() {
        S21UserProfileService profileService = S21UserProfileServiceFactory.getInstance().getService();
        List<String> functionList = profileService.getAuthorizedFunctionCodeListForBizAppId(BUSINESS_ID);
        return functionList.contains(FUNCTION_UPDATE);
    }
    // QC#16256 add end

}
