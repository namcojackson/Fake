package business.blap.ZZZL0061;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

public final class ZZZL0061Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final ZZZL0061Query myInstance = new ZZZL0061Query();

    /**
     * Constructor.
     */
    private ZZZL0061Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return ZZZL0061Query
     */
    public static ZZZL0061Query getInstance() {
        return myInstance;
    }

    /**
     * @param cMsg ZZOL0010CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getBatProcLogMV(ZZZL0061CMsg cMsg, ZZZL0061SMsg sMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("cMsg", cMsg);

        return getSsmEZDClient().queryEZDMsgArray("getBatProcLogMV", ssmParam, sMsg.A);

    }
}
