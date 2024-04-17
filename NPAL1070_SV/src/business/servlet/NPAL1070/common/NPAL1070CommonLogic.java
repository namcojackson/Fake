/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1070.common;

import static business.servlet.NPAL1070.constant.NPAL1070Constant.BIZ_APP_ID;
import static business.servlet.NPAL1070.constant.NPAL1070Constant.BTN_ADD;
import static business.servlet.NPAL1070.constant.NPAL1070Constant.BTN_APPLY_TO_ALL;
import static business.servlet.NPAL1070.constant.NPAL1070Constant.BTN_ATTACHMENTS;
import static business.servlet.NPAL1070.constant.NPAL1070Constant.BTN_CHECK_ALL;
import static business.servlet.NPAL1070.constant.NPAL1070Constant.BTN_CMN_BTN_1;
import static business.servlet.NPAL1070.constant.NPAL1070Constant.BTN_CMN_BTN_10;
import static business.servlet.NPAL1070.constant.NPAL1070Constant.BTN_CMN_BTN_2;
import static business.servlet.NPAL1070.constant.NPAL1070Constant.BTN_CMN_BTN_3;
import static business.servlet.NPAL1070.constant.NPAL1070Constant.BTN_CMN_BTN_4;
import static business.servlet.NPAL1070.constant.NPAL1070Constant.BTN_CMN_BTN_5;
import static business.servlet.NPAL1070.constant.NPAL1070Constant.BTN_CMN_BTN_6;
import static business.servlet.NPAL1070.constant.NPAL1070Constant.BTN_CMN_BTN_7;
import static business.servlet.NPAL1070.constant.NPAL1070Constant.BTN_CMN_BTN_8;
import static business.servlet.NPAL1070.constant.NPAL1070Constant.BTN_CMN_BTN_9;
import static business.servlet.NPAL1070.constant.NPAL1070Constant.BTN_COPY;
import static business.servlet.NPAL1070.constant.NPAL1070Constant.BTN_DELETE_ROW;
import static business.servlet.NPAL1070.constant.NPAL1070Constant.BTN_DETAIL_WH;
import static business.servlet.NPAL1070.constant.NPAL1070Constant.BTN_DETAIL_WH_NM;
import static business.servlet.NPAL1070.constant.NPAL1070Constant.BTN_DISABLE;
import static business.servlet.NPAL1070.constant.NPAL1070Constant.BTN_IMPORT;
import static business.servlet.NPAL1070.constant.NPAL1070Constant.BTN_SEARCH;
import static business.servlet.NPAL1070.constant.NPAL1070Constant.BTN_SET_ITEM_DESCRIPTION;
import static business.servlet.NPAL1070.constant.NPAL1070Constant.BTN_SET_MANAGER_NAME;
import static business.servlet.NPAL1070.constant.NPAL1070Constant.BTN_SET_SOURCE_SUB_WAREHOUSE_NAME;
import static business.servlet.NPAL1070.constant.NPAL1070Constant.BTN_SET_SOURCE_WAREHOUSE_NAME;
import static business.servlet.NPAL1070.constant.NPAL1070Constant.BTN_SET_SUB_WAREHOUSE_NAME;
import static business.servlet.NPAL1070.constant.NPAL1070Constant.BTN_SET_WAREHOUSE_NAME;
import static business.servlet.NPAL1070.constant.NPAL1070Constant.BTN_UNCHECK_ALL;
import static business.servlet.NPAL1070.constant.NPAL1070Constant.DISPLAY_COMMENT;
import static business.servlet.NPAL1070.constant.NPAL1070Constant.DISPLAY_MAX_QTY;
import static business.servlet.NPAL1070.constant.NPAL1070Constant.DISPLAY_MIN_QTY;
import static business.servlet.NPAL1070.constant.NPAL1070Constant.DISPLAY_NM_MDSE_CD;
import static business.servlet.NPAL1070.constant.NPAL1070Constant.DISPLAY_NM_MRP_PLN_NM;
import static business.servlet.NPAL1070.constant.NPAL1070Constant.DISPLAY_NM_PROCR_TP_CD;
import static business.servlet.NPAL1070.constant.NPAL1070Constant.DISPLAY_NM_RTL_SWH_CD;
import static business.servlet.NPAL1070.constant.NPAL1070Constant.DISPLAY_NM_RTL_WH_CATG_CD;
import static business.servlet.NPAL1070.constant.NPAL1070Constant.DISPLAY_NM_RTL_WH_CD;
import static business.servlet.NPAL1070.constant.NPAL1070Constant.DISPLAY_NM_SRC_RTL_SWH_CD;
import static business.servlet.NPAL1070.constant.NPAL1070Constant.DISPLAY_NM_SRC_RTL_WH_CD;
import static business.servlet.NPAL1070.constant.NPAL1070Constant.DISPLAY_NM_WH_MGR_PSN_CD;
import static business.servlet.NPAL1070.constant.NPAL1070Constant.DISPLAY_REORDER_QTY;
import static business.servlet.NPAL1070.constant.NPAL1070Constant.DTL_CD_LB_NM_FOR_PSN;
import static business.servlet.NPAL1070.constant.NPAL1070Constant.DTL_NM_LB_NM_FOR_PSN;
import static business.servlet.NPAL1070.constant.NPAL1070Constant.EVENT_OPEN_WIN_SOURCE_SUB_WAREHOUSE;
import static business.servlet.NPAL1070.constant.NPAL1070Constant.EVENT_OPEN_WIN_SOURCE_SUB_WAREHOUSE_DETAIL;
import static business.servlet.NPAL1070.constant.NPAL1070Constant.EVENT_OPEN_WIN_SOURCE_WAREHOUSE;
import static business.servlet.NPAL1070.constant.NPAL1070Constant.EVENT_OPEN_WIN_SUB_WAREHOUSE;
import static business.servlet.NPAL1070.constant.NPAL1070Constant.EVENT_OPEN_WIN_WAREHOUSE;
import static business.servlet.NPAL1070.constant.NPAL1070Constant.FUNC_EDIT;
import static business.servlet.NPAL1070.constant.NPAL1070Constant.HDR_CD_LB_NM_FOR_PSN;
import static business.servlet.NPAL1070.constant.NPAL1070Constant.HDR_NM_LB_NM_FOR_PSN;
import static business.servlet.NPAL1070.constant.NPAL1070Constant.ITEM_MODE_ALL;
import static business.servlet.NPAL1070.constant.NPAL1070Constant.SCRN_ID;
import static business.servlet.NPAL1070.constant.NPAL1070Constant.SCR_NM_FOR_PSN;
import static business.servlet.NPAL1070.constant.NPAL1070Constant.TBL_CD_COL_NM_FOR_PSN;
import static business.servlet.NPAL1070.constant.NPAL1070Constant.TBL_NM_COL_NM_FOR_PSN;
import static business.servlet.NPAL1070.constant.NPAL1070Constant.TBL_NM_FOR_PSN;
import static business.servlet.NPAL1070.constant.NPAL1070Constant.TBL_SORT_COL_NM_FOR_PSN;
import static business.servlet.NPAL1070.constant.NPAL1070Constant.FUNC_EDIT_FSM;

