/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSBL0430;

import static business.blap.NSBL0430.constant.NSBL0430Constant.NSBM0005I;
import parts.common.EZDCMsg;
import parts.common.EZDSMsg;

import business.blap.NSBL0430.common.NSBL0430CommonLogic;

import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Mods Serial# Assignment
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/28   Hitachi         O.Okuma         Create          N/A
 * 2016/02/29   Hitachi         O.Okuma         Update          QC4723
 *</pre>
 */
public class NSBL0430BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {
        NSBL0430CMsg cMsg = (NSBL0430CMsg) arg0;
        NSBL0430SMsg sMsg = (NSBL0430SMsg) arg1;
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("NSBL0430Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NSBL0430Scrn00_CMN_Submit(cMsg, sMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NSBL0430Scrn00_CMN_Submit(NSBL0430CMsg cMsg, NSBL0430SMsg sMsg) {

        cMsg.setCommitSMsg(true);
        NSBL0430CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);
        if (!NSBL0430CommonLogic.submitSvcModSerRng(cMsg, sMsg)) {
            int errPageFromNum = NSBL0430CommonLogic.getErrPageFromNum(cMsg, sMsg);
            NSBL0430CommonLogic.pagenation(cMsg, sMsg, errPageFromNum);
        } else {
            cMsg.setMessageInfo(NSBM0005I);
        }
    }

}
