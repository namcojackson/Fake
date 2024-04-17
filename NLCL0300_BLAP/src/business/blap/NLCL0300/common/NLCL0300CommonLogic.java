/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2010/07/13   Fujitsu         N.Yamamoto      Create          4486
 *</pre>
 */
package business.blap.NLCL0300.common;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.blap.NLCL0300.NLCL0300CMsg;
import business.blap.NLCL0300.NLCL0300Query;
import business.db.INVTYTMsg;
import business.db.RTL_SWHTMsg;
import business.db.RTL_WHTMsg;
import business.db.RWS_DTLTMsg;
import business.db.RWS_DTLTMsgArray;
import business.db.RWS_HDRTMsg;
import business.db.RWS_SERTMsg;
import business.db.RWS_SERTMsgArray;
import business.db.SHPG_ORDTMsg;
import business.db.SHPG_ORD_DTLTMsg;
import business.db.SHPG_ORD_DTLTMsgArray;
import business.parts.NLXC023001PMsg;
import business.parts.NLZC003001PMsg;
import business.parts.NLZC200001PMsg;
import business.parts.NLZC205001PMsg;
import business.parts.NLZC206001PMsg;
import business.parts.NLZC207001PMsg;
import business.parts.NLZC210001PMsg;
import business.parts.NLZC302001PMsg;
import business.parts.NLZC304001PMsg;
import business.parts.NSZC048001PMsg;
import business.parts.NWZC003001PMsg;
import business.parts.NWZC107001PMsg;

import com.canon.cusa.s21.api.NLZ.NLZC003001.NLZC003001;
import com.canon.cusa.s21.api.NLZ.NLZC205001.NLZC205001;
import com.canon.cusa.s21.api.NSZ.NSZC048001.NSZC048001;
import com.canon.cusa.s21.api.NWZ.NWZC003001.NWZC003001;
import com.canon.cusa.s21.api.NWZ.NWZC107001.NWZC107001;
import com.canon.cusa.s21.common.NLB.NLBC001001.SCE_ORD_TP;
import com.canon.cusa.s21.common.NLC.NLCC001001.TRX_RSN;
import com.canon.cusa.s21.common.NLX.NLXC023001.NLXC023001;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INBD_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INVTY_ORD_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INVTY_ORD_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_HDR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PUT_AWAY_CHK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SER_TRX_EVENT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SO_PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SYS_SRC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX_SRC_TP;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/17   CSAI            K.Lee           Create          N/A
 * 2019/08/13   CITS            M.Naito         Update          QC#52185
 * 2019/11/26   CITS            T.Wada          Update          QC#54380
 * 2022/02/15   CITS            A.Marte         Update          QC#59705
 *</pre>
 */

public class NLCL0300CommonLogic {

    /** S21UserProfileService Instance */
    public static final S21UserProfileService PROFILE_SERVICE = S21UserProfileServiceFactory.getInstance().getService();

    /** Global Company Code */
    public static final String GLBL_CMPY_CD = PROFILE_SERVICE.getGlobalCompanyCode();

    public static NLCL0300Query getQuery() {
        return NLCL0300Query.getInstance();
    }

