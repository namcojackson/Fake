/*
 * <Pre>Copyright (c) 2023 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NWX.NWXC013001;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDDebugOutput;

import business.db.CCYTMsg;
import business.parts.NWXC013001PMsg;
import business.parts.NWXC013002PMsg;
import business.parts.NWXC013003PMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_COND_UNIT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_DTL_GRP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_GRP_TRGT_ATTRB;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_RULE_ATTRB;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 * <pre>
 * Get Qty Based Fee Formula API
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2023/10/26   Hitachi         T.Fukuta        Create          CSA-QC#61619
 * </pre>
 */
public class NWXC013001 extends S21ApiCommonBase {

    /** PROGRAM_ID */
    private static final String PROGRAM_ID = "NWXC013001";

    /** Data Global Company Code is not entered. */
    private static final String NWZM0163E = "NWZM0163E";

    /** Price Based Date of the parameter is not set. */
    private static final String NWZM1155E = "NWZM1155E";

    /** The parameter's "Transaction Line Number" is not set. */
    private static final String NWZM0803E = "NWZM0803E";

    /** The parameter's "Transaction Line Sub Number" is not set. */
    private static final String NWZM0804E = "NWZM0804E";

    /** The {@} parameter's "{@}" is not set. */
    private static final String NWZM1325E = "NWZM1325E";

    /** VAR_CHAR_CONST key: "QTY_BASED_FEE_QTY" */
    private static final String KEY_QTY_BASED_FEE_QTY = "QTY_BASED_FEE_QTY";

    /** VAR_CHAR_CONST key: "QTY_BASED_FEE_UOM" */
    private static final String KEY_QTY_BASED_FEE_UOM = "QTY_BASED_FEE_UOM";

    /** VAR_CHAR_CONST key: "QTY_BASED_FEE_RATE" */
    private static final String KEY_QTY_BASED_FEE_RATE = "QTY_BASED_FEE_RATE";

    /** Default "QTY_BASED_FEE_QTY" value */
    private static final int DEF_QTY_BASED_FEE_QTY = 5;

    /** Default "QTY_BASED_FEE_UOM" value */
    private static final String DEF_QTY_BASED_FEE_UOM = "EA";

    /** Default "QTY_BASED_FEE_RATE" value */
    private static final BigDecimal DEF_QTY_BASED_FEE_RATE = new BigDecimal(0.1);

    /** RATE_DIGIT */
    private static final int RATE_DIGIT = 6;

    /** 100% */
    private static final int RATE_100_PERCENT = 100;

    /** S21SsmBatchClient */
    private final S21SsmBatchClient ssmBatchClient;

