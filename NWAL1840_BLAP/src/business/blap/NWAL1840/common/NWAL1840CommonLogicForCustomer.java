/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1840.common;

import static business.blap.NWAL1840.constant.NWAL1840Constant.COMMA;
import static business.blap.NWAL1840.constant.NWAL1840Constant.SPACE;
import static business.blap.NWAL1840.constant.NWAL1840Constant.BLANK;
import static business.blap.NWAL1840.constant.NWAL1840Constant.EQUIPMENT_ORDER_VALUE_SET;
import static business.blap.NWAL1840.constant.NWAL1840Constant.SUPPLIES_ORDER_VALUE_SET;
import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.MSG_PARAM_SHIP_TO_LOC;
import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.NWAM0181E;
import static business.blap.NWAL1840.constant.NWAL1840Constant.BIZ_ID;

import java.util.List;
import java.util.Map;

import parts.common.EZDCStringItem;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NWAL1840.NWAL1840CMsg;
import business.blap.NWAL1840.NWAL1840QueryForCustomer;
import business.db.ORD_CATG_BIZ_CTXTMsg;
import business.db.ORD_CATG_BIZ_CTXTMsgArray;
import business.db.SELL_TO_CUSTTMsg;
import business.db.SELL_TO_CUSTTMsgArray;
import business.parts.NMZC610001PMsg;

import com.canon.cusa.s21.api.NMZ.NMZC610001.NMZC610001;
import com.canon.cusa.s21.api.NMZ.NMZC610001.constant.NMZC610001Constant;
import com.canon.cusa.s21.common.NWX.NWXC150001.NWXC150001DefaultCustomer;
import com.canon.cusa.s21.common.NWX.NWXC150001.NWXC150001DefaultCustomerBean;
import com.canon.cusa.s21.common.NWX.NWXC150001.NWXC150001DsCheck;
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
 * 2016/03/12   Fujitsu         T.Murai         Create          N/A
 * 2016/07/15   Fujitsu         H.Ikeda         Update          QC#11578
 * 2016/09/29   Fujitsu         T.Murai         Update          S21_NA#8659
 * 2016/12/19   Fujitsu         T.Yoshida       Update          S21_NA#16410
 * 2017/07/27   Fujitsu         S.Takami        Update          S21_NA#18243
 * 2018/02/08   Fujitsu         K.Ishizuka      Update          S21_NA#20297(Sol#379)
 * 2018/02/23   Fujitsu         K.Ishizuka      Update          S21_NA#22399
 * 2018/03/09   Fujitsu         A.Kosai         Update          S21_NA#22387
 * 2018/03/12   Fujitsu         K.Ishizuka      Update          S21_NA#24090
 * 2018/07/26   Fujitsu         H.Kumagai       Update          QC#14307
 * 2018/07/26   Fujitsu         H.Kumagai       Update          QC#26661,26713
 * 2018/11/21   Fujitsu         Hd.Sugawara     Update          QC#28683
 * 2019/01/29   Fujitsu         S.Kosaka        Update          QC#30036
 * 2019/02/01   Fujitsu         K.Kato          Update          QC#30097
 * 2019/02/15   Fujitsu         K.Kato          Update          QC#30308
 * 2019/02/25   Fujitsu         K.Kato          Update          QC#30315
 * 2019/03/14   Fujitsu         Hd.Sugawara     Update          QC#30730
 * </pre>
 */
public class NWAL1840CommonLogicForCustomer {

