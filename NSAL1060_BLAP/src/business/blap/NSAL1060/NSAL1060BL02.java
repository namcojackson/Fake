/**
 * 
 */
package business.blap.NSAL1060;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;

import business.blap.NSAL1060.common.NSAL1060CommonLogic;

import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/** <pre>
 * Meter Reading Popup.
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/25/2015   Fujitsu         M.Hara          Create          N/A
 * </pre>
 */
public class NSAL1060BL02 extends S21BusinessHandler {

    /* (非 Javadoc)
     * @see parts.ejbcommon.EZDBusinessHandler#doProcess(parts.common.EZDCMsg, parts.common.EZDSMsg)
     */
    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();

            if ("NSAL1060_INIT".equals(screenAplID)) {
                doProcess_NSAL1060Scrn00_init((NSAL1060CMsg) cMsg, (NSAL1060SMsg) sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NSAL1060Scrn00_init(NSAL1060CMsg cMsg, NSAL1060SMsg sMsg) {
        NSAL1060CommonLogic.searchMtrReadList(cMsg, sMsg, getGlobalCompanyCode());
    }
}
