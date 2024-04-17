package com.canon.cusa.s21.common.NWX.NWXC004001;

import java.math.BigDecimal;

import business.db.CPO_DTLTMsg;

import com.canon.cusa.s21.framework.common.S21ApplicationCacheHolder;
import com.canon.cusa.s21.framework.common.collections.S21LRUMap;

/**
 * <pre>
 * Get Child Merchandise Qty from CPO_DTL
 *
 * Date         Company         Name            Create/Update   Defect No                                                                                                                       
 * ----------------------------------------------------------------------                                                                                                                       
 * 2015/09/10   Fujitsu         S.Takami        Create          CNA(2.8 Allocation - SO/PO)
 *</pre>
 */

public final class NWXChildMdseQtyByCpoDtlThreadLocalCache  extends NWXThreadLocalCacheBase {

    /**
     * Caching key class.<br>
     * Key is created by 'GLBL_CMPY_CD' and 'MDSE_CD'.<br>
     * @author S.Takami
     */
    public static final class CpoDtlTMsgCacheKey extends NWXThreadLocalCacheKeyBase {

        private CpoDtlTMsgCacheKey(String glblCmpyCd, String cpoOrdNum, String cpoDtlLineNum, String cpoDtlLineSubNum) {

            StringBuilder sb = new StringBuilder(100);
            sb.append("glblCmpyCd=").append(glblCmpyCd).append(SEPARATOR);
            sb.append("cpoOrdNum=").append(cpoOrdNum).append(SEPARATOR);
            sb.append("cpoDtlLineNum=").append(cpoDtlLineNum).append(SEPARATOR);
            sb.append("cpoDtlLineSubNum=").append(cpoDtlLineSubNum).append(SEPARATOR);

            super.setKey(sb.toString());
        }
    }

    /** CPO_DTL_LINE_NUM for set parent */
    private static final String SET_DTL_LINE = "000";

    /**
     * my instance key in TreadLocal.
     */
    private static final String INSTANCE_KEY = NWXChildMdseQtyByCpoDtlThreadLocalCache.class.getName();

    /**
     * singleton instance getter.
     * @return NWXMdseTMsgThreadLocalCache
     */
    public static NWXChildMdseQtyByCpoDtlThreadLocalCache getInstance() {
        NWXChildMdseQtyByCpoDtlThreadLocalCache myInstance = (NWXChildMdseQtyByCpoDtlThreadLocalCache) S21ApplicationCacheHolder.get(INSTANCE_KEY);
        if (myInstance == null) {
            myInstance = new NWXChildMdseQtyByCpoDtlThreadLocalCache();
            S21ApplicationCacheHolder.put(INSTANCE_KEY, myInstance);
        }
        return myInstance;
    };

    /**
     * caching data. using LRU Algorithm.
     */
    private S21LRUMap<String, CPO_DTLTMsg> cacheMap = new S21LRUMap<String, CPO_DTLTMsg>(200);

    private NWXChildMdseQtyByCpoDtlThreadLocalCache() {
        super();
    }

    /**
     * cache data.
     * @param cacheKey MdseTMsgCacheKey
     * @param value MDSETMsg
     */
    public void cache(CpoDtlTMsgCacheKey cacheKey, CPO_DTLTMsg value) {
        if (value != null) {
            cacheMap.put(cacheKey.getKey(), value);
        }
    }

    /*
     * @see com.canon.cusa.s21.common.NWX.NWXC004001.NWXThreadLocalCacheBase#clear()
     */
    @Override
    public void clear() {
        cacheMap.clear();
    }

    /**
     * create caching key.
     * @param glblCmpyCd Global Company Code
     * @param cpoOrdNum CPO order number
     * @param cpoDtlLineNum CPO Detail Line Number
     * @param cpoDtlLineSubNum CPO Detail Line Sub Number
     * @return CpoDtlTMsgCacheKey cache key
     */
    public CpoDtlTMsgCacheKey createCacheKey(String glblCmpyCd, String cpoOrdNum, String cpoDtlLineNum, String cpoDtlLineSubNum) {
        return new CpoDtlTMsgCacheKey(glblCmpyCd, cpoOrdNum, cpoDtlLineNum, cpoDtlLineSubNum);
    }

    private CPO_DTLTMsg findByKey(String glblCmpyCd, String cpoOrdNum, String cpoDtlLineNum, String cpoDtlLineSubNum) {

        CPO_DTLTMsg reqTMsg = new CPO_DTLTMsg();
        reqTMsg.glblCmpyCd.setValue(glblCmpyCd);
        reqTMsg.cpoOrdNum.setValue(cpoOrdNum);
        reqTMsg.cpoDtlLineNum.setValue(cpoDtlLineNum);
        reqTMsg.cpoDtlLineSubNum.setValue(cpoDtlLineSubNum);

        return (CPO_DTLTMsg) super.findByKey(reqTMsg);
    }

