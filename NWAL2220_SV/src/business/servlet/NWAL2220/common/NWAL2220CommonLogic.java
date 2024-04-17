/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2220.common;

import static business.servlet.NWAL2220.constant.NWAL2220Constant.SCREEN_ID;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NWAL2220.NWAL2220BMsg;
import business.servlet.NWAL2220.NWAL2220_ABMsg;
import business.servlet.NWAL2220.constant.NWAL2220Constant.BTN_LBL;

import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * Import  Search & Result
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/16   Hitachi         T.Tsuchida      Create          N/A
 * 2017/09/01   Fujitsu         R.Nakamura      Update          QC#20892
 * 2022/04/18   CITS            J.Evangelista   Update          QC#59934
 *</pre>
 */
public class NWAL2220CommonLogic {

    /**
     * addCheckItem
     * @param scrnMsg NWAL2220BMsg
     */
    public static void addCheckItem(NWAL2220BMsg scrnMsg) {

        scrnMsg.addCheckItem(scrnMsg.cpoSrcTpCd_SV);
        scrnMsg.addCheckItem(scrnMsg.ordSrcRefNum);
        scrnMsg.addCheckItem(scrnMsg.imptStsCd_SV);
        scrnMsg.addCheckItem(scrnMsg.tocNm);
        scrnMsg.addCheckItem(scrnMsg.ordSrcImptDt_FM);
        scrnMsg.addCheckItem(scrnMsg.ordSrcImptDt_TO);
        scrnMsg.addCheckItem(scrnMsg.coaExtnCd_SV);
        scrnMsg.addCheckItem(scrnMsg.coaBrDescTxt);
        scrnMsg.addCheckItem(scrnMsg.cpoOrdNum);
        scrnMsg.addCheckItem(scrnMsg.sellToCustCd);
        scrnMsg.addCheckItem(scrnMsg.dsAcctNm_OT);
        scrnMsg.addCheckItem(scrnMsg.soldToCustLocCd);
        scrnMsg.addCheckItem(scrnMsg.billToCustAcctCd);
        scrnMsg.addCheckItem(scrnMsg.dsAcctNm_BT);
        scrnMsg.addCheckItem(scrnMsg.billToCustCd);
        scrnMsg.addCheckItem(scrnMsg.shipToCustAcctCd);
        scrnMsg.addCheckItem(scrnMsg.dsAcctNm_HT);
        scrnMsg.addCheckItem(scrnMsg.shipToCustCd);
    }

    // ************* For Popup *************
    /**
     * Check lastGuard is 'CLOSE' event.
     * @param lastGuard String
     * @return If lastGuard is 'CLOSE' event.
     */
    public static boolean isClosedEvent(String lastGuard) {
        return BTN_LBL.CLOSE.getGuardNm().toLowerCase().equals(lastGuard.toLowerCase());
    }

    /**
     * <pre>
     * The initial state of the screen item is set.
     * </pre>
     * @param handler EZDCommonHandler
     * @param scrnMsg NWAL2220BMsg
     */
    public static final void initialControlScreen(EZDCommonHandler handler, NWAL2220BMsg scrnMsg) {

        initCommonButton(handler);
        controlScreenHeader(handler, scrnMsg);
        controlScreenDetail(handler, scrnMsg);
        controlScreenBottom(handler, scrnMsg, false);
    }

    /**
     * <pre>
     * The Search Event of the screen item is set.
     * </pre>
     * @param handler EZDCommonHandler
     * @param scrnMsg NWAL2220BMsg
     */
    public static final void srchControlScreen(EZDCommonHandler handler, NWAL2220BMsg scrnMsg) {

        if (scrnMsg.A.getValidCount() > 0) {
            controlScreenDetail(handler, scrnMsg);
            controlScreenBottom(handler, scrnMsg, true);
        }
    }

    /**
     * Control screen header
     * @param handler EZDCommonHandler
     * @param scrnMsg NWAL2220BMsg
     */
    private static final void controlScreenHeader(EZDCommonHandler handler, NWAL2220BMsg scrnMsg) {

        controlScreenHeaderFields(handler, scrnMsg);
        if (scrnMsg.A.getValidCount() > 0) {
            setRowColors(scrnMsg, "A");
        }
    }

