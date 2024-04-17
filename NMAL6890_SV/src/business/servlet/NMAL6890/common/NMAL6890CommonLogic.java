/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NMAL6890.common;

import static business.servlet.NMAL6890.constant.NMAL6890Constant.DISPLAY_NM_ALLW_MIN_MAX;
import static business.servlet.NMAL6890.constant.NMAL6890Constant.DISPLAY_NM_ALT_OWN;
import static business.servlet.NMAL6890.constant.NMAL6890Constant.DISPLAY_NM_CUR_PAGE_NUM;
import static business.servlet.NMAL6890.constant.NMAL6890Constant.DISPLAY_NM_DFT_SRC_TYPE;
import static business.servlet.NMAL6890.constant.NMAL6890Constant.DISPLAY_NM_EMRGCY_SRC_NM;
import static business.servlet.NMAL6890.constant.NMAL6890Constant.DISPLAY_NM_EMRGCY_SRC_TYPE;
import static business.servlet.NMAL6890.constant.NMAL6890Constant.DISPLAY_NM_INV_ACCT;
import static business.servlet.NMAL6890.constant.NMAL6890Constant.DISPLAY_NM_INV_OWN;
import static business.servlet.NMAL6890.constant.NMAL6890Constant.DISPLAY_NM_OWN;
import static business.servlet.NMAL6890.constant.NMAL6890Constant.DISPLAY_NM_RTRN_LOC_NM;
import static business.servlet.NMAL6890.constant.NMAL6890Constant.DISPLAY_NM_RTRN_SRC_TYPE;
import static business.servlet.NMAL6890.constant.NMAL6890Constant.DISPLAY_NM_RTRN_WH_NM;
import static business.servlet.NMAL6890.constant.NMAL6890Constant.DISPLAY_NM_SHIP_TO_LOC_NM;
import static business.servlet.NMAL6890.constant.NMAL6890Constant.DISPLAY_NM_SHOW_SWH_LVL;
import static business.servlet.NMAL6890.constant.NMAL6890Constant.DISPLAY_NM_SPACE;
import static business.servlet.NMAL6890.constant.NMAL6890Constant.DISPLAY_NM_SRCH_OPT_NM;
import static business.servlet.NMAL6890.constant.NMAL6890Constant.DISPLAY_NM_SRC_WH_NM;
import static business.servlet.NMAL6890.constant.NMAL6890Constant.DISPLAY_NM_TOT_PAGE_NUM;
import static business.servlet.NMAL6890.constant.NMAL6890Constant.DISPLAY_NM_TRD_PRT_ID;
import static business.servlet.NMAL6890.constant.NMAL6890Constant.DISPLAY_NM_WH_CD;
import static business.servlet.NMAL6890.constant.NMAL6890Constant.DISPLAY_NM_WH_DESC;
import static business.servlet.NMAL6890.constant.NMAL6890Constant.DISPLAY_NM_WH_END_DT;
import static business.servlet.NMAL6890.constant.NMAL6890Constant.DISPLAY_NM_WH_OP;
import static business.servlet.NMAL6890.constant.NMAL6890Constant.DISPLAY_NM_WH_NM;
import static business.servlet.NMAL6890.constant.NMAL6890Constant.DISPLAY_NM_WH_START_DT;
import static business.servlet.NMAL6890.constant.NMAL6890Constant.DISPLAY_NM_WH_TEL_NUM;
import static business.servlet.NMAL6890.constant.NMAL6890Constant.DISPLAY_NM_WH_TYPE;
import static business.servlet.NMAL6890.constant.NMAL6890Constant.DISPLAY_NM_CTY_ADDR;
import static business.servlet.NMAL6890.constant.NMAL6890Constant.DTL_CD_LB_NM_FOR_OWNER;
import static business.servlet.NMAL6890.constant.NMAL6890Constant.DTL_NM_LB_NM_FOR_OWNER;
import static business.servlet.NMAL6890.constant.NMAL6890Constant.HDR_CD_LB_NM_FOR_OWNER;
import static business.servlet.NMAL6890.constant.NMAL6890Constant.HDR_NM_LB_NM_FOR_OWNER;
import static business.servlet.NMAL6890.constant.NMAL6890Constant.SCRN_ID;
import static business.servlet.NMAL6890.constant.NMAL6890Constant.SCR_NM_FOR_OWNER;
import static business.servlet.NMAL6890.constant.NMAL6890Constant.TBL_CD_COL_NM_FOR_OWNER;
import static business.servlet.NMAL6890.constant.NMAL6890Constant.TBL_NM_COL_NM_FOR_OWNER;
import static business.servlet.NMAL6890.constant.NMAL6890Constant.TBL_NM_FOR_OWNER;
import static business.servlet.NMAL6890.constant.NMAL6890Constant.TBL_SORT_COL_NM_FOR_OWNER;
import business.servlet.NMAL6890.NMAL6890BMsg;
import business.servlet.NMAL6890.constant.NMAL6890Constant.BTN_APP;
import business.servlet.NMAL6890.constant.NMAL6890Constant.BTN_CMN;
import business.servlet.NMAL6890.constant.NMAL6890Constant.BTN_STATUS;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 * <pre>
 * Business ID : NMAL6890 Warehouse Search
 * Function Name : sv common logic
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/11/2016   CITS            Y.Kuroda        Create          N/A
 * 10/21/2022   HITACHI         B.Amarsanaa     Update          QC#60609
 * 03/07/2023   Hitachi         S.Dong          Update          QC#61205
 *</pre>
 */
