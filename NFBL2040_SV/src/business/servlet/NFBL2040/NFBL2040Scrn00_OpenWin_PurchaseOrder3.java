/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFBL2040;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NFBL2040.common.NFBL2040CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2020/02/17   Fujitsu         H.Ikeda         Create          QC#53413
 *</pre>
 */
public class NFBL2040Scrn00_OpenWin_PurchaseOrder3 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFBL2040BMsg scrnMsg = (NFBL2040BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.poNum_L);
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
        Object[] params = NFBL2040CommonLogic.getParamNWAL1130ForPurchaseOrder3(scrnMsg, this.getGlobalCompanyCode());
        setArgForSubScreen(params);
    }
}
