/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC033001.cache;

import parts.common.EZDTMsgArray;
import business.db.SHPG_PLNTMsg;
import business.db.SHPG_PLNTMsgArray;
import business.parts.NWZC033001PMsg;

import com.canon.cusa.s21.common.NWX.NWXC150001.NWXC150001DsCheck;
import com.canon.cusa.s21.framework.common.S21ApplicationCacheHolder;
import com.canon.cusa.s21.framework.common.collections.S21LRUMap;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/01/04   Fujitsu         T.Yoshida       Create          S21_NA#14512
 * </pre>
 */
public final class DataCacheForHoldRelease extends LocalDataCacheBase {

    /** My Instance Key in Tread Local */
    private static final String INSTANCE_KEY = DataCacheForHoldRelease.class.getName();

    /** S21LRUMap */
    private final S21LRUMap<String, Boolean> orderCancelledCache = new S21LRUMap<String, Boolean>();

    /** S21LRUMap */
    private final S21LRUMap<String, Boolean> rmaOrderCancelledCache = new S21LRUMap<String, Boolean>();

    /** S21LRUMap */
    private final S21LRUMap<String, EZDTMsgArray> shpgPlnTMsgArrayCache = new S21LRUMap<String, EZDTMsgArray>();

    /** Private Constructor */
    private DataCacheForHoldRelease() {
    }

    /**
     * Get Singleton Instance
     * @return DataCacheForHoldRelease
     */
    public static DataCacheForHoldRelease getInstance() {

        DataCacheForHoldRelease myInstance = (DataCacheForHoldRelease) S21ApplicationCacheHolder.get(INSTANCE_KEY);
        if (myInstance == null) {
            myInstance = new DataCacheForHoldRelease();
            S21ApplicationCacheHolder.put(INSTANCE_KEY, myInstance);
        }
        return myInstance;
    }

    /**
     * Check All Line Cancelled From Cache
     * @param param NWZC033001PMsg
     * @return MDSE Info From Cache
     */
    public boolean checkAllLineCancelled(NWZC033001PMsg param) {

        Boolean isOrderCancelled = orderCancelledCache.get(param.cpoOrdNum.getValue());
        if (isOrderCancelled != null) {
            return isOrderCancelled.booleanValue();
        }

        boolean isAllLineCancelled = NWXC150001DsCheck.checkAllLineCancelled(param.glblCmpyCd.getValue(), param.cpoOrdNum.getValue());
        orderCancelledCache.put(param.cpoOrdNum.getValue(), Boolean.valueOf(isAllLineCancelled));

        return isAllLineCancelled;
    }

    /**
     * Check RMA All Line Cancelled From Cache
     * @param param NWZC033001PMsg
     * @return MDSE Info From Cache
     */
    public boolean checkRmaAllLineCancelled(NWZC033001PMsg param) {

        Boolean isRmaOrderCancelled = rmaOrderCancelledCache.get(param.cpoOrdNum.getValue());
        if (isRmaOrderCancelled != null) {
            return isRmaOrderCancelled.booleanValue();
        }

        boolean isAllRmaLineCancelled = NWXC150001DsCheck.checkAllRmaLineCancelled(param.glblCmpyCd.getValue(), param.cpoOrdNum.getValue());
        rmaOrderCancelledCache.put(param.cpoOrdNum.getValue(), Boolean.valueOf(isAllRmaLineCancelled));

        return isAllRmaLineCancelled;
    }

    /**
     * SHPG_PLNTMsgArray
     * @param findCondition FindCondition
     * @return SHPG_PLNTMsgArray
     */
    public SHPG_PLNTMsgArray getShpgPlnTMsgArray(FindCondition findCondition) {
        return (SHPG_PLNTMsgArray) super.getTMsgArray(SHPG_PLNTMsg.class, findCondition, shpgPlnTMsgArrayCache);
    }
}
