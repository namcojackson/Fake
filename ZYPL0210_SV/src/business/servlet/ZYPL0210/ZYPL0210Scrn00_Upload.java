package business.servlet.ZYPL0210;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDCommonConst;
import parts.common.EZDMsg;
import parts.common.EZDMsgDefaultValidator;
import parts.common.EZDValidatorException;
import parts.servletcommon.EZDApplicationContext;
import business.blap.ZYPL0210.ZYPL0210CMsg;
import business.servlet.ZYPL0210.constant.ZYPL0210Constant;

import com.canon.cusa.s21.framework.ZYP.file.ZYPFileNameUtil;
import com.canon.cusa.s21.framework.ZYP.file.web.ZYPUploadFileValidator;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 * @author Administrator
 */
public class ZYPL0210Scrn00_Upload extends S21CommonHandler implements ZYPL0210Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        ZYPL0210BMsg scrnMsg = (ZYPL0210BMsg) bMsg;

        // check
        scrnMsg.addCheckItem(scrnMsg.xxFileData_CU);
        scrnMsg.addCheckItem(scrnMsg.upldCsvFileDescTxt_0H);
        
        scrnMsg.addCheckItem( scrnMsg.xxFileData_CU );
        if (!scrnMsg.xxFileData_CU.isUploaded()) {
            scrnMsg.xxFileData_CU.setErrorInfo(1, "ZZM9000E", new String[] { "Upload Request" });
        }
        scrnMsg.putErrorScreen();
        
        // validate file extensions.
        String fileExt = ZYPFileNameUtil.getFileEx(scrnMsg.xxFileData_CU);
        //[MOD] FWDEF273 - C.Kim EXCEL Convert - START- 2016/03/25
//        if (!(fileExt.equals(CSV_FILE_EXTENSION)||fileExt.equals(TXT_FILE_EXTENSION))) {
        if (!(fileExt.equals(EXCEL_FILE_EXTENSION)||fileExt.equals(CSV_FILE_EXTENSION)||fileExt.equals(TXT_FILE_EXTENSION))) {
        //[MOD] FWDEF273 - C.Kim EXCEL Convert - END- 2016/03/25
            scrnMsg.xxFileData_CU.setErrorInfo(1, "ZYEM0096E", null);
        }
        
        // validate file name.
        String filePath = scrnMsg.xxFileData_CU.getValue().getFileName();
        try {
            new EZDMsgDefaultValidator().checkString(scrnMsg.getAttr(ZYPL0210Bean.xxUpldCsvFilePathTxt), filePath);
        } catch (EZDValidatorException e) {
            if (e.getErrCode() == EZDCommonConst.ERROR_HANKAKUEISU) {
                scrnMsg.xxFileData_CU.setErrorInfo(1, "ZZM9008E", new String[] {scrnMsg.xxFileData_CU.getNameForMessage() });
                scrnMsg.addCheckItem(scrnMsg.xxFileData_CU);
                scrnMsg.putErrorScreen();
            }
        }
        // validate file size.
        if (!ZYPUploadFileValidator.isAllowedFileSize(scrnMsg.xxFileData_CU, UP_FILE_SIZE_DEF)) {
            scrnMsg.addCheckItem(scrnMsg.xxFileData_CU);
            scrnMsg.putErrorScreen();
        }
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        ZYPL0210BMsg scrnMsg = (ZYPL0210BMsg) bMsg;

        // upload
        if (scrnMsg.xxFileData_CU.isUploaded()) {
            scrnMsg.xxUpldCsvFilePathTxt.setValue(scrnMsg.xxFileData_CU.getValue().getFileName());
            scrnMsg.xxFileData_CU.makeTempFile(null, ZYPFileNameUtil.createUniqueFileNm(scrnMsg.xxFileData_CU, getContextUserInfo().getUserId()), "." + ZYPFileNameUtil.getFileEx(scrnMsg.xxFileData_CU));
        }
        
        ZYPL0210CMsg bizMsg = new ZYPL0210CMsg();
        bizMsg.setBusinessID(UPLD_CSV_BIZ_APP);
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        ZYPL0210BMsg scrnMsg = (ZYPL0210BMsg) bMsg;
        ZYPL0210CMsg bizMsg = (ZYPL0210CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // 3.set alternate rows back-ground color
        S21TableColorController tblColor = new S21TableColorController(UPLD_CSV_BIZ_APP + "Scrn00", scrnMsg);
        tblColor.setAlternateRowsBG("L", scrnMsg.A);
        tblColor.setAlternateRowsBG("R", scrnMsg.A);

        scrnMsg.upldCsvFileDescTxt_0H.clear();
    }
}
