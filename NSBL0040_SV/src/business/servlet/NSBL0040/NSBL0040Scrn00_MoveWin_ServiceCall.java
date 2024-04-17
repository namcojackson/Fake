/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0040;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/06/01   SRA             Otsuji          Create          N/A
 *</pre>
 */
public class NSBL0040Scrn00_MoveWin_ServiceCall extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSBL0040BMsg scrnMsg = (NSBL0040BMsg) bMsg;

        // Move to NSBL0020
        Object[] param = new Object[2];

        int index = getButtonSelectNumber();

        ZYPEZDItemValueSetter.setValue(scrnMsg.glblCmpyCd_P1, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(scrnMsg.svcTaskNum_P1, scrnMsg.A.no(index).svcTaskNum_A1);

        param[0] = scrnMsg.glblCmpyCd_P1;
        param[1] = scrnMsg.svcTaskNum_P1;

        setArgForSubScreen(param);
    }
}
