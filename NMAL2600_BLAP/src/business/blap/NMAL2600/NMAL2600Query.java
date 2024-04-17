/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL2600;

import java.util.HashMap;
import java.util.Map;

import business.blap.NMAL2600.common.NMAL2600CommonLogic;
import business.blap.NMAL2600.constant.NMAL2600Constant;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.GNRN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORG_TIER;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * <pre>
 * Business ID : NMAL2600 Territory Admin
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/01   Fujitsu         K.Koitabashi    Create          N/A
 * 2016/03/11   SRAA            Y.Chen          Update          QC#5310
 * 2016/03/31   Fujitsu         C.Yokoi         Update          QC#5945
 * 2016/08/24   Fujitsu         C.Yokoi         Update          QC#11652
 * 2017/09/19   Hitachi         J.Kim           Update          QC#21224
 * 2018/03/07   Fujitsu         M.Ohno          Update          QC#20233-1
 * 2018/03/09   Fujitsu         K.Ishizuka      Update          QC#23307
 *</pre>
 */
public final class NMAL2600Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NMAL2600Query MY_INSTANCE = new NMAL2600Query();

    /**
     * Constructor.
     */
    private NMAL2600Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NLBL0020Query
     */
    public static NMAL2600Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getBizAreaOrgPulldownList(Map<String, Object> ssmParam) {

        return getSsmEZDClient().queryObjectList("getBizAreaOrgPulldownList", ssmParam);
    }

    /**
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getTrtyTpPulldownList(Map<String, Object> ssmParam) {

        return getSsmEZDClient().queryObjectList("getTrtyTpPulldownList", ssmParam);
    }

    /**
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getOrgTierPulldownList(Map<String, Object> ssmParam) {

        return getSsmEZDClient().queryObjectList("getOrgTierPulldownList", ssmParam);
    }

    /**
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getTrtyGrpTpPulldownList(Map<String, Object> ssmParam) {

        return getSsmEZDClient().queryObjectList("getTrtyGrpTpPulldownList", ssmParam);
    }

    /**
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getTrtyRuleTpPulldownList(Map<String, Object> ssmParam) {

        return getSsmEZDClient().queryObjectList("getTrtyRuleTpPulldownList", ssmParam);
    }

    /**
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getTrtyRuleOprdTpPulldownList(Map<String, Object> ssmParam) {

        return getSsmEZDClient().queryObjectList("getTrtyRuleOprdTpPulldownList", ssmParam);
    }

    /**
     * getOrganizationTopTier
     * @param cMsg NMAL2600CMsg
     * @param sMsg NMAL2600SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getTerritoryTopTier(NMAL2600CMsg cMsg, NMAL2600SMsg sMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("cMsg", cMsg);
        ssmParam.put("gnrnTpCdCurrent", GNRN_TP.CURRENT);
        ssmParam.put("gnrnTpCdFuture", GNRN_TP.FUTURE);
        ssmParam.put("includeInactive", cMsg.xxChkBox_H1.getValue());
        ssmParam.put("rowNum", cMsg.T.length());
        ssmParam.put("trtyStruFlg", ZYPConstant.FLG_ON_Y);

        ssmParam.put("slsDt", ZYPDateUtil.getSalesDate());
        ssmParam.put("maxeffThruDt", NMAL2600Constant.MAX_EFF_THRU_DT);
        ssmParam.put("orgTierCd", NMAL2600Constant.FIRST_TIER_CD);

        if (ZYPCommonFunc.hasValue(cMsg.xxDplyTab.getValue())) {
            NMAL2600CommonLogic.setAdvancedSearchCriteria(ssmParam, cMsg);
        }

        if (ZYPCommonFunc.hasValue(cMsg.psnCd_H1) || ZYPCommonFunc.hasValue(cMsg.xxPsnNm_H1) || ZYPCommonFunc.hasValue(cMsg.psnNum_H1)) {
            ssmParam.put("rsrcSrch", ZYPConstant.FLG_ON_Y);
        } else {
            ssmParam.put("rsrcSrch", ZYPConstant.FLG_OFF_N);
        }

        return getSsmEZDClient().queryEZDMsgArray("getTerritoryTopTier", ssmParam, sMsg.T);
    }

    /**
     * getTerritory
     * @param cMsg NMAL2600CMsg
     * @param sMsg NMAL2600SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getTerritory(NMAL2600CMsg cMsg, NMAL2600SMsg sMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("cMsg", cMsg);
        ssmParam.put("gnrnTpCdCurrent", GNRN_TP.CURRENT);
        ssmParam.put("gnrnTpCdFuture", GNRN_TP.FUTURE);
        // 2016/03/31 CSA-QC#5945 Add Start
        ssmParam.put("gnrnTpCdDelete", GNRN_TP.DELETE);
        ssmParam.put("gnrnTpCdTerminated", GNRN_TP.TERMINATED);
        // 2016/03/31 CSA-QC#5945 Add End
        // 2018/03/07 QC#20233 add start
        ssmParam.put("gnrnTpCdPast", GNRN_TP.PAST);
        // 2018/03/07 QC#20233 add end
        ssmParam.put("includeInactive", cMsg.xxChkBox_H1.getValue());
        ssmParam.put("rowNum", cMsg.T.length());
        ssmParam.put("trtyStruFlg", ZYPConstant.FLG_ON_Y);

        ssmParam.put("rgtnStsCdP20", RGTN_STS.READY_FOR_ORDER_TAKING);
        ssmParam.put("rgtnStsCdP01", RGTN_STS.PENDING_COMPLETION);
        ssmParam.put("slsDt", ZYPDateUtil.getSalesDate());
        ssmParam.put("maxeffThruDt", NMAL2600Constant.MAX_EFF_THRU_DT);
        ssmParam.put("firstTierCd", NMAL2600Constant.FIRST_TIER_CD);
        // START 2017/09/19 J.Kim [QC#21224,ADD]
        ssmParam.put("psnCd_H1", cMsg.psnCd_H1.getValue());
        ssmParam.put("psnNum_H1", cMsg.psnNum_H1.getValue());
        ssmParam.put("xxPsnNm_H1", cMsg.xxPsnNm_H1.getValue());
        // END 2017/09/19 J.Kim [QC#21224,ADD]

        if (ZYPCommonFunc.hasValue(cMsg.xxDplyTab.getValue())) {
            NMAL2600CommonLogic.setAdvancedSearchCriteria(ssmParam, cMsg);
        }

        if (ZYPCommonFunc.hasValue(cMsg.psnCd_H1) || ZYPCommonFunc.hasValue(cMsg.xxPsnNm_H1) || ZYPCommonFunc.hasValue(cMsg.psnNum_H1)) {
            ssmParam.put("rsrcSrch", ZYPConstant.FLG_ON_Y);
        } else {
            ssmParam.put("rsrcSrch", ZYPConstant.FLG_OFF_N);
        }
        if (ORG_TIER._1.equals(cMsg.orgTierCd_P1.getValue())) {
            ssmParam.put("orgTierCd", ORG_TIER._1);
        } else if (ORG_TIER._2.equals(cMsg.orgTierCd_P1.getValue())) {
            ssmParam.put("orgTierCd", ORG_TIER._2);
        } else if (ORG_TIER._3.equals(cMsg.orgTierCd_P1.getValue())) {
            ssmParam.put("orgTierCd", ORG_TIER._3);
        } else if (ORG_TIER._4.equals(cMsg.orgTierCd_P1.getValue())) {
            ssmParam.put("orgTierCd", ORG_TIER._4);
        } else if (ORG_TIER._5.equals(cMsg.orgTierCd_P1.getValue())) {
            ssmParam.put("orgTierCd", ORG_TIER._5);
        } else if (ORG_TIER._6.equals(cMsg.orgTierCd_P1.getValue())) {
            ssmParam.put("orgTierCd", ORG_TIER._6);
        } else if (ORG_TIER._7.equals(cMsg.orgTierCd_P1.getValue())) {
            ssmParam.put("orgTierCd", ORG_TIER._7);
        } else if (ORG_TIER._8.equals(cMsg.orgTierCd_P1.getValue())) {
            ssmParam.put("orgTierCd", ORG_TIER._8);
        } else if (ORG_TIER._9.equals(cMsg.orgTierCd_P1.getValue())) {
            ssmParam.put("orgTierCd", ORG_TIER._9);
        } else if (ORG_TIER._10.equals(cMsg.orgTierCd_P1.getValue())) {
            ssmParam.put("orgTierCd", ORG_TIER._10);
        }
        return getSsmEZDClient().queryEZDMsgArray("getTerritory", ssmParam, sMsg.T);
    }

    /**
     * getOrganizationHierarchyWithResource
     * @param cMsg NMAL2600CMsg
     * @param sMsg NMAL2600SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getTerritoryWithRules(NMAL2600CMsg cMsg, NMAL2600SMsg sMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("cMsg", cMsg);
        ssmParam.put("gnrnTpCdCurrent", GNRN_TP.CURRENT);
        ssmParam.put("gnrnTpCdFuture", GNRN_TP.FUTURE);
        // 2016/03/31 CSA-QC#5945 Add Start
        ssmParam.put("gnrnTpCdDelete", GNRN_TP.DELETE);
        ssmParam.put("gnrnTpCdTerminated", GNRN_TP.TERMINATED);
        // 2016/03/31 CSA-QC#5945 Add End
        // 2018/03/07 QC#20233 add start
        ssmParam.put("gnrnTpCdPast", GNRN_TP.PAST);
        // 2018/03/07 QC#20233 add end
        ssmParam.put("includeInactive", cMsg.xxChkBox_H1.getValue());
        ssmParam.put("rowNum", cMsg.T.length());
        ssmParam.put("trtyStruFlg", ZYPConstant.FLG_ON_Y);

        ssmParam.put("rgtnStsCdP20", RGTN_STS.READY_FOR_ORDER_TAKING);
        ssmParam.put("rgtnStsCdP01", RGTN_STS.PENDING_COMPLETION);
        ssmParam.put("slsDt", ZYPDateUtil.getSalesDate());
        ssmParam.put("maxeffThruDt", NMAL2600Constant.MAX_EFF_THRU_DT);
        ssmParam.put("firstTierCd", NMAL2600Constant.FIRST_TIER_CD);

        if (ZYPCommonFunc.hasValue(cMsg.xxDplyTab.getValue())) {
            NMAL2600CommonLogic.setAdvancedSearchCriteria(ssmParam, cMsg);
        }

        if (ZYPCommonFunc.hasValue(cMsg.psnCd_H1) || ZYPCommonFunc.hasValue(cMsg.xxPsnNm_H1) || ZYPCommonFunc.hasValue(cMsg.psnNum_H1)) {
            ssmParam.put("rsrcSrch", ZYPConstant.FLG_ON_Y);
        } else {
            ssmParam.put("rsrcSrch", ZYPConstant.FLG_OFF_N);
        }

        if (ORG_TIER._1.equals(cMsg.orgTierCd_P1.getValue())) {
            ssmParam.put("orgTierCd", ORG_TIER._1);
        } else if (ORG_TIER._2.equals(cMsg.orgTierCd_P1.getValue())) {
            ssmParam.put("orgTierCd", ORG_TIER._2);
        } else if (ORG_TIER._3.equals(cMsg.orgTierCd_P1.getValue())) {
            ssmParam.put("orgTierCd", ORG_TIER._3);
        } else if (ORG_TIER._4.equals(cMsg.orgTierCd_P1.getValue())) {
            ssmParam.put("orgTierCd", ORG_TIER._4);
        } else if (ORG_TIER._5.equals(cMsg.orgTierCd_P1.getValue())) {
            ssmParam.put("orgTierCd", ORG_TIER._5);
        } else if (ORG_TIER._6.equals(cMsg.orgTierCd_P1.getValue())) {
            ssmParam.put("orgTierCd", ORG_TIER._6);
        } else if (ORG_TIER._7.equals(cMsg.orgTierCd_P1.getValue())) {
            ssmParam.put("orgTierCd", ORG_TIER._7);
        } else if (ORG_TIER._8.equals(cMsg.orgTierCd_P1.getValue())) {
            ssmParam.put("orgTierCd", ORG_TIER._8);
        } else if (ORG_TIER._9.equals(cMsg.orgTierCd_P1.getValue())) {
            ssmParam.put("orgTierCd", ORG_TIER._9);
        } else if (ORG_TIER._10.equals(cMsg.orgTierCd_P1.getValue())) {
            ssmParam.put("orgTierCd", ORG_TIER._10);
        }

        return getSsmEZDClient().queryEZDMsgArray("getTerritoryWithRules", ssmParam, sMsg.T);
    }

    /**
     * <pre>
     * execute SSM id="getAdditionalOrg" in [NMAL2600Query.xml]
     * </pre>
     * @param cMsg NMAL2600CMsg
     * @param sMsg NMAL2600SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getAdditionalOrg(NMAL2600CMsg cMsg, NMAL2600SMsg sMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put(NMAL2600Constant.DB_PARAM_CMSG, cMsg);
        ssmParam.put("trtyStruFlg", ZYPConstant.FLG_ON_Y);

        return getSsmEZDClient().queryObjectList("getAdditionalOrg", ssmParam);

    }

    /**
     * getTargetOrgForTopTierSearch
     * @param cMsg NMAL2500CMsg
     * @param sMsg NMAL2500SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getTargetOrgForTopTierSearch(NMAL2600CMsg cMsg, NMAL2600SMsg sMsg) {
        // 2016/08/24 CSA-QC#11652 Add Start
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("slsDt", ZYPDateUtil.getSalesDate());
        ssmParam.put("maxEffDt", NMAL2600Constant.MAX_EFF_THRU_DT);
        ssmParam.put("gnrnTp_Current", GNRN_TP.CURRENT);
        ssmParam.put("gnrnTp_Future", GNRN_TP.FUTURE);
        ssmParam.put("orgTierCd_1", ORG_TIER._1);
        ssmParam.put("cMsg", cMsg);
        // 2018/03/09 S21_NA#23307 Add Start
        ssmParam.put("includeInactive", cMsg.xxChkBox_H1.getValue());
        ssmParam.put("gnrnTpCdDelete", GNRN_TP.DELETE);
        ssmParam.put("gnrnTpCdTerminated", GNRN_TP.TERMINATED);
        // 2018/03/09 S21_NA#23307 Add End

        if (ZYPCommonFunc.hasValue(cMsg.xxDplyTab.getValue())) {

            for (int l = 0; l < NMAL2600Constant.ADV_MAX_CNT; l++) {
                ssmParam.put("trtyRuleTpCd" + String.valueOf(l), "");
                ssmParam.put("trtyRuleOprdTpCd" + String.valueOf(l), "");
                ssmParam.put("trtyRuleFromVal" + String.valueOf(l), "");
                ssmParam.put("trtyRuleThruVal" + String.valueOf(l), "");
                ssmParam.put("AdvSearchFlg" + String.valueOf(l), "");
            }

            int j = 1;

            for (int i = 0; i < cMsg.A.getValidCount(); i++) {
                NMAL2600_ACMsg line = cMsg.A.no(i);
                String trtyRuleTpCd = line.trtyRuleTpCd_P1.getValue();
                String trtyRuleOprdTpCd = line.trtyRuleOprdTpCd_P1.getValue();
                String trtyRuleFromValTx = line.trtyRuleFromValTxt_A1.getValue();
                String trtyRuleThruValTxt = line.trtyRuleThruValTxt_A1.getValue();

                if (ZYPCommonFunc.hasValue(trtyRuleTpCd) || ZYPCommonFunc.hasValue(trtyRuleOprdTpCd) || ZYPCommonFunc.hasValue(trtyRuleFromValTx) || ZYPCommonFunc.hasValue(trtyRuleThruValTxt)) {

                    if (ZYPCommonFunc.hasValue(trtyRuleTpCd)) {
                        ssmParam.put("trtyRuleTpCd" + String.valueOf(j), trtyRuleTpCd);
                    }
                    if (ZYPCommonFunc.hasValue(trtyRuleOprdTpCd)) {
                        ssmParam.put("trtyRuleOprdTpCd" + String.valueOf(j), trtyRuleOprdTpCd);
                    }
                    if (ZYPCommonFunc.hasValue(trtyRuleFromValTx)) {
                        ssmParam.put("trtyRuleFromVal" + String.valueOf(j), trtyRuleFromValTx);
                    }
                    if (ZYPCommonFunc.hasValue(trtyRuleThruValTxt)) {
                        ssmParam.put("trtyRuleThruVal" + String.valueOf(j), trtyRuleThruValTxt);
                    }
                    ssmParam.put("AdvSearchFlg" + String.valueOf(j), ZYPConstant.FLG_ON_Y);

                    j++;
                }
            }
        }

        return getSsmEZDClient().queryEZDMsgArray("getTargetOrgForTopTierSearch", ssmParam, sMsg.Q);
        // 2016/08/24 CSA-QC#11652 Add End
    }

    /**
     * getResource
     * @param cMsg NMAL2500CMsg
     * @param sMsg NMAL2500SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getTargetOrg(NMAL2600CMsg cMsg, NMAL2600SMsg sMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("slsDt", ZYPDateUtil.getSalesDate());
        ssmParam.put("maxEffDt", NMAL2600Constant.MAX_EFF_THRU_DT);
        ssmParam.put("cMsg", cMsg);
        // 2018/03/09 S21_NA#23307 Add Start
        ssmParam.put("includeInactive", cMsg.xxChkBox_H1.getValue());
        ssmParam.put("gnrnTpCdDelete", GNRN_TP.DELETE);
        ssmParam.put("gnrnTpCdTerminated", GNRN_TP.TERMINATED);
        // 2018/03/09 S21_NA#23307 Add End

        if (ZYPCommonFunc.hasValue(cMsg.xxDplyTab.getValue())) {

            for (int l = 0; l < NMAL2600Constant.ADV_MAX_CNT; l++) {
                ssmParam.put("trtyRuleTpCd" + String.valueOf(l), "");
                ssmParam.put("trtyRuleOprdTpCd" + String.valueOf(l), "");
                ssmParam.put("trtyRuleFromVal" + String.valueOf(l), "");
                ssmParam.put("trtyRuleThruVal" + String.valueOf(l), "");
                ssmParam.put("AdvSearchFlg" + String.valueOf(l), "");
            }

            int j = 1;

            for (int i = 0; i < cMsg.A.getValidCount(); i++) {
                NMAL2600_ACMsg line = cMsg.A.no(i);
                // QC#5310
                // String xxChkBox = line.xxChkBox_A1.getValue();
                String trtyRuleTpCd = line.trtyRuleTpCd_P1.getValue();
                String trtyRuleOprdTpCd = line.trtyRuleOprdTpCd_P1.getValue();
                String trtyRuleFromValTx = line.trtyRuleFromValTxt_A1.getValue();
                String trtyRuleThruValTxt = line.trtyRuleThruValTxt_A1.getValue();

                // QC#5310
                // if (ZYPConstant.FLG_ON_Y.equals(xxChkBox)) {
                    if (ZYPCommonFunc.hasValue(trtyRuleTpCd) || ZYPCommonFunc.hasValue(trtyRuleOprdTpCd) || ZYPCommonFunc.hasValue(trtyRuleFromValTx) || ZYPCommonFunc.hasValue(trtyRuleThruValTxt)) {

                        if (ZYPCommonFunc.hasValue(trtyRuleTpCd)) {
                            ssmParam.put("trtyRuleTpCd" + String.valueOf(j), trtyRuleTpCd);
                        }
                        if (ZYPCommonFunc.hasValue(trtyRuleOprdTpCd)) {
                            ssmParam.put("trtyRuleOprdTpCd" + String.valueOf(j), trtyRuleOprdTpCd);
                        }
                        if (ZYPCommonFunc.hasValue(trtyRuleFromValTx)) {
                            ssmParam.put("trtyRuleFromVal" + String.valueOf(j), trtyRuleFromValTx);
                        }
                        if (ZYPCommonFunc.hasValue(trtyRuleThruValTxt)) {
                            ssmParam.put("trtyRuleThruVal" + String.valueOf(j), trtyRuleThruValTxt);
                        }
                        ssmParam.put("AdvSearchFlg" + String.valueOf(j), ZYPConstant.FLG_ON_Y);

                        j++;
                    }
                // QC#5310
                // }
            }
        }

        return getSsmEZDClient().queryEZDMsgArray("getTargetOrg", ssmParam, sMsg.Q);
    }

}
