/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC047001;

import static com.canon.cusa.s21.api.NSZ.NSZC047001.constant.NSZC047001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.BLLG_CYCLETMsg;
import business.db.DS_CONTR_BLLG_SCHDTMsg;
import business.db.DS_CONTR_BLLG_SCHD_MTRTMsg;
import business.db.DS_CONTR_BLLG_SCHD_SMRYTMsg;
import business.db.DS_CONTR_PRC_EFFTMsg;
import business.db.DS_CONTR_PRC_EFF_MTRTMsg;
import business.parts.NSZC047001PMsg;
import business.parts.NSZC047001_xxBaseLineListPMsg;
import business.parts.NSZC047001_xxBaseLineListPMsgArray;
import business.parts.NSZC047001_xxMtrLineListPMsg;
import business.parts.NSZC047001_xxMtrLineListPMsgArray;

import com.canon.cusa.s21.common.NSX.NSXC003001.CalcNextBllgDtBean;
import com.canon.cusa.s21.common.NSX.NSXC003001.CalcRvsSchdBean;
import com.canon.cusa.s21.common.NSX.NSXC003001.CalcRvsSchdLineBean;
import com.canon.cusa.s21.common.NSX.NSXC003001.CalcSchdSmryTermAndAmtBean;
import com.canon.cusa.s21.common.NSX.NSXC003001.CalcSchdSmryTermAndAmtForBaseBean;
import com.canon.cusa.s21.common.NSX.NSXC003001.CalcSchdSmryTermAndAmtForUsageBean;
import com.canon.cusa.s21.common.NSX.NSXC003001.CalcSchdTermAndAmtBean;
import com.canon.cusa.s21.common.NSX.NSXC003001.CalcSchdTermAndAmtLineBean;
import com.canon.cusa.s21.common.NSX.NSXC003001.NSXC003001CalcNextBllgDt;
import com.canon.cusa.s21.common.NSX.NSXC003001.NSXC003001CalcRvsSchdDt;
import com.canon.cusa.s21.common.NSX.NSXC003001.NSXC003001CalcSchdSmryTermAndAmt;
import com.canon.cusa.s21.common.NSX.NSXC003001.NSXC003001CalcSchdTermAndAmt;
import com.canon.cusa.s21.common.NSX.NSXC003001.NSXC003001SvcPhysMtrRead;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BLLG_CYCLE;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BLLG_CYCLE_UOM;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_BLLG_SCHD_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_BLLG_SCHD_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SKIP_RECOV_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_INV_CHRG_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;

/**
 * <pre>
 * Contract Billing Schedule API
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/14/2015   Hitachi         T.Aoyagi        Create          N/A
 * 01/18/2016   Hitachi         T.Tomita        Update          QC#1088
 * 01/25/2016   Hitachi         T.Aoyagi        Update          QC#3623
 * 01/28/2016   Hitachi         T.Aoyagi        Update          QC#3095
 * 02/18/2016   Hitachi         T.Tomita        Update          QC#3894
 * 03/29/2016   Hitachi         K.Kishimoto     Update          QC#5863
 * 04/18/2016   Hitachi         T.Aoyagi        Update          QC#7056
 * 06/08/2016   Hitachi         T.Aoyagi        Update          QC#9524
 * 06/23/2016   Hitachi         T.Kanasaka      Update          QC#10524
 * 06/29/2016   Hitachi         K.Kishimoto     Update          QC#7402
 * 07/20/2017   Hitachi         K.Yamada        Update          QC#20015
 * 07/21/2017   Hitachi         T.Tomita        Update          QC#20045
 * 12/21/2017   Hitachi         U.Kim           Update          QC#22282
 * 07/12/2018   Hitachi         K.Kishimoto     Update          QC#25959
 * 2018/12/10   Hitachi         K.Kitachi       Update          QC#29387
 * 2019/02/14   Hitachi         K.Kitachi       Update          QC#30066
 * 2020/03/18   Hitachi         K.Kitachi       Update          QC#55693
 * 2022/06/08   CITS            R.Jin           Update          QC#57803
 * 2022/06/16   CITS            R.Jin           Update          QC#60217
 * </pre>
 */
public class M01CreateContractProcess implements ZYPConstant {

    /** dsContrDtlTp */
    private String dsContrDtlTp;

    /** contrInfoList */
    private List<Map<String, Object>> contrInfoList;

    protected M01CreateContractProcess(S21ApiMessageMap msgMap) {
        NSZC047001PMsg pMsg = (NSZC047001PMsg) msgMap.getPmsg();
        this.contrInfoList = NSZC0470Query.getInstance().getContrInfo(pMsg.glblCmpyCd.getValue(), pMsg.dsContrDtlPk.getValue());
        // Add Start 06/29/2016 <QC#7402>
        if (this.contrInfoList.isEmpty()) {
            msgMap.addXxMsgIdWithPrm(ZZZM9006E, new String[]{"Contract information"});
            return;
        } else {
            this.dsContrDtlTp = (String) this.contrInfoList.get(0).get("DS_CONTR_DTL_TP_CD");
        }
        // Add End  06/29/2016 <QC#7402>
    }

    protected void doProcess(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {

        checkParameter(msgMap);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }
        createContractProcess(msgMap);
    }

    private void createContractProcess(S21ApiMessageMap msgMap) {

        NSZC047001PMsg pMsg = (NSZC047001PMsg) msgMap.getPmsg();
        NSZC047001_xxBaseLineListPMsgArray baseList = pMsg.xxBaseLineList;
        NSZC047001_xxMtrLineListPMsgArray usageList = pMsg.xxMtrLineList;

        setValue(pMsg.contrCloDay, NSZC047001CommonLogic.convCloDay(pMsg.contrCloDay.getValue()));
        setValue(pMsg.mtrCloDay, NSZC047001CommonLogic.convCloDay(pMsg.mtrCloDay.getValue()));

        baseChargeProcess(msgMap, baseList);
        usageChargeProcess(msgMap, usageList);
        // START 2016/06/08 T.Aoyagi [QC#9524, ADD]
        if (usageList.getValidCount() > 0) {
            NSZC047001CommonLogic.mtrEntryStsUpd(msgMap,pMsg.glblCmpyCd.getValue(), pMsg.slsDt.getValue(), (BigDecimal) contrInfoList.get(0).get("DS_CONTR_PK"));
            // START 2020/03/18 K.Kitachi [QC#55693, ADD]
            NSZC047001CommonLogic.resetXsCopyPk(msgMap, pMsg.glblCmpyCd.getValue(), (BigDecimal) contrInfoList.get(0).get("DS_CONTR_PK"));
            // END 2020/03/18 K.Kitachi [QC#55693, ADD]
        }
        // END 2016/06/08 T.Aoyagi [QC#9524, ADD]
    }

    /**
     * @param msgMap
     * @param baseList
     */
    private void baseChargeProcess(S21ApiMessageMap msgMap, NSZC047001_xxBaseLineListPMsgArray baseList) {

        for (int i = 0; i < baseList.getValidCount(); i++) {

            NSZC047001_xxBaseLineListPMsg linePMsg = baseList.no(i);

            // ----------------------------------------
            // Insert Price Effectivity
            // ----------------------------------------
            DS_CONTR_PRC_EFFTMsg prcEffTMsg = createPrcEffForBase(msgMap, linePMsg);

            // ----------------------------------------
            // Insert Schedule Summary
            // ----------------------------------------
            List<CalcSchdSmryTermAndAmtForBaseBean> schdSmryBeanList
                    = calcSchdSmryTermAndAmt(msgMap, linePMsg);

            if (schdSmryBeanList != null) {
                for (CalcSchdSmryTermAndAmtForBaseBean schdSmry : schdSmryBeanList) {

                    DS_CONTR_BLLG_SCHD_SMRYTMsg schdSmryTMsg = createSchdSmryForBase(msgMap, linePMsg, prcEffTMsg, schdSmry);
                    // START 2016/01/28 T.Aoyagi [CSA-QC#3095,ADD]
                    if (schdSmryTMsg == null) {
                        continue;
                    }
                    // END   2016/01/28 T.Aoyagi [CSA-QC#3095,ADD]

                    // ----------------------------------------
                    // Insert Schedule
                    // ----------------------------------------
                    List<CalcSchdTermAndAmtLineBean> schdBeanList = calcSchdTermAndAmt(msgMap, linePMsg, schdSmryTMsg);
                    for (CalcSchdTermAndAmtLineBean schdBean : schdBeanList) {
                        createSchdForBase(msgMap, linePMsg, prcEffTMsg, schdSmryTMsg, schdBean);
                    }
                }
            }

            if (!hasValue(linePMsg.basePrcTermDealAmtRate_BL)) {
                NSZC047001CommonLogic.updateTermAmtForPrcEff(msgMap, prcEffTMsg, schdSmryBeanList);
            }
        }
    }

