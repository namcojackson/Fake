/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NSBL0420;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import static business.servlet.NSBL0420.common.NSBL0420CommonLogic.addCheckItem;
import static business.servlet.NSBL0420.constant.NSBL0420Constant.*;
import business.blap.NSBL0420.NSBL0420CMsg;
import business.servlet.NSBL0420.common.NSBL0420CommonLogic;


import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Mods Parts Entry
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/18   Hitachi         T.Tsuchida      Create          N/A
 *</pre>
 */
public class NSBL0420Scrn00_Add extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSBL0420BMsg scrnMsg = (NSBL0420BMsg) bMsg;
        addCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSBL0420BMsg scrnMsg = (NSBL0420BMsg) bMsg;

        NSBL0420CMsg bizMsg = new NSBL0420CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSBL0420BMsg scrnMsg = (NSBL0420BMsg) bMsg;
        NSBL0420CMsg bizMsg = (NSBL0420CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NSBL0420CommonLogic.initialControlScreen(this, scrnMsg);
        addCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();
        if (EZDMessageInfo.MSGTYPE_ERROR  == scrnMsg.getMessageType()) {
            scrnMsg.setFocusItem(scrnMsg.mdseCd_TF);
            throw new EZDFieldErrorException();
        }
        scrnMsg.setFocusItem(scrnMsg.A.no(scrnMsg.A.getValidCount() - 1).svcModQty_A);
    }
}
