/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0430;

import static business.servlet.NSBL0430.constant.NSBL0430Constant.LENGTH_7;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/28    Hitachi         O.Okuma         Create          N/A
 *</pre>
 */
public class NSBL0430Scrn00_OpenWin_ItemMstrPop extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSBL0430BMsg scrnMsg = (NSBL0430BMsg) bMsg;

        scrnMsg.xxPopPrm_0.clear();
        scrnMsg.xxPopPrm_1.clear();
        scrnMsg.xxPopPrm_2.clear();
        scrnMsg.xxPopPrm_3.clear();
        scrnMsg.xxPopPrm_4.clear();
        scrnMsg.xxPopPrm_5.clear();
        scrnMsg.xxPopPrm_6.clear();

        Object[] params = new Object[LENGTH_7];

        int i = 0;
        params[i++] = scrnMsg.xxPopPrm_0;
        params[i++] = scrnMsg.xxPopPrm_1;
        params[i++] = scrnMsg.xxPopPrm_2;
        params[i++] = scrnMsg.xxPopPrm_3;
        params[i++] = scrnMsg.xxPopPrm_4;
        params[i++] = scrnMsg.xxPopPrm_5;
        params[i++] = scrnMsg.xxPopPrm_6;
        setArgForSubScreen(params);
    }
}
