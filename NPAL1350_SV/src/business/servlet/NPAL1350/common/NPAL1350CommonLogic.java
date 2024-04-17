/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1350.common;

import static business.servlet.NPAL1350.constant.NPAL1350Constant.BTN_CMN_BTN_1;
import static business.servlet.NPAL1350.constant.NPAL1350Constant.BTN_CMN_BTN_10;
import static business.servlet.NPAL1350.constant.NPAL1350Constant.BTN_CMN_BTN_2;
import static business.servlet.NPAL1350.constant.NPAL1350Constant.BTN_CMN_BTN_3;
import static business.servlet.NPAL1350.constant.NPAL1350Constant.BTN_CMN_BTN_4;
import static business.servlet.NPAL1350.constant.NPAL1350Constant.BTN_CMN_BTN_5;
import static business.servlet.NPAL1350.constant.NPAL1350Constant.BTN_CMN_BTN_6;
import static business.servlet.NPAL1350.constant.NPAL1350Constant.BTN_CMN_BTN_7;
import static business.servlet.NPAL1350.constant.NPAL1350Constant.BTN_CMN_BTN_8;
import static business.servlet.NPAL1350.constant.NPAL1350Constant.BTN_CMN_BTN_9;
import static business.servlet.NPAL1350.constant.NPAL1350Constant.BTN_NEW;
import static business.servlet.NPAL1350.constant.NPAL1350Constant.BTN_SEARCH;
import static business.servlet.NPAL1350.constant.NPAL1350Constant.DISPLAY_NM_DS_WRK_ORD_DT_FROM;
import static business.servlet.NPAL1350.constant.NPAL1350Constant.DISPLAY_NM_DS_WRK_ORD_DT_TO;
import static business.servlet.NPAL1350.constant.NPAL1350Constant.DISPLAY_NM_DS_WRK_ORD_STS;
import static business.servlet.NPAL1350.constant.NPAL1350Constant.DISPLAY_NM_DS_WRK_ORD_TP;
import static business.servlet.NPAL1350.constant.NPAL1350Constant.DISPLAY_NM_MDSE_DESC_SHORT_TXT;
import static business.servlet.NPAL1350.constant.NPAL1350Constant.DISPLAY_NM_PRNT_MDSE_CD;
import static business.servlet.NPAL1350.constant.NPAL1350Constant.DISPLAY_NM_RQST_RCV_DT_FROM;
import static business.servlet.NPAL1350.constant.NPAL1350Constant.DISPLAY_NM_RQST_RCV_DT_TO;
import static business.servlet.NPAL1350.constant.NPAL1350Constant.DISPLAY_NM_RTL_SWH_CD;
import static business.servlet.NPAL1350.constant.NPAL1350Constant.DISPLAY_NM_RTL_SWH_NM;
import static business.servlet.NPAL1350.constant.NPAL1350Constant.DISPLAY_NM_RTL_WH_CD;
import static business.servlet.NPAL1350.constant.NPAL1350Constant.DISPLAY_NM_RTL_WH_NM;
import static business.servlet.NPAL1350.constant.NPAL1350Constant.DISPLAY_NM_WRK_ORD_NUM;
import static business.servlet.NPAL1350.constant.NPAL1350Constant.FUNC_EDIT;
import static business.servlet.NPAL1350.constant.NPAL1350Constant.POPUP_PARAM_LOC_ROLE_TP_CD_LIST;
import static business.servlet.NPAL1350.constant.NPAL1350Constant.SCRN_ID;

import java.util.List;

import parts.common.EZDBBigDecimalItem;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NPAL1350.NPAL1350BMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_ITEM_TP;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 * <pre>
 * Business ID :  NPAL1350 Kitting WO Search
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 04/20/2016   CITS         Makoto Okigami     Create          N/A
 *</pre>
 */
public class NPAL1350CommonLogic {

