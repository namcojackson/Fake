/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1780;

import static business.servlet.NWAL1780.constant.NWAL1780Constant.IDX_0;
import static business.servlet.NWAL1780.constant.NWAL1780Constant.IDX_1;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * Supply Quote Search Move Window QuoteEntry
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/02   Fujitsu         T.Murai         Create          N/A
 *</pre>
 */
public class NWAL1780Scrn00_MoveWin_Quote extends S21CommonHandler {

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

        NWAL1780BMsg scrnMsg = (NWAL1780BMsg) bMsg;

        // set param
        Object[] params = new Object[IDX_1];
        params[IDX_0] = scrnMsg.A.no(getButtonSelectNumber()).splyQuoteNum_A;
        setArgForSubScreen(params);
    }
}
