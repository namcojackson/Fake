/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1570;

import static business.servlet.NWAL1570.constant.NWAL1570Constant.BIZ_ID;
import static business.servlet.NWAL1570.constant.NWAL1570Constant.MESSAGE_KIND_ERROR;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1570.NWAL1570CMsg;

import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL1570Scrn01_CMN_Download
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/02   Fujitsu         K.Sato          Create          N/A
 * 2016/11/17   Fujitsu         M.Ohno          Update          S21_NA#16082
 * 2018/05/21   Fujitsu         M.Ohno          Update          S21_NA#23523
 *</pre>
 */
public class NWAL1570Scrn01_CMN_Download extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1570BMsg scrnMsg = (NWAL1570BMsg) bMsg;

        NWAL1570CMsg bizMsg = new NWAL1570CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NWAL1570BMsg scrnMsg = (NWAL1570BMsg) bMsg;
        NWAL1570CMsg bizMsg  = (NWAL1570CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (MESSAGE_KIND_ERROR.equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }
        // 2018/05/22 QC#S21_NA mod start
        //String tempFilePath = scrnMsg.xxFileData.getTempFilePath();
        String excelFileFullPath = scrnMsg.xxFilePathTxt.getValue();
        // 2018/05/22 QC#23523 mod end
        // csv
        // Mod Start 2016/11/17 M.Ohno S21_NA#16082
        // 2018/05/21 S21_NA#23523 del start
//        String excelFileFullPath = ZYPExcelUtil.csvFileToExcel(tempFilePath);
        // 2018/05/21 S21_NA#23523 del end
        executeDownload(excelFileFullPath, true, ZYPCSVOutFile.getDialogFileName(excelFileFullPath));
        // executeDownload(tempFilePath, true, ZYPCSVOutFile.getDialogFileName(tempFilePath));
        // Mod End   2016/11/17 M.Ohno S21_NA#16082
    }
}
