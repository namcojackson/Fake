/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL2540;

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
public final class NMAL2540Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NMAL2540Query INSTANCE = new NMAL2540Query();

    /**
     * Constructor.
     */
    private NMAL2540Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NMAL2540Query
     */
    public static NMAL2540Query getInstance() {
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
