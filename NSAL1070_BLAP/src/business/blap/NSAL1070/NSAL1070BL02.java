package business.blap.NSAL1070;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;

import business.blap.NSAL1070.common.NSAL1070CommonLogic;

import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/26/2015   Fujitsu         M.Hara          Create          N/A
 *</pre>
 */
public class NSAL1070BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();

            if ("NSAL1070_INIT".equals(screenAplID)) {
                doProcess_NSAL1070Scrn00_init((NSAL1070CMsg) cMsg, (NSAL1070SMsg) sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NSAL1070Scrn00_init(NSAL1070CMsg cMsg, NSAL1070SMsg sMsg) {
        NSAL1070CommonLogic.searchXsMtrList(cMsg, sMsg, getGlobalCompanyCode());
    }
}
