/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0920;

import static business.servlet.NSAL0920.constant.NSAL0920Constant.*;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NSAL0920.common.NSAL0920CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/10/18   Hitachi         N.Arai          Create          QC#15216
 * 2017/01/23   Hitachi         K.Kitachi       Update          QC#17219
 *</pre>
 */
public class NSAL0920Scrn00_OpenWin_Branch extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0920BMsg scrnMsg = (NSAL0920BMsg) bMsg;
        // START 2017/01/23 K.Kitachi [QC#17219, MOD]
        scrnMsg.xxScrEventNm.setValue(OPENWIN_BRANCH);
        // END 2017/01/23 K.Kitachi [QC#17219, MOD]

        // set param
        Object[] scrnParams = NSAL0920CommonLogic.setParamBranch(scrnMsg);
        setArgForSubScreen(scrnParams);

    }
}
