/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1520;

import static business.servlet.NWAL1520.constant.NWAL1520Constant.SCRN_ID_00;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1520.NWAL1520CMsg;
import business.servlet.NWAL1520.common.NWAL1520CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/30   Fujitsu         A.Suda          Create          N/A
 *</pre>
 */
public class NWAL1520Scrn00_CMN_Reset extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1520BMsg scrnMsg = (NWAL1520BMsg) bMsg;

        NWAL1520CMsg bizMsg = new NWAL1520CMsg();
        bizMsg.setBusinessID("NWAL1520");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1520BMsg scrnMsg = (NWAL1520BMsg) bMsg;
        NWAL1520CMsg bizMsg  = (NWAL1520CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.clearAllGUIAttribute(SCRN_ID_00);
        NWAL1520CommonLogic.initCmnBtnProp(this);
        NWAL1520CommonLogic.setInputProtected(this, scrnMsg);
        NWAL1520CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.V, "V_Right");
        NWAL1520CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.L, "A_Right");
        NWAL1520CommonLogic.setInputProtectedforCheckBox(this, scrnMsg);
    }
}
