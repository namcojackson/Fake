package business.blap.NWAL1500;

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

public final class NWAL1500QueryForMeterEntry extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NWAL1500QueryForMeterEntry MY_INSTANCE = new NWAL1500QueryForMeterEntry();

    /**
     * Constructor.
     */
    private NWAL1500QueryForMeterEntry() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NWAL1500Query
     */
    public static NWAL1500QueryForMeterEntry getInstance() {
        return MY_INSTANCE;
    }

    public S21SsmEZDResult getConversionProcessTypeTxt(Map<String, Object> queryParam) {
        return getSsmEZDClient().queryObject("getConversionProcessTypeTxt", queryParam);
    }
}
