/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0530;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NSAL0530.NSAL0530CMsg;
import business.servlet.NSAL0530.common.NSAL0530CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Solution Search
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/05/12   Hitachi         T.Tomita        Create          N/A
 *</pre>
 */
public class NSAL0530Scrn00_PageNext extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return;
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0530BMsg scrnMsg = (NSAL0530BMsg) bMsg;

        NSAL0530CMsg bizMsg = NSAL0530CommonLogic.createCMsgForSearch();
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0530BMsg scrnMsg = (NSAL0530BMsg) bMsg;
        NSAL0530CMsg bizMsg  = (NSAL0530CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
    }
}
