/**
 * <pre> * Copyright(c)2010 Canon USA Inc. All rights reserved. * </pre>
 */
package business.blap.NLCL1020.common;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import parts.common.EZDCStringItem;
import parts.common.EZDDebugOutput;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NLCL1020.NLCL1020CMsg;
import business.blap.NLCL1020.NLCL1020Query;
import business.blap.NLCL1020.NLCL1020_ACMsg;
import business.blap.NLCL1020.constant.NLCL1020Constant;
import business.db.ALL_MDSE_VTMsg;
import business.db.ALL_MDSE_VTMsgArray;
import business.db.AVAL_INVTY_APP_IDTMsg;
import business.db.INVTYTMsg;
import business.db.INVTY_ORDTMsg;
import business.db.MDSETMsg;
import business.db.RTL_SWHTMsg;
import business.db.SHPG_PLNTMsg;
import business.parts.NLZC060001PMsg;

import com.canon.cusa.s21.api.NLZ.NLZC060001.NLZC060001;
import com.canon.cusa.s21.api.NLZ.NLZC060001.constant.NLZC060001Constant;
import com.canon.cusa.s21.common.NMX.NMXC100001.NMXC100001EnableWH;
import com.canon.cusa.s21.common.NMX.NMXC100001.NMXC100001EnableWHData;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/07/2013   Fujitsu         Y.Taoka         Create          R-WH001
 * 03/11/2019   Fujitsu         T.Ogura         Update          QC#30705
 *</pre>
 */
public class NLCL1020CommonLogic {

    /**
     * @param bizMsg NLCL1020CMsg
     * @return ALL_MDSE_VTMsg
     */
    public static ALL_MDSE_VTMsg findMdseInfobyView(NLCL1020CMsg bizMsg) {
        ALL_MDSE_VTMsg cond = new ALL_MDSE_VTMsg();
        ALL_MDSE_VTMsgArray outAllMdseVTMsg = null;
        cond.setSQLID("003");
        cond.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        cond.setConditionValue("mdseCd01", bizMsg.mdseCd_HD.getValue());

        outAllMdseVTMsg = (ALL_MDSE_VTMsgArray) EZDTBLAccessor.findByCondition(cond);
        if (outAllMdseVTMsg == null || outAllMdseVTMsg.length() == 0) {
            return null;
        } else {
            return (ALL_MDSE_VTMsg) outAllMdseVTMsg.get(0);
        }
    }

    /**
     * @param bizMsg NLCL1020CMsg
     * @param inMsg MDSETMsg
     * @return MDSETMsg
     */
    public static MDSETMsg findMdseInfo(NLCL1020CMsg bizMsg, MDSETMsg inMsg) {
      inMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
      inMsg.mdseCd.setValue(bizMsg.mdseCd_HD.getValue());

      MDSETMsg outMsg = (MDSETMsg) S21ApiTBLAccessor.findByKey(inMsg);

      return outMsg;
    }

    /**
     * @param bizMsg NLCL1020CMsg
     * @param inMsg INVTY_ORDTMsg
     * @return INVTY_ORDTMsg
     */
    public static INVTY_ORDTMsg findInvtyOrdInfo(NLCL1020CMsg bizMsg, INVTY_ORDTMsg inMsg) {
        inMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
        inMsg.invtyOrdNum.setValue(bizMsg.invtyOrdNum.getValue());

        INVTY_ORDTMsg outMsg = (INVTY_ORDTMsg) S21ApiTBLAccessor.findByKey(inMsg);

        return outMsg;
    }

    /**
     * @param bizMsg NLCL1020CMsg
     * @param inMsg SHPG_PLNTMsg
     * @return int
     */
    public static int countShpgPln(NLCL1020CMsg bizMsg, SHPG_PLNTMsg inMsg) {
        inMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        inMsg.setConditionValue("trxHdrNum01", bizMsg.invtyOrdNum.getValue());

        inMsg.setSQLID("022");

        int count = S21ApiTBLAccessor.count(inMsg);

        return count;
    }

