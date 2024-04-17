package business.blap.ZZIL0030;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/12/12   Fujitsu         C.Ogaki         Create          N/A
 *</pre>
 */
public final class ZZIL0030Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final ZZIL0030Query MY_INSTANCE = new ZZIL0030Query();

    /**
     * Constructor.
     */
    private ZZIL0030Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return SSML0001Query
     */
    public static ZZIL0030Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * search Interface List from MDB_INBD_INTFC_CONFIG
     * @param cMsg ZZIL0030CMsg
     * @param sMsg ZZIL0030SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getTargetIFList(ZZIL0030CMsg cMsg, ZZIL0030SMsg sMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("cMsg", cMsg);
        ssmParam.put("rowNum", sMsg.A.length() + 1);

        return getSsmEZDClient().queryEZDMsgArray("getTargetIFList", ssmParam, sMsg.A);
    }
}
