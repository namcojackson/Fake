/*
 * <pre>Copyright (c) 2018 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFDL0020;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFDL0020.NFDL0020CMsg;

import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/03/06   Hitachi         J.Kim           Create          QC#20945
 * 2018/05/16   Fujitsu         Y.Matsui        Update          QC#24329
 * 2018/05/21   CITS            S.Katsuma       Update          QC#24793
 *</pre>
 */
public class NFDL0020Scrn00_CMN_Download extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFDL0020BMsg scrnMsg = (NFDL0020BMsg) bMsg;

        NFDL0020CMsg bizMsg = new NFDL0020CMsg();
        bizMsg.setBusinessID("NFDL0020");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFDL0020BMsg scrnMsg = (NFDL0020BMsg) bMsg;
        NFDL0020CMsg bizMsg = (NFDL0020CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if ("E".equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }

        // START 2018/05/16 [QC#24329,MOD]
        if (scrnMsg.xxDplyTab_H.getValue().equals("AdjHistory")) {
            if (scrnMsg.H.getValidCount() != 0) {
                String tempFilePath = scrnMsg.xxFileData.getTempFilePath();
                executeDownload(tempFilePath, true, ZYPCSVOutFile.getDialogFileName(tempFilePath));
            }
        }
        // END 2018/05/16 [QC#24329,MOD]

        // START 2018/05/16 [QC#24329,ADD]
        if (scrnMsg.xxDplyTab_H.getValue().equals("Statement")) {
            if (scrnMsg.J.getValidCount() != 0) {
                String tempFilePath = scrnMsg.xxFileData.getTempFilePath();
                executeDownload(tempFilePath, true, ZYPCSVOutFile.getDialogFileName(tempFilePath));
            }
        }
        // END 2018/05/16 [QC#24329,ADD]

        // START 2018/05/21 S.Katsuma [QC#24793,ADD]
        if (scrnMsg.xxDplyTab_H.getValue().equals("Transaction")) {
            if (scrnMsg.A.getValidCount() != 0) {
                String tempFilePath = scrnMsg.xxFileData.getTempFilePath();
                executeDownload(tempFilePath, true, ZYPCSVOutFile.getDialogFileName(tempFilePath));
            }
        }
        if (scrnMsg.xxDplyTab_H.getValue().equals("Note")) {
            if (scrnMsg.F.getValidCount() != 0) {
                String tempFilePath = scrnMsg.xxFileData.getTempFilePath();
                executeDownload(tempFilePath, true, ZYPCSVOutFile.getDialogFileName(tempFilePath));
            }
        }
        if (scrnMsg.xxDplyTab_H.getValue().equals("Task")) {
            if (scrnMsg.G.getValidCount() != 0) {
                String tempFilePath = scrnMsg.xxFileData.getTempFilePath();
                executeDownload(tempFilePath, true, ZYPCSVOutFile.getDialogFileName(tempFilePath));
            }
        }
        if (scrnMsg.xxDplyTab_H.getValue().equals("Contract")) {
            if (scrnMsg.B.getValidCount() != 0) {
                String tempFilePath = scrnMsg.xxFileData.getTempFilePath();
                executeDownload(tempFilePath, true, ZYPCSVOutFile.getDialogFileName(tempFilePath));
            }
        }
        // END 2018/05/21 S.Katsuma [QC#24793,ADD]
    }
}
