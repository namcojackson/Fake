/*
 * <pre>Copyright (c) 2022 Canon USA Inc. All rights reserved.</pre>
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
 * 2022/02/15   Hitachi         A.Kohinata      Create          QC#57090
 *</pre>
 */
public class NFBL2040Scrn00_OpenWin_VndRtrn3 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFBL2040BMsg scrnMsg = (NFBL2040BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.vndRtrnNum_L);
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

        Object[] params = NFBL2040CommonLogic.getParamNWAL1130ForVndRtrn(scrnMsg, this.getGlobalCompanyCode());

        setArgForSubScreen(params);
    }
}
