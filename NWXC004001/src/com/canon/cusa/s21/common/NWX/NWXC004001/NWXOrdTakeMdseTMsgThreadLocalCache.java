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

import business.db.ORD_TAKE_MDSETMsg;

import com.canon.cusa.s21.framework.common.S21ApplicationCacheHolder;
import com.canon.cusa.s21.framework.common.collections.S21LRUMap;

/**
 * This class caches 'ORD_TAKE_MDSETMsg' by S21LRUMap in ThreadLocal
 * to get high performance.
 * @author K.Tajima
 */
public final class NWXOrdTakeMdseTMsgThreadLocalCache extends NWXThreadLocalCacheBase {

    /**
     * Caching key class.<br>
     * Key is created by 'GLBL_CMPY_CD' and 'ORD_TAKE_MDSE_CD'.<br>
     * @author K.Tajima
     */
    public static final class OrdTakeMdseTMsgCacheKey extends NWXThreadLocalCacheKeyBase {

        private OrdTakeMdseTMsgCacheKey(String glblCmpyCd, String ordTakeMdseCd) {

            StringBuilder sb = new StringBuilder(39);
            sb.append("glblCmpyCd=").append(glblCmpyCd).append(SEPARATOR);
            sb.append("ordTakeMdseCd=").append(ordTakeMdseCd).append(SEPARATOR);

            super.setKey(sb.toString());
        }
    }

    /**
     * digit of 'ORD_TAKE_MDSE_CD'.
     */
    private static final int ORD_TAKE_MDSE_CD_DIGIT = new ORD_TAKE_MDSETMsg().getAttr("ordTakeMdseCd").getDigit();

    /**
     * my instance key in TreadLocal.
     */
    private static final String INSTANCE_KEY = NWXOrdTakeMdseTMsgThreadLocalCache.class.getName();

    /**
     * singleton instance getter.
     * @return NWXOrdTakeMdseTMsgThreadLocalCache
     */
    public static NWXOrdTakeMdseTMsgThreadLocalCache getInstance() {
        NWXOrdTakeMdseTMsgThreadLocalCache myInstance = (NWXOrdTakeMdseTMsgThreadLocalCache) S21ApplicationCacheHolder.get(INSTANCE_KEY);
        if (myInstance == null) {
            myInstance = new NWXOrdTakeMdseTMsgThreadLocalCache();
            S21ApplicationCacheHolder.put(INSTANCE_KEY, myInstance);
        }
        return myInstance;
    }

    /**
     * caching data. using LRU Algorithm.
     */
    private S21LRUMap<String, ORD_TAKE_MDSETMsg> cacheMap = new S21LRUMap<String, ORD_TAKE_MDSETMsg>(200);

    private NWXOrdTakeMdseTMsgThreadLocalCache() {
        super();
    }

    /**
     * cache data.
     * @param cacheKey OrdTakeMdseTMsgCacheKey
     * @param value ORD_TAKE_MDSETMsg
     */
    public void cache(OrdTakeMdseTMsgCacheKey cacheKey, ORD_TAKE_MDSETMsg value) {
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
     * @param ordTakeMdseCd ORD_TAKE_MDSE_CD
     * @return OrdTakeMdseTMsgCacheKey
     */
    private OrdTakeMdseTMsgCacheKey createCacheKey(String glblCmpyCd, String ordTakeMdseCd) {
        return new OrdTakeMdseTMsgCacheKey(glblCmpyCd, ordTakeMdseCd);
    }

    private ORD_TAKE_MDSETMsg findByKey(String glblCmpyCd, String ordTakeMdseCd) {

        ORD_TAKE_MDSETMsg reqTMsg = new ORD_TAKE_MDSETMsg();
        reqTMsg.glblCmpyCd.setValue(glblCmpyCd);
        reqTMsg.ordTakeMdseCd.setValue(ordTakeMdseCd);

        return (ORD_TAKE_MDSETMsg) super.findByKey(reqTMsg);
    }

    /**
     * get caching data.
     * @param cacheKey OrdTakeMdseTMsgCacheKey
     * @return ORD_TAKE_MDSETMsg
     */
    public ORD_TAKE_MDSETMsg get(OrdTakeMdseTMsgCacheKey cacheKey) {
        return cacheMap.get(cacheKey.getKey());
    }

    /**
     * get caching data.<br>
     * If it isn't caching data in S21LRUMap, this class search
     * 'ORD_TAKE_MDSE table' and cache it.<br>
     * 
     * <pre>
     * +[Search Condition]+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     *  ORD_TAKE_MDSETMsg reqTMsg = new ORD_TAKE_MDSETMsg();
     *  reqTMsg.glblCmpyCd.setValue(glblCmpyCd);
     *  reqTMsg.ordTakeMdseCd.setValue(ordTakeMdseCd);
     * 
     *  (ORD_TAKE_MDSETMsg) S21FastTBLAccessor.findByKey(reqTMsg);
     * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     * </pre>
     * 
     * @param glblCmpyCd GLBL_CMPY_CD
     * @param ordTakeMdseCd ORD_TAKE_MDSE_CD
     * @return ORD_TAKE_MDSETMsg
     */
    public ORD_TAKE_MDSETMsg get(String glblCmpyCd, String ordTakeMdseCd) {

        ORD_TAKE_MDSETMsg resTMsg = null;

        OrdTakeMdseTMsgCacheKey cacheKey = createCacheKey(glblCmpyCd, ordTakeMdseCd);
        if (hasCache(cacheKey)) {
            resTMsg = get(cacheKey);
        } else {
            if (ORD_TAKE_MDSE_CD_DIGIT >= ordTakeMdseCd.length()) {
                resTMsg = findByKey(glblCmpyCd, ordTakeMdseCd);
            }
        }

        cache(cacheKey, resTMsg);
        return resTMsg;
    }

    /**
     * has caching data?
     * @param cacheKey OrdTakeMdseTMsgCacheKey
     * @return true/yes, I has. false/no, I don't have.
     */
    public boolean hasCache(OrdTakeMdseTMsgCacheKey cacheKey) {
        return super.hasCache(cacheMap, cacheKey);
    }

    @Override
    public String toString() {
        return super.toString(cacheMap);
    }

}
