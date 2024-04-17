/**
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NPAL1250;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NPAL1250.common.NPAL1250CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * Big Deal Setup
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/29   CITS            T.Gotoda        Create          N/A
 *</pre>
 */
public class NPAL1250BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if ("NPAL1250_INIT".equals(screenAplID)) {
                doProcess_NPAL1250_INIT((NPAL1250CMsg) cMsg, (NPAL1250SMsg) sMsg);
            } else if ("NPAL1250Scrn00_OnClick_Search".equals(screenAplID)) {
                doProcess_NPAL1250Scrn00_OnClick_Search((NPAL1250CMsg) cMsg, (NPAL1250SMsg) sMsg);
            } else if ("NPAL1250Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NPAL1250Scrn00_OnClick_Search((NPAL1250CMsg) cMsg, (NPAL1250SMsg) sMsg);
            } else if ("NPAL1250Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NPAL1250_INIT((NPAL1250CMsg) cMsg, (NPAL1250SMsg) sMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * <p>
     * Initialization.
     * </p>
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NPAL1250_INIT(NPAL1250CMsg cMsg, NPAL1250SMsg sMsg) {

        cMsg.A.setValidCount(0);
        sMsg.A.setValidCount(0);
        cMsg.A.clear();
        sMsg.A.clear();

        ZYPEZDItemValueSetter.setValue(cMsg.glblCmpyCd, getGlobalCompanyCode());

        EZDMsg.copy(cMsg, null, sMsg, null);
    }

    /**
     * <p>
     * Search.
     * </p>
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NPAL1250Scrn00_OnClick_Search(NPAL1250CMsg cMsg, NPAL1250SMsg sMsg) {

        cMsg.A.setValidCount(0);
        sMsg.A.setValidCount(0);
        cMsg.A.clear();
        sMsg.A.clear();

        NPAL1250CommonLogic.search(cMsg, sMsg);
    }
}
