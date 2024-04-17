/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWCL0100.common;

import static business.blap.NWCL0100.constant.NWCL0100Constant.*;
import static business.blap.NWCL0100.constant.NWCL0100Constant.SCRN_ID_00;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDCMsgArray;
import parts.common.EZDMsg;
import parts.common.EZDSMsgArray;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;
import business.blap.NWCL0100.NWCL0100CMsg;
import business.blap.NWCL0100.NWCL0100Query;
import business.blap.NWCL0100.NWCL0100SMsg;
import business.file.NWCL0100F00FMsg;
import business.parts.NSZC033001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC033001.NSZC033001;
import com.canon.cusa.s21.api.NSZ.NSZC033001.constant.NSZC033001Constant;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 *<pre>
 * NWCL0100CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/23   Fujitsu         S.Ohki          Create          N/A
 * 2016/09/06   Hitachi         Y.Takeno        Update          QC#13315
 * 2016/09/07   Hitachi         Y.Takeno        Update          QC#13337
 * 2016/09/08   Hitachi         Y.Takeno        Update          QC#13341
 * 2017/11/14   Fujitsu         Mz.Takahashi    Update          Sol#265(QC#18788)
 *</pre>
 */
public class NWCL0100CommonLogic {

    /** for I18N */
    static EZDI18NLabelConv converter = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();

