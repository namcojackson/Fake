/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0810;

import static business.servlet.NSAL0810.constant.NSAL0810Constant.*;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/18   Hitachi         Y.Tsuchimoto    Create          N/A
 *</pre>
 */
public class NSAL0810_NMAL6760 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return;
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0810BMsg scrnMsg = (NSAL0810BMsg) bMsg;
        getArgForSubScreen();

        if (!"CMN_Close".equals(getLastGuard())) {
            int index = getButtonSelectNumber();
            if (SELECT_POPUP_CUST.equals(scrnMsg.xxPopPrm_SE.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).dsAcctNum_A, scrnMsg.xxPopPrm_00);
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).dsAcctNm_AC, scrnMsg.xxPopPrm_01);
            } else if (SELECT_POPUP_BILL.equals(scrnMsg.xxPopPrm_SE.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).billToCustCd_A, scrnMsg.xxPopPrm_15);
            } else if (SELECT_POPUP_LEASE.equals(scrnMsg.xxPopPrm_SE.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).leaseCmpyCd_A, scrnMsg.xxPopPrm_15);
            }
        }
    }
}
