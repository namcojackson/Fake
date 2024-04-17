/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NWX.NWXC014001.cache;

import java.util.List;
import java.util.Map;

import com.canon.cusa.s21.framework.common.S21ApplicationCacheHolder;
import com.canon.cusa.s21.framework.common.collections.S21LRUMap;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/12   Fujitsu         T.Yoshida       Create          S21_NA#11618 (For Performance)
 * </pre>
 */
public final class DataCacheForSSM {

    /** My Instance Key in Tread Local */
    private static final String INSTANCE_KEY = DataCacheForSSM.class.getName();

    /** S21LRUMap */
    private final S21LRUMap<Map<String, String>, Map<String, String>> dsAcctCustCache = new S21LRUMap<Map<String, String>, Map<String, String>>();

    /** S21LRUMap */
    private final S21LRUMap<Map<String, String>, Map<String, String>> mdseCache = new S21LRUMap<Map<String, String>, Map<String, String>>();

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
     * Get DS Account Customer Info From Cache
     * @param ssmParam SSM Map (Map Key)
     * @param ssmClient DB Accessor
     * @return DS Account Customer Info From Cache
     */
    @SuppressWarnings("unchecked")
    public Map<String, String> getDsAcctCust(Map<String, String> ssmParam, S21SsmBatchClient ssmClient) {

        Map<String, String> dsAcctCustInfo = dsAcctCustCache.get(ssmParam);

        if (dsAcctCustInfo == null) {
            List<Map<String, String>> dsAcctCustInfoList = (List<Map<String, String>>) ssmClient.queryObjectList("selectDsAcctCust", ssmParam);

            if (dsAcctCustInfoList == null || dsAcctCustInfoList.size() == 0) {
                return null;
            }
            dsAcctCustInfo = dsAcctCustInfoList.get(0);
            dsAcctCustCache.put(ssmParam, dsAcctCustInfo);
        }

        return dsAcctCustInfo;
    }

    /**
     * Get MDSE Info From Cache
     * @param ssmParam SSM Map (Map Key)
     * @param ssmClient DB Accessor
     * @return MDSE Info From Cache
     */
    @SuppressWarnings("unchecked")
    public Map<String, String> getMdse(Map<String, String> ssmParam, S21SsmBatchClient ssmClient) {

        Map<String, String> mdseInfo = mdseCache.get(ssmParam);

        if (mdseInfo == null) {
            List<Map<String, String>> mdseInfoList = ssmClient.queryObjectList("selectMdse", ssmParam);

            if (mdseInfoList == null || mdseInfoList.size() == 0) {
                return null;
            }
            mdseInfo = mdseInfoList.get(0);
            mdseCache.put(ssmParam, mdseInfo);
        }

        return mdseInfo;
    }
}
