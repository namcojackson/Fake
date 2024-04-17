/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1770.common;

import static business.blap.NWAL1770.constant.NWAL1770Constant.IDX_14;
import static business.blap.NWAL1770.constant.NWAL1770Constant.IDX_3;

import java.math.BigDecimal;
import java.util.Map;

import business.blap.NWAL1770.NWAL1770CMsg;
import business.blap.NWAL1770.NWAL1770QueryForCustomer;
import business.blap.NWAL1770.NWAL1770QueryForSearch;
import business.blap.NWAL1770.NWAL1770_BCMsg;
import business.blap.NWAL1770.NWAL1770_CCMsg;
import business.blap.NWAL1770.NWAL1770_ECMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_DTL_GRP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SPLY_QUOTE_STS;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/25   Fujitsu         T.Yoshida       Create          N/A
 * 2016/08/10   Fujitsu         Y.Kanefusa      Update          S21_NA#9694
 * 2018/05/21   Fujitsu         Y.Kanefusa      Update          S21_NA#21841
 * 2018/06/05   Fujitsu         Y.Kanefusa      Update          S21_NA#22965
 * 2018/08/03   Fujitsu         Y.Kanefusa      Update          S21_NA#27479
 * </pre>
 */
public class NWAL1770CommonLogicForSearch {

    /**
     * Get Additional Data
     * @param bizMsg NWAL1770CMsg
     */
    public static void getSplyQuoteAddlData(NWAL1770CMsg bizMsg) {

        S21SsmEZDResult ssmResult = NWAL1770QueryForSearch.getInstance().getSplyQuoteAddlData(bizMsg);

        if (ssmResult.isCodeNormal()) {
            // Calculate Total Weight
            for (int i = 0; i < bizMsg.C.getValidCount(); i++) {
                NWAL1770_CCMsg addlLineMsg = bizMsg.C.no(i);
                BigDecimal ordQty = addlLineMsg.ordQty_C.getValue();
                BigDecimal unitNetWt = addlLineMsg.unitNetWt_C.getValue();
                ZYPEZDItemValueSetter.setValue(addlLineMsg.xxTotUnitNetWt_C, ordQty.multiply(unitNetWt));
            }
        }

        if (ZYPCommonFunc.hasValue(bizMsg.xxTsDsp19Txt_SV)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxTsDsp19Txt_SV, ZYPDateUtil.formatEzd14ToDisp(bizMsg.xxTsDsp19Txt_SV.getValue().substring(0, IDX_14)));
        }

