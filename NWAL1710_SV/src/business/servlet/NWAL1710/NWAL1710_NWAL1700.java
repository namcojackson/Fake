/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1710;

import static business.servlet.NWAL1710.constant.NWAL1710Constant.BIZ_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import business.blap.NWAL1710.NWAL1710CMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL1710_NWAL1700
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/22   Fujitsu         M.Suzuki        Create          N/A
 *</pre>
 */
public class NWAL1710_NWAL1700 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NWAL1710BMsg scrnMsg = (NWAL1710BMsg) bMsg;

        NWAL1710CMsg bizMsg = new NWAL1710CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

        //return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1710BMsg scrnMsg = (NWAL1710BMsg) bMsg;
        NWAL1710CMsg bizMsg  = (NWAL1710CMsg) cMsg;

        //EZDMsg.copy(bizMsg, null, scrnMsg, null);

        //scrnMsg.XXX.setValue(scrnMsg.P.no(AA).xxPopPrm.getValue());

        //scrnMsg.setFocusItem(scrnMsg.XXX);
    }
}
