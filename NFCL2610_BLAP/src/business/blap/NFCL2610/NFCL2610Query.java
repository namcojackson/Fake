/**
 * <Pre>Copyright(c)2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NFCL2610;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.AR_CR_RF_APVL_LIMITTMsg;
import business.db.AR_RF_RQSTTMsg;
import business.db.AR_RF_TPTMsg;
import business.db.BILL_TO_CUSTTMsg;
import business.db.BILL_TO_CUSTTMsgArray;
import business.db.GLBL_CMPYTMsg;
import business.db.SELL_TO_CUSTTMsg;
import business.db.SELL_TO_CUSTTMsgArray;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_DS_WF_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_RF_TP;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * AR Refund by Check Entry
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/10   Hitachi         J.Kim           Create          N/A
 * 2016/04/19   Hitachi         T.Tsuchida      Update          QC#4758
 * 2017/01/13   Fujitsu         T.Murai         Update          QC#16941
 * 2018/03/02   Hitachi         E.Kameishi      Update          QC#22765
 * 2021/09/09   CITS            G.Delgado       Update          QC#58793
 * 2022/01/26   CITS            D.Mamaril       Update          QC#59618
 * 2022/06/03   CITS            D.Mamaril       Update          QC#59333-1
 * 2023/06/13   Hitachi         S.Fujita        Update          QC#61486
 *</pre>
 */
