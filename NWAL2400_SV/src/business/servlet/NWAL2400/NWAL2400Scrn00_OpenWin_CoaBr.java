/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2400;

import static business.servlet.NWAL2400.constant.NWAL2400Constant.EVENT_NM_COA_BR;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NWAL2400.common.NWAL2400CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/08/02   CITS            S.Tanikawa      Create          QC#10537
 *</pre>
 */
public class NWAL2400Scrn00_OpenWin_CoaBr extends S21CommonHandler {

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
        NWAL2400BMsg scrnMsg = (NWAL2400BMsg) bMsg;

        int selectNum = getButtonSelectNumber();

        // Making of subscreen delivery information
        Object[] params = NWAL2400CommonLogic.getParamNWAL1130ForCoaBr(scrnMsg, selectNum);

        // Subscreen transition
        setArgForSubScreen(params);

        scrnMsg.eventNm.setValue(EVENT_NM_COA_BR);
    }
}
