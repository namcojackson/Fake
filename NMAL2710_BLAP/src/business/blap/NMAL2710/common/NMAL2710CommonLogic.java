/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL2710.common;

import static business.blap.NMAL2710.constant.NMAL2710Constant.BETWEEN_SAME;
import static business.blap.NMAL2710.constant.NMAL2710Constant.CSV_HEADER_CONST_NM_CMNT;
import static business.blap.NMAL2710.constant.NMAL2710Constant.CSV_HEADER_CONST_NM_MOVE_EFF_FROM;
import static business.blap.NMAL2710.constant.NMAL2710Constant.CSV_HEADER_CONST_NM_MOVE_EFF_TO;
import static business.blap.NMAL2710.constant.NMAL2710Constant.CSV_HEADER_CONST_NM_NEW_ORG_NM;
import static business.blap.NMAL2710.constant.NMAL2710Constant.CSV_HEADER_CONST_NM_OLD_ORG_NM;
import static business.blap.NMAL2710.constant.NMAL2710Constant.CSV_HEADER_CONST_NM_OPRD;
import static business.blap.NMAL2710.constant.NMAL2710Constant.CSV_HEADER_CONST_NM_POST_FROM;
import static business.blap.NMAL2710.constant.NMAL2710Constant.CSV_HEADER_CONST_NM_POST_TO;
import static business.blap.NMAL2710.constant.NMAL2710Constant.CSV_HEADER_NUM;
import static business.blap.NMAL2710.constant.NMAL2710Constant.HIGH_VAL_DT;
import static business.blap.NMAL2710.constant.NMAL2710Constant.MOVE_EFF;
import static business.blap.NMAL2710.constant.NMAL2710Constant.MOVE_EFF_FROM;
import static business.blap.NMAL2710.constant.NMAL2710Constant.MOVE_FROM_POST_EFF;
import static business.blap.NMAL2710.constant.NMAL2710Constant.MOVE_POST_CD_TO;
import static business.blap.NMAL2710.constant.NMAL2710Constant.MOVE_POST_EFF;
import static business.blap.NMAL2710.constant.NMAL2710Constant.NMAM0086E;
import static business.blap.NMAL2710.constant.NMAL2710Constant.NMAM8634E;
import static business.blap.NMAL2710.constant.NMAL2710Constant.NMAM8433E;
import static business.blap.NMAL2710.constant.NMAL2710Constant.NMAM8489E;
import static business.blap.NMAL2710.constant.NMAL2710Constant.NMAM8497E;
import static business.blap.NMAL2710.constant.NMAL2710Constant.NMAM8600E;
import static business.blap.NMAL2710.constant.NMAL2710Constant.SELCETED_RULE;
import static business.blap.NMAL2710.constant.NMAL2710Constant.SELCETED_TRTY;
import static business.blap.NMAL2710.constant.NMAL2710Constant.TRTY_LOGIC_TYPE;
import static business.blap.NMAL2710.constant.NMAL2710Constant.YYYYMMDD_LENGTH;
import static business.blap.NMAL2710.constant.NMAL2710Constant.ZZZM9006E;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import business.blap.NMAL2710.NMAL2710CMsg;
import business.blap.NMAL2710.NMAL2710Query;
import business.blap.NMAL2710.NMAL2710_ACMsg;
import business.file.NMAL2710F00FMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 *<pre>
 * NMAL2710CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/16   Fujitsu         W.Honda         Create          N/A
 * 2016/06/29   Fujitsu         W.Honda         Update          QC#11091
 * 2016/06/29   Fujitsu         W.Honda         Update          QC#11370
 * 2016/06/29   Fujitsu         W.Honda         Update          QC#11319
 * 2016/07/20   Hitachi         J.Kim           Update          QC#11940
 * 2016/10/19   Hitachi         T.Mizuki        Update          QC#11940
 *</pre>
 */
public class NMAL2710CommonLogic {

