/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL3050;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NLBL3050.constant.NLBL3050Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/10/06   CUSA            T.Tokutomi      Create          #9760
 *</pre>
 */
public class NLBL3050_NLBL3100 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // No Process.

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // No Process

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLBL3050BMsg scrnMsg = (NLBL3050BMsg) bMsg;

        if (!NLBL3050Constant.EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {
            // Coordinator Person Code
            ZYPEZDItemValueSetter.setValue(scrnMsg.psnCd_H, scrnMsg.P.no(1).xxPopPrm);
            // Coordinator Person Name
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPsnFirstMidLastNm_H, scrnMsg.P.no(6).xxPopPrm);
        }

        scrnMsg.setFocusItem(scrnMsg.psnCd_H);
    }
}
