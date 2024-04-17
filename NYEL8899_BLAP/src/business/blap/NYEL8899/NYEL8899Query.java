/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NYEL8899;

import java.util.HashMap;
import java.util.Map;

import parts.common.EZDSMsg;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * NYEL8899Query
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/03   Fujitsu         Q09079          Create          N/A
 *</pre>
 */
public final class NYEL8899Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NYEL8899Query MY_INSTANCE = new NYEL8899Query();

    /**
     * Private constructor
     */
    private NYEL8899Query() {
        super();
    }

    /**
     * Get the NYEL8899Query instance.
     * @return NYEL8899Query instance
     */
    public static NYEL8899Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * Search
     * @param bizMsg NYEL8899CMsg
     * @param glblMsg NYEL8899SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult search(NYEL8899CMsg bizMsg, EZDSMsg glblMsg) {
        Map<String, Object> params = new HashMap<String, Object>();

        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("wfProcNm", bizMsg.wfProcNm.getValue());
        params.put("wfDataNm", bizMsg.wfDataNm.getValue());

        return getSsmEZDClient().queryEZDMsg("getProcInstExtAttr", params, bizMsg);
    }

}