    /**
     * callNszc0330forDeleteSearch
     * @param bizMsg NWCL0100CMsg
     * @param glblMsg NWCL0100SMsg
     * @param srchOptUsrId String
     */
    public static void callNszc0330forDeleteSearch(NWCL0100CMsg bizMsg, NWCL0100SMsg glblMsg, String srchOptUsrId) {
        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        pMsg.clear();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_DELETE);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, bizMsg.srchOptPk_S0);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, BIZ_ID);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptUsrId, srchOptUsrId);

        if (callNszc0330(bizMsg, pMsg)) {
            createSavedSearchOptionsPullDown(bizMsg, srchOptUsrId);
            bizMsg.srchOptNm_S0.clear();

            bizMsg.setMessageInfo("ZZZM9003I", new String[] {converter.convLabel2i18nLabel(SCRN_ID_00, "Delete Search") });
        }
    }

    /**
     * callNszc0330forSaveSearch
     * @param bizMsg NWCL0100CMsg
     * @param glblMsg NWCL0100SMsg
     * @param srchOptUsrId String
     */
    public static void callNszc0330forSaveSearch(NWCL0100CMsg bizMsg, NWCL0100SMsg glblMsg, String srchOptUsrId) {
        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        pMsg.clear();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_INSERT_UPDATE);
        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptNm_S0) || isSameSaveSearchName(bizMsg)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, bizMsg.srchOptPk_S0);
        }
        if (ZYPCommonFunc.hasValue(bizMsg.srchOptNm_S0)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptNm, bizMsg.srchOptNm_S0);
        } else {
            setSelectSaveSearchName(pMsg, bizMsg);
        }
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, BIZ_ID);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptUsrId, srchOptUsrId);

        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_01, bizMsg.xxBillToAcctCdSrchTxt_H0.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_02, bizMsg.xxChkBox_H0.getValue());

        if (callNszc0330(bizMsg, pMsg)) {
            createSavedSearchOptionsPullDown(bizMsg, srchOptUsrId);
            ZYPEZDItemValueSetter.setValue(bizMsg.srchOptPk_S0, pMsg.srchOptPk);
            bizMsg.srchOptNm_S0.clear();

            bizMsg.setMessageInfo("ZZZM9003I", new String[] {converter.convLabel2i18nLabel(SCRN_ID_00, "Save Search") });
        }
    }

    private static boolean callNszc0330(NWCL0100CMsg bizMsg, NSZC033001PMsg pMsg) {
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
                        bizMsg.srchOptPk_S0.setErrorInfo(1, msgId);
                        bizMsg.srchOptNm_S0.setErrorInfo(1, msgId);
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private static boolean isSameSaveSearchName(NWCL0100CMsg bizMsg) {
        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptPk_S0)) {
            return false;
        }
        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptNm_S0)) {
            return false;
        }

        for (int i = 0; i < bizMsg.srchOptNm_L0.length(); i++) {
            if (!ZYPCommonFunc.hasValue(bizMsg.srchOptNm_L0.no(i))) {
                return false;
            }
            if (bizMsg.srchOptPk_S0.getValue().compareTo(bizMsg.srchOptPk_L0.no(i).getValue()) == 0) {
                if (bizMsg.srchOptNm_S0.getValue().equals(bizMsg.srchOptNm_L0.no(i).getValue())) {
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    /**
     * isExistSaveSearchName
     * @param bizMsg NWCL0100CMsg
     * @return boolean
     */
    public static boolean isExistSaveSearchName(NWCL0100CMsg bizMsg) {
        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptNm_S0)) {
            return false;
        }

        for (int i = 0; i < bizMsg.srchOptNm_L0.length(); i++) {
            if (!ZYPCommonFunc.hasValue(bizMsg.srchOptNm_L0.no(i))) {
                return false;
            }
            if (bizMsg.srchOptNm_S0.getValue().equals(bizMsg.srchOptNm_L0.no(i).getValue())) {
                if (ZYPCommonFunc.hasValue(bizMsg.srchOptPk_S0) && bizMsg.srchOptPk_S0.getValue().compareTo(bizMsg.srchOptPk_L0.no(i).getValue()) == 0) {
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
     * @param bizMsg NWCL0100CMsg
     */
    public static void setSelectSaveSearchName(NSZC033001PMsg pMsg, NWCL0100CMsg bizMsg) {
        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptPk_S0)) {
            return;
        }

        for (int i = 0; i < bizMsg.srchOptNm_L0.length(); i++) {
            if (!ZYPCommonFunc.hasValue(bizMsg.srchOptNm_L0.no(i))) {
                return;
            }
            if (bizMsg.srchOptPk_S0.getValue().compareTo(bizMsg.srchOptPk_L0.no(i).getValue()) == 0) {
                ZYPEZDItemValueSetter.setValue(pMsg.srchOptNm, bizMsg.srchOptNm_L0.no(i));
            }
        }
        return;
    }

    /**
     * loadOnePageToCMsg
     * @param cMsg EZDCMsg
     * @param cMsgArray EZDCMsgArray
     * @param sMsgArray EZDSMsgArray
     */
    public static void loadOnePageToCMsg(EZDCMsg cMsg, EZDCMsgArray cMsgArray, EZDSMsgArray sMsgArray) {

        NWCL0100CMsg bizMsg = (NWCL0100CMsg) cMsg;

        ZYPTableUtil.clear(cMsgArray);

        bizMsg.xxPageShowToNum.clear();
        bizMsg.xxPageShowOfNum.clear();

        int maxDisplayRows = cMsgArray.length();
        int startIndex = (bizMsg.xxPageShowFromNum.getValueInt() / maxDisplayRows) * maxDisplayRows;

        int i = startIndex;
        for (; i < startIndex + cMsgArray.length(); i++) {

            if (i < sMsgArray.getValidCount()) {

                EZDMsg sLineMsg = sMsgArray.get(i);
                int indexOfCMsg = i - startIndex;

                EZDMsg.copy(sLineMsg, null, cMsgArray.get(indexOfCMsg), null);

            } else {

                break;
            }
        }

        cMsgArray.setValidCount(i - startIndex);
        bizMsg.xxPageShowFromNum.setValue(startIndex + 1);
        bizMsg.xxPageShowToNum.setValue(startIndex + cMsgArray.getValidCount());
        bizMsg.xxPageShowOfNum.setValue(sMsgArray.getValidCount());
    }

    /**
     * createSavedSearchOptionsPullDown
     * @param bizMsg NWCL0100CMsg
     * @param srchOptUsrId user id
     */
    public static void createSavedSearchOptionsPullDown(NWCL0100CMsg bizMsg, String srchOptUsrId) {
        S21SsmEZDResult ssmResult = NWCL0100Query.getInstance().getSavedSearchOptionList(srchOptUsrId);
        if (!ssmResult.isCodeNormal()) {
            bizMsg.srchOptPk_L0.clear();
            bizMsg.srchOptNm_L0.clear();
            return;
        }

        bizMsg.srchOptPk_L0.clear();
        bizMsg.srchOptNm_L0.clear();

        List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
        for (int i = 0; i < resultList.size() && i < bizMsg.srchOptPk_L0.length(); i++) {
            Map<String, Object> resultMap = (Map<String, Object>) resultList.get(i);
            bizMsg.srchOptPk_L0.no(i).setValue((BigDecimal) resultMap.get("SRCH_OPT_PK"));
            bizMsg.srchOptNm_L0.no(i).setValue((String) resultMap.get("SRCH_OPT_NM"));
        }

    }

    /**
     * callNszc0330forSearchOption
     * @param bizMsg NWCL0100CMsg
     * @param glblMsg NWCL0100SMsg
     * @param usrId String
     * @param glblCmpyCd String
     */
    public static void callNszc0330forSearchOption(NWCL0100CMsg bizMsg, NWCL0100SMsg glblMsg, String usrId, String glblCmpyCd) {

        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        pMsg.clear();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_SEARCH);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptUsrId, usrId);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, bizMsg.srchOptPk_S0);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, BIZ_ID);

        if (!callNszc0330(bizMsg, pMsg)) {
            return;
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.xxBillToAcctCdSrchTxt_H0, pMsg.srchOptTxt_01);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_H0, pMsg.srchOptTxt_02);

    }

    /**
     * It copy 'NWCL0100CMsg.A' to 'NWCL0100SMsg.A' .
     * @param bizMsg NWCL0100CMsg
     * @param glblMsg NWCL0100SMsg
     */
    public static void saveCurrentPageToSMsg(NWCL0100CMsg bizMsg, NWCL0100SMsg glblMsg) {

        bizMsg.clearErrorInfo();
        bizMsg.A.clearErrorInfo();
        glblMsg.clearErrorInfo();
        glblMsg.A.clearErrorInfo();

        int fromIdx = bizMsg.xxPageShowFromNum.getValueInt() - 1;
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            EZDMsg.copy(bizMsg.A.no(i), null, glblMsg.A.no(fromIdx + i), null);
        }
    }

    /**
     * Show First Error Page
     * @param bizMsg NWCL0100CMsg
     * @param glblMsg NWCL0100SMsg
     * @return boolean
     */
    public static boolean showFirstErrorPage(NWCL0100CMsg bizMsg, NWCL0100SMsg glblMsg) {
        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            if (glblMsg.A.no(i).effFromDt_A0.isError() || glblMsg.A.no(i).effThruDt_A0.isError() || glblMsg.A.no(i).splyPgmUnitAmtRate_A0.isError() || glblMsg.A.no(i).splyPgmMlyQuotQty_A0.isError() || glblMsg.A.no(i).billToCustAcctCd_A0.isError()) {
                getPageFromSMsg(bizMsg, glblMsg, i);
                return false;
            }
        }
        return true;
    }

    /**
     * Get Page From SMsg
     * @param bizMsg NWCL0100CMsg
     * @param sMsg NWCL0100SMsg
     * @param indexOfSMsg int
     */
    private static void getPageFromSMsg(NWCL0100CMsg bizMsg, NWCL0100SMsg glblMsg, int indexOfSMsg) {
        ZYPTableUtil.clear(bizMsg.A);

        int startIndexOfSMsg = (indexOfSMsg / bizMsg.A.length()) * bizMsg.A.length();

        // Copy page from SMsg to CMsg
        int copiedRows = 0;
        for (int i = 0; i < bizMsg.A.length(); i++) {
            if (startIndexOfSMsg + i < glblMsg.A.getValidCount()) {
                EZDMsg.copy(glblMsg.A.no(startIndexOfSMsg + i), null, bizMsg.A.no(i), null);
                copiedRows++;
            } else {
                break;
            }
        }
        bizMsg.A.setValidCount(copiedRows);

        // Set page number
        if (glblMsg.A.getValidCount() > 0) {
            int totalRecordCount = glblMsg.A.getValidCount();
            int maxRowPerPage = bizMsg.A.length();

            // Record count
            bizMsg.xxPageShowFromNum.setValue(startIndexOfSMsg + 1);
            bizMsg.xxPageShowToNum.setValue(Math.min(totalRecordCount, startIndexOfSMsg + maxRowPerPage));
            bizMsg.xxPageShowOfNum.setValue(totalRecordCount);

        } else {
            bizMsg.xxPageShowFromNum.clear();
            bizMsg.xxPageShowToNum.clear();
            bizMsg.xxPageShowOfNum.clear();
        }
    }

    /**
     * Update the Global Message.
     * @param bizMsg NWCL0100CMsg
     * @param glblMsg NWCL0100SMsg
     */
    public static void updateGlblMsg(NWCL0100CMsg bizMsg, NWCL0100SMsg glblMsg) {

        int ixG = bizMsg.xxPageShowFromNum.getValueInt() - 1;
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            EZDMsg.copy(bizMsg.A.no(i), null, glblMsg.A.no(ixG + i), null);
        }
    }

    /**
     * Write CSV File
     * @param bizMsg NWCL0100CMsg
     * @param rs ResultSet
     * @throws SQLException SQLException
     */
    public static void writeCsvFile(NWCL0100CMsg bizMsg, ResultSet rs) throws SQLException {

        NWCL0100F00FMsg fMsg = new NWCL0100F00FMsg();
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(bizMsg.xxFileData.getTempFilePath(), fMsg);

        // write header
        writeCsvFileHeader(csvOutFile);

        // write contents
        do {

            String rsEffFromDt = rs.getString("EFF_FROM_DT");
            if (ZYPCommonFunc.hasValue(rsEffFromDt)) {
                rsEffFromDt = ZYPDateUtil.DateFormatter(rsEffFromDt, DATE_FORMAT_YYYYMMDD, DATE_FORMAT_MM_DD_YYYY);
            }

            String rsEffThruDt = rs.getString("EFF_THRU_DT");
            if (ZYPCommonFunc.hasValue(rsEffThruDt)) {
                rsEffThruDt = ZYPDateUtil.DateFormatter(rsEffThruDt, DATE_FORMAT_YYYYMMDD, DATE_FORMAT_MM_DD_YYYY);
            }

            String rsContrCratDt = rs.getString("CONTR_CRAT_DT");
            if (ZYPCommonFunc.hasValue(rsContrCratDt)) {
                rsContrCratDt = ZYPDateUtil.DateFormatter(rsContrCratDt, DATE_FORMAT_YYYYMMDD, DATE_FORMAT_MM_DD_YYYY);
            }

            String rsContrUpdDt = rs.getString("CONTR_UPD_DT");
            if (ZYPCommonFunc.hasValue(rsContrUpdDt)) {
                rsContrUpdDt = ZYPDateUtil.DateFormatter(rsContrUpdDt, DATE_FORMAT_YYYYMMDD, DATE_FORMAT_MM_DD_YYYY);
            }

            ZYPEZDItemValueSetter.setValue(fMsg.billToCustAcctCd, rs.getString("BILL_TO_CUST_ACCT_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.dsAcctNm, rs.getString("DS_ACCT_NM"));
            ZYPEZDItemValueSetter.setValue(fMsg.prcGrpPk, rs.getBigDecimal("PRC_GRP_PK"));
            ZYPEZDItemValueSetter.setValue(fMsg.prcGrpNm, rs.getString("PRC_GRP_NM"));
            ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_A, rsEffFromDt);
            ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_B, rsEffThruDt);
            ZYPEZDItemValueSetter.setValue(fMsg.splyPgmUnitAmtRate, rs.getBigDecimal("SPLY_PGM_UNIT_AMT_RATE"));
            ZYPEZDItemValueSetter.setValue(fMsg.splyPgmMlyQuotQty, rs.getBigDecimal("SPLY_PGM_MLY_QUOT_QTY"));
            ZYPEZDItemValueSetter.setValue(fMsg.autoDrCratFlg, rs.getString("AUTO_DR_CRAT_FLG"));
            ZYPEZDItemValueSetter.setValue(fMsg.xxPsnNm_A0, rs.getString("CRAT_PSN_NM"));
            ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_C, rsContrCratDt);
            ZYPEZDItemValueSetter.setValue(fMsg.xxPsnNm_A1, rs.getString("UPD_PSN_NM"));
            ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_D, rsContrUpdDt);

            csvOutFile.write();
        } while (rs.next());

        csvOutFile.close();
    }

    /**
     * Write CSV File Header
     * @param csvOutFile ZYPCSVOutFile
     */
    private static void writeCsvFileHeader(ZYPCSVOutFile csvOutFile) {
        EZDI18NLabelConv labelConv = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();
        final String[] csvHeader = new String[] {labelConv.convLabel2i18nLabel(BIZ_ID, "Bill To Acct Cd"), labelConv.convLabel2i18nLabel(BIZ_ID, "Bill To Acct Nm"), 
                labelConv.convLabel2i18nLabel(BIZ_ID, "Customer Group Cd"), labelConv.convLabel2i18nLabel(BIZ_ID, "Customer Group"),
                labelConv.convLabel2i18nLabel(BIZ_ID, "Valid From"), labelConv.convLabel2i18nLabel(BIZ_ID, "Valid To"), labelConv.convLabel2i18nLabel(BIZ_ID, "Rate"), labelConv.convLabel2i18nLabel(BIZ_ID, "Monthly Quota"),
                labelConv.convLabel2i18nLabel(BIZ_ID, "Auto Debit"), labelConv.convLabel2i18nLabel(BIZ_ID, "Created By"), labelConv.convLabel2i18nLabel(BIZ_ID, "Created On"), labelConv.convLabel2i18nLabel(BIZ_ID, "Changed By"),
                labelConv.convLabel2i18nLabel(BIZ_ID, "Changed On") };
        csvOutFile.writeHeader(csvHeader);
    }

