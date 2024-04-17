/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1840.common;

import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.MSG_PARAM_ITEM;
import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.NWAM0181E;
import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.NWAM0813E;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import parts.dbcommon.EZDTBLAccessor;
import business.blap.NWAL1840.NWAL1840CMsg;
import business.blap.NWAL1840.NWAL1840QueryForItemLine;
import business.blap.NWAL1840.NWAL1840_ACMsg;
import business.blap.NWAL1840.NWAL1840_ECMsg;
import business.db.ALL_MDSE_VTMsg;
import business.db.ALL_MDSE_VTMsgArray;
import business.db.CUST_MDSE_XREFTMsg;
import business.db.CUST_MDSE_XREFTMsgArray;
import business.db.MDSETMsg;
import business.db.ORD_TAKE_MDSETMsg;
import business.db.ORD_TAKE_MDSETMsgArray;
import business.parts.NWZC157001PMsg;
import business.parts.NWZC157002PMsg;
import business.parts.NWZC157003PMsg;
import business.parts.NWZC157004PMsg;

import com.canon.cusa.s21.api.NWZ.NWZC157001.NWZC157001;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_DTL_GRP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/17   Fujitsu         T.Murai         Create          N/A
 * 2016/06/13   Fujitsu         Y.Kanefusa      Update          QC#6480
 * 2016/07/14   Fujitsu         H.Ikeda         Update          QC#11655
 * 2016/08/22   Fujitsu         H.Ikeda         Update          QC#11655
 * 2016/09/05   Fujitsu         T.Murai         Update          S21_NA#11752,11655
 * 2016/10/18   Fujitsu         H.Ikeda         Update          S21_NA#13038
 * 01/24/2017   Fujitsu         S.Yamamoto      Update          QC#17078
 * 2018/09/14   Fujitsu         M.Ohno          Update          QC#9700
 * </pre>
 */
public class NWAL1840CommonLogicForItemLine {

    /**
     * Check Merchandise Code
     * @param bizMsg NWAL1840CMsg
     * @return Checked Merchandise Code
     */
    public static String checkMdseCd(NWAL1840CMsg bizMsg) {

        int slctLine = bizMsg.xxCellIdx.getValueInt();
        NWAL1840_ACMsg itemLineMsg = bizMsg.A.no(slctLine);

        ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg_MN, ZYPConstant.FLG_OFF_N); // Add 2016/09/28 S21_NA#11655
        String inputMdseCdValue = itemLineMsg.mdseCd_A.getValue();

        ALL_MDSE_VTMsgArray allMdseViewArray = getAllMdseViewArray(bizMsg, inputMdseCdValue);
        String baseMdseCd = "";
        // 2016/09/07 S21_NA#11655 Del Start
//        if (allMdseViewArray.getValidCount() == 0) {
//            itemLineMsg.mdseCd_A.setErrorInfo(1, NWAM0181E, new String[] {MSG_PARAM_ITEM });
//            return null;
//        }
        // 2016/08/22 S21_NA#11655 Del End

