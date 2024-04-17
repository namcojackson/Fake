/**
 * <pre>
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/08/2009   CUSA            S.Sugino        Create          N/A
 *</pre>
 */
package com.canon.cusa.s21.common.NWX.NWXC004001;

import business.db.CUST_CR_PRFLTMsg;
import business.db.CUST_CR_PRFLTMsgArray;

import com.canon.cusa.s21.framework.common.S21ApplicationCacheHolder;
import com.canon.cusa.s21.framework.common.collections.S21LRUMap;

/**
 * This class caches 'CUST_CR_PRFLTMsgArray' by S21LRUMap in
 * ThreadLocal to get high performance.
 * @author S.Sugino
 */
public final class NWXCustCrPrflTMsgThreadLocalCache extends NWXThreadLocalCacheBase {

    /**
     * Caching key class.<br>
     * 
     * <pre>
     * + Key - 1 
     *      created by 'GLBL_CMPY_CD', 'BILL_TO_CUST_CD'.
     * </pre>
     * 
     * @author S.Sugino
     */
    public static final class CustCrPrflTMsgCacheKey extends NWXThreadLocalCacheKeyBase {

        private CustCrPrflTMsgCacheKey(String glblCmpyCd, String billToCustCd) {

            StringBuilder sb = new StringBuilder(50);
            sb.append("glblCmpyCd=").append(glblCmpyCd).append(SEPARATOR);
            sb.append("billToCustCd=").append(billToCustCd).append(SEPARATOR);

            super.setKey(sb.toString());
        }
    }

    /**
     * my instance key in TreadLocal.
     */
    private static final String INSTANCE_KEY = NWXCustCrPrflTMsgThreadLocalCache.class.getName();

    /**
     * singleton instance getter.
     * @return NWXCmpsnTMsgThreadLocalCache
     */
    public static NWXCustCrPrflTMsgThreadLocalCache getInstance() {
        NWXCustCrPrflTMsgThreadLocalCache myInstance = (NWXCustCrPrflTMsgThreadLocalCache) S21ApplicationCacheHolder.get(INSTANCE_KEY);
        if (myInstance == null) {
            myInstance = new NWXCustCrPrflTMsgThreadLocalCache();
            S21ApplicationCacheHolder.put(INSTANCE_KEY, myInstance);
        }
        return myInstance;
    }

    /**
     * caching data. using LRU Algorithm.
     */
    private S21LRUMap<String, CUST_CR_PRFLTMsgArray> cacheMap = new S21LRUMap<String, CUST_CR_PRFLTMsgArray>(200);

    private NWXCustCrPrflTMsgThreadLocalCache() {
        super();
    }

    /**
     * cache data.
     * @param cacheKey CustCrPrflTMsgCacheKey
     * @param value CUST_CR_PRFLTMsgArray
     */
    public void cache(CustCrPrflTMsgCacheKey cacheKey, CUST_CR_PRFLTMsgArray value) {
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
     * @param billToCustCd BILL_TO_CUST_CD
     * @return CustCrPrflTMsgCacheKey
     */
    public CustCrPrflTMsgCacheKey createCacheKey(String glblCmpyCd, String billToCustCd) {
        return new CustCrPrflTMsgCacheKey(glblCmpyCd, billToCustCd);
    }

    private CUST_CR_PRFLTMsgArray findByCondition(String glblCmpyCd, String billToCustCd) {

        CUST_CR_PRFLTMsg reqTMsg = new CUST_CR_PRFLTMsg();
        reqTMsg.setSQLID("007");
        reqTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        reqTMsg.setConditionValue("billToCustCd01", billToCustCd);

        reqTMsg.setMaxCount(1);
        return (CUST_CR_PRFLTMsgArray) super.findByCondition(reqTMsg);
    }

    /**
     * get caching data.
     * @param cacheKey CustCrPrflTMsgCacheKey
     * @return CUST_CR_PRFLTMsgArray
     */
    public CUST_CR_PRFLTMsgArray get(CustCrPrflTMsgCacheKey cacheKey) {
        return cacheMap.get(cacheKey.getKey());
    }

    /**
     * get caching data.<br>
     * If it isn't caching data in S21LRUMap, this class search
     * 'CUST_CR_PRFL table' and cache it.<br>
     * 
     * <pre>
     * +[Search Condition]+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     *  SQLID="007"
     *  
     *  WHERE
     *          GLBL_CMPY_CD = ?glblCmpyCd01?
     *      AND BILL_TO_CUST_CD = ?billToCustCd01?
     *      AND EZCANCELFLAG = '0'
     * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     * </pre>
     * 
     * @param glblCmpyCd GLBL_CMPY_CD
     * @param billToCustCd BILL_TO_CUST_CD
     * @return CUST_CR_PRFLTMsgArray
     */
    public CUST_CR_PRFLTMsgArray get(String glblCmpyCd, String billToCustCd) {

        CUST_CR_PRFLTMsgArray resTMsgArray = null;

        CustCrPrflTMsgCacheKey cacheKey = createCacheKey(glblCmpyCd, billToCustCd);
        if (hasCache(cacheKey)) {
            resTMsgArray = get(cacheKey);
        } else {
            resTMsgArray = findByCondition(glblCmpyCd, billToCustCd);
        }

        cache(cacheKey, resTMsgArray);
        return resTMsgArray;
    }

    /**
     * has caching data?
     * @param cacheKey CustCrPrflTMsgCacheKey
     * @return true/yes, I has. false/no, I don't have.
     */
    public boolean hasCache(CustCrPrflTMsgCacheKey cacheKey) {
        return super.hasCache(cacheMap, cacheKey);
    }

    @Override
    public String toString() {
        return super.toString(cacheMap);
    }

}