        if (ZYPCommonFunc.hasValue(bizMsg.xxTsDsp19Txt_SB)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxTsDsp19Txt_SB, ZYPDateUtil.formatEzd14ToDisp(bizMsg.xxTsDsp19Txt_SB.getValue().substring(0, IDX_14)));
        }
    }

    /**
     * Get Location Number
     * @param bizMsg NWAL1770CMsg
     */
    @SuppressWarnings("unchecked")
    public static void getLocNum(NWAL1770CMsg bizMsg) {

        S21SsmEZDResult ssmResult = NWAL1770QueryForCustomer.getInstance().getShipToCustAddr(bizMsg, bizMsg.shipToCustCd.getValue());

        if (!ssmResult.isCodeNotFound()) {
            Map<String, String> shipToCustAddrInfo = (Map<String, String>) ssmResult.getResultObject();
            ZYPEZDItemValueSetter.setValue(bizMsg.locNum, shipToCustAddrInfo.get("LOC_NUM"));
        }
    }

    /**
     * Create Pulldown
     * @param bizMsg NWAL1770CMsg
     */
    public static void createPulldown(NWAL1770CMsg bizMsg) {

        NWAL1770CommonLogic.createPulldown(bizMsg);
        NWAL1770CommonLogic.createRsnCdPulldown(bizMsg);
        NWAL1770CommonLogic.createShpgSvcLvlPulldown(bizMsg);
        NWAL1770CommonLogic.createLineCatgPulldown(bizMsg, bizMsg.splyQuoteDt.getValue());

        // UOM Pulldown
        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
            final NWAL1770_BCMsg itemLineMsg = bizMsg.B.no(i);
            NWAL1770CommonLogic.createPkgUomPullDown(bizMsg, itemLineMsg);
        }

        // Confirmation Output Pulldown
        NWAL1770CommonLogicForReport.createConfOutputPulldown(bizMsg);
    }

    /**
     * Set Price
     * @param bizMsg NWAL1770CMsg
     */
    public static void setPrice(NWAL1770CMsg bizMsg) {

        setLinePrice(bizMsg);
        setSummaryPrice(bizMsg);
    }

    /**
     * Set Line Price
     * @param bizMsg NWAL1770CMsg
     */
    private static void setLinePrice(NWAL1770CMsg bizMsg) {

        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
            NWAL1770_BCMsg itemLineMsg = bizMsg.B.no(i);
            String targetLineNum = itemLineMsg.splyQuoteDtlLineNum_B.getValue();

            if (ZYPCommonFunc.hasValue(itemLineMsg.dplyQuoteLineSubNum_B)) {
                ZYPEZDItemValueSetter.setValue(itemLineMsg.dealPrcListPrcAmt_B, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(itemLineMsg.xxTotDiscAmt_B, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(itemLineMsg.xxDtlDiscAmt_B, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(itemLineMsg.xxSubTotCalcPrcAmt_B, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(itemLineMsg.xxTotFrtAmt_B, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(itemLineMsg.xxTotTaxAmt_B, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(itemLineMsg.xxTotAmt_B, BigDecimal.ZERO);
                continue;
            }

            BigDecimal discAmt = BigDecimal.ZERO;
            BigDecimal discUnitAmt = BigDecimal.ZERO; // QC#54951 2019/12/06 Add
            BigDecimal frtChrgAmt = BigDecimal.ZERO;
            BigDecimal taxAmt = BigDecimal.ZERO;

            for (int j = 0; j < bizMsg.E.getValidCount(); j++) {
                NWAL1770_ECMsg calcBaseMsg = bizMsg.E.no(j);
                String prcDtlGrpCd = calcBaseMsg.prcDtlGrpCd_E.getValue();
                BigDecimal calcPrcAmt = calcBaseMsg.calcPrcAmtRate_E.getValue();

                if (!ZYPCommonFunc.hasValue(calcPrcAmt)) {
                    continue;
                }
                // QC#22965 2018/06/05 Add Start
                if (S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, calcBaseMsg.prcCondManDelFlg_E.getValue())) {
                    continue;
                }
                // QC#22965 2018/06/05 Add End
                // QC#54951 2019/12/06 Add Start
                if (S21StringUtil.isEquals(ZYPConstant.FLG_OFF_N, calcBaseMsg.prcRuleApplyFlg_E.getValue())) {
                    continue;
                }
                // QC#54951 2019/12/06 Add End

                if (targetLineNum.equals(calcBaseMsg.splyQuoteDtlLineNum_E.getValue())) {
                    // QC#6480 2016/06/16 Mod
                    // if (PRC_DTL_GRP.DISCOUNT.equals(prcDtlGrpCd) || PRC_DTL_GRP.SPECIAL_PRICE.equals(prcDtlGrpCd) || PRC_DTL_GRP.NET_DISCOUT.equals(prcDtlGrpCd)) {
                    if (!ZYPCommonFunc.hasValue(calcBaseMsg.prcCondTpCd_E) && PRC_DTL_GRP.DISCOUNT.equals(prcDtlGrpCd) || PRC_DTL_GRP.SPECIAL_PRICE.equals(prcDtlGrpCd) || PRC_DTL_GRP.NET_DISCOUT.equals(prcDtlGrpCd)) {
                        discAmt = discAmt.add(calcPrcAmt);
                        // QC#54951 2019/12/06 Add Start
                        // ZYPEZDItemValueSetter.setValue(itemLineMsg.xxTotDiscAmt_B, calcBaseMsg.unitPrcAmt_E);
                        discUnitAmt = discUnitAmt.add(calcBaseMsg.unitPrcAmt_E.getValue());
                        // QC#54951 2019/12/06 Add End
                    } else if (PRC_DTL_GRP.FREIGHT.equals(prcDtlGrpCd) || PRC_DTL_GRP.SPECIAL_CHARGE.equals(prcDtlGrpCd)) {
                        frtChrgAmt = frtChrgAmt.add(calcPrcAmt);
                        // QC#9694 2016/08/10 Mod Start
                    } else if (PRC_DTL_GRP.ROUNDING_FACTOR_0.equals(prcDtlGrpCd)) {
                        discAmt = discAmt.add(calcPrcAmt);
                    } else if (PRC_DTL_GRP.ROUNDING_FACTOR_1.equals(prcDtlGrpCd)) {
                        frtChrgAmt = frtChrgAmt.add(calcPrcAmt);
                        // QC#9694 2016/08/10 Mod End
                    } else if (PRC_DTL_GRP.TAX.equals(prcDtlGrpCd)) {
                        taxAmt = taxAmt.add(calcPrcAmt);
                    // QC#21841 2018/05/21 Add Start
                    } else if (PRC_DTL_GRP.HANDLING_FEE.equals(prcDtlGrpCd) || PRC_DTL_GRP.FUEL_SURCHARGE.equals(prcDtlGrpCd) || PRC_DTL_GRP.SHIPPING_FEE.equals(prcDtlGrpCd)) {
                        frtChrgAmt = frtChrgAmt.add(calcPrcAmt);
                    } else if (PRC_DTL_GRP.RESTOCKING_FEE.equals(prcDtlGrpCd)) {  // QC#27479 2018/08/03 Add
                        frtChrgAmt = frtChrgAmt.add(calcPrcAmt);
                    } else if (PRC_DTL_GRP.ROUNDING_FACTOR_2.equals(prcDtlGrpCd)) {
                        frtChrgAmt = frtChrgAmt.add(calcPrcAmt);
                    // QC#21841 2018/05/21 Add End
                    }
                }
            }
            // QC#54951 2019/12/06 Add Start
            ZYPEZDItemValueSetter.setValue(itemLineMsg.xxTotDiscAmt_B, discUnitAmt);
            // QC#54951 2019/12/06 Add End

            if (ZYPCommonFunc.hasValue(itemLineMsg.xxTotDiscAmt_B)) {
                ZYPEZDItemValueSetter.setValue(itemLineMsg.xxDtlDiscAmt_B, itemLineMsg.dealSplyQuoteDtlSlsAmt_B.getValue().subtract(itemLineMsg.xxTotDiscAmt_B.getValue()));
            } else {
                ZYPEZDItemValueSetter.setValue(itemLineMsg.xxTotDiscAmt_B, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(itemLineMsg.xxDtlDiscAmt_B, itemLineMsg.dealSplyQuoteDtlSlsAmt_B);
            }

            ZYPEZDItemValueSetter.setValue(itemLineMsg.xxTotFrtAmt_B, frtChrgAmt);
            ZYPEZDItemValueSetter.setValue(itemLineMsg.xxTotTaxAmt_B, taxAmt);

            // Set Sub Total
            BigDecimal ordQty = itemLineMsg.ordQty_B.getValue();
            BigDecimal subTotCalcPrcAmt = ordQty.multiply(itemLineMsg.dealSplyQuoteDtlSlsAmt_B.getValue());
            ZYPEZDItemValueSetter.setValue(itemLineMsg.xxSubTotCalcPrcAmt_B, subTotCalcPrcAmt.subtract(discAmt));

            // Set Total
            BigDecimal totAmt = itemLineMsg.xxSubTotCalcPrcAmt_B.getValue();
            totAmt = totAmt.add(itemLineMsg.xxTotFrtAmt_B.getValue());
            totAmt = totAmt.add(itemLineMsg.xxTotTaxAmt_B.getValue());
            ZYPEZDItemValueSetter.setValue(itemLineMsg.xxTotAmt_B, totAmt);
        }
    }

    /**
     * Set Summary Price
     * @param bizMsg NWAL1770CMsg
     */
    private static void setSummaryPrice(NWAL1770CMsg bizMsg) {

        BigDecimal totSubAmt = BigDecimal.ZERO;
        BigDecimal totTaxAmt = BigDecimal.ZERO;
        BigDecimal totFrtChrgAmt = BigDecimal.ZERO;
        BigDecimal totAmt = BigDecimal.ZERO;

        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
            NWAL1770_BCMsg itemLineMsg = bizMsg.B.no(i);

            // Skip Cancel Line
            if (SPLY_QUOTE_STS.CANCELLED.equals(itemLineMsg.splyQuoteLineStsCd_B.getValue())) {
                continue;
            }

            // Skip Set Component
            String[] xxLineNumArray = itemLineMsg.xxLineNum_B.getValue().split("\\.");
            if (xxLineNumArray.length == IDX_3 && BigDecimal.ZERO.compareTo(new BigDecimal(xxLineNumArray[2])) != 0) {
                continue;
            }

            totSubAmt = totSubAmt.add(itemLineMsg.xxSubTotCalcPrcAmt_B.getValue());
            totTaxAmt = totTaxAmt.add(itemLineMsg.xxTotTaxAmt_B.getValue());
            totFrtChrgAmt = totFrtChrgAmt.add(itemLineMsg.xxTotFrtAmt_B.getValue());
            totAmt = totAmt.add(itemLineMsg.xxTotAmt_B.getValue());
        }

        // Set Header Amount
        ZYPEZDItemValueSetter.setValue(bizMsg.xxSubTotCalcPrcAmt, totSubAmt);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxTotChrgPrcAmt, totFrtChrgAmt);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxTotTaxAmt, totTaxAmt);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxTotAmt, totAmt);
    }
}
