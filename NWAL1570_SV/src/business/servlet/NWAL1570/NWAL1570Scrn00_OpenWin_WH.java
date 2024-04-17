/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1570;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import business.servlet.NWAL1570.common.NWAL1570CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL1570Scrn00_OpenWin_WH
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/02   Fujitsu         K.Sato          Create          N/A
 *</pre>
 */
public class NWAL1570Scrn00_OpenWin_WH extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1570BMsg scrnMsg = (NWAL1570BMsg) bMsg;
        // 1. check EZDeveloper item attribute values.
        NWAL1570CommonLogic.addCheckItemBizLayerErr(scrnMsg);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1570BMsg scrnMsg = (NWAL1570BMsg) bMsg;
        scrnMsg.xxScrEventNm.setValue(ctx.getEventName());

        Object[] params = NWAL1570CommonLogic.getParamNMAL6050ForWH(scrnMsg);
        setArgForSubScreen(params);

    }
}
