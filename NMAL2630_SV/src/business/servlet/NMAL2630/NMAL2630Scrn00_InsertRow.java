/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2630;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NMAL2630.NMAL2630CMsg;
import business.servlet.NMAL2630.constant.NMAL2630Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/07/20   Fujitsu         N.Sugiura       Create          N/A
 *</pre>
 */
public class NMAL2630Scrn00_InsertRow extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2630BMsg scrnMsg = (NMAL2630BMsg) bMsg;
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2630BMsg scrnMsg = (NMAL2630BMsg) bMsg;

        NMAL2630CMsg bizMsg = new NMAL2630CMsg();
        bizMsg.setBusinessID("NMAL2630");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2630BMsg scrnMsg = (NMAL2630BMsg) bMsg;
        NMAL2630CMsg bizMsg = (NMAL2630CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (scrnMsg.B.getValidCount() + 1 > scrnMsg.B.length()) {
            setButtonEnabled(NMAL2630Constant.BTN_INSERT, false);
        }
        if (scrnMsg.B.getValidCount() > 0) {
            setButtonEnabled(NMAL2630Constant.BTN_DELETE, true);
        }

    }
}
