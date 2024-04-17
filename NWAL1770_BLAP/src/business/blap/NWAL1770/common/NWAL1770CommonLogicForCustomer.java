/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1770.common;

import static business.blap.NWAL1770.constant.NWAL1770Constant.BIZ_ID;
import static business.blap.NWAL1770.constant.NWAL1770Constant.BLANK;
import static business.blap.NWAL1770.constant.NWAL1770Constant.BLLG_ATTRB_NM;
import static business.blap.NWAL1770.constant.NWAL1770Constant.BLLG_ATTRB_VAL_TXT;
import static business.blap.NWAL1770.constant.NWAL1770Constant.COMMA;
import static business.blap.NWAL1770.constant.NWAL1770Constant.EQUIPMENT_ORDER_VALUE_SET;
import static business.blap.NWAL1770.constant.NWAL1770Constant.KEY_CR_APVL_WF_ID;
import static business.blap.NWAL1770.constant.NWAL1770Constant.SPACE;
import static business.blap.NWAL1770.constant.NWAL1770Constant.SUPPLIES_ORDER_VALUE_SET;
import static business.blap.NWAL1770.constant.NWAL1770Constant.WF_ACTION_NM_APPROVE;
import static business.blap.NWAL1770.constant.NWAL1770MsgConstant.MSG_PARAM_SHIP_TO_LOC;
import static business.blap.NWAL1770.constant.NWAL1770MsgConstant.NWAM0181E;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import parts.common.EZDCStringItem;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NWAL1770.NWAL1770CMsg;
import business.blap.NWAL1770.NWAL1770QueryForCustomer;
import business.db.ORD_CATG_BIZ_CTXTMsg;
import business.db.ORD_CATG_BIZ_CTXTMsgArray;
import business.db.SELL_TO_CUSTTMsg;
import business.db.SELL_TO_CUSTTMsgArray;
import business.db.SHIP_TO_CUSTTMsg;
import business.db.SHIP_TO_CUSTTMsgArray;
import business.parts.NMZC610001PMsg;
import business.parts.NMZC610001_EligibleCheckListPMsg;

import com.canon.cusa.s21.api.NMZ.NMZC610001.NMZC610001;
import com.canon.cusa.s21.api.NMZ.NMZC610001.constant.NMZC610001Constant;
import com.canon.cusa.s21.common.NWX.NWXC150001.NWXC150001DefaultCustomer;
import com.canon.cusa.s21.common.NWX.NWXC150001.NWXC150001DefaultCustomerBean;
import com.canon.cusa.s21.common.NWX.NWXC150001.NWXC150001DsCheck;
import com.canon.cusa.s21.common.NYX.NYXC880002.NYXC880002Query;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_CATG_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WF_PROC_COND_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.nwf.core.common.S21NwfContext;
import com.canon.cusa.s21.framework.nwf.core.common.S21NwfContextFactory;
import com.canon.cusa.s21.framework.nwf.core.history.impl.S21NwfHistUserAction.EVENT;
import com.canon.cusa.s21.framework.nwf.core.model.S21NwfWorkItem;
import com.canon.cusa.s21.framework.nwf.core.process.S21NwfProcess;
import com.canon.cusa.s21.framework.nwf.util.bizapi.S21NwfUtilBizHistWorkItem;
import com.canon.cusa.s21.framework.nwf.util.bizapi.S21NwfUtilBizProcess;
import com.canon.cusa.s21.framework.nwf.util.bizapi.S21NwfUtilBizWorkItem;
import com.canon.cusa.s21.framework.nwf.util.common.S21NwfUtilContextFactory;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/12   Fujitsu         T.Yoshida       Create          N/A
 * 2016/08/26   Fujitsu         T.Murai         Update          S21_NA#13243
 * 2016/09/29   Fujitsu         H.Ikeda         Update          S21_NA#8659
 * 2016/12/16   Fujitsu         T.Yoshida       Update          S21_NA#16410
 * 2017/06/22   Hitachi         K.Kasai         Update          S21_NA#19134
 * 2017/07/27   Fujitsu         S.Takami        Update          S21_NA#18243
 * 2017/11/27   Fujitsu         M.Ohno          Update          S21_NA#21155 
 * 2018/02/09   Fujitsu         A.Kosai         Update          S21_NA#20297(Sol#379)
 * 2018/03/02   Fujitsu         A.Kosai         Update          S21_NA#20297-1(Sol#379)
 * 2018/03/07   Fujitsu         A.Kosai         Update          S21_NA#22387
 * 2018/03/12   Fujitsu         K.Ishizuka      Update          S21_NA#24090
 * 2018/06/21   Fujitsu         T.Noguchi       Update          S21_NA#14307
 * 2018/06/29   Fujitsu         H.Nagashima     Update          S21_NA#23726
 * 2018/07/10   Fujitsu         T.Noguchi       Update          S21_NA#26661,26713
 * 2018/08/02   Fujitsu         T.Noguchi       Update          S21_NA#14307
 * 2018/11/20   Fujitsu         Hd.Sugawara     Update          S21_NA#28683
 * 2019/01/29   Fujitsu         S.Kosaka        Update          QC#30036
 * 2019/01/31   Fujitsu         Hd.Sugawara     Update          S21_NA#30097
 * 2019/02/15   Fujitsu         K.Kato          Update          QC#30308
 * 2019/02/25   Fujitsu         K.Kato          Update          QC#30315
 * 2019/03/12   Fujitsu         Hd.Sugawara     Update          QC#30730
 * 2023/07/20   Hitachi         T.Fukuta        Update          CSA-QC#61467
 * </pre>
 */
public class NWAL1770CommonLogicForCustomer {

    /**
     * Get DS Accont Customer Info
     * @param bizMsg NWAL1770CMsg
     * @param isCallNameFld Called Name Field
     * @param targetItem Target Item
     * @param msgParm Message Parameter
     * @return DS Accont Customer Info
     */
    public static SELL_TO_CUSTTMsg getDsAcctCustInfo(NWAL1770CMsg bizMsg, boolean isCallNameFld, EZDCStringItem targetItem, String msgParm) {

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
            // Add Start 2018/11/20 QC#28683
            setParamForNMAL6760SpecialInstruction(bizMsg);
            // Add End 2018/11/20 QC#28683
            return null;
        }

