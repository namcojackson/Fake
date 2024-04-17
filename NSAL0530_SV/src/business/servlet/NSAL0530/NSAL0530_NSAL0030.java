/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0530;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Solution Search
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/05/12   Hitachi         T.Tomita        Create          N/A
 * 2016/11/22   Hitachi         K.Kojima        Update          QC#16168
 *</pre>
 */
public class NSAL0530_NSAL0030 extends S21CommonHandler {

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
        // START 2016/11/22 K.Kojima [QC#16168,DEL]
        // NSAL0530BMsg scrnMsg = (NSAL0530BMsg) bMsg;
        //
        // if (!CMN_CLOSE.equals(getLastGuard())) {
        // setValue(scrnMsg.serNum_H, scrnMsg.serNum_PO);
        // }
        // scrnMsg.setFocusItem(scrnMsg.serNum_H);
        // END 2016/11/22 K.Kojima [QC#16168,DEL]
    }
}
