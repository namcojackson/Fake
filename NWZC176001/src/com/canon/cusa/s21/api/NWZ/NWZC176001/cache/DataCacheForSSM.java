/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC176001.cache;

import java.util.List;
import java.util.Map;

import business.db.CPO_DTLTMsg;
import business.parts.NWXC005001PMsg;

import com.canon.cusa.s21.framework.common.S21ApplicationCacheHolder;
import com.canon.cusa.s21.framework.common.collections.S21LRUMap;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmEZDClient;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/10/13   Fujitsu         T.Yoshida       Create          QC#14973 (For Performance)
 * </pre>
 */
public final class DataCacheForSSM {

    /** My Instance Key in Tread Local */
    private static final String INSTANCE_KEY = DataCacheForSSM.class.getName();

    /** S21LRUMap */
    private final S21LRUMap<String, Integer> cntCreditOrderCache = new S21LRUMap<String, Integer>();

    /** S21LRUMap */
    private final S21LRUMap<String, List<Map<String, String>>> dsCpoDtlInfoCache = new S21LRUMap<String, List<Map<String, String>>>();

    /** Private Constructor */
    private DataCacheForSSM() {
    }

    /**
     * Get Singleton Instance
     * @return DataCacheForSSM
     */
    public static DataCacheForSSM getInstance() {

        DataCacheForSSM myInstance = (DataCacheForSSM) S21ApplicationCacheHolder.get(INSTANCE_KEY);
        if (myInstance == null) {
            myInstance = new DataCacheForSSM();
            S21ApplicationCacheHolder.put(INSTANCE_KEY, myInstance);
        }
        return myInstance;
    }

    /**
     * Count Credit Order From Cache
     * @param ssmParam SSM Map (Map Key)
     * @param ssmClient DB Accessor
     * @return Count Credit Order From Cache
     */
    public Integer cntCreditOrder(Map<String, Object> ssmParam, S21SsmEZDClient ssmClient) {

        NWXC005001PMsg pMsg = (NWXC005001PMsg) ssmParam.get("pMsg");

        final StringBuilder sb = new StringBuilder();
        sb.append(pMsg.glblCmpyCd.getValue()).append(",");
        sb.append(pMsg.cpoOrdNum_I.getValue());
        final String cacheKey = sb.toString();

        Integer cnt = cntCreditOrderCache.get(cacheKey);

        if (cnt == null) {
            cnt = (Integer) ssmClient.queryObject("cntCreditOrder", ssmParam).getResultObject();

            if (cnt == null) {
                cntCreditOrderCache.put(cacheKey, 0);
                return 0;
            }

            cntCreditOrderCache.put(cacheKey, cnt);
        }

        return cnt;
    }

    /**
     * Get DS CPO DTL Info
     * @param ssmParam SSM Map (Map Key)
     * @param ssmClient DB Accessor
     * @return DS Order Type Info From Cache
     */
    @SuppressWarnings("unchecked")
    public List<Map<String, String>> getDsCpoDtlInfo(Map<String, Object> ssmParam, S21SsmEZDClient ssmClient) {

        NWXC005001PMsg pMsg = (NWXC005001PMsg) ssmParam.get("pMsg");
        CPO_DTLTMsg dsCpoDtlTMsg = (CPO_DTLTMsg) ssmParam.get("dsCpoDtlTMsg");

        final StringBuilder sb = new StringBuilder();
        sb.append(pMsg.glblCmpyCd.getValue()).append(",");
        sb.append(dsCpoDtlTMsg.cpoOrdNum.getValue()).append(",");
        sb.append(dsCpoDtlTMsg.dsCpoConfigPk.getValue()).append(",");
        sb.append(pMsg.slsDt.getValue());
        final String cacheKey = sb.toString();

        List<Map<String, String>> dsCpoDtlInfoList = dsCpoDtlInfoCache.get(cacheKey);

        if (dsCpoDtlInfoList == null) {
            dsCpoDtlInfoList = (List<Map<String, String>>) ssmClient.queryObjectList("getDsCpoDtlInfo", ssmParam).getResultObject();

            if (dsCpoDtlInfoList == null) {
                return null;
            }

            dsCpoDtlInfoCache.put(cacheKey, dsCpoDtlInfoList);
        }

        return dsCpoDtlInfoList;
    }
}
