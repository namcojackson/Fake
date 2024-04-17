/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2570;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL2570.NMAL2570CMsg;
import business.servlet.NMAL2570.common.NMAL2570CommonLogic;
import business.servlet.NMAL2570.constant.NMAL2570Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/07   SRAA            Y.Chen          Create          QC#7781
 *</pre>
 */
public class NMAL2570Scrn00_AddSelected extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NMAL2570BMsg scrnMsg = (NMAL2570BMsg) bMsg;
        NMAL2570CMsg bizMsg = new NMAL2570CMsg();
        bizMsg.setBusinessID("NMAL2570");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NMAL2570BMsg scrnMsg = (NMAL2570BMsg) bMsg;
        NMAL2570CMsg bizMsg  = (NMAL2570CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        
        for(int i=0; i<scrnMsg.A.getValidCount(); i++){
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_A1);
        }
        scrnMsg.putErrorScreen();
        
        NMAL2570CommonLogic.initialControlScreen(this, scrnMsg);
        S21TableColorController tblColor = new S21TableColorController(NMAL2570Constant.SCREEN_ID, scrnMsg);
        tblColor.setAlternateRowsBG("B", scrnMsg.B);
        
        if (scrnMsg.B.getValidCount() > 0) {
            scrnMsg.setFocusItem(scrnMsg.B.no(scrnMsg.B.getValidCount() - 1).xxChkBox_B1);
        }
    }
}
