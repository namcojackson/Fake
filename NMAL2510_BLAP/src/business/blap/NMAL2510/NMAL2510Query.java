/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL2510;

import java.util.HashMap;
import java.util.Map;

import business.blap.NMAL2510.constant.NMAL2510Constant;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.GNRN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORG_STRU_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORG_TIER;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * <pre>
 * Business ID : NMAL2510 Resource Maintenance
 * Function Name : the data base access processing by SSM
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/19   Fujitsu         K.Koitabashi    Create          N/A
 * 2016/02/17   Fujitsu         C.Yokoi         Update          CSA-QC#3857
 * 2016/02/25   Fujitsu         C.Yokoi         Update          CSA-QC#4627
 * 2016/02/29   Fujitsu         C.Yokoi         Update          CSA-QC#3859
 * 2016/03/01   Fujitsu         M.suzuki        Update          CSA-QC#4563
 * 2016/03/04   Fujitsu         C.Yokoi         Update          CSA-QC#4654
 * 2016/03/16   Fujitsu         C.Yokoi         Update          CSA-QC#5415
 * 2016/04/05   Fujitsu         C.Yokoi         Update          CSA-QC#5411, CSA-QC#6003
 * 2016/04/13   Fujitsu         C.Yokoi         Update          CSA-QC#6960
 * 2016/08/01   Fujitsu         C.Yokoi         Update          CSA-QC#11648
 * 2016/10/17   Fujitsu         N.Sugiura       Update          CSA-QC#15140
 * 2016/12/02   Fujitsu         N.Sugiura       Update          CSA-QC#15140-3
 * 2018/03/28   Fujitsu         M.Ohno          Update          QC#21410
 * 2018/03/29   Fujitsu         K.Ishizuka      Update          QC#23171
 *</pre>
 */
