/*
 * <Pre>Copyright (c) 2010 Canon USA Inc. All rights reserved.</Pre>
 */
/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2010/07/13   Fujitsu         N.Yamamoto      Create          4486
 *</pre>
 */
package business.servlet.NLBL3060;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLBL3060.NLBL3060CMsg;
import business.servlet.NLBL3060.common.NLBL3060CommonLogic;
import business.servlet.NLBL3060.constant.NLBL3060Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/16   CSAI            K.Lee           Create          
 *</pre>
 */
public class NLBL3060Scrn00_PageNext extends S21CommonHandler implements NLBL3060Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLBL3060BMsg scrnMsg = (NLBL3060BMsg) bMsg;
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLBL3060BMsg scrnMsg = (NLBL3060BMsg) bMsg;

        NLBL3060CMsg bizMsg = new NLBL3060CMsg();
        bizMsg.setBusinessID("NLBL3060");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLBL3060BMsg scrnMsg = (NLBL3060BMsg) bMsg;
        NLBL3060CMsg bizMsg = (NLBL3060CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NLBL3060CommonLogic.setControlScreen(this, scrnMsg, bizMsg.getUserID(), null);
        scrnMsg.putErrorScreen();
    }

}
