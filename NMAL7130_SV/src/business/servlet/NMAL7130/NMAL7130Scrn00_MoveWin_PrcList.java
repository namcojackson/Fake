/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7130;

import static business.servlet.NMAL7130.constant.NMAL7130Constant.TAB_REGD_ACCT;
import static business.servlet.NMAL7130.constant.NMAL7130Constant.TAB_RELN_PRC_LIST;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import business.servlet.NMAL7130.common.NMAL7130CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL7130Scrn00_MoveWin_PrcList
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/30   Fujitsu         M.Nakamura      Create          N/A
 * 2016/02/22   Fujitsu         W.Honda         Update          CSA-QC#3636
 *</pre>
 */
public class NMAL7130Scrn00_MoveWin_PrcList extends S21CommonHandler {

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
        if (TAB_REGD_ACCT.equals(scrnMsg.xxDplyTab_H1.getValue())) {
            params[0] = scrnMsg.A.no(idx).prcCatgCd_A1;
        } else if (TAB_RELN_PRC_LIST.equals(scrnMsg.xxDplyTab_H1.getValue())) {
            params[0] = scrnMsg.B.no(idx).prcCatgCd_B1;
        }

        setArgForSubScreen(params);
    }
}
