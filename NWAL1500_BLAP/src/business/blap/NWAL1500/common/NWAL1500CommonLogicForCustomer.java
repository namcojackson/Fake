/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NWAL1500.common;

import static business.blap.NWAL1500.constant.NWAL1500Constant.BLANK;
import static business.blap.NWAL1500.constant.NWAL1500Constant.COMMA;
import static business.blap.NWAL1500.constant.NWAL1500Constant.NO_SLCT_DTL;
import static business.blap.NWAL1500.constant.NWAL1500Constant.SPACE;
import static business.blap.NWAL1500.constant.NWAL1500Constant.TAB_HEADER;
import static business.blap.NWAL1500.constant.NWAL1500Constant.TAB_LINE_CONFIG;
import static business.blap.NWAL1500.constant.NWAL1500Constant.TAB_RMA;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.MSG_PARAM_SHIP_TO_LOC;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NWAM0181E;

import java.util.List;
import java.util.Map;

import parts.common.EZDCStringItem;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NWAL1500.NWAL1500CMsg;
import business.blap.NWAL1500.NWAL1500Query;
import business.blap.NWAL1500.NWAL1500QueryForAddlHeader;
import business.blap.NWAL1500.NWAL1500QueryForCustomer;
import business.blap.NWAL1500.NWAL1500SMsg;
import business.blap.NWAL1500.NWAL1500_ACMsg;
import business.blap.NWAL1500.NWAL1500_ASMsg;
import business.blap.NWAL1500.NWAL1500_BSMsg;
import business.blap.NWAL1500.NWAL1500_CCMsg;
import business.blap.NWAL1500.NWAL1500_CSMsg;
import business.db.ORD_CATG_BIZ_CTXTMsg;
import business.db.ORD_CATG_BIZ_CTXTMsgArray;
import business.db.SELL_TO_CUSTTMsg;
import business.db.SELL_TO_CUSTTMsgArray;
import business.db.SHIP_TO_CUSTTMsg;
import business.db.SHIP_TO_CUSTTMsgArray;
import business.parts.NMZC610001PMsg;

import com.canon.cusa.s21.common.NWX.NWXC150001.NWXC150001DefaultCustomer;
import com.canon.cusa.s21.common.NWX.NWXC150001.NWXC150001DefaultCustomerBean;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_CATG_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/05   Fujitsu         T.Yoshida       Create          n/a
 * 2016/02/23   Fujitsu         Y.Taoka         Update          QC#1694
 * 2016/03/25   Fujitsu         S.Takami        Update          S21_NA#4693
 * 2016/09/28   Fujitsu         S.Takami        Update          S21_NA#8659
 * 2016/12/05   Fujitsu         T.Yoshida       Update          S21_NA#15889
 * 2017/07/13   Fujitsu         S.Takami        Update          S21_NA#19912
 * 2017/07/21   Fujitsu         S.Takami        Update          S21_NA#19847
 * 2017/08/15   Fujitsu         S.Takami        Update          S21_NA#20375
 * 2017/12/08   Fujitsu         A.Kosai         Update          S21_NA#21621
 * 2018/01/26   Fujitsu         K.Ishizuka      Update          S21_NA#23140
 * 2018/01/30   Fujitsu         S.Takami        Update          S21_NA#19808
 * 2018/03/12   Fujitsu         K.Ishizuka      Update          S21_NA#24090
 * 2018/03/12   Fujitsu         A.Kosai         Update          S21_NA#20297(Sol#379)
 * 2018/06/29   Fujitsu         M.Ohno          Update          S21_NA#23726-2
 * 2018/07/27   Fujitsu         T.Noguchi       Update          S21_NA#14307
 * 2018/09/20   Fujitsu         S.Takami        Update          S21_NA#28199
 * 2019/01/31   Fujitsu         R.Nakamura      Update          S21_NA#30097
 * 2019/03/13   Fujitsu         Hd.Sugawara     Update          S21_NA#30730
 * </pre>
 */
public class NWAL1500CommonLogicForCustomer {

