/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL2400;

import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * <pre>
 * Business ID : NWAL2400 CUSA Retail Dealer Maintenance
 * </pre>
 *
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/12   CITS            T.Gotoda        Create          N/A
 * 2016/08/02   CITS            S.Tanikawa      Update          QC#10537
 *</pre>
 */
public final class NWAL2400Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NWAL2400Query MY_INSTANCE = new NWAL2400Query();

    /**
     * Constructor.
     */
    private NWAL2400Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NWAL2400Query
     */
    public static NWAL2400Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * Get Pulldown list of Retail Division
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRetailDivisionPulldownList(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getRetailDivisionPulldownList", ssmParam);
    }

    /**
     * Search Retail Dealer
     * @param ssmParam Map<String, Object>
     * @param sMsg NWAL2400SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult searchRetailDealer(Map<String, Object> ssmParam, NWAL2400SMsg sMsg) {
        return getSsmEZDClient().queryEZDMsgArray("searchRetailDealer", ssmParam, sMsg.A);
    }

    /**
     * Get Order Category
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDsOrdCatgCd(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("getDsOrdCatgCd", ssmParam);
    }

    /**
     * Check COA Branch Code
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult chkCoaBrCd(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("chkCoaBrCd", ssmParam);
    }

    /**
     * Get Order Type Code
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDsOrdTpCd(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("getDsOrdTpCd", ssmParam);
    }

    /**
     * Get Retail Warehouse
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRtlWh(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("getRtlWh", ssmParam);
    }

    /**
     * Check Bill To Cust Code
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult chkBillToCustCd(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("chkBillToCustCd", ssmParam);
    }

    /**
     * Check DS Retail Dealer
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult chkDsRtlDlrInfo(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("chkDsRtlDlrInfo", ssmParam);
    }

    /**
     * Get DS_RTL_DLR_INFO_PK
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRtlDlrInfoPk(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("getRtlDlrInfoPk", ssmParam);
    }

    /**
     * Get COA_BR_DESC_TXT
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCoaBr(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("getCoaBr", ssmParam);
    }
}