public class NMAL6890CommonLogic {

    /**
     * <p>
     * Activates the common button.
     * </p>
     * @param button the enumeration of common button.
     * @param handler S21CommonHandler
     */
    public static void activateButton(BTN_CMN button, S21CommonHandler handler) {
        setButtonProperties(handler, button, BTN_STATUS.ENABLED);
    }

    /**
     * <p>
     * Deactivates the common button.
     * </p>
     * @param button the enumeration of common button.
     * @param handler S21CommonHandler
     */
    public static void deactivateButton(BTN_CMN button, S21CommonHandler handler) {
        setButtonProperties(handler, button, BTN_STATUS.DISABLED);
    }

    /**
     * <p>
     * Sets the properties of common button.
     * </p>
     * @param handler S21CommonHandler
     * @param button the enumeration of common button.
     * @param status the button status.
     */
    private static void setButtonProperties(S21CommonHandler handler, BTN_CMN button, BTN_STATUS status) {
        handler.setButtonProperties(button.getButtonName(), button.getEventName(), button.getLabel(), status.getStatus(), null);
    }

    /**
     * <p>
     * Activates the application button.
     * </p>
     * @param button the enumeration of application button.
     * @param handler S21CommonHandler
     */
    public static void activateButton(BTN_APP button, S21CommonHandler handler) {
        setButtonProperties(handler, button, BTN_STATUS.ENABLED);
    }

    /**
     * <p>
     * Deactivates the application button.
     * </p>
     * @param button the enumeration of application button.
     * @param handler S21CommonHandler
     */
    public static void deactivateButton(BTN_APP button, S21CommonHandler handler) {
        setButtonProperties(handler, button, BTN_STATUS.DISABLED);
    }

    /**
     * <p>
     * Sets the properties of common button.
     * </p>
     * @param handler S21CommonHandler
     * @param button the enumeration of common button.
     * @param status the button status.
     */
    private static void setButtonProperties(S21CommonHandler handler, BTN_APP button, BTN_STATUS status) {
        handler.setButtonEnabled(button.getName(), status.isEnabled());
    }

