/*
 * <pre>Copyright (c) 2018 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC002001.cache;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.common.S21ApplicationCacheHolder;
import com.canon.cusa.s21.framework.common.collections.S21LRUMap;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/06/15   Fujitsu         K.Ishizuka      Create          S21_NA#24294 (For Performance)
 * </pre>
 */
public class AddBizDayCache {

    /** My Instance Key in TreadLocal */
    private static final String INSTANCE_KEY = AddBizDayCache.class.getName();

    /** S21LRUMap */
    private final S21LRUMap<String, String> bizDayCache = new S21LRUMap<String, String>();

    /** Private Constructor */
    private AddBizDayCache() {
    }

    /**
     * Get Singleton Instance
     * @return AddBizDayCache
     */
    public static AddBizDayCache getInstance() {

        AddBizDayCache myInstance = (AddBizDayCache) S21ApplicationCacheHolder.get(INSTANCE_KEY);
        if (myInstance == null) {
            myInstance = new AddBizDayCache();
            S21ApplicationCacheHolder.put(INSTANCE_KEY, myInstance);
        }
        return myInstance;
    }

    public String addBizDay(String glblCmpyCd, String calTpCd, String yyyyMMdd, int amount) {
        String key = createCacheKey(glblCmpyCd, calTpCd, yyyyMMdd, amount);
        String bizDay = bizDayCache.get(key);
        if (bizDay == null) {
            bizDay = ZYPDateUtil.addBusinessDayEx(glblCmpyCd, calTpCd, yyyyMMdd, amount);
            if (bizDay == null) {
                bizDay = yyyyMMdd;
            }
            bizDayCache.put(key, bizDay);
        }
        return bizDay;
    }

    private String createCacheKey(String glblCmpyCd, String calTpCd, String yyyyMMdd, int amount) {
        final StringBuilder sb = new StringBuilder(256);
        sb.append(glblCmpyCd);
        sb.append(",");
        sb.append(calTpCd);
        sb.append(",");
        sb.append(yyyyMMdd);
        sb.append(",");
        sb.append(String.valueOf(amount));
        return sb.toString();
    }

}
