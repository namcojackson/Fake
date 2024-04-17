/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0060;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/** 
 *<pre>
 *
 * Model Group Maintenance
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/08/2013   Hitachi         Y.Igarashi         Create          N/A
 *</pre>
 */
public final class NSAL0060Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NSAL0060Query INSTANCE = new NSAL0060Query();

    /**
     * Constructor.
     */
    private NSAL0060Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NSAL0060Query
     */
    public static NSAL0060Query getInstance() {
        return INSTANCE;
    }


    /**
     * 
     * Get next Model Group ID
     * 
     * @param glblCmpyCd Group Company Code
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getNextModelGroupId(String glblCmpyCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);

        return getSsmEZDClient().queryObject("getNextModelGroupId", ssmParam);
    }
}
