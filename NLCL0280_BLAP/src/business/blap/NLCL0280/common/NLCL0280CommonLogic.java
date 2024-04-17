/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLCL0280.common;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import parts.common.EZDCStringItem;
import parts.common.EZDMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NLCL0280.NLCL0280CMsg;
import business.blap.NLCL0280.NLCL0280Query;
import business.blap.NLCL0280.NLCL0280SMsg;
import business.blap.NLCL0280.NLCL0280_ASMsg;
import business.blap.NLCL0280.constant.NLCL0280Constant;
import business.db.COA_PRODTMsg;
import business.db.MDSETMsg;
import business.db.ORD_TAKE_MDSETMsg;
import business.db.RTL_SWHTMsg;
import business.db.RTL_WHTMsg;
import business.db.SAVE_SRCH_OPTTMsg;
import business.db.SAVE_SRCH_OPTTMsgArray;
import business.db.SWHTMsg;
import business.parts.NSZC033001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC033001.NSZC033001;
import com.canon.cusa.s21.api.NSZ.NSZC033001.constant.NSZC033001Constant;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPRecHistUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 *<pre>
 * CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/25   CITS            T.Tokutomi      Create          N/A
 * 05/25/2016   CSAI            Y.Imazu         Update          QC#6864
 * 07/21/2016   CSAI            Y.Imazu         Update          QC#10315
 * 04/07/2023   CSA             G.Quan          Insert          QC#61206
 * 04/21/2023   CITS            DuyLe           Update          QC#61403
 *</pre>
 */
public class NLCL0280CommonLogic {

    /**
     * setPulldownSaveSearch
     * @param cMsg NLBL2020CMsg
     * @param glblCmpyCd String
     * @param userId String
     */
    public static void setPulldownSaveSearch(NLCL0280CMsg cMsg, String glblCmpyCd, String userId) {

        // Clear
        cMsg.srchOptPk_PD.clear();
        cMsg.srchOptNm_PD.clear();

        SAVE_SRCH_OPTTMsg sso = new SAVE_SRCH_OPTTMsg();
        sso.setSQLID("001");
        sso.setConditionValue("glblCmpyCd01", glblCmpyCd);
        sso.setConditionValue("srchOptAplId01", NLCL0280Constant.BUSINESS_ID);
        sso.setConditionValue("srchOptUsrId01", userId);

        SAVE_SRCH_OPTTMsgArray ssoList = (SAVE_SRCH_OPTTMsgArray) EZDTBLAccessor.findByCondition(sso);

        for (int i = 0; i < ssoList.getValidCount(); i++) {

            ZYPEZDItemValueSetter.setValue(cMsg.srchOptPk_PD.no(i), ssoList.no(i).srchOptPk);
            ZYPEZDItemValueSetter.setValue(cMsg.srchOptNm_PD.no(i), ssoList.no(i).srchOptNm);
        }
    }

    /**
     * setPulldownTrxType
     * @param cMsg NLCL0280CMsg
     * @param glblCmpyCd String
     */
    public static void setPulldownTrxType(NLCL0280CMsg cMsg, String glblCmpyCd) {

        // Clear
        cMsg.invtyTrxTpCd_PD.clear();
        cMsg.invtyTrxTpDescTxt_PD.clear();

        // Generate PullDown.
        S21SsmEZDResult result = NLCL0280Query.getInstance().getTrxTypeList(glblCmpyCd);

        if (result.isCodeNormal()) {

            List<Map<String, Object>> trxTpList = (ArrayList<Map<String, Object>>) result.getResultObject();

            for (int i = 0; i < trxTpList.size(); i++) {

                Map<String, Object> trxTpMap = trxTpList.get(i);
                ZYPEZDItemValueSetter.setValue(cMsg.invtyTrxTpCd_PD.no(i), (String) trxTpMap.get("INVTY_TRX_TP_CD"));
                ZYPEZDItemValueSetter.setValue(cMsg.invtyTrxTpDescTxt_PD.no(i), (String) trxTpMap.get("INVTY_TRX_TP_DESC_TXT"));
            }
        }
    }

    /**
     * setPulldownTrxCd
     * @param cMsg NLCL0280CMsg
     * @param glblCmpyCd String
     */
    public static void setPulldownTrxCd(NLCL0280CMsg cMsg, String glblCmpyCd) {

        // Clear
        cMsg.trxCd_PD.clear();
        cMsg.xxScrItem61Txt_TX.clear();

        // Generate PullDown.
        S21SsmEZDResult result = NLCL0280Query.getInstance().getTrxCodeList(glblCmpyCd);

        if (result.isCodeNormal()) {

            List<Map<String, Object>> trxList = (ArrayList<Map<String, Object>>) result.getResultObject();

            for (int i = 0; i < trxList.size(); i++) {

                Map<String, Object> trxMap = trxList.get(i);
                ZYPEZDItemValueSetter.setValue(cMsg.trxCd_PD.no(i), (String) trxMap.get("TRX_CD"));
                ZYPEZDItemValueSetter.setValue(cMsg.xxScrItem61Txt_TX.no(i), (String) trxMap.get("TRX_DESC_TXT"));
            }
        }
    }

    /**
     * setPulldownTrxCd
     * @param cMsg NLCL0280CMsg
     * @param glblCmpyCd String
     */
    public static void changePulldownTrxCdList(NLCL0280CMsg cMsg, String glblCmpyCd) {

        // Clear
        cMsg.trxCd_PD.clear();
        cMsg.xxScrItem61Txt_TX.clear();

        // Generate PullDown.
        S21SsmEZDResult result = NLCL0280Query.getInstance().selectTrxCodeList(glblCmpyCd, cMsg.invtyTrxTpCd_PS.getValue());

        if (result.isCodeNormal()) {

            List<Map<String, Object>> trxList = (ArrayList<Map<String, Object>>) result.getResultObject();

            for (int i = 0; i < trxList.size(); i++) {

                Map<String, Object> trxMap = trxList.get(i);
                ZYPEZDItemValueSetter.setValue(cMsg.trxCd_PD.no(i), (String) trxMap.get("TRX_CD"));
                ZYPEZDItemValueSetter.setValue(cMsg.xxScrItem61Txt_TX.no(i), (String) trxMap.get("TRX_DESC_TXT"));
            }
        }
    }

    /**
     * changePulldownTrxRsnCdList
     * @param cMsg NLCL0280CMsg
     * @param glblCmpyCd String
     */
    public static void changePulldownTrxRsnCdList(NLCL0280CMsg cMsg, String glblCmpyCd) {

        // Clear
        cMsg.trxRsnCd_PD.clear();
        cMsg.xxScrItem61Txt_TR.clear();

        // Generate PullDown.
        S21SsmEZDResult result = NLCL0280Query.getInstance().selectTrxRsnCodeList(glblCmpyCd, cMsg.invtyTrxTpCd_PS.getValue());

        if (result.isCodeNormal()) {

            List<Map<String, Object>> trxRsnList = (ArrayList<Map<String, Object>>) result.getResultObject();

            for (int i = 0; i < trxRsnList.size(); i++) {

                Map<String, Object> trxRsnMap = trxRsnList.get(i);
                ZYPEZDItemValueSetter.setValue(cMsg.trxRsnCd_PD.no(i), (String) trxRsnMap.get("TRX_RSN_CD"));
                ZYPEZDItemValueSetter.setValue(cMsg.xxScrItem61Txt_TR.no(i), (String) trxRsnMap.get("TRX_RSN_DESC_TXT"));
            }
        }
    }

