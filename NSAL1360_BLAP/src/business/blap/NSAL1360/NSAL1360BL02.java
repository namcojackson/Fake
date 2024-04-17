/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL1360;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;

import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * NSAL1360BL02
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/04/06   Hitachi         Y.Osawa         Create          N/A
 *</pre>
 */
public class NSAL1360BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NSAL1360CMsg bizMsg = (NSAL1360CMsg) cMsg;

            if ("NSAL1360_INIT".equals(screenAplID)) {
                doProcess_NSAL1360_INIT(bizMsg);

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
    private void doProcess_NSAL1360_INIT(NSAL1360CMsg bizMsg) {
         ZYPCodeDataUtil.createPulldownList("MTR_READ_METH", bizMsg.mtrReadMethCd_L, bizMsg.mtrReadMethDescTxt_L);
    }


}
