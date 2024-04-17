/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0380;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NSAL0380.common.NSAL0380CommonLogic;
import business.servlet.NSAL0380.constant.NSAL0380Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/15   Hitachi         O.Okuma         Create          QC3029
 * 2016/09/02   Hitachi         T.Kanasaka      Update          QC#11170
 *</pre>
 */
public class NSAL0380Scrn00_OpenWin_SrchSerThru extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0380BMsg scrnMsg = (NSAL0380BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.serNum_HF);
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0380BMsg scrnMsg = (NSAL0380BMsg) bMsg;
        NSAL0380CommonLogic.setTargetItem(scrnMsg, NSAL0380Constant.EVENT_NM_SER_NUM_THRU);
        // START 2016/09/02 T.Kanasaka [QC#11170, MOD]
//        setArgForSubScreen(NSAL0380CommonLogic.setConfigSearchPopUpInputParam(ctx, scrnMsg));
        Object[] params = NSAL0380CommonLogic.setConfigSearchPopUpInputParam(ctx, scrnMsg);
        params[NSAL0380Constant.PARAM_INDEX_2] = scrnMsg.serNum_HT;
        setArgForSubScreen(params);
        // END 2016/09/02 T.Kanasaka [QC#11170, MOD]
    }
}
