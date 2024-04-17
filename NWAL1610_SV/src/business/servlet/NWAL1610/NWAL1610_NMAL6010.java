/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1610;

import static business.servlet.NWAL1610.constant.NWAL1610Constant.BTN_CMN_CLOSE;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL1610_NMAL6010
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/07   Fujitsu         Yokoi           Create          N/A
 *</pre>
 */
public class NWAL1610_NMAL6010 extends S21CommonHandler {

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
        NWAL1610BMsg scrnMsg = (NWAL1610BMsg) bMsg;

        if (!BTN_CMN_CLOSE.equals(getLastGuard())) {
            if (ZYPCommonFunc.hasValue(scrnMsg.P.no(0).xxPopPrm_P)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.billToCustLocCd, scrnMsg.P.no(1).xxPopPrm_P);
            }
            if (ZYPCommonFunc.hasValue(scrnMsg.P.no(6).xxPopPrm_P)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.shipToCustLocCd, scrnMsg.P.no(6).xxPopPrm_P);
            }
        }
        scrnMsg.setFocusItem(scrnMsg.shipToCustLocCd);
    }
}
