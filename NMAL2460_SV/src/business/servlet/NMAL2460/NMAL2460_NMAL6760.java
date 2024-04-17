/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2460;

import static business.servlet.NMAL2460.constant.NMAL2460Constant.BTN_CMN_CLOSE;
import static business.servlet.NMAL2460.constant.NMAL2460Constant.BTN_OPENWIN_ACCTSRCH;
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
public class NMAL2460_NMAL6760 extends S21CommonHandler {

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

        try {
            if (BTN_CMN_CLOSE.equals(getLastGuard())) {
                return;
            }
            if (!ZYPCommonFunc.hasValue(scrnMsg.xxPopPrm_01)) {
                return;
            }

            if (BTN_OPENWIN_ACCTSRCH.equals(scrnMsg.xxScrEventNm.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.dsAcctNum_H, scrnMsg.xxPopPrm_01);
                ZYPEZDItemValueSetter.setValue(scrnMsg.dsAcctNm_H, scrnMsg.xxPopPrm_02);
                scrnMsg.setFocusItem(scrnMsg.dsAcctNum_H);
            } else {
                ZYPEZDItemValueSetter.setValue(scrnMsg.locNum_H, scrnMsg.xxPopPrm_03);
                scrnMsg.setFocusItem(scrnMsg.locNum_H);
            }
        } finally {
            scrnMsg.xxScrEventNm.clear();
        }
    }
}
