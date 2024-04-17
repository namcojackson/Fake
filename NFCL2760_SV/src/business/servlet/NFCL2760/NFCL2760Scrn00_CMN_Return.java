/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL2760;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NFCL2760.NFCL2760CMsg;
import business.servlet.NFCL2760.NFCL2760BMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
public class NFCL2760Scrn00_CMN_Return extends S21CommonHandler {

	@Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL2760CMsg bizMsg = new NFCL2760CMsg();
        NFCL2760BMsg scrnMsg = (NFCL2760BMsg) bMsg;

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        bizMsg.setBusinessID("NFCL2760");
        bizMsg.setFunctionCode("40");

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
    }
}
