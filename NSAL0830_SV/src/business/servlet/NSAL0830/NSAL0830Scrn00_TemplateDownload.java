/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0830;

import static business.servlet.NSAL0830.common.NSAL0830CommonLogic.initialControlScreen;
import static business.servlet.NSAL0830.constant.NSAL0830Constant.BUSINESS_ID;
import static business.servlet.NSAL0830.constant.NSAL0830Constant.FUNCTION_SEARCH;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0830.NSAL0830CMsg;

import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.file.ZYPExcelUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/21   Hitachi         Y.Takeno        Create          N/A
 * 2016/05/20   Hitachi         Y.Tsuchimoto    Update          QC#4061
 * 2016/09/01   Hitachi         Y.Zhang         Update          QC#11846
 *</pre>
 */
public class NSAL0830Scrn00_TemplateDownload extends S21CommonHandler {


    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0830BMsg scrnMsg = (NSAL0830BMsg) bMsg;

        NSAL0830CMsg bizMsg = new NSAL0830CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNCTION_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0830BMsg scrnMsg = (NSAL0830BMsg) bMsg;
        NSAL0830CMsg bizMsg  = (NSAL0830CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        // START 2016/09/01 Y.Zhang [QC#11846, MOD]
        // because focus set add,process execute sort change
        scrnMsg.putErrorScreen();
        initialControlScreen(this, scrnMsg);
        // END 2016/09/01 Y.Zhang [QC#11846, MOD]
        if ("E".equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }
        // execute file down load
        // START 2016/05/20 [QC#4061, MOD]
        String tempFilePath = scrnMsg.xxFileData_D.getTempFilePath();
        String excelFilePath = ZYPExcelUtil.csvFileToExcel(tempFilePath);
        executeDownload(excelFilePath, true, ZYPCSVOutFile.getDialogFileName(excelFilePath));
        // END   2016/05/20 [QC#4061, MOD]
        

    }
}
