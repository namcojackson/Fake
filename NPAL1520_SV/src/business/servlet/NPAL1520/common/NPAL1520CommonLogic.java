/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1520.common;

import static business.servlet.NPAL1520.constant.NPAL1520Constant.BIZ_APP_ID;
import static business.servlet.NPAL1520.constant.NPAL1520Constant.BTN_CMN_BTN_1;
import static business.servlet.NPAL1520.constant.NPAL1520Constant.BTN_CMN_BTN_10;
import static business.servlet.NPAL1520.constant.NPAL1520Constant.BTN_CMN_BTN_2;
import static business.servlet.NPAL1520.constant.NPAL1520Constant.BTN_CMN_BTN_3;
import static business.servlet.NPAL1520.constant.NPAL1520Constant.BTN_CMN_BTN_4;
import static business.servlet.NPAL1520.constant.NPAL1520Constant.BTN_CMN_BTN_5;
import static business.servlet.NPAL1520.constant.NPAL1520Constant.BTN_CMN_BTN_6;
import static business.servlet.NPAL1520.constant.NPAL1520Constant.BTN_CMN_BTN_7;
import static business.servlet.NPAL1520.constant.NPAL1520Constant.BTN_CMN_BTN_8;
import static business.servlet.NPAL1520.constant.NPAL1520Constant.BTN_CMN_BTN_9;
import static business.servlet.NPAL1520.constant.NPAL1520Constant.BTN_DELETE_SEARCH;
import static business.servlet.NPAL1520.constant.NPAL1520Constant.BTN_NEW;
import static business.servlet.NPAL1520.constant.NPAL1520Constant.BTN_SAVE_SEARCH;
import static business.servlet.NPAL1520.constant.NPAL1520Constant.BTN_SEARCH;
import static business.servlet.NPAL1520.constant.NPAL1520Constant.BTN_SET_ITEM_DESCRIPTION;
import static business.servlet.NPAL1520.constant.NPAL1520Constant.BTN_SET_MANAGERNAME;
import static business.servlet.NPAL1520.constant.NPAL1520Constant.BTN_SET_SOURCE_SUB_WAREHOUSENAME;
import static business.servlet.NPAL1520.constant.NPAL1520Constant.BTN_SET_SOURCE_WAREHOUSENAME;
import static business.servlet.NPAL1520.constant.NPAL1520Constant.BTN_SET_SUB_WAREHOUSENAME;
import static business.servlet.NPAL1520.constant.NPAL1520Constant.BTN_SET_WAREHOUSENAME;
import static business.servlet.NPAL1520.constant.NPAL1520Constant.BTN_VIEW_DETAIL;
import static business.servlet.NPAL1520.constant.NPAL1520Constant.DISPLAY_NM_MDSE_CD;
import static business.servlet.NPAL1520.constant.NPAL1520Constant.DISPLAY_NM_MRP_PLN_NM;
import static business.servlet.NPAL1520.constant.NPAL1520Constant.DISPLAY_NM_PROCR_TP_CD;
import static business.servlet.NPAL1520.constant.NPAL1520Constant.DISPLAY_NM_RTL_SWH_CD;
import static business.servlet.NPAL1520.constant.NPAL1520Constant.DISPLAY_NM_RTL_WH_CATG_CD;
import static business.servlet.NPAL1520.constant.NPAL1520Constant.DISPLAY_NM_RTL_WH_CD;
import static business.servlet.NPAL1520.constant.NPAL1520Constant.DISPLAY_NM_SRCH_OPT_NM;
import static business.servlet.NPAL1520.constant.NPAL1520Constant.DISPLAY_NM_SRC_RTL_SWH_CD;
import static business.servlet.NPAL1520.constant.NPAL1520Constant.DISPLAY_NM_SRC_RTL_WH_CD;
import static business.servlet.NPAL1520.constant.NPAL1520Constant.DISPLAY_NM_WH_MGR_PSN_CD;
import static business.servlet.NPAL1520.constant.NPAL1520Constant.DTL_CD_LB_NM_FOR_PSN;
import static business.servlet.NPAL1520.constant.NPAL1520Constant.DTL_NM_LB_NM_FOR_PSN;
import static business.servlet.NPAL1520.constant.NPAL1520Constant.EVENT_OPEN_WIN_SOURCE_SUB_WAREHOUSE;
import static business.servlet.NPAL1520.constant.NPAL1520Constant.EVENT_OPEN_WIN_SOURCE_WAREHOUSE;
import static business.servlet.NPAL1520.constant.NPAL1520Constant.EVENT_OPEN_WIN_SUB_WAREHOUSE;
import static business.servlet.NPAL1520.constant.NPAL1520Constant.EVENT_OPEN_WIN_WAREHOUSE;
import static business.servlet.NPAL1520.constant.NPAL1520Constant.FUNC_EDIT;
import static business.servlet.NPAL1520.constant.NPAL1520Constant.HDR_CD_LB_NM_FOR_PSN;
import static business.servlet.NPAL1520.constant.NPAL1520Constant.HDR_NM_LB_NM_FOR_PSN;
import static business.servlet.NPAL1520.constant.NPAL1520Constant.ITEM_MODE_ALL;
import static business.servlet.NPAL1520.constant.NPAL1520Constant.SCRN_ID;
import static business.servlet.NPAL1520.constant.NPAL1520Constant.SCR_NM_FOR_PSN;
import static business.servlet.NPAL1520.constant.NPAL1520Constant.TBL_CD_COL_NM_FOR_PSN;
import static business.servlet.NPAL1520.constant.NPAL1520Constant.TBL_NM_COL_NM_FOR_PSN;
import static business.servlet.NPAL1520.constant.NPAL1520Constant.TBL_NM_FOR_PSN;
import static business.servlet.NPAL1520.constant.NPAL1520Constant.TBL_SORT_COL_NM_FOR_PSN;

