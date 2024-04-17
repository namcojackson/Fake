/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NMAL2620;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.GNRN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORG_STRU_TP;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Territory Mass Update
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/26   Hitachi         T.Mizuki        Create          N/A
 * 2016/04/27   Fujitsu         C.Yokoi         Update          N/A
 * 2017/09/21   Hitachi         J.Kim           Update          QC#21047
 *</pre>
 */
public final class NMAL2620Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NMAL2620Query INSTANCE = new NMAL2620Query();

    /**
     * Constructor.
     */
    private NMAL2620Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NMAL2620Query
     */
    public static NMAL2620Query getInstance() {
        return INSTANCE;
    }

    /**
     * <pre>
     * Get BizArea list
     * </pre>
     * @param queryParam SSM Query Parameter
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getBizAreaList(Map<String, Object> queryParam) {
        return getSsmEZDClient().queryObjectList("getBizAreaList", queryParam);
    }

    /**
     * <pre>
     * Get SelectMode list
     * </pre>
     * @param queryParam SSM Query Parameter
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSelectModeList(Map<String, Object> queryParam) {
        return getSsmEZDClient().queryObjectList("getSelectModeList", queryParam);
    }

    /**
     * getSearchData
     * @param cMsg NMAL2620CMsg
     * @param sMsg NMAL2620SMsg
     * @param cnt int
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSearchData(NMAL2620CMsg cMsg, NMAL2620SMsg sMsg, int cnt) {
        return getSsmEZDClient().queryEZDMsgArray("getSearchData", getSsmParam(cMsg, sMsg, cnt), sMsg.A);
    }

    /**
     * <pre>
     * checkMoveTerritory
     * </pre>
     * @param queryParam SSM Query Parameter
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult checkMoveTerritory(Map<String, Object> queryParam) {
        return getSsmEZDClient().queryObjectList("checkMoveTerritory", queryParam);
    }

    /**
     * <pre>
     * CheckEffectiveDate
     * </pre>
     * @param queryParam SSM Query Parameter
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult checkEffectiveDate(Map<String, Object> queryParam) {
        return getSsmEZDClient().queryObjectList("checkEffectiveDate", queryParam);
    }

    /**
     * <pre>
     * checkMoveToResrcTrtyRelnExist
     * </pre>
     * @param queryParam SSM Query Parameter
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult checkMoveToResrcTrtyRelnExist(Map<String, Object> queryParam) {
        return getSsmEZDClient().queryObjectList("checkMoveToResrcTrtyRelnExist", queryParam);
    }

    /**
     * <pre>
     * CheckBusinessArea
     * </pre>
     * @param queryParam SSM Query Parameter
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult checkBusinessArea(Map<String, Object> queryParam) {
        return getSsmEZDClient().queryObjectList("checkBusinessArea", queryParam);
    }

    /**
     * <pre>
     * checkTargetTerritories
     * </pre>
     * @param queryParam SSM Query Parameter
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult checkTargetTerritories(Map<String, Object> queryParam) {
        return getSsmEZDClient().queryObjectList("checkTargetTerritories", queryParam);
    }

    /**
     * <pre>
     * getEffFrDtRsrcRltn
     * </pre>
     * @param queryParam SSM Query Parameter
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getEffFrDtRsrcRltn(Map<String, Object> queryParam) {
        return getSsmEZDClient().queryObjectList("getEffFrDtRsrcRltn", queryParam);
    }

    /**
     * <pre>
     * getRelnDt
     * </pre>
     * @param queryParam SSM Query Parameter
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRelnDt(Map<String, Object> queryParam) {
        return getSsmEZDClient().queryObjectList("getRelnDt", queryParam);
    }

    /**
     * <pre>
     * getEffFrDtTrrtryRl
     * </pre>
     * @param queryParam SSM Query Parameter
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getEffFrDtTrrtryRl(Map<String, Object> queryParam) {
        return getSsmEZDClient().queryObjectList("getEffFrDtTrrtryRl", queryParam);
    }

    /**
     * <pre>
     * checkActiveChildOrganization
     * </pre>
     * @param queryParam SSM Query Parameter
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult checkActiveChildOrganization(Map<String, Object> queryParam) {
        return getSsmEZDClient().queryObjectList("checkActiveChildOrganization", queryParam);
    }

    /**
     * <pre>
     * checkParentRelation
     * </pre>
     * @param queryParam SSM Query Parameter
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult checkParentRelation(Map<String, Object> queryParam) {
        return getSsmEZDClient().queryObjectList("checkParentRelation", queryParam);
    }

    /**
     * getSsmParam
     * @param cMsg NMAL2620CMsg
     * @param sMsg NMAL2620SMsg
     * @param cnt int
     * @return Map<String, Object>
     */
    private static Map<String, Object> getSsmParam(NMAL2620CMsg cMsg, NMAL2620SMsg sMsg, int cnt) {
        Map<String, Object> params = new HashMap<String, Object>();

        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("psnNum", cMsg.psnNum_H.getValue());
        params.put("psnCd", cMsg.psnCd.getValue());
        params.put("xxPsnNm", cMsg.xxPsnNm_H.getValue());
        List<String> gnrnTpCd = new ArrayList<String>();
        gnrnTpCd.add(GNRN_TP.CURRENT);
        gnrnTpCd.add(GNRN_TP.FUTURE);
        params.put("gnrnTpCd", gnrnTpCd);
        params.put("orgNm", cMsg.orgNm.getValue());
        params.put("trtyStruFlg", ZYPConstant.FLG_ON_Y);
        params.put("bizAreaOrgCd", cMsg.bizAreaOrgCd_V.getValue());
        params.put("slsDate", ZYPDateUtil.getSalesDate());
        params.put("hitDate", "99991231");
        params.put("orgStruTpCd",  ORG_STRU_TP.BASIC_ORGANIZATION_STRUCTURE_FOR_SALES_SUMMARY);
        params.put("slsFlg", ZYPConstant.FLG_ON_Y);
        // START 2017/09/21 J.Kim [QC#21047,ADD]
        if (!ZYPCommonFunc.hasValue(cMsg.psnNum_H) && !ZYPCommonFunc.hasValue(cMsg.xxPsnNm_H) && !ZYPCommonFunc.hasValue(cMsg.psnCd)) {
            params.put("slsRepFlg", ZYPConstant.FLG_ON_Y);
        }
        // END 2017/09/21 J.Kim [QC#21047,ADD]
        params.put("rowNum", cnt);
        return params;
    }

    /**
     * <pre>
     * getPsnNum
     * </pre>
     * @param queryParam SSM Query Parameter
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPsnNum(NMAL2620CMsg cMsg) {
        // 2016/04/27 Add Start
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("xxPsnNm", cMsg.xxPsnNm_D.getValue());
        return getSsmEZDClient().queryObjectList("getPsnNum", params);
        // 2016/04/27 Add End
    }
    
}
