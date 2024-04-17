/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NYEL8830;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * NYEL8830Query
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/06/29   Fujitsu         C.Yokoi         Create          N/A
 *</pre>
 */
public final class NYEL8830Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NYEL8830Query MY_INSTANCE = new NYEL8830Query();

    /**
     * Private constructor
     */
    private NYEL8830Query() {
        super();
    }

    /**
     * Get the NYEL8830Query instance.
     * @return NYEL8830Query instance
     */
    public static NYEL8830Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * get Header
     * @param bizMsg SSM Parameter
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getHeader(NYEL8830CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();

        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("processId", bizMsg.wfProcPk.getValue());

        return getSsmEZDClient().queryObjectList("searchHead", params);
    }

    /**
     * get Status
     * @param bizMsg SSM Parameter
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getTaskSts(NYEL8830CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();

        params.put("glblCmpyCd", getGlobalCompanyCode());

        return getSsmEZDClient().queryEZDMsgArray("getTaskSts", params, bizMsg.A);
    }

    /**
     * get History
     * @param bizMsg SSM Parameter
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getTaskHist(NYEL8830CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();

        params.put("glblCmpyCd", getGlobalCompanyCode());

        return getSsmEZDClient().queryEZDMsgArray("getTaskHist", params, bizMsg.B);
    }
}
