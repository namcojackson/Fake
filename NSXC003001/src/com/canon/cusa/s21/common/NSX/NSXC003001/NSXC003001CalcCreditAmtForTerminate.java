/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.common.NSX.NSXC003001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;

import business.db.BLLG_CYCLETMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MAN_TRMN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_INV_CHRG_TP;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SKIP_RECOV_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/29/2017   Hitachi         A.Kohinata      Create          QC#18349
 * 12/04/2017   Hitachi         K.Kishimoto     Update          QC#22880
 * 2018/05/22   Hitachi         K.Kojima        Update          QC#23302
 * 2018/07/02   Hitachi         K.Kojima        Update          QC#23063
 * 2019/12/17   Hitachi         K.Kishimoto     Update          QC#54974
 * 2022/02/04   Hitachi         K.Kitachi       Update          QC#59684
 * 2022/04/26   CITS            T.Suzuki        Update          QC#59979
 * 2022/05/16   CITS            T.Suzuki        Update          QC#60038
 * 2022/06/02   CITS            T.Suzuki        Update          QC#60038
 * 2022/07/07   Hitachi         A.Kohinata      Update          QC#60167
 * 2022/07/26   CITS            T.Suzuki        Update          QC#60038-1
 *</pre>
 */
public class NSXC003001CalcCreditAmtForTerminate implements ZYPConstant {

    /** SSM batch client */
    private static S21SsmBatchClient ssmBatchClient = S21SsmBatchClient.getClient(NSXC003001CalcCreditAmtForTerminate.class);

    /** DAYS_OF_YEAR */
    private static final BigDecimal DAYS_OF_YEAR = new BigDecimal(365);

