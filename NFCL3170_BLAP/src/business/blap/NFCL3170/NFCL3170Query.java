package business.blap.NFCL3170;

import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

import business.blap.NFCL3170.NFCL3170SMsg;
import business.blap.NFCL3170.constant.NFCL3170Constant;

/**
 *<pre>
 * bank Account Maintenance
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/08/2015   Fujitsu         T.Tanaka        Create          Initial
 * 04/27/2016   Fujitsu         S.Fujita        Update          QC#5743
 * 02/09/2023   Hitachi         R.Takau         Update          QC#55645
 *</pre>
 */
public class NFCL3170Query extends S21SsmEZDQuerySupport implements NFCL3170Constant {
    
    /**
     * Singleton instance.
     */
    private static final NFCL3170Query myInstance = new NFCL3170Query();

    /**
     * Constructor
     */
    public NFCL3170Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NFDL0010Query
     */
    public static NFCL3170Query getInstance() {
        return myInstance;
    }

    /**
     * 
     * @param bizMsg
     * @param ssmParam
     * @return
     */
    public S21SsmEZDResult getDsBankPullDownList(NFCL3170CMsg bizMsg, Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getDsBankPullDownList", ssmParam);
    }

    /**
     * 
     * @param bizMsg
     * @param ssmParam
     * @return
     */
    public S21SsmEZDResult getDsBankBrPullDownList(NFCL3170CMsg bizMsg, Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getDsBankBrPullDownList", ssmParam);
    }

    /**
     * 
     * @param bizMsg
     * @param ssmParam
     * @return
     */
    public S21SsmEZDResult getArRcptSrcPullDownList(NFCL3170CMsg bizMsg, Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getArRcptSrcPullDownList", ssmParam);
    }

    /**
     * 
     * @param bizMsg
     * @param ssmParam
     * @return
     */
    public S21SsmEZDResult getArLockBoxPullDownList(NFCL3170CMsg bizMsg, Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getArLockBoxPullDownList", ssmParam);
    }

    /**
     * 
     * @param bizMsg
     * @param ssmParam
     * @return
     */
    public S21SsmEZDResult getCcyPullDownList(NFCL3170CMsg bizMsg, Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getCcyPullDownList", ssmParam);
    }

    /**
     * 
     * @param bizMsg
     * @param ssmParam
     * @return
     */
    public S21SsmEZDResult getCntyPullDownList(NFCL3170CMsg bizMsg, Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getCntyPullDownList", ssmParam);
    }

    /**
     * 
     * @param bizMsg
     * @param ssmParam
     * @return
     */
    public S21SsmEZDResult getCtryPullDownList(NFCL3170CMsg bizMsg, Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getCtryPullDownList", ssmParam);
    }

    /**
     * 
     * @param bizMsg
     * @param ssmParam
     * @return
     */
    public S21SsmEZDResult getStPullDownList(NFCL3170CMsg bizMsg, Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getStPullDownList", ssmParam);
    }

    /**
     * 
     * @param bizMsg
     * @param ssmParam
     * @return
     */
    public S21SsmEZDResult checkDsBankAcct(NFCL3170CMsg bizMsg, Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("checkDsBankAcct", ssmParam);
    }

    // START 2016/04/27 S.Fujita [QC#5743,MOD]
//    /**
//     * 
//     * @param bizMsg
//     * @param ssmParam
//     * @return
//     */
//    public S21SsmEZDResult checkDsBankBr(NFCL3170CMsg bizMsg, Map<String, Object> ssmParam) {
//        return getSsmEZDClient().queryObject("checkDsBankBr", ssmParam);
//    }
    // END 2016/04/27 S.Fujita [QC#5743,MOD]

    /**
     * 
     * @param bizMsg
     * @param ssmParam
     * @return
     */
    public S21SsmEZDResult getDsAcctCustPK(NFCL3170CMsg bizMsg, Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("getDsAcctCustPK", ssmParam);
    }

    /**
     * 
     * @param bizMsg
     * @param ssmParam
     * @return
     */
    public S21SsmEZDResult getCntyPK(NFCL3170CMsg bizMsg, Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("getCntyPK", ssmParam);
    }

    /**
     * 
     * @param bizMsg
     * @param ssmParam
     * @return
     */
    public S21SsmEZDResult searchDsCustBankAcctReln(NFCL3170CMsg bizMsg, Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryEZDMsgArray("searchDsCustBankAcctReln", ssmParam, bizMsg.A);
    }

    /**
     * 
     * @param bizMsg
     * @param ssmParam
     * @return
     */
    public S21SsmEZDResult getDsBankAcct(NFCL3170CMsg bizMsg, Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryEZDMsgArray("getDsBankAcct", ssmParam, bizMsg.A);
    }

    /**
     * 
     * @param bizMsg
     * @param ssmParam
     * @return
     */
    public S21SsmEZDResult searchDsBankInfo(NFCL3170CMsg bizMsg, Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryEZDMsg("searchDsBankInfo", ssmParam, bizMsg);
    }

    //START 2023/02/09 R.Takau [QC#55645,ADD]
    /**
     * 
     * @param bizMsg
     * @param ssmParam
     * @return
     */
    public  Map<String, Object> getAccountInfo(NFCL3170CMsg bizMsg, Map<String, Object> ssmParam) {
        return (Map<String, Object>) getSsmEZDClient().queryObject("getAccountInfo", ssmParam).getResultObject();
    }
    //END 2023/02/09 R.Takau [QC#55645,ADD]
}
