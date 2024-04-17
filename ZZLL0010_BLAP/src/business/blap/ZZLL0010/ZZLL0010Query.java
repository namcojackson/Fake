package business.blap.ZZLL0010;

import java.util.HashMap;
import java.util.Map;

import business.blap.ZZLL0010.ZZLL0010CMsg;
import business.blap.ZZLL0010.ZZLL0010SMsg;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

public final class ZZLL0010Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final ZZLL0010Query myInstance = new ZZLL0010Query();

    /**
     * Constructor.
     */
    private ZZLL0010Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return ZZOL0010Query
     */
    public static ZZLL0010Query getInstance() {
        return myInstance;
    }

    /**
     * @param cMsg ZZOL0010CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getMntLogData(ZZLL0010CMsg cMsg, ZZLL0010SMsg sMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("cMsg", cMsg);

        return getSsmEZDClient().queryEZDMsgArray("getMntLogData", ssmParam, sMsg.A);

    }
}
