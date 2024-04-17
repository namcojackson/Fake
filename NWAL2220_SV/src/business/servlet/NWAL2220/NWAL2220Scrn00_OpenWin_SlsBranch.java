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
public class NWAL2220Scrn00_OpenWin_SlsBranch extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL2220BMsg scrnMsg = (NWAL2220BMsg) bMsg;

        scrnMsg.addCheckItem(scrnMsg.coaBrDescTxt);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL2220BMsg scrnMsg = (NWAL2220BMsg) bMsg;

        Object[] params = new Object[NMAL6050_PRM_LENGTH];
        NWAL2220CommonLogic.initParam(scrnMsg);
        setValue(scrnMsg.xxScrEventNm, ctx.getEventName());
        setValue(scrnMsg.xxPopPrm_00, TBL_NM_COA_BR);
        setValue(scrnMsg.xxPopPrm_01, COL_CD_COA_BR_CD);
        setValue(scrnMsg.xxPopPrm_02, COL_NM_COA_BR_NM);
        setValue(scrnMsg.xxPopPrm_03, COL_SORT_COA_BR_CD);
        setValue(scrnMsg.xxPopPrm_04, SCR_NM_SLS_BR);
        setValue(scrnMsg.xxPopPrm_05, HDR_COL_CD_SLS_BR);
        setValue(scrnMsg.xxPopPrm_06, HDR_COL_NM_SLS_BR);
        setValue(scrnMsg.xxPopPrm_07, DTL_COL_CD_SLS_BR);
        setValue(scrnMsg.xxPopPrm_08, DTL_COL_NM_SLS_BR);
        setValue(scrnMsg.xxPopPrm_10, scrnMsg.coaBrDescTxt);

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
        setArgForSubScreen(params);
    }
}
