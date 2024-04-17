/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL2520;

import java.util.HashMap;
import java.util.Map;

import business.blap.NMAL2510.constant.NMAL2510Constant;
import business.blap.NMAL2520.constant.NMAL2520Constant;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.GNRN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORG_TIER;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * <pre>
 * Business ID : NMAL2520 Org Structure Maintenance
 * Function Name : the data base access processing by SSM
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/19   Fujitsu         K.Koitabashi    Create          N/A
 * 2016/02/19   Fujitsu         C.Yokoi         Update          CSA-QC#3280
 * 2016/03/04   Fujitsu         C.Yokoi         Update          CSA-QC#4654
 * 2016/03/31   Fujitsu         C.Yokoi         Update          CSA-QC#5945
 * 2016/04/13   Fujitsu         C.Yokoi         Update          CSA-QC#6960
 * 2016/07/07   Fujitsu         C.Tanaka        Update          CSA-QC#11454
 * 2016/08/31   SRAA            Y.Chen          Update          QC#7966
 * 2017/06/14   Hitachi          J.Kim          Update          QC#18924
 * 2017/09/28   Fujitsu         H.Ikeda         Update          QC#21344
 * 2018/01/30   Fujitsu         Hd.Sugawara     Update          QC#22699
 *</pre>
 */