        if (allMdseViewArray.getValidCount() == 1) {
            String mdseCd = allMdseViewArray.no(0).mdseCd.getValue();
            ZYPEZDItemValueSetter.setValue(itemLineMsg.mdseCd_A, mdseCd);
            return mdseCd;
        } else if (allMdseViewArray.getValidCount() > 1) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_ON_Y);
            return null;
        }
        CUST_MDSE_XREFTMsgArray custMdseXrefArray = getCustMdseXrefArray(bizMsg, inputMdseCdValue);
        if (custMdseXrefArray.getValidCount() == 1) {
            // Mod Start 2016/09/08 S21_NA#11655
            // baseMdseCd = custMdseXrefArray.no(0).mdseCd.getValue();
            baseMdseCd = getOrdTakeMdseCd(bizMsg, custMdseXrefArray.no(0).mdseCd.getValue());
            // Mod End 2016/09/08 S21_NA#11655
            ZYPEZDItemValueSetter.setValue(itemLineMsg.mdseCd_A, baseMdseCd);
            return baseMdseCd;
        } else if (custMdseXrefArray.getValidCount() > 1) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_ON_Y);
            return null;
        }
        S21SsmEZDResult ssmResult = NWAL1840QueryForItemLine.getInstance().getBaseMdseCdFromMnfItemCd(bizMsg, inputMdseCdValue);
        if (ssmResult.isCodeNotFound()) {
            itemLineMsg.mdseCd_A.setErrorInfo(1, NWAM0181E, new String[] {MSG_PARAM_ITEM });
            return null;
        } 
        // Mod Start 2016/09/28 S21_NA#11655
        List<Map<String, Object>> mnfItemList = (List<Map<String, Object>>) ssmResult.getResultObject();
        if (mnfItemList.size() > 1) {
            Set<String> mdseSet = new HashSet<String>();
            for (Map<String, Object> mnfItem : mnfItemList) {
                String ordTakeMdseCd = (String) mnfItem.get("ORD_TAKE_MDSE_CD");
                String mdseCd = (String) mnfItem.get("MDSE_CD");

                if (ZYPCommonFunc.hasValue(ordTakeMdseCd)) {
                    mdseSet.add(ordTakeMdseCd);
                } else {
                    mdseSet.add(mdseCd);
                }
            }
            if (mdseSet.size() > 1) {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg_MN, ZYPConstant.FLG_ON_Y);
                return null;
            } else {
                for (String mdse : mdseSet) {
                    baseMdseCd = mdse;
                    break;
                }
            }
            
        } else {
            Map<String, Object> itemMap = mnfItemList.get(0);
            String ordTakeMdseCd = (String) itemMap.get("ORD_TAKE_MDSE_CD");

            if (ZYPCommonFunc.hasValue(ordTakeMdseCd)) {
                baseMdseCd = ordTakeMdseCd;
            } else {
                baseMdseCd =  (String) itemMap.get("MDSE_CD");;
            }
        }
        // baseMdseCd = (String) ssmResult.getResultObject();
        // ZYPEZDItemValueSetter.setValue(itemLineMsg.mdseCd_A, baseMdseCd);
        // Mod End 2016/09/07S21_NA#11655
        return baseMdseCd;
    }

    /**
     * Get ALL_MDSE_V Array
     * @param bizMsg NWAL1840CMsg
     * @param inputMdseCd Input Merchandise Code
     * @return ALL_MDSE_V Array
     */
    private static ALL_MDSE_VTMsgArray getAllMdseViewArray(NWAL1840CMsg bizMsg, String inputMdseCd) {

        final ALL_MDSE_VTMsg condition = new ALL_MDSE_VTMsg();
        condition.setSQLID("002");
        condition.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        condition.setConditionValue("mdseCd01", inputMdseCd);
        condition.setConditionValue("rgtnStsCd01", RGTN_STS.READY_FOR_ORDER_TAKING);
        return (ALL_MDSE_VTMsgArray) EZDTBLAccessor.findByCondition(condition);
    }

    /**
     * Get CUST_MDSE_XREF Array
     * @param bizMsg NWAL1840CMsg
     * @param inputMdseCd Input Merchandise Code
     * @return CUST_MDSE_XREF Array
     */
    private static CUST_MDSE_XREFTMsgArray getCustMdseXrefArray(NWAL1840CMsg bizMsg, String inputMdseCd) {

        final CUST_MDSE_XREFTMsg condition = new CUST_MDSE_XREFTMsg();
        condition.setSQLID("001");
        condition.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        condition.setConditionValue("custMdseCd01", inputMdseCd);
        condition.setConditionValue("dsAcctNum01", bizMsg.sellToCustCd.getValue());
        condition.setConditionValue("custMdseXrefEnblFlg01", ZYPConstant.FLG_ON_Y);
        return (CUST_MDSE_XREFTMsgArray) EZDTBLAccessor.findByCondition(condition);
    }

    /**
     * Derive Item Line Information
     * @param bizMsg NWAL1840CMsg
     * @param inputMdseCd String
     * @param baseMdseTMsg MDSETMsg
     */
    public static void deriveItemLineInfo(NWAL1840CMsg bizMsg, String inputMdseCd, MDSETMsg baseMdseTMsg) {

        S21SsmEZDResult ssmResult = NWAL1840QueryForItemLine.getInstance().getPkgUomCd(bizMsg, baseMdseTMsg.mdseCd.getValue());
        String pkgUomCd = null;
        String pkgUomDescTxt = null;

        if (ssmResult.getQueryResultCount() > 0) {
            Map<String, Object> pkgUomMap = (Map<String, Object>) ssmResult.getResultObject();
            pkgUomCd = (String) pkgUomMap.get("PKG_UOM_CD");
            pkgUomDescTxt = (String) pkgUomMap.get("PKG_UOM_DESC_TXT");
        }

        NWAL1840_ACMsg itemLineMsg = bizMsg.A.no(bizMsg.xxCellIdx.getValueInt());
        ZYPEZDItemValueSetter.setValue(itemLineMsg.mdseCd_A, inputMdseCd);
        ZYPEZDItemValueSetter.setValue(itemLineMsg.mdseNm_A, baseMdseTMsg.mdseNm);
        ZYPEZDItemValueSetter.setValue(itemLineMsg.mdseDescShortTxt_A, NWAL1840CommonLogic.getMdseShortTxt(baseMdseTMsg.mdseCd.getValue(), baseMdseTMsg.glblCmpyCd.getValue()));
        ZYPEZDItemValueSetter.setValue(itemLineMsg.pkgUomCd_A, pkgUomCd);
        ZYPEZDItemValueSetter.setValue(itemLineMsg.pkgUomDescTxt_A, pkgUomDescTxt);
        ZYPEZDItemValueSetter.setValue(itemLineMsg.dealPrcListPrcAmt_A, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(itemLineMsg.dealNetUnitPrcAmt_A, BigDecimal.ZERO);
        // Del 2016/10/18 #13038 Start
        //ZYPEZDItemValueSetter.setValue(itemLineMsg.schdAllwQty_A, BigDecimal.ZERO);
        // Del 2016/10/18 #13038 End
        ZYPEZDItemValueSetter.setValue(itemLineMsg.ordQty_DE, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(itemLineMsg.ordQty_SC, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(itemLineMsg.xxTotDiscAmt_A, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(itemLineMsg.schdAgmtLineDealNetAmt_A, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(itemLineMsg.schdAgmtLineDealFrtAmt_A, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(itemLineMsg.schdAgmtLineDealTaxAmt_A, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(itemLineMsg.schdAgmtLineFuncNetAmt_A, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(itemLineMsg.schdAgmtLineFuncFrtAmt_A, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(itemLineMsg.schdAgmtLineFuncTaxAmt_A, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(itemLineMsg.xxTotAmt_A, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(itemLineMsg.supdLockFlg_A, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(itemLineMsg.funcNetUnitPrcAmt_A, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(itemLineMsg.funcPrcListPrcAmt_A, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(itemLineMsg.exchRate_A, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(itemLineMsg.manPrcFlg_A, ZYPConstant.FLG_OFF_N);
        itemLineMsg.sbstMdseCd_A.clear();

    }

    /**
     * Merchandise otherCheck
     * @param bizMsg NWAL1840CMsg
     * @return Each Quantity
     */
    public static boolean otherCheck(NWAL1840CMsg bizMsg) {

        if (ZYPCommonFunc.hasValue(bizMsg.dsContrNum)) {

            S21SsmEZDResult ssmResult = NWAL1840QueryForItemLine.getInstance().checkContractNum(bizMsg, bizMsg.A.no(bizMsg.xxCellIdx.getValueInt()).mdseCd_A.getValue());
            Integer countResult = (Integer) ssmResult.getResultObject();

            if (countResult == 0) {
                bizMsg.A.no(bizMsg.xxCellIdx.getValueInt()).mdseCd_A.setErrorInfo(1, NWAM0813E);
                return false;
            }
        }
        return true;
    }

    /**
     * Derive Line Prices
     * @param bizMsg NWAL1840CMsg
     */
    public static void deriveLinePrice(NWAL1840CMsg bizMsg, boolean delFlg) {

        NWAL1840_ACMsg itemLineMsg = bizMsg.A.no(bizMsg.xxCellIdx.getValueInt());
        if (!ZYPCommonFunc.hasValue(itemLineMsg.schdAllwQty_A)) {
            return;
        }

        if (delFlg) {
            delSlctLineCalcBase(bizMsg);
        }

        itemLineMsg.xxErrFlg_A.clear(); // Add 2016/09/05 #11752
        BigDecimal slctSchdLineNum = itemLineMsg.schdAgmtLineNum_A.getValue();

        // Call NWZC1570 Pricing API
        NWZC157001PMsg prcApiPMsg = NWAL1840CommonLogicForPricing.callPricingApiOfGetBasePriceMode(bizMsg);
        new NWZC157001().execute(prcApiPMsg, ONBATCH_TYPE.ONLINE);

        if (S21ApiUtil.isXxMsgId(prcApiPMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(prcApiPMsg);
            for (S21ApiMessage msg : msgList) {
                String msgId = msg.getXxMsgid();
                String[] msgPrms = msg.getXxMsgPrmArray();
                bizMsg.setMessageInfo(msgId, msgPrms);
                return;
            }
        }

        for (int i = 0; i < prcApiPMsg.NWZC157002PMsg.getValidCount(); i++) {
            if (S21ApiUtil.isXxMsgId(prcApiPMsg.NWZC157002PMsg.no(i))) {
                List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(prcApiPMsg.NWZC157002PMsg.no(i));
                for (S21ApiMessage msg : msgList) {
                    String msgId = msg.getXxMsgid();
                    String[] msgPrms = msg.getXxMsgPrmArray();
                    bizMsg.setMessageInfo(msgId, msgPrms);
                    // Add Start 2016/09/05 #11752
                    ZYPEZDItemValueSetter.setValue(itemLineMsg.xxErrFlg_A, prcApiPMsg.NWZC157002PMsg.no(i).xxErrFlg);
                    itemLineMsg.dealNetUnitPrcAmt_A.clear();
                    // Add End 2016/09/05 #11752
                    return;
                }
            }
        }

        delSlctLineCalcBase(bizMsg);
        // Set Calc Base
        NWZC157002PMsg prcLinePMsg = prcApiPMsg.NWZC157002PMsg.no(0);
        if (!S21ApiUtil.isXxMsgId(prcLinePMsg)) {
            int validCnt = bizMsg.E.getValidCount();
            BigDecimal baseAutoPrcAmtRate = null;
            boolean isExistBasePrc = false;

            for (int i = 0; i < prcLinePMsg.NWZC157003PMsg.getValidCount(); i++) {
                NWZC157003PMsg prcElementPMsg = prcLinePMsg.NWZC157003PMsg.no(i);
                NWAL1840_ECMsg calcBaseMsg = bizMsg.E.no(validCnt);
                ZYPEZDItemValueSetter.setValue(calcBaseMsg.schdAgmtLineNum_E, slctSchdLineNum);
                ZYPEZDItemValueSetter.setValue(calcBaseMsg.prcCondTpCd_E, prcElementPMsg.prcCondTpCd);
                ZYPEZDItemValueSetter.setValue(calcBaseMsg.prcCondTpDescTxt_E, prcElementPMsg.prcCondTpDescTxt);
                ZYPEZDItemValueSetter.setValue(calcBaseMsg.prcDtlGrpCd_E, prcElementPMsg.prcDtlGrpCd);
                ZYPEZDItemValueSetter.setValue(calcBaseMsg.prcJrnlGrpCd_E, prcElementPMsg.prcJrnlGrpCd);
                ZYPEZDItemValueSetter.setValue(calcBaseMsg.prcCondManEntryFlg_E, prcElementPMsg.prcCondManEntryFlg);
                ZYPEZDItemValueSetter.setValue(calcBaseMsg.prcCondManAddFlg_E, prcElementPMsg.prcCondManAddFlg);
                ZYPEZDItemValueSetter.setValue(calcBaseMsg.prcCondManDelFlg_E, prcElementPMsg.prcCondManDelFlg);
                ZYPEZDItemValueSetter.setValue(calcBaseMsg.pkgUomCd_E, prcElementPMsg.pkgUomCd);
                ZYPEZDItemValueSetter.setValue(calcBaseMsg.prcCondUnitCd_E, prcElementPMsg.prcCondUnitCd);
                ZYPEZDItemValueSetter.setValue(calcBaseMsg.prcCalcMethCd_E, prcElementPMsg.prcCalcMethCd);
                ZYPEZDItemValueSetter.setValue(calcBaseMsg.autoPrcCcyCd_E, prcElementPMsg.autoPrcCcyCd);
                ZYPEZDItemValueSetter.setValue(calcBaseMsg.autoPrcAmtRate_E, prcElementPMsg.autoPrcAmtRate);
                ZYPEZDItemValueSetter.setValue(calcBaseMsg.maxPrcAmtRate_E, prcElementPMsg.maxPrcAmtRate);
                ZYPEZDItemValueSetter.setValue(calcBaseMsg.minPrcAmtRate_E, prcElementPMsg.minPrcAmtRate);
                ZYPEZDItemValueSetter.setValue(calcBaseMsg.manPrcAmtRate_E, prcElementPMsg.manPrcAmtRate);
                ZYPEZDItemValueSetter.setValue(calcBaseMsg.calcPrcAmtRate_E, prcElementPMsg.calcPrcAmtRate);
                ZYPEZDItemValueSetter.setValue(calcBaseMsg.unitPrcAmt_E, prcElementPMsg.unitPrcAmt);
                ZYPEZDItemValueSetter.setValue(calcBaseMsg.dsMdsePrcPk_E, prcElementPMsg.dsMdsePrcPk);
                ZYPEZDItemValueSetter.setValue(calcBaseMsg.specCondPrcPk_E, prcElementPMsg.specCondPrcPk);
                ZYPEZDItemValueSetter.setValue(calcBaseMsg.frtPerWtPk_E, prcElementPMsg.frtPerWtPk);
                ZYPEZDItemValueSetter.setValue(calcBaseMsg.schdAgmtPrcCalcBasePk_E, prcElementPMsg.ordPrcCalcBasePk);
                // 2018/09/14 QC9700 add start
                ZYPEZDItemValueSetter.setValue(calcBaseMsg.prcRuleApplyFlg_E, prcElementPMsg.prcRuleApplyFlg);
                ZYPEZDItemValueSetter.setValue(calcBaseMsg.prcRulePrcdPk_E, prcElementPMsg.prcRulePrcdPk);
                // 2018/09/14 QC9700 add end

                if (PRC_DTL_GRP.BASE_PRICE.equals(prcElementPMsg.prcDtlGrpCd.getValue())) {
                    baseAutoPrcAmtRate = prcElementPMsg.autoPrcAmtRate.getValue();
                    isExistBasePrc = true;
                }
                validCnt++;
            }
            bizMsg.E.setValidCount(validCnt);

            // set Field Amount
            NWZC157004PMsg prcTotalPMsg = prcApiPMsg.NWZC157004PMsg.no(0);
            ZYPEZDItemValueSetter.setValue(itemLineMsg.dealNetUnitPrcAmt_A, prcTotalPMsg.xxUnitNetPrcAmt);
            if (isExistBasePrc) {
                ZYPEZDItemValueSetter.setValue(itemLineMsg.dealPrcListPrcAmt_A, baseAutoPrcAmtRate);
            }

            ZYPEZDItemValueSetter.setValue(itemLineMsg.xxTotDiscAmt_A, prcTotalPMsg.xxTotDiscAmt);
            ZYPEZDItemValueSetter.setValue(itemLineMsg.schdAgmtLineDealFrtAmt_A, prcTotalPMsg.xxTotFrtAmt);
            ZYPEZDItemValueSetter.setValue(itemLineMsg.schdAgmtLineDealTaxAmt_A, prcTotalPMsg.xxTotTaxAmt);
            ZYPEZDItemValueSetter.setValue(itemLineMsg.xxTotAmt_A, prcTotalPMsg.xxTotAmt);

            // Set Sub Total
            BigDecimal allwQty = itemLineMsg.schdAllwQty_A.getValue();
            ZYPEZDItemValueSetter.setValue(itemLineMsg.schdAgmtLineDealNetAmt_A, allwQty.multiply(itemLineMsg.dealNetUnitPrcAmt_A.getValue()));
        }
    }

    /**
     * Delete Select Line Calculation Base
     * @param bizMsg NWAL1840CMsg
     */
    private static void delSlctLineCalcBase(NWAL1840CMsg bizMsg) {

        BigDecimal slctSchdLineNum = bizMsg.A.no(bizMsg.xxCellIdx.getValueInt()).schdAgmtLineNum_A.getValue();

        List<Integer> deleteRows = new ArrayList<Integer>(bizMsg.E.getValidCount());
        for (int i = 0; i < bizMsg.E.getValidCount(); i++) {
            BigDecimal schdAgmtLineNum = bizMsg.E.no(i).schdAgmtLineNum_E.getValue();
            if (slctSchdLineNum.compareTo(schdAgmtLineNum) == 0) {
                deleteRows.add(i);
            }
        }

        ZYPTableUtil.deleteRows(bizMsg.E, deleteRows);
    }

    // Add Start 2016/09/08 S21_NA#11655
    /**
     * Get Order Take MDSE Code
     * @param bizMsg NWAL1840CMsg
     * @param mdseCd MDSE Code
     * @return Order Take MDSE Code
     */
    private static String getOrdTakeMdseCd(NWAL1840CMsg bizMsg, String mdseCd) {

        // 01/24/2017 QC#17078 Add Start
        if (mdseCd.length() < 8) {
            return mdseCd;
        }
        // 01/24/2017 QC#17078 Add End

        ORD_TAKE_MDSETMsg ordTakeMdseTMsg = new ORD_TAKE_MDSETMsg();
        ordTakeMdseTMsg.setSQLID("002");
        ordTakeMdseTMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        ordTakeMdseTMsg.setConditionValue("ordTakeMdseCd01", mdseCd.substring(0, 8));

        ORD_TAKE_MDSETMsgArray tMsgArray = (ORD_TAKE_MDSETMsgArray) EZDTBLAccessor.findByCondition(ordTakeMdseTMsg);
        if (0 == tMsgArray.getValidCount()) {
            return mdseCd;
        } else {
            return tMsgArray.no(0).ordTakeMdseCd.getValue();
        }
    }
    // Add Start 2016/09/08 S21_NA#11655
}
