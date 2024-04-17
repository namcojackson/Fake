/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NLEL0080;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.blap.NLEL0080.constant.NLEL0080Constant;
import business.db.DS_ASSET_MSTRTMsg;
import business.db.DS_ASSET_MSTRTMsgArray;
import business.db.PRNT_VNDTMsg;
import business.db.PRNT_VNDTMsgArray;
import business.db.SELL_TO_CUSTTMsg;
import business.db.SELL_TO_CUSTTMsgArray;
import business.db.SVC_MACH_MSTRTMsg;
import business.db.SVC_MACH_MSTRTMsgArray;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ASSET_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ACCT_CLS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORG_STRU_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * DS Asset Manual Entry
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/25   Hitachi         J.Kim           Create          N/A
 * 2016/04/11   Hitachi         T.Tsuchida      Update          QC#6734
 * 2016/05/11   Hitachi         K.Kojima        Update          QC#6870
 * 2016/05/20   Hitachi         T.Tsuchida      Update          QC#8096
 * 2016/06/27   Hitachi         K.Kojima        Update          QC#10866
 * 2016/10/24   Hitachi         J.Kim           Update          QC#11026
 * 2018/03/05   Hitachi         J.Kim           Update          QC#24570
 *</pre>
 */
public final class NLEL0080Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NLEL0080Query INSTANCE = new NLEL0080Query();

    /**
     * Constructor.
     */
    private NLEL0080Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NLEL0080Query
     */
    public static NLEL0080Query getInstance() {
        return INSTANCE;
    }

    // START 2016/05/11 K.Kojima [QC#6870,MOD]
    // /**
    // * getSvcMachMstr
    // * @param glblCmpyCd String
    // * @param serNum String
    // * @return SVC_MACH_MSTRTMsg
    // */
    // public SVC_MACH_MSTRTMsg getSvcMachMstr(String glblCmpyCd,
    // String serNum) {
    // SVC_MACH_MSTRTMsg svcMachMstr = new SVC_MACH_MSTRTMsg();
    // svcMachMstr.setConditionValue("glblCmpyCd01", glblCmpyCd);
    // svcMachMstr.setConditionValue("serNum01", serNum);
    // svcMachMstr.setSQLID("002");
    // svcMachMstr.setMaxCount(1);
    // SVC_MACH_MSTRTMsgArray svcMachMstrTMsgArray =
    // (SVC_MACH_MSTRTMsgArray)
    // EZDTBLAccessor.findByCondition(svcMachMstr);
    // if (svcMachMstrTMsgArray == null ||
    // svcMachMstrTMsgArray.getValidCount() != 1) {
    // return null;
    // }
    // return (SVC_MACH_MSTRTMsg) svcMachMstrTMsgArray.get(0);
    // }
    /**
     * getSvcMachMstr
     * @param glblCmpyCd String
     * @param serNum String
     * @return SVC_MACH_MSTRTMsgArray
     */
    public SVC_MACH_MSTRTMsgArray getSvcMachMstr(String glblCmpyCd, String serNum) {
        SVC_MACH_MSTRTMsg svcMachMstr = new SVC_MACH_MSTRTMsg();
        svcMachMstr.setConditionValue("glblCmpyCd01", glblCmpyCd);
        svcMachMstr.setConditionValue("serNum01", serNum);
        svcMachMstr.setSQLID("002");
        svcMachMstr.setMaxCount(2);
        return (SVC_MACH_MSTRTMsgArray) EZDTBLAccessor.findByCondition(svcMachMstr);
    }

    // END 2016/05/11 K.Kojima [QC#6870,MOD]

    /**
     * isExistCpoDtl
     * @param glblCmpyCd String
     * @param cpoOrdNum String
     * @param cpoDtlLineNum String
     * @param svcMachMstrPk BigDecimal
     * @return boolean
     */
    public boolean isExistCpoDtl(String glblCmpyCd, String cpoOrdNum, String cpoDtlLineNum, BigDecimal svcMachMstrPk) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("cpoOrdNum", cpoOrdNum);
        queryParam.put("cpoDtlLineNum", cpoDtlLineNum);
        queryParam.put("svcMachMstrPk", svcMachMstrPk);
        S21SsmEZDResult rslt = getSsmEZDClient().queryObject("getCpoDtl", queryParam);
        if (rslt != null && rslt.isCodeNormal()) {
            BigDecimal count = (BigDecimal) rslt.getResultObject();
            if (ZYPCommonFunc.hasValue(count) && BigDecimal.ZERO.compareTo(count) != 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * isExistShipToCust
     * @param glblCmpyCd String
     * @param sellToCustCd String
     * @return boolean
     */
    public boolean isExistSellToCust(String glblCmpyCd, String sellToCustCd) {
        SELL_TO_CUSTTMsg sellToCustTMsg = new SELL_TO_CUSTTMsg();
        sellToCustTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        sellToCustTMsg.setConditionValue("sellToCustCd01", sellToCustCd);
        sellToCustTMsg.setSQLID("001");
        SELL_TO_CUSTTMsgArray sellToCustTMsgArray = (SELL_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(sellToCustTMsg);
        if (sellToCustTMsgArray.getValidCount() > 0) {
            return true;
        }
        return false;
    }

    /**
     * isExistSalesRep
     * @param glblCmpyCd String
     * @param slsRepTocCd String
     * @return boolean
     */
    public boolean isExistSalesRep(String glblCmpyCd, String slsRepTocCd) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("slsRepTocCd", slsRepTocCd);
        queryParam.put("orgstruTpIsBasic", ORG_STRU_TP.BASIC_ORGANIZATION_STRUCTURE_FOR_SALES_SUMMARY);
        queryParam.put("slsDt", ZYPDateUtil.getSalesDate());
        S21SsmEZDResult rslt = getSsmEZDClient().queryObject("getSalesRep", queryParam);
        if (rslt != null && rslt.isCodeNormal()) {
            BigDecimal count = (BigDecimal) rslt.getResultObject();
            if (ZYPCommonFunc.hasValue(count) && BigDecimal.ZERO.compareTo(count) != 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * getTocCd
     * @param glblCmpyCd String
     * @param slsRepTocCd String
     * @return String
     */
    public String getTocCd(String glblCmpyCd, String slsRepTocCd) {
        String rtSlsRepTocCd = "";
        if (ZYPCommonFunc.hasValue(slsRepTocCd)) {
            Map<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put("glblCmpyCd", glblCmpyCd);
            queryParam.put("slsRepTocCd", slsRepTocCd);
            queryParam.put("orgstruTpIsBasic", ORG_STRU_TP.BASIC_ORGANIZATION_STRUCTURE_FOR_SALES_SUMMARY);
            queryParam.put("slsDt", ZYPDateUtil.getSalesDate());
            S21SsmEZDResult rslt = getSsmEZDClient().queryObject("getTocCd", queryParam);
            if (rslt != null && rslt.isCodeNormal()) {
                rtSlsRepTocCd = (String) rslt.getResultObject();
            }
        }
        return rtSlsRepTocCd;
    }

    /**
     * getDsAssetMstr
     * @param glblCmpyCd String
     * @param dsAssetMstrPk BigDecimal
     * @return boolean
     */
    public DS_ASSET_MSTRTMsg getDsAssetMstr(String glblCmpyCd, BigDecimal dsAssetMstrPk) {
        DS_ASSET_MSTRTMsg dsAssetMstrTMsg = new DS_ASSET_MSTRTMsg();
        ZYPEZDItemValueSetter.setValue(dsAssetMstrTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsAssetMstrTMsg.dsAssetMstrPk, dsAssetMstrPk);
        return (DS_ASSET_MSTRTMsg) S21FastTBLAccessor.findByKey(dsAssetMstrTMsg);
    }

    /**
     * isExistDsAssetMstr
     * @param glblCmpyCd String
     * @param mdseCd String
     * @param serNum String
     * @return boolean
     */
    public boolean isExistDsAssetMstr(String glblCmpyCd, String mdseCd, String serNum) {
        DS_ASSET_MSTRTMsg dsAssetMstrTMsg = new DS_ASSET_MSTRTMsg();
        dsAssetMstrTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        dsAssetMstrTMsg.setConditionValue("mdseCd01", mdseCd);
        dsAssetMstrTMsg.setConditionValue("serNum01", serNum);
        dsAssetMstrTMsg.setSQLID("002");
        DS_ASSET_MSTRTMsgArray dsAssetMstrTMsgArray = (DS_ASSET_MSTRTMsgArray) EZDTBLAccessor.findByCondition(dsAssetMstrTMsg);
        if (dsAssetMstrTMsgArray.getValidCount() > 0) {
            return true;
        }
        return false;
    }

    /**
     * getSvcMachMstr
     * @param glblCmpyCd String
     * @param svcMachMstrPk BigDecimal
     * @return SVC_MACH_MSTRTMsg
     */
    public SVC_MACH_MSTRTMsg getSvcMachMstr(String glblCmpyCd, BigDecimal svcMachMstrPk) {
        SVC_MACH_MSTRTMsg svcMachMstrTMsg = new SVC_MACH_MSTRTMsg();
        ZYPEZDItemValueSetter.setValue(svcMachMstrTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(svcMachMstrTMsg.svcMachMstrPk, svcMachMstrPk);
        return (SVC_MACH_MSTRTMsg) S21FastTBLAccessor.findByKey(svcMachMstrTMsg);
    }

    // START 2016/06/27 K.Kojima [QC#10866,ADD]
    /**
     * getDefaultLife
     * @param glblCmpyCd String
     * @param assetTpCd String
     * @return boolean
     */
    public String getDefaultLife(String glblCmpyCd, String assetTpCd) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("assetTpCd", assetTpCd);
        queryParam.put("maxDate", NLEL0080Constant.MAX_DATE);
        queryParam.put("salesDate", ZYPDateUtil.getSalesDate());
        S21SsmEZDResult rslt = getSsmEZDClient().queryObject("getDefaultLife", queryParam);
        if (rslt != null && rslt.isCodeNormal()) {
            return (String) rslt.getResultObject();
        }
        return null;
    }

    // END 2016/06/27 K.Kojima [QC#10866,ADD]

    // START 2016/09/15 J.Kim [QC#10360,ADD]
    /**
     * getVndCd
     * @param glblCmpyCd String
     * @param vndName String
     * @return String
     */
    public String getVndCd(String glblCmpyCd, String vndName) {

        String vndCd = "";
        PRNT_VNDTMsg inTMsg = new PRNT_VNDTMsg();
        inTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inTMsg.setConditionValue("prntVndNm01", vndName);
        inTMsg.setConditionValue("inacDt01", ZYPDateUtil.getSalesDate());
        inTMsg.setSQLID("003");
        PRNT_VNDTMsgArray outTMsg = (PRNT_VNDTMsgArray) EZDTBLAccessor.findByCondition(inTMsg);
        if (outTMsg != null && outTMsg.getValidCount() > 0) {
            vndCd = outTMsg.no(0).prntVndCd.getValue();
        }
        return vndCd;
    }

    /**
     * isExistDsAcctCust
     * @param glblCmpyCd String
     * @param dsAcctNum String
     * @return List<Map<String, Object>>
     */
    public List<Map<String, Object>> isExistDsAcctCust(String glblCmpyCd, String dsAcctNum) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("dsAcctNum", dsAcctNum);
        queryParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        S21SsmEZDResult result = getSsmEZDClient().queryObjectList("getDsAcctCust", queryParam);
        if (result == null || result.isCodeNotFound()) {
            return null;
        }
        return (List<Map<String, Object>>) result.getResultObject();
    }

    /**
     * isExistPrntDsAssetMstrPk
     * @param glblCmpyCd String
     * @param assetTpNm String
     * @param prntDsAssetMstrPk BigDecimal
     * @return boolean
     */
    public boolean isExistPrntDsAssetMstrPk(String glblCmpyCd, String assetTpNm, BigDecimal prntDsAssetMstrPk) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("assetTpCd", assetTpNm);
        queryParam.put("prntDsAssetMstrPk", prntDsAssetMstrPk);
        queryParam.put("assetStsCd", ASSET_STS.PENDING);
        // START 2016/10/24 J.Kim [QC#11026,DEL]
        // if (ASSET_SRC_TP.PROCURED.equals(mode)) {
        // queryParam.put("procModeCd", PROCURED);
        // } else {
        // queryParam.put("procModeCd", LEASED);
        // }
        // END 2016/10/24 J.Kim [QC#11026,DEL]
        S21SsmEZDResult rslt = getSsmEZDClient().queryObject("getPrntDsAssetMstrPk", queryParam);
        if (rslt != null && rslt.isCodeNormal()) {
            BigDecimal count = (BigDecimal) rslt.getResultObject();
            if (ZYPCommonFunc.hasValue(count) && BigDecimal.ZERO.compareTo(count) != 0) {
                return true;
            }
        }
        return false;
    }

    // END 2016/09/15 J.Kim [QC#10360,ADD]

    /**
     * getCpoDtlLineNum
     * @param glblCmpyCd String
     * @param cpoOrdNum String
     * @param dsOrdPosnNum String
     * @param dsCpoLineNum String
     * @param dsCpoLineSubNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCpoDtlLineNum(String glblCmpyCd //
            , String cpoOrdNum, String dsOrdPosnNum, String dsCpoLineNum, String dsCpoLineSubNum) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("cpoOrdNum", cpoOrdNum);
        queryParam.put("dsOrdPosnNum", dsOrdPosnNum);
        queryParam.put("dsCpoLineNum", dsCpoLineNum);
        queryParam.put("dsCpoLineSubNum", dsCpoLineSubNum);
        return getSsmEZDClient().queryObjectList("getCpoDtlLineNum", queryParam);
    }

    // START 2018/03/05 J.Kim [QC#24570,ADD]
    /**
     * findByCondition
     * @param glblCmpyCd String
     * @param curLocAcctNum String
     * @return String
     */
    public boolean findByCondition(String glblCmpyCd, String curLocAcctNum) {

        SELL_TO_CUSTTMsg inTMsg = new SELL_TO_CUSTTMsg();
        inTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inTMsg.setConditionValue("sellToCustCd01", curLocAcctNum);
        inTMsg.setSQLID("003");
        SELL_TO_CUSTTMsgArray outTMsg = (SELL_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(inTMsg);
        if (outTMsg != null && outTMsg.getValidCount() > 0) {
            if (DS_ACCT_CLS.FM_ACCTS.equals(outTMsg.no(0).dsAcctClsCd.getValue())) {
                return true;
            }
        }
        return false;
    }
    // END 2018/03/05 J.Kim [QC#24570,ADD]

    // START 2018/08/23 J.Kim [QC#22267,ADD]
    /**
     * doProcessAmountSummary
     * @param dsAssetMstrPk BigDecimal
     */
    public Map<String, Object> doProcessAmountSummary(String glblCmpyCd, BigDecimal dsAssetMstrPk) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("prntDsAssetMstrPk", dsAssetMstrPk);
        S21SsmEZDResult result = getSsmEZDClient().queryObject("getAmount", queryParam);
        return (Map<String, Object>) result.getResultObject();
    }
    // END 2018/08/23 J.Kim [QC#22267,ADD]
}
