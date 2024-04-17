/*
 * <pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.common.NWX.NWXC001001;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import parts.common.EZDTMsg;
import business.db.CCYTMsg;
import business.db.CMPSNTMsg;
import business.db.CMPSNTMsgArray;
import business.db.GLBL_CMPYTMsg;
import business.db.MDSETMsg;

import com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001ExchangeAmoutData;
import com.canon.cusa.s21.common.NWX.NWXC004001.NWXCmpsnTMsgThreadLocalCache;
import com.canon.cusa.s21.common.NWX.NWXC004001.NWXMdseTMsgThreadLocalCache;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_TP;
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
 * - calculation of amount of set Component
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/28/2012   FUJITSU         M.FUJI          CREATE          N/A
 * 05/23/2013   Fujitsu         M.Yamamoto      Update          #R-OM003
 * 05/23/2013   Fujitsu         N.Nakazawa      Update          WDS Defect#1653
 * 03/03/2016   Fujitsu         T.Ishii         Update          S21_NA#2846#1
 * </pre>
 */
public class NWXC001001EditPriceAmount {

    /** Standard Currency Code is not existed in Master. */
    public static final String NWZM0230E = "NWZM0230E";

    /** CMPSN Master is not set correctly. */
    public static final String NWZM0319E = "NWZM0319E";

    /** MDSE COODE is not existed in MDSE Master. */
    public static final String NWZM0382E = "NWZM0382E";

    /**
     * It is a computational method that shows multiplication.
     */
    private static final String MULTIPLICATION = "*";

    /** Regular Item */
    private static final String SET_ITEM_CATG_INDEPENDENT = "0";

    /** Set Parent Item  */
    private static final String SET_ITEM_CATG_PARENT = "1";

    /** Set Comp Item */
    private static final String SET_ITEM_CATG_CHILD = "2";

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
     * - Sales Date
     * - Merchandise Code
     * - Amount(Gross, Net)
     * 
     * </pre>
     * @param editPrcAmtInfo NWXC001001EditPriceAmountInfo
     * @return NWXC001001EditPriceAmountInfo
     */
    public static NWXC001001EditPriceAmountInfo getCmpsnPriceList(NWXC001001EditPriceAmountInfo editPrcAmtInfo) {

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

        final NWXC001001UnitPriceData unitPrcData = new NWXC001001UnitPriceData();
        editPrcAmtInfo.setUnitPrcData(unitPrcData);
        unitPrcData.setMdseCd(mdseMsg.mdseCd.getValue());
        unitPrcData.setCcyCd(editPrcAmtInfo.getCcyCd());

        // SetItemCategory
        setSetItemCategory(unitPrcData, mdseMsg.mdseTpCd.getValue());

        // set Dealings Price
        BigDecimal dealGrsUnitPrcAmt = editPrcAmtInfo.getDealGrsPrcAmt();
        unitPrcData.setDealGrsUnitPrcAmt(dealGrsUnitPrcAmt);
        BigDecimal dealNetUnitPrcAmt = editPrcAmtInfo.getDealNetPrcAmt();
        unitPrcData.setDealNetUnitPrcAmt(dealNetUnitPrcAmt);

        // S21_NA#2846#1 start
        // UnitPrice Distribution(SetItem Only)
        // if
        // (SET_ITEM_CATG_PARENT.equals(unitPrcData.getXxSetItemCatgCd()))
        // {
        //
        // List<CMPSNTMsg> cmpsnList =
        // getCompositionData(editPrcAmtInfo,
        // unitPrcData.getMdseCd());
        // if (editPrcAmtInfo.getXxMsgIdList().size() > 0) {
        // return editPrcAmtInfo;
        // }
        //
        // // get Dealings Currency Scale
        // int scale = getDealCcyDigit(editPrcAmtInfo.getGlblCmpyCd(),
        // unitPrcData.getCcyCd());
        //
        // distributionPrice(editPrcAmtInfo, unitPrcData, cmpsnList,
        // scale);
        // }
        // S21_NA#2846#1 end

        // get ExchangeRate
        NWXC001001RateData rateData = getExchRate(editPrcAmtInfo, unitPrcData.getCcyCd(), stdCcyCd);
        if (editPrcAmtInfo.getXxMsgIdList().size() > 0) {
            return editPrcAmtInfo;
        }

        // Convert: dealings currency Unit Price->functional currency
        exchFuncUnitPrc(editPrcAmtInfo, unitPrcData, rateData, editPrcAmtInfo.getGlblCmpyCd());
        if (editPrcAmtInfo.getXxMsgIdList().size() > 0) {
            return editPrcAmtInfo;
        }

        // OutputData
        setOutputData(editPrcAmtInfo, unitPrcData, rateData.getActlExchRate());

        return editPrcAmtInfo;
    }

