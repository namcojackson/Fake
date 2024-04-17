/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0990;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NSAL0990.common.NSAL0990CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/07/30   Hitachi         T.Tomita        Create          QC#14307(Solution#020)
 *</pre>
 */
public class NSAL0990Scrn00_OpenWin_SpecialInstruction extends S21CommonHandler {

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
        NSAL0990BMsg scrnMsg = (NSAL0990BMsg) bMsg;

        Object[] params = NSAL0990CommonLogic.getParamNMAL3300(scrnMsg);
        setArgForSubScreen(params);
    }
}
