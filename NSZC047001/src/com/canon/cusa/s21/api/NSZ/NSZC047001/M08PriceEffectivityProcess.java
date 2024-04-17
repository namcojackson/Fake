package com.canon.cusa.s21.api.NSZ.NSZC047001;

import static com.canon.cusa.s21.api.NSZ.NSZC047001.constant.NSZC047001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;
import business.db.DS_CONTR_BLLG_MTRTMsg;
import business.db.DS_CONTR_BLLG_SCHDTMsg;
import business.db.DS_CONTR_BLLG_SCHD_SMRYTMsg;
import business.db.DS_CONTR_DTLTMsg;
import business.db.DS_CONTR_PRC_EFFTMsg;
import business.parts.NSZC047001PMsg;
import business.parts.NSZC047001_xxBaseLineListPMsg;
import business.parts.NSZC047001_xxBaseLineListPMsgArray;
import business.parts.NSZC047001_xxMtrLineListPMsg;
import business.parts.NSZC047001_xxMtrLineListPMsgArray;
import business.parts.NSZC047008PMsg;
import business.parts.NSZC047008_xxBaseLineListPMsg;
import business.parts.NSZC047008_xxMtrLineListPMsg;

import com.canon.cusa.s21.common.NSX.NSXC003001.CalcSchdSmryTermAndAmtBean;
import com.canon.cusa.s21.common.NSX.NSXC003001.CalcSchdSmryTermAndAmtForBaseBean;
import com.canon.cusa.s21.common.NSX.NSXC003001.CalcSchdSmryTermAndAmtForUsageBean;
import com.canon.cusa.s21.common.NSX.NSXC003001.CalcSchdTermAndAmtLineBean;
import com.canon.cusa.s21.common.NSX.NSXC003001.NSXC003001CalcSchdSmryTermAndAmt;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BLLG_CYCLE;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;

/**
 * <pre>
 * Contract Billing Schedule API
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/14/2015   Hitachi         T.Aoyagi        Create          N/A
 * 03/28/2016   Hitachi         K.Kishimoto     Update          QC#1003
 * 08/02/2016   Hitachi         K.Kishimoto     Update          QC#7402
 * 2016/09/28   Hitachi         K.Kishimoto     Update          QC#14853
 * 2017/10/02   Hitachi         K.Kitachi       Update          QC#21212
 * 2018/06/25   Hitachi         K.Kitachi       Update          QC#22245
 * 2019/11/15   Hitachi         K.Kitachi       Update          QC#54021
 * 2020/03/18   Hitachi         K.Kitachi       Update          QC#55693
 * 2022/10/23   CITS            T.Suzuki        Update          QC#58427
 *</pre>
 */
public class M08PriceEffectivityProcess implements ZYPConstant {

    /** ONBATCH_TYPE */
    private ONBATCH_TYPE onBatchTp;

    protected void doProcess(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {

        this.onBatchTp = onBatchType;
        checkParameter(msgMap);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }
        priceEffectivityProcess(msgMap);
    }

