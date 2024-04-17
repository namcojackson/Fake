/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0800;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/11   Hitachi         Y.Tsuchimoto    Create          N/A
 * 2017/08/15   CITS            T.Kikuhara      Update          QC#18799(L3)
 *</pre>
 */
public final class NSAL0800Query extends S21SsmEZDQuerySupport {
    /**
     * Singleton instance
     */
    private static final NSAL0800Query INSTANCE = new NSAL0800Query();

    /**
     * Private constructor
     */
    private NSAL0800Query() {
        super();
    }

    /**
     * Singleton instance getter
     * @return NSAL0800Query
     */
    public static NSAL0800Query getInstance() {
        return INSTANCE;
    }

    /**
     * getDsContrIntfcDefRule
     * @param contrIntfcSrcTpCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDsContrIntfcDefRule(String contrIntfcSrcTpCd) {
        String glblCmpyCd = getGlobalCompanyCode();
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("contrIntfcSrcTpCd", contrIntfcSrcTpCd);
        return getSsmEZDClient().queryObject("getDsContrIntfcDefRule", ssmParam);
    }

    /**
     * getFirstContrIntfcSrcTpCd
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getFirstContrIntfcSrcTpCd() {
        String glblCmpyCd = getGlobalCompanyCode();
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        return getSsmEZDClient().queryObject("getFirstContrIntfcSrcTpCd", ssmParam);
    }

    /**
     * getDsContrIntfcDefRuleByContrIntfcSrcTpCd
     * @param map Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDsContrIntfcDefRuleByContrIntfcSrcTpCd(Map<String, Object> map) {
        String glblCmpyCd = getGlobalCompanyCode();
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("contrIntfcSrcTpCd", (String) map.get("CONTR_INTFC_SRC_TP_CD"));
        ssmParam.put("mtrEstTpCd", (String) map.get("MTR_EST_TP_CD"));
        ssmParam.put("baseBllgCycleCd", (String) map.get("BASE_BLLG_CYCLE_CD"));
        ssmParam.put("mtrBllgCycleCd", (String) map.get("MTR_BLLG_CYCLE_CD"));
        ssmParam.put("contrUplftTpCd", (String) map.get("CONTR_UPLFT_TP_CD"));
        ssmParam.put("uplftPrcMethCd", (String) map.get("UPLFT_PRC_METH_CD"));
        ssmParam.put("bllgThruTpCd", (String) map.get("BLLG_THRU_TP_CD"));
        ssmParam.put("stubPrepFromTpCd", (String) map.get("STUB_PREP_FROM_TP_CD"));
        ssmParam.put("stubPrepThruTpCd", (String) map.get("STUB_PREP_THRU_TP_CD"));
        ssmParam.put("mtrReadMethCd", (String) map.get("MTR_READ_METH_CD"));
        ssmParam.put("dsContrSlsCrTpCd", (String) map.get("DS_CONTR_SLS_CR_TP_CD"));
        ssmParam.put("dsContrClsCd", (String) map.get("DS_CONTR_CLS_CD"));
        ssmParam.put("contrAutoRnwTpCd", (String) map.get("CONTR_AUTO_RNW_TP_CD"));
        ssmParam.put("rnwPrcMethCd", (String) map.get("RNW_PRC_METH_CD"));
        ssmParam.put("startReadVldTpCd", (String) map.get("START_READ_VLD_TP_CD"));
        // QC#18799 ADD START
        ssmParam.put("svcLineBizCd", (String) map.get("SVC_LINE_BIZ_CD"));
        ssmParam.put("dsContrClsCd2", (String) map.get("DS_CONTR_CLS_CD2"));
        // QC#18799 ADD END
        return getSsmEZDClient().queryObject("getDsContrIntfcDefRuleByContrIntfcSrcTpCd", ssmParam);
    }

    // QC#18799 ADD START
    /**
     * getSvcLineBizCdList
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSvcLineBizCdList() {
        String glblCmpyCd = getGlobalCompanyCode();
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        return getSsmEZDClient().queryObjectList("getSvcLineBizCdList", ssmParam);
    }

    /**
     * getDsContrIntfcDefRule for Download
     * @param contrIntfcSrcTpCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDsContrIntfcDefRuleForDownload(String contrIntfcSrcTpCd) {
        String glblCmpyCd = getGlobalCompanyCode();
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        return getSsmEZDClient().queryObject("getDsContrIntfcDefRule", ssmParam);
    }
    // QC#18799 ADD END

}
