/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0450;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import static business.servlet.NSBL0450.common.NSBL0450CommonLogic.initialControlScreen;
import static business.servlet.NSBL0450.common.NSBL0450CommonLogic.checkMandatory;
import static business.servlet.NSBL0450.constant.NSBL0450Constant.BUSINESS_ID;
import static business.servlet.NSBL0450.constant.NSBL0450Constant.FUNCTION_SEARCH;

import business.blap.NSBL0450.NSBL0450CMsg;

import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Supplemental Task
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/05   Hitachi         T.Iwamoto         Create          N/A
 *</pre>
 */
public class NSBL0450Scrn00_CMN_Download extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSBL0450BMsg scrnMsg = (NSBL0450BMsg) bMsg;
        checkMandatory(scrnMsg);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSBL0450BMsg scrnMsg = (NSBL0450BMsg) bMsg;
        NSBL0450CMsg bizMsg = new NSBL0450CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNCTION_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSBL0450BMsg scrnMsg = (NSBL0450BMsg) bMsg;
        NSBL0450CMsg bizMsg = (NSBL0450CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        initialControlScreen(this, scrnMsg);
        scrnMsg.putErrorScreen();
        if ("E".equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }
        // execute file down load
        String tempFilePath = scrnMsg.xxFileData.getTempFilePath();
        executeDownload(tempFilePath, true, ZYPCSVOutFile.getDialogFileName(tempFilePath));
    }
}
