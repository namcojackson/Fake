/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0400;

import static business.servlet.NSBL0400.constant.NSBL0400Constant.*;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import business.blap.NSBL0400.NSBL0400CMsg;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/14   Hitachi         M.Gotou         Create          N/A
 * 2016/07/12   Hitachi         O.Okuma         Update          QC#11685
 *</pre>
 */
public class NSBL0400Scrn00_PageNext extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSBL0400BMsg scrnMsg = (NSBL0400BMsg) bMsg;

        NSBL0400CMsg bizMsg = new NSBL0400CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(EZD_FUNC_CD_INQ);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSBL0400BMsg scrnMsg = (NSBL0400BMsg) bMsg;
        NSBL0400CMsg bizMsg  = (NSBL0400CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        scrnMsg.setFocusItem(scrnMsg.svcModYr);
    }
}