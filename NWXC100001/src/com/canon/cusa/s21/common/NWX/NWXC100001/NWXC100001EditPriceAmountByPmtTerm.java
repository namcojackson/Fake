/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.common.NWX.NWXC100001;

import static java.math.RoundingMode.HALF_UP;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDTMsg;
import business.db.GLBL_CMPYTMsg;
import business.db.MDSETMsg;

import com.canon.cusa.s21.common.NWX.NWXC004001.NWXMdseTMsgThreadLocalCache;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;

/**
 * <pre>
 * It is a class that offers the function concerning the currency conversion.
 * and calculation price of set Component.
 * 
 * The offered function is as follows.
 * - Acquisition of exchange rate
 * - Conversion of amount of function currency
 * - Conversion of amount of dealings currency
 * - Conversion of amount of dealings currency (from currency -> to currency)
 * - calculation of amount of set Component
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/04/2013   Fujitsu         D.Yanagisawa    CREATE          #MEX-LC004
 * </pre>
 */
public class NWXC100001EditPriceAmountByPmtTerm {

    /** Standard Currency Code is not existed in Master. */
    public static final String NWZM0230E = "NWZM0230E";

    /** CMPSN Master is not set correctly. */
    public static final String NWZM0319E = "NWZM0319E";

    /** MDSE COODE is not existed in MDSE Master. */
    public static final String NWZM0382E = "NWZM0382E";

    /** Not Supported. */
    public static final String NWZM1310E = "NWZM1310E";

    /**
     * It is a computational method that shows multiplication.
     */
    private static final String MULTIPLICATION = "*";

    /**
     * It is a computational method that shows division.
     */
    private static final String DIVISION = "/";

    /** ONE means Number is 1 */
    private static final BigDecimal ONE = BigDecimal.ONE;

    /** ZERO means Number is 0 */
    private static final BigDecimal ZERO = BigDecimal.ZERO;

