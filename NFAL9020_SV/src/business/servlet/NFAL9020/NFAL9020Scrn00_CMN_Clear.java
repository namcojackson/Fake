/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NFAL9020;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NFAL9020.common.NFAL9020CommonLogic;
import business.servlet.NFAL9020.constant.NFAL9020Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * Class name: NFAL9020Scrn00_CMN_Clear
 * <dd>The class explanation: Business processing for Component ID :
 * NFAL9020Scrn00_CMN_Clear
 * <dd>Remarks:
 * @version 1.0
 * @author N.Sasaki
 */
public class NFAL9020Scrn00_CMN_Clear extends S21CommonHandler implements NFAL9020Constant {

    /** Singleton instance. */
    NFAL9020CommonLogic common = new NFAL9020CommonLogic();

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // NFAL9020BMsg scrnMsg = (NFAL9020BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // NFAL9020BMsg scrnMsg = (NFAL9020BMsg) bMsg;

        // NFAL9020CMsg bizMsg = new NFAL9020CMsg();
        // bizMsg.setBusinessID("NFAL9020");
        // bizMsg.setFunctionCode("20");
        // EZDMsg.copy(scrnMsg, null, bizMsg, null);

        // return bizMsg;

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFAL9020BMsg scrnMsg = (NFAL9020BMsg) bMsg;
        // NFAL9020CMsg bizMsg = (NFAL9020CMsg) cMsg;
        // EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.A.clear();
        scrnMsg.A.setValidCount(0);
        scrnMsg.xxPageShowFromNum.setValue(0);
        scrnMsg.xxPageShowToNum.setValue(0);
        scrnMsg.xxPageShowOfNum.setValue(0);

        scrnMsg.ajeId.clear();
        scrnMsg.sysSrcCd_3.clear();
        scrnMsg.trxCd_3.clear();
        scrnMsg.trxRsnCd_3.clear();

        NFAL9020CommonLogic.changeTableColorByRow(scrnMsg);

        scrnMsg.setFocusItem(scrnMsg.ajeId);
    }

}
