/**
 * <pre>
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/23/2009   CUSA            K.Tajima        Create          N/A
 *</pre>
 */
package business.blap.NMAL6130;

import java.io.File;
import java.util.List;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NMAL6130.constant.NMAL6130Constant;
import business.db.MSTR_ATT_DATATMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPBLOBAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.file.ZYPFileNameUtil;
import com.canon.cusa.s21.framework.ZYP.file.ZYPFileReader;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

public class NMAL6130BL06 extends S21BusinessHandler implements NMAL6130Constant {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {

            String screenAplID = cMsg.getScreenAplID();

            if ("NMAL6130Scrn00_Upload".equals(screenAplID)) {
                doProcess_NMAL6130Scrn00_Upload((NMAL6130CMsg) cMsg);

            } else if ("NMAL6130Scrn00_Delete".equals(screenAplID)) {
                doProcess_NMAL6130Scrn00_Delete((NMAL6130CMsg) cMsg);

            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * business logic to [Upload attachment file] on NWAL0370Scrn00.
     * @param bizMsg NMAL6130CMsg
     */
    private void doProcess_NMAL6130Scrn00_Upload(NMAL6130CMsg bizMsg) {

        // upload file
        String uploadFilePath = bizMsg.xxFileData.getTempFilePath();
        byte[] uploadFileData = ZYPFileReader.getByteArray(uploadFilePath);
        bizMsg.xxFileData.deleteTempFile();
        // ++++++++++++++++++++++++++++++++++++++++++++++++++
        // + create attachment record
        // ++++++++++++++++++++++++++++++++++++++++++++++++++
        MSTR_ATT_DATATMsg mstrAttDataTMsg = new MSTR_ATT_DATATMsg();
        mstrAttDataTMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
        mstrAttDataTMsg.mstrAttDataPk.setValue(ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.MSTR_ATT_DATA_SQ));
        mstrAttDataTMsg.mstrAttDataGrpTxt.setValue(bizMsg.mstrAttDataGrpTxt_I.getValue());
        mstrAttDataTMsg.mstrAttDataNm.setValue(getUploadFileNm(uploadFilePath));
        mstrAttDataTMsg.mstrAttDataVol.setValue(uploadFileData.length);
        mstrAttDataTMsg.mstrAttDataDescTxt.setValue(bizMsg.mstrAttDataDescTxt_I.getValue());
        ZYPEZDItemValueSetter.setValue(mstrAttDataTMsg.batProcEndFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(mstrAttDataTMsg.cmpyPk, bizMsg.cmpyPk_I);
        ZYPEZDItemValueSetter.setValue(mstrAttDataTMsg.ptyLocPk, bizMsg.ptyLocPk_I);
        ZYPEZDItemValueSetter.setValue(mstrAttDataTMsg.mstrBizId, bizMsg.mstrBizId_I);
        
        // Create Master Att Data Activity Type Code Pulldown
        ZYPEZDItemValueSetter.setValue(mstrAttDataTMsg.mstrActvCd, bizMsg.mstrActvCd_I1);

        EZDTBLAccessor.create(mstrAttDataTMsg);

        // ### SUCCESS
        if (RTNCD_NORMAL.equals(mstrAttDataTMsg.getReturnCode())) {

            // Update BLOB
            int resultRows = new ZYPBLOBAccessor(mstrAttDataTMsg).updateBLOB(DB_COL_BLOB, uploadFileData);
            if (resultRows != 1) {
                bizMsg.setMessageInfo(MSG_ID_UNKNOWN_ERROR);
            }
            return;

            // ### ERROR
        } else {
            if (RTNCD_DUPLICATE.equals(mstrAttDataTMsg.getReturnCode())) {
                bizMsg.setMessageInfo(MSG_ID_ATTACHMENT_DUPLICATE);
            } else {
                bizMsg.setMessageInfo(MSG_ID_UNKNOWN_ERROR);
            }
            return;
        }

    }

    /**
     * business logic to [Delete attachment files] on NWAL0370Scrn00.
     * @param bizMsg NMAL6130CMsg
     */
    private void doProcess_NMAL6130Scrn00_Delete(NMAL6130CMsg bizMsg) {

        List<Integer> selectedList = ZYPTableUtil.getSelectedRows(bizMsg.A, "xxChkBox", ZYPConstant.CHKBOX_ON_Y);

        for (Integer index : selectedList) {

            MSTR_ATT_DATATMsg mstrAttDataTMsg = new MSTR_ATT_DATATMsg();
            mstrAttDataTMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
            mstrAttDataTMsg.mstrAttDataPk.setValue(bizMsg.A.no(index).mstrAttDataPk.getValue());

            // findByKeyForUpdateNoWait [ATT_DATA]
            mstrAttDataTMsg = (MSTR_ATT_DATATMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(mstrAttDataTMsg);

            // remove Attachments
            if (mstrAttDataTMsg != null) {
                EZDTBLAccessor.logicalRemove(mstrAttDataTMsg);
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
