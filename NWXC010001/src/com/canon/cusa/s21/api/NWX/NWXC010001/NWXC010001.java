/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NWX.NWXC010001;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.db.CCYTMsg;
import business.parts.NWXC010001PMsg;
import business.parts.NWXC010002PMsg;
import business.parts.NWXC010003PMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_COND_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_COND_UNIT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_DTL_GRP;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 * <pre>
 * Negotiated Deal Formula API
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/08/26   Fujitsu         Y.Kanefusa      Create          N/A
 * 2015/08/26   Fujitsu         Y.Kanefusa      Create          QC#2039
 * 2016/04/29   SRA             E.Inada         Update          QC#7809
 * 2016/07/08   Fujitsu         Y.Kanefusa      Update          S21_NA#9694
 * 2016/08/22   Fujitsu         Y.Kanefusa      Update          S21_NA#13369
 * 2016/08/29   Fujitsu         Y.Kanefusa      Update          S21_NA#10738
 * 2017/10/19   Fujitsu         Y.Kanefusa      Update          S21_NA#21651
 * 2018/01/18   Fujitsu         A.Kosai         Update          S21_NA#23105
 * 2018/08/27   Fujitsu         Y.Kanefusa      Update          S21_NA#27792
 * 2018/09/03   Fujitsu         Y.Kanefusa      Update          S21_NA#9700
 * </pre>
 */

public class NWXC010001 extends S21ApiCommonBase {

    /** PROGRAM_ID */
    private static final String PROGRAM_ID = "NWXC010001";

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

    /** Please enter a number equal to or greater than 0 in [@]. */
    private static final String NWZM1343E = "NWZM1343E";

    /** Negotiated Deal rule can't be applied together with other price adjustment rules. */
    private static final String NWZM1943E = "NWZM1943E";

    /** RATE_DIGIT */
    private static final int RATE_DIGIT = 6;

    /** ssmBatchClient */
    private final S21SsmBatchClient ssmBatchClient;

