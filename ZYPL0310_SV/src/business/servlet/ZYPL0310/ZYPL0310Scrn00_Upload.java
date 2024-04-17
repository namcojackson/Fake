/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.ZYPL0310;

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
import business.blap.ZYPL0310.ZYPL0310CMsg;
import business.servlet.ZYPL0310.common.ZYPL0310CommonLogic;
import business.servlet.ZYPL0310.constant.ZYPL0310Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.file.ZYPFileNameUtil;
import com.canon.cusa.s21.framework.ZYP.file.web.ZYPUploadFileValidator;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
public class ZYPL0310Scrn00_Upload extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        ZYPL0310BMsg scrnMsg = (ZYPL0310BMsg) bMsg;

        if(!scrnMsg.attDataTpCd.getValue().equals(ZYPL0310Constant.ATT_DATA_TYPE_CODE_FILE)){
            scrnMsg.xxFileData.clearErrorInfo();
        }
        scrnMsg.addCheckItem(scrnMsg.xxAttDataCmntTxt_I);
        scrnMsg.putErrorScreen();

        if(scrnMsg.attDataTpCd.getValue().equals(ZYPL0310Constant.ATT_DATA_TYPE_CODE_FILE)){
            // File Upload
            // validate ezd item attribute.
            scrnMsg.addCheckItem(scrnMsg.xxFileData);
            if(scrnMsg.xxFileData.isError()){
                scrnMsg.xxFileData.setValue(null);
            }
            scrnMsg.putErrorScreen();

            if(S21StringUtil.isNotEmpty(scrnMsg.othSysUrl.getValue())){
                scrnMsg.othSysUrl.setErrorInfo(1, "ZZM9017E", new String[]{ZYPL0310Constant.ATT_DATA_TYPE_URL, ZYPL0310Constant.ATT_DATA_TYPE_FILE});
                scrnMsg.addCheckItem(scrnMsg.othSysUrl);
                scrnMsg.xxFileData.setValue(null);
            }
            if(scrnMsg.docMgtDocId.getValueInt() != 0){
                scrnMsg.docMgtDocId.setErrorInfo(1, "ZZM9017E", new String[]{ZYPL0310Constant.ATT_DATA_TYPE_THEREFORE, ZYPL0310Constant.ATT_DATA_TYPE_FILE});
                scrnMsg.addCheckItem(scrnMsg.docMgtDocId);
                scrnMsg.xxFileData.setValue(null);
            } 
            scrnMsg.putErrorScreen();

            EZDBMimeSourceItem xxFileDataItem = scrnMsg.xxFileData;
            if (xxFileDataItem.getValue() == null || xxFileDataItem.getValue().getFileName() == null) {
                xxFileDataItem.setErrorInfo(1, "ZZM9000E", new String[] {xxFileDataItem.getNameForMessage() });
                scrnMsg.addCheckItem(xxFileDataItem);
                scrnMsg.putErrorScreen();
            }

            // validate file name.
            String filePath = scrnMsg.xxFileData.getValue().getFileName();
            String fileNm = filePath.substring(filePath.lastIndexOf(File.separator) + 1);
            try {
                new EZDMsgDefaultValidator().checkString(scrnMsg.getAttr(ZYPL0310Bean.attDataNm_I), fileNm);
            } catch (EZDValidatorException e) {
                if (e.getErrCode() == EZDCommonConst.ERROR_HANKAKUEISU) {
                    scrnMsg.xxFileData.setErrorInfo(1, "ZZM9008E", new String[] {scrnMsg.xxFileData.getNameForMessage() });
                    scrnMsg.addCheckItem(scrnMsg.xxFileData);
                    scrnMsg.xxFileData.setValue(null);
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
                        scrnMsg.xxFileData.setValue(null);
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
                        scrnMsg.xxFileData.setValue(null);
                        scrnMsg.putErrorScreen();
                    }
                }
            }

            // validate file size.
            if (!ZYPUploadFileValidator.isAllowedFileSize(scrnMsg.xxFileData, scrnMsg.numConstVal_I2.getValueInt())) {
                scrnMsg.addCheckItem(scrnMsg.xxFileData);
                scrnMsg.xxFileData.setValue(null);
                scrnMsg.putErrorScreen();
            }
        } else if (scrnMsg.attDataTpCd.getValue().equals(ZYPL0310Constant.ATT_DATA_TYPE_CODE_URL)) {
            // WebPage(URL) upload
            // validate ezd item attribute.
            if(S21StringUtil.isEmpty(scrnMsg.othSysUrl.getValue())){
                scrnMsg.othSysUrl.setErrorInfo(1, "ZZM9000E", new String[] {scrnMsg.othSysUrl.getNameForMessage() });
            }
            scrnMsg.addCheckItem(scrnMsg.othSysUrl);

            if(scrnMsg.xxFileData.getValue() != null && scrnMsg.xxFileData.getValue().getFileName() != null){
                scrnMsg.xxFileData.setErrorInfo(1, "ZZM9017E", new String[]{ZYPL0310Constant.ATT_DATA_TYPE_FILE, ZYPL0310Constant.ATT_DATA_TYPE_URL});
                scrnMsg.xxFileData.setValue(null);
                scrnMsg.addCheckItem(scrnMsg.xxFileData);
            }
            if(scrnMsg.docMgtDocId.getValueInt() != 0){
                scrnMsg.docMgtDocId.setErrorInfo(1, "ZZM9017E", new String[]{ZYPL0310Constant.ATT_DATA_TYPE_THEREFORE, ZYPL0310Constant.ATT_DATA_TYPE_URL});
                scrnMsg.addCheckItem(scrnMsg.docMgtDocId);
            } 
            scrnMsg.putErrorScreen();
        } else if (scrnMsg.attDataTpCd.getValue().equals(ZYPL0310Constant.ATT_DATA_TYPE_CODE_NOTE)) {
            // Note upload
            // validate ezd item attribute.
            if(S21StringUtil.isEmpty(scrnMsg.xxAttDataCmntTxt_I.getValue())){
                scrnMsg.xxAttDataCmntTxt_I.setErrorInfo(1, "ZZM9000E", new String[] {scrnMsg.xxAttDataCmntTxt_I.getNameForMessage() });
            }
            scrnMsg.addCheckItem(scrnMsg.xxAttDataCmntTxt_I);
            
            if(S21StringUtil.isNotEmpty(scrnMsg.othSysUrl.getValue())){
                scrnMsg.othSysUrl.setErrorInfo(1, "ZZM9017E", new String[]{ZYPL0310Constant.ATT_DATA_TYPE_URL, ZYPL0310Constant.ATT_DATA_TYPE_NOTE});
                scrnMsg.addCheckItem(scrnMsg.othSysUrl);
            }
            if(scrnMsg.xxFileData.getValue() != null && scrnMsg.xxFileData.getValue().getFileName() != null){
                scrnMsg.xxFileData.setErrorInfo(1, "ZZM9017E", new String[]{ZYPL0310Constant.ATT_DATA_TYPE_FILE, ZYPL0310Constant.ATT_DATA_TYPE_NOTE});
                scrnMsg.xxFileData.setValue(null);
                scrnMsg.addCheckItem(scrnMsg.xxFileData);
            }
            if(scrnMsg.docMgtDocId.getValueInt() != 0){
                scrnMsg.docMgtDocId.setErrorInfo(1, "ZZM9017E", new String[]{ZYPL0310Constant.ATT_DATA_TYPE_THEREFORE, ZYPL0310Constant.ATT_DATA_TYPE_NOTE});
                scrnMsg.addCheckItem(scrnMsg.docMgtDocId);
            } 
            scrnMsg.putErrorScreen();
        } else if (scrnMsg.attDataTpCd.getValue().equals(ZYPL0310Constant.ATT_DATA_TYPE_CODE_THEREFORE)) {
            // Therefore upload
            // validate ezd item attribute.
            if(scrnMsg.docMgtDocId.getValueInt() <= 0){
                scrnMsg.docMgtDocId.setErrorInfo(1, "ZZM9003E", new String[] {scrnMsg.docMgtDocId.getNameForMessage() });
            } 
            if(ZYPL0310CommonLogic.isUploadedThreforeDocId(scrnMsg)){
                scrnMsg.docMgtDocId.setErrorInfo(1, "ZYPM0205E", new String[] {String.valueOf(scrnMsg.docMgtDocId.getValueInt()) });
            }
            scrnMsg.addCheckItem(scrnMsg.docMgtDocId);

            if(S21StringUtil.isNotEmpty(scrnMsg.othSysUrl.getValue())){
                scrnMsg.othSysUrl.setErrorInfo(1, "ZZM9017E", new String[]{ZYPL0310Constant.ATT_DATA_TYPE_URL, ZYPL0310Constant.ATT_DATA_TYPE_THEREFORE});
                scrnMsg.addCheckItem(scrnMsg.othSysUrl);
            }
            if(scrnMsg.xxFileData.getValue() != null && scrnMsg.xxFileData.getValue().getFileName() != null){
                scrnMsg.xxFileData.setErrorInfo(1, "ZZM9017E", new String[]{ZYPL0310Constant.ATT_DATA_TYPE_FILE, ZYPL0310Constant.ATT_DATA_TYPE_THEREFORE});
                scrnMsg.xxFileData.setValue(null);
                scrnMsg.addCheckItem(scrnMsg.xxFileData);
            }
            scrnMsg.putErrorScreen();
        }

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        ZYPL0310BMsg scrnMsg = (ZYPL0310BMsg) bMsg;

        if (scrnMsg.attDataTpCd.getValue().equals(ZYPL0310Constant.ATT_DATA_TYPE_CODE_FILE) && scrnMsg.xxFileData.isUploaded()) {

            // File Upload
            scrnMsg.xxFileData.makeTempFile(null, ZYPFileNameUtil.createUniqueFileNm(scrnMsg.xxFileData, getContextUserInfo().getUserId()), "." + ZYPFileNameUtil.getFileEx(scrnMsg.xxFileData));

            return ZYPL0310CommonLogic.createCMsgForUpdate(scrnMsg);
        } else if (scrnMsg.attDataTpCd.getValue().equals(ZYPL0310Constant.ATT_DATA_TYPE_CODE_URL)
                   || scrnMsg.attDataTpCd.getValue().equals(ZYPL0310Constant.ATT_DATA_TYPE_CODE_NOTE)
                   || scrnMsg.attDataTpCd.getValue().equals(ZYPL0310Constant.ATT_DATA_TYPE_CODE_THEREFORE)) {
            // WebPage(URL), Note, Therefore upload
            return ZYPL0310CommonLogic.createCMsgForUpdate(scrnMsg);
        } else {
            return null;
        }
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        ZYPL0310BMsg scrnMsg = (ZYPL0310BMsg) bMsg;
        ZYPL0310CMsg bizMsg = (ZYPL0310CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (!"E".equals(bizMsg.getMessageKind())) {
            scrnMsg.xxAttDataCmntTxt_O.setValue(scrnMsg.xxAttDataCmntTxt_I.getValue());
            scrnMsg.xxFileData.clear();
            scrnMsg.xxAttDataCmntTxt_I.clear();
            scrnMsg.othSysUrl.clear();
        }

        ZYPL0310CommonLogic.setGUIComponentsProperties(this, getArgForSubScreen(), scrnMsg);

        ZYPL0310CommonLogic.setAlternateRowsBG(ZYPL0310Constant.MY_SCRN_ID, ZYPL0310Bean.A, scrnMsg);

        S21SortColumnIMGController.clearIMG(ZYPL0310Constant.MY_SCRN_ID, scrnMsg, scrnMsg.A.no(0).getBaseContents());

        scrnMsg.setFocusItem(scrnMsg.xxFileData);
    }

}