    private void priceEffectivityProcess(S21ApiMessageMap msgMap) {

        NSZC047001PMsg pMsg = mapToMode01PMsg(msgMap);

        setValue(pMsg.contrCloDay, NSZC047001CommonLogic.convCloDay(pMsg.contrCloDay.getValue()));
        setValue(pMsg.mtrCloDay, NSZC047001CommonLogic.convCloDay(pMsg.mtrCloDay.getValue()));

        S21ApiMessageMap delMsgMap = new S21ApiMessageMap(pMsg);
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        NSZC047001_xxBaseLineListPMsgArray baseList = pMsg.xxBaseLineList;
        NSZC047001_xxMtrLineListPMsgArray usageList = pMsg.xxMtrLineList;
        // Mod Start 03/28/2016 <QC#1003>
        List<Map<String, Object>> contrInfoList = NSZC0470Query.getInstance().getContrInfo(pMsg.glblCmpyCd.getValue(), pMsg.dsContrDtlPk.getValue());
        String dsContrDtlTp = (String) contrInfoList.get(0).get("DS_CONTR_DTL_TP_CD");
        // Mod End   03/28/2016 <QC#1003>
        String invSchdMaxThruDt = NSZC047001CommonLogic.getInvSchdMaxThruDt(pMsg);
        List<Map<String, Object>> currPrcEffList = NSZC047001CommonLogic.getPrcEffList(pMsg);

        // START 2022/10/23 T.Suzuki [QC#58427, MOD]
//        if (hasValue(invSchdMaxThruDt)) {
//            NSZC047001CommonLogic.deletePrcEffAndSchdInfo(delMsgMap, dsContrDtlTp, invSchdMaxThruDt);
//            NSZC047001CommonLogic.updatePrcEffAndSchdInfo(delMsgMap, invSchdMaxThruDt);
//        } else {
//            for (Map<String, Object> currPrcEff : currPrcEffList) {
//                NSZC047001CommonLogic.deletePrcEffAndSchdInfo(delMsgMap, ((BigDecimal) currPrcEff.get("DS_CONTR_PRC_EFF_PK")));
//            }
//        }
        String tmpEffFromDt = null;
        String dsContrPrcEffStsCd = null;
        NSZC047008PMsg nszc047008PMsg = (NSZC047008PMsg) msgMap.getPmsg();
        if (hasValue(nszc047008PMsg.svcCrRebilPk)) {
            if (baseList.getValidCount() > 0) {
                NSZC047001_xxBaseLineListPMsg baseLine = baseList.no(0);
                tmpEffFromDt = baseLine.effFromDt_BL.getValue();
                dsContrPrcEffStsCd = baseLine.dsContrPrcEffStsCd_BL.getValue();
            } else if (usageList.getValidCount() > 0) {
                NSZC047001_xxMtrLineListPMsg usageLine = usageList.no(0);
                tmpEffFromDt = usageLine.effFromDt_ML.getValue();
                dsContrPrcEffStsCd = usageLine.dsContrPrcEffStsCd_ML.getValue();
                }
        }

        if (hasValue(nszc047008PMsg.svcCrRebilPk) && ZYPDateUtil.compare(pMsg.slsDt.getValue(), tmpEffFromDt) < 0
                && (DS_CONTR_DTL_STS.RENEWAL_HOLD_FOR_PO.equals(dsContrPrcEffStsCd)
                || DS_CONTR_DTL_STS.RENEWAL_HOLD.equals(dsContrPrcEffStsCd))) {
            invSchdMaxThruDt = ZYPDateUtil.addDays(tmpEffFromDt, -1);
        } else {
            if (hasValue(invSchdMaxThruDt)) {
                NSZC047001CommonLogic.deletePrcEffAndSchdInfo(delMsgMap, dsContrDtlTp, invSchdMaxThruDt);
                NSZC047001CommonLogic.updatePrcEffAndSchdInfo(delMsgMap, invSchdMaxThruDt);
            } else {
                for (Map<String, Object> currPrcEff : currPrcEffList) {
                    NSZC047001CommonLogic.deletePrcEffAndSchdInfo(delMsgMap, ((BigDecimal) currPrcEff.get("DS_CONTR_PRC_EFF_PK")));
                }
            }
        }
        // END 2022/10/23 T.Suzuki [QC#58427, MOD]

        // Mod Start 08/02/2016 <QC#7402>
        // ----------------------------------------
        // Base Charge
        // ----------------------------------------
        for (int i = 0; i < baseList.getValidCount(); i++) {
            pMsg = mapToMode01PMsg(msgMap, i);

            NSZC047001_xxBaseLineListPMsg linePMsg = baseList.no(i);
            String effFromDt  = linePMsg.effFromDt_BL.getValue();
            String effThruDt  = linePMsg.effThruDt_BL.getValue();
            if (hasValue(invSchdMaxThruDt) && effThruDt.compareTo(invSchdMaxThruDt) <= 0) {
                continue;
            }
            if (hasValue(invSchdMaxThruDt)) {
                if (invSchdMaxThruDt.compareTo(effFromDt) >= 0 && invSchdMaxThruDt.compareTo(effThruDt) <= 0) {
                    BigDecimal basePrcTermRate = pMsg.xxBaseLineList.no(0).basePrcTermDealAmtRate_BL.getValue();
                    Map<String, BigDecimal> smryBasePrcAmt = NSZC0470Query.getInstance().getSummaryBasePrcAmt(glblCmpyCd, pMsg.dsContrDtlPk.getValue(), null, effFromDt, invSchdMaxThruDt, FLG_ON_Y);
                    BigDecimal fixedBasePrcTermRate = BigDecimal.ZERO;
                    if (smryBasePrcAmt != null) {
                        fixedBasePrcTermRate = (BigDecimal) smryBasePrcAmt.get("BASE_ACTL_PRC_DEAL_AMT");
                        if (!hasValue(fixedBasePrcTermRate)) {
                            fixedBasePrcTermRate = BigDecimal.ZERO;
                        }
                    }
                    if (hasValue(basePrcTermRate)) {
                        basePrcTermRate = basePrcTermRate.subtract(fixedBasePrcTermRate);
                        setValue(pMsg.xxBaseLineList.no(0).basePrcTermDealAmtRate_BL, basePrcTermRate);
                    }
                }
            }

            if (!hasValue(linePMsg.dsContrPrcEffPk_BL)) {
                setValue(pMsg.xxBaseLineList.no(0).dsContrPrcEffPk_BL, BigDecimal.ZERO);
            }
            // create Price Effectivity and Schedule Information
            if (hasValue(invSchdMaxThruDt) && effFromDt.compareTo(invSchdMaxThruDt) <= 0 && effThruDt.compareTo(invSchdMaxThruDt) >= 0 ) {
                DS_CONTR_DTLTMsg dsContrDtlTMsg = NSZC0470Query.getInstance().getDsContrDtlTMsg(glblCmpyCd, pMsg.dsContrDtlPk.getValue());
                updatePrcEffAndSchdInfoForBaseSchdAdd(msgMap, dsContrDtlTMsg, pMsg, linePMsg, invSchdMaxThruDt);
            } else {
                createPrcEffAndSchdInfo(msgMap, pMsg, effFromDt, effThruDt);
            }
            if (msgMap.getMsgMgr().isXxMsgId()) {
                return;
            }
        }
        // ----------------------------------------
        // Meter Charge
        // ----------------------------------------
        BigDecimal preBllgMtrPk = null;
        String preFromDt = null;
        for (int i = 0; i < usageList.getValidCount(); i++) {

            pMsg = mapToMode01PMsg(msgMap, i);
            NSZC047001_xxMtrLineListPMsg linePMsg = usageList.no(i);
            BigDecimal bllgMtrPk = linePMsg.dsContrBllgMtrPk_ML.getValue();

            String effFromDt = linePMsg.effFromDt_ML.getValue();
            String effThruDt = linePMsg.effThruDt_ML.getValue();
            if ((hasValue(preBllgMtrPk) && preBllgMtrPk.compareTo(bllgMtrPk) == 0) && (hasValue(preFromDt) && preFromDt.equals(effFromDt))) {
                continue;
            }
            preBllgMtrPk = bllgMtrPk;
            preFromDt = effFromDt;
            if (hasValue(invSchdMaxThruDt) && effThruDt.compareTo(invSchdMaxThruDt) <= 0) {
                continue;
            }
//            if (hasValue(invSchdMaxThruDt)) {
//                if (invSchdMaxThruDt.compareTo(effFromDt) >= 0 && invSchdMaxThruDt.compareTo(effThruDt) <= 0) {
//                    effFromDt = ZYPDateUtil.addDays(invSchdMaxThruDt, 1);
//                    setValue(pMsg.xxMtrLineList.no(0).effFromDt_ML, effFromDt);
//                    addPeSeqNumVal = BigDecimal.ONE;
//                }
//            }

            for (int j = 0; j < pMsg.xxMtrLineList.getValidCount(); j++) {
                if (!hasValue(linePMsg.dsContrPrcEffPk_ML)) {
                    setValue(pMsg.xxMtrLineList.no(j).dsContrPrcEffPk_ML, BigDecimal.ZERO);
                }
            }
            // create Price Effectivity and Schedule Information
            if (hasValue(invSchdMaxThruDt) && effFromDt.compareTo(invSchdMaxThruDt) <= 0 && effThruDt.compareTo(invSchdMaxThruDt) >= 0 ) {
                DS_CONTR_DTLTMsg dsContrDtlTMsg = NSZC0470Query.getInstance().getDsContrDtlTMsg(glblCmpyCd, pMsg.dsContrDtlPk.getValue());
                updatePrcEffAndSchdInfoForUsageSchdAdd(msgMap, dsContrDtlTMsg, pMsg, invSchdMaxThruDt);
            } else {
                createPrcEffAndSchdInfo(msgMap, pMsg, effFromDt, effThruDt);
            }
            if (msgMap.getMsgMgr().isXxMsgId()) {
                return;
            }
        }
        // Mod End   08/02/2016 <QC#7402>
        // Add Start 03/28/2016 <QC#1003>
        if (usageList.getValidCount() > 0) {
            NSZC047001CommonLogic.mtrEntryStsUpd(msgMap, glblCmpyCd, pMsg.slsDt.getValue(), (BigDecimal) contrInfoList.get(0).get("DS_CONTR_PK"));
            // START 2020/03/18 K.Kitachi [QC#55693, ADD]
            NSZC047001CommonLogic.resetXsCopyPk(msgMap, glblCmpyCd, (BigDecimal) contrInfoList.get(0).get("DS_CONTR_PK"));
            // END 2020/03/18 K.Kitachi [QC#55693, ADD]
        }
        // Add End   03/28/2016 <QC#1003>
    }