public final class NMAL2510Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NMAL2510Query MY_INSTANCE = new NMAL2510Query();

    /**
     * Constructor.
     */
    private NMAL2510Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NLBL0020Query
     */
    public static NMAL2510Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPsnTpPulldownList(Map<String, Object> ssmParam) {

        return getSsmEZDClient().queryObjectList("getPsnTpPulldownList", ssmParam);
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
    public S21SsmEZDResult getTrtyGrpTpPulldownList(Map<String, Object> ssmParam) {

        return getSsmEZDClient().queryObjectList("getTrtyGrpTpPulldownList", ssmParam);
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
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getBizAreaOrgTerritoryPulldownList(Map<String, Object> ssmParam) {

        return getSsmEZDClient().queryObjectList("getBizAreaOrgPulldownList", ssmParam);
    }

    /**
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getAcctTeamRoleTpPulldownList(Map<String, Object> ssmParam) {

        return getSsmEZDClient().queryObjectList("getAcctTeamRoleTpPulldownList", ssmParam);
    }

    /**
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRevAcctTpPulldownList(Map<String, Object> ssmParam) {

        return getSsmEZDClient().queryObjectList("getRevAcctTpPulldownList", ssmParam);
    }

    /**
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getTmZonePulldownList(Map<String, Object> ssmParam) {

        return getSsmEZDClient().queryObjectList("getTmZonePulldownList", ssmParam);
    }

    /**
     * getPerson
     * @param cMsg NMAL2510CMsg
     * @param sMsg NMAL2510SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPerson(NMAL2510CMsg cMsg, NMAL2510SMsg sMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("slsDt", ZYPDateUtil.getSalesDate());
        ssmParam.put("cMsg", cMsg);

        return getSsmEZDClient().queryEZDMsg("getPerson", ssmParam, sMsg);
    }

    /**
     * getOrganization
     * @param cMsg NMAL2510CMsg
     * @param sMsg NMAL2510SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getOrganization(NMAL2510CMsg cMsg, NMAL2510SMsg sMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put(NMAL2510Constant.DB_PARAM_CMSG, cMsg);
        ssmParam.put("slsDt", ZYPDateUtil.getSalesDate());
        ssmParam.put("gnrnTpDelete", GNRN_TP.DELETE);
        ssmParam.put("gnrnTpTerminated", GNRN_TP.TERMINATED);
        // 2018/03/28 QC#21410 add start
        ssmParam.put("gnrnTpPast", GNRN_TP.PAST);
        // 2018/03/28 QC#21410 ad endd
        ssmParam.put("showAllAssignments", cMsg.xxChkBox_A1.getValue());
        ssmParam.put("rowNum", NMAL2510Constant.DB_PARAM_ROWNUM_MAX);

        return getSsmEZDClient().queryEZDMsgArray("getOrganization", ssmParam, sMsg.A);
    }

    /**
     * getOrganizationHierarchy
     * @param orgCd String
     * @param orgNm String
     * @param bizAreaOrgCd String
     * @param sMsg NMAL2510SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getOrganizationHierarchy(String orgCd, String orgNm, String bizAreaOrgCd, NMAL2510SMsg sMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("orgCd", orgCd);
        ssmParam.put("orgNm", orgNm);
        ssmParam.put("bizAreaOrgCd", bizAreaOrgCd);
        ssmParam.put("gnrnTpCd_Current", GNRN_TP.CURRENT);
        ssmParam.put("gnrnTpCd_Future", GNRN_TP.FUTURE); // 2016/02/17 CSA-QC#3857 Mod 

        // 2016/03/01 S21_NA#4653 Add Start ------------
        ssmParam.put("ortTier1", ORG_TIER._1);
        ssmParam.put("ortTier2", ORG_TIER._2);
        ssmParam.put("ortTier3", ORG_TIER._3);
        ssmParam.put("ortTier4", ORG_TIER._4);
        ssmParam.put("ortTier5", ORG_TIER._5);
        ssmParam.put("ortTier6", ORG_TIER._6);
        ssmParam.put("ortTier7", ORG_TIER._7);
        ssmParam.put("ortTier8", ORG_TIER._8);
        ssmParam.put("ortTier9", ORG_TIER._9);
        ssmParam.put("ortTier10", ORG_TIER._10);
        // 2016/03/01 S21_NA#4653 Add End --------------

        return getSsmEZDClient().queryEZDMsgArray("getOrganizationHierarchy", ssmParam, sMsg.T);
    }

    /**
     * getTerritory
     * @param cMsg NMAL2510CMsg
     * @param sMsg NMAL2510SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getTerritory(NMAL2510CMsg cMsg, NMAL2510SMsg sMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put(NMAL2510Constant.DB_PARAM_CMSG, cMsg);
        ssmParam.put("slsDt", ZYPDateUtil.getSalesDate());
        ssmParam.put("gnrnTpDelete", GNRN_TP.DELETE);
        ssmParam.put("gnrnTpTerminated", GNRN_TP.TERMINATED);
        // 2018/03/28 QC#21410 add start
        ssmParam.put("gnrnTpPast", GNRN_TP.PAST);
        // 2018/03/28 QC#21410 add end
        ssmParam.put("showAllAssignments", cMsg.xxChkBox_B1.getValue());
        ssmParam.put("rowNum", NMAL2510Constant.DB_PARAM_ROWNUM_MAX);

        return getSsmEZDClient().queryEZDMsgArray("getTerritory", ssmParam, sMsg.B);
    }

    /**
     * getRevenue
     * @param cMsg NMAL2510CMsg
     * @param sMsg NMAL2510SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRevenue(NMAL2510CMsg cMsg, NMAL2510SMsg sMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put(NMAL2510Constant.DB_PARAM_CMSG, cMsg);
        ssmParam.put("rowNum", NMAL2510Constant.DB_PARAM_ROWNUM_MAX);

        return getSsmEZDClient().queryEZDMsgArray("getRevenue", ssmParam, sMsg.C);
    }

    /**
     * checkBusinessAreaSalesForOrganization
     * @param cMsg NMAL2510CMsg
     * @param idx Integer
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult checkBusinessAreaSalesForOrganization(NMAL2510CMsg cMsg, Integer idx) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("trtyStruFlg", ZYPConstant.FLG_OFF_N);
        ssmParam.put("bizAreaOrgCd", cMsg.A.no(idx).bizAreaOrgCd_P2.getValue());

        return getSsmEZDClient().queryObject("checkBusinessAreaSales", ssmParam);
    }

    /**
     * checkBusinessAreaRoll
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
     * checkBusinessAreaOrg
     * @param bizAreaOrgCd String
     * @param orgCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult checkBusinessAreaOrg(String bizAreaOrgCd, String orgCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("trtyStruFlg", ZYPConstant.FLG_OFF_N);
        ssmParam.put("bizAreaOrgCd", bizAreaOrgCd);
        ssmParam.put("orgCd", orgCd);

        return getSsmEZDClient().queryObject("checkBusinessAreaOrg", ssmParam);
    }

    /**
     * checkBusinessAreaSalesForTerritory
     * @param cMsg NMAL2510CMsg
     * @param idx Integer
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult checkBusinessAreaSalesForTerritory(NMAL2510CMsg cMsg, Integer idx) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("trtyStruFlg", ZYPConstant.FLG_ON_Y);
        ssmParam.put("bizAreaOrgCd", cMsg.B.no(idx).bizAreaOrgCd_P3.getValue());

        return getSsmEZDClient().queryObject("checkBusinessAreaSales", ssmParam);
    }

    /**
     * checkSalesRoll
     * @param cMsg NMAL2510CMsg
     * @param idx Integer
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult checkSalesRoll(NMAL2510CMsg cMsg, Integer idx) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("trtyStruFlg", ZYPConstant.FLG_OFF_N);
        ssmParam.put("orgFuncRoleTpCd", cMsg.A.no(idx).orgFuncRoleTpCd_P2.getValue());

        return getSsmEZDClient().queryObject("checkSalesRoll", ssmParam);
    }

    // 2016/12/02 S21_NA#15140-3 Mod Start
    /**
     * checkActiveSalesRepRoll
     * @param cMsg NMAL2510CMsg
     * @param idx Integer
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult checkActiveSalesRepRoll(NMAL2510CMsg cMsg, Integer idx) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("orgFuncRoleTpCd", cMsg.A.no(idx).orgFuncRoleTpCd_P2.getValue());
        ssmParam.put("flgOffN", ZYPConstant.FLG_OFF_N);
        ssmParam.put("flgOnY", ZYPConstant.FLG_ON_Y);

        return getSsmEZDClient().queryObject("checkActiveSalesRepRoll", ssmParam);
    }
    // 2016/12/02 S21_NA#15140-3 Mod End

    /**
     * checkActiveResourceRevenue
     * @param cMsg NMAL2510CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult checkActiveResourceRevenue(NMAL2510CMsg cMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("psnCd", cMsg.psnCd_H1.getValue());
        ssmParam.put("slsDt", ZYPDateUtil.getSalesDate());

        return getSsmEZDClient().queryObjectList("checkActiveResourceRevenue", ssmParam);
    }

    /**
     * getHierachyDataforManualInput
     * @param cMsg NMAL2510CMsg
     * @param idx Integer
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getHierachyDataforManualInput(NMAL2510CMsg cMsg, Integer idx) {

        Map<String, String> queryParam = new HashMap<String, String>();

        queryParam.put("glblCmpyCd", getGlobalCompanyCode());
        queryParam.put("orgNm", cMsg.A.no(idx).orgNm_A2.getValue());
        queryParam.put("trtyStruFlg", ZYPConstant.FLG_OFF_N);
        queryParam.put("tierCd0", ORG_TIER._0);

        return getSsmEZDClient().queryObjectList("getHierachyDataforManualInput", queryParam);
    }

    /**
     * getTerritoryDataforManualInput
     * @param cMsg NMAL2510CMsg
     * @param idx Integer
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getTerritoryDataforManualInput(NMAL2510CMsg cMsg, Integer idx) {

        Map<String, String> queryParam = new HashMap<String, String>();

        queryParam.put("glblCmpyCd", getGlobalCompanyCode());
        queryParam.put("bizAreaOrgCd", cMsg.B.no(idx).bizAreaOrgCd_P3.getValue());
        queryParam.put("orgNm", cMsg.B.no(idx).orgNm_B2.getValue());
        queryParam.put("trtyStruFlg", ZYPConstant.FLG_ON_Y);

        return getSsmEZDClient().queryObjectList("getTerritoryDataforManualInput", queryParam);
    }

    /**
     * getOrganizationFunctionRoleTypeDataforManualInput
     * @param cMsg NMAL2510CMsg
     * @param idx Integer
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getOrganizationFunctionRoleTypeDataforManualInput(NMAL2510CMsg cMsg, Integer idx) {

        Map<String, String> queryParam = new HashMap<String, String>();

        queryParam.put("glblCmpyCd", getGlobalCompanyCode());
        // 2016/02/25 CSA-QC#4627 Mod Start
        queryParam.put("gnrnTpCd_Current", GNRN_TP.CURRENT);
        queryParam.put("gnrnTpCd_Future", GNRN_TP.FUTURE);
        queryParam.put("rgtnStsCd_ReadyForOrder", RGTN_STS.READY_FOR_ORDER_TAKING);
        queryParam.put("rgtnStsCd_Pending", RGTN_STS.PENDING_COMPLETION);
        // 2016/02/25 CSA-QC#4627 Mod End
        queryParam.put("psnCd", cMsg.psnCd_H1.getValue());

        return getSsmEZDClient().queryObjectList("getOrganizationFunctionRoleTypeDataforManualInput", queryParam);
    }

    /**
     * getContractTypeCd
     * @param cMsg NMAL2510CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getContractTypeCd(NMAL2510CMsg cMsg) {

        Map<String, String> queryParam = new HashMap<String, String>();

        queryParam.put("glblCmpyCd", getGlobalCompanyCode());
        queryParam.put("splyIndFlg", ZYPConstant.FLG_ON_Y);

        return getSsmEZDClient().queryObjectList("getContractTypeCd", queryParam);
    }

    /**
     * getThirdPtyTechFlg
     * @param orgFuncRole String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getThirdPtyTechFlg(String orgFuncRole) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("thirdPtyTechFlg", ZYPConstant.FLG_ON_Y);
        // 2016/04/05 CSA-QC#5411 Add Start
        ssmParam.put("orgFuncRoleTpCd", orgFuncRole);
        // 2016/04/05 CSA-QC#5411 Add End

        // 2016/04/05 CSA-QC#5411 Delete Start
        // ssmParam.put("psnCd", cMsg.psnCd_H1.getValue());
        // ssmParam.put("gnrnTpCd_Current", GNRN_TP.CURRENT);
        //  ssmParam.put("gnrnTpCd_Future", GNRN_TP.FUTURE);
        // 2016/04/05 CSA-QC#5411 Delete End

        return getSsmEZDClient().queryObject("getThirdPtyTechFlg", ssmParam);
    }

    /**
     * getSplyIndFlg
     * @param cMsg NMAL2510CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSplyIndFlg(NMAL2510CMsg cMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("ctacTpCd", cMsg.ctacTpCd_D1.getValue());
        ssmParam.put("splyIndFlg", ZYPConstant.FLG_ON_Y);

        return getSsmEZDClient().queryObject("getSplyIndFlg", ssmParam);
    }

    /**
     * getDefaultRevenue
     * @param cMsg NMAL2510CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDefaultRevenue(NMAL2510CMsg cMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("psnCd", cMsg.psnCd_H1.getValue());
        ssmParam.put("revCoaFlg", ZYPConstant.FLG_ON_Y);
        ssmParam.put("slsDt", ZYPDateUtil.getSalesDate());

        return getSsmEZDClient().queryObjectList("getDefaultRevenue", ssmParam);
    }

    /**
     * getDuplicatePsnNum
     * @param cMsg NMAL2510CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDuplicatePsnNum(NMAL2510CMsg cMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("psnNum", cMsg.psnNum_H1.getValue());
        ssmParam.put("slsDt", ZYPDateUtil.getSalesDate());

        return getSsmEZDClient().queryObject("getDuplicatePsnNum", ssmParam);
    }

    // 2016/12/02 S21_NA#15140-3 Mod Start
    /**
     * isManagerRole
     * @param cMsg NMAL2510CMsg
     * @param i int
     * @param condFlg String (Y:Manager, N:Sales Rep)
     * @return S21SsmEZDResult
     */
    public boolean isManagerRole(NMAL2510CMsg cMsg, int i, String condFlg)  {
        // 2016/02/29 CSA-QC#3859 Add Start
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("orgFuncRoleTpCd", cMsg.A.no(i).orgFuncRoleTpCd_P2.getValue());
        ssmParam.put("flgOnY", ZYPConstant.FLG_ON_Y);
        ssmParam.put("condFlg", condFlg);

        return (Integer) getSsmEZDClient().queryObject("isManagerRole", ssmParam).getResultObject() > 0;
        // 2016/02/29 CSA-QC#3859 Add End
    }

    /**
     * countManagerInSameOrganization
     * @param cMsg NMAL2510CMsg
     * @param i int
     * @param condFlg String (Y:Manager, N:Sales Rep)
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getMgrCntByOrg(NMAL2510CMsg cMsg, int i, String condFlg) {
        // 2016/02/29 CSA-QC#3859 Add Start
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("orgCd", cMsg.A.no(i).orgCd_A2.getValue());
        ssmParam.put("slsDt", ZYPDateUtil.getSalesDate());
        ssmParam.put("maxEffThruDate", NMAL2510Constant.MAX_EFF_THRU_DT);
        ssmParam.put("rgtnSts_Terminated", RGTN_STS.TERMINATED);
        ssmParam.put("condFlg", condFlg);
        ssmParam.put("flgOnY", ZYPConstant.FLG_ON_Y);

        // QC#3859
        return getSsmEZDClient().queryObjectList("getMgrCntByOrg", ssmParam);
        // 2016/02/29 CSA-QC#3859 Add End
    }
    // 2016/12/02 S21_NA#15140-3 Mod End


    /**
     * isOrgCurrentOrFuture
     * @param cMsg NMAL2510CMsg
     * @param i int
     * @return S21SsmEZDResult
     */
    public boolean isOrgCurrentOrFuture(NMAL2510CMsg cMsg, int i) {
        // 2016/03/04 CSA-QC#4654 Add Start
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("orgCd", cMsg.A.no(i).orgCd_A2.getValue());
        ssmParam.put("gnrnTp_Current", GNRN_TP.CURRENT);
        ssmParam.put("gnrnTp_Future", GNRN_TP.FUTURE);

        return (Integer) getSsmEZDClient().queryObject("isOrgCurrentOrFuture", ssmParam).getResultObject() > 0;
        // 2016/03/04 CSA-QC#4654 Add Start
    }

    /**
     * isTerritorySales
     * @param cMsg NMAL2510CMsg
     * @param i int
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult isTerritorySales(NMAL2510CMsg cMsg, int i) {
        // 2016/03/04 CSA-QC#4654 Add Start
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd"         , getGlobalCompanyCode());
        ssmParam.put("territoryId"        , cMsg.B.no(i).orgCd_B2.getValue());
        ssmParam.put("slsFlg"             , ZYPConstant.FLG_ON_Y);
        ssmParam.put("orgStruTp_Territory", ORG_STRU_TP.TERRITORY_STRUCTURE);

        return getSsmEZDClient().queryObject("isTerritorySales", ssmParam);
        // 2016/03/04 CSA-QC#4654 Add Start
    }

    /**
     * isOrgFuncRoleSales
     * @param cMsg NMAL2510CMsg
     * @param i int
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult isOrgFuncRoleSales(NMAL2510CMsg cMsg, int i) {
        // 2016/03/04 CSA-QC#4654 Add Start
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd"         , getGlobalCompanyCode());
        ssmParam.put("roleTp"             , cMsg.A.no(i).orgFuncRoleTpCd_P2.getValue());
        ssmParam.put("orgStruTp_BOS"      , ORG_STRU_TP.BASIC_ORGANIZATION_STRUCTURE_FOR_SALES_SUMMARY);
        ssmParam.put("flgOnY"             , ZYPConstant.FLG_ON_Y);

        return getSsmEZDClient().queryObject("isOrgFuncRoleSales", ssmParam);
        // 2016/03/04 CSA-QC#4654 Add End
    }

    /**
     * countSalesRepInTerritory
     * @param cMsg NMAL2510CMsg
     * @param i int
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult countSalesRepInTerritory(NMAL2510CMsg cMsg, int i) {
        // 2016/04/04 CSA-QC#6003 Add Start
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd"          , getGlobalCompanyCode());
        ssmParam.put("territoryId"         , cMsg.B.no(i).orgCd_B2.getValue());
        ssmParam.put("orgStruTp_Territory" , ORG_STRU_TP.TERRITORY_STRUCTURE);
        ssmParam.put("gnrnTp_Current"      , GNRN_TP.CURRENT);
        ssmParam.put("gnrnTp_Future"       , GNRN_TP.FUTURE);
        // 2016/08/01 CSA-QC#11648 Add Start
        if (ZYPCommonFunc.hasValue(cMsg.psnCd_H1)) {
            ssmParam.put("psnCd"           , cMsg.psnCd_H1.getValue());
        }
        // 2016/08/01 CSA-QC#11648 Add End
        ssmParam.put("resrcAssignStartDt"  , cMsg.B.no(i).effFromDt_B2.getValue());
        // 2018/03/29 S21_NA#23171 Mod Start
        //if (ZYPCommonFunc.hasValue(cMsg.B.no(i).effThruDt_B2.getValue())) {
            // ssmParam.put("resrcAssignEndDt", cMsg.B.no(i).effThruDt_B2.getValue());
        //}
        ssmParam.put("resrcAssignEndDt", cMsg.B.no(i).effThruDt_B2.getValue());
        // 2018/03/29 S21_NA#23171 Mod End
        ssmParam.put("maxEffDt"            , NMAL2510Constant.MAX_EFF_THRU_DT);
        ssmParam.put("flgOffN"             , ZYPConstant.FLG_OFF_N);

        return getSsmEZDClient().queryObject("countSalesRepInTerritory", ssmParam);
        // 2016/04/05 CSA-QC#6003 Add End
    }

    /**
     * getTopTierParentOrganization
     * @param cMsg NMAL2510CMsg
     * @param i int
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getTopTierParentOrganization(NMAL2510CMsg cMsg, int i) {
        // 2016/04/04 CSA-QC#6960 Add Start
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd"          , getGlobalCompanyCode());
        ssmParam.put("gnrnTp_Current"      , GNRN_TP.CURRENT);
        ssmParam.put("gnrnTp_Future"       , GNRN_TP.FUTURE);
        ssmParam.put("maxEffDt"            , NMAL2510Constant.MAX_EFF_THRU_DT);
        ssmParam.put("slsDt"               , ZYPDateUtil.getSalesDate());
        ssmParam.put("orgCd"               , cMsg.A.no(i).orgCd_A2.getValue());

        return getSsmEZDClient().queryObject("getTopTierParentOrganization", ssmParam);
        // 2016/04/05 CSA-QC#6960 Add End
    }
}
