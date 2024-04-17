/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1540.common;
import static business.servlet.NPAL1540.constant.NPAL1540Constant.BTN_OPENWIN_SER_ENTRY;
import static business.servlet.NPAL1540.constant.NPAL1540Constant.AFT_DECL_PNT_DIGIT_NUM_WT;
import static business.servlet.NPAL1540.constant.NPAL1540Constant.BIZ_APP_ID;
import static business.servlet.NPAL1540.constant.NPAL1540Constant.BTN_ADD_ALL_LINE;
import static business.servlet.NPAL1540.constant.NPAL1540Constant.BTN_ADD_LINE;
import static business.servlet.NPAL1540.constant.NPAL1540Constant.BTN_APPLY;
import static business.servlet.NPAL1540.constant.NPAL1540Constant.BTN_ATTACHMENTS;
import static business.servlet.NPAL1540.constant.NPAL1540Constant.BTN_CHECK_ALL;
import static business.servlet.NPAL1540.constant.NPAL1540Constant.BTN_CMN_BTN_1;
import static business.servlet.NPAL1540.constant.NPAL1540Constant.BTN_CMN_BTN_10;
import static business.servlet.NPAL1540.constant.NPAL1540Constant.BTN_CMN_BTN_11;
import static business.servlet.NPAL1540.constant.NPAL1540Constant.BTN_CMN_BTN_2;
import static business.servlet.NPAL1540.constant.NPAL1540Constant.BTN_CMN_BTN_3;
import static business.servlet.NPAL1540.constant.NPAL1540Constant.BTN_CMN_BTN_4;
import static business.servlet.NPAL1540.constant.NPAL1540Constant.BTN_CMN_BTN_5;
import static business.servlet.NPAL1540.constant.NPAL1540Constant.BTN_CMN_BTN_6;
import static business.servlet.NPAL1540.constant.NPAL1540Constant.BTN_CMN_BTN_7;
import static business.servlet.NPAL1540.constant.NPAL1540Constant.BTN_CMN_BTN_8;
import static business.servlet.NPAL1540.constant.NPAL1540Constant.BTN_CMN_BTN_9;
import static business.servlet.NPAL1540.constant.NPAL1540Constant.BTN_DELETE_ROW;
import static business.servlet.NPAL1540.constant.NPAL1540Constant.BTN_ERROR_CORRECTION;
import static business.servlet.NPAL1540.constant.NPAL1540Constant.BTN_PO_ENTRY;
import static business.servlet.NPAL1540.constant.NPAL1540Constant.BTN_SEARCH;
import static business.servlet.NPAL1540.constant.NPAL1540Constant.BTN_UN_CHECK_ALL;
import static business.servlet.NPAL1540.constant.NPAL1540Constant.COLUMN_DISP_NM_FOR_CARRIER_CODE;
import static business.servlet.NPAL1540.constant.NPAL1540Constant.COLUMN_DISP_NM_FOR_CARRIER_NAME;
import static business.servlet.NPAL1540.constant.NPAL1540Constant.COLUMN_SQL_NM_FOR_CARRIER_CODE;
import static business.servlet.NPAL1540.constant.NPAL1540Constant.COLUMN_SQL_NM_FOR_CARRIER_NAME;
import static business.servlet.NPAL1540.constant.NPAL1540Constant.COLUMN_WIDTH_FOR_CARRIER_CODE;
import static business.servlet.NPAL1540.constant.NPAL1540Constant.COLUMN_WIDTH_FOR_CARRIER_NAME;
import static business.servlet.NPAL1540.constant.NPAL1540Constant.DISPLAY_NM_ASN_BOL_NUM;
import static business.servlet.NPAL1540.constant.NPAL1540Constant.DISPLAY_NM_ASN_MDSE_CD;
import static business.servlet.NPAL1540.constant.NPAL1540Constant.DISPLAY_NM_ASN_PLN_DELY_DT;
import static business.servlet.NPAL1540.constant.NPAL1540Constant.DISPLAY_NM_ASN_PRO_NUM;
import static business.servlet.NPAL1540.constant.NPAL1540Constant.DISPLAY_NM_ASN_QTY;
import static business.servlet.NPAL1540.constant.NPAL1540Constant.DISPLAY_NM_ASN_SO_NUM;
import static business.servlet.NPAL1540.constant.NPAL1540Constant.DISPLAY_NM_ASN_TOT_FRT_AMT;
import static business.servlet.NPAL1540.constant.NPAL1540Constant.DISPLAY_NM_ASN_TOT_SHIP_WT;
import static business.servlet.NPAL1540.constant.NPAL1540Constant.DISPLAY_NM_ASN_TTL_WT;
import static business.servlet.NPAL1540.constant.NPAL1540Constant.DISPLAY_NM_CARR_CD;
import static business.servlet.NPAL1540.constant.NPAL1540Constant.DISPLAY_NM_DISP_PO_DTL_LINE_NUM;
import static business.servlet.NPAL1540.constant.NPAL1540Constant.DISPLAY_NM_PO_ORD_NUM;
import static business.servlet.NPAL1540.constant.NPAL1540Constant.DISPLAY_NM_SHIP_DT;
import static business.servlet.NPAL1540.constant.NPAL1540Constant.DISPLAY_NM_SHIP_FROM_SO_NUM;
import static business.servlet.NPAL1540.constant.NPAL1540Constant.DISPLAY_NM_VND_INVTY_LOC_CD;
import static business.servlet.NPAL1540.constant.NPAL1540Constant.FUNC_EDIT;
import static business.servlet.NPAL1540.constant.NPAL1540Constant.SCRN_ID;
import static business.servlet.NPAL1540.constant.NPAL1540Constant.SELECT_TABLE_NAME_CARRIER_SEARCH;
import static business.servlet.NPAL1540.constant.NPAL1540Constant.SORT_COLUMN_NAME_FOR_CARRIER_CODE;
import static business.servlet.NPAL1540.constant.NPAL1540Constant.SORT_CONDITION_FOR_CARRIER_CODE;
import static business.servlet.NPAL1540.constant.NPAL1540Constant.WHERE_DISP_NM_FOR_CARRIER_CODE;
import static business.servlet.NPAL1540.constant.NPAL1540Constant.WHERE_DISP_NM_FOR_CARRIER_NAME;
import static business.servlet.NPAL1540.constant.NPAL1540Constant.WHERE_SQL_NM_FOR_CARRIER_CODE;
import static business.servlet.NPAL1540.constant.NPAL1540Constant.WHERE_SQL_NM_FOR_CARRIER_NAME;
import static business.servlet.NPAL1540.constant.NPAL1540Constant.WINDOW_TITLE_CARRIER_SEARCH;

