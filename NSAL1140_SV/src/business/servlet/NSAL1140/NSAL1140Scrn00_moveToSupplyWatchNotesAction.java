/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1140;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import static business.servlet.NSAL1140.constant.NSAL1140Constant.*;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Supply Watch.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/12   Hitachi         T.Nishimura     Create          N/A
 * 2016/03/22   Hitachi         A.Kohinata      Update          QC#5827
 *</pre>
 */
public class NSAL1140Scrn00_moveToSupplyWatchNotesAction extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL1140BMsg scrnMsg = (NSAL1140BMsg) bMsg;
        int rowNum = getButtonSelectNumber();
        Object[] params = new Object[MOVE_TO_SUPPLY_WATCH_NOTES_ACTION_PARAMS];
        int i = 0;
        params[i++] = scrnMsg.A.no(rowNum).dsContrPk;
        params[i++] = scrnMsg.A.no(rowNum).shipToCustAcctCd_A;
        params[i++] = scrnMsg.A.no(rowNum).svcPgmMdseCd;
        setArgForSubScreen(params);
    }
}
