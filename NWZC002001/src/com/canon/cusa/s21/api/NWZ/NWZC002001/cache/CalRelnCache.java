/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC002001.cache;

import parts.common.EZDTMsg;
import parts.common.EZDTMsgArray;
import business.db.CAL_RELNTMsg;
import business.db.CAL_RELNTMsgArray;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.common.S21ApplicationCacheHolder;
import com.canon.cusa.s21.framework.common.collections.S21LRUMap;
import com.canon.cusa.s21.framework.log.S21AbendException;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/19   Fujitsu         T.Yoshida       Create          S21_NA#8166 (For Performance)
 * </pre>
 */
public final class CalRelnCache extends LocalDataCacheBase {

    /** My Instance Key in TreadLocal */
    private static final String INSTANCE_KEY = CalRelnCache.class.getName();

    /** S21LRUMap */
    private final S21LRUMap<String, EZDTMsg> tMsgCache = new S21LRUMap<String, EZDTMsg>();

    /** S21LRUMap */
    private final S21LRUMap<String, EZDTMsgArray> tMsgArrayCache = new S21LRUMap<String, EZDTMsgArray>();

    /** S21LRUMap */
    private final S21LRUMap<String, Boolean> bizDayCache = new S21LRUMap<String, Boolean>();

    /** Private Constructor */
    private CalRelnCache() {
    }

    /**
     * Get Singleton Instance
     * @return CalRelnCache
     */
    public static CalRelnCache getInstance() {

        CalRelnCache myInstance = (CalRelnCache) S21ApplicationCacheHolder.get(INSTANCE_KEY);
        if (myInstance == null) {
            myInstance = new CalRelnCache();
            S21ApplicationCacheHolder.put(INSTANCE_KEY, myInstance);
        }
        return myInstance;
    }

    /**
     * Get CAL_RELNTMsg
     * @param glblCmpyCd Global Company Code
     * @param calSubTpCd Calendar Sub Type Code
     * @param calMultCd Calendar Mult Code
     * @return CAL_RELNTMsg
     */
    public CAL_RELNTMsg getTMsgByKey(String glblCmpyCd, String calSubTpCd, String calMultCd) {

        final StringBuilder sb = new StringBuilder();
        sb.append(glblCmpyCd).append(",");
        sb.append(calSubTpCd).append(",");
        sb.append(calMultCd);

        final String cacheKey = sb.toString();

        EZDTMsg resTMsg = tMsgCache.get(cacheKey);

        if (resTMsg == null) {

            // find by key
            final CAL_RELNTMsg reqTMsg = new CAL_RELNTMsg();
            reqTMsg.glblCmpyCd.setValue(glblCmpyCd);
            reqTMsg.calSubTpCd.setValue(calSubTpCd);
            reqTMsg.calMultCd.setValue(calMultCd);

            resTMsg = super.findByKey(reqTMsg);
            if (resTMsg == null) {
                tMsgCache.put(cacheKey, new CAL_RELNTMsg());
                return null;
            }

            tMsgCache.put(cacheKey, resTMsg);
            return (CAL_RELNTMsg) resTMsg;
        }

        CAL_RELNTMsg calRelnTMsg = (CAL_RELNTMsg) resTMsg;

        if (ZYPCommonFunc.hasValue(calRelnTMsg.glblCmpyCd)) {
            return calRelnTMsg;
        }

        return null;
    }

    /**
     * Get CAL_RELNTMsgArray
     * @param findCondition FindCondition
     * @return CAL_RELNTMsgArray
     */
    public CAL_RELNTMsgArray getTMsgArray(FindCondition findCondition) {
        return (CAL_RELNTMsgArray) super.getTMsgArray(CAL_RELNTMsg.class, findCondition, tMsgArrayCache);
    }

    /**
     * Check Business Day
     * @param glblCmpyCd Global Company Code
     * @param calTpCd Calendar Type Code
     * @param yyyyMMdd Check Date
     * @return Check Result
     */
    public boolean isBizDay(String glblCmpyCd, String calTpCd, String yyyyMMdd) {

        final StringBuilder sb = new StringBuilder();
        sb.append(glblCmpyCd).append(",");
        sb.append(calTpCd).append(",");
        sb.append(yyyyMMdd);

        final String cacheKey = sb.toString();

        Boolean isBizDay = bizDayCache.get(cacheKey);

        if (isBizDay == null) {
            try {
                isBizDay = ZYPDateUtil.isBusinessDayEx(glblCmpyCd, calTpCd, yyyyMMdd);
            } catch (S21AbendException e) {
                isBizDay = false;
            }

            bizDayCache.put(cacheKey, isBizDay);
        }

        return isBizDay;
    }
}
