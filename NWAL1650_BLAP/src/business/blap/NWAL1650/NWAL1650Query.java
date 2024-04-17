/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1650;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * NWAL1650Query
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/12   Fujitsu         M.Yamada        Create          N/A
 *</pre>
 */
public final class NWAL1650Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NWAL1650Query MY_INSTANCE = new NWAL1650Query();

    /**
     * Private constructor
     */
    private NWAL1650Query() {
        super();
    }

    /**
     * Get the NWAL1650Query instance.
     * @return NWAL1650Query instance
     */
    public static NWAL1650Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * <pre>
     * Search
     * @param glblCmpyCd    glblCmpyCd
     * @param dsAcctNum     dsAcctNum
     * @return S21SsmEZDResult
     * </pre>
     */
    public S21SsmEZDResult getDsAcctRefAttrb(String glblCmpyCd, String dsAcctNum) {
        if (dsAcctNum == null) {
            return null;
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("dsAcctNum", dsAcctNum);

        return getSsmEZDClient().queryObjectList("getDsAcctRefAttrb", params);
    }

}