    /**
     * @param msgMap
     * @param usageList
     */
    private void usageChargeProcess(S21ApiMessageMap msgMap, NSZC047001_xxMtrLineListPMsgArray usageList) {

        NSZC047001PMsg pMsg = (NSZC047001PMsg) msgMap.getPmsg();
        BigDecimal preBllgMtrPk = null;
        List<NSZC047001_xxMtrLineListPMsg> xsMtrList;

        for (int i = 0; i < usageList.getValidCount(); i++) {

            NSZC047001_xxMtrLineListPMsg linePMsg = usageList.no(i);
            BigDecimal bllgMtrPk = linePMsg.dsContrBllgMtrPk_ML.getValue();

            if (hasValue(preBllgMtrPk) && preBllgMtrPk.compareTo(bllgMtrPk) == 0) {
                continue;
            }

            preBllgMtrPk = bllgMtrPk;
            xsMtrList = getXsMtrList(bllgMtrPk, usageList);

            // ----------------------------------------
            // Insert Price Effectivity
            // ----------------------------------------
            DS_CONTR_PRC_EFFTMsg prcEffTMsg = createPrcEffForUsage(msgMap, linePMsg);

            // ----------------------------------------
            // Insert Price Effectivity Meter
            // ----------------------------------------
            for (NSZC047001_xxMtrLineListPMsg xsMtrPMsg : xsMtrList) {
                createPrcEffMtr(msgMap, xsMtrPMsg, prcEffTMsg);
            }

            // ----------------------------------------
            // Insert Schedule Summary
            // ----------------------------------------
            List<CalcSchdSmryTermAndAmtForUsageBean> schdSmryBeanList = calcSchdSmryTermAndAmt(msgMap, linePMsg);

            if (schdSmryBeanList != null) {

                for (CalcSchdSmryTermAndAmtForUsageBean schdSmry : schdSmryBeanList) {

                    CalcSchdSmryTermAndAmtBean allowanceBean = calcAllowance(msgMap, schdSmry, xsMtrList);
                    DS_CONTR_BLLG_SCHD_SMRYTMsg schdSmryTMsg = createSchdSmryForUsage(msgMap, linePMsg, prcEffTMsg, schdSmry, allowanceBean);
                    // START 2016/01/28 T.Aoyagi [CSA-QC#3095,ADD]
                    if (schdSmryTMsg == null) {
                        continue;
                    }
                    // END   2016/01/28 T.Aoyagi [CSA-QC#3095,ADD]

                    List<CalcSchdSmryTermAndAmtForUsageBean> allowBeanList = allowanceBean.getUsageList();
                    for (CalcSchdSmryTermAndAmtForUsageBean allowBean : allowBeanList) {
                        createSchdMtr(msgMap, schdSmryTMsg, allowBean);
                    }

                    // ----------------------------------------
                    // Insert Schedule
                    // ----------------------------------------
                    List<CalcSchdTermAndAmtLineBean> schdBeanList = calcSchdTermAndAmt(msgMap, linePMsg, schdSmryTMsg);
                    for (CalcSchdTermAndAmtLineBean schdBean : schdBeanList) {
                        DS_CONTR_BLLG_SCHDTMsg schdTMsg = createSchdForUsage(msgMap, linePMsg, prcEffTMsg, schdSmryTMsg, schdBean);
                        // START 2016/01/28 T.Aoyagi [CSA-QC#3095,ADD]
                        if (schdTMsg == null) {
                            continue;
                        }
                        // END   2016/01/28 T.Aoyagi [CSA-QC#3095,ADD]

                        if (DS_CONTR_DTL_TP.FLEET.equals(this.dsContrDtlTp)) {
                            List<Map<String, Object>> fleetMachList = NSZC0470Query.getInstance().getFleetMachContrInfo(pMsg.glblCmpyCd.getValue(), pMsg.dsContrDtlPk.getValue()
                                                                                                            , schdTMsg.bllgSchdThruDt.getValue(), schdTMsg.bllgSchdFromDt.getValue()
                                                                                                            , schdTMsg.bllgSchdFromDt.getValue(), bllgMtrPk);
                            for (Map<String, Object> fleetMachInfo : fleetMachList) {
                                createSchdForFleetMachUsage(msgMap, linePMsg, prcEffTMsg, schdSmryTMsg, schdTMsg, schdBean, fleetMachInfo);
                            }
                        }
                    }
                }
            }
        }
    }

