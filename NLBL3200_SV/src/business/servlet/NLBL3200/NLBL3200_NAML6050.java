/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL3200;

import static business.servlet.NLBL3200.constant.NLBL3200Constant.EVENT_NM_CMN_CLOSE;
import static business.servlet.NLBL3200.constant.NLBL3200Constant.TBL_NM_FOR_OTBD_CARR_V;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import business.servlet.NLBL3200.NLBL3200BMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Manage Shipping Orders
 * 
 * Make Error. Don't Use Event.
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/16   CITS            T.Tokutomi      Create          N/A
 *</pre>
 */
public class NLBL3200_NAML6050 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // No Process.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // No Process.

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLBL3200BMsg scrnMsg = (NLBL3200BMsg) bMsg;

        if (!EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {
            if (TBL_NM_FOR_OTBD_CARR_V.equals(scrnMsg.xxTblNm_P1.getValue())) {
                scrnMsg.setFocusItem(scrnMsg.carrCd_H1);
            }
        }
    }
}