    /**
     * Get DS Accont Customer Info
     * @param bizMsg NWAL1500CMsg
     * @param isCallNameFld Called Name Field
     * @param targetItem Target Item
     * @param msgParm Message Parameter
     * @return DS Accont Customer Info
     */
    public static SELL_TO_CUSTTMsg getDsAcctCustInfo(NWAL1500CMsg bizMsg, boolean isCallNameFld, EZDCStringItem targetItem, String msgParm) {

        SELL_TO_CUSTTMsgArray sellToCustTMsgArray = null;
        if (isCallNameFld) {
            sellToCustTMsgArray = getDsSlsAcctCustForName(bizMsg, targetItem.getValue());
        } else {
            sellToCustTMsgArray = getDsSlsAcctCustForAcct(bizMsg, targetItem.getValue());
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_OFF_N);

        if (sellToCustTMsgArray.getValidCount() == 0) {
            targetItem.setErrorInfo(1, NWAM0181E, new String[] {msgParm });
            return null;
        } else if (sellToCustTMsgArray.getValidCount() != 1) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_ON_Y);
            // 2018/07/27 S21_NA#14307 Add Start
            NWAL1500CommonLogicForCustomer.setAccountSearchPopupParam(bizMsg);
            // 2018/07/27 S21_NA#14307 Add End
            return null;
        }

        return sellToCustTMsgArray.no(0);
    }

    /**
     * Common Process For Derive From Bill To
     * @param bizMsg NWAL1500CMsg
     * @param sellToCustTMsg SELL_TO_CUST
     * @param billToCustCd Bill To Customer Code
     */
    public static void cmnProcForDeriveFromBillTo(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, SELL_TO_CUSTTMsg sellToCustTMsg, String billToCustCd) { // 2016/09/28 S21_NA#8659 Add billToCustCd // 2018/01/30 S21_NA#19808

        // 2016/09/28 S21_NA#8659 Mod Start
//        NMZC610001PMsg nMZC610001PMsg = callCustInfoGetApiForDefaultMode(bizMsg, dsAcctCustTMsg.dsAcctNum.getValue());
        // Mod Start 2019/01/31 QC#30097
//        NMZC610001PMsg nMZC610001PMsg = callCustInfoGetApiForDefaultMode(bizMsg, sellToCustTMsg.sellToCustCd.getValue(), null, billToCustCd, NWXC150001DefaultCustomer.MODE_BILL_TO_ACCT);
        NMZC610001PMsg nMZC610001PMsg = callCustInfoGetApiForDefaultMode(bizMsg, sellToCustTMsg.sellToCustCd.getValue(), null, billToCustCd, NWXC150001DefaultCustomer.MODE_BILL_TO_ACCT, ZYPConstant.FLG_OFF_N);
        // Mod End 2019/01/31 QC#30097
        // 2016/09/28 S21_NA#8659 Mod End
        if (nMZC610001PMsg == null) {
            return;
        }

        setBillToInfo(bizMsg, sellToCustTMsg, nMZC610001PMsg);

        if (TAB_HEADER.equals(bizMsg.xxDplyTab.getValue())) {
            // 2018/01/30 S21_NA#19808 Add Start
            for (int i = 0; i < bizMsg.A.getValidCount(); i++ ) {
                NWAL1500CommonLogicForPagination.copyConfigDataToSMsg(bizMsg.A.no(i), glblMsg);
            }
            for (int i = 0; i < bizMsg.C.getValidCount(); i++ ) {
                NWAL1500CommonLogicForPagination.copyRmaConfigDataToSMsg(bizMsg.C.no(i), glblMsg);
            }
            // 2018/01/30 S21_NA#19808 Add End
            NWAL1500CommonLogicForCategory.deriveDefaultPmtTerm(bizMsg);
            deriveDefaultBillToInfoForDtlFld(bizMsg, glblMsg); // 2018/01/30 S21_NA#19808
            // 2018/01/30 S21_NA#19808 Add Start
            NWAL1500CommonLogicForPagination.pagingLineConfig(bizMsg, glblMsg);
            // 2018/01/30 S21_NA#19808 Add End
        }
    }

    /**
     * Common Process For Derive From Ship To
     * @param bizMsg NWAL1500CMsg
     * @param glblMsg Global Message
     * @param isSetShipToFlg Set Ship To Flag
     */
    public static void cmnProcForDeriveFromShipTo(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, boolean isSetShipToFlg) { // 2018/01/30 S21_NA#19808 Mod

        String dplyTab = bizMsg.xxDplyTab.getValue();
        int slctIndex = bizMsg.xxCellIdx.getValueInt();
        String shipToCustAcctCd = null;
        String shipToCustCd = null; // 2016/09/28 S21_NA#8659 Add
        // 2018/01/30 S21_NA#19808 Add Start
        NWAL1500_ACMsg configMsg = null;
        NWAL1500_CCMsg rmaConfigMsg = null;
        // 2018/01/30 S21_NA#19808 Add End
        // 2018/09/20 S21_NA#28199 Add Start
        int bizMsgCnt = bizMsg.xxCellIdx.getValueInt();
        // 2018/09/20 S21_NA#28199 Add End

        if (TAB_LINE_CONFIG.equals(dplyTab)) {
            shipToCustAcctCd = bizMsg.A.no(slctIndex).shipToCustAcctCd_LC.getValue();
            shipToCustCd = bizMsg.A.no(slctIndex).shipToCustCd_LC.getValue(); // 2016/09/28 S21_NA#8659 Add
            // 2018/01/30 S21_NA#19808 Add Start
            configMsg = bizMsg.A.no(slctIndex);
            slctIndex = NWAL1500CommonLogicForPagination.getDetectedDetailIndexOfGlobalMessage(configMsg, glblMsg);
            // 2018/01/30 S21_NA#19808 Add End
        } else if (TAB_RMA.equals(dplyTab)) {
            shipToCustAcctCd = bizMsg.C.no(slctIndex).shipToCustAcctCd_RC.getValue();
            shipToCustCd = bizMsg.C.no(slctIndex).shipToCustCd_RC.getValue(); // 2016/09/28 S21_NA#8659 Add
            // 2018/01/30 S21_NA#19808 Add Start
            rmaConfigMsg = bizMsg.C.no(slctIndex);
            slctIndex = NWAL1500CommonLogicForPagination.getDetectedDetailIndexOfGlobalMessage(rmaConfigMsg, glblMsg);
            // 2018/01/30 S21_NA#19808 Add End
        } else {
            shipToCustAcctCd = bizMsg.shipToCustAcctCd.getValue();
            shipToCustCd = bizMsg.shipToCustCd.getValue(); // 2016/09/28 S21_NA#8659 Add
        }

        // Mod Start 2019/01/31 QC#30097
        if (isSetShipToFlg) {
        // 2016/09/28 S21_NA#8659 Mod Start
//        NMZC610001PMsg nMZC610001PMsg = callCustInfoGetApiForDefaultMode(bizMsg, shipToCustAcctCd);
//            NMZC610001PMsg nMZC610001PMsg = callCustInfoGetApiForDefaultMode(bizMsg, shipToCustAcctCd, shipToCustCd, null, NWXC150001DefaultCustomer.MODE_SHIP_ACCT_TO_BILL_TO);
            // 2016/09/28 S21_NA#8659 Mod End
            NMZC610001PMsg nMZC610001PMsg = callCustInfoGetApiForDefaultMode(bizMsg, shipToCustAcctCd, shipToCustCd, null, NWXC150001DefaultCustomer.MODE_SHIP_ACCT_TO_BILL_TO, ZYPConstant.FLG_OFF_N);
            if (nMZC610001PMsg == null) {
                return;
            }

//        if (isSetShipToFlg) {
            setShipToInfo(bizMsg, nMZC610001PMsg);
            if (TAB_LINE_CONFIG.equals(dplyTab)) {
                shipToCustCd = bizMsg.A.no(slctIndex).shipToCustCd_LC.getValue();
            } else if (TAB_RMA.equals(dplyTab)) {
                shipToCustCd = bizMsg.C.no(slctIndex).shipToCustCd_RC.getValue();
            } else {
                shipToCustCd = bizMsg.shipToCustCd.getValue();
            }
        }
        // Mod End 2019/01/31 QC#30097

        // Add Start 2019/01/31 QC#30097
        // Del Start 2019/03/13 QC#30730
        //NMZC610001PMsg nMZC610001BSPMsg = callCustInfoGetApiForDefaultMode(bizMsg, shipToCustAcctCd, shipToCustCd, null, NWXC150001DefaultCustomer.MODE_SHIP_ACCT_TO_BILL_TO, ZYPConstant.FLG_ON_Y);
        //if (nMZC610001BSPMsg == null) {
        //    return;
        //}
        // Del End 2019/03/13 QC#30730
        // Add End 2019/01/31 QC#30097

        // Mod Start 2019/01/31 QC#30097
//        String defBillToCustCd = nMZC610001PMsg.DefaultBillShipList.no(0).billToCustCd.getValue();
        // Del Start 2019/03/13 QC#30730
        //String defBillToCustCd = nMZC610001BSPMsg.DefaultBillShipList.no(0).billToCustCd.getValue();
        // Del End 2019/03/13 QC#30730
        // Mod End 2019/01/31 QC#30097
        // Del Start 2019/03/13 QC#30730
        //setBillToInfo(bizMsg, defBillToCustCd);
        // Del End 2019/03/13 QC#30730

        // Add Start 2019/03/13 QC#30730
        boolean isSoldToSet = false;
        if (ZYPCommonFunc.hasValue(bizMsg.soldToCustAcctNm) && ZYPCommonFunc.hasValue(bizMsg.sellToCustCd)) {
            isSoldToSet = true;
        }
        // Add End 2019/03/13 QC#30730

        if (TAB_HEADER.equals(dplyTab)) {
            // 2017/08/15 S21_NA#20375 Mod Start
//            if (!setSoldToInfo(bizMsg)) {
//                return;
//            }
            // Mod Start 2019/01/31 QC#30097
//            if (!setSoldToInfo(bizMsg, false, nMZC610001PMsg)) {
            // Mod Start 2019/03/13 QC#30730
            //if (!setSoldToInfo(bizMsg, false, nMZC610001BSPMsg, ZYPConstant.FLG_ON_Y)) {
            if (!setSoldToInfo(bizMsg)) {
                // Mod End 2019/03/13 QC#30730
                return;
            }
            // Mod End 2019/01/31 QC#30097
            // 2017/08/15 S21_NA#20375 Mod Start

            // Add Start 2019/03/13 QC#30730
            setBillToInfoForShipTo(bizMsg, shipToCustCd, isSoldToSet);
            // Add End 2019/03/13 QC#30730

            if (ZYPCommonFunc.hasValue(bizMsg.billToCustAcctCd) || ZYPCommonFunc.hasValue(bizMsg.billToCustCd)) {
                NWAL1500CommonLogicForCategory.deriveDefaultPmtTerm(bizMsg);
            }

            if (!NWAL1500CommonLogic.deriveDefaultSlsRep(bizMsg)) {
                return;
            }
            // 2018/06/29 S21_NA#23726 del start
//            if (!NWAL1500CommonLogic.deriveDefaultCarrSvcLvl(bizMsg)) {
//                return;
//            }
            // 2018/06/29 S21_NA#23726 del end

            if (ZYPCommonFunc.hasValue(bizMsg.billToCustCd)) {
                deriveDefaultBillToInfoForDtlFld(bizMsg, glblMsg); // 2018/01/30 S21_NA#19808
            }

            if (ZYPCommonFunc.hasValue(bizMsg.shipToCustCd)) {
                deriveDefaultShipToInfoForDtlFld(bizMsg, glblMsg); // 2018/01/30 S21_NA#19808 Mod
            }

            // 2018/01/30 S21_NA#19808 Add Start
            if (ZYPCommonFunc.hasValue(bizMsg.soldToCustLocCd)) {
                deriveDefaultSoldToInfoForDtlFld(bizMsg, glblMsg);
            }
            // 2018/01/30 S21_NA#19808 Add End

            if (!NWAL1500CommonLogic.deriveDefaultPO(bizMsg)) {
                return;
            }

            if (ZYPCommonFunc.hasValue(bizMsg.dsOrdCatgCd) && ZYPCommonFunc.hasValue(bizMsg.shipToCustCd)) {
                if (!NWAL1500CommonLogic.deriveDefaultWh(bizMsg, glblMsg)) { // 2018/01/30 S21_NA#19808 Mod
                    return;
                }
            }

            // if (ZYPCommonFunc.hasValue(bizMsg.dsOrdCatgCd)) { // S21_NA#15889 Del
            if (ZYPCommonFunc.hasValue(bizMsg.dsOrdCatgCd) && ZYPConstant.FLG_ON_Y.equals(bizMsg.xxRsltFlg_SE.getValue())) { // S21_NA#15889 Add
                NWAL1500CommonLogic.deriveDefaultPrcList(bizMsg, glblMsg); // 2018/01/30 S21_NA#19808
            }

            // 2018/03/12 S21_NA#20297(Sol#379) Add Start
            if (!NWAL1500CommonLogic.deriveDefaultShippingInfo(bizMsg)) {
                return;
            }
            // 2018/03/12 S21_NA#20297(Sol#379) Add End

            // 2018/06/29 S21_NA#23726-2 add start
            if (!NWAL1500CommonLogic.deriveDefaultCarrSvcLvl(bizMsg)) {
                return;
            }
            // 2018/06/29 S21_NA#23726-2 add end

        // S21_NA#15889 Add Start
        } else if (TAB_LINE_CONFIG.equals(dplyTab)) {
            // Add Start 2019/03/13 QC#30730
            setBillToInfoForShipTo(bizMsg, shipToCustCd, isSoldToSet);
            // Add End 2019/03/13 QC#30730

            if (ZYPCommonFunc.hasValue(bizMsg.A.no(bizMsgCnt).shipToCustCd_LC)) { // 2018/01/30 S21_NA#19808 Mod -> 2018/09/20 S21_NA#28199 Mod "bizMsg.A.no(bizMsgCnt)"
                // 2018/09/20 S21_NA#28199 Add Start
                NWAL1500CommonLogicForPagination.copyConfigDataToSMsg(configMsg, glblMsg);
                // 2018/09/20 S21_NA#28199 Add End
                NWAL1500CommonLogic.deriveDefaultSlsRepForConfig(bizMsg, glblMsg, slctIndex, true); // 2018/01/30 S21_NA#19808 Mod
                NWAL1500CommonLogic.deriveDefaultWhForConfig(bizMsg, glblMsg, slctIndex, true); // 2018/01/30 S21_NA#19808 Mod
            }
        } else if (TAB_RMA.equals(dplyTab)) {
            // Add Start 2019/03/13 QC#30730
            setBillToInfoForShipTo(bizMsg, shipToCustCd, isSoldToSet);
            // Add End 2019/03/13 QC#30730

            if (ZYPCommonFunc.hasValue(bizMsg.C.no(bizMsgCnt).shipToCustCd_RC)) { // 2018/01/30 S21_NA#19808 Mod -> 2018/09/20 S21_NA#28199 Mod "bizMsg.C.no(bizMsgCnt)"
                // 2018/09/20 S21_NA#28199 Add Start
                NWAL1500CommonLogicForPagination.copyRmaConfigDataToSMsg(rmaConfigMsg, glblMsg);
                // 2018/09/20 S21_NA#28199 Add End
                NWAL1500CommonLogic.deriveDefaultSlsRepForConfig(bizMsg, glblMsg, slctIndex, false); // 2018/01/30 S21_NA#19808 Mod
                NWAL1500CommonLogic.deriveDefaultWhForConfig(bizMsg, glblMsg, slctIndex, false); // 2018/01/30 S21_NA#19808 Mod
            }
        }
        // S21_NA#15889 Add End
    }

    /**
     * Common Process For Derive From Sold To
     * @param bizMsg NWAL1500CMsg
     * @param glblMsg NWAL1500SMsg
     * @param isSetSoldToFlg Set Sold To Flag
     */
    public static void cmnProcForDeriveFromSoldTo(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, boolean isSetSoldToFlg) { // 2018/01/30 S21_NA#19808 Mod

        // 2016/09/28 S21_NA#8659 Mod Start
//        NMZC610001PMsg nMZC610001PMsg = callCustInfoGetApiForDefaultMode(bizMsg, bizMsg.sellToCustCd.getValue());
        // Mod Start 2019/01/31 QC#30097
        if (isSetSoldToFlg) {
//        NMZC610001PMsg nMZC610001PMsg = callCustInfoGetApiForDefaultMode(bizMsg, bizMsg.sellToCustCd.getValue(), null, bizMsg.soldToCustLocCd.getValue(), NWXC150001DefaultCustomer.MODE_BILL_TO_ACCT);
            NMZC610001PMsg nMZC610001PMsg = callCustInfoGetApiForDefaultMode(bizMsg, bizMsg.sellToCustCd.getValue(), null, bizMsg.soldToCustLocCd.getValue(), NWXC150001DefaultCustomer.MODE_BILL_TO_ACCT, ZYPConstant.FLG_OFF_N);
        // 2016/09/28 S21_NA#8659 Mod End
            if (nMZC610001PMsg == null) {
                return;
            }

            if (!setSoldToInfo(bizMsg, true, nMZC610001PMsg, ZYPConstant.FLG_OFF_N)) {
                return;
            }
        }
        // Mod End 2019/01/31 QC#30097

        // Add Start 2019/01/31 QC#30097
        NMZC610001PMsg nMZC610001BSPMsg = callCustInfoGetApiForDefaultMode(bizMsg, bizMsg.sellToCustCd.getValue(), null, bizMsg.soldToCustLocCd.getValue(), NWXC150001DefaultCustomer.MODE_BILL_TO_ACCT, ZYPConstant.FLG_ON_Y);
        if (nMZC610001BSPMsg == null) {
            return;
        }
        // Add End 2019/01/31 QC#30097

        // 2018/03/12 S21_NA#20297(Sol#379) Add Start
        String prevShipToCustCd = bizMsg.shipToCustCd.getValue();
        // 2018/03/12 S21_NA#20297(Sol#379) Add End

        // Mod Start 2019/02/01 QC#30097
//        String defBillToCustCd = nMZC610001PMsg.DefaultBillShipList.no(0).billToCustCd.getValue();
        String defBillToCustCd = nMZC610001BSPMsg.DefaultBillShipList.no(0).billToCustCd.getValue();
        // Mod End 2019/02/01 QC#30097

        setBillToInfo(bizMsg, defBillToCustCd);
        // Mod Start 2019/02/01 QC#30097
//        setShipToInfo(bizMsg, nMZC610001PMsg);
        setShipToInfo(bizMsg, nMZC610001BSPMsg);
        // Mod End 2019/02/01 QC#30097
        // 2017/07/13 S21_NA#19912 Add Start
        // Del Start 2019/01/31 QC#30097
//        boolean hasDefaultShipTo = nMZC610001PMsg.DefaultBillShipList.getValidCount() > 0;
//        if (hasDefaultShipTo) {
//            hasDefaultShipTo = ZYPCommonFunc.hasValue(nMZC610001PMsg.DefaultBillShipList.no(0).shipToCustCd);
//        }
//        if ((!ZYPCommonFunc.hasValue(bizMsg.shipToCustAcctNm) //
//                || !ZYPCommonFunc.hasValue(bizMsg.shipToCustAcctCd)) //
//                && hasDefaultShipTo) {
//            ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustAcctNm, bizMsg.soldToCustAcctNm);
//            ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustAcctCd, bizMsg.sellToCustCd);
//        }
        // Del End 2019/01/31 QC#30097
        // 2017/07/13 S21_NA#19912 Add End

        // Del Start 2019/01/31 QC#30097
//        if (isSetSoldToFlg && !setSoldToInfo(bizMsg, true, nMZC610001PMsg)) { // 2017/07/13 S21_NA#19912 Add 2nd and 3rd parameter
//            return;
//        }
        // Del End 2019/01/31 QC#30097

        if (ZYPCommonFunc.hasValue(bizMsg.billToCustAcctCd) || ZYPCommonFunc.hasValue(bizMsg.billToCustCd)) {
            NWAL1500CommonLogicForCategory.deriveDefaultPmtTerm(bizMsg);
        }

        if (!NWAL1500CommonLogic.deriveDefaultSlsRep(bizMsg)) {
            return;
        }
        // 2018/06/29 S21_NA#23726-2 del start
//        if (!NWAL1500CommonLogic.deriveDefaultCarrSvcLvl(bizMsg)) {
//            return;
//        }
        // 2018/06/29 S21_NA#23726-2 del end

        NWAL1500CommonLogic.deriveDefaultPO(bizMsg);

        if (ZYPCommonFunc.hasValue(bizMsg.billToCustCd)) {
            deriveDefaultBillToInfoForDtlFld(bizMsg, glblMsg); // 2018/01/30 S21_NA#19808 Mos
        }

        if (ZYPCommonFunc.hasValue(bizMsg.shipToCustCd)) {
            deriveDefaultShipToInfoForDtlFld(bizMsg, glblMsg); // 2018/01/30 S21_NA#19808 Mod
        }

        if (ZYPCommonFunc.hasValue(bizMsg.soldToCustLocCd)) {
            deriveDefaultSoldToInfoForDtlFld(bizMsg, glblMsg); // 2018/01/30 S21_NA#19808 Mod
        }

        if (ZYPCommonFunc.hasValue(bizMsg.dsOrdCatgCd) && ZYPCommonFunc.hasValue(bizMsg.shipToCustCd)) {
            if (!NWAL1500CommonLogic.deriveDefaultWh(bizMsg, glblMsg)) { // 2018/01/30 S21_NA#19808 Mod
                return;
            }
        }

        if (ZYPCommonFunc.hasValue(bizMsg.dsOrdCatgCd)) {
            NWAL1500CommonLogic.deriveDefaultPrcList(bizMsg, glblMsg); // 2018/01/30 S21_NA#19808
        }

        deriveDefaultCsmpContrInfo(bizMsg);

        // 2018/03/12 S21_NA#20297(Sol#379) Add Start
        if (!ZYPCommonFunc.hasValue(prevShipToCustCd) && ZYPCommonFunc.hasValue(bizMsg.shipToCustCd)) {
            if (!NWAL1500CommonLogic.deriveDefaultShippingInfo(bizMsg)) {
                return;
            }
        }
        // 2018/03/12 S21_NA#20297(Sol#379) Add End

        // 2018/06/29 S21_NA#23726-2 add start
        if (!NWAL1500CommonLogic.deriveDefaultCarrSvcLvl(bizMsg)) {
            return;
        }
        // 2018/06/29 S21_NA#23726-2 add end
    }

    /**
     * Get Bill To Info
     * @param bizMsg NWAL1500CMsg
     * @param custLocNum Customer Location Number
     * @param msgParm Message Parameter
     * @return Bill To Info
     */
    @SuppressWarnings("unchecked")
    public static Map<String, String> getBillToInfo(NWAL1500CMsg bizMsg, EZDCStringItem custLocNum, String msgParm) {

        S21SsmEZDResult ssmResult = NWAL1500QueryForCustomer.getInstance().getBillToCustInfoList(bizMsg, custLocNum.getValue());

        if (ssmResult.isCodeNotFound()) {
            custLocNum.setErrorInfo(1, NWAM0181E, new String[] {msgParm });
            return null;
        }

        List<Map<String, String>> billToCustInfoList = (List<Map<String, String>>) ssmResult.getResultObject();
        ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_OFF_N);

        if (billToCustInfoList.size() != 1) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_ON_Y);
            // 2018/07/27 S21_NA#14307 Add Start
            NWAL1500CommonLogicForCustomer.setAccountSearchPopupParam(bizMsg);
            // 2018/07/27 S21_NA#14307 Add End
            return null;
        }

        return billToCustInfoList.get(0);
    }

    /**
     * Get Ship To Info
     * @param bizMsg NWAL1500CMsg
     * @param custLocNum Customer Location Number
     * @return Ship To Info
     */
    @SuppressWarnings("unchecked")
    public static Map<String, String> getShipToInfo(NWAL1500CMsg bizMsg, EZDCStringItem custLocNum) {

        S21SsmEZDResult ssmResult = NWAL1500QueryForCustomer.getInstance().getShipToCustInfoList(bizMsg, custLocNum.getValue());

        if (ssmResult.isCodeNotFound()) {
            custLocNum.setErrorInfo(1, NWAM0181E, new String[] {MSG_PARAM_SHIP_TO_LOC });
            return null;
        }

        List<Map<String, String>> shipToCustInfoList = (List<Map<String, String>>) ssmResult.getResultObject();
        ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_OFF_N);

        if (shipToCustInfoList.size() != 1) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_ON_Y);
            // 2018/07/27 S21_NA#14307 Add Start
            NWAL1500CommonLogicForCustomer.setAccountSearchPopupParam(bizMsg);
            // 2018/07/27 S21_NA#14307 Add End
            return null;
        }

        return shipToCustInfoList.get(0);
    }

    // 2016/09/28 S21_NA#8659 Del Start
