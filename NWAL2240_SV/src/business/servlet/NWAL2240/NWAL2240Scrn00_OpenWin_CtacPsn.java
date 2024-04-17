/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2240;

import static business.servlet.NWAL2240.constant.NWAL2240Constant.POP_PAR_15;
import static business.servlet.NWAL2240.constant.NWAL2240Constant.POP_PAR_16;
import static business.servlet.NWAL2240.constant.NWAL2240Constant.POP_PAR_17;
import static business.servlet.NWAL2240.constant.NWAL2240Constant.POP_PAR_19;
import static business.servlet.NWAL2240.constant.NWAL2240Constant.POP_PAR_20;
import static business.servlet.NWAL2240.constant.NWAL2240Constant.POP_PAR_3;
import static business.servlet.NWAL2240.constant.NWAL2240Constant.POP_PAR_4;
import static business.servlet.NWAL2240.constant.NWAL2240Constant.POP_PAR_6;
import static business.servlet.NWAL2240.constant.NWAL2240Constant.POP_PAR_7;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/13   Fujitsu         S.Ohki          Create          N/A
 *</pre>
 */
public class NWAL2240Scrn00_OpenWin_CtacPsn extends S21CommonHandler {

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

        NWAL2240BMsg scrnMsg = (NWAL2240BMsg) bMsg;

        int idx = getButtonSelectNumber();

        ZYPTableUtil.clear(scrnMsg.P);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(POP_PAR_3).xxPopPrm, scrnMsg.shipToCustAcctCd_H0.getValue());
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(POP_PAR_4).xxPopPrm, scrnMsg.locNum_H0.getValue());
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(POP_PAR_6).xxPopPrm, scrnMsg.C.no(idx).ctacPsnFirstNm_C0);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(POP_PAR_7).xxPopPrm, scrnMsg.C.no(idx).ctacPsnLastNm_C0);

        Object[] params = new Object[POP_PAR_20];

        for (int i = 0; i < POP_PAR_15; i++) {
            params[i] = scrnMsg.P.no(i).xxPopPrm;
        }

        params[POP_PAR_15] = scrnMsg.P.no(POP_PAR_15).dsCtacPntPk;
        params[POP_PAR_16] = scrnMsg.P.no(POP_PAR_16).dsCtacPntPk;
        params[POP_PAR_17] = scrnMsg.P.no(POP_PAR_17).dsCtacPntPk;
        params[POP_PAR_19] = scrnMsg.P.no(POP_PAR_19).dsCtacPntPk;

        setArgForSubScreen(params);
    }
}
