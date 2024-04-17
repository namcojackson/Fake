/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLCL0630;

import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * <pre>
 * Business ID : NLCL0630 Tech PI Inquiry
 * Function Name : the data base access processing by SSM
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/26/2016   CITS         Makoto Okigami     Create          N/A
 * 12/11/2018   Fujitsu         T.Ogura         Update          QC#28755
 *</pre>
 */
public final class NLCL0630Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NLCL0630Query MY_INSTANCE = new NLCL0630Query();

    /**
     * Constructor.
     */
    private NLCL0630Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NLCL0630Query
     */
    public static NLCL0630Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * Search
     * @param ssmParam Map<String, Object>
     * @param sMsg NLCL0630SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult search(Map<String, Object> ssmParam, NLCL0630SMsg sMsg) {
        return getSsmEZDClient().queryEZDMsgArray("search", ssmParam, sMsg.A);
    }

    /**
     * Get Technician Name
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getTechnicianName(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getTechnicianName", ssmParam);
    }

    // START 2018/12/11 T.Ogura [QC#28755,ADD]
    /**
     * Get Location Name
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getLocationName(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getLocationName", ssmParam);
    }
    // END   2018/12/11 T.Ogura [QC#28755,ADD]

    // START 2018/12/03 T.Ogura [QC#27978,ADD]
    /**
     * Get Physical Inventory Status Name
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPhysicalInventoryStatusName(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getPhysicalInventoryStatusName", ssmParam);
    }
    // END   2018/12/03 T.Ogura [QC#27978,ADD]

    // START 2018/12/03 T.Ogura [QC#27978,ADD]
    /**
     * Get Count Status
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCountStatus(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getCountStatus", ssmParam);
    }
    // END   2018/12/03 T.Ogura [QC#27978,ADD]

    // START 2018/12/03 T.Ogura [QC#27978,ADD]
    /**
     * Get PI Controls from PI Numer
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPIControlsFromPINumer(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getPIControlsFromPINumer", ssmParam);
    }
    // END   2018/12/03 T.Ogura [QC#27978,ADD]

    // START 2018/12/11 T.Ogura [QC#28755,ADD]
    /**
     * Count Same Location [OPEN] Status
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult countSameLocationOpenStatus(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("countSameLocationOpenStatus", ssmParam);
    }
    // END   2018/12/11 T.Ogura [QC#28755,ADD]

}
