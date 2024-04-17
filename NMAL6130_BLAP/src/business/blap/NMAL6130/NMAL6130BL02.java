/**
 * <pre>
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/23/2009   CUSA            K.Tajima        Create          N/A
 * 10/31/2013   Hitachi         T.Kawazu        Update          QC2852
 *</pre>
 */
package business.blap.NMAL6130;

import java.math.BigDecimal;

import parts.common.EZDCBigDecimalItem;
import parts.common.EZDCMsg;
import parts.common.EZDCStringItem;
import parts.common.EZDDebugOutput;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NMAL6130.constant.NMAL6130Constant;
import business.db.MSTR_ACTV_TPTMsg;
import business.db.MSTR_ACTV_TPTMsgArray;
import business.db.MSTR_ATT_DATATMsg;
import business.db.MSTR_ATT_DATATMsgArray;
import business.db.NUM_CONSTTMsg;
import business.db.VAR_CHAR_CONSTTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPBLOBAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.file.ZYPFileWriter;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

public class NMAL6130BL02 extends S21BusinessHandler implements NMAL6130Constant {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {

            String screenAplID = cMsg.getScreenAplID();

            if ("NMAL6130_INIT".equals(screenAplID)) {
                doProcess_NMAL6130_INIT((NMAL6130CMsg) cMsg);

            } else if ("NMAL6130Scrn00_Upload".equals(screenAplID)) {
                doProcess_NMAL6130Scrn00_Upload((NMAL6130CMsg) cMsg);

            } else if ("NMAL6130Scrn00_Download".equals(screenAplID)) {
                doProcess_NMAL6130Scrn00_Download((NMAL6130CMsg) cMsg);

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
     * business logic for initialize NMAL6130Scrn00.
     * @param bizMsg NMAL6130CMsg
     */
    private void doProcess_NMAL6130_INIT(NMAL6130CMsg bizMsg) {

        if ("NMAL6130_INIT".equals(bizMsg.getScreenAplID())) {
            initializeConstTableInfo(bizMsg);
            // Create Master Att Data Activity Type Code Pulldown
            MSTR_ACTV_TPTMsg mstrActvTpTmsg = new MSTR_ACTV_TPTMsg();
            mstrActvTpTmsg.setSQLID("100");
            mstrActvTpTmsg.setConditionValue("glblCmpyCd01", getGlobalCompanyCode());
            
            MSTR_ACTV_TPTMsgArray mstrActvTpTmsgArray = (MSTR_ACTV_TPTMsgArray) EZDTBLAccessor.findByCondition(mstrActvTpTmsg);
            
            if (mstrActvTpTmsgArray.length() > 0) {
            	
              for (int i = 0,length = mstrActvTpTmsgArray.length(); i < length; i++) {
    				bizMsg.mstrActvCd_I2.no(i).setValue(mstrActvTpTmsgArray.no(i).mstrActvCd.getValue());
    				bizMsg.mstrActvNm_I2.no(i).setValue(mstrActvTpTmsgArray.no(i).mstrActvNm.getValue());
    			}
            }
        }
        
        // find attachment files.
        MSTR_ATT_DATATMsgArray mstrAttDataTArray = findAttachments(bizMsg);
        EZDDebugOutput.println(1, "findByCondition(MSTR_ATT_DATA) = " + mstrAttDataTArray, this);

        bizMsg.A.clear();
        bizMsg.A.setValidCount(mstrAttDataTArray.length());

        // find master att data activity
        MSTR_ACTV_TPTMsgArray tMsgArray = (MSTR_ACTV_TPTMsgArray)ZYPCodeDataUtil.findAll("MSTR_ACTV_TP");
        
        for (int i = 0, length=mstrAttDataTArray.length(); i < length; i++) {

            EZDMsg.copy(mstrAttDataTArray.no(i), null, bizMsg.A.no(i), null);

            // No
            bizMsg.A.no(i).xxNum.setValue(i + 1);

            // Attachment data volume & volume unit
            calcAttachmentVol(bizMsg.A.no(i));

            // Attachment description
            if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).mstrAttDataDescTxt)) {
                bizMsg.A.no(i).mstrAttDataDescTxt_FG.setValue(ZYPConstant.FLG_ON_Y);
            }
            
