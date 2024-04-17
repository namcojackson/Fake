/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NMZ.NMZC600001.cache;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.canon.cusa.s21.framework.common.S21ApplicationCacheHolder;
import com.canon.cusa.s21.framework.common.collections.S21LRUMap;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/08/10   Fujitsu         T.Yoshida       Create          N/A
 * 2017/02/22   Fujitsu         H.Nagashima     Update          QC#17252
 * </pre>
 */
public final class DataCacheForSSM {

    /** My Instance Key in Tread Local */
    private static final String INSTANCE_KEY = DataCacheForSSM.class.getName();

    /** S21LRUMap */
    private final S21LRUMap<Map<String, Object>, Map<String, Object>> billInProcAmtCache = new S21LRUMap<Map<String, Object>, Map<String, Object>>();

    /** S21LRUMap */
    private final S21LRUMap<Map<String, Object>, Map<String, Object>> acctInProcAmtCache = new S21LRUMap<Map<String, Object>, Map<String, Object>>();

    //QC#15306 add Start
    /** S21LRUMap */
    private final S21LRUMap<String, Integer> aftDeclPntDigitNumCache = new S21LRUMap<String, Integer>();
    //QC#15306 add End
    //QC#17252 add Start
    /** S21LRUMap */
    private final S21LRUMap<Map<String, Object>, BigDecimal> acctCrLimitAmtCache = new S21LRUMap<Map<String, Object>, BigDecimal>();
    /** S21LRUMap */
    private final S21LRUMap<Map<String, String>, String> dsAcctNumCache = new S21LRUMap<Map<String, String>, String>();
    //QC#17252 add End

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
     * Get Bill InProc Amt From Cache
     * @param ssmParam SSM Map (Map Key)
     * @param ssmClient DB Accessor
     * @return Bill InProc Amt From Cache
     */
    @SuppressWarnings("unchecked")
    public Map<String, Object> getBillInProcAmtFromCache(Map<String, Object> ssmParam, S21SsmBatchClient ssmClient) {

        Map<String, Object> billInProcAmtInfo = billInProcAmtCache.get(ssmParam);

        if (billInProcAmtInfo == null) {
            List<Map<String, Object>> billInProcAmtInfoList = ssmClient.queryObjectList("getBillInProcAmt", ssmParam);

            if (billInProcAmtInfoList == null || billInProcAmtInfoList.size() == 0) {
                return new HashMap<String, Object>();
            }
            billInProcAmtInfo = billInProcAmtInfoList.get(0);
            billInProcAmtCache.put(ssmParam, billInProcAmtInfo);
        }

        return billInProcAmtInfo;
    }

    /**
     * Get Account InProc Amt From Cache
     * @param ssmParam SSM Map (Map Key)
     * @param ssmClient DB Accessor
     * @return Account InProc Amt From Cache
     */
    @SuppressWarnings("unchecked")
    public Map<String, Object> getAcctInProcAmtFromCache(Map<String, Object> ssmParam, S21SsmBatchClient ssmClient) {

        Map<String, Object> acctInProcAmtInfo = acctInProcAmtCache.get(ssmParam);

        if (acctInProcAmtInfo == null) {
            List<Map<String, Object>> acctInProcAmtInfoList = ssmClient.queryObjectList("getAcctInProcAmt", ssmParam);

            if (acctInProcAmtInfoList == null || acctInProcAmtInfoList.size() == 0) {
                return new HashMap<String, Object>();
            }
            acctInProcAmtInfo = acctInProcAmtInfoList.get(0);
            acctInProcAmtCache.put(ssmParam, acctInProcAmtInfo);
        }

        return acctInProcAmtInfo;
    }

    //QC#15306 add Start
    public Integer getAftDeclPntDigitNumFromCache(String glblCmpyCd, S21SsmBatchClient ssmClient) {

        Integer aftDeclPntDigitNum = aftDeclPntDigitNumCache.get(glblCmpyCd);

        if (aftDeclPntDigitNum == null) {
            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd",  glblCmpyCd);
            aftDeclPntDigitNum = (Integer) ssmClient.queryObject("getAftDeclPntDigitNum", ssmParam);

            if (aftDeclPntDigitNum == null) {
                return null;
            }
            aftDeclPntDigitNumCache.put(glblCmpyCd, aftDeclPntDigitNum);
        }

        return aftDeclPntDigitNum;
    }
    //QC#15306 add End
    //QC#17252 add Start
    /**
     * Get Account Credit Limit Amount From Cache
     * @param ssmParam SSM Map (Map Key)
     * @param ssmClient DB Accessor
     * @return Account Credit Limit Amount From Cache
     */
    public BigDecimal getAcctCrLimitAmtFromCache(Map<String, Object> ssmParam, S21SsmBatchClient ssmClient) {

        BigDecimal acctCrLimitAmt = acctCrLimitAmtCache.get(ssmParam);

        if (acctCrLimitAmt == null) {
            acctCrLimitAmt = (BigDecimal) ssmClient.queryObject("getAcctCrLimitAmt", ssmParam);

            if (acctCrLimitAmt == null) {
                return null;
            }
            acctCrLimitAmtCache.put(ssmParam, acctCrLimitAmt);
        }

        return acctCrLimitAmt;
    }
    /**
     * Get Account Number From Cache
     * @param ssmParam SSM Map (Map Key)
     * @param ssmClient DB Accessor
     * @return Account Number From Cache
     */
    @SuppressWarnings("unchecked")
    public String getDsAcctNumCache(Map<String, String> ssmParam, S21SsmBatchClient ssmClient) {

        String dsAcctNum = dsAcctNumCache.get(ssmParam);

        if (dsAcctNum == null) {
            List<String> dsAcctNumList = (List<String>) ssmClient.queryObjectList("getDsAcctNum", ssmParam);

            if (dsAcctNumList.isEmpty()) {
                return null;
            }

            dsAcctNum = dsAcctNumList.get(0);
            dsAcctNumCache.put(ssmParam, dsAcctNum);
        }

        return dsAcctNum;
    }

    //QC#17252 add End
}