    /**
     * <pre>
     * The exchange rate is acquired.
     * Exchange rate and computational method ('*' or '/') of a day concerned nearest
     * are acquired from the rate for past one month on a day (sales date) concerned.
     * 
     * It doesn't process when the required parameter is not setting.
     * 
     * Required parameter
     * - Global Company Code
     * - Currency Code
     * - To Currency Code
     * - Sales Date
     * - Merchandise Code
     * - Amount(Gross, Net)
     * 
     * </pre>
     * @param editPrcAmtInfo NWXC100001EditPriceAmountByPmtTermInfo
     * @return NWXC100001EditPriceAmountByPmtTermInfo
     */
    public static NWXC100001EditPriceAmountByPmtTermInfo exchPrcAmt(NWXC100001EditPriceAmountByPmtTermInfo editPrcAmtInfo) {

        // check input data
        if (!checkInputParam(editPrcAmtInfo)) {
            return editPrcAmtInfo;
        }

        // get Standard Currency Code
        String stdCcyCd = getStdCcyCd(editPrcAmtInfo.getGlblCmpyCd());
        if (stdCcyCd == null) {
            editPrcAmtInfo.getXxMsgIdList().add(NWZM0230E);
            return editPrcAmtInfo;
        }

        // check Merchandise Code
        MDSETMsg mdseMsg = NWXMdseTMsgThreadLocalCache.getInstance().get(editPrcAmtInfo.getGlblCmpyCd(), editPrcAmtInfo.getMdseCd());
        if (mdseMsg == null) {
            editPrcAmtInfo.getXxMsgIdList().add(NWZM0382E);
            return editPrcAmtInfo;
        }

        final NWXC100001UnitPriceData unitPrcData = new NWXC100001UnitPriceData();
        editPrcAmtInfo.setUnitPrcData(unitPrcData);
        unitPrcData.setMdseCd(mdseMsg.mdseCd.getValue());
        unitPrcData.setCcyCd(editPrcAmtInfo.getCcyCd());
        unitPrcData.setToCcyCd(editPrcAmtInfo.getToCcyCd());

        // get ExchangeRate
        NWXC100001RateData rateDataForToCcyCd = new NWXC100001RateData();
        String ccyCd = editPrcAmtInfo.getCcyCd();
        String toCcyCd = editPrcAmtInfo.getToCcyCd();

        Integer scaleForCcy = null;

        if (ccyCd.equals(toCcyCd)) {

            rateDataForToCcyCd.setAcctArthTpCd(MULTIPLICATION);
            rateDataForToCcyCd.setActlExchRate(ONE);

            if (stdCcyCd.equals(ccyCd)) {

                // set Functional Price
                BigDecimal funcGrsUnitPrcAmt = editPrcAmtInfo.getDealGrsPrcAmt();
                unitPrcData.setFuncGrsUnitPrcAmt(funcGrsUnitPrcAmt);
                BigDecimal funcNetUnitPrcAmt = editPrcAmtInfo.getDealNetPrcAmt();
                unitPrcData.setFuncNetUnitPrcAmt(funcNetUnitPrcAmt);

            }

        } else {

            if (stdCcyCd.equals(ccyCd)) {

                // get ExchangeRate By Payment Term Cash Discount Code
                rateDataForToCcyCd = getExchRateByPmtTerm(editPrcAmtInfo, stdCcyCd, editPrcAmtInfo.getToCcyCd());
                if (editPrcAmtInfo.getXxMsgIdList().size() > 0) {
                    return editPrcAmtInfo;
                }

                if (MULTIPLICATION.equals(rateDataForToCcyCd.getAcctArthTpCd())) {
                    rateDataForToCcyCd.setAcctArthTpCd(DIVISION);
                } else {
                    rateDataForToCcyCd.setAcctArthTpCd(MULTIPLICATION);
                }

                scaleForCcy = NWXC100001ExchangeByPmtTerm.getDealCcyDigit(editPrcAmtInfo.getGlblCmpyCd(), toCcyCd);

                // set Functional Price
                BigDecimal funcGrsUnitPrcAmt = editPrcAmtInfo.getDealGrsPrcAmt();
                unitPrcData.setFuncGrsUnitPrcAmt(funcGrsUnitPrcAmt);
                BigDecimal funcNetUnitPrcAmt = editPrcAmtInfo.getDealNetPrcAmt();
                unitPrcData.setFuncNetUnitPrcAmt(funcNetUnitPrcAmt);

            } else {

                if (stdCcyCd.equals(toCcyCd)) {

                    // get ExchangeRate
                    rateDataForToCcyCd = getExchRateByPmtTerm(editPrcAmtInfo, stdCcyCd, editPrcAmtInfo.getCcyCd());

                    scaleForCcy = NWXC100001ExchangeByPmtTerm.getDealCcyDigit(editPrcAmtInfo.getGlblCmpyCd(), toCcyCd);

                } else {
                    // Not Supported
                    editPrcAmtInfo.getXxMsgIdList().add(NWZM1310E);
                    return editPrcAmtInfo;
                }
            }
        }

        // set Dealings Price
        BigDecimal dealGrsUnitPrcAmt = NWXC100001ExchangeByPmtTerm.calcAmt(editPrcAmtInfo.getDealGrsPrcAmt(), rateDataForToCcyCd.getAcctArthTpCd(), rateDataForToCcyCd.getActlExchRate(), scaleForCcy, HALF_UP);
        unitPrcData.setDealGrsUnitPrcAmt(dealGrsUnitPrcAmt);
        BigDecimal dealNetUnitPrcAmt = NWXC100001ExchangeByPmtTerm.calcAmt(editPrcAmtInfo.getDealNetPrcAmt(), rateDataForToCcyCd.getAcctArthTpCd(), rateDataForToCcyCd.getActlExchRate(), scaleForCcy, HALF_UP);;
        unitPrcData.setDealNetUnitPrcAmt(dealNetUnitPrcAmt);

        NWXC100001RateData rateData = new NWXC100001RateData();
        // Convert: dealings currency Unit Price->functional currency
        if (!stdCcyCd.equals(ccyCd)) {

            // get ExchangeRate
            rateData = getExchRateByPmtTerm(editPrcAmtInfo, stdCcyCd, editPrcAmtInfo.getCcyCd());
            if (editPrcAmtInfo.getXxMsgIdList().size() > 0) {
                return editPrcAmtInfo;
            }

            exchFuncUnitPrc(editPrcAmtInfo, unitPrcData, rateData, editPrcAmtInfo.getGlblCmpyCd());
            if (editPrcAmtInfo.getXxMsgIdList().size() > 0) {
                return editPrcAmtInfo;
            }
        } else {
            rateData.setAcctArthTpCd(MULTIPLICATION);
            rateData.setActlExchRate(ONE);
        }

        // OutputData
        setOutputData(editPrcAmtInfo, unitPrcData, rateData.getActlExchRate());

        return editPrcAmtInfo;
    }

