package business.blap.ZZOL0052;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

public final class ZZOL0052Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final ZZOL0052Query myInstance = new ZZOL0052Query();

    /**
     * Constructor.
     */
    private ZZOL0052Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return ZZOL0051Query
     */
    public static ZZOL0052Query getInstance() {
        return myInstance;
    }

    /**
     * get search result
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     * @return S21SsmEZDResult search result
     */
    public S21SsmEZDResult getList(ZZOL0052CMsg cMsg) {
        
        // set parameter
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd"      , cMsg.glblCmpyCd.getValue());
        ssmParam.put("upldCsvRstProcNm", cMsg.upldCsvRstProcNm.getValue());
        
        return getSsmEZDClient().queryEZDMsgArray("getList", ssmParam, cMsg.A);
    }

}
