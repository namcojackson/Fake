/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL0640;

import static business.servlet.NLCL0640.constant.NLCL0640Constant.BIZ_APP_ID;
import static business.servlet.NLCL0640.constant.NLCL0640Constant.FILE_EXT_CSV;
import static business.servlet.NLCL0640.constant.NLCL0640Constant.FILE_EXT_SUPPORTED;
import static business.servlet.NLCL0640.constant.NLCL0640Constant.FILE_EXT_TXT;
import static business.servlet.NLCL0640.constant.NLCL0640Constant.FILE_EXT_XLS;
import static business.servlet.NLCL0640.constant.NLCL0640Constant.FILE_EXT_XLSX;
import static business.servlet.NLCL0640.constant.NLCL0640Constant.FUNCTION_CD_SEARCH;
import static business.servlet.NLCL0640.constant.NLCL0640Constant.ZYEM0004E;
import static business.servlet.NLCL0640.constant.NLCL0640Constant.ZYPM0188E;

import java.util.Arrays;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NLCL0640.NLCL0640CMsg;
//import business.servlet.NLCL0640.constant.NLCL0640Constant;

import business.blap.NLCL0640.NLCL0640CMsg;
import business.servlet.NLCL0640.common.NLCL0640CommonLogic;

import com.canon.cusa.s21.framework.ZYP.file.ZYPFileNameUtil;
import com.canon.cusa.s21.framework.ZYP.file.web.ZYPUploadFileValidator;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/09/2018   CITS            Y.Iwasaki       Update          QC#10572
 *</pre>
 */
public class NLCL0640Scrn00_Import_CountItems extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLCL0640BMsg scrnMsg = (NLCL0640BMsg) bMsg;

        if (scrnMsg.xxFileData_UP.isUploaded()) {
            if (!ZYPUploadFileValidator.isAuthorizedExtension(scrnMsg.xxFileData_UP, Arrays.asList(new String[] {FILE_EXT_CSV, FILE_EXT_TXT, FILE_EXT_XLS, FILE_EXT_XLSX }))) {
                scrnMsg.xxFileData_UP.setErrorInfo(1, ZYPM0188E, new String[] {FILE_EXT_SUPPORTED });
                scrnMsg.setMessageInfo(ZYPM0188E, new String[] {FILE_EXT_SUPPORTED });
            }
        } else {
            scrnMsg.xxFileData_UP.setErrorInfo(1, ZYEM0004E);
            scrnMsg.setMessageInfo(ZYEM0004E);
        }

        scrnMsg.addCheckItem(scrnMsg.xxFileData_UP);
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLCL0640BMsg scrnMsg = (NLCL0640BMsg) bMsg;

        if (scrnMsg.xxFileData_UP.isUploaded()) {
            scrnMsg.xxFileData_UP.makeTempFile(
                null,
                ZYPFileNameUtil.createUniqueFileNm(scrnMsg.xxFileData_UP,
                        getUserProfileService().getContextUserInfo().getUserId()),
                "." + ZYPFileNameUtil.getFileEx(scrnMsg.xxFileData_UP)
            );
        }

        NLCL0640CMsg bizMsg = new NLCL0640CMsg();
        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

        //return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLCL0640BMsg scrnMsg = (NLCL0640BMsg) bMsg;
        NLCL0640CMsg bizMsg  = (NLCL0640CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if ("E".equals(bizMsg.getMessageKind())) {
            scrnMsg.addCheckItem(scrnMsg.xxFileData_UP);
            scrnMsg.putErrorScreen();
            return;
        }

        NLCL0640CommonLogic.ctrlScrnItemDisp(this, scrnMsg);
    }
}
