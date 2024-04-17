/*
 * <pre>Copyright (c) 2023 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFAL0270;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFAL0270.NFAL0270CMsg;
import static business.servlet.NFAL0270.constant.NFAL0270Constant.*;
import static business.servlet.NFAL0270.common.NFAL0270CommonLogic.*;

import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.file.ZYPExcelUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2023/05/17   Hitachi         G.Quan          Create          QC#61387
 *</pre>
 */
public class NFAL0270Scrn00_CMN_Download extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFAL0270BMsg scrnMsg = (NFAL0270BMsg) bMsg;
        addCheckItem(scrnMsg);

        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFAL0270BMsg scrnMsg = (NFAL0270BMsg) bMsg;

        NFAL0270CMsg bizMsg = new NFAL0270CMsg();
        bizMsg.setBusinessID(BUSINESS_APPL_ID);
        bizMsg.setFunctionCode(FUNC_SRCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFAL0270BMsg scrnMsg = (NFAL0270BMsg) bMsg;
        NFAL0270CMsg bizMsg  = (NFAL0270CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (!"E".equals(bizMsg.getMessageKind())) {

            // execute file download
            String tempFilePath = scrnMsg.xxFileData.getTempFilePath();
            String excelFileFullPath = ZYPExcelUtil.csvFileToExcel(tempFilePath);
            executeDownload(excelFileFullPath, true, ZYPCSVOutFile.getDialogFileName(excelFileFullPath));

        } else {

            // Error
            addCheckItem(scrnMsg);
            scrnMsg.putErrorScreen();
        }
    }
}
