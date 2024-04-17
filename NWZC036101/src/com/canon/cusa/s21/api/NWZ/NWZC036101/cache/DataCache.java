package com.canon.cusa.s21.api.NWZ.NWZC036101.cache;

import java.util.HashMap;
import java.util.Map;

import business.parts.NWZC036101PMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.common.S21ApplicationCacheHolder;
import com.canon.cusa.s21.framework.common.collections.S21LRUMap;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/11   Fujitsu         T.Yoshida       Create          S21_NA#11618 (For Performance)
 * 2017/05/26   Fujitsu         M.Yamada        Update          QC#18663
 * 2019/09/21   Fujitsu         S.Takami        Update          QC#53650
 * 2019/10/10   Fujitsu         S.Takami        Update          QC#54078
 * </pre>
 */
public class DataCache {

    /** My Instance Key in TreadLocal */
    private static final String INSTANCE_KEY = DataCache.class.getName();

    /** S21LRUMap */
    private final S21LRUMap<String, String> varCharConstCache = new S21LRUMap<String, String>();

    /** S21LRUMap */
    private final S21LRUMap<String, Integer> scaleCache = new S21LRUMap<String, Integer>();

    /** S21LRUMap */
    private final S21LRUMap<String, String> custCache = new S21LRUMap<String, String>();

    /** Private Constructor */
    private DataCache() {
    }

    /**
     * Get Singleton Instance
     * @return DataCache
     */
    public static DataCache getInstance() {

        DataCache myInstance = (DataCache) S21ApplicationCacheHolder.get(INSTANCE_KEY);
        if (myInstance == null) {
            myInstance = new DataCache();
            S21ApplicationCacheHolder.put(INSTANCE_KEY, myInstance);
        }
        return myInstance;
    }

    /**
     * Get VarCharConst Value From Cache
     * @param glblCmpyCd Global Company Code
     * @param key Shipping VarCharConst Key
     * @return VarCharConst Value From Cache
     */
    public String getVarCharConstValueFromCache(String glblCmpyCd, String key) {

        String varCharConstValue = varCharConstCache.get(key);

        if (!ZYPCommonFunc.hasValue(varCharConstValue)) {
            varCharConstValue = ZYPCodeDataUtil.getVarCharConstValue(key, glblCmpyCd);
            if (ZYPCommonFunc.hasValue(varCharConstValue)) {
                varCharConstCache.put(key, varCharConstValue);
            }
        }

        return varCharConstValue;
    }

    /**
     * Get Scale From Cache
     * @param param NWZC036101PMsg
     * @param ssmBatchClient S21SsmBatchClient
     * @return Scale From Cache
     */
    public Integer getScaleFromCache(NWZC036101PMsg param, S21SsmBatchClient ssmBatchClient) {

        String glblCmpyCd = param.glblCmpyCd.getValue();
        Integer scale = scaleCache.get(glblCmpyCd);

        if (scale == null) {
            scale = (Integer) ssmBatchClient.queryObject("getScale", param);
            if (scale != null) {
                scaleCache.put(glblCmpyCd, scale);
            }
        }

        return scale;
    }


    /**
     * convCustCdToLocNum
     * @param param NWZC036101PMsg
     * @param locRoleTp location role type
     * @param ssmBatchClient S21SsmBatchClient
     * @return locNum
     */
    public String convCustCdToLocNum(NWZC036101PMsg param, String locRoleTp, S21SsmBatchClient ssmBatchClient) {
        Map<String, String> qPrm = new HashMap<String, String>();
        qPrm.put("glblCmpyCd", param.glblCmpyCd.getValue());
        qPrm.put("billToCustCd", param.billToLocNum.getValue());
        qPrm.put("shipToCustCd", param.shipToLocNum.getValue());
        qPrm.put("locRoleTp", locRoleTp);

        String locNum = custCache.get(qPrm.toString());

        if (locNum == null) {
            locNum = (String) ssmBatchClient.queryObject("getLocNumFromCustCd", qPrm);
            if (locNum != null) {
                custCache.put(qPrm.toString(), locNum);
            }
        }

        return locNum;
    }
    // START 2019/09/21 S.Takami [QC#53650,ADD] => 2019/10/10 S.Takami [QC#54078,MOD] without any comments
    /**
     * <pre>
     * get location name from customer code.m
     * @param param NWZC036101PMsg
     * @param custCd Customer Code (Ship To Customer Code, or Bill To Customer Code
     * @param locRoleTp location role type
     * @param colTp getting column type LOC_NM, ACCT_CD
     * @param ssmBatchClient S21SsmBatchClient
     * @return locNum
     * </pre>
     */
    public String getLocNameFromCustCd(NWZC036101PMsg param, String custCd, String locRoleTp, String colTp, S21SsmBatchClient ssmBatchClient) {
        Map<String, String> qPrm = new HashMap<String, String>();
        qPrm.put("glblCmpyCd", param.glblCmpyCd.getValue());
        qPrm.put("custCd", custCd);
        qPrm.put("locRoleTp", locRoleTp);
        // START 2019/10/10 S.Takami [QC#54078,ADD]
        qPrm.put("colTp", colTp);
        // END 2019/10/10 S.Takami [QC#54078,ADD]

        String locName = custCache.get(qPrm.toString());

        if (locName == null) {
            locName = (String) ssmBatchClient.queryObject("getLocNameFromCustCd", qPrm);
            if (locName != null) {
                custCache.put(qPrm.toString(), locName);
            }
        }

        return locName;
    }
    // END 2019/09/21 S.Takami [QC#53650,ADD]
}
