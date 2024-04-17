/**
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 * 
 * <pre>
 * It is a class that offers the function concerning the currency conversion.
 * 
 * The offered function is as follows.
 * - Acquisition of exchange rate
 * - Conversion of amount of function currency
 * - Conversion of amount of dealings currency
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/04/2013   Fujitsu         D.Yanagisawa    CREATE          #MEX-LC004
 * </pre>
 */
package com.canon.cusa.s21.common.NWX.NWXC100001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static java.math.BigDecimal.ZERO;
import static java.math.RoundingMode.HALF_UP;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.db.CCYTMsg;
import business.db.PMT_TERM_CASH_DISCTMsg;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

public class NWXC100001ExchangeByPmtTerm {

    public static final String NWZM0230E = "NWZM0230E";
    public static final String NWZM0233E = "NWZM0233E";
    public static final String NWZM0424E = "NWZM0424E";

    /**
     * It is a computational method that shows multiplication.
     */
    private static final String MULTIPLICATION = "*";

    private static final S21SsmBatchClient ssmClient = S21SsmBatchClient.getClient(NWXC100001ExchangeByPmtTerm.class);
    

    /**
     * <pre>
     * It converts it into the amount of the function currency from the amount of the dealings currency.
     * It converts it from the amount of the dealings currency into the amount
     * of the function currency according to the conversion rate and the computational method.
     * 
     * When the converted amount of money is a unsetting, the amount of money is not converted.
     * 
     * About the scale when converting it, the scale corresponding to the functional
     * currency is acquired, and converted.
     * When the converted amount falls below the smallest sum corresponding to the
     * functional currency, the smallest sum is set.
     * 
     * <u>Required parameter</u>
     * - Global Company Code
     * 
     * It is assumed that it makes an error when it meets the following requirements
     * and returns error information.
     * When there is no scale of the functional currency - NWZM0230E
     * </pre>
     * 
     * @param exchData Conversion result
     * @param rateData Exchange rate and computational method
     */
    public static void exchFuncUnitPrice(NWXC100001ExchangeByPmtTermData exchData, NWXC100001RateData rateData) {

        final Integer scale = getStdCcyDigit(exchData.getGlblCmpyCd());
        if (scale == null) {
            addXxMsgId(exchData, NWZM0230E);
            return;
        }

        final String     acctArthTpCd = rateData.getAcctArthTpCd();
        final BigDecimal actlExchRate = rateData.getActlExchRate();

        for (NWXC100001ExchangePriceData prcData : exchData.getPriceData()) {
            for (NWXC100001ExchangeAmount amtData : prcData.getAmountList()) {
                final BigDecimal funcAmt = calcAmt(amtData.getDealAmt(), acctArthTpCd, actlExchRate, scale, HALF_UP);
                amtData.setFuncAmt(funcAmt);
            }
        }
    }

