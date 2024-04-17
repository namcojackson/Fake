/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL0280;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NLCL0280.common.NLCL0280CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NLCL0280Scrn00_OpenWin_FlipItem Cross Refference Item Popup
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/06   CITS            K.Ogino         Create          N/A
 *</pre>
 */
public class NLCL0280Scrn00_OpenWin_FlipItem extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLCL0280BMsg scrnMsg = (NLCL0280BMsg) bMsg;

        // set popup parameter
        Object[] params = NLCL0280CommonLogic.setCrossReffItemPopupParam(scrnMsg, getGlobalCompanyCode(), getButtonSelectNumber());

        // send popup parameter
        setArgForSubScreen(params);
    }
}
