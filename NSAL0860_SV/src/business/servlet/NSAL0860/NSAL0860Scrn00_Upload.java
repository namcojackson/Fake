/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0860;

import static business.servlet.NSAL0860.constant.NSAL0860Constant.*;

import java.util.Arrays;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NSAL0860.NSAL0860CMsg;

import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.file.ZYPExcelUtil;
import com.canon.cusa.s21.framework.ZYP.file.ZYPFileNameUtil;
import com.canon.cusa.s21.framework.ZYP.file.web.ZYPUploadFileValidator;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/06/2016   Hitachi         Y.Osawa         Create          N/A
 *</pre>
 */
public class NSAL0860Scrn00_Upload extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0860BMsg scrnMsg = (NSAL0860BMsg) bMsg;
        if (scrnMsg.xxFileData_U.isUploaded()) {
            if (!ZYPUploadFileValidator.isAuthorizedExtension(scrnMsg.xxFileData_U, Arrays.asList(FILE_EXTENSION))) {
                scrnMsg.xxFileData_U.setErrorInfo(1, NSAM0404E);
            }
        } else {
            scrnMsg.xxFileData_U.setErrorInfo(1, NSAM0007E, new String[] {"Upload File" });
        }
        scrnMsg.addCheckItem(scrnMsg.xxFileData_U);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0860BMsg scrnMsg = (NSAL0860BMsg) bMsg;
        if (scrnMsg.xxFileData_U.isUploaded()) {
            scrnMsg.xxFileData_U.makeTempFile(null, ZYPFileNameUtil.createUniqueFileNm(scrnMsg.xxFileData_U, getUserProfileService().getContextUserInfo().getUserId()), "." + ZYPFileNameUtil.getFileEx(scrnMsg.xxFileData_U));
        }

        NSAL0860CMsg bizMsg = new NSAL0860CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_UPDATE);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0860BMsg scrnMsg = (NSAL0860BMsg) bMsg;
        NSAL0860CMsg bizMsg = (NSAL0860CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        if ("E".equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }

        String tempFilePath = scrnMsg.xxFileData_D.getTempFilePath();

        String excelFilePath = ZYPExcelUtil.csvFileToExcel(tempFilePath);
        executeDownload(excelFilePath, true, ZYPCSVOutFile.getDialogFileName(excelFilePath));
    }
}