    private static void adjustmentFuncGrsAmount(NWXC001001UnitPriceData priceData, int scale) {

        // Total of Each Price * Child Quantity
        BigDecimal totalFuncGrsAmt = ZERO;

        BigDecimal funcGrsUnitPrcAmt = priceData.getFuncGrsUnitPrcAmt();

        for (NWXC001001UnitPriceData childPriceData : priceData.getChildUnitPrcDataList()) {

            BigDecimal childMdseQty = childPriceData.getChildMdseQty();

            // The number of compositions is multiplied to piece goods
            // unit price and a total unit price is calculated.
            BigDecimal big = multiply(childPriceData.getFuncGrsUnitPrcAmt(), childMdseQty);

            totalFuncGrsAmt = add(totalFuncGrsAmt, big);
        }

        BigDecimal diffPrice = subtract(funcGrsUnitPrcAmt, totalFuncGrsAmt);
        if (diffPrice.compareTo(ZERO) == 0) {
            return;
        }

        for (NWXC001001UnitPriceData childPriceData : priceData.getChildUnitPrcDataList()) {

            if (childPriceData.getAllocRatio().compareTo(ZERO) == 0) {
                continue;
            }

            BigDecimal childMdseQty = childPriceData.getChildMdseQty();

            BigDecimal eachDiffPrice = divide(diffPrice, childMdseQty, scale, RoundingMode.DOWN);

            // When the difference can be divided by the number of
            // compositions
            if (diffPrice.compareTo(multiply(eachDiffPrice, childMdseQty)) == 0) {

                // It adjusts a difference for the data.
                childPriceData.setFuncGrsUnitPrcAmt(add(childPriceData.getFuncGrsUnitPrcAmt(), eachDiffPrice));

                return;
            }
        }

        for (NWXC001001UnitPriceData childPriceData : priceData.getChildUnitPrcDataList()) {

            // Data that has already executed the fraction adjustment
            // is adjusted when it cannot be divided.
            if (childPriceData.isAdjustment()) {

                // It is adjusted a difference as the data of the
                // number of minimum compositions, and, in addition,
                // adjusts a total unit price.
                BigDecimal eachDiffPrice = divide(diffPrice, childPriceData.getChildMdseQty(), scale, RoundingMode.DOWN);
                childPriceData.setFuncGrsUnitPrcAmt(add(childPriceData.getFuncGrsUnitPrcAmt(), eachDiffPrice));

                break;
            }
        }
    }

    private static void adjustmentFuncNetAmount(NWXC001001UnitPriceData priceData, int scale) {

        // Total of Each Price * Child Quantity
        BigDecimal totalFuncNetAmt = ZERO;

        BigDecimal funcNetUnitPrcAmt = priceData.getFuncNetUnitPrcAmt();

        for (NWXC001001UnitPriceData childPriceData : priceData.getChildUnitPrcDataList()) {

            BigDecimal childMdseQty = childPriceData.getChildMdseQty();

            // The number of compositions is multiplied to piece goods
            // unit price and a total unit price is calculated.
            BigDecimal big = multiply(childPriceData.getFuncNetUnitPrcAmt(), childMdseQty);

            totalFuncNetAmt = add(totalFuncNetAmt, big);
        }

        BigDecimal diffPrice = subtract(funcNetUnitPrcAmt, totalFuncNetAmt);
        if (diffPrice.compareTo(ZERO) == 0) {
            return;
        }

        for (NWXC001001UnitPriceData childPriceData : priceData.getChildUnitPrcDataList()) {

            if (childPriceData.getAllocRatio().compareTo(ZERO) == 0) {
                continue;
            }

            BigDecimal childMdseQty = childPriceData.getChildMdseQty();

            BigDecimal eachDiffPrice = divide(diffPrice, childMdseQty, scale, RoundingMode.DOWN);

            // When the difference can be divided by the number of
            // compositions
            if (diffPrice.compareTo(multiply(eachDiffPrice, childMdseQty)) == 0) {

                // It adjusts a difference for the data.
                childPriceData.setFuncNetUnitPrcAmt(add(childPriceData.getFuncNetUnitPrcAmt(), eachDiffPrice));

                return;
            }
        }

        for (NWXC001001UnitPriceData childPriceData : priceData.getChildUnitPrcDataList()) {

            // Data that has already executed the fraction adjustment
            // is adjusted when it cannot be divided.
            if (childPriceData.isAdjustment()) {

                // It is adjusted a difference as the data of the
                // number of minimum compositions, and, in addition,
                // adjusts a total unit price.
                BigDecimal eachDiffPrice = divide(diffPrice, childPriceData.getChildMdseQty(), scale, RoundingMode.DOWN);
                childPriceData.setFuncNetUnitPrcAmt(add(childPriceData.getFuncNetUnitPrcAmt(), eachDiffPrice));

                break;
            }
        }
    }

