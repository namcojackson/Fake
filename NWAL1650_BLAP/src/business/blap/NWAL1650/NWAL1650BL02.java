/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1650;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NWAL1650.common.NWAL1650CommonLogic;

import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * NWAL1650BL02
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/12   Fujitsu         M.Yamada        Create          N/A
 * 2015/12/03   Fujitsu         Y.Kanefusa      Update          #1309
 *</pre>
 */
public class NWAL1650BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NWAL1650CMsg bizMsg = (NWAL1650CMsg) cMsg;

            if ("NWAL1650_INIT".equals(screenAplID)) {
                doProcess_NWAL1650_INIT(bizMsg);
            // ADD START 2015/12/03 #1309
            }else if ("NWAL1650Scrn00_CMN_Clear".equals(screenAplID)) {
                    doProcess_NWAL1650_INIT(bizMsg);
            // ADD END 2015/12/03 #1309
            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * INIT Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NWAL1650_INIT(NWAL1650CMsg bizMsg) {

        NWAL1650CommonLogic.setBizMsg(getGlobalCompanyCode(), bizMsg);
    }

}
