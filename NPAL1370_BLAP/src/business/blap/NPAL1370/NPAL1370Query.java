/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NPAL1370;

import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * <pre>
 * Business ID : NPAL1370 Min Max Planning Copy Popup
 * Function Name : SQL Query
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/03/2015   CITS       Takeshi Tokutomi     Create          N/A
 *</pre>
 */
public final class NPAL1370Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NPAL1370Query MY_INSTANCE = new NPAL1370Query();

    /**
     * Constructor.
     */
    private NPAL1370Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NPAL1370Query
     */
    public static NPAL1370Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * Get Warehouse Name
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getWHName(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("getWHName", ssmParam);
    }

    /**
     * Get Sub Warehouse Name
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSWHName(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("getSWHName", ssmParam);
    }
}
