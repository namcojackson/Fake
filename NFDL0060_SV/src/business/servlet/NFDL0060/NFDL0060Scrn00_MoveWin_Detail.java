/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFDL0060;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/08/22   Hitachi         K.Kojima        Update          QC#13331
 *</pre>
 */
public class NFDL0060Scrn00_MoveWin_Detail extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NFDL0060BMsg scrnMsg = (NFDL0060BMsg) bMsg;
        int selectIdx = getButtonSelectNumber();

        if (scrnMsg.A.no(selectIdx).cltItemTpDescTxt_A1.getValue().equals(scrnMsg.xxScrItem50Txt_TS.getValue())) {
            Object[] params = new Object[3];
            params[0] = scrnMsg.A.no(selectIdx).dsAcctNum_A1;
            params[1] = null;
            params[2] = scrnMsg.A.no(selectIdx).cltTaskPk_A1;
            setArgForSubScreen(params);
        } else {
            Object[] params = new Object[4];
            params[0] = scrnMsg.A.no(selectIdx).dsAcctNum_A1;
            params[1] = null;
            params[2] = null;
            params[3] = scrnMsg.A.no(selectIdx).cltTaskPk_A1;
            setArgForSubScreen(params);
        }
    }
}
