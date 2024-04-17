/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6730;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NMAL6730.NMAL6730CMsg;
//import business.servlet.NMAL6730.constant.NMAL6730Constant;

import business.blap.NMAL6730.NMAL6730CMsg;
import business.servlet.NMAL6730.common.NMAL6730CommonLogic;
import business.servlet.NMAL6730.constant.NMAL6730Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/06   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
public class NMAL6730Scrn00_CMN_Reset extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NMAL6730BMsg scrnMsg = (NMAL6730BMsg) bMsg;
        NMAL6730CMsg bizMsg = new NMAL6730CMsg();
        bizMsg.setBusinessID(NMAL6730Constant.BUSINESS_ID);
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
        scrnMsg.setFocusItem(scrnMsg.ccyCd_P1);
    }
}
