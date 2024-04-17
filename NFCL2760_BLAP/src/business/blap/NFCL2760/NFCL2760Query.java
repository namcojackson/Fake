package business.blap.NFCL2760;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_DS_WF_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_TRX_TP;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/13   Hitachi         K.Kojima        Update          QC#6465
 * 2018/02/13   Fujitsu         H.Ikeda         Update          QC#20435
 * 2018/06/06   Fujitsu         Y.Matsui        Update          QC#25956
 * 2018/10/09   Fujitsu         T.Ogura         Update          QC#28166
 * 2022/01/06   CITS            G.Delgado       Update          QC#59329
 * 2022/02/22   CITS            D.Mamaril       Update          QC#59733
 * 2022/04/22   CITS            D.Mamaril       Update          QC#59333
 * 2022/05/12   CITS            D.Mamaril       Update          QC#59333
 * 2023/07/03   Hitachi         S.Fujita        Update          QC#60397
 * 2023/08/02   Hitachi         S.Fujita        Update          QC#60397
 *</pre>
 */
public class NFCL2760Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NFCL2760Query MY_INSTANCE = new NFCL2760Query();

    /**
     * private constructor.
     */
    private NFCL2760Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NFCL2760Query
     */
    public static NFCL2760Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param cMsg NFCL2760CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult findRcptData(Map map, NFCL2760CMsg cMsg) {

        return getSsmEZDClient().queryEZDMsg("findRcptData", map, cMsg);
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param map Map
     * @param sMsg NFCL2760SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult findInvData(Map map, NFCL2760SMsg sMsg) {

        return getSsmEZDClient().queryEZDMsgArray("findInvData", map, sMsg.A);
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param map Map
     * @param asMsg NFCL2760_ASMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult findInvDataByAdj(Map map, NFCL2760_ASMsg asMsg) {

        return getSsmEZDClient().queryEZDMsg("findInvDataByAdj", map, asMsg);
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param map Map
     * @param sMsg NFCL2760SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult findAdjAndDeduction(Map map, NFCL2760SMsg sMsg) {

        return getSsmEZDClient().queryEZDMsgArray("findAdjAndDeduction", map, sMsg.A);
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param map Map
     * @param cMsg NFCL2760CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult findDeductionData(Map map, NFCL2760CMsg cMsg) {

        return getSsmEZDClient().queryEZDMsg("findDeductionData", map, cMsg);
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param map Map
     * @param sMsg NFCL2760SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult findInvDownLoad(Map map, NFCL2760SMsg sMsg) {

        return getSsmEZDClient().queryEZDMsgArray("findInvDownLoad", map, sMsg.B);
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param map Map
     * @param bsMsg NFCL2760_BSMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult findInvDownLoadByAdj(Map map, NFCL2760_BSMsg bsMsg) {

        return getSsmEZDClient().queryEZDMsg("findInvDownLoadByAdj", map, bsMsg);
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param map Map
     * @param sMsg NFCL2760SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult findDownLoadAdjAndDeduction(Map map, NFCL2760SMsg sMsg) {

        return getSsmEZDClient().queryEZDMsgArray("findDownLoadAdjAndDeduction", map, sMsg.B);
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param map Map
     * @param sMsg NFCL2760SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult findArTrxBal(Map map, NFCL2760SMsg sMsg) {

        return getSsmEZDClient().queryEZDMsgArray("findArTrxBal", map, sMsg.B);
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param map Map
     * @param sMsg NFCL2760SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult findAddLineArTrxBal(Map map, NFCL2760SMsg sMsg) {

        return getSsmEZDClient().queryEZDMsgArray("findAddLineArTrxBal", map, sMsg.B);
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param map Map
     * @param sMsg NFCL2760SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult findAddLineArTrxBalByTrxNum(Map map, NFCL2760SMsg sMsg) {

        return getSsmEZDClient().queryEZDMsgArray("findAddLineArTrxBalByTrxNum", map, sMsg.D);
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param map Map
     * @param sMsg NFCL2760SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult findAddLineArTrxBalByCustRefNum(Map map, NFCL2760SMsg sMsg) {

        return getSsmEZDClient().queryEZDMsgArray("findAddLineArTrxBalByCustRefNum", map, sMsg.D);
    }

    /**
     * Get Account Name
     * @param bizMsg NFCL3030CMsg
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDsAcctNm(NFCL2760CMsg bizMsg, Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("getDsAcctNm", ssmParam);
    }
    // START 2023/07/03 S.Fujita [QC#60397,ADD]

    /**
     * getHrTtlNm
     * @param bizMsg NFCL2760CMsg
     * @param ssmParam SSM Parameters
     * @return String
     */
    public String getHrTtlNm(NFCL2760CMsg bizMsg, Map<String, Object> ssmParam) {
        return (String) getSsmEZDClient().queryObject("getHrTtlNm",ssmParam).getResultObject();
    }

    /**
     * getHrTtlApvlLimitPk
     * @param bizMsg NFCL2760CMsg
     * @param ssmParam SSM Parameters
     * @return BigDecimal
     */
    public BigDecimal getHrTtlApvlLimitPk(NFCL2760CMsg bizMsg, Map<String, Object> ssmParam) {
        return (BigDecimal) getSsmEZDClient().queryObject("getHrTtlApvlLimitPk",ssmParam).getResultObject();
    }

    // END 2023/07/03 S.Fujita [QC#60397,ADD]
    // START 2016/09/06 J.Kim [QC#13793,ADD]
    /**
     * <pre>
     * </pre>
     * @param bizMsg NFCL2760CMsg
     * @param sMsg NFCL2760SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult findDownLoadInfoData(NFCL2760CMsg bizMsg, NFCL2760SMsg sMsg) {

        Map<String, String> inFindInvDataMap = new HashMap<String, String>();
        inFindInvDataMap.put("glblCmpyCd", bizMsg.glblCmpyCd_H1.getValue());
        inFindInvDataMap.put("rcptNum", bizMsg.rcptNum.getValue());
        inFindInvDataMap.put("arScrCancFlgY", ZYPConstant.FLG_ON_Y);
        inFindInvDataMap.put("arScrCancFlgN", ZYPConstant.FLG_OFF_N);
        // START 2018/06/06 Y.Matsui [QC#25956,ADD]
        inFindInvDataMap.put("arTrxTpCdAcc", AR_TRX_TP.ON_ACCOUNT);
        // END   2018/06/06 Y.Matsui [QC#25956,ADD]
        return getSsmEZDClient().queryEZDMsgArray("findDownLoadInfoData", inFindInvDataMap, sMsg.C);
    }
    // END 2016/09/06 J.Kim [QC#13793,ADD]

    // START 2018/02/13 [QC#20435,ADD]
    /**
     * Is AR_CASH_APP
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult isArCachApp(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("isArCachApp", ssmParam);
    }
    // END   2018/02/13 [QC#20435,ADD]

    // START 2018/10/09 T.Ogura [QC#28166,ADD]
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
        // START 2022/05/12 D.Mamaril [QC#59333, ADD]
        param.put("rejected", AR_DS_WF_STS.REJECTED);
        // END 2022/05/12 D.Mamaril [QC#59333, ADD]
        return getSsmEZDClient().queryObject("getApplyingRefundCount", param);
    }
    // END   2018/10/09 T.Ogura [QC#28166,ADD]

    // START 2022/04/22 D.Mamaril [QC#59333,ADD]
    /**
     * getRefundDetails
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRefundDetails(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("getRefundDetails", ssmParam);
    }
    // END 2022/04/22 D.Mamaril [QC#59333,ADD]
}
