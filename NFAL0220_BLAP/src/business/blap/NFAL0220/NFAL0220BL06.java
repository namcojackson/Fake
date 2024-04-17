/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NFAL0220;

import static business.blap.NFAL0220.constant.NFAL0220Constant.*;
import parts.common.EZDCMsg;
import parts.common.EZDSMsg;

import business.blap.NFAL0220.common.NFAL0220CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Manual Journal Entry
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/02   Fujitsu         S.Fujita        Create          N/A
 * 2016/06/21   Hitachi         J.Kim           Update          QC#10325
 *</pre>
 */
public class NFAL0220BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {
        NFAL0220CMsg cMsg = (NFAL0220CMsg) arg0;
        NFAL0220SMsg sMsg = (NFAL0220SMsg) arg1;
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("NFAL0220Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NFAL0220Scrn00_CMN_Submit(cMsg, sMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }

    }

    private void doProcess_NFAL0220Scrn00_CMN_Submit(NFAL0220CMsg cMsg, NFAL0220SMsg sMsg) {

        int pageFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
        NFAL0220CommonLogic.setPagenation(cMsg, sMsg, pageFromNum);
        cMsg.setCommitSMsg(true);

        // Cancel
        if (ZYPConstant.CHKBOX_ON_Y.equals(cMsg.xxChkBox_C.getValue())) {
            if (!NFAL0220CommonLogic.deleteManualJournalEntry(cMsg, sMsg)) {
                return;
            }
            cMsg.setMessageInfo(ZZSM4116I, new String[] {"Journal Entry" });
            cMsg.manJrnlEntryHdrPk.clear();
        } else {
            if (!NFAL0220CommonLogic.isErrorSubmitCondition(cMsg, sMsg)) {
                int rowNum = sMsg.E.no(0).xxErrNum.getValueInt();
                int pageNum = NFAL0220CommonLogic.convertPageNumToFirstRowIndex(cMsg.A.length(), rowNum);
                NFAL0220CommonLogic.pagenation(cMsg, sMsg, pageNum);
                return;
            }

            NFAL0220CommonLogic.createManualJournalEntry(cMsg, sMsg);
            NFAL0220CommonLogic.pagenation(cMsg, sMsg, pageFromNum);
        }
    }
}