        return sellToCustTMsgArray.no(0);
    }

    /**
     * Common Process For Derive From Bill To
     * @param bizMsg NWAL1770CMsg
     * @param sellToCustTMsg SELL_TO_CUSTTMsg
     */
    public static void cmnProcForDeriveFromBillTo(NWAL1770CMsg bizMsg, SELL_TO_CUSTTMsg sellToCustTMsg) {
        
        // Mod Start 2019/01/31 QC#30097
        //NMZC610001PMsg nMZC610001PMsg = callCustInfoGetApiForDefaultMode(bizMsg, sellToCustTMsg.sellToCustCd.getValue(), null, bizMsg.billToCustCd.getValue(), NWXC150001DefaultCustomer.MODE_BILL_TO_ACCT);
        NMZC610001PMsg nMZC610001PMsg = callCustInfoGetApiForDefaultMode(bizMsg, //
                sellToCustTMsg.sellToCustCd.getValue(), null, bizMsg.billToCustCd.getValue(), //
                NWXC150001DefaultCustomer.MODE_BILL_TO_ACCT, ZYPConstant.FLG_OFF_N);
        // Mod End 2019/01/31 QC#30097
        if (nMZC610001PMsg == null) {
            return;
        }

        setBillToInfo(bizMsg, sellToCustTMsg, nMZC610001PMsg);
        NWAL1770CommonLogic.deriveDefaultPmtTerm(bizMsg, bizMsg.billToCustCd.getValue());
    }

    /**
     * Common Process For Derive From Ship To
     * @param bizMsg NWAL1770CMsg
     * @param isSetShipToFlg Set Ship To Flag
     */
    public static void cmnProcForDeriveFromShipTo(NWAL1770CMsg bizMsg, boolean isSetShipToFlg) {

        // Mod Start 2016/09/29 S21_NA#8659
        // NMZC610001PMsg nMZC610001PMsg = callCustInfoGetApiForDefaultMode(bizMsg, bizMsg.shipToCustAcctCd.getValue());
        // Del Start 2019/01/31 QC#30097
        //NMZC610001PMsg nMZC610001PMsg = callCustInfoGetApiForDefaultMode(bizMsg, bizMsg.shipToCustAcctCd.getValue(), bizMsg.shipToCustCd.getValue(), null, NWXC150001DefaultCustomer.MODE_SHIP_ACCT_TO_BILL_TO);
        // Del End 2019/01/31 QC#30097
        // Mod End   2016/09/29 S21_NA#8659

        // Del Start 2019/01/31 QC#30097
        //if (nMZC610001PMsg == null) {
        //    return;
        //}
        // Del End 2019/01/31 QC#30097

        if (isSetShipToFlg) {
            // Add Start 2019/01/31 QC#30097
            NMZC610001PMsg nMZC610001PMsg = callCustInfoGetApiForDefaultMode(bizMsg, //
                    bizMsg.shipToCustAcctCd.getValue(), bizMsg.shipToCustCd.getValue(), null, //
                    NWXC150001DefaultCustomer.MODE_SHIP_ACCT_TO_BILL_TO, ZYPConstant.FLG_OFF_N);

            if (nMZC610001PMsg == null) {
                return;
            }
            // Add End 2019/01/31 QC#30097

            // Mod Start 2019/01/31 QC#30097
            //setShipToInfo(bizMsg, nMZC610001PMsg);
            setShipToInfo(bizMsg, nMZC610001PMsg, false);
            // Mod End 2019/01/31 QC#30097
        }
        deriveRefField(bizMsg);

        // Mod Start 2019/01/31 QC#30097
        //String defBillToCustCd = nMZC610001PMsg.DefaultBillShipList.no(0).billToCustCd.getValue();
        // Del Start 2019/03/12 QC#30730
        //NMZC610001PMsg deriveNMZC610001PMsg = callCustInfoGetApiForDefaultMode(bizMsg, //
        //        bizMsg.shipToCustAcctCd.getValue(), bizMsg.shipToCustCd.getValue(), null, //
        //        NWXC150001DefaultCustomer.MODE_SHIP_ACCT_TO_BILL_TO, ZYPConstant.FLG_ON_Y);
        //
        //if (deriveNMZC610001PMsg == null) {
        //    return;
        //}
        //
        //String defBillToCustCd = deriveNMZC610001PMsg.DefaultBillShipList.no(0).billToCustCd.getValue();
        // Del End 2019/03/12 QC#30730
        // Mod End 2019/01/31 QC#30097

        // Del Start 2019/03/12 QC#30730
        //setBillToInfo(bizMsg, defBillToCustCd);
        // Del End 2019/03/12 QC#30730

        // Add Start 2019/03/12 QC#30730
        boolean isSoldToSet = false;
        if (ZYPCommonFunc.hasValue(bizMsg.soldToCustAcctNm) && ZYPCommonFunc.hasValue(bizMsg.sellToCustCd)) {
            isSoldToSet = true;
        }
        // Add Start 2019/03/12 QC#30730

        // Mod Start 2019/01/31 QC#30097
        //if (!setSoldToInfo(bizMsg)) {
        // Mod Start 2019/03/12 QC#30730
        //if (!setSoldToInfo(bizMsg, false, deriveNMZC610001PMsg)) {
        if (!setSoldToInfo(bizMsg)) {
            // Mod End 2019/03/12 QC#30730
            // Mod End 2019/01/31 QC#30097
            return;
        }

        // Add Start 2019/03/12 QC#30730
        String defBillToCustCd = null;

        defBillToCustCd = NWXC150001DefaultCustomer.getRelatedBillTo(bizMsg.glblCmpyCd.getValue(), bizMsg.shipToCustCd.getValue());
        if (!isSoldToSet && !ZYPCommonFunc.hasValue(defBillToCustCd)) {
            NMZC610001PMsg deriveNMZC610001PMsg = callCustInfoGetApiForDefaultMode(bizMsg, //
                    bizMsg.sellToCustCd.getValue(), null, bizMsg.soldToCustLocCd.getValue(), //
                    NWXC150001DefaultCustomer.MODE_BILL_TO_ACCT, ZYPConstant.FLG_ON_Y);

            if (deriveNMZC610001PMsg == null) {
                return;
            }

            defBillToCustCd = deriveNMZC610001PMsg.DefaultBillShipList.no(0).billToCustCd.getValue();
        }

        setBillToInfo(bizMsg, defBillToCustCd);
        // Add End 2019/03/12 QC#30730

        if (!NWAL1770CommonLogicForSalesCredit.deriveDefaultSlsRep(bizMsg)) {
            return;
        }

        // QC#23726 2018/06/29 del Start
//        if (!NWAL1770CommonLogic.deriveDefaultCarrSvcLvl(bizMsg)) {
//            return;
//        }
        // QC#23726 2018/06/29 del End

        if (!deriveDefaultPO(bizMsg)) {
            return;
        }

//      if (ZYPCommonFunc.hasValue(bizMsg.splyQuoteCatgCd)) { // S21_NA#16410 Del
        if (ZYPCommonFunc.hasValue(bizMsg.splyQuoteCatgCd) && ZYPConstant.FLG_ON_Y.equals(bizMsg.xxRqstFlg_SE.getValue())) { // S21_NA#16410 Add
            NWAL1770CommonLogic.deriveDefaultPrcList(bizMsg);
        }

        if (ZYPCommonFunc.hasValue(bizMsg.billToCustAcctCd) || ZYPCommonFunc.hasValue(bizMsg.billToCustCd)) {
            NWAL1770CommonLogic.deriveDefaultPmtTerm(bizMsg, bizMsg.billToCustCd.getValue());
        }

        // 2018/02/09 S21_NA#20297(Sol#379) Add Start
        if (!NWAL1770CommonLogic.deriveDefaultShippingInfo(bizMsg)) {
            return;
        }

        if (!NWAL1770CommonLogic.deriveDefaultShippingComment(bizMsg)) {
            return;
        }
        // 2018/02/09 S21_NA#20297(Sol#379) Add End
        // QC#23726 2018/06/29 add Start
        if (!NWAL1770CommonLogic.deriveDefaultCarrSvcLvl(bizMsg)) {
            return;
        }
        // QC#23726 2018/06/29 add End
    }

    /**
     * Common Process For Derive From Sold To
     * @param bizMsg NWAL1770CMsg
     * @param isSetSoldToFlg Set Sold To Flag
     */
    public static void cmnProcForDeriveFromSoldTo(NWAL1770CMsg bizMsg, boolean isSetSoldToFlg) {
        // Mod Start 2016/09/29 S21_NA#8659
        //NMZC610001PMsg nMZC610001PMsg = callCustInfoGetApiForDefaultMode(bizMsg, bizMsg.sellToCustCd.getValue());
        // Del Start 2019/01/31 QC#30097
        //NMZC610001PMsg nMZC610001PMsg = callCustInfoGetApiForDefaultMode(bizMsg, bizMsg.sellToCustCd.getValue(), null, bizMsg.soldToCustLocCd.getValue(), NWXC150001DefaultCustomer.MODE_BILL_TO_ACCT);
        // Del End 2019/01/31 QC#30097
        // Mod End   2016/09/29 S21_NA#8659
        // Del Start 2019/01/31 QC#30097
        //if (nMZC610001PMsg == null) {
        //    return;
        //}
        // Del End 2019/01/31 QC#30097

        // Add Start 2019/01/31 QC#30097
        if (isSetSoldToFlg) {
            NMZC610001PMsg nMZC610001PMsg = callCustInfoGetApiForDefaultMode(bizMsg, //
                    bizMsg.sellToCustCd.getValue(), null, bizMsg.soldToCustLocCd.getValue(), //
                    NWXC150001DefaultCustomer.MODE_BILL_TO_ACCT, ZYPConstant.FLG_OFF_N);

            if (nMZC610001PMsg == null) {
                return;
            }

            if (!setSoldToInfo(bizMsg, true, nMZC610001PMsg)) {
                return;
            }
        }
        // Add End 2019/01/31 QC#30097

        // 2018/03/02 S21_NA#20297-1(Sol#379) Add Start
        String prevShipToCustCd = bizMsg.shipToCustCd.getValue();
        // 2018/03/02 S21_NA#20297-1(Sol#379) Add End

        // Mod Start 2019/01/31 QC#30097
        //String defBillToCustCd = nMZC610001PMsg.DefaultBillShipList.no(0).billToCustCd.getValue();
        //
        //setBillToInfo(bizMsg, defBillToCustCd);
        //setShipToInfo(bizMsg, nMZC610001PMsg);
        NMZC610001PMsg deriveNMZC610001PMsg = callCustInfoGetApiForDefaultMode(bizMsg, //
                bizMsg.sellToCustCd.getValue(), null, bizMsg.soldToCustLocCd.getValue(), //
                NWXC150001DefaultCustomer.MODE_BILL_TO_ACCT, ZYPConstant.FLG_ON_Y);

        if (deriveNMZC610001PMsg == null) {
            return;
        }

        String defBillToCustCd = deriveNMZC610001PMsg.DefaultBillShipList.no(0).billToCustCd.getValue();

        setBillToInfo(bizMsg, defBillToCustCd);
        // Mod Start 2019/01/31 QC#30097
        //setShipToInfo(bizMsg, deriveNMZC610001PMsg);
        setShipToInfo(bizMsg, deriveNMZC610001PMsg, true);
        // Mod End 2019/01/31 QC#30097
        // Mod End 2019/01/31 QC#30097

        // 2017/07/27 S21_NA#18243 Add Start
        // Del Start 2019/01/31 QC#30097
        //boolean hasDefaultShipTo = nMZC610001PMsg.DefaultBillShipList.getValidCount() > 0;
        //if (hasDefaultShipTo) {
        //    hasDefaultShipTo = ZYPCommonFunc.hasValue(nMZC610001PMsg.DefaultBillShipList.no(0).shipToCustCd);
        //}
        //if ((!ZYPCommonFunc.hasValue(bizMsg.shipToCustAcctNm) //
        //        || !ZYPCommonFunc.hasValue(bizMsg.shipToCustAcctCd)) //
        //        && hasDefaultShipTo) {
        //    ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustAcctNm, bizMsg.soldToCustAcctNm);
        //    ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustAcctCd, bizMsg.sellToCustCd);
        //}
        // Del End 2019/01/31 QC#30097
        // 2017/07/27 S21_NA#18243 Add End
        deriveRefField(bizMsg);

        // Del Start 2019/01/31 QC#30097
        //if (isSetSoldToFlg && !setSoldToInfo(bizMsg, true, nMZC610001PMsg)) { // 2017/07/27 S21_NA#18243 Add 2nd 3rd Parameters for setSoldToInfo
        //    return;
        //}
        // Del End 2019/01/31 QC#30097

        if (!NWAL1770CommonLogicForSalesCredit.deriveDefaultSlsRep(bizMsg)) {
            return;
        }

        // QC#23726 2018/06/29 del Start
//        if (!NWAL1770CommonLogic.deriveDefaultCarrSvcLvl(bizMsg)) {
//            return;
//        }
        // QC#23726 2018/06/29 del End

        deriveDefaultPO(bizMsg);

        if (ZYPCommonFunc.hasValue(bizMsg.splyQuoteCatgCd)) {
            NWAL1770CommonLogic.deriveDefaultPrcList(bizMsg);
        }

        if (ZYPCommonFunc.hasValue(bizMsg.billToCustAcctCd) || ZYPCommonFunc.hasValue(bizMsg.billToCustCd)) {
            NWAL1770CommonLogic.deriveDefaultPmtTerm(bizMsg, bizMsg.billToCustCd.getValue());
        }

        // 2018/03/02 S21_NA#20297-1(Sol#379) Add Start
        if (!ZYPCommonFunc.hasValue(prevShipToCustCd) && ZYPCommonFunc.hasValue(bizMsg.shipToCustCd)) {
            if (!NWAL1770CommonLogic.deriveDefaultShippingInfo(bizMsg)) {
                return;
            }

            if (!NWAL1770CommonLogic.deriveDefaultShippingComment(bizMsg)) {
                return;
            }
        }
        // 2018/03/02 S21_NA#20297-1(Sol#379) Add End
        // QC#23726 2018/06/29 add Start
        if (!NWAL1770CommonLogic.deriveDefaultCarrSvcLvl(bizMsg)) {
            return;
        }
        // QC#23726 2018/06/29 add End
    }

    /**
     * Combine Customer Address
     * @param bizMsg NWAL1770CMsg
     * @param connectValue Connect Value
     * @return Customer Address
     */
    public static String cmbnAddr(NWAL1770CMsg bizMsg, String connectValue) {

        if (!ZYPCommonFunc.hasValue(bizMsg.firstLineAddr)) {
            return null;
        }

        StringBuilder addr = new StringBuilder(bizMsg.firstLineAddr.getValue());

        if (ZYPCommonFunc.hasValue(bizMsg.scdLineAddr)) {
            addr.append(connectValue);
            addr.append(bizMsg.scdLineAddr.getValue());
        }
        if (ZYPCommonFunc.hasValue(bizMsg.thirdLineAddr)) {
            addr.append(connectValue);
            addr.append(bizMsg.thirdLineAddr.getValue());
        }
        if (ZYPCommonFunc.hasValue(bizMsg.frthLineAddr)) {
            addr.append(connectValue);
            addr.append(bizMsg.frthLineAddr.getValue());
        }
        if (ZYPCommonFunc.hasValue(bizMsg.ctyAddr)) {
            addr.append(connectValue);
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
     * Get Bill To Info
     * @param bizMsg NWAL1770CMsg
     * @param custLocNum Customer Location Number
     * @param msgParm Message Parameter
     * @return Bill To Info
     */
    @SuppressWarnings("unchecked")
    public static Map<String, String> getBillToInfo(NWAL1770CMsg bizMsg, EZDCStringItem custLocNum, String msgParm) {

        S21SsmEZDResult ssmResult = NWAL1770QueryForCustomer.getInstance().getBillToCustInfoList(bizMsg, custLocNum.getValue());

        if (ssmResult.isCodeNotFound()) {
            custLocNum.setErrorInfo(1, NWAM0181E, new String[] {msgParm });
            return null;
        }

        List<Map<String, String>> billToCustInfoList = (List<Map<String, String>>) ssmResult.getResultObject();
        ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_OFF_N);

        if (billToCustInfoList.size() != 1) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_ON_Y);
            // Add Start 2018/11/20 QC#28683
            setParamForNMAL6760SpecialInstruction(bizMsg);
            // Add End 2018/11/20 QC#28683
            return null;
        }

        return billToCustInfoList.get(0);
    }

    /**
     * Get Ship To Info
     * @param bizMsg NWAL1770CMsg
     * @return Ship To Info
     */
    @SuppressWarnings("unchecked")
    public static Map<String, String> getShipToInfo(NWAL1770CMsg bizMsg) {

        S21SsmEZDResult ssmResult = NWAL1770QueryForCustomer.getInstance().getShipToCustInfoList(bizMsg);

        if (ssmResult.isCodeNotFound()) {
            bizMsg.shipToCustCd.setErrorInfo(1, NWAM0181E, new String[] {MSG_PARAM_SHIP_TO_LOC });
            return null;
        }

        List<Map<String, String>> shipToCustInfoList = (List<Map<String, String>>) ssmResult.getResultObject();
        ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_OFF_N);

        if (shipToCustInfoList.size() != 1) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_ON_Y);
            // Add Start 2018/11/20 QC#28683
            setParamForNMAL6760SpecialInstruction(bizMsg);
            // Add End 2018/11/20 QC#28683
            return null;
        }

        return shipToCustInfoList.get(0);
    }

    /**
     * Set Bill To Info
     * @param bizMsg NWAL1770CMsg
     * @param sellToCustTMsg SELL_TO_CUSTTMsg
     * @param nMZC610001PMsg NMZC610001PMsg
     */
    private static void setBillToInfo(NWAL1770CMsg bizMsg, SELL_TO_CUSTTMsg sellToCustTMsg, NMZC610001PMsg nMZC610001PMsg) {

        ZYPEZDItemValueSetter.setValue(bizMsg.billToCustAcctCd, sellToCustTMsg.sellToCustCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.billToCustAcctNm, sellToCustTMsg.dsAcctNm);

        if (nMZC610001PMsg.DefaultBillShipList.getValidCount() == 0) {
            return;
        }

        String defBillToCustCd = nMZC610001PMsg.DefaultBillShipList.no(0).billToCustCd.getValue();
        // 2018/03/07 S21_NA#22387 Mod Start
//        S21SsmEZDResult ssmResult = NWAL1770QueryForCustomer.getInstance().getBillToCustAddr(bizMsg, defBillToCustCd);
        S21SsmEZDResult ssmResult = NWAL1770QueryForCustomer.getInstance().getBillToCustInfoList(bizMsg, defBillToCustCd);
        // 2018/03/07 S21_NA#22387 Mod End
        if (ssmResult.isCodeNotFound()) {
            return;
        }

        // 2018/03/07 S21_NA#22387 Add Start
        List<Map<String, String>> billToCustInfoList = (List<Map<String, String>>) ssmResult.getResultObject();
        Map<String, String> billToCustInfo = billToCustInfoList.get(0);
        // 2018/03/07 S21_NA#22387 Add End

        ZYPEZDItemValueSetter.setValue(bizMsg.billToCustCd, defBillToCustCd);
        // 2018/03/07 S21_NA#22387 Mod Start
//        ZYPEZDItemValueSetter.setValue(bizMsg.xxAllLineAddr_BT, (String) ssmResult.getResultObject());
        ZYPEZDItemValueSetter.setValue(bizMsg.xxAllLineAddr_BT, billToCustInfo.get("BILL_TO_ADDR"));
        ZYPEZDItemValueSetter.setValue(bizMsg.billToLocNum, billToCustInfo.get("LOC_NUM"));
        // 2018/03/07 S21_NA#22387 Mod End
    }

    /**
     * Set Bill To Info
     * @param bizMsg NWAL1770CMsg
     * @param defBillToCustCd Default Bill To Customer Code
     */
    @SuppressWarnings("unchecked")
    private static void setBillToInfo(NWAL1770CMsg bizMsg, String defBillToCustCd) {

        if (!ZYPCommonFunc.hasValue(defBillToCustCd)) {
            return;
        } else if (ZYPCommonFunc.hasValue(bizMsg.billToCustAcctNm) && ZYPCommonFunc.hasValue(bizMsg.billToCustAcctCd)) {
            return;
        }

        S21SsmEZDResult ssmResult = NWAL1770QueryForCustomer.getInstance().getBillToCustInfoList(bizMsg, defBillToCustCd);

        if (ssmResult.isCodeNotFound()) {
            return;
        }

        List<Map<String, String>> billToCustInfoList = (List<Map<String, String>>) ssmResult.getResultObject();
        Map<String, String> billToCustInfo = billToCustInfoList.get(0);
        ZYPEZDItemValueSetter.setValue(bizMsg.billToCustAcctCd, billToCustInfo.get("SELL_TO_CUST_CD"));
        ZYPEZDItemValueSetter.setValue(bizMsg.billToCustAcctNm, billToCustInfo.get("DS_ACCT_NM"));
        ZYPEZDItemValueSetter.setValue(bizMsg.billToCustCd, billToCustInfo.get("BILL_TO_CUST_CD"));
        ZYPEZDItemValueSetter.setValue(bizMsg.xxAllLineAddr_BT, billToCustInfo.get("BILL_TO_ADDR"));
        // 2018/03/07 S21_NA#22387 Add Start
        ZYPEZDItemValueSetter.setValue(bizMsg.billToLocNum, billToCustInfo.get("LOC_NUM"));
        // 2018/03/07 S21_NA#22387 Add End
    }

    // Mod Start 2019/01/31 QC#30097
    ///**
    // * Set Ship To Info
    // * @param bizMsg NWAL1770CMsg
    // * @param nMZC610001PMsg NMZC610001PMsg
    // */
    //@SuppressWarnings("unchecked")
    //private static void setShipToInfo(NWAL1770CMsg bizMsg, NMZC610001PMsg nMZC610001PMsg) {
    /**
     * Set Ship To Info
     * @param bizMsg NWAL1770CMsg
     * @param nMZC610001PMsg NMZC610001PMsg
     * @param isSetShipToFlg Set Ship To Account Flag
     */
    @SuppressWarnings("unchecked")
    private static void setShipToInfo(NWAL1770CMsg bizMsg, NMZC610001PMsg nMZC610001PMsg, boolean isSetShipToAcctFlg) {
        // Mod End 2019/01/31 QC#30097

        if (nMZC610001PMsg.DefaultBillShipList.getValidCount() == 0) {
            return;
        }

        // 2017/07/27 S21_NA#18243 Add Start
        if (ZYPCommonFunc.hasValue(bizMsg.shipToCustCd)) {
            return;
        }
        // 2017/07/27 S21_NA#18243 Add End

        String defShipToCustCd = nMZC610001PMsg.DefaultBillShipList.no(0).shipToCustCd.getValue();
        if (!ZYPCommonFunc.hasValue(defShipToCustCd)) {
            // 2017/07/27 S21_NA#18243 Add Start
            bizMsg.shipToCustCd.clear();
            // 2017/07/27 S21_NA#18243 Add End
            return;
        }

        S21SsmEZDResult ssmResult = NWAL1770QueryForCustomer.getInstance().getShipToCustAddr(bizMsg, defShipToCustCd);
        if (ssmResult.isCodeNotFound()) {
            return;
        }

        Map<String, String> shipToCustAddrInfo = (Map<String, String>) ssmResult.getResultObject();
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustCd, shipToCustAddrInfo.get("SHIP_TO_CUST_CD"));
        ZYPEZDItemValueSetter.setValue(bizMsg.xxAllLineAddr_SH, shipToCustAddrInfo.get("SHIP_TO_ADDR"));
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToLocNm, shipToCustAddrInfo.get("LOC_NM"));
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
        ZYPEZDItemValueSetter.setValue(bizMsg.locNum, shipToCustAddrInfo.get("LOC_NUM"));
        ZYPEZDItemValueSetter.setValue(bizMsg.dropShipFlg, ZYPConstant.FLG_OFF_N);
        // 2017/11/27 S21_NA#21155 add star
        bizMsg.shipToLocNm_DS.clear();
        // 2017/11/27 S21_NA#21155 add end
        // 2018/03/07 S21_NA#22387 Add Start
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToLocNum, shipToCustAddrInfo.get("LOC_NUM"));
        // 2018/03/07 S21_NA#22387 Add End

        // Add Start 2019/01/31 QC#30097
        if (isSetShipToAcctFlg) {
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustAcctCd, shipToCustAddrInfo.get("SELL_TO_CUST_CD"));
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustAcctNm, shipToCustAddrInfo.get("DS_ACCT_NM"));
        }
        // Add End 2019/01/31 QC#30097
    }

    // 2017/07/27 S21_NA#18243 Add Start
    private static boolean setSoldToInfo(NWAL1770CMsg bizMsg) {

        return setSoldToInfo(bizMsg, false, null);
    }
    // 2017/07/27 S21_NA#18243 Add End
    /**
     * Set Ship To Info
     * @param bizMsg NWAL1770CMsg
     * @param soldToMode true: sold to mode false: ship to mode
     * @param nMZC610001PMsg NMZC610001PMsg
     * @return has API error : false
     */
    private static boolean setSoldToInfo(NWAL1770CMsg bizMsg, boolean soldToMode, NMZC610001PMsg nMZC610001PMsg) { // 2017/07/27 S21_NA#18243 Change Interface.

        bizMsg.xxRqstFlg_SE.clear(); // S21_NA#16410 Add
        if (!soldToMode) { // 2017/07/27 S21_NA#18243 Add Condition
            if (ZYPCommonFunc.hasValue(bizMsg.soldToCustAcctNm) && ZYPCommonFunc.hasValue(bizMsg.sellToCustCd)) {
                return true;
            } else if (!ZYPCommonFunc.hasValue(bizMsg.shipToCustAcctCd)) {
                return true;
            }
        } // 2017/07/27 S21_NA#18243 Add Condition

        // Mod Start 2019/03/12 QC#30730
        if (!soldToMode) { // 2017/07/27 S21_NA#18243 Add Condition
            ZYPEZDItemValueSetter.setValue(bizMsg.sellToCustCd, bizMsg.shipToCustAcctCd);
            ZYPEZDItemValueSetter.setValue(bizMsg.soldToCustAcctNm, bizMsg.shipToCustAcctNm);
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg_SE, ZYPConstant.FLG_ON_Y); // S21_NA#16410 Add
        } // 2017/07/27 S21_NA#18243 Add Condition
        // Mod End 2019/03/12 QC#30730

        // 2017/07/27 S21_NA#18243 Mod Start