    private void checkParameter(S21ApiMessageMap msgMap) {
        NSZC047001PMsg pMsg = (NSZC047001PMsg) msgMap.getPmsg();
        NSZC047001CommonLogic.mandatoryCheck(msgMap, pMsg.glblCmpyCd, ZZZM9007E, new String[]{"Global Company Code"});
        NSZC047001CommonLogic.mandatoryCheck(msgMap, pMsg.xxModeCd, ZZZM9007E, new String[]{"Mode Code"});
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

    private List<NSZC047001_xxMtrLineListPMsg> getXsMtrList(BigDecimal bllgMtrPk, NSZC047001_xxMtrLineListPMsgArray usageList) {

        List<NSZC047001_xxMtrLineListPMsg> xsMtrList = new ArrayList<NSZC047001_xxMtrLineListPMsg>();

        for (int i = 0; i < usageList.getValidCount(); i++) {

            NSZC047001_xxMtrLineListPMsg linePMsg = usageList.no(i);

            if (bllgMtrPk.compareTo(linePMsg.dsContrBllgMtrPk_ML.getValue()) == 0) {
                xsMtrList.add(linePMsg);
            }
        }
        return xsMtrList;
    }

    protected DS_CONTR_PRC_EFFTMsg createPrcEffForBase(S21ApiMessageMap msgMap
                                                            , NSZC047001_xxBaseLineListPMsg linePMsg) {


        NSZC047001PMsg pMsg = (NSZC047001PMsg) msgMap.getPmsg();

        BigDecimal dsContrPrcEffPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CONTR_PRC_EFF_SQ);
        BigDecimal dsContrPrcEffSqNum = linePMsg.dsContrPrcEffSqNum_BL.getValue();
        if (!hasValue(dsContrPrcEffSqNum)) {
            dsContrPrcEffSqNum = BigDecimal.ONE;
        }

        DS_CONTR_PRC_EFFTMsg inTMsg = new DS_CONTR_PRC_EFFTMsg();
        setValue(inTMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(inTMsg.dsContrPrcEffPk, dsContrPrcEffPk);
        setValue(inTMsg.dsContrPrcEffSqNum, dsContrPrcEffSqNum);
        setValue(inTMsg.dsContrDtlPk, pMsg.dsContrDtlPk);
        inTMsg.dsContrBllgMtrPk.clear();
        setValue(inTMsg.contrPrcEffFromDt, pMsg.contrEffFromDt);
        setValue(inTMsg.contrPrcEffThruDt, pMsg.contrEffThruDt);
        setValue(inTMsg.bllgCycleCd, linePMsg.baseBllgCycleCd_BL);
        setValue(inTMsg.basePrcDealAmt, linePMsg.basePrcDealAmt_BL);
        inTMsg.basePrcFuncAmt.clear();
        setValue(inTMsg.basePrcTermDealAmtRate, linePMsg.basePrcTermDealAmtRate_BL);
        inTMsg.basePrcTermFuncAmtRate.clear();
        setValue(inTMsg.ccyCd, (String) this.contrInfoList.get(0).get("CCY_CD"));
        setValue(inTMsg.baseChrgFlg, FLG_ON_Y);
        setValue(inTMsg.usgChrgFlg, FLG_OFF_N);
        if (!hasValue(linePMsg.dsContrPrcEffPk_BL)) {
            setValue(inTMsg.dsContrPrcEffStsCd, (String) this.contrInfoList.get(0).get("DS_CONTR_DTL_STS_CD"));
            inTMsg.contrRnwErrRsnCd.clear();
            // Mod Start 06/29/2016 <QC#7402>
            setValue(inTMsg.qltyAsrnHldFlg, (String) this.contrInfoList.get(0).get("QLTY_ASRN_HLD_FLG"));
            // START 2017/12/21 U.Kim [QC#22282, MOD]
            // setValue(inTMsg.mtrHldFlg, (String) this.contrInfoList.get(0).get("MTR_HLD_FLG"));
            setValue(inTMsg.mtrHldFlg, ZYPConstant.FLG_OFF_N);
            // END 2017/12/21 U.Kim [QC#22282, MOD]
            setValue(inTMsg.contrHldFlg, (String) this.contrInfoList.get(0).get("CONTR_HLD_FLG"));
            setValue(inTMsg.bllgHldFlg, (String) this.contrInfoList.get(0).get("BLLG_HLD_FLG"));
            setValue(inTMsg.qltyAsrnHldPendApvlFlg, (String) this.contrInfoList.get(0).get("QLTY_ASRN_HLD_PEND_APVL_FLG"));
            // Mod End   06/29/2016 <QC#7402>
        } else {
            // START 2022/06/16 R.Jin <QC#60217,MOD>
//            setValue(inTMsg.dsContrPrcEffStsCd, linePMsg.dsContrPrcEffStsCd_BL);
            if (DS_CONTR_DTL_STS.ACTIVE.equals(linePMsg.dsContrPrcEffStsCd_BL.getValue()) && ZYPCommonFunc.hasValue(linePMsg.effFromDt_BL) && ZYPDateUtil.compare(linePMsg.effFromDt_BL.getValue(), pMsg.slsDt.getValue()) > 0) {
                setValue(inTMsg.dsContrPrcEffStsCd, DS_CONTR_DTL_STS.SIGNED);
            } else if(DS_CONTR_DTL_STS.SIGNED.equals(linePMsg.dsContrPrcEffStsCd_BL.getValue()) && ZYPCommonFunc.hasValue(linePMsg.effFromDt_BL) && ZYPDateUtil.compare(linePMsg.effFromDt_BL.getValue(), pMsg.slsDt.getValue()) <= 0) {
                setValue(inTMsg.dsContrPrcEffStsCd, DS_CONTR_DTL_STS.ACTIVE);
            } else {
                setValue(inTMsg.dsContrPrcEffStsCd, linePMsg.dsContrPrcEffStsCd_BL);
            }
            // END 2022/06/16 R.Jin <QC#60217,MOD>
            inTMsg.contrRnwErrRsnCd.clear();
            setValue(inTMsg.qltyAsrnHldFlg, linePMsg.qltyAsrnHldFlg_BL);
            // START 2017/12/21 U.Kim [QC#22282, MOD]
            // setValue(inTMsg.mtrHldFlg, linePMsg.mtrHldFlg_BL);
            setValue(inTMsg.mtrHldFlg, ZYPConstant.FLG_OFF_N);
            // END 2017/12/21 U.Kim [QC#22282, MOD]
            setValue(inTMsg.contrHldFlg, linePMsg.contrHldFlg_BL);
            setValue(inTMsg.bllgHldFlg, linePMsg.bllgHldFlg_BL);
            // Mod Start 07/20/2017 QC#20015
            // Add Start 06/29/2016 <QC#7402>
            if (hasValue(linePMsg.qltyAsrnHldPendApvlFlg_BL)) {
                setValue(inTMsg.qltyAsrnHldPendApvlFlg, linePMsg.qltyAsrnHldPendApvlFlg_BL);
            } else {
                setValue(inTMsg.qltyAsrnHldPendApvlFlg, ZYPConstant.FLG_OFF_N);
            }
            // Add End   06/29/2016 <QC#7402>
            // Mod End 07/20/2017 QC#20015
        }
        // Del Start 06/29/2016 <QC#7402>
        // START 2016/01/18 T.Tomita [QC#1088, ADD]
//        setValue(inTMsg.qltyAsrnHldPendApvlFlg, ZYPConstant.FLG_OFF_N);
        // END 2016/01/18 T.Tomita [QC#1088, ADD]
        // Del End   06/29/2016 <QC#7402>
        // Add Start 06/29/2016 <QC#7402>
        if (FLG_OFF_N.equals(pMsg.xxRqstFlg.getValue())) {
            return inTMsg;
        }
        // Add End   06/29/2016 <QC#7402>
        S21ApiTBLAccessor.insert(inTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
            msgMap.addXxMsgIdWithPrm(NSAM0032E, new String[]{"DS_CONTR_PRC_EFF"});
            return null;
        }
        return inTMsg;
    }

    protected DS_CONTR_BLLG_SCHD_SMRYTMsg createSchdSmryForBase(S21ApiMessageMap msgMap
                                                                        , NSZC047001_xxBaseLineListPMsg linePMsg
                                                                        , DS_CONTR_PRC_EFFTMsg tMsg
                                                                        , CalcSchdSmryTermAndAmtForBaseBean schdSmryBean) {
        NSZC047001PMsg pMsg = (NSZC047001PMsg) msgMap.getPmsg();
        BigDecimal dsContrBllgSchedSmryPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CONTR_BLLG_SCHD_SMRY_SQ);

        DS_CONTR_BLLG_SCHD_SMRYTMsg inTMsg = new DS_CONTR_BLLG_SCHD_SMRYTMsg();
        setValue(inTMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(inTMsg.dsContrBllgSchdSmryPk, dsContrBllgSchedSmryPk);
        setValue(inTMsg.dsContrDtlPk, pMsg.dsContrDtlPk);
        setValue(inTMsg.dsContrPrcEffPk, tMsg.dsContrPrcEffPk);
        setValue(inTMsg.dsContrPrcEffSqNum, tMsg.dsContrPrcEffSqNum);
        inTMsg.dsContrBllgMtrPk.clear();
        setValue(inTMsg.dsContrBllgSchdSqNum, schdSmryBean.getDsContrBllgSchdSqNum());
        setValue(inTMsg.perSchdNum, schdSmryBean.getPerSchdNum());
        setValue(inTMsg.perBllgCycleCd, schdSmryBean.getPerBllgCycleCd());
        setValue(inTMsg.bllgSchdFromDt, schdSmryBean.getBllgSchdFromDt());
        setValue(inTMsg.bllgSchdThruDt, schdSmryBean.getBllgSchdThruDt());
        setValue(inTMsg.bllgCycleCd, linePMsg.baseBllgCycleCd_BL);
        setValue(inTMsg.basePrcDealAmt, linePMsg.basePrcDealAmt_BL);
        inTMsg.basePrcFuncAmt.clear();
        setValue(inTMsg.basePrcDealAdjAmt, schdSmryBean.getAdjAmt());
        inTMsg.basePrcFuncAdjAmt.clear();
        setValue(inTMsg.baseSubTotPrcDealAmt, schdSmryBean.getBaseSubTotPrcDealAmt());
        inTMsg.baseSubTotPrcFuncAmt.clear();
        setValue(inTMsg.ccyCd, (String) this.contrInfoList.get(0).get("CCY_CD"));
        inTMsg.mlyCopyInclPrcQty.clear();
        inTMsg.dsContrBllgSchdTrmnDt.clear();
        setValue(inTMsg.baseChrgFlg, FLG_ON_Y);
        setValue(inTMsg.usgChrgFlg, FLG_OFF_N);
        inTMsg.origBllgSchdSmryNum.clear();

        BigDecimal prntDsContrDtlPk = null;
        if (DS_CONTR_CATG.AGGREGATE.equals((String) this.contrInfoList.get(0).get("DS_CONTR_CATG_CD"))) {
            prntDsContrDtlPk = (BigDecimal) this.contrInfoList.get(0).get("PRNT_DS_CONTR_DTL_PK");
        }
        setValue(inTMsg.prntDsContrDtlPk, prntDsContrDtlPk);
        setValue(inTMsg.dsBllgSchdTpCd, DS_BLLG_SCHD_TP.REGULAR);

        // Add Start 06/09/2016 <QC#7402>
        if (FLG_OFF_N.equals(pMsg.xxRqstFlg.getValue())) {
            return inTMsg;
        }
        // Add End   06/09/2016 <QC#7402>
        S21ApiTBLAccessor.insert(inTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
            msgMap.addXxMsgIdWithPrm(NSAM0032E, new String[]{"DS_CONTR_BLLG_SCHD_SMRY"});
            return null;
        }
        return inTMsg;
    }

    protected DS_CONTR_BLLG_SCHDTMsg createSchdForBase(S21ApiMessageMap msgMap
                                                    , NSZC047001_xxBaseLineListPMsg linePMsg
                                                    , DS_CONTR_PRC_EFFTMsg prcEffTMsg
                                                    , DS_CONTR_BLLG_SCHD_SMRYTMsg schdSmryTMsg
                                                    , CalcSchdTermAndAmtLineBean schdBean) {

        NSZC047001PMsg pMsg = (NSZC047001PMsg) msgMap.getPmsg();
        BigDecimal dsContrBllgSchdPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CONTR_BLLG_SCHD_SQ);

        String skipRecovTpCd = "";
        String bllgSkipFlg = "";
        String rvsSchdDt = "";
        CalcRvsSchdBean rvsSchdBean = calcRvsSchdDt(msgMap, schdBean, linePMsg.baseBllgCycleCd_BL.getValue());
        if (rvsSchdBean != null) {
            skipRecovTpCd = rvsSchdBean.getSkipRecovTpCd();
            bllgSkipFlg = rvsSchdBean.getBllgSkipFlg();
            rvsSchdDt = rvsSchdBean.getRvsSchdDt();
        }
        String bllblFlg = FLG_ON_Y;
        if (FLG_ON_Y.equals(bllgSkipFlg)) {
            bllblFlg = FLG_OFF_N;
        }

        BigDecimal svcPhysMtrReadGrpSq = getSvcPhysMtrReadGrpSq(pMsg, schdBean);
        String mtrEntryCpltFlg = FLG_OFF_N;
        if (hasValue(svcPhysMtrReadGrpSq)) {
            mtrEntryCpltFlg = FLG_ON_Y;
        }

        BigDecimal bllgPerMthAot = getBllgPerMthAot(pMsg.glblCmpyCd.getValue()
                                                    , linePMsg.baseBllgCycleCd_BL.getValue()
                                                    , schdSmryTMsg);

        String perBllgCycleCd = schdSmryTMsg.perBllgCycleCd.getValue();
        String dsContrBllgSchdSqNum;
        String bllgSchdFromDt;
        String bllgSchdThruDt;
        String bllgSchdPrrtFlg;
        String nextBllgDt = getNextBllgDt(msgMap, schdSmryTMsg, schdBean);
        BigDecimal baseActlPrcDealAmt;
        // START 2016/02/18 T.Tomita [QC#3894, MOD]
        if (BLLG_CYCLE_UOM.DAYS.equals(perBllgCycleCd) || BLLG_CYCLE_UOM.WEEKS.equals(perBllgCycleCd)) {
            // --------------------
            // Per Days
            // --------------------
            dsContrBllgSchdSqNum = String.valueOf(1);
            bllgSchdFromDt = schdSmryTMsg.bllgSchdFromDt.getValue();
            bllgSchdThruDt = schdSmryTMsg.bllgSchdThruDt.getValue();
            bllgSchdPrrtFlg = FLG_ON_Y;
            baseActlPrcDealAmt = schdSmryTMsg.baseSubTotPrcDealAmt.getValue();
        } else {
            // --------------------
            // Per Billing Cycle
            // --------------------
            dsContrBllgSchdSqNum = schdBean.getDsContrBllgSchdSqNum();
            bllgSchdFromDt = schdBean.getBllgSchdFromDt();
            bllgSchdThruDt = schdBean.getBllgSchdThruDt();
            bllgSchdPrrtFlg = FLG_OFF_N;
            baseActlPrcDealAmt = schdBean.getBasePrcDealAmt();
        }
        // END 2016/02/18 T.Tomita [QC#3894, MOD]

        // --------------------
        // set value to TMsg
        // --------------------
        DS_CONTR_BLLG_SCHDTMsg inTMsg = new DS_CONTR_BLLG_SCHDTMsg();
        setValue(inTMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(inTMsg.dsContrBllgSchdPk, dsContrBllgSchdPk);
        setValue(inTMsg.dsContrDtlPk, pMsg.dsContrDtlPk);
        setValue(inTMsg.dsContrBllgSchdSmryPk, schdSmryTMsg.dsContrBllgSchdSmryPk);
        setValue(inTMsg.dsContrPrcEffPk, prcEffTMsg.dsContrPrcEffPk);
        setValue(inTMsg.dsContrPrcEffSqNum, prcEffTMsg.dsContrPrcEffSqNum);
        setValue(inTMsg.dsContrBllgSchdSqNum, dsContrBllgSchdSqNum);
        setValue(inTMsg.dsContrBllgSchdLvlNum, schdSmryTMsg.dsContrBllgSchdSqNum);
        setValue(inTMsg.baseChrgFlg, FLG_ON_Y);
        setValue(inTMsg.usgChrgFlg, FLG_OFF_N);
        setValue(inTMsg.skipRecovTpCd, skipRecovTpCd);
        inTMsg.svcInvNum.clear();
        inTMsg.invDt.clear();
        setValue(inTMsg.nextBllgDt, nextBllgDt);
        setValue(inTMsg.bllgSchdFromDt, bllgSchdFromDt);
        setValue(inTMsg.bllgSchdThruDt, bllgSchdThruDt);
        setValue(inTMsg.bllgCycleCd, linePMsg.baseBllgCycleCd_BL);
        setValue(inTMsg.bllgSchdPrrtFlg, bllgSchdPrrtFlg);
        setValue(inTMsg.basePrcDealAmt, linePMsg.basePrcDealAmt_BL);
        inTMsg.basePrcFuncAmt.clear();
        setValue(inTMsg.baseActlPrcDealAmt, baseActlPrcDealAmt);
        inTMsg.baseActlPrcFuncAmt.clear();
        inTMsg.readMtrCnt.clear();
        inTMsg.bllgMtrCnt.clear();
        inTMsg.mtrChrgDealAmt.clear();
        inTMsg.mtrChrgFuncAmt.clear();
        setValue(inTMsg.dsContrTrmnFlg, FLG_OFF_N);
        setValue(inTMsg.ccyCd, (String) this.contrInfoList.get(0).get("CCY_CD"));
        inTMsg.bllgSchdTrxSrcTpCd.clear();
        setValue(inTMsg.bllgStageFlg, FLG_OFF_N);
        setValue(inTMsg.invFlg, FLG_OFF_N);
        setValue(inTMsg.invTpCd, INV_TP.INVOICE);
        setValue(inTMsg.dsBllgSchdStsCd, DS_BLLG_SCHD_STS.OPEN);
        inTMsg.dsContrBllgMtrPk.clear();
        setValue(inTMsg.bllblFlg, bllblFlg);
        setValue(inTMsg.rvsSchdDt, rvsSchdDt);
        setValue(inTMsg.mtrEntryCpltFlg, mtrEntryCpltFlg);
        inTMsg.svcPhysMtrReadGrpSq.clear();
        setValue(inTMsg.dsContrDtlTpCd, this.dsContrDtlTp);
        BigDecimal prntDsContrBllgSchdPk = null;
        BigDecimal prntDsContrDtlPk = null;
        // START 07/12/2018 [QC#25959, MOD]
        Map<String, Object> prntBllgSchdInfo;
        String prntNextBllgDt = null;
        // START 04/18/2016 T.Aoyagi [QC#7056, MOD]
        if (DS_CONTR_CATG.AGGREGATE.equals((String) this.contrInfoList.get(0).get("DS_CONTR_CATG_CD"))) {
            prntDsContrDtlPk = (BigDecimal) this.contrInfoList.get(0).get("PRNT_DS_CONTR_DTL_PK");
            if (!DS_CONTR_DTL_TP.AGGREGATE.equals(this.dsContrDtlTp)) {
                // mod start 2017/07/21 QC#20045
                // START 2019/02/14 K.Kitachi [QC#30066, MOD]
//                prntBllgSchdInfo = NSZC0470Query.getInstance().getPrntBllgSchdPk(pMsg.glblCmpyCd.getValue(), prntDsContrDtlPk, null, bllgSchdFromDt, bllgSchdThruDt, pMsg.baseChrgFlg.getValue(), INV_TP.INVOICE);
                prntBllgSchdInfo = NSZC0470Query.getInstance().getPrntBllgSchdPk(pMsg.glblCmpyCd.getValue(), prntDsContrDtlPk, null, bllgSchdFromDt, bllgSchdThruDt, pMsg.baseChrgFlg.getValue(), INV_TP.INVOICE, pMsg.dsContrDtlPk.getValue());
                // END 2019/02/14 K.Kitachi [QC#30066, MOD]
                if (prntBllgSchdInfo != null) {
                    prntDsContrBllgSchdPk = (BigDecimal) prntBllgSchdInfo.get("DS_CONTR_BLLG_SCHD_PK");
                    prntNextBllgDt = (String) prntBllgSchdInfo.get("NEXT_BLLG_DT");
                    setValue(inTMsg.nextBllgDt, prntNextBllgDt);
                }
                // mod end   2017/07/21 QC#20045
            }
        }
        // Add Start 03/29/2016 <QC#5863>
        if (DS_CONTR_DTL_TP.ACCESSORIES.equals(this.dsContrDtlTp)) {
            prntDsContrDtlPk = (BigDecimal) this.contrInfoList.get(0).get("PRNT_DS_CONTR_DTL_PK");
            // mod start 2017/07/21 QC#20045
            // START 2019/02/14 K.Kitachi [QC#30066, MOD]
//            prntBllgSchdInfo = NSZC0470Query.getInstance().getPrntBllgSchdPk(pMsg.glblCmpyCd.getValue(), prntDsContrDtlPk, null, bllgSchdFromDt, bllgSchdThruDt, pMsg.baseChrgFlg.getValue(), INV_TP.INVOICE);
            prntBllgSchdInfo = NSZC0470Query.getInstance().getPrntBllgSchdPk(pMsg.glblCmpyCd.getValue(), prntDsContrDtlPk, null, bllgSchdFromDt, bllgSchdThruDt, pMsg.baseChrgFlg.getValue(), INV_TP.INVOICE, pMsg.dsContrDtlPk.getValue());
            // END 2019/02/14 K.Kitachi [QC#30066, MOD]
            if (prntBllgSchdInfo != null) {
                prntDsContrBllgSchdPk = (BigDecimal) prntBllgSchdInfo.get("DS_CONTR_BLLG_SCHD_PK");
                prntNextBllgDt = (String) prntBllgSchdInfo.get("NEXT_BLLG_DT");
                // START 2018/12/10 K.Kitachi [QC#29387, ADD]
                setValue(inTMsg.nextBllgDt, prntNextBllgDt);
                // END 2018/12/10 K.Kitachi [QC#29387, ADD]
            }
            // mod end   2017/07/21 QC#20045
        }
        // Add End   03/29/2016 <QC#5863>
        // END 04/18/2016 T.Aoyagi [QC#7056, MOD]
        // END   07/12/2018 [QC#25959, MOD]
        setValue(inTMsg.prntDsContrBllgSchdPk, prntDsContrBllgSchdPk);
        setValue(inTMsg.bllgPerMthAot, bllgPerMthAot);
        setValue(inTMsg.prntDsContrDtlPk, prntDsContrDtlPk);
        inTMsg.svcCrRebilPk.clear();
        inTMsg.svcCrRebilDtlPk.clear();
        inTMsg.origDsContrBllgSchdPk.clear();
        setValue(inTMsg.dsBllgSchdTpCd, DS_BLLG_SCHD_TP.REGULAR);
        setValue(inTMsg.basePrcAdjDealAmt, schdBean.getBasePrcAdjDealAmt());
        inTMsg.basePrcAdjFuncAmt.clear();

        setValue(inTMsg.bllgCalcFlg, FLG_OFF_N);
        // Add Start 06/09/2016 <QC#7402>
        if (FLG_OFF_N.equals(pMsg.xxRqstFlg.getValue())) {
            return inTMsg;
        }
        // Add End   06/09/2016 <QC#7402>
        S21ApiTBLAccessor.insert(inTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
            msgMap.addXxMsgIdWithPrm(NSAM0032E, new String[]{"DS_CONTR_BLLG_SCHD"});
            return null;
        }
        return inTMsg;
    }

    protected DS_CONTR_PRC_EFFTMsg createPrcEffForUsage(S21ApiMessageMap msgMap
                                            , NSZC047001_xxMtrLineListPMsg linePMsg) {

        NSZC047001PMsg pMsg = (NSZC047001PMsg) msgMap.getPmsg();

        BigDecimal dsContrPrcEffPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CONTR_PRC_EFF_SQ);
        BigDecimal dsContrPrcEffSqNum = linePMsg.dsContrPrcEffSqNum_ML.getValue();
        if (!hasValue(dsContrPrcEffSqNum)) {
            dsContrPrcEffSqNum = BigDecimal.ONE;
        }

        DS_CONTR_PRC_EFFTMsg inTMsg = new DS_CONTR_PRC_EFFTMsg();
        setValue(inTMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(inTMsg.dsContrPrcEffPk, dsContrPrcEffPk);
        setValue(inTMsg.dsContrPrcEffSqNum, dsContrPrcEffSqNum);
        setValue(inTMsg.dsContrDtlPk, pMsg.dsContrDtlPk);
        setValue(inTMsg.dsContrBllgMtrPk, linePMsg.dsContrBllgMtrPk_ML);
        setValue(inTMsg.contrPrcEffFromDt, pMsg.contrEffFromDt);
        setValue(inTMsg.contrPrcEffThruDt, pMsg.contrEffThruDt);
        setValue(inTMsg.bllgCycleCd, linePMsg.mtrBllgCycleCd_ML);
        inTMsg.basePrcDealAmt.clear();
        inTMsg.basePrcFuncAmt.clear();
        inTMsg.basePrcTermDealAmtRate.clear();
        inTMsg.basePrcTermFuncAmtRate.clear();
        setValue(inTMsg.ccyCd, (String) this.contrInfoList.get(0).get("CCY_CD"));
        setValue(inTMsg.baseChrgFlg, FLG_OFF_N);
        setValue(inTMsg.usgChrgFlg, FLG_ON_Y);
        if (!hasValue(linePMsg.dsContrPrcEffPk_ML)) {
            setValue(inTMsg.dsContrPrcEffStsCd, (String) this.contrInfoList.get(0).get("DS_CONTR_DTL_STS_CD"));
            inTMsg.contrRnwErrRsnCd.clear();
            // Mod Start 06/29/2016 <QC#7402>
            setValue(inTMsg.qltyAsrnHldFlg, (String) this.contrInfoList.get(0).get("QLTY_ASRN_HLD_FLG"));
            setValue(inTMsg.mtrHldFlg, (String) this.contrInfoList.get(0).get("MTR_HLD_FLG"));
            setValue(inTMsg.contrHldFlg, (String) this.contrInfoList.get(0).get("CONTR_HLD_FLG"));
            setValue(inTMsg.bllgHldFlg, (String) this.contrInfoList.get(0).get("BLLG_HLD_FLG"));
            setValue(inTMsg.qltyAsrnHldPendApvlFlg, (String) this.contrInfoList.get(0).get("QLTY_ASRN_HLD_PEND_APVL_FLG"));
            // Mod End   06/29/2016 <QC#7402>
        } else {
            // START 2022/06/16 R.Jin <QC#60217,MOD> 
//            setValue(inTMsg.dsContrPrcEffStsCd, linePMsg.dsContrPrcEffStsCd_ML);
            if (DS_CONTR_DTL_STS.ACTIVE.equals(linePMsg.dsContrPrcEffStsCd_ML.getValue()) && ZYPCommonFunc.hasValue(linePMsg.effFromDt_ML) && ZYPDateUtil.compare(linePMsg.effFromDt_ML.getValue(), pMsg.slsDt.getValue()) > 0) {
                setValue(inTMsg.dsContrPrcEffStsCd, DS_CONTR_DTL_STS.SIGNED);
            } else if (DS_CONTR_DTL_STS.SIGNED.equals(linePMsg.dsContrPrcEffStsCd_ML.getValue()) && ZYPCommonFunc.hasValue(linePMsg.effFromDt_ML) && ZYPDateUtil.compare(linePMsg.effFromDt_ML.getValue(), pMsg.slsDt.getValue()) <= 0) {
                setValue(inTMsg.dsContrPrcEffStsCd, DS_CONTR_DTL_STS.ACTIVE);
            } else {
                setValue(inTMsg.dsContrPrcEffStsCd, linePMsg.dsContrPrcEffStsCd_ML);
            }
            // END 2022/06/16 R.Jin <QC#60217,MOD> 
            
            inTMsg.contrRnwErrRsnCd.clear();
            setValue(inTMsg.qltyAsrnHldFlg, linePMsg.qltyAsrnHldFlg_ML);
            // START 2022/06/08 R.Jin <QC#57803,MOD> 
//            setValue(inTMsg.mtrHldFlg, linePMsg.mtrHldFlg_ML);
            if (ZYPCommonFunc.hasValue(linePMsg.effFromDt_ML) && ZYPDateUtil.compare(linePMsg.effFromDt_ML.getValue(), pMsg.slsDt.getValue()) > 0) {
                setValue(inTMsg.mtrHldFlg, ZYPConstant.FLG_OFF_N);
            } else {
                setValue(inTMsg.mtrHldFlg, linePMsg.mtrHldFlg_ML);
            }
            // END 2022/06/08 R.Jin <QC#57803,MOD> 
            setValue(inTMsg.contrHldFlg, linePMsg.contrHldFlg_ML);
            setValue(inTMsg.bllgHldFlg, linePMsg.bllgHldFlg_ML);
            // Add Start 06/29/2016 <QC#7402>
            if (hasValue(linePMsg.qltyAsrnHldPendApvlFlg_ML)) {
                setValue(inTMsg.qltyAsrnHldPendApvlFlg, linePMsg.qltyAsrnHldPendApvlFlg_ML);
            } else {
                setValue(inTMsg.qltyAsrnHldPendApvlFlg, ZYPConstant.FLG_OFF_N);
            }
            // Add End  06/29/2016 <QC#7402>
        }
        // Del Start 06/29/2016 <QC#7402>
        // START 2016/01/18 T.Tomita [QC#1088, ADD]
//        setValue(inTMsg.qltyAsrnHldPendApvlFlg, ZYPConstant.FLG_OFF_N);
        // END 2016/01/18 T.Tomita [QC#1088, ADD]
        // Del End   06/29/2016 <QC#7402>
        // Add Start 06/29/2016 <QC#7402>
        if (FLG_OFF_N.equals(pMsg.xxRqstFlg.getValue())) {
            return inTMsg;
        }
        // Add End   06/29/2016 <QC#7402>
        S21ApiTBLAccessor.insert(inTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
            msgMap.addXxMsgIdWithPrm(NSAM0032E, new String[]{"DS_CONTR_PRC_EFF"});
            return null;
        }
        return inTMsg;
    }

    protected DS_CONTR_PRC_EFF_MTRTMsg createPrcEffMtr(S21ApiMessageMap msgMap
                                                , NSZC047001_xxMtrLineListPMsg linePMsg
                                                , DS_CONTR_PRC_EFFTMsg tMsg) {

        NSZC047001PMsg pMsg = (NSZC047001PMsg) msgMap.getPmsg();
        BigDecimal dsContrPrcEffMtrPk = null;
        dsContrPrcEffMtrPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CONTR_PRC_EFF_MTR_SQ);

        DS_CONTR_PRC_EFF_MTRTMsg inTMsg = new DS_CONTR_PRC_EFF_MTRTMsg();
        setValue(inTMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(inTMsg.dsContrPrcEffMtrPk, dsContrPrcEffMtrPk);
        setValue(inTMsg.dsContrPrcEffPk, tMsg.dsContrPrcEffPk);
        setValue(inTMsg.contrXsCopyPk, linePMsg.contrXsCopyPk_ML);
        setValue(inTMsg.dsContrBllgMtrPk, linePMsg.dsContrBllgMtrPk_ML);
        setValue(inTMsg.xsMtrCopyQty, linePMsg.xsMtrCopyQty_ML);
        setValue(inTMsg.xsMtrAmtRate, linePMsg.xsMtrAmtRate_ML);
        setValue(inTMsg.xsMtrFirstFlg, linePMsg.xsMtrFirstFlg_ML);

        S21ApiTBLAccessor.insert(inTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
            msgMap.addXxMsgIdWithPrm(NSAM0032E, new String[]{"DS_CONTR_PRC_EFF_MTR"});
            return null;
        }
        return inTMsg;
    }

    protected DS_CONTR_BLLG_SCHD_SMRYTMsg createSchdSmryForUsage(S21ApiMessageMap msgMap
                                                        , NSZC047001_xxMtrLineListPMsg linePMsg
                                                        , DS_CONTR_PRC_EFFTMsg tMsg
                                                        , CalcSchdSmryTermAndAmtForUsageBean schdSmryBean
                                                        , CalcSchdSmryTermAndAmtBean allowanceBean) {
        NSZC047001PMsg pMsg = (NSZC047001PMsg) msgMap.getPmsg();

        BigDecimal dsContrBllgSchedSmryPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CONTR_BLLG_SCHD_SMRY_SQ);
        DS_CONTR_BLLG_SCHD_SMRYTMsg inTMsg = new DS_CONTR_BLLG_SCHD_SMRYTMsg();

        setValue(inTMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(inTMsg.dsContrBllgSchdSmryPk, dsContrBllgSchedSmryPk);
        setValue(inTMsg.dsContrDtlPk, pMsg.dsContrDtlPk);
        setValue(inTMsg.dsContrPrcEffPk, tMsg.dsContrPrcEffPk);
        setValue(inTMsg.dsContrPrcEffSqNum, tMsg.dsContrPrcEffSqNum);
        setValue(inTMsg.dsContrBllgMtrPk, linePMsg.dsContrBllgMtrPk_ML.getValue());
        setValue(inTMsg.dsContrBllgSchdSqNum, schdSmryBean.getDsContrBllgSchdSqNum());
        setValue(inTMsg.perSchdNum, schdSmryBean.getPerSchdNum());
        setValue(inTMsg.perBllgCycleCd, schdSmryBean.getPerBllgCycleCd());
        setValue(inTMsg.bllgSchdFromDt, schdSmryBean.getBllgSchdFromDt());
        setValue(inTMsg.bllgSchdThruDt, schdSmryBean.getBllgSchdThruDt());
        setValue(inTMsg.bllgCycleCd, linePMsg.mtrBllgCycleCd_ML);
        inTMsg.basePrcDealAmt.clear();
        inTMsg.basePrcFuncAmt.clear();
        inTMsg.basePrcDealAdjAmt.clear();
        inTMsg.basePrcFuncAdjAmt.clear();
        inTMsg.baseSubTotPrcDealAmt.clear();
        inTMsg.baseSubTotPrcFuncAmt.clear();
        setValue(inTMsg.ccyCd, (String) this.contrInfoList.get(0).get("CCY_CD"));
        setValue(inTMsg.mlyCopyInclPrcQty, allowanceBean.getUsageList().get(0).getXsMtrCopyQty());
        inTMsg.dsContrBllgSchdTrmnDt.clear();
        setValue(inTMsg.baseChrgFlg, FLG_OFF_N);
        setValue(inTMsg.usgChrgFlg, FLG_ON_Y);
        inTMsg.origBllgSchdSmryNum.clear();
        BigDecimal prntDsContrDtlPk = null;
        if (DS_CONTR_CATG.AGGREGATE.equals((String) this.contrInfoList.get(0).get("DS_CONTR_CATG_CD"))) {
            prntDsContrDtlPk = (BigDecimal) this.contrInfoList.get(0).get("PRNT_DS_CONTR_DTL_PK");
        }
        setValue(inTMsg.prntDsContrDtlPk, prntDsContrDtlPk);
        setValue(inTMsg.dsBllgSchdTpCd, DS_BLLG_SCHD_TP.REGULAR);

        // Add Start 06/29/2016 <QC#7402>
        if (FLG_OFF_N.equals(pMsg.xxRqstFlg.getValue())) {
            return inTMsg;
        }
        // Add End   06/29/2016 <QC#7402>
        S21ApiTBLAccessor.insert(inTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
            msgMap.addXxMsgIdWithPrm(NSAM0032E, new String[]{"DS_CONTR_BLLG_SCHD_SMRY"});
            return null;
        }
        return inTMsg;
    }

    protected DS_CONTR_BLLG_SCHD_MTRTMsg createSchdMtr(S21ApiMessageMap msgMap
                                , DS_CONTR_BLLG_SCHD_SMRYTMsg tMsg
                                , CalcSchdSmryTermAndAmtForUsageBean allowBean) {

        NSZC047001PMsg pMsg = (NSZC047001PMsg) msgMap.getPmsg();
        BigDecimal dsContrBllgSchdMtrPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CONTR_BLLG_SCHD_MTR_SQ);


        DS_CONTR_BLLG_SCHD_MTRTMsg inTMsg = new DS_CONTR_BLLG_SCHD_MTRTMsg();
        setValue(inTMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(inTMsg.dsContrBllgSchdMtrPk, dsContrBllgSchdMtrPk);
        setValue(inTMsg.dsContrBllgSchdSmryPk, tMsg.dsContrBllgSchdSmryPk);
        setValue(inTMsg.contrXsCopyPk, allowBean.getContrXsCopyPk());
        setValue(inTMsg.dsContrBllgMtrPk, allowBean.getDsContrBllgMtrPk());
        setValue(inTMsg.xsMtrCopyQty, allowBean.getXsMtrCopyQty());
        setValue(inTMsg.xsMtrAmtRate, allowBean.getXsMtrAmtRate());
        setValue(inTMsg.xsMtrFirstFlg, allowBean.getXsMtrFirstFlg());

        S21ApiTBLAccessor.insert(inTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
            msgMap.addXxMsgIdWithPrm(NSAM0032E, new String[]{"DS_CONTR_BLLG_SCHD_MTR"});
            return null;
        }
        return inTMsg;
    }

    protected DS_CONTR_BLLG_SCHDTMsg createSchdForUsage(S21ApiMessageMap msgMap
                                                    , NSZC047001_xxMtrLineListPMsg linePMsg
                                                    , DS_CONTR_PRC_EFFTMsg prcEffTMsg
                                                    , DS_CONTR_BLLG_SCHD_SMRYTMsg schdSmryTMsg
                                                    , CalcSchdTermAndAmtLineBean schdBean) {

        NSZC047001PMsg pMsg = (NSZC047001PMsg) msgMap.getPmsg();
        BigDecimal dsContrBllgSchdPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CONTR_BLLG_SCHD_SQ);

        String skipRecovTpCd = "";
        String bllgSkipFlg = "";
        String rvsSchdDt = "";
        CalcRvsSchdBean rvsSchdBean = calcRvsSchdDt(msgMap, schdBean, linePMsg.mtrBllgCycleCd_ML.getValue());
        if (rvsSchdBean != null) {
            skipRecovTpCd = rvsSchdBean.getSkipRecovTpCd();
            bllgSkipFlg = rvsSchdBean.getBllgSkipFlg();
            rvsSchdDt = rvsSchdBean.getRvsSchdDt();
        }
        String bllblFlg = FLG_ON_Y;
        if (FLG_ON_Y.equals(bllgSkipFlg)) {
            bllblFlg = FLG_OFF_N;
        }
        BigDecimal svcPhysMtrReadGrpSq = getSvcPhysMtrReadGrpSq(pMsg, schdBean);
        String mtrEntryCpltFlg = FLG_OFF_N;
        if (hasValue(svcPhysMtrReadGrpSq)) {
            mtrEntryCpltFlg = FLG_ON_Y;
        }
        BigDecimal bllgPerMthAot = getBllgPerMthAot(pMsg.glblCmpyCd.getValue()
                                                    , linePMsg.mtrBllgCycleCd_ML.getValue()
                                                    , schdSmryTMsg);
        String perBllgCycleCd = schdSmryTMsg.perBllgCycleCd.getValue();
        String dsContrBllgSchdSqNum;
        String bllgSchdFromDt;
        String bllgSchdThruDt;
        String bllgSchdPrrtFlg;
        String nextBllgDt = getNextBllgDt(msgMap, schdSmryTMsg, schdBean);
        // START 2016/02/18 T.Tomita [QC#3894, MOD]
        if (BLLG_CYCLE_UOM.DAYS.equals(perBllgCycleCd) || BLLG_CYCLE_UOM.WEEKS.equals(perBllgCycleCd)) {
            // --------------------
            // Per Days
            // --------------------
             dsContrBllgSchdSqNum = String.valueOf(1);
             bllgSchdFromDt = schdSmryTMsg.bllgSchdFromDt.getValue();
             bllgSchdThruDt = schdSmryTMsg.bllgSchdThruDt.getValue();
             bllgSchdPrrtFlg = FLG_ON_Y;
        } else {
            // --------------------
            // Per Billing Cycle
            // --------------------
            dsContrBllgSchdSqNum = schdBean.getDsContrBllgSchdSqNum();
            bllgSchdFromDt = schdBean.getBllgSchdFromDt();
            bllgSchdThruDt = schdBean.getBllgSchdThruDt();
            bllgSchdPrrtFlg = FLG_OFF_N;
        }
        // END 2016/02/18 T.Tomita [QC#3894, MOD]

        // --------------------
        // set value to TMsg
        // --------------------
        DS_CONTR_BLLG_SCHDTMsg inTMsg = new DS_CONTR_BLLG_SCHDTMsg();
        setValue(inTMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(inTMsg.dsContrBllgSchdPk, dsContrBllgSchdPk);
        setValue(inTMsg.dsContrDtlPk, pMsg.dsContrDtlPk);
        setValue(inTMsg.dsContrBllgSchdSmryPk, schdSmryTMsg.dsContrBllgSchdSmryPk);
        setValue(inTMsg.dsContrPrcEffPk, prcEffTMsg.dsContrPrcEffPk);
        setValue(inTMsg.dsContrPrcEffSqNum, prcEffTMsg.dsContrPrcEffSqNum);
        setValue(inTMsg.dsContrBllgSchdSqNum, dsContrBllgSchdSqNum);
        setValue(inTMsg.dsContrBllgSchdLvlNum, schdSmryTMsg.dsContrBllgSchdSqNum);
        setValue(inTMsg.baseChrgFlg, FLG_OFF_N);
        setValue(inTMsg.usgChrgFlg, FLG_ON_Y);
        setValue(inTMsg.skipRecovTpCd, skipRecovTpCd);
        inTMsg.svcInvNum.clear();
        inTMsg.invDt.clear();
        setValue(inTMsg.nextBllgDt, nextBllgDt);
        setValue(inTMsg.bllgSchdFromDt, bllgSchdFromDt);
        setValue(inTMsg.bllgSchdThruDt, bllgSchdThruDt);
        setValue(inTMsg.bllgCycleCd, linePMsg.mtrBllgCycleCd_ML);
        setValue(inTMsg.bllgSchdPrrtFlg, bllgSchdPrrtFlg);
        inTMsg.basePrcDealAmt.clear();
        inTMsg.basePrcFuncAmt.clear();
        inTMsg.baseActlPrcDealAmt.clear();
        inTMsg.baseActlPrcFuncAmt.clear();
        inTMsg.readMtrCnt.clear();
        inTMsg.bllgMtrCnt.clear();
        inTMsg.mtrChrgDealAmt.clear();
        inTMsg.mtrChrgFuncAmt.clear();
        setValue(inTMsg.dsContrTrmnFlg, FLG_OFF_N);
        setValue(inTMsg.ccyCd, (String) this.contrInfoList.get(0).get("CCY_CD"));
        inTMsg.bllgSchdTrxSrcTpCd.clear();
        setValue(inTMsg.bllgStageFlg, FLG_OFF_N);
        setValue(inTMsg.invFlg, FLG_OFF_N);
        setValue(inTMsg.invTpCd, INV_TP.INVOICE);
        setValue(inTMsg.dsBllgSchdStsCd, DS_BLLG_SCHD_STS.OPEN);
        setValue(inTMsg.dsContrBllgMtrPk, linePMsg.dsContrBllgMtrPk_ML);
        setValue(inTMsg.bllblFlg, bllblFlg);
        setValue(inTMsg.rvsSchdDt, rvsSchdDt);
        setValue(inTMsg.mtrEntryCpltFlg, mtrEntryCpltFlg);
        inTMsg.svcPhysMtrReadGrpSq.clear();
        setValue(inTMsg.dsContrDtlTpCd, this.dsContrDtlTp);
        BigDecimal prntDsContrBllgSchdPk = null;
        BigDecimal prntDsContrDtlPk = null;
        // START 07/12/2018 [QC#25959, MOD]
        Map<String, Object> prntBllgSchdInfo;
        String prntNextBllgDt = null;
        if (DS_CONTR_CATG.AGGREGATE.equals((String) this.contrInfoList.get(0).get("DS_CONTR_CATG_CD"))) {
            prntDsContrDtlPk = (BigDecimal) this.contrInfoList.get(0).get("PRNT_DS_CONTR_DTL_PK");
            if (!DS_CONTR_DTL_TP.AGGREGATE.equals(this.dsContrDtlTp)) {
                // START 2016/01/25 T.Aoyagi [CSA-QC3623, MOD]
//                prntDsContrBllgSchdPk = NSZC0470Query.getInstance().getPrntBllgSchdPk(pMsg.glblCmpyCd.getValue(), prntDsContrDtlPk, linePMsg.dsContrBllgMtrPk_ML.getValue(), nextBllgDt, pMsg.baseChrgFlg.getValue());
                // START 04/18/2016 T.Aoyagi [QC#7056, MOD]
                // mod start 2017/07/21 QC#20045
                prntBllgSchdInfo = NSZC0470Query.getInstance().getPrntBllgSchdPkForUsg(pMsg.glblCmpyCd.getValue(), prntDsContrDtlPk, linePMsg.dsContrBllgMtrPk_ML.getValue(), bllgSchdFromDt, bllgSchdThruDt, INV_TP.INVOICE);
                if (prntBllgSchdInfo != null) {
                    prntDsContrBllgSchdPk = (BigDecimal) prntBllgSchdInfo.get("DS_CONTR_BLLG_SCHD_PK");
                    prntNextBllgDt = (String) prntBllgSchdInfo.get("NEXT_BLLG_DT");
                    setValue(inTMsg.nextBllgDt, prntNextBllgDt);
                }
                // mod end   2017/07/21 QC#20045
                // END 04/18/2016 T.Aoyagi [QC#7056, MOD]
                // END   2016/01/25 T.Aoyagi [CSA-QC3623, MOD]
            }
        }
        // END   07/12/2018 [QC#25959, MOD]
        setValue(inTMsg.prntDsContrBllgSchdPk, prntDsContrBllgSchdPk);
        setValue(inTMsg.bllgPerMthAot, bllgPerMthAot);
        setValue(inTMsg.prntDsContrDtlPk, prntDsContrDtlPk);
        inTMsg.svcCrRebilPk.clear();
        inTMsg.svcCrRebilDtlPk.clear();
        inTMsg.origDsContrBllgSchdPk.clear();
        setValue(inTMsg.dsBllgSchdTpCd, DS_BLLG_SCHD_TP.REGULAR);
        inTMsg.basePrcAdjFuncAmt.clear();

        setValue(inTMsg.bllgCalcFlg, FLG_OFF_N);
        // Add Start 06/29/2016 <QC#7402>
        if (FLG_OFF_N.equals(pMsg.xxRqstFlg.getValue())) {
            return inTMsg;
        }
        // Add End   06/29/2016 <QC#7402>
        S21ApiTBLAccessor.insert(inTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
            msgMap.addXxMsgIdWithPrm(NSAM0032E, new String[]{"DS_CONTR_BLLG_SCHD"});
            return null;
        }
        return inTMsg;
    }

    protected DS_CONTR_BLLG_SCHDTMsg createSchdForFleetMachUsage(S21ApiMessageMap msgMap
                                                    , NSZC047001_xxMtrLineListPMsg linePMsg
                                                    , DS_CONTR_PRC_EFFTMsg prcEffTMsg
                                                    , DS_CONTR_BLLG_SCHD_SMRYTMsg schdSmryTMsg
                                                    , DS_CONTR_BLLG_SCHDTMsg schdTMsg
                                                    , CalcSchdTermAndAmtLineBean schdBean
                                                    , Map<String, Object> fleetMachInfo) {

        NSZC047001PMsg pMsg = (NSZC047001PMsg) msgMap.getPmsg();
        BigDecimal dsContrBllgSchdPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CONTR_BLLG_SCHD_SQ);

        BigDecimal dsContrDtlPk = (BigDecimal) fleetMachInfo.get("DS_CONTR_DTL_PK");
        String skipRecovTpCd = "";

        CalcRvsSchdBean rvsSchdBean = calcRvsSchdDt(msgMap, schdBean, linePMsg.mtrBllgCycleCd_ML.getValue());
        if (rvsSchdBean != null) {
            skipRecovTpCd = rvsSchdBean.getSkipRecovTpCd();
        }

        BigDecimal svcPhysMtrReadGrpSq = getSvcPhysMtrReadGrpSq(pMsg, schdBean);
        String mtrEntryCpltFlg = FLG_OFF_N;
        if (hasValue(svcPhysMtrReadGrpSq)) {
            mtrEntryCpltFlg = FLG_ON_Y;
        }
        String dsContrDtlTpCd = (String) fleetMachInfo.get("DS_CONTR_DTL_TP_CD");
        BigDecimal bllgPerMthAot = getBllgPerMthAot(pMsg.glblCmpyCd.getValue()
                                                    , linePMsg.mtrBllgCycleCd_ML.getValue()
                                                    , schdSmryTMsg);
        // --------------------
        // set value to TMsg
        // --------------------
        DS_CONTR_BLLG_SCHDTMsg inTMsg = new DS_CONTR_BLLG_SCHDTMsg();
        setValue(inTMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(inTMsg.dsContrBllgSchdPk, dsContrBllgSchdPk);
        setValue(inTMsg.dsContrDtlPk, dsContrDtlPk);
        inTMsg.dsContrBllgSchdSmryPk.clear();
        inTMsg.dsContrPrcEffPk.clear();
        inTMsg.dsContrPrcEffSqNum.clear();
        inTMsg.dsContrBllgSchdSqNum.clear();
        inTMsg.dsContrBllgSchdLvlNum.clear();
        setValue(inTMsg.baseChrgFlg, FLG_OFF_N);
        setValue(inTMsg.usgChrgFlg, FLG_ON_Y);
        setValue(inTMsg.skipRecovTpCd, skipRecovTpCd);
        inTMsg.svcInvNum.clear();
        inTMsg.invDt.clear();
        setValue(inTMsg.nextBllgDt, schdTMsg.nextBllgDt);
        setValue(inTMsg.bllgSchdFromDt, schdTMsg.bllgSchdFromDt);
        setValue(inTMsg.bllgSchdThruDt, schdTMsg.bllgSchdThruDt);
        setValue(inTMsg.bllgCycleCd, schdTMsg.bllgCycleCd);
        setValue(inTMsg.bllgSchdPrrtFlg, schdTMsg.bllgSchdPrrtFlg);
        inTMsg.basePrcDealAmt.clear();
        inTMsg.basePrcFuncAmt.clear();
        inTMsg.baseActlPrcDealAmt.clear();
        inTMsg.baseActlPrcFuncAmt.clear();
        inTMsg.readMtrCnt.clear();
        inTMsg.bllgMtrCnt.clear();
        inTMsg.mtrChrgDealAmt.clear();
        inTMsg.mtrChrgFuncAmt.clear();
        setValue(inTMsg.dsContrTrmnFlg, FLG_OFF_N);
        setValue(inTMsg.ccyCd, (String) this.contrInfoList.get(0).get("CCY_CD"));
        inTMsg.bllgSchdTrxSrcTpCd.clear();
        setValue(inTMsg.bllgStageFlg, FLG_OFF_N);
        setValue(inTMsg.invFlg, FLG_OFF_N);
        setValue(inTMsg.invTpCd, INV_TP.INVOICE);
        setValue(inTMsg.dsBllgSchdStsCd, DS_BLLG_SCHD_STS.OPEN);
        setValue(inTMsg.dsContrBllgMtrPk, (BigDecimal) fleetMachInfo.get("DS_CONTR_BLLG_MTR_PK"));
        setValue(inTMsg.bllblFlg, schdTMsg.bllblFlg);
        setValue(inTMsg.rvsSchdDt, schdTMsg.rvsSchdDt);
        setValue(inTMsg.mtrEntryCpltFlg, mtrEntryCpltFlg);
        setValue(inTMsg.svcPhysMtrReadGrpSq, svcPhysMtrReadGrpSq);
        setValue(inTMsg.dsContrDtlTpCd, dsContrDtlTpCd);
        setValue(inTMsg.prntDsContrBllgSchdPk, schdTMsg.dsContrBllgSchdPk);
        setValue(inTMsg.bllgPerMthAot, bllgPerMthAot);
        setValue(inTMsg.prntDsContrDtlPk, pMsg.dsContrDtlPk);
        inTMsg.svcCrRebilPk.clear();
        inTMsg.svcCrRebilDtlPk.clear();
        inTMsg.origDsContrBllgSchdPk.clear();
        setValue(inTMsg.dsBllgSchdTpCd, DS_BLLG_SCHD_TP.REGULAR);
        inTMsg.basePrcAdjDealAmt.clear();
        inTMsg.basePrcAdjFuncAmt.clear();

        setValue(inTMsg.bllgCalcFlg, FLG_OFF_N);
        S21ApiTBLAccessor.insert(inTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
            msgMap.addXxMsgIdWithPrm(NSAM0032E, new String[]{"DS_CONTR_BLLG_SCHD"});
            return null;
        }
        return inTMsg;
    }

    protected List<CalcSchdSmryTermAndAmtForBaseBean> calcSchdSmryTermAndAmt(S21ApiMessageMap msgMap
                                                                , NSZC047001_xxBaseLineListPMsg linePMsg) {
        NSZC047001PMsg pMsg = (NSZC047001PMsg) msgMap.getPmsg();

        CalcSchdSmryTermAndAmtBean inBean = new CalcSchdSmryTermAndAmtBean();
        inBean.setGlblCmpyCd(pMsg.glblCmpyCd.getValue());
        inBean.setBllgSchdFromDt(pMsg.contrEffFromDt.getValue());
        inBean.setBllgSchdThruDt(pMsg.contrEffThruDt.getValue());
        inBean.setBllgCycleCd(linePMsg.baseBllgCycleCd_BL.getValue());
        inBean.setContrCloDay(pMsg.contrCloDay.getValue());
        inBean.setBasePrcDealAmt(linePMsg.basePrcDealAmt_BL.getValue());
        inBean.setBasePrcTermDealAmtRate(linePMsg.basePrcTermDealAmtRate_BL.getValue());
        inBean.setBaseChrgFlg(FLG_ON_Y);
        inBean.setUsgChrgFlg(FLG_OFF_N);
        inBean.setCcyCd((String) this.contrInfoList.get(0).get("CCY_CD"));

        CalcSchdSmryTermAndAmtBean outBean = NSXC003001CalcSchdSmryTermAndAmt.calcSchdSmryTermAndAmt(inBean);
        return outBean.getBaseList();
    }

    protected List<CalcSchdTermAndAmtLineBean> calcSchdTermAndAmt(S21ApiMessageMap msgMap
                                                                , NSZC047001_xxBaseLineListPMsg linePMsg
                                                                , DS_CONTR_BLLG_SCHD_SMRYTMsg schdSmryTMsg) {
        NSZC047001PMsg pMsg = (NSZC047001PMsg) msgMap.getPmsg();

        CalcSchdTermAndAmtBean inBean = new CalcSchdTermAndAmtBean();
        inBean.setGlblCmpyCd(pMsg.glblCmpyCd.getValue());
        inBean.setBllgSchdFromDt(schdSmryTMsg.bllgSchdFromDt.getValue());
        inBean.setBllgSchdThruDt(schdSmryTMsg.bllgSchdThruDt.getValue());
        inBean.setBllgCycleCd(linePMsg.baseBllgCycleCd_BL.getValue());
        inBean.setDsContrCloDay(pMsg.contrCloDay.getValue());
        inBean.setBasePrcDealAmt(schdSmryTMsg.basePrcDealAmt.getValue());
        inBean.setBasePrcAdjDealAmt(schdSmryTMsg.basePrcDealAdjAmt.getValue());
        CalcSchdTermAndAmtBean outBean = NSXC003001CalcSchdTermAndAmt.calcSchdTermAndAmt(inBean);

        return outBean.getLine();
    }

    protected List<CalcSchdSmryTermAndAmtForUsageBean> calcSchdSmryTermAndAmt(S21ApiMessageMap msgMap
                                                                , NSZC047001_xxMtrLineListPMsg linePMsg) {
        NSZC047001PMsg pMsg = (NSZC047001PMsg) msgMap.getPmsg();

        CalcSchdSmryTermAndAmtBean inBean = new CalcSchdSmryTermAndAmtBean();
        inBean.setGlblCmpyCd(pMsg.glblCmpyCd.getValue());
        inBean.setBllgSchdFromDt(pMsg.contrEffFromDt.getValue());
        inBean.setBllgSchdThruDt(pMsg.contrEffThruDt.getValue());
        inBean.setBllgCycleCd(linePMsg.mtrBllgCycleCd_ML.getValue());
        inBean.setContrCloDay(pMsg.mtrCloDay.getValue());
        inBean.setBasePrcDealAmt(BigDecimal.ZERO);
        inBean.setBasePrcTermDealAmtRate(BigDecimal.ZERO);
        inBean.setBaseChrgFlg(FLG_OFF_N);
        inBean.setUsgChrgFlg(FLG_ON_Y);
        inBean.setCcyCd((String) this.contrInfoList.get(0).get("CCY_CD"));
        CalcSchdSmryTermAndAmtBean outBean = NSXC003001CalcSchdSmryTermAndAmt.calcSchdSmryTermAndAmt(inBean);
        return outBean.getUsageList();
    }

    protected CalcSchdSmryTermAndAmtBean calcAllowance(S21ApiMessageMap msgMap, CalcSchdSmryTermAndAmtForUsageBean schdSmry
                                                                    , List<NSZC047001_xxMtrLineListPMsg> linePMsgList) {
        NSZC047001PMsg pMsg = (NSZC047001PMsg) msgMap.getPmsg();
        NSZC047001_xxMtrLineListPMsg mtrLinePMsg = linePMsgList.get(0);

        CalcSchdSmryTermAndAmtBean inBean = new CalcSchdSmryTermAndAmtBean();
        inBean.setGlblCmpyCd(pMsg.glblCmpyCd.getValue());
        inBean.setBllgSchdFromDt(pMsg.contrEffFromDt.getValue());
        inBean.setBllgSchdThruDt(pMsg.contrEffThruDt.getValue());
        inBean.setBllgCycleCd(mtrLinePMsg.mtrBllgCycleCd_ML.getValue());
        inBean.setContrCloDay(pMsg.mtrCloDay.getValue());
        inBean.setBasePrcDealAmt(BigDecimal.ZERO);
        inBean.setBasePrcTermDealAmtRate(BigDecimal.ZERO);
        inBean.setBaseChrgFlg(FLG_OFF_N);
        inBean.setUsgChrgFlg(FLG_ON_Y);
        List<CalcSchdSmryTermAndAmtForUsageBean> usageList = new ArrayList<CalcSchdSmryTermAndAmtForUsageBean>();
        for (NSZC047001_xxMtrLineListPMsg linePMsg : linePMsgList) {
            CalcSchdSmryTermAndAmtForUsageBean usageBean = new CalcSchdSmryTermAndAmtForUsageBean();
            //usageBean.setBllgSchdFromDt(pMsg.contrEffFromDt.getValue());
            //usageBean.setBllgSchdThruDt(pMsg.contrEffThruDt.getValue());
            usageBean.setPerBllgCycleCd(schdSmry.getPerBllgCycleCd());
            usageBean.setPerSchdNum(schdSmry.getPerSchdNum());
            usageBean.setDsContrBllgMtrPk(linePMsg.dsContrBllgMtrPk_ML.getValue());
            usageBean.setContrXsCopyPk(linePMsg.contrXsCopyPk_ML.getValue());
            usageBean.setXsMtrCopyQty(linePMsg.xsMtrCopyQty_ML.getValue());
            usageBean.setXsMtrAmtRate(linePMsg.xsMtrAmtRate_ML.getValue());
            usageBean.setXsMtrFirstFlg(linePMsg.xsMtrFirstFlg_ML.getValue());
            usageList.add(usageBean);
        }
        inBean.setUsageList(usageList);
        CalcSchdSmryTermAndAmtBean outBean = NSXC003001CalcSchdSmryTermAndAmt.calcAllowance(inBean);
        return outBean;
    }

    protected List<CalcSchdTermAndAmtLineBean> calcSchdTermAndAmt(S21ApiMessageMap msgMap
                                                                , NSZC047001_xxMtrLineListPMsg linePMsg
                                                                , DS_CONTR_BLLG_SCHD_SMRYTMsg schdSmryTMsg) {
        NSZC047001PMsg pMsg = (NSZC047001PMsg) msgMap.getPmsg();

        CalcSchdTermAndAmtBean inBean = new CalcSchdTermAndAmtBean();
        inBean.setGlblCmpyCd(pMsg.glblCmpyCd.getValue());
        inBean.setBllgSchdFromDt(schdSmryTMsg.bllgSchdFromDt.getValue());
        inBean.setBllgSchdThruDt(schdSmryTMsg.bllgSchdThruDt.getValue());
        inBean.setBllgCycleCd(linePMsg.mtrBllgCycleCd_ML.getValue());
        inBean.setDsContrCloDay(pMsg.mtrCloDay.getValue());
        inBean.setBasePrcDealAmt(BigDecimal.ZERO);
        inBean.setBasePrcAdjDealAmt(BigDecimal.ZERO);
        CalcSchdTermAndAmtBean outBean = NSXC003001CalcSchdTermAndAmt.calcSchdTermAndAmt(inBean);

        return outBean.getLine();
    }

    protected CalcRvsSchdBean calcRvsSchdDt(S21ApiMessageMap msgMap
                                            , CalcSchdTermAndAmtLineBean schdBean
                                            , String bllgCycleCd) {
        NSZC047001PMsg pMsg = (NSZC047001PMsg) msgMap.getPmsg();
        CalcRvsSchdBean inBean = new CalcRvsSchdBean();

        String svcInvChrgTp = "";

        if (isBaseChrg(pMsg)) {
            svcInvChrgTp = SVC_INV_CHRG_TP.BASE_CHARGE;
        } else {
            svcInvChrgTp = SVC_INV_CHRG_TP.METER_CHARGE;
        }

        List<Map<String, Object>> resultList = NSZC0470Query.getInstance().getSkipRecovInfo(pMsg.glblCmpyCd.getValue(), pMsg.dsContrDtlPk.getValue(), svcInvChrgTp);

        List<CalcRvsSchdLineBean> skipLineList = new ArrayList<CalcRvsSchdLineBean>();

        inBean.setBllgSchdFromDt(schdBean.getBllgSchdFromDt());
        inBean.setBllgSchdThruDt(schdBean.getBllgSchdThruDt());
        if (isBaseChrg(pMsg)) {
            inBean.setBllgTmgTp(pMsg.baseBllgTmgCd.getValue());
            inBean.setContrBllgDay(pMsg.contrBllgDay.getValue());
            inBean.setContrCloDay(pMsg.contrCloDay.getValue());
        } else {
            inBean.setBllgTmgTp(pMsg.mtrBllgTmgCd.getValue());
            inBean.setContrBllgDay(pMsg.mtrBllgDay.getValue());
            inBean.setContrCloDay(pMsg.mtrCloDay.getValue());
        }
        inBean.setSkipRecovTpCd(SKIP_RECOV_TP.NONE);
        inBean.setBllgSkipFlg(FLG_OFF_N);

        for (Map<String, Object> resultMap : resultList) {
            CalcRvsSchdLineBean lineBean = new CalcRvsSchdLineBean();
            lineBean.setSkipRecovMth((String) resultMap.get("SKIP_RECOV_TP_CD"));
            lineBean.setSkipRecovTpCd((String) resultMap.get("SKIP_RECOV_MTH"));
            lineBean.setBllgSkipFlg((String) resultMap.get("BLLG_SKIP_FLG"));
            lineBean.setBllgRecovFlg((String) resultMap.get("BLLG_RECOV_FLG"));
            skipLineList.add(lineBean);
        }
        inBean.setSkipLine(skipLineList);

        CalcRvsSchdBean outBean = NSXC003001CalcRvsSchdDt.calcRvsSchdDt(inBean);
        return outBean;
    }

    protected String getNextBllgDt(S21ApiMessageMap msgMap
                                    , DS_CONTR_BLLG_SCHD_SMRYTMsg schdSmryBean
                                    , CalcSchdTermAndAmtLineBean schdBean) {

        NSZC047001PMsg pMsg = (NSZC047001PMsg) msgMap.getPmsg();

        CalcNextBllgDtBean inBean = new CalcNextBllgDtBean();
        if (FLG_ON_Y.equals(pMsg.baseChrgFlg.getValue())) {
            inBean.setBllgTmgTp(pMsg.baseBllgTmgCd.getValue());
            inBean.setContrBllgDay((String) this.contrInfoList.get(0).get("CONTR_BLLG_DAY"));
            inBean.setInvUpToDt((String) this.contrInfoList.get(0).get("BASE_INV_UP_TO_DT"));
        } else {
            inBean.setBllgTmgTp(pMsg.mtrBllgTmgCd.getValue());
            inBean.setContrBllgDay((String) this.contrInfoList.get(0).get("MTR_BLLG_DAY"));
            inBean.setInvUpToDt((String) this.contrInfoList.get(0).get("MTR_INV_UP_TO_DT"));
        }
        if (schdBean != null) {
            inBean.setBllgSchdFromDt(schdBean.getBllgSchdFromDt());
            inBean.setBllgSchdThruDt(schdBean.getBllgSchdThruDt());
        } else {
            inBean.setBllgSchdFromDt(schdSmryBean.bllgSchdFromDt.getValue());
            inBean.setBllgSchdThruDt(schdSmryBean.bllgSchdThruDt.getValue());
        }

        // START 2016/06/23 T.Kanasaka [QC#10524, ADD]
        inBean.setContrEffFromDt((String) this.contrInfoList.get(0).get("CONTR_EFF_FROM_DT"));
        // END 2016/06/23 T.Kanasaka [QC#10524, ADD]

        return NSXC003001CalcNextBllgDt.calcTermAmt(inBean);
    }

    private boolean isBaseChrg(NSZC047001PMsg pMsg) {
        boolean result = false;
        if (FLG_ON_Y.equals(pMsg.baseChrgFlg.getValue())) {
            result = true;
        }
        return result;
    }

//    private BigDecimal getPrntContrDtlInfo(NSZC047001PMsg pMsg, String dsContrDtlTpCd) {
//
//        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
//        BigDecimal dsContrDtlPk = (BigDecimal) this.contrInfoList.get(0).get("PRNT_DS_CONTR_DTL_PK");
//        if (!hasValue(dsContrDtlPk)) {
//            dsContrDtlPk = (BigDecimal) this.contrInfoList.get(0).get("DS_CONTR_DTL_PK");
//        }
//        return (BigDecimal) NSZC0470Query.getInstance().getPrntContrDtlInfo(glblCmpyCd, dsContrDtlPk, dsContrDtlTpCd);
//    }

    private BigDecimal getBllgPerMthAot(String glblCmpyCd, String bllgCycleCd, DS_CONTR_BLLG_SCHD_SMRYTMsg schdSmryTMsg) {

        BigDecimal bllgPerMthAot = BigDecimal.ZERO;

        BLLG_CYCLETMsg bllgCycleTMsg = NSZC0470Query.getInstance().getBllgCycleTMsg(glblCmpyCd, bllgCycleCd);
        String perBllgCycleCd = schdSmryTMsg.perBllgCycleCd.getValue();
        BigDecimal perSchdNum = schdSmryTMsg.perSchdNum.getValue();

        if (BLLG_CYCLE.DAILY.equals(perBllgCycleCd)) {
            // Per days
            bllgPerMthAot = perSchdNum;
        } else {
            // Per Billing Cycle
            // START 2016/01/28 T.Aoyagi [CSA-QC#3095,MOD]
            if (bllgCycleTMsg != null) {
                bllgPerMthAot = bllgCycleTMsg.bllgCycleMthAot.getValue();
            }
            // END   2016/01/28 T.Aoyagi [CSA-QC#3095,MOD]
        }
        return bllgPerMthAot;
    }

    private BigDecimal getSvcPhysMtrReadGrpSq(NSZC047001PMsg pMsg
                                    , CalcSchdTermAndAmtLineBean schdBean) {

        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        BigDecimal dsContrDtlPk = pMsg.dsContrDtlPk.getValue();
        BigDecimal dsContrBllgMtrPk = pMsg.dsContrDtlPk.getValue();
        String fromDt = schdBean.getBllgSchdFromDt();
        String thruDt = schdBean.getBllgSchdThruDt();

        BigDecimal dsConrBllgSchdSqNum = NSXC003001SvcPhysMtrRead.getLastBillingMeterSvcPhysMtrReadGrpSq(glblCmpyCd
                                                                        , dsContrDtlPk
                                                                        , dsContrBllgMtrPk
                                                                        , fromDt
                                                                        , thruDt);
        return dsConrBllgSchdSqNum;

    }
}
