/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NYEL8820;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * NYEL8820Query
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/08/17   Fujitsu         Q09079          Create          N/A
 *</pre>
 */
public final class NYEL8820Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NYEL8820Query MY_INSTANCE = new NYEL8820Query();

    /**
     * Private constructor
     */
    private NYEL8820Query() {
        super();
    }

    /**
     * Get the NYEL8820Query instance.
     * @return NYEL8820Query instance
     */
    public static NYEL8820Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * Search
     * @param bizMsg NYEL8820CMsg
     * @param glblMsg NYEL8820SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult search(NYEL8820CMsg bizMsg, NYEL8820SMsg glblMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        // TODO [Template] set ssm parameters.
        params.put("glblCmpyCd", getGlobalCompanyCode());

        return getSsmEZDClient().queryEZDMsgArray("getSampleToSMsg", params, glblMsg.A);
    }
}
