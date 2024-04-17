/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7000;

import static business.servlet.NMAL7000.constant.NMAL7000Constant.BIZ_ID;
import static business.servlet.NMAL7000.constant.NMAL7000Constant.MESSAGE_KIND_ERROR;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL7000.NMAL7000CMsg;

import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.file.ZYPExcelUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL7000Scrn00_CMN_Download
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/16   Fujitsu         M.Nakamura      Create          N/A
 * 2016/11/18   Fujitsu         R.Nakamura      Update          QC#16082
 *</pre>
 */
public class NMAL7000Scrn00_CMN_Download extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7000BMsg scrnMsg = (NMAL7000BMsg) bMsg;

        NMAL7000CMsg bizMsg = new NMAL7000CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NMAL7000BMsg scrnMsg = (NMAL7000BMsg) bMsg;
        NMAL7000CMsg bizMsg  = (NMAL7000CMsg) cMsg;
        if (MESSAGE_KIND_ERROR.equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        String tempFilePath = scrnMsg.xxFileData_A1.getTempFilePath();
        // Mod Start 2016/11/17 QC#166082
        String excelFileFullPath = ZYPExcelUtil.csvFileToExcel(tempFilePath);
//      executeDownload(tempFilePath, true, ZYPCSVOutFile.getDialogFileName(tempFilePath));
        executeDownload(excelFileFullPath, true, ZYPCSVOutFile.getDialogFileName(excelFileFullPath));
        // Mod End 2016/11/17 QC#166082
    }
}
