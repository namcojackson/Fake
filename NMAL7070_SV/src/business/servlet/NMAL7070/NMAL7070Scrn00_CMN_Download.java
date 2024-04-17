/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7070;

import static business.servlet.NMAL7070.constant.NMAL7070Constant.BIZ_ID;
import static business.servlet.NMAL7070.constant.NMAL7070Constant.MESSAGE_KIND_ERROR;
import static business.servlet.NMAL7070.constant.NMAL7070Constant.NMAM0185E;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL7070.NMAL7070CMsg;
import business.servlet.NMAL7070.common.NMAL7070CommonLogic;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.file.ZYPExcelUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Supply Agreement Plan Search
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/08   Fujitsu         M.Ohno          Create          N/A
 * 2016/11/21   Fujitsu         R.Nakamura      Update          QC#16082
 *</pre>
 */
public class NMAL7070Scrn00_CMN_Download extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7070BMsg scrnMsg = (NMAL7070BMsg) bMsg;
        if (ZYPCommonFunc.hasValue(scrnMsg.effFromDt) && ZYPCommonFunc.hasValue(scrnMsg.effThruDt)) {

            if (ZYPDateUtil.compare(scrnMsg.effThruDt.getValue(), scrnMsg.effFromDt.getValue()) < 0) {
                scrnMsg.effFromDt.setErrorInfo(1, NMAM0185E);
                scrnMsg.effThruDt.setErrorInfo(1, NMAM0185E);
            }
        }

        NMAL7070CommonLogic.commonAddCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7070BMsg scrnMsg = (NMAL7070BMsg) bMsg;

        NMAL7070CMsg bizMsg = new NMAL7070CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NMAL7070BMsg scrnMsg = (NMAL7070BMsg) bMsg;
        NMAL7070CMsg bizMsg = (NMAL7070CMsg) cMsg;
        if (MESSAGE_KIND_ERROR.equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        String tempFilePath = scrnMsg.xxFileData_A.getTempFilePath();
        // Mod Start 2016/11/21 QC#16082
        String excelFileFullPath = ZYPExcelUtil.csvFileToExcel(tempFilePath);
//      executeDownload(tempFilePath, true, ZYPCSVOutFile.getDialogFileName(tempFilePath));
        executeDownload(excelFileFullPath, true, ZYPCSVOutFile.getDialogFileName(excelFileFullPath));
      // Mod End 2016/11/21 QC#16082
    }
}
