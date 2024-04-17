/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NLEL0080;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NLEL0080.common.NLEL0080CommonLogic;

import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * DS Asset Manual Entry
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/25   Hitachi         J.Kim           Create          N/A
 * 2016/04/08   Hitachi         T.Tsuchida      Update          QC#6769
 *</pre>
 */
public class NLEL0080BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {

        NLEL0080CMsg cMsg = (NLEL0080CMsg) arg0;

        super.preDoProcess(cMsg, null);

        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("NLEL0080Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NLEL0080Scrn00_CMN_Submit(cMsg);
            }
        } finally {
            super.postDoProcess(cMsg, null);
        }
    }

    private void doProcess_NLEL0080Scrn00_CMN_Submit(NLEL0080CMsg cMsg) {

        if (!NLEL0080CommonLogic.executeInsertDsAssetMstrDtl(cMsg)) {
            return;
        }
    }
}
