/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NWAL2260;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NWAL2260.common.NWAL2260CommonLogic;

import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Import Attribute Maintenance
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/24   Hitachi         O.Okuma         Create          N/A
 *</pre>
 */
public class NWAL2260BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {
        NWAL2260CMsg cMsg = (NWAL2260CMsg) arg0;

        super.preDoProcess(cMsg, null);

        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("NWAL2260_INIT".equals(screenAplID)) {
                doProcess_NWAL2260_INIT(cMsg);
            } else if ("NWAL2260Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NWAL2260Scrn00_CMN_Reset(cMsg);
            } else if ("NWAL2260Scrn00_CMN_Save".equals(screenAplID)) {
                doProcess_NWAL2260Scrn00_CMN_Save(cMsg);
            }
        } finally {
            super.postDoProcess(cMsg, null);
        }
    }

    private void doProcess_NWAL2260_INIT(NWAL2260CMsg cMsg) {

        if (!NWAL2260CommonLogic.checkParams(cMsg)) {
            return;
        }

        setValue(cMsg.glblCmpyCd, getGlobalCompanyCode());
        NWAL2260CommonLogic.clearMsg(cMsg);
        NWAL2260CommonLogic.setInitParams(cMsg);
        NWAL2260CommonLogic.getSearchData(cMsg, false);
        NWAL2260CommonLogic.checkParamsAftrSearch(cMsg);
    }

    private void doProcess_NWAL2260Scrn00_CMN_Reset(NWAL2260CMsg cMsg) {
        NWAL2260CommonLogic.clearMsg(cMsg);
        NWAL2260CommonLogic.getSearchData(cMsg, false);
    }

    private void doProcess_NWAL2260Scrn00_CMN_Save(NWAL2260CMsg cMsg) {
        NWAL2260CommonLogic.clearMsg(cMsg);
        NWAL2260CommonLogic.getSearchData(cMsg, true);
    }

}
