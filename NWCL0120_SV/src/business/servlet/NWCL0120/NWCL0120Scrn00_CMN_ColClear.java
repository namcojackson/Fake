/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWCL0120;

import static business.servlet.NWCL0120.constant.NWCL0120Constant.BIZ_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWCL0120.NWCL0120CMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWCL0120Scrn00_CMN_ColClear
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/17   Fujitsu         S.Ohki          Create          N/A
 *</pre>
 */
public class NWCL0120Scrn00_CMN_ColClear extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWCL0120BMsg scrnMsg = (NWCL0120BMsg) bMsg;

        NWCL0120CMsg bizMsg = new NWCL0120CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWCL0120BMsg scrnMsg = (NWCL0120BMsg) bMsg;
        NWCL0120CMsg bizMsg = (NWCL0120CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
    }
}