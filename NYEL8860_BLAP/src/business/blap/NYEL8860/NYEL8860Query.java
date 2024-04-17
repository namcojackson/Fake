/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NYEL8860;

import java.util.HashMap;
import java.util.Map;

import business.blap.NYEL8860.NYEL8860CMsg;
import business.blap.NYEL8860.NYEL8860SMsg;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * NYEL8860Query
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/06/26   Fujitsu         M.Yamada        Create          N/A
 *</pre>
 */
public final class NYEL8860Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NYEL8860Query MY_INSTANCE = new NYEL8860Query();

    /**
     * Private constructor
     */
    private NYEL8860Query() {
        super();
    }

    /**
     * Get the NYEL8860Query instance.
     * @return NYEL8860Query instance
     */
    public static NYEL8860Query getInstance() {
        return MY_INSTANCE;
    }
    /**
     * 
     * @param bizMsg
     * @param glblMsg
     * @return
     */
    public S21SsmEZDResult search(NYEL8860CMsg bizMsg, NYEL8860SMsg glblMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        // TODO [Template] set ssm parameters.
        params.put("glblCmpyCd", getGlobalCompanyCode());

        return getSsmEZDClient().queryEZDMsgArray("getSampleToSMsg", params, glblMsg.A);
    }
}