//    /**
//     * Call Customer Information Get API For Default Mode
//     * @param bizMsg NWAL1500CMsg
//     * @param dsAcctNum Direct Sales Account Number
//     * @return NMZC610001PMsg
//     */
//    private static NMZC610001PMsg callCustInfoGetApiForDefaultMode(NWAL1500CMsg bizMsg, String dsAcctNum) {
//
//        NMZC610001PMsg pMsg = new NMZC610001PMsg();
//        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, bizMsg.glblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NMZC610001Constant.PROCESS_DEFAULT_BILL_SHIP);
//        ZYPEZDItemValueSetter.setValue(pMsg.dsTrxRuleTpCd, getDsTrxRuleTpCd(bizMsg));
//        ZYPEZDItemValueSetter.setValue(pMsg.dsAcctNum_I1, dsAcctNum);
//
//        // call NMZC6100 Customer Information Get API
//        new NMZC610001().execute(pMsg, ONBATCH_TYPE.ONLINE);
//
//        if (S21ApiUtil.isXxMsgId(pMsg)) {
//            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
//            for (int i = 0; i < msgList.size(); i++) {
//                S21ApiMessage msg = msgList.get(i);
//                String msgId = msg.getXxMsgid();
//                String[] msgPrms = msg.getXxMsgPrmArray();
//                bizMsg.setMessageInfo(msgId, msgPrms);
//                return null;
//            }
//        }
//
//        return pMsg;
//    }
    // 2016/09/28 S21_NA#8659 Del End

    // 2016/09/28 S21_NA#8659 Add Start
    /**
     * <pre>
     * Call Customer Information Get API For Default Mode
     * 2016/09/28 S21_NA#8659 Change Interface
     * </pre>
     * @param bizMsg Business Message
     * @param dsAcctNum Direct Sales Account Number
     * @param shipToCustCd Ship to Customer Code (if you set bill to, this parameter should be null)
     * @param billToCustCd Bill To Customer Code (if you set ship to, this parameter should be null)
     * @param xxMode API calling mode
     * @param xxSelFlg select flag
     * @return NMZC610001PMsg Default Customer API return value
     */
    // Mod Start 2019/01/31 QC#30097
