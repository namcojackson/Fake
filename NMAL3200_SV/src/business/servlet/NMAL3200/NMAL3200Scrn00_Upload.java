/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL3200;

import static business.servlet.NMAL3200.constant.NMAL3200Constant.BIZ_APP_ID;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.BTN_CMN_BTN_2_ID;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.BTN_CMN_BTN_2_NAME;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.BTN_CMN_BTN_2_VAL;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.CONTR_ASSN_TRGT_TP_DESC_TXT;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.CSV_FILE_EXTENSION;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.DISPLAY_NM_UPLOAD_FILE;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.EVENT_NM_NMAL3200_UPLOAD;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.FUNCTION_CD_UPDATE;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.NMAM0836E;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.NMAM8617E;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.SCRN_ID;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.TAB_DOWNLOAD;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.TXT_FILE_EXTENSION;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.XLSX_FILE_EXTENSION;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.XLS_FILE_EXTENSION;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.ZZM9000E;

import java.util.Arrays;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL3200.NMAL3200CMsg;
import business.servlet.NMAL3200.common.NMAL3200CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.file.ZYPFileNameUtil;
import com.canon.cusa.s21.framework.ZYP.file.web.ZYPUploadFileValidator;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 * <pre>
 * Business ID : NMAL3200 Marketing Data Analysis
 * Function Name : Upload
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/24/2016   CITS            K.Ogino         Create          N/A
 */
public class NMAL3200Scrn00_Upload extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL3200BMsg scrnMsg = (NMAL3200BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.mktgFldMapNm_DB);
        scrnMsg.addCheckItem(scrnMsg.contrAssnTrgtTpCd_SL);

        // Extension Check
        if (scrnMsg.xxFileData.isUploaded()) {
            ZYPUploadFileValidator.isAuthorizedExtension(scrnMsg.xxFileData, Arrays.asList(new String[] {CSV_FILE_EXTENSION, TXT_FILE_EXTENSION, XLSX_FILE_EXTENSION, XLS_FILE_EXTENSION }));
        } else {
            scrnMsg.xxFileData.setErrorInfo(1, ZZM9000E, new String[] {DISPLAY_NM_UPLOAD_FILE });
            scrnMsg.addCheckItem(scrnMsg.xxFileData);
            scrnMsg.putErrorScreen();
            throw new EZDFieldErrorException();
        }

        // validate file size.
        if (!ZYPUploadFileValidator.isAllowedFileSize(scrnMsg.xxFileData, scrnMsg.numConstVal_SZ.getValueInt())) {
            scrnMsg.addCheckItem(scrnMsg.xxFileData);
            scrnMsg.putErrorScreen();
        }

        if (!ZYPCommonFunc.hasValue(scrnMsg.contrAssnTrgtTpCd_SL)) {
            scrnMsg.contrAssnTrgtTpCd_SL.setErrorInfo(1, NMAM0836E, new String[] {CONTR_ASSN_TRGT_TP_DESC_TXT });
        }

        if (!ZYPCommonFunc.hasValue(scrnMsg.exactCondFlg) //
                && !ZYPCommonFunc.hasValue(scrnMsg.prtlCondFlg) //
                && !ZYPCommonFunc.hasValue(scrnMsg.dunsCondFlg) // 
                && !ZYPCommonFunc.hasValue(scrnMsg.glnCondFlg)) {
            scrnMsg.exactCondFlg.setErrorInfo(1, NMAM8617E);
            scrnMsg.prtlCondFlg.setErrorInfo(1, NMAM8617E);
            scrnMsg.dunsCondFlg.setErrorInfo(1, NMAM8617E);
            scrnMsg.glnCondFlg.setErrorInfo(1, NMAM8617E);
        }
        scrnMsg.addCheckItem(scrnMsg.xxFileData);
        scrnMsg.addCheckItem(scrnMsg.exactCondFlg);
        scrnMsg.addCheckItem(scrnMsg.prtlCondFlg);
        scrnMsg.addCheckItem(scrnMsg.dunsCondFlg);
        scrnMsg.addCheckItem(scrnMsg.glnCondFlg);

        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL3200BMsg scrnMsg = (NMAL3200BMsg) bMsg;

        if (scrnMsg.xxFileData.isUploaded()) {
            scrnMsg.xxFileData.makeTempFile(null, ZYPFileNameUtil.createUniqueFileNm(scrnMsg.xxFileData, getUserProfileService().getContextUserInfo().getUserId()), "." + ZYPFileNameUtil.getFileEx(scrnMsg.xxFileData));
        }

        NMAL3200CMsg bizMsg = new NMAL3200CMsg();
        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_UPDATE);
        scrnMsg.mktgFldMapNm_DL.clear();
        scrnMsg.upldErrFlg_SL.clear();
        scrnMsg.mktgMapRqstProcFlg_SL.clear();
        scrnMsg.xxScrEventNm.setValue(EVENT_NM_NMAL3200_UPLOAD);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL3200BMsg scrnMsg = (NMAL3200BMsg) bMsg;
        NMAL3200CMsg bizMsg = (NMAL3200CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {
            throw new EZDFieldErrorException();
        }
        S21TableColorController tblColor = new S21TableColorController(SCRN_ID, bMsg);
        tblColor.setAlternateRowsBG("A", scrnMsg.A);

        if (scrnMsg.A.getValidCount() > 0) {
            NMAL3200CommonLogic.setAttr(this, scrnMsg);
            scrnMsg.setFocusItem(scrnMsg.A.no(0).mktgMapRqstPk_A1);
            if (ZYPCommonFunc.hasValue(scrnMsg.mktgMapRqstPk_UP)) {
                scrnMsg.mktgFldMapNm_SC.setInputProtected(true);
            } else {
                scrnMsg.mktgFldMapNm_SC.setInputProtected(false);
            }
        }
        scrnMsg.xxDplyTab.setValue(TAB_DOWNLOAD);
        this.setButtonProperties(BTN_CMN_BTN_2_ID, BTN_CMN_BTN_2_NAME, BTN_CMN_BTN_2_VAL, 0, null);
    }
}
