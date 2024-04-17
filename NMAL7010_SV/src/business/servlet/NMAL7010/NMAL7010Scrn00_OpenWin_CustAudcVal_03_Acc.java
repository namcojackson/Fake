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
 * 2016/04/22   SRA             E.Inada         Create          QC#7376
 *</pre>
 */
public class NMAL7010Scrn00_OpenWin_CustAudcVal_03_Acc extends S21CommonHandler {

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

        int idx = getButtonSelectNumber();
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrEventNm_DH, "OpenWin_CustAudcVal_03_Acc");
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxCellIdx, new BigDecimal(idx));

        setArgForSubScreen(NMAL7010CommonLogic.createArgumentNMWAL6760(scrnMsg.X.no(idx).xxScrItem30Txt_X3, scrnMsg.X.no(idx).xxRecNmTxt_X3));

        scrnMsg.setFocusItem(scrnMsg.X.no(idx).xxScrItem30Txt_X3);
    }
}