//    private static NMZC610001PMsg callCustInfoGetApiForDefaultMode(NWAL1500CMsg bizMsg, String dsAcctNum, String shipToCustCd, String billToCustCd, String xxMode) {
    private static NMZC610001PMsg callCustInfoGetApiForDefaultMode(NWAL1500CMsg bizMsg, String dsAcctNum, String shipToCustCd, String billToCustCd, String xxMode, String xxSelFlg) {
    // Mod End 2019/01/31 QC#30097

        NWXC150001DefaultCustomerBean paramBean = new NWXC150001DefaultCustomerBean();
        paramBean.setGlblCmpyCd(bizMsg.glblCmpyCd.getValue());
        paramBean.setDsTrxRuleTpCd(getDsTrxRuleTpCd(bizMsg));
        paramBean.setDsAcctNum(dsAcctNum);
        if (ZYPCommonFunc.hasValue(shipToCustCd)) {
            paramBean.setShipToCustCd(shipToCustCd);
        } else if (ZYPCommonFunc.hasValue(billToCustCd)) {
            // 2017/07/13 S21_NA#19912 Mod Start
//            paramBean.setBillToCustCd(billToCustCd);
            if (!S21StringUtil.isEquals(NWXC150001DefaultCustomer.MODE_SHIP_ACCT_TO_BILL_TO, xxMode)) {
                paramBean.setBillToCustCd(billToCustCd);
            }
            // 2017/07/13 S21_NA#19912 Mod End
        }
        paramBean.setXxMode(xxMode);
        // Add Start 2019/01/31 QC#30097
        paramBean.setXxSelFlg(xxSelFlg);
        // Add End 2019/01/31 QC#30097
        paramBean.setOnBatchType(ONBATCH_TYPE.ONLINE);

        NMZC610001PMsg pMsg = NWXC150001DefaultCustomer.getDefaultCustomerData(paramBean);

        if (S21ApiUtil.isXxMsgId(pMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
            for (int i = 0; i < msgList.size(); i++) {
                S21ApiMessage msg = msgList.get(i);
                String msgId = msg.getXxMsgid();
                String[] msgPrms = msg.getXxMsgPrmArray();
                bizMsg.setMessageInfo(msgId, msgPrms);
                return null;
            }
        }

        return pMsg;
    }
    // 2016/09/28 S21_NA#8659 Add End

    /**
     * Set Bill To Info
     * @param bizMsg NWAL1500CMsg
     * @param sellToCustTMsg SELL_TO_CUSTTMsg
     * @param nMZC610001PMsg NMZC610001PMsg
     */
    @SuppressWarnings("unchecked")
    private static void setBillToInfo(NWAL1500CMsg bizMsg, SELL_TO_CUSTTMsg sellToCustTMsg, NMZC610001PMsg nMZC610001PMsg) {

        String dplyTab = bizMsg.xxDplyTab.getValue();
        int slctIndex = bizMsg.xxCellIdx.getValueInt();

        String acctNum = sellToCustTMsg.sellToCustCd.getValue();
        String acctNm = sellToCustTMsg.dsAcctNm.getValue();

        if (TAB_LINE_CONFIG.equals(dplyTab)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(slctIndex).billToCustAcctCd_LC, acctNum);
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(slctIndex).billToCustAcctNm_LC, acctNm);
        } else if (TAB_RMA.equals(dplyTab)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(slctIndex).billToCustAcctCd_RC, acctNum);
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(slctIndex).billToCustAcctNm_RC, acctNm);
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.billToCustAcctCd, acctNum);
            ZYPEZDItemValueSetter.setValue(bizMsg.billToCustAcctNm, acctNm);
        }

        if (nMZC610001PMsg.DefaultBillShipList.getValidCount() == 0) {
            return;
        }

        String defBillToCustCd = nMZC610001PMsg.DefaultBillShipList.no(0).billToCustCd.getValue();
        S21SsmEZDResult ssmResult = NWAL1500QueryForCustomer.getInstance().getBillToCustAddr(bizMsg, defBillToCustCd);
        if (ssmResult.isCodeNotFound()) {
            return;
        }

        Map<String, String> billToAddrMap = (Map<String, String>) ssmResult.getResultObject();

        if (TAB_LINE_CONFIG.equals(dplyTab)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(slctIndex).billToCustCd_LC, defBillToCustCd);
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(slctIndex).billToCustLocAddr_LC, billToAddrMap.get("BILL_TO_ADDR"));
        } else if (TAB_RMA.equals(dplyTab)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(slctIndex).billToCustCd_RC, defBillToCustCd);
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(slctIndex).billToCustLocAddr_RC, billToAddrMap.get("BILL_TO_ADDR"));
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.billToCustCd, defBillToCustCd);
            ZYPEZDItemValueSetter.setValue(bizMsg.xxAllLineAddr_BT, billToAddrMap.get("BILL_TO_ADDR"));
            ZYPEZDItemValueSetter.setValue(bizMsg.entBillToCustLocAddr, billToAddrMap.get("ENT_BILL_TO_ADDR"));
        }
    }

    // Add Start 2019/03/13 QC#30730
    /**
     * setBillToInfoForShipTo
     * @param bizMsg NWAL1500CMsg
     * @param shipToCustCd String
     * @param isSoldToSet boolean
     */
    private static void setBillToInfoForShipTo(NWAL1500CMsg bizMsg, String shipToCustCd, boolean isSoldToSet) {
        String defBillToCustCd = null;

        defBillToCustCd = NWXC150001DefaultCustomer.getRelatedBillTo(bizMsg.glblCmpyCd.getValue(), shipToCustCd);
        if (!isSoldToSet && !ZYPCommonFunc.hasValue(defBillToCustCd)) {
            NMZC610001PMsg nMZC610001BSPMsg = callCustInfoGetApiForDefaultMode(bizMsg, //
                    bizMsg.sellToCustCd.getValue(), null, bizMsg.soldToCustLocCd.getValue(), //
                    NWXC150001DefaultCustomer.MODE_BILL_TO_ACCT, ZYPConstant.FLG_ON_Y);

            if (nMZC610001BSPMsg == null) {
                return;
            }

            defBillToCustCd = nMZC610001BSPMsg.DefaultBillShipList.no(0).billToCustCd.getValue();
        }

        setBillToInfo(bizMsg, defBillToCustCd);
    }
    // Add End 2019/03/13 QC#30730

    /**
     * Set Bill To Info
     * @param bizMsg NWAL1500CMsg
     * @param defBillToCustCd Default Bill To Customer Code
     */
    @SuppressWarnings("unchecked")
    private static void setBillToInfo(NWAL1500CMsg bizMsg, String defBillToCustCd) {

        if (!ZYPCommonFunc.hasValue(defBillToCustCd)) {
            return;
        }

        String dplyTab = bizMsg.xxDplyTab.getValue();
        int slctIndex = bizMsg.xxCellIdx.getValueInt();

        if (TAB_LINE_CONFIG.equals(dplyTab)) {
            if (ZYPCommonFunc.hasValue(bizMsg.A.no(slctIndex).billToCustAcctNm_LC) && ZYPCommonFunc.hasValue(bizMsg.A.no(slctIndex).billToCustAcctCd_LC)) {
                return;
            }
        } else if (TAB_RMA.equals(dplyTab)) {
            if (ZYPCommonFunc.hasValue(bizMsg.C.no(slctIndex).billToCustAcctNm_RC) && ZYPCommonFunc.hasValue(bizMsg.C.no(slctIndex).billToCustAcctCd_RC)) {
                return;
            }
        } else {
            if (ZYPCommonFunc.hasValue(bizMsg.billToCustAcctNm) && ZYPCommonFunc.hasValue(bizMsg.billToCustAcctCd)) {
                return;
            }
        }

        S21SsmEZDResult ssmResult = NWAL1500QueryForCustomer.getInstance().getBillToCustInfoList(bizMsg, defBillToCustCd);

        if (ssmResult.isCodeNotFound()) {
            return;
        }

        List<Map<String, String>> billToCustInfoList = (List<Map<String, String>>) ssmResult.getResultObject();
        Map<String, String> billToCustInfo = billToCustInfoList.get(0);

        if (TAB_LINE_CONFIG.equals(dplyTab)) {
            NWAL1500_ACMsg confMsg = bizMsg.A.no(slctIndex);
            ZYPEZDItemValueSetter.setValue(confMsg.billToCustAcctCd_LC, billToCustInfo.get("DS_ACCT_NUM"));
            ZYPEZDItemValueSetter.setValue(confMsg.billToCustAcctNm_LC, billToCustInfo.get("DS_ACCT_NM"));
            ZYPEZDItemValueSetter.setValue(confMsg.billToCustCd_LC, billToCustInfo.get("BILL_TO_CUST_CD"));
            ZYPEZDItemValueSetter.setValue(confMsg.billToCustLocAddr_LC, billToCustInfo.get("BILL_TO_ADDR"));
        } else if (TAB_RMA.equals(dplyTab)) {
            NWAL1500_CCMsg rmaConfMsg = bizMsg.C.no(slctIndex);
            ZYPEZDItemValueSetter.setValue(rmaConfMsg.billToCustAcctCd_RC, billToCustInfo.get("DS_ACCT_NUM"));
            ZYPEZDItemValueSetter.setValue(rmaConfMsg.billToCustAcctNm_RC, billToCustInfo.get("DS_ACCT_NM"));
            ZYPEZDItemValueSetter.setValue(rmaConfMsg.billToCustCd_RC, billToCustInfo.get("BILL_TO_CUST_CD"));
            ZYPEZDItemValueSetter.setValue(rmaConfMsg.billToCustLocAddr_RC, billToCustInfo.get("BILL_TO_ADDR"));
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.billToCustAcctCd, billToCustInfo.get("DS_ACCT_NUM"));
            ZYPEZDItemValueSetter.setValue(bizMsg.billToCustAcctNm, billToCustInfo.get("DS_ACCT_NM"));
            ZYPEZDItemValueSetter.setValue(bizMsg.billToCustCd, billToCustInfo.get("BILL_TO_CUST_CD"));
            ZYPEZDItemValueSetter.setValue(bizMsg.xxAllLineAddr_BT, billToCustInfo.get("BILL_TO_ADDR"));
            ZYPEZDItemValueSetter.setValue(bizMsg.entBillToCustLocAddr, billToCustInfo.get("ENT_BILL_TO_ADDR"));
        }
    }

    /**
     * Derive Default Bill To Information For Detail Fields
     * @param bizMsg NWAL1500CMsg
     */
    public static void deriveDefaultBillToInfoForDtlFld(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) { // 2018/01/30 S21_NA#19808

        // 2018/01/30 S21_NA#19808 Add Start
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            NWAL1500CommonLogicForPagination.copyConfigDataToSMsg(bizMsg.A.no(i), glblMsg);
        }
        for (int i = 0; i < bizMsg.C.getValidCount(); i++) {
            NWAL1500CommonLogicForPagination.copyRmaConfigDataToSMsg(bizMsg.C.no(i), glblMsg);
        }
        // 2018/01/30 S21_NA#19808 Add End
        // 2018/01/30 S21_NA#19808 bizMsg.A, C => glblMsg.A, C
        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            NWAL1500_ASMsg configLineMsg = glblMsg.A.no(i);
            ZYPEZDItemValueSetter.setValue(configLineMsg.billToCustCd_LC, bizMsg.billToCustCd);
            ZYPEZDItemValueSetter.setValue(configLineMsg.billToCustAcctCd_LC, bizMsg.billToCustAcctCd);
            ZYPEZDItemValueSetter.setValue(configLineMsg.billToCustAcctNm_LC, bizMsg.billToCustAcctNm);
            ZYPEZDItemValueSetter.setValue(configLineMsg.billToCustLocAddr_LC, bizMsg.xxAllLineAddr_BT);
        }

        for (int i = 0; i < glblMsg.C.getValidCount(); i++) {
            NWAL1500_CSMsg configLineMsg = glblMsg.C.no(i);
            ZYPEZDItemValueSetter.setValue(configLineMsg.billToCustCd_RC, bizMsg.billToCustCd);
            ZYPEZDItemValueSetter.setValue(configLineMsg.billToCustAcctCd_RC, bizMsg.billToCustAcctCd);
            ZYPEZDItemValueSetter.setValue(configLineMsg.billToCustAcctNm_RC, bizMsg.billToCustAcctNm);
            ZYPEZDItemValueSetter.setValue(configLineMsg.billToCustLocAddr_RC, bizMsg.xxAllLineAddr_BT);
        }
        // 2018/01/30 S21_NA#19808 Add Start
        NWAL1500CommonLogicForPagination.pagingLineConfig(bizMsg, glblMsg);
        NWAL1500CommonLogicForPagination.pagingRma(bizMsg, glblMsg);
        // 2018/01/30 S21_NA#19808 Add End
    }

    /**
     * Set Ship To Info
     * @param bizMsg NWAL1500CMsg
     * @param nMZC610001PMsg NMZC610001PMsg
     */
    @SuppressWarnings("unchecked")
    private static void setShipToInfo(NWAL1500CMsg bizMsg, NMZC610001PMsg nMZC610001PMsg) {

        if (nMZC610001PMsg.DefaultBillShipList.getValidCount() == 0) {
            return;
        }

        String dplyTab = bizMsg.xxDplyTab.getValue();
        int slctIndex = bizMsg.xxCellIdx.getValueInt();

        if (TAB_LINE_CONFIG.equals(dplyTab)) {
            if (ZYPCommonFunc.hasValue(bizMsg.A.no(slctIndex).shipToCustCd_LC)) {
                return;
            }
        } else if (TAB_RMA.equals(dplyTab)) {
            if (ZYPCommonFunc.hasValue(bizMsg.C.no(slctIndex).shipToCustCd_RC)) {
                return;
            }
        } else {
            if (ZYPCommonFunc.hasValue(bizMsg.shipToCustCd)) {
                return;
            }
        }

        String defShipToCustCd = nMZC610001PMsg.DefaultBillShipList.no(0).shipToCustCd.getValue();
        if (!ZYPCommonFunc.hasValue(defShipToCustCd)) {
            // 2017/07/13 S21_NA#19912 Add Start
            if (TAB_LINE_CONFIG.equals(dplyTab)) {
                bizMsg.A.no(slctIndex).shipToCustCd_LC.clear();
            } else if (TAB_RMA.equals(dplyTab)) {
                bizMsg.C.no(slctIndex).shipToCustCd_RC.clear();
            } else {
                bizMsg.shipToCustCd.clear();
            }
            // 2017/07/13 S21_NA#19912 Add End
            return;
        }

        S21SsmEZDResult ssmResult = NWAL1500QueryForCustomer.getInstance().getShipToCustAddr(bizMsg, defShipToCustCd);
        if (ssmResult.isCodeNotFound()) {
            return;
        }

        Map<String, String> shipToCustAddrInfo = (Map<String, String>) ssmResult.getResultObject();

        if (TAB_LINE_CONFIG.equals(dplyTab)) {
            NWAL1500_ACMsg confMsg = bizMsg.A.no(slctIndex);
            ZYPEZDItemValueSetter.setValue(confMsg.shipToCustCd_LC, shipToCustAddrInfo.get("SHIP_TO_CUST_CD"));
            ZYPEZDItemValueSetter.setValue(confMsg.shipToCustLocAddr_LC, shipToCustAddrInfo.get("SHIP_TO_ADDR"));
            ZYPEZDItemValueSetter.setValue(confMsg.shipToAddlLocNm_LC, shipToCustAddrInfo.get("ADDL_LOC_NM"));
            ZYPEZDItemValueSetter.setValue(confMsg.shipToFirstLineAddr_LC, shipToCustAddrInfo.get("FIRST_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(confMsg.shipToScdLineAddr_LC, shipToCustAddrInfo.get("SCD_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(confMsg.shipToThirdLineAddr_LC, shipToCustAddrInfo.get("THIRD_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(confMsg.shipToFrthLineAddr_LC, shipToCustAddrInfo.get("FRTH_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(confMsg.shipToFirstRefCmntTxt_LC, shipToCustAddrInfo.get("FIRST_REF_CMNT_TXT"));
            ZYPEZDItemValueSetter.setValue(confMsg.shipToScdRefCmntTxt_LC, shipToCustAddrInfo.get("SCD_REF_CMNT_TXT"));
            ZYPEZDItemValueSetter.setValue(confMsg.shipToCtyAddr_LC, shipToCustAddrInfo.get("CTY_ADDR"));
            ZYPEZDItemValueSetter.setValue(confMsg.shipToStCd_LC, shipToCustAddrInfo.get("ST_CD"));
            ZYPEZDItemValueSetter.setValue(confMsg.shipToPostCd_LC, shipToCustAddrInfo.get("POST_CD"));
            ZYPEZDItemValueSetter.setValue(confMsg.shipToProvNm_LC, shipToCustAddrInfo.get("PROV_NM"));
            ZYPEZDItemValueSetter.setValue(confMsg.shipToCtryCd_LC, shipToCustAddrInfo.get("CTRY_CD"));
            ZYPEZDItemValueSetter.setValue(confMsg.shipToCntyNm_LC, shipToCustAddrInfo.get("CNTY_NM"));
            ZYPEZDItemValueSetter.setValue(confMsg.dropShipFlg_LC, ZYPConstant.FLG_OFF_N);
            // 2017/12/08 S21_NA#21621 Add Start
            confMsg.addShipToLocNm_LC.clear();
            // 2017/12/08 S21_NA#21621 Add End
        } else if (TAB_RMA.equals(dplyTab)) {
            NWAL1500_CCMsg rmaConfMsg = bizMsg.C.no(slctIndex);
            ZYPEZDItemValueSetter.setValue(rmaConfMsg.shipToCustCd_RC, shipToCustAddrInfo.get("SHIP_TO_CUST_CD"));
            ZYPEZDItemValueSetter.setValue(rmaConfMsg.shipToCustLocAddr_RC, shipToCustAddrInfo.get("SHIP_TO_ADDR"));
            ZYPEZDItemValueSetter.setValue(rmaConfMsg.shipToAddlLocNm_RC, shipToCustAddrInfo.get("ADDL_LOC_NM"));
            ZYPEZDItemValueSetter.setValue(rmaConfMsg.shipToFirstLineAddr_RC, shipToCustAddrInfo.get("FIRST_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(rmaConfMsg.shipToScdLineAddr_RC, shipToCustAddrInfo.get("SCD_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(rmaConfMsg.shipToThirdLineAddr_RC, shipToCustAddrInfo.get("THIRD_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(rmaConfMsg.shipToFrthLineAddr_RC, shipToCustAddrInfo.get("FRTH_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(rmaConfMsg.shipToFirstRefCmntTxt_RC, shipToCustAddrInfo.get("FIRST_REF_CMNT_TXT"));
            ZYPEZDItemValueSetter.setValue(rmaConfMsg.shipToScdRefCmntTxt_RC, shipToCustAddrInfo.get("SCD_REF_CMNT_TXT"));
            ZYPEZDItemValueSetter.setValue(rmaConfMsg.shipToCtyAddr_RC, shipToCustAddrInfo.get("CTY_ADDR"));
            ZYPEZDItemValueSetter.setValue(rmaConfMsg.shipToStCd_RC, shipToCustAddrInfo.get("ST_CD"));
            ZYPEZDItemValueSetter.setValue(rmaConfMsg.shipToPostCd_RC, shipToCustAddrInfo.get("POST_CD"));
            ZYPEZDItemValueSetter.setValue(rmaConfMsg.shipToProvNm_RC, shipToCustAddrInfo.get("PROV_NM"));
            ZYPEZDItemValueSetter.setValue(rmaConfMsg.shipToCtryCd_RC, shipToCustAddrInfo.get("CTRY_CD"));
            ZYPEZDItemValueSetter.setValue(rmaConfMsg.shipToCntyNm_RC, shipToCustAddrInfo.get("CNTY_NM"));
            ZYPEZDItemValueSetter.setValue(rmaConfMsg.dropShipFlg_RC, ZYPConstant.FLG_OFF_N);
            // 2017/12/08 S21_NA#21621 Add Start
            rmaConfMsg.addShipToLocNm_RC.clear();
            // 2017/12/08 S21_NA#21621 Add End
        } else {
            // Add Start 2019/01/31 QC#30097
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustAcctCd, shipToCustAddrInfo.get("DS_ACCT_NUM"));
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustAcctNm, shipToCustAddrInfo.get("DS_ACCT_NM"));
            // Add End 2019/01/31 QC#30097
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustCd, shipToCustAddrInfo.get("SHIP_TO_CUST_CD"));
            ZYPEZDItemValueSetter.setValue(bizMsg.xxAllLineAddr_SH, shipToCustAddrInfo.get("SHIP_TO_ADDR"));
            ZYPEZDItemValueSetter.setValue(bizMsg.entShipToCustLocAddr, shipToCustAddrInfo.get("ENT_SHIP_TO_ADDR"));
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToAddlLocNm, shipToCustAddrInfo.get("ADDL_LOC_NM"));
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToFirstLineAddr, shipToCustAddrInfo.get("FIRST_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToScdLineAddr, shipToCustAddrInfo.get("SCD_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToThirdLineAddr, shipToCustAddrInfo.get("THIRD_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToFrthLineAddr, shipToCustAddrInfo.get("FRTH_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToFirstRefCmntTxt, shipToCustAddrInfo.get("FIRST_REF_CMNT_TXT"));
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToScdRefCmntTxt, shipToCustAddrInfo.get("SCD_REF_CMNT_TXT"));
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToCtyAddr, shipToCustAddrInfo.get("CTY_ADDR"));
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToStCd, shipToCustAddrInfo.get("ST_CD"));
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToPostCd, shipToCustAddrInfo.get("POST_CD"));
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToProvNm, shipToCustAddrInfo.get("PROV_NM"));
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToCtryCd, shipToCustAddrInfo.get("CTRY_CD"));
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToCntyNm, shipToCustAddrInfo.get("CNTY_NM"));
            ZYPEZDItemValueSetter.setValue(bizMsg.dropShipFlg, ZYPConstant.FLG_OFF_N);
            // 2017/12/07 S21_NA#21621 Add Start
            bizMsg.addShipToLocNm.clear();
            // 2017/12/07 S21_NA#21621 Add End
        }
    }

    /**
     * Derive Default Ship To Information For Detail Fields
     * @param bizMsg NWAL1500CMsg
     */
    public static void deriveDefaultShipToInfoForDtlFld(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) { // 2018/01/30 S21_NA#19808 Mod

        // 2018/01/30 S21_NA#19808 Add Start
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            NWAL1500CommonLogicForPagination.copyConfigDataToSMsg(bizMsg.A.no(i), glblMsg);
        }
        for (int i = 0; i < bizMsg.C.getValidCount(); i++) {
            NWAL1500CommonLogicForPagination.copyRmaConfigDataToSMsg(bizMsg.C.no(i), glblMsg);
        }
        // 2018/01/30 S21_NA#19808 Add End
        // 2018/01/30 S21_NA#19808 Mod bizMsg.A, C => glblMsg.A, C
        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            NWAL1500_ASMsg configLineMsg = glblMsg.A.no(i);
            ZYPEZDItemValueSetter.setValue(configLineMsg.shipToCustCd_LC, bizMsg.shipToCustCd);
            ZYPEZDItemValueSetter.setValue(configLineMsg.shipToCustAcctCd_LC, bizMsg.shipToCustAcctCd);
            ZYPEZDItemValueSetter.setValue(configLineMsg.shipToCustAcctNm_LC, bizMsg.shipToCustAcctNm);
            ZYPEZDItemValueSetter.setValue(configLineMsg.shipToCustLocAddr_LC, bizMsg.xxAllLineAddr_SH);
            ZYPEZDItemValueSetter.setValue(configLineMsg.shipToLocNm_LC, bizMsg.shipToLocNm);
            ZYPEZDItemValueSetter.setValue(configLineMsg.shipToAddlLocNm_LC, bizMsg.shipToAddlLocNm);
            ZYPEZDItemValueSetter.setValue(configLineMsg.shipToFirstLineAddr_LC, bizMsg.shipToFirstLineAddr);
            ZYPEZDItemValueSetter.setValue(configLineMsg.shipToScdLineAddr_LC, bizMsg.shipToScdLineAddr);
            ZYPEZDItemValueSetter.setValue(configLineMsg.shipToThirdLineAddr_LC, bizMsg.shipToThirdLineAddr);
            ZYPEZDItemValueSetter.setValue(configLineMsg.shipToFrthLineAddr_LC, bizMsg.shipToFrthLineAddr);
            ZYPEZDItemValueSetter.setValue(configLineMsg.shipToFirstRefCmntTxt_LC, bizMsg.shipToFirstRefCmntTxt);
            ZYPEZDItemValueSetter.setValue(configLineMsg.shipToScdRefCmntTxt_LC, bizMsg.shipToScdRefCmntTxt);
            ZYPEZDItemValueSetter.setValue(configLineMsg.shipToCtyAddr_LC, bizMsg.shipToCtyAddr);
            ZYPEZDItemValueSetter.setValue(configLineMsg.shipToStCd_LC, bizMsg.shipToStCd);
            ZYPEZDItemValueSetter.setValue(configLineMsg.shipToPostCd_LC, bizMsg.shipToPostCd);
            ZYPEZDItemValueSetter.setValue(configLineMsg.shipToProvNm_LC, bizMsg.shipToProvNm);
            ZYPEZDItemValueSetter.setValue(configLineMsg.shipToCtryCd_LC, bizMsg.shipToCtryCd);
            ZYPEZDItemValueSetter.setValue(configLineMsg.shipToCntyNm_LC, bizMsg.shipToCntyNm);
            ZYPEZDItemValueSetter.setValue(configLineMsg.dropShipFlg_LC, bizMsg.dropShipFlg);
            // 2017/12/08 S21_NA#21621 Add Start
            ZYPEZDItemValueSetter.setValue(configLineMsg.addShipToLocNm_LC, bizMsg.shipToLocNm);
            // 2017/12/08 S21_NA#21621 Add End
        }

        for (int i = 0; i < glblMsg.C.getValidCount(); i++) {
            NWAL1500_CSMsg configLineMsg = glblMsg.C.no(i);
            ZYPEZDItemValueSetter.setValue(configLineMsg.shipToCustCd_RC, bizMsg.shipToCustCd);
            ZYPEZDItemValueSetter.setValue(configLineMsg.shipToCustAcctCd_RC, bizMsg.shipToCustAcctCd);
            ZYPEZDItemValueSetter.setValue(configLineMsg.shipToCustAcctNm_RC, bizMsg.shipToCustAcctNm);
            ZYPEZDItemValueSetter.setValue(configLineMsg.shipToCustLocAddr_RC, bizMsg.xxAllLineAddr_SH);
            ZYPEZDItemValueSetter.setValue(configLineMsg.shipToLocNm_RC, bizMsg.shipToLocNm);
            ZYPEZDItemValueSetter.setValue(configLineMsg.shipToAddlLocNm_RC, bizMsg.shipToAddlLocNm);
            ZYPEZDItemValueSetter.setValue(configLineMsg.shipToFirstLineAddr_RC, bizMsg.shipToFirstLineAddr);
            ZYPEZDItemValueSetter.setValue(configLineMsg.shipToScdLineAddr_RC, bizMsg.shipToScdLineAddr);
            ZYPEZDItemValueSetter.setValue(configLineMsg.shipToThirdLineAddr_RC, bizMsg.shipToThirdLineAddr);
            ZYPEZDItemValueSetter.setValue(configLineMsg.shipToFrthLineAddr_RC, bizMsg.shipToFrthLineAddr);
            ZYPEZDItemValueSetter.setValue(configLineMsg.shipToFirstRefCmntTxt_RC, bizMsg.shipToFirstRefCmntTxt);
            ZYPEZDItemValueSetter.setValue(configLineMsg.shipToScdRefCmntTxt_RC, bizMsg.shipToScdRefCmntTxt);
            ZYPEZDItemValueSetter.setValue(configLineMsg.shipToCtyAddr_RC, bizMsg.shipToCtyAddr);
            ZYPEZDItemValueSetter.setValue(configLineMsg.shipToStCd_RC, bizMsg.shipToStCd);
            ZYPEZDItemValueSetter.setValue(configLineMsg.shipToPostCd_RC, bizMsg.shipToPostCd);
            ZYPEZDItemValueSetter.setValue(configLineMsg.shipToProvNm_RC, bizMsg.shipToProvNm);
            ZYPEZDItemValueSetter.setValue(configLineMsg.shipToCtryCd_RC, bizMsg.shipToCtryCd);
            ZYPEZDItemValueSetter.setValue(configLineMsg.shipToCntyNm_RC, bizMsg.shipToCntyNm);
            ZYPEZDItemValueSetter.setValue(configLineMsg.dropShipFlg_RC, bizMsg.dropShipFlg);
            // 2017/12/08 S21_NA#21621 Add Start
            ZYPEZDItemValueSetter.setValue(configLineMsg.addShipToLocNm_RC, bizMsg.shipToLocNm);
            // 2017/12/08 S21_NA#21621 Add End
        }

        // Clear Control Field Parameters
        // 2018/01/30 S21_NA#19808 Mod bizMsg.B => glblMsg.B
        for (int i = 0; i < glblMsg.B.getValidCount(); i++) {
            NWAL1500_BSMsg lineMsg = glblMsg.B.no(i);
            lineMsg.bllgAttrbCustAcctCd_LL.clear();
            lineMsg.firstBllgAttrbNm_LL.clear();
            lineMsg.firstBllgAttrbValTxt_LL.clear();
            lineMsg.scdBllgAttrbNm_LL.clear();
            lineMsg.scdBllgAttrbValTxt_LL.clear();
            lineMsg.thirdBllgAttrbNm_LL.clear();
            lineMsg.thirdBllgAttrbValTxt_LL.clear();
            lineMsg.frthBllgAttrbNm_LL.clear();
            lineMsg.frthBllgAttrbValTxt_LL.clear();
            lineMsg.fifthBllgAttrbNm_LL.clear();
            lineMsg.fifthBllgAttrbValTxt_LL.clear();
            lineMsg.sixthBllgAttrbNm_LL.clear();
            lineMsg.sixthBllgAttrbValTxt_LL.clear();
        }
        // 2018/01/30 S21_NA#19808 Add Start
        NWAL1500CommonLogicForPagination.pagingLineConfig(bizMsg, glblMsg);
        NWAL1500CommonLogicForPagination.pagingRma(bizMsg, glblMsg);
        // 2018/01/30 S21_NA#19808 Add End
    }

    // 2017/07/13 S21_NA#19912 Add Start
    private static boolean setSoldToInfo(NWAL1500CMsg bizMsg) {

        // Mod Start 2019/01/31 QC#30097
//        return setSoldToInfo(bizMsg, false, null);
        return setSoldToInfo(bizMsg, false, null, ZYPConstant.FLG_OFF_N);
        // Mod End 2019/01/31 QC#30097
    }
    // 2017/07/13 S21_NA#19912 Add End
    /**
     * Set Ship To Info
     * @param bizMsg NWAL1500CMsg
     * @param soldToMode true: sold to mode false: ship to mode
     * @param nMZC610001PMsg NMZC610001PMsg
     * @param xxSelFlg String
     * @return has API error : false
     */
    @SuppressWarnings("unchecked")
    // Mod Start 2019/01/31 QC#30097
