/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2460;

import static business.servlet.NMAL2460.constant.NMAL2460Constant.BIZ_ID;
import static business.servlet.NMAL2460.constant.NMAL2460Constant.EZD_FUNC_CD_INQ;
import static business.servlet.NMAL2460.constant.NMAL2460Constant.MESSAGE_KIND_ERROR;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL2460.NMAL2460CMsg;

import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.file.ZYPExcelUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/26   Hitachi         O.Okuma         Create          N/A
 * 2016/11/17   Fujitsu         R.nakamura      Update          QC#16082
 *</pre>
 */
public class NMAL2460Scrn00_CMN_Download extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2460BMsg scrnMsg = (NMAL2460BMsg) bMsg;

        NMAL2460CMsg bizMsg = new NMAL2460CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(EZD_FUNC_CD_INQ);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NMAL2460BMsg scrnMsg = (NMAL2460BMsg) bMsg;
        NMAL2460CMsg bizMsg  = (NMAL2460CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (MESSAGE_KIND_ERROR.equals(cMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }

        // execute file download
        String tempFilePath = scrnMsg.xxFileData.getTempFilePath();
        // Mod Start 2016/11/17 QC#16082
//        executeDownload(tempFilePath, true, ZYPCSVOutFile.getDialogFileName(tempFilePath));
        String excelFileFullPath = ZYPExcelUtil.csvFileToExcel(tempFilePath);
        executeDownload(excelFileFullPath, true, ZYPCSVOutFile.getDialogFileName(excelFileFullPath));
        // Mod End 2016/11/17 QC#16082
    }
}
