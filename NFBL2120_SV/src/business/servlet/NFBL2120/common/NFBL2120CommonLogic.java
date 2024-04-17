/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFBL2120.common;

import static business.servlet.NFBL2120.constant.NFBL2120Constant.BTN_CMN_CLR;
import static business.servlet.NFBL2120.constant.NFBL2120Constant.BTN_CMN_CLS;
import static business.servlet.NFBL2120.constant.NFBL2120Constant.BTN_SEARCH;
import static business.servlet.NFBL2120.constant.NFBL2120Constant.BTN_SHOW_DOC;
import static business.servlet.NFBL2120.constant.NFBL2120Constant.SCRN_ID_00;

import business.servlet.NFBL2120.NFBL2120BMsg;
import business.servlet.NFBL2120.NFBL2120_ABMsgArray;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * NFBL2120CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/09/14   Fujitsu         W.Honda         Create          N/A
 *</pre>
 */
public class NFBL2120CommonLogic {

    /**
     * Initial Common Button properties.
     * 
     * @param handler Event Handler
     */
    public static void initCmnBtnProp(S21CommonHandler handler) {
        // 4th parameter(0:Inactive, 1:Active)
        handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_CLS[0], BTN_CMN_CLS[1], BTN_CMN_CLS[2], 1, null);
        handler.setButtonProperties(BTN_SEARCH[0], BTN_SEARCH[1], BTN_SEARCH[2], 1, null);
        handler.setButtonProperties(BTN_SHOW_DOC[0], BTN_SHOW_DOC[1], BTN_SHOW_DOC[2], 1, null);
    }

    /**
     * <pre>
     * The initial state of the screen item is set.
     * </pre>
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL7190BMsg
     */
    public static final void controlScreenFields(S21CommonHandler handler, NFBL2120BMsg scrnMsg) {
        if (scrnMsg.A.getValidCount() == 0) {
            setBtnProp(handler, BTN_SHOW_DOC, 0);
        } else {
            setBtnProp(handler, BTN_SHOW_DOC, 1);
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                scrnMsg.A.no(i).xxScrDply_A1.setInputProtected(true);
            }
        }
    }

    /**
     * Set Common Button properties.
     * 
     * @param handler Event Handler
     * @param btnProp Button Properties in Constant class
     * @param sts Status (0:Inactive, 1:Active)
     */
    public static void setBtnProp(S21CommonHandler handler, String[] btnProp, int sts) {
        handler.setButtonProperties(btnProp[0], btnProp[1], btnProp[2], sts, null);
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * 
     * @param scrnMsg     NFBL2120BMsg
     * @param scrnAMsgAry NFBL2120_ABMsgArray
     * @param tblId       HTML id attribute given to the Table
     */
    public static void setRowsBGWithClear(NFBL2120BMsg scrnMsg, NFBL2120_ABMsgArray scrnAMsgAry, String tblId) {
        setRowsBGWithClear(scrnMsg, scrnAMsgAry, tblId, 1);
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * 
     * @param scrnMsg     NFBL2120BMsg
     * @param scrnAMsgAry NFBL2120_ABMsgArray
     * @param tblId       HTML id attribute given to the Table
     * @param grpRows     color reversal switch lines
     */
    public static void setRowsBGWithClear(NFBL2120BMsg scrnMsg, NFBL2120_ABMsgArray scrnAMsgAry, String tblId, int grpRows) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);

        // Color on
        tblColor.setAlternateRowsBG(tblId, scrnAMsgAry, grpRows);
    }

    /**
     * Table has an id attribute of the argument row background color back to the initial color. 
     * 
     * @param scrnMsg     NFBL2120BMsg
     * @param scrnAMsgAry NFBL2120_ABMsgArray
     * @param tblId       HTML id attribute given to the Table
     */
    public static void clearRowsBG(NFBL2120BMsg scrnMsg, NFBL2120_ABMsgArray scrnAMsgAry, String tblId) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);
    }
}
