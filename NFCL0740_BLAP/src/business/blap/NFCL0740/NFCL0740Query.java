/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NFCL0740;

import java.math.BigDecimal;
import java.util.Map;

import business.blap.NFCL0740.constant.NFCL0740Constant;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * write-Off Request Creation
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/01   Fujitsu         T.Tanaka        Create          Initial
 * 2016/05/13   Fujitsu         C.Naito         Update          QC#7971
 * 2023/06/30   Hitachi         S.Fujita        Update          QC#60397
 *</pre>
 */
public class NFCL0740Query extends S21SsmEZDQuerySupport implements NFCL0740Constant {
    /**
     * Singleton instance.
     */
    private static final NFCL0740Query MY_INSTANCE = new NFCL0740Query();

    /**
     * Constructor
     */
    public NFCL0740Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NFDL0010Query
     */
    public static NFCL0740Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * @param bizMsg NFCL0740CMsg
     * @param ssmParam SSM Parameters
     * @return SSM Result
     */
    public S21SsmEZDResult getArAdjRsnPullDownList(NFCL0740CMsg bizMsg, Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getArAdjRsnPullDownList", ssmParam);
    }

    /**
     * @param bizMsg NFCL0740CMsg
     * @param ssmParam SSM Parameters
     * @return SSM Result
     */
    public S21SsmEZDResult getDsAcctNm(NFCL0740CMsg bizMsg, Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("getDsAcctNm", ssmParam);
    }

    // [QC#7971] INSERT start
    /**
     * getArAdjTpPullDownList
     * @param bizMsg NFCL0740CMsg
     * @param ssmParam SSM Parameters
     * @return SSM Result
     */
    public S21SsmEZDResult getArAdjTpPullDownList(NFCL0740CMsg bizMsg, Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getArAdjTpPullDownList", ssmParam);
    }
    // [QC#7971] INSERT end

    // START 2023/06/30 S.Fujita [QC#60397,ADD]

    /**
     * getHrTtlNm
     * @param bizMsg NFCL0740CMsg
     * @param ssmParam SSM Parameters
     * @return String
     */
    public String getHrTtlNm(NFCL0740CMsg bizMsg, Map<String, Object> ssmParam) {
        return (String) getSsmEZDClient().queryObject("getHrTtlNm",ssmParam).getResultObject();
    }

    /**
     * getHrTtlApvlLimitPk
     * @param bizMsg NFCL0740CMsg
     * @param ssmParam SSM Parameters
     * @return BigDecimal
     */
    public BigDecimal getHrTtlApvlLimitPk(NFCL0740CMsg bizMsg, Map<String, Object> ssmParam) {
        return (BigDecimal) getSsmEZDClient().queryObject("getHrTtlApvlLimitPk",ssmParam).getResultObject();
    }

    // END 2023/06/30 S.Fujita [QC#60397,ADD]
}
