/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7080;

import static business.servlet.NMAL7080.constant.NMAL7080Constant.MODE_8_DIGIT;
import static business.servlet.NMAL7080.constant.NMAL7080Constant.ZZM9000E;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL7080.common.NMAL7080CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Supply Agreement Plan Set Up
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/15   Fujitsu         M.Ohno          Create          N/A
 *</pre>
 */
public class NMAL7080Scrn00_OpenWin_Mdse extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7080BMsg scrnMsg = (NMAL7080BMsg) bMsg;

        NMAL7080CommonLogic.clearMandantoryCheckHeader(scrnMsg);
        NMAL7080CommonLogic.clearMandantoryCheckDetail(scrnMsg);
        if (!ZYPCommonFunc.hasValue(scrnMsg.xxDt10Dt_MD)) {
            scrnMsg.xxDt10Dt_MD.setErrorInfo(1, ZZM9000E, new String[] {scrnMsg.xxDt10Dt_MD.getNameForMessage() });
        }
        NMAL7080CommonLogic.headerAddCheckItem(scrnMsg);
        NMAL7080CommonLogic.detailAddCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7080BMsg scrnMsg = (NMAL7080BMsg) bMsg;

        int i = 0;
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm_P, scrnMsg.A.no(getButtonSelectNumber()).mdseCd_A);
        scrnMsg.P.no(i++).xxPopPrm_P.clear();
        scrnMsg.P.no(i++).xxPopPrm_P.clear();
        scrnMsg.P.no(i++).xxPopPrm_P.clear();
        scrnMsg.P.no(i++).xxPopPrm_P.clear();
        scrnMsg.P.no(i++).xxPopPrm_P.clear();
        scrnMsg.P.no(i++).xxPopPrm_P.clear();
        scrnMsg.P.no(i++).xxPopPrm_P.clear();
        scrnMsg.P.no(i++).xxPopPrm_P.clear();
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm_P, MODE_8_DIGIT);

        scrnMsg.P.setValidCount(i);

        Object[] params = new Object[10];
        for (int j = 0; j < scrnMsg.P.getValidCount() && j < params.length; j++) {
            params[j] = scrnMsg.P.no(j).xxPopPrm_P;
        }
        setArgForSubScreen(params);

    }
}
