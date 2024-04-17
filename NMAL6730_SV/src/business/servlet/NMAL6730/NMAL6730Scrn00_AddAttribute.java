/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6730;

import static business.servlet.NMAL6730.constant.NMAL6730Constant.BUSINESS_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL6730.NMAL6730CMsg;
import business.servlet.NMAL6730.common.NMAL6730CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/04   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
public class NMAL6730Scrn00_AddAttribute extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NMAL6730BMsg scrnMsg = (NMAL6730BMsg) bMsg;

        NMAL6730CommonLogic.clearMandatoryError(scrnMsg);
        for (int i = 0; i < scrnMsg.K.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.K.no(i).bllgAttrbNm_K1);
            scrnMsg.addCheckItem(scrnMsg.K.no(i).bllgAttrbValTxt_K1);
        }
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL6730BMsg scrnMsg = (NMAL6730BMsg) bMsg;

        NMAL6730CMsg bizMsg = new NMAL6730CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6730BMsg scrnMsg = (NMAL6730BMsg) bMsg;
        NMAL6730CMsg bizMsg = (NMAL6730CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NMAL6730CommonLogic.initialControlScreen(getUserProfileService(), this, scrnMsg, bizMsg.getUserID());
        NMAL6730CommonLogic.setBgColor(scrnMsg);
    }
}