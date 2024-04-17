/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL7410;

import static business.blap.NMAL7410.constant.NMAL7410Constant.DO_PROCESS_SUBMIT;
import static business.blap.NMAL7410.constant.NMAL7410Constant.ZZZM9003I;
import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NMAL7410.common.NMAL7410CommonLogic;

import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/29   Fujitsu         T.Murai         Create          N/A
 * </pre>
 */
public class NMAL7410BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            NMAL7410CMsg bizMsg = (NMAL7410CMsg) cMsg;
            NMAL7410SMsg glblMsg = (NMAL7410SMsg) sMsg;

            if ("NMAL7410Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NMAL7410Scrn00_CMN_Submit(bizMsg, glblMsg);
            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * do Process (CMN_Submit)
     * @param bizMsg NMAL7410CMsg
     * @param glblMsg NMAL7410SMsg
     * @param isCallSave Called Save Button
     */
    private void doProcess_NMAL7410Scrn00_CMN_Submit(NMAL7410CMsg bizMsg, NMAL7410SMsg glblMsg) {

        // Copy cMsg Check Info
        NMAL7410CommonLogic.copyFromCMsgOntoSmsg(bizMsg, glblMsg);


        // Delete Line - CSMP_PRC_XREF
        if (!NMAL7410CommonLogic.deleteXref(bizMsg, glblMsg)) {
            return;
        }

        // Register Line - CSMP_PRC_XREF
        if (!NMAL7410CommonLogic.registXref(bizMsg, glblMsg)) {
            return;
        }
        bizMsg.setMessageInfo(ZZZM9003I, new String[] {DO_PROCESS_SUBMIT });
    }
}
