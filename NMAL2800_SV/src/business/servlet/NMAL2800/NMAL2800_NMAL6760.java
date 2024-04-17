/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2800;

import static business.servlet.NMAL2800.constant.NMAL2800Constant.BTN_CMN_CLS;
import static business.servlet.NMAL2800.constant.NMAL2800Constant.POPUP_LOC;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL2800_NMAL6760
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/16   Fujitsu         C.Tanaka        Create          N/A
 *</pre>
 */
public class NMAL2800_NMAL6760 extends S21CommonHandler {

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

        if (BTN_CMN_CLS.equals(getLastGuard())) {
            return;
        }

        NMAL2800BMsg scrnMsg = (NMAL2800BMsg) bMsg;

        if (POPUP_LOC.equals(scrnMsg.P.no(10).xxPopPrm.getValue())) {
            int index = getButtonSelectNumber();
            NMAL2800_ABMsg aBMsg = scrnMsg.A.no(index);
            if (!ZYPCommonFunc.hasValue(aBMsg.prosRvwStsCd_AB)) {
                ZYPEZDItemValueSetter.setValue(aBMsg.aftLocNum_A1, scrnMsg.P.no(2).xxPopPrm);
                scrnMsg.setFocusItem(aBMsg.aftLocNum_A1);
            }
        }
    }
}