    /**
     * Get DS Accont Customer Info
     * @param bizMsg NWAL1840CMsg
     * @param isCallNameFld Called Name Field
     * @param targetItem Target Item
     * @param msgParm Message Parameter
     * @return Sell To Customer Info
     */
    public static SELL_TO_CUSTTMsg getDsAcctCustInfo(NWAL1840CMsg bizMsg, boolean isCallNameFld, EZDCStringItem targetItem, String msgParm) {

        SELL_TO_CUSTTMsgArray dsAcctCustTMsgArray = null;
        if (isCallNameFld) {
            dsAcctCustTMsgArray = getDsSlsAcctCustForName(bizMsg, targetItem.getValue());
        } else {
            dsAcctCustTMsgArray = getDsSlsAcctCustForAcct(bizMsg, targetItem.getValue());
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_OFF_N);

        if (dsAcctCustTMsgArray.getValidCount() == 0) {
            targetItem.setErrorInfo(1, NWAM0181E, new String[] {msgParm });
            return null;
        } else if (dsAcctCustTMsgArray.getValidCount() != 1) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_ON_Y);
            return null;
        }

        return dsAcctCustTMsgArray.no(0);
    }

    /**
     * Common Process For Derive From Bill To
     * @param bizMsg NWAL1840CMsg
     * @param dsAcctCustTMsg SELL_TO_CUSTTMsg
     */
    public static void cmnProcForDeriveFromBillTo(NWAL1840CMsg bizMsg, SELL_TO_CUSTTMsg dsAcctCustTMsg) {

        // Mod Start S21_NA#8659 2016/09/29
//        NMZC610001PMsg nMZC610001PMsg = callCustInfoGetApiForDefaultMode(bizMsg, dsAcctCustTMsg.dsAcctNum.getValue());
        // 2019/02/01 S21_NA#30097 Mod Start
        //NMZC610001PMsg nMZC610001PMsg = callCustInfoGetApiForDefaultMode(bizMsg, dsAcctCustTMsg.sellToCustCd.getValue(), null, bizMsg.billToCustLocCd.getValue(), NWXC150001DefaultCustomer.MODE_BILL_TO_ACCT);
        NMZC610001PMsg nMZC610001PMsg = callCustInfoGetApiForDefaultMode(bizMsg, dsAcctCustTMsg.sellToCustCd.getValue(), null, bizMsg.billToCustLocCd.getValue(), NWXC150001DefaultCustomer.MODE_BILL_TO_ACCT, ZYPConstant.FLG_OFF_N);
        // 2019/02/01 S21_NA#30097 Mod End
        // Mod End S21_NA#8659 2016/09/29

        if (nMZC610001PMsg == null) {
            return;
        }

        setBillToInfo(bizMsg, dsAcctCustTMsg, nMZC610001PMsg);
        NWAL1840CommonLogic.deriveDefaultPmtTerm(bizMsg, bizMsg.billToCustLocCd.getValue());
    }

    /**
     * Common Process For Derive From Ship To
     * @param bizMsg NWAL1840CMsg
     * @param isSetShipToFlg Set Ship To Flag
     */
    public static void cmnProcForDeriveFromShipTo(NWAL1840CMsg bizMsg, boolean isSetShipToFlg) {

        // Mod Start S21_NA#8659 2016/09/29
//        NMZC610001PMsg nMZC610001PMsg = callCustInfoGetApiForDefaultMode(bizMsg, bizMsg.shipToCustAcctCd.getValue());
        // 2019/02/01 S21_NA#30097 Mod Start
        if (isSetShipToFlg) {
            //NMZC610001PMsg nMZC610001PMsg = callCustInfoGetApiForDefaultMode(bizMsg, bizMsg.shipToCustAcctCd.getValue(), bizMsg.shipToCustLocCd.getValue(), null, NWXC150001DefaultCustomer.MODE_SHIP_ACCT_TO_BILL_TO);
            NMZC610001PMsg nMZC610001PMsg = callCustInfoGetApiForDefaultMode(bizMsg, bizMsg.shipToCustAcctCd.getValue(), bizMsg.shipToCustLocCd.getValue(), null, NWXC150001DefaultCustomer.MODE_SHIP_ACCT_TO_BILL_TO, ZYPConstant.FLG_OFF_N);

            if (nMZC610001PMsg == null) {
                return;
            }

        //if (isSetShipToFlg) {
            setShipToInfo(bizMsg, nMZC610001PMsg);
        }
        // 2019/02/01 S21_NA#30097 Mod End
        
        NWAL1840CommonLogic.setDefShipInfo(bizMsg); // 2018/02/09 S21_NA#20297(Sol#379) Add

        // 2019/02/01 S21_NA#30097 Add Start
        // Del Start 2019/03/14 QC#30730
        //NMZC610001PMsg nMZC610001BSPMsg = callCustInfoGetApiForDefaultMode(bizMsg, bizMsg.shipToCustAcctCd.getValue(), bizMsg.shipToCustLocCd.getValue(), null, NWXC150001DefaultCustomer.MODE_SHIP_ACCT_TO_BILL_TO, ZYPConstant.FLG_ON_Y);
        // Del End 2019/03/14 QC#30730
        // 2019/02/01 S21_NA#30097 Add End

        // 2019/02/01 S21_NA#30097 Mod Start
        //String defBillToCustCd = nMZC610001PMsg.DefaultBillShipList.no(0).billToCustCd.getValue();
        // Del Start 2019/03/14 QC#30730
        //String defBillToCustCd = nMZC610001BSPMsg.DefaultBillShipList.no(0).billToCustCd.getValue();
        // Del End 2019/03/14 QC#30730
        // 2019/02/01 S21_NA#30097 Mod Start
        // Del Start 2019/03/14 QC#30730
        //setBillToInfo(bizMsg, defBillToCustCd);
        // Del End 2019/03/14 QC#30730

        // Add End 2019/03/14 QC#30730
        boolean isSoldToSet = false;
        if (ZYPCommonFunc.hasValue(bizMsg.soldToCustAcctNm) && ZYPCommonFunc.hasValue(bizMsg.sellToCustCd)) {
            isSoldToSet = true;
        }
        // Add Start 2019/03/14 QC#30730

        // 2019/02/01 S21_NA#30097 Mod Start
        //if (!setSoldToInfo(bizMsg)) {
        // Mod Start 2019/03/14 QC#30730
        //if (!setSoldToInfo(bizMsg, false, nMZC610001BSPMsg, ZYPConstant.FLG_ON_Y)) {
        if (!setSoldToInfo(bizMsg)) {
            // Mod End 2019/03/14 QC#30730
        // 2019/02/01 S21_NA#30097 Mod End
            return;
        }

        // Add Start 2019/03/14 QC#30730
        String defBillToCustCd = null;

        defBillToCustCd = NWXC150001DefaultCustomer.getRelatedBillTo(bizMsg.glblCmpyCd.getValue(), bizMsg.shipToCustLocCd.getValue());
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
        // Add End 2019/03/14 QC#30730

        if (ZYPCommonFunc.hasValue(bizMsg.billToCustAcctCd) || ZYPCommonFunc.hasValue(bizMsg.billToCustLocCd)) {
            NWAL1840CommonLogic.deriveDefaultPmtTerm(bizMsg, bizMsg.billToCustLocCd.getValue());
        }

        if (!NWAL1840CommonLogic.deriveDefaultSlsRep(bizMsg)) {
        //if (!NWAL1840CommonLogicForSalesCredit.deriveDefaultSlsRep(bizMsg)) {
            return;
        }

        if (!NWAL1840CommonLogic.deriveDefaultCarrSvcLvl(bizMsg)) {
            return;
        }

        if (!deriveDefaultPO(bizMsg)) {
            return;
        }

        // QC#11884 2016/08/04 Mod Start
        // if (ZYPCommonFunc.hasValue(bizMsg.schdAgmtCatgCd)) {
        // if (ZYPCommonFunc.hasValue(bizMsg.dsOrdCatgCd)) { // S21_NA#16410 Del
        if (ZYPCommonFunc.hasValue(bizMsg.dsOrdCatgCd) && ZYPConstant.FLG_ON_Y.equals(bizMsg.xxRqstFlg_SE.getValue())) { // S21_NA#16410 Add
            NWAL1840CommonLogic.deriveDefaultPrcList(bizMsg);
        }
        // QC#11884 2016/08/03 Mod End
    }

    /**
     * Common Process For Derive From Sold To
     * @param bizMsg NWAL1840CMsg
     * @param isSetSoldToFlg Set Sold To Flag
     */
    public static void cmnProcForDeriveFromSoldTo(NWAL1840CMsg bizMsg, boolean isSetSoldToFlg) {

        // 2019/02/01 S21_NA#30097 Mod Start
        if (isSetSoldToFlg) {
            // Mod Start S21_NA#8659 2016/09/29
//          NMZC610001PMsg nMZC610001PMsg = callCustInfoGetApiForDefaultMode(bizMsg, bizMsg.sellToCustCd.getValue());
            //NMZC610001PMsg nMZC610001PMsg = callCustInfoGetApiForDefaultMode(bizMsg, bizMsg.sellToCustCd.getValue(), null, bizMsg.soldToCustLocCd.getValue(), NWXC150001DefaultCustomer.MODE_BILL_TO_ACCT);
            NMZC610001PMsg nMZC610001PMsg = callCustInfoGetApiForDefaultMode(bizMsg, bizMsg.sellToCustCd.getValue(), null, bizMsg.soldToCustLocCd.getValue(), NWXC150001DefaultCustomer.MODE_BILL_TO_ACCT, ZYPConstant.FLG_OFF_N);
            // Mod End S21_NA#8659 2016/09/29
            if (nMZC610001PMsg == null) {
                return;
            }
            if (!setSoldToInfo(bizMsg, true, nMZC610001PMsg, ZYPConstant.FLG_OFF_N)) {
                return;
            }

        }
        // 2019/02/01 S21_NA#30097 Mod End

        // 2019/02/01 S21_NA#30097 Add Start
        NMZC610001PMsg nMZC610001BSPMsg = callCustInfoGetApiForDefaultMode(bizMsg, bizMsg.sellToCustCd.getValue(), null, bizMsg.soldToCustLocCd.getValue(), NWXC150001DefaultCustomer.MODE_BILL_TO_ACCT, ZYPConstant.FLG_ON_Y);
        if (nMZC610001BSPMsg == null) {
            return;
        }
        // 2019/02/01 S21_NA#30097 Add End

        // 2018/02/09 S21_NA#20297(Sol#379) Add Start
        String prevShipToCustLocCd = bizMsg.shipToCustLocCd.getValue();
        // 2018/02/09 S21_NA#20297(Sol#379) Add End

        // 2019/02/01 S21_NA#30097 Mod Start
        //String defBillToCustCd = nMZC610001PMsg.DefaultBillShipList.no(0).billToCustCd.getValue();
        String defBillToCustCd = nMZC610001BSPMsg.DefaultBillShipList.no(0).billToCustCd.getValue();
        // 2019/02/01 S21_NA#30097 Mod End

        setBillToInfo(bizMsg, defBillToCustCd);
        // 2019/02/01 S21_NA#30097 Mod Start
        //setShipToInfo(bizMsg, nMZC610001PMsg);        
        setShipToInfo(bizMsg, nMZC610001BSPMsg);        
        // 2019/02/01 S21_NA#30097 Mod End
        // 2017/07/27 S21_NA#18243 Add Start
        // 2019/02/01 S21_NA#30097 Del Start
        //boolean hasDefaultShipTo = nMZC610001PMsg.DefaultBillShipList.getValidCount() > 0;
        //// 2019/02/01 S21_NA#30097 Mod End
        //if (hasDefaultShipTo) {
        //   /hasDefaultShipTo = ZYPCommonFunc.hasValue(nMZC610001PMsg.DefaultBillShipList.no(0).shipToCustCd);
        //}
        //if ((!ZYPCommonFunc.hasValue(bizMsg.shipToCustAcctNm) //
        //        || !ZYPCommonFunc.hasValue(bizMsg.shipToCustAcctCd)) //
        //        && hasDefaultShipTo) {
        //    ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustAcctNm, bizMsg.soldToCustAcctNm);
        //    ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustAcctCd, bizMsg.sellToCustCd);
        //}
        //// 2017/07/27 S21_NA#18243 Add End
        //
        //if (isSetSoldToFlg && !setSoldToInfo(bizMsg, true, nMZC610001PMsg)) { // 2017/07/27 S21_NA#18243 Add 2nd and 3rd parameter
        //    return;
        //}
        // 2019/02/01 S21_NA#30097 Del End

        if (ZYPCommonFunc.hasValue(bizMsg.billToCustAcctCd) || ZYPCommonFunc.hasValue(bizMsg.billToCustLocCd)) {
            NWAL1840CommonLogic.deriveDefaultPmtTerm(bizMsg, bizMsg.billToCustLocCd.getValue());
        }

        if (!NWAL1840CommonLogic.deriveDefaultSlsRep(bizMsg)) {
        //if (!NWAL1840CommonLogicForSalesCredit.deriveDefaultSlsRep(bizMsg)) {
            return;
        }

        if (!NWAL1840CommonLogic.deriveDefaultCarrSvcLvl(bizMsg)) {
            return;
        }

        deriveDefaultPO(bizMsg);
        // QC#11884 2016/08/04 Mod Start
        //if (ZYPCommonFunc.hasValue(bizMsg.schdAgmtCatgCd)) {
        if (ZYPCommonFunc.hasValue(bizMsg.dsOrdCatgCd)) {
            NWAL1840CommonLogic.deriveDefaultPrcList(bizMsg);
        }
        // QC#11884 2016/08/04 Mod End

        // 2018/02/09 S21_NA#20297(Sol#379) Add Start
        if (!ZYPCommonFunc.hasValue(prevShipToCustLocCd) && ZYPCommonFunc.hasValue(bizMsg.shipToCustLocCd)) {
            NWAL1840CommonLogic.setDefShipInfo(bizMsg);
        }
        // 2018/02/09 S21_NA#20297(Sol#379) Add End
    }

    // QC#11578 2016/07/15 Add Start
    /**
     * Combine Customer Address
     * @param bizMsg NWAL1840CMsg
     * @param connectValue Connect Value
     * @return Customer Address
     */
    public static String cmbnAddr(NWAL1840CMsg bizMsg, String connectValue) {

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
    // QC#11578 2016/07/15 Add End

    /**
     * Get Bill To Info
     * @param bizMsg NWAL1840CMsg
     * @param custLocNum Customer Location Number
     * @param msgParm Message Parameter
     * @return Bill To Info
     */
    public static Map<String, String> getBillToInfo(NWAL1840CMsg bizMsg, EZDCStringItem custLocNum, String msgParm) {

        S21SsmEZDResult ssmResult = NWAL1840QueryForCustomer.getInstance().getBillToCustInfoList(bizMsg, custLocNum.getValue());

        if (ssmResult.isCodeNotFound()) {
            custLocNum.setErrorInfo(1, NWAM0181E, new String[] {msgParm });
            return null;
        }

        List<Map<String, String>> billToCustInfoList = (List<Map<String, String>>) ssmResult.getResultObject();
        ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_OFF_N);

        if (billToCustInfoList.size() != 1) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_ON_Y);
            return null;
        }

        return billToCustInfoList.get(0);
    }

    /**
     * Get Ship To Info
     * @param bizMsg NWAL1840CMsg
     * @return Ship To Info
     */
    public static Map<String, String> getShipToInfo(NWAL1840CMsg bizMsg) {

        S21SsmEZDResult ssmResult = NWAL1840QueryForCustomer.getInstance().getShipToCustInfoList(bizMsg);

        if (ssmResult.isCodeNotFound()) {
            bizMsg.shipToCustLocCd.setErrorInfo(1, NWAM0181E, new String[] {MSG_PARAM_SHIP_TO_LOC });
            return null;
        }

        List<Map<String, String>> shipToCustInfoList = (List<Map<String, String>>) ssmResult.getResultObject();
        ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_OFF_N);

        if (shipToCustInfoList.size() != 1) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_ON_Y);
            return null;
        }

        return shipToCustInfoList.get(0);
    }

    /**
     * Set Bill To Info
     * @param bizMsg NWAL1840CMsg
     * @param dsAcctCustTMsg SELL_TO_CUSTTMsg
     * @param nMZC610001PMsg NMZC610001PMsg
     */
    private static void setBillToInfo(NWAL1840CMsg bizMsg, SELL_TO_CUSTTMsg dsAcctCustTMsg, NMZC610001PMsg nMZC610001PMsg) {

        ZYPEZDItemValueSetter.setValue(bizMsg.billToCustAcctCd, dsAcctCustTMsg.sellToCustCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.billToCustAcctNm, dsAcctCustTMsg.dsAcctNm);

        if (nMZC610001PMsg.DefaultBillShipList.getValidCount() == 0) {
            return;
        }

        String defBillToCustCd = nMZC610001PMsg.DefaultBillShipList.no(0).billToCustCd.getValue();
        // 2018/03/09 S21_NA#22387 Mod Start
//        S21SsmEZDResult ssmResult = NWAL1840QueryForCustomer.getInstance().getBillToCustAddr(bizMsg, defBillToCustCd);
        S21SsmEZDResult ssmResult = NWAL1840QueryForCustomer.getInstance().getBillToCustInfoList(bizMsg, defBillToCustCd);
        // 2018/03/09 S21_NA#22387 Mod End
        if (ssmResult.isCodeNotFound()) {
            return;
        }

        // 2018/03/09 S21_NA#22387 Add Start
        List<Map<String, String>> billToCustInfoList = (List<Map<String, String>>) ssmResult.getResultObject();
        Map<String, String> billToCustInfo = billToCustInfoList.get(0);
        // 2018/03/09 S21_NA#22387 Add End

        ZYPEZDItemValueSetter.setValue(bizMsg.billToCustLocCd, defBillToCustCd);
        // 2018/03/09 S21_NA#22387 Mod Start
//        ZYPEZDItemValueSetter.setValue(bizMsg.xxAllLineAddr_BT, (String) ssmResult.getResultObject());
        ZYPEZDItemValueSetter.setValue(bizMsg.xxAllLineAddr_BT, billToCustInfo.get("BILL_TO_ADDR"));
        ZYPEZDItemValueSetter.setValue(bizMsg.billToLocNum, billToCustInfo.get("BILL_TO_LOC_NUM"));
        // 2018/03/09 S21_NA#22387 Mod End
    }

    /**
     * Set Bill To Info
     * @param bizMsg NWAL1840CMsg
     * @param defBillToCustCd Default Bill To Customer Code
     */
    private static void setBillToInfo(NWAL1840CMsg bizMsg, String defBillToCustCd) {

        if (!ZYPCommonFunc.hasValue(defBillToCustCd)) {
            return;
        } else if (ZYPCommonFunc.hasValue(bizMsg.billToCustAcctNm) && ZYPCommonFunc.hasValue(bizMsg.billToCustAcctCd)) {
            return;
        }

        S21SsmEZDResult ssmResult = NWAL1840QueryForCustomer.getInstance().getBillToCustInfoList(bizMsg, defBillToCustCd);

        if (ssmResult.isCodeNotFound()) {
            return;
        }

        List<Map<String, String>> billToCustInfoList = (List<Map<String, String>>) ssmResult.getResultObject();
        Map<String, String> billToCustInfo = billToCustInfoList.get(0);
        ZYPEZDItemValueSetter.setValue(bizMsg.billToCustAcctCd, billToCustInfo.get("SELL_TO_CUST_CD"));
        ZYPEZDItemValueSetter.setValue(bizMsg.billToCustAcctNm, billToCustInfo.get("DS_ACCT_NM"));
        ZYPEZDItemValueSetter.setValue(bizMsg.billToCustLocCd, billToCustInfo.get("BILL_TO_CUST_CD"));
        ZYPEZDItemValueSetter.setValue(bizMsg.xxAllLineAddr_BT, billToCustInfo.get("BILL_TO_ADDR"));
        // 2018/03/09 S21_NA#22387 Add Start
        ZYPEZDItemValueSetter.setValue(bizMsg.billToLocNum, billToCustInfo.get("BILL_TO_LOC_NUM"));
        // 2018/03/09 S21_NA#22387 Add End
    }

    /**
     * Set Ship To Info
     * @param bizMsg NWAL1840CMsg
     * @param nMZC610001PMsg NMZC610001PMsg
     */
    private static void setShipToInfo(NWAL1840CMsg bizMsg, NMZC610001PMsg nMZC610001PMsg) {

        if (nMZC610001PMsg.DefaultBillShipList.getValidCount() == 0) {
            return;
        }

        // 2017/07/27 S21_NA#18243 Add Start
        if (ZYPCommonFunc.hasValue(bizMsg.shipToCustLocCd)) {
            return;
        }
        // 2017/07/27 S21_NA#18243 Add End
        String defShipToCustCd = nMZC610001PMsg.DefaultBillShipList.no(0).shipToCustCd.getValue();
        if (!ZYPCommonFunc.hasValue(defShipToCustCd)) {
            // 2017/07/27 S21_NA#18243 Add Start
            bizMsg.shipToCustLocCd.clear();
            // 2017/07/27 S21_NA#18243 Add End
            return;
        }

        S21SsmEZDResult ssmResult = NWAL1840QueryForCustomer.getInstance().getShipToCustAddr(bizMsg, defShipToCustCd);
        if (ssmResult.isCodeNotFound()) {
            return;
        }

        Map<String, String> shipToCustAddrInfo = (Map<String, String>) ssmResult.getResultObject();
        // 2019/02/01 S21_NA#30097 Addd Start
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustAcctCd, shipToCustAddrInfo.get("DS_ACCT_NUM"));
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustAcctNm, shipToCustAddrInfo.get("DS_ACCT_NM"));
        // 2019/02/01 S21_NA#30097 Add End
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustLocCd, shipToCustAddrInfo.get("SHIP_TO_CUST_CD"));
        ZYPEZDItemValueSetter.setValue(bizMsg.xxAllLineAddr_SH, shipToCustAddrInfo.get("SHIP_TO_ADDR"));
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToLocNm, shipToCustAddrInfo.get("LOC_NM"));
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToAddlLocNm, shipToCustAddrInfo.get("ADDL_LOC_NM"));
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToFirstLineAddr, shipToCustAddrInfo.get("FIRST_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToScdLineAddr, shipToCustAddrInfo.get("SCD_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToThirdLineAddr, shipToCustAddrInfo.get("THIRD_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToFrthLineAddr, shipToCustAddrInfo.get("FRTH_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(bizMsg.shipTo01RefCmntTxt, shipToCustAddrInfo.get("FIRST_REF_CMNT_TXT"));
        ZYPEZDItemValueSetter.setValue(bizMsg.shipTo02RefCmntTxt, shipToCustAddrInfo.get("SCD_REF_CMNT_TXT"));
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToCtyAddr, shipToCustAddrInfo.get("CTY_ADDR"));
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToStCd, shipToCustAddrInfo.get("ST_CD"));
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToPostCd, shipToCustAddrInfo.get("POST_CD"));
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToProvNm, shipToCustAddrInfo.get("PROV_NM"));
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToCtryCd, shipToCustAddrInfo.get("CTRY_CD"));
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToCntyNm, shipToCustAddrInfo.get("CNTY_NM"));
        ZYPEZDItemValueSetter.setValue(bizMsg.locNum, shipToCustAddrInfo.get("LOC_NUM"));
        ZYPEZDItemValueSetter.setValue(bizMsg.dropShipFlg, ZYPConstant.FLG_OFF_N);
        bizMsg.shipToLocNm_DS.clear(); // 2018/02/23 S21_NA#22399 Add
        // 2018/03/09 S21_NA#22387 Add Start
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToLocNum, shipToCustAddrInfo.get("LOC_NUM"));
        // 2018/03/09 S21_NA#22387 Add End
    }

    // 2017/07/27 S21_NA#18243 Add Start
    private static boolean setSoldToInfo(NWAL1840CMsg bizMsg) {

        // 2019/02/01 S21_NA#30097 Mod Start
        //return setSoldToInfo(bizMsg, false, null);
        return setSoldToInfo(bizMsg, false, null, ZYPConstant.FLG_OFF_N);
        // 2019/02/01 S21_NA#30097 Mod End
    }
    // 2017/07/27 S21_NA#18243 Add End

    /**
     * Set Ship To Info
     * @param bizMsg NWAL1840CMsg
     * @param soldToMode true: sold to mode false: ship to mode
     * @param nMZC610001PMsg NMZC610001PMsg
     * @return has API error : false
     */
    // 2019/02/01 S21_NA#30097 Mod Start
    //private static boolean setSoldToInfo(NWAL1840CMsg bizMsg, boolean soldToMode, NMZC610001PMsg nMZC610001PMsg) { // 2017/07/27 S21_NA#18243 change interface
    private static boolean setSoldToInfo(NWAL1840CMsg bizMsg, boolean soldToMode, NMZC610001PMsg nMZC610001PMsg, String xxSelFlg) {
    // 2019/02/01 S21_NA#30097 Mod Start

        bizMsg.xxRqstFlg_SE.clear(); // S21_NA#16410 Add
        if (!soldToMode) { // 2017/07/27 S21_NA#18243 Add Condition
            if (ZYPCommonFunc.hasValue(bizMsg.soldToCustAcctNm) && ZYPCommonFunc.hasValue(bizMsg.sellToCustCd)) {
                return true;
            } else if (!ZYPCommonFunc.hasValue(bizMsg.shipToCustAcctCd)) {
                return true;
            }
        } // 2017/07/27 S21_NA#18243 Add Condition

        if (!soldToMode) { // 2017/07/27 S21_NA#18243 Add Condition
            ZYPEZDItemValueSetter.setValue(bizMsg.sellToCustCd, bizMsg.shipToCustAcctCd);
            ZYPEZDItemValueSetter.setValue(bizMsg.soldToCustAcctNm, bizMsg.shipToCustAcctNm);
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg_SE, ZYPConstant.FLG_ON_Y); // S21_NA#16410 Add
        } // 2017/07/27 S21_NA#18243 Add Condition

        // 2017/07/27 S21_NA#18243 Mod Start
