/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL3090;

import static business.servlet.NMAL6820.constant.NMAL6820Constant.EVENT_NM_CMN_CLOSE;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NLBL3090 Call Coordinator Assignment Setup
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/18/2015   CITS       Yasushi Nomura        Create          N/A
 *</pre>
 */
public class NLBL3090_NMAL6050 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // There is no processing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // There is no processing.
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        if (!EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {
            NLBL3090BMsg scrnMsg = (NLBL3090BMsg) bMsg;
            int selectIdx = getButtonSelectNumber();
            ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(selectIdx).stCd_B, scrnMsg.xxCondCd_P1);
        }

    }
}