    private void checkParameter(S21ApiMessageMap msgMap) {

        NSZC047008PMsg pMsg = (NSZC047008PMsg) msgMap.getPmsg();
        NSZC047001CommonLogic.mandatoryCheck(msgMap, pMsg.glblCmpyCd, ZZZM9007E, new String[]{"Global Company Code"});
        NSZC047001CommonLogic.mandatoryCheck(msgMap, pMsg.slsDt, ZZZM9007E, new String[]{"Salse Date"});
        NSZC047001CommonLogic.mandatoryCheck(msgMap, pMsg.dsContrDtlPk, ZZZM9007E, new String[]{"DS Contract Detail PK"});
        NSZC047001CommonLogic.mandatoryCheck(msgMap, pMsg.baseChrgFlg, ZZZM9007E, new String[]{"Base Charge Flag"});
        NSZC047001CommonLogic.mandatoryCheck(msgMap, pMsg.usgChrgFlg, ZZZM9007E, new String[]{"Usage Charge Flag"});
        NSZC047001CommonLogic.mandatoryCheck(msgMap, pMsg.contrEffFromDt, ZZZM9007E, new String[]{"Contract Effective From Date"});
        NSZC047001CommonLogic.mandatoryCheck(msgMap, pMsg.contrEffThruDt, ZZZM9007E, new String[]{"Contract Effective Thru Date"});
        if (FLG_ON_Y.equals(pMsg.baseChrgFlg.getValue())) {
            NSZC047001CommonLogic.mandatoryCheck(msgMap, pMsg.contrCloDay, ZZZM9007E, new String[]{"Contract Close Day"});
            NSZC047001CommonLogic.mandatoryCheck(msgMap, pMsg.baseBllgTmgCd, ZZZM9007E, new String[]{"Base Billing Timing Code"});
            NSZC047001CommonLogic.mandatoryCheck(msgMap, pMsg.contrBllgDay, ZZZM9007E, new String[]{"Contract Billing Day"});
        }
        if (FLG_ON_Y.equals(pMsg.usgChrgFlg.getValue())) {
            NSZC047001CommonLogic.mandatoryCheck(msgMap, pMsg.mtrCloDay, ZZZM9007E, new String[]{"Meter Close Day"});
            NSZC047001CommonLogic.mandatoryCheck(msgMap, pMsg.mtrBllgTmgCd, ZZZM9007E, new String[]{"Meter Billing Timing Code"});
            NSZC047001CommonLogic.mandatoryCheck(msgMap, pMsg.mtrBllgDay, ZZZM9007E, new String[]{"Meter Billing Day"});
        }
    }

    private void createPrcEffAndSchdInfo(S21ApiMessageMap msgMap, NSZC047001PMsg pMsg, String peFromDt, String peThruDt) {

        callCreateContractProcess(msgMap, pMsg, peFromDt, peThruDt);
    }

    private boolean callCreateContractProcess(S21ApiMessageMap msgMap, NSZC047001PMsg pMsg, String contrEffFromDt, String contrEffThruDt) {
        setValue(pMsg.contrEffFromDt, contrEffFromDt);
        setValue(pMsg.contrEffThruDt, contrEffThruDt);
        S21ApiMessageMap newMsgMap = new S21ApiMessageMap(pMsg);
        M01CreateContractProcess createContractProcess = new M01CreateContractProcess(newMsgMap);
        createContractProcess.doProcess(newMsgMap, this.onBatchTp);
        return true;
    }

    private NSZC047001PMsg mapToMode01PMsg(S21ApiMessageMap msgMap) {

        NSZC047008PMsg mode08PMsg = (NSZC047008PMsg) msgMap.getPmsg();
        NSZC047001PMsg mode01PMsg = new NSZC047001PMsg();

        EZDMsg.copy(mode08PMsg, null, mode01PMsg, null);

        if (FLG_ON_Y.equals(mode08PMsg.baseChrgFlg.getValue())) {
            for (int i = 0; i < mode08PMsg.xxBaseLineList.getValidCount(); i++) {
                NSZC047001_xxBaseLineListPMsg mode01LinePMsg = mode01PMsg.xxBaseLineList.no(i);
                NSZC047008_xxBaseLineListPMsg mode08LinePMsg = mode08PMsg.xxBaseLineList.no(i);
                EZDMsg.copy(mode08LinePMsg, null, mode01LinePMsg, null);
                mode01PMsg.xxBaseLineList.setValidCount(i + 1);
            }
        }

        if (FLG_ON_Y.equals(mode08PMsg.usgChrgFlg.getValue())) {

            for (int j = 0; j < mode08PMsg.xxMtrLineList.getValidCount(); j++) {
                NSZC047001_xxMtrLineListPMsg mode01LinePMsg = mode01PMsg.xxMtrLineList.no(j);
                NSZC047008_xxMtrLineListPMsg mode08LinePMsg = mode08PMsg.xxMtrLineList.no(j);
                EZDMsg.copy(mode08LinePMsg, null, mode01LinePMsg, null);
                mode01PMsg.xxMtrLineList.setValidCount(j + 1);
            }
        }
        return mode01PMsg;
    }

