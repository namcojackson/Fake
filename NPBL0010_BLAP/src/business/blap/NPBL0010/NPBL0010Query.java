/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NPBL0010;

import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * <pre>
 * Business ID : NPBL0010 Inventory Request Listh
 * Function Name : the data base access processing by SSM
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/12/2016   CITS        Makoto Okigami      Create          N/A
 * 
 *</pre>
 */
public final class NPBL0010Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NPBL0010Query MY_INSTANCE = new NPBL0010Query();

    /**
     * Constructor.
     */
    private NPBL0010Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NPBL0010Query
     */
    public static NPBL0010Query getInstance() {
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
     * Search Header
     * @param ssmParam Map<String, Object>
     * @param sMsg NPBL0010SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult searchHeader(Map<String, Object> ssmParam, NPBL0010SMsg sMsg) {
        return getSsmEZDClient().queryEZDMsgArray("searchHeader", ssmParam, sMsg.A);
    }

    /**
     * Search Detail
     * @param ssmParam Map<String, Object>
     * @param sMsg NPBL0010SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult searchDetail(Map<String, Object> ssmParam, NPBL0010SMsg sMsg) {
        return getSsmEZDClient().queryEZDMsgArray("searchDetail", ssmParam, sMsg.A);
    }

    /**
     * Search Serial
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult searchSerial(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("searchSerial", ssmParam);
    }

}
