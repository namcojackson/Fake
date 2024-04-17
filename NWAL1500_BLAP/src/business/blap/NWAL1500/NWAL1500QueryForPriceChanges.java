package business.blap.NWAL1500;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/02   Fujitsu         T.Ishii         Create          n/a
 *</pre>
 */

public final class NWAL1500QueryForPriceChanges extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NWAL1500QueryForPriceChanges MY_INSTANCE = new NWAL1500QueryForPriceChanges();

    /**
     * Constructor.
     */
    public NWAL1500QueryForPriceChanges() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NWAL1500Query
     */
    public static NWAL1500QueryForPriceChanges getInstance() {
        return MY_INSTANCE;
    }

    /**
     * <pre>
     * @param glblCmpyCd    String
     * @param mdseCd        String
     * @return  S21SsmEZDResult
     * </pre>
     */
    public S21SsmEZDResult getInPondWt(String glblCmpyCd, String mdseCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("mdseCd", mdseCd);

        return getSsmEZDClient().queryObject("getInPondWt", params);
    }

}
