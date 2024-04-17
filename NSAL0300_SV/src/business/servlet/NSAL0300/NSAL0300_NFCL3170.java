/*
 * <pre>Copyright (c) 2023 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0300;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0300.NSAL0300CMsg;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2023/02/20   Hitachi         R.Takau         Create          QC#55645
 *</pre>
 */
public class NSAL0300_NFCL3170 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0300BMsg scrnMsg = (NSAL0300BMsg) bMsg;

        NSAL0300CMsg bizMsg = new NSAL0300CMsg();
        bizMsg.setBusinessID("NSAL0300");
        bizMsg.setFunctionCode("20");

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSAL0300BMsg scrnMsg = (NSAL0300BMsg) bMsg;
        NSAL0300CMsg bizMsg  = (NSAL0300CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
    }
}