/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL1330;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NSAL1330.common.NSAL1330CommonLogic;

import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * NSAL1330BL06
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/04/14   Hitachi         Y.Takeno        Create          N/A
 * 2017/10/10   Hitachi         Y.Takeno        Update          QC#20059
 * 2017/10/16   Hitachi         Y.Takeno        Update          QC#20001
 * 2023/11/06   Hitachi         H.Watanabe      Update          QC#61924
 *</pre>
 */
public class NSAL1330BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NSAL1330CMsg bizMsg = (NSAL1330CMsg) cMsg;
            NSAL1330SMsg glblMsg = (NSAL1330SMsg) sMsg;

            if ("NSAL1330Scrn00_CMN_Save".equals(screenAplID)) {
                doProcess_NSAL1330Scrn00_CMN_Save(bizMsg, glblMsg);

            } else if ("NSAL1330Scrn00_OpenWin_Supply_Agreement".equals(screenAplID)) {
                doProcess_NSAL1330Scrn00_OpenWin_Supply_Agreement(bizMsg, glblMsg);

            // START 2017/10/16 [QC#20001, ADD]
            } else if ("NSAL1330Scrn00_OpenWin_Supply_Agreement_forModelPricing".equals(screenAplID)) {
                doProcess_NSAL1330Scrn00_OpenWin_Supply_Agreement_forModelPricing(bizMsg, glblMsg);
            // END   2017/10/16 [QC#20001, ADD]

            } else if ("NSAL1330Scrn00_CMN_Delete".equals(screenAplID)) {
                doProcess_NSAL1330Scrn00_CMN_Delete(bizMsg);

            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NSAL1330Scrn00_OpenWin_Supply_Agreement(NSAL1330CMsg bizMsg, NSAL1330SMsg glblMsg) {
        doProcess_NSAL1330Scrn00_CMN_Save(bizMsg, glblMsg);
    }

    private void doProcess_NSAL1330Scrn00_CMN_Delete(NSAL1330CMsg bizMsg) {
        if (NSAL1330CommonLogic.checkExclusionCtrl(getGlobalCompanyCode(), bizMsg)) {
            return;
        }
        NSAL1330CommonLogic.deleteShellPrice(getGlobalCompanyCode(), bizMsg);
    }

    /**
     * CMN_Save Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NSAL1330Scrn00_CMN_Save(NSAL1330CMsg bizMsg, NSAL1330SMsg glblMsg) {
        // START 2017/10/10 [QC#20059, DEL]
        // NSAL1330CommonLogic.checkAvailableToSave(bizMsg, glblMsg);
        // if (!ZYPConstant.FLG_ON_Y.equals(bizMsg.submtFlg_W.getValue())) {
        //     bizMsg.setMessageInfo(NSAM0679W);
        //     ZYPEZDItemValueSetter.setValue(bizMsg.submtFlg_W, ZYPConstant.FLG_ON_Y);
        //     return;
        // }
        // END   2017/10/10 [QC#20059, DEL]
        bizMsg.submtFlg_W.clear();

        // 2023/11/06 QC#61924 Add Start
        if (!NSAL1330CommonLogic.hasDeactivateAccountOrLocation(bizMsg, glblMsg)) {
            return;
        }
        // 2023/11/06 QC#61924 Add End

        NSAL1330CommonLogic.forceApplyAllForSave(bizMsg, glblMsg);
        //
        NSAL1330CommonLogic.setBaseTotal(bizMsg);
        if (NSAL1330CommonLogic.checkExclusionCtrl(getGlobalCompanyCode(), bizMsg)) {
            return;
        }
        NSAL1330CommonLogic.saveShell(getGlobalCompanyCode(), bizMsg, glblMsg);
    }

    // START 2017/10/16 [QC#20001, ADD]
    private void doProcess_NSAL1330Scrn00_OpenWin_Supply_Agreement_forModelPricing(NSAL1330CMsg bizMsg, NSAL1330SMsg glblMsg) {
        doProcess_NSAL1330Scrn00_CMN_Save(bizMsg, glblMsg);
    }
    // END   2017/10/16 [QC#20001, ADD]
}
