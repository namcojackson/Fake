/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NYEL8870;

import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * NYEL8850Query
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/27   Fujitsu         Q10627          Create          N/A
 *</pre>
 */
public final class NYEL8870Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NYEL8870Query MY_INSTANCE = new NYEL8870Query();

    /**
     * Private constructor
     */
    private NYEL8870Query() {
        super();
    }

    /**
     * Get the NYEL8870Query instance.
     * @return NYEL8870Query instance
     */
    public static NYEL8870Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * getProcNames
     * @param params params
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getProcNames(Map params) {
        return getSsmEZDClient().queryObjectList("getProcNames", params);
    }

    /**
     * getProcNames
     * @param params params
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getUserPrefs(Map params) {
        return getSsmEZDClient().queryObjectList("getUserPrefs", params);
    }

}
