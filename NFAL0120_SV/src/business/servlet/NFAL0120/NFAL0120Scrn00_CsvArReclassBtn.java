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

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SYS_SRC;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * Class name: Screen Component ID : NFAL0120Scrn00_CSVDownloadBtn
 * <dd>Remarks:
 * @version 1.0
 * @author N.Sasaki
 */
public class NFAL0120Scrn00_CsvArReclassBtn extends S21CommonHandler implements NFAL0120Constant {

    /** Singleton instance. */
    private NFAL0120CommonLogic common = new NFAL0120CommonLogic();

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFAL0120BMsg scrnMsg = (NFAL0120BMsg) bMsg;

        scrnMsg.addCheckItem(scrnMsg.glDt_FR);

        /*
        if (!scrnMsg.glDt_TO.isClear()) {
            scrnMsg.glDt_TO.setErrorInfo(1, "NFAM0023E");
        }
        // AJE ID
        if (!scrnMsg.sysSrcCd_3.isClear() && !scrnMsg.sysSrcCd_3.getValue().equals(SYS_SRC.S21_ACCOUNTING_AR)) {
            scrnMsg.sysSrcCd_3.setErrorInfo(1, "NFAM0023E");
        }
        if (!scrnMsg.trxCd_3.isClear() && !scrnMsg.trxCd_3.getValue().equals(TRX_CD_AR_RECLASS)) {
            scrnMsg.trxCd_3.setErrorInfo(1, "NFAM0023E");
        }
        if (!scrnMsg.trxRsnCd_3.isClear() && !scrnMsg.trxRsnCd_3.getValue().equals(TRX_RSN_CD_AR_RECLASS)) {
            scrnMsg.trxRsnCd_3.setErrorInfo(1, "NFAM0023E");
        }
        // Nine Segment
        if (!scrnMsg.coaBrCd_3.isClear()) {
            scrnMsg.coaBrCd_3.setErrorInfo(1, "NFAM0023E");
        }
        if (!scrnMsg.coaCcCd.isClear()) {
            scrnMsg.coaCcCd.setErrorInfo(1, "NFAM0023E");
        }
        if (!scrnMsg.coaAcctCd.isClear()) {
            scrnMsg.coaAcctCd.setErrorInfo(1, "NFAM0023E");
        }
        if (!scrnMsg.drCoaProdCd.isClear()) {
            scrnMsg.drCoaProdCd.setErrorInfo(1, "NFAM0023E");
        }
        if (!scrnMsg.coaChCd_3.isClear()) {
            scrnMsg.coaChCd_3.setErrorInfo(1, "NFAM0023E");
        }
        if (!scrnMsg.coaAfflCd.isClear()) {
            scrnMsg.coaAfflCd.setErrorInfo(1, "NFAM0023E");
        }
        if (!scrnMsg.coaProjCd.isClear()) {
            scrnMsg.coaProjCd.setErrorInfo(1, "NFAM0023E");
        }
        // Other Criterias
        if (!scrnMsg.tocCd.isClear()) {
            scrnMsg.tocCd.setErrorInfo(1, "NFAM0023E");
        }
        if (!scrnMsg.billToCustCd.isClear()) {
            scrnMsg.billToCustCd.setErrorInfo(1, "NFAM0023E");
        }
        if (!scrnMsg.vndCd.isClear()) {
            scrnMsg.vndCd.setErrorInfo(1, "NFAM0023E");
        }
        if (!scrnMsg.ajeInvNum.isClear()) {
            scrnMsg.ajeInvNum.setErrorInfo(1, "NFAM0023E");
        }
        if (!scrnMsg.prmoPk.isClear()) {
            scrnMsg.prmoPk.setErrorInfo(1, "NFAM0023E");
        }
        if (!scrnMsg.ajeItemCd.isClear()) {
            scrnMsg.ajeItemCd.setErrorInfo(1, "NFAM0023E");
        }
        if (!scrnMsg.coaProdCd.isClear()) {
            scrnMsg.coaProdCd.setErrorInfo(1, "NFAM0023E");
        }
        if (!scrnMsg.soNum.isClear()) {
            scrnMsg.soNum.setErrorInfo(1, "NFAM0023E");
        }

        scrnMsg.addCheckItem(scrnMsg.glDt_TO);

        scrnMsg.addCheckItem(scrnMsg.sysSrcCd_3);
        scrnMsg.addCheckItem(scrnMsg.trxCd_3);
        scrnMsg.addCheckItem(scrnMsg.trxRsnCd_3);

        scrnMsg.addCheckItem(scrnMsg.coaBrCd_3);
        scrnMsg.addCheckItem(scrnMsg.coaCcCd);
        scrnMsg.addCheckItem(scrnMsg.coaAcctCd);
        scrnMsg.addCheckItem(scrnMsg.drCoaProdCd);
        scrnMsg.addCheckItem(scrnMsg.coaChCd_3);
        scrnMsg.addCheckItem(scrnMsg.coaAfflCd);
        scrnMsg.addCheckItem(scrnMsg.coaProjCd);

        scrnMsg.addCheckItem(scrnMsg.tocCd);
        scrnMsg.addCheckItem(scrnMsg.billToCustCd);
        scrnMsg.addCheckItem(scrnMsg.vndCd);
        scrnMsg.addCheckItem(scrnMsg.ajeInvNum);
        scrnMsg.addCheckItem(scrnMsg.prmoPk);
        scrnMsg.addCheckItem(scrnMsg.ajeItemCd);
        scrnMsg.addCheckItem(scrnMsg.coaProdCd);
        scrnMsg.addCheckItem(scrnMsg.soNum);

        scrnMsg.putErrorScreen();
        */
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFAL0120BMsg scrnMsg = (NFAL0120BMsg) bMsg;
        NFAL0120CMsg bizMsg = new NFAL0120CMsg();
        bizMsg.setBusinessID("NFAL0120");
        bizMsg.setFunctionCode("20");
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
        scrnMsg.putErrorScreen();

        if (!ERROR_STR.equals(bizMsg.getMessageKind())) {
            // execute file download
            String tempFilePath = scrnMsg.xxFileData.getTempFilePath();
            executeDownload(tempFilePath, true, ZYPCSVOutFile.getDialogFileName(tempFilePath));
        }

        NFAL0120CommonLogic.initFocusItem(scrnMsg);
        common.setInputProtectedForListInputFiled(scrnMsg);
        common.changeTableColorByRow(scrnMsg);
        */
    }

}