import java.util.ArrayList;
import java.util.List;

import parts.common.EZDGUIAttribute;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NPAL1070.NPAL1070BMsg;
import business.servlet.NPAL1070.NPAL1070_XBMsgArray;

import com.canon.cusa.s21.common.NMX.NMXC100001.NMXC100001EnableWH;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_TP;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * Business ID : NPAL1070 Min-Max Planning Entry
 * Function Name : Common Logic (SV)
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/24/2016   CITS            Keiichi Masaki  Create          N/A
 * 11/07/2017   CITS            S.Katsuma       Update          Sol#014(QC#18401)
 * 2018/12/05   Hitachi         J.Kim           Update          QC#18224
 * 2022/10/05   Hitachi         M.Kikushima     Update          QC#60560
 *</pre>
 */

public class NPAL1070CommonLogic {
    /**
     * The method explanation: The display control of the screen item
     * for Initialized screen
     * @param handler EZDCommonHandler
     * @param scrnMsg NPAL1070BMsg
     * @param functionList List<String>S
     */
    public static void ctrlScrnItemDispInit(EZDCommonHandler handler, NPAL1070BMsg scrnMsg, List<String> functionList) {

        // column input protection
        // true : block entry
        // false : input possible
        // Header Control
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
        scrnMsg.mrpEnblFlg.setInputProtected(false);
        // START 2017/11/07 S.Katsuma [Sol#014(QC#18401),ADD]
        scrnMsg.calcOrdProcCd.setInputProtected(false);
        // END 2017/11/07 S.Katsuma [Sol#014(QC#18401),ADD]
        scrnMsg.rplshDlyFlg.setInputProtected(false);
        scrnMsg.rplshMonFlg.setInputProtected(false);
        scrnMsg.rplshTueFlg.setInputProtected(false);
        scrnMsg.rplshWedFlg.setInputProtected(false);
        scrnMsg.rplshThuFlg.setInputProtected(false);
        scrnMsg.rplshFriFlg.setInputProtected(false);

        // Detail Header Control
        scrnMsg.xxPageShowFromNum.setInputProtected(true);
        scrnMsg.xxPageShowToNum.setInputProtected(true);
        scrnMsg.xxPageShowOfNum.setInputProtected(true);

        // Button activation
        handler.setButtonEnabled(BTN_SET_ITEM_DESCRIPTION, true);
        handler.setButtonEnabled(BTN_SET_WAREHOUSE_NAME, true);
        handler.setButtonEnabled(BTN_SET_SUB_WAREHOUSE_NAME, true);
        handler.setButtonEnabled(BTN_SET_MANAGER_NAME, true);
        handler.setButtonEnabled(BTN_SET_SOURCE_WAREHOUSE_NAME, true);
        handler.setButtonEnabled(BTN_SET_SOURCE_SUB_WAREHOUSE_NAME, true);
        handler.setButtonEnabled(BTN_SEARCH, true);
        handler.setButtonEnabled(BTN_ATTACHMENTS, false);

        // common button protection
        // 0 : inactive
        // 1 : active
        handler.setButtonProperties(BTN_CMN_BTN_1[0], BTN_CMN_BTN_1[1], BTN_CMN_BTN_1[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_3[0], BTN_CMN_BTN_3[1], BTN_CMN_BTN_3[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_4[0], BTN_CMN_BTN_4[1], BTN_CMN_BTN_4[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_5[0], BTN_CMN_BTN_5[1], BTN_CMN_BTN_5[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_6[0], BTN_CMN_BTN_6[1], BTN_CMN_BTN_6[2], 1, null);
        handler.setButtonProperties(BTN_CMN_BTN_7[0], BTN_CMN_BTN_7[1], BTN_CMN_BTN_7[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_8[0], BTN_CMN_BTN_8[1], BTN_CMN_BTN_8[2], 1, null);
        handler.setButtonProperties(BTN_CMN_BTN_9[0], BTN_CMN_BTN_9[1], BTN_CMN_BTN_9[2], 1, null);
        handler.setButtonProperties(BTN_CMN_BTN_10[0], BTN_CMN_BTN_10[1], BTN_CMN_BTN_10[2], 1, null);

        // set protect based on authority
        setAuthorityProtect(handler, scrnMsg, functionList);

    }

    /**
     * Sets the authority protect.
     * @param handler EZDCommonHandler
     * @param scrnMsg NPAL1070BMsg
     * @param functionList List<String>
     */
    public static void setAuthorityProtect(EZDCommonHandler handler, NPAL1070BMsg scrnMsg, List<String> functionList) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID, scrnMsg);
        //tblColor.setAlternateRowsBG("A_Left", scrnMsg.A);
        //tblColor.setAlternateRowsBG("A_Right", scrnMsg.A);
        tblColor.setAlternateRowsBG("A", scrnMsg.A);

        if (hasRegisterAuthority(functionList)) {
            // Edit Mode
            // Button
            handler.setButtonEnabled(BTN_ADD, true);
            handler.setButtonEnabled(BTN_IMPORT, true);
            handler.setButtonEnabled(BTN_COPY, true);

            if (scrnMsg.A.getValidCount() == 0) {
                handler.setButtonEnabled(BTN_CHECK_ALL, false);
                handler.setButtonEnabled(BTN_UNCHECK_ALL, false);
                handler.setButtonEnabled(BTN_DELETE_ROW, false);
                handler.setButtonEnabled(BTN_DISABLE, false);
                // START 2022/10/05 M.Kikushima [QC#60560,ADD]
                handler.setButtonEnabled(BTN_APPLY_TO_ALL, false);
                // END 2022/10/05 M.Kikushima [QC#60560,ADD]
                // CMN_Submit
                handler.setButtonProperties(BTN_CMN_BTN_2[0], BTN_CMN_BTN_2[1], BTN_CMN_BTN_2[2], 0, null);
            } else {
                handler.setButtonEnabled(BTN_CHECK_ALL, true);
                handler.setButtonEnabled(BTN_UNCHECK_ALL, true);
                handler.setButtonEnabled(BTN_DELETE_ROW, true);
                handler.setButtonEnabled(BTN_DISABLE, true);
                handler.setButtonEnabled(BTN_ATTACHMENTS, true);
                // START 2022/10/05 M.Kikushima [QC#60560,ADD]
                handler.setButtonEnabled(BTN_APPLY_TO_ALL, true);
                // END 2022/10/05 M.Kikushima [QC#60560,ADD]

                // CMN_Submit
                handler.setButtonProperties(BTN_CMN_BTN_2[0], BTN_CMN_BTN_2[1], BTN_CMN_BTN_2[2], 1, null);
            }

            // Detail
            scrnMsg.procrTpCd_AP.setInputProtected(false);
            scrnMsg.procrTpDescTxt_AP.setInputProtected(false);

            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                EZDGUIAttribute button = new EZDGUIAttribute(SCRN_ID, BTN_DETAIL_WH + i);
                button.setVisibility(true);
                scrnMsg.addGUIAttribute(button);

                EZDGUIAttribute button1 = new EZDGUIAttribute(SCRN_ID, BTN_DETAIL_WH_NM + i);
                button1.setVisibility(true);
                scrnMsg.addGUIAttribute(button1);

                scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(false);
                scrnMsg.A.no(i).mrpPlnNm_A1.setInputProtected(false);
                scrnMsg.A.no(i).rtlWhCatgDescTxt_A1.setInputProtected(true);
                scrnMsg.A.no(i).rtlWhCd_A1.setInputProtected(true);
                scrnMsg.A.no(i).rtlWhNm_A1.setInputProtected(true);
                scrnMsg.A.no(i).rtlSwhCd_A1.setInputProtected(true);
                scrnMsg.A.no(i).rtlSwhNm_A1.setInputProtected(true);
                scrnMsg.A.no(i).mdseCd_A1.setInputProtected(true);
                scrnMsg.A.no(i).mdseDescShortTxt_A1.setInputProtected(true);
                scrnMsg.A.no(i).coaMdseTpCd_A1.setInputProtected(true);
                scrnMsg.A.no(i).coaProdCd_A1.setInputProtected(true);
                scrnMsg.A.no(i).ropQty_A1.setInputProtected(false);
                scrnMsg.A.no(i).maxInvtyQty_A1.setInputProtected(false);
                scrnMsg.A.no(i).ovrdMaxInvtyQty_A1.setInputProtected(false);
                scrnMsg.A.no(i).mrpEnblFlg_A1.setInputProtected(false);
                scrnMsg.A.no(i).procrTpCd_AS.setInputProtected(false);
                scrnMsg.A.no(i).srcRtlWhCd_A1.setInputProtected(false);
                scrnMsg.A.no(i).rtlWhNm_A2.setInputProtected(true);
                scrnMsg.A.no(i).srcRtlSwhCd_A1.setInputProtected(false);
                scrnMsg.A.no(i).rtlSwhNm_A2.setInputProtected(true);
                scrnMsg.A.no(i).rplshDlyFlg_A1.setInputProtected(false);
                scrnMsg.A.no(i).rplshMonFlg_A1.setInputProtected(false);
                scrnMsg.A.no(i).rplshTueFlg_A1.setInputProtected(false);
                scrnMsg.A.no(i).rplshWedFlg_A1.setInputProtected(false);
                scrnMsg.A.no(i).rplshThuFlg_A1.setInputProtected(false);
                scrnMsg.A.no(i).rplshFriFlg_A1.setInputProtected(false);
                scrnMsg.A.no(i).supdFlg_A1.setInputProtected(true);
                scrnMsg.A.no(i).mrpInfoCmntTxt_A1.setInputProtected(false);
            }

            // START 2018/12/03 J.Kim [QC#18224,ADD]
            if (functionList.contains(FUNC_EDIT_FSM)) {
                for (int index = 0; index < scrnMsg.A.getValidCount(); index++) {
                    scrnMsg.A.no(index).procrTpCd_AS.setInputProtected(true);
                    scrnMsg.A.no(index).srcRtlWhCd_A1.setInputProtected(true);
                    scrnMsg.A.no(index).rtlWhNm_A2.setInputProtected(true);
                    scrnMsg.A.no(index).srcRtlSwhCd_A1.setInputProtected(true);
                    handler.setButtonEnabled(BTN_DETAIL_WH, index, false);
                    handler.setButtonEnabled(BTN_DETAIL_WH_NM, index, false);
                }
            }
            // END 2018/12/03 J.Kim [QC#18224,ADD]
        } else {
            // Reference Mode
            // Button
            handler.setButtonEnabled(BTN_ADD, false);
            handler.setButtonEnabled(BTN_IMPORT, false);
            handler.setButtonEnabled(BTN_COPY, false);
            handler.setButtonEnabled(BTN_CHECK_ALL, false);
            handler.setButtonEnabled(BTN_UNCHECK_ALL, false);
            handler.setButtonEnabled(BTN_DELETE_ROW, false);
            handler.setButtonEnabled(BTN_DISABLE, false);
            // CMN_Submit
            handler.setButtonProperties(BTN_CMN_BTN_2[0], BTN_CMN_BTN_2[1], BTN_CMN_BTN_2[2], 0, null);

            // Detail
            scrnMsg.procrTpCd_AP.setInputProtected(true);
            scrnMsg.procrTpDescTxt_AP.setInputProtected(true);

            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                EZDGUIAttribute button = new EZDGUIAttribute(SCRN_ID, BTN_DETAIL_WH + i);
                button.setVisibility(false);
                scrnMsg.addGUIAttribute(button);

                EZDGUIAttribute button1 = new EZDGUIAttribute(SCRN_ID, BTN_DETAIL_WH_NM + i);
                button1.setVisibility(false);
                scrnMsg.addGUIAttribute(button1);

                scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(true);
                scrnMsg.A.no(i).mrpPlnNm_A1.setInputProtected(true);
                scrnMsg.A.no(i).rtlWhCatgDescTxt_A1.setInputProtected(true);
                scrnMsg.A.no(i).rtlWhCd_A1.setInputProtected(true);
                scrnMsg.A.no(i).rtlWhNm_A1.setInputProtected(true);
                scrnMsg.A.no(i).rtlSwhCd_A1.setInputProtected(true);
                scrnMsg.A.no(i).rtlSwhNm_A1.setInputProtected(true);
                scrnMsg.A.no(i).mdseCd_A1.setInputProtected(true);
                scrnMsg.A.no(i).mdseDescShortTxt_A1.setInputProtected(true);
                scrnMsg.A.no(i).coaMdseTpCd_A1.setInputProtected(true);
                scrnMsg.A.no(i).coaProdCd_A1.setInputProtected(true);
                scrnMsg.A.no(i).ropQty_A1.setInputProtected(true);
                scrnMsg.A.no(i).maxInvtyQty_A1.setInputProtected(true);
                scrnMsg.A.no(i).ovrdMaxInvtyQty_A1.setInputProtected(true);
                scrnMsg.A.no(i).mrpEnblFlg_A1.setInputProtected(true);
                scrnMsg.A.no(i).procrTpCd_AS.setInputProtected(true);
                scrnMsg.A.no(i).srcRtlWhCd_A1.setInputProtected(true);
                scrnMsg.A.no(i).rtlWhNm_A2.setInputProtected(true);
                scrnMsg.A.no(i).srcRtlSwhCd_A1.setInputProtected(true);
                scrnMsg.A.no(i).rtlSwhNm_A2.setInputProtected(true);
                scrnMsg.A.no(i).rplshDlyFlg_A1.setInputProtected(true);
                scrnMsg.A.no(i).rplshMonFlg_A1.setInputProtected(true);
                scrnMsg.A.no(i).rplshTueFlg_A1.setInputProtected(true);
                scrnMsg.A.no(i).rplshWedFlg_A1.setInputProtected(true);
                scrnMsg.A.no(i).rplshThuFlg_A1.setInputProtected(true);
                scrnMsg.A.no(i).rplshFriFlg_A1.setInputProtected(true);
                scrnMsg.A.no(i).supdFlg_A1.setInputProtected(true);
                scrnMsg.A.no(i).mrpInfoCmntTxt_A1.setInputProtected(true);
            }
        }
    }

    /**
     * Checks for register authority.
     * @param functionList List<String>
     * @return true, if successful
     */
    private static boolean hasRegisterAuthority(List<String> functionList) {
        for (String function : functionList) {
            // START 2018/12/03 J.Kim [QC#18224]
            //if (FUNC_EDIT.equals(function)) {
            if (FUNC_EDIT.equals(function) || FUNC_EDIT_FSM.equals(function)) {
                // END 2018/12/03 J.Kim [QC#18224]
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
    public static void setNameForMessage(NPAL1070BMsg scrnMsg) {

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

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).mrpPlnNm_A1.setNameForMessage(DISPLAY_NM_MRP_PLN_NM);
            scrnMsg.A.no(i).ropQty_A1.setNameForMessage(DISPLAY_MIN_QTY);
            scrnMsg.A.no(i).maxInvtyQty_A1.setNameForMessage(DISPLAY_MAX_QTY);
            scrnMsg.A.no(i).ovrdMaxInvtyQty_A1.setNameForMessage(DISPLAY_REORDER_QTY);
            scrnMsg.A.no(i).procrTpCd_AS.setNameForMessage(DISPLAY_NM_PROCR_TP_CD);
            scrnMsg.A.no(i).srcRtlWhCd_A1.setNameForMessage(DISPLAY_NM_SRC_RTL_WH_CD);
            scrnMsg.A.no(i).srcRtlSwhCd_A1.setNameForMessage(DISPLAY_NM_SRC_RTL_SWH_CD);
            scrnMsg.A.no(i).mrpInfoCmntTxt_A1.setNameForMessage(DISPLAY_COMMENT);
        }

    }

    /**
     * The method explanation: set initial parameter to call popup.(NMAL6800)
     * @param scrnMsg NPAL1070BMsg
     */
    public static void setInitParamForItemMasterPopup(NPAL1070BMsg scrnMsg) {

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
     * The method explanation: set parameter to call popup.(NMAL6800)
     * @param scrnMsg NPAL1070BMsg
     * @return Object[]
     */
    public static Object[] setParamForItemMasterPopup(NPAL1070BMsg scrnMsg) {

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
     * The method explanation: set initial parameter to call popup.(NMAL6050)
     * @param scrnMsg NPAL1070BMsg
     */
    public static void setInitParamForManagerPopup(NPAL1070BMsg scrnMsg) {

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
     * The method explanation: set parameter to call popup.(NMAL6050)
     * @param scrnMsg NPAL1070BMsg
     * @return Object[]
     */
    public static Object[] setParamForManagerPopup(NPAL1070BMsg scrnMsg) {

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
     * @param scrnMsg NPAL1070BMsg
     * @param eventRowIndex int
     * @return LocationPopup Parameter (NPAL1010) Object[]
     */
    public static Object[] setLocationPopupParam(NPAL1070BMsg scrnMsg, int eventRowIndex) {

        ZYPTableUtil.clear(scrnMsg.P);
        // these parameter do not set value.
        // param1 :Location Code
        // param2 :Location Name
        // param11:Only RTL_WH Flag
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
            // param9 :SWH Code
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(8).xxPopPrm, scrnMsg.srcRtlSwhCd);
            // param10:SWH Name
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(9).xxPopPrm, scrnMsg.rtlSwhNm_S2);
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
        } else if (EVENT_OPEN_WIN_SOURCE_SUB_WAREHOUSE_DETAIL.equals(scrnMsg.xxPopPrm_EV.getValue())) {
            // param3 :Location Type(List)
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(2).xxPopPrm, NMXC100001EnableWH.getLocRoleTpForPopup(scrnMsg.glblCmpyCd.getValue(), BIZ_APP_ID, srcLocTpList));
            // param7 :Retail WH Code
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(6).xxPopPrm, scrnMsg.A.no(eventRowIndex).srcRtlWhCd_A1);
            // param8 :Retail WH Name
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(7).xxPopPrm, scrnMsg.A.no(eventRowIndex).rtlWhNm_A2);
            // param9 :SWH Code
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(8).xxPopPrm, scrnMsg.A.no(eventRowIndex).srcRtlSwhCd_A1);
            // param10:SWH Name
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(9).xxPopPrm, scrnMsg.A.no(eventRowIndex).rtlSwhNm_A2);
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
     * The method explanation: set initial parameter to call popup.(NPAL1370)
     * @param scrnMsg NPAL1070BMsg
     */
    public static void setInitParamForMinMax_CopyPopup(NPAL1070BMsg scrnMsg) {

        scrnMsg.xxPopPrm_C0.clear();
        scrnMsg.xxPopPrm_C1.clear();
        scrnMsg.xxPopPrm_C2.clear();
        scrnMsg.xxPopPrm_C3.clear();
        scrnMsg.xxPopPrm_C4.clear();

    }

    /**
     * The method explanation: set parameter to call popup.(NPAL1370)
     * @param scrnMsg NPAL1070BMsg
     * @return Object[]
     */
    public static Object[] setParamForMinMax_CopyPopup(NPAL1070BMsg scrnMsg) {

        Object[] params = new Object[5];
        params[0] = scrnMsg.xxPopPrm_C0;
        params[1] = scrnMsg.xxPopPrm_C1;
        params[2] = scrnMsg.xxPopPrm_C2;
        params[3] = scrnMsg.xxPopPrm_C3;
        params[4] = scrnMsg.xxPopPrm_C4;

        return params;
    }

    /**
     * The method explanation: set parameter to call popup.(ZYPL0310)
     * @param p NPAL1070_XBMsgArray
     * @param size int
     * @return Object[]
     */

    public static Object[] toArray_popupForZYPL0310(NPAL1070_XBMsgArray p, int size) {
        Object[] param = new Object[size];
        for (int i = 0; i < size; i++) {
            param[i] = p.no(i).xxPopPrm_AT.getValue();
        }
        return param;
    }

}
