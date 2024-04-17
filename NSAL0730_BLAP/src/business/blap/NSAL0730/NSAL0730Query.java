/**
 * <Pre>Copyright(c)2013 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL0730;

import static business.blap.NSAL0730.constant.NSAL0730Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.DS_CONTRTMsg;
import business.db.DS_CONTR_BLLG_MTRTMsg;
import business.db.DS_CONTR_DTLTMsg;
import business.db.DS_CONTR_DTL_TPTMsg;
import business.db.DS_CONTR_PRC_EFFTMsg;
import business.db.SVC_MEMO_RSNTMsg;
import business.db.SVC_MEMO_RSNTMsgArray;

import com.canon.cusa.s21.common.NSX.NSXC001001.constant.DS_CONTR_CTRL_STS;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CRAT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_TRX_RULE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/19   Hitachi         J.Kim           Create          N/A
 * 2016/02/19   Hitachi         T.Aoyagi        Update          QC3694
 * 2016/04/07   Hitachi         T.Tomita        Update          QC#4464
 * 2017/06/19   Hitachi         T.Mizuki        Update          QC#19256
 * 2017/10/04   Hitachi         K.Kojima        Update          QC#20523
 * 2019/01/10   Hitachi         K.Kitachi       Update          QC#26928
 * 2019/05/14   Hitachi         A.Kohinata      Update          QC#50331
 * 2020/06/25   CITS            T.Wada          Update          QC#55590
 *</pre>
 */
