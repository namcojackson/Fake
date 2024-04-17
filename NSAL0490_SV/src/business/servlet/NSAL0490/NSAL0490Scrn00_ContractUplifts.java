/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0490;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/09   Fujitsu         T.Yoshida       Create          N/A
 * 2016/01/27   Hitachi         T.Nishimura     Update          N/A
 *</pre>
 */
public class NSAL0490Scrn00_ContractUplifts extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // 2016/01/27 CSA T.Nishimura  Add Start
        NSAL0490BMsg scrnMsg = (NSAL0490BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.mdlId);
        scrnMsg.addCheckItem(scrnMsg.mdlNm);
        scrnMsg.putErrorScreen();
        // 2016/01/27 CSA T.Nishimura Add End
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        // 2016/01/27 CSA T.Nishimura  Add Start
        NSAL0490BMsg scrnMsg = (NSAL0490BMsg) bMsg;

        Object[] params = new Object[2];
        int i = 0;
        params[i++] = scrnMsg.mdlId;
        params[i++] = scrnMsg.mdlNm;
        setArgForSubScreen(params);
        // 2016/01/27 CSA T.Nishimura Add End
     }
}
