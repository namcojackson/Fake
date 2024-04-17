/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NPAL1520;

import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * <pre>
 * Business ID : NPAL1520 Min-Max Planning Inquiry
 * Function Name : The data base access processing by SSM
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/11/2016   CITS            Keiichi Masaki  Create          N/A
 *</pre>
 */
public final class NPAL1520Query extends S21SsmEZDQuerySupport {
    /**
     * Singleton instance.
     */
    private static final NPAL1520Query MY_INSTANCE = new NPAL1520Query();

    /**
     * Constructor.
     */
    private NPAL1520Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return  NPAL1520Query
     */
    public static NPAL1520Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * Get Pulldown list of SearchOption
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSearchOptionPulldownList(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getSearchOptionPulldownList", ssmParam);
    }

    /**
     * Get Item Description
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getItemDescription(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getItemDescription", ssmParam);
    }

    /**
     * Get Manager Name
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getManagerName(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getManagerName", ssmParam);
    }

    /**
     * Search
     * @param ssmParam Map<String, Object>
     * @param sMsg NPAL1520SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult search(Map<String, Object> ssmParam, NPAL1520SMsg sMsg) {
        return getSsmEZDClient().queryEZDMsgArray("search", ssmParam, sMsg.A);
    }

}
