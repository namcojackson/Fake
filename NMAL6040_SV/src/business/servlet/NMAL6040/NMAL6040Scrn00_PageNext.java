/*
 * <pre>Copyright (c) 2012 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6040;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL6040.NMAL6040CMsg;
import business.servlet.NMAL6040.constant.NMAL6040Constant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * NMAL6040 P&L Product Structure Pop Up
 *
 * Date         Company         Name            Create/Update   Defect No 
 * ----------------------------------------------------------------------
 * 09/05/2012   Fujitsu         H.Mizutani      Update          N/A 
 *</pre>
 */
public class NMAL6040Scrn00_PageNext extends S21CommonHandler implements NMAL6040Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // No Operation
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL6040BMsg scrnMsg = (NMAL6040BMsg) bMsg;

        // set values to items of paging for next page parent page
        scrnMsg.A.clear();
        scrnMsg.A.setValidCount(0);
        scrnMsg.xxPageShowFromNum.setValue(scrnMsg.xxPageShowToNum.getValueInt());
        scrnMsg.xxPageShowToNum.clear();

        NMAL6040CMsg bizMsg = new NMAL6040CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NMAL6040BMsg scrnMsg = (NMAL6040BMsg) bMsg;
        NMAL6040CMsg bizMsg = (NMAL6040CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
    }

}
