/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NFBL1110;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import business.blap.NFBL1110.NFBL1110CMsg;
import business.blap.NFBL1110.NFBL1110Query;
import business.blap.NFBL1110.constant.NFBL1110Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * AP Invoice Maintenance Batch Entry
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/01   CUSA            Y.Aikawa        Create          N/A
 * 2016/08/04   Fujitsu         T.Murai         Update          QC#12692
 * 2016/08/23   Fujitsu         T.Murai         Update          QC#12830,12947
 * 2017/11/13   CITS            K.Ogino         Update          QC#21686
 * </pre>
 */
public final class NFBL1110Query extends S21SsmEZDQuerySupport implements NFBL1110Constant {
    /**
     * Singleton instance.
     */
    private static final NFBL1110Query INSTANCE = new NFBL1110Query();
    /**
     * User Profile
     */
    public static final S21UserProfileService PROFILE_SERVICE = S21UserProfileServiceFactory.getInstance().getService();
    /**
     * Global Company Code.
     */
    public static final String GLBL_CMPY_CD = PROFILE_SERVICE.getGlobalCompanyCode();
    /**
     * Constructor.
     */
    private NFBL1110Query() {
        super();
    }
    /**
     * Singleton instance getter.
     * @return NFBL1110Query
     */
    public static NFBL1110Query getInstance() {
        return INSTANCE;
    }

    /**
     * NFBL1110Query.xml id="getInvListByBatNum"
     * @param bizMsg NFBL1110CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getInvListByBatNum(NFBL1110CMsg bizMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", GLBL_CMPY_CD);
        ssmParam.put("apBatNum", bizMsg.apBatNum_BA.getValue());
        ssmParam.put("rownum", bizMsg.Y.length());

        return getSsmEZDClient().queryObjectList("getInvListByBatNum", ssmParam, -1, -1);
    }

    /**
     * NFBL1110Query.xml id="getCntrTpPulldownListValue"
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCntrTpPulldownListValue() {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", GLBL_CMPY_CD);

        return getSsmEZDClient().queryObjectList("getCntrTpPulldownListValue", ssmParam, -1, -1);
    }

    /**
     * NFBL1110Query.xml id="getBatPkByBatNum"
     * @param bizMsg NFBL1110CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getBatPkByBatNum(String apBatNum) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", GLBL_CMPY_CD);
        ssmParam.put("apBatNum", apBatNum);

        return getSsmEZDClient().queryObjectList("getBatPkByBatNum", ssmParam, -1, -1);
    }

    /**
     * NFBL1110Query.xml id="checkLocNm"
     * @param bizMsg NFBL1110CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult checkLocNm(NFBL1110CMsg bizMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", GLBL_CMPY_CD);
        ssmParam.put("locNm", bizMsg.vndSiteNm_IH.getValue());

        return getSsmEZDClient().queryObjectList("checkLocNm", ssmParam, -1, -1);
    }

    /**
     * NFBL1110Query.xml id="checkPrntVnd"
     * @param bizMsg NFBL1110CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult checkPrntVnd(NFBL1110CMsg bizMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", GLBL_CMPY_CD);
        ssmParam.put("prntVndCd", bizMsg.prntVndCd_IH.getValue());

        return getSsmEZDClient().queryObjectList("checkPrntVnd", ssmParam, -1, -1);
    }

    /**
     * NFBL1110Query.xml id="searchRecordByBatchPk"
     * @param cmMaintInvBatPk BigDecimal
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult searchRecordByBatchPk(BigDecimal cmMaintInvBatPk) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", GLBL_CMPY_CD);
        ssmParam.put("cmMaintInvBatPk", cmMaintInvBatPk.toString());

        return getSsmEZDClient().queryObjectList("searchRecordByBatchPk", ssmParam, -1, -1);
    }

    /**
     * NFBL1110Query.xml id="getTotalRecordCount"
     * @param cmMaintInvBatPk BigDecimal
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getTotalRecordCount(BigDecimal cmMaintInvBatPk) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", GLBL_CMPY_CD);
        ssmParam.put("cmMaintInvBatPk", cmMaintInvBatPk.toString());
        // Mod Start 2016/08/22 QC#12830
        //ssmParam.put("recCntForOneSerNum", Integer.toString(INT_6));
        ssmParam.put("recCntForOneSerNum", "1");
        // Mod End 2016/08/22 QC#12830

        return getSsmEZDClient().queryObjectList("getTotalRecordCount", ssmParam, -1, -1);
    }

    /**
     * NFBL1110Query.xml id="searchRecordByInvInfo"
     * @param apInvNum String
     * @param apVndCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult searchRecordByInvInfo(String apInvNum, String apVndCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", GLBL_CMPY_CD);
        ssmParam.put("apInvNum", apInvNum);
        ssmParam.put("apVndCd", apVndCd);

        return getSsmEZDClient().queryObjectList("searchRecordByInvInfo", ssmParam, -1, -1);
    }

    /**
     * NFBL1110Query.xml id="getInvLineTotalRecordCount"
     * @param apInvNum String
     * @param apVndCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getInvLineTotalRecordCount(String apInvNum, String apVndCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", GLBL_CMPY_CD);
        ssmParam.put("apInvNum", apInvNum);
        ssmParam.put("apVndCd", apVndCd);
        // Mod Start 2016/08/22 QC#12830
        //ssmParam.put("recCntForOneSerNum", Integer.toString(INT_6));
        ssmParam.put("recCntForOneSerNum", "1");
        // Mod End 2016/08/22 QC#12830

        return getSsmEZDClient().queryObjectList("getInvLineTotalRecordCount", ssmParam, -1, -1);
    }

    /**
     * NFBL1110Query.xml id="getPrntVndNm"
     * @param bizMsg NFBL1110CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPrntVndNm(NFBL1110CMsg bizMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", GLBL_CMPY_CD);
        ssmParam.put("prntVndCd", bizMsg.prntVndCd_IH.getValue());

        return getSsmEZDClient().queryObjectList("getPrntVndNm", ssmParam, -1, -1);
    }

    /**
     * NFBL1110Query.xml id="getInvoiceStatusPulldownValue"
     * 
     * <pre>
     * Get records for [Adjusted Reason] pulldown.
     * </pre>
     * 
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getApAdjRsnPulldownValue() {

        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", GLBL_CMPY_CD);
        return getSsmEZDClient().queryObjectList("getApAdjRsnPulldownValue", queryParam, -1, -1);

    }

    /**
     * NFBL1110Query.xml id="getLocNm"
     * 
     * <pre>
     * Get Supplier Name.
     * </pre>
     * 
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getLocNm(String vndCd) {

        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", GLBL_CMPY_CD);
        queryParam.put("vndCd", vndCd);
        return getSsmEZDClient().queryObjectList("getLocNm", queryParam, -1, -1);

    }

    /**
     * NFBL1110Query.xml id="getCcyCd"
     * 
     * <pre>
     * Get Currency Code.
     * </pre>
     * 
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCcyCd() {
        
        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", GLBL_CMPY_CD);
        return getSsmEZDClient().queryObjectList("getCcyCd", queryParam, -1, -1);

    }

    // Add Start 2016/08/04 QC#12692
    /**
     * Check Serial Number
     * @param bizMsg NFBL1110CMsg
     * @return S21SsmEZDResult
     */
    public Integer countSerNum(NFBL1110CMsg bizMsg, String serNum, String slsDt) {

        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", GLBL_CMPY_CD);
        queryParam.put("locNm", bizMsg.vndSiteNm_IH.getValue());
        queryParam.put("batchDt", slsDt);
        queryParam.put("serNum", serNum);
        return (Integer) getSsmEZDClient().queryObject("countSerNum", queryParam).getResultObject();

    }
    // Add End 2016/08/04 QC#12692