public final class NSAL0730Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance
     */
    private static final NSAL0730Query INSTANCE = new NSAL0730Query();

    /**
     * Private constructor
     */
    private NSAL0730Query() {
        super();
    }

    /**
     * Singleton instance getter
     * @return NSAL0730Query
     */
    public static NSAL0730Query getInstance() {
        return INSTANCE;
    }

    /**
     * Service Memo Reason
     * @param cMsg NSAL0730CMsg
     * @return SVC_MEMO_RSNTMsgArray
     */
    public SVC_MEMO_RSNTMsgArray getServiceMemoReasonInfo(NSAL0730CMsg cMsg) {
        SVC_MEMO_RSNTMsg inMsg = new SVC_MEMO_RSNTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", cMsg.glblCmpyCd.getValue());
        inMsg.setConditionValue("svcMemoTpCd01", SVC_MEMO_TP.UPDATE_PO_NUM);
        return (SVC_MEMO_RSNTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
    }

    /**
     * DS SContract Info
     * @param cMsg NSAL0730CMsg
     * @param dsContrPk dsContrPk
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDSContractInfo(NSAL0730CMsg cMsg, BigDecimal dsContrPk) {

        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("dsContrPk", dsContrPk);
        params.put("dsContrMachLvlNum", LVL_NUM_1);
        // START 2019/01/10 K.Kitachi [QC#26928, MOD]
//        return getSsmEZDClient().queryObject("getDSContractInfo", params);
        return getSsmEZDClient().queryObjectList("getDSContractInfo", params);
        // END 2019/01/10 K.Kitachi [QC#26928, MOD]
    }

    /**
     * DS Contract Detail Info
     * @param cMsg NSAL0730CMsg
     * @param dsContrPk String
     * @param dsContrDtlPkList List<BigDecimal>
     * @return S21SsmEZDResult
     */
    // START 2016/02/19 T.Aoyagi [QC3694, MOD]
//    public S21SsmEZDResult getDSContractDetailInfo(NSAL0730CMsg cMsg, String dsContrPk) {
    public S21SsmEZDResult getDSContractDetailInfo(NSAL0730CMsg cMsg, BigDecimal dsContrPk, List<BigDecimal> dsContrDtlPkList) {
    // END 2016/02/19 T.Aoyagi [QC3694, MOD]

        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("dsContrPk", dsContrPk);
        // START 2016/02/19 T.Aoyagi [QC3694, ADD]
        if (!dsContrDtlPkList.isEmpty()) {
            params.put("existContrDtlPk", ZYPConstant.FLG_ON_Y);
        }
        params.put("dsContrDtlPkList", dsContrDtlPkList);
        // END 2016/02/19 T.Aoyagi [QC3694, ADD]
        params.put("dsContrMachLvlNum", LVL_NUM_2);
        params.put("dsContrDtlTpCdIsFlt", DS_CONTR_DTL_TP.FLEET);
        params.put("dsContrDtlTpCdIsAgg", DS_CONTR_DTL_TP.AGGREGATE);
        params.put("dsContrDtlTpCdIsAcc", DS_CONTR_DTL_TP.ACCESSORIES);
        params.put("flgOffN", ZYPConstant.FLG_OFF_N);
        // START 2017/06/19 T.Mizuki [QC#19256,ADD]
        params.put("dsContrDtlStsOrdr", DS_CONTR_DTL_STS.ORDER);
        // END 2017/06/19 T.Mizuki [QC#19256,ADD]
        // add start 2019/05/14 QC#50331
        params.put("dsContrCratTp", DS_CONTR_CRAT_TP.OTHER);
        // add end 2019/05/14 QC#50331
        return getSsmEZDClient().queryObjectList("getDSContractDetailInfo", params);
    }

    /**
     * DS Contract Detail Base/Overage Info
     * @param cMsg NSAL0730CMsg
     * @param dsContrPk String
     * @param dsContrDtlPkList List<BigDecimal>
     * @return S21SsmEZDResult
     */
    // START 2016/02/19 T.Aoyagi [QC3694, MOD]
//    public S21SsmEZDResult getDSContractDetailBaseOverageInfo(NSAL0730CMsg cMsg, String dsContrPk) {
    public S21SsmEZDResult getDSContractDetailBaseOverageInfo(NSAL0730CMsg cMsg, BigDecimal dsContrPk, List<BigDecimal> dsContrDtlPkList) {
    // END 2016/02/19 T.Aoyagi [QC3694, MOD]

        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("dsContrPk", dsContrPk);
        // START 2016/02/19 T.Aoyagi [QC3694, ADD]
        if (!dsContrDtlPkList.isEmpty()) {
            params.put("existContrDtlPk", ZYPConstant.FLG_ON_Y);
        }
        params.put("dsContrDtlPkList", dsContrDtlPkList);
        params.put("dsContrDtlTpCdIsFlt", DS_CONTR_DTL_TP.FLEET);
        params.put("dsContrDtlTpCdIsAgg", DS_CONTR_DTL_TP.AGGREGATE);
        params.put("dsContrDtlTpCdIsAcc", DS_CONTR_DTL_TP.ACCESSORIES);
        // END 2016/02/19 T.Aoyagi [QC3694, ADD]
        params.put("chreFlg", ZYPConstant.FLG_ON_Y);
        params.put("dsContrMachLvlNum", LVL_NUM_3);
        // START 2017/06/19 T.Mizuki [QC#19256,ADD]
        params.put("dsContrDtlStsOrdr", DS_CONTR_DTL_STS.ORDER);
        // END 2017/06/19 T.Mizuki [QC#19256,ADD]
        // add start 2019/05/14 QC#50331
        params.put("dsContrCratTp", DS_CONTR_CRAT_TP.OTHER);
        // add end 2019/05/14 QC#50331
        return getSsmEZDClient().queryObjectList("getDSContractDetailBaseOverageInfo", params);
    }

    /**
     * Service Memo
     * @param cMsg NSAL0730SMsg
     * @param dsContrPk BigDecimal
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSvcMemo(NSAL0730SMsg cMsg, BigDecimal dsContrPk) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("dsContractPk", dsContrPk);
        params.put("contractMemo", SVC_MEMO_CATG.CONTRACT_MEMO);
        params.put("updateTp", SVC_MEMO_TP.UPDATE_PO_NUM);
        return getSsmEZDClient().queryObject("getSvcMemo", params);
    }

    /**
     * DS_CONTR_STS_V
     * @param cMsg NSAL0730SMsg
     * @param dsContrPk BigDecimal
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getContStsV(NSAL0730CMsg cMsg, BigDecimal dsContrPk) {

        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("dsContrPk", dsContrPk);
        params.put("ctrlStsCd", new String[] {DS_CONTR_CTRL_STS.EXPIRED, DS_CONTR_CTRL_STS.CANCELLED, DS_CONTR_CTRL_STS.TERMINATED, DS_CONTR_CTRL_STS.TERMINATED_HOLD, DS_CONTR_CTRL_STS.EXPIRED_HOLD });
        return getSsmEZDClient().queryObject("getContStsV", params);
    }

    /**
     * DS_CONTR_DTL_STS_V
     * @param cMsg NSAL0730CMsg
     * @param dsContrPk BigDecimal
     * @param dsContractDtlPk BigDecimal
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getContrDtlStsV(NSAL0730CMsg cMsg, BigDecimal dsContrPk, BigDecimal dsContractDtlPk) {

        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("dsContrPk", dsContrPk);
        params.put("ctrlStsCd", new String[] {DS_CONTR_CTRL_STS.EXPIRED, DS_CONTR_CTRL_STS.CANCELLED, DS_CONTR_CTRL_STS.TERMINATED, DS_CONTR_CTRL_STS.TERMINATED_HOLD, DS_CONTR_CTRL_STS.EXPIRED_HOLD });
        params.put("dsContractDtlPk", dsContractDtlPk);
        return getSsmEZDClient().queryObject("getContrDtlStsV", params);
    }

    /**
     * DS_CONTR_BLLG_MTR_STS_V
     * @param cMsg NSAL0730CMsg
     * @param dsContractDtlPk BigDecimal
     * @param dsContrCtrBllgMtrPk BigDecimal
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getContrBllgMtrStsV(NSAL0730CMsg cMsg, BigDecimal dsContractDtlPk, BigDecimal dsContrCtrBllgMtrPk) {

        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("dsContractDtlPk", dsContractDtlPk);
        params.put("ctrlStsCd", new String[] {DS_CONTR_CTRL_STS.EXPIRED, DS_CONTR_CTRL_STS.CANCELLED, DS_CONTR_CTRL_STS.TERMINATED, DS_CONTR_CTRL_STS.TERMINATED_HOLD, DS_CONTR_CTRL_STS.EXPIRED_HOLD });
        params.put("dsContrCtrBllgMtrPk", dsContrCtrBllgMtrPk);
        return getSsmEZDClient().queryObject("getContrBllgMtrStsV", params);
    }

    /**
     * getDsContracReportGroupInfoList
     * @param params HashMap<String, Object>
     * @param globalMsg NSAL0730CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDsContracReportGroupInfoList(HashMap<String, Object> params, NSAL0730SMsg globalMsg) {
        return getSsmEZDClient().queryEZDMsgArray("getDsContracReportGroupInfoList", params, globalMsg.A);
    }

    // add start 2016/04/07 CSA Defect#4464
    /**
     * Get DS_CONTR
     * @param glblCmpyCd String
     * @param dsContrPk BigDecimal
     * @return DS_CONTRTMsg
     */
    public DS_CONTRTMsg getDsContr(String glblCmpyCd, BigDecimal dsContrPk) {
        DS_CONTRTMsg inMsg = new DS_CONTRTMsg();
        setValue(inMsg.glblCmpyCd, glblCmpyCd);
        setValue(inMsg.dsContrPk, dsContrPk);
        return (DS_CONTRTMsg) EZDTBLAccessor.findByKey(inMsg);
    }

    /**
     * Get DS_CONTR_DTL
     * @param glblCmpyCd String
     * @param dsContrPk BigDecimal
     * @return DS_CONTR_DTLTMsg
     */
    public DS_CONTR_DTLTMsg getDsContrDtl(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        DS_CONTR_DTLTMsg inMsg = new DS_CONTR_DTLTMsg();
        setValue(inMsg.glblCmpyCd, glblCmpyCd);
        setValue(inMsg.dsContrDtlPk, dsContrDtlPk);
        return (DS_CONTR_DTLTMsg) EZDTBLAccessor.findByKey(inMsg);
    }

    /**
     * Get DS_CONTR_BLLG_MTR
     * @param glblCmpyCd String
     * @param dsContrBllgMtrPk BigDecimal
     * @return DS_CONTR_BLLG_MTRTMsg
     */
    public DS_CONTR_BLLG_MTRTMsg getDsContrBllgMtr(String glblCmpyCd, BigDecimal dsContrBllgMtrPk) {
        DS_CONTR_BLLG_MTRTMsg inMsg = new DS_CONTR_BLLG_MTRTMsg();
        setValue(inMsg.glblCmpyCd, glblCmpyCd);
        setValue(inMsg.dsContrBllgMtrPk, dsContrBllgMtrPk);
        return (DS_CONTR_BLLG_MTRTMsg) EZDTBLAccessor.findByKey(inMsg);
    }

    /**
     * Get DS_CONTR_DTL_TP
     * @param glblCmpyCd String
     * @param dsContrDtlTpCd String
     * @return DS_CONTR_DTL_TPTMsg
     */
    public DS_CONTR_DTL_TPTMsg getDsContrDtlTp(String glblCmpyCd, String dsContrDtlTpCd) {
        DS_CONTR_DTL_TPTMsg inMsg = new DS_CONTR_DTL_TPTMsg();
        setValue(inMsg.glblCmpyCd, glblCmpyCd);
        setValue(inMsg.dsContrDtlTpCd, dsContrDtlTpCd);
        return (DS_CONTR_DTL_TPTMsg) ZYPCodeDataUtil.findByKey(inMsg);
    }
    // add end 2016/04/07 CSA Defect#4464

    // START 2017/10/04 K.Kojima [QC#20523,ADD]
    /**
     * getRenewalHoldforPoReleaseDsContrPrcEffPk
     * @param glblCmpyCd String
     * @param dsContrPk BigDecimal
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRenewalHoldforPoReleaseDsContrPrcEffPk(String glblCmpyCd, BigDecimal dsContrPk) {

        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("dsContrPk", dsContrPk);
        params.put("renewalHoldForPO", DS_CONTR_DTL_STS.RENEWAL_HOLD_FOR_PO);
        params.put("dsContrMachLvlNum1", LVL_NUM_1);
        params.put("dsContrMachLvlNum2", LVL_NUM_2);
        params.put("dsContrMachLvlNum3", LVL_NUM_3);
        params.put("flgY", ZYPConstant.FLG_ON_Y);
        return getSsmEZDClient().queryObjectList("getRenewalHoldforPoReleaseDsContrPrcEffPk", params);
    }

    /**
     * getRenewalHoldforPoReleaseDsContrDtlPk
     * @param glblCmpyCd String
     * @param dsContrPk BigDecimal
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRenewalHoldforPoReleaseDsContrDtlPk(String glblCmpyCd, BigDecimal dsContrDtlPk) {

        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("dsContrDtlPk", dsContrDtlPk);
        params.put("renewalHoldForPO", DS_CONTR_DTL_STS.RENEWAL_HOLD_FOR_PO);
        return getSsmEZDClient().queryObjectList("getRenewalHoldforPoReleaseDsContrDtlPk", params);
    }

    /**
     * Get DS_CONTR_PRC_EFF
     * @param glblCmpyCd String
     * @param dsContrDtlTpCd String
     * @return DS_CONTR_PRC_EFFTMsg
     */
    public DS_CONTR_PRC_EFFTMsg getDsContrPrcEff(String glblCmpyCd, BigDecimal dsContrPrcEffPk) {
        DS_CONTR_PRC_EFFTMsg inMsg = new DS_CONTR_PRC_EFFTMsg();
        setValue(inMsg.glblCmpyCd, glblCmpyCd);
        setValue(inMsg.dsContrPrcEffPk, dsContrPrcEffPk);
        return (DS_CONTR_PRC_EFFTMsg) EZDTBLAccessor.findByKey(inMsg);
    }
    // END 2017/10/04 K.Kojima [QC#20523,ADD]
    // QC#55590 Add Start
    /**
     * getDsPoReqFlg
     */
    public String getDsPoReqFlg(String glblCmpyCd, BigDecimal dsContrPk) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("dsContrPk", dsContrPk);
        ssmPrm.put("dsTrxRuleTpCd", DS_TRX_RULE_TP.CONTRACT);
        return (String) getSsmEZDClient().queryObject("getDsPoReqFlg", ssmPrm).getResultObject();
    }
    /**
     * getRenewalHoldforPoReleaseDsContrPrcEffPk4NonPo
     * @param glblCmpyCd
     * @param dsContrPk
     * @return
     */
    public S21SsmEZDResult getRenewalHoldforPoReleaseDsContrPrcEffPk4NonPo(String glblCmpyCd, BigDecimal dsContrPk) {

        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("dsContrPk", dsContrPk);
        params.put("renewalHoldForPO", DS_CONTR_DTL_STS.RENEWAL_HOLD_FOR_PO);
        return getSsmEZDClient().queryObjectList("getRenewalHoldforPoReleaseDsContrPrcEffPk4NonPo", params);
    }

    // QC#55590 Add Start

}
