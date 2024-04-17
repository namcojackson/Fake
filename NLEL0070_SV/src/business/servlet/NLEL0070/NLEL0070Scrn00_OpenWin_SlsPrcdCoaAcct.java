/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLEL0070;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NLEL0070.NLEL0070CMsg;
//import business.servlet.NLEL0070.constant.NLEL0070Constant;

import business.servlet.NLEL0070.common.NLEL0070CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/29/2016   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
public class NLEL0070Scrn00_OpenWin_SlsPrcdCoaAcct extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLEL0070BMsg scrnMsg = (NLEL0070BMsg) bMsg;
        scrnMsg.xxScrEventNm.setValue(ctx.getEventName());
        setArgForSubScreen(NLEL0070CommonLogic.getNMAL6050Param(scrnMsg, scrnMsg.slsPrcdCoaAcctCd_M1.getValue()));
    }
}
