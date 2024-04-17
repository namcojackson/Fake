/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1770.common;

import static business.blap.NWAL1770.constant.NWAL1770Constant.DROP_SHIP_RTL_WH_CD;
import static business.blap.NWAL1770.constant.NWAL1770MsgConstant.MSG_PARAM_FRT_TERMS;
import static business.blap.NWAL1770.constant.NWAL1770MsgConstant.MSG_PARAM_ITEM;
import static business.blap.NWAL1770.constant.NWAL1770MsgConstant.MSG_PARAM_SHPG_SVC_LVL;
import static business.blap.NWAL1770.constant.NWAL1770MsgConstant.MSG_PARAM_SUPER_SEDE_API;
import static business.blap.NWAL1770.constant.NWAL1770MsgConstant.NWAM0181E;
import static business.blap.NWAL1770.constant.NWAL1770MsgConstant.NWAM0189E;
import static business.blap.NWAL1770.constant.NWAL1770MsgConstant.NWAM8473W;
import static business.blap.NWAL1770.constant.NWAL1770MsgConstant.NWZM1530E;
import static business.blap.NWAL1770.constant.NWAL1770MsgConstant.NWZM2008E;
import static business.blap.NWAL1770.constant.NWAL1770MsgConstant.ZZM9000E;
import static business.blap.NWAL1770.constant.NWAL1770MsgConstant.ZZZM9001E;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import parts.common.EZDMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NWAL1770.NWAL1770CMsg;
import business.blap.NWAL1770.NWAL1770QueryForItemLine;
import business.blap.NWAL1770.NWAL1770QueryForSearch;
import business.blap.NWAL1770.NWAL1770SMsg;
import business.blap.NWAL1770.NWAL1770_BCMsg;
import business.blap.NWAL1770.NWAL1770_BCMsgArray;
import business.blap.NWAL1770.NWAL1770_ECMsg;
import business.db.ALL_MDSE_VTMsg;
import business.db.ALL_MDSE_VTMsgArray;
import business.db.CUST_MDSE_XREFTMsg;
import business.db.CUST_MDSE_XREFTMsgArray;
import business.db.MDSETMsg;
import business.db.MDSE_STORE_PKGTMsg;
import business.db.ORD_TAKE_MDSETMsg;
import business.db.ORD_TAKE_MDSETMsgArray;
import business.parts.NWZC157001PMsg;
import business.parts.NWZC157002PMsg;
import business.parts.NWZC157003PMsg;
import business.parts.NWZC157004PMsg;
import business.parts.NWZC192001PMsg;

import com.canon.cusa.s21.api.NWZ.NWZC192001.NWZC192001;
import com.canon.cusa.s21.common.NWX.NWXC004001.NWXMdseTMsgThreadLocalCache;
import com.canon.cusa.s21.common.NWX.NWXC150001.NWXC150001DsCheck;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_DTL_GRP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/19   Fujitsu         T.Yoshida       Create          N/A
 * 2016/05/16   Fujitsu         T.Murai         Update          S21_NA#7861
 * 2016/08/10   Fujitsu         S.Iidaka        Update          S21_NA#13173
 * 2016/08/22   Fujitsu         H.Ikeda         Update          S21_NA#11655
 * 2016/09/13   Fujitsu         H.Ikeda         Update          S21_NA#11655
 * 2016/09/29   Fujitsu         H.Ikeda         Update          S21_NA#11655
 * 2017/03/02   Fujitsu         M.Ohno          Update          S21_NA#16800
 * 2017/09/20   Fujitsu         S.Ohki          Update          S21_NA#21068
 * 2017/10/03   Fujitsu         S.Ohki          Update          S21_NA#21068-2
 * 2017/10/18   Hitachi         J.Kim           Update          QC#21067
 * 2017/10/26   Fujitsu         R.Nakamura      Update          S21_NA#20304
 * 2018/02/06   Fujitsu         S.Ohki          Update          S21_NA#20173(Sol#453)
 * 2018/02/13   Fujitsu         T.Aoi           Update          S21_NA#21165
 * 2018/04/03   Fujitsu         K.Ishizuka      Update          S21_NA#25209
 * 2018/07/19   Fujitsu         M.Ishii         Update          S21_NA#26153
 * 2018/09/18   Fujitsu         S.Kosaka        Update          QC#9700
 * 2018/12/12   Fujitsu         K.Kato          Update          QC#229315
 * 2019/01/05   Fujitsu         Y.Kanefusa      Update          S21_NA#29824
 * 2023/07/20   Hitachi         T.Fukuta        Update          CSA-QC#61467
 * </pre>
 */
public class NWAL1770CommonLogicForItemLine {

