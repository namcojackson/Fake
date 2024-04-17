/*
 * <pre>Copyright (c) 2018 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC226001;

import java.util.List;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.common.S21ApplicationCacheHolder;
import com.canon.cusa.s21.framework.common.collections.S21LRUMap;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/06/13   Fujitsu         K.Ishizuka      Create          S21_NA#24294 (For Performance)
 * </pre>
 */
public class DataCacheForSSM {

    /** My Instance Key in Tread Local */
    private static final String INSTANCE_KEY = DataCacheForSSM.class.getName();

    /** S21LRUMap */
    private final S21LRUMap<Map<String, Object>, String> billToCustLocNumCache = new S21LRUMap<Map<String, Object>, String>();

    /** S21LRUMap */
    private final S21LRUMap<Map<String, Object>, String> shipToCustLocNumCache = new S21LRUMap<Map<String, Object>, String>();

    /** S21LRUMap */
    private final S21LRUMap<Map<String, Object>, Map<String, Object>> shipToCustInfoCache = new S21LRUMap<Map<String, Object>, Map<String, Object>>();
    
    /** S21LRUMap */
    private final S21LRUMap<Map<String, Object>, Map<String, Object>> confLineInfoCache = new S21LRUMap<Map<String, Object>, Map<String, Object>>();
    
    /** S21LRUMap */
    private final S21LRUMap<Map<String, Object>, String> ctacCustRefTpCache = new S21LRUMap<Map<String, Object>, String>();

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
     * Get Bill To Location Number From Cache
     * @param ssmParam SSM Map (Map Key)
     * @param ssmClient DB Accessor
     * @return Bill To Location Number From Cache
     */
    public String getBillToLocNum(Map<String, Object> ssmParam) {

        String billToLocNum = billToCustLocNumCache.get(ssmParam);

        if (!ZYPCommonFunc.hasValue(billToLocNum)) {
            billToLocNum = NWZC226001Query.getInstance().queryString("getBillToLocNum", ssmParam);

            if (!ZYPCommonFunc.hasValue(billToLocNum)) {
                return null;
            }
            billToCustLocNumCache.put(ssmParam, billToLocNum);
        }

        return billToLocNum;
    }

    /**
     * Get Ship To Location Number From Cache
     * @param ssmParam SSM Map (Map Key)
     * @param ssmClient DB Accessor
     * @return Ship To Location Number From Cache
     */
    public String getShipToLocNum(Map<String, Object> ssmParam) {

        String shipToLocNum = shipToCustLocNumCache.get(ssmParam);

        if (!ZYPCommonFunc.hasValue(shipToLocNum)) {
            shipToLocNum = NWZC226001Query.getInstance().queryString("getShipToLocNum", ssmParam);

            if (!ZYPCommonFunc.hasValue(shipToLocNum)) {
                return null;
            }
            shipToCustLocNumCache.put(ssmParam, shipToLocNum);
        }

        return shipToLocNum;
    }

    /**
     * Get Ship To Customer Info From Cache
     * @param ssmParam SSM Map (Map Key)
     * @param ssmClient DB Accessor
     * @return Ship To Customer Info From Cache
     */
    public Map<String, Object> getShipToCustInfo(Map<String, Object> ssmParam) {

        Map<String, Object> shipToCustInfo = shipToCustInfoCache.get(ssmParam);

        if (shipToCustInfo == null) {
            List<Map<String, Object>> shipToCustInfoList = (List<Map<String, Object>>) NWZC226001Query.getInstance().queryMapList("getShipInfo", ssmParam);

            if (shipToCustInfoList == null || shipToCustInfoList.size() == 0) {
                return null;
            }
            shipToCustInfo = shipToCustInfoList.get(0);
            shipToCustInfoCache.put(ssmParam, shipToCustInfo);
        }

        return shipToCustInfo;
    }
    
    /**
     * Get Config Line Info From Cache
     * @param ssmParam SSM Map (Map Key)
     * @param ssmClient DB Accessor
     * @return Config Line Info From Cache
     */
    public Map<String, Object> getConfigLineInfo(Map<String, Object> ssmParam) {

        Map<String, Object> confLineInfo = confLineInfoCache.get(ssmParam);

        if (confLineInfo == null) {
            List<Map<String, Object>> confLineInfoList = (List<Map<String, Object>>) NWZC226001Query.getInstance().queryMapList("getConfigLineInfo", ssmParam);

            if (confLineInfoList == null || confLineInfoList.size() == 0) {
                return null;
            }
            confLineInfo = confLineInfoList.get(0);
            confLineInfoCache.put(ssmParam, confLineInfo);
        }

        return confLineInfo;
    }
    
    /**
     * Contact Customer Reference Type From Cache
     * @param ssmParam SSM Map (Map Key)
     * @param ssmClient DB Accessor
     * @return Contact Customer Reference Type From Cache
     */
    public String getCtacCustRefTpCd(Map<String, Object> ssmParam) {

        String ctacCustRefTp = ctacCustRefTpCache.get(ssmParam);

        if (!ZYPCommonFunc.hasValue(ctacCustRefTp)) {
            ctacCustRefTp = NWZC226001Query.getInstance().queryString("getCtacCustRefTpCd", ssmParam);

            if (!ZYPCommonFunc.hasValue(ctacCustRefTp)) {
                return null;
            }
            ctacCustRefTpCache.put(ssmParam, ctacCustRefTp);
        }

        return ctacCustRefTp;
    }

}
