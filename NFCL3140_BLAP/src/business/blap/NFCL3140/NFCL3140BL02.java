/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NFCL3140;

import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NFCL3140.common.NFCL3140CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Invoice Type Setup screen
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/25   Hitachi         K.Kojima        Create          N/A
 * 2016/08/09   Hitachi         K.Kojima        Update          QC#13200
 *</pre>
 */
public class NFCL3140BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {
        NFCL3140CMsg cMsg = (NFCL3140CMsg) arg0;
        super.preDoProcess(arg0, arg1);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if ("NFCL3140_INIT".equals(screenAplID)) {
                doProcess_NFCL3140_INIT((NFCL3140CMsg) cMsg);
            } else if ("NFCL3140_NWAL1130".equals(screenAplID)) {
                doProcessNFCL3140_NWAL1130((NFCL3140CMsg) cMsg);
            } else if ("NFCL3140Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NFCL3140Scrn00_CMN_Clear((NFCL3140CMsg) cMsg);
            } else if ("NFCL3140Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NFCL3140Scrn00_CMN_Submit((NFCL3140CMsg) cMsg);
            } else if ("NFCL3140Scrn00_OnChange_Class".equals(screenAplID)) {
                doProcess_NFCL3140Scrn00_OnChange_Class((NFCL3140CMsg) cMsg);
                // START 2016/08/09 K.Kojima [QC#13200,ADD]
            } else if ("NFCL3140Scrn00_OnChange_AutoSequence".equals(screenAplID)) {
                doProcess_NFCL3140Scrn00_OnChange_AutoSequence((NFCL3140CMsg) cMsg);
                // END 2016/08/09 K.Kojima [QC#13200,ADD]
            }
        } finally {
            super.postDoProcess(arg0, arg1);
        }
    }

    private void doProcess_NFCL3140_INIT(NFCL3140CMsg cMsg) {
        cMsg.clear();
        cMsg.A.clear();
        cMsg.B.clear();
        cMsg.A.setValidCount(0);
        cMsg.B.setValidCount(0);

        ZYPEZDItemValueSetter.setValue(cMsg.glblCmpyCd, getGlobalCompanyCode());
        NFCL3140CommonLogic.getPulldownListInvTp(cMsg);
        NFCL3140CommonLogic.getPulldownListGrouping(cMsg);
        // START 2016/08/09 K.Kojima [QC#13200,ADD]
        NFCL3140CommonLogic.getPulldownListAutoSequence(cMsg);
        // END 2016/08/09 K.Kojima [QC#13200,ADD]
    }

    private void doProcessNFCL3140_NWAL1130(NFCL3140CMsg cMsg) {
        if ("OpenWin_DsInvoiceType".equals(cMsg.xxScrEventNm.getValue())) {
            NFCL3140CommonLogic.getDetailData(cMsg);
            NFCL3140CommonLogic.getGroupingData(cMsg);
            NFCL3140CommonLogic.getPulldownListGroupingNoAccess(cMsg);
            // START 2016/08/09 K.Kojima [QC#13200,ADD]
            NFCL3140CommonLogic.getExtCurSqNum(cMsg);
            // END 2016/08/09 K.Kojima [QC#13200,ADD]
        }
    }

    private void doProcess_NFCL3140Scrn00_CMN_Clear(NFCL3140CMsg cMsg) {
        cMsg.clear();
        doProcess_NFCL3140_INIT(cMsg);
    }

    private void doProcess_NFCL3140Scrn00_CMN_Submit(NFCL3140CMsg cMsg) {
        Map<String, Object> beforeData = NFCL3140CommonLogic.getBeforeData(cMsg);
        ZYPEZDItemValueSetter.setValue(cMsg.ezUpTime, (String) beforeData.get("EZUPTIME"));
        ZYPEZDItemValueSetter.setValue(cMsg.ezUpTimeZone, (String) beforeData.get("EZUPTIMEZONE"));
    }

    private void doProcess_NFCL3140Scrn00_OnChange_Class(NFCL3140CMsg cMsg) {
        return;
    }

    // START 2016/08/09 K.Kojima [QC#13200,ADD]
    private void doProcess_NFCL3140Scrn00_OnChange_AutoSequence(NFCL3140CMsg cMsg) {
        NFCL3140CommonLogic.getExtCurSqNum(cMsg);
    }
    // END 2016/08/09 K.Kojima [QC#13200,ADD]

}
