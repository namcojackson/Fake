/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL0140;

import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/20   SRAA            Y.Chen          Create          QC#4505
 *</pre>
 */
public final class NWAL0140Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NWAL0140Query INSTANCE = new NWAL0140Query();

    /**
     * Constructor.
     */
    private NWAL0140Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NWAL0140Query
     */
    public static NWAL0140Query getInstance() {
        return INSTANCE;
    }

    /**
     * Get address by postal code
     * @param queryParam SSM Query Parameter
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getAddrByPost(Map<String, Object> queryParam) {
        return getSsmEZDClient().queryObjectList("getAddrByPost", queryParam);
    }

}
