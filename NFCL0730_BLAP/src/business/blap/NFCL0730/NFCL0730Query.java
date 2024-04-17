package business.blap.NFCL0730;

import java.math.BigDecimal;
import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

import business.blap.NFCL0730.constant.NFCL0730Constant;

/**
 *<pre>
 * invoice Adjustment
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/17/2015   Fujitsu         T.Tanaka        Create          Initial
 * 04/13/2016   CSAI            K.Uramori       Update          QC#7012
 * 06/30/2023   Hitachi         S.Fujita        Update          QC#60397
 * 08/02/2023   Hitachi         S.Fujita        Update          QC#60397
 *</pre>
 */
public class NFCL0730Query extends S21SsmEZDQuerySupport implements NFCL0730Constant {
    
    /**
     * Singleton instance.
     */
    private static final NFCL0730Query myInstance = new NFCL0730Query();
    
    /**
     * Constructor
     */
    public NFCL0730Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NFDL0010Query
     */
    public static NFCL0730Query getInstance() {
        return myInstance;
    }

    public S21SsmEZDResult getDetails(NFCL0730CMsg bizMsg, Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryEZDMsgArray("getDetails", ssmParam, bizMsg.B);
    }

    public S21SsmEZDResult getResultDetails(NFCL0730CMsg bizMsg, Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryEZDMsgArray("getResultDetails", ssmParam, bizMsg.B);
    }

    //---- start add 2016/04/13 QC#7012
    /**
     * <pre>
     * </pre>
     * 
     * @param map Map
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult findArAdjTpForAdj( Map<String, Object> ssmParam) {

        return getSsmEZDClient().queryObjectList("findArAdjTpForAdj", ssmParam);
    }
    //---- end 2016/04/13
    // START 2023/06/30 S.Fujita [QC#60397,ADD]

    /**
     * getHrTtlNm
     * @param bizMsg NFCL0730CMsg
     * @param ssmParam SSM Parameters
     * @return String
     */
    public String getHrTtlNm(NFCL0730CMsg bizMsg, Map<String, Object> ssmParam) {
        return (String) getSsmEZDClient().queryObject("getHrTtlNm",ssmParam).getResultObject();
    }

    /**
     * getHrTtlApvlLimitPk
     * @param bizMsg NFCL0730CMsg
     * @param ssmParam SSM Parameters
     * @return BigDecimal
     */
    public BigDecimal getHrTtlApvlLimitPk(NFCL0730CMsg bizMsg, Map<String, Object> ssmParam) {
        return (BigDecimal) getSsmEZDClient().queryObject("getHrTtlApvlLimitPk",ssmParam).getResultObject();
    }

    // END 2023/06/30 S.Fujita [QC#60397,ADD]
}