    // 10/20/2015 del start
//    /**
//     * @param bizMsg NLCL1020CMsg
//     * @param inMsg INVTY_ORD_DTLTMsg
//     * @return INVTY_ORD_DTLTMsgArray
//     */
//    public static INVTY_ORD_DTLTMsgArray findInvtyOrdDtlList(NLCL1020CMsg bizMsg, INVTY_ORD_DTLTMsg inMsg) {
//        inMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
//        inMsg.setConditionValue("invtyOrdNum01", bizMsg.invtyOrdNum.getValue());
//
//        inMsg.setMaxCount(1);
//        inMsg.setSQLID("001");
//
//        INVTY_ORD_DTLTMsgArray outMsg = (INVTY_ORD_DTLTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
//
//        if (outMsg.length() >= 1) {
//            for (int i = 0; i < outMsg.length(); i++) {
//                bizMsg.invtyLocCd_TO.setValue(outMsg.no(0).invtyLocCd.getValue());
//            }
//        }
//        return outMsg;
//    }
    // 10/20/2015 del end

    /**
     * @param bizMsg NLCL1020CMsg
     * @param stkStsCd String
     * @return AVAL_INVTY_APP_IDTMsg
     */
    public static AVAL_INVTY_APP_IDTMsg findAvalInvtyAppIdInfo(NLCL1020CMsg bizMsg, String stkStsCd) {

        AVAL_INVTY_APP_IDTMsg inMsg = new AVAL_INVTY_APP_IDTMsg();

        inMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
        inMsg.bizAppId.setValue(NLCL1020Constant.BUSINESS_ID);
        inMsg.locStsCd.setValue(bizMsg.locStsCd_P1.getValue());
        inMsg.stkStsCd.setValue(stkStsCd);

        return (AVAL_INVTY_APP_IDTMsg) S21ApiTBLAccessor.findByKey(inMsg);
    }

