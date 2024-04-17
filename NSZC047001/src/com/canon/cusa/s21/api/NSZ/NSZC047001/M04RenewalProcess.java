package com.canon.cusa.s21.api.NSZ.NSZC047001;

import static com.canon.cusa.s21.api.NSZ.NSZC047001.constant.NSZC047001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;

import business.db.DS_CONTR_BLLG_MTRTMsg;
import business.db.DS_CONTR_DTLTMsg;
import business.db.DS_CONTR_PRC_EFFTMsg;
import business.db.SVC_MEMOTMsg;
import business.parts.NSZC047001PMsg;
import business.parts.NSZC047004PMsg;

import com.canon.cusa.s21.common.NSX.NSXC003001.CalcSchdSmryTermAndAmtBean;
import com.canon.cusa.s21.common.NSX.NSXC003001.CalcSchdSmryTermAndAmtForBaseBean;
import com.canon.cusa.s21.common.NSX.NSXC003001.NSXC003001CalcSchdSmryTermAndAmt;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONTR_RNW_ERR_RSN;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_RSN;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * Contract Billing Schedule API
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/14/2015   Hitachi         T.Aoyagi        Create          N/A
 * 01/18/2016   Hitachi         T.Tomita        Update          QC#1088
 * 01/21/2016   Hitachi         K.Kishimoto     Update          QC#3331
 * 05/13/2016   Hitachi         T.Kanasaka      Update          QC#8233
 * 08/08/2016   Hitachi         T.Tomita        Update          QC#13146
 * 08/09/2016   Hitachi         K.Kishimoto     Update          QC#12310
 * 10/06/2017   Hitachi         M.Kidokoro      Update          QC#21546
 * 2018/06/18   Hitachi         U.Kim           Update          QC#24903
 * 2021/04/06   CITS            S.Go            Update          QC#58642
 * </pre>
 */
public class M04RenewalProcess implements ZYPConstant {

    /** ONBATCH_TYPE */
    private ONBATCH_TYPE onBatchTp;

    protected void doProcess(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {

        this.onBatchTp = onBatchType;
        checkParameter(msgMap);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }
        // Add STrat 08/09/2016 <AC#12310>
        if (isErrPerSeqNum(msgMap)) {
            return;
        }
        // Add End  08/09/2016 <AC#12310>
        renewalProcess(msgMap);
    }