    private NSZC047001PMsg mapToMode01PMsg(S21ApiMessageMap msgMap, int idx) {

        NSZC047008PMsg mode08PMsg = (NSZC047008PMsg) msgMap.getPmsg();
        NSZC047001PMsg mode01PMsg = new NSZC047001PMsg();

        EZDMsg.copy(mode08PMsg, null, mode01PMsg, null);

        if (FLG_ON_Y.equals(mode08PMsg.baseChrgFlg.getValue())) {

            NSZC047001_xxBaseLineListPMsg mode01LinePMsg = mode01PMsg.xxBaseLineList.no(0);
            NSZC047008_xxBaseLineListPMsg mode08LinePMsg = mode08PMsg.xxBaseLineList.no(idx);
            EZDMsg.copy(mode08LinePMsg, null, mode01LinePMsg, null);
            mode01PMsg.xxBaseLineList.setValidCount(1);
        }

        if (FLG_ON_Y.equals(mode08PMsg.usgChrgFlg.getValue())) {
            int idx01 = 0;
            BigDecimal nextBllgMtrPk = null;
            String nextFromDt = null;
            BigDecimal bllgMtrPk = null;
            String fromDt = null;
            for (int j = idx; j < mode08PMsg.xxMtrLineList.getValidCount(); j++) {
                NSZC047001_xxMtrLineListPMsg mode01LinePMsg = mode01PMsg.xxMtrLineList.no(idx01);
                NSZC047008_xxMtrLineListPMsg mode08LinePMsg = mode08PMsg.xxMtrLineList.no(j);
                EZDMsg.copy(mode08LinePMsg, null, mode01LinePMsg, null);
                mode01PMsg.xxMtrLineList.setValidCount(idx01 + 1);
                idx01++;
                if (mode08PMsg.xxMtrLineList.getValidCount() <= j + 1) {
                    break;
                }
                bllgMtrPk = mode01PMsg.xxMtrLineList.no(j).dsContrBllgMtrPk_ML.getValue();
                fromDt = mode01PMsg.xxMtrLineList.no(j).effFromDt_ML.getValue();
                nextBllgMtrPk = mode01PMsg.xxMtrLineList.no(j + 1).dsContrBllgMtrPk_ML.getValue();
                nextFromDt = mode01PMsg.xxMtrLineList.no(j + 1).effFromDt_ML.getValue();

                if (!((hasValue(bllgMtrPk) && bllgMtrPk.compareTo(nextBllgMtrPk) == 0) && (hasValue(fromDt) && fromDt.equals(nextFromDt)))) {
                    break;
                }
            }
        }
        return mode01PMsg;
    }

