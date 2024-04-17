/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1350;

import static business.servlet.NSAL1350.constant.NSAL1350Constant.BIZ_ID;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NSAL1350.NSAL1350CMsg;
import business.servlet.NSAL1350.common.NSAL1350CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NSAL1350Scrn00_CMN_Close
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/04/26   Hitachi         T.Mizuki        Create          N/A
 *</pre>
 */
public class NSAL1350Scrn00_CMN_Close extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL1350BMsg scrnMsg = (NSAL1350BMsg) bMsg;

        NSAL1350CMsg bizMsg = new NSAL1350CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSAL1350BMsg scrnMsg = (NSAL1350BMsg) bMsg;
        NSAL1350CMsg bizMsg = (NSAL1350CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        Serializable arg = getArgForSubScreen();

        if (arg instanceof Object[]) {
            NSAL1350CommonLogic.setOutputPrm((Object[]) arg, scrnMsg);
        }
    }
}
