package business.blap.NMAL7110;

import static business.blap.NMAL7110.constant.NMAL7110Constant.BIZ_ID;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/12/2015   Fujitsu         M.Hara          Create          N/A
 *</pre>
 */
public final class NMAL7110Query extends S21SsmEZDQuerySupport {
    /** Singleton instance. */
    private static final NMAL7110Query MY_INSTANCE = new NMAL7110Query();

    /**
     * <pre>
     * Constructor.
     * </pre>
     */
    private NMAL7110Query() {
        super();
    }

    /**
     * <pre>
     * Get the NMAL7110Query instance.
     * </pre>
     * @return NMAL7110Query instance
     */
    public static NMAL7110Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * searchPrdcContrList
     * @param param Map<String, Object>
     * @param sMsg  NMAL7110SMsg
     * @return  S21SsmEZDResult
     */
    public S21SsmEZDResult searchPrdcContrList(Map<String, Object> param, NMAL7110SMsg sMsg) {
        return getSsmEZDClient().queryEZDMsgArray("getPrdcContrList", param, sMsg.A);
    }

    /**
     * getSavedSearchOptionList
     * @param usrId user id
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSavedSearchOptionList(String usrId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("srchOptAplId", BIZ_ID);
        params.put("srchOptUsrId", usrId);

        return getSsmEZDClient().queryObjectList("getSavedSearchOptionList", params);
    }
}
