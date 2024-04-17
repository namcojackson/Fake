/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1500;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/04   Fujitsu         T.Yoshida       Create          N/A
 *</pre>
 */
public class NWAL1500Scrn00_OpenWin_Model extends S21CommonHandler {

    // TODO
    // UnUsed T.Yoshida

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        // NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;
        // scrnMsg.xxScrEventNm.setValue(ctx.getEventName());
        // scrnMsg.xxCellIdx.setValue(getButtonSelectNumber());
        //
        // Object[] params = NWAL1500CommonLogic.getParamNMAL6050ForMdl(scrnMsg);
        // setArgForSubScreen(params);
    }
}
