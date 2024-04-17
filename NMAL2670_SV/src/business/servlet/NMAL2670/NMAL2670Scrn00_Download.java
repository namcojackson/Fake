/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2670;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL2670.NMAL2670CMsg;
import business.servlet.NMAL2670.constant.NMAL2670Constant;

import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.file.ZYPExcelUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/08   CUSA            Fujitsu         Create          N/A
 * 2016/11/17   Fujitsu         R.nakamura      Update          QC#16082
 *</pre>
 */
public class NMAL2670Scrn00_Download extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2670BMsg scrnMsg = (NMAL2670BMsg) bMsg;

        NMAL2670CMsg bizMsg = new NMAL2670CMsg();
        bizMsg.setBusinessID(NMAL2670Constant.APP_ID);
        bizMsg.setFunctionCode(NMAL2670Constant.FUNCTION_CODE);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2670BMsg scrnMsg = (NMAL2670BMsg) bMsg;
        NMAL2670CMsg bizMsg = (NMAL2670CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {
            throw new EZDFieldErrorException();
        }

        // execute file download
        String tempFilePath = scrnMsg.xxFileData.getTempFilePath();
        // Mod Start 2016/11/17 QC#16082
//      executeDownload(tempFilePath, true, ZYPCSVOutFile.getDialogFileName(tempFilePath));
        String excelFileFullPath = ZYPExcelUtil.csvFileToExcel(tempFilePath);
        executeDownload(excelFileFullPath, true, ZYPCSVOutFile.getDialogFileName(excelFileFullPath));
        // Mod End 2016/11/17 QC#16082
    }
}
