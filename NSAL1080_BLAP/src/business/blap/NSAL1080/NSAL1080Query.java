/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL1080;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Credit Rebill Main Screen
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/20   Hitachi         A.Kohinata      Create          N/A
 *</pre>
 */
public final class NSAL1080Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NSAL1080Query INSTANCE = new NSAL1080Query();

    /**
     * Constructor.
     */
    private NSAL1080Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NSAL1080Query
     */
    public static NSAL1080Query getInstance() {
        return INSTANCE;
    }

    /**
     * getCIInfo
     * @param cMsg NSAL1080CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCIInfo(NSAL1080CMsg cMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("custIncdtId", cMsg.custIncdtId.getValue());

        return getSsmEZDClient().queryEZDMsg("getCIInfo", params, cMsg);
    }
}
