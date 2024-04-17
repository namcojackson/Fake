/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1430.common;

import static business.servlet.NPAL1430.constant.NPAL1430Constant.BTN_ADD_LINE;
import static business.servlet.NPAL1430.constant.NPAL1430Constant.BTN_CMN_BTN_1;
import static business.servlet.NPAL1430.constant.NPAL1430Constant.BTN_CMN_BTN_10;
import static business.servlet.NPAL1430.constant.NPAL1430Constant.BTN_CMN_BTN_2;
import static business.servlet.NPAL1430.constant.NPAL1430Constant.BTN_CMN_BTN_3;
import static business.servlet.NPAL1430.constant.NPAL1430Constant.BTN_CMN_BTN_4;
import static business.servlet.NPAL1430.constant.NPAL1430Constant.BTN_CMN_BTN_5;
import static business.servlet.NPAL1430.constant.NPAL1430Constant.BTN_CMN_BTN_6;
import static business.servlet.NPAL1430.constant.NPAL1430Constant.BTN_CMN_BTN_7;
import static business.servlet.NPAL1430.constant.NPAL1430Constant.BTN_CMN_BTN_8;
import static business.servlet.NPAL1430.constant.NPAL1430Constant.BTN_CMN_BTN_9;
import static business.servlet.NPAL1430.constant.NPAL1430Constant.BTN_DELETE_LINE;
import static business.servlet.NPAL1430.constant.NPAL1430Constant.BTN_SEARCH;
import static business.servlet.NPAL1430.constant.NPAL1430Constant.BTN_SEARCH_LINE_ITEM_NAME;
import static business.servlet.NPAL1430.constant.NPAL1430Constant.DTL_CD_LB_NM_FOR_MKT_MDL;
import static business.servlet.NPAL1430.constant.NPAL1430Constant.DTL_NM_LB_NM_FOR_MKT_MDL;
import static business.servlet.NPAL1430.constant.NPAL1430Constant.EVENT_NM_CMN_DELETE_LINE;
import static business.servlet.NPAL1430.constant.NPAL1430Constant.FUNC_EDIT;
import static business.servlet.NPAL1430.constant.NPAL1430Constant.HDR_CD_LB_NM_FOR_MKT_MDL;
import static business.servlet.NPAL1430.constant.NPAL1430Constant.HDR_NM_LB_NM_FOR_MKT_MDL;
import static business.servlet.NPAL1430.constant.NPAL1430Constant.MODE_CODE_10;
import static business.servlet.NPAL1430.constant.NPAL1430Constant.NPAM0087E;
import static business.servlet.NPAL1430.constant.NPAL1430Constant.NPAM1216E;
import static business.servlet.NPAL1430.constant.NPAL1430Constant.NPAM1234E;
import static business.servlet.NPAL1430.constant.NPAL1430Constant.NPAM1351E;
import static business.servlet.NPAL1430.constant.NPAL1430Constant.SCRN_ID;
import static business.servlet.NPAL1430.constant.NPAL1430Constant.SCR_NM_FOR_MKT_MDL;
import static business.servlet.NPAL1430.constant.NPAL1430Constant.TBL_CD_COL_NM_FOR_MKT_MDL;
import static business.servlet.NPAL1430.constant.NPAL1430Constant.TBL_NM_COL_NM_FOR_MKT_MDL;
import static business.servlet.NPAL1430.constant.NPAL1430Constant.TBL_NM_FOR_MKT_MDL;
import static business.servlet.NPAL1430.constant.NPAL1430Constant.TBL_SORT_COL_NM_FOR_MKT_MDL;
import static java.math.BigDecimal.ONE;

import java.math.BigDecimal;
import java.util.List;

import parts.common.EZDFieldErrorException;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NPAL1430.NPAL1430BMsg;
import business.servlet.NPAL1430.NPAL1430_ABMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 * <pre>
 * Business ID : NPAL1430 Reman Standard Parts Setup
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/18/2016   CITS            S.Tanikawa      Create          N/A
 * 08/28/2016   CITS            T.Gotoda        Update          QC#13404
 * 09/21/2016   CITS            Y.Fujii         Update          QC#13403
 *</pre>
 */

