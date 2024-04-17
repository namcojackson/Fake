/**
 * <pre>
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/26/2009   CUSA            K.Tajima        Create          N/A
 *</pre>
 */
package com.canon.cusa.s21.common.NWX.NWXC004001;

import business.db.HLD_RSNTMsg;

import com.canon.cusa.s21.framework.common.S21ApplicationCacheHolder;
import com.canon.cusa.s21.framework.common.collections.S21LRUMap;

/**
 * This class caches 'HLD_RSNTMsg' by S21LRUMap in ThreadLocal to get
 * high performance.
 * @author K.Tajima
 */
public final class NWXHldRsnTMsgThreadLocalCache extends NWXThreadLocalCacheBase {

    /**
     * Caching key class.<br>
     * Key is created by 'GLBL_CMPY_CD' and 'HLD_RSN_CD'.<br>
     * @author K.Tajima
     */
    public static final class HldRsnTMsgCacheKey extends NWXThreadLocalCacheKeyBase {

        private HldRsnTMsgCacheKey(String glblCmpyCd, String hldRsnCd) {

            StringBuilder sb = new StringBuilder(29);
            sb.append("glblCmpyCd=").append(glblCmpyCd).append(SEPARATOR);
            sb.append("hldRsnCd=").append(hldRsnCd).append(SEPARATOR);

            super.setKey(sb.toString());
        }
    }

    /**
     * my instance key in TreadLocal.
     */
    private static final String INSTANCE_KEY = NWXHldRsnTMsgThreadLocalCache.class.getName();

    /**
     * singleton instance getter.
     * @return NWXHldRsnTMsgThreadLocalCache
     */
    public static NWXHldRsnTMsgThreadLocalCache getInstance() {
        NWXHldRsnTMsgThreadLocalCache myInstance = (NWXHldRsnTMsgThreadLocalCache) S21ApplicationCacheHolder.get(INSTANCE_KEY);
        if (myInstance == null) {
            myInstance = new NWXHldRsnTMsgThreadLocalCache();
            S21ApplicationCacheHolder.put(INSTANCE_KEY, myInstance);
        }
        return myInstance;
    };

    /**
     * caching data. using LRU Algorithm.
     */
    private S21LRUMap<String, HLD_RSNTMsg> cacheMap = new S21LRUMap<String, HLD_RSNTMsg>(200);

    private NWXHldRsnTMsgThreadLocalCache() {
        super();
    }

    /**
     * cache data.
     * @param cacheKey HldRsnTMsgCacheKey
     * @param value HLD_RSNTMsg
     */
    public void cache(HldRsnTMsgCacheKey cacheKey, HLD_RSNTMsg value) {
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
     * @param hldRsnCd HLD_RSN_CD
     * @return HldRsnTMsgCacheKey
     */
    public HldRsnTMsgCacheKey createCacheKey(String glblCmpyCd, String hldRsnCd) {
        return new HldRsnTMsgCacheKey(glblCmpyCd, hldRsnCd);
    }

    private HLD_RSNTMsg findByKey(String glblCmpyCd, String hldRsnCd) {

        HLD_RSNTMsg reqTMsg = new HLD_RSNTMsg();
        reqTMsg.glblCmpyCd.setValue(glblCmpyCd);
        reqTMsg.hldRsnCd.setValue(hldRsnCd);

        return (HLD_RSNTMsg) super.findByKey(reqTMsg);
    }

    /**
     * get caching data.
     * @param cacheKey HldRsnTMsgCacheKey
     * @return HLD_RSNTMsg
     */
    public HLD_RSNTMsg get(HldRsnTMsgCacheKey cacheKey) {
        return cacheMap.get(cacheKey.getKey());
    }

    /**
     * get caching data.<br>
     * If it isn't caching data in S21LRUMap, this class search
     * 'HLD_RSN table' and cache it.<br>
     * 
     * <pre>
     * +[Search Condition]+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     *  HLD_RSNTMsg reqTMsg = new HLD_RSNTMsg();
     *  reqTMsg.glblCmpyCd.setValue(glblCmpyCd);
     *  reqTMsg.hldRsnCd.setValue(hldRsnCd);
     * 
     *  (HLD_RSNTMsg) S21ApiTBLAccessor.findByKey(reqTMsg);
     * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     * </pre>
     * 
     * @param glblCmpyCd GLBL_CMPY_CD
     * @param hldRsnCd HLD_RSN_CD
     * @return HLD_RSNTMsg
     */
    public HLD_RSNTMsg get(String glblCmpyCd, String hldRsnCd) {

        HLD_RSNTMsg resTMsg = null;

        HldRsnTMsgCacheKey cacheKey = createCacheKey(glblCmpyCd, hldRsnCd);
        if (hasCache(cacheKey)) {
            resTMsg = get(cacheKey);
        } else {
            resTMsg = findByKey(glblCmpyCd, hldRsnCd);
        }

        cache(cacheKey, resTMsg);
        return resTMsg;
    }

    /**
     * has caching data?
     * @param cacheKey HldRsnTMsgCacheKey
     * @return true/yes, I has. false/no, I don't have.
     */
    public boolean hasCache(HldRsnTMsgCacheKey cacheKey) {
        return super.hasCache(cacheMap, cacheKey);
    }

    @Override
    public String toString() {
        return super.toString(cacheMap);
    }

}
