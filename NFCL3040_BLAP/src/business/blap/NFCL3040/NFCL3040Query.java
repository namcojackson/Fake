package business.blap.NFCL3040;

import java.util.Map;

import business.blap.NFCL3040.NFCL3040SMsg;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

public class NFCL3040Query  extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NFCL3040Query MY_INSTANCE = new NFCL3040Query();

    /**
     * Private constructor
     */
    private NFCL3040Query() {
        super();
    }

    /**
     * Get the NLEL0060Query instance.
     * @return NLEL0060Query instance
     */
    public static NFCL3040Query getInstance() {
        return MY_INSTANCE;
    }
    /**
     * @param globalMsg NFCL3040SMsg
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult searchBatchList(NFCL3040SMsg globalMsg, Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryEZDMsgArray("searchBatchList", ssmParam, globalMsg.A);
    }
}
