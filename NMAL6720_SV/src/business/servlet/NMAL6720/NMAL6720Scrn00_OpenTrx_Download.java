/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6720;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL6720.NMAL6720CMsg;

import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.file.ZYPExcelUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * OpenTrx_Download Event
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2020/07/22   CITS            K.Ogino         Create          QC#57316
 *</pre>
 */
public class NMAL6720Scrn00_OpenTrx_Download extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // do nothing

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NMAL6720BMsg scrnMsg = (NMAL6720BMsg) bMsg;
        NMAL6720CMsg bizMsg = new NMAL6720CMsg();
        bizMsg.setBusinessID("NMAL6720");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NMAL6720BMsg scrnMsg = (NMAL6720BMsg) bMsg;
        NMAL6720CMsg bizMsg = (NMAL6720CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {
            throw new EZDFieldErrorException();
        }
        String tempFilePath = scrnMsg.xxFileData.getTempFilePath();
        String excelFileFullPath = ZYPExcelUtil.csvFileToExcel(tempFilePath);
        executeDownload(excelFileFullPath, true, ZYPCSVOutFile.getDialogFileName(excelFileFullPath));
    }
}
