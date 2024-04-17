/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NWAL1500.common;

import static business.blap.NWAL1500.constant.NWAL1500Constant.BIZ_ID;

import com.canon.cusa.s21.common.NWX.NWXC150001.NWXC150001DsCheck;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_CATG_CTX_TP;
import com.canon.cusa.s21.framework.common.S21StringUtil;

import parts.common.EZDCStringItem;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NWAL1500.NWAL1500CMsg;
import business.blap.NWAL1500.NWAL1500Query;
import business.blap.NWAL1500.NWAL1500_ACMsg;
import business.blap.NWAL1500.NWAL1500_CCMsg;
import business.blap.NWAL1500.constant.NWAL1500Constant;
import business.db.ORD_CATG_BIZ_CTXTMsg;
import business.db.ORD_CATG_BIZ_CTXTMsgArray;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/04   Fujitsu         T.ishii         Create          n/a
 * 2018/07/27   Fujitsu         T.Noguchi       Update          S21_NA#14307
 * 2018/09/06   Fujitsu         M.Ishii         Update          S21_NA#27941
 * </pre>
 */
public class NWAL1500CommonLogicForSpecialInstruction {

    /**
     * Get Trx Rule Type
     * @param bizMsg NWAL1500CMsg
     * @return Trx Rule Type
     */
    public static String getTrxRuleTp(NWAL1500CMsg bizMsg) {
        ORD_CATG_BIZ_CTXTMsg tMsg = getOrdCatgBizCtx(bizMsg);
        if (tMsg == null) {
            return null;
        }
        return tMsg.firstBizCtxAttrbTxt.getValue();
    }

    private static ORD_CATG_BIZ_CTXTMsg getOrdCatgBizCtx(NWAL1500CMsg bizMsg) {

        ORD_CATG_BIZ_CTXTMsg inTMsg = new ORD_CATG_BIZ_CTXTMsg();
        inTMsg.setSQLID("005");
        inTMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        inTMsg.setConditionValue("ordCatgCtxTpCd01A", NWAL1500Constant.EQUIPMENT_ORDER);
        inTMsg.setConditionValue("ordCatgCtxTpCd01B", NWAL1500Constant.SUPPLIES_ORDER);
        inTMsg.setConditionValue("dsOrdCatgCd01", bizMsg.dsOrdCatgCd.getValue());
        inTMsg.setConditionValue("dsOrdTpCd01", bizMsg.dsOrdTpCd.getValue());
        ORD_CATG_BIZ_CTXTMsgArray array = (ORD_CATG_BIZ_CTXTMsgArray) EZDTBLAccessor.findByCondition(inTMsg);
        if (array == null || array.length() == 0) {
            return null;
        }
        return array.no(0);
    }