    /**
     * set Search Result
     * @param ssmResult S21SsmEZDResult
     * @param bizMsg NMAL2710CMsg
     */
    public static void setSeachResult(S21SsmEZDResult ssmResult, NMAL2710CMsg bizMsg) {

        List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();

        int i = 0;
        for (Map<String, Object> result : resultList) {
            if (i >= bizMsg.A.getValidCount()) {
                break;
            }
            NMAL2710_ACMsg bizLineMsg = bizMsg.A.no(i);
            ZYPEZDItemValueSetter.setValue(bizLineMsg.orgCd_A1, (String) result.get("ORG_CD"));
            ZYPEZDItemValueSetter.setValue(bizLineMsg.orgNm_A1, (String) result.get("ORG_NM"));
            ZYPEZDItemValueSetter.setValue(bizLineMsg.trtyRulePk_A1, (BigDecimal) result.get("TRTY_RULE_PK"));
            ZYPEZDItemValueSetter.setValue(bizLineMsg.orgDescTxt_A1, (String) result.get("ORG_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(bizLineMsg.bizAreaOrgCd_A1, (String) result.get("BIZ_AREA_ORG_CD"));
            ZYPEZDItemValueSetter.setValue(bizLineMsg.bizAreaOrgDescTxt_A1, (String) result.get("BIZ_AREA_ORG_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(bizLineMsg.trtyGrpTpCd_A1, (String) result.get("TRTY_GRP_TP_CD"));
            ZYPEZDItemValueSetter.setValue(bizLineMsg.trtyGrpTpDescTxt_A1, (String) result.get("TRTY_GRP_TP_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(bizLineMsg.trtyRuleOprdTpCd_A1, (String) result.get("TRTY_RULE_OPRD_TP_CD"));
            ZYPEZDItemValueSetter.setValue(bizLineMsg.trtyRuleOprdTpDescTxt_A1, (String) result.get("TRTY_RULE_OPRD_TP_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(bizLineMsg.trtyRuleFromValTxt_A1, (String) result.get("TRTY_RULE_FROM_VAL_TXT"));
            ZYPEZDItemValueSetter.setValue(bizLineMsg.trtyRuleThruValTxt_A1, (String) result.get("TRTY_RULE_THRU_VAL_TXT"));
            ZYPEZDItemValueSetter.setValue(bizLineMsg.trtyRuleLogicTpCd_A1, (String) result.get("TRTY_RULE_LOGIC_TP_CD"));
            ZYPEZDItemValueSetter.setValue(bizLineMsg.trtyRuleLogicTpDescTxt_A1, (String) result.get("TRTY_RULE_LOGIC_TP_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(bizLineMsg.effFromDt_A1, (String) result.get("EFF_FROM_DT"));
            ZYPEZDItemValueSetter.setValue(bizLineMsg.effThruDt_A1, (String) result.get("EFF_THRU_DT"));
            i++;
        }
    }

    /**
     * writeCsvFile
     * @param bizMsg NMAL2710CMsg
     * @param rs ResultSet
     * @param glblCmpyCd String
     * @throws SQLException SQLException
     */
    public static void writeCsvFile(NMAL2710CMsg bizMsg, ResultSet rs, String glblCmpyCd) throws SQLException {

        NMAL2710F00FMsg fMsg = new NMAL2710F00FMsg();
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(bizMsg.xxFileData.getTempFilePath(), fMsg);

        String[] csvHeader = createCsvFileHeaderNameArray(glblCmpyCd);
        if (!checkCsvHeader(csvHeader)) {
            bizMsg.setMessageInfo(NMAM8433E, new String[] {"CSV Header" });
            return;
        }

        csvOutFile.writeHeader(csvHeader);

        while (rs.next()) {
            // resultSet -> fMsg
            ZYPEZDItemValueSetter.setValue(fMsg.trtyRuleOprdTpDescTxt, rs.getString("TRTY_RULE_OPRD_TP_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(fMsg.trtyRuleFromValTxt, rs.getString("TRTY_RULE_FROM_VAL_TXT"));
            ZYPEZDItemValueSetter.setValue(fMsg.trtyRuleThruValTxt, rs.getString("TRTY_RULE_THRU_VAL_TXT"));
            ZYPEZDItemValueSetter.setValue(fMsg.oldOrgNm, rs.getString("ORG_NM"));
            // fMsg.newOrgNm do not set.
            ZYPEZDItemValueSetter.setValue(fMsg.moveEffFromDtTxt, formatDt(ZYPDateUtil.getSalesDate()));
            // fMsg.moveEffThruDtTxt do not set.
            String rqstRsltCmntTxt = bizMsg.rqstRsltCmntTxt_DC.getValue();
            rqstRsltCmntTxt = rqstRsltCmntTxt.replace("\r\n", " ");
            ZYPEZDItemValueSetter.setValue(fMsg.massUpdRsnCmntTxt, rqstRsltCmntTxt);
            csvOutFile.write();

            fMsg.clear();
        }

        csvOutFile.close();
    }

    /**
     * createCsvFileHeaderNameArray
     * @param glblCmpyCd String
     * @return String[]
     */
    public static String[] createCsvFileHeaderNameArray(String glblCmpyCd) {
        List<String> hdrElementList = new ArrayList<String>();
        hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue(CSV_HEADER_CONST_NM_OPRD, glblCmpyCd));
        hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue(CSV_HEADER_CONST_NM_POST_FROM, glblCmpyCd));
        hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue(CSV_HEADER_CONST_NM_POST_TO, glblCmpyCd));
        hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue(CSV_HEADER_CONST_NM_OLD_ORG_NM, glblCmpyCd));
        hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue(CSV_HEADER_CONST_NM_NEW_ORG_NM, glblCmpyCd));
        hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue(CSV_HEADER_CONST_NM_MOVE_EFF_FROM, glblCmpyCd));
        hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue(CSV_HEADER_CONST_NM_MOVE_EFF_TO, glblCmpyCd));
        hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue(CSV_HEADER_CONST_NM_CMNT, glblCmpyCd));

        return hdrElementList.toArray(new String[hdrElementList.size()]);
    }

    /**
     * getOrgCd
     * @param bizMsg NMAL2710CMsg
     * @return boolean
     */
    public static boolean getOrgCd(NMAL2710CMsg bizMsg) {
        S21SsmEZDResult ssmResult = NMAL2710Query.getInstance().getOrgCd(bizMsg.orgNm_DC.getValue());
        if (ssmResult.isCodeNormal()) {
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt >= 2) {
                bizMsg.orgNm_DC.setErrorInfo(1, NMAM8489E, new String[] {MOVE_POST_CD_TO});
                bizMsg.setMessageInfo(NMAM8489E, new String[] {MOVE_POST_CD_TO});
                return false;
            } else {
                List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
                ZYPEZDItemValueSetter.setValue(bizMsg.orgCd_DC, (String) resultList.get(0).get("ORG_CD"));
                return true;
            }
        } else {
            // No result
            bizMsg.orgNm_DC.setErrorInfo(1, ZZZM9006E, new String[] {bizMsg.orgNm_DC.getValue()});
            bizMsg.setMessageInfo(ZZZM9006E, new String[] {bizMsg.orgNm_DC.getValue()});
            return false;
        }
    }

    /**
     * checkActiveForMoveFromRule
     * @param bizMsg NMAL2710CMsg
     * @param selectedRows List<Integer>
     * @param selectedOrgCd List<String>
     * @param selectedRulePk List<BigDecimal>
     * @param glblCmpyCd String
     * @return boolean
     */
    public static boolean checkActiveForMoveFromRule(NMAL2710CMsg bizMsg, List<Integer> selectedRows, List<String> selectedOrgCd, List<BigDecimal> selectedRulePk, String glblCmpyCd) {
        boolean resultFlg = true;

        S21SsmEZDResult ssmResult = NMAL2710Query.getInstance().getMoveFromEffctive(selectedOrgCd, selectedRulePk);

        if (ssmResult.isCodeNormal()) {
            List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();

            for (Map<String, Object> result : resultList) {
                String orgEffThruDt = (String) result.get("ORG_EFF_THRU_DT");
                String ruleEffThruDt = (String) result.get("TRTY_RULE_EFF_THRU_DT");
                String slsDt = ZYPDateUtil.getSalesDate(glblCmpyCd);
                if (!ZYPCommonFunc.hasValue(orgEffThruDt)) {
                    orgEffThruDt = HIGH_VAL_DT;
                }

                if (!ZYPCommonFunc.hasValue(ruleEffThruDt)) {
                    ruleEffThruDt = HIGH_VAL_DT;
                }

                // Territory Active Check
                boolean isErrTrty = false;
                if (ZYPCommonFunc.hasValue((String) result.get("ORG_EFF_FROM_DT"))
                        && ZYPDateUtil.compare((String) result.get("ORG_EFF_FROM_DT"), slsDt) > 0) {
                    isErrTrty = true;
                }

                if (ZYPDateUtil.compare(orgEffThruDt, slsDt) < 0) {
                    isErrTrty = true;
                }

                if (isErrTrty) {
                    bizMsg.A.no(getTargetRec(bizMsg, selectedRows, (BigDecimal) result.get("TRTY_RULE_PK"))).xxChkBox_A1.setErrorInfo(1, NMAM8497E, new String[] {SELCETED_TRTY});
                    bizMsg.setMessageInfo(NMAM8497E, new String[] {SELCETED_TRTY});
                    resultFlg = false;
                }

                // QC#11370 2016/07/04 Add start
                if (ZYPCommonFunc.hasValue(bizMsg.effFromDt_DC)) {
                    String moveEffFrom = ZYPDateUtil.addDays(bizMsg.effFromDt_DC.getValue(), -1);
                    if (ZYPDateUtil.compare(moveEffFrom, orgEffThruDt) > 0) {
                        // QC#11940 2016/07/20 Add start
                        // bizMsg.A.no(getTargetRec(bizMsg, selectedRows, (BigDecimal) result.get("TRTY_RULE_PK"))).xxChkBox_A1.setErrorInfo(1, NMAM8082E, new String[] {MOVE_EFF_FROM, MOVE_FROM_POST_EFF});
                        // bizMsg.setMessageInfo(NMAM8082E, new String[] {MOVE_EFF_FROM, MOVE_FROM_POST_EFF});
                        bizMsg.A.no(getTargetRec(bizMsg, selectedRows, (BigDecimal) result.get("TRTY_RULE_PK"))).xxChkBox_A1.setErrorInfo(1, NMAM8634E, new String[] {MOVE_EFF_FROM, MOVE_FROM_POST_EFF});
                        bizMsg.setMessageInfo(NMAM8634E, new String[] {MOVE_EFF_FROM, MOVE_FROM_POST_EFF});
                        // QC#11940 2016/07/20 Add end
                        resultFlg = false;
                    }
                }
                // QC#11370 2016/07/04 Add end

                // Rule Active Check
                boolean isErrRule = false;
                if (ZYPCommonFunc.hasValue((String) result.get("RULE_EFF_FROM_DT"))
                        && ZYPDateUtil.compare((String) result.get("RULE_EFF_FROM_DT"), slsDt) > 0) {
                    isErrRule = true;
                }

                if (ZYPDateUtil.compare(ruleEffThruDt, slsDt) < 0) {
                    isErrRule = true;
                }

                if (isErrRule) {
                    bizMsg.A.no(getTargetRec(bizMsg, selectedRows, (BigDecimal) result.get("TRTY_RULE_PK"))).xxChkBox_A1.setErrorInfo(1, NMAM8497E, new String[] {SELCETED_RULE});
                    bizMsg.setMessageInfo(NMAM8497E, new String[] {SELCETED_RULE});
                    resultFlg = false;
                }

                // QC#11319 2016/07/05 Add start
                if (ZYPCommonFunc.hasValue((String) result.get("RULE_EFF_FROM_DT"))
                        && ZYPCommonFunc.hasValue(bizMsg.effFromDt_DC)) {
                    String moveEffFrom = ZYPDateUtil.addDays(bizMsg.effFromDt_DC.getValue(), -1);
                    if (ZYPDateUtil.compare(moveEffFrom, (String) result.get("RULE_EFF_FROM_DT")) < 0) {
                        bizMsg.A.no(getTargetRec(bizMsg, selectedRows, (BigDecimal) result.get("TRTY_RULE_PK"))).xxChkBox_A1.setErrorInfo(1, NMAM8600E);
                        bizMsg.setMessageInfo(NMAM8600E);
                        resultFlg = false;
                    }
                }
                // QC#11319 2016/07/05 Add end

            }
        }

        return resultFlg;
    }

    /**
     * getTargetRec
     * @param bizMsg NMAL2710CMsg
     * @param selectedRows List<Integer>
     * @param targetPk BigDecimal
     * @return boolean
     */
    public static int getTargetRec(NMAL2710CMsg bizMsg, List<Integer> selectedRows, BigDecimal targetPk) {
        for (int idx : selectedRows) {
            if (targetPk.compareTo(bizMsg.A.no(idx).trtyRulePk_A1.getValue()) == 0) {
                return idx;
            }
        }

        return 0;
    }

    /**
     * checkLogicTypeIdentity
     * @param bizMsg NMAL2710CMsg
     * @param selectedOrgCds List<String>
     * @param selectedRulePk List<BigDecimal>
     * @param selectedRows List<Integer>
     * @return String[]
     */
    public static boolean checkLogicTypeIdentity(NMAL2710CMsg bizMsg, List<String> selectedOrgCds, List<BigDecimal> selectedRulePk, List<Integer> selectedRows) {
        String checkLogicTpCd = null;

        // Move To Territory Logic Type Check
        S21SsmEZDResult moveToSsmResult = NMAL2710Query.getInstance().getLogicTypeMoveTo(bizMsg.orgCd_DC.getValue(), bizMsg.effFromDt_DC.getValue(), bizMsg.effThruDt_DC.getValue());

        if (moveToSsmResult.isCodeNormal()) {
            List<Map<String, Object>> moveToResultList = (List<Map<String, Object>>) moveToSsmResult.getResultObject();

            for (Map<String, Object> checkMoveToResult : moveToResultList) {
                if (!ZYPCommonFunc.hasValue(checkLogicTpCd)) {
                    checkLogicTpCd = (String) checkMoveToResult.get("TRTY_RULE_LOGIC_TP_CD");
                    continue;
                }

                if (!checkLogicTpCd.equals((String) checkMoveToResult.get("TRTY_RULE_LOGIC_TP_CD"))) {
                    bizMsg.orgNm_DC.setErrorInfo(1, NMAM0086E, new String[] {TRTY_LOGIC_TYPE, BETWEEN_SAME});
                    bizMsg.setMessageInfo(NMAM0086E, new String[] {TRTY_LOGIC_TYPE, BETWEEN_SAME});
                    return false;
                }
            }
        }

        // Move From Territory Logic Type Check
        boolean isErrMoveFrom = false;
        for (int i : selectedRows) {
            if (!ZYPCommonFunc.hasValue(checkLogicTpCd)) {
                checkLogicTpCd = bizMsg.A.no(i).trtyRuleLogicTpCd_A1.getValue();
                continue;
            }

            if (!checkLogicTpCd.equals(bizMsg.A.no(i).trtyRuleLogicTpCd_A1.getValue())) {
                isErrMoveFrom = true;
            }
        }
        if (isErrMoveFrom) {
            for (int i : selectedRows) {
                bizMsg.A.no(i).xxChkBox_A1.setErrorInfo(1, NMAM0086E, new String[] {TRTY_LOGIC_TYPE, BETWEEN_SAME});
                bizMsg.setMessageInfo(NMAM0086E, new String[] {TRTY_LOGIC_TYPE, BETWEEN_SAME});
            }
            return false;
        }

        return true;
    }

    /**
     * checkEffectiveRange
     * @param bizMsg NMAL2710CMsg
     * @return boolean
     */
    public static boolean checkEffectiveRange(NMAL2710CMsg bizMsg) {
        S21SsmEZDResult ssmResult = NMAL2710Query.getInstance().getEffectiveRange(bizMsg.orgCd_DC.getValue(), bizMsg.effFromDt_DC.getValue(), bizMsg.effThruDt_DC.getValue());
        if (ssmResult.isCodeNormal()) {
            return true;
        } else {
            // QC#11940 2016/07/20 Mod start
            // // QC#11091 2016/06/29 Mod start
            // // bizMsg.effFromDt_DC.setErrorInfo(1, NMAM0086E, new
            // String[] {TRTY_RULE_EFF, OUT_OF_EFF});
            // // bizMsg.effThruDt_DC.setErrorInfo(1, NMAM0086E, new
            // String[] {TRTY_RULE_EFF, OUT_OF_EFF});
            // // bizMsg.setMessageInfo(NMAM0086E, new String[]
            // {TRTY_RULE_EFF, OUT_OF_EFF});
            // bizMsg.effFromDt_DC.setErrorInfo(1, NMAM8082E, new
            // String[] {MOVE_EFF, MOVE_POST_EFF});
            // bizMsg.effThruDt_DC.setErrorInfo(1, NMAM8082E, new
            // String[] {MOVE_EFF, MOVE_POST_EFF});
            // bizMsg.setMessageInfo(NMAM8082E, new String[]
            // {MOVE_EFF, MOVE_POST_EFF});
            // // QC#11091 2016/06/29 Mod end
            // mod start 2016/10/19 CSA QC#11940
            bizMsg.orgNm_DC.setErrorInfo(1, NMAM8634E, new String[] {MOVE_EFF, MOVE_POST_EFF });
            // mod end 2016/10/19 CSA QC#11940
            bizMsg.effFromDt_DC.setErrorInfo(1, NMAM8634E, new String[] {MOVE_EFF, MOVE_POST_EFF });
            bizMsg.effThruDt_DC.setErrorInfo(1, NMAM8634E, new String[] {MOVE_EFF, MOVE_POST_EFF });
            bizMsg.setMessageInfo(NMAM8634E, new String[] {MOVE_EFF, MOVE_POST_EFF });
            // QC#11940 2016/07/20 Mod end
            return false;
        }
    }

    /**
     * checkCsvHeader
     * @param csvHeader String[]
     * @return boolean
     */
    public static boolean checkCsvHeader(String[] csvHeader) {
        if (csvHeader.length == CSV_HEADER_NUM) {
            for (int i = 0; i < csvHeader.length; i++) {
                if (!ZYPCommonFunc.hasValue(csvHeader[i])) {
                    return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }

    /**
     * formatDt
     * @param dt String
     * @return String formated
     */
    public static String formatDt(String dt) {

        if (!ZYPCommonFunc.hasValue(dt)) {
            return "";
        } else if (dt.length() > YYYYMMDD_LENGTH) {
            dt = dt.substring(0, YYYYMMDD_LENGTH);
        }

        return ZYPDateUtil.formatEzd8ToDisp(dt, true);
    }

    /**
     * Clear Detail Control
     * @param bizMsg Business Msg
     */
    public static void clearDetailControl(NMAL2710CMsg bizMsg) {
        bizMsg.orgNm_DC.clear();
        bizMsg.orgCd_DC.clear();
        bizMsg.effFromDt_DC.clear();
        bizMsg.effThruDt_DC.clear();
        bizMsg.rqstRsltCmntTxt_DC.clear();
    }
}
