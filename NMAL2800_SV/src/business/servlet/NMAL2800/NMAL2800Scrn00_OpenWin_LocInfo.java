/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2800;

import static business.servlet.NMAL2800.constant.NMAL2800Constant.POPUP_LOC;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL2800Scrn00_OpenWin_LocInfo
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/16   Fujitsu         C.Tanaka        Create          N/A
 *</pre>
 */
public class NMAL2800Scrn00_OpenWin_LocInfo extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2800BMsg scrnMsg = (NMAL2800BMsg) bMsg;

        scrnMsg.P.clear();

        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(2).xxPopPrm, scrnMsg.A.no(getButtonSelectNumber()).aftLocNum_A1);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(10).xxPopPrm, POPUP_LOC);

        Object[] params = new Object[3];
        params[0] = scrnMsg.P.no(0).xxPopPrm;
        params[1] = scrnMsg.P.no(1).xxPopPrm;
        params[2] = scrnMsg.P.no(2).xxPopPrm;

        setArgForSubScreen(params);
    }
}
