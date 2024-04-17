/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL3000;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFCL3000.NFCL3000CMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NFCL3000Scrn00_ShipToLocName_D
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/08/01   Fujitsu         S.Fujita        Create          QC#10148
 *</pre>
 */
public class NFCL3000Scrn00_ShipToLocName_D extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL3000BMsg scrnMsg = (NFCL3000BMsg) bMsg;
        int idx = getButtonSelectNumber();
        scrnMsg.xxCellIdx.setValue(idx);

        scrnMsg.addCheckItem(scrnMsg.D.no(idx).shipToCustAcctCd_D1);
        scrnMsg.addCheckItem(scrnMsg.D.no(idx).locNum_D1);

        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL3000BMsg scrnMsg = (NFCL3000BMsg) bMsg;
        scrnMsg.xxCellIdx.setValue(getButtonSelectNumber());

        NFCL3000CMsg bizMsg = new NFCL3000CMsg();
        bizMsg.setBusinessID("NFCL3000");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL3000BMsg scrnMsg = (NFCL3000BMsg) bMsg;
        NFCL3000CMsg bizMsg  = (NFCL3000CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        int idx = scrnMsg.xxCellIdx.getValueInt();

        scrnMsg.addCheckItem(scrnMsg.D.no(idx).shipToCustAcctCd_D1);
        scrnMsg.addCheckItem(scrnMsg.D.no(idx).locNum_D1);
        scrnMsg.putErrorScreen();
    }
}
