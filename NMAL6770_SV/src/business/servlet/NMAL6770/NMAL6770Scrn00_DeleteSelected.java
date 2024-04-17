/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6770;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL6770.NMAL6770CMsg;
import business.servlet.NMAL6770.common.NMAL6770CommonLogic;
import business.servlet.NMAL6770.constant.NMAL6770Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/07   SRAA            Y.Chen          Create          QC#7781
 *</pre>
 */
public class NMAL6770Scrn00_DeleteSelected extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NMAL6770BMsg scrnMsg = (NMAL6770BMsg) bMsg;
        NMAL6770CMsg bizMsg = new NMAL6770CMsg();
        bizMsg.setBusinessID("NMAL6770");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NMAL6770BMsg scrnMsg = (NMAL6770BMsg) bMsg;
        NMAL6770CMsg bizMsg  = (NMAL6770CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        
        NMAL6770CommonLogic.initialControlScreen(this, scrnMsg);
        S21TableColorController tblColor = new S21TableColorController(NMAL6770Constant.SCREEN_ID, scrnMsg);
        tblColor.setAlternateRowsBG("B", scrnMsg.B);
    }
}
