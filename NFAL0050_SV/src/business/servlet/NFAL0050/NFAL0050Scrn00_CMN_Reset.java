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
 * Class name: Screen Component ID : NFAL0050Scrn00_CMN_Reset
 * <dd>Remarks:
 * @version 1.0
 * @author N.Sasaki
 */
public class NFAL0050Scrn00_CMN_Reset extends S21CommonHandler implements NFAL0050Constant {

    /** Singleton instance. */
    private NFAL0050CommonLogic common = new NFAL0050CommonLogic();

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // NFAL0050BMsg scrnMsg = (NFAL0050BMsg) bMsg;
        //
        // scrnMsg.addCheckItem(scrnMsg.ajeId);
        // scrnMsg.addCheckItem(scrnMsg.ajePtrnIndTpCd01_3);
        // scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFAL0050BMsg scrnMsg = (NFAL0050BMsg) bMsg;
        NFAL0050CMsg bizMsg = new NFAL0050CMsg();

        scrnMsg.ajeId.setValue(scrnMsg.ajeId_T.getValue());
        scrnMsg.ajePtrnIndTpCd_1V.setValue(scrnMsg.ajePtrnIndTpNm_1T.getValue());
        scrnMsg.ajePtrnIndTpCd_2V.setValue(scrnMsg.ajePtrnIndTpNm_2T.getValue());
        scrnMsg.ajePtrnIndTpCd_3V.setValue(scrnMsg.ajePtrnIndTpNm_3T.getValue());
        scrnMsg.ajePtrnActlCd_1V.setValue(scrnMsg.ajePtrnActlNm_1T.getValue());
        scrnMsg.ajePtrnActlCd_2V.setValue(scrnMsg.ajePtrnActlNm_2T.getValue());
        scrnMsg.ajePtrnActlCd_3V.setValue(scrnMsg.ajePtrnActlNm_3T.getValue());

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

        if (scrnMsg.xxSetFlg.getValue().equals(HAS_NO_RESULT)) {
            common.resetAllFileds(scrnMsg, this);
        } else {
            common.setSubmitDeleteBtn(scrnMsg, this);
        }
        common.afterSearch(ctx, scrnMsg, this);
        common.setPasteMode(scrnMsg, this, false);
        // common.setEditableMode(scrnMsg, this);
        common.clearMessageOnScrn(scrnMsg);
        // common.setFocus(scrnMsg);
    }

}
