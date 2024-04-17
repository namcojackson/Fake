/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0620;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2019/08/30   Hitachi         T.Aoyagi        Update          QC#53005
 *</pre>
 */
public class NSAL0620Scrn00_OpenWin_SrchConfig extends S21CommonHandler {

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

        NSAL0620BMsg scrnMsg = (NSAL0620BMsg) bMsg;
        scrnMsg.xxScrEventNm.setValue("OpenWin_SrchConfig");
        scrnMsg.svcConfigMstrPk_P.clear();
        Object[] params = new Object[31];
        params[1] = scrnMsg.svcConfigMstrPk_H;
        params[29] = scrnMsg.svcConfigMstrPk_P;
        setArgForSubScreen(params);
    }
}
