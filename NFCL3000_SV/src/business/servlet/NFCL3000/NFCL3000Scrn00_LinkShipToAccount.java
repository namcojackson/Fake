/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL3000;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NFCL3000.NFCL3000CMsg;
//import business.servlet.NFCL3000.constant.NFCL3000Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
public class NFCL3000Scrn00_LinkShipToAccount extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NFCL3000BMsg scrnMsg = (NFCL3000BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NFCL3000BMsg scrnMsg = (NFCL3000BMsg) bMsg;

        //NFCL3000CMsg bizMsg = new NFCL3000CMsg();
        //bizMsg.setBusinessID("NFCL3000");
        //bizMsg.setFunctionCode("20");
        //EZDMsg.copy(scrnMsg, null, bizMsg, null);

        //return bizMsg;

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL3000BMsg scrnMsg = (NFCL3000BMsg) bMsg;

        scrnMsg.P.clear();
        
        //NMAL6760
        Object[] param = new Object[15];
        param[0] = scrnMsg.shipToCustAcctCd_H2;
        param[1] = scrnMsg.shipToCustAcctNm_H2;
        param[2] = scrnMsg.locNum_H2;
        param[3] = scrnMsg.P.no(3).xxPopPrm;
        param[4] = scrnMsg.P.no(4).xxPopPrm;
        param[5] = scrnMsg.P.no(5).xxPopPrm;
        param[6] = scrnMsg.P.no(6).xxPopPrm;
        param[7] = scrnMsg.P.no(7).xxPopPrm;
        param[8] = scrnMsg.P.no(8).xxPopPrm;
        param[9] = scrnMsg.P.no(9).xxPopPrm;
        param[10] = scrnMsg.P.no(10).xxPopPrm;
        param[11] = scrnMsg.P.no(11).xxPopPrm;
        param[12] = scrnMsg.P.no(12).xxPopPrm;
        param[13] = scrnMsg.P.no(13).xxPopPrm;
        param[14] = scrnMsg.P.no(14).xxPopPrm;
        
        setArgForSubScreen(param);
    }
}