    // Add Start 08/02/2016 <QC#7402>
    private void updatePrcEffAndSchdInfoForBaseSchdAdd(S21ApiMessageMap msgMap, DS_CONTR_DTLTMsg dsContrDtlTMsg, NSZC047001PMsg mode01PMsg, NSZC047001_xxBaseLineListPMsg linePMsg, String invSchdMaxThruDt) {

        NSZC047008PMsg pMsg = (NSZC047008PMsg) msgMap.getPmsg();

        String effThruDt = linePMsg.effThruDt_BL.getValue();
        String baseChrgFlg = pMsg.baseChrgFlg.getValue();
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        BigDecimal dsContrDtlPk = pMsg.dsContrDtlPk.getValue();

        List<Map<String, BigDecimal>> updateBllgSchdList = NSZC0470Query.getInstance().getBllgSchdForUpdateByFromDt(glblCmpyCd, dsContrDtlPk, null, invSchdMaxThruDt, baseChrgFlg);
        List<BigDecimal> prcEffPkList = getPrcEffPkList(updateBllgSchdList);
        List<BigDecimal> schdSmryPkList = getSchdSmryPkList(updateBllgSchdList);
        // START 2018/06/25 K.Kitachi [QC#22245, ADD]
        if (prcEffPkList.size() == 0 || schdSmryPkList.size() == 0) {
            return;
        }
        // END 2018/06/25 K.Kitachi [QC#22245, ADD]

        DS_CONTR_PRC_EFFTMsg prcEffTMsg = null;
        for (BigDecimal prcEffPk : prcEffPkList) {

            DS_CONTR_PRC_EFFTMsg tmpPrcEffTMsg = NSZC0470Query.getInstance().getDsContrPrcEffTMsg(glblCmpyCd, prcEffPk);
            if (tmpPrcEffTMsg.contrPrcEffFromDt.getValue().compareTo(invSchdMaxThruDt) <= 0 && invSchdMaxThruDt.compareTo(tmpPrcEffTMsg.contrPrcEffThruDt.getValue()) <= 0) {
                prcEffTMsg = tmpPrcEffTMsg;
                break;
            }
        }
        // START 2019/11/15 K.Kitachi [QC#54021, ADD]
        if (prcEffTMsg == null) {
            return;
        }
        // END 2019/11/15 K.Kitachi [QC#54021, ADD]
        setValue(prcEffTMsg.contrPrcEffThruDt, effThruDt);
        BigDecimal calcTermAmt = null;
        BigDecimal prmTermAmt = linePMsg.basePrcTermDealAmtRate_BL.getValue();
        if (hasValue(prmTermAmt)) {
            BigDecimal peFixTermAmt = NSZC0470Query.getInstance().getFixBaseTermAmtRate(glblCmpyCd, prcEffTMsg.dsContrPrcEffPk.getValue(), null);
            calcTermAmt = prmTermAmt.subtract(peFixTermAmt);
        }

        DS_CONTR_BLLG_SCHD_SMRYTMsg schdSmryTMsg = null;
        
        for (BigDecimal schdSmryPk : schdSmryPkList) {
            DS_CONTR_BLLG_SCHD_SMRYTMsg tmpSchdSmryTMsg = NSZC0470Query.getInstance().getDsContrBllgSchdSmryTMsg(glblCmpyCd, schdSmryPk);
            if (tmpSchdSmryTMsg.bllgSchdFromDt.getValue().compareTo(invSchdMaxThruDt) <= 0 && invSchdMaxThruDt.compareTo(tmpSchdSmryTMsg.bllgSchdThruDt.getValue()) <= 0) {
                schdSmryTMsg = tmpSchdSmryTMsg;
                break;
            }
        }
        // START 2019/11/15 K.Kitachi [QC#54021, ADD]
        if (schdSmryTMsg == null) {
            return;
        }
        // END 2019/11/15 K.Kitachi [QC#54021, ADD]

        setValue(mode01PMsg.xxRqstFlg, FLG_OFF_N);
        S21ApiMessageMap newMsgMap = new S21ApiMessageMap(mode01PMsg);
        M01CreateContractProcess createContractProcess = new M01CreateContractProcess(newMsgMap);

        String tmpFromDt = ZYPDateUtil.addDays(invSchdMaxThruDt, 1);
        List<CalcSchdSmryTermAndAmtForBaseBean> tmpTopSchdBeanList = calcSchdSmryTermAndAmt(newMsgMap, dsContrDtlTMsg, schdSmryTMsg, tmpFromDt, effThruDt, calcTermAmt);
        boolean isUpdateTopSchd = false;
        int asisTopSchdSqNum = 0;
        int asisSchdSqNum = 0; 
        // Mod Start 09/28/2016 <QC#14853>
        if (!BLLG_CYCLE.DAILY.equals(schdSmryTMsg.perBllgCycleCd.getValue()) && schdSmryTMsg.perBllgCycleCd.getValue().equals(tmpTopSchdBeanList.get(0).getPerBllgCycleCd())) {
            isUpdateTopSchd = true;
            asisTopSchdSqNum = Integer.parseInt(schdSmryTMsg.dsContrBllgSchdSqNum.getValue());
            DS_CONTR_BLLG_SCHDTMsg asisSchd = NSZC0470Query.getInstance().getAsisSchdByPePkDt(glblCmpyCd, prcEffTMsg.dsContrPrcEffPk.getValue(), invSchdMaxThruDt);
            asisSchdSqNum = Integer.parseInt(asisSchd.dsContrBllgSchdSqNum.getValue());
        } else {
            asisTopSchdSqNum = Integer.parseInt(schdSmryTMsg.dsContrBllgSchdSqNum.getValue());
        }
        int topSchdCnt = 1;
        int topSchdSqNum = asisTopSchdSqNum;
        for (CalcSchdSmryTermAndAmtForBaseBean tmpTopSchdBean : tmpTopSchdBeanList) {
            DS_CONTR_BLLG_SCHD_SMRYTMsg targetTopSchd = createContractProcess.createSchdSmryForBase(newMsgMap, linePMsg, prcEffTMsg, tmpTopSchdBean);
            if (isUpdateTopSchd && topSchdCnt == 1) {
                setValue(schdSmryTMsg.bllgSchdThruDt, tmpTopSchdBeanList.get(0).getBllgSchdThruDt());
                Map<String, BigDecimal> topSchdMap = NSZC0470Query.getInstance().getBaseTermAmtAndAdjAmt(glblCmpyCd, schdSmryTMsg.dsContrBllgSchdSmryPk.getValue());
                BigDecimal perSchdNum = schdSmryTMsg.perSchdNum.getValue();
                perSchdNum = perSchdNum.add(tmpTopSchdBean.getPerSchdNum());
                setValue(schdSmryTMsg.perSchdNum, perSchdNum);
                BigDecimal baseSubTotPrcDealAmt = (BigDecimal) topSchdMap.get("BASE_ACTL_PRC_DEAL_AMT");
                BigDecimal tmpSubTotPrcDealAmt = tmpTopSchdBean.getBaseSubTotPrcDealAmt();
                if (hasValue(tmpSubTotPrcDealAmt)) {
                    baseSubTotPrcDealAmt = baseSubTotPrcDealAmt.add(tmpSubTotPrcDealAmt);
                    setValue(schdSmryTMsg.baseSubTotPrcDealAmt, baseSubTotPrcDealAmt);
                }
                BigDecimal basePrcDealAdjAmt = (BigDecimal) topSchdMap.get("BASE_PRC_ADJ_DEAL_AMT");
                BigDecimal tmpPrcDealAdjAmt = tmpTopSchdBean.getAdjAmt();
                if (hasValue(tmpPrcDealAdjAmt)) {
                    basePrcDealAdjAmt = basePrcDealAdjAmt.add(tmpPrcDealAdjAmt);
                    setValue(schdSmryTMsg.basePrcDealAdjAmt, basePrcDealAdjAmt);
                }
                S21ApiTBLAccessor.update(schdSmryTMsg);
            } else if (!isUpdateTopSchd || topSchdCnt != 1) {
                topSchdSqNum = topSchdSqNum + 1;
            }
            tmpTopSchdBean.setDsContrBllgSchdSqNum(Integer.toString(topSchdSqNum));
            List<CalcSchdTermAndAmtLineBean> schdBeanList = createContractProcess.calcSchdTermAndAmt(newMsgMap, linePMsg, targetTopSchd);
            if (!isUpdateTopSchd || topSchdCnt != 1) {
                setValue(targetTopSchd.dsContrBllgSchdSqNum, Integer.toString(topSchdSqNum));
                S21ApiTBLAccessor.insert(targetTopSchd);
            } else {
                targetTopSchd = schdSmryTMsg;
            }
            for (CalcSchdTermAndAmtLineBean schdBean : schdBeanList) {
                DS_CONTR_BLLG_SCHDTMsg tmpSchd = createContractProcess.createSchdForBase(newMsgMap, linePMsg, prcEffTMsg, targetTopSchd, schdBean);
                if (isUpdateTopSchd && topSchdCnt == 1) {
                    setValue(tmpSchd.dsContrBllgSchdSqNum, Integer.toString(asisSchdSqNum + Integer.parseInt(schdBean.getDsContrBllgSchdSqNum())));
                }
                S21ApiTBLAccessor.insert(tmpSchd);
            }
            topSchdCnt++;
        }
        // Mod End   09/28/2016 <QC#14853>
        BigDecimal baseTermAmtRate = NSZC0470Query.getInstance().getSumBaseDealAmt(prcEffTMsg);
        setValue(prcEffTMsg.basePrcTermDealAmtRate, baseTermAmtRate);
        S21ApiTBLAccessor.update(prcEffTMsg);
    }

