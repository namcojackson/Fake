/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL1030.common;

import static business.servlet.NLCL1030.constant.NLCL1030Constant.BIZ_APP_ID;
import static business.servlet.NLCL1030.constant.NLCL1030Constant.BTN_CMN_BTN_1;
import static business.servlet.NLCL1030.constant.NLCL1030Constant.BTN_CMN_BTN_10;
import static business.servlet.NLCL1030.constant.NLCL1030Constant.BTN_CMN_BTN_2;
import static business.servlet.NLCL1030.constant.NLCL1030Constant.BTN_CMN_BTN_3;
import static business.servlet.NLCL1030.constant.NLCL1030Constant.BTN_CMN_BTN_4;
import static business.servlet.NLCL1030.constant.NLCL1030Constant.BTN_CMN_BTN_5;
import static business.servlet.NLCL1030.constant.NLCL1030Constant.BTN_CMN_BTN_6;
import static business.servlet.NLCL1030.constant.NLCL1030Constant.BTN_CMN_BTN_7;
import static business.servlet.NLCL1030.constant.NLCL1030Constant.BTN_CMN_BTN_8;
import static business.servlet.NLCL1030.constant.NLCL1030Constant.BTN_CMN_BTN_9;
import static business.servlet.NLCL1030.constant.NLCL1030Constant.BTN_DELETE_SEARCH;
import static business.servlet.NLCL1030.constant.NLCL1030Constant.BTN_SAVE_SEARCH;
import static business.servlet.NLCL1030.constant.NLCL1030Constant.BTN_SEARCH;
import static business.servlet.NLCL1030.constant.NLCL1030Constant.DISPLAY_ABC_NM;
import static business.servlet.NLCL1030.constant.NLCL1030Constant.DISPLAY_CURRENT_PAGE_NUMBER;
import static business.servlet.NLCL1030.constant.NLCL1030Constant.DISPLAY_NM_SRCH_OPT_NM;
import static business.servlet.NLCL1030.constant.NLCL1030Constant.DISPLAY_SRCH_OPT_PK;
import static business.servlet.NLCL1030.constant.NLCL1030Constant.DISPLAY_SWH_CD;
import static business.servlet.NLCL1030.constant.NLCL1030Constant.DISPLAY_WH_CD;
import static business.servlet.NLCL1030.constant.NLCL1030Constant.DISPLAY_WH_NM;
import static business.servlet.NLCL1030.constant.NLCL1030Constant.DISPLAY_WH_TP_CD;
import static business.servlet.NLCL1030.constant.NLCL1030Constant.FUNCTION_UPDATE;
import static business.servlet.NLCL1030.constant.NLCL1030Constant.INVTY_ACCT_CD_01;
import static business.servlet.NLCL1030.constant.NLCL1030Constant.SCRN_ID;

import java.util.List;

import parts.common.EZDBStringItem;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NLCL1030.NLCL1030BMsg;

import com.canon.cusa.s21.common.NMX.NMXC100001.NMXC100001EnableWH;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * Business ID : NLCL1030 Inventory ABC Analysis Search
 * Function Name : Common Logic
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/21/2016   CITS            T.Hakodate      Create          N/A
 * 01/17/2017   CITS            T.Kikuhara      Update          QC#16256
 * 11/21/2018   Fujitsu         T.Ogura         Update          QC#29123
 *</pre>
 */
public class NLCL1030CommonLogic {

    /**
     * The method explanation: The display control of the screen item
     * for Initialized screen
     * @param handler EZDCommonHandler
     * @param scrnMsg NPAL1220BMsg
     * @param functionList List<String>S
     */
    public static void ctrlScrnItemDispInit(EZDCommonHandler handler, NLCL1030BMsg scrnMsg, List<String> functionList) {

        // button activation
        handler.setButtonEnabled(BTN_SAVE_SEARCH, true);
        handler.setButtonEnabled(BTN_DELETE_SEARCH, true);
        handler.setButtonEnabled(BTN_SEARCH, true);

        // common button protection
        // 0 : inactive
        // 1 : active
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
     * Table Column Protect
     * @param scrnMsg NLCL1030BMsg
     */
    public static void setTableScreen(NLCL1030BMsg scrnMsg) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID, scrnMsg);

        tblColor.setAlternateRowsBG("A", scrnMsg.A);

