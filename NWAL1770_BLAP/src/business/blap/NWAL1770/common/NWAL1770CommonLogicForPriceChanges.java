/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1770.common;

import static business.blap.NWAL1770.constant.NWAL1770Constant.IDX_1;
import static business.blap.NWAL1770.constant.NWAL1770Constant.IDX_3;
import static business.blap.NWAL1770.constant.NWAL1770Constant.TOT_WT_SCALE;
import static business.blap.NWAL1770.constant.NWAL1770MsgConstant.MSG_PARAM_MAX_PRC;
import static business.blap.NWAL1770.constant.NWAL1770MsgConstant.MSG_PARAM_MIN_PRC;
import static business.blap.NWAL1770.constant.NWAL1770MsgConstant.NWAM0775E;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;
import business.blap.NWAL1770.NWAL1770CMsg;
import business.blap.NWAL1770.NWAL1770Query;
import business.blap.NWAL1770.NWAL1770_BCMsg;
import business.blap.NWAL1770.NWAL1770_ECMsg;
import business.blap.NWAL1770.NWAL1770_ECMsgArray;
import business.blap.NWAL1770.NWAL1770_SCMsg;
import business.blap.NWAL1770.NWAL1770_SCMsgArray;
import business.blap.NWAL1770.constant.NWAL1770Constant;
import business.db.PRC_CATGTMsg;
import business.parts.NWZC157001PMsg;

import com.canon.cusa.s21.api.NWZ.NWZC157001.NWZC157001;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_PMT_METH;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PKG_UOM;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_COND_UNIT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_DTL_GRP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SPLY_QUOTE_STS;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/07   Fujitsu         T.Yoshida       Create          N/A
 * 2017/08/07   Fujitsu         Y.Kanefusa      Update          S21_NA#10347
 * 2018/03/19   Fujitsu         A.Kosai         Update          S21_NA#24810
 * 2018/04/16   Fujitsu         N.Sugiura       Update          S21_NA#22965
 * 2018/05/21   Fujitsu         Y.Kanefusa      Update          S21_NA#21841
 * 2018/08/03   Fujitsu         Y.Kanefusa      Update          S21_NA#27479
 * 2018/09/18   Fujitsu         S.Kosaka        Update          QC#9700
 * </pre>
 */
public class NWAL1770CommonLogicForPriceChanges {

