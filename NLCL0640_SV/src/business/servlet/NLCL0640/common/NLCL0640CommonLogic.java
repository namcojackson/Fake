/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL0640.common;

import static business.servlet.NLCL0640.constant.NLCL0640Constant.BIZ_APP_ID;
import static business.servlet.NLCL0640.constant.NLCL0640Constant.BTN_ADD_COUNT_ITEM;
import static business.servlet.NLCL0640.constant.NLCL0640Constant.BTN_CMN_BTN_1;
import static business.servlet.NLCL0640.constant.NLCL0640Constant.BTN_CMN_BTN_10;
import static business.servlet.NLCL0640.constant.NLCL0640Constant.BTN_CMN_BTN_2;
import static business.servlet.NLCL0640.constant.NLCL0640Constant.BTN_CMN_BTN_3;
import static business.servlet.NLCL0640.constant.NLCL0640Constant.BTN_CMN_BTN_4;
import static business.servlet.NLCL0640.constant.NLCL0640Constant.BTN_CMN_BTN_5;
import static business.servlet.NLCL0640.constant.NLCL0640Constant.BTN_CMN_BTN_6;
import static business.servlet.NLCL0640.constant.NLCL0640Constant.BTN_CMN_BTN_7;
import static business.servlet.NLCL0640.constant.NLCL0640Constant.BTN_CMN_BTN_8;
import static business.servlet.NLCL0640.constant.NLCL0640Constant.BTN_CMN_BTN_9;
import static business.servlet.NLCL0640.constant.NLCL0640Constant.BTN_IMPORT_COUNT_ITEMS;
import static business.servlet.NLCL0640.constant.NLCL0640Constant.BTN_SEARCH_MDSE_NAME;
import static business.servlet.NLCL0640.constant.NLCL0640Constant.DISPLAY_NM_CNT_INP_QTY;
import static business.servlet.NLCL0640.constant.NLCL0640Constant.DISPLAY_NM_MDSE_CD;
import static business.servlet.NLCL0640.constant.NLCL0640Constant.DISPLAY_NM_MDSE_DESC_SHORT_TXT;
import static business.servlet.NLCL0640.constant.NLCL0640Constant.DISPLAY_NM_SER_NUM;
import static business.servlet.NLCL0640.constant.NLCL0640Constant.DISPLAY_NM_TECH_LOC_CD;
import static business.servlet.NLCL0640.constant.NLCL0640Constant.FUNC_EDIT;
import static business.servlet.NLCL0640.constant.NLCL0640Constant.POPUP_PARAM_FINISH_DLG_BTN_LABEL;
import static business.servlet.NLCL0640.constant.NLCL0640Constant.POPUP_PARAM_RECOUNT_DLG_BTN_LABEL;

import java.util.List;

import parts.servletcommon.EZDCommonHandler;
import business.servlet.NLCL0640.NLCL0640BMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PHYS_INVTY_CNT_STS;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/** 
 * <pre>
 * Business ID : NLCL0640 Tech PI Count
 * </pre>
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/10/2016   CITS            Makoto Okigami  Create          N/A
 * 01/17/2017   CITS            T.Kikuhara      Update          QC#16256
 * 05/09/2018   CITS            Y.Iwasaki       Update          QC#10572
 * 12/11/2018   Fujitsu         T.Ogura         Update          QC#28755
 * 12/17/2019   Fujitsu         T.Ogura         Update          QC#54986
 *</pre>
 */
public class NLCL0640CommonLogic {

