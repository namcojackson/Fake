/**
 * <Pre>Copyright(c)2013 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL0590;

import java.util.HashMap;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/30   Hitachi         J.Kim           Create          N/A
 *</pre>
 */
public final class NSAL0590Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance
     */
    private static final NSAL0590Query INSTANCE = new NSAL0590Query();

    /**
     * Private constructor
     */
    private NSAL0590Query() {
        super();
    }

    /**
     * Singleton instance getter
     * @return NSAL0590Query
     */
    public static NSAL0590Query getInstance() {
        return INSTANCE;
    }

    /**
     * getDsContracReportGroupInfoList
     * @param params HashMap<String, Object>
     * @param globalMsg NSAL0590CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDsContracReportGroupInfoList(HashMap<String, Object> params, NSAL0590SMsg globalMsg) {
        return getSsmEZDClient().queryEZDMsgArray("getDsContracReportGroupInfoList", params, globalMsg.A);
    }
}
