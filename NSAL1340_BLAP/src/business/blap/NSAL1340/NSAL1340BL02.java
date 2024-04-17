/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL1340;

import static business.blap.NSAL1340.constant.NSAL1340Constant.NSAM0627E;
import static business.blap.NSAL1340.constant.NSAL1340Constant.NZZM0000E;

import java.math.BigDecimal;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NSAL1340.common.NSAL1340CommonLogic;

import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * NSAL1340BL02
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/05/09   Hitachi         Y.Osawa         Create          N/A
 *</pre>
 */
public class NSAL1340BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NSAL1340CMsg bizMsg = (NSAL1340CMsg) cMsg;
            NSAL1340SMsg glblMsg = (NSAL1340SMsg) sMsg;

            if ("NSAL1340_INIT".equals(screenAplID)) {
                doProcess_NSAL1340_INIT(bizMsg, glblMsg);

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
    private void doProcess_NSAL1340_INIT(NSAL1340CMsg bizMsg, NSAL1340SMsg glblMsg) {
        if (!NSAL1340CommonLogic.getPrcListDispNm(bizMsg, getGlobalCompanyCode())) {
            bizMsg.setMessageInfo(NSAM0627E, new String[] {"Price List"});
            return;
        }
        if (!NSAL1340CommonLogic.getPrcMtrPkgNm(bizMsg, getGlobalCompanyCode())) {
            bizMsg.setMessageInfo(NSAM0627E, new String[] {"Package Name"});
            return;
        }
        if (!NSAL1340CommonLogic.getBllgCntrNm(bizMsg, getGlobalCompanyCode())) {
            bizMsg.setMessageInfo(NSAM0627E, new String[] {"Billing Counter Name"});
            return;
        }
        if (NSAL1340CommonLogic.getBandList(bizMsg)) {
            bizMsg.xxRadioBtn.setValue(BigDecimal.ZERO);
        } else {
            bizMsg.setMessageInfo(NZZM0000E);
            return;
        }
    }

}
