/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1150;

import static business.servlet.NSAL1150.constant.NSAL1150Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/20   Hitachi         T.Kanasaka      Create          N/A
 *</pre>
 */
public class NSAL1150_NSAL0110 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSAL1150BMsg scrnMsg = (NSAL1150BMsg) bMsg;
        if (!POPUP_CLOSE_EVENT.equals(getLastGuard())) {
            setValue(scrnMsg.condSqlTxt_CO, scrnMsg.xxPopPrm_00);
        }
    }
}
