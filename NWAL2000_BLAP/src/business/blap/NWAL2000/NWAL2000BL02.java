/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL2000;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;

import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * NWAL2000BL02
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/08   Fujitsu         C.Yokoi         Create          N/A
 *</pre>
 */
public class NWAL2000BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NWAL2000CMsg bizMsg = (NWAL2000CMsg) cMsg;

            if ("NWAL2000_INIT".equals(screenAplID)) {
                doProcess_NWAL2000_INIT(bizMsg);

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
     */
    private void doProcess_NWAL2000_INIT(NWAL2000CMsg bizMsg) {
       ZYPCodeDataUtil.createPulldownList("CONFIG_CATG", bizMsg.configCatgCd_CD, bizMsg.configCatgDescTxt_DI);
       ZYPCodeDataUtil.createPulldownList("CHNG_RSN_TP", bizMsg.chngRsnTpCd_L0, bizMsg.chngRsnTpDescTxt_L0);
    }
}
