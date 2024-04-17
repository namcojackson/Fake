/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLCL0600;

import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * <pre>
 * Business ID : NLCL0600 PI Entry
 * Function Name : the data base access processing by SSM
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/24/2016   CITS        Makoto Okigami      Create          N/A
 * 10/05/2016   CITS        Y.Fujii             Update          QC#14417
 * 
 *</pre>
 */
public final class NLCL0600Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NLCL0600Query MY_INSTANCE = new NLCL0600Query();

    /**
     * Constructor.
     */
    private NLCL0600Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NLCL0600Query
     */
    public static NLCL0600Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * Search
     * @param ssmParam Map<String, Object>
     * @param sMsg NLCL0600SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult search(Map<String, Object> ssmParam, NLCL0600SMsg sMsg) {
        return getSsmEZDClient().queryEZDMsgArray("search", ssmParam, sMsg.A);
    }

    /**
     * Get Rtl WH
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRtlWh(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getRtlWh", ssmParam);
    }

    /**
     * Get Rtl SWH
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRtlSwh(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getRtlSwh", ssmParam);
    }

    /**
     * Get Location
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getLocation(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getLocation", ssmParam);
    }

    /**
     * Get Rtl SWH From Rtl WH Code
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRtlSwhFromRtlWhCd(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getRtlSwhFromRtlWhCd", ssmParam);
    }

    /**
     * Get Physical Inventory Control For Name
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPhysicalInventoryControlFromName(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getPhysicalInventoryControlFromName", ssmParam);
    }

    /**
     * Check Rtl WH Effective Date
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult checkRtlWhEffectiveDate(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("checkRtlWhEffectiveDate", ssmParam);
    }

    /**
     * Get Physical Inventory Control
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPhysicalInventoryControl(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getPhysicalInventoryControl", ssmParam);
    }

    /**
     * Check Physical Inventory Control
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult checkPhysicalInventoryControl(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("checkPhysicalInventoryControl", ssmParam);
    }

}
