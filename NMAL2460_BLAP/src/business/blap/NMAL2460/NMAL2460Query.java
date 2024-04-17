/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NMAL2460;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ASG_TRTY_ATTRB;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ACCT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.GNRN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORG_STRU_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRTY_RULE_LOGIC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRTY_RULE_OPRD_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRTY_RULE_TP;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Account Owner Lookup
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/27   Hitachi         O.Okuma         Create          N/A
 * 2016/09/12   SRAA            Y.Chen          Update          QC#10569
 * 2016/09/13   SRAA            Y.Chen          Update          QC#14430
 * 2017/09/13   Fujitsu         W.Honda         Update          QC#21044
 * 2017/09/21   Fujitsu         W.Honda         Update          QC#20586
 * 2017/11/28   Fujitsu         Hd.Sugawara     Update          QC#21044
 *</pre>
 */
public final class NMAL2460Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NMAL2460Query INSTANCE = new NMAL2460Query();

    /**
     * Constructor.
     */
    private NMAL2460Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NMAL2460Query
     */
    public static NMAL2460Query getInstance() {
        return INSTANCE;
    }

    /**
     * setSearchParam
     * @param cMsg NMAL2460CMsg
     * @param glblCmpyCd String
     * @return  Map<String, Object>
     */
    // Mod Start 2017/09/21 QC#20586
