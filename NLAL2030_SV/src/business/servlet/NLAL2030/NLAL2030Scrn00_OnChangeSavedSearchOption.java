/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLAL2030;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLAL2030.NLAL2030CMsg;
import business.servlet.NLAL2030.constant.NLAL2030Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NLAL2030Scrn00_OnChangeSavedSearchOption
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/04   Fujitsu         Y.Taoka         Create          N/A
 *</pre>
 */
public class NLAL2030Scrn00_OnChangeSavedSearchOption extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return;
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLAL2030BMsg scrnMsg = (NLAL2030BMsg) bMsg;

        NLAL2030CMsg bizMsg = new NLAL2030CMsg();
        bizMsg.setBusinessID(NLAL2030Constant.BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLAL2030BMsg scrnMsg = (NLAL2030BMsg) bMsg;
        NLAL2030CMsg bizMsg  = (NLAL2030CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        scrnMsg.addCheckItem(scrnMsg.srchOptPk_S);
        scrnMsg.putErrorScreen();
    }
}
