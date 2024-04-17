/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1190;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import business.servlet.NSAL1190.common.NSAL1190CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/15   Hitachi         O.Okuma         Create          N/A
 *</pre>
 */
public class NSAL1190Scrn00_OnChangeMtrIdxCd extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL1190BMsg scrnMsg = (NSAL1190BMsg) bMsg;

        // event line
        final int eventLine = getButtonSelectNumber();
        scrnMsg.xxNum_EV.setValue(eventLine);
        NSAL1190CommonLogic.changeMtrIdxCd(this, scrnMsg);
        scrnMsg.setFocusItem(scrnMsg.A.no(eventLine).mtrIdxCd_A);
    }
}
