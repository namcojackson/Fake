/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
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
 * NMAL7010Scrn00_OpenWin_QualifierValueItem
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/15   Fujitsu         M.Nakamura      Create          N/A
 *</pre>
 */
public class NMAL7010Scrn00_OpenWin_QualifierValueItem extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7010BMsg scrnMsg = (NMAL7010BMsg) bMsg;

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrEventNm_DH, "OpenWin_QualifierValueItem");
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxCellIdx, new BigDecimal(getButtonSelectNumber()));

        setArgForSubScreen(NMAL7010CommonLogic.setArgumentNMAL6800(scrnMsg, ctx.getEventName(), getButtonSelectNumber()));
    }
}
