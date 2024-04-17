/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2800;

import static business.servlet.NMAL2800.constant.NMAL2800Constant.BIZ_ID;
import static business.servlet.NMAL2800.constant.NMAL2800Constant.EXT;
import static business.servlet.NMAL2800.constant.NMAL2800Constant.FILE_SIZE;
import static business.servlet.NMAL2800.constant.NMAL2800Constant.FUNC_CD_UPD;
import static business.servlet.NMAL2800.constant.NMAL2800Constant.MESSAGE_KIND_ERROR;
import static business.servlet.NMAL2800.constant.NMAL2800Constant.MODE;
import static business.servlet.NMAL2800.constant.NMAL2800Constant.MODE_CD_SRCH;
import static business.servlet.NMAL2800.constant.NMAL2800Constant.MODE_CD_UPL;
import static business.servlet.NMAL2800.constant.NMAL2800Constant.MODE_UPL;
import static business.servlet.NMAL2800.constant.NMAL2800Constant.NMAM0052E;
import static business.servlet.NMAL2800.constant.NMAL2800Constant.NMAM8552E;
import static business.servlet.NMAL2800.constant.NMAL2800Constant.NMAM8553E;
import static business.servlet.NMAL2800.constant.NMAL2800Constant.NZZM0006E;
import static business.servlet.NMAL2800.constant.NMAL2800Constant.SCRN_ID_00;
import static business.servlet.NMAL2800.constant.NMAL2800Constant.TXT;
import static business.servlet.NMAL2800.constant.NMAL2800Constant.XLS;
import static business.servlet.NMAL2800.constant.NMAL2800Constant.XLSX;
import static business.servlet.NMAL2800.constant.NMAL2800Constant.ZZM9000E;

import java.util.Arrays;

import parts.common.EZDBMimeSourceItem;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL2800.NMAL2800CMsg;
import business.servlet.NMAL2800.common.NMAL2800CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.file.ZYPFileNameUtil;
import com.canon.cusa.s21.framework.ZYP.file.web.ZYPUploadFileValidator;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL2800Scrn00_Upload
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/16   Fujitsu         C.Tanaka        Create          N/A
 *</pre>
 */
public class NMAL2800Scrn00_Upload extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2800BMsg scrnMsg = (NMAL2800BMsg) bMsg;
        EZDBMimeSourceItem fileData = scrnMsg.xxFileData_U;

        if (!ZYPCommonFunc.hasValue(scrnMsg.xxTpCd_H1)) {
            scrnMsg.xxTpCd_H1.setErrorInfo(1, ZZM9000E, new String[] {MODE });
        } else if (MODE_CD_SRCH.equals(scrnMsg.xxTpCd_H1.getValue())) {
            scrnMsg.xxTpCd_H1.setErrorInfo(1, NMAM8552E, new String[] {MODE_UPL });
        } else if (MODE_CD_UPL.equals(scrnMsg.xxTpCd_H1.getValue()) && scrnMsg.A.getValidCount() > 0) {
            scrnMsg.setMessageInfo(NMAM8553E);
        } else if (fileData.isUploaded()) {
            // Mod Start 2016/11/17 QC#16082
//            if (!ZYPUploadFileValidator.isAuthorizedExtension(fileData, Arrays.asList(new String[] {EXT }))) {
            if (!ZYPUploadFileValidator.isAuthorizedExtension(fileData, Arrays.asList(new String[] {TXT, EXT, XLSX, XLS }))) {
            // Mod End 2016/11/17 QC#16082
                scrnMsg.setMessageInfo(NMAM0052E);
            }
            if (!ZYPUploadFileValidator.isAllowedFileSize(fileData, FILE_SIZE)) {
                scrnMsg.setMessageInfo(NZZM0006E, new String[] {Integer.toString(FILE_SIZE) });
            }
        }

        scrnMsg.addCheckItem(scrnMsg.xxTpCd_H1);
        scrnMsg.addCheckItem(fileData);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2800BMsg scrnMsg = (NMAL2800BMsg) bMsg;

        if (ZYPCommonFunc.hasValue(scrnMsg.getMessageCode())) {
            throw new EZDFieldErrorException();
        }

        EZDBMimeSourceItem fileData = scrnMsg.xxFileData_U;
        if (fileData.isUploaded()) {
            fileData.makeTempFile(null, ZYPFileNameUtil.createUniqueFileNm(fileData, //
                    getContextUserInfo().getUserId()), "." + ZYPFileNameUtil.getFileEx(fileData));
        }

        NMAL2800CMsg bizMsg = new NMAL2800CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_UPD);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2800BMsg scrnMsg = (NMAL2800BMsg) bMsg;
        NMAL2800CMsg bizMsg = (NMAL2800CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.addCheckItem(scrnMsg.xxFileData_U);
        scrnMsg.putErrorScreen();

        if (MESSAGE_KIND_ERROR.equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }

        NMAL2800CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.A, "A_Left");
        NMAL2800CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.A, "A_Right");
        NMAL2800CommonLogic.ctrlDtlFieldProp(scrnMsg, true);
        S21SortColumnIMGController.clearIMG(SCRN_ID_00, scrnMsg, scrnMsg.A.no(0).getBaseContents());

        scrnMsg.setFocusItem(scrnMsg.xxTpCd_H1);

        NMAL2800CommonLogic.addCheckItem(scrnMsg);
    }
}
