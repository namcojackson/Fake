/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7130;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import business.servlet.NMAL7130.common.NMAL7130CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL7130Scrn00_OpenWin_AcctSrch
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/30   Fujitsu         M.Nakamura      Create          N/A
 * 2016/02/22   Fujitsu         W.Honda         Update          CSA-QC#3636
 *</pre>
 */
public class NMAL7130Scrn00_OpenWin_AcctSrch extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // START 2016/02/22 W.Honda [S21 CSA QC#3636 ADD]
        NMAL7130BMsg scrnMsg = (NMAL7130BMsg) bMsg;

        NMAL7130CommonLogic.addCheckScreenItem(scrnMsg);

        scrnMsg.putErrorScreen();
        // END 2016/02/22 W.Honda [S21 CSA QC#3636 ADD]
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7130BMsg scrnMsg = (NMAL7130BMsg) bMsg;

        // Clear Params
        scrnMsg.xxPopPrm_P1.clear();

        int idx = getButtonSelectNumber();

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxCellIdx, new BigDecimal(idx));

        // Set Params
        Object[] params = new Object[18];
        params[0] = scrnMsg.D.no(idx).dsAcctNum_D1;
        params[1] = scrnMsg.D.no(idx).dsAcctNm_D1;
        params[2] = scrnMsg.xxPopPrm_P1;
        params[3] = scrnMsg.xxPopPrm_P1;
        params[4] = scrnMsg.xxPopPrm_P1;
        params[5] = scrnMsg.xxPopPrm_P1;
        params[6] = scrnMsg.xxPopPrm_P1;
        params[7] = scrnMsg.xxPopPrm_P1;
        params[8] = scrnMsg.xxPopPrm_P1;
        params[9] = scrnMsg.xxPopPrm_P1;
        params[10] = scrnMsg.xxPopPrm_P1;
        params[11] = scrnMsg.xxPopPrm_P1;
        params[12] = scrnMsg.xxPopPrm_P1;
        params[13] = scrnMsg.xxPopPrm_P1;
        params[14] = scrnMsg.xxPopPrm_P1;
        params[15] = scrnMsg.xxPopPrm_P1;
        params[16] = scrnMsg.xxPopPrm_P1;
        params[17] = scrnMsg.xxPopPrm_P1;

        setArgForSubScreen(params);
    }
}