    /**
     * controlScreenHeaderFields
     * @param handler EZDCommonHandler
     * @param scrnMsg NWAL2220BMsg
     */
    private static final void controlScreenHeaderFields(EZDCommonHandler handler, NWAL2220BMsg scrnMsg) {

        scrnMsg.cpoSrcTpCd_SV.setInputProtected(false);
        scrnMsg.ordSrcRefNum.setInputProtected(false);
        scrnMsg.imptStsCd_SV.setInputProtected(false);
        scrnMsg.tocNm.setInputProtected(false);
        scrnMsg.ordSrcImptDt_FM.setInputProtected(false);
        scrnMsg.ordSrcImptDt_TO.setInputProtected(false);
        scrnMsg.coaExtnCd_SV.setInputProtected(false);
        scrnMsg.coaBrDescTxt.setInputProtected(false);
        scrnMsg.cpoOrdNum.setInputProtected(false);
        scrnMsg.sellToCustCd.setInputProtected(false);
        scrnMsg.dsAcctNm_OT.setInputProtected(false);
        scrnMsg.soldToCustLocCd.setInputProtected(false);
        scrnMsg.billToCustAcctCd.setInputProtected(false);
        scrnMsg.dsAcctNm_BT.setInputProtected(false);
        scrnMsg.billToCustCd.setInputProtected(false);
        scrnMsg.shipToCustAcctCd.setInputProtected(false);
        scrnMsg.dsAcctNm_HT.setInputProtected(false);
        scrnMsg.shipToCustCd.setInputProtected(false);
    }

    /**
     * controlScreenDetail
     * @param handler EZDCommonHandler
     * @param scrnMsg NWAL2220BMsg
     */
    private static void controlScreenDetail(EZDCommonHandler handler, NWAL2220BMsg scrnMsg) {

        if (scrnMsg.A.getValidCount() > 0) {
            controlScreenDetailFields(handler, scrnMsg);
            setRowColors(scrnMsg, "A");
        }
    }

    /**
     * controlScreenBottom
     * @param handler EZDCommonHandler
     * @param scrnMsg NWAL2220BMsg
     * @param isEnabled boolean
     */
    private static void controlScreenBottom(EZDCommonHandler handler, NWAL2220BMsg scrnMsg, boolean isEnabled) {

        handler.setButtonEnabled(BTN_LBL.DETAIL.getOrgNm(), isEnabled);
    }

