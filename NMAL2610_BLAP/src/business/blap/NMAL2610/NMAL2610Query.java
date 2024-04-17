/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL2610;

import java.util.HashMap;
import java.util.Map;

import business.blap.NMAL2610.constant.NMAL2610Constant;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BIZ_AREA_ORG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.GNRN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORG_STRU_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * <pre>
 * Business ID : NMAL2610 Territory Detail
 * Function Name : the data base access processing by SSM
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/01   Fujitsu         K.Koitabashi    Create          N/A
 * 2016/03/10   Fujitsu         C.Yokoi         Update          CSA-QC#5221
 * 2016/03/11   Fujitsu         C.Yokoi         Update          CSA-QC#5308, CSA-QC#5363
 * 2016/03/31   Fujitsu         C.Yokoi         Update          CSA-QC#5945
 * 2016/04/05   Fujitsu         C.Yokoi         Update          CSA-QC#6003
 * 2016/06/24   Hitachi         A.Kohinata      Update          CSA-QC#10276
 * 2016/06/27   Hitachi         A.Kohinata      Update          CSA-QC#10365
 * 2016/06/29   Hitachi         A.Kohinata      Update          CSA-QC#11087
 * 2016/07/07   Fujitsu         C.Tanaka        Update          CSA-QC#11178
 * 2016/08/29   SRAA            Y.Chen          Update          CSA-QC#7966
 * 2016/09/14   SRAA            Y.Chen          Update          QC#11638
 * 2017/03/03   Fujitsu         R.Nakamura      Update          QC#15878
 * 2017/08/21   Fujitsu         A.Kosai         Update          QC#20273
 * 2018/06/01   Fujitsu         Hd.Sugawara     Update          QC#24293
 * 2019/05/13   Fujitsu         T.Noguchi       Update          QC#50128
 * 2019/09/05   Fujitsu         Hd.Sugawara     Update          QC#51704
 *</pre>
 */