    /** Constructor */
    public NWXC013001() {
        super();
        ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * execute
     * @param param NWXC013001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(NWXC013001PMsg param, final ONBATCH_TYPE onBatchType) {

        if (EZDDebugOutput.isDebug()) {
            EZDDebugOutput.println(1, "+++ NWXC013001PMsg input\n" + param , this);
        }

        S21ApiMessageMap msgMap = new S21ApiMessageMap(param);

        if (checkParam(msgMap)) {
            if (EZDDebugOutput.isDebug()) {
                EZDDebugOutput.println(1, "+++ NWXC013001PMsg output(parameter error)\n" + param , this);
            }
            return;
        }

        calcQtyBasedFee(msgMap);

        if (EZDDebugOutput.isDebug()) {
            EZDDebugOutput.println(1, "+++ NWXC013001PMsg output\n" + param , this);
        }
    }

    /**
     * execute
     * @param params List&lt;NWXC013001PMsg&gt;
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(List<NWXC013001PMsg> params, final ONBATCH_TYPE onBatchType) {

        for (NWXC013001PMsg param : params) {
            execute(param, onBatchType);
        }
    }

    /**
     * Check Parameters
     * @param msgMap S21ApiMessageMap
     * @return true if success, fail otherwise
     */
    private boolean checkParam(S21ApiMessageMap msgMap) {

        NWXC013001PMsg pMsg = (NWXC013001PMsg) msgMap.getPmsg();

        if (!ZYPCommonFunc.hasValue(pMsg.glblCmpyCd)) {
            msgMap.addXxMsgIdWithPrm(NWZM0163E, null);
            msgMap.flush();
            return true;
        }
        if (!ZYPCommonFunc.hasValue(pMsg.prcBaseDt)) {
            msgMap.addXxMsgIdWithPrm(NWZM1155E, null);
            msgMap.flush();
            return true;
        }
        if (!ZYPCommonFunc.hasValue(pMsg.prcRuleHdrPk)) {
            msgMap.addXxMsgIdWithPrm(NWZM1325E, new String[] {PROGRAM_ID, "Price Rule Header PK"});
            msgMap.flush();
            return true;
        }

        for (int i = 0; i < pMsg.NWXC013002PMsg.getValidCount(); i++) {

            NWXC013002PMsg line = pMsg.NWXC013002PMsg.no(i);
            S21ApiMessageMap lineMap = new S21ApiMessageMap(line);

            if (!ZYPCommonFunc.hasValue(line.trxLineNum)) {
                lineMap.addXxMsgIdWithPrm(NWZM0803E, null);
                lineMap.flush();
                return true;
            }

            if (!ZYPCommonFunc.hasValue(line.trxLineSubNum)) {
                lineMap.addXxMsgIdWithPrm(NWZM0804E, null);
                lineMap.flush();
                return true;
            }

            if (!ZYPCommonFunc.hasValue(line.ordQty)) {
                lineMap.addXxMsgIdWithPrm(NWZM1325E, new String[] {PROGRAM_ID, "Order Quantity"});
                lineMap.flush();
                return true;
            }

            if (!ZYPCommonFunc.hasValue(line.specCondPrcPk)) {
                lineMap.addXxMsgIdWithPrm(NWZM1325E, new String[] {PROGRAM_ID, "Specific Condition Price PK"});
                lineMap.flush();
                return true;
            }

            if (!ZYPCommonFunc.hasValue(line.mdseCd) && !ZYPCommonFunc.hasValue(line.ordTakeMdseCd)) {
                lineMap.addXxMsgIdWithPrm(NWZM1325E, new String[] {PROGRAM_ID, "Merchandise Code/Order Take Merchandise Code"});
                lineMap.flush();
                return true;
            }
        }

        return false;
    }

    /**
     * Calculate Quantity Based Fee (for whole Order)
     * @param msgMap S21ApiMessageMap
     */
    private void calcQtyBasedFee(S21ApiMessageMap msgMap) {

        NWXC013001PMsg pMsg = (NWXC013001PMsg) msgMap.getPmsg();

        boolean isNeedCalc = isNeedCalcFee(pMsg);
        if (isNeedCalc) {

            NWXC013002PMsg lastLine = null;
            NWXC013001SumInfoBean sumInfo = new NWXC013001SumInfoBean();

            for (int i = 0; i < pMsg.NWXC013002PMsg.getValidCount(); i++) {
                NWXC013002PMsg line = pMsg.NWXC013002PMsg.no(i);
                boolean calculated = calcQtyBasedFeeForLine(pMsg, line, sumInfo);
                if (calculated) {
                    lastLine = line;
                }
            }

            if (lastLine != null) {
                adjustFraction(pMsg, lastLine, sumInfo);
            }
        }
    }

    /**
     * Confirming need to calculate fee, or not
     * @param pMsg NWXC013001PMsg
     * @return true if need to calculate, false otherwise
     */
    private boolean isNeedCalcFee(NWXC013001PMsg pMsg) {

        Map<String, List<NWXC013002PMsg>> ruleItems = getPriceRuleItems(pMsg);
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();

        // Check items and merge same item lines
        String uom = getQtyBasedFeeUom(glblCmpyCd);
        for (int i = 0; i < pMsg.NWXC013002PMsg.getValidCount(); i++) {
            NWXC013002PMsg line = pMsg.NWXC013002PMsg.no(i);

            // Check item
            String mdseCd = line.mdseCd.getValue();
            if (ZYPCommonFunc.hasValue(line.ordTakeMdseCd)) {
                mdseCd = line.ordTakeMdseCd.getValue();
            }
            if (!ruleItems.containsKey(mdseCd)) {
                // Not a target item
                continue;
            }

            // Check UOM
            NWXC013003PMsg basePrice = getBasePrice(line);
            if (basePrice == null) {
                // Missing base price
                continue;
            }
            if (!uom.equals(basePrice.pkgUomCd.getValue())) {
                // Not a target uom
                continue;
            }

            List<NWXC013002PMsg> targetLines = ruleItems.get(mdseCd);
            targetLines.add(line);
        }

        // Check qty
        boolean isNeedCalc = false;
        int minQty = getQtyBasedFeeQty(glblCmpyCd);
        for (String mdseCd : ruleItems.keySet()) {
            List<NWXC013002PMsg> targetLines = ruleItems.get(mdseCd);
            if (targetLines.size() == 0) {
                // Target item has no lines
                continue;
            }

            int sumQty = 0;
            for (NWXC013002PMsg line : targetLines) {
                sumQty += line.ordQty.getValueInt();
            }
            if (0 < sumQty && sumQty < minQty) {
                // Need to calculate fee
                isNeedCalc = true;
                break;
            }
        }

        return isNeedCalc;
    }

    /**
     * Get Price Rule Items
     * @param pMsg NWXC013001PMsg
     * @return price rule items
     */
    private Map<String, List<NWXC013002PMsg>> getPriceRuleItems(NWXC013001PMsg pMsg) {

        Map<String, Object> param = new HashMap<String, Object>();

        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("prcGrpTrgtAttrbCd", PRC_GRP_TRGT_ATTRB.ITEM_NUMBER);
        param.put("prcBaseDt", pMsg.prcBaseDt.getValue());
        param.put("prcRuleHdrPk", pMsg.prcRuleHdrPk.getValue());
        param.put("prcRuleAttrbCd", PRC_RULE_ATTRB.MATERIAL_PRICE_GROUP);

        Map<String, List<NWXC013002PMsg>> itemMap = new HashMap<String, List<NWXC013002PMsg>>();
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getPrcGrpDtlTrgtItem", param);
        if (resultList != null) {
            for (Map<String, Object> result : resultList) {
                // Mapped value: "instance of ArrayList" will be used at caller for merge same items
                itemMap.put((String) result.get("MDSE_CD"), new ArrayList<NWXC013002PMsg>());
            }
        }

        return itemMap;
    }

    /**
     * Calculate Quantity Based Fee (for line)
     * @param pMsg NWXC013001PMsg
     * @param line NWXC013002PMsg
     * @param sumInfo NWXC013001SumInfoBean
     * @return true if calculate, false otherwise
     */
    private boolean calcQtyBasedFeeForLine(
            NWXC013001PMsg pMsg, NWXC013002PMsg line, NWXC013001SumInfoBean sumInfo) {

        NWXC013003PMsg base = getBasePrice(line);
        NWXC013003PMsg discount = getDiscountPrice(line);
        NWXC013003PMsg target = getTargetCalcPrice(line, PRC_DTL_GRP.FREIGHT);

        if (base == null || !ZYPCommonFunc.hasValue(base.unitPrcAmt) ||
                BigDecimal.ZERO.compareTo(line.ordQty.getValue()) == 0) {
            return false;
        }

        if (target == null) {
            target = line.NWXC013003PMsg.no(line.NWXC013003PMsg.getValidCount());
            line.NWXC013003PMsg.setValidCount(line.NWXC013003PMsg.getValidCount() + 1);
        }

        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        String ccyCd = base.autoPrcCcyCd.getValue();
        int scale = getCcyDigit(glblCmpyCd, ccyCd);
        BigDecimal qty = line.ordQty.getValue();

        BigDecimal unitPrice = calcUnitPrice(base, discount);
        BigDecimal totalQtyBasedFee = calcFeeOfLine(glblCmpyCd, unitPrice, qty, scale);
        BigDecimal qtyBasedFee = divide(totalQtyBasedFee, qty, scale);
        BigDecimal subTotal = round(totalQtyBasedFee, scale);

        ZYPEZDItemValueSetter.setValue(target.trxLineNum, line.trxLineNum);
        ZYPEZDItemValueSetter.setValue(target.trxLineSubNum, line.trxLineSubNum);
        ZYPEZDItemValueSetter.setValue(target.configCatgCd, line.configCatgCd);
        target.prcCondTpCd.clear();
        target.prcCondTpDescTxt.clear();
        ZYPEZDItemValueSetter.setValue(target.prcDtlGrpCd, PRC_DTL_GRP.FREIGHT);
        target.prcJrnlGrpCd.clear();
        ZYPEZDItemValueSetter.setValue(target.prcCatgCd, base.prcCatgCd);
        ZYPEZDItemValueSetter.setValue(target.prcCondManEntryFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(target.prcCondManAddFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(target.prcCondManDelFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(target.pkgUomCd, base.pkgUomCd);
        ZYPEZDItemValueSetter.setValue(target.prcCondUnitCd, PRC_COND_UNIT.AMT);
        target.prcCalcMethCd.clear();
        ZYPEZDItemValueSetter.setValue(target.autoPrcCcyCd, base.autoPrcCcyCd);
        ZYPEZDItemValueSetter.setValue(target.autoPrcAmtRate, qtyBasedFee);
        target.maxPrcAmtRate.clear();
        target.minPrcAmtRate.clear();
        target.manPrcAmtRate.clear();
        ZYPEZDItemValueSetter.setValue(target.calcPrcAmtRate, subTotal);
        ZYPEZDItemValueSetter.setValue(target.unitPrcAmt, qtyBasedFee);
        target.dsMdsePrcPk.clear();
        ZYPEZDItemValueSetter.setValue(target.specCondPrcPk, line.specCondPrcPk);
        target.frtPerWtPk.clear();
        target.ordPrcCalcBasePk.clear();
        ZYPEZDItemValueSetter.setValue(target.prcRuleApplyFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(target.prcRulePrcdPk, pMsg.prcRulePrcdPk);

        BigDecimal linePrice = unitPrice.multiply(qty);
        sumInfo.addOrderPrice(linePrice);
        sumInfo.addTotalFee(round(totalQtyBasedFee, scale));

        if (EZDDebugOutput.isDebug()) {
            EZDDebugOutput.println(1, "+++ Qty Based Fee is calculated\n" +
                    "lineNum = " + target.trxLineNum.getValue() + "." + target.trxLineSubNum.getValue() + "\n" +
                    "unitPrice = " + unitPrice.toPlainString() + "\n" +
                    "totalQtyBasedFee = " + totalQtyBasedFee.toPlainString() + "\n" +
                    "unitPrcAmt = " + qtyBasedFee.toPlainString() + "\n" +
                    "calcPrcAmtRate = " + subTotal.toPlainString(), this);
        }

        return true;
    }

    /**
     * Adjust Fraction
     * @param pMsg NWXC013001PMsg
     * @param line NWXC013002PMsg
     * @param sumInfo NWXC013001SumInfoBean
     */
    private void adjustFraction(
            NWXC013001PMsg pMsg, NWXC013002PMsg line, NWXC013001SumInfoBean sumInfo) {

        NWXC013003PMsg base = getBasePrice(line);

        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        String ccyCd = base.autoPrcCcyCd.getValue();

        int scale = getCcyDigit(glblCmpyCd, ccyCd);
        BigDecimal rate = getQtyBasedFeeRate(glblCmpyCd);
        BigDecimal orderPrice = sumInfo.getOrderPrice();
        BigDecimal orderFee = round(orderPrice.multiply(rate), scale);
        BigDecimal totalFee = round(sumInfo.getTotalFee(), scale);

        if (orderFee.compareTo(totalFee) == 0) {
            return;
        }

        BigDecimal fraction = orderFee.subtract(totalFee);

        NWXC013003PMsg target = getTargetCalcPrice(line, PRC_DTL_GRP.ROUNDING_FACTOR_1);

        if (target == null) {
            target = line.NWXC013003PMsg.no(line.NWXC013003PMsg.getValidCount());
            line.NWXC013003PMsg.setValidCount(line.NWXC013003PMsg.getValidCount() + 1);
        }

        ZYPEZDItemValueSetter.setValue(target.trxLineNum, line.trxLineNum);
        ZYPEZDItemValueSetter.setValue(target.trxLineSubNum, line.trxLineSubNum);
        ZYPEZDItemValueSetter.setValue(target.configCatgCd, line.configCatgCd);
        target.prcCondTpCd.clear();
        target.prcCondTpDescTxt.clear();
        ZYPEZDItemValueSetter.setValue(target.prcDtlGrpCd, PRC_DTL_GRP.ROUNDING_FACTOR_1);
        target.prcJrnlGrpCd.clear();
        ZYPEZDItemValueSetter.setValue(target.prcCatgCd, base.prcCatgCd);
        ZYPEZDItemValueSetter.setValue(target.prcCondManEntryFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(target.prcCondManAddFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(target.prcCondManDelFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(target.pkgUomCd, base.pkgUomCd);
        ZYPEZDItemValueSetter.setValue(target.prcCondUnitCd, PRC_COND_UNIT.AMT);
        target.prcCalcMethCd.clear();
        ZYPEZDItemValueSetter.setValue(target.autoPrcCcyCd, base.autoPrcCcyCd);
        ZYPEZDItemValueSetter.setValue(target.autoPrcAmtRate, BigDecimal.ZERO);
        target.maxPrcAmtRate.clear();
        target.minPrcAmtRate.clear();
        target.manPrcAmtRate.clear();
        ZYPEZDItemValueSetter.setValue(target.calcPrcAmtRate, fraction);
        ZYPEZDItemValueSetter.setValue(target.unitPrcAmt, BigDecimal.ZERO);
        target.dsMdsePrcPk.clear();
        ZYPEZDItemValueSetter.setValue(target.specCondPrcPk, line.specCondPrcPk);
        target.frtPerWtPk.clear();
        target.ordPrcCalcBasePk.clear();
        ZYPEZDItemValueSetter.setValue(target.prcRuleApplyFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(target.prcRulePrcdPk, pMsg.prcRulePrcdPk);

        if (EZDDebugOutput.isDebug()) {
            EZDDebugOutput.println(1, "+++ Qty Based Fee (Fraction)\n" +
                    "lineNum = " + target.trxLineNum.getValue() + "." + target.trxLineSubNum.getValue() + "\n" +
                    "fraction = " + fraction.toPlainString(), this);
        }
    }

    /**
     * Get Base Price from Price List
     * @param line NWXC013002PMsg
     * @return NWXC013003PMsg
     */
    private NWXC013003PMsg getBasePrice(NWXC013002PMsg line) {

        for (int i = 0; i < line.NWXC013003PMsg.getValidCount(); i++) {
            NWXC013003PMsg calc = line.NWXC013003PMsg.no(i);
            if (PRC_DTL_GRP.BASE_PRICE.equals(calc.prcDtlGrpCd.getValue())) {
                return calc;
            }
        }

        return null;
    }

    /**
     * Get Discount Price from Price List
     * @param line NWXC013002PMsg
     * @return NWXC013003PMsg
     */
    private NWXC013003PMsg getDiscountPrice(NWXC013002PMsg line) {

        for (int i = 0; i < line.NWXC013003PMsg.getValidCount(); i++) {
            NWXC013003PMsg calc = line.NWXC013003PMsg.no(i);
            if (PRC_DTL_GRP.DISCOUNT.equals(calc.prcDtlGrpCd.getValue())) {
                return calc;
            }
        }

        return null;
    }

    /**
     * Get Target Calculation Price from Price List
     * @param line NWXC013002PMsg
     * @param prcDtlGrpCd Price detail group code
     * @return NWXC013003PMsg
     */
    private NWXC013003PMsg getTargetCalcPrice(NWXC013002PMsg line, String prcDtlGrpCd) {

        for (int i = 0; i < line.NWXC013003PMsg.getValidCount(); i++) {
            NWXC013003PMsg calc = line.NWXC013003PMsg.no(i);
            if (line.specCondPrcPk.getValue().compareTo(calc.specCondPrcPk.getValue()) == 0 &&
                calc.prcDtlGrpCd.getValue().compareTo(prcDtlGrpCd) == 0) {
                return calc;
            }
        }

        return null;
    }

    /**
     * Calculate Unit Price
     * @param base NWXC013003PMsg(Base Price)
     * @param discount NWXC013003PMsg(Discount Price)
     * @return unit price
     */
    private BigDecimal calcUnitPrice(NWXC013003PMsg base, NWXC013003PMsg discount) {

        BigDecimal unitPrice = base.unitPrcAmt.getValue();

        if (discount != null) {
            BigDecimal discountPrice = discount.unitPrcAmt.getValue();
            if (discountPrice != null) {
                unitPrice = unitPrice.subtract(discountPrice);
            }
        }

        return unitPrice;
    }

    /**
     * Calculate Qty Based Fee of Line
     * @param glblCmpyCd Global Company Code
     * @param unitPrice Unit Price
     * @param qty Quautity of line
     * @param scale Rounding Scale
     * @return caluculated fee of line
     */
    private BigDecimal calcFeeOfLine(String glblCmpyCd, BigDecimal unitPrice, BigDecimal qty, int scale) {

        BigDecimal rate = getQtyBasedFeeRate(glblCmpyCd);
        BigDecimal unitFee = round(unitPrice.multiply(rate), 2);
        return unitFee.multiply(qty);
    }

    /**
     * Round by specified scale
     * @param value Target Value
     * @param scale Rounding Scale
     * @return rounded value
     */
    private BigDecimal round(BigDecimal value, int scale) {
        return value.setScale(scale, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * Divide
     * @param num1 Target Value
     * @param num2 Divisor
     * @param scale Rounding Scale
     * @return divided value
     */
    private BigDecimal divide(BigDecimal num1, BigDecimal num2, int scale) {

        if (BigDecimal.ZERO.compareTo(num2) == 0) {
            return BigDecimal.ZERO;
        }

        num1 = round(num1, scale);
        return round(num1.divide(num2, scale, BigDecimal.ROUND_HALF_UP), scale);
    }

    /**
     * Get Currency Digit (called "scale" at Java implementation)
     * @param glblCmpyCd Global Company Code
     * @param ccyCd Currency Code
     * @return digit value
     */
    private int getCcyDigit(String glblCmpyCd, String ccyCd) {

        CCYTMsg ccyTMsg = new CCYTMsg();
        ZYPEZDItemValueSetter.setValue(ccyTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(ccyTMsg.ccyCd, ccyCd);
        ccyTMsg = (CCYTMsg) S21CacheTBLAccessor.findByKey(ccyTMsg);
        if (ccyTMsg != null) {
            return ccyTMsg.aftDeclPntDigitNum.getValueInt();
        }
        return 0;
    }

    /**
     * Get "UOM" Value from VAR_CHAR_CONST Table
     * @param glblCmpyCd
     * @return UOM value
     */
    private String getQtyBasedFeeUom(String glblCmpyCd) {

        String val = ZYPCodeDataUtil.getVarCharConstValue(KEY_QTY_BASED_FEE_UOM, glblCmpyCd);
        if (val == null) {
            return DEF_QTY_BASED_FEE_UOM;
        }
        return val;
    }

    /**
     * Get "QTY" Value from NUM_CONST Table
     * @param glblCmpyCd Global Company Code
     * @return QTY value
     */
    private int getQtyBasedFeeQty(String glblCmpyCd) {

        BigDecimal val = ZYPCodeDataUtil.getNumConstValue(KEY_QTY_BASED_FEE_QTY, glblCmpyCd);
        if (val == null) {
            return DEF_QTY_BASED_FEE_QTY;
        }
        return val.intValue();
    }

    /**
     * Get "RATE" Value from NUM_CONST Table
     * @param glblCmpyCd Global Company Code
     * @return RATE value
     */
    private BigDecimal getQtyBasedFeeRate(String glblCmpyCd) {

        BigDecimal val = ZYPCodeDataUtil.getNumConstValue(KEY_QTY_BASED_FEE_RATE, glblCmpyCd);
        if (val == null) {
            return DEF_QTY_BASED_FEE_RATE;
        }
        return val
            .divide(new BigDecimal(RATE_100_PERCENT), RATE_DIGIT, BigDecimal.ROUND_HALF_UP);
    }
}
