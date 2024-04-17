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

import business.db.MDSETMsg;
import business.db.ORD_TAKE_MDSETMsg;

import com.canon.cusa.s21.framework.common.S21ApplicationCacheHolder;
import com.canon.cusa.s21.framework.common.collections.S21LRUMap;

/**
 * This class caches 'MDSETMsg' by S21LRUMap in ThreadLocal to get
 * high performance.
 * @author K.Tajima
 */
public final class NWXMdseTMsgThreadLocalCache extends NWXThreadLocalCacheBase {

    /**
     * Caching key class.<br>
     * Key is created by 'GLBL_CMPY_CD' and 'MDSE_CD'.<br>
     * @author K.Tajima
     */
    public static final class MdseTMsgCacheKey extends NWXThreadLocalCacheKeyBase {

        private MdseTMsgCacheKey(String glblCmpyCd, String mdseCd) {

            StringBuilder sb = new StringBuilder(40);
            sb.append("glblCmpyCd=").append(glblCmpyCd).append(SEPARATOR);
            sb.append("mdseCd=").append(mdseCd).append(SEPARATOR);

            super.setKey(sb.toString());
        }
    }

    /**
     * my instance key in TreadLocal.
     */
    private static final String INSTANCE_KEY = NWXMdseTMsgThreadLocalCache.class.getName();

    /**
     * singleton instance getter.
     * @return NWXMdseTMsgThreadLocalCache
     */
    public static NWXMdseTMsgThreadLocalCache getInstance() {
        NWXMdseTMsgThreadLocalCache myInstance = (NWXMdseTMsgThreadLocalCache) S21ApplicationCacheHolder.get(INSTANCE_KEY);
        if (myInstance == null) {
            myInstance = new NWXMdseTMsgThreadLocalCache();
            S21ApplicationCacheHolder.put(INSTANCE_KEY, myInstance);
        }
        return myInstance;
    };

    /**
     * caching data. using LRU Algorithm.
     */
    private S21LRUMap<String, MDSETMsg> cacheMap = new S21LRUMap<String, MDSETMsg>(200);

    private NWXMdseTMsgThreadLocalCache() {
        super();
    }

    /**
     * cache data.
     * @param cacheKey MdseTMsgCacheKey
     * @param value MDSETMsg
     */
    public void cache(MdseTMsgCacheKey cacheKey, MDSETMsg value) {
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
     * @param mdseCd MDSE_CD
     * @return MdseTMsgCacheKey
     */
    public MdseTMsgCacheKey createCacheKey(String glblCmpyCd, String mdseCd) {
        return new MdseTMsgCacheKey(glblCmpyCd, mdseCd);
    }

    private MDSETMsg findByKey(String glblCmpyCd, String mdseCd) {

        MDSETMsg reqTMsg = new MDSETMsg();
        reqTMsg.glblCmpyCd.setValue(glblCmpyCd);
        reqTMsg.mdseCd.setValue(mdseCd);

        return (MDSETMsg) super.findByKey(reqTMsg);
    }

    /**
     * get caching data.
     * @param cacheKey MdseTMsgCacheKey
     * @return MDSETMsg
     */
    public MDSETMsg get(MdseTMsgCacheKey cacheKey) {
        return cacheMap.get(cacheKey.getKey());
    }

    /**
     * get caching data.<br>
     * If it isn't caching data in S21LRUMap, this class search 'MDSE
     * table' and cache it.<br>
     * 
     * <pre>
     * +[Search Condition]+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     *  MDSETMsg reqTMsg = new MDSETMsg();
     *  reqTMsg.glblCmpyCd.setValue(glblCmpyCd);
     *  reqTMsg.mdseCd.setValue(mdseCd);
     * 
     *  (MDSETMsg) S21FastTBLAccessor.findByKey(reqTMsg);
     * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     * </pre>
     * 
     * @param glblCmpyCd GLBL_CMPY_CD
     * @param mdseCd MDSE_CD
     * @return MDSETMsg
     */
    public MDSETMsg get(String glblCmpyCd, String mdseCd) {

        MDSETMsg resTMsg = null;

        MdseTMsgCacheKey cacheKey = createCacheKey(glblCmpyCd, mdseCd);
        if (hasCache(cacheKey)) {
            resTMsg = get(cacheKey);
        } else {
            // ORD_TAKE_MDSE_CD
            ORD_TAKE_MDSETMsg ordTakeMdseTMsg = NWXOrdTakeMdseTMsgThreadLocalCache.getInstance().get(glblCmpyCd, mdseCd);
            if (ordTakeMdseTMsg != null) {
                String _mdseCd = ordTakeMdseTMsg.mdseCd.getValue();
                MdseTMsgCacheKey _cacheKey = createCacheKey(glblCmpyCd, _mdseCd);
                if (hasCache(_cacheKey)) {
                    resTMsg = get(_cacheKey);
                } else {
                    resTMsg = findByKey(glblCmpyCd, _mdseCd);
                }
                cache(_cacheKey, resTMsg);
                // MDSE_CD
            } else {
                resTMsg = findByKey(glblCmpyCd, mdseCd);
            }
        }

        cache(cacheKey, resTMsg);
        return resTMsg;
    }

    /**
     * has caching data?
     * @param cacheKey MdseTMsgCacheKey
     * @return true/yes, I has. false/no, I don't have.
     */
    public boolean hasCache(MdseTMsgCacheKey cacheKey) {
        return super.hasCache(cacheMap, cacheKey);
    }

    @Override
    public String toString() {
        return super.toString(cacheMap);
    }

}
