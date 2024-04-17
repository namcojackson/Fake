/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL3000;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFCL3000.NFCL3000CMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/08   Fujitus         T.Tanaka        Create          N/A
 *</pre>
 */
public class NFCL3000Scrn00_CreditCard extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL3000BMsg scrnMsg = (NFCL3000BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.invNum_H1);
        scrnMsg.addCheckItem(scrnMsg.billToCustAcctCd_H3);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL3000BMsg scrnMsg = (NFCL3000BMsg) bMsg;

        NFCL3000CMsg bizMsg = new NFCL3000CMsg();
        bizMsg.setBusinessID("NFCL3000");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL3000BMsg scrnMsg = (NFCL3000BMsg) bMsg;
        NFCL3000CMsg bizMsg  = (NFCL3000CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        //NWAL2010 Credit Card Screen
        scrnMsg.P.clear();
        Object[] params = new Object[13];
        params[0] = scrnMsg.billToCustAcctCd_H3;
        params[1] = scrnMsg.crCardTrxTpCd_E1;
        params[2] = scrnMsg.invNum_H1;
        params[3] = scrnMsg.P.no(3).xxPopPrm;
        params[4] = scrnMsg.P.no(4).xxPopPrm;
        params[5] = scrnMsg.P.no(5).xxPopPrm;
        params[6] = scrnMsg.P.no(6).xxPopPrm;
        params[7] = scrnMsg.firstTrxInfoNum;
        params[8] = scrnMsg.scdTrxInfoNum;
        params[9] = scrnMsg.thirdTrxInfoNum;
        params[10] = scrnMsg.frthTrxInfoNum;
        params[11] = scrnMsg.fifthTrxInfoNum;
        params[12] = scrnMsg.crCardTrxPk_E1;

        setArgForSubScreen(params);
    }
}
