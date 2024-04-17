/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.common.NWX.NWXC150001.cache;

import java.math.BigDecimal;
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
 * 2016/06/07   Fujitsu         T.Yoshida       Create          S21_NA#8166 (For Performance)
 * 2018/05/20   Fujitsu         S.Takami        Update          S21_NA#25604
 * 2018/06/05   Fujitsu         S.Takami        Update          S21_NA#25151
 * 2018/06/14   Fujitsu         K.Ishizuka      Update          S21_NA#24294
 * 2018/06/26   Fujitsu         H.Nagashima     Update          S21_NA#23726
 * 2018/07/09   Fujitsu         S.Takami        Update          S21_NA#26895
 * 2018/07/13   Fujitsu         S.Takami        Update          S21_NA#27228
 * 2018/08/21   Fujitsu         S.Takami        Update          S21_NA#26767
 * </pre>
 */
public final class DataCacheForSSM {

    /** My Instance Key in TreadLocal */
    private static final String INSTANCE_KEY = DataCacheForSSM.class.getName();

    /** S21LRUMap */
    private final S21LRUMap<Map<String, String>, Integer> retailWhRelationCache = new S21LRUMap<Map<String, String>, Integer>();

    /** S21LRUMap */
    private final S21LRUMap<Map<String, String>, List<Map<String, String>>> dealWhCache = new S21LRUMap<Map<String, String>, List<Map<String, String>>>();

    /** S21LRUMap */
    private final S21LRUMap<Map<String, String>, Integer> acctCache = new S21LRUMap<Map<String, String>, Integer>();

    /** S21LRUMap */
    private final S21LRUMap<Map<String, String>, Integer> dsAcctCache = new S21LRUMap<Map<String, String>, Integer>();

    // QC#14593 2016/09/28 Add
    /** S21LRUMap */
    private final S21LRUMap<Map<String, Object>, String> svcInstlFlgFromInstInfoCache = new S21LRUMap<Map<String, Object>, String>();

    /** S21LRUMap */
    private final S21LRUMap<Map<String, String>, String> custIstlFlgCache = new S21LRUMap<Map<String, String>, String>();

    /** S21LRUMap */
    private final S21LRUMap<Map<String, String>, String> shopItemFlgCache = new S21LRUMap<Map<String, String>, String>();

    /** S21LRUMap */
    private final S21LRUMap<Map<String, String>, String> defaultAcctFromShipToCustCache = new S21LRUMap<Map<String, String>, String>();

    /** S21LRUMap */
    private final S21LRUMap<Map<String, String>, String> defaultAcctFromBillToCustCache = new S21LRUMap<Map<String, String>, String>();

    /** S21LRUMap */
    private final S21LRUMap<Map<String, Object>, String> lineStsCache = new S21LRUMap<Map<String, Object>, String>();

    /** S21LRUMap */
    private final S21LRUMap<Map<String, Object>, String> lineStsForInboundCache = new S21LRUMap<Map<String, Object>, String>();

    // 2018/05/20 S21_NA#25604 Add Start
    /** S21LRUMap */
    private final S21LRUMap<Map<String, String>, Integer> orderCtxCntCache = new S21LRUMap<Map<String, String>, Integer>();
    // 2018/05/20 S21_NA#25604 Add End

    // 2018/06/05 S21_NA#25151 Add Start
    private final S21LRUMap<Map<String, String>, String> innternalOrdProcCache = new S21LRUMap<Map<String, String>, String>();
    // 2018/06/05 S21_NA#25151 Add End

    /** S21LRUMap */
    private final S21LRUMap<Map<String, String>, Map<String, String>> defWhTmplCache = new S21LRUMap<Map<String, String>, Map<String, String>>();

    // QC#23726 2018/06/25 add Start
    private final S21LRUMap<Map<String, String>, List<String>> custCarrScvLvlRelnCache = new S21LRUMap<Map<String, String>, List<String>>();
    private final S21LRUMap<Map<String, Object>, String> dsBizAreaCache = new S21LRUMap<Map<String, Object>, String>();
    // QC#23726 2018/06/25 add End

