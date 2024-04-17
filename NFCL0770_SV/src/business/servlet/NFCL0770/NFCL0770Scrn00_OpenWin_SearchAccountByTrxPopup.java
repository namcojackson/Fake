/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL0770;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/11/02   Fujitsu         S.Takami        Create          QC#28289
 *</pre>
 */
public class NFCL0770Scrn00_OpenWin_SearchAccountByTrxPopup extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL0770BMsg scrnMsg = (NFCL0770BMsg) bMsg;

        scrnMsg.M.clear();
        scrnMsg.M.no(0).xxPopPrm.clear();

        Object[] param = new Object[3];
        param[0] = scrnMsg.M.no(0).xxPopPrm;
        param[1] = scrnMsg.M.no(1).xxPopPrm;
        param[2] = scrnMsg.M.no(2).xxPopPrm;

        setArgForSubScreen(param);
    }
}
