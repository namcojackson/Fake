/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0840;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/14   Hitachi         Y.Takeno        Create          N/A
 *</pre>
 */
public class NSAL0840_NMAL6800 extends S21CommonHandler {


    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0840BMsg scrnMsg = (NSAL0840BMsg) bMsg;
        getArgForSubScreen();

        if (!"CMN_Close".equals(getLastGuard())) {
            int index = getButtonSelectNumber();
            setValue(scrnMsg.A.no(index).mdseCd_A, scrnMsg.xxPopPrm_00);
        }
    }
}
