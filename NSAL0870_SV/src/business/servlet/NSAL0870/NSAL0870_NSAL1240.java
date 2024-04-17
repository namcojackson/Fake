/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0870;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Meter Interface Status Inquiry
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/17   Hitachi         T.Mizuki        Create          N/A
 *</pre>
 */
public class NSAL0870_NSAL1240 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0870BMsg scrnMsg = (NSAL0870BMsg) bMsg;

        if ("CMN_Close".equals(getLastGuard())) {
            int index = getButtonSelectNumber();
            setConfigSearchPopUpOutputParam(ctx, scrnMsg, index);
        }
    }

    private void setConfigSearchPopUpOutputParam(EZDApplicationContext ctx, NSAL0870BMsg scrnMsg, int index) {

        if (ZYPCommonFunc.hasValue(scrnMsg.O.no(0).svcMachMstrPk_O)) {
            scrnMsg.serNum_01.clear();
        }
        if (ZYPCommonFunc.hasValue(scrnMsg.O.no(0).serNum_O)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.serNum_01, scrnMsg.O.no(0).serNum_O);
        }

    }
}
