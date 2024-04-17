/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
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
 * 2016/07/27   Hitachi         T.Tsuchida      Create          QC#12002
 * 2016/09/28   Hitachi         T.Tsuchida      Update          QC#13960
 * 2016/10/26   Hitachi         Y.Tsuchimoto    Update          QC#15493
 * 2018/05/25   CITS            K.Ogino         Update          QC#25902,QC#25190,QC#25141
 *</pre>
 */
public class NFBL2040Scrn00_OpenWin_Receipt2 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NFBL2040BMsg scrnMsg = (NFBL2040BMsg) bMsg;
        // START 2016/10/26 Y.Tsuchimoto [QC#15493,MOD]
        scrnMsg.addCheckItem(scrnMsg.poNum_HT);
        // END   2016/10/26 Y.Tsuchimoto [QC#15493,MOD]
        scrnMsg.addCheckItem(scrnMsg.delyOrdNum_DH);
        // START QC#25902,QC#25190,QC#25141
        scrnMsg.addCheckItem(scrnMsg.vndRtrnNum_H);
        // END QC#25902,QC#25190,QC#25141
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

        // START 2016/10/26 Y.Tsuchimoto [QC#15493,MOD]
        Object[] params = NFBL2040CommonLogic.getParamNWAL1130ForReceipt(scrnMsg, this.getGlobalCompanyCode(), scrnMsg.poNum_HT.getValue(), scrnMsg.delyOrdNum_DH.getValue(), scrnMsg.rwsNum_DH.getValue());
        // END   2016/10/26 Y.Tsuchimoto [QC#15493,MOD]
        setArgForSubScreen(params);
    }
}
