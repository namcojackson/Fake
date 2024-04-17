/**
 * <Pre>Copyright (c) 2016-2017 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC226001;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import parts.common.EZDTMsg;

import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmEZDClient;

/**
 * <pre>
 * NWZC2260 : Order Import API Query
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/11/21   Fujitsu         T.Ishii         Create          N/A
 * 2017/08/21   Fujitsu         R.Nakamura      Update          QC#19233
 *</pre>
 */
public class NWZC226001Query {

    /**
     * Singleton instance.
     */
    private static final NWZC226001Query myInstance = new NWZC226001Query();

    /** S21SsmBatchClient */
    private S21SsmBatchClient ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

    /** S21SsmZZDClient */
    private S21SsmEZDClient ssmEzdClient = S21SsmEZDClient.getClient(this.getClass());

    protected static NWZC226001Query getInstance() {

        return myInstance;
    }

    /**
     * query map list
     * @param queryId String
     * @param ssmParam Map<String, Object>
     * @return List<Map<String, Object>>
     */
    protected List<Map<String, Object>> queryMapList(String queryId, Map<String, Object> ssmParam) {

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> result = (List<Map<String, Object>>) ssmBatchClient.queryObjectList(queryId, ssmParam);

        if (result == null) {

            return new ArrayList<Map<String, Object>>();
        }

        return result;
    }

    /**
     * query map list
     * @param queryId String
     * @param tMsg EZDTMsg
     * @return List<Map<String, Object>>
     */
    protected List<Map<String, Object>> queryMapList(String queryId, EZDTMsg tMsg) {

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> result = (List<Map<String, Object>>) ssmBatchClient.queryObjectList(queryId, tMsg);

        if (result == null) {

            return new ArrayList<Map<String, Object>>();
        }

        return result;
    }

    /**
     * query map
     * @param queryId String
     * @param ssmParam Map<String, Object>
     * @return List<Map<String, Object>>
     */
    protected Map<String, Object> queryMap(String queryId, Map<String, Object> ssmParam) {

        @SuppressWarnings("unchecked")
        Map<String, Object> result = (Map<String, Object>) ssmBatchClient.queryObject(queryId, ssmParam);

        if (result == null || result.isEmpty()) {

            return null;
        }
        return result;
    }

    /**
     * query string
     * @param queryId String
     * @param ssmParam Map<String, Object>
     * @return String
     */
    protected String queryString(String queryId, Map<String, Object> ssmParam) {

        String result = (String) ssmBatchClient.queryObject(queryId, ssmParam);

        return result;
    }

    /**
     * query string list
     * @param queryId String
     * @param ssmParam Map<String, Object>
     * @return List<String>
     */
    protected List<String> queryStringList(String queryId, Map<String, Object> ssmParam) {

        @SuppressWarnings("unchecked")
        List<String> result = (List<String>) ssmBatchClient.queryObjectList(queryId, ssmParam);

        if (result == null) {

            return new ArrayList<String>();
        }

        return result;
    }

    /**
     * query big decimal
     * @param queryId String
     * @param ssmParam Map<String, Object>
     * @return BigDecimal
     */
    protected BigDecimal queryBigDecimal(String queryId, Map<String, Object> ssmParam) {

        BigDecimal result = (BigDecimal) ssmBatchClient.queryObject(queryId, ssmParam);

        return result;
    }

    // Add Start 2017/08/21 QC#19233
    /**
     * query big decimal list
     * @param queryId String
     * @param ssmParam Map<String, Object>
     * @return List<BigDecimal>
     */
    protected List<BigDecimal> queryBigDecimalList(String queryId, Map<String, Object> ssmParam) {

        @SuppressWarnings("unchecked")
        List<BigDecimal> result = (List<BigDecimal>) ssmBatchClient.queryObjectList(queryId, ssmParam);

        if (result == null) {

            return new ArrayList<BigDecimal>();
        }

        return result;
    }

    /**
     * query int
     * @param queryId String
     * @param ssmParam Map<String, Object>
     * @return int
     */
    protected int queryInt(String queryId, Map<String, Object> ssmParam) {

        int result = (Integer) ssmBatchClient.queryObject(queryId, ssmParam);

        return result;
    }
    // Add End 2017/08/21 QC#19233

    protected S21SsmEZDClient getSsmEZDClient() {
        return this.ssmEzdClient;
    }
    
    /**
     * query map list
     * @param queryId String
     * @param ssmParam Map<String, Object>
     * @return List<Map<String, Object>>
     */
    @SuppressWarnings("unchecked")
    protected List queryObjectList(String queryId, Map<String, Object> ssmParam) {

        List result = (List) ssmBatchClient.queryObjectList(queryId, ssmParam);

        if (result == null) {

            return new ArrayList();
        }

        return result;
    }
}