//        // Mod Start S21_NA#8659 2016/09/29
////        NMZC610001PMsg nMZC610001PMsg = callCustInfoGetApiForDefaultMode(bizMsg, bizMsg.sellToCustCd.getValue());
//        NMZC610001PMsg nMZC610001PMsg = callCustInfoGetApiForDefaultMode(bizMsg, bizMsg.sellToCustCd.getValue(), null, bizMsg.soldToCustLocCd.getValue(), NWXC150001DefaultCustomer.MODE_BILL_TO_ACCT);
//        // Mod End S21_NA#8659 2016/09/29
        if (nMZC610001PMsg == null) {
            // 2019/02/01 S21_NA#30097 Mod Start
            //nMZC610001PMsg = callCustInfoGetApiForDefaultMode(bizMsg, bizMsg.sellToCustCd.getValue(), null, bizMsg.soldToCustLocCd.getValue(), NWXC150001DefaultCustomer.MODE_BILL_TO_ACCT);
            nMZC610001PMsg = callCustInfoGetApiForDefaultMode(bizMsg, bizMsg.sellToCustCd.getValue(), null, bizMsg.soldToCustLocCd.getValue(), NWXC150001DefaultCustomer.MODE_BILL_TO_ACCT, xxSelFlg);
            // 2019/02/01 S21_NA#30097 Mod End
        }
        // 2017/07/27 S21_NA#18243 Mod End

        if (nMZC610001PMsg == null) {
            return false;
        }

        if (nMZC610001PMsg.DefaultBillShipList.getValidCount() == 0) {
            return true;
        }

        String defBillToCustCd = nMZC610001PMsg.DefaultBillShipList.no(0).billToCustCd.getValue();
        // 2018/03/09 S21_NA#22387 Mod Start
