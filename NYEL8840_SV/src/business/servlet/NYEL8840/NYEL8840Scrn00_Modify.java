/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NYEL8840;

//import static business.servlet.NYEL8840.constant.NYEL8840Constant.BIZ_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NYEL8840Scrn00_Modify
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/06/26   Fujitsu         M.Yamada        Create          N/A
 *</pre>
 */
public class NYEL8840Scrn00_Modify extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NYEL8840BMsg scrnMsg = (NYEL8840BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NYEL8840BMsg scrnMsg = (NYEL8840BMsg) bMsg;
        //
        //NYEL8840CMsg bizMsg = new NYEL8840CMsg();
        //bizMsg.setBusinessID(BIZ_ID);
        //bizMsg.setFunctionCode("20");
        //EZDMsg.copy(scrnMsg, null, bizMsg, null);
        //
        //return bizMsg;

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        //NYEL8840BMsg scrnMsg = (NYEL8840BMsg) bMsg;
        //NYEL8840CMsg bizMsg  = (NYEL8840CMsg) cMsg;
        //
        //EZDMsg.copy(bizMsg, null, scrnMsg, null);

    }
}
