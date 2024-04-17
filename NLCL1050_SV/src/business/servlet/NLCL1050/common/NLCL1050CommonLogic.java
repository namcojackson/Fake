/*
 * Copyright(c)2015 Canon USA Inc. All rights reserved.
 */
package business.servlet.NLCL1050.common;

import static business.servlet.NLCL1050.constant.NLCL1050Constant.BTN_ADDLINE;
import static business.servlet.NLCL1050.constant.NLCL1050Constant.BTN_CMN_BTN_1;
import static business.servlet.NLCL1050.constant.NLCL1050Constant.BTN_CMN_BTN_10;
import static business.servlet.NLCL1050.constant.NLCL1050Constant.BTN_CMN_BTN_2;
import static business.servlet.NLCL1050.constant.NLCL1050Constant.BTN_CMN_BTN_3;
import static business.servlet.NLCL1050.constant.NLCL1050Constant.BTN_CMN_BTN_4;
import static business.servlet.NLCL1050.constant.NLCL1050Constant.BTN_CMN_BTN_5;
import static business.servlet.NLCL1050.constant.NLCL1050Constant.BTN_CMN_BTN_6;
import static business.servlet.NLCL1050.constant.NLCL1050Constant.BTN_CMN_BTN_7;
import static business.servlet.NLCL1050.constant.NLCL1050Constant.BTN_CMN_BTN_8;
import static business.servlet.NLCL1050.constant.NLCL1050Constant.BTN_CMN_BTN_9;
import static business.servlet.NLCL1050.constant.NLCL1050Constant.BTN_DELETE;
import static business.servlet.NLCL1050.constant.NLCL1050Constant.BTN_DELETELINE;
import static business.servlet.NLCL1050.constant.NLCL1050Constant.BTN_SAVE;
import static business.servlet.NLCL1050.constant.NLCL1050Constant.BTN_SEARCH;
import static business.servlet.NLCL1050.constant.NLCL1050Constant.FUNC_ID_SUBMIT;
import static business.servlet.NLCL1050.constant.NLCL1050Constant.SCREEN_ID;

import java.util.List;

import parts.servletcommon.EZDCommonHandler;
import business.servlet.NLCL1050.NLCL1050BMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * Business ID : NLCL1050 ABC Class Setup
 * Function Name : Common Logic
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/12   CITS            N.Akaishi       Create          N/A
 *</pre>
 */
public class NLCL1050CommonLogic {

    /**
     * <pre>
     * The initial state of the screen item is set.
     * </pre>
     * @param handler EZDCommonHandler
     * @param scrnMsg NLCL1050BMsg
     * @param funcList List<String>
     */
    public static final void ctrlScreenItem(EZDCommonHandler handler, NLCL1050BMsg scrnMsg, List<String> funcList) {

        // clear html attribute
        scrnMsg.clearAllGUIAttribute(SCREEN_ID);

        if (0 < scrnMsg.A.getValidCount()) {
            S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
            tblColor.setAlternateRowsBG("A", scrnMsg.A);
        }

        // check Role
        boolean inquiry = chkInquiryRole(funcList);

        // Header Item
        scrnMsg.abcAnlsClsNm.setInputProtected(false);

        if (ZYPCommonFunc.hasValue(scrnMsg.abcAnlsClsNum_SV)) {
            scrnMsg.abcAnlsClsNm.setInputProtected(true);
        }
        // set Enable Item
        if (inquiry) {

            if (scrnMsg.A.getValidCount() > 0) {
                for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                    scrnMsg.A.no(i).xxChkBox.setInputProtected(false);
                    scrnMsg.A.no(i).abcAnlsClsPrtyNum.setInputProtected(false);
                    scrnMsg.A.no(i).abcAnlsClsTagCd.setInputProtected(false);
                    scrnMsg.A.no(i).abcAnlsClsTagDescTxt.setInputProtected(false);
                    scrnMsg.A.no(i).cycleCntFreqDaysAot.setInputProtected(false);
                    scrnMsg.A.no(i).valAsgPct.setInputProtected(false);
                }

            }

            if (scrnMsg.A.getValidCount() == 0) {
                handler.setButtonEnabled(BTN_SAVE, false);
                handler.setButtonEnabled(BTN_DELETE, false);
            } else {
                handler.setButtonEnabled(BTN_SAVE, true);
                handler.setButtonEnabled(BTN_DELETE, true);
            }

            if (scrnMsg.A.length() == scrnMsg.A.getValidCount()) {
                handler.setButtonEnabled(BTN_ADDLINE, false);
            } else {
                handler.setButtonEnabled(BTN_ADDLINE, true);
            }

            if (scrnMsg.A.getValidCount() == 0) {
                handler.setButtonEnabled(BTN_DELETELINE, false);
            } else {
                handler.setButtonEnabled(BTN_DELETELINE, true);
            }

        } else {

            if (scrnMsg.A.getValidCount() > 0) {
                for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                    scrnMsg.A.no(i).xxChkBox.setInputProtected(true);
                    scrnMsg.A.no(i).abcAnlsClsPrtyNum.setInputProtected(true);
                    scrnMsg.A.no(i).abcAnlsClsTagCd.setInputProtected(true);
                    scrnMsg.A.no(i).abcAnlsClsTagDescTxt.setInputProtected(true);
                    scrnMsg.A.no(i).cycleCntFreqDaysAot.setInputProtected(true);
                    scrnMsg.A.no(i).valAsgPct.setInputProtected(true);
                }
            }