    private void renewalProcess(S21ApiMessageMap msgMap) {

        NSZC047004PMsg pMsg = (NSZC047004PMsg) msgMap.getPmsg();
        String baseChrgFlg = pMsg.baseChrgFlg.getValue();
        String usgChrgFlg = pMsg.usgChrgFlg.getValue();
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        BigDecimal dsContrDtlPk = pMsg.dsContrDtlPk.getValue();
        BigDecimal dsContrBllgMtrPk = null;
        // START 2021/04/06 S.Go [QC#58642,ADD]
        String  effFromDt = pMsg.effFromDt.getValue();
        String  effThruDt = pMsg.effThruDt.getValue();
        // END 2021/04/06 S.Go [QC#58642,ADD]
        if (pMsg.xxMtrLineList.getValidCount() > 0) {
            dsContrBllgMtrPk = pMsg.xxMtrLineList.no(0).dsContrBllgMtrPk_ML.getValue();
            // START 2021/04/06 S.Go [QC#58642,ADD]
            if (effThruDt != null) {
                NSZC047001CommonLogic.updateCumCopyEndDtForDsContrBllgMtr(msgMap, glblCmpyCd, dsContrBllgMtrPk, effFromDt, effThruDt);
            }
            // END 2021/04/06 S.Go [QC#58642,ADD]
        }
        // START 2021/04/06 S.Go [QC#58642,DEL]
        // String effFromDt = pMsg.effFromDt.getValue();
        // END 2021/04/06 S.Go [QC#58642,DEL]
        String contrRnwErrRsnCd = pMsg.contrRnwErrRsnCd.getValue();

        DS_CONTR_DTLTMsg dsContrDtlTMsg = NSZC0470Query.getInstance().getDsContrDtlTMsg(glblCmpyCd, dsContrDtlPk);
        String dsContrDtlTpCd = dsContrDtlTMsg.dsContrDtlTpCd.getValue();

        List<BigDecimal> prcEffPkList = NSZC0470Query.getInstance().getPrcEffPkByEffFromDt(glblCmpyCd, dsContrDtlPk, dsContrBllgMtrPk, effFromDt, baseChrgFlg);
        if (!prcEffPkList.isEmpty()) {
            // Mod Start 01/21/2016 <QC#3331>
            NSZC047001CommonLogic.deletePrcEffAndSchdInfo(msgMap, glblCmpyCd, prcEffPkList, usgChrgFlg, dsContrDtlTpCd);
            // Mod End 01/21/2016 <QC#3331>
        }

        // get Max Price Effectivity Sequence Number
        BigDecimal seqNum = NSZC0470Query.getInstance().getMaxPrcEffSqNum(glblCmpyCd, dsContrDtlPk, dsContrBllgMtrPk, baseChrgFlg);
        if (seqNum == null) {
            seqNum = BigDecimal.ONE;
        } else {
            seqNum = seqNum.add(BigDecimal.ONE);
        }

        if (!hasValue(contrRnwErrRsnCd)) {
            createPrcEffAndSchdInfo(msgMap, dsContrDtlTMsg, seqNum);
        } else {
            // START 2017/10/06 M.Kidokoro [QC#21546,MOD]
            // // Renewal Hold
            // createPrcEffForRenewalHold(msgMap, dsContrDtlTMsg, seqNum);
            createPrcEffAndSchdInfo(msgMap, dsContrDtlTMsg, seqNum);
            if (contrRnwErrRsnCd.equals(CONTR_RNW_ERR_RSN.MAX_PRICE_UP_RATIO_ERROR)) {
                // START 2018/06/18 U.Kim [QC#24903,MOD]
                // createSvcMemo(msgMap, dsContrDtlTMsg);
                createSvcMemo(msgMap, dsContrDtlTMsg, baseChrgFlg, usgChrgFlg);
                // END 2018/06/18 U.Kim [QC#24903,MOD]
            }
            // END 2017/10/06 M.Kidokoro [QC#21546,MOD]
        }

        // get dsContrPrcEffPk
        prcEffPkList = NSZC0470Query.getInstance().getPrcEffPkByEffFromDt(glblCmpyCd, dsContrDtlPk, dsContrBllgMtrPk, effFromDt, baseChrgFlg);
        if (!prcEffPkList.isEmpty()) {
            setValue(pMsg.dsContrPrcEffPk, prcEffPkList.get(0));
        }
    }

    private void checkParameter(S21ApiMessageMap msgMap) {

        NSZC047004PMsg pMsg = (NSZC047004PMsg) msgMap.getPmsg();
        NSZC047001CommonLogic.mandatoryCheck(msgMap, pMsg.glblCmpyCd, ZZZM9007E, new String[]{"Global Company Code"});
        NSZC047001CommonLogic.mandatoryCheck(msgMap, pMsg.slsDt, ZZZM9007E, new String[]{"Salse Date"});
        NSZC047001CommonLogic.mandatoryCheck(msgMap, pMsg.dsContrDtlPk, ZZZM9007E, new String[]{"DS Contract Detail PK"});
        NSZC047001CommonLogic.mandatoryCheck(msgMap, pMsg.baseChrgFlg, ZZZM9007E, new String[]{"Base Charge Flag"});
        NSZC047001CommonLogic.mandatoryCheck(msgMap, pMsg.usgChrgFlg, ZZZM9007E, new String[]{"Usage Charge Flag"});
        NSZC047001CommonLogic.mandatoryCheck(msgMap, pMsg.effFromDt, ZZZM9007E, new String[]{"Effective From Date"});
        NSZC047001CommonLogic.mandatoryCheck(msgMap, pMsg.effThruDt, ZZZM9007E, new String[]{"Effective Thru Date"});
        if (FLG_ON_Y.equals(pMsg.baseChrgFlg.getValue())) {
            NSZC047001CommonLogic.mandatoryCheck(msgMap, pMsg.basePrcDealAmt, ZZZM9007E, new String[]{"Base Price Deal Amount"});
            NSZC047001CommonLogic.mandatoryCheck(msgMap, pMsg.basePrcTermDealAmtRate, ZZZM9007E, new String[]{"Base Price Term Deal Amount Rate"});
        }
        if (FLG_ON_Y.equals(pMsg.usgChrgFlg.getValue())) {
            for (int i = 0; i < pMsg.xxMtrLineList.getValidCount(); i++) {
                NSZC047001CommonLogic.mandatoryCheck(msgMap, pMsg.xxMtrLineList.no(i).dsContrBllgMtrPk_ML, ZZZM9007E, new String[]{"DS Contract Billing Meter PK"});
                NSZC047001CommonLogic.mandatoryCheck(msgMap, pMsg.xxMtrLineList.no(i).contrXsCopyPk_ML, ZZZM9007E, new String[]{"Contract Excess Copy PK"});
                NSZC047001CommonLogic.mandatoryCheck(msgMap, pMsg.xxMtrLineList.no(i).xsMtrAmtRate_ML, ZZZM9007E, new String[]{"Excess Meter Amount Rate"});
                NSZC047001CommonLogic.mandatoryCheck(msgMap, pMsg.xxMtrLineList.no(i).xsMtrFirstFlg_ML, ZZZM9007E, new String[]{"Excess Meter First Flag"});
            }
        }
    }

