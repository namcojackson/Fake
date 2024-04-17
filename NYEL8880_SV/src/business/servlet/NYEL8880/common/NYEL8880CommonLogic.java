/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NYEL8880.common;

import static business.servlet.NYEL8880.constant.NYEL8880Constant.BTN_CMN_CLR;
import static business.servlet.NYEL8880.constant.NYEL8880Constant.BTN_CMN_CLS;
import parts.common.EZDGUIAttribute;
import static business.servlet.NYEL8880.constant.NYEL8880Constant.SCRN_ID_00;
import business.servlet.NYEL8880.NYEL8880BMsg;
import business.servlet.NYEL8880.NYEL8880_ABMsgArray;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * NYEL8850CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/09/08   Fujitsu         Q10627          Create          N/A
 *</pre>
 */
public class NYEL8880CommonLogic {

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
     * Initial Common Button properties.
     * @param handler Event Handler
     */
    public static void initCmnBtnProp(S21CommonHandler handler) {
        // 4th parameter(0:Inactive, 1:Active)
        handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_CLS[0], BTN_CMN_CLS[1], BTN_CMN_CLS[2], 1, null);
    }

    /**
     * @param scrnMsg NYEL8850BMsg
     */
    public static void initRowCtrlProp(NYEL8880BMsg scrnMsg) {
        scrnMsg.clearAllGUIAttribute(SCRN_ID_00);
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * @param scrnMsg NYEL8850BMsg
     * @param scrnAMsgAry NYEL8850_ABMsgArray
     * @param tblId HTML id attribute given to the Table
     */
    public static void setRowsBGWithClear(NYEL8880BMsg scrnMsg, NYEL8880_ABMsgArray scrnAMsgAry, String tblId) {
        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);

        // Color on
        tblColor.setAlternateRowsBG(tblId, scrnAMsgAry, 1);
    }

    private static void setVisibility(NYEL8880BMsg scrnMsg, String id, boolean isVisible) {
        EZDGUIAttribute ctrl = new EZDGUIAttribute(SCRN_ID_00, id);
        ctrl.setVisibility(isVisible);
        scrnMsg.addGUIAttribute(ctrl);
    }
}
