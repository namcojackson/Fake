/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2800;

import static business.servlet.NMAL2800.constant.NMAL2800Constant.BTN_CMN_CLS;
import static business.servlet.NMAL2800.constant.NMAL2800Constant.POPUP_EMP;
import static business.servlet.NMAL2800.constant.NMAL2800Constant.POPUP_OWNER;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL2800_NMAL2570
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/16   Fujitsu         C.Tanaka        Create          N/A
 *</pre>
 */
public class NMAL2800_NMAL2570 extends S21CommonHandler {

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

        if (POPUP_EMP.equals(scrnMsg.P.no(10).xxPopPrm.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.befPsnNum_H1, scrnMsg.P.no(1).xxPopPrm);
            scrnMsg.setFocusItem(scrnMsg.befPsnNum_H1);
        } else if (POPUP_OWNER.equals(scrnMsg.P.no(10).xxPopPrm.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrItem61Txt_H1, scrnMsg.P.no(3).xxPopPrm);
            scrnMsg.setFocusItem(scrnMsg.xxScrItem61Txt_H1);
        }
    }
}
