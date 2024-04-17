/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0270;

import static business.servlet.NSBL0270.constant.NSBL0270Constant.*;

import java.util.Arrays;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSBL0270.NSBL0270CMsg;
import business.servlet.NSBL0270.common.NSBL0270CommonLogic;

import com.canon.cusa.s21.framework.ZYP.file.ZYPFileNameUtil;
import com.canon.cusa.s21.framework.ZYP.file.web.ZYPUploadFileValidator;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/18   Hitachi         K.Kasai         Create          N/A
 *</pre>
 */
public class NSBL0270Scrn00_Upload extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSBL0270BMsg scrnMsg = (NSBL0270BMsg) bMsg;
        if (scrnMsg.xxFileData.isUploaded()) {
            if (!ZYPUploadFileValidator.isAuthorizedExtension(scrnMsg.xxFileData, Arrays.asList(FILE_EXTENSION))) {
                scrnMsg.xxFileData.setErrorInfo(1, NSAM0404E);
            }
        } else {
            scrnMsg.xxFileData.setErrorInfo(1, NSAM0007E, new String[] {"Upload File" });
        }
        scrnMsg.putErrorScreen();
        if (scrnMsg.xxFileData.isUploaded()) {
            if (!ZYPUploadFileValidator.isAllowedFileSize(scrnMsg.xxFileData, 1)) {
                scrnMsg.addCheckItem(scrnMsg.xxFileData);
            }
        }
        scrnMsg.putErrorScreen();
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NSBL0270CommonLogic.checkFormatTm(scrnMsg.A.no(i));
        }
        scrnMsg.addCheckItem(scrnMsg.xxFileData);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSBL0270BMsg scrnMsg = (NSBL0270BMsg) bMsg;
        if (scrnMsg.xxFileData.isUploaded()) {
            scrnMsg.xxFileData.makeTempFile(null, ZYPFileNameUtil.createUniqueFileNm(scrnMsg.xxFileData, getUserProfileService().getContextUserInfo().getUserId()), "." + ZYPFileNameUtil.getFileEx(scrnMsg.xxFileData));
        }

        NSBL0270CMsg bizMsg = new NSBL0270CMsg();
        bizMsg.setBusinessID("NSBL0270");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSBL0270BMsg scrnMsg = (NSBL0270BMsg) bMsg;
        NSBL0270CMsg bizMsg  = (NSBL0270CMsg) cMsg;

        NSBL0270CommonLogic.focusItem(scrnMsg);
        NSBL0270CommonLogic.formatItem(scrnMsg);
        if ("E".equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        List<String> functionList = getUserProfileService().getFunctionCodeListForBizAppId(BIZ_ID);
        scrnMsg.clearAllGUIAttribute(SCR_ID_00);
        NSBL0270CommonLogic.activateButtons(this, scrnMsg, functionList);
        NSBL0270CommonLogic.activateScreenItems(this, functionList, scrnMsg);
        NSBL0270CommonLogic.alternateTableRowColor(scrnMsg);
        NSBL0270CommonLogic.focusItem(scrnMsg);
        NSBL0270CommonLogic.formatItem(scrnMsg);
        NSBL0270CommonLogic.setSplitTblFocus(scrnMsg);

    }
}