        for (int rowIndex = 0; rowIndex < scrnMsg.A.getValidCount(); rowIndex++) {
            scrnMsg.A.no(rowIndex).abcAsgNm_A.setInputProtected(true);
            scrnMsg.A.no(rowIndex).abcAsgDescTxt_A.setInputProtected(true);
            scrnMsg.A.no(rowIndex).rtlWhCatgDescTxt_A.setInputProtected(true);
            scrnMsg.A.no(rowIndex).rtlWhCdSrchTxt_AW.setInputProtected(true);
            scrnMsg.A.no(rowIndex).rtlWhNmSrchTxt_AW.setInputProtected(true);
            scrnMsg.A.no(rowIndex).rtlWhCdSrchTxt_AS.setInputProtected(true);
            scrnMsg.A.no(rowIndex).abcAnlsCritNm_A.setInputProtected(true);
            scrnMsg.A.no(rowIndex).effFromDt_A.setInputProtected(true);
            scrnMsg.A.no(rowIndex).effThruDt_A.setInputProtected(true);
            scrnMsg.A.no(rowIndex).abcAnlsClsNm_A.setInputProtected(true);
        }
    }

    /**
     * <pre>
     * The input scrnMsg is cleared.
     * </pre>
     * @param scrnMsg NLCL1030BMsg
     */
    public static void clearScreenMsg(NLCL1030BMsg scrnMsg) {

        // Header
        scrnMsg.srchOptNm_S.clear();
        scrnMsg.srchOptPk_S.clear();
        scrnMsg.abcAsgNm.clear();
        scrnMsg.rtlWhCatgCd_H1.clear();
        scrnMsg.rtlWhCdSrchTxt_RW.clear();
        scrnMsg.rtlWhNmSrchTxt_RW.clear();
        scrnMsg.rtlSwhCdSrchTxt_SW.clear();
        scrnMsg.xxPageShowCurNum_A.clear();
        scrnMsg.xxPageShowTotNum_A.clear();

        // Detail
        clearTable(scrnMsg);
    }

    /**
     * clear table
     * @param scrnMsg NLCL1030BMsg
     */
    public static void clearTable(NLCL1030BMsg scrnMsg) {

        ZYPTableUtil.clear(scrnMsg.A);
        scrnMsg.xxPageShowFromNum_A.clear();
        scrnMsg.xxPageShowToNum_A.clear();
        scrnMsg.xxPageShowOfNum_A.clear();
    }

    /**
     * set Name For Message
     * @param scrnMsg NLCL1030BMsg
     */
    public static void setNameForMessage(NLCL1030BMsg scrnMsg) {
        scrnMsg.srchOptPk_S.setNameForMessage(DISPLAY_SRCH_OPT_PK);
        scrnMsg.srchOptNm_S.setNameForMessage(DISPLAY_NM_SRCH_OPT_NM);
        scrnMsg.abcAsgNm.setNameForMessage(DISPLAY_ABC_NM);
        scrnMsg.rtlWhCatgCd_H1.setNameForMessage(DISPLAY_WH_TP_CD);
        scrnMsg.rtlWhCdSrchTxt_RW.setNameForMessage(DISPLAY_WH_CD);
        scrnMsg.rtlWhNmSrchTxt_RW.setNameForMessage(DISPLAY_WH_NM);
        scrnMsg.rtlSwhCdSrchTxt_SW.setNameForMessage(DISPLAY_SWH_CD);
        scrnMsg.xxPageShowCurNum_A.setNameForMessage(DISPLAY_CURRENT_PAGE_NUMBER);

    }

    /**
     * add Check Item Header
     * @param scrnMsg NLCL1030BMsg
     */
    public static void addCheckItemHeader(NLCL1030BMsg scrnMsg) {

        scrnMsg.addCheckItem(scrnMsg.srchOptNm_S);
        scrnMsg.addCheckItem(scrnMsg.srchOptPk_S);

        scrnMsg.addCheckItem(scrnMsg.abcAsgNm);
        scrnMsg.addCheckItem(scrnMsg.rtlWhCatgCd_H1);
        scrnMsg.addCheckItem(scrnMsg.rtlWhCdSrchTxt_RW);
        scrnMsg.addCheckItem(scrnMsg.rtlWhNmSrchTxt_RW);
        scrnMsg.addCheckItem(scrnMsg.rtlSwhCdSrchTxt_SW);

    }

    /**
     * The method explanation: set initial parameter to call popup.
     * @param scrnMsg NLCL1030BMsg
     */
    public static void setInitParamForLocationPopup(NLCL1030BMsg scrnMsg) {

        scrnMsg.srchOptTxt_P1.clear();
        // START 2018/11/21 T.Ogura [QC#29123,MOD]
//        scrnMsg.invtyLocNm_P1.clear();
        scrnMsg.srchOptTxt_P2.clear();
        // END   2018/11/21 T.Ogura [QC#29123,MOD]
        scrnMsg.xxLocRoleTpCdListTxt_P1.clear();
        scrnMsg.xxChkInpValFlg_P1.clear();
        scrnMsg.xxSelFlg_P1.clear();
        scrnMsg.locRoleTpCd_P1.clear();
        scrnMsg.rtlWhCdSrchTxt_PR.clear();
        scrnMsg.rtlWhNmSrchTxt_PR.clear();
        scrnMsg.rtlWhCdSrchTxt_PS.clear();
        scrnMsg.rtlWhNmSrchTxt_PS.clear();
        scrnMsg.xxChkBox_P1.clear();
        // START 2018/11/21 T.Ogura [QC#29123,MOD]
//        scrnMsg.invtyAcctCd_P1.clear();
        scrnMsg.srchOptTxt_P3.clear();
        // END   2018/11/21 T.Ogura [QC#29123,MOD]
        scrnMsg.invtyOwnrCd_P1.clear();
        scrnMsg.whOwnrCd_P1.clear();
        scrnMsg.rtlWhCatgCd_P1.clear();
        scrnMsg.xxMultWhTpTxt_P1.clear();

    }

    /**
     * The method explanation: set parameter to call pop up.
     * @param scrnMsg NLCL1030BMsg
     * @param rtlWhCd EZDBStringItem
     * @param rtlWhNm EZDBStringItem
     * @param rtlSwhCd EZDBStringItem
     * @param rtlWhCatgCd EZDBStringItem
     * @param onlyRtlWhLvlFlg String
     * @return Object[]
     */
    public static Object[] setParamForLocationPopup(NLCL1030BMsg scrnMsg, EZDBStringItem rtlWhCd, EZDBStringItem rtlWhNm, EZDBStringItem rtlSwhCd, EZDBStringItem rtlWhCatgCd, String onlyRtlWhLvlFlg) {

        if (ZYPCommonFunc.hasValue(rtlWhCd)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhCdSrchTxt_PR, rtlWhCd);
        }
        if (ZYPCommonFunc.hasValue(rtlWhNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhNmSrchTxt_PR, rtlWhNm);
        }
        if (ZYPCommonFunc.hasValue(rtlSwhCd) && ZYPConstant.FLG_OFF_N.equals(onlyRtlWhLvlFlg)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhCdSrchTxt_PS, rtlSwhCd);
        }
        if (ZYPCommonFunc.hasValue(rtlWhCatgCd)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhCatgCd_P1, rtlWhCatgCd);
        }

        String sbLocRoleTpList = NMXC100001EnableWH.getLocRoleTpForPopup(scrnMsg.glblCmpyCd.getValue(), BIZ_APP_ID);

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxLocRoleTpCdListTxt_P1, sbLocRoleTpList);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxChkInpValFlg_P1, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxSelFlg_P1, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxChkBox_P1, onlyRtlWhLvlFlg);
        // START 2018/11/21 T.Ogura [QC#29123,MOD]
