/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7210.common;

import static business.servlet.NMAL7210.constant.NMAL7210Constant.BTN_CMN_APL;
import static business.servlet.NMAL7210.constant.NMAL7210Constant.BTN_CMN_APR;
import static business.servlet.NMAL7210.constant.NMAL7210Constant.BTN_CMN_CLR;
import static business.servlet.NMAL7210.constant.NMAL7210Constant.BTN_CMN_DEL;
import static business.servlet.NMAL7210.constant.NMAL7210Constant.BTN_CMN_DWL;
import static business.servlet.NMAL7210.constant.NMAL7210Constant.BTN_CMN_RJT;
import static business.servlet.NMAL7210.constant.NMAL7210Constant.BTN_CMN_RST;
import static business.servlet.NMAL7210.constant.NMAL7210Constant.BTN_CMN_RTN;
import static business.servlet.NMAL7210.constant.NMAL7210Constant.BTN_CMN_SAV;
import static business.servlet.NMAL7210.constant.NMAL7210Constant.BTN_CMN_SUB;
import static business.servlet.NMAL7210.constant.NMAL7210Constant.SCRN_ID_00;
import business.servlet.NMAL7210.NMAL7210BMsg;
import business.servlet.NMAL7210.NMAL7210_ABMsgArray;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * NMAL7210CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/22   Fujitsu         H.Ikeda         Create          N/A
 *</pre>
 */
public class NMAL7210CommonLogic {

    /**
     * Initial Common Button properties.
     * 
     * @param handler Event Handler
     */
    public static void initCmnBtnProp(S21CommonHandler handler) {
        // 4th parameter(0:Inactive, 1:Active)
        handler.setButtonProperties(BTN_CMN_SAV[0], BTN_CMN_SAV[1], BTN_CMN_SAV[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APL[0], BTN_CMN_APL[1], BTN_CMN_APL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APR[0], BTN_CMN_APR[1], BTN_CMN_APR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RJT[0], BTN_CMN_RJT[1], BTN_CMN_RJT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DWL[0], BTN_CMN_DWL[1], BTN_CMN_DWL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DEL[0], BTN_CMN_DEL[1], BTN_CMN_DEL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RST[0], BTN_CMN_RST[1], BTN_CMN_RST[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RTN[0], BTN_CMN_RTN[1], BTN_CMN_RTN[2], 1, null);
    }

    /**
     * Set Common Button properties.
     * 
     * @param handler Event Handler
     * @param btnProp Button Properties in Constant class
     * @param sts Status (0:Inactive, 1:Active)
     */
    public static void setCmnBtnProp(S21CommonHandler handler, String[] btnProp, int sts) {
        handler.setButtonProperties(btnProp[0], btnProp[1], btnProp[2], sts, null);
    }

    /**
     * Set Button properties.
     * @param handler S21CommonHandler
     * @param scrnMsg NMAL7210BMsg
     */
    public static void setBtnProp(S21CommonHandler handler, NMAL7210BMsg scrnMsg) {
        if (scrnMsg.A.getValidCount() > 0) {
            handler.setButtonEnabled("ViewFormula", true);
            handler.setButtonEnabled("NewFormula", true);
            handler.setButtonProperties(BTN_CMN_DWL[0], BTN_CMN_DWL[1], BTN_CMN_DWL[2], 1, null);
        } else {
            handler.setButtonEnabled("ViewFormula", false);
            handler.setButtonEnabled("NewFormula", false);
            handler.setButtonProperties(BTN_CMN_DWL[0], BTN_CMN_DWL[1], BTN_CMN_DWL[2], 0, null);
        }
    }

    /**
     * scrnProtect.
     * @param scrnMsg NMAL7210BMsg
     */
    public static void scrnProtect(NMAL7210BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).prcFmlaNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).prcFmlaDescTxt_A1.setInputProtected(true);
        }
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * 
     * @param scrnMsg     NMAL7210BMsg
     * @param scrnAMsgAry NMAL7210_ABMsgArray
     * @param tblId       HTML id attribute given to the Table
     */
    public static void setRowsBGWithClear(NMAL7210BMsg scrnMsg, NMAL7210_ABMsgArray scrnAMsgAry, String tblId) {
        setRowsBGWithClear(scrnMsg, scrnAMsgAry, tblId, 1);
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * 
     * @param scrnMsg     NMAL7210BMsg
     * @param scrnAMsgAry NMAL7210_ABMsgArray
     * @param tblId       HTML id attribute given to the Table
     * @param grpRows     color reversal switch lines
     */
    public static void setRowsBGWithClear(NMAL7210BMsg scrnMsg, NMAL7210_ABMsgArray scrnAMsgAry, String tblId, int grpRows) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);
        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);
        // Color on
        tblColor.setAlternateRowsBG(tblId, scrnAMsgAry, grpRows);
    }

    /**
     * Table has an id attribute of the argument row background color back to the initial color. 
     * 
     * @param scrnMsg     NMAL7210BMsg
     * @param scrnAMsgAry NMAL7210_ABMsgArray
     * @param tblId       HTML id attribute given to the Table
     */
    public static void clearRowsBG(NMAL7210BMsg scrnMsg, NMAL7210_ABMsgArray scrnAMsgAry, String tblId) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);
        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);
    }

    /**
     * Set Argument for NMAL7220.
     * @param scrnMsg NMAL7210BMsg
     * @return Object[]
     */
    public static Object[] setArgumentNMAL7210(NMAL7210BMsg scrnMsg) {
        Object[] param = new Object[1];
        // Parameter is not required.
        param[0] = scrnMsg.A.no(scrnMsg.xxRadioBtn.getValueInt()).prcFmlaPk_A1.getValue();

        return param;
    }

}
