/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NFAL0050;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFAL0050.NFAL0050CMsg;
import business.servlet.NFAL0050.common.NFAL0050CommonLogic;
import business.servlet.NFAL0050.constant.NFAL0050Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * Class name: Screen Component ID :
 * NFAL0050Scrn00_OnChange_SYS_SRC_CD
 * <dd>Remarks:
 * @version 1.0
 * @author N.Sasaki
 */
public class NFAL0050Scrn00_OnChange_SYS_SRC_CD extends S21CommonHandler implements NFAL0050Constant {

    /** Singleton instance. */
    private NFAL0050CommonLogic common = new NFAL0050CommonLogic();

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFAL0050BMsg scrnMsg = (NFAL0050BMsg) bMsg;

        if (scrnMsg.sysSrcCd_3.isClear()) {
            // NFAM0043E=0,Invalid @
            scrnMsg.sysSrcCd_3.setErrorInfo(1, "NFAM0043E", new String[] {"input" });
        }
        scrnMsg.addCheckItem(scrnMsg.sysSrcCd_3);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFAL0050BMsg scrnMsg = (NFAL0050BMsg) bMsg;
        NFAL0050CMsg bizMsg = new NFAL0050CMsg();

        bizMsg.setBusinessID("NFAL0050");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;

        // return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFAL0050BMsg scrnMsg = (NFAL0050BMsg) bMsg;
        NFAL0050CMsg bizMsg = (NFAL0050CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        common.setNewAjeId(scrnMsg, this);
        if (!scrnMsg.eventId_P.getValue().equals(EVT_PASTE)) {
            common.resetFiledsExceptHeader(scrnMsg, this);
        }
        scrnMsg.setFocusItem(scrnMsg.sysSrcCd_3);
    }

}
