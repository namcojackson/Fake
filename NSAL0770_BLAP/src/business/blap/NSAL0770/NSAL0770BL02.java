/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL0770;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NSAL0770.common.NSAL0770CommonLogic;

import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Change Status Audit
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/06   Hitachi         K.Kishimoto     Create          N/A
 *</pre>
 */
public class NSAL0770BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {
        NSAL0770CMsg cMsg = (NSAL0770CMsg) arg0;
        NSAL0770SMsg sMsg = (NSAL0770SMsg) arg1;
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("NSAL0770_INIT".equals(screenAplID)) {
                doProcess_NSAL0770_INIT(cMsg, sMsg);
            } else if ("NSAL0770Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NSAL0770Scrn00_CMN_Clear(cMsg, sMsg);
            } else if ("NSAL0770Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NSAL0770Scrn00_PageNext(cMsg, sMsg);
            } else if ("NSAL0770Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NSAL0770Scrn00_PagePrev(cMsg, sMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NSAL0770_INIT(NSAL0770CMsg cMsg, NSAL0770SMsg sMsg) {

        NSAL0770CommonLogic.clearMsg(cMsg, sMsg);

        NSAL0770CommonLogic.getSearchData(cMsg, sMsg);

        NSAL0770CommonLogic.pagenation(cMsg, sMsg, 0);
        cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
    }

    private void doProcess_NSAL0770Scrn00_CMN_Clear(NSAL0770CMsg cMsg, NSAL0770SMsg sMsg) {

        NSAL0770CommonLogic.clearMsg(cMsg, sMsg);
        doProcess_NSAL0770_INIT(cMsg, sMsg);
    }

    private void doProcess_NSAL0770Scrn00_PageNext(NSAL0770CMsg cMsg, NSAL0770SMsg sMsg) {

        int pageFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
        NSAL0770CommonLogic.setPagenation(cMsg, sMsg, pageFromNum);

        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt() + cMsg.A.length() - 1;
        NSAL0770CommonLogic.pagenation(cMsg, sMsg, pagenationFrom);
    }

    private void doProcess_NSAL0770Scrn00_PagePrev(NSAL0770CMsg cMsg, NSAL0770SMsg sMsg) {

        int pageFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
        NSAL0770CommonLogic.setPagenation(cMsg, sMsg, pageFromNum);

        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt() - cMsg.A.length() - 1;
        NSAL0770CommonLogic.pagenation(cMsg, sMsg, pagenationFrom);
    }

}