            // Button Item
            handler.setButtonEnabled(BTN_ADDLINE, false);
            handler.setButtonEnabled(BTN_DELETELINE, false);
            handler.setButtonEnabled(BTN_SAVE, false);
            handler.setButtonEnabled(BTN_DELETE, false);

        }

        // Button Item
        handler.setButtonEnabled(BTN_SEARCH, true);

        // common button protection
        // 0 : inactive
        // 1 : active
        handler.setButtonProperties(BTN_CMN_BTN_1[0], BTN_CMN_BTN_1[1], BTN_CMN_BTN_1[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_2[0], BTN_CMN_BTN_2[1], BTN_CMN_BTN_2[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_3[0], BTN_CMN_BTN_3[1], BTN_CMN_BTN_3[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_4[0], BTN_CMN_BTN_4[1], BTN_CMN_BTN_4[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_5[0], BTN_CMN_BTN_5[1], BTN_CMN_BTN_5[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_6[0], BTN_CMN_BTN_6[1], BTN_CMN_BTN_6[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_7[0], BTN_CMN_BTN_7[1], BTN_CMN_BTN_7[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_8[0], BTN_CMN_BTN_8[1], BTN_CMN_BTN_8[2], 1, null);
        handler.setButtonProperties(BTN_CMN_BTN_9[0], BTN_CMN_BTN_9[1], BTN_CMN_BTN_9[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_10[0], BTN_CMN_BTN_10[1], BTN_CMN_BTN_10[2], 1, null);
    }

    /**
     * chkInquiryRole
     * @param functionList List<String>
     * @return true:Update false:Inquiry
     */
    public static boolean chkInquiryRole(List<String> functionList) {
        for (String function : functionList) {
            if (FUNC_ID_SUBMIT.equals(function)) {
                return true;
            }
        }
        return false;
    }

    /**
     * <pre>
     * addCheckItem
     * </pre>
     * @param scrnMsg Screen Msg
     */
    public static void addCheckItem(NLCL1050BMsg scrnMsg) {
        addCheckHeaderItem(scrnMsg);
        addCheckDetailItem(scrnMsg);
    }

    /**
     * <pre>
     * addCheckHeaderItem
     * </pre>
     * @param scrnMsg Screen Msg
     */
    public static void addCheckHeaderItem(NLCL1050BMsg scrnMsg) {
        scrnMsg.addCheckItem(scrnMsg.abcAnlsClsNm);
    }

    /**
     * <pre>
     * addCheckDetailItem
     * </pre>
     * @param scrnMsg Screen Msg
     */
    public static void addCheckDetailItem(NLCL1050BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).abcAnlsClsPrtyNum);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).abcAnlsClsTagCd);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).abcAnlsClsTagDescTxt);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).cycleCntFreqDaysAot);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).valAsgPct);
        }
    }

    /**
     * Return true if userId have update authority.
     * @param userId String
     * @return boolean
     */
    public static final boolean hasUpdateAuthority(String userId) {
        S21UserProfileService profileService = S21UserProfileServiceFactory.getInstance().getService();
        if (profileService.isFunctionGranted(userId, new String[] {FUNC_ID_SUBMIT })) {
            return true;
        }
        return false;
    }

    /**
     * 
     * set xxSupdFlg (for delete)
     * 
     * @param scrnMsg NLCL1050BMsg
     */
    public static void commonInit(NLCL1050BMsg scrnMsg) {
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxSupdFlg, ZYPConstant.FLG_OFF_N);
    }
}
