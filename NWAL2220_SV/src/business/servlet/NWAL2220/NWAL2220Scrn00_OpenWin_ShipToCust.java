/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2220;

import static business.servlet.NWAL2220.constant.NWAL2220Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import business.servlet.NWAL2220.common.NWAL2220CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Import  Search & Result
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/17   Hitachi         T.Tsuchida      Create          N/A
 *</pre>
 */
public class NWAL2220Scrn00_OpenWin_ShipToCust extends S21CommonHandler {


    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL2220BMsg scrnMsg = (NWAL2220BMsg) bMsg;

        scrnMsg.addCheckItem(scrnMsg.shipToCustAcctCd);
        scrnMsg.addCheckItem(scrnMsg.dsAcctNm_HT);
        scrnMsg.addCheckItem(scrnMsg.shipToCustCd);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL2220BMsg scrnMsg = (NWAL2220BMsg) bMsg;

        Object[] params = new Object[NMAL6760_PRM_LENGTH];
        NWAL2220CommonLogic.initParam(scrnMsg);
        setValue(scrnMsg.xxScrEventNm, ctx.getEventName());
        setValue(scrnMsg.xxPopPrm_00, scrnMsg.shipToCustAcctCd);
        setValue(scrnMsg.xxPopPrm_01, scrnMsg.dsAcctNm_HT);
        setValue(scrnMsg.xxPopPrm_12, DISP_HRCH_ACCT_CD_SHIP);
        setValue(scrnMsg.xxPopPrm_16, scrnMsg.shipToCustCd);

        int i = 0;
        params[i++] = scrnMsg.xxPopPrm_00;
        params[i++] = scrnMsg.xxPopPrm_01;
        params[i++] = scrnMsg.xxPopPrm_02;
        params[i++] = scrnMsg.xxPopPrm_03;
        params[i++] = scrnMsg.xxPopPrm_04;
        params[i++] = scrnMsg.xxPopPrm_05;
        params[i++] = scrnMsg.xxPopPrm_06;
        params[i++] = scrnMsg.xxPopPrm_07;
        params[i++] = scrnMsg.xxPopPrm_08;
        params[i++] = scrnMsg.xxPopPrm_09;
        params[i++] = scrnMsg.xxPopPrm_10;
        params[i++] = scrnMsg.xxPopPrm_11;
        params[i++] = scrnMsg.xxPopPrm_12;
        params[i++] = scrnMsg.xxPopPrm_13;
        params[i++] = scrnMsg.xxPopPrm_14;
        params[i++] = scrnMsg.xxPopPrm_15;
        params[i++] = scrnMsg.xxPopPrm_16;
        params[i++] = scrnMsg.xxPopPrm_17;
        params[i++] = scrnMsg.xxPopPrm_18;
        params[i++] = scrnMsg.xxPopPrm_19;
        params[i++] = scrnMsg.xxPopPrm_20;
        params[i++] = scrnMsg.xxPopPrm_21;
        params[i++] = scrnMsg.xxPopPrm_22;
        params[i++] = scrnMsg.xxPopPrm_23;
        params[i++] = scrnMsg.xxPopPrm_24;
        params[i++] = scrnMsg.xxPopPrm_25;
        params[i++] = scrnMsg.xxPopPrm_26;
        params[i++] = scrnMsg.xxPopPrm_27;
        params[i++] = scrnMsg.xxPopPrm_28;
        params[i++] = scrnMsg.xxPopPrm_29;
        params[i++] = scrnMsg.xxPopPrm_30;
        params[i++] = scrnMsg.xxPopPrm_31;
        params[i++] = scrnMsg.xxPopPrm_32;
        params[i++] = scrnMsg.xxPopPrm_33;
        params[i++] = scrnMsg.xxPopPrm_34;
        setArgForSubScreen(params);
    }
}
