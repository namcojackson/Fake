package business.blap.NFCL3020;

import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.blap.NFCL3020.constant.NFCL3020Constant;
import business.db.SELL_TO_CUSTTMsg;
import business.db.SELL_TO_CUSTTMsgArray;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Batch Entry
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/12/2015   CSAI            K.Lee           Create          Initial
 * 2018/01/17   Fujitsu         H.Ikeda         Update          QC#22759
 * 2018/03/08   Fujitsu         H.Ikeda         Update          QC#24469
 * 2018/04/06   Fujitsu         H.Ikeda         Update          QC#25338
 * 2018/05/09   Fujitsu         Y.Matsui        Update          QC#25856
 *</pre>
 */
public class NFCL3020Query extends S21SsmEZDQuerySupport implements NFCL3020Constant {

    /**
     * Singleton instance.
     */
    private static final NFCL3020Query myInstance = new NFCL3020Query();

    /**
     * Constructor
     */
    public NFCL3020Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NFDL0010Query
     */
    public static NFCL3020Query getInstance() {
        return myInstance;
    }

    /**
     * @param bizMsg NFCL3020CMsg
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getBatRcptList(NFCL3020CMsg bizMsg, Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryEZDMsg("getBatRcptList", ssmParam, bizMsg);
    }

    // START 2018/01/16 H.Ikeda [QC#22759, ADD]
    /**
     * searchCustmorName
     * @param bizMsg
     * @param ssmParam
     * @return
     */
    public S21SsmEZDResult searchCustmorName(NFCL3020CMsg bizMsg, Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("searchCustmorName", ssmParam);
    }
    
    /**
     * @param bizMsg NFCL3020CMsg
     * @return SELL_TO_CUSTTMsgArray
     */
    public static SELL_TO_CUSTTMsgArray findBillToAcctCust(NFCL3020CMsg bizMsg) {

        SELL_TO_CUSTTMsg inTMsg = new SELL_TO_CUSTTMsg();
        inTMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        inTMsg.setConditionValue("sellToCustCd01", bizMsg.payerCustCd_BH.getValue());
        inTMsg.setMaxCount(0);
        inTMsg.setSQLID("001");
        SELL_TO_CUSTTMsgArray outMsg = (SELL_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(inTMsg);
        return outMsg;
    }
    // END   2018/01/16 H.Ikeda [QC#22759, ADD]
    
    /**
     * @param globalMsg NFCL3020SMsg
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRcptList(NFCL3020SMsg globalMsg, Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryEZDMsgArray("getRcptList", ssmParam, globalMsg.B);
    }

    /**
     * @param bizMsg NFCL3020CMsg
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getArRcptSrcPullDownList(NFCL3020CMsg bizMsg, Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getArRcptSrcPullDownList", ssmParam);
    }

    /**
     * @param bizMsg NFCL3020CMsg
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getBankPullDownList(NFCL3020CMsg bizMsg, Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getBankPullDownList", ssmParam);
    }

    /**
     * @param bizMsg NFCL3020CMsg
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getBankBrPullDownList(NFCL3020CMsg bizMsg, Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getBankBrPullDownList", ssmParam);
    }

    /**
     * @param bizMsg NFCL3020CMsg
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getBankAccountPullDownList(NFCL3020CMsg bizMsg, Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getBankAccountPullDownList", ssmParam);
    }

    /**
     * @param bizMsg NFCL3020CMsg
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult geArRcptTrxTpPullDownList(NFCL3020CMsg bizMsg, Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getArRcptTrxTpPullDownList", ssmParam);
    }

    /**
     * @param bizMsg NFCL3020CMsg
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult geArBatRcptStsPullDownList(NFCL3020CMsg bizMsg, Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getArBatRcptStsPullDownList", ssmParam);
    }

    // START 2018/03/08 H.Ikeda [QC#24469, ADD]
    /**
     * @param bizMsg NFCL3020CMsg
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRcptAmtList(NFCL3020CMsg bizMsg, Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getRcptAmtList", ssmParam);
    }
    // END   2018/03/08 H.Ikeda [QC#24469, ADD]
    
    // START 2018/04/06 H.Ikeda [QC#25338, ADD]
    /**
     * @param bizMsg NFCL3020CMsg
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRcptTotalAmount(NFCL3020CMsg bizMsg, Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("getRcptTotalAmount", ssmParam);
    }
    // END   2018/04/06 H.Ikeda [QC#25338, ADD]

    // START 2018/05/09 [QC#25856, ADD]
    /**
     * Get Customer Code/Name Location
     * @param bizMsg NFCL3020CMsg
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCustomer(NFCL3020CMsg bizMsg, Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getCustomer", ssmParam);
    }
    // END   2018/05/09 [QC#25856, ADD]
}
