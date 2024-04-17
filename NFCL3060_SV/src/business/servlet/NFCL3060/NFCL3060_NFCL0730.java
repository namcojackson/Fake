/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL3060;

import static business.servlet.NFCL3060.constant.NFCL3060Constant.BIZ_ID;
import static business.servlet.NFCL3060.constant.NFCL3060Constant.FUNC_CD_SRCH;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import business.blap.NFCL3060.NFCL3060CMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NFCL3060_NFCL0730
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/14   Fujitsu         S.Fujita        Create          QC#11023
 *</pre>
 */
public class NFCL3060_NFCL0730 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL3060BMsg scrnMsg = (NFCL3060BMsg) bMsg;

        NFCL3060CMsg bizMsg = new NFCL3060CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_SRCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL3060BMsg scrnMsg = (NFCL3060BMsg) bMsg;
        NFCL3060CMsg bizMsg  = (NFCL3060CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
    }
}
