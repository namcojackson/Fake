/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7230;

import static business.servlet.NMAL7230.constant.NMAL7230Constant.BIZ_ID;
import static business.servlet.NMAL7230.constant.NMAL7230Constant.MESSAGE_KIND_ERROR;
import static business.servlet.NMAL7230.constant.NMAL7230Constant.NZZM0002I;
import static business.servlet.NMAL7230.constant.NMAL7230Constant.ZYPM0188E;
import static business.servlet.NMAL7230.constant.NMAL7230Constant.ZZM9000E;

import java.util.Arrays;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL7230.NMAL7230CMsg;
import business.servlet.NMAL7230.common.NMAL7230CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.file.ZYPFileNameUtil;
import com.canon.cusa.s21.framework.ZYP.file.web.ZYPUploadFileValidator;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL7230Scrn00_Upload
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/17   Fujitsu         W.Honda         Create          N/A
 * 2016/11/21   Fujitsu         M.Ohno          Update          S21_NA#16082
 *</pre>
 */
public class NMAL7230Scrn00_Upload extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7230BMsg scrnMsg = (NMAL7230BMsg) bMsg;

        NMAL7230CommonLogic.clearMandantoryCheckDetail(scrnMsg);
        NMAL7230CommonLogic.addCheckItemForHeader(scrnMsg);
        NMAL7230CommonLogic.addCheckItemForDetail(scrnMsg);
        scrnMsg.putErrorScreen();

        // Upload file exist check
        if (!scrnMsg.xxFileData_UP.isUploaded()) {
            scrnMsg.xxFileData_UP.setErrorInfo(1, ZZM9000E, new String[] {"Upload File" });
            scrnMsg.addCheckItem(scrnMsg.xxFileData_UP);
            scrnMsg.putErrorScreen();
        }

        // Upload file extension check
        // Mod Start 2016/11/18 M.Ohno S21_NA#16082
        if (!ZYPUploadFileValidator.isAuthorizedExtension(scrnMsg.xxFileData_UP, Arrays.asList(new String[] {"csv", "txt", "xls", "xlsx" }))) {
            scrnMsg.xxFileData_UP.setErrorInfo(1, ZYPM0188E, new String[] {"csv,txt,xls,xlsx"});
         // Mod End  2016/11/18 M.Ohno S21_NA#16082
            scrnMsg.addCheckItem(scrnMsg.xxFileData_UP);
            scrnMsg.putErrorScreen();
        }

        scrnMsg.addCheckItem(scrnMsg.xxFileData_UP);
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7230BMsg scrnMsg = (NMAL7230BMsg) bMsg;

        // Upload file move to Temporarily saved folder
        scrnMsg.xxFileData_UP.makeTempFile(null
                , ZYPFileNameUtil.createUniqueFileNm(scrnMsg.xxFileData_UP, getContextUserInfo().getUserId())
                                                   , "." + ZYPFileNameUtil.getFileEx(scrnMsg.xxFileData_UP));

        NMAL7230CMsg bizMsg = new NMAL7230CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7230BMsg scrnMsg = (NMAL7230BMsg) bMsg;
        NMAL7230CMsg bizMsg  = (NMAL7230CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NMAL7230CommonLogic.controlScreen(this, scrnMsg);

        if (MESSAGE_KIND_ERROR.equals(bizMsg.getMessageKind())) {
            NMAL7230CommonLogic.addCheckItemForHeader(scrnMsg);
            NMAL7230CommonLogic.addCheckItemForDetail(scrnMsg);
            scrnMsg.putErrorScreen();
            throw new EZDFieldErrorException();
        }

        if (0 < scrnMsg.A.getValidCount()) {
            scrnMsg.setFocusItem(scrnMsg.A.no(scrnMsg.A.getValidCount() - 1).lineBizTpDescTxt_A1);
        } else {
            scrnMsg.setFocusItem(scrnMsg.lineBizTpCd);
        }

        if (!ZYPCommonFunc.hasValue(scrnMsg.getMessageCode())) {
            scrnMsg.setMessageInfo(NZZM0002I);
        }
    }
}