    private List<CalcSchdSmryTermAndAmtForBaseBean> calcSchdSmryTermAndAmt(S21ApiMessageMap msgMap, DS_CONTR_DTLTMsg dsContrDtlTMsg, DS_CONTR_BLLG_SCHD_SMRYTMsg topSchd, String fromDt, String thruDt, BigDecimal basePrcTermDealAmtRate) {
        NSZC047001PMsg pMsg = (NSZC047001PMsg) msgMap.getPmsg();

        CalcSchdSmryTermAndAmtBean inBean = new CalcSchdSmryTermAndAmtBean();
        inBean.setGlblCmpyCd(pMsg.glblCmpyCd.getValue());
        inBean.setBllgSchdFromDt(fromDt);
        inBean.setBllgSchdThruDt(thruDt);
        inBean.setBllgCycleCd(topSchd.bllgCycleCd.getValue());
        inBean.setContrCloDay(dsContrDtlTMsg.contrCloDay.getValue());
        inBean.setBasePrcDealAmt(topSchd.basePrcDealAmt.getValue());
        inBean.setBasePrcTermDealAmtRate(basePrcTermDealAmtRate);
        inBean.setBaseChrgFlg(FLG_ON_Y);
        inBean.setUsgChrgFlg(FLG_OFF_N);
        inBean.setCcyCd(topSchd.ccyCd.getValue());

        CalcSchdSmryTermAndAmtBean outBean = NSXC003001CalcSchdSmryTermAndAmt.calcSchdSmryTermAndAmt(inBean);
        return outBean.getBaseList();
    }

    private List<BigDecimal> getPrcEffPkList(List<Map<String, BigDecimal>> bllgSchdList) {

        List<BigDecimal> pkList = new ArrayList<BigDecimal>();
        for (Map<String, BigDecimal> bllgSchdInfo : bllgSchdList) {
            BigDecimal pk = bllgSchdInfo.get("DS_CONTR_PRC_EFF_PK");
            // START 2017/10/02 K.Kitachi [QC#21212, MOD]
//          if (!pkList.contains(pk)) {
            if (hasValue(pk) && !pkList.contains(pk)) {
            // END 2017/10/02 K.Kitachi [QC#21212, MOD]
                pkList.add(pk);
            }
        }
        return pkList;
    }

    private List<BigDecimal> getSchdSmryPkList(List<Map<String, BigDecimal>> bllgSchdList) {

        List<BigDecimal> pkList = new ArrayList<BigDecimal>();
        for (Map<String, BigDecimal> bllgSchdInfo : bllgSchdList) {
            BigDecimal pk = bllgSchdInfo.get("DS_CONTR_BLLG_SCHD_SMRY_PK");
            // START 2017/10/02 K.Kitachi [QC#21212, MOD]
//          if (!pkList.contains(pk)) {
            if (hasValue(pk) && !pkList.contains(pk)) {
            // END 2017/10/02 K.Kitachi [QC#21212, MOD]
                pkList.add(pk);
            }
        }
        return pkList;
    }

