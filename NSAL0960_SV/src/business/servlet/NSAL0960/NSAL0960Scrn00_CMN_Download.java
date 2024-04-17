/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0960;

import static business.servlet.NSAL0960.constant.NSAL0960Constant.BUSINESS_ID;
import static business.servlet.NSAL0960.constant.NSAL0960Constant.FUNCTION_SEARCH;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0960.NSAL0960CMsg;
import business.servlet.NSAL0960.common.NSAL0960CommonLogic;

import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.file.ZYPExcelUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/21   Hitachi         Y.Tsuchimoto    Create          N/A
 *</pre>
 */
public class NSAL0960Scrn00_CMN_Download extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return;
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0960BMsg scrnMsg = (NSAL0960BMsg) bMsg;

        NSAL0960CMsg bizMsg = new NSAL0960CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNCTION_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0960BMsg scrnMsg = (NSAL0960BMsg) bMsg;
        NSAL0960CMsg bizMsg = (NSAL0960CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NSAL0960CommonLogic.initialControlScreen(this, scrnMsg);
        scrnMsg.putErrorScreen();
        scrnMsg.setFocusItem(scrnMsg.dsContrVldListDescTxt_H);
        if (scrnMsg.A.getValidCount() > 0) {
            setValue(scrnMsg.xxRadioBtn_A, BigDecimal.ZERO);
        }
        if ("E".equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }
        // execute excel file down load
        String tempFilePath = scrnMsg.xxFileData_H.getTempFilePath();
        String excelFilePAth = ZYPExcelUtil.csvFileToExcel(tempFilePath);

        executeDownload(excelFilePAth, true, ZYPCSVOutFile.getDialogFileName(excelFilePAth));

    }
}