    private static boolean checkInputParam(NWXC100001EditPriceAmountByPmtTermInfo editPrcAmtInfo) {

        if (!ZYPCommonFunc.hasValue(editPrcAmtInfo.getGlblCmpyCd())) {
            editPrcAmtInfo.getXxMsgIdList().add(NWZM0382E);
            return false;
        }

        if (!ZYPCommonFunc.hasValue(editPrcAmtInfo.getMdseCd())) {
            editPrcAmtInfo.getXxMsgIdList().add(NWZM0382E);
            return false;
        }

        if (!ZYPCommonFunc.hasValue(editPrcAmtInfo.getCcyCd())) {
            editPrcAmtInfo.getXxMsgIdList().add(NWZM0382E);
            return false;
        }

        if (!ZYPCommonFunc.hasValue(editPrcAmtInfo.getToCcyCd())) {
            editPrcAmtInfo.getXxMsgIdList().add(NWZM0382E);
            return false;
        }

        if (!ZYPCommonFunc.hasValue(editPrcAmtInfo.getDealGrsPrcAmt())) {
            editPrcAmtInfo.getXxMsgIdList().add(NWZM0382E);
            return false;
        }

        if (!ZYPCommonFunc.hasValue(editPrcAmtInfo.getDealNetPrcAmt())) {
            editPrcAmtInfo.getXxMsgIdList().add(NWZM0382E);
            return false;
        }

        if (!ZYPCommonFunc.hasValue(editPrcAmtInfo.getSlsDt())) {
            editPrcAmtInfo.getXxMsgIdList().add(NWZM0382E);
            return false;
        }

        return true;
    }

    private static void exchFuncUnitPrc(NWXC100001EditPriceAmountByPmtTermInfo editPrcAmtInfo, NWXC100001UnitPriceData priceData, NWXC100001RateData rateData, String glblCmpyCd) {

        // It doesn't calculate because the result is the same even if
        // multiplying it when the conversion rate is one and the
        // value of Gross is set.
        if (rateData.getActlExchRate().compareTo(ONE) == 0) {

            // Each price
            priceData.setFuncGrsUnitPrcAmt(priceData.getDealGrsUnitPrcAmt());
            priceData.setFuncNetUnitPrcAmt(priceData.getDealNetUnitPrcAmt());

            for (NWXC100001UnitPriceData childPriceData : priceData.getChildUnitPrcDataList()) {

                // Each price
                childPriceData.setFuncGrsUnitPrcAmt(childPriceData.getDealGrsUnitPrcAmt());
                childPriceData.setFuncNetUnitPrcAmt(childPriceData.getDealNetUnitPrcAmt());
            }

            return;
        }

        // Setting of conversion information
        NWXC100001ExchangeByPmtTermData grsExchData = getGrsExchData(priceData, glblCmpyCd);
        NWXC100001ExchangeByPmtTermData netExchData = getNetExchData(priceData, glblCmpyCd);

        // Convert: dealings currency Unit Price->functional currency
        // Unit Price
        NWXC100001ExchangeByPmtTerm.exchFuncUnitPrice(grsExchData, rateData);

        if (!grsExchData.getXxMsgIdList().isEmpty()) {
            for (String msgId : grsExchData.getXxMsgIdList()) {
                editPrcAmtInfo.getXxMsgIdList().add(msgId);
            }
            return;
        }

        NWXC100001ExchangeByPmtTerm.exchFuncUnitPrice(netExchData, rateData);

        if (!netExchData.getXxMsgIdList().isEmpty()) {
            for (String msgId : netExchData.getXxMsgIdList()) {
                editPrcAmtInfo.getXxMsgIdList().add(msgId);
            }
            return;
        }

        // Setting of conversion result
        setFuncUnitPrcAmt(priceData, grsExchData, netExchData);

        // The smallest sum of the functional currency is set in
        // case of 0 or less.
        if (priceData.getFuncGrsUnitPrcAmt().compareTo(ZERO) <= 0) {

            priceData.setFuncGrsUnitPrcAmt(ONE.movePointLeft(priceData.getFuncGrsUnitPrcAmt().scale()).multiply(ZERO));
            priceData.setFuncNetUnitPrcAmt(ONE.movePointLeft(priceData.getFuncNetUnitPrcAmt().scale()).multiply(ZERO));
        }
    }

