/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL2840;

import static business.blap.NMAL2840.constant.NMAL2840Constant.NZZM0002I;
import static business.blap.NMAL2840.constant.NMAL2840Constant.UPDATE_COUNT_CONFIGRATION;
import static business.blap.NMAL2840.constant.NMAL2840Constant.ZZZM9006E;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import parts.common.EZDAbendException;
import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NMAL2840.common.NMAL2840CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DUNS_PROC_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * NMAL2840BL02
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/25   Fujitsu         R.Nakamura      Create          N/A
 * 2016/06/14   Fujitsu         H.Ikeda         Update          QC#9960
 * 2016/06/15   Fujitsu         R.Nakamura      Update          QC#10073
 * 2016/06/20   Fujitsu         R.Nakamura      Update          QC#10340
 * 2016/06/28   Fujitsu         R.Nakamura      Update          QC#10905
 * 2016/06/29   Fujitsu         R.Nakamura      Update          QC#10905
 * 2016/07/01   Fujitsu         R.Nakamura      Update          QC#11316
 * 2016/10/06   Fujitsu         R.Nakamura      Update          QC#14861
 * 2017/12/15   Fujitsu         Hd.Sugawara     Update          QC#20905
 *</pre>
 */
public class NMAL2840BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NMAL2840CMsg bizMsg = (NMAL2840CMsg) cMsg;
            NMAL2840SMsg glblMsg = (NMAL2840SMsg) sMsg;

            if ("NMAL2840_INIT".equals(screenAplID)) {
                doProcess_NMAL2840_INIT(bizMsg, glblMsg);

            } else if ("NMAL2840Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NMAL2840Scrn00_CMN_Clear(bizMsg, glblMsg);

            } else if ("NMAL2840Scrn00_ExpForReview".equals(screenAplID)) {
                doProcess_NMAL2840Scrn00_ExpForReview(bizMsg, glblMsg);

            } else if ("NMAL2840Scrn00_ExtractSend".equals(screenAplID)) {
                doProcess_NMAL2840Scrn00_ExtractSend(bizMsg, glblMsg);

            } else if ("NMAL2840Scrn00_MoveWin_UploadPricing".equals(screenAplID)) {
                doProcess_NMAL2840Scrn00_MoveWin_UploadPricing(bizMsg, glblMsg);

            } else if ("NMAL2840Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NMAL2840Scrn00_PageNext(bizMsg, glblMsg);

            } else if ("NMAL2840Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NMAL2840Scrn00_PagePrev(bizMsg, glblMsg);

            } else if ("NMAL2840Scrn00_ExtractReset".equals(screenAplID)) {
                doProcess_NMAL2840Scrn00_ExtractReset(bizMsg, glblMsg);

            } else if ("NMAL2840Scrn00_ReviewReset".equals(screenAplID)) {
                doProcess_NMAL2840Scrn00_ReviewReset(bizMsg, glblMsg);

            } else if ("NMAL2840_ZYPL0210".equals(screenAplID)) {
                doProcess_NMAL2840_ZYPL0210(bizMsg, glblMsg);

            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * INIT Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL2840_INIT(NMAL2840CMsg bizMsg, NMAL2840SMsg glblMsg) {

        initExtractForDNBCleansing(bizMsg);

        initReviewAndProcessDNBData(bizMsg);

        initAuditInformation(bizMsg, glblMsg);
    }

    /**
     * Clear Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL2840Scrn00_CMN_Clear(NMAL2840CMsg bizMsg, NMAL2840SMsg glblMsg) {

        initExtractForDNBCleansing(bizMsg);

        initReviewAndProcessDNBData(bizMsg);

        initAuditInformation(bizMsg, glblMsg);
    }

    /**
     * ExportForReview Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL2840Scrn00_ExpForReview(NMAL2840CMsg bizMsg, NMAL2840SMsg glblMsg) {

        // Del Start 2016/06/15 QC#10073
//        initExtractForDNBCleansing(bizMsg);
        // Del End 2016/06/15 QC#10073

        initAuditInformation(bizMsg, glblMsg);
    }

    /**
     * ExtractSend Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL2840Scrn00_ExtractSend(NMAL2840CMsg bizMsg, NMAL2840SMsg glblMsg) {

        // Del Start 2016/06/15 QC#10073
//        initExtractForDNBCleansing(bizMsg);
        // Del End 2016/06/15 QC#10073

        initAuditInformation(bizMsg, glblMsg);
    }

    /**
     * MoveWin_UploadPricing Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL2840Scrn00_MoveWin_UploadPricing(NMAL2840CMsg bizMsg, NMAL2840SMsg glblMsg) {
        //
    }

    /**
     * PageNext Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL2840Scrn00_PageNext(NMAL2840CMsg bizMsg, NMAL2840SMsg glblMsg) {

        NMAL2840CommonLogic.updateGlblMsg(bizMsg, glblMsg);
        int startRowNum = bizMsg.xxPageShowToNum.getValueInt();
        NMAL2840CommonLogic.loadOnePageToCMsg(bizMsg, glblMsg, startRowNum);

    }

    /**
     * PagePrev Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL2840Scrn00_PagePrev(NMAL2840CMsg bizMsg, NMAL2840SMsg glblMsg) {

        NMAL2840CommonLogic.updateGlblMsg(bizMsg, glblMsg);
        int startRowNum = bizMsg.xxPageShowFromNum.getValueInt() - bizMsg.A.length() - 1;
        NMAL2840CommonLogic.loadOnePageToCMsg(bizMsg, glblMsg, startRowNum);

    }

    /**
     * ResetFilters_ExtractDNBCleansing Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL2840Scrn00_ExtractReset(NMAL2840CMsg bizMsg, NMAL2840SMsg glblMsg) {

        initExtractForDNBCleansing(bizMsg);

        initAuditInformation(bizMsg, glblMsg);
    }

    /**
     * ResetFilters_ReviewProcessDNBData Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL2840Scrn00_ReviewReset(NMAL2840CMsg bizMsg, NMAL2840SMsg glblMsg) {

        initReviewAndProcessDNBData(bizMsg);

        initAuditInformation(bizMsg, glblMsg);
    }

    /**
     * ZYPL0210 Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL2840_ZYPL0210(NMAL2840CMsg bizMsg, NMAL2840SMsg glblMsg) {
        //
    }

    /**
     * Init Extract for DNB Cleansing
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void initExtractForDNBCleansing(NMAL2840CMsg bizMsg) {

        getPullDownDunsExtractModeList(bizMsg);

        getLastEextractSendDate(bizMsg);

        getExtractFieldsExtractSend(bizMsg);
    }

    /**
     * Init Review and Process DNB Data for DNB Cleansing
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void initReviewAndProcessDNBData(NMAL2840CMsg bizMsg) {

        getMaxUpdateCount(bizMsg);

        getPullDownImportedDNBDateList(bizMsg);

        getLastDNBFileUpload(bizMsg);

        getExtractFieldsDNBFileUpdate(bizMsg);
    }

    /**
     * Init Audit Information
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void initAuditInformation(NMAL2840CMsg bizMsg, NMAL2840SMsg glblMsg) {

        getAuditInformation(bizMsg, glblMsg);
    }

    /**
     * getPullDownADCode
     * @param cMsg NMAL3000CMsg
     */
    private void getPullDownDunsExtractModeList(NMAL2840CMsg bizMsg) {

        S21SsmEZDResult ssmResult = NMAL2840Query.getInstance().getPullDownDunsExtractModeList(bizMsg);

        bizMsg.dunsCritCd_PC.clear();
        bizMsg.dunsCritDescTxt_PC.clear();
        if (!ssmResult.isCodeNormal()) {
            return;
        }

        List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
        // Mod Start 2016/06/15 QC#10073
        String dunsCritCd = NMAL2840CommonLogic.getDunsCRITCd(getGlobalCompanyCode(), (String) resultList.get(0).get("DUNS_CRIT_DEF_VAL_TXT"));
//        ZYPEZDItemValueSetter.setValue(bizMsg.dunsCritCd_PS, (String) resultList.get(0).get("DUNS_CRIT_DEF_VAL_TXT"));
        ZYPEZDItemValueSetter.setValue(bizMsg.dunsCritCd_PS, dunsCritCd);
        // Mod End 2016/06/15 QC#10073
        for (int i = 0; i < resultList.size() && i < bizMsg.dunsCritCd_PC.length(); i++) {
            Map<String, Object> resultMap = (Map<String, Object>) resultList.get(i);
            ZYPEZDItemValueSetter.setValue(bizMsg.dunsCritCd_PC.no(i), (String) resultMap.get("DUNS_CRIT_CD"));
            ZYPEZDItemValueSetter.setValue(bizMsg.dunsCritDescTxt_PC.no(i), (String) resultMap.get("DUNS_CRIT_DESC_TXT"));
        }
    }

    /**
     * getPullDownADCode
     * @param cMsg NMAL2840CMsg
     */
    private void getPullDownImportedDNBDateList(NMAL2840CMsg bizMsg) {

        S21SsmEZDResult ssmResult = NMAL2840Query.getInstance().getPullDownImportedDNBDateList(bizMsg);

        bizMsg.xxDtTm_PS.clear();
        bizMsg.xxDtTm_PC.clear();
        bizMsg.xxDtTm_PN.clear();
        if (!ssmResult.isCodeNormal()) {
            return;
        }

        List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
        for (int i = 0; i < resultList.size() && i < bizMsg.xxDtTm_PC.length(); i++) {
            Map<String, Object> resultMap = (Map<String, Object>) resultList.get(i);
            String resultValue = (String) resultMap.get("DUNS_PROC_TS");
            ZYPEZDItemValueSetter.setValue(bizMsg.xxDtTm_PC.no(i), resultValue);
            ZYPEZDItemValueSetter.setValue(bizMsg.xxDtTm_PN.no(i), NMAL2840CommonLogic.formatDateTime(resultValue));
            // Mod Start 2016/07/01 QC#11316
            if (i == 0) {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxDtTm_PS, NMAL2840CommonLogic.formatDateTime(resultValue));
            }
            // Mod End 2016/07/01 QC#11316
        }
    }

    /**
     * getLastEextractSendDate
     * @param cMsg NMAL2840CMsg
     */
    private void getLastEextractSendDate(NMAL2840CMsg bizMsg) {

        // Mod Start 2016/10/06 QC#14861
//        S21SsmEZDResult ssmResult = NMAL2840Query.getInstance().getLastDNBDate(bizMsg, DUNS_PROC_TP_CD_10);
        S21SsmEZDResult ssmResult = NMAL2840Query.getInstance().getLastDNBDate(bizMsg, DUNS_PROC_TP.EXTRACT_FOR_DNB);
        // Mod End 2016/10/06 QC#14861

        bizMsg.xxDtTm_1.clear();
        if (!ssmResult.isCodeNormal()) {
            return;
        }
        // Del Start 2016/06/14 QC#9960
//        bizMsg.xxDtTm_1.setValue(ssmResult.getResultObject().toString());
        // Del End 2016/06/14 QC#9960
        String resultValue = (String) ssmResult.getResultObject();
        ZYPEZDItemValueSetter.setValue(bizMsg.xxDtTm_1, NMAL2840CommonLogic.formatDateTime(resultValue));
    }

    /**
     * getLastDNBFileUpload
     * @param cMsg NMAL2840CMsg
     */
    private void getLastDNBFileUpload(NMAL2840CMsg bizMsg) {

        // Mod Start 2016/10/06 QC#14861
//        S21SsmEZDResult ssmResult = NMAL2840Query.getInstance().getLastDNBDate(bizMsg, DUNS_PROC_TP_CD_40);
        S21SsmEZDResult ssmResult = NMAL2840Query.getInstance().getLastDNBDate(bizMsg, DUNS_PROC_TP.UPLOAD_DNB_FILE);
        // Mod End 2016/10/06 QC#14861

        bizMsg.xxDtTm_2.clear();
        if (!ssmResult.isCodeNormal()) {
            return;
        }
        String resultValue = (String) ssmResult.getResultObject();
        ZYPEZDItemValueSetter.setValue(bizMsg.xxDtTm_2, NMAL2840CommonLogic.formatDateTime(resultValue));
    }

    /**
     * getLastSextractSendDate
     * @param cMsg NMAL2840CMsg
     */
    private void getMaxUpdateCount(NMAL2840CMsg bizMsg) {

        // Mod Start 2016/06/28 QC#10905
//        NUM_CONSTTMsg numConstMsg = new NUM_CONSTTMsg();
//        numConstMsg.setSQLID("001");
//        numConstMsg.setConditionValue("glblCmpyCd01", getGlobalCompanyCode());
//        numConstMsg.setConditionValue("numConstNm01", UPDATE_COUNT_CONFIGRATION);
//
//        NUM_CONSTTMsgArray numConstArray = (NUM_CONSTTMsgArray) EZDTBLAccessor.findByCondition(numConstMsg);
//
//        if (numConstArray == null || numConstArray.length() <= 0) {
//            throw new EZDAbendException(ZZZM9006E, "Max Upload Count");
//        }
//
//        numConstMsg = numConstArray.no(0);
//        BigDecimal resultValue = numConstMsg.numConstVal.getValue();
        BigDecimal resultValue = ZYPCodeDataUtil.getNumConstValue(UPDATE_COUNT_CONFIGRATION, getGlobalCompanyCode());
        if (!ZYPCommonFunc.hasValue(resultValue)) {
            throw new EZDAbendException(ZZZM9006E, "Max Upload Count");
        }
        // Mod Start 2016/06/28 QC#10905
        ZYPEZDItemValueSetter.setValue(bizMsg.xxRowNum, resultValue);
    }

    /**
     * getExtractFieldsExtractSend
     * @param cMsg NMAL2840CMsg
     */
    private void getExtractFieldsExtractSend(NMAL2840CMsg bizMsg) {

        S21SsmEZDResult ssmResult = NMAL2840Query.getInstance().getExtractFieldsExtractSend(bizMsg);

        NMAL2840CommonLogic.clearExtractFieldsExtractSend(bizMsg);

        NMAL2840CommonLogic.setExtractFieldsExtractSend(bizMsg, ssmResult);

    }

    /**
     * getExtractFieldsDNBFileUpdate
     * @param cMsg NMAL2840CMsg
     */
    private void getExtractFieldsDNBFileUpdate(NMAL2840CMsg bizMsg) {

        S21SsmEZDResult ssmResult = NMAL2840Query.getInstance().getExtractFieldsDNBFileUpdate(bizMsg);

        NMAL2840CommonLogic.clearExtractFieldsDNBFileUpload(bizMsg);

        NMAL2840CommonLogic.setExtractFieldsDNBFileUpload(bizMsg, ssmResult);

    }

    /**
     * getExtractFieldsDNBFileUpdate
     * @param cMsg NMAL2840CMsg[
     * @param glblMsg NMAL2840SMsg
     */
    private void getAuditInformation(NMAL2840CMsg bizMsg, NMAL2840SMsg glblMsg) {

        NMAL2840CommonLogic.clearAuditInformation(bizMsg, glblMsg);

        S21SsmEZDResult ssmResult = NMAL2840Query.getInstance().getAuditInformation(bizMsg, glblMsg);

        if (ssmResult.isCodeNotFound()) {
            bizMsg.xxPageShowFromNum.clear();
            bizMsg.xxPageShowToNum.clear();
            bizMsg.xxPageShowOfNum.clear();
        } else {
            List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
            String checkGroupValue = "";
            int i = 0;
            int cnt = 0;
            // Add Start 2016/06/20 QC#10340
            String setComment = "";
            // Add End 2016/06/20 QC#10340

            for (; i < resultList.size() && cnt < glblMsg.A.length(); i++) {
                Map<String, Object> resultMap = (Map<String, Object>) resultList.get(i);
                // Mod Start 2016/07/01 QC#11316
                // Mod Start 2016/10/06 QC#14861
//                String checkKeyValue = NMAL2840CommonLogic.setStringValue(resultMap, "DUNS_FILE_NM") //
//                        + "," + NMAL2840CommonLogic.setStringValue(resultMap, "DUNS_PROC_TS");
                // Mod Start 2017/12/15 QC#20905
                //String checkKeyValue = NMAL2840CommonLogic.getStringValue(resultMap, "DUNS_FILE_NM") //
                //+ "," + NMAL2840CommonLogic.getStringValue(resultMap, "DUNS_PROC_TS");
                String checkKeyValue = NMAL2840CommonLogic.getAuditCommentKey(resultMap);
                // Mod End 2017/12/15 QC#20905

                // Mod End 2016/10/06 QC#14861
                // Mod End 2016/07/01 QC#11316

                if (!checkKeyValue.equals(checkGroupValue) || i == 0) {
                    // Mod Start 2016/10/06 QC#14861
//                    String workStr = NMAL2840CommonLogic.setStringValue(resultMap, "DUNS_FILE_NM");
//                    String setFileNm = NMAL2840CommonLogic.setStringValueDetail(resultMap, "DUNS_FILE_TP_DESC_TXT", workStr);
                    String workStr = NMAL2840CommonLogic.getStringValue(resultMap, "DUNS_FILE_NM");
                    String setFileNm = NMAL2840CommonLogic.getStringValueDetail(resultMap, "DUNS_FILE_TP_DESC_TXT", workStr);
                    // Mod Start 2016/07/01 QC#11316
//                    workStr = NMAL2840CommonLogic.setStringValue(resultMap, "PSN_CD");
//                    String setProcess = NMAL2840CommonLogic.setStringValueDetail(resultMap, "DUNS_PROC_BY_ID", workStr);
//                    String setProcessNM = NMAL2840CommonLogic.setStringValue(resultMap, "PSN_FULL_NM");
//                    setProcess += "(" + setProcessNM + ")";
//                    String procById = NMAL2840CommonLogic.setStringValue(resultMap, "DUNS_PROC_BY_ID");
//                    String usrNm = NMAL2840CommonLogic.setStringValue(resultMap, "USR_NM");
//                    String firstNm = NMAL2840CommonLogic.setStringValue(resultMap, "FIRST_NM");
//                    String lastNm = NMAL2840CommonLogic.setStringValue(resultMap, "LAST_NM");
                    String procById = NMAL2840CommonLogic.getStringValue(resultMap, "DUNS_PROC_BY_ID");
                    String usrNm = NMAL2840CommonLogic.getStringValue(resultMap, "USR_NM");
                    String firstNm = NMAL2840CommonLogic.getStringValue(resultMap, "FIRST_NM");
                    String lastNm = NMAL2840CommonLogic.getStringValue(resultMap, "LAST_NM");
                    // Mod End 2016/10/06 QC#14861
                    String setProcess = "";
                    if (!ZYPCommonFunc.hasValue(firstNm)) {
                        setProcess = procById;
                    } else {
                        setProcess = usrNm + "(" + firstNm + " " + lastNm + ")";
                    }
                    // Mod End 2016/07/01 QC#11316
                    // Mod Start 2016/10/06 QC#14861
//                    String setTimeStamp = NMAL2840CommonLogic.setStringValue(resultMap, "DUNS_PROC_TS");
                    String setTimeStamp = NMAL2840CommonLogic.getStringValue(resultMap, "DUNS_PROC_TS");
                    // Mod End 2016/10/06 QC#14861
                    // Mod Start 2016/07/01 QC#11316
//                    String setCmntDmy = NMAL2840CommonLogic.setStringValue(resultMap, "DUNS_CRIT_DEF_VAL_FLG");
//                    // Mod Start 2016/06/29 QC10905
//                    String setCmntValue = NMAL2840CommonLogic.setStringValueComment(resultMap, "DUNS_CRIT_DEF_VAL_TXT", setCmntDmy);
//                    String setKeyValue = "";
//                    if (ZYPCommonFunc.hasValue(checkKeyValue)) {
//                        setKeyValue = checkKeyValue + ":";
//                    }
//
//                    // Mod Start 2016/06/20 QC#10340
//                    setComment = NMAL2840CommonLogic.setStringValue(resultMap, "DUNS_PROC_CMNT_TXT");
//                    if (!ZYPCommonFunc.hasValue(setComment)) {
//                        setComment += setKeyValue + setCmntValue;
//                    } else {
//                        setComment += "," + setKeyValue + setCmntValue;
//                    }
                    // Mod End 2016/06/20 QC#10340
                    // Mod End 2016/06/29 QC10905
                    setComment = "";
                    setComment = NMAL2840CommonLogic.getAuditComment(resultMap, setComment);
                    // Mod End 2016/07/01 QC#11316

                    ZYPEZDItemValueSetter.setValue(glblMsg.A.no(cnt).dunsProcTpDescTxt_A, (String) resultMap.get("DUNS_PROC_TP_DESC_TXT").toString());
                    ZYPEZDItemValueSetter.setValue(glblMsg.A.no(cnt).dunsFileNm_A, setFileNm);
                    ZYPEZDItemValueSetter.setValue(glblMsg.A.no(cnt).dunsFileLineNum_A, (BigDecimal) resultMap.get("DUNS_FILE_LINE_NUM"));
                    ZYPEZDItemValueSetter.setValue(glblMsg.A.no(cnt).xxDtTm_A, NMAL2840CommonLogic.formatDateTime(setTimeStamp));
                    // Mod Start 2016/07/01 QC#11316
                    ZYPEZDItemValueSetter.setValue(glblMsg.A.no(cnt).fill103Txt_A, setProcess);
                    ZYPEZDItemValueSetter.setValue(glblMsg.A.no(cnt).xxDunsProcCmntTxt_A, setComment);
                    // Mod End 2016/07/01 QC#11316

                    // Mod Start 2016/07/01 QC#11316
                    // Mod Start 2016/10/06 QC#14861
//                    checkGroupValue = NMAL2840CommonLogic.setStringValue(resultMap, "DUNS_FILE_NM") //
//                            + "," + NMAL2840CommonLogic.setStringValue(resultMap, "DUNS_PROC_TS");
                    // Mod Start 2017/12/15 QC#20905
                    //checkGroupValue = NMAL2840CommonLogic.getStringValue(resultMap, "DUNS_FILE_NM") //
                    //+ "," + NMAL2840CommonLogic.getStringValue(resultMap, "DUNS_PROC_TS");
                    checkGroupValue = NMAL2840CommonLogic.getAuditCommentKey(resultMap);
                    // Mod End 2017/12/15 QC#20905
                    // Mod End 2016/10/06 QC#14861
                    // Mod End 2016/07/01 QC#11316
                    cnt++;
                } else {
                    // Mod Start 2016/07/01 QC#11316
                    // Add Start 2016/06/20 QC#10340
//                    String setCmntDmy = NMAL2840CommonLogic.setStringValue(resultMap, "DUNS_CRIT_DEF_VAL_FLG");
//                    String setCmntValue = NMAL2840CommonLogic.setStringValueDetail(resultMap, "DUNS_CRIT_DEF_VAL_TXT", setCmntDmy);
//                    setComment += "," + setCmntValue;
                    setComment = NMAL2840CommonLogic.getAuditComment(resultMap, setComment);
                    ZYPEZDItemValueSetter.setValue(glblMsg.A.no(cnt - 1).xxDunsProcCmntTxt_A, setComment);
                    // Add End 2016/06/20 QC#10340
                    // Mod End 2016/07/01 QC#11316
                }
            }

            glblMsg.A.setValidCount(cnt);
            int startRowNum = (1 / bizMsg.A.length()) * bizMsg.A.length();
            NMAL2840CommonLogic.loadOnePageToCMsg(bizMsg, glblMsg, startRowNum);
        }

        bizMsg.setMessageInfo(NZZM0002I);

    }
}
