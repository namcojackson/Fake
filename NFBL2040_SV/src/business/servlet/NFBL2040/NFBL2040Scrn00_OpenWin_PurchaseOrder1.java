/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFBL2040;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NFBL2040.common.NFBL2040CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import static business.servlet.NFBL2040.constant.NFBL2040Constant.OPENWIN_PURCHASE_ORDER_1;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/25   Hitachi         Y.Tsuchimoto    Create          QC#11999
 * 2018/02/02   Hitachi         Y.Takeno        Update          QC#21703
 * 2018/05/25   CITS            K.Ogino         Update          QC#25902,QC#25190,QC#25141
 * 2020/02/17   Fujitsu         H.Ikeda         Update          QC#53413
 *</pre>
 */
public class NFBL2040Scrn00_OpenWin_PurchaseOrder1 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFBL2040BMsg scrnMsg = (NFBL2040BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.poNum);
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
        // START QC#25902,QC#25190,QC#25141
        // START 2020/02/17 [QC#53413, MOD]
        //Object[] params = NFBL2040CommonLogic.getParamNWAL1130ForPurchaseOrder(scrnMsg, this.getGlobalCompanyCode(), true);
        Object[] params = NFBL2040CommonLogic.getParamNWAL1130ForPurchaseOrder(scrnMsg, this.getGlobalCompanyCode(), OPENWIN_PURCHASE_ORDER_1);
        // END   2020/02/17 [QC#53413, MOD]
        // END QC#25902,QC#25190,QC#25141
        setArgForSubScreen(params);
    }
}
