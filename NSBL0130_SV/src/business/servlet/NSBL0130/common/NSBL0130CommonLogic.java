/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0130.common;

import static business.servlet.NSBL0130.constant.NSBL0130Constant.*;

import business.servlet.NSBL0130.NSBL0130BMsg;

import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/** 
 *<pre>
 *
 * Hold Detail Popup
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/06/07   Hitachi         Y.Igarashi         Create          N/A
 *</pre>
 */
public class NSBL0130CommonLogic {


    /**
     * 
     * setBgColor
     * 
     * @param scrnMsg NSBL0130BMsg
     */
    public static void setBgColor(NSBL0130BMsg scrnMsg) {
        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        tblColor.clearRowsBG(TBL_ID, scrnMsg.A);
        if (scrnMsg.A.getValidCount() > 0) {
            tblColor.setAlternateRowsBG(TBL_ID, scrnMsg.A, 1);
        }
    }

}
