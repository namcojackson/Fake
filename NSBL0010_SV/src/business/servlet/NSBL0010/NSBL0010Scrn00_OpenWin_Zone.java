/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0010;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/04/29   SRA             E.Inada         Create          N/A
 *</pre>
 */
public class NSBL0010Scrn00_OpenWin_Zone extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSBL0010BMsg scrnMsg = (NSBL0010BMsg) bMsg;

        // TODO Delete after Popup would be created. ---
        if (!ZYPCommonFunc.hasValue(scrnMsg.getMessageCode())) {
            scrnMsg.setMessageInfo("NFAM0043E", new String[] {"[No setup. Please add program.]" });
            throw new EZDFieldErrorException();
        }
        // ----------------------------------------------

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NSBL0010BMsg scrnMsg = (NSBL0010BMsg) bMsg;

        //NSBL0010CMsg bizMsg = new NSBL0010CMsg();
        //bizMsg.setBusinessID("NSBL0010");
        //bizMsg.setFunctionCode("20");
        //EZDMsg.copy(scrnMsg, null, bizMsg, null);

        //return bizMsg;

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        //NSBL0010BMsg scrnMsg = (NSBL0010BMsg) bMsg;
        //NSBL0010CMsg bizMsg  = (NSBL0010CMsg) cMsg;

        //EZDMsg.copy(bizMsg, null, scrnMsg, null);

    }
}
