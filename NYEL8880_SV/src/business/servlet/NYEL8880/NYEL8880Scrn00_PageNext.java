/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NYEL8880;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NYEL8880.NYEL8880CMsg;
import business.servlet.NYEL8880.common.NYEL8880CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
public class NYEL8880Scrn00_PageNext extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NYEL8880BMsg scrnMsg = (NYEL8880BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NYEL8880BMsg scrnMsg = (NYEL8880BMsg) bMsg;

        NYEL8880CMsg bizMsg = new NYEL8880CMsg();
        bizMsg.setBusinessID("NYEL8880");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NYEL8880BMsg scrnMsg = (NYEL8880BMsg) bMsg;
        NYEL8880CMsg bizMsg  = (NYEL8880CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

//        NYEL8880CommonLogic.initRowCtrlProp(scrnMsg);
//        NYEL8880CommonLogic.initCmnBtnProp(this);
//
//        NYEL8880CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.A, "A");
//
//        if (scrnMsg.getMessageCode().endsWith("E")) {
//            return;
//        }

    }
}
