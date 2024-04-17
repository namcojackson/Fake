/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL3170;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NFCL3170.NFCL3170CMsg;
//import business.servlet.NFCL3170.constant.NFCL3170Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * bank Account Maintenance
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/08/2015   Fujitsu         T.Tanaka        Create          Initial
 *</pre>
 */
public class NFCL3170Scrn00_Click_LinkCustomer extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NFCL3170BMsg scrnMsg = (NFCL3170BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL3170BMsg scrnMsg = (NFCL3170BMsg) bMsg;
        if(!ZYPCommonFunc.hasValue(scrnMsg.dsAcctNum_H1.getValue())) {
            scrnMsg.dsAcctNm_H1.clear();
            scrnMsg.locNum_H1.clear();
        }

        //NMAL6760
        scrnMsg.P.clear();
        Object[] param = new Object[15];
        param[0] = scrnMsg.dsAcctNum_H1;
        param[1] = scrnMsg.dsAcctNm_H1;
        param[2] = scrnMsg.locNum_H1;
        param[3] = scrnMsg.P.no(3).xxPopPrm_P1;
        param[4] = scrnMsg.P.no(4).xxPopPrm_P1;
        param[5] = scrnMsg.P.no(5).xxPopPrm_P1;
        param[6] = scrnMsg.P.no(6).xxPopPrm_P1;
        param[7] = scrnMsg.P.no(7).xxPopPrm_P1;
        param[8] = scrnMsg.P.no(8).xxPopPrm_P1;
        param[9] = scrnMsg.P.no(9).xxPopPrm_P1;
        param[10] = scrnMsg.P.no(10).xxPopPrm_P1;
        param[11] = scrnMsg.P.no(11).xxPopPrm_P1;
        param[12] = scrnMsg.P.no(12).xxPopPrm_P1;
        param[13] = scrnMsg.P.no(13).xxPopPrm_P1;
        param[14] = scrnMsg.P.no(14).xxPopPrm_P1;

        setArgForSubScreen(param);
    }
}