public class NPAL1430CommonLogic {

    /**
     * The method explanation: The display control of the screen item
     * for Initialized screen
     * @param handler EZDCommonHandler
     * @param scrnMsg NPAL1430BMsg
     * @param functionList List<String>S
     */
    public static void ctrlScrnItemDispInit(EZDCommonHandler handler, NPAL1430BMsg scrnMsg, List<String> functionList) {

        scrnMsg.clear();
        ZYPTableUtil.clear(scrnMsg.A);

        // column input protection
        // true : block entry
        // false : input possible
        // Search Condition
        scrnMsg.rmnfMdlId_AC.setInputProtected(false);
        scrnMsg.t_MdlNm.setInputProtected(false);
        scrnMsg.rmnfMdlStdPrtDescTxt.setInputProtected(false);
        scrnMsg.lastUpdDt.setInputProtected(true);

        // button activation
        handler.setButtonEnabled(BTN_SEARCH, true);

        // button inactivation
        handler.setButtonEnabled(BTN_DELETE_LINE, false);
        handler.setButtonEnabled(BTN_ADD_LINE, false);

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
    }

    /**
     * Sets the authority protect.
     * @param handler EZDCommonHandler
     * @param scrnMsg NPAL1430BMsg
     * @param functionList List<String>
     */
    private static void setAuthorityProtect(EZDCommonHandler handler, NPAL1430BMsg scrnMsg, List<String> functionList) {

        if (hasRegisterAuthority(functionList)) {
            handler.setButtonEnabled(BTN_ADD_LINE, true);
            // common button protection
            // 0 : inactive
            // 1 : active
            handler.setButtonProperties(BTN_CMN_BTN_2[0], BTN_CMN_BTN_2[1], BTN_CMN_BTN_2[2], 1, null);
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
     * Sets the name for the error message.
     * @param scrnMsg NPAL1430BMsg
     */
    public static void setNameForMessage(NPAL1430BMsg scrnMsg) {

        // Search Condition
        scrnMsg.t_MdlNm.setNameForMessage("Model");
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).mdseCd_A1.setNameForMessage("Item Number");
            scrnMsg.A.no(i).rmnfReqQty_A1.setNameForMessage("Qty");
        }
    }

