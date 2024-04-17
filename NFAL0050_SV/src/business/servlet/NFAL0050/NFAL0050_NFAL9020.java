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
 * Class name: Screen Component ID : NFAL0050_NFAL9020
 * <dd>Remarks:
 * @version 1.0
 * @author N.Sasaki
 */
public class NFAL0050_NFAL9020 extends S21CommonHandler implements NFAL0050Constant {

    /** Singleton instance. */
    private NFAL0050CommonLogic common = new NFAL0050CommonLogic();

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // NFAL0050BMsg scrnMsg = (NFAL0050BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        if (!ctx.getEventName().equals(EVENT_CMN_CLOSE)) {
            NFAL0050BMsg scrnMsg = (NFAL0050BMsg) bMsg;
            NFAL0050CMsg bizMsg = new NFAL0050CMsg();

            scrnMsg.ajePtrnIndTpCd_1V.setValue(scrnMsg.ajePtrnIndTpCd_01.getValue());
            scrnMsg.ajePtrnActlCd_1V.setValue(scrnMsg.ajePtrnActlCd_01.getValue());
            scrnMsg.ajePtrnIndTpCd_2V.setValue(scrnMsg.ajePtrnIndTpCd_02.getValue());
            scrnMsg.ajePtrnActlCd_2V.setValue(scrnMsg.ajePtrnActlCd_02.getValue());
            scrnMsg.ajePtrnIndTpCd_3V.setValue(scrnMsg.ajePtrnIndTpCd_03.getValue());
            scrnMsg.ajePtrnActlCd_3V.setValue(scrnMsg.ajePtrnActlCd_03.getValue());

            bizMsg.setBusinessID("NFAL0050");
            bizMsg.setFunctionCode("20");
            EZDMsg.copy(scrnMsg, null, bizMsg, null);
            return bizMsg;
        } else {
            return null;
        }
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFAL0050BMsg scrnMsg = (NFAL0050BMsg) bMsg;

        if (!ctx.getEventName().equals(EVENT_CMN_CLOSE)) {
            NFAL0050CMsg bizMsg = (NFAL0050CMsg) cMsg;
            EZDMsg.copy(bizMsg, null, scrnMsg, null);

            common.protectSearchableFileds(scrnMsg, this, true);
            common.setEditableMode(scrnMsg, this);
            NFAL0050CommonLogic.setInputProtectedTextField(scrnMsg);
            scrnMsg.setFocusItem(scrnMsg.ajePtrnIndTpCd_1V);
        } else {
            common.setFocus(scrnMsg);
        }
        common.clearMessageOnScrn(scrnMsg);
    }
}
