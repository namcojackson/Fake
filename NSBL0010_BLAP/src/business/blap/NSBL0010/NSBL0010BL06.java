/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSBL0010;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NSBL0010.common.NSBL0010CommonLogic;
import business.blap.NSBL0010.constant.NSBL0010Constant;

import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * Service Dispatch Schedule/Dispatch Screen
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/04/29   SRA             E.Inada         Create          N/A
 *</pre>
 */
public class NSBL0010BL06 extends S21BusinessHandler implements NSBL0010Constant {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            // +++++ [START] : Programming Area +++++++++++++++++++++

            String screenAplID = cMsg.getScreenAplID();

            NSBL0010CMsg bizMsg = (NSBL0010CMsg) cMsg;
            NSBL0010SMsg shareMsg = (NSBL0010SMsg) sMsg;

            if ("NSBL0010Scrn00_Schedule".equals(screenAplID)) {
                doProcess_NSBL0010Scrn00_Schedule(bizMsg, shareMsg);

            } else if ("NSBL0010Scrn00_Dispatch".equals(screenAplID)) {
                doProcess_NSBL0010Scrn00_Dispatch(bizMsg, shareMsg);

            } else if ("NSBL0010Scrn00_Cancel".equals(screenAplID)) {
                doProcess_NSBL0010Scrn00_Cancel(bizMsg, shareMsg);

            } else if ("NSBL0010Scrn00_Accept".equals(screenAplID)) {
                doProcess_NSBL0010Scrn00_Accept(bizMsg, shareMsg);

            } else if ("NSBL0010Scrn00_UpdateETA".equals(screenAplID)) {
                doProcess_NSBL0010Scrn00_UpdateETA(bizMsg, shareMsg);

            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

            // +++++ [E N D] : Programming Area ++++++++++++++++++++++
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * Method name: doProcess_NSBL0010Scrn00_Schedule
     * @param bizMsg NSBL0010CMsg
     * @param shareMsg NSBL0010SMsg
     */
    private void doProcess_NSBL0010Scrn00_Schedule(NSBL0010CMsg bizMsg, NSBL0010SMsg shareMsg) {

        NSBL0010CommonLogic.callApi(bizMsg, shareMsg, MODE_SCHEDULE);
    }

    /**
     * Method name: doProcess_NSBL0010Scrn00_Dispatch
     * @param bizMsg NSBL0010CMsg
     * @param shareMsg NSBL0010SMsg
     */
    private void doProcess_NSBL0010Scrn00_Dispatch(NSBL0010CMsg bizMsg, NSBL0010SMsg shareMsg) {

        NSBL0010CommonLogic.callApi(bizMsg, shareMsg, MODE_DISPATCH);
    }

    /**
     * Method name: doProcess_NSBL0010Scrn00_Cancel
     * @param bizMsg NSBL0010CMsg
     * @param shareMsg NSBL0010SMsg
     */
    private void doProcess_NSBL0010Scrn00_Cancel(NSBL0010CMsg bizMsg, NSBL0010SMsg shareMsg) {

        NSBL0010CommonLogic.callApi(bizMsg, shareMsg, MODE_CANCEL);
    }

    /**
     * Method name: doProcess_NSBL0010Scrn00_Accept
     * @param bizMsg NSBL0010CMsg
     * @param shareMsg NSBL0010SMsg
     */
    private void doProcess_NSBL0010Scrn00_Accept(NSBL0010CMsg bizMsg, NSBL0010SMsg shareMsg) {
        NSBL0010CommonLogic.accept(bizMsg, shareMsg, getGlobalCompanyCode());
    }

    /**
     * Method name: doProcess_NSBL0010Scrn00_UpdateETA
     * @param bizMsg NSBL0010CMsg
     * @param shareMsg NSBL0010SMsg
     */
    private void doProcess_NSBL0010Scrn00_UpdateETA(NSBL0010CMsg bizMsg, NSBL0010SMsg shareMsg) {
        NSBL0010CommonLogic.updateETA(bizMsg, shareMsg, getGlobalCompanyCode());
    }
}
