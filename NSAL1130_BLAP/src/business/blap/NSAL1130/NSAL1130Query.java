/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL1130;

import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 *
 * NSAL1130 Counter History Popup
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/26   Hitachi         K.Kojima        Create          N/A
 * 2016/04/19   Hitachi         K.Yamada        Update          CSA QC#7233
 * 
 *</pre>
 */
public final class NSAL1130Query extends S21SsmEZDQuerySupport {

    /** Singleton instance. */
    private static final NSAL1130Query MY_INSTANCE = new NSAL1130Query();

    /**
     * <pre>
     * Constructor.
     * </pre>
     */
    private NSAL1130Query() {
        super();
    }

    /**
     * <pre>
     * Get the NSAL1130Query instance.
     * </pre>
     * @return NSAL1130Query instance
     */
    public static NSAL1130Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * getCounterHistoryList
     * @param queryParam Search Condition
     * @param sMsg NSAL1130SMsg
     * @return Search Result
     */
    public S21SsmEZDResult getCounterHistoryList(Map<String, Object> queryParam, NSAL1130SMsg sMsg) {
        return getSsmEZDClient().queryEZDMsgArray("getCounterHistoryList", queryParam, sMsg.A);
    }

    // add start 2016/04/19 CSA Defect#7233
    /**
     * getMtrLb
     * @param queryParam Search Condition
     * @return Search Result
     */
    public S21SsmEZDResult getMtrLb(Map<String, Object> queryParam) {
        return getSsmEZDClient().queryObjectList("getMtrLb", queryParam);
    }
    // add end 2016/04/19 CSA Defect#7233

}
