/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/08/05   Fujitsu         S.Yoshida       Create          N/A
 *</pre>
 */
package business.servlet.NLCL0230.common;

import parts.servletcommon.EZDCommonHandler;
import business.servlet.NLCL0230.NLCL0230BMsg;
import business.servlet.NLCL0230.constant.NLCL0230Constant;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

public class NLCL0230CommonLogic implements NLCL0230Constant {

    /**
     * <pre>
     * Initialization common button.
     * </pre>
     * @param handler EZDCommonHandler
     */
    public static void initCommonButton(EZDCommonHandler handler) {
        handler.setButtonProperties(BTN_CMN_BTN_8[0], BTN_CMN_BTN_8[1], BTN_CMN_BTN_8[2], 1, null);
        handler.setButtonProperties(BTN_CMN_BTN_10[0], BTN_CMN_BTN_10[1], BTN_CMN_BTN_10[2], 1, null);
    }

    /**
     * <pre>
     * Initialization button.
     * </pre>
     * @param handler EZDCommonHandler
     */
    public static void initButton(EZDCommonHandler handler) {
        handler.setButtonEnabled(BTN_SEARCH[0], true);
    }

    /**
     * <pre>
     * Clear screen informations.
     * </pre>
     * @param scrnMsg NLCL0230BMsg
     */
    public static void clear(NLCL0230BMsg scrnMsg) {
        scrnMsg.invtyLocCd.clear();
        scrnMsg.invtyLocNm.clear();
        scrnMsg.invtyLocTpCd_P1.setValue("--");
        ZYPTableUtil.clear(scrnMsg.A);
    }

    /**
     * <pre>
     * Set alternate rows back-ground color.
     * </pre>
     * @param scrnMsg NLCL0230BMsg
     */
    public static void setRowsBGColor(NLCL0230BMsg scrnMsg) {

        // set alternate rows back-ground color
        S21TableColorController tblColor = new S21TableColorController("NLCL0230Scrn00", scrnMsg);
        tblColor.setAlternateRowsBG("A", scrnMsg.A);

    }
}
