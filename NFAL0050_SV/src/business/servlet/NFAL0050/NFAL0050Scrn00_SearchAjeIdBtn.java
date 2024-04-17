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
 * Class name: Screen Component ID : NFAL0050Scrn00_SearchAjeIdBtn
 * <dd>Remarks:
 * @version 1.0
 * @author N.Sasaki
 */
public class NFAL0050Scrn00_SearchAjeIdBtn extends S21CommonHandler implements NFAL0050Constant {

    /** Singleton instance. */
    private NFAL0050CommonLogic common = new NFAL0050CommonLogic();

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFAL0050BMsg scrnMsg = (NFAL0050BMsg) bMsg;

        scrnMsg.sysSrcCd_3.setInputProtected(false);
        scrnMsg.trxCd_3.setInputProtected(false);
        scrnMsg.trxRsnCd_3.setInputProtected(false);

        if (!common.isValidAjeIDFormat(scrnMsg)) {
            scrnMsg.ajeId.setErrorInfo(1, "NFAM0045E", new String[] {"AJE ID" });
        }
        scrnMsg.addCheckItem(scrnMsg.ajeId);
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

        scrnMsg.addCheckItem(scrnMsg.ajeId);
        // Show error message for invalid
        // SysSrc, Trx, TrxRsn
        scrnMsg.addCheckItem(scrnMsg.sysSrcCd_3);
        scrnMsg.addCheckItem(scrnMsg.trxCd_3);
        scrnMsg.addCheckItem(scrnMsg.trxRsnCd_3);
        // Program end if error has been detected above
        scrnMsg.putErrorScreen();

        NFAL0050CommonLogic.setInputProtectedTextField(scrnMsg);

        int meg = scrnMsg.getMessageType();
        if (meg != ERROR) {
            // AJE ID, SysSrcCd, TrxCd,TrxRsnCd shouldn't be changed
            // once search has been done, unless invalid code has
            // entered
            common.protectSearchableFileds(scrnMsg, this, true);
        }
        common.afterSearch(ctx, scrnMsg, this);
        // common.setEditableMode(scrnMsg, this);
        // common.setFocus(scrnMsg);
    }

}
