/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC004001.cache;

import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.common.S21ApplicationCacheHolder;
import com.canon.cusa.s21.framework.common.collections.S21LRUMap;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/01/04   Fujitsu         T.Yoshida       Create          S21_NA#14512
 * </pre>
 */
public final class DataCacheForValidation extends LocalDataCacheBase {

    /** My Instance Key in Tread Local */
    private static final String INSTANCE_KEY = DataCacheForValidation.class.getName();

    /** S21LRUMap */
    private final S21LRUMap<String, String> varCharConstValueCache = new S21LRUMap<String, String>();

    /** S21LRUMap */
    private final S21LRUMap<Map<String, String>, Boolean> allLineCreditDataCache = new S21LRUMap<Map<String, String>, Boolean>();

    /** S21LRUMap */
    private final S21LRUMap<Map<String, String>, Boolean> exemptionDataCache = new S21LRUMap<Map<String, String>, Boolean>();

    /** Private Constructor */
    private DataCacheForValidation() {
    }

    /**
     * Get Singleton Instance
     * @return DataCacheForValidation
     */
    public static DataCacheForValidation getInstance() {

        DataCacheForValidation myInstance = (DataCacheForValidation) S21ApplicationCacheHolder.get(INSTANCE_KEY);
        if (myInstance == null) {
            myInstance = new DataCacheForValidation();
            S21ApplicationCacheHolder.put(INSTANCE_KEY, myInstance);
        }
        return myInstance;
    }

    /**
     * Get VarCharConst Value From Cache
     * @param glblCmpyCd Grobal Company Code
     * @param varCharConstNm VarCharConst Name
     * @return VarCharConstValue From Cache
     */
    public String getVarCharConstValue(String glblCmpyCd, String varCharConstNm) {

        String varCharConstValue = varCharConstValueCache.get(varCharConstNm);

        if (!ZYPCommonFunc.hasValue(varCharConstValue)) {
            varCharConstValue = ZYPCodeDataUtil.getVarCharConstValue(varCharConstNm, glblCmpyCd);
            varCharConstValueCache.put(varCharConstNm, varCharConstValue);
        }

        return varCharConstValue;
    }

    /**
     * Check All Line Credit From Cache
     * @param ssmParam SSM Map (Map Key)
     * @param ssmClient DB Accessor
     * @return Check All Line Credit From Cache
     */
    public boolean isAllLineCredit(Map<String, String> ssmParam, S21SsmBatchClient ssmClient) {

        Boolean isAllLineCredit = allLineCreditDataCache.get(ssmParam);
        if (isAllLineCredit != null) {
            return isAllLineCredit.booleanValue();
        }

        boolean allLineCreditData = (Integer) ssmClient.queryObject("cntNotAllLineCredit", ssmParam) == 0;
        allLineCreditDataCache.put(ssmParam, Boolean.valueOf(allLineCreditData));

        return allLineCreditData;
    }

    /**
     * Check Exemption From Cache
     * @param ssmParam SSM Map (Map Key)
     * @param ssmClient DB Accessor
     * @return Chech Exemption From Cache
     */
    public boolean hasExemption(Map<String, String> ssmParam, S21SsmBatchClient ssmClient) {

        Boolean hasExemption = exemptionDataCache.get(ssmParam);
        if (hasExemption != null) {
            return hasExemption.booleanValue();
        }

        boolean exemptionData = (Integer) ssmClient.queryObject("hasExemption", ssmParam) > 0;
        exemptionDataCache.put(ssmParam, Boolean.valueOf(exemptionData));

        return exemptionData;
    }
}
