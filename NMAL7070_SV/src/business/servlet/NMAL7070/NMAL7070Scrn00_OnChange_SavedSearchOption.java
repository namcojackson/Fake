/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7070;

import static business.servlet.NMAL7070.constant.NMAL7070Constant.BIZ_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL7070.NMAL7070CMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Supply Agreement Plan Search
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/08   Fujitsu         M.Ohno          Create          N/A
 *</pre>
 */
public class NMAL7070Scrn00_OnChange_SavedSearchOption extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7070BMsg scrnMsg = (NMAL7070BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.srchOptPk);
        scrnMsg.addCheckItem(scrnMsg.srchOptNm);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7070BMsg scrnMsg = (NMAL7070BMsg) bMsg;

        NMAL7070CMsg bizMsg = new NMAL7070CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7070BMsg scrnMsg = (NMAL7070BMsg) bMsg;
        NMAL7070CMsg bizMsg = (NMAL7070CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        scrnMsg.addCheckItem(scrnMsg.srchOptPk);
        scrnMsg.addCheckItem(scrnMsg.srchOptNm);
        scrnMsg.putErrorScreen();

    }
}
