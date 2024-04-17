/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1640;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1640.NWAL1640CMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/20   SRAA            Y.Chen          Create          QC#4505
 *</pre>
 */
public class NWAL1640Scrn00_GetAddress extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NWAL1640BMsg scrnMsg = (NWAL1640BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.splyPostCd);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NWAL1640BMsg scrnMsg = (NWAL1640BMsg) bMsg;
        NWAL1640CMsg bizMsg = new NWAL1640CMsg();
        bizMsg.setBusinessID("NWAL1640");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NWAL1640BMsg scrnMsg = (NWAL1640BMsg) bMsg;
        NWAL1640CMsg bizMsg  = (NWAL1640CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        
        scrnMsg.addCheckItem(scrnMsg.splyCtyAddr);
        scrnMsg.addCheckItem(scrnMsg.splyStCd);
        scrnMsg.addCheckItem(scrnMsg.splyPostCd);
        scrnMsg.putErrorScreen();
        
        scrnMsg.setFocusItem(scrnMsg.splyPostCd);
    }
}
