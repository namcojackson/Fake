/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1740;

import static business.servlet.NWAL1740.constant.NWAL1740Constant.BIZ_ID;
import static business.servlet.NWAL1740.constant.NWAL1740Constant.NWAM8441E;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1740.NWAL1740CMsg;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL1740Scrn00_Add_Mdse
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/25   Fujitsu         M.Suzuki        Create          N/A
 *</pre>
 */
public class NWAL1740Scrn00_Add_Mdse extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1740BMsg scrnMsg = (NWAL1740BMsg) bMsg;
        if (scrnMsg.A.getValidCount() >= scrnMsg.A.length()) {
            scrnMsg.setMessageInfo(NWAM8441E);
            throw new EZDFieldErrorException();
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1740BMsg scrnMsg = (NWAL1740BMsg) bMsg;
        NWAL1740CMsg bizMsg = new NWAL1740CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1740BMsg scrnMsg = (NWAL1740BMsg) bMsg;
        NWAL1740CMsg bizMsg = (NWAL1740CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        scrnMsg.setFocusItem(scrnMsg.A.no(scrnMsg.A.getValidCount() - 1).xxBtnFlg_A);
    }
}
