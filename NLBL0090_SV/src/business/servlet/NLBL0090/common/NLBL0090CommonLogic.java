/**
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL0090.common;

import static business.servlet.NLBL0090.constant.NLBL0090Constant.BIZ_APP_ID;
import static business.servlet.NLBL0090.constant.NLBL0090Constant.BOL_CRRER_BUTTON;
import static business.servlet.NLBL0090.constant.NLBL0090Constant.BOL_HSTRY_BUTTON;
import static business.servlet.NLBL0090.constant.NLBL0090Constant.BOL_SEARCH;
import static business.servlet.NLBL0090.constant.NLBL0090Constant.BOL_SELLTO_BUTTON;
import static business.servlet.NLBL0090.constant.NLBL0090Constant.BOL_SHIPTO_BUTTON;
import static business.servlet.NLBL0090.constant.NLBL0090Constant.BTN_CMN_APPLY;
import static business.servlet.NLBL0090.constant.NLBL0090Constant.BTN_CMN_APPROVE;
import static business.servlet.NLBL0090.constant.NLBL0090Constant.BTN_CMN_CLEAR;
import static business.servlet.NLBL0090.constant.NLBL0090Constant.BTN_CMN_DELETE;
import static business.servlet.NLBL0090.constant.NLBL0090Constant.BTN_CMN_DOWNLOAD;
import static business.servlet.NLBL0090.constant.NLBL0090Constant.BTN_CMN_REJECT;
import static business.servlet.NLBL0090.constant.NLBL0090Constant.BTN_CMN_RESET;
import static business.servlet.NLBL0090.constant.NLBL0090Constant.BTN_CMN_RETURN;
import static business.servlet.NLBL0090.constant.NLBL0090Constant.BTN_CMN_SAVE;
import static business.servlet.NLBL0090.constant.NLBL0090Constant.BTN_CMN_SUBMIT;
import static business.servlet.NLBL0090.constant.NLBL0090Constant.CLOSE;
import static business.servlet.NLBL0090.constant.NLBL0090Constant.FUNCTION_UPDATE;
import static business.servlet.NLBL0090.constant.NLBL0090Constant.IDX_0;
import static business.servlet.NLBL0090.constant.NLBL0090Constant.IDX_1;
import static business.servlet.NLBL0090.constant.NLBL0090Constant.IDX_10;
import static business.servlet.NLBL0090.constant.NLBL0090Constant.IDX_11;
import static business.servlet.NLBL0090.constant.NLBL0090Constant.IDX_12;
import static business.servlet.NLBL0090.constant.NLBL0090Constant.IDX_13;
import static business.servlet.NLBL0090.constant.NLBL0090Constant.IDX_14;
import static business.servlet.NLBL0090.constant.NLBL0090Constant.IDX_15;
import static business.servlet.NLBL0090.constant.NLBL0090Constant.IDX_16;
import static business.servlet.NLBL0090.constant.NLBL0090Constant.IDX_17;
import static business.servlet.NLBL0090.constant.NLBL0090Constant.IDX_2;
import static business.servlet.NLBL0090.constant.NLBL0090Constant.IDX_3;
import static business.servlet.NLBL0090.constant.NLBL0090Constant.IDX_4;
import static business.servlet.NLBL0090.constant.NLBL0090Constant.IDX_5;
import static business.servlet.NLBL0090.constant.NLBL0090Constant.IDX_6;
import static business.servlet.NLBL0090.constant.NLBL0090Constant.IDX_7;
import static business.servlet.NLBL0090.constant.NLBL0090Constant.IDX_8;
import static business.servlet.NLBL0090.constant.NLBL0090Constant.IDX_9;
import static business.servlet.NLBL0090.constant.NLBL0090Constant.PAGENATION_NEXT;
import static business.servlet.NLBL0090.constant.NLBL0090Constant.PAGENATION_TBL_NM;
import static business.servlet.NLBL0090.constant.NLBL0090Constant.POP_PARAM_ACCTS_ONLY;
import static business.servlet.NLBL0090.constant.NLBL0090Constant.POP_PARAM_ACT_IACT;
import static business.servlet.NLBL0090.constant.NLBL0090Constant.POP_PARAM_ALL;
import static business.servlet.NLBL0090.constant.NLBL0090Constant.POP_PARAM_SHIPS_ONLY;
import static business.servlet.NLBL0090.constant.NLBL0090Constant.RADIO_BUTTON_NAME_VALUE;
import static business.servlet.NLBL0090.constant.NLBL0090Constant.SCRN_ID;
import static business.servlet.NLBL0090.constant.NLBL0090Constant.STYLE_NAME_FOR_EVEN_NUM_BG;
import static business.servlet.NLBL0090.constant.NLBL0090Constant.TBL_ID_B;
import static business.servlet.NLBL0090.constant.NLBL0090Constant.TBL_ID_LEFT_A;
import static business.servlet.NLBL0090.constant.NLBL0090Constant.TBL_ID_RIGHT_A;
import static business.servlet.NLBL0090.constant.NLBL0090Constant.TB_BOL_DTL;
import static business.servlet.NLBL0090.constant.NLBL0090Constant.TB_BOL_TRCKNG;

import java.util.List;

import parts.common.EZDGUIAttribute;
import parts.servletcommon.EZDApplicationContext;
import parts.servletcommon.EZDCommonHandler;
import business.blap.NLBL0090.NLBL0090CMsg;
import business.servlet.NLBL0090.NLBL0090BMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;
import com.fujitsu.uji.http.HttpDispatchContext;
import com.fujitsu.uji.util.Parameters;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/07/22   Fujitsu         Mori            Create          N/A
 * 2016/04/07   CITS            Y.Nomura        Update          for CSA
 * 2016/09/29   CITS            S.Tanikawa      Update          QC#2434
 * 2017/01/16   CITS            T.Kikuhara      Update          QC#16256
 *</pre>
 */
