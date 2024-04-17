/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL0200.common;

import static business.servlet.NMAL0200.constant.NMAL0200Constant.BIZ_APP_ID;
import static business.servlet.NMAL0200.constant.NMAL0200Constant.BTN_CMN_BTN_10_ID;
import static business.servlet.NMAL0200.constant.NMAL0200Constant.BTN_CMN_BTN_10_NAME;
import static business.servlet.NMAL0200.constant.NMAL0200Constant.BTN_CMN_BTN_10_VAL;
import static business.servlet.NMAL0200.constant.NMAL0200Constant.BTN_CMN_BTN_1_ID;
import static business.servlet.NMAL0200.constant.NMAL0200Constant.BTN_CMN_BTN_1_NAME;
import static business.servlet.NMAL0200.constant.NMAL0200Constant.BTN_CMN_BTN_1_VAL;
import static business.servlet.NMAL0200.constant.NMAL0200Constant.BTN_CMN_BTN_2_ID;
import static business.servlet.NMAL0200.constant.NMAL0200Constant.BTN_CMN_BTN_2_NAME;
import static business.servlet.NMAL0200.constant.NMAL0200Constant.BTN_CMN_BTN_2_VAL;
import static business.servlet.NMAL0200.constant.NMAL0200Constant.BTN_CMN_BTN_3_ID;
import static business.servlet.NMAL0200.constant.NMAL0200Constant.BTN_CMN_BTN_3_NAME;
import static business.servlet.NMAL0200.constant.NMAL0200Constant.BTN_CMN_BTN_3_VAL;
import static business.servlet.NMAL0200.constant.NMAL0200Constant.BTN_CMN_BTN_4_ID;
import static business.servlet.NMAL0200.constant.NMAL0200Constant.BTN_CMN_BTN_4_NAME;
import static business.servlet.NMAL0200.constant.NMAL0200Constant.BTN_CMN_BTN_4_VAL;
import static business.servlet.NMAL0200.constant.NMAL0200Constant.BTN_CMN_BTN_5_ID;
import static business.servlet.NMAL0200.constant.NMAL0200Constant.BTN_CMN_BTN_5_NAME;
import static business.servlet.NMAL0200.constant.NMAL0200Constant.BTN_CMN_BTN_5_VAL;
import static business.servlet.NMAL0200.constant.NMAL0200Constant.BTN_CMN_BTN_6_ID;
import static business.servlet.NMAL0200.constant.NMAL0200Constant.BTN_CMN_BTN_6_NAME;
import static business.servlet.NMAL0200.constant.NMAL0200Constant.BTN_CMN_BTN_6_VAL;
import static business.servlet.NMAL0200.constant.NMAL0200Constant.BTN_CMN_BTN_7_ID;
import static business.servlet.NMAL0200.constant.NMAL0200Constant.BTN_CMN_BTN_7_NAME;
import static business.servlet.NMAL0200.constant.NMAL0200Constant.BTN_CMN_BTN_7_VAL;
import static business.servlet.NMAL0200.constant.NMAL0200Constant.BTN_CMN_BTN_8_ID;
import static business.servlet.NMAL0200.constant.NMAL0200Constant.BTN_CMN_BTN_8_NAME;
import static business.servlet.NMAL0200.constant.NMAL0200Constant.BTN_CMN_BTN_8_VAL;
import static business.servlet.NMAL0200.constant.NMAL0200Constant.BTN_CMN_BTN_9_ID;
import static business.servlet.NMAL0200.constant.NMAL0200Constant.BTN_CMN_BTN_9_NAME;
import static business.servlet.NMAL0200.constant.NMAL0200Constant.BTN_CMN_BTN_9_VAL;
import static business.servlet.NMAL0200.constant.NMAL0200Constant.FUNCTION_UPDATE;

import java.util.List;

import parts.servletcommon.EZDCommonHandler;
import business.servlet.NMAL0200.NMAL0200BMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_STRU_ELMNT_TP;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * Business ID : NMAL0200 Product Categorization Maintenance
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/07/2016   CITS            K.Ogino         Create          N/A
 *</pre>
 */
public class NMAL0200CommonLogic {

    /**
     * Set Common Button for Initialized screen
     * @param handler EZDCommonHandler
     */
    public static void setCommonButtonInit(EZDCommonHandler handler) {
        // 0 : inactive
        // 1 : active
        if (isUpdatable()) {
            handler.setButtonProperties(BTN_CMN_BTN_2_ID, BTN_CMN_BTN_2_NAME, BTN_CMN_BTN_2_VAL, 1, null);
        } else {
            handler.setButtonProperties(BTN_CMN_BTN_2_ID, BTN_CMN_BTN_2_NAME, BTN_CMN_BTN_2_VAL, 0, null);
        }
        handler.setButtonProperties(BTN_CMN_BTN_1_ID, BTN_CMN_BTN_1_NAME, BTN_CMN_BTN_1_VAL, 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_3_ID, BTN_CMN_BTN_3_NAME, BTN_CMN_BTN_3_VAL, 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_4_ID, BTN_CMN_BTN_4_NAME, BTN_CMN_BTN_4_VAL, 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_5_ID, BTN_CMN_BTN_5_NAME, BTN_CMN_BTN_5_VAL, 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_6_ID, BTN_CMN_BTN_6_NAME, BTN_CMN_BTN_6_VAL, 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_7_ID, BTN_CMN_BTN_7_NAME, BTN_CMN_BTN_7_VAL, 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_8_ID, BTN_CMN_BTN_8_NAME, BTN_CMN_BTN_8_VAL, 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_9_ID, BTN_CMN_BTN_9_NAME, BTN_CMN_BTN_9_VAL, 1, null);
        handler.setButtonProperties(BTN_CMN_BTN_10_ID, BTN_CMN_BTN_10_NAME, BTN_CMN_BTN_10_VAL, 1, null);

    }

