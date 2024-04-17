/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NYEL8870.common;

import static business.servlet.NYEL8870.constant.NYEL8870Constant.CMN_BTN1;
import static business.servlet.NYEL8870.constant.NYEL8870Constant.CMN_BTN2;
import static business.servlet.NYEL8870.constant.NYEL8870Constant.CMN_BTN3;
import static business.servlet.NYEL8870.constant.NYEL8870Constant.CMN_BTN4;
import static business.servlet.NYEL8870.constant.NYEL8870Constant.CMN_BTN5;
import static business.servlet.NYEL8870.constant.NYEL8870Constant.CMN_BTN6;
import static business.servlet.NYEL8870.constant.NYEL8870Constant.CMN_BTN7;
import static business.servlet.NYEL8870.constant.NYEL8870Constant.CMN_BTN8;
import static business.servlet.NYEL8870.constant.NYEL8870Constant.CMN_BTN9;
import static business.servlet.NYEL8870.constant.NYEL8870Constant.CMN_BTN10;
import static business.servlet.NYEL8870.constant.NYEL8870Constant.SCREEN_NAME;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

//import parts.common.EZDGUIAttribute;


import business.servlet.NYEL8870.NYEL8870BMsg;
import business.servlet.NYEL8870.NYEL8870_ABMsgArray;

/**
 *<pre>
 * NYEL8870CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/30   Fujitsu         Q10627          Create          N/A
 *</pre>
 */
public class NYEL8870CommonLogic {

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
        handler.setButtonProperties(CMN_BTN1[0], CMN_BTN1[1], CMN_BTN1[2], 0, null);
        handler.setButtonProperties(CMN_BTN2[0], CMN_BTN2[1], CMN_BTN2[2], 1, null);
        handler.setButtonProperties(CMN_BTN3[0], CMN_BTN3[1], CMN_BTN3[2], 0, null);
        handler.setButtonProperties(CMN_BTN4[0], CMN_BTN4[1], CMN_BTN4[2], 0, null);
        handler.setButtonProperties(CMN_BTN5[0], CMN_BTN5[1], CMN_BTN5[2], 0, null);
        handler.setButtonProperties(CMN_BTN6[0], CMN_BTN6[1], CMN_BTN6[2], 0, null);
        handler.setButtonProperties(CMN_BTN7[0], CMN_BTN7[1], CMN_BTN7[2], 0, null);
        handler.setButtonProperties(CMN_BTN8[0], CMN_BTN8[1], CMN_BTN8[2], 0, null);
        handler.setButtonProperties(CMN_BTN9[0], CMN_BTN9[1], CMN_BTN9[2], 1, null);
        handler.setButtonProperties(CMN_BTN10[0], CMN_BTN10[1], CMN_BTN10[2], 1, null);
    }

    /**
     * @param scrnMsg NYEL8870BMsg
     */
    public static void initRowCtrlProp(NYEL8870BMsg scrnMsg) {
        scrnMsg.clearAllGUIAttribute(SCREEN_NAME);

    }

    /**
     * <p>
     * Sets the name for the error message.
     * </p>
     * @param scrnMsg NYEL8870BMsg
     */
    public static void setNameForMessage(NYEL8870BMsg scrnMsg) {
        scrnMsg.wfNtfyEmlTpCd_V.setNameForMessage("Email Notification");
        scrnMsg.wfNtfyTpCd_V.setNameForMessage("Notification Type");
        scrnMsg.wfProcNm_V.setNameForMessage("Process Name");
    }


    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * @param scrnMsg NYEL8870BMsg
     * @param scrnAMsgAry NYEL8870_ABMsgArray
     * @param tblId HTML id attribute given to the Table
     */
    public static void setRowsBGWithClear(NYEL8870BMsg scrnMsg, NYEL8870_ABMsgArray scrnAMsgAry, String tblId) {
        S21TableColorController tblColor = new S21TableColorController(SCREEN_NAME, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);

        // Color on
        tblColor.setAlternateRowsBG(tblId, scrnAMsgAry, 1);
    }

//    private static void setVisibility(NYEL8870BMsg scrnMsg, String id, boolean isVisible) {
//        EZDGUIAttribute ctrl = new EZDGUIAttribute(SCREEN_NAME, id);
//        ctrl.setVisibility(isVisible);
//        scrnMsg.addGUIAttribute(ctrl);
//    }
}
