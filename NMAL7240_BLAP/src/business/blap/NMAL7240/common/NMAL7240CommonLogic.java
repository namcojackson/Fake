/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL7240.common;

import static business.blap.NMAL7240.constant.NMAL7240Constant.BIZ_ID;
import static business.blap.NMAL7240.constant.NMAL7240Constant.CHK_DUPLICATE_ITEM_NM_LIST;
import static business.blap.NMAL7240.constant.NMAL7240Constant.CHK_TERM_DUPLICATE_ITEM_NM_LIST;
import static business.blap.NMAL7240.constant.NMAL7240Constant.COMBI_START_END;
import static business.blap.NMAL7240.constant.NMAL7240Constant.CSV_EXTENSION;
import static business.blap.NMAL7240.constant.NMAL7240Constant.CSV_FILE_NAME;
import static business.blap.NMAL7240.constant.NMAL7240Constant.CSV_HDR;
import static business.blap.NMAL7240.constant.NMAL7240Constant.DELETE_SEARCH;
import static business.blap.NMAL7240.constant.NMAL7240Constant.EFFECTIVE_DATE_FROM;
import static business.blap.NMAL7240.constant.NMAL7240Constant.EFF_FROM_DT_A1;
import static business.blap.NMAL7240.constant.NMAL7240Constant.EFF_THRU_DT_A1;
import static business.blap.NMAL7240.constant.NMAL7240Constant.FREIGHT_RATE;
import static business.blap.NMAL7240.constant.NMAL7240Constant.FROM_SCALE_QUANTITY;
import static business.blap.NMAL7240.constant.NMAL7240Constant.LINE_OF_BUSINESS;
import static business.blap.NMAL7240.constant.NMAL7240Constant.MESSAGE_KIND_ERROR;
import static business.blap.NMAL7240.constant.NMAL7240Constant.NMAM0072E;
import static business.blap.NMAL7240.constant.NMAL7240Constant.NMAM0163E;
import static business.blap.NMAL7240.constant.NMAL7240Constant.NMAM8187E;
import static business.blap.NMAL7240.constant.NMAL7240Constant.NMAM8191E;
import static business.blap.NMAL7240.constant.NMAL7240Constant.SAVE_SEARCH;
import static business.blap.NMAL7240.constant.NMAL7240Constant.SHIPPING_SERVICE_LEVEL;
import static business.blap.NMAL7240.constant.NMAL7240Constant.UPLOAD_FORMAT_ERROR;
import static business.blap.NMAL7240.constant.NMAL7240Constant.UPLOAD_READ_ERROR;
import static business.blap.NMAL7240.constant.NMAL7240Constant.YYYYMMDD_LENGTH;
import static business.blap.NMAL7240.constant.NMAL7240Constant.ZONE;
import static business.blap.NMAL7240.constant.NMAL7240Constant.ZZM9000E;
import static business.blap.NMAL7240.constant.NMAL7240Constant.ZZZM9003I;
import static business.blap.NMAL7240.constant.NMAL7240Constant.DATE_FORMAT_PADDING_ZERO;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCSVInFile;
import parts.common.EZDItemAttrDefines;
import parts.common.EZDItemAttribute;
import parts.common.EZDMsg;
import parts.common.EZDMsgArray;
import parts.common.EZDSItem;
import parts.common.EZDSchemaInfo;
import business.blap.NMAL7240.NMAL7240CMsg;
import business.blap.NMAL7240.NMAL7240Query;
import business.blap.NMAL7240.NMAL7240SMsg;
import business.blap.NMAL7240.NMAL7240_ACMsg;
import business.blap.NMAL7240.NMAL7240_ASMsg;
import business.blap.NMAL7240.NMAL7240_ASMsgArray;
import business.db.LINE_BIZ_TPTMsg;
import business.db.LINE_BIZ_TPTMsgArray;
import business.db.SHPG_SVC_LVLTMsg;
import business.db.SHPG_SVC_LVLTMsgArray;
import business.file.NMAL7240F00FMsg;
import business.parts.NSZC033001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC033001.NSZC033001;
import com.canon.cusa.s21.api.NSZ.NSZC033001.constant.NSZC033001Constant;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPRecHistUtil;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;

/**
 *<pre>
 * NMAL7240CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/05   Fujitsu         W.Honda         Create          N/A
 * 2016/05/17   Fujitsu         W.Honda         Update          QC#7040
 * 2016/08/10   Fujitsu         R.Nakamura      Update          QC#12160
 * 2016/09/15   SRAA            Y.Chen          Update          QC#9816
 * 2018/03/13   Fujitsu         K.Kato          Update          QC#30715
 *</pre>
 */
public class NMAL7240CommonLogic {