//        // Mod Start 2016/09/29 S21_NA#8659
//        // NMZC610001PMsg nMZC610001PMsg = callCustInfoGetApiForDefaultMode(bizMsg, bizMsg.sellToCustCd.getValue());
//        NMZC610001PMsg nMZC610001PMsg = callCustInfoGetApiForDefaultMode(bizMsg, bizMsg.sellToCustCd.getValue(), null, bizMsg.soldToCustLocCd.getValue(), NWXC150001DefaultCustomer.MODE_BILL_TO_ACCT);
//        // Mod End   2016/09/29 S21_NA#8659
        // Del Start 2019/01/31 QC#30097
        //if (nMZC610001PMsg == null) {
        //    nMZC610001PMsg = callCustInfoGetApiForDefaultMode(bizMsg, bizMsg.sellToCustCd.getValue(), null, bizMsg.soldToCustLocCd.getValue(), NWXC150001DefaultCustomer.MODE_BILL_TO_ACCT);
        //}
        // Del End 2019/01/31 QC#30097
        // 2017/07/27 S21_NA#18243 Mod End

        // Add Start 2019/03/12 QC#30730
        if (nMZC610001PMsg == null) {
            nMZC610001PMsg = callCustInfoGetApiForDefaultMode(bizMsg, //
                    bizMsg.sellToCustCd.getValue(), null, bizMsg.soldToCustLocCd.getValue(), //
                    NWXC150001DefaultCustomer.MODE_BILL_TO_ACCT, ZYPConstant.FLG_OFF_N);

        }
        // Add End 2019/03/12 QC#30730

        if (nMZC610001PMsg == null) {
            return false;
        }

        if (nMZC610001PMsg.DefaultBillShipList.getValidCount() == 0) {
            return true;
        }

        String defBillToCustCd = nMZC610001PMsg.DefaultBillShipList.no(0).billToCustCd.getValue();
        // 2018/03/07 S21_NA#22387 Mod Start
