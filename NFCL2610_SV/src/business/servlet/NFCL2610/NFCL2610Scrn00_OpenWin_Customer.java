/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL2610;

import static business.servlet.NFCL2610.constant.NFCL2610Constant.*;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NFCL2610.common.NFCL2610CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/10   Hitachi         J.Kim           Create          N/A
 *</pre>
 */
public class NFCL2610Scrn00_OpenWin_Customer extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL2610BMsg scrnMsg = (NFCL2610BMsg) bMsg;
        NFCL2610CommonLogic.clearPopUpParam(scrnMsg);

        setValue(scrnMsg.xxScrEventNm, "OpenWin_Customer");
        Object[] params = new Object[PRM_NMAL6760];
        int i = 0;

        setValue(scrnMsg.xxPopPrm_P0, scrnMsg.billToCustAcctCd);
        params[i++] = scrnMsg.xxPopPrm_P0;
        params[i++] = scrnMsg.xxPopPrm_P1;
        params[i++] = scrnMsg.xxPopPrm_P2;
        params[i++] = scrnMsg.xxPopPrm_P3;
        params[i++] = scrnMsg.xxPopPrm_P4;
        params[i++] = scrnMsg.xxPopPrm_P5;
        params[i++] = scrnMsg.xxPopPrm_P6;
        params[i++] = scrnMsg.xxPopPrm_P7;
        params[i++] = scrnMsg.xxPopPrm_P8;
        params[i++] = scrnMsg.xxPopPrm_P9;
        params[i++] = scrnMsg.xxPopPrm_PA;
        params[i++] = scrnMsg.xxPopPrm_PB;
        setValue(scrnMsg.xxPopPrm_PC, DISP_HRCH_ACCT_CD_BILL);
        params[i++] = scrnMsg.xxPopPrm_PC;
        params[i++] = scrnMsg.xxPopPrm_PD;
        params[i++] = scrnMsg.xxPopPrm_PE;
        setValue(scrnMsg.xxPopPrm_PF, scrnMsg.billToCustLocCd);
        params[i++] = scrnMsg.xxPopPrm_PF;
        params[i++] = scrnMsg.xxPopPrm_PG;
        params[i++] = scrnMsg.xxPopPrm_PH;
        params[i++] = scrnMsg.xxPopPrm_PI;
        params[i++] = scrnMsg.xxPopPrm_PJ;
        params[i++] = scrnMsg.xxPopPrm_PK;
        params[i++] = scrnMsg.xxPopPrm_PL;
        params[i++] = scrnMsg.xxPopPrm_PM;
        params[i++] = scrnMsg.xxPopPrm_PN;
        setArgForSubScreen(params);
    }
}