    /**
     * @param inBean CalcCreditAmtForTerminateBean
     * @return CalcCreditAmtForTerminateBean
     */
    public static CalcCreditAmtForTerminateBean calcCreditAmtForTerminate(CalcCreditAmtForTerminateBean inBean) {

        String glblCmpyCd = inBean.getGlblCmpyCd();
        BigDecimal dsContrDtlPk = inBean.getDsContrDtlPk();
        String trmnDt = inBean.getTrmnDt();
        inBean.setCreditAmt(BigDecimal.ZERO);

        Map<String, Object> bllgSchdMap = getBllgSchdInclTrmnDt(glblCmpyCd, dsContrDtlPk, trmnDt);
        if (bllgSchdMap == null) {
            return inBean;
        }

        String bllgSchdFromDt = (String) bllgSchdMap.get("BLLG_SCHD_FROM_DT");
        String bllgSchdThruDt = (String) bllgSchdMap.get("BLLG_SCHD_THRU_DT");
        String invFlg = (String) bllgSchdMap.get("INV_FLG");
        String svcInvNum = (String) bllgSchdMap.get("SVC_INV_NUM");
        String bllgCycleCd = (String) bllgSchdMap.get("BLLG_CYCLE_CD");
        // ADD START 2022/04/26 QC#59979
        String skipRecovTpCd = (String) bllgSchdMap.get("SKIP_RECOV_TP_CD");
        String prntSkipRecovTpCd = "";
        // ADD END   2022/04/26 QC#59979
        BigDecimal basePrcDealAmt = (BigDecimal) bllgSchdMap.get("BASE_PRC_DEAL_AMT");
        BigDecimal baseActlPrcDealAmt = (BigDecimal) bllgSchdMap.get("BASE_ACTL_PRC_DEAL_AMT");

        if (ZYPConstant.FLG_OFF_N.equals(invFlg)) {
            // MOD START 2022/06/01 QC#60038
            // MOD START 2022/04/26 QC#59979
            /*if (hasValue(skipRecovTpCd) && !skipRecovTpCd.equals(SKIP_RECOV_TP.SKIP)) {
                Map<String, Object> dsContrDtlTpMap = getDsContrDtlTpCd(glblCmpyCd, dsContrDtlPk);
                String dsContrDtlTpCd = (String) dsContrDtlTpMap.get("DS_CONTR_DTL_TP_CD");
                BigDecimal prntDsContrDtlPk = (BigDecimal) dsContrDtlTpMap.get("PRNT_DS_CONTR_DTL_PK");

                if (hasValue(dsContrDtlTpCd) && DS_CONTR_DTL_TP.ACCESSORIES.equals(dsContrDtlTpCd) && hasValue(prntDsContrDtlPk)) {
                    Map<String, Object> prntBllgSchdMap = getBllgSchdInclTrmnDt(glblCmpyCd, prntDsContrDtlPk, trmnDt);
                    prntSkipRecovTpCd = (String) prntBllgSchdMap.get("SKIP_RECOV_TP_CD");
                } else {
                    return inBean;
                }

                if (hasValue(prntSkipRecovTpCd) && !prntSkipRecovTpCd.equals(SKIP_RECOV_TP.SKIP)) {
                    return inBean;
                }
            }*/
            // MOD END   2022/04/26 QC#59979
            String nextSchdThruDt = getInvedMaxBllgSchdThruDtForBase(glblCmpyCd, dsContrDtlPk, bllgSchdThruDt);
            if (!hasValue(nextSchdThruDt)) {
                return inBean;
            }
            // MOD END   2022/06/01 QC#60038
        }

        // MOD START 2022/05/16 QC#60038
        // DEL START 2022/07/26 QC#60038-1
//        String billWithEquipFlg = getBillWithEquipFlg(glblCmpyCd, dsContrDtlPk);
        // DEL END   2022/07/26 QC#60038-1
        // MOD END   2022/05/16 QC#60038

        // Bill With Equipment
        // MOD START 2022/05/16 QC#60038
        // DEL START 2022/07/26 QC#60038-1
        /*
        if (ZYPConstant.FLG_ON_Y.equals(invFlg) && ZYPConstant.FLG_ON_Y.equals(billWithEquipFlg) && !hasValue(svcInvNum)) {
//            return inBean;
            // MOD START 2022/05/18 QC#60038 [CCI-QC#2537, MOD]
            String nextDt = ZYPDateUtil.addDays(trmnDt, 1);
            bllgSchdMap = getBllgSchdInclTrmnDt(glblCmpyCd, dsContrDtlPk, nextDt);
            if (bllgSchdMap == null) {
                return inBean;
            }
            bllgSchdFromDt = (String) bllgSchdMap.get("BLLG_SCHD_FROM_DT");
            bllgSchdThruDt = (String) bllgSchdMap.get("BLLG_SCHD_THRU_DT");
            invFlg = (String) bllgSchdMap.get("INV_FLG");
            svcInvNum = (String) bllgSchdMap.get("SVC_INV_NUM");
            baseActlPrcDealAmt = (BigDecimal) bllgSchdMap.get("BASE_ACTL_PRC_DEAL_AMT");
            if (ZYPConstant.FLG_ON_Y.equals(invFlg) && !hasValue(svcInvNum)) {
                return inBean;
            }
            // MOD END  2022/05/18 QC#60038 [CCI-QC#2537, MOD]
        } */
        // DEL END   2022/05/16 QC#60038-1

        BigDecimal creditAmt = BigDecimal.ZERO;

        if (ZYPDateUtil.compare(trmnDt, bllgSchdThruDt) < 0 && BigDecimal.ZERO.compareTo(baseActlPrcDealAmt) < 0) {
            // MOD START 2022/04/26 QC#59979
            // MOD START 2022/07/26 QC#60038-1
            //if (isDailyCalcuration(skipRecovTpCd, prntSkipRecovTpCd)) {
            if (ZYPConstant.FLG_ON_Y.equals(invFlg) && hasValue(svcInvNum)) {
            // MOD END   2022/07/26 QC#60038-1
                // START 2018/07/02 K.Kojima [QC#23063,MOD]
                // BigDecimal diffDays = new BigDecimal(getDiffDays(trmnDt, bllgSchdFromDt));
                // int digitNum = getDigitNum(glblCmpyCd, dsContrDtlPk);
                // 
                // BLLG_CYCLETMsg bllgCycleTMsg = getBllgCycle(inBean.getGlblCmpyCd(), bllgCycleCd);
                // BigDecimal prrtDivRate = bllgCycleTMsg.prrtDivRate.getValue();
                // BigDecimal divRate = DAYS_OF_YEAR.divide(prrtDivRate, digitNum, BigDecimal.ROUND_HALF_UP);
                // 
                // BigDecimal newBaseActlPrcDealAmt = basePrcDealAmt.multiply(diffDays).divide(divRate, digitNum, BigDecimal.ROUND_HALF_UP);
                // 
                // creditAmt = baseActlPrcDealAmt.subtract(newBaseActlPrcDealAmt);
                // if (BigDecimal.ZERO.compareTo(creditAmt) > 0) {
                //     creditAmt = BigDecimal.ZERO;
                // }
                String creditStartDate = ZYPDateUtil.addDays(trmnDt, 1);
                // START 2022/02/04 K.Kitachi [QC#59684, ADD]
                String manTrmnTpCd = inBean.getManTrmnTpCd();
                if (MAN_TRMN_TP.ALL_PERIOD.equals(manTrmnTpCd)) {
                    creditStartDate = trmnDt;
                }
                // END 2022/02/04 K.Kitachi [QC#59684, ADD]
                BigDecimal diffDaysOld = new BigDecimal(getDiffDays(bllgSchdThruDt, bllgSchdFromDt));
                BigDecimal diffDaysNew = new BigDecimal(getDiffDays(bllgSchdThruDt, creditStartDate));
                int digitNum = getDigitNum(glblCmpyCd, dsContrDtlPk);
    
                creditAmt = baseActlPrcDealAmt.multiply(diffDaysNew).divide(diffDaysOld, digitNum, BigDecimal.ROUND_HALF_UP);
                // END 2018/07/02 K.Kojima [QC#23063,MOD]
            }
            // MOD END 2022/04/26 QC#59979
        }

        BigDecimal billedAmt = getBilledAmtAfterTrmnDt(glblCmpyCd, dsContrDtlPk, bllgSchdThruDt);
        if (BigDecimal.ZERO.compareTo(billedAmt) < 0) {
            creditAmt = creditAmt.add(billedAmt);
        }

        inBean.setCreditAmt(creditAmt);
        return inBean;
    }

