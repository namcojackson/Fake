/**<pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>*/
package com.canon.cusa.s21.api.NWZ.NWZC150001;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.db.DS_CPO_CONFIGTMsg;
import business.db.MDSETMsg;

import com.canon.cusa.s21.api.NWZ.NWZC150001.NWZC150001.SvcMachCache;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;

/**
 * <pre>
 * DS CPO Update API: Common Bean
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/05/15   Fujitsu         T.Ogura         Create          N/A
 * 2018/06/18   Fujitsu         K.Ishizuka      Update          S21_NA#24294
 * </pre>
 */
public class NWZC150001DsCpoCommonBean {

    /** Online Batch Type */
    private ONBATCH_TYPE onBatchType = null;

    /** DS_CPO_CONFIG Array for Cache */
    private List<DS_CPO_CONFIGTMsg> dsCpoConfigList = null;

    /** Service Machine Master Data Chache */
    private SvcMachCache svcMachCache = null;

    /** Cache with or without Order Category */
    private Map<Map<String, String>, Boolean> ordCatgCacheMap = null;

    // 2018/06/18 S21_NA#24294 Mod Start
    /** MDSE List */
    // private List<MDSETMsg> dsMdseInfoList = null;
    private List<MDSETMsg> mdseInfoList = null;
    // 2018/06/18 S21_NA#24294 Mod End

    /** Presence of Retail Equip Order */
    private boolean isRetailEquipOrder = false;

    /** Constructor */
    public NWZC150001DsCpoCommonBean(NWZC150001 myInstance) {
        super();
        this.svcMachCache = myInstance.new SvcMachCache();
        this.ordCatgCacheMap = new HashMap<Map<String, String>, Boolean>();
    }

    /**
     * @return onBatchType
     */
    public ONBATCH_TYPE getOnBatchType() {
        return onBatchType;
    }

    /**
     * @param onBatchType ONBATCH_TYPE
     */
    public void setOnBatchType(ONBATCH_TYPE onBatchType) {
        this.onBatchType = onBatchType;
    }

    /**
     * @return dsCpoConfigList
     */
    public List<DS_CPO_CONFIGTMsg> getDsCpoConfigList() {
        return dsCpoConfigList;
    }

    /**
     * @param dsCpoConfigList List<DS_CPO_CONFIGTMsg>
     */
    public void setDsCpoConfigList(List<DS_CPO_CONFIGTMsg> dsCpoConfigList) {
        this.dsCpoConfigList = dsCpoConfigList;
    }

    /**
     * @return svcMachCache
     */
    public SvcMachCache getSvcMachCache() {
        return svcMachCache;
    }

    /**
     * @param svcMachCache SvcMachCache
     */
    public void setSvcMachCache(SvcMachCache svcMachCache) {
        this.svcMachCache = svcMachCache;
    }

    /**
     * @return ordCatgCacheMap
     */
    public Map<Map<String, String>, Boolean> getOrdCatgCacheMap() {
        return ordCatgCacheMap;
    }

    /**
     * @param ordCatgCacheMap Map<Map<String, String>, Boolean>
     */
    public void setOrdCatgCacheMap(Map<Map<String, String>, Boolean> ordCatgCacheMap) {
        this.ordCatgCacheMap = ordCatgCacheMap;
    }

    // 2018/06/18 S21_NA#24294 Mod Start
//    /**
//     * @return dsMdseInfoList
//     */
//    public List<MDSETMsg> getDsMdseInfoList() {
//        return dsMdseInfoList;
//    }
//
//    /**
//     * @param dsMdseInfoList List<MDSETMsg>
//     */
//    public void setDsMdseInfoList(List<MDSETMsg> dsMdseInfoList) {
//        this.dsMdseInfoList = dsMdseInfoList;
//    }
    /**
     * @return dsMdseInfoList
     */
    public List<MDSETMsg> getMdseInfoList() {
        return mdseInfoList;
    }

    /**
     * @param dsMdseInfoList List<MDSETMsg>
     */
    public void setMdseInfoList(List<MDSETMsg> mdseInfoList) {
        this.mdseInfoList = mdseInfoList;
    }
    // 2018/06/18 S21_NA#24294 Mod End

    /**
     * @return isRetailEquipOrder
     */
    public boolean getIsRetailEquipOrder() {
        return isRetailEquipOrder;
    }

    /**
     * @param isRetailEquipOrder boolean
     */
    public void setIsRetailEquipOrder(boolean isRetailEquipOrder) {
        this.isRetailEquipOrder = isRetailEquipOrder;
    }
}
