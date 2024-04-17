/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1840.common;

import static business.blap.NWAL1840.constant.NWAL1840Constant.IDX_1;
import static business.blap.NWAL1840.constant.NWAL1840Constant.IDX_3;
import static business.blap.NWAL1840.constant.NWAL1840Constant.SCHD_SUB_LINE_NUM_001;
import static business.blap.NWAL1840.constant.NWAL1840Constant.TOT_WT_SCALE;
import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.MSG_PARAM_MAX_PRC;
import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.MSG_PARAM_MIN_PRC;
import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.NWAM0775E;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;
import business.blap.NWAL1660.constant.NWAL1660Constant;
import business.blap.NWAL1840.NWAL1840CMsg;
import business.blap.NWAL1840.NWAL1840Query;
import business.blap.NWAL1840.NWAL1840_ACMsg;
import business.blap.NWAL1840.NWAL1840_ECMsg;
import business.blap.NWAL1840.NWAL1840_ECMsgArray;
import business.blap.NWAL1840.NWAL1840_NCMsg;
import business.blap.NWAL1840.NWAL1840_NCMsgArray;
import business.db.PRC_CATGTMsg;
import business.parts.NWZC157001PMsg;
import business.parts.NWZC157002PMsg;
import business.parts.NWZC157003PMsg;
import business.parts.NWZC157004PMsg;

import com.canon.cusa.s21.api.NWZ.NWZC157001.NWZC157001;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_PMT_METH;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PKG_UOM;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_COND_UNIT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_DTL_GRP;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/23   Fujitsu         T.Murai         Create          N/A
 * 2017/06/30   Fujitsu         A.Kosai         Update          QC#18332
 * 2018/04/16   Fujitsu         H.Nagashima     Update          QC#22965
 * 2018/05/21   Fujitsu         Y.Kanefusa      Update          S21_NA#21841
 * 2018/08/03   Fujitsu         Y.Kanefusa      Update          S21_NA#27479
 * 2018/09/14   Fujitsu         M.Ohno          Update          QC#9700
 * </pre>
 */
public class NWAL1840CommonLogicForPriceChanges {

    /** Log Out */
    private static final boolean LOG_OUT = false;

