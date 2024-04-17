/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6720;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.fujitsu.uji.http.HttpDispatchContext;
import com.fujitsu.uji.util.Parameters;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
public class NMAL6720Scrn00_ShowRelatedAccount extends S21CommonHandler {

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

        Parameters parameters = ((HttpDispatchContext) ctx.getDispatchContext()).getParameters();
        int selectedIndex = Integer.parseInt((String) parameters.get("selectNumber")[0]);
        if (0 <= selectedIndex) {
            NMAL6720BMsg scrnMsg = (NMAL6720BMsg) bMsg;

            Object[] params = new Object[2];
            params[0] = scrnMsg.dsAcctNum_H1;
            params[1] = scrnMsg.locNum_H1;

            setArgForSubScreen(params);
        }

    }
}
