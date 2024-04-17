/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0920;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NSAL0920.NSAL0920CMsg;
//import business.servlet.NSAL0920.constant.NSAL0920Constant;

import business.servlet.NSAL0920.common.NSAL0920CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Contract Billing Search
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/03   Hitachi         K.Kasai         Create          N/A
 * 2016/04/14   Hitachi         K.Kishimoto     Update          QC#6657
 *</pre>
 */
public class NSAL0920Scrn00_MoveWin_Mach extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0920BMsg scrnMsg = (NSAL0920BMsg) bMsg;
        NSAL0920CommonLogic.clearPopupParameter(scrnMsg);
        Object[] params = new Object[8];
        int selectRow = getButtonSelectNumber();
        params[0] = scrnMsg.xxPopPrm_00;
        params[1] = scrnMsg.xxPopPrm_01;
        //Mod Start 04/14/2016 <QC#6657>
        params[2] = scrnMsg.xxPopPrm_02;
        params[3] = scrnMsg.A.no(selectRow).dsContrNum_A;
        params[4] = scrnMsg.A.no(selectRow).mtrBllgFromDt_A;
        params[5] = scrnMsg.A.no(selectRow).mtrBllgThruDt_A;
        //Mod End   04/14/2016 <QC#6657>
        params[6] = scrnMsg.xxPopPrm_06;
        params[7] = scrnMsg.xxPopPrm_07;

        setArgForSubScreen(params);
    }
}