    /**
     * The method explanation: The display control of the screen item
     * for Initialized screen
     * @param handler EZDCommonHandler
     * @param scrnMsg NPAL1350BMsg
     * @param functionList List<String>
     */
    public static void ctrlScrnItemDispInit(EZDCommonHandler handler, NPAL1350BMsg scrnMsg, List<String> functionList) {

        // column input protection
        // true : block entry
        // false : input possible

        // Header
        scrnMsg.mdseDescShortTxt.setInputProtected(true);
        scrnMsg.rtlWhNm.setInputProtected(true);
        scrnMsg.rtlSwhNm.setInputProtected(true);

        handler.setButtonEnabled(BTN_SEARCH, true);
        handler.setButtonEnabled(BTN_NEW, false);

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
        handler.setButtonProperties(BTN_CMN_BTN_8[0], BTN_CMN_BTN_8[1], BTN_CMN_BTN_8[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_9[0], BTN_CMN_BTN_9[1], BTN_CMN_BTN_9[2], 1, null);
        handler.setButtonProperties(BTN_CMN_BTN_10[0], BTN_CMN_BTN_10[1], BTN_CMN_BTN_10[2], 1, null);

        setAuthorityProtect(handler, scrnMsg, functionList);
        setTableScreen(scrnMsg);
    }

    /**
     * Sets the authority protect.
     * @param handler EZDCommonHandler
     * @param scrnMsg NPAL1350BMsg
     * @param functionList List<String>
     */
    private static void setAuthorityProtect(EZDCommonHandler handler, NPAL1350BMsg scrnMsg, List<String> functionList) {

        if (hasRegisterAuthority(functionList)) {
            handler.setButtonEnabled(BTN_NEW, true);
        }
    }

    /**
     * Checks for register authority.
     * @param functionList List<String>
     * @return true, if successful
     */
    private static boolean hasRegisterAuthority(List<String> functionList) {
        for (String function : functionList) {
            if (FUNC_EDIT.equals(function)) {
                return true;
            }
        }
        return false;
    }

    /**
     * <p>
     * Sets the name for the error message.
     * </p>
     * @param scrnMsg NPAL1350BMsg
     */
    public static void setNameForMessage(NPAL1350BMsg scrnMsg) {

        // Header
        scrnMsg.wrkOrdNum.setNameForMessage(DISPLAY_NM_WRK_ORD_NUM);
        scrnMsg.dsWrkOrdTpCd_PL.setNameForMessage(DISPLAY_NM_DS_WRK_ORD_TP);
        scrnMsg.dsWrkOrdStsNm_PL.setNameForMessage(DISPLAY_NM_DS_WRK_ORD_STS);
        scrnMsg.wrkOrdDt_FM.setNameForMessage(DISPLAY_NM_DS_WRK_ORD_DT_FROM);
        scrnMsg.wrkOrdDt_TO.setNameForMessage(DISPLAY_NM_DS_WRK_ORD_DT_TO);
        scrnMsg.prntMdseCd.setNameForMessage(DISPLAY_NM_PRNT_MDSE_CD);
        scrnMsg.mdseDescShortTxt.setNameForMessage(DISPLAY_NM_MDSE_DESC_SHORT_TXT);
        scrnMsg.rtlWhCd.setNameForMessage(DISPLAY_NM_RTL_WH_CD);
        scrnMsg.rtlWhNm.setNameForMessage(DISPLAY_NM_RTL_WH_NM);
        scrnMsg.rtlSwhCd.setNameForMessage(DISPLAY_NM_RTL_SWH_CD);
        scrnMsg.rtlSwhNm.setNameForMessage(DISPLAY_NM_RTL_SWH_NM);
        scrnMsg.rqstRcvDt_FM.setNameForMessage(DISPLAY_NM_RQST_RCV_DT_FROM);
        scrnMsg.rqstRcvDt_TO.setNameForMessage(DISPLAY_NM_RQST_RCV_DT_TO);

    }

    /**
     * Table Column Protect
     * @param scrnMsg NPAL1350BMsg
     */
    public static void setTableScreen(NPAL1350BMsg scrnMsg) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID, scrnMsg);
        tblColor.setAlternateRowsBG("A", scrnMsg.A);

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).fnshMdseDescShortTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).rtlWhNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).rtlSwhNm_A1.setInputProtected(true);
        }
    }

    /**
     * Set Kit Item Search Param
     * @param scrnMsg NPAL1350BMsg
     * @param glblCmpyCd String
     * @return Kit Item Search Parameter
     */
    public static Object[] setKitItemSearchParam(NPAL1350BMsg scrnMsg, String glblCmpyCd) {

        // Parameter Clear
        ZYPTableUtil.clear(scrnMsg.P);

        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).xxPopPrm, scrnMsg.prntMdseCd);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(2).xxPopPrm, MDSE_ITEM_TP.KIT);

        // Parameter Set
        Object[] param = new Object[10];
        param[0] = scrnMsg.P.no(0).xxPopPrm;
        param[1] = scrnMsg.P.no(1).xxPopPrm;
        param[2] = scrnMsg.P.no(2).xxPopPrm;
        param[3] = scrnMsg.P.no(3).xxPopPrm;
        param[4] = scrnMsg.P.no(4).xxPopPrm;
        param[5] = scrnMsg.P.no(5).xxPopPrm;
        param[6] = scrnMsg.P.no(6).xxPopPrm;
        param[7] = scrnMsg.P.no(7).xxPopPrm;
        param[8] = scrnMsg.P.no(8).xxPopPrm;
        param[9] = scrnMsg.P.no(9).xxPopPrm;

        return param;

    }


    /**
     * The method explanation: set parameter to call popup.
     * @param scrnMsg NPAL1350BMsg
     * @return Object[]
     */
    public static Object[] setParamForKittingWarehousePopup(NPAL1350BMsg scrnMsg) {

        scrnMsg.P.clear();

        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(2).xxPopPrm, POPUP_PARAM_LOC_ROLE_TP_CD_LIST);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(3).xxPopPrm, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(4).xxPopPrm, ZYPConstant.FLG_OFF_N);

        Object[] params = new Object[12];
        params[0] = scrnMsg.P.no(0).xxPopPrm;
        params[1] = scrnMsg.P.no(1).xxPopPrm;
        params[2] = scrnMsg.P.no(2).xxPopPrm;
        params[3] = scrnMsg.P.no(3).xxPopPrm;
        params[4] = scrnMsg.P.no(4).xxPopPrm;
        params[5] = scrnMsg.P.no(5).xxPopPrm;
        params[6] = scrnMsg.rtlWhCd;
        params[7] = scrnMsg.rtlWhNm;
        params[8] = scrnMsg.rtlSwhCd;
        params[9] = scrnMsg.rtlSwhNm;
        params[10] = scrnMsg.P.no(10).xxPopPrm;
        params[11] = scrnMsg.P.no(11).xxPopPrm;

        return params;
    }

    /**
     * The method explanation: set parameter to call screen.
     * @param scrnMsg NPAL1350BMsg
     * @param eventRowIndex int
     * @return Object[]
     */
    public static Object[] setWorkOrderEntryParam(NPAL1350BMsg scrnMsg, int eventRowIndex) {

        scrnMsg.P.clear();

        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).xxPopPrm, scrnMsg.A.no(eventRowIndex).wrkOrdNum_A1);
        EZDBBigDecimalItem ordQty = null;
        EZDBBigDecimalItem prchReqLineSubNum = null;

        Object[] params = new Object[8];
        params[0] = scrnMsg.P.no(0).xxPopPrm;
        params[1] = scrnMsg.P.no(1).xxPopPrm;
        params[2] = ordQty;
        params[3] = scrnMsg.P.no(3).xxPopPrm;
        params[4] = scrnMsg.P.no(4).xxPopPrm;
        params[5] = scrnMsg.P.no(5).xxPopPrm;
        params[6] = scrnMsg.P.no(6).xxPopPrm;
        params[7] = prchReqLineSubNum;

        return params;
    }

    /**
     * clear table
     * @param scrnMsg NPAL1350BMsg
     */
    public static void clearTable(NPAL1350BMsg scrnMsg) {
        scrnMsg.A.clear();
        ZYPTableUtil.clear(scrnMsg.A);
        scrnMsg.xxPageShowFromNum.clear();
        scrnMsg.xxPageShowToNum.clear();
        scrnMsg.xxPageShowOfNum.clear();
    }

}
