/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL0090;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NLBL0090.common.NLBL0090CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/07/22   Fujitsu         Mori            Create          N/A
 *</pre>
 */
public class NLBL0090Scrn00_OpenWin_SellTo extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLBL0090BMsg scrnMsg = (NLBL0090BMsg) bMsg;

        scrnMsg.addCheckItem(scrnMsg.sellToCustCd_H2);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NLBL0090BMsg scrnMsg = (NLBL0090BMsg) bMsg;

        Object[] params = NLBL0090CommonLogic.setParamForShipToCustPopup(scrnMsg, true);
        setArgForSubScreen(params);

        scrnMsg.xxScrEventNm.setValue(getClass().getSimpleName());
    }

}