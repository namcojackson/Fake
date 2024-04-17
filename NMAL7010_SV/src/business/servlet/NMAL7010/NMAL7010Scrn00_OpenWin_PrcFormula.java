/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7010;

import static business.servlet.NMAL7010.constant.NMAL7010Constant.NMAM8217E;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.NMAM8225E;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import business.servlet.NMAL7010.common.NMAL7010CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_QLFY_TP;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL7010Scrn00_OpenWin_PrcFormula
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/03   Fujitsu         M.Nakamura      Create          N/A
 *</pre>
 */
public class NMAL7010Scrn00_OpenWin_PrcFormula extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        int idx = getButtonSelectNumber();

        NMAL7010BMsg scrnMsg = (NMAL7010BMsg) bMsg;
        if (!PRC_QLFY_TP.ITEM_CODE.equals(scrnMsg.A.no(idx).prcQlfyTpCd_PA.getValue())) {
            if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(idx).prcQlfyValTxt_PA)) {
                scrnMsg.A.no(idx).prcFmlaPk_PA.setErrorInfo(1, NMAM8225E);
            }
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.A.no(idx).prcListEquipPrcAmt_PA)) {
            scrnMsg.A.no(idx).prcFmlaPk_PA.setErrorInfo(1, NMAM8217E);
            scrnMsg.A.no(idx).prcListEquipPrcAmt_PA.setErrorInfo(1, NMAM8217E);
            scrnMsg.addCheckItem(scrnMsg.A.no(idx).prcFmlaPk_PA);
            scrnMsg.addCheckItem(scrnMsg.A.no(idx).prcListEquipPrcAmt_PA);
        }
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7010BMsg scrnMsg = (NMAL7010BMsg) bMsg;

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrEventNm_DH, "OpenWin_PrcFormula");
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxCellIdx, new BigDecimal(getButtonSelectNumber()));

        setArgForSubScreen(NMAL7010CommonLogic.setArgumentNWAL1130(scrnMsg, ctx.getEventName(), getButtonSelectNumber(), getGlobalCompanyCode()));
    }
}
