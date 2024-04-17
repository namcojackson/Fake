/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NPAL1440;

import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * <pre>
 * Business ID : NPAL1440 PR History Popup
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/27/2016   CITS       Yasushi Nomura        Create          N/A
 *</pre>
 */
public final class NPAL1440Query extends S21SsmEZDQuerySupport {
    /** Singleton instance. */
    private static final NPAL1440Query MY_INSTANCE = new NPAL1440Query();

    /**
     * <pre>
     * Constructor.
     * </pre>
     */
    private NPAL1440Query() {
        super();
    }

    /**
     * <pre>
     * Get the NPAL1440Query instance.
     * </pre>
     * @return NPAL1440Query instance
     */
    public static NPAL1440Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * search
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult search(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("search", ssmParam);
    }

}
