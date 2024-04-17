/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFAL0220;

import static business.servlet.NFAL0220.constant.NFAL0220Constant.*;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFAL0220.NFAL0220CMsg;
import business.servlet.NFAL0220.common.NFAL0220CommonLogic;

import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NFAL0220Scrn00_TemplateDownload
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/03   Hitachi         J.Kim           Create          N/A
 * 2016/08/04   Hitachi         J.Kim           Create          QC#12851
 *</pre>
 */
public class NFAL0220Scrn00_TemplateDownload extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFAL0220BMsg scrnMsg = (NFAL0220BMsg) bMsg;

        NFAL0220CMsg bizMsg = NFAL0220CommonLogic.setRequestData_SearchCommon();

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFAL0220BMsg scrnMsg = (NFAL0220BMsg) bMsg;
        NFAL0220CMsg bizMsg = (NFAL0220CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.putErrorScreen();
        if (MESSAGE_ERROR.equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }
        // execute file down load
        String tempFilePath = scrnMsg.xxFileData.getTempFilePath();
        // START 2016/08/04 J.Kim [QC#12851,MOD]
        // String excelFileFullPath = ZYPExcelUtil.csvFileToExcel(tempFilePath);
        // executeDownload(excelFileFullPath, true, ZYPCSVOutFile.getDialogFileName(excelFileFullPath));
        executeDownload(tempFilePath, true, ZYPCSVOutFile.getDialogFileName(tempFilePath));
        // END 2016/08/04 J.Kim [QC#12851,MOD]
    }
}
