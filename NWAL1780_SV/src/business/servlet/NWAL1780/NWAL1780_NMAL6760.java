/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1780;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/04   Fujitsu         T.Murai         Create          N/A
 *</pre>
 */
public class NWAL1780_NMAL6760 extends S21CommonHandler {

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

        if ("CMN_Close".equals(getLastGuard())) {
            return;
        }

        NWAL1780BMsg scrnMsg = (NWAL1780BMsg) bMsg;

        // Set Focus
        String scrEventNm = scrnMsg.xxScrEventNm.getValue();
        if ("OpenWin_SoldTo".equals(scrEventNm)) {
            scrnMsg.setFocusItem(scrnMsg.shipToCustAcctCd);
        } else if ("OpenWin_ShipTo".equals(scrEventNm)) {
            scrnMsg.setFocusItem(scrnMsg.mdseCd);
        }
    }
}