    private void createPrcEffAndSchdInfo(S21ApiMessageMap msgMap, DS_CONTR_DTLTMsg dsContrDtlTMsg, BigDecimal seqNum) {

        NSZC047004PMsg mode04PMsg = (NSZC047004PMsg) msgMap.getPmsg();
        String baseChrgFlg = mode04PMsg.baseChrgFlg.getValue();
        String usgChrgFlg = mode04PMsg.usgChrgFlg.getValue();

        NSZC047001PMsg mode01PMsg = new NSZC047001PMsg();

        setValue(mode01PMsg.glblCmpyCd, mode04PMsg.glblCmpyCd);
        setValue(mode01PMsg.xxModeCd, mode04PMsg.xxModeCd);
        setValue(mode01PMsg.slsDt, mode04PMsg.slsDt);
        setValue(mode01PMsg.dsContrDtlPk, mode04PMsg.dsContrDtlPk);
        setValue(mode01PMsg.baseChrgFlg, mode04PMsg.baseChrgFlg);
        setValue(mode01PMsg.usgChrgFlg, mode04PMsg.usgChrgFlg);
        setValue(mode01PMsg.contrEffFromDt, mode04PMsg.effFromDt);
        setValue(mode01PMsg.contrEffThruDt, mode04PMsg.effThruDt);

        if (FLG_ON_Y.equals(baseChrgFlg)) {
            setValue(mode01PMsg.contrCloDay, dsContrDtlTMsg.contrCloDay);
            setValue(mode01PMsg.baseBllgTmgCd, dsContrDtlTMsg.baseBllgTmgCd);
            setValue(mode01PMsg.contrBllgDay, dsContrDtlTMsg.contrBllgDay);
            setValue(mode01PMsg.xxBaseLineList.no(0).dsContrPrcEffSqNum_BL, seqNum);
            setValue(mode01PMsg.xxBaseLineList.no(0).effFromDt_BL, mode04PMsg.effFromDt);
            setValue(mode01PMsg.xxBaseLineList.no(0).effThruDt_BL, mode04PMsg.effThruDt);
            setValue(mode01PMsg.xxBaseLineList.no(0).baseBllgCycleCd_BL, dsContrDtlTMsg.baseBllgCycleCd);
            setValue(mode01PMsg.xxBaseLineList.no(0).basePrcDealAmt_BL, mode04PMsg.basePrcDealAmt);
            setValue(mode01PMsg.xxBaseLineList.no(0).basePrcTermDealAmtRate_BL, mode04PMsg.basePrcTermDealAmtRate);
            String dsContrPrcEffStsCd = getPrcEffStsCd(mode04PMsg);
            setValue(mode01PMsg.xxBaseLineList.no(0).dsContrPrcEffStsCd_BL, dsContrPrcEffStsCd);
            setValue(mode01PMsg.xxBaseLineList.no(0).qltyAsrnHldFlg_BL, FLG_OFF_N);
            setValue(mode01PMsg.xxBaseLineList.no(0).mtrHldFlg_BL, FLG_OFF_N);
            setValue(mode01PMsg.xxBaseLineList.no(0).contrHldFlg_BL, FLG_OFF_N);
            setValue(mode01PMsg.xxBaseLineList.no(0).bllgHldFlg_BL, FLG_OFF_N);
            // Dummy Pk Set
            setValue(mode01PMsg.xxBaseLineList.no(0).dsContrPrcEffPk_BL, BigDecimal.ZERO);
            mode01PMsg.xxBaseLineList.setValidCount(1);
        }

        if (FLG_ON_Y.equals(usgChrgFlg)) {
            setValue(mode01PMsg.mtrCloDay, dsContrDtlTMsg.mtrCloDay);
            setValue(mode01PMsg.mtrBllgTmgCd, dsContrDtlTMsg.mtrBllgTmgCd);
            setValue(mode01PMsg.mtrBllgDay, dsContrDtlTMsg.mtrBllgDay);

            for (int i = 0; i < mode04PMsg.xxMtrLineList.getValidCount(); i++) {
                // add start 2016/08/08 CSA Defect#13146
                String mtrBllgCycleCd = null;
                DS_CONTR_BLLG_MTRTMsg dsContrBllgMtrTMsg = NSZC0470Query.getInstance().getBllgMtrTMsg(mode04PMsg.glblCmpyCd.getValue(), mode04PMsg.xxMtrLineList.no(i).dsContrBllgMtrPk_ML.getValue());
                if (dsContrBllgMtrTMsg != null) {
                    mtrBllgCycleCd = dsContrBllgMtrTMsg.bllgMtrBllgCycleCd.getValue();
                }
                // add end 2016/08/08 CSA Defect#13146
                setValue(mode01PMsg.xxMtrLineList.no(i).dsContrPrcEffSqNum_ML, seqNum);
                setValue(mode01PMsg.xxMtrLineList.no(i).effFromDt_ML, mode04PMsg.effFromDt);
                setValue(mode01PMsg.xxMtrLineList.no(i).effThruDt_ML, mode04PMsg.effThruDt);
                // mod start 2016/08/08 CSA Defect#13146
                setValue(mode01PMsg.xxMtrLineList.no(i).mtrBllgCycleCd_ML, mtrBllgCycleCd);
                // mod end 2016/08/08 CSA Defect#13146
                setValue(mode01PMsg.xxMtrLineList.no(i).dsContrBllgMtrPk_ML, mode04PMsg.xxMtrLineList.no(i).dsContrBllgMtrPk_ML);
                setValue(mode01PMsg.xxMtrLineList.no(i).contrXsCopyPk_ML, mode04PMsg.xxMtrLineList.no(i).contrXsCopyPk_ML);
                setValue(mode01PMsg.xxMtrLineList.no(i).xsMtrCopyQty_ML, mode04PMsg.xxMtrLineList.no(i).xsMtrCopyQty_ML);
                setValue(mode01PMsg.xxMtrLineList.no(i).xsMtrAmtRate_ML, mode04PMsg.xxMtrLineList.no(i).xsMtrAmtRate_ML);
                setValue(mode01PMsg.xxMtrLineList.no(i).xsMtrFirstFlg_ML, mode04PMsg.xxMtrLineList.no(i).xsMtrFirstFlg_ML);
                String dsContrPrcEffStsCd = getPrcEffStsCd(mode04PMsg);
                setValue(mode01PMsg.xxMtrLineList.no(i).dsContrPrcEffStsCd_ML, dsContrPrcEffStsCd);
                setValue(mode01PMsg.xxMtrLineList.no(i).qltyAsrnHldFlg_ML, FLG_OFF_N);
                setValue(mode01PMsg.xxMtrLineList.no(i).mtrHldFlg_ML, FLG_OFF_N);
                setValue(mode01PMsg.xxMtrLineList.no(i).contrHldFlg_ML, FLG_OFF_N);
                setValue(mode01PMsg.xxMtrLineList.no(i).bllgHldFlg_ML, FLG_OFF_N);
                // Dummy Pk Set
                setValue(mode01PMsg.xxMtrLineList.no(i).dsContrPrcEffPk_ML, BigDecimal.ZERO);
                mode01PMsg.xxMtrLineList.setValidCount(i + 1);
            }
        }

        callCreateContractProcess(msgMap, mode01PMsg);
    }

