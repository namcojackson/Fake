/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1820;

import static business.servlet.NWAL1820.constant.NWAL1820Constant.BIZ_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1820.NWAL1820CMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL1820Scrn00_CMN_ColClear
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/15   Fujitsu         Y.Taoka         Create          N/A
 *</pre>
 */
public class NWAL1820Scrn00_CMN_ColClear extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
        return;
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1820BMsg scrnMsg = (NWAL1820BMsg) bMsg;

        NWAL1820CMsg bizMsg = new NWAL1820CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1820BMsg scrnMsg = (NWAL1820BMsg) bMsg;
        NWAL1820CMsg bizMsg  = (NWAL1820CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

    }
}
