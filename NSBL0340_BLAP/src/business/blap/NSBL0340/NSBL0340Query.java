/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSBL0340;

import java.util.HashMap;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Service Task Summary
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/21/2016   Hitachi         J.Kim           Create          N/A
 *</pre>
 */
public final class NSBL0340Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance
     */
    private static final NSBL0340Query INSTANCE = new NSBL0340Query();

    /**
     * Private constructor
     */
    private NSBL0340Query() {
        super();
    }

    /**
     * Singleton instance getter
     * @return NSAL0020Query
     */
    public static NSBL0340Query getInstance() {
        return INSTANCE;
    }

    /**
     * getOpenSvcTaskDtlInfoList
     * @param params HashMap<String, Object>
     * @param sMsg NSBL0340SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getOpenSvcTaskDtlInfoList(HashMap<String, Object> params, NSBL0340SMsg sMsg) {
        return getSsmEZDClient().queryEZDMsgArray("getOpenSvcTaskDtlInfoList", params, sMsg.A);
    }
}
