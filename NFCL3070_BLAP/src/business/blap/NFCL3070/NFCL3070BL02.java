/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NFCL3070;

import static business.blap.NFCL3070.constant.NFCL3070Constant.MESSAGE_KIND_ERROR;
import static business.blap.NFCL3070.constant.NFCL3070Constant.MESSAGE_KIND_WARN;
import static business.blap.NFCL3070.constant.NFCL3070Constant.NFCM0786E;
import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NFCL3070.common.NFCL3070CommonLogic;
import business.db.INVTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * NFCL3070BL02
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/25   Fujitsu         C.Yokoi         Create          N/A
 * 2016/02/19   Fujitsu         S.Fujita        Update          N/A
 *</pre>
 */
public class NFCL3070BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NFCL3070CMsg bizMsg = (NFCL3070CMsg) cMsg;
            NFCL3070SMsg glblMsg = (NFCL3070SMsg) sMsg;

            if ("NFCL3070_INIT".equals(screenAplID)) {
                doProcess_NFCL3070_INIT(bizMsg, glblMsg);

            } else if ("NFCL3070Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NFCL3070_INIT(bizMsg, glblMsg);

            } else if ("NFCL3070Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NFCL3070Scrn00_CMN_Submit(bizMsg, glblMsg);

            } else if ("NFCL3070Scrn00_CMN_Yes".equals(screenAplID)) {
                doProcess_NFCL3070Scrn00_CMN_Submit(bizMsg, glblMsg);

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
    private void doProcess_NFCL3070_INIT(NFCL3070CMsg bizMsg, NFCL3070SMsg glblMsg) {
        bizMsg.glblCmpyCd.setValue(getGlobalCompanyCode());

        // set pulldown "Type of Credit"
        NFCL3070CommonLogic.setTypeOfCredit(bizMsg);
        // check exists Customer Invoice Number
        if (ZYPCommonFunc.hasValue(bizMsg.origInvNum)) {
            INVTMsg invTMsg = NFCL3070CommonLogic.getInvTMsg(bizMsg);

            if (!S21CacheTBLAccessor.RTNCD_NORMAL.equals(invTMsg.getReturnCode())) {
                bizMsg.setMessageInfo(NFCM0786E);
                return;
            }
        }
    }

    /**
     * CMN_Submit Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NFCL3070Scrn00_CMN_Submit(NFCL3070CMsg bizMsg, NFCL3070SMsg glblMsg) {
        // Check exists CI Number & get CI Adjust Amount
        if (bizMsg.getMessageCode().endsWith(MESSAGE_KIND_ERROR) || bizMsg.getMessageCode().endsWith(MESSAGE_KIND_WARN)) {
            return;
        }
    }

}
