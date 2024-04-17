/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NFCL2650;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/02   Fujitsu         M.Ohno          Create          N/A
 * 2020/01/16   Fujitsu         M.Ishii           Update          QC#55216
 *</pre>
 */
public final class NFCL2650Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NFCL2650Query MY_INSTANCE = new NFCL2650Query();

    /**
     * Private constructor
     */
    private NFCL2650Query() {
        super();
    }

    /**
     * Get the NFCL2650Query instance.
     * @return NFCL2650Query instance
     */
    public static NFCL2650Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * getAcctNmFrom
     * @param cMsg NFCL2650CMsg
     * @param sMsg NFCL2650SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getAcctNmFrom(NFCL2650CMsg cMsg, NFCL2650SMsg sMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("sellToCustCd", cMsg.dsAcctNum_FR.getValue());
        return getSsmEZDClient().queryObject("getAcctNm", params);
    }

    /**
     * getAcctNmTo
     * @param cMsg NFCL2650CMsg
     * @param sMsg NFCL2650SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getAcctNmTo(NFCL2650CMsg cMsg, NFCL2650SMsg sMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("sellToCustCd", cMsg.dsAcctNum_TO.getValue());
        return getSsmEZDClient().queryObject("getAcctNm", params);
    }

    // START 2016/11/25 [QC#13259, DEL]
//    /**
//     * getBillToCustNm
//     * @param cMsg NFCL2650CMsg
//     * @param sMsg NFCL2650SMsg
//     * @return S21SsmEZDResult
//     */
//    public S21SsmEZDResult getBillToCustNm(NFCL2650CMsg cMsg, NFCL2650SMsg sMsg) {
//
//        Map<String, Object> params = new HashMap<String, Object>();
//        params.put("glblCmpyCd", getGlobalCompanyCode());
//        params.put("billToCustCd", cMsg.billToCustCd.getValue()); 
//        return getSsmEZDClient().queryObject("getBillToCustNm", params);
//    }
    // END 2016/11/25 [QC#13259, DEL]
    /**
     * getBetweenAcctNum
     * @param cMsg NFCL2650CMsg
     * @param sMsg NFCL2650SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getBetweenAcctNum(NFCL2650CMsg cMsg, int maxNum) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("maxNum", maxNum + 1);
        if (!cMsg.dsAcctNum_FR.isClear() && !cMsg.dsAcctNum_TO.isClear()) {
            params.put("sellToCustCdFrom", cMsg.dsAcctNum_FR.getValue());
            params.put("sellToCustCdTo", cMsg.dsAcctNum_TO.getValue());
        } else if (!cMsg.dsAcctNm_F2.isClear() && !cMsg.dsAcctNm_T2.isClear()) {
            params.put("dsAcctNmFrom", cMsg.dsAcctNm_F2.getValue());
            params.put("dsAcctNmTo", cMsg.dsAcctNm_T2.getValue());
        }
        return getSsmEZDClient().queryObjectList("getBetweenAcctNum", params);
    }

    /**
     * getDsAcctCustFromNum
     * @param cMsg NFCL2650CMsg
     * @param sMsg NFCL2650SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDsAcctCustFromNum(NFCL2650CMsg cMsg, NFCL2650SMsg sMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("sellToCustCd", cMsg.dsAcctNum_FR.getValue());
        return getSsmEZDClient().queryObjectList("getAcctCust", params);
    }

    /**
     * getDsAcctCustToNum
     * @param cMsg NFCL2650CMsg
     * @param sMsg NFCL2650SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDsAcctCustToNum(NFCL2650CMsg cMsg, NFCL2650SMsg sMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("sellToCustCd", cMsg.dsAcctNum_TO.getValue());
        return getSsmEZDClient().queryObjectList("getAcctCust", params);
    }

    /**
     * getDsAcctCustFromNm
     * @param cMsg NFCL2650CMsg
     * @param sMsg NFCL2650SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDsAcctCustFromNm(NFCL2650CMsg cMsg, NFCL2650SMsg sMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("dsAcctNm", cMsg.dsAcctNm_F2.getValue());
        return getSsmEZDClient().queryObjectList("getAcctCust", params);
    }

    /**
     * getDsAcctCustToNm
     * @param cMsg NFCL2650CMsg
     * @param sMsg NFCL2650SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDsAcctCustToNm(NFCL2650CMsg cMsg, NFCL2650SMsg sMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("dsAcctNm", cMsg.dsAcctNm_T2.getValue());
        return getSsmEZDClient().queryObjectList("getAcctCust", params);
    }

    // START 2016/11/25 [QC#13259, ADD]
    /**
     * get location Name by Location Number
     * @param locNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getLocNm(String locNum) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("locNum", locNum); 
        return getSsmEZDClient().queryObject("getLocNm", params);
    }
    // END 2016/11/25 [QC#13259, ADD]
    // START   2020/01/16 [QC#55216,ADD]
    /**
     * get Statement Need Account by Sell To Cust
     * @param acctNumList List<String>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getStmtNeedAcct(List<String> acctNumList) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("acctNumList", acctNumList);
        params.put("arStmtFlgY", ZYPConstant.FLG_ON_Y);
        return getSsmEZDClient().queryObjectList("getStmtNeedAcct", params);
    }

    /**
     * get Statement Need Account by Bill To Cust
     * @param billToCustCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getStmtNeedAcctFromBillTo(String billToCustCd) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("billToCustCd", billToCustCd);
        params.put("arStmtFlgY", ZYPConstant.FLG_ON_Y);
        return getSsmEZDClient().queryObjectList("getStmtNeedAcctFromBillTo", params);
    }

    /**
     * get Customer with Invalid Collector by Sell To Cust
     * @param acctNumList List<String>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCustomerWithInvalidCollector(List<String> acctNumList) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("acctNumList", acctNumList);
        return getSsmEZDClient().queryObjectList("getCustomerWithInvalidCollector", params);
    }

    /**
     * get Customer with Invalid Collector by Bill To Cust
     * @param billToCustCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCustomerWithInvalidCollectorFromBillTo(String billToCustCd) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("billToCustCd", billToCustCd);
        return getSsmEZDClient().queryObject("getCustomerWithInvalidCollectorFromBillTo", params);
    }
    // END   2020/01/16 [QC#55216,ADD]
}
