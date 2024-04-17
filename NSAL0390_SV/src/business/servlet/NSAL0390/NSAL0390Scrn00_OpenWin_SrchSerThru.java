/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0390;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import business.servlet.NSAL0390.common.NSAL0390CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/18   Hitachi         A.Kohinata      Create          QC#4212
 *</pre>
 */
public class NSAL0390Scrn00_OpenWin_SrchSerThru extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0390BMsg scrnMsg = (NSAL0390BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.serNum_HT);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0390BMsg scrnMsg = (NSAL0390BMsg) bMsg;
        scrnMsg.xxScrEventNm.setValue("OpenWin_SrchSerThru");

        Object[] params = NSAL0390CommonLogic.setConfigSearchPopUpInputParam(scrnMsg);
        setArgForSubScreen(params);
    }
}
