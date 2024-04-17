/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2040;

import static business.servlet.NWAL2040.constant.NWAL2040Constant.*;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL2040.NWAL2040CMsg;

import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.file.ZYPExcelUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/23   Hitachi         K.Kojima        Create          CSA S21 NA Unit Test #56  Add Upload Function
 * 2016/11/17   Fujitsu         M.Ohno          Update          S21_NA#16082
 *</pre>
 */
public class NWAL2040Scrn00_CMN_Download extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL2040BMsg scrnMsg = (NWAL2040BMsg) bMsg;

        NWAL2040CMsg bizMsg = new NWAL2040CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNC_CD_SRCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL2040BMsg scrnMsg = (NWAL2040BMsg) bMsg;
        NWAL2040CMsg bizMsg = (NWAL2040CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (!"E".equals(bizMsg.getMessageKind())) {
            String tempFilePath = scrnMsg.xxFileData.getTempFilePath();
            // Mod Start 2016/11/17 M.Ohno S21_NA#16082
            String excelFileFullPath = ZYPExcelUtil.csvFileToExcel(tempFilePath);
            executeDownload(excelFileFullPath, true, ZYPCSVOutFile.getDialogFileName(excelFileFullPath));
            // executeDownload(tempFilePath, true, ZYPCSVOutFile.getDialogFileName(tempFilePath));
            // Mod End   2016/11/17 M.Ohno S21_NA#16082
        }

    }
}
