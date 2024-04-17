/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL3200;

import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * <pre>
 * Business ID : NMAL3200 Marketing Data Analysis
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/24/2016   CITS            K.Ogino         Create          N/A
 *</pre>
 */
public final class NMAL3200Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NMAL3200Query MY_INSTANCE = new NMAL3200Query();

    /**
     * Constructor.
     */
    private NMAL3200Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NMAL3200Query
     */
    public static NMAL3200Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * Find multiple record query
     * @param statementId String
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult findMultipleRecord(String statementId, Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList(statementId, ssmParam);
    }

    /**
     * Find multiple record query
     * @param statementId String
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult findSingleRecord(String statementId, Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject(statementId, ssmParam);
    }
}
