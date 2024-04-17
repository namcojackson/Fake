/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NFAL0080;

import java.util.HashMap;
import java.util.Map;

import business.blap.NFAL0080.constant.NFAL0080Constant;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * Class name: NFAL0080Query
 * <dd>The class explanation: Business processing for Component ID :
 * NFAL0090Query
 * <dd>Remarks:
 * @version 1.0
 * @author N.Sasaki
 */
public final class NFAL0080Query extends S21SsmEZDQuerySupport implements NFAL0080Constant {

    /**
     * Singleton instance.
     */
    private static final NFAL0080Query INSTANCE = new NFAL0080Query();

    /**
     * User Profile
     */
    public static final S21UserProfileService PROFILE_SERVICE = S21UserProfileServiceFactory.getInstance().getService();

    /**
     * Global Company Code.
     */
    public static final String GLBL_CMPY_CD = PROFILE_SERVICE.getGlobalCompanyCode();

    /**
     * Constructor.
     */
    private NFAL0080Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NFAL0080Query
     */
    public static NFAL0080Query getInstance() {
        return INSTANCE;
    }

    /**
     * NFAL0080Query.xml id="getCoaSetLkupTpCd"
     * 
     * <pre>
     * Get pulldown value from FIRST_PROD_CTRL_V view.
     * </pre>
     * 
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCoaSetLkupTpCd() {

        Map<String, String> queryParam = new HashMap<String, String>();

        queryParam.put("glblCmpyCd", GLBL_CMPY_CD);

        return getSsmEZDClient().queryObjectList("getCoaSetLkupTpCd", queryParam, -1, -1);
    }

    /**
     * NFAL0080Query.xml id="getWholeRecords"
     * 
     * <pre>
     * Get result from IMPT_INV_PROD_LINE table.
     * </pre>
     * 
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getWholeRecords() {

        Map<String, String> queryParam = new HashMap<String, String>();

        queryParam.put("glblCmpyCd", GLBL_CMPY_CD);

        return getSsmEZDClient().queryObjectList("getWholeRecords", queryParam, -1, -1);
    }

    /**
     * NFAL0080Query.xml id="checkRecord"
     * 
     * <pre>
     * Check if there is a record having the same IMPT_INV_GRP_SLS_CD in IMPT_INV_PROD_LINE table. 
     * </pre>
     * 
     * @param queryMap Map<String, String>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult checkRecord(Map<String, String> queryMap) {

        Map<String, String> queryParam = new HashMap<String, String>();

        queryParam.put("glblCmpyCd", GLBL_CMPY_CD);
        queryParam.put("eligCoaSegPtrnCd", queryMap.get("eligCoaSegPtrnCd"));
        queryParam.put("coaSegLkupTpCd", queryMap.get("coaSegLkupTpCd"));

        return getSsmEZDClient().queryObjectList("checkRecord", queryParam, -1, -1);
    }

}