//    private static boolean setSoldToInfo(NWAL1500CMsg bizMsg, boolean soldToMode, NMZC610001PMsg nMZC610001PMsg) { // 2017/07/13 S21_NA#19912 change interface
    private static boolean setSoldToInfo(NWAL1500CMsg bizMsg, boolean soldToMode, NMZC610001PMsg nMZC610001PMsg, String xxSelFlg) {
    // Mod End 2019/01/31 QC#30097

        bizMsg.xxRsltFlg_SE.clear(); // S21_NA#15889 Add
        if (!soldToMode) { // 2017/07/13 S21_NA#19912 Add Condition
            if (ZYPCommonFunc.hasValue(bizMsg.soldToCustAcctNm) && ZYPCommonFunc.hasValue(bizMsg.sellToCustCd)) {
                return true;
            } else if (!ZYPCommonFunc.hasValue(bizMsg.shipToCustAcctCd)) {
                return true;
            }
        } // 2017/07/13 S21_NA#19912 Add Condition

        if (!soldToMode) { // 2017/07/13 S21_NA#19912 Add Condition
            ZYPEZDItemValueSetter.setValue(bizMsg.sellToCustCd, bizMsg.shipToCustAcctCd);
            ZYPEZDItemValueSetter.setValue(bizMsg.soldToCustAcctNm, bizMsg.shipToCustAcctNm);
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRsltFlg_SE, ZYPConstant.FLG_ON_Y); // S21_NA#15889 Add
        } // 2017/07/13 S21_NA#19912 Add Condition
        deriveDefaultCsmpContrInfo(bizMsg);

        // 2017/07/13 S21_NA#19912 Mod Start
        // 2016/09/28 S21_NA#8659 Mod Start
