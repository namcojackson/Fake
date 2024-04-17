/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7130;

import static business.servlet.NMAL7130.constant.NMAL7130Constant.BIZ_ID;
import static business.servlet.NMAL7130.constant.NMAL7130Constant.ZZM9000E;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NMAL7130.NMAL7130CMsg;
import business.servlet.NMAL7130.common.NMAL7130CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL7130Scrn00_SrchPrcContr
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/30   Fujitsu         M.Nakamura      Create          N/A
 * 2016/02/22   Fujitsu         W.Honda         Update          CSA-QC#3636
 *</pre>
 */
public class NMAL7130Scrn00_SrchPrcContr extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7130BMsg scrnMsg = (NMAL7130BMsg) bMsg;

        // START 2016/02/22 W.Honda [S21 CSA QC#3636 ADD]
        NMAL7130CommonLogic.addCheckScreenItem(scrnMsg);

        scrnMsg.putErrorScreen();
        // END 2016/02/22 W.Honda [S21 CSA QC#3636 ADD]

        if (!ZYPCommonFunc.hasValue(scrnMsg.prcContrPk_H1)) {
            scrnMsg.prcContrPk_H1.setErrorInfo(1, ZZM9000E, new String[] {scrnMsg.prcContrPk_H1.getNameForMessage()});
        }

        scrnMsg.addCheckItem(scrnMsg.prcContrPk_H1);
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7130BMsg scrnMsg = (NMAL7130BMsg) bMsg;

        NMAL7130CMsg bizMsg = new NMAL7130CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7130BMsg scrnMsg = (NMAL7130BMsg) bMsg;
        NMAL7130CMsg bizMsg  = (NMAL7130CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        for (int i = 0; i < scrnMsg.D.getValidCount(); i++) {
            NMAL7130CommonLogic.setProtectRebateAdmin(scrnMsg, i);
        }
        NMAL7130CommonLogic.setTermCondProtect(this, scrnMsg);
        NMAL7130CommonLogic.setAttProtect(this, scrnMsg);
    }
}