    /**
     * Constructor
     */
    public NWXC010001() {
        super();
        ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * execute Negotiated Deal Formula API
     * @param param NWXC010001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(NWXC010001PMsg param, final ONBATCH_TYPE onBatchType) {

        S21ApiMessageMap msgMap = new S21ApiMessageMap(param);

        if (checkParam(msgMap)) {
            msgMap.flush();
            return;
        }

        calculateNego(msgMap);
    }

    /**
     * execute Negotiated Deal Formula API
     * @param params List<NWXC010001PMsg>
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(List<NWXC010001PMsg> params, final ONBATCH_TYPE onBatchType) {

        for (NWXC010001PMsg param : params) {
            execute(param, onBatchType);
        }

    }

    private boolean checkParam(S21ApiMessageMap msgMap) {

        NWXC010001PMsg pMsg = (NWXC010001PMsg) msgMap.getPmsg();

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
        if (!ZYPCommonFunc.hasValue(pMsg.negoDealAmt)) {
            msgMap.addXxMsgIdWithPrm(NWZM1325E, new String[] {PROGRAM_ID, "Negotiated Deal Amount" });
            msgMap.flush();
            return true;
        }

        for (int i = 0; i < pMsg.NWXC010002PMsg.getValidCount(); i++) {

            NWXC010002PMsg line = pMsg.NWXC010002PMsg.no(i);
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
                lineMap.addXxMsgIdWithPrm(NWZM1325E, new String[] {PROGRAM_ID, "Line Qty" });
                lineMap.flush();
                return true;
            }

            if (!ZYPCommonFunc.hasValue(line.invQty)) {
                lineMap.addXxMsgIdWithPrm(NWZM1325E, new String[] {PROGRAM_ID, "Invoiced Qty" });
                lineMap.flush();
                return true;
            }

//            if (!ZYPCommonFunc.hasValue(line.basePrcAmt)) {
//                lineMap.addXxMsgIdWithPrm(NWZM1325E, new String[] {PROGRAM_ID, "Net Amount" });
//                lineMap.flush();
//                return true;
//            }

            if (!ZYPCommonFunc.hasValue(line.xxPrcCloFlg)) {
                lineMap.addXxMsgIdWithPrm(NWZM1325E, new String[] {PROGRAM_ID, "Close Flag" });
                lineMap.flush();
                return true;
            }

            if (!ZYPCommonFunc.hasValue(line.xxNegoFlg)) {
                lineMap.addXxMsgIdWithPrm(NWZM1325E, new String[] {PROGRAM_ID, "Negotiate Flag" });
                lineMap.flush();
                return true;
            }

        }

        if (pMsg.negoDealAmt.getValue().compareTo(BigDecimal.ZERO) < 0) {
            msgMap.addXxMsgIdWithPrm(NWZM1343E, new String[] {PROGRAM_ID, "Negotiated Deal Amount" });
            msgMap.flush();
            return true;
        }
        return false;
    }

    private void calculateNego(S21ApiMessageMap msgMap) {

        NWXC010001PMsg pMsg = (NWXC010001PMsg) msgMap.getPmsg();

        BigDecimal netAmount = BigDecimal.ZERO;
        BigDecimal totalAmt = BigDecimal.ZERO;
        BigDecimal frozenAmt = BigDecimal.ZERO;

        BigDecimal rate = BigDecimal.ZERO;
        BigDecimal discAmt = BigDecimal.ZERO;
        BigDecimal discAmtTotal = BigDecimal.ZERO;
        BigDecimal numerAmt = BigDecimal.ZERO;
        BigDecimal denomAmt = BigDecimal.ZERO;
        BigDecimal baseAmt = BigDecimal.ZERO;
        NWXC010002PMsg line = null;
        NWXC010002PMsg lastLine = null;
        BigDecimal maxValue = null;

        for (int i = 0; i < pMsg.NWXC010002PMsg.getValidCount(); i++) {
            line = pMsg.NWXC010002PMsg.no(i);

            // QC#10738 2016/08/29 Mod Start
            // netAmount = line.basePrcAmt.getValue();
            deleteManualPriceDiscount(line);

            netAmount = getNetAmount(line);
            ZYPEZDItemValueSetter.setValue(line.basePrcAmt, netAmount);
            // QC#10738 2016/08/29 Mod End

            totalAmt = totalAmt.add(netAmount);
            if (line.invQty.getValue().compareTo(BigDecimal.ZERO) > 0 || ZYPConstant.FLG_OFF_N.equals(line.xxNegoFlg.getValue())) {
                frozenAmt = frozenAmt.add(netAmount);
            }
        }

        
        // subtract ShellContractAmount
        // totalAmt = totalAmt.subtract(getTotalShellAmt(pMsg)); // QC#7809
        // totalAmt = totalAmt.add(getTotalShellAmt(pMsg)); //#13369 2016/08/22 Del

        denomAmt = totalAmt.subtract(frozenAmt);
        if (denomAmt.compareTo(BigDecimal.ZERO) == 0) {
            return;
        }

        baseAmt = totalAmt.add(getTotalShellAmt(pMsg)); //#13369 2016/08/22 Add

        // MOD START 2015/12/16 #2039
        //baseAmt = pMsg.negoDealAmt.getValue().subtract(totalAmt);
        //baseAmt = totalAmt.subtract(pMsg.negoDealAmt.getValue()); #13369 2016/08/22 Add
        baseAmt = baseAmt.subtract(pMsg.negoDealAmt.getValue());
        // MOD END 2015/12/15 #2039

        for (int i = 0; i < pMsg.NWXC010002PMsg.getValidCount(); i++) {
            line = pMsg.NWXC010002PMsg.no(i);
            if (line.invQty.getValue().compareTo(BigDecimal.ZERO) > 0 || ZYPConstant.FLG_OFF_N.equals(line.xxNegoFlg.getValue())) {
                continue;
            } else {
                // QC#21651 2017/10/19 Mod Start
                //numerAmt = line.basePrcAmt.getValue().setScale(RATE_DIGIT, BigDecimal.ROUND_HALF_UP);
                ////rate = numerAmt.divide(denomAmt).setScale(RATE_DIGIT, BigDecimal.ROUND_HALF_UP);
                //rate = numerAmt.divide(denomAmt, RATE_DIGIT, BigDecimal.ROUND_HALF_UP);
                //discAmt = editCalcBase(msgMap, line, baseAmt, rate);
                if (baseAmt.equals(denomAmt)) {
                    discAmt = editCalcBase(msgMap, line, line.basePrcAmt.getValue().setScale(RATE_DIGIT, BigDecimal.ROUND_HALF_UP), BigDecimal.ONE);
                } else {
                    numerAmt = line.basePrcAmt.getValue().setScale(RATE_DIGIT, BigDecimal.ROUND_HALF_UP);
                    rate = numerAmt.divide(denomAmt, RATE_DIGIT, BigDecimal.ROUND_HALF_UP);
                    discAmt = editCalcBase(msgMap, line, baseAmt, rate);
                }
                // QC#21651 2017/10/19 Mod End
                // QC#27792 2018/08/27 Add End
                discAmtTotal = discAmtTotal.add(discAmt);
                // lastLine = line;
                if (maxValue == null) {
                    lastLine = line;
                    maxValue = discAmt.abs();
                } else if (discAmt.abs().compareTo(maxValue) > 0) {
                    lastLine = line;
                    maxValue = discAmt.abs();
                }
                // QC#27792 2018/08/27 Add End
            }
        }

        if (discAmtTotal.compareTo(baseAmt) != 0) {
            BigDecimal fraction = baseAmt.subtract(discAmtTotal);

            // find first row
            // 2018/01/18 S21_NA#23105 Mod Start
//            for (int i = 0; i < pMsg.NWXC010002PMsg.getValidCount(); i++) {
            for (int i = pMsg.NWXC010002PMsg.getValidCount() - 1; i >= 0; i--) {
            // 2018/01/18 S21_NA#23105 Mod End
                line = pMsg.NWXC010002PMsg.no(i);
                if (line.invQty.getValue().compareTo(BigDecimal.ZERO) == 0
                        && ZYPConstant.FLG_ON_Y.equals(line.xxNegoFlg.getValue())
                        && line.ordQty.getValue().abs().compareTo(BigDecimal.ONE) == 0) {
                    adjustFraction(line, fraction);
                    fraction = BigDecimal.ZERO;
                    break;
                }
            }

// QC#9694 2016/07/08 Mod Start
//            if (fraction.compareTo(BigDecimal.ZERO) != 0) {
//                msgMap.addXxMsgIdWithPrm(NWZM1325E, new String[] {"Negotiation Price Calcuration" });
//                msgMap.flush();
//            }
            adjustFractionForLastLine(pMsg, lastLine, fraction);
// QC#9694 2016/07/08 Mod End
        }

    }

    private BigDecimal getTotalShellAmt(NWXC010001PMsg pMsg) {
        if (!ZYPCommonFunc.hasValue(pMsg.trxHdrNum)) {
            return BigDecimal.ZERO;
        }
        final Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmParam.put("trxHdrNum", pMsg.trxHdrNum.getValue());
        ssmParam.put("billWithEquipFlg", ZYPConstant.FLG_ON_Y);
        BigDecimal totalShellAmt = (BigDecimal) this.ssmBatchClient.queryObject("getTotalShellAmount", ssmParam);
        if (totalShellAmt == null) {
            return BigDecimal.ZERO;
        }
        return totalShellAmt;
    }

    private BigDecimal editCalcBase(S21ApiMessageMap msgMap, NWXC010002PMsg line, BigDecimal baseAmt, BigDecimal rate) {

        NWXC010001PMsg pMsg = (NWXC010001PMsg) msgMap.getPmsg();
        NWXC010003PMsg base = null;
        NWXC010003PMsg desc = null;

        for (int i = 0; i < line.NWXC010003PMsg.getValidCount(); i++) {
            NWXC010003PMsg calcBase = line.NWXC010003PMsg.no(i);
            if (PRC_DTL_GRP.BASE_PRICE.equals(calcBase.prcDtlGrpCd.getValue())) {
                base = calcBase;
            } else {
                if (line.specCondPrcPk.getValue().compareTo(calcBase.specCondPrcPk.getValue()) == 0) {
                    desc = calcBase;
                } else {
                    if (!ZYPCommonFunc.hasValue(calcBase.prcCondTpCd)) {
                        msgMap.addXxMsgIdWithPrm(NWZM1943E, null);
                        msgMap.flush();
                        return BigDecimal.ZERO;
                    }
                }
            }
        }
        if (base == null) {
            return BigDecimal.ZERO;
        }
        if (desc == null) {
            desc = line.NWXC010003PMsg.no(line.NWXC010003PMsg.getValidCount());
        }

        int scale = getDealCcyDigit(pMsg.glblCmpyCd.getValue(), base.autoPrcCcyCd.getValue());
        BigDecimal discAmt = baseAmt.multiply(rate).setScale(scale, BigDecimal.ROUND_HALF_UP);
        BigDecimal discPrc = discAmt.divide(line.ordQty.getValue(), scale, BigDecimal.ROUND_HALF_UP);

        ZYPEZDItemValueSetter.setValue(desc.trxLineNum, line.trxLineNum);
        ZYPEZDItemValueSetter.setValue(desc.trxLineSubNum, line.trxLineSubNum);
        ZYPEZDItemValueSetter.setValue(desc.configCatgCd, line.configCatgCd);
        desc.prcCondTpCd.clear();
        desc.prcCondTpDescTxt.clear();
        ZYPEZDItemValueSetter.setValue(desc.prcDtlGrpCd, PRC_DTL_GRP.DISCOUNT);
        desc.prcJrnlGrpCd.clear();
        ZYPEZDItemValueSetter.setValue(desc.prcCatgCd, base.prcCatgCd);
        ZYPEZDItemValueSetter.setValue(desc.prcCondManEntryFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(desc.prcCondManAddFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(desc.prcCondManDelFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(desc.pkgUomCd, base.pkgUomCd);
        ZYPEZDItemValueSetter.setValue(desc.prcCondUnitCd, PRC_COND_UNIT.AMT);
        desc.prcCalcMethCd.clear();
        ZYPEZDItemValueSetter.setValue(desc.autoPrcCcyCd, base.autoPrcCcyCd);
        ZYPEZDItemValueSetter.setValue(desc.autoPrcAmtRate, discPrc);
        desc.maxPrcAmtRate.clear();
        desc.minPrcAmtRate.clear();
        desc.manPrcAmtRate.clear();
        ZYPEZDItemValueSetter.setValue(desc.calcPrcAmtRate, discPrc.multiply(line.ordQty.getValue()));
        ZYPEZDItemValueSetter.setValue(desc.unitPrcAmt, discPrc);
        desc.dsMdsePrcPk.clear();
        ZYPEZDItemValueSetter.setValue(desc.specCondPrcPk, line.specCondPrcPk);
        desc.frtPerWtPk.clear();
        desc.ordPrcCalcBasePk.clear();
        // QC#9700  2018/09/03 Add Start
        ZYPEZDItemValueSetter.setValue(desc.prcRuleApplyFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(desc.prcRulePrcdPk, pMsg.prcRulePrcdPk);
        // QC#9700  2018/09/03 Add End

        line.NWXC010003PMsg.setValidCount(line.NWXC010003PMsg.getValidCount() + 1);

        return discPrc.multiply(line.ordQty.getValue());
    }

    private void adjustFraction(NWXC010002PMsg line, BigDecimal fraction) {
        // find discount row
        for (int i = 0; i < line.NWXC010003PMsg.getValidCount(); i++) {
            NWXC010003PMsg calcBase = line.NWXC010003PMsg.no(i);
            if (PRC_DTL_GRP.DISCOUNT.equals(calcBase.prcDtlGrpCd.getValue())
                    && line.specCondPrcPk.getValue().compareTo(calcBase.specCondPrcPk.getValue()) == 0) {
                fraction = fraction.add(calcBase.calcPrcAmtRate.getValue());
                ZYPEZDItemValueSetter.setValue(calcBase.autoPrcAmtRate, fraction);
                ZYPEZDItemValueSetter.setValue(calcBase.calcPrcAmtRate, fraction);
                ZYPEZDItemValueSetter.setValue(calcBase.unitPrcAmt, fraction);
                break;
            }
        }

    }

 // QC#9694 2016/07/08 Add 
    private void adjustFractionForLastLine(NWXC010001PMsg pMsg, NWXC010002PMsg targetline, BigDecimal fraction){
        // find Last Row
        NWXC010003PMsg calcBase = null;
        NWXC010003PMsg base = null;
        if (targetline == null) {
            return;
        }
        for (int i = 0; i < targetline.NWXC010003PMsg.getValidCount(); i++) {
            calcBase = targetline.NWXC010003PMsg.no(i);
            if (PRC_DTL_GRP.BASE_PRICE.equals(calcBase.prcDtlGrpCd.getValue())) {
                base = calcBase;
            }
        }
        if (base == null) {
            return;
        }
        NWXC010003PMsg fractionCalcBase = targetline.NWXC010003PMsg.no(targetline.NWXC010003PMsg.getValidCount());
        
        ZYPEZDItemValueSetter.setValue(fractionCalcBase.trxLineNum, targetline.trxLineNum.getValue());
        ZYPEZDItemValueSetter.setValue(fractionCalcBase.trxLineSubNum, targetline.trxLineSubNum.getValue());
        ZYPEZDItemValueSetter.setValue(fractionCalcBase.configCatgCd, targetline.configCatgCd.getValue());
        ZYPEZDItemValueSetter.setValue(fractionCalcBase.prcDtlGrpCd, PRC_DTL_GRP.ROUNDING_FACTOR_0);
        ZYPEZDItemValueSetter.setValue(fractionCalcBase.prcCatgCd, base.prcCatgCd);
        ZYPEZDItemValueSetter.setValue(fractionCalcBase.prcCondManEntryFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(fractionCalcBase.prcCondManAddFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(fractionCalcBase.prcCondManDelFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(fractionCalcBase.pkgUomCd,  base.pkgUomCd);
        ZYPEZDItemValueSetter.setValue(fractionCalcBase.prcCondUnitCd, PRC_COND_UNIT.AMT);
        ZYPEZDItemValueSetter.setValue(fractionCalcBase.autoPrcCcyCd, base.autoPrcCcyCd);
        ZYPEZDItemValueSetter.setValue(fractionCalcBase.autoPrcAmtRate, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(fractionCalcBase.calcPrcAmtRate, fraction);
        ZYPEZDItemValueSetter.setValue(fractionCalcBase.unitPrcAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(fractionCalcBase.specCondPrcPk, targetline.specCondPrcPk);
        // QC#9700  2018/09/03 Add Start
        ZYPEZDItemValueSetter.setValue(fractionCalcBase.prcRuleApplyFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(fractionCalcBase.prcRulePrcdPk, pMsg.prcRulePrcdPk);
        // QC#9700  2018/09/03 Add End

        targetline.NWXC010003PMsg.setValidCount(targetline.NWXC010003PMsg.getValidCount() + 1);
    }

    private int getDealCcyDigit(String glblCmpyCd, String dealCcyCd) {
        CCYTMsg ccyTMsg = new CCYTMsg();
        ZYPEZDItemValueSetter.setValue(ccyTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(ccyTMsg.ccyCd, dealCcyCd);
        ccyTMsg = (CCYTMsg) S21CacheTBLAccessor.findByKey(ccyTMsg);
        if (ccyTMsg != null) {
            return ccyTMsg.aftDeclPntDigitNum.getValueInt();
        }
        return 0;
    }

    // QC#10738 2016/08/29 Mod Start
    private void deleteManualPriceDiscount(NWXC010002PMsg line){
        List<Integer> delList = new ArrayList<Integer>();
        NWXC010003PMsg calcBase = null;
        for (int i = 0; i < line.NWXC010003PMsg.getValidCount(); i++) {
            calcBase = line.NWXC010003PMsg.no(i);
            if (line.invQty.getValue().compareTo(BigDecimal.ZERO) == 0 && ZYPConstant.FLG_ON_Y.equals(line.xxNegoFlg.getValue())) {
                if (PRC_COND_TP.MANUAL_PRICE.equals(calcBase.prcCondTpCd.getValue())) {
                    delList.add(i);
                }
            }
        }
        if (delList.size() > 0) {
            ZYPTableUtil.deleteRows(line.NWXC010003PMsg, delList);
            delList.clear();
        }
    }

    private BigDecimal getNetAmount(NWXC010002PMsg line) {
        BigDecimal rtnVal = BigDecimal.ZERO;
        NWXC010003PMsg calcBase = null;
        for (int i = 0; i < line.NWXC010003PMsg.getValidCount(); i++) {
            calcBase = line.NWXC010003PMsg.no(i);
            if (PRC_DTL_GRP.BASE_PRICE.equals(calcBase.prcDtlGrpCd.getValue())) {
                rtnVal = add(rtnVal, calcBase.calcPrcAmtRate.getValue());
            } else if (PRC_DTL_GRP.DISCOUNT.equals(calcBase.prcDtlGrpCd.getValue())) {
                rtnVal = subtract(rtnVal, calcBase.calcPrcAmtRate.getValue());
            } else if (PRC_DTL_GRP.ROUNDING_FACTOR_0.equals(calcBase.prcDtlGrpCd.getValue())) {
                rtnVal = subtract(rtnVal, calcBase.calcPrcAmtRate.getValue());
            }
        }
        return rtnVal;
    }

    private BigDecimal add(BigDecimal num1, BigDecimal num2) {
        if (num1 == null) {
            num1 = BigDecimal.ZERO;
        }
        if (num2 == null) {
            num2 = BigDecimal.ZERO;
        }
        return num1.add(num2);
    }

    private BigDecimal subtract(BigDecimal num1, BigDecimal num2) {
        if (num1 == null) {
            num1 = BigDecimal.ZERO;
        }
        if (num2 == null) {
            num2 = BigDecimal.ZERO;
        }
        return num1.subtract(num2);
    }
    // QC#10738 2016/08/29 Mod End
}