    /**
     * get caching data.
     * @param cacheKey CpoDtlTMsgCacheKey
     * @return CPO_DTLTMsg
     */
    public CPO_DTLTMsg get(CpoDtlTMsgCacheKey cacheKey) {
        return cacheMap.get(cacheKey.getKey());
    }

    /**
     * get caching data.<br>
     * If it isn't caching data in S21LRUMap, this class search 'CPO_DTL
     * table' and cache it.<br>
     * 
     * <pre>
     * +[Search Condition]+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     *  CPO_DTLTMsg reqTMsg = new CPO_DTLTMsg();
     *  reqTMsg.glblCmpyCd.setValue(glblCmpyCd);
     *  reqTMsg.cpoOrdNum.setValue(cpoOrdNum);
     *  reqTMsg.cpoDtlLineNum.setValue(cpoDtlLineNum);
     *  reqTMsg.cpoDtlLineSubNum.setValue(cpoDtlLineSubNum);
     * 
     *  (CPO_DTLTMsg) S21FastTBLAccessor.findByKey(reqTMsg);
     *
     *  After getting CPO_DTL, calculate Child Merchandise Qty:
     *      CPO_DTL.ORD_QTY / set parent CPO_DTL.ORD_QTY
     * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     * </pre>
     * 
     * @param glblCmpyCd GLBL_CMPY_CD glblCmpyCd Global Company Code
     * @param cpoOrdNum CPO order number
     * @param cpoDtlLineNum CPO Detail Line Number
     * @param cpoDtlLineSubNum CPO Detail Line Sub Number
     * @return CPO_DTLTMsg
     */
    public CPO_DTLTMsg get(String glblCmpyCd, String cpoOrdNum, String cpoDtlLineNum, String cpoDtlLineSubNum) {

        CPO_DTLTMsg resPrntTMsg = null;
        CPO_DTLTMsg resTMsg = null;

        // Parent line
        CpoDtlTMsgCacheKey cacheKeyPrnt = createCacheKey(glblCmpyCd, cpoOrdNum, cpoDtlLineNum, SET_DTL_LINE);
        if (hasCache(cacheKeyPrnt)) {
            resPrntTMsg = get(cacheKeyPrnt);
        } else {
            resPrntTMsg = findByKey(glblCmpyCd, cpoOrdNum, cpoDtlLineNum, SET_DTL_LINE);
            if (null !=  resPrntTMsg) {
                cache(cacheKeyPrnt, resPrntTMsg);
            }
        }
        if (null == resPrntTMsg) {
            return null;
        }

        // Child Line
        CpoDtlTMsgCacheKey cacheKey = createCacheKey(glblCmpyCd, cpoOrdNum, cpoDtlLineNum, cpoDtlLineSubNum);
        if (hasCache(cacheKey)) {
            resTMsg = get(cacheKey);
        } else {
            resTMsg = findByKey(glblCmpyCd, cpoOrdNum, cpoDtlLineNum, cpoDtlLineSubNum);
            // calculate child mdse qty rate
            if (null != resTMsg) {
                BigDecimal ordQty = resTMsg.ordQty.getValue();
                BigDecimal prntOrdQty = resPrntTMsg.ordQty.getValue();
                BigDecimal childMdseQty = ordQty.divide(prntOrdQty);

                // calculate child merchandise qty
                resTMsg.ordQty.setValue(childMdseQty);
                cache(cacheKey, resTMsg);
            }
        }
        return resTMsg;
    }

    /**
     * Get Child Merchandise Qty
     * @param glblCmpyCd GLBL_CMPY_CD glblCmpyCd Global Company Code
     * @param cpoOrdNum CPO order number
     * @param cpoDtlLineNum CPO Detail Line Number
     * @param cpoDtlLineSubNum CPO Detail Line Sub Number
     * @return BigDecimal: Child Merchandise Qty
     */
    public BigDecimal getChildMdseQtyFromCpoDtl(String glblCmpyCd, String cpoOrdNum, String cpoDtlLineNum, String cpoDtlLineSubNum) {
        BigDecimal rslt = BigDecimal.ZERO;
        CPO_DTLTMsg childCpoDtlTMsg = get(glblCmpyCd, cpoOrdNum, cpoDtlLineNum, cpoDtlLineSubNum);
        if (null != childCpoDtlTMsg) {
            rslt = childCpoDtlTMsg.ordQty.getValue();
        }
        return rslt;
    }
    /**
     * has caching data?
     * @param cacheKey MdseTMsgCacheKey
     * @return true/yes, I has. false/no, I don't have.
     */
    public boolean hasCache(CpoDtlTMsgCacheKey cacheKey) {
        return super.hasCache(cacheMap, cacheKey);
    }

    /*
     * @see com.canon.cusa.s21.common.NWX.NWXC004001.NWXThreadLocalCacheBase#toString()
     */
    @Override
    public String toString() {
        return super.toString(cacheMap);
    }

}
