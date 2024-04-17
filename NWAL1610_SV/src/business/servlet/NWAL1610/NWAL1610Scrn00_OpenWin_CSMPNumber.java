/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1610;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NWAL1610.common.NWAL1610CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/08/30   Fujitsu         S.Takami        Create          S21_NA#9806
 *</pre>
 */
public class NWAL1610Scrn00_OpenWin_CSMPNumber extends S21CommonHandler {

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

        NWAL1610BMsg scrnMsg = (NWAL1610BMsg) bMsg;
        scrnMsg.xxScrEventNm.setValue(ctx.getEventName());

        Object[] params = NWAL1610CommonLogic.getParamNWAL1130ForCSMPNumber(scrnMsg);
        setArgForSubScreen(params);
    }
}
