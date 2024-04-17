package com.canon.cusa.s21.api.NSZ.NSZC047001;

import static com.canon.cusa.s21.api.NSZ.NSZC047001.constant.NSZC047001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.BLLG_CYCLETMsg;
import business.db.DS_CONTRTMsg;
import business.db.DS_CONTR_BLLG_MTRTMsg;
import business.db.DS_CONTR_BLLG_SCHDTMsg;
import business.db.DS_CONTR_BLLG_SCHD_SMRYTMsg;
import business.db.DS_CONTR_DTLTMsg;
import business.db.DS_CONTR_PRC_EFFTMsg;
import business.parts.NSZC047001PMsg;
import business.parts.NSZC047001_xxBaseLineListPMsg;
import business.parts.NSZC047001_xxMtrLineListPMsg;
import business.parts.NSZC047005PMsg;

import com.canon.cusa.s21.common.NSX.NSXC003001.CalcSchdSmryTermAndAmtBean;
import com.canon.cusa.s21.common.NSX.NSXC003001.CalcSchdSmryTermAndAmtForBaseBean;
import com.canon.cusa.s21.common.NSX.NSXC003001.CalcSchdSmryTermAndAmtForUsageBean;
import com.canon.cusa.s21.common.NSX.NSXC003001.CalcSchdTermAndAmtLineBean;
import com.canon.cusa.s21.common.NSX.NSXC003001.NSXC003001CalcSchdSmryTermAndAmt;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_EDI;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;

/**
 * <pre>
 * Contract Billing Schedule API
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/14/2015   Hitachi         T.Aoyagi        Create          N/A
 * 01/21/2016   Hitachi         K.Kishimoto     Update          QC#3331
 * 06/29/2016   Hitachi         K.Kishimoto     Update          QC#7428,QC#7429
 * 2017/06/30   Hitachi         K.Kitachi       Update          QC#18288
 * 10/03/2017   Hitachi         E.Kameishi      Update          QC#18636
 * 2017/10/30   Hitachi         K.Kitachi       Update          QC#21449
 * 2017/11/01   Hitachi         K.Kojima        Update          QC#21859
 * 2017/12/05   Hitachi         K.Kojima        Update          QC#21595
 * 2019/03/05   Hitachi         K.Kitachi       Update          QC#30619
 * </pre>
 */
public class M05AnnualEscalationProcess implements ZYPConstant {

    /** ONBATCH_TYPE */
    private ONBATCH_TYPE onBatchTp;

    protected void doProcess(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {

        this.onBatchTp = onBatchType;
        checkParameter(msgMap);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }
        annualEscalationProcess(msgMap);
    }

    private void annualEscalationProcess(S21ApiMessageMap msgMap) {

        NSZC047005PMsg pMsg = (NSZC047005PMsg) msgMap.getPmsg();
        String baseChrgFlg = pMsg.baseChrgFlg.getValue();
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        BigDecimal dsContrDtlPk = pMsg.dsContrDtlPk.getValue();
        BigDecimal dsContrBllgMtrPk = null;
        if (pMsg.xxMtrLineList.getValidCount() > 0) {
            dsContrBllgMtrPk = pMsg.xxMtrLineList.no(0).dsContrBllgMtrPk_ML.getValue();
        }
        String effFromDt = pMsg.effFromDt.getValue();

        DS_CONTR_DTLTMsg dsContrDtlTMsg = NSZC0470Query.getInstance().getDsContrDtlTMsg(glblCmpyCd, dsContrDtlPk);
        String dsContrDtlTpCd = dsContrDtlTMsg.dsContrDtlTpCd.getValue();

        // START 2017/12/05 K.Kojima [QC#21595,ADD]
        DS_CONTRTMsg dsContrTMsg = NSZC0470Query.getInstance().getDsContrTMsg(glblCmpyCd, dsContrDtlTMsg.dsContrPk.getValue());
        // END 2017/12/05 K.Kojima [QC#21595,ADD]

        deletePrcEffAndSchdInfo(msgMap, dsContrDtlTpCd);

        Map<String, Object> prcEffMap = NSZC0470Query.getInstance().getLtstPrcEffSqNum(glblCmpyCd, dsContrDtlPk, dsContrBllgMtrPk, baseChrgFlg);
        BigDecimal seqNum = BigDecimal.ZERO;
        BigDecimal prcEffPk = null;
        // START 2017/11/01 K.Kojima [QC#21859,ADD]
        String contrPrcEffFromDt = null;
        // END 2017/11/01 K.Kojima [QC#21859,ADD]
        if (prcEffMap != null) {
            seqNum = (BigDecimal) prcEffMap.get("DS_CONTR_PRC_EFF_SQ_NUM");
            prcEffPk = (BigDecimal) prcEffMap.get("DS_CONTR_PRC_EFF_PK");
            // START 2017/11/01 K.Kojima [QC#21859,ADD]
            contrPrcEffFromDt = (String) prcEffMap.get("CONTR_PRC_EFF_FROM_DT");
            // END 2017/11/01 K.Kojima [QC#21859,ADD]
        }
        seqNum = seqNum.add(BigDecimal.ONE);

        String maxBllgSchdThruDt = NSZC0470Query.getInstance().getMaxBllgSchdThruDt(glblCmpyCd, dsContrDtlPk, dsContrBllgMtrPk, baseChrgFlg);
        if (hasValue(maxBllgSchdThruDt)) {

            if (maxBllgSchdThruDt.equals(ZYPDateUtil.addDays(effFromDt, -1))) {
                updatePrcEffAndSchdInfo(msgMap, effFromDt);
            } else {
                // Mod Start 06/29/2016 <QC#7428,QC#7429>
                if (FLG_ON_Y.equals(baseChrgFlg)) {
                    // START 2017/11/01 K.Kojima [QC#21859,MOD]
                    // updatePrcEffAndSchdInfoForBaseSchdAdd(msgMap, dsContrDtlTMsg, effFromDt);
                    BigDecimal unScheduleBasePrice = NSZC0470Query.getInstance().getUnScheduleBasePrice(glblCmpyCd, prcEffPk, effFromDt);
                    // START 2017/12/05 K.Kojima [QC#21595,MOD]
                    // updatePrcEffAndSchdInfoForBaseSchdAdd(msgMap, dsContrDtlTMsg, effFromDt, contrPrcEffFromDt, unScheduleBasePrice);
                    updatePrcEffAndSchdInfoForBaseSchdAdd(msgMap, dsContrDtlTMsg, effFromDt, contrPrcEffFromDt, unScheduleBasePrice, dsContrTMsg);
                    // END 2017/12/05 K.Kojima [QC#21595,MOD]
                    // END 2017/11/01 K.Kojima [QC#21859,MOD]
                } else {
                    // START 2017/12/05 K.Kojima [QC#21595,MOD]
                    // updatePrcEffAndSchdInfoForUsageSchdAdd(msgMap, dsContrDtlTMsg, effFromDt);
                    updatePrcEffAndSchdInfoForUsageSchdAdd(msgMap, dsContrDtlTMsg, effFromDt, dsContrTMsg);
                    // END 2017/12/05 K.Kojima [QC#21595,MOD]
                }
                // Mod End   06/29/2016 <QC#7428,QC#7429>
            }
        }

        // Mod Start 06/29/2016 <QC#7428,QC#7429>
        // START 2017/12/05 K.Kojima [QC#21595,MOD]
        // createPrcEffAndSchdInfo(msgMap, dsContrDtlTMsg, null, null, prcEffPk, seqNum);
        createPrcEffAndSchdInfo(msgMap, dsContrDtlTMsg, null, null, prcEffPk, seqNum, dsContrTMsg);
        // END 2017/12/05 K.Kojima [QC#21595,MOD]
        // Mod End   06/29/2016 <QC#7428,QC#7429>

        // START 2017/06/30 K.Kitachi [QC#18288, ADD]
        NSZC047001CommonLogic.updateUpliftInfo(glblCmpyCd, pMsg.slsDt.getValue(), dsContrDtlPk);
        // END 2017/06/30 K.Kitachi [QC#18288, ADD]

        // START 2019/03/05 K.Kitachi [QC#30619, ADD]
        NSZC047001CommonLogic.resetAccSchdPrntInfo(msgMap, glblCmpyCd, dsContrDtlPk);
        // END 2019/03/05 K.Kitachi [QC#30619, ADD]
    }

