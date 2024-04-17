/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0790;

import static business.servlet.NSAL0790.constant.NSAL0790Constant.*;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NSAL0790.NSAL0790CMsg;
import business.servlet.NSAL0790.common.NSAL0790CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
public class NSAL0790Scrn00_ResubmitRollup extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0790BMsg scrnMsg = (NSAL0790BMsg) bMsg;
        NSAL0790CMsg bizMsg = new NSAL0790CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_UPDATE);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0790BMsg scrnMsg = (NSAL0790BMsg) bMsg;
        NSAL0790CMsg bizMsg = (NSAL0790CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NSAL0790CommonLogic.initialControlScreen(this, scrnMsg);
    }
}