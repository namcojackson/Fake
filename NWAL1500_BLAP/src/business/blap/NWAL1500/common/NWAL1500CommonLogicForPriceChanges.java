/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NWAL1500.common;

import static business.blap.NWAL1500.constant.NWAL1500Constant.LINE_STS_NM_CANCELLED;
import static business.blap.NWAL1500.constant.NWAL1500Constant.NWAL1660_MODE_REFERENCE;
import static business.blap.NWAL1500.constant.NWAL1500Constant.NWAL1660_MODE_REGIST;
import static business.blap.NWAL1500.constant.NWAL1500Constant.TOT_WT_SCALE;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NWAL1500.NWAL1500CMsg;
import business.blap.NWAL1500.NWAL1500QueryForPriceChanges;
import business.blap.NWAL1500.NWAL1500QueryForSaveSubmit;
import business.blap.NWAL1500.NWAL1500SMsg;
import business.blap.NWAL1500.NWAL1500_ACMsg;
import business.blap.NWAL1500.NWAL1500_ACMsgArray;
import business.blap.NWAL1500.NWAL1500_BCMsg;
import business.blap.NWAL1500.NWAL1500_BSMsg;
import business.blap.NWAL1500.NWAL1500_CCMsg;
import business.blap.NWAL1500.NWAL1500_CCMsgArray;
import business.blap.NWAL1500.NWAL1500_DCMsg;
import business.blap.NWAL1500.NWAL1500_DCMsgArray;
import business.blap.NWAL1500.NWAL1500_DSMsg;
import business.blap.NWAL1500.NWAL1500_GCMsg;
import business.blap.NWAL1500.NWAL1500_GCMsgArray;
import business.blap.NWAL1500.NWAL1500_HCMsg;
import business.blap.NWAL1500.NWAL1500_HCMsgArray;
import business.blap.NWAL1500.NWAL1500_ISMsg;
import business.blap.NWAL1500.NWAL1500_ISMsgArray;
import business.blap.NWAL1500.NWAL1500_NCMsg;
import business.blap.NWAL1500.NWAL1500_NCMsgArray;
import business.blap.NWAL1500.constant.NWAL1500Constant;
import business.db.GLBL_CMPYTMsg;
import business.db.PRC_CATGTMsg;
import business.db.PRC_CATGTMsgArray;
import business.db.TOCTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_PMT_METH;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LINE_BIZ_ROLE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_COND_UNIT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_DTL_GRP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTRN_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/04   Fujitsu         T.ishii         Create          n/a
 * 2016/05/10   Fujitsu         S.Takami        Update          S21_NA#7706
 * 2017/11/02   Fujitsu         T.Aoi           Update          S21_NA#20146(Sol#92)
 * 2018/02/06   Fujitsu         S.Takami        Update          S21_NA#19808
 * 2018/03/15   Fujitsu         S.Takami        Update          S21_NA#19808-2(bizMsg.I->glblMsg.I, without any comments)
 * 2018/04/11   Fujitsu         Y.Kanefusa      Update          S21_NA#22965
 * 2018/04/20   Fujitsu         Y.Kanefusa      Update          S21_NA#26259
 * 2018/05/21   Fujitsu         Y.Kanefusa      Update          S21_NA#21841
 * 2018/08/03   Fujitsu         Y.Kanefusa      Update          S21_NA#27479
 * </pre>
 */
public class NWAL1500CommonLogicForPriceChanges {

    /**
     * setScreenItems.
     * @param bizMsg NWAL1500CMsg
     * @param bMsg NWAL1500_BCMsg
     * @param iMsgArray NWAL1500_ISMsgArray
     */
    public static void setScreenItems(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, NWAL1500_BSMsg bMsg, NWAL1500_ISMsgArray iMsgArray) { // 2018/02/06 S21_NA#19808

        // summary information
//        ZYPEZDItemValueSetter.setValue(bMsg.entCpoDtlDealSlsAmt_LL, bizMsg.xxUnitNetPrcAmt_N);
        ZYPEZDItemValueSetter.setValue(bMsg.cpoDtlDealGrsAmt_LL, bizMsg.xxUnitGrsPrcAmt_N);
        ZYPEZDItemValueSetter.setValue(bMsg.cpoDtlDealTaxAmt_LL, bizMsg.xxTotTaxAmt_N);
        ZYPEZDItemValueSetter.setValue(bMsg.xxLineTotPrcAmt_LL, bizMsg.xxTotAmt_N);

        ZYPEZDItemValueSetter.setValue(bMsg.entDealNetUnitPrcAmt_LL, bizMsg.xxUnitNetPrcAmt_N);
        ZYPEZDItemValueSetter.setValue(bMsg.lineDealSubTotAmt_LL,    bizMsg.xxTotNetPrcAmt_N);
        ZYPEZDItemValueSetter.setValue(bMsg.lineDealChrgAmt_LL,      bizMsg.xxTotChrgPrcAmt_N);
        ZYPEZDItemValueSetter.setValue(bMsg.lineDealTaxAmt_LL,       bizMsg.xxTotTaxAmt_N);
        ZYPEZDItemValueSetter.setValue(bMsg.lineDealTotAmt_LL,       bizMsg.xxTotAmt_N);

        // 2016/05/10 S21_NA#7706 Add Start
        ZYPEZDItemValueSetter.setValue(bMsg.dealSvcRevTrnsfAmt_LL, bizMsg.dealSvcRevTrnsfAmt_N);
        ZYPEZDItemValueSetter.setValue(bMsg.dealSvcCostTrnsfAmt_LL, bizMsg.dealSvcCostTrnsfAmt_N);
        ZYPEZDItemValueSetter.setValue(bMsg.dealManFlAdjAmt_LL, bizMsg.dealManFlAdjAmt_N);
        ZYPEZDItemValueSetter.setValue(bMsg.dealManRepRevAdjAmt_LL, bizMsg.dealManRepRevAdjAmt_N);
        calcFuncAmtForPriceChange(bizMsg, bMsg);
        // 2016/05/10 S21_NA#7706 Add End

        String configCatgCd = CONFIG_CATG.OUTBOUND;
        String cpoOrdNum = bizMsg.cpoOrdNum.getValue();
        String cpoDtlLineNum = bMsg.cpoDtlLineNum_LL.getValue();
        String cpoDtlLineSubNum = bMsg.cpoDtlLineSubNum_LL.getValue();
        // delete, add
        setPriceCondition(glblMsg.I, bizMsg.N, configCatgCd, cpoOrdNum, cpoDtlLineNum, cpoDtlLineSubNum);
    }

    /**
     * setScreenItems.
     * @param bizMsg NWAL1500CMsg
     * @param dMsg NWAL1500_DCMsg
     * @param iMsgArray NWAL1500_ISMsgArray
     */
    public static void setScreenItems(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, NWAL1500_DSMsg dMsg, NWAL1500_ISMsgArray iMsgArray) { // 2018/02/06 S21_NA#19808

        // summary information
//        ZYPEZDItemValueSetter.setValue(dMsg.entDealNetUnitPrcAmt_RL, bizMsg.xxUnitNetPrcAmt_N);
        ZYPEZDItemValueSetter.setValue(dMsg.cpoDtlDealGrsAmt_RL, bizMsg.xxUnitGrsPrcAmt_N);
        ZYPEZDItemValueSetter.setValue(dMsg.cpoDtlDealTaxAmt_RL, bizMsg.xxTotTaxAmt_N);
        ZYPEZDItemValueSetter.setValue(dMsg.xxLineTotPrcAmt_RL, bizMsg.xxTotAmt_N);

        ZYPEZDItemValueSetter.setValue(dMsg.entDealNetUnitPrcAmt_RL, bizMsg.xxUnitNetPrcAmt_N);
        ZYPEZDItemValueSetter.setValue(dMsg.lineDealSubTotAmt_RL,    bizMsg.xxTotNetPrcAmt_N);
        ZYPEZDItemValueSetter.setValue(dMsg.lineDealChrgAmt_RL,      bizMsg.xxTotChrgPrcAmt_N);
        ZYPEZDItemValueSetter.setValue(dMsg.lineDealTaxAmt_RL,       bizMsg.xxTotTaxAmt_N);
        ZYPEZDItemValueSetter.setValue(dMsg.lineDealTotAmt_RL,       bizMsg.xxTotAmt_N);

        // 2016/05/10 S21_NA#7706 Add Start
        ZYPEZDItemValueSetter.setValue(dMsg.dealSvcRevTrnsfAmt_RL, bizMsg.dealSvcRevTrnsfAmt_N);
        ZYPEZDItemValueSetter.setValue(dMsg.dealSvcCostTrnsfAmt_RL, bizMsg.dealSvcCostTrnsfAmt_N);
        ZYPEZDItemValueSetter.setValue(dMsg.dealManFlAdjAmt_RL, bizMsg.dealManFlAdjAmt_N);
        ZYPEZDItemValueSetter.setValue(dMsg.dealManRepRevAdjAmt_RL, bizMsg.dealManRepRevAdjAmt_N);
        calcFuncAmtForPriceChange(bizMsg, dMsg);
        // 2016/05/10 S21_NA#7706 Add End

        String configCatgCd = CONFIG_CATG.INBOUND;
        String cpoOrdNum = bizMsg.cpoOrdNum.getValue();
        String cpoDtlLineNum = dMsg.cpoDtlLineNum_RL.getValue();
        String cpoDtlLineSubNum = dMsg.cpoDtlLineSubNum_RL.getValue();
        // delete, add
        setPriceCondition(glblMsg.I, bizMsg.N, configCatgCd, cpoOrdNum, cpoDtlLineNum, cpoDtlLineSubNum);
    }

