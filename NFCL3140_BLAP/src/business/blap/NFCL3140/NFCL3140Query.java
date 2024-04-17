/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NFCL3140;

import static business.blap.NFCL3140.constant.NFCL3140Constant.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_TP;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Invoice Type Setup screen
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/25   Hitachi         K.Kojima        Create          N/A
 * 2016/08/09   Hitachi         K.Kojima        Update          QC#13200
 *</pre>
 */
public final class NFCL3140Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NFCL3140Query INSTANCE = new NFCL3140Query();

    /**
     * Constructor.
     */
    private NFCL3140Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NFCL3140Query
     */
    public static NFCL3140Query getInstance() {
        return INSTANCE;
    }

    /**
     * getPulldownListInvTp
     * @param glblCmpyCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPulldownListInvTp(String glblCmpyCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("invoice", INV_TP.INVOICE);
        params.put("creditMemo", INV_TP.CREDIT_MEMO);
        params.put("rowNum", PULLDOWN_SIZE);
        return getSsmEZDClient().queryObjectList("getPulldownListInvTp", params);
    }

    /**
     * getPulldownListGrouping
     * @param bcMsgArray NFCL3140_BCMsgArray
     * @param glblCmpyCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPulldownListGrouping(NFCL3140_BCMsgArray bcMsgArray, String glblCmpyCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("rowNum", PULLDOWN_SIZE);
        return getSsmEZDClient().queryEZDMsgArray("getPulldownListGrouping", params, bcMsgArray);
    }

    /**
     * getDetailData
     * @param cMsg NFCL3140CMsg
     * @param glblCmpyCd String
     * @param dsInvTpCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDetailData(NFCL3140CMsg cMsg, String glblCmpyCd, String dsInvTpCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("dsInvTpCd", dsInvTpCd);
        return getSsmEZDClient().queryEZDMsg("getDetailData", params, cMsg);
    }

    /**
     * getPulldownListGrouping
     * @param acMsgArray NFCL3140_ACMsgArray
     * @param glblCmpyCd String
     * @param dsInvTpCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getGroupingData(NFCL3140_ACMsgArray acMsgArray, String glblCmpyCd, String dsInvTpCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("dsInvTpCd", dsInvTpCd);
        params.put("rowNum", PULLDOWN_SIZE);
        return getSsmEZDClient().queryEZDMsgArray("getGroupingData", params, acMsgArray);
    }

    /**
     * getSameNmData
     * @param glblCmpyCd String
     * @param dsInvTpNm String
     * @param dsInvTpCd String
     * @return String
     */
    public String getSameNmData(String glblCmpyCd, String dsInvTpNm, String dsInvTpCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("dsInvTpNm", dsInvTpNm);
        params.put("dsInvTpCd", dsInvTpCd);
        S21SsmEZDResult rs = getSsmEZDClient().queryObject("getSameNmData", params);
        if (rs.isCodeNotFound()) {
            return null;
        }
        return (String) rs.getResultObject();
    }

    /**
     * getMaxData
     * @param glblCmpyCd String
     * @return Map<String, Object>
     */
    public Map<String, Object> getMaxData(String glblCmpyCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        S21SsmEZDResult rs = getSsmEZDClient().queryObject("getMaxData", params);
        if (rs.isCodeNotFound()) {
            return null;
        }
        Map<String, Object> result = (Map<String, Object>) rs.getResultObject();
        return result;
    }

    /**
     * getBeforeData
     * @param glblCmpyCd String
     * @param dsInvTpCd String
     * @return Map<String, Object>
     */
    public Map<String, Object> getBeforeData(String glblCmpyCd, String dsInvTpCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("dsInvTpCd", dsInvTpCd);
        S21SsmEZDResult rs = getSsmEZDClient().queryObject("getBeforeData", params);
        if (rs.isCodeNotFound()) {
            return null;
        }
        Map<String, Object> result = (Map<String, Object>) rs.getResultObject();
        return result;
    }

    /**
     * getDeleteTarget
     * @param glblCmpyCd String
     * @param dsInvTpCd String
     * @return List<BigDecimal>
     */
    public List<BigDecimal> getDeleteTarget(String glblCmpyCd, String dsInvTpCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("dsInvTpCd", dsInvTpCd);
        S21SsmEZDResult rs = getSsmEZDClient().queryObjectList("getDeleteTarget", params);
        if (rs.isCodeNotFound()) {
            return null;
        }
        List<BigDecimal> result = (List<BigDecimal>) rs.getResultObject();
        return result;
    }

    /**
     * getCrDsInvTpCd
     * @param glblCmpyCd String
     * @param crDsInvTpCd String
     * @return String
     */
    public String getCrDsInvTpCd(String glblCmpyCd, String crDsInvTpCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("crDsInvTpCd", crDsInvTpCd);
        S21SsmEZDResult rs = getSsmEZDClient().queryObject("getCrDsInvTpCd", params);
        if (rs.isCodeNotFound()) {
            return null;
        }
        return (String) rs.getResultObject();
    }


    /**
     * getExtCurSqNum
     * @param glblCmpyCd String
     * @param autoSeqCd String
     * @return String
     */
    public String getExtCurSqNum(String glblCmpyCd, String autoSeqCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("autoSeqCd", autoSeqCd);
        S21SsmEZDResult rs = getSsmEZDClient().queryObject("getExtCurSqNum", params);
        if (rs.isCodeNotFound()) {
            return null;
        }
        return (String) rs.getResultObject();
    }

}
