/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2460;

import static business.servlet.NMAL2460.constant.NMAL2460Constant.BTN_CMN_CLOSE;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/26   Hitachi         O.Okuma         Create          N/A
 *</pre>
 */
public class NMAL2460_NMAL2570 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2460BMsg scrnMsg = (NMAL2460BMsg) bMsg;

        if (BTN_CMN_CLOSE.equals(getLastGuard())) {
            return;
        }
        if (!ZYPCommonFunc.hasValue(scrnMsg.xxPopPrm_01)) {
            return;
        }

        ZYPEZDItemValueSetter.setValue(scrnMsg.psnCd_H, scrnMsg.xxPopPrm_02);
        ZYPEZDItemValueSetter.setValue(scrnMsg.fullPsnNm_H, scrnMsg.xxPopPrm_04);

        scrnMsg.setFocusItem(scrnMsg.fullPsnNm_H);
    }
}
