/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL2170;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NWAL2170.common.NWAL2170CommonLogic;

import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * NWAL2170BL06
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/17   Fujitsu         M.Yamada        Create          N/A
 * 2023/11/06   Hitachi         H.Watanabe      Update          QC#61924
 *</pre>
 */
public class NWAL2170BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NWAL2170CMsg bizMsg = (NWAL2170CMsg) cMsg;
            NWAL2170SMsg glblMsg = (NWAL2170SMsg) sMsg;

            if ("NWAL2170Scrn00_CMN_Save".equals(screenAplID)) {
                doProcess_NWAL2170Scrn00_CMN_Save(bizMsg, glblMsg);

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
    private void doProcess_NWAL2170Scrn00_CMN_Save(NWAL2170CMsg bizMsg, NWAL2170SMsg glblMsg) {
        boolean isImport = NWAL2170CommonLogic.isImport(bizMsg);
        if (isImport) {
            if (NWAL2170CommonLogic.deriveImptFromInputValue(getGlobalCompanyCode(), bizMsg, glblMsg)) {
                return; // error
            }

            if (NWAL2170CommonLogic.checkImptExclusionCtrl(getGlobalCompanyCode(), bizMsg)) {
                return; // error
            }
            // 2023/11/06 QC#61924 Add Start
            if (!NWAL2170CommonLogic.hasDeactivateAccountOrLocation(bizMsg, glblMsg)) {
                return;
            }
            // 2023/11/06 QC#61924 Add End
        }

        if (isImport) {
            NWAL2170CommonLogic.updateImptCustInfo(getGlobalCompanyCode(), bizMsg);
        }

    }
}