public class NLBL0090CommonLogic {

    // ************* Pagenation Process *************
    /**
     * Setup PagenationTable name to bMessage.
     * @param bMessage NLBL0090BMsg
     * @param ctx EZDApplicationContext
     */
    public static void setPagenationTableNm(NLBL0090BMsg bMessage, EZDApplicationContext ctx) {

        HttpDispatchContext httpCtx = (HttpDispatchContext) ctx.getDispatchContext();
        Parameters params = httpCtx.getParameters();
        String xxPagenationTableNm = params.getSingleValue(PAGENATION_TBL_NM);
        bMessage.xxTblNm_Z1.setValue(xxPagenationTableNm);
    }

    /**
     * Crear PagenationTable name From bMessage, when that is 'A'.
     * @param bMessage NLBL0090BMsg
     * @param pagenation String
     */
    public static void clearPagenationTableA(NLBL0090BMsg bMessage, String pagenation) {

        ZYPTableUtil.clear(bMessage.A);
        if (PAGENATION_NEXT.equals(pagenation)) {
            bMessage.xxPageShowFromNum_A1.setValue(bMessage.xxPageShowToNum_A1.getValueInt());
        } else {
            bMessage.xxPageShowFromNum_A1.setValue(bMessage.xxPageShowFromNum_A1.getValueInt() - bMessage.A.length() - 1);
        }
        bMessage.xxPageShowToNum_A1.clear();
    }

    /**
     * Crear PagenationTable name From bMessage, when that is 'B'.
     * @param bMessage NLBL0090BMsg
     * @param pagenation String
     */
    public static void clearPagenationTableB(NLBL0090BMsg bMessage, String pagenation) {

        ZYPTableUtil.clear(bMessage.B);
        if (PAGENATION_NEXT.equals(pagenation)) {
            bMessage.xxPageShowFromNum_B1.setValue(bMessage.xxPageShowToNum_B1.getValueInt());
        } else {
            bMessage.xxPageShowFromNum_B1.setValue(bMessage.xxPageShowFromNum_B1.getValueInt() - bMessage.B.length() - 1);
        }
        bMessage.xxPageShowToNum_B1.clear();
    }

    // ************* Setup Screen Controles *************

    /**
     * Crear Tracking Table Info ( Table:'A' ).
     * @param bMessage NLBL0090BMsg
     */
    public static void clearTrckngTableInfo(NLBL0090BMsg bMessage) {
        bMessage.clearAllGUIAttribute(SCRN_ID);

        ZYPTableUtil.clear(bMessage.A);
        clearPageInfoA(bMessage);
    }