            // Activity
            if (ZYPCommonFunc.hasValue(mstrAttDataTArray.no(i).mstrActvCd)) {
            	
            	for (int j=0,len=tMsgArray.length();j<len;j++) {
            		if (tMsgArray.no(j).mstrActvCd.getValue().equals(mstrAttDataTArray.no(i).mstrActvCd.getValue())) {
                    	ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).mstrActvNm,tMsgArray.no(j).mstrActvNm);            			
            		}
            	}
            }

            // EZINTIME
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).ezInTime, mstrAttDataTArray.no(i).ezInTime);
            String ezInTime = bizMsg.A.no(i).ezInTime.getValue();
            // Update QC2852 T.Kawazu Start
            // mm/dd/yyyy
            //bizMsg.A.no(i).ezInTime_DT.setValue(ezInTime.substring(4, 6) + "/" + ezInTime.substring(6, 8) + "/" + ezInTime.substring(0, 4));
            bizMsg.A.no(i).ezInTime_DT.setValue(ZYPDateUtil.formatEzd8ToDisp(ezInTime.substring(0, 8)));
            // Update QC2852 T.Kawazu End
            // HH:mm:ss
            bizMsg.A.no(i).ezInTime_TM.setValue(ezInTime.substring(8, 10) + ":" + ezInTime.substring(10, 12) + ":" + ezInTime.substring(12, 14));
         }

    }

    /**
     * business logic to [Download attachment file] on NWAL0370Scrn00.
     * @param bizMsg NMAL6130CMsg
     */
    private void doProcess_NMAL6130Scrn00_Download(NMAL6130CMsg bizMsg) {

        // findByKey [MSTR_ATT_DATA]
        MSTR_ATT_DATATMsg mstrAttDataTMsg = new MSTR_ATT_DATATMsg();
        mstrAttDataTMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
        mstrAttDataTMsg.mstrAttDataPk.setValue(bizMsg.A.no(bizMsg.xxCellIdx_DL.getValueInt()).mstrAttDataPk.getValue());

        mstrAttDataTMsg = (MSTR_ATT_DATATMsg) EZDTBLAccessor.findByKey(mstrAttDataTMsg);

        // nothing data to download.
        if (mstrAttDataTMsg == null) {
            doDownloadDataNotFoundProcess(bizMsg);
            return;

        } else {

            ZYPBLOBAccessor blobAccessor = new ZYPBLOBAccessor(mstrAttDataTMsg);
            byte[] downloadData = blobAccessor.findBLOB(DB_COL_BLOB);

            // nothing data to download.
            if (downloadData == null) {
                doDownloadDataNotFoundProcess(bizMsg);
                return;

            } else {

                bizMsg.xxFileData_DL.setTempFilePath(null, mstrAttDataTMsg.mstrAttDataNm.getValue(), "");
                ZYPFileWriter.writeFile(bizMsg.xxFileData_DL.getTempFilePath(), downloadData);
            }
        }
    }

    /**
     * business logic to [Upload attachment file] on NWAL0370Scrn00.
     * @param bizMsg NMAL6130CMsg
     */
    private void doProcess_NMAL6130Scrn00_Upload(NMAL6130CMsg bizMsg) {
        doProcess_NMAL6130_INIT(bizMsg);
    }

    /**
     * business logic to [Delete attachment files] on NWAL0370Scrn00.
     * @param bizMsg NMAL6130CMsg
     */
    private void doProcess_NMAL6130Scrn00_Delete(NMAL6130CMsg bizMsg) {
        doProcess_NMAL6130_INIT(bizMsg);
    }

    private void initializeConstTableInfo(NMAL6130CMsg bizMsg) {
        EZDDebugOutput.println(1, "+++++++++++++++++++++++++ initializeConstTableInfo:START +++++++++++++++++++++++++", this);

        int attachmentsLimitNum = findAttachmentsLimitNum(bizMsg);
        EZDDebugOutput.println(1, "attachmentsLimitNum = " + attachmentsLimitNum, this);

        int authorizeFileVol = findAuthorizeFileVol(bizMsg);
        EZDDebugOutput.println(1, "authorizeFileVol    = " + authorizeFileVol, this);

        String systemNgFileEx = findSystemNgFileEx(bizMsg);
        EZDDebugOutput.println(1, "systemNgFileEx      = " + systemNgFileEx, this);

        String authorizeFileEx = findAuthorizeFileEx(bizMsg);
        EZDDebugOutput.println(1, "authorizeFileEx     = " + authorizeFileEx, this);

        EZDDebugOutput.println(1, "+++++++++++++++++++++++++ initializeConstTableInfo:E N D +++++++++++++++++++++++++", this);
    }

    private int findAttachmentsLimitNum(NMAL6130CMsg bizMsg) {

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

    private int findAuthorizeFileVol(NMAL6130CMsg bizMsg) {

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

    private String findSystemNgFileEx(NMAL6130CMsg bizMsg) {

        // EZDCStringItem keyItem = bizMsg.varCharConstNm_I1;
        EZDCStringItem valItem = bizMsg.varCharConstVal_I1;

        VAR_CHAR_CONSTTMsg tMsg = new VAR_CHAR_CONSTTMsg();
        tMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
        tMsg.varCharConstNm.setValue("MSTR_ATT_SYSTEM_NG_FILE_EX");

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

    private String findAuthorizeFileEx(NMAL6130CMsg bizMsg) {

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

    private MSTR_ATT_DATATMsgArray findAttachments(NMAL6130CMsg bizMsg) {

        // findByCondition [MSTR_ATT_DATA]
        MSTR_ATT_DATATMsg mstrAttDataT = new MSTR_ATT_DATATMsg();

        mstrAttDataT.setSQLID("001");
        mstrAttDataT.setMaxCount(bizMsg.A.length());
        mstrAttDataT.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        mstrAttDataT.setConditionValue("mstrBizId01", bizMsg.mstrBizId_I.getValue());
        mstrAttDataT.setConditionValue("mstrAttDataGrpTxt01", bizMsg.mstrAttDataGrpTxt_I.getValue());

        return (MSTR_ATT_DATATMsgArray) EZDTBLAccessor.findByCondition(mstrAttDataT);
    }

    private void calcAttachmentVol(NMAL6130_ACMsg aCMsg) {

        BigDecimal dataVol = ZYPCommonFunc.getBigDecimal(aCMsg.mstrAttDataVol, BigDecimal.ZERO.toString());

        switch (dataVol.compareTo(BYTE_KB)) {

            // byte
            case -1:
                aCMsg.mstrAttDataVol_O.setValue(dataVol);
                aCMsg.xxFileVolUnit.setValue("byte");
                break;

            // KB
            case 0:
                aCMsg.mstrAttDataVol_O.setValue(dataVol.divide(BYTE_KB, 0, BigDecimal.ROUND_HALF_UP));
                aCMsg.xxFileVolUnit.setValue("KB");
                break;

            case 1:
                switch (dataVol.compareTo(BYTE_MB)) {

                    // KB
                    case -1:
                        aCMsg.mstrAttDataVol_O.setValue(dataVol.divide(BYTE_KB, 0, BigDecimal.ROUND_HALF_UP));
                        aCMsg.xxFileVolUnit.setValue("KB");
                        break;

                    // MB
                    case 0:
                    case 1:
                        aCMsg.mstrAttDataVol_O.setValue(dataVol.divide(BYTE_MB, 0, BigDecimal.ROUND_HALF_UP));
                        aCMsg.xxFileVolUnit.setValue("MB");
                        break;

                }
                break;

            default:
                // nothing to do.
                break;
        }
    }

    private void doDownloadDataNotFoundProcess(NMAL6130CMsg bizMsg) {

        bizMsg.setMessageInfo(MSG_ID_DOWNLOADFILE_NOT_FOUND, new String[] {bizMsg.A.no(bizMsg.xxCellIdx_DL.getValueInt()).mstrAttDataNm.getValue() });

        doProcess_NMAL6130_INIT(bizMsg);
    }

}
