/*
 * <pre>Copyright (c) 2012 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6050;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL6050.NMAL6050CMsg;
import business.servlet.NMAL6050.constant.NMAL6050Constant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * NMAL6050 P&L Product Structure Pop Up
 *
 * Date         Company         Name            Create/Update   Defect No 
 * ----------------------------------------------------------------------
 * 09/05/2012   Fujitsu         H.Mizutani      Update          N/A 
 *</pre>
 */
public class NMAL6050Scrn00_PageNext extends S21CommonHandler implements NMAL6050Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // No Operation
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL6050BMsg scrnMsg = (NMAL6050BMsg) bMsg;

        // set values to items of paging for next page parent page
        scrnMsg.A.clear();
        scrnMsg.A.setValidCount(0);
        scrnMsg.xxPageShowFromNum_10.setValue(scrnMsg.xxPageShowToNum_10.getValueInt());
        scrnMsg.xxPageShowToNum_10.clear();

        NMAL6050CMsg bizMsg = new NMAL6050CMsg();
        bizMsg.setBusinessID("NMAL6050");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NMAL6050BMsg scrnMsg = (NMAL6050BMsg) bMsg;
        NMAL6050CMsg bizMsg = (NMAL6050CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
    }

}
