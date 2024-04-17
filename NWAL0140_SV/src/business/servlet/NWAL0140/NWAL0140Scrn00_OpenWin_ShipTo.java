/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/6/23   CUSA            Fujitsu         Update          N/A
 *</pre>
 */
package business.servlet.NWAL0140;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NWAL0140.constant.NWAL0140Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class NWAL0140Scrn00_OpenWin_ShipTo extends S21CommonHandler implements NWAL0140Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // NWAL0140BMsg scrnMsg = (NWAL0140BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL0140BMsg scrnMsg = (NWAL0140BMsg) bMsg;

        scrnMsg.xxScrEventNm.setValue(EVENT_OPEN_WIN_SHIP_TO);

        scrnMsg.xxPopPrm.setValue("");
        scrnMsg.xxPopPrm_W1.setValue("");
        scrnMsg.xxPopPrm_W2.setValue("");
        scrnMsg.xxPopPrm_W3.setValue("");

        scrnMsg.xxPopPrm_W4.setValue(scrnMsg.shipToCustCd.getValue());
        scrnMsg.xxPopPrm_W5.setValue("");

        EZDBStringItem[] params = new EZDBStringItem[6];

        // 1. Bill To Code
        params[0] = scrnMsg.xxPopPrm;
        // 2. Bill To Name
        params[1] = scrnMsg.xxPopPrm_W1;
        // 3. Sell To Code
        params[2] = scrnMsg.xxPopPrm_W2;
        // 4. Sell To Name
        params[3] = scrnMsg.xxPopPrm_W3;
        // 5. Ship To Code
        params[4] = scrnMsg.xxPopPrm_W4;
        // 6. Ship To Name
        params[5] = scrnMsg.xxPopPrm_W5;

        setArgForSubScreen(params);
    }
}
