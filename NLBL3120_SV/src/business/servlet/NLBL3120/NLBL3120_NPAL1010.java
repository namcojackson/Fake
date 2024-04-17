/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL3120;

import static business.servlet.NLBL3120.constant.NLBL3120Constant.EVENT_NM_CMN_CLOSE;
import static business.servlet.NLBL3120.constant.NLBL3120Constant.EVENT_NM_OPENWIN_LOCINFO_RTLWH;
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
 * 2015/12/04   Fujitsu         S.Yoshida       Create          N/A
 *</pre>
 */
public class NLBL3120_NPAL1010 extends S21CommonHandler {

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

        NLBL3120BMsg scrnMsg = (NLBL3120BMsg) bMsg;

        if (EVENT_NM_OPENWIN_LOCINFO_RTLWH.equals(scrnMsg.xxMntEventNm.getValue())) {
            if (!EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhCd, scrnMsg.P.no(6).xxPopPrm);
                ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhNm, scrnMsg.P.no(7).xxPopPrm);
            }
            scrnMsg.setFocusItem(scrnMsg.rtlWhCd);
        }
    }
}
