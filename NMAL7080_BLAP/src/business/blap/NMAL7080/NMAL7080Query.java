/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL7080;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Supply Agreement Plan Set Up
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/15   Fujitsu         M.Ohno          Create          N/A
 * 2016/05/06   SRAA            K.Aratani       Update          QC#8012
 * 2016/10/14   Fujitsu         M.Ohno          Update          S21_NA#13253
 * 2018/04/04   Fujitsu         K.Ishizuka      Update          S21_NA#23336
 *</pre>
 */
public final class NMAL7080Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NMAL7080Query MY_INSTANCE = new NMAL7080Query();

    /**
     * Private constructor
     */
    private NMAL7080Query() {
        super();
    }

    /**
     * Get the NMAL7080Query instance.
     * @return NMAL7080Query instance
     */
    public static NMAL7080Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * getSupplyAgreement
     * @param bizMsg NMAL7080SMsg
     * @param glblMsg NMAL7080SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSupplyAgreement(NMAL7080CMsg bizMsg, NMAL7080SMsg glblMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("splyAgmtPlnPk", bizMsg.splyAgmtPlnPk.getValue());
        params.put("splyAgmtPlnStsCd", bizMsg.splyAgmtPlnStsCd.getValue());
        params.put("slsDt", ZYPDateUtil.getSalesDate(getGlobalCompanyCode()));
        params.put("mdseCd", bizMsg.mdseCd_IC.getValue());
        params.put("rowNum", glblMsg.A.length() + 1);
        return getSsmEZDClient().queryObjectList("getSupplyAgreement", params);
    }

    /**
     * getSupplyAgreementName
     * @param bizMsg NMAL7080SMsg
     * @param glblMsg NMAL7080SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSupplyAgreementName(NMAL7080CMsg bizMsg, NMAL7080SMsg glblMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("splyAgmtPlnNm", bizMsg.splyAgmtPlnNm.getValue());

        return getSsmEZDClient().queryObjectList("getSupplyAgreementName", params);
    }

    /**
     * getSupplyAgreementDetailFreq
     * @param bizMsg NMAL7080SMsg
     * @param glblMsg NMAL7080SMsg
     * @return S21SsmEZDResult
     */
    // 2018/04/04 S21_NA#23336 Mod Start
    //public S21SsmEZDResult getSupplyAgreementDetailFreq(NMAL7080CMsg bizMsg, NMAL7080SMsg glblMsg) {
    public S21SsmEZDResult getSupplyAgreementDetailFreq(BigDecimal splyAgmtPlnPk) {
    // 2018/04/04 S21_NA#23336 Mod End
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        // 2018/04/04 S21_NA#23336 Mod Start
        // params.put("splyAgmtPlnPk", bizMsg.splyAgmtPlnPk.getValue());
        params.put("splyAgmtPlnPk", splyAgmtPlnPk);
        // 2018/04/04 S21_NA#23336 Mod End

        return getSsmEZDClient().queryObjectList("getSupplyAgreementDetailFreq", params);
    }

    /**
     * getNotDeleteSupplyAgreementDetail
     * @param bizMsg NMAL7080SMsg
     * @param glblMsg NMAL7080SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getNotDeleteSupplyAgreementDetail(NMAL7080_ACMsg bizAMsg, NMAL7080SMsg glblMsg, List<BigDecimal> dtlPkList, BigDecimal splyAgmtPlnPk) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("splyAgmtPlnPk", splyAgmtPlnPk);
        if (dtlPkList.size() > 0) {
            BigDecimal[] dtlPk = (BigDecimal[]) dtlPkList.toArray(new BigDecimal[0]);
            params.put("dtlPk", dtlPk);
        } else {
            params.put("dtlPk", null);
        }
        params.put("mdseCd", bizAMsg.mdseCd_A.getValue());
        params.put("splyAgmtFreqTpCd", bizAMsg.splyAgmtFreqTpCd_A.getValue());
        params.put("splyAgmtPlnFirstQty", bizAMsg.splyAgmtPlnFirstQty_A.getValue());
        params.put("splyAgmtPlnQty", bizAMsg.splyAgmtPlnQty_A.getValue());
        params.put("splyAgmtPlnSqNum", bizAMsg.splyAgmtPlnSqNum_A.getValue());
        params.put("effFromDt", bizAMsg.effFromDt_A.getValue());
        params.put("effThruDt", bizAMsg.effThruDt_A.getValue());

        return getSsmEZDClient().queryObject("getNotDeleteSupplyAgreementDetail", params);
    }

    /**
     * getNotDeleteSupplyAgreementDetail
     * @param bizMsg NMAL7080SMsg
     * @param glblMsg NMAL7080SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult countDtlDupByEffDt(NMAL7080_ACMsg bizAMsg, NMAL7080SMsg glblMsg, List<BigDecimal> dtlPkList, BigDecimal splyAgmtPlnPk) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("splyAgmtPlnPk", splyAgmtPlnPk);
        if (dtlPkList.size() > 0) {
            BigDecimal[] dtlPk = (BigDecimal[]) dtlPkList.toArray(new BigDecimal[0]);
            params.put("dtlPk", dtlPk);
        } else {
            params.put("dtlPk", null);
        }
        params.put("mdseCd", bizAMsg.mdseCd_A.getValue());
        params.put("effFromDt", bizAMsg.effFromDt_A.getValue());
        if (ZYPCommonFunc.hasValue(bizAMsg.effThruDt_A)) {
            params.put("effThruDt", bizAMsg.effThruDt_A.getValue());
        } else {
            params.put("effThruDt", "99991231");
        }

        //QC#8012
        if (ZYPCommonFunc.hasValue(bizAMsg.splyAgmtFreqTpCd_A)) {
            params.put("splyAgmtFreqTpCd", bizAMsg.splyAgmtFreqTpCd_A.getValue());
        }
        if (ZYPCommonFunc.hasValue(bizAMsg.splyAgmtPlnFirstQty_A)) {
            params.put("splyAgmtPlnFirstQty", bizAMsg.splyAgmtPlnFirstQty_A.getValue());
        }
        if (ZYPCommonFunc.hasValue(bizAMsg.splyAgmtPlnQty_A)) {
            params.put("splyAgmtPlnQty", bizAMsg.splyAgmtPlnQty_A.getValue());
        }
        if (ZYPCommonFunc.hasValue(bizAMsg.splyAgmtPlnSqNum_A)) {
            params.put("splyAgmtPlnSqNum", bizAMsg.splyAgmtPlnSqNum_A.getValue());
        }

        return getSsmEZDClient().queryObject("countDtlDupByEffDt", params);
    }

	// 2016/10/14 S21_NA#13253 Add Start
    /**
     * countActiveSupplyPriceList
     * @param bizMsg NMAL7080SMsg
     * @param glblMsg NMAL7080SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult countActiveSupplyPriceList(NMAL7080CMsg bizMsg, NMAL7080SMsg glblMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("splyAgmtPlnPk", bizMsg.splyAgmtPlnPk.getValue());
        params.put("slsDt", ZYPDateUtil.getSalesDate(getGlobalCompanyCode()));

        return getSsmEZDClient().queryObject("countActiveSupplyPriceList", params);
    }
    // 2016/10/14 S21_NA#13253 Add End

}
