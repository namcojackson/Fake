/**
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NPAL1240;

import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * <pre>
 * Business ID : NPAL1240 Qualifier Setup
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/08/27   CITS            M.Ouchi         Create          N/A
 * </pre>
 */
public class NPAL1240Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NPAL1240Query INSTANCE = new NPAL1240Query();

    /**
     * Constructor.
     */
    public NPAL1240Query() {
        super();
    }

    /**
     * <p>
     * Gets the singleton instance.
     * </p>
     * @return the instance of NPAL1240Query
     */
    public static NPAL1240Query getInstance() {
        return INSTANCE;
    }

    /**
     * <p>
     * Searches By Big Deal #.
     * </p>
     * @param params query parameters
     * @return result
     */
    public S21SsmEZDResult searchByBigDealNum(Map<String, Object> params) {
        return getSsmEZDClient().queryObjectList("searchByBigDealNum", params);
    }

    /**
     * <p>
     * Searches By Ship To Customer Code.
     * </p>
     * @param params query parameters
     * @return result
     */
    public S21SsmEZDResult searchByShipToCustCd(Map<String, Object> params) {
        return getSsmEZDClient().queryObjectList("searchByShipToCustCd", params);
    }
}
