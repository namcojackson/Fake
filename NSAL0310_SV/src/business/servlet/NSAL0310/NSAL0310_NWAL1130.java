/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0310;

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
 * 2015/10/08   Hitachi         T.Tomita        Create          N/A
 *</pre>
 */
public class NSAL0310_NWAL1130 extends S21CommonHandler {

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

        NSAL0310BMsg scrnMsg = (NSAL0310BMsg) bMsg;
        if (!"CMN_Close".equals(getLastGuard())) {
            String mdlNm = scrnMsg.B.no(1).xxComnScrColValTxt.getValue();
            if (ZYPCommonFunc.hasValue(mdlNm)) {
                setValue(scrnMsg.mdlNm, mdlNm);
            }
        }
        scrnMsg.setFocusItem(scrnMsg.mdlNm);
    }
}
