/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC047001;

import static com.canon.cusa.s21.api.NSZ.NSZC047001.constant.NSZC047001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.db.BLLG_CYCLETMsg;
import business.db.CCYTMsg;
import business.db.DS_CONTR_BLLG_SCHDTMsg;
import business.db.DS_CONTR_BLLG_SCHD_SMRYTMsg;
import business.db.DS_CONTR_PRC_EFFTMsg;
import business.parts.NSZC047001PMsg;
import business.parts.NSZC047001_xxBaseLineListPMsg;
import business.parts.NSZC047001_xxMtrLineListPMsg;
import business.parts.NSZC047001_xxMtrLineListPMsgArray;
import business.parts.NSZC047002PMsg;
import business.parts.NSZC047002_xxBaseLineListPMsg;
import business.parts.NSZC047002_xxMtrLineListPMsg;

import com.canon.cusa.s21.common.NSX.NSXC003001.CalcSchdSmryTermAndAmtBean;
import com.canon.cusa.s21.common.NSX.NSXC003001.CalcSchdSmryTermAndAmtForBaseBean;
import com.canon.cusa.s21.common.NSX.NSXC003001.CalcSchdSmryTermAndAmtForUsageBean;
import com.canon.cusa.s21.common.NSX.NSXC003001.CalcSchdTermAndAmtLineBean;
import com.canon.cusa.s21.common.NSX.NSXC003001.CalcTermAmtBean;
import com.canon.cusa.s21.common.NSX.NSXC003001.NSXC003001CalcSchdSmryTermAndAmt;
import com.canon.cusa.s21.common.NSX.NSXC003001.NSXC003001CalcTermAmt;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BLLG_CYCLE;
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
 * 01/21/2016   Hitachi         K.Kishimoto     Update          QC#3331
 * 02/19/2016   Hitachi         T.Tomita        Update          QC#3895
 * 03/28/2016   Hitachi         K.Kishimoto     Update          QC#1003
 * 06/23/2016   Hitachi         T.Kanasaka      Update          QC#10107
 * 09/06/2016   Hitachi         K.Kishimoto     Update          QC#12881
 * 10/03/2017   Hitachi         E.Kameishi      Update          QC#18636
 * 2017/10/30   Hitachi         K.Kitachi       Update          QC#21449
 * 2020/03/18   Hitachi         K.Kitachi       Update          QC#55693
 *</pre>
 */
public class M02TopScheduleProcess implements ZYPConstant {

    /** delSchdSmryPkList */
    private List<BigDecimal> delSchdSmryPkList = new ArrayList<BigDecimal>();

    /** delSchdMtrPkList */
    private List<BigDecimal> delSchdMtrPkList = new ArrayList<BigDecimal>();

    /** delSchdPkList */
    private List<BigDecimal> delSchdPkList = new ArrayList<BigDecimal>();

    protected void doProcess(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {

        checkParameter(msgMap);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }
        topScheduleProcess(msgMap);
    }

    private void topScheduleProcess(S21ApiMessageMap msgMap) {

        NSZC047002PMsg pMsg = (NSZC047002PMsg) msgMap.getPmsg();

        setValue(pMsg.contrCloDay, NSZC047001CommonLogic.convCloDay(pMsg.contrCloDay.getValue()));
        setValue(pMsg.mtrCloDay, NSZC047001CommonLogic.convCloDay(pMsg.mtrCloDay.getValue()));

        String baseChrgFlg = pMsg.baseChrgFlg.getValue();
        String usgChrgFlg = pMsg.usgChrgFlg.getValue();
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();

        BigDecimal dsContrPrcEffPk = null;
        BigDecimal dsContrBllgMtrPk = null;
        if (hasValue(pMsg.xxBaseLineList.no(0).dsContrPrcEffPk_BL.getValue())) {
            dsContrPrcEffPk = pMsg.xxBaseLineList.no(0).dsContrPrcEffPk_BL.getValue();
        } else if (hasValue(pMsg.xxMtrLineList.no(0).dsContrPrcEffPk_ML.getValue())) {
            dsContrPrcEffPk = pMsg.xxMtrLineList.no(0).dsContrPrcEffPk_ML.getValue();
            dsContrBllgMtrPk = pMsg.xxMtrLineList.no(0).dsContrBllgMtrPk_ML.getValue();
        }

        DS_CONTR_PRC_EFFTMsg dsContrPrcEffTMsg = NSZC0470Query.getInstance().getDsContrPrcEffTMsg(glblCmpyCd, dsContrPrcEffPk);
        BigDecimal dsContrDtlPk = dsContrPrcEffTMsg.dsContrDtlPk.getValue();

        String dsContrDtlTpCd = "";
        String ccyCd = "";
        List<Map<String, Object>> contrInfoList = NSZC0470Query.getInstance().getContrInfo(pMsg.glblCmpyCd.getValue(), pMsg.dsContrDtlPk.getValue());
        if (!contrInfoList.isEmpty()) {
            dsContrDtlTpCd = (String) contrInfoList.get(0).get("DS_CONTR_DTL_TP_CD");
            ccyCd = (String) contrInfoList.get(0).get("CCY_CD");
        }

        String lastBilledDt = "";
        if (FLG_ON_Y.equals(baseChrgFlg)) {
            lastBilledDt = NSZC0470Query.getInstance().getMaxBllgSchdThruDtByPrcEffPk(glblCmpyCd, dsContrDtlPk, dsContrPrcEffPk, dsContrBllgMtrPk, baseChrgFlg);
            if (hasValue(lastBilledDt)) {
                deletePrcEffAndSchdInfo(msgMap, dsContrDtlTpCd, dsContrPrcEffPk, lastBilledDt);
                updatePrcEffAndSchdInfo(msgMap, dsContrPrcEffPk, lastBilledDt);
            } else {
                deleteSchdInfo(msgMap, dsContrPrcEffPk);
            }
            baseChargeProcess(msgMap, dsContrPrcEffTMsg, lastBilledDt, ccyCd);
        }

        if (FLG_ON_Y.equals(usgChrgFlg)) {
            lastBilledDt = NSZC0470Query.getInstance().getMaxBllgSchdThruDtByPrcEffPk(glblCmpyCd, dsContrDtlPk, dsContrPrcEffPk, dsContrBllgMtrPk, baseChrgFlg);
            if (hasValue(lastBilledDt)) {
                deletePrcEffAndSchdInfo(msgMap, dsContrDtlTpCd, dsContrPrcEffPk, lastBilledDt);
                updatePrcEffAndSchdInfo(msgMap, dsContrPrcEffPk, lastBilledDt);
            } else {
                deleteSchdInfo(msgMap, dsContrPrcEffPk);
            }
            usageChargeProcess(msgMap, dsContrPrcEffTMsg, lastBilledDt, dsContrDtlTpCd);
            // Add Start 03/28/2016 <QC#1003>
            NSZC047001CommonLogic.mtrEntryStsUpd(msgMap, glblCmpyCd, pMsg.slsDt.getValue(), (BigDecimal) contrInfoList.get(0).get("DS_CONTR_PK"));
            // Add End   03/28/2016 <QC#1003>
            // START 2020/03/18 K.Kitachi [QC#55693, ADD]
            NSZC047001CommonLogic.resetXsCopyPk(msgMap, glblCmpyCd, (BigDecimal) contrInfoList.get(0).get("DS_CONTR_PK"));
            // END 2020/03/18 K.Kitachi [QC#55693, ADD]
        }
    }

