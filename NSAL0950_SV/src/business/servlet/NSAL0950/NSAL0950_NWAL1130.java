/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0950;

import static business.servlet.NSAL0950.constant.NSAL0950Constant.SELECT_POPUP_DETAIL;
import static business.servlet.NSAL0950.constant.NSAL0950Constant.SELECT_POPUP_SEARCH;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/14   Hitachi         Y.Tsuchimoto    Create          N/A
 *</pre>
 */
public class NSAL0950_NWAL1130 extends S21CommonHandler {

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

        NSAL0950BMsg scrnMsg = (NSAL0950BMsg) bMsg;
        getArgForSubScreen();

        if (!"CMN_Close".equals(getLastGuard())) {
            if (SELECT_POPUP_SEARCH.equals(scrnMsg.xxPopPrm_SE.getValue())) {
                setValue(scrnMsg.vldCmptTxt_S, scrnMsg.X.no(1).xxComnScrColValTxt);
                scrnMsg.setFocusItem(scrnMsg.vldCmptTxt_S);
            } else if (SELECT_POPUP_DETAIL.equals(scrnMsg.xxPopPrm_SE.getValue())) {
                setValue(scrnMsg.vldCmptTxt_D, scrnMsg.X.no(1).xxComnScrColValTxt);
                scrnMsg.setFocusItem(scrnMsg.vldCmptTxt_D);
            }
        }
    }
}
