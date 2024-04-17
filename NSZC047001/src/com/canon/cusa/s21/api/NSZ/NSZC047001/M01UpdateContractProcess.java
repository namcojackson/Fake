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

import parts.common.EZDMsg;
import business.db.DS_CONTR_DTLTMsg;
import business.db.DS_CONTR_PRC_EFFTMsg;
import business.parts.NSZC047001PMsg;
import business.parts.NSZC047001_xxBaseLineListPMsg;
import business.parts.NSZC047001_xxBaseLineListPMsgArray;
import business.parts.NSZC047001_xxMtrLineListPMsg;
import business.parts.NSZC047001_xxMtrLineListPMsgArray;
import business.parts.NSZC047008PMsg;
import business.parts.NSZC047008_xxBaseLineListPMsg;

import com.canon.cusa.s21.common.NSX.NSXC001001.constant.DS_CONTR_CTRL_STS;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_STS;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;

/**
 * <pre>
 * Contract Billing Schedule API
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/14/2015   Hitachi         T.Aoyagi        Create          N/A
 * 03/03/2016   Hitachi         K.Kishimoto     Update          QC#4773
 * 2016/08/02   Hitachi         K.Kishimoto     Update          QC#7402
 * 2016/09/28   Hitachi         K.Kishimoto     Update          QC#14853
 * 2017/07/20   Hitachi         K.Yamada        Update          QC#20015
 * 2017/07/25   Hitachi         T.Tomita        Update          QC#20100
 * 2017/08/21   Hitachi         M.Kidokoro      Update          QC#20057
 * 2017/10/18   Hitachi         K.Kitachi       Update          QC#21222
 * 2017/11/17   Hitachi         M.Kidokoro      Update          QC#22606
 * 2017/12/11   Hitachi         T.Tomita        Update          QC#21222
 * 2017/12/20   Hitachi         U.Kim           Update          QC#22282
 * 2023/08/18   CITS            T.Kojima        Update          QC#60846
 *</pre>
 */
public class M01UpdateContractProcess implements ZYPConstant {

    /** onBatchTp */
    private ONBATCH_TYPE onBatchTp;

    protected void doProcess(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {

        this.onBatchTp = onBatchType;
        checkParameter(msgMap);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }
        updateContractProcess(msgMap);
    }

