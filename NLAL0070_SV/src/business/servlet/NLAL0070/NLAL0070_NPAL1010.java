/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLAL0070;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/03/2013   Hitachi         T.Tomita        Create          QC1209
 *</pre>
 */
public class NLAL0070_NPAL1010 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // no process
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NLAL0070BMsg scrnMsg = (NLAL0070BMsg) bMsg;
        if (ZYPCommonFunc.hasValue(scrnMsg.P.no(1).xxPopPrm_H1)) {
            scrnMsg.invtyLocCd_P1.setValue(scrnMsg.P.no(0).xxPopPrm_H1.getValue());
            scrnMsg.invtyLocNm_P1.setValue(scrnMsg.P.no(1).xxPopPrm_H1.getValue());
        }
        // Set focus.
        scrnMsg.setFocusItem(scrnMsg.invtyLocCd_P1);
    }
}