//        NMZC610001PMsg nMZC610001PMsg = callCustInfoGetApiForDefaultMode(bizMsg, bizMsg.sellToCustCd.getValue());
//        NMZC610001PMsg nMZC610001PMsg = callCustInfoGetApiForDefaultMode(bizMsg, bizMsg.sellToCustCd.getValue(), null, bizMsg.soldToCustLocCd.getValue(), NWXC150001DefaultCustomer.MODE_BILL_TO_ACCT);
        // 2016/09/28 S21_NA#8659 Mod End
        if (nMZC610001PMsg == null) {
            // Mod Start 2019/01/31 QC#30097
//            nMZC610001PMsg = callCustInfoGetApiForDefaultMode(bizMsg, bizMsg.sellToCustCd.getValue(), null, bizMsg.soldToCustLocCd.getValue(), NWXC150001DefaultCustomer.MODE_BILL_TO_ACCT);
            nMZC610001PMsg = callCustInfoGetApiForDefaultMode(bizMsg, bizMsg.sellToCustCd.getValue(), null, bizMsg.soldToCustLocCd.getValue(), NWXC150001DefaultCustomer.MODE_BILL_TO_ACCT, xxSelFlg);
            // Mod End 2019/01/31 QC#30097
        }
        // 2017/07/13 S21_NA#19912 Mod End
        if (nMZC610001PMsg == null) {
            return false;
        }

        if (nMZC610001PMsg.DefaultBillShipList.getValidCount() == 0) {
            return true;
        }

        String defBillToCustCd = nMZC610001PMsg.DefaultBillShipList.no(0).billToCustCd.getValue();
        // 2017/08/15 S21_NA#20375 Mod Start
