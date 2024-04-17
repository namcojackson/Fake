/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1540;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NWAL1540.common.NWAL1540CommonLogic;

import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * NWAL1540BL06
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/09   Fujitsu         M.Yamada        Create          N/A
 *</pre>
 */
public class NWAL1540BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NWAL1540CMsg bizMsg = (NWAL1540CMsg) cMsg;
            NWAL1540SMsg glblMsg = (NWAL1540SMsg) sMsg;

            if ("NWAL1540Scrn00_Recalculate".equals(screenAplID)) {
                doProcess_NWAL1540Scrn00_Recalculate(bizMsg, glblMsg);

            } else if ("NWAL1540Scrn00_CMN_ColSave".equals(screenAplID)) {
                ZYPGUITableColumn.setColData(bizMsg, glblMsg);

            } else if ("NWAL1540Scrn00_CMN_ColClear".equals(screenAplID)) {
                ZYPGUITableColumn.clearColData(bizMsg, glblMsg);

            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NWAL1540Scrn00_Recalculate(NWAL1540CMsg bizMsg, NWAL1540SMsg glblMsg) {
        NWAL1540CommonLogic.recalculateProfitability(bizMsg, glblMsg);
    }

}
