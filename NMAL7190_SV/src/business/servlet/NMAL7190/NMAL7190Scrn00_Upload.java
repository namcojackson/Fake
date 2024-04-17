/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7190;

import static business.servlet.NMAL7190.constant.NMAL7190Constant.BIZ_ID;
import static business.servlet.NMAL7190.constant.NMAL7190Constant.NMAM0192E;
import static business.servlet.NMAL7190.constant.NMAL7190Constant.ZYPM0188E;
import static business.servlet.NMAL7190.constant.NMAL7190Constant.ZZM9000E;

import java.util.Arrays;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL7190.NMAL7190CMsg;
import business.servlet.NMAL7190.common.NMAL7190CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.file.ZYPFileNameUtil;
import com.canon.cusa.s21.framework.ZYP.file.web.ZYPUploadFileValidator;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL7190Scrn00_Upload
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/14   Fujitsu         W.Honda         Create          N/A
 * 2016/11/22   Fujitsu         M.Ohno          Update          S21_NA#16082
 *</pre>
 */
public class NMAL7190Scrn00_Upload extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7190BMsg scrnMsg = (NMAL7190BMsg) bMsg;

        NMAL7190CommonLogic.clearMandantoryCheckHeader(scrnMsg);
        NMAL7190CommonLogic.clearMandantoryCheckDetail(scrnMsg);
        NMAL7190CommonLogic.addCheckItemForHeader(scrnMsg);
        NMAL7190CommonLogic.addCheckItemForDetail(scrnMsg);
        scrnMsg.putErrorScreen();

        // Upload file exist check
        if (!scrnMsg.xxFileData_UP.isUploaded()) {
            scrnMsg.xxFileData_UP.setErrorInfo(1, ZZM9000E, new String[] {"Upload File" });
            scrnMsg.addCheckItem(scrnMsg.xxFileData_UP);
            scrnMsg.putErrorScreen();
        }

        // Upload file extension check
        // Mod Start 2016/11/21 M.Ohno S21_NA#16082
        if (!ZYPUploadFileValidator.isAuthorizedExtension(scrnMsg.xxFileData_UP, Arrays.asList(new String[] {"csv", "txt", "xls", "xlsx" }))) {
            scrnMsg.xxFileData_UP.setErrorInfo(1, ZYPM0188E, new String[] {"csv,txt,xls,xlsx"});
         // Mod End  2016/11/21 M.Ohno S21_NA#16082
            scrnMsg.addCheckItem(scrnMsg.xxFileData_UP);
            scrnMsg.putErrorScreen();
        }

        scrnMsg.addCheckItem(scrnMsg.xxFileData_UP);

        scrnMsg.addCheckItem(scrnMsg.prcGrpNm);
        scrnMsg.addCheckItem(scrnMsg.prcGrpDescTxt);
        scrnMsg.addCheckItem(scrnMsg.prcGrpTpCd);
        scrnMsg.addCheckItem(scrnMsg.effFromDt);
        scrnMsg.addCheckItem(scrnMsg.effThruDt);
        scrnMsg.addCheckItem(scrnMsg.actvFlg);
        scrnMsg.putErrorScreen();

        if (!ZYPCommonFunc.hasValue(scrnMsg.prcGrpTpCd)) {
            scrnMsg.prcGrpTpCd.setErrorInfo(1, NMAM0192E);
            scrnMsg.addCheckItem(scrnMsg.prcGrpTpCd);
            scrnMsg.putErrorScreen();
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7190BMsg scrnMsg = (NMAL7190BMsg) bMsg;

        // Upload file move to Temporarily saved folder
        scrnMsg.xxFileData_UP.makeTempFile(null
                , ZYPFileNameUtil.createUniqueFileNm(scrnMsg.xxFileData_UP, getContextUserInfo().getUserId())
                                                   , "." + ZYPFileNameUtil.getFileEx(scrnMsg.xxFileData_UP));

        NMAL7190CMsg bizMsg = new NMAL7190CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7190BMsg scrnMsg = (NMAL7190BMsg) bMsg;
        NMAL7190CMsg bizMsg  = (NMAL7190CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NMAL7190CommonLogic.controlScreen(this, scrnMsg);

        if (scrnMsg.A.getValidCount() > 0) {
            scrnMsg.setFocusItem(scrnMsg.A.no(scrnMsg.A.getValidCount() - 1).prcGrpTrgtTpCd_A1);
        }
    }
}
