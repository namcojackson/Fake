/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NPAL1270;

import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * <pre>
 * Business ID : NPAL1270 PO Requisition List
 * Function Name : the data base access processing by SSM
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/12/2016   CITS            R Shimamoto     Create          N/A
 * 
 *</pre>
 */
public final class NPAL1270Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NPAL1270Query MY_INSTANCE = new NPAL1270Query();

    /**
     * Constructor.
     */
    private NPAL1270Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NPAL1270Query
     */
    public static NPAL1270Query getInstance() {
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
     * Get Pulldown list of Requisition Type
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRequisitionTypePulldownList(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getRequisitionTypePulldownList", ssmParam);
    }

    /**
     * Get Pulldown list of Document Source Type
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDocumentSourceTypePulldownList(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getDocumentSourceTypePulldownList", ssmParam);
    }

    /**
     * Get Pulldown list of Business Unit
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getBusinessUnitPulldownList(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getBusinessUnitPulldownList", ssmParam);
    }

    /**
     * Search
     * @param ssmParam Map<String, Object>
     * @param sMsg NPAL1270SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult search(Map<String, Object> ssmParam, NPAL1270SMsg sMsg) {
        return getSsmEZDClient().queryEZDMsgArray("search", ssmParam, sMsg.A);
    }

}