import java.util.ArrayList;
import java.util.List;

import parts.servletcommon.EZDCommonHandler;
import business.servlet.NPAL1540.NPAL1540BMsg;
import business.servlet.NPAL1540.NPAL1540_XBMsgArray;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * Business ID :  NPAL1540 Manual ASN Entry
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/19/2016   CITS         Makoto Okigami     Create          N/A
 * 03/06/2018   CITS         T.Wada             Update          QC#20445-2
 *</pre>
 */
public class NPAL1540CommonLogic {

    /**
     * The method explanation: The display control of the screen item
     * for Initialized screen
     * @param handler EZDCommonHandler
     * @param scrnMsg NPAL1540BMsg
     */
    public static void ctrlScrnItemDispInit(EZDCommonHandler handler, NPAL1540BMsg scrnMsg) {

        // Header
        scrnMsg.asnSoNum.setInputProtected(false);
        scrnMsg.xxScrItem54Txt.setInputProtected(true);
        scrnMsg.xxScrItem7Txt.setInputProtected(true);
        scrnMsg.poOrdNum.setInputProtected(false);
        scrnMsg.vndCd.setInputProtected(true);
        scrnMsg.dplyVndNm.setInputProtected(true);
        scrnMsg.shipFromSoNum.setInputProtected(false);
        scrnMsg.xxLinkAncr.setInputProtected(false);
        scrnMsg.carrCd.setInputProtected(false);
        scrnMsg.asnBolNum.setInputProtected(false);
        scrnMsg.asnProNum.setInputProtected(false);
        scrnMsg.shipDt.setInputProtected(false);
        scrnMsg.asnPlnDelyDt.setInputProtected(false);
        scrnMsg.asnTotFrtAmt.setInputProtected(true);
        scrnMsg.asnTotShipWt.setInputProtected(false);
        scrnMsg.vndInvtyLocCd.setInputProtected(false);

        handler.setButtonEnabled(BTN_SEARCH, true);
        handler.setButtonEnabled(BTN_ERROR_CORRECTION, true);
        handler.setButtonEnabled(BTN_APPLY, true);
        handler.setButtonEnabled(BTN_PO_ENTRY, false);

        scrnMsg.asnTotFrtAmt.setAppFracDigit(scrnMsg.xxNum_C1.getValueInt());
        scrnMsg.asnTotShipWt.setAppFracDigit(AFT_DECL_PNT_DIGIT_NUM_WT);

        // Detail Header
        scrnMsg.dispPoDtlLineNum.setInputProtected(true);

        handler.setButtonEnabled(BTN_ADD_ALL_LINE, false);
        handler.setButtonEnabled(BTN_ADD_LINE, false);

        // Detail
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).asnTotFrtAmt_A1.setAppFracDigit(scrnMsg.xxNum_C1.getValueInt());
            scrnMsg.A.no(i).asnTotShipWt_A1.setAppFracDigit(AFT_DECL_PNT_DIGIT_NUM_WT);
        }

        // Detail Footer
        handler.setButtonEnabled(BTN_CHECK_ALL, false);
        handler.setButtonEnabled(BTN_UN_CHECK_ALL, false);
        handler.setButtonEnabled(BTN_DELETE_ROW, false);
        handler.setButtonEnabled(BTN_ATTACHMENTS, false);

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
        handler.setButtonProperties(BTN_CMN_BTN_11[0], BTN_CMN_BTN_11[1], BTN_CMN_BTN_11[2], 0, null);

        setAuthorityProtect(handler, scrnMsg);
        setTableScreen(scrnMsg);
    }

    /**
     * Set Screen Item Attribute
     * @param handler EZDCommonHandler
     * @param scrnMsg NPAL1540BMsg
     */
    public static void setScrnItemAttr(EZDCommonHandler handler, NPAL1540BMsg scrnMsg) {

        if (ZYPCommonFunc.hasValue(scrnMsg.asnSoNum_HD)) {
            // Header
            scrnMsg.poOrdNum.setInputProtected(true);
            scrnMsg.shipFromSoNum.setInputProtected(true);
            scrnMsg.xxLinkAncr.setInputProtected(true);
            scrnMsg.carrCd.setInputProtected(true);
            scrnMsg.asnBolNum.setInputProtected(true);
            scrnMsg.asnProNum.setInputProtected(true);
            scrnMsg.shipDt.setInputProtected(true);
            scrnMsg.asnPlnDelyDt.setInputProtected(true);
            scrnMsg.asnTotShipWt.setInputProtected(true);
            scrnMsg.vndInvtyLocCd.setInputProtected(true);

            handler.setButtonEnabled(BTN_SEARCH, true);
            handler.setButtonEnabled(BTN_APPLY, false);
            handler.setButtonEnabled(BTN_PO_ENTRY, true);

            // Detail Header
            scrnMsg.dispPoDtlLineNum.setInputProtected(true);

            handler.setButtonEnabled(BTN_ADD_ALL_LINE, false);
            handler.setButtonEnabled(BTN_ADD_LINE, false);

            // QC#20445-2
            setBtnOpenwinSerEntryEnabled(handler, scrnMsg);

            // Detail Footer
            handler.setButtonEnabled(BTN_CHECK_ALL, false);
            handler.setButtonEnabled(BTN_UN_CHECK_ALL, false);
            handler.setButtonEnabled(BTN_DELETE_ROW, false);
            handler.setButtonEnabled(BTN_ATTACHMENTS, true);

            // common button protection
            // 0 : inactive
            // 1 : active
            handler.setButtonProperties(BTN_CMN_BTN_2[0], BTN_CMN_BTN_2[1], BTN_CMN_BTN_2[2], 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_11[0], BTN_CMN_BTN_11[1], BTN_CMN_BTN_11[2], 0, null);
        } else if (!ZYPCommonFunc.hasValue(scrnMsg.poOrdNum_HD)) {
            // Header
            scrnMsg.poOrdNum.setInputProtected(false);
            scrnMsg.shipFromSoNum.setInputProtected(false);
            scrnMsg.xxLinkAncr.setInputProtected(false);
            scrnMsg.carrCd.setInputProtected(false);
            scrnMsg.asnBolNum.setInputProtected(false);
            scrnMsg.asnProNum.setInputProtected(false);
            scrnMsg.shipDt.setInputProtected(false);
            scrnMsg.asnPlnDelyDt.setInputProtected(false);
            scrnMsg.asnTotShipWt.setInputProtected(false);
            scrnMsg.vndInvtyLocCd.setInputProtected(false);

            handler.setButtonEnabled(BTN_SEARCH, true);
            handler.setButtonEnabled(BTN_APPLY, true);
            handler.setButtonEnabled(BTN_PO_ENTRY, false);

            // Detail Header
            scrnMsg.dispPoDtlLineNum.setInputProtected(true);

            handler.setButtonEnabled(BTN_ADD_ALL_LINE, false);
            handler.setButtonEnabled(BTN_ADD_LINE, false);

            // Detail Footer
            handler.setButtonEnabled(BTN_CHECK_ALL, false);
            handler.setButtonEnabled(BTN_UN_CHECK_ALL, false);
            handler.setButtonEnabled(BTN_DELETE_ROW, false);
            handler.setButtonEnabled(BTN_ATTACHMENTS, false);

            // common button protection
            // 0 : inactive
            // 1 : active
            handler.setButtonProperties(BTN_CMN_BTN_2[0], BTN_CMN_BTN_2[1], BTN_CMN_BTN_2[2], 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_11[0], BTN_CMN_BTN_11[1], BTN_CMN_BTN_11[2], 0, null);
        } else if (scrnMsg.A.getValidCount() > 0) {
            // Header
            scrnMsg.poOrdNum.setInputProtected(true);
            scrnMsg.shipFromSoNum.setInputProtected(false);
            scrnMsg.xxLinkAncr.setInputProtected(false);
            scrnMsg.carrCd.setInputProtected(false);
            scrnMsg.asnBolNum.setInputProtected(false);
            scrnMsg.asnProNum.setInputProtected(false);
            scrnMsg.shipDt.setInputProtected(false);
            scrnMsg.asnPlnDelyDt.setInputProtected(false);
            scrnMsg.asnTotShipWt.setInputProtected(false);
            scrnMsg.vndInvtyLocCd.setInputProtected(false);

            handler.setButtonEnabled(BTN_SEARCH, false);
            handler.setButtonEnabled(BTN_APPLY, false);
            handler.setButtonEnabled(BTN_PO_ENTRY, true);

            // Detail Header
            scrnMsg.dispPoDtlLineNum.setInputProtected(false);

            handler.setButtonEnabled(BTN_ADD_ALL_LINE, false);
            handler.setButtonEnabled(BTN_ADD_LINE, true);

            // QC#20445-2
            setBtnOpenwinSerEntryEnabled(handler, scrnMsg);

            // Detail Footer
            handler.setButtonEnabled(BTN_CHECK_ALL, true);
            handler.setButtonEnabled(BTN_UN_CHECK_ALL, true);
            handler.setButtonEnabled(BTN_DELETE_ROW, true);
            handler.setButtonEnabled(BTN_ATTACHMENTS, false);

            // common button protection
            // 0 : inactive
            // 1 : active
            handler.setButtonProperties(BTN_CMN_BTN_2[0], BTN_CMN_BTN_2[1], BTN_CMN_BTN_2[2], 1, null);
            handler.setButtonProperties(BTN_CMN_BTN_11[0], BTN_CMN_BTN_11[1], BTN_CMN_BTN_11[2], 1, null);
        } else {
            // Header
            scrnMsg.poOrdNum.setInputProtected(false);
            scrnMsg.shipFromSoNum.setInputProtected(false);
            scrnMsg.xxLinkAncr.setInputProtected(false);
            scrnMsg.carrCd.setInputProtected(false);
            scrnMsg.asnBolNum.setInputProtected(false);
            scrnMsg.asnProNum.setInputProtected(false);
            scrnMsg.shipDt.setInputProtected(false);
            scrnMsg.asnPlnDelyDt.setInputProtected(false);
            scrnMsg.asnTotShipWt.setInputProtected(false);
            scrnMsg.vndInvtyLocCd.setInputProtected(false);

            handler.setButtonEnabled(BTN_SEARCH, false);
            handler.setButtonEnabled(BTN_APPLY, true);
            handler.setButtonEnabled(BTN_PO_ENTRY, true);

            // Detail Header
            scrnMsg.dispPoDtlLineNum.setInputProtected(false);

            handler.setButtonEnabled(BTN_ADD_ALL_LINE, true);
            handler.setButtonEnabled(BTN_ADD_LINE, true);

            // Detail Footer
            handler.setButtonEnabled(BTN_CHECK_ALL, false);
            handler.setButtonEnabled(BTN_UN_CHECK_ALL, false);
            handler.setButtonEnabled(BTN_DELETE_ROW, false);
            handler.setButtonEnabled(BTN_ATTACHMENTS, false);

            // common button protection
            // 0 : inactive
            // 1 : active
            handler.setButtonProperties(BTN_CMN_BTN_2[0], BTN_CMN_BTN_2[1], BTN_CMN_BTN_2[2], 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_11[0], BTN_CMN_BTN_11[1], BTN_CMN_BTN_11[2], 1, null);
        }

        setAuthorityProtect(handler, scrnMsg);
    }
    /**
     * setBtnOpenwinSerEntryEnabled
     * @param handler
     * @param scrnMsg
     */
    private static void setBtnOpenwinSerEntryEnabled(EZDCommonHandler handler, NPAL1540BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).rcvSerTakeFlg_A1) && ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).rcvSerTakeFlg_A1.getValue())) {
                handler.setButtonEnabled(BTN_OPENWIN_SER_ENTRY, i, true);
            } else {
                handler.setButtonEnabled(BTN_OPENWIN_SER_ENTRY, i, false);
            }
        }
    }

    /**
     * Sets the authority protect.
     * @param handler EZDCommonHandler
     * @param scrnMsg NPAL1540BMsg
     */
    private static void setAuthorityProtect(EZDCommonHandler handler, NPAL1540BMsg scrnMsg) {

        if (!isUpdatable()) {
            handler.setButtonEnabled(BTN_APPLY, false);
            handler.setButtonEnabled(BTN_ADD_ALL_LINE, false);
            handler.setButtonEnabled(BTN_ADD_LINE, false);
            handler.setButtonEnabled(BTN_CHECK_ALL, false);
            handler.setButtonEnabled(BTN_UN_CHECK_ALL, false);
            handler.setButtonEnabled(BTN_DELETE_ROW, false);
            handler.setButtonEnabled(BTN_ATTACHMENTS, false);

            handler.setButtonProperties(BTN_CMN_BTN_2[0], BTN_CMN_BTN_2[1], BTN_CMN_BTN_2[2], 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_8[0], BTN_CMN_BTN_8[1], BTN_CMN_BTN_8[2], 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_11[0], BTN_CMN_BTN_11[1], BTN_CMN_BTN_11[2], 0, null);
        }
    }

    /**
     * <p>
     * Sets the name for the error message.
     * </p>
     * @param scrnMsg NPAL1540BMsg
     */
    public static void setNameForMessage(NPAL1540BMsg scrnMsg) {

        // Header
        scrnMsg.asnSoNum.setNameForMessage(DISPLAY_NM_ASN_SO_NUM);
        scrnMsg.poOrdNum.setNameForMessage(DISPLAY_NM_PO_ORD_NUM);
        scrnMsg.shipFromSoNum.setNameForMessage(DISPLAY_NM_SHIP_FROM_SO_NUM);
        scrnMsg.carrCd.setNameForMessage(DISPLAY_NM_CARR_CD);
        scrnMsg.asnBolNum.setNameForMessage(DISPLAY_NM_ASN_BOL_NUM);
        scrnMsg.asnProNum.setNameForMessage(DISPLAY_NM_ASN_PRO_NUM);
        scrnMsg.shipDt.setNameForMessage(DISPLAY_NM_SHIP_DT);
        scrnMsg.asnPlnDelyDt.setNameForMessage(DISPLAY_NM_ASN_PLN_DELY_DT);
        scrnMsg.asnTotShipWt.setNameForMessage(DISPLAY_NM_ASN_TOT_SHIP_WT);
        scrnMsg.vndInvtyLocCd.setNameForMessage(DISPLAY_NM_VND_INVTY_LOC_CD);
        scrnMsg.dispPoDtlLineNum.setNameForMessage(DISPLAY_NM_DISP_PO_DTL_LINE_NUM);

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).asnMdseCd_A1.setNameForMessage(DISPLAY_NM_ASN_MDSE_CD);
            scrnMsg.A.no(i).asnQty_A1.setNameForMessage(DISPLAY_NM_ASN_QTY);
            scrnMsg.A.no(i).asnTotShipWt_A1.setNameForMessage(DISPLAY_NM_ASN_TTL_WT);
            scrnMsg.A.no(i).asnTotFrtAmt_A1.setNameForMessage(DISPLAY_NM_ASN_TOT_FRT_AMT);
        }
    }

    /**
     * Table Column Protect
     * @param scrnMsg NPAL1540BMsg
     */
    public static void setTableScreen(NPAL1540BMsg scrnMsg) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID, scrnMsg);
        tblColor.setAlternateRowsBG("A", scrnMsg.A);

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(scrnMsg.asnSoNum_HD)) {
                scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(true);
                scrnMsg.A.no(i).ediPoOrdNum_A1.setInputProtected(true);
                scrnMsg.A.no(i).asnMdseCd_A1.setInputProtected(true);
                scrnMsg.A.no(i).mdseCd_A1.setInputProtected(true);
                scrnMsg.A.no(i).mdseDescShortTxt_A1.setInputProtected(true);
                scrnMsg.A.no(i).asnQty_A1.setInputProtected(true);
                scrnMsg.A.no(i).asnTotShipWt_A1.setInputProtected(true);
                scrnMsg.A.no(i).asnTotFrtAmt_A1.setInputProtected(true);
                scrnMsg.A.no(i).xxScrItem8Txt_A1.setInputProtected(true);
                scrnMsg.A.no(i).xxScrItem19Txt_A1.setInputProtected(true);
            } else {
                scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(false);
                scrnMsg.A.no(i).ediPoOrdNum_A1.setInputProtected(true);
                scrnMsg.A.no(i).asnMdseCd_A1.setInputProtected(false);
                scrnMsg.A.no(i).mdseCd_A1.setInputProtected(true);
                scrnMsg.A.no(i).mdseDescShortTxt_A1.setInputProtected(true);
                scrnMsg.A.no(i).asnQty_A1.setInputProtected(false);
                scrnMsg.A.no(i).asnTotShipWt_A1.setInputProtected(false);
                scrnMsg.A.no(i).asnTotFrtAmt_A1.setInputProtected(false);
                scrnMsg.A.no(i).xxScrItem8Txt_A1.setInputProtected(true);
                scrnMsg.A.no(i).xxScrItem19Txt_A1.setInputProtected(true);
            }
        }
    }

    /**
     * Check error info
     * @param scrnMsg NPAL1540BMsg
     */
    public static void chkErrorInfo(NPAL1540BMsg scrnMsg) {

        scrnMsg.addCheckItem(scrnMsg.asnSoNum);
        scrnMsg.addCheckItem(scrnMsg.poOrdNum);
        scrnMsg.addCheckItem(scrnMsg.shipFromSoNum);
        scrnMsg.addCheckItem(scrnMsg.carrCd);
        scrnMsg.addCheckItem(scrnMsg.asnBolNum);
        scrnMsg.addCheckItem(scrnMsg.asnProNum);
        scrnMsg.addCheckItem(scrnMsg.shipDt);
        scrnMsg.addCheckItem(scrnMsg.asnPlnDelyDt);
        scrnMsg.addCheckItem(scrnMsg.asnTotShipWt);
        scrnMsg.addCheckItem(scrnMsg.vndInvtyLocCd);

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).asnMdseCd_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).asnQty_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).asnTotShipWt_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).asnTotFrtAmt_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxScrItem8Txt_A1);
        }
    }

    /**
     * Function check
     * @return true:edit false:reference
     */
    public static boolean isUpdatable() {
        List<String> functionList = getFunctionList();
        return functionList.contains(FUNC_EDIT);
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

    /**
     * Set Carrier Search Param
     * @param scrnMsg NPAL1540BMsg
     * @param glblCmpyCd String
     * @return Carrier Search Parameter
     */
    public static Object[] setCarrierSearchParam(NPAL1540BMsg scrnMsg, String glblCmpyCd) {

        // Parameter Clear
        ZYPTableUtil.clear(scrnMsg.R);

        // Paramter Set
        Object[] param = new Object[7];
        param[0] = "";
        param[1] = WINDOW_TITLE_CARRIER_SEARCH;
        param[2] = SELECT_TABLE_NAME_CARRIER_SEARCH;
        param[3] = getSearchConditionForCarrier(scrnMsg);
        param[4] = getDisplayColumnForCarrier(scrnMsg);
        param[5] = getSortForCarrier(scrnMsg);
        param[6] = scrnMsg.R;

        return param;

    }

    /**
     * getSearchConditionForCarrier
     * @param scrnMsg NPAL1540BMsg
     * @return Condition SQL
     */
    private static List<Object> getSearchConditionForCarrier(NPAL1540BMsg scrnMsg) {
        List<Object> whereList = new ArrayList<Object>();

        Object[] whereObj1 = {WHERE_DISP_NM_FOR_CARRIER_CODE, WHERE_SQL_NM_FOR_CARRIER_CODE, scrnMsg.carrCd.getValue(), ZYPConstant.FLG_ON_Y};
        Object[] whereObj2 = {WHERE_DISP_NM_FOR_CARRIER_NAME, WHERE_SQL_NM_FOR_CARRIER_NAME, null, ZYPConstant.FLG_ON_Y};

        whereList.add(whereObj1);
        whereList.add(whereObj2);

        return whereList;
    }

    /**
     * getDisplayColumnForCarrier
     * @param scrnMsg NPAL1540BMsg
     * @return Column SQL
     */
    private static List<Object> getDisplayColumnForCarrier(NPAL1540BMsg scrnMsg) {
        List<Object> colList = new ArrayList<Object>();

        Object[] colObj1 = {COLUMN_DISP_NM_FOR_CARRIER_CODE, COLUMN_SQL_NM_FOR_CARRIER_CODE, COLUMN_WIDTH_FOR_CARRIER_CODE, ZYPConstant.FLG_ON_Y};
        Object[] colObj2 = {COLUMN_DISP_NM_FOR_CARRIER_NAME, COLUMN_SQL_NM_FOR_CARRIER_NAME, COLUMN_WIDTH_FOR_CARRIER_NAME, ZYPConstant.FLG_OFF_N};

        colList.add(colObj1);
        colList.add(colObj2);

        return colList;
    }

    /**
     * getSortForCarrier
     * @param scrnMsg NPAL1540BMsg
     * @return Sort SQL
     */
    private static List<Object> getSortForCarrier(NPAL1540BMsg scrnMsg) {
        List<Object> sortList = new ArrayList<Object>();

        Object[] sortObj1 = {SORT_COLUMN_NAME_FOR_CARRIER_CODE, SORT_CONDITION_FOR_CARRIER_CODE};

        sortList.add(sortObj1);

        return sortList;
    }

    /**
     * The method explanation: set parameter to call popup.(ZYPL0310)
     * @param p NPAL1540_XBMsgArray
     * @param size int
     * @return Object[]
     */

    public static Object[] toArray_popupForZYPL0310(NPAL1540_XBMsgArray p, int size) {
        Object[] param = new Object[size];
        for (int i = 0; i < size; i++) {
            param[i] = p.no(i).xxPopPrm_AT.getValue();
        }
        return param;
    }

}
