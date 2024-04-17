/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFAL0050;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NFAL0050.NFAL0050CMsg;
//import business.servlet.NFAL0050.constant.NFAL0050Constant;

import business.blap.NFAL0050.NFAL0050CMsg;
import business.servlet.NFAL0050.common.NFAL0050CommonLogic;
import business.servlet.NFAL0050.constant.NFAL0050Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
public class NFAL0050Scrn00_CatgSearchBtn extends S21CommonHandler implements NFAL0050Constant{

    /** Singleton instance. */
    private NFAL0050CommonLogic common = new NFAL0050CommonLogic();
    
    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NFAL0050BMsg scrnMsg = (NFAL0050BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFAL0050BMsg scrnMsg = (NFAL0050BMsg) bMsg;

        NFAL0050CMsg bizMsg = new NFAL0050CMsg();
        bizMsg.setBusinessID("NFAL0050");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFAL0050BMsg scrnMsg = (NFAL0050BMsg) bMsg;
        NFAL0050CMsg bizMsg = (NFAL0050CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.addCheckItem(scrnMsg.ajeId);
        // Show error message for invalid Journal Category Code
        scrnMsg.addCheckItem(scrnMsg.jrnlCatgCd);
        // Program end if error has been detected above
        scrnMsg.putErrorScreen();

        NFAL0050CommonLogic.setInputProtectedTextField(scrnMsg);
        common.afterSearch(ctx, scrnMsg, this);

    }
}
