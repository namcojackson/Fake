/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
/**
 * <pre>
 * Currency Conversion Common Component
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/18/2009   Canon           Y.Kondo         Create          N/A
 * 11/03/2009   Canon           Y.Kondo         Update          DefID 1336
 * 06/18/2010   Canon           Y.Aikawa        Update          N/A
 * 07/16/2010   Canon           Y.Aikawa        Update          N/A
 * 10/14/2010   Canon           T.Tanaka        Update          Merge R2->R3 Cache
 * 10/27/2010   Canon           I.Kondo         Update          DefID:M63
 * 11/24/2010   Canon           I.Kondo         Update          DefID:M125
 * </pre>
 */
package com.canon.cusa.s21.common.NFX.NFXC307001;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

import parts.common.EZDDebugOutput;
import business.db.ACCT_DLY_ACTL_EXCH_RATESTMsg;
import business.db.AR_CCY_CTRLTMsg;
import business.db.CCYTMsg;
import business.db.GLBL_CMPYTMsg;

import com.canon.cusa.s21.common.NFX.NFXC308001.NFCConst;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CCY;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.GLBL_CMPY;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 * NFCCurrencyConversion class.
 */
public class NFCCurrencyConversion {

    /** Table Column Name : ACCT_DLY_ACTL_EXCH_RATES.GLBL_CMPY_CD */
    private static final String RATES_GLBL_CMPY_CD = "glblCmpyCd";

    /** Table Column Name : ACCT_DLY_ACTL_EXCH_RATES.CCY_CD */
    private static final String RATES_CCY_CD = "ccyCd";

    /**
     * Table Column Name :
     * ACCT_DLY_ACTL_EXCH_RATES.ACTL_EXCH_RATE_ENT_DT
     */
    private static final String RATES_ACTL_EXCH_RATE_ENT_DT = "actlExchRateEndDt";

    /** Exchange Rate Data Key : ACTL_EXCH_RATE */
    private static final String ACTL_EXCH_RATE = "ACTL_EXCH_RATE";

    /** Exchange Rate Data Key : ROUND_METH_CD */
    private static final String ROUND_METH_CD = "ROUND_METH_CD";

    /** Exchange Rate Data Key : AFT_DECL_PNT_DIGIT_NUM */
    private static final String AFT_DECL_PNT_DIGIT_NUM = "AFT_DECL_PNT_DIGIT_NUM";

    /** Exchange Rate Data Key : STD_CCY_CD */
    private static final String STD_CCY_CD = "STD_CCY_CD";

    /** Arithmetic operator type : Multiplication */
    private static final String ARTH_TP_MULTIPLY = "*";

    /** Arithmetic operator type : Dividing calculation */
    private static final String ARTH_TP_DIVIDE = "/";

