/**
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL1010.common;

import java.util.List;

import parts.common.EZDBStringItem;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NLCL1010.NLCL1010BMsg;
import business.servlet.NLCL1010.NLCL1010Bean;
import business.servlet.NLCL1010.NLCL1010_ABMsg;
import business.servlet.NLCL1010.NLCL1010_PBMsgArray;
import business.servlet.NLCL1010.constant.NLCL1010Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SER_TRX_EVENT;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUIFocusRule;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableFocusRule;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/07/19   Fujitsu         Tozuka          Create          R-WH002
 * 2016/09/29   CITS            S.Tanikawa      Update          QC#2434
 * </pre>
 * 
 * .
 */
public class NLCL1010CommonLogic {

    /** Common button 1. */
    private static final String[] BTN_CMN_SAVE = {"btn1", "CMN_Save", "Save" };

    /** Common button 2. */
    private static final String[] BTN_CMN_SUBMIT = {"btn2", "CMN_Submit", "Submit" };

    /** Common button 3. */
    private static final String[] BTN_CMN_APPLY = {"btn3", "CMN_Apply", "Apply" };

    /** Common button 4. */
    private static final String[] BTN_CMN_APPROVE = {"btn4", "CMN_Approve", "Approve" };

    /** Common button 5. */
    private static final String[] BTN_CMN_REJECT = {"btn5", "CMN_Reject", "Reject" };

    /** Common button 6. */
    private static final String[] BTN_CMN_DOWNLOAD = {"btn6", "CMN_Download", "Download" };

    /** Common button 7. */
    private static final String[] BTN_CMN_DELETE = {"btn7", "CMN_Delete", "Delete" };

    /** Common button 8. */
    private static final String[] BTN_CMN_CLEAR = {"btn8", "CMN_Clear", "Clear" };

    /** Common button 9. */
    private static final String[] BTN_CMN_RESET = {"btn9", "CMN_Reset", "Reset" };

    /** Common button 10. */
    private static final String[] BTN_CMN_RETURN = {"btn10", "CMN_Return", "Return" };

    /**
     * <pre>
     * Details are initialized (sorting sign deletion and BG color control).
     * </pre>
     * @param scrnMsg Screen Msg
     * @param baseContents String[][]
     */
    public static void clearGUIAttribute(NLCL1010BMsg scrnMsg, String[][] baseContents) {

        S21SortColumnIMGController.clearIMG(NLCL1010Constant.SCREEN_ID, scrnMsg, baseContents);
        scrnMsg.clearAllGUIAttribute(NLCL1010Constant.SCREEN_ID);
    }

    /**
     * <pre>
     * The pop up parameter is cleared.
     * </pre>
     * @param scrnMsg Screen Msg
     */
    public static void clearPopUpParam(NLCL1010BMsg scrnMsg) {
        scrnMsg.P.clear();
    }

    /**
     * popup array.
     * @param p NLCL1010_PBMsgArray
     * @param size int
     * @return Object[]
     */
    public static Object[] toArray_popup(NLCL1010_PBMsgArray p, int size) {
        Object[] param = new Object[size];
        for (int i = 0; i < size; i++) {
            param[i] = p.no(i).xxPopPrm;
        }
        return param;
    }

