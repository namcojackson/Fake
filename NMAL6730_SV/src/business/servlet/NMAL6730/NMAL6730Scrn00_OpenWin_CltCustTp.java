/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6730;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.servlet.NMAL6730.common.NMAL6730CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/05   Fujitsu         N.Sugiura       Create          N/A
 *</pre>
 */
public class NMAL6730Scrn00_OpenWin_CltCustTp extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NMAL6730BMsg scrnMsg = (NMAL6730BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NMAL6730BMsg scrnMsg = (NMAL6730BMsg) bMsg;

        //NMAL6730CMsg bizMsg = new NMAL6730CMsg();
        //bizMsg.setBusinessID("NMAL6730");
        //bizMsg.setFunctionCode("20");
        //EZDMsg.copy(scrnMsg, null, bizMsg, null);

        //return bizMsg;

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6730BMsg scrnMsg = (NMAL6730BMsg) bMsg;

        NMAL6730CommonLogic.clearParams(scrnMsg);

        Object[] params = new Object[11];
        scrnMsg.xxPopPrm_P0.setValue("CLT_CUST_TP");
        scrnMsg.xxPopPrm_P1.setValue("CLT_CUST_TP_CD");
        scrnMsg.xxPopPrm_P2.setValue("CLT_CUST_TP_NM");
        scrnMsg.xxPopPrm_P3.setValue("CLT_CUST_TP_CD");
        scrnMsg.xxPopPrm_P4.setValue("Collection Customer Type Popup");
        scrnMsg.xxPopPrm_P5.setValue("Collection Customer Type Code");
        scrnMsg.xxPopPrm_P6.setValue("Collection Customer Type Name");
        scrnMsg.xxPopPrm_P7.setValue("Collection Customer Type Code");
        scrnMsg.xxPopPrm_P8.setValue("Collection Customer Type Name");
        scrnMsg.xxPopPrm_P9.setValue(scrnMsg.cltCustTpCd_F1.getValue());

        params[0] = scrnMsg.xxPopPrm_P0;
        params[1] = scrnMsg.xxPopPrm_P1;
        params[2] = scrnMsg.xxPopPrm_P2;
        params[3] = scrnMsg.xxPopPrm_P3;
        params[4] = scrnMsg.xxPopPrm_P4;
        params[5] = scrnMsg.xxPopPrm_P5;
        params[6] = scrnMsg.xxPopPrm_P6;
        params[7] = scrnMsg.xxPopPrm_P7;
        params[8] = scrnMsg.xxPopPrm_P8;
        params[9] = scrnMsg.xxPopPrm_P9;
        params[10] = scrnMsg.xxPopPrm_PA;

        setArgForSubScreen(params);

    }
}
