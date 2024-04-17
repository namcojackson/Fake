/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1500;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NWAL1500.common.NWAL1500CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/30   Fujitsu         T.Yoshida       Create          N/A
 *</pre>
 */
public class NWAL1500Scrn00_OpenWin_SubstituteItem extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;
        int selectIndex = getButtonSelectNumber();
        scrnMsg.xxCellIdx.setValue(selectIndex);

        scrnMsg.addCheckItem(scrnMsg.B.no(selectIndex).mdseCd_LL);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;
        scrnMsg.xxScrEventNm.setValue(ctx.getEventName());

        Object[] params = NWAL1500CommonLogic.getParamNWAL1130ForSbstItem(scrnMsg);
        setArgForSubScreen(params);
    }
}
