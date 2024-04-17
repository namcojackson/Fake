/**
 * <pre>
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/26/2009   CUSA            K.Tajima        Create          N/A
 * 11/09/2009   CUSA            S.Sugino        Update          N/A. To sort it in the call origin, it deletes it.
 *</pre>
 */
package com.canon.cusa.s21.common.NWX.NWXC004001;

import business.db.CMPSNTMsg;
import business.db.CMPSNTMsgArray;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_CMPSN_TP;
import com.canon.cusa.s21.framework.common.S21ApplicationCacheHolder;
import com.canon.cusa.s21.framework.common.collections.S21LRUMap;

/**
 * This class caches 'CMPSNTMsgArray' by S21LRUMap in ThreadLocal to
 * get high performance.
 * @author K.Tajima
 */
public final class NWXCmpsnTMsgThreadLocalCache extends NWXThreadLocalCacheBase {

    /**
     * Caching key class.<br>
     * 
     * <pre>
     * + Key - 1 
     *      created by 'GLBL_CMPY_CD', 'PRNT_MDSE_CD', 'EFF_FROM_DT' and 'EFF_THRU_DT'.
     * + Key - 2
     *      created by 'GLBL_CMPY_CD', 'PRNT_MDSE_CD', 'CHILD_MDSE_CD', 'EFF_FROM_DT' and 'EFF_THRU_DT'.
     * </pre>
     * 
     * @author K.Tajima
     */
    public static final class CmpsnTMsgCacheKey extends NWXThreadLocalCacheKeyBase {

        private CmpsnTMsgCacheKey(String glblCmpyCd, String prntMdseCd, String effFromDt, String effThruDt) {

            StringBuilder sb = new StringBuilder();
            sb.append("glblCmpyCd=").append(glblCmpyCd).append(SEPARATOR);
            sb.append("prntMdseCd=").append(prntMdseCd).append(SEPARATOR);
            sb.append("effFromDt=").append(effFromDt).append(SEPARATOR);
            sb.append("effThruDt=").append(effThruDt).append(SEPARATOR);

            super.setKey(sb.toString());
        }

        private CmpsnTMsgCacheKey(String glblCmpyCd, String prntMdseCd, String childMdseCd, String effFromDt, String effThruDt) {

            StringBuilder sb = new StringBuilder();
            sb.append("glblCmpyCd=").append(glblCmpyCd).append(SEPARATOR);
            sb.append("prntMdseCd=").append(prntMdseCd).append(SEPARATOR);
            sb.append("childMdseCd=").append(childMdseCd).append(SEPARATOR);
            sb.append("effFromDt=").append(effFromDt).append(SEPARATOR);
            sb.append("effThruDt=").append(effThruDt).append(SEPARATOR);

            super.setKey(sb.toString());
        }
    }

    /**
     * my instance key in TreadLocal.
     */
    private static final String INSTANCE_KEY = NWXCmpsnTMsgThreadLocalCache.class.getName();

    /**
     * singleton instance getter.
     * @return NWXCmpsnTMsgThreadLocalCache
     */
    public static NWXCmpsnTMsgThreadLocalCache getInstance() {
        NWXCmpsnTMsgThreadLocalCache myInstance = (NWXCmpsnTMsgThreadLocalCache) S21ApplicationCacheHolder.get(INSTANCE_KEY);
        if (myInstance == null) {
            myInstance = new NWXCmpsnTMsgThreadLocalCache();
            S21ApplicationCacheHolder.put(INSTANCE_KEY, myInstance);
        }
        return myInstance;
    }

    /**
     * caching data. using LRU Algorithm.
     */
    private S21LRUMap<String, CMPSNTMsgArray> cacheMap = new S21LRUMap<String, CMPSNTMsgArray>(200);

    private NWXCmpsnTMsgThreadLocalCache() {
        super();
    }