    // QC#22965 2018/04/11 Add Start
    public static void setScreenItemsForHeader(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, NWAL1500_ISMsgArray sourcePriceConditionList, String configCatgCd) {

        String trxLineNum = null;
        String trxLineSubNum = null;
        BigDecimal invoicedAmt = BigDecimal.ZERO;
        String ordLineSts = bizMsg.varCharConstVal_TB.getValue();
        if (CONFIG_CATG.OUTBOUND.equals(configCatgCd)) {
            for (int idx = 0; idx < glblMsg.B.getValidCount(); idx++) {
                NWAL1500_BSMsg lineMsg = glblMsg.B.no(idx);
                if (BigDecimal.ZERO.compareTo(lineMsg.invQty_LL.getValue()) != 0) {
                    continue;
                }
                if (ordLineSts.equals(lineMsg.ordLineStsDescTxt_LL.getValue()) 
                        || ORD_LINE_STS.CANCELLED.equals(lineMsg.ordLineStsCd_LL.getValue())) {
                    continue;
                }
                String xxLineNum = lineMsg.xxLineNum_LL.getValue();
                String[] xxLineNumArray = xxLineNum.split("\\.");
                if (xxLineNumArray.length == 3 && BigDecimal.ZERO.compareTo(new BigDecimal(xxLineNumArray[2])) != 0) {
                    continue;
                }
                trxLineNum = lineMsg.cpoDtlLineNum_LL.getValue();
                trxLineSubNum = lineMsg.cpoDtlLineSubNum_LL.getValue();
                break;
            }
        } else {
            for (int idx = 0; idx < glblMsg.D.getValidCount(); idx++) {
                NWAL1500_DSMsg rmaLineMsg = glblMsg.D.no(idx);
                if (BigDecimal.ZERO.compareTo(rmaLineMsg.rtrnQty_RL.getValue()) != 0) {
                    continue;
                }
                if (ordLineSts.equals(rmaLineMsg.rtrnLineStsDescTxt_RL.getValue()) 
                        || RTRN_LINE_STS.CANCELLED.equals(rmaLineMsg.ordLineStsCd_RL.getValue()) 
                        || RTRN_LINE_STS.CLOSED.equals(rmaLineMsg.ordLineStsCd_RL.getValue())) {
                    continue;
                }
                String xxLineNum = rmaLineMsg.xxLineNum_RL.getValue();
                String[] xxLineNumArray = xxLineNum.split("\\.");
                if (xxLineNumArray.length == 3 && BigDecimal.ZERO.compareTo(new BigDecimal(xxLineNumArray[2])) != 0) {
                    continue;
                }
                trxLineNum = rmaLineMsg.cpoDtlLineNum_RL.getValue();
                trxLineSubNum = rmaLineMsg.cpoDtlLineSubNum_RL.getValue();
                break;
            }
        }
        if (trxLineNum == null) {
            return;
        }
        for (int n = 0; n < bizMsg.N.getValidCount(); n++) {
            invoicedAmt = BigDecimal.ZERO;
            NWAL1500_NCMsg sourcePriceCondition = bizMsg.N.no(n);
            for (int i = 0; i < glblMsg.I.getValidCount(); i++) {
                NWAL1500_ISMsg priceCondition = glblMsg.I.no(i);

                if (sourcePriceCondition.specCondPrcPk_NL.getValue().equals(priceCondition.specCondPrcPk_LP.getValue()) && configCatgCd.equals(priceCondition.configCatgCd_LP.getValue())) {
                    if (CONFIG_CATG.OUTBOUND.equals(configCatgCd)) {
                        int lineidx = NWAL1500CommonLogicForLineControl.getCpoLineRow(glblMsg.B, priceCondition.cpoDtlLineNum_LP.getValue(), priceCondition.cpoDtlLineSubNum_LP.getValue());
                        if (lineidx >= 0) {
                            if (BigDecimal.ZERO.compareTo(glblMsg.B.no(lineidx).invQty_LL.getValue()) != 0) {
                                invoicedAmt = invoicedAmt.add(priceCondition.calcPrcAmtRate_LP.getValue());
                                continue;
                            }
                        }
                    } else {
                        int lineidx = NWAL1500CommonLogicForLineControl.getCpoLineRow(glblMsg.D, priceCondition.cpoDtlLineNum_LP.getValue(), priceCondition.cpoDtlLineSubNum_LP.getValue());
                        if (lineidx >= 0) {
                            if (BigDecimal.ZERO.compareTo(glblMsg.D.no(lineidx).rtrnQty_RL.getValue()) != 0) {
                                invoicedAmt = invoicedAmt.add(priceCondition.calcPrcAmtRate_LP.getValue());
                                continue;
                            }
                        }
                    }
                    ZYPEZDItemValueSetter.setValue(priceCondition.prcCondManEntryFlg_LP, sourcePriceCondition.prcCondManEntryFlg_NL);
                    ZYPEZDItemValueSetter.setValue(priceCondition.prcCondManAddFlg_LP, sourcePriceCondition.prcCondManAddFlg_NL);
                    ZYPEZDItemValueSetter.setValue(priceCondition.prcCondManDelFlg_LP, sourcePriceCondition.prcCondManDelFlg_NL);
                    ZYPEZDItemValueSetter.setValue(priceCondition.prcCondUnitCd_LP, sourcePriceCondition.prcCondUnitCd_NL);
                    ZYPEZDItemValueSetter.setValue(priceCondition.calcPrcAmtRate_LP, BigDecimal.ZERO);
                    ZYPEZDItemValueSetter.setValue(priceCondition.unitPrcAmt_LP, BigDecimal.ZERO);
                    if (PRC_COND_UNIT.PCT.equals(sourcePriceCondition.prcCondUnitCd_NL.getValue())) {
                        ZYPEZDItemValueSetter.setValue(priceCondition.manPrcAmtRate_LP, sourcePriceCondition.manPrcAmtRate_NL);
                    }
                }
            }
            // Create Calc_Base
            NWAL1500_ISMsg row = glblMsg.I.no(glblMsg.I.getValidCount());
            ZYPEZDItemValueSetter.setValue(row.cpoDtlLineNum_LP, trxLineNum);
            ZYPEZDItemValueSetter.setValue(row.cpoDtlLineSubNum_LP, trxLineSubNum);
            ZYPEZDItemValueSetter.setValue(row.configCatgCd_LP, configCatgCd);
            ZYPEZDItemValueSetter.setValue(row.prcCondTpCd_LP, sourcePriceCondition.prcCondTpCd_NL);
            ZYPEZDItemValueSetter.setValue(row.prcCondTpDescTxt_LP, sourcePriceCondition.prcCondTpDescTxt_NL);
            if (PRC_DTL_GRP.DISCOUNT.equals(sourcePriceCondition.prcDtlGrpCd_NL.getValue())) {
                ZYPEZDItemValueSetter.setValue(row.prcDtlGrpCd_LP, PRC_DTL_GRP.ROUNDING_FACTOR_0);
            } else if (PRC_DTL_GRP.FREIGHT.equals(sourcePriceCondition.prcDtlGrpCd_NL.getValue())) {
                ZYPEZDItemValueSetter.setValue(row.prcDtlGrpCd_LP, PRC_DTL_GRP.ROUNDING_FACTOR_1);
            // QC#21841 2018/05/21 Add Start
            } else if (PRC_DTL_GRP.HANDLING_FEE.equals(sourcePriceCondition.prcDtlGrpCd_NL.getValue())) {
                ZYPEZDItemValueSetter.setValue(row.prcDtlGrpCd_LP, PRC_DTL_GRP.ROUNDING_FACTOR_2);
            } else if (PRC_DTL_GRP.FUEL_SURCHARGE.equals(sourcePriceCondition.prcDtlGrpCd_NL.getValue())) {
                ZYPEZDItemValueSetter.setValue(row.prcDtlGrpCd_LP, PRC_DTL_GRP.ROUNDING_FACTOR_2);
            } else if (PRC_DTL_GRP.SHIPPING_FEE.equals(sourcePriceCondition.prcDtlGrpCd_NL.getValue())) {
                ZYPEZDItemValueSetter.setValue(row.prcDtlGrpCd_LP, PRC_DTL_GRP.ROUNDING_FACTOR_2);
            } else if (PRC_DTL_GRP.RESTOCKING_FEE.equals(sourcePriceCondition.prcDtlGrpCd_NL.getValue())) { // QC#27479 2018/08/03 Add
                ZYPEZDItemValueSetter.setValue(row.prcDtlGrpCd_LP, PRC_DTL_GRP.ROUNDING_FACTOR_2);
            // QC#21841 2018/05/21 Add End
            }
            row.prcCatgCd_LP.clear();
            ZYPEZDItemValueSetter.setValue(row.prcCondManEntryFlg_LP, sourcePriceCondition.prcCondManEntryFlg_NL);
            ZYPEZDItemValueSetter.setValue(row.prcCondManAddFlg_LP, sourcePriceCondition.prcCondManAddFlg_NL);
            ZYPEZDItemValueSetter.setValue(row.prcCondManDelFlg_LP, sourcePriceCondition.prcCondManDelFlg_NL);
            ZYPEZDItemValueSetter.setValue(row.pkgUomCd_LP, sourcePriceCondition.pkgUomCd_NL.getValue());
            ZYPEZDItemValueSetter.setValue(row.prcCondUnitCd_LP, sourcePriceCondition.prcCondUnitCd_NL);
            ZYPEZDItemValueSetter.setValue(row.autoPrcCcyCd_LP, sourcePriceCondition.autoPrcCcyCd_NL.getValue());
            ZYPEZDItemValueSetter.setValue(row.manPrcAmtRate_LP, sourcePriceCondition.manPrcAmtRate_NL);
            ZYPEZDItemValueSetter.setValue(row.unitPrcAmt_LP, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(row.calcPrcAmtRate_LP, sourcePriceCondition.calcPrcAmtRate_NL.getValue().subtract(invoicedAmt));
            ZYPEZDItemValueSetter.setValue(row.specCondPrcPk_LP, sourcePriceCondition.specCondPrcPk_NL);
            // QC#9700  2018/09/03 Add Start
            ZYPEZDItemValueSetter.setValue(row.prcRuleApplyFlg_LP, sourcePriceCondition.prcRuleApplyFlg_NL);
            ZYPEZDItemValueSetter.setValue(row.prcRulePrcdPk_LP, sourcePriceCondition.prcRulePrcdPk_NL);
            // QC#9700  2018/09/03 Add End
            glblMsg.I.setValidCount(glblMsg.I.getValidCount() + 1);
        }
        if (bizMsg.N.getValidCount() > 0) {
            if (!NWAL1500CommonLogic.callPricingApiGetOrderManualMode(bizMsg, glblMsg, ZYPConstant.FLG_OFF_N)) {
                return;
            }
        }
    }
    // QC#22965 2018/04/11 Add End

    private static void setPriceCondition(NWAL1500_ISMsgArray priceConditionList, NWAL1500_NCMsgArray sourcePriceConditionList, String configCatgCd, String cpoOrdNum, String cpoDtlLineNum, String cpoDtlLineSubNum) {

        // delete
        deletePriceCondition(priceConditionList, configCatgCd, cpoOrdNum, cpoDtlLineNum, cpoDtlLineSubNum);
        // add
        addPriceCondition(priceConditionList, sourcePriceConditionList, configCatgCd, cpoOrdNum, cpoDtlLineNum, cpoDtlLineSubNum);
    }

    private static void deletePriceCondition(NWAL1500_ISMsgArray priceConditionList, String configCatgCd, String cpoOrdNum, String cpoDtlLineNum, String cpoDtlLineSubNum) {

        List<Integer> deleteIndexList = new ArrayList<Integer>();
        for (int priceConditionIndex = 0; priceConditionIndex < priceConditionList.getValidCount(); priceConditionIndex++) {
            NWAL1500_ISMsg priceCondition = priceConditionList.no(priceConditionIndex);
            if (isSameLineForPriceCondition(priceCondition, configCatgCd, cpoDtlLineNum, cpoDtlLineSubNum)) { // S21_NA#2320
                deleteIndexList.add(priceConditionIndex);
            }
        }
        ZYPTableUtil.deleteRows(priceConditionList, deleteIndexList);
    }

    private static void addPriceCondition(NWAL1500_ISMsgArray priceConditionList, NWAL1500_NCMsgArray sourcePriceConditionList, String configCatgCd, String cpoOrdNum, String cpoDtlLineNum, String cpoDtlLineSubNum) {

        for (int sourceIndex = 0; sourceIndex < sourcePriceConditionList.getValidCount(); sourceIndex++) {
            NWAL1500_NCMsg sourcePriceCondition = sourcePriceConditionList.no(sourceIndex);
            int addIndex = priceConditionList.getValidCount();
            priceConditionList.setValidCount(addIndex + 1);
            EZDMsg.copy(sourcePriceCondition, "NL", priceConditionList.no(addIndex), "LP");
            ZYPEZDItemValueSetter.setValue(priceConditionList.no(addIndex).cpoOrdNum_LP, cpoOrdNum);
            ZYPEZDItemValueSetter.setValue(priceConditionList.no(addIndex).cpoDtlLineNum_LP, cpoDtlLineNum);
            ZYPEZDItemValueSetter.setValue(priceConditionList.no(addIndex).cpoDtlLineSubNum_LP, cpoDtlLineSubNum);
            ZYPEZDItemValueSetter.setValue(priceConditionList.no(addIndex).configCatgCd_LP, configCatgCd);
        }
        priceConditionList.setValidCount(priceConditionList.getValidCount() + sourcePriceConditionList.getValidCount());
    }

    /**
     * setParameters.
     * @param bizMsg bizMsg
     * @param bMsg NWAL1500_BCMsg
     * @param iMsgArray NWAL1500_ISMsgArray
     */
    public static void setParameters(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, NWAL1500_BCMsg bMsg, NWAL1500_ISMsgArray iMsgArray) {

        // clear parameters
        bizMsg.xxModeCd_N.clear();
        bizMsg.xxModeCd_PM.clear(); // QC#22965 2018/04/11 Add
        bizMsg.xxViewChngLogSrcCd_N.clear(); 
        bizMsg.trxHdrNum_N.clear();
        bizMsg.prcBaseDt_N.clear();
        bizMsg.prcCalcDt_N.clear();
        bizMsg.dsOrdTpCd_N.clear();
        bizMsg.dsOrdCatgCd_N.clear();
        bizMsg.lineBizTpCd_N.clear();
        bizMsg.cpoSrcTpCd_N.clear();
        bizMsg.custIssPoNum_N.clear();
        bizMsg.dsPmtMethCd_N.clear();
        bizMsg.spclHdlgTpCd_N.clear();
        bizMsg.leasePrchOptCd_N.clear();
        bizMsg.dsOrdPosnNum_N.clear();
        bizMsg.trxLineNum_N.clear();
        bizMsg.trxLineSubNum_N.clear();
        bizMsg.configCatgCd_N.clear();
        bizMsg.inEachQty_N.clear();
        bizMsg.shipToFirstLineAddr_N.clear();
        bizMsg.shipToScdLineAddr_N.clear();
        bizMsg.shipToCtyAddr_N.clear();
        bizMsg.shipToStCd_N.clear();
        bizMsg.shipToCntyNm_N.clear();
        bizMsg.shipToPostCd_N.clear();
        bizMsg.shipToCtryCd_N.clear();
        bizMsg.prcCatgCd_N.clear();
        bizMsg.csmpNum_N.clear();
        bizMsg.dlrRefNum_N.clear();
        bizMsg.prcContrNum_N.clear();
        bizMsg.coaBrCd_N.clear();
        bizMsg.mdseCd_N.clear();
        bizMsg.billToCustCd_N.clear();
        bizMsg.billToCustAcctCd_N.clear();
        bizMsg.sellToCustCd_N.clear();
        bizMsg.soldToCustLocCd_N.clear();
        bizMsg.shipToCustCd_N.clear();
        bizMsg.shipToCustAcctCd_N.clear();
        bizMsg.frtCondCd_N.clear();
        bizMsg.shpgSvcLvlCd_N.clear();
        bizMsg.ccyCd_N.clear();
        bizMsg.uomCd_N.clear();
        bizMsg.ordCustUomQty_N.clear();
        bizMsg.dsCpoLineCatgCd_N.clear();
        bizMsg.invQty_N.clear();
        bizMsg.mdlId_N.clear();
        bizMsg.rtrnRsnCd_N.clear();
        bizMsg.coaExtnCd_N.clear();
        bizMsg.slsRepOrSlsTeamTocCd_N.clear();
        bizMsg.rtlWhCd_N.clear();
        bizMsg.xxTotBaseAmt_N.clear();
        bizMsg.xxSubTotCalcPrcAmt_N.clear();
        bizMsg.xxTotChrgPrcAmt_N.clear();
        bizMsg.xxTotDiscAmt_N.clear();
        bizMsg.xxTotSpclPrcAmt_N.clear();
        bizMsg.xxTotNetDiscAmt_N.clear();
        bizMsg.xxUnitNetPrcAmt_N.clear();
        bizMsg.xxUnitGrsPrcAmt_N.clear();
        bizMsg.xxTotNetPrcAmt_N.clear();
        bizMsg.xxGrsAmt_N.clear();
        bizMsg.xxUnitFrtAmt_N.clear();
        bizMsg.xxTotFrtAmt_N.clear();
        bizMsg.xxUnitSpclChrgAmt_N.clear();
        bizMsg.xxTotSpclChrgAmt_N.clear();
        bizMsg.xxTotFrtSubAmt_N.clear();
        bizMsg.xxUnitRestkFeeAmt_N.clear();
        bizMsg.xxTotRestkFeeAmt_N.clear();
        bizMsg.xxTotNetPrcAmt_N2.clear();
        bizMsg.xxTotTaxAmt_N.clear();
        bizMsg.xxUnitPrc_N.clear();
        bizMsg.xxTotAmt_N.clear();
        bizMsg.dealPrcListPrcAmt_N.clear();
        bizMsg.dsCpoLineNum_N.clear();
        bizMsg.dsCpoLineSubNum_N.clear();
        bizMsg.dealSvcRevTrnsfAmt_N.clear();
        bizMsg.dealSvcCostTrnsfAmt_N.clear();
        bizMsg.dealManFlAdjAmt_N.clear();
        bizMsg.dealManRepRevAdjAmt_N.clear();
        bizMsg.xxSfxKeyTxt_N.clear();
        bizMsg.N.clear();

        // order header information
        if (LINE_STS_NM_CANCELLED.equals(bMsg.ordLineStsDescTxt_LL.getValue())) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxModeCd_N, NWAL1660_MODE_REFERENCE);
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxModeCd_N, NWAL1660_MODE_REGIST);
        }
        ZYPEZDItemValueSetter.setValue(bizMsg.xxModeCd_PM, NWAL1500Constant.NWAL1660_PROCESS_LVL_LINE);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxViewChngLogSrcCd_N, NWAL1500Constant.NWAL1660_VIEW_SRC_CD); // QC#9700  2018/09/03 Add 
        ZYPEZDItemValueSetter.setValue(bizMsg.trxHdrNum_N, bizMsg.cpoOrdNum);
        ZYPEZDItemValueSetter.setValue(bizMsg.prcBaseDt_N, bMsg.prcBaseDt_LL);
        ZYPEZDItemValueSetter.setValue(bizMsg.prcCalcDt_N, bizMsg.slsDt);
        ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdTpCd_N, bizMsg.dsOrdTpCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdCatgCd_N, bizMsg.dsOrdCatgCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.lineBizTpCd_N, bizMsg.lineBizTpCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.cpoSrcTpCd_N, bizMsg.cpoSrcTpCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.custIssPoNum_N, bizMsg.custIssPoNum);
        if (ZYPCommonFunc.hasValue(bizMsg.dsCrCardPk)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.dsPmtMethCd_N, DS_PMT_METH.CREDIT_CARD);
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.dsPmtMethCd_N, DS_PMT_METH.INVOICE);
        }
        ZYPEZDItemValueSetter.setValue(bizMsg.spclHdlgTpCd_N, bizMsg.spclHdlgTpCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.leasePrchOptCd_N, bizMsg.leasePrchOptCd);

        // order line information
        ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdPosnNum_N, bMsg.dsOrdPosnNum_LL);
        ZYPEZDItemValueSetter.setValue(bizMsg.trxLineNum_N, bMsg.cpoDtlLineNum_LL);
        ZYPEZDItemValueSetter.setValue(bizMsg.trxLineSubNum_N, bMsg.cpoDtlLineSubNum_LL);
        ZYPEZDItemValueSetter.setValue(bizMsg.configCatgCd_N, CONFIG_CATG.OUTBOUND);
        ZYPEZDItemValueSetter.setValue(bizMsg.inEachQty_N, bMsg.ordQty_LL);
        NWAL1500_ACMsg config = getConfigLine(bizMsg.A, bMsg.dsOrdPosnNum_LL.getValue());
        if (config != null) {
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToFirstLineAddr_N, config.shipToFirstLineAddr_LC);
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToScdLineAddr_N, config.shipToScdLineAddr_LC);
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToCtyAddr_N, config.shipToCtyAddr_LC);
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToStCd_N, config.shipToStCd_LC);
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToCntyNm_N, config.shipToCntyNm_LC);
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToPostCd_N, config.shipToPostCd_LC);
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToCtryCd_N, config.shipToCtryCd_LC);
            ZYPEZDItemValueSetter.setValue(bizMsg.billToCustCd_N, config.billToCustCd_LC);
            ZYPEZDItemValueSetter.setValue(bizMsg.billToCustAcctCd_N, config.billToCustAcctCd_LC);
            ZYPEZDItemValueSetter.setValue(bizMsg.mdlId_N, config.mdlId_LC);
        }
        ZYPEZDItemValueSetter.setValue(bizMsg.prcCatgCd_N, bMsg.prcCatgCd_LL);
        ZYPEZDItemValueSetter.setValue(bizMsg.csmpNum_N, bizMsg.csmpContrNum);
        ZYPEZDItemValueSetter.setValue(bizMsg.dlrRefNum_N, bMsg.dlrRefNum_LL);
        ZYPEZDItemValueSetter.setValue(bizMsg.prcContrNum_N, bizMsg.prcContrNum);
        // 2017/11/02 S21_NA#20146 Mod Start
        //NWAL1500_GCMsg salesCredit = getSalesCredit(bizMsg.G, bMsg.dsOrdPosnNum_LL.getValue(), new String[] {LINE_BIZ_ROLE_TP.ESS_WRITER, LINE_BIZ_ROLE_TP.LFS_WRITER, LINE_BIZ_ROLE_TP.PPS_WRITER });
        NWAL1500_GCMsg salesCredit = getSalesCredit(bizMsg.G, bMsg.dsOrdPosnNum_LL.getValue(), new String[] {LINE_BIZ_ROLE_TP.ESS_WRITER, LINE_BIZ_ROLE_TP.LFS_WRITER, LINE_BIZ_ROLE_TP.PPS_WRITER, LINE_BIZ_ROLE_TP.EMSD_WRITER });
        // 2017/11/02 S21_NA#20146 Mod End
        if (salesCredit != null) {
            TOCTMsg salesRep = getSalesRep(bizMsg.glblCmpyCd.getValue(), salesCredit.slsRepTocCd_GS.getValue());
            if (salesRep != null) {
                ZYPEZDItemValueSetter.setValue(bizMsg.coaBrCd_N, salesRep.coaBrCd);
                ZYPEZDItemValueSetter.setValue(bizMsg.coaExtnCd_N, salesRep.coaExtnCd);
                ZYPEZDItemValueSetter.setValue(bizMsg.slsRepOrSlsTeamTocCd_N, salesRep.tocCd);
            }
        }
        ZYPEZDItemValueSetter.setValue(bizMsg.mdseCd_N, bMsg.mdseCd_LL);
        ZYPEZDItemValueSetter.setValue(bizMsg.sellToCustCd_N, bizMsg.sellToCustCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.soldToCustLocCd_N, bizMsg.soldToCustLocCd);

        if (config != null) {
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustCd_N, config.shipToCustCd_LC);
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustAcctCd_N, config.shipToCustAcctCd_LC);
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.frtCondCd_N, bizMsg.frtCondCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.shpgSvcLvlCd_N, bizMsg.shpgSvcLvlCd);
        // Price List
        S21SsmEZDResult ssmRslt = NWAL1500QueryForSaveSubmit.getInstance().checkPriceCatgNm(bizMsg, bizMsg.prcCatgNm.getValue());
        if (ssmRslt.isCodeNormal()) {
            List<Map<String, String>> rsltMapList = (List<Map<String, String>>) ssmRslt.getResultObject();
            PRC_CATGTMsg priceCategory = getPriceCategory(bizMsg.glblCmpyCd.getValue(), rsltMapList.get(0).get("PRC_CATG_CD"));
            if (priceCategory != null) {
                ZYPEZDItemValueSetter.setValue(bizMsg.ccyCd_N, priceCategory.ccyCd);
            }
        }
        ZYPEZDItemValueSetter.setValue(bizMsg.uomCd_N, bMsg.custUomCd_LL);
        ZYPEZDItemValueSetter.setValue(bizMsg.ordCustUomQty_N, bMsg.ordCustUomQty_LL);
        ZYPEZDItemValueSetter.setValue(bizMsg.dsCpoLineCatgCd_N, bMsg.dsOrdLineCatgCd_LL);
        ZYPEZDItemValueSetter.setValue(bizMsg.invQty_N, bMsg.invQty_LL);
        ZYPEZDItemValueSetter.setValue(bizMsg.rtrnRsnCd_N, "");
        ZYPEZDItemValueSetter.setValue(bizMsg.rtlWhCd_N, bMsg.rtlWhCd_LL);

        // summary information
        ZYPEZDItemValueSetter.setValue(bizMsg.xxUnitNetPrcAmt_N, bMsg.entCpoDtlDealSlsAmt_LL);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxUnitGrsPrcAmt_N, bMsg.cpoDtlDealGrsAmt_LL);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxTotTaxAmt_N, bMsg.cpoDtlDealTaxAmt_LL);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxTotAmt_N, bMsg.xxLineTotPrcAmt_LL);

        // 2015/11/24 S21_NA#1020 Add Start
        ZYPEZDItemValueSetter.setValue(bizMsg.dealPrcListPrcAmt_N, bMsg.entCpoDtlDealSlsAmt_LL);
        ZYPEZDItemValueSetter.setValue(bizMsg.dsCpoLineNum_N, bMsg.dsCpoLineNum_LL);
        ZYPEZDItemValueSetter.setValue(bizMsg.dsCpoLineSubNum_N, bMsg.dsCpoLineSubNum_LL);
        // 2016/05/10 S21_NA#7706 Mod Start
