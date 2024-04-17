/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0300;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NSAL0300.common.NSAL0300CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/01   Hitachi         T.Aoyagi        Create          QC#11261
 * 2018/01/11   Hitachi         T.Tomita        Update          QC#18552
 *</pre>
 */
public class NSAL0300Scrn00_OpenWin_ServiceProgram extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0300BMsg scrnMsg = (NSAL0300BMsg) bMsg;

        int selectIdx = getButtonSelectNumber();
        // Mod Start 2018/01/11 QC#18552
        String mdseCd = null;
        String mdseDescShortTxt = null;
        if (selectIdx > -1) {
            mdseCd = scrnMsg.B.no(selectIdx).svcPgmMdseCd_B.getValue();
//            mdseDescShortTxt = scrnMsg.B.no(selectIdx).mdseDescShortTxt_B.getValue();
        } else {
            mdseDescShortTxt = scrnMsg.mdseDescShortTxt_SP.getValue();
        }
        setArgForSubScreen(NSAL0300CommonLogic.setSvcPgmCommonPopUpParam(scrnMsg, getGlobalCompanyCode(), mdseCd, mdseDescShortTxt));
        // Mod End 2018/01/11 QC#18552
    }
}