    private void updatePrcEffAndSchdInfoForUsageSchdAdd(S21ApiMessageMap msgMap, DS_CONTR_DTLTMsg dsContrDtlTMsg, NSZC047001PMsg mode01PMsg, String invSchdMaxThruDt) {

        NSZC047008PMsg pMsg = (NSZC047008PMsg) msgMap.getPmsg();

        String effThruDt = mode01PMsg.xxMtrLineList.no(0).effThruDt_ML.getValue();
        String baseChrgFlg = pMsg.baseChrgFlg.getValue();
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        BigDecimal dsContrDtlPk = pMsg.dsContrDtlPk.getValue();
        BigDecimal dsContrBllgMtrPk = null;
        if (pMsg.xxMtrLineList.getValidCount() > 0) {
            dsContrBllgMtrPk = pMsg.xxMtrLineList.no(0).dsContrBllgMtrPk_ML.getValue();
        }

        List<Map<String, BigDecimal>> updateBllgSchdList = NSZC0470Query.getInstance().getBllgSchdForUpdateByFromDt(glblCmpyCd, dsContrDtlPk, dsContrBllgMtrPk, invSchdMaxThruDt, baseChrgFlg);
        List<BigDecimal> prcEffPkList = getPrcEffPkList(updateBllgSchdList);
        List<BigDecimal> schdSmryPkList = getSchdSmryPkList(updateBllgSchdList);
        // START 2018/06/25 K.Kitachi [QC#22245, ADD]
        if (prcEffPkList.size() == 0 || schdSmryPkList.size() == 0) {
            return;
        }
        // END 2018/06/25 K.Kitachi [QC#22245, ADD]

        DS_CONTR_PRC_EFFTMsg prcEffTMsg = null;
        for (BigDecimal prcEffPk : prcEffPkList) {

            DS_CONTR_PRC_EFFTMsg tmpPrcEffTMsg = NSZC0470Query.getInstance().getDsContrPrcEffTMsg(glblCmpyCd, prcEffPk);
            if (tmpPrcEffTMsg.contrPrcEffFromDt.getValue().compareTo(invSchdMaxThruDt) <= 0 && invSchdMaxThruDt.compareTo(tmpPrcEffTMsg.contrPrcEffThruDt.getValue()) <= 0) {
                prcEffTMsg = tmpPrcEffTMsg;
                break;
            }
        }
        // START 2019/11/15 K.Kitachi [QC#54021, ADD]
        if (prcEffTMsg == null) {
            return;
        }
        // END 2019/11/15 K.Kitachi [QC#54021, ADD]
        setValue(prcEffTMsg.contrPrcEffThruDt, effThruDt);

        DS_CONTR_BLLG_SCHD_SMRYTMsg schdSmryTMsg = null;
        
        for (BigDecimal schdSmryPk : schdSmryPkList) {
            DS_CONTR_BLLG_SCHD_SMRYTMsg tmpShcdSmryTMsg = NSZC0470Query.getInstance().getDsContrBllgSchdSmryTMsg(glblCmpyCd, schdSmryPk);
            if (tmpShcdSmryTMsg.bllgSchdFromDt.getValue().compareTo(invSchdMaxThruDt) <= 0 && invSchdMaxThruDt.compareTo(tmpShcdSmryTMsg.bllgSchdThruDt.getValue()) <= 0) {
                schdSmryTMsg = tmpShcdSmryTMsg;
                break;
            }
        }
        // START 2019/11/15 K.Kitachi [QC#54021, ADD]
        if (schdSmryTMsg == null) {
            return;
        }
        // END 2019/11/15 K.Kitachi [QC#54021, ADD]

        setValue(mode01PMsg.xxRqstFlg, FLG_OFF_N);
        S21ApiMessageMap newMsgMap = new S21ApiMessageMap(mode01PMsg);
        M01CreateContractProcess createContractProcess = new M01CreateContractProcess(newMsgMap);
        NSZC047001_xxMtrLineListPMsg linePMsg = mode01PMsg.xxMtrLineList.no(0);

        String tmpFromDt = ZYPDateUtil.addDays(invSchdMaxThruDt, 1);
        CalcSchdSmryTermAndAmtBean inBean = new CalcSchdSmryTermAndAmtBean();
        inBean.setGlblCmpyCd(pMsg.glblCmpyCd.getValue());
        inBean.setBllgSchdFromDt(tmpFromDt);
        inBean.setBllgSchdThruDt(effThruDt);
        inBean.setBllgCycleCd(schdSmryTMsg.bllgCycleCd.getValue());
        inBean.setContrCloDay(dsContrDtlTMsg.mtrCloDay.getValue());
        inBean.setBaseChrgFlg(FLG_OFF_N);
        inBean.setUsgChrgFlg(FLG_ON_Y);
        inBean.setCcyCd(schdSmryTMsg.ccyCd.getValue());

        CalcSchdSmryTermAndAmtBean outBean = NSXC003001CalcSchdSmryTermAndAmt.calcSchdSmryTermAndAmt(inBean);
        List<CalcSchdSmryTermAndAmtForUsageBean> usageBeanList = outBean.getUsageList();

        boolean isUpdateTopSchd = false;
        int asisTopSchdSqNum = Integer.parseInt(schdSmryTMsg.dsContrBllgSchdSqNum.getValue());
        int asisSchdSqNum = 0; 
        if (!BLLG_CYCLE.DAILY.equals(schdSmryTMsg.perBllgCycleCd.getValue()) && schdSmryTMsg.perBllgCycleCd.getValue().equals(usageBeanList.get(0).getPerBllgCycleCd())) {
            isUpdateTopSchd = true;
            DS_CONTR_BLLG_SCHDTMsg asisSchd = NSZC0470Query.getInstance().getAsisSchdByPePkDt(glblCmpyCd, prcEffTMsg.dsContrPrcEffPk.getValue(), invSchdMaxThruDt);
            asisSchdSqNum = Integer.parseInt(asisSchd.dsContrBllgSchdSqNum.getValue());
        }
        int topSchdCnt = 1;
        // Mod Start   09/28/2016 <QC#14853>
        int topSchdSqNum = asisTopSchdSqNum;
        for (CalcSchdSmryTermAndAmtForUsageBean tmpTopSchdBean : usageBeanList) {
            List<NSZC047001_xxMtrLineListPMsg> linePMsgList = setLinePmsgListByPe(dsContrDtlTMsg, tmpTopSchdBean.getBllgSchdFromDt(), pMsg, tmpTopSchdBean.getBllgSchdThruDt(), prcEffTMsg);
            CalcSchdSmryTermAndAmtBean allowanceBean = createContractProcess.calcAllowance(newMsgMap, tmpTopSchdBean, linePMsgList);
            DS_CONTR_BLLG_SCHD_SMRYTMsg tmpTopSchd = createContractProcess.createSchdSmryForUsage(newMsgMap, linePMsg, prcEffTMsg, tmpTopSchdBean, allowanceBean);
            if (isUpdateTopSchd && topSchdCnt == 1) {
                setValue(schdSmryTMsg.bllgSchdThruDt, tmpTopSchd.bllgSchdThruDt);
                BigDecimal perSchdNum = schdSmryTMsg.perSchdNum.getValue();
                perSchdNum = perSchdNum.add(tmpTopSchdBean.getPerSchdNum());
                setValue(schdSmryTMsg.perSchdNum, perSchdNum);
                S21ApiTBLAccessor.update(schdSmryTMsg);
                topSchdSqNum = asisTopSchdSqNum;
                setValue(tmpTopSchd.dsContrBllgSchdSqNum, schdSmryTMsg.dsContrBllgSchdSqNum);
                setValue(tmpTopSchd.dsContrBllgSchdSmryPk, schdSmryTMsg.dsContrBllgSchdSmryPk);
            } else if (!isUpdateTopSchd || topSchdCnt != 1) {
                topSchdSqNum = topSchdSqNum + 1;
                setValue(tmpTopSchd.dsContrBllgSchdSqNum, Integer.toString(topSchdSqNum));
                S21ApiTBLAccessor.insert(tmpTopSchd);
                insertTopSchdMtr(newMsgMap, createContractProcess, allowanceBean, tmpTopSchd);
            }
            tmpTopSchdBean.setDsContrBllgSchdSqNum(Integer.toString(topSchdSqNum));
            List<CalcSchdTermAndAmtLineBean> schdBeanList = createContractProcess.calcSchdTermAndAmt(newMsgMap, linePMsg, tmpTopSchd);
            for (CalcSchdTermAndAmtLineBean schdBean : schdBeanList) {
                DS_CONTR_BLLG_SCHDTMsg tmpSchd = createContractProcess.createSchdForUsage(newMsgMap, linePMsg, prcEffTMsg, tmpTopSchd, schdBean);
                if (isUpdateTopSchd && topSchdCnt == 1) {
                    setValue(tmpSchd.dsContrBllgSchdSqNum, Integer.toString(asisSchdSqNum + Integer.parseInt(schdBean.getDsContrBllgSchdSqNum())));
                }
                S21ApiTBLAccessor.insert(tmpSchd);
                List<Map<String, Object>> fleetMachList = null;
                if (DS_CONTR_DTL_TP.FLEET.equals(dsContrDtlTMsg.dsContrDtlTpCd.getValue())) {
                    fleetMachList = NSZC0470Query.getInstance().getFleetMachContrInfo(pMsg.glblCmpyCd.getValue(), pMsg.dsContrDtlPk.getValue(), tmpSchd.bllgSchdThruDt.getValue(), tmpSchd.bllgSchdFromDt.getValue(),
                            tmpSchd.bllgSchdFromDt.getValue(), dsContrBllgMtrPk);
                    for (Map<String, Object> fleetMachInfo : fleetMachList) {
                        createContractProcess.createSchdForFleetMachUsage(newMsgMap, linePMsg, prcEffTMsg, tmpTopSchd, tmpSchd, schdBean, fleetMachInfo);
                    }
                }
            }
            topSchdCnt++;
        }
        // Mod End   09/28/2016 <QC#14853>
        BigDecimal baseTermAmtRate = NSZC0470Query.getInstance().getSumBaseDealAmt(prcEffTMsg);
        setValue(prcEffTMsg.basePrcTermDealAmtRate, baseTermAmtRate);
        S21ApiTBLAccessor.update(prcEffTMsg);
    }

