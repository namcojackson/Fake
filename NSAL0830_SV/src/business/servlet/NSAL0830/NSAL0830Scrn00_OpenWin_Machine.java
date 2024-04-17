/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0830;

import static business.servlet.NSAL0830.constant.NSAL0830Constant.PARAM_INDEX_30;
import static business.servlet.NSAL0830.constant.NSAL0830Constant.PARAM_LENGTH_NSAL1240;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/21   Hitachi         Y.Takeno       Create          N/A
 *</pre>
 */
public class NSAL0830Scrn00_OpenWin_Machine extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0830BMsg scrnMsg = (NSAL0830BMsg) bMsg;
        setArgForSubScreen(setConfigSearchPopUpInputParam(ctx, scrnMsg));
    }

    private Object[] setConfigSearchPopUpInputParam(EZDApplicationContext ctx, NSAL0830BMsg scrnMsg) {
        ZYPTableUtil.clear(scrnMsg.O);
        scrnMsg.O.setValidCount(0);
        Object[] params = new Object[PARAM_LENGTH_NSAL1240];
        params[PARAM_INDEX_30] = scrnMsg.O;
        return params;
    }
}
