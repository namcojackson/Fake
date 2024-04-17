package business.blap.ZZOL0010;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

public final class ZZOL0010Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final ZZOL0010Query myInstance = new ZZOL0010Query();

    /**
     * Constructor.
     */
    private ZZOL0010Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return ZZOL0010Query
     */
    public static ZZOL0010Query getInstance() {
        return myInstance;
    }

    /**
     * @param cMsg ZZOL0010CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getAOM02Data(ZZOL0010CMsg cMsg, ZZOL0010SMsg sMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("cMsg", cMsg);

        return getSsmEZDClient().queryEZDMsgArray("getAOM02Data", ssmParam, sMsg.A);

    }
}
