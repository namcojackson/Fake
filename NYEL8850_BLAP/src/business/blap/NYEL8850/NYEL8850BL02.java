/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NYEL8850;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;

import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

import business.blap.NYEL8850.common.NYEL8850CommonLogic;

/**
 *<pre>
 * NYEL8850BL02
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/08/14   Fujitsu         Q09079          Create          N/A
 *</pre>
 */
public class NYEL8850BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NYEL8850CMsg bizMsg = (NYEL8850CMsg) cMsg;
            NYEL8850SMsg glblMsg = (NYEL8850SMsg) sMsg;

            if ("NYEL8850_INIT".equals(screenAplID)) {
                doProcess_NYEL8850_INIT(bizMsg, glblMsg);

            } else if ("NYEL8850Scrn00_Refresh".equals(screenAplID)) {
                doProcess_NYEL8850Scrn00_Refresh(bizMsg, glblMsg);
                
            } else if ("NYEL8850Scrn00_Display".equals(screenAplID)) {
                doProcess_NYEL8850Scrn00_Display(bizMsg, glblMsg);

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
    private void doProcess_NYEL8850_INIT(NYEL8850CMsg bizMsg, NYEL8850SMsg glblMsg) {
        NYEL8850CommonLogic.search(bizMsg, glblMsg, this.getContextUserInfo().getUserId(), 0);
    }

    /**
     * Refresh Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NYEL8850Scrn00_Refresh(NYEL8850CMsg bizMsg, NYEL8850SMsg glblMsg) {
        NYEL8850CommonLogic.search(bizMsg, glblMsg, this.getContextUserInfo().getUserId(), 0);
    }
    
    /**
     * Refresh Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NYEL8850Scrn00_Display(NYEL8850CMsg bizMsg, NYEL8850SMsg glblMsg) {
        NYEL8850CommonLogic.search(bizMsg, glblMsg, this.getContextUserInfo().getUserId());
    }
}