public final class NMAL2520Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NMAL2520Query MY_INSTANCE = new NMAL2520Query();

    /**
     * Constructor.
     */
    private NMAL2520Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NLBL0020Query
     */
    public static NMAL2520Query getInstance() {
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
    public S21SsmEZDResult getOrgTierPulldownList(Map<String, Object> ssmParam) {

        return getSsmEZDClient().queryObjectList("getOrgTierPulldownList", ssmParam);
    }

    /**
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getLineBizTpPulldownList(Map<String, Object> ssmParam) {

        return getSsmEZDClient().queryObjectList("getLineBizTpPulldownList", ssmParam);
    }

    /**
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCsrRgTpPulldownList(Map<String, Object> ssmParam) {

        return getSsmEZDClient().queryObjectList("getCsrRgTpPulldownList", ssmParam);
    }

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
     * @param cMsg NMAL2520CMsg
     * @param sMsg NMAL2520SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getOrganization(NMAL2520CMsg cMsg, NMAL2520SMsg sMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("slsDt", ZYPDateUtil.getSalesDate());
        ssmParam.put(NMAL2520Constant.DB_PARAM_CMSG, cMsg);
        ssmParam.put("firstTierCd", NMAL2520Constant.FIRST_TIER_CD);

        return getSsmEZDClient().queryEZDMsg("getOrganization", ssmParam, sMsg);
    }

    /**
     * @param cMsg NMAL2520CMsg
     * @param sMsg NMAL2520SMsg
     * @return S21SsmEZDResult
     */

    public S21SsmEZDResult getOrganizationParent(NMAL2520CMsg cMsg, NMAL2520SMsg sMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("globalCmpyCd", getGlobalCompanyCode());
        ssmParam.put(NMAL2520Constant.DB_PARAM_CMSG, cMsg);
        ssmParam.put("slsDt", ZYPDateUtil.getSalesDate());
        ssmParam.put("rowNum", NMAL2520Constant.DB_PARAM_ROWNUM_MAX);
        ssmParam.put("firstTierCd", NMAL2520Constant.FIRST_TIER_CD);
        // 2016/03/31 CSA-QC#5945 Add Start
        ssmParam.put("gnrnTpDelete", GNRN_TP.DELETE);
        ssmParam.put("gnrnTpTerminated", GNRN_TP.TERMINATED);
        // 2016/03/31 CSA-QC#5945 Add End
        // QC#7966
        ssmParam.put("gnrnTpCdCurrent", GNRN_TP.CURRENT);
        ssmParam.put("gnrnTpCdFuture", GNRN_TP.FUTURE);

        return getSsmEZDClient().queryEZDMsgArray("getOrganizationParent", ssmParam, sMsg.A);
    }

// QC#7966
//    /**
//     * @param cMsg NMAL2520CMsg
//     * @param sMsg NMAL2520SMsg
//     * @return S21SsmEZDResult
//     */
//
//    public S21SsmEZDResult getOrganizationParentWithFilter(NMAL2520CMsg cMsg, NMAL2520SMsg sMsg) {
//        Map<String, Object> ssmParam = new HashMap<String, Object>();
//
//        ssmParam.put("globalCmpyCd", getGlobalCompanyCode());
//        ssmParam.put(NMAL2520Constant.DB_PARAM_CMSG, cMsg);
//        ssmParam.put("slsDt", ZYPDateUtil.getSalesDate());
//        ssmParam.put("rowNum", NMAL2520Constant.DB_PARAM_ROWNUM_MAX);
//        ssmParam.put("gnrnTpCd_Current", GNRN_TP.CURRENT);
//        ssmParam.put("firstTierCd", NMAL2520Constant.FIRST_TIER_CD);
//
//        return getSsmEZDClient().queryEZDMsgArray("getOrganizationParentWithFilter", ssmParam, sMsg.A);
//    }

    /**
     * @param bizAreaOrgCd String
     * @param orgFuncRoleTpCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult checkBusinessAreaRoll(String bizAreaOrgCd, String orgFuncRoleTpCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("trtyStruFlg", ZYPConstant.FLG_OFF_N);
        ssmParam.put("bizAreaOrgCd", bizAreaOrgCd);
        ssmParam.put("orgFuncRoleTpCd", orgFuncRoleTpCd);

        return getSsmEZDClient().queryObject("checkBusinessAreaRoll", ssmParam);
    }

    /**
     * @param cMsg NMAL2520CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult checkDuplicateOrganizationName(NMAL2520CMsg cMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("trtyStruFlg", ZYPConstant.FLG_OFF_N);
        ssmParam.put("orgNm", cMsg.orgNm_H1.getValue());

        return getSsmEZDClient().queryObjectList("checkDuplicateOrganizationName", ssmParam);
    }

    /**
     * @param cMsg NMAL2520CMsg
     * @param suffix String
     * @param i int
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult checkActiveChildOrganization(NMAL2520CMsg cMsg, String suffix, int i) {

        // 2016/07/07 CSA-QC#11454 Add Start
        String slsDt = ZYPDateUtil.getSalesDate();
        // 2016/07/07 CSA-QC#11454 Add End

        // 2016/02/19 CSA-QC#3280 Add Start
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("maxEffDt", NMAL2520Constant.DB_PARAM_MAX_EFF_DT);

        if (NMAL2520Constant.SUFFIX_A.equals(suffix)) {
            ssmParam.put("orgCd", cMsg.orgCd_H1);
            ssmParam.put("effFromDt", cMsg.A.no(i).effFromDt_A1.getValue());
            ssmParam.put("effThruDt", cMsg.A.no(i).effThruDt_A1.getValue());
        } else {
            ssmParam.put("orgCd", cMsg.B.no(i).orgCd_B1.getValue());
            // 2016/07/07 CSA-QC#11454 Mod Start
            // ssmParam.put("effFromDt",
            // cMsg.B.no(i).effFromDt_B1.getValue());
            ssmParam.put("effFromDt", slsDt);
            // 2016/07/07 CSA-QC#11454 Mod End
            ssmParam.put("effThruDt", cMsg.B.no(i).effThruDt_B1.getValue());
        }
        // 2016/07/07 CSA-QC#11454 Add Start
        ssmParam.put("gnrnTpCd_Current", GNRN_TP.CURRENT);
        ssmParam.put("gnrnTpCd_Future", GNRN_TP.FUTURE);
        // 2016/07/07 CSA-QC#11454 Add End
        return getSsmEZDClient().queryObject("checkActiveChildOrganization", ssmParam);
        // 2016/02/19 CSA-QC#3280 Add End
    }

    /**
     * @param cMsg NMAL2520CMsg
     * @param sMsg NMAL2520SMsg
     * @return S21SsmEZDResult
     */

    public S21SsmEZDResult getOrganizationChildren(NMAL2520CMsg cMsg, NMAL2520SMsg sMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("globalCmpyCd", getGlobalCompanyCode());
        ssmParam.put(NMAL2520Constant.DB_PARAM_CMSG, cMsg);
        ssmParam.put("slsDt", ZYPDateUtil.getSalesDate());
        ssmParam.put("rowNum", NMAL2520Constant.DB_PARAM_ROWNUM_MAX);
        ssmParam.put("firstTierCd", NMAL2520Constant.FIRST_TIER_CD);
        ssmParam.put("orgCd", cMsg.orgCd_H1.getValue());
        // 2016/03/31 CSA-QC#5945 Add Start
        ssmParam.put("gnrnTpDelete", GNRN_TP.DELETE);
        ssmParam.put("gnrnTpTerminated", GNRN_TP.TERMINATED);
        // 2016/03/31 CSA-QC#5945 Add End
        // QC#7966
        ssmParam.put("gnrnTpCdCurrent", GNRN_TP.CURRENT);
        ssmParam.put("gnrnTpCdFuture", GNRN_TP.FUTURE);

        return getSsmEZDClient().queryEZDMsgArray("getOrganizationChildren", ssmParam, sMsg.B);
    }

    /**
     * @param cMsg NMAL2520CMsg
     * @param sMsg NMAL2520SMsg
     * @return S21SsmEZDResult
     */

    public S21SsmEZDResult getResource(NMAL2520CMsg cMsg, NMAL2520SMsg sMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("globalCmpyCd", getGlobalCompanyCode());
        ssmParam.put(NMAL2520Constant.DB_PARAM_CMSG, cMsg);
        ssmParam.put("slsDt", ZYPDateUtil.getSalesDate());
        ssmParam.put("rowNum", NMAL2520Constant.DB_PARAM_ROWNUM_MAX);
        // 2016/03/31 CSA-QC#5945 Add Start
        ssmParam.put("gnrnTpDelete", GNRN_TP.DELETE);
        ssmParam.put("gnrnTpTerminated", GNRN_TP.TERMINATED);
        // 2016/03/31 CSA-QC#5945 Add End
        // QC#7966
        ssmParam.put("gnrnTpCdCurrent", GNRN_TP.CURRENT);
        ssmParam.put("gnrnTpCdFuture", GNRN_TP.FUTURE);
        // START 2017/06/14 J.Kim [QC#18924,ADD]
        ssmParam.put("limitCount", sMsg.C.length() + 1);
        // END 2017/06/14 J.Kim [QC#18924,ADD]

        return getSsmEZDClient().queryEZDMsgArray("getResource", ssmParam, sMsg.C);
    }

    /**
     * @param cMsg NMAL2520CMsg
     * @param sMsg NMAL2520SMsg
     * @return S21SsmEZDResult
     */

    public S21SsmEZDResult getResourceForBackPopup(NMAL2520CMsg cMsg, NMAL2520SMsg sMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("globalCmpyCd", getGlobalCompanyCode());
        ssmParam.put(NMAL2520Constant.DB_PARAM_CMSG, cMsg);
        ssmParam.put("slsDt", ZYPDateUtil.getSalesDate());
        ssmParam.put("rowNum", NMAL2520Constant.DB_PARAM_ROWNUM_MAX);

        return getSsmEZDClient().queryEZDMsgArray("getResourceForBackFromPopup", ssmParam, sMsg.C);
    }

    /**
     * @param cMsg NMAL2520CMsg
     * @param sMsg NMAL2520SMsg
     * @return S21SsmEZDResult
     */

    public S21SsmEZDResult getOrganizationHierarchy(NMAL2520CMsg cMsg, NMAL2520SMsg sMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("globalCmpyCd", getGlobalCompanyCode());
        ssmParam.put(NMAL2520Constant.DB_PARAM_CMSG, cMsg);
        ssmParam.put("slsDt", ZYPDateUtil.getSalesDate());
        ssmParam.put("rowNum", NMAL2520Constant.DB_PARAM_ROWNUM_MAX);
        ssmParam.put("gnrnTpCd_Current", GNRN_TP.CURRENT);
        ssmParam.put("gnrnTpCd_Future", GNRN_TP.FUTURE);
        ssmParam.put("firstTierCd", NMAL2520Constant.FIRST_TIER_CD);

        return getSsmEZDClient().queryEZDMsgArray("getOrganizationHierarchy", ssmParam, sMsg.T);
    }

    /**
     * @param cMsg NMAL2520CMsg
     * @param idx Integer
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult checkSalesRoll(NMAL2520SMsg sMsg, Integer idx) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("trtyStruFlg", ZYPConstant.FLG_OFF_N);
        ssmParam.put("orgFuncRoleTpCd", sMsg.C.no(idx).orgFuncRoleTpCd_P1.getValue());

        return getSsmEZDClient().queryObject("checkSalesRoll", ssmParam);
    }

    /**
     * @param cMsg NMAL2520CMsg
     * @param idx Integer
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult checkActiveSalesRepRoll(NMAL2520SMsg sMsg, Integer idx) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("trtyStruFlg", ZYPConstant.FLG_OFF_N);
        ssmParam.put("orgFuncRoleTpCd", sMsg.C.no(idx).orgFuncRoleTpCd_P1.getValue());

        return getSsmEZDClient().queryObject("checkActiveSalesRepRoll", ssmParam);
    }

    /**
     * @param cMsg NMAL2520CMsg
     * @param psnCd String
     * @param bizAreaOrgCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getActiveSalesRepRollByPsnCd(NMAL2520CMsg cMsg, String psnCd, String bizAreaOrgCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("psnCd", psnCd);
        ssmParam.put("bizAreaOrgCd", bizAreaOrgCd);

        return getSsmEZDClient().queryObjectList("getActiveSalesRepRollByPsnCd", ssmParam);
    }

    /**
     * @param cMsg NMAL2520CMsg
     * @param idx Integer
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult checkActiveResourceRevenue(NMAL2520SMsg sMsg, Integer idx) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("psnCd", sMsg.C.no(idx).psnCd_C1.getValue());
        ssmParam.put("slsDt", ZYPDateUtil.getSalesDate());

        return getSsmEZDClient().queryObjectList("checkActiveResourceRevenue", ssmParam);
    }

    /**
     * @param sMsg NMAL2520SMsg
     * @param idx Integer
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getResourceAdditional(NMAL2520CMsg cMsg, Integer idx) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("psnCd", cMsg.C.no(idx).psnCd_C1.getValue());
        ssmParam.put("gnrnTpCd_Current", GNRN_TP.CURRENT);
        ssmParam.put("gnrnTpCd_Future", GNRN_TP.FUTURE);
        ssmParam.put("slsDt", ZYPDateUtil.getSalesDate());

        return getSsmEZDClient().queryObjectList("getResourceAdditional", ssmParam);
    }

    /**
     * @param cMsg NMAL2520CMsg
     * @param idx Integer
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getOrganizationAdditional(NMAL2520CMsg cMsg, Integer idx) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("orgCd", cMsg.A.no(idx).orgCd_A1.getValue());
        ssmParam.put("trtyStruFlg", ZYPConstant.FLG_OFF_N);

        return getSsmEZDClient().queryObjectList("getOrganizationAdditional", ssmParam);
    }

    // QC#5672
    /**
     * @param cMsg NMAL2520CMsg
     * @param idx Integer
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getOrganizationByName(NMAL2520CMsg cMsg, Integer idx) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("orgNm", cMsg.A.no(idx).orgNm_A1.getValue());
        ssmParam.put("trtyStruFlg", ZYPConstant.FLG_OFF_N);

        return getSsmEZDClient().queryObjectList("getOrganizationByName", ssmParam);
    }

    /**
     * @param cMsg NMAL2520CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getOrganizationAdditional(NMAL2520CMsg cMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("orgCd", cMsg.orgCd_H1.getValue());
        ssmParam.put("trtyStruFlg", ZYPConstant.FLG_OFF_N);

        return getSsmEZDClient().queryObjectList("getOrganizationAdditional", ssmParam);
    }

    /**
     * @param cMsg NMAL2520CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getOrganizationForAddChild(NMAL2520CMsg cMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("orgNm", cMsg.orgNm_H1.getValue());
        ssmParam.put("trtyStruFlg", ZYPConstant.FLG_OFF_N);

        return getSsmEZDClient().queryObject("getOrganizationForAddChild", ssmParam);
    }

    /**
     * @param cMsg NMAL2520CMsg
     * @param sMsg NMAL2520SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getTierDescription(NMAL2520CMsg cMsg, NMAL2520SMsg sMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        // 2017/09/28 QC#21344 Add Start
        if (!ZYPCommonFunc.hasValue(cMsg.orgTierCd_P1)) {
            cMsg.orgTierCd_P1.setValue(ORG_TIER._1);
        }
        // 2017/09/28 QC#21344 Add End
        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put(NMAL2520Constant.DB_PARAM_CMSG, cMsg);
        ssmParam.put("slsDt", ZYPDateUtil.getSalesDate());

        return getSsmEZDClient().queryObjectList("getTierDescription", ssmParam);

    }

    /**
     * @param cMsg NMAL2520CMsg
     * @param sMsg NMAL2520SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getZeroTierOrg(NMAL2520CMsg cMsg, NMAL2520SMsg sMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put(NMAL2520Constant.DB_PARAM_CMSG, cMsg);
        ssmParam.put("trtyStruFlg", ZYPConstant.FLG_OFF_N);
        ssmParam.put("orgTierCd", NMAL2520Constant.ZEROTH_TIER_CD);

        return getSsmEZDClient().queryObjectList("getZeroTierOrg", ssmParam);

    }

    /**
     * getDefaultRevenue
     * @param cMsg NMAL2520CMsg
     * @param i int
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDefaultRevenue(NMAL2520SMsg sMsg, int i) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("psnCd", sMsg.C.no(i).psnCd_C1.getValue());
        ssmParam.put("revCoaFlg", ZYPConstant.FLG_ON_Y);
        ssmParam.put("slsDt", ZYPDateUtil.getSalesDate());

        return getSsmEZDClient().queryObjectList("getDefaultRevenue", ssmParam);
    }

    /**
     * isManagerRole
     * @param cMsg NMAL2520CMsg
     * @param i int
     * @return S21SsmEZDResult
     */
    public boolean isManagerRole(NMAL2520SMsg sMsg, int i) {
        // 2016/02/29 CSA-QC#3859 Add Start
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("orgFuncRoleTpCd", sMsg.C.no(i).orgFuncRoleTpCd_P1.getValue());
        ssmParam.put("flgOnY", ZYPConstant.FLG_ON_Y);

        return (Integer) getSsmEZDClient().queryObject("isManagerRole", ssmParam).getResultObject() > 0;
        // 2016/02/29 CSA-QC#3859 Add End
    }

    /**
     * isOrgCurrentOrFuture
     * @param cMsg NMAL2520CMsg
     * @return S21SsmEZDResult
     */
    public boolean isOrgCurrentOrFuture(NMAL2520CMsg cMsg) {
        // 2016/03/04 CSA-QC#4654 Add Start
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("orgCd", cMsg.orgCd_H1.getValue());
        ssmParam.put("gnrnTp_Current", GNRN_TP.CURRENT);
        ssmParam.put("gnrnTp_Future", GNRN_TP.FUTURE);
        ssmParam.put("maxEffDate", NMAL2520Constant.DB_PARAM_MAX_EFF_DT);
        ssmParam.put("salesDate", ZYPDateUtil.getSalesDate());

        return (Integer) getSsmEZDClient().queryObject("isOrgCurrentOrFuture", ssmParam).getResultObject() > 0;
        // 2016/03/04 CSA-QC#4654 Add Start
    }

    /**
     * getTopTierParentOrganization
     * @param cMsg NMAL2520CMsg
     * @param i int
     * @param isCheckByParentOrg boolean
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getTopTierParentOrganization(NMAL2520CMsg cMsg, int i, boolean isCheckByParentOrg) {
        // 2016/04/13 CSA-QC#6960 Add Start
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("gnrnTp_Current", GNRN_TP.CURRENT);
        ssmParam.put("gnrnTp_Future", GNRN_TP.FUTURE);
        ssmParam.put("maxEffDt", NMAL2510Constant.MAX_EFF_THRU_DT);
        ssmParam.put("slsDt", ZYPDateUtil.getSalesDate());
        if (isCheckByParentOrg) {
            ssmParam.put("orgCd", cMsg.A.no(i).orgCd_A1.getValue());
        } else {
            ssmParam.put("orgCd", cMsg.orgCd_H1.getValue());
        }

        return getSsmEZDClient().queryObject("getTopTierParentOrganization", ssmParam);
        // 2016/04/13 CSA-QC#6960 Add End
    }

    // Add Start 2018/01/30 QC#22699
    /**
     * @param cMsg NMAL2520CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDeleteZeroTierReln(NMAL2520CMsg cMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("orgTierCd", NMAL2520Constant.ZEROTH_TIER_CD);
        ssmParam.put("gnrnTpCdCurrent", GNRN_TP.CURRENT);
        ssmParam.put("gnrnTpCdFuture", GNRN_TP.FUTURE);
        ssmParam.put("trtyStruFlg", ZYPConstant.FLG_OFF_N);
        ssmParam.put("orgCd", cMsg.orgCd_H1.getValue());

        return getSsmEZDClient().queryObject("getDeleteZeroTierReln", ssmParam);
    }
    // Add End 2018/01/30 QC#22699
}