//        ZYPEZDItemValueSetter.setValue(bizMsg.dealSvcRevTrnsfAmt_N, BigDecimal.ZERO);
//        ZYPEZDItemValueSetter.setValue(bizMsg.dealSvcCostTrnsfAmt_N, BigDecimal.ZERO);
//        ZYPEZDItemValueSetter.setValue(bizMsg.dealManFlAdjAmt_N, BigDecimal.ZERO);
//        ZYPEZDItemValueSetter.setValue(bizMsg.dealManRepRevAdjAmt_N, BigDecimal.ZERO);
        if (ZYPCommonFunc.hasValue(bMsg.dealSvcRevTrnsfAmt_LL)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.dealSvcRevTrnsfAmt_N, bMsg.dealSvcRevTrnsfAmt_LL);
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.dealSvcRevTrnsfAmt_N, BigDecimal.ZERO);
        }
        if (ZYPCommonFunc.hasValue(bMsg.dealSvcCostTrnsfAmt_LL)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.dealSvcCostTrnsfAmt_N, bMsg.dealSvcCostTrnsfAmt_LL);
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.dealSvcCostTrnsfAmt_N, BigDecimal.ZERO);
        }
        if (ZYPCommonFunc.hasValue(bMsg.dealManFlAdjAmt_LL)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.dealManFlAdjAmt_N, bMsg.dealManFlAdjAmt_LL);
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.dealManFlAdjAmt_N, BigDecimal.ZERO);
        }
        if (ZYPCommonFunc.hasValue(bMsg.dealManRepRevAdjAmt_LL)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.dealManRepRevAdjAmt_N, bMsg.dealManRepRevAdjAmt_LL);
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.dealManRepRevAdjAmt_N, BigDecimal.ZERO);
        }
        // 2016/05/10 S21_NA#7706 Mod End
        // 2015/11/24 S21_NA#1020 Add End

        // price condition
        String configCatgCd = CONFIG_CATG.OUTBOUND;
        String cpoOrdNum = bizMsg.cpoOrdNum.getValue();
        String cpoDtlLineNum = bMsg.cpoDtlLineNum_LL.getValue();
        String cpoDtlLineSubNum = bMsg.cpoDtlLineSubNum_LL.getValue();
        NWAL1500CommonLogicForPriceChanges.setPriceConditionList(bizMsg.N, glblMsg.I, configCatgCd, cpoOrdNum, cpoDtlLineNum, cpoDtlLineSubNum);
    }

    /**
     * setParameters.
     * @param bizMsg NWAL1500CMsg
     * @param dMsg NWAL1500_DCMsg
     * @param iMsgArray NWAL1500_ISMsgArray
     */
    public static void setParameters(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, NWAL1500_DCMsg dMsg, NWAL1500_ISMsgArray iMsgArray) {

        // clear parameters
        bizMsg.xxModeCd_N.clear();
        bizMsg.xxModeCd_PM.clear(); // QC#22965 2018/04/11 Add
        bizMsg.trxHdrNum_N.clear();
        bizMsg.prcBaseDt_N.clear();
        bizMsg.prcCalcDt_N.clear();
        bizMsg.dsOrdTpCd_N.clear();
        bizMsg.dsOrdCatgCd_N.clear();
        bizMsg.lineBizTpCd_N.clear();
        bizMsg.cpoSrcTpCd_N.clear();
        bizMsg.custIssPoNum_N.clear();
        bizMsg.dsPmtMethCd_N.clear();
        bizMsg.spclHdlgTpCd_N.clear();
        bizMsg.leasePrchOptCd_N.clear();
        bizMsg.dsOrdPosnNum_N.clear();
        bizMsg.trxLineNum_N.clear();
        bizMsg.trxLineSubNum_N.clear();
        bizMsg.configCatgCd_N.clear();
        bizMsg.inEachQty_N.clear();
        bizMsg.shipToFirstLineAddr_N.clear();
        bizMsg.shipToScdLineAddr_N.clear();
        bizMsg.shipToCtyAddr_N.clear();
        bizMsg.shipToStCd_N.clear();
        bizMsg.shipToCntyNm_N.clear();
        bizMsg.shipToPostCd_N.clear();
        bizMsg.shipToCtryCd_N.clear();
        bizMsg.prcCatgCd_N.clear();
        bizMsg.csmpNum_N.clear();
        bizMsg.dlrRefNum_N.clear();
        bizMsg.prcContrNum_N.clear();
        bizMsg.coaBrCd_N.clear();
        bizMsg.mdseCd_N.clear();
        bizMsg.billToCustCd_N.clear();
        bizMsg.billToCustAcctCd_N.clear();
        bizMsg.sellToCustCd_N.clear();
        bizMsg.soldToCustLocCd_N.clear();
        bizMsg.shipToCustCd_N.clear();
        bizMsg.shipToCustAcctCd_N.clear();
        bizMsg.frtCondCd_N.clear();
        bizMsg.shpgSvcLvlCd_N.clear();
        bizMsg.ccyCd_N.clear();
        bizMsg.uomCd_N.clear();
        bizMsg.ordCustUomQty_N.clear();
        bizMsg.dsCpoLineCatgCd_N.clear();
        bizMsg.invQty_N.clear();
        bizMsg.mdlId_N.clear();
        bizMsg.rtrnRsnCd_N.clear();
        bizMsg.coaExtnCd_N.clear();
        bizMsg.slsRepOrSlsTeamTocCd_N.clear();
        bizMsg.rtlWhCd_N.clear();
        bizMsg.xxTotBaseAmt_N.clear();
        bizMsg.xxSubTotCalcPrcAmt_N.clear();
        bizMsg.xxTotChrgPrcAmt_N.clear();
        bizMsg.xxTotDiscAmt_N.clear();
        bizMsg.xxTotSpclPrcAmt_N.clear();
        bizMsg.xxTotNetDiscAmt_N.clear();
        bizMsg.xxUnitNetPrcAmt_N.clear();
        bizMsg.xxUnitGrsPrcAmt_N.clear();
        bizMsg.xxTotNetPrcAmt_N.clear();
        bizMsg.xxGrsAmt_N.clear();
        bizMsg.xxUnitFrtAmt_N.clear();
        bizMsg.xxTotFrtAmt_N.clear();
        bizMsg.xxUnitSpclChrgAmt_N.clear();
        bizMsg.xxTotSpclChrgAmt_N.clear();
        bizMsg.xxTotFrtSubAmt_N.clear();
        bizMsg.xxUnitRestkFeeAmt_N.clear();
        bizMsg.xxTotRestkFeeAmt_N.clear();
        bizMsg.xxTotNetPrcAmt_N2.clear();
        bizMsg.xxTotTaxAmt_N.clear();
        bizMsg.xxUnitPrc_N.clear();
        bizMsg.xxTotAmt_N.clear();
        bizMsg.dealPrcListPrcAmt_N.clear();
        bizMsg.xxSfxKeyTxt_N.clear();
        bizMsg.N.clear();

        // order header information
        if (LINE_STS_NM_CANCELLED.equals(dMsg.rtrnLineStsDescTxt_RL.getValue())) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxModeCd_N, NWAL1660_MODE_REFERENCE);
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxModeCd_N, NWAL1660_MODE_REGIST);
        }
        ZYPEZDItemValueSetter.setValue(bizMsg.trxHdrNum_N, bizMsg.cpoOrdNum);
        ZYPEZDItemValueSetter.setValue(bizMsg.prcBaseDt_N, dMsg.prcBaseDt_RL);
        ZYPEZDItemValueSetter.setValue(bizMsg.prcCalcDt_N, bizMsg.slsDt);
        ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdTpCd_N, bizMsg.dsOrdTpCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdCatgCd_N, bizMsg.dsOrdCatgCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.lineBizTpCd_N, bizMsg.lineBizTpCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.cpoSrcTpCd_N, bizMsg.cpoSrcTpCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.custIssPoNum_N, bizMsg.custIssPoNum);
        if (ZYPCommonFunc.hasValue(bizMsg.dsCrCardPk)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.dsPmtMethCd_N, DS_PMT_METH.CREDIT_CARD);
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.dsPmtMethCd_N, DS_PMT_METH.INVOICE);
        }
        ZYPEZDItemValueSetter.setValue(bizMsg.spclHdlgTpCd_N, bizMsg.spclHdlgTpCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.leasePrchOptCd_N, bizMsg.leasePrchOptCd);

        // order line information
        ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdPosnNum_N, dMsg.dsOrdPosnNum_RL);
        ZYPEZDItemValueSetter.setValue(bizMsg.trxLineNum_N, dMsg.cpoDtlLineNum_RL);
        ZYPEZDItemValueSetter.setValue(bizMsg.trxLineSubNum_N, dMsg.cpoDtlLineSubNum_RL);
        ZYPEZDItemValueSetter.setValue(bizMsg.configCatgCd_N, CONFIG_CATG.OUTBOUND);
        ZYPEZDItemValueSetter.setValue(bizMsg.inEachQty_N, dMsg.ordQty_RL);
        NWAL1500_CCMsg config = getConfigLine(bizMsg.C, dMsg.dsOrdPosnNum_RL.getValue());
        if (config != null) {
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToFirstLineAddr_N, config.shipToFirstLineAddr_RC);
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToScdLineAddr_N, config.shipToScdLineAddr_RC);
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToCtyAddr_N, config.shipToCtyAddr_RC);
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToStCd_N, config.shipToStCd_RC);
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToCntyNm_N, config.shipToCntyNm_RC);
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToPostCd_N, config.shipToPostCd_RC);
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToCtryCd_N, config.shipToCtryCd_RC);
            ZYPEZDItemValueSetter.setValue(bizMsg.billToCustCd_N, config.billToCustCd_RC);
            ZYPEZDItemValueSetter.setValue(bizMsg.billToCustAcctCd_N, config.billToCustAcctCd_RC);
            ZYPEZDItemValueSetter.setValue(bizMsg.mdlId_N, config.mdlId_RC);
        }
        ZYPEZDItemValueSetter.setValue(bizMsg.prcCatgCd_N, dMsg.prcCatgCd_RL);
        ZYPEZDItemValueSetter.setValue(bizMsg.csmpNum_N, bizMsg.csmpContrNum);
        ZYPEZDItemValueSetter.setValue(bizMsg.dlrRefNum_N, dMsg.dlrRefNum_RL);
        ZYPEZDItemValueSetter.setValue(bizMsg.prcContrNum_N, bizMsg.prcContrNum);
        NWAL1500_HCMsg salesCredit = getSalesCredit(bizMsg.H, dMsg.dsOrdPosnNum_RL.getValue(), new String[] {LINE_BIZ_ROLE_TP.ESS_WRITER, LINE_BIZ_ROLE_TP.LFS_WRITER, LINE_BIZ_ROLE_TP.PPS_WRITER });
        if (salesCredit != null) {
            TOCTMsg salesRep = getSalesRep(bizMsg.glblCmpyCd.getValue(), salesCredit.slsRepTocCd_HS.getValue());
            if (salesRep != null) {
                ZYPEZDItemValueSetter.setValue(bizMsg.coaBrCd_N, salesRep.coaBrCd);
                ZYPEZDItemValueSetter.setValue(bizMsg.coaExtnCd_N, salesRep.coaExtnCd);
                ZYPEZDItemValueSetter.setValue(bizMsg.slsRepOrSlsTeamTocCd_N, salesRep.tocCd);
            }
        }
        ZYPEZDItemValueSetter.setValue(bizMsg.mdseCd_N, dMsg.mdseCd_RL);
        ZYPEZDItemValueSetter.setValue(bizMsg.sellToCustCd_N, bizMsg.sellToCustCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.soldToCustLocCd_N, bizMsg.soldToCustLocCd);

        if (config != null) {
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustCd_N, config.shipToCustCd_RC);
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustAcctCd_N, config.shipToCustAcctCd_RC);
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.frtCondCd_N, bizMsg.frtCondCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.shpgSvcLvlCd_N, bizMsg.shpgSvcLvlCd);
        PRC_CATGTMsg priceCategory = getPriceCategory(bizMsg.glblCmpyCd.getValue(), dMsg.prcCatgCd_RL.getValue());
        if (priceCategory != null) {
            ZYPEZDItemValueSetter.setValue(bizMsg.ccyCd_N, priceCategory.ccyCd);
        }
        ZYPEZDItemValueSetter.setValue(bizMsg.uomCd_N, dMsg.custUomCd_RL);
        ZYPEZDItemValueSetter.setValue(bizMsg.ordCustUomQty_N, dMsg.ordCustUomQty_RL);
        ZYPEZDItemValueSetter.setValue(bizMsg.dsCpoLineCatgCd_N, dMsg.dsOrdLineCatgCd_RL);
        ZYPEZDItemValueSetter.setValue(bizMsg.invQty_N, dMsg.rtrnQty_RL);
        ZYPEZDItemValueSetter.setValue(bizMsg.rtrnRsnCd_N, dMsg.rtrnRsnCd_RL);
        ZYPEZDItemValueSetter.setValue(bizMsg.rtlWhCd_N, dMsg.rtlWhCd_RL);

        // summary information
        ZYPEZDItemValueSetter.setValue(bizMsg.xxUnitNetPrcAmt_N, dMsg.entDealNetUnitPrcAmt_RL);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxUnitGrsPrcAmt_N, dMsg.cpoDtlDealGrsAmt_RL);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxTotTaxAmt_N, dMsg.cpoDtlDealTaxAmt_RL);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxTotAmt_N, dMsg.xxLineTotPrcAmt_RL);

        // 2015/11/24 S21_NA#1020 Add Start
        ZYPEZDItemValueSetter.setValue(bizMsg.dealPrcListPrcAmt_N, dMsg.entCpoDtlDealSlsAmt_RL);
        ZYPEZDItemValueSetter.setValue(bizMsg.dsCpoLineNum_N, dMsg.dsCpoLineNum_RL);
        ZYPEZDItemValueSetter.setValue(bizMsg.dsCpoLineSubNum_N, dMsg.dsCpoLineSubNum_RL);
        // 2016/05/10 S21_NA#7706 Mod Start