    /**
     * setParameters.
     * @param bizMsg bizMsg
     * @param itemLineMsg NWAL1770_BCMsg
     */
    public static void setPrcChangeParam(NWAL1770CMsg bizMsg, NWAL1770_BCMsg itemLineMsg) {

        // Clear Price Change Popup Parameters
        clearPrcChangeParam(bizMsg);

        // Header Information
        String lineStsCd = itemLineMsg.splyQuoteLineStsCd_B.getValue();
        // 2018/03/19 S21_NA#24810 Mod Start
//        String cancelStsTxt = ZYPCodeDataUtil.getVarCharConstValue(KEY_QUOTE_CANCELLED, bizMsg.glblCmpyCd.getValue());
        String cancelStsTxt = NWAL1770CommonLogic.getCancelStsTxt(bizMsg);
        // 2018/03/19 S21_NA#24810 Mod End
        if (cancelStsTxt.equals(itemLineMsg.splyQuoteStsDescTxt_B.getValue()) || SPLY_QUOTE_STS.CANCELLED.equals(lineStsCd) || SPLY_QUOTE_STS.SUBMITTED.equals(lineStsCd)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxModeCd_S, NWAL1770Constant.MODE_REFERENCE);
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxModeCd_S, NWAL1770Constant.MODE_REGIST);
        }
        ZYPEZDItemValueSetter.setValue(bizMsg.xxModeCd_PM, NWAL1770Constant.PROCESS_LVL_LINE); /// QC#22965 Add
        ZYPEZDItemValueSetter.setValue(bizMsg.trxHdrNum_S, bizMsg.splyQuoteNum);
        // QC#10347 2017/07/24 Add Start
        // ZYPEZDItemValueSetter.setValue(bizMsg.prcBaseDt_S, bizMsg.splyQuoteDt);
        ZYPEZDItemValueSetter.setValue(bizMsg.prcBaseDt_S, itemLineMsg.prcBaseDt_B);
        // QC#10347 2017/07/24 Add End
        ZYPEZDItemValueSetter.setValue(bizMsg.prcCalcDt_S, bizMsg.slsDt);
        ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdCatgCd_S, bizMsg.splyQuoteCatgCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdTpCd_S, bizMsg.dsOrdTpCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.lineBizTpCd_S, bizMsg.lineBizTpCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.cpoSrcTpCd_S, bizMsg.cpoSrcTpCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.custIssPoNum_S, bizMsg.custIssPoNum);
        if (ZYPCommonFunc.hasValue(bizMsg.dsCrCardPk)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.dsPmtMethCd_S, DS_PMT_METH.CREDIT_CARD);
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.dsPmtMethCd_S, DS_PMT_METH.INVOICE);
        }
        ZYPEZDItemValueSetter.setValue(bizMsg.spclHdlgTpCd_S, bizMsg.spclHdlgTpCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.frtCondCd_S, bizMsg.frtCondCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.shpgSvcLvlCd_S, bizMsg.shpgSvcLvlCd);

        // Line Information
        ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdPosnNum_S, String.valueOf(IDX_1));
        ZYPEZDItemValueSetter.setValue(bizMsg.trxLineNum_S, itemLineMsg.splyQuoteDtlLineNum_B);
        ZYPEZDItemValueSetter.setValue(bizMsg.trxLineSubNum_S, itemLineMsg.splyQuoteDtlLineSubNum_B);
        ZYPEZDItemValueSetter.setValue(bizMsg.configCatgCd_S, CONFIG_CATG.OUTBOUND);
        ZYPEZDItemValueSetter.setValue(bizMsg.inEachQty_S, itemLineMsg.ordQty_B);
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToFirstLineAddr_S, bizMsg.shipToFirstLineAddr);
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToScdLineAddr_S, bizMsg.shipToScdLineAddr);
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToCtyAddr_S, bizMsg.shipToCtyAddr);
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToStCd_S, bizMsg.shipToStCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToCntyNm_S, bizMsg.shipToCntyNm);
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToPostCd_S, bizMsg.shipToPostCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToCtryCd_S, bizMsg.shipToCtryCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.billToCustCd_S, bizMsg.billToCustCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.billToCustAcctCd_S, bizMsg.billToCustAcctCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.sellToCustCd_S, bizMsg.sellToCustCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.soldToCustLocCd_S, bizMsg.soldToCustLocCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustCd_S, bizMsg.shipToCustCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustAcctCd_S, bizMsg.shipToCustAcctCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.prcCatgCd_S, itemLineMsg.prcCatgCd_B);
        ZYPEZDItemValueSetter.setValue(bizMsg.prcContrNum_S, bizMsg.prcContrNum);
        ZYPEZDItemValueSetter.setValue(bizMsg.coaBrCd_S, bizMsg.coaBrCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.coaExtnCd_S, bizMsg.coaExtnCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.slsRepOrSlsTeamTocCd_S, bizMsg.slsRepTocCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.mdseCd_S, itemLineMsg.mdseCd_B);

        // Price Information
        ZYPEZDItemValueSetter.setValue(bizMsg.ccyCd_S, bizMsg.ccyCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.uomCd_S, itemLineMsg.custUomCd_B);
        ZYPEZDItemValueSetter.setValue(bizMsg.ordCustUomQty_S, itemLineMsg.ordCustUomQty_B);
        ZYPEZDItemValueSetter.setValue(bizMsg.dsCpoLineCatgCd_S, itemLineMsg.dsOrdLineCatgCd_B);
        ZYPEZDItemValueSetter.setValue(bizMsg.invQty_S, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(bizMsg.rtlWhCd_S, itemLineMsg.rtlWhCd_B);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxUnitNetPrcAmt_S, itemLineMsg.dealSplyQuoteDtlNetAmt_B);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxUnitGrsPrcAmt_S, itemLineMsg.dealSplyQuoteDtlSlsAmt_B);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxTotTaxAmt_S, itemLineMsg.xxTotTaxAmt_B);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxTotAmt_S, itemLineMsg.xxTotAmt_B);
        ZYPEZDItemValueSetter.setValue(bizMsg.dealPrcListPrcAmt_S, itemLineMsg.dealSplyQuoteDtlNetAmt_B);
        ZYPEZDItemValueSetter.setValue(bizMsg.dsCpoLineNum_S, itemLineMsg.dplyQuoteLineNum_B);
        ZYPEZDItemValueSetter.setValue(bizMsg.dsCpoLineSubNum_S, itemLineMsg.dplyQuoteLineSubNum_B);
        ZYPEZDItemValueSetter.setValue(bizMsg.dealSvcRevTrnsfAmt_S, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(bizMsg.dealSvcCostTrnsfAmt_S, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(bizMsg.dealManFlAdjAmt_S, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(bizMsg.dealManRepRevAdjAmt_S, BigDecimal.ZERO);

        // Calc Base Information
        NWAL1770CommonLogicForPriceChanges.setCalcBaseParam(bizMsg, itemLineMsg);
    }

    /**
     * Clear Price Change Parameters
     * @param bizMsg bizMsg
     */
    private static void clearPrcChangeParam(NWAL1770CMsg bizMsg) {

        bizMsg.xxModeCd_S.clear();
        bizMsg.xxModeCd_PM.clear(); // S21_NA#22965 Add
        bizMsg.trxHdrNum_S.clear();
        bizMsg.prcBaseDt_S.clear();
        bizMsg.prcCalcDt_S.clear();
        bizMsg.dsOrdTpCd_S.clear();
        bizMsg.dsOrdCatgCd_S.clear();
        bizMsg.lineBizTpCd_S.clear();
        bizMsg.cpoSrcTpCd_S.clear();
        bizMsg.custIssPoNum_S.clear();
        bizMsg.dsPmtMethCd_S.clear();
        bizMsg.spclHdlgTpCd_S.clear();
        bizMsg.leasePrchOptCd_S.clear();
        bizMsg.dsOrdPosnNum_S.clear();
        bizMsg.trxLineNum_S.clear();
        bizMsg.trxLineSubNum_S.clear();
        bizMsg.configCatgCd_S.clear();
        bizMsg.inEachQty_S.clear();
        bizMsg.shipToFirstLineAddr_S.clear();
        bizMsg.shipToScdLineAddr_S.clear();
        bizMsg.shipToCtyAddr_S.clear();
        bizMsg.shipToStCd_S.clear();
        bizMsg.shipToCntyNm_S.clear();
        bizMsg.shipToPostCd_S.clear();
        bizMsg.shipToCtryCd_S.clear();
        bizMsg.prcCatgCd_S.clear();
        bizMsg.csmpNum_S.clear();
        bizMsg.dlrRefNum_S.clear();
        bizMsg.prcContrNum_S.clear();
        bizMsg.coaBrCd_S.clear();
        bizMsg.mdseCd_S.clear();
        bizMsg.billToCustCd_S.clear();
        bizMsg.billToCustAcctCd_S.clear();
        bizMsg.sellToCustCd_S.clear();
        bizMsg.soldToCustLocCd_S.clear();
        bizMsg.shipToCustCd_S.clear();
        bizMsg.shipToCustAcctCd_S.clear();
        bizMsg.frtCondCd_S.clear();
        bizMsg.shpgSvcLvlCd_S.clear();
        bizMsg.ccyCd_S.clear();
        bizMsg.uomCd_S.clear();
        bizMsg.ordCustUomQty_S.clear();
        bizMsg.dsCpoLineCatgCd_S.clear();
        bizMsg.invQty_S.clear();
        bizMsg.mdlId_S.clear();
        bizMsg.rtrnRsnCd_S.clear();
        bizMsg.coaExtnCd_S.clear();
        bizMsg.slsRepOrSlsTeamTocCd_S.clear();
        bizMsg.rtlWhCd_S.clear();
        bizMsg.xxTotBaseAmt_S.clear();
        bizMsg.xxSubTotCalcPrcAmt_S.clear();
        bizMsg.xxTotChrgPrcAmt_S.clear();
        bizMsg.xxTotDiscAmt_S.clear();
        bizMsg.xxTotSpclPrcAmt_S.clear();
        bizMsg.xxTotNetDiscAmt_S.clear();
        bizMsg.xxUnitNetPrcAmt_S.clear();
        bizMsg.xxUnitGrsPrcAmt_S.clear();
        bizMsg.xxTotNetPrcAmt_S.clear();
        bizMsg.xxGrsAmt_S.clear();
        bizMsg.xxUnitFrtAmt_S.clear();
        bizMsg.xxTotFrtAmt_S.clear();
        bizMsg.xxUnitSpclChrgAmt_S.clear();
        bizMsg.xxTotSpclChrgAmt_S.clear();
        bizMsg.xxTotFrtSubAmt_S.clear();
        bizMsg.xxUnitRestkFeeAmt_S.clear();
        bizMsg.xxTotRestkFeeAmt_S.clear();
        bizMsg.xxTotNetPrcAmt_S2.clear();
        bizMsg.xxTotTaxAmt_S.clear();
        bizMsg.xxUnitPrc_S.clear();
        bizMsg.xxTotAmt_S.clear();
        bizMsg.xxTotUnitNetWt_S.clear(); // S21_NA#22965 Add
        bizMsg.dealPrcListPrcAmt_S.clear();
        bizMsg.dsCpoLineNum_S.clear();
        bizMsg.dsCpoLineSubNum_S.clear();
        bizMsg.dealSvcRevTrnsfAmt_S.clear();
        bizMsg.dealSvcCostTrnsfAmt_S.clear();
        bizMsg.dealManFlAdjAmt_S.clear();
        bizMsg.dealManRepRevAdjAmt_S.clear();
        bizMsg.xxSfxKeyTxt_S.clear();
        ZYPTableUtil.clear(bizMsg.S);
    }

    /**
     * Set Calc Base Parameters
     * @param bizMsg NWAL1770CMsg
     * @param lineMsg NWAL1770CMsg
     */
    private static void setCalcBaseParam(NWAL1770CMsg bizMsg, NWAL1770_BCMsg lineMsg) {

        List<NWAL1770_ECMsg> calcBaseList = getCalcBaseList(bizMsg, lineMsg);
        int paramCount = 0;

        for (NWAL1770_ECMsg priceCondition : calcBaseList) {
            EZDMsg.copy(priceCondition, "E", bizMsg.S.no(paramCount), "CB");
            paramCount++;
        }
        bizMsg.S.setValidCount(calcBaseList.size());

        return;
    }

    /**
     * Get Calc Base List
     * @param bizMsg NWAL1770CMsg
     * @param lineMsg NWAL1770_BCMsg
     */
    private static List<NWAL1770_ECMsg> getCalcBaseList(NWAL1770CMsg bizMsg, NWAL1770_BCMsg lineMsg) {

        List<NWAL1770_ECMsg> calcBaseList = new ArrayList<NWAL1770_ECMsg>();
        for (int i = 0; i < bizMsg.E.getValidCount(); i++) {
            NWAL1770_ECMsg calcBaseMsg = bizMsg.E.no(i);
            if (isSameLine(lineMsg, calcBaseMsg)) {
                calcBaseList.add(calcBaseMsg);
            }
        }
        return calcBaseList;
    }

    /**
     * Check Same Line
     * @param lineMsg NWAL1770_BCMsg
     * @param calcBaseMsg NWAL1770_ECMsg
     */
    private static boolean isSameLine(NWAL1770_BCMsg lineMsg, NWAL1770_ECMsg calcBaseMsg) {

        if (!S21StringUtil.isEquals(lineMsg.splyQuoteDtlLineNum_B.getValue(), calcBaseMsg.splyQuoteDtlLineNum_E.getValue())) {
            return false;
        }

        if (ZYPCommonFunc.hasValue(lineMsg.splyQuoteDtlLineSubNum_B)) {
            if (!S21StringUtil.isEquals(lineMsg.splyQuoteDtlLineSubNum_B.getValue(), calcBaseMsg.splyQuoteDtlLineSubNum_E.getValue())) {
                return false;
            }
        }

        return true;
    }

    /**
     * Set ScreenItems From Price Changes
     * @param bizMsg NWAL1770CMsg
     * @param lineMsg NWAL1770_BCMsg
     */
    public static void setScreenItemsFromPrcChange(NWAL1770CMsg bizMsg, NWAL1770_BCMsg lineMsg) {

        ZYPEZDItemValueSetter.setValue(lineMsg.dealSplyQuoteDtlSlsAmt_B, bizMsg.xxUnitGrsPrcAmt_S);
        ZYPEZDItemValueSetter.setValue(lineMsg.dealSplyQuoteDtlNetAmt_B, bizMsg.xxUnitNetPrcAmt_S);
        ZYPEZDItemValueSetter.setValue(lineMsg.xxSubTotCalcPrcAmt_B, bizMsg.xxTotNetPrcAmt_S);
        ZYPEZDItemValueSetter.setValue(lineMsg.xxTotFrtAmt_B, bizMsg.xxTotChrgPrcAmt_S);
        ZYPEZDItemValueSetter.setValue(lineMsg.xxTotTaxAmt_B, bizMsg.xxTotTaxAmt_S);
        ZYPEZDItemValueSetter.setValue(lineMsg.xxTotAmt_B, bizMsg.xxTotAmt_S);

        // Set Calc Base
        setCalcBaseFromPrcChange(bizMsg, lineMsg);
    }

    /**
     * Set Calc Base From Price Changes
     * @param bizMsg NWAL1770CMsg
     * @param lineMsg NWAL1770_BCMsg
     */
    private static void setCalcBaseFromPrcChange(NWAL1770CMsg bizMsg, NWAL1770_BCMsg lineMsg) {

        // Delete
        delCalcBase(bizMsg, lineMsg);

        // Add
        addCalcBase(bizMsg, lineMsg);
    }

    /**
     * Delete Calc Base
     * @param bizMsg NWAL1770CMsg
     * @param lineMsg NWAL1770_BCMsg
     */
    private static void delCalcBase(NWAL1770CMsg bizMsg, NWAL1770_BCMsg lineMsg) {

        List<Integer> deleteIndexList = new ArrayList<Integer>();

        for (int calcBaseIndex = 0; calcBaseIndex < bizMsg.E.getValidCount(); calcBaseIndex++) {
            NWAL1770_ECMsg calcBaseMsg = bizMsg.E.no(calcBaseIndex);
            if (isSameLine(lineMsg, calcBaseMsg)) {
                deleteIndexList.add(calcBaseIndex);
            }
        }

        ZYPTableUtil.deleteRows(bizMsg.E, deleteIndexList);
    }

    /**
     * Add Calc Base
     * @param bizMsg NWAL1770CMsg
     * @param lineMsg NWAL1770_BCMsg
     */
    private static void addCalcBase(NWAL1770CMsg bizMsg, NWAL1770_BCMsg lineMsg) {

        for (int sourceIndex = 0; sourceIndex < bizMsg.S.getValidCount(); sourceIndex++) {
            NWAL1770_SCMsg sourceCalcBaseMsg = bizMsg.S.no(sourceIndex);
            int addIndex = bizMsg.E.getValidCount();
            bizMsg.E.setValidCount(addIndex + 1);
            NWAL1770_ECMsg calcBaseMsg = bizMsg.E.no(addIndex);
            EZDMsg.copy(sourceCalcBaseMsg, "CB", calcBaseMsg, "E");
            ZYPEZDItemValueSetter.setValue(calcBaseMsg.splyQuoteNum_E, bizMsg.splyQuoteNum);
            ZYPEZDItemValueSetter.setValue(calcBaseMsg.splyQuoteDtlLineNum_E, lineMsg.splyQuoteDtlLineNum_B);
            ZYPEZDItemValueSetter.setValue(calcBaseMsg.splyQuoteDtlLineSubNum_E, lineMsg.splyQuoteDtlLineSubNum_B);
        }

        // bizMsg.E.setValidCount(bizMsg.E.getValidCount() + bizMsg.S.getValidCount());
    }

    /**
     * Check Price Range.
     * @param lineMsg NWAL1770_BCMsg
     * @param calcBaseMsg NWAL1770_ECMsg
     * @return boolean
     */
    public static boolean checkPriceRange(NWAL1770_BCMsg lineMsg, NWAL1770_ECMsg calcBaseMsg) {

        if (!checkAmtRange(lineMsg, calcBaseMsg)) {
            return false;
        }
        return true;
    }

    /**
     * Check Amount Range
     * @param lineMsg NWAL1770_BCMsg
     * @param calcBaseMsg NWAL1770_ECMsg
     * @return Check OK : true
     */
    private static boolean checkAmtRange(NWAL1770_BCMsg lineMsg, NWAL1770_ECMsg calcBaseMsg) {

        if (!ZYPCommonFunc.hasValue(lineMsg.dealSplyQuoteDtlNetAmt_B)) {
            return true;
        }

        if (ZYPCommonFunc.hasValue(calcBaseMsg.minPrcAmtRate_E)) {
            if (lineMsg.dealSplyQuoteDtlNetAmt_B.getValue().compareTo(calcBaseMsg.minPrcAmtRate_E.getValue()) < 0) {
                lineMsg.dealSplyQuoteDtlSlsAmt_B.setErrorInfo(1, NWAM0775E, new String[] {MSG_PARAM_MIN_PRC, calcBaseMsg.minPrcAmtRate_E.getValue().toString() });
                return false;
            }
        }

        if (ZYPCommonFunc.hasValue(calcBaseMsg.maxPrcAmtRate_E)) {
            if (lineMsg.dealSplyQuoteDtlNetAmt_B.getValue().compareTo(calcBaseMsg.maxPrcAmtRate_E.getValue()) > 0) {
                lineMsg.dealSplyQuoteDtlSlsAmt_B.setErrorInfo(1, NWAM0775E, new String[] {MSG_PARAM_MAX_PRC, calcBaseMsg.maxPrcAmtRate_E.getValue().toString() });
                return false;
            }
        }

        return true;
    }

    // S21_NA#22965 Add Start
    /**
     * setPrcChangeParamForHeader.
     * @param bizMsg bizMsg
     * @param lineMsg NWAL1770_BCMsg
     */
    public static void setPrcChangeParamForHeader(NWAL1770CMsg bizMsg) {

        // Clear Price Change Popup Parameters
        clearPrcChangeParam(bizMsg);

        // Header Information
        ZYPEZDItemValueSetter.setValue(bizMsg.xxModeCd_S, NWAL1770Constant.MODE_REGIST);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxModeCd_PM, NWAL1770Constant.PROCESS_LVL_HEADER);
        ZYPEZDItemValueSetter.setValue(bizMsg.trxHdrNum_S, bizMsg.splyQuoteNum);
        ZYPEZDItemValueSetter.setValue(bizMsg.prcBaseDt_S, bizMsg.slsDt);
        ZYPEZDItemValueSetter.setValue(bizMsg.prcCalcDt_S, bizMsg.slsDt);
        ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdCatgCd_S, bizMsg.splyQuoteCatgCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdTpCd_S, bizMsg.dsOrdTpCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.lineBizTpCd_S, bizMsg.lineBizTpCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.cpoSrcTpCd_S, bizMsg.cpoSrcTpCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.custIssPoNum_S, bizMsg.custIssPoNum);
        ZYPEZDItemValueSetter.setValue(bizMsg.dsPmtMethCd_S, DS_PMT_METH.INVOICE);
        ZYPEZDItemValueSetter.setValue(bizMsg.spclHdlgTpCd_S, bizMsg.spclHdlgTpCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.frtCondCd_S, bizMsg.frtCondCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.shpgSvcLvlCd_S, bizMsg.shpgSvcLvlCd);

        // Line Information
        BigDecimal totQty = BigDecimal.ZERO;
        BigDecimal xxTotUnitNetWt = BigDecimal.ZERO;
        BigDecimal xxSubTotCalcPrcAmt = BigDecimal.ZERO;
        BigDecimal xxTotChrgPrcAmt = BigDecimal.ZERO;
        BigDecimal xxTotTaxAmt = BigDecimal.ZERO;
        BigDecimal xxTotAmt = BigDecimal.ZERO;
        for(int i = 0; i < bizMsg.B.getValidCount(); i++) {
            NWAL1770_BCMsg line =  bizMsg.B.no(i);
            if (ZYPCommonFunc.hasValue(line.ordQty_B)) {
                totQty = totQty.add(line.ordQty_B.getValue());
            }
            if (ZYPCommonFunc.hasValue(line.xxSubTotCalcPrcAmt_B)) {
                xxSubTotCalcPrcAmt = xxSubTotCalcPrcAmt.add(line.xxSubTotCalcPrcAmt_B.getValue());
            }
            if (ZYPCommonFunc.hasValue(line.xxTotFrtAmt_B)) {
                xxTotChrgPrcAmt = xxTotChrgPrcAmt.add(line.xxTotFrtAmt_B.getValue());
            }
            if (ZYPCommonFunc.hasValue(line.xxTotTaxAmt_B)) {
                xxTotTaxAmt = xxTotTaxAmt.add(line.xxTotTaxAmt_B.getValue());
            }
            if (ZYPCommonFunc.hasValue(line.xxTotAmt_B)) {
                xxTotAmt = xxTotAmt.add(line.xxTotAmt_B.getValue());
            }
            if (ZYPCommonFunc.hasValue(line.mdseCd_B) && ZYPCommonFunc.hasValue(line.ordQty_B)) {
                xxTotUnitNetWt = xxTotUnitNetWt.add(getTotWt(bizMsg, line.mdseCd_B.getValue(), line.ordQty_B.getValue()));
            }
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.configCatgCd_S, CONFIG_CATG.OUTBOUND);
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToFirstLineAddr_S, bizMsg.shipToFirstLineAddr);
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToScdLineAddr_S, bizMsg.shipToScdLineAddr);
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToCtyAddr_S, bizMsg.shipToCtyAddr);
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToStCd_S, bizMsg.shipToStCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToCntyNm_S, bizMsg.shipToCntyNm);
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToPostCd_S, bizMsg.shipToPostCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToCtryCd_S, bizMsg.shipToCtryCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.billToCustCd_S, bizMsg.billToCustCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.billToCustAcctCd_S, bizMsg.billToCustAcctCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.sellToCustCd_S, bizMsg.sellToCustCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.soldToCustLocCd_S, bizMsg.soldToCustLocCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustCd_S, bizMsg.shipToCustCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustAcctCd_S, bizMsg.shipToCustAcctCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.prcCatgCd_S, bizMsg.prcCatgCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.prcContrNum_S, bizMsg.prcContrNum);
        ZYPEZDItemValueSetter.setValue(bizMsg.coaBrCd_S, bizMsg.coaBrCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.coaExtnCd_S, bizMsg.coaExtnCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.slsRepOrSlsTeamTocCd_S, bizMsg.slsRepTocCd);
        PRC_CATGTMsg priceCategory = NWAL1770CommonLogic.getPriceCategory(bizMsg.glblCmpyCd.getValue(), bizMsg.prcCatgCd.getValue());
        if (priceCategory != null) {
            ZYPEZDItemValueSetter.setValue(bizMsg.ccyCd_S, priceCategory.ccyCd);
        }

        // summary information
        ZYPEZDItemValueSetter.setValue(bizMsg.xxSubTotCalcPrcAmt_S, xxSubTotCalcPrcAmt);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxTotChrgPrcAmt_S, xxTotChrgPrcAmt);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxTotTaxAmt_S, xxTotTaxAmt);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxTotAmt_S, xxTotAmt);
        ZYPEZDItemValueSetter.setValue(bizMsg.inEachQty_S, totQty);
        ZYPEZDItemValueSetter.setValue(bizMsg.uomCd_S, PKG_UOM.EACH);
        ZYPEZDItemValueSetter.setValue(bizMsg.ordCustUomQty_S, totQty);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxTotDiscAmt_S, BigDecimal.ZERO);

        ZYPEZDItemValueSetter.setValue(bizMsg.invQty_S, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(bizMsg.dealSvcRevTrnsfAmt_S, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(bizMsg.dealSvcCostTrnsfAmt_S, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(bizMsg.dealManFlAdjAmt_S, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(bizMsg.dealManRepRevAdjAmt_S, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxTotUnitNetWt_S, xxTotUnitNetWt);

        // Calc Base Information
        NWAL1770CommonLogicForPriceChanges.setCalcBaseParamForHeader(bizMsg.S, bizMsg.E);
    }

    private static void setCalcBaseParamForHeader(NWAL1770_SCMsgArray paramPriceConditionArray, NWAL1770_ECMsgArray priceConditionArray) {

        int paramCount = 0;
        paramPriceConditionArray.clear();
        NWAL1770_ECMsg row = null;
        Map<BigDecimal, NWAL1770_ECMsg> map = new HashMap<BigDecimal, NWAL1770_ECMsg>();
        Map<BigDecimal, NWAL1770_ECMsg> roundMap = new HashMap<BigDecimal, NWAL1770_ECMsg>();
        for (int i = 0; i < priceConditionArray.getValidCount(); i++) {
            NWAL1770_ECMsg priceCondition = priceConditionArray.no(i);
            row = map.get(priceCondition.specCondPrcPk_E.getValue());
            if (PRC_DTL_GRP.ROUNDING_FACTOR_0.equals(priceCondition.prcDtlGrpCd_E.getValue()) 
                    || PRC_DTL_GRP.ROUNDING_FACTOR_1.equals(priceCondition.prcDtlGrpCd_E.getValue())
                    || PRC_DTL_GRP.ROUNDING_FACTOR_2.equals(priceCondition.prcDtlGrpCd_E.getValue())) { // QC#21841 2018/05/21 Add
                NWAL1770_BCMsg roundRow = new NWAL1770_BCMsg();
                EZDMsg.copy(row, null, roundRow, null);
                roundMap.put(priceCondition.specCondPrcPk_E.getValue(), priceCondition);
            } else {
                if (row == null) {
                    NWAL1770_ECMsg value = new NWAL1770_ECMsg();
                    EZDMsg.copy(priceCondition, null, value, null);
                    map.put(priceCondition.specCondPrcPk_E.getValue(), value);
                } else {
                    ZYPEZDItemValueSetter.setValue(row.calcPrcAmtRate_E, row.calcPrcAmtRate_E.getValue().add(priceCondition.calcPrcAmtRate_E.getValue()));
                }
            }
        }
        for (Map.Entry<BigDecimal, NWAL1770_ECMsg> entry : roundMap.entrySet()) {
            row = map.get(entry.getKey());
            if (row != null) {
                NWAL1770_ECMsg priceCondition = entry.getValue();
                ZYPEZDItemValueSetter.setValue(row.calcPrcAmtRate_E, row.calcPrcAmtRate_E.getValue().add(priceCondition.calcPrcAmtRate_E.getValue()));
            }
        }

        for (Map.Entry<BigDecimal, NWAL1770_ECMsg> entry : map.entrySet()) {
            EZDMsg.copy(entry.getValue(), "E", paramPriceConditionArray.no(paramCount), "CB");
            paramCount++;
        }
        paramPriceConditionArray.setValidCount(paramCount);

        return;
    }

    public static void setScreenItemsFromPrcChangeForHeader(NWAL1770CMsg bizMsg) {

        String cancelStsTxt = NWAL1770CommonLogic.getCancelStsTxt(bizMsg);
        String trxLineNum = null;
        String trxLineSubNum = null;
        for (int idx = 0; idx < bizMsg.B.getValidCount(); idx++) {
            NWAL1770_BCMsg lineMsg = bizMsg.B.no(idx);
            // Skip Cancel Line
            if (cancelStsTxt.equals(lineMsg.splyQuoteStsDescTxt_B.getValue()) || SPLY_QUOTE_STS.CANCELLED.equals(lineMsg.splyQuoteLineStsCd_B.getValue())) {
                continue;
            }

            // Skip Set Component
            String[] xxLineNumArray = lineMsg.xxLineNum_B.getValue().split("\\.");
            if (xxLineNumArray.length == IDX_3 && BigDecimal.ZERO.compareTo(new BigDecimal(xxLineNumArray[2])) != 0) {
                continue;
            }
            trxLineNum = lineMsg.splyQuoteDtlLineNum_B.getValue();
            trxLineSubNum = lineMsg.splyQuoteDtlLineSubNum_B.getValue();
            break;
        }
        if (trxLineNum == null) {
            return;
        }

        for (int n = 0; n < bizMsg.S.getValidCount(); n++) {
            NWAL1770_SCMsg sourcePriceCondition = bizMsg.S.no(n);
            for (int i = 0; i < bizMsg.E.getValidCount(); i++) {
                NWAL1770_ECMsg priceCondition = bizMsg.E.no(i);
                if (sourcePriceCondition.specCondPrcPk_CB.getValue().equals(priceCondition.specCondPrcPk_E.getValue())) {
                    ZYPEZDItemValueSetter.setValue(priceCondition.prcCondManEntryFlg_E, sourcePriceCondition.prcCondManEntryFlg_CB);
                    ZYPEZDItemValueSetter.setValue(priceCondition.prcCondManAddFlg_E, sourcePriceCondition.prcCondManAddFlg_CB);
                    ZYPEZDItemValueSetter.setValue(priceCondition.prcCondManDelFlg_E, sourcePriceCondition.prcCondManDelFlg_CB);
                    ZYPEZDItemValueSetter.setValue(priceCondition.prcCondUnitCd_E, sourcePriceCondition.prcCondUnitCd_CB);
                    ZYPEZDItemValueSetter.setValue(priceCondition.calcPrcAmtRate_E, BigDecimal.ZERO);
                    ZYPEZDItemValueSetter.setValue(priceCondition.unitPrcAmt_E, BigDecimal.ZERO);
                    // QC#9700  2018/09/18 Add Start
                    ZYPEZDItemValueSetter.setValue(priceCondition.prcRuleApplyFlg_E, sourcePriceCondition.prcRuleApplyFlg_CB);
                    ZYPEZDItemValueSetter.setValue(priceCondition.prcRulePrcdPk_E, sourcePriceCondition.prcRulePrcdPk_CB);
                    // QC#9700  2018/09/18 Add End
                    if(PRC_COND_UNIT.PCT.equals(sourcePriceCondition.prcCondUnitCd_CB.getValue())){
                        ZYPEZDItemValueSetter.setValue(priceCondition.manPrcAmtRate_E, sourcePriceCondition.manPrcAmtRate_CB);
                    }
                }
            }
            // Create Calc_Base
            NWAL1770_ECMsg row = bizMsg.E.no(bizMsg.E.getValidCount());
            ZYPEZDItemValueSetter.setValue(row.splyQuoteDtlLineNum_E, trxLineNum);
            ZYPEZDItemValueSetter.setValue(row.splyQuoteDtlLineSubNum_E, trxLineSubNum);
            ZYPEZDItemValueSetter.setValue(row.prcCondTpCd_E, sourcePriceCondition.prcCondTpCd_CB);
            ZYPEZDItemValueSetter.setValue(row.prcCondTpDescTxt_E, sourcePriceCondition.prcCondTpDescTxt_CB);
            if (PRC_DTL_GRP.DISCOUNT.equals(sourcePriceCondition.prcDtlGrpCd_CB.getValue())) {
                ZYPEZDItemValueSetter.setValue(row.prcDtlGrpCd_E, PRC_DTL_GRP.ROUNDING_FACTOR_0);
            } else if (PRC_DTL_GRP.FREIGHT.equals(sourcePriceCondition.prcDtlGrpCd_CB.getValue())) {
                ZYPEZDItemValueSetter.setValue(row.prcDtlGrpCd_E, PRC_DTL_GRP.ROUNDING_FACTOR_1);
            // QC#21841 2018/05/21 Add Start
            } else if (PRC_DTL_GRP.HANDLING_FEE.equals(sourcePriceCondition.prcDtlGrpCd_CB.getValue())) {
                ZYPEZDItemValueSetter.setValue(row.prcDtlGrpCd_E, PRC_DTL_GRP.ROUNDING_FACTOR_2);
            } else if (PRC_DTL_GRP.FUEL_SURCHARGE.equals(sourcePriceCondition.prcDtlGrpCd_CB.getValue())) {
                ZYPEZDItemValueSetter.setValue(row.prcDtlGrpCd_E, PRC_DTL_GRP.ROUNDING_FACTOR_2);
            } else if (PRC_DTL_GRP.SHIPPING_FEE.equals(sourcePriceCondition.prcDtlGrpCd_CB.getValue())) {
                ZYPEZDItemValueSetter.setValue(row.prcDtlGrpCd_E, PRC_DTL_GRP.ROUNDING_FACTOR_2);
            // QC#21841 2018/05/21 Add End
            } else if (PRC_DTL_GRP.RESTOCKING_FEE.equals(sourcePriceCondition.prcDtlGrpCd_CB.getValue())) { // QC#27479 2018/08/03 Add
                ZYPEZDItemValueSetter.setValue(row.prcDtlGrpCd_E, PRC_DTL_GRP.ROUNDING_FACTOR_2);
            }
            ZYPEZDItemValueSetter.setValue(row.prcCondManEntryFlg_E, sourcePriceCondition.prcCondManEntryFlg_CB);
            ZYPEZDItemValueSetter.setValue(row.prcCondManAddFlg_E, sourcePriceCondition.prcCondManAddFlg_CB);
            ZYPEZDItemValueSetter.setValue(row.prcCondManDelFlg_E, sourcePriceCondition.prcCondManDelFlg_CB);
            ZYPEZDItemValueSetter.setValue(row.pkgUomCd_E, sourcePriceCondition.uomCd_CB.getValue());
            ZYPEZDItemValueSetter.setValue(row.prcCondUnitCd_E, sourcePriceCondition.prcCondUnitCd_CB);
            ZYPEZDItemValueSetter.setValue(row.autoPrcCcyCd_E, sourcePriceCondition.autoPrcCcyCd_CB.getValue());
            ZYPEZDItemValueSetter.setValue(row.manPrcAmtRate_E, sourcePriceCondition.manPrcAmtRate_CB);
            ZYPEZDItemValueSetter.setValue(row.unitPrcAmt_E, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(row.calcPrcAmtRate_E, sourcePriceCondition.calcPrcAmtRate_CB.getValue());
            ZYPEZDItemValueSetter.setValue(row.specCondPrcPk_E, sourcePriceCondition.specCondPrcPk_CB);
            // QC#9700  2018/09/18 Add Start
            ZYPEZDItemValueSetter.setValue(row.prcRuleApplyFlg_E, sourcePriceCondition.prcRuleApplyFlg_CB);
            ZYPEZDItemValueSetter.setValue(row.prcRulePrcdPk_E, sourcePriceCondition.prcRulePrcdPk_CB);
            // QC#9700  2018/09/18 Add End
            bizMsg.E.setValidCount(bizMsg.E.getValidCount() + 1);

        }
        if (bizMsg.S.getValidCount() > 0) {
            NWZC157001PMsg prcApiPMsg = NWAL1770CommonLogicForPricing.callPricingApi(bizMsg, NWZC157001.MANUAL_PRICE);
            if (prcApiPMsg == null) {
                return;
            }
            // Setup Price Elements
            NWAL1770CommonLogicForPricing.setPriceElement(bizMsg, prcApiPMsg);
        }

    }


    /**
     * setHdrParam
     * @param bizMsg    NWAL1770CMsg
     * @param prcApiPMsg    NWZC157001PMsg
     */
    public static void setHdrParam(NWAL1770CMsg bizMsg, NWZC157001PMsg prcApiPMsg, String mode) {
        // Header
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        prcApiPMsg.xxModeCd.setValue(mode);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcBaseDt, bizMsg.slsDt);
        prcApiPMsg.prcCtxTpCd.setValue(PRC_CTX_TP.SALES_PRICING);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.orgRqstDelyDt, bizMsg.slsDt);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.dsOrdCatgCd, bizMsg.splyQuoteCatgCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.dsOrdTpCd, bizMsg.dsOrdTpCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.lineBizTpCd, bizMsg.lineBizTpCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.dsAcctNum, bizMsg.sellToCustCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.cpoSrcTpCd, bizMsg.cpoSrcTpCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.trxHdrNum, bizMsg.splyQuoteNum);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcContrNum, bizMsg.prcContrNum);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.coaBrCd, NWAL1770CommonLogic.getCoaBrCd(bizMsg));
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.custIssPoNum, bizMsg.custIssPoNum);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.taxCalcFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.spclHdlgTpCd, bizMsg.spclHdlgTpCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.xxSmryLineFlg, ZYPConstant.FLG_ON_Y);
    }

    private static BigDecimal getTotWt(NWAL1770CMsg bizMsg, String mdseCd, BigDecimal inEachQty) {
        S21SsmEZDResult rslt = NWAL1770Query.getInstance().getInPondWt(bizMsg.glblCmpyCd.getValue(), mdseCd);
        if (!rslt.isCodeNormal()) {
            return BigDecimal.ZERO;
        }
        BigDecimal wt = (BigDecimal) rslt.getResultObject();
        if (!ZYPCommonFunc.hasValue(inEachQty)) {
            return BigDecimal.ZERO;
        }
        return inEachQty.multiply(wt).setScale(TOT_WT_SCALE, RoundingMode.HALF_UP);
    }
    // S21_NA#22965 Add End

}
