/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFDL0030;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFDL0030.NFDL0030CMsg;
import business.servlet.NFDL0030.common.NFDL0030CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 2016/05/09   Fujitsu         S.Fujita        Update          QC#7021
 *</pre>
 */
public class NFDL0030Scrn00_CMN_Clear extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NFDL0030BMsg scrnMsg = (NFDL0030BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // START 2016/05/09 S.Fujita [QC#7021,MOD]
        NFDL0030BMsg scrnMsg = (NFDL0030BMsg) bMsg;

        NFDL0030CMsg bizMsg = new NFDL0030CMsg();
        bizMsg.setBusinessID("NFDL0030");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

//        return null;
        // END 2016/05/09 S.Fujita [QC#7021,MOD]
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        // START 2016/05/09 S.Fujita [QC#7021,MOD]
        NFDL0030BMsg scrnMsg = (NFDL0030BMsg) bMsg;
        NFDL0030CMsg bizMsg = (NFDL0030CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NFDL0030CommonLogic.clearScreen(scrnMsg);
        NFDL0030CommonLogic.initialize(this, scrnMsg, getUserProfileService());
        // END 2016/05/09 S.Fujita [QC#7021,MOD]

    }
}