    /**
     * <pre>
     * Screen protecting control [For Initialized State]
     * </pre>
     * 
     * .
     * @param handler EZCommandHandler
     * @param scrnMsg NLCL1010BMsg
     * @param functionList List<String>
     */
    public static void setInitialScrnFieldsState(EZDCommonHandler handler, NLCL1010BMsg scrnMsg, List<String> functionList) {

        // Search Condition
        scrnMsg.serNum_C0.setInputProtected(false);
        scrnMsg.mdseCd_C0.setInputProtected(false);
        setLinkProtected(scrnMsg.mdseCd_CL, false);
        scrnMsg.serTrxEventCd_P0.setInputProtected(false);
        scrnMsg.serErrStsCd_P0.setInputProtected(false);
        scrnMsg.xxFromDt_C0.setInputProtected(false);
        scrnMsg.xxThruDt_C0.setInputProtected(false);
        scrnMsg.fromLocCd_C0.setInputProtected(false);
        setLinkProtected(scrnMsg.fromLocCd_CL, false);
        scrnMsg.toLocCd_C0.setInputProtected(false);
        setLinkProtected(scrnMsg.toLocCd_CL, false);
        scrnMsg.serTrxLtstFlg_C0.setInputProtected(false);
        scrnMsg.xxRsltFlg_C0.setInputProtected(false);
        scrnMsg.serTrxSrcTpCd_P0.setInputProtected(false);
        scrnMsg.serTrxSrcHdrNum_C0.setInputProtected(false);
        scrnMsg.serTrxRefNum_C0.setInputProtected(false);
        scrnMsg.origMdseCd_C0.setInputProtected(false);
        setLinkProtected(scrnMsg.origMdseCd_CL, false);

        // Add Serial Trx
        scrnMsg.serNum_H0.setInputProtected(false);
        scrnMsg.mdseCd_H0.setInputProtected(false);
        setLinkProtected(scrnMsg.mdseCd_HL, false);
        scrnMsg.serTrxEventCd_P1.setInputProtected(false);
        scrnMsg.fromLocCd_H0.setInputProtected(false);
        setLinkProtected(scrnMsg.fromLocCd_HL, false);
        scrnMsg.toLocCd_H0.setInputProtected(false);
        setLinkProtected(scrnMsg.toLocCd_HL, false);
        // 10/15/2015 add start
        if (ZYPCommonFunc.hasValue(scrnMsg.serTrxEventCd_P1)
        &&  SER_TRX_EVENT.STOCK_STATUS_CHANGE.equals(scrnMsg.serTrxEventCd_P1.getValue())) {
            scrnMsg.stkStsCd_F1.setInputProtected(false);
        } else {
            scrnMsg.stkStsCd_F1.setInputProtected(true);
        }
        scrnMsg.stkStsCd_T1.setInputProtected(false);
        // 10/15/2015 add end
        scrnMsg.serTrxSrcTpCd_P1.setInputProtected(false);
        scrnMsg.serTrxSrcHdrNum_H0.setInputProtected(false);
        scrnMsg.serTrxSrcLineNum_H0.setInputProtected(false);
        scrnMsg.serTrxSrcLineSubNum_H0.setInputProtected(false);
        scrnMsg.serTrxRefNum_H0.setInputProtected(false);
        scrnMsg.oldMdseCd_H0.setInputProtected(true);

        // set old merchandise code Protect
        fireAddSerEvtChanged(scrnMsg);

        // Button
        handler.setButtonEnabled(NLCL1010Constant.BTN_SEARCH, true);
        handler.setButtonEnabled(NLCL1010Constant.BTN_ADD, true);

        // Common
        handler.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPLY[0], BTN_CMN_APPLY[1], BTN_CMN_APPLY[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_REJECT[0], BTN_CMN_REJECT[1], BTN_CMN_REJECT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DOWNLOAD[0], BTN_CMN_DOWNLOAD[1], BTN_CMN_DOWNLOAD[2], 1, null);
        handler.setButtonProperties(BTN_CMN_DELETE[0], BTN_CMN_DELETE[1], BTN_CMN_DELETE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);

        // set protect based on authority
        setAuthorityProtect(handler, scrnMsg, functionList);
    }

