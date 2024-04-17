/**
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLGL0060;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2020/01/09   CITS            M.Furugoori         Create          N/A
 * </pre>
 */
public class NLGL0060Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NLGL0060Query INSTANCE = new NLGL0060Query();

    /**
     * Constructor.
     */
    public NLGL0060Query() {
        super();
    }

    /**
     * <p>
     * Gets the singleton instance.
     * </p>
     * @return the instance of NLGL0060Query
     */
    public static NLGL0060Query getInstance() {
        return INSTANCE;
    }

    /**
     * getTaskList
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getTaskList(Map<String, String> ssmParam) {
        return getSsmEZDClient().queryObjectList("getTaskList", ssmParam);
    }

    /**
     * <p>
     * Search
     * </p>
     * @param cMsg NLGL0060CMsg
     * @param sMsg NLGL0060SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult search(NLGL0060CMsg cMsg, NLGL0060SMsg sMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("whCd", cMsg.whCd_H);
        params.put("wmsTaskCd", cMsg.wmsTaskCd_H);
        params.put("procStsCd", cMsg.procStsCd_H);
        params.put("wmsInbdTrxPk", cMsg.wmsInbdTrxPk_H);
        params.put("wmsMdseCd", cMsg.wmsMdseCd_H);
        params.put("rownum", sMsg.A.length() + 1);

        return getSsmEZDClient().queryEZDMsgArray("search", params, sMsg.A);
    }

}
