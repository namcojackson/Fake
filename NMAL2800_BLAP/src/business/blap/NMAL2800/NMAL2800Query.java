/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL2800;

import static business.blap.NMAL2800.constant.NMAL2800Constant.PROS_RVW_STS_CD_ALL;
import static business.blap.NMAL2800.constant.NMAL2800Constant.PROS_RVW_STS_CD_BLANK;
import static business.blap.NMAL2800.constant.NMAL2800Constant.TM_TS_FROM;
import static business.blap.NMAL2800.constant.NMAL2800Constant.TM_TS_TO;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.GNRN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROS_RVW_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * NMAL2800Query
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/16   Fujitsu         C.Tanaka        Create          N/A
 * 2016/07/25   Fujitsu         N.Sugiura       Update          QC#12394
 * 2016/07/26   Fujitsu         N.Sugiura       Update          QC#12417
 * 2016/10/03   Hitachi         T.Mizuki        Update          QC#12418
 * 2017/06/15   Hitachi         E.Kameishi      Update          QC#18184
 * 2017/10/10   Fujitsu         M.Ohno          Update          QC#20662
 * 2017/12/14   Fujitsu         N.Sugiura       Update          QC#22244
 * 2018/01/16   Fujitsu         Hd.Sugawara     Update          QC#23148
 * 2018/03/28   Fujitsu         R.Nakamura      Update          QC#23149
 *</pre>
 */
