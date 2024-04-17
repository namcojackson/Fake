/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1150.common;

import static business.servlet.NPAL1150.constant.NPAL1150Constant.BUSINESS_APPLICATION_ID;
import static business.servlet.NPAL1150.constant.NPAL1150Constant.BUTTON_GUARD_APPLY;
import static business.servlet.NPAL1150.constant.NPAL1150Constant.BUTTON_GUARD_APPROVE;
import static business.servlet.NPAL1150.constant.NPAL1150Constant.BUTTON_GUARD_CLEAR;
import static business.servlet.NPAL1150.constant.NPAL1150Constant.BUTTON_GUARD_DELETE;
import static business.servlet.NPAL1150.constant.NPAL1150Constant.BUTTON_GUARD_DOWNLOAD;
import static business.servlet.NPAL1150.constant.NPAL1150Constant.BUTTON_GUARD_REJECT;
import static business.servlet.NPAL1150.constant.NPAL1150Constant.BUTTON_GUARD_RESET;
import static business.servlet.NPAL1150.constant.NPAL1150Constant.BUTTON_GUARD_RETURN;
import static business.servlet.NPAL1150.constant.NPAL1150Constant.BUTTON_GUARD_SAVE;
import static business.servlet.NPAL1150.constant.NPAL1150Constant.BUTTON_GUARD_SUBMIT;
import static business.servlet.NPAL1150.constant.NPAL1150Constant.BUTTON_LABEL_APPLY;
import static business.servlet.NPAL1150.constant.NPAL1150Constant.BUTTON_LABEL_APPROVE;
import static business.servlet.NPAL1150.constant.NPAL1150Constant.BUTTON_LABEL_CLEAR;
import static business.servlet.NPAL1150.constant.NPAL1150Constant.BUTTON_LABEL_DELETE;
import static business.servlet.NPAL1150.constant.NPAL1150Constant.BUTTON_LABEL_DOWNLOAD;
import static business.servlet.NPAL1150.constant.NPAL1150Constant.BUTTON_LABEL_REJECT;
import static business.servlet.NPAL1150.constant.NPAL1150Constant.BUTTON_LABEL_RESET;
import static business.servlet.NPAL1150.constant.NPAL1150Constant.BUTTON_LABEL_RETURN;
import static business.servlet.NPAL1150.constant.NPAL1150Constant.BUTTON_LABEL_SAVE;
import static business.servlet.NPAL1150.constant.NPAL1150Constant.BUTTON_LABEL_SUBMIT;
import static business.servlet.NPAL1150.constant.NPAL1150Constant.BUTTON_NAME_APPLY;
import static business.servlet.NPAL1150.constant.NPAL1150Constant.BUTTON_NAME_APPROVE;
import static business.servlet.NPAL1150.constant.NPAL1150Constant.BUTTON_NAME_CANCEL;
import static business.servlet.NPAL1150.constant.NPAL1150Constant.BUTTON_NAME_CLEAR;
import static business.servlet.NPAL1150.constant.NPAL1150Constant.BUTTON_NAME_DELETE;
import static business.servlet.NPAL1150.constant.NPAL1150Constant.BUTTON_NAME_DOWNLOAD;
import static business.servlet.NPAL1150.constant.NPAL1150Constant.BUTTON_NAME_PO_ENTRY;
import static business.servlet.NPAL1150.constant.NPAL1150Constant.BUTTON_NAME_REJECT;
import static business.servlet.NPAL1150.constant.NPAL1150Constant.BUTTON_NAME_REPROCESS;
import static business.servlet.NPAL1150.constant.NPAL1150Constant.BUTTON_NAME_RESET;
import static business.servlet.NPAL1150.constant.NPAL1150Constant.BUTTON_NAME_RETURN;
import static business.servlet.NPAL1150.constant.NPAL1150Constant.BUTTON_NAME_SAVE;
import static business.servlet.NPAL1150.constant.NPAL1150Constant.BUTTON_NAME_SELECT_ALL;
import static business.servlet.NPAL1150.constant.NPAL1150Constant.BUTTON_NAME_SUBMIT;
import static business.servlet.NPAL1150.constant.NPAL1150Constant.BUTTON_NAME_UN_SELECT_ALL;
import static business.servlet.NPAL1150.constant.NPAL1150Constant.FUNCTION_UPDATE;

import java.math.BigDecimal;
import java.util.List;