    /**
     * <pre>
     * The input scrnMsg is cleared.
     * </pre>
     * @param scrnMsg NMAL6890BMsg
     */
    public static void clearScreenMsg(NMAL6890BMsg scrnMsg) {

        // Header
        scrnMsg.srchOptPk_SS.clear();
        scrnMsg.srchOptNm.clear();
        scrnMsg.rtlWhNm_H1.clear();
        scrnMsg.rtlWhDescTxt.clear();
        scrnMsg.rtlWhCd.clear();
        scrnMsg.rtlWhCatgCd_RS.clear();
        scrnMsg.fullPsnNm_O1.clear();
        scrnMsg.fullPsnNm_O2.clear();
        scrnMsg.invtyAcctCd_IS.clear();
        scrnMsg.shipToLocNm.clear();
        scrnMsg.rtrnToLocNm.clear();
        scrnMsg.telNum.clear();
        scrnMsg.procrTpCd_SS.clear();
        scrnMsg.rtlWhNm_H2.clear();
        scrnMsg.procrTpCd_RS.clear();
        scrnMsg.rtlWhNm_H3.clear();
        scrnMsg.procrTpCd_ES.clear();
        scrnMsg.rtlWhNm_H4.clear();
        scrnMsg.plnItemInsrcCd_MS.clear();
        scrnMsg.vndLocCd.clear();
        scrnMsg.invtyOwnrCd_OS.clear();
        scrnMsg.firstRefCmntTxt.clear();
        scrnMsg.effFromDt.clear();
        scrnMsg.effThruDt.clear();
        // START 2023/03/07 S.Dong [QC#61205, ADD]
        scrnMsg.whOwnrCd_H1.clear();
        // END 2023/03/07 S.Dong [QC#61205, ADD]
        scrnMsg.rtlSwhCd.clear();
        scrnMsg.xxPageShowCurNum.clear();
        scrnMsg.xxPageShowTotNum.clear();
        scrnMsg.fullPsnNm_G1.clear();
        scrnMsg.fullPsnNm_P1.clear();
        scrnMsg.fullPsnNm_P2.clear();
        scrnMsg.xxPopPrm_P1.clear();
        scrnMsg.xxCondCd_P1.clear();
        scrnMsg.xxCondCd_P2.clear();
        //10/21/2022 HITACHI B.Amarsanaa (QC#60608) ADD START
        scrnMsg.ctyAddr.clear();
        //10/21/2022 HITACHI B.Amarsanaa (QC#60608) ADD END 
        // Detail
        clearTable(scrnMsg);
    }

    /**
     * clear table
     * @param scrnMsg
     */
    public static void clearTable(NMAL6890BMsg scrnMsg) {

        ZYPTableUtil.clear(scrnMsg.A);
        scrnMsg.xxPageShowFromNum.clear();
        scrnMsg.xxPageShowToNum.clear();
        scrnMsg.xxPageShowOfNum.clear();
    }

    /**
     * <p>
     * Sets the name for the error message.
     * </p>
     * @param scrnMsg scrnMsg (BMsg)
     */
    public static void setNameForMessage(NMAL6890BMsg scrnMsg) {
        // Search Options
        scrnMsg.srchOptNm.setNameForMessage(DISPLAY_NM_SRCH_OPT_NM);

        // Search Condition
        scrnMsg.rtlWhNm_H1.setNameForMessage(DISPLAY_NM_WH_NM);
        scrnMsg.rtlWhDescTxt.setNameForMessage(DISPLAY_NM_WH_DESC);
        scrnMsg.rtlWhCd.setNameForMessage(DISPLAY_NM_WH_CD);
        scrnMsg.rtlWhCatgCd_RS.setNameForMessage(DISPLAY_NM_WH_TYPE);
        scrnMsg.fullPsnNm_O1.setNameForMessage(DISPLAY_NM_OWN);
        scrnMsg.fullPsnNm_O2.setNameForMessage(DISPLAY_NM_ALT_OWN);
        scrnMsg.invtyAcctCd_IS.setNameForMessage(DISPLAY_NM_INV_ACCT);
        scrnMsg.shipToLocNm.setNameForMessage(DISPLAY_NM_SHIP_TO_LOC_NM);
        scrnMsg.rtrnToLocNm.setNameForMessage(DISPLAY_NM_RTRN_LOC_NM);
        scrnMsg.telNum.setNameForMessage(DISPLAY_NM_WH_TEL_NUM);
        scrnMsg.procrTpCd_SS.setNameForMessage(DISPLAY_NM_DFT_SRC_TYPE);
        scrnMsg.rtlWhNm_H2.setNameForMessage(DISPLAY_NM_SRC_WH_NM);
        scrnMsg.procrTpCd_RS.setNameForMessage(DISPLAY_NM_RTRN_SRC_TYPE);
        scrnMsg.rtlWhNm_H3.setNameForMessage(DISPLAY_NM_RTRN_WH_NM);
        scrnMsg.procrTpCd_ES.setNameForMessage(DISPLAY_NM_EMRGCY_SRC_TYPE);
        scrnMsg.rtlWhNm_H4.setNameForMessage(DISPLAY_NM_EMRGCY_SRC_NM);
        scrnMsg.plnItemInsrcCd_MS.setNameForMessage(DISPLAY_NM_ALLW_MIN_MAX);
        scrnMsg.vndLocCd.setNameForMessage(DISPLAY_NM_TRD_PRT_ID);
        scrnMsg.invtyOwnrCd_OS.setNameForMessage(DISPLAY_NM_INV_OWN);
        scrnMsg.firstRefCmntTxt.setNameForMessage(DISPLAY_NM_SPACE);
        scrnMsg.effFromDt.setNameForMessage(DISPLAY_NM_WH_START_DT);
        scrnMsg.effThruDt.setNameForMessage(DISPLAY_NM_WH_END_DT);
        // START 2023/03/07 S.Dong [QC#61205, ADD]
        scrnMsg.whOwnrCd_H1.setNameForMessage(DISPLAY_NM_WH_OP);
        // END 2023/03/07 S.Dong [QC#61205, ADD]
        scrnMsg.rtlSwhCd.setNameForMessage(DISPLAY_NM_SHOW_SWH_LVL);
        scrnMsg.xxPageShowCurNum.setNameForMessage(DISPLAY_NM_CUR_PAGE_NUM);
        scrnMsg.xxPageShowTotNum.setNameForMessage(DISPLAY_NM_TOT_PAGE_NUM);
        //10/21/2022 HITACHI B.Amarsanaa (QC#60608) ADD START
        scrnMsg.ctyAddr.setNameForMessage(DISPLAY_NM_CTY_ADDR);
        //10/21/2022 HITACHI B.Amarsanaa (QC#60608) ADD END
    }

