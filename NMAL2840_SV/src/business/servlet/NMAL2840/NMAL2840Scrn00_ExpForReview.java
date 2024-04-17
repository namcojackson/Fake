/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2840;

import static business.servlet.NMAL2840.constant.NMAL2840Constant.MESSAGE_KIND_ERROR;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL2840.NMAL2840CMsg;
import business.servlet.NMAL2840.common.NMAL2840CommonLogic;

import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.file.ZYPExcelUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/31   Fujitsu         R.Nakamura      Create          N/A
 * 2016/11/17   Fujitsu         R.nakamura      Update          QC#16082
 *</pre>
 */
public class NMAL2840Scrn00_ExpForReview extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2840BMsg scrnMsg = (NMAL2840BMsg) bMsg;
        NMAL2840CommonLogic.commonAddCheckItemReview(scrnMsg);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2840BMsg scrnMsg = (NMAL2840BMsg) bMsg;

        NMAL2840CMsg bizMsg = new NMAL2840CMsg();
        bizMsg.setBusinessID("NMAL2840");
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2840BMsg scrnMsg = (NMAL2840BMsg) bMsg;
        NMAL2840CMsg bizMsg = (NMAL2840CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (MESSAGE_KIND_ERROR.equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }

        String tempFilePath = scrnMsg.xxFileData.getTempFilePath();
        // Mod Start 2016/11/17 QC#16082
//      executeDownload(tempFilePath, true, ZYPCSVOutFile.getDialogFileName(tempFilePath));
        String excelFileFullPath = ZYPExcelUtil.csvFileToExcel(tempFilePath);
        executeDownload(excelFileFullPath, true, ZYPCSVOutFile.getDialogFileName(excelFileFullPath));
        // Mod End 2016/11/17 QC#16082

        NMAL2840CommonLogic.initialControlScreen(getUserProfileService(), this, scrnMsg);
        scrnMsg.setFocusItem(scrnMsg.xxDtTm_PS);
    }
}
