/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL3060.common;

import static business.servlet.NFCL3060.constant.NFCL3060Constant.BTN_01_SAV_GUARD;
import static business.servlet.NFCL3060.constant.NFCL3060Constant.BTN_01_SAV_LABEL;
import static business.servlet.NFCL3060.constant.NFCL3060Constant.BTN_01_SAV_NAME;
import static business.servlet.NFCL3060.constant.NFCL3060Constant.BTN_02_SUB_GUARD;
import static business.servlet.NFCL3060.constant.NFCL3060Constant.BTN_02_SUB_LABEL;
import static business.servlet.NFCL3060.constant.NFCL3060Constant.BTN_02_SUB_NAME;
import static business.servlet.NFCL3060.constant.NFCL3060Constant.BTN_03_APL_GUARD;
import static business.servlet.NFCL3060.constant.NFCL3060Constant.BTN_03_APL_LABEL;
import static business.servlet.NFCL3060.constant.NFCL3060Constant.BTN_03_APL_NAME;
import static business.servlet.NFCL3060.constant.NFCL3060Constant.BTN_04_APR_GUARD;
import static business.servlet.NFCL3060.constant.NFCL3060Constant.BTN_04_APR_LABEL;
import static business.servlet.NFCL3060.constant.NFCL3060Constant.BTN_04_APR_NAME;
import static business.servlet.NFCL3060.constant.NFCL3060Constant.BTN_05_REJ_GUARD;
import static business.servlet.NFCL3060.constant.NFCL3060Constant.BTN_05_REJ_LABEL;
import static business.servlet.NFCL3060.constant.NFCL3060Constant.BTN_05_REJ_NAME;
import static business.servlet.NFCL3060.constant.NFCL3060Constant.BTN_06_DWL_GUARD;
import static business.servlet.NFCL3060.constant.NFCL3060Constant.BTN_06_DWL_LABEL;
import static business.servlet.NFCL3060.constant.NFCL3060Constant.BTN_06_DWL_NAME;
import static business.servlet.NFCL3060.constant.NFCL3060Constant.BTN_07_DEL_GUARD;
import static business.servlet.NFCL3060.constant.NFCL3060Constant.BTN_07_DEL_LABEL;
import static business.servlet.NFCL3060.constant.NFCL3060Constant.BTN_07_DEL_NAME;
import static business.servlet.NFCL3060.constant.NFCL3060Constant.BTN_08_CLE_GUARD;
import static business.servlet.NFCL3060.constant.NFCL3060Constant.BTN_08_CLE_LABEL;
import static business.servlet.NFCL3060.constant.NFCL3060Constant.BTN_08_CLE_NAME;
import static business.servlet.NFCL3060.constant.NFCL3060Constant.BTN_09_RST_GUARD;
import static business.servlet.NFCL3060.constant.NFCL3060Constant.BTN_09_RST_LABEL;
import static business.servlet.NFCL3060.constant.NFCL3060Constant.BTN_09_RST_NAME;
import static business.servlet.NFCL3060.constant.NFCL3060Constant.BTN_10_RTR_GUARD;
import static business.servlet.NFCL3060.constant.NFCL3060Constant.BTN_10_RTR_LABEL;
import static business.servlet.NFCL3060.constant.NFCL3060Constant.BTN_10_RTR_NAME;
import static business.servlet.NFCL3060.constant.NFCL3060Constant.SCRN_ID_00;
import business.servlet.NFCL3060.NFCL3060BMsg;
import business.servlet.NFCL3060.NFCL3060_ABMsgArray;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * NFCL3060CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/1/8     Fujitsu         S.Fujita        Create          N/A
 *</pre>
 */
public class NFCL3060CommonLogic {

    /**
     * Initial Common Button properties.
     * @param handler Event Handler
     */
    public static void initCmnBtnProp(S21CommonHandler handler) {

        handler.setButtonProperties(BTN_01_SAV_NAME, BTN_01_SAV_GUARD, BTN_01_SAV_LABEL, 0, null);
        handler.setButtonProperties(BTN_02_SUB_NAME, BTN_02_SUB_GUARD, BTN_02_SUB_LABEL, 0, null);
        handler.setButtonProperties(BTN_03_APL_NAME, BTN_03_APL_GUARD, BTN_03_APL_LABEL, 0, null);
        handler.setButtonProperties(BTN_04_APR_NAME, BTN_04_APR_GUARD, BTN_04_APR_LABEL, 0, null);
        handler.setButtonProperties(BTN_05_REJ_NAME, BTN_05_REJ_GUARD, BTN_05_REJ_LABEL, 0, null);
        handler.setButtonProperties(BTN_06_DWL_NAME, BTN_06_DWL_GUARD, BTN_06_DWL_LABEL, 0, null);
        handler.setButtonProperties(BTN_07_DEL_NAME, BTN_07_DEL_GUARD, BTN_07_DEL_LABEL, 0, null);
        handler.setButtonProperties(BTN_08_CLE_NAME, BTN_08_CLE_GUARD, BTN_08_CLE_LABEL, 0, null);
        handler.setButtonProperties(BTN_09_RST_NAME, BTN_09_RST_GUARD, BTN_09_RST_LABEL, 0, null);
        handler.setButtonProperties(BTN_10_RTR_NAME, BTN_10_RTR_GUARD, BTN_10_RTR_LABEL, 1, null);
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * @param scrnMsg NFCL3060BMsg
     * @param scrnAMsgAry NFCL3060_ABMsgArray
     * @param tblId HTML id attribute given to the Table
     */
    public static void setRowsBGWithClear(NFCL3060BMsg scrnMsg, NFCL3060_ABMsgArray scrnAMsgAry, String tblId) {
        setRowsBGWithClear(scrnMsg, scrnAMsgAry, tblId, 1);
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * @param scrnMsg NFCL3060BMsg
     * @param scrnAMsgAry NFCL3060_ABMsgArray
     * @param tblId HTML id attribute given to the Table
     * @param grpRows color reversal switch lines
     */
    public static void setRowsBGWithClear(NFCL3060BMsg scrnMsg, NFCL3060_ABMsgArray scrnAMsgAry, String tblId, int grpRows) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);

        // Color on
        tblColor.setAlternateRowsBG(tblId, scrnAMsgAry, grpRows);
    }

    /**
     * Table has an id attribute of the argument row background color
     * back to the initial color.
     * @param scrnMsg NFCL3060BMsg
     * @param scrnAMsgAry NFCL3060_ABMsgArray
     * @param tblId HTML id attribute given to the Table
     */
    public static void clearRowsBG(NFCL3060BMsg scrnMsg, NFCL3060_ABMsgArray scrnAMsgAry, String tblId) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);
    }

    /**
     * addCheckItemHeader
     * @param scrnMsg NFCL3060BMsg
     */
    public static void addCheckItemHeader(NFCL3060BMsg scrnMsg) {

        scrnMsg.addCheckItem(scrnMsg.arTrxNum);
        scrnMsg.addCheckItem(scrnMsg.xxDplyByItemNm);
    }
}
