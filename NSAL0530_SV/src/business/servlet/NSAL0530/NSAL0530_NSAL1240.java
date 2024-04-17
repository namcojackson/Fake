/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0530;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/11/22   Hitachi         K.Kojima        Create          QC#16168
 *</pre>
 */
public class NSAL0530_NSAL1240 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSAL0530BMsg scrnMsg = (NSAL0530BMsg) bMsg;
        if (scrnMsg.O.getValidCount() > 0) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.serNum_H, scrnMsg.O.no(0).serNum_O.getValue());
        }
        scrnMsg.setFocusItem(scrnMsg.serNum_H);
    }
}
