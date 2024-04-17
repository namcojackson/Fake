/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1510;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1510.NWAL1510CMsg;
import business.servlet.NWAL1510.common.NWAL1510CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/01/18   CITS            T.Hakodate      Create          S21_NA#18460(Sol#087)
 *</pre>
 */
public class NWAL1510Scrn00_OnChange_InstallType extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // do nothing

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1510BMsg scrnMsg = (NWAL1510BMsg) bMsg;

        NWAL1510CMsg bizMsg = new NWAL1510CMsg();
        bizMsg.setBusinessID("NWAL1510");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1510BMsg scrnMsg = (NWAL1510BMsg) bMsg;
        NWAL1510CMsg bizMsg  = (NWAL1510CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NWAL1510CommonLogic.setTabProtect(this, scrnMsg);

    }
}