    //Mod Start 03/03/2016 <QC#4773>
    private void updateContractProcess(S21ApiMessageMap msgMap) {

        NSZC047001PMsg pMsg = (NSZC047001PMsg) msgMap.getPmsg();
        NSZC047001_xxBaseLineListPMsgArray baseList = pMsg.xxBaseLineList;
        NSZC047001_xxMtrLineListPMsgArray usageList = pMsg.xxMtrLineList;

        setValue(pMsg.contrCloDay, NSZC047001CommonLogic.convCloDay(pMsg.contrCloDay.getValue()));
        setValue(pMsg.mtrCloDay, NSZC047001CommonLogic.convCloDay(pMsg.mtrCloDay.getValue()));

        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        // Mod Start 08/02/2016 <QC#7402>
        DS_CONTR_DTLTMsg dtlTMsg = NSZC0470Query.getInstance().getDsContrDtlTMsg(glblCmpyCd, pMsg.dsContrDtlPk.getValue());
        String dsContrDtlTp = dtlTMsg.dsContrDtlTpCd.getValue();
        // Mod End   08/02/2016 <QC#7402>
        // ----------------------------------------
        // Base Charge
        // ----------------------------------------
        // START 2017/11/17 M.Kidokoro [QC#22606, MOD]
//        if (FLG_ON_Y.equals(pMsg.baseChrgFlg.getValue())) {
        BASECHARGE: if (FLG_ON_Y.equals(pMsg.baseChrgFlg.getValue())) {
        // END 2017/11/17 M.Kidokoro [QC#22606, MOD]

            NSZC047001_xxBaseLineListPMsg linePMsg = baseList.no(0);
        // Add Start 08/02/2016 <QC#7402>
//            BigDecimal fixedTermAmtRate = NSZC0470Query.getInstance().getFixedTermAmtRate(glblCmpyCd, pMsg.dsContrDtlPk.getValue());
            BigDecimal prmBaseCharge = linePMsg.basePrcDealAmt_BL.getValue();
            // Add Start 2017/07/25 <QC#20100>
            String prmBaseBllgCycleCd = linePMsg.baseBllgCycleCd_BL.getValue();
            // Add End   2017/07/25 <QC#20100>
        // Add End   08/02/2016 <QC#7402>

            // get target Price Effectivity
//            BigDecimal dsContrPrcEffPk = linePMsg.dsContrPrcEffPk_BL.getValue();
//            DS_CONTR_PRC_EFFTMsg dsContrPrcEffTMsg = NSZC0470Query.getInstance().getDsContrPrcEffTMsg(glblCmpyCd, dsContrPrcEffPk);
//
//            boolean isChangedPrice = isChangedBasePrice(dsContrPrcEffTMsg, linePMsg);
//            boolean isChangedTermAmount = isChangedTermAmount(pMsg, linePMsg);
//            boolean isChangedPeriod = isChangedPeriod(msgMap);
//
//            if (!isChangedPrice && !isChangedTermAmount && !isChangedPeriod) {
//                // No changed
//                return;
//            }
//
            String invSchdMaxThruDt = NSZC047001CommonLogic.getInvSchdMaxThruDt(pMsg);
            List<Map<String, Object>> currPrcEffList = NSZC047001CommonLogic.getPrcEffList(pMsg);
            // START 2017/11/17 M.Kidokoro [QC#22606, ADD]
            if (currPrcEffList.size() == 0) {
                break BASECHARGE;
            }
            // END 2017/11/17 M.Kidokoro [QC#22606, ADD]

            // START 2023/08/18 T.Kojima [QC#60846, DEL]
//          if (hasValue(invSchdMaxThruDt)) {
//              NSZC047001CommonLogic.deletePrcEffAndSchdInfo(msgMap, dsContrDtlTp, invSchdMaxThruDt);
//              NSZC047001CommonLogic.updatePrcEffAndSchdInfo(msgMap, invSchdMaxThruDt);
//          } else {
//              for (Map<String, Object> currPrcEff : currPrcEffList) {
//                  NSZC047001CommonLogic.deletePrcEffAndSchdInfo(msgMap, ((BigDecimal) currPrcEff.get("DS_CONTR_PRC_EFF_PK")));
//              }
//          }
            // END 2023/08/18 T.Kojima [QC#60846, DEL]
            String slsDt = pMsg.slsDt.getValue();
            Map<String, Object> orgPrcEff = null;
            for (Map<String, Object> currPrcEff : currPrcEffList) {
                if (slsDt.compareTo((String)currPrcEff.get("CONTR_PRC_EFF_FROM_DT")) >= 0 && slsDt.compareTo((String)currPrcEff.get("CONTR_PRC_EFF_THRU_DT")) <= 0) {
                    orgPrcEff = currPrcEff;
                    break;
                }
            }
            if (orgPrcEff == null) {
                if (slsDt.compareTo((String)currPrcEffList.get(0).get("CONTR_PRC_EFF_FROM_DT")) < 0) {
                    orgPrcEff = currPrcEffList.get(0);
                } else if (slsDt.compareTo((String)currPrcEffList.get(currPrcEffList.size() - 1).get("CONTR_PRC_EFF_THRU_DT")) > 0) {
                    orgPrcEff = currPrcEffList.get(currPrcEffList.size() - 1);
                }
            }
            // START 2023/08/18 T.Kojima [QC#60846, ADD]
            BigDecimal peBaseCharge = (BigDecimal) orgPrcEff.get("BASE_PRC_DEAL_AMT");
            String peBaseBllgCycleCd = (String) orgPrcEff.get("BLLG_CYCLE_CD");
            boolean isCreateNewPe = false;

            // check if either Amount or BllgCycle gets changed.
            if (peBaseCharge.compareTo(prmBaseCharge) != 0 || !peBaseBllgCycleCd.equals(prmBaseBllgCycleCd)) {
                // need to recreate PE
                isCreateNewPe = true;
            }

            if (!isCreateNewPe) {
                invSchdMaxThruDt = pMsg.contrEffThruDt.getValue();
            }

            if (hasValue(invSchdMaxThruDt)) {
                NSZC047001CommonLogic.deletePastPrcEffAndSchdInfo(msgMap, dsContrDtlTp, pMsg.contrEffFromDt.getValue());
                NSZC047001CommonLogic.deletePrcEffAndSchdInfo(msgMap, dsContrDtlTp, invSchdMaxThruDt);
                NSZC047001CommonLogic.updatePrcEffAndSchdInfo(msgMap, invSchdMaxThruDt);
            } else {
                for (Map<String, Object> currPrcEff : currPrcEffList) {
                    NSZC047001CommonLogic.deletePrcEffAndSchdInfo(msgMap, ((BigDecimal) currPrcEff.get("DS_CONTR_PRC_EFF_PK")));
                }
            }
            // END 2023/08/18 T.Kojima [QC#60846, ADD]

            Map<String, Object> fixPrcEff = null;
            // START 2023/08/18 T.Kojima [QC#60846, ADD]
            List<Map<String, Object>> fixPrcEffList = null;
            // END 2023/08/18 T.Kojima [QC#60846, ADD]
            if (hasValue(invSchdMaxThruDt)) {
                // START 2023/08/18 T.Kojima [QC#60846, MOD]
                // List<Map<String, Object>> fixPrcEffList = NSZC047001CommonLogic.getPrcEffList(pMsg);
                fixPrcEffList = NSZC047001CommonLogic.getPrcEffList(pMsg);
                // END 2023/08/18 T.Kojima [QC#60846, MOD]
                if (fixPrcEffList != null && fixPrcEffList.size() > 0) {
                    fixPrcEff = fixPrcEffList.get(fixPrcEffList.size() - 1);
                }
            }
            NSZC047008PMsg m08Pmsg = new NSZC047008PMsg();
            EZDMsg.copy(pMsg, null, m08Pmsg, null);
            int m08Line = 0;
            List<NSZC047008_xxBaseLineListPMsg> m08BaseList = new ArrayList<NSZC047008_xxBaseLineListPMsg>();
            BigDecimal prmAmtRate = linePMsg.basePrcTermDealAmtRate_BL.getValue();
            BigDecimal invoicedAmt = BigDecimal.ZERO;
            BigDecimal peSeqNum = BigDecimal.ONE;
            String effFromDt = pMsg.contrEffFromDt.getValue();
            String effThruDt = pMsg.contrEffThruDt.getValue();
            // Mod Start 08/02/2016 <QC#7402>
            if (fixPrcEff != null) {
                // START 2023/08/18 T.Kojima [QC#60846, DEL]
//              BigDecimal peBaseCharge = (BigDecimal) fixPrcEff.get("BASE_PRC_DEAL_AMT");
//              // Add Start 2017/07/25 <QC#20100>
//              String peBaseBllgCycleCd = (String) fixPrcEff.get("BLLG_CYCLE_CD");
//              // Add End   2017/07/25 <QC#20100>
//              boolean isCreateNewPe = false;
//              // Mod Start 2017/07/25 <QC#20100>
//              if (peBaseCharge.compareTo(prmBaseCharge) != 0 || !peBaseBllgCycleCd.equals(prmBaseBllgCycleCd)) {
//                  isCreateNewPe = true;
//              }
//              // Mod End   2017/07/25 <QC#20100>
                // END 2023/08/18 T.Kojima [QC#60846, DEL]
                Map<String, BigDecimal> smryBasePrcAmt = NSZC0470Query.getInstance().getSummaryBasePrcAmt(glblCmpyCd, pMsg.dsContrDtlPk.getValue(), null, effFromDt, effThruDt, FLG_ON_Y);
                if (smryBasePrcAmt != null) {
                    invoicedAmt = (BigDecimal)smryBasePrcAmt.get("BASE_ACTL_PRC_DEAL_AMT");
                }
                if (hasValue(prmAmtRate)) {
                    prmAmtRate = prmAmtRate.subtract(invoicedAmt);
                }
                if (isCreateNewPe == true) {
                    effFromDt = ZYPDateUtil.addDays(invSchdMaxThruDt, 1);
                    peSeqNum = (BigDecimal) fixPrcEff.get("DS_CONTR_PRC_EFF_SQ_NUM");
                    peSeqNum = peSeqNum.add(BigDecimal.ONE);
                } else {
                    // Mod Start 09/28/2016 <QC#14853>
                    if (hasValue(prmAmtRate)) {
                        BigDecimal peFixTermAmt = NSZC0470Query.getInstance().getFixBaseTermAmtRate(glblCmpyCd, (BigDecimal) fixPrcEff.get("DS_CONTR_PRC_EFF_PK"), null);
                        prmAmtRate = prmAmtRate.add(peFixTermAmt);
                    }
                    // Mod End   09/28/2016 <QC#14853>
                    effFromDt = (String) fixPrcEff.get("CONTR_PRC_EFF_FROM_DT");
                    peSeqNum = (BigDecimal) fixPrcEff.get("DS_CONTR_PRC_EFF_SQ_NUM");
                }
            }
            // Mod End   08/02/2016 <QC#7402>
            // START 2023/08/18 T.Kojima [QC#60846, ADD]
            if (!isCreateNewPe) {
                String minEffFromDtBl = null;
                String maxEffThruDtBl = null;

                for (Map<String, Object> tmpFixPrcEff : fixPrcEffList) {
                    String effFromDtBl = (String) tmpFixPrcEff.get("CONTR_PRC_EFF_FROM_DT");
                    String effThruDtBl = (String) tmpFixPrcEff.get("CONTR_PRC_EFF_THRU_DT");
                    if (DS_CONTR_CTRL_STS.CANCELLED.equals((String) tmpFixPrcEff.get("DS_CONTR_PRC_EFF_STS_CD"))) {
                        continue;
                    }
                    // Get the earliest and latest period of PriceEffective
                    if (minEffFromDtBl == null || effFromDtBl.compareTo(minEffFromDtBl) < 0) {
                        minEffFromDtBl = effFromDtBl;
                    }
                    if (maxEffThruDtBl == null || effThruDtBl.compareTo(maxEffThruDtBl) > 0) {
                        maxEffThruDtBl = effThruDtBl;
                    }
                }

                for (Map<String, Object> tmpFixPrcEff : fixPrcEffList) {
                    boolean minEffFromFlg = false;
                    boolean maxEffThruFlg = false;

                    if (DS_CONTR_CTRL_STS.CANCELLED.equals((String) tmpFixPrcEff.get("DS_CONTR_PRC_EFF_STS_CD"))) {
                        continue;
                    }
                    // Set flags for the earliest and latest period of PriceEffective
                    if (((String) tmpFixPrcEff.get("CONTR_PRC_EFF_FROM_DT")).compareTo(minEffFromDtBl) == 0) {
                        minEffFromFlg = true;
                    }
                    if (((String) tmpFixPrcEff.get("CONTR_PRC_EFF_THRU_DT")).compareTo(maxEffThruDtBl) == 0) {
                        maxEffThruFlg = true;
                    }

                    if (minEffFromFlg) {
                        // set displayed values
                        setValue(m08Pmsg.xxBaseLineList.no(m08Line).effFromDt_BL, pMsg.contrEffFromDt.getValue());
                    } else {
                        setValue(m08Pmsg.xxBaseLineList.no(m08Line).effFromDt_BL, (String) tmpFixPrcEff.get("CONTR_PRC_EFF_FROM_DT"));
                    }
                    if (maxEffThruFlg) {
                        // set displayed values
                        setValue(m08Pmsg.xxBaseLineList.no(m08Line).effThruDt_BL, pMsg.contrEffThruDt.getValue());
                    } else {
                        setValue(m08Pmsg.xxBaseLineList.no(m08Line).effThruDt_BL, (String) tmpFixPrcEff.get("CONTR_PRC_EFF_THRU_DT"));
                    }

                    setValue(m08Pmsg.xxBaseLineList.no(m08Line).dsContrPrcEffPk_BL, (BigDecimal) tmpFixPrcEff.get("DS_CONTR_PRC_EFF_PK"));
                    setValue(m08Pmsg.xxBaseLineList.no(m08Line).dsContrPrcEffSqNum_BL, (BigDecimal) tmpFixPrcEff.get("DS_CONTR_PRC_EFF_SQ_NUM"));
                    setValue(m08Pmsg.xxBaseLineList.no(m08Line).baseBllgCycleCd_BL, (String) tmpFixPrcEff.get("BLLG_CYCLE_CD"));
                    setValue(m08Pmsg.xxBaseLineList.no(m08Line).basePrcDealAmt_BL, (BigDecimal) tmpFixPrcEff.get("BASE_PRC_DEAL_AMT"));
                    setValue(m08Pmsg.xxBaseLineList.no(m08Line).basePrcTermDealAmtRate_BL, (BigDecimal) tmpFixPrcEff.get("BASE_TERM_DEAL_AMT_RATE"));
                    setValue(m08Pmsg.xxBaseLineList.no(m08Line).dsContrPrcEffStsCd_BL, (String) tmpFixPrcEff.get("DS_CONTR_PRC_EFF_STS_CD"));
                    setValue(m08Pmsg.xxBaseLineList.no(m08Line).qltyAsrnHldFlg_BL, (String) tmpFixPrcEff.get("QLTY_ASRN_HLD_FLG"));
                    setValue(m08Pmsg.xxBaseLineList.no(m08Line).qltyAsrnHldPendApvlFlg_BL, (String) tmpFixPrcEff.get("QLTY_ASRN_HLD_PEND_APVL_FLG"));
                    setValue(m08Pmsg.xxBaseLineList.no(m08Line).mtrHldFlg_BL, (String) tmpFixPrcEff.get("MTR_HLD_FLG"));
                    setValue(m08Pmsg.xxBaseLineList.no(m08Line).contrHldFlg_BL,  (String) tmpFixPrcEff.get("CONTR_HLD_FLG"));
                    setValue(m08Pmsg.xxBaseLineList.no(m08Line).bllgHldFlg_BL, (String) tmpFixPrcEff.get("BLLG_HLD_FLG"));
                    m08Line++;
                }
                m08Pmsg.xxBaseLineList.setValidCount(m08Line);
            } else {
            // END 2023/08/18 T.Kojima [QC#60846, ADD]
            setValue(m08Pmsg.xxBaseLineList.no(m08Line).dsContrPrcEffSqNum_BL, peSeqNum);
            m08Pmsg.xxBaseLineList.no(m08Line).dsContrPrcEffPk_BL.clear();
            setValue(m08Pmsg.xxBaseLineList.no(m08Line).effFromDt_BL, effFromDt);
            setValue(m08Pmsg.xxBaseLineList.no(m08Line).effThruDt_BL, effThruDt);
            setValue(m08Pmsg.xxBaseLineList.no(m08Line).baseBllgCycleCd_BL, linePMsg.baseBllgCycleCd_BL);
            setValue(m08Pmsg.xxBaseLineList.no(m08Line).basePrcDealAmt_BL, linePMsg.basePrcDealAmt_BL);
            if (hasValue(prmAmtRate)) {
                setValue(m08Pmsg.xxBaseLineList.no(m08Line).basePrcTermDealAmtRate_BL, prmAmtRate);
            }
            if (orgPrcEff != null) {
                setValue(m08Pmsg.xxBaseLineList.no(m08Line).dsContrPrcEffStsCd_BL, (String) orgPrcEff.get("DS_CONTR_PRC_EFF_STS_CD"));
                setValue(m08Pmsg.xxBaseLineList.no(m08Line).qltyAsrnHldFlg_BL, (String) orgPrcEff.get("QLTY_ASRN_HLD_FLG"));
                setValue(m08Pmsg.xxBaseLineList.no(m08Line).mtrHldFlg_BL, (String) orgPrcEff.get("MTR_HLD_FLG"));
                setValue(m08Pmsg.xxBaseLineList.no(m08Line).contrHldFlg_BL, (String) orgPrcEff.get("CONTR_HLD_FLG"));
                setValue(m08Pmsg.xxBaseLineList.no(m08Line).bllgHldFlg_BL, (String) orgPrcEff.get("BLLG_HLD_FLG"));
                // Add Start 07/20/2017 QC#20015
                setValue(m08Pmsg.xxBaseLineList.no(m08Line).qltyAsrnHldPendApvlFlg_BL, (String) orgPrcEff.get("QLTY_ASRN_HLD_PEND_APVL_FLG"));
                // Add End 07/20/2017 QC#20015
            } else {
                setValue(m08Pmsg.xxBaseLineList.no(m08Line).dsContrPrcEffStsCd_BL, linePMsg.dsContrPrcEffStsCd_BL);
                setValue(m08Pmsg.xxBaseLineList.no(m08Line).qltyAsrnHldFlg_BL, linePMsg.qltyAsrnHldFlg_BL);
                // START 2017/12/20 U.Kim [QC#22282, MOD]
                // setValue(m08Pmsg.xxBaseLineList.no(m08Line).mtrHldFlg_BL, linePMsg.mtrHldFlg_BL);
                setValue(m08Pmsg.xxBaseLineList.no(m08Line).mtrHldFlg_BL, ZYPConstant.FLG_OFF_N);
                // END 2017/12/20 U.Kim [QC#22282, MOD]
                setValue(m08Pmsg.xxBaseLineList.no(m08Line).contrHldFlg_BL, linePMsg.contrHldFlg_BL);
                setValue(m08Pmsg.xxBaseLineList.no(m08Line).bllgHldFlg_BL, linePMsg.bllgHldFlg_BL);
                // Add Start 07/20/2017 QC#20015
                setValue(m08Pmsg.xxBaseLineList.no(m08Line).qltyAsrnHldPendApvlFlg_BL, linePMsg.qltyAsrnHldPendApvlFlg_BL);
                // Add End 07/20/2017 QC#20015
            }
            // Mod Start 2017/12/11 QC#21222
            // START 2017/10/19 K.Kitachi [QC#21222, ADD]
            if (isDtlActive(glblCmpyCd, pMsg.dsContrDtlPk.getValue()) && ZYPDateUtil.compare(effFromDt, slsDt) <= 0 && ZYPDateUtil.compare(slsDt, effThruDt) <= 0) {
                setValue(m08Pmsg.xxBaseLineList.no(m08Line).dsContrPrcEffStsCd_BL, DS_CONTR_DTL_STS.ACTIVE);
            }
            // END 2017/10/19 K.Kitachi [QC#21222, ADD]
            // Mod End 2017/12/11 QC#21222
            m08BaseList.add(m08Pmsg.xxBaseLineList.no(m08Line));
            m08Line++;
            // START 2023/08/18 T.Kojima [QC#60846, ADD]
            }
            // END 2023/08/18 T.Kojima [QC#60846, ADD]
            callCreateContractProcess(m08Pmsg);
            // Add Start 08/02/2016 <QC#7402>
            NSZC047001CommonLogic.updateBacePrcAmtAndTermAmtForDsContrDtl(msgMap, glblCmpyCd, pMsg.dsContrDtlPk.getValue(), slsDt);
            // Add End   08/02/2016 <QC#7402>
        }

        // ----------------------------------------
        // Meter Charge
        // ----------------------------------------
        // START 2017/11/17 M.Kidokoro [QC#22606, MOD]
//        if (FLG_ON_Y.equals(pMsg.usgChrgFlg.getValue())) {
        METERCHARGE: if (FLG_ON_Y.equals(pMsg.usgChrgFlg.getValue())) {
        // END 2017/11/17 M.Kidokoro [QC#22606, MOD]
            NSZC047001_xxMtrLineListPMsg linePMsg = usageList.no(0);
            BigDecimal bllgMtrPk = linePMsg.dsContrBllgMtrPk_ML.getValue();

            List<NSZC047001_xxMtrLineListPMsg> prmXsMtrList = getXsMtrList(bllgMtrPk, usageList);

            // get target Price Effectivity
//            BigDecimal dsContrPrcEffPk = linePMsg.dsContrPrcEffPk_ML.getValue();
//            DS_CONTR_PRC_EFFTMsg dsContrPrcEffTMsg = NSZC0470Query.getInstance().getDsContrPrcEffTMsg(glblCmpyCd, dsContrPrcEffPk);
//
//            boolean isChangedMtr = isChangedMtr(dsContrPrcEffTMsg, prmXsMtrList);
//            boolean isChangedPeriod = isChangedPeriod(msgMap);
//
//            if (!isChangedMtr && !isChangedPeriod) {
//                // No changed
//                return;
//            }

            String invSchdMaxThruDt = NSZC047001CommonLogic.getInvSchdMaxThruDt(pMsg);
            List<Map<String, Object>> currPrcEffList = NSZC047001CommonLogic.getPrcEffList(pMsg);
            // START 2017/11/17 M.Kidokoro [QC#22606, ADD]
            if (currPrcEffList.size() == 0) {
                break METERCHARGE;
            }
            // END 2017/11/17 M.Kidokoro [QC#22606, ADD]

            // START 2023/08/18 T.Kojima [QC#60846, DEL]
//            if (hasValue(invSchdMaxThruDt)) {
//                NSZC047001CommonLogic.deletePrcEffAndSchdInfo(msgMap, dsContrDtlTp, invSchdMaxThruDt);
//                NSZC047001CommonLogic.updatePrcEffAndSchdInfo(msgMap, invSchdMaxThruDt);
//            } else {
//                for (Map<String, Object> currPrcEff : currPrcEffList) {
//                    NSZC047001CommonLogic.deletePrcEffAndSchdInfo(msgMap, ((BigDecimal) currPrcEff.get("DS_CONTR_PRC_EFF_PK")));
//                }
//            }
            // END 2023/08/18 T.Kojima [QC#60846, DEL]
            String slsDt = pMsg.slsDt.getValue();
            Map<String, Object> orgPrcEff = null;
            for (Map<String, Object> currPrcEff : currPrcEffList) {
                if (slsDt.compareTo((String)currPrcEff.get("CONTR_PRC_EFF_FROM_DT")) >= 0 && slsDt.compareTo((String)currPrcEff.get("CONTR_PRC_EFF_THRU_DT")) <= 0) {
                    orgPrcEff = currPrcEff;
                    break;
                }
            }
            if (orgPrcEff == null) {
                if (slsDt.compareTo((String)currPrcEffList.get(0).get("CONTR_PRC_EFF_FROM_DT")) < 0) {
                    orgPrcEff = currPrcEffList.get(0);
                } else if (slsDt.compareTo((String)currPrcEffList.get(currPrcEffList.size() - 1).get("CONTR_PRC_EFF_THRU_DT")) > 0) {
                    orgPrcEff = currPrcEffList.get(currPrcEffList.size() - 1);
                }
            }
            // START 2023/08/18 T.Kojima [QC#60846, ADD]            
            DS_CONTR_PRC_EFFTMsg peTMsg = NSZC0470Query.getInstance().getDsContrPrcEffTMsg(glblCmpyCd, linePMsg.dsContrPrcEffPk_ML.getValue());
            List<Map<String, Object>> peMtrList = NSZC0470Query.getInstance().getDsContrPrcEffMtr(glblCmpyCd, linePMsg.dsContrPrcEffPk_ML.getValue());
            boolean isCreateNewPe = false;
            String peBllgCycleCd = null;
            if (peTMsg != null) {
                peBllgCycleCd = peTMsg.bllgCycleCd.getValue();
            }
            for (int i = 0; i < prmXsMtrList.size(); i++) {
                BigDecimal prmXsMtrCoptQty = prmXsMtrList.get(i).xsMtrCopyQty_ML.getValue();
                BigDecimal prmXsMtrAmtRate = prmXsMtrList.get(i).xsMtrAmtRate_ML.getValue();
                BigDecimal peXsMtrCoptQty = new BigDecimal(0);
                BigDecimal peXsMtrAmtRate = new BigDecimal(0);
                String prmBllgCycleCd = prmXsMtrList.get(i).mtrBllgCycleCd_ML.getValue();
                // Check if the number of tiers has increased
                if (i < peMtrList.size()) {
                    peXsMtrCoptQty = (BigDecimal) peMtrList.get(i).get("XS_MTR_COPY_QTY");
                    peXsMtrAmtRate = (BigDecimal) peMtrList.get(i).get("XS_MTR_AMT_RATE");
                }
                // Check if value has changed
                if (prmXsMtrCoptQty.compareTo(peXsMtrCoptQty) != 0 || prmXsMtrAmtRate.compareTo(peXsMtrAmtRate) != 0 || (hasValue(peBllgCycleCd) && prmBllgCycleCd.compareTo(peBllgCycleCd) != 0)) {
                    isCreateNewPe = true;
                    break;
                    }
            }
            if (!isCreateNewPe) {
                invSchdMaxThruDt = pMsg.contrEffThruDt.getValue();
            }

            if (hasValue(invSchdMaxThruDt)) {
                NSZC047001CommonLogic.deletePastPrcEffAndSchdInfo(msgMap, dsContrDtlTp, pMsg.contrEffFromDt.getValue());
                NSZC047001CommonLogic.deletePrcEffAndSchdInfo(msgMap, dsContrDtlTp, invSchdMaxThruDt);
                NSZC047001CommonLogic.updatePrcEffAndSchdInfo(msgMap, invSchdMaxThruDt);
            } else {
                for (Map<String, Object> currPrcEff : currPrcEffList) {
                    NSZC047001CommonLogic.deletePrcEffAndSchdInfo(msgMap, ((BigDecimal) currPrcEff.get("DS_CONTR_PRC_EFF_PK")));
                }
            }
            // END 2023/08/18 T.Kojima [QC#60846, ADD]
            Map<String, Object> fixPrcEff = null;
            // START 2023/08/18 T.Kojima [QC#60846, ADD]
            List<Map<String, Object>> fixPrcEffList = null;
            // END 2023/08/18 T.Kojima [QC#60846, ADD];
            if (hasValue(invSchdMaxThruDt)) {
                // START 2023/08/18 T.Kojima [QC#60846, MOD]
                //List<Map<String, Object>> fixPrcEffList = NSZC047001CommonLogic.getPrcEffList(pMsg);
                fixPrcEffList = NSZC047001CommonLogic.getPrcEffList(pMsg);
                // END 2023/08/18 T.Kojima [QC#60846, MOD];
                if (fixPrcEffList != null && fixPrcEffList.size() > 0) {
                    fixPrcEff = fixPrcEffList.get(fixPrcEffList.size() - 1);
                }
            }
            NSZC047008PMsg m08Pmsg = new NSZC047008PMsg();
            EZDMsg.copy(pMsg, null, m08Pmsg, null);
            String effFromDt = pMsg.contrEffFromDt.getValue();
            String effThruDt = pMsg.contrEffThruDt.getValue();
            int m08Line = 0;
            BigDecimal peSeqNum = BigDecimal.ONE;
            // Mod Start 08/02/2016 <QC#7402>
            if (fixPrcEff != null) {
                // START 2023/08/18 T.Kojima [QC#60846, DEL]
//              DS_CONTR_PRC_EFFTMsg peTMsg = NSZC0470Query.getInstance().getDsContrPrcEffTMsg(glblCmpyCd, linePMsg.dsContrPrcEffPk_ML.getValue());
//              List<Map<String, Object>> peMtrList = NSZC0470Query.getInstance().getDsContrPrcEffMtr(glblCmpyCd, linePMsg.dsContrPrcEffPk_ML.getValue());
//              boolean isCreateNewPe = false;
//              if (prmXsMtrList.size() != peMtrList.size()) {
//                  isCreateNewPe = true;
//              } else {
//                  // Mod Start 2017/07/25 <QC#20100>
//                  String peBllgCycleCd = null;
//                  if (peTMsg != null) {
//                      peBllgCycleCd = peTMsg.bllgCycleCd.getValue();
//                  }
//                  for (int i = 0; i < prmXsMtrList.size(); i++) {
//                      BigDecimal prmXsMtrCoptQty = prmXsMtrList.get(i).xsMtrCopyQty_ML.getValue();
//                      BigDecimal prmXsMtrAmtRate = prmXsMtrList.get(i).xsMtrAmtRate_ML.getValue();
//                      BigDecimal peXsMtrCoptQty = (BigDecimal) peMtrList.get(i).get("XS_MTR_COPY_QTY");
//                      BigDecimal peXsMtrAmtRate = (BigDecimal) peMtrList.get(i).get("XS_MTR_AMT_RATE");
//                      String prmBllgCycleCd = prmXsMtrList.get(i).mtrBllgCycleCd_ML.getValue();
//                      if (prmXsMtrCoptQty.compareTo(peXsMtrCoptQty) != 0 || prmXsMtrAmtRate.compareTo(peXsMtrAmtRate) != 0 || (hasValue(peBllgCycleCd) && !prmBllgCycleCd.equals(peBllgCycleCd))) {
//                          isCreateNewPe = true;
//                          break;
//                      }
//                  }
//                  // Mod End   2017/07/25 <QC#20100>
//              }
                // END 2023/08/18 T.Kojima [QC#60846, DEL]
                if (isCreateNewPe == true) {
                    effFromDt = ZYPDateUtil.addDays(invSchdMaxThruDt, 1);
                    peSeqNum = (BigDecimal) fixPrcEff.get("DS_CONTR_PRC_EFF_SQ_NUM");
                    peSeqNum = peSeqNum.add(BigDecimal.ONE);
                } else {
//                    BigDecimal peFixTermAmt = NSZC0470Query.getInstance().getFixBaseTermAmtRate(glblCmpyCd, (BigDecimal) fixPrcEff.get("DS_CONTR_PRC_EFF_PK"), null);
                    effFromDt = (String) fixPrcEff.get("CONTR_PRC_EFF_FROM_DT");
                    peSeqNum = (BigDecimal) fixPrcEff.get("DS_CONTR_PRC_EFF_SQ_NUM");
                }
            }
            // Mod End   08/02/2016 <QC#7402>
            // START 2023/08/18 T.Kojima [QC#60846, ADD]
            if (!isCreateNewPe) {
                String minEffFromDtMl = null;
                String maxEffThruDtMl = null;

                for (Map<String, Object> tmpFixPrcEff : fixPrcEffList) {
                    if (DS_CONTR_CTRL_STS.CANCELLED.equals((String) tmpFixPrcEff.get("DS_CONTR_PRC_EFF_STS_CD"))) {
                        continue;
                    }
                    String effFromDtMl = (String) tmpFixPrcEff.get("CONTR_PRC_EFF_FROM_DT");
                    String effThruDtMl = (String) tmpFixPrcEff.get("CONTR_PRC_EFF_THRU_DT");

                    // get the earliest and the latest period of PE
                    if (minEffFromDtMl == null || effFromDtMl.compareTo(minEffFromDtMl) < 0) {
                        minEffFromDtMl = effFromDtMl;
                    }
                    if (maxEffThruDtMl == null || effFromDtMl.compareTo(minEffFromDtMl) > 0) {
                        maxEffThruDtMl = effThruDtMl;
                    }
                }

                for (Map<String, Object> tmpFixPrcEff : fixPrcEffList) {
                    if (DS_CONTR_CTRL_STS.CANCELLED.equals((String) tmpFixPrcEff.get("DS_CONTR_PRC_EFF_STS_CD"))) {
                        continue;
                    }
                    List<Map<String, Object>> tmpPeMtrList = NSZC0470Query.getInstance().getDsContrPrcEffMtr(glblCmpyCd, (BigDecimal) tmpFixPrcEff.get("DS_CONTR_PRC_EFF_PK"));
                    ArrayList<BigDecimal> xsCopyList = new ArrayList<BigDecimal>();

                    for (Map<String, Object> tmpPeMtr : tmpPeMtrList) {
                        // for DS_CONTR_PRC_EFF_MTR(including multi tier)
                        for (NSZC047001_xxMtrLineListPMsg prmXsMtrPMsg : prmXsMtrList) {
                            BigDecimal dsContrBllgMtrPkMl = (BigDecimal) tmpFixPrcEff.get("DS_CONTR_BLLG_MTR_PK");
                            String effFromDtMl = (String) tmpFixPrcEff.get("CONTR_PRC_EFF_FROM_DT");
                            String effThruDtMl = (String) tmpFixPrcEff.get("CONTR_PRC_EFF_THRU_DT");

                            if (dsContrBllgMtrPkMl.compareTo(prmXsMtrPMsg.dsContrBllgMtrPk_ML.getValue()) == 0) {
                                boolean minEffFromFlg = false;
                                boolean maxEffThruFlg = false;

                                BigDecimal xsCopyPk = new BigDecimal(0);
                                // set flags for the earliest and latest period of PE
                                if (effFromDtMl.compareTo(minEffFromDtMl) == 0) {
                                    minEffFromFlg = true;
                                }
                                if (effThruDtMl.compareTo(maxEffThruDtMl) == 0) {
                                    maxEffThruFlg = true;
                                }
                                // check if xsCopyPk is already registered
                                xsCopyPk = (BigDecimal) tmpPeMtr.get("XS_MTR_COPY_QTY");
                                if (xsCopyList.contains(xsCopyPk)) {
                                    continue;
                                }
                                xsCopyList.add(xsCopyPk);

                                if (maxEffThruFlg) {
                                    // set displayed values
                                    setValue(m08Pmsg.xxMtrLineList.no(m08Line).effThruDt_ML, pMsg.contrEffThruDt.getValue());
                                } else {
                                    // set original PE Meter values
                                    setValue(m08Pmsg.xxMtrLineList.no(m08Line).effThruDt_ML, (String) tmpFixPrcEff.get("CONTR_PRC_EFF_THRU_DT"));
                                }
                                if (minEffFromFlg) {
                                    // set displayed values
                                    setValue(m08Pmsg.xxMtrLineList.no(m08Line).effFromDt_ML, pMsg.contrEffFromDt.getValue());
                                } else {
                                    setValue(m08Pmsg.xxMtrLineList.no(m08Line).effFromDt_ML, (String) tmpFixPrcEff.get("CONTR_PRC_EFF_FROM_DT"));
                                }
                                // for DS_CONTR_PRC_EFF
                                setValue(m08Pmsg.xxMtrLineList.no(m08Line).xsMtrCopyQty_ML, (BigDecimal) tmpPeMtr.get("XS_MTR_COPY_QTY"));
                                setValue(m08Pmsg.xxMtrLineList.no(m08Line).xsMtrAmtRate_ML, (BigDecimal) tmpPeMtr.get("XS_MTR_AMT_RATE"));
                                setValue(m08Pmsg.xxMtrLineList.no(m08Line).xsMtrFirstFlg_ML, (String) tmpPeMtr.get("XS_MTR_FIRST_FLG"));
                                setValue(m08Pmsg.xxMtrLineList.no(m08Line).dsContrBllgMtrPk_ML, (BigDecimal) tmpFixPrcEff.get("DS_CONTR_BLLG_MTR_PK"));
                                setValue(m08Pmsg.xxMtrLineList.no(m08Line).contrXsCopyPk_ML, (BigDecimal) tmpPeMtr.get("CONTR_XS_COPY_PK"));
                                setValue(m08Pmsg.xxMtrLineList.no(m08Line).dsContrPrcEffPk_ML, (BigDecimal) tmpFixPrcEff.get("DS_CONTR_PRC_EFF_PK"));
                                setValue(m08Pmsg.xxMtrLineList.no(m08Line).dsContrPrcEffSqNum_ML, (BigDecimal) tmpFixPrcEff.get("DS_CONTR_PRC_EFF_SQ_NUM"));
                                setValue(m08Pmsg.xxMtrLineList.no(m08Line).mtrBllgCycleCd_ML, (String) tmpFixPrcEff.get("BLLG_CYCLE_CD"));
                                setValue(m08Pmsg.xxMtrLineList.no(m08Line).dsContrPrcEffStsCd_ML, (String) tmpFixPrcEff.get("DS_CONTR_PRC_EFF_STS_CD"));
                                setValue(m08Pmsg.xxMtrLineList.no(m08Line).qltyAsrnHldFlg_ML, (String) tmpFixPrcEff.get("QLTY_ASRN_HLD_FLG"));
                                setValue(m08Pmsg.xxMtrLineList.no(m08Line).mtrHldFlg_ML, (String) tmpFixPrcEff.get("MTR_HLD_FLG"));
                                setValue(m08Pmsg.xxMtrLineList.no(m08Line).contrHldFlg_ML, (String) tmpFixPrcEff.get("CONTR_HLD_FLG"));
                                setValue(m08Pmsg.xxMtrLineList.no(m08Line).bllgHldFlg_ML, (String) tmpFixPrcEff.get("BLLG_HLD_FLG"));
                                m08Line++;
                            }
                        }
                    }
                }
                m08Pmsg.xxMtrLineList.setValidCount(m08Line);
            } else {
            // END 2023/05/26 T.Kojima [QC#60846, ADD]
            for (int i = 0; i < pMsg.xxMtrLineList.getValidCount(); i++) {
                setValue(m08Pmsg.xxMtrLineList.no(m08Line).dsContrPrcEffSqNum_ML, peSeqNum);
                m08Pmsg.xxMtrLineList.no(m08Line).dsContrPrcEffPk_ML.clear();
                setValue(m08Pmsg.xxMtrLineList.no(m08Line).effFromDt_ML, effFromDt);
                setValue(m08Pmsg.xxMtrLineList.no(m08Line).effThruDt_ML, effThruDt);
                setValue(m08Pmsg.xxMtrLineList.no(m08Line).mtrBllgCycleCd_ML, pMsg.xxMtrLineList.no(i).mtrBllgCycleCd_ML);
                setValue(m08Pmsg.xxMtrLineList.no(m08Line).dsContrBllgMtrPk_ML, pMsg.xxMtrLineList.no(i).dsContrBllgMtrPk_ML);
                setValue(m08Pmsg.xxMtrLineList.no(m08Line).contrXsCopyPk_ML, pMsg.xxMtrLineList.no(i).contrXsCopyPk_ML);
                setValue(m08Pmsg.xxMtrLineList.no(m08Line).xsMtrCopyQty_ML, pMsg.xxMtrLineList.no(i).xsMtrCopyQty_ML);
                setValue(m08Pmsg.xxMtrLineList.no(m08Line).xsMtrAmtRate_ML, pMsg.xxMtrLineList.no(i).xsMtrAmtRate_ML);
                setValue(m08Pmsg.xxMtrLineList.no(m08Line).xsMtrFirstFlg_ML, pMsg.xxMtrLineList.no(i).xsMtrFirstFlg_ML);
                if (orgPrcEff != null) {
                    setValue(m08Pmsg.xxMtrLineList.no(m08Line).dsContrPrcEffStsCd_ML, (String) orgPrcEff.get("DS_CONTR_PRC_EFF_STS_CD"));
                    setValue(m08Pmsg.xxMtrLineList.no(m08Line).qltyAsrnHldFlg_ML, (String) orgPrcEff.get("QLTY_ASRN_HLD_FLG"));
                    setValue(m08Pmsg.xxMtrLineList.no(m08Line).mtrHldFlg_ML, (String) orgPrcEff.get("MTR_HLD_FLG"));
                    setValue(m08Pmsg.xxMtrLineList.no(m08Line).contrHldFlg_ML, (String) orgPrcEff.get("CONTR_HLD_FLG"));
                    setValue(m08Pmsg.xxMtrLineList.no(m08Line).bllgHldFlg_ML, (String) orgPrcEff.get("BLLG_HLD_FLG"));
                } else {
                    setValue(m08Pmsg.xxMtrLineList.no(m08Line).dsContrPrcEffStsCd_ML, pMsg.xxMtrLineList.no(i).dsContrPrcEffStsCd_ML);
                    setValue(m08Pmsg.xxMtrLineList.no(m08Line).qltyAsrnHldFlg_ML, pMsg.xxMtrLineList.no(i).qltyAsrnHldFlg_ML);
                    setValue(m08Pmsg.xxMtrLineList.no(m08Line).mtrHldFlg_ML, pMsg.xxMtrLineList.no(i).mtrHldFlg_ML);
                    setValue(m08Pmsg.xxMtrLineList.no(m08Line).contrHldFlg_ML, pMsg.xxMtrLineList.no(i).contrHldFlg_ML);
                    setValue(m08Pmsg.xxMtrLineList.no(m08Line).bllgHldFlg_ML, pMsg.xxMtrLineList.no(i).bllgHldFlg_ML);
                }
                // Mod Start 2017/12/11 QC#21222
                // START 2017/10/19 K.Kitachi [QC#21222, ADD]
                if (isDtlActive(glblCmpyCd, pMsg.dsContrDtlPk.getValue()) && ZYPDateUtil.compare(effFromDt, slsDt) <= 0 && ZYPDateUtil.compare(slsDt, effThruDt) <= 0) {
                    setValue(m08Pmsg.xxMtrLineList.no(m08Line).dsContrPrcEffStsCd_ML, DS_CONTR_DTL_STS.ACTIVE);
                }
                // END 2017/10/19 K.Kitachi [QC#21222, ADD]
                // Mod End 2017/12/11 QC#21222
                m08Line++;
            }
            m08Pmsg.xxMtrLineList.setValidCount(m08Line);
            // START 2023/08/18 T.Kojima [QC#60846, ADD]
            }
            // END 2023/08/18 T.Kojima [QC#60846, ADD]
            callCreateContractProcess(m08Pmsg);

            // START 2017/08/21 M.Kidokoro [QC#20057, ADD]
            NSZC047001CommonLogic.updateBllgMtrBllgCycleCdForDsContrBllgMtr(msgMap, glblCmpyCd, bllgMtrPk, slsDt);
            // END 2017/08/21 M.Kidokoro [QC#20057, ADD]
        }
    }
    //Mod End   03/03/2016 <QC#4773>

