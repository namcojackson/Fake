/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NYEL8820;

import static business.servlet.NYEL8820.constant.NYEL8820Constant.BIZ_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import business.blap.NYEL8820.NYEL8820CMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NYEL8820_XXXL0000
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/08/26   Fujitsu         Q09079          Create          N/A
 *</pre>
 */
public class NYEL8820_XXXL0000 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NYEL8820BMsg scrnMsg = (NYEL8820BMsg) bMsg;

        NYEL8820CMsg bizMsg = new NYEL8820CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NYEL8820BMsg scrnMsg = (NYEL8820BMsg) bMsg;
        NYEL8820CMsg bizMsg  = (NYEL8820CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        //scrnMsg.XXX.setValue(scrnMsg.P.no(AA).xxPopPrm.getValue());

        //scrnMsg.setFocusItem(scrnMsg.XXX);
    }
}