    /**
     * cache data.
     * @param cacheKey CmpsnTMsgCacheKey
     * @param value CMPSNTMsgArray
     */
    public void cache(CmpsnTMsgCacheKey cacheKey, CMPSNTMsgArray value) {
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
     * @param prntMdseCd PRNT_MDSE_CD
     * @param childMdseCd CHILD_MDSE_CD
     * @param effFromDt EFF_FROM_DT
     * @param effThruDt EFF_THRU_DT
     * @return CmpsnTMsgCacheKey
     */
    public CmpsnTMsgCacheKey createCacheKeyByChildMdseCd(String glblCmpyCd, String prntMdseCd, String childMdseCd, String effFromDt, String effThruDt) {
        return new CmpsnTMsgCacheKey(glblCmpyCd, prntMdseCd, childMdseCd, effFromDt, effThruDt);
    }

    /**
     * create caching key.
     * @param glblCmpyCd GLBL_CMPY_CD
     * @param prntMdseCd PRNT_MDSE_CD
     * @param effFromDt EFF_FROM_DT
     * @param effThruDt EFF_THRU_DT
     * @return CmpsnTMsgCacheKey
     */
    public CmpsnTMsgCacheKey createCacheKeyByPrntMdseCd(String glblCmpyCd, String prntMdseCd, String effFromDt, String effThruDt) {
        return new CmpsnTMsgCacheKey(glblCmpyCd, prntMdseCd, effFromDt, effThruDt);
    }

    private CMPSNTMsgArray findByChildMdseCd(String glblCmpyCd, String prntMdseCd, String childMdseCd, String effFromDt, String effThruDt) {

        CMPSNTMsg reqTMsg = new CMPSNTMsg();
        reqTMsg.setSQLID("003");
        reqTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        reqTMsg.setConditionValue("prntMdseCd01", prntMdseCd);
        reqTMsg.setConditionValue("childOrdTakeMdseCd01A", childMdseCd);
        reqTMsg.setConditionValue("childOrdTakeMdseCd01B", childMdseCd);
        reqTMsg.setConditionValue("effFromDt01", effFromDt);
        reqTMsg.setConditionValue("effThruDt01", effThruDt);

        return (CMPSNTMsgArray) super.findByCondition(reqTMsg);
    }

    @SuppressWarnings("unchecked")
    private CMPSNTMsgArray findByPrntMdseCd(String glblCmpyCd, String prntMdseCd, String effFromDt, String effThruDt) {

        CMPSNTMsg reqTMsg = new CMPSNTMsg();
        reqTMsg.setSQLID("002");
        reqTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        reqTMsg.setConditionValue("prntMdseCd01", prntMdseCd);
        reqTMsg.setConditionValue("mdseCmpsnTpCd01A", MDSE_CMPSN_TP.SET_MDSE);
        reqTMsg.setConditionValue("mdseCmpsnTpCd01B", MDSE_CMPSN_TP.SET_ORDERTAKE_MDSE);
        reqTMsg.setConditionValue("effFromDt01", effFromDt);
        reqTMsg.setConditionValue("effThruDt01", effThruDt);

        return (CMPSNTMsgArray) super.findByCondition(reqTMsg);
    }

    /**
     * get caching data.
     * @param cacheKey CmpsnTMsgCacheKey
     * @return CMPSNTMsgArray
     */
    public CMPSNTMsgArray get(CmpsnTMsgCacheKey cacheKey) {
        return cacheMap.get(cacheKey.getKey());
    }

    /**
     * get caching data.<br>
     * If it isn't caching data in S21LRUMap, this class search 'CMPSN
     * table' and cache it.<br>
     * 
     * <pre>
     * +[Search Condition]+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     *  SQLID="002"
     *  
     *  WHERE
     *          GLBL_CMPY_CD = ?glblCmpyCd01?
     *      AND PRNT_MDSE_CD = ?prntMdseCd01?
     *      AND ( MDSE_CMPSN_TP_CD = ?mdseCmpsnTpCd01A? OR MDSE_CMPSN_TP_CD = ?mdseCmpsnTpCd01B?)
     *      AND EFF_FROM_DT <= ?effFromDt01?
     *      AND EFF_THRU_DT >= ?effThruDt01?
     *      AND EZCANCELFLAG = '0'
     * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     * </pre>
     * 
     * @param glblCmpyCd GLBL_CMPY_CD
     * @param prntMdseCd PRNT_MDSE_CD
     * @param effFromDt EFF_FROM_DT
     * @param effThruDt EFF_THRU_DT
     * @return CMPSNTMsgArray
     */
    public CMPSNTMsgArray get(String glblCmpyCd, String prntMdseCd, String effFromDt, String effThruDt) {

        CMPSNTMsgArray resTMsgArray = null;

        CmpsnTMsgCacheKey cacheKey = createCacheKeyByPrntMdseCd(glblCmpyCd, prntMdseCd, effFromDt, effThruDt);
        if (hasCache(cacheKey)) {
            resTMsgArray = get(cacheKey);
        } else {
            resTMsgArray = findByPrntMdseCd(glblCmpyCd, prntMdseCd, effFromDt, effThruDt);
        }

        cache(cacheKey, resTMsgArray);
        return resTMsgArray;
    }

    /**
     * get caching data.<br>
     * If it isn't caching data in S21LRUMap, this class search 'CMPSN
     * table' and cache it.<br>
     * 
     * <pre>
     * +[Search Condition]+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     *  SQLID="003"
     *  
     *  WHERE
     *          GLBL_CMPY_CD = ?glblCmpyCd01?
     *      AND PRNT_MDSE_CD = ?prntMdseCd01?
     *      AND ( CHILD_ORD_TAKE_MDSE_CD = SUBSTR( ?childOrdTakeMdseCd01A?, 1, 8 ) OR CHILD_MDSE_CD = ?childOrdTakeMdseCd01B? )
     *      AND EFF_FROM_DT <= ?effFromDt01?
     *      AND EFF_THRU_DT >= ?effThruDt01?
     *      AND EZCANCELFLAG = '0'
     * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     * </pre>
     * 
     * @param glblCmpyCd GLBL_CMPY_CD
     * @param prntMdseCd PRNT_MDSE_CD
     * @param childMdseCd CHILD_MDSE_CD
     * @param effFromDt EFF_FROM_DT
     * @param effThruDt EFF_THRU_DT
     * @return CMPSNTMsg
     */
    public CMPSNTMsg get(String glblCmpyCd, String prntMdseCd, String childMdseCd, String effFromDt, String effThruDt) {

        CMPSNTMsgArray resTMsgArray = null;

        CmpsnTMsgCacheKey cacheKey = createCacheKeyByChildMdseCd(glblCmpyCd, prntMdseCd, childMdseCd, effFromDt, effThruDt);
        if (hasCache(cacheKey)) {
            resTMsgArray = get(cacheKey);
        } else {
            resTMsgArray = findByChildMdseCd(glblCmpyCd, prntMdseCd, childMdseCd, effFromDt, effThruDt);
        }

        cache(cacheKey, resTMsgArray);

        CMPSNTMsg resTMsg = null;
        if (hasValidCount(resTMsgArray)) {
            resTMsg = resTMsgArray.no(0);
        }
        return resTMsg;
    }

    /**
     * has caching data?
     * @param cacheKey CmpsnTMsgCacheKey
     * @return true/yes, I has. false/no, I don't have.
     */
    public boolean hasCache(CmpsnTMsgCacheKey cacheKey) {
        return super.hasCache(cacheMap, cacheKey);
    }

    @Override
    public String toString() {
        return super.toString(cacheMap);
    }

}