    private boolean callCreateContractProcess(S21ApiMessageMap msgMap, NSZC047001PMsg pMsg) {
        S21ApiMessageMap newMsgMap = new S21ApiMessageMap(pMsg);
        M01CreateContractProcess createContractProcess = new M01CreateContractProcess(newMsgMap);
        createContractProcess.doProcess(newMsgMap, this.onBatchTp);
        return true;
    }

    // START 2017/10/06 K.Kojima [QC#21547,DEL]
    // private boolean createPrcEffForRenewalHold(S21ApiMessageMap msgMap, DS_CONTR_DTLTMsg dsContrDtlTMsg, BigDecimal seqNum) {
    // 
    //     NSZC047004PMsg pMsg = (NSZC047004PMsg) msgMap.getPmsg();
    //     BigDecimal dsContrPrcEffPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CONTR_PRC_EFF_SQ);
    // 
    //     DS_CONTR_PRC_EFFTMsg inTMsg = new DS_CONTR_PRC_EFFTMsg();
    //     setValue(inTMsg.glblCmpyCd, pMsg.glblCmpyCd);
    //     setValue(inTMsg.dsContrPrcEffPk, dsContrPrcEffPk);
    //     setValue(inTMsg.dsContrPrcEffSqNum, seqNum);
    //     setValue(inTMsg.dsContrDtlPk, pMsg.dsContrDtlPk);
    //     setValue(inTMsg.dsContrBllgMtrPk, pMsg.xxMtrLineList.no(0).dsContrBllgMtrPk_ML);
    //     setValue(inTMsg.dsContrPrcEffStsCd, DS_CONTR_DTL_STS.RENEWAL_HOLD);
    //     setValue(inTMsg.contrPrcEffFromDt, pMsg.effFromDt);
    //     setValue(inTMsg.contrPrcEffThruDt, pMsg.effThruDt);
    //     inTMsg.bllgCycleCd.clear();
    //     inTMsg.basePrcDealAmt.clear();
    //     inTMsg.basePrcFuncAmt.clear();
    //     inTMsg.basePrcTermDealAmtRate.clear();
    //     inTMsg.basePrcTermFuncAmtRate.clear();
    //     inTMsg.ccyCd.clear();
    //     setValue(inTMsg.baseChrgFlg, pMsg.baseChrgFlg);
    //     setValue(inTMsg.usgChrgFlg, pMsg.usgChrgFlg);
    //     setValue(inTMsg.contrRnwErrRsnCd, pMsg.contrRnwErrRsnCd);
    //     setValue(inTMsg.qltyAsrnHldFlg, FLG_OFF_N);
    //     setValue(inTMsg.mtrHldFlg, FLG_OFF_N);
    //     setValue(inTMsg.contrHldFlg, FLG_OFF_N);
    //     setValue(inTMsg.bllgHldFlg, FLG_OFF_N);
    //     // START 2016/01/18 T.Tomita [QC#1088, ADD]
    //     setValue(inTMsg.qltyAsrnHldPendApvlFlg, ZYPConstant.FLG_OFF_N);
    //     // END 2016/01/18 T.Tomita [QC#1088, ADD]
    //     S21ApiTBLAccessor.insert(inTMsg);
    // 
    //     if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
    //         msgMap.addXxMsgIdWithPrm(NSAM0032E, new String[]{"DS_CONTR_PRC_EFF"});
    //         return false;
    //     }
    //     return true;
    // }
    // END 2017/10/06 K.Kojima [QC#21547,DEL]

