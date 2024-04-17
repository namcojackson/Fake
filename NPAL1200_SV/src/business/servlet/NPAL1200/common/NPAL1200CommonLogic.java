/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1200.common;

import static business.servlet.NPAL1200.constant.NPAL1200Constant.BTN_CMN_BTN_1;
import static business.servlet.NPAL1200.constant.NPAL1200Constant.BTN_CMN_BTN_10;
import static business.servlet.NPAL1200.constant.NPAL1200Constant.BTN_CMN_BTN_2;
import static business.servlet.NPAL1200.constant.NPAL1200Constant.BTN_CMN_BTN_3;
import static business.servlet.NPAL1200.constant.NPAL1200Constant.BTN_CMN_BTN_4;
import static business.servlet.NPAL1200.constant.NPAL1200Constant.BTN_CMN_BTN_5;
import static business.servlet.NPAL1200.constant.NPAL1200Constant.BTN_CMN_BTN_6;
import static business.servlet.NPAL1200.constant.NPAL1200Constant.BTN_CMN_BTN_7;
import static business.servlet.NPAL1200.constant.NPAL1200Constant.BTN_CMN_BTN_8;
import static business.servlet.NPAL1200.constant.NPAL1200Constant.BTN_CMN_BTN_9;
import static business.servlet.NPAL1200.constant.NPAL1200Constant.BUSINESS_APPLICATION_ID;
import static business.servlet.NPAL1200.constant.NPAL1200Constant.FRAC_DEGIT;
import static business.servlet.NPAL1200.constant.NPAL1200Constant.FUNCTION_UPDATE;
import static business.servlet.NPAL1200.constant.NPAL1200Constant.MODE_ADD;
import static business.servlet.NPAL1200.constant.NPAL1200Constant.MODE_DELETE;
import static business.servlet.NPAL1200.constant.NPAL1200Constant.MODE_EDIT;
import static business.servlet.NPAL1200.constant.NPAL1200Constant.MODE_INIT;
import static business.servlet.NPAL1200.constant.NPAL1200Constant.MODE_NORMAL;
import static business.servlet.NPAL1200.constant.NPAL1200Constant.SCREEN_ID;
import java.util.List;

import parts.common.EZDBItem;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NPAL1200.NPAL1200BMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WH_OWNR;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * Business ID : NPAL1200 Insourcing Planning Setup
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/25/2016   CITS       Yasushi Nomura        Create          N/A
 * 01/17/2017   CITS            T.Kikuhara      Update          QC#16256
 * 03/01/2023   CITS            R.Kurahashi     Update          QC#61128
 *</pre>
 */
public class NPAL1200CommonLogic {

