/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NYEL8870;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;

import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

import business.blap.NYEL8870.common.NYEL8870CommonLogic;

/**
 *<pre>
 * NYEL8850BL02
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/24   Fujitsu         Q10627          Create          N/A
 *</pre>
 */
public class NYEL8870BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();

            NYEL8870CMsg bizMsg = (NYEL8870CMsg) cMsg;
            NYEL8870SMsg glblMsg = (NYEL8870SMsg) sMsg;

            if ("NYEL8870_INIT".equals(screenAplID)
                || "NYEL8870Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NYEL8870_INIT(bizMsg, glblMsg);

            } else if ("NYEL8870Scrn00_Search".equals(screenAplID)) {
                doProcess_NYEL8870Scrn00_Search(bizMsg, glblMsg);

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
    private void doProcess_NYEL8870_INIT(NYEL8870CMsg bizMsg, NYEL8870SMsg glblMsg) {

        search(bizMsg, glblMsg);

    }

    /**
     * Search Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NYEL8870Scrn00_Search(NYEL8870CMsg bizMsg, NYEL8870SMsg glblMsg) {

        search(bizMsg, glblMsg);

        if (!"E".equals(bizMsg.getMessageKind())) {
            bizMsg.setMessageInfo("NZZM0002I");
        }
    }

    /**
     * Search common
     * @param bizMsg Business Msg
     */
    private void search(NYEL8870CMsg bizMsg, NYEL8870SMsg glblMsg) {
        String usrId = getContextUserInfo().getUserId();
        String glblCmpyCd = getGlobalCompanyCode();

        NYEL8870CommonLogic.initProcNamePullDown(bizMsg, glblCmpyCd);
        NYEL8870CommonLogic.initNtfyTypePullDown(bizMsg);
        NYEL8870CommonLogic.initNtfyEmailTypePullDown(bizMsg);

        NYEL8870CommonLogic.search(bizMsg, glblMsg, usrId, glblCmpyCd);
    }


}
