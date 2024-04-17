/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL0710;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.*;
import static business.blap.NSAL0710.constant.NSAL0710Constant.*;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NSAL0710.common.NSAL0710CommonLogic;

import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Update Read Methods
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/18   Hitachi         K.Kasai         Create          N/A
 * 2016/11/18   Hitachi         T.Mizuki        Update          QC#4210
 *</pre>
 */
public class NSAL0710BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {
        NSAL0710CMsg cMsg = (NSAL0710CMsg) arg0;
        // mod start 2016/11/18 CSA QC#4210
        NSAL0710SMsg sMsg = (NSAL0710SMsg) arg1;
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("NSAL0710Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NSAL0710Scrn00_CMN_Submit(cMsg, sMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NSAL0710Scrn00_CMN_Submit(NSAL0710CMsg cMsg, NSAL0710SMsg sMsg) {

        NSAL0710CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);
        //Validation
        if (!checkExistSelectCheckbox(sMsg)) {
            cMsg.setMessageInfo(NSAM0015E);
            return;
        }
        //Update Process
        NSAL0710CommonLogic.updateMtrReadMeth(cMsg, sMsg);

        NSAL0710CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
    }

    /**
     * @param sMsg
     * @param existCheck
     * @return
     */
    private boolean checkExistSelectCheckbox(NSAL0710SMsg sMsg) {
        boolean existCheck = false;
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (FLG_ON_Y.equals(sMsg.A.no(i).xxChkBox_A1.getValue())
                    || FLG_ON_Y.equals(sMsg.A.no(i).xxChkBox_A2.getValue())) {
                // mod end 2016/11/18 CSA QC#4210
                existCheck = true;
                break;
            }
        }
        return existCheck;
    }
}
