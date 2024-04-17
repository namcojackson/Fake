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
 * Class name: Screen Component ID : NFAL0050Scrn00_CMN_Delete
 * <dd>Remarks:
 * @version 1.0
 * @author N.Sasaki
 */
public class NFAL0050Scrn00_CMN_Delete extends S21CommonHandler implements NFAL0050Constant {

    /** Singleton instance. */
    private NFAL0050CommonLogic common = new NFAL0050CommonLogic();

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFAL0050BMsg scrnMsg = (NFAL0050BMsg) bMsg;

        scrnMsg.addCheckItem(scrnMsg.ajePtrnIndTpCd_1V);
        scrnMsg.addCheckItem(scrnMsg.ajePtrnIndTpCd_2V);
        scrnMsg.addCheckItem(scrnMsg.ajePtrnIndTpCd_3V);
        scrnMsg.addCheckItem(scrnMsg.ajePtrnActlCd_1V);
        scrnMsg.addCheckItem(scrnMsg.ajePtrnActlCd_2V);
        scrnMsg.addCheckItem(scrnMsg.ajePtrnActlCd_3V);

        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFAL0050BMsg scrnMsg = (NFAL0050BMsg) bMsg;
        NFAL0050CMsg bizMsg = new NFAL0050CMsg();
        bizMsg.setBusinessID("NFAL0050");
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;

        // return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFAL0050BMsg scrnMsg = (NFAL0050BMsg) bMsg;
        NFAL0050CMsg bizMsg = (NFAL0050CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        int meg = scrnMsg.getMessageType();
        // When no error message
        if (meg != ERROR) {
            scrnMsg.setMessageInfo("ZZM8100I");
            common.setPasteMode(scrnMsg, this, false);
            common.resetAllFileds(scrnMsg, this);
        } else {
            if (!scrnMsg.eventId_P.getValue().equals(EVT_PASTE)) {
                // For Mutual Exclusion,
                // lets a user to redo search
                common.protectSearchableFileds(scrnMsg, this, false);
            }
        }
        common.clearMessageOnScrn(scrnMsg);
        common.setFocus(scrnMsg);
    }

}
