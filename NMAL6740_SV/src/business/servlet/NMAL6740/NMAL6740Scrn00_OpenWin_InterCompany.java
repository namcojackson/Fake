/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6740;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL6740.common.NMAL6740CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/20   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
public class NMAL6740Scrn00_OpenWin_InterCompany extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

//        NMAL6740BMsg scrnMsg = (NMAL6740BMsg) bMsg;
//
//        NMAL6740CommonLogic.clearParams(scrnMsg);
//
//        Object[] params = new Object[11];
//        scrnMsg.xxPopPrm_P0.setValue("COA_AFFL");
//        scrnMsg.xxPopPrm_P1.setValue("COA_AFFL_CD");
//        scrnMsg.xxPopPrm_P2.setValue("COA_AFFL_NM");
//        scrnMsg.xxPopPrm_P3.setValue("COA_AFFL_SORT_NUM");
//        scrnMsg.xxPopPrm_P4.setValue("Intercompany");
//        scrnMsg.xxPopPrm_P5.setValue("Intercompany Cd");
//        scrnMsg.xxPopPrm_P6.setValue("Intercompany Nm");
//        scrnMsg.xxPopPrm_P7.setValue("Intercompany Cd");
//        scrnMsg.xxPopPrm_P8.setValue("Intercompany Nm");
//        scrnMsg.xxPopPrm_P9.clear();
//        scrnMsg.xxPopPrm_PA.clear();
//
//        params[0] = scrnMsg.xxPopPrm_P0;
//        params[1] = scrnMsg.xxPopPrm_P1;
//        params[2] = scrnMsg.xxPopPrm_P2;
//        params[3] = scrnMsg.xxPopPrm_P3;
//        params[4] = scrnMsg.xxPopPrm_P4;
//        params[5] = scrnMsg.xxPopPrm_P5;
//        params[6] = scrnMsg.xxPopPrm_P6;
//        params[7] = scrnMsg.xxPopPrm_P7;
//        params[8] = scrnMsg.xxPopPrm_P8;
//        params[9] = scrnMsg.coaAfflCd_F1;
//        params[10] = scrnMsg.coaAfflNm_F1;
//
//        setArgForSubScreen(params);
    }
}
