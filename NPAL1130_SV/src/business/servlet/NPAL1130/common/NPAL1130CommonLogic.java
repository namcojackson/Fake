/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NPAL1130.common;

import static business.servlet.NPAL1130.constant.NPAL1130Constant.BUSINESS_ID;
import static business.servlet.NPAL1130.constant.NPAL1130Constant.BUTTON_GUARD_APPLY;
import static business.servlet.NPAL1130.constant.NPAL1130Constant.BUTTON_GUARD_APPROVE;
import static business.servlet.NPAL1130.constant.NPAL1130Constant.BUTTON_GUARD_CLEAR;
import static business.servlet.NPAL1130.constant.NPAL1130Constant.BUTTON_GUARD_DELETE;
import static business.servlet.NPAL1130.constant.NPAL1130Constant.BUTTON_GUARD_DOWNLOAD;
import static business.servlet.NPAL1130.constant.NPAL1130Constant.BUTTON_GUARD_REJECT;
import static business.servlet.NPAL1130.constant.NPAL1130Constant.BUTTON_GUARD_RESET;
import static business.servlet.NPAL1130.constant.NPAL1130Constant.BUTTON_GUARD_RETURN;
import static business.servlet.NPAL1130.constant.NPAL1130Constant.BUTTON_GUARD_SAVE;
import static business.servlet.NPAL1130.constant.NPAL1130Constant.BUTTON_GUARD_SUBMIT;
import static business.servlet.NPAL1130.constant.NPAL1130Constant.BUTTON_LABEL_APPLY;
import static business.servlet.NPAL1130.constant.NPAL1130Constant.BUTTON_LABEL_APPROVE;
import static business.servlet.NPAL1130.constant.NPAL1130Constant.BUTTON_LABEL_CLEAR;
import static business.servlet.NPAL1130.constant.NPAL1130Constant.BUTTON_LABEL_DELETE;
import static business.servlet.NPAL1130.constant.NPAL1130Constant.BUTTON_LABEL_DOWNLOAD;
import static business.servlet.NPAL1130.constant.NPAL1130Constant.BUTTON_LABEL_REJECT;
import static business.servlet.NPAL1130.constant.NPAL1130Constant.BUTTON_LABEL_RESET;
import static business.servlet.NPAL1130.constant.NPAL1130Constant.BUTTON_LABEL_RETURN;
import static business.servlet.NPAL1130.constant.NPAL1130Constant.BUTTON_LABEL_SAVE;
import static business.servlet.NPAL1130.constant.NPAL1130Constant.BUTTON_LABEL_SUBMIT;
import static business.servlet.NPAL1130.constant.NPAL1130Constant.BUTTON_NAME_APPLY;
import static business.servlet.NPAL1130.constant.NPAL1130Constant.BUTTON_NAME_APPROVE;
import static business.servlet.NPAL1130.constant.NPAL1130Constant.BUTTON_NAME_CLEAR;
import static business.servlet.NPAL1130.constant.NPAL1130Constant.BUTTON_NAME_DELETE;
import static business.servlet.NPAL1130.constant.NPAL1130Constant.BUTTON_NAME_DOWNLOAD;
import static business.servlet.NPAL1130.constant.NPAL1130Constant.BUTTON_NAME_REJECT;
import static business.servlet.NPAL1130.constant.NPAL1130Constant.BUTTON_NAME_RESET;
import static business.servlet.NPAL1130.constant.NPAL1130Constant.BUTTON_NAME_RETURN;
import static business.servlet.NPAL1130.constant.NPAL1130Constant.BUTTON_NAME_SAVE;
import static business.servlet.NPAL1130.constant.NPAL1130Constant.BUTTON_NAME_SEARCH;
import static business.servlet.NPAL1130.constant.NPAL1130Constant.BUTTON_NAME_SEARCH_LOC_NAME;
import static business.servlet.NPAL1130.constant.NPAL1130Constant.BUTTON_NAME_SEARCH_MDSE_NAME;
import static business.servlet.NPAL1130.constant.NPAL1130Constant.BUTTON_NAME_SUBMIT;
import static business.servlet.NPAL1130.constant.NPAL1130Constant.EVENT_ID_INIT;
import static business.servlet.NPAL1130.constant.NPAL1130Constant.EVENT_ID_RESET;
import static business.servlet.NPAL1130.constant.NPAL1130Constant.FUNCTION_UPDATE;
import static business.servlet.NPAL1130.constant.NPAL1130Constant.SCRN_ID;

import java.math.BigDecimal;
import java.util.List;

import parts.common.EZDGUIAttribute;
import business.servlet.NPAL1130.NPAL1130BMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * NPAL1130 Replenishment Plan Inquiry (Detail)
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/05/21   Hitachi         A.Kohinata      Create          N/A
 * 2016/03/01   CITS            K.Masaki        Update          CSA Project
 * 2017/01/17   CITS            T.Kikuhara      Update          QC#16256
 * 2017/11/07   CITS            S.Katsuma       Update          SOL#014(QC#18401)
 *</pre>
 */
public class NPAL1130CommonLogic {

