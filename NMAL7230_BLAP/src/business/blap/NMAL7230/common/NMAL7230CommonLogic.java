/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL7230.common;

import static business.blap.NMAL7230.constant.NMAL7230Constant.BIZ_ID;
import static business.blap.NMAL7230.constant.NMAL7230Constant.COUNTRY;
import static business.blap.NMAL7230.constant.NMAL7230Constant.COUNTRY_STATE;
import static business.blap.NMAL7230.constant.NMAL7230Constant.CSV_EXTENSION;
import static business.blap.NMAL7230.constant.NMAL7230Constant.CSV_FILE_NAME;
import static business.blap.NMAL7230.constant.NMAL7230Constant.CSV_HDR;
import static business.blap.NMAL7230.constant.NMAL7230Constant.CUSTOMER;
import static business.blap.NMAL7230.constant.NMAL7230Constant.CUSTOMER_GROUP;
import static business.blap.NMAL7230.constant.NMAL7230Constant.DELETE_SEARCH;
import static business.blap.NMAL7230.constant.NMAL7230Constant.FIVE_POSTALCODE_PATTERN;
import static business.blap.NMAL7230.constant.NMAL7230Constant.FREIGHT_ZONE;
import static business.blap.NMAL7230.constant.NMAL7230Constant.LINE_OF_BUSINESS;
import static business.blap.NMAL7230.constant.NMAL7230Constant.NMAM0072E;
import static business.blap.NMAL7230.constant.NMAL7230Constant.NMAM0163E;
import static business.blap.NMAL7230.constant.NMAL7230Constant.NMAM8075E;
import static business.blap.NMAL7230.constant.NMAL7230Constant.NMAM8178E;
import static business.blap.NMAL7230.constant.NMAL7230Constant.NMAM8187E;
import static business.blap.NMAL7230.constant.NMAL7230Constant.NMAM8191E;
import static business.blap.NMAL7230.constant.NMAL7230Constant.NMAM8409E;
import static business.blap.NMAL7230.constant.NMAL7230Constant.NMAM8410E;
import static business.blap.NMAL7230.constant.NMAL7230Constant.PORTALCODE_ZERO_PACK;
import static business.blap.NMAL7230.constant.NMAL7230Constant.POSTALCODE;
import static business.blap.NMAL7230.constant.NMAL7230Constant.POSTALCODE_PATTERN;
import static business.blap.NMAL7230.constant.NMAL7230Constant.POSTAL_FROM;
import static business.blap.NMAL7230.constant.NMAL7230Constant.POSTAL_TO;
import static business.blap.NMAL7230.constant.NMAL7230Constant.EFFECTIVE_DATE_FROM;
import static business.blap.NMAL7230.constant.NMAL7230Constant.SAVE_SEARCH;
import static business.blap.NMAL7230.constant.NMAL7230Constant.STATE;
import static business.blap.NMAL7230.constant.NMAL7230Constant.TEN_POSTALCODE_PATTERN;
import static business.blap.NMAL7230.constant.NMAL7230Constant.THIS_FREIGHT_ZONE;
import static business.blap.NMAL7230.constant.NMAL7230Constant.UPLOAD_FORMAT_ERROR;
import static business.blap.NMAL7230.constant.NMAL7230Constant.UPLOAD_READ_ERROR;
import static business.blap.NMAL7230.constant.NMAL7230Constant.YYYYMMDD_LENGTH;
import static business.blap.NMAL7230.constant.NMAL7230Constant.YYYYMMDD_PATTERN;
import static business.blap.NMAL7230.constant.NMAL7230Constant.ZONE;
import static business.blap.NMAL7230.constant.NMAL7230Constant.ZZM9000E;
import static business.blap.NMAL7230.constant.NMAL7230Constant.ZZZM9003I;
import static business.blap.NMAL7230.constant.NMAL7230Constant.DATE_FORMAT_PADDING_ZERO;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parts.common.EZDCSVInFile;
import parts.common.EZDCommonFunc;
import parts.common.EZDMsg;
import parts.common.EZDSItem;
import parts.common.EZDSchemaInfo;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NMAL7230.NMAL7230CMsg;
import business.blap.NMAL7230.NMAL7230Query;
import business.blap.NMAL7230.NMAL7230SMsg;
import business.blap.NMAL7230.NMAL7230_ACMsg;
import business.blap.NMAL7230.NMAL7230_ASMsg;
import business.blap.NMAL7230.NMAL7230_ASMsgArray;
import business.db.CTRYTMsg;
import business.db.LINE_BIZ_TPTMsg;
import business.db.LINE_BIZ_TPTMsgArray;
import business.db.PRC_GRPTMsg;
import business.db.PRC_GRPTMsgArray;
import business.db.SELL_TO_CUSTTMsg;
import business.db.SELL_TO_CUSTTMsgArray;
import business.db.STTMsg;
import business.file.NMAL7230F00FMsg;
import business.parts.NSZC033001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC033001.NSZC033001;
import com.canon.cusa.s21.api.NSZ.NSZC033001.constant.NSZC033001Constant;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CTRY;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPRecHistUtil;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;

/**
 *<pre>
 * NMAL7230CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/17   Fujitsu         W.Honda         Create          N/A
 * 2016/09/15   SRAA            Y.Chen          Update          QC#9816
 * 2016/11/02   Fujitsu         T.Murai         Update          QC#9810
 * 2018/03/13   Fujitsu         K.Kato          Update          QC#30715
 *</pre>
 */
public class NMAL7230CommonLogic {

