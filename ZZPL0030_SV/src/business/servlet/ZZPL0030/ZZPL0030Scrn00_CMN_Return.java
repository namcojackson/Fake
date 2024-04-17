/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.ZZPL0030;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   Fujitsu         T.Tsuji         Create          N/A
 *</pre>
 */
public class ZZPL0030Scrn00_CMN_Return extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //ZZPL0030BMsg scrnMsg = (ZZPL0030BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //ZZPL0030BMsg scrnMsg = (ZZPL0030BMsg) bMsg;

        //ZZPL0030CMsg bizMsg = new ZZPL0030CMsg();
        //bizMsg.setBusinessID("ZZPL0030");
        //bizMsg.setFunctionCode("20");
        //EZDMsg.copy(scrnMsg, null, bizMsg, null);

        //return bizMsg;

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        //ZZPL0030BMsg scrnMsg = (ZZPL0030BMsg) bMsg;
        //ZZPL0030CMsg bizMsg  = (ZZPL0030CMsg) cMsg;

        //EZDMsg.copy(bizMsg, null, scrnMsg, null);

    }
}
