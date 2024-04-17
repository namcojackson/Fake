/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL2090;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;

import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * NWAL2090BL02
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/09   Fujitsu         C.Yokoi         Create          N/A
 *</pre>
 */
public class NWAL2090BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NWAL2090CMsg bizMsg = (NWAL2090CMsg) cMsg;
            NWAL2090SMsg glblMsg = (NWAL2090SMsg) sMsg;

            if ("NWAL2090_INIT".equals(screenAplID)) {
                doProcess_NWAL2090_INIT(bizMsg, glblMsg);

            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * INIT Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL2090_INIT(NWAL2090CMsg bizMsg, NWAL2090SMsg glblMsg) {
        ZYPCodeDataUtil.createPulldownList("CHNG_RSN_TP", bizMsg.chngRsnTpCd_CD, bizMsg.chngRsnTpNm_DI);
    }
}
