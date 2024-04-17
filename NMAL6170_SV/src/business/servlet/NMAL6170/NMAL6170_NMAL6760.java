/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6170;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/07/05   Hitachi         J.Kim           Create          N/A
 *</pre>
 */
public class NMAL6170_NMAL6760 extends S21CommonHandler {

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

        NMAL6170BMsg scrnMsg = (NMAL6170BMsg) bMsg;
        if (ZYPCommonFunc.hasValue(scrnMsg.P.no(0).xxPopPrm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.dsAcctNum_F, scrnMsg.P.no(0).xxPopPrm);
        }

        scrnMsg.setFocusItem(scrnMsg.dsAcctNum_F);
    }
}