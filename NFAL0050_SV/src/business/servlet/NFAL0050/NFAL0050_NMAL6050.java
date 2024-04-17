/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NFAL0050;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NFAL0050.common.NFAL0050CommonLogic;
import business.servlet.NFAL0050.constant.NFAL0050Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * Class name: Screen Component ID : NFAL0050_NMAL6050
 * <dd>Remarks:
 * @version 1.0
 * @author N.Sasaki
 */
public class NFAL0050_NMAL6050 extends S21CommonHandler implements NFAL0050Constant {

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

        if (scrnMsg.eventId.getValue().equals(EVT_COA_BR)) {
            //---- start mod 2016/01/21
            scrnMsg.setFocusItem(scrnMsg.ajeCoaBrCd);
            //---- end 2016/01/21
        } else if (scrnMsg.eventId.getValue().equals(EVT_COA_CC)) {
            scrnMsg.setFocusItem(scrnMsg.ajeCoaCcCd);
        } else if (scrnMsg.eventId.getValue().equals(EVT_COA_PROD)) {
            scrnMsg.setFocusItem(scrnMsg.ajeCoaProdCd);
        } else if (scrnMsg.eventId.getValue().equals(EVT_COA_CH)) {
            scrnMsg.setFocusItem(scrnMsg.ajeCoaChCd_3);
        } else if (scrnMsg.eventId.getValue().equals(EVT_COA_AFFL)) {
            scrnMsg.setFocusItem(scrnMsg.ajeCoaAfflCd);
        } else if (scrnMsg.eventId.getValue().equals(EVT_COA_PROJ)) {
            scrnMsg.setFocusItem(scrnMsg.ajeCoaProjCd);
        } else if (scrnMsg.eventId.getValue().equals(EVT_JRNL_CATG)) {
            scrnMsg.setFocusItem(scrnMsg.jrnlCatgCd);
        }
        common.clearMessageOnScrn(scrnMsg);
    }

}