    // Add Start 2016/08/04 QC#12947
    /**
     * Check Serial Number
     * @param bizMsg NFBL1110CMsg
     * @return S21SsmEZDResult
     */
    public String getApVndCd(NFBL1110CMsg bizMsg) {

        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", GLBL_CMPY_CD);
        queryParam.put("locNm", bizMsg.vndSiteNm_IH.getValue());
        queryParam.put("prntVndCd", bizMsg.prntVndCd_IH.getValue());
        return (String) getSsmEZDClient().queryObject("getApVndCd", queryParam).getResultObject();

    }
    // Add End 2016/08/04 QC#12947

    // START 2016/09/21 W.Honda [Unit Test#201,ADD]
    /**
     * Check Serial Number
     * @param bizMsg NFBL1110CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getAttDataForTherefore(String attGrpTxt) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", GLBL_CMPY_CD);
        queryParam.put("attGrpTxt", attGrpTxt);

        String thereforeCatg = ZYPCodeDataUtil.getVarCharConstValue(CONST_NM_NFBL1110_THEREFORE_CATG_LIST, getGlobalCompanyCode());
        String[] docMgtCatgList = null;
        if (ZYPCommonFunc.hasValue(thereforeCatg)
                && thereforeCatg.contains(",")) {
            docMgtCatgList = thereforeCatg.split(",");
        } else {
            docMgtCatgList = new String[1];
            docMgtCatgList[0] = thereforeCatg;
        }
        queryParam.put("docMgtCatgList", docMgtCatgList);

        return getSsmEZDClient().queryObject("getAttDataForTherefore", queryParam);
    }
    // END 2016/09/21 W.Honda [Unit Test#201,ADD]

    /**
     * Check Invoice Number and Vendor Code Add QC#21686
     * @param bizMsg NFBL1110CMsg
     * @return S21SsmEZDResult
     */
    public String chkInvRecordSts(NFBL1110CMsg bizMsg) {

        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", GLBL_CMPY_CD);
        queryParam.put("apInvNum", bizMsg.apInvNum_IH.getValue());
        queryParam.put("apVndCd", bizMsg.apVndCd_HD.getValue());
        return (String) getSsmEZDClient().queryObject("chkInvRecordSts", queryParam).getResultObject();

    }
}
