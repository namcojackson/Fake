/**
 * <pre>
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/23/2009   CUSA            K.Tajima        Create          N/A
 *</pre>
 */
package business.blap.ZYPL0300;

import java.io.File;
import java.util.List;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.ZYPL0300.constant.ZYPL0300Constant;
import business.db.ATT_DATATMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPBLOBAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.file.ZYPFileNameUtil;
import com.canon.cusa.s21.framework.ZYP.file.ZYPFileReader;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

public class ZYPL0300BL06 extends S21BusinessHandler implements ZYPL0300Constant {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {

            String screenAplID = cMsg.getScreenAplID();

            if ("ZYPL0300Scrn00_Upload".equals(screenAplID)) {
                doProcess_ZYPL0300Scrn00_Upload((ZYPL0300CMsg) cMsg);

            } else if ("ZYPL0300Scrn00_Delete".equals(screenAplID)) {
                doProcess_ZYPL0300Scrn00_Delete((ZYPL0300CMsg) cMsg);

            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * business logic to [Upload attachment file] on NWAL0370Scrn00.
     * @param bizMsg ZYPL0300CMsg
     */
    private void doProcess_ZYPL0300Scrn00_Upload(ZYPL0300CMsg bizMsg) {

        // upload file
        String uploadFilePath = bizMsg.xxFileData.getTempFilePath();
        byte[] uploadFileData = ZYPFileReader.getByteArray(uploadFilePath);
        bizMsg.xxFileData.deleteTempFile();

        // ++++++++++++++++++++++++++++++++++++++++++++++++++
        // + create attachment record
        // ++++++++++++++++++++++++++++++++++++++++++++++++++
        ATT_DATATMsg attDataTMsg = new ATT_DATATMsg();
        attDataTMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
        attDataTMsg.attDataPk.setValue(ZYPOracleSeqAccessor.getNumberBigDecimal("ATT_DATA_SQ"));
        attDataTMsg.ezBusinessID.setValue(bizMsg.ezBusinessID_I.getValue());
        attDataTMsg.attDataGrpTxt.setValue(bizMsg.attDataGrpTxt_I.getValue());
        attDataTMsg.attDataNm.setValue(getUploadFileNm(uploadFilePath));
        attDataTMsg.attDataVol.setValue(uploadFileData.length);
        attDataTMsg.attDataDescTxt.setValue(bizMsg.attDataDescTxt_I.getValue());

        EZDTBLAccessor.create(attDataTMsg);

        // ### SUCCESS
        if (RTNCD_NORMAL.equals(attDataTMsg.getReturnCode())) {

            // Update BLOB
            int resultRows = new ZYPBLOBAccessor(attDataTMsg).updateBLOB(DB_COL_BLOB, uploadFileData);
            if (resultRows != 1) {
                bizMsg.setMessageInfo(MSG_ID_UNKNOWN_ERROR);
            }
            return;

            // ### ERROR
        } else {
            if (RTNCD_DUPLICATE.equals(attDataTMsg.getReturnCode())) {
                bizMsg.setMessageInfo(MSG_ID_ATTACHMENT_DUPLICATE);
            } else {
                bizMsg.setMessageInfo(MSG_ID_UNKNOWN_ERROR);
            }
            return;
        }

    }

    /**
     * business logic to [Delete attachment files] on NWAL0370Scrn00.
     * @param bizMsg ZYPL0300CMsg
     */
    private void doProcess_ZYPL0300Scrn00_Delete(ZYPL0300CMsg bizMsg) {

        List<Integer> selectedList = ZYPTableUtil.getSelectedRows(bizMsg.A, "xxChkBox", ZYPConstant.CHKBOX_ON_Y);

        for (Integer index : selectedList) {

            ATT_DATATMsg attDataTMsg = new ATT_DATATMsg();
            attDataTMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
            attDataTMsg.attDataPk.setValue(bizMsg.A.no(index).attDataPk.getValue());

            // findByKeyForUpdateNoWait [ATT_DATA]
            attDataTMsg = (ATT_DATATMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(attDataTMsg);

            // remove Attachments
            if (attDataTMsg != null) {
                EZDTBLAccessor.logicalRemove(attDataTMsg);
            }

        }
    }

    private String getUploadFileNm(String uploadFilePath) {
        String fileNm = uploadFilePath.substring(uploadFilePath.lastIndexOf(File.separator) + 1, uploadFilePath.lastIndexOf("_@"));
        fileNm = fileNm.substring(0, fileNm.lastIndexOf("_"));
        String fileEx = ZYPFileNameUtil.getFileEx(uploadFilePath);
        return fileNm + "." + fileEx;
    }

}
