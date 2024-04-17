/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1810.common;

import static business.servlet.NWAL1810.constant.NWAL1810Constant.BTN_CMN_CLR;
import static business.servlet.NWAL1810.constant.NWAL1810Constant.BTN_CMN_CLS;
import static business.servlet.NWAL1810.constant.NWAL1810Constant.NZZM0012E;
import static business.servlet.NWAL1810.constant.NWAL1810Constant.SCRN_ID_00;
import parts.common.EZDBStringItem;
import business.servlet.NWAL1810.NWAL1810BMsg;
import business.servlet.NWAL1810.NWAL1810_ABMsgArray;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * NWAL1810CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/25   Fujitsu         S.Ohki          Create          N/A
 * 2022/10/20   Hitachi         H.Watanabe      Update          QC#60258
 *</pre>
 */
public class NWAL1810CommonLogic {

    /**
     * Initial Common Button properties.
     * @param handler Event Handler
     */
    public static void initCmnBtnProp(S21CommonHandler handler) {

        // 2022/10/20 QC#60258 Mod Start
//        handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 1, null);
        // 2022/10/20 QC#60258 Mod End
        handler.setButtonProperties(BTN_CMN_CLS[0], BTN_CMN_CLS[1], BTN_CMN_CLS[2], 1, null);
    }

    /**
     * Set Common Button properties.
     * @param handler Event Handler
     * @param btnProp Button Properties in Constant class
     * @param sts Status (0:Inactive, 1:Active)
     */
    public static void setCmnBtnProp(S21CommonHandler handler, String[] btnProp, int sts) {
        handler.setButtonProperties(btnProp[0], btnProp[1], btnProp[2], sts, null);
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * @param scrnMsg NWAL1810BMsg
     * @param scrnAMsgAry NWAL1810_ABMsgArray
     * @param tblId HTML id attribute given to the Table
     */
    public static void setRowsBGWithClear(NWAL1810BMsg scrnMsg, NWAL1810_ABMsgArray scrnAMsgAry, String tblId) {
        setRowsBGWithClear(scrnMsg, scrnAMsgAry, tblId, 1);
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * @param scrnMsg NWAL1810BMsg
     * @param scrnAMsgAry NWAL1810_ABMsgArray
     * @param tblId HTML id attribute given to the Table
     * @param grpRows color reversal switch lines
     */
    public static void setRowsBGWithClear(NWAL1810BMsg scrnMsg, NWAL1810_ABMsgArray scrnAMsgAry, String tblId, int grpRows) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);

        // Color on
        tblColor.setAlternateRowsBG(tblId, scrnAMsgAry, grpRows);
    }

    /**
     * Table has an id attribute of the argument row background color
     * back to the initial color.
     * @param scrnMsg NWAL1810BMsg
     * @param scrnAMsgAry NWAL1810_ABMsgArray
     * @param tblId HTML id attribute given to the Table
     */
    public static void clearRowsBG(NWAL1810BMsg scrnMsg, NWAL1810_ABMsgArray scrnAMsgAry, String tblId) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);
    }

    /**
     * Page Item Clear.
     * @param scrnMsg NWAL1810BMsg
     */
    public static void pageItemClear(NWAL1810BMsg scrnMsg) {

        // set values to items of pagenation for next page pagenation
        scrnMsg.A.clear();
        scrnMsg.A.setValidCount(0);
        scrnMsg.xxPageShowFromNum_A.clear();
        scrnMsg.xxPageShowToNum_A.clear();
        scrnMsg.xxPageShowOfNum_A.clear();

        scrnMsg.B.clear();
        scrnMsg.B.setValidCount(0);
        scrnMsg.xxPageShowFromNum_B.clear();
        scrnMsg.xxPageShowToNum_B.clear();
        scrnMsg.xxPageShowOfNum_B.clear();
    }

    /**
     * Set Tab Protect.
     * @param scrnMsg NWAL1810BMsg
     * @param item EZDBStringItem
     */
    public static void setTabProtect(NWAL1810BMsg scrnMsg, EZDBStringItem item) {

        scrnMsg.setMessageInfo(NZZM0012E, new String[] {item.getNameForMessage() });
        scrnMsg.xxTabProt_DE.setInputProtected(true);   // Detail
        scrnMsg.xxTabProt_SU.setInputProtected(true);   // Summary
    }
    // 2022/10/20 QC#60258 Add Start
    /**
     * Search Validation check
     * @param scrnMsg NWAL1810BMsg
     */
    public static void searchValidCheck(NWAL1810BMsg scrnMsg) {

        scrnMsg.addCheckItem(scrnMsg.xxFromDt);
        scrnMsg.addCheckItem(scrnMsg.xxThruDt);
    }
    // 2022/10/20 QC#60258 Add End
}
