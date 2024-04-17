/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2720;

import static business.servlet.NMAL2720.constant.NMAL2720Constant.BIZ_ID;
import static business.servlet.NMAL2720.constant.NMAL2720Constant.BTN_CMN_CLOSE;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import business.blap.NMAL2720.NMAL2720CMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL2720_NMAL2530
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/22   Fujitsu         M.Nakamura      Create          N/A
 *</pre>
 */
public class NMAL2720_NMAL2530 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NMAL2720BMsg scrnMsg = (NMAL2720BMsg) bMsg;

        NMAL2720CMsg bizMsg = new NMAL2720CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

        //return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2720BMsg scrnMsg = (NMAL2720BMsg) bMsg;

        if (BTN_CMN_CLOSE.equals(getLastGuard())) {
            return;
        }
        if (!ZYPCommonFunc.hasValue(scrnMsg.P.no(1).xxPopPrm)) {
            return;
        }

        ZYPEZDItemValueSetter.setValue(scrnMsg.orgNm_H, scrnMsg.P.no(1).xxPopPrm);

        scrnMsg.setFocusItem(scrnMsg.orgNm_H);
    }
}
