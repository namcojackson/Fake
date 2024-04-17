/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL3120;

import static business.servlet.NLBL3120.constant.NLBL3120Constant.EVENT_NM_CMN_CLOSE;
import static business.servlet.NLBL3120.constant.NLBL3120Constant.EVENT_NM_OPENWIN_MODEL;
import static business.servlet.NLBL3120.constant.NLBL3120Constant.EVENT_NM_OPENWIN_SLS_REP;
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
public class NLBL3120_NMAL6050 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // There is no process.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLBL3120BMsg scrnMsg = (NLBL3120BMsg) bMsg;

        if (EVENT_NM_OPENWIN_MODEL.equals(scrnMsg.xxMntEventNm.getValue())) {
            if (!EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.t_MdlNm, scrnMsg.P.no(10).xxPopPrm);
            }
            scrnMsg.setFocusItem(scrnMsg.t_MdlNm);

        } else if (EVENT_NM_OPENWIN_SLS_REP.equals(scrnMsg.xxMntEventNm.getValue())) {
            if (!EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.slsRepOrSlsTeamTocCd, scrnMsg.P.no(9).xxPopPrm);
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPsnFirstMidLastNm_SR, scrnMsg.P.no(10).xxPopPrm);
            }
            scrnMsg.setFocusItem(scrnMsg.slsRepOrSlsTeamTocCd);
        }

    }
}
