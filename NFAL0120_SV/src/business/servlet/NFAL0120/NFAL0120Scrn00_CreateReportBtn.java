/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NFAL0120;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFAL0120.NFAL0120CMsg;
import business.servlet.NFAL0120.common.NFAL0120CommonLogic;
import business.servlet.NFAL0120.constant.NFAL0120Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.printing.S21PrintingBusinessApInOutEJBLocal;

/**
 * Class name: Screen Component ID : NFAL0120Scrn00_CreateReportBtn
 * <dd>Remarks:
 * @version 1.0
 * @author N.Sasaki
 */
public class NFAL0120Scrn00_CreateReportBtn extends S21CommonHandler implements NFAL0120Constant {

    /** Singleton instance. */
    private NFAL0120CommonLogic common = new NFAL0120CommonLogic();

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFAL0120BMsg scrnMsg = (NFAL0120BMsg) bMsg;

        scrnMsg.addCheckItem(scrnMsg.glDt_FR);
        scrnMsg.addCheckItem(scrnMsg.glDt_TO);
        // Number only
        //scrnMsg.addCheckItem(scrnMsg.prmoPk);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFAL0120BMsg scrnMsg = (NFAL0120BMsg) bMsg;
        NFAL0120CMsg bizMsg = new NFAL0120CMsg();
        bizMsg.setBusinessID("NFAL0120");
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;

        // return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFAL0120BMsg scrnMsg = (NFAL0120BMsg) bMsg;
        NFAL0120CMsg bizMsg = (NFAL0120CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        /*
        scrnMsg.addCheckItem(scrnMsg.ajeId);
        scrnMsg.addCheckItem(scrnMsg.coaCcCd);
        scrnMsg.addCheckItem(scrnMsg.coaAcctCd);
        scrnMsg.addCheckItem(scrnMsg.drCoaProdCd);
        scrnMsg.addCheckItem(scrnMsg.coaAfflCd);
        scrnMsg.addCheckItem(scrnMsg.coaProjCd);
        scrnMsg.addCheckItem(scrnMsg.billToCustCd);
        scrnMsg.addCheckItem(scrnMsg.vndCd);
        scrnMsg.addCheckItem(scrnMsg.ajeInvNum);
        scrnMsg.addCheckItem(scrnMsg.prmoPk);
        scrnMsg.addCheckItem(scrnMsg.coaProdCd);
        */
        scrnMsg.putErrorScreen();

        int meg = scrnMsg.getMessageType();
        // When no error message
        if (meg != ERROR) {
            // Call BLAP for creating a report based on a work table
            bizMsg = (NFAL0120CMsg) new S21PrintingBusinessApInOutEJBLocal().executeBlap(ctx, bizMsg, "NFAL0120", "70");
            scrnMsg.setMessageInfo("ZZM8100I");
        }

        // if (!ERROR_STR.equals(bizMsg.getMessageKind())) {
        // // execute file download
        // String tempFilePath = scrnMsg.xxFileData.getTempFilePath();
        // executeDownloadPdf(tempFilePath, true);
        // }

        NFAL0120CommonLogic.initFocusItem(scrnMsg);
        common.setInputProtectedForListInputFiled(scrnMsg);
        common.changeTableColorByRow(scrnMsg);
    }

}
