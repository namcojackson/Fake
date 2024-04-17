package business.blap.NWAL1500;

import java.math.BigDecimal;
import java.util.Map;

import business.db.ORD_TAKE_MDSETMsg;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/02   Fujitsu         T.Ishii         Create          n/a
 * 2018/01/23   Fujitsu         S.Takami        Update          S21_NA#23650
 *</pre>
 */

public final class NWAL1500QueryForConfigId extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NWAL1500QueryForConfigId MY_INSTANCE = new NWAL1500QueryForConfigId();

    /**
     * Constructor.
     */
    private NWAL1500QueryForConfigId() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NWAL1500Query
     */
    public static NWAL1500QueryForConfigId getInstance() {
        return MY_INSTANCE;
    }

    public S21SsmEZDResult getComponentList(Map<String, Object> queryParam) {
        return getSsmEZDClient().queryObjectList("getComponentList", queryParam);
    }

    public S21SsmEZDResult getCoaProd(Map<String, Object> queryParam) {
        return getSsmEZDClient().queryObject("getCoaProd", queryParam);
    }

    public S21SsmEZDResult getPrimaryOrdLineCatg(Map<String, Object> queryParam) {
        return getSsmEZDClient().queryObject("getPrimaryOrdLineCatg", queryParam);
    }

    public S21SsmEZDResult getPrimaryRtlSubLoc(Map<String, Object> queryParam) {
        return getSsmEZDClient().queryObject("getPrimaryRtlSubLoc", queryParam);
    }

    public S21SsmEZDResult getChildMdseList(Map<String, Object> queryParam) {
        // 2018/01/23 S21_NA#23650 Add Start
        int size = new ORD_TAKE_MDSETMsg().getAttr("ordTakeMdseCd").getDigit();
        queryParam.put("ordTakeLen", new BigDecimal(size));
        // 2018/01/23 S21_NA#23650 Add End
        return getSsmEZDClient().queryObjectList("getChildMdseList", queryParam);
    }
}
