/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC157001.cache;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.common.S21ApplicationCacheHolder;
import com.canon.cusa.s21.framework.common.collections.S21LRUMap;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/20   Fujitsu         T.Yoshida       Create          S21_NA#10321 (For Performance)
 * 2018/04/11   Fujitsu         Y.Kanefusa      Update          S21_NA#22965
 * </pre>
 */
public final class DataCacheForSSM {

    /** My Instance Key in Tread Local */
    private static final String INSTANCE_KEY = DataCacheForSSM.class.getName();

    /** S21LRUMap */
    private final S21LRUMap<Map<String, String>, Map<String, String>> dsOrdTpCache = new S21LRUMap<Map<String, String>, Map<String, String>>();

    /** S21LRUMap */
    private final S21LRUMap<Map<String, String>, Map<String, String>> dsAcctCustCache = new S21LRUMap<Map<String, String>, Map<String, String>>();

    /** S21LRUMap */
    private final S21LRUMap<Map<String, String>, Map<String, String>> billToCustCache = new S21LRUMap<Map<String, String>, Map<String, String>>();

    /** S21LRUMap */
    private final S21LRUMap<Map<String, String>, Map<String, String>> shipToCustCache = new S21LRUMap<Map<String, String>, Map<String, String>>();

    /** S21LRUMap */
    private final S21LRUMap<Map<String, String>, Map<String, Object>> mdseCustCache = new S21LRUMap<Map<String, String>, Map<String, Object>>();

    /** S21LRUMap */
    private final S21LRUMap<String, String> taxAreaCache = new S21LRUMap<String, String>();

    /** S21LRUMap */
    private final S21LRUMap<Map<String, String>, Map<String, String>> rtlWhCache = new S21LRUMap<Map<String, String>, Map<String, String>>();

    /** S21LRUMap */
    private final S21LRUMap<Map<String, String>, Map<String, String>> tocCache = new S21LRUMap<Map<String, String>, Map<String, String>>();

    /** S21LRUMap */
    private final S21LRUMap<Map<String, String>, String> prcListStruTpCache = new S21LRUMap<Map<String, String>, String>();

    /** S21LRUMap */
    private final S21LRUMap<Map<String, String>, List<Map<String, String>>> linePrcListCache = new S21LRUMap<Map<String, String>, List<Map<String, String>>>();

    /** S21LRUMap */
    private final S21LRUMap<Map<String, Object>, List<Map<String, Object>>> basePrcEquipmentCache = new S21LRUMap<Map<String, Object>, List<Map<String, Object>>>();

    /** S21LRUMap */
    private final S21LRUMap<Map<String, String>, List<String>> subPrcListCache = new S21LRUMap<Map<String, String>, List<String>>();

    /** S21LRUMap */
    private final S21LRUMap<Map<String, Object>, List<Map<String, String>>> prcListConfigCache = new S21LRUMap<Map<String, Object>, List<Map<String, String>>>();

    /** S21LRUMap */
    private final S21LRUMap<Map<String, String>, String> prcLeaseTpCache = new S21LRUMap<Map<String, String>, String>();

    /** S21LRUMap */
    private final S21LRUMap<Map<String, Object>, Map<String, Object>> prcRuleCache = new S21LRUMap<Map<String, Object>, Map<String, Object>>();
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
     * Get DS Order Type Info From Cache
     * @param ssmParam SSM Map (Map Key)
     * @param ssmClient DB Accessor
     * @return DS Order Type Info From Cache
     */
    @SuppressWarnings("unchecked")
    public Map<String, String> getDsOrdTpInfo(Map<String, String> ssmParam, S21SsmBatchClient ssmClient) {

        Map<String, String> dsOrdTpInfo = dsOrdTpCache.get(ssmParam);

        if (dsOrdTpInfo == null) {
            List<Map<String, String>> dsOrdTpInfoList = (List<Map<String, String>>) ssmClient.queryObjectList("selectDsOrdTp", ssmParam);

            if (dsOrdTpInfoList == null || dsOrdTpInfoList.size() == 0) {
                return null;
            }
            dsOrdTpInfo = dsOrdTpInfoList.get(0);
            dsOrdTpCache.put(ssmParam, dsOrdTpInfo);
        }

        return dsOrdTpInfo;
    }

