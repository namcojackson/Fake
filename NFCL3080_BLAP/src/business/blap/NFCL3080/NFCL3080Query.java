/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NFCL3080;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * NFCL3080Query
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/13   Fujitsu         S.Fujita        Create          N/A
 * 2016/04/13   Fujitsu         S.Fujita        Create          QC#6975
 *</pre>
 */
public final class NFCL3080Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NFCL3080Query MY_INSTANCE = new NFCL3080Query();

    /**
     * Private constructor
     */
    private NFCL3080Query() {
        super();
    }

    /**k
     * Get the NFCL3080Query instance.
     * @return NFCL3080Query instance
     */
    public static NFCL3080Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * OrgInvNum Check
     * @param bizMsg NFCL3080CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult checkOrgInvNum(NFCL3080CMsg bizMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("invNum", bizMsg.invNum.getValue());

        return getSsmEZDClient().queryObject("getOrgInvNum", params);
    }

    /**
     * SvcInvNum Check
     * @param bizMsg NFCL3080CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult checkSvcInvNum(NFCL3080CMsg bizMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        // START 2016/04/13 S.Fujita [S21 NA Unit Test QC#6975,MOD]
        params.put("invNum", bizMsg.invNum_OR.getValue());
        // params.put("invNum", bizMsg.invNum.getValue());
        // END 2016/04/13 S.Fujita [S21 NA Unit Test QC#6975,MOD]

        return getSsmEZDClient().queryObject("getSvcInvNum", params);
    }

    /**
     * Meter Search
     * @param bizMsg NFCL3080CMsg
     * @param glblMsg NFCL3080SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult meterSearch(NFCL3080CMsg bizMsg, NFCL3080SMsg glblMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("invNum", bizMsg.invNum.getValue());

        params.put("rowNum", glblMsg.A.length() + 1);

        return getSsmEZDClient().queryEZDMsgArray("getMeterList", params, glblMsg.A);
    }

    /**
     * SvcInv Search
     * @param bizMsg NFCL3080CMsg
     * @param glblMsg NFCL3080SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult svcInvSearch(NFCL3080CMsg bizMsg, NFCL3080SMsg glblMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        // START 2016/04/13 S.Fujita [S21 NA Unit Test QC#6975,MOD]
        // params.put("invNum", bizMsg.invNum.getValue());
        params.put("invNum", bizMsg.invNum_OR.getValue());
        // END 2016/04/13 S.Fujita [S21 NA Unit Test QC#6975,MOD]

        params.put("rowNum", glblMsg.A.length() + 1);

        return getSsmEZDClient().queryEZDMsgArray("getSvcInvList", params, glblMsg.A);
    }

    /**
     * InvPrt Check
     * @param bizMsg NFCL3080CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult checkInvPrt(NFCL3080CMsg bizMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("invNum", bizMsg.invNum.getValue());

        return getSsmEZDClient().queryObject("getInvPrt", params);
    }
}
