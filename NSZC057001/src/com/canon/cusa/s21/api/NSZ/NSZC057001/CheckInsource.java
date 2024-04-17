/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC057001;

import static com.canon.cusa.s21.api.NSZ.NSZC057001.constant.NSZC057001Constant.*;
import static com.canon.cusa.s21.api.NSZ.NSZC057001.NSZC057001CommonLogic.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;

import com.canon.cusa.s21.common.NSX.NSXC001001.CovTmplInfo;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001GetCovTmpl;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_VLD_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

import business.db.BILL_TO_CUSTTMsg;
import business.db.BILL_TO_CUSTTMsgArray;
import business.db.SELL_TO_CUSTTMsg;
import business.db.SELL_TO_CUSTTMsgArray;
import business.db.DS_CONTRTMsg;
import business.db.DS_CONTR_BLLG_MTRTMsg;
import business.db.DS_CONTR_BLLG_MTRTMsgArray;
import business.db.DS_CONTR_DTLTMsg;
import business.db.DS_CONTR_DTLTMsgArray;
import business.db.DS_CONTR_VLD_RSLT_WRKTMsg;
import business.db.DS_CONTR_VLD_RSLT_WRKTMsgArray;
import business.parts.NSZC057001PMsg;

/**
 * <pre>
 * Contract Validation
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/07   Hitachi         K.Yamada        Create          QC#3051
 * 2016/06/30   Hitachi         K.Yamada        Update          QC#3051
 * 2017/02/14   Hitachi         T.Mizuki        Update          QC#17554
 * 2017/04/17   Hitachi         T.Tomita        Update          QC#18155
 * 2017/04/20   Hitachi         T.Tomita        Update          QC#18383
 * 2017/04/24   Hitachi         K.Kitachi       Update          QC#17554,18377
 * 2017/05/12   Hitachi         K.Kitachi       Update          QC#18535
 * 2017/06/22   Hitachi         K.Kitachi       Update          QC#19340
 * 2017/07/27   Hitachi         T.Tomita        Update          QC#20226
 * 2017/07/31   Hitachi         T.Tomita        Update          QC#20362
 * 2017/07/31   Hitachi         T.Tomita        Update          QC#18764
 * 2017/08/03   Hitachi         K.Kitachi       Update          QC#20224
 * 2017/08/08   Hitachi         K.Kitachi       Update          QC#20531
 * 2017/08/16   Hitachi         T.Tomita        Update          QC#18761
 * 2017/09/13   Hitachi         U.Kim           Update          QC#20896
 * 2017/11/16   Hitachi         K.Yamada        Update          QC#21179
 * 2018/02/02   Hitachi         U.Kim           Update          QC#23687
 * 2018/02/14   Hitachi         K.Kojima        Update          QC#24145
 * 2018/03/15   Hitachi         K.Kojima        Update          QC#22611
 * 2018/05/21   Hitachi         K.Kitachi       Update          QC#26217
 * 2018/05/22   Hitachi         K.Kim           Update          QC#26201
 * </pre>
 */
public class CheckInsource {

