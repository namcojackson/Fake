package com.canon.cusa.s21.api.NWZ.NWZC155001;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDTMsg;

import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.common.S21ApplicationCacheHolder;
import com.canon.cusa.s21.framework.common.collections.S21LRUMap;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;

/**
 * <pre>
 * DI Check API
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/28   Fujitsu         C.Yokoi         Create          N/A
 *</pre>
 */
public final class NWZC155001_CacheFindByKey {
    /** Parameter delimiter */
    private static final String DELIMITER = ",";

    /**
     * my instance key in TreadLocal.
     */
    private static final String INSTANCE_KEY = NWZC155001_CacheFindByKey.class.getName();

    /**
     * caching FindByKeyForUpdate data. using LRU Algorithm.
     */
    private S21LRUMap<String, EZDTMsg> cacheLockMap = new S21LRUMap<String, EZDTMsg>(200);

    /**
     * caching just FindByKey data. using LRU Algorithm.
     */
    private S21LRUMap<String, EZDTMsg> cacheMap = new S21LRUMap<String, EZDTMsg>(200);

    /**
     * singleton
     */
    private NWZC155001_CacheFindByKey() {

    }

    /** Execute bulk insert/update Number */
    private static final int BULK_NUM = 1000;

    /**
     * bulk Update Data
     */
    private Map<Class, Map<Class, List<EZDTMsg>>> updateMap = new HashMap<Class, Map<Class, List<EZDTMsg>>>();

    /**
     * <pre>
     * bulk update
     * </pre>
     * @param execClass execute class
     * @param tMsg update TMsg(need to contains not to update culomn
     * data)
     * @return true: normal end. false: error
     */
    public boolean bulkUpdateWithCache(Class execClass, EZDTMsg tMsg) {
        String key = makeKey(tMsg);
        cacheLockMap.put(key, tMsg);

        Map<Class, List<EZDTMsg>> updateTMsgMap = updateMap.get(execClass);
        if (updateTMsgMap == null) {
            updateTMsgMap = new HashMap<Class, List<EZDTMsg>>();
            updateMap.put(execClass, updateTMsgMap);
        }
        List<EZDTMsg> updateList = updateTMsgMap.get(tMsg.getClass());
        if (updateList == null) {
            updateList = new ArrayList<EZDTMsg>();
            updateTMsgMap.put(tMsg.getClass(), updateList);
        }
        updateList.add(tMsg);
        if (updateList.size() >= BULK_NUM) {
            return updateFlash(execClass);
        }
        return true;
    }

    /**
     * do bulk Update
     * @param execClass execute class
     * @return true: normal end. false: error
     */
    public boolean updateFlash(Class execClass) {
        Map<Class, List<EZDTMsg>> updateTMsgMap = updateMap.get(execClass);
        if (updateTMsgMap == null) {
            return true;
        }
        for (List<EZDTMsg> updateList : updateTMsgMap.values()) {
            if (updateList == null || updateList.size() == 0) {
                continue;
            }
            int normalLineNum = S21FastTBLAccessor.update(updateList.toArray(new EZDTMsg[0]));
            if (normalLineNum != updateList.size()) {
                for (EZDTMsg tMsg : updateList) {
                    outUpdErr(tMsg);
                }
                updateList.clear();
                return false;
            }
            updateList.clear();
        }
        return true;
    }

    /**
     * <pre>
     * Out Primary Key Infomation for Update Error
     * </pre>
     * @param ezdTMsg target TMsg.
     */
    private static void outUpdErr(EZDTMsg ezdTMsg) {
        S21InfoLogOutput.println("DACM0747E", getErrParam(ezdTMsg));
    }

