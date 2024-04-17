/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL0200;

import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * <pre>
 * Business ID : NMAL0200 Product Categorization Maintenance
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/07/2016   CITS            K.Ogino         Create          N/A
 *</pre>
 */
public final class NMAL0200Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NMAL0200Query MY_INSTANCE = new NMAL0200Query();

    /**
     * Constructor.
     */
    private NMAL0200Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NMAL0200Query
     */
    public static NMAL0200Query getInstance() {
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
