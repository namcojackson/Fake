/*
 * <pre>Copyright (c) 2023 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0520;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0520.NSAL0520CMsg;
import business.servlet.NSAL0520.constant.NSAL0520Constant;

import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.file.ZYPExcelUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2023/05/15   Hitachi         R.Takau         Create          QC#61409
 *</pre>
 */
public class NSAL0520Scrn00_Download extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0520BMsg scrnMsg = (NSAL0520BMsg) bMsg;
        NSAL0520CMsg bizMsg = new NSAL0520CMsg();
        bizMsg.setBusinessID(NSAL0520Constant.BIZ_ID);
        bizMsg.setFunctionCode(NSAL0520Constant.EZD_FUNC_CD_INQ);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSAL0520BMsg scrnMsg = (NSAL0520BMsg) bMsg;
        NSAL0520CMsg bizMsg = (NSAL0520CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.putErrorScreen();
        if ("E".equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }

        // execute file down load
        String tempFilePath = scrnMsg.xxFileData_D.getTempFilePath();
        String excelFileFullPath = ZYPExcelUtil.csvFileToExcel(tempFilePath);
        executeDownload(excelFileFullPath, true, ZYPCSVOutFile.getDialogFileName(excelFileFullPath));
    }
}
