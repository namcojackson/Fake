/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/07/22   Fujitsu         Mori            Create          N/A
 *</pre>
 */
package business.blap.NLBL0100;

import business.blap.NLBL0100.constant.NLBL0100Constant;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

public final class NLBL0100Query extends S21SsmEZDQuerySupport implements NLBL0100Constant {

    /**
     * Singleton instance.
     */
    private static final NLBL0100Query myInstance = new NLBL0100Query();

    /**
     * Constructor.
     */
    private NLBL0100Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return SSML0001Query
     */
    public static NLBL0100Query getInstance() {
        return myInstance;
    }

    /**
     * <pre>
     * Search BOLTrackingHistoryList
     * </pre>
     * 
     * @param cMsg
     * @param sMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getHistoryHeaderInfo(NLBL0100CMsg cMsg) {

        return getSsmEZDClient().queryEZDMsg("getHistoryHeaderInfo", cMsg, cMsg);
    }

    /**
     * <pre>
     * Search BOLTrackingHistoryList
     * </pre>
     * 
     * @param cMsg
     * @param sMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getHistoryDetailList(NLBL0100CMsg cMsg, NLBL0100SMsg sMsg) {

        return getSsmEZDClient().queryEZDMsgArray("getHistoryDetailList", cMsg, sMsg.A);
    }

}
