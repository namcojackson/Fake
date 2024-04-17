/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1140;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.*;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Supply Watch.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/12   Hitachi         T.Nishimura     Create          N/A
 * 2016/10/21   Hitachi         T.Tomita        Update          QC#15196
 *</pre>
 */
public class NSAL1140_NWAL1130 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        // START 2016/10/21 T.Tomita [QC#15196, ADD]
        NSAL1140BMsg scrnMsg = (NSAL1140BMsg) bMsg;
        if (!"CMN_Close".equals(getLastGuard())) {
            String svcContrBrCd = scrnMsg.X.no(0).xxComnScrColValTxt.getValue();
            if (hasValue(svcContrBrCd)) {
                setValue(scrnMsg.svcContrBrCd, svcContrBrCd);
            }
            scrnMsg.setFocusItem(scrnMsg.svcContrBrCd);
        }
        // END 2016/10/21 T.Tomita [QC#15196, ADD]
    }
}
