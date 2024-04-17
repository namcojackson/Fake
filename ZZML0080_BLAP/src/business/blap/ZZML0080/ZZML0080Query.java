/*
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * Original Author: Tak Yoshida
 * Company: SRA AMERICA, Inc.
 * Date: Apr 30, 2009
 */
package business.blap.ZZML0080;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * @author q02673
 */
public final class ZZML0080Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final ZZML0080Query INSTANCE = new ZZML0080Query();

    /**
     * Constructor.
     */
    private ZZML0080Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return ZZML0080Query
     */
    public static ZZML0080Query getInstance() {
        return INSTANCE;
    }

    /**
     * @param cMsg ZZML0080CMsg
     * @param sMsg ZZML0080SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getMlGrpAddr(ZZML0080CMsg cMsg, ZZML0080SMsg sMsg) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        map.put("mlGrpId", cMsg.mlGrpId.getValue());
        map.put("mlGrpNm", cMsg.mlGrpNm.getValue());
        //map.put("rowNumMin", cMsg.xxPageShowFromNum.getValue());
        //map.put("rowNumMax", cMsg.xxPageShowToNum.getValue());
        map.put("rowNum", cMsg.A.length() + 1);

        return getSsmEZDClient().queryEZDMsgArray("getMlGrpAddr", map, sMsg.A);
    }
}
