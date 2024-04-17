/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0410;

import static business.servlet.NSAL0410.constant.NSAL0410Constant.BIZ_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0410.NSAL0410CMsg;
import business.servlet.NSAL0410.common.NSAL0410CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Additional Charge
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/24   Fujitsu         C.Naito         Create          N/A
 *</pre>
 */
public class NSAL0410Scrn00_CMN_Reset extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0410BMsg scrnMsg = (NSAL0410BMsg) bMsg;

        // for Delete Event
        scrnMsg.xxPgFlg_DE.clear();

        // clear scroll position
        scrnMsg.xxListNum_AX.clear();
        scrnMsg.xxListNum_AY.clear();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0410BMsg scrnMsg = (NSAL0410BMsg) bMsg;

        NSAL0410CMsg bizMsg = new NSAL0410CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0410BMsg scrnMsg = (NSAL0410BMsg) bMsg;
        NSAL0410CMsg bizMsg = (NSAL0410CMsg) cMsg;

        if (bizMsg != null) {
            EZDMsg.copy(bizMsg, null, scrnMsg, null);
            NSAL0410CommonLogic.initialControlScreen(this, scrnMsg, bizMsg.getUserID(), getGlobalCompanyCode(), false);
        }
        // set Focus
        scrnMsg.setFocusItem(scrnMsg.A.no(0).xxChkBox_A);
    }
}
