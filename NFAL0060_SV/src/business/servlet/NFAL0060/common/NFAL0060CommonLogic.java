/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NFAL0060.common;

// import parts.common.EZDBMsg;
import parts.common.EZDBMsg;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NFAL0060.NFAL0060BMsg;
import business.servlet.NFAL0060.constant.NFAL0060Constant;

import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * Class name: NFAL0060CommonLogic
 * <dd>The class explanation: For screen component.
 * <dd>Remarks:
 * @version 1.0
 * @author N.Sasaki
 */

/**
 *<pre>
 * Auto Write-Off Result Inquiry Screen Detail
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/24   CSAI            K.Uramori       Update          QC#5849 remove AJE_INTFC_TP from key
 *</pre>
 */
public class NFAL0060CommonLogic implements NFAL0060Constant {

    /** S21UserProfileService profileService */
    private static S21UserProfileService profileService = S21UserProfileServiceFactory.getInstance().getService();

    /** String globalCompanyCode */
    private static String globalCompanyCode = profileService.getGlobalCompanyCode();

    /**
     * Method name: getGlobalCompanyCode
     * <dd>The method explanation: Search search Exchange Rate.
     * <dd>Remarks:
     * @return String globalCompanyCode
     */
    public static String getGlobalCompanyCode() {
        return globalCompanyCode;
    }