    // 2018/07/09 S21_NA#26895 Add Start
    private final S21LRUMap<Map<String, String>, List<String>> overWriteExemptRtlWhCdCache = new S21LRUMap<Map<String, String>, List<String>>();
    // 2018/07/09 S21_NA#26895 Add End

    // 2018/07/13 S21_NA#27228 Add Start
    private final S21LRUMap<Map<String, String>, List<String>> overWriteExemptBaseRtlWhCdCache = new S21LRUMap<Map<String, String>, List<String>>();
    // 2018/07/13 S21_NA#27228 Add Start

    // 2018/08/21 S21_NA#26767 Add Start
    private final S21LRUMap<Map<String, Object>, BigDecimal> svcMachMstrPkBySerNumAndMdseCdCache = new S21LRUMap<Map<String, Object>, BigDecimal>();

    private final S21LRUMap<Map<String, Object>, Map<String, Object>> svcMachMstrPkAndConfigIdBySerNumAndMdseCd = new S21LRUMap<Map<String, Object>, Map<String, Object>>();

    private final S21LRUMap<Map<String, Object>, List<BigDecimal>> svcMachMstrPkByConfigIdAndMdseCd = new S21LRUMap<Map<String, Object>, List<BigDecimal>>();
    // 2018/08/21 S21_NA#26767 Add End

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
     * Get Retail WH Relation Count From Cache
     * @param ssmParam SSM Map (Map Key)
     * @param ssmClient DB Accessor
     * @return Retail WH Relation Count From Cache
     */
    public Integer getRetailWhRelationCnt(Map<String, String> ssmParam, S21SsmBatchClient ssmClient) {

        Integer relationCnt = retailWhRelationCache.get(ssmParam);

        if (relationCnt == null) {
            relationCnt = (Integer) ssmClient.queryObject("checkRetailWhRelation", ssmParam);
            retailWhRelationCache.put(ssmParam, relationCnt);
        }

        return relationCnt;
    }

    /**
     * Get Deal WH List From Cache
     * @param ssmParam SSM Map (Map Key)
     * @param ssmClient DB Accessor
     * @return Deal WH List From Cache
     */
    @SuppressWarnings("unchecked")
    public List<Map<String, String>> getDealWhList(Map<String, String> ssmParam, S21SsmBatchClient ssmClient) {

        List<Map<String, String>> dealWhList = dealWhCache.get(ssmParam);

        if (dealWhList == null) {
            dealWhList = ssmClient.queryObjectList("getDealWhMapping", ssmParam);
            dealWhCache.put(ssmParam, dealWhList);
        }

        return dealWhList;
    }

    /**
     * Get Account Count From Cache
     * @param ssmParam SSM Map (Map Key)
     * @param ssmClient DB Accessor
     * @return Account Count From Cache
     */
    public Integer getAcctCnt(Map<String, String> ssmParam, S21SsmBatchClient ssmClient) {

        Integer acctCnt = acctCache.get(ssmParam);

        if (acctCnt == null) {
            acctCnt = (Integer) ssmClient.queryObject("getAcctCnt", ssmParam);
            acctCache.put(ssmParam, acctCnt);
        }

        return acctCnt;
    }

    /**
     * Get DS Account Count From Cache
     * @param ssmParam SSM Map (Map Key)
     * @param ssmClient DB Accessor
     * @return DS Account Count From Cache
     */
    public Integer getDsAcctCnt(Map<String, String> ssmParam, S21SsmBatchClient ssmClient) {

        Integer dsAcctCnt = dsAcctCache.get(ssmParam);

        if (dsAcctCnt == null) {
            dsAcctCnt = (Integer) ssmClient.queryObject("getDsAcctCnt", ssmParam);
            dsAcctCache.put(ssmParam, dsAcctCnt);
        }

        return dsAcctCnt;
    }

