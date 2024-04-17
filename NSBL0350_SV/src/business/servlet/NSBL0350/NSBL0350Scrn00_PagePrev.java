/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0350;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSBL0350.NSBL0350CMsg;
import static business.servlet.NSBL0350.common.NSBL0350CommonLogic.initialControlScreen;
import static business.servlet.NSBL0350.constant.NSBL0350Constant.BUSINESS_ID;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;


/** 
 *<pre>
 * Group Level Report
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/22   Hitachi         T.Tsuchida      Create          N/A
 *</pre>
 */
public class NSBL0350Scrn00_PagePrev extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return;
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSBL0350BMsg scrnMsg = (NSBL0350BMsg) bMsg;

        NSBL0350CMsg bizMsg = new NSBL0350CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSBL0350BMsg scrnMsg = (NSBL0350BMsg) bMsg;
        NSBL0350CMsg bizMsg  = (NSBL0350CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        initialControlScreen(this, scrnMsg);
    }
}
