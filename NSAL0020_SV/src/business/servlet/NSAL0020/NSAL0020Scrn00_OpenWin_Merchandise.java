/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0020;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import business.servlet.NSAL0020.common.NSAL0020CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/06/04   Hitachi        T.Yonekura       Create           N/A
 * 2016/03/02   Hitachi        K.Kasai          Update           CSA QC#3586
 *</pre>
 */
public class NSAL0020Scrn00_OpenWin_Merchandise extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0020BMsg scrnMsg = (NSAL0020BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.mdseCd);
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0020BMsg scrnMsg = (NSAL0020BMsg) bMsg;

        // mod start 2016/03/02 CSA Defect#3586
        NSAL0020CommonLogic.clearPopupParam(scrnMsg);

        Object[] params = new Object[7];

        params[0] = scrnMsg.mdseCd;
        params[1] = scrnMsg.xxPopPrm_01;
        params[2] = scrnMsg.xxPopPrm_02;
        params[3] = scrnMsg.xxPopPrm_03;
        params[4] = scrnMsg.xxPopPrm_04;
        params[5] = scrnMsg.xxPopPrm_05;
        params[6] = scrnMsg.xxPopPrm_06;
        // mod end 2016/03/02 CSA Defect#3586

        setArgForSubScreen(params);

    }
}
