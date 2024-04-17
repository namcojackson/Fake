/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL7270;

import static business.blap.NMAL7270.constant.NMAL7270Constant.ASTERISK;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_RULE_ATTRB;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_RULE_COND_TP;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * NMAL7270Query
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/22   Fujitsu         M.Nakamura      Create          N/A
 * 2016/07/08   Fujitsu         Y.Kanefusa      Update          S21_NA#9694
 *</pre>
 */
public final class NMAL7270Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NMAL7270Query MY_INSTANCE = new NMAL7270Query();

    /**
     * Private constructor
     */
    private NMAL7270Query() {
        super();
    }

    /**
     * Get the NMAL7270Query instance.
     * @return NMAL7270Query instance
     */
    public static NMAL7270Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * Search
     * @param bizMsg NMAL7270CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult search(NMAL7270CMsg bizMsg) {
      Map<String, Object> params = new HashMap<String, Object>();
      params.put("glblCmpyCd", getGlobalCompanyCode());
      params.put("prcRuleHdrPk", bizMsg.prcRuleHdrPk_H1.getValue());
      params.put("prcRuleCondTpCd", PRC_RULE_COND_TP.PRICE_RULES);

      return getSsmEZDClient().queryEZDMsg("search", params, bizMsg);
    }

    /**
     * searchTrxCond
     * @param bizMsg NMAL7270CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult searchTrxCond(NMAL7270CMsg bizMsg) {
      Map<String, Object> params = new HashMap<String, Object>();
      params.put("glblCmpyCd", getGlobalCompanyCode());
      params.put("prcRuleHdrPk", bizMsg.prcRuleHdrPk_H1.getValue());
      params.put("callDt", PRC_RULE_ATTRB.CALL_DATE);
      params.put("prcDt", PRC_RULE_ATTRB.PRICING_DATE);
      params.put("reqDt", PRC_RULE_ATTRB.REQUEST_DATE);

      return getSsmEZDClient().queryEZDMsgArray("searchTrxCond", params, bizMsg.A);
    }

    /**
     * searchTrxCond
     * @param bizMsg NMAL7270CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult searchPrcDtl(NMAL7270CMsg bizMsg) {
      Map<String, Object> params = new HashMap<String, Object>();
      params.put("glblCmpyCd", getGlobalCompanyCode());
      params.put("prcRuleHdrPk", bizMsg.prcRuleHdrPk_H1 .getValue());

      return getSsmEZDClient().queryEZDMsgArray("searchPrcDtl", params, bizMsg.B);
    }

    /**
     * getUsingRuleCondGrp.
     * @param prcRuleHdrPk BigDecimal
     * @param prcRuleTrxCondPk BigDecimal
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getUsingRuleCondGrp(BigDecimal prcRuleHdrPk, BigDecimal prcRuleTrxCondPk) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("prcRuleHdrPk", prcRuleHdrPk);
        params.put("prcRuleTrxCondPk", prcRuleTrxCondPk);

        return getSsmEZDClient().queryObject("getUsingRuleCondGrp", params);
    }

    /**
     * isExistRuleNm.
     * @param prcRuleHdrPk BigDecimal
     * @param prcRuleNm String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult isExistRuleNm(BigDecimal prcRuleHdrPk, String prcRuleNm) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("prcRuleHdrPk", prcRuleHdrPk);
        params.put("prcRuleNm", prcRuleNm);

        return getSsmEZDClient().queryObject("isExistRuleNm", params);
    }

    /**
     * isExistsPrcRuleCmbnExcl.
     * @param lineBCMsg NMAL7270_BCMsg
     * @param prcFmlaTpCd String
     * @param prcFuncTpCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult isExistsPrcRuleCmbnExcl(NMAL7270_BCMsg lineBCMsg, String prcFmlaTpCd, String prcFuncTpCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("all", ASTERISK);
        params.put("prcRuleModTpCd", lineBCMsg.prcRuleModTpCd_B1.getValue());
        params.put("prcRuleAdjTpCd", lineBCMsg.prcRuleAdjTpCd_B1.getValue());
        params.put("prcRuleAdjLvlCd", lineBCMsg.prcRuleAdjLvlCd_B1.getValue());
        params.put("prcRuleAttrbCd", lineBCMsg.prcRuleAttrbCd_B1.getValue());
        params.put("prcFmlaTpCd", prcFmlaTpCd);
        params.put("prcFuncTpCd", prcFuncTpCd);

        return getSsmEZDClient().queryObject("isExistsPrcRuleCmbnExcl", params);
    }

    /**
     * getPrcRuleCondGrp.
     * @param prcRuleHdrPk BigDecimal
     * @param prcRuleCondGrpCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPrcRuleCondHdr(BigDecimal prcRuleHdrPk, String prcRuleCondGrpCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("prcRuleHdrPk", prcRuleHdrPk);
        params.put("prcRuleCondGrpCd", prcRuleCondGrpCd);

        return getSsmEZDClient().queryObject("getPrcRuleCondHdr", params);
    }

    /**
     * getAnyColmn.
     * @param colNm String
     * @param tblNm String
     * @param whereCol String
     * @param whereVal String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getAnyColmn(String colNm, String tblNm, String whereCol, String whereVal) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("colNm", colNm);
        params.put("tblNm", tblNm);
        params.put("whereCol", whereCol);
        params.put("whereVal", whereVal);

        return getSsmEZDClient().queryObject("getAnyColmn", params);
    }
    // QC#9694 2016/07/08 Add Start
    /**
      * getCountDefPrcd
      * @param bizMsg NMAL7260CMsg
      * @return Integer
      */
     public Integer getCountDefPrcd(NMAL7270CMsg bizMsg) {
         Map<String, Object> params = new HashMap<String, Object>();
         params.put("glblCmpyCd", getGlobalCompanyCode());
         params.put("defRulePrcd", bizMsg.defRulePrcdNum_H1.getValue());
         params.put("actvFlg", ZYPConstant.FLG_ON_Y);

         return (Integer) getSsmEZDClient().queryObject("getCountDefPrcd", params).getResultObject();
     }
     // QC#9694 2016/07/08 Add End
}
