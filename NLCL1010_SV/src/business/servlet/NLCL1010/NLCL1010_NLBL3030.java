/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL1010;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NLCL1010.constant.NLCL1010Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/07/19   Fujitsu         Tozuka          Create          R-WH002
 *</pre>
 */
public class NLCL1010_NLBL3030 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLCL1010BMsg scrnMsg = (NLCL1010BMsg) bMsg;
        int selectedIndex = getButtonSelectNumber();

        if (!NLCL1010Constant.POPUP_CLOSE.equals(getLastGuard())) {
            String message = scrnMsg.P.no(2).xxPopPrm.getValue();
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(selectedIndex).serTrxCmntTxt_A1, message);
            if (ZYPCommonFunc.hasValue(message)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(selectedIndex).xxYesNoCd_A1, ZYPConstant.FLG_ON_Y);
            } else {
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(selectedIndex).xxYesNoCd_A1, ZYPConstant.FLG_OFF_N);
            }
        }

    }
}
