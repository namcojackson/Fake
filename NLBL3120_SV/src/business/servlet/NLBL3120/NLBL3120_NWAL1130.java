/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL3120;

import static business.servlet.NLBL3120.constant.NLBL3120Constant.EVENT_NM_CMN_CLOSE;
import static business.servlet.NLBL3120.constant.NLBL3120Constant.EVENT_NM_OPENWIN_CARR_APLY;
import static business.servlet.NLBL3120.constant.NLBL3120Constant.EVENT_NM_OPENWIN_CARR_INFO;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Workload Balancing and Monitor
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/03   Fujitsu         S.Yoshida       Create          N/A
 *</pre>
 */
public class NLBL3120_NWAL1130 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // There is no process
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLBL3120BMsg scrnMsg = (NLBL3120BMsg) bMsg;

        if (EVENT_NM_OPENWIN_CARR_APLY.equals(scrnMsg.xxMntEventNm.getValue())) {
            if (!EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.carrCd_BT, scrnMsg.P.no(0).xxComnScrColValTxt);
                ZYPEZDItemValueSetter.setValue(scrnMsg.carrNm_BT, scrnMsg.P.no(1).xxComnScrColValTxt);
            }
            scrnMsg.setFocusItem(scrnMsg.carrNm_BT);

        } else if (EVENT_NM_OPENWIN_CARR_INFO.equals(scrnMsg.xxMntEventNm.getValue())) {
            if (!EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {
                int index = getButtonSelectNumber();
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).carrCd_A1, scrnMsg.P.no(0).xxComnScrColValTxt);
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).locNm_A1, scrnMsg.P.no(1).xxComnScrColValTxt);
                scrnMsg.setFocusItem(scrnMsg.A.no(index).carrCd_A1);
            }
            scrnMsg.setFocusItem(scrnMsg.A.no(0).carrCd_A1);
        }

    }
}
