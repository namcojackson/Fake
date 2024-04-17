/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0620;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Contract Search
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/23   Hitachi         T.Tsuchida      Create          N/A
 * 2016/11/22   Hitachi         K.Kojima        Update          QC#16168
 *</pre>
 */
public class NSAL0620_NSAL0030 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return;
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        // START 2016/11/22 K.Kojima [QC#16168,DEL]
        // NSAL0620BMsg scrnMsg = (NSAL0620BMsg) bMsg;
        //
        // if (!NSAL0620CommonLogic.isClosedEvent(getLastGuard())) {
        // scrnMsg.setFocusItem(scrnMsg.serNum_H);
        // scrnMsg.serNum_H.setValue(scrnMsg.serNum_PO.getValue());
        // }
        // END 2016/11/22 K.Kojima [QC#16168,DEL]
    }
}
