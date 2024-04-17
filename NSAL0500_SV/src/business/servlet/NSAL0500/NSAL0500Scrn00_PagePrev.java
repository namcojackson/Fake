/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0500;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NSAL0500.NSAL0500CMsg;
import business.servlet.NSAL0500.common.NSAL0500CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Sub Contract Maintenance
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/04/07   Hitachi         T.Aoyagi        Create          N/A
 * 2016/01/06   Hitachi         T.Tsuchida      Update          N/A
 *</pre>
 */
public class NSAL0500Scrn00_PagePrev extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return;
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0500BMsg scrnMsg = (NSAL0500BMsg) bMsg;
        NSAL0500CMsg bizMsg = NSAL0500CommonLogic.createCMsgForSearch();

        String tableId = NSAL0500CommonLogic.getTableId(ctx);
        scrnMsg.xxModeInd.setValue(tableId);

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0500BMsg scrnMsg = (NSAL0500BMsg) bMsg;
        NSAL0500CMsg bizMsg = (NSAL0500CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
    }
}