    /**
     * The method explanation: The display control of the screen item
     * @param handler EZDCommonHandler
     * @param scrnMsg NLCL0640BMsg
     */
    public static void ctrlScrnItemDisp(EZDCommonHandler handler, NLCL0640BMsg scrnMsg) {

        // Header
        // START 2018/12/11 T.Ogura [QC#28755,MOD]
//        scrnMsg.techTocCd.setInputProtected(true);
//        scrnMsg.techNm.setInputProtected(true);
        scrnMsg.rtlWhCd.setInputProtected(true);
        scrnMsg.rtlWhNm.setInputProtected(true);
        // END   2018/12/11 T.Ogura [QC#28755,MOD]
        scrnMsg.physInvtyDt.setInputProtected(true);
        scrnMsg.physInvtyCtrlNm.setInputProtected(true);

        if (PHYS_INVTY_CNT_STS.SCHEDULED.equals(scrnMsg.physInvtyCntStsCd.getValue())) {

            // Detail
            scrnMsg.techLocCd_SL.setInputProtected(true);
            scrnMsg.mdseCd.setInputProtected(true);
            scrnMsg.mdseDescShortTxt.setInputProtected(true);
            scrnMsg.cntInpQty.setInputProtected(true);
            scrnMsg.serNum.setInputProtected(true);
            scrnMsg.xxFileData_UP.setInputProtected(true);

            // button activation
            handler.setButtonEnabled(BTN_SEARCH_MDSE_NAME, false);
            handler.setButtonEnabled(BTN_ADD_COUNT_ITEM, false);
            handler.setButtonEnabled(BTN_IMPORT_COUNT_ITEMS, false);
            // Submit/Clear button
            handler.setButtonProperties(BTN_CMN_BTN_2[0], BTN_CMN_BTN_2[1], BTN_CMN_BTN_2[2], 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_9[0], BTN_CMN_BTN_9[1], BTN_CMN_BTN_9[2], 0, null);

        } else if (PHYS_INVTY_CNT_STS.COUNTING.equals(scrnMsg.physInvtyCntStsCd.getValue())) {

            // Detail
            scrnMsg.techLocCd_SL.setInputProtected(false);
            scrnMsg.mdseCd.setInputProtected(false);
            scrnMsg.mdseDescShortTxt.setInputProtected(true);
            scrnMsg.cntInpQty.setInputProtected(false);
            scrnMsg.serNum.setInputProtected(false);
            scrnMsg.xxFileData_UP.setInputProtected(false);

            // button activation
            handler.setButtonEnabled(BTN_SEARCH_MDSE_NAME, true);
            handler.setButtonEnabled(BTN_ADD_COUNT_ITEM, true);
            handler.setButtonEnabled(BTN_IMPORT_COUNT_ITEMS, true);
            // Submit/Clear button
            handler.setButtonProperties(BTN_CMN_BTN_2[0], BTN_CMN_BTN_2[1], BTN_CMN_BTN_2[2], 1, null);
            handler.setButtonProperties(BTN_CMN_BTN_9[0], BTN_CMN_BTN_9[1], BTN_CMN_BTN_9[2], 1, null);

        } else if (PHYS_INVTY_CNT_STS.WAITRECOUNT.equals(scrnMsg.physInvtyCntStsCd.getValue())) {

            // Detail
            scrnMsg.techLocCd_SL.setInputProtected(true);
            scrnMsg.mdseCd.setInputProtected(true);
            scrnMsg.mdseDescShortTxt.setInputProtected(true);
            scrnMsg.cntInpQty.setInputProtected(true);
            scrnMsg.serNum.setInputProtected(true);
            scrnMsg.xxFileData_UP.setInputProtected(true);

            // button activation
            handler.setButtonEnabled(BTN_SEARCH_MDSE_NAME, false);
            handler.setButtonEnabled(BTN_ADD_COUNT_ITEM, false);
            handler.setButtonEnabled(BTN_IMPORT_COUNT_ITEMS, false);
            // Submit/Clear button
            handler.setButtonProperties(BTN_CMN_BTN_2[0], BTN_CMN_BTN_2[1], BTN_CMN_BTN_2[2], 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_9[0], BTN_CMN_BTN_9[1], BTN_CMN_BTN_9[2], 0, null);

        } else if (PHYS_INVTY_CNT_STS.RECOUNTING.equals(scrnMsg.physInvtyCntStsCd.getValue())) {

            // Detail
            scrnMsg.techLocCd_SL.setInputProtected(true);
            scrnMsg.mdseCd.setInputProtected(true);
            scrnMsg.mdseDescShortTxt.setInputProtected(true);
            scrnMsg.cntInpQty.setInputProtected(false);
            scrnMsg.serNum.setInputProtected(true);
            // START 2018/12/11 T.Ogura [QC#28755,MOD]
//            scrnMsg.xxFileData_UP.setInputProtected(true);
            scrnMsg.xxFileData_UP.setInputProtected(false);
            // END   2018/12/11 T.Ogura [QC#28755,MOD]

            // button activation
            handler.setButtonEnabled(BTN_SEARCH_MDSE_NAME, false);
            if (ZYPCommonFunc.hasValue(scrnMsg.mdseCd)) {
                scrnMsg.cntInpQty.setInputProtected(false);
                handler.setButtonEnabled(BTN_ADD_COUNT_ITEM, true);
            } else {
                scrnMsg.cntInpQty.setInputProtected(true);
                handler.setButtonEnabled(BTN_ADD_COUNT_ITEM, false);
            }
            // START 2018/12/11 T.Ogura [QC#28755,MOD]
//            handler.setButtonEnabled(BTN_IMPORT_COUNT_ITEMS, false);
            handler.setButtonEnabled(BTN_IMPORT_COUNT_ITEMS, true);
            // END   2018/12/11 T.Ogura [QC#28755,MOD]
            // Submit/Clear button
            handler.setButtonProperties(BTN_CMN_BTN_2[0], BTN_CMN_BTN_2[1], BTN_CMN_BTN_2[2], 1, null);
            handler.setButtonProperties(BTN_CMN_BTN_9[0], BTN_CMN_BTN_9[1], BTN_CMN_BTN_9[2], 1, null);

        } else {

            // Detail
            scrnMsg.techLocCd_SL.setInputProtected(true);
            scrnMsg.mdseCd.setInputProtected(true);
            scrnMsg.mdseDescShortTxt.setInputProtected(true);
            scrnMsg.cntInpQty.setInputProtected(true);
            scrnMsg.serNum.setInputProtected(true);
            scrnMsg.xxFileData_UP.setInputProtected(true);

            // button activation
            handler.setButtonEnabled(BTN_SEARCH_MDSE_NAME, false);
            handler.setButtonEnabled(BTN_ADD_COUNT_ITEM, false);
            handler.setButtonEnabled(BTN_IMPORT_COUNT_ITEMS, false);
            // Submit/Clear button
            handler.setButtonProperties(BTN_CMN_BTN_2[0], BTN_CMN_BTN_2[1], BTN_CMN_BTN_2[2], 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_9[0], BTN_CMN_BTN_9[1], BTN_CMN_BTN_9[2], 0, null);

        }

        // common button protection
        // 0 : inactive
        // 1 : active
        // QC#16256 add start
        // This Screen is Read only Screen
        if (isUpdatable()) {
            handler.setButtonProperties(BTN_CMN_BTN_1[0], BTN_CMN_BTN_1[1], BTN_CMN_BTN_1[2], 0, null);
            //handler.setButtonProperties(BTN_CMN_BTN_2[0], BTN_CMN_BTN_2[1], BTN_CMN_BTN_2[2], 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_3[0], BTN_CMN_BTN_3[1], BTN_CMN_BTN_3[2], 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_4[0], BTN_CMN_BTN_4[1], BTN_CMN_BTN_4[2], 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_5[0], BTN_CMN_BTN_5[1], BTN_CMN_BTN_5[2], 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_6[0], BTN_CMN_BTN_6[1], BTN_CMN_BTN_6[2], 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_7[0], BTN_CMN_BTN_7[1], BTN_CMN_BTN_7[2], 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_8[0], BTN_CMN_BTN_8[1], BTN_CMN_BTN_8[2], 0, null);
            //handler.setButtonProperties(BTN_CMN_BTN_9[0], BTN_CMN_BTN_9[1], BTN_CMN_BTN_9[2], 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_10[0], BTN_CMN_BTN_10[1], BTN_CMN_BTN_10[2], 1, null);
        } else {
            handler.setButtonProperties(BTN_CMN_BTN_1[0], BTN_CMN_BTN_1[1], BTN_CMN_BTN_1[2], 0, null);
            //handler.setButtonProperties(BTN_CMN_BTN_2[0], BTN_CMN_BTN_2[1], BTN_CMN_BTN_2[2], 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_3[0], BTN_CMN_BTN_3[1], BTN_CMN_BTN_3[2], 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_4[0], BTN_CMN_BTN_4[1], BTN_CMN_BTN_4[2], 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_5[0], BTN_CMN_BTN_5[1], BTN_CMN_BTN_5[2], 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_6[0], BTN_CMN_BTN_6[1], BTN_CMN_BTN_6[2], 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_7[0], BTN_CMN_BTN_7[1], BTN_CMN_BTN_7[2], 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_8[0], BTN_CMN_BTN_8[1], BTN_CMN_BTN_8[2], 0, null);
            //handler.setButtonProperties(BTN_CMN_BTN_9[0], BTN_CMN_BTN_9[1], BTN_CMN_BTN_9[2], 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_10[0], BTN_CMN_BTN_10[1], BTN_CMN_BTN_10[2], 1, null);
        }
        // QC#16256 add end

    }

