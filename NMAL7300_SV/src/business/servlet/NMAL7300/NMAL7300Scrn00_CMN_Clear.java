/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7300;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL7300.NMAL7300CMsg;
import business.servlet.NMAL7300.common.NMAL7300CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/11/10   Fujitsu         Y.Kanefusa      Create          N/A
 *</pre>
 */
public class NMAL7300Scrn00_CMN_Clear extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NMAL7300BMsg scrnMsg = (NMAL7300BMsg) bMsg;
        Serializable arg = getArgForSubScreen();
        NMAL7300CommonLogic.setInputParam(scrnMsg, (Object[]) arg);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NMAL7300BMsg scrnMsg = (NMAL7300BMsg) bMsg;
        NMAL7300CMsg bizMsg = new NMAL7300CMsg();
        bizMsg.setBusinessID("NMAL7300");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7300BMsg scrnMsg = (NMAL7300BMsg) bMsg;
        NMAL7300CMsg bizMsg  = (NMAL7300CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NMAL7300CommonLogic.initCmnBtnProp(this);
        NMAL7300CommonLogic.setHeaderProtect(scrnMsg);
        NMAL7300CommonLogic.setDetailProtect(scrnMsg);

    }
}
