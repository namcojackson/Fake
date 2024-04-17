/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1160;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL1160.NSAL1160CMsg;
import business.servlet.NSAL1160.common.NSAL1160CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/26/2016   Hitachi         J.Kim           Create          N/A
 *</pre>
 */
public class NSAL1160Scrn00_CMN_Reset extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL1160BMsg scrnMsg = (NSAL1160BMsg) bMsg;
        NSAL1160CMsg bizMsg = NSAL1160CommonLogic.setRequestData_SearchCommon();

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL1160BMsg scrnMsg = (NSAL1160BMsg) bMsg;
        NSAL1160CMsg bizMsg = (NSAL1160CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NSAL1160CommonLogic.screenControlProcess(this, scrnMsg);
    }
}
