/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1140;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL1140.NSAL1140CMsg;
import static business.servlet.NSAL1140.constant.NSAL1140Constant.*;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Supply Watch.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/12   Hitachi         T.Nishimura        Create          N/A
 *</pre>
 */
public class NSAL1140Scrn00_OnChangeSavedSearchOption extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return;
    }
    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL1140BMsg scrnMsg = (NSAL1140BMsg) bMsg;

        NSAL1140CMsg bizMsg = new NSAL1140CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNCTION_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL1140BMsg scrnMsg = (NSAL1140BMsg) bMsg;
        NSAL1140CMsg bizMsg  = (NSAL1140CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        scrnMsg.addCheckItem(scrnMsg.srchOptPk_1V);
        scrnMsg.putErrorScreen();
    }
}
