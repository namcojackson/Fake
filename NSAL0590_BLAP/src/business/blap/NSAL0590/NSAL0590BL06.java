package business.blap.NSAL0590;

import static business.blap.NSAL0590.constant.NSAL0590Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;

import business.blap.NSAL0590.common.NSAL0590CommonLogic;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 *
 * Report Group
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/30   Hitachi         J.Kim           Create          N/A
 *</pre>
 */
public class NSAL0590BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {
        NSAL0590CMsg cMsg = (NSAL0590CMsg) arg0;
        NSAL0590SMsg sMsg = (NSAL0590SMsg) arg1;

        super.preDoProcess(cMsg, sMsg);
        String screenAplId = cMsg.getScreenAplID();
        try {
            if (screenAplId.equals("NSAL0590Scrn00_CMN_Submit")) {
                doProcess_NSAL0590Scrn00_CMN_Submit(cMsg, sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplId);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NSAL0590Scrn00_CMN_Submit(NSAL0590CMsg cMsg, NSAL0590SMsg sMsg) {

        NSAL0590CommonLogic.copyCMsgToSMsg(cMsg, sMsg);

        for (int idx = 0; idx < cMsg.A.getValidCount(); idx++) {
            NSAL0590_ACMsg acMsg = cMsg.A.no(idx);
            if (!hasValue(acMsg.dsContrRptGrpDescTxt)) {
                cMsg.setMessageInfo(NSAM0212E, new String[] {"Description" });
                return;
            }

            if (!hasValue(acMsg.dsContrRptGrpStartDt)) {
                cMsg.setMessageInfo(NSAM0212E, new String[] {"Start Date" });
                return;
            }

            if (hasValue(acMsg.dsContrRptGrpEndDt)) {
                if (ZYPDateUtil.compare(acMsg.dsContrRptGrpStartDt.getValue(), acMsg.dsContrRptGrpEndDt.getValue()) > 0) {
                    cMsg.setMessageInfo(NSAM0346E, new String[] {"End Date", "Start Date" });
                    return;
                }
            }
        }

        NSAL0590CommonLogic.submitDsContrRptGrp(cMsg, sMsg);
    }
}
