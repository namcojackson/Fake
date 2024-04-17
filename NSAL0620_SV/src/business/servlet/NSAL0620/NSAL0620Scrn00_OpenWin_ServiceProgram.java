/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0620;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NSAL0620.common.NSAL0620CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/01   Hitachi         T.Aoyagi        Update          QC#11261
 *</pre>
 */
public class NSAL0620Scrn00_OpenWin_ServiceProgram extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0620BMsg scrnMsg = (NSAL0620BMsg) bMsg;
        String mdseDescShortTxt =  scrnMsg.mdseDescShortTxt_H.getValue();
        setArgForSubScreen(NSAL0620CommonLogic.setSvcPgmCommonPopUpParam(scrnMsg, getGlobalCompanyCode(), mdseDescShortTxt));
    }
}