    /**
     * Method name: initCommonButton
     * <dd>The method explanation: init Common Button Control.
     * <dd>Remarks:
     * @param handler EZ Common Handler
     */
    public static void initCommonButton(EZDCommonHandler handler) {

        handler.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 1, null);
        handler.setButtonProperties(BTN_CMN_APPLY[0], BTN_CMN_APPLY[1], BTN_CMN_APPLY[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_REJECT[0], BTN_CMN_REJECT[1], BTN_CMN_REJECT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DOWNLOAD[0], BTN_CMN_DOWNLOAD[1], BTN_CMN_DOWNLOAD[2], 0, null);
        //---- start mod 2016/03/24 remove this function since there's delete row function.
        handler.setButtonProperties(BTN_CMN_DELETE[0], BTN_CMN_DELETE[1], BTN_CMN_DELETE[2], 0, null);
        //---- end 2016/03/24
        handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 1, null);
      //---- start mod 2016/03/24 remove this function.
        handler.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 0, null);
        //---- end 2016/03/24
        handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);

        // Common Button Confirm Message Settings
        handler.setButtonConfirmMsg(BTN_CMN_SUBMIT[1], "ZZM8101I", new String[] {BTN_CMN_SUBMIT[2] }, 0);
        handler.setButtonConfirmMsg(BTN_CMN_DELETE[1], "ZZM8101I", new String[] {BTN_CMN_DELETE[2] }, 0);
        handler.setButtonConfirmMsg(BTN_CMN_CLEAR[1], "ZZM8101I", new String[] {BTN_CMN_CLEAR[2] }, 0);
        handler.setButtonConfirmMsg(BTN_CMN_RESET[1], "ZZM8101I", new String[] {BTN_CMN_RESET[2] }, 0);
        handler.setButtonConfirmMsg(BTN_CMN_RETURN[1], "ZZM8101I", new String[] {BTN_CMN_RETURN[2] }, 0);
    }

    /**
     * Method name: setNameForMessage
     * <dd>The method explanation: Set Name For Message.
     * <dd>Remarks:
     * @param bMsg EZDBMsg
     */
    public static void setNameForMessage(EZDBMsg bMsg) {

        NFAL0060BMsg scrnMsg = (NFAL0060BMsg) bMsg;

        EZDI18NLabelConv labelConv = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();

        //scrnMsg.ajeIntfcTpCd_3S.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID, "AJE Pattern Interface Type Code"));
        //scrnMsg.ajeIntfcTpNm_S.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID, "AJE Pattern Interface Type Description"));
        scrnMsg.ajePtrnIndTpCd_3.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID, "AJE Pattern Indicator Type Code"));
        scrnMsg.ajePtrnIndTpNm.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID, "AJE Pattern Indicator Type Description"));
        scrnMsg.ajePtrnActlCd_3.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID, "AJE Pattern Actual Code Value"));
        scrnMsg.ajePtrnActlNm.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID, "AJE Pattern Actual Code Value Description"));

        scrnMsg.ajeCdTblListCd_3.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID, "AJE Pattern Actual Code Table"));
        scrnMsg.ajePtrnIndTpCd_NW.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID, "AJE Pattern Indicator Type"));
        scrnMsg.ajePtrnIndTpNm_NW.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID, "AJE Pattern Indicator Type Description"));
        //scrnMsg.ajeIntfcTpCd_3.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID, "AJE Pattern Interface Type Code"));
        //scrnMsg.ajeIntfcTpNm.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID, "AJE Pattern Interface Type Description"));
        //scrnMsg.ajeIntfcColTxt_3.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID, "AJE Pattern Interface Colmn Name"));
        
        // add
        scrnMsg.ajeIntfcColTxt_IN.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID, "AJE Pattern Interface Colmn Name"));
    }

    /**
     * Method name: protectParmanentFields
     * <dd>Enable or Disable Button Property
     * @param scrnMsg NFAL0060BMsg
     */
    public static void protectParmanentFields(NFAL0060BMsg scrnMsg) {

        //scrnMsg.ajeIntfcTpNm_S.setInputProtected(true);
        scrnMsg.ajePtrnIndTpNm.setInputProtected(true);
        scrnMsg.ajePtrnActlNm.setInputProtected(true);
        //scrnMsg.ajeIntfcTpNm.setInputProtected(true);
    }

    /**
     * Method name: enableAddRow
     * <dd>Enable or Disable Button Property
     * @param scrnMsg NFAL0050BMsg
     * @param handler EZ Common Handler
     * @param flag boolean
     */
    public void enableAddRow(NFAL0060BMsg scrnMsg, EZDCommonHandler handler, boolean flag) {
        handler.setButtonEnabled(EVT_ADD_ROW, flag);
    }

    /**
     * Method name: enableDeleteRow
     * <dd>Enable or Disable Button Property
     * @param scrnMsg NFAL0050BMsg
     * @param handler EZ Common Handler
     * @param flag boolean
     */
    public void enableDeleteRow(NFAL0060BMsg scrnMsg, EZDCommonHandler handler, boolean flag) {
        handler.setButtonEnabled(EVT_DELETE_ROW, flag);
    }

    /**
     * Method name: enableSubmitDelete
     * <dd>Enable or Disable Button Property
     * @param scrnMsg NFAL0050BMsg
     * @param handler EZDCommonHandler
     * @param enable boolean
     */
    public void enableSubmitDelete(NFAL0060BMsg scrnMsg, EZDCommonHandler handler, boolean enable) {
        handler.setButtonEnabled(BTN_CMN_SUBMIT[0], enable);
        //handler.setButtonEnabled(BTN_CMN_DELETE[0], enable);
    }

    /**
     * Method name: resetAllFileds
     * <dd>The method explanation:
     * <dd>Remarks:
     * @param scrnMsg Screen Component Interface Message
     * @param handler EZDCommonHandler
     */
    public void resetAllFileds(NFAL0060BMsg scrnMsg, EZDCommonHandler handler) {

        clearHeaderFileds(scrnMsg);
        clearAddRow(scrnMsg, handler);
        clearDisplaiedRows(scrnMsg, handler);
        clearDeletedRow(scrnMsg);
        //enableAddRow(scrnMsg, handler, false);
        enableDeleteRow(scrnMsg, handler, false);
        enableSubmitDelete(scrnMsg, handler, false);
        //---- start add 2016/03/24
        controlInputFields(scrnMsg, handler, false);
        //---- end 2016/03/24
    }

    /**
     * Method name: clearHeaderFileds
     * <dd>The method explanation:
     * <dd>Remarks:
     * @param scrnMsg Screen Component Interface Message
     */
    public void clearHeaderFileds(NFAL0060BMsg scrnMsg) {
        //scrnMsg.ajeIntfcTpCd_3S.clear();
        //scrnMsg.ajeIntfcTpNm_S.clear();
        scrnMsg.ajePtrnIndTpCd_3.clear();
        scrnMsg.ajePtrnIndTpNm.clear();
        scrnMsg.ajePtrnActlCd_3.clear();
        scrnMsg.ajePtrnActlCd_1.clear();
        scrnMsg.ajePtrnActlCd_2.clear();
        scrnMsg.ajePtrnActlCd_3.clear();
        scrnMsg.ajePtrnActlNm.clear();
    }

    /**
     * Method name: clearAddRow
     * <dd>The method explanation:
     * <dd>Remarks:
     * @param scrnMsg Screen Component Interface Message
     * @param handler EZDCommonHandler
     */
    public void clearAddRow(NFAL0060BMsg scrnMsg, EZDCommonHandler handler) {
        scrnMsg.ajeCdTblListCd_3.clear();
        scrnMsg.ajePtrnIndTpCd_NW.clear();
        scrnMsg.ajePtrnIndTpNm_NW.clear();
        //scrnMsg.ajeIntfcTpCd_3.clear();
        //scrnMsg.ajeIntfcTpNm.clear();
        //scrnMsg.ajeIntfcColTxt_3.clear();
        // add
        scrnMsg.ajeIntfcColTxt_IN.clear();
        // Add row by one click
        scrnMsg.C.clear();
        scrnMsg.C.setValidCount(0);
        // enableAddRow(scrnMsg, handler, false);
    }

    /**
     * Method name: clearDisplaiedRows
     * <dd>The method explanation:
     * <dd>Remarks:
     * @param scrnMsg Screen Component Interface Message
     * @param handler EZDCommonHandler
     */
    public void clearDisplaiedRows(NFAL0060BMsg scrnMsg, EZDCommonHandler handler) {
        // Displaied List
        scrnMsg.A.clear();
        scrnMsg.A.setValidCount(0);
        // Deleted Row
        scrnMsg.B.clear();
        scrnMsg.B.setValidCount(0);
        // Add row by one click
        scrnMsg.C.clear();
        scrnMsg.C.setValidCount(0);
        // Total Add row
        scrnMsg.D.clear();
        scrnMsg.D.setValidCount(0);
        // Submit/Delete
        enableSubmitDelete(scrnMsg, handler, false);
    }

    /**
     * Method name: clearDeletedRow
     * <dd>The method explanation:
     * <dd>Remarks:
     * @param scrnMsg Screen Component Interface Message
     */
    public void clearDeletedRow(NFAL0060BMsg scrnMsg) {
        // Deleted Row
        scrnMsg.B.clear();
        scrnMsg.B.setValidCount(0);
        // Add row by one click
        scrnMsg.C.clear();
        scrnMsg.C.setValidCount(0);
        // Total Add row
        scrnMsg.D.clear();
        scrnMsg.D.setValidCount(0);
    }

    /**
     * Method name: isAllSelected
     * <dd>The method explanation:
     * <dd>Remarks:
     * @param scrnMsg Screen Component Interface Message
     * @return boolean
     */
    public boolean isAllSelected(NFAL0060BMsg scrnMsg) {
    	// change to check the text box
        /*if (!scrnMsg.ajeCdTblListCd_3.isClear() && !scrnMsg.ajeIntfcTpCd_3.isClear() && !scrnMsg.ajeIntfcColTxt_IN.isClear()) {
            return true;
        } else {
            return false;
        }
        */
    	// disable this function
        return true;
    }

    /**
     * Method name: clearChkBoxes
     * <dd>The method explanation: set check boxes
     * <dd>Remarks:
     * @param scrnMsg Screen Component Interface Message
     */
    public void clearChkBoxes(NFAL0060BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).xxChkBox_A.clear();
            scrnMsg.A.no(i).xxChkBox_A.setInputProtected(false);
        }
    }

    /**
     * Method name: setInputProtectedTextFiled
     * <dd>The method explanation: Set input protected
     * <dd>Remarks:
     * @param scrnMsg Screen Component Interface Message
     */
    public static void setInputProtectedTextFiled(NFAL0060BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).ajePtrnIndTpNm_A.setInputProtected(true);
            scrnMsg.A.no(i).ajePtrnActlNm_A.setInputProtected(true);
            //scrnMsg.A.no(i).ajeIntfcTpNm_A.setInputProtected(true);
            scrnMsg.A.no(i).ajeIntfcColTxt_A.setInputProtected(true);
        }
    }
    
    //---- start add 2016/03/24
    public static void controlInputFields(NFAL0060BMsg scrnMsg, EZDCommonHandler handler, boolean protect) {
        
        scrnMsg.ajePtrnIndTpCd_NW.setInputProtected(protect);
        scrnMsg.ajePtrnIndTpNm_NW.setInputProtected(protect);
        scrnMsg.ajeCdTblListCd_3.setInputProtected(protect);
        scrnMsg.ajeIntfcColTxt_IN.setInputProtected(protect);
        
        if (protect) {
            handler.setButtonEnabled("AddRow", false);
        } else {
            handler.setButtonEnabled("AddRow", true);
        }
        
    }
    
    public static void controlChkBox(NFAL0060BMsg scrnMsg, EZDCommonHandler handler, boolean protect) {
        
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).xxChkBox_A.setInputProtected(protect);
        }
        
        if (protect) {
            handler.setButtonEnabled(BTN_CMN_SUBMIT[0], false);
        } else {
            handler.setButtonEnabled(BTN_CMN_SUBMIT[0], true);
        }
    }
    //---- end 2016/03/24

}