    /**
     * <pre>
     * Get Table Name and Primary Key String List
     * </pre>
     * @param ezdTMsg target TMsg.
     * @return String[2]{Table Name, Primary Key String}
     */
    private static String[] getErrParam(EZDTMsg ezdTMsg) {

        StringBuilder pk = new StringBuilder();

        List[] keyColumnList = ezdTMsg.getKeyColumnList();
        for (int i = 0; i < keyColumnList[1].size(); i++) {
            pk.append((String) keyColumnList[1].get(i));
            pk.append(DELIMITER);
            pk.append(ezdTMsg.getDBValue((String) keyColumnList[0].get(i)));
            pk.append(" ");
        }
        return new String[] {ezdTMsg.getTableName(), pk.toString() };

    }

    /**
     * put Cache.
     * @param tMsg target TMsg
     */
    public void putCache(EZDTMsg tMsg) {
        String key = makeKey(tMsg);
        cacheLockMap.put(key, tMsg);
    }

    /**
     * singleton instance getter.
     * @return DWXMdseTMsgThreadLocalCache
     */
    public static NWZC155001_CacheFindByKey getInstance() {
        NWZC155001_CacheFindByKey myInstance = (NWZC155001_CacheFindByKey) S21ApplicationCacheHolder.get(INSTANCE_KEY);
        if (myInstance == null) {
            myInstance = new NWZC155001_CacheFindByKey();
            S21ApplicationCacheHolder.put(INSTANCE_KEY, myInstance);
        }
        return myInstance;
    }

    /**
     * DB search by primary key (use S21ApiTBLAccessor.findByKey)
     * @param tMsg target TMsg
     * @return search result
     */
    public EZDTMsg findByKeyWithCache(EZDTMsg tMsg) {
        String key = makeKey(tMsg);
        EZDTMsg tMsgRes = cacheLockMap.get(key);
        if (tMsgRes != null) {
            return tMsgRes;
        }
        tMsgRes = cacheMap.get(key);
        if (tMsgRes != null) {
            return tMsgRes;
        }
        tMsgRes = S21ApiTBLAccessor.findByKey(tMsg);
        cacheMap.put(key, tMsgRes);
        return tMsgRes;
    }

    /**
     * DB search by primary key with lock (use
     * S21ApiTBLAccessor.findByKeyForUpdate)
     * @param tMsg target TMsg
     * @return search result
     */
    public EZDTMsg findByKeyForUpdateWithCache(EZDTMsg tMsg) {
        String key = makeKey(tMsg);
        EZDTMsg tMsgRes = cacheLockMap.get(key);
        if (tMsgRes != null) {
            return tMsgRes;
        }
        tMsgRes = S21ApiTBLAccessor.findByKeyForUpdate(tMsg);
        cacheLockMap.put(key, tMsgRes);
        return tMsgRes;
    }

    /**
     * <pre>
     * bulk update
     * </pre>
     * @param tMsg update TMsg(need to contains not to update culomn
     * data)
     */
    public void updateWithCache(EZDTMsg tMsg) {
        String key = makeKey(tMsg);
        cacheLockMap.put(key, tMsg);
        S21ApiTBLAccessor.update(tMsg);
    }

    /**
     * <pre>
     * bulk insert
     * </pre>
     * @param tMsg insert TMsg(need to contains not to update culomn
     * data)
     */
    public void insertWithCache(EZDTMsg tMsg) {
        String key = makeKey(tMsg);
        cacheLockMap.put(key, tMsg);
        S21ApiTBLAccessor.insert(tMsg);
    }

    /**
     * clear bulk data and cache data
     */
    public void clear() {
        cacheLockMap.clear();
        cacheMap.clear();
    }

    /**
     * make cache Key
     * @param tMsg target TMsg
     * @return cache Key
     */
    private String makeKey(EZDTMsg tMsg) {
        StringBuffer sb = new StringBuffer(tMsg.getClass().getSimpleName());
        List[] keyColumnList = tMsg.getKeyColumnList();
        for (int i = 0; i < keyColumnList[0].size(); i++) {
            sb.append(DELIMITER);
            sb.append(tMsg.getDBValue((String) keyColumnList[0].get(i)));
        }
        return sb.toString();
    }

}
