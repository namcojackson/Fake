/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL0700;

import static business.blap.NSAL0700.constant.NSAL0700Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.FLG_ON_Y;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NSAL0700.common.NSAL0700CommonLogic;

import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Contract On Billing Hold
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/04   Hitachi         K.Kasai         Create          N/A
 * 2016/11/28   Hitachi         T.Mizuki        Update          QC#4210
 *</pre>
 */
public class NSAL0700BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {
        NSAL0700CMsg cMsg = (NSAL0700CMsg) arg0;
        // mod start 2016/11/28 CSA QC#4210
        NSAL0700SMsg sMsg = (NSAL0700SMsg) arg1;
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("NSAL0700Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NSAL0700Scrn00_CMN_Submit(cMsg, sMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NSAL0700Scrn00_CMN_Submit(NSAL0700CMsg cMsg, NSAL0700SMsg sMsg) {

        NSAL0700CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);
        //Validation
        if (!checkExistSelectCheckbox(sMsg)) {
            cMsg.setMessageInfo(NSAM0015E);
            return;
        }

        NSAL0700CommonLogic.changeBllgHold(cMsg, sMsg);

        int noCmsg = cMsg.A.no(0).xxRowNum_A1.getValueInt();
        cMsg.A.clear();
        int count = 0;
        for (int i = noCmsg; i < sMsg.A.getValidCount(); i++) {
            if (count < cMsg.A.length()) {
                EZDMsg.copy(sMsg.A.get(i), null, cMsg.A.get(count), null);
                count++;
            } else {
                break;
            }
        }
        cMsg.A.setValidCount(count);
    }

    /**
     * @param sMsg
     * @return
     */
    private boolean checkExistSelectCheckbox(NSAL0700SMsg sMsg) {
        boolean existCheck = false;
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (FLG_ON_Y.equals(sMsg.A.no(i).xxChkBox_A1.getValue())
                    || FLG_ON_Y.equals(sMsg.A.no(i).xxChkBox_A2.getValue())
                    || FLG_ON_Y.equals(sMsg.A.no(i).xxChkBox_A3.getValue())) {
            // mod end 2016/11/28 CSA QC#4210
                existCheck = true;
                break;
            }
        }
        return existCheck;
    }
}