public final class NFCL2610Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance
     */
    private static final NFCL2610Query INSTANCE = new NFCL2610Query();

    /**
     * Private constructor
     */
    private NFCL2610Query() {
        super();
    }

    /**
     * Singleton instance getter
     * @return NFCL2610Query
     */
    public static NFCL2610Query getInstance() {
        return INSTANCE;
    }

    /**
     * findArRfRqstInfo
     * @param bizMsg NFCL2610CMsg
     * @return AR_RF_RQSTTMsg
     */
    public static AR_RF_RQSTTMsg findArRfRqstInfo(NFCL2610CMsg bizMsg) {
        AR_RF_RQSTTMsg inTMsg = new AR_RF_RQSTTMsg();
        ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inTMsg.arRfRqstPk, bizMsg.arRfRqstPk);
        AR_RF_RQSTTMsg outTMsg = (AR_RF_RQSTTMsg) EZDTBLAccessor.findByKey(inTMsg);
        return outTMsg;
    }

    /**
     * findGlblCmpy
     * @param bizMsg NFCL2610CMsg
     * @return GLBL_CMPYTMsg
     */
    public static GLBL_CMPYTMsg findGlblCmpy(NFCL2610CMsg bizMsg) {

        GLBL_CMPYTMsg inTMsg = new GLBL_CMPYTMsg();
        ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        GLBL_CMPYTMsg outTMsg = (GLBL_CMPYTMsg) EZDTBLAccessor.findByKey(inTMsg);
        return outTMsg;
    }

    /**
     * findAcctDlyActlExchRatest
     * @param bizMsg NFCL2610CMsg
     * @param ccyCd String
     * @param slsDt String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult findAcctDlyActlExchRatest(NFCL2610CMsg bizMsg, String ccyCd, String slsDt) {

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", bizMsg.glblCmpyCd);
        paramMap.put("ccyCd", ccyCd);
        paramMap.put("slsDt", ZYPDateUtil.getSalesDate());
        return getSsmEZDClient().queryObject("getExchRate", paramMap);
    }

    /**
     * @param bizMsg NFCL2610CMsg
     * @return SELL_TO_CUSTTMsgArray
     */
    public static SELL_TO_CUSTTMsgArray findBillToAcctCust(NFCL2610CMsg bizMsg) {

        SELL_TO_CUSTTMsg inTMsg = new SELL_TO_CUSTTMsg();
        inTMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        inTMsg.setConditionValue("sellToCustCd01", bizMsg.billToCustAcctCd.getValue());
        inTMsg.setMaxCount(0);
        inTMsg.setSQLID("001");
        SELL_TO_CUSTTMsgArray outMsg = (SELL_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(inTMsg);
        return outMsg;
    }

    /**
     * @param bizMsg NFCL2610CMsg
     * @return AR_RF_TPTMsg
     */
    public static AR_RF_TPTMsg findArRfTp(NFCL2610CMsg bizMsg) {
        AR_RF_TPTMsg inTMsg = new AR_RF_TPTMsg();
        ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inTMsg.arRfTpCd, bizMsg.arRfTpCd_H);
        AR_RF_TPTMsg outTMsg = (AR_RF_TPTMsg) EZDTBLAccessor.findByKey(inTMsg);
        return outTMsg;
    }

    /**
     * getRefundEligibleUser
     * @param bizMsg NFCL2610CMsg
     * @return AR_CR_RF_APVL_LIMITTMsg
     */
    public static AR_CR_RF_APVL_LIMITTMsg getRefundEligibleUser(NFCL2610CMsg bizMsg) {

        AR_CR_RF_APVL_LIMITTMsg inTMsg = new AR_CR_RF_APVL_LIMITTMsg();
        ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inTMsg.arCrApvlUsrId, bizMsg.getUserID());
        AR_CR_RF_APVL_LIMITTMsg outTMsg = (AR_CR_RF_APVL_LIMITTMsg) EZDTBLAccessor.findByKey(inTMsg);
        return outTMsg;
    }

    /**
     * @param bizMsg NFCL2610CMsg
     * @return BILL_TO_CUSTTMsgArray
     */
    public static BILL_TO_CUSTTMsgArray findBillToCustList(NFCL2610CMsg bizMsg) {

        BILL_TO_CUSTTMsg inTMsg = new BILL_TO_CUSTTMsg();
        inTMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        inTMsg.setConditionValue("billToCustCd01", bizMsg.billToCustLocCd.getValue());
        inTMsg.setMaxCount(0);
        inTMsg.setSQLID("003");
        BILL_TO_CUSTTMsgArray outMsg = (BILL_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(inTMsg);
        return outMsg;
    }

    /**
     * getArTrxBalInfo
     * @param sMsg NFCL2610SMsg
     * @param params Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getArTrxBalInfo(NFCL2610SMsg sMsg, Map<String, Object> params) {
        return getSsmEZDClient().queryEZDMsgArray("getTransactionSeachInfo", params, sMsg.A);
    }

    /**
     * getArTrxBalInfo
     * @param sMsg NFCL2610SMsg
     * @param params Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getArRfRqstlInfo(NFCL2610SMsg sMsg, Map<String, Object> params) {
        return getSsmEZDClient().queryEZDMsgArray("getArRfRqstDtlSeachInfo", params, sMsg.A);
    }

    /**
     * getSupplierInfo
     * @param cMsg NFCL2610CMsg
     * @param params Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSupplierInfo(NFCL2610CMsg cMsg, Map<String, Object> params) {
        return getSsmEZDClient().queryEZDMsgArray("getSupplierInfo", params, cMsg.B);
    }

    // START 2016/07/29 K.Kojima [QC#12657,ADD]
    /**
     * getPulldownArRfTpCd
     * @param glblCmpyCd String
     * @param arRfTpCdCreditCardRefund String
     * @param isCollectionReps boolean
     * @return S21SsmEZDResult
     */
    // START 2023/06/13 S.Fujita [QC#61486,MOD]
    // public S21SsmEZDResult getPulldownArRfTpCd(String glblCmpyCd, String arRfTpCdCreditCardRefund) {
    public S21SsmEZDResult getPulldownArRfTpCd(String glblCmpyCd, String arRfTpCdCreditCardRefund, boolean isCollectionReps) {
    // END 2023/06/13 S.Fujita [QC#61486,MOD]
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("arRfTpCdCreditCardRefund", arRfTpCdCreditCardRefund);
        // START 2023/06/13 S.Fujita [QC#61486,ADD]
        if (isCollectionReps) {
            ssmParam.put("arRfTpCdCustomerRefund", AR_RF_TP.CUSTOMER);
        }
        // END 2023/06/13 S.Fujita [QC#61486,ADD]
        return getSsmEZDClient().queryObjectList("getPulldownArRfTpCd", ssmParam);
    }
    // END 2016/07/29 K.Kojima [QC#12657,ADD]

    // START 2017/01/13 [QC#16941,ADD]
    /**
     * countAlreadyRqstData
     * @param arTrxNum String
     * @param glblCmpyCd String
     * @return int
     */
    // START 2021/09/09 G.Delgado [QC#58793, MOD]
    // public int countAlreadyRqstData(NFCL2610_ACMsg aCMsg, String glblCmpyCd) {
    public int countAlreadyRqstData(String arTrxNum, String glblCmpyCd) {
    // END 2021/09/09 G.Delgado [QC#58793, MOD]
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        // START 2021/09/09 G.Delgado [QC#58793, MOD]
        // ssmParam.put("arNumber", aCMsg.arTrxNum_A.getValue());
        ssmParam.put("arNumber", arTrxNum);
        // END 2021/09/09 G.Delgado [QC#58793, MOD]
        ssmParam.put("wfStsCd00", AR_DS_WF_STS.PENDING);

        return (Integer)getSsmEZDClient().queryObject("countAlreadyRqstData", ssmParam).getResultObject();
    }
    // END   2017/01/13 [QC#16941,ADD]

    /**
     * countRqstData
     * @param aCMsg NFCL2610_ACMsg
     * @param glblCmpyCd String
     * @return int
     */
    public String getRfSts(String glblCmpyCd, BigDecimal arRfRqstPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("arRfRqstPk", arRfRqstPk);

        return (String) getSsmEZDClient().queryObject("getRfSts", ssmParam).getResultObject();
    }
}