    public static String getRtlWhNm(String glblCmpyCd, String rtlWhCd) {
        if (!ZYPCommonFunc.hasValue(rtlWhCd)) {
            return null;
        }
        RTL_WHTMsg inMsg = new RTL_WHTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.rtlWhCd, rtlWhCd);
        RTL_WHTMsg outMsg = (RTL_WHTMsg) EZDTBLAccessor.findByKey(inMsg);
        if (outMsg != null) {
            return outMsg.rtlWhNm.getValue();
        }
        return null;
    }

    /**
     * setPulldownStkStsList
     * @param cMsg NLCL0300CMsg
     */
    public static void setPulldownStkStsList(String glblCmpyCd, NLCL0300CMsg bizMsg) {

        bizMsg.stkStsCd_LC.clear();
        bizMsg.stkStsDescTxt_LD.clear();

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("bizAppId", "NLCL0300");

        // Execute
        S21SsmEZDResult result = NLCL0300Query.getInstance().getStkStsList(param);

        if (result.isCodeNormal()) {

            List<Map<String, Object>> resultMap = (List<Map<String, Object>>) result.getResultObject();

            for (int i = 0; i < resultMap.size(); i++) {

                ZYPEZDItemValueSetter.setValue(bizMsg.stkStsCd_LC.no(i), (String) resultMap.get(i).get("STK_STS_CD"));
                ZYPEZDItemValueSetter.setValue(bizMsg.stkStsDescTxt_LD.no(i), (String) resultMap.get(i).get("STK_STS_DESC_TXT"));
            }
        }
    }

    /**
     * setPulldownRtlSwhList
     * @param cMsg NLCL0300CMsg
     */
    public static void setPulldownRtlSwhList(String glblCmpyCd, NLCL0300CMsg bizMsg) {

        bizMsg.rtlSwhCd_LC.clear();
        bizMsg.rtlSwhCd_LD.clear();

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("rtlWhCd", bizMsg.rtlWhCd_HL.getValue());
        param.put("slsDt", ZYPDateUtil.getSalesDate(glblCmpyCd));

        // Execute
        S21SsmEZDResult result = NLCL0300Query.getInstance().getRtlSwhList(param);

        if (result.isCodeNormal()) {
            List<Map<String, Object>> resultMap = (List<Map<String, Object>>) result.getResultObject();
            for (int i = 0; i < resultMap.size(); i++) {
                ZYPEZDItemValueSetter.setValue(bizMsg.rtlSwhCd_LC.no(i), (String) resultMap.get(i).get("RTL_SWH_CD"));
                ZYPEZDItemValueSetter.setValue(bizMsg.rtlSwhCd_LD.no(i), (String) resultMap.get(i).get("RTL_SWH_CD"));
            }
        }
    }

    /**
     * setPulldownAdjTrxTypeList
     * @param cMsg NLCL0300CMsg
     */
    public static Map<String, Object> getRtlWhMap(String glblCmpyCd, String rltWhCd, String rtlWhNm) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        if (ZYPCommonFunc.hasValue(rltWhCd)) {
            param.put("rtlWhCd", rltWhCd);
        } else if (ZYPCommonFunc.hasValue(rtlWhNm)) {
            param.put("rtlWhNm", rtlWhNm);
        }
        param.put("slsDt", ZYPDateUtil.getSalesDate(glblCmpyCd));

        // Execute
        S21SsmEZDResult result = NLCL0300Query.getInstance().getRtlWhMap(param);

        Map<String, Object> resultMap = null;
        if (result.isCodeNormal()) {
            resultMap = (Map<String, Object>) result.getResultObject();
        }
        return resultMap;
    }

    /**
     * setPulldownAdjTrxTypeList
     * @param cMsg NLCL0300CMsg
     */
    public static RTL_SWHTMsg getRTL_SWHTMsg(String glblCmpyCd, String rtlWhCd, String rtlSwhCd) {

        RTL_SWHTMsg inMsg = new RTL_SWHTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.rtlWhCd, rtlWhCd);
        ZYPEZDItemValueSetter.setValue(inMsg.rtlSwhCd, rtlSwhCd);
        RTL_SWHTMsg outMsg = (RTL_SWHTMsg) S21CacheTBLAccessor.findByKey(inMsg);
        return outMsg;
    }

    /**
     * setPulldownAdjTrxTypeList
     * @param cMsg NLCL0300CMsg
     */
    public static List<Map<String, Object>> getModelConfigList(String glblCmpyCd, String mdlDescTxt) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("mdlDescTxt", mdlDescTxt);
        param.put("slsDt", ZYPDateUtil.getSalesDate(glblCmpyCd));

        // Execute
        S21SsmEZDResult result = NLCL0300Query.getInstance().getModelConfigList(param);

        List<Map<String, Object>> resultList = null;
        if (result.isCodeNormal()) {
            resultList = (List<Map<String, Object>>) result.getResultObject();
        }
        return resultList;
    }

    /**
     * setPulldownAdjTrxTypeList
     * @param cMsg NLCL0300CMsg
     */
    public static List<Map<String, Object>> getConfigList(String glblCmpyCd, BigDecimal svcConfigMstrPk) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("svcConfigMstrPk", svcConfigMstrPk);

        // Execute
        S21SsmEZDResult result = NLCL0300Query.getInstance().getConfigList(param);

        List<Map<String, Object>> resultList = null;
        if (result.isCodeNormal()) {
            resultList = (List<Map<String, Object>>) result.getResultObject();
        }
        return resultList;
    }

    /**
     * setPulldownAdjTrxTypeList
     * @param cMsg NLCL0300CMsg
     */
    public static Map<String, Object> getMdseMap(String glblCmpyCd, String mdseCd) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("mdseCd", mdseCd);

        // Execute
        S21SsmEZDResult result = NLCL0300Query.getInstance().getMdseMap(param);

        Map<String, Object> resultMap = null;
        if (result.isCodeNormal()) {
            resultMap = (Map<String, Object>) result.getResultObject();
        }
        return resultMap;
    }

    /**
     * setPulldownAdjTrxTypeList
     * @param cMsg NLCL0300CMsg
     */
    public static Map<String, Object> getIbMap(String glblCmpyCd, String mdseCd, String serNum) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("mdseCd", mdseCd);
        param.put("serNum", serNum);

        // Execute
        S21SsmEZDResult result = NLCL0300Query.getInstance().getIbMap(param);

        Map<String, Object> resultMap = null;
        if (result.isCodeNormal()) {
            resultMap = (Map<String, Object>) result.getResultObject();
        }
        return resultMap;

    }

    /**
     * setPulldownAdjTrxTypeList
     * @param cMsg NLCL0300CMsg
     */
    public static String getLocStsCd(String glblCmpyCd, String locStsDescTxt) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("locStsDescTxt", locStsDescTxt);

        // Execute
        S21SsmEZDResult result = NLCL0300Query.getInstance().getLocStsCd(param);

        String locStsCd = null;
        if (result.isCodeNormal()) {
            locStsCd = (String) result.getResultObject();
        }
        return locStsCd;
    }

    /**
     * setPulldownAdjTrxTypeList
     * @param cMsg NLCL0300CMsg
     */
    public static String getStkStsCd(String glblCmpyCd, String stkStsDescTxt) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("stkStsDescTxt", stkStsDescTxt);

        // Execute
        S21SsmEZDResult result = NLCL0300Query.getInstance().getStkStsCd(param);

        String stkStsCd = null;
        if (result.isCodeNormal()) {
            stkStsCd = (String) result.getResultObject();
        }
        return stkStsCd;
    }

    /**
     * setPulldownAdjTrxTypeList
     * @param cMsg NLCL0300CMsg
     */
    public static INVTYTMsg getInvtyTMsg(String glblCmpyCd, String invtyLocCd, String mdseCd, String stkStsCd, String locStsCd) {

        INVTYTMsg inMsg = new INVTYTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.invtyLocCd, invtyLocCd);
        ZYPEZDItemValueSetter.setValue(inMsg.mdseCd, mdseCd);
        ZYPEZDItemValueSetter.setValue(inMsg.stkStsCd, stkStsCd);
        ZYPEZDItemValueSetter.setValue(inMsg.locStsCd, locStsCd);
        INVTYTMsg outMsg = (INVTYTMsg) EZDTBLAccessor.findByKey(inMsg);
        return outMsg;
    }

    public static NLZC003001PMsg getInventoryOrderApiPMsgHeader (String glblCmpyCd, NLCL0300CMsg bizMsg) {
        NLZC003001PMsg hdrPMsg = new NLZC003001PMsg();
        ZYPEZDItemValueSetter.setValue(hdrPMsg.xxProcTpCd, NLZC003001.PROC_TP_CRAT);
        ZYPEZDItemValueSetter.setValue(hdrPMsg.xxDtTpCd, NLZC003001.DT_TP_HDR);
        ZYPEZDItemValueSetter.setValue(hdrPMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(hdrPMsg.invtyOrdTpCd, INVTY_ORD_TP.CONFIGURATION_CHANGE);
        ZYPEZDItemValueSetter.setValue(hdrPMsg.invtyLocCd, bizMsg.rtlWhCd_H);
        ZYPEZDItemValueSetter.setValue(hdrPMsg.locStsCd, LOC_STS.DC_STOCK);
        ZYPEZDItemValueSetter.setValue(hdrPMsg.trxCd, TRX.MOVEMENT);
        ZYPEZDItemValueSetter.setValue(hdrPMsg.trxRsnCd, TRX_RSN.CONFIG_CHANGE_STOCK_OUT);
        ZYPEZDItemValueSetter.setValue(hdrPMsg.trxSrcTpCd, TRX_SRC_TP.MOVEMENT);
        ZYPEZDItemValueSetter.setValue(hdrPMsg.sysSrcCd, SYS_SRC.S21_LOGISTICS);
        ZYPEZDItemValueSetter.setValue(hdrPMsg.invtyOrdStsCd, INVTY_ORD_STS.APPROVED);
        ZYPEZDItemValueSetter.setValue(hdrPMsg.firstInvtyOrdCmntTxt, bizMsg.firstInvtyOrdCmntTxt_H);
        ZYPEZDItemValueSetter.setValue(hdrPMsg.scdInvtyOrdCmntTxt, bizMsg.scdInvtyOrdCmntTxt_H);
        ZYPEZDItemValueSetter.setValue(hdrPMsg.thirdInvtyOrdCmntTxt, bizMsg.thirdInvtyOrdCmntTxt_H);
        ZYPEZDItemValueSetter.setValue(hdrPMsg.svcConfigMstrPk, bizMsg.svcConfigMstrPk_HL);
        ZYPEZDItemValueSetter.setValue(hdrPMsg.mdlId, bizMsg.mdlId_H);
        return hdrPMsg;
    }
// QC#54380 Mod Start
//    public static List<NLZC003001PMsg> getInventoryOrderApiPMsgDetailList(String glblCmpyCd, NLCL0300CMsg bizMsg, NLZC003001PMsg hdrPMsg, NLCL0300CMsg bizMsg2) {
//        List<NLZC003001PMsg> list = new ArrayList<NLZC003001PMsg>();
//
//        boolean flg = false;
//        if (bizMsg2.A.length() > 0) {
//        	 flg = true;
//        }
//        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
//            NLZC003001PMsg dtlPMsg = new NLZC003001PMsg();
//
//            ZYPEZDItemValueSetter.setValue(bizMsg2.A.no(i).invtyOrdNum_T, hdrPMsg.invtyOrdNum);
//            ZYPEZDItemValueSetter.setValue(bizMsg2.A.no(i).invtyOrdLineNum_T, ZYPCommonFunc.leftPad(String.valueOf(i + 1), 3, "0"));
//
//            ZYPEZDItemValueSetter.setValue(dtlPMsg.xxProcTpCd, NLZC003001.PROC_TP_CRAT);
//            ZYPEZDItemValueSetter.setValue(dtlPMsg.xxDtTpCd, NLZC003001.DT_TP_DTL);
//            ZYPEZDItemValueSetter.setValue(dtlPMsg.glblCmpyCd, glblCmpyCd);
//            ZYPEZDItemValueSetter.setValue(dtlPMsg.invtyOrdNum, bizMsg2.A.no(i).invtyOrdNum_T);
//            ZYPEZDItemValueSetter.setValue(dtlPMsg.invtyOrdTpCd, INVTY_ORD_TP.CONFIGURATION_CHANGE);
//            ZYPEZDItemValueSetter.setValue(dtlPMsg.invtyLocCd, bizMsg2.A.no(i).invtyLocCd_A);
//            ZYPEZDItemValueSetter.setValue(dtlPMsg.invtyLocCd_D1, bizMsg2.A.no(i).invtyLocCd_A);
//            ZYPEZDItemValueSetter.setValue(dtlPMsg.locStsCd, LOC_STS.DC_STOCK);
//            ZYPEZDItemValueSetter.setValue(dtlPMsg.locStsCd_D1, LOC_STS.DC_STOCK);
//            ZYPEZDItemValueSetter.setValue(hdrPMsg.trxCd, hdrPMsg.trxCd);
//            ZYPEZDItemValueSetter.setValue(hdrPMsg.trxRsnCd, hdrPMsg.trxRsnCd);
//            ZYPEZDItemValueSetter.setValue(dtlPMsg.trxSrcTpCd, TRX_SRC_TP.MOVEMENT);
//            ZYPEZDItemValueSetter.setValue(dtlPMsg.sysSrcCd, SYS_SRC.S21_LOGISTICS);
//            ZYPEZDItemValueSetter.setValue(dtlPMsg.invtyOrdLineNum, bizMsg2.A.no(i).invtyOrdLineNum_T);
//            ZYPEZDItemValueSetter.setValue(dtlPMsg.mdseCd, bizMsg2.A.no(i).mdseCd_A);
//            ZYPEZDItemValueSetter.setValue(dtlPMsg.stkStsCd, bizMsg2.A.no(i).stkStsCd_A);
//            ZYPEZDItemValueSetter.setValue(dtlPMsg.ordQty, bizMsg2.A.no(i).ordQty_A);
//            ZYPEZDItemValueSetter.setValue(dtlPMsg.invtyOrdLineCostAmt, bizMsg2.A.no(i).invtyOrdLineCostAmt_A);
//            ZYPEZDItemValueSetter.setValue(dtlPMsg.rmvConfigFlg, bizMsg2.A.no(i).rmvConfigFlg_A);
//            ZYPEZDItemValueSetter.setValue(dtlPMsg.invtyOrdDtlStsCd, INVTY_ORD_STS.APPROVED);
//            ZYPEZDItemValueSetter.setValue(dtlPMsg.svcConfigMstrPk, bizMsg2.A.no(i).svcConfigMstrPk_A);
//            if (ZYPCommonFunc.hasValue(bizMsg2.A.no(i).serNum_A)) {
//                ZYPEZDItemValueSetter.setValue(dtlPMsg.serialInfoList.no(0).invtyOrdNum, bizMsg2.A.no(i).invtyOrdNum_T);
//                ZYPEZDItemValueSetter.setValue(dtlPMsg.serialInfoList.no(0).invtyOrdLineNum, bizMsg2.A.no(i).invtyOrdLineNum_T);
//                ZYPEZDItemValueSetter.setValue(dtlPMsg.serialInfoList.no(0).serNum, bizMsg2.A.no(i).serNum_A);
//                dtlPMsg.serialInfoList.setValidCount(1);
//            }
//
//            list.add(dtlPMsg);
//        }
//        return list;
//    }
    public static List<NLZC003001PMsg> getInventoryOrderApiPMsgDetailList(String glblCmpyCd, NLCL0300CMsg bizMsg, NLZC003001PMsg hdrPMsg) {
        List<NLZC003001PMsg> list = new ArrayList<NLZC003001PMsg>();

        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            NLZC003001PMsg dtlPMsg = new NLZC003001PMsg();

            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).invtyOrdNum_T, hdrPMsg.invtyOrdNum);
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).invtyOrdLineNum_T, ZYPCommonFunc.leftPad(String.valueOf(i + 1), 3, "0"));

            ZYPEZDItemValueSetter.setValue(dtlPMsg.xxProcTpCd, NLZC003001.PROC_TP_CRAT);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.xxDtTpCd, NLZC003001.DT_TP_DTL);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.invtyOrdNum, bizMsg.A.no(i).invtyOrdNum_T);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.invtyOrdTpCd, INVTY_ORD_TP.CONFIGURATION_CHANGE);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.invtyLocCd, bizMsg.A.no(i).invtyLocCd_A);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.invtyLocCd_D1, bizMsg.A.no(i).invtyLocCd_A);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.locStsCd, LOC_STS.DC_STOCK);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.locStsCd_D1, LOC_STS.DC_STOCK);
            ZYPEZDItemValueSetter.setValue(hdrPMsg.trxCd, hdrPMsg.trxCd);
            ZYPEZDItemValueSetter.setValue(hdrPMsg.trxRsnCd, hdrPMsg.trxRsnCd);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.trxSrcTpCd, TRX_SRC_TP.MOVEMENT);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.sysSrcCd, SYS_SRC.S21_LOGISTICS);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.invtyOrdLineNum, bizMsg.A.no(i).invtyOrdLineNum_T);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.mdseCd, bizMsg.A.no(i).mdseCd_A);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.stkStsCd, bizMsg.A.no(i).stkStsCd_A);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.ordQty, bizMsg.A.no(i).ordQty_A);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.invtyOrdLineCostAmt, bizMsg.A.no(i).invtyOrdLineCostAmt_A);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.rmvConfigFlg, bizMsg.A.no(i).rmvConfigFlg_A);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.invtyOrdDtlStsCd, INVTY_ORD_STS.APPROVED);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.svcConfigMstrPk, bizMsg.A.no(i).svcConfigMstrPk_A);
            if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).serNum_A)) {
                ZYPEZDItemValueSetter.setValue(dtlPMsg.serialInfoList.no(0).invtyOrdNum, bizMsg.A.no(i).invtyOrdNum_T);
                ZYPEZDItemValueSetter.setValue(dtlPMsg.serialInfoList.no(0).invtyOrdLineNum, bizMsg.A.no(i).invtyOrdLineNum_T);
                ZYPEZDItemValueSetter.setValue(dtlPMsg.serialInfoList.no(0).serNum, bizMsg.A.no(i).serNum_A);
                dtlPMsg.serialInfoList.setValidCount(1);
            }

            list.add(dtlPMsg);
        }
        return list;
    }

    public static List<NWZC107001PMsg> getAllocNonCpoApiPMsgList(String glblCmpyCd, NLCL0300CMsg bizMsg) {
        List<NWZC107001PMsg> list = new ArrayList<NWZC107001PMsg>();
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            NWZC107001PMsg nwzc107001PMsg = new NWZC107001PMsg();
            ZYPEZDItemValueSetter.setValue(nwzc107001PMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(nwzc107001PMsg.slsDt, ZYPDateUtil.getSalesDate(glblCmpyCd));
            ZYPEZDItemValueSetter.setValue(nwzc107001PMsg.xxSysSrcCd, SYS_SRC.S21_LOGISTICS);
            ZYPEZDItemValueSetter.setValue(nwzc107001PMsg.xxRqstTpCd, NWZC107001.REQ_TP_NEW);
            ZYPEZDItemValueSetter.setValue(nwzc107001PMsg.xxPrtlAcptFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(nwzc107001PMsg.trxSrcTpCd, TRX_SRC_TP.MOVEMENT);
            ZYPEZDItemValueSetter.setValue(nwzc107001PMsg.trxCd, TRX.MOVEMENT);
            ZYPEZDItemValueSetter.setValue(nwzc107001PMsg.trxRsnCd, TRX_RSN.CONFIG_CHANGE_STOCK_OUT);
            ZYPEZDItemValueSetter.setValue(nwzc107001PMsg.trxHdrNum, bizMsg.A.no(i).invtyOrdNum_T);
            ZYPEZDItemValueSetter.setValue(nwzc107001PMsg.trxLineNum, bizMsg.A.no(i).invtyOrdLineNum_T);
            ZYPEZDItemValueSetter.setValue(nwzc107001PMsg.trxLineSubNum, "001");
            ZYPEZDItemValueSetter.setValue(nwzc107001PMsg.mdseCd, bizMsg.A.no(i).mdseCd_A);
            ZYPEZDItemValueSetter.setValue(nwzc107001PMsg.invtyLocCd, bizMsg.A.no(i).invtyLocCd_A);
            ZYPEZDItemValueSetter.setValue(nwzc107001PMsg.locStsCd, LOC_STS.DC_STOCK);
            ZYPEZDItemValueSetter.setValue(nwzc107001PMsg.stkStsCd, bizMsg.A.no(i).stkStsCd_A);
            ZYPEZDItemValueSetter.setValue(nwzc107001PMsg.xxRqstQty, bizMsg.A.no(i).ordQty_A);
            ZYPEZDItemValueSetter.setValue(nwzc107001PMsg.xxUnitPrc, bizMsg.A.no(i).unitPrcAmt_A);
            ZYPEZDItemValueSetter.setValue(nwzc107001PMsg.xxSlsAmt, bizMsg.A.no(i).invtyOrdLineCostAmt_A);
            ZYPEZDItemValueSetter.setValue(nwzc107001PMsg.billToCustCd, bizMsg.A.no(i).invtyLocCd_A);
            ZYPEZDItemValueSetter.setValue(nwzc107001PMsg.sellToCustCd, bizMsg.A.no(i).invtyLocCd_A);
            ZYPEZDItemValueSetter.setValue(nwzc107001PMsg.shipToCustCd, bizMsg.A.no(i).invtyLocCd_A);
            ZYPEZDItemValueSetter.setValue(nwzc107001PMsg.rsdDt, ZYPDateUtil.getSalesDate(glblCmpyCd));
            list.add(nwzc107001PMsg);
        }

        return list;
    }

    public static List<NWZC003001PMsg> getShippingPlanUpdateApiPMsgList(String glblCmpyCd, NLCL0300CMsg bizMsg) {
        List<NWZC003001PMsg> list = new ArrayList<NWZC003001PMsg>();
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            NWZC003001PMsg shpgPlnApiPMsg = new NWZC003001PMsg();
            ZYPEZDItemValueSetter.setValue(shpgPlnApiPMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(shpgPlnApiPMsg.shpgModeCd, NWZC003001.MODE_SHIPPINGREQUEST);
            ZYPEZDItemValueSetter.setValue(shpgPlnApiPMsg.trxHdrNum, bizMsg.A.no(i).invtyOrdNum_T);
            ZYPEZDItemValueSetter.setValue(shpgPlnApiPMsg.trxLineNum, bizMsg.A.no(i).invtyOrdLineNum_T);
            ZYPEZDItemValueSetter.setValue(shpgPlnApiPMsg.trxLineSubNum, "001");
            if (bizMsg.A.no(i).ordQty_A.getValue().compareTo(bizMsg.A.no(i).invtyAvalQty_A.getValue()) > 0) {
                ZYPEZDItemValueSetter.setValue(shpgPlnApiPMsg.avalSoQty, bizMsg.A.no(i).invtyAvalQty_A);
            } else {
                ZYPEZDItemValueSetter.setValue(shpgPlnApiPMsg.avalSoQty, bizMsg.A.no(i).ordQty_A);
            }

            ZYPEZDItemValueSetter.setValue(shpgPlnApiPMsg.trxSrcTpCd, TRX_SRC_TP.MOVEMENT);
            list.add(shpgPlnApiPMsg);
        }
        return list;
    }

    public static List<NLZC205001PMsg> getSoApiPMsgList(String glblCmpyCd, NLCL0300CMsg bizMsg) {
        List<NLZC205001PMsg> list = new ArrayList<NLZC205001PMsg>();
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            NLXC023001PMsg nlxc023001PMsg = new NLXC023001PMsg();
            ZYPEZDItemValueSetter.setValue(nlxc023001PMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(nlxc023001PMsg.trxHdrNum, bizMsg.A.no(i).invtyOrdNum_T);
            ZYPEZDItemValueSetter.setValue(nlxc023001PMsg.trxLineNum, bizMsg.A.no(i).invtyOrdLineNum_T);
            ZYPEZDItemValueSetter.setValue(nlxc023001PMsg.trxSrcTpCd, TRX_SRC_TP.MOVEMENT);
            NLXC023001 nlxc023001 = new NLXC023001();
            nlxc023001.execute(nlxc023001PMsg, ONBATCH_TYPE.ONLINE);

            NLZC205001PMsg soApiPMsg = new NLZC205001PMsg();
            ZYPEZDItemValueSetter.setValue(soApiPMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(soApiPMsg.sceOrdTpCd, SCE_ORD_TP.CONFIG_CHANGE);
            ZYPEZDItemValueSetter.setValue(soApiPMsg.shpgPlnNum, nlxc023001PMsg.shpgPlnNum);
            ZYPEZDItemValueSetter.setValue(soApiPMsg.shpgFrceFlg, NLZC205001.SHPG_FRCE_FLG_ON);
            ZYPEZDItemValueSetter.setValue(soApiPMsg.xxModeCd, NLZC205001.MODE_NEW);
            list.add(soApiPMsg);
        }
        return list;
    }

    public static List<NLZC200001PMsg> getRwsApiPMsgList(List<NLZC205001PMsg> soApiPMsgList) {
        List<NLZC200001PMsg> list = new ArrayList<NLZC200001PMsg>();
        for (NLZC205001PMsg soApiPMsg : soApiPMsgList) {
            NLZC200001PMsg rwsApiPMsg = new NLZC200001PMsg();
            ZYPEZDItemValueSetter.setValue(rwsApiPMsg.glblCmpyCd, soApiPMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(rwsApiPMsg.sysSrcCd, SYS_SRC.S21_LOGISTICS);
            ZYPEZDItemValueSetter.setValue(rwsApiPMsg.inbdSrcTpCd, INBD_SRC_TP.SO);
            ZYPEZDItemValueSetter.setValue(rwsApiPMsg.soNum, soApiPMsg.soNum);
            list.add(rwsApiPMsg);
            break;
        }
        return list;
    }

    public static List<NLZC304001PMsg> getRwsSerApiPMsg(String glblCmpyCd, NLCL0300CMsg bizMsg, List<NLZC200001PMsg> rwsApiPMsgList) {
        List<NLZC304001PMsg> rwsSerApiPMsgList = new ArrayList<NLZC304001PMsg>();
        for (NLZC200001PMsg rwsApiPMsg : rwsApiPMsgList) {
            for (int i = 0; i <rwsApiPMsg.RWSNumList.getValidCount(); i++) {

                NLZC304001PMsg rwsSerApi = new NLZC304001PMsg();
                ZYPEZDItemValueSetter.setValue(rwsSerApi.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(rwsSerApi.rwsNum, rwsApiPMsg.RWSNumList.no(i).rwsNum);
                int serialListIdx = 0;

            	Map<String, Object> param = new HashMap<String, Object>();
                param.put("glblCmpyCd", glblCmpyCd);
                param.put("rwsNum", rwsApiPMsg.RWSNumList.no(i).rwsNum.getValue());

                // Execute
                S21SsmEZDResult result = NLCL0300Query.getInstance().getRwsSerApiParameterList(param);

                if (result.isCodeNormal()) {
                    List<Map<String, Object>> resultList = (List<Map<String, Object>>) result.getResultObject();
                    for (Map<String, Object> resultMap : resultList) {
                        ZYPEZDItemValueSetter.setValue(rwsSerApi.SerialList.no(serialListIdx).rwsDtlLineNum, (String) resultMap.get("RWS_DTL_LINE_NUM"));
                        ZYPEZDItemValueSetter.setValue(rwsSerApi.SerialList.no(serialListIdx).serNum, (String) resultMap.get("SER_NUM"));
                        ZYPEZDItemValueSetter.setValue(rwsSerApi.SerialList.no(serialListIdx).mdseCd, (String) resultMap.get("MDSE_CD"));
                        ZYPEZDItemValueSetter.setValue(rwsSerApi.SerialList.no(serialListIdx).putAwayChkStsCd, PUT_AWAY_CHK_STS.NO_NEED);
                        ZYPEZDItemValueSetter.setValue(rwsSerApi.SerialList.no(serialListIdx).serNumSendFlg, ZYPConstant.FLG_OFF_N);
                        serialListIdx++;
                    }
                }
                rwsSerApi.SerialList.setValidCount(serialListIdx);
                if (serialListIdx > 0) {
                    rwsSerApiPMsgList.add(rwsSerApi);
                }
            }
        }
        return rwsSerApiPMsgList;
    }

    public static NLZC302001PMsg getSerialUpdateApiPMsg(String glblCmpyCd, NLCL0300CMsg bizMsg) {
        NLZC302001PMsg serialUpdateApiPMsg = new NLZC302001PMsg();
        ZYPEZDItemValueSetter.setValue(serialUpdateApiPMsg.glblCmpyCd, glblCmpyCd);
        int serialApiPMsgIdx = 0;
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).serNum_A)) {
                ZYPEZDItemValueSetter.setValue(serialUpdateApiPMsg.UpdateDetailList.no(serialApiPMsgIdx).serNum, bizMsg.A.no(i).serNum_A);
                ZYPEZDItemValueSetter.setValue(serialUpdateApiPMsg.UpdateDetailList.no(serialApiPMsgIdx).mdseCd, bizMsg.A.no(i).mdseCd_A);
                ZYPEZDItemValueSetter.setValue(serialUpdateApiPMsg.UpdateDetailList.no(serialApiPMsgIdx).serTrxTs, getSystemDateTime());
                ZYPEZDItemValueSetter.setValue(serialUpdateApiPMsg.UpdateDetailList.no(serialApiPMsgIdx).serTrxEventCd, SER_TRX_EVENT.SUB_WAREHOUSE_CHANGE);
                ZYPEZDItemValueSetter.setValue(serialUpdateApiPMsg.UpdateDetailList.no(serialApiPMsgIdx).toStkStsCd, bizMsg.A.no(i).stkStsCd_A);
                ZYPEZDItemValueSetter.setValue(serialUpdateApiPMsg.UpdateDetailList.no(serialApiPMsgIdx).manCratFlg, ZYPConstant.FLG_ON_Y);
                serialApiPMsgIdx++;
            }
            serialUpdateApiPMsg.UpdateDetailList.setValidCount(serialApiPMsgIdx);
        }
        return serialUpdateApiPMsg;
    }

    /**
     * setShipParam
     * @param glblCmpyCd String
     * @param sMsgALine NLBL2020_ASMsg
     * @return NLZC210001PMsg
     */
    public static List<NLZC210001PMsg> getShipConfirmationApiPMsg(String glblCmpyCd, String soNum) {

        SHPG_ORDTMsg shpgOrdInMsg = new SHPG_ORDTMsg();
        ZYPEZDItemValueSetter.setValue(shpgOrdInMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(shpgOrdInMsg.soNum, soNum);
        SHPG_ORDTMsg shpgOrdOutMsg = (SHPG_ORDTMsg) EZDTBLAccessor.findByKey(shpgOrdInMsg);

        SHPG_ORD_DTLTMsg inMsg = new SHPG_ORD_DTLTMsg();
        inMsg.setSQLID("002");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("soNum01", soNum);
        SHPG_ORD_DTLTMsgArray shpgOrdDtlArray = (SHPG_ORD_DTLTMsgArray) EZDTBLAccessor.findByCondition(inMsg);

        List<NLZC210001PMsg> list = new ArrayList<NLZC210001PMsg>();
        for (int i = 0; i < shpgOrdDtlArray.getValidCount(); i++) {
            NLZC210001PMsg pMsg = new NLZC210001PMsg();
            ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(pMsg.whCd, shpgOrdDtlArray.no(i).invtyLocCd);
            ZYPEZDItemValueSetter.setValue(pMsg.soNum, shpgOrdDtlArray.no(i).soNum);
            ZYPEZDItemValueSetter.setValue(pMsg.sceOrdTpCd, shpgOrdOutMsg.sceOrdTpCd);
            ZYPEZDItemValueSetter.setValue(pMsg.shipDtTmTs, ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmss"));
            ZYPEZDItemValueSetter.setValue(pMsg.soSlpNum, shpgOrdDtlArray.no(i).soSlpNum);
            ZYPEZDItemValueSetter.setValue(pMsg.soProcStsCd_DT, SO_PROC_STS.SHIP);
            ZYPEZDItemValueSetter.setValue(pMsg.mdseCd, shpgOrdDtlArray.no(i).mdseCd);
            ZYPEZDItemValueSetter.setValue(pMsg.fromStkStsCd, shpgOrdDtlArray.no(i).fromStkStsCd);
            ZYPEZDItemValueSetter.setValue(pMsg.shipQty, shpgOrdDtlArray.no(i).shpgQty);
            ZYPEZDItemValueSetter.setValue(pMsg.totFrtAmt, BigDecimal.ZERO);
            list.add(pMsg);
        }
        return list;
    }

    /**
     * @param glblCmpyCd String
     * @param rwsHdrTMsg RWS_HDRTMsg
     * @return NLZC206001PMsg
     */
    public static NLZC206001PMsg getRwsPutAwayApiPMsg(String glblCmpyCd, RWS_HDRTMsg rwsHdrTMsg) {

        String rwsNum = rwsHdrTMsg.rwsNum.getValue();

        RWS_DTLTMsg rwsDtlInMsg = new RWS_DTLTMsg();
        rwsDtlInMsg.setSQLID("002");
        rwsDtlInMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        rwsDtlInMsg.setConditionValue("rwsNum01", rwsNum);
        RWS_DTLTMsgArray rwsDtlArray = (RWS_DTLTMsgArray) EZDTBLAccessor.findByCondition(rwsDtlInMsg);


        RWS_SERTMsg rwsSerInMsg = new RWS_SERTMsg();
        rwsSerInMsg.setSQLID("002");
        rwsSerInMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        rwsSerInMsg.setConditionValue("rwsNum01", rwsNum);
        RWS_SERTMsgArray rwsSerArray = (RWS_SERTMsgArray) EZDTBLAccessor.findByCondition(rwsSerInMsg);

        NLZC206001PMsg putAwayS21DcApiPMsg = new NLZC206001PMsg();

        // Set Parameter
        ZYPEZDItemValueSetter.setValue(putAwayS21DcApiPMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(putAwayS21DcApiPMsg.slsDt, ZYPDateUtil.getSalesDate(glblCmpyCd));

        int rwsDtlCnt = 0;
        int rwsSerCnt = 0;

        for (; rwsDtlCnt < rwsDtlArray.getValidCount(); rwsDtlCnt++) {
            ZYPEZDItemValueSetter.setValue(putAwayS21DcApiPMsg.RWSPutAwayList.no(rwsDtlCnt).rwsNum, rwsDtlArray.no(rwsDtlCnt).rwsNum);
            ZYPEZDItemValueSetter.setValue(putAwayS21DcApiPMsg.RWSPutAwayList.no(rwsDtlCnt).rwsDtlLineNum, rwsDtlArray.no(rwsDtlCnt).rwsDtlLineNum);
            ZYPEZDItemValueSetter.setValue(putAwayS21DcApiPMsg.RWSPutAwayList.no(rwsDtlCnt).invtyStkStsCd, rwsDtlArray.no(rwsDtlCnt).invtyStkStsCd);
            ZYPEZDItemValueSetter.setValue(putAwayS21DcApiPMsg.RWSPutAwayList.no(rwsDtlCnt).sceOrdTpCd, rwsHdrTMsg.sceOrdTpCd);
            ZYPEZDItemValueSetter.setValue(putAwayS21DcApiPMsg.RWSPutAwayList.no(rwsDtlCnt).rwsStkDtTmTs, ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmss"));
            ZYPEZDItemValueSetter.setValue(putAwayS21DcApiPMsg.RWSPutAwayList.no(rwsDtlCnt).rwsStkQty, rwsDtlArray.no(rwsDtlCnt).rwsQty.getValue().abs());
            ZYPEZDItemValueSetter.setValue(putAwayS21DcApiPMsg.RWSPutAwayList.no(rwsDtlCnt).whCd, rwsDtlArray.no(rwsDtlCnt).invtyLocCd);
            ZYPEZDItemValueSetter.setValue(putAwayS21DcApiPMsg.RWSPutAwayList.no(rwsDtlCnt).mdseCd, rwsDtlArray.no(rwsDtlCnt).mdseCd);
            ZYPEZDItemValueSetter.setValue(putAwayS21DcApiPMsg.RWSPutAwayList.no(rwsDtlCnt).rwsRefNum, rwsHdrTMsg.rwsRefNum);
        }

        // Set Serial
        for (; rwsSerCnt < rwsSerArray.getValidCount(); rwsSerCnt++) {
            ZYPEZDItemValueSetter.setValue(putAwayS21DcApiPMsg.RcvSerNumList.no(rwsSerCnt).rwsDtlLineNum, rwsSerArray.no(rwsSerCnt).rwsLineNum);
            ZYPEZDItemValueSetter.setValue(putAwayS21DcApiPMsg.RcvSerNumList.no(rwsSerCnt).serNum, rwsSerArray.no(rwsSerCnt).serNum);
            ZYPEZDItemValueSetter.setValue(putAwayS21DcApiPMsg.RcvSerNumList.no(rwsSerCnt).mdseCd, rwsSerArray.no(rwsSerCnt).mdseCd);
        }
        putAwayS21DcApiPMsg.RWSPutAwayList.setValidCount(rwsDtlCnt);
        putAwayS21DcApiPMsg.RcvSerNumList.setValidCount(rwsSerCnt);

        return putAwayS21DcApiPMsg;
    }

    /**
     * setSoCloseParam
     * @param cMsg NLBL2020CMsg
     * @param rwsHdrMap Map<String, Object>
     * @return NLZC207001PMsg
     */
    public static NLZC207001PMsg getRwsCpltApiPMsg(String glblCmpyCd, RWS_HDRTMsg rwsHdrTMsg) {

        NLZC207001PMsg pMsg = new NLZC207001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, ZYPDateUtil.getSalesDate(glblCmpyCd));
        ZYPEZDItemValueSetter.setValue(pMsg.rwsNum, rwsHdrTMsg.rwsNum);
        ZYPEZDItemValueSetter.setValue(pMsg.sceOrdTpCd, rwsHdrTMsg.sceOrdTpCd);
        ZYPEZDItemValueSetter.setValue(pMsg.rwsCloDtTmTs, ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmss"));
        ZYPEZDItemValueSetter.setValue(pMsg.whCd, rwsHdrTMsg.whCd);
        ZYPEZDItemValueSetter.setValue(pMsg.rwsRefNum, rwsHdrTMsg.rwsRefNum);

        return pMsg;
    }

    private static String getSystemDateTime() {

        String yyyymmdd = ZYPDateUtil.getSalesDate();
        String hhmmss = ZYPDateUtil.getCurrentSystemTime("HHmmss");

        return yyyymmdd + hhmmss;
    }

    // QC#54380 Mod Start
    public static NSZC048001PMsg callSvcMdlApi(String glblCmpyCd, NLCL0300CMsg bizMsg, int dtlLineNum) {

        NSZC048001PMsg svcMdlPMsg = new NSZC048001PMsg();

        ZYPEZDItemValueSetter.setValue(svcMdlPMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(svcMdlPMsg.slsDt, ZYPDateUtil.getSalesDate(glblCmpyCd));
        ZYPEZDItemValueSetter.setValue(svcMdlPMsg.prntMdseCd, bizMsg.A.no(dtlLineNum).mdseCd_A);

        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(bizMsg.A.no(i).rmvConfigFlg_A.getValue())) {
                continue;
            }

            if (i == dtlLineNum ) {
                continue;
            }

            int ix = svcMdlPMsg.xxChildMdseCdList.getValidCount();
            ZYPEZDItemValueSetter.setValue(svcMdlPMsg.xxChildMdseCdList.no(ix++).childMdseCd,  bizMsg.A.no(i).mdseCd_A);
            svcMdlPMsg.xxChildMdseCdList.setValidCount(ix);
        }

        NSZC048001 smApi = new NSZC048001();
        smApi.execute(svcMdlPMsg, ONBATCH_TYPE.ONLINE);

        return svcMdlPMsg;
    }
//    public static NSZC048001PMsg callSvcMdlApi(String glblCmpyCd, NLCL0300CMsg bizMsg) {
//
//        NSZC048001PMsg svcMdlPMsg = new NSZC048001PMsg();
//
//        ZYPEZDItemValueSetter.setValue(svcMdlPMsg.glblCmpyCd, glblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(svcMdlPMsg.slsDt, ZYPDateUtil.getSalesDate(glblCmpyCd));
//        ZYPEZDItemValueSetter.setValue(svcMdlPMsg.prntMdseCd, bizMsg.A.no(0).mdseCd_A);
//
//        for (int i = 1; i < bizMsg.A.getValidCount(); i++) {
//            if (ZYPConstant.FLG_ON_Y.equals(bizMsg.A.no(i).rmvConfigFlg_A.getValue())) {
//                continue;
//            }
//
//            int ix = svcMdlPMsg.xxChildMdseCdList.getValidCount();
//            ZYPEZDItemValueSetter.setValue(svcMdlPMsg.xxChildMdseCdList.no(ix++).childMdseCd,  bizMsg.A.no(i).mdseCd_A);
//            svcMdlPMsg.xxChildMdseCdList.setValidCount(ix);
//        }
//
//        NSZC048001 smApi = new NSZC048001();
//        smApi.execute(svcMdlPMsg, ONBATCH_TYPE.ONLINE);
//
//        return svcMdlPMsg;
//    }
    // QC#54380 Mod End

    // START 2019/08/13 M.Naito [QC#52185,ADD]
    /**
     * isSpecifiedOrder
     * @param glblCmpyCd String
     * @param svcConfigMstrPk BigDecimal
     * @return NLZC207001PMsg
     */
    public static boolean isSpecifiedOrder(String glblCmpyCd, BigDecimal svcConfigMstrPk) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("svcConfigMstrPk", svcConfigMstrPk);
        param.put("ordHdrStsCancelled", ORD_HDR_STS.CANCELLED);
        param.put("ordHdrStsClosed", ORD_HDR_STS.CLOSED);
        param.put("invtyOrdStsCancelled", INVTY_ORD_STS.CANCEL);
        param.put("invtyOrdStsClosed", INVTY_ORD_STS.CLOSED);

        // Execute
        S21SsmEZDResult result = NLCL0300Query.getInstance().getOrderBySvcConfigMstrPk(param);

        String cpoOrdNum = null;
        if (result.isCodeNormal()) {
            cpoOrdNum = (String) result.getResultObject();
        }
        if (ZYPCommonFunc.hasValue(cpoOrdNum)) {
            return true;
        }
        return false;
    }
    // END 2019/08/13 M.Naito [QC#52185,ADD]

    // START 2022/02/15 A.Marte [QC#59705,ADD]
    /**
     * hasAvailNonSerialSingleItem
     * @param glblCmpyCd String
     * @param mdseCd String
     * @param invtyLocCd String
     * @return boolean
     */
    public static boolean hasAvailNonSerialSingleItem(String glblCmpyCd, String mdseCd, String invtyLocCd) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("mdseCd", mdseCd);
        param.put("invtyLocCd", invtyLocCd);

        // Execute
        S21SsmEZDResult result = NLCL0300Query.getInstance().countAvailNonSerialSingleItem(param);

        Integer availItemCount = 0;
        if (result.isCodeNormal()) {
            availItemCount = (Integer) result.getResultObject();
        }
        return ( availItemCount > 0 );
    }
    // END 2022/02/15 A.Marte [QC#59705,ADD]
}