    /**
     * changePulldownTrxRsnCdListSelTrxCdNoType
     * @param cMsg NLCL0280CMsg
     * @param glblCmpyCd String
     */
    public static void changePulldownTrxRsnCdListSelTrxCdNoType(NLCL0280CMsg cMsg, String glblCmpyCd) {

        // Clear
        cMsg.trxRsnCd_PD.clear();
        cMsg.xxScrItem61Txt_TR.clear();

        // Generate PullDown.
        S21SsmEZDResult result = NLCL0280Query.getInstance().selectTrxRsnCodeListNoTrxTp(glblCmpyCd, cMsg.trxCd_PS.getValue());

        if (result.isCodeNormal()) {

            List<Map<String, Object>> trxRsnList = (ArrayList<Map<String, Object>>) result.getResultObject();

            for (int i = 0; i < trxRsnList.size(); i++) {

                Map<String, Object> trxRsnMap = trxRsnList.get(i);
                ZYPEZDItemValueSetter.setValue(cMsg.trxRsnCd_PD.no(i), (String) trxRsnMap.get("TRX_RSN_CD"));
                ZYPEZDItemValueSetter.setValue(cMsg.xxScrItem61Txt_TR.no(i), (String) trxRsnMap.get("TRX_RSN_DESC_TXT"));
            }
        }
    }

    /**
     * changePulldownTrxRsnCdListSelTrxCdHasType
     * @param cMsg NLCL0280CMsg
     * @param glblCmpyCd String
     */
    public static void changePulldownTrxRsnCdListSelTrxCdHasType(NLCL0280CMsg cMsg, String glblCmpyCd) {

        // Clear
        cMsg.trxRsnCd_PD.clear();
        cMsg.xxScrItem61Txt_TR.clear();

        // Generate PullDown.
        S21SsmEZDResult result = NLCL0280Query.getInstance().selectTrxRsnCodeListHasTrxTp(glblCmpyCd, cMsg.trxCd_PS.getValue(), cMsg.invtyTrxTpCd_PS.getValue());

        if (result.isCodeNormal()) {

            List<Map<String, Object>> trxRsnList = (ArrayList<Map<String, Object>>) result.getResultObject();

            for (int i = 0; i < trxRsnList.size(); i++) {

                Map<String, Object> trxRsnMap = trxRsnList.get(i);
                ZYPEZDItemValueSetter.setValue(cMsg.trxRsnCd_PD.no(i), (String) trxRsnMap.get("TRX_RSN_CD"));
                ZYPEZDItemValueSetter.setValue(cMsg.xxScrItem61Txt_TR.no(i), (String) trxRsnMap.get("TRX_RSN_DESC_TXT"));
            }
        }
    }

    /**
     * setPulldownTrxType
     * @param cMsg NLCL0280CMsg
     */
    public static void setPulldownItemType(NLCL0280CMsg cMsg) {

        // Clear
        cMsg.mdseItemTpCd_PD.clear();
        cMsg.xxScrItem61Txt_MT.clear();

        // generate pull down
        ZYPCodeDataUtil.createPulldownList("MDSE_ITEM_TP", cMsg.mdseItemTpCd_PD, cMsg.xxScrItem61Txt_MT, ":");
    }

    /**
     * setPulldownMdseType
     * @param cMsg NLCL0280CMsg
     */
    public static void setPulldownMdseType(NLCL0280CMsg cMsg) {

        // Clear
        cMsg.coaProjCd_PD.clear();
        cMsg.xxScrItem61Txt_PC.clear();

        // generate pull down
        ZYPCodeDataUtil.createPulldownList("COA_PROJ", cMsg.coaProjCd_PD, cMsg.xxScrItem61Txt_PC, ":");
    }

    /**
     * setPulldownXrefType
     * @param cMsg NLCL0280CMsg
     */
    public static void setPulldownXrefType(NLCL0280CMsg cMsg) {

        // Clear
        cMsg.mdseItemRelnTpCd_PD.clear();
        cMsg.mdseItemRelnTpDescTxt_PD.clear();

        // generate pull down
        ZYPCodeDataUtil.createPulldownList("MDSE_ITEM_RELN_TP", cMsg.mdseItemRelnTpCd_PD, cMsg.mdseItemRelnTpDescTxt_PD);
    }

    /**
     * createLocStsChkBox
     * @param cMsg NLCL0280CMsg
     * @param glblCmpyCd String
     */
    public static void createLocStsChkBox(NLCL0280CMsg cMsg, String glblCmpyCd) {

        S21SsmEZDResult ssmResult = NLCL0280Query.getInstance().getLocStsList(glblCmpyCd);

        if (ssmResult.isCodeNormal()) {

            List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();

            if (resultList != null && !resultList.isEmpty()) {

                int validCount = resultList.size();

                for (int i = 0; i < resultList.size(); i++) {

                    Map<String, Object> resultMap = (Map<String, Object>) resultList.get(i);

                    if (i >= cMsg.L.length()) {

                        validCount = i;
                        break;
                    }

                    ZYPEZDItemValueSetter.setValue(cMsg.L.no(i).xxScrItem61Txt_LS, (String) resultMap.get("LOC_STS_DESC_TXT"));
                    ZYPEZDItemValueSetter.setValue(cMsg.L.no(i).locStsCd_LS, (String) resultMap.get("LOC_STS_CD"));
                }

                cMsg.L.setValidCount(validCount);
                return;
            }
        }

        cMsg.setMessageInfo(NLCL0280Constant.NLCM0170E, new String[]{"Location Status Master"});
        return;
    }

