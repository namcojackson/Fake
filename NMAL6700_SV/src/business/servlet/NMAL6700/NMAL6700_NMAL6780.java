/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6700;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NMAL6700.NMAL6700CMsg;
//import business.servlet.NMAL6700.constant.NMAL6700Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/20   Fujitsu         N.Sugiura       Create          N/A
 *</pre>
 */
public class NMAL6700_NMAL6780 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        //do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6700BMsg scrnMsg = (NMAL6700BMsg) bMsg;
        String scrEventNm = scrnMsg.xxScrEventNm.getValue();
        int index = getButtonSelectNumber();
        if ("OpenWin_BillTo".equals(scrEventNm)) {
            scrnMsg.F.no(index).dsDefBillToCd_F1.setValue(scrnMsg.P.no(1).xxPopPrm.getValue());
        } else if ("OpenWin_ShipTo".equals(scrEventNm)) {
            scrnMsg.F.no(index).dsDefShipToCd_F1.setValue(scrnMsg.P.no(2).xxPopPrm.getValue());
        }

    }
}