//        ZYPEZDItemValueSetter.setValue(bizMsg.dealSvcRevTrnsfAmt_N, BigDecimal.ZERO);
//        ZYPEZDItemValueSetter.setValue(bizMsg.dealSvcCostTrnsfAmt_N, BigDecimal.ZERO);
//        ZYPEZDItemValueSetter.setValue(bizMsg.dealManFlAdjAmt_N, BigDecimal.ZERO);
//        ZYPEZDItemValueSetter.setValue(bizMsg.dealManRepRevAdjAmt_N, BigDecimal.ZERO);
        if (ZYPCommonFunc.hasValue(dMsg.dealSvcRevTrnsfAmt_RL)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.dealSvcRevTrnsfAmt_N, dMsg.dealSvcRevTrnsfAmt_RL);
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.dealSvcRevTrnsfAmt_N, BigDecimal.ZERO);
        }
        if (ZYPCommonFunc.hasValue(dMsg.dealSvcCostTrnsfAmt_RL)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.dealSvcCostTrnsfAmt_N, dMsg.dealSvcCostTrnsfAmt_RL);
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.dealSvcCostTrnsfAmt_N, BigDecimal.ZERO);
        }
        if (ZYPCommonFunc.hasValue(dMsg.dealManFlAdjAmt_RL)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.dealManFlAdjAmt_N, dMsg.dealManFlAdjAmt_RL);
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.dealManFlAdjAmt_N, BigDecimal.ZERO);
        }
        if (ZYPCommonFunc.hasValue(dMsg.dealManRepRevAdjAmt_RL)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.dealManRepRevAdjAmt_N, dMsg.dealManRepRevAdjAmt_RL);
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.dealManRepRevAdjAmt_N, BigDecimal.ZERO);
        }
        // 2016/05/10 S21_NA#7706 Mod End
        // 2015/11/24 S21_NA#1020 Add End

        // price condition
        String configCatgCd = CONFIG_CATG.INBOUND;
        String cpoOrdNum = bizMsg.cpoOrdNum.getValue();
        String cpoDtlLineNum = dMsg.cpoDtlLineNum_RL.getValue();
        String cpoDtlLineSubNum = dMsg.cpoDtlLineSubNum_RL.getValue();
        NWAL1500CommonLogicForPriceChanges.setPriceConditionList(bizMsg.N, glblMsg.I, configCatgCd, cpoOrdNum, cpoDtlLineNum, cpoDtlLineSubNum);
    }

    private static PRC_CATGTMsg getPriceCategory(String glblCmpyCd, String prcCatgCd) {
        PRC_CATGTMsg prcCatg = new PRC_CATGTMsg();
        ZYPEZDItemValueSetter.setValue(prcCatg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prcCatg.prcCatgCd, prcCatgCd);
        return (PRC_CATGTMsg) S21FastTBLAccessor.findByKey(prcCatg);
    }

    private static TOCTMsg getSalesRep(String glblCmpyCd, String tocCd) {
        TOCTMsg toc = new TOCTMsg();
        ZYPEZDItemValueSetter.setValue(toc.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(toc.tocCd, tocCd);
        return (TOCTMsg) S21FastTBLAccessor.findByKey(toc);
    }

    private static List<NWAL1500_ISMsg> getPriceConditionList(NWAL1500_ISMsgArray priceConditionArray, String configCatgCd, String cpoOrdNum, String cpoDtlLineNum, String cpoDtlLineSubNum) {

        List<NWAL1500_ISMsg> priceConditionList = new ArrayList<NWAL1500_ISMsg>();
        for (int i = 0; i < priceConditionArray.getValidCount(); i++) {
            NWAL1500_ISMsg priceCondition = priceConditionArray.no(i);
            if (isSameLineForPriceCondition(priceCondition, configCatgCd, cpoDtlLineNum, cpoDtlLineSubNum)) { // S21_NA#2320
                priceConditionList.add(priceCondition);
            }
        }
        return priceConditionList;
    }

    private static boolean isSameLineForPriceCondition(NWAL1500_ISMsg priceCondition, String configCatgCd, String cpoDtlLineNum, String cpoDtlLineSubNum) { // S21_NA#2320

        // S21_NA#2320 start
        // if (S21StringUtil.isNotEmpty(cpoOrdNum)) {
        // if (!S21StringUtil.isEquals(cpoOrdNum,
        // priceCondition.cpoOrdNum_LP.getValue())) {
        // return false;
        // }
        // }
        if (!S21StringUtil.isEquals(configCatgCd, priceCondition.configCatgCd_LP.getValue())) {
            return false;
        }
        // S21_NA#2320 end
        if (!S21StringUtil.isEquals(cpoDtlLineNum, priceCondition.cpoDtlLineNum_LP.getValue())) {
            return false;
        }
        if (S21StringUtil.isNotEmpty(cpoDtlLineSubNum)) {
            if (!S21StringUtil.isEquals(cpoDtlLineSubNum, priceCondition.cpoDtlLineSubNum_LP.getValue())) {
                return false;
            }
        }
        return true;
    }

    private static void setPriceConditionList(NWAL1500_NCMsgArray paramPriceConditionArray, NWAL1500_ISMsgArray priceConditionArray, String configCatgCd, String cpoOrdNum, String cpoDtlLineNum, String cpoDtlLineSubNum) {

        int paramCount = 0;
        paramPriceConditionArray.clear();
        List<NWAL1500_ISMsg> priceConditionList = getPriceConditionList(priceConditionArray, configCatgCd, cpoOrdNum, cpoDtlLineNum, cpoDtlLineSubNum);
        for (NWAL1500_ISMsg priceCondition : priceConditionList) {
            EZDMsg.copy(priceCondition, "LP", paramPriceConditionArray.no(paramCount), "NL");
            paramCount++;
        }
        paramPriceConditionArray.setValidCount(priceConditionList.size());
        return;
    }

    /**
     * isSetComponent
     * @param dMsgArray NWAL1500_DCMsgArray
     * @param selectedRow int
     * @return boolean
     */
    public static boolean isSetComponent(NWAL1500_DCMsgArray dMsgArray, int selectedRow) {

        BigDecimal selectedLineNum = dMsgArray.no(selectedRow).dsCpoLineNum_RL.getValue();
        BigDecimal selectedLineSubNum = dMsgArray.no(selectedRow).dsCpoLineSubNum_RL.getValue();
        for (int i = 0; i < dMsgArray.getValidCount(); i++) {
            NWAL1500_DCMsg target = dMsgArray.no(i);
            if (compareBigDecimal(selectedLineNum, target.dsCpoLineNum_RL.getValue()) == 0) {
                if (compareBigDecimal(selectedLineSubNum, target.dsCpoLineSubNum_RL.getValue()) > 0) {
                    return true;
                }
            }

        }
        return false;
    }

    private static NWAL1500_ACMsg getConfigLine(NWAL1500_ACMsgArray aMsgArray, String configNum) {
        for (int i = 0; i < aMsgArray.getValidCount(); i++) {
            NWAL1500_ACMsg aMsg = aMsgArray.no(i);
            if (S21StringUtil.isEquals(aMsg.dsOrdPosnNum_LC.getValue(), configNum)) {
                return aMsg;
            }
        }
        return null;
    }

    private static NWAL1500_CCMsg getConfigLine(NWAL1500_CCMsgArray cMsgArray, String configNum) {
        for (int i = 0; i < cMsgArray.getValidCount(); i++) {
            NWAL1500_CCMsg cMsg = cMsgArray.no(i);
            if (S21StringUtil.isEquals(cMsg.dsOrdPosnNum_RC.getValue(), configNum)) {
                return cMsg;
            }
        }
        return null;
    }

    private static NWAL1500_GCMsg getSalesCredit(NWAL1500_GCMsgArray gMsgArray, String configNum, String[] lineBizRoleTp) {
        for (int i = 0; i < gMsgArray.getValidCount(); i++) {
            NWAL1500_GCMsg gMsg = gMsgArray.no(i);
            if (S21StringUtil.isEquals(gMsg.dsOrdPosnNum_GS.getValue(), configNum)) {
                if (Arrays.asList(lineBizRoleTp).contains(gMsg.slsRepRoleTpCd_GS.getValue())) {
                    return gMsg;
                }
            }
        }
        return null;
    }

    private static NWAL1500_HCMsg getSalesCredit(NWAL1500_HCMsgArray hMsgArray, String configNum, String[] lineBizRoleTp) {
        for (int i = 0; i < hMsgArray.getValidCount(); i++) {
            NWAL1500_HCMsg hMsg = hMsgArray.no(i);
            if (S21StringUtil.isEquals(hMsg.dsOrdPosnNum_HS.getValue(), configNum)) {
                if (Arrays.asList(lineBizRoleTp).contains(hMsg.slsRepRoleTpCd_HS.getValue())) {
                    return hMsg;
                }
            }
        }
        return null;
    }

    private static int compareBigDecimal(BigDecimal source, BigDecimal target) {

        if (source == null) {
            if (target == null) {
                return 0;
            } else {
                return -1;
            }
        } else {
            if (target == null) {
                return 1;
            } else {
                return source.compareTo(target);
            }
        }
    }

    // 2016/05/10 S21_NA#7706 Add Start
    private static void calcFuncAmtForPriceChange(NWAL1500CMsg bizMsg, EZDMsg lineMsg) {

        String dealCcyCd = null;
        String prcCatgCd = null;
        String prcCatgNm = null;
        if (lineMsg instanceof NWAL1500_BSMsg) {
            prcCatgCd = ((NWAL1500_BSMsg) lineMsg).prcCatgCd_LL.getValue();
            prcCatgNm = ((NWAL1500_BSMsg) lineMsg).prcCatgNm_LL.getValue();
        } else if (lineMsg instanceof NWAL1500_DSMsg) {
            prcCatgCd = ((NWAL1500_DSMsg) lineMsg).prcCatgCd_RL.getValue();
            prcCatgNm = ((NWAL1500_DSMsg) lineMsg).prcCatgNm_RL.getValue();
        }
        if (null == prcCatgCd) {
            prcCatgCd = bizMsg.prcCatgCd_N.getValue();
        }
        if (null == prcCatgCd) {
            prcCatgCd = bizMsg.prcCatgCd.getValue();
            prcCatgNm = bizMsg.prcCatgNm.getValue();
        }
        if (S21StringUtil.isEmpty(prcCatgCd)) {
            if (!S21StringUtil.isEmpty(prcCatgNm)) {
                PRC_CATGTMsg prcCatgTMsg = new PRC_CATGTMsg();
                prcCatgTMsg.setSQLID("001");
                prcCatgTMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
                prcCatgTMsg.setConditionValue("prcCatgNm01", prcCatgNm);
                PRC_CATGTMsgArray prcCatgTMsgArray = (PRC_CATGTMsgArray) EZDTBLAccessor.findByCondition(prcCatgTMsg);

                if (prcCatgTMsgArray.getValidCount() > 0) {
                    prcCatgCd = prcCatgTMsgArray.no(0).prcCatgCd.getValue();
                    dealCcyCd = prcCatgTMsgArray.no(0).ccyCd.getValue();
                }
            }
        } else {
            PRC_CATGTMsg prcCatgTMsg = new PRC_CATGTMsg();
            prcCatgTMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(prcCatgTMsg.prcCatgCd, prcCatgCd);
            prcCatgTMsg = (PRC_CATGTMsg) S21CacheTBLAccessor.findByKey(prcCatgTMsg);
            if (null != prcCatgTMsg) {
                dealCcyCd = prcCatgTMsg.ccyCd.getValue();
            }
        }
        if (null == dealCcyCd) {
            GLBL_CMPYTMsg glblCmpyTMsg = new GLBL_CMPYTMsg();
            ZYPEZDItemValueSetter.setValue(glblCmpyTMsg.glblCmpyCd, bizMsg.glblCmpyCd);
            glblCmpyTMsg = (GLBL_CMPYTMsg) S21CacheTBLAccessor.findByKey(glblCmpyTMsg);
            if (null != glblCmpyTMsg) {
                dealCcyCd = glblCmpyTMsg.stdCcyCd.getValue();
            }
            prcCatgCd = bizMsg.prcCatgCd.getValue();
        }

        if (S21StringUtil.isEmpty(dealCcyCd)) {
            if (lineMsg instanceof NWAL1500_BSMsg) {
                NWAL1500_BSMsg lineConfMsg = (NWAL1500_BSMsg) lineMsg;
                ZYPEZDItemValueSetter.setValue(lineConfMsg.funcSvcRevTrnsfAmt_LL, lineConfMsg.dealSvcRevTrnsfAmt_LL);
                ZYPEZDItemValueSetter.setValue(lineConfMsg.funcSvcCostTrnsfAmt_LL, lineConfMsg.dealSvcCostTrnsfAmt_LL);
                ZYPEZDItemValueSetter.setValue(lineConfMsg.funcManFlAdjAmt_LL, lineConfMsg.dealManFlAdjAmt_LL);
                ZYPEZDItemValueSetter.setValue(lineConfMsg.funcManRepRevAdjAmt_LL, lineConfMsg.dealManRepRevAdjAmt_LL);
            } else if (lineMsg instanceof NWAL1500_DSMsg) {
                NWAL1500_DSMsg rmaLineMsg = (NWAL1500_DSMsg) lineMsg;
                ZYPEZDItemValueSetter.setValue(rmaLineMsg.funcSvcRevTrnsfAmt_RL, rmaLineMsg.dealSvcRevTrnsfAmt_RL);
                ZYPEZDItemValueSetter.setValue(rmaLineMsg.funcSvcCostTrnsfAmt_RL, rmaLineMsg.dealSvcCostTrnsfAmt_RL);
                ZYPEZDItemValueSetter.setValue(rmaLineMsg.funcManFlAdjAmt_RL, rmaLineMsg.dealManFlAdjAmt_RL);
                ZYPEZDItemValueSetter.setValue(rmaLineMsg.funcManRepRevAdjAmt_RL, rmaLineMsg.dealManRepRevAdjAmt_RL);
            }
        } else {
            NWAL1500CommonLogicForSaveSubmit comLogic = NWAL1500CommonLogicForSaveSubmit.getInstance();
            String glblCmpyCd = bizMsg.glblCmpyCd.getValue();
            String slsDt = bizMsg.slsDt.getValue();
            if (lineMsg instanceof NWAL1500_BSMsg) {
                NWAL1500_BSMsg lineConfMsg = (NWAL1500_BSMsg) lineMsg;
                BigDecimal funcAmt = comLogic.calcFuncAmt(glblCmpyCd, dealCcyCd, slsDt, lineConfMsg.dealSvcRevTrnsfAmt_LL.getValue());
                ZYPEZDItemValueSetter.setValue(lineConfMsg.funcSvcRevTrnsfAmt_LL, funcAmt);

                funcAmt = comLogic.calcFuncAmt(glblCmpyCd, dealCcyCd, slsDt, lineConfMsg.dealSvcCostTrnsfAmt_LL.getValue());
                ZYPEZDItemValueSetter.setValue(lineConfMsg.funcSvcCostTrnsfAmt_LL, funcAmt);

                funcAmt = comLogic.calcFuncAmt(glblCmpyCd, dealCcyCd, slsDt, lineConfMsg.dealManFlAdjAmt_LL.getValue());
                ZYPEZDItemValueSetter.setValue(lineConfMsg.funcManFlAdjAmt_LL, funcAmt);

                funcAmt = comLogic.calcFuncAmt(glblCmpyCd, dealCcyCd, slsDt, lineConfMsg.dealManRepRevAdjAmt_LL.getValue());
                ZYPEZDItemValueSetter.setValue(lineConfMsg.funcManRepRevAdjAmt_LL, funcAmt);
            } else if (lineMsg instanceof NWAL1500_DSMsg) {
                NWAL1500_DSMsg rmaLineMsg = (NWAL1500_DSMsg) lineMsg;
                BigDecimal funcAmt = comLogic.calcFuncAmt(glblCmpyCd, dealCcyCd, slsDt, rmaLineMsg.dealSvcRevTrnsfAmt_RL.getValue());
                ZYPEZDItemValueSetter.setValue(rmaLineMsg.funcSvcRevTrnsfAmt_RL, funcAmt);

                funcAmt = comLogic.calcFuncAmt(glblCmpyCd, dealCcyCd, slsDt, rmaLineMsg.dealSvcCostTrnsfAmt_RL.getValue());
                ZYPEZDItemValueSetter.setValue(rmaLineMsg.funcSvcCostTrnsfAmt_RL, funcAmt);

                funcAmt = comLogic.calcFuncAmt(glblCmpyCd, dealCcyCd, slsDt, rmaLineMsg.dealManFlAdjAmt_RL.getValue());
                ZYPEZDItemValueSetter.setValue(rmaLineMsg.funcManFlAdjAmt_RL, funcAmt);

                funcAmt = comLogic.calcFuncAmt(glblCmpyCd, dealCcyCd, slsDt, rmaLineMsg.dealManRepRevAdjAmt_RL.getValue());
                ZYPEZDItemValueSetter.setValue(rmaLineMsg.funcManRepRevAdjAmt_RL, funcAmt);
            }
        }
    }
    // 2016/05/10 S21_NA#7706 Add End
    // QC#22965 2018/04/11 Add Start
    /**
     * setParametersForHeader.
     * @param bizMsg NWAL1500CMsg
     * @param dMsg NWAL1500_DCMsg
     * @param iMsgArray NWAL1500_ISMsgArray
     */
    public static void setParametersForHeader(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, NWAL1500_ISMsgArray iMsgArray, String configCatgCd) {
        bizMsg.xxModeCd_N.clear();
        bizMsg.xxModeCd_PM.clear();
        bizMsg.xxViewChngLogSrcCd_N.clear();
        bizMsg.trxHdrNum_N.clear();
        bizMsg.prcBaseDt_N.clear();
        bizMsg.prcCalcDt_N.clear();
        bizMsg.dsOrdTpCd_N.clear();
        bizMsg.dsOrdCatgCd_N.clear();
        bizMsg.lineBizTpCd_N.clear();
        bizMsg.cpoSrcTpCd_N.clear();
        bizMsg.custIssPoNum_N.clear();
        bizMsg.dsPmtMethCd_N.clear();
        bizMsg.spclHdlgTpCd_N.clear();
        bizMsg.leasePrchOptCd_N.clear();
        bizMsg.dsOrdPosnNum_N.clear();
        bizMsg.trxLineNum_N.clear();
        bizMsg.trxLineSubNum_N.clear();
        bizMsg.configCatgCd_N.clear();
        bizMsg.inEachQty_N.clear();
        bizMsg.shipToFirstLineAddr_N.clear();
        bizMsg.shipToScdLineAddr_N.clear();
        bizMsg.shipToCtyAddr_N.clear();
        bizMsg.shipToStCd_N.clear();
        bizMsg.shipToCntyNm_N.clear();
        bizMsg.shipToPostCd_N.clear();
        bizMsg.shipToCtryCd_N.clear();
        bizMsg.prcCatgCd_N.clear();
        bizMsg.csmpNum_N.clear();
        bizMsg.dlrRefNum_N.clear();
        bizMsg.prcContrNum_N.clear();
        bizMsg.coaBrCd_N.clear();
        bizMsg.mdseCd_N.clear();
        bizMsg.billToCustCd_N.clear();
        bizMsg.billToCustAcctCd_N.clear();
        bizMsg.sellToCustCd_N.clear();
        bizMsg.soldToCustLocCd_N.clear();
        bizMsg.shipToCustCd_N.clear();
        bizMsg.shipToCustAcctCd_N.clear();
        bizMsg.frtCondCd_N.clear();
        bizMsg.shpgSvcLvlCd_N.clear();
        bizMsg.ccyCd_N.clear();
        bizMsg.uomCd_N.clear();
        bizMsg.ordCustUomQty_N.clear();
        bizMsg.dsCpoLineCatgCd_N.clear();
        bizMsg.invQty_N.clear();
        bizMsg.mdlId_N.clear();
        bizMsg.rtrnRsnCd_N.clear();
        bizMsg.coaExtnCd_N.clear();
        bizMsg.slsRepOrSlsTeamTocCd_N.clear();
        bizMsg.rtlWhCd_N.clear();
        bizMsg.xxTotBaseAmt_N.clear();
        bizMsg.xxSubTotCalcPrcAmt_N.clear();
        bizMsg.xxTotChrgPrcAmt_N.clear();
        bizMsg.xxTotDiscAmt_N.clear();
        bizMsg.xxTotSpclPrcAmt_N.clear();
        bizMsg.xxTotNetDiscAmt_N.clear();
        bizMsg.xxUnitNetPrcAmt_N.clear();
        bizMsg.xxUnitGrsPrcAmt_N.clear();
        bizMsg.xxTotNetPrcAmt_N.clear();
        bizMsg.xxGrsAmt_N.clear();
        bizMsg.xxUnitFrtAmt_N.clear();
        bizMsg.xxTotFrtAmt_N.clear();
        bizMsg.xxUnitSpclChrgAmt_N.clear();
        bizMsg.xxTotSpclChrgAmt_N.clear();
        bizMsg.xxTotFrtSubAmt_N.clear();
        bizMsg.xxUnitRestkFeeAmt_N.clear();
        bizMsg.xxTotRestkFeeAmt_N.clear();
        bizMsg.xxTotNetPrcAmt_N2.clear();
        bizMsg.xxTotTaxAmt_N.clear();
        bizMsg.xxUnitPrc_N.clear();
        bizMsg.xxTotAmt_N.clear();
        bizMsg.dealPrcListPrcAmt_N.clear();
        bizMsg.xxSfxKeyTxt_N.clear();
        bizMsg.N.clear();

        BigDecimal totQty = BigDecimal.ZERO;
        BigDecimal invQty = BigDecimal.ZERO;
        BigDecimal xxTotUnitNetWt =  BigDecimal.ZERO;
        BigDecimal xxSubTotCalcPrcAmt = BigDecimal.ZERO;
        BigDecimal xxTotChrgPrcAmt = BigDecimal.ZERO;
        BigDecimal xxTotTaxAmt = BigDecimal.ZERO;
        BigDecimal xxTotAmt = BigDecimal.ZERO;
//        BigDecimal xxTotAmt = BigDecimal.ZERO;
        String ordLineSts = bizMsg.varCharConstVal_TB.getValue();
        if(CONFIG_CATG.OUTBOUND.equals(configCatgCd)){
            for(int i = 0; i < glblMsg.B.getValidCount(); i++){
                NWAL1500_BSMsg lineMsg =  glblMsg.B.no(i);
                if(!ZYPCommonFunc.hasValue(lineMsg.mdseCd_LL)){
                    continue;
                }
                if(!ZYPCommonFunc.hasValue(lineMsg.ordQty_LL)){
                    continue;
                }
                if (ordLineSts.equals(lineMsg.ordLineStsDescTxt_LL.getValue()) 
                        || ORD_LINE_STS.CANCELLED.equals(lineMsg.ordLineStsCd_LL.getValue())) {
                    continue;
                }
                String xxLineNum = lineMsg.xxLineNum_LL.getValue();
                String[] xxLineNumArray = xxLineNum.split("\\.");
                if (xxLineNumArray.length == 3 && BigDecimal.ZERO.compareTo(new BigDecimal(xxLineNumArray[2])) != 0) {
                    continue;
                }
                totQty = totQty.add(lineMsg.ordQty_LL.getValue());
                invQty = invQty.add(lineMsg.invQty_LL.getValue());
                xxTotUnitNetWt = xxTotUnitNetWt.add(getTotWt(bizMsg, lineMsg.mdseCd_LL.getValue(), lineMsg.ordQty_LL.getValue()));
                xxSubTotCalcPrcAmt = xxSubTotCalcPrcAmt.add(lineMsg.lineDealSubTotAmt_LL.getValue());
                xxTotChrgPrcAmt = xxTotChrgPrcAmt.add(lineMsg.lineDealChrgAmt_LL.getValue());
                xxTotTaxAmt = xxTotTaxAmt.add(lineMsg.lineDealTaxAmt_LL.getValue());
                xxTotAmt = xxTotAmt.add(lineMsg.lineDealTotAmt_LL.getValue());
            }
        } else {
            for(int i = 0; i < glblMsg.D.getValidCount(); i++){
                NWAL1500_DSMsg rmaLineMsg =  glblMsg.D.no(i);
                if(!ZYPCommonFunc.hasValue(rmaLineMsg.mdseCd_RL)){
                    continue;
                }
                if(!ZYPCommonFunc.hasValue(rmaLineMsg.ordQty_RL)){
                    continue;
                }
                if (ordLineSts.equals(rmaLineMsg.rtrnLineStsDescTxt_RL.getValue()) 
                        || RTRN_LINE_STS.CANCELLED.equals(rmaLineMsg.ordLineStsCd_RL.getValue()) 
                        || RTRN_LINE_STS.CLOSED.equals(rmaLineMsg.ordLineStsCd_RL.getValue())) {
                    continue;
                }
                String xxLineNum = rmaLineMsg.xxLineNum_RL.getValue();
                String[] xxLineNumArray = xxLineNum.split("\\.");
                if (xxLineNumArray.length == 3 && BigDecimal.ZERO.compareTo(new BigDecimal(xxLineNumArray[2])) != 0) {
                    continue;
                }
                totQty = totQty.add(rmaLineMsg.ordQty_RL.getValue());
                invQty = invQty.add(rmaLineMsg.rtrnQty_RL.getValue());
                xxTotUnitNetWt = xxTotUnitNetWt.add(getTotWt(bizMsg, rmaLineMsg.mdseCd_RL.getValue(), rmaLineMsg.ordQty_RL.getValue()));
                xxSubTotCalcPrcAmt = xxSubTotCalcPrcAmt.add(rmaLineMsg.lineDealSubTotAmt_RL.getValue());
                xxTotChrgPrcAmt = xxTotChrgPrcAmt.add(rmaLineMsg.lineDealChrgAmt_RL.getValue());
                xxTotTaxAmt = xxTotTaxAmt.add(rmaLineMsg.lineDealTaxAmt_RL.getValue());
                xxTotAmt = xxTotAmt.add(rmaLineMsg.lineDealTotAmt_RL.getValue());
            }
        }

        // order header information
        ZYPEZDItemValueSetter.setValue(bizMsg.xxModeCd_N, NWAL1500Constant.NWAL1660_MODE_REGIST);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxModeCd_PM, NWAL1500Constant.NWAL1660_PROCESS_LVL_HEADER);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxViewChngLogSrcCd_N, NWAL1500Constant.NWAL1660_VIEW_SRC_CD); // QC#9700  2018/09/03 Add 
        ZYPEZDItemValueSetter.setValue(bizMsg.trxHdrNum_N, bizMsg.cpoOrdNum);
        ZYPEZDItemValueSetter.setValue(bizMsg.prcBaseDt_N, bizMsg.slsDt);
        ZYPEZDItemValueSetter.setValue(bizMsg.prcCalcDt_N, bizMsg.slsDt);
        ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdTpCd_N, bizMsg.dsOrdTpCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdCatgCd_N, bizMsg.dsOrdCatgCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.lineBizTpCd_N, bizMsg.lineBizTpCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.cpoSrcTpCd_N, bizMsg.cpoSrcTpCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.custIssPoNum_N, bizMsg.custIssPoNum);
        if (ZYPCommonFunc.hasValue(bizMsg.dsCrCardPk)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.dsPmtMethCd_N, DS_PMT_METH.CREDIT_CARD);
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.dsPmtMethCd_N, DS_PMT_METH.INVOICE);
        }
        ZYPEZDItemValueSetter.setValue(bizMsg.spclHdlgTpCd_N, bizMsg.spclHdlgTpCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.leasePrchOptCd_N, bizMsg.leasePrchOptCd);

        // order line information
        ZYPEZDItemValueSetter.setValue(bizMsg.inEachQty_N, totQty);
        ZYPEZDItemValueSetter.setValue(bizMsg.invQty_N, invQty);
        ZYPEZDItemValueSetter.setValue(bizMsg.prcCatgCd_N, bizMsg.prcCatgCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.csmpNum_N, bizMsg.csmpContrNum);
        ZYPEZDItemValueSetter.setValue(bizMsg.dlrRefNum_N, bizMsg.dlrRefNum);
        ZYPEZDItemValueSetter.setValue(bizMsg.prcContrNum_N, bizMsg.prcContrNum);
        ZYPEZDItemValueSetter.setValue(bizMsg.sellToCustCd_N, bizMsg.sellToCustCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.soldToCustLocCd_N, bizMsg.soldToCustLocCd);

        ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustCd_N, bizMsg.shipToCustCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustAcctCd_N, bizMsg.shipToCustAcctCd);

        ZYPEZDItemValueSetter.setValue(bizMsg.frtCondCd_N, bizMsg.frtCondCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.shpgSvcLvlCd_N, bizMsg.shpgSvcLvlCd);
        S21SsmEZDResult ssmRslt = NWAL1500QueryForSaveSubmit.getInstance().checkPriceCatgNm(bizMsg, bizMsg.prcCatgNm.getValue());
        if (ssmRslt.isCodeNormal()) {
            List<Map<String, String>> rsltMapList = (List<Map<String, String>>) ssmRslt.getResultObject();
            PRC_CATGTMsg priceCategory = getPriceCategory(bizMsg.glblCmpyCd.getValue(), rsltMapList.get(0).get("PRC_CATG_CD"));
            if (priceCategory != null) {
                ZYPEZDItemValueSetter.setValue(bizMsg.ccyCd_N, priceCategory.ccyCd);
            }
        }

        // summary information
        ZYPEZDItemValueSetter.setValue(bizMsg.xxSubTotCalcPrcAmt_N, xxSubTotCalcPrcAmt);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxTotChrgPrcAmt_N, xxTotChrgPrcAmt);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxTotTaxAmt_N, xxTotTaxAmt);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxTotAmt_N, xxTotAmt);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxTotTaxAmt_N, xxTotTaxAmt);
