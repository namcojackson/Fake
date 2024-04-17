/*
 * <pre>Copyright (c) 2018 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.common.NWX.NWXC150001.cache;

import java.math.BigDecimal;

import parts.common.EZDTMsg;
import parts.common.EZDTMsgArray;
import business.db.MDSE_STORE_PKGTMsg;
import business.db.MDSE_TP_VAL_SETTMsg;
import business.db.MDSE_TP_VAL_SETTMsgArray;

import com.canon.cusa.s21.framework.common.S21ApplicationCacheHolder;
import com.canon.cusa.s21.framework.common.collections.S21LRUMap;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/05/20   Fujitsu         S.Takami        Create          S21_NA#25604
 * </pre>
 */
public class MdseTpValSetCache extends LocalDataCacheBase {

    /** S21LRUMap */
    private final S21LRUMap<String, EZDTMsg> tMsgCache = new S21LRUMap<String, EZDTMsg>();
    private final S21LRUMap<String, EZDTMsgArray> tMsgArrayCache = new S21LRUMap<String, EZDTMsgArray>();

    /** My Instance Key in TreadLocal */
    private static final String INSTANCE_KEY = MdseTpValSetCache.class.getName();


    /** Private Constructor */
    private MdseTpValSetCache() {
    }

    /**
     * Get Singleton Instance
     * @return ConfigTpCache
     */
    public static MdseTpValSetCache getInstance() {

        MdseTpValSetCache myInstance = (MdseTpValSetCache) S21ApplicationCacheHolder.get(INSTANCE_KEY);
        if (myInstance == null) {
            myInstance = new MdseTpValSetCache();
            S21ApplicationCacheHolder.put(INSTANCE_KEY, myInstance);
        }
        return myInstance;
    }

    public MDSE_TP_VAL_SETTMsgArray getTMsgArray(FindCondition findCondition) {

        return (MDSE_TP_VAL_SETTMsgArray) super.getTMsgArray(MDSE_TP_VAL_SETTMsg.class, findCondition, tMsgArrayCache);
    }

    public MDSE_TP_VAL_SETTMsg getTMsgByKey(String glblCmpyCd, BigDecimal mdseTpValSetPk) {

        final StringBuilder sb = new StringBuilder();
        sb.append(glblCmpyCd).append(",");
        sb.append(String.valueOf(mdseTpValSetPk));

        final String cacheKey = sb.toString();

        EZDTMsg resTMsg = tMsgCache.get(cacheKey);

        if (resTMsg == null) {

            // find by key
            final MDSE_TP_VAL_SETTMsg reqTMsg = new MDSE_TP_VAL_SETTMsg();
            reqTMsg.glblCmpyCd.setValue(glblCmpyCd);
            reqTMsg.mdseTpValSetPk.setValue(mdseTpValSetPk);

            resTMsg = super.findByKey(reqTMsg);
            if (resTMsg != null) {
                tMsgCache.put(cacheKey, resTMsg);
            }
        }

        return (MDSE_TP_VAL_SETTMsg) resTMsg;
    }
}