    // QC#14593 2016/09/28 Mod Start
    /**
     * Get Service Install Flag From Inst Info From Cache
     * @param ssmParam SSM Map (Map Key)
     * @param ssmClient DB Accessor
     * @return Service Install Rule Flag From Cache
     */
    public String getSvcIstlRuleFlg(Map<String, Object> ssmParam, S21SsmBatchClient ssmClient) {

        String svcInstRuleFlg = svcInstlFlgFromInstInfoCache.get(ssmParam);

        if (!ZYPCommonFunc.hasValue(svcInstRuleFlg)) {
            svcInstRuleFlg = (String) ssmClient.queryObject("getSvcIstlRuleFlg", ssmParam);
            svcInstlFlgFromInstInfoCache.put(ssmParam, svcInstRuleFlg);
        }

        return svcInstRuleFlg;
    }
    // QC#14593 2016/09/28 Mod End
    /**
     * Get Customer Install Flag From Cache
     * @param ssmParam SSM Map (Map Key)
     * @param ssmClient DB Accessor
     * @return Customer Install Flag From Cache
     */
    public String getCustIstlFlg(Map<String, String> ssmParam, S21SsmBatchClient ssmClient) {

        String custIstlFlg = custIstlFlgCache.get(ssmParam);

        if (!ZYPCommonFunc.hasValue(custIstlFlg)) {
            custIstlFlg = (String) ssmClient.queryObject("getDefaultCustIstlFlg", ssmParam);
            custIstlFlgCache.put(ssmParam, custIstlFlg);
        }

        return custIstlFlg;
    }

    /**
     * Get Shop Item Flag From Cache
     * @param ssmParam SSM Map (Map Key)
     * @param ssmClient DB Accessor
     * @return Shop Item Flag From Cache
     */
    public String getShopItemFlg(Map<String, String> ssmParam, S21SsmBatchClient ssmClient) {

        String shopItemFlg = shopItemFlgCache.get(ssmParam);

        if (!ZYPCommonFunc.hasValue(shopItemFlg)) {
            shopItemFlg = (String) ssmClient.queryObject("getDefaultShopItemFlg", ssmParam);
            shopItemFlgCache.put(ssmParam, shopItemFlg);
        }

        return shopItemFlg;
    }

    /**
     * Get Default Ship To Customer From Cache
     * @param ssmParam SSM Map (Map Key)
     * @param ssmClient DB Accessor
     * @return Default Ship To Customer From Cache
     */
    public String getDefaultAcctFromShipToCust(Map<String, String> ssmParam, S21SsmBatchClient ssmClient) {

        String defaultAcctFromShipToCust = defaultAcctFromShipToCustCache.get(ssmParam);

        if (!ZYPCommonFunc.hasValue(defaultAcctFromShipToCust)) {
            defaultAcctFromShipToCust = (String) ssmClient.queryObject("getDefaultAcctFromShipToCust", ssmParam);
            defaultAcctFromShipToCustCache.put(ssmParam, defaultAcctFromShipToCust);
        }

        return defaultAcctFromShipToCust;
    }

    /**
     * Get Default Bill To Customer From Cache
     * @param ssmParam SSM Map (Map Key)
     * @param ssmClient DB Accessor
     * @return Default Bill To Customer From Cache
     */
    public String getDefaultAcctFromBillToCust(Map<String, String> ssmParam, S21SsmBatchClient ssmClient) {

        String defaultAcctFromBillToCust = defaultAcctFromBillToCustCache.get(ssmParam);

        if (!ZYPCommonFunc.hasValue(defaultAcctFromBillToCust)) {
            defaultAcctFromBillToCust = (String) ssmClient.queryObject("getDefaultAcctFromBillToCust", ssmParam);
            defaultAcctFromBillToCustCache.put(ssmParam, defaultAcctFromBillToCust);
        }

        return defaultAcctFromBillToCust;
    }

    /**
     * Get Line Status From Cache
     * @param ssmParam SSM Map (Map Key)
     * @param ssmClient DB Accessor
     * @return Line Status From Cache
     */
    public String getLineStatus(Map<String, Object> ssmParam, S21SsmBatchClient ssmClient) {

        String lineSts = lineStsCache.get(ssmParam);

        if (!ZYPCommonFunc.hasValue(lineSts)) {
            lineSts = (String) ssmClient.queryObject("getLineStatus", ssmParam);
            lineStsCache.put(ssmParam, lineSts);
        }

        return lineSts;
    }

