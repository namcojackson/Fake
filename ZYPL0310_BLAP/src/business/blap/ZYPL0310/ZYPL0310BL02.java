package business.blap.ZYPL0310;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Clob;
import java.util.List;
import java.util.Map;

import javax.xml.ws.soap.SOAPFaultException;

import net.therefore.schemas.webservices.interop.v0001.messages.IThereforeService;
import net.therefore.schemas.webservices.interop.v0001.types.CreateLinkFileParams;
import net.therefore.schemas.webservices.interop.v0001.types.CreateLinkFileResponse;
import parts.common.EZDCBigDecimalItem;
import parts.common.EZDCMsg;
import parts.common.EZDCStringItem;
import parts.common.EZDDebugOutput;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.ZYPL0310.constant.ZYPL0310Constant;
import business.db.ATT_DATATMsg;
import business.db.ATT_DATATMsgArray;
import business.db.NUM_CONSTTMsg;
import business.db.VAR_CHAR_CONSTTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.file.ZYPFileReader;
import com.canon.cusa.s21.framework.ZYP.file.ZYPFileWriter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.therefore.webservice.S21ThereforeWebServiceProxy;
import com.sun.xml.internal.ws.client.ClientTransportException;

public class ZYPL0310BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {

            String screenAplID = cMsg.getScreenAplID();

            if ("ZYPL0310_INIT".equals(screenAplID)) {
                doProcess_ZYPL0310_INIT((ZYPL0310CMsg) cMsg);

            } else if ("ZYPL0310Scrn00_Upload".equals(screenAplID)) {
                doProcess_ZYPL0310Scrn00_Upload((ZYPL0310CMsg) cMsg);

            } else if ("ZYPL0310Scrn00_Download".equals(screenAplID)) {
                doProcess_ZYPL0310Scrn00_Download((ZYPL0310CMsg) cMsg);

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
     * business logic for initialize ZYPL0310Scrn00.
     * @param bizMsg ZYPL0310CMsg
     */
    private void doProcess_ZYPL0310_INIT(ZYPL0310CMsg bizMsg) {

        if ("ZYPL0310_INIT".equals(bizMsg.getScreenAplID())) {
            initializeConstTableInfo(bizMsg);
        }

        // find attachment files.
        ATT_DATATMsgArray attDataTArray = findAttachments(bizMsg);
        EZDDebugOutput.println(1, "findByCondition(ATT_DATA) = " + attDataTArray, this);

        bizMsg.A.clear();
        bizMsg.A.setValidCount(attDataTArray.length());

        for (int i = 0; i < attDataTArray.length(); i++) {

            EZDMsg.copy(attDataTArray.no(i), null, bizMsg.A.no(i), null);

            // No
            bizMsg.A.no(i).xxNum.setValue(i + 1);

            // Attachment description
            if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).xxAttDataCmntTxt)) {
                bizMsg.A.no(i).attDataDescTxt_FG.setValue(ZYPConstant.FLG_ON_Y);
            }

            // EZINTIME
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).ezInTime, attDataTArray.no(i).ezInTime);
            String ezInTime = bizMsg.A.no(i).ezInTime.getValue();
            // mm/dd/yyyy
            bizMsg.A.no(i).ezInTime_DT.setValue(ezInTime.substring(4, 6) + "/" + ezInTime.substring(6, 8) + "/" + ezInTime.substring(0, 4));
            // HH:mm:ss
            bizMsg.A.no(i).ezInTime_TM.setValue(ezInTime.substring(8, 10) + ":" + ezInTime.substring(10, 12) + ":" + ezInTime.substring(12, 14));

            // Attached By
            bizMsg.A.no(i).ezInUserID.setValue(attDataTArray.no(i).ezInUserID.getValue());

            // Data Type
            for(int j=0; j<bizMsg.xxAttDataTpNm_OT.length(); j++){
                bizMsg.A.no(i).xxAttDataTpNm_AO.no(j).setValue(bizMsg.xxAttDataTpNm_OT.no(j).getValue());
            }
            for(int j=0; j<bizMsg.attDataTpCd_OT.length(); j++){
                bizMsg.A.no(i).attDataTpCd_AO.no(j).setValue(bizMsg.attDataTpCd_OT.no(j).getValue());
            }
            bizMsg.A.no(i).attDataTpCd_AI.setValue(attDataTArray.no(i).attDataTpCd.getValue());

            // Document Type
            // Mod Start 2019/05/10 QC#50015
