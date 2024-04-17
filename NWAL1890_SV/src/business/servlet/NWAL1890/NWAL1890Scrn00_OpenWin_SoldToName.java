/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1890;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import business.servlet.NWAL1890.common.NWAL1890CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/08/08   Fujitsu         T.Aoi           Create          N/A
 *</pre>
 */
public class NWAL1890Scrn00_OpenWin_SoldToName extends S21CommonHandler {

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

        NWAL1890BMsg scrnMsg = (NWAL1890BMsg) bMsg;

        scrnMsg.xxScrEventNm.setValue(ctx.getEventName());

        String glblCmpyCd = getGlobalCompanyCode();
        String cpoOrdNum = scrnMsg.cpoOrdNum.getValue();

        Object[] params = NWAL1890CommonLogic.getParamForSoldToCustName(scrnMsg, glblCmpyCd, cpoOrdNum);
        setArgForSubScreen(params);
    }
}
