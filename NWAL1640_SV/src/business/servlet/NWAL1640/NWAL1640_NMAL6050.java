/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1640;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL1640_NMAL6050
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/02   Fujitsu         C.Yokoi         Create          N/A
 *</pre>
 */
public class NWAL1640_NMAL6050 extends S21CommonHandler {

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

        NWAL1640BMsg scrnMsg = (NWAL1640BMsg) bMsg;

        if (ZYPCommonFunc.hasValue(scrnMsg.P.no(9).xxPopPrm_P)) {
            scrnMsg.splyStCd.setValue(scrnMsg.P.no(9).xxPopPrm_P.getValue());
        }
        if (ZYPCommonFunc.hasValue(scrnMsg.P.no(10).xxPopPrm_P)) {
            scrnMsg.stNm.setValue(scrnMsg.P.no(10).xxPopPrm_P.getValue());
        }

        // Set focus.
        scrnMsg.setFocusItem(scrnMsg.splyStCd);
    }
}