    /**
     * Get Line Status For Inbound From Cache
     * @param ssmParam SSM Map (Map Key)
     * @param ssmClient DB Accessor
     * @return Line Status For Inbound From Cache
     */
    public String getLineStatusForInbound(Map<String, Object> ssmParam, S21SsmBatchClient ssmClient) {

        String lineSts = lineStsForInboundCache.get(ssmParam);

        if (!ZYPCommonFunc.hasValue(lineSts)) {
            lineSts = (String) ssmClient.queryObject("getLineStatusForInbound", ssmParam);
            lineStsForInboundCache.put(ssmParam, lineSts);
        }

        return lineSts;
    }

    // 2018/05/20 S21_NA#25604 Add Start
    public Integer getOrderCtxCnt(Map<String, String> ssmParam, S21SsmBatchClient ssmClient) {

        Integer rsltCnt = orderCtxCntCache.get(ssmParam);

        if (rsltCnt == null) {
            rsltCnt = (Integer) ssmClient.queryObject("getOrderCtxCnt", ssmParam);
            orderCtxCntCache.put(ssmParam, rsltCnt);
        }

        return rsltCnt;
    }
    // 2018/05/20 S21_NA#25604 Add End

    // 2018/06/05 S21_NA#25151 Add Start
    public String getInternalOrdProcFlg(Map<String, String> ssmParam, S21SsmBatchClient ssmClient) {

        String itrlOrdProcFlg = innternalOrdProcCache.get(ssmParam);

        if (itrlOrdProcFlg == null) {
            itrlOrdProcFlg = (String) ssmClient.queryObject("getInternalOrdProcFlg", ssmParam);
            innternalOrdProcCache.put(ssmParam, itrlOrdProcFlg);
        }

        return itrlOrdProcFlg;
    }
    // 2018/06/05 S21_NA#25151 Add End
    
    // 2018/06/14 S21_NA#24294 Add Start
    /**
     * Get DS Order Type Info From Cache
     * @param ssmParam SSM Map (Map Key)
     * @param ssmClient DB Accessor
     * @return DS Order Type Info From Cache
     */
    @SuppressWarnings("unchecked")
    public Map<String, String> getWhMpngTmpl(Map<String, String> ssmParam, S21SsmBatchClient ssmClient) {

        Map<String, String> defWhTmpl = defWhTmplCache.get(ssmParam);

        if (defWhTmpl == null) {
            List<Map<String, String>> dsOrdTpInfoList = (List<Map<String, String>>) ssmClient.queryObjectList("getWhMpngTmpl", ssmParam);

            if (dsOrdTpInfoList == null || dsOrdTpInfoList.size() == 0) {
                return null;
            }
            defWhTmpl = dsOrdTpInfoList.get(0);
            defWhTmplCache.put(ssmParam, defWhTmpl);
        }

        return defWhTmpl;
    }
    // 2018/06/14 S21_NA#24294 Add End
    // QC#23726 2018/06/25 add Start
    @SuppressWarnings("unchecked")
    public List<String> getCustCarrScvLvlReln(Map<String, String> ssmParam, S21SsmBatchClient ssmClient) {

        List<String> custCarrSvcLvlRelnList = custCarrScvLvlRelnCache.get(ssmParam);

        if (custCarrSvcLvlRelnList == null) {
            custCarrSvcLvlRelnList = (List<String>) ssmClient.queryObjectList("getCustCarrScvLvlReln", ssmParam);

            if (custCarrSvcLvlRelnList.isEmpty()) {
                return null;
            }
            custCarrScvLvlRelnCache.put(ssmParam, custCarrSvcLvlRelnList);
        }

        return custCarrSvcLvlRelnList;
    }
    public String getDsBizArea(Map<String, Object> ssmParam, S21SsmBatchClient ssmClient) {

        String dsBizArea = dsBizAreaCache.get(ssmParam);

        if (dsBizArea == null) {
            dsBizArea = (String) ssmClient.queryObject("getDsBizArea", ssmParam);

            if (dsBizArea.isEmpty()) {
                return null;
            }
            dsBizAreaCache.put(ssmParam, dsBizArea);
        }

        return dsBizArea;
    }
    // QC#23726 2018/06/25 add End