    /**
     * <pre>
     * </pre>
     * 
     * @param glblCmpyCd GlobalCompanyCode
     * @param dealCcyCd DealCurrencyCode
     * @param dealAmt DealAmount
     * @param startingPoint StartingPoint
     * @param exchRateData ExchangeRateData
     * @return NFXC3070Bean
     */
    public NFXC3070Bean convertCurrency(String glblCmpyCd, String dealCcyCd, BigDecimal dealAmt, String startingPoint, Map<String, Object> exchRateData) {

        debugLog("convertCurrency start");
        BigDecimal exchRate = null;
        BigDecimal declPntDigitNum = null;
        String roundMethCd = null;
        String stdCcyCd = null;
        NFXC3070Bean rtrnBean = new NFXC3070Bean();
        if (checkParam(glblCmpyCd, dealCcyCd, dealAmt, startingPoint)) {

            debugLog("checkParam : ParameterNG");
            return rtrnBean;
        }

        if (exchRateData != null && exchRateData.get(ACTL_EXCH_RATE) != null && exchRateData.get(AFT_DECL_PNT_DIGIT_NUM) != null && exchRateData.get(ROUND_METH_CD) != null && exchRateData.get(STD_CCY_CD) != null) {

            exchRate = (BigDecimal) exchRateData.get(ACTL_EXCH_RATE);
            declPntDigitNum = (BigDecimal) exchRateData.get(AFT_DECL_PNT_DIGIT_NUM);
            roundMethCd = (String) exchRateData.get(ROUND_METH_CD);
            stdCcyCd = (String) exchRateData.get(STD_CCY_CD);
        } else {

            exchRateData = new HashMap<String, Object>();
            GLBL_CMPYTMsg glblCmpyT = (GLBL_CMPYTMsg) ZYPCodeDataUtil.findByCode(GLBL_CMPY.class, glblCmpyCd, glblCmpyCd);
            if (glblCmpyT == null) {

                debugLog("GLBL_CMPY : NO DATA");
                debugLog("GLBL_CMPY_CD : " + glblCmpyCd);
                return rtrnBean;
            }
            // Get Exchange Rate
            if (dealCcyCd.equals(glblCmpyT.stdCcyCd.getValue())) {

                exchRate = ZYPCodeDataUtil.getNumConstValue(NFCConst.CST_NUM_CONST_NAME_AR_STD_EXCH_RATE, glblCmpyCd);
                if (exchRate == null) {

                    debugLog("EXCH_RATE : NO DATA");
                    debugLog("GLBL_CMPY_CD : " + glblCmpyCd);
                    return rtrnBean;
                }
            } else {

                ACCT_DLY_ACTL_EXCH_RATESTMsg exchRatesT = new ACCT_DLY_ACTL_EXCH_RATESTMsg();
                Map<String, Object> exchRatesParam = new HashMap<String, Object>();
                exchRatesParam.put(RATES_GLBL_CMPY_CD, glblCmpyCd);
                exchRatesParam.put(RATES_CCY_CD, dealCcyCd);
                exchRatesParam.put(RATES_ACTL_EXCH_RATE_ENT_DT, startingPoint);
                S21SsmEZDResult resultExchRates = NFXC307001Query.getInstance().getActlExchRate(exchRatesParam, exchRatesT);
                if (resultExchRates.getQueryResultCount() > 0) {
                    exchRate = exchRatesT.actlExchRate.getValue();
                } else {
                    debugLog("ACCT_DLY_ACTL_EXCH_RATES : NO DATA");
                    debugLog("GLBL_CMPY_CD : " + glblCmpyCd);
                    debugLog("CCY_CD : " + dealCcyCd);
                    debugLog("ACTL_EXCH_RATE_ENT_DT : " + startingPoint);
                    return rtrnBean;
                }

            }

            AR_CCY_CTRLTMsg arCcyCtrl = new AR_CCY_CTRLTMsg();
            arCcyCtrl.glblCmpyCd.setValue(glblCmpyCd);
            arCcyCtrl.ccyCd.setValue(glblCmpyT.stdCcyCd.getValue());

            arCcyCtrl = (AR_CCY_CTRLTMsg) S21CacheTBLAccessor.findByKey(arCcyCtrl);

            if (!S21CacheTBLAccessor.RTNCD_NORMAL.equals(arCcyCtrl.getReturnCode())) {
                debugLog("AR_CCY_CTRL : NO DATA");
                debugLog("GLBL_CMPY_CD : " + glblCmpyCd);
                debugLog("CCY_CD : " + glblCmpyT.stdCcyCd.getValue());
                return rtrnBean;
            }

            roundMethCd = arCcyCtrl.roundMethCd.getValue();
            declPntDigitNum = arCcyCtrl.aftDeclPntDigitNum.getValue();
            stdCcyCd = glblCmpyT.stdCcyCd.getValue();
            exchRateData.put(ACTL_EXCH_RATE, exchRate);
            exchRateData.put(ROUND_METH_CD, roundMethCd);
            exchRateData.put(AFT_DECL_PNT_DIGIT_NUM, declPntDigitNum);
            exchRateData.put(STD_CCY_CD, stdCcyCd);

        }

        CCYTMsg ccyT = (CCYTMsg) ZYPCodeDataUtil.findByCode(CCY.class, glblCmpyCd, dealCcyCd);
        if (ccyT == null) {

            debugLog("CCY : NO DATA");
            debugLog("GLBL_CMPY_CD : " + glblCmpyCd);
            debugLog("CCY_CD : " + dealCcyCd);
            return rtrnBean;
        }

        BigDecimal currencyAmount = getFuncAmt(dealAmt, exchRate, declPntDigitNum, ccyT.acctArthTpCd.getValue(), roundMethCd);

        if (null == currencyAmount) {
            // Error
            return rtrnBean;
        }

        rtrnBean.setFuncAmt(currencyAmount);
        rtrnBean.setExchRate(exchRate);
        rtrnBean.setFuncCcyCd(stdCcyCd);
        rtrnBean.setRtrnCd(NFCConst.CST_RTN_CD_NORM);
        rtrnBean.setExchRateData(exchRateData);

        debugLog("convertCurrency end");
        return rtrnBean;
    }

