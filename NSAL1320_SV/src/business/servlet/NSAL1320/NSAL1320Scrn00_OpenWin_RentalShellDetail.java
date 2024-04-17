/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1320;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NSAL1320.common.NSAL1320CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/04/16   Fujitsu         A.Kosai         Create          QC#10374
 *</pre>
 */
public class NSAL1320Scrn00_OpenWin_RentalShellDetail extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL1320BMsg scrnMsg = (NSAL1320BMsg) bMsg;

        scrnMsg.xxScrEventNm.setValue(ctx.getEventName());
        scrnMsg.xxCellIdx.setValue(getButtonSelectNumber());

        Object[] params = NSAL1320CommonLogic.getParamNWAL2370(scrnMsg);
        setArgForSubScreen(params);
    }
}