import java.util.ArrayList;
import java.util.List;

import parts.servletcommon.EZDCommonHandler;
import business.servlet.NPAL1520.NPAL1520BMsg;

import com.canon.cusa.s21.common.NMX.NMXC100001.NMXC100001EnableWH;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_TP;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
*<pre>
* Business ID : NPAL1520 Min-Max Planning Inquiry
*
* Date         Company         Name            Create/Update   Defect No
* ----------------------------------------------------------------------
* 01/11/2016   CITS            Keiichi Masaki  Create          N/A
* 2022/10/05   Hitachi         M.Kikushima     Update          QC#60560
*</pre>
*/
public class NPAL1520CommonLogic {

    /**
     * The method explanation: The display control of the screen item
     * for Initialized screen
     * @param handler EZDCommonHandler
     * @param scrnMsg NPAL1520BMsg
     * @param functionList List<String>S
     */
    public static void ctrlScrnItemDispInit(EZDCommonHandler handler, NPAL1520BMsg scrnMsg, List<String> functionList) {

        // column input protection
        // true : block entry
        // false : input possible
        // Search Option
        scrnMsg.srchOptPk_PD.setInputProtected(false);
        scrnMsg.srchOptNm_PD.setInputProtected(false);
        scrnMsg.srchOptPk_SL.setInputProtected(false);
        scrnMsg.srchOptNm_TX.setInputProtected(false);

        // Search Condition
        scrnMsg.mrpPlnNm.setInputProtected(false);
        scrnMsg.mdseCd.setInputProtected(false);
        scrnMsg.mdseDescShortTxt.setInputProtected(true);
        scrnMsg.rtlWhCatgCd_PD.setInputProtected(false);
        scrnMsg.rtlWhCatgDescTxt_PD.setInputProtected(false);
        scrnMsg.rtlWhCatgCd_SL.setInputProtected(false);
        scrnMsg.rtlWhCd.setInputProtected(false);
        scrnMsg.rtlWhNm_W1.setInputProtected(true);
        scrnMsg.rtlSwhCd.setInputProtected(false);
        scrnMsg.rtlSwhNm_S1.setInputProtected(true);
        scrnMsg.whMgrPsnCd.setInputProtected(false);
        scrnMsg.fullPsnNm.setInputProtected(true);
        scrnMsg.procrTpCd_PD.setInputProtected(false);
        scrnMsg.procrTpDescTxt_PD.setInputProtected(false);
        scrnMsg.procrTpCd_SL.setInputProtected(false);
        scrnMsg.srcRtlWhCd.setInputProtected(false);
        scrnMsg.rtlWhNm_W2.setInputProtected(true);
        scrnMsg.srcRtlSwhCd.setInputProtected(false);
        scrnMsg.rtlSwhNm_S2.setInputProtected(true);

        // Detail Header Control
        scrnMsg.xxPageShowFromNum.setInputProtected(true);
        scrnMsg.xxPageShowToNum.setInputProtected(true);
        scrnMsg.xxPageShowOfNum.setInputProtected(true);

        // Button activation
        handler.setButtonEnabled(BTN_SAVE_SEARCH, true);
        handler.setButtonEnabled(BTN_DELETE_SEARCH, true);
        handler.setButtonEnabled(BTN_SET_ITEM_DESCRIPTION, true);
        handler.setButtonEnabled(BTN_SET_WAREHOUSENAME, true);
        handler.setButtonEnabled(BTN_SET_SUB_WAREHOUSENAME, true);
        handler.setButtonEnabled(BTN_SET_MANAGERNAME, true);
        handler.setButtonEnabled(BTN_SET_SOURCE_WAREHOUSENAME, true);
        handler.setButtonEnabled(BTN_SET_SOURCE_SUB_WAREHOUSENAME, true);
        handler.setButtonEnabled(BTN_SEARCH, true);
        handler.setButtonEnabled(BTN_NEW, false);

        if (ZYPCommonFunc.hasValue(scrnMsg.A.no(0).mrpPlnNm_A1)) {
            handler.setButtonEnabled(BTN_VIEW_DETAIL, true);
        } else {
            handler.setButtonEnabled(BTN_VIEW_DETAIL, false);
        }

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

        // set protect based on authority
        setAuthorityProtect(handler, scrnMsg, functionList);

    }

