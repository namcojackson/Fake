/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0920;

import static business.servlet.NSAL0920.constant.NSAL0920Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.*;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import business.servlet.NSAL0920.common.NSAL0920CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/06   Hitachi         T.Tomita        Create          QC#1029
 *</pre>
 */
public class NSAL0920Scrn00_OpenWin_BillToLocNm extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSAL0920BMsg scrnMsg = (NSAL0920BMsg) bMsg;
        NSAL0920CommonLogic.clearPopupParameter(scrnMsg);
        setValue(scrnMsg.xxScrEventNm, OPENWIN_BILLTOLOCNM);
        setValue(scrnMsg.xxPopPrm_00, scrnMsg.dsAcctNum);
        setValue(scrnMsg.xxPopPrm_12, DISP_HRCH_ACCT_CD_BILL);
        setValue(scrnMsg.xxPopPrm_03, scrnMsg.locNm);
        Object[] params = new Object[24];
        params[0] = scrnMsg.xxPopPrm_00;
        params[1] = scrnMsg.xxPopPrm_01;
        params[2] = scrnMsg.xxPopPrm_02;
        params[3] = scrnMsg.xxPopPrm_03;
        params[4] = scrnMsg.xxPopPrm_04;
        params[5] = scrnMsg.xxPopPrm_05;
        params[6] = scrnMsg.xxPopPrm_06;
        params[7] = scrnMsg.xxPopPrm_07;
        params[8] = scrnMsg.xxPopPrm_08;
        params[9] = scrnMsg.xxPopPrm_09;
        params[10] = scrnMsg.xxPopPrm_10;
        params[11] = scrnMsg.xxPopPrm_11;
        params[12] = scrnMsg.xxPopPrm_12;
        params[13] = scrnMsg.xxPopPrm_13;
        params[14] = scrnMsg.xxPopPrm_14;
        params[15] = scrnMsg.xxPopPrm_15;
        params[16] = scrnMsg.xxPopPrm_16;
        params[17] = scrnMsg.xxPopPrm_17;
        params[18] = scrnMsg.xxPopPrm_18;
        params[19] = scrnMsg.xxPopPrm_19;
        params[20] = scrnMsg.xxPopPrm_20;
        params[21] = scrnMsg.xxPopPrm_21;
        params[22] = scrnMsg.xxPopPrm_22;
        params[23] = scrnMsg.xxPopPrm_23;
        setArgForSubScreen(params);
    }
}
