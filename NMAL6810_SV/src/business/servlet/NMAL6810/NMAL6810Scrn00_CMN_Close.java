/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6810;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Item Master Template Popup
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/17   Hitachi         K.Kishimoto     Create          N/A
 *</pre>
 */
public class NMAL6810Scrn00_CMN_Close extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NMAL6810BMsg scrnMsg = (NMAL6810BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NMAL6810BMsg scrnMsg = (NMAL6810BMsg) bMsg;

        //NMAL6810CMsg bizMsg = new NMAL6810CMsg();
        //bizMsg.setBusinessID("NMAL6810");
        //bizMsg.setFunctionCode("20");
        //EZDMsg.copy(scrnMsg, null, bizMsg, null);

        //return bizMsg;

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        //NMAL6810BMsg scrnMsg = (NMAL6810BMsg) bMsg;
        //NMAL6810CMsg bizMsg  = (NMAL6810CMsg) cMsg;

        //EZDMsg.copy(bizMsg, null, scrnMsg, null);

    }
}
