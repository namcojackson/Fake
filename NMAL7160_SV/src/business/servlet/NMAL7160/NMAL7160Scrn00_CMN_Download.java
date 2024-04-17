/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7160;

import static business.servlet.NMAL7160.constant.NMAL7160Constant.BIZ_ID;
import static business.servlet.NMAL7160.constant.NMAL7160Constant.FUNCTION_CD;
import static business.servlet.NMAL7160.constant.NMAL7160Constant.ZZZM9010E;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL7160.NMAL7160CMsg;
import business.servlet.NMAL7160.common.NMAL7160CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.file.ZYPExcelUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/17   Fujitsu         T.Murai         Create          N/A
 * 2016/11/21   Fujitsu         M.Ohno          Update          S21_NA#16082
 *</pre>
 */
public class NMAL7160Scrn00_CMN_Download extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NMAL7160BMsg scrnMsg = (NMAL7160BMsg) bMsg;

        if (ZYPCommonFunc.hasValue(scrnMsg.csmpInvErrDt_FR) && ZYPCommonFunc.hasValue(scrnMsg.csmpInvErrDt_TO)) {
            if (scrnMsg.csmpInvErrDt_FR.getValue().compareTo(scrnMsg.csmpInvErrDt_TO.getValue()) > 0) {
                scrnMsg.csmpInvErrDt_FR.setErrorInfo(1, ZZZM9010E, new String[] {scrnMsg.csmpInvErrDt_FR.getNameForMessage(), scrnMsg.csmpInvErrDt_TO.getNameForMessage() });
                scrnMsg.csmpInvErrDt_TO.setErrorInfo(1, ZZZM9010E, new String[] {scrnMsg.csmpInvErrDt_FR.getNameForMessage(), scrnMsg.csmpInvErrDt_TO.getNameForMessage() });
            }
        }

        NMAL7160CommonLogic.allAddCheck(scrnMsg);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7160BMsg scrnMsg = (NMAL7160BMsg) bMsg;

        NMAL7160CMsg bizMsg = new NMAL7160CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNCTION_CD);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7160BMsg scrnMsg = (NMAL7160BMsg) bMsg;
        NMAL7160CMsg bizMsg = (NMAL7160CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NMAL7160CommonLogic.allAddCheck(scrnMsg);
        if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
            throw new EZDFieldErrorException();
        }
        // execute file download
        String tempFilePath = scrnMsg.xxFileData.getTempFilePath();
        // Mod Start 2016/11/21 M.Ohno S21_NA#16082
        String excelFileFullPath = ZYPExcelUtil.csvFileToExcel(tempFilePath);
        executeDownload(excelFileFullPath, true, ZYPCSVOutFile.getDialogFileName(excelFileFullPath));
        // executeDownload(tempFilePath, true, ZYPCSVOutFile.getDialogFileName(tempFilePath));
        // Mod End   2016/11/21 M.Ohno S21_NA#16082 

    }
}
