/**
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6860;

import static business.servlet.NMAL6860.constant.NMAL6860Constant.EVENT_NM_CMN_CLOSE;
import static business.servlet.NMAL6860.constant.NMAL6860Constant.TAB_NM_GENERAL;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/08   CITS            T.Gotoda        Create          N/A
 *</pre>
 */
public class NMAL6860_NMAL6760 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // Do nothing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // Do nothing.
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        if (EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {
            return;
        }

        NMAL6860BMsg scrnMsg = (NMAL6860BMsg) bMsg;

        if (TAB_NM_GENERAL.equals(scrnMsg.xxDplyTab.getValue())) {
            // sets the returned value.
            ZYPEZDItemValueSetter.setValue(scrnMsg.dsAcctNum_H1, scrnMsg.xxPopPrm_CA);
            // focus on Customer Number.
            scrnMsg.setFocusItem(scrnMsg.dsAcctNum_H1);
        } else {
            // sets the returned value.
            ZYPEZDItemValueSetter.setValue(scrnMsg.locNum_H2, scrnMsg.xxPopPrm_CC);
            ZYPEZDItemValueSetter.setValue(scrnMsg.billToCustCd_H2, scrnMsg.xxPopPrm_CP);
            // focus on Customer Location.
            scrnMsg.setFocusItem(scrnMsg.locNum_H2);
        }
    }
}