/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7190;

import static business.servlet.NMAL7190.constant.NMAL7190Constant.BIZ_ID;
import static business.servlet.NMAL7190.constant.NMAL7190Constant.MESSAGE_KIND_ERROR;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL7190.NMAL7190CMsg;
import business.servlet.NMAL7190.common.NMAL7190CommonLogic;

import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.file.ZYPExcelUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL7190Scrn00_CMN_Download
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/14   Fujitsu         W.Honda         Create          N/A
 * 2016/11/18   Fujitsu         M.Ohno          Update          S21_NA#16082
 *</pre>
 */
public class NMAL7190Scrn00_CMN_Download extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NMAL7190BMsg scrnMsg = (NMAL7190BMsg) bMsg;

        NMAL7190CommonLogic.clearMandantoryCheckHeader(scrnMsg);
        NMAL7190CommonLogic.clearMandantoryCheckDetail(scrnMsg);
        NMAL7190CommonLogic.addCheckItemForHeader(scrnMsg);
        NMAL7190CommonLogic.addCheckItemForDetail(scrnMsg);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7190BMsg scrnMsg = (NMAL7190BMsg) bMsg;

        NMAL7190CMsg bizMsg = new NMAL7190CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NMAL7190BMsg scrnMsg = (NMAL7190BMsg) bMsg;
        NMAL7190CMsg bizMsg  = (NMAL7190CMsg) cMsg;
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
