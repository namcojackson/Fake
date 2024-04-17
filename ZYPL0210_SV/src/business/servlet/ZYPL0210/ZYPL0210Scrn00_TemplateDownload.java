package business.servlet.ZYPL0210;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.ZYPL0210.ZYPL0210CMsg;
import business.servlet.ZYPL0210.common.ZYPL0210CommonLogic;
import business.servlet.ZYPL0210.constant.ZYPL0210Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * ZYPL0210Scrn00_TemplateDownload
 */
public class ZYPL0210Scrn00_TemplateDownload extends S21CommonHandler implements ZYPL0210Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // none
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        ZYPL0210BMsg scrnMsg = (ZYPL0210BMsg) bMsg;

        ZYPL0210CMsg bizMsg = new ZYPL0210CMsg();
        bizMsg.setBusinessID(UPLD_CSV_BIZ_APP);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        // 1.
        ZYPL0210BMsg scrnMsg = (ZYPL0210BMsg) bMsg;
        ZYPL0210CMsg bizMsg = (ZYPL0210CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if ("E".equals(cMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }

        // 2.
        String upldCsvId = null;
        if (scrnMsg.upldCsvId_CT.isClear()) {
            upldCsvId = scrnMsg.upldCsvId_0H.getValue();
        } else {
            upldCsvId = scrnMsg.upldCsvId_CT.getValue();
        }

        String dlFileName = TMPL_FILE_NAME.replaceAll(TMPL_FILE_VALIABLE, upldCsvId);
        
        String excelFileFullPath = ZYPL0210CommonLogic.convertCsvToExcel(scrnMsg.xxFileData_TD.getTempFilePath(), bizMsg.xxUpldCsvFilePathTxt.getValue());

        //[MOD] FWDEF273 - C.Kim EXCEL Convert - START- 2016/03/25
        // String excelFileFullPath = ZYPExcelUtil.csvFileToExcel(scrnMsg.xxFileData_TD.getTempFilePath());
        //  executeDownload(scrnMsg.xxFileData_TD.getTempFilePath(), true, dlFileName);
        executeDownload(excelFileFullPath, true, dlFileName);
        //[MOD] FWDEF273 - C.Kim EXCEL Convert - END - 2016/03/25
    }

}