import parts.servletcommon.EZDCommonHandler;
import business.servlet.NPAL1150.NPAL1150BMsg;
import business.servlet.NPAL1150.constant.NPAL1150Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * NPAL1150 ASN Error Correction
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/05/15   Hitachi         T.Kawazu        Create          N/A
 * 2013/05/30   Hitachi         T.Kawazu        Update          QC1233
 * 2013/06/13   Hitachi         T.Tomita        Update          QC1233
 * 2017/01/17   CITS            T.Kikuhara      Update          QC#16256
 * 2017/10/27   CITS            M.Naito         Update          QC#20380
 *</pre>
 */

public class NPAL1150CommonLogic {
    /**
     * Method name: initCommonButton <dd>The method explanation: init
     * Common Button Control. <dd>Remarks:
     * @param handler EZ Common Handler
     */
    public static final void initCommonButton(EZDCommonHandler handler) {

        // QC#16256 add start
        // This Screen is Read only Screen
        if (isUpdatable()) {
            handler.setButtonProperties(BUTTON_NAME_SAVE, BUTTON_GUARD_SAVE, BUTTON_LABEL_SAVE, 0, null);
            handler.setButtonProperties(BUTTON_NAME_SUBMIT, BUTTON_GUARD_SUBMIT, BUTTON_LABEL_SUBMIT, 0, null);
            handler.setButtonProperties(BUTTON_NAME_APPLY, BUTTON_GUARD_APPLY, BUTTON_LABEL_APPLY, 0, null);
            handler.setButtonProperties(BUTTON_NAME_APPROVE, BUTTON_GUARD_APPROVE, BUTTON_LABEL_APPROVE, 0, null);
            handler.setButtonProperties(BUTTON_NAME_REJECT, BUTTON_GUARD_REJECT, BUTTON_LABEL_REJECT, 0, null);
            handler.setButtonProperties(BUTTON_NAME_DOWNLOAD, BUTTON_GUARD_DOWNLOAD, BUTTON_LABEL_DOWNLOAD, 0, null);
            handler.setButtonProperties(BUTTON_NAME_DELETE, BUTTON_GUARD_DELETE, BUTTON_LABEL_DELETE, 0, null);
            handler.setButtonProperties(BUTTON_NAME_CLEAR, BUTTON_GUARD_CLEAR, BUTTON_LABEL_CLEAR, 0, null);
            handler.setButtonProperties(BUTTON_NAME_RESET, BUTTON_GUARD_RESET, BUTTON_LABEL_RESET, 1, null);
            handler.setButtonProperties(BUTTON_NAME_RETURN, BUTTON_GUARD_RETURN, BUTTON_LABEL_RETURN, 1, null);
        } else {
            handler.setButtonProperties(BUTTON_NAME_SAVE, BUTTON_GUARD_SAVE, BUTTON_LABEL_SAVE, 0, null);
            handler.setButtonProperties(BUTTON_NAME_SUBMIT, BUTTON_GUARD_SUBMIT, BUTTON_LABEL_SUBMIT, 0, null);
            handler.setButtonProperties(BUTTON_NAME_APPLY, BUTTON_GUARD_APPLY, BUTTON_LABEL_APPLY, 0, null);
            handler.setButtonProperties(BUTTON_NAME_APPROVE, BUTTON_GUARD_APPROVE, BUTTON_LABEL_APPROVE, 0, null);
            handler.setButtonProperties(BUTTON_NAME_REJECT, BUTTON_GUARD_REJECT, BUTTON_LABEL_REJECT, 0, null);
            handler.setButtonProperties(BUTTON_NAME_DOWNLOAD, BUTTON_GUARD_DOWNLOAD, BUTTON_LABEL_DOWNLOAD, 0, null);
            handler.setButtonProperties(BUTTON_NAME_DELETE, BUTTON_GUARD_DELETE, BUTTON_LABEL_DELETE, 0, null);
            handler.setButtonProperties(BUTTON_NAME_CLEAR, BUTTON_GUARD_CLEAR, BUTTON_LABEL_CLEAR, 0, null);
            handler.setButtonProperties(BUTTON_NAME_RESET, BUTTON_GUARD_RESET, BUTTON_LABEL_RESET, 1, null);
            handler.setButtonProperties(BUTTON_NAME_RETURN, BUTTON_GUARD_RETURN, BUTTON_LABEL_RETURN, 1, null);
        }
        // QC#16256 add end
    }

    // 2013/06/13 QC1233 T.Tomita Add Start

    /**
     * editProtectedScrnMsg
     * @param handler EZDCommonHandler
     * @param scrnMsg NPAL1150BMsg
     */
    public static void editProtectedScrnMsg(EZDCommonHandler handler, NPAL1150BMsg scrnMsg) {
        editProtectedScrnMsgUpperArea(scrnMsg);
        setProtectedUnderDetailTab(scrnMsg);
        controlPOEntryButton(handler, scrnMsg);
    }

