/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL9900;

import static business.servlet.NSAL9900.constant.NSAL9900Constant.*;

import java.util.Arrays;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL9900.NSAL9900CMsg;
import business.servlet.NSAL9900.common.NSAL9900CommonLogic;

import com.canon.cusa.s21.framework.ZYP.file.ZYPFileNameUtil;
import com.canon.cusa.s21.framework.ZYP.file.web.ZYPUploadFileValidator;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/05/28   Hitachi         T.Aoyagi        Create          N/A
 *</pre>
 */
public class NSAL9900Scrn00_UploadCsv extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL9900BMsg scrnMsg = (NSAL9900BMsg) bMsg;
        if (scrnMsg.xxFileData.isUploaded()) {
            if (!ZYPUploadFileValidator.isAuthorizedExtension(scrnMsg.xxFileData, Arrays.asList(new String[] {"csv", "txt" }))) {
                scrnMsg.xxFileData.setErrorInfo(1, ZYEM0096E);
            }
        } else {
            scrnMsg.xxFileData.setErrorInfo(1, ZZM9000E, new String[] {"Upload File" });
        }
        scrnMsg.addCheckItem(scrnMsg.xxFileData);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL9900BMsg scrnMsg = (NSAL9900BMsg) bMsg;

        if (scrnMsg.xxFileData.isUploaded()) {
            scrnMsg.xxFileData.makeTempFile(null, ZYPFileNameUtil.createUniqueFileNm(scrnMsg.xxFileData, getUserProfileService().getContextUserInfo().getUserId()), "." + ZYPFileNameUtil.getFileEx(scrnMsg.xxFileData));
        }

        NSAL9900CMsg bizMsg = NSAL9900CommonLogic.createCMsgForSearch();
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSAL9900BMsg scrnMsg = (NSAL9900BMsg) bMsg;
        NSAL9900CMsg bizMsg  = (NSAL9900CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NSAL9900CommonLogic.screenControlProcess(this, scrnMsg);
        NSAL9900CommonLogic.addCheckItem(scrnMsg);

        scrnMsg.putErrorScreen(MSG_ID_NSAM0205E, new String[0]);

        if (!"E".equals(bizMsg.getMessageKind())) {
            scrnMsg.setMessageInfo(ZZM8100I);
        }
    }
}
