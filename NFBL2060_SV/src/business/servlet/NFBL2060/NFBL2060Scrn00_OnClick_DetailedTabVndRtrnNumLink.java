/*
 * <pre>Copyright (c) 2018 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFBL2060;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/05/23   CITS            K.Ogino         Create          QC#25902
 *</pre>
 */
public class NFBL2060Scrn00_OnClick_DetailedTabVndRtrnNumLink extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NFBL2060BMsg scrnMsg = (NFBL2060BMsg) bMsg;

        Object[] params = new Object[1];
        params[0] = scrnMsg.D.no(getButtonSelectNumber()).vndRtrnNum_D1;

        setArgForSubScreen(params);
    }
}
