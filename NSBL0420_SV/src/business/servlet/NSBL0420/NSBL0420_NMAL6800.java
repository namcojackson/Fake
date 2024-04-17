/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NSBL0420;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NSBL0420.NSBL0420CMsg;
//import business.servlet.NSBL0420.constant.NSBL0420Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Mods Parts Entry
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/18   Hitachi         T.Tsuchida      Create          N/A
 * 2016/07/13   Hitachi         O.Okuma         Update          QC#11685
 *</pre>
 */
public class NSBL0420_NMAL6800 extends S21CommonHandler {

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
        NSBL0420BMsg scrnMsg = (NSBL0420BMsg) bMsg;
        getArgForSubScreen();

        if (!"CMN_Close".equals(getLastGuard())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.mdseCd_TF, scrnMsg.xxPopPrm_0);
        }
        scrnMsg.setFocusItem(scrnMsg.mdseCd_TF);
    }
}
