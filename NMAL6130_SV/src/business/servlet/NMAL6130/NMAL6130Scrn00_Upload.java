/**
 * <pre>
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/23/2009   CUSA            K.Tajima        Create          N/A
 *</pre>
 */
package business.servlet.NMAL6130;

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
import business.blap.NMAL6130.NMAL6130CMsg;
import business.servlet.NMAL6130.common.NMAL6130CommonLogic;
import business.servlet.NMAL6130.constant.NMAL6130Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.file.ZYPFileNameUtil;
import com.canon.cusa.s21.framework.ZYP.file.web.ZYPUploadFileValidator;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class NMAL6130Scrn00_Upload extends S21CommonHandler implements NMAL6130Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL6130BMsg scrnMsg = (NMAL6130BMsg) bMsg;

        // validate ezd item attribute.
        scrnMsg.addCheckItem(scrnMsg.xxFileData);
        scrnMsg.addCheckItem(scrnMsg.mstrAttDataDescTxt_I);
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
            new EZDMsgDefaultValidator().checkString(scrnMsg.getAttr(NMAL6130Bean.mstrAttDataNm_I), fileNm);
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

        NMAL6130BMsg scrnMsg = (NMAL6130BMsg) bMsg;

        if (scrnMsg.xxFileData.isUploaded()) {

            scrnMsg.xxFileData.makeTempFile(null, ZYPFileNameUtil.createUniqueFileNm(scrnMsg.xxFileData, getContextUserInfo().getUserId()), "." + ZYPFileNameUtil.getFileEx(scrnMsg.xxFileData));

            return NMAL6130CommonLogic.createCMsgForUpdate(scrnMsg);

        } else {
            return null;
        }
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6130BMsg scrnMsg = (NMAL6130BMsg) bMsg;
        NMAL6130CMsg bizMsg = (NMAL6130CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (!"E".equals(bizMsg.getMessageKind())) {
            scrnMsg.mstrAttDataDescTxt_O.setValue(scrnMsg.mstrAttDataDescTxt_I.getValue());
            scrnMsg.xxFileData.clear();
            scrnMsg.mstrAttDataDescTxt_I.clear();
        }

        NMAL6130CommonLogic.setGUIComponentsProperties(this, getArgForSubScreen(), scrnMsg);

        NMAL6130CommonLogic.setAlternateRowsBG(MY_SCRN_ID, NMAL6130Bean.A, scrnMsg);

        S21SortColumnIMGController.clearIMG(NMAL6130Constant.MY_SCRN_ID, scrnMsg, scrnMsg.A.no(0).getBaseContents());

        scrnMsg.setFocusItem(scrnMsg.xxFileData);
    }

}
