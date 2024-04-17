/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6160;

import static business.servlet.NMAL6160.constant.NMAL6160Constant.PRM_NMAL2630;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Multi Candinate Popup
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/15   Fujitsu         M.Ohno          Create          N/A
 *</pre>
 */
public class NMAL6160Scrn00_OpenWin_Trty extends S21CommonHandler {

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

        NMAL6160BMsg scrnMsg = (NMAL6160BMsg) bMsg;
        scrnMsg.P.clear();
        Object[] params = new Object[PRM_NMAL2630];
        int i = 0;
        params[i] = scrnMsg.P.no(i).xxPopPrm_P;
        i++;
        params[i] = scrnMsg.P.no(i).xxPopPrm_P;
        i++;
        params[i] = scrnMsg.P.no(i).xxPopPrm_P;
        i++;
        params[i] = scrnMsg.P.no(i).xxPopPrm_P;
        i++;
        params[i] = scrnMsg.P.no(i).xxPopPrm_P;
        i++;
        params[i] = scrnMsg.P.no(i).xxPopPrm_P;
        i++;
        params[i] = scrnMsg.P.no(i).xxPopPrm_P;
        i++;
        params[i] = scrnMsg.P.no(i).xxPopPrm_P;
        i++;
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i).xxPopPrm_P, scrnMsg.B.no(getButtonSelectNumber()).orgCd_B);
        params[i] = scrnMsg.P.no(i).xxPopPrm_P;

        setArgForSubScreen(params);
    }
}
