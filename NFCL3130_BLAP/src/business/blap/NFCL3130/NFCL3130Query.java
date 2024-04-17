package business.blap.NFCL3130;

import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/12   Hitachi         K.Kojima        Update          QC#11049
 *</pre>
 */
public class NFCL3130Query extends S21SsmEZDQuerySupport {
    /**
     * Singleton instance.
     */
    private static final NFCL3130Query MY_INSTANCE = new NFCL3130Query();

    /**
     * private constructor.
     */
    private NFCL3130Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NFCL2660Query
     */
    public static NFCL3130Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param cMsg NFCL3130CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult findRcptData(Map map, NFCL3130CMsg cMsg) {
        return getSsmEZDClient().queryEZDMsg("findRcptSrc", map, cMsg);
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param cMsg NFCL3130CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult findBankAcct(Map map, NFCL3130CMsg cMsg) {
        return getSsmEZDClient().queryEZDMsg("findBankAcct", map, cMsg);
    }

    // START 2016/07/12 K.Kojima [QC#11576,DEL]
    // /**
    // *
    // * @param bizMsg
    // * @param ssmParam
    // * @return
    // */
    // public S21SsmEZDResult findBankAcctByRcptSrc(NFCL3130CMsg
    // bizMsg, Map<String, Object> ssmParam) {
    // return
    // getSsmEZDClient().queryObjectList("findBankAcctByRcptSrc",
    // ssmParam);
    // }
    // END 2016/07/12 K.Kojima [QC#11576,DEL]

    // START 2016/07/12 K.Kojima [QC#11049,ADD]
    /**
     * @param ssmParam Map<String, Object>
     * @return
     */
    public S21SsmEZDResult findBankAcctByRcptSrc(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("findBankAcctByRcptSrc", ssmParam);
    }

    // END 2016/07/12 K.Kojima [QC#11049,ADD]

    /**
     * 
     * @param bizMsg
     * @param ssmParam
     * @return
     */
    public S21SsmEZDResult findMaxCd(NFCL3130CMsg bizMsg, Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("findMaxCd", ssmParam);
    }
}