    /**
     * check Item
     * @param scrnMsg NMAL6890BMsg
     */
    public static void commonAddCheckItem(NMAL6890BMsg scrnMsg) {

        scrnMsg.addCheckItem(scrnMsg.srchOptPk_SS);
        scrnMsg.addCheckItem(scrnMsg.srchOptNm);

        scrnMsg.addCheckItem(scrnMsg.rtlWhNm_H1);
        scrnMsg.addCheckItem(scrnMsg.rtlWhDescTxt);
        scrnMsg.addCheckItem(scrnMsg.rtlWhCd);
        scrnMsg.addCheckItem(scrnMsg.rtlWhCatgCd_RS);
        scrnMsg.addCheckItem(scrnMsg.fullPsnNm_O1);
        scrnMsg.addCheckItem(scrnMsg.fullPsnNm_O2);
        scrnMsg.addCheckItem(scrnMsg.invtyAcctCd_IS);
        scrnMsg.addCheckItem(scrnMsg.shipToLocNm);
        scrnMsg.addCheckItem(scrnMsg.rtrnToLocNm);
        scrnMsg.addCheckItem(scrnMsg.telNum);
        scrnMsg.addCheckItem(scrnMsg.procrTpCd_SS);
        scrnMsg.addCheckItem(scrnMsg.rtlWhNm_H2);
        scrnMsg.addCheckItem(scrnMsg.procrTpCd_RS);
        scrnMsg.addCheckItem(scrnMsg.rtlWhNm_H3);
        scrnMsg.addCheckItem(scrnMsg.procrTpCd_ES);
        scrnMsg.addCheckItem(scrnMsg.rtlWhNm_H4);
        scrnMsg.addCheckItem(scrnMsg.plnItemInsrcCd_MS);
        scrnMsg.addCheckItem(scrnMsg.vndLocCd);
        scrnMsg.addCheckItem(scrnMsg.invtyOwnrCd_OS);
        scrnMsg.addCheckItem(scrnMsg.firstRefCmntTxt);
        scrnMsg.addCheckItem(scrnMsg.effFromDt);
        scrnMsg.addCheckItem(scrnMsg.effThruDt);
        // START 2023/03/07 S.Dong [QC#61205, ADD]
        scrnMsg.addCheckItem(scrnMsg.whOwnrCd_H1);
        // END 2023/03/07 S.Dong [QC#61205, ADD]

    }