//        ZYPEZDItemValueSetter.setValue(bizMsg.xxTotAmt_N, xxTotAmt);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxTotUnitNetWt_N, xxTotTaxAmt);
        ZYPEZDItemValueSetter.setValue(bizMsg.configCatgCd_N, configCatgCd); // QC#9700  2018/09/03 Add

        // price condition
        NWAL1500CommonLogicForPriceChanges.setPriceConditionListForHeader(bizMsg.N, glblMsg.I, configCatgCd);
    }
    
    private static void setPriceConditionListForHeader(NWAL1500_NCMsgArray paramPriceConditionArray, NWAL1500_ISMsgArray priceConditionArray, String configCatgCd) {

        int paramCount = 0;
        paramPriceConditionArray.clear();
        NWAL1500_ISMsg row = null;
        Map<BigDecimal, NWAL1500_ISMsg> map = new HashMap<BigDecimal, NWAL1500_ISMsg>();
        Map<BigDecimal, NWAL1500_ISMsg> roundMap = new HashMap<BigDecimal, NWAL1500_ISMsg>();
        for (int i = 0; i < priceConditionArray.getValidCount(); i++) {
            NWAL1500_ISMsg priceCondition = priceConditionArray.no(i);
            if (configCatgCd.equals(priceCondition.configCatgCd_LP.getValue())) {
                row = map.get(priceCondition.specCondPrcPk_LP.getValue());
                if (PRC_DTL_GRP.ROUNDING_FACTOR_0.equals(priceCondition.prcDtlGrpCd_LP.getValue()) 
                        || PRC_DTL_GRP.ROUNDING_FACTOR_1.equals(priceCondition.prcDtlGrpCd_LP.getValue()) 
                        || PRC_DTL_GRP.ROUNDING_FACTOR_2.equals(priceCondition.prcDtlGrpCd_LP.getValue())) { // QC#21841 2018/05/21
                    NWAL1500_ISMsg roundRow = new NWAL1500_ISMsg();
                    EZDMsg.copy(row, null, roundRow, null);
                    roundMap.put(priceCondition.specCondPrcPk_LP.getValue(), priceCondition);
                } else {
                    if (row == null) {
                        NWAL1500_ISMsg value = new NWAL1500_ISMsg();
                        EZDMsg.copy(priceCondition, null, value, null);
                        map.put(priceCondition.specCondPrcPk_LP.getValue(), value);
                    } else {
                        ZYPEZDItemValueSetter.setValue(row.calcPrcAmtRate_LP, row.calcPrcAmtRate_LP.getValue().add(priceCondition.calcPrcAmtRate_LP.getValue()));
                    }
                }
            }
        }
        for (Map.Entry<BigDecimal, NWAL1500_ISMsg> entry : roundMap.entrySet()) {
            row = map.get(entry.getKey());
            if (row != null) {
                NWAL1500_ISMsg priceCondition = entry.getValue();
                ZYPEZDItemValueSetter.setValue(row.calcPrcAmtRate_LP, row.calcPrcAmtRate_LP.getValue().add(priceCondition.calcPrcAmtRate_LP.getValue()));
            }
        }

        for (Map.Entry<BigDecimal, NWAL1500_ISMsg> entry : map.entrySet()) {
            EZDMsg.copy(entry.getValue(), "LP", paramPriceConditionArray.no(paramCount), "NL");
            paramCount++;
        }
        paramPriceConditionArray.setValidCount(paramCount);

        return;
    }
    private static BigDecimal getTotWt(NWAL1500CMsg bizMsg, String mdseCd, BigDecimal inEachQty) {
        S21SsmEZDResult rslt = NWAL1500QueryForPriceChanges.getInstance().getInPondWt(bizMsg.glblCmpyCd.getValue(), mdseCd);
        if (!rslt.isCodeNormal()) {
            return BigDecimal.ZERO;
        }
        BigDecimal wt = (BigDecimal) rslt.getResultObject();
        if (!ZYPCommonFunc.hasValue(inEachQty)) {
            return BigDecimal.ZERO;
        }
        return inEachQty.multiply(wt).setScale(TOT_WT_SCALE, RoundingMode.HALF_UP);
    }

    // QC#22965 2018/04/11 Add End
}
