/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2700;

import static business.servlet.NMAL2700.constant.NMAL2700Constant.BIZ_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL2700.NMAL2700CMsg;
import business.servlet.NMAL2700.common.NMAL2700CommonLogic;

import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.file.ZYPExcelUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL2700Scrn00_CMN_Download
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/05   Fujitsu         M.Suzuki        Create          N/A
 * 2016/11/17   Fujitsu         R.nakamura      Update          QC#16082
 *</pre>
 */
public class NMAL2700Scrn00_CMN_Download extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2700BMsg scrnMsg = (NMAL2700BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.firstOrgCd);
        scrnMsg.addCheckItem(scrnMsg.orgFuncRoleTpCd);
        scrnMsg.addCheckItem(scrnMsg.orgFuncRoleTpNm);
        scrnMsg.addCheckItem(scrnMsg.orgFuncRoleTpDescTxt);
        scrnMsg.addCheckItem(scrnMsg.mgrFlg);
        scrnMsg.addCheckItem(scrnMsg.spclFlg);
        scrnMsg.addCheckItem(scrnMsg.equipFlg);
        scrnMsg.addCheckItem(scrnMsg.cmsnFlg);
        scrnMsg.addCheckItem(scrnMsg.actvFlg);
        NMAL2700CommonLogic.checkMandator(scrnMsg);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2700BMsg scrnMsg = (NMAL2700BMsg) bMsg;
        NMAL2700CMsg bizMsg = new NMAL2700CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2700BMsg scrnMsg = (NMAL2700BMsg) bMsg;
        NMAL2700CMsg bizMsg  = (NMAL2700CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NMAL2700CommonLogic.initialControlScreen(getUserProfileService(), this, scrnMsg);
        String tempFilePath = scrnMsg.xxFileData.getTempFilePath();
        // Mod Start 2016/11/17 QC#16082
//        executeDownload(tempFilePath, true, ZYPCSVOutFile.getDialogFileName(tempFilePath));
        String excelFileFullPath = ZYPExcelUtil.csvFileToExcel(tempFilePath);
        executeDownload(excelFileFullPath, true, ZYPCSVOutFile.getDialogFileName(excelFileFullPath));
        // Mod End 2016/11/17 QC#16082
    }
}
