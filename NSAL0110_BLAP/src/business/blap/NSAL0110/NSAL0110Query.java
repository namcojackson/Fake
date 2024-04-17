/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0110;

import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/** 
 *<pre>
 *
 * Contract Popup
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/07/12   Hitachi         Y.Igarashi         Create          N/A
 * 2015/11/02   Hitachi         K.Kasai            Update          N/A
 * 2015/12/11   Hitachi         T.Kanasaka         Update          QC#1586
 *</pre>
 */
public final class NSAL0110Query extends S21SsmEZDQuerySupport {

    /** Singleton instance. */
    private static final NSAL0110Query MY_INSTANCE = new NSAL0110Query();

    /**
     * <pre>
     * Constructor.
     * </pre>
     */
    private NSAL0110Query() {
        super();
    }

    /**
     * <pre>
     * Get the NSAL0110Query instance.
     * </pre>
     * @return NSAL0110Query instance
     */
    public static NSAL0110Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * 
     * searchContractList
     * 
     * @param queryParam Search Condition
     * @param sMsg searchContractList
     * @return Search Result
     */
    public S21SsmEZDResult searchContractList(Map<String, Object> queryParam, NSAL0110SMsg sMsg) {
        return getSsmEZDClient().queryEZDMsgArray("searchContractList", queryParam, sMsg.A);
    }

    // START 2015/12/11 T.Kanasaka [QC#1586, DEL]
//    public S21SsmEZDResult searchContrCtrStsHeader(Map<String, Object> param) {
//        return getSsmEZDClient().queryObjectList("getContrCtrStsHeader", param);
//    }
    // END 2015/12/11 T.Kanasaka [QC#1586, DEL]

    // START 2015/12/11 T.Kanasaka [QC#1586, ADD]
    public S21SsmEZDResult searchContrCtrSts(Map<String, Object> param) {
        return getSsmEZDClient().queryObjectList("getContrCtrSts", param);
    }
    // END 2015/12/11 T.Kanasaka [QC#1586, ADD]

    public S21SsmEZDResult searchContractCategory(Map<String, Object> param) {
        return getSsmEZDClient().queryObjectList("getContractCategory", param);
    }

    public S21SsmEZDResult searchContractClass(Map<String, Object> param) {
        return getSsmEZDClient().queryObjectList("getContractClass", param);
    }

    public S21SsmEZDResult searchContractDetailType(Map<String, Object> param) {
        return getSsmEZDClient().queryObjectList("getContractDetailType", param);
    }

    // START 2015/12/11 T.Kanasaka [QC#1586, DEL]
//    public S21SsmEZDResult searchContractDetailStatus(Map<String, Object> param) {
//        return getSsmEZDClient().queryObjectList("getContractDetailStatus", param);
//    }
    // END 2015/12/11 T.Kanasaka [QC#1586, DEL]
}

