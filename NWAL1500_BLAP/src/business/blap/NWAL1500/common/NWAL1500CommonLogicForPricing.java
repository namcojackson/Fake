/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1500.common;

import parts.common.EZDMsg;
import business.blap.NWAL1500.NWAL1500CMsg;
import business.blap.NWAL1500.NWAL1500SMsg;
import business.blap.NWAL1500.NWAL1500_ISMsg;
import business.parts.NWZC157001PMsg;
import business.parts.NWZC157002PMsg;
import business.parts.NWZC157003PMsg;
import business.parts.NWZC157004PMsg;

import com.canon.cusa.s21.common.NWX.NWXC150001.NWXC150001DsCheck;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ORD_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CPO_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;

import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.ZZM9037E;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/17   Fujitsu         S.Takami        Create          S21_NA#2166
 * 2016/06/07   Fujitsu         T.Murai         Update          S21_NA#6442
 * 2016/06/06   Fujitsu         Y.Kanefusa      Update          S21_NA#6480
 * 2017/11/24   Fujitsu         Y.Kanefusa      Update          S21_NA#22789
 * 2018/01/31   Fujitsu         S.Takami        Update          S21_NA#19808
 * 2018/03/15   Fujitsu         S.Takami        Update          S21_NA#19808-2(bizMsg.I->glblMsg.I without any comments)
 * 2018/03/28   Fujitsu         A.Kosai         Update          S21_NA#24853
 * 2018/07/09   Fujitsu         S.Takami        Update          S21_NA#26895
 * 2018/08/30   Fujitsu         S.Takami        Update          S21_NA#26895-3
 * 2019/01/11   Fujitsu         K.Ishizuka      Update          S21_NA#29811
 * 2023/03/07   CITS            R.Azucena       Update          QC#61251
 * 2023/05/11   CITS            R.Azucena       Update          QC#61514
 *</pre>
 */
public class NWAL1500CommonLogicForPricing {

    private static final boolean LOG_OUT = false;

