/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0400;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/05/24   Hitachi         U.Kim           Create          QC#22393
 *</pre>
 */
public class NSBL0400_NSAL1240 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSBL0400BMsg scrnMsg = (NSBL0400BMsg) bMsg;

        if ("CMN_Close".equals(getLastGuard())) {
            if (scrnMsg.O.getValidCount() > 0) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.serNum, scrnMsg.O.no(0).serNum_O.getValue());
            }
        }
        scrnMsg.setFocusItem(scrnMsg.serNum);
    }
}
