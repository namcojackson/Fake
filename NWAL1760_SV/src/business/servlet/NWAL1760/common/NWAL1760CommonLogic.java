/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1760.common;

import static business.servlet.NWAL1760.constant.NWAL1760Constant.SCRN_ID_00;
import static business.servlet.NWAL1760.constant.NWAL1760Constant.BTN_CMN_CLR;
import static business.servlet.NWAL1760.constant.NWAL1760Constant.BTN_CMN_CLS;
import business.servlet.NWAL1760.NWAL1760BMsg;
import business.servlet.NWAL1760.NWAL1760_ABMsgArray;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * Price List Popup
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/25   Fujitsu         A.Suda          Create          N/A
 *</pre>
 */
public class NWAL1760CommonLogic {

    /**
     * Initial Common Button properties.
     * @param handler Event Handler
     */
    public static void initCmnBtnProp(S21CommonHandler handler) {

        // 4th parameter(0:Inactive, 1:Active)
        handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLS[0], BTN_CMN_CLS[1], BTN_CMN_CLS[2], 1, null);

    }

    /**
     * Initial Common Button properties.
     * @param scrnMsg NWAL1760BMsg
     */
    public static void setGuiAttr(NWAL1760BMsg scrnMsg) {

        scrnMsg.csmpNum.setInputProtected(true);
        scrnMsg.custAcctNum.setInputProtected(true);
        scrnMsg.dlrRefNum.setInputProtected(true);
        scrnMsg.dsAcctNm.setInputProtected(true);
        scrnMsg.dsOrdCatgDescTxt.setInputProtected(true);
        scrnMsg.dsOrdTpDescTxt.setInputProtected(true);

    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * @param scrnMsg NWAL1760BMsg
     * @param scrnAMsgAry NWAL1760_ABMsgArray
     * @param tblId HTML id attribute given to the Table
     */
    public static void setRowsBGWithClear(NWAL1760BMsg scrnMsg, NWAL1760_ABMsgArray scrnAMsgAry, String tblId) {
        setRowsBGWithClear(scrnMsg, scrnAMsgAry, tblId, 1);
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * @param scrnMsg NWAL1760BMsg
     * @param scrnAMsgAry NWAL1760_ABMsgArray
     * @param tblId HTML id attribute given to the Table
     * @param grpRows color reversal switch lines
     */
    public static void setRowsBGWithClear(NWAL1760BMsg scrnMsg, NWAL1760_ABMsgArray scrnAMsgAry, String tblId, int grpRows) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);

        // Color on
        tblColor.setAlternateRowsBG(tblId, scrnAMsgAry, grpRows);
    }

    /**
     * Table has an id attribute of the argument row background color
     * back to the initial color.
     * @param scrnMsg NWAL1760BMsg
     * @param scrnAMsgAry NWAL1760_ABMsgArray
     * @param tblId HTML id attribute given to the Table
     */
    public static void clearRowsBG(NWAL1760BMsg scrnMsg, NWAL1760_ABMsgArray scrnAMsgAry, String tblId) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);
    }
}
