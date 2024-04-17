package business.blap.NFDL0040;

import java.util.Map;

import business.blap.NFDL0040.constant.NFDL0040Constant;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Promise/Dispute Entry
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/21/2015   CSAI            K.Lee           Create          Initial
 * 2016/04/25   Fujitsu         C.Naito         Update          QC#6269-2
 * 2023/05/26   Hitachi         S.Nakatani      Update          QC#61271
 *</pre>
 */
public class NFDL0040Query extends S21SsmEZDQuerySupport implements NFDL0040Constant {

    /**
     * Singleton instance.
     */
    private static final NFDL0040Query myInstance = new NFDL0040Query();

    /**
     * Constructor
     */
    public NFDL0040Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NFDL0010Query
     */
    public static NFDL0040Query getInstance() {
        return myInstance;
    }

    /**
     * @param map Map
     * @param globalMsg NFDL0010SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPromise(Map map, NFDL0040SMsg globalMsg) {
        return getSsmEZDClient().queryEZDMsgArray("getPromise", map, globalMsg.A);
    }

    /**
     * @param map Map
     * @param globalMsg NFDL0010SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDispute(Map map, NFDL0040SMsg globalMsg) {
        return getSsmEZDClient().queryEZDMsgArray("getDispute", map, globalMsg.B);
    }

    /**
     * @param map Map
     * @param globalMsg NFDL0010SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCollectablePromiseCount(Map map) {
        return getSsmEZDClient().queryObject("countCollectablePromise", map);
    }

    /**
     * @param map Map
     * @param globalMsg NFDL0010SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getActualInvoiceBalance(Map map) {
        return getSsmEZDClient().queryObject("getActualInvoiceBalance", map);
    }

    // [QC#6269-2] INSERT start
    /**
     * @param map Map
     * @param globalMsg NFDL0040SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getArTrxBalData(Map map) {
        return getSsmEZDClient().queryObject("getArTrxBalData", map);
    }
    // [QC#6269-2] INSERT end

    // START 2023/05/26 S.Nakatani [QC#61271,ADD]
    /**
     * @param map Map
     * @param globalMsg NFDL0010SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPromiseForMultiInv(Map map, NFDL0040SMsg globalMsg) {
        return getSsmEZDClient().queryEZDMsgArray("getPromiseForMultiInv", map, globalMsg.A);
    }

    /**
     * @param map Map
     * @param globalMsg NFDL0010SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDisputeForMultiInv(Map map, NFDL0040SMsg globalMsg) {
        return getSsmEZDClient().queryEZDMsgArray("getDisputeForMultiInv", map, globalMsg.B);
    }

    /**
     * @param map Map
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getOpenDisputeCount(Map map) {
        return getSsmEZDClient().queryObject("countOpenDispute", map);
    }
    // END 2023/05/26 S.Nakatani [QC#61271,ADD]

}