    // START2019/12/17 [QC#54974, Add]
    /**
     * @param inBean CalcCreditAmtForTerminateBean
     * @return CalcCreditAmtForTerminateBean
     */
    public static CalcCreditAmtForTerminateBean calcCreditAmtForTerminate1Period(CalcCreditAmtForTerminateBean inBean) {

        String glblCmpyCd = inBean.getGlblCmpyCd();
        BigDecimal dsContrDtlPk = inBean.getDsContrDtlPk();
        String trmnDt = inBean.getTrmnDt();
        inBean.setCreditAmt(BigDecimal.ZERO);

        Map<String, Object> bllgSchdMap = getBllgSchdInclTrmnDt(glblCmpyCd, dsContrDtlPk, trmnDt);
        if (bllgSchdMap == null) {
            return inBean;
        }

        String bllgSchdFromDt = (String) bllgSchdMap.get("BLLG_SCHD_FROM_DT");
        String bllgSchdThruDt = (String) bllgSchdMap.get("BLLG_SCHD_THRU_DT");
        String invFlg = (String) bllgSchdMap.get("INV_FLG");
        String svcInvNum = (String) bllgSchdMap.get("SVC_INV_NUM");
        BigDecimal baseActlPrcDealAmt = (BigDecimal) bllgSchdMap.get("BASE_ACTL_PRC_DEAL_AMT");

        if (ZYPConstant.FLG_OFF_N.equals(invFlg)) {
            return inBean;
        }

        // Bill With Equipment
        if (ZYPConstant.FLG_ON_Y.equals(invFlg) && !hasValue(svcInvNum)) {
            return inBean;
        }

        BigDecimal creditAmt = BigDecimal.ZERO;

        if (ZYPDateUtil.compare(trmnDt, bllgSchdThruDt) < 0 && BigDecimal.ZERO.compareTo(baseActlPrcDealAmt) < 0) {
            String creditStartDate = ZYPDateUtil.addDays(trmnDt, 1);
            BigDecimal diffDaysOld = new BigDecimal(getDiffDays(bllgSchdThruDt, bllgSchdFromDt));
            BigDecimal diffDaysNew = new BigDecimal(getDiffDays(bllgSchdThruDt, creditStartDate));
            int digitNum = getDigitNum(glblCmpyCd, dsContrDtlPk);

            creditAmt = baseActlPrcDealAmt.multiply(diffDaysNew).divide(diffDaysOld, digitNum, BigDecimal.ROUND_HALF_UP);
        }

        inBean.setCreditAmt(creditAmt);
        return inBean;
    }
    // END  2019/12/17 [QC#54974, Add]

