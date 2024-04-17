/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0380;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * History Level Report
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/03   Hitachi         T.Tsuchida      Create          N/A
 *</pre>
 */
public class NSBL0380_NMAL2530 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return;
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSBL0380BMsg scrnMsg = (NSBL0380BMsg) bMsg;
        getArgForSubScreen();

        if (!"CMN_Close".equals(getLastGuard())) {
            scrnMsg.setFocusItem(scrnMsg.orgCd_HT);
            ZYPEZDItemValueSetter.setValue(scrnMsg.orgCd_HT, scrnMsg.xxPopPrm_0);
            ZYPEZDItemValueSetter.setValue(scrnMsg.orgDescTxt_H, scrnMsg.xxPopPrm_1);
        }
    }
}
