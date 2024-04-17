/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL2710;

import static business.blap.NMAL2710.constant.NMAL2710Constant.FROM_POST_PAD_STR;
import static business.blap.NMAL2710.constant.NMAL2710Constant.HIGH_VAL_DT;
import static business.blap.NMAL2710.constant.NMAL2710Constant.HYPHEN_REPLACE_TARGET_CHAR;
import static business.blap.NMAL2710.constant.NMAL2710Constant.PERCENT_REPLACE_TARGET_CHAR;
import static business.blap.NMAL2710.constant.NMAL2710Constant.POSTAL_LENGTH;
import static business.blap.NMAL2710.constant.NMAL2710Constant.REPLACE_CHAR_BLANK;
import static business.blap.NMAL2710.constant.NMAL2710Constant.THRU_POST_PAD_STR;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.GNRN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRTY_RULE_TP;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * NMAL2710Query
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/16   Fujitsu         W.Honda         Create          N/A
 * 2016/08/02   Fujitsu         C.Yokoi         Update          QC#11004
 *</pre>
 */
public final class NMAL2710Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NMAL2710Query MY_INSTANCE = new NMAL2710Query();

    /**
     * Private constructor
     */
    private NMAL2710Query() {
        super();
    }

    /**
     * Get the NMAL2710Query instance.
     * @return NMAL2710Query instance
     */
    public static NMAL2710Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * getTrtyRuleForPostal
     * @param bizMsg NMAL2710CMsg
     * @param isDownload boolean
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getTrtyRuleForPostal(NMAL2710CMsg bizMsg, boolean isDownload) {
        Map<String, Object> params = createParamsTrtyRuleForPostal(bizMsg, isDownload);

        return getSsmEZDClient().queryObjectList("getTrtyRuleForPostal", params);
    }

    /**
     * createParamsTrtyRuleForPostal
     * @param bizMsg NMAL2710CMsg
     * @param isDownload boolean
     * @return S21SsmEZDResult
     */
    public Map<String, Object> createParamsTrtyRuleForPostal(NMAL2710CMsg bizMsg, boolean isDownload) {
        Map<String, Object> params = new HashMap<String, Object>();

        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("orgNm", bizMsg.orgNm_H.getValue());
        params.put("trtyStruFlgY", ZYPConstant.FLG_ON_Y);
        params.put("bizAreaOrgCd", bizMsg.bizAreaOrgCd_H.getValue());
        params.put("trtyRuleTpPost", TRTY_RULE_TP.POSTAL_CODE);
        params.put("trtyGrpTpCd", bizMsg.trtyGrpTpCd_H.getValue());
        String trtyRuleFromValTxt = null;
        if (ZYPCommonFunc.hasValue(bizMsg.trtyRuleFromValTxt_H)) {
            trtyRuleFromValTxt = bizMsg.trtyRuleFromValTxt_H.getValue();
            trtyRuleFromValTxt = trtyRuleFromValTxt.replace(PERCENT_REPLACE_TARGET_CHAR, REPLACE_CHAR_BLANK);
            trtyRuleFromValTxt = trtyRuleFromValTxt.replace(HYPHEN_REPLACE_TARGET_CHAR, REPLACE_CHAR_BLANK);
            trtyRuleFromValTxt = ZYPCommonFunc.rightPad(trtyRuleFromValTxt, POSTAL_LENGTH, FROM_POST_PAD_STR);
        }
        params.put("trtyRuleFromValTxt", trtyRuleFromValTxt);
        String trtyRuleThruValTxt = null;
        if (ZYPCommonFunc.hasValue(bizMsg.trtyRuleThruValTxt_H)) {
            trtyRuleThruValTxt = bizMsg.trtyRuleThruValTxt_H.getValue();
            trtyRuleThruValTxt = trtyRuleThruValTxt.replace(PERCENT_REPLACE_TARGET_CHAR, REPLACE_CHAR_BLANK);
            trtyRuleThruValTxt = trtyRuleThruValTxt.replace(HYPHEN_REPLACE_TARGET_CHAR, REPLACE_CHAR_BLANK);
            trtyRuleThruValTxt = ZYPCommonFunc.rightPad(trtyRuleThruValTxt, POSTAL_LENGTH, THRU_POST_PAD_STR);
        }
        params.put("trtyRuleThruValTxt", trtyRuleThruValTxt);
        if (!isDownload) {
            params.put("rowNum", bizMsg.A.length() + 1);
        }
        params.put("salesDate", ZYPDateUtil.getSalesDate(getGlobalCompanyCode()));

        return params;
    }

    /**
     * getOrgCd
     * @param orgNm String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getOrgCd(String orgNm) {
        Map<String, Object> params = new HashMap<String, Object>();

        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("orgNm", orgNm);
        params.put("trtyStruFlgY", ZYPConstant.FLG_ON_Y);

        return getSsmEZDClient().queryObjectList("getOrgCd", params);
    }

    /**
     * getMoveFromEffctive
     * @param orgCds List<String>
     * @param rulePks List<BigDecimal>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getMoveFromEffctive(List<String> orgCds, List<BigDecimal> rulePks) {
        Map<String, Object> params = new HashMap<String, Object>();

        params.put("glblCmpyCd", getGlobalCompanyCode());
        String[] orgCdList = orgCds.toArray(new String[orgCds.size()]);
        params.put("orgCdList", orgCdList);
        params.put("gnrnTpCdList", new String[] {GNRN_TP.CURRENT, GNRN_TP.FUTURE});
        BigDecimal[] rulePkList = rulePks.toArray(new BigDecimal[rulePks.size()]);
        params.put("rulePkList", rulePkList);
        params.put("trtyStruFlgY", ZYPConstant.FLG_ON_Y);
        params.put("trtyRuleTpPost", TRTY_RULE_TP.POSTAL_CODE);

        return getSsmEZDClient().queryObjectList("getMoveFromEffctive", params);
    }

    /**
     * getLogicTypeMoveFrom
     * @param bizMsg NMAL2710CMsg
     * @param orgCds List<String>
     * @param rulePks List<BigDecimal>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getLogicTypeMoveFrom(NMAL2710CMsg bizMsg, List<String> orgCds, List<BigDecimal> rulePks) {
        Map<String, Object> params = new HashMap<String, Object>();

        params.put("glblCmpyCd", getGlobalCompanyCode());
        String[] orgCdList = orgCds.toArray(new String[orgCds.size()]);
        params.put("orgCdList", orgCdList);
        BigDecimal[] rulePkList = rulePks.toArray(new BigDecimal[rulePks.size()]);
        params.put("rulePkList", rulePkList);
        params.put("trtyStruFlgY", ZYPConstant.FLG_ON_Y);
        params.put("trtyRuleTpPost", TRTY_RULE_TP.POSTAL_CODE);
        params.put("effFromDt", bizMsg.effFromDt_DC.getValue());
        String effThruDt = bizMsg.effThruDt_DC.getValue();
        if (!ZYPCommonFunc.hasValue(effThruDt)) {
            effThruDt = HIGH_VAL_DT;
        }
        params.put("effThruDt", effThruDt);

        return getSsmEZDClient().queryObjectList("getLogicTypeMoveFrom", params);
    }

    /**
     * getLogicTypeMoveTo
     * @param orgCd String
     * @param effFromDt String
     * @param effThruDt String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getLogicTypeMoveTo(String orgCd, String effFromDt, String effThruDt) {
        Map<String, Object> params = new HashMap<String, Object>();

        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("orgCd", orgCd);
        params.put("trtyStruFlgY", ZYPConstant.FLG_ON_Y);
        params.put("trtyRuleTpPost", TRTY_RULE_TP.POSTAL_CODE);
        params.put("effFromDt", effFromDt);
        if (!ZYPCommonFunc.hasValue(effThruDt)) {
            effThruDt = HIGH_VAL_DT;
        }
        params.put("effThruDt", effThruDt);

        return getSsmEZDClient().queryObjectList("getLogicTypeMoveTo", params);
    }

    /**
     * getEffectiveRange
     * @param orgCd String
     * @param effFromDt String
     * @param effThruDt String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getEffectiveRange(String orgCd, String effFromDt, String effThruDt) {
        Map<String, Object> params = new HashMap<String, Object>();

        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("orgCd", orgCd);
        params.put("trtyStruFlgY", ZYPConstant.FLG_ON_Y);
        params.put("effFromDt", effFromDt);
        if (!ZYPCommonFunc.hasValue(effThruDt)) {
            effThruDt = HIGH_VAL_DT;
        }
        params.put("effThruDt", effThruDt);

        return getSsmEZDClient().queryObjectList("getEffectiveRange", params);
    }

    /**
     * getTerritoryRules
     * @param orgCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getTerritoryRules(String orgCd) {
        // 2016/08/02 CSA-QC#11004 Add Start
        Map<String, Object> params = new HashMap<String, Object>();

        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("orgCd", orgCd);
        params.put("flgOnY", ZYPConstant.FLG_ON_Y);
        params.put("maxEffThruDt", HIGH_VAL_DT);
        params.put("slsDt", ZYPDateUtil.getSalesDate());
        params.put("trtyRuleTpCd", TRTY_RULE_TP.POSTAL_CODE);

        return getSsmEZDClient().queryObjectList("getTrtyRules", params);
        // 2016/08/02 CSA-QC#11004 Add End
    }
}
