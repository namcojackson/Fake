/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7200;

import static business.servlet.NMAL7200.constant.NMAL7200Constant.MESSAGE_KIND_ERROR;
import static business.servlet.NMAL7200.constant.NMAL7200Constant.NMAM0192E;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL7200.NMAL7200CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.file.ZYPExcelUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/09/05   Fujitsu         R.Nakamura      Create          QC#8222
 * 2016/11/18   Fujitsu         M.Ohno          Update          S21_NA#16082
 *</pre>
 */
public class NMAL7200Scrn00_CMN_Download extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7200BMsg scrnMsg = (NMAL7200BMsg) bMsg;

        if (!ZYPCommonFunc.hasValue(scrnMsg.xxScrItem29Txt) //
                && !ZYPCommonFunc.hasValue(scrnMsg.prcGrpNm) //
                && !ZYPCommonFunc.hasValue(scrnMsg.prcGrpDescTxt)) {
            scrnMsg.setMessageInfo(NMAM0192E);
        }
        scrnMsg.addCheckItem(scrnMsg.prcGrpDescTxt);
        scrnMsg.addCheckItem(scrnMsg.prcGrpNm);
        scrnMsg.addCheckItem(scrnMsg.xxScrItem29Txt);
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7200BMsg scrnMsg = (NMAL7200BMsg) bMsg;

        NMAL7200CMsg bizMsg = new NMAL7200CMsg();
        bizMsg.setBusinessID("NMAL7200");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7200BMsg scrnMsg = (NMAL7200BMsg) bMsg;
        NMAL7200CMsg bizMsg = (NMAL7200CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (MESSAGE_KIND_ERROR.equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }

        String tempFilePath = scrnMsg.xxFileData.getTempFilePath();
        // Mod Start 2016/11/18 M.Ohno S21_NA#16082
        String excelFileFullPath = ZYPExcelUtil.csvFileToExcel(tempFilePath);
        executeDownload(excelFileFullPath, true, ZYPCSVOutFile.getDialogFileName(excelFileFullPath));
        // executeDownload(tempFilePath, true, ZYPCSVOutFile.getDialogFileName(tempFilePath));
        // Mod End   2016/11/18 M.Ohno S21_NA#16082 

    }
}
