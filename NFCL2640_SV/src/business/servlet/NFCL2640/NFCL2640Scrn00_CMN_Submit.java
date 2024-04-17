/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL2640;

import static business.servlet.NFCL2640.constant.NFCL2640Constant.BIZ_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFCL2640.NFCL2640CMsg;
import business.servlet.NFCL2640.common.NFCL2640CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Statement Schedule Maintenance
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/25   Fujitsu         M.Ohno          Create          N/A
 *</pre>
 */
public class NFCL2640Scrn00_CMN_Submit extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NFCL2640BMsg scrnMsg = (NFCL2640BMsg) bMsg;
        scrnMsg.A.setCheckParam(new String[] {NFCL2640Bean.arStmtStsCd_A1}, 1);
        scrnMsg.addCheckItem(scrnMsg.arStmtIssCycleCd);
        scrnMsg.addCheckItem(scrnMsg.A);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL2640BMsg scrnMsg = (NFCL2640BMsg) bMsg;

        NFCL2640CMsg bizMsg = new NFCL2640CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL2640BMsg scrnMsg = (NFCL2640BMsg) bMsg;
        NFCL2640CMsg bizMsg = (NFCL2640CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        scrnMsg.A.setCheckParam(new String[] {NFCL2640Bean.arStmtStsCd_A1}, 1);
        scrnMsg.addCheckItem(scrnMsg.A);
        scrnMsg.putErrorScreen();
        if (bizMsg.A.getValidCount() > 0 && bMsg.getMessageType() != 0) {
            NFCL2640CommonLogic.searchControlScreen(this, scrnMsg, getGlobalCompanyCode());
        }

    }
}
