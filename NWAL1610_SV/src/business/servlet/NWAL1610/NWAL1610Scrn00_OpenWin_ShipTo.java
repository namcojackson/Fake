/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1610;

import static business.servlet.NWAL1610.constant.NWAL1610Constant.OPENWIN_SHIP_TO;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NWAL1610.common.NWAL1610CommonLogic;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL1610Scrn00_OpenWin_ShipTo
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/07   Fujitsu         Yokoi           Create          N/A
 * 2016/05/16   Fujitsu         M.Yamada        Update          S21 CSA QC#6148
 * 2017/11/16   Fujitsu         H.Sugawara      Update          QC#17322(Sol#174)
 *</pre>
 */
public class NWAL1610Scrn00_OpenWin_ShipTo extends S21CommonHandler {

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
        NWAL1610BMsg scrnMsg = (NWAL1610BMsg) bMsg;
        ZYPTableUtil.clear(scrnMsg.P);
        scrnMsg.xxScrEventNm.setValue(OPENWIN_SHIP_TO);
        // Del Start 2017/11/16 QC#17322(Sol#174)
        //ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).xxPopPrm_P, DS_ACCT_RELN_TP.SHIP_TO);
        // Del End 2017/11/16 QC#17322(Sol#174)

        Object[] params = NWAL1610CommonLogic.getOpenWinShipToParam(scrnMsg);
        setArgForSubScreen(params);
    }
}