    /**
     * controlScreenDetailFields
     * @param handler EZDCommonHandler
     * @param scrnMsg NWAL2220BMsg
     */
    private static final void controlScreenDetailFields(EZDCommonHandler handler, NWAL2220BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NWAL2220_ABMsg abMsg = scrnMsg.A.no(i);
            abMsg.cpoSrcTpDescTxt_A.setInputProtected(true);
            abMsg.ordSrcRefNum_A.setInputProtected(true);
            abMsg.imptStsDescTxt_A.setInputProtected(true);
            abMsg.xxTsDsp19Txt_A.setInputProtected(true);
            abMsg.tocNm_A.setInputProtected(true);
            abMsg.coaExtnDescTxt_A.setInputProtected(true);
            abMsg.coaBrDescTxt_A.setInputProtected(true);
            abMsg.dsOrdCatgDescTxt_A.setInputProtected(true);
            // Mod Start 2017/09/01 QC#20892
//            abMsg.dsOrdRsnDescTxt_A.setInputProtected(true);
            abMsg.dsOrdTpDescTxt_A.setInputProtected(true);
            // Mod End 2017/09/01 QC#20892
            abMsg.cpoOrdNum_A.setInputProtected(true);
            abMsg.sellToCustCd_A.setInputProtected(true);
            abMsg.dsAcctNm_AO.setInputProtected(true);
            abMsg.soldToCustLocCd_A.setInputProtected(true);
            abMsg.billToCustAcctCd_A.setInputProtected(true);
            abMsg.dsAcctNm_AB.setInputProtected(true);
            abMsg.billToCustCd_A.setInputProtected(true);
            abMsg.shipToCustAcctCd_A.setInputProtected(true);
            abMsg.dsAcctNm_AH.setInputProtected(true);
            abMsg.shipToCustCd_A.setInputProtected(true);
        }
    }

    /**
     * initCommonButton
     * @param handler EZDCommonHandler
     */
    private static void initCommonButton(EZDCommonHandler handler) {

        handler.setButtonProperties(BTN_LBL.SAVE.getOrgNm(), BTN_LBL.SAVE.getGuardNm(), BTN_LBL.SAVE.getLblNm(), 0, null);
        handler.setButtonProperties(BTN_LBL.SUBMIT.getOrgNm(), BTN_LBL.SUBMIT.getGuardNm(), BTN_LBL.SUBMIT.getLblNm(), 0, null);
        handler.setButtonProperties(BTN_LBL.APPLY.getOrgNm(), BTN_LBL.APPLY.getGuardNm(), BTN_LBL.APPLY.getLblNm(), 0, null);
        handler.setButtonProperties(BTN_LBL.APPROVE.getOrgNm(), BTN_LBL.APPROVE.getGuardNm(), BTN_LBL.APPROVE.getLblNm(), 0, null);
        handler.setButtonProperties(BTN_LBL.REJECT.getOrgNm(), BTN_LBL.REJECT.getGuardNm(), BTN_LBL.REJECT.getLblNm(), 0, null);
        handler.setButtonProperties(BTN_LBL.DOWNLOAD.getOrgNm(), BTN_LBL.DOWNLOAD.getGuardNm(), BTN_LBL.DOWNLOAD.getLblNm(), 1, null);
        handler.setButtonProperties(BTN_LBL.DELETE.getOrgNm(), BTN_LBL.DELETE.getGuardNm(), BTN_LBL.DELETE.getLblNm(), 0, null);
        // START 2022/04/18 J.Evangelista [QC#59934,MOD]
//        handler.setButtonProperties(BTN_LBL.CLEAR.getOrgNm(), BTN_LBL.CLEAR.getGuardNm(), BTN_LBL.CLEAR.getLblNm(), 0, null);
//        handler.setButtonProperties(BTN_LBL.RESET.getOrgNm(), BTN_LBL.RESET.getGuardNm(), BTN_LBL.RESET.getLblNm(), 1, null);
        handler.setButtonProperties(BTN_LBL.CLEAR.getOrgNm(), BTN_LBL.CLEAR.getGuardNm(), BTN_LBL.CLEAR.getLblNm(), 1, null);
        handler.setButtonProperties(BTN_LBL.RESET.getOrgNm(), BTN_LBL.RESET.getGuardNm(), BTN_LBL.RESET.getLblNm(), 0, null);
        // END 2022/04/18 J.Evangelista [QC#59934,MOD]
        handler.setButtonProperties(BTN_LBL.RETURN.getOrgNm(), BTN_LBL.RETURN.getGuardNm(), BTN_LBL.RETURN.getLblNm(), 1, null);

    }

    /**
     * setRowColors
     * @param scrnMsg NWAL2220BMsg
     * @param tblId String
     */
    private static void setRowColors(NWAL2220BMsg scrnMsg, String tblId) {

        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        tblColor.setAlternateRowsBG(tblId, scrnMsg.A);
    }

    /**
     * The method explanation: set parameter to call popup.
     * @param scrnMsg NWAL2220BMsg
     */
    public static void initParam(NWAL2220BMsg scrnMsg) {

        scrnMsg.xxScrEventNm.clear();
        scrnMsg.xxPopPrm_00.clear();
        scrnMsg.xxPopPrm_01.clear();
        scrnMsg.xxPopPrm_02.clear();
        scrnMsg.xxPopPrm_03.clear();
        scrnMsg.xxPopPrm_04.clear();
        scrnMsg.xxPopPrm_05.clear();
        scrnMsg.xxPopPrm_06.clear();
        scrnMsg.xxPopPrm_07.clear();
        scrnMsg.xxPopPrm_08.clear();
        scrnMsg.xxPopPrm_09.clear();
        scrnMsg.xxPopPrm_10.clear();
        scrnMsg.xxPopPrm_11.clear();
        scrnMsg.xxPopPrm_12.clear();
        scrnMsg.xxPopPrm_13.clear();
        scrnMsg.xxPopPrm_14.clear();
        scrnMsg.xxPopPrm_15.clear();
        scrnMsg.xxPopPrm_16.clear();
        scrnMsg.xxPopPrm_17.clear();
        scrnMsg.xxPopPrm_18.clear();
        scrnMsg.xxPopPrm_19.clear();
        scrnMsg.xxPopPrm_20.clear();
        scrnMsg.xxPopPrm_21.clear();
        scrnMsg.xxPopPrm_22.clear();
        scrnMsg.xxPopPrm_23.clear();
        scrnMsg.xxPopPrm_24.clear();
        scrnMsg.xxPopPrm_25.clear();
        scrnMsg.xxPopPrm_26.clear();
        scrnMsg.xxPopPrm_27.clear();
        scrnMsg.xxPopPrm_28.clear();
        scrnMsg.xxPopPrm_29.clear();
        scrnMsg.xxPopPrm_30.clear();
        scrnMsg.xxPopPrm_31.clear();
        scrnMsg.xxPopPrm_32.clear();
        scrnMsg.xxPopPrm_33.clear();
        scrnMsg.xxPopPrm_34.clear();
    }

}
