/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1830;

import static business.servlet.NWAL1830.constant.NWAL1830Constant.BIZ_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1830.NWAL1830CMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL1830Scrn00_OnChange_OrderCategory
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/29   Fujitsu         Y.Taoka         Create          N/A
 *</pre>
 */
public class NWAL1830Scrn00_OnChange_OrderCategory extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
        return;
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1830BMsg scrnMsg = (NWAL1830BMsg) bMsg;

        NWAL1830CMsg bizMsg = new NWAL1830CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1830BMsg scrnMsg = (NWAL1830BMsg) bMsg;
        NWAL1830CMsg bizMsg  = (NWAL1830CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        scrnMsg.setFocusItem(scrnMsg.dsOrdTpCd_OL);

    }
}