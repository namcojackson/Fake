/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL9900;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL9900.NSAL9900CMsg;
import business.servlet.NSAL9900.common.NSAL9900CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/05/28   Hitachi         T.Aoyagi        Create          N/A
 *</pre>
 */
public class NSAL9900Scrn00_CopyRow extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL9900BMsg scrnMsg = (NSAL9900BMsg) bMsg;
        NSAL9900CMsg bizMsg = NSAL9900CommonLogic.createCMsgForSearch();
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL9900BMsg scrnMsg = (NSAL9900BMsg) bMsg;
        NSAL9900CMsg bizMsg  = (NSAL9900CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NSAL9900CommonLogic.screenControlByItemValue(this, scrnMsg);
    }
}
