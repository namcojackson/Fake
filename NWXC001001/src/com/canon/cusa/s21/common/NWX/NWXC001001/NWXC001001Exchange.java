/**
 * <pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</pre>
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
 * 06/25/2009   Fujitsu         S.Sugino        Create          N/A
 * 08/12/2009   Fujitsu         N.Mitsuishi     Update          N/A
 * 09/16/2009   Fujitsu         S.Sugino        Update          132
 * 03/03/2009   Fujitsu         N.Mitsuishi     Update          N/A
 * 04/14/2010   Fujitsu         K.Tajima        Update          5667
 * 10/01/2010   Fujitsu         K.Tajima        Update          Performance tuning
 * 06/29/2016   Fujitsu         T.Yoshida       Update          S21_NA#10321 (For Performance)
 * </pre>
 */
package com.canon.cusa.s21.common.NWX.NWXC001001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static java.math.BigDecimal.ZERO;
import static java.math.RoundingMode.HALF_UP;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.db.CCYTMsg;

import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

public class NWXC001001Exchange {

    public static final String NWZM0230E = "NWZM0230E";
    public static final String NWZM0233E = "NWZM0233E";
    public static final String NWZM0424E = "NWZM0424E";

    /**
     * It is a computational method that shows multiplication.
     */
    private static final String MULTIPLICATION = "*";

    /**
     * It is a computational method that shows division.
     */
    private static final String DIVISION = "/";

    private static final S21SsmBatchClient ssmClient = S21SsmBatchClient.getClient(NWXC001001Exchange.class);
    
    /**
     * <pre>
     * It converts it into the amount of the dealings currency from the amount of the function currency.
     * It converts it from the amount of the function currency into the amount
     * of the dealings currency according to the conversion rate and the computational method.
     * 
     * The conversion rate is retrieved subject to the required parameter and acquired.
     * {@link #getRate(String, String, String)}
     * 
     * <u>Required parameter</u>
     * - Global Company Code
     * - Currency Code
     * - Sales Date
     * 
     * It is assumed that it makes an error when it meets the following requirements
     * and returns error information.
     * When there is no scale of the functional currency - NWZM0230E
     * Refer to {@link #getRate(String, String, String)} for the error that occurs by acquiring the exchange rate.
     * </pre>
     * 
     * @see #exchDealUnitPrice(NWXC001001ExchangeData,
     * NWXC001001RateData)
     * @param exchData Conversion result
     */
    public static void exchDealUnitPrice(NWXC001001ExchangeData exchData) {

        String glblCmpyCd = exchData.getGlblCmpyCd();
        String ccyCd      = exchData.getCcyCd();
        String slsDt      = exchData.getSlsDt();

        final NWXC001001RateData rateData = getRate(glblCmpyCd, ccyCd, slsDt);

        if (rateData != null && !rateData.getXxMsgIdList().isEmpty()) {
            for (String xxMsgId : rateData.getXxMsgIdList()) {
                addXxMsgId(exchData, xxMsgId);
            }
            return;
        }

        exchDealUnitPrice(exchData, rateData);
    }

    /**
     * <pre>
     * It converts it into the amount of the dealings currency from the amount of the function currency.
     * It converts it from the amount of the function currency into the amount
     * of the dealings currency according to the conversion rate and the computational method.
     * 
     * When the converted amount of money is a unsetting, the amount of money is not converted.
     * 
     * About the scale when converting it, the scale corresponding to the dealings
     * currency is acquired, and converted.
     * When the converted amount falls below the smallest sum corresponding to the
     * dealings currency, the smallest sum is set.
     * 
     * <u>Required parameter</u>
     * - Global Company Code
     * - Currency Code
     * 
     * It is assumed that it makes an error when it meets the following requirements
     * and returns error information.
     * When there is no scale of the functional currency - NWZM0230E
     * </pre>
     * 
     * @param exchData Conversion result
     * @param rateData Exchange rate and computational method
     */
    public static void exchDealUnitPrice(NWXC001001ExchangeData exchData, NWXC001001RateData rateData) {

        final Integer scale = getDealCcyDigit(exchData.getGlblCmpyCd(), exchData.getCcyCd());
        if (scale == null) {
            addXxMsgId(exchData, NWZM0230E);
            return;
        }

        final BigDecimal actlExchRate = rateData.getActlExchRate();
        String acctArthTpCd = rateData.getAcctArthTpCd();

        // Because it is conversion from the function to dealings, the conversion sign from dealings to the function is reversed.
        if (MULTIPLICATION.equals(acctArthTpCd)) {
            acctArthTpCd = DIVISION;
        } else {
            acctArthTpCd = MULTIPLICATION;
        }

        for (NWXC001001ExchangePriceData prcData : exchData.getPriceData()) {
            for (NWXC001001ExchangeAmount amtData : prcData.getAmountList()) {
                final BigDecimal dealAmt = calcAmt(amtData.getFuncAmt(), acctArthTpCd, actlExchRate, scale, HALF_UP);
                amtData.setDealAmt(dealAmt);
            }
        }
    }