    /**
     * hasWareHouseLocation
     * @param glblCmpyCd String
     * @param cMsg NLCL0280CMsg
     * @return boolean true: exist false:not exist
     */
    public static boolean hasWhLocation(String glblCmpyCd, NLCL0280CMsg cMsg) {

        RTL_SWHTMsg rtlSwhTMsg = new RTL_SWHTMsg();
        ZYPEZDItemValueSetter.setValue(rtlSwhTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(rtlSwhTMsg.rtlWhCd, cMsg.rtlWhCd_H1.getValue());
        ZYPEZDItemValueSetter.setValue(rtlSwhTMsg.rtlSwhCd, cMsg.rtlSwhCd_H1.getValue());
        rtlSwhTMsg = (RTL_SWHTMsg) EZDTBLAccessor.findByKey(rtlSwhTMsg);

        if (rtlSwhTMsg != null) {

            ZYPEZDItemValueSetter.setValue(cMsg.rtlSwhNm_H1, rtlSwhTMsg.rtlSwhNm.getValue());
            return true;
        }

        cMsg.rtlWhCd_H1.setErrorInfo(1, NLCL0280Constant.NLZM2279E, new String[] {"Warehouse", "Sub Warehouse" });
        cMsg.rtlSwhCd_H1.setErrorInfo(1, NLCL0280Constant.NLZM2279E, new String[] {"Warehouse", "Sub Warehouse" });
        cMsg.setMessageInfo(NLCL0280Constant.ZZM9037E);
        return false;
    }

    /**
     * chkOrdTakeMdse
     * @param glblCmpyCd String
     * @param mdseCd String
     * @return boolean
     */
    public static boolean chkOrdTakeMdse(String glblCmpyCd, String mdseCd) {

        if (mdseCd.length() == NLCL0280Constant.MDSE_DIGIT_8) {

            ORD_TAKE_MDSETMsg ordTakeMdseTMsg = new ORD_TAKE_MDSETMsg();
            ZYPEZDItemValueSetter.setValue(ordTakeMdseTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(ordTakeMdseTMsg.ordTakeMdseCd, mdseCd);

            ordTakeMdseTMsg = (ORD_TAKE_MDSETMsg) EZDTBLAccessor.findByKey(ordTakeMdseTMsg);

            if (ordTakeMdseTMsg != null) {

                return true;

            } else {

                return false;
            }

        } else {

            return false;
        }
    }

    /**
     * hasMdseCd
     * @param glblCmpyCd String
     * @param mdseCd String
     * @return boolean true: exist false:not exist
     */
    public static boolean hasMdseCd(String glblCmpyCd, String mdseCd) {

        MDSETMsg mdseTMsg = new MDSETMsg();
        ZYPEZDItemValueSetter.setValue(mdseTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(mdseTMsg.mdseCd, mdseCd);
        mdseTMsg = (MDSETMsg) EZDTBLAccessor.findByKey(mdseTMsg);

        if (mdseTMsg != null) {

            return true;

        } else {

            return false;
        }
    }

    /**
     * hasRtlWhCd
     * @param glblCmpyCd String
     * @param rtlWhCd String
     * @return boolean true: exist false:not exist
     */
    public static boolean setCorProdDescTxt(String glblCmpyCd, NLCL0280CMsg cMsg) {

        COA_PRODTMsg coaProdTMsg = new COA_PRODTMsg();
        ZYPEZDItemValueSetter.setValue(coaProdTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(coaProdTMsg.coaProdCd, cMsg.coaProdCd_H1.getValue());
        coaProdTMsg = (COA_PRODTMsg) EZDTBLAccessor.findByKey(coaProdTMsg);

        if (coaProdTMsg != null) {

            ZYPEZDItemValueSetter.setValue(cMsg.coaProdDescTxt_H1, coaProdTMsg.coaProdDescTxt);
            return true;

        } else {

            cMsg.coaProdCd_H1.setErrorInfo(1, NLCL0280Constant.NLZM2278E, new String[] {"Product" });
            cMsg.coaProdDescTxt_H1.clear();
            cMsg.setMessageInfo(NLCL0280Constant.ZZM9037E);
            return false;
        }
    }

    /**
     * setRtlSubWhNm
     * @param glblCmpyCd String
     * @param rtlWhCd String
     * @return boolean true: exist false:not exist
     */
    public static boolean setRtlSubWhNm(String glblCmpyCd, NLCL0280CMsg cMsg) {

        SWHTMsg swhTMsg = new SWHTMsg();
        ZYPEZDItemValueSetter.setValue(swhTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(swhTMsg.rtlSwhCd, cMsg.rtlSwhCd_H1.getValue());
        swhTMsg = (SWHTMsg) EZDTBLAccessor.findByKey(swhTMsg);

        if (swhTMsg != null) {

            ZYPEZDItemValueSetter.setValue(cMsg.rtlSwhNm_H1, swhTMsg.rtlSwhNm.getValue());
            return true;

        } else {

            cMsg.rtlSwhCd_H1.setErrorInfo(1, NLCL0280Constant.NLZM2278E, new String[] {"Sub Warehouse" });
            cMsg.rtlSwhNm_H1.clear();
            cMsg.setMessageInfo(NLCL0280Constant.ZZM9037E);
            return false;
        }
    }

    /**
     * setRtlWhNm
     * @param glblCmpyCd String
     * @param rtlWhCd String
     * @return boolean true: exist false:not exist
     */
    public static boolean setRtlWhNm(String glblCmpyCd, NLCL0280CMsg cMsg) {

        RTL_WHTMsg rtlWhTMsg = new RTL_WHTMsg();
        ZYPEZDItemValueSetter.setValue(rtlWhTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(rtlWhTMsg.rtlWhCd, cMsg.rtlWhCd_H1.getValue());
        rtlWhTMsg = (RTL_WHTMsg) EZDTBLAccessor.findByKey(rtlWhTMsg);

        if (rtlWhTMsg != null) {

            ZYPEZDItemValueSetter.setValue(cMsg.rtlWhNm_H1, rtlWhTMsg.rtlWhNm.getValue());
            return true;

        } else {

            cMsg.rtlWhCd_H1.setErrorInfo(1, NLCL0280Constant.NLZM2278E, new String[] {"Warehouse" });
            cMsg.rtlWhNm_H1.clear();
            cMsg.setMessageInfo(NLCL0280Constant.ZZM9037E);
            return false;
        }
    }

    /**
     * setRtlWhNm
     * @param glblCmpyCd String
     * @param rtlWhCd String
     * @return boolean true: exist false:not exist
     */
    public static boolean setVndNm(String glblCmpyCd, NLCL0280CMsg cMsg) {

        S21SsmEZDResult result = NLCL0280Query.getInstance().getVndNm(glblCmpyCd, cMsg.vndCd_H1.getValue());

        if (result.isCodeNormal()) {

            String locNm = (String) result.getResultObject();
            ZYPEZDItemValueSetter.setValue(cMsg.locNm_H1, locNm);
            return true;

        } else {

            cMsg.vndCd_H1.setErrorInfo(1, NLCL0280Constant.NLZM2278E, new String[] {"Supplier" });
            cMsg.locNm_H1.clear();
            cMsg.setMessageInfo(NLCL0280Constant.ZZM9037E);
            return false;
        }
    }

    /**
     * setShipFromToLocNm
     * @param glblCmpyCd String
     * @param cMsg NLCL0280CMsg
     * @param shipFromToLocCd EZDCStringItem
     * @param shipFromToLocNm EZDCStringItem
     * @param locTpCd String
     * @param errTxt String
     * @return Map<String, Object>
     */
    public static Map<String, Object> setShipFromToLocNm(String glblCmpyCd, NLCL0280CMsg cMsg, EZDCStringItem shipFromToLocCd, EZDCStringItem shipFromToLocNm, String locTpCd, String errTxt) {

        S21SsmEZDResult result = NLCL0280Query.getInstance().getShipToLocationInfo(glblCmpyCd, shipFromToLocCd.getValue());

        if (result.isCodeNormal()) {

            List<Map<String, Object>> list = (ArrayList<Map<String, Object>>) result.getResultObject();

            if (list.size() > 0) {

                return list.get(0);
            }
        }

        shipFromToLocCd.setErrorInfo(1, NLCL0280Constant.NLZM2278E, new String[] {errTxt});
        shipFromToLocNm.clear();
        cMsg.setMessageInfo(NLCL0280Constant.ZZM9037E);
        return null;
    }

    /**
     * search
     * @param glblCmpyCd String
     * @param cMsg NLCL0280CMsg
     * @param sMsg NLCL0280SMsg
     * @param boolean ordTakeMdse
     * @param shipFromLocTpCd String
     * @param shipToLocTpCd String
     */
    public static void search(String glblCmpyCd, NLCL0280CMsg cMsg, NLCL0280SMsg sMsg, boolean ordTakeMdse, String shipFromLocTpCd, String shipToLocTpCd) {

        // Clear
        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(sMsg.A);

        // Updated by DuyLe - 04/21/2023 - QC#61403 - Added rownum parameter - START
        S21SsmEZDResult result = NLCL0280Query.getInstance().getInvtyTrx(glblCmpyCd, cMsg, ordTakeMdse, shipFromLocTpCd, shipToLocTpCd, NLCL0280Constant.LIMIT_SEARCH_ROWNUM);
        // Updated by DuyLe - 04/21/2023 - QC#61403 - Added rownum parameter - END

        if (result.isCodeNormal()) {

            List<Map<String, Object>> invtyTrxList = (ArrayList<Map<String, Object>>) result.getResultObject();
//            Map<BigDecimal, List<String>> rcvSerListMap = getRcvSerMapList(glblCmpyCd, invtyTrxList);

            int index = 0;
//            BigDecimal preInvtyTrxPk = null;
//            BigDecimal rmnTrxQty = BigDecimal.ZERO;
//            Map<String, Object> preInvtyTrxMap = new HashMap<String, Object>();

            // Set result to sMsg
            for (Map<String, Object> invtyTrxMap : invtyTrxList) {
//
//                BigDecimal invtyTrxPk = (BigDecimal) invtyTrxMap.get("INVTY_TRX_PK");
//                BigDecimal invtyTrxQty = (BigDecimal) invtyTrxMap.get("INVTY_TRX_QTY");
//
//                List<String> rcvSerList = rcvSerListMap.get(invtyTrxPk);
//
//                /*************************************************************
//                 * Receiving Trx for Serial
//                 ************************************************************/
//                if (rcvSerList != null && !rcvSerList.isEmpty()) {
//
//                    rmnTrxQty = invtyTrxQty;
//                    preInvtyTrxPk = null;
//
//                    for (String rcvSerNum : rcvSerList) {
//
//                        // Specified Serial Check
//                        if (ZYPCommonFunc.hasValue(cMsg.serNum_H1) && !cMsg.serNum_H1.getValue().equals(rcvSerNum)) {
//
//                            continue;
//                        }
//
//                        if (index == sMsg.A.length()) {
//
//                            index++;
//                            break;
//                        }
//
//                        // Quantity Sign Check
//                        if (BigDecimal.ZERO.compareTo(invtyTrxQty) > 0) {
//
//                            setRsltToSMsg(sMsg.A.no(index), invtyTrxMap, rcvSerNum, BigDecimal.ONE.negate());
//                            rmnTrxQty = rmnTrxQty.add(BigDecimal.ONE);
//
//                        } else {
//
//                            setRsltToSMsg(sMsg.A.no(index), invtyTrxMap, rcvSerNum, BigDecimal.ONE);
//                            rmnTrxQty = rmnTrxQty.subtract(BigDecimal.ONE);
//                        }
//
//                        index++;
//
//                        // Remaining Quantity Check
//                        if (BigDecimal.ZERO.compareTo(rmnTrxQty) == 0) {
//
//                            break;
//                        }
//                    }
//
//                    if (index > sMsg.A.length()) {
//
//                        break;
//                    }
//
//                    // Process for Remaining Quantity
//                    if (!ZYPCommonFunc.hasValue(cMsg.serNum_H1) && BigDecimal.ZERO.compareTo(rmnTrxQty.abs()) < 0) {
//
//                        if (index == sMsg.A.length()) {
//
//                            index++;
//                            break;
//                        }
//
//                        setRsltToSMsg(sMsg.A.no(index), invtyTrxMap, null, rmnTrxQty);
//                        index++;
//                    }
//
//                    rmnTrxQty = BigDecimal.ZERO;
//
//
//                /*************************************************************
//                 * Shipping and Receiving Trx for Non-Serial
//                 ************************************************************/
//                } else {
//
//                    String serNum = (String) invtyTrxMap.get("SER_NUM");
//
//                    // Specified Serial Check
//                    if (ZYPCommonFunc.hasValue(cMsg.serNum_H1) && !cMsg.serNum_H1.getValue().equals(serNum)) {
//
//                        continue;
//                    }
//
//                    // Process for Remaining Previous Quantity
//                    if (!ZYPCommonFunc.hasValue(cMsg.serNum_H1) && preInvtyTrxPk != null && !preInvtyTrxPk.equals(invtyTrxPk) && BigDecimal.ZERO.compareTo(rmnTrxQty.abs()) < 0) {
//
//                        if (index == sMsg.A.length()) {
//
//                            index++;
//                            break;
//                        }
//
//                        setRsltToSMsg(sMsg.A.no(index), preInvtyTrxMap, null, rmnTrxQty);
//                        index++;
//                    }
//
//                    // Initialize Remaining Quantity
//                    if (preInvtyTrxPk == null || !preInvtyTrxPk.equals(invtyTrxPk)) {
//
//                        rmnTrxQty = invtyTrxQty;
//                    }
//
//                    if (index == sMsg.A.length()) {
//
//                        index++;
//                        break;
//                    }
//
//                    if (ZYPCommonFunc.hasValue(serNum)) {
//
//                        // Quantity Sign Check
//                        if (BigDecimal.ZERO.compareTo(invtyTrxQty) > 0) {
//
//                            setRsltToSMsg(sMsg.A.no(index), invtyTrxMap, serNum, BigDecimal.ONE.negate());
//                            rmnTrxQty = rmnTrxQty.add(BigDecimal.ONE);
//
//                        } else {
//
//                            setRsltToSMsg(sMsg.A.no(index), invtyTrxMap, serNum, BigDecimal.ONE);
//                            rmnTrxQty = rmnTrxQty.subtract(BigDecimal.ONE);
//                        }
//
//                    } else {
//
//                        setRsltToSMsg(sMsg.A.no(index), invtyTrxMap, null, invtyTrxQty);
//                        rmnTrxQty = BigDecimal.ZERO;
//                    }
//
//                    index++;
//
//                    preInvtyTrxMap = invtyTrxMap;
//                    preInvtyTrxPk = invtyTrxPk;
//                }

                if (index < sMsg.A.length()) {
                    setRsltToSMsg(sMsg.A.no(index), invtyTrxMap);
                }

                index++;

            }
//
//
//            // Process for Remaining Previous Quantity
//            if (!ZYPCommonFunc.hasValue(cMsg.serNum_H1) && index < sMsg.A.length() && preInvtyTrxPk != null && BigDecimal.ZERO.compareTo(rmnTrxQty.abs()) < 0) {
//
//                setRsltToSMsg(sMsg.A.no(index), preInvtyTrxMap, null, rmnTrxQty);
//                index++;
//            }

            int resultCnt = index;

            if (index == 0) {

                cMsg.setMessageInfo(NLCL0280Constant.NZZM0000E);
                cMsg.xxPageShowFromNum.clear();
                cMsg.xxPageShowOfNum.clear();
                cMsg.xxPageShowToNum.clear();
                return;
            }

            if (index > sMsg.A.length()) {

                cMsg.setMessageInfo(NLCL0280Constant.NZZM0001W);
                resultCnt = sMsg.A.length();

            } else {

                cMsg.setMessageInfo(NLCL0280Constant.ZZZM9003I, new String[]{"Search"});
            }

            sMsg.A.setValidCount(resultCnt);

            // copy 1page sMsg => cMsg
            int i = 0;

            for (; i < sMsg.A.getValidCount(); i++) {

                if (i == cMsg.A.length()) {

                    break;
                }

                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
            }

            cMsg.A.setValidCount(i);

            // set Page info
            cMsg.xxPageShowFromNum.setValue(1);
            cMsg.xxPageShowToNum.setValue(cMsg.A.getValidCount());
            cMsg.xxPageShowOfNum.setValue(resultCnt);

        } else {

            cMsg.setMessageInfo(NLCL0280Constant.NZZM0000E);
            cMsg.xxPageShowFromNum.clear();
            cMsg.xxPageShowOfNum.clear();
            cMsg.xxPageShowToNum.clear();
        }
    }

    /**
     * getRcvSerMapList
     * @param glblCmpyCd String
     * @param invtyTrxList List<Map<String, Object>>
     * @return Map<BigDecimal, List<String>>
     */
    private static Map<BigDecimal, List<String>> getRcvSerMapList(String glblCmpyCd, List<Map<String, Object>> invtyTrxList) {

        List<BigDecimal> rcvSerInvtyTrxPkList = new ArrayList<BigDecimal>();

        for (Map<String, Object> invtyTrxMap : invtyTrxList) {

            String serNum = (String) invtyTrxMap.get("SER_NUM");
            String rwsNum = (String) invtyTrxMap.get("RWS_NUM");
            String rwsDtlLineNum = (String) invtyTrxMap.get("RWS_DTL_LINE_NUM");
            String rcvSerTakeFlg = (String) invtyTrxMap.get("RCV_SER_TAKE_FLG");

            if (!ZYPCommonFunc.hasValue(serNum) && ZYPCommonFunc.hasValue(rwsNum) && ZYPCommonFunc.hasValue(rwsDtlLineNum) && ZYPConstant.FLG_ON_Y.equals(rcvSerTakeFlg)) {

                rcvSerInvtyTrxPkList.add((BigDecimal) invtyTrxMap.get("INVTY_TRX_PK"));
            }
        }

        Map<BigDecimal, List<String>> rcvSerListMap = new HashMap<BigDecimal, List<String>>();

        if (rcvSerInvtyTrxPkList != null && !rcvSerInvtyTrxPkList.isEmpty()) {

            S21SsmEZDResult rcvSerRslt = NLCL0280Query.getInstance().getRcvSerList(glblCmpyCd, rcvSerInvtyTrxPkList);

            if (rcvSerRslt.isCodeNormal()) {

                List<Map<String, Object>> rcvSerMapList = (ArrayList<Map<String, Object>>) rcvSerRslt.getResultObject();

                int serCnt = 0;
                BigDecimal preInvtyTrxPk = null;
                BigDecimal invtyTrxQty = null;

                HashSet<String> trxKeySet = new HashSet<String>();

                for (Map<String, Object> rcvSerMap : rcvSerMapList) {

                    BigDecimal invtyTrxPk = (BigDecimal) rcvSerMap.get("INVTY_TRX_PK");

                    String trxCd = (String) rcvSerMap.get("TRX_CD");
                    String trxRsnCd = (String) rcvSerMap.get("TRX_RSN_CD");
                    String rwsNum = (String) rcvSerMap.get("RWS_NUM");
                    String rwsLineNum = (String) rcvSerMap.get("RWS_LINE_NUM");
                    String stkStsCd = (String) rcvSerMap.get("INVTY_STK_STS_CD");
                    String serNum = (String) rcvSerMap.get("SER_NUM");

                    StringBuilder trxKeyTxt = new StringBuilder();
                    trxKeyTxt.append(trxCd);
                    trxKeyTxt.append(":");
                    trxKeyTxt.append(trxRsnCd);
                    trxKeyTxt.append(":");
                    trxKeyTxt.append(rwsNum);
                    trxKeyTxt.append(":");
                    trxKeyTxt.append(rwsLineNum);
                    trxKeyTxt.append(":");
                    trxKeyTxt.append(stkStsCd);
                    trxKeyTxt.append(":");
                    trxKeyTxt.append(serNum);

                    String trxKey = new String(trxKeyTxt);

                    if (preInvtyTrxPk == null || !preInvtyTrxPk.equals(invtyTrxPk)) {

                        invtyTrxQty = ((BigDecimal) rcvSerMap.get("INVTY_TRX_QTY")).abs();
                        serCnt = 0;
                    }

                    preInvtyTrxPk = invtyTrxPk;

                    if (invtyTrxQty.intValue() == serCnt || trxKeySet.contains(trxKey)) {

                        continue;
                    }

                    List<String> rcvSerList = rcvSerListMap.get(invtyTrxPk);

                    if (rcvSerList == null) {

                        rcvSerList = new ArrayList<String>();
                    }

                    rcvSerList.add(serNum);
                    rcvSerListMap.put(invtyTrxPk, rcvSerList);
                    trxKeySet.add(trxKey);
                    serCnt++;
                }
            }
        }

        return rcvSerListMap;
    }

    /**
     * setRsltToSMsg
     * @param sMsgALine NLCL0280_ASMsg
     * @param invtyTrxMap Map<String, Object>
     */
    private static void setRsltToSMsg(NLCL0280_ASMsg sMsgALine, Map<String, Object> invtyTrxMap) {

        ZYPEZDItemValueSetter.setValue(sMsgALine.rtlWhCd_A1, (String) invtyTrxMap.get("RTL_WH_CD"));
        ZYPEZDItemValueSetter.setValue(sMsgALine.rtlWhNm_A1, (String) invtyTrxMap.get("RTL_WH_NM"));
        ZYPEZDItemValueSetter.setValue(sMsgALine.rtlSwhCd_A1, (String) invtyTrxMap.get("RTL_SWH_CD"));
        ZYPEZDItemValueSetter.setValue(sMsgALine.mdseCd_A1, (String) invtyTrxMap.get("MDSE_CD"));
        ZYPEZDItemValueSetter.setValue(sMsgALine.mdseDescShortTxt_A1, (String) invtyTrxMap.get("MDSE_DESC_SHORT_TXT"));
        ZYPEZDItemValueSetter.setValue(sMsgALine.xxScrItem61Txt_PJ, ZYPCommonFunc.concatString((String) invtyTrxMap.get("COA_PROJ_CD"), ":", (String) invtyTrxMap.get("COA_PROJ_DESC_TXT")));
        ZYPEZDItemValueSetter.setValue(sMsgALine.xxScrItem61Txt_MD, ZYPCommonFunc.concatString((String) invtyTrxMap.get("MDSE_ITEM_TP_CD"), ":", (String) invtyTrxMap.get("MDSE_ITEM_TP_DESC_TXT")));
        ZYPEZDItemValueSetter.setValue(sMsgALine.xxScrItem61Txt_PD, ZYPCommonFunc.concatString((String) invtyTrxMap.get("COA_PROD_CD"), ":", (String) invtyTrxMap.get("COA_PROD_DESC_TXT")));
        ZYPEZDItemValueSetter.setValue(sMsgALine.xxRelnTrgtFlg_A1, (String) invtyTrxMap.get("ITEM_FLIP_FLG"));
        ZYPEZDItemValueSetter.setValue(sMsgALine.splyItemNum_A1, (String) invtyTrxMap.get("SPLY_ITEM_NUM"));
        ZYPEZDItemValueSetter.setValue(sMsgALine.mnfItemCd_A1, (String) invtyTrxMap.get("MNF_ITEM_CD"));
        ZYPEZDItemValueSetter.setValue(sMsgALine.svcConfigMstrPk_A1, (BigDecimal) invtyTrxMap.get("SVC_CONFIG_MSTR_PK"));
        ZYPEZDItemValueSetter.setValue(sMsgALine.t_MdlNm_A1, (String) invtyTrxMap.get("MDL_NM"));
        ZYPEZDItemValueSetter.setValue(sMsgALine.serNumFlg_A1, (String) invtyTrxMap.get("SER_TRX_FLG"));
        ZYPEZDItemValueSetter.setValue(sMsgALine.invtyTrxPk_A1, (BigDecimal) invtyTrxMap.get("INVTY_TRX_PK"));
        ZYPEZDItemValueSetter.setValue(sMsgALine.invtyTrxTpDescTxt_A1, (String) invtyTrxMap.get("INVTY_TRX_TP_DESC_TXT"));
        ZYPEZDItemValueSetter.setValue(sMsgALine.xxScrItem61Txt_AX, ZYPCommonFunc.concatString((String) invtyTrxMap.get("TRX_CD"), ":", (String) invtyTrxMap.get("TRX_DESC_TXT")));
        ZYPEZDItemValueSetter.setValue(sMsgALine.xxScrItem61Txt_AR, ZYPCommonFunc.concatString((String) invtyTrxMap.get("TRX_RSN_CD"), ":", (String) invtyTrxMap.get("TRX_RSN_DESC_TXT")));
        ZYPEZDItemValueSetter.setValue(sMsgALine.invtyTrxQty_A1, (BigDecimal) invtyTrxMap.get("INVTY_TRX_QTY"));
        ZYPEZDItemValueSetter.setValue(sMsgALine.xxScrItem61Txt_SS, ZYPCommonFunc.concatString((String) invtyTrxMap.get("STK_STS_CD"), ":", (String) invtyTrxMap.get("STK_STS_DESC_TXT")));
        ZYPEZDItemValueSetter.setValue(sMsgALine.xxScrItem61Txt_AS, ZYPCommonFunc.concatString((String) invtyTrxMap.get("LOC_STS_CD"), ":", (String) invtyTrxMap.get("LOC_STS_DESC_TXT")));
        ZYPEZDItemValueSetter.setValue(sMsgALine.invtyTrxSlpNum_A1, (String) invtyTrxMap.get("SRC_ORD_NUM"));
        ZYPEZDItemValueSetter.setValue(sMsgALine.dplyLineNum_A1, (String) invtyTrxMap.get("SRC_ORD_LINE_NUM"));
        ZYPEZDItemValueSetter.setValue(sMsgALine.soNum_A1, (String) invtyTrxMap.get("SO_NUM"));
        ZYPEZDItemValueSetter.setValue(sMsgALine.soSlpNum_A1, (String) invtyTrxMap.get("SO_SLP_NUM"));
        ZYPEZDItemValueSetter.setValue(sMsgALine.rwsNum_A1, (String) invtyTrxMap.get("RWS_NUM"));
        ZYPEZDItemValueSetter.setValue(sMsgALine.rwsDtlLineNum_A1, (String) invtyTrxMap.get("RWS_DTL_LINE_NUM"));
        ZYPEZDItemValueSetter.setValue(sMsgALine.dsAcctNm_A1, (String) invtyTrxMap.get("PARTY_NM"));
        ZYPEZDItemValueSetter.setValue(sMsgALine.xxTsDsp19Txt_A1, (String) invtyTrxMap.get("EZINTIME"));
        ZYPEZDItemValueSetter.setValue(sMsgALine.shipToLocCustNm_A1, (String) invtyTrxMap.get("SHIP_TO_LOC_CUST_NM"));
        ZYPEZDItemValueSetter.setValue(sMsgALine.ajeLinkFlg_A1, (String) invtyTrxMap.get("AJE_LINK_FLG"));
        // START 2023/04/07 G.Quan [QC#61206, ADD]
        ZYPEZDItemValueSetter.setValue(sMsgALine.xxRecHistCratTs_A1, (String) invtyTrxMap.get("XX_REC_HIST_CRAT_TS"));
        ZYPEZDItemValueSetter.setValue(sMsgALine.xxRecHistCratByNm_A1, ZYPRecHistUtil.getFullNameForRecHist((String) invtyTrxMap.get("XX_REC_HIST_CRAT_BY_NM")));
        ZYPEZDItemValueSetter.setValue(sMsgALine.xxRecHistUpdTs_A1, (String) invtyTrxMap.get("XX_REC_HIST_UPD_TS"));
        ZYPEZDItemValueSetter.setValue(sMsgALine.xxRecHistUpdByNm_A1, ZYPRecHistUtil.getFullNameForRecHist((String) invtyTrxMap.get("XX_REC_HIST_UPD_BY_NM")));
        ZYPEZDItemValueSetter.setValue(sMsgALine.xxRecHistTblNm_A1, (String) invtyTrxMap.get("XX_REC_HIST_TBL_NM"));
        // END 2023/04/07 G.Quan [QC#61206, ADD]

        if (ZYPCommonFunc.hasValue(sMsgALine.xxTsDsp19Txt_A1)) {

            String dispTs = ZYPDateUtil.formatEzd17ToDisp(sMsgALine.xxTsDsp19Txt_A1.getValue()).substring(0, 19);
            ZYPEZDItemValueSetter.setValue(sMsgALine.xxTsDsp19Txt_A1, dispTs);
        }
    }

    /**
     * check duplicate search name
     * @param cMsg NPAL1220CMsg
     * @return boolean
     */
    public static boolean isExistSaveSearchName(NLCL0280CMsg cMsg) {

        if (!ZYPCommonFunc.hasValue(cMsg.srchOptNm_H1)) {

            return false;
        }

        for (int i = 0; i < cMsg.srchOptNm_PD.length(); i++) {

            if (!ZYPCommonFunc.hasValue(cMsg.srchOptNm_PD.no(i))) {

                return false;
            }

            if (cMsg.srchOptNm_H1.getValue().equals(cMsg.srchOptNm_PD.no(i).getValue())) {

                if (ZYPCommonFunc.hasValue(cMsg.srchOptPk_PS) && cMsg.srchOptPk_PS.getValue().compareTo(cMsg.srchOptPk_PD.no(i).getValue()) == 0) {

                    return false;
                }

                return true;
            }
        }

        return false;
    }

    /**
     * Call API NSZC033001 (Save SearchOption)
     * @param cMsg NLBL2020CMsg
     * @param sMsg NLBL2020SMsg
     * @param usrId String
     * @param glblCmpyCd String
     */
    public static void callNszc0330forSaveSearchOption(NLCL0280CMsg cMsg, NLCL0280SMsg sMsg, String usrId, String glblCmpyCd) {

        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        pMsg.clear();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_INSERT_UPDATE);

        if (!ZYPCommonFunc.hasValue(cMsg.srchOptNm_H1) || isSameSaveSearchName(cMsg)) {

            ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, cMsg.srchOptPk_PS.getValue());
        }

        if (ZYPCommonFunc.hasValue(cMsg.srchOptNm_H1)) {

            ZYPEZDItemValueSetter.setValue(pMsg.srchOptNm, cMsg.srchOptNm_H1.getValue());

        } else {

            setSelectSaveSearchName(pMsg, cMsg);
        }

        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, NLCL0280Constant.BUSINESS_ID);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptUsrId, usrId);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_01, cMsg.trxDt_H1.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_02, cMsg.trxDt_H2.getValue());

        if (ZYPCommonFunc.hasValue(cMsg.invtyTrxPk_H1)) {

            ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_03, cMsg.invtyTrxPk_H1.getValue().toPlainString());
        }

        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_04, cMsg.invtyTrxTpCd_PS.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_05, cMsg.trxCd_PS.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_06, cMsg.trxRsnCd_PS.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_07, cMsg.invtyTrxSlpNum_H1.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_08, cMsg.rwsNum_H1.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_09, cMsg.soNum_H1.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_10, cMsg.mdseCd_H1.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_11, cMsg.mdseDescShortTxt_H1.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_12, cMsg.mdseItemTpCd_PS.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_13, cMsg.coaProjCd_PS.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_14, cMsg.coaProdCd_H1.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_15, cMsg.mdseItemRelnTpCd_PS.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_16, cMsg.relnMdseCd_H1.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_17, cMsg.serNum_H1.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_18, cMsg.splyItemNum_H1.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_19, cMsg.mnfItemCd_H1.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_20, cMsg.rtlWhCd_H1.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_21, cMsg.rtlSwhCd_H1.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_22, cMsg.vndCd_H1.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_23, cMsg.shipFromLocCustCd_H1.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_24, cMsg.shipToLocCustCd_H1.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_25, cMsg.srchOptTxt_LS.getValue());

