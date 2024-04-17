/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1360.common;

import static business.servlet.NPAL1280.constant.NPAL1280Constant.IDX_3;
import static business.servlet.NPAL1360.constant.NPAL1360Constant.BIZ_APP_ID;
import static business.servlet.NPAL1360.constant.NPAL1360Constant.BTN_ATTACHMENTS;
import static business.servlet.NPAL1360.constant.NPAL1360Constant.BTN_CANCEL;
import static business.servlet.NPAL1360.constant.NPAL1360Constant.BTN_CMN_BTN_01;
import static business.servlet.NPAL1360.constant.NPAL1360Constant.BTN_CMN_BTN_02;
import static business.servlet.NPAL1360.constant.NPAL1360Constant.BTN_CMN_BTN_03;
import static business.servlet.NPAL1360.constant.NPAL1360Constant.BTN_CMN_BTN_04;
import static business.servlet.NPAL1360.constant.NPAL1360Constant.BTN_CMN_BTN_05;
import static business.servlet.NPAL1360.constant.NPAL1360Constant.BTN_CMN_BTN_06;
import static business.servlet.NPAL1360.constant.NPAL1360Constant.BTN_CMN_BTN_07;
import static business.servlet.NPAL1360.constant.NPAL1360Constant.BTN_CMN_BTN_08;
import static business.servlet.NPAL1360.constant.NPAL1360Constant.BTN_CMN_BTN_09;
import static business.servlet.NPAL1360.constant.NPAL1360Constant.BTN_CMN_BTN_10;
import static business.servlet.NPAL1360.constant.NPAL1360Constant.BTN_COMPONENT;
import static business.servlet.NPAL1360.constant.NPAL1360Constant.BTN_LINE_SERIAL;
import static business.servlet.NPAL1360.constant.NPAL1360Constant.BTN_LINE_SWH;
import static business.servlet.NPAL1360.constant.NPAL1360Constant.BTN_PRINT;
import static business.servlet.NPAL1360.constant.NPAL1360Constant.BTN_SEARCH;
import static business.servlet.NPAL1360.constant.NPAL1360Constant.BTN_SERIAL;
import static business.servlet.NPAL1360.constant.NPAL1360Constant.BTN_SET_KITITEMNAME;
import static business.servlet.NPAL1360.constant.NPAL1360Constant.BTN_SET_SWHNAME;
import static business.servlet.NPAL1360.constant.NPAL1360Constant.BTN_SET_WHNAME;
import static business.servlet.NPAL1360.constant.NPAL1360Constant.BTN_SUPPLYDEMAND;
import static business.servlet.NPAL1360.constant.NPAL1360Constant.DISPLAY_COMP_SWH;
import static business.servlet.NPAL1360.constant.NPAL1360Constant.DISPLAY_KIT_ITEM;
import static business.servlet.NPAL1360.constant.NPAL1360Constant.DISPLAY_MESSAGE;
import static business.servlet.NPAL1360.constant.NPAL1360Constant.DISPLAY_ORD_QTY;
import static business.servlet.NPAL1360.constant.NPAL1360Constant.DISPLAY_REQ_COMP_DT;
import static business.servlet.NPAL1360.constant.NPAL1360Constant.DISPLAY_SERIAL_NUM;
import static business.servlet.NPAL1360.constant.NPAL1360Constant.DISPLAY_SPPLY_SWH;
import static business.servlet.NPAL1360.constant.NPAL1360Constant.DISPLAY_WH;
import static business.servlet.NPAL1360.constant.NPAL1360Constant.DISPLAY_WRK_ORD_NUM;
import static business.servlet.NPAL1360.constant.NPAL1360Constant.DISPLAY_WRK_ORD_TP;
import static business.servlet.NPAL1360.constant.NPAL1360Constant.EVENT_OPEN_WIN_COMPLETION_SWH;
import static business.servlet.NPAL1360.constant.NPAL1360Constant.EVENT_OPEN_WIN_KIT_SERIAL;
import static business.servlet.NPAL1360.constant.NPAL1360Constant.EVENT_OPEN_WIN_SUPPLY_SERIAL;
import static business.servlet.NPAL1360.constant.NPAL1360Constant.EVENT_OPEN_WIN_SUPPLY_SWH;
import static business.servlet.NPAL1360.constant.NPAL1360Constant.EVENT_OPEN_WIN_WH;
import static business.servlet.NPAL1360.constant.NPAL1360Constant.FUNC_EDIT;
import static business.servlet.NPAL1360.constant.NPAL1360Constant.INDEX_0;
import static business.servlet.NPAL1360.constant.NPAL1360Constant.INDEX_1;
import static business.servlet.NPAL1360.constant.NPAL1360Constant.INDEX_10;
import static business.servlet.NPAL1360.constant.NPAL1360Constant.INDEX_11;
import static business.servlet.NPAL1360.constant.NPAL1360Constant.INDEX_12;
import static business.servlet.NPAL1360.constant.NPAL1360Constant.INDEX_2;
import static business.servlet.NPAL1360.constant.NPAL1360Constant.INDEX_3;
import static business.servlet.NPAL1360.constant.NPAL1360Constant.INDEX_4;
import static business.servlet.NPAL1360.constant.NPAL1360Constant.INDEX_5;
import static business.servlet.NPAL1360.constant.NPAL1360Constant.INDEX_6;
import static business.servlet.NPAL1360.constant.NPAL1360Constant.INDEX_7;
import static business.servlet.NPAL1360.constant.NPAL1360Constant.INDEX_8;
import static business.servlet.NPAL1360.constant.NPAL1360Constant.INDEX_9;
import static business.servlet.NPAL1360.constant.NPAL1360Constant.KIT_ITEM_LINE_NUM;
import static business.servlet.NPAL1360.constant.NPAL1360Constant.MAX_ROWS;
import static business.servlet.NPAL1360.constant.NPAL1360Constant.NPAM1521E;
import static business.servlet.NPAL1360.constant.NPAL1360Constant.SCRN_ID;
import static business.servlet.NPAL1360.constant.NPAL1360Constant.SERIAL_EDIT;
import static business.servlet.NPAL1360.constant.NPAL1360Constant.SERIAL_READ;
import static business.servlet.NPAL1360.constant.NPAL1360Constant.SUFFIX;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDGUIAttribute;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NPAL1360.NPAL1360BMsg;
import business.servlet.NPAL1360.NPAL1360_XBMsgArray;

