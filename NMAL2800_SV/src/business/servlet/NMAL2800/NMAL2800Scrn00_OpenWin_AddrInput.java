/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2800;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL2800Scrn00_OpenWin_AddrInput
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/16   Fujitsu         C.Tanaka        Create          N/A
 *</pre>
 */
public class NMAL2800Scrn00_OpenWin_AddrInput extends S21CommonHandler {

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

        NMAL2800BMsg scrnMsg = (NMAL2800BMsg) bMsg;

        scrnMsg.P.clear();

        int index = getButtonSelectNumber();
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).xxPopPrm, scrnMsg.A.no(index).aftLocFirstLineAddr_A1);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(1).xxPopPrm, scrnMsg.A.no(index).aftLocScdLineAddr_A1);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(2).xxPopPrm, scrnMsg.A.no(index).aftLocThirdLineAddr_A1);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(3).xxPopPrm, scrnMsg.A.no(index).aftLocFrthLineAddr_A1);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(4).xxPopPrm, scrnMsg.A.no(index).aftLocCtyAddr_A1);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(5).xxPopPrm, scrnMsg.A.no(index).aftLocStCd_A1);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(7).xxPopPrm, scrnMsg.A.no(index).cntyNm_A1);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(8).xxPopPrm, scrnMsg.A.no(index).aftLocPostCd_A1);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(9).xxPopPrm, scrnMsg.A.no(index).aftCtryCd_A1);

        Object[] params = new Object[10];
        params[0] = scrnMsg.P.no(0).xxPopPrm;
        params[1] = scrnMsg.P.no(1).xxPopPrm;
        params[2] = scrnMsg.P.no(2).xxPopPrm;
        params[3] = scrnMsg.P.no(3).xxPopPrm;
        params[4] = scrnMsg.P.no(4).xxPopPrm;
        params[5] = scrnMsg.P.no(5).xxPopPrm;
        params[6] = scrnMsg.P.no(6).xxPopPrm;
        params[7] = scrnMsg.P.no(7).xxPopPrm;
        params[8] = scrnMsg.P.no(8).xxPopPrm;
        params[9] = scrnMsg.P.no(9).xxPopPrm;

        setArgForSubScreen(params);
    }
}
