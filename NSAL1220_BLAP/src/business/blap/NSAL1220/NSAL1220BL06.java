/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL1220;

import static business.blap.NSAL1220.constant.NSAL1220Constant.COA_BR_CD;
import static business.blap.NSAL1220.constant.NSAL1220Constant.ZZZM9001E;
import static business.blap.NSAL1220.constant.NSAL1220Constant.NSAM0156E;
import static business.blap.NSAL1220.constant.NSAL1220Constant.NSAM0035E;
import static business.blap.NSAL1220.constant.NSAL1220Constant.TOTAL_RATE;
import java.math.BigDecimal;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;

import business.blap.NSAL1220.common.NSAL1220CommonLogic;

import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/** 
 *<pre>
 * Contract Branch Revenue Distribution
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/16   Hitachi         T.Tsuchida      Create          N/A
 *</pre>
 */
public class NSAL1220BL06 extends S21BusinessHandler {
    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {
        NSAL1220CMsg cMsg = (NSAL1220CMsg) arg0;
        super.preDoProcess(cMsg, null);

        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("NSAL1220Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NSAL1220Scrn00_CMN_Submit(cMsg);
            }
        } finally {
            super.postDoProcess(cMsg, null);
        }
    }

    private void doProcess_NSAL1220Scrn00_CMN_Submit(NSAL1220CMsg cMsg) {
        NSAL1220CommonLogic.calc(cMsg);
        if (cMsg.A.getValidCount() == 0) {
            cMsg.setMessageInfo(ZZZM9001E);
            return;
        }
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            for (int j = i + 1; j < cMsg.A.getValidCount(); j++) {
                if ((cMsg.A.no(i).coaBrCd_A.getValue()).equals(cMsg.A.no(j).coaBrCd_A.getValue())) {
                    cMsg.A.no(i).coaBrCd_A.setErrorInfo(1, NSAM0035E, new String[] {COA_BR_CD });
                    cMsg.A.no(j).coaBrCd_A.setErrorInfo(1, NSAM0035E, new String[] {COA_BR_CD });
                    return;
                }
            }
        }

        if (cMsg.prcAllocRate_T.getValue().compareTo(BigDecimal.valueOf(TOTAL_RATE)) != 0) {
            cMsg.setMessageInfo(NSAM0156E);
            return;
        }

        if (!NSAL1220CommonLogic.checkMaster(cMsg)) {
            return;
        } else {
            NSAL1220CommonLogic.insertBranch(cMsg);
        }
    }

}
