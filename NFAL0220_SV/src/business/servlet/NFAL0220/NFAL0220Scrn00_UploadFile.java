/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFAL0220;

import static business.servlet.NFAL0220.constant.NFAL0220Constant.*;

import java.util.Arrays;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;

import parts.servletcommon.EZDApplicationContext;

import business.blap.NFAL0220.NFAL0220CMsg;
import business.servlet.NFAL0220.common.NFAL0220CommonLogic;

import com.canon.cusa.s21.framework.ZYP.file.ZYPFileNameUtil;
import com.canon.cusa.s21.framework.ZYP.file.web.ZYPUploadFileValidator;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NFAL0220Scrn00_UploadFile
 * Manual Journal Entry
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/03   Hitachi         J.Kim           Create          N/A
 * 2016/08/04   Hitachi         J.Kim           Create          QC#12851
 *</pre>
 */
public class NFAL0220Scrn00_UploadFile extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFAL0220BMsg scrnMsg = (NFAL0220BMsg) bMsg;
        if (scrnMsg.xxFileData.isUploaded()) {
            ZYPUploadFileValidator.isAuthorizedExtension(scrnMsg.xxFileData, Arrays.asList(FILE_EXTENSION));
        } else {
            scrnMsg.xxFileData.setErrorInfo(1, NFAM0173E, new String[] {"Upload File" });
        }
        scrnMsg.addCheckItem(scrnMsg.xxFileData);
        scrnMsg.putErrorScreen();
        if (scrnMsg.xxFileData.isUploaded()) {
            if (!ZYPUploadFileValidator.isAllowedFileSize(scrnMsg.xxFileData, 1)) {
                scrnMsg.addCheckItem(scrnMsg.xxFileData);
            }
        }
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFAL0220BMsg scrnMsg = (NFAL0220BMsg) bMsg;

        if (scrnMsg.xxFileData.isUploaded()) {
            scrnMsg.xxFileData.makeTempFile(null, ZYPFileNameUtil.createUniqueFileNm(scrnMsg.xxFileData, getUserProfileService().getContextUserInfo().getUserId()), "." + ZYPFileNameUtil.getFileEx(scrnMsg.xxFileData));
        }

        NFAL0220CMsg bizMsg = NFAL0220CommonLogic.setRequestData_SearchCommon();

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFAL0220BMsg scrnMsg = (NFAL0220BMsg) bMsg;
        NFAL0220CMsg bizMsg = (NFAL0220CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // START 2016/08/04 J.Kim [QC#12851,ADD]
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NFAL0220_ABMsg abMsg = scrnMsg.A.no(i);
            scrnMsg.addCheckItem(abMsg.xxChkBox_A);
            scrnMsg.addCheckItem(abMsg.xxScrItem61Txt_A);
            scrnMsg.addCheckItem(abMsg.jrnlDealAmt_A1);
            scrnMsg.addCheckItem(abMsg.jrnlDealAmt_A2);
            scrnMsg.addCheckItem(abMsg.manJrnlLineTxt_A);
            scrnMsg.addCheckItem(abMsg.dsAcctNum_A);
            scrnMsg.addCheckItem(abMsg.serNum_A);
            scrnMsg.addCheckItem(abMsg.jrnlEntrySrcTpCd_LS);
            scrnMsg.addCheckItem(abMsg.jrnlEntrySrcValTxt_A);
        }
        // END 2016/08/04 J.Kim [QC#12851,ADD]
        scrnMsg.putErrorScreen();
        if (MESSAGE_ERROR.equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }
    }
}
