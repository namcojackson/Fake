/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL0660;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NSAL0660.common.NSAL0660CommonLogic;

import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Add general Notes
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/02   Hitachi         K.Kasai         Create          N/A
 * 2016/12/06   Hitachi         T.Mizuki        Update          QC#4210
 *</pre>
 */
public class NSAL0660BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {
        NSAL0660CMsg cMsg = (NSAL0660CMsg) arg0;
        // mod start 2016/12/06 CSA QC#4210
        NSAL0660SMsg sMsg = (NSAL0660SMsg) arg1;
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("NSAL0660Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NSAL0660Scrn00_CMN_Submit(cMsg, sMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NSAL0660Scrn00_CMN_Submit(NSAL0660CMsg cMsg, NSAL0660SMsg sMsg) {
        NSAL0660CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);
        NSAL0660CommonLogic.insertSvcMemo(cMsg, sMsg);

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
    // mod end 2016/12/06 CSA QC#4210
}
