/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NFAL0050;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NFAL0050.common.NFAL0050CommonLogic;
import business.servlet.NFAL0050.constant.NFAL0050Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * Class name: Screen Component ID : NFAL0050Scrn00_PasteRowBtn
 * <dd>Remarks:
 * @version 1.0
 * @author N.Sasaki
 */
public class NFAL0050Scrn00_PasteRowBtn extends S21CommonHandler implements NFAL0050Constant {

    /** Singleton instance. */
    private NFAL0050CommonLogic common = new NFAL0050CommonLogic();

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // NFAL0050BMsg scrnMsg = (NFAL0050BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // NFAL0050BMsg scrnMsg = (NFAL0050BMsg) bMsg;
        // NFAL0050CMsg bizMsg = new NFAL0050CMsg();
        // bizMsg.setBusinessID("NFAL0050");
        // bizMsg.setFunctionCode("20");
        // EZDMsg.copy(scrnMsg, null, bizMsg, null);

        // return bizMsg;

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFAL0050BMsg scrnMsg = (NFAL0050BMsg) bMsg;
        // NFAL0050CMsg bizMsg = (NFAL0050CMsg) cMsg;
        // EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (scrnMsg.C.getValidCount() > 0) {
            // Set statsu as event mode
            common.setPasteMode(scrnMsg, this, true);

            scrnMsg.A.clear();
            scrnMsg.A.setValidCount(0);

            int count = 0;
            for (; count < scrnMsg.C.getValidCount(); count++) {
                scrnMsg.C.no(count).xxChkBox_C.clear();
                EZDMsg.copy(scrnMsg.C.no(count), "C", scrnMsg.A.no(count), "A");
            }
            scrnMsg.A.setValidCount(count);

            common.clearHeaderFields(scrnMsg);
            common.clearIndicatorFileds(scrnMsg);
            common.clearAddRow(scrnMsg);

            common.enableSelectAll(scrnMsg, this, false);
            common.enablePasteRow(scrnMsg, this, false);

            common.setEditableMode(scrnMsg, this);
        }

    }

}
