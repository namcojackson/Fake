/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0200;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static business.servlet.NSBL0200.constant.NSBL0200Constant.*;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 *
 * Technician Recommend Popup
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/07/03   Hitachi         Y.Igarashi         Create          N/A
 *</pre>
 */
public class NSBL0200Scrn00_SelectTech extends S21CommonHandler {

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

        NSBL0200BMsg scrnMsg = (NSBL0200BMsg) bMsg;

        Object[] arg = (Object[]) getArgForSubScreen();
        if (arg instanceof Object[]) {
            Object[] params = (Object[]) arg;
            setValue((EZDBStringItem) params[ARGS_TECH_CODE], scrnMsg.A.no(getButtonSelectNumber()).techCd_RS);
        }
    }
}