    /**
     * Set screen Attribute
     * @param scrnMsg NMAL0200BMsg
     */
    public static void setTableColumnAttr(NMAL0200BMsg scrnMsg) {
        if (isUpdatable()) {
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                if (ZYPConstant.FLG_OFF_N.equals(scrnMsg.A.no(i).xxMstChkFlg_A1.getValue())) {
                    // New Record
                    scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(false);
                    scrnMsg.A.no(i).mdseStruElmntTpCd_A1.setInputProtected(false);
                    scrnMsg.A.no(i).prodCtrlCd_A1.setInputProtected(false);
                    scrnMsg.A.no(i).prodCtrlNm_A1.setInputProtected(false);
                    if (MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_3.equals(scrnMsg.A.no(i).mdseStruElmntTpCd_A1.getValue())) {
                        scrnMsg.A.no(i).scdProdHrchCd_A1.setInputProtected(false);
                        scrnMsg.A.no(i).xxLinkAncr_A1.setInputProtected(false);
                    } else {
                        scrnMsg.A.no(i).scdProdHrchCd_A1.setInputProtected(true);
                        scrnMsg.A.no(i).xxLinkAncr_A1.setInputProtected(true);
                    }
                    scrnMsg.A.no(i).xxDplyByCtrlItemCdNm_A1.setInputProtected(true);
                } else {
                    // DB Record
                    scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(false);
                    scrnMsg.A.no(i).mdseStruElmntTpCd_A1.setInputProtected(true);
                    scrnMsg.A.no(i).prodCtrlCd_A1.setInputProtected(true);
                    scrnMsg.A.no(i).prodCtrlNm_A1.setInputProtected(false);
                    if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).xxBtnFlg_A1.getValue())) {
                        scrnMsg.A.no(i).scdProdHrchCd_A1.setInputProtected(true);
                        scrnMsg.A.no(i).xxLinkAncr_A1.setInputProtected(true);
                    } else {
                        scrnMsg.A.no(i).scdProdHrchCd_A1.setInputProtected(false);
                        scrnMsg.A.no(i).xxLinkAncr_A1.setInputProtected(false);
                    }
                    scrnMsg.A.no(i).xxDplyByCtrlItemCdNm_A1.setInputProtected(true);
                }
            }
        } else {
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(true);
                scrnMsg.A.no(i).mdseStruElmntTpCd_A1.setInputProtected(true);
                scrnMsg.A.no(i).prodCtrlCd_A1.setInputProtected(true);
                scrnMsg.A.no(i).prodCtrlNm_A1.setInputProtected(true);
                scrnMsg.A.no(i).scdProdHrchCd_A1.setInputProtected(true);
                scrnMsg.A.no(i).xxLinkAncr_A1.setInputProtected(true);
                scrnMsg.A.no(i).xxDplyByCtrlItemCdNm_A1.setInputProtected(true);
            }
        }
    }

    /**
     * AddLine event set screen Attribute
     * @param scrnMsg NMAL0200BMsg
     * @param idx int
     */
    public static void setTableColumnAttr4AddLine(NMAL0200BMsg scrnMsg, int idx) {
        scrnMsg.A.no(idx).xxChkBox_A1.setInputProtected(false);
        scrnMsg.A.no(idx).mdseStruElmntTpCd_A1.setInputProtected(false);
        scrnMsg.A.no(idx).prodCtrlCd_A1.setInputProtected(false);
        scrnMsg.A.no(idx).prodCtrlNm_A1.setInputProtected(false);
        scrnMsg.A.no(idx).scdProdHrchCd_A1.setInputProtected(true);
        scrnMsg.A.no(idx).xxLinkAncr_A1.setInputProtected(true);
        scrnMsg.A.no(idx).xxDplyByCtrlItemCdNm_A1.setInputProtected(true);
    }

    /**
     * Check error info
     * @param scrnMsg NMAL0200BMsg
     */
    public static void chkErrorInfo(NMAL0200BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).mdseStruElmntTpCd_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).prodCtrlCd_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).prodCtrlNm_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).scdProdHrchCd_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxDplyByCtrlItemCdNm_A1);
        }
    }

    /**
     * Function check
     * @return true:edit false:reference
     */
    public static boolean isUpdatable() {
        List<String> functionList = getFunctionList();
        return functionList.contains(FUNCTION_UPDATE);
    }

    /**
     * Function List
     * @return List<String> Function List
     */
    private static List<String> getFunctionList() {
        S21UserProfileService profileService = S21UserProfileServiceFactory.getInstance().getService();
        List<String> functionList = profileService.getAuthorizedFunctionCodeListForBizAppId(BIZ_APP_ID);
        return functionList;
    }
}
