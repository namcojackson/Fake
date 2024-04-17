/*
 * <pre>Copyright (c) 2018 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL2620;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;

import parts.servletcommon.EZDApplicationContext;

import business.servlet.NFCL2620.common.NFCL2620CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/09/14   Fujitsu         T.Ogura         Create          QC#25091
 *</pre>
 */
public class NFCL2620Scrn00_OpenWin_ApInv extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // Do nothing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL2620BMsg scrnMsg = (NFCL2620BMsg) bMsg;
        NFCL2620CommonLogic.clearPopUpParam(scrnMsg);
        int selectIdx = getButtonSelectNumber();

        Object[] params = new Object[3];
        params[0] = scrnMsg.A.no(selectIdx).vndCd;
        params[1] = scrnMsg.A.no(selectIdx).apVndInvNum;
        scrnMsg.xxPopPrm_P0.setValue("00");
        params[2] = scrnMsg.xxPopPrm_P0;

        setArgForSubScreen(params);
    }
}
