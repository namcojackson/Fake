/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0540;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0540.NSAL0540CMsg;
import business.servlet.NSAL0540.common.NSAL0540CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Solution Maintenance
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/21   Hitachi         T.Tomita        Create          QC#6399
 *</pre>
 */
public class NSAL0540_NSAL1240 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return;
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL0540BMsg scrnMsg = (NSAL0540BMsg) bMsg;
        NSAL0540CMsg bizMsg = NSAL0540CommonLogic.createCMsgForSearch();
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSAL0540BMsg scrnMsg = (NSAL0540BMsg) bMsg;
        NSAL0540CMsg bizMsg = (NSAL0540CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NSAL0540CommonLogic.screenControlProcess(this, scrnMsg);
        scrnMsg.setFocusItem(scrnMsg.svcSlnNm);
    }
}
