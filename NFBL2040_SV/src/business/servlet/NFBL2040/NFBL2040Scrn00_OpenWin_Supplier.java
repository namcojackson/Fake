/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFBL2040;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NFBL2040.common.NFBL2040CommonLogic;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/22   Hitachi         Y.Tsuchimoto    Create          QC#12008
 * 2016/08/08   Hitachi         Y.Tsuchimoto    Update          QC#12854
 * 2016/10/04   Hitachi         T.Tsuchida      Update          QC#13414
 * 2017/11/15   CITS            T.Wada          Update          QC#21727
 *</pre>
 */
public class NFBL2040Scrn00_OpenWin_Supplier extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFBL2040BMsg scrnMsg = (NFBL2040BMsg) bMsg;
        //QC#21727
//        scrnMsg.addCheckItem(scrnMsg.prntVndCd);
//        scrnMsg.addCheckItem(scrnMsg.dplyVndNm);
        // START 2016/08/08 Y.Tsuchimoto [QC#12854,DEL]
        //scrnMsg.addCheckItem(scrnMsg.apVndCd);
        // END   2016/08/08 Y.Tsuchimoto [QC#12854,DEL]
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

        Object[] params = NFBL2040CommonLogic.getParamNWAL1130ForSupplier(scrnMsg, this.getGlobalCompanyCode(), ZYPDateUtil.getSalesDate(this.getGlobalCompanyCode()));
        setArgForSubScreen(params);
    }
}
