/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0210;

import static business.servlet.NSBL0210.constant.NSBL0210Constant.*;

import java.util.Arrays;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSBL0210.NSBL0210CMsg;
import business.servlet.NSBL0210.common.NSBL0210CommonLogic;

import com.canon.cusa.s21.framework.ZYP.file.ZYPFileNameUtil;
import com.canon.cusa.s21.framework.ZYP.file.web.ZYPUploadFileValidator;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/10   Hitachi         Y.Takeno        Create          N/A
 *</pre>
 */
public class NSBL0210Scrn00_Upload extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSBL0210BMsg scrnMsg = (NSBL0210BMsg) bMsg;
        if (scrnMsg.xxFileData_U.isUploaded()) {
            ZYPUploadFileValidator.isAuthorizedExtension(scrnMsg.xxFileData_U, Arrays.asList(FILE_EXTENSIONS));
        } else {
            scrnMsg.xxFileData_U.setErrorInfo(1, NSBM0166E, new String[] {"Upload File" });
        }
        scrnMsg.addCheckItem(scrnMsg.xxFileData_U);
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSBL0210BMsg scrnMsg = (NSBL0210BMsg) bMsg;
        if (scrnMsg.xxFileData_U.isUploaded()) {
            scrnMsg.xxFileData_U.makeTempFile(null, ZYPFileNameUtil.createUniqueFileNm(scrnMsg.xxFileData_U, getUserProfileService().getContextUserInfo().getUserId()), "." + ZYPFileNameUtil.getFileEx(scrnMsg.xxFileData_U));
        }

        NSBL0210CMsg bizMsg = new NSBL0210CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNCTION_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSBL0210BMsg scrnMsg = (NSBL0210BMsg) bMsg;
        NSBL0210CMsg bizMsg = (NSBL0210CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NSBL0210CommonLogic.initialControlScreen(getUserProfileService(), this, scrnMsg, bizMsg.getUserID());
        NSBL0210CommonLogic.addCheckItemsDetail(scrnMsg);

        scrnMsg.putErrorScreen();
        if ("E".equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }
    }
}
