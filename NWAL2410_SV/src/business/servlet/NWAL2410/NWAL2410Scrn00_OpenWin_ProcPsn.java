/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2410;

import static business.servlet.NWAL2410.constant.NWAL2410Constant.EVNT_PROC_PSN;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NWAL2410.common.NWAL2410CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL2410Scrn00_OpenWin_ProcPsn
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/01/25   Fujitsu         q10253          Create          N/A
 *</pre>
 */
public class NWAL2410Scrn00_OpenWin_ProcPsn extends S21CommonHandler {

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

        NWAL2410BMsg scrnMsg = (NWAL2410BMsg) bMsg;
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrEventNm, EVNT_PROC_PSN);
        Object[] params = NWAL2410CommonLogic.setAuthPsnPopupParam(scrnMsg, getGlobalCompanyCode(), scrnMsg.A.no(getButtonSelectNumber()).defOrdProcPsnCd_A.getValue());
        setArgForSubScreen(params);
    }
}
