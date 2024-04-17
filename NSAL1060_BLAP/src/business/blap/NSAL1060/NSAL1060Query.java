package business.blap.NSAL1060;

import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/** <pre>
 * Meter Reading Popup.
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/25/2015   Fujitsu         M.Hara          Create          N/A
 * </pre>
 */
public final class NSAL1060Query extends S21SsmEZDQuerySupport {

    /** Singleton instance. */
    private static final NSAL1060Query MY_INSTANCE = new NSAL1060Query();

    /**
     * <pre>
     * Constructor.
     * </pre>
     */
    private NSAL1060Query() {
        super();
    }

    /**
     * <pre>
     * Get the NSAL0110Query instance.
     * </pre>
     * @return NSAL0110Query instance
     */
    public static NSAL1060Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * searchMtrReadList
     * @param param Map<String, Object>
     * @param sMsg  NSAL1060SMsg
     * @return  S21SsmEZDResult
     */
    public S21SsmEZDResult searchMtrReadList(Map<String, Object> param, NSAL1060SMsg sMsg) {
        return getSsmEZDClient().queryEZDMsgArray("getMtrReadList", param, sMsg.A);
    }
}
