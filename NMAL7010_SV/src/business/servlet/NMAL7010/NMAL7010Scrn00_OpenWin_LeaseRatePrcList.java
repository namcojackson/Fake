/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7010;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL7010.common.NMAL7010CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/21   SRA             E.Inada         Create          QC#7080
 *</pre>
 */
public class NMAL7010Scrn00_OpenWin_LeaseRatePrcList extends S21CommonHandler {

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

        NMAL7010BMsg scrnMsg = (NMAL7010BMsg) bMsg;

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrEventNm_DH, "OpenWin_LeaseRatePrcList");
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxCellIdx, new BigDecimal(getButtonSelectNumber()));

        setArgForSubScreen(NMAL7010CommonLogic.setArgumentNWAL1130(scrnMsg, ctx.getEventName(), getButtonSelectNumber(), getGlobalCompanyCode()));

        scrnMsg.setFocusItem(scrnMsg.prcCatgNm_CA);
    }
}
