/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL3070;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/02/2015   Fujitsu         Y.Taoka         Create          N/A
 * 01/25/2016   Hitachi         T.Tomita        Update          CSA QC#1029
 *</pre>
 */
public class NLBL3070_NMAL6780 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // There is no process.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        // START 2016/01/25 T.Tomita [QC#1029, MOD]
//        NLBL3070BMsg scrnMsg = (NLBL3070BMsg) bMsg;
//
//        if (!NLBL3070Constant.EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {
//            ZYPEZDItemValueSetter.setValue(scrnMsg.shipToCustCd, scrnMsg.P.no(2).xxPopPrm);
//            ZYPEZDItemValueSetter.setValue(scrnMsg.shipToCustNm, scrnMsg.P.no(3).xxPopPrm);
//        }
//        scrnMsg.setFocusItem(scrnMsg.shipToCustCd);
        return;
        // END 2016/01/25 T.Tomita [QC#1029, MOD]
    }
}
