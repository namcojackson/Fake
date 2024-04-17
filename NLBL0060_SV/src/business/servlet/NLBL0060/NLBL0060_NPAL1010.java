/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL0060;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/05/21   CUSA            Fujitsu         Create          R-OM025 Inventory Transaction
 *</pre>
 */
public class NLBL0060_NPAL1010 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLBL0060BMsg scrnMsg = (NLBL0060BMsg) bMsg;
        EZDBStringItem tmp;
        int index = getButtonSelectNumber();
        if (0 > index) {
            tmp = scrnMsg.whCd_H2;
        } else {

            tmp = scrnMsg.A.no(index).whCd_A1;
        }       
        // Focus
        scrnMsg.setFocusItem(tmp);
        scrnMsg.addCheckItem(tmp);
        scrnMsg.putErrorScreen();

    }
}
