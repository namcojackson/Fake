/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0480;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/10   Fujitsu         M.Yamada        Create          N/A
 *</pre>
 */
public class NSAL0480Scrn00_ModelNameLink extends S21CommonHandler {

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

        return;

        //TODO
        //        NSAL0480BMsg scrnMsg = (NSAL0480BMsg) bMsg;
        //
        //        scrnMsg.xxScrEventNm.setValue(ctx.getEventName());
        //
        //        // TODO set parameter
        //        ZYPEZDItemValueSetter.setValue(scrnMsg.t_MdlNm_P, scrnMsg.A.no(getButtonSelectNumber()).t_MdlNm_A);
        //
        //        Object[] params = new Object[NSAL0XXX_PRM_LENGTH];
        //        int i = 0;
        //        params[i] = scrnMsg.t_MdlNm_P;
        //
        //        setArgForSubScreen(params);
    }
}
