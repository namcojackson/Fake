/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL0740;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Write-Off Request Creation
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/29   Fujitsu         T.Tanaka        Create          N/A
 * 2016/07/08   Hitachi         K.Kojima        Update          QC#8784
 *</pre>
 */
public class NFCL0740Scrn00_Click_SetCustomerName2 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // START 2016/07/08 K.Kojima [QC#8784,DEL]
        // NFCL0740BMsg scrnMsg = (NFCL0740BMsg) bMsg;
        //
        // scrnMsg.addCheckItem(scrnMsg.dsAcctNum_H2);
        // scrnMsg.putErrorScreen();
        // END 2016/07/08 K.Kojima [QC#8784,DEL]
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // START 2016/07/08 K.Kojima [QC#8784,DEL]
        // NFCL0740BMsg scrnMsg = (NFCL0740BMsg) bMsg;
        // scrnMsg.dsAcctNm_H2.clear();
        //
        // if (!ZYPCommonFunc.hasValue(scrnMsg.dsAcctNum_H2)) {
        // return null;
        // }
        //
        // NFCL0740CMsg bizMsg = new NFCL0740CMsg();
        // bizMsg.setBusinessID("NFCL0740");
        // bizMsg.setFunctionCode("20");
        // EZDMsg.copy(scrnMsg, null, bizMsg, null);
        //
        // return bizMsg;
        // END 2016/07/08 K.Kojima [QC#8784,DEL]
        // START 2016/07/08 K.Kojima [QC#8784,ADD]
        return null;
        // END 2016/07/08 K.Kojima [QC#8784,ADD]
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        // START 2016/07/08 K.Kojima [QC#8784,DEL]
        // NFCL0740BMsg scrnMsg = (NFCL0740BMsg) bMsg;
        // NFCL0740CMsg bizMsg = (NFCL0740CMsg) cMsg;
        //
        // if (!ZYPCommonFunc.hasValue(scrnMsg.dsAcctNum_H2)) {
        // return;
        // }
        //
        // EZDMsg.copy(bizMsg, null, scrnMsg, null);
        //
        // scrnMsg.addCheckItem(scrnMsg.dsAcctNum_H2);
        // scrnMsg.putErrorScreen();
        // scrnMsg.setFocusItem(scrnMsg.dsAcctNum_H2);
        // END 2016/07/08 K.Kojima [QC#8784,DEL]
    }
}
