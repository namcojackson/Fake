package business.blap.NSAL0750;

import static business.blap.NSAL0750.constant.NSAL0750Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;

import business.blap.NSAL0750.common.NSAL0750CommonLogic;

import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 *
 * Update PO Information
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/19   Hitachi         J.Kim           Create          N/A
 * 2016/12/08   Hitachi         T.Mizuki        Update          QC#4210
 *</pre>
 */
public class NSAL0750BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {
        NSAL0750CMsg cMsg = (NSAL0750CMsg) arg0;
        NSAL0750SMsg sMsg = (NSAL0750SMsg) arg1;

        super.preDoProcess(cMsg, sMsg);
        String screenAplId = cMsg.getScreenAplID();
        try {
            if (screenAplId.equals("NSAL0750Scrn00_CMN_Submit")) {
                doProcess_NSAL0750Scrn00_CMN_Submit(cMsg, sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplId);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * Submit event handler
     * @param cMsg NSAL0750CMsg
     * @param sMsg NSAL0750SMsg
     */
    private void doProcess_NSAL0750Scrn00_CMN_Submit(NSAL0750CMsg cMsg, NSAL0750SMsg sMsg) {

        // mod start 2016/12/08 CSA QC#4210
        NSAL0750CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);
        if (!NSAL0750CommonLogic.checkSelect(cMsg, sMsg)) {
        // mod end 2016/12/08 CSA QC#4210
            cMsg.setMessageInfo(NSAM0015E);
            return;
        }

        if (!hasValue(cMsg.baseBllgTmgCd_H3)) {
            cMsg.baseBllgTmgCd_H3.setErrorInfo(1, ZZM9000E, new String[] {"Invoicing Rule" });
            return;
        }

        NSAL0750CommonLogic.submitContractDetailInfo(cMsg, sMsg);
    }
}
