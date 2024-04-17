/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1220;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;

import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Contract Branch Revenue Distribution
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/14   Hitachi         T.Tsuchida      Create          N/A
 *</pre>
 */
public class NSAL1220_NMAL2550 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
   }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL1220BMsg scrnMsg = (NSAL1220BMsg) bMsg;

        int index = getButtonSelectNumber();

        if ("CMN_Close".equals(getLastGuard())) {
            scrnMsg.setFocusItem(scrnMsg.A.no(index).coaBrCd_A);
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).coaBrCd_A, scrnMsg.xxPopPrm_3);
        }
    }
}