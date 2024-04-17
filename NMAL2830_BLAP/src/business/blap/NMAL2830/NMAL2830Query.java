/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL2830;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import business.blap.NMAL2830.constant.NMAL2830Constant;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_XREF_ACCT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.GNRN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * NMAL2830Query
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/09   Fujitsu         T.Ogura         Create          N/A
 *</pre>
 */
public final class NMAL2830Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NMAL2830Query MY_INSTANCE = new NMAL2830Query();

    /**
     * Private constructor
     */
    private NMAL2830Query() {
        super();
    }

    /**
     * Get the NMAL2830Query instance.
     * @return NMAL2830Query instance
     */
    public static NMAL2830Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * getSavedSearchOptionList
     * @param usrId user id
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSavedSearchOptionList(String usrId) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("srchOptAplId", NMAL2830Constant.BIZ_ID);
        params.put("srchOptUsrId", usrId);

        return getSsmEZDClient().queryObjectList("getSavedSearchOptionList", params);
    }

    /**
     * getProspectList
     * @param bizMsg NMAL2830CMsg
     * @param glblMsg NMAL2830SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getProspectList(NMAL2830CMsg bizMsg, NMAL2830SMsg glblMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("salesDate", ZYPDateUtil.getSalesDate(getGlobalCompanyCode()));
        params.put("xxFromDt", bizMsg.xxFromDt.getValue());
        params.put("dsAcctNm", bizMsg.dsAcctNm.getValue());
        params.put("fill65Txt_RN", bizMsg.fill65Txt_RN.getValue());
        params.put("orgNm_TN", bizMsg.orgNm_TN.getValue());
        params.put("xxToDt", bizMsg.xxToDt.getValue());
        params.put("dsXrefAcctCd", bizMsg.dsXrefAcctCd.getValue());
        params.put("psnCd", bizMsg.psnCd.getValue());
        params.put("orgNm_ON", bizMsg.orgNm_ON.getValue());
        params.put("xxAllLineAddr", bizMsg.xxAllLineAddr.getValue());
        params.put("ctyAddr", bizMsg.ctyAddr.getValue());
        params.put("stCd", bizMsg.stCd.getValue());
        params.put("postCd", bizMsg.postCd.getValue());
        params.put("dsAcctNum", bizMsg.dsAcctNum.getValue());
        params.put("locNum", bizMsg.locNum.getValue());
        params.put("gnrnTpCd_2", GNRN_TP.CURRENT);
        params.put("rgtnStsCd_P01", RGTN_STS.PENDING_COMPLETION);
        params.put("rgtnStsCd_P20", RGTN_STS.READY_FOR_ORDER_TAKING);
        params.put("dsXrefAcctTpCd_sfdc", DS_XREF_ACCT_TP.SFDC);
        params.put("dsXrefAcctTpCd_oasis", DS_XREF_ACCT_TP.OASIS);
        params.put("rowNum", NMAL2830Constant.MAX_ROW_PROSPECT);
        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxTpCd_D.getValue())) {
            params.put("dispTrtyFlg", bizMsg.xxTpCd_D.getValue());
        }
        params.put("max_date", NMAL2830Constant.MAX_DATE);

        return getSsmEZDClient().queryEZDMsgArray("getProspectList", params, glblMsg.B);
    }

    /**
     * getDuplicateList_realTime
     * @param bizMsg NMAL2830CMsg
     * @param bsMsg NMAL2830_BSMsg
     * @param glblMsg NMAL2830SMsg
     * @param address String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDuplicateList_realTime(NMAL2830CMsg bizMsg, NMAL2830_BSMsg bsMsg, NMAL2830SMsg glblMsg, String address) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("salesDate", ZYPDateUtil.getSalesDate(getGlobalCompanyCode()));
        params.put("rgtnStsCd_P01", RGTN_STS.PENDING_COMPLETION);
        params.put("rgtnStsCd_P20", RGTN_STS.READY_FOR_ORDER_TAKING);
        params.put("dsXrefAcctTpCd_sfdc", DS_XREF_ACCT_TP.SFDC);
        params.put("dsXrefAcctTpCd_oasis", DS_XREF_ACCT_TP.OASIS);
        params.put("dsAcctNm_B", bsMsg.dsAcctNm_B.getValue());
        if (ZYPCommonFunc.hasValue(bsMsg.dsAcctNm_B)) {
            params.put("dsAcctNm_B_not_sps", bsMsg.dsAcctNm_B.getValue().replaceAll(NMAL2830Constant.REG_EXP_ALPH_NUM_ONLY, ""));
        }
        params.put("dsLocNm_B", bsMsg.dsLocNm_B.getValue());
        if (ZYPCommonFunc.hasValue(bsMsg.dsLocNm_B)) {
            params.put("dsLocNm_B_not_sps", bsMsg.dsLocNm_B.getValue().replaceAll(NMAL2830Constant.REG_EXP_ALPH_NUM_ONLY, ""));
        }
        params.put("firstLineAddr_B", bsMsg.firstLineAddr_B.getValue());
        params.put("scdLineAddr_B", bsMsg.scdLineAddr_B.getValue());
        params.put("thirdLineAddr_B", bsMsg.thirdLineAddr_B.getValue());
        params.put("frthLineAddr_B", bsMsg.frthLineAddr_B.getValue());
        if (ZYPCommonFunc.hasValue(address)) {
            params.put("address_not_sps", address.replaceAll(NMAL2830Constant.REG_EXP_ALPH_NUM_ONLY, ""));
        }
        params.put("ctyAddr_B", bsMsg.ctyAddr_B.getValue());
        params.put("stCd_B", bsMsg.stCd_B.getValue());
        params.put("postCd_B", bsMsg.postCd_B.getValue());
        params.put("dunsNum_B", bsMsg.dunsNum_B.getValue());
        params.put("locNum_B", bsMsg.locNum_B.getValue());
        params.put("rowNum", NMAL2830Constant.MAX_ROW_DUPLICATE_REAL_TIME);
        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxTpCd_D.getValue())) {
            params.put("dispTrtyFlg", bizMsg.xxTpCd_D.getValue());
        }
        params.put("max_date", NMAL2830Constant.MAX_DATE);

        return getSsmEZDClient().queryEZDMsgArray("getDuplicateList_realTime", params, glblMsg.C);
    }

    /**
     * getProspectList_nonRealTime
     * @param bizMsg NMAL2830CMsg
     * @param bsMsg NMAL2830_BSMsg
     * @param glblMsg NMAL2830SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getProspectList_nonRealTime(NMAL2830CMsg bizMsg, NMAL2830_BSMsg bsMsg, NMAL2830SMsg glblMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("salesDate", ZYPDateUtil.getSalesDate(getGlobalCompanyCode()));
        params.put("rgtnStsCd_P01", RGTN_STS.PENDING_COMPLETION);
        params.put("rgtnStsCd_P20", RGTN_STS.READY_FOR_ORDER_TAKING);
        params.put("locTp_NonUnderReview", NMAL2830Constant.NON_UNDER_REVIEW);
        params.put("dsXrefAcctTpCd_sfdc", DS_XREF_ACCT_TP.SFDC);
        params.put("dsXrefAcctTpCd_oasis", DS_XREF_ACCT_TP.OASIS);
        params.put("dsAcctNum_B", bsMsg.dsAcctNum_B.getValue());
        params.put("locNum_B", bsMsg.locNum_B.getValue());
        params.put("rowNum", NMAL2830Constant.MAX_ROW_DUPLICATE_NON_REAL_TIME);
        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxTpCd_D.getValue())) {
            params.put("dispTrtyFlg", bizMsg.xxTpCd_D.getValue());
        }
        params.put("max_date", NMAL2830Constant.MAX_DATE);

        return getSsmEZDClient().queryEZDMsgArray("getProspectList_nonRealTime", params, glblMsg.C);
    }

    /**
     * existLocNumCnt
     * @param locNum String
     * @param acctNum String
     * @return BigDecimal
     */
    public BigDecimal existLocNumCnt(String locNum, String acctNum) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("salesDate", ZYPDateUtil.getSalesDate(getGlobalCompanyCode()));
        params.put("rgtnStsCd_P20", RGTN_STS.READY_FOR_ORDER_TAKING);
        params.put("rgtnStsCd_P01", RGTN_STS.PENDING_COMPLETION);
        params.put("locNum", locNum);
        params.put("max_date", NMAL2830Constant.MAX_DATE);

        return (BigDecimal) getSsmEZDClient().queryObject("existLocNumCnt", params).getResultObject();
    }

    /**
     * getCntActiveLocNum
     * @param locNum String
     * @param acctNum String
     * @return BigDecimal
     */
    public BigDecimal getCntActiveLocNum(String locNum, String acctNum) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("dsAcctNum", acctNum);
        params.put("locNum", locNum);
        params.put("rgtnStsCd_P20", RGTN_STS.READY_FOR_ORDER_TAKING);
        params.put("rgtnStsCd_P01", RGTN_STS.PENDING_COMPLETION);

        return (BigDecimal) getSsmEZDClient().queryObject("getCntActiveLocNum", params).getResultObject();
    }

    /**
     * getUpdateInfo
     * @param asMsg NMAL2830_ASMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getUpdateInfo(NMAL2830_ASMsg asMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("salesDate", ZYPDateUtil.getSalesDate(getGlobalCompanyCode()));
        params.put("rgtnStsCd_P01", RGTN_STS.PENDING_COMPLETION);
        params.put("rgtnStsCd_P20", RGTN_STS.READY_FOR_ORDER_TAKING);
        if (NMAL2830Constant.ROW_TP_PROSPECT.equals(asMsg.xxRowId_AT.getValue())) {
            params.put("dsAcctNum", asMsg.dsAcctNum_A1.getValue());
            params.put("locNum", asMsg.locNum_A1.getValue());
        } else if (NMAL2830Constant.ROW_TP_DUPLICATE.equals(asMsg.xxRowId_AT.getValue())) {
            params.put("dsAcctNum", asMsg.dsAcctNum_A3.getValue());
            params.put("locNum", asMsg.locNum_A3.getValue());
        }
        params.put("max_date", NMAL2830Constant.MAX_DATE);

        return getSsmEZDClient().queryObjectList("getUpdateInfo", params);
    }

    /**
     * getCntActiveLocNumThatExcCurLocNum
     * @param asMsg NMAL2830_ASMsg
     * @return BigDecimal
     */
    public BigDecimal getCntActiveLocNumThatExcCurLocNum(NMAL2830_ASMsg asMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        if (NMAL2830Constant.ROW_TP_PROSPECT.equals(asMsg.xxRowId_AT.getValue())) {
            params.put("dsAcctNum", asMsg.dsAcctNum_A1.getValue());
            params.put("cur_locNum", asMsg.locNum_A1.getValue());
        } else if (NMAL2830Constant.ROW_TP_DUPLICATE.equals(asMsg.xxRowId_AT.getValue())) {
            params.put("dsAcctNum", asMsg.dsAcctNum_A3.getValue());
            params.put("cur_locNum", asMsg.locNum_A3.getValue());
        }
        params.put("rgtnStsCd_P20", RGTN_STS.READY_FOR_ORDER_TAKING);
        params.put("rgtnStsCd_P01", RGTN_STS.PENDING_COMPLETION);

        return (BigDecimal) getSsmEZDClient().queryObject("getCntActiveLocNumThatExcCurLocNum", params).getResultObject();
    }

    /**
     * getRequestedMergeTo
     * @param asMsg NMAL2830_ASMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRequestedMergeTo(NMAL2830_ASMsg asMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("salesDate", ZYPDateUtil.getSalesDate(getGlobalCompanyCode()));
        params.put("rgtnStsCd_P20", RGTN_STS.READY_FOR_ORDER_TAKING);
        params.put("rgtnStsCd_P01", RGTN_STS.PENDING_COMPLETION);
        params.put("dsXrefAcctTpCd_sfdc", DS_XREF_ACCT_TP.SFDC);
        params.put("dsXrefAcctTpCd_oasis", DS_XREF_ACCT_TP.OASIS);
        params.put("max_date", NMAL2830Constant.MAX_DATE);
        params.put("locNum_M", asMsg.locNum_M.getValue());

        return getSsmEZDClient().queryObjectList("getRequestedMergeTo", params);
    }
}
