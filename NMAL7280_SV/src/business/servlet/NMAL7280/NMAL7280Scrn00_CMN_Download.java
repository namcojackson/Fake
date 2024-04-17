/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7280;

import static business.servlet.NMAL7280.constant.NMAL7280Constant.BIZ_ID;
import static business.servlet.NMAL7280.constant.NMAL7280Constant.MESSAGE_KIND_ERROR;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import parts.common.EZDFieldErrorException;
import business.blap.NMAL7280.NMAL7280CMsg;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.file.ZYPExcelUtil;

/**
 *<pre>
 * NMAL7280Scrn00_CMN_Download
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/04   Fujitsu         M.Suzuki        Create          N/A
 * 2016/11/18   Fujitsu         M.Ohno          Update          S21_NA#16082
 *</pre>
 */
public class NMAL7280Scrn00_CMN_Download extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7280BMsg scrnMsg = (NMAL7280BMsg) bMsg;

        NMAL7280CMsg bizMsg = new NMAL7280CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NMAL7280BMsg scrnMsg = (NMAL7280BMsg) bMsg;
        NMAL7280CMsg bizMsg  = (NMAL7280CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (MESSAGE_KIND_ERROR.equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }

        String tempFilePath = scrnMsg.xxFileData.getTempFilePath();
        // Mod Start 2016/11/18 M.Ohno S21_NA#16082
        String excelFileFullPath = ZYPExcelUtil.csvFileToExcel(tempFilePath);
        executeDownload(excelFileFullPath, true, ZYPCSVOutFile.getDialogFileName(excelFileFullPath));
        // executeDownload(tempFilePath, true, ZYPCSVOutFile.getDialogFileName(tempFilePath));
        // Mod End   2016/11/18 M.Ohno S21_NA#16082
    }
}
