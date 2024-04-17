package business.servlet.NSAL1060.common;

import business.servlet.NSAL1060.NSAL1060BMsg;

/** <pre>
 * Meter Reading Popup.
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/25/2015   Fujitsu         M.Hara          Create          N/A
 * </pre>
 */
public class NSAL1060CommonLogic {

    /**
     * setProtected
     * @param scrnMsg NSAL1060BMsg
     */
    public static void setProtected(NSAL1060BMsg scrnMsg) {
        scrnMsg.serNum.setInputProtected(true);
    }
}