    private void checkParameter(S21ApiMessageMap msgMap) {

        NSZC047005PMsg pMsg = (NSZC047005PMsg) msgMap.getPmsg();
        NSZC047001CommonLogic.mandatoryCheck(msgMap, pMsg.glblCmpyCd, ZZZM9007E, new String[]{"Global Company Code"});
        NSZC047001CommonLogic.mandatoryCheck(msgMap, pMsg.slsDt, ZZZM9007E, new String[]{"Salse Date"});
        NSZC047001CommonLogic.mandatoryCheck(msgMap, pMsg.dsContrDtlPk, ZZZM9007E, new String[]{"DS Contract Detail PK"});
        NSZC047001CommonLogic.mandatoryCheck(msgMap, pMsg.baseChrgFlg, ZZZM9007E, new String[]{"Base Charge Flag"});
        NSZC047001CommonLogic.mandatoryCheck(msgMap, pMsg.usgChrgFlg, ZZZM9007E, new String[]{"Usage Charge Flag"});
        NSZC047001CommonLogic.mandatoryCheck(msgMap, pMsg.effFromDt, ZZZM9007E, new String[]{"Effective From Date"});
        NSZC047001CommonLogic.mandatoryCheck(msgMap, pMsg.effThruDt, ZZZM9007E, new String[]{"Effective Thru Date"});
        if (FLG_ON_Y.equals(pMsg.baseChrgFlg.getValue())) {
            NSZC047001CommonLogic.mandatoryCheck(msgMap, pMsg.basePrcDealAmt, ZZZM9007E, new String[]{"Base Price Deal Amount"});
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

    private void deletePrcEffAndSchdInfo(S21ApiMessageMap msgMap, String dsContrDtlTp) {

        NSZC047005PMsg pMsg = (NSZC047005PMsg) msgMap.getPmsg();

        String baseChrgFlg = pMsg.baseChrgFlg.getValue();
        String usgChrgFlg = pMsg.usgChrgFlg.getValue();
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        BigDecimal dsContrDtlPk = pMsg.dsContrDtlPk.getValue();
        BigDecimal dsContrBllgMtrPk = null;
        if (pMsg.xxMtrLineList.getValidCount() > 0) {
            dsContrBllgMtrPk = pMsg.xxMtrLineList.no(0).dsContrBllgMtrPk_ML.getValue();
        }
        String effFromDt = pMsg.effFromDt.getValue();

        List<Map<String, BigDecimal>> deleteBllgSchdList = NSZC0470Query.getInstance().getBllgSchdForDeleteByFromDt(glblCmpyCd, dsContrDtlPk, dsContrBllgMtrPk, effFromDt, baseChrgFlg);
//        List<Map<String, BigDecimal>> deleteBllgSchdList = NSZC0470Query.getInstance().getBllgSchdForDelete(glblCmpyCd, dsContrDtlPk, dsContrBllgMtrPk, effFromDt, baseChrgFlg);
        List<BigDecimal> prcEffPkList = getPrcEffPkList(deleteBllgSchdList);
        List<BigDecimal> schdSmryPkList = getSchdSmryPkList(deleteBllgSchdList);
        List<BigDecimal> schdPkList = getSchdPkList(deleteBllgSchdList);

        // Add Start 01/21/2016 <QC#3331>
        for (BigDecimal schdPk : schdPkList) {
            NSZC047001CommonLogic.deleteSvcContrBllgInfo(msgMap, glblCmpyCd, schdPk);
        }
        // Add Start 01/21/2016 <QC#3331>
        NSZC0470Query.getInstance().removeSchd(glblCmpyCd, schdPkList);
        // START 2017/10/03 E.Kameishi [QC18636, ADD]
        NSZC0470Query.getInstance().removeSchdTestMtrSmry(glblCmpyCd, schdPkList);
        // END 2017/10/03 E.Kameishi [QC18636, ADD]
        if (FLG_ON_Y.equals(usgChrgFlg) && DS_CONTR_DTL_TP.FLEET.equals(dsContrDtlTp)) {
            for (BigDecimal prntSchdPk : schdPkList) {

                List<BigDecimal> prntSchdPkList = NSZC0470Query.getInstance().getDeleteChildSchdList(glblCmpyCd, prntSchdPk);
                // START 2017/10/30 K.Kitachi [QC#21449, ADD]
                NSZC047001CommonLogic.deleteSvcContrBllgInfo(msgMap, glblCmpyCd, prntSchdPkList);
                // END 2017/10/30 K.Kitachi [QC#21449, ADD]
                NSZC0470Query.getInstance().removeSchd(glblCmpyCd, prntSchdPkList);
            }
        }

        for (BigDecimal schdSmryPk : schdSmryPkList) {

            DS_CONTR_BLLG_SCHD_SMRYTMsg shcdSmryTMsg = NSZC0470Query.getInstance().getDsContrBllgSchdSmryTMsg(glblCmpyCd, schdSmryPk);
            if (effFromDt.compareTo(shcdSmryTMsg.bllgSchdFromDt.getValue()) <= 0) {
                NSZC0470Query.getInstance().removeSchdSmry(glblCmpyCd, schdSmryPk);

                if (FLG_ON_Y.equals(usgChrgFlg)) {
                    List<BigDecimal> schdMtrPkList = NSZC0470Query.getInstance().getDsContrBllgSchdMtrForCancel(glblCmpyCd, schdSmryPk);
                    NSZC0470Query.getInstance().removeSchdMtr(glblCmpyCd, schdMtrPkList);
                }
            }
        }

        for (BigDecimal prcEffPk : prcEffPkList) {

            DS_CONTR_PRC_EFFTMsg prcEffTMsg = NSZC0470Query.getInstance().getDsContrPrcEffTMsg(glblCmpyCd, prcEffPk);
            if (effFromDt.compareTo(prcEffTMsg.contrPrcEffFromDt.getValue()) <= 0) {
                NSZC0470Query.getInstance().removePrcEff(glblCmpyCd, prcEffPk);

                if (FLG_ON_Y.equals(usgChrgFlg)) {
                    List<BigDecimal> prcEffMtrPkList = NSZC0470Query.getInstance().getDeletePrcEffMtrList(glblCmpyCd, prcEffPk);
                    NSZC0470Query.getInstance().removePrcEffMtr(glblCmpyCd, prcEffMtrPkList);
                }
            }
        }
    }

    private void updatePrcEffAndSchdInfo(S21ApiMessageMap msgMap, String effFromDt) {

        NSZC047005PMsg pMsg = (NSZC047005PMsg) msgMap.getPmsg();

        String baseChrgFlg = pMsg.baseChrgFlg.getValue();
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        BigDecimal dsContrDtlPk = pMsg.dsContrDtlPk.getValue();
        BigDecimal dsContrBllgMtrPk = null;
        if (pMsg.xxMtrLineList.getValidCount() > 0) {
            dsContrBllgMtrPk = pMsg.xxMtrLineList.no(0).dsContrBllgMtrPk_ML.getValue();
        }

        List<Map<String, BigDecimal>> updateBllgSchdList = NSZC0470Query.getInstance().getBllgSchdForUpdate(glblCmpyCd, dsContrDtlPk, dsContrBllgMtrPk, effFromDt, baseChrgFlg);
        List<BigDecimal> prcEffPkList = getPrcEffPkList(updateBllgSchdList);
        List<BigDecimal> schdSmryPkList = getSchdSmryPkList(updateBllgSchdList);

        for (BigDecimal schdSmryPk : schdSmryPkList) {

            DS_CONTR_BLLG_SCHD_SMRYTMsg shcdSmryTMsg = NSZC0470Query.getInstance().getDsContrBllgSchdSmryTMsg(glblCmpyCd, schdSmryPk);
            if (shcdSmryTMsg.bllgSchdFromDt.getValue().compareTo(effFromDt) <= 0 && effFromDt.compareTo(shcdSmryTMsg.bllgSchdThruDt.getValue()) <= 0) {
                updateSchdSmry(msgMap, shcdSmryTMsg, effFromDt, baseChrgFlg);
                break;
            }
        }

        for (BigDecimal prcEffPk : prcEffPkList) {

            DS_CONTR_PRC_EFFTMsg prcEffTMsg = NSZC0470Query.getInstance().getDsContrPrcEffTMsg(glblCmpyCd, prcEffPk);
            if (prcEffTMsg.contrPrcEffFromDt.getValue().compareTo(effFromDt) <= 0 && effFromDt.compareTo(prcEffTMsg.contrPrcEffThruDt.getValue()) <= 0) {
                updatePrcEff(msgMap, prcEffTMsg, effFromDt, baseChrgFlg);
                break;
            }
        }
    }

    // Add Start 06/29/2016 <QC#7428,QC#7429>
    // START 2017/11/01 K.Kojima [QC#21859,MOD]
    // private void updatePrcEffAndSchdInfoForBaseSchdAdd(S21ApiMessageMap msgMap, DS_CONTR_DTLTMsg dsContrDtlTMsg, String effFromDt) {
    // START 2017/12/05 K.Kojima [QC#21595,MOD]
    // private void updatePrcEffAndSchdInfoForBaseSchdAdd(S21ApiMessageMap msgMap, DS_CONTR_DTLTMsg dsContrDtlTMsg, String effFromDt, String contrPrcEffFromDt, BigDecimal unScheduleBasePrice) {
    private void updatePrcEffAndSchdInfoForBaseSchdAdd(S21ApiMessageMap msgMap, DS_CONTR_DTLTMsg dsContrDtlTMsg, String effFromDt, String contrPrcEffFromDt, BigDecimal unScheduleBasePrice, DS_CONTRTMsg dsContrTMsg) {
    // END 2017/12/05 K.Kojima [QC#21595,MOD]
    // END 2017/11/01 K.Kojima [QC#21859,MOD]

        NSZC047005PMsg pMsg = (NSZC047005PMsg) msgMap.getPmsg();

        String baseChrgFlg = pMsg.baseChrgFlg.getValue();
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        BigDecimal dsContrDtlPk = pMsg.dsContrDtlPk.getValue();
        String newThruDt = ZYPDateUtil.addDays(effFromDt, -1);

        List<Map<String, BigDecimal>> updateBllgSchdList = NSZC0470Query.getInstance().getBllgSchdForUpdateByFromDt(glblCmpyCd, dsContrDtlPk, null, effFromDt, baseChrgFlg);
        List<BigDecimal> prcEffPkList = getPrcEffPkList(updateBllgSchdList);
        List<BigDecimal> schdSmryPkList = getSchdSmryPkList(updateBllgSchdList);

        DS_CONTR_PRC_EFFTMsg prcEffTMsg = null;
        for (BigDecimal prcEffPk : prcEffPkList) {

            DS_CONTR_PRC_EFFTMsg tmpPrcEffTMsg = NSZC0470Query.getInstance().getDsContrPrcEffTMsg(glblCmpyCd, prcEffPk);
            if (tmpPrcEffTMsg.contrPrcEffFromDt.getValue().compareTo(effFromDt) <= 0 && effFromDt.compareTo(tmpPrcEffTMsg.contrPrcEffThruDt.getValue()) <= 0) {
                prcEffTMsg = tmpPrcEffTMsg;
                break;
            }
        }
        setValue(prcEffTMsg.contrPrcEffThruDt, newThruDt);

        DS_CONTR_BLLG_SCHD_SMRYTMsg shcdSmryTMsg = null;
        
        for (BigDecimal schdSmryPk : schdSmryPkList) {
            DS_CONTR_BLLG_SCHD_SMRYTMsg tmpShcdSmryTMsg = NSZC0470Query.getInstance().getDsContrBllgSchdSmryTMsg(glblCmpyCd, schdSmryPk);
            if (tmpShcdSmryTMsg.bllgSchdFromDt.getValue().compareTo(effFromDt) <= 0 && effFromDt.compareTo(tmpShcdSmryTMsg.bllgSchdThruDt.getValue()) <= 0) {
                shcdSmryTMsg = tmpShcdSmryTMsg;
                break;
            }
        }

        // START 2017/12/05 K.Kojima [QC#21595,MOD]
        // NSZC047001PMsg mode01PMsg = getMode01Pmsg(msgMap, dsContrDtlTMsg, pMsg.effFromDt.getValue(), pMsg.effThruDt.getValue(), prcEffTMsg.dsContrPrcEffPk.getValue(), prcEffTMsg.dsContrPrcEffSqNum.getValue());
        NSZC047001PMsg mode01PMsg = getMode01Pmsg(msgMap, dsContrDtlTMsg, pMsg.effFromDt.getValue(), pMsg.effThruDt.getValue(), prcEffTMsg.dsContrPrcEffPk.getValue(), prcEffTMsg.dsContrPrcEffSqNum.getValue(), dsContrTMsg);
        // END 2017/12/05 K.Kojima [QC#21595,MOD]
        setValue(mode01PMsg.xxRqstFlg, FLG_OFF_N);
        S21ApiMessageMap newMsgMap = new S21ApiMessageMap(mode01PMsg);
        M01CreateContractProcess createContractProcess = new M01CreateContractProcess(newMsgMap);
        NSZC047001_xxBaseLineListPMsg linePMsg = mode01PMsg.xxBaseLineList.no(0);

        // START 2017/11/01 K.Kojima [QC#21859,MOD]
        // List<CalcSchdSmryTermAndAmtForBaseBean> tmpTopSchdBeanList = calcSchdSmryTermAndAmt(msgMap, dsContrDtlTMsg, shcdSmryTMsg, newThruDt);
        List<CalcSchdSmryTermAndAmtForBaseBean> tmpTopSchdBeanList = calcSchdSmryTermAndAmt(msgMap, dsContrDtlTMsg, shcdSmryTMsg, newThruDt, contrPrcEffFromDt, unScheduleBasePrice);
        // END 2017/11/01 K.Kojima [QC#21859,MOD]
        if (tmpTopSchdBeanList.size() == 1) {
            CalcSchdSmryTermAndAmtForBaseBean perSchdSmryBean = tmpTopSchdBeanList.get(tmpTopSchdBeanList.size() - 1);
            setValue(shcdSmryTMsg.bllgSchdThruDt, newThruDt);
            setValue(shcdSmryTMsg.perSchdNum, perSchdSmryBean.getPerSchdNum());
            setValue(shcdSmryTMsg.baseSubTotPrcDealAmt, perSchdSmryBean.getBaseSubTotPrcDealAmt());
            setValue(shcdSmryTMsg.basePrcDealAdjAmt, perSchdSmryBean.getAdjAmt());
            List<CalcSchdTermAndAmtLineBean> schdBeanList = createContractProcess.calcSchdTermAndAmt(newMsgMap, linePMsg, shcdSmryTMsg);
            CalcSchdTermAndAmtLineBean schdBean = schdBeanList.get(schdBeanList.size()-1);
            DS_CONTR_BLLG_SCHDTMsg tmpSchd = createContractProcess.createSchdForBase(newMsgMap, linePMsg, prcEffTMsg, shcdSmryTMsg, schdBean);
            DS_CONTR_BLLG_SCHDTMsg asisSchd = NSZC0470Query.getInstance().getAsisSchdByPePkDt(glblCmpyCd, prcEffTMsg.dsContrPrcEffPk.getValue(), tmpSchd.bllgSchdFromDt.getValue());
            if (asisSchd == null) {
                S21ApiTBLAccessor.insert(tmpSchd);
            } else {
                setValue(asisSchd.skipRecovTpCd, tmpSchd.skipRecovTpCd);
                setValue(asisSchd.nextBllgDt, tmpSchd.nextBllgDt);
                setValue(asisSchd.bllgSchdThruDt, tmpSchd.bllgSchdThruDt);
                setValue(asisSchd.bllgSchdPrrtFlg, tmpSchd.bllgSchdPrrtFlg);
                setValue(asisSchd.basePrcDealAmt, tmpSchd.basePrcDealAmt);
                setValue(asisSchd.baseActlPrcDealAmt, tmpSchd.baseActlPrcDealAmt);
                setValue(asisSchd.basePrcAdjDealAmt, tmpSchd.basePrcAdjDealAmt);
                setValue(asisSchd.dsContrTrmnFlg, tmpSchd.dsContrTrmnFlg);
                setValue(asisSchd.bllblFlg, tmpSchd.bllblFlg);
                setValue(asisSchd.rvsSchdDt, tmpSchd.rvsSchdDt);
                setValue(asisSchd.bllgPerMthAot, tmpSchd.bllgPerMthAot);
                S21ApiTBLAccessor.update(asisSchd);
            }
            Map<String, BigDecimal> topSchdMap = NSZC0470Query.getInstance().getBaseTermAmtAndAdjAmt(glblCmpyCd, shcdSmryTMsg.dsContrBllgSchdSmryPk.getValue());
            if (topSchdMap != null) {
                setValue(shcdSmryTMsg.baseSubTotPrcDealAmt, (BigDecimal) topSchdMap.get("BASE_ACTL_PRC_DEAL_AMT"));
                setValue(shcdSmryTMsg.basePrcDealAdjAmt, (BigDecimal) topSchdMap.get("BASE_PRC_ADJ_DEAL_AMT"));
            }
            S21ApiTBLAccessor.update(shcdSmryTMsg);
        } else {
            CalcSchdSmryTermAndAmtForBaseBean asisSchdSmryBean = tmpTopSchdBeanList.get(tmpTopSchdBeanList.size() - 2);
            CalcSchdSmryTermAndAmtForBaseBean perSchdSmryBean = tmpTopSchdBeanList.get(tmpTopSchdBeanList.size() - 1);
            int topSchdNum = Integer.parseInt(shcdSmryTMsg.dsContrBllgSchdSqNum.getValue());
            topSchdNum++;
            perSchdSmryBean.setDsContrBllgSchdSqNum(Integer.toString(topSchdNum));
            DS_CONTR_BLLG_SCHD_SMRYTMsg insTopSchd = createContractProcess.createSchdSmryForBase(newMsgMap, linePMsg, prcEffTMsg, perSchdSmryBean);
            S21ApiTBLAccessor.insert(insTopSchd);
            List<CalcSchdTermAndAmtLineBean> schdBeanList = createContractProcess.calcSchdTermAndAmt(newMsgMap, linePMsg, insTopSchd);
            for (CalcSchdTermAndAmtLineBean schdBean : schdBeanList) {
                DS_CONTR_BLLG_SCHDTMsg tmpSchd = createContractProcess.createSchdForBase(newMsgMap, linePMsg, prcEffTMsg, insTopSchd, schdBean);
                DS_CONTR_BLLG_SCHDTMsg asisSchd = NSZC0470Query.getInstance().getAsisSchdByPePkDt(glblCmpyCd, prcEffTMsg.dsContrPrcEffPk.getValue(), tmpSchd.bllgSchdFromDt.getValue());
                if (asisSchd != null) {
                    NSZC047001CommonLogic.deleteSchdBySchd(newMsgMap, asisSchd, FLG_OFF_N);
                }
                S21ApiTBLAccessor.insert(tmpSchd);
            }
            setValue(shcdSmryTMsg.bllgSchdThruDt, asisSchdSmryBean.getBllgSchdThruDt());
            setValue(shcdSmryTMsg.perSchdNum, asisSchdSmryBean.getPerSchdNum());
            Map<String, BigDecimal> asisTopSchdMap = NSZC0470Query.getInstance().getBaseTermAmtAndAdjAmt(glblCmpyCd, shcdSmryTMsg.dsContrBllgSchdSmryPk.getValue());
            if (asisTopSchdMap != null) {
                setValue(shcdSmryTMsg.baseSubTotPrcDealAmt, (BigDecimal) asisTopSchdMap.get("BASE_ACTL_PRC_DEAL_AMT"));
                setValue(shcdSmryTMsg.basePrcDealAdjAmt, (BigDecimal) asisTopSchdMap.get("BASE_PRC_ADJ_DEAL_AMT"));
            }
            S21ApiTBLAccessor.update(shcdSmryTMsg);
        }
        BigDecimal baseTermAmtRate = NSZC0470Query.getInstance().getSumBaseDealAmt(prcEffTMsg);
        setValue(prcEffTMsg.basePrcTermDealAmtRate, baseTermAmtRate);
        S21ApiTBLAccessor.update(prcEffTMsg);
    }

    // START 2017/12/05 K.Kojima [QC#21595,MOD]
    // private void updatePrcEffAndSchdInfoForUsageSchdAdd(S21ApiMessageMap msgMap, DS_CONTR_DTLTMsg dsContrDtlTMsg, String effFromDt) {
    private void updatePrcEffAndSchdInfoForUsageSchdAdd(S21ApiMessageMap msgMap, DS_CONTR_DTLTMsg dsContrDtlTMsg, String effFromDt, DS_CONTRTMsg dsContrTMsg) {
    // END 2017/12/05 K.Kojima [QC#21595,MOD]

        NSZC047005PMsg pMsg = (NSZC047005PMsg) msgMap.getPmsg();

        String baseChrgFlg = pMsg.baseChrgFlg.getValue();
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        BigDecimal dsContrDtlPk = pMsg.dsContrDtlPk.getValue();
        BigDecimal dsContrBllgMtrPk = null;
        if (pMsg.xxMtrLineList.getValidCount() > 0) {
            dsContrBllgMtrPk = pMsg.xxMtrLineList.no(0).dsContrBllgMtrPk_ML.getValue();
        }
        String newThruDt = ZYPDateUtil.addDays(effFromDt, -1);

        List<Map<String, BigDecimal>> updateBllgSchdList = NSZC0470Query.getInstance().getBllgSchdForUpdateByFromDt(glblCmpyCd, dsContrDtlPk, dsContrBllgMtrPk, effFromDt, baseChrgFlg);
        List<BigDecimal> prcEffPkList = getPrcEffPkList(updateBllgSchdList);
        List<BigDecimal> schdSmryPkList = getSchdSmryPkList(updateBllgSchdList);

        DS_CONTR_PRC_EFFTMsg prcEffTMsg = null;
        for (BigDecimal prcEffPk : prcEffPkList) {

            DS_CONTR_PRC_EFFTMsg tmpPrcEffTMsg = NSZC0470Query.getInstance().getDsContrPrcEffTMsg(glblCmpyCd, prcEffPk);
            if (tmpPrcEffTMsg.contrPrcEffFromDt.getValue().compareTo(effFromDt) <= 0 && effFromDt.compareTo(tmpPrcEffTMsg.contrPrcEffThruDt.getValue()) <= 0) {
                prcEffTMsg = tmpPrcEffTMsg;
                break;
            }
        }
        setValue(prcEffTMsg.contrPrcEffThruDt, newThruDt);

        DS_CONTR_BLLG_SCHD_SMRYTMsg shcdSmryTMsg = null;
        
        for (BigDecimal schdSmryPk : schdSmryPkList) {
            DS_CONTR_BLLG_SCHD_SMRYTMsg tmpShcdSmryTMsg = NSZC0470Query.getInstance().getDsContrBllgSchdSmryTMsg(glblCmpyCd, schdSmryPk);
            if (tmpShcdSmryTMsg.bllgSchdFromDt.getValue().compareTo(effFromDt) <= 0 && effFromDt.compareTo(tmpShcdSmryTMsg.bllgSchdThruDt.getValue()) <= 0) {
                shcdSmryTMsg = tmpShcdSmryTMsg;
                break;
            }
        }

        // START 2017/12/05 K.Kojima [QC#21595,MOD]
        // NSZC047001PMsg mode01PMsg = getMode01Pmsg(msgMap, dsContrDtlTMsg, pMsg.effFromDt.getValue(), pMsg.effThruDt.getValue(), prcEffTMsg.dsContrPrcEffPk.getValue(), prcEffTMsg.dsContrPrcEffSqNum.getValue());
        NSZC047001PMsg mode01PMsg = getMode01Pmsg(msgMap, dsContrDtlTMsg, pMsg.effFromDt.getValue(), pMsg.effThruDt.getValue(), prcEffTMsg.dsContrPrcEffPk.getValue(), prcEffTMsg.dsContrPrcEffSqNum.getValue(), dsContrTMsg);
        // END 2017/12/05 K.Kojima [QC#21595,MOD]
        setValue(mode01PMsg.xxRqstFlg, FLG_OFF_N);
        S21ApiMessageMap newMsgMap = new S21ApiMessageMap(mode01PMsg);
        M01CreateContractProcess createContractProcess = new M01CreateContractProcess(newMsgMap);
        NSZC047001_xxMtrLineListPMsg linePMsg = mode01PMsg.xxMtrLineList.no(0);

        CalcSchdSmryTermAndAmtBean inBean = new CalcSchdSmryTermAndAmtBean();
        inBean.setGlblCmpyCd(pMsg.glblCmpyCd.getValue());
        inBean.setBllgSchdFromDt(shcdSmryTMsg.bllgSchdFromDt.getValue());
        inBean.setBllgSchdThruDt(newThruDt);
        inBean.setBllgCycleCd(shcdSmryTMsg.bllgCycleCd.getValue());
        inBean.setContrCloDay(dsContrDtlTMsg.mtrCloDay.getValue());
        inBean.setBaseChrgFlg(FLG_OFF_N);
        inBean.setUsgChrgFlg(FLG_ON_Y);
        inBean.setCcyCd(shcdSmryTMsg.ccyCd.getValue());

        List<Map<String, Object>> fleetMachList = null;
        if (DS_CONTR_DTL_TP.FLEET.equals(dsContrDtlTMsg.dsContrDtlTpCd.getValue())) {
            fleetMachList = NSZC0470Query.getInstance().getFleetMachContrInfo(pMsg.glblCmpyCd.getValue(), pMsg.dsContrDtlPk.getValue()
                    , newThruDt, effFromDt, effFromDt, dsContrBllgMtrPk);
        }

        CalcSchdSmryTermAndAmtBean outBean = NSXC003001CalcSchdSmryTermAndAmt.calcSchdSmryTermAndAmt(inBean);
        List<CalcSchdSmryTermAndAmtForUsageBean> usageBeanList = outBean.getUsageList();
        
        if (usageBeanList.size() == 1) {
            CalcSchdSmryTermAndAmtForUsageBean perSchdSmryBean = usageBeanList.get(usageBeanList.size() - 1);
            setValue(shcdSmryTMsg.bllgSchdThruDt, newThruDt);
            List<CalcSchdTermAndAmtLineBean> schdBeanList = createContractProcess.calcSchdTermAndAmt(newMsgMap, linePMsg, shcdSmryTMsg);
            CalcSchdTermAndAmtLineBean schdBean = schdBeanList.get(schdBeanList.size()-1);
            DS_CONTR_BLLG_SCHDTMsg tmpSchd = createContractProcess.createSchdForUsage(newMsgMap, linePMsg, prcEffTMsg, shcdSmryTMsg, schdBean);
            DS_CONTR_BLLG_SCHDTMsg asisSchd = NSZC0470Query.getInstance().getAsisSchdByPePkDt(glblCmpyCd, prcEffTMsg.dsContrPrcEffPk.getValue(), tmpSchd.bllgSchdFromDt.getValue());
            if (asisSchd == null) {
                S21ApiTBLAccessor.insert(tmpSchd);
                if (fleetMachList != null) {
                    for (Map<String, Object> fleetMachInfo : fleetMachList) {
                        createContractProcess.createSchdForFleetMachUsage(newMsgMap, linePMsg, prcEffTMsg, shcdSmryTMsg, tmpSchd, schdBean, fleetMachInfo);
                    }
                }
            } else {
                setValue(asisSchd.skipRecovTpCd, tmpSchd.skipRecovTpCd);
                setValue(asisSchd.nextBllgDt, tmpSchd.nextBllgDt);
                setValue(asisSchd.bllgSchdThruDt, tmpSchd.bllgSchdThruDt);
                setValue(asisSchd.bllgSchdPrrtFlg, tmpSchd.bllgSchdPrrtFlg);
                setValue(asisSchd.dsContrTrmnFlg, tmpSchd.dsContrTrmnFlg);
                setValue(asisSchd.bllblFlg, tmpSchd.bllblFlg);
                setValue(asisSchd.rvsSchdDt, tmpSchd.rvsSchdDt);
                setValue(asisSchd.bllgPerMthAot, tmpSchd.bllgPerMthAot);
                S21ApiTBLAccessor.update(asisSchd);
                if (fleetMachList != null) {
                    for (Map<String, Object> fleetMachInfo : fleetMachList) {
                        updateFleetChildMachineUsage(msgMap, asisSchd, fleetMachInfo);
                    }
                }
            }
            List<NSZC047001_xxMtrLineListPMsg> linePMsgList = setLinePmsgListByPe(dsContrDtlTMsg, shcdSmryTMsg.bllgSchdFromDt.getValue(), pMsg, newThruDt, prcEffTMsg);
            deleteTopSchdMtr(glblCmpyCd, shcdSmryTMsg.dsContrBllgSchdSmryPk.getValue());
            CalcSchdSmryTermAndAmtBean allowanceBean = createContractProcess.calcAllowance(newMsgMap, perSchdSmryBean, linePMsgList);
            insertTopSchdMtr(newMsgMap, createContractProcess, allowanceBean, shcdSmryTMsg);
            setValue(shcdSmryTMsg.bllgSchdThruDt, perSchdSmryBean.getBllgSchdThruDt());
            setValue(shcdSmryTMsg.perSchdNum, perSchdSmryBean.getPerSchdNum());
            setValue(shcdSmryTMsg.bllgSchdThruDt, perSchdSmryBean.getBllgSchdThruDt());
            setValue(shcdSmryTMsg.mlyCopyInclPrcQty, allowanceBean.getUsageList().get(0).getXsMtrCopyQty());
            S21ApiTBLAccessor.update(shcdSmryTMsg);
        } else {
            CalcSchdSmryTermAndAmtForUsageBean asisSchdSmryBean = usageBeanList.get(usageBeanList.size() - 2);
            CalcSchdSmryTermAndAmtForUsageBean perSchdSmryBean = usageBeanList.get(usageBeanList.size() - 1);
            //As-Is Top Schedule Update
            setValue(shcdSmryTMsg.bllgSchdThruDt, asisSchdSmryBean.getBllgSchdThruDt());
            setValue(shcdSmryTMsg.perSchdNum, asisSchdSmryBean.getPerSchdNum());
            S21ApiTBLAccessor.update(shcdSmryTMsg);
            //To-Be Top Schedule Insert
            int topSchdNum = Integer.parseInt(shcdSmryTMsg.dsContrBllgSchdSqNum.getValue());
            topSchdNum++;
            perSchdSmryBean.setDsContrBllgSchdSqNum(Integer.toString(topSchdNum));
            List<NSZC047001_xxMtrLineListPMsg> linePMsgList = setLinePmsgListByPe(dsContrDtlTMsg, perSchdSmryBean.getBllgSchdFromDt(), pMsg, newThruDt, prcEffTMsg);
            CalcSchdSmryTermAndAmtBean allowanceBean = createContractProcess.calcAllowance(newMsgMap, perSchdSmryBean, linePMsgList);
            DS_CONTR_BLLG_SCHD_SMRYTMsg insTopSchd = createContractProcess.createSchdSmryForUsage(newMsgMap, linePMsg, prcEffTMsg, perSchdSmryBean, allowanceBean);
            S21ApiTBLAccessor.insert(insTopSchd);
            insertTopSchdMtr(newMsgMap, createContractProcess, allowanceBean, insTopSchd);
            //To-Be Billing Schedule Insert
            List<CalcSchdTermAndAmtLineBean> schdBeanList = createContractProcess.calcSchdTermAndAmt(newMsgMap, linePMsg, insTopSchd);
            for (CalcSchdTermAndAmtLineBean schdBean : schdBeanList) {
                DS_CONTR_BLLG_SCHDTMsg tmpSchd = createContractProcess.createSchdForUsage(newMsgMap, linePMsg, prcEffTMsg, insTopSchd, schdBean);
                DS_CONTR_BLLG_SCHDTMsg asisSchd = NSZC0470Query.getInstance().getAsisSchdByPePkDt(glblCmpyCd, prcEffTMsg.dsContrPrcEffPk.getValue(), tmpSchd.bllgSchdFromDt.getValue());
                if (asisSchd != null) {
                    NSZC047001CommonLogic.deleteSchdBySchd(newMsgMap, asisSchd, FLG_ON_Y);
                }
                S21ApiTBLAccessor.insert(tmpSchd);
                if (fleetMachList != null) {
                    for (Map<String, Object> fleetMachInfo : fleetMachList) {
                        createContractProcess.createSchdForFleetMachUsage(newMsgMap, linePMsg, prcEffTMsg, insTopSchd, tmpSchd, schdBean, fleetMachInfo);
                    }
                }
            }
        }
        BigDecimal baseTermAmtRate = NSZC0470Query.getInstance().getSumBaseDealAmt(prcEffTMsg);
        setValue(prcEffTMsg.basePrcTermDealAmtRate, baseTermAmtRate);
        S21ApiTBLAccessor.update(prcEffTMsg);
    }

    private List<NSZC047001_xxMtrLineListPMsg> setLinePmsgListByPe(DS_CONTR_DTLTMsg dsContrDtlTMsg, String effFromDt, NSZC047005PMsg pMsg, String newThruDt, DS_CONTR_PRC_EFFTMsg prcEffTMsg) {
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

    private void updateFleetChildMachineUsage(S21ApiMessageMap newMsgMap, DS_CONTR_BLLG_SCHDTMsg prntSchd, Map<String, Object> fleetMachInfo) {
        BigDecimal prntSchdPk = prntSchd.dsContrBllgSchdPk.getValue();
        BigDecimal dsContrDtlPk = (BigDecimal) fleetMachInfo.get("DS_CONTR_DTL_PK");
        DS_CONTR_BLLG_SCHDTMsg machSchd = NSZC0470Query.getInstance().getFleetChildSchd(prntSchd.glblCmpyCd.getValue(), dsContrDtlPk, prntSchdPk);
        if (machSchd == null) {
            return;
        }
        setValue(machSchd.skipRecovTpCd, prntSchd.skipRecovTpCd);
        setValue(machSchd.nextBllgDt, prntSchd.nextBllgDt);
        setValue(machSchd.bllgSchdThruDt, prntSchd.bllgSchdThruDt);
        setValue(machSchd.bllgSchdPrrtFlg, prntSchd.bllgSchdPrrtFlg);
        setValue(machSchd.rvsSchdDt, prntSchd.rvsSchdDt);
        setValue(machSchd.bllgPerMthAot, prntSchd.bllgPerMthAot);
        S21ApiTBLAccessor.update(machSchd);
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

    private void deleteTopSchdMtr(String glblCmpyCd, BigDecimal delSchdSmryPk) {
        List<BigDecimal> delSchdSmryPkList = new ArrayList<BigDecimal>();
        delSchdSmryPkList.add(delSchdSmryPk);
        List<BigDecimal> delSchdMtrPkList = NSZC0470Query.getInstance().getDeleteSchdMtrList(glblCmpyCd, delSchdSmryPkList);
        NSZC0470Query.getInstance().removeSchdMtr(glblCmpyCd, delSchdMtrPkList);
    }

    // START 2017/11/01 K.Kojima [QC#21859,MOD]
    // private List<CalcSchdSmryTermAndAmtForBaseBean> calcSchdSmryTermAndAmt(S21ApiMessageMap msgMap, DS_CONTR_DTLTMsg dsContrDtlTMsg, DS_CONTR_BLLG_SCHD_SMRYTMsg topSchd, String thruDt) {
    private List<CalcSchdSmryTermAndAmtForBaseBean> calcSchdSmryTermAndAmt(S21ApiMessageMap msgMap, DS_CONTR_DTLTMsg dsContrDtlTMsg, DS_CONTR_BLLG_SCHD_SMRYTMsg topSchd, String thruDt, String contrPrcEffFromDt, BigDecimal unScheduleBasePrice) {
    // END 2017/11/01 K.Kojima [QC#21859,MOD]
        NSZC047005PMsg pMsg = (NSZC047005PMsg) msgMap.getPmsg();

        CalcSchdSmryTermAndAmtBean inBean = new CalcSchdSmryTermAndAmtBean();
        inBean.setGlblCmpyCd(pMsg.glblCmpyCd.getValue());
        inBean.setBllgSchdFromDt(topSchd.bllgSchdFromDt.getValue());
        inBean.setBllgSchdThruDt(thruDt);
        inBean.setBllgCycleCd(topSchd.bllgCycleCd.getValue());
        inBean.setContrCloDay(dsContrDtlTMsg.contrCloDay.getValue());
        inBean.setBasePrcDealAmt(topSchd.basePrcDealAmt.getValue());
        // START 2017/11/01 K.Kojima [QC#21859,ADD]
        BigDecimal basePrcTermDealAmtRate = null;
        int bllgCycleCnt = calcBllgCycleCntFromDuration(inBean.getGlblCmpyCd(), contrPrcEffFromDt, inBean.getBllgSchdThruDt(), inBean.getBllgCycleCd());
        if (bllgCycleCnt > 0 && ZYPCommonFunc.hasValue(inBean.getBasePrcDealAmt())) {
            basePrcTermDealAmtRate = inBean.getBasePrcDealAmt().multiply(BigDecimal.valueOf(bllgCycleCnt));
            if (unScheduleBasePrice != null) {
                basePrcTermDealAmtRate = basePrcTermDealAmtRate.subtract(unScheduleBasePrice);
            }
        }
        inBean.setBasePrcTermDealAmtRate(basePrcTermDealAmtRate);
        // END 2017/11/01 K.Kojima [QC#21859,ADD]
        inBean.setBaseChrgFlg(FLG_ON_Y);
        inBean.setUsgChrgFlg(FLG_OFF_N);
        inBean.setCcyCd(topSchd.ccyCd.getValue());

        CalcSchdSmryTermAndAmtBean outBean = NSXC003001CalcSchdSmryTermAndAmt.calcSchdSmryTermAndAmt(inBean);
        return outBean.getBaseList();
    }

    // Add End   06/29/2016 <QC#7428,QC#7429>

    private void updateSchdSmry(S21ApiMessageMap msgMap, DS_CONTR_BLLG_SCHD_SMRYTMsg inTMsg, String effFromDt, String baseChrgFlg) {

        String glblCmpyCd = inTMsg.glblCmpyCd.getValue();
        BigDecimal dsContrDtlPk = inTMsg.dsContrDtlPk.getValue();
        BigDecimal dsContrBllgMtrPk = inTMsg.dsContrBllgMtrPk.getValue();
        String bllgSchdFromDt = inTMsg.bllgSchdFromDt.getValue();
        String bllgSchdThruDt = inTMsg.bllgSchdThruDt.getValue();

        Map<String, BigDecimal> smryBasePrcAmt = NSZC0470Query.getInstance().getSummaryBasePrcAmt(glblCmpyCd, dsContrDtlPk, dsContrBllgMtrPk, bllgSchdFromDt, bllgSchdThruDt, baseChrgFlg);

        if (smryBasePrcAmt != null) {
            if (FLG_ON_Y.equals(baseChrgFlg)) {

                setValue(inTMsg.basePrcDealAdjAmt, smryBasePrcAmt.get("BASE_PRC_ADJ_DEAL_AMT"));
                setValue(inTMsg.baseSubTotPrcDealAmt, smryBasePrcAmt.get("BASE_ACTL_PRC_DEAL_AMT"));
            }
            setValue(inTMsg.perSchdNum, smryBasePrcAmt.get("PER_SCHD_NUM"));
        }

        setValue(inTMsg.bllgSchdThruDt, ZYPDateUtil.addDays(effFromDt, -1));

        S21ApiTBLAccessor.update(inTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
            msgMap.addXxMsgIdWithPrm(NSAM0031E, new String[]{"DS_CONTR_BLLG_SCHD_SMRY"});
        }
    }

    private void updatePrcEff(S21ApiMessageMap msgMap, DS_CONTR_PRC_EFFTMsg inTMsg, String effFromDt, String baseChrgFlg) {

        if (FLG_ON_Y.equals(baseChrgFlg)) {
            String glblCmpyCd = inTMsg.glblCmpyCd.getValue();
            BigDecimal dsContrDtlPk = inTMsg.dsContrDtlPk.getValue();
            BigDecimal dsContrBllgMtrPk = inTMsg.dsContrBllgMtrPk.getValue();
            String bllgSchdFromDt = inTMsg.contrPrcEffFromDt.getValue();
            String bllgSchdThruDt = inTMsg.contrPrcEffThruDt.getValue();

            Map<String, BigDecimal> smryBasePrcAmt = NSZC0470Query.getInstance().getSummaryBasePrcAmt(glblCmpyCd, dsContrDtlPk, dsContrBllgMtrPk, bllgSchdFromDt, bllgSchdThruDt, baseChrgFlg);

            if (smryBasePrcAmt != null) {
                setValue(inTMsg.basePrcTermDealAmtRate, smryBasePrcAmt.get("BASE_ACTL_PRC_DEAL_AMT"));
            }
        }

        setValue(inTMsg.contrPrcEffThruDt, ZYPDateUtil.addDays(effFromDt, -1));

        S21ApiTBLAccessor.update(inTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
            msgMap.addXxMsgIdWithPrm(NSAM0031E, new String[]{"DS_CONTR_BLLG_SCHD_SMRY"});
        }
    }

    private List<BigDecimal> getPrcEffPkList(List<Map<String, BigDecimal>> bllgSchdList) {

        List<BigDecimal> pkList = new ArrayList<BigDecimal>();
        for (Map<String, BigDecimal> bllgSchdInfo : bllgSchdList) {
            BigDecimal pk = bllgSchdInfo.get("DS_CONTR_PRC_EFF_PK");
            if (!pkList.contains(pk)) {
                pkList.add(pk);
            }
        }
        return pkList;
    }

    private List<BigDecimal> getSchdSmryPkList(List<Map<String, BigDecimal>> bllgSchdList) {

        List<BigDecimal> pkList = new ArrayList<BigDecimal>();
        for (Map<String, BigDecimal> bllgSchdInfo : bllgSchdList) {
            BigDecimal pk = bllgSchdInfo.get("DS_CONTR_BLLG_SCHD_SMRY_PK");
            if (!pkList.contains(pk)) {
                pkList.add(pk);
            }
        }
        return pkList;
    }

    private List<BigDecimal> getSchdPkList(List<Map<String, BigDecimal>> bllgSchdList) {

        List<BigDecimal> pkList = new ArrayList<BigDecimal>();
        for (Map<String, BigDecimal> bllgSchdInfo : bllgSchdList) {
            BigDecimal pk = bllgSchdInfo.get("DS_CONTR_BLLG_SCHD_PK");
            if (!pkList.contains(pk)) {
                pkList.add(pk);
            }
        }
        return pkList;
    }

    // Mod Start 06/29/2016 <QC#7428,QC#7429>
    // START 2017/12/05 K.Kojima [QC#21595,MOD]
    // private void createPrcEffAndSchdInfo(S21ApiMessageMap msgMap, DS_CONTR_DTLTMsg dsContrDtlTMsg, String effFromDt, String effThruDt, BigDecimal prcEffPk, BigDecimal seqNum) {
    private void createPrcEffAndSchdInfo(S21ApiMessageMap msgMap, DS_CONTR_DTLTMsg dsContrDtlTMsg, String effFromDt, String effThruDt, BigDecimal prcEffPk, BigDecimal seqNum, DS_CONTRTMsg dsContrTMsg) {
    // END 2017/12/05 K.Kojima [QC#21595,MOD]

        // START 2017/12/05 K.Kojima [QC#21595,MOD]
        // NSZC047001PMsg mode01PMsg = getMode01Pmsg(msgMap, dsContrDtlTMsg, effFromDt, effThruDt, prcEffPk, seqNum);
        NSZC047001PMsg mode01PMsg = getMode01Pmsg(msgMap, dsContrDtlTMsg, effFromDt, effThruDt, prcEffPk, seqNum, dsContrTMsg);
        // END 2017/12/05 K.Kojima [QC#21595,MOD]

        callCreateContractProcess(msgMap, mode01PMsg);
    }

    // START 2017/12/05 K.Kojima [QC#21595,MOD]
    // private NSZC047001PMsg getMode01Pmsg(S21ApiMessageMap msgMap, DS_CONTR_DTLTMsg dsContrDtlTMsg, String effFromDt, String effThruDt, BigDecimal prcEffPk, BigDecimal seqNum) {
    private NSZC047001PMsg getMode01Pmsg(S21ApiMessageMap msgMap, DS_CONTR_DTLTMsg dsContrDtlTMsg, String effFromDt, String effThruDt, BigDecimal prcEffPk, BigDecimal seqNum, DS_CONTRTMsg dsContrTMsg) {
    // END 2017/12/05 K.Kojima [QC#21595,MOD]
        NSZC047005PMsg mode05PMsg = (NSZC047005PMsg) msgMap.getPmsg();
        String baseChrgFlg = mode05PMsg.baseChrgFlg.getValue();
        String usgChrgFlg = mode05PMsg.usgChrgFlg.getValue();

        NSZC047001PMsg mode01PMsg = new NSZC047001PMsg();

        setValue(mode01PMsg.glblCmpyCd, mode05PMsg.glblCmpyCd);
        setValue(mode01PMsg.xxModeCd, mode05PMsg.xxModeCd);
        setValue(mode01PMsg.slsDt, mode05PMsg.slsDt);
        setValue(mode01PMsg.dsContrDtlPk, mode05PMsg.dsContrDtlPk);
        setValue(mode01PMsg.baseChrgFlg, mode05PMsg.baseChrgFlg);
        setValue(mode01PMsg.usgChrgFlg, mode05PMsg.usgChrgFlg);
        if (hasValue(effFromDt)) {
            setValue(mode01PMsg.contrEffFromDt, effFromDt);
            setValue(mode01PMsg.contrEffThruDt, effThruDt);
        } else {
            setValue(mode01PMsg.contrEffFromDt, mode05PMsg.effFromDt);
            setValue(mode01PMsg.contrEffThruDt, mode05PMsg.effThruDt);
        }

        if (FLG_ON_Y.equals(baseChrgFlg)) {
            setValue(mode01PMsg.contrCloDay, dsContrDtlTMsg.contrCloDay);
            setValue(mode01PMsg.baseBllgTmgCd, dsContrDtlTMsg.baseBllgTmgCd);
            setValue(mode01PMsg.contrBllgDay, dsContrDtlTMsg.contrBllgDay);
            setValue(mode01PMsg.xxBaseLineList.no(0).dsContrPrcEffSqNum_BL, seqNum);
            if (hasValue(effFromDt)) {
                setValue(mode01PMsg.xxBaseLineList.no(0).effFromDt_BL, effFromDt);
                setValue(mode01PMsg.xxBaseLineList.no(0).effThruDt_BL, ZYPDateUtil.addDays(mode05PMsg.effFromDt.getValue(), -1));
                BigDecimal basePrcDealAmt = NSZC0470Query.getInstance().getPrcEffBasePrcAmt(mode05PMsg.glblCmpyCd.getValue(), prcEffPk);
                setValue(mode01PMsg.xxBaseLineList.no(0).basePrcDealAmt_BL, basePrcDealAmt);
                mode01PMsg.xxBaseLineList.no(0).basePrcTermDealAmtRate_BL.clear();
            } else {
                setValue(mode01PMsg.xxBaseLineList.no(0).effFromDt_BL, mode05PMsg.effFromDt);
                setValue(mode01PMsg.xxBaseLineList.no(0).effThruDt_BL, mode05PMsg.effThruDt);
                setValue(mode01PMsg.xxBaseLineList.no(0).basePrcDealAmt_BL, mode05PMsg.basePrcDealAmt);
                setValue(mode01PMsg.xxBaseLineList.no(0).basePrcTermDealAmtRate_BL, mode05PMsg.basePrcTermDealAmtRate);
            }
            setValue(mode01PMsg.xxBaseLineList.no(0).baseBllgCycleCd_BL, dsContrDtlTMsg.baseBllgCycleCd);
            // START 2017/12/05 K.Kojima [QC#21595,MOD]
            // String dsContrPrcEffStsCd = getPrcEffStsCd(mode05PMsg);
            String dsContrPrcEffStsCd = getPrcEffStsCd(mode05PMsg, dsContrTMsg);
            // END 2017/12/05 K.Kojima [QC#21595,MOD]
            setValue(mode01PMsg.xxBaseLineList.no(0).dsContrPrcEffStsCd_BL, dsContrPrcEffStsCd);
            setValue(mode01PMsg.xxBaseLineList.no(0).qltyAsrnHldFlg_BL, FLG_OFF_N);
            setValue(mode01PMsg.xxBaseLineList.no(0).mtrHldFlg_BL, FLG_OFF_N);
            setValue(mode01PMsg.xxBaseLineList.no(0).contrHldFlg_BL, FLG_OFF_N);
            setValue(mode01PMsg.xxBaseLineList.no(0).bllgHldFlg_BL, FLG_OFF_N);
            setValue(mode01PMsg.xxBaseLineList.no(0).qltyAsrnHldPendApvlFlg_BL, FLG_OFF_N);
            // Dummy Pk Set
            setValue(mode01PMsg.xxBaseLineList.no(0).dsContrPrcEffPk_BL, BigDecimal.ZERO);
            mode01PMsg.xxBaseLineList.setValidCount(1);
        }

        if (FLG_ON_Y.equals(usgChrgFlg)) {
            setValue(mode01PMsg.mtrCloDay, dsContrDtlTMsg.mtrCloDay);
            setValue(mode01PMsg.mtrBllgTmgCd, dsContrDtlTMsg.mtrBllgTmgCd);
            setValue(mode01PMsg.mtrBllgDay, dsContrDtlTMsg.mtrBllgDay);
            BigDecimal dsContrBllgMtrPk = null;
            dsContrBllgMtrPk = mode05PMsg.xxMtrLineList.no(0).dsContrBllgMtrPk_ML.getValue();
            DS_CONTR_BLLG_MTRTMsg bllgMtr = NSZC0470Query.getInstance().getBllgMtrTMsg(mode05PMsg.glblCmpyCd.getValue(), dsContrBllgMtrPk);

            for (int i = 0; i < mode05PMsg.xxMtrLineList.getValidCount(); i++) {
                setValue(mode01PMsg.xxMtrLineList.no(i).dsContrPrcEffSqNum_ML, seqNum);
                setValue(mode01PMsg.xxMtrLineList.no(i).effFromDt_ML, mode05PMsg.effFromDt);
                setValue(mode01PMsg.xxMtrLineList.no(i).effThruDt_ML, mode05PMsg.effThruDt);
                setValue(mode01PMsg.xxMtrLineList.no(i).mtrBllgCycleCd_ML, bllgMtr.bllgMtrBllgCycleCd);
                setValue(mode01PMsg.xxMtrLineList.no(i).dsContrBllgMtrPk_ML, mode05PMsg.xxMtrLineList.no(i).dsContrBllgMtrPk_ML);
                setValue(mode01PMsg.xxMtrLineList.no(i).contrXsCopyPk_ML, mode05PMsg.xxMtrLineList.no(i).contrXsCopyPk_ML);
                if (hasValue(effFromDt)) {
                    Map<String, Object> prcEffMtrInfo = NSZC0470Query.getInstance().getPrcEffMtrInfo(mode05PMsg.glblCmpyCd.getValue(), prcEffPk, mode05PMsg.xxMtrLineList.no(i).contrXsCopyPk_ML.getValue());
                    setValue(mode01PMsg.xxMtrLineList.no(i).xsMtrCopyQty_ML, (BigDecimal) prcEffMtrInfo.get("XS_MTR_COPY_QTY"));
                    setValue(mode01PMsg.xxMtrLineList.no(i).xsMtrAmtRate_ML, (BigDecimal) prcEffMtrInfo.get("XS_MTR_AMT_RATE"));
                } else {
                    setValue(mode01PMsg.xxMtrLineList.no(i).xsMtrCopyQty_ML, mode05PMsg.xxMtrLineList.no(i).xsMtrCopyQty_ML);
                    setValue(mode01PMsg.xxMtrLineList.no(i).xsMtrAmtRate_ML, mode05PMsg.xxMtrLineList.no(i).xsMtrAmtRate_ML);
                }
                setValue(mode01PMsg.xxMtrLineList.no(i).xsMtrFirstFlg_ML, mode05PMsg.xxMtrLineList.no(i).xsMtrFirstFlg_ML);
                // START 2017/12/05 K.Kojima [QC#21595,MOD]
                // String dsContrPrcEffStsCd = getPrcEffStsCd(mode05PMsg);
                String dsContrPrcEffStsCd = getPrcEffStsCd(mode05PMsg, dsContrTMsg);
                // END 2017/12/05 K.Kojima [QC#21595,MOD]
                setValue(mode01PMsg.xxMtrLineList.no(i).dsContrPrcEffStsCd_ML, dsContrPrcEffStsCd);
                setValue(mode01PMsg.xxMtrLineList.no(i).qltyAsrnHldFlg_ML, FLG_OFF_N);
                setValue(mode01PMsg.xxMtrLineList.no(i).mtrHldFlg_ML, FLG_OFF_N);
                setValue(mode01PMsg.xxMtrLineList.no(i).contrHldFlg_ML, FLG_OFF_N);
                setValue(mode01PMsg.xxMtrLineList.no(i).bllgHldFlg_ML, FLG_OFF_N);
                setValue(mode01PMsg.xxMtrLineList.no(i).qltyAsrnHldPendApvlFlg_ML, FLG_OFF_N);
                // Dummy Pk Set
                setValue(mode01PMsg.xxMtrLineList.no(i).dsContrPrcEffPk_ML, BigDecimal.ZERO);
                mode01PMsg.xxMtrLineList.setValidCount(i + 1);
            }
        }
        return mode01PMsg;
    }
    // Mod End  06/29/2016 <QC#7428,QC#7429>

    private boolean callCreateContractProcess(S21ApiMessageMap msgMap, NSZC047001PMsg pMsg) {
        S21ApiMessageMap newMsgMap = new S21ApiMessageMap(pMsg);
        M01CreateContractProcess createContractProcess = new M01CreateContractProcess(newMsgMap);
        createContractProcess.doProcess(newMsgMap, this.onBatchTp);
        return true;
    }

    // START 2017/12/05 K.Kojima [QC#21595,MOD]
    // private String getPrcEffStsCd(NSZC047005PMsg mode05PMsg) {
    private String getPrcEffStsCd(NSZC047005PMsg mode05PMsg, DS_CONTRTMsg dsContrTMsg) {
    // END 2017/12/05 K.Kojima [QC#21595,MOD]

        // START 2017/12/05 K.Kojima [QC#21595,ADD]
        if (DS_CONTR_EDI.CFS.equals(dsContrTMsg.dsContrEdiCd.getValue())) {
            return DS_CONTR_DTL_STS.SIGNED;
        }
        // END 2017/12/05 K.Kojima [QC#21595,ADD]

        String effFromDt = mode05PMsg.effFromDt.getValue();
        String effThruDt = mode05PMsg.effThruDt.getValue();
        String slsDt = mode05PMsg.slsDt.getValue();

        if (effFromDt.compareTo(slsDt) <= 0 && slsDt.compareTo(effThruDt) <= 0) {
            return DS_CONTR_DTL_STS.ACTIVE;
        }
        return DS_CONTR_DTL_STS.SIGNED;
    }

    private int calcBllgCycleCntFromDuration(String glblCmpyCd, String contrEffFromDt, String contrEffThruDt, String baseBllgCycleCd) {
        // calculate duration
        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        Date startDt;
        try {
            startDt = df.parse(contrEffFromDt);
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }

        String paramEndDate = contrEffThruDt;
        Calendar cal = Calendar.getInstance();
        String calcEndDate = "";
        BigDecimal durnCnt = BigDecimal.ZERO;

        while (paramEndDate.compareTo(calcEndDate) > 0) {
            cal.setTime(startDt);
            durnCnt = durnCnt.add(BigDecimal.ONE);

            cal.add(Calendar.MONTH, durnCnt.intValue());
            cal.add(Calendar.DATE, -1);

            calcEndDate = df.format(cal.getTime());
        }

        if (paramEndDate.compareTo(calcEndDate) != 0) {
            return 0;
        }

        BLLG_CYCLETMsg bcTMsg = new BLLG_CYCLETMsg();
        setValue(bcTMsg.glblCmpyCd, glblCmpyCd);
        setValue(bcTMsg.bllgCycleCd, baseBllgCycleCd);
        bcTMsg = (BLLG_CYCLETMsg) EZDTBLAccessor.findByKey(bcTMsg);

        if (bcTMsg == null || durnCnt.intValue() % bcTMsg.bllgCycleMthAot.getValueInt() != 0) {
            return 0;
        }
        return durnCnt.intValue() / bcTMsg.bllgCycleMthAot.getValueInt();
    }

}
