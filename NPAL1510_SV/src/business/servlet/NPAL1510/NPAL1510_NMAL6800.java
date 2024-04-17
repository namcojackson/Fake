/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1510;

import static business.servlet.NPAL1510.constant.NPAL1510Constant.EVENT_OPEN_WIN_ITEM;
import static business.servlet.NPAL1510.constant.NPAL1510Constant.EVENT_OPEN_WIN_SUBITEM;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/11/2016   CITS            Hisashi       Create          n/a
 *</pre>
 */
public class NPAL1510_NMAL6800 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // no process.

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // no process.
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        if ("CMN_Close".equals(getLastGuard())) {
            return;
        }

        NPAL1510BMsg scrnMsg = (NPAL1510BMsg) bMsg;

        if (EVENT_OPEN_WIN_ITEM.equals(scrnMsg.xxPopPrm_PA.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.mdseCd, scrnMsg.xxPopPrm_P0);
            scrnMsg.setFocusItem(scrnMsg.mdseCd);
        } else if (EVENT_OPEN_WIN_SUBITEM.equals(scrnMsg.xxPopPrm_PA.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.mdseCd_SB, scrnMsg.xxPopPrm_P0);
            scrnMsg.setFocusItem(scrnMsg.mdseCd_SB);
        }
    }
}
