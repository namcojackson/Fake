/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7240;

//import static business.servlet.NMAL7240.constant.NMAL7240Constant.BIZ_ID;
import static business.servlet.NMAL7240.constant.NMAL7240Constant.BIZ_ID;
import static business.servlet.NMAL7240.constant.NMAL7240Constant.MESSAGE_KIND_ERROR;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NMAL7240.NMAL7240CMsg;
//import business.servlet.NMAL7240.constant.NMAL7240Constant;

import business.blap.NMAL7240.NMAL7240CMsg;
import business.servlet.NMAL7240.common.NMAL7240CommonLogic;

import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.file.ZYPExcelUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL7240Scrn00_TemplateDownload
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/13   Fujitsu         W.Honda         Create          QC#8303
 * 2016/05/17   Fujitsu         W.Honda         Update          QC#7040
 * 2016/11/22   Fujitsu         M.Ohno          Update          S21_NA#16082
 *</pre>
 */
public class NMAL7240Scrn00_TemplateDownload extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7240BMsg scrnMsg = (NMAL7240BMsg) bMsg;

        NMAL7240CommonLogic.addCheckItemForHeader(scrnMsg);
        NMAL7240CommonLogic.clearMandantoryCheckDetail(scrnMsg);
        NMAL7240CommonLogic.addCheckItemForDetail(scrnMsg);

        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7240BMsg scrnMsg = (NMAL7240BMsg) bMsg;

        NMAL7240CMsg bizMsg = new NMAL7240CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NMAL7240BMsg scrnMsg = (NMAL7240BMsg) bMsg;
        NMAL7240CMsg bizMsg  = (NMAL7240CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (MESSAGE_KIND_ERROR.equals(bizMsg.getMessageKind())) {

            // QC#7040 2016/05/17 Del start
//            NMAL7240CommonLogic.addCheckItemForHeader(scrnMsg);
            // QC#7040 2016/05/17 Del end
            throw new EZDFieldErrorException();
        }

        String tempFilePath = scrnMsg.xxFileData.getTempFilePath();
        // Mod Start 2016/11/22 M.Ohno S21_NA#16082
        String excelFileFullPath = ZYPExcelUtil.csvFileToExcel(tempFilePath);
        executeDownload(excelFileFullPath, true, ZYPCSVOutFile.getDialogFileName(excelFileFullPath));
        // executeDownload(tempFilePath, true, ZYPCSVOutFile.getDialogFileName(tempFilePath));
        // Mod End   2016/11/22 M.Ohno S21_NA#16082
    }
}
