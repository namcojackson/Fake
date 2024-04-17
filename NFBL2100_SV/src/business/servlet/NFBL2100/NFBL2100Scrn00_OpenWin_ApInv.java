/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFBL2100;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NFBL2100 Lease Buyout Approve List
 * Function Name : NFBL2100Scrn00_OpenWin_ApInv
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/14/2017   CITS            K.Ogino         Create          QC#22345
 *</pre>
 */
public class NFBL2100Scrn00_OpenWin_ApInv extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // There is no processing.

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // There is no processing.
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFBL2100BMsg scrnMsg = (NFBL2100BMsg) bMsg;
        int selectIdx = getButtonSelectNumber();

        Object[] params = new Object[3];
        params[0] = scrnMsg.A.no(selectIdx).vndCd_A;
        params[1] = scrnMsg.A.no(selectIdx).apInvNum_A;
        scrnMsg.xxPopPrm.setValue("00");
        params[2] = scrnMsg.xxPopPrm;

        setArgForSubScreen(params);

    }
}