    /**
     * callNszc0330forDeleteSearch
     * @param bizMsg NMAL7240CMsg
     * @param glblMsg NMAL7240SMsg
     * @param srchOptUsrId String
     * @param glblCmpyCd String
     */
    public static void callNszc0330forDeleteSearch(NMAL7240CMsg bizMsg, NMAL7240SMsg glblMsg, String srchOptUsrId, String glblCmpyCd) {
        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        pMsg.clear();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_DELETE);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, bizMsg.srchOptPk_S);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, BIZ_ID);

        if (callNszc0330(bizMsg, pMsg)) {
            createSavedSearchOptionsPullDown(bizMsg, srchOptUsrId);
            bizMsg.srchOptNm_S.clear();
            // QC#7040 2016/05/17 Mod start
//            bizMsg.setMessageInfo("ZZZM9003I", new String[] {"Delete Search"});
            bizMsg.setMessageInfo(ZZZM9003I, new String[] {DELETE_SEARCH});
            // QC#7040 2016/05/17 Mod end
        }
    }

    /**
     * callNszc0330forSearchOption
     * @param bizMsg NMAL7240CMsg
     * @param glblMsg NMAL7240SMsg
     * @param srchOptUsrId String
     * @param glblCmpyCd String
     */
    public static void callNszc0330forSearchOption(NMAL7240CMsg bizMsg, NMAL7240SMsg glblMsg, String srchOptUsrId, String glblCmpyCd) {
        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        pMsg.clear();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_SEARCH);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptUsrId, srchOptUsrId);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, bizMsg.srchOptPk_S);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, BIZ_ID);

        if (!callNszc0330(bizMsg, pMsg)) {
            return;
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.lineBizTpCd, pMsg.srchOptTxt_01.getValue());
        if (ZYPCommonFunc.isNumberCheck(pMsg.srchOptTxt_02.getValue())) {
            ZYPEZDItemValueSetter.setValue(bizMsg.frtZoneNum, new BigDecimal(pMsg.srchOptTxt_02.getValue()));
        } else {
            bizMsg.frtZoneNum.clear();
        }
        ZYPEZDItemValueSetter.setValue(bizMsg.xxDsMultMsgDplyTxt_HL, pMsg.srchOptTxt_03.getValue());
        if (ZYPDateUtil.isValidDate(pMsg.srchOptTxt_04.getValue(), "yyyyMMdd")) {
            ZYPEZDItemValueSetter.setValue(bizMsg.effFromDt, pMsg.srchOptTxt_04.getValue());
        } else {
            bizMsg.effFromDt.clear();
        }
        if (ZYPDateUtil.isValidDate(pMsg.srchOptTxt_05.getValue(), "yyyyMMdd")) {
            ZYPEZDItemValueSetter.setValue(bizMsg.effThruDt, pMsg.srchOptTxt_05.getValue());
        } else {
            bizMsg.effThruDt.clear();
        }
        ZYPEZDItemValueSetter.setValue(bizMsg.actvFlg, pMsg.srchOptTxt_06.getValue());

    }

    private static boolean callNszc0330(NMAL7240CMsg bizMsg, NSZC033001PMsg pMsg) {
        // Search Option API(NSZC0330) is executed
        NSZC033001 api = new NSZC033001();
        api.execute(pMsg, ONBATCH_TYPE.ONLINE);

        String msgId;
        if (!S21ApiUtil.getXxMsgIdList(pMsg).isEmpty()) {
            for (int j = 0; j < pMsg.xxMsgIdList.length(); j++) {
                if (ZYPCommonFunc.hasValue(pMsg.xxMsgIdList.no(j).xxMsgId)) {
                    msgId = pMsg.xxMsgIdList.no(j).xxMsgId.getValue();
                    bizMsg.setMessageInfo(msgId);
                    // QC#7040 2016/05/17 Mod start
//                    if (msgId.endsWith("E")) {
                    if (msgId.endsWith(MESSAGE_KIND_ERROR)) {
                    // QC#7040 2016/05/17 Mod end
                        bizMsg.srchOptPk_S.setErrorInfo(1, msgId);
                        bizMsg.srchOptNm_S.setErrorInfo(1, msgId);
                        return false;
                    }
                }
            }
        }
        return true;
    }


    /**
     * createSavedSearchOptionsPullDown
     * @param bizMsg NMAL7240CMsg
     * @param srchOptUsrId String
     */
    public static void createSavedSearchOptionsPullDown(NMAL7240CMsg bizMsg, String srchOptUsrId) {
        S21SsmEZDResult ssmResult = NMAL7240Query.getInstance().getSavedSearchOptionList(srchOptUsrId);
        if (!ssmResult.isCodeNormal()) {
            bizMsg.srchOptPk_L.clear();
            bizMsg.srchOptNm_L.clear();
            return;
        }

        bizMsg.srchOptPk_L.clear();
        bizMsg.srchOptNm_L.clear();
        List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
        for (int i = 0; i < resultList.size() && i < bizMsg.srchOptPk_L.length(); i++) {
            Map<String, Object> resultMap = (Map<String, Object>) resultList.get(i);
            bizMsg.srchOptPk_L.no(i).setValue((BigDecimal) resultMap.get("SRCH_OPT_PK"));
            bizMsg.srchOptNm_L.no(i).setValue((String) resultMap.get("SRCH_OPT_NM"));
        }

    }
    /**
     * callNszc0330forSaveSearch
     * @param bizMsg NMAL7240CMsg
     * @param glblMsg NMAL7240SMsg
     * @param srchOptUsrId String
     * @param glblCmpyCd String
     */
    public static void callNszc0330forSaveSearch(NMAL7240CMsg bizMsg, NMAL7240SMsg glblMsg, String srchOptUsrId, String glblCmpyCd) {
        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        pMsg.clear();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_INSERT_UPDATE);
        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptNm_S) //
                || isSameSaveSearchName(bizMsg)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, bizMsg.srchOptPk_S);
        }
        if (ZYPCommonFunc.hasValue(bizMsg.srchOptNm_S)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptNm, bizMsg.srchOptNm_S);
        } else {
            setSelectSaveSearchName(pMsg, bizMsg);
        }
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, BIZ_ID);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptUsrId, srchOptUsrId);

        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_01, bizMsg.lineBizTpCd.getValue());
        if (ZYPCommonFunc.hasValue(bizMsg.frtZoneNum)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_02, bizMsg.frtZoneNum.getValue().toString());
        }
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_03, bizMsg.xxDsMultMsgDplyTxt_HL.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_04, bizMsg.effFromDt.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_05, bizMsg.effThruDt.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_06, bizMsg.actvFlg.getValue());

        if (callNszc0330(bizMsg, pMsg)) {
            createSavedSearchOptionsPullDown(bizMsg, srchOptUsrId);
            ZYPEZDItemValueSetter.setValue(bizMsg.srchOptPk_S, pMsg.srchOptPk);
            bizMsg.srchOptNm_S.clear();
            // QC#7040 2016/05/17 Mod start
//            bizMsg.setMessageInfo("ZZZM9003I", new String[] {"Save Search"});
            bizMsg.setMessageInfo(ZZZM9003I, new String[] {SAVE_SEARCH});
            // QC#7040 2016/05/17 Mod end
        }
    }

    private static boolean isSameSaveSearchName(NMAL7240CMsg bizMsg) {
        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptPk_S)) {
            return false;
        }
        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptNm_S)) {
            return false;
        }

        for (int i = 0; i < bizMsg.srchOptNm_L.length(); i++) {
            if (!ZYPCommonFunc.hasValue(bizMsg.srchOptNm_L.no(i))) {
                return false;
            }
            if (bizMsg.srchOptPk_S.getValue().compareTo(bizMsg.srchOptPk_L.no(i).getValue()) == 0) {
                if (bizMsg.srchOptNm_S.getValue().equals(bizMsg.srchOptNm_L.no(i).getValue())) {
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    /**
     * isExistSaveSearchName
     * @param bizMsg NMAL7240CMsg
     * @return boolean
     */
    public static boolean isExistSaveSearchName(NMAL7240CMsg bizMsg) {
        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptNm_S)) {
            return false;
        }

        for (int i = 0; i < bizMsg.srchOptNm_L.length(); i++) {
            if (!ZYPCommonFunc.hasValue(bizMsg.srchOptNm_L.no(i))) {
                return false;
            }
            if (bizMsg.srchOptNm_S.getValue().equals(bizMsg.srchOptNm_L.no(i).getValue())) {
                if (ZYPCommonFunc.hasValue(bizMsg.srchOptPk_S) //
                        && bizMsg.srchOptPk_S.getValue().compareTo(bizMsg.srchOptPk_L.no(i).getValue()) == 0) {
                    return false;
                }
                return true;
            }
        }
        return false;
    }

    /**
     * setSelectSaveSearchName
     * @param pMsg NSZC033001PMsg
     * @param bizMsg NMAL7240CMsg
     */
    public static void setSelectSaveSearchName(NSZC033001PMsg pMsg, NMAL7240CMsg bizMsg) {
        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptPk_S)) {
            return;
        }

        for (int i = 0; i < bizMsg.srchOptNm_L.length(); i++) {
            if (!ZYPCommonFunc.hasValue(bizMsg.srchOptNm_L.no(i))) {
                return;
            }
            if (bizMsg.srchOptPk_S.getValue().compareTo(bizMsg.srchOptPk_L.no(i).getValue()) == 0) {
                ZYPEZDItemValueSetter.setValue(pMsg.srchOptNm, bizMsg.srchOptNm_L.no(i));
            }
        }
        return;
    }

    /**
     * write CsvFile
     * @param rs S21SsmEZDResult
     * @param bizMsg NMAL7240CMsg
     * @param glblCmpyCd GlobalCompanyCode
     * @throws SQLException SQLException
     */
    public static void writeCsvFileContInfo(ResultSet rs, NMAL7240CMsg bizMsg, String glblCmpyCd) throws SQLException {

        bizMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(CSV_FILE_NAME), CSV_EXTENSION);
        NMAL7240F00FMsg fMsg = new NMAL7240F00FMsg();
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(bizMsg.xxFileData.getTempFilePath(), fMsg);

        //write header
        csvOutFile.writeHeader(CSV_HDR);

        if (!rs.next()) {

            bizMsg.setMessageInfo("NZZM0000E", null);
            csvOutFile.close();
            return;
        }

        //write contents
        do {

            ZYPEZDItemValueSetter.setValue(fMsg.lineBizTpDescTxt_A1, rs.getString("LINE_BIZ_TP_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(fMsg.frtZoneNum_A1, rs.getBigDecimal("FRT_ZONE_NUM"));
            ZYPEZDItemValueSetter.setValue(fMsg.shpgSvcLvlDescTxt_A1, rs.getString("SHPG_SVC_LVL_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(fMsg.fromSclQty_A1, rs.getBigDecimal("FROM_SCL_QTY"));
            ZYPEZDItemValueSetter.setValue(fMsg.qtyUnitTpCd_A1, rs.getString("QTY_UNIT_TP_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.shpgFrtRate_A1, rs.getBigDecimal("SHPG_FRT_RATE"));
            ZYPEZDItemValueSetter.setValue(fMsg.frtRateCcyCd_A1, rs.getString("FRT_RATE_CCY_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.frtRatePerNum_A1, rs.getBigDecimal("FRT_RATE_PER_NUM"));
            ZYPEZDItemValueSetter.setValue(fMsg.perUnitTpCd_A1, rs.getString("PER_UNIT_TP_CD"));
            if (ZYPCommonFunc.hasValue(rs.getString("EFF_FROM_DT"))) {
                ZYPEZDItemValueSetter.setValue(fMsg.moveEffFromDtTxt_A1, formatDt(rs.getString("EFF_FROM_DT")));
            }
            if (ZYPCommonFunc.hasValue(rs.getString("EFF_FROM_DT"))) {
                ZYPEZDItemValueSetter.setValue(fMsg.moveEffThruDtTxt_A1, formatDt(rs.getString("EFF_THRU_DT")));
            }

            csvOutFile.write();
            fMsg.clear();
        } while (rs.next());

        csvOutFile.close();
    }

    /**
     * readCsvHeader_UPLOAD
     * @param dt String
     * @return String formated
     */
    private static String formatDt(String dt) {

        if (!ZYPCommonFunc.hasValue(dt)) {
            return "";
        } else if (dt.length() > YYYYMMDD_LENGTH) {
            dt = dt.substring(0, YYYYMMDD_LENGTH);
        }

        return ZYPDateUtil.formatEzd8ToDisp(dt, true);
    }

    /**
     * readHeaderCsvFile.
     * @param mappedFile EZDCSVInFile
     * @param bizMsg NMAL7240CMsg
     * @return boolean
     */
    public static boolean readHeaderCsvFile(EZDCSVInFile mappedFile, NMAL7240CMsg bizMsg) {
        int header = mappedFile.read();
        if (header == 1) {
            bizMsg.setMessageInfo("ZYEM0004E");
            return false;
        }
        return true;
    }

    /**
     * validateAndCopyToGlblMsg_UPLOAD
     * @param status int
     * @param count int
     * @param glblLineMsg NMAL7240_ASMsgArray
     * @param fMsg fMsgNMAL7240F01FMsg
     * @param bizMsg NMAL7240CMsg
     * @return boolean if error then true else false
     */
    public static boolean checkFormatAndCopyToGlblMsg_UPLOAD(
        int status
        , int count
        , NMAL7240_ASMsgArray glblLineMsg
        , NMAL7240F00FMsg fMsg
        , NMAL7240CMsg bizMsg) {

        /****************************************************************
         * Record count check
         ****************************************************************/
        if (count > glblLineMsg.length()) {
            bizMsg.setMessageInfo(NMAM8187E, new String[] {FREIGHT_RATE, String.valueOf(glblLineMsg.length())});
            return true;
        }

        /****************************************************************
         * CSV format check
         ****************************************************************/
        if (checkFormatCSV(status, count, fMsg, bizMsg, glblLineMsg, CSV_HDR)) {
            return true;
        }

// QC#9816
//        // Add Start 2016/08/10 QC#12160
//        ZYPEZDItemValueSetter.setValue(fMsg.qtyUnitTpCd_A1, bizMsg.qtyUnitTpCd_H);
//        ZYPEZDItemValueSetter.setValue(fMsg.frtRateCcyCd_A1, bizMsg.frtRateCcyCd_H);
//        ZYPEZDItemValueSetter.setValue(fMsg.perUnitTpCd_A1, bizMsg.perUnitTpCd_H);
//        // Add End 2016/08/10 QC#12160
//        /****************************************************************
//         * Copy FMsg to SMsg.
//         ****************************************************************/
//        EZDMsg.copy(fMsg, null, glblLineMsg.no(count), null);
//        ZYPEZDItemValueSetter.setValue(glblLineMsg.no(count).effFromDt_A1, fMsg.effFromDtTxt_A1.getValue());
//        ZYPEZDItemValueSetter.setValue(glblLineMsg.no(count).effThruDt_A1, fMsg.effThruDtTxt_A1.getValue());

        return false;
    }

    /**
     * checkFormatCSV
     * @param status int
     * @param uploadCount int
     * @param fMsg fMsgNMAL7240F00FMsg
     * @param bizMsg NMAL7240CMsg
     * @param glblLineMsg NMAL7240_ASMsgArray
     * @param colStrList String[]
     * @return boolean if error then true else false
     */
    public static boolean checkFormatCSV(int status, int uploadCount, NMAL7240F00FMsg fMsg, NMAL7240CMsg bizMsg, NMAL7240_ASMsgArray glblLineMsg, String[] colStrList) {
        // QC#9816
        NMAL7240_ASMsg asMsg = glblLineMsg.no(uploadCount);
        ZYPEZDItemValueSetter.setValue(fMsg.qtyUnitTpCd_A1, bizMsg.qtyUnitTpCd_H);
        ZYPEZDItemValueSetter.setValue(fMsg.frtRateCcyCd_A1, bizMsg.frtRateCcyCd_H);
        ZYPEZDItemValueSetter.setValue(fMsg.perUnitTpCd_A1, bizMsg.perUnitTpCd_H);
        EZDMsg.copy(fMsg, null, asMsg, null);

        // 2019/03/13 QC#30715 add start
        EZDSchemaInfo ezdSchemaInfo = new NMAL7240_ASMsg().getSchema();
        // 2019/03/13 QC#30715 add End

        List<EZDSItem> itemList = new ArrayList<EZDSItem>();
        itemList.add(asMsg.lineBizTpDescTxt_A1);
        itemList.add(asMsg.frtZoneNum_A1);
        itemList.add(asMsg.shpgSvcLvlDescTxt_A1);
        itemList.add(asMsg.fromSclQty_A1);
        itemList.add(null);
        itemList.add(asMsg.shpgFrtRate_A1);
        itemList.add(null);
        itemList.add(asMsg.frtRatePerNum_A1);
        itemList.add(null);
        itemList.add(asMsg.effFromDt_A1);
        itemList.add(asMsg.effThruDt_A1);
        
        // QC#9816
        // int displayRowNum = uploadCount + 1;
        int displayRowNum = uploadCount + 2;
        if (status == UPLOAD_READ_ERROR) {
            bizMsg.setMessageInfo(NMAM8191E, new String[]{"row #" + String.valueOf(displayRowNum)});
            return true;
        }

        if (UPLOAD_READ_ERROR < status && status <= UPLOAD_READ_ERROR + colStrList.length) {
            int colIdx = status - UPLOAD_READ_ERROR;
            // QC#9816
            // bizMsg.setMessageInfo(NMAM8191E, new String[]{String.valueOf(displayRowNum) + "row, " + colStrList[colIdx - 1]});
            // return true;
            if(itemList.get(colIdx - 1) != null){
                itemList.get(colIdx - 1).setErrorInfo(1, NMAM8191E, new String[]{"row #" + String.valueOf(displayRowNum)});
            } else {
                asMsg.xxChkBox_A1.setErrorInfo(1, NMAM8191E, new String[]{"row #" + String.valueOf(displayRowNum) + " " + colStrList[colIdx - 1]});
            }
        }
        if (UPLOAD_FORMAT_ERROR < status && status <= UPLOAD_FORMAT_ERROR + colStrList.length) {
            int colIdx = status - UPLOAD_FORMAT_ERROR;
            // QC#9816
            // bizMsg.setMessageInfo(NMAM8191E, new String[]{String.valueOf(displayRowNum) + "row, " + colStrList[colIdx - 1]});
            // return true;
            if(itemList.get(colIdx - 1) != null){
                itemList.get(colIdx - 1).setErrorInfo(1, NMAM8191E, new String[]{"row #" + String.valueOf(displayRowNum)});
            } else {
                asMsg.xxChkBox_A1.setErrorInfo(1, NMAM8191E, new String[]{"row #" + String.valueOf(displayRowNum) + " " + colStrList[colIdx - 1]});
            }
        }

        if (ZYPCommonFunc.hasValue(fMsg.moveEffFromDtTxt_A1)) {
            if (fMsg.moveEffFromDtTxt_A1.getValue().contains("/")) {
                String sepDeleteString = fMsg.moveEffFromDtTxt_A1.getValue();
                // 2019/03/13 QC#30715 add start
                sepDeleteString = formatDateStr(sepDeleteString);
                // 2019/03/13 QC#30715 add End
                sepDeleteString = sepDeleteString.replaceAll("/", "");
                ZYPEZDItemValueSetter.setValue(fMsg.moveEffFromDtTxt_A1, ZYPDateUtil.formatDisp8ToEzd(sepDeleteString));
            }
            // 2019/03/13 QC#30715 Mod start
            if (fMsg.moveEffFromDtTxt_A1.getValue().length() > ezdSchemaInfo.getAttr("effFromDt_A1").getDigit()) {
                asMsg.effFromDt_A1.setErrorInfo(1, NMAM8191E, new String[]{"row #" + String.valueOf(displayRowNum)});
            //if (!ZYPDateUtil.isValidDate(fMsg.moveEffFromDtTxt_A1.getValue(), ZYPDateUtil.TYPE_YYYYMMDD)) {
            } else if (!ZYPDateUtil.isValidDate(fMsg.moveEffFromDtTxt_A1.getValue(), ZYPDateUtil.TYPE_YYYYMMDD)) {
                // QC#9816
                // bizMsg.setMessageInfo(NMAM8191E, new String[]{String.valueOf(displayRowNum) + "row, Effective Date From"});
                // return true;
                asMsg.effFromDt_A1.setErrorInfo(1, NMAM8191E, new String[]{"row #" + String.valueOf(displayRowNum)});
            } else {
                ZYPEZDItemValueSetter.setValue(asMsg.effFromDt_A1, fMsg.moveEffFromDtTxt_A1.getValue());
            }
        }

        if (ZYPCommonFunc.hasValue(fMsg.moveEffThruDtTxt_A1)) {
            if (fMsg.moveEffThruDtTxt_A1.getValue().contains("/")) {
                String sepDeleteString = fMsg.moveEffThruDtTxt_A1.getValue();
                // 2019/03/13 QC#30715 add start
                sepDeleteString = formatDateStr(sepDeleteString);
                // 2019/03/13 QC#30715 add End
                sepDeleteString = sepDeleteString.replaceAll("/", "");
                ZYPEZDItemValueSetter.setValue(fMsg.moveEffThruDtTxt_A1, ZYPDateUtil.formatDisp8ToEzd(sepDeleteString));
            }
            // 2019/03/13 QC#30715 Mod start
            if (fMsg.moveEffThruDtTxt_A1.getValue().length() > ezdSchemaInfo.getAttr("effThruDt_A1").getDigit()) {
                asMsg.effThruDt_A1.setErrorInfo(1, NMAM8191E, new String[]{"row #" + String.valueOf(displayRowNum)});
            //if (!ZYPDateUtil.isValidDate(fMsg.moveEffThruDtTxt_A1.getValue(), ZYPDateUtil.TYPE_YYYYMMDD)) {
            } else if (!ZYPDateUtil.isValidDate(fMsg.moveEffThruDtTxt_A1.getValue(), ZYPDateUtil.TYPE_YYYYMMDD)) {
                // QC#9816
                // bizMsg.setMessageInfo(NMAM8191E, new String[]{String.valueOf(displayRowNum) + "row, Effective Date To"});
                // return true;
                asMsg.effThruDt_A1.setErrorInfo(1, NMAM8191E, new String[]{"row #" + String.valueOf(displayRowNum)});
            } else {
                ZYPEZDItemValueSetter.setValue(asMsg.effThruDt_A1, fMsg.moveEffThruDtTxt_A1.getValue());
            }
        }
        return false;
    }

    /**
     * validateAndCopyToGlblMsg_UPLOAD
     * @param glblAMsg NMAL7240_ASMsgArray
     * @param bizMsg NMAL7240CMsg
     * @param glblCmpyCd GlobalCompanyCode
     * @return boolean if error then true else false
     */
    public static boolean validateGlblMsg_UPLOAD(
        NMAL7240_ASMsgArray glblAMsg
        , NMAL7240CMsg bizMsg
        , String glblCmpyCd) {

        boolean isErr = false;

        for (int i = 0; i < glblAMsg.getValidCount(); i++) {
            // mandatory check
            if (!ZYPCommonFunc.hasValue(glblAMsg.no(i).lineBizTpDescTxt_A1)) {
                glblAMsg.no(i).lineBizTpDescTxt_A1.setErrorInfo(1, ZZM9000E, new String[] {LINE_OF_BUSINESS});
                isErr = true;
            }

            if (!ZYPCommonFunc.hasValue(glblAMsg.no(i).frtZoneNum_A1)) {
                glblAMsg.no(i).frtZoneNum_A1.setErrorInfo(1, ZZM9000E, new String[] {ZONE});
                isErr = true;
            }

            if (!ZYPCommonFunc.hasValue(glblAMsg.no(i).shpgSvcLvlDescTxt_A1)) {
                glblAMsg.no(i).shpgSvcLvlDescTxt_A1.setErrorInfo(1, ZZM9000E, new String[] {SHIPPING_SERVICE_LEVEL});
                isErr = true;
            }

            if (!ZYPCommonFunc.hasValue(glblAMsg.no(i).fromSclQty_A1)) {
                glblAMsg.no(i).fromSclQty_A1.setErrorInfo(1, ZZM9000E, new String[] {FROM_SCALE_QUANTITY});
                isErr = true;
            }

            if (!ZYPCommonFunc.hasValue(glblAMsg.no(i).effFromDt_A1)) {
                glblAMsg.no(i).effFromDt_A1.setErrorInfo(1, ZZM9000E, new String[] {EFFECTIVE_DATE_FROM});
                isErr = true;
            }

            // 2019/03/13 QC#30715 add start
            if (glblAMsg.no(i).effFromDt_A1.isError()) {
                isErr = true;
            }

            if (glblAMsg.no(i).effThruDt_A1.isError()) {
                isErr = true;
            }
            // 2019/03/13 QC#30715 add End

            // Record existence check
            if (ZYPCommonFunc.hasValue(glblAMsg.no(i).lineBizTpDescTxt_A1)) {
                LINE_BIZ_TPTMsg inTMsg = new LINE_BIZ_TPTMsg();
                LINE_BIZ_TPTMsgArray outTMsg = null;
                ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(inTMsg.lineBizTpDescTxt, glblAMsg.no(i).lineBizTpDescTxt_A1);
                outTMsg = (LINE_BIZ_TPTMsgArray) S21CodeTableAccessor.findByCondition(inTMsg);
                if (outTMsg.length() < 1) {
                    glblAMsg.no(i).lineBizTpDescTxt_A1.setErrorInfo(1, NMAM0163E, new String[] {glblAMsg.no(i).lineBizTpDescTxt_A1.getValue(), inTMsg.getTableName()});
                    isErr = true;
                }
            }

            if (ZYPCommonFunc.hasValue(glblAMsg.no(i).shpgSvcLvlDescTxt_A1)) {
                SHPG_SVC_LVLTMsg inTMsg = new SHPG_SVC_LVLTMsg();
                SHPG_SVC_LVLTMsgArray outTMsg = null;
                ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(inTMsg.shpgSvcLvlDescTxt, glblAMsg.no(i).shpgSvcLvlDescTxt_A1);
                outTMsg = (SHPG_SVC_LVLTMsgArray) S21CodeTableAccessor.findByCondition(inTMsg);
                if (outTMsg.length() < 1) {
                    glblAMsg.no(i).shpgSvcLvlDescTxt_A1.setErrorInfo(1, NMAM0163E, new String[] {glblAMsg.no(i).shpgSvcLvlDescTxt_A1.getValue(), inTMsg.getTableName()});
                    isErr = true;
                }
            }
        }

        return isErr;
    }

    /**
     * Set to the global Message and Copy to the bBusiness Message.
     * @param ssmResult S21SsmEZDResult
     * @param bizMsg NMAL7240CMsg
     * @param glblMsg NMAL7240SMsg
     */
    public static void setGlblMsgAndCopyBizMsg(S21SsmEZDResult ssmResult, NMAL7240CMsg bizMsg, NMAL7240SMsg glblMsg) {

        // SSM Result
        List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
        int i = 0;

        for (; i < glblMsg.A.getValidCount(); i++) {
            Map<String, Object> resultMap = (Map<String, Object>) resultList.get(i);

            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).frtRateShpgPk_A1, (BigDecimal) resultMap.get("FRT_RATE_SHPG_PK"));
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).lineBizTpCd_A1, (String) resultMap.get("LINE_BIZ_TP_CD"));
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).lineBizTpDescTxt_A1, (String) resultMap.get("LINE_BIZ_TP_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).frtZoneNum_A1, (BigDecimal) resultMap.get("FRT_ZONE_NUM"));
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).shpgSvcLvlCd_A1, (String) resultMap.get("SHPG_SVC_LVL_CD"));
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).shpgSvcLvlDescTxt_A1, (String) resultMap.get("SHPG_SVC_LVL_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).fromSclQty_A1, (BigDecimal) resultMap.get("FROM_SCL_QTY"));
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).qtyUnitTpCd_A1, (String) resultMap.get("QTY_UNIT_TP_CD"));
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).shpgFrtRate_A1, (BigDecimal) resultMap.get("SHPG_FRT_RATE"));
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).frtRateCcyCd_A1, (String) resultMap.get("FRT_RATE_CCY_CD"));
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).frtRatePerNum_A1, (BigDecimal) resultMap.get("FRT_RATE_PER_NUM"));
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).perUnitTpCd_A1, (String) resultMap.get("PER_UNIT_TP_CD"));
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).effFromDt_A1, (String) resultMap.get("EFF_FROM_DT"));
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).effThruDt_A1, (String) resultMap.get("EFF_THRU_DT"));
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).ezUpTime_A1, (String) resultMap.get("EZUPTIME"));
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).ezUpTimeZone_A1, (String) resultMap.get("EZUPTIMEZONE"));
            // QC#9810 2016/11/02 ADD START
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).xxRecHistCratTs_A1, (String) resultMap.get("XX_REC_HIST_CRAT_TS"));
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).xxRecHistCratByNm_A1, ZYPRecHistUtil.getFullNameForRecHist((String) resultMap.get("XX_REC_HIST_CRAT_BY_NM")));
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).xxRecHistUpdTs_A1, (String) resultMap.get("XX_REC_HIST_UPD_TS"));
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).xxRecHistUpdByNm_A1, ZYPRecHistUtil.getFullNameForRecHist((String) resultMap.get("XX_REC_HIST_UPD_BY_NM")));
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).xxRecHistTblNm_A1, (String) resultMap.get("XX_REC_HIST_TBL_NM"));
            // QC#9810 2016/11/02 ADD END

            EZDMsg.copy(glblMsg.A.no(i), null, bizMsg.A.no(i), null);
        }

        bizMsg.A.setValidCount(i);
    }

    /**
     * Check Active Duplication Rate.
     * @param bizLineMsg NMAL7240_ACMsg
     * @param deleteList List<BigDecimal>
     * @param updateList List<BigDecimal>
     * @return check result
     */
    public static boolean checkActiveDuplicateRate(NMAL7240_ACMsg bizLineMsg, List<BigDecimal> deleteList, List<BigDecimal> updateList) {

        S21SsmEZDResult ssmResult = NMAL7240Query.getInstance().checkActiveDuplicationZone(bizLineMsg);

        if (!ssmResult.isCodeNotFound()) {
            List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();

            for (int j = 0; j < resultList.size(); j++) {
                Map<String, Object> resultMap = (Map<String, Object>) resultList.get(j);

                if (!updateList.contains((BigDecimal) resultMap.get("FRT_RATE_SHPG_PK"))
                        && !deleteList.contains((BigDecimal) resultMap.get("FRT_RATE_SHPG_PK"))) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Check Active Duplication Rate.
     * @param bizMsg NMAL7240CMsg
     * @return check result
     */
    public static boolean checkActiveRateForScreen(NMAL7240CMsg bizMsg) {

        Integer[] errIdxList = checkDupAttrb(bizMsg.A, CHK_DUPLICATE_ITEM_NM_LIST);
        if (errIdxList.length > 0) {
            for (int errIdx : errIdxList) {
                bizMsg.A.no(errIdx).effFromDt_A1.setErrorInfo(1, NMAM0072E, new String[]{COMBI_START_END});
                bizMsg.A.no(errIdx).effThruDt_A1.setErrorInfo(1, NMAM0072E, new String[]{COMBI_START_END});
            }
            bizMsg.setMessageInfo(NMAM0072E, new String[]{COMBI_START_END});
            return true;
        }

        Integer[] errIdxList2 = checkDupTermByGrp(bizMsg.A, EFF_FROM_DT_A1, EFF_THRU_DT_A1, CHK_TERM_DUPLICATE_ITEM_NM_LIST);
        if (errIdxList2.length > 0) {
            for (int errIdx : errIdxList2) {
                bizMsg.A.no(errIdx).effFromDt_A1.setErrorInfo(1, NMAM0072E, new String[]{COMBI_START_END});
                bizMsg.A.no(errIdx).effThruDt_A1.setErrorInfo(1, NMAM0072E, new String[]{COMBI_START_END});
            }
            bizMsg.setMessageInfo(NMAM0072E, new String[]{COMBI_START_END});
            return true;
        }
        return false;
    }

    /**
     * <pre>
     * checkDupAttrb
     * </pre>
     * @param msgAry EZDMsgArray
     * @param chkItemNmList Message Item Name for check.
     * @return Duplicate Index
     */
    public static Integer[] checkDupAttrb(EZDMsgArray msgAry, String[] chkItemNmList) {
        return checkDupAttrbByGrp(msgAry, chkItemNmList, null);
    }

    /**
     * <pre>
     * checkDupAttrbByGrp
     * </pre>
     * @param msgAry EZDMsgArray
     * @param chkItemNmList Message Item Name for check
     * @param grpItemNmList Message Item name for grouping
     * @return Duplicate Index
     */
    public static Integer[] checkDupAttrbByGrp(EZDMsgArray msgAry, String[] chkItemNmList, String[] grpItemNmList) {

        if (msgAry.getValidCount() <= 1) {
            return new Integer[0];
        }

        if (chkItemNmList == null
                || chkItemNmList.length == 0) {
            Integer[] errIdxList = new Integer[msgAry.getValidCount()];
            for (int i = 0; i < errIdxList.length; i++) {
                errIdxList[i] = i;
            }
            return errIdxList;
        }

        List<Integer> errIdxList = new ArrayList<Integer>(msgAry.getValidCount());
        List<String> grpList = new ArrayList<String>();
        Map<String, Integer> compMap = new HashMap<String, Integer>();

        for (int i = 0; i < msgAry.getValidCount(); i++) {
            EZDMsg msg = msgAry.get(i);
            String grpKey = makeCompVal(msg, grpItemNmList);
            String compVal = makeCompVal(msg, chkItemNmList);

            if (grpList.contains(grpKey)) {
                if (compMap.containsKey(compVal)) {
                    errIdxList.add(i);
                    errIdxList.add(0, compMap.get(compVal));
                    continue;
                }
                compMap.put(compVal, Integer.valueOf(i));

                continue;
            }

            grpList.add(grpKey);
        }

        return errIdxList.toArray(new Integer[errIdxList.size()]);
    }

    /**
     * <pre>
     * checkDupAttrbByGrp
     * </pre>
     * @param msgAry EZDMsgArray
     * @param effFromItemNm Effective From Item Name
     * @param effThruItemNm Effective Thru Item Name
     * @param grpItemNmList Message Item name for grouping
     * @return Duplicate Index
     */
    public static Integer[] checkDupTermByGrp(EZDMsgArray msgAry, String effFromItemNm, String effThruItemNm, String[] grpItemNmList) {

        if (msgAry.getValidCount() <= 1) {
            return new Integer[0];
        }

        if (!ZYPCommonFunc.hasValue(effFromItemNm)
                || !ZYPCommonFunc.hasValue(effThruItemNm)) {
            Integer[] errIdxList = new Integer[msgAry.getValidCount()];
            for (int i = 0; i < errIdxList.length; i++) {
                errIdxList[i] = i;
            }
            return errIdxList;
        }

        List<Integer> errIdxList = new ArrayList<Integer>(msgAry.getValidCount());
        List<String[]> msgList = new ArrayList<String[]>();

        for (int i = 0; i < msgAry.getValidCount(); i++) {
            EZDMsg msg = msgAry.get(i);
            String grpKey = makeCompVal(msg, grpItemNmList);
            String effFromDt = msg.getValueString(effFromItemNm, 0);
            String effThruDt = msg.getValueString(effThruItemNm, 0);
            if (!ZYPCommonFunc.hasValue(effThruDt)) {
                effThruDt = "99991231";
            }
            msgList.add(new String[] {grpKey, effFromDt, effThruDt});
        }

        for (int i = 0; i < msgAry.getValidCount() - 1; i++) {

            if (errIdxList.contains(i)) {
                continue;
            }

            String[] msg1 = msgList.get(i);
            String grpKey1 = msg1[0];
            String effFromDt1 = msg1[1];
            String effThruDt1 = msg1[2];

            boolean dupFlg = false;
            for (int j = i + 1; j < msgAry.getValidCount(); j++) {

                String[] msg2 = msgList.get(j);
                String grpKey2 = msg2[0];
                String effFromDt2 = msg2[1];
                String effThruDt2 = msg2[2];

                if (!grpKey1.equals(grpKey2)) {
                    continue;
                }

                if (effFromDt1.compareTo(effFromDt2) < 0) {

                    if (effThruDt1.compareTo(effFromDt2) >= 0) {
                        // error
                        errIdxList.add(j);
                        dupFlg = true;
                    }

                } else if (effFromDt1.compareTo(effFromDt2) > 0) {

                    if (effFromDt1.compareTo(effThruDt2) <= 0) {
                        // error
                        errIdxList.add(j);
                        dupFlg = true;
                    }

                } else {
                    // error
                    errIdxList.add(j);
                    dupFlg = true;
                }

            }

            if (dupFlg) {
                errIdxList.add(0, i);
            }
        }

        return errIdxList.toArray(new Integer[errIdxList.size()]);
    }

    /**
     * <pre>
     * makeCompVal
     * </pre>
     * @param msg EZDMsg
     * @param itemNmList Message Item Name
     * @return
     */
    private static String makeCompVal(EZDMsg msg, String[] itemNmList) {

        if (itemNmList == null) {
            return "";
        }

        StringBuilder compVal = new StringBuilder();

        for (String chkAttrbNm : itemNmList) {
            EZDItemAttribute itemAttrb = msg.getAttr(chkAttrbNm);

            String attrbVal = "";
            switch (itemAttrb.getType()) {
                case EZDItemAttrDefines.TYPE_SEISU_SYOSU:
                    attrbVal = msg.getValueBigDecimal(chkAttrbNm, 0).toString();
                    break;
                default:
                    attrbVal = msg.getValueString(chkAttrbNm, 0);
            }
            compVal.append(attrbVal);
            compVal.append(",");
        }

        return compVal.toString();
    }

    // 2019/03/13 QC#30715 add start
    /**
     * formatDateStr
     * @param baseStr String of format -M/-D/YYYY
     * @return String of formated MM/DD/YYYY
     */
    public static String formatDateStr(String baseStr) {
        String[] devStr = baseStr.split("/");
        StringBuilder sb = new StringBuilder();

        // Illegal case
        if (devStr.length != 3 || devStr[0] == null || devStr[1] == null || devStr[2] == null || devStr[0].length() < 1 || devStr[1].length() < 1 || devStr[2].length() != 4) {
            return baseStr;
        } else {
            if (devStr[0].length() == 1) {
                sb.append(DATE_FORMAT_PADDING_ZERO);
            }
            sb.append(devStr[0]);
            sb.append("/");
            if (devStr[1].length() == 1) {
                sb.append(DATE_FORMAT_PADDING_ZERO);
            }
            sb.append(devStr[1]);
            sb.append("/");
            sb.append(devStr[2]);
            return sb.toString();
        }
    }
    // 2019/03/13 QC#30715 add end
}
