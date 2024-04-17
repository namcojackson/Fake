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
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 2015/10/19   Hitachi         Y.Tsuchimoto    Update          N/A(No Mark up comment)
 *</pre>
 */
public class NSAL0020Scrn00_OpenWin_ReturnWH extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

//        NSAL0020BMsg scrnMsg = (NSAL0020BMsg) bMsg;
//        scrnMsg.addCheckItem(scrnMsg.rtrnToWhCd);
//        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
//
//        NSAL0020BMsg scrnMsg = (NSAL0020BMsg) bMsg;
//
//        scrnMsg.invtyLocTpCd_P4.setValue(LOC_TP.WAREHOUSE);
//        scrnMsg.invtyLocNm_P4.clear();
//
//        Object[] params = new Object[3];
//
//        params[0] = scrnMsg.invtyLocTpCd_P4;
//        params[1] = scrnMsg.rtrnToWhCd;
//        params[2] = scrnMsg.invtyLocNm_P4;
//        
//        scrnMsg.xxScrEventNm.setValue(getClass().getSimpleName());
//
//        setArgForSubScreen(params);

    }
}
