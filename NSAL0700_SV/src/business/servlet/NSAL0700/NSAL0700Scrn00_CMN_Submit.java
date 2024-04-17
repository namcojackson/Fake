/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0700;

import static business.servlet.NSAL0700.common.NSAL0700CommonLogic.*;
import static business.servlet.NSAL0700.constant.NSAL0700Constant.*;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0700.NSAL0700CMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/04   Hitachi         K.Kasai         Create          N/A
 *</pre>
 */
public class NSAL0700Scrn00_CMN_Submit extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0700BMsg scrnMsg = (NSAL0700BMsg) bMsg;
        addCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0700BMsg scrnMsg = (NSAL0700BMsg) bMsg;

        NSAL0700CMsg bizMsg = new NSAL0700CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0700BMsg scrnMsg = (NSAL0700BMsg) bMsg;
        NSAL0700CMsg bizMsg  = (NSAL0700CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        addCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();
        if ("E".equals(bizMsg.getMessageKind())) {
            return;
        }

        this.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 0, null);
    }
}