    /**
     * <pre>
     * The exchange rate is acquired.
     * Exchange rate and computational method ('*' or '/') of a day concerned nearest
     * are acquired from the rate for past one month on a day (sales date) concerned.
     * 
     * When the necessary following item values are the unsettings
     * when the rate is acquired, it doesn't process it.
     * 
     * <u>Required parameter</u>
     * - Global Company Code
     * - To Currency Code
     * - Sales Date
     * - Payment Term Cash Discount Code
     * 
     * It doesn't process when the required parameter is a unsetting,
     * and "exchData" of the parameter is returned.
     * 
     * It is assumed that it makes an error when it meets the following requirements
     * and returns error information.
     * There is no retrieval result - NWZM0233E
     * The conversion rate is less than 0 - NWZM0424E
     * </pre>
     * 
     * @param glblCmpyCd Global Company Code
     * @param toCcyCd To Currency Code
     * @param slsDt Sales Date
     * @param pmtTermCashDiscCd Payment Term Cash Discount Code
     * @return Exchange rate and computational method
     */
    public static NWXC100001RateData getRateByPmtTerm(String glblCmpyCd, String toCcyCd, String slsDt, String pmtTermCashDiscCd) {

        if (!hasValue(glblCmpyCd) || !hasValue(toCcyCd) || !hasValue(slsDt)) {
            return null;
        }

        NWXC100001RateData rateData = null;

        if (hasValue(pmtTermCashDiscCd)) {
            PMT_TERM_CASH_DISCTMsg pmtTermCashTMsg = new PMT_TERM_CASH_DISCTMsg();
            pmtTermCashTMsg.glblCmpyCd.setValue(glblCmpyCd);
            pmtTermCashTMsg.pmtTermCashDiscCd.setValue(pmtTermCashDiscCd); 
            
            pmtTermCashTMsg = (PMT_TERM_CASH_DISCTMsg) S21CacheTBLAccessor.findByKey(pmtTermCashTMsg);
            
            if (pmtTermCashTMsg != null) {
                if (hasValue(pmtTermCashTMsg.actlExchRateTpNum)) {
                    final Map<String, String> ssmParamForMult = new HashMap<String, String>();
                    ssmParamForMult.put("glblCmpyCd", glblCmpyCd);
                    ssmParamForMult.put("toCcyCd", toCcyCd);
                    ssmParamForMult.put("slsDt", slsDt);
                    ssmParamForMult.put("actlExchRateTpNum", pmtTermCashTMsg.actlExchRateTpNum.getValue());

                    rateData = (NWXC100001RateData) ssmClient.queryObject("getMultExchRateData", ssmParamForMult);
                }
            }
        }

        if (rateData == null) {
            final Map<String, String> ssmParam = new HashMap<String, String>();
            ssmParam.put("glblCmpyCd", glblCmpyCd);
            ssmParam.put("ccyCd",      toCcyCd);
            ssmParam.put("slsDt",      slsDt);

            rateData = (NWXC100001RateData) ssmClient.queryObject("getExchRateData", ssmParam);
        }

        if (rateData == null) {
            rateData = new NWXC100001RateData();
            addXxMsgId(rateData, NWZM0233E);
            return rateData;
        }

        if (rateData.getActlExchRate().compareTo(ZERO) <= 0) {
            addXxMsgId(rateData, NWZM0424E);
            return rateData;
        }

        return rateData;
    }

    private static void addXxMsgId(NWXC100001ExchangeByPmtTermData exchData, String msgId) {
        List<String> xxMsgIdList = exchData.getXxMsgIdList();
        if (xxMsgIdList.isEmpty()) {
            xxMsgIdList = new ArrayList<String>(1);
        }
        xxMsgIdList.add(msgId);
        exchData.setXxMsgIdList(xxMsgIdList);
    }

    private static void addXxMsgId(NWXC100001RateData rateData, String msgId) {
        List<String> xxMsgIdList = rateData.getXxMsgIdList();
        if (xxMsgIdList.isEmpty()) {
            xxMsgIdList = new ArrayList<String>(1);
        }
        xxMsgIdList.add(msgId);
        rateData.setXxMsgIdList(xxMsgIdList);
    }

    public static BigDecimal calcAmt(BigDecimal amt, String acctArthTpCd, BigDecimal actlExchRate, int scale, RoundingMode rMode) {

        BigDecimal retAmt = null;

        if (MULTIPLICATION.equals(acctArthTpCd)) {
            if (amt != null) {
                retAmt = amt.multiply(actlExchRate).setScale(scale, HALF_UP);
            }
        } else {
            if (amt != null) {
                retAmt = amt.divide(actlExchRate, scale, HALF_UP);
            }
        }

        return retAmt;
    }

    public static Integer getDealCcyDigit(String glblCmpyCd, String dealCcyCd) {

        CCYTMsg ccyTMsg = new CCYTMsg();
        setValue(ccyTMsg.glblCmpyCd, glblCmpyCd);
        setValue(ccyTMsg.ccyCd,      dealCcyCd);
        
        ccyTMsg = (CCYTMsg)S21CacheTBLAccessor.findByKey(ccyTMsg);
        
        if (ccyTMsg == null) {
            return null;
            
        } else {
            // AFT_DECL_PNT_DIGIT_NUM
            final BigDecimal aftDeclPntDigitNum = ccyTMsg.aftDeclPntDigitNum.getValue();
            if (aftDeclPntDigitNum == null) {
                return null;
            } else {
                return aftDeclPntDigitNum.intValue();
            }
        }
    }

    private static Integer getStdCcyDigit(String glblCmpyCd) {

        final Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);

        return (Integer) ssmClient.queryObject("getStdCcyDigit", ssmParam);
    }

}
