/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL3120;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NFCL3120.NFCL3120CMsg;
//import business.servlet.NFCL3120.constant.NFCL3120Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/01   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
public class NFCL3120Scrn00_Click_LinkCustomer extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NFCL3120BMsg scrnMsg = (NFCL3120BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NFCL3120BMsg scrnMsg = (NFCL3120BMsg) bMsg;

        //NFCL3120CMsg bizMsg = new NFCL3120CMsg();
        //bizMsg.setBusinessID("NFCL3120");
        //bizMsg.setFunctionCode("20");
        //EZDMsg.copy(scrnMsg, null, bizMsg, null);

        //return bizMsg;

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL3120BMsg scrnMsg = (NFCL3120BMsg) bMsg;

        scrnMsg.xxPopPrm_01.clear();
        scrnMsg.xxPopPrm_02.clear();
        scrnMsg.xxPopPrm_03.clear();
        scrnMsg.xxPopPrm_04.clear();
        scrnMsg.xxPopPrm_05.clear();
        scrnMsg.xxPopPrm_06.clear();
        scrnMsg.xxPopPrm_07.clear();
        scrnMsg.xxPopPrm_08.clear();
        scrnMsg.xxPopPrm_09.clear();
        scrnMsg.xxPopPrm_10.clear();
        scrnMsg.xxPopPrm_11.clear();
        scrnMsg.xxPopPrm_12.clear();
        scrnMsg.xxPopPrm_13.clear();
        scrnMsg.xxPopPrm_14.clear();
        
        
        Object[] param = new Object[15];
        param[0] = scrnMsg.dsAcctNum_H1;
        param[1] = scrnMsg.dsAcctNm_H1;
        param[2] = scrnMsg.xxPopPrm_02;
        param[3] = scrnMsg.xxPopPrm_03;
        param[4] = scrnMsg.xxPopPrm_04;
        param[5] = scrnMsg.xxPopPrm_05;
        param[6] = scrnMsg.xxPopPrm_06;
        param[7] = scrnMsg.xxPopPrm_07;
        param[8] = scrnMsg.xxPopPrm_08;
        param[9] = scrnMsg.xxPopPrm_09;
        param[10] = scrnMsg.xxPopPrm_10;
        param[11] = scrnMsg.xxPopPrm_11;
        param[12] = scrnMsg.xxPopPrm_12;
        param[13] = scrnMsg.xxPopPrm_13;
        param[14] = scrnMsg.xxPopPrm_14;

        setArgForSubScreen(param);

    }
}
