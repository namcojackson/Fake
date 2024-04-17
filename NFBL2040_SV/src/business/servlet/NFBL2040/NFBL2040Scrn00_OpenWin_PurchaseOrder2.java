/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFBL2040;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import business.servlet.NFBL2040.common.NFBL2040CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import static business.servlet.NFBL2040.constant.NFBL2040Constant.OPENWIN_PURCHASE_ORDER_2;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/25   Hitachi         Y.Tsuchimoto    Create          QC#11999
 * 2016/10/26   Hitachi         Y.Tsuchimoto    Update          QC#15493
 * 2020/02/17   Fujitsu         H.Ikeda         Update          QC#53413
 *</pre>
 */
public class NFBL2040Scrn00_OpenWin_PurchaseOrder2 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFBL2040BMsg scrnMsg = (NFBL2040BMsg) bMsg;
        // START 2016/10/26 Y.Tsuchimoto [QC#15493,MOD]
        scrnMsg.addCheckItem(scrnMsg.poNum_HT);
        // END   2016/10/26 Y.Tsuchimoto [QC#15493,MOD]
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFBL2040BMsg scrnMsg = (NFBL2040BMsg) bMsg;

        scrnMsg.xxScrEventNm.setValue(ctx.getEventName());
        scrnMsg.xxCellIdx.setValue(getButtonSelectNumber());
        // START 2020/02/17 [QC#53413, MOD]
        //Object[] params = NFBL2040CommonLogic.getParamNWAL1130ForPurchaseOrder(scrnMsg, this.getGlobalCompanyCode(), false);
        Object[] params = NFBL2040CommonLogic.getParamNWAL1130ForPurchaseOrder(scrnMsg, this.getGlobalCompanyCode(), OPENWIN_PURCHASE_ORDER_2);
        // END   2020/02/17 [QC#53413, MOD]
        setArgForSubScreen(params);
    }
}
