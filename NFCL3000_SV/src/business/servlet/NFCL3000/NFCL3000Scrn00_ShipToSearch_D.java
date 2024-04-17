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
public class NFCL3000Scrn00_ShipToSearch_D extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL3000BMsg scrnMsg = (NFCL3000BMsg) bMsg;

        scrnMsg.xxScrItem10Txt.setValue("ShipTo_D");
        int idx = getButtonSelectNumber();
        scrnMsg.xxCellIdx.setValue(idx);

        scrnMsg.P.clear();
        // Ship To Only
        scrnMsg.P.no(8).xxPopPrm.setValue("04");
        // Active Only
        scrnMsg.P.no(11).xxPopPrm.setValue("01");
        //NMAL6760
        Object[] param = new Object[17];
        param[0] = scrnMsg.D.no(idx).shipToCustAcctCd_D1;
        param[1] = scrnMsg.D.no(idx).shipToCustAcctNm_D1;
        param[2] = scrnMsg.D.no(idx).locNum_D1;
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
        param[15] = scrnMsg.P.no(15).xxPopPrm;
        // START 2016/08/01 S.Fujita [QC#10148,MOD]
//        param[16] = scrnMsg.D.no(idx).shipToCustCd_D1;
        param[16] = scrnMsg.P.no(16).xxPopPrm;
        // END   2016/08/01 S.Fujita [QC#10148,MOD]

        setArgForSubScreen(param);
    }
}
