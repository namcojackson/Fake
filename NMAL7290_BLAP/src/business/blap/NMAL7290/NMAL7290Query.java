/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL7290;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_RULE_ATTRB;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_RULE_COND_TP;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * NMAL7290Query
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/24   Fujitsu         C.Tanaka        Create          N/A
 * 2016/09/27   Fujitsu         R.nakamura      Update          QC#6931
 *</pre>
 */
public final class NMAL7290Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NMAL7290Query MY_INSTANCE = new NMAL7290Query();

    /**
     * Private constructor
     */
    private NMAL7290Query() {
        super();
    }

    /**
     * Get the NMAL7290Query instance.
     * @return NMAL7290Query instance
     */
    public static NMAL7290Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * Search Price Rule
     * @param bizMsg NMAL7290CMsg
     * @param glblMsg NMAL7290SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult searchPrcRule(NMAL7290CMsg bizMsg, NMAL7290SMsg glblMsg) {
        Map<String, Object> params = new HashMap<String, Object>();

        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("prcRuleCatgCd", bizMsg.prcRuleCatgCd.getValue());
        params.put("prcRuleNm", bizMsg.prcRuleNm.getValue());
        params.put("effDt", ZYPDateUtil.getSalesDate());
        params.put("prcRuleAttrbCd_Cat", PRC_RULE_ATTRB.ORDER_CATEGORY);
        params.put("prcRuleCondFromTxt_Cat", bizMsg.dsOrdCatgCd.getValue());
        params.put("prcRuleAttrbCd_Rsn", PRC_RULE_ATTRB.ORDER_REASON);
        params.put("prcRuleCondFromTxt_Rsn", bizMsg.dsOrdTpCd.getValue());
        params.put("prcRuleCondFromTxt_LB", bizMsg.lineBizTpCd.getValue());
        params.put("rowNum", bizMsg.A.length() + 1);

        String prcRuleCondTpCd = bizMsg.prcRuleCondTpCd.getValue();
        if (!PRC_RULE_COND_TP.RULES_AND_TABLES.equals(prcRuleCondTpCd)) {
            params.put("prcRuleCondTpCd", prcRuleCondTpCd);
        }

        return getSsmEZDClient().queryEZDMsgArray("searchPrcRule", params, bizMsg.A);
    }

    /**
     * Search Price Rule Precedence
     * @param bizMsg NMAL7290CMsg
     * @param glblMsg NMAL7290SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult searchPrcRulePrcd(NMAL7290CMsg bizMsg, NMAL7290SMsg glblMsg) {
        Map<String, Object> params = new HashMap<String, Object>();

        params.put("glblCmpyCd", getGlobalCompanyCode());
        // QC#9694 2016/07/08 Mod Start
        //params.put("prcRulePrcdGrpNum", bizMsg.prcRulePrcdGrpNum.getValue());
        params.put("prcRulePrcdPk", bizMsg.prcRulePrcdPk.getValue());
        // QC#9694 2016/07/08 Mod End
        params.put("rowNum", bizMsg.B.length() + 1);

        return getSsmEZDClient().queryEZDMsgArray("searchPrcRulePrcd", params, bizMsg.B);
    }

    // QC#9694 2016/07/08 Mod Start
    /**
     * Search Price Rule Precedence Header
     * @param bizMsg NMAL7290CMsg
     * @param glblMsg NMAL7290SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult searchPrcRulePrcdHdr(NMAL7290CMsg bizMsg, NMAL7290SMsg glblMsg) {
        Map<String, Object> params = new HashMap<String, Object>();

        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("prcRulePrcdPk", bizMsg.prcRulePrcdPk.getValue());

        return getSsmEZDClient().queryEZDMsg("searchPrcRulePrcdHdr", params, bizMsg);
    }

    /**
     * 
     * @param bizMsg
     * @param bCMsg
     * @return
     */
    public BigDecimal getCntPrcRuleHdr(NMAL7290CMsg bizMsg, NMAL7290_BCMsg bCMsg) {
        Map<String, Object> params = new HashMap<String, Object>();

        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("prcRulePrcdPk", bizMsg.prcRulePrcdPk_BK.getValue());
        params.put("prcRuleHdrPk", bCMsg.prcRuleHdrPk_B.getValue());

        return (BigDecimal) getSsmEZDClient().queryObject("getCntPrcRuleHdr", params).getResultObject();
    }
    /**
     * 
     * @param bizMsg
     * @param bCMsg
     * @return
     */

    public S21SsmEZDResult getPrcRuleDtlData(NMAL7290CMsg bizMsg, NMAL7290_BCMsg bCMsg) {
        Map<String, Object> params = new HashMap<String, Object>();

        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("prcRuleHdrPk", bCMsg.prcRuleHdrPk_B.getValue());

        return getSsmEZDClient().queryObjectList("getPrcRuleData", params);
    }
    // QC#9694 2016/07/08 Mod End

    // Add Start 2016/09/27 QC#6931
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
    // Add End 2016/09/27 QC#6931
}
