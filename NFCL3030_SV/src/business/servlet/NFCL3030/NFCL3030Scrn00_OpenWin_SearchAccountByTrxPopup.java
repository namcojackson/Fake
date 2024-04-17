/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL3030;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 2024/02/23   Hitachi         S.Ikariya       Update          QC#63452
 *</pre>
 */
public class NFCL3030Scrn00_OpenWin_SearchAccountByTrxPopup extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL3030BMsg scrnMsg = (NFCL3030BMsg) bMsg;

        scrnMsg.M.clear();
        scrnMsg.M.no(0).xxPopPrm.clear();

        // START 2024/02/23 S.Ikariya [QC#63452, MOD]
//        Object[] param = new Object[3];
        Object[] param = new Object[4];
        param[0] = scrnMsg.M.no(0).xxPopPrm;
        param[1] = scrnMsg.M.no(1).xxPopPrm;
        param[2] = scrnMsg.M.no(2).xxPopPrm;
        param[3] = scrnMsg.M.no(3).xxPopPrm;
        // END 2024/02/23 S.Ikariya [QC#63452, MOD]

        setArgForSubScreen(param);
    }
}
