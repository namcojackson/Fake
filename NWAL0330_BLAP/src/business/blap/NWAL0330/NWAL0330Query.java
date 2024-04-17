/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
/**
 * <pre>
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/23/2010   Fujitsu         S.Yamamoto      Create          540(All2)
 * </pre>
 */
package business.blap.NWAL0330;

import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

public final class NWAL0330Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NWAL0330Query myInstance = new NWAL0330Query();

    /**
     * Constructor.
     */
    private NWAL0330Query() {
        super();
    }

    /**
     * <pre>
     * get Aging Amout
     * </pre>
     * @param queryParam SSM Query Parameter
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getAgingAmt(Map<String, String> queryParam) {

        return getSsmEZDClient().queryObjectList("getAgingAmt", queryParam);
    }
    
    /**
     * <pre>
     * get On Credit Hold Amount
     * </pre>
     * @param queryParam SSM Query Parameter
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getOnCreditHoldAmt(Map<String, String> queryParam) {

        return getSsmEZDClient().queryObjectList("getOnCreditHoldAmt", queryParam);
    }

    /**
     * <pre>
     * get Approved Credits Amount
     * </pre>
     * @param queryParam SSM Query Parameter
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getApprovedCredits(Map<String, String> queryParam) {

        return getSsmEZDClient().queryObject("getApprovedCredits", queryParam);
    }

    /**
     * Singleton instance getter.
     * @return NWAL0330Query
     */
    public static NWAL0330Query getInstance() {
        return myInstance;
    }

}
