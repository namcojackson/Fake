/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL7250;

import static business.blap.NMAL7250.constant.NMAL7250Constant.BIZ_ID;
import static business.blap.NMAL7250.constant.NMAL7250Constant.FLG_NO;
import static business.blap.NMAL7250.constant.NMAL7250Constant.FLG_YES;
import static business.blap.NMAL7250.constant.NMAL7250Constant.HIGH_VAL_DT;
import static business.blap.NMAL7250.constant.NMAL7250Constant.STS_ACTIVE;
import static business.blap.NMAL7250.constant.NMAL7250Constant.STS_ACTIVE_INACTIVE_CODE;
import static business.blap.NMAL7250.constant.NMAL7250Constant.STS_ACTIVE_ONLY_CODE;
import static business.blap.NMAL7250.constant.NMAL7250Constant.STS_ALL_CODE;
import static business.blap.NMAL7250.constant.NMAL7250Constant.STS_DELETE;
import static business.blap.NMAL7250.constant.NMAL7250Constant.STS_DELETE_ONLY_CODE;
import static business.blap.NMAL7250.constant.NMAL7250Constant.STS_INACTIVE;
import static business.blap.NMAL7250.constant.NMAL7250Constant.STS_INACTIVE_ONLY_CODE;
import static business.blap.NMAL7250.constant.NMAL7250Constant.STS_NONE;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.blap.NMAL7250.constant.NMAL7250Constant;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_GRP_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_RULE_ATTRB;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_RULE_COND_TP;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * NMAL7250Query
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/14   Fujitsu         W.Honda         Create          N/A
 * 2016/06/01   Fujitsu         Y.Kanefusa      Update          S21_NA#9173
 * 2016/07/13   Fujitsu         W.Honda         Update          S21_NA#10278
 * 2017/08/09   Fujitsu         Y.Kanefusa      Update          S21_NA#20249
 * 2017/11/10   Fujitsu         Y.Kanefusa      Update          S21_NA#20249-1
 * 2018/04/04   Fujitsu         R.Nakamura      Update          S21_NA#25206
 *</pre>
 */