public final class NMAL2610Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NMAL2610Query MY_INSTANCE = new NMAL2610Query();

    /**
     * Constructor.
     */
    private NMAL2610Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NLBL0020Query
     */
    public static NMAL2610Query getInstance() {
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
    public S21SsmEZDResult getTrtyRulePulldownList(Map<String, Object> ssmParam) {

        return getSsmEZDClient().queryObjectList("getTrtyRulePulldownList", ssmParam);
    }

    /**
     * getTerritory
     * @param cMsg NMAL2610CMsg
     * @param sMsg NMAL2610SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getTerritory(NMAL2610CMsg cMsg, NMAL2610SMsg sMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("cMsg", cMsg);
        ssmParam.put("gnrnTpCdCurrent", GNRN_TP.CURRENT);
        ssmParam.put("gnrnTpCdFuture", GNRN_TP.FUTURE);
        ssmParam.put("trtyStruFlg", ZYPConstant.FLG_ON_Y);
        ssmParam.put("maxeffThruDt", NMAL2610Constant.MAX_EFF_THRU_DT);

        return getSsmEZDClient().queryEZDMsg("getTerritory", ssmParam, sMsg);
    }

    /**
     * getTerritoryParent
     * @param cMsg NMAL2610CMsg
     * @param sMsg NMAL2610SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getTerritoryParent(NMAL2610CMsg cMsg, NMAL2610SMsg sMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("cMsg", cMsg);
        ssmParam.put("gnrnTpCdCurrent", GNRN_TP.CURRENT);
        ssmParam.put("gnrnTpCdFuture", GNRN_TP.FUTURE);
        // 2016/03/31 CSA-QC#5945 Add Start
        ssmParam.put("gnrnTpCdDelete", GNRN_TP.DELETE);
        ssmParam.put("gnrnTpCdTerminated", GNRN_TP.TERMINATED);
        // 2016/03/31 CSA-QC#5945 Add End
        ssmParam.put("trtyStruFlg", ZYPConstant.FLG_ON_Y);
        ssmParam.put("firstTierCd", NMAL2610Constant.FIRST_TIER_CD);
        ssmParam.put("slsDt", ZYPDateUtil.getSalesDate());
        ssmParam.put("maxeffThruDt", NMAL2610Constant.MAX_EFF_THRU_DT);

        ssmParam.put("filter", ZYPConstant.FLG_OFF_N);

        return getSsmEZDClient().queryEZDMsgArray("getTerritoryParent", ssmParam, sMsg.A);
    }

    /**
     * getTerritoryParentWithFilter
     * @param cMsg NMAL2610CMsg
     * @param sMsg NMAL2610SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getTerritoryParentWithFilter(NMAL2610CMsg cMsg, NMAL2610SMsg sMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("cMsg", cMsg);
        ssmParam.put("gnrnTpCdCurrent", GNRN_TP.CURRENT);
        ssmParam.put("gnrnTpCdFuture", GNRN_TP.FUTURE);
        // 2016/06/24 QC#10276 Add Start
        ssmParam.put("gnrnTpCdDelete", GNRN_TP.DELETE);
        ssmParam.put("gnrnTpCdTerminated", GNRN_TP.TERMINATED);
        // 2016/06/24 QC#10276 Add End
        ssmParam.put("trtyStruFlg", ZYPConstant.FLG_ON_Y);
        ssmParam.put("firstTierCd", NMAL2610Constant.FIRST_TIER_CD);
        ssmParam.put("slsDt", ZYPDateUtil.getSalesDate());
        ssmParam.put("maxeffThruDt", NMAL2610Constant.MAX_EFF_THRU_DT);

        ssmParam.put("filter", ZYPConstant.FLG_ON_Y);

        return getSsmEZDClient().queryEZDMsgArray("getTerritoryParent", ssmParam, sMsg.A);
    }

    /**
     * getTerritoryChild
     * @param cMsg NMAL2610CMsg
     * @param sMsg NMAL2610SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getTerritoryChild(NMAL2610CMsg cMsg, NMAL2610SMsg sMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("cMsg", cMsg);
        ssmParam.put("gnrnTpCdCurrent", GNRN_TP.CURRENT);
        ssmParam.put("gnrnTpCdFuture", GNRN_TP.FUTURE);
        // 2016/03/31 CSA-QC#5945 Add Start
        ssmParam.put("gnrnTpCdDelete", GNRN_TP.DELETE);
        ssmParam.put("gnrnTpCdTerminated", GNRN_TP.TERMINATED);
        // 2016/03/31 CSA-QC#5945 Add End
        ssmParam.put("trtyStruFlg", ZYPConstant.FLG_ON_Y);

        ssmParam.put("slsDt", ZYPDateUtil.getSalesDate());
        ssmParam.put("maxeffThruDt", NMAL2610Constant.MAX_EFF_THRU_DT);

        return getSsmEZDClient().queryEZDMsgArray("getTerritoryChild", ssmParam, sMsg.B);
    }

    /**
     * getTerritoryRules
     * @param cMsg NMAL2610CMsg
     * @param sMsg NMAL2610SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getTerritoryRules(NMAL2610CMsg cMsg, NMAL2610SMsg sMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("cMsg", cMsg);
        ssmParam.put("trtyStruFlg", ZYPConstant.FLG_ON_Y);
        ssmParam.put("slsDt", ZYPDateUtil.getSalesDate());
        ssmParam.put("maxeffThruDt", NMAL2610Constant.MAX_EFF_THRU_DT);

        // 2016/03/11 CSA-QC#5308 Mod Start
        if (NMAL2610Constant.LINK_COPY_TERRITORY.equals(cMsg.xxEventFlgTxt.getValue())) {
            ssmParam.put("chkBox", ZYPConstant.FLG_ON_Y);
        } else {
            // QC#7966
            // ssmParam.put("chkBox", cMsg.xxChkBox_H1.getValue());
            ssmParam.put("chkBox", cMsg.xxChkBox_F2.getValue());
        }
        // 2016/03/11 CSA-QC#5308 Mod End

        // 2019/05/13 QC#50128 Add Start
        ssmParam.put("rowNum", sMsg.C.length() + 1);
        // 2019/05/13 QC#50128 Add End

        return getSsmEZDClient().queryEZDMsgArray("getTerritoryRules", ssmParam, sMsg.C);
    }
    
    // QC#7966
    /**
     * getTerritoryRules
     * @param cMsg NMAL2610CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getTerritoryRulesWithoutFilter(NMAL2610CMsg cMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("cMsg", cMsg);
        ssmParam.put("trtyStruFlg", ZYPConstant.FLG_ON_Y);
        ssmParam.put("slsDt", ZYPDateUtil.getSalesDate());
        ssmParam.put("maxeffThruDt", NMAL2610Constant.MAX_EFF_THRU_DT);
        // QC#11638
        // ssmParam.put("chkBox", cMsg.xxChkBox_F2.getValue());
        ssmParam.put("chkBox", ZYPConstant.FLG_ON_Y);
        return getSsmEZDClient().queryObjectList("getTerritoryRulesWithoutFilter", ssmParam);
    }
    
    /**
     * getResource
     * @param cMsg NMAL2610CMsg
     * @param sMsg NMAL2610SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getResource(NMAL2610CMsg cMsg, NMAL2610SMsg sMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("cMsg", cMsg);
        ssmParam.put("gnrnTpCdCurrent", GNRN_TP.CURRENT);
        ssmParam.put("gnrnTpCdFuture", GNRN_TP.FUTURE);
        // 2016/03/31 CSA-QC#5945 Add Start
        ssmParam.put("gnrnTpCdDelete", GNRN_TP.DELETE);
        ssmParam.put("gnrnTpCdTerminated", GNRN_TP.TERMINATED);
        // 2016/03/31 CSA-QC#5945 Add End
        ssmParam.put("trtyStruFlg", ZYPConstant.FLG_ON_Y);
        ssmParam.put("slsDt", ZYPDateUtil.getSalesDate());
        ssmParam.put("maxeffThruDt", NMAL2610Constant.MAX_EFF_THRU_DT);

        // 2016/03/11 CSA-QC#5363 Mod Start
        if (NMAL2610Constant.LINK_COPY_TERRITORY.equals(cMsg.xxEventFlgTxt.getValue())) {
            ssmParam.put("copyTrtyFlg", ZYPConstant.FLG_ON_Y);
        }
        // 2016/03/11 CSA-QC#5363 Mod End

        return getSsmEZDClient().queryEZDMsgArray("getResource", ssmParam, sMsg.D);
    }

    /**
     * getTerritoryAdditional
     * @param cMsg NMAL2610CMsg
     * @param sMsg NMAL2610SMsg
     * @param index index
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getTerritoryAdditional(NMAL2610CMsg cMsg, NMAL2610SMsg sMsg, int index) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("cMsg", cMsg);
        ssmParam.put("gnrnTpCdCurrent", GNRN_TP.CURRENT);
        ssmParam.put("gnrnTpCdFuture", GNRN_TP.FUTURE);
        ssmParam.put("trtyStruFlg", ZYPConstant.FLG_ON_Y);
        ssmParam.put("maxeffThruDt", NMAL2610Constant.MAX_EFF_THRU_DT);
        ssmParam.put("orgCd", cMsg.A.no(index).orgCd_A1.getValue());

        return getSsmEZDClient().queryObjectList("getTerritoryAdditional", ssmParam);
    }
    /**
     * getResourceAdditional
     * @param cMsg NMAL2610CMsg
     * @param sMsg NMAL2610SMsg
     * @param index index
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getResourceAdditional(NMAL2610CMsg cMsg, NMAL2610SMsg sMsg, int index) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("cMsg", cMsg);
        ssmParam.put("gnrnTpCdCurrent", GNRN_TP.CURRENT);
        ssmParam.put("gnrnTpCdFuture", GNRN_TP.FUTURE);
        ssmParam.put("rgtnStsCdP20", RGTN_STS.READY_FOR_ORDER_TAKING);
        ssmParam.put("rgtnStsCdP01", RGTN_STS.PENDING_COMPLETION);
        ssmParam.put("psnCd", cMsg.D.no(index).psnCd_D1.getValue());

        return getSsmEZDClient().queryObjectList("getResourceAdditional", ssmParam);
    }

    /**
     * @param cMsg NMAL2610CMsg
     * @param sMsg NMAL2610SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getZeroTierOrg(NMAL2610CMsg cMsg, NMAL2610SMsg sMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put(NMAL2610Constant.DB_PARAM_CMSG, cMsg);
        ssmParam.put("trtyStruFlg", ZYPConstant.FLG_ON_Y);
        ssmParam.put("orgTierCd", NMAL2610Constant.ZEROTH_TIER_CD);

        return getSsmEZDClient().queryObjectList("getZeroTierOrg", ssmParam);
    }

    // 2016/06/24 QC#10276 Add Start
    /**
     * getTerritoryByName
     * @param cMsg NMAL2520CMsg
     * @param index int
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getTerritoryByName(NMAL2610CMsg cMsg, int index) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("orgNm", cMsg.A.no(index).orgNm_A1.getValue());
        ssmParam.put("effFromDt", cMsg.A.no(index).effFromDt_A1.getValue());
        ssmParam.put("effThruDt", cMsg.A.no(index).effThruDt_A1.getValue());
        ssmParam.put("maxeffThruDt", NMAL2610Constant.MAX_EFF_THRU_DT);
        ssmParam.put("trtyStruFlg", ZYPConstant.FLG_ON_Y);

        return getSsmEZDClient().queryObject("getTerritoryByName", ssmParam);
    }
    // 2016/06/24 QC#10276 Add End

    // 2016/06/27 QC#10365 Add Start
    /**
     * getTierDescription
     * @param cMsg NMAL2610CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getTierDescription(NMAL2610CMsg cMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("bizAreaOrgCd", cMsg.bizAreaOrgCd_P1.getValue());
        ssmParam.put("orgTierCd", cMsg.orgTierCd_P1.getValue());
        ssmParam.put("slsDt", ZYPDateUtil.getSalesDate());
        ssmParam.put("maxeffThruDt", NMAL2610Constant.MAX_EFF_THRU_DT);

        return getSsmEZDClient().queryObject("getTierDescription", ssmParam);
    }
    // 2016/06/27 QC#10365 Add End

    /**
     * checkActiveSalesRoll
     * @param cMsg NMAL2610CMsg
     * @param index int
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult checkActiveSalesRoll(NMAL2610CMsg cMsg, int index) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("psnCd", cMsg.D.no(index).psnCd_D1.getValue());
        ssmParam.put("effFromDt", cMsg.D.no(index).effFromDt_D1.getValue());
        ssmParam.put("maxEffThruDate", NMAL2610Constant.MAX_EFF_THRU_DT);
        ssmParam.put("orgStruTp", ORG_STRU_TP.BASIC_ORGANIZATION_STRUCTURE_FOR_SALES_SUMMARY);
        ssmParam.put("slsFlg", ZYPConstant.FLG_ON_Y);
        if (ZYPCommonFunc.hasValue(cMsg.D.no(index).effThruDt_D1)) {
            ssmParam.put("effThruDt", cMsg.D.no(index).effThruDt_D1.getValue());
        }

        return getSsmEZDClient().queryObject("checkActiveSalesRepRoll", ssmParam);
    }

    /**
     * checkActiveResource
     * @param cMsg NMAL2610CMsg
     * @param index int
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult checkActiveResource(NMAL2610CMsg cMsg, int index) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("psnCd"     , cMsg.D.no(index).psnCd_D1.getValue());
        ssmParam.put("effFromDt" , cMsg.D.no(index).effFromDt_D1.getValue());
        ssmParam.put("effThruDt" , cMsg.D.no(index).effThruDt_D1.getValue());
        ssmParam.put("maxEffDt"  , NMAL2610Constant.MAX_EFF_THRU_DT);

        return getSsmEZDClient().queryObject("checkActiveResource", ssmParam);
    }

    /**
     * alreadyTerritoryAssigned
     * @param cMsg NMAL2610CMsg
     * @param index int
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult alreadyTerritoryAssigned(NMAL2610CMsg cMsg, int index) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("gnrnTpCdCurrent", GNRN_TP.CURRENT);
        ssmParam.put("gnrnTpCdFuture", GNRN_TP.FUTURE);
        ssmParam.put("trtyStruFlg", ZYPConstant.FLG_ON_Y);
        ssmParam.put("psnCd", cMsg.D.no(index).psnCd_D1.getValue());
        ssmParam.put("orgCd", cMsg.orgCd_H1.getValue());

        return getSsmEZDClient().queryObject("alreadyTerritoryAssigned", ssmParam);
    }

    /**
     * @param cMsg NMAL2610CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult checkDuplicateTerritoryName(NMAL2610CMsg cMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("trtyStruFlg", ZYPConstant.FLG_ON_Y);
        ssmParam.put("orgNm", cMsg.orgNm_H1.getValue());

        return getSsmEZDClient().queryObject("checkDuplicateTerritoryName", ssmParam);
    }

    /**
     * @param cMsg NMAL2520CMsg
     * @param i int
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult checkActiveChildOrganization(NMAL2610CMsg cMsg, int i) {
        // 2016/03/10 CSA-QC#5221 Add Start
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("orgCd", cMsg.B.no(i).orgCd_B1.getValue());
        ssmParam.put("effThruDt", cMsg.B.no(i).effThruDt_B1.getValue());

        return getSsmEZDClient().queryObject("checkActiveChildOrganization", ssmParam);
        // 2016/03/10 CSA-QC#5221 Add End
    }

    /**
     * @param cMsg NMAL2520CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult checkTerritoryBizArea(NMAL2610CMsg cMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("orgStruTp_Territory", ORG_STRU_TP.TERRITORY_STRUCTURE);
        ssmParam.put("slsFlg", ZYPConstant.FLG_ON_Y);
        ssmParam.put("bizArea", cMsg.bizAreaOrgCd_P1.getValue());

        return getSsmEZDClient().queryObject("checkTerritoryBizArea", ssmParam);
    }

    /**
     * @param cMsg NMAL2520CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult checkSalesRepFlgOfTrtyGrp(NMAL2610CMsg cMsg) {
        // 2016/04/05 CSA-QC#6003 Add Start
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("flgOffN",    ZYPConstant.FLG_OFF_N);
        ssmParam.put("trtyGrpTp",  cMsg.trtyGrpTpCd_P1.getValue());

        return getSsmEZDClient().queryObject("checkSalesRepFlgOfTrtyGrp", ssmParam);
        // 2016/04/05 CSA-QC#6003 Add End
    }

    // 2016/06/29 CSA-QC#11087 Add Start
    /**
     * checkSalesRep
     * @param cMsg NMAL2610CMsg
     * @param index int
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult checkSalesRep(NMAL2610CMsg cMsg, int index) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("psnCd", cMsg.D.no(index).psnCd_D1.getValue());
        ssmParam.put("slsRepFlg", ZYPConstant.FLG_ON_Y);

        return getSsmEZDClient().queryObject("checkSalesRep", ssmParam);
    }
    // 2016/06/29 CSA-QC#11087 Add End

    // 2016/07/07 CSA-QC#11178 Add Start
    /**
     * checkSalesRoleAsgResrc
     * @param cMsg NMAL2610CMsg
     * @param index int
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult checkSalesRoleAsgResrc(NMAL2610CMsg cMsg, int index) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("psnCd", cMsg.D.no(index).psnCd_D1.getValue());
        ssmParam.put("gnrnTpCd_Current", GNRN_TP.CURRENT);
        ssmParam.put("gnrnTpCd_Future", GNRN_TP.FUTURE);
        ssmParam.put("rgtnStsCd_P01", RGTN_STS.PENDING_COMPLETION);
        ssmParam.put("rgtnStsCd_P20", RGTN_STS.READY_FOR_ORDER_TAKING);
        ssmParam.put("firstOrgCdSales", BIZ_AREA_ORG.SALES);

        return getSsmEZDClient().queryObject("checkSalesRoleAsgResrc", ssmParam);
    }
    // 2016/07/07 CSA-QC#11178 End Start

    // Add Start 2017/03/03 QC#15878
    // Del Start 2018/06/01 QC#24293
    ///**
    // * checkExistAcctNm
    // * @param getValue String
    // * @return S21SsmEZDResult
    // */
    //public S21SsmEZDResult checkExistAcctNm(String getValue) {
    //    Map<String, Object> ssmParam = new HashMap<String, Object>();
    //
    //    ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
    //    ssmParam.put("acctNm", getValue);
    //
    //    return getSsmEZDClient().queryObject("checkExistAcctNm", ssmParam);
    //}
    // Del End 2018/06/01 QC#24293

    // Add Start 2018/06/01 QC#24293
    /**
     * checkExistAcctNm
     * @param getValue String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult checkExistAcctNum(String getValue) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("acctNum", getValue);

        return getSsmEZDClient().queryObject("checkExistAcctNum", ssmParam);
    }
    // Add End 2018/06/01 QC#24293

    /**
     * checkExistAcctNm
     * @param getValue String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult checkExistCntyNm(String getValue) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("cntyNm", getValue);

        return getSsmEZDClient().queryObject("checkExistCntyNm", ssmParam);
    }

    /**
     * checkExistAcctNm
     * @param getValue String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult checkExistCtyAddr(String getValue) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("ctyAddr", getValue);

        return getSsmEZDClient().queryObject("checkExistCtyAddr", ssmParam);
    }
    // Add End 2017/03/03 QC#15878
    // 2017/08/21 QC#20273 Add Start
    /**
     * checkExistLocNum
     * @param getValue String
     * @param slsDt
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult checkExistLocNum(String getValue, String slsDt) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("slsDt", slsDt);
        ssmParam.put("locNum", getValue);
        ssmParam.put("rgtnStsCd", RGTN_STS.TERMINATED);

        return getSsmEZDClient().queryObject("checkExistlocNum", ssmParam);
    }
    // 2017/08/21 QC#20273 Add End

    // Add Start 2019/09/05 QC#51704
    /**
     * getTerritoryParentOverlap
     * @param prntOrgCd String
     * @param chldOrgCd String
     * @param pastOnlyFlag boolean
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getTerritoryParentOverlap(String prntOrgCd, String chldOrgCd, boolean pastOnlyFlag) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("gnrnTpCdPast", GNRN_TP.PAST);
        ssmParam.put("gnrnTpCdDelete", GNRN_TP.DELETE);
        ssmParam.put("gnrnTpCdTerminated", GNRN_TP.TERMINATED);
        ssmParam.put("trtyStruFlg", ZYPConstant.FLG_ON_Y);
        ssmParam.put("firstTierCd", NMAL2610Constant.FIRST_TIER_CD);
        ssmParam.put("slsDt", ZYPDateUtil.getSalesDate());
        ssmParam.put("maxEffThruDt", NMAL2610Constant.MAX_EFF_THRU_DT);

        ssmParam.put("prntOrgCd", prntOrgCd);
        ssmParam.put("chldOrgCd", chldOrgCd);
        if (pastOnlyFlag) {
            ssmParam.put("pastOnlyFlag", ZYPConstant.FLG_ON_Y);
        } else {
            ssmParam.put("pastOnlyFlag", ZYPConstant.FLG_OFF_N);
        }

        return getSsmEZDClient().queryObjectList("getTerritoryParentOverlap", ssmParam);
    }
    // Add End 2019/09/05 QC#51704
}