    // 2013/06/13 QC1233 T.Tomita Add End

    /**
     * editProtectedScrnMsgUpperArea
     * @param scrnMsg NPAL1150BMsg
     */
    public static void editProtectedScrnMsgUpperArea(NPAL1150BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).xxChkBox_B1.setInputProtected(false);
            // 2017/10/27 QC20380 M.Naito Mod Start
//            scrnMsg.A.no(i).vndNm_B1.setInputProtected(true);
            scrnMsg.A.no(i).dplyVndNm_B1.setInputProtected(true);
            // 2017/10/27 QC20380 M.Naito Mod End
            scrnMsg.A.no(i).ediPoOrdNum_B1.setInputProtected(true);
            scrnMsg.A.no(i).asnSoNum_B1.setInputProtected(true);
            scrnMsg.A.no(i).asnEdiProcStsNm_B1.setInputProtected(true);
            if (!isSaveMode(scrnMsg, i)) {
                scrnMsg.A.no(i).xxChkBox_B1.setInputProtected(true);
            }
        }
    }

    /**
     * isSaveMode
     * @param scrnMsg NPAL1150BMsg
     * @param i int
     * @return boolean
     */
    public static boolean isSaveMode(NPAL1150BMsg scrnMsg, int i) {

        if (scrnMsg.A.no(i).openAsnWrkFlg_B1.getValue().equals(ZYPConstant.FLG_ON_Y)) {

            return true;
        }
        return false;
    }

    /**
     * setProtectedUnderDetailTab
     * @param scrnMsg NPAL1150BMsg
     */
    public static void setProtectedUnderDetailTab(NPAL1150BMsg scrnMsg) {

        BigDecimal num = scrnMsg.xxNum.getValue();
        if (!ZYPCommonFunc.hasValue(num)) {
            return;
        }

        int index = num.intValue();

        if (isSaveMode(scrnMsg, index)) {
            scrnMsg.C.no(0).dispPoDtlLineNum_D1.setInputProtected(false);

            scrnMsg.C.no(0).batErrMsgTxt_DV.setInputProtected(false);

            for (int i = 1; i < scrnMsg.C.getValidCount(); i++) {

                scrnMsg.C.no(i).batErrMsgTxt_DV.setInputProtected(false);

                if (!ZYPCommonFunc.hasValue(scrnMsg.C.no(i).ediPoOrdDtlLineNum_D1.getValue())) {
                    scrnMsg.C.no(i).dispPoDtlLineNum_D1.setInputProtected(false);
                    continue;
                }

                scrnMsg.C.no(i).dispPoDtlLineNum_D1.setInputProtected(false);

            }
        } else {

            scrnMsg.C.no(0).batErrMsgTxt_DV.setInputProtected(false);

            for (int i = 1; i < scrnMsg.C.getValidCount(); i++) {

                scrnMsg.C.no(i).batErrMsgTxt_DV.setInputProtected(false);

            }
        }

    }

    /**
     * saveButtonOn
     * @param handler EZDCommonHandler
     */
    public static void saveButtonOn(EZDCommonHandler handler) {
        if (isUpdatable()) {
            handler.setButtonEnabled(BUTTON_NAME_SAVE, true);
        }

    }

    /**
     * saveButtonOff
     * @param handler EZDCommonHandler
     */
    public static void saveButtonOff(EZDCommonHandler handler) {
        handler.setButtonEnabled(BUTTON_NAME_SAVE, false);

    }

    /**
     * controlSaveButton
     * @param handler EZDCommonHandler
     * @param scrnMsg NPAL1150BMsg
     */
    public static void controlSaveButton(EZDCommonHandler handler, NPAL1150BMsg scrnMsg) {

        BigDecimal index = scrnMsg.xxNum.getValue();
        if (ZYPCommonFunc.hasValue(index)) {
            int i = index.intValue();
            if (isSaveMode(scrnMsg, i)) {
                NPAL1150CommonLogic.saveButtonOn(handler);
            } else {
                NPAL1150CommonLogic.saveButtonOff(handler);
            }
        } else {
            NPAL1150CommonLogic.saveButtonOff(handler);
        }

    }

    /**
     * controlSaveButton
     * @param handler EZDCommonHandler
     * @param scrnMsg NPAL1150BMsg
     */
    public static void controlButton(EZDCommonHandler handler, NPAL1150BMsg scrnMsg) {

        // Button ON/OFF Control
        if (scrnMsg.A.getValidCount() > 0 && isUpdatable()) {
            handler.setButtonEnabled(BUTTON_NAME_REPROCESS, true);
            handler.setButtonEnabled(BUTTON_NAME_CANCEL, true);
            handler.setButtonEnabled(BUTTON_NAME_SELECT_ALL, true);
            handler.setButtonEnabled(BUTTON_NAME_UN_SELECT_ALL, true);
        } else {
            handler.setButtonEnabled(BUTTON_NAME_REPROCESS, false);
            handler.setButtonEnabled(BUTTON_NAME_CANCEL, false);
            handler.setButtonEnabled(BUTTON_NAME_SELECT_ALL, false);
            handler.setButtonEnabled(BUTTON_NAME_UN_SELECT_ALL, false);
        }
        if (scrnMsg.B.getValidCount() > 0 && isUpdatable()) {
            handler.setButtonEnabled(BUTTON_NAME_PO_ENTRY, true);
        } else {
            handler.setButtonEnabled(BUTTON_NAME_PO_ENTRY, false);
        }
        controlSaveButton(handler, scrnMsg);
    }

    /**
     * clearParamNPAL6050
     * @param scrnMsg NPAL1150BMsg
     */
    public static void clearParamNPAL6050(NPAL1150BMsg scrnMsg) {
        scrnMsg.xxTblNm.clear();
        scrnMsg.xxTblCdColNm.clear();
        scrnMsg.xxTblNmColNm.clear();
        scrnMsg.xxTblSortColNm.clear();
        scrnMsg.xxScrNm.clear();
        scrnMsg.xxHdrCdLbNm.clear();
        scrnMsg.xxHdrNmLbNm.clear();
        scrnMsg.xxDtlCdLbNm.clear();
        scrnMsg.xxDtlNmLbNm.clear();

    }

    /**
     * initialize
     * @param handler EZDCommonHandler
     * @param scrnMsg NPAL1150BMsg
     */
    public static void initialize(EZDCommonHandler handler, NPAL1150BMsg scrnMsg) {
        handler.setButtonEnabled(BUTTON_NAME_REPROCESS, false);
        handler.setButtonEnabled(BUTTON_NAME_CANCEL, false);
        handler.setButtonEnabled(BUTTON_NAME_PO_ENTRY, false);
        handler.setButtonEnabled(BUTTON_NAME_SELECT_ALL, false);
        handler.setButtonEnabled(BUTTON_NAME_UN_SELECT_ALL, false);
        scrnMsg.xxDplyTab.setValue(NPAL1150Constant.UNDER_TAB_HEADER);
        scrnMsg.B.setInputProtected(true);
    }

    /**
     * controlPOEntryButton
     * @param handler EZDCommonHandler
     * @param scrnMsg NPAL1150BMsg
     */
    public static void controlPOEntryButton(EZDCommonHandler handler, NPAL1150BMsg scrnMsg) {

        BigDecimal index = scrnMsg.xxNum.getValue();

        if (ZYPCommonFunc.hasValue(index)) {

            int i = index.intValue();

            if (isSaveMode(scrnMsg, i)) {
                scrnMsg.B.no(0).poOrdNum_H1.setInputProtected(false);
                if (isUpdatable()) {
                    handler.setButtonEnabled(BUTTON_NAME_PO_ENTRY, true);
                }
            } else {
                scrnMsg.B.no(0).poOrdNum_H1.setInputProtected(true);
                if (isUpdatable()) {
                    handler.setButtonEnabled(BUTTON_NAME_PO_ENTRY, true);
                }
            }
        } else {
            scrnMsg.B.no(0).poOrdNum_H1.setInputProtected(true);
            handler.setButtonEnabled(BUTTON_NAME_PO_ENTRY, false);
        }

    }

    /**
     * isSameEdiLineNum
     * @param lineNum1 String
     * @param lineNum2 String
     * @return boolean
     */
    public static boolean isSameEdiLineNum(String lineNum1, String lineNum2) {
        if (ZYPCommonFunc.hasValue(lineNum1)) {
            if (!lineNum1.equals(lineNum2)) {
                return false;
            }
        } else {
            return false;
        }

        return true;
    }

    // QC#16256 add start
    /**
     * Function check
     * @return true:edit false:reference
     */
    private static boolean isUpdatable() {
        S21UserProfileService profileService = S21UserProfileServiceFactory.getInstance().getService();
        List<String> functionList = profileService.getAuthorizedFunctionCodeListForBizAppId(BUSINESS_APPLICATION_ID);
        return functionList.contains(FUNCTION_UPDATE);
    }
    // QC#16256 add end

}
