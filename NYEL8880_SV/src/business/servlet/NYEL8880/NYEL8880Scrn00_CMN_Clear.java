/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NYEL8880;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NYEL8880.NYEL8880CMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 2019/01/08   Fujitsu         M.Ugaki         Update          QC#29856
 *</pre>
 */
public class NYEL8880Scrn00_CMN_Clear extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NYEL8880BMsg scrnMsg = (NYEL8880BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NYEL8880BMsg scrnMsg = (NYEL8880BMsg) bMsg;

        //NYEL8880CMsg bizMsg = new NYEL8880CMsg();
        //bizMsg.setBusinessID("NYEL8880");
        //bizMsg.setFunctionCode("20");
        //EZDMsg.copy(scrnMsg, null, bizMsg, null);

        //return bizMsg;

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NYEL8880BMsg scrnMsg = (NYEL8880BMsg) bMsg;
// QC#29856 MOD START 2019/01/08
//        NYEL8880CMsg bizMsg  = (NYEL8880CMsg) cMsg;
//
//        bizMsg.clear();
//
//        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.clear();
// QC#29856 MOD END   2019/01/08

    }
}
