/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1600;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NWAL1600.common.NWAL1600CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL1600Scrn00_OnBlur_CalcTotalPercent
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/09   Fujitsu         C.Yokoi         Create          N/A
 *</pre>
 */
public class NWAL1600Scrn00_OnBlur_CalcTotalPercent extends S21CommonHandler {

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
        NWAL1600BMsg scrnMsg = (NWAL1600BMsg) bMsg;

        NWAL1600CommonLogic.addCheckSlsRepItems(scrnMsg);
        scrnMsg.xxDealSlsPct.setValue(NWAL1600CommonLogic.calcTotalPercent(scrnMsg));
    }
}
