/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0480;

import static business.blap.NSAL0480.constant.NSAL0480Constant.APL_ID;
import static business.blap.NSAL0480.constant.NSAL0480Constant.LIMIT_ROWNUM;

import java.util.HashMap;
import java.util.Map;

import business.blap.NSAL0480.constant.NSAL0480Constant.MDL_ACTV_FLG;
import business.blap.NSAL0480.constant.NSAL0480Constant.QUERY_PRM_ID;

import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/06   Fujitsu         M.Yamada        Create          N/A
 * 2015/10/05   Hitachi         Y.Tsuchimoto    Update          NA#CSA
 *</pre>
 */
public final class NSAL0480Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NSAL0480Query INSTANCE = new NSAL0480Query();

    /**
     * Private constructor
     */
    private NSAL0480Query() {
        super();
    }

    /**
     * Get instance
     * @return NSAL0480Query singleton instance
     */
    public static NSAL0480Query getInstance() {
        return INSTANCE;
    }

    /**
     * getMeterGroupList
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getMeterGroupList() {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(QUERY_PRM_ID.GLBL_CMPY_CD.getQueryPrm(), getGlobalCompanyCode());

        return getSsmEZDClient().queryObjectList("getMeterGroupList", params);
    }

    /**
     * getServiceSkillList
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getServiceSkillList() {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(QUERY_PRM_ID.GLBL_CMPY_CD.getQueryPrm(), getGlobalCompanyCode());

        return getSsmEZDClient().queryObjectList("getServiceSkillList", params);
    }

    /**
     * getInstallationRuleList
     * @param svcIstlRuleFlg String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getInstallationRuleList(String svcIstlRuleFlg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(QUERY_PRM_ID.GLBL_CMPY_CD.getQueryPrm(), getGlobalCompanyCode());
        params.put(QUERY_PRM_ID.SVC_ISTL_RULE_FLG.getQueryPrm(), svcIstlRuleFlg);

        return getSsmEZDClient().queryObjectList("getInstallationRuleList", params);
    }

    /**
     * getSearchData
     * @param bizMsg NSAL0480CMsg
     * @param glblMsg NSAL0480SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSearchData(NSAL0480CMsg bizMsg, NSAL0480SMsg glblMsg) {

        return getSsmEZDClient().queryObjectList("getSearchData" //
                , getSsmParam(bizMsg, glblMsg, LIMIT_ROWNUM + 1));

    }

    /**
     * getSsmParam
     * @param bizMsg NSAL0480CMsg
     * @param glblMsg NSAL0480SMsg
     * @param rownum int
     * @return Map<String, Object>
     */
    public static Map<String, Object> getSsmParam(NSAL0480CMsg bizMsg, NSAL0480SMsg glblMsg, int rownum) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(QUERY_PRM_ID.GLBL_CMPY_CD.getQueryPrm(), bizMsg.glblCmpyCd.getValue());
        params.put(QUERY_PRM_ID.MDL_ACTV_FLG_ACTIVE.getQueryPrm(), MDL_ACTV_FLG.Y.getmdlActvFlgNm());
        params.put(QUERY_PRM_ID.MDL_ACTV_FLG_IN_ACTIVE.getQueryPrm(), MDL_ACTV_FLG.N.getmdlActvFlgNm());
        params.put(QUERY_PRM_ID.MDL_DESC_TXT.getQueryPrm(), bizMsg.mdlDescTxt_H.getValue());
        params.put(QUERY_PRM_ID.MDL_ACTV_FLG.getQueryPrm(), bizMsg.mdlActvFlg_H.getValue());
        params.put(QUERY_PRM_ID.XX_CRAT_DT.getQueryPrm() //
                , S21StringUtil.concatStrings(bizMsg.xxCratDt_H.getValue(), "%"));
        params.put(QUERY_PRM_ID.SVC_SEG_CD.getQueryPrm(), bizMsg.svcSegCd_H.getValue());
        params.put(QUERY_PRM_ID.SVC_SKILL_NUM.getQueryPrm(), bizMsg.svcSkillNum_H.getValue());
        params.put(QUERY_PRM_ID.SVC_ISTL_RULE_NUM.getQueryPrm(), bizMsg.svcIstlRuleNum_HY.getValue());
        params.put(QUERY_PRM_ID.SVC_DEISTL_RULE_NUM.getQueryPrm(), bizMsg.svcIstlRuleNum_HN.getValue());
        params.put(QUERY_PRM_ID.SVC_ISTL_REQ_FLG_ON.getQueryPrm(), bizMsg.svcIstlReqFlg_HY.getValue());
        params.put(QUERY_PRM_ID.SVC_ISTL_REQ_FLG_OFF.getQueryPrm(), bizMsg.svcIstlReqFlg_HN.getValue());
        params.put(QUERY_PRM_ID.T_MDL_NM.getQueryPrm(), bizMsg.t_MdlNm_H.getValue());
        params.put(QUERY_PRM_ID.MDL_GRP_NM.getQueryPrm(), bizMsg.mdlGrpNm_H.getValue());
        params.put(QUERY_PRM_ID.MTR_GRP_PK.getQueryPrm(), bizMsg.mtrGrpPk_H.getValue());
        params.put(QUERY_PRM_ID.MDSE_CD.getQueryPrm(), bizMsg.t_ItemCd_H.getValue());
        params.put(QUERY_PRM_ID.SPLY_MDSE_CD.getQueryPrm(), bizMsg.mdseCd_H.getValue());
        params.put(QUERY_PRM_ID.IMG_SPLY_OEM_CD.getQueryPrm(), bizMsg.imgSplyOemCd_H.getValue());
        params.put(QUERY_PRM_ID.MSE_ITEM_CLS_TP_CD.getQueryPrm(), bizMsg.mdseItemClsTpCd_H.getValue());
        // 2015/10/05 CSA Y.Tsuchimoto Add Start
        params.put(QUERY_PRM_ID.CUST_ISTL_FLG_ON.getQueryPrm(), bizMsg.custIstlFlg_HY.getValue());
        params.put(QUERY_PRM_ID.CUST_ISTL_FLG_OFF.getQueryPrm(), bizMsg.custIstlFlg_HN.getValue());
        params.put(QUERY_PRM_ID.SITE_SRVY_REQ_FLG_ON.getQueryPrm(), bizMsg.siteSrvyReqFlg_HY.getValue());
        params.put(QUERY_PRM_ID.SITE_SRVY_REQ_FLG_OFF.getQueryPrm(), bizMsg.siteSrvyReqFlg_HN.getValue());
        // 2015/10/05 CSA Y.Tsuchimoto Add End

        params.put(QUERY_PRM_ID.ONE.getQueryPrm(), 1);
        params.put(QUERY_PRM_ID.LEN.getQueryPrm(), bizMsg.getAttr("xxCratDt_H").getDigit());

        params.put(QUERY_PRM_ID.LIMIT_ROWNUM.getQueryPrm(), rownum);

        return params;

    }

    /**
     * getSavedSearchOptionList
     * @param userId String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSavedSearchOptionList(String userId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(QUERY_PRM_ID.GLBL_CMPY_CD.getQueryPrm(), getGlobalCompanyCode());
        params.put(QUERY_PRM_ID.SRCH_OPT_APL_ID.getQueryPrm(), APL_ID);
        params.put(QUERY_PRM_ID.SRCH_OPT_USR_ID.getQueryPrm(), userId);

        return getSsmEZDClient().queryObjectList("getSavedSearchOptionList", params);
    }

    public S21SsmEZDResult getServiceSegmentList(int len) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(QUERY_PRM_ID.GLBL_CMPY_CD.getQueryPrm(), getGlobalCompanyCode());
        params.put(QUERY_PRM_ID.ONE.getQueryPrm(), 1);
        params.put(QUERY_PRM_ID.LEN.getQueryPrm(), len);

        return getSsmEZDClient().queryObjectList("getServiceSegmentList", params);
    }
}
