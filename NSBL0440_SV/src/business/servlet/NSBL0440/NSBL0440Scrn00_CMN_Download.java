/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0440;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSBL0440.NSBL0440CMsg;
import business.servlet.NSBL0440.constant.NSBL0440Constant;

import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.file.ZYPExcelUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/02/02   Hitachi         M.Kidokoro      Create          QC#18150
 *</pre>
 */
public class NSBL0440Scrn00_CMN_Download extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSBL0440BMsg scrnMsg = (NSBL0440BMsg) bMsg;

        NSBL0440CMsg bizMsg = new NSBL0440CMsg();
        bizMsg.setBusinessID(NSBL0440Constant.BIZ_ID);
        bizMsg.setFunctionCode(NSBL0440Constant.EZD_FUNC_CD_INQ);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSBL0440BMsg scrnMsg = (NSBL0440BMsg) bMsg;
        NSBL0440CMsg bizMsg = (NSBL0440CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {
            throw new EZDFieldErrorException();
        }

        // execute file download
        String tempFilePath = scrnMsg.xxFileData.getTempFilePath();
        String excelFilePath = ZYPExcelUtil.csvFileToExcel(tempFilePath);
        executeDownload(excelFilePath, true, ZYPCSVOutFile.getDialogFileName(excelFilePath));
    }
}