    /**
     * checkCfsBillingServiceChargeZeroDiff
     * @param mainClass NSZC057001
     * @param param NSZC057001PMsg
     * @return DS_CONTR_VLD_RSLT_WRKTMsgArray
     */
    public static DS_CONTR_VLD_RSLT_WRKTMsgArray checkCfsBillingServiceChargeZeroDiff(NSZC057001 mainClass, NSZC057001PMsg param) {

        DS_CONTR_VLD_RSLT_WRKTMsgArray rtrnTMsgArray = new DS_CONTR_VLD_RSLT_WRKTMsgArray();
        rtrnTMsgArray.setMsgList(new DS_CONTR_VLD_RSLT_WRKTMsg[MAX_SIZE]);
        int arrayIdx = 0;

        DS_CONTR_VLD_RSLT_WRKTMsg rtrnTMsg;

        DS_CONTRTMsg dsContrTMsg = mainClass.dsContrCache.get(KEY);
        // START 2017/06/22 K.Kitachi [QC#19340, MOD]
        // START 2018/02/02 U.Kim [QC#23687,MOD]
        // if (!isLeaseCompany(dsContrTMsg)) {
        if (!isLeaseCompany(mainClass, dsContrTMsg)) {
        // END 2018/02/02 U.Kim [QC#23687,MOD]
        // END 2017/06/22 K.Kitachi [QC#19340, MOD]
            return rtrnTMsgArray;
        }

        DS_CONTR_DTLTMsgArray dsContrDtlTMsgArray = mainClass.dsContrDtlArrayCache.get(KEY);
        // Add Start 2017/07/31 QC#18764
        BigDecimal aggTotalBasePrcDealAmt = BigDecimal.ZERO;
        BigDecimal aggTotalBasePerCycleDealAmt = BigDecimal.ZERO;
        Map<BigDecimal, String> dsContrDtlMap = new HashMap<BigDecimal, String>();
        // Add End 2017/07/31 QC#18764

        for (int i = 0; i < dsContrDtlTMsgArray.getValidCount(); i++) {
            DS_CONTR_DTLTMsg dsContrDtlTMsg = dsContrDtlTMsgArray.no(i);

            // get machine & contract info
            Map<String, Object> machAndContrInfo = getMachAndContrInfo(mainClass, param.glblCmpyCd.getValue(), dsContrDtlTMsg.dsContrDtlPk.getValue());
            if (machAndContrInfo == null) {
                continue;
            }
            // START 2017/04/24 K.Kitachi [QC#17554, ADD]
            String mainUnitLineFlg = (String) machAndContrInfo.get("MAIN_UNIT_LINE_FLG");
            if (mainUnitLineFlg.equals(ZYPConstant.FLG_OFF_N)) {
                continue;
            }
            // END 2017/04/24 K.Kitachi [QC#17554, ADD]
            String serNum = (String) machAndContrInfo.get("SER_NUM");
            // mod start 2017/02/14 CSA QC#17554
            if (!hasValue(serNum)) {
                continue;
            }
            // mod end 2017/02/14 CSA QC#17554
            String baseChrgFlg = (String) machAndContrInfo.get("BASE_CHRG_FLG");
            if (baseChrgFlg.equals(ZYPConstant.FLG_OFF_N)) {
                continue;
            }
            // Add Start 2017/07/27 QC#20226
            String procDt = getProcDt(param.slsDt.getValue(), dsContrDtlTMsg);
            // Add End 2017/07/27 QC#20226

            // Mod Start 2017/07/27 QC#20226
            Map<String, Object> dsContrPrcEff = getDsContrPrcEff(mainClass, param.glblCmpyCd.getValue(), dsContrDtlTMsg.dsContrDtlPk.getValue(), procDt);
            // Mod End 2017/07/27 QC#20226
            if (dsContrPrcEff == null) {
                continue;
            }
            BigDecimal dsContrPrcEffPk = (BigDecimal) dsContrPrcEff.get("DS_CONTR_PRC_EFF_PK");
            BigDecimal basePrcDealAmt = (BigDecimal) dsContrPrcEff.get("BASE_PRC_DEAL_AMT");
            // START 2017/04/24 K.Kitachi [QC#17554, ADD]
            // Mod Start 2017/07/27 QC#20226
            BigDecimal sumBasePrcDealAmtOfAcc = getSumBasePrcDealAmtOfAcc(mainClass, param.glblCmpyCd.getValue(), dsContrDtlTMsg.dsContrDtlPk.getValue(), procDt);
            // Mod End 2017/07/27 QC#20226
            if (hasValue(basePrcDealAmt) && hasValue(sumBasePrcDealAmtOfAcc)) {
                basePrcDealAmt = basePrcDealAmt.add(sumBasePrcDealAmtOfAcc);
            } else if (!hasValue(basePrcDealAmt) && hasValue(sumBasePrcDealAmtOfAcc)) {
                basePrcDealAmt = sumBasePrcDealAmtOfAcc;
            }
            // END 2017/04/24 K.Kitachi [QC#17554, ADD]
            if (!hasValue(basePrcDealAmt) || basePrcDealAmt.compareTo(BigDecimal.ZERO) != 0) {
                continue;
            }
            BigDecimal sumBasePerCycleDealAmt = getSumBasePerCycleDealAmtForCheckInsource(mainClass, param.glblCmpyCd.getValue(), serNum);
            // Add Start 2017/07/31 QC#18764
            aggTotalBasePrcDealAmt = aggTotalBasePrcDealAmt.add(basePrcDealAmt);
            if (hasValue(sumBasePerCycleDealAmt)) {
                aggTotalBasePerCycleDealAmt = aggTotalBasePerCycleDealAmt.add(sumBasePerCycleDealAmt);
            }
            if (DS_CONTR_CATG.AGGREGATE.equals(dsContrTMsg.dsContrCatgCd.getValue())) {
                if (!dsContrDtlMap.containsKey(dsContrDtlTMsg.dsContrDtlPk.getValue())) {
                    dsContrDtlMap.put(dsContrDtlTMsg.dsContrDtlPk.getValue(), serNum);
                }
                continue;
            }
            // Add End 2017/07/31 QC#18764

            // START 2018/05/22 K.Kim [QC#26201, ADD]
            String usgChrgFlg = (String) machAndContrInfo.get("USG_CHRG_FLG");
            if (baseChrgFlg.equals(ZYPConstant.FLG_ON_Y) && usgChrgFlg.equals(ZYPConstant.FLG_OFF_N) && basePrcDealAmt.compareTo(BigDecimal.ZERO) == 0) {
                continue;
            }
            // END 2018/05/22 K.Kim [QC#26201, ADD]

            rtrnTMsg = new DS_CONTR_VLD_RSLT_WRKTMsg();
            setValue(rtrnTMsg.dsContrPk, dsContrTMsg.dsContrPk);
            setValue(rtrnTMsg.dsContrDtlPk, dsContrDtlTMsg.dsContrDtlPk);
            setValue(rtrnTMsg.dsContrVldStsCd, DS_CONTR_VLD_STS.SUCCESS);
            // START 2018/02/14 K.Kojima [QC#24145,ADD]
            if (!existCfsLeaseContrForBeforeCheck(mainClass, dsContrTMsg.glblCmpyCd.getValue(), serNum)) {
                setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(NSZM1321E), DS_CONTR_DTL_PK, dsContrDtlTMsg.dsContrDtlPk.getValue());
            } else
            // END 2018/02/14 K.Kojima [QC#24145,ADD]
            // mod start 2017/04/17 CSA Defect#18155
            if (hasValue(sumBasePerCycleDealAmt) && sumBasePerCycleDealAmt.compareTo(BigDecimal.ZERO) != 0) {
            // mod end 2017/04/17 CSA Defect#18155
                setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(NSZM0984E, new String[] {serNum }), DS_CONTR_PRC_EFF_PK, dsContrPrcEffPk);
            }
            EZDMsg.copy(rtrnTMsg, null, rtrnTMsgArray.no(arrayIdx++), null);
            rtrnTMsgArray.setValidCount(rtrnTMsgArray.getValidCount() + 1);
        }
        // Add Start 2017/07/31 QC#18764
        if (!DS_CONTR_CATG.AGGREGATE.equals(dsContrTMsg.dsContrCatgCd.getValue()) || dsContrDtlMap.isEmpty()) {
            return rtrnTMsgArray;
        }

        if (!hasValue(aggTotalBasePrcDealAmt) || aggTotalBasePrcDealAmt.compareTo(BigDecimal.ZERO) != 0) {
            return rtrnTMsgArray;
        }

        boolean aggLineErrFlg = false;
        if (hasValue(aggTotalBasePerCycleDealAmt) && aggTotalBasePerCycleDealAmt.compareTo(BigDecimal.ZERO) != 0) {
            aggLineErrFlg = true;
        }

        for (Map.Entry<BigDecimal, String> dsContrDtl : dsContrDtlMap.entrySet()) {
            rtrnTMsg = new DS_CONTR_VLD_RSLT_WRKTMsg();
            setValue(rtrnTMsg.dsContrPk, dsContrTMsg.dsContrPk);
            setValue(rtrnTMsg.dsContrDtlPk, dsContrDtl.getKey());
            setValue(rtrnTMsg.dsContrVldStsCd, DS_CONTR_VLD_STS.SUCCESS);
            if (aggLineErrFlg) {
                setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(NSZM0984E, new String[] {dsContrDtl.getValue() }), DS_CONTR_DTL_PK, dsContrDtl.getKey());
            }
            EZDMsg.copy(rtrnTMsg, null, rtrnTMsgArray.no(rtrnTMsgArray.getValidCount()), null);
            rtrnTMsgArray.setValidCount(rtrnTMsgArray.getValidCount() + 1);
        }
        // Add End 2017/07/31 QC#18764
        return rtrnTMsgArray;
    }

    /**
     * checkCfsBillingServiceChargeNonZeroDiff
     * @param mainClass NSZC057001
     * @param param NSZC057001PMsg
     * @return DS_CONTR_VLD_RSLT_WRKTMsgArray
     */
    public static DS_CONTR_VLD_RSLT_WRKTMsgArray checkCfsBillingServiceChargeNonZeroDiff(NSZC057001 mainClass, NSZC057001PMsg param) {

        DS_CONTR_VLD_RSLT_WRKTMsgArray rtrnTMsgArray = new DS_CONTR_VLD_RSLT_WRKTMsgArray();
        rtrnTMsgArray.setMsgList(new DS_CONTR_VLD_RSLT_WRKTMsg[MAX_SIZE]);
        int arrayIdx = 0;

        DS_CONTR_VLD_RSLT_WRKTMsg rtrnTMsg;

        DS_CONTRTMsg dsContrTMsg = mainClass.dsContrCache.get(KEY);
        // START 2017/06/22 K.Kitachi [QC#19340, MOD]
        // START 2018/02/02 U.Kim [QC#23687,MOD]
        // if (!isLeaseCompany(dsContrTMsg)) {
        if (!isLeaseCompany(mainClass, dsContrTMsg)) {
        // END 2018/02/02 U.Kim [QC#23687,MOD]
        // END 2017/06/22 K.Kitachi [QC#19340, MOD]
            return rtrnTMsgArray;
        }

        DS_CONTR_DTLTMsgArray dsContrDtlTMsgArray = mainClass.dsContrDtlArrayCache.get(KEY);
        // Add Start 2017/07/31 QC#18764
        BigDecimal aggTotalBasePrcDealAmt = BigDecimal.ZERO;
        BigDecimal aggTotalBasePerCycleDealAmt = BigDecimal.ZERO;
        Map<BigDecimal, String> dsContrDtlMap = new HashMap<BigDecimal, String>();
        // Add End 2017/07/31 QC#18764

        for (int i = 0; i < dsContrDtlTMsgArray.getValidCount(); i++) {
            DS_CONTR_DTLTMsg dsContrDtlTMsg = dsContrDtlTMsgArray.no(i);

            // get machine & contract info
            Map<String, Object> machAndContrInfo = getMachAndContrInfo(mainClass, param.glblCmpyCd.getValue(), dsContrDtlTMsg.dsContrDtlPk.getValue());
            if (machAndContrInfo == null) {
                continue;
            }
            // START 2017/04/24 K.Kitachi [QC#17554, ADD]
            String mainUnitLineFlg = (String) machAndContrInfo.get("MAIN_UNIT_LINE_FLG");
            if (mainUnitLineFlg.equals(ZYPConstant.FLG_OFF_N)) {
                continue;
            }
            // END 2017/04/24 K.Kitachi [QC#17554, ADD]
            String serNum = (String) machAndContrInfo.get("SER_NUM");
            // mod start 2017/02/14 CSA QC#17554
            if (!hasValue(serNum)) {
                continue;
            }
            // mod end 2017/02/14 CSA QC#17554
            String baseChrgFlg = (String) machAndContrInfo.get("BASE_CHRG_FLG");
            if (baseChrgFlg.equals(ZYPConstant.FLG_OFF_N)) {
                continue;
            }
            // Add Start 2017/07/27 QC#20226
            String procDt = getProcDt(param.slsDt.getValue(), dsContrDtlTMsg);
            // Add End 2017/07/27 QC#20226
            // Mod Start 2017/07/27 QC#20226
            Map<String, Object> dsContrPrcEff = getDsContrPrcEff(mainClass, param.glblCmpyCd.getValue(), dsContrDtlTMsg.dsContrDtlPk.getValue(), procDt);
            // Mod End 2017/07/27 QC#20226
            if (dsContrPrcEff == null) {
                continue;
            }
            BigDecimal dsContrPrcEffPk = (BigDecimal) dsContrPrcEff.get("DS_CONTR_PRC_EFF_PK");
            BigDecimal basePrcDealAmt = (BigDecimal) dsContrPrcEff.get("BASE_PRC_DEAL_AMT");
            // START 2017/04/24 K.Kitachi [QC#17554, ADD]
            // Mod Start 2017/07/27 QC#20226
            BigDecimal sumBasePrcDealAmtOfAcc = getSumBasePrcDealAmtOfAcc(mainClass, param.glblCmpyCd.getValue(), dsContrDtlTMsg.dsContrDtlPk.getValue(), procDt);
            // Mod End 2017/07/27 QC#20226
            if (hasValue(basePrcDealAmt) && hasValue(sumBasePrcDealAmtOfAcc)) {
                basePrcDealAmt = basePrcDealAmt.add(sumBasePrcDealAmtOfAcc);
            } else if (!hasValue(basePrcDealAmt) && hasValue(sumBasePrcDealAmtOfAcc)) {
                basePrcDealAmt = sumBasePrcDealAmtOfAcc;
            }
            // END 2017/04/24 K.Kitachi [QC#17554, ADD]
            // START 2018/05/21 K.Kitachi [QC#26217, ADD]
            BigDecimal sumAddlChrgFlatDealPrcAmt = getSumAddlChrgFlatDealPrcAmt(mainClass, param.glblCmpyCd.getValue(), dsContrDtlTMsg.dsContrDtlPk.getValue(), procDt);
            if (hasValue(basePrcDealAmt) && hasValue(sumAddlChrgFlatDealPrcAmt)) {
                basePrcDealAmt = basePrcDealAmt.add(sumAddlChrgFlatDealPrcAmt);
            } else if (!hasValue(basePrcDealAmt) && hasValue(sumAddlChrgFlatDealPrcAmt)) {
                basePrcDealAmt = sumAddlChrgFlatDealPrcAmt;
            }
            // END 2018/05/21 K.Kitachi [QC#26217, ADD]
            if (!hasValue(basePrcDealAmt) || basePrcDealAmt.compareTo(BigDecimal.ZERO) == 0) {
                continue;
            }
            BigDecimal sumBasePerCycleDealAmt = getSumBasePerCycleDealAmtForCheckInsource(mainClass, param.glblCmpyCd.getValue(), serNum);
            // Add Start 2017/07/31 QC#18764
            aggTotalBasePrcDealAmt = aggTotalBasePrcDealAmt.add(basePrcDealAmt);
            if (hasValue(sumBasePerCycleDealAmt)) {
                aggTotalBasePerCycleDealAmt = aggTotalBasePerCycleDealAmt.add(sumBasePerCycleDealAmt);
            }
            if (DS_CONTR_CATG.AGGREGATE.equals(dsContrTMsg.dsContrCatgCd.getValue())) {
                if (!dsContrDtlMap.containsKey(dsContrDtlTMsg.dsContrDtlPk.getValue())) {
                    dsContrDtlMap.put(dsContrDtlTMsg.dsContrDtlPk.getValue(), serNum);
                }
                continue;
            }
            // Add End 2017/07/31 QC#18764

            BigDecimal cfsBasePriceErrorAllowance = ZYPCodeDataUtil.getNumConstValue(CFS_BASE_PRICE_ERROR_ALLOWANCE, param.glblCmpyCd.getValue());

            rtrnTMsg = new DS_CONTR_VLD_RSLT_WRKTMsg();
            setValue(rtrnTMsg.dsContrPk, dsContrTMsg.dsContrPk);
            setValue(rtrnTMsg.dsContrDtlPk, dsContrDtlTMsg.dsContrDtlPk);
            setValue(rtrnTMsg.dsContrVldStsCd, DS_CONTR_VLD_STS.SUCCESS);
            // START 2016/06/30 K.Yamada [QC#3051, MOD]
            boolean errFlg = false;
            // START 2018/02/14 K.Kojima [QC#24145,ADD]
            if (!existCfsLeaseContrForBeforeCheck(mainClass, dsContrTMsg.glblCmpyCd.getValue(), serNum)) {
                setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(NSZM1321E), DS_CONTR_DTL_PK, dsContrDtlTMsg.dsContrDtlPk.getValue());
            } else
            // END 2018/02/14 K.Kojima [QC#24145,ADD]
            if (!hasValue(sumBasePerCycleDealAmt) || !hasValue(cfsBasePriceErrorAllowance)) {
                errFlg = true;
            // Mod Start 2017/07/31 QC#20362
            } else if (BigDecimal.ZERO.compareTo(sumBasePerCycleDealAmt) < 0) {
            // Mod End 2017/07/31 QC#20362
                // START 2017/05/12 K.Kitachi [QC#18535, MOD]
//                BigDecimal errPercent = basePrcDealAmt.subtract(sumBasePerCycleDealAmt).divide(basePrcDealAmt).abs();
                BigDecimal errPercent = basePrcDealAmt.subtract(sumBasePerCycleDealAmt).divide(basePrcDealAmt, cfsBasePriceErrorAllowance.scale(), BigDecimal.ROUND_HALF_DOWN).abs();
                // END 2017/05/12 K.Kitachi [QC#18535, MOD]
                if (errPercent.compareTo(cfsBasePriceErrorAllowance) > 0) {
                    errFlg = true;
                }
            }
            if (errFlg) {
                setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(NSZM0985E, new String[] {serNum }), DS_CONTR_PRC_EFF_PK, dsContrPrcEffPk);
            }
            // END 2016/06/30 K.Yamada [QC#3051, MOD]
            EZDMsg.copy(rtrnTMsg, null, rtrnTMsgArray.no(arrayIdx++), null);
            rtrnTMsgArray.setValidCount(rtrnTMsgArray.getValidCount() + 1);
        }
        // Add Start 2017/07/31 QC#18764
        if (!DS_CONTR_CATG.AGGREGATE.equals(dsContrTMsg.dsContrCatgCd.getValue()) || dsContrDtlMap.isEmpty()) {
            return rtrnTMsgArray;
        }

        if (!hasValue(aggTotalBasePrcDealAmt) || aggTotalBasePrcDealAmt.compareTo(BigDecimal.ZERO) == 0) {
            return rtrnTMsgArray;
        }

        BigDecimal cfsBasePriceErrorAllowance = ZYPCodeDataUtil.getNumConstValue(CFS_BASE_PRICE_ERROR_ALLOWANCE, param.glblCmpyCd.getValue());
        boolean aggLineErrFlg = false;
        if (hasValue(aggTotalBasePerCycleDealAmt) && BigDecimal.ZERO.compareTo(aggTotalBasePerCycleDealAmt) < 0) {
            BigDecimal errPercent = aggTotalBasePrcDealAmt.subtract(aggTotalBasePerCycleDealAmt).divide(aggTotalBasePrcDealAmt, cfsBasePriceErrorAllowance.scale(), BigDecimal.ROUND_HALF_DOWN).abs();
            if (errPercent.compareTo(cfsBasePriceErrorAllowance) > 0) {
                aggLineErrFlg= true;
            }
        }

        for (Map.Entry<BigDecimal, String> dsContrDtl : dsContrDtlMap.entrySet()) {
            rtrnTMsg = new DS_CONTR_VLD_RSLT_WRKTMsg();
            setValue(rtrnTMsg.dsContrPk, dsContrTMsg.dsContrPk);
            setValue(rtrnTMsg.dsContrDtlPk, dsContrDtl.getKey());
            setValue(rtrnTMsg.dsContrVldStsCd, DS_CONTR_VLD_STS.SUCCESS);
            if (aggLineErrFlg) {
                setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(NSZM0985E, new String[] {dsContrDtl.getValue() }), DS_CONTR_DTL_PK, dsContrDtl.getKey());
            }
            EZDMsg.copy(rtrnTMsg, null, rtrnTMsgArray.no(rtrnTMsgArray.getValidCount()), null);
            rtrnTMsgArray.setValidCount(rtrnTMsgArray.getValidCount() + 1);
        }
        // Add End 2017/07/31 QC#18764
        return rtrnTMsgArray;
    }

    /**
     * checkCfsDealerCodeDiff
     * @param mainClass NSZC057001
     * @param param NSZC057001PMsg
     * @return DS_CONTR_VLD_RSLT_WRKTMsgArray
     */
    public static DS_CONTR_VLD_RSLT_WRKTMsgArray checkCfsDealerCodeDiff(NSZC057001 mainClass, NSZC057001PMsg param) {

        DS_CONTR_VLD_RSLT_WRKTMsgArray rtrnTMsgArray = new DS_CONTR_VLD_RSLT_WRKTMsgArray();
        rtrnTMsgArray.setMsgList(new DS_CONTR_VLD_RSLT_WRKTMsg[MAX_SIZE]);
        int arrayIdx = 0;

        DS_CONTR_VLD_RSLT_WRKTMsg rtrnTMsg;

        DS_CONTRTMsg dsContrTMsg = mainClass.dsContrCache.get(KEY);
        // START 2017/06/22 K.Kitachi [QC#19340, MOD]
        // START 2018/02/02 U.Kim [QC#23687,MOD]
        // if (!isLeaseCompany(dsContrTMsg)) {
        if (!isLeaseCompany(mainClass, dsContrTMsg)) {
        // END 2018/02/02 U.Kim [QC#23687,MOD]
        // END 2017/06/22 K.Kitachi [QC#19340, MOD]
            return rtrnTMsgArray;
        }

        DS_CONTR_DTLTMsgArray dsContrDtlTMsgArray = mainClass.dsContrDtlArrayCache.get(KEY);

        // START 2017/06/22 K.Kitachi [QC#19340, MOD]
        String csaDealerCode = getLeaseDealerCode(param.glblCmpyCd.getValue(), dsContrTMsg.leaseCmpyCd.getValue());
        // END 2017/06/22 K.Kitachi [QC#19340, MOD]

        for (int i = 0; i < dsContrDtlTMsgArray.getValidCount(); i++) {
            DS_CONTR_DTLTMsg dsContrDtlTMsg = dsContrDtlTMsgArray.no(i);

            // get machine & contract info
            Map<String, Object> machAndContrInfo = getMachAndContrInfo(mainClass, param.glblCmpyCd.getValue(), dsContrDtlTMsg.dsContrDtlPk.getValue());
            if (machAndContrInfo == null) {
                continue;
            }
            // START 2017/09/13 U.Kim [QC#20896, ADD]
            String mainUnitLineFlg = (String) machAndContrInfo.get("MAIN_UNIT_LINE_FLG");
            if (mainUnitLineFlg.equals(ZYPConstant.FLG_OFF_N)) {
                continue;
            }
            // END 2017/09/13 U.Kim [QC#20896, ADD]
            String serNum = (String) machAndContrInfo.get("SER_NUM");
            // mod start 2017/02/14 CSA QC#17554
            if (!hasValue(serNum)) {
                continue;
            }
            // mod end 2017/02/14 CSA QC#17554

            // START 2018/05/22 K.Kim [QC#26201, ADD]
            String procDt = getProcDt(param.slsDt.getValue(), dsContrDtlTMsg);
            Map<String, Object> dsContrPrcEff = getDsContrPrcEff(mainClass, param.glblCmpyCd.getValue(), dsContrDtlTMsg.dsContrDtlPk.getValue(), procDt);
            if (dsContrPrcEff == null) {
                continue;
            }
            BigDecimal basePrcDealAmt = (BigDecimal) dsContrPrcEff.get("BASE_PRC_DEAL_AMT");
            String baseChrgFlg = (String) machAndContrInfo.get("BASE_CHRG_FLG");
            String usgChrgFlg = (String) machAndContrInfo.get("USG_CHRG_FLG");
            if (baseChrgFlg.equals(ZYPConstant.FLG_ON_Y) && usgChrgFlg.equals(ZYPConstant.FLG_OFF_N) && basePrcDealAmt.compareTo(BigDecimal.ZERO) == 0) {
                continue;
            }
            // END 2018/05/22 K.Kim [QC#26201, ADD]

            rtrnTMsg = new DS_CONTR_VLD_RSLT_WRKTMsg();
            setValue(rtrnTMsg.dsContrPk, dsContrTMsg.dsContrPk);
            setValue(rtrnTMsg.dsContrDtlPk, dsContrDtlTMsg.dsContrDtlPk);
            setValue(rtrnTMsg.dsContrVldStsCd, DS_CONTR_VLD_STS.SUCCESS);
            // START 2018/02/14 K.Kojima [QC#24145,ADD]
            if (!existCfsLeaseContrForBeforeCheck(mainClass, dsContrTMsg.glblCmpyCd.getValue(), serNum)) {
                setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(NSZM1321E), DS_CONTR_DTL_PK, dsContrDtlTMsg.dsContrDtlPk.getValue());
            } else
            // END 2018/02/14 K.Kojima [QC#24145,ADD]
            // START 2016/06/30 K.Yamada [QC#3051, MOD]
            if (!existCfsLeaseContr(mainClass, param.glblCmpyCd.getValue(), serNum, csaDealerCode, null, null, null, null) || existCfsLeaseContrForCheckCfsDealerCodeDiff(mainClass, param.glblCmpyCd.getValue(), serNum, csaDealerCode)) {
            // END 2016/06/30 K.Yamada [QC#3051, MOD]
                setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(NSZM0986E, new String[] {serNum }), DS_CONTR_DTL_PK, dsContrDtlTMsg.dsContrDtlPk.getValue());
            }
            EZDMsg.copy(rtrnTMsg, null, rtrnTMsgArray.no(arrayIdx++), null);
            rtrnTMsgArray.setValidCount(rtrnTMsgArray.getValidCount() + 1);
        }

        return rtrnTMsgArray;
    }

    /**
     * checkCfsNonFltMachineDiff
     * @param mainClass NSZC057001
     * @param param NSZC057001PMsg
     * @return DS_CONTR_VLD_RSLT_WRKTMsgArray
     */
    public static DS_CONTR_VLD_RSLT_WRKTMsgArray checkCfsNonFltMachineDiff(NSZC057001 mainClass, NSZC057001PMsg param) {

        DS_CONTR_VLD_RSLT_WRKTMsgArray rtrnTMsgArray = new DS_CONTR_VLD_RSLT_WRKTMsgArray();
        rtrnTMsgArray.setMsgList(new DS_CONTR_VLD_RSLT_WRKTMsg[MAX_SIZE]);
        int arrayIdx = 0;

        DS_CONTR_VLD_RSLT_WRKTMsg rtrnTMsg;

        DS_CONTRTMsg dsContrTMsg = mainClass.dsContrCache.get(KEY);
        // START 2017/06/22 K.Kitachi [QC#19340, MOD]
        // START 2018/02/02 U.Kim [QC#23687,MOD]
        // if (!isLeaseCompany(dsContrTMsg)) {
        if (!isLeaseCompany(mainClass, dsContrTMsg)) {
        // END 2018/02/02 U.Kim [QC#23687,MOD]
        // END 2017/06/22 K.Kitachi [QC#19340, MOD]
            return rtrnTMsgArray;
        }

        DS_CONTR_DTLTMsgArray dsContrDtlTMsgArray = mainClass.dsContrDtlArrayCache.get(KEY);

        for (int i = 0; i < dsContrDtlTMsgArray.getValidCount(); i++) {
            DS_CONTR_DTLTMsg dsContrDtlTMsg = dsContrDtlTMsgArray.no(i);

            // get machine & contract info
            Map<String, Object> machAndContrInfo = getMachAndContrInfo(mainClass, param.glblCmpyCd.getValue(), dsContrDtlTMsg.dsContrDtlPk.getValue());
            if (machAndContrInfo == null) {
                continue;
            }
            // START 2017/09/13 U.Kim [QC#20896, ADD]
            String mainUnitLineFlg = (String) machAndContrInfo.get("MAIN_UNIT_LINE_FLG");
            if (mainUnitLineFlg.equals(ZYPConstant.FLG_OFF_N)) {
                continue;
            }
            // END 2017/09/13 U.Kim [QC#20896, ADD]
            String serNum = (String) machAndContrInfo.get("SER_NUM");
            // mod start 2017/02/14 CSA QC#17554
            if (!hasValue(serNum)) {
                continue;
            }
            // mod end 2017/02/14 CSA QC#17554

            // START 2018/05/22 K.Kim [QC#26201, ADD]
            String procDt = getProcDt(param.slsDt.getValue(), dsContrDtlTMsg);
            Map<String, Object> dsContrPrcEff = getDsContrPrcEff(mainClass, param.glblCmpyCd.getValue(), dsContrDtlTMsg.dsContrDtlPk.getValue(), procDt);
            if (dsContrPrcEff == null) {
                continue;
            }
            BigDecimal basePrcDealAmt = (BigDecimal) dsContrPrcEff.get("BASE_PRC_DEAL_AMT");
            String baseChrgFlg = (String) machAndContrInfo.get("BASE_CHRG_FLG");
            String usgChrgFlg = (String) machAndContrInfo.get("USG_CHRG_FLG");
            if (baseChrgFlg.equals(ZYPConstant.FLG_ON_Y) && usgChrgFlg.equals(ZYPConstant.FLG_OFF_N) && basePrcDealAmt.compareTo(BigDecimal.ZERO) == 0) {
                continue;
            }
            // END 2018/05/22 K.Kim [QC#26201, ADD]

            rtrnTMsg = new DS_CONTR_VLD_RSLT_WRKTMsg();
            setValue(rtrnTMsg.dsContrPk, dsContrTMsg.dsContrPk);
            setValue(rtrnTMsg.dsContrDtlPk, dsContrDtlTMsg.dsContrDtlPk);
            setValue(rtrnTMsg.dsContrVldStsCd, DS_CONTR_VLD_STS.SUCCESS);
            // START 2018/02/14 K.Kojima [QC#24145,ADD]
            if (!existCfsLeaseContrForBeforeCheck(mainClass, dsContrTMsg.glblCmpyCd.getValue(), serNum)) {
                setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(NSZM1321E), DS_CONTR_DTL_PK, dsContrDtlTMsg.dsContrDtlPk.getValue());
            } else
            // END 2018/02/14 K.Kojima [QC#24145,ADD]
            if (!existCfsLeaseContrForCheckCfsNonFltMachineDiff(mainClass, param.glblCmpyCd.getValue(), serNum)) {
                setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(NSZM0987E, new String[] {serNum }), DS_CONTR_DTL_PK, dsContrDtlTMsg.dsContrDtlPk.getValue());
            }
            EZDMsg.copy(rtrnTMsg, null, rtrnTMsgArray.no(arrayIdx++), null);
            rtrnTMsgArray.setValidCount(rtrnTMsgArray.getValidCount() + 1);
        }

        return rtrnTMsgArray;
    }

    /**
     * checkCfsNonFltBaseCycleDiff
     * @param mainClass NSZC057001
     * @param param NSZC057001PMsg
     * @return DS_CONTR_VLD_RSLT_WRKTMsgArray
     */
    public static DS_CONTR_VLD_RSLT_WRKTMsgArray checkCfsNonFltBaseCycleDiff(NSZC057001 mainClass, NSZC057001PMsg param) {

        DS_CONTR_VLD_RSLT_WRKTMsgArray rtrnTMsgArray = new DS_CONTR_VLD_RSLT_WRKTMsgArray();
        rtrnTMsgArray.setMsgList(new DS_CONTR_VLD_RSLT_WRKTMsg[MAX_SIZE]);
        int arrayIdx = 0;

        DS_CONTR_VLD_RSLT_WRKTMsg rtrnTMsg;

        DS_CONTRTMsg dsContrTMsg = mainClass.dsContrCache.get(KEY);
        // START 2017/06/22 K.Kitachi [QC#19340, MOD]
        // START 2018/02/02 U.Kim [QC#23687,MOD]
        // if (!isLeaseCompany(dsContrTMsg)) {
        if (!isLeaseCompany(mainClass, dsContrTMsg)) {
        // END 2018/02/02 U.Kim [QC#23687,MOD]
        // END 2017/06/22 K.Kitachi [QC#19340, MOD]
            return rtrnTMsgArray;
        }

        DS_CONTR_DTLTMsgArray dsContrDtlTMsgArray = mainClass.dsContrDtlArrayCache.get(KEY);

        for (int i = 0; i < dsContrDtlTMsgArray.getValidCount(); i++) {
            DS_CONTR_DTLTMsg dsContrDtlTMsg = dsContrDtlTMsgArray.no(i);

            // get machine & contract info
            Map<String, Object> machAndContrInfo = getMachAndContrInfo(mainClass, param.glblCmpyCd.getValue(), dsContrDtlTMsg.dsContrDtlPk.getValue());
            if (machAndContrInfo == null) {
                continue;
            }
            // START 2017/04/24 K.Kitachi [QC#17554, ADD]
            String mainUnitLineFlg = (String) machAndContrInfo.get("MAIN_UNIT_LINE_FLG");
            if (mainUnitLineFlg.equals(ZYPConstant.FLG_OFF_N)) {
                continue;
            }
            // END 2017/04/24 K.Kitachi [QC#17554, ADD]
            String serNum = (String) machAndContrInfo.get("SER_NUM");
            // mod start 2017/02/14 CSA QC#17554
            if (!hasValue(serNum)) {
                continue;
            }
            // mod end 2017/02/14 CSA QC#17554
            String baseChrgFlg = (String) machAndContrInfo.get("BASE_CHRG_FLG");
            if (baseChrgFlg.equals(ZYPConstant.FLG_OFF_N)) {
                continue;
            }
            String cfsBllgCycleCd = (String) machAndContrInfo.get("CFS_BASE_BLLG_CYCLE_CD");
            if (!hasValue(cfsBllgCycleCd)) {
                continue;
            }
            // Add Start 2017/07/27 QC#20226
            String procDt = getProcDt(param.slsDt.getValue(), dsContrDtlTMsg);
            // Add End 2017/07/27 QC#20226

            // add start 2017/04/17 CSA Defect#18155
            // Mod Start 2017/07/27 QC#20226
            Map<String, Object> dsContrPrcEff = getDsContrPrcEff(mainClass, param.glblCmpyCd.getValue(), dsContrDtlTMsg.dsContrDtlPk.getValue(), procDt);
            // Mod End 2017/07/27 QC#20226
            if (dsContrPrcEff == null) {
                continue;
            }
            BigDecimal basePrcDealAmt = (BigDecimal) dsContrPrcEff.get("BASE_PRC_DEAL_AMT");
            // START 2017/04/24 K.Kitachi [QC#17554, ADD]
            // Mod Start 2017/07/27 QC#20226
            BigDecimal sumBasePrcDealAmtOfAcc = getSumBasePrcDealAmtOfAcc(mainClass, param.glblCmpyCd.getValue(), dsContrDtlTMsg.dsContrDtlPk.getValue(), procDt);
            // Mod End 2017/07/27 QC#20226
            if (hasValue(basePrcDealAmt) && hasValue(sumBasePrcDealAmtOfAcc)) {
                basePrcDealAmt = basePrcDealAmt.add(sumBasePrcDealAmtOfAcc);
            } else if (!hasValue(basePrcDealAmt) && hasValue(sumBasePrcDealAmtOfAcc)) {
                basePrcDealAmt = sumBasePrcDealAmtOfAcc;
            }
            // END 2017/04/24 K.Kitachi [QC#17554, ADD]
            if (!hasValue(basePrcDealAmt)) {
                continue;
            }
            // add end 2017/04/17 CSA Defect#18155

            // START 2018/05/22 K.Kim [QC#26201, ADD]
            String usgChrgFlg = (String) machAndContrInfo.get("USG_CHRG_FLG");
            if (baseChrgFlg.equals(ZYPConstant.FLG_ON_Y) && usgChrgFlg.equals(ZYPConstant.FLG_OFF_N) && basePrcDealAmt.compareTo(BigDecimal.ZERO) == 0) {
                continue;
            }
            // END 2018/05/22 K.Kim [QC#26201, ADD]

            rtrnTMsg = new DS_CONTR_VLD_RSLT_WRKTMsg();
            setValue(rtrnTMsg.dsContrPk, dsContrTMsg.dsContrPk);
            setValue(rtrnTMsg.dsContrDtlPk, dsContrDtlTMsg.dsContrDtlPk);
            setValue(rtrnTMsg.dsContrVldStsCd, DS_CONTR_VLD_STS.SUCCESS);
            // START 2018/02/14 K.Kojima [QC#24145,ADD]
            if (!existCfsLeaseContrForBeforeCheck(mainClass, dsContrTMsg.glblCmpyCd.getValue(), serNum)) {
                setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(NSZM1321E), DS_CONTR_DTL_PK, dsContrDtlTMsg.dsContrDtlPk.getValue());
            } else
            // END 2018/02/14 K.Kojima [QC#24145,ADD]
            // mod start 2017/04/17 CSA Defect#18155
            if (BigDecimal.ZERO.compareTo(basePrcDealAmt) != 0) {
                // START 2016/06/30 K.Yamada [QC#3051, MOD]
                // Mod Start 2017/07/31 QC#20362
                if (existCfsLeaseContr(mainClass, param.glblCmpyCd.getValue(), serNum, null, ZYPConstant.FLG_ON_Y, null, null, null) && existCfsLeaseContrForCheckCfsNonFltBaseCycleDiff(mainClass, param.glblCmpyCd.getValue(), serNum, cfsBllgCycleCd)) {
                // Mod End 2017/07/31 QC#20362
                // END 2016/06/30 K.Yamada [QC#3051, MOD]
                    setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(NSZM0988E, new String[] {serNum }), DS_CONTR_DTL_PK, dsContrDtlTMsg.dsContrDtlPk.getValue());
                }
            }
            // mod end 2017/04/17 CSA Defect#18155
            EZDMsg.copy(rtrnTMsg, null, rtrnTMsgArray.no(arrayIdx++), null);
            rtrnTMsgArray.setValidCount(rtrnTMsgArray.getValidCount() + 1);
        }

        return rtrnTMsgArray;
    }

    /**
     * checkCfsNonFltUsageCycleDiff
     * @param mainClass NSZC057001
     * @param param NSZC057001PMsg
     * @return DS_CONTR_VLD_RSLT_WRKTMsgArray
     */
    public static DS_CONTR_VLD_RSLT_WRKTMsgArray checkCfsNonFltUsageCycleDiff(NSZC057001 mainClass, NSZC057001PMsg param) {

        DS_CONTR_VLD_RSLT_WRKTMsgArray rtrnTMsgArray = new DS_CONTR_VLD_RSLT_WRKTMsgArray();
        rtrnTMsgArray.setMsgList(new DS_CONTR_VLD_RSLT_WRKTMsg[MAX_SIZE]);
        int arrayIdx = 0;

        DS_CONTR_VLD_RSLT_WRKTMsg rtrnTMsg;

        DS_CONTRTMsg dsContrTMsg = mainClass.dsContrCache.get(KEY);
        // START 2017/06/22 K.Kitachi [QC#19340, MOD]
        // START 2018/02/02 U.Kim [QC#23687,MOD]
        // if (!isLeaseCompany(dsContrTMsg)) {
        if (!isLeaseCompany(mainClass, dsContrTMsg)) {
        // END 2018/02/02 U.Kim [QC#23687,MOD]
        // END 2017/06/22 K.Kitachi [QC#19340, MOD]
            return rtrnTMsgArray;
        }

        DS_CONTR_DTLTMsgArray dsContrDtlTMsgArray = mainClass.dsContrDtlArrayCache.get(KEY);

        for (int i = 0; i < dsContrDtlTMsgArray.getValidCount(); i++) {
            DS_CONTR_DTLTMsg dsContrDtlTMsg = dsContrDtlTMsgArray.no(i);

            // get machine & contract info
            Map<String, Object> machAndContrInfo = getMachAndContrInfo(mainClass, param.glblCmpyCd.getValue(), dsContrDtlTMsg.dsContrDtlPk.getValue());
            if (machAndContrInfo == null) {
                continue;
            }
            // START 2017/09/13 U.Kim [QC#20896, ADD]
            String mainUnitLineFlg = (String) machAndContrInfo.get("MAIN_UNIT_LINE_FLG");
            if (mainUnitLineFlg.equals(ZYPConstant.FLG_OFF_N)) {
                continue;
            }
            // END 2017/09/13 U.Kim [QC#20896, ADD]
            String serNum = (String) machAndContrInfo.get("SER_NUM");
            // mod start 2017/02/14 CSA QC#17554
            if (!hasValue(serNum)) {
                continue;
            }
            // mod end 2017/02/14 CSA QC#17554
            String usgChrgFlg = (String) machAndContrInfo.get("USG_CHRG_FLG");
            if (usgChrgFlg.equals(ZYPConstant.FLG_OFF_N)) {
                continue;
            }

            rtrnTMsg = new DS_CONTR_VLD_RSLT_WRKTMsg();
            setValue(rtrnTMsg.dsContrPk, dsContrTMsg.dsContrPk);
            setValue(rtrnTMsg.dsContrDtlPk, dsContrDtlTMsg.dsContrDtlPk);
            // START 2017/08/08 K.Kitachi [QC#20531, MOD]
            setValue(rtrnTMsg.dsContrVldStsCd, DS_CONTR_VLD_STS.SUCCESS);
            // END 2017/08/08 K.Kitachi [QC#20531, MOD]

            DS_CONTR_BLLG_MTRTMsgArray dsContrBllgMtrTMsgArray = mainClass.dsContrBllgMtrArrayCache.get(KEY + i);

            if (dsContrBllgMtrTMsgArray == null || dsContrBllgMtrTMsgArray.getValidCount() == 0) {
                dsContrBllgMtrTMsgArray = getDsContrBllgMtr(param.glblCmpyCd.getValue(), dsContrDtlTMsg.dsContrDtlPk.getValue());
                if (dsContrBllgMtrTMsgArray != null && dsContrBllgMtrTMsgArray.getValidCount() > 0) {
                    mainClass.dsContrBllgMtrArrayCache.put(KEY + i, dsContrBllgMtrTMsgArray);
                }
            }

            if (dsContrBllgMtrTMsgArray == null || dsContrBllgMtrTMsgArray.getValidCount() == 0) {
                continue;
            }

            // START 2017/08/08 K.Kitachi [QC#20531, ADD]
            boolean isChecked = false;
            // END 2017/08/08 K.Kitachi [QC#20531, ADD]
            for (int j = 0; j < dsContrBllgMtrTMsgArray.getValidCount(); j++) {
                DS_CONTR_BLLG_MTRTMsg dsContrBllgMtrTMsg = dsContrBllgMtrTMsgArray.no(j);
                // START 2017/08/03 K.Kitachi [QC#20224, ADD]
                // START 2018/02/02 U.Kim [QC#23687,MOD]
                // if (!NSXC001001ContrValidation.isLeaseCompany(param.glblCmpyCd.getValue(), dsContrBllgMtrTMsg.bllgMtrBillToCustCd.getValue())) {
                if (!isLeaseCompanyForBllgMtr(mainClass, param.glblCmpyCd.getValue(), dsContrBllgMtrTMsg.bllgMtrBillToCustCd.getValue())) {
                // END 2018/02/02 U.Kim [QC#23687,MOD]
                    continue;
                }
                // END 2017/08/03 K.Kitachi [QC#20224, ADD]
                // START 2017/08/08 K.Kitachi [QC#20531, ADD]
                isChecked = true;
                // END 2017/08/08 K.Kitachi [QC#20531, ADD]
                String bllgCycleCd = dsContrBllgMtrTMsg.bllgMtrBllgCycleCd.getValue();
                if (!hasValue(bllgCycleCd)) {
                    continue;
                }
                String cfsBllgCycleCd = convertBllgCycleCd(param.glblCmpyCd.getValue(), bllgCycleCd);
                if (!hasValue(cfsBllgCycleCd)) {
                    continue;
                }
                // START 2018/02/14 K.Kojima [QC#24145,ADD]
                if (!existCfsLeaseContrForBeforeCheck(mainClass, dsContrTMsg.glblCmpyCd.getValue(), serNum)) {
                    setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(NSZM1321E), DS_CONTR_DTL_PK, dsContrDtlTMsg.dsContrDtlPk.getValue());
                    break;
                } else
                // END 2018/02/14 K.Kojima [QC#24145,ADD]
                // START 2016/06/30 K.Yamada [QC#3051, MOD]
                if (!existCfsLeaseContr(mainClass, param.glblCmpyCd.getValue(), serNum, null, null, ZYPConstant.FLG_ON_Y, null, null) || existCfsLeaseContrForCheckCfsNonFltUsageCycleDiff(mainClass, param.glblCmpyCd.getValue(), serNum, cfsBllgCycleCd, dsContrBllgMtrTMsg.bllgMtrLbCd.getValue())) {
                // END 2016/06/30 K.Yamada [QC#3051, MOD]
                    setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(NSZM0989E, new String[] {serNum }), DS_CONTR_BLLG_MTR_PK, dsContrBllgMtrTMsg.dsContrBllgMtrPk.getValue());
                    break;
                }
            }

            // START 2017/08/08 K.Kitachi [QC#20531, MOD]
            if (isChecked) {
                EZDMsg.copy(rtrnTMsg, null, rtrnTMsgArray.no(arrayIdx++), null);
                rtrnTMsgArray.setValidCount(rtrnTMsgArray.getValidCount() + 1);
            }
            // END 2017/08/08 K.Kitachi [QC#20531, MOD]
        }

        return rtrnTMsgArray;
    }

    /**
     * checkCfsNonFltUsageTier1RateDiff
     * @param mainClass NSZC057001
     * @param param NSZC057001PMsg
     * @return DS_CONTR_VLD_RSLT_WRKTMsgArray
     */
    public static DS_CONTR_VLD_RSLT_WRKTMsgArray checkCfsNonFltUsageTier1RateDiff(NSZC057001 mainClass, NSZC057001PMsg param) {

        DS_CONTR_VLD_RSLT_WRKTMsgArray rtrnTMsgArray = new DS_CONTR_VLD_RSLT_WRKTMsgArray();
        rtrnTMsgArray.setMsgList(new DS_CONTR_VLD_RSLT_WRKTMsg[MAX_SIZE]);
        int arrayIdx = 0;

        DS_CONTR_VLD_RSLT_WRKTMsg rtrnTMsg = null;

        DS_CONTRTMsg dsContrTMsg = mainClass.dsContrCache.get(KEY);
        // START 2017/06/22 K.Kitachi [QC#19340, MOD]
        // START 2018/02/02 U.Kim [QC#23687,MOD]
        // if (!isLeaseCompany(dsContrTMsg)) {
        if (!isLeaseCompany(mainClass, dsContrTMsg)) {
        // END 2018/02/02 U.Kim [QC#23687,MOD]
        // END 2017/06/22 K.Kitachi [QC#19340, MOD]
            return rtrnTMsgArray;
        }

        DS_CONTR_DTLTMsgArray dsContrDtlTMsgArray = mainClass.dsContrDtlArrayCache.get(KEY);

        for (int i = 0; i < dsContrDtlTMsgArray.getValidCount(); i++) {
            DS_CONTR_DTLTMsg dsContrDtlTMsg = dsContrDtlTMsgArray.no(i);

            // get machine & contract info
            Map<String, Object> machAndContrInfo = getMachAndContrInfo(mainClass, param.glblCmpyCd.getValue(), dsContrDtlTMsg.dsContrDtlPk.getValue());
            if (machAndContrInfo == null) {
                continue;
            }
            // START 2017/09/13 U.Kim [QC#20896, ADD]
            String mainUnitLineFlg = (String) machAndContrInfo.get("MAIN_UNIT_LINE_FLG");
            if (mainUnitLineFlg.equals(ZYPConstant.FLG_OFF_N)) {
                continue;
            }
            // END 2017/09/13 U.Kim [QC#20896, ADD]
            String serNum = (String) machAndContrInfo.get("SER_NUM");
            // mod start 2017/02/14 CSA QC#17554
            if (!hasValue(serNum)) {
                continue;
            }
            // mod end 2017/02/14 CSA QC#17554
            String usgChrgFlg = (String) machAndContrInfo.get("USG_CHRG_FLG");
            if (ZYPConstant.FLG_OFF_N.equals(usgChrgFlg)) {
                continue;
            }
            // Add Start 2017/07/27 QC#20226
            String procDt = getProcDt(param.slsDt.getValue(), dsContrDtlTMsg);
            // Add End 2017/07/27 QC#20226

            rtrnTMsg = new DS_CONTR_VLD_RSLT_WRKTMsg();
            setValue(rtrnTMsg.dsContrPk, dsContrTMsg.dsContrPk);
            setValue(rtrnTMsg.dsContrDtlPk, dsContrDtlTMsg.dsContrDtlPk);
            // START 2017/08/08 K.Kitachi [QC#20531, MOD]
            setValue(rtrnTMsg.dsContrVldStsCd, DS_CONTR_VLD_STS.SUCCESS);
            // END 2017/08/08 K.Kitachi [QC#20531, MOD]
            // Mod Start 2017/07/27 QC#20226
            List<Map<String, Object>> usgChrgInfo = getUsgChrgInfo(mainClass, param.glblCmpyCd.getValue(), dsContrDtlTMsg.dsContrDtlPk.getValue(), procDt);
            // Mod End 2017/07/27 QC#20226
            // START 2017/08/08 K.Kitachi [QC#20531, ADD]
            boolean isChecked = false;
            // END 2017/08/08 K.Kitachi [QC#20531, ADD]
            for (int j = 0; j < usgChrgInfo.size(); j++) {
                // START 2017/08/03 K.Kitachi [QC#20224, ADD]
                // START 2018/02/02 U.Kim [QC#23687,MOD]
                // if (!NSXC001001ContrValidation.isLeaseCompany(param.glblCmpyCd.getValue(), (String) usgChrgInfo.get(j).get("BLLG_MTR_BILL_TO_CUST_CD"))) {
                if (!isLeaseCompanyForBllgMtr(mainClass, param.glblCmpyCd.getValue(), (String) usgChrgInfo.get(j).get("BLLG_MTR_BILL_TO_CUST_CD"))) {
                // END 2018/02/02 U.Kim [QC#23687,MOD]
                    continue;
                }
                // END 2017/08/03 K.Kitachi [QC#20224, ADD]
                // START 2017/08/08 K.Kitachi [QC#20531, ADD]
                isChecked = true;
                // END 2017/08/08 K.Kitachi [QC#20531, ADD]
                // START 2018/02/14 K.Kojima [QC#24145,ADD]
                if (!existCfsLeaseContrForBeforeCheck(mainClass, dsContrTMsg.glblCmpyCd.getValue(), serNum)) {
                    setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(NSZM1321E), DS_CONTR_DTL_PK, dsContrDtlTMsg.dsContrDtlPk.getValue());
                    break;
                }
                // END 2018/02/14 K.Kojima [QC#24145,ADD]
                // START 2016/06/30 K.Yamada [QC#3051, MOD]
                // Mod Start 2017/07/27 QC#20226
                BigDecimal dsContrPrcEffPk = getDsContrPrcEffPk(mainClass, param.glblCmpyCd.getValue(), dsContrDtlTMsg.dsContrDtlPk.getValue(), procDt);
                // Mod End 2017/07/27 QC#20226
                String bllgMtrLbCd = (String) usgChrgInfo.get(j).get("BLLG_MTR_LB_CD");
                BigDecimal xsMtrAmtRate = (BigDecimal) usgChrgInfo.get(j).get("XS_MTR_AMT_RATE");
                String usgTpBillCd = convertMtrLbCd(param.glblCmpyCd.getValue(), bllgMtrLbCd);
                if (!hasValue(usgTpBillCd)) {
                    continue;
                }
                BigDecimal tierCfs = getTierCount(mainClass, param.glblCmpyCd.getValue(), serNum, null, null, ZYPConstant.FLG_ON_Y, usgTpBillCd, null);
                // mod start 2017/04/19 CSA Defect#18383
                if (!hasValue(tierCfs)) {
                    continue;
                }
                if (tierCfs.compareTo(BigDecimal.ZERO) == 0 || existCfsLeaseContrForCheckCfsNonFltUsageTierRateDiff(mainClass, param.glblCmpyCd.getValue(), serNum, usgTpBillCd, xsMtrAmtRate, null, null, null)) {
                    setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(NSZM0990E, new String[] {serNum }), DS_CONTR_PRC_EFF_PK, dsContrPrcEffPk);
                    break;
                }
                // mod end 2017/04/19 CSA Defect#18383
                // END 2016/06/30 K.Yamada [QC#3051, MOD]
            }
            // START 2017/08/08 K.Kitachi [QC#20531, MOD]
            if (isChecked) {
                EZDMsg.copy(rtrnTMsg, null, rtrnTMsgArray.no(arrayIdx++), null);
                rtrnTMsgArray.setValidCount(rtrnTMsgArray.getValidCount() + 1);
            }
            // END 2017/08/08 K.Kitachi [QC#20531, MOD]
        }

        return rtrnTMsgArray;
    }

    /**
     * checkCfsNonFltUsageTier2RateDiff
     * @param mainClass NSZC057001
     * @param param NSZC057001PMsg
     * @return DS_CONTR_VLD_RSLT_WRKTMsgArray
     */
    public static DS_CONTR_VLD_RSLT_WRKTMsgArray checkCfsNonFltUsageTier2RateDiff(NSZC057001 mainClass, NSZC057001PMsg param) {
        return checkCfsNonFltUsageTierRateDiff(mainClass, param, NSZM0991E, TIER_2);
    }

    /**
     * checkCfsNonFltUsageTier3RateDiff
     * @param mainClass NSZC057001
     * @param param NSZC057001PMsg
     * @return DS_CONTR_VLD_RSLT_WRKTMsgArray
     */
    public static DS_CONTR_VLD_RSLT_WRKTMsgArray checkCfsNonFltUsageTier3RateDiff(NSZC057001 mainClass, NSZC057001PMsg param) {
        return checkCfsNonFltUsageTierRateDiff(mainClass, param, NSZM0992E, TIER_3);
    }

    /**
     * checkCfsNonFltUsageTier4RateDiff
     * @param mainClass NSZC057001
     * @param param NSZC057001PMsg
     * @return DS_CONTR_VLD_RSLT_WRKTMsgArray
     */
    public static DS_CONTR_VLD_RSLT_WRKTMsgArray checkCfsNonFltUsageTier4RateDiff(NSZC057001 mainClass, NSZC057001PMsg param) {
        return checkCfsNonFltUsageTierRateDiff(mainClass, param, NSZM0993E, TIER_4);
    }

    /**
     * checkCfsNonFltUsageTier1AllowanceDiff
     * @param mainClass NSZC057001
     * @param param NSZC057001PMsg
     * @return DS_CONTR_VLD_RSLT_WRKTMsgArray
     */
    public static DS_CONTR_VLD_RSLT_WRKTMsgArray checkCfsNonFltUsageTier1AllowanceDiff(NSZC057001 mainClass, NSZC057001PMsg param) {

        DS_CONTR_VLD_RSLT_WRKTMsgArray rtrnTMsgArray = new DS_CONTR_VLD_RSLT_WRKTMsgArray();
        rtrnTMsgArray.setMsgList(new DS_CONTR_VLD_RSLT_WRKTMsg[MAX_SIZE]);
        int arrayIdx = 0;

        DS_CONTR_VLD_RSLT_WRKTMsg rtrnTMsg = null;

        DS_CONTRTMsg dsContrTMsg = mainClass.dsContrCache.get(KEY);
        // START 2017/06/22 K.Kitachi [QC#19340, MOD]
        // START 2018/02/02 U.Kim [QC#23687,MOD]
        // if (!isLeaseCompany(dsContrTMsg)) {
        if (!isLeaseCompany(mainClass, dsContrTMsg)) {
        // END 2018/02/02 U.Kim [QC#23687,MOD]
        // END 2017/06/22 K.Kitachi [QC#19340, MOD]
            return rtrnTMsgArray;
        }

        DS_CONTR_DTLTMsgArray dsContrDtlTMsgArray = mainClass.dsContrDtlArrayCache.get(KEY);
        // Add Start 2017/07/31 QC#18764
        Map<String, BigDecimal> aggXsMtrCopyQtyMap = new HashMap<String, BigDecimal>();
        Map<BigDecimal, String> dsContrDtlMap = new HashMap<BigDecimal, String>();
        // Add End 2017/07/31 QC#18764

        for (int i = 0; i < dsContrDtlTMsgArray.getValidCount(); i++) {
            DS_CONTR_DTLTMsg dsContrDtlTMsg = dsContrDtlTMsgArray.no(i);

            // get machine & contract info
            Map<String, Object> machAndContrInfo = getMachAndContrInfo(mainClass, param.glblCmpyCd.getValue(), dsContrDtlTMsg.dsContrDtlPk.getValue());
            if (machAndContrInfo == null) {
                continue;
            }
            // START 2017/09/13 U.Kim [QC#20896, ADD]
            String mainUnitLineFlg = (String) machAndContrInfo.get("MAIN_UNIT_LINE_FLG");
            if (mainUnitLineFlg.equals(ZYPConstant.FLG_OFF_N)) {
                continue;
            }
            // END 2017/09/13 U.Kim [QC#20896, ADD]
            String serNum = (String) machAndContrInfo.get("SER_NUM");
            // mod start 2017/02/14 CSA QC#17554
            if (!hasValue(serNum)) {
                continue;
            }
            // mod end 2017/02/14 CSA QC#17554
            String usgChrgFlg = (String) machAndContrInfo.get("USG_CHRG_FLG");
            if (ZYPConstant.FLG_OFF_N.equals(usgChrgFlg)) {
                continue;
            }
            // Add Start 2017/07/27 QC#20226
            String procDt = getProcDt(param.slsDt.getValue(), dsContrDtlTMsg);
            // Add End 2017/07/27 QC#20226

            rtrnTMsg = new DS_CONTR_VLD_RSLT_WRKTMsg();
            setValue(rtrnTMsg.dsContrPk, dsContrTMsg.dsContrPk);
            setValue(rtrnTMsg.dsContrDtlPk, dsContrDtlTMsg.dsContrDtlPk);
            // START 2017/08/08 K.Kitachi [QC#20531, MOD]
            setValue(rtrnTMsg.dsContrVldStsCd, DS_CONTR_VLD_STS.SUCCESS);
            // END 2017/08/08 K.Kitachi [QC#20531, MOD]
            // Mod Start 2017/07/27 QC#20226
            List<Map<String, Object>> usgChrgInfo = getUsgChrgInfo(mainClass, param.glblCmpyCd.getValue(), dsContrDtlTMsg.dsContrDtlPk.getValue(), procDt);
            // Mod End 2017/07/27 QC#20226
            // Add Start 2017/07/31 QC#18764
            if (!dsContrDtlMap.containsKey(dsContrDtlTMsg.dsContrDtlPk.getValue())) {
                dsContrDtlMap.put(dsContrDtlTMsg.dsContrDtlPk.getValue(), serNum);
            }
            // Add End 2017/07/31 QC#18764
            // START 2017/08/08 K.Kitachi [QC#20531, ADD]
            boolean isChecked = false;
            // END 2017/08/08 K.Kitachi [QC#20531, ADD]
            for (int j = 0; j < usgChrgInfo.size(); j++) {
                // START 2017/08/03 K.Kitachi [QC#20224, ADD]
                // START 2018/02/02 U.Kim [QC#23687, ADD]
                // if (!NSXC001001ContrValidation.isLeaseCompany(param.glblCmpyCd.getValue(), (String) usgChrgInfo.get(j).get("BLLG_MTR_BILL_TO_CUST_CD"))) {
                if (!isLeaseCompanyForBllgMtr(mainClass, param.glblCmpyCd.getValue(), (String) usgChrgInfo.get(j).get("BLLG_MTR_BILL_TO_CUST_CD"))) {
                    continue;
                }
                // END 2018/02/02 U.Kim [QC#23687, ADD]
                // END 2017/08/03 K.Kitachi [QC#20224, ADD]
                // START 2017/08/08 K.Kitachi [QC#20531, ADD]
                isChecked = true;
                // END 2017/08/08 K.Kitachi [QC#20531, ADD]
                // START 2018/02/14 K.Kojima [QC#24145,ADD]
                if (!DS_CONTR_CATG.AGGREGATE.equals(dsContrTMsg.dsContrCatgCd.getValue()) && !existCfsLeaseContrForBeforeCheck(mainClass, dsContrTMsg.glblCmpyCd.getValue(), serNum)) {
                    setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(NSZM1321E), DS_CONTR_DTL_PK, dsContrDtlTMsg.dsContrDtlPk.getValue());
                    break;
                }
                // END 2018/02/14 K.Kojima [QC#24145,ADD]
                // START 2016/06/30 K.Yamada [QC#3051, MOD]
                // Mod Start 2017/07/27 QC#20226
                BigDecimal dsContrPrcEffPk = getDsContrPrcEffPk(mainClass, param.glblCmpyCd.getValue(), dsContrDtlTMsg.dsContrDtlPk.getValue(), procDt);
                // Mod End 2017/07/27 QC#20226
                String bllgMtrLbCd = (String) usgChrgInfo.get(j).get("BLLG_MTR_LB_CD");
                BigDecimal xsMtrCopyQty = (BigDecimal) usgChrgInfo.get(j).get("XS_MTR_COPY_QTY");
                String usgTpBillCd = convertMtrLbCd(param.glblCmpyCd.getValue(), bllgMtrLbCd);
                if (!hasValue(usgTpBillCd)) {
                    continue;
                }
                // Add Start 2017/07/31 QC#18764
                if (DS_CONTR_CATG.AGGREGATE.equals(dsContrTMsg.dsContrCatgCd.getValue())) {
                    if (aggXsMtrCopyQtyMap.containsKey(usgTpBillCd)) {
                        BigDecimal totalXsMtrCopyQty = aggXsMtrCopyQtyMap.get(usgTpBillCd).add(xsMtrCopyQty);
                        aggXsMtrCopyQtyMap.put(usgTpBillCd, totalXsMtrCopyQty);
                    } else {
                        aggXsMtrCopyQtyMap.put(usgTpBillCd, xsMtrCopyQty);
                    }
                    continue;
                }
                // Add End 2017/07/31 QC#18764

                BigDecimal tierCfs = getTierCount(mainClass, param.glblCmpyCd.getValue(), serNum, null, null, ZYPConstant.FLG_ON_Y, usgTpBillCd, null);
                // mod start 2017/04/19 CSA Defect#18383
                if (!hasValue(tierCfs)) {
                    continue;
                }
                if (tierCfs.compareTo(BigDecimal.ZERO) == 0 || existCfsLeaseContrForCheckCfsNonFltUsageTierAllowanceDiff(mainClass, param.glblCmpyCd.getValue(), serNum, usgTpBillCd, xsMtrCopyQty, null, null, null)) {
                    setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(NSZM0990E, new String[] {serNum }), DS_CONTR_PRC_EFF_PK, dsContrPrcEffPk);
                    break;
                }
                // mod end 2017/04/19 CSA Defect#18383
                // END 2016/06/30 K.Yamada [QC#3051, MOD]
            }
            // Add Start 2017/07/31 QC#18764
            if (DS_CONTR_CATG.AGGREGATE.equals(dsContrTMsg.dsContrCatgCd.getValue())) {
                continue;
            }
            // Add End 2017/07/31 QC#18764
            // START 2017/08/08 K.Kitachi [QC#20531, MOD]
            if (isChecked) {
                EZDMsg.copy(rtrnTMsg, null, rtrnTMsgArray.no(arrayIdx++), null);
                rtrnTMsgArray.setValidCount(rtrnTMsgArray.getValidCount() + 1);
            }
            // END 2017/08/08 K.Kitachi [QC#20531, MOD]
        }
        // Add Start 2017/07/31 QC#18764
        if (!DS_CONTR_CATG.AGGREGATE.equals(dsContrTMsg.dsContrCatgCd.getValue()) || dsContrDtlMap.isEmpty()) {
            return rtrnTMsgArray;
        }

        if (aggXsMtrCopyQtyMap.isEmpty()) {
            // START 2017/08/08 K.Kitachi [QC#20531, DEL]
//            EZDMsg.copy(rtrnTMsg, null, rtrnTMsgArray.no(rtrnTMsgArray.getValidCount()), null);
//            rtrnTMsgArray.setValidCount(rtrnTMsgArray.getValidCount() + 1);
            // END 2017/08/08 K.Kitachi [QC#20531, DEL]
            return rtrnTMsgArray;
        }

        BigDecimal dsContrDtlPk;
        String serNum;
        for (Map.Entry<BigDecimal, String> dsContrDtl : dsContrDtlMap.entrySet()) {
            dsContrDtlPk = dsContrDtl.getKey();
            serNum = dsContrDtl.getValue();

            rtrnTMsg = new DS_CONTR_VLD_RSLT_WRKTMsg();
            setValue(rtrnTMsg.dsContrPk, dsContrTMsg.dsContrPk);
            setValue(rtrnTMsg.dsContrDtlPk, dsContrDtl.getKey());
            setValue(rtrnTMsg.dsContrVldStsCd, DS_CONTR_VLD_STS.SUCCESS);
            String aggUsgTpBillCd = null;
            BigDecimal aggXsMtrCopyQty = BigDecimal.ZERO;
            Map<String, BigDecimal> aggXsMtrCopyQtymap;
            BigDecimal cfsXsMtrCopyQty = BigDecimal.ZERO;
            for (Map.Entry<String, BigDecimal> xsMtrCopy : aggXsMtrCopyQtyMap.entrySet()) {
                // START 2018/02/14 K.Kojima [QC#24145,ADD]
                if (!existCfsLeaseContrForBeforeCheck(mainClass, dsContrTMsg.glblCmpyCd.getValue(), serNum)) {
                    setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(NSZM1321E), DS_CONTR_DTL_PK, dsContrDtlPk);
                    break;
                }
                // END 2018/02/14 K.Kojima [QC#24145,ADD]

                aggUsgTpBillCd = xsMtrCopy.getKey();
                aggXsMtrCopyQty = xsMtrCopy.getValue();

                aggXsMtrCopyQtymap = getAggXsMtrCopyQty(mainClass, param.glblCmpyCd.getValue(), serNum, aggUsgTpBillCd);
                if (aggXsMtrCopyQtymap == null || aggXsMtrCopyQtymap.isEmpty()) {
                    continue;
                }
                // Mod Start 2017/08/16 QC#187611
                cfsXsMtrCopyQty = aggXsMtrCopyQtymap.get("FIRST_XS_MTR_COPY_DPLY_QTY");
                // Mod End 2017/08/16 QC#187611
                if (!hasValue(cfsXsMtrCopyQty) || cfsXsMtrCopyQty.compareTo(BigDecimal.ZERO) == 0) {
                    continue;
                }

                if (cfsXsMtrCopyQty.compareTo(aggXsMtrCopyQty) != 0) {
                    setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(NSZM0990E, new String[] {serNum }), DS_CONTR_DTL_PK, dsContrDtlPk);
                    break;
                }
            }
            EZDMsg.copy(rtrnTMsg, null, rtrnTMsgArray.no(rtrnTMsgArray.getValidCount()), null);
            rtrnTMsgArray.setValidCount(rtrnTMsgArray.getValidCount() + 1);
        }
        // Add End 2017/07/31 QC#18764
        return rtrnTMsgArray;
    }

    /**
     * checkCfsNonFltUsageTier2AllowanceDiff
     * @param mainClass NSZC057001
     * @param param NSZC057001PMsg
     * @return DS_CONTR_VLD_RSLT_WRKTMsgArray
     */
    public static DS_CONTR_VLD_RSLT_WRKTMsgArray checkCfsNonFltUsageTier2AllowanceDiff(NSZC057001 mainClass, NSZC057001PMsg param) {
        return checkCfsNonFltUsageTierAllowanceDiff(mainClass, param, NSZM0995E, TIER_2);
    }

    /**
     * checkCfsNonFltUsageTier3AllowanceDiff
     * @param mainClass NSZC057001
     * @param param NSZC057001PMsg
     * @return DS_CONTR_VLD_RSLT_WRKTMsgArray
     */
    public static DS_CONTR_VLD_RSLT_WRKTMsgArray checkCfsNonFltUsageTier3AllowanceDiff(NSZC057001 mainClass, NSZC057001PMsg param) {
        return checkCfsNonFltUsageTierAllowanceDiff(mainClass, param, NSZM0996E, TIER_3);
    }

    /**
     * checkCfsNonFltUsageTier4AllowanceDiff
     * @param mainClass NSZC057001
     * @param param NSZC057001PMsg
     * @return DS_CONTR_VLD_RSLT_WRKTMsgArray
     */
    public static DS_CONTR_VLD_RSLT_WRKTMsgArray checkCfsNonFltUsageTier4AllowanceDiff(NSZC057001 mainClass, NSZC057001PMsg param) {
        return checkCfsNonFltUsageTierAllowanceDiff(mainClass, param, NSZM0997E, TIER_4);
    }

    /**
     * checkCfsFltBaseCycleDiff
     * @param mainClass NSZC057001
     * @param param NSZC057001PMsg
     * @return DS_CONTR_VLD_RSLT_WRKTMsgArray
     */
    public static DS_CONTR_VLD_RSLT_WRKTMsgArray checkCfsFltBaseCycleDiff(NSZC057001 mainClass, NSZC057001PMsg param) {

        DS_CONTR_VLD_RSLT_WRKTMsgArray rtrnTMsgArray = new DS_CONTR_VLD_RSLT_WRKTMsgArray();
        rtrnTMsgArray.setMsgList(new DS_CONTR_VLD_RSLT_WRKTMsg[MAX_SIZE]);
        int arrayIdx = 0;

        DS_CONTR_VLD_RSLT_WRKTMsg rtrnTMsg;

        DS_CONTRTMsg dsContrTMsg = mainClass.dsContrCache.get(KEY);
        // START 2017/06/22 K.Kitachi [QC#19340, MOD]
        // START 2018/02/02 U.Kim [QC#23687,MOD]
        // if (!isLeaseCompany(dsContrTMsg)) {
        if (!isLeaseCompany(mainClass, dsContrTMsg)) {
        // END 2018/02/02 U.Kim [QC#23687,MOD]
        // END 2017/06/22 K.Kitachi [QC#19340, MOD]
            return rtrnTMsgArray;
        }

        // get machine & contract info
        Map<String, Object> machAndContrInfo = getMachAndContrInfoForFleetLine(mainClass, param.glblCmpyCd.getValue(), dsContrTMsg.dsContrPk.getValue());
        if (machAndContrInfo == null) {
            return rtrnTMsgArray;
        }

        BigDecimal dsContrDtlPk = (BigDecimal) machAndContrInfo.get("DS_CONTR_DTL_PK");
        String cfsBllgCycleCd = (String) machAndContrInfo.get("CFS_BASE_BLLG_CYCLE_CD");
        if (!hasValue(cfsBllgCycleCd)) {
            return rtrnTMsgArray;
        }

        // add start 2017/04/17 CSA Defect#18155
        // Mod Start 2017/07/27 QC#20226
        DS_CONTR_DTLTMsg dsContrDtlTMsg = getDsContrDtlForPk(param.glblCmpyCd.getValue(), dsContrDtlPk);
        Map<String, Object> dsContrPrcEff = getDsContrPrcEff(mainClass, param.glblCmpyCd.getValue(), dsContrDtlPk, getProcDt(param.slsDt.getValue(), dsContrDtlTMsg));
        // Mod End 2017/07/27 QC#20226
        if (dsContrPrcEff == null) {
            return rtrnTMsgArray;
        }
        BigDecimal basePrcDealAmt = (BigDecimal) dsContrPrcEff.get("BASE_PRC_DEAL_AMT");
        if (!hasValue(basePrcDealAmt)) {
            return rtrnTMsgArray;
        }
        // add end 2017/04/17 CSA Defect#18155

        rtrnTMsg = new DS_CONTR_VLD_RSLT_WRKTMsg();
        setValue(rtrnTMsg.dsContrPk, dsContrTMsg.dsContrPk);
        setValue(rtrnTMsg.dsContrDtlPk, dsContrDtlPk);
        setValue(rtrnTMsg.dsContrVldStsCd, DS_CONTR_VLD_STS.SUCCESS);
        // mod start 2017/04/17 CSA Defect#18155
        if (BigDecimal.ZERO.compareTo(basePrcDealAmt) != 0) {
            // START 2018/02/14 K.Kojima [QC#24145,ADD]
            if (!existCfsLeaseContrForBeforeCheck(mainClass, dsContrTMsg)) {
                setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(NSZM1321E), DS_CONTR_DTL_PK, dsContrDtlPk);
            } else
            // END 2018/02/14 K.Kojima [QC#24145,ADD]
            // START 2016/06/30 K.Yamada [QC#3051, MOD]
            if (!existCfsLeaseContr(mainClass, param.glblCmpyCd.getValue(), null, null, ZYPConstant.FLG_ON_Y, null, null, dsContrTMsg.dsContrPk.getValue()) || existCfsLeaseContrForCheckCfsFltBaseCycleDiff(mainClass, param.glblCmpyCd.getValue(), dsContrTMsg.dsContrPk.getValue(), cfsBllgCycleCd)) {
            // END 2016/06/30 K.Yamada [QC#3051, MOD]
                setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(NSZM0988E, new String[] {dsContrTMsg.dsContrNum.getValue() }), DS_CONTR_DTL_PK, dsContrDtlPk);
            }
        }
        EZDMsg.copy(rtrnTMsg, null, rtrnTMsgArray.no(arrayIdx++), null);
        rtrnTMsgArray.setValidCount(rtrnTMsgArray.getValidCount() + 1);

        return rtrnTMsgArray;
    }

    /**
     * checkCfsFltUsageCycleDiff
     * @param mainClass NSZC057001
     * @param param NSZC057001PMsg
     * @return DS_CONTR_VLD_RSLT_WRKTMsgArray
     */
    public static DS_CONTR_VLD_RSLT_WRKTMsgArray checkCfsFltUsageCycleDiff(NSZC057001 mainClass, NSZC057001PMsg param) {

        DS_CONTR_VLD_RSLT_WRKTMsgArray rtrnTMsgArray = new DS_CONTR_VLD_RSLT_WRKTMsgArray();
        rtrnTMsgArray.setMsgList(new DS_CONTR_VLD_RSLT_WRKTMsg[MAX_SIZE]);
        int arrayIdx = 0;

        DS_CONTR_VLD_RSLT_WRKTMsg rtrnTMsg;

        DS_CONTRTMsg dsContrTMsg = mainClass.dsContrCache.get(KEY);
        // START 2017/06/22 K.Kitachi [QC#19340, MOD]
        // START 2018/02/02 U.Kim [QC#23687,MOD]
        // if (!isLeaseCompany(dsContrTMsg)) {
        if (!isLeaseCompany(mainClass, dsContrTMsg)) {
        // END 2018/02/02 U.Kim [QC#23687,MOD]
        // END 2017/06/22 K.Kitachi [QC#19340, MOD]
            return rtrnTMsgArray;
        }

        // get machine & contract info
        Map<String, Object> machAndContrInfo = getMachAndContrInfoForFleetLine(mainClass, param.glblCmpyCd.getValue(), dsContrTMsg.dsContrPk.getValue());
        if (machAndContrInfo == null) {
            return rtrnTMsgArray;
        }

        BigDecimal dsContrDtlPk = (BigDecimal) machAndContrInfo.get("DS_CONTR_DTL_PK");
        String usgChrgFlg = (String) machAndContrInfo.get("USG_CHRG_FLG");
        if (usgChrgFlg.equals(ZYPConstant.FLG_OFF_N)) {
            return rtrnTMsgArray;
        }

        rtrnTMsg = new DS_CONTR_VLD_RSLT_WRKTMsg();
        setValue(rtrnTMsg.dsContrPk, dsContrTMsg.dsContrPk);
        setValue(rtrnTMsg.dsContrDtlPk, dsContrDtlPk);
        // START 2017/08/08 K.Kitachi [QC#20531, MOD]
        setValue(rtrnTMsg.dsContrVldStsCd, DS_CONTR_VLD_STS.SUCCESS);
        // END 2017/08/08 K.Kitachi [QC#20531, MOD]

        DS_CONTR_BLLG_MTRTMsgArray dsContrBllgMtrTMsgArray = mainClass.dsContrBllgMtrArrayCache.get(KEY + dsContrDtlPk);

        if (dsContrBllgMtrTMsgArray == null || dsContrBllgMtrTMsgArray.getValidCount() == 0) {
            dsContrBllgMtrTMsgArray = getDsContrBllgMtr(param.glblCmpyCd.getValue(), dsContrDtlPk);
            if (dsContrBllgMtrTMsgArray != null && dsContrBllgMtrTMsgArray.getValidCount() > 0) {
                mainClass.dsContrBllgMtrArrayCache.put(KEY + dsContrDtlPk, dsContrBllgMtrTMsgArray);
            }
        }

        if (dsContrBllgMtrTMsgArray == null || dsContrBllgMtrTMsgArray.getValidCount() == 0) {
            return rtrnTMsgArray;
        }

        // START 2017/08/08 K.Kitachi [QC#20531, ADD]
        boolean isChecked = false;
        // END 2017/08/08 K.Kitachi [QC#20531, ADD]
        for (int j = 0; j < dsContrBllgMtrTMsgArray.getValidCount(); j++) {
            DS_CONTR_BLLG_MTRTMsg dsContrBllgMtrTMsg = dsContrBllgMtrTMsgArray.no(j);
            // START 2017/08/03 K.Kitachi [QC#20224, ADD]
            // START 2018/02/02 U.Kim [QC#23687, ADD]
            // if (!NSXC001001ContrValidation.isLeaseCompany(param.glblCmpyCd.getValue(), dsContrBllgMtrTMsg.bllgMtrBillToCustCd.getValue())) {
            if (!isLeaseCompanyForBllgMtr(mainClass, param.glblCmpyCd.getValue(), dsContrBllgMtrTMsg.bllgMtrBillToCustCd.getValue())) {
                continue;
            }
            // END 2018/02/02 U.Kim [QC#23687, ADD]
            // END 2017/08/03 K.Kitachi [QC#20224, ADD]
            // START 2017/08/08 K.Kitachi [QC#20531, ADD]
            isChecked = true;
            // END 2017/08/08 K.Kitachi [QC#20531, ADD]
            // START 2018/02/14 K.Kojima [QC#24145,ADD]
            if (!existCfsLeaseContrForBeforeCheck(mainClass, dsContrTMsg)) {
                setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(NSZM1321E), DS_CONTR_DTL_PK, dsContrDtlPk);
                break;
            }
            // END 2018/02/14 K.Kojima [QC#24145,ADD]
            String bllgCycleCd = dsContrBllgMtrTMsg.bllgMtrBllgCycleCd.getValue();
            if (!hasValue(bllgCycleCd)) {
                continue;
            }
            String cfsBllgCycleCd = convertBllgCycleCd(param.glblCmpyCd.getValue(), bllgCycleCd);
            if (!hasValue(cfsBllgCycleCd)) {
                continue;
            }
            // START 2016/06/30 K.Yamada [QC#3051, ADD]
            String usgTpBillCd = convertMtrLbCd(param.glblCmpyCd.getValue(), dsContrBllgMtrTMsg.bllgMtrLbCd.getValue());
            if (!hasValue(usgTpBillCd)) {
                continue;
            }
            // END 2016/06/30 K.Yamada [QC#3051, ADD]
            // mod start 2017/04/20 CSA Defect#18383
            if (existCfsLeaseContr(mainClass, param.glblCmpyCd.getValue(), null, null, null, ZYPConstant.FLG_ON_Y, usgTpBillCd, dsContrTMsg.dsContrPk.getValue()) && existCfsLeaseContrForCheckCfsFltUsageCycleDiff(mainClass, param.glblCmpyCd.getValue(), dsContrTMsg.dsContrPk.getValue(), cfsBllgCycleCd, usgTpBillCd)) {
            // mod end 2017/04/20 CSA Defect#18383
                setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(NSZM0989E, new String[] {dsContrTMsg.dsContrNum.getValue() }), DS_CONTR_BLLG_MTR_PK, dsContrBllgMtrTMsg.dsContrBllgMtrPk.getValue());
                break;
            }
        }

        // START 2017/08/08 K.Kitachi [QC#20531, MOD]
        if (isChecked) {
            EZDMsg.copy(rtrnTMsg, null, rtrnTMsgArray.no(arrayIdx++), null);
            rtrnTMsgArray.setValidCount(rtrnTMsgArray.getValidCount() + 1);
        }
        // END 2017/08/08 K.Kitachi [QC#20531, MOD]

        return rtrnTMsgArray;
    }

    /**
     * checkCfsFltBaseChargeDiff
     * @param mainClass NSZC057001
     * @param param NSZC057001PMsg
     * @return DS_CONTR_VLD_RSLT_WRKTMsgArray
     */
    public static DS_CONTR_VLD_RSLT_WRKTMsgArray checkCfsFltBaseChargeDiff(NSZC057001 mainClass, NSZC057001PMsg param) {

        DS_CONTR_VLD_RSLT_WRKTMsgArray rtrnTMsgArray = new DS_CONTR_VLD_RSLT_WRKTMsgArray();
        rtrnTMsgArray.setMsgList(new DS_CONTR_VLD_RSLT_WRKTMsg[MAX_SIZE]);
        int arrayIdx = 0;

        DS_CONTR_VLD_RSLT_WRKTMsg rtrnTMsg;

        DS_CONTRTMsg dsContrTMsg = mainClass.dsContrCache.get(KEY);
        // START 2017/06/22 K.Kitachi [QC#19340, MOD]
        // START 2018/02/02 U.Kim [QC#23687,MOD]
        // if (!isLeaseCompany(dsContrTMsg)) {
        if (!isLeaseCompany(mainClass, dsContrTMsg)) {
        // END 2018/02/02 U.Kim [QC#23687,MOD]
        // END 2017/06/22 K.Kitachi [QC#19340, MOD]
            return rtrnTMsgArray;
        }

        // get machine & contract info
        Map<String, Object> machAndContrInfo = getMachAndContrInfoForFleetLine(mainClass, param.glblCmpyCd.getValue(), dsContrTMsg.dsContrPk.getValue());
        if (machAndContrInfo == null) {
            return rtrnTMsgArray;
        }
        BigDecimal dsContrDtlPk = (BigDecimal) machAndContrInfo.get("DS_CONTR_DTL_PK");
        // Mod Start 2017/07/27 QC#20226
        DS_CONTR_DTLTMsg dsContrDtlTMsg = getDsContrDtlForPk(param.glblCmpyCd.getValue(), dsContrDtlPk);
        BigDecimal basePrcDealAmt = getBaseChrgAmt(mainClass, param.glblCmpyCd.getValue(), dsContrDtlPk, getProcDt(param.slsDt.getValue(), dsContrDtlTMsg));
        // Mod End 2017/07/27 QC#20226
        if (basePrcDealAmt == null) {
            return rtrnTMsgArray;
        }
        BigDecimal sumBasePerCycleDealAmt = getSumBasePerCycleDealAmtForCheckCfsFltBaseChargeDiff(mainClass, param.glblCmpyCd.getValue(), dsContrTMsg.dsContrPk.getValue());

        rtrnTMsg = new DS_CONTR_VLD_RSLT_WRKTMsg();
        setValue(rtrnTMsg.dsContrPk, dsContrTMsg.dsContrPk);
        setValue(rtrnTMsg.dsContrDtlPk, dsContrDtlPk);
        setValue(rtrnTMsg.dsContrVldStsCd, DS_CONTR_VLD_STS.SUCCESS);
        // START 2018/02/14 K.Kojima [QC#24145,ADD]
        if (!existCfsLeaseContrForBeforeCheck(mainClass, dsContrTMsg)) {
            // START 2018/05/22 K.Kim [QC#26201, ADD]
            if (BigDecimal.ZERO.compareTo(basePrcDealAmt) == 0) {
                return rtrnTMsgArray;
            }
            // END 2018/05/22 K.Kim [QC#26201, ADD]
            setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(NSZM1321E), DS_CONTR_DTL_PK, dsContrDtlPk);
        } else
        // END 2018/02/14 K.Kojima [QC#24145,ADD]
        // mod start 2017/04/17 CSA Defect#18155
        if (hasValue(sumBasePerCycleDealAmt) && basePrcDealAmt.compareTo(sumBasePerCycleDealAmt) != 0) {
        // mod end 2017/04/17 CSA Defect#18155
            setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(NSZM1000E, new String[] {dsContrTMsg.dsContrNum.getValue() }), DS_CONTR_DTL_PK, dsContrDtlPk);
            // START 2018/03/15 K.Kojima [QC#22611,DEL]
            // setValue(rtrnTMsg.dsContrVldStsCd, DS_CONTR_VLD_STS.WARNING);
            // END 2018/03/15 K.Kojima [QC#22611,DEL]
        }
        EZDMsg.copy(rtrnTMsg, null, rtrnTMsgArray.no(arrayIdx++), null);
        rtrnTMsgArray.setValidCount(rtrnTMsgArray.getValidCount() + 1);

        return rtrnTMsgArray;
    }

    /**
     * checkCfsFltUsageTier1RateDiff
     * @param mainClass NSZC057001
     * @param param NSZC057001PMsg
     * @return DS_CONTR_VLD_RSLT_WRKTMsgArray
     */
    public static DS_CONTR_VLD_RSLT_WRKTMsgArray checkCfsFltUsageTier1RateDiff(NSZC057001 mainClass, NSZC057001PMsg param) {

        DS_CONTR_VLD_RSLT_WRKTMsgArray rtrnTMsgArray = new DS_CONTR_VLD_RSLT_WRKTMsgArray();
        rtrnTMsgArray.setMsgList(new DS_CONTR_VLD_RSLT_WRKTMsg[MAX_SIZE]);
        int arrayIdx = 0;

        DS_CONTR_VLD_RSLT_WRKTMsg rtrnTMsg = null;

        DS_CONTRTMsg dsContrTMsg = mainClass.dsContrCache.get(KEY);
        // START 2017/06/22 K.Kitachi [QC#19340, MOD]
        // START 2018/02/02 U.Kim [QC#23687,MOD]
        // if (!isLeaseCompany(dsContrTMsg)) {
        if (!isLeaseCompany(mainClass, dsContrTMsg)) {
        // END 2018/02/02 U.Kim [QC#23687,MOD]
        // END 2017/06/22 K.Kitachi [QC#19340, MOD]
            return rtrnTMsgArray;
        }

        // get machine & contract info
        Map<String, Object> machAndContrInfo = getMachAndContrInfoForFleetLine(mainClass, param.glblCmpyCd.getValue(), dsContrTMsg.dsContrPk.getValue());
        if (machAndContrInfo == null) {
            return rtrnTMsgArray;
        }
        BigDecimal dsContrDtlPk = (BigDecimal) machAndContrInfo.get("DS_CONTR_DTL_PK");
        // Add Start 2017/07/27 QC#20226
        DS_CONTR_DTLTMsg dsContrDtlTMsg = getDsContrDtlForPk(param.glblCmpyCd.getValue(), dsContrDtlPk);
        String procDt = getProcDt(param.slsDt.getValue(), dsContrDtlTMsg);
        // Add End 2017/07/27 QC#20226

        rtrnTMsg = new DS_CONTR_VLD_RSLT_WRKTMsg();
        setValue(rtrnTMsg.dsContrPk, dsContrTMsg.dsContrPk);
        setValue(rtrnTMsg.dsContrDtlPk, dsContrDtlPk);
        // START 2017/08/08 K.Kitachi [QC#20531, MOD]
        setValue(rtrnTMsg.dsContrVldStsCd, DS_CONTR_VLD_STS.SUCCESS);
        // END 2017/08/08 K.Kitachi [QC#20531, MOD]
        // Mod Start 2017/07/27 QC#20226
        List<Map<String, Object>> usgChrgInfo = getUsgChrgInfo(mainClass, param.glblCmpyCd.getValue(), dsContrDtlPk, procDt);
        // Mod End 2017/07/27 QC#20226
        // START 2017/08/08 K.Kitachi [QC#20531, ADD]
        boolean isChecked = false;
        // END 2017/08/08 K.Kitachi [QC#20531, ADD]
        for (int j = 0; j < usgChrgInfo.size(); j++) {
            // START 2017/08/03 K.Kitachi [QC#20224, ADD]
            // START 2018/02/02 U.Kim [QC#23687, ADD]
            // if (!NSXC001001ContrValidation.isLeaseCompany(param.glblCmpyCd.getValue(), (String) usgChrgInfo.get(j).get("BLLG_MTR_BILL_TO_CUST_CD"))) {
            if (!isLeaseCompanyForBllgMtr(mainClass, param.glblCmpyCd.getValue(), (String) usgChrgInfo.get(j).get("BLLG_MTR_BILL_TO_CUST_CD"))) {
                continue;
            }
            // END 2018/02/02 U.Kim [QC#23687, ADD]
            // END 2017/08/03 K.Kitachi [QC#20224, ADD]
            // START 2017/08/08 K.Kitachi [QC#20531, ADD]
            isChecked = true;
            // END 2017/08/08 K.Kitachi [QC#20531, ADD]
            // START 2018/02/14 K.Kojima [QC#24145,ADD]
            if (!existCfsLeaseContrForBeforeCheck(mainClass, dsContrTMsg)) {
                setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(NSZM1321E), DS_CONTR_DTL_PK, dsContrDtlPk);
                break;
            }
            // END 2018/02/14 K.Kojima [QC#24145,ADD]
            // START 2016/06/30 K.Yamada [QC#3051, MOD]
            // Mod Start 2017/07/27 QC#20226
            BigDecimal dsContrPrcEffPk = getDsContrPrcEffPk(mainClass, param.glblCmpyCd.getValue(), dsContrDtlPk, procDt);
            // Mod End 2017/07/27 QC#20226
            String bllgMtrLbCd = (String) usgChrgInfo.get(j).get("BLLG_MTR_LB_CD");
            BigDecimal xsMtrAmtRate = (BigDecimal) usgChrgInfo.get(j).get("XS_MTR_AMT_RATE");
            String usgTpBillCd = convertMtrLbCd(param.glblCmpyCd.getValue(), bllgMtrLbCd);
            if (!hasValue(usgTpBillCd)) {
                continue;
            }
            BigDecimal tierCfs = getTierCount(mainClass, param.glblCmpyCd.getValue(), null, null, null, ZYPConstant.FLG_ON_Y, usgTpBillCd, dsContrTMsg.dsContrPk.getValue());
            // mod start 2017/04/20 CSA Defect#18383
            if (!hasValue(tierCfs)) {
                continue;
            }
            if (tierCfs.compareTo(BigDecimal.ZERO) == 0 || existCfsLeaseContrForCheckCfsFltUsageTierRateDiff(mainClass, param.glblCmpyCd.getValue(), dsContrTMsg.dsContrPk.getValue(), usgTpBillCd, xsMtrAmtRate, null, null, null)) {
                setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(NSZM0990E, new String[] {dsContrTMsg.dsContrNum.getValue() }), DS_CONTR_PRC_EFF_PK, dsContrPrcEffPk);
                break;
            }
            // mod end 2017/04/20 CSA Defect#18383
            // END 2016/06/30 K.Yamada [QC#3051, MOD]
        }
        // START 2017/08/08 K.Kitachi [QC#20531, MOD]
        if (isChecked) {
            EZDMsg.copy(rtrnTMsg, null, rtrnTMsgArray.no(arrayIdx++), null);
            rtrnTMsgArray.setValidCount(rtrnTMsgArray.getValidCount() + 1);
        }
        // END 2017/08/08 K.Kitachi [QC#20531, MOD]

        return rtrnTMsgArray;
    }

    /**
     * checkCfsFltUsageTier2RateDiff
     * @param mainClass NSZC057001
     * @param param NSZC057001PMsg
     * @return DS_CONTR_VLD_RSLT_WRKTMsgArray
     */
    public static DS_CONTR_VLD_RSLT_WRKTMsgArray checkCfsFltUsageTier2RateDiff(NSZC057001 mainClass, NSZC057001PMsg param) {
        return checkCfsFltUsageTierRateDiff(mainClass, param, NSZM0991E, TIER_2);
    }

    /**
     * checkCfsFltUsageTier3RateDiff
     * @param mainClass NSZC057001
     * @param param NSZC057001PMsg
     * @return DS_CONTR_VLD_RSLT_WRKTMsgArray
     */
    public static DS_CONTR_VLD_RSLT_WRKTMsgArray checkCfsFltUsageTier3RateDiff(NSZC057001 mainClass, NSZC057001PMsg param) {
        return checkCfsFltUsageTierRateDiff(mainClass, param, NSZM0992E, TIER_3);
    }

    /**
     * checkCfsFltUsageTier4RateDiff
     * @param mainClass NSZC057001
     * @param param NSZC057001PMsg
     * @return DS_CONTR_VLD_RSLT_WRKTMsgArray
     */
    public static DS_CONTR_VLD_RSLT_WRKTMsgArray checkCfsFltUsageTier4RateDiff(NSZC057001 mainClass, NSZC057001PMsg param) {
        return checkCfsFltUsageTierRateDiff(mainClass, param, NSZM0993E, TIER_4);
    }

    /**
     * checkCfsFltUsageTier1AllowanceDiff
     * @param mainClass NSZC057001
     * @param param NSZC057001PMsg
     * @return DS_CONTR_VLD_RSLT_WRKTMsgArray
     */
    public static DS_CONTR_VLD_RSLT_WRKTMsgArray checkCfsFltUsageTier1AllowanceDiff(NSZC057001 mainClass, NSZC057001PMsg param) {

        DS_CONTR_VLD_RSLT_WRKTMsgArray rtrnTMsgArray = new DS_CONTR_VLD_RSLT_WRKTMsgArray();
        rtrnTMsgArray.setMsgList(new DS_CONTR_VLD_RSLT_WRKTMsg[MAX_SIZE]);
        int arrayIdx = 0;

        DS_CONTR_VLD_RSLT_WRKTMsg rtrnTMsg = null;

        DS_CONTRTMsg dsContrTMsg = mainClass.dsContrCache.get(KEY);
        // START 2017/06/22 K.Kitachi [QC#19340, MOD]
        // START 2018/02/02 U.Kim [QC#23687,MOD]
        // if (!isLeaseCompany(dsContrTMsg)) {
        if (!isLeaseCompany(mainClass, dsContrTMsg)) {
        // END 2018/02/02 U.Kim [QC#23687,MOD]
        // END 2017/06/22 K.Kitachi [QC#19340, MOD]
            return rtrnTMsgArray;
        }

        // get machine & contract info
        Map<String, Object> machAndContrInfo = getMachAndContrInfoForFleetLine(mainClass, param.glblCmpyCd.getValue(), dsContrTMsg.dsContrPk.getValue());
        if (machAndContrInfo == null) {
            return rtrnTMsgArray;
        }
        BigDecimal dsContrDtlPk = (BigDecimal) machAndContrInfo.get("DS_CONTR_DTL_PK");
        // Add Start 2017/07/27 QC#20226
        DS_CONTR_DTLTMsg dsContrDtlTMsg = getDsContrDtlForPk(param.glblCmpyCd.getValue(), dsContrDtlPk);
        String procDt = getProcDt(param.slsDt.getValue(), dsContrDtlTMsg);
        // Add End 2017/07/27 QC#20226

        rtrnTMsg = new DS_CONTR_VLD_RSLT_WRKTMsg();
        setValue(rtrnTMsg.dsContrPk, dsContrTMsg.dsContrPk);
        setValue(rtrnTMsg.dsContrDtlPk, dsContrDtlPk);
        // START 2017/08/08 K.Kitachi [QC#20531, MOD]
        setValue(rtrnTMsg.dsContrVldStsCd, DS_CONTR_VLD_STS.SUCCESS);
        // END 2017/08/08 K.Kitachi [QC#20531, MOD]
        // Mod Start 2017/07/27 QC#20226
        List<Map<String, Object>> usgChrgInfo = getUsgChrgInfo(mainClass, param.glblCmpyCd.getValue(), dsContrDtlPk, procDt);
        // Mod End 2017/07/27 QC#20226
        // START 2017/08/08 K.Kitachi [QC#20531, ADD]
        boolean isChecked = false;
        // END 2017/08/08 K.Kitachi [QC#20531, ADD]
        for (int j = 0; j < usgChrgInfo.size(); j++) {
            // START 2017/08/03 K.Kitachi [QC#20224, ADD]
            // START 2018/02/02 U.Kim [QC#23687, ADD]
            // if (!NSXC001001ContrValidation.isLeaseCompany(param.glblCmpyCd.getValue(), (String) usgChrgInfo.get(j).get("BLLG_MTR_BILL_TO_CUST_CD"))) {
            if (!isLeaseCompanyForBllgMtr(mainClass, param.glblCmpyCd.getValue(), (String) usgChrgInfo.get(j).get("BLLG_MTR_BILL_TO_CUST_CD"))) {
                continue;
            }
            // END 2018/02/02 U.Kim [QC#23687, ADD]
            // END 2017/08/03 K.Kitachi [QC#20224, ADD]
            // START 2017/08/08 K.Kitachi [QC#20531, ADD]
            isChecked = true;
            // END 2017/08/08 K.Kitachi [QC#20531, ADD]
            // START 2018/02/14 K.Kojima [QC#24145,ADD]
            if (!existCfsLeaseContrForBeforeCheck(mainClass, dsContrTMsg)) {
                setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(NSZM1321E), DS_CONTR_DTL_PK, dsContrDtlPk);
                break;
            }
            // END 2018/02/14 K.Kojima [QC#24145,ADD]
            // START 2016/06/30 K.Yamada [QC#3051, MOD]
            // Mod Start 2017/07/27 QC#20226
            BigDecimal dsContrPrcEffPk = getDsContrPrcEffPk(mainClass, param.glblCmpyCd.getValue(), dsContrDtlPk, procDt);
            // Mod End 2017/07/27 QC#20226
            String bllgMtrLbCd = (String) usgChrgInfo.get(j).get("BLLG_MTR_LB_CD");
            BigDecimal xsMtrCopyQty = (BigDecimal) usgChrgInfo.get(j).get("XS_MTR_COPY_QTY");
            String usgTpBillCd = convertMtrLbCd(param.glblCmpyCd.getValue(), bllgMtrLbCd);
            if (!hasValue(usgTpBillCd)) {
                continue;
            }
            BigDecimal tierCfs = getTierCount(mainClass, param.glblCmpyCd.getValue(), null, null, null, ZYPConstant.FLG_ON_Y, usgTpBillCd, dsContrTMsg.dsContrPk.getValue());
            // mod start 2017/04/20 CSA Defect#18383
            if (!hasValue(tierCfs)) {
                continue;
            }
            if (tierCfs.compareTo(BigDecimal.ZERO) == 0 || existCfsLeaseContrForCheckCfsFltUsageTierAllowanceDiff(mainClass, param.glblCmpyCd.getValue(), dsContrTMsg.dsContrPk.getValue(), usgTpBillCd, xsMtrCopyQty, null, null, null)) {
                setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(NSZM0990E, new String[] {dsContrTMsg.dsContrNum.getValue() }), DS_CONTR_PRC_EFF_PK, dsContrPrcEffPk);
                break;
            }
            // mod end 2017/04/20 CSA Defect#18383
            // END 2016/06/30 K.Yamada [QC#3051, MOD]
        }
        // START 2017/08/08 K.Kitachi [QC#20531, MOD]
        if (isChecked) {
            EZDMsg.copy(rtrnTMsg, null, rtrnTMsgArray.no(arrayIdx++), null);
            rtrnTMsgArray.setValidCount(rtrnTMsgArray.getValidCount() + 1);
        }
        // END 2017/08/08 K.Kitachi [QC#20531, MOD]

        return rtrnTMsgArray;
    }

    /**
     * checkCfsFltUsageTier2AllowanceDiff
     * @param mainClass NSZC057001
     * @param param NSZC057001PMsg
     * @return DS_CONTR_VLD_RSLT_WRKTMsgArray
     */
    public static DS_CONTR_VLD_RSLT_WRKTMsgArray checkCfsFltUsageTier2AllowanceDiff(NSZC057001 mainClass, NSZC057001PMsg param) {
        return checkCfsFltUsageTierAllowanceDiff(mainClass, param, NSZM0995E, TIER_2);
    }

    /**
     * checkCfsFltUsageTier3AllowanceDiff
     * @param mainClass NSZC057001
     * @param param NSZC057001PMsg
     * @return DS_CONTR_VLD_RSLT_WRKTMsgArray
     */
    public static DS_CONTR_VLD_RSLT_WRKTMsgArray checkCfsFltUsageTier3AllowanceDiff(NSZC057001 mainClass, NSZC057001PMsg param) {
        return checkCfsFltUsageTierAllowanceDiff(mainClass, param, NSZM0996E, TIER_3);
    }

    /**
     * checkCfsFltUsageTier4AllowanceDiff
     * @param mainClass NSZC057001
     * @param param NSZC057001PMsg
     * @return DS_CONTR_VLD_RSLT_WRKTMsgArray
     */
    public static DS_CONTR_VLD_RSLT_WRKTMsgArray checkCfsFltUsageTier4AllowanceDiff(NSZC057001 mainClass, NSZC057001PMsg param) {
        return checkCfsFltUsageTierAllowanceDiff(mainClass, param, NSZM0997E, TIER_4);
    }

    /**
     * checkCfsFltFtrDiff
     * @param mainClass NSZC057001
     * @param param NSZC057001PMsg
     * @return DS_CONTR_VLD_RSLT_WRKTMsgArray
     */
    public static DS_CONTR_VLD_RSLT_WRKTMsgArray checkCfsFltFtrDiff(NSZC057001 mainClass, NSZC057001PMsg param) {

        DS_CONTR_VLD_RSLT_WRKTMsgArray rtrnTMsgArray = new DS_CONTR_VLD_RSLT_WRKTMsgArray();
        rtrnTMsgArray.setMsgList(new DS_CONTR_VLD_RSLT_WRKTMsg[MAX_SIZE]);
        int arrayIdx = 0;

        DS_CONTR_VLD_RSLT_WRKTMsg rtrnTMsg;

        DS_CONTRTMsg dsContrTMsg = mainClass.dsContrCache.get(KEY);
        // START 2017/06/22 K.Kitachi [QC#19340, MOD]
        // START 2018/02/02 U.Kim [QC#23687,MOD]
        // if (!isLeaseCompany(dsContrTMsg)) {
        if (!isLeaseCompany(mainClass, dsContrTMsg)) {
        // END 2018/02/02 U.Kim [QC#23687,MOD]
        // END 2017/06/22 K.Kitachi [QC#19340, MOD]
            return rtrnTMsgArray;
        }

        // START 2017/11/16 [QC#21179, ADD]
        String excludeCls = ZYPCodeDataUtil.getVarCharConstValue(NSZC0570_CFS_FTR_CHC_CONTR_CLS, param.glblCmpyCd.getValue());
        if (Arrays.asList(excludeCls.split(",")).contains(dsContrTMsg.dsContrClsCd.getValue())) {
            return rtrnTMsgArray;
        }
        // END 2017/11/16 [QC#21179, ADD]

        // Add Start 2017/07/27 QC#20226
        String procDt = getProcDt(param.slsDt.getValue(), dsContrTMsg);
        // Add End 2017/07/27 QC#20226

        DS_CONTR_DTLTMsgArray dsContrDtlTMsgArray = mainClass.dsContrDtlArrayCache.get(KEY);

        NSXC001001GetCovTmpl getCovTmpl = new NSXC001001GetCovTmpl();
        CovTmplInfo covTmplInfo = new CovTmplInfo();
        covTmplInfo.setGlblCmpyCd(param.glblCmpyCd.getValue());
        // Mod Start 2017/07/27 QC#20226
        covTmplInfo.setSlsDt(procDt);
        // Mod End 2017/07/27 QC#20226
        // START 2017/04/24 K.Kitachi [QC#18377, ADD]
        Map<String, Object> machAndContrInfoForFleetLine = getMachAndContrInfoForFleetLine(mainClass, param.glblCmpyCd.getValue(), dsContrTMsg.dsContrPk.getValue());
        covTmplInfo.setSvcPgmMdseCd((String) machAndContrInfoForFleetLine.get("SVC_PGM_MDSE_CD"));
        String cfsFtrFlg = ZYPConstant.FLG_OFF_N;
        // START 2018/02/15 K.Kojima [QC#24145,MOD]
        // String msgId = NSZM0999E;
        String msgId = NSZM0998E;
        // END 2018/02/15 K.Kojima [QC#24145,MOD]
        if (getCovTmpl.isSuplIncl(covTmplInfo)) {
            cfsFtrFlg = ZYPConstant.FLG_ON_Y;
            // START 2018/02/15 K.Kojima [QC#24145,MOD]
            // msgId = NSZM0998E;
            msgId = NSZM0999E;
            // END 2018/02/15 K.Kojima [QC#24145,MOD]
        }
        // END 2017/04/24 K.Kitachi [QC#18377, ADD]

        for (int i = 0; i < dsContrDtlTMsgArray.getValidCount(); i++) {
            DS_CONTR_DTLTMsg dsContrDtlTMsg = dsContrDtlTMsgArray.no(i);

            // get machine & contract info
            Map<String, Object> machAndContrInfo = getMachAndContrInfo(mainClass, param.glblCmpyCd.getValue(), dsContrDtlTMsg.dsContrDtlPk.getValue());
            if (machAndContrInfo == null) {
                continue;
            }
            // START 2017/09/13 U.Kim [QC#20896, ADD]
            String mainUnitLineFlg = (String) machAndContrInfo.get("MAIN_UNIT_LINE_FLG");
            if (mainUnitLineFlg.equals(ZYPConstant.FLG_OFF_N)) {
                continue;
            }
            // END 2017/09/13 U.Kim [QC#20896, ADD]

            // START 2017/04/24 K.Kitachi [QC#18377, DEL]
//            covTmplInfo.setSvcPgmMdseCd(dsContrDtlTMsg.svcPgmMdseCd.getValue());
//            String cfsFtrFlg = ZYPConstant.FLG_OFF_N;
//            String msgId = NSZM0999E;
//            if (getCovTmpl.isSuplIncl(covTmplInfo)) {
//                cfsFtrFlg = ZYPConstant.FLG_ON_Y;
//                msgId = NSZM0998E;
//            }
            // END 2017/04/24 K.Kitachi [QC#18377, DEL]

            String serNum = (String) machAndContrInfo.get("SER_NUM");
            // mod start 2017/02/14 CSA QC#17554
            if (!hasValue(serNum)) {
                continue;
            }

            // START 2018/05/22 K.Kim [QC#26201, ADD]
            Map<String, Object> dsContrPrcEff = getDsContrPrcEff(mainClass, param.glblCmpyCd.getValue(), dsContrDtlTMsg.dsContrDtlPk.getValue(), procDt);
            if (dsContrPrcEff == null) {
                continue;
            }
            BigDecimal basePrcDealAmt = (BigDecimal) dsContrPrcEff.get("BASE_PRC_DEAL_AMT");
            String baseChrgFlg = (String) machAndContrInfo.get("BASE_CHRG_FLG");
            String usgChrgFlg = (String) machAndContrInfo.get("USG_CHRG_FLG");
            if (baseChrgFlg.equals(ZYPConstant.FLG_ON_Y) && usgChrgFlg.equals(ZYPConstant.FLG_OFF_N) && basePrcDealAmt.compareTo(BigDecimal.ZERO) == 0) {
                continue;
            }
            // END 2018/05/22 K.Kim [QC#26201, ADD]

            // mod end 2017/02/14 CSA QC#17554
            rtrnTMsg = new DS_CONTR_VLD_RSLT_WRKTMsg();
            setValue(rtrnTMsg.dsContrPk, dsContrTMsg.dsContrPk);
            setValue(rtrnTMsg.dsContrDtlPk, dsContrDtlTMsg.dsContrDtlPk);
            setValue(rtrnTMsg.dsContrVldStsCd, DS_CONTR_VLD_STS.SUCCESS);
            // START 2018/02/14 K.Kojima [QC#24145,ADD]
            if (!existCfsLeaseContrForBeforeCheck(mainClass, param.glblCmpyCd.getValue(), serNum)) {
                setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(NSZM1321E), DS_CONTR_DTL_PK, dsContrDtlTMsg.dsContrDtlPk.getValue());
            } else
            // END 2018/02/14 K.Kojima [QC#24145,ADD]
            if (!existCfsLeaseContrForCheckCfsFltFtrDiff(mainClass, param.glblCmpyCd.getValue(), serNum, cfsFtrFlg)) {
                setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(msgId, new String[] {serNum }), DS_CONTR_DTL_PK, dsContrDtlTMsg.dsContrDtlPk.getValue());
            }
            EZDMsg.copy(rtrnTMsg, null, rtrnTMsgArray.no(arrayIdx++), null);
            rtrnTMsgArray.setValidCount(rtrnTMsgArray.getValidCount() + 1);
        }

        return rtrnTMsgArray;
    }

    private static DS_CONTR_VLD_RSLT_WRKTMsgArray checkCfsNonFltUsageTierRateDiff(NSZC057001 mainClass, NSZC057001PMsg param, String msgId, int tier) {

        DS_CONTR_VLD_RSLT_WRKTMsgArray rtrnTMsgArray = new DS_CONTR_VLD_RSLT_WRKTMsgArray();
        rtrnTMsgArray.setMsgList(new DS_CONTR_VLD_RSLT_WRKTMsg[MAX_SIZE]);
        int arrayIdx = 0;

        DS_CONTR_VLD_RSLT_WRKTMsg rtrnTMsg = null;

        DS_CONTRTMsg dsContrTMsg = mainClass.dsContrCache.get(KEY);
        // START 2017/06/22 K.Kitachi [QC#19340, MOD]
        // START 2018/02/02 U.Kim [QC#23687,MOD]
        // if (!isLeaseCompany(dsContrTMsg)) {
        if (!isLeaseCompany(mainClass, dsContrTMsg)) {
        // END 2018/02/02 U.Kim [QC#23687,MOD]
        // END 2017/06/22 K.Kitachi [QC#19340, MOD]
            return rtrnTMsgArray;
        }

        DS_CONTR_DTLTMsgArray dsContrDtlTMsgArray = mainClass.dsContrDtlArrayCache.get(KEY);

        for (int i = 0; i < dsContrDtlTMsgArray.getValidCount(); i++) {
            DS_CONTR_DTLTMsg dsContrDtlTMsg = dsContrDtlTMsgArray.no(i);

            // get machine & contract info
            Map<String, Object> machAndContrInfo = getMachAndContrInfo(mainClass, param.glblCmpyCd.getValue(), dsContrDtlTMsg.dsContrDtlPk.getValue());
            if (machAndContrInfo == null) {
                continue;
            }
            // START 2017/09/13 U.Kim [QC#20896, ADD]
            String mainUnitLineFlg = (String) machAndContrInfo.get("MAIN_UNIT_LINE_FLG");
            if (mainUnitLineFlg.equals(ZYPConstant.FLG_OFF_N)) {
                continue;
            }
            // END 2017/09/13 U.Kim [QC#20896, ADD]
            String serNum = (String) machAndContrInfo.get("SER_NUM");
            // mod start 2017/02/14 CSA QC#17554
            if (!hasValue(serNum)) {
                continue;
            }
            // mod end 2017/02/14 CSA QC#17554
            String usgChrgFlg = (String) machAndContrInfo.get("USG_CHRG_FLG");
            if (ZYPConstant.FLG_OFF_N.equals(usgChrgFlg)) {
                continue;
            }
            // Add Start 2017/07/27 QC#20226
            String procDt = getProcDt(param.slsDt.getValue(), dsContrDtlTMsg);
            // Add End 2017/07/27 QC#20226

            rtrnTMsg = new DS_CONTR_VLD_RSLT_WRKTMsg();
            setValue(rtrnTMsg.dsContrPk, dsContrTMsg.dsContrPk);
            setValue(rtrnTMsg.dsContrDtlPk, dsContrDtlTMsg.dsContrDtlPk);
            // START 2017/08/08 K.Kitachi [QC#20531, MOD]
            setValue(rtrnTMsg.dsContrVldStsCd, DS_CONTR_VLD_STS.SUCCESS);
            // END 2017/08/08 K.Kitachi [QC#20531, MOD]

            DS_CONTR_BLLG_MTRTMsgArray dsContrBllgMtrTMsgArray = mainClass.dsContrBllgMtrArrayCache.get(KEY + i);
            if (dsContrBllgMtrTMsgArray == null || dsContrBllgMtrTMsgArray.getValidCount() == 0) {
                dsContrBllgMtrTMsgArray = getDsContrBllgMtr(param.glblCmpyCd.getValue(), dsContrDtlTMsg.dsContrDtlPk.getValue());
                if (dsContrBllgMtrTMsgArray != null && dsContrBllgMtrTMsgArray.getValidCount() > 0) {
                    mainClass.dsContrBllgMtrArrayCache.put(KEY + i, dsContrBllgMtrTMsgArray);
                }
            }
            if (dsContrBllgMtrTMsgArray == null || dsContrBllgMtrTMsgArray.getValidCount() == 0) {
                return rtrnTMsgArray;
            }

            // START 2017/08/08 K.Kitachi [QC#20531, ADD]
            boolean isChecked = false;
            // END 2017/08/08 K.Kitachi [QC#20531, ADD]
            for (int j = 0; j < dsContrBllgMtrTMsgArray.getValidCount(); j++) {
                // START 2016/06/30 K.Yamada [QC#3051, MOD]
                DS_CONTR_BLLG_MTRTMsg dsContrBllgMtrTMsg = dsContrBllgMtrTMsgArray.no(j);
                // START 2017/08/03 K.Kitachi [QC#20224, ADD]
                // START 2018/02/02 U.Kim [QC#23687, ADD]
                // if (!NSXC001001ContrValidation.isLeaseCompany(param.glblCmpyCd.getValue(), dsContrBllgMtrTMsg.bllgMtrBillToCustCd.getValue())) {
                if (!isLeaseCompanyForBllgMtr(mainClass, param.glblCmpyCd.getValue(), dsContrBllgMtrTMsg.bllgMtrBillToCustCd.getValue())) {
                    continue;
                }
                // END 2018/02/02 U.Kim [QC#23687, ADD]
                // END 2017/08/03 K.Kitachi [QC#20224, ADD]
                // START 2017/08/08 K.Kitachi [QC#20531, ADD]
                isChecked = true;
                // END 2017/08/08 K.Kitachi [QC#20531, ADD]
                // START 2018/02/14 K.Kojima [QC#24145,ADD]
                if (!existCfsLeaseContrForBeforeCheck(mainClass, param.glblCmpyCd.getValue(), serNum)) {
                    setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(NSZM1321E), DS_CONTR_DTL_PK, dsContrDtlTMsg.dsContrDtlPk.getValue());
                    break;
                }
                // END 2018/02/14 K.Kojima [QC#24145,ADD]
                // Mod Start 2017/07/27 QC#20226
                BigDecimal dsContrPrcEffPk = getDsContrPrcEffPk(mainClass, param.glblCmpyCd.getValue(), dsContrDtlTMsg.dsContrDtlPk.getValue(), procDt);
                // Mod End 2017/07/27 QC#20226
                String bllgMtrLbCd = dsContrBllgMtrTMsg.bllgMtrLbCd.getValue();
                String usgTpBillCd = convertMtrLbCd(param.glblCmpyCd.getValue(), bllgMtrLbCd);
                if (!hasValue(usgTpBillCd)) {
                    continue;
                }
                // Mod Start 2017/07/27 QC#20226
                List<Map<String, Object>> usgChrgInfoList = getUsgChrgInfoForCheckCfsUsageTierRateDiff(mainClass, param.glblCmpyCd.getValue(), dsContrDtlTMsg.dsContrDtlPk.getValue(), dsContrBllgMtrTMsg.dsContrBllgMtrPk.getValue(), procDt);
                // Mod End 2017/07/27 QC#20226
                BigDecimal tierCfs = getTierCount(mainClass, param.glblCmpyCd.getValue(), serNum, null, null, ZYPConstant.FLG_ON_Y, usgTpBillCd, null);
                // mod start 2017/04/20 CSA Defect#18383
                if (!hasValue(tierCfs)) {
                    continue;
                }
                if (usgChrgInfoList.size() == 0 && tierCfs.compareTo(BigDecimal.ZERO) == 0) {
                    continue;
                }
                if (usgChrgInfoList.size() == 0 || tierCfs.compareTo(BigDecimal.ZERO) == 0) {
                    setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(msgId, new String[] {serNum }), DS_CONTR_PRC_EFF_PK, dsContrPrcEffPk);
                    break;
                }
                // mod end 2017/04/20 CSA Defect#18383
                if (usgChrgInfoList.size() < tier && tierCfs.intValue() < tier) {
                    continue;
                }
                if (usgChrgInfoList.size() < tier || tierCfs.intValue() < tier) {
                    setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(msgId, new String[] {serNum }), DS_CONTR_PRC_EFF_PK, dsContrPrcEffPk);
                    break;
                }
                BigDecimal xsMtrAmtRate = (BigDecimal) usgChrgInfoList.get(tier - 1).get("XS_MTR_AMT_RATE");
                boolean existFlg = false;
                if (tier == TIER_2) {
                    existFlg = existCfsLeaseContrForCheckCfsNonFltUsageTierRateDiff(mainClass, param.glblCmpyCd.getValue(), serNum, usgTpBillCd, null, xsMtrAmtRate, null, null);
                } else if (tier == TIER_3) {
                    existFlg = existCfsLeaseContrForCheckCfsNonFltUsageTierRateDiff(mainClass, param.glblCmpyCd.getValue(), serNum, usgTpBillCd, null, null, xsMtrAmtRate, null);
                } else if (tier == TIER_4) {
                    existFlg = existCfsLeaseContrForCheckCfsNonFltUsageTierRateDiff(mainClass, param.glblCmpyCd.getValue(), serNum, usgTpBillCd, null, null, null, xsMtrAmtRate);
                }
                if (existFlg) {
                    setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(msgId, new String[] {serNum }), DS_CONTR_PRC_EFF_PK, dsContrPrcEffPk);
                    break;
                }
                // END 2016/06/30 K.Yamada [QC#3051, MOD]
            }

            // START 2017/08/08 K.Kitachi [QC#20531, MOD]
            if (isChecked) {
                EZDMsg.copy(rtrnTMsg, null, rtrnTMsgArray.no(arrayIdx++), null);
                rtrnTMsgArray.setValidCount(rtrnTMsgArray.getValidCount() + 1);
            }
            // END 2017/08/08 K.Kitachi [QC#20531, MOD]
        }

        return rtrnTMsgArray;
    }

    private static DS_CONTR_VLD_RSLT_WRKTMsgArray checkCfsNonFltUsageTierAllowanceDiff(NSZC057001 mainClass, NSZC057001PMsg param, String msgId, int tier) {

        DS_CONTR_VLD_RSLT_WRKTMsgArray rtrnTMsgArray = new DS_CONTR_VLD_RSLT_WRKTMsgArray();
        rtrnTMsgArray.setMsgList(new DS_CONTR_VLD_RSLT_WRKTMsg[MAX_SIZE]);
        int arrayIdx = 0;

        DS_CONTR_VLD_RSLT_WRKTMsg rtrnTMsg = null;

        DS_CONTRTMsg dsContrTMsg = mainClass.dsContrCache.get(KEY);
        // START 2017/06/22 K.Kitachi [QC#19340, MOD]
        // START 2018/02/02 U.Kim [QC#23687,MOD]
        // if (!isLeaseCompany(dsContrTMsg)) {
        if (!isLeaseCompany(mainClass, dsContrTMsg)) {
        // END 2018/02/02 U.Kim [QC#23687,MOD]
        // END 2017/06/22 K.Kitachi [QC#19340, MOD]
            return rtrnTMsgArray;
        }

        DS_CONTR_DTLTMsgArray dsContrDtlTMsgArray = mainClass.dsContrDtlArrayCache.get(KEY);
        // Add Start 2017/07/31 QC#18764
        Map<String, BigDecimal> aggXsMtrCopyQtyMap = new HashMap<String, BigDecimal>();
        Map<BigDecimal, String> dsContrDtlMap = new HashMap<BigDecimal, String>();
        // Add End 2017/07/31 QC#18764

        for (int i = 0; i < dsContrDtlTMsgArray.getValidCount(); i++) {
            DS_CONTR_DTLTMsg dsContrDtlTMsg = dsContrDtlTMsgArray.no(i);

            // get machine & contract info
            Map<String, Object> machAndContrInfo = getMachAndContrInfo(mainClass, param.glblCmpyCd.getValue(), dsContrDtlTMsg.dsContrDtlPk.getValue());
            if (machAndContrInfo == null) {
                continue;
            }
            // START 2017/09/13 U.Kim [QC#20896, ADD]
            String mainUnitLineFlg = (String) machAndContrInfo.get("MAIN_UNIT_LINE_FLG");
            if (mainUnitLineFlg.equals(ZYPConstant.FLG_OFF_N)) {
                continue;
            }
            // END 2017/09/13 U.Kim [QC#20896, ADD]
            String serNum = (String) machAndContrInfo.get("SER_NUM");
            // mod start 2017/02/14 CSA QC#17554
            if (!hasValue(serNum)) {
                continue;
            }
            // mod end 2017/02/14 CSA QC#17554
            String usgChrgFlg = (String) machAndContrInfo.get("USG_CHRG_FLG");
            if (ZYPConstant.FLG_OFF_N.equals(usgChrgFlg)) {
                continue;
            }
            // Add Start 2017/07/27 QC#20226
            String procDt = getProcDt(param.slsDt.getValue(), dsContrDtlTMsg);
            // Add End 2017/07/27 QC#20226

            rtrnTMsg = new DS_CONTR_VLD_RSLT_WRKTMsg();
            setValue(rtrnTMsg.dsContrPk, dsContrTMsg.dsContrPk);
            setValue(rtrnTMsg.dsContrDtlPk, dsContrDtlTMsg.dsContrDtlPk);
            // START 2017/08/08 K.Kitachi [QC#20531, MOD]
            setValue(rtrnTMsg.dsContrVldStsCd, DS_CONTR_VLD_STS.SUCCESS);
            // END 2017/08/08 K.Kitachi [QC#20531, MOD]

            DS_CONTR_BLLG_MTRTMsgArray dsContrBllgMtrTMsgArray = mainClass.dsContrBllgMtrArrayCache.get(KEY + i);
            if (dsContrBllgMtrTMsgArray == null || dsContrBllgMtrTMsgArray.getValidCount() == 0) {
                dsContrBllgMtrTMsgArray = getDsContrBllgMtr(param.glblCmpyCd.getValue(), dsContrDtlTMsg.dsContrDtlPk.getValue());
                if (dsContrBllgMtrTMsgArray != null && dsContrBllgMtrTMsgArray.getValidCount() > 0) {
                    mainClass.dsContrBllgMtrArrayCache.put(KEY + i, dsContrBllgMtrTMsgArray);
                }
            }
            if (dsContrBllgMtrTMsgArray == null || dsContrBllgMtrTMsgArray.getValidCount() == 0) {
                return rtrnTMsgArray;
            }
            // Add Start 2017/07/31 QC#18764
            if (!dsContrDtlMap.containsKey(dsContrDtlTMsg.dsContrDtlPk.getValue())) {
                dsContrDtlMap.put(dsContrDtlTMsg.dsContrDtlPk.getValue(), serNum);
            }
            // Add End 2017/07/31 QC#18764
            // START 2017/08/08 K.Kitachi [QC#20531, ADD]
            boolean isChecked = false;
            // END 2017/08/08 K.Kitachi [QC#20531, ADD]
            for (int j = 0; j < dsContrBllgMtrTMsgArray.getValidCount(); j++) {
                // START 2016/06/30 K.Yamada [QC#3051, MOD]
                DS_CONTR_BLLG_MTRTMsg dsContrBllgMtrTMsg = dsContrBllgMtrTMsgArray.no(j);
                // START 2017/08/03 K.Kitachi [QC#20224, ADD]
                // START 2018/02/02 U.Kim [QC#23687, ADD]
                // if (!NSXC001001ContrValidation.isLeaseCompany(param.glblCmpyCd.getValue(), dsContrBllgMtrTMsg.bllgMtrBillToCustCd.getValue())) {
                if (!isLeaseCompanyForBllgMtr(mainClass, param.glblCmpyCd.getValue(), dsContrBllgMtrTMsg.bllgMtrBillToCustCd.getValue())) {
                    continue;
                }
                // END 2018/02/02 U.Kim [QC#23687, ADD]
                // END 2017/08/03 K.Kitachi [QC#20224, ADD]
                // START 2017/08/08 K.Kitachi [QC#20531, ADD]
                isChecked = true;
                // END 2017/08/08 K.Kitachi [QC#20531, ADD]
                // START 2018/02/14 K.Kojima [QC#24145,ADD]
                if (!DS_CONTR_CATG.AGGREGATE.equals(dsContrTMsg.dsContrCatgCd.getValue()) && !existCfsLeaseContrForBeforeCheck(mainClass, param.glblCmpyCd.getValue(), serNum)) {
                    setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(NSZM1321E), DS_CONTR_DTL_PK, dsContrDtlTMsg.dsContrDtlPk.getValue());
                    break;
                }
                // END 2018/02/14 K.Kojima [QC#24145,ADD]
                // Mod Start 2017/07/27 QC#20226
                BigDecimal dsContrPrcEffPk = getDsContrPrcEffPk(mainClass, param.glblCmpyCd.getValue(), dsContrDtlTMsg.dsContrDtlPk.getValue(), procDt);
                // Mod End 2017/07/27 QC#20226
                String bllgMtrLbCd = dsContrBllgMtrTMsg.bllgMtrLbCd.getValue();
                String usgTpBillCd = convertMtrLbCd(param.glblCmpyCd.getValue(), bllgMtrLbCd);
                if (!hasValue(usgTpBillCd)) {
                    continue;
                }
                // Mod Start 2017/07/27 QC#20226
                List<Map<String, Object>> usgChrgInfoList = getUsgChrgInfoForCheckCfsUsageTierRateDiff(mainClass, param.glblCmpyCd.getValue(), dsContrDtlTMsg.dsContrDtlPk.getValue(), dsContrBllgMtrTMsg.dsContrBllgMtrPk.getValue(), procDt);
                // Mod End 2017/07/27 QC#20226
                BigDecimal tierCfs = getTierCount(mainClass, param.glblCmpyCd.getValue(), serNum, null, null, ZYPConstant.FLG_ON_Y, usgTpBillCd, null);
                // mod start 2017/04/20 CSA Defect#18383
                if (!hasValue(tierCfs)) {
                    continue;
                }
                if (usgChrgInfoList.size() == 0 && tierCfs.compareTo(BigDecimal.ZERO) == 0) {
                    continue;
                }
                if (usgChrgInfoList.size() == 0 || tierCfs.compareTo(BigDecimal.ZERO) == 0) {
                    setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(msgId, new String[] {serNum }), DS_CONTR_PRC_EFF_PK, dsContrPrcEffPk);
                    break;
                }
                // mod end 2017/04/20 CSA Defect#18383
                if (usgChrgInfoList.size() < tier && tierCfs.intValue() < tier) {
                    continue;
                }
                if (usgChrgInfoList.size() < tier || tierCfs.intValue() < tier) {
                    setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(msgId, new String[] {serNum }), DS_CONTR_PRC_EFF_PK, dsContrPrcEffPk);
                    break;
                }
                BigDecimal xsMtrCopyQty = (BigDecimal) usgChrgInfoList.get(tier - 1).get("XS_MTR_COPY_QTY");
                // Add Start 2017/07/31 QC#18764
                if (DS_CONTR_CATG.AGGREGATE.equals(dsContrTMsg.dsContrCatgCd.getValue())) {
                    if (aggXsMtrCopyQtyMap.containsKey(usgTpBillCd)) {
                        BigDecimal totalXsMtrCopyQty = aggXsMtrCopyQtyMap.get(usgTpBillCd).add(xsMtrCopyQty);
                        aggXsMtrCopyQtyMap.put(usgTpBillCd, totalXsMtrCopyQty);
                    } else {
                        aggXsMtrCopyQtyMap.put(usgTpBillCd, xsMtrCopyQty);
                    }
                    continue;
                }
                // Add End 2017/07/31 QC#18764
                boolean existFlg = false;
                if (tier == TIER_2) {
                    existFlg = existCfsLeaseContrForCheckCfsNonFltUsageTierAllowanceDiff(mainClass, param.glblCmpyCd.getValue(), serNum, usgTpBillCd, null, xsMtrCopyQty, null, null);
                } else if (tier == TIER_3) {
                    existFlg = existCfsLeaseContrForCheckCfsNonFltUsageTierAllowanceDiff(mainClass, param.glblCmpyCd.getValue(), serNum, usgTpBillCd, null, null, xsMtrCopyQty, null);
                } else if (tier == TIER_4) {
                    existFlg = existCfsLeaseContrForCheckCfsNonFltUsageTierAllowanceDiff(mainClass, param.glblCmpyCd.getValue(), serNum, usgTpBillCd, null, null, null, xsMtrCopyQty);
                }
                if (existFlg) {
                    setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(msgId, new String[] {serNum }), DS_CONTR_PRC_EFF_PK, dsContrPrcEffPk);
                    break;
                }
                // END 2016/06/30 K.Yamada [QC#3051, MOD]
            }
            // Add Start 2017/07/31 QC#18764
            if (DS_CONTR_CATG.AGGREGATE.equals(dsContrTMsg.dsContrCatgCd.getValue())) {
                continue;
            }
            // Add End 2017/07/31 QC#18764
            // START 2017/08/08 K.Kitachi [QC#20531, MOD]
            if (isChecked) {
                EZDMsg.copy(rtrnTMsg, null, rtrnTMsgArray.no(arrayIdx++), null);
                rtrnTMsgArray.setValidCount(rtrnTMsgArray.getValidCount() + 1);
            }
            // END 2017/08/08 K.Kitachi [QC#20531, MOD]
        }
        // Add Start 2017/07/31 QC#18764
        if (!DS_CONTR_CATG.AGGREGATE.equals(dsContrTMsg.dsContrCatgCd.getValue()) || dsContrDtlMap.isEmpty()) {
            return rtrnTMsgArray;
        }

        if (aggXsMtrCopyQtyMap.isEmpty()) {
            // START 2017/08/08 K.Kitachi [QC#20531, DEL]
//            for (Map.Entry<BigDecimal, String> dsContrDtl : dsContrDtlMap.entrySet()) {
//                rtrnTMsg = new DS_CONTR_VLD_RSLT_WRKTMsg();
//                setValue(rtrnTMsg.dsContrPk, dsContrTMsg.dsContrPk);
//                setValue(rtrnTMsg.dsContrDtlPk, dsContrDtl.getKey());
//                setValue(rtrnTMsg.dsContrVldStsCd, DS_CONTR_VLD_STS.SUCCESS);
//                EZDMsg.copy(rtrnTMsg, null, rtrnTMsgArray.no(rtrnTMsgArray.getValidCount()), null);
//                rtrnTMsgArray.setValidCount(rtrnTMsgArray.getValidCount() + 1);
//            }
            // END 2017/08/08 K.Kitachi [QC#20531, DEL]
            return rtrnTMsgArray;
        }

        BigDecimal dsContrDtlPk;
        String serNum;
        for (Map.Entry<BigDecimal, String> dsContrDtl : dsContrDtlMap.entrySet()) {
            dsContrDtlPk = dsContrDtl.getKey();
            serNum = dsContrDtl.getValue();

            rtrnTMsg = new DS_CONTR_VLD_RSLT_WRKTMsg();
            setValue(rtrnTMsg.dsContrPk, dsContrTMsg.dsContrPk);
            setValue(rtrnTMsg.dsContrDtlPk, dsContrDtl.getKey());
            // START 2017/08/08 K.Kitachi [QC#20531, MOD]
            setValue(rtrnTMsg.dsContrVldStsCd, DS_CONTR_VLD_STS.SUCCESS);
            // END 2017/08/08 K.Kitachi [QC#20531, MOD]
            String aggUsgTpBillCd = null;
            BigDecimal aggXsMtrCopyQty = BigDecimal.ZERO;
            Map<String, BigDecimal> aggXsMtrCopyQtymap;
            BigDecimal cfsXsMtrCopyQty = BigDecimal.ZERO;
            for (Map.Entry<String, BigDecimal> xsMtrCopy : aggXsMtrCopyQtyMap.entrySet()) {
                // START 2018/02/14 K.Kojima [QC#24145,ADD]
                if (!existCfsLeaseContrForBeforeCheck(mainClass, param.glblCmpyCd.getValue(), serNum)) {
                    setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(NSZM1321E), DS_CONTR_DTL_PK, dsContrDtlPk);
                    break;
                }
                // END 2018/02/14 K.Kojima [QC#24145,ADD]
                aggUsgTpBillCd = xsMtrCopy.getKey();
                aggXsMtrCopyQty = xsMtrCopy.getValue();

                aggXsMtrCopyQtymap = getAggXsMtrCopyQty(mainClass, param.glblCmpyCd.getValue(), serNum, aggUsgTpBillCd);
                if (aggXsMtrCopyQtymap == null || aggXsMtrCopyQtymap.isEmpty()) {
                    continue;
                }

                cfsXsMtrCopyQty = aggXsMtrCopyQtymap.get(getCfsXsMtrCopyQtyColNmForTier(tier));
                if (!hasValue(cfsXsMtrCopyQty) || cfsXsMtrCopyQty.compareTo(BigDecimal.ZERO) == 0) {
                    continue;
                }

                if (cfsXsMtrCopyQty.compareTo(aggXsMtrCopyQty) != 0) {
                    setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(NSZM0990E, new String[] {serNum }), DS_CONTR_DTL_PK, dsContrDtlPk);
                    break;
                }
            }
            EZDMsg.copy(rtrnTMsg, null, rtrnTMsgArray.no(rtrnTMsgArray.getValidCount()), null);
            rtrnTMsgArray.setValidCount(rtrnTMsgArray.getValidCount() + 1);
        }
        // Add End 2017/07/31 QC#18764
        return rtrnTMsgArray;
    }

    private static DS_CONTR_VLD_RSLT_WRKTMsgArray checkCfsFltUsageTierRateDiff(NSZC057001 mainClass, NSZC057001PMsg param, String msgId, int tier) {

        DS_CONTR_VLD_RSLT_WRKTMsgArray rtrnTMsgArray = new DS_CONTR_VLD_RSLT_WRKTMsgArray();
        rtrnTMsgArray.setMsgList(new DS_CONTR_VLD_RSLT_WRKTMsg[MAX_SIZE]);
        int arrayIdx = 0;

        DS_CONTR_VLD_RSLT_WRKTMsg rtrnTMsg = null;

        DS_CONTRTMsg dsContrTMsg = mainClass.dsContrCache.get(KEY);
        // START 2017/06/22 K.Kitachi [QC#19340, MOD]
        // START 2018/02/02 U.Kim [QC#23687,MOD]
        // if (!isLeaseCompany(dsContrTMsg)) {
        if (!isLeaseCompany(mainClass, dsContrTMsg)) {
        // END 2018/02/02 U.Kim [QC#23687,MOD]
        // END 2017/06/22 K.Kitachi [QC#19340, MOD]
            return rtrnTMsgArray;
        }

        // get machine & contract info
        Map<String, Object> machAndContrInfo = getMachAndContrInfoForFleetLine(mainClass, param.glblCmpyCd.getValue(), dsContrTMsg.dsContrPk.getValue());
        if (machAndContrInfo == null) {
            return rtrnTMsgArray;
        }
        BigDecimal dsContrDtlPk = (BigDecimal) machAndContrInfo.get("DS_CONTR_DTL_PK");
        // Add Start 2017/07/27 QC#20226
        DS_CONTR_DTLTMsg dsContrDtlTMsg = getDsContrDtlForPk(param.glblCmpyCd.getValue(), dsContrDtlPk);
        String procDt = getProcDt(param.slsDt.getValue(), dsContrDtlTMsg);
        // Add End 2017/07/27 QC#20226

        rtrnTMsg = new DS_CONTR_VLD_RSLT_WRKTMsg();
        setValue(rtrnTMsg.dsContrPk, dsContrTMsg.dsContrPk);
        setValue(rtrnTMsg.dsContrDtlPk, dsContrDtlPk);
        // START 2017/08/08 K.Kitachi [QC#20531, MOD]
        setValue(rtrnTMsg.dsContrVldStsCd, DS_CONTR_VLD_STS.SUCCESS);
        // END 2017/08/08 K.Kitachi [QC#20531, MOD]

        DS_CONTR_BLLG_MTRTMsgArray dsContrBllgMtrTMsgArray = mainClass.dsContrBllgMtrArrayCache.get(KEY + dsContrDtlPk);
        if (dsContrBllgMtrTMsgArray == null || dsContrBllgMtrTMsgArray.getValidCount() == 0) {
            dsContrBllgMtrTMsgArray = getDsContrBllgMtr(param.glblCmpyCd.getValue(), dsContrDtlPk);
            if (dsContrBllgMtrTMsgArray != null && dsContrBllgMtrTMsgArray.getValidCount() > 0) {
                mainClass.dsContrBllgMtrArrayCache.put(KEY + dsContrDtlPk, dsContrBllgMtrTMsgArray);
            }
        }
        if (dsContrBllgMtrTMsgArray == null || dsContrBllgMtrTMsgArray.getValidCount() == 0) {
            return rtrnTMsgArray;
        }

        // START 2017/08/08 K.Kitachi [QC#20531, ADD]
        boolean isChecked = false;
        // END 2017/08/08 K.Kitachi [QC#20531, ADD]
        for (int j = 0; j < dsContrBllgMtrTMsgArray.getValidCount(); j++) {
            // START 2016/06/30 K.Yamada [QC#3051, MOD]
            DS_CONTR_BLLG_MTRTMsg dsContrBllgMtrTMsg = dsContrBllgMtrTMsgArray.no(j);
            // START 2017/08/03 K.Kitachi [QC#20224, ADD]
            // START 2018/02/02 U.Kim [QC#23687, ADD]
            // if (!NSXC001001ContrValidation.isLeaseCompany(param.glblCmpyCd.getValue(), dsContrBllgMtrTMsg.bllgMtrBillToCustCd.getValue())) {
            if (!isLeaseCompanyForBllgMtr(mainClass, param.glblCmpyCd.getValue(), dsContrBllgMtrTMsg.bllgMtrBillToCustCd.getValue())) {
                continue;
            }
            // END 2018/02/02 U.Kim [QC#23687, ADD]
            // END 2017/08/03 K.Kitachi [QC#20224, ADD]
            // START 2017/08/08 K.Kitachi [QC#20531, ADD]
            isChecked = true;
            // END 2017/08/08 K.Kitachi [QC#20531, ADD]
            // START 2018/02/14 K.Kojima [QC#24145,ADD]
            if (!existCfsLeaseContrForBeforeCheck(mainClass, dsContrTMsg)) {
                setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(NSZM1321E), DS_CONTR_DTL_PK, dsContrDtlPk);
                break;
            }
            // END 2018/02/14 K.Kojima [QC#24145,ADD]
            // Mod Start 2017/07/27 QC#20226
            BigDecimal dsContrPrcEffPk = getDsContrPrcEffPk(mainClass, param.glblCmpyCd.getValue(), dsContrBllgMtrTMsg.dsContrDtlPk.getValue(), procDt);
            // Mod End 2017/07/27 QC#20226
            String bllgMtrLbCd = dsContrBllgMtrTMsg.bllgMtrLbCd.getValue();
            String usgTpBillCd = convertMtrLbCd(param.glblCmpyCd.getValue(), bllgMtrLbCd);
            if (!hasValue(usgTpBillCd)) {
                continue;
            }
            // Mod Start 2017/07/27 QC#20226
            List<Map<String, Object>> usgChrgInfoList = getUsgChrgInfoForCheckCfsUsageTierRateDiff(mainClass, param.glblCmpyCd.getValue(), dsContrDtlPk, dsContrBllgMtrTMsg.dsContrBllgMtrPk.getValue(), procDt);
            // Mod End 2017/07/27 QC#20226
            BigDecimal tierCfs = getTierCount(mainClass, param.glblCmpyCd.getValue(), null, null, null, ZYPConstant.FLG_ON_Y, usgTpBillCd, dsContrTMsg.dsContrPk.getValue());
            // mod start 2017/04/20 CSA Defect#18383
            if (!hasValue(tierCfs)) {
                continue;
            }
            if (usgChrgInfoList.size() == 0 && tierCfs.compareTo(BigDecimal.ZERO) == 0) {
                continue;
            }
            if (usgChrgInfoList.size() == 0 || tierCfs.compareTo(BigDecimal.ZERO) == 0) {
                setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(msgId, new String[] {dsContrTMsg.dsContrNum.getValue() }), DS_CONTR_PRC_EFF_PK, dsContrPrcEffPk);
                break;
            }
            // mod end 2017/04/20 CSA Defect#18383
            if (usgChrgInfoList.size() < tier && tierCfs.intValue() < tier) {
                continue;
            }
            if (usgChrgInfoList.size() < tier || tierCfs.intValue() < tier) {
                setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(msgId, new String[] {dsContrTMsg.dsContrNum.getValue() }), DS_CONTR_PRC_EFF_PK, dsContrPrcEffPk);
                break;
            }
            BigDecimal xsMtrAmtRate = (BigDecimal) usgChrgInfoList.get(tier - 1).get("XS_MTR_AMT_RATE");
            boolean existFlg = false;
            if (tier == TIER_2) {
                existFlg = existCfsLeaseContrForCheckCfsFltUsageTierRateDiff(mainClass, param.glblCmpyCd.getValue(), dsContrTMsg.dsContrPk.getValue(), usgTpBillCd, null, xsMtrAmtRate, null, null);
            } else if (tier == TIER_3) {
                existFlg = existCfsLeaseContrForCheckCfsFltUsageTierRateDiff(mainClass, param.glblCmpyCd.getValue(), dsContrTMsg.dsContrPk.getValue(), usgTpBillCd, null, null, xsMtrAmtRate, null);
            } else if (tier == TIER_4) {
                existFlg = existCfsLeaseContrForCheckCfsFltUsageTierRateDiff(mainClass, param.glblCmpyCd.getValue(), dsContrTMsg.dsContrPk.getValue(), usgTpBillCd, null, null, null, xsMtrAmtRate);
            }
            if (existFlg) {
                setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(msgId, new String[] {dsContrTMsg.dsContrNum.getValue() }), DS_CONTR_PRC_EFF_PK, dsContrPrcEffPk);
                break;
            }
            // END 2016/06/30 K.Yamada [QC#3051, MOD]
        }

        // START 2017/08/08 K.Kitachi [QC#20531, MOD]
        if (isChecked) {
            EZDMsg.copy(rtrnTMsg, null, rtrnTMsgArray.no(arrayIdx++), null);
            rtrnTMsgArray.setValidCount(rtrnTMsgArray.getValidCount() + 1);
        }
        // END 2017/08/08 K.Kitachi [QC#20531, MOD]

        return rtrnTMsgArray;
    }

    private static DS_CONTR_VLD_RSLT_WRKTMsgArray checkCfsFltUsageTierAllowanceDiff(NSZC057001 mainClass, NSZC057001PMsg param, String msgId, int tier) {

        DS_CONTR_VLD_RSLT_WRKTMsgArray rtrnTMsgArray = new DS_CONTR_VLD_RSLT_WRKTMsgArray();
        rtrnTMsgArray.setMsgList(new DS_CONTR_VLD_RSLT_WRKTMsg[MAX_SIZE]);
        int arrayIdx = 0;

        DS_CONTR_VLD_RSLT_WRKTMsg rtrnTMsg = null;

        DS_CONTRTMsg dsContrTMsg = mainClass.dsContrCache.get(KEY);
        // START 2017/06/22 K.Kitachi [QC#19340, MOD]
        // START 2018/02/02 U.Kim [QC#23687,MOD]
        // if (!isLeaseCompany(dsContrTMsg)) {
        if (!isLeaseCompany(mainClass, dsContrTMsg)) {
        // END 2018/02/02 U.Kim [QC#23687,MOD]
        // END 2017/06/22 K.Kitachi [QC#19340, MOD]
            return rtrnTMsgArray;
        }

        // get machine & contract info
        Map<String, Object> machAndContrInfo = getMachAndContrInfoForFleetLine(mainClass, param.glblCmpyCd.getValue(), dsContrTMsg.dsContrPk.getValue());
        if (machAndContrInfo == null) {
            return rtrnTMsgArray;
        }
        BigDecimal dsContrDtlPk = (BigDecimal) machAndContrInfo.get("DS_CONTR_DTL_PK");
        // Add Start 2017/07/27 QC#20226
        DS_CONTR_DTLTMsg dsContrDtlTMsg = getDsContrDtlForPk(param.glblCmpyCd.getValue(), dsContrDtlPk);
        String procDt = getProcDt(param.slsDt.getValue(), dsContrDtlTMsg);
        // Add End 2017/07/27 QC#20226

        rtrnTMsg = new DS_CONTR_VLD_RSLT_WRKTMsg();
        setValue(rtrnTMsg.dsContrPk, dsContrTMsg.dsContrPk);
        setValue(rtrnTMsg.dsContrDtlPk, dsContrDtlPk);
        // START 2017/08/08 K.Kitachi [QC#20531, MOD]
        setValue(rtrnTMsg.dsContrVldStsCd, DS_CONTR_VLD_STS.SUCCESS);
        // END 2017/08/08 K.Kitachi [QC#20531, MOD]

        DS_CONTR_BLLG_MTRTMsgArray dsContrBllgMtrTMsgArray = mainClass.dsContrBllgMtrArrayCache.get(KEY + dsContrDtlPk);
        if (dsContrBllgMtrTMsgArray == null || dsContrBllgMtrTMsgArray.getValidCount() == 0) {
            dsContrBllgMtrTMsgArray = getDsContrBllgMtr(param.glblCmpyCd.getValue(), dsContrDtlPk);
            if (dsContrBllgMtrTMsgArray != null && dsContrBllgMtrTMsgArray.getValidCount() > 0) {
                mainClass.dsContrBllgMtrArrayCache.put(KEY + dsContrDtlPk, dsContrBllgMtrTMsgArray);
            }
        }
        if (dsContrBllgMtrTMsgArray == null || dsContrBllgMtrTMsgArray.getValidCount() == 0) {
            return rtrnTMsgArray;
        }

        // START 2017/08/08 K.Kitachi [QC#20531, ADD]
        boolean isChecked = false;
        // END 2017/08/08 K.Kitachi [QC#20531, ADD]
        for (int j = 0; j < dsContrBllgMtrTMsgArray.getValidCount(); j++) {
            // START 2016/06/30 K.Yamada [QC#3051, MOD]
            DS_CONTR_BLLG_MTRTMsg dsContrBllgMtrTMsg = dsContrBllgMtrTMsgArray.no(j);
            // START 2017/08/03 K.Kitachi [QC#20224, ADD]
            // START 2018/02/02 U.Kim [QC#23687, ADD]
            // if (!NSXC001001ContrValidation.isLeaseCompany(param.glblCmpyCd.getValue(), dsContrBllgMtrTMsg.bllgMtrBillToCustCd.getValue())) {
            if (!isLeaseCompanyForBllgMtr(mainClass, param.glblCmpyCd.getValue(), dsContrBllgMtrTMsg.bllgMtrBillToCustCd.getValue())) {
                continue;
            }
            // END 2018/02/02 U.Kim [QC#23687, ADD]
            // END 2017/08/03 K.Kitachi [QC#20224, ADD]
            // START 2017/08/08 K.Kitachi [QC#20531, ADD]
            isChecked = true;
            // END 2017/08/08 K.Kitachi [QC#20531, ADD]
            // START 2018/02/14 K.Kojima [QC#24145,ADD]
            if (!existCfsLeaseContrForBeforeCheck(mainClass, dsContrTMsg)) {
                setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(NSZM1321E), DS_CONTR_DTL_PK, dsContrDtlPk);
                break;
            }
            // END 2018/02/14 K.Kojima [QC#24145,ADD]
            // Mod Start 2017/07/27 QC#20226
            BigDecimal dsContrPrcEffPk = getDsContrPrcEffPk(mainClass, param.glblCmpyCd.getValue(), dsContrBllgMtrTMsg.dsContrDtlPk.getValue(), procDt);
            // Mod End 2017/07/27 QC#20226
            String bllgMtrLbCd = dsContrBllgMtrTMsg.bllgMtrLbCd.getValue();
            String usgTpBillCd = convertMtrLbCd(param.glblCmpyCd.getValue(), bllgMtrLbCd);
            if (!hasValue(usgTpBillCd)) {
                continue;
            }
            // Mod Start 2017/07/27 QC#20226
            List<Map<String, Object>> usgChrgInfoList = getUsgChrgInfoForCheckCfsUsageTierRateDiff(mainClass, param.glblCmpyCd.getValue(), dsContrDtlPk, dsContrBllgMtrTMsg.dsContrBllgMtrPk.getValue(), procDt);
            // Mod End 2017/07/27 QC#20226
            BigDecimal tierCfs = getTierCount(mainClass, param.glblCmpyCd.getValue(), null, null, null, ZYPConstant.FLG_ON_Y, usgTpBillCd, dsContrTMsg.dsContrPk.getValue());
            // mod start 2017/04/20 CSA Defect#18383
            if (!hasValue(tierCfs)) {
                continue;
            }
            if (usgChrgInfoList.size() == 0 && tierCfs.compareTo(BigDecimal.ZERO) == 0) {
                continue;
            }
            if (usgChrgInfoList.size() == 0 || tierCfs.compareTo(BigDecimal.ZERO) == 0) {
                setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(msgId, new String[] {dsContrTMsg.dsContrNum.getValue() }), DS_CONTR_PRC_EFF_PK, dsContrPrcEffPk);
                break;
            }
            // mod end 2017/04/20 CSA Defect#18383
            if (usgChrgInfoList.size() < tier && tierCfs.intValue() < tier) {
                continue;
            }
            if (usgChrgInfoList.size() < tier || tierCfs.intValue() < tier) {
                setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(msgId, new String[] {dsContrTMsg.dsContrNum.getValue() }), DS_CONTR_PRC_EFF_PK, dsContrPrcEffPk);
                break;
            }
            BigDecimal xsMtrCopyQty = (BigDecimal) usgChrgInfoList.get(tier - 1).get("XS_MTR_COPY_QTY");
            boolean existFlg = false;
            if (tier == TIER_2) {
                existFlg = existCfsLeaseContrForCheckCfsFltUsageTierAllowanceDiff(mainClass, param.glblCmpyCd.getValue(), dsContrTMsg.dsContrPk.getValue(), usgTpBillCd, null, xsMtrCopyQty, null, null);
            } else if (tier == TIER_3) {
                existFlg = existCfsLeaseContrForCheckCfsFltUsageTierAllowanceDiff(mainClass, param.glblCmpyCd.getValue(), dsContrTMsg.dsContrPk.getValue(), usgTpBillCd, null, null, xsMtrCopyQty, null);
            } else if (tier == TIER_4) {
                existFlg = existCfsLeaseContrForCheckCfsFltUsageTierAllowanceDiff(mainClass, param.glblCmpyCd.getValue(), dsContrTMsg.dsContrPk.getValue(), usgTpBillCd, null, null, null, xsMtrCopyQty);
            }
            if (existFlg) {
                setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(msgId, new String[] {dsContrTMsg.dsContrNum.getValue() }), DS_CONTR_PRC_EFF_PK, dsContrPrcEffPk);
                break;
            }
            // END 2016/06/30 K.Yamada [QC#3051, MOD]
        }

        // START 2017/08/08 K.Kitachi [QC#20531, MOD]
        if (isChecked) {
            EZDMsg.copy(rtrnTMsg, null, rtrnTMsgArray.no(arrayIdx++), null);
            rtrnTMsgArray.setValidCount(rtrnTMsgArray.getValidCount() + 1);
        }
        // END 2017/08/08 K.Kitachi [QC#20531, MOD]

        return rtrnTMsgArray;
    }

    // START 2017/06/22 K.Kitachi [QC#19340, MOD]
    private static String getLeaseDealerCode(String glblCmpyCd, String billToCustCd){
        BILL_TO_CUSTTMsg billToCustTMsg = new BILL_TO_CUSTTMsg();
        billToCustTMsg.setSQLID("019");
        billToCustTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        billToCustTMsg.setConditionValue("billToCustCd01", billToCustCd);
        BILL_TO_CUSTTMsgArray billToCustTMsgArray = (BILL_TO_CUSTTMsgArray) S21ApiTBLAccessor.findByCondition(billToCustTMsg);
        if (billToCustTMsgArray.getValidCount() == 0) {
            return null;
        }
        String sellToCustCd = billToCustTMsgArray.no(0).sellToCustCd.getValue();
        SELL_TO_CUSTTMsg sellToCustTMsg = new SELL_TO_CUSTTMsg();
        sellToCustTMsg.setSQLID("003");
        sellToCustTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        sellToCustTMsg.setConditionValue("sellToCustCd01", sellToCustCd);
        SELL_TO_CUSTTMsgArray sellToCustTMsgArray = (SELL_TO_CUSTTMsgArray) S21ApiTBLAccessor.findByCondition(sellToCustTMsg);
        if (sellToCustTMsgArray.getValidCount() == 0) {
            return null;
        }
        return sellToCustTMsgArray.no(0).dsAcctDlrCd.getValue();
    }
    // END 2017/06/22 K.Kitachi [QC#19340, MOD]

    // Add Start 2017/07/31 QC#18764
    private static String getCfsXsMtrCopyQtyColNmForTier(int tier) {
        // Mod Start 2017/08/16 QC#187611
        String rtnVal = "FIRST_XS_MTR_COPY_DPLY_QTY";
        switch (tier) {
            case TIER_2:
                rtnVal = "SCD_XS_MTR_COPY_DPLY_QTY";
                break;
            case TIER_3:
                rtnVal = "THIRD_XS_MTR_COPY_DPLY_QTY";
                break;
            case TIER_4:
                rtnVal = "FRTH_XS_MTR_COPY_DPLY_QTY";
                break;
            default:
                break;
        }
        // Mod End 2017/08/16 QC#187611
        return rtnVal;
    }
    // Add End 2017/07/31 QC#18764
}
