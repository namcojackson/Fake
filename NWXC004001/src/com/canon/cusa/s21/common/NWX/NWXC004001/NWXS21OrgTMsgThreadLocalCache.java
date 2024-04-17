/**
 * <pre>
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/01/2010   Fujitsu         I.Yanagita      Create          N/A
 *</pre>
 */
package com.canon.cusa.s21.common.NWX.NWXC004001;

import business.db.S21_ORGTMsg;

import com.canon.cusa.s21.framework.common.S21ApplicationCacheHolder;
import com.canon.cusa.s21.framework.common.collections.S21LRUMap;

/**
 * This class caches 'S21_ORGTMsg' by S21LRUMap in ThreadLocal to get
 * high performance.
 * @author K.Tajima
 */
public final class NWXS21OrgTMsgThreadLocalCache extends NWXThreadLocalCacheBase {

    /**
     * Caching key class.<br>
     * Key is created by 'GLBL_CMPY_CD' and 'TOC_CD'.<br>
     * @author K.Tajima
     */
    public static final class S21OrgTMsgCacheKey extends NWXThreadLocalCacheKeyBase {

        private S21OrgTMsgCacheKey(String glblCmpyCd, String tocCd) {

            StringBuilder sb = new StringBuilder(40);
            sb.append("glblCmpyCd=").append(glblCmpyCd).append(SEPARATOR);
            sb.append("tocCd=").append(tocCd).append(SEPARATOR);

            super.setKey(sb.toString());
        }
    }

    /**
     * my instance key in TreadLocal.
     */
    private static final String INSTANCE_KEY = NWXS21OrgTMsgThreadLocalCache.class.getName();

    /**
     * singleton instance getter.
     * @return NWXS21OrgTMsgThreadLocalCache
     */
    public static NWXS21OrgTMsgThreadLocalCache getInstance() {
        NWXS21OrgTMsgThreadLocalCache myInstance = (NWXS21OrgTMsgThreadLocalCache) S21ApplicationCacheHolder.get(INSTANCE_KEY);
        if (myInstance == null) {
            myInstance = new NWXS21OrgTMsgThreadLocalCache();
            S21ApplicationCacheHolder.put(INSTANCE_KEY, myInstance);
        }
        return myInstance;
    };

    /**
     * caching data. using LRU Algorithm.
     */
    private S21LRUMap<String, S21_ORGTMsg> cacheMap = new S21LRUMap<String, S21_ORGTMsg>(200);

    private NWXS21OrgTMsgThreadLocalCache() {
        super();
    }

    /**
     * cache data.
     * @param cacheKey S21OrgTMsgCacheKey
     * @param value S21_ORGTMsg
     */
    public void cache(S21OrgTMsgCacheKey cacheKey, S21_ORGTMsg value) {
        if (value != null) {
            cacheMap.put(cacheKey.getKey(), value);
        }
    }

    @Override
    public void clear() {
        cacheMap.clear();
    }

    /**
     * create caching key.
     * @param glblCmpyCd GLBL_CMPY_CD
     * @param tocCd TOC_CD
     * @return S21OrgTMsgCacheKey
     */
    public S21OrgTMsgCacheKey createCacheKey(String glblCmpyCd, String tocCd) {
        return new S21OrgTMsgCacheKey(glblCmpyCd, tocCd);
    }

    private S21_ORGTMsg findByKey(String glblCmpyCd, String tocCd) {

        S21_ORGTMsg reqTMsg = new S21_ORGTMsg();
        reqTMsg.glblCmpyCd.setValue(glblCmpyCd);
        reqTMsg.tocCd.setValue(tocCd);

        return (S21_ORGTMsg) super.findByKey(reqTMsg);
    }

    /**
     * get caching data.
     * @param cacheKey S21OrgTMsgCacheKey
     * @return S21_ORGTMsg
     */
    public S21_ORGTMsg get(S21OrgTMsgCacheKey cacheKey) {
        return cacheMap.get(cacheKey.getKey());
    }

    /**
     * get caching data.<br>
     * If it isn't caching data in S21LRUMap, this class search 'S21_ORG
     * table' and cache it.<br>
     * 
     * <pre>
     * +[Search Condition]+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     *  S21_ORGTMsg reqTMsg = new S21_ORGTMsg();
     *  reqTMsg.glblCmpyCd.setValue(glblCmpyCd);
     *  reqTMsg.tocCd.setValue(tocCd);
     * 
     *  (S21_ORGTMsg) S21FastTBLAccessor.findByKey(reqTMsg);
     * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     * </pre>
     * 
     * @param glblCmpyCd GLBL_CMPY_CD
     * @param tocCd TOC_CD
     * @return S21_ORGTMsg
     */
    public S21_ORGTMsg get(String glblCmpyCd, String tocCd) {

        S21_ORGTMsg resTMsg = null;

        S21OrgTMsgCacheKey cacheKey = createCacheKey(glblCmpyCd, tocCd);
        if (hasCache(cacheKey)) {
            resTMsg = get(cacheKey);
        } else {
            resTMsg = findByKey(glblCmpyCd, tocCd);
        }

        cache(cacheKey, resTMsg);
        return resTMsg;
    }

    /**
     * has caching data?
     * @param cacheKey S21OrgTMsgCacheKey
     * @return true/yes, I has. false/no, I don't have.
     */
    public boolean hasCache(S21OrgTMsgCacheKey cacheKey) {
        return super.hasCache(cacheMap, cacheKey);
    }

    @Override
    public String toString() {
        return super.toString(cacheMap);
    }

}
