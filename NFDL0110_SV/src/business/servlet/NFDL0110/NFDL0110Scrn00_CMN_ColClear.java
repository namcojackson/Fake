/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFDL0110;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFDL0110.NFDL0110CMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Customer Care
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/14   CUSA            K.Lee           Create          N/A
 * 2017/01/18   Fujitsu         S.Fujita        Update          QC#16813
 *</pre>
 */
public class NFDL0110Scrn00_CMN_ColClear extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFDL0110BMsg scrnMsg = (NFDL0110BMsg) bMsg;
        NFDL0110CMsg bizMsg = new NFDL0110CMsg();

        bizMsg.setBusinessID("NFDL0110");
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        // START 2016/01/18 S.Fujita [QC#16813,ADD]
        NFDL0110BMsg scrnMsg = (NFDL0110BMsg) bMsg;
        NFDL0110CMsg bizMsg  = (NFDL0110CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        // END   2016/01/18 S.Fujita [QC#16813,ADD]
    }
}
