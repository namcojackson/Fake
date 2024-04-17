/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NPAL1540;

import static business.blap.NPAL1540.constant.NPAL1540Constant.EVENT_NM_NPAL1540_CMN_COL_CLEAR;
import static business.blap.NPAL1540.constant.NPAL1540Constant.EVENT_NM_NPAL1540_CMN_COL_SAVE;
import static business.blap.NPAL1540.constant.NPAL1540Constant.EVENT_NM_NPAL1540_CMN_SUBMIT;
import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NPAL1540.common.NPAL1540CommonLogic;

import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * Business ID : NPAL1540 Manual ASN Entry
 * Function Name : update business process
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/20/2016   CITS         Makoto Okigami     Create          N/A
 *</pre>
 */
public class NPAL1540BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if (EVENT_NM_NPAL1540_CMN_SUBMIT.equals(screenAplID)) {
                doProcess_CMN_Submit((NPAL1540CMsg) cMsg, (NPAL1540SMsg) sMsg);
            } else if (EVENT_NM_NPAL1540_CMN_COL_CLEAR.equals(screenAplID)) {
                ZYPGUITableColumn.clearColData((NPAL1540CMsg) cMsg, (NPAL1540SMsg) sMsg);
            } else if (EVENT_NM_NPAL1540_CMN_COL_SAVE.equals(screenAplID)) {
                ZYPGUITableColumn.setColData((NPAL1540CMsg) cMsg, (NPAL1540SMsg) sMsg);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * Submit
     * @param cMsg NPAL1540CMsg
     * @param sMsg NPAL1540SMsg
     */
    private void doProcess_CMN_Submit(NPAL1540CMsg cMsg, NPAL1540SMsg sMsg) {

        String glblCmpyCd = getGlobalCompanyCode();

        NPAL1540CommonLogic.updateSMsg(cMsg, sMsg);
        NPAL1540CommonLogic.submit(cMsg, sMsg, glblCmpyCd);
        NPAL1540CommonLogic.loadOnePageToCMsg(cMsg, cMsg.A, sMsg, sMsg.A);
    }

}
