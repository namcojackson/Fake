package business.blap.NPAL1090;

import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * <pre>
 * Business ID : NPAL1090 Tech Parts Request List
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/09/2015   CITS       Yasushi Nomura        Create          N/A
 * 12/19/2016   CITS            K.Ogino         Update          QC#15825
 *</pre>
 */
public final class NPAL1090Query extends S21SsmEZDQuerySupport {
    /** Singleton instance. */
    private static final NPAL1090Query MY_INSTANCE = new NPAL1090Query();

    /**
     * <pre>
     * Constructor.
     * </pre>
     */
    private NPAL1090Query() {
        super();
    }

    /**
     * <pre>
     * Get the NPAL1090Query instance.
     * </pre>
     * @return NPAL1090Query instance
     */
    public static NPAL1090Query getInstance() {
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
     * Get Pulldown list of HeaderStatus
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getHeaderStatusPulldownList(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getHeaderStatusPulldownList", ssmParam);
    }

    /**
     * Get Pulldown list of ApprovalStatus
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getApprovalStatusPulldownList(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getApprovalStatusPulldownList", ssmParam);
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
     * Search PR List
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult search(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("search", ssmParam);
    }

}
