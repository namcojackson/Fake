/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6720;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/03   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
public class NMAL6720Scrn00_OpenWin_CtacSearch extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NMAL6720BMsg scrnMsg = (NMAL6720BMsg) bMsg;

        scrnMsg.P.clear();

        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(4).xxPopPrm_P, scrnMsg.locNum_H1);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(11).xxPopPrm_P, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(23).xxPopPrm_P, ZYPConstant.FLG_ON_Y);

        Object[] params = new Object[24];
        params[0] = scrnMsg.P.no(0).xxPopPrm_P;
        params[1] = scrnMsg.P.no(1).xxPopPrm_P;
        params[2] = scrnMsg.dsAcctNm_PR;
        params[3] = scrnMsg.P.no(3).xxPopPrm_P;
        params[4] = scrnMsg.P.no(4).xxPopPrm_P;
        params[5] = scrnMsg.P.no(5).xxPopPrm_P;
        params[6] = scrnMsg.P.no(6).xxPopPrm_P;
        params[7] = scrnMsg.P.no(7).xxPopPrm_P;
        params[8] = scrnMsg.P.no(8).xxPopPrm_P;
        params[9] = scrnMsg.P.no(9).xxPopPrm_P;
        params[10] = scrnMsg.P.no(10).xxPopPrm_P;
        params[11] = scrnMsg.P.no(11).xxPopPrm_P;
        params[12] = scrnMsg.P.no(12).xxPopPrm_P;
        params[13] = scrnMsg.P.no(13).xxPopPrm_P;
        params[14] = scrnMsg.P.no(14).xxPopPrm_P;
        params[15] = scrnMsg.P.no(15).ctacPsnPk_P;
        params[16] = scrnMsg.P.no(16).ctacPsnPk_P;
        params[17] = scrnMsg.P.no(17).ctacPsnPk_P;
        // params[18] = null;
        params[19] = scrnMsg.ctacPsnPk_H1;
        params[20] = scrnMsg.P.no(20).xxPopPrm_P;
        params[21] = scrnMsg.P.no(21).xxPopPrm_P;
        params[22] = scrnMsg.P.no(22).xxPopPrm_P;
        params[23] = scrnMsg.P.no(23).xxPopPrm_P;

        setArgForSubScreen(params);
    }
}
