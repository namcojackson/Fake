/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NFAL0230;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NFAL0230.common.NFAL0230CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/23   Fujitsu         T.Murai         Create          N/A
 * </pre>
 */
public class NFAL0230BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            NFAL0230CMsg bizMsg = (NFAL0230CMsg) cMsg;
            NFAL0230SMsg glblMsg = (NFAL0230SMsg) sMsg;

            if ("NFAL0230_INIT".equals(screenAplID)) {
                doProcess_NFAL0230_INIT(bizMsg, glblMsg);

            } else if ("NFAL0230Scrn00_CMN_Download".equals(screenAplID)) {
                doProcess_NFAL0230Scrn00_CMN_Download(bizMsg);

            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * do Process (Init)
     * @param bizMsg NFAL0230CMsg
     * @param glblMsg NFAL0230SMsg
     */
    private void doProcess_NFAL0230_INIT(NFAL0230CMsg bizMsg, NFAL0230SMsg glblMsg) {

        // Initial Screen Objects
        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(glblMsg.A);

        // Setting Initial Values
        String glblCmpyCd = getGlobalCompanyCode();
        ZYPEZDItemValueSetter.setValue(bizMsg.glblCmpyCd, glblCmpyCd);

        NFAL0230CommonLogic.search(bizMsg, glblMsg);

    }

    /**
     * do Process (CMN_Submit)
     * @param bizMsg NFAL0230CMsg
     * @param glblMsg NFAL0230SMsg
     */
    private void doProcess_NFAL0230Scrn00_CMN_Download(NFAL0230CMsg bizMsg) {

        NFAL0230CommonLogic.csvDownload(bizMsg);

    }
}
