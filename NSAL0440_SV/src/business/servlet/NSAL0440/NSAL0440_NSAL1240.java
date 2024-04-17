/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0440;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/17   Hitachi         K.Kasai         Create          QC#2815
 *</pre>
 */
public class NSAL0440_NSAL1240 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0440BMsg scrnMsg = (NSAL0440BMsg) bMsg;

        if ("CMN_Close".equals(getLastGuard())) {
            int index = getButtonSelectNumber();
            setConfigSearchPopUpOutputParam(ctx, scrnMsg, index);
        }
    }

    private void setConfigSearchPopUpOutputParam(EZDApplicationContext ctx, NSAL0440BMsg scrnMsg, int index) {

        for (int i = 0; i < scrnMsg.O.getValidCount() ; i++) {
            setValue(scrnMsg.Q.no(i).serNum_Q, scrnMsg.O.no(i).serNum_O);
        }
        if (scrnMsg.O.getValidCount() == 1) {
            setValue(scrnMsg.serNum, scrnMsg.O.no(0).serNum_O);
        }
        scrnMsg.Q.setValidCount(scrnMsg.O.getValidCount());
    }
}
