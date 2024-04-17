/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7250;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/16   Fujitsu         W.Honda         Create          N/A
 *</pre>
 */
public class NMAL7250Scrn00_OpenWin_AccountNum extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // There is no process.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7250BMsg scrnMsg = (NMAL7250BMsg) bMsg;

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrEventNm, ctx.getEventName());

        // Clear Params
        ZYPTableUtil.clear(scrnMsg.P);

        // Set Params
        Object[] params = new Object[15];
        params[0]  = scrnMsg.dsAcctNum;
        params[1]  = scrnMsg.dsAcctNm;
        params[2]  = scrnMsg.P.no(5).xxPopPrm;
        params[3]  = scrnMsg.P.no(5).xxPopPrm;
        params[4]  = scrnMsg.P.no(5).xxPopPrm;
        params[5]  = scrnMsg.P.no(5).xxPopPrm;
        params[6]  = scrnMsg.P.no(5).xxPopPrm;
        params[7]  = scrnMsg.P.no(5).xxPopPrm;
        params[8]  = scrnMsg.P.no(5).xxPopPrm;
        params[9]  = scrnMsg.P.no(5).xxPopPrm;
        params[10] = scrnMsg.P.no(5).xxPopPrm;
        params[11] = scrnMsg.P.no(5).xxPopPrm;
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(4).xxPopPrm, "01");
        params[12] = scrnMsg.P.no(4).xxPopPrm;
        params[13] = scrnMsg.P.no(0).xxPopPrm;
        params[14] = scrnMsg.P.no(1).xxPopPrm;

        setArgForSubScreen(params);
    }
}
