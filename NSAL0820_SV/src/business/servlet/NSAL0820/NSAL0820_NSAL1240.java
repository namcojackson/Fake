/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0820;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Actual Counters for Interface
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/03   Hitachi         T.Tomita        Create          QC#3312
 *</pre>
 */
public class NSAL0820_NSAL1240 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSAL0820BMsg scrnMsg = (NSAL0820BMsg) bMsg;

        if ("CMN_Close".equals(getLastGuard())) {
            int index = getButtonSelectNumber();
            setConfigSearchPopUpOutputParam(ctx, scrnMsg, index);
        }
    }

    private void setConfigSearchPopUpOutputParam(EZDApplicationContext ctx, NSAL0820BMsg scrnMsg, int index) {

        if (ZYPCommonFunc.hasValue(scrnMsg.O.no(0).serNum_O)) {
            setValue(scrnMsg.A.no(index).serNum_A, scrnMsg.O.no(0).serNum_O);
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.O.no(0).mdseCd_O)) {
            setValue(scrnMsg.A.no(index).mdseCd_A, scrnMsg.O.no(0).mdseCd_O);
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.O.no(0).svcMachMstrPk_O)) {
            setValue(scrnMsg.A.no(index).svcMachMstrPk_A, scrnMsg.O.no(0).svcMachMstrPk_O);
        }
    }
}
