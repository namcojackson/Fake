/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSBL0390;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.*;

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
public class NSBL0390BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {
        NSBL0390CMsg cMsg = (NSBL0390CMsg) arg0;

        super.preDoProcess(cMsg, null);

        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("NSBL0390_INIT".equals(screenAplID)) {
                doProcess_NSBL0390_INIT(cMsg);
            } else if ("NSBL0390Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NSBL0390Scrn00_CMN_Clear(cMsg);
            } else if ("NSBL0390Scrn00_Create".equals(screenAplID)) {
                doProcess_NSBL0390Scrn00_Create(cMsg);
            }
        } finally {
            super.postDoProcess(cMsg, null);
        }
    }

    private void doProcess_NSBL0390_INIT(NSBL0390CMsg cMsg) {

        NSBL0390CommonLogic.clearMsg(cMsg);
        NSBL0390CommonLogic.createPullDown(cMsg);
        NSBL0390CommonLogic.setInitParams(cMsg);
        NSBL0390CommonLogic.initDate(cMsg);
        setValue(cMsg.glblCmpyCd, getGlobalCompanyCode());
    }

    private void doProcess_NSBL0390Scrn00_CMN_Clear(NSBL0390CMsg cMsg) {
        cMsg.clear();
        NSBL0390CommonLogic.clearMsg(cMsg);
        NSBL0390CommonLogic.createPullDown(cMsg);
        NSBL0390CommonLogic.setInitParams(cMsg);
        NSBL0390CommonLogic.initDate(cMsg);
        setValue(cMsg.glblCmpyCd, getGlobalCompanyCode());
    }

    private void doProcess_NSBL0390Scrn00_Create(NSBL0390CMsg cMsg) {

        NSBL0390CommonLogic.setSvcModSqNum(cMsg);
        NSBL0390CommonLogic.setSvcModPlnId(cMsg);
    }
}
