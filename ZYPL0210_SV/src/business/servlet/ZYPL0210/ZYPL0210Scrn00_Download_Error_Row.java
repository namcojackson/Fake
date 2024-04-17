/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.ZYPL0210;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.ZYPL0210.ZYPL0210CMsg;
//import business.servlet.ZYPL0210.constant.ZYPL0210Constant;

import business.blap.ZYPL0210.ZYPL0210CMsg;
import business.servlet.ZYPL0210.common.ZYPL0210CommonLogic;
import business.servlet.ZYPL0210.constant.ZYPL0210Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
public class ZYPL0210Scrn00_Download_Error_Row extends S21CommonHandler implements ZYPL0210Constant{

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

        int index = getButtonSelectNumber();
        bizMsg.upldCsvRqstPk_CT.setValue(scrnMsg.A.no(index).upldCsvRqstPk.getValue());

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
        int index = getButtonSelectNumber();

        String upldCsvId = null;
        if (scrnMsg.upldCsvId_CT.isClear()) {
            upldCsvId = scrnMsg.upldCsvId_0H.getValue();
        } else {
            upldCsvId = scrnMsg.upldCsvId_CT.getValue();
        }

        String dlFileName = DNLD_UPLD_FILE_NAME.replaceAll(DNLD_UPLD_FILE_VALIABLE_UPLD_ID, upldCsvId);
        dlFileName = dlFileName.replaceAll(DNLD_UPLD_FILE_VALIABLE_RQST_ID, scrnMsg.A.no(index).upldCsvRqstPk.getValue().toPlainString());
        //[MOD] FWDEF273 - C.Kim EXCEL Convert - START- 2016/03/25
        
        String excelFileFullPath = ZYPL0210CommonLogic.convertCsvToExcel(scrnMsg.xxFileData_CD.getTempFilePath(), bizMsg.xxUpldCsvFilePathTxt.getValue());
        executeDownload(excelFileFullPath, true, dlFileName);
    }
}