    private String getPrcEffStsCd(NSZC047004PMsg mode04PMsg) {

        String effFromDt = mode04PMsg.effFromDt.getValue();
        String effThruDt = mode04PMsg.effThruDt.getValue();
        String slsDt = mode04PMsg.slsDt.getValue();

        if (effFromDt.compareTo(slsDt) <= 0 && slsDt.compareTo(effThruDt) <= 0) {
            return DS_CONTR_DTL_STS.ACTIVE;
        }
        return DS_CONTR_DTL_STS.SIGNED;
    }
    // Add Start 08/09/2016 <QC#12310>
    private Boolean isErrPerSeqNum(S21ApiMessageMap msgMap) {
        NSZC047004PMsg pMsg = (NSZC047004PMsg) msgMap.getPmsg();
        List<Map<String, Object>>contrInfoList = NSZC0470Query.getInstance().getContrInfo(pMsg.glblCmpyCd.getValue(), pMsg.dsContrDtlPk.getValue());

        CalcSchdSmryTermAndAmtBean inBean = new CalcSchdSmryTermAndAmtBean();
        inBean.setGlblCmpyCd(pMsg.glblCmpyCd.getValue());
        inBean.setBllgSchdFromDt(pMsg.effFromDt.getValue());
        inBean.setBllgSchdThruDt(pMsg.effThruDt.getValue());
        inBean.setBllgCycleCd((String) contrInfoList.get(0).get("BASE_BLLG_CYCLE_CD"));
        inBean.setContrCloDay((String) contrInfoList.get(0).get("CONTR_CLO_DAY"));
        inBean.setBasePrcDealAmt(pMsg.basePrcDealAmt.getValue());
        inBean.setBaseChrgFlg(FLG_ON_Y);
        inBean.setUsgChrgFlg(FLG_OFF_N);
        inBean.setCcyCd((String) contrInfoList.get(0).get("CCY_CD"));

        CalcSchdSmryTermAndAmtBean outBean = NSXC003001CalcSchdSmryTermAndAmt.calcSchdSmryTermAndAmt(inBean);
        List<CalcSchdSmryTermAndAmtForBaseBean> tmpTopSchdBeanList = outBean.getBaseList();
        for (CalcSchdSmryTermAndAmtForBaseBean tmpTopSchdBean :tmpTopSchdBeanList) {
            if (OVER_PER_SEQ.compareTo(tmpTopSchdBean.getPerSchdNum()) <= 0) {
                msgMap.addXxMsgId(NSZM1054E);
                return true;
            }
        }
        
        return false;
        // Add End  08/09/2016 <QC#12310>
    }

