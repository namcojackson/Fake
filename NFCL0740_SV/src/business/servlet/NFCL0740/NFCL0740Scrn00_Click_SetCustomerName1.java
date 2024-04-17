/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL0740;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFCL0740.NFCL0740CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Write-Off Request Creation
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/29   Fujitsu         T.Tanaka        Create          N/A
 *</pre>
 */
public class NFCL0740Scrn00_Click_SetCustomerName1 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL0740BMsg scrnMsg = (NFCL0740BMsg) bMsg;

        scrnMsg.addCheckItem(scrnMsg.dsAcctNum_H1);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL0740BMsg scrnMsg = (NFCL0740BMsg) bMsg;
        scrnMsg.dsAcctNm_H1.clear();

        if (!ZYPCommonFunc.hasValue(scrnMsg.dsAcctNum_H1)) {
            return null;
        }

        NFCL0740CMsg bizMsg = new NFCL0740CMsg();
        bizMsg.setBusinessID("NFCL0740");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL0740BMsg scrnMsg = (NFCL0740BMsg) bMsg;
        NFCL0740CMsg bizMsg  = (NFCL0740CMsg) cMsg;

        if (!ZYPCommonFunc.hasValue(scrnMsg.dsAcctNum_H1)) {
            return;
        }

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.addCheckItem(scrnMsg.dsAcctNum_H1);
        scrnMsg.putErrorScreen();
        scrnMsg.setFocusItem(scrnMsg.dsAcctNum_H1);
    }
}
