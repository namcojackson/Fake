/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1500;

import static business.servlet.NWAL1500.constant.NWAL1500Constant.BIZ_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1500.NWAL1500CMsg;

import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.file.ZYPExcelUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/01/2016   Fujitsu         T.Ishii         Create          S21_NA#1623
 * 2016/11/17   Fujitsu         M.Ohno          Update          S21_NA#16082
 * 2018/05/22   Fujitsu         A.Kosai         Update          S21_NA#23523
 *</pre>
 */
public class NWAL1500Scrn00_CMN_Download extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;

        NWAL1500CMsg bizMsg = new NWAL1500CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;
        NWAL1500CMsg bizMsg = (NWAL1500CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (!"E".equals(bizMsg.getMessageKind())) {
            // execute file download
            String tempFilePath = scrnMsg.xxFileData.getTempFilePath();
            // Mod Start 2016/11/17 M.Ohno S21_NA#16082
            // 2018/05/22 S21_NA#23523 Mod Start
//            String excelFileFullPath = ZYPExcelUtil.csvFileToExcel(tempFilePath);
//            executeDownload(excelFileFullPath, true, ZYPCSVOutFile.getDialogFileName(excelFileFullPath));
            executeDownload(tempFilePath, true, ZYPCSVOutFile.getDialogFileName(tempFilePath));
            // 2018/05/22 S21_NA#23523 Mod End
            // executeDownload(tempFilePath, true, ZYPCSVOutFile.getDialogFileName(tempFilePath));
            // Mod End   2016/11/17 M.Ohno S21_NA#16082
        }
    }
}