    private static Map<String, Object> getBllgSchdInclTrmnDt(String glblCmpyCd, BigDecimal dsContrDtlPk, String trmnDt) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        ssmParam.put("trmnDt", trmnDt);
        return (Map<String, Object>) ssmBatchClient.queryObject("getBllgSchdInclTrmnDt", ssmParam);
    }

    private static BigDecimal getBilledAmtAfterTrmnDt(String glblCmpyCd, BigDecimal dsContrDtlPk, String bllgSchdThruDt) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        ssmParam.put("bllgSchdThruDt", bllgSchdThruDt);
        //Add Start 2017/12/04 <QC#22880>
        ssmParam.put("invoice", INV_TP.INVOICE);
        //Add End   2017/12/04 <QC#22880>
        return (BigDecimal) ssmBatchClient.queryObject("getBilledAmtAfterTrmnDt", ssmParam);
    }

    private static int getDigitNum(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        BigDecimal digitNum = (BigDecimal) ssmBatchClient.queryObject("getDigitNum", ssmParam);
        if (!hasValue(digitNum)) {
            return 2;
        }
        return digitNum.intValue();
    }

    private static BLLG_CYCLETMsg getBllgCycle(String glblCmpyCd, String bllgCycleCd) {
        BLLG_CYCLETMsg inMsg = new BLLG_CYCLETMsg();
        setValue(inMsg.glblCmpyCd, glblCmpyCd);
        setValue(inMsg.bllgCycleCd, bllgCycleCd);
        return (BLLG_CYCLETMsg) EZDTBLAccessor.findByKey(inMsg);
    }

    private static int getDiffDays(String thruDt, String fromDt) {
        int diffDays = ZYPDateUtil.getDiffDays(thruDt, fromDt) + 1;
        return diffDays;
    }

    // START 2018/05/22 K.Kojima [QC#23302,ADD]
    /**
     * @param inBean CalcCreditAddlChrgAmtForTerminateBean
     * @return CalcCreditAddlChrgAmtForTerminateBean
     */
    public static CalcCreditAddlChrgAmtForTerminateBean calcCreditAddlChrgAmtForTerminate(CalcCreditAddlChrgAmtForTerminateBean inBean) {
        String glblCmpyCd = inBean.getGlblCmpyCd();
        // mod start 2022/07/07 QC#60167
        //BigDecimal dsContrAddlChrgPk = inBean.getDsContrAddlChrgPk();
        BigDecimal dsContrDtlPk = inBean.getDsContrDtlPk();
        // mod end 2022/07/07 QC#60167
        String trmnDt = inBean.getTrmnDt();
        inBean.setCreditAmt(BigDecimal.ZERO);
        // mod start 2022/07/07 QC#60167
        //if (glblCmpyCd == null || dsContrAddlChrgPk == null || trmnDt == null) {
        if (glblCmpyCd == null || dsContrDtlPk == null || trmnDt == null) {
        // mod end 2022/07/07 QC#60167
            return inBean;
        }

        BigDecimal creditAmt = BigDecimal.ZERO;

        // mod start 2022/07/07 QC#60167
        //Map<String, Object> bllgSchdMap = getSvcInvLineInclTrmnDt(glblCmpyCd, dsContrAddlChrgPk, trmnDt);
        Map<String, Object> bllgSchdMap = getSvcInvLineInclTrmnDt(glblCmpyCd, dsContrDtlPk, trmnDt);
        // mod end 2022/07/07 QC#60167
        if (bllgSchdMap != null) {
            String bllgPerFromDt = (String) bllgSchdMap.get("BLLG_PER_FROM_DT");
            String bllgPerThruDt = (String) bllgSchdMap.get("BLLG_PER_THRU_DT");
            BigDecimal invLineDealNetAmt = (BigDecimal) bllgSchdMap.get("INV_LINE_DEAL_NET_AMT");
            BigDecimal dsContrPk = (BigDecimal) bllgSchdMap.get("DS_CONTR_PK");

            if (ZYPDateUtil.compare(trmnDt, bllgPerThruDt) < 0 && BigDecimal.ZERO.compareTo(invLineDealNetAmt) < 0) {
                String creditStartDate = ZYPDateUtil.addDays(trmnDt, 1);
                // START 2022/02/04 K.Kitachi [QC#59684, ADD]
                String manTrmnTpCd = inBean.getManTrmnTpCd();
                if (MAN_TRMN_TP.ALL_PERIOD.equals(manTrmnTpCd)) {
                    creditStartDate = trmnDt;
                }
                // END 2022/02/04 K.Kitachi [QC#59684, ADD]
                BigDecimal diffDaysOld = new BigDecimal(getDiffDays(bllgPerThruDt, bllgPerFromDt));
                BigDecimal diffDaysNew = new BigDecimal(getDiffDays(bllgPerThruDt, creditStartDate));
                int digitNum = getDigitNumForContr(glblCmpyCd, dsContrPk);

                creditAmt = invLineDealNetAmt.multiply(diffDaysNew).divide(diffDaysOld, digitNum, BigDecimal.ROUND_HALF_UP);
            }
        }

        // mod start 2022/07/07 QC#60167
        //BigDecimal billedAmt = getSvcInvLineAfterTrmnDt(glblCmpyCd, dsContrAddlChrgPk, trmnDt);
        BigDecimal billedAmt = getSvcInvLineAfterTrmnDt(glblCmpyCd, dsContrDtlPk, trmnDt);
        // mod end 2022/07/07 QC#60167
        if (billedAmt != null && BigDecimal.ZERO.compareTo(billedAmt) < 0) {
            creditAmt = creditAmt.add(billedAmt);
        }

        inBean.setCreditAmt(creditAmt);
        return inBean;
    }

    // mod start 2022/07/07 QC#60167
    //private static Map<String, Object> getSvcInvLineInclTrmnDt(String glblCmpyCd, BigDecimal dsContrAddlChrgPk, String trmnDt) {
    //    Map<String, Object> ssmParam = new HashMap<String, Object>();
    //    ssmParam.put("glblCmpyCd", glblCmpyCd);
    //    ssmParam.put("dsContrAddlChrgPk", dsContrAddlChrgPk);
    //    ssmParam.put("trmnDt", trmnDt);
    //    return (Map<String, Object>) ssmBatchClient.queryObject("getSvcInvLineInclTrmnDt", ssmParam);
    //}
    private static Map<String, Object> getSvcInvLineInclTrmnDt(String glblCmpyCd, BigDecimal dsContrDtlPk, String trmnDt) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        ssmParam.put("trmnDt", trmnDt);
        ssmParam.put("invTpInvoice", INV_TP.INVOICE);
        ssmParam.put("svcInvChrgTpAddlChrg", SVC_INV_CHRG_TP.ADDITIONAL_CHARGE);
        ssmParam.put("svcInvChrgTpBaseChrg", SVC_INV_CHRG_TP.BASE_CHARGE);
        return (Map<String, Object>) ssmBatchClient.queryObject("getSvcInvLineInclTrmnDt", ssmParam);
    }
    // mod end 2022/07/07 QC#60167

    // mod start 2022/07/07 QC#60167
    //private static BigDecimal getSvcInvLineAfterTrmnDt(String glblCmpyCd, BigDecimal dsContrAddlChrgPk, String trmnDt) {
    //    Map<String, Object> ssmParam = new HashMap<String, Object>();
    //    ssmParam.put("glblCmpyCd", glblCmpyCd);
    //    ssmParam.put("dsContrAddlChrgPk", dsContrAddlChrgPk);
    //    ssmParam.put("trmnDt", trmnDt);
    //    return (BigDecimal) ssmBatchClient.queryObject("getSvcInvLineAfterTrmnDt", ssmParam);
    //}
    private static BigDecimal getSvcInvLineAfterTrmnDt(String glblCmpyCd, BigDecimal dsContrDtlPk, String trmnDt) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        ssmParam.put("trmnDt", trmnDt);
        ssmParam.put("invTpInvoice", INV_TP.INVOICE);
        ssmParam.put("svcInvChrgTpAddlChrg", SVC_INV_CHRG_TP.ADDITIONAL_CHARGE);
        ssmParam.put("svcInvChrgTpBaseChrg", SVC_INV_CHRG_TP.BASE_CHARGE);
        return (BigDecimal) ssmBatchClient.queryObject("getSvcInvLineAfterTrmnDt", ssmParam);
    }
    // mod end 2022/07/07 QC#60167

    private static int getDigitNumForContr(String glblCmpyCd, BigDecimal dsContrPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrPk", dsContrPk);
        BigDecimal digitNum = (BigDecimal) ssmBatchClient.queryObject("getDigitNumForContr", ssmParam);
        if (!hasValue(digitNum)) {
            return 2;
        }
        return digitNum.intValue();
    }
    // END 2018/05/22 K.Kojima [QC#23302,ADD]
    
    // ADD START 2022/04/26 QC#59979
    private static Map<String, Object> getDsContrDtlTpCd(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);

        return (Map<String, Object>) ssmBatchClient.queryObject("getDsContrDtlTpCd", ssmParam);
    }

    private static boolean isDailyCalcuration(String skipRecovTpCd, String prntSkipRecovTpCd) {
        if (hasValue(skipRecovTpCd) && SKIP_RECOV_TP.SKIP.equals(skipRecovTpCd)) {
            return false;

        } else if ((hasValue(prntSkipRecovTpCd) && SKIP_RECOV_TP.SKIP.equals(prntSkipRecovTpCd))) {
            return false;

        }
        return true;
    }
    // ADD END 2022/04/26 QC#59979
    
    // MOD START 2022/05/16 QC#60038
    private static String getBillWithEquipFlg(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);

        String billWithEquipFlg = (String) ssmBatchClient.queryObject("getBillWithEquipFlg", ssmParam);
        if (!hasValue(billWithEquipFlg)) {
            billWithEquipFlg =  "";
        }

        return billWithEquipFlg;
    }
    // MOD END 2022/05/16 QC#60038
    
    // ADD START 2022/06/02 QC#60038
    /**
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @param bllgSchdThruDt String
     * @return String
     */
    private static String getInvedMaxBllgSchdThruDtForBase(String glblCmpyCd, BigDecimal dsContrDtlPk, String bllgSchdThruDt) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        ssmParam.put("bllgSchdThruDt", bllgSchdThruDt);
        return (String) (String) ssmBatchClient.queryObject("getInvedMaxBllgSchdThruDtForBase", ssmParam);
    }

    // MOD END   2022/06/02 QC#60038
}