    // 2018/07/09 S21_NA#26895 Add Start
    public List<String> getOverWriteExemptRtlWhCdList(Map<String, String> ssmParam, S21SsmBatchClient ssmClient) {

        List<String> overWriteExemptRtlWhCdList = overWriteExemptRtlWhCdCache.get(ssmParam);

        if (overWriteExemptRtlWhCdList == null) {
            overWriteExemptRtlWhCdList = (List<String>) ssmClient.queryObjectList("getOverWriteExemptRtlWhCdList", ssmParam);

            if (overWriteExemptRtlWhCdList.isEmpty()) {
                return null;
            }
            overWriteExemptRtlWhCdCache.put(ssmParam, overWriteExemptRtlWhCdList);
        }

        return overWriteExemptRtlWhCdList;
    }
    // 2018/07/09 S21_NA#26895 Add End

    // 2018/07/09 S21_NA#27228 Add Start
    public List<String> getOverWriteExemptBaseRtlWhCdList(Map<String, String> ssmParam, S21SsmBatchClient ssmClient) {

        List<String> overWriteExemptBaseRtlWhCdList = overWriteExemptBaseRtlWhCdCache.get(ssmParam);

        if (overWriteExemptBaseRtlWhCdList == null) {
            overWriteExemptBaseRtlWhCdList = (List<String>) ssmClient.queryObjectList("getOverWriteExemptBaseRtlWhCdList", ssmParam);

            if (overWriteExemptBaseRtlWhCdList.isEmpty()) {
                return null;
            }
            overWriteExemptBaseRtlWhCdCache.put(ssmParam, overWriteExemptBaseRtlWhCdList);
        }

        return overWriteExemptBaseRtlWhCdList;
    }
    // 2018/07/09 S21_NA#27228 Add End

    // 2018/08/21 S21_NA#26767 Add Start
    public BigDecimal getSvcMachMstrPkBySerNumAndMdseCd(Map<String, Object> ssmParam, S21SsmBatchClient ssmClient) {
        BigDecimal svcMachMstrPk = this.svcMachMstrPkBySerNumAndMdseCdCache.get(ssmParam);
        if (svcMachMstrPk == null) {
            svcMachMstrPk = (BigDecimal) ssmClient.queryObject("getSvcMachMstrPkBySerNumAndMdseCd", ssmParam);
            if (svcMachMstrPk != null) {
                this.svcMachMstrPkBySerNumAndMdseCdCache.put(ssmParam, svcMachMstrPk);
            }
        }
        return svcMachMstrPk;
    }

    public Map<String, Object> getSvcMachMstrPkAndConfigIdBySerNumAndMdseCd(Map<String, Object> ssmParam, S21SsmBatchClient ssmClient) {

        Map<String, Object> svcMachMstrMap = this.svcMachMstrPkAndConfigIdBySerNumAndMdseCd.get(ssmParam);
        if (svcMachMstrMap == null) {
            svcMachMstrMap = (Map<String, Object>) ssmClient.queryObject("getSvcMachMstrPkAndConfigIdBySerNumAndMdseCd", ssmParam);
            if (svcMachMstrMap != null) {
                this.svcMachMstrPkAndConfigIdBySerNumAndMdseCd.put(ssmParam, svcMachMstrMap);
            }
        }
        return svcMachMstrMap;
    }

    public List<BigDecimal> getSvcMachMstrPkByConfigIdAndMdseCd(Map<String, Object> ssmParam, S21SsmBatchClient ssmClient) {

        List<BigDecimal> svcMachMstrPkList = this.svcMachMstrPkByConfigIdAndMdseCd.get(ssmParam);
        if (svcMachMstrPkList == null) {
            svcMachMstrPkList = (List<BigDecimal>) ssmClient.queryObjectList("getSvcMachMstrPkByConfigIdAndMdseCd", ssmParam);
            if (svcMachMstrPkList != null) {
                this.svcMachMstrPkByConfigIdAndMdseCd.put(ssmParam, svcMachMstrPkList);
            }
        }
        return svcMachMstrPkList;
    }
    // 2018/08/21 S21_NA#26767 Add End
}
