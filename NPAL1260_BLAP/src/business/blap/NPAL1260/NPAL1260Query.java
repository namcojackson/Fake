package business.blap.NPAL1260;

import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * <pre>
 * Business ID : NPAL1090 Tech Parts Request Inquiry
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/02/2015   CITS       Yasushi Nomura        Create          N/A
 *</pre>
 */
public final class NPAL1260Query extends S21SsmEZDQuerySupport {
    /** Singleton instance. */
    private static final NPAL1260Query MY_INSTANCE = new NPAL1260Query();

    /**
     * <pre>
     * Constructor.
     * </pre>
     */
    private NPAL1260Query() {
        super();
    }

    /**
     * <pre>
     * Get the NPAL1260Query instance.
     * </pre>
     * @return NPAL1260Query instance
     */
    public static NPAL1260Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * Get Pulldown list of SearchOption
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSearchOptionPulldownList(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getSearchOptionPulldownList", ssmParam);
    }

    /**
     * Get Pulldown list of RequisitionType
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRequisitionTypePulldownList(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getRequisitionTypePulldownList", ssmParam);
    }

    /**
     * Get Pulldown list of LineStatus
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getLineStatusPulldownList(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getLineStatusPulldownList", ssmParam);
    }

    /**
     * Get Pulldown list of LineType
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getLineTypePulldownList(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getLineTypePulldownList", ssmParam);
    }

    /**
     * Get Pulldown list of LineType
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getLineTypePulldownList2(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getLineTypePulldownList2", ssmParam);
    }

    /**
     * Get Pulldown list of TechTerritory
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getTechTerritoryPulldownList(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getTechTerritoryPulldownList", ssmParam);
    }

    /**
     * Get Pulldown list of DocumentSourceType
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDocumentSourceTypePulldownList(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getDocumentSourceTypePulldownList", ssmParam);
    }

    /**
     * Get Pulldown list of RequestedShipMethod
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRequestedShipMethodPulldownList(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getRequestedShipMethodPulldownList", ssmParam);
    }

    /**
     * Find CustomerName
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult findCustomerName(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("findCustomerName", ssmParam);
    }

    /**
     * Search PR List
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult search(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("search", ssmParam);
    }

    /**
     * getProNum
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getProNum(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getProNum", ssmParam);
    }
}