// 2016/09/06 QC#13315 Add Start
    /**
     * isExistDsAcctCust
     * 
     * @param bizMsg NWCL0100CMsg
     * @param acctNum Account Number
     * @return boolean
     */
    public static boolean isExistDsAcctCust(NWCL0100CMsg bizMsg, String acctNum) {

      S21SsmEZDResult result = NWCL0100Query.getInstance().countupBillToCustPk(acctNum);
      BigDecimal resultCount = (BigDecimal)result.getResultObject();
      if (ZYPCommonFunc.hasValue(resultCount) && resultCount.intValue() > 0) {
          return true;
      }
      return false;
    }

// 2016/09/06 QC#13315 Add End

// 2017/11/14 Sol#265(QC#18788) Add Start
    public static boolean isExistPrcGrpPk(NWCL0100CMsg bizMsg, BigDecimal prcGrpPk) {

        S21SsmEZDResult result = NWCL0100Query.getInstance().countupPrcGrpPk(prcGrpPk);
        BigDecimal resultCount = (BigDecimal)result.getResultObject();
        if (ZYPCommonFunc.hasValue(resultCount) && resultCount.intValue() > 0) {
            return true;
        }
        return false;
      }
// 2017/11/14 Sol#265(QC#18788) Add End

// 2016/09/07 QC#13337 Add Start
    /**
     * checkValidTermDuplication
     * 
     * @param glblMsg NWCL0100SMsg
     */
    public static void checkValidTermDuplication(NWCL0100SMsg glblMsg) {

        Map<String, List<Map<String, Object>>> compareListMap = new HashMap<String, List<Map<String, Object>>>();
        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            String acctCd = glblMsg.A.no(i).billToCustAcctCd_A0.getValue();

            if (!ZYPCommonFunc.hasValue(acctCd)) {
                continue;
            }

            if (!compareListMap.containsKey(acctCd)) {
                compareListMap.put(acctCd, new ArrayList<Map<String, Object>>());
            }
            List<Map<String, Object>> compareList = compareListMap.get(acctCd);
            compareList.add(createCompareDataMap(glblMsg, i));
        }

        for(String acctCd : compareListMap.keySet()) {
            List<Map<String, Object>> compareList = compareListMap.get(acctCd);
            List<BigDecimal> updRecordPkList = new ArrayList<BigDecimal>();
            for (Map dataMap : compareList) {
                BigDecimal splyPgmContrPk = (BigDecimal)dataMap.get("SPLY_PGM_CONTR_PK");
                if (ZYPCommonFunc.hasValue(splyPgmContrPk)) {
                    updRecordPkList.add(splyPgmContrPk);
                }
            }

            S21SsmEZDResult result = NWCL0100Query.getInstance().getSplyPgmContrByAcctCd(acctCd);
            if (result.isCodeNormal()) {
                List<Map<String, Object>> resultList = (List<Map<String, Object>>)result.getResultObject();
                for (Map<String, Object> resultMap : resultList) {
                    BigDecimal splyPgmContrPk = (BigDecimal)resultMap.get("SPLY_PGM_CONTR_PK");
                    if (!updRecordPkList.contains(splyPgmContrPk)) {
                        compareList.add(resultMap);
                    }
                }
            }
        }

        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            String frmDt = glblMsg.A.no(i).effFromDt_A0.getValue();
            String toDt = replaceEmptyDt(glblMsg.A.no(i).effThruDt_A0.getValue());
            String acctCd = glblMsg.A.no(i).billToCustAcctCd_A0.getValue();
            String dplyCtrlCd = glblMsg.A.no(i).dplyCtrlCd_A0.getValue();

            if (!ZYPCommonFunc.hasValue(acctCd)) {
                continue;
            }

            if (DISP_CTRL_CD_CURRENT.equals(dplyCtrlCd) || DISP_CTRL_CD_FUTURE.equals(dplyCtrlCd) || DISP_CTRL_CD_NEW.equals(dplyCtrlCd) || DISP_CTRL_CD_SALESDATE.equals(dplyCtrlCd)) {
                List<Map<String, Object>> compareList = compareListMap.get(acctCd);
                for (Map<String, Object> dataMap : compareList) {
                    Integer cmpNo = (Integer)dataMap.get("NO");
                    String cmpFrmDt = (String)dataMap.get("EFF_FROM_DT");
                    String cmpToDt = replaceEmptyDt((String)dataMap.get("EFF_THRU_DT"));

                    if (!Integer.valueOf(i).equals(cmpNo)) {
                        if (!(cmpToDt.compareTo(frmDt) < 0 || cmpFrmDt.compareTo(toDt) > 0)) {
                            if (DISP_CTRL_CD_CURRENT.equals(dplyCtrlCd)) {
                                glblMsg.A.no(i).effThruDt_A0.setErrorInfo(1, NWCM0126E);
                            } else {
                                glblMsg.A.no(i).effFromDt_A0.setErrorInfo(1, NWCM0126E);
                                glblMsg.A.no(i).effThruDt_A0.setErrorInfo(1, NWCM0126E);
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * createCompareDataMap
     * 
     * @param bizMsg NWCL0100SMsg
     * @param i int
     * @return Map<String, Object>
     */
    
    private static Map<String, Object> createCompareDataMap(NWCL0100SMsg glblMsg, int i) {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("NO", i);
        dataMap.put("SPLY_PGM_CONTR_PK", glblMsg.A.no(i).splyPgmContrPk_A0.getValue());
        dataMap.put("BILL_TO_CUST_ACCT_CD", glblMsg.A.no(i).billToCustAcctCd_A0.getValue());
        dataMap.put("EFF_FROM_DT", glblMsg.A.no(i).effFromDt_A0.getValue());
        dataMap.put("EFF_THRU_DT", glblMsg.A.no(i).effThruDt_A0.getValue());

        return dataMap;
    }

    private static final String replaceEmptyDt(String dt) {
        if (!ZYPCommonFunc.hasValue(dt)) {
            return MAX_DT_VAL;
        }
        return dt;
    }
    // 2016/09/07 QC#13337 Add End
}
