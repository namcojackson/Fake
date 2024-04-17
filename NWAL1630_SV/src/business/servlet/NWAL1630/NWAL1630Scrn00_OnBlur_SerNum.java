/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1630;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1630.NWAL1630CMsg;
import business.servlet.NWAL1630.common.NWAL1630CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2019/01/24   Fujitsu         K.Ishizuka      Create          S21_NA#29446
 *</pre>
 */
public class NWAL1630Scrn00_OnBlur_SerNum extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NWAL1630BMsg scrnMsg = (NWAL1630BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.serNum);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NWAL1630BMsg scrnMsg = (NWAL1630BMsg) bMsg;
        NWAL1630CMsg bizMsg = new NWAL1630CMsg();
        bizMsg.setBusinessID("NWAL1630");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NWAL1630BMsg scrnMsg = (NWAL1630BMsg) bMsg;
        NWAL1630CMsg bizMsg = (NWAL1630CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NWAL1630CommonLogic.addChkSerNumContrNum(scrnMsg);
    }
}
