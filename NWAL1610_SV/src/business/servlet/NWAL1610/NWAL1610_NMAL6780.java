/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1610;

import static business.servlet.NWAL1610.constant.NWAL1610Constant.OPENWIN_BILL_TO;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL1610_NMAL6780
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/21   Fujitsu         C.Yokoi         Create          N/A
 * 2016/05/16   Fujitsu         M.Yamada        Delete          QC#6148 this class has obsoleted.
 *</pre>
 */
public class NWAL1610_NMAL6780 extends S21CommonHandler {

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

        NWAL1610BMsg scrnMsg = (NWAL1610BMsg) bMsg;
        // Set Focus
        if (OPENWIN_BILL_TO.equals(scrnMsg.xxScrEventNm.getValue())) {
            scrnMsg.setFocusItem(scrnMsg.billToCustLocCd);
        } else {
            scrnMsg.setFocusItem(scrnMsg.shipToCustLocCd);
        }
    }
}