    /**
     * @param bizMsg NLCL1020CMsg
     * @param mdseCd String
     * @param stkStsCd String
     * @return INVTYTMsg
     */
    public static INVTYTMsg findInvtyInfo(NLCL1020CMsg bizMsg, String mdseCd, String stkStsCd) {

        INVTYTMsg inInvtyMsg = new INVTYTMsg();

        inInvtyMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
        // 10/20/2015 mod start
        // inInvtyMsg.invtyLocCd.setValue(bizMsg.invtyLocCd_FR.getValue()); 
        ZYPEZDItemValueSetter.setValue(inInvtyMsg.invtyLocCd, getInvtyLocCdFromRtlSwh(bizMsg.glblCmpyCd.getValue(), bizMsg.fromRtlWhCd.getValue(), bizMsg.fromRtlSwhCd.getValue()));
        // 10/20/2015 mod end
        inInvtyMsg.locStsCd.setValue(bizMsg.locStsCd_P1.getValue());
        inInvtyMsg.mdseCd.setValue(mdseCd);
        inInvtyMsg.stkStsCd.setValue(stkStsCd);

        return (INVTYTMsg) S21ApiTBLAccessor.findByKey(inInvtyMsg);
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param bizMsg NLCL1020CMsg
     */
    public static void setCurrentAvailableDetail(NLCL1020CMsg bizMsg) {

        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {

            bizMsg.A.no(i).invtyAvalQty_AO.clear();

            INVTYTMsg outInvtyMsg = NLCL1020CommonLogic.findInvtyInfo(bizMsg, bizMsg.A.no(i).mdseCd_A1.getValue(), bizMsg.A.no(i).stkStsCd_AP.getValue());

            if (outInvtyMsg != null && ZYPCommonFunc.hasValue(outInvtyMsg.invtyAvalQty)) {
                bizMsg.A.no(i).invtyAvalQty_AO.setValue(outInvtyMsg.invtyAvalQty.getValue());
            }
        }
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param bizMsg Business Component Interface Message
     * @return boolean
     */
    public static boolean isSubmitApplyCheck(NLCL1020CMsg bizMsg) {

        if (!isAvalLocCd(bizMsg)) {
            return false;
        }

        if (!isLocCorrelation(bizMsg)) {
            return false;
        }

        // START 2019/03/11 T.Ogura [QC#30705,ADD]
        if (!isTechPiOpenedPhysInvty(bizMsg)) {
            return false;
        }
        // END   2019/03/11 T.Ogura [QC#30705,ADD]

        if (!isDetailRecordCheck(bizMsg)) {
            return false;
        }
        return true;
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param bizMsg Business Component Interface 
     * mdseMsg
     * @return boolean
     */
    public static boolean isAvalLocCd(NLCL1020CMsg bizMsg) {

        boolean isErr = false;
        // Check Location From
        // 10/20/2015 mod start
        // isErr = checkEnableLocCd(bizMsg.glblCmpyCd.getValue(), bizMsg.invtyLocCd_FR, bizMsg.invtyLocNm_FR);
        isErr = checkEnableLocCd(bizMsg.glblCmpyCd.getValue(), bizMsg.fromRtlWhCd, bizMsg.fromRtlSwhCd, bizMsg.locNm_FR);
        // 10/20/2015 mod end

        // Check Location To
        // 10/20/2015 mod start
        // if (checkEnableLocCd(bizMsg.glblCmpyCd.getValue(), bizMsg.invtyLocCd_TO, bizMsg.invtyLocNm_TO)) {
        if (checkEnableLocCd(bizMsg.glblCmpyCd.getValue(), bizMsg.toRtlWhCd, bizMsg.toRtlSwhCd, bizMsg.locNm_TO)) {
        // 10/20/2015 mod end
            isErr = true;
        }

        if (isErr) {
            return false;
        }
        return true;
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param bizMsg Business Component Interface 
     * mdseMsg
     * @return boolean
     */
    public static boolean isAvalLocCdFroCSV(NLCL1020CMsg bizMsg) {

        boolean isErr = false;
        // Check Location From
        // 10/20/2015 mod start
        // isErr = checkEnableLocCd(bizMsg.glblCmpyCd.getValue(), bizMsg.invtyLocCd_FR, bizMsg.invtyLocNm_FR);
        isErr = checkEnableLocCd(bizMsg.glblCmpyCd.getValue(), bizMsg.fromRtlWhCd, bizMsg.fromRtlSwhCd, bizMsg.locNm_FR);
        // 10/20/2015 mod end

        // Check Location To
        // 10/20/2015 mod start
//        if (ZYPCommonFunc.hasValue(bizMsg.invtyLocCd_TO)) {
//            if (checkEnableLocCd(bizMsg.glblCmpyCd.getValue(), bizMsg.invtyLocCd_TO, bizMsg.invtyLocNm_TO)) {
//                isErr = true;
//            }
//        }
        if (ZYPCommonFunc.hasValue(bizMsg.toRtlWhCd) && ZYPCommonFunc.hasValue(bizMsg.toRtlSwhCd)) {
            if (checkEnableLocCd(bizMsg.glblCmpyCd.getValue(), bizMsg.toRtlWhCd, bizMsg.toRtlSwhCd, bizMsg.locNm_TO)) {
                isErr = true;
            }
        }
        // 10/20/2015 mod end

        if (isErr) {
            bizMsg.setMessageInfo("ZZM9037E");
            return false;
        }
        return true;
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param bizMsg Business Component Interface Message
     * @return boolean
     */
    public static boolean isLocCorrelation(NLCL1020CMsg bizMsg) {

        // 10/20/2015 mod start
//        if (bizMsg.invtyLocCd_FR.getValue().equals(bizMsg.invtyLocCd_TO.getValue())) {
//            bizMsg.invtyLocCd_FR.setErrorInfo(1, NLCL1020Constant.NLCM0035E, new String[] {NLCL1020Constant.LOC_FROM, NLCL1020Constant.LOC_TO }); 
//            bizMsg.invtyLocCd_TO.setErrorInfo(1, NLCL1020Constant.NLCM0035E, new String[] {NLCL1020Constant.LOC_FROM, NLCL1020Constant.LOC_TO }); 
//            EZDDebugOutput.println(1, "isLocCorrelation--### Error ###--Correlation check of LocFrom and LocTo", NLCL1020Constant.BUSINESS_ID);
//
//            return false;
//        }

        boolean isLocCorrelation = true;
        // WH Check
        if (bizMsg.fromRtlWhCd.getValue().equals(bizMsg.toRtlWhCd.getValue())) {
            bizMsg.fromRtlWhCd.setErrorInfo(1, NLCL1020Constant.NLCM0035E, new String[] {NLCL1020Constant.FROM_TECH_WH, NLCL1020Constant.TO_TECH_WH });
            bizMsg.toRtlWhCd.setErrorInfo(1, NLCL1020Constant.NLCM0035E, new String[] {NLCL1020Constant.FROM_TECH_WH, NLCL1020Constant.TO_TECH_WH });
            isLocCorrelation = false;
            EZDDebugOutput.println(1, "isLocCorrelation--### Error ###--Correlation check of From Tech WH and To Tech WH", NLCL1020Constant.BUSINESS_ID);
        }
        // SWH Check
        if (!bizMsg.fromRtlSwhCd.getValue().equals(bizMsg.toRtlSwhCd.getValue())) {
            bizMsg.fromRtlSwhCd.setErrorInfo(1, NLCL1020Constant.NLZM2302E);
            bizMsg.toRtlSwhCd.setErrorInfo(1, NLCL1020Constant.NLZM2302E);
            isLocCorrelation = false;
            EZDDebugOutput.println(1, "isLocCorrelation--### Error ###--Correlation check of From Tech SWH and To Tech SWH", NLCL1020Constant.BUSINESS_ID);
        }
        // 10/20/2015 mod end
        return isLocCorrelation;
    }

    // START 2019/03/11 T.Ogura [QC#30705,ADD]
    /**
     * isTechPiOpenedPhysInvty
     * @param bizMsg Business Component Interface Message
     * @return boolean
     */
    public static boolean isTechPiOpenedPhysInvty(NLCL1020CMsg bizMsg) {

        boolean isTechPiOpenedFlg = true;

        // From Tech WH/SWH Check
        if (checkOpenedPhysInvty(bizMsg.glblCmpyCd.getValue(), bizMsg.fromRtlWhCd, bizMsg.fromRtlSwhCd)) {
            isTechPiOpenedFlg = false;
            EZDDebugOutput.println(1, "isTechPiOpenedPhysInvty--### Error ###--Tech PI Opened Physical Inventory. [From Tech WH/SWH]", NLCL1020Constant.BUSINESS_ID);
        }

        // To Tech WH/SWH Check
        if (checkOpenedPhysInvty(bizMsg.glblCmpyCd.getValue(), bizMsg.toRtlWhCd, bizMsg.toRtlSwhCd)) {
            isTechPiOpenedFlg = false;
            EZDDebugOutput.println(1, "isTechPiOpenedPhysInvty--### Error ###--Tech PI Opened Physical Inventory. [To Tech WH/SWH]", NLCL1020Constant.BUSINESS_ID);
        }

        return isTechPiOpenedFlg;
    }
    // END   2019/03/11 T.Ogura [QC#30705,ADD]

    // START 2019/03/11 T.Ogura [QC#30705,ADD]
    /**
     * checkOpenedPhysInvty
     * @param glblCmpyCd String
     * @param rtlWhCd EZDCStringItem
     * @param rtlSwhCd EZDCStringItem
     * @return boolean
     */
    public static boolean checkOpenedPhysInvty(String glblCmpyCd, EZDCStringItem rtlWhCd, EZDCStringItem rtlSwhCd) {

        boolean isError = false;

        NLZC060001PMsg pMsg = new NLZC060001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.invtyLocCd, rtlWhCd.getValue() + rtlSwhCd.getValue());

        NLZC060001 nlzc060001 = new NLZC060001();
        nlzc060001.execute(pMsg, ONBATCH_TYPE.BATCH);

        if (NLZC060001Constant.RETURN_CODE_ERROR.equals(pMsg.xxRsltStsCd.getValue())) {
            rtlWhCd.setErrorInfo(1, NLCL1020Constant.NLCM0232E, new String[] {rtlWhCd.getValue() + "/" + rtlSwhCd.getValue()});
            rtlSwhCd.setErrorInfo(1, NLCL1020Constant.NLCM0232E, new String[] {rtlWhCd.getValue() + "/" + rtlSwhCd.getValue()});
            isError = true;
        }

        return isError;
    }
    // END   2019/03/11 T.Ogura [QC#30705,ADD]

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param bizMsg Business Component Interface Message
     * @return boolean
     */
    public static boolean isDetailRecordCheck(NLCL1020CMsg bizMsg) {

        boolean outIsSSAllocationDetailCheck;
        boolean outIsAvailableQtyDetailCheck;
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {

            outIsSSAllocationDetailCheck = isSSAllocationDetailCheck(bizMsg, bizMsg.A.no(i));

            if (!outIsSSAllocationDetailCheck) {
                return false;
            }

            outIsAvailableQtyDetailCheck = isAvailableQtyDetailCheck(bizMsg, bizMsg.A.no(i));

            if (!outIsAvailableQtyDetailCheck) {
                return false;
            }
        }
        return true;
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param bizMsg NLCL1020CMsg
     * @param bizMsgA NLCL1020_ACMsg
     * @return boolean
     */
    public static boolean isSSAllocationDetailCheck(NLCL1020CMsg bizMsg, NLCL1020_ACMsg bizMsgA) {

        AVAL_INVTY_APP_IDTMsg tMsg = NLCL1020CommonLogic.findAvalInvtyAppIdInfo(bizMsg, bizMsgA.stkStsCd_AP.getValue());

        if (tMsg == null) {
            bizMsgA.stkStsCd_AP.setErrorInfo(1, NLCL1020Constant.NLCM0067E, new String[] {NLCL1020Constant.STOCK_STATUS });
            EZDDebugOutput.println(1, "isSSAllocationDetailCheck--### Error ###--[Aval Invyt App Id]No Search Results.", NLCL1020Constant.BUSINESS_ID);

            return false;
        }
        return true;
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param bizMsg NLCL1020CMsg
     * @param bizMsgA NLCL1020_ACMsg
     * @return boolean
     */
    public static boolean isAvailableQtyDetailCheck(NLCL1020CMsg bizMsg, NLCL1020_ACMsg bizMsgA) {

        bizMsgA.invtyAvalQty_AO.clear();

        String mdseCd = bizMsgA.mdseCd_A1.getValue();
        String stkStsCd = bizMsgA.stkStsCd_AP.getValue();

        INVTYTMsg outInvtyMsg = NLCL1020CommonLogic.findInvtyInfo(bizMsg, mdseCd, stkStsCd);

        BigDecimal invtyAvalQty;

        if (outInvtyMsg == null) {
            bizMsgA.invtyAvalQty_AI.setErrorInfo(1, NLCL1020Constant.NLCM0021E);
            EZDDebugOutput.println(1, "isAvailableQtyDetailCheck--### Error ###--[Invty]No Search Results.", NLCL1020Constant.BUSINESS_ID);

            return false;
        } else {

            invtyAvalQty = outInvtyMsg.invtyAvalQty.getValue();
            bizMsgA.invtyAvalQty_AO.setValue(invtyAvalQty);
        }

        if (ZYPCommonFunc.hasValue(invtyAvalQty) && BigDecimal.ZERO.compareTo(invtyAvalQty) > 0) {
            bizMsgA.invtyAvalQty_AI.setErrorInfo(1, NLCL1020Constant.NLCM0032E);
            EZDDebugOutput.println(1, "isAvailableQtyDetailCheck--### Error ###--[InvtyAvalQty]is Minus.", NLCL1020Constant.BUSINESS_ID);

            return false;
        }

        if (!ZYPCommonFunc.hasValue(invtyAvalQty) || invtyAvalQty.compareTo(bizMsgA.invtyAvalQty_AI.getValue()) < 0) {
            bizMsgA.invtyAvalQty_AI.setErrorInfo(1, NLCL1020Constant.NLCM0016E, new String[] {NLCL1020Constant.QTY, NLCL1020Constant.CURRENT_AVAILABLE_QTY });
            EZDDebugOutput.println(1, "isAvailableQtyDetailCheck--### Error ###--[InvtyAvalQty]Comparison.", NLCL1020Constant.BUSINESS_ID);

            return false;
        }
        return true;
    }

    /**
     * searchByTrxNum
     * @param paramMap Map
     * @return List
     */
    @SuppressWarnings("unchecked")
    public static List<Object> searchByTrxNum(Map<String, Object> paramMap) {
        S21SsmEZDResult result = NLCL1020Query.getInstance().searchByTrxNum(paramMap);
        List<Object> list = null;
        if (result.isCodeNormal()) {
            list = (List) result.getResultObject();
        }
        return list;
    }


    /**
     * Method name: checkNull
     * <dd>The method explanation: checkNull
     * <dd>Remarks:
     * @param val String
     * @return String
     */
    public static String checkNull(String val) {
        if (!ZYPCommonFunc.hasValue(val)) {
            return "";
        } else {
            return val;
        }
    }

    /**
     * Method name: checkNull
     * <dd>The method explanation: checkNull
     * <dd>Remarks:
     * @param val BigDecimal
     * @return BigDecimal
     */
    public static BigDecimal checkNull(BigDecimal val) {

        if (!ZYPCommonFunc.hasValue(val)) {
            return BigDecimal.ZERO;
        } else {
            return val;
        }
    }

    /**
     * Get LocRoleTp For Popup
     * @param  glblCmpyCd String
     * @return String LocRoleTp
     */
    public static String getLocRoleTpForPopup(String glblCmpyCd) {
        return NMXC100001EnableWH.getLocRoleTpForPopup(glblCmpyCd, NLCL1020Constant.BUSINESS_ID);
    }

    /**
     * check Enable LocCd
     * @param glblCmpyCd String
     * @param ezdRtlWhCd EZDCStringItem 
     * @param ezdRtlSwhCd EZDCStringItem 
     * @param ezdLocNm EZDCStringItem
     * @return boolean
     */
    public static boolean checkEnableLocCd(String glblCmpyCd, EZDCStringItem ezdRtlWhCd, EZDCStringItem ezdRtlSwhCd, EZDCStringItem ezdLocNm) {

        // 10/20/2015 mod start
//        NMXC100001EnableWHData dwxc100001EnableWHFrom = 
//            NMXC100001EnableWH.checkEnableWH(glblCmpyCd, locCd.getValue(), NLCL1020Constant.BUSINESS_ID, null, ZYPConstant.FLG_OFF_N);
//        if (dwxc100001EnableWHFrom == null) {
//            locCd.setErrorInfo(1, NLCL1020Constant.NLCM0004E);
//            return true;
//        } else if (ZYPCommonFunc.hasValue(dwxc100001EnableWHFrom.getXxMsgId())) {
//            locCd.setErrorInfo(1, dwxc100001EnableWHFrom.getXxMsgId());
//            return true;
//        } else {
//            locNm.setValue(dwxc100001EnableWHFrom.getInvtyLocNm());
//        }
        String strLocCd = getInvtyLocCdFromRtlSwh(glblCmpyCd, ezdRtlWhCd.getValue(), ezdRtlSwhCd.getValue());
        NMXC100001EnableWHData dwxc100001EnableWHFrom = NMXC100001EnableWH.checkEnableWH(glblCmpyCd, strLocCd, NLCL1020Constant.BUSINESS_ID, null, ZYPConstant.FLG_OFF_N);
        if (dwxc100001EnableWHFrom == null) {
            ezdRtlWhCd.setErrorInfo(1, NLCL1020Constant.NLCM0004E);
            ezdRtlSwhCd.setErrorInfo(1, NLCL1020Constant.NLCM0004E);
            return true;
        } else if (ZYPCommonFunc.hasValue(dwxc100001EnableWHFrom.getXxMsgId())) {
            ezdRtlWhCd.setErrorInfo(1, dwxc100001EnableWHFrom.getXxMsgId());
            ezdRtlSwhCd.setErrorInfo(1, dwxc100001EnableWHFrom.getXxMsgId());
            return true;
        } else {
            ezdLocNm.setValue(dwxc100001EnableWHFrom.getInvtyLocNm());
        }
        // 10/20/2015 mod end
        return false;
    }

    /**
     * Get INVTY_LOC_CD from RTL_SWH table
     * @param glblCmpyCd String
     * @param rtlWhCd String
     * @param rtlSwhCd String
     * @return String
     * 
     */
    public static String getInvtyLocCdFromRtlSwh(String glblCmpyCd, String rtlWhCd, String rtlSwhCd) {
        RTL_SWHTMsg rtlSwh = new RTL_SWHTMsg();
        ZYPEZDItemValueSetter.setValue(rtlSwh.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(rtlSwh.rtlWhCd, rtlWhCd);
        ZYPEZDItemValueSetter.setValue(rtlSwh.rtlSwhCd, rtlSwhCd);
        rtlSwh = (RTL_SWHTMsg) S21FastTBLAccessor.findByKey(rtlSwh);
        if (rtlSwh == null) {
            return "";
        } else {
            return rtlSwh.invtyLocCd.getValue();
        }
    }
}
