/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0270;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSBL0270.NSBL0270CMsg;

import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.file.ZYPExcelUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/18   Hitachi         K.Kasai         Create          N/A
 *</pre>
 */
public class NSBL0270Scrn00_TemplateDownload extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSBL0270BMsg scrnMsg = (NSBL0270BMsg) bMsg;

        NSBL0270CMsg bizMsg = new NSBL0270CMsg();
        bizMsg.setBusinessID("NSBL0270");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSBL0270BMsg scrnMsg = (NSBL0270BMsg) bMsg;
        NSBL0270CMsg bizMsg  = (NSBL0270CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        scrnMsg.putErrorScreen();
        if ("E".equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }
        // execute file down load
        String tempFilePath = scrnMsg.xxFileData.getTempFilePath();
        String excelFileFullPath = ZYPExcelUtil.csvFileToExcel(tempFilePath);
        executeDownload(excelFileFullPath, true, ZYPCSVOutFile.getDialogFileName(excelFileFullPath));
    }
}
