/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.ZZIL0050;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/12/18   Fujitsu         C.Ogaki         Create          N/A
 *</pre>
 */
public class ZZIL0050Scrn00_Select extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        ZZIL0050BMsg scrnMsg = (ZZIL0050BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.intfcId);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        ZZIL0050BMsg scrnMsg = (ZZIL0050BMsg) bMsg;

        // Get selected result index
        int selected = getButtonSelectNumber();

        // Populating return values
        Serializable arg = getArgForSubScreen();

        if (arg != null) {
            if (arg instanceof Object[]) {
                Object[] params = (Object[]) arg;
                EZDBStringItem templVal = (EZDBStringItem) params[0];
                templVal.setValue(scrnMsg.A.no(selected).intfcId_A.getValue());
            }
        }
    }
}