    // 2018/07/27 S21_NA#14307 Add Start
    /**
     * Init Process Special Instruction
     * @param bizMsg NWAL1500CMsg
     * @param configMsg NWAL1500_ACMsg
     * @param rmaMsg NWAL1500_CCMsg
     * @param noCheckItem EZDCStringItem
     */
    public static void initProcSpecialInstruction(NWAL1500CMsg bizMsg, NWAL1500_ACMsg configMsg, NWAL1500_CCMsg rmaMsg, EZDCStringItem noCheckItem) {

        // Initialize
        bizMsg.sellToCustCd_SP.clear();
        bizMsg.billToCustCd_SP.clear();
        bizMsg.shipToCustCd_SP.clear();
        
        // set Backup Item
        if (configMsg != null) { // Line Config
            ZYPEZDItemValueSetter.setValue(bizMsg.sellToCustCd_BK, configMsg.sellToCustCd_LC);
            ZYPEZDItemValueSetter.setValue(bizMsg.billToCustCd_BK, configMsg.billToCustCd_LC);
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustCd_BK, configMsg.shipToCustCd_LC);
        }
        else if (rmaMsg != null) { // RMA
            ZYPEZDItemValueSetter.setValue(bizMsg.sellToCustCd_BK, rmaMsg.sellToCustCd_RC);
            ZYPEZDItemValueSetter.setValue(bizMsg.billToCustCd_BK, rmaMsg.billToCustCd_RC);
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustCd_BK, rmaMsg.shipToCustCd_RC);
        }
        else { // Header
            ZYPEZDItemValueSetter.setValue(bizMsg.sellToCustCd_BK, bizMsg.sellToCustCd);
            ZYPEZDItemValueSetter.setValue(bizMsg.billToCustCd_BK, bizMsg.billToCustCd);
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustCd_BK, bizMsg.shipToCustCd);
        }
        // noCheckItem setting
        if (noCheckItem != null) {
            noCheckItem.clear();
        }
    }

    /**
     * Init Process Special Instruction for Clear
     * @param bizMsg NWAL1500CMsg
     */
    public static void initProcSpecialInstructionForClear(NWAL1500CMsg bizMsg) {

        // Initialize
        bizMsg.sellToCustCd_SP.clear();
        bizMsg.billToCustCd_SP.clear();
        bizMsg.shipToCustCd_SP.clear();

        bizMsg.sellToCustCd_BK.clear();
        bizMsg.billToCustCd_BK.clear();
        bizMsg.shipToCustCd_BK.clear();
    }
 
    /**
     * Common Process For Special Instruction
     * @param bizMsg NWAL1500CMsg
     * @param configMsg NWAL1500_ACMsg
     * @param rmaMsg NWAL1500_CCMsg
     */
    public static void cmnProcForSpecialInstruction(NWAL1500CMsg bizMsg, NWAL1500_ACMsg configMsg, NWAL1500_CCMsg rmaMsg) {

        if (bizMsg == null) {
            return;
        }

        // Check display Other Popup
        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxRqstFlg)) {
            return;
        }

        boolean isDisplay = false;

        String glblCmpyCd = bizMsg.glblCmpyCd.getValue();
        String trxRuleTp = NWAL1500CommonLogicForSpecialInstruction.getTrxRuleTp(bizMsg);
        String funcCatgId = NWAL1500Query.getInstance().getOrdCatgBizCtxFstAttbTxt(bizMsg.glblCmpyCd.getValue(), ORD_CATG_CTX_TP.CUST_SPCL_ORDER_CATEGORY, bizMsg.dsOrdCatgCd.getValue(), bizMsg.dsOrdTpCd.getValue());

        String sellToCustCd = null;
        String billToCustCd = null;
        String shipToCustCd = null;
        // set Backup Item
        if (configMsg != null) { // Line Config
            sellToCustCd = configMsg.sellToCustCd_LC.getValue();
            billToCustCd = configMsg.billToCustCd_LC.getValue();
            shipToCustCd = configMsg.shipToCustCd_LC.getValue();
        }
        else if (rmaMsg != null) { // RMA
            sellToCustCd = rmaMsg.sellToCustCd_RC.getValue();
            billToCustCd = rmaMsg.billToCustCd_RC.getValue();
            shipToCustCd = rmaMsg.shipToCustCd_RC.getValue();
        }
        else { // Header
            sellToCustCd = bizMsg.sellToCustCd.getValue();
            billToCustCd = bizMsg.billToCustCd.getValue();
            shipToCustCd = bizMsg.shipToCustCd.getValue();
        }

        // Check common function result
        // Sold To
        if (ZYPCommonFunc.hasValue(sellToCustCd) && !S21StringUtil.isEquals(sellToCustCd, bizMsg.sellToCustCd_BK.getValue())) {

            if (NWXC150001DsCheck.spclInstnDisplayDetermination(glblCmpyCd, trxRuleTp, null, sellToCustCd, null, null, BIZ_ID, funcCatgId, bizMsg.lineBizTpCd.getValue())) {
                ZYPEZDItemValueSetter.setValue(bizMsg.sellToCustCd_SP, sellToCustCd);
                isDisplay = true;
            }
        }

        // Bill To
        if (ZYPCommonFunc.hasValue(billToCustCd) && !S21StringUtil.isEquals(billToCustCd, bizMsg.billToCustCd_BK.getValue())) {

            if (NWXC150001DsCheck.spclInstnDisplayDetermination(glblCmpyCd, trxRuleTp, null, null, billToCustCd, null, BIZ_ID, funcCatgId, bizMsg.lineBizTpCd.getValue())) {
                ZYPEZDItemValueSetter.setValue(bizMsg.billToCustCd_SP, billToCustCd);
                isDisplay = true;
            }
        }

        // Ship To
        if (ZYPCommonFunc.hasValue(shipToCustCd) && !S21StringUtil.isEquals(shipToCustCd, bizMsg.shipToCustCd_BK.getValue())) {

            if (NWXC150001DsCheck.spclInstnDisplayDetermination(glblCmpyCd, trxRuleTp, null, null, null, shipToCustCd, BIZ_ID, funcCatgId, bizMsg.lineBizTpCd.getValue())) {
                ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustCd_SP, shipToCustCd);
                isDisplay = true;
            }
        }

        if (isDisplay) {
            // NMAL3300 param set
            ZYPEZDItemValueSetter.setValue(bizMsg.xxPopPrm_P2, funcCatgId);
            ZYPEZDItemValueSetter.setValue(bizMsg.xxPopPrm_P3, trxRuleTp);
        }
    }
}
