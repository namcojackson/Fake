/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1020;

import static business.servlet.NSAL1020.constant.NSAL1020Constant.*;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Supply Order Serial# Search
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/19   Hitachi         T.Tsuchida      Create          N/A
 *</pre>
 */
public class NSAL1020Scrn00_OpenWin_SerNumSrch extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL1020BMsg scrnMsg = (NSAL1020BMsg) bMsg;
        setArgForSubScreen(setConfigSearchPopUpInputParam(ctx, scrnMsg));
    }

    private Object[] setConfigSearchPopUpInputParam(EZDApplicationContext ctx, NSAL1020BMsg scrnMsg) {
        ZYPTableUtil.clear(scrnMsg.O);
        scrnMsg.O.setValidCount(0);
        Object[] params = new Object[PARAM_LENGTH_NSAL1240];
        params[PARAM_INDEX_2] = scrnMsg.serNum_H;
        params[PARAM_INDEX_30] = scrnMsg.O;
        return params;
    }
}