    /**
     * Get DS Account Customer Info From Cache
     * @param ssmParam SSM Map (Map Key)
     * @param ssmClient DB Accessor
     * @return DS Account Customer Info From Cache
     */
    @SuppressWarnings("unchecked")
    public Map<String, String> getDsAcctCustInfo(Map<String, String> ssmParam, S21SsmBatchClient ssmClient) {

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
     * Get Bill To Customer Info From Cache
     * @param ssmParam SSM Map (Map Key)
     * @param ssmClient DB Accessor
     * @return Bill To Customer Info From Cache
     */
    @SuppressWarnings("unchecked")
    public Map<String, String> getBillToCustInfo(Map<String, String> ssmParam, S21SsmBatchClient ssmClient) {

        Map<String, String> billToCustInfo = billToCustCache.get(ssmParam);

        if (billToCustInfo == null) {
            List<Map<String, String>> billToCustInfoList = (List<Map<String, String>>) ssmClient.queryObjectList("selectBillToCust", ssmParam);

            if (billToCustInfoList == null || billToCustInfoList.size() == 0) {
                return null;
            }
            billToCustInfo = billToCustInfoList.get(0);
            billToCustCache.put(ssmParam, billToCustInfo);
        }

        return billToCustInfo;
    }

    /**
     * Get Ship To Customer Info From Cache
     * @param ssmParam SSM Map (Map Key)
     * @param ssmClient DB Accessor
     * @return Ship To Customer Info From Cache
     */
    @SuppressWarnings("unchecked")
    public Map<String, String> getShipToCustInfo(Map<String, String> ssmParam, S21SsmBatchClient ssmClient) {

        Map<String, String> shipToCustInfo = shipToCustCache.get(ssmParam);

        if (shipToCustInfo == null) {
            List<Map<String, String>> shipToCustInfoList = (List<Map<String, String>>) ssmClient.queryObjectList("selectShipToCust", ssmParam);

            if (shipToCustInfoList == null || shipToCustInfoList.size() == 0) {
                return null;
            }
            shipToCustInfo = shipToCustInfoList.get(0);
            shipToCustCache.put(ssmParam, shipToCustInfo);
        }

        return shipToCustInfo;
    }

    /**
     * Get MDSE Info From Cache
     * @param ssmParam SSM Map (Map Key)
     * @param ssmClient DB Accessor
     * @return MDSE Info From Cache
     */
    @SuppressWarnings("unchecked")
    public Map<String, Object> getMdseInfo(Map<String, String> ssmParam, S21SsmBatchClient ssmClient) {

        Map<String, Object> mdseInfo = mdseCustCache.get(ssmParam);

        if (mdseInfo == null) {
            List<Map<String, Object>> mdseInfoList = (List<Map<String, Object>>) ssmClient.queryObjectList("selectMdse", ssmParam);

            if (mdseInfoList == null || mdseInfoList.size() == 0) {
                return null;
            }
            mdseInfo = mdseInfoList.get(0);
            mdseCustCache.put(ssmParam, mdseInfo);
        }

        return mdseInfo;
    }

    /**
     * Get Tax Area ID From Cache
     * @param ssmParam SSM Map (Map Key)
     * @param ssmClient DB Accessor
     * @return Tax Area ID From Cache
     */
    @SuppressWarnings("unchecked")
    public String getTaxAreaId(Map<String, String> ssmParam, S21SsmBatchClient ssmClient) {

        final StringBuilder sb = new StringBuilder();
        sb.append(ssmParam.get("glblCmpyCd")).append(",");
        sb.append(ssmParam.get("cntyNm")).append(",");
        sb.append(ssmParam.get("ctyAddr")).append(",");
        sb.append(ssmParam.get("stCd"));
        final String cacheKey = sb.toString();

        String taxAreaId = taxAreaCache.get(cacheKey);

        if (taxAreaId == null) {
            List<String> taxAreaIdList = (List<String>) ssmClient.queryObjectList("selectTaxArea", ssmParam);

            if (taxAreaIdList == null || taxAreaIdList.size() == 0) {
                taxAreaCache.put(cacheKey, "");
                return null;
            }
            taxAreaId = taxAreaIdList.get(0);
            taxAreaCache.put(cacheKey, taxAreaId);
        } else if (taxAreaId.length() == 0) {
            return null;
        }

        return taxAreaId;
    }

    /**
     * Get Retail Warehouse Info From Cache
     * @param ssmParam SSM Map (Map Key)
     * @param ssmClient DB Accessor
     * @return Retail Warehouse Info From Cache
     */
    @SuppressWarnings("unchecked")
    public Map<String, String> getRtlWhInfo(Map<String, String> ssmParam, S21SsmBatchClient ssmClient) {

        Map<String, String> rtlWhInfo = rtlWhCache.get(ssmParam);

        if (rtlWhInfo == null) {
            List<Map<String, String>> rtlWhInfoList = (List<Map<String, String>>) ssmClient.queryObjectList("selectRtlWh", ssmParam);

            if (rtlWhInfoList == null || rtlWhInfoList.size() == 0) {
                return null;
            }
            rtlWhInfo = rtlWhInfoList.get(0);
            rtlWhCache.put(ssmParam, rtlWhInfo);
        }

        return rtlWhInfo;
    }

    /**
     * Get Toc Info From Cache
     * @param ssmParam SSM Map (Map Key)
     * @param ssmClient DB Accessor
     * @return Toc Info From Cache
     */
    @SuppressWarnings("unchecked")
    public Map<String, String> getTocInfo(Map<String, String> ssmParam, S21SsmBatchClient ssmClient) {

        Map<String, String> tocInfo = tocCache.get(ssmParam);

        if (tocInfo == null) {
            List<Map<String, String>> tocInfoList = (List<Map<String, String>>) ssmClient.queryObjectList("selectToc", ssmParam);

            if (tocInfoList == null || tocInfoList.size() == 0) {
                return null;
            }
            tocInfo = tocInfoList.get(0);
            tocCache.put(ssmParam, tocInfo);
        }

        return tocInfo;
    }

    /**
     * Get Price List Structure Type From Cache
     * @param ssmParam SSM Map (Map Key)
     * @param ssmClient DB Accessor
     * @return Price List Structure Type From Cache
     */
    public String getPrcListStruTp(Map<String, String> ssmParam, S21SsmBatchClient ssmClient) {

        String prcListStruTp = prcListStruTpCache.get(ssmParam);

        if (!ZYPCommonFunc.hasValue(prcListStruTp)) {
            prcListStruTp = (String) ssmClient.queryObject("getPrcListStruTp", ssmParam);
            prcListStruTpCache.put(ssmParam, prcListStruTp);
        }

        return prcListStruTp;
    }

    /**
     * Get Line Price List From Cache
     * @param ssmParam SSM Map (Map Key)
     * @param ssmClient DB Accessor
     * @return Line Price List From Cache
     */
    @SuppressWarnings("unchecked")
    public List<Map<String, String>> getLinePrcList(Map<String, String> ssmParam, S21SsmBatchClient ssmClient) {

        List<Map<String, String>> linePrcList = linePrcListCache.get(ssmParam);

        if (linePrcList == null) {
            linePrcList = (List<Map<String, String>>) ssmClient.queryObjectList("selectPriceList", ssmParam);

            if (linePrcList == null) {
                linePrcList = new ArrayList<Map<String, String>>();
            }
            linePrcListCache.put(ssmParam, linePrcList);
        }

        return linePrcList;
    }

    /**
     * Get Base Price Equipment From Cache
     * @param ssmParam SSM Map (Map Key)
     * @param ssmClient DB Accessor
     * @return Base Price Equipment From Cache
     */
    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> getBasePrcEquipment(Map<String, Object> ssmParam, S21SsmBatchClient ssmClient) {

        List<Map<String, Object>> basePrcEquipmentList = basePrcEquipmentCache.get(ssmParam);

        if (basePrcEquipmentList == null) {
            basePrcEquipmentList = (List<Map<String, Object>>) ssmClient.queryObjectList("getBasePriceEquipment", ssmParam);

            if (basePrcEquipmentList == null) {
                basePrcEquipmentList = new ArrayList<Map<String, Object>>();
            }
            basePrcEquipmentCache.put(ssmParam, basePrcEquipmentList);
        }

        return basePrcEquipmentList;
    }

    /**
     * Get Sub Price List From Cache
     * @param ssmParam SSM Map (Map Key)
     * @param ssmClient DB Accessor
     * @return Sub Price List From Cache
     */
    @SuppressWarnings("unchecked")
    public List<String> getSubPrcList(Map<String, String> ssmParam, S21SsmBatchClient ssmClient) {

        List<String> subPrcList = subPrcListCache.get(ssmParam);

        if (subPrcList == null) {
            subPrcList = (List<String>) ssmClient.queryObjectList("selectSubPriceList", ssmParam);

            if (subPrcList == null || subPrcList.size() == 0) {
                return null;
            }
            subPrcListCache.put(ssmParam, subPrcList);
        }

        return subPrcList;
    }

    /**
     * Get Price List Config From Cache
     * @param ssmParam SSM Map (Map Key)
     * @param ssmClient DB Accessor
     * @return Price List Config From Cache
     */
    @SuppressWarnings("unchecked")
    public List<Map<String, String>> getPrcListConfig(Map<String, Object> ssmParam, S21SsmBatchClient ssmClient) {

        List<Map<String, String>> prcListConfigList = prcListConfigCache.get(ssmParam);

        if (prcListConfigList == null) {
            prcListConfigList = (List<Map<String, String>>) ssmClient.queryObjectList("getPrcListConfig", ssmParam);

            if (prcListConfigList == null || prcListConfigList.size() == 0) {
                return null;
            }
            prcListConfigCache.put(ssmParam, prcListConfigList);
        }

        return prcListConfigList;
    }

    /**
     * Get Price Lease Type From Cache
     * @param ssmParam SSM Map (Map Key)
     * @param ssmClient DB Accessor
     * @return Price Lease Type From Cache
     */
    public String getPrcLeaseTp(Map<String, String> ssmParam, S21SsmBatchClient ssmClient) {

        String prcLeaseTp = prcLeaseTpCache.get(ssmParam);

        if (prcLeaseTp == null) {
            prcLeaseTp = (String) ssmClient.queryObject("getPrcLeaseTp", ssmParam);

            if (!ZYPCommonFunc.hasValue(prcLeaseTp)) {
                prcLeaseTpCache.put(ssmParam, "");
                return prcLeaseTp;
            }
            prcLeaseTpCache.put(ssmParam, prcLeaseTp);
        }

        return prcLeaseTp;
    }

     // QC#22965 2018/04/11 Add Start
    /**
     * Get Price Rule From Cache
     * @param ssmParam SSM Map (Map Key)
     * @param ssmClient DB Accessor
     * @return Price Rule From Cache
     */
    @SuppressWarnings("unchecked")
    public Map<String, Object> getPriceRule(Map<String, Object> ssmParam, S21SsmBatchClient ssmClient) {

        Map<String, Object> prcRule = prcRuleCache.get(ssmParam);

        if (prcRule == null) {
            prcRule = (Map<String, Object>) ssmClient.queryObject("getPriceRule", ssmParam);

            if (prcRule == null || prcRule.size() == 0) {
                return prcRule;
            }
            prcRuleCache.put(ssmParam, prcRule);
        }

        return prcRule;
    }
    // QC#22965 2018/04/11 Add End
}