    /**
     * <pre>
     * Screen protecting control [For Searched State]
     * </pre>
     * 
     * .
     * @param handler EZCommandHandler
     * @param scrnMsg NLCL1010BMsg
     * @param functionList List<String>
     */
    public static void setSearchedScrnFieldsState(EZDCommonHandler handler, NLCL1010BMsg scrnMsg, List<String> functionList) {

        // Search Condition
        scrnMsg.serNum_C0.setInputProtected(false);
        scrnMsg.mdseCd_C0.setInputProtected(false);
        setLinkProtected(scrnMsg.mdseCd_CL, false);
        scrnMsg.serTrxEventCd_P0.setInputProtected(false);
        scrnMsg.serErrStsCd_P0.setInputProtected(false);
        scrnMsg.xxFromDt_C0.setInputProtected(false);
        scrnMsg.xxThruDt_C0.setInputProtected(false);
        scrnMsg.fromLocCd_C0.setInputProtected(false);
        setLinkProtected(scrnMsg.fromLocCd_CL, false);
        scrnMsg.toLocCd_C0.setInputProtected(false);
        setLinkProtected(scrnMsg.toLocCd_CL, false);
        scrnMsg.serTrxLtstFlg_C0.setInputProtected(false);
        scrnMsg.xxRsltFlg_C0.setInputProtected(false);
        scrnMsg.serTrxSrcTpCd_P0.setInputProtected(false);
        scrnMsg.serTrxSrcHdrNum_C0.setInputProtected(false);
        scrnMsg.serTrxRefNum_C0.setInputProtected(false);
        scrnMsg.origMdseCd_C0.setInputProtected(false);
        setLinkProtected(scrnMsg.origMdseCd_CL, false);

        // Add Serial Trx
        scrnMsg.serNum_H0.setInputProtected(false);
        scrnMsg.mdseCd_H0.setInputProtected(false);
        setLinkProtected(scrnMsg.mdseCd_HL, false);
        scrnMsg.serTrxEventCd_P1.setInputProtected(false);
        scrnMsg.fromLocCd_H0.setInputProtected(false);
        setLinkProtected(scrnMsg.fromLocCd_HL, false);
        scrnMsg.toLocCd_H0.setInputProtected(false);
        setLinkProtected(scrnMsg.toLocCd_HL, false);
        // 10/15/2015 add start
        if (ZYPCommonFunc.hasValue(scrnMsg.serTrxEventCd_P1)
        &&  SER_TRX_EVENT.STOCK_STATUS_CHANGE.equals(scrnMsg.serTrxEventCd_P1.getValue())) {
            scrnMsg.stkStsCd_F1.setInputProtected(false);
        } else {
            scrnMsg.stkStsCd_F1.setInputProtected(true);
        }
        scrnMsg.stkStsCd_T1.setInputProtected(false);
        // 10/15/2015 add end
        scrnMsg.serTrxSrcTpCd_P1.setInputProtected(false);
        scrnMsg.serTrxSrcHdrNum_H0.setInputProtected(false);
        scrnMsg.serTrxSrcLineNum_H0.setInputProtected(false);
        scrnMsg.serTrxSrcLineSubNum_H0.setInputProtected(false);
        scrnMsg.serTrxRefNum_H0.setInputProtected(false);
        scrnMsg.oldMdseCd_H0.setInputProtected(true);

        // set old merchandise code Protect
        fireAddSerEvtChanged(scrnMsg);

        // detail
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NLCL1010_ABMsg detail = scrnMsg.A.no(i);
            detail.xxChkBox_A1.setInputProtected(false);
            detail.mdseDescShortTxt_A1.setInputProtected(true); // UPDATE 2016/09/29 QC#2434
            detail.xxScrItem500Txt_A1.setInputProtected(true);
            detail.xxScrItem500Txt_A2.setInputProtected(true);
        }
        // Common
        handler.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 1, null);
        handler.setButtonProperties(BTN_CMN_APPLY[0], BTN_CMN_APPLY[1], BTN_CMN_APPLY[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_REJECT[0], BTN_CMN_REJECT[1], BTN_CMN_REJECT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DOWNLOAD[0], BTN_CMN_DOWNLOAD[1], BTN_CMN_DOWNLOAD[2], 1, null);
        handler.setButtonProperties(BTN_CMN_DELETE[0], BTN_CMN_DELETE[1], BTN_CMN_DELETE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);

        // set protect based on authority
        setAuthorityProtect(handler, scrnMsg, functionList);
    }

    /**
     * Sets the authority protect.
     * @param handler EZDCommonHandler
     * @param scrnMsg NLCL1010BMsg
     * @param functionList List<String>
     */
    private static void setAuthorityProtect(EZDCommonHandler handler, NLCL1010BMsg scrnMsg, List<String> functionList) {
        if (!hasRegisterAuthority(functionList)) {
            // Add
            scrnMsg.serNum_H0.setInputProtected(true);
            scrnMsg.mdseCd_H0.setInputProtected(true);
            setLinkProtected(scrnMsg.mdseCd_HL, true);
            scrnMsg.serTrxEventCd_P1.setInputProtected(true);
            scrnMsg.fromLocCd_H0.setInputProtected(true);
            setLinkProtected(scrnMsg.fromLocCd_HL, true);
            scrnMsg.toLocCd_H0.setInputProtected(true);
            setLinkProtected(scrnMsg.toLocCd_HL, true);
            scrnMsg.stkStsCd_F1.setInputProtected(true); // 10/15/2015 add
            scrnMsg.stkStsCd_T1.setInputProtected(true); // 10/15/2015 add
            scrnMsg.serTrxSrcTpCd_P1.setInputProtected(true);
            scrnMsg.serTrxSrcHdrNum_H0.setInputProtected(true);
            scrnMsg.serTrxSrcLineNum_H0.setInputProtected(true);
            scrnMsg.serTrxSrcLineSubNum_H0.setInputProtected(true);
            scrnMsg.serTrxRefNum_H0.setInputProtected(true);
            scrnMsg.serTrxCmntTxt_H0.setInputProtected(true);

            // set old merchandise code Protect
            fireAddSerEvtChanged(scrnMsg);

            // Button
            handler.setButtonEnabled(NLCL1010Constant.BTN_ADD, false);

            // detail
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                NLCL1010_ABMsg detail = scrnMsg.A.no(i);
                detail.xxChkBox_A1.setInputProtected(true);
            }
            handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 0, null);

        }
    }

    /**
     * Checks for register authority.
     * @param functionList List<String>
     * @return true, if successful
     */
    public static boolean hasRegisterAuthority(List<String> functionList) {
        for (String function : functionList) {
            if (NLCL1010Constant.FUNCTION_UPDATE.equals(function)) {
                return true;
            }
        }
        return false;
    }

    /**
     * set alternate rows background.
     * @param scrnMsg screen message
     */
    public static void setTableAttribute(NLCL1010BMsg scrnMsg) {

        // alternate row background color
        S21TableColorController tblColor = new S21TableColorController(NLCL1010Constant.SCREEN_ID, scrnMsg);
        tblColor.setAlternateRowsBG(NLCL1010Constant.TABLE_A_LEFT, scrnMsg.A);
        tblColor.setAlternateRowsBG(NLCL1010Constant.TABLE_A_RIGHT, scrnMsg.A);

        // Setup Screen detail forcus control
        scrnMsg.clearGUIAttribute(NLCL1010Constant.SCREEN_ID, ZYPGUITableFocusRule.PREFIX_KEY + NLCL1010Bean.A);
        // 
        ZYPGUITableFocusRule tblFocusRule = new ZYPGUITableFocusRule(NLCL1010Constant.SCREEN_ID, NLCL1010Constant.TABLE_A);
        scrnMsg.addGUIAttribute(tblFocusRule);

        ZYPGUIFocusRule fRule = new ZYPGUIFocusRule(NLCL1010Constant.BTN_ADD);
        fRule.setNextId(NLCL1010Bean.xxChkBox_A1 + "#0");

        // set focus rule
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            fRule = new ZYPGUIFocusRule(NLCL1010Bean.xxChkBox_A1 + "#" + i);
            if (i > 0) {
                fRule.setPrevId(NLCL1010Bean.xxLinkAncr_A1 + "#" + (i - 1));
            }
            tblFocusRule.addRule(fRule);

            //
            fRule = new ZYPGUIFocusRule(NLCL1010Bean.xxChkBox_A1 + "#" + i);
            fRule.setNextId(NLCL1010Bean.xxLinkAncr_A1 + "#" + i);
            tblFocusRule.addRule(fRule);

            fRule = new ZYPGUIFocusRule(NLCL1010Bean.xxLinkAncr_A1 + "#" + i);
            fRule.setPrevId(NLCL1010Bean.xxChkBox_A1 + "#" + i);
            tblFocusRule.addRule(fRule);

            fRule = new ZYPGUIFocusRule(NLCL1010Bean.xxLinkAncr_A1 + "#" + i);
            if (i + 1 != scrnMsg.A.length()) {
                fRule.setNextId(NLCL1010Bean.xxChkBox_A1 + "#" + (i + 1));
            }
            tblFocusRule.addRule(fRule);
        }
    }

    /**
     * Fire add ser evt changed.
     * @param scrnMsg NLCL1010BMsg
     */
    public static void fireAddSerEvtChanged(NLCL1010BMsg scrnMsg) {
        // old merchandise code available only when Item Change Stock in selected
        if (SER_TRX_EVENT.ITEM_CHANGE_STOCK_IN.equals(scrnMsg.serTrxEventCd_P1.getValue())) {
            scrnMsg.oldMdseCd_H0.setInputProtected(false);
            setLinkProtected(scrnMsg.oldMdseCd_HL, false);
        } else {
            scrnMsg.oldMdseCd_H0.setInputProtected(true);
            setLinkProtected(scrnMsg.oldMdseCd_HL, true);
        }
    }

    /**
     * enable link.
     * @param item EZDBStringItem
     * @param linkProtected boolean
     */
    private static void setLinkProtected(EZDBStringItem item, boolean linkProtected) {
        if (linkProtected) {
            // kill link
            item.clear();
        } else {
            // enable link
            item.setValue(ZYPConstant.FLG_ON_Y);
        }
    }

    /**
     * check Search item.
     * @param scrnMsg NLCL1010BMsg
     */
    public static void checkSearchItem(NLCL1010BMsg scrnMsg) {
        scrnMsg.addCheckItem(scrnMsg.serNum_C0);
        scrnMsg.addCheckItem(scrnMsg.mdseCd_C0);
        scrnMsg.addCheckItem(scrnMsg.serTrxEventCd_P0);
        scrnMsg.addCheckItem(scrnMsg.serErrStsCd_P0);
        scrnMsg.addCheckItem(scrnMsg.xxFromDt_C0);
        scrnMsg.addCheckItem(scrnMsg.xxThruDt_C0);
        scrnMsg.addCheckItem(scrnMsg.fromLocCd_C0);
        scrnMsg.addCheckItem(scrnMsg.toLocCd_C0);
        scrnMsg.addCheckItem(scrnMsg.serTrxLtstFlg_C0);
        scrnMsg.addCheckItem(scrnMsg.xxRsltFlg_C0);
        scrnMsg.addCheckItem(scrnMsg.serTrxSrcTpCd_P0);
        scrnMsg.addCheckItem(scrnMsg.serTrxSrcHdrNum_C0);
        scrnMsg.addCheckItem(scrnMsg.serTrxRefNum_C0);
        scrnMsg.addCheckItem(scrnMsg.origMdseCd_C0);
    }

    /**
     * Check detail item.
     * @param scrnMsg NLCL1010BMsg
     */
    public static void checkDetailItem(NLCL1010BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NLCL1010_ABMsg detail = scrnMsg.A.no(i);
            scrnMsg.addCheckItem(detail.xxChkBox_A1);
        }
    }

    /**
     * Check search condition.
     * @param scrnMsg NLCL1010BMsg
     * @return false, if error
     */
    public static boolean checkSearchCondition(NLCL1010BMsg scrnMsg) {
        if (!ZYPCommonFunc.hasValue(scrnMsg.serNum_C0) && !ZYPCommonFunc.hasValue(scrnMsg.mdseCd_C0) && !ZYPCommonFunc.hasValue(scrnMsg.serTrxSrcHdrNum_C0) && !ZYPCommonFunc.hasValue(scrnMsg.serTrxRefNum_C0)
                && !ZYPCommonFunc.hasValue(scrnMsg.origMdseCd_C0)) {
            scrnMsg.serNum_C0.setErrorInfo(1, NLCL1010Constant.NLCM0114E);
            scrnMsg.addCheckItem(scrnMsg.serNum_C0);
            scrnMsg.mdseCd_C0.setErrorInfo(1, NLCL1010Constant.NLCM0114E);
            scrnMsg.addCheckItem(scrnMsg.mdseCd_C0);
            scrnMsg.serTrxSrcHdrNum_C0.setErrorInfo(1, NLCL1010Constant.NLCM0114E);
            scrnMsg.addCheckItem(scrnMsg.serTrxSrcHdrNum_C0);
            scrnMsg.serTrxRefNum_C0.setErrorInfo(1, NLCL1010Constant.NLCM0114E);
            scrnMsg.addCheckItem(scrnMsg.serTrxRefNum_C0);
            scrnMsg.origMdseCd_C0.setErrorInfo(1, NLCL1010Constant.NLCM0114E);
            scrnMsg.addCheckItem(scrnMsg.origMdseCd_C0);

            return false;
        }

        return true;
    }

    /**
     * Check add item.
     * @param scrnMsg NLCL1010BMsg
     */
    public static void checkAddItem(NLCL1010BMsg scrnMsg) {
        scrnMsg.addCheckItem(scrnMsg.serNum_H0);
        scrnMsg.addCheckItem(scrnMsg.mdseCd_H0);
        scrnMsg.addCheckItem(scrnMsg.serTrxEventCd_P1);
        scrnMsg.addCheckItem(scrnMsg.fromLocCd_H0);
        scrnMsg.addCheckItem(scrnMsg.toLocCd_H0);
        scrnMsg.addCheckItem(scrnMsg.stkStsCd_F1);           // 10/15/2015 add
        scrnMsg.addCheckItem(scrnMsg.stkStsCd_T1);           // 10/15/2015 add
        scrnMsg.addCheckItem(scrnMsg.serTrxSrcTpCd_P1);
        scrnMsg.addCheckItem(scrnMsg.serTrxSrcHdrNum_H0);
        scrnMsg.addCheckItem(scrnMsg.serTrxSrcLineNum_H0);
        scrnMsg.addCheckItem(scrnMsg.serTrxSrcLineSubNum_H0);
        scrnMsg.addCheckItem(scrnMsg.serTrxRefNum_H0);
        scrnMsg.addCheckItem(scrnMsg.oldMdseCd_H0);
        scrnMsg.addCheckItem(scrnMsg.xxDt10Dt_H0);           // 10/15/2015 add
        scrnMsg.addCheckItem(scrnMsg.xxSvcFromHourMnTxt_H0); // 10/15/2015 add
        scrnMsg.addCheckItem(scrnMsg.serTrxCmntTxt_H0);
    }

    /**
     * Clear add warning variables.
     * @param scrnMsg NLCL1010BMsg
     */
    public static void clearAddWarningVariables(NLCL1010BMsg scrnMsg) {
        scrnMsg.serNum_HS.clear();
        scrnMsg.serTrxSrcHdrNum_HS.clear();
        scrnMsg.serTrxRefNum_HS.clear();
    }
}