    /**
     * <p>
     * Sets the name for the error message.
     * </p>
     * @param scrnMsg NLCL0640BMsg
     */
    public static void setNameForMessage(NLCL0640BMsg scrnMsg) {

        // Detail
        scrnMsg.techLocCd_SL.setNameForMessage(DISPLAY_NM_TECH_LOC_CD);
        scrnMsg.mdseCd.setNameForMessage(DISPLAY_NM_MDSE_CD);
        scrnMsg.mdseDescShortTxt.setNameForMessage(DISPLAY_NM_MDSE_DESC_SHORT_TXT);
        scrnMsg.cntInpQty.setNameForMessage(DISPLAY_NM_CNT_INP_QTY);
        scrnMsg.serNum.setNameForMessage(DISPLAY_NM_SER_NUM);

    }

    /**
     * Set Tech PI Finish Dialog Param
     * @param scrnMsg NLCL0640BMsg
     * @param glblCmpyCd String
     * @return Tech PI Finish Dialog Parameter
     */
    public static Object[] setTechPIFinishDialogParam(NLCL0640BMsg scrnMsg, String glblCmpyCd) {

        // Paramter Set
        // START 2019/12/17 T.Ogura [QC#54986,MOD]
//        Object[] param = new Object[2];
//        param[0] = POPUP_PARAM_FINISH_DLG_MSG;
//        param[1] = POPUP_PARAM_FINISH_DLG_BTN_LABEL;
        Object[] param = new Object[1];
        param[0] = POPUP_PARAM_FINISH_DLG_BTN_LABEL;
        // END   2019/12/17 T.Ogura [QC#54986,MOD]

        return param;

    }

    /**
     * Set ReCount Dialog Param
     * @param scrnMsg NLCL0640BMsg
     * @param glblCmpyCd String
     * @return Tech PI Finish Dialog Parameter
     */
    public static Object[] setReCountDialogParam(NLCL0640BMsg scrnMsg, String glblCmpyCd) {

        // Paramter Set
        // START 2019/12/17 T.Ogura [QC#54986,MOD]
//        Object[] param = new Object[2];
//        param[0] = POPUP_PARAM_RECOUNT_DLG_MSG;
//        param[1] = POPUP_PARAM_RECOUNT_DLG_BTN_LABEL;
        Object[] param = new Object[1];
        param[0] = POPUP_PARAM_RECOUNT_DLG_BTN_LABEL;
        // END   2019/12/17 T.Ogura [QC#54986,MOD]

        return param;

    }

    // QC#16256 add start
    /**
     * Function check
     * @return true:edit false:reference
     */
    private static boolean isUpdatable() {
        S21UserProfileService profileService = S21UserProfileServiceFactory.getInstance().getService();
        List<String> functionList = profileService.getAuthorizedFunctionCodeListForBizAppId(BIZ_APP_ID);
        return functionList.contains(FUNC_EDIT);
    }
    // QC#16256 add end

}
