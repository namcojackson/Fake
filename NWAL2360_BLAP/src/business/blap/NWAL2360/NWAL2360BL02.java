/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL2360;

import static business.blap.NWAL2360.constant.NWAL2360Constant.EVENT_NM_NWAL2360_INIT;
import static business.blap.NWAL2360.constant.NWAL2360Constant.NMAM0038I;
import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NWAL2360.common.NWAL2360CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * SOM Approval Detail
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/29   CITS            S.Tanikawa      Create          N/A
 *</pre>
 */
public class NWAL2360BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if (EVENT_NM_NWAL2360_INIT.equals(screenAplID)) {
                doProcess_NWAL2360_INIT((NWAL2360CMsg) cMsg, (NWAL2360SMsg) sMsg);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NWAL2360_INIT(NWAL2360CMsg cMsg, NWAL2360SMsg sMsg) {

        String glblCmpyCd = getGlobalCompanyCode();

        if (!ZYPCommonFunc.hasValue(cMsg.dsImptOrdPk)) {
            cMsg.setMessageInfo(NMAM0038I);
        } else {
            NWAL2360CommonLogic.search(cMsg, sMsg, glblCmpyCd);
        }
    }
}
