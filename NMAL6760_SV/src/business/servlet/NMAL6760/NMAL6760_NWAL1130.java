/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6760;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL6760_NWAL1130
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/13   Fujitsu         C.Tanaka        Create          CSA
 *</pre>
 */
public class NMAL6760_NWAL1130 extends S21CommonHandler {

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

        if ("CMN_Close".equals(getLastGuard())) {
            return;
        }

        NMAL6760BMsg scrnMsg = (NMAL6760BMsg) bMsg;

        ZYPEZDItemValueSetter.setValue(scrnMsg.dsAcctNum_01, scrnMsg.I.no(0).xxComnScrColValTxt.getValue());
    }
}
