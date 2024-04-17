/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1500;

import static business.servlet.NWAL1500.constant.NWAL1500Constant.TAB_LINE_CONFIG;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.TAB_RMA;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1500.NWAL1500CMsg;
import business.servlet.NWAL1500.common.NWAL1500CommonLogicForScrnFields;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/11   Fujitsu         T.Ishii         Create          N/A
 *</pre>
 */
public class NWAL1500_NSAL1240 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        if (!"CMN_Close".equals(getLastGuard())) {
            return null;
        }

        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;
        if (0 == scrnMsg.R.getValidCount()) {
            return null;
        }

        NWAL1500CMsg bizMsg = new NWAL1500CMsg();
        bizMsg.setBusinessID("NWAL1500");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;
        NWAL1500CMsg bizMsg = (NWAL1500CMsg) cMsg;

        if (bizMsg != null) {
            EZDMsg.copy(bizMsg, null, scrnMsg, null);
            int selectedIndex = this.getButtonSelectNumber();
            if (TAB_LINE_CONFIG.equals(scrnMsg.xxDplyTab.getValue())) {
                NWAL1500_ABMsg config = scrnMsg.A.no(selectedIndex);
//                scrnMsg.setFocusItem(config.xxChkBox_LC);
                scrnMsg.setFocusItem(config.svcConfigMstrPk_LC);
            } else if (TAB_RMA.equals(scrnMsg.xxDplyTab.getValue())) {
                NWAL1500_CBMsg config = scrnMsg.C.no(selectedIndex);
//                scrnMsg.setFocusItem(config.xxChkBox_RC);
                scrnMsg.setFocusItem(config.svcConfigMstrPk_RC);
            }
            NWAL1500CommonLogicForScrnFields.setProtect(this, scrnMsg);
        }
    }
}
