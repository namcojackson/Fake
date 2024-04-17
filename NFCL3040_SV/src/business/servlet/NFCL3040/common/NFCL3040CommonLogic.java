package business.servlet.NFCL3040.common;

import static business.servlet.NFCL3040.constant.NFCL3040Constant.*;

import parts.common.EZDBMsgArray;
import parts.common.EZDGUIAttribute;

import parts.servletcommon.EZDCommonHandler;
import business.servlet.NFCL3040.NFCL3040BMsg;
import business.servlet.NFCL3040.NFCL3040_ABMsgArray;

import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * Batch Inquiry
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/06/13   Hitachi         J.Kim           Create          QC#25695
 *</pre>
 */
public class NFCL3040CommonLogic {

    /**
     * Initialize
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NFCL3010BMsg
     */
    public static void initialize(EZDCommonHandler handler, NFCL3040BMsg scrnMsg) {

        NFCL3040_ABMsgArray lineMsgArray = scrnMsg.A;

        scrnMsg.clearAllGUIAttribute(SCRN_ID_00);
        scrnMsg.setInputProtected(false);
        
        handler.setButtonProperties("btn1", "", "Save", 0, null);
        handler.setButtonProperties("btn2", "", "Submit", 0, null);
        handler.setButtonProperties("btn3", "", "Apply", 0, null);
        handler.setButtonProperties("btn4", "", "Approve", 0, null);
        handler.setButtonProperties("btn5", "", "Reject", 0, null);
        if (lineMsgArray.getValidCount() > 0) {
            handler.setButtonProperties("btn6", "CMN_Download", "Download", 1, null);
        } else {
            handler.setButtonProperties("btn6", "CMN_Download", "Download", 0, null);
        }

        handler.setButtonProperties("btn7", "", "Delete", 0, null);
        handler.setButtonProperties("btn8", "CMN_Reset", "Clear", 1, null);
        handler.setButtonProperties("btn9", "", "Reset", 0, null);
        handler.setButtonProperties("btn10", "CMN_Return", "Return", 1, null);

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).arBatRcptNm.setInputProtected(true);
            scrnMsg.A.no(i).arRcptSrcNm.setInputProtected(true);
            scrnMsg.A.no(i).arBatRcptDt.setInputProtected(true);
            scrnMsg.A.no(i).arBatRcptStsNm.setInputProtected(true);
            scrnMsg.A.no(i).arBatRcptCnt.setInputProtected(true);
            scrnMsg.A.no(i).arBatRcptAmt.setInputProtected(true);
            scrnMsg.A.no(i).arLockBoxFileNm.setInputProtected(true);
            scrnMsg.A.no(i).arLockBoxNm.setInputProtected(true);
            scrnMsg.A.no(i).arLockBoxBatNum.setInputProtected(true);
        }
    }

    /**
     * Clear Screen background color
     * @param scrnMsg NFCL3040BMsg
     */
    public static void clearScrnBackgroundColor(NFCL3040BMsg scrnMsg) {
        scrnMsg.clearAllGUIAttribute(SCRN_ID_00);
    }

    /**
     * Set Item Visibility
     * @param scrnMsg NFCL3040BMsg
     * @param itemNm String
     * @param visibility boolean
     */
    public static void setItemVisibility(NFCL3040BMsg scrnMsg, String itemNm, boolean visibility) {
        EZDGUIAttribute itemVisibility = new EZDGUIAttribute(SCRN_ID_00, itemNm);
        itemVisibility.setVisibility(visibility);
        scrnMsg.addGUIAttribute(itemVisibility);
    }

    /**
     * Initial Common Button properties.
     * @param handler Event Handler
     */
    public static void initCmnBtnProp(EZDCommonHandler handler) {

        handler.setButtonProperties(BTN_CMN_SAV[0], BTN_CMN_SAV[1], BTN_CMN_SAV[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APL[0], BTN_CMN_APL[1], BTN_CMN_APL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APR[0], BTN_CMN_APR[1], BTN_CMN_APR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RJT[0], BTN_CMN_RJT[1], BTN_CMN_RJT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DWL[0], BTN_CMN_DWL[1], BTN_CMN_DWL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DEL[0], BTN_CMN_DEL[1], BTN_CMN_DEL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RST[0], BTN_CMN_RST[1], BTN_CMN_RST[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RTN[0], BTN_CMN_RTN[1], BTN_CMN_RTN[2], 1, null);

    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * @param scrnMsg NFCL3040BMsg
     * @param bMsgArray EZDBMsgArray
     * @param tblId HTML id attribute given to the Table
     * @param grpRows color reversal switch lines
     */
    public static void setRowsBGWithClear(NFCL3040BMsg scrnMsg, EZDBMsgArray bMsgArray, String tblId) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);
        tblColor.clearRowsBG(TABLE_A, bMsgArray);
        if (bMsgArray.getValidCount() > 0) {
            tblColor.setAlternateRowsBG(tblId, bMsgArray);
        }
    }

    /**
     * Table has an id attribute of the argument row background color
     * back to the initial color.
     * @param scrnMsg NFCL3040BMsg
     * @param scrnAMsgAry NFCL3040_ABMsgArray
     * @param tblId HTML id attribute given to the Table
     */
    public static void clearRowsBG(NFCL3040BMsg scrnMsg, NFCL3040_ABMsgArray scrnAMsgAry, String tblId) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);
        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);
    }
}
