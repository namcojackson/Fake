/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL2340;

import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * SOM Address Mass Apply
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/08/22   CITS            S.Tanikawa      Create          N/A
 * 2017/09/20   Fujitsu         R.Nakamura      Update          QC#21114
 *</pre>
 */
public final class NWAL2340Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NWAL2340Query MY_INSTANCE = new NWAL2340Query();

    /**
     * Constructor.
     */
    private NWAL2340Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NWAL2340Query
     */
    public static NWAL2340Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * Search
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public Integer getCntAcct(Map<String, Object> ssmParam) {
        S21SsmEZDResult ssmResult = getSsmEZDClient().queryObject("getCntAcct", ssmParam);
        Integer resCnt = (Integer) ssmResult.getResultObject();

        return resCnt;
    }

    // Add Start 2017/09/20 QC#21114
    /**
     * Search
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getAcctInfoList(Map<String, Object> ssmParam) {

        return getSsmEZDClient().queryObjectList("getAcctInfoList", ssmParam);
    }
    // Add End 2017/09/20 QC#21114
}
