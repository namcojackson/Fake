package business.blap.NSAL1140;

import java.util.List;
import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Supply Watch.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/12   Hitachi         T.Nishimura     Create          N/A
 * 2017/05/24   Hitachi         K.Kitachi       Update          QC#18315
 *</pre>
 */
public final class NSAL1140Query extends S21SsmEZDQuerySupport {

    /** instance */
    private static final NSAL1140Query INSTANCE = new NSAL1140Query();

    /**
     * Private constructor
     */
    private NSAL1140Query() {
        super();
    }

    /**
     * Get instance
     * @return NSAL0510Query singleton instance
     */
    public static NSAL1140Query getInstance() {
        return INSTANCE;
    }

    /**
     * getSearchData.
     * @param sc Map<String, Object>
     * @param sMsg NSAL1140SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSearchData(Map<String, Object> sc, NSAL1140SMsg sMsg) {
        return getSsmEZDClient().queryEZDMsgArray("getSearchData", sc, sMsg.A);
    }

    /**
     * getStatusHistory.
     * @param sc Map<String, Object>
     * @param cMsg NSAL1140CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getStatusHistory(Map<String, Object> sc, NSAL1140CMsg cMsg) {
        return getSsmEZDClient().queryEZDMsgArray("getStatusHistory", sc, cMsg.B);

    }

    // START 2017/05/24 K.Kitachi [QC#18315, ADD]
    /**
     * getApproverPulldownList.
     * @param param Map<String, Object>
     * @return List<Map<String, Object>>
     */
    public List<Map<String, Object>> getApproverPulldownList(Map<String, Object> param) {
        return (List<Map<String, Object>>) getSsmEZDClient().queryObjectList("getApproverPulldownList", param).getResultObject();
    }
    // END 2017/05/24 K.Kitachi [QC#18315, ADD]
}