    /**
     * @param glblCmpyCd String
     * @param dealCcyCd String
     * @param dealAmt BigDecimal
     * @param exchRate BigDecimal
     * @return BigDecimal
     */
    public BigDecimal getFuncAmtByRate(String glblCmpyCd, String dealCcyCd, BigDecimal dealAmt, BigDecimal exchRate) {

        if (checkParam(glblCmpyCd, dealCcyCd, dealAmt)) {
            // Input parameter error.
            return null;
        }

        GLBL_CMPYTMsg glblCmpyT = (GLBL_CMPYTMsg) ZYPCodeDataUtil.findByCode(GLBL_CMPY.class, glblCmpyCd, glblCmpyCd);
        if (glblCmpyT == null) {
            // error.
            return null;
        }

        AR_CCY_CTRLTMsg arCcyCtrl = new AR_CCY_CTRLTMsg();
        arCcyCtrl.glblCmpyCd.setValue(glblCmpyCd);
        arCcyCtrl.ccyCd.setValue(glblCmpyT.stdCcyCd.getValue());

        arCcyCtrl = (AR_CCY_CTRLTMsg) S21CacheTBLAccessor.findByKey(arCcyCtrl);

        if (!S21CacheTBLAccessor.RTNCD_NORMAL.equals(arCcyCtrl.getReturnCode())) {
            // error.
            return null;
        }

        CCYTMsg ccyT = (CCYTMsg) ZYPCodeDataUtil.findByCode(CCY.class, glblCmpyCd, dealCcyCd);
        if (ccyT == null) {
            // error.
            return null;
        }

        BigDecimal currencyAmount = getFuncAmt(dealAmt, exchRate, arCcyCtrl.aftDeclPntDigitNum.getValue(), ccyT.acctArthTpCd.getValue(), arCcyCtrl.roundMethCd.getValue());

        return currencyAmount;
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param glblCmpyCd GlobalCompanyCode
     * @param dealCcyCd DealCurrencyCode
     * @param dealAmt DealAmount
     * @param startingPoint StartingPoint
     * @return false : ParameterOK, true : ParameterNG
     */
    private boolean checkParam(String glblCmpyCd, String dealCcyCd, BigDecimal dealAmt, String startingPoint) {

        debugLog("checkParam start");

        if (S21StringUtil.isEmpty(glblCmpyCd)) {
            debugLog("GlobalCompanyCode is Empty.");
            return true;
        }
        if (S21StringUtil.isEmpty(dealCcyCd)) {
            debugLog("DealCurrencyCode is Empty.");
            return true;
        }
        if (dealAmt == null) {
            debugLog("DealAmount is NULL.");
            return true;
        }
        if (S21StringUtil.isEmpty(startingPoint)) {
            debugLog("StartingPoint is Empty.");
            return true;
        }
        if (!ZYPDateUtil.isValidDate(startingPoint, ZYPDateUtil.TYPE_YYYYMMDD)) {
            debugLog("StartingPoint : " + startingPoint);
            return true;
        }
        debugLog("checkParam end");
        return false;
    }

    /**
     * @param glblCmpyCd String
     * @param dealCcyCd String
     * @param dealAmt BigDecimal
     * @return false : ParameterOK, true : ParameterNG
     */
    private static boolean checkParam(String glblCmpyCd, String dealCcyCd, BigDecimal dealAmt) {

        if (S21StringUtil.isEmpty(glblCmpyCd)) {
            return true;
        }
        if (S21StringUtil.isEmpty(dealCcyCd)) {
            return true;
        }
        if (dealAmt == null) {
            return true;
        }
        return false;
    }

    private static BigDecimal getFuncAmt(BigDecimal dealAmt, BigDecimal exchRate, BigDecimal declPntDigitNum, String acctArthTpCd, String roundMethCd) {
        BigDecimal currencyAmount = null;
        if (ARTH_TP_MULTIPLY.equals(acctArthTpCd)) {
            // dealAmt * exchRate
            boolean negativeFlg;
            if (dealAmt.compareTo(BigDecimal.ZERO) < 0) {
                dealAmt = dealAmt.negate();
                negativeFlg = true;
            } else {
                negativeFlg = false;
            }

            currencyAmount = dealAmt.multiply(exchRate);

            if (NFCConst.CST_ROUND_METH_CD_ROUND_UP.equals(roundMethCd)) {
                currencyAmount = currencyAmount.setScale(declPntDigitNum.intValue(), RoundingMode.CEILING);
            } else if (NFCConst.CST_ROUND_METH_CD_ROUND_OFF.equals(roundMethCd)) {
                currencyAmount = currencyAmount.setScale(declPntDigitNum.intValue(), RoundingMode.HALF_UP);
            } else if (NFCConst.CST_ROUND_METH_CD_ROUND_DOWN.equals(roundMethCd)) {
                currencyAmount = currencyAmount.setScale(declPntDigitNum.intValue(), RoundingMode.FLOOR);
            } else {
                // ERROR.
                return null;
            }

            if (negativeFlg) {
                currencyAmount = currencyAmount.negate();
            }

        } else if (ARTH_TP_DIVIDE.equals(acctArthTpCd)) {
            // dealAmt / exchRate
            if (BigDecimal.ZERO.compareTo(exchRate) == 0) {
                currencyAmount = BigDecimal.ZERO;
            } else {

                currencyAmount = dealAmt;

                boolean negativeFlg;
                if (dealAmt.compareTo(BigDecimal.ZERO) < 0) {
                    currencyAmount = currencyAmount.negate();
                    negativeFlg = true;
                } else {
                    negativeFlg = false;
                }

                if (NFCConst.CST_ROUND_METH_CD_ROUND_UP.equals(roundMethCd)) {
                    currencyAmount = currencyAmount.divide(exchRate, declPntDigitNum.intValue(), RoundingMode.CEILING);
                } else if (NFCConst.CST_ROUND_METH_CD_ROUND_OFF.equals(roundMethCd)) {
                    currencyAmount = currencyAmount.divide(exchRate, declPntDigitNum.intValue(), RoundingMode.HALF_UP);
                } else if (NFCConst.CST_ROUND_METH_CD_ROUND_DOWN.equals(roundMethCd)) {
                    currencyAmount = currencyAmount.divide(exchRate, declPntDigitNum.intValue(), RoundingMode.FLOOR);
                } else {
                    // ERROR.
                    return null;
                }

                if (negativeFlg) {
                    currencyAmount = currencyAmount.negate();
                }

            }

        } else {
            // ERROR(ACCT_ARTH_TP_CD of off the subject)
            return null;
        }

        return currencyAmount;
    }

    /**
     * @param logmsg String
     */
    private void debugLog(String logmsg) {
        EZDDebugOutput.println(NFCConst.DEBUG_MSG_LVL, logmsg, this);
    }
}