    // START 2017/10/06 M.Kidokoro [QC#21546,ADD]
    // START 2018/06/18 U.Kim [QC#24903,MOD]
    // private boolean createSvcMemo(S21ApiMessageMap msgMap, DS_CONTR_DTLTMsg dsContrDtlTMsg) {
    private boolean createSvcMemo(S21ApiMessageMap msgMap, DS_CONTR_DTLTMsg dsContrDtlTMsg, String baseChrgFlg, String usgChrgFlg) {
    // END 2018/06/18 U.Kim [QC#24903,MOD]
        BigDecimal memoPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.SVC_MEMO_SQ);
        // START 2018/06/18 U.Kim [QC#24903,MOD]
        // String contrRnwErrRsnTxt = S21MessageFunc.clspGetMessage(NSZM0846E);
        String contrRnwErrRsnTxt = null;
        if (ZYPConstant.FLG_ON_Y.equals(baseChrgFlg)) {
            contrRnwErrRsnTxt = S21MessageFunc.clspGetMessage(NSZM0845E);
        } else if (ZYPConstant.FLG_ON_Y.equals(usgChrgFlg)) {
            contrRnwErrRsnTxt = S21MessageFunc.clspGetMessage(NSZM0846E);
        }
        // END 2018/06/18 U.Kim [QC#24903,MOD]

        SVC_MEMOTMsg inParam = new SVC_MEMOTMsg();
        ZYPEZDItemValueSetter.setValue(inParam.glblCmpyCd, dsContrDtlTMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(inParam.svcMemoPk, memoPk);
        ZYPEZDItemValueSetter.setValue(inParam.svcMemoCatgCd, SVC_MEMO_CATG.CONTRACT_MEMO);
        ZYPEZDItemValueSetter.setValue(inParam.svcMemoTpCd, SVC_MEMO_TP.RENEW_CONTRACT_OR_MACHINE);
        ZYPEZDItemValueSetter.setValue(inParam.svcCmntTxt, contrRnwErrRsnTxt);
        ZYPEZDItemValueSetter.setValue(inParam.dsContrPk, dsContrDtlTMsg.dsContrPk.getValue());
        ZYPEZDItemValueSetter.setValue(inParam.dsContrDtlPk, dsContrDtlTMsg.dsContrDtlPk.getValue());
        ZYPEZDItemValueSetter.setValue(inParam.lastUpdUsrId, S21UserProfileServiceFactory.getInstance().getService().getContextUserInfo().getUserId());
        ZYPEZDItemValueSetter.setValue(inParam.lastUpdTs, ZYPDateUtil.getCurrentSystemTime(DT_FORMAT_TS));
        ZYPEZDItemValueSetter.setValue(inParam.svcMemoRsnCd, SVC_MEMO_RSN.RENEWAL_PROGRAM);

        S21ApiTBLAccessor.insert(inParam);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inParam.getReturnCode())) {
            msgMap.addXxMsgId(NSZM0391E);
            return false;
        }
        return true;
    }
    // END 2017/10/06 M.Kidokoro [QC#21546,ADD]
}
