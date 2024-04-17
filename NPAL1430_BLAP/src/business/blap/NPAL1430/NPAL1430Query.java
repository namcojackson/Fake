/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NPAL1430;

import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * <pre>
 * Business ID : NPAL1430 Reman Standard Parts Setup
 * Function Name : the data base access processing by SSM
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/18/2016   CITS            S.Tanikawa      Create          N/A
 * 08/28/2016   CITS            T.Gotoda        Update          QC#13404
 * 
 *</pre>
 */

public final class NPAL1430Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NPAL1430Query MY_INSTANCE = new NPAL1430Query();

    /**
     * Constructor.
     */
    private NPAL1430Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NPAL1430Query
     */
    public static NPAL1430Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * Search
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult search(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("search", ssmParam);
    }

    /**
     * Search
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult searchModel(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("searchModel", ssmParam);
    }

    /**
     * Check Mdse Code
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult chkMdseCd(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("chkMdseCd", ssmParam);
    }

    /**
     * chkMdseIntangible
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult chkMdseIntangible(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("chkMdseIntangible", ssmParam);
    }

}
