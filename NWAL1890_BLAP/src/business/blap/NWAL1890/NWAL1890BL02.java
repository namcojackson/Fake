/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1890;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NWAL1890.common.NWAL1890CommonLogic;

import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 *  Order Line Filter Popup
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/08/10   Fujitsu         T.Aoi           Create          N/A
 *</pre>
 */
public class NWAL1890BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            NWAL1890CMsg bizMsg = (NWAL1890CMsg) cMsg;
            NWAL1890SMsg glblMsg = (NWAL1890SMsg) sMsg;

            if ("NWAL1890Scrn00_Search".equals(screenAplID)) {
                doProcess_NWAL1890Scrn00_Search(bizMsg, glblMsg);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * <pre>
     * Process of screen event NWAL1890Scrn00_Search
     * </pre>
     * @param bizMsg NWAL1890CMsg
     * @param glblMsg NWAL1890SMsg
     */
    private void doProcess_NWAL1890Scrn00_Search(NWAL1890CMsg bizMsg, NWAL1890SMsg glblMsg) {

        if (!NWAL1890CommonLogic.mndtyChk(bizMsg)) {
            return;
        }

        if (!NWAL1890CommonLogic.existsChk(bizMsg)) {
            return;
        }

    }
}
