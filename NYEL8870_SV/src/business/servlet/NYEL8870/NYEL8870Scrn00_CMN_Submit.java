/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NYEL8870;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NYEL8870.NYEL8870CMsg;
import business.servlet.NYEL8870.common.NYEL8870CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
public class NYEL8870Scrn00_CMN_Submit extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NYEL8870BMsg scrnMsg = (NYEL8870BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NYEL8870BMsg scrnMsg = (NYEL8870BMsg) bMsg;

        NYEL8870CMsg bizMsg = new NYEL8870CMsg();
        bizMsg.setBusinessID("NYEL8870");
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NYEL8870BMsg scrnMsg = (NYEL8870BMsg) bMsg;
        NYEL8870CMsg bizMsg  = (NYEL8870CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NYEL8870CommonLogic.initRowCtrlProp(scrnMsg);
        NYEL8870CommonLogic.initCmnBtnProp(this);

        NYEL8870CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.A, "A");

        if (scrnMsg.getMessageCode().endsWith("E")) {
            return;
        }

    }
}
