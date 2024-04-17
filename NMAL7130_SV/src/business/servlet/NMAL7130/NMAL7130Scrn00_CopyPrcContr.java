/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7130;

import static business.servlet.NMAL7130.constant.NMAL7130Constant.BIZ_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL7130.NMAL7130CMsg;
import business.servlet.NMAL7130.common.NMAL7130CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL7130Scrn00_CopyPrcContr
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/30   Fujitsu         M.Nakamura      Create          N/A
 * 2016/02/22   Fujitsu         W.Honda         Update          CSA-QC#3636
 *</pre>
 */
public class NMAL7130Scrn00_CopyPrcContr extends S21CommonHandler {

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

        NMAL7130CommonLogic.setTermCondProtect(this, scrnMsg);
    }
}