    /**
     * callNszc0330forSearchOption
     * @param bizMsg NMAL3000CMsg
     * @param glblMsg NMAL3000SMsg
     * @param usrId String
     * @param glblCmpyCd String
     */
    public static void callNszc0330forSearchOption(NMAL7230CMsg bizMsg, NMAL7230SMsg glblMsg, String usrId, String glblCmpyCd) {

        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        pMsg.clear();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_SEARCH);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptUsrId, usrId);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, bizMsg.srchOptPk_S);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, BIZ_ID);

        if (!callNszc0330(bizMsg, pMsg)) {
            return;
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.lineBizTpCd, pMsg.srchOptTxt_01);

        if (EZDCommonFunc.isNumberCheck(pMsg.srchOptTxt_02.getValue())) {
            ZYPEZDItemValueSetter.setValue(bizMsg.frtZoneNum, new BigDecimal(pMsg.srchOptTxt_02.getValue()));
        } else {
            bizMsg.frtZoneNum.clear();
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.xxDsMultMsgDplyTxt_HS, pMsg.srchOptTxt_03);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxDsMultMsgDplyTxt_HC, pMsg.srchOptTxt_04);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxDsMultMsgDplyTxt_HA, pMsg.srchOptTxt_05);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxDsMultMsgDplyTxt_HG, pMsg.srchOptTxt_06);
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToFromPostCd, pMsg.srchOptTxt_07);
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToThruPostCd, pMsg.srchOptTxt_08);

        if (ZYPDateUtil.isValidDate(pMsg.srchOptTxt_09.getValue(), YYYYMMDD_PATTERN)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.effFromDt, pMsg.srchOptTxt_09.getValue());
        } else {
            bizMsg.effFromDt.clear();
        }
        if (ZYPDateUtil.isValidDate(pMsg.srchOptTxt_10.getValue(), YYYYMMDD_PATTERN)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.effThruDt, pMsg.srchOptTxt_10.getValue());
        } else {
            bizMsg.effThruDt.clear();
        }
        ZYPEZDItemValueSetter.setValue(bizMsg.actvFlg, pMsg.srchOptTxt_11);
    }

    /**
     * callNszc0330forDeleteSearch
     * @param bizMsg NLBL3080CMsg
     * @param glblMsg NLBL3080SMsg
     * @param userId String
     * @param glblCmpyCd String
     */
    public static void callNszc0330forDeleteSearch(NMAL7230CMsg bizMsg, NMAL7230SMsg glblMsg, String userId, String glblCmpyCd) {
        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        pMsg.clear();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_DELETE);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, bizMsg.srchOptPk_S);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, BIZ_ID);

        if (callNszc0330(bizMsg, pMsg)) {
            createSavedSearchOptionsPullDown(bizMsg, userId);
            bizMsg.srchOptNm_S.clear();
            bizMsg.setMessageInfo("ZZZM9003I", new String[] {DELETE_SEARCH});
        }
    }

    /**
     * callNszc0330forSaveSearch
     * @param bizMsg NMAL7230CMsg
     * @param glblMsg NMAL7230SMsg
     * @param usrId NMAL7230SMsg
     * @param glblCmpyCd NMAL7230SMsg
     */
    public static void callNszc0330forSaveSearch(NMAL7230CMsg bizMsg, NMAL7230SMsg glblMsg, String usrId, String glblCmpyCd) {
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
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptUsrId, usrId);

        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_01, bizMsg.lineBizTpCd);

        if (ZYPCommonFunc.hasValue(bizMsg.frtZoneNum)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_02, bizMsg.frtZoneNum.getValue().toString());
        }

        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_03, bizMsg.xxDsMultMsgDplyTxt_HS);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_04, bizMsg.xxDsMultMsgDplyTxt_HC);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_05, bizMsg.xxDsMultMsgDplyTxt_HA);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_06, bizMsg.xxDsMultMsgDplyTxt_HG);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_07, bizMsg.shipToFromPostCd);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_08, bizMsg.shipToThruPostCd);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_09, bizMsg.effFromDt.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_10, bizMsg.effThruDt.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_11, bizMsg.actvFlg);

        if (callNszc0330(bizMsg, pMsg)) {
            createSavedSearchOptionsPullDown(bizMsg, usrId);
            ZYPEZDItemValueSetter.setValue(bizMsg.srchOptPk_S, pMsg.srchOptPk);
            bizMsg.srchOptNm_S.clear();
            bizMsg.setMessageInfo(ZZZM9003I, new String[] {SAVE_SEARCH});
        }
    }

    /**
     * callNszc0330
     * @param bizMsg NMAL7230CMsg
     * @param pMsg NSZC033001PMsg
     * @return boolean
     */
    private static boolean callNszc0330(NMAL7230CMsg bizMsg, NSZC033001PMsg pMsg) {

        // Search Option API(NSZC0330) is executed
        NSZC033001 api = new NSZC033001();
        api.execute(pMsg, ONBATCH_TYPE.ONLINE);

        String msgId;
        if (!S21ApiUtil.getXxMsgIdList(pMsg).isEmpty()) {
            for (int j = 0; j < pMsg.xxMsgIdList.length(); j++) {
                if (ZYPCommonFunc.hasValue(pMsg.xxMsgIdList.no(j).xxMsgId)) {
                    msgId = pMsg.xxMsgIdList.no(j).xxMsgId.getValue();
                    bizMsg.setMessageInfo(msgId);
                    if (msgId.endsWith("E")) {
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
     * setSelectSaveSearchName
     * @param pMsg NSZC033001PMsg
     * @param bizMsg NMAL7230CMsg
     */
    public static void setSelectSaveSearchName(NSZC033001PMsg pMsg, NMAL7230CMsg bizMsg) {

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

    private static boolean isSameSaveSearchName(NMAL7230CMsg bizMsg) {
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
     * @param bizMsg NMAL7230CMsg
     * @return boolean
     */
    public static boolean isExistSaveSearchName(NMAL7230CMsg bizMsg) {
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
     * createSavedSearchOptionsPullDown
     * @param bizMsg NMAL7230CMsg
     * @param srchOptUsrId String
     */
    public static void createSavedSearchOptionsPullDown(NMAL7230CMsg bizMsg, String srchOptUsrId) {
        S21SsmEZDResult ssmResult = NMAL7230Query.getInstance().getSavedSearchOptionList(srchOptUsrId);
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
     * write CsvFile
     * @param rs S21SsmEZDResult
     * @param bizMsg NMAL7230CMsg
     * @param glblCmpyCd GlobalCompanyCode
     * @throws SQLException SQLException
     */
    public static void writeCsvFileContInfo(ResultSet rs, NMAL7230CMsg bizMsg, String glblCmpyCd) throws SQLException {

        bizMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(CSV_FILE_NAME), CSV_EXTENSION);
        NMAL7230F00FMsg fMsg = new NMAL7230F00FMsg();
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
            ZYPEZDItemValueSetter.setValue(fMsg.dsAcctNm_A1, rs.getString("DS_ACCT_NM"));
            ZYPEZDItemValueSetter.setValue(fMsg.prcGrpNm_A1, rs.getString("PRC_GRP_NM"));
            ZYPEZDItemValueSetter.setValue(fMsg.frtZoneNum_A1, rs.getBigDecimal("FRT_ZONE_NUM"));
            ZYPEZDItemValueSetter.setValue(fMsg.shipToStCd_A1, rs.getString("SHIP_TO_ST_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.shipToCtryCd_A1, rs.getString("SHIP_TO_CTRY_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.shipToFromPostCd_A1, rs.getString("SHIP_TO_FROM_POST_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.shipToThruPostCd_A1, rs.getString("SHIP_TO_THRU_POST_CD"));
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
     * @param bizMsg NMAL7230CMsg
     * @return boolean
     */
    public static boolean readHeaderCsvFile(EZDCSVInFile mappedFile, NMAL7230CMsg bizMsg) {
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
     * @param glblLineMsg NMAL7230_ASMsgArray
     * @param fMsg fMsgNMAL7230F01FMsg
     * @param bizMsg NMAL7230CMsg
     * @return boolean if error then true else false
     */
    public static boolean checkFormatAndCopyToGlblMsg_UPLOAD(
        int status
        , int count
        , NMAL7230_ASMsgArray glblLineMsg
        , NMAL7230F00FMsg fMsg
        , NMAL7230CMsg bizMsg) {

        /****************************************************************
         * Record count check
         ****************************************************************/
        if (count > glblLineMsg.length()) {
            bizMsg.setMessageInfo(NMAM8187E, new String[] {FREIGHT_ZONE, String.valueOf(glblLineMsg.length())});
            return true;
        }

        /****************************************************************
         * CSV format check
         ****************************************************************/
        if (checkFormatCSV(status, count, fMsg, bizMsg, glblLineMsg, CSV_HDR)) {
            return true;
        }

        /****************************************************************
         * Copy FMsg to SMsg.
         ****************************************************************/
        // QC#9816
        // EZDMsg.copy(fMsg, null, glblLineMsg.no(count), null);
        // ZYPEZDItemValueSetter.setValue(glblLineMsg.no(count).effFromDt_A1, fMsg.effFromDtTxt_A1.getValue());
        // ZYPEZDItemValueSetter.setValue(glblLineMsg.no(count).effThruDt_A1, fMsg.effThruDtTxt_A1.getValue());

        return false;
    }

    /**
     * validateAndCopyToGlblMsg_UPLOAD
     * @param glblAMsg NMAL7230_ASMsgArray
     * @param bizMsg NMAL7230CMsg
     * @param glblCmpyCd GlobalCompanyCode
     * @return boolean if error then true else false
     */
    public static boolean validateGlblMsg_UPLOAD(
        NMAL7230_ASMsgArray glblAMsg
        , NMAL7230CMsg bizMsg
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

            // 2019/03/13 QC#30715 add start
            if (!ZYPCommonFunc.hasValue(glblAMsg.no(i).effFromDt_A1)) {
                glblAMsg.no(i).effFromDt_A1.setErrorInfo(1, ZZM9000E, new String[] {EFFECTIVE_DATE_FROM});
                isErr = true;
            }

            if (glblAMsg.no(i).effFromDt_A1.isError()) {
                isErr = true;
            }

            if (glblAMsg.no(i).effThruDt_A1.isError()) {
                isErr = true;
            }
            // 2019/03/13 QC#30715 add End

            // detail combination check
            if (ZYPCommonFunc.hasValue(glblAMsg.no(i).dsAcctNm_A1)) {
                // Customer
                if (ZYPCommonFunc.hasValue(glblAMsg.no(i).prcGrpNm_A1)) {
                    glblAMsg.no(i).prcGrpNm_A1.setErrorInfo(1, NMAM8409E, new String[]{CUSTOMER, CUSTOMER_GROUP});
                    isErr = true;
                }
            }

            if (ZYPCommonFunc.hasValue(glblAMsg.no(i).shipToCtryCd_A1)
                    || ZYPCommonFunc.hasValue(glblAMsg.no(i).shipToStCd_A1)) {
                // Country & State
                if (NMAL7230CommonLogic.checkCombinationMandatoryForShipto(glblAMsg.no(i), bizMsg)) {
                    isErr = true;
                }

            } else if (ZYPCommonFunc.hasValue(glblAMsg.no(i).shipToFromPostCd_A1)
                    || ZYPCommonFunc.hasValue(glblAMsg.no(i).shipToThruPostCd_A1)) {
                // Portal Code From/To
                if (NMAL7230CommonLogic.checkCombinationMandatoryForPostalCode(glblAMsg.no(i), bizMsg)) {
                    isErr = true;
                } else {
                    if (!NMAL7230CommonLogic.checkPortalCodeFormat(glblAMsg.no(i).shipToFromPostCd_A1.getValue())) {
                        glblAMsg.no(i).shipToFromPostCd_A1.setErrorInfo(1, NMAM8075E, new String[] {POSTALCODE_PATTERN});
                        isErr = true;
                    }

                    if (!NMAL7230CommonLogic.checkPortalCodeFormat(glblAMsg.no(i).shipToThruPostCd_A1.getValue())) {
                        glblAMsg.no(i).shipToThruPostCd_A1.setErrorInfo(1, NMAM8075E, new String[] {POSTALCODE_PATTERN});
                        isErr = true;
                    }
                }
            } else {
                glblAMsg.no(i).shipToCtryCd_A1.setErrorInfo(1, NMAM8410E, new String[] {COUNTRY + "/" + STATE + " or " + POSTAL_FROM + "/" + POSTAL_TO});
                glblAMsg.no(i).shipToStCd_A1.setErrorInfo(1, NMAM8410E, new String[] {COUNTRY + "/" + STATE + " or " + POSTAL_FROM + "/" + POSTAL_TO});
                glblAMsg.no(i).shipToFromPostCd_A1.setErrorInfo(1, NMAM8410E, new String[] {COUNTRY + "/" + STATE + " or " + POSTAL_FROM + "/" + POSTAL_TO});
                glblAMsg.no(i).shipToThruPostCd_A1.setErrorInfo(1, NMAM8410E, new String[] {COUNTRY + "/" + STATE + " or " + POSTAL_FROM + "/" + POSTAL_TO});
                isErr = true;
            }

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
                } else {
                    ZYPEZDItemValueSetter.setValue(glblAMsg.no(i).lineBizTpCd_A1, outTMsg.no(0).lineBizTpCd);
                }
            }

            if (ZYPCommonFunc.hasValue(glblAMsg.no(i).dsAcctNm_A1)) {
                SELL_TO_CUSTTMsg inTMsg = new SELL_TO_CUSTTMsg();
                SELL_TO_CUSTTMsgArray outTMsg = null;
                inTMsg.setSQLID("505");
                inTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
                inTMsg.setConditionValue("dsAcctNm01", glblAMsg.no(i).dsAcctNm_A1.getValue());
                outTMsg = (SELL_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(inTMsg);
                if (outTMsg.length() < 1) {
                    bizMsg.A.no(i).dsAcctNm_A1.setErrorInfo(1, NMAM0163E, new String[] {glblAMsg.no(i).dsAcctNm_A1.getValue(), inTMsg.getTableName()});
                    isErr = true;
                } else {
                    ZYPEZDItemValueSetter.setValue(glblAMsg.no(i).dsAcctNum_A1, outTMsg.no(0).sellToCustCd);
                }
            }

            if (ZYPCommonFunc.hasValue(glblAMsg.no(i).prcGrpNm_A1)) {
                PRC_GRPTMsg inTMsg = new PRC_GRPTMsg();
                PRC_GRPTMsgArray outTMsg = null;
                inTMsg.setSQLID("001");
                inTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
                inTMsg.setConditionValue("prcGrpNm01", glblAMsg.no(i).prcGrpNm_A1.getValue());
                outTMsg = (PRC_GRPTMsgArray) EZDTBLAccessor.findByCondition(inTMsg);
                if (outTMsg.length() < 1) {
                    bizMsg.A.no(i).prcGrpNm_A1.setErrorInfo(1, NMAM0163E, new String[] {glblAMsg.no(i).prcGrpNm_A1.getValue(), inTMsg.getTableName()});
                    isErr = true;
                } else {
                    ZYPEZDItemValueSetter.setValue(glblAMsg.no(i).prcGrpPk_A1, outTMsg.no(0).prcGrpPk);
                }
            }

            if (ZYPCommonFunc.hasValue(glblAMsg.no(i).shipToCtryCd_A1)) {
                CTRYTMsg inCtryMsg = new CTRYTMsg();
                CTRYTMsg outCtryMsg = null;
                ZYPEZDItemValueSetter.setValue(inCtryMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(inCtryMsg.ctryCd, glblAMsg.no(i).shipToCtryCd_A1);
                outCtryMsg = (CTRYTMsg) S21CodeTableAccessor.findByKey(inCtryMsg);
                if (outCtryMsg == null) {
                    bizMsg.A.no(i).shipToCtryCd_A1.setErrorInfo(1, NMAM0163E, new String[] {glblAMsg.no(i).shipToCtryCd_A1.getValue(), inCtryMsg.getTableName()});
                    isErr = true;
                }

                if (ZYPCommonFunc.hasValue(glblAMsg.no(i).shipToStCd_A1)
                        && CTRY.UNITED_STATES_OF_AMERICA.equals(glblAMsg.no(i).shipToCtryCd_A1.getValue())) {
                    STTMsg inStMsg = new STTMsg();
                    STTMsg outStMsg = null;
                    ZYPEZDItemValueSetter.setValue(inStMsg.glblCmpyCd, glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(inStMsg.stCd, glblAMsg.no(i).shipToStCd_A1.getValue());
                    outStMsg = (STTMsg) S21CodeTableAccessor.findByKey(inStMsg);
                    if (outStMsg == null) {
                        bizMsg.A.no(i).shipToStCd_A1.setErrorInfo(1, NMAM0163E, new String[] {glblAMsg.no(i).shipToStCd_A1.getValue(), inStMsg.getTableName()});
                        isErr = true;
                    }
                }
            }
        }

        return isErr;
    }

    /**
     * checkFormatCSV
     * @param status int
     * @param uploadCount int
     * @param fMsg fMsgNMAL7230F01FMsg
     * @param bizMsg NMAL7230CMsg
     * @param glblLineMsg NMAL7230_ASMsgArray
     * @param colStrList String[]
     * @return boolean if error then true else false
     */
    public static boolean checkFormatCSV(int status, int uploadCount, NMAL7230F00FMsg fMsg, NMAL7230CMsg bizMsg, NMAL7230_ASMsgArray glblLineMsg, String[] colStrList) {
        // QC#9816
        NMAL7230_ASMsg asMsg = glblLineMsg.no(uploadCount);
        EZDMsg.copy(fMsg, null, asMsg, null);

        // 2019/03/13 QC#30715 add start
        EZDSchemaInfo ezdSchemaInfo = new NMAL7230_ASMsg().getSchema();
        // 2019/03/13 QC#30715 add End

        List<EZDSItem> itemList = new ArrayList<EZDSItem>();
        itemList.add(asMsg.lineBizTpDescTxt_A1);
        itemList.add(asMsg.dsAcctNm_A1);
        itemList.add(asMsg.prcGrpNm_A1);
        itemList.add(asMsg.frtZoneNum_A1);
        itemList.add(asMsg.shipToStCd_A1);
        itemList.add(asMsg.shipToCtryCd_A1);
        itemList.add(asMsg.shipToFromPostCd_A1);
        itemList.add(asMsg.shipToThruPostCd_A1);
        itemList.add(asMsg.effFromDt_A1);
        itemList.add(asMsg.effThruDt_A1);

        // QC#9816
        // int displayRowNum = uploadCount + 1;
        int displayRowNum = uploadCount + 2;
        if (status == UPLOAD_READ_ERROR) {
             bizMsg.setMessageInfo(NMAM8191E, new String[]{"Row #" + String.valueOf(displayRowNum)});
             return true;
        }

        if (UPLOAD_READ_ERROR < status && status <= UPLOAD_READ_ERROR + colStrList.length) {
            int colIdx = status - UPLOAD_READ_ERROR;
            // QC#9816
            // bizMsg.setMessageInfo(NMAM8191E, new String[]{String.valueOf(displayRowNum) + "row, " + colStrList[colIdx - 1]});
            // return true;
            itemList.get(colIdx - 1).setErrorInfo(1, NMAM8191E, new String[]{"row #" + String.valueOf(displayRowNum)});
        }
        if (UPLOAD_FORMAT_ERROR < status && status <= UPLOAD_FORMAT_ERROR + colStrList.length) {
            int colIdx = status - UPLOAD_FORMAT_ERROR;
            // QC#9816
            // bizMsg.setMessageInfo(NMAM8191E, new String[]{String.valueOf(displayRowNum) + "row, " + colStrList[colIdx - 1]});
            // return true;
            itemList.get(colIdx - 1).setErrorInfo(1, NMAM8191E, new String[]{"row #" + String.valueOf(displayRowNum)});
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
            // 2019/03/13 QC#30715 Mod End
                // QC#9816
                // bizMsg.setMessageInfo(NMAM8191E, new String[]{String.valueOf(displayRowNum) + "row, Date From"});
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
            // 2019/03/13 QC#30715 Mod End
                // QC#9816
                // bizMsg.setMessageInfo(NMAM8191E, new String[]{String.valueOf(displayRowNum) + "row, Date To"});
                // return true;
                asMsg.effThruDt_A1.setErrorInfo(1, NMAM8191E, new String[]{"row #" + String.valueOf(displayRowNum)});
            } else {
                ZYPEZDItemValueSetter.setValue(asMsg.effThruDt_A1, fMsg.moveEffThruDtTxt_A1.getValue());
            }
        }
        return false;
    }

    /**
     * Set to the global Message and Copy to the bBusiness Message.
     * @param ssmResult S21SsmEZDResult
     * @param bizMsg NMAL7230CMsg
     * @param glblMsg NMAL7230SMsg
     */
    public static void setGlblMsgAndCopyBizMsg(S21SsmEZDResult ssmResult, NMAL7230CMsg bizMsg, NMAL7230SMsg glblMsg) {

        // SSM Result
        List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
        int i = 0;

        for (; i < glblMsg.A.getValidCount(); i++) {
            Map<String, Object> resultMap = (Map<String, Object>) resultList.get(i);

            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).frtZonePk_A1, (BigDecimal) resultMap.get("FRT_ZONE_PK"));
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).lineBizTpCd_A1, (String) resultMap.get("LINE_BIZ_TP_CD"));
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).lineBizTpDescTxt_A1, (String) resultMap.get("LINE_BIZ_TP_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).dsAcctNm_A1, (String) resultMap.get("DS_ACCT_NM"));
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).prcGrpNm_A1, (String) resultMap.get("PRC_GRP_NM"));
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).frtZoneNum_A1, (BigDecimal) resultMap.get("FRT_ZONE_NUM"));
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).shipToStCd_A1, (String) resultMap.get("SHIP_TO_ST_CD"));
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).shipToCtryCd_A1, (String) resultMap.get("SHIP_TO_CTRY_CD"));
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).shipToFromPostCd_A1, (String) resultMap.get("SHIP_TO_FROM_POST_CD"));
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).shipToThruPostCd_A1, (String) resultMap.get("SHIP_TO_THRU_POST_CD"));
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
     * mandatory combination check for ship to
     * @param glblLineMsg NMAL7230_ASMsg
     * @param bizMsg NMAL7230CMsg
     * @return check result
     */
    public static boolean checkCombinationMandatoryForShipto(NMAL7230_ASMsg glblLineMsg, NMAL7230CMsg bizMsg) {

        boolean isErr = false;

        if (!ZYPCommonFunc.hasValue(glblLineMsg.shipToCtryCd_A1)) {
            glblLineMsg.shipToCtryCd_A1.setErrorInfo(1, NMAM8178E, new String[] {STATE, COUNTRY});
            isErr = true;
        // QC#9809 2016/06/13 Mod start
//        }
//
//        if (!ZYPCommonFunc.hasValue(glblLineMsg.shipToStCd_A1)) {
        } else if (ZYPCommonFunc.hasValue(bizMsg.ctryCd)
                && bizMsg.ctryCd.getValue().equals(glblLineMsg.shipToCtryCd_A1.getValue())
                && !ZYPCommonFunc.hasValue(glblLineMsg.shipToStCd_A1)) {
                glblLineMsg.shipToStCd_A1.setErrorInfo(1, NMAM8178E, new String[] {COUNTRY, STATE});
                isErr = true;
            // QC#9809 2016/06/13 Mod end
        }

        if (ZYPCommonFunc.hasValue(glblLineMsg.shipToFromPostCd_A1)) {
            glblLineMsg.shipToFromPostCd_A1.setErrorInfo(1, NMAM8409E, new String[] {COUNTRY + "/" + STATE, POSTAL_FROM});
            isErr = true;
        }

        if (ZYPCommonFunc.hasValue(glblLineMsg.shipToThruPostCd_A1)) {
            glblLineMsg.shipToThruPostCd_A1.setErrorInfo(1, NMAM8409E, new String[] {COUNTRY + "/" + STATE, POSTAL_TO});
            isErr = true;
        }

        return isErr;
    }

    /**
     * mandatory combination check for postal code
     * @param glblLineMsg NMAL7230_ASMsg
     * @param bizMsg NMAL7230CMsg
     * @return check result
     */
    public static boolean checkCombinationMandatoryForPostalCode(NMAL7230_ASMsg glblLineMsg, NMAL7230CMsg bizMsg) {

        boolean isErr = false;

        if (!ZYPCommonFunc.hasValue(glblLineMsg.shipToFromPostCd_A1)) {
            glblLineMsg.shipToFromPostCd_A1.setErrorInfo(1, NMAM8178E, new String[] {POSTAL_FROM, POSTAL_TO});
            isErr = true;
        }

        if (!ZYPCommonFunc.hasValue(glblLineMsg.shipToThruPostCd_A1)) {
            glblLineMsg.shipToThruPostCd_A1.setErrorInfo(1, NMAM8178E, new String[] {POSTAL_TO, POSTAL_FROM});
            return true;
        }

        return isErr;
    }

    /**
     * Check Overlap Postal Code.
     * @param bizLineMsg NMAL7230_ACMsg
     * @param deleteList List<BigDecimal>
     * @param updateList List<BigDecimal>
     * @return check result
     */
    public static boolean checkOverlapPostalCode(NMAL7230_ACMsg bizLineMsg, List<BigDecimal> deleteList, List<BigDecimal> updateList) {

        // because not Postal Code Record.
        if (ZYPCommonFunc.hasValue(bizLineMsg.shipToCtryCd_A1)
                || ZYPCommonFunc.hasValue(bizLineMsg.shipToStCd_A1)) {
            return false;
        }

        S21SsmEZDResult ssmResult = NMAL7230Query.getInstance().checkActiveDuplicationZone(bizLineMsg);

        if (!ssmResult.isCodeNotFound()) {
            List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();

            for (int j = 0; j < resultList.size(); j++) {
                Map<String, Object> resultMap = (Map<String, Object>) resultList.get(j);

                if (!updateList.contains((BigDecimal) resultMap.get("FRT_ZONE_PK"))
                        && !deleteList.contains((BigDecimal) resultMap.get("FRT_ZONE_PK"))) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Check Active Duplication Zone.
     * @param bizLineMsg NMAL7230_ACMsg
     * @param deleteList List<BigDecimal>
     * @param updateList List<BigDecimal>
     * @return check result
     */
    public static boolean checkActiveDuplicateZone(NMAL7230_ACMsg bizLineMsg, List<BigDecimal> deleteList, List<BigDecimal> updateList) {

        S21SsmEZDResult ssmResult = NMAL7230Query.getInstance().checkActiveDuplicationZone(bizLineMsg);

        if (!ssmResult.isCodeNotFound()) {
            List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();

            for (int j = 0; j < resultList.size(); j++) {
                Map<String, Object> resultMap = (Map<String, Object>) resultList.get(j);

                if (!updateList.contains((BigDecimal) resultMap.get("FRT_ZONE_PK"))
                        && !deleteList.contains((BigDecimal) resultMap.get("FRT_ZONE_PK"))) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Check Active Duplication Zone.
     * @param bizMsg NMAL7230CMsg
     * @return check result
     */
    public static boolean checkActiveZoneForScreen(NMAL7230CMsg bizMsg) {

        if (bizMsg.A.getValidCount() <= 1) {
            return false;
        }

        List<Integer> errIdxList = new ArrayList<Integer>(bizMsg.A.getValidCount());
        List<String[]> msgList = makeCompVal(bizMsg);

        // Customer,Customer Group, Country/State
        boolean isErr = false;
        for (int i = 0; i < msgList.size() - 1; i++) {

            if (errIdxList.contains(i)) {
                continue;
            }

            String[] msg1 = msgList.get(i);
            String grpKey1 = msg1[0];
            if (POSTALCODE.equals(grpKey1)) {
                continue;
            }
            String effFromDt1 = msg1[1];
            String effThruDt1 = msg1[2];

            boolean dupFlg = false;
            for (int j = i + 1; j < msgList.size(); j++) {

                String[] msg2 = msgList.get(j);
                String grpKey2 = msg2[0];
                if (POSTALCODE.equals(grpKey2)) {
                    continue;
                }
                String effFromDt2 = msg2[1];
                String effThruDt2 = msg2[2];

                if (!grpKey1.equals(grpKey2)) {
                    continue;
                }

                if (effFromDt1.compareTo(effFromDt2) < 0) {

                    if (effThruDt1.compareTo(effFromDt2) >= 0) {
                        // error
                        errIdxList.add(j);
                        bizMsg.A.no(j).xxChkBox_A1.setErrorInfo(1, NMAM0072E, new String[] {THIS_FREIGHT_ZONE});
                        dupFlg = true;
                    }

                } else if (effFromDt1.compareTo(effFromDt2) > 0) {

                    if (effFromDt1.compareTo(effThruDt2) <= 0) {
                        // error
                        errIdxList.add(j);
                        bizMsg.A.no(j).xxChkBox_A1.setErrorInfo(1, NMAM0072E, new String[] {THIS_FREIGHT_ZONE});
                        dupFlg = true;
                    }

                } else {
                    // error
                    errIdxList.add(j);
                    bizMsg.A.no(j).xxChkBox_A1.setErrorInfo(1, NMAM0072E, new String[] {THIS_FREIGHT_ZONE});
                    dupFlg = true;
                }

            }

            if (dupFlg) {
                errIdxList.add(0, i);
                bizMsg.A.no(i).xxChkBox_A1.setErrorInfo(1, NMAM0072E, new String[] {THIS_FREIGHT_ZONE});
                isErr = true;
            }
        }

        // PortalCode
        List<Integer> postCdErrIdxList = new ArrayList<Integer>(bizMsg.A.getValidCount());
        List<String[]> postCdMsgList = makePostCdGrpKey(bizMsg);
        for (int i = 0; i < postCdMsgList.size() - 1; i++) {

            if (postCdErrIdxList.contains(i)) {
                continue;
            }

            String[] msg1 = postCdMsgList.get(i);
            String grpKey1 = msg1[0];
            String effFromDt1 = msg1[1];
            String effThruDt1 = msg1[2];
            if (!ZYPCommonFunc.hasValue(effThruDt1)) {
                effThruDt1 = "99991231";
            }

            boolean dupFlg = false;
            for (int j = i + 1; j < postCdMsgList.size(); j++) {

                String[] msg2 = postCdMsgList.get(j);
                String grpKey2 = msg2[0];
                String effFromDt2 = msg2[1];
                String effThruDt2 = msg2[2];
                if (!ZYPCommonFunc.hasValue(effThruDt2)) {
                    effThruDt2 = "99991231";
                }

                if (!grpKey1.equals(grpKey2)) {
                    continue;
                }

                if (effFromDt1.compareTo(effFromDt2) < 0) {

                    if (effThruDt1.compareTo(effFromDt2) >= 0) {
                        // error
                        postCdErrIdxList.add(Integer.valueOf(msg2[3]));
                        dupFlg = true;
                    }

                } else if (effFromDt1.compareTo(effFromDt2) > 0) {

                    if (effFromDt1.compareTo(effThruDt2) <= 0) {
                        // error
                        postCdErrIdxList.add(Integer.valueOf(msg2[3]));
                        dupFlg = true;
                    }

                } else {
                    // error
                    postCdErrIdxList.add(Integer.valueOf(msg2[3]));
                    dupFlg = true;
                }

            }

            if (dupFlg) {
                postCdErrIdxList.add(0, Integer.valueOf(msg1[3]));
            }
        }

        if (postCdErrIdxList.size() > 0) {
            for (int errIdx : postCdErrIdxList) {
                bizMsg.A.no(errIdx).xxChkBox_A1.setErrorInfo(1, NMAM0072E, new String[]{THIS_FREIGHT_ZONE});
            }
            isErr = true;
        }

        return isErr;
    }

    /**
     * Check Overlap Postal Code.
     * @param bizMsg NMAL7230CMsg
     * @return check result
     */
    public static boolean checkOverlapPostalCodeForUploadFile(NMAL7230CMsg bizMsg) {

        if (bizMsg.A.getValidCount() <= 1) {
            return false;
        }

        boolean isErr = false;

        // PortalCode
        List<Integer> postCdErrIdxList = new ArrayList<Integer>(bizMsg.A.getValidCount());
        List<String[]> postCdMsgList = makePostCdGrpKey(bizMsg);
        for (int i = 0; i < postCdMsgList.size() - 1; i++) {

            if (postCdErrIdxList.contains(i)) {
                continue;
            }

            String[] msg1 = postCdMsgList.get(i);
            String grpKey1 = msg1[0];
            String effFromDt1 = msg1[1];
            String effThruDt1 = msg1[2];
            if (!ZYPCommonFunc.hasValue(effThruDt1)) {
                effThruDt1 = "99991231";
            }

            boolean dupFlg = false;
            for (int j = i + 1; j < postCdMsgList.size(); j++) {

                String[] msg2 = postCdMsgList.get(j);
                String grpKey2 = msg2[0];
                String effFromDt2 = msg2[1];
                String effThruDt2 = msg2[2];
                if (!ZYPCommonFunc.hasValue(effThruDt2)) {
                    effThruDt2 = "99991231";
                }

                if (!grpKey1.equals(grpKey2)) {
                    continue;
                }

                if (effFromDt1.compareTo(effFromDt2) < 0) {

                    if (effThruDt1.compareTo(effFromDt2) >= 0) {
                        // error
                        postCdErrIdxList.add(Integer.valueOf(msg2[3]));
                        dupFlg = true;
                    }

                } else if (effFromDt1.compareTo(effFromDt2) > 0) {

                    if (effFromDt1.compareTo(effThruDt2) <= 0) {
                        // error
                        postCdErrIdxList.add(Integer.valueOf(msg2[3]));
                        dupFlg = true;
                    }

                } else {
                    // error
                    postCdErrIdxList.add(Integer.valueOf(msg2[3]));
                    dupFlg = true;
                }

            }

            if (dupFlg) {
                postCdErrIdxList.add(0, Integer.valueOf(msg1[3]));
            }
        }

        if (postCdErrIdxList.size() > 0) {
            for (int errIdx : postCdErrIdxList) {
                bizMsg.A.no(errIdx).shipToFromPostCd_A1.setErrorInfo(1, NMAM0072E, new String[]{THIS_FREIGHT_ZONE});
                bizMsg.A.no(errIdx).shipToThruPostCd_A1.setErrorInfo(1, NMAM0072E, new String[]{THIS_FREIGHT_ZONE});
            }
            isErr = true;
        }

        return isErr;
    }

    /**
     * <pre>
     * makeCompVal
     * </pre>
     * @param msg EZDMsg
     * @param itemNmList Message Item Name
     * @return
     */
    private static List<String[]> makeCompVal(NMAL7230CMsg bizMsg) {
        List<String[]> msgList = new ArrayList<String[]>();

        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {

            StringBuilder compVal = new StringBuilder();
            NMAL7230_ACMsg bizLineMsg = bizMsg.A.no(i);

            compVal.append(bizLineMsg.lineBizTpDescTxt_A1.getValue());
            compVal.append(",");

            if (ZYPCommonFunc.hasValue(bizLineMsg.shipToCtryCd_A1)
                    || ZYPCommonFunc.hasValue(bizLineMsg.shipToStCd_A1)) {

                compVal.append(COUNTRY_STATE);
                compVal.append(",");
                compVal.append(bizLineMsg.dsAcctNm_A1.getValue());
                compVal.append(",");
                compVal.append(bizLineMsg.prcGrpNm_A1.getValue());
                compVal.append(",");
                compVal.append(bizLineMsg.shipToCtryCd_A1.getValue());
                compVal.append(",");
                if (ZYPCommonFunc.hasValue(bizLineMsg.shipToCtryCd_A1)
                        && CTRY.UNITED_STATES_OF_AMERICA.equals(bizLineMsg.shipToCtryCd_A1.getValue())) {
                    compVal.append(bizLineMsg.shipToStCd_A1.getValue());
                }

            } else if (ZYPCommonFunc.hasValue(bizLineMsg.shipToFromPostCd_A1)) {

                compVal = new StringBuilder();
                compVal.append(POSTALCODE);
            }

            String grpKey = compVal.toString();
            String effFromDt = bizMsg.A.no(i).effFromDt_A1.getValue();
            String effThruDt = bizMsg.A.no(i).effThruDt_A1.getValue();
            if (!ZYPCommonFunc.hasValue(effThruDt)) {
                effThruDt = "99991231";
            }

            msgList.add(new String[] {grpKey, effFromDt, effThruDt});
        }

        return msgList;
    }

    /**
     * <pre>
     * makeCompVal
     * </pre>
     * @param bizMsg NMAL7230CMsg
     * @return
     */
    private static List<String[]> makePostCdGrpKey(NMAL7230CMsg bizMsg) {

        List<String[]> msgList = new ArrayList<String[]>();

        for (int i = 0; i < bizMsg.A.getValidCount() - 1; i++) {

            if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).shipToCtryCd_A1)
                    || ZYPCommonFunc.hasValue(bizMsg.A.no(i).shipToStCd_A1)) {
                continue;
            }

            String grpKey1 = makeInternalPostCdGrpKey(bizMsg.A.no(i));
            BigDecimal fromPostCode1 = new BigDecimal(convertdigit5to10(bizMsg.A.no(i).shipToFromPostCd_A1.getValue().replaceAll("-", "")));
            BigDecimal thruPostCode1 = new BigDecimal(convertdigit5to10(bizMsg.A.no(i).shipToThruPostCd_A1.getValue().replaceAll("-", "")));

            boolean dupFlg = false;
            for (int j = i + 1; j < bizMsg.A.getValidCount(); j++) {

                if (ZYPCommonFunc.hasValue(bizMsg.A.no(j).shipToCtryCd_A1)
                        || ZYPCommonFunc.hasValue(bizMsg.A.no(j).shipToStCd_A1)) {
                    continue;
                }

                String grpKey2 = makeInternalPostCdGrpKey(bizMsg.A.no(j));
                BigDecimal fromPostCode2 = new BigDecimal(convertdigit5to10(bizMsg.A.no(j).shipToFromPostCd_A1.getValue().replaceAll("-", "")));
                BigDecimal thruPostCode2 = new BigDecimal(convertdigit5to10(bizMsg.A.no(j).shipToThruPostCd_A1.getValue().replaceAll("-", "")));

                if (!grpKey1.equals(grpKey2)) {
                    continue;
                }

                if (fromPostCode1.compareTo(fromPostCode2) < 0) {

                    if (thruPostCode1.compareTo(fromPostCode2) >= 0) {
                        // error
                        dupFlg = true;
                        msgList.add(new String[] {grpKey1 + "," + POSTALCODE + i
                                                  , bizMsg.A.no(j).effFromDt_A1.getValue()
                                                  , bizMsg.A.no(j).effThruDt_A1.getValue()
                                                  , String.valueOf(j)});
                    }

                } else if (fromPostCode1.compareTo(fromPostCode2) > 0) {

                    if (fromPostCode1.compareTo(thruPostCode2) <= 0) {
                        // error
                        dupFlg = true;
                        msgList.add(new String[] {grpKey1 + "," + POSTALCODE + i
                                , bizMsg.A.no(j).effFromDt_A1.getValue()
                                , bizMsg.A.no(j).effThruDt_A1.getValue()
                                , String.valueOf(j)});
                    }

                } else {
                    // error
                    dupFlg = true;
                    msgList.add(new String[] {grpKey1 + "," + POSTALCODE + i
                            , bizMsg.A.no(j).effFromDt_A1.getValue()
                            , bizMsg.A.no(j).effThruDt_A1.getValue()
                            , String.valueOf(j)});
                }

            }

            if (dupFlg) {
                msgList.add(0
                        , new String[] {grpKey1 + "," + POSTALCODE + i
                                    , bizMsg.A.no(i).effFromDt_A1.getValue()
                                    , bizMsg.A.no(i).effThruDt_A1.getValue()
                                    , String.valueOf(i)});
            }
        }
        return msgList;
    }

    /**
     * <pre>
     * makeCompVal
     * </pre>
     * @param msg EZDMsg
     * @param itemNmList Message Item Name
     * @return
     */
    private static String makeInternalPostCdGrpKey(NMAL7230_ACMsg bizLineMsg) {

        StringBuilder compVal = new StringBuilder();


        compVal.append(bizLineMsg.lineBizTpDescTxt_A1.getValue());
        compVal.append(",");
        compVal.append(bizLineMsg.dsAcctNm_A1.getValue());
        compVal.append(",");
        compVal.append(bizLineMsg.prcGrpNm_A1.getValue());

        return compVal.toString();
    }

    /**
     * <pre>
     * portal code format check
     * </pre>
     * @param val String
     * @return check result
     */
    public static boolean checkPortalCodeFormat(String val) {

        String regexFive = FIVE_POSTALCODE_PATTERN;
        Pattern pFive = Pattern.compile(regexFive);
        Matcher m5 = pFive.matcher(val);
        if (m5.find()) {
            return true;
        }

        String regexTen = TEN_POSTALCODE_PATTERN;
        Pattern pTen = Pattern.compile(regexTen);
        Matcher m10 = pTen.matcher(val);
        if (m10.find()) {
            return true;
        }

        return false;
    }

    /**
     * <pre>
     * portal code format check
     * </pre>
     * @param val String
     * @return check result
     */
    private static String convertdigit5to10(String val) {

        String regexFive = FIVE_POSTALCODE_PATTERN;
        Pattern pFive = Pattern.compile(regexFive);
        Matcher m5 = pFive.matcher(val);
        if (m5.find()) {
            return val + PORTALCODE_ZERO_PACK;
        }

        return val;
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