    private static NWXC100001ExchangeByPmtTermData getGrsExchData(NWXC100001UnitPriceData priceData, String glblCmpyCd) {

        NWXC100001ExchangeByPmtTermData exchData = new NWXC100001ExchangeByPmtTermData();
        exchData.setGlblCmpyCd(glblCmpyCd);

        NWXC100001ExchangeAmountData exchAmtData = new NWXC100001ExchangeAmountData();
        NWXC100001ExchangeAmount grsAmt = new NWXC100001ExchangeAmount();

        // Set or Regular item Dealings gross unit price amount
        grsAmt.setDealAmt(priceData.getDealGrsUnitPrcAmt());

        exchAmtData.setGrsAmt(grsAmt);

        List<NWXC100001ExchangePriceData> exchDataList = new ArrayList<NWXC100001ExchangePriceData>();
        exchDataList.add(exchAmtData);

        for (NWXC100001UnitPriceData childPriceData : priceData.getChildUnitPrcDataList()) {

            exchAmtData = new NWXC100001ExchangeAmountData();
            grsAmt = new NWXC100001ExchangeAmount();

            // Component Dealings gross unit price amount
            grsAmt.setDealAmt(childPriceData.getDealGrsUnitPrcAmt());

            exchAmtData.setGrsAmt(grsAmt);
            exchDataList.add(exchAmtData);
        }

        exchData.setPriceData(exchDataList);

        return exchData;
    }

    private static NWXC100001ExchangeByPmtTermData getNetExchData(NWXC100001UnitPriceData priceData, String glblCmpyCd) {

        NWXC100001ExchangeByPmtTermData exchData = new NWXC100001ExchangeByPmtTermData();
        exchData.setGlblCmpyCd(glblCmpyCd);

        NWXC100001ExchangeAmountData exchAmtData = new NWXC100001ExchangeAmountData();
        NWXC100001ExchangeAmount grsAmt = new NWXC100001ExchangeAmount();

        // Set or Regular item Dealings gross unit price amount
        grsAmt.setDealAmt(priceData.getDealNetUnitPrcAmt());

        exchAmtData.setGrsAmt(grsAmt);

        List<NWXC100001ExchangePriceData> exchDataList = new ArrayList<NWXC100001ExchangePriceData>();
        exchDataList.add(exchAmtData);

        for (NWXC100001UnitPriceData childPriceData : priceData.getChildUnitPrcDataList()) {

            exchAmtData = new NWXC100001ExchangeAmountData();
            grsAmt = new NWXC100001ExchangeAmount();

            // Component Dealings gross unit price amount
            grsAmt.setDealAmt(childPriceData.getDealNetUnitPrcAmt());

            exchAmtData.setGrsAmt(grsAmt);
            exchDataList.add(exchAmtData);
        }

        exchData.setPriceData(exchDataList);

        return exchData;
    }

