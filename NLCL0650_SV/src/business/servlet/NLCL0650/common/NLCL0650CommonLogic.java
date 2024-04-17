package business.servlet.NLCL0650.common;

import static business.servlet.NLCL0650.constant.NLCL0650Constant.BTN_CMN_BTN_10;
import static business.servlet.NLCL0650.constant.NLCL0650Constant.BTN_CMN_BTN_8;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NLCL0650.NLCL0650BMsg;

/** 
 * <pre>
 * Business ID : NLCL0650 Tech-PI Count ReCount / Counting Completed
 * </pre>
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/15/2016   CITS            Makoto Okigami  Create          N/A
 *</pre>
 */
public class NLCL0650CommonLogic {

    /**
     * The method explanation: The display control of the screen item
     * @param handler EZDCommonHandler
     * @param scrnMsg NLCL0650BMsg
     */
    public static void ctrlScrnItemDisp(EZDCommonHandler handler, NLCL0650BMsg scrnMsg) {

        // common button protection
        // 0 : inactive
        // 1 : active
        handler.setButtonProperties(BTN_CMN_BTN_8[0], BTN_CMN_BTN_8[1], BTN_CMN_BTN_8[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_10[0], BTN_CMN_BTN_10[1], scrnMsg.xxScrItem20Txt.getValue(), 1, null);

    }

    /**
     * <p>
     * Sets the name for the error message.
     * </p>
     * @param scrnMsg NLCL0650BMsg
     */
    public static void setNameForMessage(NLCL0650BMsg scrnMsg) {

        // There is no processing.

    }

}
