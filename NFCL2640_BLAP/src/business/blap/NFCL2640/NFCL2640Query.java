/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NFCL2640;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Statement Schedule Maintenance
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/25   Fujitsu         M.Ohno          Create          N/A
 *</pre>
 */
public final class NFCL2640Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NFCL2640Query MY_INSTANCE = new NFCL2640Query();

    /**
     * Private constructor
     */
    private NFCL2640Query() {
        super();
    }

    /**
     * Get the NFCL2640Query instance.
     * @return NFCL2640Query instance
     */
    public static NFCL2640Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * Search
     * @param cMsg NFCL2640CMsg
     * @param sMsg NFCL2640SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getArStmtCtrl(NFCL2640CMsg cMsg, NFCL2640SMsg sMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("arStmtIssCycleCd", cMsg.arStmtIssCycleCd.getValue());
        params.put("arStmtStsCd", cMsg.arStmtStsCd.getValue());
        if (cMsg.arStmtDt_FR.isClear()) {
            params.put("arStmtDt_FR", "00010101");
        } else {
            params.put("arStmtDt_FR", cMsg.arStmtDt_FR.getValue());
        }
        if (cMsg.arStmtDt_TO.isClear()) {
            params.put("arStmtDt_TO", "99991231");
        } else {
            params.put("arStmtDt_TO", cMsg.arStmtDt_TO.getValue());
        }
        return getSsmEZDClient().queryEZDMsgArray("getArStmtCtrl", params, sMsg.A);
    }

    /**
     * Count StmtDt
     * @param cMsg NFCL2640CMsg
     * @param sMsg NFCL2640SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult countStmtDt(NFCL2640CMsg cMsg, NFCL2640SMsg sMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("arStmtDt_AD", cMsg.arStmtDt_AD.getValue());
        return getSsmEZDClient().queryObject("countStmtDt", params);
    }

    /**
     * Max StmtDt
     * @param cMsg NFCL2640CMsg
     * @param sMsg NFCL2640SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult maxStmtDt(NFCL2640CMsg cMsg, NFCL2640SMsg sMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("arStmtIssCycleCd", cMsg.arStmtIssCycleCd.getValue());
        return getSsmEZDClient().queryObject("maxStmtDt", params);
    }

}
