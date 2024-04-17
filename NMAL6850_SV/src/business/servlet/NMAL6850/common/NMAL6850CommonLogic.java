/**
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6850.common;

import static business.servlet.NMAL6850.constant.NMAL6850Constant.BIZ_ID;
import static business.servlet.NMAL6850.constant.NMAL6850Constant.DISPLAY_NM_ARCS_SPLY_NUM;
import static business.servlet.NMAL6850.constant.NMAL6850Constant.DISPLAY_NM_INAC_DT;
import static business.servlet.NMAL6850.constant.NMAL6850Constant.DISPLAY_NM_PMT_TERM_DESC_TXT;
import static business.servlet.NMAL6850.constant.NMAL6850Constant.DISPLAY_NM_PRNT_VND_CD;
import static business.servlet.NMAL6850.constant.NMAL6850Constant.DISPLAY_NM_SPLY_NM;
import static business.servlet.NMAL6850.constant.NMAL6850Constant.DISPLAY_NM_SPLY_TP;
import static business.servlet.NMAL6850.constant.NMAL6850Constant.DISPLAY_NM_TAX_PAYER_REG_NUM;
import static business.servlet.NMAL6850.constant.NMAL6850Constant.DISPLAY_NM_VND_PMT_METH_DESC_TXT;
import static business.servlet.NMAL6850.constant.NMAL6850Constant.SCRN_ID;
import parts.common.EZDMsg;
import business.blap.NMAL6850.NMAL6850CMsg;
import business.servlet.NMAL6850.NMAL6850BMsg;
import business.servlet.NMAL6850.constant.NMAL6850Constant.BTN_APP;
import business.servlet.NMAL6850.constant.NMAL6850Constant.BTN_CMN;
import business.servlet.NMAL6850.constant.NMAL6850Constant.BTN_STATUS;
import business.servlet.NMAL6850.constant.NMAL6850Constant.FUNC_CD;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 * <pre>
 * NMAL6850 Supplier Search.
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/07/24   CITS            M.Ouchi         Create          N/A
 * 2020/02/28   Fujitsu         C.Hara          Update          QC#55971
 * </pre>
 */
public class NMAL6850CommonLogic {

    /**
     * <p>
     * Copies scrnMsg(BMsg) to bizMsg(CMsg) for Search.
     * </p>
     * @param scrnMsg (BMsg)
     * @return bizMsg (CMsg)
     */
    public static NMAL6850CMsg copyScrnMsgToBizMsgForSearch(NMAL6850BMsg scrnMsg) {
        NMAL6850CMsg bizMsg = new NMAL6850CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD.SEARCH.getCode());
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

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
     * <p>
     * Sets the name for the error message.
     * </p>
     * @param scrnMsg scrnMsg (BMsg)
     */
    public static void setNameForMessage(NMAL6850BMsg scrnMsg) {
        // Header.
        scrnMsg.prntVndCd_H.setNameForMessage(DISPLAY_NM_PRNT_VND_CD);
        scrnMsg.splyNm_H.setNameForMessage(DISPLAY_NM_SPLY_NM);
        // 2020/02/28 QC#55971 Mod Start
        // scrnMsg.splyTpCd_H.setNameForMessage(DISPLAY_NM_SPLY_TP);
        scrnMsg.prntVndTpDescTxt_H.setNameForMessage(DISPLAY_NM_SPLY_TP);
        // 2020/02/28 QC#55971 Mod Start
        // Detail.
        for (int rowIndex = 0; rowIndex < scrnMsg.A.getValidCount(); rowIndex++) {
            scrnMsg.A.no(rowIndex).prntVndCd_A.setNameForMessage(DISPLAY_NM_PRNT_VND_CD);
            scrnMsg.A.no(rowIndex).prntVndNm_A.setNameForMessage(DISPLAY_NM_SPLY_NM);
            scrnMsg.A.no(rowIndex).splyTpDescTxt_A.setNameForMessage(DISPLAY_NM_SPLY_TP);
            scrnMsg.A.no(rowIndex).taxPayerRgNum_A.setNameForMessage(DISPLAY_NM_TAX_PAYER_REG_NUM);
            scrnMsg.A.no(rowIndex).inacDt_A.setNameForMessage(DISPLAY_NM_INAC_DT);
            scrnMsg.A.no(rowIndex).vndPmtTermDescTxt_A.setNameForMessage(DISPLAY_NM_PMT_TERM_DESC_TXT);
            scrnMsg.A.no(rowIndex).vndPmtMethDescTxt_A.setNameForMessage(DISPLAY_NM_VND_PMT_METH_DESC_TXT);
            scrnMsg.A.no(rowIndex).arcsSplyNum_A.setNameForMessage(DISPLAY_NM_ARCS_SPLY_NUM);
        }
    }

    /**
     * @param scrnMsg
     */
    public static void setTableScreen(NMAL6850BMsg scrnMsg) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID, scrnMsg);
        tblColor.setAlternateRowsBG("A", scrnMsg.A);

        for (int rowIndex = 0; rowIndex < scrnMsg.A.getValidCount(); rowIndex++) {
            scrnMsg.A.no(rowIndex).prntVndCd_A.setInputProtected(true);
            scrnMsg.A.no(rowIndex).prntVndNm_A.setInputProtected(true);
            scrnMsg.A.no(rowIndex).prntVndTpDescTxt_A.setInputProtected(true);
            scrnMsg.A.no(rowIndex).taxPayerRgNum_A.setInputProtected(true);
            scrnMsg.A.no(rowIndex).inacDt_A.setInputProtected(true);
            scrnMsg.A.no(rowIndex).vndPmtTermDescTxt_A.setInputProtected(true);
            scrnMsg.A.no(rowIndex).vndPmtMethDescTxt_A.setInputProtected(true);
            scrnMsg.A.no(rowIndex).arcsSplyNum_A.setInputProtected(true);
        }
    }

    /**
     * <p>
     * Resets the paging view.
     * </p>
     * @param scrnMsg bMsg
     */
    public static void resetPegingView(NMAL6850BMsg scrnMsg) {
        scrnMsg.xxPageShowFromNum.setValue(1);
        scrnMsg.xxPageShowToNum.clear();
        scrnMsg.xxPageShowOfNum.clear();
    }
}
