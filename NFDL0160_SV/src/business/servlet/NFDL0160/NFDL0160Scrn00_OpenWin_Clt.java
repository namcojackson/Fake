/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFDL0160;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NFDL0160.common.NFDL0160CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Collector Portfolio Maintenance
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/17   Fujitsu         C.Naito         Create          N/A
 *</pre>
 */
public class NFDL0160Scrn00_OpenWin_Clt extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NFDL0160BMsg scrnMsg = (NFDL0160BMsg) bMsg;

        int selectedIndex = this.getButtonSelectNumber();
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxCellIdx, BigDecimal.valueOf(selectedIndex));
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFDL0160BMsg scrnMsg = (NFDL0160BMsg) bMsg;

        scrnMsg.xxScrEventNm.setValue(ctx.getEventName());

        Object[] params = NFDL0160CommonLogic.getParamNWAL1130ForOpenWinClt(scrnMsg);
        setArgForSubScreen(params);
    }
}
