package business.blap.NFCL0510;

import java.util.Map;

//import business.blap.NFDL0010.constant.NFDL0010Constant;
import business.blap.NFCL0510.constant.NFCL0510Constant;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Lock box Error Correction Screen
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/21/2015   CSAI            K.Lee           Create          Initial
 * 02/16/2016   Fujitsu         T.Tanaka        Update          change constant
 * 12/05/2016   Fujitsu         S.Fujita        Update          QC#16255
 * 01/30/2018   Hitachi         Y.Takeno        Update          QC#19728
 * 06/11/2018   Hitachi         Y.Takeno        Create          QC#19723
 *</pre>
 */
//public class NFCL0510Query extends S21SsmEZDQuerySupport implements NFDL0010Constant {
public class NFCL0510Query extends S21SsmEZDQuerySupport implements NFCL0510Constant {

    /**
     * Singleton instance.
     */
    private static final NFCL0510Query myInstance = new NFCL0510Query();

    /**
     * Constructor
     */
    public NFCL0510Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NFDL0010Query
     */
    public static NFCL0510Query getInstance() {
        return myInstance;
    }

    /**
     * @param ssmParam Map<String, Object>
     * @param globalMsg NFDL0010SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getLockboxList(Map<String, Object> ssmParam, NFCL0510SMsg globalMsg) {
        return getSsmEZDClient().queryEZDMsgArray("getLockboxList", ssmParam, globalMsg.A);
    }

    /**
     * Get Receipt Invoice List
     * @param map Map<String, Object>
     * @param globalMsg NFDL0010SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getReceiptInvoiceList(Map<String, Object> map, NFCL0510SMsg globalMsg) {
        return getSsmEZDClient().queryEZDMsgArray("getReceiptInvoiceList", map, globalMsg.B);
    }

    /**
     * Get Lock box Status
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getLockBoxStatus(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getLockBoxStatus", ssmParam);
    }

    /**
     * Get Status Message Detail
     * @param bizMsg NFCL0510CMsg
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getStatusMsgDetail(NFCL0510CMsg bizMsg, Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getStatusMsgDetail", ssmParam);
    }

    // START 2018/01/30 [QC#19728, ADD]
    /**
     * Get AR_RCPT_RCV_INTFC
     * @param bizMsg NFCL0510CMsg
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getArRcprRcvIntfc(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getArRcprRcvIntfc", ssmParam);
    }
    // END  2018/01/30 [QC#19728, ADD]

    // START 2016/11/30 S.Fujita [QC#16255,DEL]
//    /**
//     * Get Max Update time
//     * @param bizMsg NFCL0510CMsg
//     * @param ssmParam Map<String, Object>
//     * @return S21SsmEZDResult
//     */
//    public S21SsmEZDResult getMaxUptime(NFCL0510CMsg bizMsg, Map<String, Object> ssmParam) {
//        return getSsmEZDClient().queryObject("getMaxUptime", ssmParam);
//    }
    // END   2016/11/30 S.Fujita [QC#16255,DEL]

    // START 2018/06/11 [QC#19723, MOD]
    /**
     * Get Receipt Invoice Download List
     * @param map Map<String, Object>
     * @param globalMsg NFDL0010SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getReceiptInvoiceDownloadList(Map<String, Object> map, NFCL0510SMsg globalMsg) {
        return getSsmEZDClient().queryEZDMsgArray("getReceiptInvoiceDownloadList", map, globalMsg.B);
    }
    // END   2018/06/11 [QC#19723, MOD]
}
