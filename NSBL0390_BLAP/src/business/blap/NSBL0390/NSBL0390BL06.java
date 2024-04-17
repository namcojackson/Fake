/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSBL0390;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static business.blap.NSBL0390.constant.NSBL0390Constant.*;
import parts.common.EZDCMsg;
import parts.common.EZDSMsg;

import business.blap.NSBL0390.common.NSBL0390CommonLogic;

import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Mods Entry
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/15   Hitachi         O.Okuma         Create          N/A
 *</pre>
 */
public class NSBL0390BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {
        NSBL0390CMsg cMsg = (NSBL0390CMsg) arg0;

        super.preDoProcess(cMsg, null);

        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("NSBL0390Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NSBL0390Scrn00_CMN_Submit(cMsg);
            }
        } finally {
            super.postDoProcess(cMsg, null);
        }
    }

    private void doProcess_NSBL0390Scrn00_CMN_Submit(NSBL0390CMsg cMsg) {

        if (hasValue(cMsg.svcModPk)) {
            NSBL0390CommonLogic.updateSvcMnf(cMsg);
        } else {
            if (NSBL0390CommonLogic.checktSvcModSqNum(cMsg)) {
                NSBL0390CommonLogic.insertSvcMnf(cMsg);
            } else {
                cMsg.setMessageInfo(NSBM0136E);
            }
        }
    }
}
