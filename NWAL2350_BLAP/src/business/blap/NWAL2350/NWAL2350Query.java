/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL2350;

import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * SOM Profitability
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/29   CITS            S.Tanikawa      Create          N/A
 *</pre>
 */

public final class NWAL2350Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NWAL2350Query MY_INSTANCE = new NWAL2350Query();

    /**
     * Constructor.
     */
    private NWAL2350Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NWAL2350Query
     */
    public static NWAL2350Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * search
     * @param ssmParam Map<String, Object>
     * @param sMsg NWAL2350SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult search(Map<String, Object> ssmParam, NWAL2350SMsg sMsg) {
        return getSsmEZDClient().queryEZDMsg("search", ssmParam, sMsg);
    }
}
