/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC002001.cache;

import java.util.List;
import java.util.Map;

import business.db.AREA_LEAD_TMTMsg;
import business.db.TRNSP_LTTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.common.S21ApplicationCacheHolder;
import com.canon.cusa.s21.framework.common.collections.S21LRUMap;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/20   Fujitsu         T.Yoshida       Create          S21_NA#8166 (For Performance)
 * </pre>
 */
public final class DataCacheForSSM {

    /** My Instance Key in TreadLocal */
    private static final String INSTANCE_KEY = DataCacheForSSM.class.getName();

    /** S21LRUMap */
    private final S21LRUMap<Map<String, String>, String> rtlWhCdCache = new S21LRUMap<Map<String, String>, String>();

    /** S21LRUMap */
    private final S21LRUMap<Map<String, String>, TRNSP_LTTMsg> trnspLeadTmTMsgCache = new S21LRUMap<Map<String, String>, TRNSP_LTTMsg>();

    /** S21LRUMap */
    private final S21LRUMap<Map<String, String>, AREA_LEAD_TMTMsg> areaLeadTmTMsgCache = new S21LRUMap<Map<String, String>, AREA_LEAD_TMTMsg>();

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
     * Get Retail Warehouse Code from Inventory Location Code
     * @param ssmParam SSM Map (Map Key)
     * @param ssmClient DB Accessor
     * @return Retail Warehouse Code
     */
    public String getRtlWhCdByInvtyLocCd(Map<String, String> ssmParam, S21SsmBatchClient ssmClient) {

        String rtlWhCd = rtlWhCdCache.get(ssmParam);

        if (rtlWhCd == null) {
            rtlWhCd = (String) ssmClient.queryObject("getRtlWhCdByInvtyLocCd", ssmParam);

            if (!ZYPCommonFunc.hasValue(rtlWhCd)) {
                rtlWhCd = "";
            }

            rtlWhCdCache.put(ssmParam, rtlWhCd);
        }

        return rtlWhCd;
    }

    /**
     * Get Longest Transportation Lead Time
     * @param ssmParam SSM Map (Map Key)
     * @param ssmClient DB Accessor
     * @return TRNSP_LTTMsg
     */
    @SuppressWarnings("unchecked")
    public TRNSP_LTTMsg getLongestTrnspLeadTm(Map<String, String> ssmParam, S21SsmBatchClient ssmClient) {

        TRNSP_LTTMsg trnspLtTMsg = trnspLeadTmTMsgCache.get(ssmParam);

        if (trnspLtTMsg == null) {
            List<TRNSP_LTTMsg> trnspLtList = ssmClient.queryObjectList("getLongestTrnspLT", ssmParam);
            if (trnspLtList.isEmpty()) {
                trnspLtTMsg = new TRNSP_LTTMsg();
            } else {
                trnspLtTMsg = trnspLtList.get(0);
            }
            trnspLeadTmTMsgCache.put(ssmParam, trnspLtTMsg);
        }

        if (ZYPCommonFunc.hasValue(trnspLtTMsg.shpgModeCd)) {
            return trnspLtTMsg;
        }

        return null;
    }

    /**
     * Get Longest Area Lead Time
     * @param ssmParam SSM Map (Map Key)
     * @param ssmClient DB Accessor
     * @return AREA_LEAD_TMTMsg
     */
    @SuppressWarnings("unchecked")
    public AREA_LEAD_TMTMsg getLongestAreaLeadTm(Map<String, String> ssmParam, S21SsmBatchClient ssmClient) {

        AREA_LEAD_TMTMsg areaLtTMsg = areaLeadTmTMsgCache.get(ssmParam);

        if (areaLtTMsg == null) {
            List<AREA_LEAD_TMTMsg> areaLeadTmList = ssmClient.queryObjectList("getLongestAreaLeadTM", ssmParam);
            if (areaLeadTmList.isEmpty()) {
                areaLtTMsg = new AREA_LEAD_TMTMsg();
            } else {
                areaLtTMsg = areaLeadTmList.get(0);
            }
            areaLeadTmTMsgCache.put(ssmParam, areaLtTMsg);
        }

        if (ZYPCommonFunc.hasValue(areaLtTMsg.shpgModeCd)) {
            return areaLtTMsg;
        }

        return null;
    }
}
