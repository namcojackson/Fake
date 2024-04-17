/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1760;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Price List Popup
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/25   Fujitsu         A.Suda          Create          N/A
 *</pre>
 */
public final class NWAL1760Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NWAL1760Query MY_INSTANCE = new NWAL1760Query();

    /**
     * Private constructor
     */
    private NWAL1760Query() {
        super();
    }

    /**
     * Get the NWAL1760Query instance.
     * @return NWAL1760Query instance
     */
    public static NWAL1760Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * Get LineOfBusinessCode
     * @param bizMsg NWAL1760CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getLineOfBusinessCd(NWAL1760CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("dsOrdTpCd", bizMsg.dsOrdTpCd.getValue());

        return getSsmEZDClient().queryObjectList("getLineOfBusinessCd", params);
    }

    /**
     * Get DsAccountName
     * @param bizMsg NWAL1760CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDsAccountNm(NWAL1760CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("custAcctNum", bizMsg.custAcctNum.getValue());

        return getSsmEZDClient().queryObjectList("getDsAccountNm", params);
    }

    /**
     * Get OrderCategoryName
     * @param bizMsg NWAL1760CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getOrderCategoryNm(NWAL1760CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("dsOrdCatgCd", bizMsg.dsOrdCatgCd.getValue());

        return getSsmEZDClient().queryObjectList("getOrderCategoryNm", params);
    }

    /**
     * Get OrderReasonName
     * @param bizMsg NWAL1760CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getOrderReasonNm(NWAL1760CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("dsOrdTpCd", bizMsg.dsOrdTpCd.getValue());

        return getSsmEZDClient().queryObjectList("getOrderReasonNm", params);
    }

    /**
     * Get PriceCategoryName
     * @param xxPrcListCd String
     * @param prcCatgNm String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPriceCategoryNm(String xxPrcListCd, String prcCatgNm) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("prcCatgCd", xxPrcListCd);
        params.put("prcCatgNm", prcCatgNm);

        return getSsmEZDClient().queryObjectList("getPriceCategoryNm", params);
    }
}
