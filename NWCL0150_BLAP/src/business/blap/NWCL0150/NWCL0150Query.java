/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWCL0150;

import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * <pre>
 * NWCL0140 CFS Lease Package Maintenance Screen
 * Function Name : the data base access processing by SSM
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/09/2016   CITS            K.Ogino         Create          N/A
 *</pre>
 */
public final class NWCL0150Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NWCL0150Query MY_INSTANCE = new NWCL0150Query();

    /**
     * Constructor.
     */
    private NWCL0150Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NWCL0140Query
     */
    public static NWCL0150Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * Find single record query
     * @param statementId String
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult findSingleRecord(String statementId, Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject(statementId, ssmParam);
    }
}