    /**
     * Crear Tracking Table Info ( 'B' table ).
     * @param bMessage NLBL0090BMsg
     */
    public static void clearDetailTableInfo(NLBL0090BMsg bMessage) {
        bMessage.clearAllGUIAttribute(SCRN_ID);

        ZYPTableUtil.clear(bMessage.B);
        clearPageInfoB(bMessage);
    }

    /**
     * Initialize Page Info of 'A' table.
     * @param bMessage NLBL0090BMsg
     */
    private static void clearPageInfoA(NLBL0090BMsg bMessage) {

        bMessage.xxPageShowFromNum_A1.clear();
        bMessage.xxPageShowToNum_A1.clear();
        bMessage.xxPageShowOfNum_A1.clear();
    }

    /**
     * Initialize Page Info of 'B' table.
     * @param bMessage NLBL0090BMsg
     */
    private static void clearPageInfoB(NLBL0090BMsg bMessage) {

        bMessage.xxPageShowFromNum_B1.clear();
        bMessage.xxPageShowToNum_B1.clear();
        bMessage.xxPageShowOfNum_B1.clear();
    }

    /**
     * Clear value of screen item.
     * @param bMessage NLBL0090BMsg
     */
    public static void clearViewItems(NLBL0090BMsg bMessage) {

        bMessage.bolNum_H1.clear();
        bMessage.proNum_H1.clear();
        bMessage.carrCd_H2.clear();
        bMessage.carrNm_H1.clear();
        bMessage.carrCd_P1.clear();
        bMessage.whCd_H1.clear();
        bMessage.sellToCustCd_H2.clear();
        bMessage.dsAcctNm_H2.clear();
        bMessage.shipToCustCd_H2.clear();
        bMessage.dsAcctNm_H3.clear();
        bMessage.podStsTpForScrCd_H2.clear();
        bMessage.podStsCd_H2.clear();
        bMessage.trxHdrNum_H1.clear();
        bMessage.soNum_H1.clear();
        bMessage.podStsDt_H1.clear();
        bMessage.podStsDt_H2.clear();
    }

    // ************* Control GUI Control's activation *************
    /**
     * Initilaize GUI Controls for BOL Tracking Tab.
     * @param bMessage bMessage
     * @param handler EZDCommonHandler
     */
    public static void initGuiAttrForBOLTrckngTab(NLBL0090BMsg bMessage, EZDCommonHandler handler) {

        // Button of footer
        protectFooterButton(handler);
        handler.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);

        // Input Item of header
        setInputProtectionOfHeaderAttr(bMessage, false);

        activateControlPldStsCd(bMessage);

