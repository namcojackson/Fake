/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7130;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import business.servlet.NMAL7130.common.NMAL7130CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL7130Scrn00_MoveWin_CsmpSrchMassUpd
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/30   Fujitsu         M.Nakamura      Create          N/A
 * 2016/02/22   Fujitsu         W.Honda         Update          CSA-QC#3636
 *</pre>
 */
public class NMAL7130Scrn00_MoveWin_CsmpSrchMassUpd extends S21CommonHandler {

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
        int idx = getButtonSelectNumber();
        Object[] params = new Object[1];

        if (idx >= 0) {
            if (ZYPCommonFunc.hasValue(scrnMsg.prcContrNum_H1)) {
                params[0] = scrnMsg.prcContrNum_H1;
                setArgForSubScreen(params);
            }
        }

    }
}
