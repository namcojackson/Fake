package business.blap.NWAL1500;

import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/02   Fujitsu         T.Ishii         Create          n/a
 * 2017/10/04   Fujitsu         S.Takami        Update          S21_NA#21300
 * 2019/03/08   Fujitsu         R.Nakamura      Update          S21_NA#30629
 * 2019/06/18   Fujitsu         K.Kato          Update          S21_NA#QC#50732
 *</pre>
 */

public final class NWAL1500QueryForLineCancel extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NWAL1500QueryForLineCancel MY_INSTANCE = new NWAL1500QueryForLineCancel();

    /**
     * Constructor.
     */
    private NWAL1500QueryForLineCancel() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NWAL1500Query
     */
    public static NWAL1500QueryForLineCancel getInstance() {
        return MY_INSTANCE;
    }

    public S21SsmEZDResult getUncancelableOrderForLineConfig(Map<String, Object> queryParam) {
        // 2017/10/04 S21_NA#21300 Mod Start
//        return getSsmEZDClient().queryObject("getUncancelableOrderForLineConfig", queryParam);
        return getSsmEZDClient().queryObjectList("getUncancelableOrderForLineConfig", queryParam);
        // 2017/10/04 S21_NA#21300 Mod End
    }

    public S21SsmEZDResult getLineQuantityForLineConfig(Map<String, Object> queryParam) {
        // 2017/10/04 S21_NA#21300 Mod Start
//        return getSsmEZDClient().queryObject("getLineQuantityForLineConfig", queryParam);
        return getSsmEZDClient().queryObjectList("getLineQuantityForLineConfig", queryParam);
        // 2017/10/04 S21_NA#21300 Mod End
    }

    // Add Start 2019/03/07 QC#30629
    public S21SsmEZDResult getLineQuantityForSetParent(Map<String, Object> queryParam) {
        return getSsmEZDClient().queryObjectList("getLineQuantityForSetParent", queryParam);
    }

    public S21SsmEZDResult getSetChildrenQty(Map<String, Object> queryParam) {
        return getSsmEZDClient().queryObjectList("getSetChildrenQty", queryParam);
    }
    // Add End 2019/03/07 QC#30629

    public S21SsmEZDResult getUncancelableOrderForRMA(Map<String, Object> queryParam) {
        return getSsmEZDClient().queryObject("getUncancelableOrderForRMA", queryParam);
    }

    public S21SsmEZDResult getLineQuantityForRMA(Map<String, Object> queryParam) {
        return getSsmEZDClient().queryObject("getLineQuantityForRMA", queryParam);
    }

    public S21SsmEZDResult getChildMdseQty(Map<String, Object> queryParam) {
        return getSsmEZDClient().queryObject("getChildMdseQty", queryParam);
    }

    public S21SsmEZDResult getMdseTpValSet(Map<String, Object> queryParam) {
        return getSsmEZDClient().queryObject("getMdseTpValSet", queryParam);
    }

    public S21SsmEZDResult getRetailEquipmentOrder(Map<String, Object> queryParam) {
        return getSsmEZDClient().queryObject("getRetailEquipmentOrder", queryParam);
    }

    // 2017/10/04 S21_NA#21300 Add Start
    public S21SsmEZDResult getUncancelableOrderForLineConfigLine(Map<String, Object> queryParam) {
        return getSsmEZDClient().queryObjectList("getUncancelableOrderForLineConfigLine", queryParam);
    }
    // 2017/10/04 S21_NA#21300 Add End

    // QC#26691
    public S21SsmEZDResult getSetChildren(Map<String, Object> queryParam, String stmtId) {
        return getSsmEZDClient().queryObjectList(stmtId, queryParam);
    }

    // 2019/06/18 S21_NA#50732 Add Start
    public S21SsmEZDResult getOpenedRwsForRmaLine(Map<String, Object> queryParam) {
        return getSsmEZDClient().queryObject("getOpenedRwsForRmaLine", queryParam);
    }
    // 2019/06/18 S21_NA#50732 Add End

}