//        ZYPEZDItemValueSetter.setValue(scrnMsg.invtyAcctCd_P1, INVTY_ACCT_CD_01);
        ZYPEZDItemValueSetter.setValue(scrnMsg.srchOptTxt_P3, INVTY_ACCT_CD_01);
        // END   2018/11/21 T.Ogura [QC#29123,MOD]
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxMultWhTpTxt_P1, ZYPConstant.FLG_ON_Y);

        Object[] params = new Object[20];

        params[0] = scrnMsg.srchOptTxt_P1;
        // START 2018/11/21 T.Ogura [QC#29123,MOD]
//        params[1] = scrnMsg.invtyLocNm_P1;
        params[1] = scrnMsg.srchOptTxt_P2;
        // END   2018/11/21 T.Ogura [QC#29123,MOD]
        params[2] = scrnMsg.xxLocRoleTpCdListTxt_P1;
        params[3] = scrnMsg.xxChkInpValFlg_P1;
        params[4] = scrnMsg.xxSelFlg_P1;
        params[5] = scrnMsg.locRoleTpCd_P1;
        params[6] = scrnMsg.rtlWhCdSrchTxt_PR;
        params[7] = scrnMsg.rtlWhNmSrchTxt_PR;
        params[8] = scrnMsg.rtlWhCdSrchTxt_PS;
        params[9] = scrnMsg.rtlWhNmSrchTxt_PS;
        params[10] = scrnMsg.xxChkBox_P1;
        // START 2018/11/21 T.Ogura [QC#29123,MOD]
//        params[11] = scrnMsg.invtyAcctCd_P1;
        params[11] = scrnMsg.srchOptTxt_P3;
        // END   2018/11/21 T.Ogura [QC#29123,MOD]
        params[12] = scrnMsg.invtyOwnrCd_P1;
        params[13] = scrnMsg.whOwnrCd_P1;
        params[14] = scrnMsg.rtlWhCatgCd_P1;
        params[15] = scrnMsg.xxMultWhTpTxt_P1;
        params[16] = scrnMsg.xxPopPrm_17;
        params[17] = scrnMsg.xxPopPrm_18;
        params[18] = scrnMsg.xxPopPrm_19;
        params[19] = scrnMsg.xxPopPrm_20;

        return params;
    }

    /**
     * toUpperCase
     * @param arg String
     * @return String
     */
    public static String toUpperCase(String arg) {

        if (ZYPCommonFunc.hasValue(arg)) {

            return arg.toUpperCase();
        }

        return arg;
    }

    // QC#16256 add start
    /**
     * Function check
     * @return true:edit false:reference
     */
    private static boolean isUpdatable() {
        S21UserProfileService profileService = S21UserProfileServiceFactory.getInstance().getService();
        List<String> functionList = profileService.getAuthorizedFunctionCodeListForBizAppId(BIZ_APP_ID);
        return functionList.contains(FUNCTION_UPDATE);
    }
    // QC#16256 add end

}
