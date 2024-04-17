/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.common.NWX.NWXC150001.cache;

import parts.common.EZDTMsg;
import business.db.CONFIG_TPTMsg;

import com.canon.cusa.s21.framework.common.S21ApplicationCacheHolder;
import com.canon.cusa.s21.framework.common.collections.S21LRUMap;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/07   Fujitsu         T.Yoshida       Create          S21_NA#8166 (For Performance)
 * </pre>
 */
public final class ConfigTpCache extends LocalDataCacheBase {

    /** My Instance Key in TreadLocal */
    private static final String INSTANCE_KEY = ConfigTpCache.class.getName();

    /** S21LRUMap */
    private final S21LRUMap<String, EZDTMsg> tMsgCache = new S21LRUMap<String, EZDTMsg>();

    /** Private Constructor */
    private ConfigTpCache() {
    }

    /**
     * Get Singleton Instance
     * @return ConfigTpCache
     */
    public static ConfigTpCache getInstance() {

        ConfigTpCache myInstance = (ConfigTpCache) S21ApplicationCacheHolder.get(INSTANCE_KEY);
        if (myInstance == null) {
            myInstance = new ConfigTpCache();
            S21ApplicationCacheHolder.put(INSTANCE_KEY, myInstance);
        }
        return myInstance;
    }

    /**
     * Get CONFIG_TPTMsg
     * @param glblCmpyCd Global Company Code
     * @param configTpCd Config Type Code
     * @return CONFIG_TPTMsg
     */
    public CONFIG_TPTMsg getTMsgByKey(String glblCmpyCd, String configTpCd) {

        final StringBuilder sb = new StringBuilder();
        sb.append(glblCmpyCd).append(",");
        sb.append(configTpCd);

        final String cacheKey = sb.toString();

        EZDTMsg resTMsg = tMsgCache.get(cacheKey);

        if (resTMsg == null) {

            // find by key
            final CONFIG_TPTMsg reqTMsg = new CONFIG_TPTMsg();
            reqTMsg.glblCmpyCd.setValue(glblCmpyCd);
            reqTMsg.configTpCd.setValue(configTpCd);

            resTMsg = super.findByKey(reqTMsg);
            if (resTMsg != null) {
                tMsgCache.put(cacheKey, resTMsg);
            }
        }

        return (CONFIG_TPTMsg) resTMsg;
    }
}
