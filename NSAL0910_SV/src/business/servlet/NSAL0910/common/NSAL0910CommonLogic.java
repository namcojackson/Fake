/**
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NSAL0910.common;

import static business.servlet.NSAL0910.constant.NSAL0910Constant.*;

import java.util.List;

import parts.common.EZDBMsgArray;

import business.servlet.NSAL0910.NSAL0910BMsg;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CFS_INV_PROC_STS;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/10   Hitachi         K.Kasai         Create          N/A
 *</pre>
 */
public class NSAL0910CommonLogic {

    /**
     * Activate buttons by Function List
     * @param handler S21CommonHandler
     * @param scrnMsg NSAL0910BMsg
     * @param functionList List<String>
     */
    public static void activateButtonsByFuncList(S21CommonHandler handler, NSAL0910BMsg scrnMsg, List<String> functionList) {

        handler.setButtonProperties(BTN_CMN_BTN_1[0], BTN_CMN_BTN_1[1], BTN_CMN_BTN_1[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_3[0], BTN_CMN_BTN_3[1], BTN_CMN_BTN_3[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_4[0], BTN_CMN_BTN_4[1], BTN_CMN_BTN_4[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_5[0], BTN_CMN_BTN_5[1], BTN_CMN_BTN_5[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DOWNLOAD[0], BTN_CMN_DOWNLOAD[1], BTN_CMN_DOWNLOAD[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_7[0], BTN_CMN_BTN_7[1], BTN_CMN_BTN_7[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
        handler.setButtonEnabled(BTN_SEARCH, false);
        if (isInqFunc(functionList)) {
            // Inquiry
            handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 1, null);
            handler.setButtonEnabled(BTN_SEARCH, true);
        }
        if (isUpdFunc(functionList)) {
            // Update
            handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 1, null);
            handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 1, null);
            handler.setButtonEnabled(BTN_SEARCH, true);
            activateButtons(handler, scrnMsg);
        }
    }

    /**
     * Activate screen items By Function List
     * @param handler S21CommonHandler
     * @param functionList List<String>
     * @param scrnMsg NSAL0910BMsg
     */
    public static void activateScreenItemsByFuncList(S21CommonHandler handler, List<String> functionList, NSAL0910BMsg scrnMsg) {

        // Detail
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).dsAcctDlrCd_A.setInputProtected(true);
            scrnMsg.A.no(i).procDt_A.setInputProtected(true);
            scrnMsg.A.no(i).invDt_A.setInputProtected(true);
            scrnMsg.A.no(i).csaInvNum_A.setInputProtected(true);
            scrnMsg.A.no(i).csaContrNum_A.setInputProtected(true);
            scrnMsg.A.no(i).cfsSerNum_A.setInputProtected(true);
            scrnMsg.A.no(i).cfsLeasePblNum_A.setInputProtected(true);
            scrnMsg.A.no(i).invLineNum_A.setInputProtected(true);
            scrnMsg.A.no(i).cfsInvProcStsDescTxt_A.setInputProtected(true);
            scrnMsg.A.no(i).intfcErrMsgTxt_A.setInputProtected(true);
            scrnMsg.A.no(i).bllgPerFromDt_A.setInputProtected(true);
            scrnMsg.A.no(i).bllgPerThruDt_A.setInputProtected(true);
            scrnMsg.A.no(i).ftrFlgOrigTxt_A.setInputProtected(true);
            scrnMsg.A.no(i).bllgMtrLbNm_A.setInputProtected(true);
            scrnMsg.A.no(i).cfsBllgTpTxt_A.setInputProtected(true);
            scrnMsg.A.no(i).cmFlgOrigTxt_A.setInputProtected(true);
            scrnMsg.A.no(i).cfsLeaseNum_A.setInputProtected(true);
            scrnMsg.A.no(i).invTotDealNetAmt_A.setInputProtected(true);
            scrnMsg.A.no(i).endCustNm_A.setInputProtected(true);
            scrnMsg.A.no(i).dsContrNum_A.setInputProtected(true);
            scrnMsg.A.no(i).csaDsContrModTxt_A.setInputProtected(true);
            scrnMsg.A.no(i).dsContrPk_A.setInputProtected(true);
            scrnMsg.A.no(i).usgItemCd_A.setInputProtected(true);
            scrnMsg.A.no(i).svcItemCd_A.setInputProtected(true);
            scrnMsg.A.no(i).ftrItemCd_A.setInputProtected(true);
            scrnMsg.A.no(i).dfrdAcctgRuleCd_A.setInputProtected(true);
            scrnMsg.A.no(i).dfrdInvRuleCd_A.setInputProtected(true);
            scrnMsg.A.no(i).billToCustAcctCd_A.setInputProtected(true);
            scrnMsg.A.no(i).billToLocNum_A.setInputProtected(true);
            scrnMsg.A.no(i).shipToCustAcctCd_A.setInputProtected(true);
            scrnMsg.A.no(i).shipToLocNum_A.setInputProtected(true);
            scrnMsg.A.no(i).svcLineContrDtlPk_A.setInputProtected(true);
            scrnMsg.A.no(i).usgLineContrDtlPk_A.setInputProtected(true);
            scrnMsg.A.no(i).uomCd_A.setInputProtected(true);
            scrnMsg.A.no(i).billToCustCd_A.setInputProtected(true);
            scrnMsg.A.no(i).dsInvTpCd_A.setInputProtected(true);
            scrnMsg.A.no(i).contrVrsnEffFromDt_A.setInputProtected(true);
            scrnMsg.A.no(i).cfsFleetFlg_A.setInputProtected(true);
            scrnMsg.A.no(i).cfsInvBatNum_A.setInputProtected(true);
            scrnMsg.A.no(i).invNum_A.setInputProtected(true);
        }

        if (isUpdFunc(functionList)) {
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                scrnMsg.A.no(i).csaInvNum_A.setInputProtected(false);
                scrnMsg.A.no(i).csaContrNum_A.setInputProtected(false);
                scrnMsg.A.no(i).cfsSerNum_A.setInputProtected(false);
            }
        }
    }

    /**
     *  Control Screen Fields
     * @param scrnMsg NSAL0910BMsg
     */
    public static void controlScreenFields(NSAL0910BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).invTotDealNetAmt_A.setAppFracDigit(2);
            if (CFS_INV_PROC_STS.ERROR_RECORD.equals(scrnMsg.A.no(i).cfsInvProcStsCd_A.getValue())) {
                scrnMsg.A.no(i).csaInvNum_A.setInputProtected(false);
                scrnMsg.A.no(i).csaContrNum_A.setInputProtected(false);
                scrnMsg.A.no(i).cfsSerNum_A.setInputProtected(false);
            } else {
                scrnMsg.A.no(i).csaInvNum_A.setInputProtected(true);
                scrnMsg.A.no(i).csaContrNum_A.setInputProtected(true);
                scrnMsg.A.no(i).cfsSerNum_A.setInputProtected(true);
            }
        }
    }

    /**
     * Activate buttons
     * @param handler S21CommonHandler
     * @param scrnMsg NSAL0910BMsg
     */
    public static void activateButtons(S21CommonHandler handler, NSAL0910BMsg scrnMsg) {

        if (scrnMsg.A.getValidCount() == 0) {
            handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 0, null);
        } else {
            handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 1, null);
        }
    }

    /**
     * Alternate table row color
     * @param scrnMsg NSAL0910BMsg
     */
    public static void alternateTableRowColor(NSAL0910BMsg scrnMsg) {
        S21TableColorController control = new S21TableColorController(SCR_ID_00, scrnMsg);
        try {
            EZDBMsgArray table = (EZDBMsgArray) scrnMsg.getClass().getField("A").get(scrnMsg);
            control.setAlternateRowsBG("A", table);
        } catch (Throwable e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * Focus item
     * @param scrnMsg NSAL0910BMsg
     */
    public static void focusItem(NSAL0910BMsg scrnMsg) {
        if (scrnMsg.A.getValidCount() > 0) {
            scrnMsg.setFocusItem(scrnMsg.A.no(0).csaInvNum_A);
        } else {
            scrnMsg.setFocusItem(scrnMsg.dsAcctDlrCd);
        }
    }

    /**
     * guiAttributeClear
     * @param scrnMsg NSAL0910BMsg
     * @param baseContents String[][]
     */
    public static void clearGUIAttribute(NSAL0910BMsg scrnMsg, String[][] baseContents) {
        S21SortColumnIMGController.clearIMG(SCR_ID_00, scrnMsg, baseContents);
        scrnMsg.clearAllGUIAttribute(SCR_ID_00);
    }

    /**
     * Check input For Search
     * @param scrnMsg NSAL0910BMsg
     */
    public static void addCheckItemForSearch(NSAL0910BMsg scrnMsg) {
        scrnMsg.addCheckItem(scrnMsg.dsAcctDlrCd);
        scrnMsg.addCheckItem(scrnMsg.procDt_FR);
        scrnMsg.addCheckItem(scrnMsg.procDt_TO);
        scrnMsg.addCheckItem(scrnMsg.cfsInvProcStsCd);
        scrnMsg.addCheckItem(scrnMsg.xxCratDt_FR);
        scrnMsg.addCheckItem(scrnMsg.xxCratDt_TO);
        scrnMsg.addCheckItem(scrnMsg.csaInvNum);
        scrnMsg.addCheckItem(scrnMsg.csaContrNum);
        scrnMsg.addCheckItem(scrnMsg.cfsSerNum);
    }

    /**
     * Check input for Submit
     * @param scrnMsg NSAL0910BMsg
     */
    public static void addCheckItemForSubmit(NSAL0910BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).csaInvNum_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).csaContrNum_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).cfsSerNum_A);
        }
    }

    private static boolean isInqFunc(List<String> functionList) {
        return functionList != null && !functionList.isEmpty() && functionList.contains(FUNC_ID_INQ);
    }

    private static boolean isUpdFunc(List<String> functionList) {
        if (functionList != null && !functionList.isEmpty()) {
            if (functionList.contains(FUNC_ID_UPD)) {
                return true;
            }
        }
        return false;
    }
}