//    public static Map<String, Object> setSearchParam(NMAL2460CMsg cMsg, String glblCmpyCd) {
    public static Map<String, Object> setSearchParam(NMAL2460CMsg cMsg, String glblCmpyCd, int MaxRowCnt) {
        Map<String, Object> params = new HashMap<String, Object>();
        // set ssm parameters.
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("cMsg", cMsg);
        params.put("slsDt", ZYPDateUtil.getSalesDate());
        params.put("maxDt", "99991231");
        params.put("gnrnTp_Current", GNRN_TP.CURRENT);
        params.put("flgOnY", ZYPConstant.FLG_ON_Y);
        params.put("flgOffN", ZYPConstant.FLG_OFF_N);
        params.put("orgStruTp_TS", ORG_STRU_TP.TERRITORY_STRUCTURE);
        params.put("rgtnSts_Ready4Order", RGTN_STS.READY_FOR_ORDER_TAKING);
//        params.put("rowNum_Max", 5001);
        params.put("rowNum_Max", MaxRowCnt);
        // Mod End 2017/09/21 QC#20586

        if (ZYPCommonFunc.hasValue(cMsg.orgNm_H2)) {
            params.put("orgNm", cMsg.orgNm_H2.getValue());
        }

        if (ZYPCommonFunc.hasValue(cMsg.postCd_HF) || ZYPCommonFunc.hasValue(cMsg.postCd_HT)) {
            params.put("postCd_FiveDigit", 5);
            params.put("postCd_FullDigit", 10);
            params.put("postCd_NineDigit", 9);
            params.put("zero", "0");
            params.put("hyphen", "-");
        }
        if (ZYPCommonFunc.hasValue(cMsg.postCd_HF)) {
            params.put("postCdFromVal", getFullDigitPostalCode(cMsg.postCd_HF.getValue(), true));
        }
        if (ZYPCommonFunc.hasValue(cMsg.postCd_HT)) {
            params.put("postCdThruVal", getFullDigitPostalCode(cMsg.postCd_HT.getValue(), false));
        }

        if (DS_ACCT_TP.CUSTOMER.equals(cMsg.dsAcctTpCd_H.getValue())) {
            params.put("dsAcctTp", DS_ACCT_TP.CUSTOMER);
        } else {
            params.put("dsAcctTp", DS_ACCT_TP.PROSPECT);
        }

        return params;
    }

    /**
     * Get Full Digits of Postal Code
     * @param postalCode String
     * @return String
     */
    public static String getFullDigitPostalCode(String postalCode, boolean isFromPost) {
        postalCode = postalCode.replace("-", "");

        if (postalCode.endsWith("%")) {
            postalCode = postalCode.replace("%", "");
        }

        for (int i = postalCode.length(); i < 9; i++) {
            if (isFromPost) {
                postalCode = postalCode + "0";
            } else {
                postalCode = postalCode + "9";
            }
        }

        return postalCode;
    }

    /**
     * getSearchLayer1
     * @param cMsg NMAL2460CMsg
     * @param sMsg NMAL2460SMsg
     * @param glblCmpyCd String
     * @return S21SsmEZDResult
     */
        // Mod Start 2017/09/21 QC#20586
    public S21SsmEZDResult getSearchLayer1(NMAL2460CMsg cMsg, NMAL2460SMsg sMsg, String glblCmpyCd) {
        Map<String, Object> params = new HashMap<String, Object>();
//        params = setSearchParam(cMsg, glblCmpyCd);
        params = setSearchParam(cMsg, glblCmpyCd, sMsg.A.length() + 1);
        // Mod End 2017/09/21 QC#20586

        return getSsmEZDClient().queryEZDMsgArray("getSearchLayer1", params, sMsg.A);
    }

    /**
     * getSearchLayer2
     * @param cMsg NMAL2460CMsg
     * @param glblCmpyCd String
     * @return  Map<String, Object>
     */
    public static Map<String, Object> setSearchParamForLayout2(NMAL2460CMsg cMsg, String glblCmpyCd, List<Map<String, Object>> lineBizRoleTpList, int MaxRowCnt) {
        Map<String, Object> params = new HashMap<String, Object>();
        // set ssm parameters.
        params = setSearchParam(cMsg, glblCmpyCd, MaxRowCnt);

        params.put("rowNum_1", 1);
        params.put("rowNum_2", 2);
        params.put("rowNum_3", 3);
        params.put("rowNum_4", 4);
        params.put("rowNum_5", 5);

// QC#10569
//        if (ZYPCommonFunc.hasValue(cMsg.fullPsnNm_H) || ZYPCommonFunc.hasValue(cMsg.psnCd_H)) {
//            params.put("employeeFlg", ZYPConstant.FLG_ON_Y);
//        }
        if (ZYPCommonFunc.hasValue(cMsg.orgNm_H1) 
                || ZYPCommonFunc.hasValue(cMsg.trtyGrpTpCd_H)
                || ZYPCommonFunc.hasValue(cMsg.fullPsnNm_H)
                || ZYPCommonFunc.hasValue(cMsg.psnCd_H)
                || ZYPCommonFunc.hasValue(cMsg.orgNm_H2)
                // QC#14430
                || ZYPCommonFunc.hasValue(cMsg.bizAreaOrgCd_H)
                ) {
            params.put("orgSrchPrmExstFlg", ZYPConstant.FLG_ON_Y);
        }

        List<String> lineBizRoleList = new ArrayList<String>();
        for (Map<String, Object> lineBizRoleTpMap : lineBizRoleTpList){
            if (ASG_TRTY_ATTRB.ATTRIBUTE1_ID.equals((String) lineBizRoleTpMap.get("ASG_TRTY_ATTRB_CD"))) {
                params.put("lineBizRoleTp_01", (String) lineBizRoleTpMap.get("LINE_BIZ_ROLE_TP_CD"));
                lineBizRoleList.add((String) lineBizRoleTpMap.get("LINE_BIZ_ROLE_TP_CD"));
                continue;
            } else if (ASG_TRTY_ATTRB.ATTRIBUTE2_ID.equals((String) lineBizRoleTpMap.get("ASG_TRTY_ATTRB_CD"))) {
                params.put("lineBizRoleTp_02", (String) lineBizRoleTpMap.get("LINE_BIZ_ROLE_TP_CD"));
                lineBizRoleList.add((String) lineBizRoleTpMap.get("LINE_BIZ_ROLE_TP_CD"));
                continue;
            } else if (ASG_TRTY_ATTRB.ATTRIBUTE3_ID.equals((String) lineBizRoleTpMap.get("ASG_TRTY_ATTRB_CD"))) {
                params.put("lineBizRoleTp_03", (String) lineBizRoleTpMap.get("LINE_BIZ_ROLE_TP_CD"));
                lineBizRoleList.add((String) lineBizRoleTpMap.get("LINE_BIZ_ROLE_TP_CD"));
                continue;
            } else if (ASG_TRTY_ATTRB.ATTRIBUTE4_ID.equals((String) lineBizRoleTpMap.get("ASG_TRTY_ATTRB_CD"))) {
                params.put("lineBizRoleTp_04", (String) lineBizRoleTpMap.get("LINE_BIZ_ROLE_TP_CD"));
                lineBizRoleList.add((String) lineBizRoleTpMap.get("LINE_BIZ_ROLE_TP_CD"));
                continue;
            } else if (ASG_TRTY_ATTRB.ATTRIBUTE5_ID.equals((String) lineBizRoleTpMap.get("ASG_TRTY_ATTRB_CD"))) {
                params.put("lineBizRoleTp_05", (String) lineBizRoleTpMap.get("LINE_BIZ_ROLE_TP_CD"));
                lineBizRoleList.add((String) lineBizRoleTpMap.get("LINE_BIZ_ROLE_TP_CD"));
                continue;
            } else if (ASG_TRTY_ATTRB.ATTRIBUTE6_ID.equals((String) lineBizRoleTpMap.get("ASG_TRTY_ATTRB_CD"))) {
                params.put("lineBizRoleTp_06", (String) lineBizRoleTpMap.get("LINE_BIZ_ROLE_TP_CD"));
                lineBizRoleList.add((String) lineBizRoleTpMap.get("LINE_BIZ_ROLE_TP_CD"));
                continue;
            } else if (ASG_TRTY_ATTRB.ATTRIBUTE7_ID.equals((String) lineBizRoleTpMap.get("ASG_TRTY_ATTRB_CD"))) {
                params.put("lineBizRoleTp_07", (String) lineBizRoleTpMap.get("LINE_BIZ_ROLE_TP_CD"));
                lineBizRoleList.add((String) lineBizRoleTpMap.get("LINE_BIZ_ROLE_TP_CD"));
                continue;
            } else if (ASG_TRTY_ATTRB.ATTRIBUTE8_ID.equals((String) lineBizRoleTpMap.get("ASG_TRTY_ATTRB_CD"))) {
                params.put("lineBizRoleTp_08", (String) lineBizRoleTpMap.get("LINE_BIZ_ROLE_TP_CD"));
                lineBizRoleList.add((String) lineBizRoleTpMap.get("LINE_BIZ_ROLE_TP_CD"));
                continue;
            } else if (ASG_TRTY_ATTRB.ATTRIBUTE9_ID.equals((String) lineBizRoleTpMap.get("ASG_TRTY_ATTRB_CD"))) {
                params.put("lineBizRoleTp_09", (String) lineBizRoleTpMap.get("LINE_BIZ_ROLE_TP_CD"));
                lineBizRoleList.add((String) lineBizRoleTpMap.get("LINE_BIZ_ROLE_TP_CD"));
                continue;
            } else if (ASG_TRTY_ATTRB.ATTRIBUTE10_ID.equals((String) lineBizRoleTpMap.get("ASG_TRTY_ATTRB_CD"))) {
                params.put("lineBizRoleTp_10", (String) lineBizRoleTpMap.get("LINE_BIZ_ROLE_TP_CD"));
                lineBizRoleList.add((String) lineBizRoleTpMap.get("LINE_BIZ_ROLE_TP_CD"));
                continue;
            } else if (ASG_TRTY_ATTRB.ATTRIBUTE11_ID.equals((String) lineBizRoleTpMap.get("ASG_TRTY_ATTRB_CD"))) {
                params.put("lineBizRoleTp_11", (String) lineBizRoleTpMap.get("LINE_BIZ_ROLE_TP_CD"));
                lineBizRoleList.add((String) lineBizRoleTpMap.get("LINE_BIZ_ROLE_TP_CD"));
                continue;
            } else if (ASG_TRTY_ATTRB.ATTRIBUTE12_ID.equals((String) lineBizRoleTpMap.get("ASG_TRTY_ATTRB_CD"))) {
                params.put("lineBizRoleTp_12", (String) lineBizRoleTpMap.get("LINE_BIZ_ROLE_TP_CD"));
                lineBizRoleList.add((String) lineBizRoleTpMap.get("LINE_BIZ_ROLE_TP_CD"));
                continue;
            } else if (ASG_TRTY_ATTRB.ATTRIBUTE13_ID.equals((String) lineBizRoleTpMap.get("ASG_TRTY_ATTRB_CD"))) {
                params.put("lineBizRoleTp_13", (String) lineBizRoleTpMap.get("LINE_BIZ_ROLE_TP_CD"));
                lineBizRoleList.add((String) lineBizRoleTpMap.get("LINE_BIZ_ROLE_TP_CD"));
                continue;
            } else if (ASG_TRTY_ATTRB.ATTRIBUTE14_ID.equals((String) lineBizRoleTpMap.get("ASG_TRTY_ATTRB_CD"))) {
                params.put("lineBizRoleTp_14", (String) lineBizRoleTpMap.get("LINE_BIZ_ROLE_TP_CD"));
                lineBizRoleList.add((String) lineBizRoleTpMap.get("LINE_BIZ_ROLE_TP_CD"));
                continue;
            } else if (ASG_TRTY_ATTRB.ATTRIBUTE15_ID.equals((String) lineBizRoleTpMap.get("ASG_TRTY_ATTRB_CD"))) {
                params.put("lineBizRoleTp_15", (String) lineBizRoleTpMap.get("LINE_BIZ_ROLE_TP_CD"));
                lineBizRoleList.add((String) lineBizRoleTpMap.get("LINE_BIZ_ROLE_TP_CD"));
                continue;
            } else if (ASG_TRTY_ATTRB.ATTRIBUTE16_ID.equals((String) lineBizRoleTpMap.get("ASG_TRTY_ATTRB_CD"))) {
                params.put("lineBizRoleTp_16", (String) lineBizRoleTpMap.get("LINE_BIZ_ROLE_TP_CD"));
                lineBizRoleList.add((String) lineBizRoleTpMap.get("LINE_BIZ_ROLE_TP_CD"));
                continue;
            } else if (ASG_TRTY_ATTRB.ATTRIBUTE17_ID.equals((String) lineBizRoleTpMap.get("ASG_TRTY_ATTRB_CD"))) {
                params.put("lineBizRoleTp_17", (String) lineBizRoleTpMap.get("LINE_BIZ_ROLE_TP_CD"));
                lineBizRoleList.add((String) lineBizRoleTpMap.get("LINE_BIZ_ROLE_TP_CD"));
                continue;
            } else if (ASG_TRTY_ATTRB.ATTRIBUTE18_ID.equals((String) lineBizRoleTpMap.get("ASG_TRTY_ATTRB_CD"))) {
                params.put("lineBizRoleTp_18", (String) lineBizRoleTpMap.get("LINE_BIZ_ROLE_TP_CD"));
                lineBizRoleList.add((String) lineBizRoleTpMap.get("LINE_BIZ_ROLE_TP_CD"));
                continue;
            } else if (ASG_TRTY_ATTRB.ATTRIBUTE19_ID.equals((String) lineBizRoleTpMap.get("ASG_TRTY_ATTRB_CD"))) {
                params.put("lineBizRoleTp_19", (String) lineBizRoleTpMap.get("LINE_BIZ_ROLE_TP_CD"));
                lineBizRoleList.add((String) lineBizRoleTpMap.get("LINE_BIZ_ROLE_TP_CD"));
                continue;
            } else if (ASG_TRTY_ATTRB.ATTRIBUTE20_ID.equals((String) lineBizRoleTpMap.get("ASG_TRTY_ATTRB_CD"))) {
                params.put("lineBizRoleTp_20", (String) lineBizRoleTpMap.get("LINE_BIZ_ROLE_TP_CD"));
                lineBizRoleList.add((String) lineBizRoleTpMap.get("LINE_BIZ_ROLE_TP_CD"));
            }
        }
        return params;
    }

    /**
     * getSearchLayer2
     * @param cMsg NMAL2460CMsg
     * @param sMsg NMAL2460SMsg
     * @param glblCmpyCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSearchLayer2(NMAL2460CMsg cMsg, NMAL2460SMsg sMsg, String glblCmpyCd, List<Map<String, Object>> lineBizRoleTpList) {
        Map<String, Object> params = new HashMap<String, Object>();
        // Add Start 2017/09/21 QC#20586
//        params = setSearchParamForLayout2(cMsg, glblCmpyCd, lineBizRoleTpList);
        params = setSearchParamForLayout2(cMsg, glblCmpyCd, lineBizRoleTpList, sMsg.B.length() + 1);
        // Add End 2017/09/21 QC#20586

        return getSsmEZDClient().queryEZDMsgArray("getSearchLayer2", params, sMsg.B);
    }

    /**
     * getLineBizRoleTp
     * @param orgNm String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getLineBizRoleTp() {
        Map<String, Object> params = new HashMap<String, Object>();
        // set ssm parameters.
        params.put("glblCmpyCd", getGlobalCompanyCode());

        return getSsmEZDClient().queryObjectList("getLineBizRoleTp", params);
    }

    // Mod Start 2017/11/28 QC#21044
    ///**
    // * getTerritoryDetail
    // * @param orgNm String
    // * @return S21SsmEZDResult
    // */
    //public S21SsmEZDResult getTerritoryDetail(String orgNm) {
    /**
     * getTerritoryDetail
     * @param orgNm String
     * @param nonSlsRepFlg String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getTerritoryDetail(String orgNm, String nonSlsRepFlg) {
        // Mod End 2017/11/28 QC#21044
        Map<String, Object> params = new HashMap<String, Object>();
        // set ssm parameters.
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("trtyName", orgNm);
        params.put("slsDt", ZYPDateUtil.getSalesDate());
        params.put("gnrnTp_Current", GNRN_TP.CURRENT);
        params.put("gnrnTp_Future", GNRN_TP.FUTURE);
        params.put("flgOffN", ZYPConstant.FLG_OFF_N);
        params.put("flgOnY", ZYPConstant.FLG_ON_Y);
        params.put("maxDt", "99991231");
        // Add Start 2017/11/28 QC#21044
        if (ZYPCommonFunc.hasValue(nonSlsRepFlg)) {
            params.put("nonSlsRepFlg", nonSlsRepFlg);
        }
        // Add End 2017/11/28 QC#21044

        return getSsmEZDClient().queryObjectList("getTerritoryDetail", params);
    }

    /**
     * checkActiveTerritory
     * @param orgNm String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult checkActiveTerritory(String orgNm) {
        Map<String, Object> params = new HashMap<String, Object>();
        // set ssm parameters.
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("trtyNm", orgNm);
        params.put("slsDt", ZYPDateUtil.getSalesDate());
        params.put("gnrnTp_Current", GNRN_TP.CURRENT);
        params.put("flgOnY", ZYPConstant.FLG_ON_Y);
        params.put("maxDt", "99991231");

        return getSsmEZDClient().queryObject("checkActiveTerritory", params);
    }

    /**
     * getTitleLabel
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getTitleLabel(String dsAcctTp) {
        Map<String, Object> params = new HashMap<String, Object>();
        // set ssm parameters.
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("dsAcctTp", dsAcctTp);

        return getSsmEZDClient().queryObjectList("getTitleLabel", params);
    }

    /**
     * getLineBizRankNum
     * @param asgTrtyAttrb String
     * @param dsAcctTp String
     * @param trtyGrpTp String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getLineBizRankNum(String asgTrtyAttrb, String dsAcctTp, String trtyGrpTp) {
        Map<String, Object> params = new HashMap<String, Object>();
        // set ssm parameters.
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("asgTrtyAttrb", asgTrtyAttrb);
        params.put("dsAcctTp", dsAcctTp);
        params.put("trtyGrpTp", trtyGrpTp);

        return getSsmEZDClient().queryObject("getLineBizRankNum", params);
    }

    /**
     * checkActiveResorce
     * @param trtyNm String
     * @param isSlsRep boolean
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult checkActiveResorce(String trtyNm, boolean isSlsRep) {
        Map<String, Object> params = new HashMap<String, Object>();
        // set ssm parameters.
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("trtyNm", trtyNm);
        params.put("flgOnY", ZYPConstant.FLG_ON_Y);
        params.put("slsDt", ZYPDateUtil.getSalesDate());
        params.put("maxDt", "99991231");
        params.put("gnrnTp_Current", GNRN_TP.CURRENT);
        if (isSlsRep) {
            params.put("salesRepRoleFlg", ZYPConstant.FLG_ON_Y);
        }

        return getSsmEZDClient().queryObject("checkActiveResorce", params);
    }

    // QC#12193
    /**
     * @param dsOrgNm String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getOrgTier(String dsOrgNm) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("dsOrgNm", dsOrgNm);
        return getSsmEZDClient().queryObjectList("getOrgTier", params);
    }

    // QC#7966
    /**
     * getTerritoryRules
     * @param cMsg NMAL2610CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getTerritoryRules(NMAL2460_BSMsg bsMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("cMsg", bsMsg);
        ssmParam.put("trtyStruFlg", ZYPConstant.FLG_ON_Y);
        ssmParam.put("slsDt", ZYPDateUtil.getSalesDate());
        ssmParam.put("maxeffThruDt", "99991231");
        return getSsmEZDClient().queryObjectList("getTerritoryRules", ssmParam);
    }

    // QC#21044
    /**
     * getTerritoryRules
     * @param cMsg NMAL2610CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getTrtyRule(String orgCd, String locNum) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("orgCd", orgCd);
        ssmParam.put("gnrnTpCd", GNRN_TP.CURRENT);
        ssmParam.put("ruleTpCd", TRTY_RULE_TP.LOCATION_NUMBER);
        ssmParam.put("fromValTxt", locNum);
        ssmParam.put("oprdTpCd", TRTY_RULE_OPRD_TP.EQUAL);
        ssmParam.put("logicTpCd", TRTY_RULE_LOGIC_TP.OR);
        ssmParam.put("slsDt", ZYPDateUtil.getSalesDate());
        ssmParam.put("maxDt", "99991231");

        return getSsmEZDClient().queryObject("getTrtyRule", ssmParam);
    }

    // QC#21044
    /**
     * existsTerritoryRule
     * @param cMsg NMAL2610CMsg
     * @return boolean
     */
    public boolean existsTerritoryRule(String locNum, String orgCd, String orgStruTpCd) {
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("trtyRuleTpCd", TRTY_RULE_TP.LOCATION_NUMBER);
        ssmParam.put("orgStruTpCd", orgStruTpCd);
        ssmParam.put("slsDt", ZYPDateUtil.getSalesDate());
        ssmParam.put("trtyRuleFromValTxt", locNum);
        ssmParam.put("orgCd", orgCd);
        S21SsmEZDResult result = getSsmEZDClient().queryObject("getCountTerritoryRule", ssmParam);
        Integer cnt = (Integer) result.getResultObject();
        if (cnt == null) {
            return false;
        }
        return cnt > 0;
    }

    // QC#21044
    /**
     * isRuleLogicAllOr
     * @param cMsg NMAL2610CMsg
     * @return boolean
     */
    public boolean isRuleLogicAllOr(String orgCd, String orgStruTpCd) {
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("orgCd", orgCd);
        ssmParam.put("orgStruTpCd", orgStruTpCd);
        ssmParam.put("slsDt", ZYPDateUtil.getSalesDate());
        ssmParam.put("trtyRuleTpCd", TRTY_RULE_TP.LOCATION_NUMBER);
        ssmParam.put("trtyRuleLogicTpCd", TRTY_RULE_LOGIC_TP.OR);
        S21SsmEZDResult result = getSsmEZDClient().queryObject("getCountTerritoryRuleLogicNotOR", ssmParam);
        Integer cnt = (Integer) result.getResultObject();
        if (cnt == null) {
            return false;
        }
        return cnt > 0;
    }
}
