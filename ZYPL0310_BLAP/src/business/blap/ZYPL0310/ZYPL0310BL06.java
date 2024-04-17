package business.blap.ZYPL0310;

import java.io.File;
import java.util.List;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.common.EZDSystemEnv;
import parts.dbcommon.EZDTBLAccessor;

import business.blap.ZYPL0310.ZYPL0310CMsg;
import business.blap.ZYPL0310.constant.ZYPL0310Constant;
import business.db.ATT_DATATMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPBLOBAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.file.ZYPFileNameUtil;
import com.canon.cusa.s21.framework.ZYP.file.ZYPFileReader;
import com.canon.cusa.s21.framework.ZYP.file.ZYPFileTherefore;
import com.canon.cusa.s21.framework.ZYP.file.ZYPFileUpDown;
import com.canon.cusa.s21.framework.ZYP.file.ZYPFileUpDownConst;
import com.canon.cusa.s21.framework.ZYP.file.ZYPFileUtil;
import com.canon.cusa.s21.framework.ZYP.file.ZYPFileWriter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.internal.calendar.S21SystemDate;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

public class ZYPL0310BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {

            String screenAplID = cMsg.getScreenAplID();

            if ("ZYPL0310Scrn00_Upload".equals(screenAplID)) {
                doProcess_ZYPL0310Scrn00_Upload((ZYPL0310CMsg) cMsg);

            } else if ("ZYPL0310Scrn00_Delete".equals(screenAplID)) {
                doProcess_ZYPL0310Scrn00_Delete((ZYPL0310CMsg) cMsg);

            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * business logic to [Upload attachment file] on NWAL0370Scrn00.
     * @param bizMsg ZYPL0310CMsg
     */
    private void doProcess_ZYPL0310Scrn00_Upload(ZYPL0310CMsg bizMsg) {

        String uploadFilePath = null;
        byte[] uploadFileData = null;
        
        // [START] Write file to File Storage
        String uploadFileFullPathForFS = "";
        // [END] Write file to File Storage
        
        // ++++++++++++++++++++++++++++++++++++++++++++++++++
        // + create attachment record
        // ++++++++++++++++++++++++++++++++++++++++++++++++++
        ATT_DATATMsg attDataTMsg = new ATT_DATATMsg();

        if(bizMsg.attDataTpCd.getValue().equals(ZYPL0310Constant.ATT_DATA_TYPE_CODE_FILE)){
            // upload file
            uploadFilePath = bizMsg.xxFileData.getTempFilePath();
            uploadFileData = ZYPFileReader.getByteArray(uploadFilePath);
            bizMsg.xxFileData.deleteTempFile();
            attDataTMsg.attDataNm.setValue(getUploadFileNm(uploadFilePath));
            attDataTMsg.attDataVol.setValue(uploadFileData.length);
            
            // [START] -ADD- Write file to File Storage
            // S21.attachment.dir=/WebSphere/apps/filebox/attachment
            String uniqueFileNm = uploadFilePath.substring(uploadFilePath.lastIndexOf(File.separator) + 1);
            uploadFileFullPathForFS = ZYPFileWriter.uploadAttachmentFileToFS(uploadFileData, uniqueFileNm, bizMsg.ezBusinessID_I.getValue());
            // [END]] -ADD- Write file to File Storage
        }
        attDataTMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
        attDataTMsg.attDataPk.setValue(ZYPOracleSeqAccessor.getNumberBigDecimal("ATT_DATA_SQ"));
        attDataTMsg.ezBusinessID.setValue(bizMsg.ezBusinessID_I.getValue());
        attDataTMsg.attDataGrpTxt.setValue(bizMsg.attDataGrpTxt_I.getValue());
        // attDataTMsg.attDataDescTxt.setValue(bizMsg.attDataDescTxt_I.getValue());
        attDataTMsg.bizAppNm.setValue(bizMsg.bizAppNm.getValue());
        attDataTMsg.attDataKeyTxt.setValue(bizMsg.attDataKeyTxt.getValue());
        attDataTMsg.attDataTpCd.setValue(bizMsg.attDataTpCd.getValue());
        attDataTMsg.attDocTpCd.setValue(bizMsg.attDocTpCd.getValue());
        // [START] -MOD- Write file to File Storage
//        attDataTMsg.othSysUrl.setValue(bizMsg.othSysUrl.getValue());
        if (bizMsg.attDataTpCd.getValue().equals(ZYPL0310Constant.ATT_DATA_TYPE_CODE_FILE)) {
        	attDataTMsg.othSysUrl.setValue(uploadFileFullPathForFS);
        } else {
        	attDataTMsg.othSysUrl.setValue(bizMsg.othSysUrl.getValue());
        }
        // [END] -MOD- Write file to File Storage
        if(bizMsg.attDataTpCd.getValue().equals(ZYPL0310Constant.ATT_DATA_TYPE_CODE_THEREFORE)){
            attDataTMsg.attDataNm.setValue(bizMsg.docMgtDocId.getValue().toString());
        }
        if(bizMsg.attDataTpCd.getValue().equals(ZYPL0310Constant.ATT_DATA_TYPE_CODE_THEREFORE)){
            attDataTMsg.othSysUrl.setValue(EZDSystemEnv.getString("S21.therfore.attachment.url") + bizMsg.docMgtDocId.getValueInt());
        }

        EZDTBLAccessor.create(attDataTMsg);

        // ### SUCCESS
        if (RTNCD_NORMAL.equals(attDataTMsg.getReturnCode())) {

        	// [START] -DEL- Write file to File Storage
//            if(bizMsg.attDataTpCd.getValue().equals(ZYPL0310Constant.ATT_DATA_TYPE_CODE_FILE)){
//                // Update BLOB
//                int resultRows = new ZYPBLOBAccessor(attDataTMsg).updateBLOB(ZYPL0310Constant.DB_COL_BLOB, uploadFileData);
//                if (resultRows != 1) {
//                    bizMsg.setMessageInfo(ZYPL0310Constant.MSG_ID_UNKNOWN_ERROR);
//                }
//            }
            // [END] -DEL- Write file to File Storage

            // Update CLOB
            if(ZYPCommonFunc.hasValue(bizMsg.xxAttDataCmntTxt_I)) {
                int resultClobRows = ZYPL0310Query.getInstance().updAttCmnt(bizMsg, attDataTMsg);
                if (resultClobRows != 1) {
                    bizMsg.setMessageInfo(ZYPL0310Constant.MSG_ID_UNKNOWN_ERROR);
                    // [START] -ADD- Write file to File Storage
                    if (bizMsg.attDataTpCd.getValue().equals(ZYPL0310Constant.ATT_DATA_TYPE_CODE_FILE)) {
                		try {
                			if (uploadFileFullPathForFS.length() > 0) {
                				new File(uploadFileFullPathForFS).delete();
                			}
                		} catch (Exception e) {
                			e.printStackTrace();
                		}
                    }
                    // [END] -ADD- Write file to File Storage
                }
            }

            // if Therefore, call buisiness API
            if(bizMsg.attDataTpCd.getValue().equals(ZYPL0310Constant.ATT_DATA_TYPE_CODE_THEREFORE)
                    && S21StringUtil.isNotEmpty(bizMsg.ezBusinessAplID.getValue())){
                callBusinessApiForTherefore(bizMsg, ZYPFileUpDownConst.BIZAPI_TYPE_CREATE, attDataTMsg.attDataPk.getValueInt());
            }

            return;

        // ### ERROR
        } else {
            if (RTNCD_DUPLICATE.equals(attDataTMsg.getReturnCode())) {
                bizMsg.setMessageInfo(ZYPL0310Constant.MSG_ID_ATTACHMENT_DUPLICATE);
            } else {
                bizMsg.setMessageInfo(ZYPL0310Constant.MSG_ID_UNKNOWN_ERROR);
            }
            // [START] -ADD- Write file to File Storage
            if (bizMsg.attDataTpCd.getValue().equals(ZYPL0310Constant.ATT_DATA_TYPE_CODE_FILE)) {
        		try {
        			if (uploadFileFullPathForFS.length() > 0) {
        				new File(uploadFileFullPathForFS).delete();
        			}
        		} catch (Exception e) {
        			e.printStackTrace();
        		}
            }
            // [END] -ADD- Write file to File Storage
            return;
        }

    }

    /**
     * business logic to [Delete attachment files] on NWAL0370Scrn00.
     * @param bizMsg ZYPL0310CMsg
     */
    private void doProcess_ZYPL0310Scrn00_Delete(ZYPL0310CMsg bizMsg) {

        List<Integer> selectedList = ZYPTableUtil.getSelectedRows(bizMsg.A, "xxChkBox", ZYPConstant.CHKBOX_ON_Y);

        for (Integer index : selectedList) {

            // if Therefore, call buisiness API
            if(bizMsg.A.no(index).attDataTpCd_AI.getValue().equals(ZYPL0310Constant.ATT_DATA_TYPE_CODE_THEREFORE)
                    && S21StringUtil.isNotEmpty(bizMsg.ezBusinessAplID.getValue())){
                callBusinessApiForTherefore(bizMsg, ZYPFileUpDownConst.BIZAPI_TYPE_DELETE, bizMsg.A.no(index).attDataPk.getValueInt());
            }

            ATT_DATATMsg attDataTMsg = new ATT_DATATMsg();
            attDataTMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
            attDataTMsg.attDataPk.setValue(bizMsg.A.no(index).attDataPk.getValue());

            // findByKeyForUpdateNoWait [ATT_DATA]
            attDataTMsg = (ATT_DATATMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(attDataTMsg);

            // remove Attachments
            if (attDataTMsg != null) {
                EZDTBLAccessor.logicalRemove(attDataTMsg);
            }
            // [START] -ADD- Write file to File Storage
            if (bizMsg.attDataTpCd.getValue().equals(ZYPL0310Constant.ATT_DATA_TYPE_CODE_FILE)) {
        		try {
        			if (attDataTMsg.othSysUrl.getValue().length() > 0) {
        				File targetFile =  new File(attDataTMsg.othSysUrl.getValue());
        				if(targetFile.exists() && !targetFile.isDirectory()) {
        					targetFile.delete();
        				}
        			}
        		} catch (Exception e) {
        			throw new S21AbendException(e);
        		}
            }
            // [END] -ADD- Write file to File Storage
        }
    }

    private String getUploadFileNm(String uploadFilePath) {
        String fileNm = uploadFilePath.substring(uploadFilePath.lastIndexOf(File.separator) + 1, uploadFilePath.lastIndexOf("_@"));
        fileNm = fileNm.substring(0, fileNm.lastIndexOf("_"));
        String fileEx = ZYPFileNameUtil.getFileEx(uploadFilePath);
        return fileNm + "." + fileEx;
    }

    private void callBusinessApiForTherefore(ZYPL0310CMsg bizMsg, String bizApiType, int attDataPk){
        S21InfoLogOutput.println("Buisiness API[" + bizMsg.ezBusinessAplID.getValue() + "] for Therefore (attDataPk[" + attDataPk + "])START");

        ZYPFileTherefore zypTf = new ZYPFileTherefore();
        zypTf.callBizApi(bizMsg.ezBusinessAplID.getValue(), ZYPFileUpDownConst.API_METHOD_NAME, new Object[]{attDataPk, ONBATCH_TYPE.ONLINE, bizApiType});
        // Set Business msg info
        List<S21ApiMessage> msgList = zypTf.getApiMsgList();
        if (msgList != null && msgList.size() > 0) {
        	for(S21ApiMessage msg : msgList){
        		if (msg.getXxMsgPrmNum() > 0){
        			bizMsg.setMessageInfo(msg.getXxMsgid(), msg.getXxMsgPrmArray());
        		} else {
        			bizMsg.setMessageInfo(msg.getXxMsgid());
        		}
            }
        }
        S21InfoLogOutput.println("Buisiness API[" + bizMsg.ezBusinessAplID.getValue() + "] for Therefore (attDataPk[" + attDataPk + "])END");
    }
}
