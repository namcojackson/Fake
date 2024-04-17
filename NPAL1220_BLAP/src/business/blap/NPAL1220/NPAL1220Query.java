/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NPAL1220;

import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * <pre>
 * Business ID : NPAL1220 ASL Search
 * Function Name : the data base access processing by SSM
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/17/2015   CITS      Takeshi Tokutomi      Create          N/A
 * 01/11/2018   CITS            S.Katsuma       Update          QC#12226
 * 
 *</pre>
 */
public final class NPAL1220Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NPAL1220Query MY_INSTANCE = new NPAL1220Query();

    /**
     * Constructor.
     */
    private NPAL1220Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NPAL1220Query
     */
    public static NPAL1220Query getInstance() {
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
     * Get Pulldown list of Qualifier Type
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getQualifierTypePulldownList(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getQualifierTypePulldownList", ssmParam);
    }

    /**
     * Get Pulldown list of Merchandise Type
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getMerchandiseTypePulldownList(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getMerchandiseTypePulldownList", ssmParam);
    }

    /**
     * Get Item Description
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getItemDescription(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("getItemDescription", ssmParam);
    }

    /**
     * Get Supplier Name
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSupplierName(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("getSupplierName", ssmParam);
    }

    /**
     * Search
     * @param ssmParam Map<String, Object>
     * @param sMsg NPAL1220SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult search(Map<String, Object> ssmParam, NPAL1220SMsg sMsg) {
        return getSsmEZDClient().queryEZDMsgArray("search", ssmParam, sMsg.A);
    }

    // START 2018/01/11 S.Katsuma [QC#12226,ADD]
    /**
     * Get Supplier Name
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSupplierSiteName(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("getSupplierSiteName", ssmParam);
    }
    // END 2018/01/11 S.Katsuma [QC#12226,ADD]
}
