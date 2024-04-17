/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL2350;

import static business.blap.NWAL2350.constant.NWAL2350Constant.EVENT_NM_NWAL2350_INIT;
import static business.blap.NWAL2350.constant.NWAL2350Constant.NMAM0038I;
import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NWAL2350.common.NWAL2350CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * SOM Profitability
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/29   CITS            S.Tanikawa      Create          N/A
 *</pre>
 */
public class NWAL2350BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if (EVENT_NM_NWAL2350_INIT.equals(screenAplID)) {
                doProcess_NWAL2350_INIT((NWAL2350CMsg) cMsg, (NWAL2350SMsg) sMsg);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NWAL2350_INIT(NWAL2350CMsg cMsg, NWAL2350SMsg sMsg) {

        String glblCmpyCd = getGlobalCompanyCode();

        if (!ZYPCommonFunc.hasValue(cMsg.dsImptOrdPk)) {
            cMsg.setMessageInfo(NMAM0038I);
        } else {
            NWAL2350CommonLogic.search(cMsg, sMsg, glblCmpyCd);
        }
    }
}
