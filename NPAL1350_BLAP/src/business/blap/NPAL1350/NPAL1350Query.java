/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NPAL1350;

import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * <pre>
 * Business ID : NPAL1350 Kitting WO Search
 * Function Name : the data base access processing by SSM
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 04/20/2016   CITS        Makoto Okigami      Create          N/A
 * 03/14/2018   CITS        K.Fukumura          Update          QC#22324
 *</pre>
 */
public final class NPAL1350Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NPAL1350Query MY_INSTANCE = new NPAL1350Query();

    /**
     * Constructor.
     */
    private NPAL1350Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NPBL0010Query
     */
    public static NPAL1350Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * Get Pulldown list of Search Option
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSearchOptionPulldownList(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getSearchOptionPulldownList", ssmParam);
    }

    /**
     * Get Pulldown list of Work Order Status
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getWorkOrderStatusPulldownList(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getWorkOrderStatusPulldownList", ssmParam);
    }

    /**
     * Search
     * @param ssmParam Map<String, Object>
     * @param sMsg NPAL1350SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult search(Map<String, Object> ssmParam, NPAL1350SMsg sMsg) {
        return getSsmEZDClient().queryEZDMsgArray("search", ssmParam, sMsg.A);
    }
    // 2018/03/14 Add Start
    /**
     * Get Kit BOM
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getKitBom(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getKitBom", ssmParam);
    }

    /**
     * Get Cmpsn Msg
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCmpsnMsg(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getCmpsnMsg", ssmParam);
    }
    // 2018/03/14 Add End
}