public final class NMAL7250Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NMAL7250Query MY_INSTANCE = new NMAL7250Query();

    /**
     * Private constructor
     */
    private NMAL7250Query() {
        super();
    }

    /**
     * Get the NMAL7250Query instance.
     * @return NMAL7250Query instance
     */
    public static NMAL7250Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * Search
     * @param bizMsg NMAL7250CMsg
     * @param glblMsg NMAL7250SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult search(NMAL7250CMsg bizMsg, NMAL7250SMsg glblMsg) {
      Map<String, Object> params = new HashMap<String, Object>();
      params.put("glblCmpyCd", getGlobalCompanyCode());
      params.put("slsDt", ZYPDateUtil.getSalesDate());
      params.put("flgY", ZYPConstant.FLG_ON_Y);
      params.put("flgN", ZYPConstant.FLG_OFF_N);
      params.put("highValDt", HIGH_VAL_DT);
      params.put("flgYDisplay", FLG_YES);
      params.put("flgNDisplay", FLG_NO);
      params.put("active", STS_ACTIVE);
      params.put("inactive", STS_INACTIVE);
      params.put("delete", STS_DELETE);
      params.put("none", STS_NONE);
      params.put("activeCode", STS_ACTIVE_ONLY_CODE);
      params.put("inactiveCode", STS_INACTIVE_ONLY_CODE);
      params.put("deleteCode", STS_DELETE_ONLY_CODE);
      params.put("actInactCode", STS_ACTIVE_INACTIVE_CODE);
      params.put("allCode", STS_ALL_CODE);
      params.put("prcRuleAttrbCdShipToAcct", PRC_RULE_ATTRB.ACCNT_NUM_SHIP_TO);
      params.put("prcRuleAttrbCdBillToAcct", PRC_RULE_ATTRB.BILL_TO_ACCT_NUM);
      params.put("prcRuleAttrbCdSoldToAcct", PRC_RULE_ATTRB.SOLD_TO_ACCT_NUM);
      params.put("prcRuleAttrbCdOrderCategory", PRC_RULE_ATTRB.ORDER_CATEGORY);
      params.put("prcRuleAttrbCdOrderReason", PRC_RULE_ATTRB.ORDER_REASON);
      params.put("prcRuleAttrbCdCsmpNum", PRC_RULE_ATTRB.CSMP_NUM);
      params.put("prcRuleAttrbCdCustGrpShipTo", PRC_RULE_ATTRB.CUSTOMER_PRICE_GROUP_SHIP_TO);
      params.put("prcRuleAttrbCdCustGrp", PRC_RULE_ATTRB.CUSTOMER_PRICE_GROUP_BILL_TO);
      params.put("prcRuleAttrbCdCustGrpSoldTo", PRC_RULE_ATTRB.CUSTOMER_PRICE_GROUP_SOLD_TO);
      params.put("prcRuleAttrbCdMatGrp", PRC_RULE_ATTRB.MATERIAL_PRICE_GROUP);
      params.put("prcRuleAttrbCdBranch", PRC_RULE_ATTRB.BRANCH);
      params.put("prcRuleAttrbCdOrdLineBiz", PRC_RULE_ATTRB.ORDER_LINE_OF_BUSINESS);
      params.put("prcGrpTpCdCustGrp", PRC_GRP_TP.CUSTOMER_PRICING_GROUP);
      params.put("prcGrpTpCdMatGrp", PRC_GRP_TP.MATERIAL_GROUP);

      params.put("prcRuleNm",          bizMsg.prcRuleNm.getValue());
      params.put("defRulePrcdNum",     bizMsg.defRulePrcdNum.getValue());
      params.put("prcRuleCatgCd",      bizMsg.prcRuleCatgCd.getValue());
      params.put("prcRuleTrxCatgCd",   bizMsg.prcRuleTrxCatgCd.getValue());
      params.put("dsOrdCatgCd",        bizMsg.dsOrdCatgCd.getValue());
      params.put("dsOrdTpCd",          bizMsg.dsOrdTpCd.getValue());
      params.put("prcRuleAttrbCd",     bizMsg.prcRuleAttrbCd.getValue());
      params.put("prcRuleCondFromTxt", bizMsg.prcRuleCondFromTxt.getValue());
      params.put("dsAcctNum",          bizMsg.dsAcctNum.getValue());
      params.put("dsAcctNm",           bizMsg.dsAcctNm.getValue());
      params.put("csmpNum",            bizMsg.csmpContrNum.getValue());
      params.put("prcGrpPkCG",         bizMsg.prcGrpPk_CG.getValue());
      params.put("coaBrCd",            bizMsg.coaBrCd.getValue());
      params.put("lineBizTpCd",        bizMsg.lineBizTpCd.getValue());
      params.put("prcGrpPkMA",         bizMsg.prcGrpPk_MA.getValue());
      params.put("effFromDt",          bizMsg.effFromDt.getValue());
      params.put("effThruDt",          bizMsg.effThruDt.getValue());
      params.put("prcDispRecTpCd",     bizMsg.prcDispRecTpCd.getValue());
      if (!PRC_RULE_COND_TP.RULES_AND_TABLES.equals(bizMsg.prcRuleCondTpCd.getValue())) {
          params.put("prcRuleCondTpCd",    bizMsg.prcRuleCondTpCd.getValue());
      }


      if (ZYPCommonFunc.hasValue(bizMsg.prcRuleAttrbCd)) {
          params.put("attrbFlg",   ZYPConstant.FLG_ON_Y);
      } else {
          params.put("attrbFlg",   ZYPConstant.FLG_OFF_N);
      }

      if (ZYPCommonFunc.hasValue(bizMsg.dsOrdCatgCd)) {
          params.put("ordCatgFlg",   ZYPConstant.FLG_ON_Y);
      } else {
          params.put("ordCatgFlg",   ZYPConstant.FLG_OFF_N);
      }

      if (ZYPCommonFunc.hasValue(bizMsg.dsOrdTpCd)) {
          params.put("ordTpFlg",   ZYPConstant.FLG_ON_Y);
      } else {
          params.put("ordTpFlg",   ZYPConstant.FLG_OFF_N);
      }

      if (ZYPCommonFunc.hasValue(bizMsg.dsAcctNum)
              || ZYPCommonFunc.hasValue(bizMsg.dsAcctNm)) {
          params.put("acctFlg",   ZYPConstant.FLG_ON_Y);
      } else {
          params.put("acctFlg",   ZYPConstant.FLG_OFF_N);
      }

      if (ZYPCommonFunc.hasValue(bizMsg.csmpContrNum)) {
          params.put("csmpFlg",   ZYPConstant.FLG_ON_Y);
      } else {
          params.put("csmpFlg",   ZYPConstant.FLG_OFF_N);
      }

      if (ZYPCommonFunc.hasValue(bizMsg.prcGrpPk_CG)
              || ZYPCommonFunc.hasValue(bizMsg.prcGrpPk_MA)) {
          params.put("grpFlg",   ZYPConstant.FLG_ON_Y);
      } else {
          params.put("grpFlg",   ZYPConstant.FLG_OFF_N);
      }

      if (ZYPCommonFunc.hasValue(bizMsg.coaBrCd)) {
          params.put("coaBrFlg",   ZYPConstant.FLG_ON_Y);
      } else {
          params.put("coaBrFlg",   ZYPConstant.FLG_OFF_N);
      }

      if (S21StringUtil.isEquals(PRC_RULE_ATTRB.CUSTOMER_CHANNEL_SHIP_TO, bizMsg.prcRuleAttrbCd.getValue()) 
                || S21StringUtil.isEquals(PRC_RULE_ATTRB.CUSTOMER_PRICE_GROUP_SHIP_TO, bizMsg.prcRuleAttrbCd.getValue())
                || S21StringUtil.isEquals(PRC_RULE_ATTRB.ACCNT_NUM_SHIP_TO, bizMsg.prcRuleAttrbCd.getValue())
                || S21StringUtil.isEquals(PRC_RULE_ATTRB.ACCNT_NUM_SHIP_TO, bizMsg.prcRuleAttrbCd.getValue())
                || S21StringUtil.isEquals(PRC_RULE_ATTRB.CUSTOMER_PRICE_GROUP_BILL_TO , bizMsg.prcRuleAttrbCd.getValue())
                // QC#20249-1 2017/11/10 Del Start
                // || S21StringUtil.isEquals(PRC_RULE_ATTRB.MATERIAL_PRICE_GROUP_QTYBREAK, bizMsg.prcRuleAttrbCd.getValue()) // QC#20249 2017/08/09 Add
                // || S21StringUtil.isEquals(PRC_RULE_ATTRB.LINE_QTY_QTYBREAK, bizMsg.prcRuleAttrbCd.getValue()) // QC#20249 2017/08/09 Add
                // QC#20249-1 2017/11/10 Del End
          ) {
          params.put("itemPrcRuleCondFromTxt", null);
          params.put("itemPrcRuleCondThruTxt", null);
        } else {
            params.put("itemPrcRuleCondFromTxt", new StringBuffer().append(NMAL7250Constant.PRC_RULE_COND_FROM_TXT).append(bizMsg.prcRuleAttrbCd.getValue()).toString());
            if (S21StringUtil.isEquals(PRC_RULE_ATTRB.TOTAL_TRANSACTION_WEIGHT, bizMsg.prcRuleAttrbCd.getValue()) 
                    || S21StringUtil.isEquals(PRC_RULE_ATTRB.CALL_DATE, bizMsg.prcRuleAttrbCd.getValue())
                    || S21StringUtil.isEquals(PRC_RULE_ATTRB.LINE_AMOUNT, bizMsg.prcRuleAttrbCd.getValue()) 
                    || S21StringUtil.isEquals(PRC_RULE_ATTRB.LINE_QTY, bizMsg.prcRuleAttrbCd.getValue())
                    || S21StringUtil.isEquals(PRC_RULE_ATTRB.ORDER_SUBTOTAL, bizMsg.prcRuleAttrbCd.getValue()) 
                    || S21StringUtil.isEquals(PRC_RULE_ATTRB.PRICING_DATE, bizMsg.prcRuleAttrbCd.getValue())
                    || S21StringUtil.isEquals(PRC_RULE_ATTRB.REQUEST_DATE, bizMsg.prcRuleAttrbCd.getValue())
                    || S21StringUtil.isEquals(PRC_RULE_ATTRB.LINE_QTY_QTYBREAK, bizMsg.prcRuleAttrbCd.getValue()) // QC#20249-1 2017/11/10 Add
                ) {
                params.put("itemPrcRuleCondThruTxt", new StringBuffer().append(NMAL7250Constant.PRC_RULE_COND_THRU_TXT).append(bizMsg.prcRuleAttrbCd.getValue()).toString());
            }
        }

      return getSsmEZDClient().queryEZDMsgArray("searchPrcAdjustList", params, glblMsg.A);
    }

    /**
     * getSavedSearchOptionList
     * @param usrId user id
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSavedSearchOptionList(String usrId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("srchOptAplId", BIZ_ID);
        params.put("srchOptUsrId", usrId);

        return getSsmEZDClient().queryObjectList("getSavedSearchOptionList", params);
    }

    // Add Start 2018/04/04 QC#25206
    /**
     * getPullPrcRuleAttrbList
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPullPrcRuleAttrbList() {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        List<String> prcRuleAttrbCdList = new ArrayList<String>();
        prcRuleAttrbCdList.add(PRC_RULE_ATTRB.FORMULA);
        prcRuleAttrbCdList.add(PRC_RULE_ATTRB.PERCENT);
        prcRuleAttrbCdList.add(PRC_RULE_ATTRB.VALUE);
        params.put("prcRuleAttrbCdList", prcRuleAttrbCdList);

        return getSsmEZDClient().queryObjectList("getPullPrcRuleAttrbList", params);
    }
    // Add End 2018/04/04 QC#25206
}