//        S21SsmEZDResult ssmResult = NWAL1770QueryForCustomer.getInstance().getBillToCustAddr(bizMsg, defBillToCustCd);
        S21SsmEZDResult ssmResult = NWAL1770QueryForCustomer.getInstance().getBillToCustInfoList(bizMsg, defBillToCustCd);
        // 2018/03/07 S21_NA#22387 Mod End
        if (ssmResult.isCodeNotFound()) {
            return true;
        }

        // 2018/03/07 S21_NA#22387 Add Start
        List<Map<String, String>> billToCustInfoList = (List<Map<String, String>>) ssmResult.getResultObject();
        Map<String, String> billToCustInfo = billToCustInfoList.get(0);
        // 2018/03/07 S21_NA#22387 Add End

        ZYPEZDItemValueSetter.setValue(bizMsg.soldToCustLocCd, defBillToCustCd);
        // 2018/03/07 S21_NA#22387 Mod Start
//        ZYPEZDItemValueSetter.setValue(bizMsg.xxAllLineAddr_SE, (String) ssmResult.getResultObject());
        ZYPEZDItemValueSetter.setValue(bizMsg.xxAllLineAddr_SE, billToCustInfo.get("BILL_TO_ADDR"));
        ZYPEZDItemValueSetter.setValue(bizMsg.sellToLocNum, billToCustInfo.get("LOC_NUM"));
        // 2018/03/07 S21_NA#22387 Mod End

        return true;
    }

    /**
     * Check Exist Customer
     * @param bizMsg NWAL1770CMsg
     */
    public static void checkExistCustomer(NWAL1770CMsg bizMsg) {

        SHIP_TO_CUSTTMsg condition = new SHIP_TO_CUSTTMsg();
        condition.setSQLID("002");
        condition.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        condition.setConditionValue("shipToCustCd01", bizMsg.shipToCustCd.getValue());
        condition.setConditionValue("rgtnStsCd01", RGTN_STS.READY_FOR_ORDER_TAKING);
        SHIP_TO_CUSTTMsgArray tmsgArray = (SHIP_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(condition);

        if (tmsgArray.getValidCount() == 0) {
            bizMsg.shipToCustCd.setErrorInfo(1, NWAM0181E, new String[] {MSG_PARAM_SHIP_TO_LOC });
        }
    }

    /**
     * Get Direct Sales Account Customer For Name
     * @param bizMsg NWAL1770CMsg
     * @param custNm Customer Name
     * @return Direct Sales Account Customer
     */
    private static SELL_TO_CUSTTMsgArray getDsSlsAcctCustForName(NWAL1770CMsg bizMsg, String custNm) {

        final SELL_TO_CUSTTMsg condition = new SELL_TO_CUSTTMsg();
        // 2018/03/12 S21_NA#24090 Mod Start
        // condition.setSQLID("502");
        condition.setSQLID("506");
        // 2018/03/12 S21_NA#24090 Mod End
        condition.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        condition.setConditionValue("dsAcctNm01", custNm);
        condition.setConditionValue("rgtnStsCd01", RGTN_STS.READY_FOR_ORDER_TAKING);

        return (SELL_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(condition);
    }

    /**
     * Get Direct Sales Account Customer For Account
     * @param bizMsg NWAL1770CMsg
     * @param acctNum Account Number
     * @return Direct Sales Account Customer
     */
    private static SELL_TO_CUSTTMsgArray getDsSlsAcctCustForAcct(NWAL1770CMsg bizMsg, String acctNum) {

        final SELL_TO_CUSTTMsg condition = new SELL_TO_CUSTTMsg();
        // 2018/03/12 S21_NA#24090 Mod Start
        // condition.setSQLID("503");
        condition.setSQLID("507");
        // 2018/03/12 S21_NA#24090 Mod End
        condition.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        //START 2017/06/22 K.Kasai [QC#19134,MOD]
        condition.setConditionValue("sellToCustCd01", acctNum);
        //END 2017/06/22 K.Kasai [QC#19134,MOD]
        condition.setConditionValue("rgtnStsCd01", RGTN_STS.READY_FOR_ORDER_TAKING);

        return (SELL_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(condition);
    }

    // Mod Start   2016/09/29 S21_NA#11655
//    /**
//     * Call Customer Information Get API For Default Mode
//     * @param bizMsg NWAL1770CMsg
//     * @param dsAcctNum Direct Sales Account Number
//     * @return NMZC610001PMsg
//     */
//    private static NMZC610001PMsg callCustInfoGetApiForDefaultMode(NWAL1770CMsg bizMsg, String dsAcctNum) {
//
//        NMZC610001PMsg pMsg = new NMZC610001PMsg();
//        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, bizMsg.glblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NMZC610001Constant.PROCESS_DEFAULT_BILL_SHIP);
//        ZYPEZDItemValueSetter.setValue(pMsg.dsTrxRuleTpCd, getDsTrxRuleTpCd(bizMsg));
//        ZYPEZDItemValueSetter.setValue(pMsg.dsAcctNum_I1, dsAcctNum);
//
//        // Call NMZC6100 Customer Information Get API
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
    // Mod Start 2019/01/31 QC#30097
    ///**
    // * <pre>
    // * Call Customer Information Get API For Default Mode
    // * 2016/09/29 S21_NA#8659 Change Interface
    // * </pre>
    // * @param bizMsg Business Message
    // * @param dsAcctNum Direct Sales Account Number
    // * @param shipToCustCd Ship to Customer Code (if you set bill to, this parameter should be null)
    // * @param billToCustCd Bill To Customer Code (if you set ship to, this parameter should be null)
    // * @param xxMode API calling mode
    // * @return NMZC610001PMsg Default Customer API return value
    // */
    //private static NMZC610001PMsg callCustInfoGetApiForDefaultMode(NWAL1770CMsg bizMsg, String dsAcctNum, String shipToCustCd, String billToCustCd, String xxMode) {
    /**
     * <pre>
     * Call Customer Information Get API For Default Mode
     * 2016/09/29 S21_NA#8659 Change Interface
     * </pre>
     * @param bizMsg Business Message
     * @param dsAcctNum Direct Sales Account Number
     * @param shipToCustCd Ship to Customer Code (if you set bill to, this parameter should be null)
     * @param billToCustCd Bill To Customer Code (if you set ship to, this parameter should be null)
     * @param xxMode API calling mode
     * @param xxSelFlg Y:Select own account and related account. / other(default):Select own account only.
     * @return NMZC610001PMsg Default Customer API return value
     */
    private static NMZC610001PMsg callCustInfoGetApiForDefaultMode(NWAL1770CMsg bizMsg, String dsAcctNum, String shipToCustCd, String billToCustCd, String xxMode, String xxSelFlg) {
        // Mod End 2019/01/31 QC#30097

        NWXC150001DefaultCustomerBean paramBean = new NWXC150001DefaultCustomerBean();
        paramBean.setGlblCmpyCd(bizMsg.glblCmpyCd.getValue());
        paramBean.setDsTrxRuleTpCd(getDsTrxRuleTpCd(bizMsg));
        paramBean.setDsAcctNum(dsAcctNum);
        // Add Start 2019/01/31 QC#30097
        paramBean.setXxSelFlg(xxSelFlg);
        // Add End 2019/01/31 QC#30097

        if (ZYPCommonFunc.hasValue(shipToCustCd)) {
            paramBean.setShipToCustCd(shipToCustCd);
        } else if (ZYPCommonFunc.hasValue(billToCustCd)) {
            // 2017/07/27 S21_NA#18243 Mod Start
//            paramBean.setBillToCustCd(billToCustCd);
            if (!S21StringUtil.isEquals(NWXC150001DefaultCustomer.MODE_SHIP_ACCT_TO_BILL_TO, xxMode)) {
                paramBean.setBillToCustCd(billToCustCd);
            }
            // 2017/07/27 S21_NA#18243 Mod End
        }
        paramBean.setXxMode(xxMode);
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

    // Mod End   2016/09/29 S21_NA#11655

    /**
     * Get Direct Sales Transaction Rule Type Code
     * @param bizMsg NWAL1770CMsg
     * @return Direct Sales Transaction Rule Type Code
     */
    private static String getDsTrxRuleTpCd(NWAL1770CMsg bizMsg) {

        ORD_CATG_BIZ_CTXTMsg condition = new ORD_CATG_BIZ_CTXTMsg();
        condition.setSQLID("003");
        condition.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        condition.setConditionValue("ordCatgCtxTpCd01A", EQUIPMENT_ORDER_VALUE_SET);
        condition.setConditionValue("ordCatgCtxTpCd01B", SUPPLIES_ORDER_VALUE_SET);
        condition.setConditionValue("dsOrdCatgCd01", bizMsg.splyQuoteCatgCd.getValue());
        condition.setConditionValue("dsOrdTpCd01", bizMsg.dsOrdTpCd.getValue());

        ORD_CATG_BIZ_CTXTMsgArray tmsgArray = (ORD_CATG_BIZ_CTXTMsgArray) EZDTBLAccessor.findByCondition(condition);
        if (tmsgArray.getValidCount() > 0) {
            return tmsgArray.no(0).firstBizCtxAttrbTxt.getValue();
        }

        condition = new ORD_CATG_BIZ_CTXTMsg();
        condition.setSQLID("002");
        condition.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        condition.setConditionValue("ordCatgCtxTpCd01A", EQUIPMENT_ORDER_VALUE_SET);
        condition.setConditionValue("ordCatgCtxTpCd01B", SUPPLIES_ORDER_VALUE_SET);
        condition.setConditionValue("dsOrdCatgCd01", bizMsg.splyQuoteCatgCd.getValue());

        tmsgArray = (ORD_CATG_BIZ_CTXTMsgArray) EZDTBLAccessor.findByCondition(condition);
        if (tmsgArray.getValidCount() > 0) {
            return tmsgArray.no(0).firstBizCtxAttrbTxt.getValue();
        }

        return BLANK;
    }

    /**
     * Derive Default PO
     * @param bizMsg NWAL1770CMsg
     * @return No API Error : true
     */
    private static boolean deriveDefaultPO(NWAL1770CMsg bizMsg) {

        // 2019/02/25 S21_NA#30315 Mod Start
        //if (ZYPCommonFunc.hasValue(bizMsg.custIssPoNum) || !ZYPCommonFunc.hasValue(bizMsg.sellToCustCd)) {
        if (!ZYPCommonFunc.hasValue(bizMsg.sellToCustCd)) {
        // 2019/02/25 S21_NA#30315 Mod End
            return true;
        }

        String trxRuleTpCd = getTrxRuleTp(bizMsg);
        if (!ZYPCommonFunc.hasValue(trxRuleTpCd)) {
            return true;
        }

        NMZC610001PMsg custInfoApiPMsg = callCustomerInfoGetApi(bizMsg, trxRuleTpCd);

        if (S21ApiUtil.isXxMsgId(custInfoApiPMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(custInfoApiPMsg);
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

        if (custInfoApiPMsg.TransactionRuleList.getValidCount() > 0) {
            String defCustIssPoNum = custInfoApiPMsg.TransactionRuleList.no(0).dsBlktPoNum.getValue();
            if (ZYPCommonFunc.hasValue(defCustIssPoNum)) {
                ZYPEZDItemValueSetter.setValue(bizMsg.custIssPoNum, defCustIssPoNum);
            }
        }

        return true;
    }

    /**
     * Call NMZC6100 Customer Infomation Get API
     * @param bizMsg NWAL1770CMsg
     * @param trxRuleTpCd Trx Rule Type Code
     * @return NMZC611001PMsg
     */
    private static NMZC610001PMsg callCustomerInfoGetApi(NWAL1770CMsg bizMsg, String trxRuleTpCd) {

        NMZC610001PMsg pMsg = new NMZC610001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NMZC610001Constant.PROCESS_MODE_TRANSACTION);
        ZYPEZDItemValueSetter.setValue(pMsg.dsTrxRuleTpCd, trxRuleTpCd);
        ZYPEZDItemValueSetter.setValue(pMsg.dsAcctNum_I1, bizMsg.sellToCustCd);
        // 2019/01/29 QC#30036 Add Start
        ZYPEZDItemValueSetter.setValue(pMsg.billToCustCd, bizMsg.soldToCustLocCd);
        // 2019/01/29 QC#30036 Add End
        // 2019/02/15 QC#30308 Add Start
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, bizMsg.slsDt);
        // 2019/02/15 QC#30308 Add End
        new NMZC610001().execute(pMsg, ONBATCH_TYPE.ONLINE);

        return pMsg;
    }

    /**
     * Get Trx Rule Type
     * @param bizMsg NWAL1770CMsg
     * @return Trx Rule Type
     */
    public static String getTrxRuleTp(NWAL1770CMsg bizMsg) {

        ORD_CATG_BIZ_CTXTMsg tMsg = getOrdCatgBizCtx(bizMsg);
        if (tMsg == null) {
            return null;
        }

        return tMsg.firstBizCtxAttrbTxt.getValue();
    }

    /**
     * Get Order Category Business Context
     * @param bizMsg NWAL1770CMsg
     * @return Order Category Business Context
     */
    private static ORD_CATG_BIZ_CTXTMsg getOrdCatgBizCtx(NWAL1770CMsg bizMsg) {

        ORD_CATG_BIZ_CTXTMsg tMsg = new ORD_CATG_BIZ_CTXTMsg();
        tMsg.setSQLID("005");
        tMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        tMsg.setConditionValue("ordCatgCtxTpCd01A", EQUIPMENT_ORDER_VALUE_SET);
        tMsg.setConditionValue("ordCatgCtxTpCd01B", SUPPLIES_ORDER_VALUE_SET);
        tMsg.setConditionValue("dsOrdCatgCd01", bizMsg.splyQuoteCatgCd.getValue());
        tMsg.setConditionValue("dsOrdTpCd01", bizMsg.dsOrdTpCd.getValue());
        ORD_CATG_BIZ_CTXTMsgArray tMsgArray = (ORD_CATG_BIZ_CTXTMsgArray) EZDTBLAccessor.findByCondition(tMsg);

        if (tMsgArray == null || tMsgArray.length() == 0) {
            return null;
        }

        return tMsgArray.no(0);
    }

    /**
     * Clear Reference Field
     * @param bizMsg NWAL1770CMsg
     */
    private static void clearRefField(NWAL1770CMsg bizMsg) {

        bizMsg.firstBllgAttrbNm.clear();
        bizMsg.firstBllgAttrbValTxt.clear();
        bizMsg.scdBllgAttrbNm.clear();
        bizMsg.scdBllgAttrbValTxt.clear();
        bizMsg.thirdBllgAttrbNm.clear();
        bizMsg.thirdBllgAttrbValTxt.clear();
        bizMsg.frthBllgAttrbNm.clear();
        bizMsg.frthBllgAttrbValTxt.clear();
        bizMsg.fifthBllgAttrbNm.clear();
        bizMsg.fifthBllgAttrbValTxt.clear();
        bizMsg.sixthBllgAttrbNm.clear();
        bizMsg.sixthBllgAttrbValTxt.clear();
    }

    /**
     * Derive Reference Field
     * @param bizMsg NWAL1770CMsg
     */
    @SuppressWarnings("unchecked")
    private static void deriveRefField(NWAL1770CMsg bizMsg) {

        // Clear
        clearRefField(bizMsg);

        // Add Start 2016/08/26 S21_NA#13243
        if (!ZYPCommonFunc.hasValue(bizMsg.shipToCustCd.getValue())) {
            return;
        }
        // Add End 2016/08/26 S21_NA#13243

        List<Map<String, String>> bllgAttrbList = null;
        String dsAcctNum = NWXC150001DsCheck.getDefaultAcctFromShipToCust(bizMsg.glblCmpyCd.getValue(), bizMsg.shipToCustCd.getValue());
        S21SsmEZDResult ssmResult = NWAL1770QueryForCustomer.getInstance().getDsAcctRefAttrb(bizMsg, dsAcctNum);

        if (ssmResult == null || ssmResult.isCodeNotFound()) {
            bllgAttrbList = getRelationInfoList(bizMsg, dsAcctNum);
            if (bllgAttrbList == null || bllgAttrbList.isEmpty()) {
                return;
            }
        } else {
            bllgAttrbList = (List<Map<String, String>>) ssmResult.getResultObject();
        }

        int bllgAttrbSize = bllgAttrbList.size();
        int refIndex = 0;

        Map<String, String> bllgAttrbMap = bllgAttrbList.get(refIndex++);
        ZYPEZDItemValueSetter.setValue(bizMsg.firstBllgAttrbNm, bllgAttrbMap.get(BLLG_ATTRB_NM));
        ZYPEZDItemValueSetter.setValue(bizMsg.firstBllgAttrbValTxt, bllgAttrbMap.get(BLLG_ATTRB_VAL_TXT));
        if (refIndex == bllgAttrbSize) {
            return;
        }

        bllgAttrbMap = bllgAttrbList.get(refIndex++);
        ZYPEZDItemValueSetter.setValue(bizMsg.scdBllgAttrbNm, bllgAttrbMap.get(BLLG_ATTRB_NM));
        ZYPEZDItemValueSetter.setValue(bizMsg.scdBllgAttrbValTxt, bllgAttrbMap.get(BLLG_ATTRB_VAL_TXT));
        if (refIndex == bllgAttrbSize) {
            return;
        }

        bllgAttrbMap = bllgAttrbList.get(refIndex++);
        ZYPEZDItemValueSetter.setValue(bizMsg.thirdBllgAttrbNm, bllgAttrbMap.get(BLLG_ATTRB_NM));
        ZYPEZDItemValueSetter.setValue(bizMsg.thirdBllgAttrbValTxt, bllgAttrbMap.get(BLLG_ATTRB_VAL_TXT));
        if (refIndex == bllgAttrbSize) {
            return;
        }

        bllgAttrbMap = bllgAttrbList.get(refIndex++);
        ZYPEZDItemValueSetter.setValue(bizMsg.frthBllgAttrbNm, bllgAttrbMap.get(BLLG_ATTRB_NM));
        ZYPEZDItemValueSetter.setValue(bizMsg.frthBllgAttrbValTxt, bllgAttrbMap.get(BLLG_ATTRB_VAL_TXT));
        if (refIndex == bllgAttrbSize) {
            return;
        }

        bllgAttrbMap = bllgAttrbList.get(refIndex++);
        ZYPEZDItemValueSetter.setValue(bizMsg.fifthBllgAttrbNm, bllgAttrbMap.get(BLLG_ATTRB_NM));
        ZYPEZDItemValueSetter.setValue(bizMsg.fifthBllgAttrbValTxt, bllgAttrbMap.get(BLLG_ATTRB_VAL_TXT));
        if (refIndex == bllgAttrbSize) {
            return;
        }

        bllgAttrbMap = bllgAttrbList.get(refIndex++);
        ZYPEZDItemValueSetter.setValue(bizMsg.sixthBllgAttrbNm, bllgAttrbMap.get(BLLG_ATTRB_NM));
        ZYPEZDItemValueSetter.setValue(bizMsg.sixthBllgAttrbValTxt, bllgAttrbMap.get(BLLG_ATTRB_VAL_TXT));
    }

    /**
     * Get Relation Information List
     * @param bizMsg NWAL1770CMsg
     * @return Relation Information List
     */
    @SuppressWarnings("unchecked")
    private static List<Map<String, String>> getRelationInfoList(NWAL1770CMsg bizMsg, String dsAcctNum) {

        NMZC610001PMsg nMZC610001PMsg = callCustInfoGetApiForEligibleMode(bizMsg, dsAcctNum);
        if (nMZC610001PMsg == null) {
            return null;
        }

        for (int i = 0; i < nMZC610001PMsg.EligibleCheckList.getValidCount(); i++) {
            NMZC610001_EligibleCheckListPMsg bsPMsg = nMZC610001PMsg.EligibleCheckList.no(i);

            if (ZYPConstant.FLG_ON_Y.equals(bsPMsg.dsAcctRelnRecipFlg.getValue())) {
                S21SsmEZDResult ssmResult = NWAL1770QueryForCustomer.getInstance().getDsAcctRefAttrb(bizMsg, bsPMsg.relnDsAcctNum.getValue());
                if (ssmResult != null && ssmResult.isCodeNormal()) {
                    return (List<Map<String, String>>) ssmResult.getResultObject();
                }
            }
        }

        for (int i = 0; i < nMZC610001PMsg.EligibleCheckList.getValidCount(); i++) {
            NMZC610001_EligibleCheckListPMsg bsPMsg = nMZC610001PMsg.EligibleCheckList.no(i);

            if (ZYPConstant.FLG_OFF_N.equals(bsPMsg.dsAcctRelnRecipFlg.getValue())) {
                S21SsmEZDResult ssmResult = NWAL1770QueryForCustomer.getInstance().getDsAcctRefAttrb(bizMsg, bsPMsg.relnDsAcctNum.getValue());
                if (ssmResult != null && ssmResult.isCodeNormal()) {
                    return (List<Map<String, String>>) ssmResult.getResultObject();
                }
            }
        }

        return null;
    }

    /**
     * Call Customer Information Get API For ELIGIBLE Check Mode
     * @param bizMsg NWAL1770CMsg
     * @param dsAcctNum Direct Sales Account Number
     * @return NMZC610001PMsg
     */
    private static NMZC610001PMsg callCustInfoGetApiForEligibleMode(NWAL1770CMsg bizMsg, String dsAcctNum) {

        NMZC610001PMsg pMsg = new NMZC610001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NMZC610001Constant.PROCESS_MODE_ELIGIBLE_CHECK);
        ZYPEZDItemValueSetter.setValue(pMsg.xxChildRelnFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(pMsg.shipToCustCd, bizMsg.shipToCustCd);
        ZYPEZDItemValueSetter.setValue(pMsg.dsAcctNum_I2, dsAcctNum);

        // Call NMZC6100 Customer Information Get API
        new NMZC610001().execute(pMsg, ONBATCH_TYPE.ONLINE);

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

    // 2018/06/21 S21_NA#14307 Add Start
    /**
     * Init Process Special Instruction
     * @param bizMsg NWAL1770CMsg
     */
    public static void initProcSpecialInstruction(NWAL1770CMsg bizMsg, EZDCStringItem noCheckItem) {

        // Initialize
        bizMsg.sellToCustCd_SP.clear();
        bizMsg.billToCustCd_SP.clear();
        bizMsg.shipToCustCd_SP.clear();
        
        // set Backup Item
        ZYPEZDItemValueSetter.setValue(bizMsg.sellToCustCd_BK, bizMsg.sellToCustCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.billToCustCd_BK, bizMsg.billToCustCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustCd_BK, bizMsg.shipToCustCd);
        
        // noCheckItem setting
        if (noCheckItem != null) {
            noCheckItem.clear();
        }
    }

    /**
     * Common Process For Special Instruction
     * @param bizMsg NWAL1770CMsg
     */
    public static void cmnProcForSpecialInstruction(NWAL1770CMsg bizMsg) {

        boolean isDisplay = false;

        String glblCmpyCd = bizMsg.glblCmpyCd.getValue();
        String trxRuleTp = NWAL1770CommonLogicForCustomer.getTrxRuleTp(bizMsg);
        // 2018/07/10 S21_NA#26661,26713 Add Start
        String funcCatgId = NWXC150001DsCheck.getOrdCatgBizCtxFstAttbTxt(bizMsg.glblCmpyCd.getValue(), ORD_CATG_CTX_TP.CUST_SPCL_ORDER_CATEGORY, bizMsg.splyQuoteCatgCd.getValue(), bizMsg.dsOrdTpCd.getValue());
        // 2018/07/10 S21_NA#26661,26713 Add End

        // Check display NMAL6760
        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxRqstFlg)) {
            // NMAL6760 param set
            ZYPEZDItemValueSetter.setValue(bizMsg.xxPopPrm_10, trxRuleTp);
            // 2018/07/10 S21_NA#26661,26713 Add Start
            ZYPEZDItemValueSetter.setValue(bizMsg.xxPopPrm_12, funcCatgId);
            // 2018/07/10 S21_NA#26661,26713 Add End
            return;
        }

        // Check common function result
        // Sold To
        if (ZYPCommonFunc.hasValue(bizMsg.sellToCustCd) && 
            !S21StringUtil.isEquals(bizMsg.sellToCustCd.getValue(), bizMsg.sellToCustCd_BK.getValue())) {

            if (NWXC150001DsCheck.spclInstnDisplayDetermination(
                    // 2018/07/10 S21_NA#26661,26713 Mod Start
                    //glblCmpyCd, trxRuleTp, null, bizMsg.sellToCustCd.getValue(), null, null, BIZ_ID, null)){
                    glblCmpyCd, trxRuleTp, null, bizMsg.sellToCustCd.getValue(), null, null, BIZ_ID, funcCatgId, bizMsg.lineBizTpCd.getValue())){
                    // 2018/07/10 S21_NA#26661,26713 Mod End
                ZYPEZDItemValueSetter.setValue(bizMsg.sellToCustCd_SP, bizMsg.sellToCustCd);
                isDisplay = true;
            }
        }

        // Bill To
        if (ZYPCommonFunc.hasValue(bizMsg.billToCustCd) && 
            !S21StringUtil.isEquals(bizMsg.billToCustCd.getValue(), bizMsg.billToCustCd_BK.getValue())) {

            if (NWXC150001DsCheck.spclInstnDisplayDetermination(
                    // 2018/07/10 S21_NA#26661,26713 Mod Start
                    //glblCmpyCd, trxRuleTp, null, null, bizMsg.billToCustCd.getValue(), null, BIZ_ID, null)){
                    glblCmpyCd, trxRuleTp, null, null, bizMsg.billToCustCd.getValue(), null, BIZ_ID, funcCatgId, bizMsg.lineBizTpCd.getValue())){
                    // 2018/07/10 S21_NA#26661,26713 Mod End
                ZYPEZDItemValueSetter.setValue(bizMsg.billToCustCd_SP, bizMsg.billToCustCd);
                isDisplay = true;
            }
        }

        // Ship To
        if (ZYPCommonFunc.hasValue(bizMsg.shipToCustCd) && 
            !S21StringUtil.isEquals(bizMsg.shipToCustCd.getValue(), bizMsg.shipToCustCd_BK.getValue())) {

            if (NWXC150001DsCheck.spclInstnDisplayDetermination(
                    // 2018/07/10 S21_NA#26661,26713 Mod Start
                    //glblCmpyCd, trxRuleTp, null, null, null, bizMsg.shipToCustCd.getValue(), BIZ_ID, null)){
                    glblCmpyCd, trxRuleTp, null, null, null, bizMsg.shipToCustCd.getValue(), BIZ_ID, funcCatgId, bizMsg.lineBizTpCd.getValue())){
                    // 2018/07/10 S21_NA#26661,26713 Mod End
                ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustCd_SP, bizMsg.shipToCustCd);
                isDisplay = true;
            }
        }

        if (isDisplay) {
            // NMAL3300 param set
            // 2018/07/10 S21_NA#26661,26713 Add Start
            ZYPEZDItemValueSetter.setValue(bizMsg.xxPopPrm_02, funcCatgId);
            // 2018/07/10 S21_NA#26661,26713 Add End
            ZYPEZDItemValueSetter.setValue(bizMsg.xxPopPrm_03, trxRuleTp);
        }
    }
    // 2018/06/21 S21_NA#14307 Add End

    // 2018/07/10 S21_NA#26661,26713 Add Start
    /**
     * Set Account Search Popup Parameter
     * @param bizMsg NWAL1770CMsg
     */
    public static void setAccountSearchPopupParam(NWAL1770CMsg bizMsg) {

        // Transaction Type
        String trxRuleTp = NWAL1770CommonLogicForCustomer.getTrxRuleTp(bizMsg);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxPopPrm_10, trxRuleTp);

        // Function Category ID
        String funcCatgId = NWXC150001DsCheck.getOrdCatgBizCtxFstAttbTxt(bizMsg.glblCmpyCd.getValue(), ORD_CATG_CTX_TP.CUST_SPCL_ORDER_CATEGORY, bizMsg.splyQuoteCatgCd.getValue(), bizMsg.dsOrdTpCd.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.xxPopPrm_12, funcCatgId);
    }
    
    // 2018/07/10 S21_NA#26661,26713 Add Start

    // 2018/08/02 S21_NA#14307 Add Start
    /**
     * Init Process Special Instruction for Clear
     * @param bizMsg NWAL1770CMsg
     */
    public static void initProcSpecialInstructionForClear(NWAL1770CMsg bizMsg) {

        // Initialize
        bizMsg.sellToCustCd_SP.clear();
        bizMsg.billToCustCd_SP.clear();
        bizMsg.shipToCustCd_SP.clear();

        bizMsg.sellToCustCd_BK.clear();
        bizMsg.billToCustCd_BK.clear();
        bizMsg.shipToCustCd_BK.clear();
    }
    // 2018/08/02 S21_NA#14307 Add End

    // Add Start 2018/11/20 QC#28683
    /**
     * setParamForNMAL6760SpecialInstruction
     * @param bizMsg NWAL1770CMsg
     */
    public static void setParamForNMAL6760SpecialInstruction(NWAL1770CMsg bizMsg) {
        String glblCmpyCd = bizMsg.glblCmpyCd.getValue();
        String splyQuoteCatgCd = bizMsg.splyQuoteCatgCd.getValue();
        String dsOrdTpCd = bizMsg.dsOrdTpCd.getValue();

        String trxRuleTp = NWAL1770CommonLogicForCustomer.getTrxRuleTp(bizMsg);
        String funcCatgId = NWXC150001DsCheck.getOrdCatgBizCtxFstAttbTxt(glblCmpyCd, ORD_CATG_CTX_TP.CUST_SPCL_ORDER_CATEGORY, splyQuoteCatgCd, dsOrdTpCd);

        ZYPEZDItemValueSetter.setValue(bizMsg.xxPopPrm_10, trxRuleTp);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxPopPrm_12, funcCatgId);
    }
    // Add End 2018/11/20 QC#28683

    // START 2023/07/20 T.Fukuta [CSA-QC#61467,ADD]
    /**
     * Search Credit Rep
     * @param bizMsg NWAL1770CMsg
     */
    public static void searchCreditRep(NWAL1770CMsg bizMsg) {

        String ordNum = bizMsg.cpoOrdNum.getValue();
        if (S21StringUtil.isEmpty(ordNum)) {
            return;
        }

        bizMsg.crAnlstPsnNm.clear();
        String glblCmpyCd = bizMsg.glblCmpyCd.getValue();
        S21NwfProcess proc = getWfProcess(glblCmpyCd, ordNum);
        if (proc != null) {
            String creditRep = getCreditRep(proc);
            ZYPEZDItemValueSetter.setValue(bizMsg.crAnlstPsnNm, creditRep);
        }
    }

    /**
     * Get Workflow Process
     * @param glblCmpyCd global company code
     * @param ordNum order number
     * @return workflow process
     */
    private static S21NwfProcess getWfProcess(String glblCmpyCd, String ordNum) {

        String wfProcNmCrReview = ZYPCodeDataUtil.getVarCharConstValue(KEY_CR_APVL_WF_ID, glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(wfProcNmCrReview)) {
            wfProcNmCrReview = "";
        }
        S21NwfContextFactory factory = new S21NwfUtilContextFactory();

        try {
            // Get Workflow Process by Order Number (The latest item of process list is the current process)
            S21NwfContext context = factory.getContex();
            List<S21NwfProcess> procListByOrder = context.getProcessFindByGroup(ordNum);
            if (procListByOrder == null || procListByOrder.size() == 0) {
                return null;
            }
            Collections.sort(procListByOrder, new Comparator<S21NwfProcess>() {
                @Override
                public int compare(S21NwfProcess o1, S21NwfProcess o2) {
                    return o1.getStartTimestamp().compareTo(o2.getStartTimestamp()) * -1;
                }
            });
            S21NwfProcess procByOrder = procListByOrder.get(0);
            String procName = procByOrder.getProcessName();
            if (!wfProcNmCrReview.equals(procName)) {
                // Workflow process is not "Credit Review"
                return null;
            }
            String docId = procByOrder.getDocumentId();

            // Get Workflow Process by workflow document id (Always 1 record)
            List<S21NwfProcess> procListByDocId = context.getProcessForBiz(procName, docId);
            if (procListByDocId == null || procListByDocId.size() == 0) {
                return null;
            }
            return procListByDocId.get(0);

        } catch (Exception e) {
            throw new S21AbendException(e);
        }
    }

    /**
     * Get Credit Rep
     * @param proc workflow process
     * @return credit rep user name
     */
    @SuppressWarnings("unchecked")
    private static String getCreditRep(S21NwfProcess proc) {

        if (proc instanceof S21NwfUtilBizProcess) {
            NYXC880002Query queryWf = NYXC880002Query.getInstance();

            // Get Workflow Task (The last item of task list is the current task)
            S21NwfUtilBizProcess bizProc = (S21NwfUtilBizProcess) proc;
            List<S21NwfUtilBizWorkItem> taskList = bizProc.getTasks();
            if (taskList == null || taskList.size() == 0) {
                return null;
            }

            int taskIndex = taskList.size() - 1;
            S21NwfUtilBizWorkItem task = taskList.get(taskIndex);
            String taskStatus = task.getStatusCode();
            if (S21NwfWorkItem.STATUS.RUN.getCode().equals(taskStatus)) {
                // Workflow is running
                // Get "To"(Next approver) User Name
                BigDecimal nextUserId = task.getWorkItemUId();
                S21SsmEZDResult ssmResult1 = queryWf.getToUsers(nextUserId, null, WF_PROC_COND_STS.ACTIVE);
                if (!ssmResult1.isCodeNotFound()) {
                    // Get User Name (The first item of user list is the next approver)
                    List<Map<String, Object>> userList = (List<Map<String, Object>>) ssmResult1.getResultObject();
                    Map<String, Object> user = userList.get(0);
                    String userName = (String) user.get("WFUSRNM");
                    return userName;
                }
            } else if (S21NwfWorkItem.STATUS.CLOSE.getCode().equals(taskStatus)) {
                // Workflow is closed
                List<S21NwfUtilBizHistWorkItem> historyList = bizProc.getHistory();
                if (historyList == null || historyList.size() < taskList.size()) {
                    return null;
                }
                // The same position's item is the history related on current task
                S21NwfUtilBizHistWorkItem history = historyList.get(taskIndex);
                String actionNm = history.getActName();
                if (WF_ACTION_NM_APPROVE.equals(actionNm)) {
                    // approved
                    // Get User Name of last approver
                    String toUserId = history.getActOpUser();
                    S21SsmEZDResult ssmResult3 = queryWf.getPsnNm(toUserId, null);
                    if (!ssmResult3.isCodeNotFound()) {
                        List<Map<String, Object>> psnNmList = (List<Map<String, Object>>) ssmResult3.getResultObject();
                        Map<String, Object> psnNm = psnNmList.get(0);
                        String fullName = (String) psnNm.get("FULL_PSN_NM");
                        if (!S21StringUtil.isEmpty(fullName)) {
                            return toUserId + " " + fullName;
                        } else {
                            return toUserId;
                        }
                    }
                }   // rejected or another status
            }   // Unknown status
        }

        return null;
    }
    // END 2023/07/20 T.Fukuta [CSA-QC#61467,ADD]
}
