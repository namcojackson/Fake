/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NLEL0080;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NLEL0080.common.NLEL0080CommonLogic;

import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * DS Asset Manual Entry
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/25   Hitachi         J.Kim           Create          N/A
 *</pre>
 */
public class NLEL0080BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {

        NLEL0080CMsg cMsg = (NLEL0080CMsg) arg0;

        super.preDoProcess(cMsg, null);

        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("NLEL0080_INIT".equals(screenAplID)) {
                doProcess_NLEL0080_INIT(cMsg);
            } else if ("NLEL0080Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NLEL0080Scrn00_CMN_Clear(cMsg);
            } else if ("NLEL0080Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NLEL0080Scrn00_CMN_Reset(cMsg);
            } else if ("NLEL0080Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NLEL0080Scrn00_CMN_Submit(cMsg);
            } else if ("NLEL0080Scrn00_Delete".equals(screenAplID)) {
                doProcess_NLEL0080Scrn00_Delete(cMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
        } finally {
            super.postDoProcess(cMsg, null);
        }
    }

    private void doProcess_NLEL0080_INIT(NLEL0080CMsg cMsg) {

        doProcessInit(cMsg);

    }

    private void doProcess_NLEL0080Scrn00_CMN_Clear(NLEL0080CMsg cMsg) {

        doProcessInit(cMsg);

    }

    private void doProcess_NLEL0080Scrn00_CMN_Reset(NLEL0080CMsg cMsg) {

        doProcessInit(cMsg);

    }

    private void doProcess_NLEL0080Scrn00_CMN_Submit(NLEL0080CMsg cMsg) {
        return;
    }

    private void doProcess_NLEL0080Scrn00_Delete(NLEL0080CMsg cMsg) {

        if (!NLEL0080CommonLogic.checkSelect(cMsg)) {
            return;
        }
    }

    private void doProcessInit(NLEL0080CMsg cMsg) {

        NLEL0080CommonLogic.clearMsg(cMsg);

        cMsg.glblCmpyCd.setValue(getGlobalCompanyCode());
        NLEL0080CommonLogic.createPullDown(cMsg);
    }
}
