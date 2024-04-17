/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0920;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NSAL0920.NSAL0920CMsg;
//import business.servlet.NSAL0920.constant.NSAL0920Constant;

import business.blap.NSAL0920.NSAL0920CMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Contract Billing Search
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/03   Hitachi         K.Kasai         Create          N/A
 *</pre>
 */
public class NSAL0920Scrn00_CMN_ColSave extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0920BMsg scrnMsg = (NSAL0920BMsg) bMsg;

        NSAL0920CMsg bizMsg = new NSAL0920CMsg();
        bizMsg.setBusinessID("NSAL0920");
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        //NSAL0920BMsg scrnMsg = (NSAL0920BMsg) bMsg;
        //NSAL0920CMsg bizMsg  = (NSAL0920CMsg) cMsg;

        //EZDMsg.copy(bizMsg, null, scrnMsg, null);

    }
}