    private static boolean checkInputParam(NWXC001001EditPriceAmountInfo editPrcAmtInfo) {

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

    private static void distributionPrice(NWXC001001EditPriceAmountInfo editPrcAmtInfo, NWXC001001UnitPriceData priceData, List<CMPSNTMsg> cmpsnList, int scale) {

        setComponentData(priceData, cmpsnList);

        // Distribution gross price
        setDealGrsAmount(priceData, scale);

        // Distribution net price
        setDealNetAmount(priceData, scale);
    }

    private static void exchFuncUnitPrc(NWXC001001EditPriceAmountInfo editPrcAmtInfo, NWXC001001UnitPriceData priceData, NWXC001001RateData rateData, String glblCmpyCd) {

        // It doesn't calculate because the result is the same even if
        // multiplying it when the conversion rate is one and the
        // value of Gross is set.
        if (rateData.getActlExchRate().compareTo(ONE) == 0) {

            // Each price
            priceData.setFuncGrsUnitPrcAmt(priceData.getDealGrsUnitPrcAmt());
            priceData.setFuncNetUnitPrcAmt(priceData.getDealNetUnitPrcAmt());

            for (NWXC001001UnitPriceData childPriceData : priceData.getChildUnitPrcDataList()) {

                // Each price
                childPriceData.setFuncGrsUnitPrcAmt(childPriceData.getDealGrsUnitPrcAmt());
                childPriceData.setFuncNetUnitPrcAmt(childPriceData.getDealNetUnitPrcAmt());
            }

            return;
        }

        // Setting of conversion information
        NWXC001001ExchangeData grsExchData = getGrsExchData(priceData, glblCmpyCd);
        NWXC001001ExchangeData netExchData = getNetExchData(priceData, glblCmpyCd);

        // Convert: dealings currency Unit Price->functional currency
        // Unit Price
        NWXC001001Exchange.exchFuncUnitPrice(grsExchData, rateData);

        if (!grsExchData.getXxMsgIdList().isEmpty()) {
            for (String msgId : grsExchData.getXxMsgIdList()) {
                editPrcAmtInfo.getXxMsgIdList().add(msgId);
            }
            return;
        }

        NWXC001001Exchange.exchFuncUnitPrice(netExchData, rateData);

        if (!netExchData.getXxMsgIdList().isEmpty()) {
            for (String msgId : netExchData.getXxMsgIdList()) {
                editPrcAmtInfo.getXxMsgIdList().add(msgId);
            }
            return;
        }

        // Setting of conversion result
        setFuncUnitPrcAmt(priceData, grsExchData, netExchData);

        // Adjustment amount
        // S21_NA#2846#1 if
        // (SET_ITEM_CATG_PARENT.equals(priceData.getXxSetItemCatgCd()))
        // {
        adjustmentFuncGrsAmount(priceData, priceData.getFuncGrsUnitPrcAmt().scale());
        adjustmentFuncNetAmount(priceData, priceData.getFuncNetUnitPrcAmt().scale());

        // S21_NA#2846#1 } else {

        // The smallest sum of the functional currency is set in
        // case of 0 or less.
        if (priceData.getFuncGrsUnitPrcAmt().compareTo(ZERO) <= 0) {
            // Upd Start WDS Defect#1653
            // priceData.setFuncGrsUnitPrcAmt(ONE.movePointLeft(priceData.getFuncGrsUnitPrcAmt().scale()));
            // priceData.setFuncNetUnitPrcAmt(ONE.movePointLeft(priceData.getFuncNetUnitPrcAmt().scale()));
            priceData.setFuncGrsUnitPrcAmt(ONE.movePointLeft(priceData.getFuncGrsUnitPrcAmt().scale()).multiply(ZERO));
            priceData.setFuncNetUnitPrcAmt(ONE.movePointLeft(priceData.getFuncNetUnitPrcAmt().scale()).multiply(ZERO));
            // Upd End WDS Defect#1653
        }
        // S21_NA#2846#1 }
    }

    private static List<CMPSNTMsg> getCompositionData(NWXC001001EditPriceAmountInfo editPrcAmtInfo, String mdseCd) {

        String glblCmpyCd = editPrcAmtInfo.getGlblCmpyCd();
        String slsDt = editPrcAmtInfo.getSlsDt();

        CMPSNTMsgArray array = NWXCmpsnTMsgThreadLocalCache.getInstance().get(glblCmpyCd, mdseCd, slsDt, slsDt);
        if (array.getValidCount() == 0) {
            editPrcAmtInfo.getXxMsgIdList().add(NWZM0319E);
            return Collections.emptyList();
        }

        List<CMPSNTMsg> list = new ArrayList<CMPSNTMsg>(array.getValidCount());
        for (int i = 0; i < array.getValidCount(); i++) {

            BigDecimal allocRatio = array.no(i).allocRatio.getValue();
            if (allocRatio == null || allocRatio.compareTo(ZERO) == -1) {
                editPrcAmtInfo.getXxMsgIdList().add(NWZM0319E);
                return Collections.emptyList();
            }

            BigDecimal childMdseQty = array.no(i).childMdseQty.getValue();
            if (childMdseQty == null || childMdseQty.compareTo(ZERO) <= 0) {
                editPrcAmtInfo.getXxMsgIdList().add(NWZM0319E);
                return Collections.emptyList();
            }

            list.add(array.no(i));
        }

        sortCompositionData(list);

        return list;
    }

    private static NWXC001001ExchangeData getGrsExchData(NWXC001001UnitPriceData priceData, String glblCmpyCd) {

        NWXC001001ExchangeData exchData = new NWXC001001ExchangeData();
        exchData.setGlblCmpyCd(glblCmpyCd);

        NWXC001001ExchangeAmoutData exchAmtData = new NWXC001001ExchangeAmoutData();
        NWXC001001ExchangeAmount grsAmt = new NWXC001001ExchangeAmount();

        // Set or Regular item Dealings gross unit price amount
        grsAmt.setDealAmt(priceData.getDealGrsUnitPrcAmt());

        exchAmtData.setGrsAmt(grsAmt);

        List<NWXC001001ExchangePriceData> exchDataList = new ArrayList<NWXC001001ExchangePriceData>();
        exchDataList.add(exchAmtData);

        for (NWXC001001UnitPriceData childPriceData : priceData.getChildUnitPrcDataList()) {

            exchAmtData = new NWXC001001ExchangeAmoutData();
            grsAmt = new NWXC001001ExchangeAmount();

            // Component Dealings gross unit price amount
            grsAmt.setDealAmt(childPriceData.getDealGrsUnitPrcAmt());

            exchAmtData.setGrsAmt(grsAmt);
            exchDataList.add(exchAmtData);
        }

        exchData.setPriceData(exchDataList);

        return exchData;
    }

    private static NWXC001001ExchangeData getNetExchData(NWXC001001UnitPriceData priceData, String glblCmpyCd) {

        NWXC001001ExchangeData exchData = new NWXC001001ExchangeData();
        exchData.setGlblCmpyCd(glblCmpyCd);

        NWXC001001ExchangeAmoutData exchAmtData = new NWXC001001ExchangeAmoutData();
        NWXC001001ExchangeAmount grsAmt = new NWXC001001ExchangeAmount();

        // Set or Regular item Dealings gross unit price amount
        grsAmt.setDealAmt(priceData.getDealNetUnitPrcAmt());

        exchAmtData.setGrsAmt(grsAmt);

        List<NWXC001001ExchangePriceData> exchDataList = new ArrayList<NWXC001001ExchangePriceData>();
        exchDataList.add(exchAmtData);

        for (NWXC001001UnitPriceData childPriceData : priceData.getChildUnitPrcDataList()) {

            exchAmtData = new NWXC001001ExchangeAmoutData();
            grsAmt = new NWXC001001ExchangeAmount();

            // Component Dealings gross unit price amount
            grsAmt.setDealAmt(childPriceData.getDealNetUnitPrcAmt());

            exchAmtData.setGrsAmt(grsAmt);
            exchDataList.add(exchAmtData);
        }

        exchData.setPriceData(exchDataList);

        return exchData;
    }

    private static NWXC001001RateData getExchRate(NWXC001001EditPriceAmountInfo editPrcAmtInfo, String ccyCd, String stdCcyCd) {

        NWXC001001RateData rateData = new NWXC001001RateData();

        if (ccyCd.equals(stdCcyCd)) {

            rateData.setAcctArthTpCd(MULTIPLICATION);
            rateData.setActlExchRate(ONE);

        } else {

            // When the functional currency and the dealings currency
            // are different, the conversion rate of the dealings
            // currency is acquired.
            String glblCmpyCd = editPrcAmtInfo.getGlblCmpyCd();
            String slsDt = editPrcAmtInfo.getSlsDt();
            rateData = NWXC001001Exchange.getRate(glblCmpyCd, ccyCd, slsDt);

            if (rateData != null && !rateData.getXxMsgIdList().isEmpty()) {
                for (String xxMsgId : rateData.getXxMsgIdList()) {
                    editPrcAmtInfo.getXxMsgIdList().add(xxMsgId);
                }
                return rateData;
            }
        }

        return rateData;
    }

    private static void setComponentData(NWXC001001UnitPriceData priceData, List<CMPSNTMsg> cmpsnList) {

        List<NWXC001001UnitPriceData> childPriceList = new ArrayList<NWXC001001UnitPriceData>();

        for (CMPSNTMsg cmpsnMsg : cmpsnList) {

            NWXC001001UnitPriceData childPrice = new NWXC001001UnitPriceData();

            String mdseCd = cmpsnMsg.childMdseCd.getValue();
            if (!ZYPCommonFunc.hasValue(mdseCd)) {
                mdseCd = cmpsnMsg.childOrdTakeMdseCd.getValue();
            }
            if (!ZYPCommonFunc.hasValue(mdseCd)) {
                continue;
            }

            childPrice.setMdseCd(mdseCd);
            childPrice.setAllocRatio(cmpsnMsg.allocRatio.getValue());
            childPrice.setChildMdseQty(cmpsnMsg.childMdseQty.getValue());

            childPriceList.add(childPrice);
        }

        priceData.setChildUnitPrcDataList(childPriceList);
    }

    private static void setDealGrsAmount(NWXC001001UnitPriceData priceData, int scale) {

        BigDecimal maxEachPrice = ZERO;

        // Total of Each Price * Child Quantity
        BigDecimal totalDealGrsAmt = ZERO;

        BigDecimal dealGrsUnitPrcAmt = priceData.getDealGrsUnitPrcAmt();

        for (NWXC001001UnitPriceData childPriceData : priceData.getChildUnitPrcDataList()) {

            BigDecimal allocRatio = childPriceData.getAllocRatio();
            BigDecimal childMdseQty = childPriceData.getChildMdseQty();

            if (allocRatio.compareTo(ZERO) != 0) {

                BigDecimal grsUnitPrcAmt = multiply(dealGrsUnitPrcAmt, allocRatio, scale, RoundingMode.DOWN);

                // The number of compositions is multiplied to piece
                // goods unit price and a total unit price is
                // calculated.
                BigDecimal eachGrsUnitPrcAmt = divide(grsUnitPrcAmt, childMdseQty, scale, RoundingMode.DOWN);
                childPriceData.setDealGrsUnitPrcAmt(eachGrsUnitPrcAmt);

                // Each Price * Child Quantity
                BigDecimal big = multiply(eachGrsUnitPrcAmt, childMdseQty);

                totalDealGrsAmt = add(totalDealGrsAmt, big);

            } else {
                childPriceData.setDealGrsUnitPrcAmt(ZERO);
            }

            if (maxEachPrice.compareTo(childPriceData.getDealGrsUnitPrcAmt()) == -1) {
                maxEachPrice = childPriceData.getDealGrsUnitPrcAmt();
            }
        }

        BigDecimal diffPrice = subtract(dealGrsUnitPrcAmt, totalDealGrsAmt);
        if (diffPrice.compareTo(ZERO) == 0) {
            // The one with the maximum piece goods unit price is
            // adjusted for the Promotion price and the adjustment of
            // the price of the function currency.
            for (NWXC001001UnitPriceData childPriceData : priceData.getChildUnitPrcDataList()) {
                if (maxEachPrice.compareTo(childPriceData.getDealGrsUnitPrcAmt()) == 0) {
                    childPriceData.setAdjustment(true);
                    break;
                }
            }
            return;
        }

        String mdseCd = null;
        BigDecimal minChildQty = null;

        for (NWXC001001UnitPriceData childPriceData : priceData.getChildUnitPrcDataList()) {

            if (childPriceData.getAllocRatio().compareTo(ZERO) == 0) {
                continue;
            }

            BigDecimal childMdseQty = childPriceData.getChildMdseQty();

            BigDecimal eachDiffPrice = divide(diffPrice, childMdseQty, scale, RoundingMode.DOWN);
            // When the difference can be divided by the number of
            // compositions
            if (diffPrice.compareTo(multiply(eachDiffPrice, childMdseQty)) == 0) {

                // It adjusts a difference for the data.
                childPriceData.setDealGrsUnitPrcAmt(add(childPriceData.getDealGrsUnitPrcAmt(), eachDiffPrice));

                // The adjustment flag is set.
                childPriceData.setAdjustment(true);

                return;

            } else {
                if (minChildQty == null || minChildQty.compareTo(childMdseQty) == 1) {
                    minChildQty = childMdseQty;
                    mdseCd = childPriceData.getMdseCd();
                }
            }
        }

        for (NWXC001001UnitPriceData childPriceData : priceData.getChildUnitPrcDataList()) {

            if (childPriceData.getMdseCd().equals(mdseCd)) {

                // The adjustment flag is set.
                childPriceData.setAdjustment(true);

                // It is adjusted a difference as the data of the
                // number of minimum compositions, and,
                // in addition, adjusts a total unit price.
                BigDecimal eachDiffPrice = divide(diffPrice, childPriceData.getChildMdseQty(), scale, RoundingMode.DOWN);
                childPriceData.setDealGrsUnitPrcAmt(add(childPriceData.getDealGrsUnitPrcAmt(), eachDiffPrice));

                break;
            }
        }
    }

    private static void setFuncUnitPrcAmt(NWXC001001UnitPriceData priceData, NWXC001001ExchangeData grsExchData, NWXC001001ExchangeData netExchData) {

        // Functional gross unit price amount
        List<NWXC001001ExchangePriceData> gesExchDataList = grsExchData.getPriceData();
        if (gesExchDataList.isEmpty()) {
            return;
        }

        NWXC001001ExchangeAmoutData grsExchAmtData = (NWXC001001ExchangeAmoutData) gesExchDataList.get(0);

        BigDecimal funcGrsUnitPrcAmt = grsExchAmtData.getGrsAmt().getFuncAmt();
        priceData.setFuncGrsUnitPrcAmt(funcGrsUnitPrcAmt);

        int j = 0;
        for (int i = 1; i < gesExchDataList.size(); i++) {
            NWXC001001UnitPriceData childPriceData = priceData.getChildUnitPrcDataList().get(j++);

            // Functional gross unit price amount
            grsExchAmtData = (NWXC001001ExchangeAmoutData) gesExchDataList.get(i);

            funcGrsUnitPrcAmt = grsExchAmtData.getGrsAmt().getFuncAmt();
            childPriceData.setFuncGrsUnitPrcAmt(funcGrsUnitPrcAmt);
        }

        // Functional net unit price amount
        List<NWXC001001ExchangePriceData> netExchDataList = netExchData.getPriceData();
        if (netExchDataList.isEmpty()) {
            return;
        }

        NWXC001001ExchangeAmoutData netExchAmtData = (NWXC001001ExchangeAmoutData) netExchDataList.get(0);

        BigDecimal funcNetUnitPrcAmt = netExchAmtData.getGrsAmt().getFuncAmt();
        priceData.setFuncNetUnitPrcAmt(funcNetUnitPrcAmt);

        j = 0;
        for (int i = 1; i < netExchDataList.size(); i++) {
            NWXC001001UnitPriceData childPriceData = priceData.getChildUnitPrcDataList().get(j++);

            // Functional net unit price amount
            netExchAmtData = (NWXC001001ExchangeAmoutData) netExchDataList.get(i);

            funcNetUnitPrcAmt = netExchAmtData.getGrsAmt().getFuncAmt();
            childPriceData.setFuncNetUnitPrcAmt(funcNetUnitPrcAmt);
        }
    }

    private static void setOutputData(NWXC001001EditPriceAmountInfo editPrcAmtInfo, NWXC001001UnitPriceData unitPrcData, BigDecimal actlExchRate) {

        unitPrcData.setExchRate(actlExchRate);

        // Set or Regular item data
        String ccyCd = unitPrcData.getCcyCd();

        // Component data
        for (NWXC001001UnitPriceData childPriceData : unitPrcData.getChildUnitPrcDataList()) {

            childPriceData.setExchRate(actlExchRate);
            childPriceData.setCcyCd(ccyCd);
            childPriceData.setXxSetItemCatgCd(SET_ITEM_CATG_CHILD);
        }
    }

    private static void setSetItemCategory(NWXC001001UnitPriceData unitPrcData, String mdseTpCd) {

        if (!MDSE_TP.SET.equals(mdseTpCd)) {
            unitPrcData.setXxSetItemCatgCd(SET_ITEM_CATG_INDEPENDENT);
        } else {
            unitPrcData.setXxSetItemCatgCd(SET_ITEM_CATG_PARENT);
        }
    }

    private static BigDecimal add(BigDecimal big1, BigDecimal big2) {

        if (big1 == null) {
            big1 = ZERO;
        }
        if (big2 == null) {
            big2 = ZERO;
        }
        return big1.add(big2);
    }

    private static BigDecimal divide(BigDecimal big1, BigDecimal big2, int scale, RoundingMode roundingMode) {

        if (big1 == null || big2 == null) {
            return ZERO;
        }
        if (big1.compareTo(ZERO) == 0 || big2.compareTo(ZERO) == 0) {
            return ZERO;
        }
        return big1.divide(big2, scale, roundingMode);
    }

    private static EZDTMsg findByKeyWithCache(EZDTMsg reqTMsg) {

        return S21CacheTBLAccessor.findByKey(reqTMsg);
    }

    private static CCYTMsg getCcy(String glblCmpyCd, String ccyCd) {

        CCYTMsg ccyMsg = new CCYTMsg();
        ZYPEZDItemValueSetter.setValue(ccyMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(ccyMsg.ccyCd, ccyCd);

        return (CCYTMsg) findByKeyWithCache(ccyMsg);
    }

    private static int getDealCcyDigit(String glblCmpyCd, String ccyCd) {

        CCYTMsg ccyMsg = getCcy(glblCmpyCd, ccyCd);

        return ccyMsg.aftDeclPntDigitNum.getValueInt();
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

    private static BigDecimal multiply(BigDecimal big1, BigDecimal big2) {

        if (big1 == null || big2 == null) {
            return ZERO;
        }
        return big1.multiply(big2);
    }

    private static BigDecimal multiply(BigDecimal big1, BigDecimal big2, int scale, RoundingMode roundingMode) {

        if (big1 == null || big2 == null) {
            return ZERO;
        }
        return big1.multiply(big2).setScale(scale, roundingMode);
    }

    private static void setDealNetAmount(NWXC001001UnitPriceData priceData, int scale) {

        BigDecimal maxEachPrice = ZERO;

        // Total of Each Price * Child Quantity
        BigDecimal totalDealNetAmt = ZERO;

        BigDecimal dealNetUnitPrcAmt = priceData.getDealNetUnitPrcAmt();

        for (NWXC001001UnitPriceData childPriceData : priceData.getChildUnitPrcDataList()) {

            BigDecimal allocRatio = childPriceData.getAllocRatio();
            BigDecimal childMdseQty = childPriceData.getChildMdseQty();

            if (allocRatio.compareTo(ZERO) != 0) {

                BigDecimal grsUnitPrcAmt = multiply(dealNetUnitPrcAmt, allocRatio, scale, RoundingMode.DOWN);

                // The number of compositions is multiplied to piece
                // goods unit price and a total unit price is
                // calculated.
                BigDecimal eachGrsUnitPrcAmt = divide(grsUnitPrcAmt, childMdseQty, scale, RoundingMode.DOWN);
                childPriceData.setDealNetUnitPrcAmt(eachGrsUnitPrcAmt);

                // Each Price * Child Quantity
                BigDecimal big = multiply(eachGrsUnitPrcAmt, childMdseQty);

                totalDealNetAmt = add(totalDealNetAmt, big);

            } else {
                childPriceData.setDealNetUnitPrcAmt(ZERO);
            }

            if (maxEachPrice.compareTo(childPriceData.getDealNetUnitPrcAmt()) == -1) {
                maxEachPrice = childPriceData.getDealNetUnitPrcAmt();
            }
        }

        BigDecimal diffPrice = subtract(dealNetUnitPrcAmt, totalDealNetAmt);
        if (diffPrice.compareTo(ZERO) == 0) {
            // The one with the maximum piece goods unit price is
            // adjusted for the Promotion price and the adjustment of
            // the price of the function currency.
            for (NWXC001001UnitPriceData childPriceData : priceData.getChildUnitPrcDataList()) {
                if (maxEachPrice.compareTo(childPriceData.getDealNetUnitPrcAmt()) == 0) {
                    childPriceData.setAdjustment(true);
                    break;
                }
            }
            return;
        }

        String mdseCd = null;
        BigDecimal minChildQty = null;

        for (NWXC001001UnitPriceData childPriceData : priceData.getChildUnitPrcDataList()) {

            if (childPriceData.getAllocRatio().compareTo(ZERO) == 0) {
                continue;
            }

            BigDecimal childMdseQty = childPriceData.getChildMdseQty();

            BigDecimal eachDiffPrice = divide(diffPrice, childMdseQty, scale, RoundingMode.DOWN);
            // When the difference can be divided by the number of
            // compositions
            if (diffPrice.compareTo(multiply(eachDiffPrice, childMdseQty)) == 0) {

                // It adjusts a difference for the data.
                childPriceData.setDealNetUnitPrcAmt(add(childPriceData.getDealNetUnitPrcAmt(), eachDiffPrice));

                // The adjustment flag is set.
                childPriceData.setAdjustment(true);

                return;

            } else {
                if (minChildQty == null || minChildQty.compareTo(childMdseQty) == 1) {
                    minChildQty = childMdseQty;
                    mdseCd = childPriceData.getMdseCd();
                }
            }
        }

        for (NWXC001001UnitPriceData childPriceData : priceData.getChildUnitPrcDataList()) {

            if (childPriceData.getMdseCd().equals(mdseCd)) {

                // The adjustment flag is set.
                childPriceData.setAdjustment(true);

                // It is adjusted a difference as the data of the
                // number of minimum compositions, and,
                // in addition, adjusts a total unit price.
                BigDecimal eachDiffPrice = divide(diffPrice, childPriceData.getChildMdseQty(), scale, RoundingMode.DOWN);
                childPriceData.setDealNetUnitPrcAmt(add(childPriceData.getDealNetUnitPrcAmt(), eachDiffPrice));

                break;
            }
        }
    }

    private static void sortCompositionData(List<CMPSNTMsg> list) {

        Collections.sort(list, new Comparator<CMPSNTMsg>() {

            public int compare(CMPSNTMsg o1, CMPSNTMsg o2) {
                BigDecimal ratio1 = o1.allocRatio.getValue();
                BigDecimal ratio2 = o2.allocRatio.getValue();

                int compare = ratio1.compareTo(ratio2);
                if (compare == 0) {
                    compare = compareMdseCd(o1, o2);
                }
                return -compare;
            }

            private int compareMdseCd(CMPSNTMsg o1, CMPSNTMsg o2) {
                String mdseCd1 = o1.childMdseCd.getValue();
                if (!ZYPCommonFunc.hasValue(mdseCd1)) {
                    mdseCd1 = o1.childOrdTakeMdseCd.getValue();
                }
                String mdseCd2 = o2.childMdseCd.getValue();
                if (!ZYPCommonFunc.hasValue(mdseCd2)) {
                    mdseCd2 = o2.childOrdTakeMdseCd.getValue();
                }
                return mdseCd1.compareTo(mdseCd2);
            }
        });
    }

    private static BigDecimal subtract(BigDecimal big1, BigDecimal big2) {

        if (big1 == null) {
            big1 = ZERO;
        }
        if (big2 == null) {
            big2 = ZERO;
        }
        return big1.subtract(big2);
    }

}
