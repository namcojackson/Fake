/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7090;

import static business.servlet.NMAL7090.constant.NMAL7090Constant.BIZ_ID;
import static business.servlet.NMAL7090.constant.NMAL7090Constant.EVENT_UPLOAD;
import static business.servlet.NMAL7090.constant.NMAL7090Constant.FUNCTION_CD_SEARCH;
import static business.servlet.NMAL7090.constant.NMAL7090Constant.PRAM_UPLOAD_FILE_EXTENSION_CSV;
import static business.servlet.NMAL7090.constant.NMAL7090Constant.PRAM_UPLOAD_FILE_EXTENSION_TXT;
import static business.servlet.NMAL7090.constant.NMAL7090Constant.PRAM_UPLOAD_FILE_EXTENSION_XLS;
import static business.servlet.NMAL7090.constant.NMAL7090Constant.PRAM_UPLOAD_FILE_EXTENSION_XLSX;
import static business.servlet.NMAL7090.constant.NMAL7090Constant.ZZM9000E;

import java.util.Arrays;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL7090.NMAL7090CMsg;
import business.servlet.NMAL7090.common.NMAL7090CommonLogic;

import com.canon.cusa.s21.framework.ZYP.file.ZYPFileNameUtil;
import com.canon.cusa.s21.framework.ZYP.file.web.ZYPUploadFileValidator;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID  : NMAL7090 Item Supersessions Mass Add
 * Function Name: Upload
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/28   CITS            S.Tanikawa      Create          N/A
 * 2016/11/22   Fujitsu         R.Nakamura      Update          QC#16082
 *</pre>
 */
public class NMAL7090Scrn00_Upload extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7090BMsg scrnMsg = (NMAL7090BMsg) bMsg;

        NMAL7090CommonLogic.addCheckTableB(scrnMsg, false);

        scrnMsg.addCheckItem(scrnMsg.xxFileData);
        scrnMsg.putErrorScreen();

        if (scrnMsg.xxFileData.isUploaded()) {
            // Mod Start 2016/11/22 QC#16082
//            ZYPUploadFileValidator.isAuthorizedExtension(scrnMsg.xxFileData, Arrays.asList(new String[] {PRAM_UPLOAD_FILE_EXTENSION_CSV }));
            ZYPUploadFileValidator.isAuthorizedExtension(scrnMsg.xxFileData, Arrays.asList(new String[] {PRAM_UPLOAD_FILE_EXTENSION_TXT, PRAM_UPLOAD_FILE_EXTENSION_CSV, PRAM_UPLOAD_FILE_EXTENSION_XLSX, PRAM_UPLOAD_FILE_EXTENSION_XLS }));
            // Mod End 2016/11/22 QC#16082
        } else {
            scrnMsg.xxFileData.setErrorInfo(1, ZZM9000E, new String[] {"Upload File" });
        }

        scrnMsg.addCheckItem(scrnMsg.xxFileData);
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7090BMsg scrnMsg = (NMAL7090BMsg) bMsg;

        if (scrnMsg.xxFileData.isUploaded()) {
            scrnMsg.xxFileData.makeTempFile(null, ZYPFileNameUtil.createUniqueFileNm(scrnMsg.xxFileData, getUserProfileService().getContextUserInfo().getUserId()), "." + ZYPFileNameUtil.getFileEx(scrnMsg.xxFileData));
        }

        NMAL7090CMsg bizMsg = new NMAL7090CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7090BMsg scrnMsg = (NMAL7090BMsg) bMsg;
        NMAL7090CMsg bizMsg = (NMAL7090CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NMAL7090CommonLogic.setScrnProp(this, scrnMsg, EVENT_UPLOAD);

        // set focus
        if (scrnMsg.B.getValidCount() > 0) {
            scrnMsg.setFocusItem(scrnMsg.B.no(scrnMsg.B.getValidCount() - 1).oldMdseCd_B);
        }
    }
}
