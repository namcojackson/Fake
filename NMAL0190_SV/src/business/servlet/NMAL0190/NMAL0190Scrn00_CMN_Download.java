/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL0190;

import static business.servlet.NMAL0190.constant.NMAL0190Constant.BIZ_ID;
import static business.servlet.NMAL0190.constant.NMAL0190Constant.MESSAGE_KIND_ERROR;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL0190.NMAL0190CMsg;

import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.file.ZYPExcelUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL0190Scrn00_CMN_Download
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/07   Fujitsu         T.Arima         Create          N/A
 * 2016/11/17   Fujitsu         R.nakamura      Update          QC#16082
 *</pre>
 */
public class NMAL0190Scrn00_CMN_Download extends S21CommonHandler {

    @Override
    /**
     * Check Input Event
     * - do nothing
     */
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    /**
     * Set Request Date Event
     * - do download action.
     */
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL0190BMsg scrnMsg = (NMAL0190BMsg) bMsg;

        NMAL0190CMsg bizMsg = new NMAL0190CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    /**
     * Do Process Event
     * - execute Download 
     */
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NMAL0190BMsg scrnMsg = (NMAL0190BMsg) bMsg;
        NMAL0190CMsg bizMsg  = (NMAL0190CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // Show Error Message
        if (MESSAGE_KIND_ERROR.equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }

        String tempFilePath = scrnMsg.xxFileData.getTempFilePath();
        // execute CSV Download
        // Mod Start 2016/11/17 QC#16082
//        executeDownload(tempFilePath, true, ZYPCSVOutFile.getDialogFileName(tempFilePath));
        String excelFileFullPath = ZYPExcelUtil.csvFileToExcel(tempFilePath);
        executeDownload(excelFileFullPath, true, ZYPCSVOutFile.getDialogFileName(excelFileFullPath));
        // Mod End 2016/11/17 QC#16082
    }
}
