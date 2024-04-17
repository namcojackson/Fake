/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NFCL3120;

import static business.blap.NFCL3120.constant.NFCL3120Constant.*;
import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NFCL3120.common.NFCL3120CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Bank Account Search
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/08/01   Hitachi         K.Kojima        Create          QC#5521
 *</pre>
 */
public class NFCL3120BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("NFCL3120Scrn00_CMN_ColClear".equals(screenAplID)) {
                ZYPGUITableColumn.clearColData(cMsg, sMsg);
            } else if ("NFCL3120Scrn00_CMN_ColSave".equals(screenAplID)) {
                ZYPGUITableColumn.setColData(cMsg, sMsg);
            } else if ("NFCL3120Scrn00_SaveSearch".equals(screenAplID)) {
                doProcess_NFCL3120Scrn00_SaveSearch(cMsg, sMsg);
            } else if ("NFCL3120Scrn00_DeleteSearch".equals(screenAplID)) {
                doProcess_NFCL3120Scrn00_DeleteSearch(cMsg, sMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * Method name: doProcess_NFCL3120Scrn00_SaveSearch <dd>The method
     * explanation: Save Search <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFCL3120Scrn00_SaveSearch(EZDCMsg cMsg, EZDSMsg sMsg) {

        NFCL3120CMsg bizMsg = (NFCL3120CMsg) cMsg;
        NFCL3120SMsg glblMsg = (NFCL3120SMsg) sMsg;

        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptPk_S) && !ZYPCommonFunc.hasValue(bizMsg.srchOptNm_S)) {
            bizMsg.srchOptNm_S.setErrorInfo(1, ZZM9000E, new String[] {"Search Option Name" });
            return;
        }
        if (NFCL3120CommonLogic.isExistSaveSearchName(bizMsg)) {
            bizMsg.srchOptNm_S.setErrorInfo(1, NFCM0858E, new String[] {"Save", "Search Option Name" });
            return;
        }

        NFCL3120CommonLogic.callNszc0330forSaveSearch(bizMsg, glblMsg, getContextUserInfo().getUserId(), this.getGlobalCompanyCode());

    }

    /**
     * Method name: doProcess_NFCL3120Scrn00_DeleteSearch <dd>The
     * method explanation: Delete Search <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFCL3120Scrn00_DeleteSearch(EZDCMsg cMsg, EZDSMsg sMsg) {
        NFCL3120CMsg bizMsg = (NFCL3120CMsg) cMsg;
        NFCL3120SMsg glblMsg = (NFCL3120SMsg) sMsg;

        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptPk_S)) {
            bizMsg.srchOptPk_S.setErrorInfo(1, NFCM0859E, new String[] {"Saved Search Options" });
            return;
        }

        NFCL3120CommonLogic.callNszc0330forDeleteSearch(bizMsg, glblMsg, getContextUserInfo().getUserId(), this.getGlobalCompanyCode());
    }

}