    private void checkParameter(S21ApiMessageMap msgMap) {

        NSZC047001PMsg pMsg = (NSZC047001PMsg) msgMap.getPmsg();
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

    private boolean isChangedBasePrice(DS_CONTR_PRC_EFFTMsg dsContrPrcEffTMsg, NSZC047001_xxBaseLineListPMsg linePMsg) {

        BigDecimal prmBasePrcDealAmt = linePMsg.basePrcDealAmt_BL.getValue();
        BigDecimal existBasePrcDealAmt = dsContrPrcEffTMsg.basePrcDealAmt.getValue();

        if (!hasValue(prmBasePrcDealAmt) && !hasValue(existBasePrcDealAmt)) {
            return false;
        }
        if (!hasValue(prmBasePrcDealAmt) || !hasValue(existBasePrcDealAmt)) {
            return true;
        }
        if (prmBasePrcDealAmt.compareTo(existBasePrcDealAmt) == 0) {
            return false;
        }
        return true;
    }

    private boolean isChangedTermAmount(NSZC047001PMsg pMsg, NSZC047001_xxBaseLineListPMsg linePMsg) {

        BigDecimal prmBasePrcTermDealAmtRate = linePMsg.basePrcTermDealAmtRate_BL.getValue();
        if (!hasValue(prmBasePrcTermDealAmtRate)) {
            return true;
        }

        BigDecimal existBasePrcTermDealAmtRate = NSZC0470Query.getInstance().getTermAmtRate(pMsg.glblCmpyCd.getValue(), pMsg.dsContrDtlPk.getValue());
        if (!hasValue(existBasePrcTermDealAmtRate)) {
            return true;
        }
        if (prmBasePrcTermDealAmtRate.compareTo(existBasePrcTermDealAmtRate) == 0) {
            return false;
        }
        return true;
    }

    private boolean isChangedMtr(DS_CONTR_PRC_EFFTMsg dsContrPrcEffTMsg, List<NSZC047001_xxMtrLineListPMsg> prmXsMtrList) {

        String glblCmpyCd = dsContrPrcEffTMsg.glblCmpyCd.getValue();
        BigDecimal dsContrPrcEffPk = dsContrPrcEffTMsg.dsContrPrcEffPk.getValue();

        List<Map<String, Object>> xsMtrList = NSZC0470Query.getInstance().getDsContrPrcEffMtr(glblCmpyCd, dsContrPrcEffPk);

        int prmXsMtrCount = prmXsMtrList.size();
        int existXsMtrCount = xsMtrList.size();
        if (prmXsMtrCount != existXsMtrCount) {
            return true;
        }

        boolean isExist = false;

        for (int i = 0; i < prmXsMtrList.size(); i++) {

            isExist = false;
            for (int j = 0; j < xsMtrList.size(); j++) {
                BigDecimal prmMtrCopyQty = prmXsMtrList.get(i).xsMtrCopyQty_ML.getValue();
                BigDecimal prmXsMtrAmtRate = prmXsMtrList.get(i).xsMtrAmtRate_ML.getValue();
                BigDecimal existMtrCopyQty = (BigDecimal) xsMtrList.get(j).get("XS_MTR_COPY_QTY");
                BigDecimal existXsMtrAmtRate = (BigDecimal) xsMtrList.get(j).get("XS_MTR_AMT_RATE");

                if (prmMtrCopyQty.compareTo(existMtrCopyQty) == 0
                        && prmXsMtrAmtRate.compareTo(existXsMtrAmtRate) == 0) {
                    isExist = true;
                    break;
                }
            }
            if (!isExist) {
                return true;
            }
        }

        return false;
    }

    private boolean isChangedPeriod(S21ApiMessageMap msgMap) {

        NSZC047001PMsg pMsg = (NSZC047001PMsg) msgMap.getPmsg();

        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        String baseChrgFlg = pMsg.baseChrgFlg.getValue();
        BigDecimal dsContrDtlPk = pMsg.dsContrDtlPk.getValue();
        BigDecimal dsContrBllgMtrPk = null;
        if (pMsg.xxMtrLineList.getValidCount() > 0) {
            dsContrBllgMtrPk = pMsg.xxMtrLineList.no(0).dsContrBllgMtrPk_ML.getValue();
        }

        String prmContrEffFromDt = pMsg.contrEffFromDt.getValue();
        String prmContrEffThruDt = pMsg.contrEffThruDt.getValue();
        String existContrEffFromDt = "";
        String existContrEffThruDt = "";

        Map<String, String> prcEffPeriodInfo = NSZC0470Query.getInstance().getPrcEffPeriod(glblCmpyCd, dsContrDtlPk, dsContrBllgMtrPk, baseChrgFlg);
        if (prcEffPeriodInfo != null) {
            existContrEffFromDt = prcEffPeriodInfo.get("CONTR_PRC_EFF_FROM_DT");
            existContrEffThruDt = prcEffPeriodInfo.get("CONTR_PRC_EFF_THRU_DT");
        }

        if (!prmContrEffFromDt.equals(existContrEffFromDt)) {
            return true;
        }
        if (!prmContrEffThruDt.equals(existContrEffThruDt)) {
            return true;
        }
        return false;
    }

    private void callCreateContractProcess(NSZC047008PMsg m08Pmsg) {
        S21ApiMessageMap newMsgMap = new S21ApiMessageMap(m08Pmsg);
        M08PriceEffectivityProcess createContractProcess = new M08PriceEffectivityProcess();
        createContractProcess.doProcess(newMsgMap, this.onBatchTp);
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

    // Add Start 2017/12/11 QC#21222
    private boolean isDtlActive(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        DS_CONTR_DTLTMsg tmsg = NSZC0470Query.getInstance().getDsContrDtlTMsg(glblCmpyCd, dsContrDtlPk);
        if (tmsg == null || !DS_CONTR_DTL_STS.ACTIVE.equals(tmsg.dsContrDtlStsCd.getValue())) {
            return false;
        }
        return true;
    }
    // Add End 2017/12/11 QC#21222
}
