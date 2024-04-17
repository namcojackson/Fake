/**
 * <Pre>Copyright(c)2013 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL1160;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * Supply Watch Notes Action
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/26/2016   Hitachi         J.Kim           Create          N/A
 *</pre>
 */
public final class NSAL1160Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance
     */
    private static final NSAL1160Query INSTANCE = new NSAL1160Query();

    /**
     * Private constructor
     */
    private NSAL1160Query() {
        super();
    }

    /**
     * Singleton instance getter
     * @return NSAL1160Query
     */
    public static NSAL1160Query getInstance() {
        return INSTANCE;
    }

    /**
     * searchHeaderInfo
     * @param bizMsg NSAL1160CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult searchHeaderInfo(NSAL1160CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("dsContrPk", bizMsg.dsContrPk_P.getValue());
        params.put("shipToCustAcctCd", bizMsg.shipToCustAcctCd_P.getValue());
        return getSsmEZDClient().queryObject("getSearchHeaderInfo", params);
    }

    /**
     * getSearchNotesInfo
     * @param params Map<String, Object>
     * @param sMsg NSAL1160SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSearchNotesInfo(NSAL1160SMsg sMsg, Map<String, Object> params) {
        return getSsmEZDClient().queryEZDMsgArray("getSearchNotesInfo", params, sMsg.A);
    }

    /**
     * searchWorkflowActionsInfo
     * @param params Map<String, Object>
     * @param sMsg NSAL1160SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSearchWorkflowActionsInfo(NSAL1160SMsg sMsg, Map<String, Object> params) {
        return getSsmEZDClient().queryEZDMsgArray("getSearchWorkflowActionsInfo", params, sMsg.B);
    }

    /**
     * getSearchEnforcementActions
     * @param params Map<String, Object>
     * @param sMsg NSAL1160SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSearchEnforcementActions(NSAL1160SMsg sMsg, Map<String, Object> params) {
        return getSsmEZDClient().queryEZDMsgArray("getSearchEnforcementActions", params, sMsg.C);
    }
}
