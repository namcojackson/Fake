/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6160.common;

import static business.servlet.NMAL6160.constant.NMAL6160Constant.BTN_CMN_CLR;
import static business.servlet.NMAL6160.constant.NMAL6160Constant.BTN_CMN_CLS;
import static business.servlet.NMAL6160.constant.NMAL6160Constant.SCRN_ID_00;
import static business.servlet.NMAL6160.constant.NMAL6160Constant.ZZM9000E;
import parts.common.EZDBStringItem;
import business.servlet.NMAL6160.NMAL6160BMsg;
import business.servlet.NMAL6160.NMAL6160_ABMsgArray;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * Multi Candinate Popup
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/15   Fujitsu         M.Ohno          Create          N/A
 *</pre>
 */
public class NMAL6160CommonLogic {

    /**
     * Initial Common Button properties.
     * @param handler Event Handler
     */
    public static void initCmnBtnProp(S21CommonHandler handler) {
        handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_CLS[0], BTN_CMN_CLS[1], BTN_CMN_CLS[2], 1, null);

    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * @param scrnMsg NMAL6160BMsg
     * @param scrnAMsgAry NMAL6160_ABMsgArray
     * @param tblId HTML id attribute given to the Table
     */
    public static void setRowsBGWithClear(NMAL6160BMsg scrnMsg, NMAL6160_ABMsgArray scrnAMsgAry, String tblId) {
        setRowsBGWithClear(scrnMsg, scrnAMsgAry, tblId, 1);
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * @param scrnMsg NMAL6160BMsg
     * @param scrnAMsgAry NMAL6160_ABMsgArray
     * @param tblId HTML id attribute given to the Table
     * @param grpRows color reversal switch lines
     */
    public static void setRowsBGWithClear(NMAL6160BMsg scrnMsg, NMAL6160_ABMsgArray scrnAMsgAry, String tblId, int grpRows) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);

        // Color on
        tblColor.setAlternateRowsBG(tblId, scrnAMsgAry, grpRows);
    }

    /**
     * Table has an id attribute of the argument row background color
     * back to the initial color.
     * @param scrnMsg NMAL6160BMsg
     * @param scrnAMsgAry NMAL6160_ABMsgArray
     * @param tblId HTML id attribute given to the Table
     */
    public static void clearRowsBG(NMAL6160BMsg scrnMsg, NMAL6160_ABMsgArray scrnAMsgAry, String tblId) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);
    }

    /**
     * setItemInValueErrorInfo
     * @param param Object
     * @param scrnMsg NMAL6160BMsg
     * @param item EZDBStringItem
     */
    public static final void setItemInValueErrorInfo(EZDBStringItem item, Object param, NMAL6160BMsg scrnMsg) {

        if (ZYPCommonFunc.hasValue((EZDBStringItem) param)) {
            ZYPEZDItemValueSetter.setValue(item, (EZDBStringItem) param);
        } else {
            scrnMsg.setMessageInfo(ZZM9000E, new String[] {item.getNameForMessage() });
        }
    }
}
