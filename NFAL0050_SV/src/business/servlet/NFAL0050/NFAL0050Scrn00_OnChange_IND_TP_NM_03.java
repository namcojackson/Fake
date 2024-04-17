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
 * NFAL0050Scrn00_OnChange_IND_TP_NM_03
 * <dd>Remarks:
 * @version 1.0
 * @author N.Sasaki
 */
public class NFAL0050Scrn00_OnChange_IND_TP_NM_03 extends S21CommonHandler implements NFAL0050Constant {

    /** Singleton instance. */
    private NFAL0050CommonLogic common = new NFAL0050CommonLogic();

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFAL0050BMsg scrnMsg = (NFAL0050BMsg) bMsg;
        common.clearMessageOnScrn(scrnMsg);

        if (scrnMsg.ajePtrnIndTpCd_3V.isClear()) {
            inputProtectedOtherIndType(scrnMsg);
            common.setUnEditableMode(scrnMsg, this);
        }
        scrnMsg.addCheckItem(scrnMsg.ajePtrnIndTpCd_3V);
        scrnMsg.putErrorScreen();
    }

    private void inputProtectedOtherIndType(NFAL0050BMsg scrnMsg) {
        scrnMsg.ajePtrnIndTpCd_1V.setInputProtected(true);
        scrnMsg.ajePtrnActlCd_1V.setInputProtected(true);
        scrnMsg.ajePtrnIndTpCd_2V.setInputProtected(true);
        scrnMsg.ajePtrnActlCd_2V.setInputProtected(true);
        scrnMsg.ajePtrnIndTpCd_3V.setInputProtected(false);
        scrnMsg.ajePtrnActlCd_3V.setInputProtected(true);
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

        String selectedVal = scrnMsg.ajePtrnIndTpCd_3V.getValue();
        if (!selectedVal.equals(DEFAULT_VAL_CD_3) // Not default
                && (selectedVal.equals(scrnMsg.ajePtrnIndTpCd_1V.getValue()) || selectedVal.equals(scrnMsg.ajePtrnIndTpCd_2V.getValue()))) {
            scrnMsg.ajePtrnIndTpCd_3V.setErrorInfo(1, "NFAM0060E", new String[] {"Indicator Type" });
            inputProtectedOtherIndType(scrnMsg);
            common.setUnEditableMode(scrnMsg, this);
        }
        scrnMsg.addCheckItem(scrnMsg.ajePtrnIndTpCd_3V);
        scrnMsg.putErrorScreen();

        NFAL0050CommonLogic.setInputProtectedTextField(scrnMsg);

        int meg = scrnMsg.getMessageType();
        // When no error message
        if (meg == ERROR) {
            common.setUnEditableMode(scrnMsg, this);
        } else {
            common.setEditableMode(scrnMsg, this);
            common.changeTableColorByRow(ctx, scrnMsg);
        }
        scrnMsg.setFocusItem(scrnMsg.ajePtrnIndTpCd_3V);
    }

}