//        S21SsmEZDResult ssmResult = NWAL1840QueryForCustomer.getInstance().getBillToCustAddr(bizMsg, defBillToCustCd);
        S21SsmEZDResult ssmResult = NWAL1840QueryForCustomer.getInstance().getBillToCustInfoList(bizMsg, defBillToCustCd);
        // 2018/03/09 S21_NA#22387 Mod End
        if (ssmResult.isCodeNotFound()) {
            return true;
        }

        // 2018/03/09 S21_NA#22387 Add Start
        List<Map<String, String>> billToCustInfoList = (List<Map<String, String>>) ssmResult.getResultObject();
        Map<String, String> billToCustInfo = billToCustInfoList.get(0);
        // 2018/03/09 S21_NA#22387 Add End

        ZYPEZDItemValueSetter.setValue(bizMsg.soldToCustLocCd, defBillToCustCd);
        // 2018/03/09 S21_NA#22387 Mod Start
//        ZYPEZDItemValueSetter.setValue(bizMsg.xxAllLineAddr_SE, (String) ssmResult.getResultObject());
        ZYPEZDItemValueSetter.setValue(bizMsg.xxAllLineAddr_SE, billToCustInfo.get("BILL_TO_ADDR"));
        ZYPEZDItemValueSetter.setValue(bizMsg.sellToLocNum, billToCustInfo.get("BILL_TO_LOC_NUM"));

        // 2019/02/01 S21_NA#30097 Add Start
        // Del Start 2019/03/14 QC#30730
        //String sellToCustCd = billToCustInfo.get("SELL_TO_CUST_CD");
        //String soldToCustAcctNm = billToCustInfo.get("DS_ACCT_NM");
        //if (!S21StringUtil.isEquals(bizMsg.sellToCustCd.getValue(), sellToCustCd)) {
        //    ZYPEZDItemValueSetter.setValue(bizMsg.sellToCustCd, sellToCustCd);
        //    ZYPEZDItemValueSetter.setValue(bizMsg.soldToCustAcctNm, soldToCustAcctNm);
        //}
        // Del End 2019/03/14 QC#30730
        // 2019/02/01 S21_NA#30097 Add End

        return true;
    }

    /**
     * Get Direct Sales Account Customer For Name
     * @param bizMsg NWAL1840CMsg
     * @param custNm Customer Name
     * @return Direct Sales Account Customer
     */
    private static SELL_TO_CUSTTMsgArray getDsSlsAcctCustForName(NWAL1840CMsg bizMsg, String custNm) {

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
     * @param bizMsg NWAL1840CMsg
     * @param acctNum Account Number
     * @return Direct Sales Account Customer
     */
    private static SELL_TO_CUSTTMsgArray getDsSlsAcctCustForAcct(NWAL1840CMsg bizMsg, String acctNum) {

        final SELL_TO_CUSTTMsg condition = new SELL_TO_CUSTTMsg();
        // 2018/03/12 S21_NA#24090 Mod Start
        // condition.setSQLID("503");
        condition.setSQLID("507");
        // 2018/03/12 S21_NA#24090 Mod End
        condition.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        condition.setConditionValue("sellToCustCd01", acctNum);
        condition.setConditionValue("rgtnStsCd01", RGTN_STS.READY_FOR_ORDER_TAKING);

        return (SELL_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(condition);
    }

    // Del Start 2016/09/29 S21_NA#8659
//    /**
//     * Call Customer Information Get API For Default Mode
//     * @param bizMsg NWAL1840CMsg
//     * @param dsAcctNum Direct Sales Account Number
//     * @return NMZC610001PMsg
//     */
//    private static NMZC610001PMsg callCustInfoGetApiForDefaultMode(NWAL1840CMsg bizMsg, String dsAcctNum) {
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
//            for (S21ApiMessage msg : msgList) {
//                String msgId = msg.getXxMsgid();
//                String[] msgPrms = msg.getXxMsgPrmArray();
//                bizMsg.setMessageInfo(msgId, msgPrms);
//                return null;
//            }
//        }
//
//        return pMsg;
//    }
    // Del Start 2016/09/29 S21_NA#8659

    // Add Start 2016/09/29 S21_NA#8659
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
     * @return NMZC610001PMsg Default Customer API return value
     */
    // 2019/02/01 S21_NA#30097 Mod Start
    //private static NMZC610001PMsg callCustInfoGetApiForDefaultMode(NWAL1840CMsg bizMsg, String dsAcctNum, String shipToCustCd, String billToCustCd, String xxMode) {
    private static NMZC610001PMsg callCustInfoGetApiForDefaultMode(NWAL1840CMsg bizMsg, String dsAcctNum, String shipToCustCd, String billToCustCd, String xxMode, String xxSelFlg) {
    // 2019/02/01 S21_NA#30097 Mod End

        NWXC150001DefaultCustomerBean paramBean = new NWXC150001DefaultCustomerBean();
        paramBean.setGlblCmpyCd(bizMsg.glblCmpyCd.getValue());
        paramBean.setDsTrxRuleTpCd(getDsTrxRuleTpCd(bizMsg));
        paramBean.setDsAcctNum(dsAcctNum);

        if (ZYPCommonFunc.hasValue(shipToCustCd)) {
            paramBean.setShipToCustCd(shipToCustCd);
        } else if (ZYPCommonFunc.hasValue(billToCustCd)) {
            // 2017/07/27 S21_NA#18243 Add Start
//            paramBean.setBillToCustCd(billToCustCd);
            if (!S21StringUtil.isEquals(NWXC150001DefaultCustomer.MODE_SHIP_ACCT_TO_BILL_TO, xxMode)) {
                paramBean.setBillToCustCd(billToCustCd);
            }
            // 2017/07/27 S21_NA#18243 Add End
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
    // Add End 2016/09/29 S21_NA#8659


    /**
     * Get Direct Sales Transaction Rule Type Code
     * @param bizMsg NWAL1840CMsg
     * @return Direct Sales Transaction Rule Type Code
     */
    private static String getDsTrxRuleTpCd(NWAL1840CMsg bizMsg) {

        ORD_CATG_BIZ_CTXTMsg condition = new ORD_CATG_BIZ_CTXTMsg();
        condition.setSQLID("003");
        condition.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        condition.setConditionValue("ordCatgCtxTpCd01A", EQUIPMENT_ORDER_VALUE_SET);
        condition.setConditionValue("ordCatgCtxTpCd01B", SUPPLIES_ORDER_VALUE_SET);
        // 2019/02/01 S21_NA#30097 Mod Start
        //condition.setConditionValue("dsOrdCatgCd01", bizMsg.schdAgmtCatgCd.getValue());
        condition.setConditionValue("dsOrdCatgCd01", bizMsg.dsOrdCatgCd.getValue());
        // 2019/02/01 S21_NA#30097 Mod End
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
        // 2019/02/01 S21_NA#30097 Mod Start
        //condition.setConditionValue("dsOrdCatgCd01", bizMsg.schdAgmtCatgCd.getValue());
        condition.setConditionValue("dsOrdCatgCd01", bizMsg.dsOrdCatgCd.getValue());
        // 2019/02/01 S21_NA#30097 Mod End

        tmsgArray = (ORD_CATG_BIZ_CTXTMsgArray) EZDTBLAccessor.findByCondition(condition);
        if (tmsgArray.getValidCount() > 0) {
            return tmsgArray.no(0).firstBizCtxAttrbTxt.getValue();
        }

        return BLANK;
    }

    /**
     * Derive Default PO
     * @param bizMsg NWAL1840CMsg
     * @return No API Error : true
     */
    private static boolean deriveDefaultPO(NWAL1840CMsg bizMsg) {

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

            for (S21ApiMessage msg : msgList) {
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
     * @param bizMsg NWAL1840CMsg
     * @param trxRuleTpCd String
     * @return NMZC610001PMsg
     */
    private static NMZC610001PMsg callCustomerInfoGetApi(NWAL1840CMsg bizMsg, String trxRuleTpCd) {

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
     * @param bizMsg NWAL1840CMsg
     * @return Trx Rule Type
     */
    public static String getTrxRuleTp(NWAL1840CMsg bizMsg) {

        ORD_CATG_BIZ_CTXTMsg tMsg = getOrdCatgBizCtx(bizMsg);
        if (tMsg == null) {
            return null;
        }

        return tMsg.firstBizCtxAttrbTxt.getValue();
    }

    /**
     * Get Order Category Business Context
     * @param bizMsg NWAL1840CMsg
     * @return Order Category Business Context
     */
    private static ORD_CATG_BIZ_CTXTMsg getOrdCatgBizCtx(NWAL1840CMsg bizMsg) {

        ORD_CATG_BIZ_CTXTMsg tMsg = new ORD_CATG_BIZ_CTXTMsg();
        tMsg.setSQLID("005");
        tMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        tMsg.setConditionValue("ordCatgCtxTpCd01A", EQUIPMENT_ORDER_VALUE_SET);
        tMsg.setConditionValue("ordCatgCtxTpCd01B", SUPPLIES_ORDER_VALUE_SET);
        // 2019/01/29 QC#30036(QC#11884?) Mod Start
        //tMsg.setConditionValue("dsOrdCatgCd01", bizMsg.schdAgmtCatgCd.getValue());
        tMsg.setConditionValue("dsOrdCatgCd01", bizMsg.dsOrdCatgCd.getValue());
        // 2019/01/29 QC#30036(QC#11884?) Mod End
        tMsg.setConditionValue("dsOrdTpCd01", bizMsg.dsOrdTpCd.getValue());
        ORD_CATG_BIZ_CTXTMsgArray tMsgArray = (ORD_CATG_BIZ_CTXTMsgArray) EZDTBLAccessor.findByCondition(tMsg);

        if (tMsgArray == null || tMsgArray.length() == 0) {
            return null;
        }

        return tMsgArray.no(0);
    }

    // 2018/07/26 QC#14307 Add Start
    /**
     * Init Process Special Instruction
     * @param bizMsg NWAL1840CMsg
     */
    public static void initProcSpecialInstruction(NWAL1840CMsg bizMsg, EZDCStringItem noCheckItem) {

        // Initialize
        bizMsg.sellToCustCd_SP.clear();
        bizMsg.billToCustLocCd_SP.clear();
        bizMsg.shipToCustLocCd_SP.clear();
        
        // set Backup Item
        ZYPEZDItemValueSetter.setValue(bizMsg.sellToCustCd_BK, bizMsg.sellToCustCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.billToCustLocCd_BK, bizMsg.billToCustLocCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustLocCd_BK, bizMsg.shipToCustLocCd);
        
        // noCheckItem setting
        if (noCheckItem != null) {
            noCheckItem.clear();
        }
    }

    /**
     * Common Process For Special Instruction
     * @param bizMsg NWAL1840CMsg
     */
    public static boolean cmnProcForSpecialInstruction(NWAL1840CMsg bizMsg) {

        boolean isDisplay = false;

        String glblCmpyCd = bizMsg.glblCmpyCd.getValue();
        String trxRuleTp = NWAL1840CommonLogicForCustomer.getTrxRuleTp(bizMsg);
        // 2018/07/26 QC#26661,26713 Add Start
        String funcCatgId = NWXC150001DsCheck.getOrdCatgBizCtxFstAttbTxt(bizMsg.glblCmpyCd.getValue(), ORD_CATG_CTX_TP.CUST_SPCL_ORDER_CATEGORY, bizMsg.dsOrdCatgCd.getValue(), bizMsg.dsOrdTpCd.getValue());
        // 2018/07/26 QC#26661,26713 Add End

        // Check display NMAL6760
        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxRqstFlg)) {
            // NMAL6760 param set
            ZYPEZDItemValueSetter.setValue(bizMsg.xxPopPrm_0A, trxRuleTp);
            // 2018/07/26 QC#26661,26713 Add Start
            ZYPEZDItemValueSetter.setValue(bizMsg.xxPopPrm_0C, funcCatgId);
            // 2018/07/26 QC#26661,26713 Add End
            return isDisplay;
        }

        // Check common function result
        // Sold To
        if (ZYPCommonFunc.hasValue(bizMsg.sellToCustCd) && 
            !S21StringUtil.isEquals(bizMsg.sellToCustCd.getValue(), bizMsg.sellToCustCd_BK.getValue())) {

            if (NWXC150001DsCheck.spclInstnDisplayDetermination(
                    // 2018/07/26 QC#26661,26713 Mod Start
                    glblCmpyCd, trxRuleTp, null, bizMsg.sellToCustCd.getValue(), null, null, BIZ_ID, funcCatgId, bizMsg.lineBizTpCd.getValue())){
                    // 2018/07/26 QC#26661,26713 Mod End
                ZYPEZDItemValueSetter.setValue(bizMsg.sellToCustCd_SP, bizMsg.sellToCustCd);
                isDisplay = true;
            }
        }

        // Bill To
        if (ZYPCommonFunc.hasValue(bizMsg.billToCustLocCd) && 
            !S21StringUtil.isEquals(bizMsg.billToCustLocCd.getValue(), bizMsg.billToCustLocCd_BK.getValue())) {

            if (NWXC150001DsCheck.spclInstnDisplayDetermination(
                    // 2018/07/26 QC#26661,26713 Mod Start
                    glblCmpyCd, trxRuleTp, null, null, bizMsg.billToCustLocCd.getValue(), null, BIZ_ID, funcCatgId, bizMsg.lineBizTpCd.getValue())){
                    // 2018/07/26 QC#26661,26713 Mod End
                ZYPEZDItemValueSetter.setValue(bizMsg.billToCustLocCd_SP, bizMsg.billToCustLocCd);
                isDisplay = true;
            }
        }

        // Ship To
        if (ZYPCommonFunc.hasValue(bizMsg.shipToCustLocCd) && 
            !S21StringUtil.isEquals(bizMsg.shipToCustLocCd.getValue(), bizMsg.shipToCustLocCd_BK.getValue())) {

            if (NWXC150001DsCheck.spclInstnDisplayDetermination(
                    // 2018/07/26 QC#26661,26713 Mod Start
                    glblCmpyCd, trxRuleTp, null, null, null, bizMsg.shipToCustLocCd.getValue(), BIZ_ID, funcCatgId, bizMsg.lineBizTpCd.getValue())){
                    // 2018/07/26 QC#26661,26713 Mod End
                ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustLocCd_SP, bizMsg.shipToCustLocCd);
                isDisplay = true;
            }
        }

        if (isDisplay) {
            // NMAL3300 param set
            // 2018/07/26 QC#26661,26713 Add Start
            ZYPEZDItemValueSetter.setValue(bizMsg.xxPopPrm_02, funcCatgId);
            // 2018/07/26 QC#26661,26713 Add End
            ZYPEZDItemValueSetter.setValue(bizMsg.xxPopPrm_03, trxRuleTp);
        }
        return isDisplay;
    }
    // 2018/07/26 QC#14307 Add End

    // 2018/07/26 QC#26661,26713 Add Start
    /**
     * Set Account Search Popup Parameter
     * @param bizMsg NWAL1840CMsg
     */
    public static void setAccountSearchPopupParam(NWAL1840CMsg bizMsg) {

        // Transaction Type
        String trxRuleTp = NWAL1840CommonLogicForCustomer.getTrxRuleTp(bizMsg);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxPopPrm_0A, trxRuleTp);

        // Function Category ID
        String funcCatgId = NWXC150001DsCheck.getOrdCatgBizCtxFstAttbTxt(bizMsg.glblCmpyCd.getValue(), ORD_CATG_CTX_TP.CUST_SPCL_ORDER_CATEGORY, bizMsg.dsOrdCatgCd.getValue(), bizMsg.dsOrdTpCd.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.xxPopPrm_0C, funcCatgId);
    }

    // 2018/07/26 QC#26661,26713 Add End

    // Add Start 2018/11/21 QC#28683
    /**
     * setParamForNMAL6760SpecialInstruction
     * @param bizMsg NWAL1770CMsg
     */
    public static void setParamForNMAL6760SpecialInstruction(NWAL1840CMsg bizMsg) {
        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxRqstFlg.getValue())) {
            // display NMAL6760
            String glblCmpyCd = bizMsg.glblCmpyCd.getValue();
            String dsOrdCatgCd = bizMsg.dsOrdCatgCd.getValue();
            String dsOrdTpCd = bizMsg.dsOrdTpCd.getValue();

            String trxRuleTp = NWAL1840CommonLogicForCustomer.getTrxRuleTp(bizMsg);
            String funcCatgId = NWXC150001DsCheck.getOrdCatgBizCtxFstAttbTxt(glblCmpyCd, ORD_CATG_CTX_TP.CUST_SPCL_ORDER_CATEGORY, dsOrdCatgCd, dsOrdTpCd);

            // NMAL6760 param set
            ZYPEZDItemValueSetter.setValue(bizMsg.xxPopPrm_0A, trxRuleTp);
            ZYPEZDItemValueSetter.setValue(bizMsg.xxPopPrm_0C, funcCatgId);
        }
    }
    // Add End 2018/11/21 QC#28683

}