    /**
     * <pre>
     * Initial common button
     * </pre>
     * @param handler EZCommandHandler
     */
    public static void initCommonButton(EZDCommonHandler handler) {
        // QC#16256 add start
        // This Screen is Read only Screen
        if (isUpdatable()) {
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
        } else {
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
        // QC#16256 add end
    }

    /**
     * <pre>
     * Details are initialized (sorting sign deletion and BG color control).
     * </pre>
     * @param scrnMsg Screen Msg
     * @param baseContents String[][]
     */
    public static void clearGUIAttribute(NPAL1200BMsg scrnMsg, String[][] baseContents) {
        S21SortColumnIMGController.clearIMG(SCREEN_ID, scrnMsg, baseContents);
        scrnMsg.clearAllGUIAttribute(SCREEN_ID);
    }

    /**
     * @param handler S21CommonHandler
     * @param scrnMsg NPAL1200BMsg
     */
    public static void control(S21CommonHandler handler, NPAL1200BMsg scrnMsg) {
        // Common
        disable(scrnMsg.rtlWhNm_HF);
        disable(scrnMsg.rtlWhNm_HT);
        disable(scrnMsg.rtlWhNm_DF);
        disable(scrnMsg.rtlWhNm_DT);
        if (scrnMsg.xxNum_MD.getValueInt() == MODE_INIT) {
            handler.setButtonEnabled("AddDetailLine", true);
            handler.setButtonEnabled("EditDetailLine", false);
            handler.setButtonEnabled("CancelDetailLine", false);
        } else if (scrnMsg.xxNum_MD.getValueInt() == MODE_NORMAL) {
            handler.setButtonEnabled("AddDetailLine", true);
            if (0 < scrnMsg.A.getValidCount()) {
                handler.setButtonEnabled("EditDetailLine", true);
                handler.setButtonEnabled("CancelDetailLine", true);
            } else {
                handler.setButtonEnabled("EditDetailLine", false);
                handler.setButtonEnabled("CancelDetailLine", false);
            }
            handler.setButtonProperties(BTN_CMN_BTN_2[0], BTN_CMN_BTN_2[1], BTN_CMN_BTN_2[2], 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_9[0], BTN_CMN_BTN_9[1], BTN_CMN_BTN_9[2], 0, null);
        } else if (scrnMsg.xxNum_MD.getValueInt() == MODE_ADD) {
            handler.setButtonEnabled("AddDetailLine", true);
            handler.setButtonEnabled("EditDetailLine", false);
            handler.setButtonEnabled("CancelDetailLine", true);
            if (isUpdatable()) {
                handler.setButtonProperties(BTN_CMN_BTN_2[0], BTN_CMN_BTN_2[1], BTN_CMN_BTN_2[2], 1, null);
            }
            handler.setButtonProperties(BTN_CMN_BTN_9[0], BTN_CMN_BTN_9[1], BTN_CMN_BTN_9[2], 1, null);
        } else if (scrnMsg.xxNum_MD.getValueInt() == MODE_EDIT) {
            handler.setButtonEnabled("AddDetailLine", false);
            handler.setButtonEnabled("EditDetailLine", false);
            handler.setButtonEnabled("CancelDetailLine", false);
            if (isUpdatable()) {
                handler.setButtonProperties(BTN_CMN_BTN_2[0], BTN_CMN_BTN_2[1], BTN_CMN_BTN_2[2], 1, null);
            }
            handler.setButtonProperties(BTN_CMN_BTN_9[0], BTN_CMN_BTN_9[1], BTN_CMN_BTN_9[2], 1, null);
        } else if (scrnMsg.xxNum_MD.getValueInt() == MODE_DELETE) {
            handler.setButtonEnabled("AddDetailLine", false);
            handler.setButtonEnabled("EditDetailLine", false);
            handler.setButtonEnabled("CancelDetailLine", false);
            if (isUpdatable()) {
                handler.setButtonProperties(BTN_CMN_BTN_2[0], BTN_CMN_BTN_2[1], BTN_CMN_BTN_2[2], 1, null);
            }
            handler.setButtonProperties(BTN_CMN_BTN_9[0], BTN_CMN_BTN_9[1], BTN_CMN_BTN_9[2], 1, null);
        }
    }

    private static void disable(EZDBItem item) {
        item.setInputProtected(true);
    }

    /**
     * clear table
     * @param scrnMsg NPAL1200BMsg
     */
    public static void clearTable(NPAL1200BMsg scrnMsg) {
        scrnMsg.A.clear();
        ZYPTableUtil.clear(scrnMsg.A);
        scrnMsg.xxPageShowFromNum.clear();
        scrnMsg.xxPageShowToNum.clear();
        scrnMsg.xxPageShowOfNum.clear();
    }

    /**
     * Table Column Protect
     * @param scrnMsg NPAL1200BMsg
     */
    public static void setTableScreen(NPAL1200BMsg scrnMsg) {

        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        tblColor.setAlternateRowsBG("A", scrnMsg.A);

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).fromSrcZnCd_D1.setInputProtected(true);
            scrnMsg.A.no(i).fromRtlWhCd_D1.setInputProtected(true);
            scrnMsg.A.no(i).rtlWhNm_D1.setInputProtected(true);
            scrnMsg.A.no(i).toSrcZnCd_D1.setInputProtected(true);
            scrnMsg.A.no(i).toRtlWhCd_D1.setInputProtected(true);
            scrnMsg.A.no(i).rtlWhNm_D2.setInputProtected(true);
            scrnMsg.A.no(i).insrcItemPrcThrhdAmt_D1.setAppFracDigit(FRAC_DEGIT);
            if (scrnMsg.xxNum_MD.getValueInt() == MODE_NORMAL) {
                scrnMsg.A.no(i).xxChkBox_D1.setInputProtected(false);
                scrnMsg.A.no(i).mdseItemClsTpCd_SE.setInputProtected(true);
                // QC#61128 Add Start
                scrnMsg.A.no(i).prchReqTpCd_SE.setInputProtected(true);
                // QC#61128 Add End
                scrnMsg.A.no(i).insrcItemPrcThrhdAmt_D1.setInputProtected(true);
                scrnMsg.A.no(i).insrcRnkSortNum_D1.setInputProtected(true);
                scrnMsg.A.no(i).insrcEnblFlg_SD.setInputProtected(true);
            }
            if (scrnMsg.xxNum_MD.getValueInt() == MODE_ADD) {
                if (scrnMsg.A.no(i).xxNum_M2.getValueInt() == MODE_EDIT) {
                    scrnMsg.A.no(i).xxChkBox_D1.setInputProtected(false);
                    scrnMsg.A.no(i).mdseItemClsTpCd_SE.setInputProtected(false);
                    // QC#61128 Add Start
                    if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).whOwnrCd_D1.getValue()) || !WH_OWNR.MNX.equals(scrnMsg.A.no(i).whOwnrCd_D1.getValue())) {
                        scrnMsg.A.no(i).prchReqTpCd_SE.setInputProtected(false);
                    }
                    // QC#61128 Add Start
                    scrnMsg.A.no(i).insrcItemPrcThrhdAmt_D1.setInputProtected(false);
                    scrnMsg.A.no(i).insrcRnkSortNum_D1.setInputProtected(false);
                    scrnMsg.A.no(i).insrcEnblFlg_SD.setInputProtected(false);
                } else {
                    scrnMsg.A.no(i).xxChkBox_D1.setInputProtected(true);
                    scrnMsg.A.no(i).mdseItemClsTpCd_SE.setInputProtected(true);
                    // QC#61128 Add Start
                    scrnMsg.A.no(i).prchReqTpCd_SE.setInputProtected(true);
                    // QC#61128 Add End
                    scrnMsg.A.no(i).insrcItemPrcThrhdAmt_D1.setInputProtected(true);
                    scrnMsg.A.no(i).insrcRnkSortNum_D1.setInputProtected(true);
                    scrnMsg.A.no(i).insrcEnblFlg_SD.setInputProtected(true);
                }
            }
            if (scrnMsg.xxNum_MD.getValueInt() == MODE_EDIT) {
                if (scrnMsg.A.no(i).xxNum_M2.getValueInt() == MODE_EDIT) {
                    scrnMsg.A.no(i).xxChkBox_D1.setInputProtected(true);
                    scrnMsg.A.no(i).mdseItemClsTpCd_SE.setInputProtected(false);
                    // QC#61128 Add Start
                    if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).whOwnrCd_D1.getValue()) || !WH_OWNR.MNX.equals(scrnMsg.A.no(i).whOwnrCd_D1.getValue())) {
                        scrnMsg.A.no(i).prchReqTpCd_SE.setInputProtected(false);
                    }
                    // QC#61128 Add Start
                    scrnMsg.A.no(i).insrcItemPrcThrhdAmt_D1.setInputProtected(false);
                    scrnMsg.A.no(i).insrcRnkSortNum_D1.setInputProtected(false);
                    scrnMsg.A.no(i).insrcEnblFlg_SD.setInputProtected(false);
                } else {
                    scrnMsg.A.no(i).xxChkBox_D1.setInputProtected(true);
                    scrnMsg.A.no(i).mdseItemClsTpCd_SE.setInputProtected(true);
                    // QC#61128 Add Start
                    scrnMsg.A.no(i).prchReqTpCd_SE.setInputProtected(true);
                    // QC#61128 Add End
                    scrnMsg.A.no(i).insrcItemPrcThrhdAmt_D1.setInputProtected(true);
                    scrnMsg.A.no(i).insrcRnkSortNum_D1.setInputProtected(true);
                    scrnMsg.A.no(i).insrcEnblFlg_SD.setInputProtected(true);
                }
            }
        }
    }

    /**
     * Add check item
     * @param scrnMsg NPAL1200BMsg
     */
    public static void addCheckItem(NPAL1200BMsg scrnMsg) {
        scrnMsg.addCheckItem(scrnMsg.rtlWhCd_HF);
        scrnMsg.addCheckItem(scrnMsg.rtlWhCd_HT);
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).mdseItemClsTpCd_SE);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).insrcItemPrcThrhdAmt_D1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).insrcRnkSortNum_D1);
            // QC#61128 Add Start
            scrnMsg.addCheckItem(scrnMsg.A.no(i).prchReqTpCd_SE);
            // QC#61128 Add End
        }
        scrnMsg.putErrorScreen();
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
