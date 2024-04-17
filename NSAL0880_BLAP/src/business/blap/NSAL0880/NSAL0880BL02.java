/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0880;


import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NSAL0880.common.NSAL0880CommonLogic;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Meter Reading Counter for Interface Popup
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/30   Hitachi         K.Kasai         Create          N/A
 *</pre>
 */
public class NSAL0880BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplId = cMsg.getScreenAplID();

            if ("NSAL0880_INIT".equals(screenAplId)) {
                doProcess_NSAL0880_INIT((NSAL0880CMsg) cMsg);

            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplId);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * doProcess_NSAL0880_INIT
     * @param cMsg NSAL0880CMsg
     */
    private void doProcess_NSAL0880_INIT(NSAL0880CMsg cMsg) {
        cMsg.glblCmpyCd.setValue(getGlobalCompanyCode());
        cMsg.slsDt.setValue(ZYPDateUtil.getSalesDate());
        NSAL0880CommonLogic.searchMtrIntfc(cMsg);
    }
}
