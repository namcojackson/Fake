/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL2200.common;

import static business.blap.NWAL2200.constant.NWAL2200Constant.BIZ_ID;
import static business.blap.NWAL2200.constant.NWAL2200Constant.NWAM0181E;
import static business.blap.NWAL2200.constant.NWAL2200Constant.NWAM0772E;
import static business.blap.NWAL2200.constant.NWAL2200Constant.NWAM0883E;
import static business.blap.NWAL2200.constant.NWAL2200Constant.NWAM0935E;
import static business.blap.NWAL2200.constant.NWAL2200Constant.NWAM0987E;
import static business.blap.NWAL2200.constant.NWAL2200Constant.NWAM0988E;
import static business.blap.NWAL2200.constant.NWAL2200Constant.NWAM0989E;
import static business.blap.NWAL2200.constant.NWAL2200Constant.TAB_ADDL_HEADER;
import static business.blap.NWAL2200.constant.NWAL2200Constant.TAB_HEADER;
import static business.blap.NWAL2200.constant.NWAL2200Constant.TAB_LINE_CONFIG;
import static business.blap.NWAL2200.constant.NWAL2200Constant.TAB_RMA;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM1839E;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDItemAttribute;
import parts.common.EZDMsg;
import parts.common.EZDSStringItem;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NWAL2200.NWAL2200CMsg;
import business.blap.NWAL2200.NWAL2200Query;
import business.blap.NWAL2200.NWAL2200SMsg;
import business.blap.NWAL2200.NWAL2200_ACMsg;
import business.blap.NWAL2200.NWAL2200_ASMsg;
import business.blap.NWAL2200.NWAL2200_ASMsgArray;
import business.blap.NWAL2200.NWAL2200_BCMsg;
import business.blap.NWAL2200.NWAL2200_BCMsgArray;
import business.blap.NWAL2200.NWAL2200_BSMsg;
import business.blap.NWAL2200.NWAL2200_CCMsg;
import business.blap.NWAL2200.NWAL2200_CSMsg;
import business.blap.NWAL2200.NWAL2200_CSMsgArray;
import business.blap.NWAL2200.NWAL2200_DCMsg;
import business.blap.NWAL2200.NWAL2200_DCMsgArray;
import business.blap.NWAL2200.NWAL2200_DSMsg;
import business.blap.NWAL2200.constant.NWAL2200Constant;
import business.db.ALL_MDSE_VTMsg;
import business.db.ALL_MDSE_VTMsgArray;
import business.db.BILL_TO_CUSTTMsg;
import business.db.BILL_TO_CUSTTMsgArray;
import business.db.CUST_MDSE_XREFTMsg;
import business.db.CUST_MDSE_XREFTMsgArray;
import business.db.DS_ORD_TP_PROC_DFNTMsg;
import business.db.MDSETMsg;
import business.db.MDSE_STORE_PKGTMsg;
import business.db.ORD_CATG_BIZ_CTXTMsg;
import business.db.ORD_CATG_BIZ_CTXTMsgArray;
import business.db.SHIP_TO_CUSTTMsg;
import business.db.SHIP_TO_CUSTTMsgArray;

import com.canon.cusa.s21.common.NWX.NWXC004001.NWXMdseTMsgThreadLocalCache;
import com.canon.cusa.s21.common.NWX.NWXC150001.NWXC150001DsCheck;
import com.canon.cusa.s21.common.NWX.NWXC220001.NWXC220001;
import com.canon.cusa.s21.common.NWX.NWXC220001.NWXC220001CustomerBean;
import com.canon.cusa.s21.common.NWX.NWXC220001.NWXC220001Result;
import com.canon.cusa.s21.common.NWX.NWXC220001.constant.NWXC220001Constant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CPO_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ORD_LINE_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_CATG_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORG_STRU_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 *<pre>
 * NWAL2200CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/22   Fujitsu         T.Ishii         Create          N/A
 * 2017/01/17   Fujitsu         H.Nagashima     Update          QC#17124
 * 2017/02/21   Fujitsu         Y.Kanefusa      Update          S21_NA#17474
 * 2017/03/30   Fujitsu         S.Ohki          Update          S21_NA#18175
 * 2017/06/06   Fujitsu         R.Nakamura      Update          S21_NA#18583
 * 2017/10/02   Fujitsu         R.Nakamura      Update          S21_NA#21181
 * 2017/11/21   Fujitsu         Y.Kanefusa      Update          QC#22658
 * 2018/01/23   Fujitsu         T.Aoi           Update          QC#18798(L3#173)
 * 2018/01/25   Fujitsu         Y.Kanefusa      Update          S21_NA#19808
 * 2018/03/08   Fujitsu         T.Aoi           Update          S21_NA#24588
 * 2018/07/25   Fujitsu         Mz.Takahashi    Update          S21_NA#14307
 * 2018/08/31   Fujitsu         K.Ishizuka      Update          S21_NA#26022
 * 2018/11/19   Fujitsu         Hd.Sugawara     Update          S21_NA#28683
 * 2019/02/08   Fujitsu         K.Kato          Update          S21_NA#30237
 * 2020/04/24   CITS            K.Ogino         Update          QC#56638
 * 2021/01/30   CITS            K.Ogino         Update          QC#58230
 * 2023/11/06   Hitachi         H.Watanabe      Update          QC#61924
 *</pre>
 */
public class NWAL2200CommonLogic {

    /**
     * compare to big decimal
     * @param source source value
     * @param target target value
     * @return result (0, > 0 , < 0)
     */
    public static int compareBigDecimal(BigDecimal source, BigDecimal target) {

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

    /**
     * setDbResult
     * @param msg EZDMsg
     * @param suffix String
     * @param dbResult Map<String, Object>
     */
    public static void setDbResult(EZDMsg msg, String suffix, Map<String, Object> dbResult) {

        @SuppressWarnings("unchecked")
        Iterator ite = msg.keysIterator();
        while (ite.hasNext()) {
            String key = (String) ite.next();
            EZDItemAttribute attr = msg.getAttr(key);
            String columnName = attr.getMessage();
            if (S21StringUtil.isEquals(columnName, "_EZUpdateDateTime")) {
                columnName = "EZUPTIME";
            }
            if (S21StringUtil.isEquals(columnName, "_EZUpTimeZone")) {
                columnName = "EZUPTIMEZONE";
            }
            if (dbResult.containsKey(columnName)) {
                Object value = dbResult.get(columnName);
                if (value instanceof String) {
                    msg.setValue(key, -1, (String) value);
                } else if (value instanceof BigDecimal) {
                    msg.setValue(key, -1, (BigDecimal) value);
                }
            }
        }
    }

    /**
     * derive from item
     * @param bizMsg NWAL2200CMsg
     * @param line NWAL2200_BCMsg
     * @return success, failure
     */
    public static boolean deriveFromItem(NWAL2200CMsg bizMsg, NWAL2200_BSMsg line) {

        EZDSStringItem inputMdseValue = line.mdseCd_LL;
        EZDSStringItem custMdseCd = line.custMdseCd_LL;
        EZDSStringItem mnfItemCd = line.mnfItemCd_LL;
        EZDSStringItem origMdseCd = line.origMdseCd_LL;

        String inputMdseCd = checkMdseCd(bizMsg, inputMdseValue, custMdseCd, mnfItemCd, origMdseCd);
        String mdseCd = line.mdseCd_LL.getValue();

        MDSETMsg mdseTMsg = NWXMdseTMsgThreadLocalCache.getInstance().get(bizMsg.glblCmpyCd.getValue(), mdseCd);

        if (mdseTMsg == null) {
            return false;
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("mdseCd", mdseTMsg.mdseCd.getValue());
        params.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        params.put("isCalledMdl", String.valueOf(false));
        S21SsmEZDResult ssmResult = NWAL2200Query.getInstance().getMdseInfo(params);
        if (!ssmResult.isCodeNotFound()) {
            @SuppressWarnings("unchecked")
            Map<String, String> mdseInfo = (Map<String, String>) ssmResult.getResultObject();
            ZYPEZDItemValueSetter.setValue(line.custUomCd_LL, mdseInfo.get("PKG_UOM_CD"));
            ZYPEZDItemValueSetter.setValue(line.pkgUomDescTxt_LL, mdseInfo.get("PKG_UOM_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(line.coaMdseTpDescTxt_LL, mdseInfo.get("COA_MDSE_TP_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(line.coaProdDescTxt_LL, mdseInfo.get("COA_PROD_DESC_TXT"));
        }

        ZYPEZDItemValueSetter.setValue(line.mdseCd_LL, inputMdseCd);
        ZYPEZDItemValueSetter.setValue(line.mdseNm_LL, mdseTMsg.mdseNm);

        String leaseByotMdseCd = ZYPCodeDataUtil.getVarCharConstValue("LEASE_BYOT_MDSE_CD", bizMsg.glblCmpyCd.getValue());
        if (inputMdseCd.equals(leaseByotMdseCd)) {
            ZYPEZDItemValueSetter.setValue(line.dsOrdLineCatgCd_LL, DS_ORD_LINE_CATG.LEASE_BUYOUT);
        }

        if (ZYPCommonFunc.hasValue(line.ordCustUomQty_LL)) {
            BigDecimal eachQty = getEachQty(bizMsg, mdseCd, line.custUomCd_LL.getValue(), line.ordCustUomQty_LL.getValue());

            ZYPEZDItemValueSetter.setValue(line.ordQty_LL, eachQty);
            if (eachQty != null && !checkQtyForSerializedItem(bizMsg, mdseCd, eachQty.intValue())) {
                inputMdseValue.setErrorInfo(1, NWAM0772E);
                return false;
            }
            // QC#18175 2017/03/30 Add Start
            if (!ZYPCommonFunc.hasValue(eachQty)) {
                inputMdseValue.setErrorInfo(1, NWAM0935E);
                return false;
            }
            // QC#18175 2017/03/30 Add End
        }
        return true;
    }

    /**
     * Check Merchandise Code
     * @param bizMsg NWAL2200CMsg
     * @param inputMdseCd Merchandise Code
     * @param custMdseCd Customer Merchandise Code
     * @param mnfItemCd Manufacturer Item Code
     * @param origMdseCd Original Merchandise Code
     * @return Checked Merchandise Code
     */
    private static String checkMdseCd(NWAL2200CMsg bizMsg, EZDSStringItem inputMdseCd, EZDSStringItem custMdseCd, EZDSStringItem mnfItemCd, EZDSStringItem origMdseCd) {

        ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_OFF_N);
        String inputMdseCdValue = inputMdseCd.getValue();

        ALL_MDSE_VTMsgArray allMdseViewArray = getAllMdseViewArray(bizMsg, inputMdseCdValue);
        if (allMdseViewArray.getValidCount() == 1) {
            String mdseCd = allMdseViewArray.no(0).mdseCd.getValue();
            ZYPEZDItemValueSetter.setValue(custMdseCd, getCustMdseCd(bizMsg, mdseCd));
            ZYPEZDItemValueSetter.setValue(mnfItemCd, getMnfItemCd(bizMsg, mdseCd));
            ZYPEZDItemValueSetter.setValue(origMdseCd, mdseCd);
            return mdseCd;
        } else if (allMdseViewArray.getValidCount() > 1) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_ON_Y);
            return null;
        }

        CUST_MDSE_XREFTMsgArray custMdseXrefArray = getCustMdseXrefArray(bizMsg, inputMdseCdValue);
        if (custMdseXrefArray.getValidCount() == 1) {
            String baseMdseCd = custMdseXrefArray.no(0).mdseCd.getValue();
            ZYPEZDItemValueSetter.setValue(custMdseCd, inputMdseCd);
            ZYPEZDItemValueSetter.setValue(mnfItemCd, getMnfItemCd(bizMsg, baseMdseCd));
            ZYPEZDItemValueSetter.setValue(origMdseCd, inputMdseCd);
            ZYPEZDItemValueSetter.setValue(inputMdseCd, baseMdseCd);
            return baseMdseCd;
        } else if (custMdseXrefArray.getValidCount() > 1) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_ON_Y);
            return null;
        }

        S21SsmEZDResult ssmResult = NWAL2200Query.getInstance().getBaseMdseCdFromMnfItemCd(bizMsg, inputMdseCdValue);
        if (ssmResult.isCodeNotFound()) {
            inputMdseCd.setErrorInfo(1, NWAM0181E, new String[] {"Item#" });
            return null;
        }

