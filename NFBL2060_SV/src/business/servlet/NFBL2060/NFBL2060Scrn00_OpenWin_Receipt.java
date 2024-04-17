/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFBL2060;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NFBL2060.common.NFBL2060CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/27   Hitachi         T.Tsuchida      Create          QC#12002
 * 2016/10/24   Hitachi         Y.Tsuchimoto    Update          QC#15493
 * 2018/05/28   CITS            K.Ogino         Update          QC#25902
 *</pre>
 */
public class NFBL2060Scrn00_OpenWin_Receipt extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFBL2060BMsg scrnMsg = (NFBL2060BMsg) bMsg;
        // START 2016/10/24 Y.Tsuchimoto [QC#15493,MOD]
        scrnMsg.addCheckItem(scrnMsg.poNum);
        // END   2016/10/24 Y.Tsuchimoto [QC#15493,MOD]
        scrnMsg.addCheckItem(scrnMsg.delyOrdNum);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFBL2060BMsg scrnMsg = (NFBL2060BMsg) bMsg;

        scrnMsg.xxScrEventNm.setValue(ctx.getEventName());
        scrnMsg.xxCellIdx.setValue(getButtonSelectNumber());
        // QC#25902
        Object[] params = NFBL2060CommonLogic.getParamNWAL1130ForVndRtrnReceipt(scrnMsg, this.getGlobalCompanyCode());
        // QC#25902 End
        setArgForSubScreen(params);

     }
}