    /**
     * Set Screen Control
     * @param evntId String
     * @param handler S21CommonHandler
     * @param scrnMsg NPAL1130BMsg
     */
    public static void setScreenControl(String evntId, S21CommonHandler handler, NPAL1130BMsg scrnMsg) {

        // START 2017/11/07 S.Katsuma [SOL#014(QC#18401),ADD]
        // Initialize Common Button
        initCommonButton(handler);
        // Initialize UI Control
        initSetUIControl(handler, scrnMsg);
        // END 2017/11/07 S.Katsuma [SOL#014(QC#18401),ADD]

        if ((EVENT_ID_INIT.equals(evntId)) || (EVENT_ID_RESET.equals(evntId))) {
            // START 2017/11/07 S.Katsuma [SOL#014(QC#18401),ADD]
            // Initialize Common Button
//            initCommonButton(handler);
            // Initialize UI Control
//            initSetUIControl(handler, scrnMsg);
            // END 2017/11/07 S.Katsuma [SOL#014(QC#18401),ADD]
            // Initialize Set Value
            initSetValue(scrnMsg);
        } else {
            // UI Control
            scrnMsg.A.setInputProtected(true);
            scrnMsg.clearAllGUIAttribute(SCRN_ID);
            S21TableColorController tblColor = new S21TableColorController(SCRN_ID,scrnMsg);
            tblColor.setAlternateRowsBG("A", scrnMsg.A);

            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                scrnMsg.clearGUIAttribute(SCRN_ID, "OpenWin_SupdInvtySearch" + i);
                if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).supdFlg_A0)
                || (ZYPConstant.FLG_OFF_N.equals(scrnMsg.A.no(i).supdFlg_A0.getValue()))) {
                     scrnMsg.A.no(i).xxAncrCtrlFlg_A0.setInputProtected(true);
                     EZDGUIAttribute supInvtyLinkCtrl = new EZDGUIAttribute(SCRN_ID, "OpenWin_SupdInvtySearch" + i);
                     supInvtyLinkCtrl.setStyleAttribute("color", "black");
                     supInvtyLinkCtrl.setStyleAttribute("text-decoration", "none");
                     scrnMsg.addGUIAttribute(supInvtyLinkCtrl);
                 } else {
                     scrnMsg.A.no(i).xxAncrCtrlFlg_A0.setInputProtected(false);
                 }
            }
        }
    }

    /**
     * It is an initial common button control method
     * @param handler S21CommonHandler
     */
    private static void initCommonButton(S21CommonHandler handler) {

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

    /**
     * Initialize Set UI Control
     * @param handler S21CommonHandler
     * @param scrnMsg NPAL1130BMsg
     * @param userId String
     */
    private static void initSetUIControl(S21CommonHandler handler, NPAL1130BMsg scrnMsg) {

        scrnMsg.xxLinkAncr_H1.setInputProtected(false);
        scrnMsg.mdseCd.setInputProtected(false);
        handler.setButtonEnabled(BUTTON_NAME_SEARCH_MDSE_NAME, true);
        scrnMsg.mdseDescShortTxt.setInputProtected(true);
        scrnMsg.xxLinkAncr_H2.setInputProtected(false);
        scrnMsg.rtlWhCd.setInputProtected(false);
        handler.setButtonEnabled(BUTTON_NAME_SEARCH_LOC_NAME, true);
        scrnMsg.rtlWhNm.setInputProtected(true);
        handler.setButtonEnabled(BUTTON_NAME_SEARCH, true);

        // START 2017/11/07 S.Katsuma [SOL#014(QC#18401),ADD]
        scrnMsg.mrpPlnNm.setInputProtected(true);
        // END 2017/11/07 S.Katsuma [SOL#014(QC#18401),ADD]
        scrnMsg.ropQty.setInputProtected(true);
        scrnMsg.maxOrdQty.setInputProtected(true);
        scrnMsg.procrTpDescTxt.setInputProtected(true);
        scrnMsg.srcRtlWhCd_S1.setInputProtected(true);
        scrnMsg.rtlWhNm_S1.setInputProtected(true);

        handler.setButtonEnabled(BUTTON_NAME_SAVE, false);
        handler.setButtonEnabled(BUTTON_NAME_SUBMIT, false);
        handler.setButtonEnabled(BUTTON_NAME_APPLY, false);
        handler.setButtonEnabled(BUTTON_NAME_APPROVE, false);
        handler.setButtonEnabled(BUTTON_NAME_REJECT, false);
        handler.setButtonEnabled(BUTTON_NAME_DOWNLOAD, false);
        handler.setButtonEnabled(BUTTON_NAME_DELETE, false);
        handler.setButtonEnabled(BUTTON_NAME_CLEAR, false);
        handler.setButtonEnabled(BUTTON_NAME_RESET, true);
        handler.setButtonEnabled(BUTTON_NAME_RETURN, true);

        scrnMsg.clearAllGUIAttribute(SCRN_ID);

    }

    /**
     * Initialize Set Value
     * @param scrnMsg NPAL1130BMsg
     */
    private static void initSetValue(NPAL1130BMsg scrnMsg) {

        scrnMsg.xxPageShowFromNum.setValue(BigDecimal.ZERO);
        scrnMsg.xxPageShowToNum.setValue(BigDecimal.ZERO);
        scrnMsg.xxPageShowOfNum.setValue(BigDecimal.ZERO);
    }

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
