/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NWX.NWXC014001.cache;

import parts.common.EZDTMsgArray;
import business.db.MDSE_TP_VAL_SETTMsg;
import business.db.MDSE_TP_VAL_SETTMsgArray;
import business.db.ORD_LINE_VAL_SETTMsg;
import business.db.ORD_LINE_VAL_SETTMsgArray;
import business.db.PRC_CONTRTMsg;
import business.db.PRC_CONTRTMsgArray;
import business.db.SHIP_TO_CUSTTMsg;
import business.db.SHIP_TO_CUSTTMsgArray;

import com.canon.cusa.s21.framework.common.S21ApplicationCacheHolder;
import com.canon.cusa.s21.framework.common.collections.S21LRUMap;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/12   Fujitsu         T.Yoshida       Create          S21_NA#11618 (For Performance)
 * </pre>
 */
public final class DataCacheForTBLAccessor extends LocalDataCacheBase {

    /** My Instance Key in TreadLocal */
    private static final String INSTANCE_KEY = DataCacheForTBLAccessor.class.getName();

    /** S21LRUMap */
    private final S21LRUMap<String, EZDTMsgArray> shipToCustTMsgArrayCache = new S21LRUMap<String, EZDTMsgArray>();

    /** S21LRUMap */
    private final S21LRUMap<String, EZDTMsgArray> prcContrTMsgArrayCache = new S21LRUMap<String, EZDTMsgArray>();

    /** S21LRUMap */
    private final S21LRUMap<String, EZDTMsgArray> mdseTpValSetTMsgArrayCache = new S21LRUMap<String, EZDTMsgArray>();

    /** S21LRUMap */
    private final S21LRUMap<String, EZDTMsgArray> ordLineValSetTMsgArrayCache = new S21LRUMap<String, EZDTMsgArray>();

    /** Private Constructor */
    private DataCacheForTBLAccessor() {
    }

    /**
     * Get Singleton Instance
     * @return DataCacheForTBLAccessor
     */
    public static DataCacheForTBLAccessor getInstance() {

        DataCacheForTBLAccessor myInstance = (DataCacheForTBLAccessor) S21ApplicationCacheHolder.get(INSTANCE_KEY);
        if (myInstance == null) {
            myInstance = new DataCacheForTBLAccessor();
            S21ApplicationCacheHolder.put(INSTANCE_KEY, myInstance);
        }
        return myInstance;
    }

    /**
     * Get SHIP_TO_CUSTTMsgArray
     * @param findCondition FindCondition
     * @return SHIP_TO_CUSTTMsgArray
     */
    public SHIP_TO_CUSTTMsgArray getShipToCustTMsgArray(FindCondition findCondition) {
        return (SHIP_TO_CUSTTMsgArray) super.getTMsgArray(SHIP_TO_CUSTTMsg.class, findCondition, shipToCustTMsgArrayCache);
    }

    /**
     * Get PRC_CONTRTMsgArray
     * @param findCondition FindCondition
     * @return PRC_CONTRTMsgArray
     */
    public PRC_CONTRTMsgArray getPrcContrTMsgArray(FindCondition findCondition) {
        return (PRC_CONTRTMsgArray) super.getTMsgArray(PRC_CONTRTMsg.class, findCondition, prcContrTMsgArrayCache);
    }

    /**
     * Get MDSE_TP_VAL_SETTMsgArray
     * @param findCondition FindCondition
     * @return MDSE_TP_VAL_SETTMsgArray
     */
    public MDSE_TP_VAL_SETTMsgArray getMdseTpValSetTMsgArray(FindCondition findCondition) {
        return (MDSE_TP_VAL_SETTMsgArray) super.getTMsgArray(MDSE_TP_VAL_SETTMsg.class, findCondition, mdseTpValSetTMsgArrayCache);
    }

    /**
     * Get ORD_LINE_VAL_SETTMsgArray
     * @param findCondition FindCondition
     * @return ORD_LINE_VAL_SETTMsgArray
     */
    public ORD_LINE_VAL_SETTMsgArray getOrdLineValSetTMsgArray(FindCondition findCondition) {
        return (ORD_LINE_VAL_SETTMsgArray) super.getTMsgArray(ORD_LINE_VAL_SETTMsg.class, findCondition, ordLineValSetTMsgArrayCache);
    }
}