    /**
     * Table Column Protect
     * @param scrnMsg NPAL1520BMsg
     */
    public static void setTableScreen(NPAL1520BMsg scrnMsg) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID, scrnMsg);
        tblColor.setAlternateRowsBG("A", scrnMsg.A);

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).mdseDescShortTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).rtlWhCatgDescTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).rtlWhNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).rtlSwhNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).fullPsnNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).procrTpDescTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).rtlWhNm_A2.setInputProtected(true);
            scrnMsg.A.no(i).rtlSwhNm_A2.setInputProtected(true);
            // START 2022/10/05 M.Kikushima [QC#60560,ADD]
            scrnMsg.A.no(i).calcOrdProcCd_A1.setInputProtected(true);
            // END 2022/10/05 M.Kikushima [QC#60560,ADD]
        }
    }

    /**
     * Sets the authority protect.
     * @param handler EZDCommonHandler
     * @param scrnMsg NPAL1520BMsg
     * @param functionList List<String>
     */
    private static void setAuthorityProtect(EZDCommonHandler handler, NPAL1520BMsg scrnMsg, List<String> functionList) {

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
     * @param scrnMsg scrnMsg (BMsg)
     */
    public static void setNameForMessage(NPAL1520BMsg scrnMsg) {
        // Search Options
        scrnMsg.srchOptNm_TX.setNameForMessage(DISPLAY_NM_SRCH_OPT_NM);

        // Search Condition
        scrnMsg.mrpPlnNm.setNameForMessage(DISPLAY_NM_MRP_PLN_NM);
        scrnMsg.mdseCd.setNameForMessage(DISPLAY_NM_MDSE_CD);
        scrnMsg.rtlWhCatgCd_SL.setNameForMessage(DISPLAY_NM_RTL_WH_CATG_CD);
        scrnMsg.rtlWhCd.setNameForMessage(DISPLAY_NM_RTL_WH_CD);
        scrnMsg.rtlSwhCd.setNameForMessage(DISPLAY_NM_RTL_SWH_CD);
        scrnMsg.whMgrPsnCd.setNameForMessage(DISPLAY_NM_WH_MGR_PSN_CD);
        scrnMsg.procrTpCd_SL.setNameForMessage(DISPLAY_NM_PROCR_TP_CD);
        scrnMsg.srcRtlWhCd.setNameForMessage(DISPLAY_NM_SRC_RTL_WH_CD);
        scrnMsg.srcRtlSwhCd.setNameForMessage(DISPLAY_NM_SRC_RTL_SWH_CD);
    }

    /**
     * The method explanation: set initial parameter to call popup.
     * @param scrnMsg NPAL1520BMsg
     */
    public static void setInitParamForItemMasterPopup(NPAL1520BMsg scrnMsg) {

        scrnMsg.xxPopPrm_M0.clear();
        scrnMsg.xxPopPrm_M1.clear();
        scrnMsg.xxPopPrm_M2.clear();
        scrnMsg.xxPopPrm_M3.clear();
        scrnMsg.xxPopPrm_M4.clear();
        scrnMsg.xxPopPrm_M5.clear();
        scrnMsg.xxPopPrm_M6.clear();
        scrnMsg.xxPopPrm_M7.clear();
        scrnMsg.xxPopPrm_M8.clear();
        scrnMsg.xxPopPrm_M9.clear();

        // Item Code is set to sub screen delivery information.
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_M0, scrnMsg.mdseCd);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_M9, ITEM_MODE_ALL);
    }

    /**
     * The method explanation: set parameter to call popup.
     * @param scrnMsg NPAL1520BMsg
     * @return Object[]
     */
    public static Object[] setParamForItemMasterPopup(NPAL1520BMsg scrnMsg) {

        Object[] params = new Object[10];
        params[0] = scrnMsg.xxPopPrm_M0;
        params[1] = scrnMsg.xxPopPrm_M1;
        params[2] = scrnMsg.xxPopPrm_M2;
        params[3] = scrnMsg.xxPopPrm_M3;
        params[4] = scrnMsg.xxPopPrm_M4;
        params[5] = scrnMsg.xxPopPrm_M5;
        params[6] = scrnMsg.xxPopPrm_M6;
        params[7] = scrnMsg.xxPopPrm_M7;
        params[8] = scrnMsg.xxPopPrm_M8;
        params[9] = scrnMsg.xxPopPrm_M9;

        return params;
    }

    /**
     * The method explanation: set initial parameter to call popup.
     * @param scrnMsg NPAL1520BMsg
     */
    public static void setInitParamForManagerPopup(NPAL1520BMsg scrnMsg) {

        scrnMsg.xxTblNm_P1.clear();
        scrnMsg.xxTblCdColNm_P1.clear();
        scrnMsg.xxTblNmColNm_P1.clear();
        scrnMsg.xxTblSortColNm_P1.clear();
        scrnMsg.xxScrNm_P1.clear();
        scrnMsg.xxHdrCdLbNm_P1.clear();
        scrnMsg.xxHdrNmLbNm_P1.clear();
        scrnMsg.xxDtlCdLbNm_P1.clear();
        scrnMsg.xxDtlNmLbNm_P1.clear();

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblNm_P1, TBL_NM_FOR_PSN);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblCdColNm_P1, TBL_CD_COL_NM_FOR_PSN);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblNmColNm_P1, TBL_NM_COL_NM_FOR_PSN);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblSortColNm_P1, TBL_SORT_COL_NM_FOR_PSN);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrNm_P1, SCR_NM_FOR_PSN);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxHdrCdLbNm_P1, HDR_CD_LB_NM_FOR_PSN);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxHdrNmLbNm_P1, HDR_NM_LB_NM_FOR_PSN);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDtlCdLbNm_P1, DTL_CD_LB_NM_FOR_PSN);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDtlNmLbNm_P1, DTL_NM_LB_NM_FOR_PSN);
    }

    /**
     * The method explanation: set parameter to call popup.
     * @param scrnMsg NPAL1520BMsg
     * @return Object[]
     */
    public static Object[] setParamForManagerPopup(NPAL1520BMsg scrnMsg) {

        Object[] params = new Object[11];
        params[0] = scrnMsg.xxTblNm_P1;
        params[1] = scrnMsg.xxTblCdColNm_P1;
        params[2] = scrnMsg.xxTblNmColNm_P1;
        params[3] = scrnMsg.xxTblSortColNm_P1;
        params[4] = scrnMsg.xxScrNm_P1;
        params[5] = scrnMsg.xxHdrCdLbNm_P1;
        params[6] = scrnMsg.xxHdrNmLbNm_P1;
        params[7] = scrnMsg.xxDtlCdLbNm_P1;
        params[8] = scrnMsg.xxDtlNmLbNm_P1;
        params[9] = scrnMsg.whMgrPsnCd;
        params[10] = scrnMsg.fullPsnNm;

        return params;
    }

    /**
     * Set Location Popup param
     * @param scrnMsg NPAL1520BMsg
     * @return LocationPopup Parameter (NPAL1010) Object[]
     */
    public static Object[] setLocationPopupParam(NPAL1520BMsg scrnMsg) {

        ZYPTableUtil.clear(scrnMsg.P);
        // these parameter do not set value.
        // param1 :Location Code
        // param2 :Location Name
        // param12:Inventory Account
        // param13:Inventory Owner
        // param14:WH Owner
        // param16:Multiple Select Flag
        // param17:WH Type Lock Flag
        // param18:Inventory Owner Lock Flag
        // param19:WH Operation Lock Flag
        // param20:Inventory Account Lock Flag

        List<String> locTpList = new ArrayList<String>();

        if (ZYPCommonFunc.hasValue(scrnMsg.locTpCd)) {
            locTpList.add(scrnMsg.locTpCd.getValue());
        } else {
            locTpList.add(LOC_TP.WAREHOUSE);
            locTpList.add(LOC_TP.TECHNICIAN);
        }

        List<String> srcLocTpList = new ArrayList<String>();
        srcLocTpList.add(LOC_TP.WAREHOUSE);

        // param4 :Data Security Flag
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(3).xxPopPrm, ZYPConstant.FLG_OFF_N);
        // param5 :Virtual Warehouse Flag
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(4).xxPopPrm, ZYPConstant.FLG_OFF_N);

        if (EVENT_OPEN_WIN_WAREHOUSE.equals(scrnMsg.xxPopPrm_EV.getValue())) {
            // param3 :Location Type(List)
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(2).xxPopPrm, NMXC100001EnableWH.getLocRoleTpForPopup(scrnMsg.glblCmpyCd.getValue(), BIZ_APP_ID, locTpList));
            // param7 :Retail WH Code
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(6).xxPopPrm, scrnMsg.rtlWhCd);
            // param8 :Retail WH Name
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(7).xxPopPrm, scrnMsg.rtlWhNm_W1);
            // param11:Only RTL_WH Flag
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(10).xxPopPrm, ZYPConstant.FLG_ON_Y);
            // param15:WH Category
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(14).xxPopPrm, scrnMsg.rtlWhCatgCd_SL);
            // param21:WH Manager Code
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(20).xxPopPrm, scrnMsg.whMgrPsnCd);
            // param22:WH Manager Name
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(21).xxPopPrm, scrnMsg.fullPsnNm);
        } else if (EVENT_OPEN_WIN_SUB_WAREHOUSE.equals(scrnMsg.xxPopPrm_EV.getValue())) {
            // param3 :Location Type(List)
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(2).xxPopPrm, NMXC100001EnableWH.getLocRoleTpForPopup(scrnMsg.glblCmpyCd.getValue(), BIZ_APP_ID, locTpList));
            // param7 :Retail WH Code
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(6).xxPopPrm, scrnMsg.rtlWhCd);
            // param8 :Retail WH Name
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(7).xxPopPrm, scrnMsg.rtlWhNm_W1);
            // param9 :SWH Code
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(8).xxPopPrm, scrnMsg.rtlSwhCd);
            // param10:SWH Name
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(9).xxPopPrm, scrnMsg.rtlSwhNm_S1);
            // param15:WH Category
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(14).xxPopPrm, scrnMsg.rtlWhCatgCd_SL);
            // param21:WH Manager Code
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(20).xxPopPrm, scrnMsg.whMgrPsnCd);
            // param22:WH Manager Name
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(21).xxPopPrm, scrnMsg.fullPsnNm);
        } else if (EVENT_OPEN_WIN_SOURCE_WAREHOUSE.equals(scrnMsg.xxPopPrm_EV.getValue())) {
            // param3 :Location Type(List)
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(2).xxPopPrm, NMXC100001EnableWH.getLocRoleTpForPopup(scrnMsg.glblCmpyCd.getValue(), BIZ_APP_ID, srcLocTpList));
            // param7 :Retail WH Code
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(6).xxPopPrm, scrnMsg.srcRtlWhCd);
            // param8 :Retail WH Name
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(7).xxPopPrm, scrnMsg.rtlWhNm_W2);
            // param11:Only RTL_WH Flag
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(10).xxPopPrm, ZYPConstant.FLG_ON_Y);
        } else if (EVENT_OPEN_WIN_SOURCE_SUB_WAREHOUSE.equals(scrnMsg.xxPopPrm_EV.getValue())) {
            // param3 :Location Type(List)
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(2).xxPopPrm, NMXC100001EnableWH.getLocRoleTpForPopup(scrnMsg.glblCmpyCd.getValue(), BIZ_APP_ID, srcLocTpList));
            // param7 :Retail WH Code
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(6).xxPopPrm, scrnMsg.srcRtlWhCd);
            // param8 :Retail WH Name
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(7).xxPopPrm, scrnMsg.rtlWhNm_W2);
            // param9 :SWH Code
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(8).xxPopPrm, scrnMsg.srcRtlSwhCd);
            // param10:SWH Name
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(9).xxPopPrm, scrnMsg.rtlSwhNm_S2);
        }

        Object[] params = new Object[22];
        params[0] = scrnMsg.P.no(0).xxPopPrm;
        params[1] = scrnMsg.P.no(1).xxPopPrm;
        params[2] = scrnMsg.P.no(2).xxPopPrm;
        params[3] = scrnMsg.P.no(3).xxPopPrm;
        params[4] = scrnMsg.P.no(4).xxPopPrm;
        params[5] = scrnMsg.P.no(5).xxPopPrm;
        params[6] = scrnMsg.P.no(6).xxPopPrm;
        params[7] = scrnMsg.P.no(7).xxPopPrm;
        params[8] = scrnMsg.P.no(8).xxPopPrm;
        params[9] = scrnMsg.P.no(9).xxPopPrm;
        params[10] = scrnMsg.P.no(10).xxPopPrm;
        params[11] = scrnMsg.P.no(11).xxPopPrm;
        params[12] = scrnMsg.P.no(12).xxPopPrm;
        params[13] = scrnMsg.P.no(13).xxPopPrm;
        params[14] = scrnMsg.P.no(14).xxPopPrm;
        params[15] = scrnMsg.P.no(15).xxPopPrm;
        params[16] = scrnMsg.P.no(16).xxPopPrm;
        params[17] = scrnMsg.P.no(17).xxPopPrm;
        params[18] = scrnMsg.P.no(18).xxPopPrm;
        params[19] = scrnMsg.P.no(19).xxPopPrm;
        params[20] = scrnMsg.P.no(20).xxPopPrm;
        params[21] = scrnMsg.P.no(21).xxPopPrm;

       return params;
    }

    /**
     * check Item
     * @param scrnMsg NPAL1520BMsg
     */
    public static void commonAddCheckItem(NPAL1520BMsg scrnMsg) {

        scrnMsg.addCheckItem(scrnMsg.srchOptPk_SL);
        scrnMsg.addCheckItem(scrnMsg.srchOptNm_TX);

        scrnMsg.addCheckItem(scrnMsg.mrpPlnNm);
        scrnMsg.addCheckItem(scrnMsg.mdseCd);
        scrnMsg.addCheckItem(scrnMsg.rtlWhCatgCd_SL);
        scrnMsg.addCheckItem(scrnMsg.rtlWhCd);
        scrnMsg.addCheckItem(scrnMsg.rtlSwhCd);
        scrnMsg.addCheckItem(scrnMsg.whMgrPsnCd);
        scrnMsg.addCheckItem(scrnMsg.procrTpCd_SL);
        scrnMsg.addCheckItem(scrnMsg.srcRtlWhCd);
        scrnMsg.addCheckItem(scrnMsg.srcRtlSwhCd);
    }

    /**
     * clear table
     * @param scrnMsg NPAL1520BMsg
     */
    public static void clearTable(NPAL1520BMsg scrnMsg) {
        scrnMsg.A.clear();
        ZYPTableUtil.clear(scrnMsg.A);
        scrnMsg.xxPageShowFromNum.clear();
        scrnMsg.xxPageShowToNum.clear();
        scrnMsg.xxPageShowOfNum.clear();
    }

    /**
     * <pre>
     * Check Search Criteria Items.
     * </pre>
     * @param scrnMsg NPAL1520BMsg
     * @return boolean
     */
    public static boolean hasSearchCriteria(NPAL1520BMsg scrnMsg) {

        // Should has value at least one criteria item.
        if (ZYPCommonFunc.hasValue(scrnMsg.mrpPlnNm)
                || ZYPCommonFunc.hasValue(scrnMsg.mdseCd)
                || ZYPCommonFunc.hasValue(scrnMsg.rtlWhCatgCd_SL)
                || ZYPCommonFunc.hasValue(scrnMsg.rtlWhCd)
                || ZYPCommonFunc.hasValue(scrnMsg.rtlSwhCd)
                || ZYPCommonFunc.hasValue(scrnMsg.whMgrPsnCd)
                || ZYPCommonFunc.hasValue(scrnMsg.procrTpCd_SL)
                || ZYPCommonFunc.hasValue(scrnMsg.srcRtlWhCd)
                || ZYPCommonFunc.hasValue(scrnMsg.srcRtlSwhCd)) {

            return true;
        }

        return false;
    }
}
