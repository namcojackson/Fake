/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL0140;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL0140.NWAL0140CMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/20   SRAA            Y.Chen          Create          QC#4505
 *</pre>
 */
public class NWAL0140Scrn00_GetAddress extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NWAL0140BMsg scrnMsg = (NWAL0140BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.postCd);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NWAL0140BMsg scrnMsg = (NWAL0140BMsg) bMsg;
        NWAL0140CMsg bizMsg = new NWAL0140CMsg();
        bizMsg.setBusinessID("NWAL0140");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NWAL0140BMsg scrnMsg = (NWAL0140BMsg) bMsg;
        NWAL0140CMsg bizMsg  = (NWAL0140CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        
        scrnMsg.addCheckItem(scrnMsg.ctyAddr);
        scrnMsg.addCheckItem(scrnMsg.stCd);
        scrnMsg.addCheckItem(scrnMsg.postCd);
        scrnMsg.addCheckItem(scrnMsg.cntyNm);
        scrnMsg.putErrorScreen();
        
        scrnMsg.setFocusItem(scrnMsg.postCd);
    }
}
