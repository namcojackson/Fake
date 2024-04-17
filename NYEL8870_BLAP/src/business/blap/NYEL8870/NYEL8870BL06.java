/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NYEL8870;



import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;

import business.blap.NYEL8870.common.NYEL8870CommonLogic;

/**
 *<pre>
 * NYEL8870BL06
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/29   Fujitsu         Q10627          Create          N/A
 *</pre>
 */
public class NYEL8870BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NYEL8870CMsg bizMsg = (NYEL8870CMsg) cMsg;
            NYEL8870SMsg glblMsg = (NYEL8870SMsg) sMsg;

            if ("NYEL8870Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NYEL8870Scrn00_CMN_Submit(bizMsg, glblMsg);

            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * CMN_Submit Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NYEL8870Scrn00_CMN_Submit(NYEL8870CMsg bizMsg, NYEL8870SMsg glblMsg) {
        String usrId = getContextUserInfo().getUserId();
        String glblCmpyCd = getGlobalCompanyCode();

        NYEL8870CommonLogic.submit(bizMsg, glblMsg, usrId, glblCmpyCd);
        if (!"E".equals(bizMsg.getMessageKind())) {
            bizMsg.setMessageInfo("NZZM0002I");
        }

    }

}