//        S21SsmEZDResult ssmResult = NWAL1500QueryForCustomer.getInstance().getBillToCustAddr(bizMsg, defBillToCustCd);
        S21SsmEZDResult ssmResult = NWAL1500QueryForCustomer.getInstance().getBillToCustInfoList(bizMsg, defBillToCustCd);
        // 2017/08/15 S21_NA#20375 Mod End
        if (ssmResult.isCodeNotFound()) {
            return true;
        }

        // 2017/08/15 S21_NA#20375 Mod Start
//        Map<String, String> billToAddrMap = (Map<String, String>) ssmResult.getResultObject();
        List<Map<String, String>> billToCustInfoList = (List<Map<String, String>>) ssmResult.getResultObject();
        Map<String, String> billToAddrMap = billToCustInfoList.get(0);
        // 2017/08/15 S21_NA#20375 Mod End
        ZYPEZDItemValueSetter.setValue(bizMsg.soldToCustLocCd, defBillToCustCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxAllLineAddr_SE, billToAddrMap.get("BILL_TO_ADDR"));
        ZYPEZDItemValueSetter.setValue(bizMsg.entSoldToCustLocAddr, billToAddrMap.get("ENT_BILL_TO_ADDR"));

        // 2017/08/15 S21_NA#20375 Add Start
        // Del Start 2019/03/13 QC#30730
        //String sellToCustCd = billToAddrMap.get("DS_ACCT_NUM");
        //String soldToCustAcctNm = billToAddrMap.get("DS_ACCT_NM");
        //if (!S21StringUtil.isEquals(bizMsg.sellToCustCd.getValue(), sellToCustCd)) {
        //    ZYPEZDItemValueSetter.setValue(bizMsg.sellToCustCd, sellToCustCd);
        //    ZYPEZDItemValueSetter.setValue(bizMsg.soldToCustAcctNm, soldToCustAcctNm);
        //}
        // Del End 2019/03/13 QC#30730
        // 2017/08/15 S21_NA#20375 Add End

        return true;
    }

    /**
     * Derive Default Sold To Information For Detail Fields
     * @param bizMsg NWAL1500CMsg
     */
    private static void deriveDefaultSoldToInfoForDtlFld(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) { // 2018/01/30 S21_NA#19808 Mod

        // 2018/01/30 S21_NA#19808 Add Start
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            NWAL1500CommonLogicForPagination.copyConfigDataToSMsg(bizMsg.A.no(i), glblMsg);
        }
        for (int i = 0; i < bizMsg.C.getValidCount(); i++) {
            NWAL1500CommonLogicForPagination.copyRmaConfigDataToSMsg(bizMsg.C.no(i), glblMsg);
        }
        // 2018/01/30 S21_NA#19808 Add End
        // 2018/01/30 S21_NA#19808 Mod bizMsg.A, C => glblMsg.A, C
        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            NWAL1500_ASMsg configLineMsg = glblMsg.A.no(i);
            ZYPEZDItemValueSetter.setValue(configLineMsg.soldToCustLocCd_LC, bizMsg.soldToCustLocCd);
            ZYPEZDItemValueSetter.setValue(configLineMsg.sellToCustCd_LC, bizMsg.sellToCustCd);
            ZYPEZDItemValueSetter.setValue(configLineMsg.soldToCustAcctNm_LC, bizMsg.soldToCustAcctNm);
            ZYPEZDItemValueSetter.setValue(configLineMsg.soldToCustLocAddr_LC, bizMsg.xxAllLineAddr_SE);
        }

        for (int i = 0; i < glblMsg.C.getValidCount(); i++) {
            NWAL1500_CSMsg configLineMsg = glblMsg.C.no(i);
            ZYPEZDItemValueSetter.setValue(configLineMsg.soldToCustLocCd_RC, bizMsg.soldToCustLocCd);
            ZYPEZDItemValueSetter.setValue(configLineMsg.sellToCustCd_RC, bizMsg.sellToCustCd);
            ZYPEZDItemValueSetter.setValue(configLineMsg.soldToCustAcctNm_RC, bizMsg.soldToCustAcctNm);
            ZYPEZDItemValueSetter.setValue(configLineMsg.soldToCustLocAddr_RC, bizMsg.xxAllLineAddr_SE);
        }
        // 2018/01/30 S21_NA#19808 Add Start
        NWAL1500CommonLogicForPagination.pagingLineConfig(bizMsg, glblMsg);
        NWAL1500CommonLogicForPagination.pagingRma(bizMsg, glblMsg);
        // 2018/01/30 S21_NA#19808 Add End
    }

    /**
     * Combine Customer Address
     * @param bizMsg NWAL1500CMsg
     * @param connectStr String
     * @return Customer Address
     */
    public static String cmbnAddr(NWAL1500CMsg bizMsg, String connectStr) {

        if (!ZYPCommonFunc.hasValue(bizMsg.firstLineAddr)) {
            return null;
        }
        
        // 2018/01/26 S21_NA#23140 Mod Start
        StringBuilder addr = new StringBuilder("");
        if (S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, bizMsg.dropShipFlg.getValue())) {
            if (ZYPCommonFunc.hasValue(bizMsg.shipToAddlLocNm)) {
                addr.append(bizMsg.shipToAddlLocNm.getValue());
                addr.append(connectStr);
            }
        }
        addr.append(bizMsg.firstLineAddr.getValue());
        //StringBuilder addr = new StringBuilder(bizMsg.firstLineAddr.getValue());
        // 2018/01/26 S21_NA#23140 Mod End

        if (ZYPCommonFunc.hasValue(bizMsg.scdLineAddr)) {
            addr.append(connectStr);
            addr.append(bizMsg.scdLineAddr.getValue());
        }
        if (ZYPCommonFunc.hasValue(bizMsg.thirdLineAddr)) {
            addr.append(connectStr);
            addr.append(bizMsg.thirdLineAddr.getValue());
        }
        if (ZYPCommonFunc.hasValue(bizMsg.frthLineAddr)) {
            addr.append(connectStr);
            addr.append(bizMsg.frthLineAddr.getValue());
        }
        if (ZYPCommonFunc.hasValue(bizMsg.ctyAddr)) {
            addr.append(connectStr);
            addr.append(bizMsg.ctyAddr.getValue());
        }
        if (ZYPCommonFunc.hasValue(bizMsg.stCd)) {
            addr.append(COMMA);
            addr.append(bizMsg.stCd.getValue());
        }
        if (ZYPCommonFunc.hasValue(bizMsg.postCd)) {
            addr.append(SPACE);
            addr.append(bizMsg.postCd.getValue());
        }

        return addr.toString();
    }

    /**
     * Get Direct Sales Account Customer For Name
     * @param bizMsg NWAL1500CMsg
     * @param custNm Customer Name
     * @return Direct Sales Account Customer
     */
    private static SELL_TO_CUSTTMsgArray getDsSlsAcctCustForName(NWAL1500CMsg bizMsg, String custNm) {

        final SELL_TO_CUSTTMsg condition = new SELL_TO_CUSTTMsg();
        // 2018/03/12 S21_NA#24090 Mod Start
        // condition.setSQLID("502");
        condition.setSQLID("506");
        // 2018/03/12 S21_NA#24090 Mod Start
        condition.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        condition.setConditionValue("dsAcctNm01", custNm);
        condition.setConditionValue("rgtnStsCd01", RGTN_STS.READY_FOR_ORDER_TAKING);

        return (SELL_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(condition);
    }

    /**
     * Get Direct Sales Account Customer For Account
     * @param bizMsg NWAL1500CMsg
     * @param acctNum Account Number
     * @return Direct Sales Account Customer
     */
    private static SELL_TO_CUSTTMsgArray getDsSlsAcctCustForAcct(NWAL1500CMsg bizMsg, String acctNum) {

        final SELL_TO_CUSTTMsg condition = new SELL_TO_CUSTTMsg();
        // 2018/03/12 S21_NA#24090 Mod Start
        // condition.setSQLID("503");
        condition.setSQLID("507");
        // 2018/03/12 S21_NA#24090 Mod Start
        condition.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        condition.setConditionValue("sellToCustCd01", acctNum);
        condition.setConditionValue("rgtnStsCd01", RGTN_STS.READY_FOR_ORDER_TAKING);

        return (SELL_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(condition);
    }

    /**
     * Get Direct Sales Transaction Rule Type Code
     * @param bizMsg NWAL1500CMsg
     * @return Direct Sales Transaction Rule Type Code
     */
    private static String getDsTrxRuleTpCd(NWAL1500CMsg bizMsg) {

        ORD_CATG_BIZ_CTXTMsg condition = new ORD_CATG_BIZ_CTXTMsg();
        condition.setSQLID("003");
        condition.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        condition.setConditionValue("ordCatgCtxTpCd01A", ORD_CATG_CTX_TP.EQUIPMENT_ORDER_VALUE_SET);
        condition.setConditionValue("ordCatgCtxTpCd01B", ORD_CATG_CTX_TP.SUPPLIES_ORDER_VALUE_SET);
        condition.setConditionValue("dsOrdCatgCd01", bizMsg.dsOrdCatgCd.getValue());
        condition.setConditionValue("dsOrdTpCd01", bizMsg.dsOrdTpCd.getValue());

        ORD_CATG_BIZ_CTXTMsgArray tmsgArray = (ORD_CATG_BIZ_CTXTMsgArray) EZDTBLAccessor.findByCondition(condition);
        if (tmsgArray.getValidCount() > 0) {
            return tmsgArray.no(0).firstBizCtxAttrbTxt.getValue();
        }

        condition = new ORD_CATG_BIZ_CTXTMsg();
        condition.setSQLID("002");
        condition.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        condition.setConditionValue("ordCatgCtxTpCd01A", ORD_CATG_CTX_TP.EQUIPMENT_ORDER_VALUE_SET);
        condition.setConditionValue("ordCatgCtxTpCd01B", ORD_CATG_CTX_TP.SUPPLIES_ORDER_VALUE_SET);
        condition.setConditionValue("dsOrdCatgCd01", bizMsg.dsOrdCatgCd.getValue());

        tmsgArray = (ORD_CATG_BIZ_CTXTMsgArray) EZDTBLAccessor.findByCondition(condition);
        if (tmsgArray.getValidCount() > 0) {
            return tmsgArray.no(0).firstBizCtxAttrbTxt.getValue();
        }

        return BLANK;
    }

    /**
     * Check Exist Customer
     * @param bizMsg NWAL1500CMsg
     */
    public static void checkExistCustomer(NWAL1500CMsg bizMsg) {

        int selectIdx = bizMsg.xxCellIdx.getValueInt();
        String shipToCustCd = null;

        if (NO_SLCT_DTL == selectIdx) {
            shipToCustCd = bizMsg.shipToCustCd.getValue();
        } else {
            if (TAB_LINE_CONFIG.equals(bizMsg.xxDplyTab.getValue())) {
                shipToCustCd = bizMsg.A.no(selectIdx).shipToCustCd_LC.getValue();
            } else {
                shipToCustCd = bizMsg.C.no(selectIdx).shipToCustCd_RC.getValue();
            }
        }

        // 2016/03/25 S21_NA#4693 Mod Start
//        SHIP_TO_CUSTTMsg condition = new SHIP_TO_CUSTTMsg();
//        condition.setSQLID("002");
//        condition.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
//        condition.setConditionValue("shipToCustCd01", shipToCustCd);
//        condition.setConditionValue("rgtnStsCd01", RGTN_STS.READY_FOR_ORDER_TAKING);
//
//        SHIP_TO_CUSTTMsgArray tmsgArray = (SHIP_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(condition);
//
//        if (tmsgArray.getValidCount() > 0) {
//            return;
//        }
        SHIP_TO_CUSTTMsg shipToCust = getShipToCustByCondition(bizMsg.glblCmpyCd.getValue(), shipToCustCd);
        if (null != shipToCust) {
            return;
        }
        // 2016/03/25 S21_NA#4693 Mod End

        if (NO_SLCT_DTL == selectIdx) {
            bizMsg.shipToCustCd.setErrorInfo(1, NWAM0181E, new String[] {MSG_PARAM_SHIP_TO_LOC });
        } else {
            if (TAB_LINE_CONFIG.equals(bizMsg.xxDplyTab.getValue())) {
                bizMsg.A.no(selectIdx).shipToCustCd_LC.setErrorInfo(1, NWAM0181E, new String[] {MSG_PARAM_SHIP_TO_LOC });
            } else {
                bizMsg.C.no(selectIdx).shipToCustCd_RC.setErrorInfo(1, NWAM0181E, new String[] {MSG_PARAM_SHIP_TO_LOC });
            }
        }
    }

    /**
     * Derive Default CSMP Contract Information
     * @param bizMsg NWAL1500CMsg
     */
    @SuppressWarnings("unchecked")
    private static void deriveDefaultCsmpContrInfo(NWAL1500CMsg bizMsg) {

        S21SsmEZDResult ssmResult = NWAL1500QueryForAddlHeader.getInstance().getCsmpContrInfoList(bizMsg, false, true);

        if (ssmResult.isCodeNotFound()) {
            return;
        }

        bizMsg.csmpContrNum.clear();
        bizMsg.dlrRefNum.clear();

        List<Map<String, String>> csmpContrInfoList = (List<Map<String, String>>) ssmResult.getResultObject();
        // 2017/07/21 S21_NA#19847 Add Start
        if (csmpContrInfoList.size() != 1) {
            return;
        }
        // 2017/07/21 S21_NA#19847 Add End
        ZYPEZDItemValueSetter.setValue(bizMsg.csmpContrNum, csmpContrInfoList.get(0).get("CSMP_NUM"));
        ZYPEZDItemValueSetter.setValue(bizMsg.dlrRefNum, csmpContrInfoList.get(0).get("DLR_REF_NUM"));

        // 2017/07/21 S21_NA#19847 Add Start
        deriveDefaultAssPg(bizMsg);
        // 2017/07/21 S21_NA#19847 Add End
    }

    /**
     * <pre>
     * get Ship To Customer
     * @param glblCmpyCd Global Company Code
     * @param shipToCustCd Ship To Customer Code
     * @return SHIP_TO_CUSTTMsg, if system can't find SHIP_TO_CUST record, return null
     * </pre>
     */
    public static SHIP_TO_CUSTTMsg getShipToCustByCondition(String glblCmpyCd, String shipToCustCd) {

        SHIP_TO_CUSTTMsg condition = new SHIP_TO_CUSTTMsg();
        condition.setSQLID("002");
        condition.setConditionValue("glblCmpyCd01", glblCmpyCd);
        condition.setConditionValue("shipToCustCd01", shipToCustCd);
        condition.setConditionValue("rgtnStsCd01", RGTN_STS.READY_FOR_ORDER_TAKING);

        SHIP_TO_CUSTTMsgArray tmsgArray = (SHIP_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(condition);

        if (tmsgArray.getValidCount() > 0) {
            return tmsgArray.no(0);
        } else {
            return null;
        }
    }

    // 2017/07/21 S21_NA#19847 Add Start
    /**
     * Derive Association Program Name and Number
     * @param bizMsg NWAL1500 Business Message (already set up CSMP Number or Dealer Ref#)
     */
    public static void deriveDefaultAssPg(NWAL1500CMsg bizMsg) {

        bizMsg.prcContrNm.clear();
        bizMsg.prcContrNum.clear();
        S21SsmEZDResult ssmResult = NWAL1500QueryForAddlHeader.getInstance().getPrcContrInfoListByPrcContr(bizMsg);

        if (ssmResult.isCodeNotFound()) {
            return;
        }

        List<Map<String, String>> prcContrInfoList = (List<Map<String, String>>) ssmResult.getResultObject();

        ZYPEZDItemValueSetter.setValue(bizMsg.prcContrNm, prcContrInfoList.get(0).get("PRC_CONTR_NM"));
        ZYPEZDItemValueSetter.setValue(bizMsg.prcContrNum, prcContrInfoList.get(0).get("PRC_CONTR_NUM"));
    }
    // 2017/07/21 S21_NA#19847 Add End

    // 2018/07/27 S21_NA#14307 Add Start
    /**
     * Set Account Search Popup Parameter
     * @param bizMsg NWAL1500CMsg
     */
    public static void setAccountSearchPopupParam(NWAL1500CMsg bizMsg) {

        // Transaction Type
        String trxRuleTp = NWAL1500CommonLogicForSpecialInstruction.getTrxRuleTp(bizMsg);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxPopPrm_P9, trxRuleTp);

        // Function Category ID
        String funcCatgId = NWAL1500Query.getInstance().getOrdCatgBizCtxFstAttbTxt(bizMsg.glblCmpyCd.getValue(), ORD_CATG_CTX_TP.CUST_SPCL_ORDER_CATEGORY, bizMsg.dsOrdCatgCd.getValue(), bizMsg.dsOrdTpCd.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.xxPopPrm_PC, funcCatgId);
    }
    // 2018/07/27 S21_NA#14307 Add End
    
}
