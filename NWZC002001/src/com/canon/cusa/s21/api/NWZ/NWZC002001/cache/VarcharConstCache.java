/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC002001.cache;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.common.S21ApplicationCacheHolder;
import com.canon.cusa.s21.framework.common.collections.S21LRUMap;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/08/02   Fujitsu         T.Mizuki        Create          S21_NA#5823 (For Performance)
 * </pre>
 */
public final class VarcharConstCache extends LocalDataCacheBase {

    /** My Instance Key in TreadLocal */
    private static final String INSTANCE_KEY = VarcharConstCache.class.getName();

    /** S21LRUMap */
    private final S21LRUMap<String, String> varcharConstCache = new S21LRUMap<String, String>();

    /** Private Constructor */
    private VarcharConstCache() {
    }

    /**
     * Get Singleton Instance
     * @return VarcharConstCache
     */
    public static VarcharConstCache getInstance() {

        VarcharConstCache myInstance = (VarcharConstCache) S21ApplicationCacheHolder.get(INSTANCE_KEY);
        if (myInstance == null) {
            myInstance = new VarcharConstCache();
            S21ApplicationCacheHolder.put(INSTANCE_KEY, myInstance);
        }
        return myInstance;
    }

    /**
     * Get varCharConst
     * @param glblCmpyCd Global Company Code
     * @param varCharConstNm String
     * @return String
     */
    public String getVarCharConst(String glblCmpyCd, String varCharConstNm) {

        String resSt = varcharConstCache.get(varCharConstNm);

        if (!ZYPCommonFunc.hasValue(resSt)) {
            resSt = ZYPCodeDataUtil.getVarCharConstValue(varCharConstNm, glblCmpyCd);
            if (ZYPCommonFunc.hasValue(resSt)) {
                varcharConstCache.put(varCharConstNm, resSt);
            }
        }

        return resSt;
    }
}