import com.canon.cusa.s21.common.NMX.NMXC100001.NMXC100001EnableWH;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_WRK_ORD_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INBD_OTBD;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_ITEM_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WRK_ORD_STS;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * Business ID : NPAL1360 Kitting Work Order Entry
 * Function Name : Common Logic
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/12/2016   CITS            Keiichi Masaki  Create          N/A
 * 01/31/2017   CITS            Y.IWASAKI       Update          QC#16109
 * 03/07/2017   CITS            Y.IWASAKI       Update          QC#17363
 * 11/07/2017   CITS            S.Katsuma       Update          SOL#014(QC#18401)
 *</pre>
 */

public class NPAL1360CommonLogic {

    /**
     * The method explanation: The display control of the screen item
     * for Initialized screen
     * @param handler EZDCommonHandler
     * @param scrnMsg NPAL1360BMsg
     * @param functionList List<String>
     */
    public static void ctrlScrnItemDispInit(EZDCommonHandler handler, NPAL1360BMsg scrnMsg, List<String> functionList) {

        // column input protection
        // true : block entry
        // false : input possible
        // Header Control
        scrnMsg.wrkOrdNum.setInputProtected(false);
        scrnMsg.dsWrkOrdStsNm.setInputProtected(true);
        scrnMsg.wrkOrdDt.setInputProtected(true);
        scrnMsg.prchReqNum.setInputProtected(true);
        scrnMsg.rtlWhNm.setInputProtected(true);
        scrnMsg.rtlSwhNm.setInputProtected(true);
        scrnMsg.fnshMdseDescShortTxt.setInputProtected(true);
        scrnMsg.effFromDt.setInputProtected(true);
        scrnMsg.effThruDt.setInputProtected(true);
        scrnMsg.basePkgUomCd.setInputProtected(true);
        scrnMsg.fnshGoodsRcvQty.setInputProtected(true);
        scrnMsg.fnshGoodsBalQty.setInputProtected(true);
        scrnMsg.fnshGoodsCancQty.setInputProtected(true);

        // Button activation
        handler.setButtonEnabled(BTN_SEARCH, true);

        // common button protection
        // 0 : inactive
        // 1 : active
        handler.setButtonProperties(BTN_CMN_BTN_01[0], BTN_CMN_BTN_01[1], BTN_CMN_BTN_01[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_03[0], BTN_CMN_BTN_03[1], BTN_CMN_BTN_03[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_04[0], BTN_CMN_BTN_04[1], BTN_CMN_BTN_04[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_05[0], BTN_CMN_BTN_05[1], BTN_CMN_BTN_05[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_06[0], BTN_CMN_BTN_06[1], BTN_CMN_BTN_06[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_07[0], BTN_CMN_BTN_07[1], BTN_CMN_BTN_07[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_08[0], BTN_CMN_BTN_08[1], BTN_CMN_BTN_08[2], 1, null);
        handler.setButtonProperties(BTN_CMN_BTN_09[0], BTN_CMN_BTN_09[1], BTN_CMN_BTN_09[2], 1, null);
        handler.setButtonProperties(BTN_CMN_BTN_10[0], BTN_CMN_BTN_10[1], BTN_CMN_BTN_10[2], 1, null);

        // set protect based on authority
        setAuthorityProtect(handler, scrnMsg, functionList);

    }

    /**
     * Sets the authority protect.
     * @param handler EZDCommonHandler
     * @param scrnMsg NPAL1360BMsg
     * @param functionList List<String>
     */
    public static void setAuthorityProtect(EZDCommonHandler handler, NPAL1360BMsg scrnMsg, List<String> functionList) {

        scrnMsg.clearAllGUIAttribute(SCRN_ID);
        S21TableColorController tblColor = new S21TableColorController(SCRN_ID, scrnMsg);
        tblColor.setAlternateRowsBG("A", scrnMsg.A);

        if (hasRegisterAuthority(functionList)) {
            // Edit Mode
            if (!ZYPCommonFunc.hasValue(scrnMsg.wrkOrdStsCd)) {
                // Initial
                editModeInit(handler, scrnMsg);
            } else if (WRK_ORD_STS.SAVED.equals(scrnMsg.wrkOrdStsCd.getValue())) {
                // Work order status is "Saved".
                editModeInit(handler, scrnMsg);
            } else {
                // Work order status is others.
                editModeAfterSubmit(handler, scrnMsg);
            }
        } else {
            // Reference Mode
            referenceMode(handler, scrnMsg);
        }
    }

    /**
     * Edit mode screen control initial or saved status
     * @param handler EZDCommonHandler
     * @param scrnMsg NPAL1360BMsg
     */
    public static void editModeInit(EZDCommonHandler handler, NPAL1360BMsg scrnMsg) {

        // Header Control
        scrnMsg.dsWrkOrdTpCd_CD.setInputProtected(false);
        scrnMsg.dsWrkOrdTpDescTxt_NM.setInputProtected(false);
        scrnMsg.dsWrkOrdTpCd_SL.setInputProtected(false);
        scrnMsg.rtlWhCd.setInputProtected(false);
        scrnMsg.cpltRtlSwhCd.setInputProtected(false);
        scrnMsg.rqstRcvDt.setInputProtected(false);
        scrnMsg.fnshGoodsMdseCd.setInputProtected(false);
        scrnMsg.fnshGoodsOrdQty.setInputProtected(false);
        scrnMsg.wrkOrdMsgTxt.setInputProtected(false);
        scrnMsg.serNum.setInputProtected(true);

        // Anchor
        scrnMsg.xxLinkAncr_WH.setInputProtected(false);
        scrnMsg.xxLinkAncr_SW.setInputProtected(false);
        scrnMsg.xxLinkAncr_KT.setInputProtected(false);

        // Button
        handler.setButtonEnabled(BTN_SET_WHNAME, true);
        handler.setButtonEnabled(BTN_SET_SWHNAME, true);
        handler.setButtonEnabled(BTN_SET_KITITEMNAME, true);
        handler.setButtonEnabled(BTN_COMPONENT, true);
        handler.setButtonEnabled(BTN_PRINT, false);
        // START 2017/11/07 S.Katsuma [SOL#014(QC#18401),ADD]
        if (scrnMsg.A.getValidCount() == 0) {
            handler.setButtonEnabled(BTN_SUPPLYDEMAND, false);
        } else {
            handler.setButtonEnabled(BTN_SUPPLYDEMAND, true);
        }
        // END 2017/11/07 S.Katsuma [SOL#014(QC#18401),ADD]

        if (!ZYPCommonFunc.hasValue(scrnMsg.wrkOrdStsCd)) {
            handler.setButtonEnabled(BTN_CANCEL, false);
            handler.setButtonEnabled(BTN_ATTACHMENTS, false);
        } else if (WRK_ORD_STS.SAVED.equals(scrnMsg.wrkOrdStsCd.getValue()) || WRK_ORD_STS.SHIPPED.equals(scrnMsg.wrkOrdStsCd.getValue())) {
            handler.setButtonEnabled(BTN_CANCEL, true);
            handler.setButtonEnabled(BTN_ATTACHMENTS, true);
        }

        // Serial# Button
        if (!ZYPCommonFunc.hasValue(scrnMsg.wrkOrdStsCd)) {
            // Create new WO
            handler.setButtonEnabled(BTN_SERIAL, true);
        } else if (WRK_ORD_STS.SAVED.equals(scrnMsg.wrkOrdStsCd.getValue())) {
            // Load existing WO, but may change MDSE_CD
            handler.setButtonEnabled(BTN_SERIAL, true);
        } else if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.rcvSerTakeFlg.getValue())) {
            // Load existing WO with serial item
            handler.setButtonEnabled(BTN_SERIAL, true);
        } else {
            // Load existing WO with no-serial item
            handler.setButtonEnabled(BTN_SERIAL, false);
        }

        // CMN_Submit
        if (scrnMsg.A.getValidCount() == 0) {
            handler.setButtonProperties(BTN_CMN_BTN_02[0], BTN_CMN_BTN_02[1], BTN_CMN_BTN_02[2], 0, null);
        } else {
            handler.setButtonProperties(BTN_CMN_BTN_02[0], BTN_CMN_BTN_02[1], BTN_CMN_BTN_02[2], 1, null);
        }

        // Detail Control
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            // SWH button
            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).invtyCtrlFlg_A1.getValue()) && DS_WRK_ORD_TP.KIT.equals(scrnMsg.dsWrkOrdTpCd_SL.getValue())) {
                if (!ZYPCommonFunc.hasValue(scrnMsg.wrkOrdStsCd) || WRK_ORD_STS.SAVED.equals(scrnMsg.wrkOrdStsCd.getValue())) {
                    handler.setButtonEnabled(BTN_LINE_SWH, i, true);
                    scrnMsg.A.no(i).splyRtlSwhCd_A1.setInputProtected(false);
                } else {
                    handler.setButtonEnabled(BTN_LINE_SWH, i, false);
                    scrnMsg.A.no(i).splyRtlSwhCd_A1.setInputProtected(true);
                }
            } else {
                handler.setButtonEnabled(BTN_LINE_SWH, i, false);
                scrnMsg.A.no(i).splyRtlSwhCd_A1.setInputProtected(true);
            }
            // Serial#
            EZDGUIAttribute serial = new EZDGUIAttribute(SCRN_ID, BTN_LINE_SERIAL + i);
            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).rcvSerTakeFlg_D1.getValue())) {
                if (BigDecimal.ONE.equals(scrnMsg.A.no(i).origGoodsOrdQty_A1.getValue())) {
                    scrnMsg.A.no(i).serNum_A1.setInputProtected(false);
                    serial.setVisibility(false);
                } else {
                    scrnMsg.A.no(i).serNum_A1.setInputProtected(true);
                    serial.setVisibility(true);
                }
            } else {
                scrnMsg.A.no(i).serNum_A1.setInputProtected(true);
                serial.setVisibility(false);
            }
            scrnMsg.addGUIAttribute(serial);

            scrnMsg.A.no(i).origMdseDescShortTxt_A1.setInputProtected(true);
        }
    }

    /**
     * Edit mode screen control after submit.
     * @param handler EZDCommonHandler
     * @param scrnMsg NPAL1360BMsg
     */
    public static void editModeAfterSubmit(EZDCommonHandler handler, NPAL1360BMsg scrnMsg) {

        // Header
        scrnMsg.dsWrkOrdTpCd_CD.setInputProtected(true);
        scrnMsg.dsWrkOrdTpDescTxt_NM.setInputProtected(true);
        scrnMsg.dsWrkOrdTpCd_SL.setInputProtected(true);
        scrnMsg.rtlWhCd.setInputProtected(true);
        scrnMsg.cpltRtlSwhCd.setInputProtected(true);
        scrnMsg.rqstRcvDt.setInputProtected(true);
        scrnMsg.fnshGoodsMdseCd.setInputProtected(true);
        scrnMsg.fnshGoodsOrdQty.setInputProtected(true);
        scrnMsg.wrkOrdMsgTxt.setInputProtected(true);
        scrnMsg.serNum.setInputProtected(true);

        // Anchor
        scrnMsg.xxLinkAncr_WH.setInputProtected(true);
        scrnMsg.xxLinkAncr_SW.setInputProtected(true);
        scrnMsg.xxLinkAncr_KT.setInputProtected(true);

        // Button
        handler.setButtonEnabled(BTN_SET_WHNAME, false);
        handler.setButtonEnabled(BTN_SET_SWHNAME, false);
        handler.setButtonEnabled(BTN_SET_KITITEMNAME, false);
        handler.setButtonEnabled(BTN_COMPONENT, false);
        handler.setButtonEnabled(BTN_ATTACHMENTS, true);
        if (WRK_ORD_STS.VALIDATED.equals(scrnMsg.wrkOrdStsCd.getValue()) || WRK_ORD_STS.SHIPPED.equals(scrnMsg.wrkOrdStsCd.getValue())) {
            handler.setButtonEnabled(BTN_PRINT, true);
        } else {
            handler.setButtonEnabled(BTN_PRINT, false);
        }

        if (WRK_ORD_STS.SHIPPED.equals(scrnMsg.wrkOrdStsCd.getValue())) {
            handler.setButtonEnabled(BTN_CANCEL, true);
        } else {
            handler.setButtonEnabled(BTN_CANCEL, false);
        }
        // START 2017/11/07 S.Katsuma [SOL#014(QC#18401),ADD]
        handler.setButtonEnabled(BTN_SUPPLYDEMAND, true);
        // END 2017/11/07 S.Katsuma [SOL#014(QC#18401),ADD]

        // Serial# Button
        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.rcvSerTakeFlg.getValue())) {
            handler.setButtonEnabled(BTN_SERIAL, true);
        } else {
            handler.setButtonEnabled(BTN_SERIAL, false);
        }

        // CMN_Submit
        handler.setButtonProperties(BTN_CMN_BTN_02[0], BTN_CMN_BTN_02[1], BTN_CMN_BTN_02[2], 0, null);

        // Detail Control
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            // SWH button
            handler.setButtonEnabled(BTN_LINE_SWH, i, false);
            scrnMsg.A.no(i).splyRtlSwhCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).serNum_A1.setInputProtected(true);
            // Serial#
            EZDGUIAttribute serial = new EZDGUIAttribute(SCRN_ID, BTN_LINE_SERIAL + i);
            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).rcvSerTakeFlg_D1.getValue())) {
                if (BigDecimal.ONE.equals(scrnMsg.A.no(i).origGoodsOrdQty_A1.getValue())) {
                    serial.setVisibility(false);
                } else {
                    serial.setVisibility(true);
                }
            } else {
                scrnMsg.A.no(i).serNum_A1.setInputProtected(true);
                serial.setVisibility(false);
            }
            scrnMsg.addGUIAttribute(serial);

            scrnMsg.A.no(i).origMdseDescShortTxt_A1.setInputProtected(true);
        }
    }

    /**
     * Reference mode screen control
     * @param handler EZDCommonHandler
     * @param scrnMsg NPAL1360BMsg
     */
    public static void referenceMode(EZDCommonHandler handler, NPAL1360BMsg scrnMsg) {
        // Header Control
        scrnMsg.dsWrkOrdTpCd_CD.setInputProtected(true);
        scrnMsg.dsWrkOrdTpDescTxt_NM.setInputProtected(true);
        scrnMsg.rtlWhCd.setInputProtected(true);
        scrnMsg.cpltRtlSwhCd.setInputProtected(true);
        scrnMsg.rqstRcvDt.setInputProtected(true);
        scrnMsg.fnshGoodsMdseCd.setInputProtected(true);
        scrnMsg.serNum.setInputProtected(true);
        scrnMsg.fnshGoodsOrdQty.setInputProtected(true);
        scrnMsg.wrkOrdMsgTxt.setInputProtected(true);

        // Anchor
        scrnMsg.xxLinkAncr_WH.setInputProtected(true);
        scrnMsg.xxLinkAncr_SW.setInputProtected(true);
        scrnMsg.xxLinkAncr_KT.setInputProtected(true);

        // Button Control
        handler.setButtonEnabled(BTN_COMPONENT, false);
        handler.setButtonEnabled(BTN_CANCEL, false);
        handler.setButtonEnabled(BTN_PRINT, false);
        handler.setButtonEnabled(BTN_ATTACHMENTS, false);
        // START 2017/11/07 S.Katsuma [SOL#014(QC#18401),ADD]
        handler.setButtonEnabled(BTN_SUPPLYDEMAND, false);
        // END 2017/11/07 S.Katsuma [SOL#014(QC#18401),ADD]

        // CMN_Submit
        handler.setButtonProperties(BTN_CMN_BTN_02[0], BTN_CMN_BTN_02[1], BTN_CMN_BTN_02[2], 0, null);

        // Serial# Button
        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.rcvSerTakeFlg.getValue()) && ZYPCommonFunc.hasValue(scrnMsg.fnshGoodsOrdQty)) {
            handler.setButtonEnabled(BTN_SERIAL, true);
        } else {
            handler.setButtonEnabled(BTN_SERIAL, false);
        }

        // Detail Control
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            // SWH button
            EZDGUIAttribute swh = new EZDGUIAttribute(SCRN_ID, BTN_LINE_SWH + i);
            swh.setVisibility(false);
            scrnMsg.addGUIAttribute(swh);
            // Serial# button
            EZDGUIAttribute serial = new EZDGUIAttribute(SCRN_ID, BTN_LINE_SERIAL + i);
            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).rcvSerTakeFlg_D1.getValue())) {
                if (BigDecimal.ONE.equals(scrnMsg.A.no(i).origGoodsOrdQty_A1.getValue())) {
                    serial.setVisibility(false);
                } else {
                    serial.setVisibility(true);
                }
            } else {
                serial.setVisibility(false);
            }
            scrnMsg.addGUIAttribute(serial);

            scrnMsg.A.no(i).origMdseDescShortTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).splyRtlSwhCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).serNum_A1.setInputProtected(true);
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
    public static void setNameForMessage(NPAL1360BMsg scrnMsg) {

        // Search Condition
        scrnMsg.wrkOrdNum.setNameForMessage(DISPLAY_WRK_ORD_NUM);
        scrnMsg.dsWrkOrdTpCd_SL.setNameForMessage(DISPLAY_WRK_ORD_TP);
        scrnMsg.rtlWhCd.setNameForMessage(DISPLAY_WH);
        scrnMsg.cpltRtlSwhCd.setNameForMessage(DISPLAY_COMP_SWH);
        scrnMsg.rqstRcvDt.setNameForMessage(DISPLAY_REQ_COMP_DT);
        scrnMsg.fnshGoodsMdseCd.setNameForMessage(DISPLAY_KIT_ITEM);
        scrnMsg.fnshGoodsOrdQty.setNameForMessage(DISPLAY_ORD_QTY);
        scrnMsg.serNum.setNameForMessage(DISPLAY_SERIAL_NUM);
        scrnMsg.wrkOrdMsgTxt.setNameForMessage(DISPLAY_MESSAGE);

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).splyRtlSwhCd_A1.setNameForMessage(DISPLAY_SPPLY_SWH);
            scrnMsg.A.no(i).serNum_A1.setNameForMessage(DISPLAY_SERIAL_NUM);
        }

    }

    /**
     * Set Location Popup parameter
     * @param scrnMsg NPAL1360BMsg
     * @param eventRowIndex int
     * @return LocationPopup Parameter (NPAL1010) Object[]
     */
    public static Object[] setLocationPopupParam(NPAL1360BMsg scrnMsg, int eventRowIndex) {

        ZYPTableUtil.clear(scrnMsg.P);

        List<String> locTpList = new ArrayList<String>();
        locTpList.add(LOC_TP.WAREHOUSE);

        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(INDEX_2).xxPopPrm, NMXC100001EnableWH.getLocRoleTpForPopup(scrnMsg.glblCmpyCd.getValue(), BIZ_APP_ID, locTpList));
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(INDEX_3).xxPopPrm, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(INDEX_4).xxPopPrm, ZYPConstant.FLG_OFF_N);

        if (EVENT_OPEN_WIN_WH.equals(scrnMsg.eventNm.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(INDEX_6).xxPopPrm, scrnMsg.rtlWhCd);
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(INDEX_10).xxPopPrm, ZYPConstant.FLG_ON_Y);
        } else if (EVENT_OPEN_WIN_COMPLETION_SWH.equals(scrnMsg.eventNm.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(INDEX_6).xxPopPrm, scrnMsg.rtlWhCd);
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(INDEX_8).xxPopPrm, scrnMsg.cpltRtlSwhCd);
        } else if (EVENT_OPEN_WIN_SUPPLY_SWH.equals(scrnMsg.eventNm.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(INDEX_6).xxPopPrm, scrnMsg.rtlWhCd);
        }

        Object[] params = new Object[INDEX_12];
        params[INDEX_0] = scrnMsg.P.no(INDEX_0).xxPopPrm;
        params[INDEX_1] = scrnMsg.P.no(INDEX_1).xxPopPrm;
        params[INDEX_2] = scrnMsg.P.no(INDEX_2).xxPopPrm;
        params[INDEX_3] = scrnMsg.P.no(INDEX_3).xxPopPrm;
        params[INDEX_4] = scrnMsg.P.no(INDEX_4).xxPopPrm;
        params[INDEX_5] = scrnMsg.P.no(INDEX_5).xxPopPrm;
        params[INDEX_6] = scrnMsg.P.no(INDEX_6).xxPopPrm;
        params[INDEX_7] = scrnMsg.P.no(INDEX_7).xxPopPrm;
        params[INDEX_8] = scrnMsg.P.no(INDEX_8).xxPopPrm;
        params[INDEX_9] = scrnMsg.P.no(INDEX_9).xxPopPrm;
        params[INDEX_10] = scrnMsg.P.no(INDEX_10).xxPopPrm;
        params[INDEX_11] = scrnMsg.P.no(INDEX_11).xxPopPrm;

       return params;
    }

    /**
     * Set Kit Item Popup parameter
     * @param scrnMsg NPAL1360BMsg
     * @return Item Master Popup Parameter (NPAL6800) Object[]
     */
    public static Object[] setKitItemPopupParam(NPAL1360BMsg scrnMsg) {

        ZYPTableUtil.clear(scrnMsg.P);

        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(INDEX_0).xxPopPrm, scrnMsg.fnshGoodsMdseCd);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(INDEX_2).xxPopPrm, MDSE_ITEM_TP.KIT);

        Object[] params = new Object[INDEX_10];
        params[INDEX_0] = scrnMsg.P.no(INDEX_0).xxPopPrm;
        params[INDEX_1] = scrnMsg.P.no(INDEX_1).xxPopPrm;
        params[INDEX_2] = scrnMsg.P.no(INDEX_2).xxPopPrm;
        params[INDEX_3] = scrnMsg.P.no(INDEX_3).xxPopPrm;
        params[INDEX_4] = scrnMsg.P.no(INDEX_4).xxPopPrm;
        params[INDEX_5] = scrnMsg.P.no(INDEX_5).xxPopPrm;
        params[INDEX_6] = scrnMsg.P.no(INDEX_6).xxPopPrm;
        params[INDEX_7] = scrnMsg.P.no(INDEX_7).xxPopPrm;
        params[INDEX_8] = scrnMsg.P.no(INDEX_8).xxPopPrm;
        params[INDEX_9] = scrnMsg.P.no(INDEX_9).xxPopPrm;

        return params;

    }

    /**
     * Set Serial# Entry Popup parameter
     * @param scrnMsg NPAL1360BMsg
     * @param eventRowIndex int
     * @param functionList List<String>
     * @return Serial# Entry Popup Parameter (NLBL3000) Object[]
     */
    public static Object[] setSerialPopupParam(NPAL1360BMsg scrnMsg, List<String> functionList, int eventRowIndex) {

        ZYPTableUtil.clear(scrnMsg.P);
        ZYPTableUtil.clear(scrnMsg.L);
        ZYPTableUtil.clear(scrnMsg.W);
        Object[] params = new Object[INDEX_12];

        // 0.Suffix
        // The parameter set "8.Serial# List" table suffix
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(INDEX_0).xxPopPrm, SUFFIX);
        // 1.Header Number
        if (ZYPCommonFunc.hasValue(scrnMsg.wrkOrdNum)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(INDEX_1).xxPopPrm, scrnMsg.wrkOrdNum);
        }
        // 2.Merchandise Code --> null
        // 3.Merchandise Name --> null
        // 5.Mode 1:Edit 2:Read
        if (hasRegisterAuthority(functionList)) {
            if (!ZYPCommonFunc.hasValue(scrnMsg.wrkOrdStsCd)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(INDEX_5).xxPopPrm, SERIAL_EDIT);
            } else {
                if (WRK_ORD_STS.SAVED.equals(scrnMsg.wrkOrdStsCd.getValue())) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(INDEX_5).xxPopPrm, SERIAL_EDIT);
                } else {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(INDEX_5).xxPopPrm, SERIAL_READ);
                }
            }
        } else {
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(INDEX_5).xxPopPrm, SERIAL_READ);
        }

        // 6.Transaction Line Number --> null
        // 7.Transaction Line Sub Number --> null
        // 8.Serial# List

        if (EVENT_OPEN_WIN_KIT_SERIAL.equals(scrnMsg.eventNm.getValue())) {
            int fnshGoodsOrdQty = scrnMsg.fnshGoodsOrdQty.getValueInt();
            scrnMsg.L.setValidCount(fnshGoodsOrdQty);

            // At first, set corresponding serials from S to L, and backup other serials to W.
            int l = 0;
            int k = 0;
            for (int j = 0; j < scrnMsg.S.getValidCount(); j++) {
                if (KIT_ITEM_LINE_NUM.equals(scrnMsg.S.no(j).wrkOrdDtlLineNum_S1.getValue())) {
                    // 8-4.Serial#
                    ZYPEZDItemValueSetter.setValue(scrnMsg.L.no(l).serNum_L1, scrnMsg.S.no(j).serNum_S1);
                    l++;
                } else {
                    // Save Other Line Serial#
                    ZYPEZDItemValueSetter.setValue(scrnMsg.W.no(k).wrkOrdDtlLineNum_W1, scrnMsg.S.no(j).wrkOrdDtlLineNum_S1);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.W.no(k).serNum_W1, scrnMsg.S.no(j).serNum_S1);
                    k++;
                    scrnMsg.W.setValidCount(k);
                }
            }

            // Set other properties from scrnMsg to L.
            for (int i = 0; i < fnshGoodsOrdQty; i++) {
                // 8-1.Header#
                if (ZYPCommonFunc.hasValue(scrnMsg.wrkOrdNum)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.L.no(i).xxHdrNum_L1, scrnMsg.wrkOrdNum);
                }
                // 8-2.Transaction# --> null
                // 8-3.Merchandise Code
                ZYPEZDItemValueSetter.setValue(scrnMsg.L.no(i).mdseCd_L1, scrnMsg.fnshGoodsMdseCd);
                // 8-5.Enable Update Flag Y:Edit N:Read
                if (hasRegisterAuthority(functionList)) {
                    if (!ZYPCommonFunc.hasValue(scrnMsg.wrkOrdStsCd)) {
                        ZYPEZDItemValueSetter.setValue(scrnMsg.L.no(i).xxEdtModeFlg_L1, ZYPConstant.FLG_ON_Y);
                    } else {
                        if (WRK_ORD_STS.SAVED.equals(scrnMsg.wrkOrdStsCd.getValue())) {
                            ZYPEZDItemValueSetter.setValue(scrnMsg.L.no(i).xxEdtModeFlg_L1, ZYPConstant.FLG_ON_Y);
                        } else {
                            ZYPEZDItemValueSetter.setValue(scrnMsg.L.no(i).xxEdtModeFlg_L1, ZYPConstant.FLG_OFF_N);
                        }
                    }
                } else {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.L.no(i).xxEdtModeFlg_L1, ZYPConstant.FLG_OFF_N);
                }
                // 8-6.Reference Number --> null
                // 8-7.Request Type --> null
                // 8-9.SWH
                ZYPEZDItemValueSetter.setValue(scrnMsg.L.no(i).rtlSwhCd_L1, scrnMsg.cpltRtlSwhCd);
                // 8-10.Inventory Location Code
                ZYPEZDItemValueSetter.setValue(scrnMsg.L.no(i).invtyLocCd_L1, scrnMsg.rtlWhCd.getValue() + scrnMsg.cpltRtlSwhCd.getValue());
                // 8-11.Serial Mandatory Flag
                ZYPEZDItemValueSetter.setValue(scrnMsg.L.no(i).serNumTakeFlg_L1, ZYPConstant.FLG_OFF_N);
            }
        } else if (EVENT_OPEN_WIN_SUPPLY_SERIAL.equals(scrnMsg.eventNm.getValue())) {
            int origGoodsOrdQtyA1 = scrnMsg.A.no(eventRowIndex).origGoodsOrdQty_A1.getValueInt();
            scrnMsg.L.setValidCount(origGoodsOrdQtyA1);

            // At first, set corresponding serials from S to L, and backup other serials to W.
            int l = 0;
            int k = 0;
            for (int j = 0; j < scrnMsg.S.getValidCount(); j++) {
                if (scrnMsg.A.no(eventRowIndex).wrkOrdDtlLineNum_A1.getValue().equals(scrnMsg.S.no(j).wrkOrdDtlLineNum_S1.getValue())) {
                    // 8-4.Serial#
                    ZYPEZDItemValueSetter.setValue(scrnMsg.L.no(l).serNum_L1, scrnMsg.S.no(j).serNum_S1);
                    l++;
                } else {
                    // Save Other Line Serial#
                    ZYPEZDItemValueSetter.setValue(scrnMsg.W.no(k).wrkOrdDtlLineNum_W1, scrnMsg.S.no(j).wrkOrdDtlLineNum_S1);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.W.no(k).serNum_W1, scrnMsg.S.no(j).serNum_S1);
                    k++;
                    scrnMsg.W.setValidCount(k);
                }
            }

            // Set other properties from scrnMsg to L.
            for (int i = 0; i < origGoodsOrdQtyA1; i++) {
                // 8-1.Header#
                if (ZYPCommonFunc.hasValue(scrnMsg.wrkOrdNum)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.L.no(i).xxHdrNum_L1, scrnMsg.wrkOrdNum);
                }
                // 8-2.Transaction#
                ZYPEZDItemValueSetter.setValue(scrnMsg.L.no(i).xxDplyTrxNumTxt_L1, String.format("%03d", eventRowIndex + 1));
                // 8-3.Merchandise Code
                ZYPEZDItemValueSetter.setValue(scrnMsg.L.no(i).mdseCd_L1, scrnMsg.A.no(eventRowIndex).mdseCd_A1);
                // 8-5.Enable Update Flag
                if (hasRegisterAuthority(functionList)) {
                    if (!ZYPCommonFunc.hasValue(scrnMsg.wrkOrdStsCd)) {
                        ZYPEZDItemValueSetter.setValue(scrnMsg.L.no(i).xxEdtModeFlg_L1, ZYPConstant.FLG_ON_Y);
                    } else {
                        if (WRK_ORD_STS.SAVED.equals(scrnMsg.wrkOrdStsCd.getValue())) {
                            ZYPEZDItemValueSetter.setValue(scrnMsg.L.no(i).xxEdtModeFlg_L1, ZYPConstant.FLG_ON_Y);
                        } else {
                            ZYPEZDItemValueSetter.setValue(scrnMsg.L.no(i).xxEdtModeFlg_L1, ZYPConstant.FLG_OFF_N);
                        }
                    }
                } else {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.L.no(i).xxEdtModeFlg_L1, ZYPConstant.FLG_OFF_N);
                }
                // 8-6.Reference Number --> null
                // 8-7.Request Type --> null
                // 8-9.SWH
                ZYPEZDItemValueSetter.setValue(scrnMsg.L.no(i).rtlSwhCd_L1, scrnMsg.A.no(eventRowIndex).splyRtlSwhCd_A1);
                // 8-10.Inventory Location Code
                ZYPEZDItemValueSetter.setValue(scrnMsg.L.no(i).invtyLocCd_L1, scrnMsg.rtlWhCd.getValue() + scrnMsg.A.no(eventRowIndex).splyRtlSwhCd_A1.getValue());
                // 8-11.Serial Mandatory Flag
                ZYPEZDItemValueSetter.setValue(scrnMsg.L.no(i).serNumTakeFlg_L1, ZYPConstant.FLG_OFF_N);
            }
        }

        // 9.WH Code
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(INDEX_9).xxPopPrm, scrnMsg.rtlWhCd);
        // 10.WH Name
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(INDEX_10).xxPopPrm, scrnMsg.rtlWhNm);

        if (EVENT_OPEN_WIN_KIT_SERIAL.equals(scrnMsg.eventNm.getValue())) {
            // 4.Order Quantity
            params[INDEX_4] =  scrnMsg.fnshGoodsOrdQty;
            // 11.Inbound/Outbound Code
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(INDEX_11).xxPopPrm, INBD_OTBD.INBOUND);
        } else if (EVENT_OPEN_WIN_SUPPLY_SERIAL.equals(scrnMsg.eventNm.getValue())) {
            // 4.Order Quantity
            params[INDEX_4] = scrnMsg.A.no(eventRowIndex).origGoodsOrdQty_A1;
            // 11.Inbound/Outbound Code
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(INDEX_11).xxPopPrm, INBD_OTBD.OUTBOUND);
        }

        params[INDEX_0] = scrnMsg.P.no(INDEX_0).xxPopPrm.getValue();
        params[INDEX_1] = scrnMsg.P.no(INDEX_1).xxPopPrm;
        params[INDEX_2] = scrnMsg.P.no(INDEX_2).xxPopPrm;
        params[INDEX_3] = scrnMsg.P.no(INDEX_3).xxPopPrm;
        params[INDEX_5] = scrnMsg.P.no(INDEX_5).xxPopPrm.getValue();
        params[INDEX_6] = scrnMsg.P.no(INDEX_6).xxPopPrm;
        params[INDEX_7] = scrnMsg.P.no(INDEX_7).xxPopPrm;
        params[INDEX_8] = scrnMsg.L;
        params[INDEX_9] = scrnMsg.P.no(INDEX_9).xxPopPrm;
        params[INDEX_10] = scrnMsg.P.no(INDEX_10).xxPopPrm;
        params[INDEX_11] = scrnMsg.P.no(INDEX_11).xxPopPrm.getValue();

        return params;

    }

    /**
     * Check Serial#
     * @param scrnMsg NPAL1360BMsg
     */
    public static void checkSerialNumber(NPAL1360BMsg scrnMsg) {

        int serializeItemCnt = scrnMsg.fnshGoodsOrdQty.getValueInt();

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).rcvSerTakeFlg_D1.getValue())) {
                serializeItemCnt = serializeItemCnt + scrnMsg.A.no(i).origGoodsOrdQty_A1.getValueInt();
            }
        }

        if (serializeItemCnt > MAX_ROWS) {
            scrnMsg.fnshGoodsOrdQty.setErrorInfo(1, NPAM1521E);
        }
    }

    /**
     * The method explanation: set parameter to call popup.(ZYPL0310)
     * @param p NPAL1360_XBMsgArray
     * @param size int
     * @return Object[]
     */

    public static Object[] toArray_popupForZYPL0310(NPAL1360_XBMsgArray p, int size) {
        Object[] param = new Object[size];
        for (int i = 0; i < size; i++) {
            param[i] = p.no(i).xxPopPrm_AT.getValue();
        }
        return param;
    }

    // START 2017/11/07 S.Katsuma [SOL#014(QC#18401),ADD]
    /**
     * Set Supply Demand Screen param
     * @param scrnMsg NPAL1360BMsg
     * @param lineIndex int
     * @return Supply / Demand Screen Param(NPAL1130) Object[]
     */
    public static Object[] setParamForSupplyDemand(NPAL1360BMsg scrnMsg, int lineIndex) {

        scrnMsg.P.no(INDEX_0).xxPopPrm.clear();
        scrnMsg.P.no(INDEX_1).xxPopPrm.clear();
        scrnMsg.P.no(INDEX_2).xxPopPrm.clear();

        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(INDEX_0).xxPopPrm, scrnMsg.A.no(lineIndex).mdseCd_A1);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(INDEX_1).xxPopPrm, scrnMsg.rtlWhCd);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(INDEX_2).xxPopPrm, scrnMsg.A.no(lineIndex).splyRtlSwhCd_A1);

        int paramCount = 0;
        Object[] params = new Object[IDX_3];
        params[paramCount++] = scrnMsg.P.no(INDEX_0).xxPopPrm;
        params[paramCount++] = scrnMsg.P.no(INDEX_1).xxPopPrm;
        params[paramCount++] = scrnMsg.P.no(INDEX_2).xxPopPrm;

        return params;
    }
    // END 2017/11/07 S.Katsuma [SOL#014(QC#18401),ADD]
}