        if (executeNszc0330(cMsg, pMsg)) {

            setPulldownSaveSearch(cMsg, glblCmpyCd, usrId);
            ZYPEZDItemValueSetter.setValue(cMsg.srchOptPk_PS, pMsg.srchOptPk.getValue());
            // Message ; The process [@] has been successfully completed.
            cMsg.setMessageInfo(NLCL0280Constant.ZZZM9003I, new String[] {"Save Search" });
        }
    }

    /**
     * Call API NSZC033001 (Delete SearchOption)
     * @param cMsg NLBL2020CMsg
     * @param sMsg NLBL2020SMsg
     * @param usrId String
     * @param glblCmpyCd String
     */
    public static void callNszc0330forDeleteSearchOption(NLCL0280CMsg cMsg, NLCL0280SMsg sMsg, String usrId, String glblCmpyCd) {

        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        pMsg.clear();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_DELETE);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, cMsg.srchOptPk_PS.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, NLCL0280Constant.BUSINESS_ID);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptUsrId, usrId);

        if (executeNszc0330(cMsg, pMsg)) {

            setPulldownSaveSearch(cMsg, glblCmpyCd, usrId);
            cMsg.srchOptNm_H1.clear();
            cMsg.srchOptPk_PS.clear();
            cMsg.setMessageInfo(NLCL0280Constant.ZZZM9003I, new String[] {"Delete Search" });
        }
    }

    /**
     * Call API NSZC033001 (Search SearchOption)
     * @param cMsg NLBL2020CMsg
     * @param sMsg NLBL2020SMsg
     * @param usrId String
     * @param glblCmpyCd String
     */
    public static void callNszc0330forSearchSearchOption(NLCL0280CMsg cMsg, NLCL0280SMsg sMsg, String usrId, String glblCmpyCd) {

        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        pMsg.clear();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_SEARCH);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, cMsg.srchOptPk_PS.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, NLCL0280Constant.BUSINESS_ID);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptUsrId, usrId);

        if (!executeNszc0330(cMsg, pMsg)) {

            return; // Error
        }

        // Set Result
        ZYPEZDItemValueSetter.setValue(cMsg.srchOptNm_H1, pMsg.srchOptNm.getValue());

        if (ZYPCommonFunc.hasValue(pMsg.srchOptTxt_01)) {

            ZYPEZDItemValueSetter.setValue(cMsg.trxDt_H1, pMsg.srchOptTxt_01.getValue());

        } else {

            cMsg.trxDt_H1.clear();
        }

        if (ZYPCommonFunc.hasValue(pMsg.srchOptTxt_02)) {

            ZYPEZDItemValueSetter.setValue(cMsg.trxDt_H2, pMsg.srchOptTxt_02.getValue());

        } else {

            cMsg.trxDt_H2.clear();
        }

        if (ZYPCommonFunc.hasValue(pMsg.srchOptTxt_03)) {

            ZYPEZDItemValueSetter.setValue(cMsg.invtyTrxPk_H1, new BigDecimal(pMsg.srchOptTxt_03.getValue()));

        } else {

            cMsg.invtyTrxPk_H1.clear();
        }

        ZYPEZDItemValueSetter.setValue(cMsg.invtyTrxTpCd_PS, pMsg.srchOptTxt_04.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.trxCd_PS, pMsg.srchOptTxt_05.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.trxRsnCd_PS, pMsg.srchOptTxt_06.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.invtyTrxSlpNum_H1, pMsg.srchOptTxt_07.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.rwsNum_H1, pMsg.srchOptTxt_08.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.soNum_H1, pMsg.srchOptTxt_09.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.mdseCd_H1, pMsg.srchOptTxt_10.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.mdseDescShortTxt_H1, pMsg.srchOptTxt_11.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.mdseItemTpCd_PS, pMsg.srchOptTxt_12.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.coaProjCd_PS, pMsg.srchOptTxt_13.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.coaProdCd_H1, pMsg.srchOptTxt_14.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.mdseItemRelnTpCd_PS, pMsg.srchOptTxt_15.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.relnMdseCd_H1, pMsg.srchOptTxt_16.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.serNum_H1, pMsg.srchOptTxt_17.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.splyItemNum_H1, pMsg.srchOptTxt_18.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.mnfItemCd_H1, pMsg.srchOptTxt_19.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.rtlWhCd_H1, pMsg.srchOptTxt_20.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.rtlSwhCd_H1, pMsg.srchOptTxt_21.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.vndCd_H1, pMsg.srchOptTxt_22.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.shipFromLocCustCd_H1, pMsg.srchOptTxt_23.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.shipToLocCustCd_H1, pMsg.srchOptTxt_24.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.srchOptTxt_LS, pMsg.srchOptTxt_25.getValue());
        cMsg.coaProdDescTxt_H1.clear();
        cMsg.rtlWhNm_H1.clear();
        cMsg.rtlSwhNm_H1.clear();
        cMsg.locNm_H1.clear();
        cMsg.dsAcctNm_H1.clear();
        cMsg.dsAcctNm_H2.clear();

        // Check Location Status
        for (int i = 0; i < cMsg.L.getValidCount(); i++) {

            ZYPEZDItemValueSetter.setValue(cMsg.L.no(i).xxChkBox_LS, ZYPConstant.FLG_OFF_N);
        }

        if (ZYPCommonFunc.hasValue(cMsg.srchOptTxt_LS)) {

            String locStsTxt = cMsg.srchOptTxt_LS.getValue();

            if (locStsTxt.indexOf(",") != -1) {

                String[] locStsArray = locStsTxt.split(",");

                for (int i = 0; i < locStsArray.length; i++) {

                    if (!locStsArray[i].trim().equals("") && locStsArray[i].length() > 0) {

                        for (int j = 0; j < cMsg.L.getValidCount(); j++) {

                            if (locStsArray[i].trim().equals(cMsg.L.no(j).locStsCd_LS.getValue())) {

                                ZYPEZDItemValueSetter.setValue(cMsg.L.no(j).xxChkBox_LS, ZYPConstant.CHKBOX_ON_Y);
                            }
                        }
                    }
                }

            } else {

                if (!locStsTxt.trim().equals("")) {

                    for (int i = 0; i < cMsg.L.getValidCount(); i++) {

                        if (locStsTxt.trim().equals(cMsg.L.no(i).locStsCd_LS.getValue())) {

                            ZYPEZDItemValueSetter.setValue(cMsg.L.no(i).xxChkBox_LS, ZYPConstant.CHKBOX_ON_Y);
                        }
                    }
                }
            }
        }
    }

    /**
     * isSameSaveSearchName
     * @param cMsg
     * @return boolean
     */
    private static boolean isSameSaveSearchName(NLCL0280CMsg cMsg) {

        if (!ZYPCommonFunc.hasValue(cMsg.srchOptPk_PS)) {

            return false;
        }

        if (!ZYPCommonFunc.hasValue(cMsg.srchOptNm_H1)) {

            return false;
        }

        for (int i = 0; i < cMsg.srchOptNm_PD.length(); i++) {

            if (!ZYPCommonFunc.hasValue(cMsg.srchOptNm_PD.no(i))) {

                return false;
            }

            if (cMsg.srchOptPk_PS.getValue().compareTo(cMsg.srchOptPk_PD.no(i).getValue()) == 0) {

                if (cMsg.srchOptNm_H1.getValue().equals(cMsg.srchOptNm_PD.no(i).getValue())) {

                    return true;
                }

                return false;
            }
        }

        return false;
    }

    /**
     * setSelectSaveSearchName
     * @param pMsg NSZC033001PMsg
     * @param cMsg NLBL2020CMsg
     */
    public static void setSelectSaveSearchName(NSZC033001PMsg pMsg, NLCL0280CMsg cMsg) {

        if (!ZYPCommonFunc.hasValue(cMsg.srchOptPk_PS)) {

            return;
        }

        for (int i = 0; i < cMsg.srchOptNm_PD.length(); i++) {

            if (!ZYPCommonFunc.hasValue(cMsg.srchOptNm_PD.no(i))) {

                return;
            }

            if (cMsg.srchOptPk_PS.getValue().compareTo(cMsg.srchOptPk_PD.no(i).getValue()) == 0) {

                ZYPEZDItemValueSetter.setValue(pMsg.srchOptNm, cMsg.srchOptNm_PD.no(i));
            }
        }

        return;
    }

    /**
     * Execute API NSZC033001
     * @param bizMsg NLBL2020CMsg
     * @param pMsg NSZC033001PMsg
     * @return
     */
    private static boolean executeNszc0330(NLCL0280CMsg cMsg, NSZC033001PMsg pMsg) {

        // Search Option API(NSZC0330) is executed
        NSZC033001 api = new NSZC033001();
        api.execute(pMsg, ONBATCH_TYPE.ONLINE);

        if (!S21ApiUtil.getXxMsgIdList(pMsg).isEmpty()) {

            for (int j = 0; j < pMsg.xxMsgIdList.length(); j++) {

                if (ZYPCommonFunc.hasValue(pMsg.xxMsgIdList.no(j).xxMsgId)) {

                    String msgId = pMsg.xxMsgIdList.no(j).xxMsgId.getValue();
                    cMsg.setMessageInfo(msgId);

                    if (msgId.endsWith("E")) {

                        cMsg.srchOptPk_PS.setErrorInfo(1, msgId);
                        cMsg.srchOptNm_H1.setErrorInfo(1, msgId);
                        return false;
                    }
                }
            }
        }

        return true;
    }

    /**
     * chkInitParam
     * @param cMsg NLCL0280CMsg
     * @return boolean
     */
    public static boolean chkInitParam(NLCL0280CMsg cMsg) {

        if (ZYPCommonFunc.hasValue(cMsg.trxDt_H1)) {

            return true;

        } else if (ZYPCommonFunc.hasValue(cMsg.trxDt_H2)) {

            return true;

        } else if (ZYPCommonFunc.hasValue(cMsg.invtyTrxPk_H1)) {

            return true;

        } else if (ZYPCommonFunc.hasValue(cMsg.invtyTrxTpCd_PS)) {

            return true;

        } else if (ZYPCommonFunc.hasValue(cMsg.trxCd_PS)) {

            return true;

        } else if (ZYPCommonFunc.hasValue(cMsg.trxRsnCd_PS)) {

            return true;

        } else if (ZYPCommonFunc.hasValue(cMsg.invtyTrxSlpNum_H1)) {

            return true;

        } else if (ZYPCommonFunc.hasValue(cMsg.rwsNum_H1)) {

            return true;

        } else if (ZYPCommonFunc.hasValue(cMsg.soNum_H1)) {

            return true;

        } else if (ZYPCommonFunc.hasValue(cMsg.mdseCd_H1)) {

            return true;

        } else if (ZYPCommonFunc.hasValue(cMsg.mdseDescShortTxt_H1)) {

            return true;

        } else if (ZYPCommonFunc.hasValue(cMsg.mdseItemTpCd_PS)) {

            return true;

        } else if (ZYPCommonFunc.hasValue(cMsg.coaProjCd_PS)) {

            return true;

        } else if (ZYPCommonFunc.hasValue(cMsg.coaProdCd_H1)) {

            return true;

        } else if (ZYPCommonFunc.hasValue(cMsg.mdseItemRelnTpCd_PS)) {

            return true;

        } else if (ZYPCommonFunc.hasValue(cMsg.relnMdseCd_H1)) {

            return true;

        } else if (ZYPCommonFunc.hasValue(cMsg.serNum_H1)) {

            return true;

        } else if (ZYPCommonFunc.hasValue(cMsg.splyItemNum_H1)) {

            return true;

        } else if (ZYPCommonFunc.hasValue(cMsg.mnfItemCd_H1)) {

            return true;

        } else if (ZYPCommonFunc.hasValue(cMsg.rtlWhCd_H1)) {

            return true;

        } else if (ZYPCommonFunc.hasValue(cMsg.rtlSwhCd_H1)) {

            return true;

        } else if (ZYPCommonFunc.hasValue(cMsg.vndCd_H1)) {

            return true;

        } else if (ZYPCommonFunc.hasValue(cMsg.shipFromLocCustCd_H1)) {

            return true;

        } else if (ZYPCommonFunc.hasValue(cMsg.shipToLocCustCd_H1)) {

            return true;
        }

        return false;
    }
}