    /**
     * setMdlInfoParam
     * @param scrnMsg NPAL1430BMsg
     * @return Model Search Popup Parameter
     */
    public static Object[] setMdlInfoParam(NPAL1430BMsg scrnMsg) {

        // Parameter Clear
        scrnMsg.P.clear();

        // Parameter Copy
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).xxPopPrm, TBL_NM_FOR_MKT_MDL);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(1).xxPopPrm, TBL_CD_COL_NM_FOR_MKT_MDL);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(2).xxPopPrm, TBL_NM_COL_NM_FOR_MKT_MDL);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(3).xxPopPrm, TBL_SORT_COL_NM_FOR_MKT_MDL);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(4).xxPopPrm, SCR_NM_FOR_MKT_MDL);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(5).xxPopPrm, HDR_CD_LB_NM_FOR_MKT_MDL);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(6).xxPopPrm, HDR_NM_LB_NM_FOR_MKT_MDL);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(7).xxPopPrm, DTL_CD_LB_NM_FOR_MKT_MDL);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(8).xxPopPrm, DTL_NM_LB_NM_FOR_MKT_MDL);

        // Parameter Set
        Object[] param = new Object[11];

        return param;
    }

    /**
     * Table Column Protect
     * @param scrnMsg NPAL1430BMsg
     * @param handler EZDCommonHandler
     */
    public static void setTableScreen(NPAL1430BMsg scrnMsg, EZDCommonHandler handler, List<String> functionList, String eventNm) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID, scrnMsg);
        tblColor.setAlternateRowsBG("A", scrnMsg.A);

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (scrnMsg.A.no(i).xxNewRowNum_A1.getValue().compareTo(ONE) == 0) {
                // New lines cannot change Item Number.
                scrnMsg.A.no(i).mdseCd_A1.setInputProtected(false);
                handler.setButtonEnabled(BTN_SEARCH_LINE_ITEM_NAME, i, true);
            } else {
                // Exist lines
                scrnMsg.A.no(i).mdseCd_A1.setInputProtected(true);
                handler.setButtonEnabled(BTN_SEARCH_LINE_ITEM_NAME, i, false);
            }

            scrnMsg.A.no(i).mdseDescShortTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).rmnfReqQty_A1.setInputProtected(false);
        }

        if (scrnMsg.A.getValidCount() > 0) {
            scrnMsg.rmnfMdlId_AC.setInputProtected(true);
            scrnMsg.t_MdlNm.setInputProtected(true);

            handler.setButtonEnabled(BTN_SEARCH, false);

            if (hasRegisterAuthority(functionList)) {
                handler.setButtonEnabled(BTN_DELETE_LINE, true);
            }
        } else {

            handler.setButtonEnabled(BTN_SEARCH, true);

            if (EVENT_NM_CMN_DELETE_LINE.equals(eventNm)) {

                scrnMsg.rmnfMdlId_AC.setInputProtected(true);
                scrnMsg.t_MdlNm.setInputProtected(true);

            } else {

                scrnMsg.rmnfMdlId_AC.setInputProtected(false);
                scrnMsg.t_MdlNm.setInputProtected(false);

            }

            handler.setButtonEnabled(BTN_DELETE_LINE, false);
        }

        // set protect based on authority
        setAuthorityProtect(handler, scrnMsg, functionList);
    }

    /**
     * setMdseInfoParam
     * @param scrnMsg NPAL1430BMsg
     * @param selectedNum int
     * @return Item Master Search Popup Parameter
     */
    public static Object[] setMdseInfoParam(NPAL1430BMsg scrnMsg, int selectedNum) {

        // Parameter Clear
        scrnMsg.P.clear();

        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).xxPopPrm, scrnMsg.A.no(selectedNum).mdseCd_A1);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(9).xxPopPrm, MODE_CODE_10);

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
     * checkSelectedLine
     * @param scrnMsg NPAL1430BMsg
     */
    public static void checkSelectedLine(NPAL1430BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NPAL1430_ABMsg abMsg = scrnMsg.A.no(i);
            if (ZYPConstant.CHKBOX_ON_Y.equals(abMsg.xxChkBox_A1.getValue())) {
                return;
            }
        }

        scrnMsg.setMessageInfo(NPAM1216E);
        throw new EZDFieldErrorException();
    }

    /**
     * checkSubmittedItem
     * @param scrnMsg NPAL1430BMsg
     */
    public static void checkSubmittedItem(NPAL1430BMsg scrnMsg) {

        // check Line Exists
        // if (scrnMsg.A.getValidCount() == 0) {
        // scrnMsg.setMessageInfo(NPAM1351E);
        // throw new EZDFieldErrorException();
        // }

        // addCheck
        addCheckSubmittedItem(scrnMsg);

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            // Check Qty ( not integer or negative )
            BigDecimal inputQty = scrnMsg.A.no(i).rmnfReqQty_A1.getValue();
            if (!inputQty.equals(BigDecimal.valueOf(inputQty.longValue())) || inputQty.compareTo(BigDecimal.ZERO) <= 0) {
                scrnMsg.A.no(i).rmnfReqQty_A1.setErrorInfo(1, NPAM1234E, new String[] {"Qty" });
            }

            // Check duplicated
            for (int j = 0; j < scrnMsg.A.getValidCount(); j++) {
                if (i != j && scrnMsg.A.no(i).mdseCd_A1.getValue().equals(scrnMsg.A.no(j).mdseCd_A1.getValue())) {
                    scrnMsg.A.no(i).mdseCd_A1.setErrorInfo(1, NPAM0087E, new String[] {});
                    scrnMsg.A.no(j).mdseCd_A1.setErrorInfo(1, NPAM0087E, new String[] {});
                }
            }
        }

        // addCheck
        addCheckSubmittedItem(scrnMsg);

    }

    /**
     * addCheckSubmittedItem
     * @param scrnMsg NPAL1430BMsg
     */
    public static void addCheckSubmittedItem(NPAL1430BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).mdseCd_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).rmnfReqQty_A1);
        }
        scrnMsg.putErrorScreen();
    }
}
