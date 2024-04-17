package business.servlet.ZZVL0010.common;


import business.servlet.ZZVL0010.ZZVL0010BMsg;
import business.servlet.ZZVL0010.constant.ZZVL0010Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/11/04   Fujitsu         C.Ogaki         Create          N/A
 *</pre>
 */
public class ZZVL0010Common {

    /**
     * setCommonButtons
     * @param   handler S21CommonHandler
     */
    public static final void setCommonButtons(S21CommonHandler handler) {
        handler.setButtonProperties(ZZVL0010Constant.BTN_CMN_CLEAR[0], ZZVL0010Constant.BTN_CMN_CLEAR[1],
                ZZVL0010Constant.BTN_CMN_CLEAR[2], 1, null);
        handler.setButtonProperties(ZZVL0010Constant.BTN_CMN_CLOSE[0], ZZVL0010Constant.BTN_CMN_CLOSE[1],
                ZZVL0010Constant.BTN_CMN_CLOSE[2], 1, null);
    }

    /**
     * ControlButtonsDisplay
     * @param objS21CommonHandler S21CommonHandler
     * @param scrnMsg ZZVL0010BMsg
     */
    public static void controlButtonsDisplay(S21CommonHandler objS21CommonHandler, ZZVL0010BMsg scrnMsg) {

        // to disable next button
        if (scrnMsg.A.getValidCount() < 200) {
            objS21CommonHandler.setButtonEnabled(ZZVL0010Constant.BTN_NEXT[1], false);
        } else {
            objS21CommonHandler.setButtonEnabled(ZZVL0010Constant.BTN_NEXT[1], true);
        }
    }

}
