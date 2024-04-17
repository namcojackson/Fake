/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2320;

import java.util.Arrays;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL2320.NWAL2320CMsg;
import business.servlet.NWAL2320.constant.NWAL2320Constant;

import com.canon.cusa.s21.framework.ZYP.file.ZYPFileNameUtil;
import com.canon.cusa.s21.framework.ZYP.file.web.ZYPUploadFileValidator;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/25/2016   Fujitsu         M.Hara          Create          N/A
 *</pre>
 */
public class NWAL2320Scrn00_Upload extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL2320BMsg scrnMsg = (NWAL2320BMsg) bMsg;

        if (!scrnMsg.xxFileData_UL.isUploaded()) {
            scrnMsg.xxFileData_UL.setErrorInfo(1, "ZZM9000E", new String[] {"Upload File" });
        }

        scrnMsg.putErrorScreen();

        if (!ZYPUploadFileValidator.isAuthorizedExtension(scrnMsg.xxFileData_UL, Arrays.asList(new String[] {"csv", "txt", "xls", "xlsx" }))) {
            scrnMsg.addCheckItem(scrnMsg.xxFileData_UL);
        }

        scrnMsg.addCheckItem(scrnMsg.xxFileData_UL);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL2320BMsg scrnMsg = (NWAL2320BMsg) bMsg;

        scrnMsg.xxFileData_UL.makeTempFile(null, ZYPFileNameUtil.createUniqueFileNm(scrnMsg.xxFileData_UL, getContextUserInfo().getUserId()), "." + ZYPFileNameUtil.getFileEx(scrnMsg.xxFileData_UL));

        NWAL2320CMsg bizMsg = new NWAL2320CMsg();
        bizMsg.setBusinessID(NWAL2320Constant.BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL2320BMsg scrnMsg = (NWAL2320BMsg) bMsg;
        NWAL2320CMsg bizMsg  = (NWAL2320CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        String msgCode = scrnMsg.getMessageCode();
        if (msgCode.endsWith("E")) {
            return;
        }
        scrnMsg.setMessageInfo(NWAL2320Constant.ZZM8100I);
        scrnMsg.ordUpldTmplTpCd.setInputProtected(true);
        scrnMsg.xxFileData_UL.setInputProtected(true);
        this.setButtonEnabled(NWAL2320Constant.BTN_UPLOAD, false);
        this.setButtonEnabled(NWAL2320Constant.BTN_UPLOAD_LINES, false);

    }
}