        String baseMdseCd = (String) ssmResult.getResultObject();
        ZYPEZDItemValueSetter.setValue(custMdseCd, getCustMdseCd(bizMsg, baseMdseCd));
        ZYPEZDItemValueSetter.setValue(mnfItemCd, inputMdseCd);
        ZYPEZDItemValueSetter.setValue(origMdseCd, inputMdseCd);
        ZYPEZDItemValueSetter.setValue(inputMdseCd, baseMdseCd);

        return baseMdseCd;
    }

    /**
     * Get ALL_MDSE_V Array
     * @param bizMsg NWAL2200CMsg
     * @param mdseCd Merchandise Code
     * @return ALL_MDSE_V Array
     */
    private static ALL_MDSE_VTMsgArray getAllMdseViewArray(NWAL2200CMsg bizMsg, String mdseCd) {

        final ALL_MDSE_VTMsg condition = new ALL_MDSE_VTMsg();
        condition.setSQLID("002");
        condition.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        condition.setConditionValue("mdseCd01", mdseCd);
        condition.setConditionValue("rgtnStsCd01", RGTN_STS.READY_FOR_ORDER_TAKING);
        return (ALL_MDSE_VTMsgArray) EZDTBLAccessor.findByCondition(condition);
    }

    /**
     * Get CUST_MDSE_XREF Array
     * @param bizMsg NWAL2200CMsg
     * @param mdseCd Merchandise Code
     * @return CUST_MDSE_XREF Array
     */
    private static CUST_MDSE_XREFTMsgArray getCustMdseXrefArray(NWAL2200CMsg bizMsg, String mdseCd) {

        final CUST_MDSE_XREFTMsg condition = new CUST_MDSE_XREFTMsg();
        condition.setSQLID("001");
        condition.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        condition.setConditionValue("custMdseCd01", mdseCd);
        condition.setConditionValue("dsAcctNum01", bizMsg.sellToCustCd.getValue());
        condition.setConditionValue("custMdseXrefEnblFlg01", ZYPConstant.FLG_ON_Y);
        return (CUST_MDSE_XREFTMsgArray) EZDTBLAccessor.findByCondition(condition);
    }

    /**
     * Get Customer MDSE Code
     * @param bizMsg NWAL2200CMsg
     * @param mdseCd Merchandise Code
     * @return Customer MDSE Code
     */
    private static String getCustMdseCd(NWAL2200CMsg bizMsg, String mdseCd) {

        final CUST_MDSE_XREFTMsg condition = new CUST_MDSE_XREFTMsg();
        condition.setSQLID("002");
        condition.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        condition.setConditionValue("mdseCd01", mdseCd);
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
     * @param bizMsg NWAL2200CMsg
     * @param mdseCd Merchandise Code
     * @return Manufacturer Item Code
     */
    private static String getMnfItemCd(NWAL2200CMsg bizMsg, String mdseCd) {

        final CUST_MDSE_XREFTMsg condition = new CUST_MDSE_XREFTMsg();
        condition.setSQLID("002");
        condition.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        condition.setConditionValue("mdseCd01", mdseCd);
        condition.setConditionValue("dsAcctNum01", bizMsg.sellToCustCd.getValue());
        condition.setConditionValue("custMdseXrefEnblFlg01", ZYPConstant.FLG_ON_Y);
        CUST_MDSE_XREFTMsgArray tMsgArray = (CUST_MDSE_XREFTMsgArray) EZDTBLAccessor.findByCondition(condition);

        if (tMsgArray == null || tMsgArray.getValidCount() == 0) {
            return null;
        }

        return tMsgArray.no(0).custMdseCd.getValue();
    }

    /**
     * Get Each Quantity
     * @param bizMsg NWAL2200CMsg
     * @param mdseCd Merchandise Code
     * @param pkgUomCd Unit of Measure Code
     * @param uomQty Unit of Measure uomQty
     * @return Each Quantity
     */
    private static BigDecimal getEachQty(NWAL2200CMsg bizMsg, String mdseCd, String pkgUomCd, BigDecimal uomQty) {

        MDSE_STORE_PKGTMsg tMsg = new MDSE_STORE_PKGTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.mdseCd, mdseCd);
        ZYPEZDItemValueSetter.setValue(tMsg.pkgUomCd, pkgUomCd);
        tMsg = (MDSE_STORE_PKGTMsg) EZDTBLAccessor.findByKey(tMsg);

        if (tMsg == null) {
            return null;
        }

        return uomQty.multiply(tMsg.inEachQty.getValue());
    }

    /**
     * Check Qty For Serialized Item
     * @param bizMsg NWAL2200CMsg
     * @param mdseCd MDSE Code
     * @param ordQty Order Qty
     * @return No Error : true
     */
    private static boolean checkQtyForSerializedItem(NWAL2200CMsg bizMsg, String mdseCd, int ordQty) {

        if (ordQty == 1 || !isExistOrdCatg(bizMsg, true)) {
            return true;
        }

        MDSETMsg tMsg = new MDSETMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.mdseCd, mdseCd);
        tMsg = (MDSETMsg) EZDTBLAccessor.findByKey(tMsg);

        if (tMsg == null || ZYPConstant.FLG_OFF_N.equals(tMsg.instlBaseCtrlFlg.getValue())) {
            return true;
        }

        return false;
    }

    /**
     * check Exist Order Category
     * @param bizMsg NWAL2200CMsg
     * @param ordCatgCtxTpCd Order Category Context Type Code
     * @param isCatgOnly Order Category Only
     * @return true: exist
     */
    private static boolean isExistOrdCatg(NWAL2200CMsg bizMsg, boolean isCatgOnly) {

        if (!ZYPCommonFunc.hasValue(bizMsg.dsOrdCatgCd)) {
            return false;
        }

        return NWAL2200Query.getInstance().isExistOrdCatg(bizMsg, ORD_CATG_CTX_TP.RETAIL_EQUIPMENT_ORDERS, isCatgOnly);
    }

    /**
     * leftString
     * @param originlVlue String
     * @param ezMsg EZDCMsg
     * @param itemName String
     * @return result string (valid length)
     */
    public static String leftString(String originlVlue, EZDCMsg ezMsg, String itemName) {

        EZDItemAttribute attr = ezMsg.getAttr(itemName);
        if (attr != null) {
            return leftString(originlVlue, attr.getDigit());
        } else {
            return originlVlue;
        }
    }

    private static String leftString(String originlVlue, int maxLength) {

        if (S21StringUtil.isEmpty(originlVlue)) {
            return "";
        }
        return ZYPCommonFunc.trimTail(S21StringUtil.subStringByLength(originlVlue, 0, maxLength));
    }

    // QC#17124 add Start
    public static boolean refreshInputData(NWAL2200CMsg bizMsg, NWAL2200SMsg glblMsg) {
        return refreshInputData(bizMsg, glblMsg, true);
    }

    public static boolean refreshInputData(NWAL2200CMsg bizMsg, NWAL2200SMsg glblMsg, boolean fullLineFlg) {
        List<String> selectedLineNum = new ArrayList<String>();

        NWAL2200CommonLogic.storeOnePageToSMsglblMsgForOutbound(glblMsg, bizMsg);
        NWAL2200CommonLogic.storeOnePageToSMsglblMsgForInbound(glblMsg, bizMsg);
        boolean hasError = false;

        // -----------------------------------
        // header tab
        // -----------------------------------

        // salesREP (for force edit able order source type)
        if (S21StringUtil.isEquals(bizMsg.dsImptOrdFrceEdtFlg.getValue(), ZYPConstant.FLG_ON_Y)) {

            if (!ZYPCommonFunc.hasValue(bizMsg.slsRepTocNm) && !ZYPCommonFunc.hasValue(bizMsg.slsRepPsnNum)) {

                bizMsg.slsRepTocCd.clear();
                bizMsg.coaBrCd.clear();
                bizMsg.coaExtnCd.clear();
            } else {

                Map<String, Object> slsRep = getSlsRepTocCdByNameAndResourceId(bizMsg.glblCmpyCd.getValue(), bizMsg.slsRepTocNm.getValue(), bizMsg.slsRepPsnNum.getValue(), bizMsg.slsDt.getValue());
                if (slsRep == null) {

                    bizMsg.slsRepTocCd.clear();
                    bizMsg.coaBrCd.clear();
                    bizMsg.coaExtnCd.clear();
                    if (ZYPCommonFunc.hasValue(bizMsg.slsRepTocNm)) {
                        bizMsg.slsRepTocNm.setErrorInfo(1, NWAM0181E, new String[] {"Sales Rep" });
                    }
                    if (ZYPCommonFunc.hasValue(bizMsg.slsRepPsnNum)) {
                        bizMsg.slsRepPsnNum.setErrorInfo(1, NWAM0181E, new String[] {"Sales Rep" });
                    }
                    hasError = true;
                } else {

                    ZYPEZDItemValueSetter.setValue(bizMsg.slsRepTocCd, (String) slsRep.get("SLS_REP_TOC_CD"));
                    ZYPEZDItemValueSetter.setValue(bizMsg.coaBrCd, (String) slsRep.get("COA_BR_CD"));
                    ZYPEZDItemValueSetter.setValue(bizMsg.coaExtnCd, (String) slsRep.get("COA_EXTN_CD"));
                }
            }
        }

        // UPDATE START 2016/08/31 Unit Test#202
        // is Prospect Customer
        // 2018/01/23 QC#18798 Mod Start
        //if (CPO_SRC_TP.SOM.equals(bizMsg.cpoSrcTpCd.getValue())) {
        if (CPO_SRC_TP.SOM.equals(bizMsg.cpoSrcTpCd.getValue()) || CPO_SRC_TP.EOPS.equals(bizMsg.cpoSrcTpCd.getValue())) {
        // 2018/01/23 QC#18798 Mod End
            if (ZYPCommonFunc.hasValue(bizMsg.shipToCustAcctCd) && NWAL2200Query.getInstance().isProspect(bizMsg, bizMsg.shipToCustAcctCd.getValue())) {
                bizMsg.shipToCustAcctCd.setErrorInfo(1, NWAM0883E);
                hasError = true;
            }
            if (ZYPCommonFunc.hasValue(bizMsg.billToCustAcctCd) && NWAL2200Query.getInstance().isProspect(bizMsg, bizMsg.billToCustAcctCd.getValue())) {
                bizMsg.billToCustAcctCd.setErrorInfo(1, NWAM0883E);
                hasError = true;
            }
            if (ZYPCommonFunc.hasValue(bizMsg.sellToCustCd) && NWAL2200Query.getInstance().isProspect(bizMsg, bizMsg.sellToCustCd.getValue())) {
                bizMsg.sellToCustCd.setErrorInfo(1, NWAM0883E);
                hasError = true;
            }
        }
        // UPDATE END 2016/08/31 Unit Test#202

        if (hasError) {

            bizMsg.xxDplyTab.setValue(TAB_HEADER);
            return false;
        }

        // -----------------------------------
        // additional header tab
        // -----------------------------------

        // freight terms
        if (!ZYPCommonFunc.hasValue(bizMsg.frtCondDescTxt)) {

            bizMsg.frtCondCd.clear();
        } else {

            String frtCondCd = getFrtCondCdByName(bizMsg.glblCmpyCd.getValue(), bizMsg.frtCondDescTxt.getValue());
            if (frtCondCd == null) {

                bizMsg.frtCondDescTxt.setErrorInfo(1, NWAM0181E, new String[] {"Freight Terms" });
                hasError = true;
            } else {

                ZYPEZDItemValueSetter.setValue(bizMsg.frtCondCd, frtCondCd);
            }
        }

        // carrier service level
        if (!ZYPCommonFunc.hasValue(bizMsg.carrSvcLvlDescTxt)) {

            bizMsg.carrSvcLvlCd.clear();
        } else {

            String carrSvcLvlCd = getCarrSvcLvlCdByName(bizMsg.glblCmpyCd.getValue(), bizMsg.carrSvcLvlDescTxt.getValue());
            if (carrSvcLvlCd == null) {

                bizMsg.carrSvcLvlDescTxt.setErrorInfo(1, NWAM0181E, new String[] {"Carrier Service Level" });
                hasError = true;
            } else {

                ZYPEZDItemValueSetter.setValue(bizMsg.carrSvcLvlCd, carrSvcLvlCd);
            }
        }

        // payment terms
        if (!ZYPCommonFunc.hasValue(bizMsg.pmtTermCashDiscDescTxt)) {

            bizMsg.addPmtTermCashDiscCd.clear();
        } else {

            String pmtTermCashDiscCd = getPmtTermCashDiscCdByName(bizMsg.glblCmpyCd.getValue(), bizMsg.pmtTermCashDiscDescTxt.getValue());
            if (pmtTermCashDiscCd == null) {

                bizMsg.pmtTermCashDiscDescTxt.setErrorInfo(1, NWAM0181E, new String[] {"Payment Terms" });
                hasError = true;
            } else {

                ZYPEZDItemValueSetter.setValue(bizMsg.addPmtTermCashDiscCd, pmtTermCashDiscCd);
            }
            // QC#17474 2017/02/21 Add Start
            if (NWXC150001DsCheck.checkCcReq(bizMsg.glblCmpyCd.getValue(), bizMsg.billToCustAcctCd.getValue(), bizMsg.billToCustCd.getValue(), bizMsg.addPmtTermCashDiscCd.getValue())) {
                bizMsg.pmtTermCashDiscDescTxt.setErrorInfo(1, NWZM1839E);
            }
            // QC#17474 2017/02/21 Add End
        }

        if (hasError) {

            bizMsg.xxDplyTab.setValue(TAB_ADDL_HEADER);
            return false;
        }

        // UPDATE START 2016/08/31 Unit Test#202
        // -----------------------------------
        // line configuration tab Config
        // -----------------------------------
        // 2018/01/23 QC#18798 Mod Start
        //if (CPO_SRC_TP.SOM.equals(bizMsg.cpoSrcTpCd.getValue())) {
        if (CPO_SRC_TP.SOM.equals(bizMsg.cpoSrcTpCd.getValue()) || CPO_SRC_TP.EOPS.equals(bizMsg.cpoSrcTpCd.getValue())) {
        // 2018/01/23 QC#18798 Mod End
            for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
                // is Prospect Customer
                if (ZYPCommonFunc.hasValue(glblMsg.A.no(i).shipToCustAcctCd_LC) && NWAL2200Query.getInstance().isProspect(bizMsg, glblMsg.A.no(i).shipToCustAcctCd_LC.getValue())) {
                    bizMsg.A.no(i).shipToCustAcctCd_LC.setErrorInfo(1, NWAM0883E);
                    hasError = true;
                }
                if (ZYPCommonFunc.hasValue(glblMsg.A.no(i).billToCustAcctCd_LC) && NWAL2200Query.getInstance().isProspect(bizMsg, glblMsg.A.no(i).billToCustAcctCd_LC.getValue())) {
                    bizMsg.A.no(i).billToCustAcctCd_LC.setErrorInfo(1, NWAM0883E);
                    hasError = true;
                }
            }
        }
        // UPDATE END 2016/08/31 Unit Test#202

        // -----------------------------------
        // line configuration tab
        // -----------------------------------
        
        selectedLineNum.clear();
        if (!fullLineFlg) {
            List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(bizMsg.B, "xxChkBox_LL", ZYPConstant.FLG_ON_Y);
            for (int index : selectedRows) {
                selectedLineNum.add(bizMsg.B.no(index).xxLineNum_LL.getValue());
            }
        }
        for (int i = 0; i < glblMsg.B.getValidCount(); i++) {

            NWAL2200_BSMsg line = glblMsg.B.no(i);
            if(!fullLineFlg){
                if (!selectedLineNum.contains(line.xxLineNum_LL.getValue())) {
                    continue;
                }
            }
            if (!ZYPConstant.FLG_ON_Y.equals(line.imptLineFlg_LL.getValue())) {
                continue;
            }

            if (!ZYPCommonFunc.hasValue(line.rtlWhNm_LL)) {

                line.rtlWhCd_LL.clear();
            } else {

                // warehouse
                String rtlWhCd = getRtlWhCdByName(bizMsg.glblCmpyCd.getValue(), line.rtlWhNm_LL.getValue());
                if (rtlWhCd == null) {

                    line.rtlWhNm_LL.setErrorInfo(1, NWAM0181E, new String[] {"Warehouse" });
                    hasError = true;
                } else {

                    ZYPEZDItemValueSetter.setValue(line.rtlWhCd_LL, rtlWhCd);
                }
            }

            // QC#58230
            // sub warehouse
            if (CPO_SRC_TP.DEAL_CONFIGURATOR.equals(bizMsg.cpoSrcTpCd.getValue()) && ZYPCommonFunc.hasValue(line.rtlSwhNm_LL)) {

                MDSETMsg mdseTMsg = NWXMdseTMsgThreadLocalCache.getInstance().get(bizMsg.glblCmpyCd.getValue(), line.mdseCd_LL.getValue());

                if (mdseTMsg == null || ZYPConstant.FLG_OFF_N.equals(mdseTMsg.invtyCtrlFlg.getValue()) && !MDSE_TP.SET.equals(mdseTMsg.mdseTpCd.getValue())) {
                    line.rtlSwhCd_LL.clear();
                    line.rtlSwhNm_LL.clear();
                }
            } else if (!ZYPCommonFunc.hasValue(line.rtlWhNm_LL) || !ZYPCommonFunc.hasValue(line.rtlSwhNm_LL)) {

                line.rtlSwhCd_LL.clear();
            } else {

                String rtlSwhCd = getRtlSwhCdByName(bizMsg.glblCmpyCd.getValue(), line.rtlWhCd_LL.getValue(), line.rtlSwhNm_LL.getValue());
                if (rtlSwhCd == null) {

                    line.rtlWhNm_LL.setErrorInfo(1, NWAM0181E, new String[] {"Sub Warehouse" });
                    hasError = true;
                } else {

                    ZYPEZDItemValueSetter.setValue(line.rtlSwhCd_LL, rtlSwhCd);
                }
            }

            // Add Start 2017/10/02 QC#21181
            StringBuilder sbInvtyLocCd = new StringBuilder();
            if (ZYPCommonFunc.hasValue(line.rtlWhCd_LL)) {
                sbInvtyLocCd.append(line.rtlWhCd_LL.getValue());
            }
            if (ZYPCommonFunc.hasValue(line.rtlSwhCd_LL)) {
                sbInvtyLocCd.append(line.rtlSwhCd_LL.getValue());
            }
            ZYPEZDItemValueSetter.setValue(line.invtyLocCd_LL, sbInvtyLocCd.toString());
            // Add End 2017/10/02 QC#21181

            // Add Start 2017/06/06 QC#18583
            // Service Machine Master Pk
            NWAL2200CommonLogic.updateSvcMachMstrPk(bizMsg.glblCmpyCd.getValue(), line);
            // Add End 2017/06/06 QC#18583

            // 2018/03/08 QC#24588 Add Start
            if (!ZYPCommonFunc.hasValue(line.finItemLineFlg_LL)) {
                ZYPEZDItemValueSetter.setValue(line.finItemLineFlg_LL, ZYPConstant.FLG_OFF_N);
            }
            // 2018/03/08 QC#24588 Add End
        }

        if (hasError) {

            bizMsg.xxDplyTab.setValue(TAB_LINE_CONFIG);
            return false;
        }

        // UPDATE START 2016/08/31 Unit Test#202
        // -----------------------------------
        // RMA tab Config
        // -----------------------------------
        // 2018/01/23 QC#18798 Mod Start
        //if (CPO_SRC_TP.SOM.equals(bizMsg.cpoSrcTpCd.getValue())) {
        if (CPO_SRC_TP.SOM.equals(bizMsg.cpoSrcTpCd.getValue()) || CPO_SRC_TP.EOPS.equals(bizMsg.cpoSrcTpCd.getValue())) {
        // 2018/01/23 QC#18798 Mod End
            for (int i = 0; i < glblMsg.C.getValidCount(); i++) {
                // is Prospect Customer
                if (ZYPCommonFunc.hasValue(glblMsg.C.no(i).shipToCustAcctCd_RC) && NWAL2200Query.getInstance().isProspect(bizMsg, glblMsg.C.no(i).shipToCustAcctCd_RC.getValue())) {
                    bizMsg.C.no(i).shipToCustAcctCd_RC.setErrorInfo(1, NWAM0883E);
                    hasError = true;
                }
                if (ZYPCommonFunc.hasValue(glblMsg.C.no(i).billToCustAcctCd_RC) && NWAL2200Query.getInstance().isProspect(bizMsg, glblMsg.C.no(i).billToCustAcctCd_RC.getValue())) {
                    bizMsg.C.no(i).billToCustAcctCd_RC.setErrorInfo(1, NWAM0883E);
                    hasError = true;
                }
            }
        }
        // UPDATE END 2016/08/31 Unit Test#202
        // -----------------------------------
        // RMA tab
        // -----------------------------------
        selectedLineNum.clear();
        if (!fullLineFlg) {
            List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(bizMsg.D, "xxChkBox_RL", ZYPConstant.FLG_ON_Y);
            for (int index : selectedRows) {
                selectedLineNum.add(bizMsg.D.no(index).xxLineNum_RL.getValue());
            }
        }
        for (int i = 0; i < glblMsg.D.getValidCount(); i++) {

            NWAL2200_DSMsg line = glblMsg.D.no(i);

            if (!fullLineFlg) {
                if (!selectedLineNum.contains(line.xxLineNum_RL.getValue())) {
                    continue;
                }
            }
            if (!ZYPCommonFunc.hasValue(line.rtlWhNm_RL)) {

                line.rtlWhCd_RL.clear();
            } else {

                // warehouse
                String rtlWhCd = getRtlWhCdByName(bizMsg.glblCmpyCd.getValue(), line.rtlWhNm_RL.getValue());
                if (rtlWhCd == null) {

                    line.rtlWhNm_RL.setErrorInfo(1, NWAM0181E, new String[] {"Warehouse" });
                    hasError = true;
                } else {

                    ZYPEZDItemValueSetter.setValue(line.rtlWhCd_RL, rtlWhCd);
                }
            }

            // sub warehouse
            if (!ZYPCommonFunc.hasValue(line.rtlWhNm_RL) || !ZYPCommonFunc.hasValue(line.rtlSwhNm_RL)) {

                line.rtlSwhCd_RL.clear();
            } else {

                String rtlSwhCd = getRtlSwhCdByName(bizMsg.glblCmpyCd.getValue(), line.rtlWhCd_RL.getValue(), line.rtlSwhNm_RL.getValue());
                if (rtlSwhCd == null) {

                    line.rtlWhNm_RL.setErrorInfo(1, NWAM0181E, new String[] {"Sub Warehouse" });
                    hasError = true;
                } else {

                    ZYPEZDItemValueSetter.setValue(line.rtlSwhCd_RL, rtlSwhCd);
                }
            }

            // Add Start 2017/10/02 QC#21181
            StringBuilder sbInvtyLocCd = new StringBuilder();
            if (ZYPCommonFunc.hasValue(line.rtlWhCd_RL)) {
                sbInvtyLocCd.append(line.rtlWhCd_RL.getValue());
            }
            if (ZYPCommonFunc.hasValue(line.rtlSwhCd_RL)) {
                sbInvtyLocCd.append(line.rtlSwhCd_RL.getValue());
            }
            ZYPEZDItemValueSetter.setValue(line.invtyLocCd_RL, sbInvtyLocCd.toString());
            // Add End 2017/10/02 QC#21181

            // Add Start 2017/06/06 QC#18583
            // Service Machine Master Pk
            NWAL2200CommonLogic.updateSvcMachMstrPkRMA(bizMsg.glblCmpyCd.getValue(), line);
            // Add End 2017/06/06 QC#18583
        }

        if (hasError) {

            bizMsg.xxDplyTab.setValue(TAB_RMA);
            return false;
        }

        return !hasError;
    }

    private static Map<String, Object> getSlsRepTocCdByNameAndResourceId(String glblCmpyCd, String slsRepTocNm, String slsRepPsnNum, String slsDt) {

        if (S21StringUtil.isEmpty(slsRepTocNm) && S21StringUtil.isEmpty(slsRepPsnNum)) {
            return null;
        }

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("slsRepTocNm", slsRepTocNm);
        ssmParam.put("slsRepPsnNum", slsRepPsnNum);
        ssmParam.put("slsDt", slsDt);
        ssmParam.put("slsRepFlg", ZYPConstant.FLG_ON_Y);
        ssmParam.put("orgStruTpCd", ORG_STRU_TP.BASIC_ORGANIZATION_STRUCTURE_FOR_SALES_SUMMARY);
        ssmParam.put("rgtnStsCd", RGTN_STS.TERMINATED);

        S21SsmEZDResult ssmResult = NWAL2200Query.getInstance().getSlsRepTocCdByNameAndResourceId(ssmParam);
        if (ssmResult.isCodeNotFound()) {
            return null;
        }
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> result = (List<Map<String, Object>>) ssmResult.getResultObject();

        return (Map<String, Object>) result.get(0);
    }

    public static String getFrtCondCdByName(String glblCmpyCd, String frtCondDescTxt) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("frtCondDescTxt", frtCondDescTxt);

        S21SsmEZDResult ssmResult = NWAL2200Query.getInstance().getFrtCondCdByName(ssmParam);
        if (ssmResult.isCodeNotFound()) {
            return null;
        }
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> result = (List<Map<String, Object>>) ssmResult.getResultObject();

        return (String) result.get(0).get("FRT_COND_CD");
    }

    public static String getCarrSvcLvlCdByName(String glblCmpyCd, String carrSvcLvlDescTxt) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("carrSvcLvlDescTxt", carrSvcLvlDescTxt.toUpperCase()); // S21_NA#11595

        S21SsmEZDResult ssmResult = NWAL2200Query.getInstance().getCarrSvcLvlCdByName(ssmParam);
        if (ssmResult.isCodeNotFound()) {
            return null;
        }
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> result = (List<Map<String, Object>>) ssmResult.getResultObject();

        return (String) result.get(0).get("CARR_SVC_LVL_CD");
    }

    private static String getPmtTermCashDiscCdByName(String glblCmpyCd, String pmtTermCashDiscDescTxt) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("pmtTermCashDiscDescTxt", pmtTermCashDiscDescTxt);

        S21SsmEZDResult ssmResult = NWAL2200Query.getInstance().getPmtTermCashDiscCdByName(ssmParam);
        if (ssmResult.isCodeNotFound()) {
            return null;
        }
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> result = (List<Map<String, Object>>) ssmResult.getResultObject();

        return (String) result.get(0).get("PMT_TERM_CASH_DISC_CD");
    }

    private static String getRtlWhCdByName(String glblCmpyCd, String rtlWhNm) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("rtlWhNm", rtlWhNm);

        S21SsmEZDResult ssmResult = NWAL2200Query.getInstance().getRtlWhCdByName(ssmParam);
        if (ssmResult.isCodeNotFound()) {
            return null;
        }
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> result = (List<Map<String, Object>>) ssmResult.getResultObject();

        return (String) result.get(0).get("RTL_WH_CD");
    }

    private static String getRtlSwhCdByName(String glblCmpyCd, String rtlWhCd, String rtlSwhNm) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("rtlWhCd", rtlWhCd);
        ssmParam.put("rtlSwhNm", rtlSwhNm);

        S21SsmEZDResult ssmResult = NWAL2200Query.getInstance().getRtlSwhCdByName(ssmParam);
        if (ssmResult.isCodeNotFound()) {
            return null;
        }
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> result = (List<Map<String, Object>>) ssmResult.getResultObject();

        return (String) result.get(0).get("RTL_SWH_CD");
    }

    public static boolean deriveCustomerAccount(NWAL2200CMsg bizMsg, NWAL2200SMsg glblMsg) {

        NWXC220001Result<NWXC220001CustomerBean> result;
        NWAL2200_ACMsg lineACMsg;
        NWAL2200_ASMsg lineASMsg;
        BILL_TO_CUSTTMsg inBillTMsg;
        SHIP_TO_CUSTTMsg inShipTMsg;

        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            lineACMsg = bizMsg.A.no(i);
            lineASMsg = glblMsg.A.no(i);
            if (!lineACMsg.billToCustLocCd_LC.getValue().equals(lineASMsg.billToCustLocCd_LC.getValue()) || (ZYPCommonFunc.hasValue(lineACMsg.billToCustLocCd_LC) && !ZYPCommonFunc.hasValue(lineACMsg.billToCustAcctCd_LC))) {
                inBillTMsg = new BILL_TO_CUSTTMsg();

                ZYPEZDItemValueSetter.setValue(inBillTMsg.glblCmpyCd, bizMsg.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(inBillTMsg.billToCustCd, lineACMsg.billToCustLocCd_LC);
                ZYPEZDItemValueSetter.setValue(inBillTMsg.rgtnStsCd, RGTN_STS.READY_FOR_ORDER_TAKING);

                result = NWXC220001.getBillToCustWithDsAcctFromCd(inBillTMsg, bizMsg.slsDt.getValue(), NWXC220001Constant.CUSTOMER_TABLE_ID.BILL_TO_CUST);

                if (result.hasResultObject()) {
                    ZYPEZDItemValueSetter.setValue(lineACMsg.billToCustAcctCd_LC, result.getResultObject().billToCustTMsg.sellToCustCd);
                } else {
                    lineACMsg.billToCustAcctCd_LC.clear();
                }
            }

            // S21_NA#11429 modify start
            if (ZYPCommonFunc.hasValue(lineACMsg.shipToCustLocCd_LC)) {
                if (!lineACMsg.shipToCustLocCd_LC.getValue().equals(lineASMsg.shipToCustLocCd_LC.getValue())
                        || (ZYPCommonFunc.hasValue(lineACMsg.shipToCustLocCd_LC) && !ZYPCommonFunc.hasValue(lineACMsg.shipToCustAcctCd_LC) || !ZYPCommonFunc.hasValue(lineACMsg.shipToFirstLineAddr_LC))) {
                    inShipTMsg = new SHIP_TO_CUSTTMsg();

                    ZYPEZDItemValueSetter.setValue(inShipTMsg.glblCmpyCd, bizMsg.glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(inShipTMsg.shipToCustCd, lineACMsg.shipToCustLocCd_LC);
                    ZYPEZDItemValueSetter.setValue(inShipTMsg.rgtnStsCd, RGTN_STS.READY_FOR_ORDER_TAKING);

                    result = NWXC220001.getShipToCustWithDsAcctFromCd(inShipTMsg, bizMsg.slsDt.getValue(), NWXC220001Constant.CUSTOMER_TABLE_ID.SHIP_TO_CUST);

                    if (result.hasResultObject()) {
                        SHIP_TO_CUSTTMsg shipTo = result.getResultObject().shipToCustTMsg;
                        ZYPEZDItemValueSetter.setValue(lineACMsg.shipToCustAcctCd_LC, shipTo.sellToCustCd);
                        ZYPEZDItemValueSetter.setValue(lineACMsg.shipToFirstRefCmntTxt_LC, shipTo.firstRefCmntTxt);
                        ZYPEZDItemValueSetter.setValue(lineACMsg.shipToScdRefCmntTxt_LC, shipTo.scdRefCmntTxt);
                        ZYPEZDItemValueSetter.setValue(lineACMsg.shipToFirstLineAddr_LC, shipTo.firstLineAddr);
                        ZYPEZDItemValueSetter.setValue(lineACMsg.shipToScdLineAddr_LC, shipTo.scdLineAddr);
                        ZYPEZDItemValueSetter.setValue(lineACMsg.shipToThirdLineAddr_LC, shipTo.thirdLineAddr);
                        ZYPEZDItemValueSetter.setValue(lineACMsg.shipToFrthLineAddr_LC, shipTo.frthLineAddr);
                        ZYPEZDItemValueSetter.setValue(lineACMsg.shipToCtyAddr_LC, shipTo.ctyAddr);
                        ZYPEZDItemValueSetter.setValue(lineACMsg.shipToCntyNm_LC, result.getResultObject().shipCntyNm);
                        ZYPEZDItemValueSetter.setValue(lineACMsg.shipToProvNm_LC, shipTo.provNm);
                        ZYPEZDItemValueSetter.setValue(lineACMsg.shipToStCd_LC, shipTo.stCd);
                        ZYPEZDItemValueSetter.setValue(lineACMsg.shipToPostCd_LC, shipTo.postCd);
                        ZYPEZDItemValueSetter.setValue(lineACMsg.shipToCtryCd_LC, shipTo.ctryCd);
                        ZYPEZDItemValueSetter.setValue(lineACMsg.shipToLocNm_LC, shipTo.locNm);
                        ZYPEZDItemValueSetter.setValue(lineACMsg.dropShipFlg_LC, ZYPConstant.FLG_OFF_N);
                    } else {
                        lineACMsg.shipToCustLocCd_LC.clear();
                        lineACMsg.shipToCustAcctCd_LC.clear();
                        lineACMsg.shipToFirstRefCmntTxt_LC.clear();
                        lineACMsg.shipToScdRefCmntTxt_LC.clear();
                        lineACMsg.shipToFirstLineAddr_LC.clear();
                        lineACMsg.shipToScdLineAddr_LC.clear();
                        lineACMsg.shipToThirdLineAddr_LC.clear();
                        lineACMsg.shipToFrthLineAddr_LC.clear();
                        lineACMsg.shipToCtyAddr_LC.clear();
                        lineACMsg.shipToCntyNm_LC.clear();
                        lineACMsg.shipToProvNm_LC.clear();
                        lineACMsg.shipToStCd_LC.clear();
                        lineACMsg.shipToPostCd_LC.clear();
                        lineACMsg.shipToCtryCd_LC.clear();
                        lineACMsg.shipToLocNm_LC.clear();
                        ZYPEZDItemValueSetter.setValue(lineACMsg.dropShipFlg_LC, ZYPConstant.FLG_OFF_N);
                    }
                }
            } else {
                lineACMsg.shipToCustLocCd_LC.clear();
                lineACMsg.shipToCustAcctCd_LC.clear();
                lineACMsg.shipToFirstRefCmntTxt_LC.clear();
                lineACMsg.shipToScdRefCmntTxt_LC.clear();
                lineACMsg.shipToFirstLineAddr_LC.clear();
                lineACMsg.shipToScdLineAddr_LC.clear();
                lineACMsg.shipToThirdLineAddr_LC.clear();
                lineACMsg.shipToFrthLineAddr_LC.clear();
                lineACMsg.shipToCtyAddr_LC.clear();
                lineACMsg.shipToCntyNm_LC.clear();
                lineACMsg.shipToProvNm_LC.clear();
                lineACMsg.shipToStCd_LC.clear();
                lineACMsg.shipToPostCd_LC.clear();
                lineACMsg.shipToCtryCd_LC.clear();
                lineACMsg.shipToLocNm_LC.clear();
                ZYPEZDItemValueSetter.setValue(lineACMsg.dropShipFlg_LC, ZYPConstant.FLG_OFF_N);
            }
            // QC#15638
            if (!CPO_SRC_TP.INVENTORY_REQUEST_ENTRY.equals(bizMsg.cpoSrcTpCd.getValue())) {
                copyShipToCustToDetail(lineACMsg, bizMsg.B);
            }
            // S21_NA#11429 modify end
        }

        NWAL2200_CCMsg lineCCMsg;
        NWAL2200_CSMsg lineCSMsg;

        for (int i = 0; i < bizMsg.C.getValidCount(); i++) {
            lineCCMsg = bizMsg.C.no(i);
            lineCSMsg = glblMsg.C.no(i);
            if (!lineCCMsg.billToCustLocCd_RC.getValue().equals(lineCSMsg.billToCustLocCd_RC.getValue()) || (ZYPCommonFunc.hasValue(lineCCMsg.billToCustLocCd_RC) && !ZYPCommonFunc.hasValue(lineCCMsg.billToCustAcctCd_RC))) {
                inBillTMsg = new BILL_TO_CUSTTMsg();

                ZYPEZDItemValueSetter.setValue(inBillTMsg.glblCmpyCd, bizMsg.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(inBillTMsg.billToCustCd, lineCCMsg.billToCustLocCd_RC);
                ZYPEZDItemValueSetter.setValue(inBillTMsg.rgtnStsCd, RGTN_STS.READY_FOR_ORDER_TAKING);

                result = NWXC220001.getBillToCustWithDsAcctFromCd(inBillTMsg, bizMsg.slsDt.getValue(), NWXC220001Constant.CUSTOMER_TABLE_ID.BILL_TO_CUST);

                if (result.hasResultObject()) {
                    ZYPEZDItemValueSetter.setValue(lineCCMsg.billToCustAcctCd_RC, result.getResultObject().billToCustTMsg.sellToCustCd);
                } else {
                    lineCCMsg.billToCustAcctCd_RC.clear();
                }
            }

            // S21_NA#11429 modify start
            if (ZYPCommonFunc.hasValue(lineCCMsg.shipToCustLocCd_RC)) {
                if (!lineCCMsg.shipToCustLocCd_RC.getValue().equals(lineCSMsg.shipToCustLocCd_RC.getValue())
                        || (ZYPCommonFunc.hasValue(lineCCMsg.shipToCustLocCd_RC) && !ZYPCommonFunc.hasValue(lineCCMsg.shipToCustAcctCd_RC) || !ZYPCommonFunc.hasValue(lineCCMsg.shipToFirstLineAddr_RC))) {
                    inShipTMsg = new SHIP_TO_CUSTTMsg();

                    ZYPEZDItemValueSetter.setValue(inShipTMsg.glblCmpyCd, bizMsg.glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(inShipTMsg.shipToCustCd, lineCCMsg.shipToCustLocCd_RC);
                    ZYPEZDItemValueSetter.setValue(inShipTMsg.rgtnStsCd, RGTN_STS.READY_FOR_ORDER_TAKING);

                    result = NWXC220001.getShipToCustWithDsAcctFromCd(inShipTMsg, bizMsg.slsDt.getValue(), NWXC220001Constant.CUSTOMER_TABLE_ID.SHIP_TO_CUST);

                    if (result.hasResultObject()) {
                        SHIP_TO_CUSTTMsg shipTo = result.getResultObject().shipToCustTMsg;
                        ZYPEZDItemValueSetter.setValue(lineCCMsg.shipToCustAcctCd_RC, result.getResultObject().shipToCustTMsg.sellToCustCd);
                        ZYPEZDItemValueSetter.setValue(lineCCMsg.shipToFirstRefCmntTxt_RC, shipTo.firstRefCmntTxt);
                        ZYPEZDItemValueSetter.setValue(lineCCMsg.shipToScdRefCmntTxt_RC, shipTo.scdRefCmntTxt);
                        ZYPEZDItemValueSetter.setValue(lineCCMsg.shipToFirstLineAddr_RC, shipTo.firstLineAddr);
                        ZYPEZDItemValueSetter.setValue(lineCCMsg.shipToScdLineAddr_RC, shipTo.scdLineAddr);
                        ZYPEZDItemValueSetter.setValue(lineCCMsg.shipToThirdLineAddr_RC, shipTo.thirdLineAddr);
                        ZYPEZDItemValueSetter.setValue(lineCCMsg.shipToFrthLineAddr_RC, shipTo.frthLineAddr);
                        ZYPEZDItemValueSetter.setValue(lineCCMsg.shipToCtyAddr_RC, shipTo.ctyAddr);
                        ZYPEZDItemValueSetter.setValue(lineCCMsg.shipToCntyNm_RC, result.getResultObject().shipCntyNm);
                        ZYPEZDItemValueSetter.setValue(lineCCMsg.shipToProvNm_RC, shipTo.provNm);
                        ZYPEZDItemValueSetter.setValue(lineCCMsg.shipToStCd_RC, shipTo.stCd);
                        ZYPEZDItemValueSetter.setValue(lineCCMsg.shipToPostCd_RC, shipTo.postCd);
                        ZYPEZDItemValueSetter.setValue(lineCCMsg.shipToCtryCd_RC, shipTo.ctryCd);
                        ZYPEZDItemValueSetter.setValue(lineCCMsg.shipToLocNm_RC, shipTo.locNm);
                        ZYPEZDItemValueSetter.setValue(lineCCMsg.dropShipFlg_RC, ZYPConstant.FLG_OFF_N);
                    } else {
                        lineCCMsg.shipToCustLocCd_RC.clear();
                        lineCCMsg.shipToCustAcctCd_RC.clear();
                        lineCCMsg.shipToFirstRefCmntTxt_RC.clear();
                        lineCCMsg.shipToScdRefCmntTxt_RC.clear();
                        lineCCMsg.shipToFirstLineAddr_RC.clear();
                        lineCCMsg.shipToScdLineAddr_RC.clear();
                        lineCCMsg.shipToThirdLineAddr_RC.clear();
                        lineCCMsg.shipToFrthLineAddr_RC.clear();
                        lineCCMsg.shipToCtyAddr_RC.clear();
                        lineCCMsg.shipToCntyNm_RC.clear();
                        lineCCMsg.shipToProvNm_RC.clear();
                        lineCCMsg.shipToStCd_RC.clear();
                        lineCCMsg.shipToPostCd_RC.clear();
                        lineCCMsg.shipToCtryCd_RC.clear();
                        lineCCMsg.shipToLocNm_RC.clear();
                        ZYPEZDItemValueSetter.setValue(lineCCMsg.dropShipFlg_RC, ZYPConstant.FLG_OFF_N);
                    }
                }
            } else {
                lineCCMsg.shipToCustLocCd_RC.clear();
                lineCCMsg.shipToCustAcctCd_RC.clear();
                lineCCMsg.shipToFirstRefCmntTxt_RC.clear();
                lineCCMsg.shipToScdRefCmntTxt_RC.clear();
                lineCCMsg.shipToFirstLineAddr_RC.clear();
                lineCCMsg.shipToScdLineAddr_RC.clear();
                lineCCMsg.shipToThirdLineAddr_RC.clear();
                lineCCMsg.shipToFrthLineAddr_RC.clear();
                lineCCMsg.shipToCtyAddr_RC.clear();
                lineCCMsg.shipToCntyNm_RC.clear();
                lineCCMsg.shipToProvNm_RC.clear();
                lineCCMsg.shipToStCd_RC.clear();
                lineCCMsg.shipToPostCd_RC.clear();
                lineCCMsg.shipToCtryCd_RC.clear();
                lineCCMsg.shipToLocNm_RC.clear();
                ZYPEZDItemValueSetter.setValue(lineCCMsg.dropShipFlg_RC, ZYPConstant.FLG_OFF_N);
            }
            copyShipToCustToDetail(lineCCMsg, bizMsg.D);
            // S21_NA#11429 modify end
        }

        return true;
    }

    private static void copyShipToCustToDetail(NWAL2200_ACMsg config, NWAL2200_BCMsgArray lineArray) {

        String dsCpoPosnNum = config.dsOrdPosnNum_LC.getValue();
        for (int i = 0; i < lineArray.getValidCount(); i++) {
            NWAL2200_BCMsg line = lineArray.no(i);
            if (S21StringUtil.isEquals(line.dsOrdPosnNum_LL.getValue(), dsCpoPosnNum)) {
                ZYPEZDItemValueSetter.setValue(line.shipToCustCd_LL, config.shipToCustLocCd_LC);
                ZYPEZDItemValueSetter.setValue(line.shipToFirstRefCmntTxt_LL, config.shipToFirstRefCmntTxt_LC);
                ZYPEZDItemValueSetter.setValue(line.shipToScdRefCmntTxt_LL, config.shipToScdRefCmntTxt_LC);
                ZYPEZDItemValueSetter.setValue(line.shipToFirstLineAddr_LL, config.shipToFirstLineAddr_LC);
                ZYPEZDItemValueSetter.setValue(line.shipToScdLineAddr_LL, config.shipToScdLineAddr_LC);
                ZYPEZDItemValueSetter.setValue(line.shipToThirdLineAddr_LL, config.shipToThirdLineAddr_LC);
                ZYPEZDItemValueSetter.setValue(line.shipToFrthLineAddr_LL, config.shipToFrthLineAddr_LC);
                ZYPEZDItemValueSetter.setValue(line.shipToCtyAddr_LL, config.shipToCtyAddr_LC);
                ZYPEZDItemValueSetter.setValue(line.shipToCntyNm_LL, config.shipToCntyNm_LC);
                ZYPEZDItemValueSetter.setValue(line.shipToProvNm_LL, config.shipToProvNm_LC);
                ZYPEZDItemValueSetter.setValue(line.shipToStCd_LL, config.shipToStCd_LC);
                ZYPEZDItemValueSetter.setValue(line.shipToPostCd_LL, config.shipToPostCd_LC);
                ZYPEZDItemValueSetter.setValue(line.shipToCtryCd_LL, config.shipToCtryCd_LC);
                ZYPEZDItemValueSetter.setValue(line.shipToLocNm_LL, config.shipToLocNm_LC);
            }
        }
    }

    private static void copyShipToCustToDetail(NWAL2200_CCMsg config, NWAL2200_DCMsgArray lineArray) {

        String dsCpoPosnNum = config.dsOrdPosnNum_RC.getValue();
        for (int i = 0; i < lineArray.getValidCount(); i++) {
            NWAL2200_DCMsg line = lineArray.no(i);
            if (S21StringUtil.isEquals(line.dsOrdPosnNum_RL.getValue(), dsCpoPosnNum)) {
                ZYPEZDItemValueSetter.setValue(line.shipToCustCd_RL, config.shipToCustLocCd_RC);
                ZYPEZDItemValueSetter.setValue(line.shipToFirstRefCmntTxt_RL, config.shipToFirstRefCmntTxt_RC);
                ZYPEZDItemValueSetter.setValue(line.shipToScdRefCmntTxt_RL, config.shipToScdRefCmntTxt_RC);
                ZYPEZDItemValueSetter.setValue(line.shipToFirstLineAddr_RL, config.shipToFirstLineAddr_RC);
                ZYPEZDItemValueSetter.setValue(line.shipToScdLineAddr_RL, config.shipToScdLineAddr_RC);
                ZYPEZDItemValueSetter.setValue(line.shipToThirdLineAddr_RL, config.shipToThirdLineAddr_RC);
                ZYPEZDItemValueSetter.setValue(line.shipToFrthLineAddr_RL, config.shipToFrthLineAddr_RC);
                ZYPEZDItemValueSetter.setValue(line.shipToCtyAddr_RL, config.shipToCtyAddr_RC);
                ZYPEZDItemValueSetter.setValue(line.shipToCntyNm_RL, config.shipToCntyNm_RC);
                ZYPEZDItemValueSetter.setValue(line.shipToProvNm_RL, config.shipToProvNm_RC);
                ZYPEZDItemValueSetter.setValue(line.shipToStCd_RL, config.shipToStCd_RC);
                ZYPEZDItemValueSetter.setValue(line.shipToPostCd_RL, config.shipToPostCd_RC);
                ZYPEZDItemValueSetter.setValue(line.shipToCtryCd_RL, config.shipToCtryCd_RC);
                ZYPEZDItemValueSetter.setValue(line.shipToLocNm_RL, config.shipToLocNm_RC);
            }
        }
    }
    // QC#17124 add End

    // Add Start 2017/06/06 QC#18583
    public static void updateSvcMachMstrPk(String glblCmpyCd, NWAL2200_BSMsg line) {

        String newSerNum = line.serNum_LL.getValue();
        String mdseCd = line.mdseCd_LL.getValue();
        // QC#22658 2017/11/20 Add Start
        MDSETMsg mdseTMsg = getMdse(glblCmpyCd, mdseCd);
        if (mdseTMsg == null || !ZYPConstant.FLG_ON_Y.equals(mdseTMsg.shpgSerTakeFlg.getValue())) {
            return;
        }
        // QC#22658 2017/11/20 Add End
        if (ZYPCommonFunc.hasValue(newSerNum)) {
            S21SsmEZDResult ssmResult = NWAL2200Query.getInstance().getSvcMachMstrPk(glblCmpyCd, mdseCd, newSerNum);

            if (ssmResult.isCodeNotFound()) {
                line.svcMachMstrPk_LL.clear();
            } else {
                BigDecimal svcMachMstrPk = (BigDecimal) ssmResult.getResultObject();
                ZYPEZDItemValueSetter.setValue(line.svcMachMstrPk_LL, svcMachMstrPk);
            }
        } else {
            line.svcMachMstrPk_LL.clear();
        }
    }

    public static void updateSvcMachMstrPkRMA(String glblCmpyCd, NWAL2200_DSMsg line) {

        String newSerNum = line.serNum_RL.getValue();
        String mdseCd = line.mdseCd_RL.getValue();
        // QC#22658 2017/11/20 Add Start
        MDSETMsg mdseTMsg = getMdse(glblCmpyCd, mdseCd);
        if (mdseTMsg == null || !ZYPConstant.FLG_ON_Y.equals(mdseTMsg.shpgSerTakeFlg.getValue())) {
            return;
        }
        // QC#22658 2017/11/20 Add End
        if (ZYPCommonFunc.hasValue(newSerNum)) {
            S21SsmEZDResult ssmResult = NWAL2200Query.getInstance().getSvcMachMstrPk(glblCmpyCd, mdseCd, newSerNum);

            if (ssmResult.isCodeNotFound()) {
                line.svcMachMstrPk_RL.clear();
            } else {
                BigDecimal svcMachMstrPk = (BigDecimal) ssmResult.getResultObject();
                ZYPEZDItemValueSetter.setValue(line.svcMachMstrPk_RL, svcMachMstrPk);
            }
        } else {
            line.svcMachMstrPk_RL.clear();
        }
    }
    // Add End 2017/06/06 QC#18583
    // QC#22658 2017/11/20 Add Start
    private static MDSETMsg getMdse(String glblCmpyCd, String mdseCd) {
        if (S21StringUtil.isEmpty(mdseCd)) {
            return null;
        }
        MDSETMsg mdse = NWXMdseTMsgThreadLocalCache.getInstance().get(glblCmpyCd, mdseCd);
        if (mdse == null) {
            return null;
        }
        return mdse;
    }
    // QC#22658 2017/11/20 Add End

    // 2018/01/23 QC#18798 Add Start
    public static  List<Map<String, Object>> getSalesRepList(String glblCmpyCd, String slsRepTocCd) {
        if (!ZYPCommonFunc.hasValue(slsRepTocCd)) {
            return null;
        }

        S21SsmEZDResult ssmResult = NWAL2200Query.getInstance().getSalesRepList(glblCmpyCd, slsRepTocCd);
        return (List<Map<String, Object>>) ssmResult.getResultObject();
    }

    public static void clearGLSegment(NWAL2200CMsg bizMsg) {
        bizMsg.psnNum_GS.clear();
        bizMsg.tocNm_GS.clear();
        bizMsg.coaExtnCd_GS.clear();
        bizMsg.coaExtnDescTxt_GS.clear();
        bizMsg.coaBrCd_GS.clear();
        bizMsg.coaBrDescTxt_GS.clear();
        bizMsg.coaCcCd_GS.clear();
        bizMsg.coaCcDescTxt_GS.clear();
    }

    public static void setGLSegment(NWAL2200CMsg bizMsg, Map<String, Object> dummyRepList) {
        ZYPEZDItemValueSetter.setValue(bizMsg.psnNum_GS, (String) dummyRepList.get("PSN_NUM"));
        ZYPEZDItemValueSetter.setValue(bizMsg.tocNm_GS, (String) dummyRepList.get("TOC_NM"));
        ZYPEZDItemValueSetter.setValue(bizMsg.coaExtnCd_GS, (String) dummyRepList.get("COA_EXTN_CD"));
        ZYPEZDItemValueSetter.setValue(bizMsg.coaExtnDescTxt_GS, (String) dummyRepList.get("COA_EXTN_DESC_TXT"));
        ZYPEZDItemValueSetter.setValue(bizMsg.coaBrCd_GS, (String) dummyRepList.get("COA_BR_CD"));
        ZYPEZDItemValueSetter.setValue(bizMsg.coaBrDescTxt_GS, (String) dummyRepList.get("COA_BR_DESC_TXT"));
        ZYPEZDItemValueSetter.setValue(bizMsg.coaCcCd_GS, (String) dummyRepList.get("COA_CC_CD"));
        ZYPEZDItemValueSetter.setValue(bizMsg.coaCcDescTxt_GS, (String) dummyRepList.get("COA_CC_DESC_TXT"));
    }
    // 2018/01/23 QC#18798 Add End
    // QC#19808 2018/01/25 Add Start
    public static void loadOnePageToCMsgForOutbound(NWAL2200SMsg glblMsg, NWAL2200CMsg bizMsg, int pageIndex) {
        int maxDisplayRows = bizMsg.B.length();
        List<String> poolConfig = new ArrayList<String>();
        int startIndex = (pageIndex / maxDisplayRows) * maxDisplayRows;

        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(bizMsg.B);

        bizMsg.xxPageShowToNum_LC.clear();
        bizMsg.xxPageShowOfNum_LC.clear();

        int i = startIndex;
        for (; i < startIndex + maxDisplayRows; i++) {

            if (i < glblMsg.B.getValidCount()) {
                int indexOfCMsg = i - startIndex;
                if (!poolConfig.contains(glblMsg.B.no(i).dsOrdPosnNum_LL.getValue())) {
                    poolConfig.add(glblMsg.B.no(i).dsOrdPosnNum_LL.getValue());
                }
                EZDMsg.copy(glblMsg.B.get(i), null, bizMsg.B.get(indexOfCMsg), null);

            } else {

                break;
            }
        }
        bizMsg.B.setValidCount(i - startIndex);

        int index = 0;
        for (int j = 0; j < glblMsg.A.getValidCount(); j++) {
            if (poolConfig.contains(glblMsg.A.no(j).dsOrdPosnNum_LC.getValue())) {
                EZDMsg.copy(glblMsg.A.no(j), null, bizMsg.A.no(index), null);
                index++;
            }
        }
        bizMsg.A.setValidCount(index);
        bizMsg.xxPageShowFromNum_LC.setValue(startIndex + 1);
        bizMsg.xxPageShowToNum_LC.setValue(startIndex + bizMsg.B.getValidCount());
        bizMsg.xxPageShowOfNum_LC.setValue(glblMsg.B.getValidCount());

        if (poolConfig.size() > 0) {
            bizMsg.dsOrdPosnNum_TL.setValue(String.valueOf(glblMsg.A.getValidCount()));
        }
    }

    public static void loadOnePageToCMsgForInbound(NWAL2200SMsg glblMsg, NWAL2200CMsg bizMsg, int pageIndex) {
        int maxDisplayRows = bizMsg.D.length();
        List<String> poolConfig = new ArrayList<String>();
        int startIndex = (pageIndex / maxDisplayRows) * maxDisplayRows;

        ZYPTableUtil.clear(bizMsg.C);
        ZYPTableUtil.clear(bizMsg.D);

        bizMsg.xxPageShowToNum_RC.clear();
        bizMsg.xxPageShowOfNum_RC.clear();

        int i = startIndex;
        for (; i < startIndex + maxDisplayRows; i++) {

            if (i < glblMsg.D.getValidCount()) {
                int indexOfCMsg = i - startIndex;
                if (!poolConfig.contains(glblMsg.D.no(i).dsOrdPosnNum_RL.getValue())) {
                    poolConfig.add(glblMsg.D.no(i).dsOrdPosnNum_RL.getValue());
                }
                EZDMsg.copy(glblMsg.D.get(i), null, bizMsg.D.get(indexOfCMsg), null);

            } else {

                break;
            }
        }
        bizMsg.D.setValidCount(i - startIndex);

        int index = 0;
        for (int j = 0; j < glblMsg.C.getValidCount(); j++) {
            if (poolConfig.contains(glblMsg.C.no(j).dsOrdPosnNum_RC.getValue())) {
                EZDMsg.copy(glblMsg.C.no(j), null, bizMsg.C.no(index), null);
                index++;
            }
        }
        bizMsg.C.setValidCount(index);
        bizMsg.xxPageShowFromNum_RC.setValue(startIndex + 1);
        bizMsg.xxPageShowToNum_RC.setValue(startIndex + bizMsg.D.getValidCount());
        bizMsg.xxPageShowOfNum_RC.setValue(glblMsg.D.getValidCount());
        if (poolConfig.size() > 0) {
            bizMsg.dsOrdPosnNum_TR.setValue(String.valueOf(glblMsg.C.getValidCount()));
        }
    }

    /**
     * loadOnePageToCMsg
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     * @param cMsgArray EZDCMsgArray
     * @param sMsgArray EZDSMsgArray
     * @param pageIndex int
     */
    public static void loadOnePageToCMsgForError(NWAL2200SMsg glblMsg, NWAL2200CMsg bizMsg, int pageIndex) {

        ZYPTableUtil.clear(bizMsg.E);

        bizMsg.xxPageShowToNum_EL.clear();
        bizMsg.xxPageShowOfNum_EL.clear();

        int maxDisplayRows = bizMsg.E.length();
        int startIndex = ((pageIndex - 1) / maxDisplayRows) * maxDisplayRows;

        int i = startIndex;
        for (; i < startIndex + maxDisplayRows; i++) {

            if (i < glblMsg.E.getValidCount()) {

                int indexOfCMsg = i - startIndex;
                EZDMsg.copy(glblMsg.E.get(i), null, bizMsg.E.get(indexOfCMsg), null);

            } else {

                break;
            }
        }

        bizMsg.E.setValidCount(i - startIndex);
        bizMsg.xxPageShowFromNum_EL.setValue(startIndex + 1);
        bizMsg.xxPageShowToNum_EL.setValue(startIndex + bizMsg.E.getValidCount());
        bizMsg.xxPageShowOfNum_EL.setValue(glblMsg.E.getValidCount());
    }
    /**
     * storeOnePageToSMsglblMsgForOutbound
     * @param glblMsg NWAL2200SMsg
     * @param bizMsg NWAL2200CMsg
     */
    public static void storeOnePageToSMsglblMsgForOutbound(NWAL2200SMsg glblMsg, NWAL2200CMsg bizMsg) {
        int index = 0;
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            index = getConfigIndex(glblMsg, bizMsg.A.no(i));
            if (index > -1) {
                EZDMsg.copy(bizMsg.A.no(i), null, glblMsg.A.no(index), null);
                glblMsg.A.no(index).xxChkBox_LC.clear();
            }
        }
        index = 0;
        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
            index = getLineIndex(glblMsg, bizMsg.B.no(i), index);
            if (index > -1) {
                EZDMsg.copy(bizMsg.B.no(i), null, glblMsg.B.no(index), null);
                glblMsg.B.no(index).xxChkBox_LL.clear();
            }
        }
    }

    /**
     * storeOnePageToSMsglblMsgForInbound
     * @param glblMsg NWAL2200SMsg
     * @param bizMsg NWAL2200CMsg
     */
    public static void storeOnePageToSMsglblMsgForInbound(NWAL2200SMsg glblMsg, NWAL2200CMsg bizMsg) {
        int index = 0;
        for (int i = 0; i < bizMsg.C.getValidCount(); i++) {
            index = getConfigIndex(glblMsg, bizMsg.C.no(i));
            if (index > -1) {
                EZDMsg.copy(bizMsg.C.no(i), null, glblMsg.C.no(index), null);
                glblMsg.C.no(index).xxChkBox_RC.clear();
            }
        }
        index = 0;
        for (int i = 0; i < bizMsg.D.getValidCount(); i++) {
            index = getLineIndex(glblMsg, bizMsg.D.no(i), index);
            if (index > -1) {
                EZDMsg.copy(bizMsg.D.no(i), null, glblMsg.D.no(index), null);
                glblMsg.D.no(index).xxChkBox_RL.clear();
            }
        }
    }

    /**
     * getConfigIndex
     * @param glblMsg NWAL2200SMsg
     * @param line NWAL2200_ACMsg
     * @return int 
     */
    public static int getConfigIndex(NWAL2200SMsg glblMsg, NWAL2200_ACMsg line) {
        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            NWAL2200_ASMsg gLine = glblMsg.A.no(i);
            if (S21StringUtil.isEquals(line.dsOrdPosnNum_LC.getValue(), gLine.dsOrdPosnNum_LC.getValue())) {
                return i;
            }
        }
        return -1;
    }

    /**
     * getConfigIndex
     * @param glblMsg NWAL2200SMsg
     * @param line NWAL2200_CCMsg
     * @return int
     */
    public static int getConfigIndex(NWAL2200SMsg glblMsg, NWAL2200_CCMsg line) {
        for (int i = 0; i < glblMsg.C.getValidCount(); i++) {
            NWAL2200_CSMsg gLine = glblMsg.C.no(i);
            if (S21StringUtil.isEquals(line.dsOrdPosnNum_RC.getValue(), gLine.dsOrdPosnNum_RC.getValue())) {
                return i;
            }
        }
        return -1;
    }

    /**
     * getLineIndex
     * @param glblMsg NWAL2200SMsg
     * @param line NWAL2200_BCMsg
     * @return int
     */
    public static int getLineIndex(NWAL2200SMsg glblMsg, NWAL2200_BCMsg line, int startIndex) {
        for (int i = startIndex; i < glblMsg.B.getValidCount(); i++) {
            NWAL2200_BSMsg gLine = glblMsg.B.no(i);
            if (S21StringUtil.isEquals(line.xxLineNum_LL.getValue(), gLine.xxLineNum_LL.getValue())) {
                return i;
            }
        }
        for (int i = 0; i < glblMsg.B.getValidCount(); i++) {
            NWAL2200_BSMsg gLine = glblMsg.B.no(i);
            if (S21StringUtil.isEquals(line.xxLineNum_LL.getValue(), gLine.xxLineNum_LL.getValue())) {
                return i;
            }
        }
        return -1;
    }

    /**
     * getLineIndex
     * @param glblMsg NWAL2200SMsg
     * @param line NWAL2200_DCMsg
     * @return int
     */
    public static int getLineIndex(NWAL2200SMsg glblMsg, NWAL2200_DCMsg line, int startIndex) {
        for (int i = startIndex; i < glblMsg.D.getValidCount(); i++) {
            NWAL2200_DSMsg gLine = glblMsg.D.no(i);
            if (S21StringUtil.isEquals(line.xxLineNum_RL.getValue(), gLine.xxLineNum_RL.getValue())) {
                return i;
            }
        }
        for (int i = 0; i < glblMsg.D.getValidCount(); i++) {
            NWAL2200_DSMsg gLine = glblMsg.D.no(i);
            if (S21StringUtil.isEquals(line.xxLineNum_RL.getValue(), gLine.xxLineNum_RL.getValue())) {
                return i;
            }
        }
        return -1;
    }
    // QC#19808 2018/01/25 Add End

    // Add Start 2018/07/25 S21_NA#14307
    /**
     * Common Process For Special Instruction
     * @param bizMsg NWAL2200CMsg 
     */
    public static void cmnProcForSpecialInstruction(NWAL2200CMsg bizMsg) {
        cmnProcForSpecialInstruction(bizMsg, null, null, null);
    }

    /**
     * Common Process For Special Instruction
     * @param bizMsg NWAL2200CMsg
     */
    public static void cmnProcForSpecialInstruction(NWAL2200CMsg bizMsg, String sellToCustCd_BK, String billToCustCd_BK, String shipToCustCd_BK) {
        boolean isDisplay = false;
        String glblCmpyCd = bizMsg.glblCmpyCd.getValue();
        String trxRuleTp = NWAL2200CommonLogic.getTrxRuleTp(bizMsg.glblCmpyCd.getValue(), bizMsg.dsOrdCatgCd.getValue(), bizMsg.dsOrdTpCd.getValue());
        String funcCatgId = NWXC150001DsCheck.getOrdCatgBizCtxFstAttbTxt(bizMsg.glblCmpyCd.getValue(), ORD_CATG_CTX_TP.CUST_SPCL_ORDER_CATEGORY, bizMsg.dsOrdCatgCd.getValue(), bizMsg.dsOrdTpCd.getValue());

        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxRqstFlg)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.P.no(2).xxPopPrm, funcCatgId);
            ZYPEZDItemValueSetter.setValue(bizMsg.P.no(3).xxPopPrm, trxRuleTp);
            return;
        }

        if (ZYPCommonFunc.hasValue(bizMsg.sellToCustCd) && 
            !S21StringUtil.isEquals(bizMsg.sellToCustCd.getValue(), sellToCustCd_BK)) {
            if (NWXC150001DsCheck.spclInstnDisplayDetermination(
                    glblCmpyCd, trxRuleTp, null, bizMsg.sellToCustCd.getValue(), null, null, BIZ_ID, funcCatgId, bizMsg.lineBizTpCd.getValue())){
                isDisplay = true;
            }
        }

        if (ZYPCommonFunc.hasValue(bizMsg.billToCustCd) && 
            !S21StringUtil.isEquals(bizMsg.billToCustCd.getValue(), billToCustCd_BK)) {
            if (NWXC150001DsCheck.spclInstnDisplayDetermination(
                    glblCmpyCd, trxRuleTp, null, null, bizMsg.billToCustCd.getValue(), null, BIZ_ID, funcCatgId, bizMsg.lineBizTpCd.getValue())){
                isDisplay = true;
            }
        }

        if (ZYPCommonFunc.hasValue(bizMsg.shipToCustCd) && 
            !S21StringUtil.isEquals(bizMsg.shipToCustCd.getValue(), shipToCustCd_BK)) {
            if (NWXC150001DsCheck.spclInstnDisplayDetermination(
                    glblCmpyCd, trxRuleTp, null, null, null, bizMsg.shipToCustCd.getValue(), BIZ_ID, funcCatgId, bizMsg.lineBizTpCd.getValue())){
                isDisplay = true;
            }
        }

        if (isDisplay) {
            ZYPEZDItemValueSetter.setValue(bizMsg.P.no(2).xxPopPrm, funcCatgId);
            ZYPEZDItemValueSetter.setValue(bizMsg.P.no(3).xxPopPrm, trxRuleTp);
            bizMsg.xxRqstFlg.setValue(ZYPConstant.FLG_ON_1);
        }
    }

    /**
     * Common Process For Special Instruction For Line
     * @param bizMsg
     * @param lineMsg
     * @param billToCustCd_BK
     * @param shipToCustCd_BK
     */
    public static void cmnProcForSpecialInstructionForLine(NWAL2200CMsg bizMsg, NWAL2200_ACMsg lineMsg, String billToCustCd_BK, String shipToCustCd_BK) {
        cmnProcForSpecialInstructionForLine(bizMsg, lineMsg.billToCustLocCd_LC.getValue(), lineMsg.shipToCustLocCd_LC.getValue(), billToCustCd_BK, shipToCustCd_BK);
    }

    /**
     * Common Process For Special Instruction For Line
     * @param bizMsg
     * @param lineMsg
     * @param billToCustCd_BK
     * @param shipToCustCd_BK
     */
    public static void cmnProcForSpecialInstructionForLine(NWAL2200CMsg bizMsg, NWAL2200_CCMsg lineMsg, String billToCustCd_BK, String shipToCustCd_BK) {
        cmnProcForSpecialInstructionForLine(bizMsg, lineMsg.billToCustLocCd_RC.getValue(), lineMsg.shipToCustLocCd_RC.getValue(), billToCustCd_BK, shipToCustCd_BK);
    }

    /**
     * Common Process For Special Instruction For Line
     * @param bizMsg NWAL2200CMsg
     */
    private static void cmnProcForSpecialInstructionForLine(NWAL2200CMsg bizMsg, String lineBillToCustLocCd, String lineShipToCustLocCd, String billToCustCd_BK, String shipToCustCd_BK) {
        boolean isDisplay = false;
        String glblCmpyCd = bizMsg.glblCmpyCd.getValue();
        String trxRuleTp = NWAL2200CommonLogic.getTrxRuleTp(bizMsg.glblCmpyCd.getValue(), bizMsg.dsOrdCatgCd.getValue(), bizMsg.dsOrdTpCd.getValue());
        String funcCatgId = NWXC150001DsCheck.getOrdCatgBizCtxFstAttbTxt(bizMsg.glblCmpyCd.getValue(), ORD_CATG_CTX_TP.CUST_SPCL_ORDER_CATEGORY, bizMsg.dsOrdCatgCd.getValue(), bizMsg.dsOrdTpCd.getValue());

        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxRqstFlg)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.P.no(2).xxPopPrm, funcCatgId);
            ZYPEZDItemValueSetter.setValue(bizMsg.P.no(3).xxPopPrm, trxRuleTp);
            return;
        }

        if (ZYPCommonFunc.hasValue(lineBillToCustLocCd) && 
            !S21StringUtil.isEquals(lineBillToCustLocCd, billToCustCd_BK)) {
            if (NWXC150001DsCheck.spclInstnDisplayDetermination(
                    glblCmpyCd, trxRuleTp, null, null, lineBillToCustLocCd, null, BIZ_ID, funcCatgId, bizMsg.lineBizTpCd.getValue())){
                isDisplay = true;
            }
        }

        if (ZYPCommonFunc.hasValue(lineShipToCustLocCd) && 
            !S21StringUtil.isEquals(lineShipToCustLocCd, shipToCustCd_BK)) {
            if (NWXC150001DsCheck.spclInstnDisplayDetermination(
                    glblCmpyCd, trxRuleTp, null, null, null, lineShipToCustLocCd, BIZ_ID, funcCatgId, bizMsg.lineBizTpCd.getValue())){
                isDisplay = true;
            }
        }

        if (isDisplay) {
            ZYPEZDItemValueSetter.setValue(bizMsg.P.no(2).xxPopPrm, funcCatgId);
            ZYPEZDItemValueSetter.setValue(bizMsg.P.no(3).xxPopPrm, trxRuleTp);
            bizMsg.xxRqstFlg.setValue(ZYPConstant.FLG_ON_1);
        }
    }
    // Add End 2018/07/25 S21_NA#14307

    // Add Start 2018/07/25 S21_NA#14307
    public static String getTrxRuleTp(String glblCmpyCd, String dsOrdCatgCd, String dsOrdTpCd) {

        ORD_CATG_BIZ_CTXTMsg tMsg = getOrdCatgBizCtx(glblCmpyCd, dsOrdCatgCd, dsOrdTpCd);
        if (tMsg == null) {
            return null;
        }
        return tMsg.firstBizCtxAttrbTxt.getValue();
    }
    // Add End 2018/07/25 S21_NA#14307

    // Add Start 2018/07/25 S21_NA#14307
    private static ORD_CATG_BIZ_CTXTMsg getOrdCatgBizCtx(String glblCmpyCd, String dsOrdCatgCd, String dsOrdTpCd) {

        ORD_CATG_BIZ_CTXTMsg inTMsg = new ORD_CATG_BIZ_CTXTMsg();
        inTMsg.setSQLID("005");
        inTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inTMsg.setConditionValue("ordCatgCtxTpCd01A", NWAL2200Constant.EQUIPMENT_ORDER);
        inTMsg.setConditionValue("ordCatgCtxTpCd01B", NWAL2200Constant.SUPPLIES_ORDER);
        inTMsg.setConditionValue("dsOrdCatgCd01", dsOrdCatgCd);
        inTMsg.setConditionValue("dsOrdTpCd01", dsOrdTpCd);
        ORD_CATG_BIZ_CTXTMsgArray array = (ORD_CATG_BIZ_CTXTMsgArray) EZDTBLAccessor.findByCondition(inTMsg);
        if (array == null || array.length() == 0) {
            return null;
        }
        return array.no(0);
    }
    // Add End 2018/07/25 S21_NA#14307
    
    // 2018/08/31 S21_NA#26022 Add Start
    public static void copyLineDataToSMsg(NWAL2200_BCMsg lineMsg, NWAL2200SMsg glblMsg) {

        int idx = getDetectedDetailIndexOfGlobalMessage(lineMsg, glblMsg);
        if (idx < 0) {
            return;
        }
        String dsOrdPosnNum = glblMsg.B.no(idx).dsOrdPosnNum_LL.getValue();
        if (!ZYPCommonFunc.hasValue(dsOrdPosnNum)) {
            dsOrdPosnNum = glblMsg.B.no(idx).xxLineNum_LL.getValue();
            int findIdx = dsOrdPosnNum.indexOf(".");
            dsOrdPosnNum = dsOrdPosnNum.substring(0, findIdx);
        }
        NWAL2200_ASMsg configMsg = getParentConfigFromGlobal(glblMsg.A, dsOrdPosnNum);
        if (S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, configMsg.xxSmryLineFlg_L.getValue())) {
            return;
        }
        if (idx >= 0) {
            EZDMsg.copy(lineMsg, null, glblMsg.B.no(idx), null);
        }
    }

    public static void copyLineDataToSMsg(NWAL2200_DCMsg rmaLineMsg, NWAL2200SMsg glblMsg) {

        int idx = getDetectedDetailIndexOfGlobalMessage(rmaLineMsg, glblMsg);
        if (idx < 0) {
            return;
        }
        String dsOrdPosnNum = glblMsg.D.no(idx).dsOrdPosnNum_RL.getValue();
        if (!ZYPCommonFunc.hasValue(dsOrdPosnNum)) {
            dsOrdPosnNum = glblMsg.D.no(idx).xxLineNum_RL.getValue();
            int findIdx = dsOrdPosnNum.indexOf(".");
            dsOrdPosnNum = dsOrdPosnNum.substring(0, findIdx);
        }
        NWAL2200_CSMsg rmaConfigMsg = getParentConfigFromGlobal(glblMsg.C, dsOrdPosnNum);
        if (S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, rmaConfigMsg.xxSmryLineFlg_R.getValue())) {
            return;
        }
        if (idx >= 0) {
            EZDMsg.copy(rmaLineMsg, null, glblMsg.D.no(idx), null);
        }
    }

    public static NWAL2200_ASMsg getParentConfigFromGlobal(NWAL2200_ASMsgArray configList, String dsOrdPosnNum) {

        for (int i = 0; i < configList.getValidCount(); i++) {
            if (S21StringUtil.isEquals(dsOrdPosnNum, configList.no(i).dsOrdPosnNum_LC.getValue())) {
                return configList.no(i);
            }
        }
        return null;
    }

    public static NWAL2200_CSMsg getParentConfigFromGlobal(NWAL2200_CSMsgArray configList, String dsOrdPosnNum) {

        for (int i = 0; i < configList.getValidCount(); i++) {
            if (S21StringUtil.isEquals(dsOrdPosnNum, configList.no(i).dsOrdPosnNum_RC.getValue())) {
                return configList.no(i);
            }
        }
        return null;
    }

    public static int getDetectedDetailIndexOfGlobalMessage(EZDMsg dtlMsg, NWAL2200SMsg glblMsg) {

        if (dtlMsg instanceof NWAL2200_ACMsg) {
            for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
                NWAL2200_ASMsg glblConfigMsg = glblMsg.A.no(i);
                if (S21StringUtil.isEquals(((NWAL2200_ACMsg) dtlMsg).dsOrdPosnNum_LC.getValue(), glblConfigMsg.dsOrdPosnNum_LC.getValue())) {
                    return i;
                }
            }
        } else if (dtlMsg instanceof NWAL2200_BCMsg) {
            for (int i = 0; i < glblMsg.B.getValidCount(); i++) {
                NWAL2200_BSMsg glblLineMsg = glblMsg.B.no(i);
                if (S21StringUtil.isEquals(((NWAL2200_BCMsg) dtlMsg).xxLineNum_LL.getValue(), glblLineMsg.xxLineNum_LL.getValue())) {
                    return i;
                }
            }
        } else if (dtlMsg instanceof NWAL2200_CCMsg) {
            for (int i = 0; i < glblMsg.C.getValidCount(); i++) {
                NWAL2200_CSMsg glblRmaConfigMsg = glblMsg.C.no(i);
                if (S21StringUtil.isEquals(((NWAL2200_CCMsg) dtlMsg).dsOrdPosnNum_RC.getValue(), glblRmaConfigMsg.dsOrdPosnNum_RC.getValue())) {
                    return i;
                }
            }
        } else if (dtlMsg instanceof NWAL2200_DCMsg) {
            for (int i = 0; i < glblMsg.D.getValidCount(); i++) {
                NWAL2200_DSMsg glblRmaLineMsg = glblMsg.D.no(i);
                if (S21StringUtil.isEquals(((NWAL2200_DCMsg) dtlMsg).xxLineNum_RL.getValue(), glblRmaLineMsg.xxLineNum_RL.getValue())) {
                    return i;
                }
            }
        } else if (dtlMsg instanceof NWAL2200_ASMsg) {
            for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
                NWAL2200_ASMsg glblLineMsg = glblMsg.A.no(i);
                if (S21StringUtil.isEquals(((NWAL2200_ASMsg) dtlMsg).dsOrdPosnNum_LC.getValue(), glblLineMsg.dsOrdPosnNum_LC.getValue())) {
                    return i;
                }
            }
        } else if (dtlMsg instanceof NWAL2200_BSMsg) {
            for (int i = 0; i < glblMsg.B.getValidCount(); i++) {
                NWAL2200_BSMsg glblLineMsg = glblMsg.B.no(i);
                if (S21StringUtil.isEquals(((NWAL2200_BSMsg) dtlMsg).xxLineNum_LL.getValue(), glblLineMsg.xxLineNum_LL.getValue())) {
                    return i;
                }
            }
        } else if (dtlMsg instanceof NWAL2200_CSMsg) {
            for (int i = 0; i < glblMsg.C.getValidCount(); i++) {
                NWAL2200_CSMsg glblLineMsg = glblMsg.C.no(i);
                if (S21StringUtil.isEquals(((NWAL2200_CSMsg) dtlMsg).dsOrdPosnNum_RC.getValue(), glblLineMsg.dsOrdPosnNum_RC.getValue())) {
                    return i;
                }
            }
        } else if (dtlMsg instanceof NWAL2200_DSMsg) {
            for (int i = 0; i < glblMsg.D.getValidCount(); i++) {
                NWAL2200_DSMsg glblRmaLineMsg = glblMsg.D.no(i);
                if (S21StringUtil.isEquals(((NWAL2200_DSMsg) dtlMsg).xxLineNum_RL.getValue(), glblRmaLineMsg.xxLineNum_RL.getValue())) {
                    return i;
                }
            }
        }
        return -1;
    }
    // 2018/08/31 S21_NA#26022 Add End

    // Add Start 2018/11/19 QC#28683
    /**
     * setParamForSpecialInstruction
     * @param bizMsg NWAL2200CMsg
     */
    public static void setParamForSpecialInstruction(NWAL2200CMsg bizMsg) {
        String glblCmpyCd = bizMsg.glblCmpyCd.getValue();
        String dsOrdCatgCd = bizMsg.dsOrdCatgCd.getValue();
        String dsOrdTpCd = bizMsg.dsOrdTpCd.getValue();

        String trxRuleTp = NWAL2200CommonLogic.getTrxRuleTp(glblCmpyCd, dsOrdCatgCd, dsOrdTpCd);
        String funcCatgId = NWXC150001DsCheck.getOrdCatgBizCtxFstAttbTxt(glblCmpyCd, ORD_CATG_CTX_TP.CUST_SPCL_ORDER_CATEGORY, dsOrdCatgCd, dsOrdTpCd);

        ZYPEZDItemValueSetter.setValue(bizMsg.P.no(2).xxPopPrm, funcCatgId);
        ZYPEZDItemValueSetter.setValue(bizMsg.P.no(3).xxPopPrm, trxRuleTp);
    }
    // Add End 2018/11/19 QC#28683

    // 2018/12/13 S21_NA#29315 Add Start
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
    // 2018/12/13 S21_NA#29315 Add End

    // 2019/02/08 QC#30237 Add Start
    /**
     * <pre>
     * Copy Line Config Config data to Global Area.
     * @param configMsg Biz. Config Message
     * @param glblMsg Global Message
     * </pre>
     */
    public static void copyConfigDataToSMsg(NWAL2200_ACMsg configMsg, NWAL2200SMsg glblMsg) {

        int idx = getDetectedDetailIndexOfGlobalMessage(configMsg, glblMsg);
        if (idx >= 0) {
            EZDMsg.copy(configMsg, null, glblMsg.A.no(idx), null);
        }
    }

    /**
     * <pre>
     * Copy RMA Config data to Global Area.
     * @param rmaConfigMsg Biz. RMA Mesasge
     * @param glblMsg Global Message
     * </pre>
     */
    public static void copyRmaConfigDataToSMsg(NWAL2200_CCMsg rmaConfigMsg, NWAL2200SMsg glblMsg) {

        int idx = getDetectedDetailIndexOfGlobalMessage(rmaConfigMsg, glblMsg);
        if (idx >= 0) {
            EZDMsg.copy(rmaConfigMsg, null, glblMsg.C.no(idx), null);
        }
        return;
    }
    // 2019/02/08 QC#30237 Add End

    /**
     * 2020/04/27 QC#56638 Add
     * Get Salse Req Defaulting
     * @param bizMsg NWAL2200CMsg
     * @return Boolean
     */
    public static boolean isSlsReqDef(NWAL2200CMsg bizMsg) {

        boolean isShipBase = true;
        if (ZYPCommonFunc.hasValue(bizMsg.dsOrdTpCd)) {
            DS_ORD_TP_PROC_DFNTMsg tMsg = new DS_ORD_TP_PROC_DFNTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(tMsg.dsOrdTpCd, bizMsg.dsOrdTpCd.getValue());
            tMsg = (DS_ORD_TP_PROC_DFNTMsg) S21FastTBLAccessor.findByKey(tMsg);

            if (tMsg != null && ZYPCommonFunc.hasValue(tMsg.baseLocTxt)) {
                if ("Ship To Location".equals(tMsg.baseLocTxt.getValue())) {
                    isShipBase = true;
                } else if ("Sold To Location".equals(tMsg.baseLocTxt.getValue())) {
                    isShipBase = false;
                } else {
                    isShipBase = true;
                }
            }
        }

        return isShipBase;
    }

    // 2023/11/06 QC#61924 Add Start
    /**
     * hasDeactivateAccountOrLocation
     * @param bizMsg NWAL2200CMsg
     * @param glblMsg NWAL2200SMsg
     * @return boolean
     */
    public static boolean hasDeactivateAccountOrLocation(NWAL2200CMsg bizMsg, NWAL2200SMsg glblMsg) {

        if (ZYPCommonFunc.hasValue(bizMsg.cpoOrdNum)) {
            return true;
        }

        boolean errorFlg = false;

        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            BILL_TO_CUSTTMsg lcBillToCustTMsg = new BILL_TO_CUSTTMsg();
            lcBillToCustTMsg = getBillToCustByCondition(bizMsg.glblCmpyCd.getValue(), bizMsg.A.no(i).billToCustLocCd_LC.getValue());
            if (null != lcBillToCustTMsg) {
                if (ZYPConstant.FLG_ON_Y.equals(lcBillToCustTMsg.deacNewTrxFlg.getValue())) {
                    bizMsg.A.no(i).billToCustLocCd_LC.setErrorInfo(1, NWAM0987E, new String[] {bizMsg.A.no(i).billToCustLocCd_LC.getValue() });
                    errorFlg = true;
                }
            }

            SHIP_TO_CUSTTMsg lcShipToCustTMsg = new SHIP_TO_CUSTTMsg();
            lcShipToCustTMsg = getShipToCustByCondition(bizMsg.glblCmpyCd.getValue(), bizMsg.A.no(i).shipToCustLocCd_LC.getValue());
            if (null != lcShipToCustTMsg) {
                if (ZYPConstant.FLG_ON_Y.equals(lcShipToCustTMsg.deacNewTrxFlg.getValue())) {
                    bizMsg.A.no(i).shipToCustLocCd_LC.setErrorInfo(1, NWAM0988E, new String[] {bizMsg.A.no(i).shipToCustLocCd_LC.getValue() });
                    errorFlg = true;
                }
            }
        }

        BILL_TO_CUSTTMsg billToCustTMsg = new BILL_TO_CUSTTMsg();
        billToCustTMsg = getBillToCustByCondition(bizMsg.glblCmpyCd.getValue(), bizMsg.billToCustCd.getValue());
        if (null != billToCustTMsg) {
            if (ZYPConstant.FLG_ON_Y.equals(billToCustTMsg.deacNewTrxFlg.getValue())) {
                bizMsg.billToCustCd.setErrorInfo(1, NWAM0987E, new String[] {bizMsg.billToCustCd.getValue() });
                errorFlg = true;
            }
        }

        SHIP_TO_CUSTTMsg shipToCustTMsg = new SHIP_TO_CUSTTMsg();
        shipToCustTMsg = getShipToCustByCondition(bizMsg.glblCmpyCd.getValue(), bizMsg.shipToCustCd.getValue());
        if (null != shipToCustTMsg) {
            if (ZYPConstant.FLG_ON_Y.equals(shipToCustTMsg.deacNewTrxFlg.getValue())) {
                bizMsg.shipToCustCd.setErrorInfo(1, NWAM0988E, new String[] {bizMsg.shipToCustCd.getValue() });
                errorFlg = true;
            }
        }

        BILL_TO_CUSTTMsg soldToCustTMsg = new BILL_TO_CUSTTMsg();
        soldToCustTMsg = getBillToCustByCondition(bizMsg.glblCmpyCd.getValue(), bizMsg.soldToCustLocCd.getValue());
        if (null != soldToCustTMsg) {
            if (ZYPConstant.FLG_ON_Y.equals(soldToCustTMsg.deacNewTrxFlg.getValue())) {
                bizMsg.soldToCustLocCd.setErrorInfo(1, NWAM0989E, new String[] {bizMsg.soldToCustLocCd.getValue() });
                errorFlg = true;
            }
        }

        if (errorFlg) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * <pre>
     * get Bill To Customer
     * @param glblCmpyCd Global Company Code
     * @param billToCustCd Bill To Customer Code
     * @return BILL_TO_CUSTTMsg, if system can't find BILL_TO_CUST record, return null
     * </pre>
     */
    private static BILL_TO_CUSTTMsg getBillToCustByCondition(String glblCmpyCd, String billToCustCd) {

        BILL_TO_CUSTTMsg condition = new BILL_TO_CUSTTMsg();
        condition.setSQLID("002");
        condition.setConditionValue("glblCmpyCd01", glblCmpyCd);
        condition.setConditionValue("billToCustCd01", billToCustCd);
        condition.setConditionValue("rgtnStsCd01", RGTN_STS.READY_FOR_ORDER_TAKING);

        BILL_TO_CUSTTMsgArray tmsgArray = (BILL_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(condition);

        if (tmsgArray.getValidCount() > 0) {
            return tmsgArray.no(0);
        } else {
            return null;
        }
    }
    // 2023/11/06 QC#61924 Add End
}
