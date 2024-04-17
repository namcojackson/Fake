/*
 * <pre>Copyright (c) 2018 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1660;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NWAL1660.NWAL1660CMsg;
//import business.servlet.NWAL1660.constant.NWAL1660Constant;

import business.servlet.NWAL1660.common.NWAL1660CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/09/03   Fujitsu         Y.Kanefusa      Create          S21_NA#9700
 *</pre>
 */
public class NWAL1660Scrn00_OpenWin_PriceAnalysisForSellPrice extends S21CommonHandler {

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

        NWAL1660BMsg scrnMsg = (NWAL1660BMsg) bMsg;
        scrnMsg.xxScrEventNm.setValue(ctx.getEventName());
        Object[] params = NWAL1660CommonLogic.getParamNWAL1900ForSellPrice(scrnMsg);
        setArgForSubScreen(params);

    }
}
