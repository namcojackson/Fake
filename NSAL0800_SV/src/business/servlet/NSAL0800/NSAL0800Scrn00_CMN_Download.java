/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0800;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0800.NSAL0800CMsg;

import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.file.ZYPExcelUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/08/15   CITS            T.Kikuhara      Create          QC#18799(L3)
 *</pre>
 */
public class NSAL0800Scrn00_CMN_Download extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0800BMsg scrnMsg = (NSAL0800BMsg) bMsg;

        NSAL0800CMsg bizMsg = new NSAL0800CMsg();
        bizMsg.setBusinessID("NSAL0800");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0800BMsg scrnMsg = (NSAL0800BMsg) bMsg;
        NSAL0800CMsg bizMsg  = (NSAL0800CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if ("E".equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }
        // execute excel file down load
        String tempFilePath = scrnMsg.xxFileData_X1.getTempFilePath();
        String excelFilePAth = ZYPExcelUtil.csvFileToExcel(tempFilePath);

        executeDownload(excelFilePAth, true, ZYPCSVOutFile.getDialogFileName(excelFilePAth));

    }
}
