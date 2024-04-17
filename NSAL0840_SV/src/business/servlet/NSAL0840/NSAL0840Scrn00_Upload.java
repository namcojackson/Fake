/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0840;

import static business.servlet.NSAL0840.common.NSAL0840CommonLogic.checkInputForTable;
import static business.servlet.NSAL0840.common.NSAL0840CommonLogic.initialControlScreen;
import static business.servlet.NSAL0840.common.NSAL0840CommonLogic.setErrorInfo;
import static business.servlet.NSAL0840.constant.NSAL0840Constant.BUSINESS_ID;
import static business.servlet.NSAL0840.constant.NSAL0840Constant.FILE_EXTENSION;
import static business.servlet.NSAL0840.constant.NSAL0840Constant.FUNCTION_SEARCH;
import static business.servlet.NSAL0840.constant.NSAL0840Constant.NSAM0007E;
import static business.servlet.NSAL0840.constant.NSAL0840Constant.NSAM0404E;

import java.util.Arrays;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0840.NSAL0840CMsg;

import com.canon.cusa.s21.framework.ZYP.file.ZYPFileNameUtil;
import com.canon.cusa.s21.framework.ZYP.file.web.ZYPUploadFileValidator;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/14   Hitachi         Y.Takeno        Create          N/A
 * 2016/05/20   Hitachi         Y.Tsuchimoto    Update          QC#4061
 * 2016/09/01   Hitachi         Y.Zhang         Update          QC#11846
 *</pre>
 */
public class NSAL0840Scrn00_Upload extends S21CommonHandler {


    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0840BMsg scrnMsg = (NSAL0840BMsg) bMsg;
        if (scrnMsg.xxFileData_U.isUploaded()) {
            // START 2016/05/20 [QC#4061, MOD]
            if (!ZYPUploadFileValidator.isAuthorizedExtension(scrnMsg.xxFileData_U, Arrays.asList(FILE_EXTENSION))) {
                scrnMsg.xxFileData_U.setErrorInfo(1, NSAM0404E);
            }
            // END   2016/05/20 [QC#4061, MOD]
        } else {
            scrnMsg.xxFileData_U.setErrorInfo(1, NSAM0007E, new String[] {"Upload File" });
        }
        scrnMsg.addCheckItem(scrnMsg.xxFileData_U);
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0840BMsg scrnMsg = (NSAL0840BMsg) bMsg;
        if (scrnMsg.xxFileData_U.isUploaded()) {
            scrnMsg.xxFileData_U.makeTempFile(null, ZYPFileNameUtil.createUniqueFileNm(scrnMsg.xxFileData_U, getUserProfileService().getContextUserInfo().getUserId()), "." + ZYPFileNameUtil.getFileEx(scrnMsg.xxFileData_U));
        }

        NSAL0840CMsg bizMsg = new NSAL0840CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNCTION_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0840BMsg scrnMsg = (NSAL0840BMsg) bMsg;
        NSAL0840CMsg bizMsg = (NSAL0840CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        setErrorInfo(this, scrnMsg);

        // START 2016/09/01 Y.Zhang [QC#11846, MOD]
        checkInputForTable(this, scrnMsg);
        scrnMsg.putErrorScreen();
        initialControlScreen(this, scrnMsg);
        // END 2016/09/01 Y.Zhang [QC#11846, MOD]
        if ("E".equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }
    }
}
