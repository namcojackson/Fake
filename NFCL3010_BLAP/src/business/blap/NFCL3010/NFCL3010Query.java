package business.blap.NFCL3010;

import java.util.Map;

import business.blap.NFCL3010.constant.NFCL3010Constant;
import business.db.CLT_PTFOTMsg;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Batch Receipt Inquiry
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/21/2015   CSAI            K.Lee           Create          Initial
 * 2018/03/16   Fujitsu         H.Ikeda         Update          QC#21717
 * 2018/06/08   Hitachi         E.Kameishi      Update          QC#25735
 * 2018/08/22   CITS            K.Kameoka       Update          QC#26750
 * 2021/10/01   CITS            S.Go            Update          QC#57903
 *</pre>
 */
public class NFCL3010Query extends S21SsmEZDQuerySupport implements NFCL3010Constant {

    /**
     * Singleton instance.
     */
    private static final NFCL3010Query myInstance = new NFCL3010Query();

    /**
     * Constructor
     */
    public NFCL3010Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NFDL0010Query
     */
    public static NFCL3010Query getInstance() {
        return myInstance;
    }

    // Start 2018/08/22 K.Kameoka [QC#26750,DEL]
//    /**
//     * @param globalMsg NFCL3010SMsg
//     * @param ssmParam Map<String, Object>
//     * @return S21SsmEZDResult
//     */
//    public S21SsmEZDResult getBatRcptList(NFCL3010SMsg globalMsg, Map<String, Object> ssmParam) {
//        return getSsmEZDClient().queryEZDMsgArray("getBatRcptList", ssmParam, globalMsg.A);
//    }
    // End 2018/08/22 K.Kameoka [QC#26750,DEL]

    // START 2018/03/16 H.ikeda [QC#21737,ADD]
    /**
     * @param globalMsg NFCL3010SMsg
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRcptDataList(NFCL3010SMsg globalMsg, Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryEZDMsgArray("getRcptDataList", ssmParam, globalMsg.A);
    }
    // End   2018/03/16 H.ikeda [QC#21737,ADD]

    // Start 2018/03/16 H.ikeda [QC#21737,DEL]
//    /**
//     * @param globalMsg NFCL3010SMsg
//     * @param ssmParam Map<String, Object>
//     * @return S21SsmEZDResult
//     */
//    public S21SsmEZDResult getRcptList(NFCL3010SMsg globalMsg, Map<String, Object> ssmParam) {
//        return getSsmEZDClient().queryEZDMsgArray("getRcptList", ssmParam, globalMsg.B);
//    }
    // End 2018/03/16 H.ikeda [QC#21737,DEL]
    
    /**
     * @param bizMsg NFCL3010CMsg
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getBankPullDownList(NFCL3010CMsg bizMsg, Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getBankPullDownList", ssmParam);
    }

    /**
     * @param bizMsg NFCL3010CMsg
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getBankBrPullDownList(NFCL3010CMsg bizMsg, Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getBankBrPullDownList", ssmParam);
    }

    /**
     * @param bizMsg NFCL3010CMsg
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getBankAccountPullDownList(NFCL3010CMsg bizMsg, Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getBankAccountPullDownList", ssmParam);
    }

    /**
     * 
     * @param tMsg
     * @return
     */
    public S21SsmEZDResult getCltPsnNm(CLT_PTFOTMsg tMsg) {
        return getSsmEZDClient().queryObject("getCltPsnNm", tMsg);
    }

    // START 2018/06/08 E.Kameishi [QC#25735,ADD]
    /**
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRcptDataDLList(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getRcptDataDLList", ssmParam);
    }
    // END 2018/06/08 E.Kameishi [QC#25735,ADD]
}