/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0860;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/06/2016   Hitachi         Y.Osawa         Create          N/A
 *</pre>
 */
public class NSAL0860_NSAL1240 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0860BMsg scrnMsg = (NSAL0860BMsg) bMsg;

        if ("CMN_Close".equals(getLastGuard())) {
            setConfigSearchPopUpOutputParam(ctx, scrnMsg);
        }
    }

    private void setConfigSearchPopUpOutputParam(EZDApplicationContext ctx, NSAL0860BMsg scrnMsg) {

        if (ZYPCommonFunc.hasValue(scrnMsg.O.no(0).serNum_O)) {
            setValue(scrnMsg.serNum, scrnMsg.O.no(0).serNum_O);
        }
    }
}
