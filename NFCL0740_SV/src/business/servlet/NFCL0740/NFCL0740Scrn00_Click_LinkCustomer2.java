/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL0740;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Write-Off Request Creation
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/29   Fujitsu         T.Tanaka        Create          N/A
 * 2016/07/08   Hitachi         K.Kojima        Update          QC#8784
 *</pre>
 */
public class NFCL0740Scrn00_Click_LinkCustomer2 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        // START 2016/07/08 K.Kojima [QC#8784,DEL]
        // NFCL0740BMsg scrnMsg = (NFCL0740BMsg) bMsg;
        // scrnMsg.xxScrEventNm.setValue(ctx.getEventName());
        // NFCL0740CommonLogic.clearPopPrm(scrnMsg);
        //
        // Object[] param = new Object[15];
        // param[0] = scrnMsg.dsAcctNum_H2;
        // param[1] = scrnMsg.dsAcctNm_H2;
        // param[2] = scrnMsg.xxPopPrm_02;
        // param[3] = scrnMsg.xxPopPrm_03;
        // param[4] = scrnMsg.xxPopPrm_04;
        // param[5] = scrnMsg.xxPopPrm_05;
        // param[6] = scrnMsg.xxPopPrm_06;
        // param[7] = scrnMsg.xxPopPrm_07;
        // param[8] = scrnMsg.xxPopPrm_08;
        // param[9] = scrnMsg.xxPopPrm_09;
        // param[10] = scrnMsg.xxPopPrm_10;
        // param[11] = scrnMsg.xxPopPrm_11;
        // param[12] = scrnMsg.xxPopPrm_12;
        // param[13] = scrnMsg.xxPopPrm_13;
        // param[14] = scrnMsg.xxPopPrm_14;
        //
        // setArgForSubScreen(param);
        // END 2016/07/08 K.Kojima [QC#8784,DEL]
    }
}
