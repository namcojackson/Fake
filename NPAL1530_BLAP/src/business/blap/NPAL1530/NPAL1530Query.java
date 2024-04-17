/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NPAL1530;

import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Business ID : NPAL1530 Min-Max Planning Report Run Screen
 * Function Name : Query
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/23/2016   CITS            Keiichi Masaki  Create          N/A
 * 10/04/2017   CITS            K.Fukumura      Update          QC#21230
 *</pre>
 */
public final class NPAL1530Query extends S21SsmEZDQuerySupport {
    /**
     * Singleton instance.
     */
    private static final NPAL1530Query MY_INSTANCE = new NPAL1530Query();

    /**
     * Constructor.
     */
    private NPAL1530Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NPAL1530Query
     */
    public static NPAL1530Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * get Plan Name
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPlanName(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getPlanName", ssmParam);
    }

    /**
     * get Warehouse and Sub Warehouse
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getWarehouseAndSubWarehouse(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getWarehouseAndSubWarehouse", ssmParam);
    }

    /**
     * get MRP Run Status
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getMrpRunStatus(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getMrpRunStatus", ssmParam);
    }

    // QC#21230 Start
    /**
     * get MRP Run Status
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getMrpInfoPlanWhSwh(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getMrpInfoPlanWhSwh", ssmParam);
    }
    // QC#21230 End
}