//            for(int j=0; j<bizMsg.xxAttDocTpNm_OT.length(); j++){
//                bizMsg.A.no(i).xxAttDocTpNm_AO.no(j).setValue(bizMsg.xxAttDocTpNm_OT.no(j).getValue());
//            }
//            for(int j=0; j<bizMsg.attDocTpCd_OT.length(); j++){
//                bizMsg.A.no(i).attDocTpCd_AO.no(j).setValue(bizMsg.attDocTpCd_OT.no(j).getValue());
//            }
            for(int j=0; j<bizMsg.xxAttDocTpNm_OH.length(); j++){
                bizMsg.A.no(i).xxAttDocTpNm_AO.no(j).setValue(bizMsg.xxAttDocTpNm_OH.no(j).getValue());
            }
            for(int j=0; j<bizMsg.attDocTpCd_OH.length(); j++){
                bizMsg.A.no(i).attDocTpCd_AO.no(j).setValue(bizMsg.attDocTpCd_OH.no(j).getValue());
            }
            bizMsg.A.no(i).attDocTpCd_AI.setValue(attDataTArray.no(i).attDocTpCd.getValue());
            // Mod End 2019/05/10 QC#50015

            // URL
            bizMsg.A.no(i).othSysUrl_AO.setValue(attDataTArray.no(i).othSysUrl.getValue());

            // sort key for Attachment Data Name column of table A
            if(bizMsg.A.no(i).attDataTpCd_AI.getValue().equals(ZYPL0310Constant.ATT_DATA_TYPE_CODE_FILE)){
                bizMsg.A.no(i).othSysUrl_BK.setValue(bizMsg.A.no(i).attDataNm.getValue());
            } else if(bizMsg.A.no(i).attDataTpCd_AI.getValue().equals(ZYPL0310Constant.ATT_DATA_TYPE_CODE_URL)
                      || bizMsg.A.no(i).attDataTpCd_AI.getValue().equals(ZYPL0310Constant.ATT_DATA_TYPE_CODE_THEREFORE)){
                bizMsg.A.no(i).othSysUrl_BK.setValue(bizMsg.A.no(i).othSysUrl_AO.getValue());
            }

            // file size
            if(bizMsg.A.no(i).attDataTpCd_AI.getValue().equals(ZYPL0310Constant.ATT_DATA_TYPE_CODE_FILE)){
                // Attachment data volume & volume unit
                calcAttachmentVol(bizMsg.A.no(i));
            }
        }

        // find Description/Note(Comment) data.
        if(attDataTArray.length() > 0){
            S21SsmEZDResult ssmResult = ZYPL0310Query.getInstance().getAttCmnt(bizMsg);
            if(ssmResult.isCodeNormal()){
                copyAttDataCmntToCmsg(ssmResult, bizMsg);
            }
        }

    }

    /**
     * business logic to [Download attachment file] on NWAL0370Scrn00.
     * @param bizMsg ZYPL0310CMsg
     */
    private void doProcess_ZYPL0310Scrn00_Download(ZYPL0310CMsg bizMsg) {

        // findByKey [ATT_DATA]
        ATT_DATATMsg attDataTMsg = new ATT_DATATMsg();
        attDataTMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
        attDataTMsg.attDataPk.setValue(bizMsg.A.no(bizMsg.xxCellIdx_DL.getValueInt()).attDataPk.getValue());

        attDataTMsg = (ATT_DATATMsg) EZDTBLAccessor.findByKey(attDataTMsg);

        // nothing data to download.
        if (attDataTMsg == null) {
            doDownloadDataNotFoundProcess(bizMsg);
            return;

        } else {
        	// [START] -MOD- Change to use Therefore Desktop client viewer 2018.06.01
//        	
//        	// [START] -MOD- Write file to File Storage
////            ZYPBLOBAccessor blobAccessor = new ZYPBLOBAccessor(attDataTMsg);
////            byte[] downloadData = blobAccessor.findBLOB(ZYPL0310Constant.DB_COL_BLOB);
//        	String filePathFromFileStorage = attDataTMsg.othSysUrl.getValue();
//        	byte[] downloadData = ZYPFileReader.getByteArray(filePathFromFileStorage);
//            // [END] -MOD- Write file to File Storage
//
//
//            // nothing data to download.
//            if (downloadData == null) {
//                doDownloadDataNotFoundProcess(bizMsg);
//                return;
//
//            } else {
//
//                bizMsg.xxFileData_DL.setTempFilePath(null, attDataTMsg.attDataNm.getValue(), "");
//                ZYPFileWriter.writeFile(bizMsg.xxFileData_DL.getTempFilePath(), downloadData);
//            }
//            
        	byte[] downloadData = null;
        	
        	// Therefore document (.thex) download case
        	if (attDataTMsg.attDataTpCd.getValue().equals(ZYPL0310Constant.ATT_DATA_TYPE_CODE_THEREFORE)) {
        		// Get Therefore doc id from ATT_DATA_NM column
            	String docId = attDataTMsg.attDataNm.getValue();
        		// Call Therefore web-service for obtaining .thex file for launching Therefore Desktop client viewer
            	CreateLinkFileResponse response = callCreateLinkFileThereforeWebservice(Integer.valueOf(docId));
                if (response == null) {
                    doDownloadDataNotFoundProcess(bizMsg);
                    return;
                }
            	downloadData = response.getLinkFileData();
            	String extns = response.getLinkFileExtension();
            	docId = docId + extns;
                if (downloadData == null) {
                    doDownloadDataNotFoundProcess(bizMsg);
                    return;
                } else {
                	bizMsg.xxFileData_DL.setTempFilePath(null, docId, null);
                    ZYPFileWriter.writeFile(bizMsg.xxFileData_DL.getTempFilePath(), downloadData);
                }
        	} else {
            	// [START] -MOD- Write file to File Storage
//              ZYPBLOBAccessor blobAccessor = new ZYPBLOBAccessor(attDataTMsg);
//              byte[] downloadData = blobAccessor.findBLOB(ZYPL0310Constant.DB_COL_BLOB);
        		String filePathFromFileStorage = attDataTMsg.othSysUrl.getValue();
        		downloadData = ZYPFileReader.getByteArray(filePathFromFileStorage);
              // [END] -MOD- Write file to File Storage
                // nothing data to download.
                if (downloadData == null) {
                    doDownloadDataNotFoundProcess(bizMsg);
                    return;
                } else {
                	bizMsg.xxFileData_DL.setTempFilePath(null, attDataTMsg.attDataNm.getValue(), "");
                    ZYPFileWriter.writeFile(bizMsg.xxFileData_DL.getTempFilePath(), downloadData);
                }
        	}
            // [END] -MOD- Change to use Therefore Desktop client viewer 2018.06.01
        }
    }
    
    // [START] -ADD- Change to use Therefore Desktop client viewer 2018.06.01
    private CreateLinkFileResponse callCreateLinkFileThereforeWebservice(int tfDocId) {
		// Get Therefore web service port instance
		IThereforeService thereforWebSerive = S21ThereforeWebServiceProxy.getInstance().getThereforeService();
		CreateLinkFileResponse response = null;
		try {
			// Set parameters
			CreateLinkFileParams param = new CreateLinkFileParams();
			param.setDocNo(tfDocId);
			
			// Invoke Therefore web service
			response = thereforWebSerive.createLinkFile(param);
			// Confirm Response result
//			String extns = response.getLinkFileExtension();
//			result = response.getLinkFileData();
			
		} catch (SOAPFaultException e) { // Most of error from Therefore side include data error falls in here
			e.printStackTrace();
			throw new S21AbendException(e);
		} catch (ClientTransportException e) { // Connection refused error falls in here, it happens before Therefore side
			e.printStackTrace();
			// when throwing S21AbendException, alert e-mail sent to operation
			throw new S21AbendException(e);
		} catch (Exception e) { // Unknown error falls in here
			e.printStackTrace();
			// when throwing S21AbendException, alert e-mail sent to operation
			throw new S21AbendException(e);
		}
		return response;
	}
    // [END] -ADD- Change to use Therefore Desktop client viewer 2018.06.01
    /**
     * business logic to [Upload attachment file] on NWAL0370Scrn00.
     * @param bizMsg ZYPL0310CMsg
     */
    private void doProcess_ZYPL0310Scrn00_Upload(ZYPL0310CMsg bizMsg) {
        doProcess_ZYPL0310_INIT(bizMsg);
    }

    /**
     * business logic to [Delete attachment files] on NWAL0370Scrn00.
     * @param bizMsg ZYPL0310CMsg
     */
    private void doProcess_ZYPL0310Scrn00_Delete(ZYPL0310CMsg bizMsg) {
        doProcess_ZYPL0310_INIT(bizMsg);
    }

    private void initializeConstTableInfo(ZYPL0310CMsg bizMsg) {
        EZDDebugOutput.println(1, "+++++++++++++++++++++++++ initializeConstTableInfo:START +++++++++++++++++++++++++", this);

        int attachmentsLimitNum = findAttachmentsLimitNum(bizMsg);
        EZDDebugOutput.println(1, "attachmentsLimitNum = " + attachmentsLimitNum, this);

        int authorizeFileVol = findAuthorizeFileVol(bizMsg);
        EZDDebugOutput.println(1, "authorizeFileVol    = " + authorizeFileVol, this);

        String systemNgFileEx = findSystemNgFileEx(bizMsg);
        EZDDebugOutput.println(1, "systemNgFileEx      = " + systemNgFileEx, this);

        String authorizeFileEx = findAuthorizeFileEx(bizMsg);
        EZDDebugOutput.println(1, "authorizeFileEx     = " + authorizeFileEx, this);

        // Mod Start 2019/05/09 QC#50015
        if (ZYPCommonFunc.hasValue(bizMsg.xxScrItem130Txt)) {
            S21SsmEZDResult ssmResult = ZYPL0310Query.getInstance().getAttDocTpCdList(bizMsg.glblCmpyCd.getValue(), bizMsg.xxScrItem130Txt.getValue());
            if (ssmResult.isCodeNormal()) {
                List<Map<String, Object>> rsltList = (List<Map<String, Object>>) ssmResult.getResultObject();
                for (int i = 0; i < rsltList.size() && i < bizMsg.attDocTpCd_OT.length(); i++) {
                    Map<String, Object> rsltMap = rsltList.get(i);
                    ZYPEZDItemValueSetter.setValue(bizMsg.attDocTpCd_OT.no(i), (String) rsltMap.get("ATT_DATA_DOC_TP_CD"));
                    ZYPEZDItemValueSetter.setValue(bizMsg.xxAttDocTpNm_OT.no(i), (String) rsltMap.get("ATT_DATA_DOC_TP_NM"));
                }
            }
        } else {
            ZYPCodeDataUtil.createPulldownList(bizMsg.cdTblNm.getValue(), bizMsg.attDocTpCd_OT, bizMsg.xxAttDocTpNm_OT);
        }
        ZYPCodeDataUtil.createPulldownList(bizMsg.cdTblNm.getValue(), bizMsg.attDocTpCd_OH, bizMsg.xxAttDocTpNm_OH);
        // Mod End 2019/05/09 QC#50015
        EZDDebugOutput.println(1, "+++++++++++++++++++++++++ initializeConstTableInfo:E N D +++++++++++++++++++++++++", this);
    }

    private int findAttachmentsLimitNum(ZYPL0310CMsg bizMsg) {

        EZDCStringItem keyItem = bizMsg.numConstNm_I1;
        EZDCBigDecimalItem valItem = bizMsg.numConstVal_I1;

        NUM_CONSTTMsg resTMsg = null;

        if (ZYPCommonFunc.hasValue(keyItem)) {

            NUM_CONSTTMsg tMsg = new NUM_CONSTTMsg();
            tMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
            tMsg.numConstNm.setValue(keyItem.getValue());

            resTMsg = (NUM_CONSTTMsg) S21CodeTableAccessor.findByKey(tMsg);
        }
        EZDDebugOutput.println(1, "findByKey(NUM_CONST) = " + resTMsg, this);

        int attachmentsLimitNum = -1;

        if (resTMsg == null) {
            attachmentsLimitNum = bizMsg.A.length();
        } else {
            BigDecimal value = resTMsg.numConstVal.getValue();
            if (!ZYPCommonFunc.hasValue(value)) {
                attachmentsLimitNum = bizMsg.A.length();
            } else if (value.intValue() > bizMsg.A.length()) {
                attachmentsLimitNum = bizMsg.A.length();
            } else {
                attachmentsLimitNum = value.intValue();
            }
        }

        valItem.setValue(attachmentsLimitNum);
        return valItem.getValueInt();
    }

    private int findAuthorizeFileVol(ZYPL0310CMsg bizMsg) {

        EZDCStringItem keyItem = bizMsg.numConstNm_I2;
        EZDCBigDecimalItem valItem = bizMsg.numConstVal_I2;

        NUM_CONSTTMsg resTMsg = null;

        if (ZYPCommonFunc.hasValue(keyItem)) {

            NUM_CONSTTMsg tMsg = new NUM_CONSTTMsg();
            tMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
            tMsg.numConstNm.setValue(keyItem.getValue());

            resTMsg = (NUM_CONSTTMsg) S21CodeTableAccessor.findByKey(tMsg);
        }
        EZDDebugOutput.println(1, "findByKey(NUM_CONST) = " + resTMsg, this);

        // 10MB
        final int DEFAULT_MB = 10;

        int authorizeFileVol = -1;

        if (resTMsg == null) {
            authorizeFileVol = DEFAULT_MB;
        } else {
            BigDecimal value = resTMsg.numConstVal.getValue();
            if (!ZYPCommonFunc.hasValue(value)) {
                authorizeFileVol = DEFAULT_MB;
            } else {
                authorizeFileVol = value.intValue();
            }
        }

        valItem.setValue(authorizeFileVol);
        return valItem.getValueInt();
    }

    private String findSystemNgFileEx(ZYPL0310CMsg bizMsg) {

        // EZDCStringItem keyItem = bizMsg.varCharConstNm_I1;
        EZDCStringItem valItem = bizMsg.varCharConstVal_I1;

        VAR_CHAR_CONSTTMsg tMsg = new VAR_CHAR_CONSTTMsg();
        tMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
        tMsg.varCharConstNm.setValue("ATT_SYSTEM_NG_FILE_EX");

        VAR_CHAR_CONSTTMsg resTMsg = (VAR_CHAR_CONSTTMsg) S21CodeTableAccessor.findByKey(tMsg);
        EZDDebugOutput.println(1, "findByKey(VAR_CHAR_CONST) = " + resTMsg, this);

        if (resTMsg == null) {
            valItem.clear();
        } else {
            String value = resTMsg.varCharConstVal.getValue();
            if (!ZYPCommonFunc.hasValue(value)) {
                valItem.clear();
            } else {
                valItem.setValue(value);
            }

        }

        return valItem.getValue();
    }

    private String findAuthorizeFileEx(ZYPL0310CMsg bizMsg) {

        EZDCStringItem keyItem = bizMsg.varCharConstNm_I2;
        EZDCStringItem valItem = bizMsg.varCharConstVal_I2;

        VAR_CHAR_CONSTTMsg resTMsg = null;

        if (ZYPCommonFunc.hasValue(keyItem)) {

            VAR_CHAR_CONSTTMsg tMsg = new VAR_CHAR_CONSTTMsg();
            tMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
            tMsg.varCharConstNm.setValue(keyItem.getValue());

            resTMsg = (VAR_CHAR_CONSTTMsg) S21CodeTableAccessor.findByKey(tMsg);
        }
        EZDDebugOutput.println(1, "findByKey(VAR_CHAR_CONST) = " + resTMsg, this);

        if (resTMsg == null) {
            valItem.clear();
        } else {
            String value = resTMsg.varCharConstVal.getValue();
            if (!ZYPCommonFunc.hasValue(value)) {
                valItem.clear();
            } else {
                valItem.setValue(value);
            }

        }

        return valItem.getValue();
    }

    private ATT_DATATMsgArray findAttachments(ZYPL0310CMsg bizMsg) {

        // findByCondition [ATT_DATA]
        ATT_DATATMsg attDataT = new ATT_DATATMsg();

        attDataT.setSQLID("001");
        attDataT.setMaxCount(bizMsg.A.length());
        attDataT.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        attDataT.setConditionValue("ezBusinessID01", bizMsg.ezBusinessID_I.getValue());
        attDataT.setConditionValue("attDataGrpTxt01", bizMsg.attDataGrpTxt_I.getValue());

        return (ATT_DATATMsgArray) EZDTBLAccessor.findByCondition(attDataT);
    }

    private void calcAttachmentVol(ZYPL0310_ACMsg aCMsg) {

        BigDecimal dataVol = ZYPCommonFunc.getBigDecimal(aCMsg.attDataVol, BigDecimal.ZERO.toString());

        switch (dataVol.compareTo(ZYPL0310Constant.BYTE_KB)) {

            // byte
            case -1:
                aCMsg.attDataVol_O.setValue(dataVol);
                aCMsg.xxFileVolUnit.setValue("byte");
                break;

            // KB
            case 0:
                aCMsg.attDataVol_O.setValue(dataVol.divide(ZYPL0310Constant.BYTE_KB, 0, BigDecimal.ROUND_HALF_UP));
                aCMsg.xxFileVolUnit.setValue("KB");
                break;

            case 1:
                switch (dataVol.compareTo(ZYPL0310Constant.BYTE_MB)) {

                    // KB
                    case -1:
                        aCMsg.attDataVol_O.setValue(dataVol.divide(ZYPL0310Constant.BYTE_KB, 0, BigDecimal.ROUND_HALF_UP));
                        aCMsg.xxFileVolUnit.setValue("KB");
                        break;

                    // MB
                    case 0:
                    case 1:
                        aCMsg.attDataVol_O.setValue(dataVol.divide(ZYPL0310Constant.BYTE_MB, 0, BigDecimal.ROUND_HALF_UP));
                        aCMsg.xxFileVolUnit.setValue("MB");
                        break;

                }
                break;

            default:
                // nothing to do.
                break;
        }
    }

    private void doDownloadDataNotFoundProcess(ZYPL0310CMsg bizMsg) {

        bizMsg.setMessageInfo(ZYPL0310Constant.MSG_ID_DOWNLOADFILE_NOT_FOUND, new String[] {bizMsg.A.no(bizMsg.xxCellIdx_DL.getValueInt()).attDataNm.getValue() });

        doProcess_ZYPL0310_INIT(bizMsg);
    }

    private void copyAttDataCmntToCmsg(S21SsmEZDResult ssmResult, ZYPL0310CMsg bizMsg){
      // get search result
      List resultList = (List) ssmResult.getResultObject();
      for(int i=0; i<resultList.size(); i++){
          Map map = (Map) resultList.get(i);
          if(map.get(ZYPL0310Constant.RSLT_CHUNK_SIZE) != null && map.get(ZYPL0310Constant.RSLT_ATT_DATA_CMNT_CLOD) != null){
              int size = ((BigDecimal) map.get(ZYPL0310Constant.RSLT_CHUNK_SIZE)).intValue();
              Clob clob = (Clob) map.get(ZYPL0310Constant.RSLT_ATT_DATA_CMNT_CLOD);

              // get clob data
              StringBuilder strAttDataCmnt = new StringBuilder();
              BufferedReader reader = null;

              try {
                  if(clob != null){
                      reader = new BufferedReader(clob.getCharacterStream());

                      char[] cbuffer = new char[size];
                      int length = -1;

                      while( ( length = reader.read( cbuffer ) ) != -1 ) {
                          strAttDataCmnt.append( new String( cbuffer, 0, length ) );
                      }
                  }
                  for(int j=0; j<bizMsg.A.getValidCount(); j++){
                      if(bizMsg.A.no(j).attDataPk.getValue().equals(map.get(ZYPL0310Constant.RSLT_ATT_DATA_PK))){
                          // copy clob data to cMsg
                          int cmntDigit = bizMsg.A.no(0).getAttr("xxAttDataCmntTxt").getDigit();
                          if(strAttDataCmnt.length() > cmntDigit){
                              bizMsg.A.no(j).xxAttDataCmntTxt.setValue(strAttDataCmnt.substring(0, cmntDigit));
                          } else {
                              bizMsg.A.no(j).xxAttDataCmntTxt.setValue(strAttDataCmnt.toString());
                          }

                          // Attachment description flag
                          if (ZYPCommonFunc.hasValue(bizMsg.A.no(j).xxAttDataCmntTxt)) {
                              bizMsg.A.no(j).attDataDescTxt_FG.setValue(ZYPConstant.FLG_ON_Y);
                          }
                      }
                  }
              } catch (Exception e){
                  String msg = "Failed to retrieve clob data. ATT_DATA_PK=" + map.get(ZYPL0310Constant.RSLT_ATT_DATA_PK);
                  throw new S21AbendException(e, msg);
              } finally {
                  try {
                      if (reader != null){
                          reader.close();
                      }
                  } catch (IOException e) {
                      String msg = "Failed to retrieve clob data. ATT_DATA_PK=" + map.get(ZYPL0310Constant.RSLT_ATT_DATA_PK);
                      throw new S21AbendException(e, msg);
                  }
              }
          }
      }
    }
}
