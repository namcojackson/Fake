package business.blap.NFCL0770;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_DS_WF_STS;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/11/02   Fujitsu         S.Takami        Update          QC#28289
 * 2022/05/06   CITS            D.Mamaril       Update          QC#59333
 * 2023/07/03   Hitachi         S.Fujita        Update          QC#60397
 *</pre>
 */
public final class NFCL0770Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NFCL0770Query MY_INSTANCE = new NFCL0770Query();

    /**
     * private constructor.
     */
    private NFCL0770Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NFCL0770Query
     */
    public static NFCL0770Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * <pre>
     * </pre>
     * @param map Query Condition
     * @param cMsg bizMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult findRcptData(Map map, NFCL0770CMsg cMsg) {

        return getSsmEZDClient().queryEZDMsg("findRcptData", map, cMsg);
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param map Map
     * @param sMsg NFCL0770SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult findInvData(Map map, NFCL0770SMsg sMsg) {

        return getSsmEZDClient().queryEZDMsgArray("findInvData", map, sMsg.A);
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param map Map
     * @param asMsg NFCL0770_ASMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult findInvDataByAdj(Map map, NFCL0770_ASMsg asMsg) {

        return getSsmEZDClient().queryEZDMsg("findInvDataByAdj", map, asMsg);
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param map Map
     * @param sMsg NFCL0770SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult findAdjAndDeduction(Map map, NFCL0770SMsg sMsg) {

        return getSsmEZDClient().queryEZDMsgArray("findAdjAndDeduction", map, sMsg.A);
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param map Map
     * @param cMsg NFCL0770CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult findDeductionData(Map map, NFCL0770CMsg cMsg) {

        return getSsmEZDClient().queryEZDMsg("findDeductionData", map, cMsg);
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param map Map
     * @param sMsg NFCL0770SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult findInvDownLoad(Map map, NFCL0770SMsg sMsg) {

        return getSsmEZDClient().queryEZDMsgArray("findInvDownLoad", map, sMsg.B);
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param map Map
     * @param bsMsg NFCL0770_BSMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult findInvDownLoadByAdj(Map map, NFCL0770_BSMsg bsMsg) {

        return getSsmEZDClient().queryEZDMsg("findInvDownLoadByAdj", map, bsMsg);
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param map Map
     * @param sMsg NFCL0770SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult findDownLoadAdjAndDeduction(Map map, NFCL0770SMsg sMsg) {

        return getSsmEZDClient().queryEZDMsgArray("findDownLoadAdjAndDeduction", map, sMsg.B);
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param map Map
     * @param sMsg NFCL0770SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult findArTrxBal(Map map, NFCL0770SMsg sMsg) {

        return getSsmEZDClient().queryEZDMsgArray("findArTrxBal", map, sMsg.B);
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param map Map
     * @param sMsg NFCL0770SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult findAddLineArTrxBal(Map map, NFCL0770SMsg sMsg) {

        return getSsmEZDClient().queryEZDMsgArray("findAddLineArTrxBal", map, sMsg.B);
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param map Map
     * @param sMsg NFCL0770SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult findAddLineArTrxBalByTrxNum(Map map, NFCL0770SMsg sMsg) {

        return getSsmEZDClient().queryEZDMsgArray("findAddLineArTrxBalByTrxNum", map, sMsg.D);
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param map Map
     * @param sMsg NFCL0770SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult findAddLineArTrxBalByCustRefNum(Map map, NFCL0770SMsg sMsg) {

        return getSsmEZDClient().queryEZDMsgArray("findAddLineArTrxBalByCustRefNum", map, sMsg.D);
    }

    /**
     * Get Account Name
     * @param bizMsg NFCL3030CMsg
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDsAcctNm(NFCL0770CMsg bizMsg, Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("getDsAcctNm", ssmParam);
    }

    // START 2023/07/03 S.Fujita [QC#60397,ADD]
    /**
     * getHrTtlNm
     * @param bizMsg NFCL0770CMsg
     * @param ssmParam SSM Parameters
     * @return String
     */
    public String getHrTtlNm(NFCL0770CMsg bizMsg, Map<String, Object> ssmParam) {
        return (String) getSsmEZDClient().queryObject("getHrTtlNm",ssmParam).getResultObject();
    }

    /**
     * getHrTtlApvlLimitPk
     * @param bizMsg NFCL0770CMsg
     * @param ssmParam SSM Parameters
     * @return BigDecimal
     */
    public BigDecimal getHrTtlApvlLimitPk(NFCL0770CMsg bizMsg, Map<String, Object> ssmParam) {
        return (BigDecimal) getSsmEZDClient().queryObject("getHrTtlApvlLimitPk",ssmParam).getResultObject();
    }

    // END 2023/07/03 S.Fujita [QC#60397,ADD]
    /**
     * <pre>
     * </pre>
     * @param bizMsg NFCL0770CMsg
     * @param sMsg NFCL0770SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult findDownLoadInfoData(NFCL0770CMsg bizMsg, NFCL0770SMsg sMsg) {

        Map<String, String> inFindInvDataMap = new HashMap<String, String>();
        inFindInvDataMap.put("glblCmpyCd", bizMsg.glblCmpyCd_H1.getValue());
        inFindInvDataMap.put("rcptNum", bizMsg.rcptNum.getValue());
        inFindInvDataMap.put("arScrCancFlgY", ZYPConstant.FLG_ON_Y);
        inFindInvDataMap.put("arScrCancFlgN", ZYPConstant.FLG_OFF_N);
        inFindInvDataMap.put("arTrxTpCdAcc", AR_TRX_TP.ON_ACCOUNT);
        return getSsmEZDClient().queryEZDMsgArray("findDownLoadInfoData", inFindInvDataMap, sMsg.C);
    }

    /**
     * Is AR_CASH_APP
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult isArCachApp(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("isArCachApp", ssmParam);
    }

    /**
     * getApplyingRefundCount
     * @param glblCmpyCd String
     * @param arTrxNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getApplyingRefundCount(String glblCmpyCd, String arTrxNum) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("arTrxNum", arTrxNum);
        param.put("crm", AR_TRX_TP.CREDIT_MEMO);
        param.put("acc", AR_TRX_TP.ON_ACCOUNT);
        // START 2022/05/06 D.Mamaril [QC#59333, ADD]
        param.put("rejected", AR_DS_WF_STS.REJECTED);
        // END 2022/05/06 D.Mamaril [QC#59333, ADD]
        return getSsmEZDClient().queryObject("getApplyingRefundCount", param);
    }
}
