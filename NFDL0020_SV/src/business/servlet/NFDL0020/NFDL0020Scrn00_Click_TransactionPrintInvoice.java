/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFDL0020;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Collection Detail Screen
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/29/2016   CSAI            K.Lee           Create          Initial
 *</pre>
 */
public class NFDL0020Scrn00_Click_TransactionPrintInvoice extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFDL0020BMsg scrnMsg = (NFDL0020BMsg) bMsg;
        Object[] params = setPopupParameter(scrnMsg);
        setArgForSubScreen(params);
    }

    private Object[] setPopupParameter(NFDL0020BMsg scrnMsg) {

        scrnMsg.xxScrEventNm.setValue("Click_TransactionPrintInvoice");
        Object[] params = new Object[2];
        params[0] = scrnMsg.dsAcctNum_H;
        params[1] = scrnMsg.billToCustCd_H;
        return params;
    }
}
