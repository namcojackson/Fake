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

import java.math.BigDecimal;

import parts.common.EZDCBigDecimalItem;
import parts.common.EZDCMsg;
import parts.common.EZDCStringItem;
import parts.common.EZDDebugOutput;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.ZYPL0300.constant.ZYPL0300Constant;
import business.db.ATT_DATATMsg;
import business.db.ATT_DATATMsgArray;
import business.db.NUM_CONSTTMsg;
import business.db.VAR_CHAR_CONSTTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPBLOBAccessor;
import com.canon.cusa.s21.framework.ZYP.file.ZYPFileWriter;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

public class ZYPL0300BL02 extends S21BusinessHandler implements ZYPL0300Constant {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {

            String screenAplID = cMsg.getScreenAplID();

            if ("ZYPL0300_INIT".equals(screenAplID)) {
                doProcess_ZYPL0300_INIT((ZYPL0300CMsg) cMsg);

            } else if ("ZYPL0300Scrn00_Upload".equals(screenAplID)) {
                doProcess_ZYPL0300Scrn00_Upload((ZYPL0300CMsg) cMsg);

            } else if ("ZYPL0300Scrn00_Download".equals(screenAplID)) {
                doProcess_ZYPL0300Scrn00_Download((ZYPL0300CMsg) cMsg);

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
     * business logic for initialize ZYPL0300Scrn00.
     * @param bizMsg ZYPL0300CMsg
     */
    private void doProcess_ZYPL0300_INIT(ZYPL0300CMsg bizMsg) {

        if ("ZYPL0300_INIT".equals(bizMsg.getScreenAplID())) {
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

            // Attachment data volume & volume unit
            calcAttachmentVol(bizMsg.A.no(i));

            // Attachment description
            if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).attDataDescTxt)) {
                bizMsg.A.no(i).attDataDescTxt_FG.setValue(ZYPConstant.FLG_ON_Y);
            }

            // EZINTIME
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).ezInTime, attDataTArray.no(i).ezInTime);
            String ezInTime = bizMsg.A.no(i).ezInTime.getValue();
            // mm/dd/yyyy
            bizMsg.A.no(i).ezInTime_DT.setValue(ezInTime.substring(4, 6) + "/" + ezInTime.substring(6, 8) + "/" + ezInTime.substring(0, 4));
            // HH:mm:ss
            bizMsg.A.no(i).ezInTime_TM.setValue(ezInTime.substring(8, 10) + ":" + ezInTime.substring(10, 12) + ":" + ezInTime.substring(12, 14));
        }

    }

    /**
     * business logic to [Download attachment file] on NWAL0370Scrn00.
     * @param bizMsg ZYPL0300CMsg
     */
    private void doProcess_ZYPL0300Scrn00_Download(ZYPL0300CMsg bizMsg) {

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

            ZYPBLOBAccessor blobAccessor = new ZYPBLOBAccessor(attDataTMsg);
            byte[] downloadData = blobAccessor.findBLOB(DB_COL_BLOB);

            // nothing data to download.
            if (downloadData == null) {
                doDownloadDataNotFoundProcess(bizMsg);
                return;

            } else {

                bizMsg.xxFileData_DL.setTempFilePath(null, attDataTMsg.attDataNm.getValue(), "");
                ZYPFileWriter.writeFile(bizMsg.xxFileData_DL.getTempFilePath(), downloadData);
            }
        }
    }

    /**
     * business logic to [Upload attachment file] on NWAL0370Scrn00.
     * @param bizMsg ZYPL0300CMsg
     */
    private void doProcess_ZYPL0300Scrn00_Upload(ZYPL0300CMsg bizMsg) {
        doProcess_ZYPL0300_INIT(bizMsg);
    }

    /**
     * business logic to [Delete attachment files] on NWAL0370Scrn00.
     * @param bizMsg ZYPL0300CMsg
     */
    private void doProcess_ZYPL0300Scrn00_Delete(ZYPL0300CMsg bizMsg) {
        doProcess_ZYPL0300_INIT(bizMsg);
    }

    private void initializeConstTableInfo(ZYPL0300CMsg bizMsg) {
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

    private int findAttachmentsLimitNum(ZYPL0300CMsg bizMsg) {

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

    private int findAuthorizeFileVol(ZYPL0300CMsg bizMsg) {

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

    private String findSystemNgFileEx(ZYPL0300CMsg bizMsg) {

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

    private String findAuthorizeFileEx(ZYPL0300CMsg bizMsg) {

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

    private ATT_DATATMsgArray findAttachments(ZYPL0300CMsg bizMsg) {

        // findByCondition [ATT_DATA]
        ATT_DATATMsg attDataT = new ATT_DATATMsg();

        attDataT.setSQLID("001");
        attDataT.setMaxCount(bizMsg.A.length());
        attDataT.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        attDataT.setConditionValue("ezBusinessID01", bizMsg.ezBusinessID_I.getValue());
        attDataT.setConditionValue("attDataGrpTxt01", bizMsg.attDataGrpTxt_I.getValue());

        return (ATT_DATATMsgArray) EZDTBLAccessor.findByCondition(attDataT);
    }

    private void calcAttachmentVol(ZYPL0300_ACMsg aCMsg) {

        BigDecimal dataVol = ZYPCommonFunc.getBigDecimal(aCMsg.attDataVol, BigDecimal.ZERO.toString());

        switch (dataVol.compareTo(BYTE_KB)) {

            // byte
            case -1:
                aCMsg.attDataVol_O.setValue(dataVol);
                aCMsg.xxFileVolUnit.setValue("byte");
                break;

            // KB
            case 0:
                aCMsg.attDataVol_O.setValue(dataVol.divide(BYTE_KB, 0, BigDecimal.ROUND_HALF_UP));
                aCMsg.xxFileVolUnit.setValue("KB");
                break;

            case 1:
                switch (dataVol.compareTo(BYTE_MB)) {

                    // KB
                    case -1:
                        aCMsg.attDataVol_O.setValue(dataVol.divide(BYTE_KB, 0, BigDecimal.ROUND_HALF_UP));
                        aCMsg.xxFileVolUnit.setValue("KB");
                        break;

                    // MB
                    case 0:
                    case 1:
                        aCMsg.attDataVol_O.setValue(dataVol.divide(BYTE_MB, 0, BigDecimal.ROUND_HALF_UP));
                        aCMsg.xxFileVolUnit.setValue("MB");
                        break;

                }
                break;

            default:
                // nothing to do.
                break;
        }
    }

    private void doDownloadDataNotFoundProcess(ZYPL0300CMsg bizMsg) {

        bizMsg.setMessageInfo(MSG_ID_DOWNLOADFILE_NOT_FOUND, new String[] {bizMsg.A.no(bizMsg.xxCellIdx_DL.getValueInt()).attDataNm.getValue() });

        doProcess_ZYPL0300_INIT(bizMsg);
    }

}
