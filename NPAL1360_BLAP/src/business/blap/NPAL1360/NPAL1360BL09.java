/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NPAL1360;

import static business.blap.NPAL1360.constant.NPAL1360Constant.BTN_PRINT;
import static business.blap.NPAL1360.constant.NPAL1360Constant.EVENT_NM_NPAL1360_PRINT;
import static business.blap.NPAL1360.constant.NPAL1360Constant.NPAM1527E;
import static business.blap.NPAL1360.constant.NPAL1360Constant.ZZZM9003I;
import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NPAL1360.common.NPAL1360CommonLogic;

import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Business ID : NPAL1360 Kitting Work Order Entry
 * Function Name : Report
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/01/2016   CITS            Keiichi Masaki  Create          N/A
 *</pre>
 */

public class NPAL1360BL09 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();

            if (EVENT_NM_NPAL1360_PRINT.equals(screenAplID)) {
                doProcess_NPAL1360_Print((NPAL1360CMsg) cMsg, (NPAL1360SMsg) sMsg);

            } else {
                return;
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * Print
     * @param cMsg NPAL1360CMsg
     * @param sMsg NPAL1360SMsg
     */
    private void doProcess_NPAL1360_Print(NPAL1360CMsg cMsg, NPAL1360SMsg sMsg) {

        if (!cMsg.wrkOrdNum.getValue().equals(sMsg.wrkOrdNum.getValue())) {
            cMsg.wrkOrdNum.setErrorInfo(1, NPAM1527E);
            return;
        }

        if (!NPAL1360CommonLogic.print(cMsg)) {
            return;
        }

        cMsg.setMessageInfo(ZZZM9003I, new String[] {BTN_PRINT });

    }

}
