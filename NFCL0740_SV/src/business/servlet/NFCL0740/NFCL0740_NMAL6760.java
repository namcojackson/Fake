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
public class NFCL0740_NMAL6760 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        if ("CMN_Close".equals(getLastGuard())) {
            return;
        }

        NFCL0740BMsg scrnMsg = (NFCL0740BMsg) bMsg;
        String scrEventNm = scrnMsg.xxScrEventNm.getValue();

        if ("Click_LinkCustomer1".equals(scrEventNm)) {
            scrnMsg.setFocusItem(scrnMsg.dsAcctNum_H1);
            // START 2016/07/08 K.Kojima [QC#8784,DEL]
            // } else {
            // scrnMsg.setFocusItem(scrnMsg.dsAcctNum_H2);
            // END 2016/07/08 K.Kojima [QC#8784,DEL]
        }
    }
}