    /**
     * setParameters.
     * @param bizMsg bizMsg
     * @param lineMsg NWAL1840_ACMsg
     */
    public static void setPrcChangeParam(NWAL1840CMsg bizMsg, NWAL1840_ACMsg lineMsg) {

        // Clear Price Change Popup Parameters
        clearPrcChangeParam(bizMsg);

        // Header Information
        if (ZYPConstant.FLG_ON_Y.equals(lineMsg.schdAgmtLineCancFlg_A.getValue())) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxModeCd_N, NWAL1660Constant.MODE_REFERENCE);
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxModeCd_N, NWAL1660Constant.MODE_REGIST);
        }
        ZYPEZDItemValueSetter.setValue(bizMsg.xxModeCd_PM, NWAL1660Constant.PROCESS_LVL_LINE);    //QC#22965 add
        ZYPEZDItemValueSetter.setValue(bizMsg.trxHdrNum_N, bizMsg.schdAgmtNum);
        ZYPEZDItemValueSetter.setValue(bizMsg.prcBaseDt_N, bizMsg.prcBaseDt);
        ZYPEZDItemValueSetter.setValue(bizMsg.prcCalcDt_N, bizMsg.slsDt);
        ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdCatgCd_N, bizMsg.dsOrdCatgCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdTpCd_N, bizMsg.dsOrdTpCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.lineBizTpCd_N, bizMsg.lineBizTpCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.cpoSrcTpCd_N, bizMsg.cpoSrcTpCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.custIssPoNum_N, bizMsg.custIssPoNum);

        ZYPEZDItemValueSetter.setValue(bizMsg.dsPmtMethCd_N, DS_PMT_METH.INVOICE);
        ZYPEZDItemValueSetter.setValue(bizMsg.spclHdlgTpCd_N, bizMsg.spclHdlgTpCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.frtCondCd_N, bizMsg.frtCondCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.shpgSvcLvlCd_N, bizMsg.shpgSvcLvlCd);

        // Line Information
        ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdPosnNum_N, String.valueOf(IDX_1));
        ZYPEZDItemValueSetter.setValue(bizMsg.trxLineNum_N, lineMsg.schdAgmtLineNum_A.getValue().toString());
        ZYPEZDItemValueSetter.setValue(bizMsg.trxLineSubNum_N, SCHD_SUB_LINE_NUM_001);
        ZYPEZDItemValueSetter.setValue(bizMsg.configCatgCd_N, CONFIG_CATG.OUTBOUND);
        ZYPEZDItemValueSetter.setValue(bizMsg.inEachQty_N, lineMsg.schdAllwQty_A);
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToFirstLineAddr_N, bizMsg.shipToFirstLineAddr);
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToScdLineAddr_N, bizMsg.shipToScdLineAddr);
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToCtyAddr_N, bizMsg.shipToCtyAddr);
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToStCd_N, bizMsg.shipToStCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToCntyNm_N, bizMsg.shipToCntyNm);
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToPostCd_N, bizMsg.shipToPostCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToCtryCd_N, bizMsg.shipToCtryCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.billToCustLocCd_N, bizMsg.billToCustLocCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.billToCustAcctCd_N, bizMsg.billToCustAcctCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.sellToCustCd_N, bizMsg.sellToCustCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.soldToCustLocCd_N, bizMsg.soldToCustLocCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustLocCd_N, bizMsg.shipToCustLocCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustAcctCd_N, bizMsg.shipToCustAcctCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.prcCatgCd_N, bizMsg.prcCatgCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.prcContrNum_N, bizMsg.prcContrNum);
        ZYPEZDItemValueSetter.setValue(bizMsg.coaBrCd_N, bizMsg.coaBrCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.coaExtnCd_N, bizMsg.coaExtnCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.slsRepOrSlsTeamTocCd_N, bizMsg.slsRepTocCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.mdseCd_N, lineMsg.mdseCd_A);

        // Price Information
        ZYPEZDItemValueSetter.setValue(bizMsg.ccyCd_N, lineMsg.ccyCd_A);
        ZYPEZDItemValueSetter.setValue(bizMsg.uomCd_N, lineMsg.pkgUomCd_A);
        ZYPEZDItemValueSetter.setValue(bizMsg.ordCustUomQty_N, lineMsg.schdAllwQty_A);
        ZYPEZDItemValueSetter.setValue(bizMsg.dsCpoLineCatgCd_N, bizMsg.dsOrdCatgCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.invQty_N, BigDecimal.ZERO);
        // ZYPEZDItemValueSetter.setValue(bizMsg.rtlWhCd_N, lineMsg.rtlWhCd_B);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxUnitNetPrcAmt_N, lineMsg.dealPrcListPrcAmt_A);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxUnitGrsPrcAmt_N, lineMsg.dealNetUnitPrcAmt_A);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxTotTaxAmt_N, lineMsg.schdAgmtLineDealTaxAmt_A);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxTotAmt_N, lineMsg.xxTotAmt_A);
        ZYPEZDItemValueSetter.setValue(bizMsg.dealPrcListPrcAmt_N, lineMsg.dealPrcListPrcAmt_A);
        ZYPEZDItemValueSetter.setValue(bizMsg.dsCpoLineNum_N, lineMsg.schdAgmtLineNum_A);
        ZYPEZDItemValueSetter.setValue(bizMsg.dsCpoLineSubNum_N, BigDecimal.ONE);
        ZYPEZDItemValueSetter.setValue(bizMsg.dealSvcRevTrnsfAmt_N, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(bizMsg.dealSvcCostTrnsfAmt_N, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(bizMsg.dealManFlAdjAmt_N, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(bizMsg.dealManRepRevAdjAmt_N, BigDecimal.ZERO);

        // Calc Base Information
        NWAL1840CommonLogicForPriceChanges.setCalcBaseParam(bizMsg, lineMsg);
    }

    /**
     * Clear Price Change Parameters
     * @param bizMsg bizMsg
     */
    private static void clearPrcChangeParam(NWAL1840CMsg bizMsg) {

        bizMsg.xxModeCd_N.clear();
        bizMsg.xxModeCd_PM.clear();     //QC#22965 add
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
        bizMsg.prcContrNum_N.clear();
        bizMsg.coaBrCd_N.clear();
        bizMsg.mdseCd_N.clear();
        bizMsg.billToCustLocCd_N.clear();
        bizMsg.billToCustAcctCd_N.clear();
        bizMsg.sellToCustCd_N.clear();
        bizMsg.soldToCustLocCd_N.clear();
        bizMsg.shipToCustLocCd_N.clear();
        bizMsg.shipToCustAcctCd_N.clear();
        bizMsg.frtCondCd_N.clear();
        bizMsg.shpgSvcLvlCd_N.clear();
        bizMsg.ccyCd_N.clear();
        bizMsg.uomCd_N.clear();
        bizMsg.ordCustUomQty_N.clear();
        bizMsg.dsCpoLineCatgCd_N.clear();
        bizMsg.invQty_N.clear();
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
        ZYPTableUtil.clear(bizMsg.N);
    }

    /**
     * Set Calc Base Parameters
     * @param bizMsg NWAL1840CMsg
     * @param lineMsg NWAL1840CMsg
     */
    private static void setCalcBaseParam(NWAL1840CMsg bizMsg, NWAL1840_ACMsg lineMsg) {

        List<NWAL1840_ECMsg> calcBaseList = getCalcBaseList(bizMsg, lineMsg);
        int paramCount = 0;

        for (NWAL1840_ECMsg priceCondition : calcBaseList) {
            EZDMsg.copy(priceCondition, "E", bizMsg.N.no(paramCount), "NL");
            paramCount++;
        }
        bizMsg.N.setValidCount(calcBaseList.size());

        return;
    }

    /**
     * Get Calc Base List
     * @param bizMsg NWAL1840CMsg
     * @param lineMsg NWAL1840_ACMsg
     */
    private static List<NWAL1840_ECMsg> getCalcBaseList(NWAL1840CMsg bizMsg, NWAL1840_ACMsg lineMsg) {

        List<NWAL1840_ECMsg> calcBaseList = new ArrayList<NWAL1840_ECMsg>(bizMsg.E.getValidCount());
        for (int i = 0; i < bizMsg.E.getValidCount(); i++) {
            NWAL1840_ECMsg calcBaseMsg = bizMsg.E.no(i);
            if (lineMsg.schdAgmtLineNum_A.getValue().compareTo(calcBaseMsg.schdAgmtLineNum_E.getValue()) == 0) {
                calcBaseList.add(calcBaseMsg);
            }
        }
        return calcBaseList;
    }

    /**
     * Set ScreenItems From Price Changes
     * @param bizMsg NWAL1840CMsg
     * @param lineMsg NWAL1840_ACMsg
     */
    public static void setScreenItemsFromPrcChange(NWAL1840CMsg bizMsg, NWAL1840_ACMsg lineMsg) {

        //ZYPEZDItemValueSetter.setValue(lineMsg.dealPrcListPrcAmt_A, bizMsg.xxUnitGrsPrcAmt_N);
        ZYPEZDItemValueSetter.setValue(lineMsg.dealNetUnitPrcAmt_A, bizMsg.xxUnitGrsPrcAmt_N);
        // 2017/06/30 QC#18332 Mod Start
//        ZYPEZDItemValueSetter.setValue(lineMsg.xxTotDiscAmt_A, bizMsg.xxTotDiscAmt_N);
        ZYPEZDItemValueSetter.setValue(lineMsg.xxTotDiscAmt_A, bizMsg.xxTotDiscAmt_N.getValue().divide(lineMsg.schdAllwQty_A.getValue(), BigDecimal.ROUND_HALF_UP));
        // 2017/06/30 QC#18332 Mod End
        ZYPEZDItemValueSetter.setValue(lineMsg.schdAgmtLineDealNetAmt_A, bizMsg.xxTotNetPrcAmt_N);
        ZYPEZDItemValueSetter.setValue(lineMsg.schdAgmtLineDealFrtAmt_A, bizMsg.xxTotChrgPrcAmt_N);
        ZYPEZDItemValueSetter.setValue(lineMsg.schdAgmtLineDealTaxAmt_A, bizMsg.xxTotTaxAmt_N);
        ZYPEZDItemValueSetter.setValue(lineMsg.xxTotAmt_A, bizMsg.xxTotAmt_N);

        // Set Calc Base
        setCalcBaseFromPrcChange(bizMsg, lineMsg);
    }

    /**
     * Set Calc Base From Price Changes
     * @param bizMsg NWAL1840CMsg
     * @param lineMsg NWAL1840_ACMsg
     */
    private static void setCalcBaseFromPrcChange(NWAL1840CMsg bizMsg, NWAL1840_ACMsg lineMsg) {

        // Delete
        delCalcBase(bizMsg, lineMsg);

        // Add
        addCalcBase(bizMsg, lineMsg);
    }

    /**
     * Delete Calc Base
     * @param bizMsg NWAL1840CMsg
     * @param lineMsg NWAL1840_ACMsg
     */
    private static void delCalcBase(NWAL1840CMsg bizMsg, NWAL1840_ACMsg lineMsg) {

        List<Integer> deleteIndexList = new ArrayList<Integer>(bizMsg.E.getValidCount());

        for (int calcBaseIndex = 0; calcBaseIndex < bizMsg.E.getValidCount(); calcBaseIndex++) {
            NWAL1840_ECMsg calcBaseMsg = bizMsg.E.no(calcBaseIndex);
            if (lineMsg.schdAgmtLineNum_A.getValue().compareTo(calcBaseMsg.schdAgmtLineNum_E.getValue()) == 0) {
                deleteIndexList.add(calcBaseIndex);
            }
        }

        ZYPTableUtil.deleteRows(bizMsg.E, deleteIndexList);
    }

    /**
     * Add Calc Base
     * @param bizMsg NWAL1840CMsg
     * @param lineMsg NWAL1840_ACMsg
     */
    private static void addCalcBase(NWAL1840CMsg bizMsg, NWAL1840_ACMsg lineMsg) {

        for (int sourceIndex = 0; sourceIndex < bizMsg.N.getValidCount(); sourceIndex++) {
            NWAL1840_NCMsg sourceCalcBaseMsg = bizMsg.N.no(sourceIndex);
            int addIndex = bizMsg.E.getValidCount();
            bizMsg.E.setValidCount(addIndex + 1);
            NWAL1840_ECMsg calcBaseMsg = bizMsg.E.no(addIndex);
            EZDMsg.copy(sourceCalcBaseMsg, "NL", calcBaseMsg, "E");
            ZYPEZDItemValueSetter.setValue(calcBaseMsg.xxLineNum_E, lineMsg.xxLineNum_A);
            ZYPEZDItemValueSetter.setValue(calcBaseMsg.schdAgmtLineNum_E, lineMsg.schdAgmtLineNum_A);
        }
    }

    /**
     * Check Price Range.
     * @param lineMsg NWAL1840_ACMsg
     * @param calcBaseMsg NWAL1840_ECMsg
     * @return boolean
     */
    public static boolean checkPriceRange(NWAL1840_ACMsg lineMsg, NWAL1840_ECMsg calcBaseMsg) {

        if (!checkAmtRange(lineMsg, calcBaseMsg)) {
            return false;
        }
        return true;
    }

     /**
     * Check Amount Range
     * @param lineMsg NWAL1840_ACMsg
     * @param calcBaseMsg NWAL1840_ECMsg
     * @return Check OK : true
     */
    private static boolean checkAmtRange(NWAL1840_ACMsg lineMsg, NWAL1840_ECMsg calcBaseMsg) {

        if (!ZYPCommonFunc.hasValue(lineMsg.dealPrcListPrcAmt_A)) {
            return true;
        }

        if (ZYPCommonFunc.hasValue(calcBaseMsg.minPrcAmtRate_E)) {
            if (lineMsg.dealPrcListPrcAmt_A.getValue().compareTo(calcBaseMsg.minPrcAmtRate_E.getValue()) < 0) {
                lineMsg.dealNetUnitPrcAmt_A.setErrorInfo(1, NWAM0775E, new String[] {MSG_PARAM_MIN_PRC, calcBaseMsg.minPrcAmtRate_E.getValue().toString() });
                return false;
            }
        }

        if (ZYPCommonFunc.hasValue(calcBaseMsg.maxPrcAmtRate_E)) {
            if (lineMsg.dealPrcListPrcAmt_A.getValue().compareTo(calcBaseMsg.maxPrcAmtRate_E.getValue()) > 0) {
                lineMsg.dealNetUnitPrcAmt_A.setErrorInfo(1, NWAM0775E, new String[] {MSG_PARAM_MAX_PRC, calcBaseMsg.maxPrcAmtRate_E.getValue().toString() });
                return false;
            }
        }

        return true;
    }

    // QC#22965 add Start
    /**
     * setPrcChangeParamForHeader.
     * @param bizMsg bizMsg
     * @param lineMsg NWAL1840_ACMsg
     */
    public static void setPrcChangeParamForHeader(NWAL1840CMsg bizMsg) {

        // Clear Price Change Popup Parameters
        clearPrcChangeParam(bizMsg);

        // Header Information
        ZYPEZDItemValueSetter.setValue(bizMsg.xxModeCd_N, NWAL1660Constant.MODE_REGIST);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxModeCd_PM, NWAL1660Constant.PROCESS_LVL_HEADER);
        ZYPEZDItemValueSetter.setValue(bizMsg.trxHdrNum_N, bizMsg.schdAgmtNum);
        ZYPEZDItemValueSetter.setValue(bizMsg.prcBaseDt_N, bizMsg.prcBaseDt);
        ZYPEZDItemValueSetter.setValue(bizMsg.prcCalcDt_N, bizMsg.slsDt);
        ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdCatgCd_N, bizMsg.dsOrdCatgCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdTpCd_N, bizMsg.dsOrdTpCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.lineBizTpCd_N, bizMsg.lineBizTpCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.cpoSrcTpCd_N, bizMsg.cpoSrcTpCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.custIssPoNum_N, bizMsg.custIssPoNum);
        ZYPEZDItemValueSetter.setValue(bizMsg.dsPmtMethCd_N, DS_PMT_METH.INVOICE);
        ZYPEZDItemValueSetter.setValue(bizMsg.spclHdlgTpCd_N, bizMsg.spclHdlgTpCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.frtCondCd_N, bizMsg.frtCondCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.shpgSvcLvlCd_N, bizMsg.shpgSvcLvlCd);

        // Line Information
        BigDecimal totQty = BigDecimal.ZERO;
        BigDecimal xxTotUnitNetWt = BigDecimal.ZERO;
        BigDecimal xxSubTotCalcPrcAmt = BigDecimal.ZERO;
        BigDecimal xxTotChrgPrcAmt = BigDecimal.ZERO;
        BigDecimal xxTotTaxAmt = BigDecimal.ZERO;
        BigDecimal xxTotAmt = BigDecimal.ZERO;
        for(int i = 0; i < bizMsg.A.getValidCount(); i++) {
            NWAL1840_ACMsg line =  bizMsg.A.no(i);
            totQty = totQty.add(line.schdAllwQty_A.getValue());
            xxSubTotCalcPrcAmt = xxSubTotCalcPrcAmt.add(line.schdAgmtLineDealNetAmt_A.getValue());
            xxTotChrgPrcAmt = xxTotChrgPrcAmt.add(line.schdAgmtLineDealFrtAmt_A.getValue());
            xxTotTaxAmt = xxTotTaxAmt.add(line.schdAgmtLineDealTaxAmt_A.getValue());
            xxTotAmt = xxTotAmt.add(line.xxTotAmt_A.getValue());
            xxTotUnitNetWt = xxTotUnitNetWt.add(getTotWt(bizMsg, line.mdseCd_A.getValue(), line.schdAllwQty_A.getValue()));
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.configCatgCd_N, CONFIG_CATG.OUTBOUND);
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToFirstLineAddr_N, bizMsg.shipToFirstLineAddr);
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToScdLineAddr_N, bizMsg.shipToScdLineAddr);
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToCtyAddr_N, bizMsg.shipToCtyAddr);
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToStCd_N, bizMsg.shipToStCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToCntyNm_N, bizMsg.shipToCntyNm);
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToPostCd_N, bizMsg.shipToPostCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToCtryCd_N, bizMsg.shipToCtryCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.billToCustLocCd_N, bizMsg.billToCustLocCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.billToCustAcctCd_N, bizMsg.billToCustAcctCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.sellToCustCd_N, bizMsg.sellToCustCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.soldToCustLocCd_N, bizMsg.soldToCustLocCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustLocCd_N, bizMsg.shipToCustLocCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustAcctCd_N, bizMsg.shipToCustAcctCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.prcCatgCd_N, bizMsg.prcCatgCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.prcContrNum_N, bizMsg.prcContrNum);
        ZYPEZDItemValueSetter.setValue(bizMsg.coaBrCd_N, bizMsg.coaBrCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.coaExtnCd_N, bizMsg.coaExtnCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.slsRepOrSlsTeamTocCd_N, bizMsg.slsRepTocCd);
        PRC_CATGTMsg priceCategory = NWAL1840CommonLogic.getPriceCategory(bizMsg.glblCmpyCd.getValue(), bizMsg.prcCatgCd.getValue());
        if (priceCategory != null) {
            ZYPEZDItemValueSetter.setValue(bizMsg.ccyCd_N, priceCategory.ccyCd);
        }

        // summary information
        ZYPEZDItemValueSetter.setValue(bizMsg.xxSubTotCalcPrcAmt_N, xxSubTotCalcPrcAmt);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxTotChrgPrcAmt_N, xxTotChrgPrcAmt);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxTotTaxAmt_N, xxTotTaxAmt);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxTotAmt_N, xxTotAmt);
        ZYPEZDItemValueSetter.setValue(bizMsg.inEachQty_N, totQty);
        ZYPEZDItemValueSetter.setValue(bizMsg.uomCd_N, PKG_UOM.EACH);
        ZYPEZDItemValueSetter.setValue(bizMsg.ordCustUomQty_N, totQty);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxTotDiscAmt_N, BigDecimal.ZERO);

        ZYPEZDItemValueSetter.setValue(bizMsg.invQty_N, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(bizMsg.dealSvcRevTrnsfAmt_N, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(bizMsg.dealSvcCostTrnsfAmt_N, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(bizMsg.dealManFlAdjAmt_N, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(bizMsg.dealManRepRevAdjAmt_N, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxTotUnitNetWt_N, xxTotUnitNetWt);

        // Calc Base Information
        NWAL1840CommonLogicForPriceChanges.setCalcBaseParamForHeader(bizMsg.N, bizMsg.E);
    }

    private static void setCalcBaseParamForHeader(NWAL1840_NCMsgArray paramPriceConditionArray, NWAL1840_ECMsgArray priceConditionArray) {

        int paramCount = 0;
        paramPriceConditionArray.clear();
        NWAL1840_ECMsg row = null;
        Map<BigDecimal, NWAL1840_ECMsg> map = new HashMap<BigDecimal, NWAL1840_ECMsg>();
        Map<BigDecimal, NWAL1840_ECMsg> roundMap = new HashMap<BigDecimal, NWAL1840_ECMsg>();
        for (int i = 0; i < priceConditionArray.getValidCount(); i++) {
            NWAL1840_ECMsg priceCondition = priceConditionArray.no(i);
            row = map.get(priceCondition.specCondPrcPk_E.getValue());
            if (PRC_DTL_GRP.ROUNDING_FACTOR_0.equals(priceCondition.prcDtlGrpCd_E.getValue())
                    || PRC_DTL_GRP.ROUNDING_FACTOR_1.equals(priceCondition.prcDtlGrpCd_E.getValue())
                    || PRC_DTL_GRP.ROUNDING_FACTOR_2.equals(priceCondition.prcDtlGrpCd_E.getValue())) { // QC#21841 2018/05/21 Add
                NWAL1840_ACMsg roundRow = new NWAL1840_ACMsg();
                EZDMsg.copy(row, null, roundRow, null);
                roundMap.put(priceCondition.specCondPrcPk_E.getValue(), priceCondition);
            } else {
                if (row == null) {
                    NWAL1840_ECMsg value = new NWAL1840_ECMsg();
                    EZDMsg.copy(priceCondition, null, value, null);
                    map.put(priceCondition.specCondPrcPk_E.getValue(), value);
                } else {
                    ZYPEZDItemValueSetter.setValue(row.calcPrcAmtRate_E, row.calcPrcAmtRate_E.getValue().add(priceCondition.calcPrcAmtRate_E.getValue()));
                }
            }
        }
        for (Map.Entry<BigDecimal, NWAL1840_ECMsg> entry : roundMap.entrySet()) {
            row = map.get(entry.getKey());
            if (row != null) {
                NWAL1840_ECMsg priceCondition = entry.getValue();
                ZYPEZDItemValueSetter.setValue(row.calcPrcAmtRate_E, row.calcPrcAmtRate_E.getValue().add(priceCondition.calcPrcAmtRate_E.getValue()));
            }
        }

        for (Map.Entry<BigDecimal, NWAL1840_ECMsg> entry : map.entrySet()) {
            EZDMsg.copy(entry.getValue(), "E", paramPriceConditionArray.no(paramCount), "NL");
            paramCount++;
        }
        paramPriceConditionArray.setValidCount(paramCount);

        return;
    }

    public static void setScreenItemsFromPrcChangeForHeader(NWAL1840CMsg bizMsg) {

        NWAL1840_ECMsg newData = null;
        BigDecimal trxLineNum = null;
        for (int idx = 0; idx < bizMsg.A.getValidCount(); idx++) {
            NWAL1840_ACMsg lineMsg = bizMsg.A.no(idx);
            if (ZYPConstant.FLG_ON_Y.equals(lineMsg.schdAgmtLineCancFlg_A.getValue())) {
                continue;
            }
            String[] xxLineNumArray = lineMsg.xxLineNum_A.getValue().split("\\.");
            if (xxLineNumArray.length == IDX_3 && BigDecimal.ZERO.compareTo(new BigDecimal(xxLineNumArray[2])) != 0) {
                continue;
            }
            trxLineNum = lineMsg.schdAgmtLineNum_A.getValue();
        }
        if (trxLineNum == null) {
            return;
        }
 
        for (int n = 0; n < bizMsg.N.getValidCount(); n++) {
            NWAL1840_NCMsg sourcePriceCondition = bizMsg.N.no(n);
            for (int i = 0; i < bizMsg.E.getValidCount(); i++) {
                NWAL1840_ECMsg priceCondition = bizMsg.E.no(i);
                if (sourcePriceCondition.specCondPrcPk_NL.getValue().equals(priceCondition.specCondPrcPk_E.getValue())) {
                    ZYPEZDItemValueSetter.setValue(priceCondition.prcCondManEntryFlg_E, sourcePriceCondition.prcCondManEntryFlg_NL);
                    ZYPEZDItemValueSetter.setValue(priceCondition.prcCondManAddFlg_E, sourcePriceCondition.prcCondManAddFlg_NL);
                    ZYPEZDItemValueSetter.setValue(priceCondition.prcCondManDelFlg_E, sourcePriceCondition.prcCondManDelFlg_NL);
                    ZYPEZDItemValueSetter.setValue(priceCondition.prcCondUnitCd_E, sourcePriceCondition.prcCondUnitCd_NL);
                    ZYPEZDItemValueSetter.setValue(priceCondition.calcPrcAmtRate_E, BigDecimal.ZERO);
                    ZYPEZDItemValueSetter.setValue(priceCondition.unitPrcAmt_E, BigDecimal.ZERO);
                    if(PRC_COND_UNIT.PCT.equals(sourcePriceCondition.prcCondUnitCd_NL.getValue())){
                        ZYPEZDItemValueSetter.setValue(priceCondition.manPrcAmtRate_E, sourcePriceCondition.manPrcAmtRate_NL);
                    }
                }
            }
            NWAL1840_ECMsg row = bizMsg.E.no(bizMsg.E.getValidCount());
            ZYPEZDItemValueSetter.setValue(row.schdAgmtLineNum_E, trxLineNum);
            ZYPEZDItemValueSetter.setValue(row.prcCondTpCd_E, sourcePriceCondition.prcCondTpCd_NL);
            ZYPEZDItemValueSetter.setValue(row.prcCondTpDescTxt_E, sourcePriceCondition.prcCondTpDescTxt_NL);
            if (PRC_DTL_GRP.DISCOUNT.equals(sourcePriceCondition.prcDtlGrpCd_NL.getValue())) {
                ZYPEZDItemValueSetter.setValue(row.prcDtlGrpCd_E, PRC_DTL_GRP.ROUNDING_FACTOR_0);
            } else if (PRC_DTL_GRP.FREIGHT.equals(sourcePriceCondition.prcDtlGrpCd_NL.getValue())) {
                ZYPEZDItemValueSetter.setValue(row.prcDtlGrpCd_E, PRC_DTL_GRP.ROUNDING_FACTOR_1);
            // QC#21841 2018/05/21 Add Start
            } else if (PRC_DTL_GRP.HANDLING_FEE.equals(sourcePriceCondition.prcDtlGrpCd_NL.getValue())) {
                ZYPEZDItemValueSetter.setValue(row.prcDtlGrpCd_E, PRC_DTL_GRP.ROUNDING_FACTOR_2);
            } else if (PRC_DTL_GRP.FUEL_SURCHARGE.equals(sourcePriceCondition.prcDtlGrpCd_NL.getValue())) {
                ZYPEZDItemValueSetter.setValue(row.prcDtlGrpCd_E, PRC_DTL_GRP.ROUNDING_FACTOR_2);
            } else if (PRC_DTL_GRP.SHIPPING_FEE.equals(sourcePriceCondition.prcDtlGrpCd_NL.getValue())) {
                ZYPEZDItemValueSetter.setValue(row.prcDtlGrpCd_E, PRC_DTL_GRP.ROUNDING_FACTOR_2);
            // QC#21841 2018/05/21 Add End
            } else if (PRC_DTL_GRP.RESTOCKING_FEE.equals(sourcePriceCondition.prcDtlGrpCd_NL.getValue())) { // QC#27479 2018/08/03 Add
                ZYPEZDItemValueSetter.setValue(row.prcDtlGrpCd_E, PRC_DTL_GRP.ROUNDING_FACTOR_2);
            }
            ZYPEZDItemValueSetter.setValue(row.prcCondManEntryFlg_E, sourcePriceCondition.prcCondManEntryFlg_NL);
            ZYPEZDItemValueSetter.setValue(row.prcCondManAddFlg_E, sourcePriceCondition.prcCondManAddFlg_NL);
            ZYPEZDItemValueSetter.setValue(row.prcCondManDelFlg_E, sourcePriceCondition.prcCondManDelFlg_NL);
            ZYPEZDItemValueSetter.setValue(row.pkgUomCd_E, sourcePriceCondition.uomCd_NL.getValue());
            ZYPEZDItemValueSetter.setValue(row.prcCondUnitCd_E, sourcePriceCondition.prcCondUnitCd_NL);
            ZYPEZDItemValueSetter.setValue(row.autoPrcCcyCd_E, sourcePriceCondition.autoPrcCcyCd_NL.getValue());
            ZYPEZDItemValueSetter.setValue(row.manPrcAmtRate_E, sourcePriceCondition.manPrcAmtRate_NL);
            ZYPEZDItemValueSetter.setValue(row.unitPrcAmt_E, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(row.calcPrcAmtRate_E, sourcePriceCondition.calcPrcAmtRate_NL.getValue());
            ZYPEZDItemValueSetter.setValue(row.specCondPrcPk_E, sourcePriceCondition.specCondPrcPk_NL);
            // 2018/09/14 QC#9700 add start
            ZYPEZDItemValueSetter.setValue(row.prcRuleApplyFlg_E, sourcePriceCondition.prcRuleApplyFlg_NL);
            ZYPEZDItemValueSetter.setValue(row.prcRulePrcdPk_E, sourcePriceCondition.prcRulePrcdPk_NL);
            // 2018/09/14 QC#9700 add end
            bizMsg.E.setValidCount(bizMsg.E.getValidCount() + 1);
            newData = null;
        }
        if (bizMsg.N.getValidCount() > 0) {
            if (!callPricingApiGetOrderManualMode(bizMsg, ZYPConstant.FLG_OFF_N)) {
                return;
            }
        }
    }

    public static boolean callPricingApiGetOrderManualMode(NWAL1840CMsg bizMsg, String lineClose) {

        NWZC157001PMsg prcApiPMsg = new NWZC157001PMsg();
        NWAL1840CommonLogicForPricing.setHdrParam(bizMsg, prcApiPMsg, NWZC157001.MANUAL_PRICE);
        //Set Same parameters of 04:GET_ALL_MODE
        NWAL1840CommonLogicForPricing.setLineParamForGetOrderAllMode(bizMsg, prcApiPMsg);

        printPriceElem(bizMsg, "******** Before Call Pricing API ********");
        printPricingDetail(prcApiPMsg, "******** Before Call Pricing API ********");
        new NWZC157001().execute(prcApiPMsg, ONBATCH_TYPE.ONLINE);
        if (S21ApiUtil.isXxMsgId(prcApiPMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(prcApiPMsg);
            for (int i = 0; i < msgList.size(); i++) {
                S21ApiMessage msg = msgList.get(i);
                String msgId = msg.getXxMsgid();
                String[] msgPrms = msg.getXxMsgPrmArray();
                bizMsg.setMessageInfo(msgId, msgPrms);
                if (msgId.endsWith("E")) {
                    return false;
                }
            }
        }

        for (int i = 0; i < prcApiPMsg.NWZC157002PMsg.getValidCount(); i++) {
            NWZC157002PMsg priceLine = prcApiPMsg.NWZC157002PMsg.no(i);
            if (S21ApiUtil.isXxMsgId(priceLine)) {
                List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(prcApiPMsg.NWZC157002PMsg.no(i));
                for (int j = 0; j < msgList.size(); j++) {
                    S21ApiMessage msg = msgList.get(j);
                    String msgId = msg.getXxMsgid();
                    String[] msgPrms = msg.getXxMsgPrmArray();
                    bizMsg.setMessageInfo(msgId, msgPrms);
                    if (msgId.endsWith("E")) {
                        return false;
                    }
                }
            }
        }

        printPricingDetail(prcApiPMsg, "******** After Call Pricing API ********");
        printPricingResult(prcApiPMsg);

        // Set up Price Elements to CMsg
        bizMsg.E.clear();
        bizMsg.E.setValidCount(0);

        Map<String, NWAL1840_ACMsg> lineMsgMap = new HashMap<String, NWAL1840_ACMsg>();
        setHdrTotalAmt(bizMsg, prcApiPMsg, lineMsgMap);

        // Line Config
        setLineConfigPricingData(bizMsg, prcApiPMsg, lineMsgMap);

        printPriceElem(bizMsg, "******** After Call Pricing API ********");
        return true;
    }

    private static void setHdrTotalAmt(NWAL1840CMsg bizMsg, NWZC157001PMsg prcApiPMsg, Map<String, NWAL1840_ACMsg> lineMsgMap) {

        BigDecimal hdrLinesAmt = BigDecimal.ZERO;
        BigDecimal hdrLinesTax = BigDecimal.ZERO;
        BigDecimal hdrLinesCharge = BigDecimal.ZERO;
        BigDecimal hdrLinesSubTotal = BigDecimal.ZERO;

        StringBuilder lineMsgKey = new StringBuilder();

        for (int i = 0; i < prcApiPMsg.NWZC157004PMsg.getValidCount(); i++) {
            NWZC157004PMsg prcTotalPMsg = prcApiPMsg.NWZC157004PMsg.no(i);

            hdrLinesAmt = hdrLinesAmt.add(prcTotalPMsg.xxTotNetPrcAmt.getValue());
            hdrLinesTax = hdrLinesTax.add(prcTotalPMsg.xxTotTaxAmt.getValue());
            hdrLinesCharge = hdrLinesCharge.add(prcTotalPMsg.xxTotFrtAmt.getValue());
            hdrLinesSubTotal = hdrLinesSubTotal.add(prcTotalPMsg.xxTotAmt.getValue());

            lineMsgKey.setLength(0);
            lineMsgKey = lineMsgKey.append(prcTotalPMsg.trxLineNum.getValue());

            NWAL1840_ACMsg lineMsg = getLineMsg(bizMsg, prcTotalPMsg.trxLineNum.getValue());
            ZYPEZDItemValueSetter.setValue(lineMsg.dealPrcListPrcAmt_A, prcTotalPMsg.xxUnitGrsPrcAmt);
            ZYPEZDItemValueSetter.setValue(lineMsg.dealNetUnitPrcAmt_A, prcTotalPMsg.xxUnitGrsPrcAmt);
            ZYPEZDItemValueSetter.setValue(lineMsg.schdAgmtLineDealTaxAmt_A, prcTotalPMsg.xxTotTaxAmt);
            ZYPEZDItemValueSetter.setValue(lineMsg.schdAgmtLineDealNetAmt_A, prcTotalPMsg.xxTotAmt);
            lineMsgMap.put(lineMsgKey.toString(), lineMsg);
        }

        // set Header Amount: Total
        bizMsg.xxSubTotCalcPrcAmt.setValue(hdrLinesAmt);
        bizMsg.xxTotTaxAmt.setValue(hdrLinesTax);
        bizMsg.xxTotChrgPrcAmt.setValue(hdrLinesCharge);
        bizMsg.xxTotAmt.setValue(hdrLinesSubTotal);

    }

    private static NWAL1840_ACMsg getLineMsg(NWAL1840CMsg bizMsg, String trxLineNum) {
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            if (trxLineNum.equals(bizMsg.A.no(i).schdAgmtLineNum_A.getValue().toString())) {
                return bizMsg.A.no(i);
            }
        }
        return null;
    }


    private static void setLineConfigPricingData(NWAL1840CMsg bizMsg, NWZC157001PMsg prcApiPMsg, Map<String, NWAL1840_ACMsg> lineMsgMap) {

        int prcElmCnt = bizMsg.E.getValidCount();
        int i = 0;

        for (i = 0; i < prcApiPMsg.NWZC157002PMsg.getValidCount(); i++) {
            NWZC157002PMsg prcLinePMsg = prcApiPMsg.NWZC157002PMsg.no(i);

            StringBuilder lineMsgKey = new StringBuilder();
            lineMsgKey = lineMsgKey.append(prcLinePMsg.trxLineNum.getValue());
            NWAL1840_ACMsg lineMsg = lineMsgMap.get(lineMsgKey.toString());

            for (int j = 0; j < prcLinePMsg.NWZC157003PMsg.getValidCount(); j++) {
                NWZC157003PMsg prcElementPMsg = prcLinePMsg.NWZC157003PMsg.no(j);
                NWAL1840_ECMsg prcLineCMsg = bizMsg.E.no(prcElmCnt);

                ZYPEZDItemValueSetter.setValue(prcLineCMsg.schdAgmtPrcCalcBasePk_E, prcElementPMsg.ordPrcCalcBasePk);
                ZYPEZDItemValueSetter.setValue(prcLineCMsg.schdAgmtLineNum_E, BigDecimal.valueOf(Integer.parseInt(prcElementPMsg.trxLineNum.getValue())));
                ZYPEZDItemValueSetter.setValue(prcLineCMsg.prcCondTpCd_E, prcElementPMsg.prcCondTpCd);
                ZYPEZDItemValueSetter.setValue(prcLineCMsg.prcDtlGrpCd_E, prcElementPMsg.prcDtlGrpCd);
                ZYPEZDItemValueSetter.setValue(prcLineCMsg.prcJrnlGrpCd_E, prcElementPMsg.prcJrnlGrpCd);
                ZYPEZDItemValueSetter.setValue(prcLineCMsg.pkgUomCd_E, prcElementPMsg.pkgUomCd);
                ZYPEZDItemValueSetter.setValue(prcLineCMsg.prcCondUnitCd_E, prcElementPMsg.prcCondUnitCd);
                ZYPEZDItemValueSetter.setValue(prcLineCMsg.prcCalcMethCd_E, prcElementPMsg.prcCalcMethCd);
                ZYPEZDItemValueSetter.setValue(prcLineCMsg.dsMdsePrcPk_E, prcElementPMsg.dsMdsePrcPk);
                ZYPEZDItemValueSetter.setValue(prcLineCMsg.specCondPrcPk_E, prcElementPMsg.specCondPrcPk);
                ZYPEZDItemValueSetter.setValue(prcLineCMsg.frtPerWtPk_E, prcElementPMsg.frtPerWtPk);
                ZYPEZDItemValueSetter.setValue(prcLineCMsg.prcCondManEntryFlg_E, prcElementPMsg.prcCondManEntryFlg);
                ZYPEZDItemValueSetter.setValue(prcLineCMsg.prcCondManAddFlg_E, prcElementPMsg.prcCondManAddFlg);
                ZYPEZDItemValueSetter.setValue(prcLineCMsg.prcCondManDelFlg_E, prcElementPMsg.prcCondManDelFlg);
                ZYPEZDItemValueSetter.setValue(prcLineCMsg.autoPrcAmtRate_E, prcElementPMsg.autoPrcAmtRate);
                ZYPEZDItemValueSetter.setValue(prcLineCMsg.maxPrcAmtRate_E, prcElementPMsg.maxPrcAmtRate);
                ZYPEZDItemValueSetter.setValue(prcLineCMsg.minPrcAmtRate_E, prcElementPMsg.minPrcAmtRate);
                ZYPEZDItemValueSetter.setValue(prcLineCMsg.manPrcAmtRate_E, prcElementPMsg.manPrcAmtRate);
                ZYPEZDItemValueSetter.setValue(prcLineCMsg.calcPrcAmtRate_E, prcElementPMsg.calcPrcAmtRate);
                ZYPEZDItemValueSetter.setValue(prcLineCMsg.unitPrcAmt_E, prcElementPMsg.unitPrcAmt);
                ZYPEZDItemValueSetter.setValue(prcLineCMsg.autoPrcCcyCd_E, prcElementPMsg.autoPrcCcyCd);
                // 2018/09/14 QC9700 add start
                ZYPEZDItemValueSetter.setValue(prcLineCMsg.prcRuleApplyFlg_E, prcElementPMsg.prcRuleApplyFlg);
                ZYPEZDItemValueSetter.setValue(prcLineCMsg.prcRulePrcdPk_E, prcElementPMsg.prcRulePrcdPk);
                // 2018/09/14 QC9700 add end

                if (PRC_DTL_GRP.BASE_PRICE.equals(prcElementPMsg.prcDtlGrpCd.getValue())) {
                    if (null != lineMsg) {
                        ZYPEZDItemValueSetter.setValue(lineMsg.dealPrcListPrcAmt_A, prcElementPMsg.autoPrcAmtRate);
                        ZYPEZDItemValueSetter.setValue(bizMsg.prcCatgCd, prcLinePMsg.prcCatgCd);
                        ZYPEZDItemValueSetter.setValue(bizMsg.prcCatgNm, NWAL1840CommonLogic.getPrcCatgNm(bizMsg, prcLinePMsg.prcCatgCd.getValue()));
                    }
                }
                prcElmCnt++;
            }
            if (null != lineMsg) {
                ZYPEZDItemValueSetter.setValue(lineMsg.xxErrFlg_A, prcLinePMsg.xxErrFlg);
            }
        }
        bizMsg.E.setValidCount(prcElmCnt);

        // Calculate SubTaotal, Charge, Tax and Line Total in Line
        for (i = 0; i < prcApiPMsg.NWZC157004PMsg.getValidCount(); i++) {
            NWZC157004PMsg prcLinePMsg = prcApiPMsg.NWZC157004PMsg.no(i);

            StringBuilder lineMsgKey = new StringBuilder();
            lineMsgKey = lineMsgKey.append(prcLinePMsg.trxLineNum.getValue());
            NWAL1840_ACMsg lineMsg = lineMsgMap.get(lineMsgKey.toString());

            if (null != lineMsg) {
                ZYPEZDItemValueSetter.setValue(lineMsg.dealNetUnitPrcAmt_A, prcLinePMsg.xxUnitGrsPrcAmt);
                ZYPEZDItemValueSetter.setValue(lineMsg.dealPrcListPrcAmt_A, prcLinePMsg.xxUnitGrsPrcAmt);
                ZYPEZDItemValueSetter.setValue(lineMsg.xxTotDiscAmt_A, prcLinePMsg.xxTotDiscAmt.getValue().divide(lineMsg.schdAllwQty_A.getValue(), BigDecimal.ROUND_HALF_UP));
                ZYPEZDItemValueSetter.setValue(lineMsg.schdAgmtLineDealFrtAmt_A, prcLinePMsg.xxTotFrtAmt);
                ZYPEZDItemValueSetter.setValue(lineMsg.schdAgmtLineDealTaxAmt_A, prcLinePMsg.xxTotTaxAmt);
                // Set Sub Total
                BigDecimal uomQty = lineMsg.schdAllwQty_A.getValue();
                ZYPEZDItemValueSetter.setValue(lineMsg.schdAgmtLineDealNetAmt_A, uomQty.multiply(prcLinePMsg.xxUnitNetPrcAmt.getValue()));

                // Set Line Total
                ZYPEZDItemValueSetter.setValue(lineMsg.xxTotAmt_A, prcLinePMsg.xxTotAmt);

            }
        }

    }
    // QC#22965 add Start
    private static BigDecimal getTotWt(NWAL1840CMsg bizMsg, String mdseCd, BigDecimal inEachQty) {
        S21SsmEZDResult rslt = NWAL1840Query.getInstance().getInPondWt(bizMsg.glblCmpyCd.getValue(), mdseCd);
        if (!rslt.isCodeNormal()) {
            return BigDecimal.ZERO;
        }
        BigDecimal wt = (BigDecimal) rslt.getResultObject();
        if (!ZYPCommonFunc.hasValue(inEachQty)) {
            return BigDecimal.ZERO;
        }
        return inEachQty.multiply(wt).setScale(TOT_WT_SCALE, RoundingMode.HALF_UP);
    }
    // QC#22965 add End

    private static void printPriceElem(NWAL1840CMsg bizMsg, String opt) {
        if (!LOG_OUT) {
            return;
        }
        final StringBuilder sb = new StringBuilder();
        sb.append("####---------- Price Element NWAL1840_ECMsg Start ----------####\r\n");
        sb.append(opt + "\r\n");
        for (int i = 0; i < bizMsg.E.getValidCount(); i++) {
            NWAL1840_ECMsg prcElem = bizMsg.E.no(i);
            sb.append("No[" + String.valueOf(i) + "]\r\n");
//            sb.append("Config Category Code     :" + prcElem.configCatgCd_E.getValue() + "\r\n");
//            sb.append("Detail Line#             :" + prcElem.cpoDtlLineNum_E.getValue() + "\r\n");
//            sb.append("Detail Line Sub#         :" + prcElem.cpoDtlLineSubNum_E.getValue() + "\r\n");
            sb.append("Display Line #           :" + prcElem.xxLineNum_E.getValue() + "\r\n");
            sb.append("Price Detail Group Cd    :" + prcElem.prcDtlGrpCd_E.getValue() + "\r\n");
            sb.append("Auto Price Amount Rate   :" + String.valueOf(prcElem.autoPrcAmtRate_E.getValue()) + "\r\n");
            sb.append("Manual Price Amount Rate :" + String.valueOf(prcElem.manPrcAmtRate_E.getValue()) + "\r\n");
            sb.append("Order Price Calc Base PK :" + prcElem.schdAgmtPrcCalcBasePk_E.getValue() + "\r\n");
            sb.append("Special Cond Price PK    :" + prcElem.specCondPrcPk_E.getValue() + "\r\n");
            sb.append("Calculate Price Amount Rete = " + String.valueOf(prcElem.calcPrcAmtRate_E.getValue()) + "\r\n");
        }
        sb.append(opt + "\r\n");
        sb.append("####---------- Price Element NWAL1840_ECMsg End ----------####\r\n");
        System.out.println(sb.toString());
    }

    private static void printPricingDetail(NWZC157001PMsg prcApiPMsg, String opt) {
        if (!LOG_OUT) {
            return;
        }
        final StringBuilder sb = new StringBuilder();
        sb.append("####---------- Pricing API Detail Parameter Result Start ----------####\r\n");
        sb.append(opt + "\r\n");
        for (int i = 0; i < prcApiPMsg.NWZC157002PMsg.getValidCount(); i++) {
            sb.append("****** Level 2 Start ******\r\n");
            NWZC157002PMsg prcDtlPMsg = prcApiPMsg.NWZC157002PMsg.no(i);

            sb.append("no[" + String.valueOf(i) + "]\r\n");
            sb.append("Config Category:   [" + prcDtlPMsg.configCatgCd.getValue() + "]\r\n");
            sb.append("Detail Line#   :   [" + prcDtlPMsg.trxLineNum.getValue() + "]\r\n");
            sb.append("Detail Sub Line# : [" + prcDtlPMsg.trxLineSubNum.getValue() + "]\r\n");
            sb.append("****** Level 2 End ******\r\n");
            for (int j = 0; j < prcDtlPMsg.NWZC157003PMsg.getValidCount(); j++) {
                sb.append("+-+-+- Level 3 [" + String.valueOf(j) + "] Start\r\n");
                NWZC157003PMsg prcElemPMsg = prcDtlPMsg.NWZC157003PMsg.no(j);
                sb.append("Config Category          = " + String.valueOf(prcElemPMsg.configCatgCd.getValue()) + "\r\n");
                sb.append("Price Detail Group       = " + String.valueOf(prcElemPMsg.prcDtlGrpCd.getValue()) + "\r\n");
                sb.append("Price Category Code      = " + String.valueOf(prcElemPMsg.prcCatgCd.getValue()) + "\r\n");
                sb.append("Auto Price Amount Rete   = " + String.valueOf(prcElemPMsg.autoPrcAmtRate.getValue()) + "\r\n");
                sb.append("Manual Price Amount Rete = " + String.valueOf(prcElemPMsg.manPrcAmtRate.getValue()) + "\r\n");
                sb.append("Calculate Price Amount Rete = " + String.valueOf(prcElemPMsg.calcPrcAmtRate.getValue()) + "\r\n");
                sb.append("+-+-+- Level 3 [" + String.valueOf(j) + "] End\r\n");
            }
        }
        sb.append(opt + "\r\n");
        sb.append("####---------- Pricing API Detail Parameter Result End ----------####\r\n");
        System.out.println(sb.toString());
    }

    private static void printPricingResult(NWZC157001PMsg prcApiPMsg) {
        if (!LOG_OUT) {
            return;
        }
        final StringBuilder sb = new StringBuilder();
        sb.append("####---------- Pricing API Result Start ----------####\r\n");
        for (int i = 0; i < prcApiPMsg.NWZC157004PMsg.getValidCount(); i++) {
            NWZC157004PMsg prcTotalPMsg = prcApiPMsg.NWZC157004PMsg.no(i);

            sb.append("no[" + String.valueOf(i) + "]\r\n");
            sb.append("Config Category:   [" + prcTotalPMsg.configCatgCd.getValue() + "]\r\n");
            sb.append("Detail Line#   :   [" + prcTotalPMsg.trxLineNum.getValue() + "]\r\n");
            sb.append("Detail Sub Line# : [" + prcTotalPMsg.trxLineSubNum.getValue() + "]\r\n");
            sb.append("Unit Gross Price Amount = " + String.valueOf(prcTotalPMsg.xxUnitGrsPrcAmt.getValue()) + "\r\n");
            sb.append("Unit Net Price Amount   = " + String.valueOf(prcTotalPMsg.xxUnitNetPrcAmt.getValue()) + "\r\n");
            sb.append("Total Tax Amount        = " + String.valueOf(prcTotalPMsg.xxTotTaxAmt.getValue()) + "\r\n");
            sb.append("Total Amount            = " + String.valueOf(prcTotalPMsg.xxTotAmt.getValue()) + "\r\n");
        }
        sb.append("####---------- Pricing API Result End   ----------####\r\n");
        System.out.println(sb.toString());
    }
    
    // QC#22965 add End
}