    private List<NSZC047001_xxMtrLineListPMsg> setLinePmsgListByPe(DS_CONTR_DTLTMsg dsContrDtlTMsg, String effFromDt, NSZC047008PMsg pMsg, String newThruDt, DS_CONTR_PRC_EFFTMsg prcEffTMsg) {
        List<NSZC047001_xxMtrLineListPMsg> asIsLinePMsgList = new ArrayList<NSZC047001_xxMtrLineListPMsg>();
        List<Map<String, Object>> asisPeMtrList = NSZC0470Query.getInstance().getDsContrPrcEffMtr(prcEffTMsg.glblCmpyCd.getValue(), prcEffTMsg.dsContrPrcEffPk.getValue());
        BigDecimal dsContrBllgMtrPk = pMsg.xxMtrLineList.no(0).dsContrBllgMtrPk_ML.getValue();
        DS_CONTR_BLLG_MTRTMsg bllgMtr = NSZC0470Query.getInstance().getBllgMtrTMsg(pMsg.glblCmpyCd.getValue(), dsContrBllgMtrPk);
        for (Map<String, Object> asisPeMtr : asisPeMtrList) {
            NSZC047001_xxMtrLineListPMsg mtr01Line = new NSZC047001_xxMtrLineListPMsg();
            setValue(mtr01Line.dsContrPrcEffSqNum_ML, prcEffTMsg.dsContrPrcEffSqNum.getValue());
            setValue(mtr01Line.effFromDt_ML, effFromDt);
            setValue(mtr01Line.effThruDt_ML, newThruDt);
            setValue(mtr01Line.mtrBllgCycleCd_ML, bllgMtr.bllgMtrBllgCycleCd);
            setValue(mtr01Line.dsContrBllgMtrPk_ML, prcEffTMsg.dsContrBllgMtrPk);
            setValue(mtr01Line.contrXsCopyPk_ML, (BigDecimal) asisPeMtr.get("CONTR_XS_COPY_PK"));
            setValue(mtr01Line.xsMtrCopyQty_ML, (BigDecimal) asisPeMtr.get("XS_MTR_COPY_QTY"));
            setValue(mtr01Line.xsMtrAmtRate_ML, (BigDecimal) asisPeMtr.get("XS_MTR_AMT_RATE"));
            setValue(mtr01Line.xsMtrFirstFlg_ML, (String) asisPeMtr.get("XS_MTR_FIRST_FLG"));
            setValue(mtr01Line.dsContrPrcEffStsCd_ML, prcEffTMsg.dsContrPrcEffStsCd.getValue());
            setValue(mtr01Line.qltyAsrnHldFlg_ML, prcEffTMsg.qltyAsrnHldFlg.getValue());
            setValue(mtr01Line.mtrHldFlg_ML, prcEffTMsg.mtrHldFlg.getValue());
            setValue(mtr01Line.contrHldFlg_ML, prcEffTMsg.contrHldFlg.getValue());
            setValue(mtr01Line.bllgHldFlg_ML, prcEffTMsg.bllgHldFlg.getValue());
            setValue(mtr01Line.qltyAsrnHldPendApvlFlg_ML, prcEffTMsg.qltyAsrnHldPendApvlFlg.getValue());
            setValue(mtr01Line.dsContrPrcEffPk_ML, prcEffTMsg.dsContrPrcEffPk.getValue());
            asIsLinePMsgList.add(mtr01Line);
        }
        return asIsLinePMsgList;
    }

    private void insertTopSchdMtr(S21ApiMessageMap newMsgMap, M01CreateContractProcess createContractProcess, CalcSchdSmryTermAndAmtBean allowanceBean, DS_CONTR_BLLG_SCHD_SMRYTMsg tobeTopSchd) {
        // ----------------------------------------
        // Insert Schedule Meter
        // ----------------------------------------
        List<CalcSchdSmryTermAndAmtForUsageBean> allowBeanList = allowanceBean.getUsageList();
        for (CalcSchdSmryTermAndAmtForUsageBean allowBean : allowBeanList) {
            createContractProcess.createSchdMtr(newMsgMap, tobeTopSchd, allowBean);
        }
    }
    // Add End   08/02/2016 <QC#7402>

}
