/**
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6850;

import static business.servlet.NMAL6850.constant.NMAL6850Constant.EVENT_NM_CMN_CLOSE;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2020/02/28   Fujitsu         C.Hara          Create          QC#55971
 *</pre>
 */

public class NMAL6850_NWAL1130 extends S21CommonHandler {

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

        NMAL6850BMsg scrnMsg = (NMAL6850BMsg) bMsg;

        ZYPEZDItemValueSetter.setValue(scrnMsg.prntVndTpDescTxt_H, scrnMsg.Z.no(1).xxComnScrColValTxt_Z);
        scrnMsg.setFocusItem(scrnMsg.prntVndTpDescTxt_H);
    }

}