    /**
     * Table Column Protect
     * @param scrnMsg NMAL6890BMsg
     */
    public static void setTableScreen(NMAL6890BMsg scrnMsg) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID, scrnMsg);

        tblColor.setAlternateRowsBG("A", scrnMsg.A);

        for (int rowIndex = 0; rowIndex < scrnMsg.A.getValidCount(); rowIndex++) {
            scrnMsg.A.no(rowIndex).rtlWhCd_A.setInputProtected(true);
            scrnMsg.A.no(rowIndex).rtlWhNm_A1.setInputProtected(true);
            scrnMsg.A.no(rowIndex).rtlWhDescTxt_A.setInputProtected(true);
            scrnMsg.A.no(rowIndex).rtlWhCatgDescTxt_A.setInputProtected(true);
            scrnMsg.A.no(rowIndex).fullPsnNm_AO.setInputProtected(true);
            scrnMsg.A.no(rowIndex).fullPsnNm_AA.setInputProtected(true);
            scrnMsg.A.no(rowIndex).invtyAcctDescTxt_A.setInputProtected(true);
            scrnMsg.A.no(rowIndex).shipToLocNm_A.setInputProtected(true);
            scrnMsg.A.no(rowIndex).rtrnToLocNm_A.setInputProtected(true);
            scrnMsg.A.no(rowIndex).procrTpDescTxt_AD.setInputProtected(true);
            scrnMsg.A.no(rowIndex).rtlWhNm_A2.setInputProtected(true);
            scrnMsg.A.no(rowIndex).procrTpDescTxt_AR.setInputProtected(true);
            scrnMsg.A.no(rowIndex).rtlWhNm_A3.setInputProtected(true);
            scrnMsg.A.no(rowIndex).procrTpDescTxt_AE.setInputProtected(true);
            scrnMsg.A.no(rowIndex).rtlWhNm_A4.setInputProtected(true);
            scrnMsg.A.no(rowIndex).plnItemInsrcDescTxt_A.setInputProtected(true);
            scrnMsg.A.no(rowIndex).vndLocCd_A.setInputProtected(true);
            scrnMsg.A.no(rowIndex).invtyOwnrDescTxt_A.setInputProtected(true);
            scrnMsg.A.no(rowIndex).firstRefCmntTxt_A.setInputProtected(true);
            scrnMsg.A.no(rowIndex).effFromDt_A.setInputProtected(true);
            scrnMsg.A.no(rowIndex).effThruDt_A.setInputProtected(true);
            scrnMsg.A.no(rowIndex).rtlSwhCd_A.setInputProtected(true);
            //10/21/2022 HITACHI B.Amarsanaa (QC#60608) ADD START
            scrnMsg.A.no(rowIndex).ctyAddr_A.setInputProtected(true);
            //10/21/2022 HITACHI B.Amarsanaa (QC#60608) ADD END
        }
    }

    /**
     * The method explanation: set initial parameter to call popup.
     * @param scrnMsg NMAL6890BMsg
     */
    public static void setInitParamForOwnerPopup(NMAL6890BMsg scrnMsg) {

        scrnMsg.xxTblNm_P1.clear();
        scrnMsg.xxTblCdColNm_P1.clear();
        scrnMsg.xxTblNmColNm_P1.clear();
        scrnMsg.xxTblSortColNm_P1.clear();
        scrnMsg.xxScrNm_P1.clear();
        scrnMsg.xxHdrCdLbNm_P1.clear();
        scrnMsg.xxHdrNmLbNm_P1.clear();
        scrnMsg.xxDtlCdLbNm_P1.clear();
        scrnMsg.xxDtlNmLbNm_P1.clear();
        scrnMsg.fullPsnNm_G1.clear();
        scrnMsg.xxCondCd_P1.clear();
        scrnMsg.xxCondCd_P2.clear();

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblNm_P1, TBL_NM_FOR_OWNER);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblCdColNm_P1, TBL_CD_COL_NM_FOR_OWNER);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblNmColNm_P1, TBL_NM_COL_NM_FOR_OWNER);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblSortColNm_P1, TBL_SORT_COL_NM_FOR_OWNER);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrNm_P1, SCR_NM_FOR_OWNER);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxHdrCdLbNm_P1, HDR_CD_LB_NM_FOR_OWNER);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxHdrNmLbNm_P1, HDR_NM_LB_NM_FOR_OWNER);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDtlCdLbNm_P1, DTL_CD_LB_NM_FOR_OWNER);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDtlNmLbNm_P1, DTL_NM_LB_NM_FOR_OWNER);
    }

    /**
     * The method explanation: set parameter to call popup.
     * @param scrnMsg NMAL6890BMsg
     * @return Object[]
     */
    public static Object[] setParamForOwnerPopup(NMAL6890BMsg scrnMsg) {

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
        params[9] = scrnMsg.xxCondCd_P1;
        params[10] = scrnMsg.fullPsnNm_G1;

        return params;
    }

    /**
     * The method explanation: set parameter to call popup.
     * @param scrnMsg NMAL6890BMsg
     * @return Object[]
     */
    public static Object[] setParamForAltOwnerPopup(NMAL6890BMsg scrnMsg) {

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
        params[9] = scrnMsg.xxCondCd_P2;
        params[10] = scrnMsg.fullPsnNm_G1;

        return params;
    }

}
