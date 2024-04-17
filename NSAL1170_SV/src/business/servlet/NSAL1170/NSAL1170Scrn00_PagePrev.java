/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1170;

import static business.servlet.NSAL1170.constant.NSAL1170Constant.*;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL1170.NSAL1170CMsg;
import business.servlet.NSAL1170.common.NSAL1170CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/27   Hitachi         T.Nishimura     Create          N/A
 *</pre>
 */
public class NSAL1170Scrn00_PagePrev extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL1170BMsg scrnMsg = (NSAL1170BMsg) bMsg;

        NSAL1170CMsg bizMsg = new NSAL1170CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNCTION_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL1170BMsg scrnMsg = (NSAL1170BMsg) bMsg;
        NSAL1170CMsg bizMsg = (NSAL1170CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NSAL1170CommonLogic.initialControlScreen(this, scrnMsg);
   }
}