    private static NWXC100001RateData getExchRateByPmtTerm(NWXC100001EditPriceAmountByPmtTermInfo editPrcAmtInfo, String stdCcyCd, String ccyCd) {

        NWXC100001RateData rateData = new NWXC100001RateData();
        String pmtTermCashDiscCd = editPrcAmtInfo.getPmtTermCashDiscCd();

        if (ccyCd.equals(stdCcyCd)) {

            rateData.setAcctArthTpCd(MULTIPLICATION);
            rateData.setActlExchRate(ONE);

        } else {

            // When the functional currency and the dealings to-currency
            // are different, the conversion rate of the dealings
            // currency is acquired.
            String glblCmpyCd = editPrcAmtInfo.getGlblCmpyCd();
            String slsDt = editPrcAmtInfo.getSlsDt();
            rateData = NWXC100001ExchangeByPmtTerm.getRateByPmtTerm(glblCmpyCd, ccyCd, slsDt, pmtTermCashDiscCd);

            if (rateData != null && !rateData.getXxMsgIdList().isEmpty()) {
                for (String xxMsgId : rateData.getXxMsgIdList()) {
                    editPrcAmtInfo.getXxMsgIdList().add(xxMsgId);
                }
                return rateData;
            }
        }

        return rateData;
    }

    private static void setFuncUnitPrcAmt(NWXC100001UnitPriceData priceData, NWXC100001ExchangeByPmtTermData grsExchData, NWXC100001ExchangeByPmtTermData netExchData) {

        // Functional gross unit price amount
        List<NWXC100001ExchangePriceData> gesExchDataList = grsExchData.getPriceData();
        if (gesExchDataList.isEmpty()) {
            return;
        }

        NWXC100001ExchangeAmountData grsExchAmtData = (NWXC100001ExchangeAmountData) gesExchDataList.get(0);

        BigDecimal funcGrsUnitPrcAmt = grsExchAmtData.getGrsAmt().getFuncAmt();
        priceData.setFuncGrsUnitPrcAmt(funcGrsUnitPrcAmt);

        int j = 0;
        for (int i = 1; i < gesExchDataList.size(); i++) {
            NWXC100001UnitPriceData childPriceData = priceData.getChildUnitPrcDataList().get(j++);

            // Functional gross unit price amount
            grsExchAmtData = (NWXC100001ExchangeAmountData) gesExchDataList.get(i);

            funcGrsUnitPrcAmt = grsExchAmtData.getGrsAmt().getFuncAmt();
            childPriceData.setFuncGrsUnitPrcAmt(funcGrsUnitPrcAmt);
        }

        // Functional net unit price amount
        List<NWXC100001ExchangePriceData> netExchDataList = netExchData.getPriceData();
        if (netExchDataList.isEmpty()) {
            return;
        }

        NWXC100001ExchangeAmountData netExchAmtData = (NWXC100001ExchangeAmountData) netExchDataList.get(0);

        BigDecimal funcNetUnitPrcAmt = netExchAmtData.getGrsAmt().getFuncAmt();
        priceData.setFuncNetUnitPrcAmt(funcNetUnitPrcAmt);

        j = 0;
        for (int i = 1; i < netExchDataList.size(); i++) {
            NWXC100001UnitPriceData childPriceData = priceData.getChildUnitPrcDataList().get(j++);

            // Functional net unit price amount
            netExchAmtData = (NWXC100001ExchangeAmountData) netExchDataList.get(i);

            funcNetUnitPrcAmt = netExchAmtData.getGrsAmt().getFuncAmt();
            childPriceData.setFuncNetUnitPrcAmt(funcNetUnitPrcAmt);
        }
    }

    private static void setOutputData(NWXC100001EditPriceAmountByPmtTermInfo editPrcAmtInfo, NWXC100001UnitPriceData unitPrcData, BigDecimal actlExchRate) {

        unitPrcData.setExchRate(actlExchRate);

    }

    private static EZDTMsg findByKeyWithCache(EZDTMsg reqTMsg) {

        return S21CacheTBLAccessor.findByKey(reqTMsg);
    }

    private static String getStdCcyCd(String glblCmpyCd) {

        GLBL_CMPYTMsg glblCmpyMsg = new GLBL_CMPYTMsg();
        ZYPEZDItemValueSetter.setValue(glblCmpyMsg.glblCmpyCd, glblCmpyCd);

        glblCmpyMsg = (GLBL_CMPYTMsg) findByKeyWithCache(glblCmpyMsg);

        if (glblCmpyMsg == null) {
            return null;
        } else {
            return glblCmpyMsg.stdCcyCd.getValue();
        }
    }

}
