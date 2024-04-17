/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0020;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * This class no use.
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/06/04   Hitachi        T.Yonekura         Create          N/A
 * 2015/10/19   Hitachi         Y.Tsuchimoto    Update          N/A(No Mark up comment)
 *</pre>
 */
public class NSAL0020Scrn00_OpenWin_Sell_To extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

//        NSAL0020BMsg scrnMsg = (NSAL0020BMsg) bMsg;
//        scrnMsg.addCheckItem(scrnMsg.sellToCustCd);
//        scrnMsg.addCheckItem(scrnMsg.postCd);
//        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

//        NSAL0020BMsg scrnMsg = (NSAL0020BMsg) bMsg;
//
//        scrnMsg.locRoleTpCd_P2.setValue(LOC_ROLE_TP.SELL_TO);
//
//        Object[] params = new Object[6];
//
//        params[0] = scrnMsg.sellToCustCd;
//        params[1] = scrnMsg.locRoleTpCd_P2;
//        params[2] = scrnMsg.locNm_P2;
//        params[3] = scrnMsg.postCd;
//        params[4] = scrnMsg.ctyAddr_P2;
//        params[5] = scrnMsg.stCd_P2;
//
//        setArgForSubScreen(params);

    }
}
