/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL3000;

import static business.servlet.NMAL3000.constant.NMAL3000Constant.BIZ_ID;
import static business.servlet.NMAL3000.constant.NMAL3000Constant.MESSAGE_KIND_ERROR;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL3000.NMAL3000CMsg;
import business.servlet.NMAL3000.common.NMAL3000CommonLogic;

import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.file.ZYPExcelUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL3000Scrn00_CMN_Download
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/19   Fujitsu         M.Suzuki        Create          N/A
 * 2016/03/09   SRAA            Y.Chen          Update          QC#5169
 * 2016/11/17   Fujitsu         R.nakamura      Update          QC#16082
 *</pre>
 */
public class NMAL3000Scrn00_CMN_Download extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL3000BMsg scrnMsg = (NMAL3000BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.srchOptNm_S);
        scrnMsg.addCheckItem(scrnMsg.srchOptPk_S);
        scrnMsg.addCheckItem(scrnMsg.dsAcctCustNum);
        scrnMsg.addCheckItem(scrnMsg.mktMdlCd);
        scrnMsg.addCheckItem(scrnMsg.dsAcctDlrCd);
        scrnMsg.addCheckItem(scrnMsg.effFromDt);
        scrnMsg.addCheckItem(scrnMsg.effThruDt);
        scrnMsg.addCheckItem(scrnMsg.slsAuthFlg_SA);
        scrnMsg.addCheckItem(scrnMsg.slsAuthFlg_SE);
        scrnMsg.addCheckItem(scrnMsg.dsAcctCustNum_CO);
        scrnMsg.addCheckItem(scrnMsg.mktMdlCd_CO);
        scrnMsg.addCheckItem(scrnMsg.effFromDt_CO);
        NMAL3000CommonLogic.checkMandatory(scrnMsg);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL3000BMsg scrnMsg = (NMAL3000BMsg) bMsg;
        NMAL3000CMsg bizMsg = new NMAL3000CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL3000BMsg scrnMsg = (NMAL3000BMsg) bMsg;
        NMAL3000CMsg bizMsg  = (NMAL3000CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NMAL3000CommonLogic.initialControlScreen(getUserProfileService(), this, scrnMsg);
        if (MESSAGE_KIND_ERROR.equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }
        String tempFilePath = scrnMsg.xxFileData.getTempFilePath();
        // Mod Start 2016/11/17 QC#16082
//        executeDownload(tempFilePath, true, ZYPCSVOutFile.getDialogFileName(tempFilePath));
        String excelFileFullPath = ZYPExcelUtil.csvFileToExcel(tempFilePath);
        executeDownload(excelFileFullPath, true, ZYPCSVOutFile.getDialogFileName(excelFileFullPath));
        // Mod End 2016/11/17 QC#16082
    }
}
