/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSBL0160;

import java.util.List;
import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/** 
 *<pre>
 *
 * Memo Entry
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/06/11   Hitachi         Y.Igarashi      Create          N/A
 * 2016/02/26   Hitachi         T.Tomita        Update          QC#3689
 *</pre>
 */
public final class NSBL0160Query extends S21SsmEZDQuerySupport {

    /** Singleton instance. */
    private static final NSBL0160Query MY_INSTANCE = new NSBL0160Query();

    /**
     * <pre>
     * Constructor.
     * </pre>
     */
    private NSBL0160Query() {
        super();
    }

    /**
     * <pre>
     * Get the NSBL0160Query instance.
     * </pre>
     * @return NSBL0160Query instance
     */
    public static NSBL0160Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * 
     * searchSvcMemoList
     * 
     * @param queryParam Search Condition
     * @param sMsg NSBL0160SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult searchSvcMemoList(Map<String, Object> queryParam, NSBL0160SMsg sMsg) {
        return getSsmEZDClient().queryEZDMsgArray("searchSvcMemoList", queryParam, sMsg.B);
    }

    /**
     * 
     * searchSvcMemoDispatchList
     * 
     * @param queryParam Search Condition
     * @param sMsg NSBL0160SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult searchSvcMemoDispatchList(Map<String, Object> queryParam, NSBL0160SMsg sMsg) {
        return getSsmEZDClient().queryEZDMsgArray("searchSvcMemoDispatchList", queryParam, sMsg.B);
    }

    /**
     * 
     * checkSvcMemoType
     * 
     * @param queryParam Search Condition
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult checkSvcMemoType(Map<String, Object> queryParam) {
        return getSsmEZDClient().queryObject("checkSvcMemoType", queryParam);
    }

    // mod start 2016/02/26 CSA Defect#3689
    /**
     * 
     * getSvcMemoRsnPullDown
     * 
     * @param queryParam Search Condition
     * @return S21SsmEZDResult
     */
    public List<Map<String, Object>> getSvcMemoRsnPullDown(Map<String, Object> queryParam) {
        return (List<Map<String, Object>>) getSsmEZDClient().queryObjectList("getSvcMemoRsnPullDown", queryParam).getResultObject();
    }
    // mod end 2016/02/26 CSA Defect#3689
}
