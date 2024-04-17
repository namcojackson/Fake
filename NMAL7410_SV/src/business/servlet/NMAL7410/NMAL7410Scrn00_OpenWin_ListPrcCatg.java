/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7410;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL7410.common.NMAL7410CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/29   Fujitsu         T.Murai         Create          N/A
 *</pre>
 */
public class NMAL7410Scrn00_OpenWin_ListPrcCatg extends S21CommonHandler {

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

        NMAL7410BMsg scrnMsg = (NMAL7410BMsg) bMsg;
        scrnMsg.xxScrEventNm.setValue(ctx.getEventName());
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxCellIdx, BigDecimal.valueOf(getButtonSelectNumber()));

        Object[] params = NMAL7410CommonLogic.getParamNWAL1130ListPrcCatg(scrnMsg);
        setArgForSubScreen(params);
    }
}