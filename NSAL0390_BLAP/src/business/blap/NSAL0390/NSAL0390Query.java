/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0390;

import static business.blap.NSAL0390.constant.NSAL0390Constant.DS_CONTR_CR_CARD_PO_PK;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;

import business.blap.NSAL0390.common.NSAL0390CommonLogic;
import business.blap.NSAL0390.constant.NSAL0390Constant;
import business.db.CR_CARD_TRXTMsg;
import business.db.DS_CR_CARDTMsg;
import business.db.DS_CR_CARDTMsgArray;
import business.db.SVC_MEMO_RSNTMsg;
import business.db.SVC_MEMO_RSNTMsgArray;

import com.canon.cusa.s21.common.NSX.NSXC001001.constant.DS_CONTR_CTRL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CR_CARD_AUTH_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_TP;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/02/19   Fujitsu         T.Yoshida       Create          N/A
 * 2016/05/18   Hitachi         A.Kohinata      Update          QC#4212
 * 2016/08/29   Hitachi         A.Kohinata      Update          QC#11243
 * 2018/03/16   CITS            M.Naito         Update          QC#20496
 *</pre>
 */
public final class NSAL0390Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NSAL0390Query INSTANCE = new NSAL0390Query();

    /**
     * Private constructor
     */
    private NSAL0390Query() {
        super();
    }

    /**
     * Get instance
     * @return NSAL0390Query singleton instance
     */
    public static NSAL0390Query getInstance() {
        return INSTANCE;
    }

    /**
     * get First Line Data
     * @param cMsg NSAL0390CMsg
     * @param dsContrPkList List<BigDecimal>
     * @return First Line Data
     */
    public S21SsmEZDResult getFstData(NSAL0390CMsg cMsg, List<BigDecimal> dsContrPkList) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("dsContrPkList", dsContrPkList);
        params.put("machLvlNum1", NSAL0390Constant.MACH_LVL_NUM_1);
        // START 2018/03/16 M.Naito [QC#20496, ADD]
        params.put("contractMemo", SVC_MEMO_CATG.CONTRACT_MEMO);
        params.put("updateCreditCardInfo", SVC_MEMO_TP.UPDATE_CREDIT_CARD_INFO);
        params.put("dsContrCrCardPoPk", DS_CONTR_CR_CARD_PO_PK);
        // END 2018/03/16 M.Naito [QC#20496, ADD]

        return getSsmEZDClient().queryObjectList("getFstData", params);
    }

    /**
     * get Second Line Data
     * @param cMsg NSAL0390CMsg
     * @param dsContrPk BigDecimal
     * @param dsContrDtlPkList List<BigDecimal
     * @return Second Line Data
     */
    public S21SsmEZDResult getScdData(NSAL0390CMsg cMsg, BigDecimal dsContrPk, List<BigDecimal> dsContrDtlPkList) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("dsContrPk", dsContrPk);
        params.put("dsContrDtlPkList", dsContrDtlPkList);
        params.put("machLvlNum2", NSAL0390Constant.MACH_LVL_NUM_2);
        params.put("dsContrCatgFlt", DS_CONTR_CATG.FLEET);
        params.put("dsContrCatgAgg", DS_CONTR_CATG.AGGREGATE);
        params.put("dsContrDtlTpCdFlt", DS_CONTR_DTL_TP.FLEET);
        params.put("dsContrDtlTpCdAgg", DS_CONTR_DTL_TP.AGGREGATE);
        params.put("serNumFrom", cMsg.serNum_HF.getValue());
        params.put("serNumThru", cMsg.serNum_HT.getValue());
        params.put("svcMachMstrPkFrom", cMsg.svcMachMstrPk_HF.getValue());
        params.put("svcMachMstrPkThru", cMsg.svcMachMstrPk_HT.getValue());
        // START 2018/03/16 M.Naito [QC#20496, ADD]
        params.put("contractMemo", SVC_MEMO_CATG.CONTRACT_MEMO);
        params.put("updateCreditCardInfo", SVC_MEMO_TP.UPDATE_CREDIT_CARD_INFO);
        params.put("dsContrCrCardPoPk", DS_CONTR_CR_CARD_PO_PK);
        // END 2018/03/16 M.Naito [QC#20496, ADD]

        return getSsmEZDClient().queryObjectList("getScdData", params);
    }

    /**
     * get Third Line Data
     * @param cMsg NSAL0390CMsg
     * @param dsContrPk BigDecimal
     * @param dsContrDtlPk DS Contract Detail PK
     * @return Third Line Data
     */
    public S21SsmEZDResult getTrdData(NSAL0390CMsg cMsg, BigDecimal dsContrPk, BigDecimal dsContrDtlPk) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("dsContrPk", dsContrPk);
        params.put("dsContrDtlPk", dsContrDtlPk);
        params.put("machLvlNum3", NSAL0390Constant.MACH_LVL_NUM_3);
        params.put("trdTpCd", NSAL0390Constant.TRD_TP_CONTR_DTL);
        params.put("trdTpBm", NSAL0390Constant.TRD_TP_BLLG_MTR);
        params.put("trdNmBase", NSAL0390Constant.TRD_NM_BASE);
        // START 2018/03/16 M.Naito [QC#20496, ADD]
        params.put("contractMemo", SVC_MEMO_CATG.CONTRACT_MEMO);
        params.put("updateCreditCardInfo", SVC_MEMO_TP.UPDATE_CREDIT_CARD_INFO);
        params.put("dsContrCrCardPoPk", DS_CONTR_CR_CARD_PO_PK);
        // END 2018/03/16 M.Naito [QC#20496, ADD]

        return getSsmEZDClient().queryObjectList("getTrdData", params);
    }

    /**
     * check Exist First Data
     * @param cMsg NSAL0390CMsg
     * @param bsMsg NSAL0390_BSMsg
     * @return true: exist
     */
    public boolean isExistFstData(NSAL0390CMsg cMsg, NSAL0390_BSMsg bsMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("dsContrPk", bsMsg.dsContrPk_B0.getValue());
        params.put("machLvlNum1", NSAL0390Constant.MACH_LVL_NUM_1);

        S21SsmEZDResult result = getSsmEZDClient().queryObject("isExistFstData", params);

        return 0 < (Integer) result.getResultObject();
    }

    /**
     * check Exist Second Data
     * @param cMsg NSAL0390CMsg
     * @param bsMsg NSAL0390_BSMsg
     * @return true: exist
     */
    public boolean isExistScdData(NSAL0390CMsg cMsg, NSAL0390_BSMsg bsMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("dsContrPk", bsMsg.dsContrPk_B0.getValue());
        params.put("dsContrDtlPk", bsMsg.dsContrDtlPk_B0.getValue());
        params.put("machLvlNum2", NSAL0390Constant.MACH_LVL_NUM_2);

        S21SsmEZDResult result = getSsmEZDClient().queryObject("isExistScdData", params);

        return 0 < (Integer) result.getResultObject();
    }

    /**
     * check Exist Third Data of Contract Detail
     * @param cMsg NSAL0390CMsg
     * @param bsMsg NSAL0390_BSMsg
     * @return true: exist
     */
    public boolean isExistTrdDataOfContrDtl(NSAL0390CMsg cMsg, NSAL0390_BSMsg bsMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("dsContrPk", bsMsg.dsContrPk_B0.getValue());
        params.put("dsContrDtlPk", bsMsg.dsContrDtlPk_B0.getValue());
        params.put("machLvlNum3", NSAL0390Constant.MACH_LVL_NUM_3);

        S21SsmEZDResult result = getSsmEZDClient().queryObject("isExistTrdDataOfContrDtl", params);

        return 0 < (Integer) result.getResultObject();
    }

    /**
     * check Exist Third Data of Billing Meter
     * @param cMsg NSAL0390CMsg
     * @param bsMsg NSAL0390_BSMsg
     * @return true: exist
     */
    public boolean isExistTrdDataOfBllgMtr(NSAL0390CMsg cMsg, NSAL0390_BSMsg bsMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("dsContrPk", bsMsg.dsContrPk_B0.getValue());
        params.put("dsContrDtlPk", bsMsg.dsContrDtlPk_B0.getValue());
        params.put("dsContrBllgMtrPk", bsMsg.dsContrBllgMtrPk_B0.getValue());
        params.put("machLvlNum3", NSAL0390Constant.MACH_LVL_NUM_3);

        S21SsmEZDResult result = getSsmEZDClient().queryObject("isExistTrdDataOfBllgMtr", params);

        return 0 < (Integer) result.getResultObject();
    }

    /**
     * Service Memo Reason
     * @param cMsg NSAL0390CMsg
     * @return SVC_MEMO_RSNTMsgArray
     */
    public SVC_MEMO_RSNTMsgArray getServiceMemoReasonInfo(NSAL0390CMsg cMsg) {
        SVC_MEMO_RSNTMsg inMsg = new SVC_MEMO_RSNTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", cMsg.glblCmpyCd.getValue());
        inMsg.setConditionValue("svcMemoTpCd01", SVC_MEMO_TP.UPDATE_CREDIT_CARD_INFO);
        return (SVC_MEMO_RSNTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
    }

    /**
     * getContStsV
     * @param cMsg NSAL0390CMsg
     * @param dsContrPk BigDecimal
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getContStsV(NSAL0390CMsg cMsg, BigDecimal dsContrPk) {

        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("dsContrPk", dsContrPk);
        params.put("ctrlStsCd", new String[] {DS_CONTR_CTRL_STS.EXPIRED, DS_CONTR_CTRL_STS.CANCELLED, DS_CONTR_CTRL_STS.TERMINATED, DS_CONTR_CTRL_STS.TERMINATED_HOLD, DS_CONTR_CTRL_STS.EXPIRED_HOLD });
        return getSsmEZDClient().queryObject("getContStsV", params);
    }

    /**
     * getContrDtlStsV
     * @param cMsg NSAL0390CMsg
     * @param dsContrPk BigDecimal
     * @param dsContractDtlPk BigDecimal
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getContrDtlStsV(NSAL0390CMsg cMsg, BigDecimal dsContrPk, BigDecimal dsContractDtlPk) {

        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("dsContrPk", dsContrPk);
        params.put("ctrlStsCd", new String[] {DS_CONTR_CTRL_STS.EXPIRED, DS_CONTR_CTRL_STS.CANCELLED, DS_CONTR_CTRL_STS.TERMINATED, DS_CONTR_CTRL_STS.TERMINATED_HOLD, DS_CONTR_CTRL_STS.EXPIRED_HOLD });
        params.put("dsContractDtlPk", dsContractDtlPk);
        return getSsmEZDClient().queryObject("getContrDtlStsV", params);
    }

    // add start 2016/08/29 CSA Defect#11243
    /**
     * getDsCrCard
     * @param cMsg NSAL0390CMsg
     * @param bsMsg NSAL0390_BSMsg
     * @return DS_CR_CARDTMsg
     */
    public DS_CR_CARDTMsg getDsCrCard(NSAL0390CMsg cMsg, NSAL0390_BSMsg bsMsg) {
        DS_CR_CARDTMsg dsCrCardTMsg = new DS_CR_CARDTMsg();
        dsCrCardTMsg.setSQLID("002");
        dsCrCardTMsg.setConditionValue("glblCmpyCd01", cMsg.glblCmpyCd.getValue());
        dsCrCardTMsg.setConditionValue("crCardCustRefNum01", bsMsg.crCardCustRefNum_B1.getValue());
        dsCrCardTMsg.setConditionValue("sellToCustCd01", bsMsg.sellToCustCd_B0.getValue());
        DS_CR_CARDTMsgArray dsCrCardResult = (DS_CR_CARDTMsgArray) EZDTBLAccessor.findByCondition(dsCrCardTMsg);

        if (dsCrCardResult == null || dsCrCardResult.length() == 0) {
            return null;
        }
        return dsCrCardResult.no(0);
    }

    /**
     * getCrCardTrxPk
     * @param cMsg NSAL0390CMsg
     * @param bsMsg NSAL0390_BSMsg
     * @return List<BigDecimal>
     */
    public List<BigDecimal> getCrCardTrxPk(NSAL0390CMsg cMsg, NSAL0390_BSMsg bsMsg) {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("crCardTrxTp", NSAL0390CommonLogic.getCrCardTrxTp(bsMsg.dsContrMachLvlNum_B0.getValue(), bsMsg.xxDtlNm_B0.getValue()));
        params.put("firstTrxInfoNum", bsMsg.dsContrPk_B0.getValue());
        params.put("scdTrxInfoNum", bsMsg.dsContrDtlPk_B0.getValue());
        params.put("thirdTrxInfoNum", bsMsg.dsContrBllgMtrPk_B0.getValue());
        params.put("crCardAuthStsSaved", CR_CARD_AUTH_STS.SAVED);
        return (List<BigDecimal>) getSsmEZDClient().queryObjectList("getCrCardTrxPk", params).getResultObject();
    }

    /**
     * getCrCardTrx
     * @param glblCmpyCd String
     * @param crCardTrxPk BigDecimal
     * @return DS_CR_CARDTMsg
     */
    public CR_CARD_TRXTMsg getCrCardTrx(String glblCmpyCd, BigDecimal crCardTrxPk) {
        CR_CARD_TRXTMsg prmTMsg = new CR_CARD_TRXTMsg();
        setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        setValue(prmTMsg.crCardTrxPk, crCardTrxPk);
        return (CR_CARD_TRXTMsg) S21FastTBLAccessor.findByKey(prmTMsg);
    }

    /**
     * getDiffData
     * @param cMsg NSAL0390CMsg
     * @param bsMsg NSAL0390_BSMsg
     * @return Map<String, Object>
     */
    public Map<String, Object> getDiffData(NSAL0390CMsg cMsg, NSAL0390_BSMsg bsMsg) {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("crCardTrxTp", NSAL0390CommonLogic.getCrCardTrxTp(bsMsg.dsContrMachLvlNum_B0.getValue(), bsMsg.xxDtlNm_B0.getValue()));
        params.put("firstTrxInfoNum", bsMsg.dsContrPk_B0.getValue());
        params.put("scdTrxInfoNum", bsMsg.dsContrDtlPk_B0.getValue());
        params.put("thirdTrxInfoNum", bsMsg.dsContrBllgMtrPk_B0.getValue());
        params.put("crCardAuthStsSaved", CR_CARD_AUTH_STS.SAVED);
        params.put("dsContrMachLvlNum", bsMsg.dsContrMachLvlNum_B0.getValue());
        return (Map<String, Object>) getSsmEZDClient().queryObject("getDiffData", params).getResultObject();
    }
    // add end 2016/08/29 CSA Defect#11243
}
