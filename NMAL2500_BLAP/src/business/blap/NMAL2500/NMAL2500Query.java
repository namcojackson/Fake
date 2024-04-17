/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL2500;

import java.util.HashMap;
import java.util.Map;

import business.blap.NMAL2500.constant.NMAL2500Constant;

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
 * Business ID : NMAL2500 Org Resource Search
 * Function Name : the data base access processing by SSM
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/19   Fujitsu         K.Koitabashi    Create          N/A
 * 2016/03/01   Fujitsu         M.suzuki        Update          S21_NA#4540
 * 2016/03/31   Fujitsu         C.Yokoi         Update          CSA-QC#5949
 * 2016/05/18   SRAA            Y.Chen          Update          QC#8543
 * 2016/07/26   Fujitsu         C.Yokoi         Update          CSA-QC#11672
 * 2016/09/01   Fujitsu         C.Yokoi         Update          CSA-QC#11652
 * 2017/02/23   Fujitsu         K.Ishizuka      Update          QC#16481
 * 2017/06/20   Fujitsu         N.Sugiura       Update          QC#19356
 * 2018/03/07   Fujitsu         M.Ohno          Update          QC#20233-1
 *</pre>
 */
public final class NMAL2500Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NMAL2500Query MY_INSTANCE = new NMAL2500Query();

    /**
     * Constructor.
     */
    private NMAL2500Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NLBL0020Query
     */
    public static NMAL2500Query getInstance() {
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
    public S21SsmEZDResult getVarCharConstPulldownList(Map<String, Object> ssmParam) {

        return getSsmEZDClient().queryObjectList("getVarCharConstPulldownList", ssmParam);
    }

    /**
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getOrgTierPulldownList(Map<String, Object> ssmParam) {

        return getSsmEZDClient().queryObjectList("getOrgTierPulldownList", ssmParam);
    }
// 2016/03/01 S21_NA#4540 Mod Start --------------
//    /**
//     * @param ssmParam Map<String, Object>
//     * @return S21SsmEZDResult
//     */
//    public S21SsmEZDResult getCsrRgTpPulldownList(Map<String, Object> ssmParam) {
//
//        return getSsmEZDClient().queryObjectList("getCsrRgTpPulldownList", ssmParam);
//    }
 // 2016/03/01 S21_NA#4540 Mod End --------------

    /**
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getOrgFuncRoleTpPulldownList(Map<String, Object> ssmParam) {

        return getSsmEZDClient().queryObjectList("getOrgFuncRoleTpPulldownList", ssmParam);
    }

    /**
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getOrgFuncRoleTpWithBusinessAreaPulldownList(Map<String, Object> ssmParam) {

        return getSsmEZDClient().queryObjectList("getOrgFuncRoleTpWithBusinessAreaPulldownList", ssmParam);
    }

    /**
     * getOrganizationHierarchy
     * @param cMsg NMAL2500CMsg
     * @param sMsg NMAL2500SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getOrganizationHierarchy(NMAL2500CMsg cMsg, NMAL2500SMsg sMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("cMsg", cMsg);
        ssmParam.put("gnrnTpCdCurrent", GNRN_TP.CURRENT);
        ssmParam.put("gnrnTpCdFuture", GNRN_TP.FUTURE);
        ssmParam.put("includeInactive", cMsg.xxChkBox_H2.getValue());
        ssmParam.put("firstTierCd", NMAL2500Constant.FIRST_TIER_CD);
        // 2016/03/31 CSA-QC#5945 Add Start
        ssmParam.put("gnrnTpCdDelete", GNRN_TP.DELETE);
        ssmParam.put("gnrnTpCdTerminated" , GNRN_TP.TERMINATED);
        // 2016/03/31 CSA-QC#5945 Add End
        // 2016/03/31 CSA-QC#6960 Add Start
        ssmParam.put("maxeffThruDt", NMAL2500Constant.MAX_EFF_THRU_DT);
        // 2016/03/31 CSA-QC#6960 Add End
        // QC#8543
        ssmParam.put("slsDt", ZYPDateUtil.getSalesDate());
        // 2017/06/20 CSA-QC#19356 Add Start
        if (NMAL2500Constant.DISPLAY_TOP_TIERS_ONLY == cMsg.xxRadioBtn_H1.getValueInt()) {
            ssmParam.put("topTiersOnly", cMsg.xxRadioBtn_H1.getValueInt());
        } else {
            ssmParam.put("orgLevelChildrenOnly", cMsg.xxRadioBtn_H1.getValueInt());
        }
        // 2017/06/20 CSA-QC#19356 Add End
        // 2018/03/07 QC#20233-1 add start
        ssmParam.put("gnrnTpCdPast", GNRN_TP.PAST);
        // 2018/03/07 QC#20233-1 add start

        return getSsmEZDClient().queryEZDMsgArray("getHierarchy", ssmParam, sMsg.T);
    }

    /**
     * getOrganizationTopTier
     * @param cMsg NMAL2500CMsg
     * @param sMsg NMAL2500SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getOrganizationTopTier(NMAL2500CMsg cMsg, NMAL2500SMsg sMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("cMsg", cMsg);
        ssmParam.put("orgTierCd", NMAL2500Constant.FIRST_TIER_CD);
        ssmParam.put("slsDt", ZYPDateUtil.getSalesDate());
        ssmParam.put("maxeffThruDt", NMAL2500Constant.MAX_EFF_THRU_DT);
        ssmParam.put("includeInactive", cMsg.xxChkBox_H2.getValue());
        ssmParam.put("gnrnTpCdCurrent", GNRN_TP.CURRENT);
        ssmParam.put("gnrnTpCdFuture" , GNRN_TP.FUTURE);
        ssmParam.put("rgtnStsCdP20", RGTN_STS.READY_FOR_ORDER_TAKING);
        ssmParam.put("rgtnStsCdP01", RGTN_STS.PENDING_COMPLETION);
        // 2016/03/31 CSA-QC#5945 Add Start
        ssmParam.put("gnrnTpCdDelete", GNRN_TP.DELETE);
        ssmParam.put("gnrnTpCdTerminated" , GNRN_TP.TERMINATED);
        // 2016/03/31 CSA-QC#5945 Add End

        // 2016/07/26 CSA-QC#11652 Add Start
        if (ZYPCommonFunc.hasValue(cMsg.xxPsnNm_H3) || ZYPCommonFunc.hasValue(cMsg.psnCd_H3)
            || ZYPCommonFunc.hasValue(cMsg.psnNum_H3) || ZYPCommonFunc.hasValue(cMsg.jobTtlCd_H3)
            || ZYPCommonFunc.hasValue(cMsg.hrTtlNm_H3) || ZYPCommonFunc.hasValue(cMsg.orgFuncRoleTpCd_P3)
            || ZYPCommonFunc.hasValue(cMsg.orgNm_H3)) {
            ssmParam.put("rsrcSearch" , ZYPConstant.FLG_ON_Y);
        }
        // 2016/07/26 CSA-QC#11652 Add End

        return getSsmEZDClient().queryEZDMsgArray("getHierarchyTopTier", ssmParam, sMsg.T);
    }

    /**
     * getOrganizationHierarchyWithResource
     * @param cMsg NMAL2500CMsg
     * @param sMsg NMAL2500SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getOrganizationHierarchyWithResource(NMAL2500CMsg cMsg, NMAL2500SMsg sMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("cMsg", cMsg);
        ssmParam.put("slsDt", ZYPDateUtil.getSalesDate());
        ssmParam.put("rgtnStsCdP20", RGTN_STS.READY_FOR_ORDER_TAKING);
        ssmParam.put("rgtnStsCdP01", RGTN_STS.PENDING_COMPLETION);
        ssmParam.put("includeInactive", cMsg.xxChkBox_H2.getValue());
        ssmParam.put("firstTierCd", NMAL2500Constant.FIRST_TIER_CD);
        ssmParam.put("gnrnTpCdCurrent", GNRN_TP.CURRENT);
        ssmParam.put("gnrnTpCdFuture" , GNRN_TP.FUTURE);
        // 2016/03/31 CSA-QC#5945 Add Start
        ssmParam.put("gnrnTpCdDelete", GNRN_TP.DELETE);
        ssmParam.put("gnrnTpCdTerminated" , GNRN_TP.TERMINATED);
        // 2016/03/31 CSA-QC#5945 Add End
        // 2016/03/31 CSA-QC#6960 Add Start
        ssmParam.put("maxeffThruDt", NMAL2500Constant.MAX_EFF_THRU_DT);
        // 2016/03/31 CSA-QC#6960 Add End

        // 2016/07/26 CSA-QC#11652 Add Start
        if (ZYPCommonFunc.hasValue(cMsg.xxPsnNm_H3) || ZYPCommonFunc.hasValue(cMsg.psnCd_H3)
            || ZYPCommonFunc.hasValue(cMsg.psnNum_H3) || ZYPCommonFunc.hasValue(cMsg.jobTtlCd_H3)
            || ZYPCommonFunc.hasValue(cMsg.hrTtlNm_H3) || ZYPCommonFunc.hasValue(cMsg.orgFuncRoleTpCd_P3)
            || ZYPCommonFunc.hasValue(cMsg.orgNm_H3)) {
            ssmParam.put("rsrcSearch" , ZYPConstant.FLG_ON_Y);
        }
        // 2016/07/26 CSA-QC#11652 Add End
        // 2018/03/07 QC#20233-1 add start
        ssmParam.put("gnrnTpCdPast", GNRN_TP.PAST);
        // 2018/03/07 QC#20233-1 add start

        return getSsmEZDClient().queryEZDMsgArray("getHierarchyWithResource", ssmParam, sMsg.T);
    }

    /**
     * getResource
     * @param cMsg NMAL2500CMsg
     * @param sMsg NMAL2500SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getResource(NMAL2500CMsg cMsg, NMAL2500SMsg sMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("cMsg", cMsg);
        ssmParam.put("bizAreaOrgCd_P1", cMsg.bizAreaOrgCd_P1.getValue());
        ssmParam.put("orgNm_H2", cMsg.orgNm_H2.getValue());
        ssmParam.put("orgTierCd_P2", cMsg.orgTierCd_P2.getValue());
        ssmParam.put("csrRgTpCd_P2", cMsg.csrRgTpCd_P2.getValue());
        ssmParam.put("includingInactive", cMsg.xxChkBox_H2.getValue());
        ssmParam.put("xxPsnNm", cMsg.xxPsnNm_H3.getValue());
        ssmParam.put("psnCd", cMsg.psnCd_H3.getValue());
        ssmParam.put("jobTtlCd", cMsg.jobTtlCd_H3.getValue());
        ssmParam.put("jobTtlNm", cMsg.hrTtlNm_H3.getValue());
        ssmParam.put("psnNum", cMsg.psnNum_H3.getValue());
        ssmParam.put("orgFuncRoleTpCd", cMsg.orgFuncRoleTpCd_P3.getValue());
        ssmParam.put("orgNm", cMsg.orgNm_H3.getValue());
        // MOD START S21_NA QC#16481
        ssmParam.put("effFromDt_FR", cMsg.effFromDt_FR.getValue());
        ssmParam.put("effFromDt_TO", cMsg.effFromDt_TO.getValue());
        ssmParam.put("effThruDt_FR", cMsg.effThruDt_FR.getValue());
        ssmParam.put("effThruDt_TO", cMsg.effThruDt_TO.getValue());
        // MOD END S21_NA QC#16481
        ssmParam.put("rgtnStsCdP20", RGTN_STS.READY_FOR_ORDER_TAKING);
        ssmParam.put("rgtnStsCdP01", RGTN_STS.PENDING_COMPLETION);
        ssmParam.put("gnrnTpCdCurrent", GNRN_TP.CURRENT);
        ssmParam.put("gnrnTpCdFuture", GNRN_TP.FUTURE);
        // 2016/03/31 CSA-QC#5945 Add Start
        ssmParam.put("gnrnTpCdDelete", GNRN_TP.DELETE);
        ssmParam.put("gnrnTpCdTerminated", GNRN_TP.TERMINATED);
        // 2016/03/31 CSA-QC#5945 Add End
        ssmParam.put("rowNum", sMsg.A.length());
        ssmParam.put("slsDt", ZYPDateUtil.getSalesDate());

        return getSsmEZDClient().queryEZDMsgArray("getResource", ssmParam, sMsg.A);
    }

    /**
     * <pre>
     * execute SSM id="getActiveChildOrganization" in [NMAL2500Query.xml]
     * </pre>
     * @param cMsg NMAL2500CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getActiveChildOrganization(NMAL2500CMsg cMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put(NMAL2500Constant.DB_PARAM_CMSG, cMsg);
        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("gnrnTpCdCurrent", GNRN_TP.CURRENT);
        ssmParam.put("gnrnTpCdFuture", GNRN_TP.FUTURE);

        return getSsmEZDClient().queryEZDMsg("getActiveChildOrganization", ssmParam, cMsg);
    }

    /**
     * <pre>
     * execute SSM id="getActiveParentOrganization" in [NMAL2500Query.xml]
     * </pre>
     * @param cMsg NMAL2500CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getActiveParentOrganization(NMAL2500CMsg cMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put(NMAL2500Constant.DB_PARAM_CMSG, cMsg);
        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("gnrnTpCdCurrent", GNRN_TP.CURRENT);
        ssmParam.put("gnrnTpCdFuture", GNRN_TP.FUTURE);

        return getSsmEZDClient().queryObjectList("getActiveParentOrganization", ssmParam);
    }

    /**
     * <pre>
     * execute SSM id="getActiveResource" in [NMAL2500Query.xml]
     * </pre>
     * @param cMsg NMAL2500CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getActiveResource(NMAL2500CMsg cMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put(NMAL2500Constant.DB_PARAM_CMSG, cMsg);
        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("slsDt", ZYPDateUtil.getSalesDate());
        ssmParam.put("gnrnTpCdCurrent", GNRN_TP.CURRENT);
        ssmParam.put("gnrnTpCdFuture", GNRN_TP.FUTURE);
        ssmParam.put("rgtnStsCdP20", RGTN_STS.READY_FOR_ORDER_TAKING);
        ssmParam.put("rgtnStsCdP01", RGTN_STS.PENDING_COMPLETION);

        return getSsmEZDClient().queryEZDMsg("getActiveResource", ssmParam, cMsg);
    }

    /**
     * <pre>
     * execute SSM id="getPsnNum" in [NMAL2500Query.xml]
     * </pre>
     * @param cMsg NMAL2500CMsg
     * @param sMsg NMAL2500SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPsnNum(NMAL2500CMsg cMsg, NMAL2500SMsg sMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put(NMAL2500Constant.DB_PARAM_CMSG, cMsg);

        return getSsmEZDClient().queryObjectList("getPsnNum", ssmParam);

    }

    /**
     * <pre>
     * execute SSM id="getAdditionalOrg" in [NMAL2500Query.xml]
     * </pre>
     * @param cMsg NMAL2500CMsg
     * @param sMsg NMAL2500SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getAdditionalOrg(NMAL2500CMsg cMsg, NMAL2500SMsg sMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put(NMAL2500Constant.DB_PARAM_CMSG, cMsg);

        return getSsmEZDClient().queryObjectList("getAdditionalOrg", ssmParam);
    }

    /**
     * getTargetOrgForTopTier
     * @param cMsg NMAL2500CMsg
     * @param sMsg NMAL2500SMsg
     * @param boolean resource search flag
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getTargetOrgForTopTier(NMAL2500CMsg cMsg, NMAL2500SMsg sMsg) {
        // 2016/09/01 CSA-QC#11652 Add Start
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("cMsg", cMsg);

        ssmParam.put("rgtnStsCdP20", RGTN_STS.READY_FOR_ORDER_TAKING);
        ssmParam.put("rgtnStsCdP01", RGTN_STS.PENDING_COMPLETION);
        ssmParam.put("gnrnTpCdCurrent", GNRN_TP.CURRENT);
        ssmParam.put("gnrnTpCdFuture", GNRN_TP.FUTURE);
        ssmParam.put("maxeffThruDt", NMAL2500Constant.MAX_EFF_THRU_DT);
        ssmParam.put("slsDt", ZYPDateUtil.getSalesDate());
        ssmParam.put("orgTierCd_1", ORG_TIER._1);

        if (ZYPCommonFunc.hasValue(cMsg.xxPsnNm_H3) || ZYPCommonFunc.hasValue(cMsg.psnCd_H3)
                || ZYPCommonFunc.hasValue(cMsg.psnNum_H3) || ZYPCommonFunc.hasValue(cMsg.jobTtlCd_H3)
                || ZYPCommonFunc.hasValue(cMsg.hrTtlNm_H3) || ZYPCommonFunc.hasValue(cMsg.orgFuncRoleTpCd_P3)
                || ZYPCommonFunc.hasValue(cMsg.orgNm_H3)) {
            ssmParam.put("rsrcSearch" , ZYPConstant.FLG_ON_Y);
        }

        return getSsmEZDClient().queryEZDMsgArray("getTargetOrgForTopTierSearch", ssmParam, sMsg.O);
        // 2016/09/01 CSA-QC#11652 Add End
    }

    /**
     * getTargetOrg
     * @param cMsg NMAL2500CMsg
     * @param sMsg NMAL2500SMsg
     * @param boolean resource search flag
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getTargetOrg(NMAL2500CMsg cMsg, NMAL2500SMsg sMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("cMsg", cMsg);

        // 2016/07/26 CSA-QC#11652 Add Start
        ssmParam.put("rgtnStsCdP20", RGTN_STS.READY_FOR_ORDER_TAKING);
        ssmParam.put("rgtnStsCdP01", RGTN_STS.PENDING_COMPLETION);
        ssmParam.put("gnrnTpCdCurrent", GNRN_TP.CURRENT);
        ssmParam.put("gnrnTpCdFuture", GNRN_TP.FUTURE);
        ssmParam.put("maxeffThruDt", NMAL2500Constant.MAX_EFF_THRU_DT);
        ssmParam.put("slsDt", ZYPDateUtil.getSalesDate());

        if (ZYPCommonFunc.hasValue(cMsg.xxPsnNm_H3) || ZYPCommonFunc.hasValue(cMsg.psnCd_H3)
                || ZYPCommonFunc.hasValue(cMsg.psnNum_H3) || ZYPCommonFunc.hasValue(cMsg.jobTtlCd_H3)
                || ZYPCommonFunc.hasValue(cMsg.hrTtlNm_H3) || ZYPCommonFunc.hasValue(cMsg.orgFuncRoleTpCd_P3)
                || ZYPCommonFunc.hasValue(cMsg.orgNm_H3)) {
            ssmParam.put("rsrcSearch" , ZYPConstant.FLG_ON_Y);
        }
        // 2016/07/26 CSA-QC#11652 Add End

        return getSsmEZDClient().queryEZDMsgArray("getTargetOrg", ssmParam, sMsg.O);
    }

    /**
     * getTargetResource
     * @param cMsg NMAL2500CMsg
     * @param sMsg NMAL2500SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getTargetResource(NMAL2500CMsg cMsg, NMAL2500SMsg sMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("cMsg", cMsg);
        ssmParam.put("includingInactive", cMsg.xxChkBox_H2.getValue());
        ssmParam.put("xxPsnNm", cMsg.xxPsnNm_H3.getValue());
        ssmParam.put("psnCd", cMsg.psnCd_H3.getValue());
        ssmParam.put("jobTtlCd", cMsg.jobTtlCd_H3.getValue());
        ssmParam.put("jobTtlNm", cMsg.hrTtlNm_H3.getValue());
        ssmParam.put("psnNum", cMsg.psnNum_H3.getValue());
        ssmParam.put("orgFuncRoleTpCd", cMsg.orgFuncRoleTpCd_P3.getValue());
        ssmParam.put("orgNm", cMsg.orgNm_H3.getValue());
        // MOD START S21_NA QC#16481
        ssmParam.put("effFromDt_FR", cMsg.effFromDt_FR.getValue());
        ssmParam.put("effFromDt_TO", cMsg.effFromDt_TO.getValue());
        ssmParam.put("effThruDt_FR", cMsg.effThruDt_FR.getValue());
        ssmParam.put("effThruDt_TO", cMsg.effThruDt_TO.getValue());
        // MOD START S21_NA QC#16481
        ssmParam.put("rgtnStsCdP20", RGTN_STS.READY_FOR_ORDER_TAKING);
        ssmParam.put("rgtnStsCdP01", RGTN_STS.PENDING_COMPLETION);
        ssmParam.put("gnrnTpCdCurrent", GNRN_TP.CURRENT);
        ssmParam.put("gnrnTpCdFuture", GNRN_TP.FUTURE);
        ssmParam.put("rowNum", sMsg.R.length());
        ssmParam.put("slsDt", ZYPDateUtil.getSalesDate());

        return getSsmEZDClient().queryEZDMsgArray("getTargetResource", ssmParam, sMsg.R);
    }

}
