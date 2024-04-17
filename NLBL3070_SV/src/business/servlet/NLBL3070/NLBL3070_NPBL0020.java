/*
 * <pre>Copyright (c) 2021 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL3070;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
/**
 *<pre>
 * Delivery Scheduling / Manage Deliveries
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/24/2021   CITS            A.Marte         Create          QC#58786
 *</pre>
 */
public class NLBL3070_NPBL0020 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // There is no process
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLBL3070BMsg scrnMsg = (NLBL3070BMsg) bMsg;
        scrnMsg.setFocusItem(scrnMsg.trxHdrNum);
    }

}