        // Button of header
        setActiveCntrlOfButtons(handler, true);
    }

    /**
     * Initilaize GUI Controls for BOL Detail Tab.
     * @param bMessage bMessage
     * @param handler EZDCommonHandler
     */
    public static void initGuiAttrForDetailTab(NLBL0090BMsg bMessage, EZDCommonHandler handler) {
        // Button of footer
        protectFooterButton(handler);
        handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);

        // Input Item of header
        setInputProtectionOfHeaderAttr(bMessage, true);

        setInputProtectionOfTableAttr(bMessage, true);   // UPDATE 2016/09/29 QC#2434

        bMessage.podStsCd_H2.setInputProtected(true);

        // Button of header
        setActiveCntrlOfButtons(handler, false);
    }

    /**
     * Cotrol Input Item's activation.
     * @param bMessage NLBL0090BMsg
     * @param protectFlg true/inactivate item, false/activate item
     */
    private static void setInputProtectionOfHeaderAttr(NLBL0090BMsg bMessage, boolean protectFlg) {

        bMessage.bolNum_H1.setInputProtected(protectFlg);
        bMessage.proNum_H1.setInputProtected(protectFlg);
        bMessage.carrCd_H1.setInputProtected(protectFlg);
        bMessage.carrCd_H2.setInputProtected(protectFlg);
        bMessage.whCd_H1.setInputProtected(protectFlg);
        bMessage.sellToCustCd_H1.setInputProtected(protectFlg);
        bMessage.sellToCustCd_H2.setInputProtected(protectFlg);
        bMessage.shipToCustCd_H1.setInputProtected(protectFlg);
        bMessage.shipToCustCd_H2.setInputProtected(protectFlg);
        bMessage.podStsTpForScrCd_H2.setInputProtected(protectFlg);
        bMessage.trxHdrNum_H1.setInputProtected(protectFlg);
        bMessage.soNum_H1.setInputProtected(protectFlg);
        bMessage.podStsDt_H1.setInputProtected(protectFlg);
        bMessage.podStsDt_H2.setInputProtected(protectFlg);
    }

    // UPDATE START 2016/09/29 QC#2434
    /**
     * Cotrol Input Item's activation.
     * @param bMessage NLBL0090BMsg
     * @param protectFlg true/inactivate item, false/activate item
     */
    private static void setInputProtectionOfTableAttr(NLBL0090BMsg bMessage, boolean protectFlg) {

        for (int i = 0; i < bMessage.B.getValidCount(); i++) {
            bMessage.B.no(i).mdseDescShortTxt_B1.setInputProtected(protectFlg);
        }
    }
    // UPDATE END 2016/09/29 QC#2434

    /**
     * Control Button's activation for header.
     * @param handler EZDCommonHandler
     * @param enabledFlg true/activate item, false/inactivate item
     */
    private static void setActiveCntrlOfButtons(EZDCommonHandler handler, boolean enabledFlg) {

        handler.setButtonEnabled(BOL_SEARCH, enabledFlg);
        handler.setButtonEnabled(BOL_CRRER_BUTTON, enabledFlg);
        handler.setButtonEnabled(BOL_SELLTO_BUTTON, enabledFlg);
        handler.setButtonEnabled(BOL_SHIPTO_BUTTON, enabledFlg);
    }

    /**
     * Control Button's activation for control area.
     * @param handler EZDCommonHandler
     */
    private static void protectFooterButton(EZDCommonHandler handler) {

        // QC#16256 add start
        // This Screen is Read only Screen
        if (isUpdatable()) {
            handler.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 0, null);
            handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 0, null);
            handler.setButtonProperties(BTN_CMN_APPLY[0], BTN_CMN_APPLY[1], BTN_CMN_APPLY[2], 0, null);
            handler.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 0, null);
            handler.setButtonProperties(BTN_CMN_REJECT[0], BTN_CMN_REJECT[1], BTN_CMN_REJECT[2], 0, null);
            handler.setButtonProperties(BTN_CMN_DOWNLOAD[0], BTN_CMN_DOWNLOAD[1], BTN_CMN_DOWNLOAD[2], 0, null);
            handler.setButtonProperties(BTN_CMN_DELETE[0], BTN_CMN_DELETE[1], BTN_CMN_DELETE[2], 0, null);
            handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 0, null);
            handler.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 0, null);
            handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 0, null);
        } else {
            handler.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 0, null);
            handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 0, null);
            handler.setButtonProperties(BTN_CMN_APPLY[0], BTN_CMN_APPLY[1], BTN_CMN_APPLY[2], 0, null);
            handler.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 0, null);
            handler.setButtonProperties(BTN_CMN_REJECT[0], BTN_CMN_REJECT[1], BTN_CMN_REJECT[2], 0, null);
            handler.setButtonProperties(BTN_CMN_DOWNLOAD[0], BTN_CMN_DOWNLOAD[1], BTN_CMN_DOWNLOAD[2], 0, null);
            handler.setButtonProperties(BTN_CMN_DELETE[0], BTN_CMN_DELETE[1], BTN_CMN_DELETE[2], 0, null);
            handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 0, null);
            handler.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 0, null);
            handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 0, null);
        }
        // QC#16256 add end
    }

    /**
     * Control POD Status Code's activation.
     * @param bMessage NLBL0090BMsg
     */
    public static void activateControlPldStsCd(NLBL0090BMsg bMessage) {

        if (!ZYPCommonFunc.hasValue(bMessage.podStsTpForScrCd_H2)) {
            bMessage.podStsCd_H2.setInputProtected(true);
        } else {
            bMessage.podStsCd_H2.setInputProtected(false);
        }
    }

    // ************* Control Tab's visibility *************
    /**
     * Visualize BOL Tracking TAB.
     * @param bMessage NLBL0090BMsg
     * @param handler EZDCommonHandler
     */
    public static void showBOLTrckngTab(NLBL0090BMsg bMessage, EZDCommonHandler handler) {

        bMessage.xxTabProt_A1.setInputProtected(true);

        if (bMessage.A.getValidCount() == 0) {
            // inactivate
            bMessage.xxTabProt_B1.setInputProtected(true);
            handler.setButtonEnabled(BOL_HSTRY_BUTTON, false);
            handler.setButtonProperties(BTN_CMN_DOWNLOAD[0], BTN_CMN_DOWNLOAD[1], BTN_CMN_DOWNLOAD[2], 0, null);
        } else {
            // activate
            bMessage.xxTabProt_B1.setInputProtected(false);
            handler.setButtonEnabled(BOL_HSTRY_BUTTON, true);
            handler.setButtonProperties(BTN_CMN_DOWNLOAD[0], BTN_CMN_DOWNLOAD[1], BTN_CMN_DOWNLOAD[2], 1, null);
        }

        bMessage.xxDplyTab.setValue(TB_BOL_TRCKNG);
    }

    /**
     * Visualize BOL Detail TAB.
     * @param bMessage NLBL0090BMsg
     */
    public static void showDetailTab(NLBL0090BMsg bMessage) {
        bMessage.xxTabProt_A1.setInputProtected(false);
        bMessage.xxTabProt_B1.setInputProtected(false);

        bMessage.xxDplyTab.setValue(TB_BOL_DTL);
    }

    /**
     * Setup Background color for Search Result List, to B Table.
     * @param bMessage NLBL0090BMsg
     */
    public static void setTblBackColorB(NLBL0090BMsg bMessage) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID, bMessage);

        bMessage.clearGUIAttribute(SCRN_ID, TBL_ID_LEFT_A);
        bMessage.clearGUIAttribute(SCRN_ID, TBL_ID_RIGHT_A);
        bMessage.clearGUIAttribute(SCRN_ID, TBL_ID_B);

        tblColor.setAlternateRowsBG(TBL_ID_B, bMessage.B);
    }

    /**
     * Combine cells, when "DC B/L#","Tracking/Pro#","Carrier","WH"
     * have same value, to A Table.
     * @param bMessage NLBL0090BMsg
     */
    public static void setTblBackColorAndUnitCellsA(NLBL0090BMsg bMessage) {

        String prevBolNum = "";
        String prevProNum = "";
        String prevLocNm = "";
        String prevWhCd = "";
        for (int i = 0; i < bMessage.A.getValidCount(); i++) {

            if (prevBolNum.equals(bMessage.A.no(i).bolNum_A1.getValue()) && prevProNum.equals(bMessage.A.no(i).proNum_A1.getValue()) && prevLocNm.equals(bMessage.A.no(i).locNm_A1.getValue())
                    && prevWhCd.equals(bMessage.A.no(i).whCd_A1.getValue())) {

                bMessage.A.no(i).bolNum_A1.clear();
                bMessage.A.no(i).proNum_A1.clear();
                bMessage.A.no(i).locNm_A1.clear();
                bMessage.A.no(i).whCd_A1.clear();
                bMessage.A.no(i).sellToCustCd_A1.clear();
                bMessage.A.no(i).shipToCustCd_A1.clear();
                bMessage.A.no(i).podStsDt_A1.clear();
                bMessage.A.no(i).podStsCd_A2.clear();
                bMessage.A.no(i).podStsNm_A1.clear();
                bMessage.A.no(i).podStsRsnCd_A2.clear();
                bMessage.A.no(i).podStsRsnNm_A1.clear();
                bMessage.A.no(i).carrTrkUrl_A1.clear();

            } else {
                prevBolNum = bMessage.A.no(i).bolNum_A1.getValue();
                prevProNum = bMessage.A.no(i).proNum_A1.getValue();
                prevLocNm = bMessage.A.no(i).locNm_A1.getValue();
                prevWhCd = bMessage.A.no(i).whCd_A1.getValue();
            }
        }

        bMessage.clearAllGUIAttribute(SCRN_ID);

        boolean recBGcolorFlg = false;

        // Generation of background color change parts in table of
        // screen
        S21TableColorController tblColor = new S21TableColorController(SCRN_ID, bMessage);

        for (int i = 0; i < bMessage.A.getValidCount(); i++) {

            if (ZYPCommonFunc.hasValue(bMessage.A.no(i).bolNum_A1)) {
                recBGcolorFlg = !recBGcolorFlg;
            }
            if (!recBGcolorFlg) {
                tblColor.setRowStyle(TBL_ID_LEFT_A, i, STYLE_NAME_FOR_EVEN_NUM_BG);
                tblColor.setRowStyle(TBL_ID_RIGHT_A, i, STYLE_NAME_FOR_EVEN_NUM_BG);
            }

            // create Radio Button by
            // "DC B/L#"&"Tracking/Pro#"&"Carrier"&"WH"
            if (!ZYPCommonFunc.hasValue(bMessage.A.no(i).bolNum_A1)) {
                EZDGUIAttribute guiAttr = new EZDGUIAttribute(SCRN_ID, RADIO_BUTTON_NAME_VALUE + Integer.toString(i));
                guiAttr.setVisibility(false);
                bMessage.addGUIAttribute(guiAttr);
            }
        }
    }

    // ************* Control Messages *************
    /**
     * check error in EZDCMsg. Does EZDCMsg have Error? Check EZDCMsg
     * has error messages.
     * @param cMsg NLBL0090CMsg
     * @return If cMsg has error messages.
     */
    public static boolean hasMsgError(NLBL0090CMsg cMsg) {
        return cMsg != null && "E".equals(cMsg.getMessageKind());
    }

    // ************* For Popup *************
    /**
     * Check lastGuard is 'CLOSE' event.
     * @param lastGuard String
     * @return If lastGuard is 'CLOSE' event.
     */
    public static boolean isClosedEvent(String lastGuard) {
        return CLOSE.toLowerCase().equals(lastGuard.toLowerCase());
    }

    /**
     * Set Account Popup param
     * @param scrnMsg NLBL0090BMsg
     * @param isSellToFlag Sell to or Ship to flag.
     * @return ShipToCustPopup Param (NMAL6760) Object[]
     */
    public static Object[] setParamForShipToCustPopup(NLBL0090BMsg scrnMsg, boolean isSellToFlag) {

        ZYPEZDItemValueSetter.setValue(scrnMsg.O.no(IDX_0).xxPopPrm_O1, scrnMsg.sellToCustCd_H2);
        scrnMsg.O.no(IDX_1).xxPopPrm_O1.clear();
        scrnMsg.O.no(IDX_2).xxPopPrm_O1.clear();
        scrnMsg.O.no(IDX_3).xxPopPrm_O1.clear();
        scrnMsg.O.no(IDX_4).xxPopPrm_O1.clear();
        scrnMsg.O.no(IDX_5).xxPopPrm_O1.clear();
        scrnMsg.O.no(IDX_6).xxPopPrm_O1.clear();
        scrnMsg.O.no(IDX_7).xxPopPrm_O1.clear();
        if (isSellToFlag) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.O.no(IDX_8).xxPopPrm_O1, POP_PARAM_ACCTS_ONLY);
        } else {
            ZYPEZDItemValueSetter.setValue(scrnMsg.O.no(IDX_8).xxPopPrm_O1, POP_PARAM_SHIPS_ONLY);
        }
        scrnMsg.O.no(IDX_9).xxPopPrm_O1.clear();
        scrnMsg.O.no(IDX_10).xxPopPrm_O1.clear();
        ZYPEZDItemValueSetter.setValue(scrnMsg.O.no(IDX_11).xxPopPrm_O1, POP_PARAM_ACT_IACT);
        ZYPEZDItemValueSetter.setValue(scrnMsg.O.no(IDX_12).xxPopPrm_O1, POP_PARAM_ALL);
        scrnMsg.O.no(IDX_13).xxPopPrm_O1.clear();
        scrnMsg.O.no(IDX_14).xxPopPrm_O1.clear();
        scrnMsg.O.no(IDX_15).xxPopPrm_O1.clear();
        scrnMsg.O.no(IDX_16).xxPopPrm_O1.clear();

        scrnMsg.O.setValidCount(IDX_17);

        Object[] params = new Object[IDX_17];

        for (int i = 0; i < scrnMsg.O.getValidCount() && i < params.length; i++) {
            params[i] = scrnMsg.O.no(i).xxPopPrm_O1;
        }

        return params;
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