    public static void calculationOrderAmount(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {

        NWAL1500CommonLogic.prepareSetToConponent(glblMsg); // S21_NA#2522 // 2018/01/31 S21_NA#19808 Mod

        // Mandatory Check
        // START 2023/03/07 R.Azucena [QC#61251 MOD]
        // if (!NWAL1500CommonLogic.checkMandatoryHeaderTab(bizMsg)) {
        //     return;
        // }
        // if (!NWAL1500CommonLogic.checkMandatoryAddlHeaderTab(bizMsg)) {
        //     return;
        // }
        if (!("NWAL1500_NWAL2000".equals(bizMsg.getScreenAplID()) && DS_ORD_CATG.INTERNAL_CSA.equals(bizMsg.dsOrdCatgCd.getValue()))) {
            if (!NWAL1500CommonLogic.checkMandatoryHeaderTab(bizMsg)) {
                // START 2023/05/11 R.Azucena [QC#61514 ADD]
                bizMsg.setMessageInfo(ZZM9037E);
                // END 2023/05/11 R.Azucena [QC#61514 ADD]
                return;
            }
            if (!NWAL1500CommonLogic.checkMandatoryAddlHeaderTab(bizMsg)) {
                // START 2023/05/11 R.Azucena [QC#61514 ADD]
                bizMsg.setMessageInfo(ZZM9037E);
                // END 2023/05/11 R.Azucena [QC#61514 ADD]
                return;
            }
        }
        // END 2023/03/07 R.Azucena [QC#61251 MOD]
        if (!NWAL1500CommonLogic.checkMandatoryLineConfigTab(bizMsg, glblMsg)) {
            // START 2023/05/11 R.Azucena [QC#61514 ADD]
            bizMsg.setMessageInfo(ZZM9037E);
            // END 2023/05/11 R.Azucena [QC#61514 ADD]
            return;
        }
        if (!NWAL1500CommonLogic.checkMandatoryRMATab(bizMsg, glblMsg)) {
            // START 2023/05/11 R.Azucena [QC#61514 ADD]
            bizMsg.setMessageInfo(ZZM9037E);
            // END 2023/05/11 R.Azucena [QC#61514 ADD]
            return;
        }

        NWAL1500CommonLogicForSaveSubmit commonLogic = NWAL1500CommonLogicForSaveSubmit.getInstance();
        if (commonLogic.isHeaderTabMasterError(bizMsg, false)) {
            // START 2023/05/11 R.Azucena [QC#61514 ADD]
            bizMsg.setMessageInfo(ZZM9037E);
            // END 2023/05/11 R.Azucena [QC#61514 ADD]
            return;
        }
        if (commonLogic.isAddlHeaderTabMasterError(bizMsg, false)) {
            // START 2023/05/11 R.Azucena [QC#61514 ADD]
            bizMsg.setMessageInfo(ZZM9037E);
            // END 2023/05/11 R.Azucena [QC#61514 ADD]
            return;
        }
        if (commonLogic.isLineConfigTabMasterError(bizMsg, glblMsg, false)) { // 2018/01/31 S21_NA#19808 Mod
            // START 2023/05/11 R.Azucena [QC#61514 ADD]
            bizMsg.setMessageInfo(ZZM9037E);
            // END 2023/05/11 R.Azucena [QC#61514 ADD]
            return;
        }
        if (commonLogic.isRMATabMasterError(bizMsg, glblMsg, false)) { // 2018/01/31 S21_NA#19808 Mod
            // START 2023/05/11 R.Azucena [QC#61514 ADD]
            bizMsg.setMessageInfo(ZZM9037E);
            // END 2023/05/11 R.Azucena [QC#61514 ADD]
            return;
        }

        // 2016/06/07 S21_NA#6442 Add Start
        if (CPO_SRC_TP.CHANGE_ORDER_MODIFICATION.equals(bizMsg.cpoSrcTpCd.getValue())) {
            return;
        }
        // 2016/06/07 S21_NA#6442 Add End
        // 2018/03/28 S21_NA#24853 Add Start
        if (NWXC150001DsCheck.checkInternalOrder(bizMsg.glblCmpyCd.getValue(), bizMsg.dsOrdTpCd.getValue(), null, bizMsg.slsDt.getValue())) {
            return;
        }
        // 2018/03/28 S21_NA#24853 Add End
        // 2018/08/30 S21_NA#26895-3 Del Start
//        // 2018/07/09 S21_NA#26895 Add Start
//        if (glblMsg.A.getValidCount() > 0) {
//            NWAL1500CommonLogic.setRtlWhCdAsBaseComponent(bizMsg, glblMsg, CONFIG_CATG.OUTBOUND);
//        }
//        if (glblMsg.C.getValidCount() > 0) {
//            NWAL1500CommonLogic.setRtlWhCdAsBaseComponent(bizMsg, glblMsg, CONFIG_CATG.INBOUND);
//        }
//        // 2018/07/09 S21_NA#26895 Add End
        // 2019/01/11 S21_NA#29811 Add Start
        for (int i = 0; i < glblMsg.C.getValidCount(); i++) {
            NWAL1500CommonLogic.setRtlWhCdAsBaseComponent(bizMsg, glblMsg, glblMsg.C.no(i).dsOrdPosnNum_RC.getValue(), CONFIG_CATG.INBOUND);
        }
        // 2019/01/11 S21_NA#29811 Add End
        // 2018/08/30 S21_NA#26895-3 Del End
        // QC#6480 2016/06/06 Del Start
        // Line Config Tab
//        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
//            NWAL1500_BCMsg lineMsg = bizMsg.B.no(i);
//            // NWAL1500_ISMsg glblBasePriceLine =
//            // NWAL1500CommonLogic.getBasePriceDataFromGlobalMsg(lineMsg,
//            // glblMsg);
//            // if (null != glblBasePriceLine) {
//            // if
//            // (lineMsg.entCpoDtlDealSlsAmt_LL.getValue().compareTo(glblBasePriceLine.autoPrcAmtRate_LP.getValue())
//            // != 0) {
//            // ZYPEZDItemValueSetter.setValue(glblBasePriceLine.manPrcAmtRate_LP,
//            // lineMsg.entCpoDtlDealSlsAmt_LL);
//            // }
//            // }
//            // 2016/01/28 S21_NA#3254 Add Start
//            String ordLineSts = bizMsg.varCharConstVal_TB.getValue();
//            if (ordLineSts.equals(lineMsg.ordLineStsDescTxt_LL.getValue()) || ORD_LINE_STS.CANCELLED.equals(lineMsg.ordLineStsCd_LL.getValue())) {
//                continue;
//            }
//            // 2016/01/28 S21_NA#3254 Add End
//            NWAL1500_ISMsg bizBasePriceLine = NWAL1500CommonLogic.getBasePriceDataFromBizlMsg(lineMsg, bizMsg);
//            if (null != bizBasePriceLine) {
//                if (lineMsg.entCpoDtlDealSlsAmt_LL.getValue().compareTo(bizBasePriceLine.autoPrcAmtRate_LP.getValue()) != 0) {
//                    // QC#6480
//                    // ZYPEZDItemValueSetter.setValue(bizBasePriceLine.manPrcAmtRate_LP, lineMsg.entCpoDtlDealSlsAmt_LL);
//                    ZYPEZDItemValueSetter.setValue(bizBasePriceLine.prcCondManEntryFlg_LP, ZYPConstant.FLG_ON_Y);
//                }
//            }
//        }
//
//        // RMA Line
//        for (int i = 0; i < bizMsg.D.getValidCount(); i++) {
//            NWAL1500_DCMsg rmaLineMsg = bizMsg.D.no(i);
//            // 2016/01/28 S21_NA#3254 Add Start
//            String ordLineSts = bizMsg.varCharConstVal_TB.getValue();
//            if (ordLineSts.equals(rmaLineMsg.rtrnLineStsDescTxt_RL.getValue()) || RTRN_LINE_STS.CANCELLED.equals(rmaLineMsg.ordLineStsCd_RL.getValue())) {
//                continue;
//            }
//            // 2016/01/28 S21_NA#3254 Add End
//            NWAL1500_ISMsg bizBasePriceLine = NWAL1500CommonLogic.getBasePriceDataFromBizlMsg(rmaLineMsg, bizMsg);
//            if (null != bizBasePriceLine) {
//                if (rmaLineMsg.entCpoDtlDealSlsAmt_RL.getValue().compareTo(bizBasePriceLine.autoPrcAmtRate_LP.getValue()) != 0) {
//                    // QC#6480
//                    // ZYPEZDItemValueSetter.setValue(bizBasePriceLine.manPrcAmtRate_LP, rmaLineMsg.entCpoDtlDealSlsAmt_RL);
//                    ZYPEZDItemValueSetter.setValue(bizBasePriceLine.prcCondManEntryFlg_LP, ZYPConstant.FLG_ON_Y);
//                }
//            }
//        }
        // QC#6480 2016/06/06 Del End

        // Call Pricing API Get Order All Mode
        if (!NWAL1500CommonLogic.callPricingApiGetOrderAllMode(bizMsg, glblMsg, ZYPConstant.FLG_OFF_N)) {
            return;
        }

        // S21_NA#1952
        /**************************************************
         * store back up data for $ button.
         **************************************************/
        ZYPTableUtil.clear(glblMsg.T);
        ZYPTableUtil.clear(glblMsg.U);
        ZYPTableUtil.clear(glblMsg.V);
        ZYPTableUtil.clear(glblMsg.W);
        ZYPTableUtil.clear(glblMsg.X);
        // EZDMsg.copy(bizMsg, "", glblMsg, "D");
        ZYPEZDItemValueSetter.setValue(glblMsg.dsOrdCatgDescTxt_D, bizMsg.dsOrdCatgDescTxt);
        ZYPEZDItemValueSetter.setValue(glblMsg.dsOrdTpCd_D, bizMsg.dsOrdTpCd);
        ZYPEZDItemValueSetter.setValue(glblMsg.dsOrdCatgCd_D, bizMsg.dsOrdCatgCd);
        ZYPEZDItemValueSetter.setValue(glblMsg.sellToCustCd_D, bizMsg.sellToCustCd);
        ZYPEZDItemValueSetter.setValue(glblMsg.negoDealAmt_D, bizMsg.negoDealAmt);
        ZYPEZDItemValueSetter.setValue(glblMsg.slsRepTocCd_D, bizMsg.slsRepTocCd);
        ZYPEZDItemValueSetter.setValue(glblMsg.slsRepTocNm_D, bizMsg.slsRepTocNm);
        ZYPEZDItemValueSetter.setValue(glblMsg.custIssPoNum_D, bizMsg.custIssPoNum);
        ZYPEZDItemValueSetter.setValue(glblMsg.leaseCmpyPoNum_D, bizMsg.leaseCmpyPoNum);
        ZYPEZDItemValueSetter.setValue(glblMsg.spclHdlgTpCd_D, bizMsg.spclHdlgTpCd);
        ZYPEZDItemValueSetter.setValue(glblMsg.shpgSvcLvlCd_D, bizMsg.shpgSvcLvlCd);
        ZYPEZDItemValueSetter.setValue(glblMsg.frtCondDescTxt_D, bizMsg.frtCondDescTxt);
        ZYPEZDItemValueSetter.setValue(glblMsg.carrSvcLvlDescTxt_D, bizMsg.carrSvcLvlDescTxt);
        ZYPEZDItemValueSetter.setValue(glblMsg.pmtTermCashDiscDescTxt_D, bizMsg.pmtTermCashDiscDescTxt);
        ZYPEZDItemValueSetter.setValue(glblMsg.prcContrNum_D, bizMsg.prcContrNum);
        ZYPEZDItemValueSetter.setValue(glblMsg.leasePrchOptCd_D, bizMsg.leasePrchOptCd);
        ZYPEZDItemValueSetter.setValue(glblMsg.leaseTermMthAot_D, bizMsg.leaseTermMthAot);
        ZYPEZDItemValueSetter.setValue(glblMsg.leaseTermMthAot_DE, bizMsg.leaseTermMthAot_EM); // QC#22789 2017/11/28 Add
        ZYPEZDItemValueSetter.setValue(glblMsg.leasePmtFreqCd_D, bizMsg.leasePmtFreqCd);
        EZDMsg.copy(glblMsg.A, null, glblMsg.T, null);
        EZDMsg.copy(glblMsg.B, null, glblMsg.U, null);
        EZDMsg.copy(glblMsg.C, null, glblMsg.V, null);
        EZDMsg.copy(glblMsg.D, null, glblMsg.W, null);
        EZDMsg.copy(glblMsg.I, null, glblMsg.X, null);
    }

    /**** below methods for debug logging****/
    /**
     * <pre>
     * For Debugging. Print out elements
     * @param bizMsg
     * @param opt
     */
    private static void printPriceElem(NWAL1500SMsg glblMsg, String opt) {
        if (!LOG_OUT) {
            return;
        }
        final StringBuilder sb = new StringBuilder();
        sb.append("####---------- Price Element NWAL1500_ISMsg Start ----------####\r\n");
        sb.append(opt + "\r\n");
        for (int i = 0; i < glblMsg.I.getValidCount(); i++) {
            NWAL1500_ISMsg prcElem = glblMsg.I.no(i);
            sb.append("No[" + String.valueOf(i) + "]\r\n");
            sb.append("Config Category Code     :" + prcElem.configCatgCd_LP.getValue() + "\r\n");
            sb.append("Detail Line#             :" + prcElem.cpoDtlLineNum_LP.getValue() + "\r\n");
            sb.append("Detail Line Sub#         :" + prcElem.cpoDtlLineSubNum_LP.getValue() + "\r\n");
            sb.append("Display Line #           :" + prcElem.xxLineNum_LP.getValue() + "\r\n");
            sb.append("Price Detail Group Cd    :" + prcElem.prcDtlGrpCd_LP.getValue() + "\r\n");
            sb.append("Auto Price Amount Rate   :" + String.valueOf(prcElem.autoPrcAmtRate_LP.getValue()) + "\r\n");
            sb.append("Manual Price Amount Rate :" + String.valueOf(prcElem.manPrcAmtRate_LP.getValue()) + "\r\n");
            sb.append("Order Price Calc Base PK :" + prcElem.ordPrcCalcBasePk_LP.getValue() + "\r\n");
            sb.append("Special Cond Price PK    :" + prcElem.specCondPrcPk_LP.getValue() + "\r\n");
        }
        sb.append(opt + "\r\n");
        sb.append("####---------- Price Element NWAL1500_ISMsg End ----------####\r\n");
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
}