    /**
     * <pre>
     * It converts it into the amount of the function currency from the amount of the dealings currency.
     * It converts it from the amount of the dealings currency into the amount
     * of the function currency according to the conversion rate and the computational method.
     * 
     * The conversion rate is retrieved subject to the required parameter and acquired.
     * {@link #getRate(String, String, String)}
     * 
     * <u>Required parameter</u>
     * - Global Company Code
     * - Currency Code
     * - Sales Date
     * 
     * It is assumed that it makes an error when it meets the following requirements
     * and returns error information.
     * When there is no scale of the functional currency - NWZM0230E
     * Refer to {@link #getRate(String, String, String)} for the error that occurs by acquiring the exchange rate.
     * </pre>
     * 
     * @see #exchFuncUnitPrice(NWXC001001ExchangeData,
     * NWXC001001RateData)
     * @param exchData Conversion result
     */
    public static void exchFuncUnitPrice(NWXC001001ExchangeData exchData) {

        String glblCmpyCd = exchData.getGlblCmpyCd();
        String ccyCd      = exchData.getCcyCd();
        String slsDt      = exchData.getSlsDt();

        final NWXC001001RateData rateData = getRate(glblCmpyCd, ccyCd, slsDt);

        if (rateData != null && !rateData.getXxMsgIdList().isEmpty()) {
            for (String xxMsgId : rateData.getXxMsgIdList()) {
                addXxMsgId(exchData, xxMsgId);
            }
            return;
        }

        exchFuncUnitPrice(exchData, rateData);
    }

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
    public static void exchFuncUnitPrice(NWXC001001ExchangeData exchData, NWXC001001RateData rateData) {

        final Integer scale = getStdCcyDigit(exchData.getGlblCmpyCd());
        if (scale == null) {
            addXxMsgId(exchData, NWZM0230E);
            return;
        }

        final String     acctArthTpCd = rateData.getAcctArthTpCd();
        final BigDecimal actlExchRate = rateData.getActlExchRate();

        for (NWXC001001ExchangePriceData prcData : exchData.getPriceData()) {
            for (NWXC001001ExchangeAmount amtData : prcData.getAmountList()) {
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
     * - Currency Code
     * - Sales Date
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
     * @param ccyCd Currency Code
     * @param slsDt Sales Date
     * @return Exchange rate and computational method
     */
    public static NWXC001001RateData getRate(String glblCmpyCd, String ccyCd, String slsDt) {

        if (!hasValue(glblCmpyCd) || !hasValue(ccyCd) || !hasValue(slsDt)) {
            return null;
        }

        final Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("ccyCd",      ccyCd);
        ssmParam.put("slsDt",      slsDt);

        // For Performance QC#10321 Mod Start
        // NWXC001001RateData rateData = (NWXC001001RateData) ssmClient.queryObject("getExchRateData", ssmParam);
        NWXC001001RateData rateData = NWXC001001DataCache.getInstance().getExchRateData(ssmParam, ssmClient);
        // For Performance QC#10321 Mod End

        if (rateData == null) {
            rateData = new NWXC001001RateData();
            addXxMsgId(rateData, NWZM0233E);
            return rateData;
        }

        if (rateData.getActlExchRate().compareTo(ZERO) <= 0) {
            addXxMsgId(rateData, NWZM0424E);
            return rateData;
        }

        return rateData;
    }

    private static void addXxMsgId(NWXC001001ExchangeData exchData, String msgId) {
        List<String> xxMsgIdList = exchData.getXxMsgIdList();
        if (xxMsgIdList.isEmpty()) {
            xxMsgIdList = new ArrayList<String>(1);
        }
        xxMsgIdList.add(msgId);
        exchData.setXxMsgIdList(xxMsgIdList);
    }

    private static void addXxMsgId(NWXC001001RateData rateData, String msgId) {
        List<String> xxMsgIdList = rateData.getXxMsgIdList();
        if (xxMsgIdList.isEmpty()) {
            xxMsgIdList = new ArrayList<String>(1);
        }
        xxMsgIdList.add(msgId);
        rateData.setXxMsgIdList(xxMsgIdList);
    }

    private static BigDecimal calcAmt(BigDecimal amt, String acctArthTpCd, BigDecimal actlExchRate, int scale, RoundingMode rMode) {

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

    private static Integer getDealCcyDigit(String glblCmpyCd, String dealCcyCd) {

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

        // For Performance QC#10321 Mod Start
        // return (Integer) ssmClient.queryObject("getStdCcyDigit", ssmParam);
        return NWXC001001DataCache.getInstance().getStdCcyDigit(ssmParam, ssmClient);
        // For Performance QC#10321 Mod End
    }

}
