package business.blap.NMAL7140;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;

import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_PRMO_QLFY_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;

/**
 *<pre>
 * NMAL7140BL02
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2019/12/06   Fujitsu         S.Kosaka        Create          N/A
 *</pre>
 */
public class NMAL7140BL02 extends S21BusinessHandler {
    /**
     * doProcess
     * @param cMsg Business Msg
     * @param sMsg Global Msg
     */
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NMAL7140CMsg bizMsg = (NMAL7140CMsg) cMsg;
            NMAL7140SMsg glblMsg = (NMAL7140SMsg) sMsg;

            if ("NMAL7140_INIT".equals(screenAplID)) {
                doProcess_NMAL7140_INIT(bizMsg, glblMsg);
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
    private void doProcess_NMAL7140_INIT(NMAL7140CMsg bizMsg, NMAL7140SMsg glblMsg) {
        createPullDown(bizMsg);
    }

    /**
     * Create pull down
     * @param bizMsg Business Msg
     */
    private void createPullDown(NMAL7140CMsg bizMsg) {
        ZYPCodeDataUtil.createPulldownList(PRC_PRMO_QLFY_TP.class, bizMsg.prcPrmoQlfyTpCd_L, bizMsg.prcPrmoQlfyTpDescTxt_L);
    }
}
