/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1130;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1130.NPAL1130CMsg;
import business.servlet.NPAL1130.common.NPAL1130CommonLogic;
import static business.servlet.NPAL1130.constant.NPAL1130Constant.*;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/11/07   CITS            S.Katsuma       Create          N/A
 *</pre>
 */
public class NPAL1130Scrn00_OnChange_DplyConfOrdOnly extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1130BMsg scrnMsg = (NPAL1130BMsg) bMsg;

        NPAL1130CMsg bizMsg = new NPAL1130CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1130BMsg scrnMsg = (NPAL1130BMsg) bMsg;
        NPAL1130CMsg bizMsg  = (NPAL1130CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NPAL1130CommonLogic.setScreenControl(EVENT_ID_SEARCH, this, scrnMsg);
        scrnMsg.setFocusItem(scrnMsg.mdseCd);

    }
}
