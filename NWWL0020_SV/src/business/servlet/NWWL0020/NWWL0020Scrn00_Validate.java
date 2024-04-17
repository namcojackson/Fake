/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWWL0020;

import static business.servlet.NWWL0020.constant.NWWL0020Constant.BIZ_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWWL0020.NWWL0020CMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWWL0020Scrn00_Validate
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/28   Fujitsu         S.Ohki          Create          N/A
 *</pre>
 */
public class NWWL0020Scrn00_Validate extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWWL0020BMsg scrnMsg = (NWWL0020BMsg) bMsg;

        scrnMsg.addCheckItem(scrnMsg.xxNtfySqlTxt);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWWL0020BMsg scrnMsg = (NWWL0020BMsg) bMsg;
        NWWL0020CMsg bizMsg = new NWWL0020CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWWL0020BMsg scrnMsg = (NWWL0020BMsg) bMsg;
        NWWL0020CMsg bizMsg = (NWWL0020CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.addCheckItem(scrnMsg.xxNtfySqlTxt);
        scrnMsg.putErrorScreen();

        scrnMsg.setFocusItem(scrnMsg.xxNtfySqlTxt);
    }
}