    private void checkParameter(S21ApiMessageMap msgMap) {

        NSZC047002PMsg pMsg = (NSZC047002PMsg) msgMap.getPmsg();
        NSZC047001CommonLogic.mandatoryCheck(msgMap, pMsg.glblCmpyCd, ZZZM9007E, new String[]{"Global Company Code"});
        NSZC047001CommonLogic.mandatoryCheck(msgMap, pMsg.slsDt, ZZZM9007E, new String[]{"Salse Date"});
        NSZC047001CommonLogic.mandatoryCheck(msgMap, pMsg.dsContrDtlPk, ZZZM9007E, new String[]{"DS Contract Detail PK"});
        NSZC047001CommonLogic.mandatoryCheck(msgMap, pMsg.baseChrgFlg, ZZZM9007E, new String[]{"Base Charge Flag"});
        NSZC047001CommonLogic.mandatoryCheck(msgMap, pMsg.usgChrgFlg, ZZZM9007E, new String[]{"Usage Charge Flag"});
        NSZC047001CommonLogic.mandatoryCheck(msgMap, pMsg.chngRngFlg, ZZZM9007E, new String[]{"Change Range Flag"});
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

    private void deletePrcEffAndSchdInfo(S21ApiMessageMap msgMap, String dsContrDtlTp, BigDecimal dsContrPrcEffPk, String lastBilledDt) {

        NSZC047002PMsg pMsg = (NSZC047002PMsg) msgMap.getPmsg();

        String baseChrgFlg = pMsg.baseChrgFlg.getValue();
        String usgChrgFlg = pMsg.usgChrgFlg.getValue();
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        BigDecimal dsContrDtlPk = pMsg.dsContrDtlPk.getValue();
        BigDecimal dsContrBllgMtrPk = null;
        if (pMsg.xxMtrLineList.getValidCount() > 0) {
            dsContrBllgMtrPk = pMsg.xxMtrLineList.no(0).dsContrBllgMtrPk_ML.getValue();
        }
        String effFromDt = ZYPDateUtil.addDays(lastBilledDt, 1);

        List<Map<String, BigDecimal>> deleteBllgSchdList = NSZC0470Query.getInstance().getBllgSchdByPrcEffPk(glblCmpyCd, dsContrDtlPk, dsContrPrcEffPk, dsContrBllgMtrPk, baseChrgFlg, effFromDt);
        List<BigDecimal> schdSmryPkList = getSchdSmryPkList(deleteBllgSchdList);
        List<BigDecimal> schdPkList = getSchdPkList(deleteBllgSchdList);

        // Add Start 01/21/2016 <QC#3331>
        // START 2016/06/23 T.Kanasaka [QC#10107, MOD]
//        for (BigDecimal delSchdPk : delSchdPkList) {
        for (BigDecimal delSchdPk : schdPkList) {
        // END 2016/06/23 T.Kanasaka [QC#10107, MOD]
            NSZC047001CommonLogic.deleteSvcContrBllgInfo(msgMap, glblCmpyCd, delSchdPk);
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
            if (effFromDt.compareTo(shcdSmryTMsg.bllgSchdFromDt.getValue()) < 0) {
                NSZC0470Query.getInstance().removeSchdSmry(glblCmpyCd, schdSmryPk);

                if (FLG_ON_Y.equals(usgChrgFlg)) {
                    List<BigDecimal> schdMtrPkList = NSZC0470Query.getInstance().getDsContrBllgSchdMtrForCancel(glblCmpyCd, schdSmryPk);
                    NSZC0470Query.getInstance().removeSchdMtr(glblCmpyCd, schdMtrPkList);
                }
            }
        }
    }

    private void updatePrcEffAndSchdInfo(S21ApiMessageMap msgMap, BigDecimal dsContrPrcEffPk, String lastBilledDt) {

        NSZC047002PMsg pMsg = (NSZC047002PMsg) msgMap.getPmsg();
        String baseChrgFlg = pMsg.baseChrgFlg.getValue();
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();

        String effFromDt = ZYPDateUtil.addDays(lastBilledDt, 1);

        List<BigDecimal> schdSmryPkList = NSZC0470Query.getInstance().getSchdSmryByPrcEffPk(glblCmpyCd, dsContrPrcEffPk);

        for (BigDecimal schdSmryPk : schdSmryPkList) {

            DS_CONTR_BLLG_SCHD_SMRYTMsg shcdSmryTMsg = NSZC0470Query.getInstance().getDsContrBllgSchdSmryTMsg(glblCmpyCd, schdSmryPk);
            if (shcdSmryTMsg.bllgSchdFromDt.getValue().compareTo(effFromDt) <= 0 && effFromDt.compareTo(shcdSmryTMsg.bllgSchdThruDt.getValue()) <= 0) {
                updateSchdSmry(msgMap, shcdSmryTMsg, effFromDt, baseChrgFlg);
                break;
            }
        }
    }

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

    private boolean deleteSchdInfo(S21ApiMessageMap msgMap, BigDecimal dsContrPrcEffPk) {

        NSZC047002PMsg pMsg = (NSZC047002PMsg) msgMap.getPmsg();
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        String dsContrDtlTp = NSZC0470Query.getInstance().getDsContrDtlTp(glblCmpyCd, pMsg.dsContrDtlPk.getValue());
        // DS_CONTR_BLLG_SCHD
        this.delSchdPkList = NSZC0470Query.getInstance().getDeleteSchdList(glblCmpyCd, dsContrPrcEffPk);
        if (DS_CONTR_DTL_TP.FLEET.equals(dsContrDtlTp)) {
            for (BigDecimal delSchdPk : delSchdPkList) {
                List<BigDecimal> childDsContrBllgSchdPkList = NSZC0470Query.getInstance().getChildDsContrBllgSchdForCancel(glblCmpyCd, delSchdPk);
                // START 2017/10/30 K.Kitachi [QC#21449, ADD]
                NSZC047001CommonLogic.deleteSvcContrBllgInfo(msgMap, glblCmpyCd, childDsContrBllgSchdPkList);
                // END 2017/10/30 K.Kitachi [QC#21449, ADD]
                for (BigDecimal childDsContrBllgSchdPk : childDsContrBllgSchdPkList) {
                    NSZC0470Query.getInstance().removeSchd(glblCmpyCd, childDsContrBllgSchdPk);
                    // START 2017/10/03 E.Kameishi [QC18636, ADD]
                    NSZC0470Query.getInstance().removeSchdTestMtrSmry(glblCmpyCd, childDsContrBllgSchdPk);
                    // END 2017/10/03 E.Kameishi [QC18636, ADD]
                }
            }
        }
        NSZC0470Query.getInstance().removeSchd(glblCmpyCd, this.delSchdPkList);
        // START 2017/10/03 E.Kameishi [QC18636, ADD]
        NSZC0470Query.getInstance().removeSchdTestMtrSmry(glblCmpyCd,  this.delSchdPkList);
        // END 2017/10/03 E.Kameishi [QC18636, ADD]

        // DS_CONTR_BLLG_SCHD_SMRY
        this.delSchdSmryPkList = NSZC0470Query.getInstance().getDeleteSchdSmryList(glblCmpyCd, dsContrPrcEffPk);
        NSZC0470Query.getInstance().removeSchdSmry(glblCmpyCd, this.delSchdSmryPkList);

        // DS_CONTR_BLLG_SCHD_MTR
        this.delSchdMtrPkList = NSZC0470Query.getInstance().getDeleteSchdMtrList(glblCmpyCd, this.delSchdSmryPkList);
        NSZC0470Query.getInstance().removeSchdMtr(glblCmpyCd, delSchdMtrPkList);

        // START 2016/06/23 T.Kanasaka [QC#10107, ADD]
        for (BigDecimal delSchdPk : this.delSchdPkList) {
            NSZC047001CommonLogic.deleteSvcContrBllgInfo(msgMap, glblCmpyCd, delSchdPk);
        }
        // END 2016/06/23 T.Kanasaka [QC#10107, ADD]

        return true;
    }

    private void baseChargeProcess(S21ApiMessageMap msgMap, DS_CONTR_PRC_EFFTMsg prcEffTMsg, String lastBilledDt, String ccyCd) {

        NSZC047002PMsg mode02PMsg = (NSZC047002PMsg) msgMap.getPmsg();
        NSZC047001PMsg mode01PMsg = mapToMode01PMsg(msgMap, lastBilledDt);

        S21ApiMessageMap mode01MsgMap = new S21ApiMessageMap(mode01PMsg);
        M01CreateContractProcess mode01 = new M01CreateContractProcess(mode01MsgMap);

        String glblCmpyCd = mode02PMsg.glblCmpyCd.getValue();
        BigDecimal dsContrPrcEffPk = prcEffTMsg.dsContrPrcEffPk.getValue();

        BigDecimal seqNum = NSZC0470Query.getInstance().getSchdSmrySqNum(glblCmpyCd, dsContrPrcEffPk);
        if (seqNum == null) {
            seqNum = BigDecimal.ONE;
        } else {
            seqNum = seqNum.add(BigDecimal.ONE);
        }

        // ----------------------------------------
        // Insert Schedule Summary
        // ----------------------------------------
        List<CalcSchdSmryTermAndAmtForBaseBean> schdSmryBeanList = getSchdSmryBaseBeanList(mode02PMsg, prcEffTMsg, lastBilledDt, seqNum);
        setAdjAmt(mode01PMsg, prcEffTMsg, schdSmryBeanList, lastBilledDt, ccyCd);
        int i = 0;
        for (CalcSchdSmryTermAndAmtForBaseBean schdSmry : schdSmryBeanList) {
            // START 2016/02/19 T.Tomita [QC#3895, MOD]
            NSZC047001_xxBaseLineListPMsg linePMsg = setMode01BaseLinePMsg(mode01PMsg.xxBaseLineList.no(i), schdSmry);
            // END 2016/02/19 T.Tomita [QC#3895, MOD]
            DS_CONTR_BLLG_SCHD_SMRYTMsg schdSmryTMsg = mode01.createSchdSmryForBase(mode01MsgMap, linePMsg, prcEffTMsg, schdSmry);

            // ----------------------------------------
            // Insert Schedule
            // ----------------------------------------
            List<CalcSchdTermAndAmtLineBean> schdBeanList = mode01.calcSchdTermAndAmt(mode01MsgMap, linePMsg, schdSmryTMsg);
            for (CalcSchdTermAndAmtLineBean schdBean : schdBeanList) {
                mode01.createSchdForBase(mode01MsgMap, linePMsg, prcEffTMsg, schdSmryTMsg, schdBean);
            }
            i++;
        }
    }

    private void usageChargeProcess(S21ApiMessageMap msgMap, DS_CONTR_PRC_EFFTMsg prcEffTMsg, String lastBilledDt, String dsContrDtlTpCd) {

        NSZC047002PMsg mode02PMsg = (NSZC047002PMsg) msgMap.getPmsg();
        NSZC047001PMsg mode01PMsg = mapToMode01PMsg(msgMap, lastBilledDt);
        S21ApiMessageMap mode01MsgMap = new S21ApiMessageMap(mode01PMsg);

        NSZC047001_xxMtrLineListPMsg linePMsg = mode01PMsg.xxMtrLineList.no(0);
        NSZC047001_xxMtrLineListPMsgArray usageList = mode01PMsg.xxMtrLineList;

        M01CreateContractProcess mode01 = new M01CreateContractProcess(mode01MsgMap);

        String glblCmpyCd = mode02PMsg.glblCmpyCd.getValue();
        BigDecimal dsContrPrcEffPk = prcEffTMsg.dsContrPrcEffPk.getValue();

        BigDecimal seqNum = NSZC0470Query.getInstance().getSchdSmrySqNum(glblCmpyCd, dsContrPrcEffPk);
        if (seqNum == null) {
            seqNum = BigDecimal.ONE;
        } else {
            seqNum = seqNum.add(BigDecimal.ONE);
        }

        List<NSZC047001_xxMtrLineListPMsg> xsMtrList;

        BigDecimal bllgMtrPk = linePMsg.dsContrBllgMtrPk_ML.getValue();

        // ----------------------------------------
        // Insert Schedule Summary
        // ----------------------------------------
        List<CalcSchdSmryTermAndAmtForUsageBean> schdSmryBeanList = getSchdSmryUsageBeanList(mode02PMsg, prcEffTMsg, lastBilledDt, seqNum);

        for (CalcSchdSmryTermAndAmtForUsageBean schdSmry : schdSmryBeanList) {

            xsMtrList = getXsMtrList(bllgMtrPk, usageList, schdSmry);

            CalcSchdSmryTermAndAmtBean allowanceBean = mode01.calcAllowance(mode01MsgMap, schdSmry, xsMtrList);
            DS_CONTR_BLLG_SCHD_SMRYTMsg schdSmryTMsg = mode01.createSchdSmryForUsage(mode01MsgMap, linePMsg, prcEffTMsg, schdSmry, allowanceBean);

            // ----------------------------------------
            // Insert Schedule Meter
            // ----------------------------------------
            List<CalcSchdSmryTermAndAmtForUsageBean> allowBeanList = allowanceBean.getUsageList();
            for (CalcSchdSmryTermAndAmtForUsageBean allowBean : allowBeanList) {
                mode01.createSchdMtr(mode01MsgMap, schdSmryTMsg, allowBean);
            }

            // ----------------------------------------
            // Insert Schedule
            // ----------------------------------------
            List<CalcSchdTermAndAmtLineBean> schdBeanList = mode01.calcSchdTermAndAmt(mode01MsgMap, linePMsg, schdSmryTMsg);
            for (CalcSchdTermAndAmtLineBean schdBean : schdBeanList) {
                DS_CONTR_BLLG_SCHDTMsg schdTMsg = mode01.createSchdForUsage(mode01MsgMap, linePMsg, prcEffTMsg, schdSmryTMsg, schdBean);

                if (DS_CONTR_DTL_TP.FLEET.equals(dsContrDtlTpCd)) {
                    List<Map<String, Object>> fleetMachList = NSZC0470Query.getInstance().getFleetMachContrInfo(mode01PMsg.glblCmpyCd.getValue(), mode01PMsg.dsContrDtlPk.getValue()
                                                                                                    , schdTMsg.bllgSchdThruDt.getValue(), schdTMsg.bllgSchdFromDt.getValue()
                                                                                                    , schdTMsg.bllgSchdFromDt.getValue(), bllgMtrPk);
                    for (Map<String, Object> fleetMachInfo : fleetMachList) {
                        mode01.createSchdForFleetMachUsage(mode01MsgMap, linePMsg, prcEffTMsg, schdSmryTMsg, schdTMsg, schdBean, fleetMachInfo);
                    }
                }
            }
        }
    }

    private NSZC047001PMsg mapToMode01PMsg(S21ApiMessageMap msgMap, String lastBilledDt) {

        NSZC047002PMsg mode02PMsg = (NSZC047002PMsg) msgMap.getPmsg();
        NSZC047001PMsg mode01PMsg = new NSZC047001PMsg();

        EZDMsg.copy(mode02PMsg, null, mode01PMsg, null);

        int m01Idx = 0;

        for (int i = 0; i < mode02PMsg.xxBaseLineList.getValidCount(); i++) {
            NSZC047002_xxBaseLineListPMsg mode02LinePMsg = mode02PMsg.xxBaseLineList.no(i);

            String effFromDt = mode02LinePMsg.effFromDt_BL.getValue();
            String effThruDt = mode02LinePMsg.effThruDt_BL.getValue();
            String newEffFromDt = effFromDt;

            if (hasValue(lastBilledDt)) {
                if (effThruDt.compareTo(lastBilledDt) <= 0) {
                    continue;
                }
                if (effFromDt.compareTo(lastBilledDt) < 0 && lastBilledDt.compareTo(effThruDt) < 0) {
                    newEffFromDt = ZYPDateUtil.addDays(lastBilledDt, 1);
                }
            }
            NSZC047001_xxBaseLineListPMsg mode01LinePMsg = mode01PMsg.xxBaseLineList.no(m01Idx);
            EZDMsg.copy(mode02LinePMsg, null, mode01LinePMsg, null);
            setValue(mode01LinePMsg.effFromDt_BL, newEffFromDt);
            mode01PMsg.xxBaseLineList.setValidCount(m01Idx + 1);
            m01Idx++;
        }

        for (int i = 0; i < mode02PMsg.xxMtrLineList.getValidCount(); i++) {
            NSZC047002_xxMtrLineListPMsg mode02LinePMsg = mode02PMsg.xxMtrLineList.no(i);

            String effFromDt = mode02LinePMsg.effFromDt_ML.getValue();
            String effThruDt = mode02LinePMsg.effThruDt_ML.getValue();
            String newEffFromDt = effFromDt;

            if (hasValue(lastBilledDt)) {
                if (effThruDt.compareTo(lastBilledDt) <= 0) {
                    continue;
                }
                if (effFromDt.compareTo(lastBilledDt) < 0 && lastBilledDt.compareTo(effThruDt) < 0) {
                    newEffFromDt = ZYPDateUtil.addDays(lastBilledDt, 1);
                }
            }
            NSZC047001_xxMtrLineListPMsg mode01LinePMsg = mode01PMsg.xxMtrLineList.no(m01Idx);
            EZDMsg.copy(mode02LinePMsg, null, mode01LinePMsg, null);
            setValue(mode01LinePMsg.effFromDt_ML, newEffFromDt);
            mode01PMsg.xxMtrLineList.setValidCount(m01Idx + 1);
            m01Idx++;
        }

        return mode01PMsg;
    }

    private List<CalcSchdSmryTermAndAmtForBaseBean> getSchdSmryBaseBeanList(NSZC047002PMsg mode02PMsg, DS_CONTR_PRC_EFFTMsg prcEffTMsg, String lastBilledDt, BigDecimal seqNum) {

        List<CalcSchdSmryTermAndAmtForBaseBean> schdSmryBeanList = new ArrayList<CalcSchdSmryTermAndAmtForBaseBean>();

        CalcSchdSmryTermAndAmtForBaseBean schdSmryBean;
        for (int i = 0; i < mode02PMsg.xxBaseLineList.getValidCount(); i++) {
            NSZC047002_xxBaseLineListPMsg linePMsg = mode02PMsg.xxBaseLineList.no(i);

//            String bllgCycleCd = linePMsg.baseBllgCycleCd_BL.getValue();
            String effFromDt = linePMsg.effFromDt_BL.getValue();
            String effThruDt = linePMsg.effThruDt_BL.getValue();
            String newEffFromDt = effFromDt;
//            BigDecimal newPerSchdNum = linePMsg.perSchdNum_BL.getValue();

            if (hasValue(lastBilledDt)) {
                if (effThruDt.compareTo(lastBilledDt) <= 0) {
                    continue;
                }
                if (effFromDt.compareTo(lastBilledDt) < 0 && lastBilledDt.compareTo(effThruDt) < 0) {
                    newEffFromDt = ZYPDateUtil.addDays(lastBilledDt, 1);
//                    newPerSchdNum = getPerSchdNum(mode02PMsg.glblCmpyCd.getValue(), bllgCycleCd, mode02PMsg.contrCloDay.getValue(), lastBilledDt, effThruDt);
                }
            }

            // START 2016/02/19 T.Tomita [QC#3895, MOD]
            List<CalcSchdSmryTermAndAmtForBaseBean> schdSmryList = calcSchdSmryTermAndAmt(mode02PMsg, linePMsg, prcEffTMsg, newEffFromDt);
            for (CalcSchdSmryTermAndAmtForBaseBean schdSmry : schdSmryList) {
                schdSmryBean = new CalcSchdSmryTermAndAmtForBaseBean();
                schdSmryBean.setDsContrBllgSchdSqNum(seqNum.toString());
                schdSmryBean.setPerSchdNum(schdSmry.getPerSchdNum());
                schdSmryBean.setPerBllgCycleCd(schdSmry.getPerBllgCycleCd());
                schdSmryBean.setBllgSchdFromDt(schdSmry.getBllgSchdFromDt());
                schdSmryBean.setBllgSchdThruDt(schdSmry.getBllgSchdThruDt());
                schdSmryBean.setBaseBllgCycleCd(linePMsg.baseBllgCycleCd_BL.getValue());
                schdSmryBean.setBasePrcDealAmt(linePMsg.basePrcDealAmt_BL.getValue());

                BigDecimal baseSubTotPrcDealAmt = BigDecimal.ZERO;
                baseSubTotPrcDealAmt = calcTermAmt(mode02PMsg, schdSmryBean, prcEffTMsg);
                schdSmryBean.setBaseSubTotPrcDealAmt(baseSubTotPrcDealAmt);

                schdSmryBeanList.add(schdSmryBean);
                seqNum = seqNum.add(BigDecimal.ONE);
            }
            // END 2016/02/19 T.Tomita [QC#3895, MOD]
        }

        return schdSmryBeanList;
    }

    private List<CalcSchdSmryTermAndAmtForUsageBean> getSchdSmryUsageBeanList(NSZC047002PMsg mode02PMsg, DS_CONTR_PRC_EFFTMsg prcEffTMsg, String lastBilledDt, BigDecimal seqNum) {

        List<CalcSchdSmryTermAndAmtForUsageBean> schdSmryBeanList = new ArrayList<CalcSchdSmryTermAndAmtForUsageBean>();

        // Mod Start 09/06/2016 <QC#12881>
        String  targetFromDt = null;

        for (int i = 0; i < mode02PMsg.xxMtrLineList.getValidCount(); i++) {
            CalcSchdSmryTermAndAmtForUsageBean schdSmryBean = new CalcSchdSmryTermAndAmtForUsageBean();
            NSZC047002_xxMtrLineListPMsg linePMsg = mode02PMsg.xxMtrLineList.no(i);

            if (targetFromDt != null && targetFromDt.equals(linePMsg.effFromDt_ML.getValue())) {
                continue;
            }
            targetFromDt = linePMsg.effFromDt_ML.getValue();

            String bllgCycleCd = linePMsg.mtrBllgCycleCd_ML.getValue();
            String effFromDt = linePMsg.effFromDt_ML.getValue();
            String effThruDt = linePMsg.effThruDt_ML.getValue();
            String newEffFromDt = effFromDt;
            BigDecimal newPerSchdNum = linePMsg.perSchdNum_ML.getValue();

            if (hasValue(lastBilledDt)) {
                if (effThruDt.compareTo(lastBilledDt) <= 0) {
                    continue;
                }
                if (effFromDt.compareTo(lastBilledDt) < 0 && lastBilledDt.compareTo(effThruDt) < 0) {
                    newEffFromDt = ZYPDateUtil.addDays(lastBilledDt, 1);
                    newPerSchdNum = getPerSchdNum(mode02PMsg.glblCmpyCd.getValue(), bllgCycleCd, mode02PMsg.mtrCloDay.getValue(), lastBilledDt, effThruDt);
                }
            }

            schdSmryBean.setDsContrBllgMtrPk(linePMsg.dsContrBllgMtrPk_ML.getValue());
            schdSmryBean.setDsContrBllgSchdSqNum(seqNum.toString());
            schdSmryBean.setPerSchdNum(newPerSchdNum);
            schdSmryBean.setPerBllgCycleCd(linePMsg.perBllgCycleCd_ML.getValue());
            schdSmryBean.setBllgSchdFromDt(newEffFromDt);
            schdSmryBean.setBllgSchdThruDt(linePMsg.effThruDt_ML.getValue());
            schdSmryBean.setXsMtrCopyQty(linePMsg.contrXsCopyPk_ML.getValue());

            schdSmryBeanList.add(schdSmryBean);
            seqNum = seqNum.add(BigDecimal.ONE);
        }
        return schdSmryBeanList;
        // Mod End   09/06/2016 <QC#12881>
    }

    private BigDecimal getPerSchdNum(String glblCmpyCd, String bllgCycleCd, String cloDay, String fromDt, String thruDt) {

        BLLG_CYCLETMsg bllgCycleTMsg = NSZC0470Query.getInstance().getBllgCycleTMsg(glblCmpyCd, bllgCycleCd);

        Calendar calFromDt = NSZC047001CommonLogic.toCalendar(fromDt);
        Calendar calThruDt = NSZC047001CommonLogic.toCalendar(thruDt);
        int diffMonth = NSZC047001CommonLogic.getDiffMonths(calThruDt, calFromDt, cloDay);
        int iPerSchdNum = diffMonth / bllgCycleTMsg.bllgCycleMthAot.getValueInt();
        BigDecimal perSchdNum = new BigDecimal(iPerSchdNum);

        return perSchdNum;
    }

    private void setAdjAmt(NSZC047001PMsg mode01PMsg, DS_CONTR_PRC_EFFTMsg prcEffTMsg, List<CalcSchdSmryTermAndAmtForBaseBean> schdSmryBeanList, String lastBilledDt, String ccyCd) {

        int digitNum = 2;
        CCYTMsg ccyTMsg = NSZC0470Query.getInstance().getCcy(mode01PMsg.glblCmpyCd.getValue(), ccyCd);
        if (ccyTMsg != null) {
            digitNum = ccyTMsg.aftDeclPntDigitNum.getValueInt();
        }

        List<NSZC047002_xxBaseLineListPMsg> sortList = new ArrayList<NSZC047002_xxBaseLineListPMsg>();

        for (CalcSchdSmryTermAndAmtForBaseBean schdSmryBean : schdSmryBeanList) {
            NSZC047002_xxBaseLineListPMsg sortPMsg = new NSZC047002_xxBaseLineListPMsg();
            setValue(sortPMsg.perSchdNum_BL, schdSmryBean.getPerSchdNum());
            setValue(sortPMsg.perBllgCycleCd_BL, schdSmryBean.getPerBllgCycleCd());
            setValue(sortPMsg.effFromDt_BL, schdSmryBean.getBllgSchdFromDt());
            setValue(sortPMsg.effThruDt_BL, schdSmryBean.getBllgSchdThruDt());
            setValue(sortPMsg.basePrcTermDealAmtRate_BL, schdSmryBean.getBaseSubTotPrcDealAmt());
            sortList.add(sortPMsg);
        }

        NSZC047001CommonLogic.sortMode02(sortList, "basePrcTermDealAmtRate_BL", 0);

        BigDecimal ttlDailyPerSchdNum = getTtlDailyPerSchdNum(sortList);

        BigDecimal termAmt = prcEffTMsg.basePrcTermDealAmtRate.getValue();
        BigDecimal unBilledAmt = getUnBilledAmt(schdSmryBeanList);
        BigDecimal billedAmt = BigDecimal.ZERO;
        if (hasValue(lastBilledDt)) {
            Map<String, BigDecimal> basePrcInfo = NSZC0470Query.getInstance().getSummaryBasePrcAmt(mode01PMsg.glblCmpyCd.getValue()
                                                                    , mode01PMsg.dsContrDtlPk.getValue()
                                                                    , null
                                                                    , prcEffTMsg.contrPrcEffFromDt.getValue()
                                                                    , lastBilledDt
                                                                    , mode01PMsg.baseChrgFlg.getValue());
            if (basePrcInfo != null) {
                billedAmt = basePrcInfo.get("BASE_ACTL_PRC_DEAL_AMT");
            }
        }

        BigDecimal ttlAdjAmt = termAmt.subtract(billedAmt.add(unBilledAmt));
        // Adjust base price amount
        for (NSZC047002_xxBaseLineListPMsg sortPMsg : sortList) {

            if (ttlAdjAmt.compareTo(BigDecimal.ZERO) == 0) {
                break;
            }

            String bllgCycleCd = sortPMsg.perBllgCycleCd_BL.getValue();
            String effFromDt = sortPMsg.effFromDt_BL.getValue();
            BigDecimal perSchdNum = sortPMsg.perSchdNum_BL.getValue();
            BigDecimal subTotPrcDealAmt = sortPMsg.basePrcTermDealAmtRate_BL.getValue();
            BigDecimal adjAmt = BigDecimal.ZERO;

            if (BLLG_CYCLE.DAILY.equals(bllgCycleCd)) {
                adjAmt = ttlAdjAmt.multiply(perSchdNum.divide(ttlDailyPerSchdNum, digitNum, BigDecimal.ROUND_HALF_UP)).setScale(digitNum, BigDecimal.ROUND_HALF_UP);

                if (subTotPrcDealAmt.add(adjAmt).compareTo(BigDecimal.ZERO) < 0) {
                    BigDecimal tmpAdjAmt = subTotPrcDealAmt.negate();
                    setAdjAmt(schdSmryBeanList, effFromDt, BigDecimal.ZERO, subTotPrcDealAmt.negate());
                    ttlAdjAmt = ttlAdjAmt.subtract(tmpAdjAmt);
                } else {
                    BigDecimal sumAmt = subTotPrcDealAmt.add(adjAmt);
                    ttlAdjAmt = ttlAdjAmt.subtract(adjAmt);
                    setAdjAmt(schdSmryBeanList, effFromDt, sumAmt, adjAmt);
                }
            } else {
                adjAmt = ttlAdjAmt;
                if (subTotPrcDealAmt.add(adjAmt).compareTo(BigDecimal.ZERO) < 0) {
                    BigDecimal tmpAdjAmt = subTotPrcDealAmt.negate();
                    setAdjAmt(schdSmryBeanList, effFromDt, BigDecimal.ZERO, subTotPrcDealAmt.negate());
                    ttlAdjAmt = ttlAdjAmt.subtract(tmpAdjAmt);
                } else {
                    BigDecimal sumAmt = subTotPrcDealAmt.add(adjAmt);
                    ttlAdjAmt = ttlAdjAmt.subtract(adjAmt);
                    setAdjAmt(schdSmryBeanList, effFromDt, sumAmt, adjAmt);
                }
            }
        }
    }

    private BigDecimal getTtlDailyPerSchdNum(List<NSZC047002_xxBaseLineListPMsg> sortList) {

        BigDecimal ttlDailyPerSchdNum = BigDecimal.ZERO;

        for (NSZC047002_xxBaseLineListPMsg sortPMsg : sortList) {

            if (BLLG_CYCLE.DAILY.equals(sortPMsg.perBllgCycleCd_BL.getValue())) {
                ttlDailyPerSchdNum = ttlDailyPerSchdNum.add(sortPMsg.perSchdNum_BL.getValue());
            }
        }

        return ttlDailyPerSchdNum;
    }

    private BigDecimal getUnBilledAmt(List<CalcSchdSmryTermAndAmtForBaseBean> schdSmryBeanList) {

        BigDecimal unBilledAmt = BigDecimal.ZERO;

        for (CalcSchdSmryTermAndAmtForBaseBean schdSmryBean : schdSmryBeanList) {
            unBilledAmt = unBilledAmt.add(schdSmryBean.getBaseSubTotPrcDealAmt());
        }
        return unBilledAmt;
    }

    private void setAdjAmt(List<CalcSchdSmryTermAndAmtForBaseBean> schdSmryBeanList, String effFromDt, BigDecimal subTotPrcDealAmt, BigDecimal adjAmt) {

        for (CalcSchdSmryTermAndAmtForBaseBean schdSmryBean : schdSmryBeanList) {

            if (effFromDt.equals(schdSmryBean.getBllgSchdFromDt())) {
                schdSmryBean.setBaseSubTotPrcDealAmt(subTotPrcDealAmt);
                schdSmryBean.setAdjAmt(adjAmt);
                break;
            }
        }
    }

    private List<NSZC047001_xxMtrLineListPMsg> getXsMtrList(BigDecimal bllgMtrPk, NSZC047001_xxMtrLineListPMsgArray usageList, CalcSchdSmryTermAndAmtForUsageBean schdSmry) {

        List<NSZC047001_xxMtrLineListPMsg> xsMtrList = new ArrayList<NSZC047001_xxMtrLineListPMsg>();

        String effFromDt = schdSmry.getBllgSchdFromDt();

        for (int i = 0; i < usageList.getValidCount(); i++) {

            NSZC047001_xxMtrLineListPMsg linePMsg = usageList.no(i);

            if (bllgMtrPk.compareTo(linePMsg.dsContrBllgMtrPk_ML.getValue()) == 0
                    && effFromDt.equals(linePMsg.effFromDt_ML.getValue())) {
                xsMtrList.add(linePMsg);
            }
        }
        return xsMtrList;
    }

    // START 2016/02/19 T.Tomita [QC#3895, MOD]
//    private BigDecimal calcTermAmt(NSZC047002PMsg mode02PMsg, NSZC047002_xxBaseLineListPMsg linePMsg, DS_CONTR_PRC_EFFTMsg prcEffTMsg) {
//        CalcTermAmtBean inBean = new CalcTermAmtBean();
//        inBean.setGlblCmpyCd(mode02PMsg.glblCmpyCd.getValue());
//        inBean.setContrPrcEffFromDt(linePMsg.effFromDt_BL.getValue());
//        inBean.setContrPrcEffThruDt(linePMsg.effThruDt_BL.getValue());
//        inBean.setContrCloDay(mode02PMsg.contrCloDay.getValue());
//        inBean.setBllgCycleCd(linePMsg.baseBllgCycleCd_BL.getValue());
//        inBean.setBasePrcDealAmt(linePMsg.basePrcDealAmt_BL.getValue());
//        inBean.setCcyCd(prcEffTMsg.ccyCd.getValue());
//        return NSXC003001CalcTermAmt.calcTermAmt(inBean);
//    }

    private List<CalcSchdSmryTermAndAmtForBaseBean> calcSchdSmryTermAndAmt(NSZC047002PMsg mode02PMsg, NSZC047002_xxBaseLineListPMsg linePMsg, DS_CONTR_PRC_EFFTMsg prcEffTMsg, String schdFromDt) {
        CalcSchdSmryTermAndAmtBean inBean = new CalcSchdSmryTermAndAmtBean();
        inBean.setGlblCmpyCd(mode02PMsg.glblCmpyCd.getValue());
        inBean.setBllgSchdFromDt(schdFromDt);
        inBean.setBllgSchdThruDt(linePMsg.effThruDt_BL.getValue());
        inBean.setBllgCycleCd(linePMsg.baseBllgCycleCd_BL.getValue());
        inBean.setContrCloDay(mode02PMsg.contrCloDay.getValue());
        inBean.setBasePrcDealAmt(linePMsg.basePrcDealAmt_BL.getValue());
        inBean.setBaseChrgFlg(FLG_ON_Y);
        inBean.setUsgChrgFlg(FLG_OFF_N);
        inBean.setCcyCd(prcEffTMsg.ccyCd.getValue());

        CalcSchdSmryTermAndAmtBean outBean = NSXC003001CalcSchdSmryTermAndAmt.calcSchdSmryTermAndAmt(inBean);
        return outBean.getBaseList();
    }

    private BigDecimal calcTermAmt(NSZC047002PMsg mode02PMsg, CalcSchdSmryTermAndAmtForBaseBean schdSmryBean, DS_CONTR_PRC_EFFTMsg prcEffTMsg) {
        CalcTermAmtBean inBean = new CalcTermAmtBean();
        inBean.setGlblCmpyCd(mode02PMsg.glblCmpyCd.getValue());
        inBean.setContrPrcEffFromDt(schdSmryBean.getBllgSchdFromDt());
        inBean.setContrPrcEffThruDt(schdSmryBean.getBllgSchdThruDt());
        inBean.setContrCloDay(mode02PMsg.contrCloDay.getValue());
        inBean.setBllgCycleCd(schdSmryBean.getBaseBllgCycleCd());
        inBean.setBasePrcDealAmt(schdSmryBean.getBasePrcDealAmt());
        inBean.setCcyCd(prcEffTMsg.ccyCd.getValue());
        return NSXC003001CalcTermAmt.calcTermAmt(inBean);
    }

    private NSZC047001_xxBaseLineListPMsg setMode01BaseLinePMsg(NSZC047001_xxBaseLineListPMsg linePMsg, CalcSchdSmryTermAndAmtForBaseBean schdSmry) {
        setValue(linePMsg.baseBllgCycleCd_BL, schdSmry.getBaseBllgCycleCd());
        setValue(linePMsg.basePrcDealAmt_BL, schdSmry.getBasePrcDealAmt());
        return linePMsg;
    }
    // END 2016/02/19 T.Tomita [QC#3895, MOD]
}
