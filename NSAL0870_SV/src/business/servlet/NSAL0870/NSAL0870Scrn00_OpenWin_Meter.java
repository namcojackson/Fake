/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0870;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Meter Interface Status Inquiry
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/17   Hitachi         T.Mizuki        Create          N/A
 *</pre>
 */
public class NSAL0870Scrn00_OpenWin_Meter extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0870BMsg scrnMsg = (NSAL0870BMsg) bMsg;
        int index = getButtonSelectNumber();
        setArgForSubScreen(setConfigSearchPopUpInputParam(ctx, scrnMsg, index));

    }

    private Object[] setConfigSearchPopUpInputParam(EZDApplicationContext ctx, NSAL0870BMsg scrnMsg, int index) {
        ZYPTableUtil.clear(scrnMsg.O);
        scrnMsg.O.setValidCount(0);

        Object[] params = new Object[2];
        params[0] = scrnMsg.A.no(index).svcMachMstrPk_A;
        params[1] = scrnMsg.A.no(index).svcPhysMtrReadGrpSq_A;

        return params;

    }
}
