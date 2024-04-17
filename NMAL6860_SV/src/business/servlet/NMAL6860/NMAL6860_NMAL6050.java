/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6860;

import static business.servlet.NMAL6860.constant.NMAL6860Constant.EVENT_NM_CMN_CLOSE;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NMAL6860.NMAL6860CMsg;
//import business.servlet.NMAL6860.constant.NMAL6860Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/08   CITS            T.Gotoda        Create          N/A
 *</pre>
 */
public class NMAL6860_NMAL6050 extends S21CommonHandler {

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

        ZYPEZDItemValueSetter.setValue( scrnMsg.coaAfflCd_H1, scrnMsg.xxCondCd_Z1);

        scrnMsg.setFocusItem(scrnMsg.coaAfflCd_H1);
    }
}
