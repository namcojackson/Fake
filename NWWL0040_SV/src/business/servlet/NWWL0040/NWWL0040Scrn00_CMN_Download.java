/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWWL0040;

import static business.servlet.NWWL0040.constant.NWWL0040Constant.BIZ_ID;
import static business.servlet.NWWL0040.constant.NWWL0040Constant.MESSAGE_KIND_ERROR;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWWL0040.NWWL0040CMsg;
import business.servlet.NWWL0040.common.NWWL0040CommonLogic;

import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.file.ZYPExcelUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWWL0040Scrn00_CMN_Download
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/27   Fujitsu         M.Ohno          Create          N/A
 * 2016/11/18   Fujitsu         M.Ohno          Update          S21_NA#16082
 *</pre>
 */
public class NWWL0040Scrn00_CMN_Download extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NWWL0040BMsg scrnMsg = (NWWL0040BMsg) bMsg;

        NWWL0040CommonLogic.checkHdr(scrnMsg);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWWL0040BMsg scrnMsg = (NWWL0040BMsg) bMsg;

        NWWL0040CMsg bizMsg = new NWWL0040CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NWWL0040BMsg scrnMsg = (NWWL0040BMsg) bMsg;
        NWWL0040CMsg bizMsg = (NWWL0040CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (MESSAGE_KIND_ERROR.equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }

        String tempFilePath = scrnMsg.xxFileData_A.getTempFilePath();
        // Mod Start 2016/11/18 M.Ohno S21_NA#16082
        String excelFileFullPath = ZYPExcelUtil.csvFileToExcel(tempFilePath);
        executeDownload(excelFileFullPath, true, ZYPCSVOutFile.getDialogFileName(excelFileFullPath));
        // executeDownload(tempFilePath, true, ZYPCSVOutFile.getDialogFileName(tempFilePath));
        // Mod End   2016/11/18 M.Ohno S21_NA#16082
        scrnMsg.setFocusItem(scrnMsg.ntfyDistListNm);
    }
}
