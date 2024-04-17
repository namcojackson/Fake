/**
 * <pre>
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/23/2009   CUSA            K.Tajima        Create          N/A
 *</pre>
 */
package business.servlet.ZYPL0300;

import java.io.File;
import java.util.Arrays;

import parts.common.EZDBMimeSourceItem;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDCommonConst;
import parts.common.EZDMsg;
import parts.common.EZDMsgDefaultValidator;
import parts.common.EZDValidatorException;
import parts.servletcommon.EZDApplicationContext;
import business.blap.ZYPL0300.ZYPL0300CMsg;
import business.servlet.ZYPL0300.common.ZYPL0300CommonLogic;
import business.servlet.ZYPL0300.constant.ZYPL0300Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.file.ZYPFileNameUtil;
import com.canon.cusa.s21.framework.ZYP.file.web.ZYPUploadFileValidator;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class ZYPL0300Scrn00_Upload extends S21CommonHandler implements ZYPL0300Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        ZYPL0300BMsg scrnMsg = (ZYPL0300BMsg) bMsg;

        // validate ezd item attribute.
        scrnMsg.addCheckItem(scrnMsg.xxFileData);
        scrnMsg.addCheckItem(scrnMsg.attDataDescTxt_I);
        scrnMsg.putErrorScreen();

        EZDBMimeSourceItem xxFileDataItem = scrnMsg.xxFileData;
        if (xxFileDataItem.getValue() == null) {
            xxFileDataItem.setErrorInfo(1, "ZZM9000E", new String[] {xxFileDataItem.getNameForMessage() });
            scrnMsg.addCheckItem(xxFileDataItem);
            scrnMsg.putErrorScreen();
        }

        // validate file name.
        String filePath = scrnMsg.xxFileData.getValue().getFileName();
        String fileNm = filePath.substring(filePath.lastIndexOf(File.separator) + 1);
        try {
            new EZDMsgDefaultValidator().checkString(scrnMsg.getAttr(ZYPL0300Bean.attDataNm_I), fileNm);
        } catch (EZDValidatorException e) {
            if (e.getErrCode() == EZDCommonConst.ERROR_HANKAKUEISU) {
                scrnMsg.xxFileData.setErrorInfo(1, "ZZM9008E", new String[] {scrnMsg.xxFileData.getNameForMessage() });
                scrnMsg.addCheckItem(scrnMsg.xxFileData);
                scrnMsg.putErrorScreen();
            }
        }

        // validate file extension. (system authorized file
        // extensions.)
        EZDBStringItem systemNgFileExEZD = scrnMsg.varCharConstVal_I1;
        if (ZYPCommonFunc.hasValue(systemNgFileExEZD)) {
            String[] systemNgFileExs = systemNgFileExEZD.getValue().split(",");
            if (systemNgFileExs.length > 0) {
                if (ZYPUploadFileValidator.isRefusedExtension(scrnMsg.xxFileData, Arrays.asList(systemNgFileExs))) {
                    scrnMsg.addCheckItem(scrnMsg.xxFileData);
                    scrnMsg.putErrorScreen();
                }
            }
        }

        // validate file size. (applicaton authorized file
        // extensions.)
        EZDBStringItem authorizeFIleEx = scrnMsg.varCharConstVal_I2;
        if (ZYPCommonFunc.hasValue(authorizeFIleEx)) {
            String[] authorizeFIleExs = authorizeFIleEx.getValue().split(",");
            if (authorizeFIleExs.length > 0) {
                if (!ZYPUploadFileValidator.isAuthorizedExtension(scrnMsg.xxFileData, Arrays.asList(authorizeFIleExs))) {
                    scrnMsg.addCheckItem(scrnMsg.xxFileData);
                    scrnMsg.putErrorScreen();
                }
            }
        }

        // validate file size.
        if (!ZYPUploadFileValidator.isAllowedFileSize(scrnMsg.xxFileData, scrnMsg.numConstVal_I2.getValueInt())) {
            scrnMsg.addCheckItem(scrnMsg.xxFileData);
            scrnMsg.putErrorScreen();
        }

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        ZYPL0300BMsg scrnMsg = (ZYPL0300BMsg) bMsg;

        if (scrnMsg.xxFileData.isUploaded()) {

            scrnMsg.xxFileData.makeTempFile(null, ZYPFileNameUtil.createUniqueFileNm(scrnMsg.xxFileData, getContextUserInfo().getUserId()), "." + ZYPFileNameUtil.getFileEx(scrnMsg.xxFileData));

            return ZYPL0300CommonLogic.createCMsgForUpdate(scrnMsg);

        } else {
            return null;
        }
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        ZYPL0300BMsg scrnMsg = (ZYPL0300BMsg) bMsg;
        ZYPL0300CMsg bizMsg = (ZYPL0300CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (!"E".equals(bizMsg.getMessageKind())) {
            scrnMsg.attDataDescTxt_O.setValue(scrnMsg.attDataDescTxt_I.getValue());
            scrnMsg.xxFileData.clear();
            scrnMsg.attDataDescTxt_I.clear();
        }

        ZYPL0300CommonLogic.setGUIComponentsProperties(this, getArgForSubScreen(), scrnMsg);

        ZYPL0300CommonLogic.setAlternateRowsBG(MY_SCRN_ID, ZYPL0300Bean.A, scrnMsg);

        S21SortColumnIMGController.clearIMG(ZYPL0300Constant.MY_SCRN_ID, scrnMsg, scrnMsg.A.no(0).getBaseContents());

        scrnMsg.setFocusItem(scrnMsg.xxFileData);
    }

}
