package business.blap.ZZZL0071;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

public final class ZZZL0071Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final ZZZL0071Query myInstance = new ZZZL0071Query();

    /**
     * Constructor.
     */
    private ZZZL0071Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return ZZZL0071Query
     */
    public static ZZZL0071Query getInstance() {
        return myInstance;
    }

    /**
     * @param cMsg ZZOL0010CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getBatProcLogMV(ZZZL0071CMsg cMsg, ZZZL0071SMsg sMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("cMsg", cMsg);

        return getSsmEZDClient().queryEZDMsgArray("getBatProcLogMV", ssmParam, sMsg.A);

    }
}
