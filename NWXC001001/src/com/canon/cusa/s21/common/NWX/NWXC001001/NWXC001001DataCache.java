/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.common.NWX.NWXC001001;

import java.util.Map;

import com.canon.cusa.s21.framework.common.S21ApplicationCacheHolder;
import com.canon.cusa.s21.framework.common.collections.S21LRUMap;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/29   Fujitsu         T.Yoshida       Create          S21_NA#10321 (For Performance)
 * </pre>
 */
public class NWXC001001DataCache {

    /** My Instance Key in Tread Local */
    private static final String INSTANCE_KEY = NWXC001001DataCache.class.getName();

    /** S21LRUMap */
    private final S21LRUMap<Map<String, String>, NWXC001001RateData> exchRateDataCache = new S21LRUMap<Map<String, String>, NWXC001001RateData>();

    /** S21LRUMap */
    private final S21LRUMap<Map<String, String>, Integer> stdCcyDigitCache = new S21LRUMap<Map<String, String>, Integer>();

    /** Private Constructor */
    private NWXC001001DataCache() {
    }

    /**
     * Get Singleton Instance
     * @return DataCacheForSSM
     */
    public static NWXC001001DataCache getInstance() {

        NWXC001001DataCache myInstance = (NWXC001001DataCache) S21ApplicationCacheHolder.get(INSTANCE_KEY);
        if (myInstance == null) {
            myInstance = new NWXC001001DataCache();
            S21ApplicationCacheHolder.put(INSTANCE_KEY, myInstance);
        }
        return myInstance;
    }

    /**
     * Get Exchange Rate Data From Cache
     * @param ssmParam SSM Map (Map Key)
     * @param ssmClient DB Accessor
     * @return Exchange Rate Data From Cache
     */
    public NWXC001001RateData getExchRateData(Map<String, String> ssmParam, S21SsmBatchClient ssmClient) {

        NWXC001001RateData exchRateData = exchRateDataCache.get(ssmParam);

        if (exchRateData == null) {
            exchRateData = (NWXC001001RateData) ssmClient.queryObject("getExchRateData", ssmParam);

            if (exchRateData == null) {
                return null;
            }
            exchRateDataCache.put(ssmParam, exchRateData);
        }

        return exchRateData;
    }

    /**
     * Get Standard Currency Digit From Cache
     * @param ssmParam SSM Map (Map Key)
     * @param ssmClient DB Accessor
     * @return Standard Currency Digit From Cache
     */
    public Integer getStdCcyDigit(Map<String, String> ssmParam, S21SsmBatchClient ssmClient) {

        Integer stdCcyDigit = stdCcyDigitCache.get(ssmParam);

        if (stdCcyDigit == null) {
            stdCcyDigit = (Integer) ssmClient.queryObject("getStdCcyDigit", ssmParam);

            if (stdCcyDigit == null) {
                return null;
            }
            stdCcyDigitCache.put(ssmParam, stdCcyDigit);
        }

        return stdCcyDigit;
    }
}