public final class NMAL2800Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NMAL2800Query MY_INSTANCE = new NMAL2800Query();

    /**
     * Private constructor
     */
    private NMAL2800Query() {
        super();
    }

    /**
     * Get the NMAL2800Query instance.
     * @return NMAL2800Query instance
     */
    public static NMAL2800Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * Search
     * @param bizMsg NMAL2800CMsg
     * @param glblMsg NMAL2800SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult search(NMAL2800CMsg bizMsg, NMAL2800SMsg glblMsg) {
        Map<String, Object> params = new HashMap<String, Object>();

        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("bizMsg", bizMsg);
        params.put("rowNum", glblMsg.A.length() + 1);
        // mod start 2016/10/03 CSA QC#12418
        params.put("gnrnTpCd", GNRN_TP.CURRENT);
        // mod end 2016/10/03 CSA QC#12418
        // START 2017/06/15 E.Kameishi [QC#18184,ADD]
        params.put("tmTsFrom", bizMsg.xxDt10Dt_H1.getValue() + TM_TS_FROM);
        params.put("tmTsTo", bizMsg.xxDt10Dt_H2.getValue() + TM_TS_TO);
        // END 2017/06/15 E.Kameishi [QC#18184,ADD]

        if (PROS_RVW_STS_CD_BLANK.equals(bizMsg.prosRvwStsCd_H1.getValue())) {
            params.put("blankFlg", ZYPConstant.FLG_ON_Y);
        } else if (PROS_RVW_STS_CD_ALL.equals(bizMsg.prosRvwStsCd_H1.getValue())) {
            params.put("allFlg", ZYPConstant.FLG_ON_Y);
        }

        return getSsmEZDClient().queryEZDMsgArray("search", params, glblMsg.A);
    }

    /**
     * Get Territory Candidate (Resource Base)
     * @param psnNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getTrtyCandiByResrc(String psnNum) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("psnNum", psnNum);
        params.put("gntnTpCd_Reln", GNRN_TP.CURRENT);
        params.put("gntnTpCd_OU", GNRN_TP.PAST);

        return getSsmEZDClient().queryObjectList("getTrtyCandiByResrc", params);
    }

    /**
     * Get DS_ACCT_NUM from SELL_TO_CUST or DS_ACCT_PROS
     * @param locNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDsAcctNumForCust(String locNum) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("locNum", locNum);

        return getSsmEZDClient().queryObjectList("getDsAcctNumForCust", params);
    }

    /**
     * Get DS_ACCT_NUM from DS_ACCT_PROS
     * @param locNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDsAcctNumForPros(String locNum) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("locNum", locNum);

        return getSsmEZDClient().queryObjectList("getDsAcctNumForPros", params);
    }

    /**
     * Get Current Owner Name and Line of Business Type
     * @param psnNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCurrentOwnerInfo(String psnNum) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("psnNum", psnNum);

        return getSsmEZDClient().queryObject("getCurrentOwnerInfo", params);
    }

    /**
     * Get Sales Rep by Organization Code
     * @param orgCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSalesRep(String orgCd) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("orgCd", orgCd);

        return getSsmEZDClient().queryObject("getSalesRep", params);
    }

    /**
     * Get Territory Organization Info
     * @param orgCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getTrtyInfo(String orgCd) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("orgCd", orgCd);
        String[] gntnTpCd = new String[] {GNRN_TP.CURRENT, GNRN_TP.FUTURE };
        params.put("gntnTpCd", gntnTpCd);

        return getSsmEZDClient().queryObject("getTrtyInfo", params);
    }

    /**
     * Get Territory Group Info
     * @param orgCd String
     * @param nonSlsRepFlg String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getTrtyGrpInfo(String orgCd, String nonSlsRepFlg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("orgCd", orgCd);
        params.put("gnrnTpCd", GNRN_TP.CURRENT);
        params.put("nonSlsRepFlg", nonSlsRepFlg);
        // 2016/07/26 QC#12394 Mod Start
        if (ZYPConstant.FLG_OFF_N.equals(nonSlsRepFlg)) {
            params.put("nonSlsRep", ZYPConstant.FLG_OFF_N);
        }
        // 2016/07/26 QC#12394 Mod End

        return getSsmEZDClient().queryObjectList("getTrtyGrpInfo", params);
    }

    /**
     * Get Account Name
     * @param locNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDsAcctNm(String locNum) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("locNum", locNum);

        return getSsmEZDClient().queryObject("getDsAcctNm", params);
    }

    /**
     * Get Organization Info
     * @param orgNm String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getOrgInfo(String orgNm) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("orgNm", orgNm);

        return getSsmEZDClient().queryObject("getOrgInfo", params);
    }
    // 2016/07/26 QC#12417 Add Start
    /**
     * Get State List
     * @param ctryCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getStateList(String ctryCd) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("ctryCd", ctryCd);

        return getSsmEZDClient().queryObjectList("getStateList", params);
    }
    /**
     * Get Country Code
     * @param ctryNm String
     * @return String
     */
    public String getCtryCd(String ctryNm) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("ctryNm", ctryNm);

        return (String) getSsmEZDClient().queryObject("getCtryCd", params).getResultObject();
    }
    /**
     * Get territory Code
     * @param trtyOrgNm String
     * @return String
     */
    public String getTrtyOrgCd(String trtyOrgNm) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("trtyOrgNm", trtyOrgNm);
        params.put("gnrnTpCd", GNRN_TP.CURRENT);
        params.put("trtyStruFlg", ZYPConstant.FLG_ON_Y);

        return (String) getSsmEZDClient().queryObject("getTrtyOrgCd", params).getResultObject();
    }
    // 2016/07/26 QC#12417 Add End

    // 2017/10/10 QC#20662 Add Start
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
    // 2017/10/10 QC#20662 Add End
    // 2016/12/14 QC#22244 Add Start
    /**
     * checkExistRvwProsNum
     * @param getValue String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult checkExistRvwProsNum(String getValue) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("rvwProsNum", getValue);
        ssmParam.put("prosRvwStsCd", PROS_RVW_STS.D);

        return getSsmEZDClient().queryObject("checkExistRvwProsNum", ssmParam);
    }
    // 2016/12/14 QC#22244 Add End

    // Add Start 2018/01/16 QC#23148
    /**
     * Get Sales Rep by Territory Organization Name
     * @param orgCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSalesRepByTrtyOrgNm(String orgCd) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("trtyOrgNm", orgCd);
        params.put("gnrnTpCd", GNRN_TP.CURRENT);

        return getSsmEZDClient().queryObjectList("getSalesRepByTrtyOrgNm", params);
    }
    // Add End 2018/01/16 QC#23148

    // Add Start 2018/03/28 QC#23149
    /**
     * Get Sales Rep by Territory Organization Name
     * @param orgCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getLocStCd(String stValue) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("stValue", stValue);

        return getSsmEZDClient().queryObjectList("getLocStCd", params);
    }
    // Add End 2018/03/28 QC#23149
}