    /**
     * Check Merchandise Code
     * @param bizMsg NWAL1770CMsg
     * @return Checked Merchandise Code
     */
    public static String checkMdseCd(NWAL1770CMsg bizMsg) {

        int slctLine = bizMsg.xxCellIdx.getValueInt();
        NWAL1770_BCMsg itemLineMsg = bizMsg.B.no(slctLine);

        ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_OFF_N);
        // Add Start 2016/09/29 S21_NA#11655
        ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg_MN, ZYPConstant.FLG_OFF_N);
        String baseMdseCd = "";
        // Add End   2016/09/29 S21_NA#11655
        String inputMdseCdValue = itemLineMsg.mdseCd_B.getValue();

        ALL_MDSE_VTMsgArray allMdseViewArray = getAllMdseViewArray(bizMsg, inputMdseCdValue);
        if (allMdseViewArray.getValidCount() == 1) {
            // Mod Start 2016/09/29 S21_NA#11655
            //String mdseCd = allMdseViewArray.no(0).mdseCd.getValue();
            //ZYPEZDItemValueSetter.setValue(itemLineMsg.custMdseCd_B, getCustMdseCd(bizMsg, mdseCd));
            //ZYPEZDItemValueSetter.setValue(itemLineMsg.mnfItemCd_B, getMnfItemCd(bizMsg, mdseCd));
            //ZYPEZDItemValueSetter.setValue(itemLineMsg.origMdseCd_B, mdseCd);
            //return mdseCd;
            baseMdseCd = allMdseViewArray.no(0).mdseCd.getValue();
            ZYPEZDItemValueSetter.setValue(itemLineMsg.custMdseCd_B, getCustMdseCd(bizMsg, baseMdseCd));
            ZYPEZDItemValueSetter.setValue(itemLineMsg.mnfItemCd_B, getMnfItemCd(bizMsg, baseMdseCd));
            ZYPEZDItemValueSetter.setValue(itemLineMsg.origMdseCd_B, baseMdseCd);
            return baseMdseCd;
            // Mod End 2016/09/29 S21_NA#11655
        } else if (allMdseViewArray.getValidCount() > 1) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_ON_Y);
            return null;
        }

        CUST_MDSE_XREFTMsgArray custMdseXrefArray = getCustMdseXrefArray(bizMsg, inputMdseCdValue);
        if (custMdseXrefArray.getValidCount() == 1) {
            // Mod Start 2016/09/29 S21_NA#11655
            //String baseMdseCd = custMdseXrefArray.no(0).mdseCd.getValue();
            baseMdseCd = custMdseXrefArray.no(0).mdseCd.getValue();
            // Mod Start 2016/09/29 S21_NA#11655
            ZYPEZDItemValueSetter.setValue(itemLineMsg.custMdseCd_B, inputMdseCdValue);
            ZYPEZDItemValueSetter.setValue(itemLineMsg.mnfItemCd_B, getMnfItemCd(bizMsg, baseMdseCd));
            ZYPEZDItemValueSetter.setValue(itemLineMsg.origMdseCd_B, inputMdseCdValue);
            // Add Start 2016/09/13 S21_NA#11655
            baseMdseCd = chgBaseMdseCd(bizMsg.glblCmpyCd.getValue(), custMdseXrefArray.no(0).mdseCd.getValue());
            // Add End   2016/09/13 S21_NA#11655
            ZYPEZDItemValueSetter.setValue(itemLineMsg.mdseCd_B, baseMdseCd);
            return baseMdseCd;
        } else if (custMdseXrefArray.getValidCount() > 1) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_ON_Y);
            return null;
        }

        S21SsmEZDResult ssmResult = NWAL1770QueryForItemLine.getInstance().getBaseMdseCdFromMnfItemCd(bizMsg, inputMdseCdValue);
        if (ssmResult.isCodeNotFound()) {
            itemLineMsg.mdseCd_B.setErrorInfo(1, NWAM0181E, new String[] {MSG_PARAM_ITEM });
            return null;
        // Mod Start 2016/09/29 S21_NA#11655
        //} else if (ssmResult.getQueryResultCount() > 1) {
        //    ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg_MN, ZYPConstant.FLG_ON_Y);
        //    return null;
        }
        List<Map<String, Object>> mnfItemList = (List<Map<String, Object>>) ssmResult.getResultObject();
        if (mnfItemList.size() > 1) {
            Set<String> mdseSet = new HashSet<String>();
            for (Map<String, Object> mnfItem : mnfItemList) {
                String ordTakeMdseCd = (String) mnfItem.get("ORD_TAKE_MDSE_CD");
                if (ZYPCommonFunc.hasValue(ordTakeMdseCd)) {
                    mdseSet.add(ordTakeMdseCd);
                } else {
                    mdseSet.add((String) mnfItem.get("MDSE_CD"));
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
        //String baseMdseCd = (String) ssmResult.getResultObject();
        // Mod End 2016/09/29 S21_NA#11655
        ZYPEZDItemValueSetter.setValue(itemLineMsg.custMdseCd_B, getCustMdseCd(bizMsg, baseMdseCd));
        ZYPEZDItemValueSetter.setValue(itemLineMsg.mnfItemCd_B, inputMdseCdValue);
        ZYPEZDItemValueSetter.setValue(itemLineMsg.origMdseCd_B, inputMdseCdValue);
        // QC#11655 2016/08/22 Mod Start
        ORD_TAKE_MDSETMsgArray tMsgArray = getOrdTakeMdse(bizMsg.glblCmpyCd.getValue(), baseMdseCd);
        if (tMsgArray != null && tMsgArray.getValidCount() > 0) {
            baseMdseCd = tMsgArray.no(0).ordTakeMdseCd.getValue();
        }
        // QC#11655 2016/08/22 Mod end
        ZYPEZDItemValueSetter.setValue(itemLineMsg.mdseCd_B, baseMdseCd);

        return baseMdseCd;
    }

    // QC#11655 2016/09/13 Add Start
    private static String chgBaseMdseCd(String glblCmpyCd, String baseMdseCd){
        if (baseMdseCd.length() > 8) {
            String mdseCd = baseMdseCd.substring(0,8);
            ORD_TAKE_MDSETMsgArray tMsgArray = getOrdTakeMdse2(glblCmpyCd, mdseCd);
            if (tMsgArray != null && tMsgArray.getValidCount() > 0) {
                baseMdseCd = tMsgArray.no(0).ordTakeMdseCd.getValue();
            }
        }
        return baseMdseCd;
    }
    // QC#11655 2016/09/13 Add End

    // QC#11655 2016/08/22 Add Start
    /**
     * Get Ord Take Mdse
     * @param glblCmpyCd String
     * @param mdseCd Merchandise Code
     * @return ALL_MDSE_V Array
     */
    private static ORD_TAKE_MDSETMsgArray getOrdTakeMdse(String glblCmpyCd, String mdseCd) {

        final ORD_TAKE_MDSETMsg condition = new ORD_TAKE_MDSETMsg();
        condition.setSQLID("001");
        
        condition.setConditionValue("glblCmpyCd01", glblCmpyCd);
        condition.setConditionValue("mdseCd01", mdseCd);

        return (ORD_TAKE_MDSETMsgArray) EZDTBLAccessor.findByCondition(condition);
    }

    /**
     * Get Ord Take Mdse
     * @param glblCmpyCd String
     * @param mdseCd Merchandise Code
     * @return ALL_MDSE_V Array
     */
    private static ORD_TAKE_MDSETMsgArray getOrdTakeMdse2(String glblCmpyCd, String mdseCd) {

        final ORD_TAKE_MDSETMsg condition = new ORD_TAKE_MDSETMsg();
        condition.setSQLID("002");
        
        condition.setConditionValue("glblCmpyCd01", glblCmpyCd);
        condition.setConditionValue("ordTakeMdseCd01", mdseCd);

        return (ORD_TAKE_MDSETMsgArray) EZDTBLAccessor.findByCondition(condition);
    }
    // QC#11655 2016/08/22 Add End

    /**
     * Get ALL_MDSE_V Array
     * @param bizMsg NWAL1770CMsg
     * @param inputMdseCdValue Input Merchandise Code
     * @return ALL_MDSE_V Array
     */
    private static ALL_MDSE_VTMsgArray getAllMdseViewArray(NWAL1770CMsg bizMsg, String inputMdseCdValue) {

        final ALL_MDSE_VTMsg condition = new ALL_MDSE_VTMsg();
        condition.setSQLID("002");
        condition.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        condition.setConditionValue("mdseCd01", inputMdseCdValue);
        condition.setConditionValue("rgtnStsCd01", RGTN_STS.READY_FOR_ORDER_TAKING);
        return (ALL_MDSE_VTMsgArray) EZDTBLAccessor.findByCondition(condition);
    }

    /**
     * Get CUST_MDSE_XREF Array
     * @param bizMsg NWAL1770CMsg
     * @param inputMdseCdValue Input Merchandise Code
     * @return CUST_MDSE_XREF Array
     */
    private static CUST_MDSE_XREFTMsgArray getCustMdseXrefArray(NWAL1770CMsg bizMsg, String inputMdseCdValue) {

        final CUST_MDSE_XREFTMsg condition = new CUST_MDSE_XREFTMsg();
        condition.setSQLID("001");
        condition.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        condition.setConditionValue("custMdseCd01", inputMdseCdValue);
        condition.setConditionValue("dsAcctNum01", bizMsg.sellToCustCd.getValue());
        condition.setConditionValue("custMdseXrefEnblFlg01", ZYPConstant.FLG_ON_Y);
        return (CUST_MDSE_XREFTMsgArray) EZDTBLAccessor.findByCondition(condition);
    }

    /**
     * Get Customer MDSE Code
     * @param bizMsg NWAL1770CMsg
     * @param mdseCd Merchandise Code
     * @return Customer MDSE Code
     */
    private static String getCustMdseCd(NWAL1770CMsg bizMsg, String mdseCd) {

        MDSETMsg mdseTMsg = NWXMdseTMsgThreadLocalCache.getInstance().get(bizMsg.glblCmpyCd.getValue(), mdseCd);
        if (mdseTMsg == null) {
            return null;
        }

        final CUST_MDSE_XREFTMsg condition = new CUST_MDSE_XREFTMsg();
        condition.setSQLID("002");
        condition.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        condition.setConditionValue("mdseCd01", mdseTMsg.mdseCd.getValue());
        condition.setConditionValue("dsAcctNum01", bizMsg.sellToCustCd.getValue());
        condition.setConditionValue("custMdseXrefEnblFlg01", ZYPConstant.FLG_ON_Y);
        CUST_MDSE_XREFTMsgArray tMsgArray = (CUST_MDSE_XREFTMsgArray) EZDTBLAccessor.findByCondition(condition);

        if (tMsgArray == null || tMsgArray.getValidCount() == 0) {
            return null;
        }

        return tMsgArray.no(0).custMdseCd.getValue();
    }

    /**
     * Get Manufacturer Item Code
     * @param bizMsg NWAL1770CMsg
     * @param mdseCd Merchandise Code
     * @return Manufacturer Item Code
     */
    private static String getMnfItemCd(NWAL1770CMsg bizMsg, String mdseCd) {

        MDSETMsg mdseTMsg = NWXMdseTMsgThreadLocalCache.getInstance().get(bizMsg.glblCmpyCd.getValue(), mdseCd);
        if (mdseTMsg == null) {
            return null;
        }

        S21SsmEZDResult ssmResult = NWAL1770QueryForItemLine.getInstance().getMnfItemCd(bizMsg, mdseTMsg.mdseCd.getValue());
        if (ssmResult.isCodeNotFound()) {
            return null;
        }

        return (String) ssmResult.getResultObject();
    }

    /**
     * Derive Item Line Information
     * @param bizMsg NWAL1770CMsg
     * @param baseMdseTMsg MDSETMsg
     */
    public static void deriveItemLineInfo(NWAL1770CMsg bizMsg, String inputMdseCd, MDSETMsg baseMdseTMsg) {

        S21SsmEZDResult ssmResult = NWAL1770QueryForItemLine.getInstance().getPkgUomCd(bizMsg, baseMdseTMsg.mdseCd.getValue());
        String pkgUomCd = (String) ssmResult.getResultObject();

        NWAL1770_BCMsg itemLineMsg = bizMsg.B.no(bizMsg.xxCellIdx.getValueInt());
        ZYPEZDItemValueSetter.setValue(itemLineMsg.mdseCd_B, inputMdseCd);
        ZYPEZDItemValueSetter.setValue(itemLineMsg.mdseNm_B, baseMdseTMsg.mdseNm);
        ZYPEZDItemValueSetter.setValue(itemLineMsg.mdseDescShortTxt_B, getMdseShortNm(bizMsg, baseMdseTMsg));
        NWAL1770CommonLogic.createPkgUomPullDown(bizMsg, itemLineMsg);
        ZYPEZDItemValueSetter.setValue(itemLineMsg.custUomCd_B, pkgUomCd);
        ZYPEZDItemValueSetter.setValue(itemLineMsg.dealPrcListPrcAmt_B, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(itemLineMsg.dealSplyQuoteDtlSlsAmt_B, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(itemLineMsg.prcCatgCd_B, bizMsg.prcCatgCd);
        ZYPEZDItemValueSetter.setValue(itemLineMsg.prcCatgNm_B, bizMsg.prcCatgNm);
        ZYPEZDItemValueSetter.setValue(itemLineMsg.xxTotDiscAmt_B, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(itemLineMsg.xxSubTotCalcPrcAmt_B, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(itemLineMsg.xxTotFrtAmt_B, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(itemLineMsg.xxTotTaxAmt_B, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(itemLineMsg.xxTotAmt_B, BigDecimal.ZERO);
        String primaryLineCatgCd = NWAL1770CommonLogic.createLineCatgPulldown(bizMsg, bizMsg.slsDt.getValue());
        ZYPEZDItemValueSetter.setValue(itemLineMsg.dsOrdLineCatgCd_B, primaryLineCatgCd);
        itemLineMsg.rtlWhCd_B.clear();
        itemLineMsg.rtlWhNm_B.clear();
        itemLineMsg.rtlSwhCd_B.clear();
        itemLineMsg.rtlSwhNm_B.clear();
        itemLineMsg.splyQuoteLineStsCd_B.clear();
        ZYPEZDItemValueSetter.setValue(itemLineMsg.setItemShipCpltFlg_B, ZYPConstant.FLG_OFF_N);
    }

    /**
     * Set Each Quantity
     * @param bizMsg NWAL1770CMsg
     * @param mdseCd Merchandise Code
     */
    public static void setEachQty(NWAL1770CMsg bizMsg, String mdseCd) {

        NWAL1770_BCMsg itemLineMsg = bizMsg.B.no(bizMsg.xxCellIdx.getValueInt());
        if (ZYPCommonFunc.hasValue(itemLineMsg.ordCustUomQty_B)) {
            ZYPEZDItemValueSetter.setValue(itemLineMsg.ordQty_B, getEachQty(bizMsg, mdseCd, itemLineMsg.custUomCd_B.getValue(), itemLineMsg.ordCustUomQty_B.getValue()));
        }
    }

    /**
     * Get Each Quantity
     * @param bizMsg NWAL1770CMsg
     * @param mdseCd Merchandise Code
     * @param pkgUomCd Unit of Measure Code
     * @param uomQty Unit of Measure uomQty
     * @return Each Quantity
     */
    public static BigDecimal getEachQty(NWAL1770CMsg bizMsg, String mdseCd, String pkgUomCd, BigDecimal uomQty) {

        MDSE_STORE_PKGTMsg tMsg = new MDSE_STORE_PKGTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.mdseCd, mdseCd);
        ZYPEZDItemValueSetter.setValue(tMsg.pkgUomCd, pkgUomCd);
        tMsg = (MDSE_STORE_PKGTMsg) S21FastTBLAccessor.findByKey(tMsg);

        if (tMsg == null) {
            return BigDecimal.ZERO;
        }

        return uomQty.multiply(tMsg.inEachQty.getValue());
    }

    // QC#6480 2016/06/16 Mod Start
    /**
     * Derive Line Price
     * @param bizMsg NWAL1770CMsg
     * @param bizMsg NWAL1770CMsg
     */
    public static void deriveLinePrice(NWAL1770CMsg bizMsg) {
        deriveLinePrice(bizMsg, true);
    }
    // QC#6480 2016/06/16 Mod End

    /**
     * Derive Line Price
     * @param bizMsg NWAL1770CMsg
     * @param delFlg boolean
     */
    // QC#6480 2016/06/16 Mod Start
    // public static void deriveLinePrice(NWAL1770CMsg bizMsg) {
    public static void deriveLinePrice(NWAL1770CMsg bizMsg, boolean delFlg) {
    // QC#6480 2016/06/16 Mod End

        NWAL1770_BCMsg itemLineMsg = bizMsg.B.no(bizMsg.xxCellIdx.getValueInt());
        if (!ZYPCommonFunc.hasValue(itemLineMsg.ordCustUomQty_B)) {
            return;
        }

        NWAL1770CommonLogic.numberingQuoteLineNumber(bizMsg);
        // QC#6480 2016/06/16 Mod Start
        // delSlctLineCalcBase(bizMsg);
        if (delFlg) {
            itemLineMsg.dealSplyQuoteDtlSlsAmt_B.clear();
            delSlctLineCalcBase(bizMsg);
        }
        // QC#6480 2016/06/16 Mod End

        String slctQuoteDtlLineNum = itemLineMsg.splyQuoteDtlLineNum_B.getValue();
        String slctQuoteDtlLineSubNum = itemLineMsg.splyQuoteDtlLineSubNum_B.getValue();

        // Call NWZC1570 Pricing API
        NWZC157001PMsg prcApiPMsg = NWAL1770CommonLogicForPricing.callPricingApiOfGetBasePriceMode(bizMsg);
        if (!S21ApiUtil.isXxMsgId(prcApiPMsg)) {
            // Set Calc Base
            NWZC157002PMsg prcLinePMsg = prcApiPMsg.NWZC157002PMsg.no(0);
            if (!S21ApiUtil.isXxMsgId(prcLinePMsg)) {
                // int validCnt = bizMsg.E.getValidCount(); // QC#29824 2019/01/05 Del
                BigDecimal baseAutoPrcAmtRate = null;
                boolean isExistBasePrc = false;

                delSlctLineCalcBase(bizMsg); // QC#6480 2016/06/16 Add
                int validCnt = bizMsg.E.getValidCount(); // QC#29824 2019/01/05 Add
                for (int i = 0; i < prcLinePMsg.NWZC157003PMsg.getValidCount(); i++) {
                    NWZC157003PMsg prcElementPMsg = prcLinePMsg.NWZC157003PMsg.no(i);
                    NWAL1770_ECMsg calcBaseMsg = bizMsg.E.no(validCnt);
                    ZYPEZDItemValueSetter.setValue(calcBaseMsg.xxLineNum_E, itemLineMsg.xxLineNum_B);
                    ZYPEZDItemValueSetter.setValue(calcBaseMsg.xxCellIdx_E, new BigDecimal(i));
                    ZYPEZDItemValueSetter.setValue(calcBaseMsg.splyQuoteDtlLineNum_E, slctQuoteDtlLineNum);
                    ZYPEZDItemValueSetter.setValue(calcBaseMsg.splyQuoteDtlLineSubNum_E, slctQuoteDtlLineSubNum);
                    ZYPEZDItemValueSetter.setValue(calcBaseMsg.prcCondTpCd_E, prcElementPMsg.prcCondTpCd);
                    ZYPEZDItemValueSetter.setValue(calcBaseMsg.prcCondTpDescTxt_E, prcElementPMsg.prcCondTpDescTxt);
                    ZYPEZDItemValueSetter.setValue(calcBaseMsg.prcDtlGrpCd_E, prcElementPMsg.prcDtlGrpCd);
                    ZYPEZDItemValueSetter.setValue(calcBaseMsg.prcJrnlGrpCd_E, prcElementPMsg.prcJrnlGrpCd);
                    ZYPEZDItemValueSetter.setValue(calcBaseMsg.prcCatgCd_E, prcElementPMsg.prcCatgCd);
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
                    ZYPEZDItemValueSetter.setValue(calcBaseMsg.ordPrcCalcBasePk_E, prcElementPMsg.ordPrcCalcBasePk);
                    // QC#9700  2018/09/18 Add Start
                    ZYPEZDItemValueSetter.setValue(calcBaseMsg.prcRuleApplyFlg_E, prcElementPMsg.prcRuleApplyFlg);
                    ZYPEZDItemValueSetter.setValue(calcBaseMsg.prcRulePrcdPk_E, prcElementPMsg.prcRulePrcdPk);
                    // QC#9700  2018/09/18 Add End

                    if (PRC_DTL_GRP.BASE_PRICE.equals(prcElementPMsg.prcDtlGrpCd.getValue())) {
                        baseAutoPrcAmtRate = prcElementPMsg.autoPrcAmtRate.getValue();
                        isExistBasePrc = true;
                    }
                    validCnt++;
                }
                bizMsg.E.setValidCount(validCnt);

                // Set Field Amount
                NWZC157004PMsg prcTotalPMsg = prcApiPMsg.NWZC157004PMsg.no(0);
                // QC#6480 2016/06/16 Mod Start
                // ZYPEZDItemValueSetter.setValue(itemLineMsg.dealPrcListPrcAmt_B,
                // prcTotalPMsg.xxUnitGrsPrcAmt);
                // ZYPEZDItemValueSetter.setValue(itemLineMsg.dealSplyQuoteDtlNetAmt_B,
                // prcTotalPMsg.xxUnitNetPrcAmt);
                // if (isExistBasePrc) {
                // ZYPEZDItemValueSetter.setValue(itemLineMsg.dealSplyQuoteDtlSlsAmt_B,
                // baseAutoPrcAmtRate);
                // ZYPEZDItemValueSetter.setValue(itemLineMsg.xxDtlDiscAmt_B, baseAutoPrcAmtRate);
                // }
                // ZYPEZDItemValueSetter.setValue(itemLineMsg.xxTotDiscAmt_B,
                // prcTotalPMsg.xxTotDiscAmt);
                ZYPEZDItemValueSetter.setValue(itemLineMsg.dealSplyQuoteDtlSlsAmt_B, prcTotalPMsg.xxUnitGrsPrcAmt);
                ZYPEZDItemValueSetter.setValue(itemLineMsg.dealSplyQuoteDtlNetAmt_B, prcTotalPMsg.xxUnitNetPrcAmt);
                ZYPEZDItemValueSetter.setValue(itemLineMsg.xxDtlDiscAmt_B, prcTotalPMsg.xxUnitNetPrcAmt);
                if (isExistBasePrc) {
                    ZYPEZDItemValueSetter.setValue(itemLineMsg.dealPrcListPrcAmt_B, baseAutoPrcAmtRate);
                }
                ZYPEZDItemValueSetter.setValue(itemLineMsg.xxDtlDiscAmt_B, prcTotalPMsg.xxUnitNetPrcAmt);
                ZYPEZDItemValueSetter.setValue(itemLineMsg.xxTotDiscAmt_B, prcTotalPMsg.xxUnitGrsPrcAmt.getValue().subtract(prcTotalPMsg.xxUnitNetPrcAmt.getValue()));
                ZYPEZDItemValueSetter.setValue(itemLineMsg.xxSubTotCalcPrcAmt_B, prcTotalPMsg.xxTotNetPrcAmt);
                // QC#6480 2016/06/16 Mod End
                ZYPEZDItemValueSetter.setValue(itemLineMsg.xxTotFrtAmt_B, prcTotalPMsg.xxTotFrtAmt);
                ZYPEZDItemValueSetter.setValue(itemLineMsg.xxTotTaxAmt_B, prcTotalPMsg.xxTotTaxAmt);
                ZYPEZDItemValueSetter.setValue(itemLineMsg.xxTotAmt_B, prcTotalPMsg.xxTotAmt);

                // Set Sub Total
                // QC#6480 2016/06/16 Del Start
                // BigDecimal ordQty = itemLineMsg.ordQty_B.getValue();
                // ZYPEZDItemValueSetter.setValue(itemLineMsg.xxSubTotCalcPrcAmt_B,
                // ordQty.multiply(itemLineMsg.dealSplyQuoteDtlSlsAmt_B.getValue()));
                // QC#6480 2016/06/16 Del End

            // S21_NA#13173 Add START
            } else {
                List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(prcLinePMsg);
                for (int i = 0; i < msgList.size(); i++) {
                    S21ApiMessage msg = msgList.get(i);
                    String msgId = msg.getXxMsgid();
                    String[] msgPrms = msg.getXxMsgPrmArray();
                    bizMsg.setMessageInfo(msgId, msgPrms);
                }
                ZYPEZDItemValueSetter.setValue(itemLineMsg.xxErrFlg_B, prcLinePMsg.xxErrFlg);
                itemLineMsg.dealSplyQuoteDtlSlsAmt_B.clear();
            }
        } else {
            if (S21ApiUtil.isXxMsgId(prcApiPMsg)) {
                List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(prcApiPMsg);
                for (int i = 0; i < msgList.size(); i++) {
                    S21ApiMessage msg = msgList.get(i);
                    String msgId = msg.getXxMsgid();
                    String[] msgPrms = msg.getXxMsgPrmArray();
                    bizMsg.setMessageInfo(msgId, msgPrms);
                }
            }
            // S21_NA#13173 Add END
        }
    }

    /**
     * Delete Select Line Calculation Base
     * @param bizMsg NWAL1770CMsg
     */
    private static void delSlctLineCalcBase(NWAL1770CMsg bizMsg) {

        String slctQuoteDtlLineNum = bizMsg.B.no(bizMsg.xxCellIdx.getValueInt()).splyQuoteDtlLineNum_B.getValue();

        List<Integer> deleteRows = new ArrayList<Integer>();
        for (int i = 0; i < bizMsg.E.getValidCount(); i++) {
            String quoteDtlLineNum = bizMsg.E.no(i).splyQuoteDtlLineNum_E.getValue();
            if (slctQuoteDtlLineNum.equals(quoteDtlLineNum)) {
                deleteRows.add(i);
            }
        }

        ZYPTableUtil.deleteRows(bizMsg.E, deleteRows);
    }

    /**
     * Derive Set Component
     * @param bizMsg NWAL1770CMsg
     */
    public static void deriveSetComponent(NWAL1770CMsg bizMsg) {

        int slctLine = bizMsg.xxCellIdx.getValueInt();
        NWAL1770_BCMsg parentLineMsg = bizMsg.B.no(slctLine);

        MDSETMsg mdseTMsg = NWXMdseTMsgThreadLocalCache.getInstance().get(bizMsg.glblCmpyCd.getValue(), parentLineMsg.mdseCd_B.getValue());
        if (mdseTMsg == null || !MDSE_TP.SET.equals(mdseTMsg.mdseTpCd.getValue())) {
            return;
        }

        int insertRow = slctLine + 1;
        int lineSubNum = 1;
        List<Map<String, Object>> childMdseList = getChildMdseList(bizMsg, mdseTMsg.mdseCd.getValue());

        for (Map<String, Object> childMdseInfo : childMdseList) {
            NWAL1770_BCMsg newChildLineMsg = insertChildLine(bizMsg, insertRow);
            if (newChildLineMsg == null) {
                return;
            }

            BigDecimal baseChildQty = BigDecimal.ONE;

            // Setup Child Line
            setupChildLine(bizMsg, parentLineMsg, newChildLineMsg, childMdseInfo, baseChildQty, lineSubNum++);
            insertRow++;

            MDSETMsg childMdseTMsg = NWXMdseTMsgThreadLocalCache.getInstance().get(bizMsg.glblCmpyCd.getValue(), (String) childMdseInfo.get("MDSE_CD"));
            if (childMdseTMsg != null && MDSE_TP.SET.equals(childMdseTMsg.mdseTpCd.getValue())) {
                List<Map<String, Object>> grandchildMdseList = getChildMdseList(bizMsg, childMdseTMsg.mdseCd.getValue());

                baseChildQty = newChildLineMsg.childMdseQty_B.getValue(); // 2017/03/02 S21_NA#16800 Add

                if (grandchildMdseList.size() > 0) {
                    insertRow--;
                    lineSubNum--;
                    List<Integer> deleteList = new ArrayList<Integer>();
                    deleteList.add(insertRow);
                    ZYPTableUtil.deleteRows(bizMsg.B, deleteList);
                }

                // baseChildQty = newChildLineMsg.childMdseQty_B.getValue(); // 2017/03/02 S21_NA#16800 Del

                for (Map<String, Object> grandchildMdseInfo : grandchildMdseList) {
                    NWAL1770_BCMsg newGrandchildLineMsg = insertChildLine(bizMsg, insertRow);
                    if (newGrandchildLineMsg == null) {
                        return;
                    }

                    // Setup Grand Child Line
                    setupChildLine(bizMsg, parentLineMsg, newGrandchildLineMsg, grandchildMdseInfo, baseChildQty, lineSubNum++);
                    insertRow++;
                }
            }
        }
    }

    /**
     * Get Child MDSE List
     * @param bizMsg NWAL1770CMsg
     * @param parentMdseCd Parent MDSE Code
     * @return Child MDSE List
     */
    @SuppressWarnings("unchecked")
    private static List<Map<String, Object>> getChildMdseList(NWAL1770CMsg bizMsg, String parentMdseCd) {

        S21SsmEZDResult ssmResult = NWAL1770QueryForItemLine.getInstance().getChildMdseList(bizMsg, parentMdseCd);

        if (ssmResult.isCodeNotFound()) {
            return new ArrayList<Map<String, Object>>();
        }

        return (List<Map<String, Object>>) ssmResult.getResultObject();
    }

    /**
     * Insert Child Line
     * @param bizMsg NWAL1770CMsg
     * @param insertRow Insert Row
     * @return New Child Line
     */
    private static NWAL1770_BCMsg insertChildLine(NWAL1770CMsg bizMsg, int insertRow) {

        NWAL1770_BCMsgArray itemLineArray = bizMsg.B;

        if (itemLineArray.getValidCount() >= itemLineArray.length()) {
            return null;
        }

        for (int i = itemLineArray.getValidCount() - 1; insertRow <= i; i--) {
            EZDMsg.copy(itemLineArray.get(i), null, itemLineArray.get(i + 1), null);
        }

        itemLineArray.get(insertRow).clear();
        itemLineArray.setValidCount(itemLineArray.getValidCount() + 1);

        return (NWAL1770_BCMsg) itemLineArray.get(insertRow);
    }

    /**
     * Setup Child Line
     * @param bizMsg NWAL1770CMsg
     * @param parentLineMsg NWAL1770_BCMsg
     * @param newChildLineMsg NWAL1770_BCMsg
     * @param childMdseInfo Child MDSE Information
     * @param baseChildQty Base Child Qty
     * @param lineSubNum Line Sub Number
     */
    private static void setupChildLine(NWAL1770CMsg bizMsg, NWAL1770_BCMsg parentLineMsg, NWAL1770_BCMsg newChildLineMsg, Map<String, Object> childMdseInfo, BigDecimal baseChildQty, int lineSubNum) {

        BigDecimal baseQty = parentLineMsg.ordCustUomQty_B.getValue();
        BigDecimal baseEachQty = parentLineMsg.ordQty_B.getValue();

        NWAL1770_BCMsg workItemLineMsg = new NWAL1770_BCMsg();
        ZYPEZDItemValueSetter.setValue(workItemLineMsg.dplyQuoteLineNum_B, parentLineMsg.dplyQuoteLineNum_B);
        ZYPEZDItemValueSetter.setValue(workItemLineMsg.dplyQuoteLineSubNum_B, new BigDecimal(lineSubNum));
        ZYPEZDItemValueSetter.setValue(workItemLineMsg.xxLineNum_B, NWAL1770CommonLogic.concatLineNum(workItemLineMsg));
        if (ZYPCommonFunc.hasValue(parentLineMsg.splyQuoteDtlLineNum_B)) {
            ZYPEZDItemValueSetter.setValue(workItemLineMsg.splyQuoteDtlLineNum_B, parentLineMsg.splyQuoteDtlLineNum_B);
            ZYPEZDItemValueSetter.setValue(workItemLineMsg.splyQuoteDtlLineSubNum_B, String.format("%03d", lineSubNum));
        }

        ZYPEZDItemValueSetter.setValue(workItemLineMsg.mdseCd_B, (String) childMdseInfo.get("MDSE_CD"));
        ZYPEZDItemValueSetter.setValue(workItemLineMsg.childMdseQty_B, baseChildQty.multiply((BigDecimal) childMdseInfo.get("CHILD_MDSE_QTY")));
        if (baseEachQty != null && baseQty != null) {
            ZYPEZDItemValueSetter.setValue(workItemLineMsg.ordCustUomQty_B, baseQty.multiply(workItemLineMsg.childMdseQty_B.getValue()));
            ZYPEZDItemValueSetter.setValue(workItemLineMsg.ordQty_B, baseEachQty.multiply(workItemLineMsg.childMdseQty_B.getValue()));
        }

        MDSETMsg mdseTMsg = NWXMdseTMsgThreadLocalCache.getInstance().get(bizMsg.glblCmpyCd.getValue(), (String) childMdseInfo.get("MDSE_CD"));
        S21SsmEZDResult ssmResult = NWAL1770QueryForItemLine.getInstance().getPkgUomCd(bizMsg, mdseTMsg.mdseCd.getValue());
        String pkgUomCd = (String) ssmResult.getResultObject();

        ZYPEZDItemValueSetter.setValue(workItemLineMsg.mdseNm_B, mdseTMsg.mdseNm);
        // START 2017/10/18 J.Kim [QC#21067,ADD]
        ZYPEZDItemValueSetter.setValue(workItemLineMsg.mnfItemCd_B, getMnfItemCd(bizMsg, (String) childMdseInfo.get("MDSE_CD")));
        // END 2017/10/18 J.Kim [QC#21067,ADD]
        ZYPEZDItemValueSetter.setValue(workItemLineMsg.mdseDescShortTxt_B, getMdseShortNm(bizMsg, mdseTMsg));
        NWAL1770CommonLogic.createPkgUomPullDown(bizMsg, workItemLineMsg);
        ZYPEZDItemValueSetter.setValue(workItemLineMsg.custUomCd_B, pkgUomCd);
        ZYPEZDItemValueSetter.setValue(workItemLineMsg.dealPrcListPrcAmt_B, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(workItemLineMsg.dealSplyQuoteDtlSlsAmt_B, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(workItemLineMsg.prcCatgCd_B, parentLineMsg.prcCatgCd_B);
        ZYPEZDItemValueSetter.setValue(workItemLineMsg.prcCatgNm_B, parentLineMsg.prcCatgNm_B);
        String primaryLineCatgCd = NWAL1770CommonLogic.createLineCatgPulldown(bizMsg, bizMsg.slsDt.getValue());
        ZYPEZDItemValueSetter.setValue(workItemLineMsg.dsOrdLineCatgCd_B, primaryLineCatgCd);
        ZYPEZDItemValueSetter.setValue(workItemLineMsg.rtlWhCd_B, parentLineMsg.rtlWhCd_B);
        ZYPEZDItemValueSetter.setValue(workItemLineMsg.rtlWhNm_B, parentLineMsg.rtlWhNm_B);
        ZYPEZDItemValueSetter.setValue(workItemLineMsg.rtlSwhCd_B, parentLineMsg.rtlSwhCd_B);
        ZYPEZDItemValueSetter.setValue(workItemLineMsg.rtlSwhNm_B, parentLineMsg.rtlSwhNm_B);
        ZYPEZDItemValueSetter.setValue(workItemLineMsg.ordLineSrcCd_B, parentLineMsg.ordLineSrcCd_B);
        ZYPEZDItemValueSetter.setValue(workItemLineMsg.supdLockFlg_B, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(workItemLineMsg.dealPrcListPrcAmt_B, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(workItemLineMsg.xxTotDiscAmt_B, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(workItemLineMsg.xxSubTotCalcPrcAmt_B, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(workItemLineMsg.xxTotFrtAmt_B, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(workItemLineMsg.xxTotTaxAmt_B, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(workItemLineMsg.xxTotAmt_B, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(workItemLineMsg.xxDtlDiscAmt_B, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(workItemLineMsg.prcBaseDt_B, parentLineMsg.prcBaseDt_B); // 2017/09/20 S21_NA#21068 Add
        ZYPEZDItemValueSetter.setValue(workItemLineMsg.rddDt_B, parentLineMsg.rddDt_B); // 2018/02/13 S21_NA#21165 Add

        EZDMsg.copy(workItemLineMsg, null, newChildLineMsg, null);
    }

    /**
     * Setup Child Line
     * @param bizMsg NWAL1770CMsg
     * @param parentLineIndex Parent Line Index
     */
    public static void updateChildLine(NWAL1770CMsg bizMsg, int parentLineIndex) {

        NWAL1770_BCMsg parentLineMsg = bizMsg.B.no(parentLineIndex);
        BigDecimal uomQty = parentLineMsg.ordCustUomQty_B.getValue();
        BigDecimal eachQty = parentLineMsg.ordQty_B.getValue();

        for (int i = parentLineIndex + 1; i < bizMsg.B.getValidCount(); i++) {
            NWAL1770_BCMsg childLineMsg = bizMsg.B.no(i);
            if (!ZYPCommonFunc.hasValue(childLineMsg.dplyQuoteLineSubNum_B)) {
                return;
            }

            BigDecimal childQty = childLineMsg.childMdseQty_B.getValue();
            if (uomQty != null && eachQty != null && childQty != null) {
                ZYPEZDItemValueSetter.setValue(childLineMsg.ordCustUomQty_B, uomQty.multiply(childQty));
                ZYPEZDItemValueSetter.setValue(childLineMsg.ordQty_B, eachQty.multiply(childQty));
            } else {
                childLineMsg.ordCustUomQty_B.clear();
                childLineMsg.ordQty_B.clear();
            }

            ZYPEZDItemValueSetter.setValue(childLineMsg.prcCatgCd_B, parentLineMsg.prcCatgCd_B);
            ZYPEZDItemValueSetter.setValue(childLineMsg.prcCatgNm_B, parentLineMsg.prcCatgNm_B);
            ZYPEZDItemValueSetter.setValue(childLineMsg.rtlWhCd_B, parentLineMsg.rtlWhCd_B);
            ZYPEZDItemValueSetter.setValue(childLineMsg.rtlWhNm_B, parentLineMsg.rtlWhNm_B);
            ZYPEZDItemValueSetter.setValue(childLineMsg.rtlSwhCd_B, parentLineMsg.rtlSwhCd_B);
            ZYPEZDItemValueSetter.setValue(childLineMsg.rtlSwhNm_B, parentLineMsg.rtlSwhNm_B);
            ZYPEZDItemValueSetter.setValue(childLineMsg.ordLineSrcCd_B, parentLineMsg.ordLineSrcCd_B);
            ZYPEZDItemValueSetter.setValue(childLineMsg.prcBaseDt_B, parentLineMsg.prcBaseDt_B); // 2017/10/03 S21_NA#21068 Add
            ZYPEZDItemValueSetter.setValue(childLineMsg.rddDt_B, parentLineMsg.rddDt_B); // 2018/02/13 S21_NA#21165 Add
        }
    }

    /**
     * Set Supersede MDSE Code
     * @param bizMsg Business Message
     * @param inputMdseCd MDSE Code
     * @return SuperSede API No Error : true
     */
    public static boolean setSupersedeMdse(NWAL1770CMsg bizMsg, String inputMdseCd) {

        bizMsg.mdseCd_SS.clear();
        bizMsg.mdseNm_SS.clear();
        ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_OFF_N);

        NWAL1770_BCMsg itemLineMsg = bizMsg.B.no(bizMsg.xxCellIdx.getValueInt());

        if (ZYPConstant.FLG_ON_Y.equals(itemLineMsg.supdLockFlg_B.getValue()) || ZYPCommonFunc.hasValue(itemLineMsg.splyQuoteStsDescTxt_B)) {
            return true;
        }

        // Call Supersede API
        if (!callSupersedeCommonApi(bizMsg, itemLineMsg, inputMdseCd)) { // 2018/02/06 S21_NA#20173 Mod
            return false;
        }

        return true;
    }

    // 2018/02/06 S21_NA#20173 Del Start
//    /**
//     * Call NWZC2060 Supersede API
//     * @param bizMsg NWAL1770CMsg
//     * @param itemLineMsg NWAL1770_BCMsg
//     * @param inputMdseCd MDSE Code
//     * @return SuperSede API No Error : true
//     */
//    private static boolean callSupersedeApi(NWAL1770CMsg bizMsg, NWAL1770_BCMsg itemLineMsg, String inputMdseCd) {
//
//        MDSETMsg mdseTMsg = NWXMdseTMsgThreadLocalCache.getInstance().get(bizMsg.glblCmpyCd.getValue(), itemLineMsg.mdseCd_B.getValue());
//
//        NWZC206001PMsg superSedeApiPMsg = new NWZC206001PMsg();
//        ZYPEZDItemValueSetter.setValue(superSedeApiPMsg.glblCmpyCd, bizMsg.glblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(superSedeApiPMsg.slsDt, bizMsg.slsDt);
//        ZYPEZDItemValueSetter.setValue(superSedeApiPMsg.xxModeCd, NWZC206001.SUPD_LATEST_MODE);
//        ZYPEZDItemValueSetter.setValue(superSedeApiPMsg.mdseCd, mdseTMsg.mdseCd);
//        ZYPEZDItemValueSetter.setValue(superSedeApiPMsg.xxAvalOrdFlg, ZYPConstant.FLG_ON_Y);
//        ZYPEZDItemValueSetter.setValue(superSedeApiPMsg.xxAvalPrchFlg, ZYPConstant.FLG_OFF_N);
//
//        new NWZC206001().execute(superSedeApiPMsg, ONBATCH_TYPE.ONLINE);
//
//        if (S21ApiUtil.isXxMsgId(superSedeApiPMsg)) {
//            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(superSedeApiPMsg);
//            for (int i = 0; i < msgList.size(); i++) {
//                itemLineMsg.supdLockFlg_B.setErrorInfo(1, NWAM0189E, new String[] {MSG_PARAM_SUPER_SEDE_API });
//                return false;
//            }
//        }
//
//        if (superSedeApiPMsg.A.getValidCount() == 0) {
//            return true;
//        }
//
//        String superSedeMdseCd = superSedeApiPMsg.A.no(0).mdseCd.getValue();
//        if (!checkPossessionSameUomCd(bizMsg, itemLineMsg, superSedeMdseCd)) {
//            return true;
//        }
//
//        if (ZYPCommonFunc.hasValue(superSedeMdseCd) && !inputMdseCd.equals(superSedeMdseCd)) {
//            String ordTakeMdseCd = getOrdTakeMdseCd(bizMsg, superSedeMdseCd);
//            ZYPEZDItemValueSetter.setValue(bizMsg.mdseCd_SS, ordTakeMdseCd);
//            ZYPEZDItemValueSetter.setValue(bizMsg.mdseNm_SS, superSedeApiPMsg.A.no(0).mdseNm.getValue());
//            ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_ON_Y);
//            return false;
//        }
//
//        return true;
//    }
//
//    /**
//     * Check Possession SameUOM Code Of Supersede MDSE Code
//     * @param bizMsg NWAL1770CMsg
//     * @param lineMsg NWAL1770_BCMsg
//     * @param supdMdseCd Supersede MDSE Code
//     * @return exist : true
//     */
//    @SuppressWarnings("unchecked")
//    private static boolean checkPossessionSameUomCd(NWAL1770CMsg bizMsg, NWAL1770_BCMsg lineMsg, String supdMdseCd) {
//
//        String slctPkgUomCd = lineMsg.custUomCd_B.getValue();
//        S21SsmEZDResult ssmRslt = NWAL1770Query.getInstance().getPkgUomInfoList(bizMsg, supdMdseCd);
//
//        if (ssmRslt.isCodeNormal()) {
//            List<Map<String, String>> pkgUomList = (List<Map<String, String>>) ssmRslt.getResultObject();
//            for (Map<String, String> pkgUomInfo : pkgUomList) {
//                String pkgUomCd = pkgUomInfo.get("PKG_UOM_CD");
//                if (ZYPCommonFunc.hasValue(pkgUomCd) && pkgUomCd.equals(slctPkgUomCd)) {
//                    return true;
//                }
//            }
//        }
//
//        return false;
//    }
//
//    /**
//     * Get Order Take MDSE Code
//     * @param bizMsg NWAL1770CMsg
//     * @param mdseCd MDSE Code
//     * @return Order Take MDSE Code
//     */
//    private static String getOrdTakeMdseCd(NWAL1770CMsg bizMsg, String mdseCd) {
//
//        ORD_TAKE_MDSETMsg ordTakeMdseTMsg = new ORD_TAKE_MDSETMsg();
//        ordTakeMdseTMsg.setSQLID("001");
//        ZYPEZDItemValueSetter.setValue(ordTakeMdseTMsg.glblCmpyCd, bizMsg.glblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(ordTakeMdseTMsg.mdseCd, mdseCd);
//
//        ORD_TAKE_MDSETMsgArray tMsgArray = (ORD_TAKE_MDSETMsgArray) EZDTBLAccessor.findByCondition(ordTakeMdseTMsg);
//        if (0 == tMsgArray.getValidCount()) {
//            return mdseCd;
//        } else {
//            return tMsgArray.no(0).ordTakeMdseCd.getValue();
//        }
//    }
    // 2018/02/06 S21_NA#20173 Del End

    // 2018/02/06 S21_NA#20173 Add Start
    /**
     * Call NWZC1920 Supersede Common API
     * @param bizMsg NWAL1770CMsg
     * @param itemLineMsg NWAL1770_BCMsg
     * @param inputMdseCd MDSE Code
     * @return SuperSede API No Error : true
     */
    private static boolean callSupersedeCommonApi(NWAL1770CMsg bizMsg, NWAL1770_BCMsg itemLineMsg, String inputMdseCd) {

        MDSETMsg mdseTMsg = NWXMdseTMsgThreadLocalCache.getInstance().get(bizMsg.glblCmpyCd.getValue(), itemLineMsg.mdseCd_B.getValue());

        NWZC192001PMsg superSedeApiPMsg = new NWZC192001PMsg();
        ZYPEZDItemValueSetter.setValue(superSedeApiPMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(superSedeApiPMsg.slsDt, bizMsg.slsDt);
        ZYPEZDItemValueSetter.setValue(superSedeApiPMsg.origMdseCd, mdseTMsg.mdseCd);
        ZYPEZDItemValueSetter.setValue(superSedeApiPMsg.dsOrdCatgCd, bizMsg.splyQuoteCatgCd);
        ZYPEZDItemValueSetter.setValue(superSedeApiPMsg.dsOrdTpCd, bizMsg.dsOrdTpCd);
        ZYPEZDItemValueSetter.setValue(superSedeApiPMsg.shipToPostCd, bizMsg.shipToPostCd);
        ZYPEZDItemValueSetter.setValue(superSedeApiPMsg.shipToLocNum, bizMsg.locNum);
        ZYPEZDItemValueSetter.setValue(superSedeApiPMsg.ordQty, itemLineMsg.ordQty_B);

        List<String> dropShipRtlWhCd = NWAL1770CommonLogicForSaveSubmit.getVarCharConstDataList(bizMsg.glblCmpyCd.getValue(), DROP_SHIP_RTL_WH_CD);
        boolean isDropShipRtlWh = dropShipRtlWhCd.contains(itemLineMsg.rtlWhCd_B.getValue());

        if (isDropShipRtlWh) {
        	ZYPEZDItemValueSetter.setValue(superSedeApiPMsg.dropShipFlg, ZYPConstant.FLG_ON_Y);  // SUPD_LATEST_MODE
        } else {
        	ZYPEZDItemValueSetter.setValue(superSedeApiPMsg.dropShipFlg, ZYPConstant.FLG_OFF_N); // SUPD_LIST_MODE
        }

        new NWZC192001().execute(superSedeApiPMsg, ONBATCH_TYPE.ONLINE);

        if (S21ApiUtil.isXxMsgId(superSedeApiPMsg)) {
            if (!S21ApiUtil.getXxMsgIdList(superSedeApiPMsg).isEmpty()) {
            	itemLineMsg.supdLockFlg_B.setErrorInfo(1, NWAM0189E, new String[] {MSG_PARAM_SUPER_SEDE_API });
                return false;
            }
        }

        String superSedeMdseCd = superSedeApiPMsg.supdToMdseCd.getValue();
        if (!ZYPCommonFunc.hasValue(superSedeMdseCd)) {
            return true;
        } else if (!inputMdseCd.equals(superSedeMdseCd)) {
        	ZYPEZDItemValueSetter.setValue(bizMsg.mdseCd_SS, superSedeMdseCd);
            ZYPEZDItemValueSetter.setValue(bizMsg.mdseNm_SS, superSedeApiPMsg.mdseDescShortTxt_SP);
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_ON_Y);
        }

        return true;
    }
    // 2018/02/06 S21_NA#20173 Add End

    /**
     * Check Mandatory Addl Tab Field
     * @param bizMsg NWAL1770CMsg
     * @return No Error : true
     */
    public static boolean checkMandatoryAddlField(NWAL1770CMsg bizMsg) {

        boolean isNormal = true;

        if (!ZYPCommonFunc.hasValue(bizMsg.frtCondDescTxt)) {
            bizMsg.frtCondDescTxt.setErrorInfo(1, ZZM9000E, new String[] {MSG_PARAM_FRT_TERMS });
            isNormal = false;
        }

        if (!ZYPCommonFunc.hasValue(bizMsg.shpgSvcLvlCd)) {
            bizMsg.shpgSvcLvlCd.setErrorInfo(1, ZZM9000E, new String[] {MSG_PARAM_SHPG_SVC_LVL });
            isNormal = false;
        }

        return isNormal;
    }

    /**
     * Get MDSE Short Name
     * @param bizMsg NWAL1770CMsg
     * @param baseMdseTMsg baseMdseTMsg
     * @return MDSE Short Name
     */
    public static String getMdseShortNm(NWAL1770CMsg bizMsg, MDSETMsg baseMdseTMsg) {

        MDSETMsg mdseTMsg = new MDSETMsg();
        ZYPEZDItemValueSetter.setValue(mdseTMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(mdseTMsg.mdseCd, baseMdseTMsg.mdseCd);
        mdseTMsg = (MDSETMsg) S21FastTBLAccessor.findByKey(mdseTMsg);

        if (mdseTMsg == null) {
            return null;
        }

        return mdseTMsg.mdseDescShortTxt.getValue();
    }

    // Add Start 2017/10/26 QC#20304
    public static boolean easyPackCheck(NWAL1770CMsg bizMsg, NWAL1770_BCMsg itemLineMsg) {
        if (NWXC150001DsCheck.isEasyPack(bizMsg.glblCmpyCd.getValue(), bizMsg.splyQuoteCatgCd.getValue(), bizMsg.dsOrdTpCd.getValue())) {
            
            // 2018/04/03 S21_NA#25209 Mod Start
            MDSETMsg tMsg = NWXMdseTMsgThreadLocalCache.getInstance().get(bizMsg.glblCmpyCd.getValue(), itemLineMsg.mdseCd_B.getValue());
            // MDSETMsg tMsg = new MDSETMsg();
            // ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
            // ZYPEZDItemValueSetter.setValue(tMsg.mdseCd, itemLineMsg.mdseCd_B.getValue());
            // tMsg = (MDSETMsg) S21CacheTBLAccessor.findByKey(tMsg);
            // 2018/04/03 S21_NA#25209 Mod End

            if (tMsg == null || !ZYPCommonFunc.hasValue(tMsg.easyPackTpCd)) {
                itemLineMsg.mdseCd_B.setErrorInfo(1, NWZM1530E);
                return false;
            }
            if (!NWXC150001DsCheck.isSplyPgmContr(bizMsg.glblCmpyCd.getValue(), bizMsg.slsDt.getValue(), bizMsg.billToCustAcctCd.getValue(), bizMsg.billToCustCd.getValue())) {
                itemLineMsg.mdseCd_B.setErrorInfo(1, NWZM2008E);
                return false;
            }
        }

        return true;
    }
    // Add End 2017/10/26 QC#20304
    
    // 2018/07/19 QC#26153 Add Start
    public static void quote_search(NWAL1770CMsg bizMsg, NWAL1770SMsg glblMsg, boolean isCallCopy, String glblCmpyCd){
        // Backup Data
        String dplyTab = bizMsg.xxDplyTab.getValue();
        String splyQuoteNum = bizMsg.splyQuoteNum.getValue();

        // Initial Screen Objects
        S21UserProfileService userProfileService = S21UserProfileServiceFactory.getInstance().getService();
        String adminPsnCd = userProfileService.getContextUserInfo().getUserId();

        NWAL1770CommonLogic.doProcess_Init_BL02(bizMsg, glblMsg, glblCmpyCd, adminPsnCd, userProfileService);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyTab, dplyTab);
        ZYPEZDItemValueSetter.setValue(bizMsg.splyQuoteNum, splyQuoteNum);

        // Get Header (SPLY_QUOTE)
        S21SsmEZDResult ssmResult = NWAL1770QueryForSearch.getInstance().getSplyQuoteHdr(bizMsg);
        if (ssmResult.isCodeNotFound()) {
            bizMsg.setMessageInfo(ZZZM9001E);
            return;
        }
        ZYPEZDItemValueSetter.setValue(bizMsg.ovrdPmtTermFlg, NWXC150001DsCheck.getOvrdPmtTermFlg(bizMsg.glblCmpyCd.getValue(), bizMsg.billToCustAcctCd.getValue(), bizMsg.billToCustCd.getValue()));

        // Get Detail (SPLY_QUOTE_DTL)
        ssmResult = NWAL1770QueryForSearch.getInstance().getSplyQuoteDtl(bizMsg, isCallCopy);

        // Get Sales Credit (SPLY_QUOTE_SLS_CR)
        NWAL1770QueryForSearch.getInstance().getSplyQuoteSalesCredit(bizMsg);

        // Get Contact Person (SPLY_QUOTE_CTAC_PSN)
        NWAL1770QueryForSearch.getInstance().getSplyQuoteCtacPsn(bizMsg);

        // Get Additional Data
        NWAL1770CommonLogicForSearch.getSplyQuoteAddlData(bizMsg);

        // Get Price Information (SPLY_QUOTE_PRC_CALC_BASE)
        NWAL1770QueryForSearch.getInstance().getSplyQuotePrcCalcBase(bizMsg, isCallCopy);
        NWAL1770CommonLogicForSearch.setPrice(bizMsg);

        // Get Location Number
        NWAL1770CommonLogicForSearch.getLocNum(bizMsg);

        // Create Pulldown
        NWAL1770CommonLogicForSearch.createPulldown(bizMsg);

        // START 2023/07/20 T.Fukuta [CSA-QC#61467,ADD]
        checkHazMat(bizMsg);
        // END 2023/07/20 T.Fukuta [CSA-QC#61467,ADD]

        // 2018/12/12 QC#29315 Add Start
        ZYPEZDItemValueSetter.setValue(bizMsg.dsBizAreaCd, NWAL1770CommonLogic.getBizAreaCd(bizMsg));
        // 2018/12/12 QC#29315 Add End

        // Store Backup Data For $ Button
        EZDMsg.copy(bizMsg, null, glblMsg, null);
        EZDMsg.copy(bizMsg.B, null, glblMsg.B, null);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxSrchNum, bizMsg.splyQuoteNum);
    }
    // 2018/07/19 QC#26153 Add End

    // START 2023/07/20 T.Fukuta [CSA-QC#61467,ADD]
    /**
     * Check HazMat for All Item Lines
     * @param bizMsg NWAL1770CMsg
     */
    public static void checkHazMat(NWAL1770CMsg bizMsg) {

        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
            checkHazMat(bizMsg.B.no(i));
        }
    }

    /**
     * Check HazMat for Specified Item line
     * @param itemLineMsg target item line message
     */
    public static void checkHazMat(NWAL1770_BCMsg itemLineMsg) {

        if (!ZYPCommonFunc.hasValue(itemLineMsg.hazMatFlg_B)) {
            return;
        }

        if (ZYPConstant.FLG_ON_Y.equals(itemLineMsg.hazMatFlg_B.getValue())) {
            itemLineMsg.hazMatFlg_B.setErrorInfo(2, NWAM8473W);
        }
    }

    /**
     * Check HazMat with searching HazMat status
     * @param bizMsg NWAL1770CMsg
     * @param inputMdseCd input marchandize code
     */
    public static void checkHazMat(NWAL1770CMsg bizMsg, String inputMdseCd) {

        int slctLine = bizMsg.xxCellIdx.getValueInt();
        NWAL1770_BCMsg itemLineMsg = bizMsg.B.no(slctLine);

        NWAL1770QueryForItemLine query = NWAL1770QueryForItemLine.getInstance();
        S21SsmEZDResult ssmResult = query.getHazMat(bizMsg, inputMdseCd);
        if (ssmResult.isCodeNotFound()) {
            return;
        }

        String hazMatFlg = (String) ssmResult.getResultObject();
        ZYPEZDItemValueSetter.setValue(itemLineMsg.hazMatFlg_B, hazMatFlg);

        if (ZYPConstant.FLG_ON_Y.equals(hazMatFlg)) {
            itemLineMsg.hazMatFlg_B.setErrorInfo(2, NWAM8473W);
        }
    }
    // END 2023/07/20 T.Fukuta [CSA-QC#61467,ADD]
}
